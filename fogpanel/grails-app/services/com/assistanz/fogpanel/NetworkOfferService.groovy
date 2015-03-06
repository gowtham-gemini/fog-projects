package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.network.CSNetworkService
import com.assistanz.cloud.cloudstack.network.NetworkOfferingResponse

import java.util.List;
import java.util.ArrayList;
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.*
import com.assistanz.cloud.cloudstack.ServiceResponse
import com.assistanz.cloud.cloudstack.CapabilityResponse;
import org.codehaus.groovy.grails.commons.ApplicationHolder

class NetworkOfferService {
    
    def springSecurityService
    def zoneService
    def licenseValidationService
    
    def networkServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
        new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSNetworkService networkService = new CSNetworkService(server);
        
        return networkService;
        
    }
    def pullNetworkFromZone(zoneReferenceId) {
        
         try {
            def zoneList = Zone.findAll()
            for(def zone: zoneList) {
                               
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("state", "Enabled");
                optional.put("guestiptype", "Isolated");
                optional.put("supportedservices", "StaticNat,SourceNat,Firewall,UserData,PortForwarding,Lb,Vpn,Dhcp,Dns");   
                optional.put("zoneid", zone.referenceZoneId);  

                def response = networkServer().listNetworkOfferings(optional);
                
                for(Iterator <NetworkOfferingResponse> iter = response.networkOfferings.iterator(); iter.hasNext();) {
                    def data = iter.next();
                
                    NetworkOffer networkOffer = NetworkOffer.findByNetworkReferenceId(data.networkOfferingId);
                    if(!networkOffer) {
                        networkOffer = new NetworkOffer()
                        networkOffer.networkReferenceId = data.networkOfferingId
                        networkOffer.name = data.networkOfferingName
                        networkOffer.description = data.networkOfferingDisplayText
                        networkOffer.networkRate = data.networkRate
                        networkOffer.specifyVlan = Boolean.parseBoolean(data.networkOfferingSpecifyVlan)
                        networkOffer.trafficType = data.networkOfferingTrafficType
                        networkOffer.conserveMode = Boolean.parseBoolean(data.networkOfferingConserveMode)
                        networkOffer.isDefault = Boolean.parseBoolean(data.networkOfferingIsDefault)
                        networkOffer.availability = data.networkOfferingAvailability  
                        networkOffer.guestIpType = data.networkOfferingGuestIpType  
                        networkOffer.specifyIpRanges = Boolean.parseBoolean(data.networkOfferingSpecifyIpRanges)
                        networkOffer.deleted = false
                        networkOffer.state = data.networkOfferingState
//                        networkOffer.zone = zone
                        networkOffer.created = new Date()
                        networkOffer.save(flush: true);
                        if (!networkOffer.save()) {
                            networkOffer.errors.allErrors.each { println it }
                        }
                         
                        for(Iterator <ServiceResponse> serviceIter = data.services.iterator(); serviceIter.hasNext();) {
                            def serviceData = serviceIter.next();
                            def networkOfferServiceList = new NetworkOfferServiceList()
                            networkOfferServiceList.networkOffer = networkOffer
                            networkOfferServiceList.service = serviceData.name
                            networkOfferServiceList.provider = serviceData.serviceProvider 
                            networkOfferServiceList.created = new Date()
                            networkOfferServiceList.save(flush: true);
    //                        for(Iterator <CapabilityResponse> capabilityIter = serviceData.capabilities.iterator(); capabilityIter.hasNext();) {
    //                            def  capabilityData = capabilityIter.next();  
    //                            Console.print("Capabilityname:" + capabilityData.capabilityName);
    //                            Console.print("Capabilityvalue:" + capabilityData.capabilityValue);
    //                            Console.print("Capabilitychoose:" + capabilityData.canChooseServiceCapability);
    //                        }              
                        } 
                    } else if(networkOffer) {
                        
                        NetworkOfferZoneCost networkOfferZoneCost = NetworkOfferZoneCost.findWhere(networkOffer: networkOffer, zone:zone)
                        if(!networkOfferZoneCost) {
                            networkOfferZoneCost = new NetworkOfferZoneCost()
                            networkOfferZoneCost.networkOffer = networkOffer
                            networkOfferZoneCost.cost = 0
                            networkOfferZoneCost.zone = zone
                            networkOfferZoneCost.save(flush: true)
                        }
                        
                    }
                }
                                
                HashMap<String,String> optionalShared  = new HashMap<String,String>();
                optionalShared.put("state", "Enabled");
                optionalShared.put("guestiptype", "Shared");
                if(zone.networkType == "Advanced") {
                    optionalShared.put("supportedservices", "UserData,Dhcp,Dns"); 
                    optionalShared.put("zoneid", zone.referenceZoneId);  
                }  
                def responseShared = networkServer().listNetworkOfferings(optionalShared);
                
                for(Iterator <NetworkOfferingResponse> iterShared = responseShared.networkOfferings.iterator(); iterShared.hasNext();) {
                    def dataShared = iterShared.next();
                
                    NetworkOffer networkOfferShared = NetworkOffer.findByNetworkReferenceId(dataShared.networkOfferingId);
                    if(!networkOfferShared) {
                        networkOfferShared = new NetworkOffer()
                        networkOfferShared.networkReferenceId = dataShared.networkOfferingId
                        networkOfferShared.name = dataShared.networkOfferingName
                        networkOfferShared.description = dataShared.networkOfferingDisplayText
                        networkOfferShared.networkRate = dataShared.networkRate
                        networkOfferShared.specifyVlan = Boolean.parseBoolean(dataShared.networkOfferingSpecifyVlan) 
                        networkOfferShared.trafficType = dataShared.networkOfferingTrafficType
                        networkOfferShared.conserveMode = Boolean.parseBoolean(dataShared.networkOfferingConserveMode)
                        networkOfferShared.isDefault = Boolean.parseBoolean(dataShared.networkOfferingIsDefault)    
                        networkOfferShared.availability = dataShared.networkOfferingAvailability  
                        networkOfferShared.guestIpType = dataShared.networkOfferingGuestIpType  
                        networkOfferShared.specifyIpRanges = Boolean.parseBoolean(dataShared.networkOfferingSpecifyIpRanges) 
                        networkOfferShared.deleted = false
                        networkOfferShared.state = dataShared.networkOfferingState
//                        networkOfferShared.zone = zone
                        networkOfferShared.created = new Date()
                        networkOfferShared.save(flush: true);
                        if (!networkOfferShared.save()) {
                            networkOfferShared.errors.allErrors.each { println it }
                        }
                        
                        for(Iterator <ServiceResponse> serviceIter = dataShared.services.iterator(); serviceIter.hasNext();) {
                            def serviceDataShared = serviceIter.next();
                            def networkOfferServiceListShared = new NetworkOfferServiceList()
                            networkOfferServiceListShared.networkOffer = networkOfferShared
                            networkOfferServiceListShared.service = serviceDataShared.name
                            networkOfferServiceListShared.provider = serviceDataShared.serviceProvider 
                            networkOfferServiceListShared.created = new Date()
                            networkOfferServiceListShared.save(flush: true);
    //                        for(Iterator <CapabilityResponse> capabilityIter = serviceData.capabilities.iterator(); capabilityIter.hasNext();) {
    //                            def  capabilityData = capabilityIter.next();  
    //                            Console.print("Capabilityname:" + capabilityData.capabilityName);
    //                            Console.print("Capabilityvalue:" + capabilityData.capabilityValue);
    //                            Console.print("Capabilitychoose:" + capabilityData.canChooseServiceCapability);
    //                        }              
                        } 
                    } else if(networkOfferShared) {
                        
                        NetworkOfferZoneCost networkOfferZoneCost = NetworkOfferZoneCost.findWhere(networkOffer: networkOfferShared, zone:zone) 
                        if(!networkOfferZoneCost) {
                            networkOfferZoneCost = new NetworkOfferZoneCost()
                            networkOfferZoneCost.networkOffer = networkOfferShared
                            networkOfferZoneCost.cost = 0
                            networkOfferZoneCost.zone = zone
                            networkOfferZoneCost.save(flush: true)
                        }
                        
                    }
                }
                
                HashMap<String,String> vpcOptional = new HashMap<String,String>();
                vpcOptional.put("state", "Enabled");
                vpcOptional.put("guestiptype", "Isolated");
                vpcOptional.put("supportedservices", "Lb,SourceNat,Dns,StaticNat,Vpn,UserData,PortForwarding,NetworkACL,Dhcp");   
                vpcOptional.put("zoneid", zone.referenceZoneId);  

                def vpcNetworkOfferResponse = networkServer().listNetworkOfferings(vpcOptional);
                
                for(Iterator <NetworkOfferingResponse> iter = vpcNetworkOfferResponse.networkOfferings.iterator(); iter.hasNext();) {
                    def data = iter.next();
                
                    NetworkOffer networkOffer = NetworkOffer.findByNetworkReferenceId(data.networkOfferingId);
                    if(!networkOffer) {
                        networkOffer = new NetworkOffer()
                        networkOffer.networkReferenceId = data.networkOfferingId
                        networkOffer.name = data.networkOfferingName
                        networkOffer.description = data.networkOfferingDisplayText
                        networkOffer.networkRate = data.networkRate
                        networkOffer.specifyVlan = Boolean.parseBoolean(data.networkOfferingSpecifyVlan)
                        networkOffer.trafficType = data.networkOfferingTrafficType
                        networkOffer.conserveMode = Boolean.parseBoolean(data.networkOfferingConserveMode)
                        networkOffer.isDefault = Boolean.parseBoolean(data.networkOfferingIsDefault)
                        networkOffer.availability = data.networkOfferingAvailability  
                        networkOffer.guestIpType = data.networkOfferingGuestIpType  
                        networkOffer.specifyIpRanges = Boolean.parseBoolean(data.networkOfferingSpecifyIpRanges)
                        networkOffer.deleted = false
                        networkOffer.state = data.networkOfferingState
//                        networkOffer.zone = zone
                        networkOffer.created = new Date()
                        networkOffer.save(flush: true);
                        if (!networkOffer.save()) {
                            networkOffer.errors.allErrors.each { println it }
                        }
                        
                        for(Iterator <ServiceResponse> serviceIter = data.services.iterator(); serviceIter.hasNext();) {
                            def serviceData = serviceIter.next();
                            def networkOfferServiceList = new NetworkOfferServiceList()
                            networkOfferServiceList.networkOffer = networkOffer
                            networkOfferServiceList.service = serviceData.name
                            networkOfferServiceList.provider = serviceData.serviceProvider 
                            networkOfferServiceList.created = new Date()
                            networkOfferServiceList.save(flush: true);
    //                        for(Iterator <CapabilityResponse> capabilityIter = serviceData.capabilities.iterator(); capabilityIter.hasNext();) {
    //                            def  capabilityData = capabilityIter.next();  
    //                            Console.print("Capabilityname:" + capabilityData.capabilityName);
    //                            Console.print("Capabilityvalue:" + capabilityData.capabilityValue);
    //                            Console.print("Capabilitychoose:" + capabilityData.canChooseServiceCapability);
    //                        }              
                        } 
                    } else if(networkOffer) {
                        NetworkOfferZoneCost networkOfferZoneCost = NetworkOfferZoneCost.findWhere(networkOffer: networkOffer, zone:zone)
                        if(!networkOfferZoneCost) {
                            networkOfferZoneCost = new NetworkOfferZoneCost()
                            networkOfferZoneCost.networkOffer = networkOffer
                            networkOfferZoneCost.cost = 0
                            networkOfferZoneCost.zone = zone
                            networkOfferZoneCost.save(flush: true)
                        }
                        
                    }
                }
                
                HashMap<String,String> vpcSet1Optional = new HashMap<String,String>();
                vpcSet1Optional.put("state", "Enabled");
                vpcSet1Optional.put("guestiptype", "Isolated");
                vpcSet1Optional.put("supportedservices", "SourceNat,Dns,StaticNat,Vpn,UserData,PortForwarding,NetworkACL,Dhcp");   
                vpcSet1Optional.put("zoneid", zone.referenceZoneId);  

                def vpcNetworkOfferSet1Response = networkServer().listNetworkOfferings(vpcSet1Optional);
                
                for(Iterator <NetworkOfferingResponse> iter = vpcNetworkOfferSet1Response.networkOfferings.iterator(); iter.hasNext();) {
                    def data = iter.next();
                
                    NetworkOffer networkOffer = NetworkOffer.findByNetworkReferenceId(data.networkOfferingId);
                    if(!networkOffer) {
                        networkOffer = new NetworkOffer()
                        networkOffer.networkReferenceId = data.networkOfferingId
                        networkOffer.name = data.networkOfferingName
                        networkOffer.description = data.networkOfferingDisplayText
                        networkOffer.networkRate = data.networkRate
                        networkOffer.specifyVlan = Boolean.parseBoolean(data.networkOfferingSpecifyVlan)
                        networkOffer.trafficType = data.networkOfferingTrafficType
                        networkOffer.conserveMode = Boolean.parseBoolean(data.networkOfferingConserveMode)
                        networkOffer.isDefault = Boolean.parseBoolean(data.networkOfferingIsDefault)
                        networkOffer.availability = data.networkOfferingAvailability  
                        networkOffer.guestIpType = data.networkOfferingGuestIpType  
                        networkOffer.specifyIpRanges = Boolean.parseBoolean(data.networkOfferingSpecifyIpRanges)
                        networkOffer.deleted = false
                        networkOffer.state = data.networkOfferingState
//                        networkOffer.zone = zone
                        networkOffer.created = new Date()
                        networkOffer.save(flush: true);
                        if (!networkOffer.save()) {
                            networkOffer.errors.allErrors.each { println it }
                        }
                        
                        for(Iterator <ServiceResponse> serviceIter = data.services.iterator(); serviceIter.hasNext();) {
                            def serviceData = serviceIter.next();
                            def networkOfferServiceList = new NetworkOfferServiceList()
                            networkOfferServiceList.networkOffer = networkOffer
                            networkOfferServiceList.service = serviceData.name
                            networkOfferServiceList.provider = serviceData.serviceProvider 
                            networkOfferServiceList.created = new Date()
                            networkOfferServiceList.save(flush: true);
    //                        for(Iterator <CapabilityResponse> capabilityIter = serviceData.capabilities.iterator(); capabilityIter.hasNext();) {
    //                            def  capabilityData = capabilityIter.next();  
    //                            Console.print("Capabilityname:" + capabilityData.capabilityName);
    //                            Console.print("Capabilityvalue:" + capabilityData.capabilityValue);
    //                            Console.print("Capabilitychoose:" + capabilityData.canChooseServiceCapability);
    //                        }              
                        } 
                    } else if(networkOffer) {
                        NetworkOfferZoneCost networkOfferZoneCost = NetworkOfferZoneCost.findWhere(networkOffer: networkOffer, zone:zone)
                        if(!networkOfferZoneCost) {
                            networkOfferZoneCost = new NetworkOfferZoneCost()
                            networkOfferZoneCost.networkOffer = networkOffer
                            networkOfferZoneCost.cost = 0
                            networkOfferZoneCost.zone = zone
                            networkOfferZoneCost.save(flush: true)
                        }
                        
                    }
                }
                
                HashMap<String,String> vpcSet2Optional = new HashMap<String,String>();
                vpcSet2Optional.put("state", "Enabled");
                vpcSet2Optional.put("guestiptype", "Isolated");
                vpcSet2Optional.put("supportedservices", "Lb,SourceNat,Dns,UserData,Dhcp");   
                vpcSet2Optional.put("zoneid", zone.referenceZoneId);  

                def vpcNetworkOfferSet2Response = networkServer().listNetworkOfferings(vpcSet2Optional);
                
                for(Iterator <NetworkOfferingResponse> iter = vpcNetworkOfferSet2Response.networkOfferings.iterator(); iter.hasNext();) {
                    def data = iter.next();
                
                    NetworkOffer networkOffer = NetworkOffer.findByNetworkReferenceId(data.networkOfferingId);
                    if(!networkOffer) {
                        networkOffer = new NetworkOffer()
                        networkOffer.networkReferenceId = data.networkOfferingId
                        networkOffer.name = data.networkOfferingName
                        networkOffer.description = data.networkOfferingDisplayText
                        networkOffer.networkRate = data.networkRate
                        networkOffer.specifyVlan = Boolean.parseBoolean(data.networkOfferingSpecifyVlan)
                        networkOffer.trafficType = data.networkOfferingTrafficType
                        networkOffer.conserveMode = Boolean.parseBoolean(data.networkOfferingConserveMode)
                        networkOffer.isDefault = Boolean.parseBoolean(data.networkOfferingIsDefault)
                        networkOffer.availability = data.networkOfferingAvailability  
                        networkOffer.guestIpType = data.networkOfferingGuestIpType  
                        networkOffer.specifyIpRanges = Boolean.parseBoolean(data.networkOfferingSpecifyIpRanges)
                        networkOffer.deleted = false
                        networkOffer.state = data.networkOfferingState
//                        networkOffer.zone = zone
                        networkOffer.created = new Date()
                        networkOffer.save(flush: true);
                        if (!networkOffer.save()) {
                            networkOffer.errors.allErrors.each { println it }
                        }
                        
                        for(Iterator <ServiceResponse> serviceIter = data.services.iterator(); serviceIter.hasNext();) {
                            def serviceData = serviceIter.next();
                            def networkOfferServiceList = new NetworkOfferServiceList()
                            networkOfferServiceList.networkOffer = networkOffer
                            networkOfferServiceList.service = serviceData.name
                            networkOfferServiceList.provider = serviceData.serviceProvider 
                            networkOfferServiceList.created = new Date()
                            networkOfferServiceList.save(flush: true);
    //                        for(Iterator <CapabilityResponse> capabilityIter = serviceData.capabilities.iterator(); capabilityIter.hasNext();) {
    //                            def  capabilityData = capabilityIter.next();  
    //                            Console.print("Capabilityname:" + capabilityData.capabilityName);
    //                            Console.print("Capabilityvalue:" + capabilityData.capabilityValue);
    //                            Console.print("Capabilitychoose:" + capabilityData.canChooseServiceCapability);
    //                        }              
                        } 
                    } else if(networkOffer) {
                        NetworkOfferZoneCost networkOfferZoneCost = NetworkOfferZoneCost.findWhere(networkOffer: networkOffer, zone:zone)
                        if(!networkOfferZoneCost) {
                            networkOfferZoneCost = new NetworkOfferZoneCost()
                            networkOfferZoneCost.networkOffer = networkOffer
                            networkOfferZoneCost.cost = 0
                            networkOfferZoneCost.zone = zone
                            networkOfferZoneCost.save(flush: true)
                        }
                        
                    }
                }
                
                if(zone.networkType == "Basic") {
                    zoneService.addDefaultNetwork(new String(zone.referenceZoneId))
                } 
            }
         }catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
        
    }        
    
    def list(zoneReferenceId) {
          
        try {
                        
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()   

            def offerList
                        
            //            if(licenseValidationService.getProduct() == "FOG_BASIC") {
            //               offerList = [];
            //            } else if(licenseValidationService.getProduct() == "FOG_ADVANCED") {
               
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                
                offerList = NetworkOffer.withCriteria {
                    eq("deleted", false)
                }
                ArrayList<ArrayList<String>> networkOfferList = new ArrayList<ArrayList<String>>();
                for(def offer: offerList) {
                    
                    HashMap item = new HashMap();    
                    item.put("referenceId", offer.networkReferenceId);
                    item.put("id", offer.id);
                    item.put("name", offer.name);
                    item.put("description", offer.description);
                    item.put("networkRate", offer.networkRate);
                    
                    def zoneString = "";
                    def zoneCostList = NetworkOfferZoneCost.findAllByNetworkOffer(offer)
                    for(def zoneCost: zoneCostList) {
                        
                        zoneString += zoneString.size() == 0 ? zoneString : ",";
                        zoneString += zoneCost.zone.aliasName
                    }
                    item.put("zone", zoneString);
                    ArrayList<String> serviceArray = new ArrayList<String>();

                    def serviceList = NetworkOfferServiceList.findAllWhere(networkOffer:offer)
                    for(def service: serviceList) {
                        serviceArray.add(service.service) 
                    }                
                    item.put("service", serviceArray);
                    networkOfferList.add(item);      
               
                }        
                return networkOfferList;
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                offerList = NetworkOffer.withCriteria {
                    eq("guestIpType", "Isolated")
                    eq("deleted", false)
                    eq("specifyVlan", false)

                }
                    
                ArrayList<ArrayList<String>> networkOfferList = new ArrayList<ArrayList<String>>();

                HashMap<String,String> vpcOptional = new HashMap<String,String>();
                vpcOptional.put("forvpc", "false");  
                   
                def vpcNetworkOfferResponse = networkServer().listNetworkOfferings(vpcOptional);

                for(Iterator <NetworkOfferingResponse> iter = vpcNetworkOfferResponse.networkOfferings.iterator(); iter.hasNext();) {
                    def data = iter.next();
                    def zone = Zone.findByReferenceZoneId(zoneReferenceId)
                    NetworkOffer offer = NetworkOffer.findByNetworkReferenceId(data.networkOfferingId);
                    if(offer && NetworkOfferZoneCost.findWhere(networkOffer:offer, zone: zone)) {
                        HashMap item = new HashMap();    
                        item.put("referenceId", offer.networkReferenceId);
                        item.put("id", offer.id);
                        item.put("name", offer.name);
                        item.put("description", offer.description);
                        item.put("networkRate", offer.networkRate);

                        def zoneString = "";
                        def zoneCostList = NetworkOfferZoneCost.findAllByNetworkOffer(offer)
                        for(def zoneCost: zoneCostList) {

                            zoneString += zoneString.size() == 0 ? zoneString : ",";
                            zoneString += zoneCost.zone.aliasName
                        }
                        item.put("zone", zoneString);
                        ArrayList<String> serviceArray = new ArrayList<String>();

                        def serviceList = NetworkOfferServiceList.findAllWhere(networkOffer:offer)
                        for(def service: serviceList) {
                            serviceArray.add(service.service) 
                        }                
                        item.put("service", serviceArray);
                        networkOfferList.add(item);
                    }
                }

                return networkOfferList;
                    
                    
                //                    for(def offer: offerList) {
                //                            def zone = Zone.findByReferenceZoneId(zoneReferenceId)
                //                            if(NetworkOfferZoneCost.findWhere(networkOffer:offer, zone: zone)) {
                //                                HashMap item = new HashMap();    
                //                                item.put("referenceId", offer.networkReferenceId);
                //                                item.put("id", offer.id);
                //                                item.put("name", offer.name);
                //                                item.put("description", offer.description);
                //                                item.put("networkRate", offer.networkRate);
                //
                //                                def zoneString = "";
                //                                def zoneCostList = NetworkOfferZoneCost.findAllByNetworkOffer(offer)
                //                                for(def zoneCost: zoneCostList) {
                //
                //                                    zoneString += zoneString.size() == 0 ? zoneString : ",";
                //                                    zoneString += zoneCost.zone.aliasName
                //                                }
                //                                item.put("zone", zoneString);
                //                                ArrayList<String> serviceArray = new ArrayList<String>();
                //
                //                                def serviceList = NetworkOfferServiceList.findAllWhere(networkOffer:offer)
                //                                for(def service: serviceList) {
                //                                    serviceArray.add(service.service) 
                //                                }                
                //                                item.put("service", serviceArray);
                //                                networkOfferList.add(item);
                //                            }
                //                    }        
                //                    return networkOfferList;
            }   
            //            }
            
            
            //            return NetworkOffer.findAll("from NetworkOffer as networkOffer where networkOffer.deleted=?", [false]); 
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    
    def getNetworkOffer(id) {
        
        def networkOffer = NetworkOffer.get(id)
        
        ArrayList<ArrayList<String>> networkOfferList = new ArrayList<ArrayList<String>>();
        HashMap item = new HashMap();    
        item.put("referenceId", networkOffer.networkReferenceId);
        item.put("id", networkOffer.id);
        item.put("name", networkOffer.name);
        item.put("description", networkOffer.description);
        item.put("networkRate", networkOffer.networkRate);
        def zoneString = "";
        def zoneCostList = NetworkOfferZoneCost.findAllByNetworkOffer(networkOffer)
        for(def zoneCost: zoneCostList) {

            zoneString += zoneString.size() == 0 ? zoneString : ",";
            zoneString += zoneCost.zone.aliasName
        }
        item.put("zone", zoneString);
        ArrayList<String> serviceArray = new ArrayList<String>();
        def serviceList = NetworkOfferServiceList.findAllWhere(networkOffer:networkOffer)
        for(def service: serviceList) {
            serviceArray.add(service.service) 
        }                
        item.put("service", serviceArray);
        networkOfferList.add(item);
        return networkOfferList;
    }
              
    def update(NetworkOffer networkOffer, String name, String description){
        try{
            
            // Create a HashMap which stores optional values
            HashMap<String,String> optional = new HashMap<String,String>();

            // Adding optional values to the HashMap
            optional.put("name", name);
            optional.put("displaytext", description);
            optional.put("availability", "optional");
            optional.put("id", new String(networkOffer.networkReferenceId));                                 
            def response = networkServer().updateNetworkOffering(optional);           
            networkOffer.name = response.networkOfferingName
            networkOffer.description = response.networkOfferingDisplayText            
           
            //save networkOffer
            networkOffer.save(flush: true);
            if (networkOffer.hasErrors()) {
                throw new ValidationException(networkOffer.errors.allErrors);
            }            
        }catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    
    def create (NetworkOffer newNetworkOffer) {
        try {            
            List<String> list = new ArrayList<String>();            
            if(newNetworkOffer.vpn == true) {
                list.add("Vpn");
            }
            if(newNetworkOffer.dhcp == true) {
                list.add("dhcp");
            } 
            if(newNetworkOffer.dns == true) {
                list.add("dns");
            } 
            if(newNetworkOffer.firewall == true) {
                list.add("firewall");
            }  
            if(newNetworkOffer.staticNat == true) {
                list.add("staticNat");
            }
            if(newNetworkOffer.loadBalancer == true) {
                list.add("Lb");
            } 
            if(newNetworkOffer.userData == true) {
                list.add("userData");
            } 
            if(newNetworkOffer.sourceNat == true) {
                list.add("sourceNat");
            } 
            if(newNetworkOffer.portForwarding == true) {
                list.add("portForwarding");
            } 
            if(newNetworkOffer.securityGroups == true) {
                list.add("securityGroups");
            }
            
            String[] array = new String[list.size()];
            list.toArray(array);
            String separator = ",";
            String supportedService = "";
            if (array.length > 0) {
                supportedService = array[0];
                for (int i=1; i<array.length; i++) {
                    supportedService = supportedService + separator + array[i];
                }
            }
            
            // Create a HashMap which stores optional values
            HashMap<String,String> optional = new HashMap<String,String>();
            
            if(new String(newNetworkOffer.networkRate)== ""){
                optional.put("networkrate", "200");
            } else {
                optional.put("networkrate", new String(newNetworkOffer.networkRate));
            }              
            def response = networkServer().createNetworkOffering(newNetworkOffer.description, "Isolated", newNetworkOffer.name, supportedService, "GUEST", null);          
            newNetworkOffer.deleted = false;
            newNetworkOffer.orderNo = 1;
            newNetworkOffer.networkReferenceId = response.networkOfferingId
            
            //save NeworkOffer
            newNetworkOffer.save(flush: true);
            if (newNetworkOffer.hasErrors()) {
                throw new ValidationException(newNetworkOffer.errors.allErrors);
            }    
            ArrayList<ArrayList<String>> networkOfferList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> networkOffer = new HashMap<String,String>();                
            networkOffer.put("networkOffer", newNetworkOffer);
            networkOffer.put("result", "OK");
            networkOfferList.add(networkOffer);
            return networkOfferList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }     
    }
    
    def delete(String id) {
        try {
            def networkOffer = NetworkOffer.get(id)                               
            def response = networkServer().deleteNetworkOffering(networkOffer.networkReferenceId)            
            if(response.success == "true") {
                networkOffer.deleted = true;
                networkOffer.save(flush: true)                
                if (networkOffer.hasErrors()) {
                    throw new ValidationException(networkOffer.errors.allErrors);
                }
                return response.success
            } else {
                return response.success
            }       
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def getNetworkOfferForVpc(vpcId) {
        try {
                      
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            
            def network = Network.findAllWhere(tierType:"WEB",account:user.account, deleted:false, vpc:VPC.findByReferenceId(vpcId))
            ArrayList<ArrayList<String>> networkOfferList = new ArrayList<ArrayList<String>>();
            
            HashMap<String,String> vpcOptional = new HashMap<String,String>();
            vpcOptional.put("forvpc", "true");  
            vpcOptional.put("guestiptype", "Isolated");  
            vpcOptional.put("state", "Enabled");  
            vpcOptional.put("supportedServices", "SourceNat");  

            def vpcNetworkOfferResponse = networkServer().listNetworkOfferings(vpcOptional);
            
            for(Iterator <NetworkOfferingResponse> iter = vpcNetworkOfferResponse.networkOfferings.iterator(); iter.hasNext();) {
                def data = iter.next();

                NetworkOffer offer = NetworkOffer.findByNetworkReferenceId(data.networkOfferingId);
                if(offer) {
                    HashMap item = new HashMap();    
                    item.put("referenceId", offer.networkReferenceId);
                    item.put("id", offer.id);
                    item.put("name", offer.name);
                    item.put("description", offer.description);
                    item.put("networkRate", offer.networkRate);
                    
                    
                    def tierType = "";
                    
                    def web = NetworkOfferServiceList.withCriteria {
                        eq('networkOffer', offer)
                        'in'("service",["Lb"])
                    }
                    
                    def app = NetworkOfferServiceList.withCriteria {
                        eq('networkOffer', offer)
                        'in'("provider",["InternalLbVm"])
                    }
                                       
                    if(app) {
                        tierType = "APP" 
                    } else if(web) {
                        tierType = "WEB"
                    } else {
                        tierType = "DB"
                    }
                    
                    if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {   
                    
                        if(tierType == "WEB") {
                            if(!network) {
                                item.put("tierType", tierType);
                                item.put("networkRate", offer.networkRate);

                                networkOfferList.add(item);
                            }
                        } else {
                            item.put("tierType", tierType);
                            item.put("networkRate", offer.networkRate);

                            networkOfferList.add(item);
                        }
                    } else {
                        item.put("tierType", tierType);
                        item.put("networkRate", offer.networkRate);

                        networkOfferList.add(item);
                    }
                }
            }
            
            return networkOfferList;
             
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    def pullNetworkOfferFromCloudStackJobStart() {
        
        PullNetworkOfferingJob.triggerNow([id:"network_offering"])
            
        return "OK"
        
    }
    def pullNetworkOfferCloudStack(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
            
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            
            this.pullNetworkFromZone(null);
            
            
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        } catch (Exception ex) {
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
}

