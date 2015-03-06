package com.assistanz.fogpanel

import grails.converters.JSON
import java.text.SimpleDateFormat
import java.text.DateFormat
//import org.json.simple.JSONObject;
import org.springframework.context.MessageSource
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class ReportService {
    MessageSource messageSource;
    def springSecurityService;
    def billableItemChartReport(billableItemData) {
        Date fromDate;
        Date toDate;
        def invoiceItemList;
        def count = 0;
//        JSONObject json = new JSONObject();
        HashMap map = new HashMap();
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        
        if(billableItemData.forDateRange != "ALL") {
            fromDate = givenDateFormater.parse(billableItemData.fromDate);
            Date givenToDate = givenDateFormater.parse(billableItemData.toDate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            toDate = toDateCalendar.getTime() 
        }
        
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
             
        String[] billableItemArray;
        String[] billableItemAmountArray;
        def billableItemList;
        if(billableItemData.forZone == "NONZONE") {
            billableItemList = BillableItem.findAllWhere(hasZone: false)
            billableItemArray = new String[billableItemList.size()]
        } else {
            billableItemList = BillableItem.findAllWhere(hasZone: true)
            billableItemArray = new String[billableItemList.size()]
        }
        def arrayCount = 0;
        def arrayAmountCount = 0;
        def planList;
                
        def invoiceList = Invoice.withCriteria {
            if(billableItemData.forAccount != "ALL") {
              eq("account", Account.get(billableItemData.forAccount));  
            } else if(billableItemData.forDateRange != "ALL") {
               ge("date", fromDate) and { le("date", toDate) }    
            }
        }
        if(billableItemData.forBillableItem == "ALL") {
           billableItemAmountArray = new String[billableItemList.size()]
           for(def billableItem : billableItemList) {
               billableItemArray[arrayCount] = billableItem.name; 
               def totalAmount = 0;
               for(def invoice: invoiceList) {
                   invoiceItemList = InvoiceItem.withCriteria {  
                       eq("invoice", invoice) 
                       and{
                         eq("billableItem", billableItem) 
                           if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                               projections {
                                   sum("totalAmount")
                               }
                           } else{
                                eq("zone", Zone.get(billableItemData.forZone));  
                                projections {
                                    sum("totalAmount")
                                }

                           }  
                       } 
                   } 
                   if(invoiceItemList[0] == null || invoiceItemList[0] == "null") {
                       totalAmount += 0;
                   } else {
                       totalAmount += invoiceItemList[0];
                   }

               }
               billableItemAmountArray[arrayAmountCount] = totalAmount; 
               arrayAmountCount += 1
               arrayCount +=1
           }
           HashMap itemData = new HashMap()
           itemData.put("billableItems", billableItemArray)
           itemData.put("billableItemAmounts", billableItemAmountArray)
           invoiceItemListArray.add(itemData) 
//           Console.print("invoiceItemListArray" + invoiceItemListArray);
           return invoiceItemListArray;

       } else {
            def selectedBillableItems = billableItemData.billableItemList.split(",");
            billableItemAmountArray = new String[selectedBillableItems.length]
            for(def i=0; i < selectedBillableItems.length; i++) {
                    def billableItem = BillableItem.findByName(selectedBillableItems[i])
                    billableItemArray[i] = selectedBillableItems[i];
                    def totalAmount = 0;
                    if(selectedBillableItems.length == 1) {
                        if(selectedBillableItems[i] == "ComputingOffer" || selectedBillableItems[i] == "StoppedInstance" || selectedBillableItems[i] == "SetupCostForInstance" || selectedBillableItems[i] == "Discount") {
                            if(billableItemData.plan !="ALL") {
                               planList = ComputingOffer.withCriteria {  
                                     eq("offerReferenceId", billableItemData.plan); 
                                }
                                return getComputePlanCostForAccount(planList, billableItem, billableItemData) 
                            } else {
                                planList = ComputingOffer.withCriteria {  
                                    if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                                    } else{
                                        eq("zone", Zone.get(billableItemData.forZone));  

                                    }  
                                }
                                return getComputePlanCost(planList, invoiceList, billableItem, billableItemData)
                            }
                        } else if(selectedBillableItems[i] == "DiskOffer") {
                            if(billableItemData.plan !="ALL") {
                                planList = DiskOffer.withCriteria {  
                                     eq("diskOfferReferenceId", billableItemData.plan); 
                                }
                                return getDiskPlanCostForAccount(planList, billableItem, billableItemData)
                            } else {
                                planList = DiskOffer.withCriteria {  
                                    if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                                    } else{
                                        eq("zone", Zone.get(billableItemData.forZone));  

                                    }  
                                }
                                return getDiskPlanCost(planList, invoiceList, billableItem, billableItemData)
                            }
                        } else {
                            for(def invoice: invoiceList) {
                                invoiceItemList = InvoiceItem.withCriteria {  
                                    eq("invoice", invoice) 
                                    and{
                                      eq("billableItem", billableItem) 
                                        if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                                            projections {
                                                sum("totalAmount")
                                            }
                                        } else{
                                             eq("zone", Zone.get(billableItemData.forZone));  
                                             projections {
                                                sum("totalAmount")
                                             }

                                        }  
                                    } 
                                } 
                                if(invoiceItemList[0] == null || invoiceItemList[0] == "null") {
                                    totalAmount += 0;
                                } else {
                                    totalAmount += invoiceItemList[0];
                                }
                                
                            }
                            billableItemAmountArray[arrayAmountCount] = totalAmount; 
                            arrayAmountCount += 1
                            arrayCount +=1
                        }
                    } else {
                        
                        for(def invoice: invoiceList) {
                            invoiceItemList = InvoiceItem.withCriteria {  
                                eq("invoice", invoice) 
                                and{
                                  eq("billableItem", billableItem) 
                                    if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                                        projections {
                                            sum("totalAmount")
                                        }
                                    } else{
                                         eq("zone", Zone.get(billableItemData.forZone));  
                                         projections {
                                            sum("totalAmount")
                                         }

                                    }  
                                } 
                            } 
                            if(invoiceItemList[0] == null || invoiceItemList[0] == "null") {
                                totalAmount += 0;
                            } else {
                                totalAmount += invoiceItemList[0];
                            }
                            
                        }
                        billableItemAmountArray[arrayAmountCount] = totalAmount; 
                        arrayAmountCount += 1
                        arrayCount +=1
                    }
                }
                HashMap itemData = new HashMap()
                itemData.put("billableItems", billableItemArray)
                itemData.put("billableItemAmounts", billableItemAmountArray)
                invoiceItemListArray.add(itemData) 
//                Console.print("invoiceItemListArray" + invoiceItemListArray);
                return invoiceItemListArray;
       }
    }
    
    
     def billableItemCountChartReport(billableItemData) {
        def user = springSecurityService.currentUser
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        
        Date fromDate;
        Date toDate;
        def invoiceItemList;
        def count = 0;
        def planList;
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Index No.")
        tableThArray.add("Account")
        tableThArray.add("Billable Item")
        tableThArray.add("Item Name")
        tableThArray.add("Item Reference Id")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
        if(billableItemData.forDateRange == "SELECTIVE") {
            fromDate = givenDateFormater.parse(billableItemData.fromDate);
            Date givenToDate = givenDateFormater.parse(billableItemData.toDate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            toDate = toDateCalendar.getTime()
        }
        
        
        def accountCriteria = Account.createCriteria()
        
        def invoiceList = Invoice.withCriteria {
            if(billableItemData.forDateRange != "ALL") {
                ge("date", fromDate) and { le("date", toDate) }    
            }
            if(billableItemData.forAccount != "ALL") {
                eq("account", Account.get(billableItemData.forAccount));  
            }
        }
                
        for(def invoice: invoiceList) {
            Date inputdate = formater2.parse(invoice.date.toString());
            String outputDate = outPutFormater.format(inputdate);
            if(billableItemData.forBillableItem == "ALL") {
                def billableItemList;
                if(billableItemData.forZone == "NONZONE") {
                    billableItemList = BillableItem.findAllWhere(hasZone: false)
                } else {
                    billableItemList = BillableItem.findAllWhere(hasZone: true)
                }
                for(def billableItem : billableItemList) {
                    invoiceItemList = InvoiceItem.withCriteria {
                        eq("invoice", invoice);
                        and {
                            eq("billableItem", billableItem) 
                            if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                            } else {
                                eq("zone", Zone.get(billableItemData.forZone)); 
                            }
                        }
                    }
                    for(def invoiceItem: invoiceItemList) {
                        if(invoiceItem.totalAmount != 0) {
                            count = count +1;
                            HashMap item = new HashMap();
                            item.put("count", count);
                            item.put("account", invoice.account.accountIdentifier);
                            item.put("billableItem", messageSource.getMessage(billableItem.name, null, new Locale(language)));
                            item.put("itemReferenceId", invoiceItem.referenceItemId);
                            item.put("date", outputDate);
                            item.put("plan", invoiceItem.referencePlanId);
                            item.put("totalAmount", invoiceItem.totalAmount);
                            if(invoiceItem.zone) {
                              item.put("zone", invoiceItem.zone.referenceZoneId);  
                            }
                            if(invoiceItem.referenceItemName == "VirtualMachine") {
                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                                if(vm) {
                                    item.put("itemName", vm.displayName);
                                    invoiceItemListArray.add(item) 
                                }
                                
                            } else if(invoiceItem.referenceItemName =="Volume") {
                                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                                if(volume) {
                                    item.put("itemName", volume.name);
                                    invoiceItemListArray.add(item)
                                }
                            } else if(invoiceItem.referenceItemName =="SetupCost") {
                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                                if(vm) {
                                    item.put("itemName", vm.displayName);
                                    invoiceItemListArray.add(item); 
                                }
                            } else if(invoiceItem.referenceItemName == "Template") {
                                def template = Template.findByTemplateReferenceId(invoiceItem.referenceItemId);
                                if(template) {
                                    item.put("itemName", template.name);
                                    invoiceItemListArray.add(item)   
                                }
                                
                            } else if(invoiceItem.referenceItemName == "Snapshot") {
                                 def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
                                 if(snapshot) {
                                    item.put("itemName", snapshot.name);
                                    invoiceItemListArray.add(item)
                                 }
                                    
                            } else {
                                item.put("itemName", invoiceItem.referenceItemName);
                                invoiceItemListArray.add(item)
                            }
                            
                        }
                    }
                    // add to array list
//                    Console.print("invoiceItemList2" + invoiceItemList)
                }
            } else {
                def billableItems = billableItemData.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                                       
                    def billableItem = BillableItem.findByName(billableItems[i])
                                       
                    invoiceItemList = InvoiceItem.withCriteria {
                        eq("invoice", invoice);
                        and {
                            eq("billableItem", billableItem) 
                            if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                            } else {
                              eq("zone", Zone.get(billableItemData.forZone)); 
                            }
                            if(billableItemData.plan != "ALL" && billableItems.length == 1) {
                                eq("referencePlanId", billableItemData.plan); 
                            } 
                        }
                    }
                    for(def invoiceItem: invoiceItemList) {
                       if(invoiceItem.totalAmount != 0) {
                            count = count +1;
                            HashMap item = new HashMap();
                            item.put("count", count);
                            item.put("account", invoice.account.accountIdentifier);
                            item.put("itemReferenceId", invoiceItem.referenceItemId);
                            item.put("date", outputDate);
                            item.put("totalAmount", invoiceItem.totalAmount);
                            if(invoiceItem.zone) {
                              item.put("zone", invoiceItem.zone.referenceZoneId);  
                            }
                            item.put("billableItem", messageSource.getMessage(billableItem.name, null, new Locale(language)));
                            item.put("plan", invoiceItem.referencePlanId);
                            if(invoiceItem.referenceItemName == "VirtualMachine") {
                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                                if(vm) {
                                    item.put("itemName", vm.displayName);
                                    invoiceItemListArray.add(item) 
                                }
                                
                            } else if(invoiceItem.referenceItemName =="Volume") {
                                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                                if(volume) {
                                    item.put("itemName", volume.name);
                                    invoiceItemListArray.add(item)
                                }
                            } else if(invoiceItem.referenceItemName =="SetupCost") {
                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                                if(vm) {
                                    item.put("itemName", vm.displayName);
                                    invoiceItemListArray.add(item); 
                                }
                            } else if(invoiceItem.referenceItemName == "Template") {
                                def template = Template.findByTemplateReferenceId(invoiceItem.referenceItemId);
                                if(template) {
                                    item.put("itemName", template.name);
                                    invoiceItemListArray.add(item)   
                                }
                                
                            } else if(invoiceItem.referenceItemName == "Snapshot") {
                                 def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
                                 if(snapshot) {
                                    item.put("itemName", snapshot.name);
                                    invoiceItemListArray.add(item)
                                 }
                                    
                            } else {
                                item.put("itemName", invoiceItem.referenceItemName);
                                invoiceItemListArray.add(item)
                            }
                        }
                        
                    }
                }  
            }
        }
//        Console.print("invoiceItemListArray" + invoiceItemListArray);
        ArrayList<ArrayList<String>> countListArray = new ArrayList<ArrayList<String>>(); 
        HashMap countItem = new HashMap()
        HashMap planCountMap = new HashMap()
        HashMap costCountMap = new HashMap()
                        
        def billableItemList;
//        if(billableItemData.forAccount == "ALL") {
//            def accountList = Account.findAll()
//            for(def acc: accountList) {
//                def accCount = 0;
//                for(def itemArray: invoiceItemListArray) {
//                    if(acc.accountIdentifier == itemArray.account) {
//                       accCount +=1 
//                    }
//                }
//                if(accCount != 0) {
//                   planCountMap.put(acc.accountIdentifier, accCount);  
//                }
//            }
//            accountCountListArray.add(accountCount)
//            
//        }
        
        
        
        if(billableItemData.forBillableItem == "ALL") {
            if(billableItemData.forZone == "NONZONE") {
                billableItemList = BillableItem.findAllWhere(hasZone: false)
            } else {
                billableItemList = BillableItem.findAllWhere(hasZone: true)
            }
            for(def bItem: billableItemList) {
                def BICount = 0;
                def costCount = 0;
                for(def itemArray: invoiceItemListArray) {
                    if(messageSource.getMessage(bItem.name, null, new Locale(language)) == itemArray.billableItem) {
                       BICount +=1 
                       costCount += itemArray.totalAmount
                    }
                }
                if(BICount != 0) {
                    planCountMap.put(messageSource.getMessage(bItem.name, null, new Locale(language)), BICount); 
                    costCountMap.put(messageSource.getMessage(bItem.name, null, new Locale(language)), costCount); 
                }

            }
            
        } else {
            def selectedBillableItems = billableItemData.billableItemList.split(",");
            if(selectedBillableItems.length == 1) {
                //add language
                if(messageSource.getMessage(selectedBillableItems[0] , null, new Locale(language))== messageSource.getMessage("common.billableItem.computingOffer", null, new Locale(language)) || selectedBillableItems[0] == messageSource.getMessage("common.billableItem.stoppedInstance", null, new Locale(language)) || selectedBillableItems[0] == messageSource.getMessage("common.billableItem.setupCostForInstance", null, new Locale(language)) || selectedBillableItems[0] == messageSource.getMessage("common.billableItem.discount", null, new Locale(language))) {
                    if(billableItemData.plan !="ALL") {
                       planList = ComputingOffer.withCriteria {  
                             eq("offerReferenceId", billableItemData.plan); 
                        }
                    } else {
                        planList = ComputingOffer.withCriteria {  
                            if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                            } else{
                                eq("zone", Zone.get(billableItemData.forZone));  

                            }  
                        }
                    }
                    for(def plan: planList) {
                        def planCount = 0;
                        def costCount = 0;
                        for(def itemArray: invoiceItemListArray) {
                            if(plan.offerReferenceId == itemArray.plan) {
                               planCount +=1 
                               costCount += itemArray.totalAmount
                            }
                        }
                        if(planCount != 0) {
                            planCountMap.put(plan.name, planCount); 
                            costCountMap.put(plan.name, costCount); 
                        }
                    }
                } else if(messageSource.getMessage(selectedBillableItems[0], null, new Locale(language)) == messageSource.getMessage("common.billableItem.diskOffer", null, new Locale(language))) {
                    if(billableItemData.plan !="ALL") {
                        planList = DiskOffer.withCriteria {  
                             eq("diskOfferReferenceId", billableItemData.plan); 
                        }
                    } else {
                        planList = DiskOffer.withCriteria {  
                            if(billableItemData.forZone == "NONZONE" || billableItemData.forZone == "ALL") {
                            } else{
                                eq("zone", Zone.get(billableItemData.forZone));  

                            }  
                        }
                    }
                    for(def plan: planList) {
                        def planCount = 0;
                        def costCount = 0;
                        for(def itemArray: invoiceItemListArray) {
                            if(plan.diskOfferReferenceId == itemArray.plan) {
                               planCount +=1 
                               costCount += itemArray.totalAmount
                            }
                        }
                        if(planCount != 0) {
                            planCountMap.put(plan.name, planCount); 
                            costCountMap.put(plan.name, costCount); 
                        }
                    }
                } else if(messageSource.getMessage(selectedBillableItems[0], null, new Locale(language)) == messageSource.getMessage("common.billableItem.template", null, new Locale(language))) {
                    def zoneList;
                    if(billableItemData.forZone == "ALL") {
                        zoneList = Zone.findAll()
                    } else {
                        zoneList = Zone.findAllWhere(id: Long.parseLong(billableItemData.forZone))
                    }
                      
                    for(def zone: zoneList) {
                        def BICount = 0;
                        def costCount = 0;
                        for(def itemArray: invoiceItemListArray) {
                            if(zone.referenceZoneId == itemArray.zone) {
                               BICount +=1 
                               costCount += itemArray.totalAmount;
                            } 
                        }
                        if(BICount != 0) {
                            planCountMap.put(zone.aliasName, BICount); 
                            costCountMap.put(zone.aliasName, costCount); 
                        }
                    }
                } else if(messageSource.getMessage(selectedBillableItems[0], null, new Locale(language)) == messageSource.getMessage("common.billableItem.snapShot", null, new Locale(language))) {
                    def zoneList;
                    if(billableItemData.forZone == "ALL") {
                        zoneList = Zone.findAll()
                    } else {
                        zoneList = Zone.findAllWhere(id: Long.parseLong(billableItemData.forZone))
                    }
                      
                    for(def zone: zoneList) {
                        def BICount = 0;
                        def costCount = 0;
                        for(def itemArray: invoiceItemListArray) {
                            if(zone.referenceZoneId == itemArray.zone) {
                               BICount +=1 
                               costCount += itemArray.totalAmount;
                            } 
                        }
                        if(BICount != 0) {
                            planCountMap.put(zone.aliasName, BICount); 
                            costCountMap.put(zone.aliasName, costCount); 
                        }
                    }
                } else {
                    def BICount = 0;
                    def costCount = 0;
                    for(def itemArray: invoiceItemListArray) {
                        if(messageSource.getMessage(selectedBillableItems[0], null, new Locale(language)) == itemArray.billableItem) {
                           BICount +=1 
                           costCount += itemArray.totalAmount
                        }
                    }
                    if(BICount != 0) {
                        planCountMap.put(messageSource.getMessage(selectedBillableItems[0], null, new Locale(language)), BICount);
                        costCountMap.put(messageSource.getMessage(selectedBillableItems[0], null, new Locale(language)), costCount); 
                    }
                }
            } else {
                for(def i=0; i < selectedBillableItems.length; i++) {
                        def BICount = 0;
                        def costCount = 0;
                        for(def itemArray: invoiceItemListArray) {
                            if(messageSource.getMessage(selectedBillableItems[i], null, new Locale(language)) == itemArray.billableItem) {
                                BICount +=1 
                                costCount += itemArray.totalAmount 
                            }
                        }
                        if(BICount != 0) {
                            planCountMap.put(messageSource.getMessage(selectedBillableItems[i], null, new Locale(language)), BICount);
                            costCountMap.put(messageSource.getMessage(selectedBillableItems[i], null, new Locale(language)), costCount);
                        }
                }
                
            }
        }
        
        String[] billableItemArray;
        String[] billableItemAmountArray;
        
        String[] billableItemCostArray;
        String[] billableItemAmountCostArray;
       
        
        Map billableItemTreeMap = new TreeMap(planCountMap);
        Map billableItemCostTreeMap = new TreeMap(costCountMap);
        
        Map sortTreeMap = billableItemTreeMap.descendingMap()
        Map sortCostTreeMap = billableItemCostTreeMap.descendingMap()
                
        billableItemArray = new String[sortTreeMap.size()]
        billableItemAmountArray = new String[sortTreeMap.size()]
        
        billableItemCostArray = new String[sortCostTreeMap.size()]
        billableItemAmountCostArray = new String[sortCostTreeMap.size()]
        
        
        def  arrayAmountCount = 0    
          
        for(def en : sortTreeMap.entrySet()) {
            billableItemArray[arrayAmountCount] = en.getKey(); 
            billableItemAmountArray[arrayAmountCount] = en.getValue(); 
            arrayAmountCount += 1
        }
        
        def  arrayCostCount = 0  
        for(def en : sortCostTreeMap.entrySet()) {
            billableItemCostArray[arrayCostCount] = en.getKey(); 
            billableItemAmountCostArray[arrayCostCount] = en.getValue(); 
            arrayCostCount += 1
        }
                
        countItem.put("billableItems", billableItemArray)
        countItem.put("billableItemAmounts", billableItemAmountArray)
        
        countItem.put("billableCostItems", billableItemCostArray)
        countItem.put("billableItemCostAmounts", billableItemAmountCostArray)
                
        countListArray.add(countItem);
        
        return countListArray;
        
     }
    
    
    
    
    
    
    def getComputePlanCost(planList, invoiceList, billableItem, billableItemData) {
                
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
        
        Map unsortMap = new HashMap();
        String[] billableItemArray;
        String[] billableItemAmountArray;
        def arrayAmountCount = 0;
        def invoiceItemList;
        billableItemArray = new String[planList.size()]
        billableItemAmountArray = new String[planList.size()]
        for(def plan: planList) {
            double totalAmount = 0.0;
            for(def invoice: invoiceList) {
                invoiceItemList = InvoiceItem.withCriteria {  
                    eq("invoice", invoice) 
                    and{
                        and {
                          eq("referencePlanId", plan.offerReferenceId)      
                        }  
                        eq("billableItem", billableItem) 
                        projections {
                            sum("totalAmount")
                        }
                    } 
                } 
                if(invoiceItemList[0] == null || invoiceItemList[0] == "null") {
                    totalAmount += 0.0;
                } else {
                    totalAmount += invoiceItemList[0];
                }
            }
            if(totalAmount !=0) {
                unsortMap.put(plan.name, totalAmount);
            }
        }
        
        Map treeMap = new TreeMap(unsortMap);
        Map sortTreeMap = treeMap.descendingMap()
        for(def en : sortTreeMap.entrySet()) {
            billableItemArray[arrayAmountCount] = en.getKey(); 
            billableItemAmountArray[arrayAmountCount] = en.getValue(); 
            arrayAmountCount += 1
        }
        
        HashMap itemData = new HashMap()
        itemData.put("billableItems", billableItemArray)
        itemData.put("billableItemAmounts", billableItemAmountArray)
        invoiceItemListArray.add(itemData) 
//        Console.print("invoiceItemListArray" + invoiceItemListArray);
        return invoiceItemListArray;
        
    }
    
    def getDiskPlanCost(planList, invoiceList, billableItem, billableItemData) {
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
        String[] billableItemArray;
        String[] billableItemAmountArray;
        Map unsortMap = new HashMap();
        def invoiceItemList;
        def arrayAmountCount = 0;
        billableItemArray = new String[planList.size()]
        billableItemAmountArray = new String[planList.size()]
        for(def plan: planList) {
            double totalAmount = 0.0;
            for(def invoice: invoiceList) {
                invoiceItemList = InvoiceItem.withCriteria {  
                    eq("invoice", invoice) 
                    and{
                        and {
                          eq("referencePlanId", plan.diskOfferReferenceId)      
                        }  
                        eq("billableItem", billableItem) 
                        projections {
                              sum("totalAmount")
                        }
                    } 
                } 
                if(invoiceItemList[0] == null || invoiceItemList[0] == "null") {
                    totalAmount += 0;
                } else {
                    totalAmount += invoiceItemList[0];
                }
            }
            if(totalAmount !=0) {
                unsortMap.put(plan.name, totalAmount);
            }
        }
        Map treeMap = new TreeMap(unsortMap);
        Map sortTreeMap = treeMap.descendingMap()
        for(def en : sortTreeMap.entrySet()) {
            billableItemArray[arrayAmountCount] = en.getKey(); 
            billableItemAmountArray[arrayAmountCount] = en.getValue(); 
            arrayAmountCount += 1
        }
        
        HashMap itemData = new HashMap()
        itemData.put("billableItems", billableItemArray)
        itemData.put("billableItemAmounts", billableItemAmountArray)
        invoiceItemListArray.add(itemData) 
//        Console.print("invoiceItemListArray" + invoiceItemListArray);
        return invoiceItemListArray;
    }
    
    def getComputePlanCostForAccount(planList, billableItem, billableItemData)  {
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
        String[] billableItemArray;
        String[] billableItemAmountArray;
        def invoiceItemListAmount;
        def arrayAmountCount = 0;
        Map unsortMap = new HashMap();
        List billableItemArrayList = new ArrayList();
        List billableItemAmountArrayList = new ArrayList();
    
        Date fromDate;
        Date toDate;
        if(billableItemData.forDateRange != "ALL") {
             fromDate = givenDateFormater.parse(billableItemData.fromDate);
             Date givenToDate = givenDateFormater.parse(billableItemData.toDate);
             Calendar toDateCalendar = Calendar.getInstance(); 
             toDateCalendar.setTime(givenToDate);
             toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
             toDateCalendar.set(Calendar.MINUTE, 59);
             toDateCalendar.set(Calendar.SECOND, 59);
             toDateCalendar.set(Calendar.MILLISECOND, 999);                   
             toDate = toDateCalendar.getTime() 
         }
        
        if(billableItemData.forAccount =="ALL") {
            def accountList = Account.findAll()
            billableItemArray = new String[accountList.size()]
            billableItemAmountArray = new String[accountList.size()]
            for(def account: accountList) {
                def invoiceList = Invoice.withCriteria {
                    eq("account", account);  
                    if(billableItemData.forDateRange != "ALL") {
                       ge("date", fromDate) and { le("date", toDate) }    
                    }
                }
                double totalAmount = 0.0;
                for(def invoice: invoiceList) {
                    invoiceItemListAmount = InvoiceItem.withCriteria {  
                        eq("invoice", invoice) 
                        and{
                            and {
                              eq("referencePlanId", billableItemData.plan)      
                            }  
                            eq("billableItem", billableItem) 
                            projections {
                                  sum("totalAmount")
                            }
                        } 
                    }
                    if(invoiceItemListAmount[0] == null || invoiceItemListAmount[0] == "null") {
                        totalAmount += 0.0;
                    } else {
                        totalAmount += invoiceItemListAmount[0];
                    }
                }
                if(totalAmount !=0) {
                    unsortMap.put(account.accountIdentifier, totalAmount);
                }
            }
            
            
            Map treeMap = new TreeMap(unsortMap);
            Map sortTreeMap = treeMap.descendingMap()
//            Console.print("treeMap" + treeMap)
            for(def en : sortTreeMap.entrySet()) {
                billableItemArray[arrayAmountCount] = en.getKey(); 
                billableItemAmountArray[arrayAmountCount] = en.getValue(); 
                arrayAmountCount += 1 
            }
        
            HashMap itemData = new HashMap()
            itemData.put("billableItems", billableItemArray)
            itemData.put("billableItemAmounts", billableItemAmountArray)
            invoiceItemListArray.add(itemData) 
//            Console.print("invoiceItemListArray" + invoiceItemListArray);
            return invoiceItemListArray;
            
        } else {
            
            def vmList = VirtualMachine.withCriteria {
                eq("owner", Account.get(billableItemData.forAccount));
                and {
                    eq("computingOffer", ComputingOffer.findByOfferReferenceId(billableItemData.plan));
                    if(billableItemData.forDateRange != "ALL") {
                     ge("upgradedDate", fromDate) and { le("upgradedDate", toDate) }    
                    } 
                }
                
            }
            billableItemArray = new String[vmList.size()]
            billableItemAmountArray = new String[vmList.size()]
            
            for(def vm: vmList) {
                double totalAmount = 0.0; 
                invoiceItemListAmount = InvoiceItem.withCriteria {  
                    eq("referenceItemId", vm.referenceId) 
                    and{
                        and {
                          eq("referencePlanId", billableItemData.plan)      
                        }  
                        eq("billableItem", billableItem) 
                    } 
                }
                for(def invoiceItem: invoiceItemListAmount) {
                    if(invoiceItem.invoice.account == Account.get(billableItemData.forAccount)) {
                        totalAmount += invoiceItem.totalAmount;
                    }
                }

                if(totalAmount !=0) {
                    unsortMap.put(vm.displayName, totalAmount);
                }
            }
            
            Map treeMap = new TreeMap(unsortMap);
            Map sortTreeMap = treeMap.descendingMap()
//            Console.print("treeMap" + treeMap)
            for(def en : sortTreeMap.entrySet()) {
                billableItemArray[arrayAmountCount] = en.getKey(); 
                billableItemAmountArray[arrayAmountCount] = en.getValue(); 
                arrayAmountCount += 1 
            }
        
            HashMap itemData = new HashMap()
            itemData.put("billableItems", billableItemArray)
            itemData.put("billableItemAmounts", billableItemAmountArray)
            invoiceItemListArray.add(itemData) 
//            Console.print("invoiceItemListArray" + invoiceItemListArray);
            return invoiceItemListArray;
        }
        
    }
    
    
    def getDiskPlanCostForAccount(planList, billableItem, billableItemData){
        
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
        String[] billableItemArray;
        String[] billableItemAmountArray;
        def invoiceItemListAmount;
        def arrayAmountCount = 0;
        Map unsortMap = new HashMap();
        Date fromDate;
        Date toDate;
        if(billableItemData.forDateRange != "ALL") {
            fromDate = givenDateFormater.parse(billableItemData.fromDate);
            Date givenToDate = givenDateFormater.parse(billableItemData.toDate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            toDate = toDateCalendar.getTime() 
        }
        if(billableItemData.forAccount =="ALL") {
            def accountList = Account.findAll()
            billableItemArray = new String[accountList.size()]
            billableItemAmountArray = new String[accountList.size()]
            for(def account: accountList) {
                def invoiceList = Invoice.withCriteria {
                    eq("account", account);  
                    if(billableItemData.forDateRange != "ALL") {
                       ge("date", fromDate) and { le("date", toDate) }    
                    }
                }
                def totalAmount = 0;
                for(def invoice: invoiceList) {
                    invoiceItemListAmount = InvoiceItem.withCriteria {  
                        eq("invoice", invoice) 
                        and{
                            and {
                              eq("referencePlanId", billableItemData.plan)      
                            }  
                            eq("billableItem", billableItem) 
                            projections {
                                  sum("totalAmount")
                            }
                        } 
                    }
                    if(invoiceItemListAmount[0] == null || invoiceItemListAmount[0] == "null") {
                        totalAmount += 0.0;
                    } else {
                        totalAmount += invoiceItemListAmount[0];
                    }
                }
                if(totalAmount !=0) {
                    unsortMap.put(account.accountIdentifier, totalAmount);
                }
            }
            
            Map treeMap = new TreeMap(unsortMap);
            Map sortTreeMap = treeMap.descendingMap()
            for(def en : sortTreeMap.entrySet()) {
                billableItemArray[arrayAmountCount] = en.getKey().toString(); 
                billableItemAmountArray[arrayAmountCount] = en.getValue(); 
                arrayAmountCount += 1
            }
            HashMap itemData = new HashMap()
            itemData.put("billableItems", billableItemArray)
            itemData.put("billableItemAmounts", billableItemAmountArray)
            invoiceItemListArray.add(itemData) 
//            Console.print("invoiceItemListArray" + invoiceItemListArray);
            return invoiceItemListArray;
            
        } else {
            
            def volumeList = Volume.withCriteria {
                eq("owner", Account.get(billableItemData.forAccount));
                and {
                    eq("diskOffer", DiskOffer.findByDiskOfferReferenceId(billableItemData.plan));
                    if(billableItemData.forDateRange != "ALL") {
                     ge("createdDate", fromDate) and { le("createdDate", toDate) }    
                    } 
                }
                
            }
            
            billableItemArray = new String[volumeList.size()]
            billableItemAmountArray = new String[volumeList.size()]
            
            for(def volume: volumeList) {
                double totalAmount = 0.0; 
                invoiceItemListAmount = InvoiceItem.withCriteria {  
                    eq("referenceItemId", volume.volumeReferenceId) 
                    and{
                        and {
                          eq("referencePlanId", billableItemData.plan)      
                        }  
                        eq("billableItem", billableItem) 
                    } 
                }
                
                for(def invoiceItem: invoiceItemListAmount) {
                    if(invoiceItem.invoice.account == Account.get(billableItemData.forAccount)) {
                        totalAmount += invoiceItem.totalAmount;
                    }
                }
                if(totalAmount !=0) {
                    unsortMap.put(volume.name, totalAmount);
                }
            }
            
            Map treeMap = new TreeMap(unsortMap);
            Map sortTreeMap = treeMap.descendingMap()
            for(def en : sortTreeMap.entrySet()) {
                billableItemArray[arrayAmountCount] = en.getKey(); 
                billableItemAmountArray[arrayAmountCount] = en.getValue(); 
                arrayAmountCount += 1 
            }
            
            HashMap itemData = new HashMap()
            itemData.put("billableItems", billableItemArray)
            itemData.put("billableItemAmounts", billableItemAmountArray)
            invoiceItemListArray.add(itemData) 
//            Console.print("invoiceItemListArray" + invoiceItemListArray);
            return invoiceItemListArray;
        } 
        
    }
}
