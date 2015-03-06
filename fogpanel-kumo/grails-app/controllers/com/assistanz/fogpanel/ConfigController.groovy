package com.assistanz.fogpanel

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.usages.CSAccountUsageService
import org.codehaus.groovy.grails.commons.ApplicationHolder

class ConfigController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    LicenseValidationService licenseValidationService
    FailedUsageService failedUsageService
    
    @Secured(['ROLE_ADMIN'])
    def cloudStackUsageServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey); 
                
        CSAccountUsageService csUsageService = new CSAccountUsageService(server); 
        
        return csUsageService;
    }
    
    
    def checkLicense() {
        
        render(view: "testLicense")
    }
    
    @Secured(['ROLE_ADMIN'])
    def generateFaildUsageConfig() {
        
        render(view: "generateFaildUsageConfig")
    }
    
    @Secured(['ROLE_ADMIN'])
    def generateFaildUsage() {
             
        failedUsageService.startFailedUsageUpdateJob()
        
        flash.message = "Job Started Successfully"
        redirect(action: "importFailedUsageStatus")
               
    }
    
    def importFailedUsageStatus() {
                
        def asyncJobs = AsynchronousJobs.createCriteria()
        
        def job = asyncJobs.list {
            eq("status", JobStatus.valueOf("RUNNING"))
            eq("jobType", JobType.valueOf("PULL_FAILED_USAGE"))
        }
        
        def jobList =  AsynchronousJobs.withCriteria { 
            maxResults(10)
            order("id", "desc")
        }
        
        if(job) { 
             flash.message = "Job Running"
            [jobStatus: "Running", jobList:jobList]         
        } else if(!job) {
                flash.message = "All Job Completed Successfully"
            [jobStatus: "Completed", jobList:jobList]         
        } 
    }
    
    def generateUsageRecord() {
        
         try {
        
            def response = cloudStackUsageServer().generateUsageRecords(params.endDate, params.startDate, null);
                
            flash.message = "Generate Usage Records in cloud stack started"
        } catch (Exception ex) {
//            throw ex;
            flash.message = "Generate Usage Records failed"
        }
    }
    
   
    
//    def validateLicenseCheck() {
//        
//        licenseValidationService.validateOnline()
//    }
//    
//    def registerLicenseCheck() {
//        
//        licenseValidationService.registerOnline()
//    }
    
    def saveLicenseInfo() {
              
//        licenseValidationService.getLicense()
        try {
            licenseValidationService.registerOnline(params.fogInstanceId, params.licenseeEmail)
//            LicenseValidatorJob.schedule(21600000)
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
    
    @Secured(['ROLE_ADMIN'])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [configInstanceList: Config.list(params), configInstanceTotal: Config.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        [configInstance: new Config(params)]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def configInstance = new Config(params)
        if (!configInstance.save(flush: true)) {
            render(view: "create", model: [configInstance: configInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'config.label', default: 'Config'), configInstance.id])
        redirect(action: "show", id: configInstance.id)
    }

    @Secured(['ROLE_ADMIN'])
    def show(Long id) {
        def configInstance = Config.get(id)
        if (!configInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'config.label', default: 'Config'), id])
            redirect(action: "list")
            return
        }

        [configInstance: configInstance]
    }
    
    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        def configInstance = Config.get(id)
        if (!configInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'config.label', default: 'Config'), id])
            redirect(action: "list")
            return
        }

        [configInstance: configInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def update(Long id, Long version) {
        def configInstance = Config.get(id)
        if (!configInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'config.label', default: 'Config'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (configInstance.version > version) {
                configInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'config.label', default: 'Config')] as Object[],
                          "Another user has updated this Config while you were editing")
                render(view: "edit", model: [configInstance: configInstance])
                return
            }
        }

        configInstance.properties = params

        if (!configInstance.save(flush: true)) {
            render(view: "edit", model: [configInstance: configInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'config.label', default: 'Config'), configInstance.id])
        redirect(action: "show", id: configInstance.id)
    }
    
    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        def configInstance = Config.get(id)
        if (!configInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'config.label', default: 'Config'), id])
            redirect(action: "list")
            return
        }

        try {
            configInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'config.label', default: 'Config'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'config.label', default: 'Config'), id])
            redirect(action: "show", id: id)
        }
    }
}
