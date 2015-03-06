package com.assistanz.fogpanel

import grails.converters.deep.JSON
import com.assistanz.fogpanel.GeneralConstants;
import java.text.DateFormat
import java.text.SimpleDateFormat
import org.apache.commons.logging.LogFactory;

class DiscountService {
    
    def springSecurityService;
    private static final log = LogFactory.getLog(this) 

    def list(name) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm"); 
        if(name == "null" || name == null) {    
            def discount = Discount.findAllWhere(deleted:false);
            ArrayList<ArrayList<String>> discountList = new ArrayList<ArrayList<String>>();    
            for(int i=0; i < discount.size(); i++) { 
                def data = discount[i]; 
               HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("id", data.id);
                item.put("discountValue", data.value);
                item.put("startDate", dateFormat.format(data.startDate).toString());
                item.put("endDate", dateFormat.format(data.endDate).toString());
                item.put("type", data.type.name());
                item.put("name", data.discountName);
                item.put("isAllPlan", data.isAllPlan);
                item.put("isAllUser", data.isAllUser);
                item.put("billingCycles", data.billingCycles);
                int[] plans = new int[data.computingOffers.size()];
                String[] planName = new int[data.computingOffers.size()];
                def j= 0
                for(Iterator k = data.computingOffers.iterator();k.hasNext();) {   
                    def computeOffer = k.next(); 
                        plans[j] = computeOffer.id;
                        planName[j] = computeOffer.name;
                        j++
                }
                item.put("plans", plans.toString().substring(1,plans.toString().length()-1));
                item.put("planNames", planName.toString().substring(1,planName.toString().length()-1));
                int[] users = new int[data.accounts.size()];
                String[] userName = new int[data.accounts.size()];
                def count = 0
                for(Iterator k = data.accounts.iterator();k.hasNext();) {   
                    def account = k.next(); 
                        users[count] = account.id;
                        userName[count] = account.accountIdentifier;
                        count++
                }
                item.put("users", users.toString().substring(1,users.toString().length()-1));
                item.put("userName", userName.toString().substring(1,userName.toString().length()-1));
                if(data.isAll == true) {
                    item.put("applyTo","ALL");
                } else if(data.isAll == false) {
                    item.put("applyTo", "SELECTIVE");
                }
                discountList.add(item); 
            }
            return discountList; 
        } else if(name != "null" || name != null) {
            def result = Discount.findWhere(discountName: name, deleted:false);
            if(result) {
               return [GeneralConstants.RESULT_FAILURE]
            } else {
                 return [GeneralConstants.RESULT_SUCCESS]
            }
        }
    }
    
    def validateDateRange(String requestBody) {
        try {  
            def requestData = JSON.parse(requestBody)
            def discount;
            def discountCriteria = Discount.createCriteria()
            DateFormat formater = new SimpleDateFormat("dd/MM/yyyy"); 
            Date startDate = formater.parse(requestData.startDate);
            Date endDate = formater.parse(requestData.endDate);
            
            ArrayList discountResult = new ArrayList();   
            ArrayList nonDiscountAccountList = new ArrayList();   
            ArrayList nonDiscountComputeOfferList = new ArrayList();   
            HashMap item = new HashMap(); 
            HashMap<String,String> discountedAccountList = new HashMap<String,String>();
            HashMap<String,String> discountedComputeOfferList = new HashMap<String,String>();
            
            
            discount = Discount.withCriteria {
               eq("type", DiscountType.valueOf(requestData.type))
               and{
                    eq("deleted", false)  
                    and {
                        if(requestData.type == "PARTICULAR") {
                          le("startDate", startDate) and { ge("endDate", startDate)  }  
                        }
                    }
                }
            }
            def discountList = Discount.findAll()
            if(discountList.size() == 0) {
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                discountResult.add(item);  
            } else {
                if(requestData.type == "USER") {
                    
                    if(discount.size()== 0) {
                        item.put("result", GeneralConstants.RESULT_SUCCESS);
                        discountResult.add(item);  
                    } else {
                        for(int i=0; i < discount.size(); i++) {
                            def discountItem = discount[i]; 
                            if(discountItem.isAll == true || discountItem.isAll == "true") {
                                HashMap<String,String> list = new HashMap<String,String>();
                                list.put("result", GeneralConstants.RESULT_FAILURE); 
                                list.put("message", "all account are added in this start and end date");
                                discountResult.add(list);  
                                return discountResult; 
                            } else {
                                item.put("result", GeneralConstants.RESULT_AVAILIABLE); 
                                for(Iterator k = discountItem.accounts.iterator();k.hasNext();) { 
                                    def account = k.next(); 
                                    discountedAccountList.put(account.id, "accountId");
                                }
                            }
                        }
                        def allAccount = Account.withCriteria {
                            ne("id", 10001.toLong())
                        }
                        for(int j=0; j < allAccount.size(); j++) { 
                            def account = allAccount[j]; 
                            if(account.status.name() == "ACTIVE") {
                                boolean blnExists = discountedAccountList.containsKey(account.id);
                                if(blnExists.toString() == "false" || blnExists == false) {
                                    HashMap<String,String> accountItem = new HashMap<String,String>(); 
                                    accountItem.put("id", account.id);
                                    accountItem.put("userName", account.accountIdentifier);
                                    if(Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])) {
                                        nonDiscountAccountList.add(accountItem);
                                    }
                                }
                            }
                        }
                        item.put("accounts", nonDiscountAccountList);  
                        discountResult.add(item); 
                    }
                }
                if(requestData.type == "PARTICULAR") {
                    if(discount.size()== 0) {
                        item.put("result", GeneralConstants.RESULT_SUCCESS);
                        discountResult.add(item);  
                    } else {
                        for(int i=0; i < discount.size(); i++) {
                            def discountItem = discount[i]; 
                            if(discountItem.isAll == true || discountItem.isAll == "true") {
                                HashMap<String,String> list = new HashMap<String,String>();
                                list.put("result", GeneralConstants.RESULT_FAILURE); 
                                list.put("message", "all account are added in this start and end date");
                                discountResult.add(list); 
                                return discountResult; 
                            } else {
                                if(discountItem.isAllPlan == true || discountItem.isAllPlan == "true") {
                                    item.put("isAllPlan", "true"); 
                                } else {
                                    item.put("isAllPlan", "false"); 
                                    item.put("result", GeneralConstants.RESULT_AVAILIABLE); 
                                    for(Iterator k = discountItem.computingOffers.iterator();k.hasNext();) { 
                                        def computerOffer = k.next(); 
                                        discountedComputeOfferList.put(computerOffer.id, "computerOffer");
                                    }
                                }
                                if(discountItem.isAllUser == true || discountItem.isAllUser == "true") {
                                    item.put("isAllUser", "true"); 
                                } else {
                                    item.put("isAllUser", "false"); 
                                    boolean blnExists = item.containsValue(GeneralConstants.RESULT_AVAILIABLE);
                                    if(blnExists.toString() == "false" || blnExists == false) {
                                        item.put("result", GeneralConstants.RESULT_AVAILIABLE); 
                                    }
                                    for(Iterator k = discountItem.accounts.iterator();k.hasNext();) { 
                                        def account = k.next(); 
                                        discountedAccountList.put(account.id, "accountId");
                                    }
                                }
                            }
                        }
                        def allComputerOffer = ComputingOffer.findAllWhere(deleted: false);
                        for(int j=0; j < allComputerOffer.size(); j++) { 
                            def compute = allComputerOffer[j]; 
                            boolean blnExists = discountedComputeOfferList.containsKey(compute.id);
                            if(blnExists.toString() == "false" || blnExists == false) {
                                HashMap<String,String> computeItem = new HashMap<String,String>(); 
                                computeItem.put("id", compute.id);
                                computeItem.put("name", compute.name);
                                computeItem.put("aliasZoneName", compute.zone.aliasName);
                                nonDiscountComputeOfferList.add(computeItem);
                            }
                        }
                        item.put("computeOffer", nonDiscountComputeOfferList);  
                        def allAccount = Account.withCriteria {
                            ne("id", 10001.toLong())
                        }
                        for(int j=0; j < allAccount.size(); j++) { 
                            def account = allAccount[j]; 
                            if(account.status.name() == "ACTIVE") {
                                boolean blnExists = discountedAccountList.containsKey(account.id);
                                if(blnExists.toString() == "false" || blnExists == false) {
                                    HashMap<String,String> accountItem = new HashMap<String,String>(); 
                                    accountItem.put("id", account.id);
                                    accountItem.put("userName", account.accountIdentifier);
                                    nonDiscountAccountList.add(accountItem);
                                }
                            }
                        }
                        item.put("accounts", nonDiscountAccountList);
                        discountResult.add(item); 
                    }
                }

            }
            return discountResult; 
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        }
    }
    
    def validateDiscount(String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            def discount;
            def discountCriteria = Discount.createCriteria()
            DateFormat formater = new SimpleDateFormat("dd/MM/yyyy"); 
            Date date = formater.parse(requestData.date);
            
            ArrayList discountResult = new ArrayList();   
            ArrayList nonDiscountAccountList = new ArrayList();   
            ArrayList nonDiscountComputeOfferList = new ArrayList();   
            HashMap item = new HashMap(); 
            HashMap<String,String> discountedAccountList = new HashMap<String,String>();
            HashMap<String,String> discountedComputeOfferList = new HashMap<String,String>();
            
            
            discount = Discount.withCriteria {
               eq("type", DiscountType.valueOf(requestData.type))
               and{
                    eq("deleted", false)  
                    and {
                        if(requestData.type == "PARTICULAR") {
                          le("startDate", startDate) and { ge("endDate", startDate)  }  
                        }
                    }
                }
            }
            
            def discountList = Discount.findAll()
            
            if(discountList.size() == 0) {
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                discountResult.add(item);  
            } else {
                if(requestData.type == "USER") {
                    if(discount.size()== 0) {
                        item.put("result", GeneralConstants.RESULT_SUCCESS);
                        discountResult.add(item);  
                    } else {
                        for(int i=0; i < discount.size(); i++) {
                            def discountItem = discount[i]; 
                            if(discountItem.isAll == true || discountItem.isAll == "true") {
                                item.put("result", GeneralConstants.RESULT_FAILURE); 
                                item.put("message", "all account are added in this start and end date");
                                discountResult.add(item);  
                                return discountResult; 
                            } else {
                                item.put("result", GeneralConstants.RESULT_AVAILIABLE); 
                                for(Iterator k = discountItem.accounts.iterator();k.hasNext();) { 
                                    def account = k.next(); 
                                    discountedAccountList.put(account.id, "accountId");
                                }
                            }
                        }
                        def allAccount = Account.withCriteria {
                            ne("id", 10001.toLong())
                        }
                        for(int j=0; j < allAccount.size(); j++) { 
                            def account = allAccount[j]; 
                            boolean blnExists = discountedAccountList.containsKey(account.id);
                            if(blnExists.toString() == "false" || blnExists == false) {
                                HashMap<String,String> accountItem = new HashMap<String,String>(); 
                                accountItem.put("id", account.id);
                                accountItem.put("userName", account.accountIdentifier);
                                if(Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])) {
                                    nonDiscountAccountList.add(accountItem);
                                }
                            }
                        }
                        item.put("accounts", nonDiscountAccountList);  
                        discountResult.add(item); 
                    }

                }
                if(requestData.type == "PARTICULAR") {
                    if(discount.size()== 0) {
                        item.put("result", GeneralConstants.RESULT_SUCCESS);
                        discountResult.add(item);  
                    } else {
                        for(int i=0; i < discount.size(); i++) {
                            def discountItem = discount[i]; 
                            if(discountItem.isAll == true || discountItem.isAll == "true") {
                                item.put("result", GeneralConstants.RESULT_FAILURE); 
                                item.put("message", "all account and all plan are added in this start and end date");
                                discountResult.add(item);  
                                return discountResult; 
                            } else {
                                if(discountItem.isAllPlan == true || discountItem.isAllPlan == "true") {
                                    item.put("isAllPlan", "true"); 
                                } else {
                                    item.put("result", GeneralConstants.RESULT_AVAILIABLE); 
                                    for(Iterator k = discountItem.computingOffers.iterator();k.hasNext();) { 
                                        def computerOffer = k.next(); 
                                        discountedComputeOfferList.put(computerOffer.id, "computerOffer");
                                    }
                                }
                                if(discountItem.isAllUser == true || discountItem.isAllUser == "true") {
                                    item.put("isAllUser", "true"); 
                                } else {
                                    boolean blnExists = item.containsValue(GeneralConstants.RESULT_AVAILIABLE);
                                    if(blnExists.toString() == "false" || blnExists == false) {
                                        item.put("result", GeneralConstants.RESULT_AVAILIABLE); 
                                    }
                                    for(Iterator k = discountItem.accounts.iterator();k.hasNext();) { 
                                        def account = k.next(); 
                                        discountedAccountList.put(account.id, "accountId");
                                    }
                                }
                            }
                        }
                        def allComputerOffer = ComputingOffer.findAllWhere(deleted: false);
                        for(int j=0; j < allComputerOffer.size(); j++) { 
                            def compute = allComputerOffer[j]; 
                            boolean blnExists = discountedComputeOfferList.containsKey(compute.id);
                            if(blnExists.toString() == "false" || blnExists == false) {
                                HashMap<String,String> computeItem = new HashMap<String,String>(); 
                                computeItem.put("id", compute.id);
                                computeItem.put("name", compute.name);
                                computeItem.put("aliasZoneName", compute.zone.aliasName);
                                nonDiscountComputeOfferList.add(computeItem);
                            }
                        }
                        item.put("computeOffer", nonDiscountComputeOfferList);  
                        def allAccount = Account.withCriteria {
                            ne("id", 10001.toLong())
                        }
                        for(int j=0; j < allAccount.size(); j++) { 
                            def account = allAccount[j]; 
                            boolean blnExists = discountedAccountList.containsKey(account.id);
                            if(blnExists.toString() == "false" || blnExists == false) {
                                HashMap<String,String> accountItem = new HashMap<String,String>(); 
                                accountItem.put("id", account.id);
                                accountItem.put("userName", account.accountIdentifier);
                                nonDiscountAccountList.add(accountItem);
                            }
                        }
                        item.put("accounts", nonDiscountAccountList);
                        discountResult.add(item); 
                    }
                } 
            }
            return discountResult; 
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        }
    }
    
    def create(String requestBody) {
        try {  
            def requestData = JSON.parse(requestBody)
            def discount = new Discount();
            discount.type = requestData.type
            if(requestData.type == "PARTICULAR") {
                discount.subType = requestData.subType
                discount.isAllPlan = requestData.isAllPlan
            } else if(requestData.type == "USER") {
                discount.subType = requestData.subType
                discount.isAllPlan = requestData.isAllPlan
            } 
            discount.isAllUser = requestData.isAllUser
            discount.value = requestData.discountValue
            discount.discountName = requestData.discountName
            discount.deleted = false
            discount.isAllUser = requestData.isAllUser
            discount.billingCycles = requestData.billingCycles
                        
            DateFormat formater = new SimpleDateFormat("dd/MM/yyyy"); 
            if(requestData.endDate == "NaN/NaN/0NaN") {

            } else {
                Date endDate = formater.parse(requestData.endDate);
                discount.endDate = endDate;
            }
            if(requestData.startDate == "NaN/NaN/0NaN") {

            } else {
                Date startDate = formater.parse(requestData.startDate);
                discount.startDate = startDate;
            }

            if(requestData.applyTo == "ALL") {
                discount.isAll = true;
                addDiscountForAll(discount, requestData);
            } else if(requestData.applyTo == "SELECTIVE") {
                if(requestData.type == "USER" && (requestData.isAllUser == true || requestData.isAllUser == "true")) {
                    discount.isAll = true;
                    addDiscountForAll(discount, requestData);
                } else if(requestData.type == "PARTICULAR" && requestData.isAllUser == true && requestData.isAllPlan == true) {
                    discount.isAll = true;
                    addDiscountForAll(discount, requestData);
                } else {
                    discount.isAll = false;
                    addDiscountForSelective(discount, requestData)
                }
                
            }
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        }   
    }
    
    def addDiscountForAll(discount, requestData) {
        try {
            
            def user = springSecurityService.currentUser
            
            
            def allAccount = Account.withCriteria {
                ne("id", 10001.toLong())
            }
            def discountBillableItem = BillableItem.get(6)
            for(def account: allAccount) {
                def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                if(invoice) {
                    if(discountBillableItem.enabled == true || discountBillableItem.enabled == "true") {
                        def invoiceItem = new InvoiceItem()
                        invoiceItem.billableItem = discountBillableItem
                        invoiceItem.invoice = invoice
                        invoiceItem.taxPercent = 0.0
                        invoiceItem.discount = discount
        //                                invoiceItem.discountPercent = userDiscount.value
                        invoiceItem.usageUnitPrice = discount.value

                        invoiceItem.referenceItemId = discount.id.toString()
                        invoiceItem.referenceItemName = discount.discountName
                        invoiceItem.save()
                    }
                }
            }
            
            discount.save(flush: true);
            if (discount.hasErrors()) {
                throw new ValidationException(discount.errors.allErrors);
            }
            ArrayList<ArrayList<String>> discountList = new ArrayList<ArrayList<String>>();       
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("id", discount.id);
            item.put("discountValue", discount.value);
            item.put("startDate", discount.startDate);
            item.put("name", discount.discountName);
            item.put("endDate", discount.endDate);
            item.put("type", discount.type.name());
            item.put("plans", "");
            item.put("users", "");
            if(discount.isAll == true) {
                item.put("applyTo","ALL");
            } else if(discount.isAll == false) {
                item.put("applyTo", "SELECTIVE");
            }
            discountList.add(item); 
            log.info("Discount:${discount.id} added  by user${user.id}");
            return discountList; 
            } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } 
    }
    
    def addDiscountForSelective(discount, requestData) {
        try {
            
            def user = springSecurityService.currentUser
            
            String[] users;
            String[] plans;
            if(requestData.type == "USER") {
                users = requestData.discountUsers.split(",");
                for(def i=0; i < users.length; i++) {
                    def account = Account.get(Integer.parseInt(users[i]))
                    discount.addToAccounts(account);  
                    def discountBillableItem = BillableItem.get(6)
                    def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                    if(invoice) {
                        if(discountBillableItem.enabled == true || discountBillableItem.enabled == "true") {
                            def invoiceItem = new InvoiceItem()
                            invoiceItem.billableItem = discountBillableItem
                            invoiceItem.invoice = invoice
                            invoiceItem.taxPercent = 0.0
                            invoiceItem.discount = discount
            //                                invoiceItem.discountPercent = userDiscount.value
                            invoiceItem.usageUnitPrice = discount.value

                            invoiceItem.referenceItemId = discount.id.toString()
                            invoiceItem.referenceItemName = discount.discountName
                            invoiceItem.save()
                        }
                    }                    
                    discount.save(flush: true);
                }
            } else if(requestData.type == "PARTICULAR") {
                if(requestData.applyTo == "SELECTIVE") {
                    if((requestData.isAllPlan == true || requestData.isAllPlan == "true") && (requestData.isAllUser == true || requestData.isAllUser == "true")) {
                        discount.isAll = true;
                    } else {
                        if(requestData.isAllPlan == false || requestData.isAllPlan == "false") {
                            plans = requestData.discountPlans.split(",");
                            for(def j=0; j < plans.length; j++) {
                                def computingOffer = ComputingOffer.get(Integer.parseInt(plans[j]))
                                discount.addToComputingOffers(computingOffer);  
                            } 
                        }
                        if(requestData.isAllUser == false || requestData.isAllUser == "false") {
                            users = requestData.discountUsers.split(",");
                            for(def i=0; i < users.length; i++) {
                                def account = Account.get(Integer.parseInt(users[i]))
                                discount.addToAccounts(account);  
                            }
                        }
                    }
                } 
            }   
            discount.save(flush: true, failOnError: true);
            if (discount.hasErrors()) {
                throw new ValidationException(discount.errors.allErrors);
            }
            ArrayList<ArrayList<String>> discountList = new ArrayList<ArrayList<String>>();       
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("id", discount.id);
            item.put("discountValue", discount.value);
            item.put("startDate", discount.startDate);
            item.put("endDate", discount.endDate);
            item.put("type", discount.type.name());
            item.put("name", discount.discountName);
//            if(requestData.type == "PARTICULAR" &&(requestData.isAllPlan == false || requestData.isAllPlan == "false")) {
//                int[] plansList = new int[discount.computingOffers.size()];
//                def j= 0
//                for(Iterator k = discount.computingOffers.iterator();k.hasNext();) {   
//                    def computeOffer = k.next(); 
//                        plansList[j] = computeOffer.id;
//                        j++
//                }
//                item.put("plans", plans.toString().substring(1,plans.toString().length()-1));
//            } else{
                item.put("plans", "");
//            }
//            if(requestData.isAllUser == false || requestData.isAllUser == "false") {
//                int[] usersList= new int[discount.accounts.size()];
//                def count = 0
//                for(Iterator k = discount.accounts.iterator();k.hasNext();) {   
//                    def account = k.next(); 
//                        usersList[count] = account.id;
//                        count++
//                }
//                item.put("users", users.toString().substring(1,users.toString().length()-1));
//            } else{
               item.put("users", ""); 
//            }
            if(discount.isAll == true) {
                item.put("applyTo","ALL");
            } else if(discount.isAll == false) {
                item.put("applyTo", "SELECTIVE");
            }
            discountList.add(item);  
            log.info("Discount:${discount.id} added  by user${user.id}");
            return discountList; 
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch(Exception ex) {
            [ex] as JSON            
        }
    }
    
    def getUser(requestBody) {
        
        def requestData = JSON.parse(requestBody)
        def discount;
        def discountCriteria = Discount.createCriteria()
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy"); 
        Date startDate = formater.parse(requestData.startDate);
        Date endDate = formater.parse(requestData.endDate);
        ArrayList<ArrayList<String>> accountList = new ArrayList<ArrayList<String>>();  

        HashMap<String,String> discountedAccountList = new HashMap<String,String>();

        String[] plans;
        plans = requestData.plans.split(",");

        discount = Discount.withCriteria {
           eq("type", DiscountType.valueOf(requestData.type))
           and{
                eq("deleted", false)  
                and {
                    le("startDate", startDate) and { ge("endDate", startDate)  }
                }
            }
        }
        def discountList = Discount.findAll()
        if(discountList.size() == 0) {
            def accounts = Account.withCriteria {
                ne("id", 10001.toLong())
            }
            for(def account: accounts) {
                if(account.status.name() == "ACTIVE") {
                    HashMap<String,String> item = new HashMap<String,String>(); 
                    item.put("id", account.id);
                    item.put("userName", account.accountIdentifier);
                    accountList.add(item);
                }
            }
            return accountList; 
        } else {
             if(discount.size()== 0) {
                def accounts = Account.withCriteria {
                    ne("id", 10001.toLong())
                }
                for(def account: accounts) {
                    if(account.status.name() == "ACTIVE") {
                        HashMap<String,String> item = new HashMap<String,String>(); 
                        item.put("id", account.id);
                        item.put("userName", account.accountIdentifier);
                        accountList.add(item);
                    }
                }
                return accountList;  
            } else {
                for(int i=0; i < discount.size(); i++) {
                    def discountItem = discount[i]; 
                    if(discountItem.isAll == true || discountItem.isAll == "true") {

                    } else {
                        for(Iterator k = discountItem.computingOffers.iterator();k.hasNext();) { 
                            def computerOffer = k.next(); 
                            for(def j=0; j < plans.length; j++) {
                                def computingOfferGivenPlan = ComputingOffer.get(Integer.parseInt(plans[j]))
                                if(computerOffer.id == computingOfferGivenPlan.id) {
                                    for(Iterator acc = discountItem.accounts.iterator();acc.hasNext();) { 
                                        def disAccount = acc.next(); 
                                        boolean blnExists = discountedAccountList.containsKey(disAccount.id);
                                        if(blnExists.toString() == "false" || blnExists == false) {
                                            discountedAccountList.put(disAccount.id, "accountId");
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                def accounts = Account.withCriteria {
                    ne("id", 10001.toLong())
                }
                for(def account: accounts) {
                    if(account.status.name() == "ACTIVE") {
                        boolean blnExists = discountedAccountList.containsKey(account.id);
                        if(blnExists.toString() == "false" || blnExists == false) {
                            HashMap<String,String> item = new HashMap<String,String>(); 
                            item.put("id", account.id);
                            item.put("userName", account.accountIdentifier);
                            accountList.add(item);
                        }
                    }
                }
                return accountList;  
            }
        }
    }
    
    def delete(id) {
      try {  
          
        def user = springSecurityService.currentUser
            def discount = Discount.get(id);
            discount.deleted = true;
            discount.save(flush: true);
            log.info("Discount:${discount.id} deleted by user${user.id}");
            if (discount.hasErrors()) {
                throw new ValidationException(discount.errors.allErrors);
            }
        } catch(Exception ex) {
            [ex] as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } 
    }
}
