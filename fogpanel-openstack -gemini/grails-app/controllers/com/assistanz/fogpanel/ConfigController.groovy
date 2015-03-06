package com.assistanz.fogpanel

import com.assistanz.cloud.config.ConfigLoader
import org.springframework.dao.DataIntegrityViolationException
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

class ConfigController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
//    LicenseValidationService licenseValidationService
}
