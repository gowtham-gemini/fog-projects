package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.cloud.config.ConfigLoader
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Keypair;
import com.assistanz.openstack.OpenStackServer;
import org.openstack4j.model.compute.ActionResponse;
import org.springframework.context.MessageSource
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import com.assistanz.fogpanel.GenerateKeyPair;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.misc.BASE64Encoder;

@Transactional
class KeypairService {
    
    def springSecurityService
    MessageSource messageSource
    OpenStackAuthService openStackAuthService

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
    
    def sshKeyListEmpty() {
        try{
            
            def sshkeyList;
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()  
            
            sshkeyList = SSHKeys.withCriteria {
                    eq('account', user.account)
                    eq('user', user)
            }
            
            OSClient os = openStackAuthService.authenticate();
            
            if(sshkeyList.isEmpty()) {
                
                List<? extends Keypair> keyPairs = os.compute().keypairs().list();

                for(def keypair: keyPairs) {
                    def sshKeys = new SSHKeys();
                    sshKeys.name = keypair.getName();
                    sshKeys.isDefault = false;
                    sshKeys.publicKey = keypair.getPublicKey();
                    sshKeys.privateKey = null;
                    sshKeys.fingerprint = keypair.getFingerprint();
                    sshKeys.account = user.account;
                    sshKeys.user = user;
                    sshKeys.createdAt = new Date();
                    sshKeys.save(flush:true);
                    
                }
                
            }
            ArrayList<ArrayList<String>> keyListResponse = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            keyListResponse.add(item);
            
            return keyListResponse;
            
        } catch(Exception ex) {
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
            
            OSClient os = openStackAuthService.authenticate();           
                       
            ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 

            GenerateKeyPair generateKeyPair = new GenerateKeyPair();
            def generatedPrivateKey = generateKeyPair.getPrivateKey();
            def generatedPublicKey = generateKeyPair.getPublicKey();
            
//            Console.print("publicKeyString--: " + publicKey)
//            Console.print("publicKeyString" + generateKeyPair.getPublicKey())
//            Console.print("privateKeyString" + generateKeyPair.getPrivateKey())
            
            
            Keypair kp = os.compute().keypairs().create(requestData.name, generatedPublicKey);
            
            def sshKeys = new SSHKeys();
            sshKeys.name = requestData.name;
            sshKeys.isDefault = false;
            sshKeys.publicKey = generatedPublicKey;
            sshKeys.privateKey = generatedPrivateKey;
            sshKeys.fingerprint = kp.getFingerprint();
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
    def resetSSHKeyForVirtualMachine(requestBody) {     
        
    }        
    
    
    def deleteSSHKey(requestData) {   
          
        def user = springSecurityService.currentUser 
//        def vm = VirtualMachine.findAllBySshKey(sshKey)                
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();            
        HashMap<String,String> item = new HashMap<String,String>();                      
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
           
            OSClient os = openStackAuthService.authenticate(); 
            
            def sshKey = SSHKeys.findWhere(name : requestData.sshKeyName, account : user.account);

            
            def virtualMachine = VirtualMachine.findWhere(user: user, keypair: sshKey, deleted: false)
            
            if(virtualMachine) {
                
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                Object[] vmArgs = new Object[2];
                vmArgs[0] = virtualMachine.name
                item.put("message", messageSource.getMessage('user.vm.cannotDeleteSSHKey', vmArgs, new Locale(defaultLanguage.value)));
                
                item.put("result", GeneralConstants.ALREADY_MAPPED);
                resultList.add(item);
            } else {
                 
                
                ActionResponse actionResponse = os.compute().keypairs().delete(requestData.sshKeyName);

                if(actionResponse.isSuccess() == true) {

                
                sshKey.delete(flush:true)
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                resultList.add(item);
                } else {
                    return actionResponse.getFault();
                }
            
            
            }

            return resultList;
//        }        
    }
    
    // true or false based on key exists
    def isPrivateKeyExistsInDB() {
        
        
        def user = springSecurityService.currentUser 
        
        
        def sshkeyList = SSHKeys.withCriteria {
            eq('account', user.account)
            isNotNull('privateKey')
        }
        
        if(sshkeyList != null && !sshkeyList.empty && sshkeyList.size()>0) {
            return true
        } else {
            return false;
        }
    }
    
}
