package com.assistanz.fogpanel

import org.springframework.dao.DataIntegrityViolationException
import grails.plugin.springsecurity.annotation.Secured

class ConfigController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    LicenseValidationService licenseValidationService
    
//    def checkLicense() {
//        
//        render(view: "testLicense")
//    }
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def validateLicenseCheck() {
        
        licenseValidationService.validateOnline()
    }
   
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def registerLicenseCheck() {
        
        licenseValidationService.registerOnline()
    }
    
    def saveLicenseInfo() {
        
        println "params"
              
        licenseValidationService.getLicense()
        try {
            licenseValidationService.registerOnline(params.fogInstanceId, params.licenseeEmail)
            LicenseValidatorJob.schedule(21600000)
            redirect(controller:"login")
            
        } catch (LicenseExpiredException ex) {
//            throw ex;
            flash.message = "License Cannot be Registered Possible License Crack"
        } catch (Exception ex) {
//            throw ex;
            flash.message = "Invalid license:" + ex.getMessage()
        }
        render(view: "/login/licenseMissing", model: [message: flash.message])
    }
}
