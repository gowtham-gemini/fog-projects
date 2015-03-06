package com.assistanz.fogpanel.licensemanager

import grails.transaction.Transactional
import java.security.MessageDigest
import java.sql.Timestamp
//import com.assistanz.util.EncryptionUtil



@Transactional
class UserService {

    def mailService 
    
    def register(User user) {
        
        Calendar calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR, 2);
        Date date = new Date();
        def time = new Timestamp(date.getTime())
        def token = user.username + date.toString()

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(token.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        
//        def hashText = EncryptionUtil.digest(EncryptionUtil.Algorithim.MD5, token)
        
        def url = "https://portal.fogpanel.com/user/confirmAccount/" + hashtext.toString();
        
        user.enabled = false
        user.tokenExpiryDate = calendar.getTime()
        user.accountExpired = false
        user.accountLocked = false
        user.passwordExpired = false
        user.token = hashtext.toString()
        user.save(flush:true)
            
        def role = Role.findByAuthority("ROLE_USER")
        UserRole.create(user, role, true)
         
        try {    
            mailService.sendMail {
                to user.username
                subject "[FogPanel License Portal] Account Verification"
                html "Dear ${user.firstName} ${user.lastName}, <br> <p>Please verify your email and activate your account by clicking the following link<br><a href=\"${url}\">${url}</a><br></p><p>Regards<br>FogPanel Team</p><br><p>Note: This is a system generated email.</p>"
            }                   
        } catch (Exception ex) {
           Console.print(ex);
        } 
    }
    
    //Add security check should be same user or should be admin
    def updateBasicInfo(User user, properties) {
        //Validate the properties don't allow all properties to be updated
        if (!user.isDirty()) {
            user.properties = properties
            user.save(flush:true)
        }
    }
    
    //Add security check should be same user or should be admin
    def updatePassword(User user, password) {
        //Validate the properties don't allow all properties to be updated
        if (!user.isDirty()) {
            user.password = password
            user.save(flush:true)
        }
    }
    
    def sendForgetPasswordLink(user) {
        
        Calendar calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR, 2);
        Date date = new Date();
        def time = new Timestamp(date.getTime())
        def token = user.username + date.toString()
        
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(token.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        
//        def hashText = EncryptionUtil.digest(EncryptionUtil.Algorithim.MD5, token)
        
        def url = "https://portal.fogpanel.com/user/resetForgetPassword/" + hashtext.toString();
        
        user.token = hashtext.toString()
        user.save(flush:true)
        
        try {    
            mailService.sendMail {
                to user.username
                subject "[FogPanel License Portal] Recover Your Password"
                html "Dear ${user.username}, <br><br> You have requested to recover you loast password.<br> Kindly click the following link to recover your password for FogPanel License Portal.<br><a href=\"${url}\">${url}</a><br>Regards,<br>FogPanel Team.<br><br>Note: This is a system generated email."
            }
        } catch (Exception ex) {
           Console.print(ex);
        } 
    }
}
