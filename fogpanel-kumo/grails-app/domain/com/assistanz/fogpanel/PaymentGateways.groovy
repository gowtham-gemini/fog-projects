package com.assistanz.fogpanel

class PaymentGateways {
    String gatewayName;
    String description;
    String status;
    String gatewayType;
    Boolean isDefault = false;
    String includeExclude;
    Double processingFeePercent;
    Double processingFeeAmount;
    String gatewayURL;
    
    static constraints = {
        gatewayName (nullable: false, unique: true, blank: false);
        description (nullable: true);
        status (nullable: false, blank: false);
        gatewayType (nullable: false,blank: false)
        isDefault (nullable: false)
        includeExclude (nullable: true)
        processingFeePercent (nullable: true)
        processingFeeAmount (nullable: true)
        gatewayURL (nullable: true,blank: true)
    }
}
