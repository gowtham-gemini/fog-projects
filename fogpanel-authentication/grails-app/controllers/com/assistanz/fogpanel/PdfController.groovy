package com.assistanz.fogpanel

import grails.converters.JSON
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.text.DecimalFormat
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

@Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
class PdfController { 
//    
//    def springSecurityService;
//    def authenticateService;    
//    MessageSource messageSource
//    
//    DecimalFormat df = new DecimalFormat("#.###############");    
//    
////    def currentUsage() {
////        
////        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency        
////        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
////        double billableItemCount = 1       
////        def invoice = Invoice.get(params.invoiceId)
////        def costCount;
////        ArrayList zoneListArray = new ArrayList();  
////        def invoiceItems = InvoiceItem.findAllWhere(invoice : invoice)
////        def zoneList = Zone.findAll()
////        def zoneCount = 0
////        for(def zone : zoneList) {
////            def zoneCostCriteria = InvoiceItem.createCriteria()
////            def zoneCost = zoneCostCriteria.list {
////                eq("invoice", invoice)
////                and{
////                    eq("zone", zone)  
////                }
////                projections {
////                    sum("totalAmount")
////                }
////            }            
////            // get zone billableItemList
////            def billableItemList = getBillableItem(zone, invoice, zoneCount, billableItemCount);
////            
////            billableItemCount = billableItemList[1]
////            if(zoneCost[0] == null || zoneCost[0] == "null") {
////                //                HashMap zoneItem = new HashMap();
////                //                zoneItem.put("zoneCost", 0); 
////                //                zoneItem.put("zone", zone.aliasName); 
////                //                zoneItem.put("zoneCount", zoneCount); 
////                //                zoneItem.put("billableItem", billableItemList[0]); 
////                //                zoneListArray.add(zoneItem)
////            } else {
////                HashMap zoneItem = new HashMap();
////                zoneItem.put("zoneCost",  Math.round(zoneCost[0] * 100d) / 100d); 
////                zoneItem.put("zone", zone.name); 
////                zoneItem.put("zoneCount", zoneCount); 
////                zoneItem.put("billableItem", billableItemList[0]); 
////                zoneListArray.add(zoneItem)
////                zoneCount +=1;
////            }
////            
////        }
////        
////        def nonZoneCostCriteria = InvoiceItem.createCriteria()
////        
////        def nonZoneCost = nonZoneCostCriteria.list {
////            eq("invoice", invoice)
////            and{
////                isNull("zone")  
////            }
////            projections {
////                sum("totalAmount")
////            }
////        }
////        // get non zone billableItemList
////        def nonZoneBillableItemList = getNonZoneBillableItem(invoice, zoneCount, billableItemCount);
////        billableItemCount = nonZoneBillableItemList[1]
////        if(nonZoneCost[0] == null || nonZoneCost[0] == "null") {
////            //            HashMap zoneItem = new HashMap();
////            //            zoneItem.put("zoneCost", 0); 
////            //            zoneItem.put("zone","Non-Zone Billable Items"); 
////            //            zoneItem.put("zoneCount", zoneCount+1); 
////            //            zoneItem.put("billableItem", nonZoneBillableItemList[0]); 
////            //            zoneListArray.add(zoneItem)
////        } else {
////            HashMap zoneItem = new HashMap();
////            zoneItem.put("zoneCost",  Math.round(nonZoneCost[0] * 100d) / 100d); 
////            zoneItem.put("zone", "Misc"); 
////            zoneItem.put("billableItem", nonZoneBillableItemList[0]); 
////            zoneItem.put("zoneCount", zoneCount+1); 
////            zoneListArray.add(zoneItem)
////        }
////                
////        if(invoice.status.name() == "DRAFT") {
////            invoiceService.updateInvoice(invoice)
////        }
////                
////        Calendar c = Calendar.getInstance();  
////        c.add(Calendar.DATE, -1);  
////        Date d = c.getTime();   
////        
////        def totalAmount = 0;
////        def credit = 0;
////        
////        if(invoice.totalAmount < 0) {
////            totalAmount = 0
////            credit = -1 * invoice.totalAmount
////        } else {
////            totalAmount = invoice.totalAmount
////            credit = 0
////        }
////        
////        [currency:currency , userAccount: invoice.account,  
////            invoice: invoice, totalAmount: totalAmount, credit: credit,
////            invoiceDate: dateFormat.format(d).toString(), zoneCost: zoneListArray, refund: invoice.refundAmount
////        ]
//        
//    }
//    
//    //get all non zone billable item list
//    def getNonZoneBillableItem(invoice, zoneCount, billableItemCount) {
//        
//        ArrayList billableItemListArray = new ArrayList(); 
//        def user = springSecurityService.currentUser 
//        def language = "";
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        def billableItemList = BillableItem.findAllWhere(hasZone: false)             
//        for(def billableItem : billableItemList) {
//            def billableItemCostCriteria = InvoiceItem.createCriteria()
//            def billableItemCost = billableItemCostCriteria.list {
//                eq("invoice", invoice)
//                and {
//                    and {
//                        eq("billableItem", billableItem)   
//                    }
//                    isNull("zone")   
//                }
//                projections {
//                    sum("totalAmount")
//                }
//            }                
//            def invoiceItem = getInvoiceItem(billableItem, null, invoice, billableItemCount);
//            if(billableItemCost[0] == null || billableItemCost[0] == "null") {
//                //                    HashMap bItem = new HashMap();
//                //                    bItem.put("zone", "Non-Zone Billable Items"); 
//                //                    bItem.put("billableItemZoneCount", zoneCount); 
//                //                    bItem.put("billableItem", billableItem.referenceItemName);
//                //                    bItem.put("billableItemCount", billableItemCount);
//                //                    bItem.put("billableItemCost", 0);
//                //                    bItem.put("invoiceItem", invoiceItem);
//                //                    billableItemListArray.add(bItem)
//            } else {                                        
//                HashMap bItem = new HashMap();
//                bItem.put("zone", "Misc"); 
//                bItem.put("billableItemZoneCount", zoneCount); 
//                if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage('common.instance', null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage('menu.admin.services.storage', null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage('common.snapshot', null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(4).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage('common.network', null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(10).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.images", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(11).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.bandwidthRead", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(12).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.bandwidthWrite", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(13).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.imageSnapshot", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(14).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.floatingIP", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(8).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage('common.billing.customItem', null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(9).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage('common.billing.recurringItem', null, new Locale(language)));
//                }
//
//                bItem.put("billableItemCost", Math.round(billableItemCost[0] * 100d) / 100d);
//                bItem.put("billableItemCount", billableItemCount);
//                bItem.put("invoiceItem", invoiceItem);
//                billableItemListArray.add(bItem)
//                billableItemCount +=1
//            }
//        }
//
//        return [billableItemListArray, billableItemCount]; 
//    }
//    
//    // get zone based billable item
//    def getBillableItem(zone, invoice, zoneCount, billableItemCount) {
//        ArrayList billableItemListArray = new ArrayList(); 
//        def user = springSecurityService.currentUser 
//        def language = "";
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        if(zone == null) {            
//                        
//        } else {
//                        
//            def billableItemCriteria = BillableItem.createCriteria()
//            def billableItemList = billableItemCriteria.list {
//                eq("hasZone", true)
//                or{ ne("id", 13.toLong())}
//                or{ne("id", 14.toLong())}
//                or{ne("id", 15.toLong())}
//                or{ne("id", 16.toLong())}
//            }
//            
//            //            def vmMonCostCriteria = InvoiceItem.createCriteria()
//            //            def vmMonbillableItemCost = vmMonCostCriteria.list {
//            //                eq("invoice", invoice)
//            //                and{
//            //                    and {
//            //                       eq("billableItem", BillableItem.get(13))   
//            //                    }
//            //                   eq("zone", zone)  
//            //                }
//            //                projections {
//            //                    sum("totalAmount")
//            //                }
//            //            }
//                
//            //            def diskMonCostCriteria = InvoiceItem.createCriteria()
//            //            def diskMonbillableItemCost = diskMonCostCriteria.list {
//            //                eq("invoice", invoice)
//            //                and{
//            //                    and {
//            //                       eq("billableItem", BillableItem.get(14))   
//            //                    }
//            //                   eq("zone", zone)  
//            //                }
//            //                projections {
//            //                    sum("totalAmount")
//            //                }
//            //            }
//                
//            //            def snapMonbillableItemCostCriteria = InvoiceItem.createCriteria()
//            //            def snapMonbillableItemCost = snapMonbillableItemCostCriteria.list {
//            //                eq("invoice", invoice)
//            //                and{
//            //                    and {
//            //                       eq("billableItem", BillableItem.get(15))   
//            //                    }
//            //                   eq("zone", zone)  
//            //                }
//            //                projections {
//            //                    sum("totalAmount")
//            //                }
//            //            }
//                
//            //            def VMSnapMonbillableItemCostCriteria = InvoiceItem.createCriteria()
//            //            def VMSnapMonbillableItemCost = VMSnapMonbillableItemCostCriteria.list {
//            //                eq("invoice", invoice)
//            //                and{
//            //                    and {
//            //                       eq("billableItem", BillableItem.get(16))   
//            //                    }
//            //                   eq("zone", zone)  
//            //                }
//            //                projections {
//            //                    sum("totalAmount")
//            //                }
//            //            }
//                
//            def vmMonCost = 0;
//            def diskMonCost= 0;
//            def snapMonCost= 0;
//            def VMSnapMonCost= 0;
//            //            if(vmMonbillableItemCost[0] == null || vmMonbillableItemCost[0] == "null") {
//            //                vmMonCost = 0;
//            //            } else {
//            //                vmMonCost = vmMonbillableItemCost[0];
//            //            }
//            //            if(diskMonbillableItemCost[0] == null || diskMonbillableItemCost[0] == "null") {
//            //               diskMonCost = 0;
//            //            } else {
//            //                diskMonCost = diskMonbillableItemCost[0];
//            //            }
//            //            if(snapMonbillableItemCost[0] == null || snapMonbillableItemCost[0] == "null") {
//            //                snapMonCost = 0;
//            //            } else {
//            //                snapMonCost = snapMonbillableItemCost[0];
//            //            }
//            //            if(VMSnapMonbillableItemCost[0] == null || VMSnapMonbillableItemCost[0] == "null") {
//            //                VMSnapMonCost = 0;
//            //            } else {
//            //                VMSnapMonCost = VMSnapMonbillableItemCost[0];
//            //            }
//            
//            
//            for(def billableItem : billableItemList) {
//                def billableItemCostCriteria = InvoiceItem.createCriteria()
//                def billableItemCost = billableItemCostCriteria.list {
//                    eq("invoice", invoice)
//                    and {
//                        and {
//                            eq("billableItem", billableItem)   
//                        }
//                        eq("zone", zone)  
//                    }
//                    projections {
//                        sum("totalAmount")
//                    }
//                }
//                def invoiceItem = getInvoiceItem(billableItem, zone, invoice, billableItemCount);
//                if(billableItemCost[0] == null || billableItemCost[0] == "null") {
//                    //                    HashMap bItem = new HashMap();
//                    //                    bItem.put("zone", zone.name); 
//                    //                    bItem.put("billableItemZoneCount", zoneCount); 
//                    //                    if((billableItem.referenceItemName == BillableItem.get(1).referenceItemName) && vmMonCost != 0) {
//                    //                        bItem.put("billableItem", messageSource.getMessage('menu.user.cloud.instance', null, new Locale(language)));
//                    //                        bItem.put("billableItemCost", Math.ceil((0.0 + vmMonCost ) * 100d) / 100d);
//                    //                        bItem.put("billableItemCount", billableItemCount);
//                    //                        bItem.put("invoiceItem", invoiceItem);
//                    //                        billableItemListArray.add(bItem)
//                    //                        billableItemCount +=1
//                    //                    } else if((billableItem.referenceItemName == BillableItem.get(2).referenceItemName)  && diskMonCost != 0) {
//                    //                        bItem.put("billableItem", messageSource.getMessage('menu.user.cloud.storage', null, new Locale(language)));
//                    //                        bItem.put("billableItemCost", Math.ceil((0.0 + diskMonCost)  * 100d) / 100d);
//                    //                        bItem.put("billableItemCount", billableItemCount);
//                    //                        bItem.put("invoiceItem", invoiceItem);
//                    //                        billableItemListArray.add(bItem)
//                    //                        billableItemCount +=1
//                    //                    } else if((billableItem.referenceItemName == BillableItem.get(3).referenceItemName)  && snapMonCost != 0) {
//                    //                        bItem.put("billableItem", messageSource.getMessage('common.snapshot', null, new Locale(language)));
//                    //                        bItem.put("billableItemCost", Math.ceil((0.0 + snapMonCost)  * 100d) / 100d);
//                    //                        bItem.put("billableItemCount", billableItemCount);
//                    //                        bItem.put("invoiceItem", invoiceItem);
//                    //                        billableItemListArray.add(bItem)
//                    //                        billableItemCount +=1
//                    //                    } 
//                    ////                    else if((billableItem.referenceItemName == BillableItem.get(16).referenceItemName)  && VMSnapMonCost != 0) {
//                    ////                        bItem.put("billableItem", messageSource.getMessage('common.snapshot.VMSnapshot', null, new Locale(language)));
//                    ////                        bItem.put("billableItemCost", Math.ceil((0.0 + VMSnapMonCost)  * 100d) / 100d);
//                    ////                        bItem.put("billableItemCount", billableItemCount);
//                    ////                        bItem.put("invoiceItem", invoiceItem);
//                    ////                        billableItemListArray.add(bItem)
//                    ////                        billableItemCount +=1
//                    ////                    }
//                } else {                                        
//                    HashMap bItem = new HashMap();
//                    bItem.put("zone", zone.name); 
//                    bItem.put("billableItemZoneCount", zoneCount); 
//                    if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage('common.instances', null, new Locale(language)));
//                    } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage('common.volumes', null, new Locale(language)));
//                    } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage('common.snapshot', null, new Locale(language)));
//                    } else if(billableItem.referenceItemName == BillableItem.get(4).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage('common.template', null, new Locale(language)));
//                    }  else if(billableItem.referenceItemName == BillableItem.get(10).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage("common.billableItem.images", null, new Locale(language))); 
//                    } else if(billableItem.referenceItemName == BillableItem.get(11).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage("common.billableItem.bandwidthRead", null, new Locale(language)));
//                    } else if(billableItem.referenceItemName == BillableItem.get(12).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage("common.billableItem.bandwidthWrite", null, new Locale(language)));
//                    } else if(billableItem.referenceItemName == BillableItem.get(13).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage("common.billableItem.imageSnapshot", null, new Locale(language)));
//                    } else if(billableItem.referenceItemName == BillableItem.get(14).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage("common.billableItem.floatingIP", null, new Locale(language)));
//                    } else if(billableItem.referenceItemName == BillableItem.get(8).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage('common.billing.customItem', null, new Locale(language)));
//                    } else if(billableItem.referenceItemName == BillableItem.get(9).referenceItemName) {
//                        bItem.put("billableItem", messageSource.getMessage('common.billing.recurringItem', null, new Locale(language)));
//                    }
//                    
//                    if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName) {
//                        bItem.put("billableItemCost", Math.round((billableItemCost[0] + vmMonCost ) * 100d) / 100d);
//                    } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName ) {
//                        bItem.put("billableItemCost", Math.round((billableItemCost[0] + diskMonCost)  * 100d) / 100d);
//                    } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName) {
//                        bItem.put("billableItemCost", Math.round((billableItemCost[0] + snapMonCost)  * 100d) / 100d);
//                    } else {
//                        bItem.put("billableItemCost", Math.round(billableItemCost[0] * 100d) / 100d);
//                    }
//                    
//                    bItem.put("billableItemCount", billableItemCount);
//                    bItem.put("invoiceItem", invoiceItem);
//                    billableItemListArray.add(bItem)
//                    billableItemCount +=1
//                }
//            }
//             
//            return  [billableItemListArray, billableItemCount]; 
//        }
//    }   
//    
//    //get invoice item details
//    def getInvoiceItem(billableItem, zone, invoice, billableItemCount) {
//        ArrayList invoiceListArray = new ArrayList(); 
//        
//        def invoiceItemLIst;
//        
//        def language = "";
//        def user = springSecurityService.currentUser 
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        
//        if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName) {
//            invoiceItemLIst = InvoiceItem.executeQuery("select item from InvoiceItem item " +
//                     "where (item.billableItem = ? OR item.billableItem = ? ) and item.invoice = ? and item.zone = ?",
//                [BillableItem.get(1), BillableItem.get(13), invoice, zone]);
//            
//        } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName) {  
//            invoiceItemLIst = InvoiceItem.executeQuery("select item from InvoiceItem item " +
//                     "where (item.billableItem = ? OR item.billableItem = ? ) and item.invoice = ? and item.zone = ?",
//                [BillableItem.get(2), BillableItem.get(14), invoice, zone]);
//                 
//        } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName) {
//            invoiceItemLIst = InvoiceItem.executeQuery("select item from InvoiceItem item " +
//                     "where (item.billableItem = ? OR item.billableItem = ? ) and item.invoice = ? and item.zone = ?",
//                [BillableItem.get(3), BillableItem.get(15), invoice, zone]);
//        } 
//        //        else if(billableItem.referenceItemName == BillableItem.get(17).referenceItemName) {
//        //            invoiceItemLIst = InvoiceItem.executeQuery("select item from InvoiceItem item " +
//        //                     "where (item.billableItem = ? OR item.billableItem = ? ) and item.invoice = ? and item.zone = ?",
//        //                     [BillableItem.get(16), BillableItem.get(17), invoice, zone]);
//        //        } 
//        else {
//            invoiceItemLIst = InvoiceItem.findAllWhere(billableItem: billableItem, invoice: invoice, zone: zone)
//        }
//               
//        for(def invoiceItem : invoiceItemLIst) {                        
//            if(invoiceItem.billableItem == BillableItem.get(1)) {
//                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
//                def flavors = Flavors.findByReferenceId(invoiceItem.referencePlanId) 
//                if(vm && flavors) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", vm.name); 
//                    iItem.put("plan", flavors.name);
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    if(vm.billingType == "monthly") {
//                        iItem.put("priceUnit", " /" + messageSource.getMessage("common.month", null, new Locale(language)));
//                        iItem.put("usageUnit", " ");                       
//                    } else {
//                        iItem.put("priceUnit", "  /" + messageSource.getMessage("common.hr", null, new Locale(language)));
//                        iItem.put("usageUnit", messageSource.getMessage("common.hr", null, new Locale(language)));                         
//                    }
//                    
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("cost", invoiceItem.totalAmount);
//                    if(vm.deleted == true || vm.deleted == "true") {
//                        iItem.put("vmStatus", "Expunging"); 
//                    } else {
//                        if(vm.flavor == flavors) {
//                            iItem.put("vmStatus", "");
//                        } else {
//                            iItem.put("vmStatus", "Expunging"); 
//                        }
//                    }
//                    invoiceListArray.add(iItem)
//                }  
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(2)) {
//                def volume = Volume.findByReferenceId(invoiceItem.referenceItemId);
//                def volumeType = VolumeType.findByReferenceId(invoiceItem.referencePlanId)
//                if(volumeType && volume) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", volume.name); 
//                    iItem.put("plan", volumeType.name);                                        
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    
//                    if(volume.billingType == "monthly") {                        
//                        iItem.put("priceUnit", messageSource.getMessage("common.gbPerMonth", null, new Locale(language)));
//                        iItem.put("usageUnit", "");                        
//                    } else {                        
//                        iItem.put("priceUnit", messageSource.getMessage("common.gbPerHr", null, new Locale(language)));
//                        iItem.put("usageUnit", messageSource.getMessage("common.hr", null, new Locale(language)));
//                    }
//                    
//                    
//                    iItem.put("cost", invoiceItem.totalAmount);
//                    if(volume.deleted == true || volume.deleted == "true") {
//                        iItem.put("vmStatus", "Expunging");
//                    } else {
//                        if(volume.volumeType == volumeType) {
//                            iItem.put("vmStatus", "");
//                        } else {
//                            iItem.put("vmStatus", "Expunging");
//                        }
//                    }
//                    
//                    invoiceListArray.add(iItem)
//                }
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(3)) {
//                def snapshot = VolumeSnapshot.findByReferenceId(invoiceItem.referenceItemId);
//                if(snapshot) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", snapshot.name); 
//                    iItem.put("plan", snapshot.volume.name);
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("priceUnit", messageSource.getMessage("common.gbPerHr", null, new Locale(language)));
//                    iItem.put("usageUnit", messageSource.getMessage("common.hr", null, new Locale(language)));
//                    iItem.put("cost", invoiceItem.totalAmount);
//                    if(snapshot.deleted == true || snapshot.deleted == "true") {
//                        iItem.put("vmStatus", "Expunging");
//                    } else {
//                        iItem.put("vmStatus", "");  
//                    }
//                    invoiceListArray.add(iItem)
//                }
//                
//                   
//            } else if(invoiceItem.billableItem == BillableItem.get(4)) {
//                def network = Network.findByReferenceId(invoiceItem.referenceItemId);
//                if(network) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", network.name); 
//                    iItem.put("plan", "-");
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    
//                    if(network.billingType == "monthly") {
//                        iItem.put("priceUnit", " /" + messageSource.getMessage("common.month", null, new Locale(language)));
//                        iItem.put("usageUnit", " ");
//                    } else {                        
//                        iItem.put("priceUnit", " /" + messageSource.getMessage("common.hr", null, new Locale(language)));
//                        iItem.put("usageUnit", messageSource.getMessage("common.hr", null, new Locale(language)));                        
//                    }                    
//                    
//                    iItem.put("cost", invoiceItem.totalAmount);
//                    if(network.deleted == true || network.deleted == "true") {
//                        iItem.put("vmStatus", "Expunging");
//                    } else {
//                        iItem.put("vmStatus", "");  
//                    }
//                    invoiceListArray.add(iItem) 
//                }
//            } else if(invoiceItem.billableItem == BillableItem.get(8)) {
//                HashMap iItem = new HashMap();
//                iItem.put("name", invoiceItem.referenceItemName); 
//                iItem.put("plan", "-");
//                iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                iItem.put("priceUnit", "");
//                iItem.put("usageUnit", "");
//                iItem.put("cost", invoiceItem.totalAmount);
//                iItem.put("vmStatus", "");
//                invoiceListArray.add(iItem)
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(9)) {
//                HashMap iItem = new HashMap();
//                iItem.put("name", invoiceItem.referenceItemName); 
//                iItem.put("plan", "-");
//                iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                iItem.put("priceUnit", "");
//                iItem.put("usageUnit", "");
//                iItem.put("cost", invoiceItem.totalAmount);
//                iItem.put("vmStatus", "");
//                invoiceListArray.add(iItem)
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(10)) {
//                def image = Images.findByReferenceId(invoiceItem.referenceItemId);
//                if(image) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", image.name); 
//                    iItem.put("plan", "-");
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);                    
//                    iItem.put("priceUnit", " /" + messageSource.getMessage("common.month", null, new Locale(language)));
//                    iItem.put("usageUnit", " ");
//                    iItem.put("cost", invoiceItem.totalAmount);
//                    if(image.deleted == true || image.deleted == "true") {
//                        iItem.put("vmStatus", "Expunging");
//                    } else {
//                        iItem.put("vmStatus", "");  
//                    }
//                    invoiceListArray.add(iItem) 
//                }
//            } else if(invoiceItem.billableItem == BillableItem.get(11)) {
////                def network = Network.findByReferenceId(invoiceItem.referenceItemId);
////                if(network) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", invoiceItem.referenceItemName); 
//                    iItem.put("plan", "-");
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("priceUnit", " /" + messageSource.getMessage("common.gb", null, new Locale(language)));
//                    iItem.put("usageUnit", messageSource.getMessage("common.gb", null, new Locale(language)));
//                    iItem.put("cost", invoiceItem.totalAmount);
////                    if(network.deleted == true || network.deleted == "true") {
////                        iItem.put("vmStatus", "Expunging");
////                    } else {
//                        iItem.put("vmStatus", "");  
////                    }
//                    invoiceListArray.add(iItem) 
////                }
//            } else if(invoiceItem.billableItem == BillableItem.get(12)) {
////                def network = Network.findByReferenceId(invoiceItem.referenceItemId);
////                if(network) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", invoiceItem.referenceItemName); 
//                    iItem.put("plan", "-");
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("priceUnit", " /" + messageSource.getMessage("common.gb", null, new Locale(language)));
//                    iItem.put("usageUnit", messageSource.getMessage("common.gb", null, new Locale(language)));
//                    iItem.put("cost", invoiceItem.totalAmount);
////                    if(network.deleted == true || network.deleted == "true") {
////                        iItem.put("vmStatus", "Expunging");
////                    } else {
//                        iItem.put("vmStatus", "");  
////                    }
//                    invoiceListArray.add(iItem) 
////                }
//            } else if(invoiceItem.billableItem == BillableItem.get(13)) {
//                
//                def vmSnapshot = Images.findByReferenceId(invoiceItem.referenceItemId);
//                if(vmSnapshot) {
//                    
//                    
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", vmSnapshot.name); 
//                    iItem.put("plan", "-");
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    
//                    if(vmSnapshot.billingType == "monthly") {
//                        iItem.put("priceUnit", "  " + messageSource.getMessage("common.gbPerMonth", null, new Locale(language)));
//                        iItem.put("usageUnit", "");
//                    } else {
//                        iItem.put("priceUnit", "  " + messageSource.getMessage("common.gbPerHr", null, new Locale(language)));
//                        iItem.put("usageUnit", messageSource.getMessage("common.hr", null, new Locale(language)));
//                    }                     
//                    
//                    iItem.put("cost", invoiceItem.totalAmount);
//                    if(vmSnapshot.deleted == true || vmSnapshot.deleted == "true") {
//                        iItem.put("vmStatus", "Expunging"); 
//                    } else {
//                        iItem.put("vmStatus", "");
//                    }
//                    invoiceListArray.add(iItem)
//                }
//            } else if(invoiceItem.billableItem == BillableItem.get(14)) {
//                
//                HashMap iItem = new HashMap();
//                iItem.put("name", invoiceItem.referenceItemName); 
//                iItem.put("plan", "-");
//                iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
//                iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                iItem.put("priceUnit", "");
//                iItem.put("usageUnit", "");
//                iItem.put("cost", invoiceItem.totalAmount);
//                def floatingIp = FloatingIp.findByReferenceId(invoiceItem.referenceItemId);
//                if(floatingIp?.deleted == true) {
//                    iItem.put("vmStatus", "Expunging");
//                } else {
//                    iItem.put("vmStatus", "");
//                }
//                invoiceListArray.add(iItem) 
//            } 
//        }
//        return invoiceListArray
//        
//    }
//    
//    
//    def invoiceSummary() {
//        
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        
//        def invoice = Invoice.get(params.invoiceId)
//                
//        def billableItemList = getInvoiceSummaryBillableItem(invoice)               
//                
//        def paymentHistory = getPayments(invoice); 
//        
//        def dueDate;
//        if(invoice.dueDate == null || invoice.dueDate == "null") {
//            dueDate = "-"
//        } else {
//            dueDate = dateFormat.format(invoice.dueDate).toString()
//        }
//        if(invoice.status.name() == "DRAFT") {
//            //            invoiceService.updateDraftInvoice(invoice, invoice.account)
//        }
//        
//        ArrayList<String> orgAddressDetails = new ArrayList<String>();  
//        
//        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS).value);
//        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_EXTENSION).value);
//        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_CITY).value);
//        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_STATE).value);
//        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY).value);
//        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_BILLING_PHONE_NUMBER1).value);
//        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_BILLING_FAX_NUMBER1).value);
//        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_ZIP).value);
//        
//        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
//        
//        //response.setContentType('application/pdf');
//        [currency:currency, userAccount: invoice.account, 
//            billableItemList: billableItemList, invoice: invoice, 
//            invoiceDate: dateFormat.format(invoice.date).toString(), 
//            dueDate: dueDate, payments: paymentHistory, 
//            orgAddressDetails: orgAddressDetails, orgName: Config.findByName(Config.ORGANISATION_NAME).value, lateFee: Config.findByName(Config.LATE_FEE_MINIMUM_AMOUNT).value]
//    }
//    
//    
//    def getInvoiceSummaryBillableItem(invoice) {        
//        ArrayList billableItemListArray = new ArrayList();         
//        def billableItemList = BillableItem.findAll();
//        def language = "";
//        def user = springSecurityService.currentUser 
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//
//        for(def billableItem: billableItemList) {                        
//            def billableItemCostCriteria = InvoiceItem.createCriteria()
//            def billableItemCost = billableItemCostCriteria.list {
//                eq("invoice", invoice)
//                and {
//                    eq("billableItem", billableItem)    
//                }
//                projections {
//                    sum("totalAmount")
//                }
//            }
//            
//            if(billableItemCost[0] == null || billableItemCost[0] == "null") {                
//            } else {                
//                def invoiceItem = getInvoiceSummaryInvoiceItems(billableItem, invoice);                
//                HashMap bItem = new HashMap();               
//                if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.runningInstance", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("menu.admin.services.storage", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.snapShot", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(4).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.network", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(10).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.images", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(11).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.bandwidthRead", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(12).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.bandwidthWrite", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(13).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.imageSnapshot", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(14).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.floatingIP", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(8).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.custom", null, new Locale(language)));
//                } else if(billableItem.referenceItemName == BillableItem.get(9).referenceItemName) {
//                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.recurringItem", null, new Locale(language)));
//                } 
//                bItem.put("billableItemCost", Math.ceil(billableItemCost[0] * 100d) / 100d);
//                bItem.put("invoiceItem", invoiceItem);
//                billableItemListArray.add(bItem)
//            }            
//        }        
//        return billableItemListArray;        
//    }        
//    def getInvoiceSummaryInvoiceItems(billableItem, invoice) {
//        
//        def language = "";
//        def user = springSecurityService.currentUser 
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        ArrayList invoiceListArray = new ArrayList(); 
//        
//        def invoiceItemLIst = InvoiceItem.findAllWhere(billableItem: billableItem, invoice: invoice)
//        
//        for(def invoiceItem : invoiceItemLIst) {            
//            if(invoiceItem.billableItem == BillableItem.get(1)) {
//                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
//                def flavors = Flavors.findByReferenceId(invoiceItem.referencePlanId)
//                if(vm && flavors) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount);
//                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                    iItem.put("itemName", vm.name + " (" + flavors.name + ")");
//                    if(vm.billingType == "monthly") {
//                        iItem.put("units", "/" + messageSource.getMessage("common.month", null, new Locale(language)));                        
//                    } else {
//                        iItem.put("units", "/" + messageSource.getMessage("common.hr", null, new Locale(language)));
//                    }
//                    
//                    
//                    iItem.put("plan", flavors.name);
//                    invoiceListArray.add(iItem)
//                }
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(2)) {
//                def volume = Volume.findByReferenceId(invoiceItem.referenceItemId);
//                def volumeType = VolumeType.findByReferenceId(invoiceItem.referencePlanId)
//                if(volumeType && volume) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount);
//                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                    iItem.put("itemName", volume.name + " (" + volumeType.name + ")");
//                    
//                    if(volume.billingType == "monthly") {
//                        iItem.put("units",  messageSource.getMessage("common.gbPerMonth", null, new Locale(language)));                        
//                    } else {
//                        iItem.put("units",  messageSource.getMessage("common.gbPerHr", null, new Locale(language)));                                      
//                    }                    
//                    
//                    iItem.put("plan", volumeType.name);               
//                    invoiceListArray.add(iItem)
//                }
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(3)) {
//                def snapshot = VolumeSnapshot.findByReferenceId(invoiceItem.referenceItemId);
//                if(snapshot) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount);
//                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                    iItem.put("itemName", snapshot.name + " (" + snapshot.volume.name + ")");
//                    iItem.put("units", messageSource.getMessage("common.gbPerHr", null, new Locale(language)));
//                    iItem.put("plan", "-");
//                    invoiceListArray.add(iItem)
//                }
//                
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(4)) {
//                def network = Network.findByReferenceId(invoiceItem.referenceItemId);
//                if(network) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount);
//                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                    iItem.put("itemName", invoiceItem.referenceItemName );
//                    if(network.billingType == "monthly") {
//                        iItem.put("units", "  /"+ messageSource.getMessage("common.month", null, new Locale(language)));
//                    } else {
//                        iItem.put("units", "  /"+ messageSource.getMessage("common.hr", null, new Locale(language)));                                              
//                    }
//                    
//                    
//                    iItem.put("plan", "");
//                    invoiceListArray.add(iItem) 
//                }
//            } else if(invoiceItem.billableItem == BillableItem.get(8)) {
//                HashMap iItem = new HashMap();
//                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                iItem.put("usageAmount", invoiceItem.usageAmount);
//                iItem.put("taxPercent", invoiceItem.taxPercent); 
//                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                iItem.put("totalAmount", invoiceItem.totalAmount);
//                iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                iItem.put("discountPercent", invoiceItem.discountPercent);
//                iItem.put("discountAmount", invoiceItem.discountAmount);
//                iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                iItem.put("itemName", invoiceItem.referenceItemName);
//                iItem.put("units", "-");
//                iItem.put("plan", "");
//                invoiceListArray.add(iItem)
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(9)) {
//                HashMap iItem = new HashMap();
//                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                iItem.put("usageAmount", invoiceItem.usageAmount);
//                iItem.put("taxPercent", invoiceItem.taxPercent); 
//                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                iItem.put("totalAmount", invoiceItem.totalAmount);
//                iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                iItem.put("discountPercent", invoiceItem.discountPercent);
//                iItem.put("discountAmount", invoiceItem.discountAmount);
//                iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                iItem.put("itemName", invoiceItem.referenceItemName);
//                iItem.put("units", "-");
//                iItem.put("plan", "");
//                invoiceListArray.add(iItem)
//                
//            } else if(invoiceItem.billableItem == BillableItem.get(10)) {
//                def image = Images.findByReferenceId(invoiceItem.referenceItemId);
//                if(image) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount);
//                    iItem.put("referencePlanId", "-");
//                    iItem.put("itemName", invoiceItem.referenceItemName);
//                    iItem.put("units", "/" + messageSource.getMessage("common.month", null, new Locale(language)));
//                    iItem.put("plan", "");
//                    invoiceListArray.add(iItem) 
//                }
//            } else if(invoiceItem.billableItem == BillableItem.get(11)) {
////                def network = Network.findByReferenceId(invoiceItem.referenceItemId);
////                if(network) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount);
//                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                    iItem.put("itemName", invoiceItem.referenceItemName);
//                    iItem.put("units", " /" + messageSource.getMessage("common.gb", null, new Locale(language)));
//                    iItem.put("plan", "");
//                    invoiceListArray.add(iItem) 
////                }
//            } else if(invoiceItem.billableItem == BillableItem.get(12)) {
////                def network = Network.findByReferenceId(invoiceItem.referenceItemId);
////                if(network) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount);
//                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                    iItem.put("itemName", invoiceItem.referenceItemName);
//                    iItem.put("units", " /" + messageSource.getMessage("common.gb", null, new Locale(language)));
//                    iItem.put("plan", "");
//                    invoiceListArray.add(iItem) 
////                }
//            }else if(invoiceItem.billableItem == BillableItem.get(13)) {
//                def vmSnap = Images.findByReferenceId(invoiceItem.referenceItemId);
//                if(vmSnap) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount); 
//                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                    iItem.put("itemName",  "VM Snapshot"+ " (" + vmSnap.name + ")");
//                    
//                    if(vmSnap.billingType == "monthly") {
//                        iItem.put("units", "  " + messageSource.getMessage("common.gbPerMonth", null, new Locale(language)));                        
//                    } else {
//                        iItem.put("units", "  " + messageSource.getMessage("common.gbPerHr", null, new Locale(language)));                        
//                    }                                         
//                    iItem.put("plan", "-");
//                    invoiceListArray.add(iItem)
//                }
//            } else if(invoiceItem.billableItem == BillableItem.get(14)) {
//                
//                HashMap iItem = new HashMap();
//                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
//                iItem.put("usageAmount", invoiceItem.usageAmount);
//                iItem.put("taxPercent", invoiceItem.taxPercent); 
//                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
//                iItem.put("totalAmount", invoiceItem.totalAmount);
//                iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                iItem.put("discountPercent", invoiceItem.discountPercent);
//                iItem.put("discountAmount", invoiceItem.discountAmount);
//                iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                iItem.put("itemName", invoiceItem.referenceItemName);
//                iItem.put("units", "/hr");
//                iItem.put("plan", "");
//                invoiceListArray.add(iItem) 
//            } 
//        }
//        return invoiceListArray
//    }
//        
//    def getSnapshot(invoice) {
//        def invoiceItemCriteria = InvoiceItem.createCriteria()
//        def snapshotCriteria = Snapshot.createCriteria()
//        def snapBillableItem = BillableItem.findByReferenceItemName("snapShot")
//        ArrayList<ArrayList<String>> snapshotList = new ArrayList<ArrayList<String>>(); 
//        HashMap invoiceItem = new HashMap();  
//        def snapshotTotalUsageUnit = invoiceItemCriteria.get {
//            eq("invoice", invoice)
//            and{
//                eq("billableItem", snapBillableItem)  
//            }
//            projections {
//                avg("usageUnits")
//                avg("usageUnitPrice")
//                sum("usageAmount")
//                avg("taxPercent")
//                sum("taxAmount")
//                sum("totalAmount")
//                avg("discountPercent")
//                sum("discountAmount")
//            }
//        }
//        if(snapshotTotalUsageUnit[0] == null || snapshotTotalUsageUnit[0] == "null") {
//            
//        } else {
//            invoiceItem.put("usageUnits", snapshotTotalUsageUnit[0]);
//            invoiceItem.put("usageUnitPrice", snapshotTotalUsageUnit[1]);
//            invoiceItem.put("usageAmount", snapshotTotalUsageUnit[2]);
//            invoiceItem.put("taxPercent", snapshotTotalUsageUnit[3]); 
//            invoiceItem.put("taxAmount", snapshotTotalUsageUnit[4]); 
//            invoiceItem.put("totalAmount", snapshotTotalUsageUnit[5]);
//            invoiceItem.put("referenceItemId", "snapshot");
//            invoiceItem.put("discountPercent", snapshotTotalUsageUnit[6]);
//            invoiceItem.put("discountAmount", snapshotTotalUsageUnit[7]);
//            invoiceItem.put("referencePlanId", "snapshot");
//            invoiceItem.put("itemName", "snapshot(all)");
//            invoiceItem.put("units", "/GB/hr");
//            invoiceItem.put("plan", "all snapshot");
//        }
//        return invoiceItem;
//    }
//    
//    def getPayments(invoice) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        def paymentsCriteria = Payments.createCriteria()
//        
//        Calendar toDateCalendar = Calendar.getInstance(); 
//        toDateCalendar.setTime(invoice.date);
//        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//        toDateCalendar.set(Calendar.MINUTE, 59);
//        toDateCalendar.set(Calendar.SECOND, 59);
//        toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//        Date toDate = toDateCalendar.getTime() 
//
//        Calendar fromDateCalendar = Calendar.getInstance(); 
//        fromDateCalendar.setTime(invoice.previousInvoiceDate);
//        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
//        fromDateCalendar.set(Calendar.MINUTE, 00);
//        fromDateCalendar.set(Calendar.SECOND, 00);
//        fromDateCalendar.set(Calendar.MILLISECOND, 001);                   
//        Date fromDate = fromDateCalendar.getTime() 
//
//        def payments = paymentsCriteria.list {
//            eq("account", invoice.account)
//            and{
//                ge("paymentDate", fromDate) and { le("paymentDate", toDate) }            
//            }
//        }
//        
//        ArrayList<ArrayList<String>> paymentList = new ArrayList<ArrayList<String>>();  
//        for(int i=0; i < payments.size(); i++) { 
//            def item = payments[i]; 
//            HashMap payment = new HashMap();                
//            payment.put("id", item.id);
//            payment.put("date", dateFormat.format(item.paymentDate).toString());
//            payment.put("amount", item.amount);
//            payment.put("tokenNo", item.paymentToken); 
//            payment.put("totalAmount", item.totalAmount); 
//            payment.put("processingFee", item.processingFee); 
//            paymentList.add(payment)
//        }
//        return paymentList
//    }
//    
//    
//    
//    
//    def creditLimitReport(params) {
//        
//        def language = "";
//        def user = springSecurityService.currentUser 
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        
//        Date fromDate;  //
//        Date toDate;     
//        
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        
//        
//        if(params.forDateRange == "SELECTIVE") {
//            fromDate = givenDateFormater.parse(params.fromDate);
//            Date givenToDate = givenDateFormater.parse(params.toDate);
//            Calendar toDateCalendar = Calendar.getInstance(); 
//            toDateCalendar.setTime(givenToDate);
//            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//            toDateCalendar.set(Calendar.MINUTE, 59);
//            toDateCalendar.set(Calendar.SECOND, 59);
//            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//            toDate = toDateCalendar.getTime()
//        }
//        
//        def accountList = Account.withCriteria {
//            params.each { k, v ->
//                if(k == "forDateRange") {
//                    if(v != "ALL") {
//                        ge("nextBillingData", fromDate) and { le("nextBillingData", toDate) }    
//                    }
//                }
//            }
//        }
//        
//        
//        if(params.format == "csv") {
//            
//            def varContainingData = '"'+messageSource.getMessage('common.accountId', null, new Locale(language))+'", "'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.exceededAmount', null, new Locale(language))+'" \n';
//            
//            for(def account: accountList) {
//                
//                if(account.totalPayable > account.creditLimit) {
//                    varContainingData += '"' + account.id + '", '
//                    varContainingData += '"' + account.firstName + '", '
//                    varContainingData += '"' + (account.totalPayable - account.creditLimit) + '", '
//                    varContainingData += "\n"
//                }
//                
//            }
//            response.setHeader("Content-disposition", "attachment; filename=credit-limit-report.csv")  
//            render(contentType: "text/csv", text:varContainingData) 
//        } else {
//            
//            
//            ArrayList<ArrayList<String>> accountListArray = new ArrayList<ArrayList<String>>(); 
//            
//            for(def account: accountList) {
//                
//                if(account.totalPayable > account.creditLimit) {
//                    HashMap item = new HashMap();
//                    item.put("accountId", account.id);
//                    item.put("accountName", account.firstName);
//                    item.put("exAmount", account.totalPayable - account.creditLimit);
//                    accountListArray.add(item) 
//                }
//            }
//            [accountList: accountListArray, params: params] 
//            
//        }
//    }
//    
//    
//    def paymentPendingReport(params) { 
//        
//        Date fromDate; 
//        Date toDate;     
//        
//        
//        def language = "";
//        def user = springSecurityService.currentUser 
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        
//        
//        if(params.forDateRange == "SELECTIVE") {
//            fromDate = givenDateFormater.parse(params.fromDate);
//            Date givenToDate = givenDateFormater.parse(params.toDate);
//            Calendar toDateCalendar = Calendar.getInstance(); 
//            toDateCalendar.setTime(givenToDate);
//            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//            toDateCalendar.set(Calendar.MINUTE, 59);
//            toDateCalendar.set(Calendar.SECOND, 59);
//            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//            toDate = toDateCalendar.getTime()
//        }
//        
//        
//        def invoiceList = Invoice.withCriteria {
//            params.each { k, v ->
//                eq("status", InvoiceStatus.values()[6])
//                if(k == "forDateRange") {
//                    if(v != "ALL") {
//                        ge("date", fromDate) and { le("date", toDate) }    
//                    }
//                }
//            }
//        }
//        
//        if(params.format == "csv") {
//            
//            def varContainingData = '"'+messageSource.getMessage('common.accountId', null, new Locale(language))+'", "'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.pendingAmount', null, new Locale(language))+'" \n';
//            
//            for(def invoice: invoiceList) {
//                
//                if((invoice.previousBalance - invoice.payment) > 0) {
//
//                    varContainingData += '"' + invoice.account.id + '", '
//                    varContainingData += '"' + invoice.account.firstName + '", '
//                    varContainingData += '"' + (invoice.previousBalance - invoice.payment) + '", '
//                    varContainingData += "\n"
//                }
//            }
//            response.setHeader("Content-disposition", "attachment; filename=payment-pending-report.csv")  
//            render(contentType: "text/csv", text:varContainingData) 
//        } else {
//                        
//            ArrayList<ArrayList<String>> paymentListArray = new ArrayList<ArrayList<String>>(); 
//            
//            for(def invoice: invoiceList) {
//                
//                if((invoice.previousBalance - invoice.payment) > 0) {
//                  
//                    HashMap item = new HashMap();  
//                    item.put("accountId",  invoice.account.id);
//                    item.put("accountName",  invoice.account.firstName);
//                    item.put("amount", invoice.previousBalance - invoice.payment);            
//                    paymentListArray.add(item)
//                    
//                    
//                }
//            }
//            [paymentList: paymentListArray, params: params] 
//            
//        }
//        
//        
//    }
//    
//    def paymentReport() {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        def user = springSecurityService.currentUser 
//        def language = "";
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        ArrayList<ArrayList<String>> paymentItemArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList();
//        if(params.format == "csv") {
//            paymentReportInCsv(params)
//        } else {
//            if(params.forDateRange == "ALL" && params.forAccount == "ALL") {
//                tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
//                tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)))
//                def accountList = Account.findAll()
//                for (def account: accountList) {
//                    def paymentCriteria = Payments.createCriteria()
//                    def paymentAmount = paymentCriteria.list {
//                        eq("account", account)
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(paymentAmount[0] == null || paymentAmount[0] == "null") {
//                    } else {
//                        HashMap paymentItem = new HashMap();
//                        paymentItem.put("account", account.accountIdentifier);
//                        paymentItem.put("totalAmount", paymentAmount[0]);
//                        paymentItemArray.add(paymentItem)
//                    }
//                }
//                render(view: "paymentAllReport", model: [paymentItemArray: paymentItemArray, tableThArray: tableThArray]) 
//            } else if (params.forDateRange != "ALL" && params.forAccount == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    Date fromDate = givenDateFormater.parse(params.fromDate);
//                    Date givenToDate = givenDateFormater.parse(params.toDate);
//                    Calendar toDateCalendar = Calendar.getInstance(); 
//                    toDateCalendar.setTime(givenToDate);
//                    toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//                    toDateCalendar.set(Calendar.MINUTE, 59);
//                    toDateCalendar.set(Calendar.SECOND, 59);
//                    toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//                    Date toDate = toDateCalendar.getTime() 
//                    tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
//                    tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)))
//                    def accountList = Account.findAll()
//                    for (def account: accountList) {
//                        def paymentCriteria = Payments.createCriteria()
//                        def paymentAmount = paymentCriteria.list {
//                            eq("account", account)
//                            and {
//                                ge("paymentDate", fromDate) and { le("paymentDate", toDate) }  
//                            }
//                            projections {
//                                sum("totalAmount")
//                            }
//                        }
//                        if(paymentAmount[0] == null || paymentAmount[0] == "null") {
//                        } else {
//                            HashMap paymentItem = new HashMap();
//                            paymentItem.put("account", account.accountIdentifier);
//                            paymentItem.put("totalAmount", paymentAmount[0]);
//                            paymentItemArray.add(paymentItem)
//                        }
//                    }
//                    render(view: "paymentAllReport", model: [paymentItemArray: paymentItemArray, tableThArray: tableThArray]) 
//                }
//            } else if (params.forDateRange != "ALL" && params.forAccount != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    Date fromDate = givenDateFormater.parse(params.fromDate);
//                    Date givenToDate = givenDateFormater.parse(params.toDate);
//                    Calendar toDateCalendar = Calendar.getInstance(); 
//                    toDateCalendar.setTime(givenToDate);
//                    toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//                    toDateCalendar.set(Calendar.MINUTE, 59);
//                    toDateCalendar.set(Calendar.SECOND, 59);
//                    toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//                    Date toDate = toDateCalendar.getTime() 
//                    tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
//                    tableThArray.add(messageSource.getMessage('common.date', null, new Locale(language)))
//                    tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)))
//                    def accountList = Account.get(params.forAccount)
//                    for (def account: accountList) {
//                        def paymentCriteria = Payments.createCriteria()
//                        def paymentAmount = paymentCriteria.list {
//                            eq("account", account)
//                            and {
//                                ge("paymentDate", fromDate) and { le("paymentDate", toDate) }  
//                            }
//                        }
//                        for(def payment: paymentAmount) {
//                            Date inputdate = formater2.parse(payment.paymentDate.toString());
//                            String outputDate = formater.format(inputdate);
//                            HashMap paymentItem = new HashMap();
//                            paymentItem.put("account", account.accountIdentifier);
//                            paymentItem.put("date", outputDate);
//                            paymentItem.put("totalAmount", payment.totalAmount);
//                            paymentItemArray.add(paymentItem)
//                        }
//                    }
//                    render(view: "paymentReport", model: [paymentItemArray: paymentItemArray, tableThArray: tableThArray, account:Account.get(params.forAccount)]) 
//                }
//            } else if (params.forDateRange == "ALL" && params.forAccount != "ALL") {
//                tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
//                tableThArray.add(messageSource.getMessage('common.date', null, new Locale(language)))
//                tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)))
//                def accountList = Account.get(params.forAccount)
//                for (def account: accountList) {
//                    def paymentCriteria = Payments.createCriteria()
//                    def paymentAmount = paymentCriteria.list {
//                        eq("account", account)
//                    }
//                    for(def payment: paymentAmount) {
//                        Date inputdate = formater2.parse(payment.paymentDate.toString());
//                        String outputDate = formater.format(inputdate);
//                        HashMap paymentItem = new HashMap();
//                        paymentItem.put("account", account.accountIdentifier);
//                        paymentItem.put("date", outputDate);
//                        paymentItem.put("totalAmount", payment.totalAmount);
//                        paymentItemArray.add(paymentItem)
//                    }
//                }
//                render(view: "paymentReport", model: [paymentItemArray: paymentItemArray, tableThArray: tableThArray, account:Account.get(params.forAccount)])
//            }
//        }
//    }
//    
//    def paymentReportInCsv(params) {
//        def user = springSecurityService.currentUser 
//        def language = "";
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy"); 
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        def varContainingData;
//        if(params.forDateRange == "ALL" && params.forAccount == "ALL") {
//            varContainingData = '"'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'" \n';
//            def accountList = Account.findAll()
//            for (def account: accountList) {
//                def paymentCriteria = Payments.createCriteria()
//                def paymentAmount = paymentCriteria.list {
//                    eq("account", account)
//                    projections {
//                        sum("totalAmount")
//                    }
//                }
//                if(paymentAmount[0] == null || paymentAmount[0] == "null") {
//                } else {
//                    varContainingData += '"' + account.accountIdentifier + '", '
//                    varContainingData += '"' + paymentAmount[0] + '"'
//                    varContainingData += "\n"
//                }
//            }
//        } else if(params.forDateRange != "ALL" && params.forAccount == "ALL") {
//            varContainingData = '"'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'" \n';
//            if(params.fromDate != "" || params.toDate != "") {
//                Date fromDate = givenDateFormater.parse(params.fromDate);
//                Date givenToDate = givenDateFormater.parse(params.toDate);
//                Calendar toDateCalendar = Calendar.getInstance(); 
//                toDateCalendar.setTime(givenToDate);
//                toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//                toDateCalendar.set(Calendar.MINUTE, 59);
//                toDateCalendar.set(Calendar.SECOND, 59);
//                toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//                Date toDate = toDateCalendar.getTime() 
//                
//
//                def accountList = Account.findAll()
//                for (def account: accountList) {
//                    def paymentCriteria = Payments.createCriteria()
//                    def paymentAmount = paymentCriteria.list {
//                        eq("account", account)
//                        and {
//                            ge("paymentDate", fromDate) and { le("paymentDate", toDate) }  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(paymentAmount[0] == null || paymentAmount[0] == "null") {
//                    } else {
//                        varContainingData += '"' + account.accountIdentifier + '", '
//                        varContainingData += '"' + paymentAmount[0] + '"'
//                        varContainingData += "\n"
//                    }
//                }
//            }
//        } else if(params.forDateRange != "ALL" && params.forAccount != "ALL") {
//            varContainingData = '"'+messageSource.getMessage('common.account', null, new Locale(language))+'", ""'+messageSource.getMessage('common.date', null, new Locale(language))+'", ""'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'" \n';;
//            if(params.fromDate != "" || params.toDate != "") {
//                Date fromDate = givenDateFormater.parse(params.fromDate);
//                Date givenToDate = givenDateFormater.parse(params.toDate);
//                Calendar toDateCalendar = Calendar.getInstance(); 
//                toDateCalendar.setTime(givenToDate);
//                toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//                toDateCalendar.set(Calendar.MINUTE, 59);
//                toDateCalendar.set(Calendar.SECOND, 59);
//                toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//                Date toDate = toDateCalendar.getTime() 
//
//                def accountList = Account.get(params.forAccount)
//                for (def account: accountList) {
//                    def paymentCriteria = Payments.createCriteria()
//                    def paymentAmount = paymentCriteria.list {
//                        eq("account", account)
//                        and {
//                            ge("paymentDate", fromDate) and { le("paymentDate", toDate) }  
//                        }
//                    }
//                    for(def payment: paymentAmount) {
//                        Date inputdate = formater2.parse(payment.paymentDate.toString());
//                        String outputDate = formater.format(inputdate);
//                        varContainingData += '"' + account.accountIdentifier + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + payment.totalAmount + '"'
//                        varContainingData += "\n"
//                    }
//                }
//            }
//        } else if(params.forDateRange == "ALL" && params.forAccount != "ALL") {
//            varContainingData = '"'+messageSource.getMessage('common.account', null, new Locale(language))+'", ""'+messageSource.getMessage('common.date', null, new Locale(language))+'", ""'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'" \n';
//            def accountList = Account.get(params.forAccount)
//            for (def account: accountList) {
//                def paymentCriteria = Payments.createCriteria()
//                def paymentAmount = paymentCriteria.list {
//                    eq("account", account)
//                }
//                for(def payment: paymentAmount) {
//                    Date inputdate = formater2.parse(payment.paymentDate.toString());
//                    String outputDate = formater.format(inputdate);
//                    varContainingData += '"' + account.accountIdentifier + '", '
//                    varContainingData += '"' + outputDate + '", '
//                    varContainingData += '"' + payment.totalAmount + '"'
//                    varContainingData += "\n"
//                }
//            }
//        }
//           
//        response.setHeader("Content-disposition", "attachment; filename=payment-report.csv")  
//        render(contentType: "text/csv", text:varContainingData)  
//    }
//    
//    
//    def accountReport() {
//        
//        def accountList;
//        def accountCriteria = Account.createCriteria()
//    
//        if(params.forDateRange == "ALL") {
//
//            if(params.forAccount == "ALL") {
//                accountList = Account.findAllWhere(accountType: AccountType.values()[Integer.parseInt(params.accountType)])
//                //                def accountListArray = [];
//                //
//                //                def accounts = params.accountList.split(",");
//                //                for(def i=0; i < accounts.length; i++) {
//                //                    accountListArray[i] = Account.get(Integer.parseInt(accounts[i]))
//                //                }
//                //
//                //                accountList = accountCriteria.list {
//                //                    'in'("account", accountListArray)
//                //                }
//            } 
//
//        } else {
//            DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//            Date fromDate = formater.parse(params.fromDate);
//            Date givenToDate = formater.parse(params.toDate);
//            Calendar toDateCalendar = Calendar.getInstance(); 
//            toDateCalendar.setTime(givenToDate);
//            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//            toDateCalendar.set(Calendar.MINUTE, 59);
//            toDateCalendar.set(Calendar.SECOND, 59);
//            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//            Date toDate = toDateCalendar.getTime()
//
//            accountList = accountCriteria.list {
//                eq("accountType", AccountType.values()[Integer.parseInt(params.accountType)])
//                and{
//                    ge("signUpDate", fromDate) and { le("signUpDate", toDate) }  
//                }
//            }
//        } 
//        [accountList: accountList]
//    
//    }
//    
//    def graphReport(params) {
//        Date fromDate;
//        Date toDate;
//        def invoiceItemList;
//        def count = 0;
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Index No.")
//        tableThArray.add("Account")
//        tableThArray.add("Billable Item")
//        tableThArray.add("Item Name")
//        tableThArray.add("Item Reference Id")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
//        
//        
//        def invoiceList = Invoice.withCriteria {
//            params.each { k, v ->
//                if(k == "forAccount") {
//                    if(v != "ALL") {
//                        eq("account", Account.get(v));  
//                    }
//                     
//                } else if(k == "forDateRange") {
//                    if(v != "ALL") {
//                        ge("date", fromDate) and { le("date", toDate) }    
//                    }
//                }
//            }
//        }
//        
//        for(def invoice: invoiceList) {
//            Date inputdate = formater2.parse(invoice.date.toString());
//            String outputDate = outPutFormater.format(inputdate);
//            invoiceItemList = InvoiceItem.withCriteria {
//                eq("invoice", invoice);
//                and {
//                    params.each { k, v ->
//                        if(k == "forZone") {
//                            if(v == "NONZONE") {
//                            } else if(v == "ALL") {
//                                def zoneList = Zone.findAll()
//                                for(def zone: zoneList) {
//                                    or {
//                                        eq("zone", zone);  
//                                    }
//                                }
//                            } else {
//                                eq("zone", zone);   
//                            }
//                        } else if(k == "forZone") {
//                            
//                        }
//                    } 
//                }
//                
//                
//                
//            }
//            
//            
//            
//            
//            
//            if(params.forZone == "ALL"){
//                def zoneList = Zone.findAll()
//                billableItemList = BillableItem.findAllWhere(hasZone: false)
//                for(def zone: zoneList) {
//                    for(def billableItem : billableItemList) {
//                        invoiceItemList = InvoiceItem.withCriteria {
//                            eq("invoice", invoice);
//                            and {
//                                eq("billableItem", billableItem) 
//                                params.each { k, v ->
//                                    if(k == "forZone") {
//                                        if(v == "NONZONE") {
//                                        } else if(v == "ALL") {
//                                            eq("zone", zone); 
//                                        }
//                                    } 
//                                    //                                if(params.) {
//                                    //                                    
//                                    //                                }
//                                }
//                            }
//                            projections {
//                                sum("totalAmount")
//                            }
//                        }
//                        
//                    }
//                }
//            }
//            
//        }
//        
//        
//        
//        
//        
//    }
//    
//    def billableItemReportNew(params) {
//        Date fromDate;  
//        Date toDate;
//        def invoiceItemList;
//        def count = 0;
//        def language = "";
//        def user = springSecurityService.currentUser 
//        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
//            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
//            language = defaultLanguage.value
//        } else {
//            language = user.account.preferredLanguage
//        }
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add(messageSource.getMessage('common.indexNo', null, new Locale(language)))
//        tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
//        tableThArray.add(messageSource.getMessage('menu.admin.report.billableItem', null, new Locale(language)))
//        tableThArray.add(messageSource.getMessage('common.itemName', null, new Locale(language)))
//        tableThArray.add(messageSource.getMessage('common.itemReferenceId', null, new Locale(language)))
//        tableThArray.add(messageSource.getMessage('common.date', null, new Locale(language)))
//        tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)))
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
//        if(params.forDateRange == "SELECTIVE") {
//            fromDate = givenDateFormater.parse(params.fromDate);
//            Date givenToDate = givenDateFormater.parse(params.toDate);
//            Calendar toDateCalendar = Calendar.getInstance(); 
//            toDateCalendar.setTime(givenToDate);
//            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
//            toDateCalendar.set(Calendar.MINUTE, 59);
//            toDateCalendar.set(Calendar.SECOND, 59);
//            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
//            toDate = toDateCalendar.getTime()
//        }                
//        def accountCriteria = Account.createCriteria()        
//        def invoiceList = Invoice.withCriteria {
//            params.each { k, v ->
//                if(k == "forAccount") {
//                    if(v != "ALL") {
//                        eq("account", Account.get(v));  
//                    }                     
//                } else if(k == "forDateRange") {
//                    if(v != "ALL") {
//                        ge("date", fromDate) and { le("date", toDate) }    
//                    }
//                }
//            }
//        }
//                
//        for(def invoice: invoiceList) {
//            Date inputdate = formater2.parse(invoice.date.toString());
//            String outputDate = outPutFormater.format(inputdate);
//            if(params.forBillableItem == "ALL") {
//                def billableItemList;
//                if(params.forZone == "NONZONE") {
//                    billableItemList = BillableItem.findAllWhere(hasZone: false)
//                } else {
//                    billableItemList = BillableItem.findAllWhere(hasZone: true)
//                }
//                for(def billableItem : billableItemList) {
//                    invoiceItemList = InvoiceItem.withCriteria {
//                        eq("invoice", invoice);
//                        and {
//                            eq("billableItem", billableItem) 
//                            params.each { k, v ->
//                                if(k == "forZone") {
//                                    if(v == "NONZONE" || v == "ALL") {
//                                    } else {
//                                        eq("zone", Zone.get(params.forZone)); 
//                                    }
//                                } 
//                                //                                if(params.) {
//                                //                                    
//                                //                                }
//                            }
//                        }
//                    }
//                    for(def invoiceItem: invoiceItemList) {
//                        if(invoiceItem.totalAmount != 0) {
//                            count = count +1;
//                            HashMap item = new HashMap();
//                            item.put("count", count);
//                            item.put("account", invoice.account.accountIdentifier);
//                            item.put("billableItem", messageSource.getMessage(billableItem.name, null, new Locale(language)));
//                            item.put("itemReferenceId", invoiceItem.referenceItemId);
//                            item.put("date", outputDate);
//                            item.put("totalAmount", invoiceItem.totalAmount);
//                            if(invoiceItem.referenceItemName == "VirtualMachine") {
//                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
//                                if(vm) {
//                                    item.put("itemName", vm.name);
//                                    invoiceItemListArray.add(item) 
//                                }
//                                
//                            } else if(invoiceItem.referenceItemName =="Volume") {
//                                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
//                                if(volume) {
//                                    item.put("itemName", volume.name);
//                                    invoiceItemListArray.add(item)
//                                }
//                            } else if(invoiceItem.referenceItemName =="SetupCost") {
//                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
//                                if(vm) {
//                                    item.put("itemName", vm.name);
//                                    invoiceItemListArray.add(item); 
//                                }
//                            } else if(invoiceItem.referenceItemName == "Template") {
//                                def template = Template.findByTemplateReferenceId(invoiceItem.referenceItemId);
//                                if(template) {
//                                    item.put("itemName", template.name);
//                                    invoiceItemListArray.add(item)   
//                                }
//                                
//                            } else if(invoiceItem.referenceItemName == "Snapshot") {
//                                def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
//                                if(snapshot) {
//                                    item.put("itemName", snapshot.name);
//                                    invoiceItemListArray.add(item)
//                                }
//                            } else if(invoiceItem.referenceItemName == "VM Snapshot") {
//                                def vmSnapshot = VMSnapshot.findByReferenceId(invoiceItem.referenceItemId);
//                                if(vmSnapshot) {
//                                    item.put("itemName", vmSnapshot.name);
//                                    invoiceItemListArray.add(item)
//                                }
//                            } else {
//                                item.put("itemName", invoiceItem.referenceItemName);
//                                invoiceItemListArray.add(item)
//                            }
//                             
//                        }
//                    }
//                }
//            } else {
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    invoiceItemList = InvoiceItem.withCriteria {
//                        eq("invoice", invoice);
//                        and {
//                            eq("billableItem", billableItem) 
//                            params.each { k, v ->
//                                if(k == "forZone") {
//                                    if(v == "NONZONE" || v == "ALL") {
//                                    } else {
//                                        eq("zone", Zone.get(params.forZone)); 
//                                    }
//                                } 
//                                else if(params.plan != "ALL" && billableItems.length == 1) {
//                                    eq("referencePlanId", params.plan); 
//                                } 
//                            }
//                        }
//                    }
//                    for(def invoiceItem: invoiceItemList) {
//                        if(invoiceItem.totalAmount != 0) {
//                            count = count +1;
//                            HashMap item = new HashMap();
//                            item.put("count", count);
//                            item.put("account", invoice.account.accountIdentifier);
//                            item.put("billableItem", messageSource.getMessage(billableItem.name, null, new Locale(language)));
//                            item.put("itemReferenceId", invoiceItem.referenceItemId);
//                            item.put("date", outputDate);
//                            item.put("totalAmount", invoiceItem.totalAmount);
//                            if(invoiceItem.referenceItemName == "VirtualMachine") {
//                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
//                                if(vm) {
//                                    item.put("itemName", vm.name);
//                                    invoiceItemListArray.add(item) 
//                                }
//                                
//                            } else if(invoiceItem.referenceItemName =="Volume") {
//                                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
//                                if(volume) {
//                                    item.put("itemName", volume.name);
//                                    invoiceItemListArray.add(item)
//                                }
//                            } else if(invoiceItem.referenceItemName =="SetupCost") {
//                                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
//                                if(vm) {
//                                    item.put("itemName", vm.name);
//                                    invoiceItemListArray.add(item); 
//                                }
//                            } else if(invoiceItem.referenceItemName == "Template") {
//                                def template = Template.findByTemplateReferenceId(invoiceItem.referenceItemId);
//                                if(template) {
//                                    item.put("itemName", template.name);
//                                    invoiceItemListArray.add(item)   
//                                }
//                                
//                            } else if(invoiceItem.referenceItemName == "Snapshot") {
//                                def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
//                                if(snapshot) {
//                                    item.put("itemName", snapshot.name);
//                                    invoiceItemListArray.add(item)
//                                }
//                                    
//                            } else {
//                                item.put("itemName", invoiceItem.referenceItemName);
//                                invoiceItemListArray.add(item)
//                            }                        
//                        }
//                        
//                    }
//                }  
//            }
//        }
//        if(params.format == "csv") {            
//            def varContainingData = ''+messageSource.getMessage("common.account", null, new Locale(language))+', "'+messageSource.getMessage("menu.admin.report.billableItem", null, new Locale(language))+'", "'+messageSource.getMessage("common.itemName", null, new Locale(language))+'", "'+messageSource.getMessage("common.itemReferenceId", null, new Locale(language))+'", "'+messageSource.getMessage("common.date", null, new Locale(language))+'", "'+messageSource.getMessage("common.totalAmount", null, new Locale(language))+'" \n';
//            for(def item: invoiceItemListArray) {
//                varContainingData += '"'+item.account+'",'
//                varContainingData += '"'+item.billableItem+'",'
//                varContainingData += '"'+item.itemName+'",'
//                varContainingData += '"'+item.itemReferenceId+'",'
//                varContainingData += '"'+item.date+'",'
//                varContainingData += '"'+item.totalAmount+'"'  
//                varContainingData += "\n"
//            }
//            response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//            render(contentType: "text/csv", text:varContainingData) 
//        } else {
//            def zone;
//            if(params.forZone == "NONZONE" || params.forZone == "ALL") { 
//                zone = params.forZone; 
//            } else {
//                zone = Zone.get(params.forZone).name; 
//            }
//            
//            def planName = ComputingOffer.findByOfferReferenceId(params.plan) ? ComputingOffer.findByOfferReferenceId(params.plan).name : DiskOffer.findByDiskOfferReferenceId(params.plan)?.name
//            def billableItemName = "";
//            if(params.forBillableItem == "ALL") { 
//                billableItemName = "ALL"
//            } else {
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    billableItemName += messageSource.getMessage(billableItems[i], null, new Locale(language))+","
//                }
//            }
//            if(params.forAccount == "ALL") { 
//                [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray, params: params, zone: zone, plan:planName, billableItemName:billableItemName]  
//            } else {
//                [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray, params: params, zone: zone, plan: planName, account:Account.get(params.forAccount), billableItemName:billableItemName] 
//            }
//        }
//    }
//    
//    def billableItemReport() {
//        if (params.format == "csv") {
//            if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
//                billableItemReportInCsvForAll(params) 
//            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
//                billableItemReportInCsvForAccount(params) 
//            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
//                billableItemReportInCsvForZone(params) 
//            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
//                billableItemReportInCsvForBillableItem(params) 
//            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvForDateRange(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvForDateRangeAndAccount(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvForDateRangeAndBillableItem(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvForDateRangeWithBillableItemAndAccount(params) 
//                }
//            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvBillableItemAndAccount(params) 
//                }
//            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
//                billableItemReportInCsvZoneAndAccount(params) 
//            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
//                billableItemReportInCsvZoneAndBillableItem(params) 
//            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvDateRangeWithZoneAndBillableItem(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvDateRangeWithZoneAndBillableItemAndAccount(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvDateRangeWithZoneAndAccount(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInCsvDateRangeAndZone(params) 
//                }
//            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
//                billableItemReportInCsvZoneAndBillableItemAndAccount(params) 
//            }
//        } else {
//            if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
//                billableItemReportInHtmlForAll(params) 
//            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
//                billableItemReportInHtmlForAccount(params) 
//            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
//                billableItemReportInHtmlForZone(params) 
//            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
//                billableItemReportInHtmlForBillableItem(params) 
//            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlForDateRange(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlForDateRangeAndAccount(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlForDateRangeAndBillableItem(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlForDateRangeWithBillableItemAndAccount(params) 
//                }
//            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlBillableItemAndAccount(params) 
//                }
//            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
//                billableItemReportInHtmlZoneAndAccount(params) 
//            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
//                billableItemReportInHtmlZoneAndBillableItem(params) 
//            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlDateRangeWithZoneAndBillableItem(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlDateRangeWithZoneAndBillableItemAndAccount(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlDateRangeWithZoneAndAccount(params) 
//                }
//            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
//                if(params.fromDate != "" || params.toDate != "") {
//                    billableItemReportInHtmlDateRangeAndZone(params) 
//                }
//            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
//                billableItemReportInHtmlZoneAndBillableItemAndAccount(params) 
//            }
//           
//        }
//        
//    }
//    
//    def billableItemReportInHtmlZoneAndBillableItemAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def zone = Zone.get(params.forZone);
//                
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    } 
//    
//    def billableItemReportInCsvZoneAndBillableItemAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        def zone = Zone.get(params.forZone);
//                
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    def billableItemReportInHtmlDateRangeAndZone(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def zone = Zone.get(params.forZone);
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and {
//                    ge("date", fromDate) and { le("date", toDate) }    
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = BillableItem.findAll()
//                for(def billableItem: billableItems) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    
//    def billableItemReportInCsvDateRangeAndZone(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        def zone = Zone.get(params.forZone);
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and {
//                    ge("date", fromDate) and { le("date", toDate) }    
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = BillableItem.findAll()
//                for(def billableItem: billableItems) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    def billableItemReportInHtmlDateRangeWithZoneAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def zone = Zone.get(params.forZone);
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and {
//                    ge("date", fromDate) and { le("date", toDate) }    
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = BillableItem.findAll()
//                for(def billableItem: billableItems) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    
//    def billableItemReportInCsvDateRangeWithZoneAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        def zone = Zone.get(params.forZone);
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and {
//                    ge("date", fromDate) and { le("date", toDate) }    
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = BillableItem.findAll()
//                for(def billableItem: billableItems) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    def billableItemReportInHtmlDateRangeWithZoneAndBillableItemAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def zone = Zone.get(params.forZone);
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and {
//                    ge("date", fromDate) and { le("date", toDate) }    
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    def billableItemReportInCsvDateRangeWithZoneAndBillableItemAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        def zone = Zone.get(params.forZone);
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and {
//                    ge("date", fromDate) and { le("date", toDate) }    
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    
//    def billableItemReportInHtmlDateRangeWithZoneAndBillableItem(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def zone = Zone.get(params.forZone);
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and {
//                    ge("date", fromDate) and { le("date", toDate) }    
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    } 
//    
//    
//    def billableItemReportInCsvDateRangeWithZoneAndBillableItem(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        def zone = Zone.get(params.forZone);
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and {
//                    ge("date", fromDate) and { le("date", toDate) }    
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    def billableItemReportInHtmlZoneAndBillableItem(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def zone = Zone.get(params.forZone);
//                
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    } 
//    
//    def billableItemReportInCsvZoneAndBillableItem(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        def zone = Zone.get(params.forZone);
//                
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    def billableItemReportInHtmlZoneAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def zone = Zone.get(params.forZone);
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate); 
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                            and {
//                                eq("zone", zone)
//                            }
//                        } 
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//
//                }
//
//            } 
//
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    
//    def billableItemReportInCsvZoneAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        def zone = Zone.get(params.forZone);
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    def billableItemReportInHtmlForZone(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def zone = Zone.get(params.forZone);
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                            and {
//                                eq("zone", zone)
//                            }
//                        } 
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//
//                }
//
//            } 
//
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    def billableItemReportInCsvForZone(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        def zone = Zone.get(params.forZone);
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                            and {
//                                eq("zone", zone)
//                            }
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    def billableItemReportInHtmlBillableItemAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    } 
//    
//    def billableItemReportInCsvBillableItemAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    
//    def billableItemReportInHtmlForDateRangeWithBillableItemAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and{
//                    ge("date", fromDate) and { le("date", toDate) }   
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    } 
//    
//    
//    def billableItemReportInCsvForDateRangeWithBillableItemAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and{
//                    ge("date", fromDate) and { le("date", toDate) }   
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    
//    def billableItemReportInHtmlForDateRangeAndBillableItem(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and{
//                    ge("date", fromDate) and { le("date", toDate) }   
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    } 
//    
//    
//    def billableItemReportInCsvForDateRangeAndBillableItem(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and{
//                    ge("date", fromDate) and { le("date", toDate) }   
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    def billableItemReportInHtmlForDateRangeAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and{
//                    ge("date", fromDate) and { le("date", toDate) }   
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    } 
//    
//    
//    def billableItemReportInCsvForDateRangeAndAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and{
//                    ge("date", fromDate) and { le("date", toDate) }   
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    
//    def billableItemReportInHtmlForDateRange(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and{
//                    ge("date", fromDate) and { le("date", toDate) }   
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//                }
//            } 
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    def billableItemReportInCsvForDateRange(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        
//        Date fromDate = givenDateFormater.parse(params.fromDate);
//        Date toDate = givenDateFormater.parse(params.toDate);
//        
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceCriteria = Invoice.createCriteria()
//            def invoiceList = invoiceCriteria.list {
//                eq("account", account)
//                and{
//                    ge("date", fromDate) and { le("date", toDate) }   
//                }
//            }
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    
//    def billableItemReportInHtmlForBillableItem(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//
//                }
//
//            } 
//
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    def billableItemReportInCsvForBillableItem(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItems = params.billableItemList.split(",");
//                for(def i=0; i < billableItems.length; i++) {
//                    def billableItem = BillableItem.findByName(billableItems[i])
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem) 
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//    
//    
//    def billableItemReportInHtmlForAll(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//
//                }
//
//            } 
//
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    def billableItemReportInHtmlForAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        
//        def invoiceItemList;
//        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
//        ArrayList tableThArray = new ArrayList(); 
//        tableThArray.add("Account")
//        tableThArray.add("Item")
//        tableThArray.add("Date")
//        tableThArray.add("Total Amount")
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else {                   
//                        HashMap invoiceItem = new HashMap();
//                        invoiceItem.put("account", invoice.account.accountIdentifier);
//                        invoiceItem.put("item", billableItem.name);
//                        invoiceItem.put("date", outputDate);
//                        invoiceItem.put("totalAmount", totalAmount[0]);
//                        invoiceItemListArray.add(invoiceItem)
//                    }
//
//                }
//
//            } 
//
//        }
//        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
//    }
//    
//    
//    def billableItemReportInCsvForAccount(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        
//        def accountList = Account.get(params.forAccount)
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//     
//    def billableItemReportInCsvForAll(params) {
//        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
//        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
//        def invoiceItemList;
//        
//        def accountList = Account.findAll()
//        for (def account: accountList) {
//            def invoiceList = Invoice.findAllWhere(account: account);
//            for (def invoice: invoiceList) {
//                Date inputdate = formater2.parse(invoice.date.toString());
//                String outputDate = formater.format(inputdate);
//                def billableItemList = BillableItem.findAll() 
//                for(def billableItem : billableItemList) {
//                    def invoiceItemCriteria = InvoiceItem.createCriteria()
//                    def totalAmount = invoiceItemCriteria.list {
//                        eq("invoice", invoice)
//                        and {
//                            eq("billableItem", billableItem)  
//                        }
//                        projections {
//                            sum("totalAmount")
//                        }
//                    }
//                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
//                    } else { 
//                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
//                        varContainingData += '"' + billableItem.name + '", '
//                        varContainingData += '"' + outputDate + '", '
//                        varContainingData += '"' + totalAmount[0] + '"'  
//                        varContainingData += "\n"
//                    }
//
//                }
//
//            } 
//
//        }
//        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
//        render(contentType: "text/csv", text:varContainingData) 
//        
//    }
//     
    def getSSHKey(params) {
        
        def sshKey = SSHKeys.get(Long.parseLong(params.keyId));
        println("sshKey: "+sshKey)
        
        response.contentType = "application/octet-stream"
        response.setHeader("Content-disposition", "attachment; filename="+sshKey.name+".pem")  
        response.outputStream << sshKey.privateKey.getBytes() 
    }
}

enum UsageType2 {
    
    NOT_AVAILABLE,//0
    RUNNING_VM_USAGE, //1
    ALLOCATED_VM_USAGE, //2
    IP_ADDRESS_USAGE, //3
    NETWORK_USAGE_BYTE_SENT, //4
    NETWORK_USAGE_BYTE_RECEIVED, //5 
    VOLUME_USAGE, //6
    TEMPLATE_USAGE, //7 
    ISO_USAGE, //8
    SNAPSHOT_USAGE, //9
    SECURITY_GROUP_USAGE, //10 
    LOAD_BALANCER_USAGE, //11
    PORT_FORWARDING_USAGE, //12
    NETWORK_OFFERING_USAGE, //13
    VPN_USER_USAGE, //14
    VM_SNAPSHOT // 15
}
