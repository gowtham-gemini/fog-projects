package com.assistanz.fogpanel

/**
 * Config maintains the default configuration for the system, which can used system wide
 * and can be modified when needed
 */
//@gorm.AuditStamp
class Config {
	
    String name
    String value
    String description
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
        
    public static final String CLOUD_STACK_URL = "CLOUD_STACK_URL";
    public static final String CLOUD_STACK_ROOT_API_KEY = "CLOUD_STACK_ROOT_API_KEY";
    public static final String CLOUD_STACK_ROOT_SECRET_KEY = "CLOUD_STACK_ROOT_SECRET_KEY";
    public static final String CLOUD_STACK_URL_SERVER_UP_DATE = "cloud.stack.server.up.date";
    
    public static final String MAIL_CONFIG_HOST = "MAIL_CONFIG_HOST";
    public static final String MAIL_CONFIG_USER_NAME = "MAIL_CONFIG_USER_NAME";
    public static final String MAIL_CONFIG_PASSWORD = "MAIL_CONFIG_PASSWORD";
    public static final String MAIL_CONFIG_PORT = "MAIL_CONFIG_PORT";
    public static final String MAIL_CONFIG_FROM = "MAIL_CONFIG_FROM";
    public static final String MAIL_CONFIG_SSL = "MAIL_CONFIG_SSL";
    public static final String MAX_LOGIN_FAILURE = "MAX_LOGIN_FAILURE";
    public static final String MAX_LOGIN_FAILURE_PER_IP = "MAX_LOGIN_FAILURE_PER_IP";
    public static final String ACCOUNT_UNLOCK_TIME = "ACCOUNT_UNLOCK_TIME";
    public static final String APPLICATION_URL = "APPLICATION_URL";
    public static final String LINK_ACTIVE_TIME = "LINK_ACTIVE_TIME";
    public static final String BILLING_PERIOD_DAYS = "billing.period.days";
    public static final String PAYMENT_DUE_DAYS = "payment.due.days";
    public static final String BILLING_CYCLE_FIXED_ENABLED = "billing.cycle.fixed.enabled";
    public static final String BILLING_PERIOD_FIXED_DAY = "billing.period.fixed.day";
    public static final String BILLING_DEFAULT_CURRENCY= "billing.default.currency";
    public static final String SINGLE_SIGN_ON_URL= "single.sign.on.url";
    
    public static final String MONTHLY_BILLING_ENABLE= "monthly.billing.enabled";
        
    public static final String INVOICE_CUSTOM_FIELD_1 = "";
    public static final String INVOICE_CUSTOM_FIELD_2 = "";
    public static final String INVOICE_CUSTOM_FIELD_3 = "";
    public static final String INVOICE_CUSTOM_FIELD_4 = "";
    public static final String INVOICE_CUSTOM_FIELD_5 = "";
    public static final String INVOICE_CUSTOM_FIELD_6 = "";
    public static final String INVOICE_CUSTOM_FIELD_7 = "";
    public static final String INVOICE_CUSTOM_FIELD_8 = "";
    public static final String CUSTOM_FIELD_1 = "";
    public static final String CUSTOM_FIELD_2 = "";
    public static final String CUSTOM_FIELD_3 = "";
    public static final String CUSTOM_FIELD_4 = "";
    public static final String CUSTOM_FIELD_5 = "";
    
    public static final String CREDIT_LIMIT_NOTIFICATION_LEVEL_1 = "credit.limit.notification.level1";
    public static final String CREDIT_LIMIT_NOTIFICATION_LEVEL_2 = "credit.limit.notification.level2";
    public static final String CREDIT_LIMIT_NOTIFICATION_LEVEL_3 = "credit.limit.notification.level3";
    
    public static final String LATE_FEE_MINIMUM_AMOUNT = "late.fee.minimum.amount";
    public static final String LATE_FEE_PERCENTAGE = "late.fee.percendage";
    public static final String LATE_FEE_APPLICABLE_AMOUNT = "late.fee.applicable.amount";
    
    public static final String ORGANISATION_ADDRESS = "organisation.address";
    public static final String ORGANISATION_ADDRESS_EXTENSION = "organisation.address.extension";
    public static final String ORGANISATION_ADDRESS_CITY = "organisation.address.city"
    public static final String ORGANISATION_ADDRESS_STATE = "organisation.address.state"
    public static final String ORGANISATION_ADDRESS_ZIP = "organisation.address.zip"
    public static final String ORGANISATION_ADDRESS_COUNTRY = "organisation.address.counrty"
    
    public static final String DEFAULT_LANGUAGE = "default.language";
    public static final String DATE_FORMAT = "date.formate";
    
    
    public static final String ORGANISATION_NAME = "organisation.name";
    public static final String ORGANISATION_EMAIL = "organisation.email";   
    
    public static final String ORGANISATION_BILLING_PHONE_NUMBER1 = "organisation.billing.phone.number1";
    public static final String ORGANISATION_BILLING_PHONE_NUMBER2 = "organisation.billing.phone.number2";
    public static final String ORGANISATION_BILLING_PHONE_NUMBER3 = "organisation.billing.phone.number3";
    
    public static final String ORGANISATION_BILLING_FAX_NUMBER1 = "organisation.billing.fax.number1";
    public static final String ORGANISATION_BILLING_FAX_NUMBER2 = "organisation.billing.fax.number2";
    public static final String ORGANISATION_BILLING_FAX_NUMBER3 = "organisation.billing.fax.number3";
    public static final String ORGANISATION_BILLING_LOGO = "organisation.billing.logo";
    public static final String ORGANISATION_BACKGROUND_IMG_URL = "organisation.background.img.url";
    
    public static final String ORGANISATION_BILLING_SINGNATURE = "organisation.billing.signature";
    
    public static final String ORGANISATION_BILLING_LEGAL_DOCUMENT = "organisation.billing.legal.document";
    public static final String ORGANISATION_BILLING_TERMS_POLICY = "organisation.billing.terms.policy";
    public static final String ORGANISATION_BILLING_RUFUND_SERVICE = "organisation.billing.refund.service";
    public static final String ORGANISATION_BILLING_TERMS_CONDITIONS = "organisation.billing.terms.conditions";
    
    public static final String INSTANCE_MINIMUM_COST_APPLICABLE_HOUR = "instance.minimum.cost.applicable.hour";
         
    public static final String PAYMENT_PROCESSING_FEE_AMOUNT = "payment.processing.fee.amount";
    public static final String PAYMENT_PROCESSING_FEE_PERCENTAGE = "payment.processing.fee.percentage";
    public static final String PAYMENT_PROCESSING_FEE_TYPE = "payment.processing.fee.type";
    
    public static final String SIGNUP_CARD_VERIFICATION_ENABLED  = "signup.card.verification.enabled";
    
    public static final String CARD_PROCESSING_ENABLED  = "creditcard.processing";
    public static final String CARD_PROCESSING_ON_CREATE_VM_ENABLED  = "creditcard.processing.in.createvm";
    
    public static final String SIGNUP_CARD_VERIFICATION_VALUE  = "signup.card.verification.value";
    
    public static final String SIGNUP_TYPE_TRIAL_ENABLED  = "signup.type.trial.enabled";
    
    public static final String SIGNUP_SETTING = "signup.setting";
    
    public static final String SIGNUP_TYPE_TRIAL_CREDIT_LIMIT  = "signup.type.trial.credit.limit";
    public static final String SIGNUP_TYPE_RETAIL_CREDIT_LIMIT  = "signup.type.retail.credit.limit";
    
    
    public static final String PAYMENT_GW_PAYPAL_HTTP_TIMEOUT = "payment.gateway.paypal.http.ConnectionTimeOut";
    public static final String PAYMENT_GW_PAYPAL_HTTP_RETRY = "payment.gateway.paypal.http.Retry";
    public static final String PAYMENT_GW_PAYPAL_HTTP_READ_TIME_OUT = "payment.gateway.paypal.http.ReadTimeOut";
    public static final String PAYMENT_GW_PAYPAL_HTTP_MAX_CONNECTION  = "payment.gateway.paypal.http.MaxConnection";
    public static final String PAYMENT_GW_PAYPAL_HTTP_PROXY_PORT = "payment.gateway.paypal.http.ProxyPort";
    public static final String PAYMENT_GW_PAYPAL_HTTP_PROXY_HOST = "payment.gateway.paypal.http.ProxyHost";
    public static final String PAYMENT_GW_PAYPAL_HTTP_USE_PROXY = "payment.gateway.paypal.http.UseProxy";
    public static final String PAYMENT_GW_PAYPAL_HTTP_USER = "payment.gateway.paypal.http.ProxyUserName";
    public static final String PAYMENT_GW_PAYPAL_HTTP_PASSWORD = "payment.gateway.paypal.http.ProxyPassword";
    public static final String PAYMENT_GW_PAYPAL_HTTP_APP_ENGINE = "payment.gateway.paypal.http.GoogleAppEngine";
    public static final String PAYMENT_GW_PAYPAL_SERVICE_ENDPOINT = "payment.gateway.paypal.service.EndPoint";
    public static final String PAYMENT_GW_PAYPAL_CLIENT_ID = "payment.gateway.paypal.clientID";
    public static final String PAYMENT_GW_PAYPAL_CLIENT_SECRET = "payment.gateway.paypal.clientSecret";
    public static final String PAYMENT_GW_CCAVENUE_MERCHANT = "payment.gateway.ccavenue.merchant";
    public static final String PAYMENT_GW_CCAVENUE_WORKING_KEY = "payment.gateway.ccavenue.working.key";
    public static final String PAYMENT_GW_PAYPAL = "PayPal";
    public static final String PAYMENT_GW_CCAVENUE = "CCAvenue";
    
    public static final String PAYMENT_GW_AUTHORIZENET_API_KEY= "payment.gateway.authorizenet.apiKey";
    public static final String PAYMENT_GW_AUTHORIZENET_API_SECRET = "payment.gateway.authorizenet.apiSecret";
    public static final String PAYMENT_GW_AUTHORIZENET_ENVIRONMENT= "payment.gateway.authorizenet.environment";
    public static final String PAYMENT_GW= "payment.gateway";
    
    
    public static final String INSTANCE_LIMIT = "instance.limit";
    public static final String STORAGE_LIMIT = "storage.limit";
    public static final String SNAPSHOT_LIMIT = "snapshot.limit";
    public static final String BANDWIDTH_LIMIT = "bandwidth.limit";
    
    
    public static final String RETAIL_INSTANCE_LIMIT = "retail.instance.limit";
    public static final String RETAIL_STORAGE_LIMIT = "retail.storage.limit";
    public static final String RETAIL_SNAPSHOT_LIMIT = "retail.snapshot.limit";
    public static final String RETAIL_BANDWIDTH_LIMIT = "retail.bandwidth.limit";
    public static final String RETAIL_PUBLIC_IP_LIMIT = "retail.public.ip.limit";
    public static final String RETAIL_VPC_LIMIT = "retail.vpc.limit";
    public static final String RETAIL_CUP_LIMIT = "retail.cpu.limit";
    public static final String RETAIL_MEMORY_LIMIT = "retail.memory.limit";
    public static final String RETAIL_PRIMARY_STORAGE_LIMIT = "retail.primary.storage.limit";
    public static final String RETAIL_SECONDARY_STORAGE_LIMIT = "retail.secondary.storage.limit";
    public static final String RETAIL_NETWORK_LIMIT = "retail.network.limit";
    public static final String RETAIL_ENABLE_THRESHOLDLIMIT = "retail.enableThresholdLimit";
      
    public static final String USAGE_BILLING_CALC_TYPE = "usage.billing.calc.type";
    
    
    public static final String FOG_INSTANCE_ID = "fog.instanceId";
    public static final String LICENSEE_EMAIL = "licensee.email";
    public static final String UPDATE_CHECKSUM = "update.checksum";
    
    
    static constraints = {       
        name (blank: false, unique: true)
        value (nullable: false, blank: true)
        description (nullable: false, blank: true)
                
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
    
    static mapping = {
      value sqlType:"longtext"
    }
    
}
