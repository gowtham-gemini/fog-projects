package com.assistanz.fogpanel

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.springframework.web.bind.annotation.RequestBody;
import javax.ws.rs.GET
import javax.ws.rs.POST;
import javax.ws.rs.Path
import javax.ws.rs.PUT
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.QueryParam
import grails.converters.deep.JSON
import com.assistanz.fogpanel.Config
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.cloud.config.ConfigLoader;
import com.assistanz.openstack.OpenStackServer;
import org.springframework.security.core.AuthenticationException

/** 
 * Acts as the rest service gateway for all configuration stored and managed
 */
@Path('/api/config')
class ConfigResource {
    ConfigService configService
//    ZoneService zoneService;
    def licenseValidationService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    def list() {
        Config.list()
    }
	
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") Long id) {   
        Config.get(id)
    }
    
    @GET
    @Path("/currency")
    @Produces(MediaType.APPLICATION_JSON)
    def getCurrency() {
        def currencyData = ApplicationHolder.getApplication().config.billing.defaultCurrency
//        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
//        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
//        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        def singleSignOnUrl = ApplicationHolder.getApplication().config.openstack.singleSignOnUrl
        
        ArrayList<ArrayList<String>> cur = new ArrayList<ArrayList<String>>();
        HashMap currency = new HashMap();  
        currency.put("currency",  currencyData);  
//        currency.put("cloudStackUrl",  cloudStackUrl);  
//        currency.put("cloudStackApiKey",  cloudStackApiKey);  
//        currency.put("cloudStackSecretKey",  cloudStackSecretKey);  
        currency.put("singleSignOnUrl",  singleSignOnUrl);  
        
        cur.add(currency)
        
        return cur as JSON;
    }
    
    @GET
    @Path('/test')
    @Produces(MediaType.APPLICATION_JSON)
    def testConfig() {
        try {
            def  cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
            if(cloudStackUrl == "" || cloudStackApiKey == "" || cloudStackSecretKey == "") {
                ["FAILED"]
          }  else {
//              zoneService.list()
                ["OK"]
          }
        } catch(Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path('/ccAvenueConfig')
    @Produces(MediaType.APPLICATION_JSON)
    def ccAvenueStatus() {
        configService.getCCAvenueConfig()
    }
    
    @GET
    @Path('/paymentGateways')
    @Produces(MediaType.APPLICATION_JSON)
    def getEnabledPaymentGateways() {
        try{
        configService.getEnabledPaymentGateway() as JSON
        }
        catch(Exception e){
            System.out.println(e);
            [e] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            Config url =  Config.findByName(Config.CLOUD_STACK_URL);
            url.value = requestData.url
            url.save(flush: true)
            Config rootKey = Config.findByName(Config.CLOUD_STACK_ROOT_API_KEY);
            rootKey.value = requestData.apiKey
            rootKey.save(flush: true)
            Config secretKey = Config.findByName(Config.CLOUD_STACK_ROOT_SECRET_KEY);
            secretKey.value = requestData.secretKey
            secretKey.save(flush: true)
            Config ssoUrl = Config.findByName(Config.SINGLE_SIGN_ON_URL);
            ssoUrl.value = requestData.ssoUrl
            ssoUrl.save(flush: true)
            
                ["OK"]
        } catch(Exception ex){
            [ex] as JSON
        }
        
               
    }
	
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(Config config, @PathParam("id") Long id) {
        try {
            def oldConfig = Config.get(id);
            oldConfig.value = config.value
            oldConfig.save(flush: true)
                ["OK"]
        } catch(Exception ex){
            ["FAILED"]
        }
        
    }
    
    @POST
    @Path("/mailConfig")
    @Produces(MediaType.APPLICATION_JSON)
    def setMailConfig(@RequestBody String requestBody) {
        try {
            configService.setMailConfig(requestBody)
                ["OK"]
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST   
    @Path("/billingType")
    @Produces(MediaType.APPLICATION_JSON)
    def setBillingType(@RequestBody String requestBody) {
        try {
            configService.setBillingType(requestBody)
                ["OK"]
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/billingType")
    @Produces(MediaType.APPLICATION_JSON)
    def getBillingType() {
        try {
            configService.getBillingType()            
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/language")
    @Produces(MediaType.APPLICATION_JSON)
    def setDefaultLanguage(@RequestBody String requestBody) {
        try {
            configService.setDefaultLanguage(requestBody)
                ["OK"]
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/signupSetting")
    @Produces(MediaType.APPLICATION_JSON)
    def setSignupDefaultSetting(@RequestBody String requestBody) {
        try {
            configService.setSignupDefaultSetting(requestBody)
                ["OK"]
        } catch(Exception ex) {
                [ex] as JSON
        }
    }    
    
    @POST
    @Path("/trialManagement")
    @Produces(MediaType.APPLICATION_JSON)
    def setTrialManagement(@RequestBody String requestBody) {
        try {
            configService.setTrialManagementConfig(requestBody)
                ["OK"] as JSON
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/mailConfig")
    @Produces(MediaType.APPLICATION_JSON)
    def getMailConfig() {
        try {
            configService.getMailConfig() as JSON
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/systemOverview")
    @Produces(MediaType.APPLICATION_JSON)
    def systemOverview() {
        try {
            configService.systemOverview() as JSON
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/revalidateLicense")
    @Produces(MediaType.APPLICATION_JSON)
    def revalidateLicense() {
        try {
            licenseValidationService.validateOnline() as JSON
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/loginSecurity")
    @Produces(MediaType.APPLICATION_JSON)
    def setLoginSecurity(@RequestBody String requestBody) {
        try {
            configService.setLoginSecurity(requestBody)
                ["OK"]
        } catch(Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/paymentConfig")
    @Produces(MediaType.APPLICATION_JSON)
    def paymentConfig(@RequestBody String requestBody) {
        try {
            configService.paymentConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    }
    
    @POST
    @Path("/authorizeNet")
    @Produces(MediaType.APPLICATION_JSON)
    def authorizeNetConfig(@RequestBody String requestBody) {
        try {
            configService.setAuthorizeNetConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    }
    
    @POST
    @Path("/paypal")
    @Produces(MediaType.APPLICATION_JSON)
    def paypalConfig(@RequestBody String requestBody) {
        try {
            configService.setPaypalConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    }
    
    @POST
    @Path("/billingConfig")
    @Produces(MediaType.APPLICATION_JSON)
    def setBillingConfig(@RequestBody String requestBody) {
        try {
            // call set billing function
            configService.setBillingConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    } 
    
    
    @POST
    @Path("/creditLimitNotification") 
    @Produces(MediaType.APPLICATION_JSON)
    def setCreditLimitNotification(@RequestBody String requestBody) {
        try {
            configService.setCreditLimitNotification(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    } 
    
    @POST
    @Path("/retailManagement")
    @Produces(MediaType.APPLICATION_JSON)
    def setRetailManagementConfig(@RequestBody String requestBody) {
        try {
            configService.setRetailManagementConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    } 
    
    @POST
    @Path("/paymentCardConfig")
    @Produces(MediaType.APPLICATION_JSON)
    def setPaymentCardConfig(@RequestBody String requestBody) {
        try {
            configService.setPaymentCardConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    } 
    
    
    @POST
    @Path("/invoiceConfig")
    @Produces(MediaType.APPLICATION_JSON)
    def invoiceConfig(@RequestBody String requestBody) {
        try {
            configService.setInvoiceConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    } 
    
    @POST
    @Path("/lateFee")
    @Produces(MediaType.APPLICATION_JSON)
    def setLateFeeConfig(@RequestBody String requestBody) {
        try {
            configService.setLateFeeConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    } 
    
    @POST
    @Path("/general")
    @Produces(MediaType.APPLICATION_JSON)
    def setGeneralConfig(@RequestBody String requestBody) {
        try {
            configService.setGeneralConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }
        
    }  
       
    @POST
    @Path("/oganizationConfig")
    @Produces(MediaType.APPLICATION_JSON)
    def setOrganisationConfig(@RequestBody String requestBody) {
        try {
            configService.setOrganisationConfig(requestBody)
                ["OK"]
        } catch(Exception ex) {
                [ex] as JSON
        }
        
    }  
       
    
    @GET
    @Path("/mailConfig/test")
    @Produces(MediaType.APPLICATION_JSON)
    def testMailConfig() {
        try {
            configService.testMailConfig()
                ["OK"]
        } catch(Exception ex){
               [ex] as JSON
        }
        
    }
    
    @POST
    @Path("/mailConfig/test")
    @Produces(MediaType.APPLICATION_JSON)
    def varifyMailConfig(@RequestBody String requestBody) {
        try {
            configService.varifyMailConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
               [ex] as JSON
        }
        
    }
    
    @GET
    @Path("/listCSCapabilities")
    @Produces(MediaType.APPLICATION_JSON)
    def listCSCapabilities() {
        try {
            configService.listCSCapabilities() as JSON
            } catch(Exception ex){
               [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/CSConfig/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getCSConfig(@PathParam("id") String id) {
        try {
            configService.getCSConfig(id) as JSON
            } catch(Exception ex){
               [ex] as JSON
        }
    }
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    def delete(Long id) {
        def config = Config.get(id)
        config.delete(flush: true)
    }   
    
    @POST
    @Path("/ccAvenue")
    @Produces(MediaType.APPLICATION_JSON)
    def ccAvenueConfig(@RequestBody String requestBody) {
        try {
            configService.setCcAvenueConfig(requestBody)
                ["OK"]
        } catch(Exception ex){
                [ex] as JSON
        }        
    } 
        
    @POST
    @Path("/openstackConfig/update")
    @Produces(MediaType.APPLICATION_JSON)
    def updateOpenstackConfig(@RequestBody String requestBody) {
         try {
            def requestData = JSON.parse(requestBody)
            Config endpointUrl =  Config.findByName(Config.OPENSTACK_ENDPOINT_URL);
            endpointUrl.value = requestData.endpointUrl
            endpointUrl.save(flush: true)
            
            Config adminUuid =  Config.findByName(Config.OPENSTACK_ADMIN_UUID);
            adminUuid.value = requestData.adminUuid
            adminUuid.save(flush: true)
                        
            Config adminPassword =  Config.findByName(Config.OPENSTACK_ADMIN_PASSWORD);
            adminPassword.value = requestData.adminPassword
            adminPassword.save(flush: true)
            
            ConfigLoader.isConfigModified = true
            return ["OK"]
        } catch(Exception ex){
            [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/zenossConfig/update")
    @Produces(MediaType.APPLICATION_JSON)
    def updateZenossConfig(@RequestBody String requestBody) {
         try {
            def requestData = JSON.parse(requestBody)
            Config endpointUrl =  Config.findByName(Config.ZENOSS_ENDPOINT_URL);
            endpointUrl.value = requestData.endpointUrl
            endpointUrl.save(flush: true)
            
            Config zenossUsername =  Config.findByName(Config.ZENOSS_USERNAME);
            zenossUsername.value = requestData.zenossUsername
            zenossUsername.save(flush: true)
                        
            Config zenossPassword =  Config.findByName(Config.ZENOSS_PASSWORD);
            zenossPassword.value = requestData.zenossPassword
            zenossPassword.save(flush: true)
            
            ConfigLoader.isConfigModified = true
            return ["OK"]
        } catch(Exception ex){
            [ex] as JSON
        }
    }
    
    
    
    @GET
    @Path("/property/reload")
    @Produces(MediaType.APPLICATION_JSON)
    def reloadConfigurationProperty() {
        try {

//            configService.reloadConfigurationProperty()
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadProperties();
            
        Properties props = configLoader.getProperties();

        // after sync gets over, change the isConfigModified flag to false
        ConfigLoader.isConfigModified = false
            
            ["OK"]
        } catch(Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/property/getConfig")
    @Produces(MediaType.APPLICATION_JSON)
    def getConfigurationFromProperty() {
        try {
            ConfigLoader configLoader = ConfigLoader.getInstance();    
            Properties props = configLoader.getProperties();
//        
            ArrayList<ArrayList<String>> configList = new ArrayList<ArrayList<String>>(); 
            
            for (String key : props.stringPropertyNames())
            {              
                HashMap<String,String> item = new HashMap<String,String>();   
                item.put("name", key);
                item.put("value", props.getProperty(key));

                // add to config list
                configList.add(item);
                
            }
            configList as JSON 
            
        } catch(Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/configChangeCheck")
    @Produces(MediaType.APPLICATION_JSON)
    def checkIsConfigModified() {

        ConfigLoader configLoader = ConfigLoader.getInstance(); 
        
        if(ConfigLoader.isConfigModified == true) {

            ["true"]
        } else {
            ["false"]
        }

    }
    
    @GET
    @Path("/isOpenStackAuthenticated")
    @Produces(MediaType.APPLICATION_JSON)
    def checkIsOpenStackAuthenticated(@QueryParam("endpointUrl") String endpointUrl,
                                      @QueryParam("adminUuid") String adminUuid,
                                      @QueryParam("adminPassword") String adminPassword) throws Exception{
        
        configService.checkOpenStackAuthenticated(endpointUrl, adminUuid, adminPassword);
       
    }
    
    @GET
    @Path("/roleCreation")
    @Produces(MediaType.APPLICATION_JSON)
    def userRoleCreation() {
        
        configService.userRoleCreation();
    }
    
}
