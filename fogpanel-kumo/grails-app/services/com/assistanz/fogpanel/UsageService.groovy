package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.usages.CSAccountUsageService
import com.assistanz.cloud.cloudstack.usages.UsageRecordResponse
import java.text.SimpleDateFormat
import javax.xml.bind.DatatypeConverter;
import java.sql.Timestamp
import java.text.DateFormat
import com.assistanz.cloud.cloudstack.usages.UsageTypeRecordResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;
    
class UsageService {
    
    
    static lastUsageDate = new Date();
    static usageCount = 0;
    
    InvoiceService invoiceService
    NotificationService notificationService
    private static final log = LogFactory.getLog(this)
    
    /**
     * connect to cloud stack and return cloud stack usage object.
     *
     */
    def cloudStackUsageServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
                
        CSAccountUsageService csUsageService = new CSAccountUsageService(server);
        
        return csUsageService;
    }
    
    
    def usageCount() {
          
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
        def offeringUsageCriteria = OfferingUsage.createCriteria()
        def daily = offeringUsageCriteria.get { 
            ne("usageType", UsageType.values()[2])
            and { ge("startDate", fromDate) and { le("startDate", toDate) } 
            }
            projections {
                sum("cost")
            } 
        }
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
        
        def zoneList = Zone.findAll()     
        def firewall = SecurityGroupTemplate.findAll()
        usageItem.put("firewall", firewall ? "true" : "false"); 

        def name = Config.findByName(Config.ORGANISATION_NAME).value
        def street = Config.findByName(Config.ORGANISATION_ADDRESS).value
        def state = Config.findByName(Config.ORGANISATION_ADDRESS_STATE).value
        def country = Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY).value
        def city = Config.findByName(Config.ORGANISATION_ADDRESS_CITY).value
        def zip = Config.findByName(Config.ORGANISATION_ADDRESS_ZIP).value 

        if(street == "" || country == "" || state == "" || city == "" || zip == "" || name == "") {
            usageItem.put("orgSetting", "false");   
            enableSignup = "false";
        } else {
            usageItem.put("orgSetting", "true"); 
        }
       
        if(!firewall) {
            enableSignup = "false";
        } 
        if(zoneList) {
            for(def zone: zoneList) {
                if(!MiscellaneousOfferZoneCost.findWhere(zone: zone , miscellaneousOffer: MiscellaneousOffer.get(1)) || !MiscellaneousOfferZoneCost.findWhere(zone: zone , miscellaneousOffer: MiscellaneousOffer.get(2)) || !MiscellaneousOfferZoneCost.findWhere(zone: zone , miscellaneousOffer: MiscellaneousOffer.get(3)) || !MiscellaneousOfferZoneCost.findWhere(zone: zone , miscellaneousOffer: MiscellaneousOffer.get(4))) {
                   enableSignup = "false";
                   break;
                }            }
            for(def zone: zoneList) {
                if(!MiscellaneousOfferZoneCost.findWhere(zone: zone , miscellaneousOffer: MiscellaneousOffer.get(1))) {
                    enableSignup = "false";   
                    bandCost = "false";
                    break
                } else {
                    bandCost = "true";
                }
            }
            for(def zone: zoneList) {
                if(!MiscellaneousOfferZoneCost.findWhere(zone: zone , miscellaneousOffer: MiscellaneousOffer.get(2))) {
                    ipCost = "false";
                    enableSignup = "false";
                    break
                } else {
                   ipCost = "true"; 
                }
                
            }
            for(def zone: zoneList) {
                if(!MiscellaneousOfferZoneCost.findWhere(zone: zone , miscellaneousOffer: MiscellaneousOffer.get(3))) {
                    snapCost = "false";
                    enableSignup = "false";
                    break
                } else {
                    snapCost = "true";
                }
                
            }
            for(def zone: zoneList) {
                if(!MiscellaneousOfferZoneCost.findWhere(zone: zone , miscellaneousOffer: MiscellaneousOffer.get(4))) {
                    vmSnapcost = "false";
                    enableSignup = "false";
                    break
                } else {
                   vmSnapcost = "true";
                }
            }
        } 
        usageItem.put("ip", ipCost);   
        usageItem.put("snap", snapCost); 
        usageItem.put("vmSnap", vmSnapcost);   
        usageItem.put("band", bandCost);
        usageItem.put("enableSignup", enableSignup); 
        
        usageList.add(usageItem)
        return usageList
    }
    
    def dailyUsageCheck() {
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
        
                
        def offeringUsageCriteria = OfferingUsage.createCriteria()
        def daily = offeringUsageCriteria.list { 
            ne("usageType", UsageType.values()[2])
            and { ge("startDate", fromDate) and { le("startDate", toDate) } 
            }
            projections {
                sum("cost")
            } 
        }
        def dailyCost;
        if(daily[0] == "null" || daily[0] == null) {
            dailyCost = 0
        } else {
            dailyCost = Math.ceil(daily[0] * 100d) / 100d
        } 
        
        def vmList = VirtualMachine.findAllWhere(deleted: false)
        def diskList = Volume.findAllWhere(deleted: false)
        def snapList = Snapshot.findAllWhere(deleted: false)
        
        if(dailyCost == 0 && (vmList || diskList || snapList)) {
            sendUsageFailedMail("ALL")
        }
        log.info("DailyUsageCheck Cron Completed");
    }
    
    
    def dailyUsageCostUpdate() {
     
        
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
        
                
        def offeringUsageCriteria = OfferingUsage.createCriteria()
        def daily = offeringUsageCriteria.list { 
            ne("usageType", UsageType.values()[2])
            and { ge("startDate", fromDate) and { le("startDate", toDate) } 
            }
            projections {
                sum("cost")
            } 
        }
        def dailyCost;
        if(daily[0] == "null" || daily[0] == null) {
            dailyCost = 0
        } else {
            dailyCost = Math.ceil(daily[0] * 100d) / 100d
        } 
        
        
        def dailyUsageCostCriteria = DailyUsageCost.createCriteria()
        def dailyTable = dailyUsageCostCriteria.get { 
            ge("date", fromDate) and { le("date", toDate) } 
        }
        
        def dailyRefundCriteria = Refund.createCriteria()
        def dailyRefund = dailyRefundCriteria.list { 
            ge("date", fromDate) and { le("date", toDate) } 
            projections {
                sum("amount")
            }
        }
        def refund
        
        if(dailyRefund[0] == "null" ||dailyRefund[0] == null) {
            refund = 0
        } else {
            refund = dailyRefund[0]
        }

        if(!dailyTable) {
            def dailyUsageCost = new DailyUsageCost()
            dailyUsageCost.refund = refund
            dailyUsageCost.cost = dailyCost
            dailyUsageCost.date = fromDate
            dailyUsageCost.save(flush: true);
        } else {
            dailyTable.refund = refund
            dailyTable.cost = dailyCost
            dailyTable.save(flush: true);
        }
        log.info("DailyUsageCalc Cron Completed");
    }
    
    
    def populateUsageRecord() {
        
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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(account.lastUsageRunDate == null || account.lastUsageRunDate == "null" || account.lastUsageRunDate ==  "") {
                        startDate = dateFormat.format(account.lastBillingData).toString()
                    } else {
                        startDate = dateFormat.format(account.lastUsageRunDate).toString()
                    }
                    
                    usageResponseForAccount(account, startDate, null)
                }
                
            } catch (RuntimeException ex) {
                Console.print(ex);
                
                usageFailureAccounts += usageFailureAccounts.size() == 0 ? usageFailureAccounts : ",";
                usageFailureAccounts += account.id
                account.isUsageRunningFailed == true
                account.save(flush: true)
                
            } catch (Exception ex) {
                Console.print(ex);
                usageFailureAccounts += usageFailureAccounts.size() == 0 ? usageFailureAccounts : ",";
                usageFailureAccounts += account.id
                account.isUsageRunningFailed == true
                account.save(flush: true)
            }
        }
        if(usageFailureAccounts.length() !=0) {
            if(usageCount > 3) {
                sendUsageFailedMail(usageFailureAccounts)
            }
        }
        usageCount++
        invoiceService.sendBillingMailForAllAccount()
    }
    
    def sendUsageFailedMail(usageFailureAccounts) {
        
        // adde log for this account
        Date date = new Date()
        def time = new Timestamp(date.getTime())
        def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
        def log = new Log()
        log.account = Account.get(10001)
        log.user = User.findByUsername(log.account.userName)
        log.date = time
        log.type = LogType.values()[4]
        log.mailSend = true
        log.description =  "Usage running failed for accounts: "+ usageFailureAccounts.toString();
        log.save(flush: true);
        
        //send Mail Menthod                
        Map tempalteMap = notificationService.getDefaultMailTemplateMap()
        tempalteMap.put("accounts",  usageFailureAccounts.toString())
        Config organizationEmail =  Config.findByName(Config.ORGANISATION_EMAIL);
        notificationService.send(organizationEmail.value, "cloudUsageNotification.subject.ftl", tempalteMap, "cloudUsageNotification.ftl")      
    }
    
    def usageResponseForAccount(account, startDate, invoice) {       
        try {
            
                                    
            // api call for list usage type.                                                                                                                                                                                                
            def usageTypeResponse = cloudStackUsageServer().listUsageTypes();
            
            for(Iterator<UsageTypeRecordResponse> iter = usageTypeResponse.usageTypes.iterator(); iter.hasNext();) {
                def data = iter.next();
                               
                HashMap<String,String> optional = new HashMap<String,String>();
                optional.put("account", account.userName);
                optional.put("domainid", account.domain.referenceId);
                optional.put("type", data.usageTypeId);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                if(data.usageTypeId == "1") {
                                       
                    // api call for list usage.
                    def response = cloudStackUsageServer().listUsageRecords(dateFormat.format(account.nextBillingData).toString(), 
                        startDate, optional);
                    
                    loopForUsageResponse(response, account , invoice)
                } else if(data.usageTypeId == "2") {
                    // api call for list usage.
                    def response = cloudStackUsageServer().listUsageRecords(dateFormat.format(account.nextBillingData).toString(), 
                       startDate , optional);
                    
                    loopForUsageResponse(response, account, invoice)
                } else if(data.usageTypeId == "6") {
                    // api call for list usage.
                    def response = cloudStackUsageServer().listUsageRecords(dateFormat.format(account.nextBillingData).toString(), 
                        startDate, optional);
                    
                    loopForUsageResponse(response, account, invoice)
                } else if(data.usageTypeId == "9") {
                    // api call for list usage.
                    def response = cloudStackUsageServer().listUsageRecords(dateFormat.format(account.nextBillingData).toString(), 
                       startDate, optional);
                    
                    loopForUsageResponse(response, account, invoice)
                } else if(data.usageTypeId == "25") {
                    // api call for list usage.
                    def response = cloudStackUsageServer().listUsageRecords(dateFormat.format(account.nextBillingData).toString(), 
                       startDate, optional);
                    
                    loopForUsageResponse(response, account, invoice)
                } 
            }
            calcVMSnapshotUsageForAllAccount(account);
        } catch (RuntimeException ex) {
            log.info("Usage last run for account: ${account.id} failed due to ${ex}");
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            log.info("Usage last run for account: ${account.id} failed due to ${ex}");
            throw ex;    
        }
    }
    
    /**
     * VM Snapshot usage calc for all account
     * 
     * */
    def calcVMSnapshotUsageForAllAccount(currentAccount) {
        
        
        def accountList;
        if(currentAccount) {
            accountList = Account.findAllWhere(userName:currentAccount.userName); 
        } else {
            // get all aviliable accounts. 
           accountList = Account.findAll(); 
        }
        
        // loop each account usage.
        for(def account: accountList) { 
            try {
                def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                if(invoice) {
                    def fromDate;
                    def toDate;
                    def vmSmapDate;
                    if(account.lastUsageRunDate == null || account.lastUsageRunDate == "null" || account.lastUsageRunDate ==  "") {
                        Calendar calendar = Calendar.getInstance(); 
                        calendar.setTime(account.lastBillingData);
                        calendar.set(Calendar.HOUR_OF_DAY, 00);
                        calendar.set(Calendar.MINUTE, 00);
                        calendar.set(Calendar.SECOND, 00);
                        calendar.set(Calendar.MILLISECOND, 999);                   
                        fromDate = calendar.getTime()
                    } else {
                        Calendar calendar = Calendar.getInstance(); 
                        calendar.setTime(account.lastUsageRunDate);
                        calendar.set(Calendar.HOUR_OF_DAY, 00);
                        calendar.set(Calendar.MINUTE, 00);
                        calendar.set(Calendar.SECOND, 00);
                        calendar.set(Calendar.MILLISECOND, 999);                   
                        fromDate = calendar.getTime()
                    }
                    Calendar currDateCalendar = Calendar.getInstance();
                    currDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                    currDateCalendar.set(Calendar.MINUTE, 59);
                    currDateCalendar.set(Calendar.SECOND, 59);
                    currDateCalendar.set(Calendar.MILLISECOND, 999);                           
                    toDate = currDateCalendar.getTime()
                    
                    Calendar vmSnapUsageDate = Calendar.getInstance();
                    if(account.vmSnapUsageDate) {
                        vmSnapUsageDate.setTime(account.vmSnapUsageDate);
                    }
                    vmSnapUsageDate.set(Calendar.HOUR_OF_DAY, 23);
                    vmSnapUsageDate.set(Calendar.MINUTE, 59);
                    vmSnapUsageDate.set(Calendar.SECOND, 59);
                    vmSnapUsageDate.set(Calendar.MILLISECOND, 999);                           
                    vmSmapDate = vmSnapUsageDate.getTime()
                    
                    if(vmSmapDate != toDate) {
                        
                        def vmList = InvoiceItem.findAllWhere(invoice: invoice, billableItem: BillableItem.get(17))

                        for(def vmSnap : vmList) {
                            def offeringUsageCriteria = OfferingUsage.createCriteria()
                            def vmsnapusages = offeringUsageCriteria.list {         
                                and { ge("startDate", fromDate) and { le("startDate", toDate) } }
                                eq("virtualMachineId", vmSnap.referenceItemId)
                                eq("usageType", UsageType.values()[15])
                                projections {
                                    sum("size")
                                    groupProperty("hours")
                                }
                            }
                                       
                            for(def vmsnapusage : vmsnapusages) { 
                                                               
                                if(vmsnapusage) {
                                    def vmSnapInvoiceItem = InvoiceItem.findWhere(invoice: invoice, billableItem: BillableItem.get(17), referenceItemId: vmSnap.referenceItemId)                                                                       
                                    
                                    def size = Double.parseDouble(vmsnapusage[0].toString())/1073741824;

                                    vmSnapInvoiceItem.usageUnits = vmSnapInvoiceItem.usageUnits + Double.parseDouble(vmsnapusage[1].toString());
                                    
                                    double usage = Math.ceil((vmSnapInvoiceItem.usageUnitPrice * size * vmSnapInvoiceItem.usageUnits) * 100d) / 100d;
                                    double subtotalUsageCost = usage

                                    //new usage calc
                                    vmSnapInvoiceItem.usageAmount =   Math.ceil((subtotalUsageCost) * 100d) / 100d;
                                    
                                    //discount amount
                                    double discountAmount = (vmSnapInvoiceItem.usageAmount/100)*vmSnapInvoiceItem.discountPercent
                                    vmSnapInvoiceItem.discountAmount = discountAmount;

                                    // sub total
                                    double subTotal = vmSnapInvoiceItem.usageAmount - discountAmount
                                    
                                    
                                    // tax amount
                                    double taxAmount = (vmSnapInvoiceItem.usageAmount/100)*vmSnapInvoiceItem.taxPercent
                                    vmSnapInvoiceItem.taxAmount = taxAmount;

                                    //total amount
                                    vmSnapInvoiceItem.totalAmount = subTotal + vmSnapInvoiceItem.taxAmount;    
                                    
                                    vmSnapInvoiceItem.save(flush: true);

                                } 
                                account.vmSnapUsageDate = currDateCalendar.getTime()
                            }
                        } 
                }
                }
                Calendar date = Calendar.getInstance();
                date.add(Calendar.DATE, -1);
                                
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


                def offeringUsageCriteria = OfferingUsage.createCriteria()
                def daily = offeringUsageCriteria.list { 
                    ne("usageType", UsageType.values()[2])
                    eq("account", account)
                    and { ge("startDate", fromDate) and { le("startDate", toDate) } 
                    }
                    projections {
                        sum("cost")
                    } 
                }
                def dailyCost;
                if(daily[0] == "null" || daily[0] == null) {
                    dailyCost = 0
                } else {
                    dailyCost = Math.ceil(daily[0] * 100d) / 100d
                } 

                def vmList = VirtualMachine.findAllWhere(deleted: false, owner:account)
                def diskList = Volume.findAllWhere(deleted: false, owner:account)
                
                if(dailyCost == 0 && (vmList || diskList)) {
                    account.isUsageRunningFailed == true
                    account.save(flush: true)
                } else {
                    account.isUsageRunningFailed == false
                    account.lastUsageRunDate = date.getTime()
                    account.save(flush: true);
                }
                log.info("Usage last run for account: ${account.id}");
            } catch (RuntimeException ex) {
               throw new RuntimeException(ex);
            } catch (Exception ex) {
               throw ex;    
            }
        }
    }
    
    
    //list all usage record.
    def list() {
        
    // get all aviliable accounts. 
    def accountList = Account.findAll();

    // loop each account usage.
    for(int i=0; i < accountList.size(); i++) { 
        def account = accountList[i]; 
        try {
                def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                if(invoice) {
                    // add aditionnal value parameter for usage api.
                    HashMap<String,String> optional = new HashMap<String,String>();
                    optional.put("account", account.userName);
                    optional.put("domainid", account.domain.referenceId);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //                dateFormat.format(account.nextBillingData).toString(), dateFormat.format(account.lastBillingData).toString()

                    // api call for list usage.
                    def response = cloudStackUsageServer().listUsageRecords(dateFormat.format(account.nextBillingData).toString(), dateFormat.format(account.lastBillingData).toString(), optional);
                    
                    loopForUsageResponse(response, account)
                }
            
            } catch (Exception ex) {
                Console.print(ex);
//                ex.printStackTrace(System.err);
//                throw ex;
            }
        }
    }
    
    
    /**
     * loop for usage record from cloud stack.
     */
    def loopForUsageResponse(response, account, invoice) {
        try {     

            // loop each usage record from cloud stack server.
            for(Iterator<UsageRecordResponse> iter = response.usageRecords.iterator(); iter.hasNext();) {
                def data = iter.next();

                // convert string date to date object. 
                Calendar endDate = DatatypeConverter.parseDateTime(data.endDate.replace("'T'", 'T'));

                // convert string date to date object. 
                Calendar startDate = DatatypeConverter.parseDateTime(data.startDate.replace("'T'", 'T'));

                 // create timestamp object for start date and end date.
                Timestamp startTimestamp = new Timestamp(startDate.getTimeInMillis()); 
                Timestamp endTimestamp = new Timestamp(endDate.getTimeInMillis()); 

                // check for aviliable usage record, if yes update else add new usage record.
                def offeringUsage;
                if(data.usageType == "25") {
                    offeringUsage = OfferingUsage.findWhere(referenceId: data.usageId, startDate: startTimestamp, endDate: endTimestamp, usageType:UsageType.values()[15], offeringId: data.offeringId);
                } else {
                    offeringUsage = OfferingUsage.findWhere(referenceId: data.usageId, startDate: startTimestamp, endDate: endTimestamp, usageType:UsageType.values()[Integer.parseInt(data.usageType)], offeringId: data.offeringId);
                }
                
                if (!offeringUsage) {

                    // add new usage record
                    addUsageRecord(offeringUsage, data, startTimestamp, endTimestamp)
                } else if(offeringUsage) {

                    // update usage record
//                    updateUsageRecord(offeringUsage, data, startTimestamp, endTimestamp) 
                }
            }
            updateInvoice(account , invoice);
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw ex;    
        }
    }
    
    
    
    
    /**
     * Add new usage record.
     */
    private addUsageRecord(offeringUsage, data, startTimestamp, endTimestamp) {
        try {  

            def offering;
            def invoiceItem
            def invoice = Invoice.findWhere(account: Account.findByUserName(data.accountName), status: InvoiceStatus.values()[6])
            offeringUsage  = new OfferingUsage();
            offeringUsage.zone = Zone.findByReferenceZoneId(data.zoneId);
            offeringUsage.referenceId = data.usageId;
            offeringUsage.offeringId = data.offeringId;
            offeringUsage.netwotkId = data.networkId;
            offeringUsage.virtualMachineId = data.vmId;
            offeringUsage.virtualMachineName = data.vmName;
            offeringUsage.templateId = data.templateId;
            offeringUsage.account = Account.findByUserName(data.accountName); 
            offeringUsage.domain = Account.findByUserName(data.accountName).domain;
            offeringUsage.description = data.description;
            if(data.usageType == "25") {
                offeringUsage.usageType  = UsageType.values()[15];
            } else {
                offeringUsage.usageType  = UsageType.values()[Integer.parseInt(data.usageType)];
            }
            offeringUsage.hours = Double.parseDouble(data.rawUsage); 
            offeringUsage.usageDisplay = data.usage; 
            offeringUsage.startDate = startTimestamp;
            offeringUsage.endDate = endTimestamp;
            offeringUsage.size = data.resourceSize;
            if(data.usageType == "1") {
               offering = ComputingOffer.findByOfferReferenceId(data.offeringId);
               if(offering) {
                   offeringUsage.cost = ComputingOfferZoneCost.findByComputingOffer(offering).cost * Double.parseDouble(data.rawUsage);
               }
                def vmbillableItem = BillableItem.get(1)
                def invoiceItemCriteriaCustom = InvoiceItem.createCriteria()
                def vms = invoiceItemCriteriaCustom.list {
                    eq("invoice", invoice) 
                    and {
                        eq("referenceItemId", data.usageId)
                        and {
                           eq("referencePlanId", data.offeringId)  
                        }
                        and {
                        eq("billableItem",  vmbillableItem )  
                          
                        }
                    }
                    order("id", "desc")
                }                 
               invoiceItem = vms[0]
            } else if(data.usageType == "2") {
                offering = ComputingOffer.findByOfferReferenceId(data.offeringId);
                if(offering) {
                    offeringUsage.cost = ComputingOfferZoneCost.findByComputingOffer(offering).cost * Double.parseDouble(data.rawUsage);
                }               
            } else if(data.usageType == "6") {
                offering = DiskOffer.findByDiskOfferReferenceId(data.offeringId);
                def size = Double.parseDouble(data.resourceSize)/1073741824;
                if(offering) {
                   offeringUsage.cost = DiskOfferZoneCost.findByDiskOffer(offering).cost * size * Double.parseDouble(data.rawUsage);
                }
                def diskbillableItem = BillableItem.get(2)              
                def invoiceItemCriteriaCustom = InvoiceItem.createCriteria()
                def disks = invoiceItemCriteriaCustom.list {
                    eq("invoice", invoice) 
                    and {
                        eq("referenceItemId", data.usageId)
                        and {
                           eq("referencePlanId", data.offeringId)  
                        }
                        and {
                        eq("billableItem",  diskbillableItem)  

                        }
                    }
                    order("id", "desc")
                }                 
               invoiceItem = disks[0]
//                invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:data.usageId, billableItem: diskbillableItem)
            } else if(data.usageType == "7") {
                def template = Template.findByTemplateReferenceId(data.templateId);
                if(template) {
                   offeringUsage.cost = template.cost;
                } 
            } else if(data.usageType == "8") {
                //iso
            } else if(data.usageType == "9") {
                def shapshot = MiscellaneousOffer.get(3);
                def size = Double.parseDouble(data.resourceSize)/1073741824;
                def snapshotCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :shapshot, zone: Zone.findByReferenceZoneId(data.zoneId));
                if(snapshotCost) {
                   offeringUsage.cost = snapshotCost.cost * size * Double.parseDouble(data.rawUsage);
                } 
                def snapbillableItem = BillableItem.get(3)
                invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:data.usageId, billableItem :snapbillableItem)
            } else if(data.usageType == "25") {
                def vmShapshot = MiscellaneousOffer.get(4);
                def vmSnapshotCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :vmShapshot, zone: Zone.findByReferenceZoneId(data.zoneId));
                if(vmSnapshotCost) {
                   offeringUsage.cost = vmSnapshotCost.cost * Double.parseDouble(data.rawUsage);
                } 
                def vmSnapbillableItem = BillableItem.get(17)
                invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:data.usageId, billableItem :vmSnapbillableItem)
            }

            offeringUsage.save(flush: true);
            if (!offeringUsage.save()) {
//                offeringUsage.errors.allErrors.each { Console.print(it) }
            }
            
            
            if(!invoiceItem) {

                 if(data.usageType == "1") {
                     def vm = VirtualMachine.findByReferenceId(data.usageId);
                     if(vm) {
                        if(vm.billingType == "hourly") {
                          addInvoiceItem(offeringUsage, invoice, data)   
                        } 
                     }
                 } else if(data.usageType == "6") {
                     def volume = Volume.findByVolumeReferenceId(data.usageId);
                     if(volume) {
                        if(volume.billingType == "hourly") {
                            addInvoiceItem(offeringUsage, invoice, data) 
                        } 
                     }
                 } else if(data.usageType == "9") {
                     def snapshot = Snapshot.findBySnapshotReferenceId(data.usageId);
                     if(snapshot) {
                         if(snapshot.billingType == "hourly") {
                             addInvoiceItem(offeringUsage, invoice, data) 
                         }  
                     }
                 }
            } else if(invoiceItem) {
                updateInvoiceItem(invoiceItem, offeringUsage, invoice)
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw ex;    
        }
    }
    
    /**
     * Update usage record.
     */
    private updateUsageRecord(offeringUsage, data, startTimestamp, endTimestamp) {
        try {      
            
            def offering;
            def invoiceItem
            def billableItem
            def invoice = Invoice.findWhere(account: Account.findByUserName(data.accountName), status: InvoiceStatus.values()[6])
            offeringUsage.usageDisplay = data.usage; 
            offeringUsage.hours = Double.parseDouble(data.rawUsage);
            offeringUsage.startDate = startTimestamp;
            offeringUsage.endDate = endTimestamp;
            offeringUsage.size = data.resourceSize;
            if(data.usageType == "1") {
               offering = ComputingOffer.findByOfferReferenceId(data.offeringId);
               if(offering) {
                   offeringUsage.cost = ComputingOfferZoneCost.findByComputingOffer(offering).cost * Double.parseDouble(data.rawUsage);
               } 
               billableItem = BillableItem.get(1)
               invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:data.usageId, referencePlanId:data.offeringId, billableItem: billableItem)
            } else if(data.usageType == "2") {
                offering = ComputingOffer.findByOfferReferenceId(data.offeringId);
                if(offering) {
                    offeringUsage.cost = ComputingOfferZoneCost.findByComputingOffer(offering).cost * Double.parseDouble(data.rawUsage);
                }
            } else if(data.usageType == "6") {
                offering = DiskOffer.findByDiskOfferReferenceId(data.offeringId);
                def size = Double.parseDouble(data.resourceSize)/1073741824;
                if(offering) {
                   offeringUsage.cost = DiskOfferZoneCost.findByDiskOffer(offering).cost * size * Double.parseDouble(data.rawUsage);
                }
                billableItem = BillableItem.get(2)
                invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:data.usageId, billableItem: billableItem)
            } else if(data.usageType == "7") {
                def template = Template.findByTemplateReferenceId(data.templateId);
                if(template) {
                   offeringUsage.cost = template.cost;
                } 
            } else if(data.usageType == "8") {
                //iso
            } else if(data.usageType == "9") {
                def shapshot = MiscellaneousOffer.get(3);
                def size = Double.parseDouble(data.resourceSize)/1073741824;
                def snapshotCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :shapshot, zone: Zone.findByReferenceZoneId(data.zoneId));
                if(snapshotCost) {
                   offeringUsage.cost = snapshotCost.cost * size * Double.parseDouble(data.rawUsage);
                } 
                billableItem = BillableItem.get(3)
                invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:data.usageId, billableItem: billableItem)
            } else if(data.usageType == "25") {
                def vmShapshot = MiscellaneousOffer.get(4);
                def vmSnapshotCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :vmShapshot, zone: Zone.findByReferenceZoneId(data.zoneId));
                if(vmSnapshotCost) {
                   offeringUsage.cost = vmSnapshotCost.cost * Double.parseDouble(data.rawUsage);
                } 
                def vmSnapbillableItem = BillableItem.get(17)
                invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId:data.usageId, billableItem :vmSnapbillableItem)
            }

            offeringUsage.save(flush: true);
            if (!offeringUsage.save()) {
                offeringUsage.errors.allErrors.each { Console.print(it) }
            }

             if(!invoiceItem) {
                 if(data.usageType == "1") {
                     def vm = VirtualMachine.findByReferenceId(data.usageId);
                     if(vm) {
                        if(vm.billingType == "hourly") {
                          addInvoiceItem(offeringUsage, invoice, data) 
                        }
                     }
                 } else if(data.usageType == "6") {
                     def volume = Volume.findByVolumeReferenceId(data.usageId);
                     if(volume) {
                        if(volume.billingType == "hourly") {
                            addInvoiceItem(offeringUsage, invoice, data) 
                        } 
                     }
                 } else if(data.usageType == "9") {
                     def snapshot = Snapshot.findBySnapshotReferenceId(data.usageId);
                     if(snapshot) {
                        if(snapshot.billingType == "hourly") {
                             addInvoiceItem(offeringUsage, invoice, data) 
                         } 
                     }
                 }
            } 

        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw ex;    
        }     
    }
    
    def updateInvoiceItem(invoiceItem, offeringUsage, invoice) {
        try {                      
            
            if(offeringUsage.usageType.name() == "VOLUME_USAGE") {
                
                // usage unit
                invoiceItem.usageUnits = invoiceItem.usageUnits + offeringUsage.hours;
                
                Volume volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                def size = Double.parseDouble(offeringUsage.size)/1073741824;
                
                if(volume.billingType == "hourly") {
                    double usage = Math.ceil((invoiceItem.usageUnitPrice * size * invoiceItem.usageUnits) * 100d) / 100d;
                    double subtotalUsageCost = usage

                    //usage amount
                    invoiceItem.usageAmount =   Math.ceil((subtotalUsageCost) * 100d) / 100d;
                }
                

            } else if(offeringUsage.usageType.name() == "SNAPSHOT_USAGE") {
                
                // usage unit
                invoiceItem.usageUnits = invoiceItem.usageUnits + offeringUsage.hours;
                
                def size = Double.parseDouble(offeringUsage.size)/1073741824;
                
                
                double usage = Math.ceil((invoiceItem.usageUnitPrice * size * invoiceItem.usageUnits) * 100d) / 100d;
                double subtotalUsageCost = usage

                //new usage calc
                invoiceItem.usageAmount =   Math.ceil((subtotalUsageCost) * 100d) / 100d;
                
               
            } else if(offeringUsage.usageType.name() == "VM_SNAPSHOT") {
                    
                    // usage unit
//                  invoiceItem.usageUnits = invoiceItem.usageUnits + offeringUsage.hours;
                
//                double usage = Math.ceil((invoiceItem.usageUnitPrice * invoiceItem.usageUnits) * 100d) / 100d;
//                double subtotalUsageCost = usage
//                
//                //new usage calc
//                invoiceItem.usageAmount =  Math.ceil((subtotalUsageCost) * 100d) / 100d;
            } else {
                
                // usage unit
                invoiceItem.usageUnits = invoiceItem.usageUnits + offeringUsage.hours;
                
                double usage = Math.ceil((invoiceItem.usageUnitPrice * invoiceItem.usageUnits) * 100d) / 100d;
                double subtotalUsageCost = usage
                
                //new usage calc
                invoiceItem.usageAmount =  Math.ceil((subtotalUsageCost) * 100d) / 100d;
            }


            //discount amount
            double discountAmount = (invoiceItem.usageAmount/100)*invoiceItem.discountPercent
            invoiceItem.discountAmount = discountAmount;

            // sub total
            double subTotal = invoiceItem.usageAmount - discountAmount

            // tax amount
            double taxAmount = (subTotal/100)*invoiceItem.taxPercent
            invoiceItem.taxAmount = taxAmount;

            //total amount
            invoiceItem.totalAmount = subTotal + invoiceItem.taxAmount;        

            invoiceItem.save(flush: true); 
            if (!invoiceItem.save()) {
                invoiceItem.errors.allErrors.each { Console.print(it) }
            }

            // save usage record.
            offeringUsage.save(flush: true);
            if (!offeringUsage.save()) {
                offeringUsage.errors.allErrors.each { Console.print(it) }
            }


        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw ex;    
        }       

    }
    
    def addInvoiceItem(offeringUsage, invoice, data) {
        try { 

            def offering;
            def discountList;
            def discountCriteria = Discount.createCriteria()
            DateFormat formater2 = new SimpleDateFormat("dd/MM/yyyy"); 
            Date curDate = Calendar.getInstance().getTime()
            def today = formater2.format(curDate)
            Date currentDate = formater2.parse(today);
            def billableItem;

            def newInvoiceItem = new InvoiceItem()
            newInvoiceItem.invoice = invoice        
            if(data.usageType == "1") {
                offering = ComputingOffer.findByOfferReferenceId(data.offeringId);
                if(offering) {
                    offeringUsage.cost = ComputingOfferZoneCost.findByComputingOffer(offering).cost * Double.parseDouble(data.rawUsage);
                    newInvoiceItem.usageUnitPrice = ComputingOfferZoneCost.findByComputingOffer(offering).cost
                    newInvoiceItem.zone = offering.zone
                }
                billableItem = BillableItem.findByReferenceItemName("computingOffer")
                newInvoiceItem.billableItem = billableItem
                newInvoiceItem.referenceItemName = "VirtualMachine"
                newInvoiceItem.referencePlanId = data.offeringId
                if(billableItem.discountable == true || billableItem.discountable == "true") {
                    discountList = discountCriteria.list {
                        eq("subType", "CREATE_VM")
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
                                newInvoiceItem.discount = discount;
                                newInvoiceItem.discountPercent = discount.value;
                                break
                            } else {
                                for(Iterator m = discount.computingOffers.iterator();m.hasNext();) { 
                                    def discountComputerOffer = m.next(); 
                                    if(discountComputerOffer == offering) {
                                        for(Iterator l = discount.accounts.iterator();l.hasNext();) { 
                                            def discountAccount = l.next(); 
                                            if(discountAccount == newVirtualMachine.owner) {
                                                newInvoiceItem.discount = discount;
                                                newInvoiceItem.discountPercent = discount.value;
                                                break 
                                            }
                                        }
                                    } 
                                }
                            }                   

                        }
                    }
                }
            } else if(data.usageType == "2") {
                offering = ComputingOffer.findByOfferReferenceId(data.offeringId);
                if(offering) {
                    offeringUsage.cost = ComputingOfferZoneCost.findByComputingOffer(offering).cost * Double.parseDouble(data.rawUsage);
                } 
            } else if(data.usageType == "6") {
                newInvoiceItem.referenceItemName = "Volume"
                def size = Double.parseDouble(data.resourceSize)/1073741824;
                offering = DiskOffer.findByDiskOfferReferenceId(data.offeringId);
                if(offering) {
                   offeringUsage.cost = DiskOfferZoneCost.findByDiskOffer(offering).cost * size * Double.parseDouble(data.rawUsage);
                   newInvoiceItem.usageUnitPrice = DiskOfferZoneCost.findByDiskOffer(offering).cost
                   newInvoiceItem.referencePlanId = data.offeringId
                   newInvoiceItem.zone = offering.zone
                }
                billableItem = BillableItem.findByReferenceItemName("diskOffer")
            } else if(data.usageType == "7") {
                def template = Template.findByTemplateReferenceId(data.templateId);
                if(template) {
                   offeringUsage.cost = template.cost;
                } 
            } else if(data.usageType == "8") {
                //iso
            } else if(data.usageType == "9") {
                newInvoiceItem.referenceItemName = "Snapshot"
                def shapshot = MiscellaneousOffer.get(3);
                def size = Double.parseDouble(data.resourceSize)/1073741824;
                def snapshotCost = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer :shapshot, zone: Zone.findByReferenceZoneId(data.zoneId));
                if(snapshotCost) {
                   offeringUsage.cost = snapshotCost.cost * size * Double.parseDouble(data.rawUsage);
                   newInvoiceItem.usageUnitPrice = snapshotCost.cost
                   newInvoiceItem.zone = Zone.findByReferenceZoneId(data.zoneId)
                } 
                billableItem = BillableItem.findByReferenceItemName("snapShot")
            } 

            if(billableItem == null || billableItem == "null" || billableItem == "") {

            } else {
                newInvoiceItem.taxPercent = billableItem.tax.percentage
            }

            //usage amount
            newInvoiceItem.usageAmount = Math.ceil(offeringUsage.cost * 100d) / 100d;

            newInvoiceItem.billableItem = billableItem;

            // usage unit
            newInvoiceItem.usageUnits = offeringUsage.hours;     


            //discount amount
            double discountAmount = (newInvoiceItem.usageAmount/100)*newInvoiceItem.discountPercent
            newInvoiceItem.discountAmount = discountAmount;

            // sub total
            double subTotal = newInvoiceItem.usageAmount - discountAmount


            if(newInvoiceItem.taxPercent == null || newInvoiceItem.taxPercent == "null") {

            } else {
                // tax amount
                double taxAmount = (subTotal/100)* newInvoiceItem.taxPercent
                newInvoiceItem.taxAmount = taxAmount;     

            }

            //total amount
            newInvoiceItem.totalAmount =  Math.ceil((subTotal + newInvoiceItem.taxAmount) * 100d) / 100d;


            newInvoiceItem.referenceItemId = offeringUsage.referenceId

            if(billableItem == null || billableItem == "null" || billableItem == "") {

            } else {
                if(billableItem.enabled == true || billableItem.enabled == "true") {
                    newInvoiceItem.save(flush: true);

                    if (!newInvoiceItem.save()) {
            //            newInvoiceItem.errors.allErrors.each { Console.print(it) }
                    }  
                }
            }

            // save usage record.
//            offeringUsage.save(flush: true);
//            if (!offeringUsage.save()) {
//                offeringUsage.errors.allErrors.each { Console.print(it) }
//            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw ex;    
        }   
    }
    
    
    def updateInvoice(account, invoice) {
        try {  
            
            if(invoice) {
                invoice = invoice
            } else {
                invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
            }
                        

            if(invoice) {
                def invoiceItemList = InvoiceItem.findAllWhere(invoice : invoice)

                def c = InvoiceItem.createCriteria()
                def results = c.list {
                    eq("invoice", invoice)
                    and {
                        ne("billableItem", BillableItem.get(6))
                    }
                    projections {
                         sum("totalAmount")
                     }
                }
                if(results[0] == null || results[0] == "null") {
                    invoice.currentDue =  0
                } else {
                    invoice.currentDue =  Math.ceil(results[0] * 100d) / 100d;
                }

                //add discount for user
                def discountBillableItem = InvoiceItem.findByBillableItem(BillableItem.get(6))
        
                if(discountBillableItem) {
                    
                    if(invoice.currentDue > 0) {
                        discountBillableItem.usageUnits = invoice.currentDue;

                        double discountAmount = (discountBillableItem.usageUnits/100)* discountBillableItem.usageUnitPrice
                        discountBillableItem.usageAmount = Math.ceil(-1 * discountAmount * 100d) / 100d;     

                        discountBillableItem.totalAmount = Math.ceil(discountBillableItem.usageAmount * 100d) / 100d;  

                        discountBillableItem.save(flush: true);


                        double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - invoice.payment

                        invoice.totalAmount =  Math.ceil((subTotal) * 100d) / 100d;
                        account.totalPayable = invoice.totalAmount
                    } else {
                        discountBillableItem.usageUnits = 0;

                        double discountAmount = (discountBillableItem.usageUnits/100)* discountBillableItem.usageUnitPrice
                        discountBillableItem.usageAmount = Math.ceil(-1 * discountAmount * 100d) / 100d;     

                        discountBillableItem.totalAmount = Math.ceil(discountBillableItem.usageAmount * 100d) / 100d;  

                        discountBillableItem.save(flush: true);


                        double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - invoice.payment

                        invoice.totalAmount =  Math.ceil((subTotal) * 100d) / 100d;
                        account.totalPayable = invoice.totalAmount
                    }
                } else {
                    double subTotal = (invoice.currentDue + invoice.previousBalance + invoice.refundAmount) - invoice.payment

                    invoice.totalAmount =  Math.ceil(subTotal * 100d) / 100d;
                    account.totalPayable = invoice.totalAmount
                }
            }
            account.save(flush: true);
            if (!account.save()) {
                account.errors.allErrors.each { Console.print(it) }
            }
            invoiceService.processForCreditLimit(account)
                        
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw ex;    
        }   
    }
    
    
    
    def genRecord() {
        
        
//        HashMap<String,String> optional = new HashMap<String,String>();
//        optional.put("account", "gowtham");
//        optional.put("domainid", "b31021ee-31d4-11e3-bebb-001676787aec");
////       //  optional.put("page", "5000");
//         
//        
//        def response = cloudStackUsageServer().listUsageRecords("2013-10-18", "2013-10-16", optional);
        
        
        def response = cloudStackUsageServer().generateUsageRecords("2013-10-30", "2013-10-01", null);
        
        
    }
}
