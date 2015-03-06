package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import com.assistanz.fogpanel.Project;
import java.util.Arrays;
import java.util.List;
import com.assistanz.cloud.config.ConfigLoader
import org.openstack4j.api.OSClient;
import org.openstack4j.api.Builders;
import org.openstack4j.model.compute.IPProtocol;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.model.compute.SecGroupExtension;
import org.openstack4j.model.compute.SecGroupExtension.Rule;
import grails.plugin.springsecurity.SpringSecurityService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.SecurityGroup as OpenStackSecurityGroup;
import org.openstack4j.model.network.SecurityGroupRule as OpenStackSecurityGroupRule; 

@Transactional
class SecurityGroupService {
    
    def springSecurityService;
    
    OpenStackAuthService openStackAuthService

    def listSecurityGroup(referenceId) {

        def user = springSecurityService.currentUser;      
        
        ArrayList<ArrayList<String>> securityGroups = new ArrayList<ArrayList<String>>();
          
        //        def securityGroupList = SecurityGroup.findAllWhere(deleted: false, user: user)
        
        def securityGroupList = SecurityGroup.withCriteria {
            eq('deleted', false)
            eq('user', user)
            if(referenceId) {
                eq('referenceId', referenceId) 
            }
        }
        for (def securityGroup : securityGroupList) {
            
            HashMap item = new HashMap();
            item.put("referenceId", securityGroup.referenceId);
            item.put("name", securityGroup.name);
            item.put("description", securityGroup.description);
            securityGroups.add(item);
            
        }
        
        return securityGroups;
        
    }
    
    def createSecurityGroup(requestData) {
        
        try { 
        
            def user = springSecurityService.currentUser;
            
            ArrayList<ArrayList<String>> createSecurityGroupResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            
            def securityGroupName = SecurityGroup.findWhere(name: requestData.name, user: user, deleted: false);
            
            if(securityGroupName) {
                
                item.put("result",  "Exists");
                createSecurityGroupResponse.add(item);   
                 
                return createSecurityGroupResponse;
            } else {    
            
                OSClient os = openStackAuthService.authenticate();  
        
                OpenStackSecurityGroup group = os.networking().securitygroup().create(Builders.securityGroup().name(requestData.name).description(requestData.description).build());                     
        
                def projectId = group.getTenantId()
        
                SecurityGroup securityGroup = new SecurityGroup();
                securityGroup.referenceId = group.getId();
                securityGroup.name = requestData.name;
                securityGroup.description = requestData.description;
                securityGroup.account = Account.findWhere(uuid: projectId);
                securityGroup.user = user;
                securityGroup.createdAt = new Date();
                securityGroup.save(flush: true);
                if (!securityGroup.save()) {
                    securityGroup.errors.allErrors.each { println it }
                }
                
                //set default rule
                for (def rule : group.getRules()) {

                    def remoteGroupId;
                    def cidr;
                    def etherType;
                    
                    def currentSecurityGroup = SecurityGroup.findByReferenceId(rule.getSecurityGroupId());
                    
                    def securityGroupRule = new SecurityGroupRules();
                    securityGroupRule.referenceId = rule.getId();
                    securityGroupRule.securityGroup = currentSecurityGroup;
                    securityGroupRule.protocol = rule.getProtocol() == null ? "Any" : rule.getProtocol();
                    securityGroupRule.direction = rule.getDirection();
                    securityGroupRule.fromPort = rule.getPortRangeMin();
                    securityGroupRule.toPort = rule.getPortRangeMax();
                    println("rule.getRemoteIpPrefix()123:   "+rule.getRemoteIpPrefix())

                    cidr = "0.0.0.0/0" + " (CIDR)";
                    securityGroupRule.cidr = cidr;
                    etherType = rule.getEtherType();
                    println("etherType"+etherType)
                    securityGroupRule.etherType = etherType;
                    
                    if(rule.getRemoteGroupId()) {

                        println("rule.getRemoteGroupId()"+rule.getRemoteGroupId())
                        remoteGroupId = rule.getRemoteGroupId(); 

                        securityGroupRule.remoteGroupId = remoteGroupId;
                        
                    }
                    securityGroupRule.createdAt = new Date();
                    securityGroupRule.save(flush: true)
                }
        
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                createSecurityGroupResponse.add(item)
        
                return createSecurityGroupResponse
            }
        
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    def updateSecurityGroup(requestData) {
        try {

            def user = springSecurityService.currentUser; 
            
            ArrayList<ArrayList<String>> securityGroupResult = new ArrayList<ArrayList<String>>(); 
            HashMap<String,String> item = new HashMap<String,String>();
            
            OSClient os = openStackAuthService.authenticate(); 
                
            OpenStackSecurityGroup group = os.networking().securitygroup().update(requestData.referenceId, requestData.name, requestData.description);
                
            def projectId = group.getTenantId()
            def securityGroup = SecurityGroup.findWhere(referenceId: requestData.referenceId, deleted: false);
            securityGroup.referenceId = group.getId();
            securityGroup.name = requestData.name;
            securityGroup.description = requestData.description;
            securityGroup.account = Account.findWhere(uuid: projectId);
            securityGroup.user = user;
            securityGroup.createdAt = new Date();
            securityGroup.save(flush: true)
        
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            securityGroupResult.add(item);   

            return securityGroupResult;  
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def deleteSecurityGroup(id) {
        try {
            
            def user = springSecurityService.currentUser;
            
            OSClient os = openStackAuthService.authenticate();   
            
            ActionResponse actionResponse = os.networking().securitygroup().delete(id);
            
            if(actionResponse.isSuccess() == true) {
            
                def securityGroup = SecurityGroup.findByReferenceId(id)
                securityGroup.deleted = true;
                securityGroup.deletedAt = new Date();
                securityGroup.save(flush: true);
                
                //Delete all mapped rules 
                def securityGroupRules = SecurityGroupRules.withCriteria {
                    eq('deleted', false)
                    eq('securityGroup', securityGroup)
                } 
                
                for(def rule : securityGroupRules) {
                    
                    rule.deleted = true;
                    rule.deletedAt = new Date();
                    rule.save(flush: true)
                }

                ["OK"]
            } else {
                
                return actionResponse.getFault();
            }
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def getSecurityGroupRules(referenceId) {
        
        try {
            
            def user = springSecurityService.currentUser;
            OSClient os = openStackAuthService.authenticate();   
        
            OpenStackSecurityGroup group = os.networking().securitygroup().get(referenceId);
            
            def currentSecurityGroup = SecurityGroup.findByReferenceId(referenceId);
           
            ArrayList<ArrayList<String>> securityGroupRulesList = new ArrayList<ArrayList<String>>();
            
            def securityGroupRules = SecurityGroupRules.withCriteria {
                
               eq('deleted', false)
               eq('securityGroup', currentSecurityGroup) 

            }
          
            for (def rule : securityGroupRules) {

                def portRange;
                def fromPort = rule.fromPort;
                def toPort = rule.toPort;
                def cidr = rule.cidr;
                def remote;
                def direction;
                def protocol;
                
                //Rule direction
                if(rule.direction == "ingress") {
                    
                    direction = GeneralConstants.DIRECTION_INGRESS;
                } else {
                    direction = GeneralConstants.DIRECTION_EGRESS;
                }
                
                //Rule protocol
                if(rule.protocol == "tcp") {
                    protocol = GeneralConstants.PROTOCOL_TCP;
                } else if(rule.protocol == "udp") {
                    protocol = GeneralConstants.PROTOCOL_UDP;
                } else if(rule.protocol == "icmp") {
                    protocol = GeneralConstants.PROTOCOL_ICMP;
                } else {
                    protocol = rule.protocol;
                }
                
                //Rule range
                if(fromPort != toPort) {
                    portRange = fromPort + "-" + toPort;
                } else {
                    portRange = fromPort;
                }

                if(cidr) {
                    remote = cidr;
                } 
                
                if(rule.remoteGroupId) {
                    
                    def securityGroup = SecurityGroup.findByReferenceId(rule.remoteGroupId);
                    remote = securityGroup.name; 
                }

                HashMap item = new HashMap();
                item.put("referenceId", rule.referenceId);
                item.put("direction", direction);
                item.put("etherType", rule.etherType);
                item.put("ipProtocol", protocol);
                item.put("portRange", portRange);
                item.put("remote", remote);
                securityGroupRulesList.add(item);

                
            }  
            return securityGroupRulesList;
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def createSecurityGroupRule(requestData) {
        
        try { 
            
            def protocol;
            def fromPort;
            def toPort;
            def cidr;

            def port = requestData.port;
            def fromPortValue = requestData.fromPort;
            def toPortValue = requestData.toPort;
            def direction = requestData.direction;
            def icmpCode = requestData.icmpCode;
            def icmpType = requestData.icmpType;

            if(requestData.rule == "tcp") {
                protocol = "tcp";
                
                if(!port.equals(null)) {

                    if(requestData.port == 0){
                        fromPort = 0;
                        toPort = 0;

                    } else if(requestData.port == -1){
                        fromPort = -1;
                        toPort = -1;
                    } else {
                        fromPort = requestData.port;
                        toPort = requestData.port;
                    }
                } else{
                    
                    if((! fromPortValue.equals(null)) && (! toPortValue.equals(null)) ) {
                        fromPort = requestData.fromPort;
                        toPort = requestData.toPort;
                    }
                }
                
            } else if(requestData.rule == "udp") {
                protocol = "udp";
                                         
                if(!port.equals(null)) {

                    if(requestData.port == 0){
                        fromPort = 0;
                        toPort = 0;

                    } else if(requestData.port == -1){
                        fromPort = -1;
                        toPort = -1;
                    } else {
                        fromPort = requestData.port;
                        toPort = requestData.port;
                    }
                } else{
                    
                    if((! fromPortValue.equals(null)) && (! toPortValue.equals(null)) ) {
                        fromPort = requestData.fromPort;
                        toPort = requestData.toPort;
                    }
                }
                
            } else if(requestData.rule == "icmp") {
                protocol = "icmp";
             
                if((! icmpCode.equals(null)) && (! icmpType.equals(null)) ) {
                    fromPort = icmpType;
                    toPort = icmpCode;
                }
                 
                
            } else if(requestData.rule == "custom") {
                def ipProtocol = requestData.ipProtocol;
                fromPort = 0;
                toPort = 0;
                protocol = ipProtocol.toString();
                
            } else if(requestData.rule == "all_tcp") {
                protocol = "tcp";
                fromPort = 1;
                toPort = 65535;
                
            } else if(requestData.rule == "all_udp") {
                protocol = "udp";
                fromPort = 1;
                toPort = 65535;
                
            } else if(requestData.rule == "all_icmp") {
                protocol = "icmp";
                fromPort = 0;
                toPort = 0;
                
            } else if(requestData.rule == "dns") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 53;
                toPort = 53;
                
            } else if(requestData.rule == "http") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 80;
                toPort = 80;
                
            } else if(requestData.rule == "https") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 443;
                toPort = 443;
                
            } else if(requestData.rule == "imap") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 143;
                toPort = 143;
                
            } else if(requestData.rule == "imaps") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 993;
                toPort = 993;
                
            } else if(requestData.rule == "ldap") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 389;
                toPort = 389;
                
            } else if(requestData.rule == "ms_sql") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 1443;
                toPort = 1443;
                
            } else if(requestData.rule == "mysql") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 3306;
                toPort = 3306;
                
            } else if(requestData.rule == "pop3") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 110;
                toPort = 110;
                
            } else if(requestData.rule == "pop3s") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 995;
                toPort = 995;
                
            } else if(requestData.rule == "rdp") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 3389;
                toPort = 3389;
                
            } else if(requestData.rule == "smtp") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 25;
                toPort = 25;
                
            } else if(requestData.rule == "smtps") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 465;
                toPort = 465;
                
            } else if(requestData.rule == "ssh") {
                direction = "ingress";
                protocol = "tcp";
                fromPort = 22;
                toPort = 22;
                
            }
                                  
            if(requestData.cidr == ""){
                cidr = "0.0.0.0/0";
            } else {
                cidr = requestData.cidr;
            }
                
            def user = springSecurityService.currentUser;
            
            OSClient os = openStackAuthService.authenticate(); 
        
//            SecGroupExtension group = os.compute().securityGroups().get(requestData.securityGroupId);
            
            println("sec id"+ requestData.securityGroupId)
            println("sec direction"+ direction)
            println("sec protocol"+ protocol)
            println("sec fromPort"+ fromPort)
            println("sec toPort"+ toPort)
            println("sec cidr"+ cidr)
            
            
            OpenStackSecurityGroupRule rule ;
            
            def currentSecurityGroup = SecurityGroup.findByReferenceId(requestData.securityGroupId);
            
            if(requestData.remote == "sg") {
                
                def securityGroup = requestData.securityGroup;
                
                rule = os.networking().securityrule().create(Builders.securityGroupRule().securityGroupId(requestData.securityGroupId).protocol(protocol)
                    .direction(direction)
                    .remoteGroupId(securityGroup)
                    .portRangeMin(fromPort)
                    .ethertype(requestData.ipVersion)
                    .portRangeMax(toPort)
                    .build());
                                
                def securityGroupRule = new SecurityGroupRules();
                securityGroupRule.referenceId = rule.getId();
                securityGroupRule.securityGroup = currentSecurityGroup;
                securityGroupRule.protocol = protocol;
                securityGroupRule.direction = direction;
                securityGroupRule.fromPort = fromPort;
                securityGroupRule.toPort = toPort;
                securityGroupRule.remoteGroupId = securityGroup;
                securityGroupRule.etherType = requestData.ipVersion;
                securityGroupRule.createdAt = new Date();
                securityGroupRule.save(flush:true)
                
            } else {
                
                 rule = os.networking().securityrule().create(Builders.securityGroupRule()
                .securityGroupId(requestData.securityGroupId)
                .direction(direction)
                .protocol(protocol)
                .portRangeMin(fromPort)
                .portRangeMax(toPort)
                .remoteIpPrefix(cidr)
                .build());  
            
                def securityGroupRule = new SecurityGroupRules();
                securityGroupRule.referenceId = rule.getId();
                securityGroupRule.securityGroup = currentSecurityGroup;
                securityGroupRule.protocol = protocol;
                securityGroupRule.direction = direction;
                securityGroupRule.fromPort = fromPort;
                securityGroupRule.toPort = toPort;
                securityGroupRule.etherType = rule.getEtherType();
                securityGroupRule.cidr = cidr + " (CIDR)";
                securityGroupRule.createdAt = new Date();
                securityGroupRule.save(flush:true)
                
            }
                                                        
            ArrayList<ArrayList<String>> securityGroupRuleResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            securityGroupRuleResponse.add(item)
        
            return securityGroupRuleResponse
        
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    def deleteSecurityGroupRule(referenceId) {
        
        try {
            
            def user = springSecurityService.currentUser;
            
            OSClient os = openStackAuthService.authenticate(); 
            
            ActionResponse actionResponse = os.compute().securityGroups().deleteRule(referenceId);
            
            if(actionResponse.isSuccess() == true) {
                
                
                def securityGroupRule = SecurityGroupRules.findByReferenceId(referenceId)
                securityGroupRule.deleted = true;
                securityGroupRule.deletedAt = new Date();
                securityGroupRule.save(flush: true);
            
                ["OK"]
            } else {
                actionResponse.getFault();
            }
            
        } catch (Exception ex) {
            throw ex;
        }
        
    }
}
