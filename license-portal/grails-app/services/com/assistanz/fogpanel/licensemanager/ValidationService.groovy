package com.assistanz.fogpanel.licensemanager

import grails.transaction.Transactional
import com.assistanz.licensor.Licensor;
import com.assistanz.fogpanel.licensemanager.*;
import com.assistanz.licensor.License;
import com.assistanz.licensor.LicenseManager;
import com.assistanz.licensor.LicenseException;
import com.assistanz.licensor.Licensor;
import org.apache.commons.logging.LogFactory;
import java.util.Date;

@Transactional
class ValidationService {
    
    private static final log = LogFactory.getLog(this)
    
    def register(license) {
        
        Long instanseId =  Long.parseLong(license.requestID)  
        ProductInstance productInstance = ProductInstance.get(instanseId)
        license = validateLicense(license, productInstance)
                        
        if(!license.valid) {
            saveLog(license)
            return license
        }
        
        println "icense.initialVersion" + license.initialVersion
        
        //splitting release version and qualifier
        def (releaseVersion , qualifier) = license.initialVersion.tokenize("-")
        
        println "releaseVersion" + releaseVersion
        
        def (versionMajor, versionMinor, versionPatch) = releaseVersion.tokenize(".")
        
        if(!productInstance.initialVersion) {
            productInstance.initialVersion = new Version()
        }
        productInstance.initialVersion.major = Byte.parseByte(versionMajor)
        productInstance.initialVersion.minor = Byte.parseByte(versionMinor)
        productInstance.initialVersion.patch = Byte.parseByte(versionPatch)
        
        productInstance.save(flush: true)
                        
        license.valid = true
        saveLog(license)
        return license
    }
    
    def saveLog(license) {
        
        ValidationLog validationLog = new ValidationLog()
        
        validationLog.productInstance = ProductInstance.get(license.requestID.toLong())
        validationLog.valid = license.valid
        validationLog.requestID = license.requestID
        validationLog.email = license.email
        validationLog.checksum = license.checksum
        validationLog.hostName = license.hostName
        validationLog.hostIP = license.hostIP
        validationLog.timeZone = license.timeZone
        validationLog.initialVersion = license.initialVersion
        validationLog.currentVersion = license.currentVersion
        validationLog.sockets = license.sockets
        validationLog.additionalData = "";
         if(!license.valid && license.lastUpdatedDate != null) {
            validationLog.lastUpdatedDate = license.lastUpdatedDate.toLong()
        } else {
            validationLog.lastUpdatedDate = new Date().getTime();  
        }      
        if(license.deployedDate == null) {
            validationLog.deployedDate = license.deployedDate;
        } else {
            validationLog.deployedDate = Long.parseLong(license.deployedDate);
        }
        if(!license.registrationDate && license.valid == true) {
            validationLog.registrationDate = new Date().getTime()
        } else if(license.registrationDate) {
            validationLog.registrationDate = license.registrationDate.toLong();  
        }
        validationLog.errorMessage = license.errorMessage
        validationLog.createdDate = new Date()
        validationLog.save(flush: true);
    }
    
    def validate(license) {
        Long instanseId =  Long.parseLong(license.requestID)  
        ProductInstance productInstance = ProductInstance.get(instanseId)
        license = validateLicense(license, productInstance)
        
        if(!license.valid) {
            saveLog(license)
            return license
        }
        
        license.valid = true
        saveLog(license)
        return license
    }
    
    
    private validateLicense(license, productInstance) {
                        
        license.status = ProductInstance.Status.VALID;
        license.errorMessage = ""
        
        if(license.requestID.length() != 12) {
            license.valid = false
            license.errorMessage = "Invalid Instance ID"
            return license
        }
        
        if(!productInstance) {
            license.valid = false
            license.errorMessage = "No product found"
            return license
        }
        
        if(!productInstance.user.username.equals(license.email))  {
            license.valid = false
            license.errorMessage = "Invalid License for the provided email"
            return license
        }
        
        
        String initialVersion = productInstance.initialVersion == null ? license.initialVersion : productInstance.initialVersion.toString()
        Long totalLicensesAllowed = productInstance.baseLicense + productInstance.additionalLicense       
        Licensor fogLicensor = LicenseManager.getNewLicensor(initialVersion, 
                license.requestID, 
                license.email, new Properties())
        
        Date extendedDate = new Date()
        extendedDate.setTime(productInstance.expirationDate.getTime() + 15 * 1000 * 60 * 60 * 24);
        
//        Console.print("productInstance.expirationDate.getTime()" + productInstance.expirationDate.getTime())
//        Console.print("extendedDate.getTime()" + extendedDate.getTime())
//        Console.print("today" + new Date().getTime())
        
        if(productInstance.expirationDate.getTime() > extendedDate.getTime()) {
            
            log.info ' inside productInstance expirationDate > today + 15 days' 
                        
            License productLicense = getProductLicense(license)
            license.valid = false
            license.status = ProductInstance.Status.valueOfName(ProductInstance.Status.EXPIRED);
            license.errorMessage = "License has expired, Please Renew"
            license.lastUpdatedDate = productLicense.lastUpdatedDate;       
            if(!productInstance.registeredDate) {
                productInstance.registeredDate = new Date();
                license.registrationDate = new Date().getTime();
            } else {
                license.registrationDate = productInstance.registeredDate.getTime(); 
            }
            license.checksum =
                fogLicensor.buildPayload(productLicense, totalLicensesAllowed, 
                    new Long(license.sockets), license.status.name(), productInstance.expirationDate.getTime(), 
                    0L, productInstance.product.code)
                
            productInstance.lastUpdatedOn = productLicense.lastUpdatedDate;
            productInstance.appStatus = "Expired";
            productInstance.activeLicense = license.sockets
            productInstance.save(flush: true)
            
            return license
        }
        
        if(license.sockets > totalLicensesAllowed) {
                   
            log.info 'inside license sockets > total sockets allowed' 
            
            License productLicense = getProductLicense(license)
            license.valid = true
            license.status = ProductInstance.Status.valueOfName(ProductInstance.Status.EMERGENCY_TRIAL);
            license.errorMessage = "Sockets Overused, Activating Emergency Trial"
            license.lastUpdatedDate = productLicense.lastUpdatedDate;
            if(!productInstance.registeredDate) {
                productInstance.registeredDate = new Date();
                license.registrationDate = new Date().getTime();
            } else {
                license.registrationDate = productInstance.registeredDate.getTime(); 
            }
            if(productInstance.emergencyExpiryDate == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(productInstance.expirationDate)
                calendar.add(Calendar.DATE, 15);
                productInstance.emergencyExpiryDate = calendar.getTime()
            }
            productInstance.lastUpdatedOn = productLicense.lastUpdatedDate
            license.checksum = fogLicensor.buildPayload(productLicense, totalLicensesAllowed, new Long(license.sockets), license.status.name(), productInstance.emergencyExpiryDate.getTime(), 0L, productInstance.product.code)
            productInstance.appStatus = "Emergency Trial";
            productInstance.activeLicense = license.sockets
            productInstance.save(flush: true)
            return license
        }
        
        if(productInstance.emergencyExpiryDate != null && productInstance.emergencyExpiryDate < new Date()) {
                       
            log.info 'inside productInstance emergencyExpiryDate date < today' 
            
            License productLicense = getProductLicense(license)
            license.valid = false
            license.status = ProductInstance.Status.valueOfName(ProductInstance.Status.EXPIRED);
            license.errorMessage = "License has expired, Please Renew"
            license.lastUpdatedDate = productLicense.lastUpdatedDate;
            if(!productInstance.registeredDate) {
                productInstance.registeredDate = new Date();
                license.registrationDate = new Date().getTime();
            } else {
                license.registrationDate = productInstance.registeredDate.getTime(); 
            }
            productInstance.lastUpdatedOn = productLicense.lastUpdatedDate
            license.checksum = fogLicensor.buildPayload(productLicense, totalLicensesAllowed, new Long(license.sockets), license.status.name(), productInstance.expirationDate.getTime(), 0L, productInstance.product.code)
            productInstance.appStatus = "Expired";
            productInstance.activeLicense = license.sockets
            productInstance.save(flush: true)
            
            return license
        }
        
        if(productInstance.lastUpdatedOn != null && productInstance.lastUpdatedOn != license.lastUpdatedDate?.toLong()) {
            
           log.info 'productInstance lastUpdatedOn date != license lastUpdatedDate'  
             
            License productLicense = getProductLicense(license)
            license.valid = true
            license.status = ProductInstance.Status.valueOfName(ProductInstance.Status.EMERGENCY_TRIAL);
            license.errorMessage = "Invalid last updated time, Activating Emergency Trial"
            license.lastUpdatedDate = productLicense.lastUpdatedDate;
            if(!productInstance.registeredDate) {
                productInstance.registeredDate = new Date();
                license.registrationDate = new Date().getTime();
            } else {
                license.registrationDate = productInstance.registeredDate.getTime(); 
            }
            if(productInstance.emergencyExpiryDate == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(productInstance.expirationDate)
                calendar.add(Calendar.DATE, 15);
                productInstance.emergencyExpiryDate = calendar.getTime()
            }
            productInstance.lastUpdatedOn = productLicense.lastUpdatedDate;
            license.checksum = fogLicensor.buildPayload(productLicense, totalLicensesAllowed, new Long(license.sockets), license.status.name(), productInstance.emergencyExpiryDate.getTime(), 0L, productInstance.product.code)
            productInstance.appStatus = "Emergency Trial";  
            productInstance.activeLicense = license.sockets
            productInstance.save(flush: true)
            return license
        }
        
        if(productInstance.expirationDate.getTime() < new Date().getTime()) {
           
            log.info 'inside expirationDate > today'  
            
            License productLicense = getProductLicense(license)
            license.valid = true
            license.status = ProductInstance.Status.valueOfName(ProductInstance.Status.EMERGENCY_TRIAL);
            license.errorMessage = "License Expired, Activating Emergency Trial"
            license.lastUpdatedDate = productLicense.lastUpdatedDate;
            if(!productInstance.registeredDate) {
                productInstance.registeredDate = new Date();
                license.registrationDate = new Date().getTime();
            } else {
                license.registrationDate = productInstance.registeredDate.getTime(); 
            }
            productInstance.lastUpdatedOn = productLicense.lastUpdatedDate
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(productInstance.expirationDate)
            calendar.add(Calendar.DATE, 15);
            Date date = calendar.getTime()
            productInstance.emergencyExpiryDate = date
            license.checksum = fogLicensor.buildPayload(productLicense, totalLicensesAllowed, new Long(license.sockets), license.status.name(), productInstance.emergencyExpiryDate.getTime(), 0L, productInstance.product.code)
            productInstance.appStatus = "Emergency Trial"; 
            productInstance.activeLicense = license.sockets
            productInstance.save(flush: true)
            return license
        }
        
        log.info 'All Condition False'  
        
        License productLicense = getProductLicense(license)
        license.checksum = fogLicensor.buildPayload(productLicense, totalLicensesAllowed, new Long(license.sockets), license.status.name(), productInstance.expirationDate.getTime(), 0L, productInstance.product.code)
        license.status = productInstance.status.name();
        license.valid = true
        license.lastUpdatedDate = productLicense.lastUpdatedDate;
        productInstance.lastUpdatedOn = productLicense.lastUpdatedDate;
        if(!productInstance.registeredDate) {
            productInstance.registeredDate = new Date();
            license.registrationDate = new Date().getTime();
        } else {
            license.registrationDate = productInstance.registeredDate.getTime(); 
        }
        productInstance.appStatus = "Active";   
        productInstance.activeLicense = license.sockets
        productInstance.save(flush: true)
        
        return license
        
    }
    
    String generateChecksum(productInstance) {
        Long totalLicensesAllowed = productInstance.baseLicense + productInstance.additionalLicense 
        
        
        License productLicense = new License()
        productLicense.requestID = String.format("%012d", productInstance.id)
        productLicense.lastUpdatedDate = productInstance.lastUpdatedOn 
                
        Licensor fogLicensor = LicenseManager.getNewLicensor(productInstance.initialVersion.toString(), 
                String.format("%012d", productInstance.id),
                productInstance.user.username, new Properties())
        
        String checksum = fogLicensor.buildPayload(productLicense, totalLicensesAllowed, new Long(productInstance.activeLicense), "VALID", productInstance.expirationDate.getTime(), 0L, productInstance.product.code)
        
        return checksum
    }
    
    private License getProductLicense(license) {
        
        License productLicense = new License()
        
        productLicense.valid = license.valid
        productLicense.requestID = license.requestID
        productLicense.email = license.email
        productLicense.checksum = license.checksum
        productLicense.hostName = license.hostName
        productLicense.hostIP = license.hostIP
        productLicense.timeZone = license.timeZone
        productLicense.initialVersion = license.initialVersion
        productLicense.currentVersion = license.currentVersion
        productLicense.sockets = license.sockets
        productLicense.additionalData = "";
        if(!license.valid && license.lastUpdatedDate != null) {
            productLicense.lastUpdatedDate = license.lastUpdatedDate.toLong()
        } else {
            productLicense.lastUpdatedDate = new Date().getTime();  
        }      
        if(license.deployedDate == null) {
            productLicense.deployedDate = license.deployedDate;
        } else {
            productLicense.deployedDate = Long.parseLong(license.deployedDate);
        }
        if(!license.registrationDate && license.valid == true) {
          productLicense.registrationDate = new Date().getTime();  
        }
        return productLicense
    }
}
