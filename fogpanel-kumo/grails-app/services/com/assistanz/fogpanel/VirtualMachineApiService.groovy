package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.virtualmachine.VirtualMachineResponse
import groovy.json.JsonBuilder
import com.assistanz.cloud.cloudstack.iso.CSIsoService
import com.assistanz.cloud.cloudstack.iso.AttachISOResponse
import com.assistanz.cloud.cloudstack.iso.DetachISOResponse
import com.assistanz.cloud.cloudstack.iso.IsoResponse
import com.assistanz.cloud.cloudstack.nic.CSNicService
import com.assistanz.fogpanel.GeneralConstants;
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.volume.CSVolumeService
import com.assistanz.cloud.cloudstack.volume.VolumeResponse
import java.net.URL;
import java.util.StringTokenizer;
import com.assistanz.fogpanel.NotificationService
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.text.DateFormat
import javax.xml.bind.DatatypeConverter;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse
import com.assistanz.cloud.cloudstack.securitygroup.CSSecurityGroupService
import javax.crypto.Cipher;
import java.security.Key;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import java.io.StringReader;
import org.bouncycastle.util.encoders.Base64;
import  com.assistanz.fogpanel.CloudStackException;
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory;

@Transactional
class VirtualMachineApiService { 
    private static final log = LogFactory.getLog(this)

    def securityGroupServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSSecurityGroupService cSSecurityGroupService = new CSSecurityGroupService(server);
        
        return cSSecurityGroupService;
    }
    
    def virtualMachineServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        return csVirtualMachineService;
    }
    
    def apiCreateVM(computingofferreferenceid, firewallid, templatereferenceid, username, zonereferenceid, billingtype) { 
        try { 
            def firewallResponse = createFirewall(firewallid, username);
            Date vmDate = Calendar.getInstance().getTime()
            def vmCurrentTime = new Timestamp(vmDate.getTime())
            Date date = new Date() 
            def currentDate = date.getTime();
            if(firewallResponse) {                                                                      
                HashMap<String,String> optional = new HashMap<String,String>();                        
                def computingOffer = ComputingOffer.findByOfferReferenceId(computingofferreferenceid);
                def template = Template.findByTemplateReferenceId(templatereferenceid);
                def account = Account.findByUserName(username);
                def user = User.findByUsername(username);
                def keyPair = SSHKeys.findWhere(account: account, isDefault: true);
                def zone = Zone.findByReferenceZoneId(zonereferenceid);
                def accId = account.id;
                def name =  "H"+accId.toString() +"-"+  currentDate.toString();                
                optional.put("hypervisor", template.hypervisor);            
                optional.put("displayname", new String(name));      
                optional.put("securitygroupids", new String(firewallResponse[0].securityGroupId));
                optional.put("account", new String(account.userName));
                optional.put("domainid", new String(account.domain.referenceId));            
                optional.put("keypair", keyPair.name);       
                optional.put("name", new String(name));
                def response =  virtualMachineServer().deployVirtualMachine(computingofferreferenceid, templatereferenceid, zonereferenceid, optional);
                def newVirtualMachine = new VirtualMachine();     
                newVirtualMachine.computingOffer = computingOffer;
                newVirtualMachine.template = template;
                newVirtualMachine.zone = zone;
                newVirtualMachine.upgradedDate = vmCurrentTime;
                newVirtualMachine.displayName = name.toString()
                newVirtualMachine.name = name.toString();
                newVirtualMachine.hypervisor = template.hypervisor;
                newVirtualMachine.cluster = computingOffer.cluster;
                newVirtualMachine.deleted = false;  
                newVirtualMachine.sshKey = keyPair;
                newVirtualMachine.referenceId = response.virtualMachineId;    
                newVirtualMachine.networkRead = 0.0; 
                newVirtualMachine.networkWrite = 0.0; 
                newVirtualMachine.securityGroupReferenceId = firewallResponse[0].securityGroupId;
                newVirtualMachine.job = response.jobId; 
                newVirtualMachine.firstRun = true;                       
                newVirtualMachine.owner = account;                                
                newVirtualMachine.user = user;
                if(billingtype) {
                    newVirtualMachine.billingType = billingtype;
                }
                def jobResponse = virtualMachineServer().virtualMachineJobResult(response.jobId)
                if(jobResponse.asychronousJobProgressStatus == "0") {
                    newVirtualMachine.state = "in progress"
                    newVirtualMachine.referenceId = jobResponse.asychronousJobInstanceId;                     
                }                  
                newVirtualMachine.save(flush: true); 
                if (!newVirtualMachine.save()) {
                    newVirtualMachine.errors.allErrors.each { println it }
                }
                if (newVirtualMachine.hasErrors()) {
                    throw new ValidationException(newVirtualMachine.errors.allErrors);
                }
                
                addFirewallRules(firewallid, firewallResponse[0].securityGroupId, username);
                ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();  
                HashMap<String,String> result = new HashMap<String,String>();
                result.put("result",  "pending");
                result.put("referenceId",  newVirtualMachine.referenceId);
                result.put("status", newVirtualMachine.state);
                result.put("jobId", newVirtualMachine.referenceId);
                result.put("name", newVirtualMachine.displayName);
                result.put("computingOffer", newVirtualMachine.computingOffer.name);
                result.put("hypervisor", newVirtualMachine.hypervisor);
                result.put("template", newVirtualMachine.template.name);                
                result.put("user", newVirtualMachine.user.username);
                result.put("zoneName", newVirtualMachine.zone.aliasName);
                result.put("osType", newVirtualMachine.template.osType.name);
                result.put("securityGroupReferenceId", newVirtualMachine.securityGroupReferenceId);
                resultList.add(result);
                log.info("Api, VM: ${newVirtualMachine.referenceId} created in pending status with job id: ${newVirtualMachine.referenceId}") 
                log.info("API create VM, Firewall: ${firewallResponse[0].securityGroupId} created for VM: ${newVirtualMachine.referenceId}") 
                return resultList;        
            }  
        } catch(CloudStackException cloudStackException) {
            throw new CloudStackException(cloudStackException)
        } catch (Exception ex) {
            throw new RuntimeException(ex)
        }              
    }
    
    def addFirewallRules(firewallid, securityGroupReferenceId, username) {        
        def account = Account.findByUserName(username);
        if(account) {
            def result = SecurityGroupTemplateRule.findAllWhere(securityGroupTemplate: SecurityGroupTemplate.get(firewallid))                    
            if(result.size() != 0) {
                for(int index = 0; index < result.size(); index++) {
                    try {
                        HashMap<String,String> optional = new HashMap<String,String>();
                        def item = result[index];                                                            
                        optional.put("account", new String(account.userName));
                        optional.put("domainid", new String(account.domain.referenceId));
                        if(item.securityGroupPort.name() == "ICMP") {                   
                            optional.put("icmpcode", item.endPort.toString());
                            optional.put("icmptype", item.startPort.toString());
                        } else if(item.securityGroupPort.name() == "TCP" || item.securityGroupPort.name() == "UDP") {
                            optional.put("endport", item.endPort.toString());
                            optional.put("startport", item.startPort.toString());
                        }             
                        optional.put("protocol", new String(item.securityGroupPort.name()));
                        optional.put("securitygroupid", new String(securityGroupReferenceId));
                        if(item.cidr != "") {
                            optional.put("cidrlist", new String(item.cidr));
                        } else {          
                        }  
                        def response = ""
                        def jobResponse = ""                    
                        if(item.securityGroupType.name() == "INGRESS") {
                            response = securityGroupServer().authorizeSecurityGroupIngress(optional);
                            jobResponse = securityGroupServer().securitygroupJobResult(response.jobId);
                        } else {
                            response = securityGroupServer().authorizeSecurityGroupEgress(optional);            
                            jobResponse = securityGroupServer().securitygroupJobResult(response.jobId);
                        }
                        log.info("API create VM, ${item.securityGroupType.name()} rule created for Firewall: ${securityGroupReferenceId}") 
                    } catch(RuntimeException ex) {
                        Console.print(ex);
                    } catch (Exception ex) {
                        Console.print(ex);
                    }
                }
            }     
        }                
    }
    def createFirewall(firewallid, username) {       
        def account = Account.findByUserName(username);
        def currentFirewall = SecurityGroupTemplate.get(firewallid);
        Date date = new Date() 
        def currentDate = date.getTime();
        ArrayList<ArrayList<String>> securityGroup = new ArrayList<ArrayList<String>>();  
        HashMap<String,String> optional = new HashMap<String,String>();
        HashMap<String,String> securityGroupItem = new HashMap<String,String>();
        if(account) {
            if(currentFirewall) {
                def name = currentDate.toString() + "_" + currentFirewall.name;                
                optional.put("account", new String(account.userName));
                optional.put("domainid", new String(account.domain.referenceId));
                optional.put("description", new String(currentFirewall.description)); 
                def response = securityGroupServer().createSecurityGroup(name, optional);                
                securityGroupItem.put("securityGroupId", response.securityGroupId);
                securityGroupItem.put("securityGroupName", response.securityGroupName);
                securityGroupItem.put("description", response.description);
                securityGroupItem.put("securityGroupAccount", response.securityGroupAccount);
                securityGroup.add(securityGroupItem);    
                return securityGroup;
            } else {
                throw new FirewallNotFoundException("{'errorCode':'2000' ,'message':'Invalid Firewall value'}");
            }
            
        } else {
            throw new InvalidUserNameException("{'errorCode':'2000' ,'message':'Invalid username value'}");
        }
    }
    
    def getVm(referenceId) {        
        try {        
            ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
            HashMap<String,String> result = new HashMap<String,String>(); 
            def jobStatus = "";
            def currentResource = ""                                                  
            currentResource = VirtualMachine.findByReferenceId(referenceId)
            if(currentResource) {
                if(currentResource.state == "Running") {
                    jobStatus = "success";                        
                } else if(currentResource.state == "Starting") {
                    jobStatus = "pending";
                } else {
                    jobStatus = "failed";
                    result.put("message",  "Cannot create VM , contact admin");
                    result.put("errorCode",  "4000");                                                 
                }                                                         
                result.put("result",  jobStatus);                    
                result.put("referenceId",  currentResource.referenceId);                        
                result.put("name", currentResource.displayName);            
                result.put("user", currentResource.user.username);            
                resultList.add(result);
                return resultList;   
            } else {
                throw new VirtualMachineNotFoundException("Invalid referenceId");
            }                                                                      
        } catch (CloudStackException cloudStackException) {
            throw new CloudStackException(cloudStackException)        
        } catch(Exception ex) {
            throw new RuntimeException(ex)
        }   
    }
}
