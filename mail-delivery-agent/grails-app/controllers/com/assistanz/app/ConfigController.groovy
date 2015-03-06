package com.assistanz.app



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.deep.JSON
import com.assistanz.app.AppConstant
import com.assistanz.app.ConfigService
import com.assistanz.app.service.*
import com.assistanz.app.ConfigLoader;
@Transactional(readOnly = true)
class ConfigController {
    
    UpdateMailSettingService updateMailSettingService
    ConfigService configService
    

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Config.list(params), model:[configInstanceCount: Config.count()]
    }

    def show(Config configInstance) {
        respond configInstance
    }

    def create() {
        respond new Config(params)
    }                 
    
    def saveSetting() {
        
        Config configInstance = Config.findByName(AppConstant.SAVE_CONFIG)
        if(configInstance == null) {
            println("save form")
            respond new Config(params)
        } else {
            println("edit form")
            println("value"+configInstance.value)
            respond configInstance
        }
    }
    
    @Transactional
    def updateSaveSetting(Config configInstance) {
        
        def configInstanceJson = configInstance as JSON
        println("configInstanceJson"+configInstanceJson)
        println("saveSettingValue"+params?.saveSettingValue)
        
        // update or save the savetype configuration
        Config saveConfig = Config.findByName(AppConstant.SAVE_CONFIG)
        
        if(saveConfig == null) {
            
            println("save")
            saveConfig = new Config(
                name:AppConstant.SAVE_CONFIG,
                value:params?.saveSettingValue,
                description:message(code: 'config.saveSetting.description'))
            saveConfig.save flush:true
        } else {
            
            println("update")
            saveConfig.value = params?.saveSettingValue
            saveConfig.save flush:true
        }
        
        // after changes gets over, change the isConfigModified flag to true
        ConfigLoader.isConfigModified = true
        
        // explicit redirect
        flash.message = message(code: 'config.saveSetting.message')
        redirect action: "index", method: "GET"
        return 
        
    }
    
    def mailSetting() {
        
        List<Config> configs = Config.list()
        
        /**
         * iterating configs list and assign to map having 
         * key as setting's config 
         * and 
         * value as setting's config value
         **/
        if(configs != null && !configs.empty) {
            
            Map<String,String> map = new HashMap<String,String>();
            for (Config config : configs) map.put(config.name, config.value);
            respond Config.list(params), model:[configMap: map]
        } else {
            respond new Config(params)
        }
    }
    
    @Transactional
    def updateMailSetting(Config configInstance) {
        
        def configInstanceJson = configInstance as JSON
        println("configInstanceJson"+configInstanceJson)
        println("params"+params)
        
        updateMailSettingService.updateMailSetting(params)
        
        // after changes gets over, change the isConfigModified flag to true
        ConfigLoader.isConfigModified = true
        
        // explicit redirect
        flash.message = message(code: 'config.saveSetting.message')
        redirect action: "index", method: "GET"
        return 
    }
    
    def varifyMailConfig() {
        try {
            println("test mail enter")
            configService.varifyMailConfig()
            
            // explicit redirect
            flash.message = message(code: 'config.testMail.message.success')
            redirect action: "index", method: "GET"
            return 
               
        } catch(Exception ex){
            // explicit redirect
            flash.message = message(code: 'config.testMail.message.failure')
            redirect action: "index", method: "GET"
            return 
        }
    }
    def testMailConfig() {
        
        println("params"+params)
        def jsonString = request.JSON
        println(""+jsonString)
        println("host"+jsonString.host)
        configService.testMailWithNewConfig(jsonString)
        
        render "OK" 
    }
    
    def syncConfiguration() {
        
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadProperties();
        
        // after sync gets over, change the isConfigModified flag to false
        ConfigLoader.isConfigModified = false
        
        flash.message = message(code: 'config.sync.message.success')
        redirect action: "index", method: "GET"
        return 
    }

    @Transactional
    def save(Config configInstance) {
        if (configInstance == null) {
            notFound()
            return
        }

        if (configInstance.hasErrors()) {
            respond configInstance.errors, view:'create'
            return
        }

        configInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'config.label', default: 'Config'), configInstance.id])
                redirect configInstance
            }
            '*' { respond configInstance, [status: CREATED] }
        }
    }

    def edit(Config configInstance) {
        respond configInstance
    }

    @Transactional
    def update(Config configInstance) {
        if (configInstance == null) {
            notFound()
            return
        }

        if (configInstance.hasErrors()) {
            respond configInstance.errors, view:'edit'
            return
        }

        configInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Config.label', default: 'Config'), configInstance.id])
                redirect configInstance
            }
            '*'{ respond configInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Config configInstance) {

        if (configInstance == null) {
            notFound()
            return
        }

        configInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Config.label', default: 'Config'), configInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'config.label', default: 'Config'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
