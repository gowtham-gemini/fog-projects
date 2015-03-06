package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.securitygroup.CSSecurityGroupService
import com.assistanz.cloud.cloudstack.SecurityGroupResponse
import com.assistanz.cloud.cloudstack.EgressRuleResponse
import com.assistanz.cloud.cloudstack.IngressRuleResponse
import grails.converters.deep.JSON
import java.sql.Array
import com.assistanz.cloud.cloudstack.securitygroup.SecuritygroupJobResultResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory;

@Transactional
class SecurityGroupService {
    
    def springSecurityService;
    LicenseValidationService licenseValidationService
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

    def list() {
        try { 
            def response; 
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()       
            HashMap<String,String> optional = new HashMap<String,String>();
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                optional.put("listall", "true");
                response = securityGroupServer().listSecurityGroups(optional);
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                optional.put("account", new String(user.account.accountIdentifier));
                optional.put("domainid", new String(user.account.domain.referenceId));
                response = securityGroupServer().listSecurityGroups(optional);
            } 
            ArrayList<ArrayList<String>> securityGroupList = new ArrayList<ArrayList<String>>();
            for(Iterator<SecurityGroupResponse> iter = response.securityGroups.iterator(); iter.hasNext();) {
                def data = iter.next();         
                HashMap securityGroup = new HashMap();
                securityGroup.put("securityGroupId",data.id);
                securityGroup.put("securityGroupName", data.name);
                def virtualMachine = VirtualMachine.findAllBySecurityGroupReferenceId(data.id);
                def vmListArray = []
                for(int vm=0; vm < virtualMachine.size(); vm++) { 
                    def vmItem = virtualMachine[vm]; 
                    vmListArray[vm] = vmItem.displayName;
                }       
                securityGroup.put("allocatedVm", vmListArray);
                securityGroupList.add(securityGroup);
            }
            return securityGroupList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def clean() {
//        HashMap<String,String> optional = new HashMap<String,String>();
//        optional.put("listall", "true");
//        def response = securityGroupServer().listSecurityGroups(optional);
        
        def vmList = VirtualMachine.findAll()
        for(def vm: vmList) {
            try {
                if(vm.deleted == true) {
//                    HashMap<String,String> secOptional = new HashMap<String,String>();
//                    secOptional.put("id", vm.securityGroupReferenceId);
//                    def securityGroupDeleteResponse = securityGroupServer().deleteSecurityGroup(secOptional);
                }
            } catch( Exception e ) {
                continue; // even if there is a processing error with a directory
            }
        }
        cleanSecurityGroupByAccount();        
    }   
        
    def cleanSecurityGroupByAccount() {
        
        def accountList = Account.withCriteria { 
            ne("accountType", AccountType.values()[2])              
        }
        for(def account: accountList) {
          
            try {
            
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", new String(account.accountIdentifier));
                optional.put("domainid", new String(account.domain.referenceId));
                def response = securityGroupServer().listSecurityGroups(optional);

                for(Iterator<SecurityGroupResponse> iter = response.securityGroups.iterator(); iter.hasNext();) {
                    def data = iter.next();         
                    HashMap securityGroup = new HashMap();
                    def virtualMachine = VirtualMachine.findAllWhere(securityGroupReferenceId : data.securityGroupId, deleted: false);
                    if(!virtualMachine) {
                        HashMap<String,String> secOptional = new HashMap<String,String>();
                        secOptional.put("id", data.securityGroupId);
                        def securityGroupDeleteResponse = securityGroupServer().deleteSecurityGroup(secOptional);
                    }
                }
            
            } catch( Exception e ) {
                continue; // even if there is a processing error with a directory
            }
        }
        log.info("SecurityGroupClean Cron Completed");
    }
    
    def list(String referenceId) {
        try { 
            def response;
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()       
            HashMap<String,String> optional = new HashMap<String,String>();
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                optional.put("listall", "true");
                optional.put("id", referenceId);
                response = securityGroupServer().listSecurityGroups(optional);
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                optional.put("id", referenceId);
                optional.put("account", new String(user.account.accountIdentifier));
                optional.put("domainid", new String(user.account.domain.referenceId));
                response = securityGroupServer().listSecurityGroups(optional);
            } 
            return response;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def create(String requestBody) {
        try { 
            
            licenseValidationService.authorizeAction(FogPanelService.SSH_KEY_DELETE)
            def result = ""
            def response 
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            optional.put("description", new String(requestData.description));  
            ArrayList<ArrayList<String>> securityGroup = new ArrayList<ArrayList<String>>();   
            HashMap<String,String> securityGroupItem = new HashMap<String,String>();
            def name = requestData.vmName + "_" + requestData.name
            HashMap<String,String> listOptional = new HashMap<String,String>();
            listOptional.put("account", new String(user.account.accountIdentifier));
            listOptional.put("domainid", new String(user.account.domain.referenceId));
            listOptional.put("securitygroupname", new String(name));
            listOptional.put("description", requestData.description);
            
            def listSecurityGroupResponse = securityGroupServer().listSecurityGroups(listOptional);
            if(listSecurityGroupResponse.securityGroups.size() == 0) {
                result = "successfully";
                response = securityGroupServer().createSecurityGroup(name, optional);
                securityGroupItem.put("result", "OK");
                securityGroupItem.put("securityGroupId", response.id);
                securityGroupItem.put("securityGroupName", response.name);
                securityGroupItem.put("description", response.description);
                securityGroupItem.put("securityGroupAccount", response.account);
                securityGroup.add(securityGroupItem);
                
            } else {
                securityGroupItem.put("result", "Exist");
                securityGroupItem.put("securityGroupId", listSecurityGroupResponse.securityGroups[0].id);
                securityGroup.add(securityGroupItem);
            } 
            log.info("Firewall created ${result}, firewall")   
            return securityGroup; 
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def deleteIngress(String ingressReferenceId) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.SECURITY_GROUP_DELETE_INGRESS)
            
            def ingressDeleteResponse = securityGroupServer().revokeSecurityGroupIngress(ingressReferenceId);
            log.info("Firewall inbound : ${ingressReferenceId} rule deleted")   
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def deleteEgress(String ingressReferenceId) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.SECURITY_GROUP_DELETE_EGRESS)
            
            def egressDeleteResponse = securityGroupServer().revokeSecurityGroupEgress(ingressReferenceId);
            log.info("Firewall outbound : ${ingressReferenceId} rule deleted")               
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }    
    def deleteSecurityGroup(String referenceId) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.SECURITY_GROUP_DELETE)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("id", referenceId);            
            def securityGroupDeleteResponse = securityGroupServer().deleteSecurityGroup(optional);
            log.info("Firewall : ${referenceId} deleted")   
            return securityGroupDeleteResponse.success
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def ingress(String requestBody) {
        try { 
            
            licenseValidationService.authorizeAction(FogPanelService.SECURITY_GROUP_ADD_INGRESS)
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            if(requestData.protocol == "ICMP") {
                //end
                optional.put("icmpcode", new String(requestData.endPort));
                //start
                optional.put("icmptype", new String(requestData.startPort));
            } else if(requestData.protocol == "TCP" || requestData.protocol == "UDP") {
                optional.put("endport", new String(requestData.endPort));
                optional.put("startport", new String(requestData.startPort));
            }             
            optional.put("protocol", new String(requestData.protocol));
            optional.put("securitygroupid", new String(requestData.securityGroupId));
            if(requestData.cidr != "") {
                optional.put("cidrlist", new String(requestData.cidr));
            } else {
                throw new Exception("cidr required");            
            }
            
            
            def response = securityGroupServer().authorizeSecurityGroupIngress(optional);
//            String jobId = new String(response[0].jobId);
            def jobResponse = securityGroupServer().securitygroupJobResult(response.jobId);
            
            ArrayList<ArrayList<String>> ingressJobList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            item.put("ruleType", GeneralConstants.INGRESS);
            ingressJobList.add(item);
            log.info("Firewall inbound rule added and returned job")   
            return ingressJobList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }    
    def egress(String requestBody) {
        try { 
            
            licenseValidationService.authorizeAction(FogPanelService.SECURITY_GROUP_ADD_EGRESS)
            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            if(requestData.protocol == "ICMP") {
                //end
                optional.put("icmpcode", new String(requestData.endPort));
                //start
                optional.put("icmptype", new String(requestData.startPort));
            } else if(requestData.protocol == "TCP" || requestData.protocol == "UDP") {
                optional.put("endport", new String(requestData.endPort));
                optional.put("startport", new String(requestData.startPort));
            }             
            optional.put("protocol", new String(requestData.protocol));
            optional.put("securitygroupid", new String(requestData.securityGroupId));
            if(requestData.cidr != "") {
                optional.put("cidrlist", new String(requestData.cidr));
            } else {
                throw new Exception("cidr required");
            }
            
            def response = securityGroupServer().authorizeSecurityGroupEgress(optional);            
            def jobResponse = securityGroupServer().securitygroupJobResult(response.jobId);            
            ArrayList<ArrayList<String>> ingressJobList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            item.put("ruleType", GeneralConstants.EGRESS);            
            ingressJobList.add(item);
            log.info("Firewall outbound rule added and returned job")   
            return ingressJobList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }        
    def job(String requestBody) {
        try {            
            // convert string to json object
            def jobResult = ""
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> securityGroupResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = securityGroupServer().securitygroupJobResult(requestData.jobId);  
            if(jobResponse.asychronousJobStatus == "0") {
                jobResult = "Pending"
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                securityGroupResult.add(item);
            } else if(jobResponse.asychronousJobStatus == "1") {
                jobResult = "Success"
                def value;
                if(requestData.ruleType == GeneralConstants.INGRESS) {                    
                    for(Iterator<SecuritygroupJobResultResponse> ingressResponse = jobResponse.ingressRules.iterator(); ingressResponse.hasNext();) {
                        def data = ingressResponse.next(); 
                        HashMap<String,String> item = new HashMap<String,String>()
                        item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                        item.put("securityGroupRuleId", data.ruleId);
                        item.put("startPort", data.startPort);
                        item.put("endPort", data.endPort);
                        item.put("cidr", data.cidr);
                        item.put("protocol", data.protocol);                   
                        securityGroupResult.add(item);      
                    }   
                } else if(requestData.ruleType == GeneralConstants.EGRESS) {
                    jobResult = "Success"
                    for(Iterator<SecuritygroupJobResultResponse> ingressResponse = jobResponse.egressRules.iterator(); ingressResponse.hasNext();) {
                        def data = ingressResponse.next(); 
                        HashMap<String,String> item = new HashMap<String,String>()
                        item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                        item.put("securityGroupRuleId", data.ruleId);
                        item.put("startPort", data.startPort);
                        item.put("endPort", data.endPort);
                        item.put("cidr", data.cidr);
                        item.put("protocol", data.protocol);                   
                        securityGroupResult.add(item);      
                    }                       
                }                            
            } else if(jobResponse.asychronousJobStatus == "2") {
                jobResult = "Failed"
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errortext);
                securityGroupResult.add(item);          
            }
            log.info("Job for Firewall ${requestData.ruleType} rule added ${jobResult}")   
            return securityGroupResult;             
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }             
    }         
}
