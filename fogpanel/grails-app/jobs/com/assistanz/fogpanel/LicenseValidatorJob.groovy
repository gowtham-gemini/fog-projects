package com.assistanz.fogpanel



class LicenseValidatorJob {
    
    LicenseValidationService licenseValidationService
    
    static triggers = {
      
    }

    def execute() {
        licenseValidationService.validateOnline();
    }
}
