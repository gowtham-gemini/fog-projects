package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.CloudStackServer.CloudStackServerException
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import com.assistanz.cloud.cloudstack.networkacl.CSNetworkACLService
import com.assistanz.cloud.cloudstack.networkacl.NetworkAclListResponse
import com.assistanz.cloud.cloudstack.networkacl.NetworkAclResponse
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;

@Transactional
class NetworkAclService {

    def springSecurityService;
    
    def asyncJobServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSAsyncJobService jobService = new CSAsyncJobService(server);
        
        return jobService;
        
    }
        
    def networkAclServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        
        CSNetworkACLService csNetworkACLService = new CSNetworkACLService(server);
        
        return csNetworkACLService;
        
    }   
    
    def listNetworkAcl(vpcId, referenceId) {        
         try {            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            HashMap<String,String> optional = new HashMap<String,String>();
            
            if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                optional.put("account", new String(user.account.accountIdentifier));
                optional.put("domainid", new String(user.account.domain.referenceId));
            }            
            if(vpcId) {
                optional.put("vpcid", vpcId);
            }
            if(referenceId) {
                optional.put("id", referenceId);
            }            
            def response = networkAclServer().listNetworkACLLists(optional)             
            ArrayList<ArrayList<String>> networkAclResponse = new ArrayList<ArrayList<String>>();
            
            for(Iterator <NetworkAclListResponse> iter = response.networkAclLists.iterator(); iter.hasNext();) {
                def data = iter.next();                
                def vpc = VPC.findByReferenceId(data.vpcId)                
                HashMap item = new HashMap();  
                item.put("name", data.name);
                item.put("referenceId", data.id);                    
                item.put("vpc", vpc?.name);
                item.put("description", data.name);
                item.put("zone", vpc?.zone?.aliasName);
                item.put("tiers", "");      
                networkAclResponse.add(item)                                                                                                                   
            }
            return networkAclResponse    
             
        } catch (CloudStackServerException ex) { 

                ArrayList<ArrayList<String>> networkAclResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", GeneralConstants.RESULT_FAILURE);
                item.put("message", ex.localizedMessage.substring(4));
                networkAclResponse.add(item)

                return networkAclResponse    

        } catch (Exception ex) {
            ArrayList<ArrayList<String>> networkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            networkAclResponse.add(item)

            return networkAclResponse    
        }  
        
    }
    
    def changeACL(requestData) {
        try {
                
            HashMap<String,String> optional = new HashMap<String,String>();
            if(requestData.networkId) {
               optional.put("networkid", requestData.networkId);  
            }
            if(requestData.privateGatewayId) {
               optional.put("gatewayid", requestData.privateGatewayId);  
            }
                        
            def response = networkAclServer().replaceNetworkACLList(requestData.aclId, optional)
           
            ArrayList<ArrayList<String>> networkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            networkAclResponse.add(item)
           
            return networkAclResponse     
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    
    def createNetworkAcl(requestData) {
        
        try {
        
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("description", requestData.desc);

    //        createVPC(String cidr, String displayText, String name,
    //            String vpcOfferingId, String zoneId, HashMap<String, String> optional)

            def response = networkAclServer().createNetworkACLList(requestData.name, requestData.vpcId, optional);
                          
            ArrayList<ArrayList<String>> networkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            networkAclResponse.add(item)

//            log.info("VPC: ${network.referenceId} Created added for account: ${user.account}") 

            return  networkAclResponse
            
            
        } catch (CloudStackServerException ex) {

                ArrayList<ArrayList<String>> networkAclResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", GeneralConstants.RESULT_FAILURE);
                item.put("message", ex.localizedMessage.substring(4));
                networkAclResponse.add(item)

                return networkAclResponse    

        } catch (Exception ex) {
            ArrayList<ArrayList<String>> networkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            networkAclResponse.add(item)

            return networkAclResponse    
        }  
    }
    
    def deleteNetworkAcl(requestData) {
                
        try {
           
            def response = networkAclServer().deleteNetworkACLList(requestData.aclId);
                          
            ArrayList<ArrayList<String>> deleteNetworkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            deleteNetworkAclResponse.add(item)

            return  deleteNetworkAclResponse
            
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> deleteNetworkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            deleteNetworkAclResponse.add(item)

            return deleteNetworkAclResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> deleteNetworkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            deleteNetworkAclResponse.add(item)

            return deleteNetworkAclResponse    
        } 
    }
    
    def deleteNetworkAclRule(requestData) {
        try {
           
            def response = networkAclServer().deleteNetworkACL(requestData.ruleId);
                          
            ArrayList<ArrayList<String>> deleteNetworkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            deleteNetworkAclResponse.add(item)

            return  deleteNetworkAclResponse
            
        } catch (CloudStackServerException ex) {

            ArrayList<ArrayList<String>> deleteNetworkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            deleteNetworkAclResponse.add(item)

            return deleteNetworkAclResponse    

        } catch (Exception ex) {
            
            ArrayList<ArrayList<String>> deleteNetworkAclResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage);
            deleteNetworkAclResponse.add(item)

            return deleteNetworkAclResponse    
        } 
    }
    
    def getNetworkAclJob(jobId) {
        try {            
                        
            ArrayList<ArrayList<String>> jobResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = asyncJobServer().queryAsyncJobResult(jobId)
            if(jobResponse.jobStatus == "0") {
                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "1") {
                                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult.add(item);
                
                return jobResult
                
            } else if(jobResponse.jobStatus == "2") {                                
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);                
                jobResult.add(item);                
                return jobResult  
            }
                
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def editAclRule(requestData) {
        try {                
            HashMap<String,String> optional = new HashMap<String,String>();            
            optional.put("action", requestData.action); 
            optional.put("number", requestData.aclRuleNum); 
            optional.put("traffictype", requestData.traffictype); 
            optional.put("aclid", requestData.aclId);         
            if(requestData.protocol == "icmp") {
                if(requestData.icmpCode) {
                    optional.put("icmpcode", Integer.toString(requestData.icmpCode));
                }
                if(requestData.icmpType) { 
                    optional.put("icmptype", Integer.toString(requestData.icmpType));
                }
            } else if(requestData.protocol == "tcp" || requestData.protocol == "udp" || requestData.protocol == "protocolnumber") {
                if(requestData.endPort) {
                    optional.put("endport", Integer.toString(requestData.endPort));
                }
                if(requestData.startPort) {
                    optional.put("startport", Integer.toString(requestData.startPort));
                }
            } else {
                if(requestData.endPort) {
                    optional.put("endport", Integer.toString(requestData.endPort));
                }
                if(requestData.startPort) {
                    optional.put("startport", Integer.toString(requestData.startPort));
                }
            }
            if(requestData.cidr) {
                optional.put("cidrlist", requestData.cidr); 
            } 
            def aclRuleEditResponse = networkAclServer().updateNetworkACLItem(requestData.id, optional)
            
             
            ArrayList<ArrayList<String>> aclRulecEditArrayList = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", aclRuleEditResponse.jobId);
            aclRulecEditArrayList.add(item)
           
            return aclRulecEditArrayList     
        } catch (Exception ex) {
            Console.print("exceptio" + ex)
            ArrayList<ArrayList<String>> errorArrayList = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>()
            item.put("jobResult", GeneralConstants.RESULT_FAILURE);
            errorArrayList.add(item);
            return errorArrayList
        } 
    }
    
    def createNetworkAclRule(requestData) {
     
        try {
                
            HashMap<String,String> optional = new HashMap<String,String>();
            
            optional.put("action", requestData.action); 
            optional.put("number", requestData.aclRuleNum); 
            optional.put("traffictype", requestData.traffictype); 
            optional.put("aclid", requestData.aclId); 
            
            if(requestData.protocol == "icmp") {
                if(requestData.icmpCode) {
                    optional.put("icmpcode", Integer.toString(requestData.icmpCode));
                }
                if(requestData.icmpType) {
                    optional.put("icmptype", Integer.toString(requestData.icmpType));
                }
            } else if(requestData.protocol == "tcp" || requestData.protocol == "udp" || requestData.protocol == "protocolnumber") {
                if(requestData.endPort) {
                    optional.put("endport", Integer.toString(requestData.endPort));
                }
                if(requestData.startPort) {
                    optional.put("startport", Integer.toString(requestData.startPort));
                }
            } else {
                if(requestData.endPort) {
                    optional.put("endport", Integer.toString(requestData.endPort));
                }
                if(requestData.startPort) {
                    optional.put("startport", Integer.toString(requestData.startPort));
                }
            }
            if(requestData.cidr) {
                optional.put("cidrlist", requestData.cidr); 
            } 
            
            def response = networkAclServer().createNetworkACL(requestData.protocol, optional)

                ArrayList<ArrayList<String>> networkEgressResponse = new ArrayList<ArrayList<String>>();
                HashMap item = new HashMap();    
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", response.jobId);
                networkEgressResponse.add(item)

            return networkEgressResponse        
        } catch (CloudStackServerException ex) {
            
            ArrayList<ArrayList<String>> networkResponse = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();    
            item.put("result", GeneralConstants.RESULT_FAILURE);
            item.put("message", ex.localizedMessage.substring(4));
            networkResponse.add(item)

            return networkResponse    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
       
    def getNetworkAclRules(aclId, referenceId) {
              
        HashMap<String,String> optional = new HashMap<String,String>();
        if(aclId) {
            optional.put("aclId", aclId);
        }
        if(referenceId) {
            optional.put("id", referenceId);
        }
        optional.put("listall", "true");
        
        ArrayList<ArrayList<String>> networkAclRuleList = new ArrayList<ArrayList<String>>();
        
        def response = networkAclServer().listNetworkACLs(optional)
        
        for(Iterator <NetworkAclResponse> iter = response.networkAcls.iterator(); iter.hasNext();) { 
            def data = iter.next();
            HashMap item = new HashMap();    
            item.put("id", data.aclItemId);
            item.put("action", data.action);
            item.put("cidrList", data.cidrList);           
            item.put("ruleNo", data.number);           
            item.put("trafficType", data.trafficType); 
            Console.print("N Rule ID:" + data.aclItemId + "startport" + data.startPort + "end port" + data.endPort )
            if(data.protocol == "icmp") {
                item.put("startPort", data.icmpCode);
                item.put("endPort", data.icmpType);
                item.put("protocol", "ICMP");
            } else if(data.protocol == "tcp" || data.protocol == "udp") {
                item.put("endPort", data.endPort);
                item.put("startPort", data.startPort);
                if(data.protocol == "udp") {
                    item.put("protocol", "UDP");
                } else {
                    item.put("protocol", "TCP"); 
                }                
            } else if(data.protocol == "all") {
                item.put("endPort", "ALL");
                item.put("startPort", "ALL");
                item.put("protocol", "ALL");
            } else {
                item.put("endPort", data.endPort);
                item.put("startPort", data.startPort);
                item.put("protocol", data.protocol);                
            }
            
            networkAclRuleList.add(item); 
        }
        return networkAclRuleList;
    }
}
