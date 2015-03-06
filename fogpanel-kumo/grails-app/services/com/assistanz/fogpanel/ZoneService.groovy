package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.zone.CSZoneService;
import com.assistanz.cloud.cloudstack.zone.ListZonesResponse;
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.ZoneResponse;
import com.assistanz.cloud.cloudstack.alerts.CSAlertService
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import com.assistanz.cloud.cloudstack.network.CSNetworkService
import com.assistanz.cloud.cloudstack.network.NetworkResponse
import com.assistanz.cloud.cloudstack.systemcapacity.CSSystemCapacityService
import com.assistanz.cloud.cloudstack.systemcapacity.CapacityResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import grails.transaction.Transactional

@Transactional
class ZoneService {
    def springSecurityService;
    MessageSource messageSource;
    HypervisorService hypervisorService;
    OsTypeService osTypeService;
    DomainService domainService;
    def networkOfferService
    LicenseValidationService licenseValidationService
    
    def networkServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSNetworkService networkService = new CSNetworkService(server);
        
        return networkService;
        
    }
    
    def asyncJobServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSAsyncJobService jobService = new CSAsyncJobService(server);
        
        return jobService;
        
    }
    def listAllOffers (zoneReferenceId) {
        try {
            def user = springSecurityService.currentUser
            ArrayList rateCardArrayList = new ArrayList(); 
            
            ArrayList computingOfferArrayList = new ArrayList(); 
            ArrayList diskOfferArrayList = new ArrayList(); 
            ArrayList networkOfferArrayList = new ArrayList();  
            ArrayList templateArrayList = new ArrayList();  
            
            ArrayList misceBandwidthArrayList = new ArrayList();  
            ArrayList misceIPArrayList = new ArrayList();  
            ArrayList misceSnapshotArrayList = new ArrayList();  
            ArrayList misceVMSnapshotArrayList = new ArrayList();              
            ArrayList misceLoadBalancerArrayList = new ArrayList();              
            ArrayList miscePortForwardArrayList = new ArrayList();              
            
            HashMap rateCardItem = new HashMap();                
            def computingOfferResult = "";
            def diskOfferResult = "";
            def networkOfferResult = "";
            def templateResult = "";
            
            def bandwidthMiscelOfferResult = ""
            def ipMiscelOfferResult = ""
            def snapshotMiscelOfferResult = ""
            def vmSnapMiscelOfferResult = ""
            def loadBalancerMiscelOfferResult = ""
            def portForwardMiscelOfferResult = ""
            def resultTemplates = ""
            
            def language = "";
            if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                language = defaultLanguage.value
            } else {
                language = user.account.preferredLanguage
            }
            
            if(zoneReferenceId == null || zoneReferenceId == "All" || zoneReferenceId == "null" || zoneReferenceId == "") {
                computingOfferResult = ComputingOffer.findAllWhere(deleted: false, available: true);                 
                diskOfferResult = DiskOffer.findAllWhere(deleted: false, available: true);                
//                networkOfferResult = NetworkOffer.findAllWhere(deleted: false);
                templateResult = Template.findAllWhere(deleted: false, isReady: true);
                
                bandwidthMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(1));
                ipMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(2));
                snapshotMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(3));
                vmSnapMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(4));
                loadBalancerMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(5));
                portForwardMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(6));
                resultTemplates = Template.findAllWhere(deleted: false, isReady: true);
            } else {
                computingOfferResult = ComputingOffer.findAllWhere(deleted: false, available: true, zone: Zone.findByReferenceZoneId(zoneReferenceId));                 
                diskOfferResult = DiskOffer.findAllWhere(deleted: false, available: true, zone: Zone.findByReferenceZoneId(zoneReferenceId));                
//                networkOfferResult = NetworkOffer.findAllWhere(deleted: false, zone: Zone.findByReferenceZoneId(zoneReferenceId));
                
                bandwidthMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(1), zone: Zone.findByReferenceZoneId(zoneReferenceId));
                ipMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(2), zone: Zone.findByReferenceZoneId(zoneReferenceId));
                snapshotMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(3), zone: Zone.findByReferenceZoneId(zoneReferenceId));
                vmSnapMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(4), zone: Zone.findByReferenceZoneId(zoneReferenceId));
                loadBalancerMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(5), zone: Zone.findByReferenceZoneId(zoneReferenceId));
                portForwardMiscelOfferResult = MiscellaneousOfferZoneCost.findAllWhere(miscellaneousOffer: MiscellaneousOffer.get(6), zone: Zone.findByReferenceZoneId(zoneReferenceId));
                
                resultTemplates = Template.withCriteria {  
                    eq("deleted", false)
                    eq("isReady", true)
                    zones { 
                        eq("referenceZoneId", zoneReferenceId) 
                    }                
                }
            }
            
            for(int i =0 ; i < computingOfferResult.size(); i++) {
                def item = computingOfferResult[i];
                HashMap computingOfferItem = new HashMap();  
                if(item) {
                    computingOfferItem.put("id", item.offerReferenceId)
                    computingOfferItem.put("name", item.name)
                    computingOfferItem.put("cost", item.computingOfferZoneCosts[0].cost)  
                    computingOfferItem.put("unit", messageSource.getMessage('common.perHr', null, new Locale(language)))                      
                    computingOfferArrayList.add(computingOfferItem);
                }
            }
            for(int i =0 ; i < diskOfferResult.size(); i++) {
                def item = diskOfferResult[i];
                HashMap diskOfferItem = new HashMap();  
                if(item) {
                    diskOfferItem.put("id", item.diskOfferReferenceId)
                    diskOfferItem.put("name", item.name)
                    diskOfferItem.put("cost", item.diskOfferZoneCosts[0].cost) 
                    diskOfferItem.put("unit", messageSource.getMessage('common.gbPerHr', null, new Locale(language)))    
                    diskOfferArrayList.add(diskOfferItem);
                }
            }
//            for(int i =0 ; i < networkOfferResult.size(); i++) {
//                def item = networkOfferResult[i];
//                HashMap networkOfferItem = new HashMap();  
//                if(item) {
//                    networkOfferItem.put("id", item.networkReferenceId)
//                    networkOfferItem.put("name", item.name)
//                    networkOfferItem.put("cost", "0")  
//                    networkOfferItem.put("unit", messageSource.getMessage('common.perHr', null, new Locale(language)))      
//                    networkOfferArrayList.add(networkOfferItem);
//                }
//            }
            for(int i =0 ; i < bandwidthMiscelOfferResult.size(); i++) {
                def item = bandwidthMiscelOfferResult[i];
                HashMap miscBandwidthItem = new HashMap();  
                if(item) {
                    miscBandwidthItem.put("id", item.miscellaneousOffer.id)
                    miscBandwidthItem.put("name", item.miscellaneousOffer.name)
                    miscBandwidthItem.put("cost", item.cost)  
                    miscBandwidthItem.put("zone", item.zone.aliasName)         
                    miscBandwidthItem.put("unit", messageSource.getMessage('common.gbPerMonth', null, new Locale(language)))         
                    misceBandwidthArrayList.add(miscBandwidthItem);
                }
            }            

            for(int i =0 ; i < ipMiscelOfferResult.size(); i++) {
                def item = ipMiscelOfferResult[i];
                HashMap ipMiscItem = new HashMap();  
                if(item) {
                    ipMiscItem.put("id", item.miscellaneousOffer.id)
                    ipMiscItem.put("name", item.miscellaneousOffer.name)
                    ipMiscItem.put("cost", item.cost)  
                    ipMiscItem.put("zone", item.zone.aliasName)    
                    ipMiscItem.put("unit", messageSource.getMessage('common.perIPMonth', null, new Locale(language))) 
                    misceIPArrayList.add(ipMiscItem);
                }
            }
            for(int i =0 ; i < snapshotMiscelOfferResult.size(); i++) {
                def item = snapshotMiscelOfferResult[i];
                HashMap snapshotItem = new HashMap();  
                if(item) {
                    snapshotItem.put("id", item.miscellaneousOffer.id)
                    snapshotItem.put("name", item.miscellaneousOffer.name)
                    snapshotItem.put("cost", item.cost)  
                    snapshotItem.put("zone", item.zone.aliasName)   
                    snapshotItem.put("unit", messageSource.getMessage('common.gbPerHr', null, new Locale(language))) 
                    misceSnapshotArrayList.add(snapshotItem);
                }
            }
            for(int i =0 ; i < vmSnapMiscelOfferResult.size(); i++) {
                def item = vmSnapMiscelOfferResult[i];
                HashMap miscvmSnapItem = new HashMap();  
                if(item) {
                    miscvmSnapItem.put("id", item.miscellaneousOffer.id)
                    miscvmSnapItem.put("name", item.miscellaneousOffer.name)
                    miscvmSnapItem.put("cost", item.cost)  
                    miscvmSnapItem.put("zone", item.zone.aliasName)     
                    miscvmSnapItem.put("unit", messageSource.getMessage('common.gbPerHr', null, new Locale(language))) 
                    misceVMSnapshotArrayList.add(miscvmSnapItem);
                }
            } 
            for(int i =0 ; i < loadBalancerMiscelOfferResult.size(); i++) {
                def item = loadBalancerMiscelOfferResult[i]; 
                HashMap misclbItem = new HashMap();  
                if(item) {
                    misclbItem.put("id", item.miscellaneousOffer.id)
                    misclbItem.put("name", item.miscellaneousOffer.name)
                    misclbItem.put("cost", item.cost)  
                    misclbItem.put("zone", item.zone.aliasName)     
                    misclbItem.put("unit", messageSource.getMessage('common.perMonth', null, new Locale(language))) 
                    misceLoadBalancerArrayList.add(misclbItem);
                }
            } 
            for(int i =0 ; i < portForwardMiscelOfferResult.size(); i++) {
                def item = portForwardMiscelOfferResult[i]; 
                HashMap miscPfItem = new HashMap();  
                if(item) {
                    miscPfItem.put("id", item.miscellaneousOffer.id)
                    miscPfItem.put("name", item.miscellaneousOffer.name)
                    miscPfItem.put("cost", item.cost)  
                    miscPfItem.put("zone", item.zone.aliasName)     
                    miscPfItem.put("unit", messageSource.getMessage('common.perMonth', null, new Locale(language))) 
                    miscePortForwardArrayList.add(miscPfItem);
                }
            } 
            for(int i =0 ; i < resultTemplates.size(); i++) {
                def item = resultTemplates[i];
                HashMap templateItem = new HashMap();  
                if(item) {
                    templateItem.put("id", item.templateReferenceId)
                    templateItem.put("name", item.name)
                    templateItem.put("cost", item.cost)     
                    templateItem.put("unit", messageSource.getMessage('common.perMonth', null, new Locale(language))) 
                    templateArrayList.add(templateItem);
                }
            } 
            rateCardItem.put("ComputOfferInfo", computingOfferArrayList);
            rateCardItem.put("diskOfferInfo", diskOfferArrayList);
//            rateCardItem.put("networkOfferInfo", networkOfferArrayList);
            rateCardItem.put("bandwidthMisceInfo", misceBandwidthArrayList);
            rateCardItem.put("portForwardMiscInfo", miscePortForwardArrayList);  
            rateCardItem.put("ipMisceInfo", misceIPArrayList);
            rateCardItem.put("snapshotMiscInfo", misceSnapshotArrayList);
            rateCardItem.put("vmSnapshotMiscInfo", misceVMSnapshotArrayList);
            rateCardItem.put("loadBalancerMiscInfo", misceLoadBalancerArrayList);
            rateCardItem.put("templateInfo", templateArrayList);
            
            rateCardArrayList.add(rateCardItem);
            return rateCardArrayList;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def getCurrentZone(id) {
        try {
            def zone = Zone.get(id);
            ArrayList<ArrayList<String>> zoneList = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap(); 
            item.put("referenceId", zone.referenceZoneId);
            item.put("name", zone.aliasName);
            item.put("networkType", zone.networkType);           
            zoneList.add(item)
            return zoneList
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }        
    def list(advanced) {
       try {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
            
        CSZoneService zoneService = new CSZoneService(server);
        def response = zoneService.listZones();
            
        for(Iterator<ZoneResponse> iter = response.zones.iterator(); iter.hasNext();) {
                def data = iter.next();
                
                if(licenseValidationService.getProduct() == "FOG_BASIC"  && data.zoneNetworkType == "Basic") {
                    Zone zone = Zone.findByReferenceZoneId(data.zoneId);
                    if (!zone) {
                        addZone(data.zoneId, data.zoneName, data.zoneNetworkType, data.zoneName, data.zoneName)
                        hypervisorService.get(new String(data.zoneId));
                        domainService.list();
                        osTypeService.list();
                        listAlerts();
                        
                        def user = springSecurityService.currentUser
                        def role = springSecurityService.getPrincipal().getAuthorities();
                        if(role.iterator().next() == "ROLE_ADMIN" ) {
                           Account account = Account.findByUserName(user.username);
                           if(account.domain == null || account.domain == "" || account.domain == "null") {
                               account.domain = Domain.findByLevel(0);
                               account.save(flush: true);
                           }
                        }
                    }
                } else if(licenseValidationService.getProduct() == "FOG_ADVANCED") {
                    Zone zone = Zone.findByReferenceZoneId(data.zoneId);
                    if (!zone) {
                        addZone(data.zoneId, data.zoneName, data.zoneNetworkType, data.zoneName, data.zoneName)
                        hypervisorService.get(new String(data.zoneId));
                        domainService.list();
                        osTypeService.list();
                        listAlerts();
                                                
                        def user = springSecurityService.currentUser
                        def role = springSecurityService.getPrincipal().getAuthorities();
                        if(role.iterator().next() == "ROLE_ADMIN" ) {
                           Account account = Account.findByUserName(user.username);
                           if(account.domain == null || account.domain == "" || account.domain == "null") {
                               account.domain = Domain.findByLevel(0);
                               account.save(flush: true);
                           }
                        }
                    }
                }   
            }
            networkOfferService.list(null) 
            def result = "";
            
            if(licenseValidationService.getProduct() == "FOG_BASIC") {
                result = Zone.findAllWhere(networkType: "Basic");
            } else if(licenseValidationService.getProduct() == "FOG_ADVANCED") {
               result = Zone.findAll();
            }
            
            ArrayList<ArrayList<String>> zoneList = new ArrayList<ArrayList<String>>();            
            for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                HashMap<String,String> zoneItem = new HashMap<String,String>();  
                    zoneItem.put("id", item.id);
                    zoneItem.put("name", item.name);
                    zoneItem.put("referenceZoneId", item.referenceZoneId);
                    zoneItem.put("aliasName", item.aliasName);
                    zoneItem.put("aliasDescription", item.aliasDescription);
                    zoneItem.put("available", item.available);
                    zoneItem.put("orderNo", item.orderNo);
                    zoneItem.put("networkType", item.networkType);
                    zoneList.add(zoneItem);
            }
            return zoneList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def addDefaultNetwork(zoneId) {
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("zoneid", zoneId);
        networkServer().listNetworks(optional)
            
        def response = networkServer().listNetworks(optional);
        for(Iterator <NetworkResponse> iter = response.networks.iterator(); iter.hasNext();) {
            def data = iter.next();

            if(data.networkName == "defaultGuestNetwork" || data.networkName == "guestNetworkForBasicZone") {
                def network = Network.findByReferenceId(data.networkId)
                if(!network) {
                    network = new Network()
                    network.referenceId = data.networkId
                    network.account = Account.findByAccountType("ADMIN");
                    network.createdDate = new Date()
                    network.description = data.networkDisplayText
                    network.name = data.networkName
                    network.state = data.networkState
                    network.type = data.networkType
                    network.networkOffer = NetworkOffer.findByNetworkReferenceId(data.networkOfferingId)
                    network.zone = Zone.findByReferenceZoneId(zoneId)
                    network.save(flush: true)
                    if (!network.save()) {
                        network.errors.allErrors.each { println it }
                    }
                }
            }
        } 
    }
    
    def addZone(zoneId, zoneName, zoneNetworkType, aliasZoneName, aliasZoneDescription) {
        def zone  = new Zone();
        zone.referenceZoneId = zoneId;
        zone.aliasName = aliasZoneName;
        zone.aliasDescription = aliasZoneDescription;
        zone.name = zoneName;
        zone.available = true;
        zone.orderNo = 1;
        zone.networkType = zoneNetworkType;
        zone.save(flush: true);
        if (!zone.save()) {
            zone.errors.allErrors.each { Console.print(it) }
        }
        
    }
    
    def update(String requestBody) {
        try {           
            
            // convert string to json object
            def requestData = JSON.parse(requestBody) 
            
            def oldZone = Zone.findByReferenceZoneId(requestData.id);

            oldZone.aliasDescription = requestData.aliasDescription
            oldZone.aliasName = requestData.aliasName
            if(oldZone.validate()) {
                oldZone.save(flush: true)
                if (oldZone.hasErrors()) {
                    throw new ValidationException(oldZone.errors.allErrors);
            } 
            ArrayList<ArrayList<String>> zoneList = new ArrayList<ArrayList<String>>();
            HashMap<String,String> zone = new HashMap<String,String>(); 
            zone.put("result", GeneralConstants.RESULT_SUCCESS);
            zone.put("zoneReferenceId", oldZone.referenceZoneId);
            zone.put("zoneName", oldZone.aliasName);
            zone.put("zoneDescription", oldZone.aliasDescription);
            zoneList.add(zone);   

            return zoneList;
            } else {
                throw new ValidationException("Validation failed");
            }                          
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }
    
    def listAlerts() {
        
        try {
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server =
                     new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

            CSAlertService csAlertService = new CSAlertService(server);
            
            HashMap<String,String> optional = new HashMap<String,String>(); 
            optional.put("type", "13");
            def response = csAlertService.listAlerts(optional);
            
            if(response.alerts.size() > 0) {
                response.alerts[0]
            
                Config cloudStackUpDate =  Config.findByName(Config.CLOUD_STACK_URL_SERVER_UP_DATE);
                cloudStackUpDate.value = response.alerts[0].alertSent
                cloudStackUpDate.save(flush: true);

                return response.alerts[0].alertSent;
            }
            
                           
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def listCapacity(String itemId, String type) {                
        try {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            def language = defaultLanguage.value
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server =
                     new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

            CSSystemCapacityService cSSystemCapacityService = new CSSystemCapacityService(server);
            
            HashMap<String,String> optional = new HashMap<String,String>(); 
            optional.put(type, itemId);
            def response = cSSystemCapacityService.listCapacity(optional);
            
            ArrayList<ArrayList<String>> capList = new ArrayList<ArrayList<String>>();
            HashMap<String,String> capItem = new HashMap<String,String>(); 
            for(Iterator<CapacityResponse> capIter = response.capacities.iterator(); capIter.hasNext();) { 
                def capData = capIter.next();
                
                if(capData.capacityType == "0"){
                    double total =  Double.parseDouble(capData.capacityTotal)/1073741824;
                    double used =  Double.parseDouble(capData.capacityUsed)/1073741824;
                    capItem.put("memoryTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+": " + Math.round(used*100)/100 + messageSource.getMessage('common.gb', null, new Locale(language)) + "  / "+ Math.round(total*100)/100 + " GB");
                    capItem.put("memoryPercentage", Double.parseDouble(capData.percentUsed));
                }  else if(capData.capacityType == "1") {
                    double total =  Double.parseDouble(capData.capacityTotal)/1000;
                    double used =  Double.parseDouble(capData.capacityUsed)/1000;
                    capItem.put("cpuTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+": " + Math.round(used*100)/100 + messageSource.getMessage('common.ghz', null, new Locale(language)) + "  / "+ Math.round(total*100)/100 + " GHz");
                    capItem.put("cpuPercentage", Double.parseDouble(capData.percentUsed));
                } else if(capData.capacityType == "2") {
                    double total =  Double.parseDouble(capData.capacityTotal)/1073741824;
                    double used =  Double.parseDouble(capData.capacityUsed)/1073741824;
                    capItem.put("storageTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+ ": " + Math.round(used*100)/100 + " / "+ Math.round(total*100)/100 + messageSource.getMessage('common.gb', null, new Locale(language)));
                    capItem.put("storagePercentage", Double.parseDouble(capData.percentUsed));
                } else if(capData.capacityType == "3") {
                    double total =  Double.parseDouble(capData.capacityTotal)/1073741824;
                    double used =  Double.parseDouble(capData.capacityUsed)/1073741824;
                    capItem.put("primaryStorageTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+": " + Math.round(used*100)/100 + messageSource.getMessage('common.gb', null, new Locale(language)) +  " / "+ Math.round(total*100)/100 + messageSource.getMessage('common.gb', null, new Locale(language)));
                    capItem.put("primaryStoragePercentage", Double.parseDouble(capData.percentUsed));
                } else if(capData.capacityType == "4") {
                    capItem.put("publicIpTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+":" + capData.capacityUsed + " / "+ capData.capacityTotal);
                    capItem.put("publicIpPercentage", Double.parseDouble(capData.percentUsed));
                } else if(capData.capacityType == "5") {
                    capItem.put("managementIpTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+":" + capData.capacityUsed + " / "+ capData.capacityTotal);
                    capItem.put("managementIpPercentage", Double.parseDouble(capData.percentUsed));
                } else if(capData.capacityType == "6") {
                    double total =  Double.parseDouble(capData.capacityTotal)/1073741824;
                    double used =  Double.parseDouble(capData.capacityUsed)/1073741824;
                    capItem.put("secodaryTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+ ": " + Math.round(used*100)/100 + messageSource.getMessage('common.gb', null, new Locale(language)) +" / "+ Math.round(total*100)/100 + messageSource.getMessage('common.gb', null, new Locale(language)));
                    capItem.put("secodaryPercentage", Double.parseDouble(capData.percentUsed));
                } else if(capData.capacityType == "7") {
                    capItem.put("VLANTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+ ":" + capData.capacityUsed + " / "+ capData.capacityTotal);
                    capItem.put("VLANPercentage", Double.parseDouble(capData.percentUsed)); 
                } else if(capData.capacityType == "8") {
                    capItem.put("directPublicIpTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+ ": " + capData.capacityUsed + " / "+ capData.capacityTotal);
                    capItem.put("directPublicIpPercentage", Double.parseDouble(capData.percentUsed));
                } else if(capData.capacityType == "9") {
                    double total =  Double.parseDouble(capData.capacityTotal)/1073741824;
                    double used =  Double.parseDouble(capData.capacityUsed)/1073741824;
                    capItem.put("localStorageTotal", messageSource.getMessage('common.allocated', null, new Locale(language))+": " + Math.round(used*100)/100 + messageSource.getMessage('common.gb', null, new Locale(language)) +"  / "+ Math.round(total*100)/100 + messageSource.getMessage('common.gb', null, new Locale(language)));
                    capItem.put("localStoragePercentage", Double.parseDouble(capData.percentUsed));
                }
            }
            capList.add(capItem) 
            
            return capList;               
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }
	
} 
