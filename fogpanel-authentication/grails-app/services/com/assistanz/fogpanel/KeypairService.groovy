package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.cloud.config.ConfigLoader
import com.assistanz.openstack.OpenStackServer;
import org.springframework.context.MessageSource
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import com.assistanz.fogpanel.GenerateKeyPair;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.misc.BASE64Encoder;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.ImportKeyPairRequest;
import com.amazonaws.services.ec2.model.ImportKeyPairResult;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.AmazonEC2;

@Transactional
class KeypairService {
    
    def springSecurityService
    MessageSource messageSource
    AwsAuthService awsAuthService;

    def listSSHKey() {
        
        try { 
            def sshkeyList;
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  
            
            sshkeyList = SSHKeys.withCriteria {
                    eq('account', user.account)
                    eq('user', user)
            }
            
            ArrayList<ArrayList<String>> keyList = new ArrayList<ArrayList<String>>();     
            for(def key: sshkeyList) {
                HashMap<String,String> keyData = new HashMap<String,String>();
                keyData.put("id", key.id);
                keyData.put("fingerprint", key.fingerprint);
                keyData.put("name", key.name);   
                keyData.put("publickey", key.publicKey); 
                keyData.put("privatekey", key.privateKey); 
                keyList.add(keyData);                 
                
            }
            return keyList
            
        } catch(Exception ex) {
             ex.printStackTrace(System.err);
            throw ex;
        }
    }    
    
    def deletePrivateKey(id) {
        try{
                        
            long sshKeyId = Long.parseLong(id)
            def sshKeys = SSHKeys.findWhere(id : sshKeyId);
            sshKeys.privateKey = null;
            sshKeys.save(flush:true);
                        
        } catch(Exception ex) {
            throw ex;
        }
        
    }
    
    
    def createSSHKey(requestData) {
        
        try {
            
            def user = springSecurityService.currentUser;
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();           
                       
            ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 

            GenerateKeyPair generateKeyPair = new GenerateKeyPair();
            def generatedPrivateKey = generateKeyPair.getPrivateKey();
            def generatedPublicKey = generateKeyPair.getPublicKey();                        
            
            ImportKeyPairRequest importKeyPairRequest = new ImportKeyPairRequest();
            importKeyPairRequest.withPublicKeyMaterial(generatedPublicKey);
            importKeyPairRequest.withKeyName(requestData.name);
            
            ImportKeyPairResult importKeyPairResult = ec2.importKeyPair(importKeyPairRequest);            
            
            def sshKeys = new SSHKeys();
            sshKeys.name = requestData.name;
            sshKeys.isDefault = false;
            sshKeys.publicKey = generatedPublicKey;
            sshKeys.privateKey = generatedPrivateKey;
            sshKeys.fingerprint = importKeyPairResult.getKeyFingerprint();
            sshKeys.account = user.account;
            sshKeys.user = user;
            sshKeys.createdAt = new Date();
            sshKeys.save(flush:true);

            log.info("SSH key created for user:${user.id}")
            item.put("publickey", generateKeyPair.getPublicKey());
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            resultList.add(item);
            return resultList;
                                                                          
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }              
        
    def deleteSSHKey(requestData) {   
          
        def user = springSecurityService.currentUser               
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();                      
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }           
            
            def sshKey = SSHKeys.findWhere(name : requestData.sshKeyName, account : user.account);
            
//            def virtualMachine = VirtualMachine.findWhere(user: user, keypair: sshKey, deleted: false)
//            
//            if(virtualMachine) {
//                
//                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//                Object[] vmArgs = new Object[2];
//                vmArgs[0] = virtualMachine.name
//                item.put("message", messageSource.getMessage('user.vm.cannotDeleteSSHKey', vmArgs, new Locale(defaultLanguage.value)));
//                
//                item.put("result", GeneralConstants.ALREADY_MAPPED);
//                resultList.add(item);
//            } else {      
                    //delete
//            }

        try {
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();

            DeleteKeyPairRequest deleteKeyPairRequest = new DeleteKeyPairRequest();
            deleteKeyPairRequest.withKeyName(requestData.sshKeyName);        
            ec2.deleteKeyPair(deleteKeyPairRequest);

            sshKey.delete(flush:true)
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            resultList.add(item);
                
        } catch(Exception e) {
            ex.printStackTrace(System.err);
            throw ex;
        }

        return resultList;      
    }
}
