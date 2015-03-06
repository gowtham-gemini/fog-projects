package com.assistanz.fogpanel.licensemanager

import grails.converters.JSON

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class ValidationController {

    def validationService
    
    def register() { 
        
        String data = request.reader.text
        Console.print("INPUT:" + data);
        
        def license = JSON.parse(data)
        license = validationService.register(license)
        Console.print("OUTPUT:" + license.toString());
        
        render license
    
    }
    
    def validate() {
        
        String data = request.reader.text
        
        Console.print("INPUT:" + data);
        
        def license = JSON.parse(data)
        license = validationService.validate(license) 
        
        Console.print("OUTPUT:" + license.toString());
        
        render license
    }
}
