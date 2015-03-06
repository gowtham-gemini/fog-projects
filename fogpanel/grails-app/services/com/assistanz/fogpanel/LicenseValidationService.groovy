package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.host.CSHostService
import com.assistanz.fogpanel.*;
import com.assistanz.licensor.Licensor;
import com.assistanz.licensor.License;
import com.assistanz.licensor.LicenseManager;
import com.assistanz.licensor.LicenseException;
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;

@Transactional
class LicenseValidationService {
    
    private static License license; //License
    private static Properties licenseValidationProperties = new Properties();
    private static final log = LogFactory.getLog(this)
    
    def getLicense() {
        licenseValidationProperties = new Properties()
        //ApplicationHolder.getApplication().config.license?.ssl?.noverify ? "true" : "false"
        licenseValidationProperties.setProperty("ssl.noverify", "true")
        //    private String additionalData;
        Calendar calendar = Calendar.getInstance();
        
        if (!license) {
            license = new License();
            license.requestID = Config.findByName(Config.FOG_INSTANCE_ID).value;
            license.email = Config.findByName(Config.LICENSEE_EMAIL).value;
            license.checksum = Config.findByName(Config.UPDATE_CHECKSUM).value;
            license.hostName = InetAddress.getLocalHost().getHostName();
            license.hostIP = InetAddress.getLocalHost().getHostAddress();  
            license.timeZone = calendar.getTimeZone().getID();
            license.initialVersion = AppVersion.first().fogVersion
            license.currentVersion = AppVersion.last().fogVersion
            license.additionalData = "";
            //            license.lastUpdatedDate = "";
            //            license.deployedDate = "";
            //            license.registrationDate = "";
        }
        license.sockets = getCloudStackHostCount();
        return license;
            
    }
    
    /**
     * returns FOG_BASIC or FOG_ADVANCED
     */
    String getProduct() {
        
        getLicense();
        try {
            Licensor licensor = LicenseManager.getLicensor(license.getInitialVersion(), license.getRequestID(), license.getEmail(), licenseValidationProperties);
//            Console.print(license.checksum)
            return licensor.getProduct(license.checksum)
        } catch (Exception ex) {
            log.fatal("Unexpected problem in license last update checksum.", ex)
            return "ERROR !!!"
        }
        
    }
        
    def getCloudStackHostCount() {
        
        try {
            
            def cloudStackUrl = ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server =
            new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

            CSHostService csHostService = new CSHostService(server);

            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("type", "Routing")
            def response = csHostService.listHosts(optional);


            return response.hosts.size();
            
        }  catch(Exception ex) {
            
            log.fatal("Unable to Connect to cloud Stack Server.");
            return 0;
        }
        
        
    }

    def authorizeAction(FogPanelService service) {
        
            
        if(isAllowed() == true) {
            return true 
        }
       
        /**
         * VM service actions
         */   
        if(service == FogPanelService.VM_CREATE) {
            throw new LicenseExpiredException("Create VM Not supported yet."); 
        } else if(service == FogPanelService.VM_LIST) {
//            throw new LicenseExpiredException(" VM list Not supported yet.");
        } else if(service == FogPanelService.VM_START) {
//            throw new LicenseExpiredException("Start VM Not supported yet.");
        } else if(service == FogPanelService.VM_STOP) {
//            throw new LicenseExpiredException("Stop VM Not supported yet.");
        } else if(service == FogPanelService.VM_VIEW_CONSOLE) {
            throw new LicenseExpiredException("Console Not supported yet.");
        } else if(service == FogPanelService.VM_REBOOT) {
//            throw new LicenseExpiredException("Reboot VM Not supported yet.");
        }  else if(service == FogPanelService.VM_DELETE) {
            throw new LicenseExpiredException("Reboot VM Not supported yet.");
        } else if(service == FogPanelService.VM_RESTORT) {
//            throw new LicenseExpiredException("Restore VM Not supported yet.");
        } else if(service == FogPanelService.VM_CHANGE_PLAN) {
            throw new LicenseExpiredException("Change plan Not supported yet.");
        } else if(service == FogPanelService.VM_RESET_PASSWORD) {
//            throw new LicenseExpiredException("Reset password Not supported yet.");
        } else if(service == FogPanelService.VM_UPDATE_HOSTNAME) {
//            throw new LicenseExpiredException("Host name change Not supported yet.");
        } else if(service == FogPanelService.VM_MIGRATE) {
//            throw new LicenseExpiredException("VM Migrate Not supported yet.");
        } else if(service == FogPanelService.VM_ACQUIRE_IP) {
//            throw new LicenseExpiredException("VM Acquire IP Not supported yet.");
        } else if(service == FogPanelService.VM_REVOKE_IP) {
//            throw new LicenseExpiredException("VM Revoke IP Not supported yet.");
        } else if(service == FogPanelService.VM_ATTACH_ISO) {
            throw new LicenseExpiredException("VM Attach ISO Not supported yet.");
        } else if(service == FogPanelService.VM_DETACH_ISO) {
            throw new LicenseExpiredException("VM Detach ISO Not supported yet.");
        }
        
        /**
         * Volume service actions
         */
        else if(service == FogPanelService.VOLUME_CREATE) {
            throw new LicenseExpiredException("Volume Create Not supported yet.");
        } else if(service == FogPanelService.VOLUME_ATTACH) {
            throw new LicenseExpiredException("Volume Attach Not supported yet.");
        } else if(service == FogPanelService.VOLUME_RESIZE) {
            throw new LicenseExpiredException("Volume Resize Not supported yet.");
        } else if(service == FogPanelService.VOLUME_DETACH) {
            throw new LicenseExpiredException("Volume Detach Not supported yet.");
        } else if(service == FogPanelService.VOLUME_DELETE) {
            throw new LicenseExpiredException("Volume Delete Not supported yet.");
        } 
        /**
         * Snapshot service actions
         */
        else if(service == FogPanelService.SNAPSHOT_CREATE) {
            throw new LicenseExpiredException("Snapshot Create Not supported yet.");
        } else if(service == FogPanelService.SNAPSHOT_DELETE) {
            throw new LicenseExpiredException("Snapshot Delete Not supported yet.");
        }
        /**
         * VM Snapshot service actions
         */
        else if(service == FogPanelService.VM_SNAPSHOT_CREATE) {
            throw new LicenseExpiredException("VM Snapshot Create Not supported yet.");
        } else if(service == FogPanelService.VM_SNAPSHOT_REVERT) {
            throw new LicenseExpiredException("VM Snapshot Revert Not supported yet.");
        } else if(service == FogPanelService.VM_SNAPSHOT_DELETE) {
            throw new LicenseExpiredException("VM Snapshot Delete Not supported yet.");
        }
        /**
         * Snapshot Policy service actions
         */
        else if(service == FogPanelService.SNAPSHOT_POLICY_CREATE) {
            throw new LicenseExpiredException("Snapshot Policy Create Not supported yet.");
        } else if(service == FogPanelService.SNAPSHOT_POLICY_DELETE) {
            throw new LicenseExpiredException("Snapshot Policy Delete Not supported yet.");
        } else if(service == FogPanelService.SNAPSHOT_POLICY_UPDATE) {
            throw new LicenseExpiredException("Snapshot Policy Update Not supported yet.");
        }
        
        /**
         * Security Group service actions
         */
        else if(service == FogPanelService.SECURITY_GROUP_CREATE) {
//            throw new LicenseExpiredException("Security Group Create Not supported yet.");
        } else if(service == FogPanelService.SECURITY_GROUP_DELETE) {
//            throw new LicenseExpiredException("Security Group Delete Not supported yet.");
        } else if(service == FogPanelService.SECURITY_GROUP_ADD_INGRESS) {
//            throw new LicenseExpiredException("Security Group Add Ingress Update Not supported yet.");
        } else if(service == FogPanelService.SECURITY_GROUP_ADD_EGRESS) {
//            throw new LicenseExpiredException("Security Group Add Egress Not supported yet.");
        } else if(service == FogPanelService.SECURITY_GROUP_DELETE_INGRESS) {
//            throw new LicenseExpiredException("Security Group Delete Ingress Not supported yet.");
        } else if(service == FogPanelService.SECURITY_GROUP_DELETE_EGRESS) {
//            throw new LicenseExpiredException("Security Group Delete Egress Not supported yet.");
        }
        
        /**
         * Account service
         */
        else if(service == FogPanelService.ACCOUNT_SIGNUP) {
//            throw new LicenseExpiredException("Account Signup Not supported yet.");
        } 
        
        /**
         * SSH Key service
         */
        else if(service == FogPanelService.SSH_KEY_CREATE) {
//            throw new LicenseExpiredException("SSH Key Create Not supported yet.");
        } else if(service == FogPanelService.SSH_KEY_DELETE) {
//            throw new LicenseExpiredException("SSH Key Delete Not supported yet.");
        } else if(service == FogPanelService.SSH_KEY_RESET) {
//            throw new LicenseExpiredException("SSH Key Reset Not supported yet.");
        }
        
        /**
         * Billing service
         */
        else if(service == FogPanelService.INVOICE_ITEM_ADD) {
            throw new LicenseExpiredException("Add invoice item Not supported yet.");
        } else if(service == FogPanelService.RECURRING_ITEM_DELETE) {
            throw new LicenseExpiredException("Recurring Item Delete Not supported yet.");
        } else if(service == FogPanelService.RECURRING_TIEM_UPDATE) {
            throw new LicenseExpiredException("Recurring Item Update Not supported yet.");
        } else if(service == FogPanelService.PAYMENT_ADD) {
            throw new LicenseExpiredException("Add Payment Not supported yet.");
        }
        
        /**
         * Offering service
         */
        else if(service == FogPanelService.OFFERING_ADD) {
            throw new LicenseExpiredException("Add offering Not supported yet.");
        } else if(service == FogPanelService.OFFERING_UPDATE) {
            throw new LicenseExpiredException("offering UpdateNot supported yet.");
        } else if(service == FogPanelService.OFFERING_DELETE) {
            throw new LicenseExpiredException("offering delete Not supported yet.");
        } else if(service == FogPanelService.OFFERING_DISABLE) {
            throw new LicenseExpiredException("offering disable Not supported yet.");
        }
        
    }
    
    Boolean isAllowed() {
        
        getLicense();
        
        Licensor licensor = LicenseManager.getLicensor(license.getInitialVersion(), license.getRequestID(), license.getEmail(), licenseValidationProperties);
        return licensor.isValid(license.checksum);
    }
    
    def getLicenseStatus() {
         
        getLicense();
        
        try {
            Licensor licensor = LicenseManager.getLicensor(license.getInitialVersion(), license.getRequestID(), license.getEmail(), licenseValidationProperties);
//            Console.print(license.checksum)
            return licensor.getStatus(license.checksum, getCloudStackHostCount()).name()
        } catch (Exception ex) {
            log.fatal("Unexpected problem in license last update checksum.", ex)
            return "ERROR !!!"
        }
        
    }
    
    def validateOnline() { 
        //Should force Licensor to to get data from Fog Server
        //String requestID, String licenseeEmail, Properties properties
        getLicense();
        
        Licensor licensor = LicenseManager.getLicensor(license.getInitialVersion(), license.getRequestID(), license.getEmail(), licenseValidationProperties);
        license = licensor.validate(license, getCloudStackHostCount())
                
        if(getProduct() == "FOG_BASIC" || getProduct() == "FOG_ADVANCED") {

        } else {
            throw new Exception("Invalid Product") 
        }
                
        //save checksum
        Config checksum =  Config.findByName(Config.UPDATE_CHECKSUM);
        checksum.value = license.checksum;
        checksum.save(flush: true);
    }
    
    def registerOnline(fogInstanceId, licenseeEmail) {
        
        try {
            //Should force Licensor to to get data from Fog Server
            //String requestID, String licenseeEmail, Properties properties
            
            if(ComputingOffer.count() != 0 || DiskOffer.count() != 0) {
                throw new LicenseExpiredException("License Cannot be Registered Possible License Crack")
            }
            
            getLicense();
                        
            Licensor licensor = LicenseManager.getLicensor(license.getInitialVersion(), fogInstanceId, licenseeEmail, licenseValidationProperties);
            license.requestID = fogInstanceId
            license.email = licenseeEmail
                                    
            license = licensor.register(license, getCloudStackHostCount()) 
            
            if(getProduct() == "FOG_BASIC" || getProduct() == "FOG_ADVANCED") {
                
            } else {
                throw new Exception("Invalid Product") 
            }
                                   
            Config fogInstanceIdConfig =  Config.findByName(Config.FOG_INSTANCE_ID);
            fogInstanceIdConfig.value = fogInstanceId;
            fogInstanceIdConfig.save(flush: true);

            Config licenseeEmailConfig =  Config.findByName(Config.LICENSEE_EMAIL);
            licenseeEmailConfig.value = licenseeEmail;
            licenseeEmailConfig.save(flush: true);

            //save checksum
            Config checksum =  Config.findByName(Config.UPDATE_CHECKSUM);
            checksum.value = license.checksum;
            checksum.save(flush: true);
        } catch(NullPointerException ex) {
            log.fatal("Unexpected problem in license registration.", ex);
            Console.print(ex)
            throw new InvalidLicenseException("Unknown error while validating. Contact Support.");
        } catch(Exception ex) {
            Console.print(ex)
            log.fatal("Unexpected problem in license registration. ", ex);
            throw new InvalidLicenseException(ex);
        }
    }
}
