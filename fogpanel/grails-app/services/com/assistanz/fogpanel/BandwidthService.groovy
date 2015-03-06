package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.virtualmachine.VirtualMachineResponse
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse
import com.assistanz.cloud.cloudstack.accounts.CSAccountService
import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import com.assistanz.cloud.cloudstack.CloudStackServer
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.transaction.Transactional

@Transactional
class BandwidthService {
    
    ZoneService zoneService
    def springSecurityService
    NotificationService notificationService
    
    def virtualMachineServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        return csVirtualMachineService;
    }

    def calc() {
        
        def virtualMachineList = VirtualMachine.findAllWhere(deleted: false); 

        def newCloudStackUpDate = zoneService.listAlerts();
        Config oldCloudStackUpDate =  Config.findByName(Config.CLOUD_STACK_URL_SERVER_UP_DATE);
        
        for(int i=0; i < virtualMachineList.size(); i++) { 
                def vm = virtualMachineList[i]; 
            
            try {
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("id", vm.referenceId);
                def response = virtualMachineServer().listVirtualMachines(optional);

                for(Iterator<VirtualMachineResponse> iter = response.virtualMachines.iterator(); iter.hasNext();) {
                    def data = iter.next();
                    VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId);
                    for(Iterator<NetworkInterfaceCardResponse> networkInterfaceCardIter = data.networkInterfaceCards.iterator(); networkInterfaceCardIter.hasNext();) {

                    def networkInterfaceCardData = networkInterfaceCardIter.next();
                        if(virtualMachine) {
                             virtualMachine.nicIp = networkInterfaceCardData.ipAddress;
                        }
                    }   
                    if((data.networkKbpsRead == "" || data.networkKbpsRead == "null" || data.networkKbpsRead == null) && (data.networkKbpsWrite == "" || data.networkKbpsWrite == "null" || data.networkKbpsWrite == null)) {
    //                        virtualMachine.networkRead == 0.0;
    //                        virtualMachine.networkWrite = 0.0;
                        } else {
                            if(newCloudStackUpDate == oldCloudStackUpDate.value) {

                                if(Double.parseDouble(data.networkKbpsRead) > virtualMachine.cloudPreviousNetworkRead) {
                                    double value = Double.parseDouble(data.networkKbpsRead) - virtualMachine.cloudPreviousNetworkRead
                                    virtualMachine.networkRead = value + virtualMachine.networkRead 
                                    virtualMachine.cloudPreviousNetworkRead = Double.parseDouble(data.networkKbpsRead)
                                } else if(Double.parseDouble(data.networkKbpsRead) < virtualMachine.cloudPreviousNetworkRead) {
                                    double value = virtualMachine.cloudPreviousNetworkRead - Double.parseDouble(data.networkKbpsRead)
                                    virtualMachine.networkRead = value + virtualMachine.networkRead  
                                    virtualMachine.cloudPreviousNetworkRead = Double.parseDouble(data.networkKbpsRead)
                                }
                                if(Double.parseDouble(data.networkKbpsWrite) > virtualMachine.cloudPreviousnetworkWrite) {
                                    double value = Double.parseDouble(data.networkKbpsWrite) - virtualMachine.cloudPreviousnetworkWrite
                                    virtualMachine.networkWrite = value + virtualMachine.networkWrite 
                                    virtualMachine.cloudPreviousnetworkWrite = Double.parseDouble(data.networkKbpsWrite)
                                } else if(Double.parseDouble(data.networkKbpsWrite) < virtualMachine.cloudPreviousnetworkWrite) {
                                    double value = virtualMachine.cloudPreviousnetworkWrite - Double.parseDouble(data.networkKbpsWrite)
                                    virtualMachine.networkWrite = value + virtualMachine.networkWrite  
                                    virtualMachine.cloudPreviousnetworkWrite = Double.parseDouble(data.networkKbpsWrite)
                                }

                            } else {
                                double value = Double.parseDouble(data.networkKbpsRead) - virtualMachine.cloudPreviousNetworkRead
                                virtualMachine.networkRead = virtualMachine.networkRead + value
                                virtualMachine.cloudPreviousNetworkRead = Double.parseDouble(data.networkKbpsRead)
                                double value2 = Double.parseDouble(data.networkKbpsWrite) - virtualMachine.cloudPreviousnetworkWrite
                                virtualMachine.networkWrite = value2 + virtualMachine.networkWrite  
                                virtualMachine.cloudPreviousnetworkWrite = Double.parseDouble(data.networkKbpsWrite)    
                            }
                        }            
                        virtualMachine.save(flush: true);  
                    }
            } catch( Exception e ) {
                    continue; // even if there is a processing error with a directory
            }
        }
                
        oldCloudStackUpDate.value = newCloudStackUpDate
        oldCloudStackUpDate.save(flush: true);
        Calendar date = Calendar.getInstance();
                
        def accountList = Account.findAllWhere(accountType: AccountType.values()[0]) 
        
        for(def account: accountList) {
            
            def vmCriteria = VirtualMachine.createCriteria()
            
            def vmList = vmCriteria.list {
                eq("owner", account)
                and {
                    eq("deleted", false)  
                }
                projections {
                    sum("networkWrite")
                }
            }
            
            if(vmList[0] == null || vmList[0] == "null") { 
                
            } else {
                
                double bandUsed = vmList[0] / 1048576
                                                             
                if(bandUsed >= Double.parseDouble(account.bandwidthLimit)) {
                    
                    if(account.status.name().toString() != "DISABLED") {
                        
                        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
                        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
                        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

                        CloudStackServer server =
                                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                        CSAccountService csAccService = new CSAccountService(server);
                        HashMap<String,String> optional = new HashMap<String,String>();
                        optional.put("account", new String(account.accountIdentifier));
                        optional.put("domainid", account.domain.referenceId);

                        def response = csAccService.disableAccount("false", optional)
                        account.status = Status.values()[3]
                        account.save(flush: true); 
                        def log = new Log()
                        log.account = account
                        log.user = User.findByUsername(account.accountIdentifier)
                        log.date = date.getTime()
                        log.type = LogType.values()[2]
                        log.mailSend = false
                        log.description = "Account " + account.accountIdentifier + " is Disabled due to bandwidth limit exceeded"


                        //send Mail Menthod
                        def mailtemplate;
                        def headerTemplate = MailTemplate.findByName("header");
                        def footerTemplate = MailTemplate.findByName("footer");
                        def finalMessage;
                        def logoConfig = Config.findByName("organisation.billing.logo")
                        def signature = Config.findByName("organisation.billing.signature");
                        def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
                        def hasHeader
                        def hasFooter                    

                        // Header Content
                        def logoUrl = ""
                        if(logoConfig.value == "") {                             
                            logoUrl = applicationUrl.toString() + "/images/fog_logo.png"
                        } else {
                            logoUrl =  logoConfig.value;
                        } 
                        def logoContent = '<img style="height: 30px; width: 100px"  src = '+logoUrl+' alt="progress" height="9" width="100">'
                        def headerContent = logoContent + headerTemplate.content;

                        // Footer content
                        def footerContent = footerTemplate.content;


                        mailtemplate = MailTemplate.findByName("accountDisabled")
                        hasHeader = mailtemplate.hasHeader
                        hasFooter = mailtemplate.hasFooter   
                        def userName = mailtemplate.content.replaceAll("\\[userName\\]", log.account.firstName)
                        def message = userName.replaceAll("\\[signature\\]", signature.value);   
                        if((hasHeader == true || hasHeader == 'true') && (hasFooter == true || hasFooter == 'true')) {
                            finalMessage = "<html><body>" + headerContent.toString() + message.toString() + footerContent.toString() + "</body></html>";
                        } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {
                            finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
                        } else if((hasHeader == false || hasHeader == 'false') && (hasFooter == true || hasFooter == 'true')) {                       
                            finalMessage = "<html><body>" + message.toString() + footerContent.toString() + "</body></html>";
                        } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {                       
                            finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
                        } else {
                            finalMessage = "<html><body>" + message.toString() + "</body></html>";
                        }
                        notificationService.send(log.account.email.toString(), mailtemplate.subject, finalMessage)  
                    
                    }

                }
            }
        }
        log.info("BandwidthCalc Cron Completed");
    }
    
    
    def count(zoneReferenceId) {
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        try {
            
            ArrayList<ArrayList<String>> band = new ArrayList<ArrayList<String>>();
            HashMap bandCostItem = new HashMap();

            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()    
            if(role.iterator().next() == "ROLE_ADMIN" ) {

                def invoiceList = Invoice.findAllWhere(status: InvoiceStatus.values()[6])

                def totalExBandwidth = 0;
                def totalBandwidth = 0;
                def totalBandwidthCost = 0;

                for(def invoice: invoiceList) {
                    def bandwidthCostCriteria = InvoiceItem.createCriteria()
                    def bandCost = InvoiceItem.withCriteria {
                        if(zoneReferenceId != "ALL") {
                            eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                        }
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", BillableItem.get(5))
                        }
                    }
                    for(def bandItem: bandCost) {
                        totalExBandwidth += bandItem.usageUnits
                        totalBandwidthCost += bandItem.totalAmount
                    } 

                    def currentVmList = InvoiceItem.withCriteria {
                        if(zoneReferenceId != "ALL") {
                            eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                        }
                        eq("invoice", invoice)
                        and {
                            eq("billableItem",BillableItem.get(1))
                        }
                    }
                    
                    HashMap<String,String> planList = new HashMap<String,String>(); 
    //                 double readGB = virtualMachine.networkRead / 1048576
                    for(def currentVm: currentVmList) {

                        def computingOffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
                        def virtualMachine = VirtualMachine.findWhere(referenceId: currentVm.referenceItemId, computingOffer: computingOffer);

                        if(virtualMachine) {
                            def totalVmb = 0
                            boolean blnExists = planList.containsKey(currentVm.referenceItemId);
                            if(blnExists.toString() == "false" || blnExists == false) {
                                double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth

                                def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :virtualMachine.zone).cost;

                                def usageUnits = (virtualMachine.networkWrite / 1048576) - planBandwidth
                                totalVmb = virtualMachine.networkWrite / 1048576
                                if(totalVmb > planBandwidth) {
                                    double usageAmount = usageUnits * bandWidthCost                          
                                    totalBandwidthCost = totalBandwidthCost + usageAmount
                                    totalExBandwidth = totalExBandwidth + usageUnits
                                }
                                totalBandwidth += totalVmb;
                            }
                            planList.put(currentVm.referenceItemId ,"referenceId");
                        }   
                    }
                }    
                bandCostItem.put("totalExBandwidth", totalExBandwidth); 
                bandCostItem.put("totalBandwidth", totalBandwidth); 
                bandCostItem.put("totalBandwidthCost", totalBandwidthCost); 
                bandCostItem.put("currency", currency); 
                bandCostItem.put("bandLimit", user.account.bandwidthLimit); 
                bandCostItem.put("accountType", user.account.accountType.name()); 
                
                
                def vmList = VirtualMachine.withCriteria {
                    if(zoneReferenceId != "ALL") {
                        eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                    }
                     eq("deleted", false)
                }
                
                
                ArrayList<ArrayList<String>> vmBand = new ArrayList<ArrayList<String>>();
                
                
                for(def vm: vmList) {
                                                            
                    double planBandwidth = 1.0 * vm.computingOffer.bandWidth 
                    
                    def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :vm.zone).cost; 
                    
                    def currentTotalBandwidthCost = 0
                    def currentTotalExBandwidth = 0
                    
                    def usageUnits = (vm.networkWrite / 1048576) - planBandwidth
                    def totalVmBand = vm.networkWrite / 1048576
                    if(totalVmBand > planBandwidth) {
                        double usageAmount = usageUnits * bandWidthCost                          
                        currentTotalBandwidthCost =  usageAmount
                        currentTotalExBandwidth = usageUnits
                    } 
                    
                    HashMap<String,String> vmBandCostItem = new HashMap<String,String>();
                    vmBandCostItem.put("vmId", vm.referenceId)
                    vmBandCostItem.put("account", vm.owner.accountIdentifier)
                    vmBandCostItem.put("vmName", vm.displayName)
                    vmBandCostItem.put("exBand", currentTotalExBandwidth)
                    vmBandCostItem.put("totalBand", vm.networkWrite / 1048576)
                    vmBandCostItem.put("bandCost", currentTotalBandwidthCost)
                    vmBand.add(vmBandCostItem) 
                }
                
                bandCostItem.put("vmBandItems", vmBand)
                
                band.add(bandCostItem)   

                return band
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                
                def invoiceList = Invoice.findAllWhere(status: InvoiceStatus.values()[6], account: user.account)

                def totalExBandwidth = 0;
                def totalBandwidth = 0;
                def totalBandwidthCost = 0;

                for(def invoice: invoiceList) {
                    def bandwidthCostCriteria = InvoiceItem.createCriteria()
                    def bandCost = InvoiceItem.withCriteria {
                        if(zoneReferenceId != "ALL") {
                            eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                        }
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", BillableItem.get(5))
                        }
                    }
                    for(def bandItem: bandCost) {
                        totalExBandwidth += bandItem.usageUnits
                        totalBandwidthCost += bandItem.totalAmount
                    } 

                    def currentVmList = InvoiceItem.withCriteria {
                        if(zoneReferenceId != "ALL") {
                            eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                        }
                        eq("invoice", invoice)
                        and {
                            eq("billableItem",BillableItem.get(1))
                        }
                    }
                    
                    HashMap<String,String> planList = new HashMap<String,String>(); 
    //                 double readGB = virtualMachine.networkRead / 1048576
                    for(def currentVm: currentVmList) {

                        def computingOffer = ComputingOffer.findByOfferReferenceId(currentVm.referencePlanId)
                        def virtualMachine = VirtualMachine.findWhere(referenceId: currentVm.referenceItemId, computingOffer: computingOffer);

                        if(virtualMachine) {
                            def totalVmb = 0
                            boolean blnExists = planList.containsKey(currentVm.referenceItemId);
                            if(blnExists.toString() == "false" || blnExists == false) {
                                double planBandwidth = 1.0 * virtualMachine.computingOffer.bandWidth

                                def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :virtualMachine.zone).cost;

                                def usageUnits = (virtualMachine.networkWrite / 1048576) - planBandwidth
                                totalVmb = virtualMachine.networkWrite / 1048576
                                if(totalVmb > planBandwidth) {  
                                    double usageAmount = usageUnits * bandWidthCost                          
                                    totalBandwidthCost = totalBandwidthCost + usageAmount
                                    totalExBandwidth = totalExBandwidth + usageUnits
                                }
                                totalBandwidth += totalVmb;
                            }
                            planList.put(currentVm.referenceItemId ,"referenceId");
                        }   
                    }
                }    
                bandCostItem.put("totalExBandwidth", totalExBandwidth); 
                bandCostItem.put("totalBandwidth", totalBandwidth); 
                bandCostItem.put("totalBandwidthCost", totalBandwidthCost); 
                bandCostItem.put("currency", currency); 
                bandCostItem.put("bandLimit", user.account.bandwidthLimit); 
                bandCostItem.put("accountType", user.account.accountType.name()); 
                                
                def vmList = VirtualMachine.withCriteria {
                    if(zoneReferenceId != "ALL") {
                        eq("zone", Zone.findByReferenceZoneId(zoneReferenceId))
                    }
                    and {
                        eq("deleted", false)
                    } 
                    eq("owner", user.account)
                   
                }
                ArrayList<ArrayList<String>> vmBand = new ArrayList<ArrayList<String>>();
                
                
                for(def vm: vmList) {
                                                            
                    double planBandwidth = 1.0 * vm.computingOffer.bandWidth 
                    
                    def bandWidthCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :MiscellaneousOffer.get(1), zone :vm.zone).cost; 
                    
                    def currentTotalBandwidthCost = 0
                    def currentTotalExBandwidth = 0
                    
                    def usageUnits = (vm.networkWrite / 1048576) - planBandwidth
                    def totalVmBand = vm.networkWrite / 1048576
                    if(totalVmBand > planBandwidth) {
                        double usageAmount = usageUnits * bandWidthCost                          
                        currentTotalBandwidthCost =  usageAmount
                        currentTotalExBandwidth = usageUnits
                    } 
                                    
                    HashMap<String,String> vmBandCostItem = new HashMap<String,String>();
                    vmBandCostItem.put("vmId", vm.referenceId)
                    vmBandCostItem.put("account", vm.owner.accountIdentifier)
                    vmBandCostItem.put("vmName", vm.displayName)
                    vmBandCostItem.put("exBand", currentTotalExBandwidth)
                    vmBandCostItem.put("totalBand", vm.networkWrite / 1048576)
                    vmBandCostItem.put("bandCost", currentTotalBandwidthCost)
                    vmBand.add(vmBandCostItem) 
                }
                
                bandCostItem.put("vmBandItems", vmBand)
                
                band.add(bandCostItem)   

                return band
            }
            
            
        }  catch (Exception ex){
            throw ex
        }
    }
}
