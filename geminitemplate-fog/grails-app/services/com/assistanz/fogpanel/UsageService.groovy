package com.assistanz.fogpanel

import java.text.SimpleDateFormat
import javax.xml.bind.DatatypeConverter;
import java.sql.Timestamp
import java.text.DateFormat
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;
import com.assistanz.cloud.config.ConfigLoader;
import com.assistanz.openstack.OpenStackServer;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.telemetry.Meter;
import org.openstack4j.model.telemetry.Sample;
import org.openstack4j.model.telemetry.SampleCriteria;
import org.openstack4j.model.telemetry.Statistics;
    
class UsageService {
    
    
    static lastUsageDate = new Date();
    static usageCount = 0;
    
    InvoiceService invoiceService
    NotificationService notificationService
    ConfigService configService;
    private static final log = LogFactory.getLog(this)
    private static final serviceList = ["instance", "volume", "network", "snapshot", "network.incoming.bytes", "network.outgoing.bytes", "ip.floating", "image"]        

    SimpleDateFormat usageDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
    
    def usageCount() {
          
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        def dateFormatValue = configService.getDateFormat();    
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        ArrayList<ArrayList<String>> usageList = new ArrayList<ArrayList<String>>();
        HashMap usageItem = new HashMap();
        
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.add(Calendar.DATE, -1); 
        startDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        startDateCalendar.set(Calendar.MINUTE, 00);
        startDateCalendar.set(Calendar.SECOND, 00);
        startDateCalendar.set(Calendar.MILLISECOND, 00);   

        Calendar toDateCalendar = Calendar.getInstance();
        toDateCalendar.add(Calendar.DATE, -1);
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);          
       
        Date fromDate = startDateCalendar.getTime()
        Date toDate = toDateCalendar.getTime()
        usageItem.put("currency",  currency);
        
        def daily =null
        def dailyCost;
        if(daily == "null" ||daily == null) {
            usageItem.put("daily",  + 0);
            dailyCost = 0
        } else {
            usageItem.put("daily",   Math.ceil(daily * 100d) / 100d);        
            dailyCost = Math.ceil(daily * 100d) / 100d
        } 
        
        //        def dailyUsageCostCriteria = DailyUsageCost.createCriteria()
        //        def dailyTable = dailyUsageCostCriteria.get { 
        //            ge("date", fromDate) and { le("date", toDate) } 
        //        }
        //
        //        if(!dailyTable) {
        //            def dailyUsageCost = new DailyUsageCost()
        //            dailyUsageCost.cost = dailyCost
        //            dailyUsageCost.date = fromDate
        //            dailyUsageCost.save(flush: true);
        //        } else {
        //            dailyTable.cost = dailyCost
        //            dailyTable.save(flush: true);
        //        }
        usageItem.put("dailyDate", dateFormat.format(fromDate).toString());
                        
        Calendar currMonDateCalendar = Calendar.getInstance();
        currMonDateCalendar.set(Calendar.DATE, 1) 
        currMonDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        currMonDateCalendar.set(Calendar.MINUTE, 00);
        currMonDateCalendar.set(Calendar.SECOND, 00);
        currMonDateCalendar.set(Calendar.MILLISECOND, 00);  
        Date curMonStartDate = currMonDateCalendar.getTime()

        Calendar currDateCalendar = Calendar.getInstance();
        currDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        currDateCalendar.set(Calendar.MINUTE, 59);
        currDateCalendar.set(Calendar.SECOND, 59);
        currDateCalendar.set(Calendar.MILLISECOND, 999);           
        
        Date curMonToDate = currDateCalendar.getTime()
        def InvoiceCriteria = Invoice.createCriteria()
        def curMonth = InvoiceCriteria.list { 
            ge("date", curMonStartDate) and { le("date", curMonToDate) } 
            projections {
                sum("totalAmount")
            }
        }
        
        if(curMonth[0] == "null" ||curMonth[0] == null) {
            usageItem.put("currMonthCost",  0);
        } else {
            usageItem.put("currMonthCost",  Math.ceil(curMonth[0] * 100d) / 100d);  
        } 
        
        usageItem.put("currMonth", dateFormat.format(curMonStartDate).toString() + " - " + dateFormat.format(curMonToDate).toString());
                
        def currentMonthPaymentsCriteriaAmount = Payments.createCriteria()
        def currentMonthPaymentAmount = currentMonthPaymentsCriteriaAmount.list {
            ge("paymentDate", curMonStartDate) and { le("paymentDate", curMonToDate) }
            and {
                eq("paymentStatus", GeneralConstants.PAYMENT_RESULT_SUCCESS)
            }
            projections {
                sum("totalAmount")
            }
        }
        if(currentMonthPaymentAmount[0] == "null" ||currentMonthPaymentAmount[0] == null) {
            usageItem.put("currMonthPayment",  0);
        } else {
            usageItem.put("currMonthPayment", Math.ceil(currentMonthPaymentAmount[0] * 100d) / 100d);  
        } 
        
        def c = Account.createCriteria()
        def accountStatusCount = c.list {
            projections {
                groupProperty "status"
                count "status"
            }
        }
                
        for(def status: accountStatusCount) {
            if(status[0].toString() == "ACTIVE") {
                usageItem.put("activeAccount", status[1]);  
            } else if(status[0].toString()  == "DISABLED") {
                usageItem.put("disableAccount", status[1]);   
            } else if(status[0].toString()  == "NOT_VERIFIED") {
                usageItem.put("inactiveAccount", status[1]);     
            } else if(status[0].toString()  == "SUSPENDED") {
                usageItem.put("suspendAccount", status[1]);     
            } else if(status[0].toString()  == "CANCELED") {
                usageItem.put("cancelAccount", status[1]);     
            } else if(status[0].toString()  == "CLOSED") {
                usageItem.put("closedAccount", status[1]);     
            }             
        }
        
        def enableSignup = "true";
        def ipCost = "false";
        def bandCost = "false";
        def vmSnapcost = "false";
        def snapCost = "false";
        
        //get config values from property files
        ConfigLoader configLoader = ConfigLoader.getInstance();

        Properties props = configLoader.getProperties();
        
        def name = props.get(Config.ORGANISATION_NAME)
        def street = props.get(Config.ORGANISATION_ADDRESS)
        def state = props.get(Config.ORGANISATION_ADDRESS_STATE)
        def country = props.get(Config.ORGANISATION_ADDRESS_COUNTRY)
        def city = props.get(Config.ORGANISATION_ADDRESS_CITY)
        def zip = props.get(Config.ORGANISATION_ADDRESS_ZIP) 
        def region = Region.findAllWhere(deleted: false)
        usageItem.put("regionConfig", region ? "true" : "false");   
        
        def volumeTypeList = VolumeType.findAllWhere(deleted: false)
        usageItem.put("volumeTypeConfig", volumeTypeList ? "true" : "false");   
        
        def openstackConfig = props.get(Config.OPENSTACK_ENDPOINT_URL)
        usageItem.put("openstackConfig", openstackConfig != "" ? "true" : "false"); 
        if(openstackConfig == "" ) {
            enableSignup = "false"; 
        }
        def zenossConfig = props.get(Config.ZENOSS_ENDPOINT_URL)
        usageItem.put("zenossConfig", zenossConfig != "" ? "true" : "false"); 
        
        def flavorList = Flavors.findAllWhere(deleted: false)
        usageItem.put("isFlavorEmpty", flavorList ? "true" : "false");  
        
        def imageList = Images.findAllWhere(deleted: false)
        usageItem.put("isImageEmpty", imageList ? "true" : "false"); 
        
        def zoneList = Zone.findAllWhere(deleted: false)
        usageItem.put("isZoneEmpty", zoneList ? "true" : "false");
        
        def domainList = Domain.findAllWhere(deleted: false, enabled:true)
        usageItem.put("isDomainEmpty", domainList ? "true" : "false");
                
        if(street == "" || country == "" || state == "" || city == "" || zip == "" || name == "") {
            usageItem.put("orgSetting", "false");   
            enableSignup = "false";
        } else {
            usageItem.put("orgSetting", "true"); 
        }
        
        if(!region || !volumeTypeList || !flavorList ||  !imageList || !zoneList) {
            enableSignup = "false";
        } 
                
        if(Domain.findByIsDefault(true)) {
            
            usageItem.put("defaultDomainConfig", "true"); 
            
        } else {
            
            usageItem.put("defaultDomainConfig", "false"); 
            enableSignup = "false";
        }
        
        usageItem.put("enableSignup", enableSignup); 
        
        usageList.add(usageItem)
        return usageList
    }
    
    def getOSAuthClient() {
        
        ConfigLoader configLoader = ConfigLoader.getInstance();

        Properties props = configLoader.getProperties();

        OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

        OSClient os = server.authenticate();        
        
        return os                
    }
    
    def populateUsageRecords() {
        
        Date currentDate = new Date()
        if(lastUsageDate.day != currentDate.day) {
            usageCount = 0
            lastUsageDate = currentDate 
        }
        
        // get all aviliable accounts. 
        def accountList = Account.findAll();
        
        
        def usageFailureAccounts = "";
        
        // loop each account usage.
        for(int i=0; i < accountList.size(); i++) { 
            def account = accountList[i]; 
            try {
                def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                if(invoice) {
                    
                    def startDate
                    
                    if(account.lastUsageRunDate == null || account.lastUsageRunDate == "null" || account.lastUsageRunDate ==  "") {
                        startDate = usageDateFormat.format(account.lastBillingData).toString()
                    } else {
                        startDate = usageDateFormat.format(account.lastUsageRunDate).toString()
                    }
                    
                    getUsageResponseForAccount(account, startDate, invoice)
                    
                    account.lastUsageRunDate = new Date()
                    account.save(flush: true);
                    
                }
                
            } catch (RuntimeException ex) {
                Console.println(ex);
                
                usageFailureAccounts += usageFailureAccounts.size() == 0 ? usageFailureAccounts : ",";
                usageFailureAccounts += account.id
                
                
            } catch (Exception ex) {
                Console.println(ex);
                usageFailureAccounts += usageFailureAccounts.size() == 0 ? usageFailureAccounts : ",";
                usageFailureAccounts += account.id
            }
        }
        if(usageFailureAccounts.length() !=0) {
            if(usageCount > 3) {
//                sendUsageFailedMail(usageFailureAccounts)
            }
        }
        usageCount++
        //        invoiceService.sendBillingMailForAllAccount()
    }
    
    
    def getUsageResponseForAccount(account, startDate, invoice) {
        
        
        try {
            
            Console.println("Usage Start for account:  " + account)
            Console.println("Usage Start for date:  " + startDate)
                        
            getInstaceUsageData(account, startDate, serviceList[0], invoice)
            
            getVolumeUsageData(account, startDate, serviceList[1], invoice)
            
            getSnapshotUsageData(account, startDate, serviceList[3], invoice)
            
            getNetworkUsageData(account, startDate, serviceList[2], invoice)
            
            getNteworkReadUsageData(account, startDate, serviceList[4], invoice)
            
            getNteworkWriteUsageData(account, startDate, serviceList[5], invoice)
            
            getFloatingIPUsageData(account, startDate, serviceList[6], invoice)
            
            getVMSNapshotUsageData(account, startDate, serviceList[7], invoice)
            
            invoiceService.updateInvoice(invoice)
                        
            Console.println("Usage done for account:  " + account)
            Console.println("Usage done for date:  " + startDate)
            
        } catch (Exception ex) {
            Console.println(ex);
            
        }
        
    }
        
    def getInstaceUsageData(account, startDate, serviceName, invoice) {
    
        def VMList = VirtualMachine.executeQuery("SELECT  vm FROM VirtualMachine vm " +
                     "WHERE vm.account = ? AND vm.billingType = ? AND (deletedAt is null OR (deletedAt BETWEEN ? AND ?)) ",
                     [account, "hourly", account.lastUsageRunDate, account.nextBillingData])
               
        Console.println("VMList:  " + VMList)
        
        for(def VM: VMList) {
            
            try {
                
                List<? extends Statistics> stats = getOSAuthClient().telemetry().meters().statistics(serviceName, SampleCriteria.create().resource(VM.referenceId).add("end", SampleCriteria.Oper.EQUALS, usageDateFormat.format(account.nextBillingData).toString()).add("start", SampleCriteria.Oper.EQUALS, startDate));
                
                for (Statistics stat : stats) {
                    
                    Console.println("VM:  " + VM)
                    Console.println("stat:  " + stat)
                    
                    def hours = stat.getDuration() / 3600;
                    
                    invoiceService.updateUsageDataForInvoiceItem(invoice, hours, VM.referenceId, VM.flavor.referenceId, serviceName, BillableItem.get(1))
                    
                } 
            
            } catch (Exception ex) {
                Console.println(ex);
                
            }
        }
        
    }
    
    def getVolumeUsageData(account, startDate, serviceName, invoice) {
                
        def volumeList = Volume.executeQuery("SELECT  volume FROM Volume volume " +
                     "WHERE volume.account = ? AND  volume.billingType = ? AND (deletedAt is null OR (deletedAt BETWEEN ? AND ?)) ",
                     [account, "hourly", account.lastUsageRunDate, account.nextBillingData])
            
        Console.println("volumeList:  " + volumeList)
        
        for(def volume: volumeList) {
            
            try {
                
                List<? extends Statistics> stats = getOSAuthClient().telemetry().meters().statistics(serviceName, SampleCriteria.create().resource(volume.referenceId).add("end", SampleCriteria.Oper.EQUALS, usageDateFormat.format(account.nextBillingData).toString()).add("start", SampleCriteria.Oper.EQUALS, startDate));
                for (Statistics stat : stats) {
                     
                    Console.println("volume:  " + volume)
                    Console.println("stat:  " + stat)
                    
                    def hours = stat.getDuration() / 3600;
                    
                    invoiceService.updateUsageDataForInvoiceItem(invoice, hours, volume.referenceId, volume.volumeType.referenceId, serviceName, BillableItem.get(2))
                    
                } 
            
            } catch (Exception ex) {
                Console.println(ex);
                
            }
            
        }
        
    }
    
    def getSnapshotUsageData(account, startDate, serviceName, invoice) {
        
        def snapshotList = VolumeSnapshot.executeQuery("SELECT  snap FROM VolumeSnapshot snap " +
                     "WHERE snap.account = ? AND snap.billingType = ? AND (deletedAt is null OR (deletedAt BETWEEN ? AND ?)) ",
                     [account, "hourly",  account.lastUsageRunDate, account.nextBillingData])
                                
        for(def snap: snapshotList) {
            
            try {
                
                List<? extends Statistics> stats = getOSAuthClient().telemetry().meters().statistics(serviceName, SampleCriteria.create().resource(snap.referenceId).add("end", SampleCriteria.Oper.EQUALS, usageDateFormat.format(account.nextBillingData).toString()).add("start", SampleCriteria.Oper.EQUALS, startDate));
                for (Statistics stat : stats) {
                    
                    def hours = stat.getDuration() / 3600;
                    
                    invoiceService.updateUsageDataForInvoiceItem(invoice, hours, snap.referenceId, snap.volume.referenceId, serviceName, BillableItem.get(3))
                    
                } 
            
            } catch (Exception ex) {
                Console.println(ex);
                
            }
            
        }
    }
    
    def getNetworkUsageData(account, startDate, serviceName, invoice) {
                
        def networkList = Network.executeQuery("SELECT  network FROM Network network " +
                     "WHERE network.account = ? AND network.billingType = ? AND (deletedAt is null OR (deletedAt BETWEEN ? AND ?)) ",
                     [account, "hourly",  account.lastUsageRunDate, account.nextBillingData])
               
        Console.println("networkList:  " + networkList)
        
        for(def network: networkList) {
            
            try {
                
                List<? extends Statistics> stats = getOSAuthClient().telemetry().meters().statistics(serviceName, SampleCriteria.create().resource(network.referenceId).add("end", SampleCriteria.Oper.EQUALS, usageDateFormat.format(account.nextBillingData).toString()).add("start", SampleCriteria.Oper.EQUALS, startDate));
                for (Statistics stat : stats) {
                    
                    Console.println("network:  " + network)
                    Console.println("stat:  " + stat)
                    
                    def hours = stat.getDuration() / 3600;
                    
                    invoiceService.updateUsageDataForInvoiceItem(invoice, hours, network.referenceId, null, serviceName, BillableItem.get(4))
                    
                } 
            
            } catch (Exception ex) {
                Console.println(ex);
                
            }
            
        }
        
    }
    
    def getNteworkReadUsageData(account, startDate, serviceName, invoice) {
                
//        def networkList = Network.executeQuery("SELECT  network FROM Network network " +
//                     "WHERE network.account = ? AND (deletedAt is null OR (deletedAt BETWEEN ? AND ?)) ",
//                     [account, account.lastUsageRunDate, account.nextBillingData])
                        
//        for(def network: networkList) {
//             Console.println("invoice.account.uuid"  + invoice.account.uuid);
            try {
                
                List<? extends Statistics> stats = getOSAuthClient().telemetry().meters().statistics(serviceName, SampleCriteria.create().add("project", SampleCriteria.Oper.EQUALS, invoice.account.uuid).add("end", SampleCriteria.Oper.EQUALS, usageDateFormat.format(account.nextBillingData).toString()).add("start", SampleCriteria.Oper.EQUALS, startDate));
                for (Statistics stat : stats) {
                    
                    Console.println("BandWidth read:")
                    Console.println("stat:  " + stat)
                
                    def hours = stat.getDuration() / 1000000000d;
                    
                    invoiceService.updateUsageDataForInvoiceItem(invoice, hours, invoice.account.uuid, null, serviceName, BillableItem.get(11))
                    
                } 
            
            } catch (Exception ex) {
                Console.println(ex);
                
            }
            
//        }
        
    }
    def getNteworkWriteUsageData(account, startDate, serviceName, invoice) {
                
//        def networkList = Network.executeQuery("SELECT  network FROM Network network " +
//                     "WHERE network.account = ? AND (deletedAt is null OR (deletedAt BETWEEN ? AND ?)) ",
//                     [account, account.lastUsageRunDate, account.nextBillingData])
//                        
//        for(def network: networkList) {
//            Console.println("invoice.account.uuid"  + invoice.account.uuid);
            try {
                
                List<? extends Statistics> stats = getOSAuthClient().telemetry().meters().statistics(serviceName, SampleCriteria.create().add("project", SampleCriteria.Oper.EQUALS, invoice.account.uuid).add("end", SampleCriteria.Oper.EQUALS, usageDateFormat.format(account.nextBillingData).toString()).add("start", SampleCriteria.Oper.EQUALS, startDate));
                
                for (Statistics stat : stats) {
                
                    Console.println("BandWidth write:")
                    Console.println("stat:  " + stat)
                
                    def hours = stat.getDuration() / 1000000000d;
                                        
                    invoiceService.updateUsageDataForInvoiceItem(invoice, hours, invoice.account.uuid, null, serviceName, BillableItem.get(12))
                    
                } 
            
            } catch (Exception ex) {
                Console.println(ex);
                
            }
            
//        }
        
    }
    
    def getFloatingIPUsageData(account, startDate, serviceName, invoice) {
        
//        def floatingIPList = IP.executeQuery("SELECT  network FROM Network network " +
//                     "WHERE network.account = ? AND (deletedAt is null OR (deletedAt BETWEEN ? AND ?)) ",
//                     [account, account.lastUsageRunDate, account.nextBillingData])
//                        
//        for(def floatingIP: floatingIPList) {
//            
//            try {
//                
//                List<? extends Statistics> stats = getOSAuthClient().telemetry().meters().statistics(serviceName, SampleCriteria.create().resource(floatingIP.referenceId).add("end", SampleCriteria.Oper.EQUALS, usageDateFormat.format(account.nextBillingData).toString()).add("start", SampleCriteria.Oper.EQUALS, startDate));
//                for (Statistics stat : stats) {
//                    
////                    def hours = stat.getDuration() / 3600;
//                    
//                    invoiceService.updateUsageDataForInvoiceItem(invoice, hours, floatingIP.referenceId, null, serviceName)
//                    
//                } 
//            
//            } catch (Exception ex) {
//                Console.println(ex);
//                
//            }
//            
//        }
        
    }
    
    
    def getVMSNapshotUsageData(account, startDate, serviceName, invoice) {
        
        def vmSnapList = Images.executeQuery("SELECT  images FROM Images images " +
                     "WHERE images.account = ? AND images.billingType = ? AND images.isVMSnapshot = ? AND (deletedAt is null OR (deletedAt BETWEEN ? AND ?)) ",
                     [account, "hourly", true, account.lastUsageRunDate, account.nextBillingData])
        
        Console.println("vmSnapList:  " + vmSnapList)
        
        for(def vmSnap: vmSnapList) {
       
            try {
                
                List<? extends Statistics> stats = getOSAuthClient().telemetry().meters().statistics(serviceName, SampleCriteria.create().resource(vmSnap.referenceId).add("end", SampleCriteria.Oper.EQUALS, usageDateFormat.format(account.nextBillingData).toString()).add("start", SampleCriteria.Oper.EQUALS, startDate));
                for (Statistics stat : stats) {
                 
                    Console.println("vmSnap:  " + vmSnap)
                    Console.println("stat:  " + stat)
                    
                    def hours = stat.getDuration() / 3600;
                    
                    invoiceService.updateUsageDataForInvoiceItem(invoice, hours, vmSnap.referenceId, null, serviceName, BillableItem.get(13))
                    
                } 
            
            } catch (Exception ex) {
                Console.println(ex);
                
            }
            
        }
        
        
    }
}
