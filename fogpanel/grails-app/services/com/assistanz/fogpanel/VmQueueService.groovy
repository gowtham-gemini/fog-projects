package com.assistanz.fogpanel

import org.springframework.amqp.core.Message;
import grails.converters.deep.JSON
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.text.DateFormat
import javax.xml.bind.DatatypeConverter;
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import com.assistanz.cloud.cloudstack.virtualmachine.VirtualMachineResponse
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse
import com.assistanz.fogpanel.GeneralConstants;
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;

class VmQueueService {

    VirtualMachineService virtualMachineService
    VolumeService volumeService
    NicService nicService
    private static final log = LogFactory.getLog(this)
    
    def virtualMachineServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSVirtualMachineService csVirtualMachineService = new CSVirtualMachineService(server);
        
        return csVirtualMachineService;
    }
  
    static rabbitQueue = [queues: 'cloudstack-events-vm-all']

    void handleMessage(msg) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
            // Do something with the message headers
    //        println "Received message with content type ${msg.contentType};${msg.encoding}"
//            println "-- MESSAGE BEGINS --"
//            println msg
//            println "-- MESSAGE ENDS --"
        
        def msgData = JSON.parse(msg)      

        VirtualMachine vm = VirtualMachine.findByReferenceId(msgData.id);
        if (!vm) {

        } else {
            if(vm.firstRun == true && msgData.'new-state' == "Running") {
                
                Date date = Calendar.getInstance().getTime()
                def currentTime = new Timestamp(date.getTime())
                
                Calendar c = Calendar.getInstance();
                //get billing type from config
                Config billingTypeFixedEnabled =  Config.findByName(Config.BILLING_CYCLE_FIXED_ENABLED);
                Config dueDays =  Config.findByName(Config.PAYMENT_DUE_DAYS);
                def invoiceDate;
                if(billingTypeFixedEnabled.value == "true" || billingTypeFixedEnabled.value == true) {
                    Config billingPeriodfixedDay =  Config.findByName(Config.BILLING_PERIOD_FIXED_DAY);
                    c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value));   
                    if(date.getDate() >= Integer.parseInt(billingPeriodfixedDay.value)) {
                        c.add(Calendar.MONTH, 1); 
                        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(billingPeriodfixedDay.value)); 
                        invoiceDate = c.getTime()
                    } else {
                        invoiceDate = c.getTime()
                    }

                } else if(billingTypeFixedEnabled.value == "false" || billingTypeFixedEnabled.value == false) {
                    Config billingPeriodDays =  Config.findByName(Config.BILLING_PERIOD_DAYS);
                    // Adding days
                    c.add(Calendar.DATE, Integer.parseInt(billingPeriodDays.value));
                    invoiceDate = c.getTime()
                }
                def discountList;
                def discountCriteria = Discount.createCriteria()
                DateFormat formater2 = new SimpleDateFormat("dd/MM/yyyy");
                Date curDate = Calendar.getInstance().getTime()
                def today = formater2.format(curDate)
                Date currentDate = formater2.parse(today);
                def account = vm.owner
                 def computingBillableItem
                if(vm.billingType == "monthly") { 
                    computingBillableItem= BillableItem.get(13) 
                } else {
                    computingBillableItem= BillableItem.get(1) 
                }
                def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                if(!invoice) {

                    account.nextBillingData = invoiceDate
                    account.lastBillingData = currentTime   
                    account.save(flush: true); 
                    invoice = new Invoice()
                    invoice.status = InvoiceStatus.values()[6]
                    invoice.account = account
                    invoice.referenceNumber = account.id + date.toString()
                    invoice.date = invoiceDate
                    invoice.previousInvoiceDate = currentTime
                    invoice.currency = ApplicationHolder.getApplication().config.billing.defaultCurrency

                    Calendar due = Calendar.getInstance();

                    Date invoiceDateString = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
                          .parse(invoiceDate.toString());

                    due.setTime(invoiceDateString)
                    due.add(Calendar.DATE, Integer.parseInt(dueDays.value));

    //                invoice.dueDate = due.getTime()
                    invoice.customerName = account.firstName
                    def address1 = new Address()
                    address1.street = account.streetAddress
                    address1.state = account.state.stateName
                    address1.country = account.country.countryName
                    address1.city = account.city
                    address1.zip = account.zip
                    invoice.billingAddress = address1
                    def address = new Address()
                    address.street = Config.findByName(Config.ORGANISATION_ADDRESS).value
                    address.state = Config.findByName(Config.ORGANISATION_ADDRESS_STATE).value
                    address.country = Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY).value
                    address.city = Config.findByName(Config.ORGANISATION_ADDRESS_CITY).value
                    address.zip = Config.findByName(Config.ORGANISATION_ADDRESS_ZIP).value             
                    invoice.organisationAddress = address                    
                    invoice.save(flush: true); 
                    log.info("Invoice generated for account: ${account.id} on create vm ${account.id}") 
                    if (invoice.hasErrors()) {
                        throw new ValidationException(invoice.errors.allErrors);
                    }
                    if(computingBillableItem.enabled == true || computingBillableItem.enabled == "true") {
                        def invoiceItem = new InvoiceItem()
                        invoiceItem.billableItem = computingBillableItem
                        invoiceItem.invoice = invoice
                        invoiceItem.zone = vm.zone
                        invoiceItem.referencePlanId = vm.computingOffer.offerReferenceId
                        invoiceItem.taxPercent = computingBillableItem.tax.percentage
                        if(computingBillableItem.discountable == true || computingBillableItem.discountable == "true") {
                            discountList = discountCriteria.list {
                                eq("subType", "CREATE_VM")
                                eq("type", DiscountType.values()[1])
                                and{
                                  le("startDate", currentDate) and { ge("endDate", currentDate)  } 
                                  and{
                                    eq("deleted", false)  
                                  }
                                }
                            }
                            if(discountList.size() == 0) {

                            } else {
                                for(int i=0; i < discountList.size(); i++) {
                                    def discount = discountList[i]; 
                                    if(discount.isAll == true || discount.isAll == "true") {
                                        invoiceItem.discount = discount;
                                        invoiceItem.discountPercent = discount.value;
                                        break
                                    } else {
                                        if((discount.isAllPlan == false || discount.isAllPlan == "false") && (discount.isAllUser == true || discount.isAllUser == "true"))  {
                                            for(Iterator m = discount.computingOffers.iterator();m.hasNext();) { 
                                                def discountComputerOffer = m.next();
                                                if(discountComputerOffer.offerReferenceId == vm.computingOffer.offerReferenceId) {
                                                    invoiceItem.discount = discount;
                                                    invoiceItem.discountPercent = discount.value;
                                                    break 
                                                }
                                            } 
                                            
                                        } else if((discount.isAllPlan == true || discount.isAllPlan == "true") && (discount.isAllUser == false || discount.isAllUser == "false"))  {
                                            for(Iterator m = discount.accounts.iterator();m.hasNext();) { 
                                                def discountAccount = m.next();
                                                if(discountAccount.id.toString() == invoice.account.id.toString()) {
                                                    invoiceItem.discount = discount;
                                                    invoiceItem.discountPercent = discount.value;
                                                    break 
                                                }
                                            } 
                                        } else {
                                            for(Iterator m = discount.accounts.iterator();m.hasNext();) { 
                                                def discountAccount = m.next();
                                                if(discountAccount.id.toString() == invoice.account.id.toString()) {
                                                    for(Iterator u = discount.computingOffers.iterator();u.hasNext();) { 
                                                        def discountComputerOffer = u.next(); 
                                                        if(discountComputerOffer.offerReferenceId == vm.computingOffer.offerReferenceId) {
                                                            invoiceItem.discount = discount;
                                                            invoiceItem.discountPercent = discount.value;
                                                            break 
                                                        }
                                                    }
                                                }
                                            } 
                                        }
                                    }                   

                                }
                            }
                        }
                        if(vm.billingType == "monthly") {
                                                    
                            double monthlyAmount = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone: vm.zone).cost * 720.00
                            
                            invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                            invoiceItem.usageUnits = 1.0
                            invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                            double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                            invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   
                            
                            double disAmount = (invoiceItem.usageAmount/100)* invoiceItem.discountPercent
                            invoiceItem.discountAmount = Math.ceil(disAmount * 100d) / 100d;   
                            
                            double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount) - invoiceItem.discountAmount
                            
                            invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                            invoiceItem.referenceItemName = "VirtualMachine"
                            invoiceItem.referenceItemId = vm.referenceId
                            invoiceItem.save(flush: true);
                            log.info("Invoice Item added for vm: ${vm.id}") 
                            
                        } else {
                            invoiceItem.usageUnitPrice = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone: vm.zone).cost
                            invoiceItem.referenceItemName = "VirtualMachine"
                            invoiceItem.referenceItemId = vm.referenceId                            
                            invoiceItem.save(flush: true); 
                            log.info("Invoice Item added for vm: ${vm.id}") 
                        }
                    }
//                    def discountBillableItem = BillableItem.get(6)
//                    if(discountBillableItem.enabled == true || discountBillableItem.enabled == "true") {
//                        def userDiscountCriteria = Discount.createCriteria()
//                        def userDiscountList = userDiscountCriteria.list {
//                            eq("subType", "CREATE_VM")
//                            eq("type", DiscountType.values()[0])
//                            and{
//                              le("startDate", currentDate) and { ge("endDate", currentDate)  } 
//                              and{
//                                eq("deleted", false)  
//                              }
//                            }
//                        }
//                        
//                        for(def userDiscount : userDiscountList) {
//                            
//                            if(userDiscount.isAll == true) {
//                                def invoiceItem = new InvoiceItem()
//                                invoiceItem.billableItem = discountBillableItem
//                                invoiceItem.invoice = invoice
//                                invoiceItem.taxPercent = 0.0
//                                invoiceItem.discount = userDiscount
////                                invoiceItem.discountPercent = userDiscount.value
//                                invoiceItem.usageUnitPrice = userDiscount.value
//                                
//                                invoiceItem.referenceItemId = userDiscount.id.toString()
//                                invoiceItem.referenceItemName = userDiscount.discountName
//                                invoiceItem.save()
//                                log.info("Discount:${userDiscount.id} added for account: ${account.id}") 
//                                break
//                            } else {
//                                for(Iterator m = userDiscount.accounts.iterator();m.hasNext();) { 
//                                    def discountAccount = m.next();
//                                    if(discountAccount.id == invoice.account.id) {
//                                        def invoiceItem = new InvoiceItem()
//                                        invoiceItem.billableItem = discountBillableItem
//                                        invoiceItem.invoice = invoice
//                                        invoiceItem.taxPercent = 0.0
//                                        invoiceItem.discount = userDiscount
//        //                                invoiceItem.discountPercent = userDiscount.value
//                                        invoiceItem.usageUnitPrice = userDiscount.value
//
//                                        invoiceItem.referenceItemId = userDiscount.id.toString()
//                                        invoiceItem.referenceItemName = userDiscount.discountName
//                                        invoiceItem.save()
//                                        log.info("Discount:${userDiscount.id} added for account: ${account.id}") 
//                                        break
//                                    }
//                                                
//                                }
//                            }
//                        } 
//                    }
                    
                    def setupCost = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone: vm.zone).setupCost
                    if(setupCost != 0) {
                        def setupCostInvoiceItem = virtualMachineService.addSetupCost(invoice, vm)
                        if(setupCostInvoiceItem != null || setupCostInvoiceItem != "null") {
                            setupCostInvoiceItem.save(flush: true); 
                            log.info("Setup cost for instance :${vm.id} for account: ${account.id}") 
                            if (!setupCostInvoiceItem.save()) {
            //                  setupCostInvoiceItem.errors.allErrors.each { Console.print(it) }
                            }
                        } 
                    }
                    if(vm.template.cost != 0) {
                        def templateInvoiceItem = virtualMachineService.addTemplateCost(invoice, vm)
                        templateInvoiceItem.save(flush: true); 
                        log.info("Template cost for instance :${vm.id} for account: ${account.id}") 
                        if (!templateInvoiceItem.save()) {
        //                  setupCostInvoiceItem.errors.allErrors.each { Console.print(it) }
                        }
                    }

    //                HashMap<String,String> optional2 = new HashMap<String,String>();
    //                optional2.put("type", "DATADISK");
    //                optional2.put("virtualmachineid", newVirtualMachine.referenceId);
    //                def volumeResponse = volumeServer().listVolumes(optional); 
    //                for(Iterator<VolumeResponse> volumeIter = volumeResponse.volumes.iterator(); volumeIter.hasNext();) {
    //                   def volumeData = volumeIter.next();
    //                   def invoiceDiskItem = new InvoiceItem()
    //                   invoiceDiskItem.billableItem = BillableItem.findByReferenceItemName("diskOffer")
    //                   invoiceDiskItem.invoice = invoice
    //                   invoiceDiskItem.usageUnitPrice = DiskOfferZoneCost.findWhere(diskOffer: newVirtualMachine.diskOffer).cost
    //                   invoiceDiskItem.referenceItemName = "Volume"
    //                   invoiceDiskItem.referenceItemId = volumeData.diskVolumeId
    //                   invoiceDiskItem.save(flush: true);              
    //               }

                } else if(invoice) {
                    if(computingBillableItem.enabled == true || computingBillableItem.enabled == "true") {
                            def invoiceItem = new InvoiceItem()
                            invoiceItem.billableItem = computingBillableItem
                            invoiceItem.invoice = invoice
                            invoiceItem.zone = vm.zone
                            invoiceItem.taxPercent = computingBillableItem.tax.percentage
                            invoiceItem.referencePlanId = vm.computingOffer.offerReferenceId
                            if(computingBillableItem.discountable == true || computingBillableItem.discountable == "true") {
                                discountList = discountCriteria.list {
                                    eq("subType", "CREATE_VM")
                                    eq("type", DiscountType.values()[1])
                                    and{
                                      le("startDate", currentDate) and { ge("endDate", currentDate)  } 
                                      and{
                                        eq("deleted", false)  
                                      }
                                    }
                                }
                                if(discountList.size() == 0) {

                                } else {
                                    for(int i=0; i < discountList.size(); i++) {
                                        def discount = discountList[i]; 
                                        if(discount.isAll == true || discount.isAll == "true") {
                                            invoiceItem.discount = discount;
                                            invoiceItem.discountPercent = discount.value;
                                            break
                                        } else {
                                            if((discount.isAllPlan == false || discount.isAllPlan == "false") && (discount.isAllUser == true || discount.isAllUser == "true"))  {
                                                for(Iterator m = discount.computingOffers.iterator();m.hasNext();) { 
                                                    def discountComputerOffer = m.next();
                                                    if(discountComputerOffer.offerReferenceId == vm.computingOffer.offerReferenceId) {
                                                        invoiceItem.discount = discount;
                                                        invoiceItem.discountPercent = discount.value;
                                                        break 
                                                    }
                                                } 

                                            } else if((discount.isAllPlan == true || discount.isAllPlan == "true") && (discount.isAllUser == false || discount.isAllUser == "false"))  {
                                                for(Iterator m = discount.accounts.iterator();m.hasNext();) { 
                                                    def discountAccount = m.next();
                                                    if(discountAccount.id.toString() == invoice.account.id.toString()) {
                                                        invoiceItem.discount = discount;
                                                        invoiceItem.discountPercent = discount.value;
                                                        break 
                                                    }
                                                } 
                                            } else {
                                                for(Iterator m = discount.accounts.iterator();m.hasNext();) { 
                                                    def discountAccount = m.next();
                                                    if(discountAccount.id.toString() == invoice.account.id.toString()) {
                                                        for(Iterator u = discount.computingOffers.iterator();u.hasNext();) { 
                                                            def discountComputerOffer = u.next(); 
                                                            if(discountComputerOffer.offerReferenceId == vm.computingOffer.offerReferenceId) {
                                                                invoiceItem.discount = discount;
                                                                invoiceItem.discountPercent = discount.value;
                                                                break 
                                                            }
                                                        }
                                                    }
                                                } 
                                            }
                                        }                   

                                    }
                                }
                            }
                            if(vm.billingType == "monthly") {
                                
                                double monthlyAmount = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone: vm.zone).cost * 720.00
                            
                                invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                                invoiceItem.usageUnits = 1.0
                                invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                                double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                                invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                                double disAmount = (invoiceItem.usageAmount/100)* invoiceItem.discountPercent
                                invoiceItem.discountAmount = Math.ceil(disAmount * 100d) / 100d;   

                                double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount) - invoiceItem.discountAmount

                                invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                                invoiceItem.referenceItemName = "VirtualMachine"
                                invoiceItem.referenceItemId = vm.referenceId
                                invoiceItem.save(flush: true);
                                log.info("Invoice Item added for vm: ${vm.id}")  
                            
                            } else {
                                invoiceItem.usageUnitPrice = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone: vm.zone).cost
                                invoiceItem.referenceItemName = "VirtualMachine"
                                invoiceItem.referenceItemId = vm.referenceId
                                invoiceItem.save(flush: true); 
                                log.info("Invoice Item added for vm: ${vm.id}")  
                            }
                    }
                    def setupCost = ComputingOfferZoneCost.findWhere(computingOffer: vm.computingOffer, zone: vm.zone).setupCost
                    if(setupCost != 0) {
                        def setupCostInvoiceItem = virtualMachineService.addSetupCost(invoice, vm)
                        if(setupCostInvoiceItem != null || setupCostInvoiceItem != "null") {
                            setupCostInvoiceItem.save(flush: true); 
                            log.info("Setup cost for instance :${vm.id} for account: ${account.id}") 
                            if (!setupCostInvoiceItem.save()) {
            //                  setupCostInvoiceItem.errors.allErrors.each { Console.print(it) }
                            }
                        }
                    }
                    if(vm.template.cost != 0) {
                        def templateInvoiceItem = virtualMachineService.addTemplateCost(invoice, vm)
                        if(templateInvoiceItem != null || templateInvoiceItem != "null") {
                            templateInvoiceItem.save(flush: true); 
                            log.info("Template cost for instance :${vm.id} for account: ${account.id}") 
                            if (!templateInvoiceItem.save()) {
            //                  setupCostInvoiceItem.errors.allErrors.each { Console.print(it) }
                            }
                        }
                    }
                } 
                
//                def jobResponse = virtualMachineServer().virtualMachineJobResult(vm.job);  
//                if(jobResponse.password.toString() != "null") {
////                    Console.print("password" + jobResponse.password)
//                  vm.password = jobResponse.password;  
//                }
                vm.state = msgData.'new-state'; 
                vm.firstRun = false;
                vm.save(flush: true);                
                volumeService.createVMDiskList()
                nicService.listNic(vm);
                virtualMachineService.sendVmMail(vm);    
//                if(vm.zone.networkType == GeneralConstants.ZONE_TYPE_ADVANCE) {
//                }                
            } else {
                vm = VirtualMachine.findByReferenceId(vm.referenceId);
                if(msgData.'new-state' != msgData.'old-state') {
                    HashMap<String,String> optional = new HashMap<String,String>();
                    optional.put("id", vm.referenceId); 

                    def response = virtualMachineServer().listVirtualMachines(optional);    
                    for(Iterator<VirtualMachineResponse> iter = response.virtualMachines.iterator(); iter.hasNext();) {
                        def data = iter.next();
                        for(Iterator<NetworkInterfaceCardResponse> networkInterfaceCardIter = data.networkInterfaceCards.iterator(); networkInterfaceCardIter.hasNext();) {
                            def networkInterfaceCardData = networkInterfaceCardIter.next();
                            vm.nicIp = networkInterfaceCardData.ipAddress;
                        }  
                        Iso iso = Iso.findByIsoReferenceId(data.isoId);
                        if(iso) {
                            vm.iso = iso;
                        } else {
                            vm.iso = null;
                        }
                    } 
                    if(msgData.'new-state' == GeneralConstants.VM_EXPUNGING_STATE) {
                        vm.deleted = true;
                        def volume = Volume.findWhere(virtualMachine:vm, type:"ROOT");
                        if(volume) {
                            volume.deleted = true;                            
                            volume.save(flush: true); 
                            log.info("Volume: ${volume.id} deleted") 
                        }
                        
                        
                        def userIPAddressList = UserIPAddress.findAllWhere(virtualMachine: vm, removed:false)
                        for (def userIPAddress: userIPAddressList) {

                            userIPAddress.virtualMachine = null
                            userIPAddress.network = null
                            userIPAddress.account = null
                            userIPAddress.user = null
                            userIPAddress.staticNatVirtualMachine = null
                            userIPAddress.isSourceNat =  false
                            userIPAddress.isStaticNat = false
                            userIPAddress.removed = true
                            userIPAddress.state = "Free"
                            userIPAddress.revokeDate = new Date()
                            userIPAddress.save(flush: true);
                            
                            log.info("IP removed :${userIPAddress.publicIPAddress}") 
                        } 
                        
                        def vmSnaplist = VMSnapshot.findAllWhere(virtualMachine: vm) 
                        for(def vmSnap : vmSnaplist) {
                            vmSnap.deleted = true                            
                            vmSnap.save(flush:true);
                            log.info("VM snapsot deleted for instance :${vm.id}") 
                        }                                
                        def niclist = Nic.findAllWhere(virtualMachine: vm) 
                        for(def nic : niclist) {
                            nic.deleted = true      
                            nic.removedDate = new Date()     
                            nic.save(flush:true);
                            log.info("Nic deleted for instance :${nic.id}") 
                        }                           
                    }
                    vm.state = msgData.'new-state';                     
                    vm.save(flush: true);
                }  
            }
        }
    }
}
