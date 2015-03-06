package com.assistanz.app.service

import grails.transaction.Transactional
import com.assistanz.app.AppConstant
import com.assistanz.app.Config
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

@Transactional
class UpdateMailSettingService {
    
    def MessageSource messageSource

    def serviceMethod() {

    }
    
    // save or update the mail setting key value to DB
    def updateMailSetting(def params) {
        
//        // for application URL
//        this._saveOrUpdate(params?.applicationUrl, 
//            messageSource.getMessage('admin.applicationUrlInfo',null,LocaleContextHolder.locale),
//            AppConstant.MAIL_APPLICATIONURL)
        
        // for host
        this._saveOrUpdate(params?.host, 
            messageSource.getMessage('admin.emailHostInfo',null,LocaleContextHolder.locale),
            AppConstant.MAIL_HOST)
        
        // for userName
        this._saveOrUpdate(params?.userName, 
            messageSource.getMessage('admin.usernameInfo',null,LocaleContextHolder.locale),
            AppConstant.MAIL_USERNAME)
        
        // for password
        this._saveOrUpdate(params?.password, 
            messageSource.getMessage('admin.passwordInfo',null,LocaleContextHolder.locale),
            AppConstant.MAIL_PASSWORD)
        
        // for port
        this._saveOrUpdate(params?.port, 
            messageSource.getMessage('admin.emailPortInfo',null,LocaleContextHolder.locale),
            AppConstant.MAIL_PORT)
            
        // for secureConnection
        this._saveOrUpdate(params?.secureConnection, 
            messageSource.getMessage('admin.emailsecureConnectionInfo',null,LocaleContextHolder.locale),
            AppConstant.MAIL_SECURECONNECTION)
        
        // for from
        this._saveOrUpdate(params?.from, 
            messageSource.getMessage('admin.emailFromInfo',null,LocaleContextHolder.locale),
            AppConstant.MAIL_FROM)
        
        // for senderName
        this._saveOrUpdate(params?.senderName, 
            messageSource.getMessage('admin.senderNameInfo',null,LocaleContextHolder.locale),
            AppConstant.MAIL_SENDERNAME)
            
        
    }
    
    def _saveOrUpdate(def value, def description, def name) {
        
        Config config = Config.findByName(name)
        
        if(config == null) {
            
            println("config save")
            config = new Config(
                name:name,
                value:value,
                description:description)
            config.save flush:true
        } else {
            
            println("config update")
            config.value = value
            config.save flush:true
        }
    }
}
