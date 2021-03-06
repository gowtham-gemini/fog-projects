package com.assistanz.fogpanel

import grails.converters.JSON
import java.text.SimpleDateFormat
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import java.text.DateFormat
import java.text.DecimalFormat
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource

@Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
class PdfController { 
    
    def springSecurityService;
    def authenticateService;
    InvoiceService invoiceService     
    MessageSource messageSource
    ConfigService configService;
    DecimalFormat df = new DecimalFormat("#.###############");      
    
    def currentUsage() {        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency            
        def role = springSecurityService.getPrincipal().getAuthorities()                        
        def dateFormatValue = configService.getDateFormat();             
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        double billableItemCount = 1       
        def invoice = Invoice.get(params.invoiceId)
        def costCount;
        ArrayList zoneListArray = new ArrayList();  
        def invoiceItems = InvoiceItem.findAllWhere(invoice : invoice)
        def zoneList = Zone.findAll()
        def zoneCount = 0
        for(def zone : zoneList) {
            def zoneCostCriteria = InvoiceItem.createCriteria()
            def zoneCost = zoneCostCriteria.list {
                eq("invoice", invoice)
                and{
                   eq("zone", zone)  
                }
                projections {
                    sum("totalAmount")
                }
            }            
            
            def billableItemList = getBillableItem(zone, invoice, zoneCount, billableItemCount);
            billableItemCount = billableItemList[1]
            if(zoneCost[0] == null || zoneCost[0] == "null") {
//                HashMap zoneItem = new HashMap();
//                zoneItem.put("zoneCost", 0); 
//                zoneItem.put("zone", zone.aliasName); 
//                zoneItem.put("zoneCount", zoneCount); 
//                zoneItem.put("billableItem", billableItemList[0]); 
//                zoneListArray.add(zoneItem)
            } else {
                HashMap zoneItem = new HashMap();
                zoneItem.put("zoneCost",  Math.ceil(zoneCost[0] * 100d) / 100d); 
                zoneItem.put("zone", zone.aliasName); 
                zoneItem.put("zoneCount", zoneCount); 
                zoneItem.put("billableItem", billableItemList[0]); 
                zoneListArray.add(zoneItem)
                zoneCount +=1;
            }
            
            
            
        }
        
        def nonZoneCostCriteria = InvoiceItem.createCriteria()
        
        def nonZoneCost = nonZoneCostCriteria.list {
            eq("invoice", invoice)
             and{
                   isNull("zone")  
                }
            projections {
                sum("totalAmount")
            }
        }
        def nonZoneBillableItemList = getBillableItem(null, invoice, zoneCount, billableItemCount);
        billableItemCount = nonZoneBillableItemList[1]
        if(nonZoneCost[0] == null || nonZoneCost[0] == "null") {
//            HashMap zoneItem = new HashMap();
//            zoneItem.put("zoneCost", 0); 
//            zoneItem.put("zone","Non-Zone Billable Items"); 
//            zoneItem.put("zoneCount", zoneCount+1); 
//            zoneItem.put("billableItem", nonZoneBillableItemList[0]); 
//            zoneListArray.add(zoneItem)
        } else {
            HashMap zoneItem = new HashMap();
            zoneItem.put("zoneCost",  Math.ceil(nonZoneCost[0] * 100d) / 100d); 
            zoneItem.put("zone", "Misc"); 
            zoneItem.put("billableItem", nonZoneBillableItemList[0]); 
            zoneItem.put("zoneCount", zoneCount+1); 
            zoneListArray.add(zoneItem)
        }
        
        
        if(invoice.status.name() == "DRAFT") {
            invoiceService.updateDraftInvoice(invoice, invoice.account)
        }
                
        Calendar c = Calendar.getInstance();  
        c.add(Calendar.DATE, -1);  
        Date d = c.getTime();   
        
        def totalAmount = 0;
        def credit = 0;
        
        if((invoice.totalAmount- invoice.account.credit) < 0) {
            totalAmount = 0
            credit = -1 * (invoice.totalAmount - invoice.account.credit)
        } else {
            totalAmount = invoice.totalAmount
            credit = 0
        }
        
        [currency:currency , userAccount: invoice.account,  
            invoice: invoice, totalAmount: totalAmount, credit: credit,
            invoiceDate: dateFormat.format(d).toString(), zoneCost: zoneListArray, refund: invoice.refundAmount
        ]
    }
    
    
    def getBillableItem(zone, invoice, zoneCount, billableItemCount) {
        ArrayList billableItemListArray = new ArrayList(); 
        def user = springSecurityService.currentUser 
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        if(zone == null) {            
            def billableItemList = BillableItem.findAllWhere(hasZone: false)             
            for(def billableItem : billableItemList) {
                def billableItemCostCriteria = InvoiceItem.createCriteria()
                def billableItemCost = billableItemCostCriteria.list {
                    eq("invoice", invoice)
                    and {
                        and {
                            eq("billableItem", billableItem)   
                        }
                        isNull("zone")   
                    }
                    projections {
                        sum("totalAmount")
                    }
                }                
                def invoiceItem = getInvoiceItem(billableItem, zone, invoice, billableItemCount);
                if(billableItemCost[0] == null || billableItemCost[0] == "null") {
//                    HashMap bItem = new HashMap();
//                    bItem.put("zone", "Non-Zone Billable Items"); 
//                    bItem.put("billableItemZoneCount", zoneCount); 
//                    bItem.put("billableItem", billableItem.referenceItemName);
//                    bItem.put("billableItemCount", billableItemCount);
//                    bItem.put("billableItemCost", 0);
//                    bItem.put("invoiceItem", invoiceItem);
//                    billableItemListArray.add(bItem)
                } else {                                        
                    HashMap bItem = new HashMap();
                    bItem.put("zone", "Misc"); 
                    bItem.put("billableItemZoneCount", zoneCount); 
                    if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName || billableItem.referenceItemName == BillableItem.get(13).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.instance', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName || billableItem.referenceItemName == BillableItem.get(14).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('menu.admin.services.storage', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName || billableItem.referenceItemName == BillableItem.get(15).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.snapshot', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(4).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.template', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(5).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.bandwidth', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(6).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billing.discount', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(7).referenceItemName) {
                         bItem.put("billableItem", messageSource.getMessage('common.billing.LateFee', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(8).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billing.customItem', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(9).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billing.recurringItem', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(10).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.vm.stopedInstance', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(11).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.setupCost', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(12).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.ip.additionalIP', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(16).referenceItemName || billableItem.referenceItemName == BillableItem.get(17).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.snapshot.VMSnapshot', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(18).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billableItem.loadBalancer', null, new Locale(language)));
                    }  else if(billableItem.referenceItemName == BillableItem.get(19).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billableItem.portForwarding', null, new Locale(language)));
                    }  else if(billableItem.referenceItemName == BillableItem.get(20).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billableItem.vpnConnection', null, new Locale(language)));
                    }  
                    
                    bItem.put("billableItemCost", Math.ceil(billableItemCost[0] * 100d) / 100d);
                    bItem.put("billableItemCount", billableItemCount);
                    bItem.put("invoiceItem", invoiceItem);
                    billableItemListArray.add(bItem)
                    billableItemCount +=1
                }
             }
            
            return [billableItemListArray, billableItemCount]; 
            
        } else {
            
            
//            def billableItemList = BillableItem.findAllWhere(hasZone: true)

            
            
            def billableItemCriteria = BillableItem.createCriteria()
            def billableItemList = billableItemCriteria.list {
                    eq("hasZone", true)
                    or{ ne("id", 13.toLong())}
                    or{ne("id", 14.toLong())}
                    or{ne("id", 15.toLong())}
                    or{ne("id", 16.toLong())}
            }
            
            def vmMonCostCriteria = InvoiceItem.createCriteria()
                def vmMonbillableItemCost = vmMonCostCriteria.list {
                    eq("invoice", invoice)
                    and{
                        and {
                           eq("billableItem", BillableItem.get(13))   
                        }
                       eq("zone", zone)  
                    }
                    projections {
                        sum("totalAmount")
                    }
                }
                
            def diskMonCostCriteria = InvoiceItem.createCriteria()
                def diskMonbillableItemCost = diskMonCostCriteria.list {
                    eq("invoice", invoice)
                    and{
                        and {
                           eq("billableItem", BillableItem.get(14))   
                        }
                       eq("zone", zone)  
                    }
                    projections {
                        sum("totalAmount")
                    }
                }
                
                def snapMonbillableItemCostCriteria = InvoiceItem.createCriteria()
                def snapMonbillableItemCost = snapMonbillableItemCostCriteria.list {
                    eq("invoice", invoice)
                    and{
                        and {
                           eq("billableItem", BillableItem.get(15))   
                        }
                       eq("zone", zone)  
                    }
                    projections {
                        sum("totalAmount")
                    }
                }
                
                def VMSnapMonbillableItemCostCriteria = InvoiceItem.createCriteria()
                def VMSnapMonbillableItemCost = VMSnapMonbillableItemCostCriteria.list {
                    eq("invoice", invoice)
                    and{
                        and {
                           eq("billableItem", BillableItem.get(16))   
                        }
                       eq("zone", zone)  
                    }
                    projections {
                        sum("totalAmount")
                    }
                }
                
            def vmMonCost;
            def diskMonCost;
            def snapMonCost;
            def VMSnapMonCost;
            if(vmMonbillableItemCost[0] == null || vmMonbillableItemCost[0] == "null") {
                vmMonCost = 0;
            } else {
                vmMonCost = vmMonbillableItemCost[0];
            }
            if(diskMonbillableItemCost[0] == null || diskMonbillableItemCost[0] == "null") {
               diskMonCost = 0;
            } else {
                diskMonCost = diskMonbillableItemCost[0];
            }
            if(snapMonbillableItemCost[0] == null || snapMonbillableItemCost[0] == "null") {
                snapMonCost = 0;
            } else {
                snapMonCost = snapMonbillableItemCost[0];
            }
            if(VMSnapMonbillableItemCost[0] == null || VMSnapMonbillableItemCost[0] == "null") {
                VMSnapMonCost = 0;
            } else {
                VMSnapMonCost = VMSnapMonbillableItemCost[0];
            }
            
            
             for(def billableItem : billableItemList) {
                def billableItemCostCriteria = InvoiceItem.createCriteria()
                def billableItemCost = billableItemCostCriteria.list {
                    eq("invoice", invoice)
                    and{
                        and {
                           eq("billableItem", billableItem)   
                        }
                       eq("zone", zone)  
                    }
                    projections {
                        sum("totalAmount")
                    }
                }
                def invoiceItem = getInvoiceItem(billableItem, zone, invoice, billableItemCount);
                if(billableItemCost[0] == null || billableItemCost[0] == "null") {
                    HashMap bItem = new HashMap();
                    bItem.put("zone", zone.aliasName); 
                    bItem.put("billableItemZoneCount", zoneCount); 
                    if((billableItem.referenceItemName == BillableItem.get(1).referenceItemName || billableItem.referenceItemName == BillableItem.get(13).referenceItemName) && vmMonCost != 0) {
                        bItem.put("billableItem", messageSource.getMessage('menu.user.cloud.instance', null, new Locale(language)));
                        bItem.put("billableItemCost", Math.ceil((0.0 + vmMonCost ) * 100d) / 100d);
                        bItem.put("billableItemCount", billableItemCount);
                        bItem.put("invoiceItem", invoiceItem);
                        billableItemListArray.add(bItem)
                        billableItemCount +=1
                    } else if((billableItem.referenceItemName == BillableItem.get(2).referenceItemName || billableItem.referenceItemName == BillableItem.get(14).referenceItemName)  && diskMonCost != 0) {
                        bItem.put("billableItem", messageSource.getMessage('menu.user.cloud.storage', null, new Locale(language)));
                        bItem.put("billableItemCost", Math.ceil((0.0 + diskMonCost)  * 100d) / 100d);
                        bItem.put("billableItemCount", billableItemCount);
                        bItem.put("invoiceItem", invoiceItem);
                        billableItemListArray.add(bItem)
                        billableItemCount +=1
                    } else if((billableItem.referenceItemName == BillableItem.get(3).referenceItemName || billableItem.referenceItemName == BillableItem.get(15).referenceItemName)  && snapMonCost != 0) {
                        bItem.put("billableItem", messageSource.getMessage('common.snapshot', null, new Locale(language)));
                        bItem.put("billableItemCost", Math.ceil((0.0 + snapMonCost)  * 100d) / 100d);
                        bItem.put("billableItemCount", billableItemCount);
                        bItem.put("invoiceItem", invoiceItem);
                        billableItemListArray.add(bItem)
                        billableItemCount +=1
                    } else if((billableItem.referenceItemName == BillableItem.get(16).referenceItemName || billableItem.referenceItemName == BillableItem.get(17).referenceItemName)  && VMSnapMonCost != 0) {
                        bItem.put("billableItem", messageSource.getMessage('common.snapshot.VMSnapshot', null, new Locale(language)));
                        bItem.put("billableItemCost", Math.ceil((0.0 + VMSnapMonCost)  * 100d) / 100d);
                        bItem.put("billableItemCount", billableItemCount);
                        bItem.put("invoiceItem", invoiceItem);
                        billableItemListArray.add(bItem)
                        billableItemCount +=1
                    }
                } else {                                        
                    HashMap bItem = new HashMap();
                    bItem.put("zone", zone.aliasName); 
                    bItem.put("billableItemZoneCount", zoneCount); 
                    if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName || billableItem.referenceItemName == BillableItem.get(13).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.instance', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName || billableItem.referenceItemName == BillableItem.get(14).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('menu.admin.services.storage', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName || billableItem.referenceItemName == BillableItem.get(15).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.snapshot', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(4).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.template', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(5).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.bandwidth', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(6).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billing.discount', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(7).referenceItemName) {
                         bItem.put("billableItem", messageSource.getMessage('common.billing.LateFee', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(8).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billing.customItem', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(9).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billing.recurringItem', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(10).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.vm.stopedInstance', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(11).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.setupCost', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(12).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.ip.additionalIP', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(16).referenceItemName || billableItem.referenceItemName == BillableItem.get(17).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.snapshot.VMSnapshot', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(18).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billableItem.loadBalancer', null, new Locale(language)));
                    } else if(billableItem.referenceItemName == BillableItem.get(19).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billableItem.portForwarding', null, new Locale(language)));
                    }  else if(billableItem.referenceItemName == BillableItem.get(20).referenceItemName) {
                        bItem.put("billableItem", messageSource.getMessage('common.billableItem.vpnConnection', null, new Locale(language)));
                    }  
                    
                    if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName) {
                        bItem.put("billableItemCost", Math.ceil((billableItemCost[0] + vmMonCost ) * 100d) / 100d);
                    } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName ) {
                       bItem.put("billableItemCost", Math.ceil((billableItemCost[0] + diskMonCost)  * 100d) / 100d);
                    } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName) {
                        bItem.put("billableItemCost", Math.ceil((billableItemCost[0] + snapMonCost)  * 100d) / 100d);
                    } else if(billableItem.referenceItemName == BillableItem.get(17).referenceItemName) {
                        bItem.put("billableItemCost", Math.ceil((billableItemCost[0] + VMSnapMonCost)  * 100d) / 100d);
                    } else {
                        bItem.put("billableItemCost", Math.ceil(billableItemCost[0] * 100d) / 100d);
                    }
                    
                    bItem.put("billableItemCount", billableItemCount);
                    bItem.put("invoiceItem", invoiceItem);
                    billableItemListArray.add(bItem)
                    billableItemCount +=1
                }
             }
             
            return  [billableItemListArray, billableItemCount]; 
        }
    }
    
    def getInvoiceItem(billableItem, zone, invoice, billableItemCount) {
        ArrayList invoiceListArray = new ArrayList(); 
        
        def invoiceItemLIst;
        
        if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName) {
            invoiceItemLIst = InvoiceItem.executeQuery("select item from InvoiceItem item " +
                     "where (item.billableItem = ? OR item.billableItem = ? ) and item.invoice = ? and item.zone = ?",
                     [BillableItem.get(1), BillableItem.get(13), invoice, zone]);
            
        } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName) {  
            invoiceItemLIst = InvoiceItem.executeQuery("select item from InvoiceItem item " +
                     "where (item.billableItem = ? OR item.billableItem = ? ) and item.invoice = ? and item.zone = ?",
                     [BillableItem.get(2), BillableItem.get(14), invoice, zone]);
                 
        } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName) {
            invoiceItemLIst = InvoiceItem.executeQuery("select item from InvoiceItem item " +
                     "where (item.billableItem = ? OR item.billableItem = ? ) and item.invoice = ? and item.zone = ?",
                     [BillableItem.get(3), BillableItem.get(15), invoice, zone]);
        } else if(billableItem.referenceItemName == BillableItem.get(17).referenceItemName) {
            invoiceItemLIst = InvoiceItem.executeQuery("select item from InvoiceItem item " +
                     "where (item.billableItem = ? OR item.billableItem = ? ) and item.invoice = ? and item.zone = ?",
                     [BillableItem.get(16), BillableItem.get(17), invoice, zone]);
        } else {
            invoiceItemLIst = InvoiceItem.findAllWhere(billableItem: billableItem, invoice: invoice, zone: zone)
        }
               
        for(def invoiceItem : invoiceItemLIst) {            
            if(invoiceItem.billableItem == BillableItem.get(1)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", vm.displayName); 
                    iItem.put("plan", computeOffer.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("priceUnit", " /hr");
                    iItem.put("usageUnit", " /hr");
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(vm.deleted == true || vm.deleted == "true") {
                        iItem.put("vmStatus", "Expunging"); 
                    } else {
                        if(vm.computingOffer == computeOffer) {
                           iItem.put("vmStatus", vm.state);
                        } else {
                           iItem.put("vmStatus", "Expunging"); 
                        }
                    }
                    invoiceListArray.add(iItem)
                }
                
            } else if(invoiceItem.billableItem == BillableItem.get(2)) {
                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                def diskOffer = DiskOffer.findByDiskOfferReferenceId(invoiceItem.referencePlanId)
                if(diskOffer && volume) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", volume.name); 
                    iItem.put("plan", diskOffer.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", " /hr/GB");
                    iItem.put("usageUnit", " /hr");
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(volume.deleted == true || volume.deleted == "true") {
                        iItem.put("vmStatus", "Expunging");
                    } else {
                        if(volume.diskOffer == diskOffer) {
                            iItem.put("vmStatus", "");
                        } else {
                            iItem.put("vmStatus", "Expunging");
                        }
                    }
                    
                    invoiceListArray.add(iItem)
                }
                
            } else if(invoiceItem.billableItem == BillableItem.get(3)) {
                def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
                if(snapshot) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", snapshot.name); 
                    iItem.put("plan", snapshot.volume.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", " /hr/GB");
                    iItem.put("usageUnit", " /hr");
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(snapshot.deleted == true || snapshot.deleted == "true") {
                        iItem.put("vmStatus", "Expunging");
                    } else {
                      iItem.put("vmStatus", "");  
                    }
                    invoiceListArray.add(iItem)
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(4)) {
                def template = Template.findByTemplateReferenceId(invoiceItem.referenceItemId);
                if(template) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", template.name); 
                    iItem.put("plan", "-");
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", "");
                    iItem.put("usageUnit", "");
                    iItem.put("cost", invoiceItem.totalAmount);
                    iItem.put("vmStatus", "");
                    invoiceListArray.add(iItem) 
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(5)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", vm.displayName); 
                    iItem.put("plan", computeOffer.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                     iItem.put("priceUnit", " /GB");
                    iItem.put("usageUnit", " /GB");
                    iItem.put("cost", invoiceItem.totalAmount);
                    iItem.put("vmStatus", "");
                    invoiceListArray.add(iItem) 
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(6)) {
                
                HashMap iItem = new HashMap();
                iItem.put("name", invoiceItem.referenceItemName); 
                iItem.put("plan", "-");
                iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                 iItem.put("priceUnit", " %");
                iItem.put("usageUnit", "");
                iItem.put("cost", invoiceItem.totalAmount);
                iItem.put("vmStatus", "");
                invoiceListArray.add(iItem)
            } else if(invoiceItem.billableItem == BillableItem.get(7)) {
                HashMap iItem = new HashMap();
                iItem.put("name", invoiceItem.referenceItemName); 
                iItem.put("plan", "-");
                iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("priceUnit", "");
                iItem.put("usageUnit", "");
                iItem.put("cost", invoiceItem.totalAmount);
                iItem.put("vmStatus", "");
                invoiceListArray.add(iItem)
                
            } else if(invoiceItem.billableItem == BillableItem.get(8)) {
                HashMap iItem = new HashMap();
                iItem.put("name", invoiceItem.referenceItemName); 
                iItem.put("plan", "-");
                iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("priceUnit", "");
                iItem.put("usageUnit", "");
                iItem.put("cost", invoiceItem.totalAmount);
                iItem.put("vmStatus", "");
                invoiceListArray.add(iItem)
                
            } else if(invoiceItem.billableItem == BillableItem.get(9)) {
                HashMap iItem = new HashMap();
                iItem.put("name", invoiceItem.referenceItemName); 
                iItem.put("plan", "-");
                iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("priceUnit", "");
                iItem.put("usageUnit", "");
                iItem.put("cost", invoiceItem.totalAmount);
                iItem.put("vmStatus", "");
                invoiceListArray.add(iItem)
                
            } else if(invoiceItem.billableItem == BillableItem.get(10)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", vm.displayName); 
                    iItem.put("plan", computeOffer.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", " /hr");
                    iItem.put("usageUnit", " /hrs");
                    iItem.put("cost", invoiceItem.totalAmount);
                    iItem.put("vmStatus", "");
                    invoiceListArray.add(iItem)
                }
                
            } else if(invoiceItem.billableItem == BillableItem.get(11)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", vm.displayName); 
                    iItem.put("plan", computeOffer.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", "");
                    iItem.put("usageUnit", "");
                    iItem.put("cost", invoiceItem.totalAmount);
                    iItem.put("vmStatus", "");
                    invoiceListArray.add(iItem) 
                
                }
            } else if(invoiceItem.billableItem == BillableItem.get(12)) {
//                def vmip = VmIp.findByIpReferenceId(invoiceItem.referenceItemId);
//                if(vmip) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("name", vmip.ipAddress); 
//                    iItem.put("plan", "-");
//                    iItem.put("price", df.format(invoiceItem.usageUnitPrice));
//                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("cost", invoiceItem.totalAmount);
//                    iItem.put("vmStatus", "");
//                    invoiceListArray.add(iItem) 
//                
//                } else if(invoiceItem.referenceItemName == "Secondary IP For Network") {
                    HashMap iItem = new HashMap();
                    iItem.put("name", invoiceItem.referenceItemId); 
                    iItem.put("plan", "-");
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", "");
                    iItem.put("usageUnit", "");
                    iItem.put("cost", invoiceItem.totalAmount);
                    iItem.put("vmStatus", "");
                    invoiceListArray.add(iItem) 
//                }
            } else if(invoiceItem.billableItem == BillableItem.get(13)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", vm.displayName); 
                    iItem.put("plan", computeOffer.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", " /month");
                    iItem.put("usageUnit", "");
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(vm.deleted == true || vm.deleted == "true") {
                        iItem.put("vmStatus", "Expunging"); 
                    } else {
                        if(vm.computingOffer == computeOffer) {
                           iItem.put("vmStatus", vm.state);
                        } else {
                           iItem.put("vmStatus", "Expunging"); 
                        }
                    }
                    invoiceListArray.add(iItem) 
                
                }
            } else if(invoiceItem.billableItem == BillableItem.get(14)) {
                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                def diskOffer = DiskOffer.findByDiskOfferReferenceId(invoiceItem.referencePlanId)
                if(volume) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", volume.name); 
                    iItem.put("plan", volume.diskOffer.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", " /GB/month");
                    iItem.put("usageUnit", "");
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(volume.deleted == true || volume.deleted == "true") {
                        iItem.put("vmStatus", "Expunging");
                    } else {
                        if(volume.diskOffer == diskOffer) {
                            iItem.put("vmStatus", "");
                        } else {
                            iItem.put("vmStatus", "Expunging");
                        }
                    }
                    invoiceListArray.add(iItem)
                }
                
            } else if(invoiceItem.billableItem == BillableItem.get(15)) {
                def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
                if(snapshot) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", snapshot.name); 
                    iItem.put("plan", snapshot.volume.name);
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("priceUnit", " /GB/month");
                    iItem.put("usageUnit", "");
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(snapshot.deleted == true || snapshot.deleted == "true") {
                        iItem.put("vmStatus", "Expunging");
                    } else {
                      iItem.put("vmStatus", "");  
                    }
                    invoiceListArray.add(iItem)
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(16)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                if(vm) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", vm.displayName); 
                    iItem.put("plan", "-");
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", " /GB/month");
                    iItem.put("usageUnit", "");
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(vm.deleted == true || vm.deleted == "true") {
                        iItem.put("vmStatus", "Expunging"); 
                    } else {
                        iItem.put("vmStatus", vm.state);
                    }
                    invoiceListArray.add(iItem) 
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(17)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                if(vm) {
                    def vmSnapshot = VMSnapshot.findAllWhere(virtualMachine: vm, deleted: false);
                    
                    HashMap iItem = new HashMap();
                    iItem.put("name", vm.displayName); 
                    iItem.put("plan", "-");
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", " /hr/GB");
                    iItem.put("usageUnit", " /hrs");
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(!vmSnapshot) {
                        iItem.put("vmStatus", "Expunging"); 
                    } else {
                        iItem.put("vmStatus", "");
                    }
                    invoiceListArray.add(iItem)
                }
            } else if(invoiceItem.billableItem == BillableItem.get(18)) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", invoiceItem.referenceItemName); 
                    iItem.put("plan", "-");
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", "");
                    iItem.put("usageUnit", "");
                    iItem.put("cost", invoiceItem.totalAmount);
                    def lb = LoadBalancer.findByReferenceId(invoiceItem.referenceItemId);
                    if(lb) {
                        iItem.put("vmStatus", "");
                    } else{
                        iItem.put("vmStatus", "Expunging");
                    }
                    invoiceListArray.add(iItem) 
            } else if(invoiceItem.billableItem == BillableItem.get(19)) {
                    HashMap iItem = new HashMap();
                    iItem.put("name", invoiceItem.referenceItemName); 
                    iItem.put("plan", "-");
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", "");
                    iItem.put("usageUnit", "");
                    iItem.put("cost", invoiceItem.totalAmount);
                    def lb = PortForwarding.findByReferenceId(invoiceItem.referenceItemId);
                    if(lb) {
                        iItem.put("vmStatus", "");
                    } else{
                        iItem.put("vmStatus", "Expunging");
                    }
                    invoiceListArray.add(iItem) 
            } else if(invoiceItem.billableItem == BillableItem.get(20)) {
                    HashMap iItem = new HashMap();
                    def vpn = VPNConnection.findByReferenceId(invoiceItem.referenceItemId);
                    iItem.put("name", "VPC:"+vpn.vpc.name +" (IP:"+vpn.vpnGateway.userIPAddress.publicIPAddress+")"); 
                    iItem.put("plan", "-");
                    iItem.put("price", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("hours", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("priceUnit", ""); 
                    iItem.put("usageUnit", ""); 
                    iItem.put("cost", invoiceItem.totalAmount);
                    if(vpn.deleted == false) {
                        iItem.put("vmStatus", "");
                    } else{
                        iItem.put("vmStatus", "Expunging");
                    }
                    invoiceListArray.add(iItem) 
            }
        }
        return invoiceListArray
        
    }
    
    
    def invoiceSummary() {
        Config configDateFormat =  Config.findByName(Config.DATE_FORMAT);
        SimpleDateFormat dateFormat = new SimpleDateFormat(configDateFormat.value);
        
        def invoice = Invoice.get(params.invoiceId)
                
        def billableItemList = getInvoiceSummaryBillableItem(invoice)               
                
        def paymentHistory = getPayments(invoice); 
//        def snapshot = getSnapshot(invoice)
//        invoiceItemList.add(snapshot)
        
        def dueDate;
        if(invoice.dueDate == null || invoice.dueDate == "null") {
            dueDate = "-"
        } else {
            dueDate = dateFormat.format(invoice.dueDate).toString()
        }
        if(invoice.status.name() == "DRAFT") {
            invoiceService.updateDraftInvoice(invoice, invoice.account)
        }
        
        ArrayList<String> orgAddressDetails = new ArrayList<String>();  
        
        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS).value);
        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_EXTENSION).value);
        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_CITY).value);
        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_STATE).value);
        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY).value);
        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_BILLING_PHONE_NUMBER1).value);
        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_BILLING_FAX_NUMBER1).value);
        orgAddressDetails.add(Config.findByName(Config.ORGANISATION_ADDRESS_ZIP).value);
        
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency
        
        //response.setContentType('application/pdf');
        [currency:currency, userAccount: invoice.account, 
            billableItemList: billableItemList, invoice: invoice, 
            invoiceDate: dateFormat.format(invoice.date).toString(), 
            dueDate: dueDate, payments: paymentHistory, 
            orgAddressDetails: orgAddressDetails, orgName: Config.findByName(Config.ORGANISATION_NAME).value, lateFee: Config.findByName(Config.LATE_FEE_MINIMUM_AMOUNT).value]
    }
    
    
    def getInvoiceSummaryBillableItem(invoice) {        
        ArrayList billableItemListArray = new ArrayList();         
        def billableItemList = BillableItem.findAll();
        def language = "";
        def user = springSecurityService.currentUser 
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }

        for(def billableItem: billableItemList) {                        
            def billableItemCostCriteria = InvoiceItem.createCriteria()
            def billableItemCost = billableItemCostCriteria.list {
                eq("invoice", invoice)
                and {
                    eq("billableItem", billableItem)    
                }
                projections {
                    sum("totalAmount")
                }
            }
            
            if(billableItemCost[0] == null || billableItemCost[0] == "null") {                
            } else {                
                def invoiceItem = getInvoiceSummaryInvoiceItems(billableItem, invoice);                
                HashMap bItem = new HashMap();               
                if(billableItem.referenceItemName == BillableItem.get(1).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.runningInstance", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(2).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("menu.admin.services.storage", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(3).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.snapShot", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(4).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.template", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(5).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.bandWidth", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(6).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.discount", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(7).referenceItemName) {
                     bItem.put("billableItem", messageSource.getMessage("common.billableItem.lateFee", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(8).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.custom", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(9).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.recurringItem", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(10).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.stoppedInstance", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(11).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.setupCost", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(12).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.ip.additionalIP", null, new Locale(language)));
                } else if(billableItem.referenceItemName == "Secondary IP For Network") {
                    bItem.put("billableItem", messageSource.getMessage("common.ip.additionalIP", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(13).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.monthlyInstance", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(14).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.monthlyVolume", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(15).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.monthlySnapshot", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(16).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.monthlyVMSnapshot", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(17).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.vmSnapshot", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(18).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.loadBalancer", null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(19).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage('common.billableItem.portForwarding', null, new Locale(language)));
                } else if(billableItem.referenceItemName == BillableItem.get(20).referenceItemName) {
                    bItem.put("billableItem", messageSource.getMessage('common.billableItem.vpnConnection', null, new Locale(language)));
                } else {
                    bItem.put("billableItem", messageSource.getMessage("common.billableItem.custom", null, new Locale(language)));
                }
                bItem.put("billableItemCost", Math.ceil(billableItemCost[0] * 100d) / 100d);
                bItem.put("invoiceItem", invoiceItem);
                billableItemListArray.add(bItem)
            }            
        }        
        return billableItemListArray;        
    }        
    def getInvoiceSummaryInvoiceItems(billableItem, invoice) {
        
        def language = "";
        def user = springSecurityService.currentUser 
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        ArrayList invoiceListArray = new ArrayList(); 
        
        def invoiceItemLIst = InvoiceItem.findAllWhere(billableItem: billableItem, invoice: invoice)
        
        for(def invoiceItem : invoiceItemLIst) {
            if(invoiceItem.billableItem == BillableItem.get(1)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", vm.displayName + " (" + computeOffer.name + ")");
                    iItem.put("units",messageSource.getMessage("common.hours", null, new Locale(language)));
                    iItem.put("plan", computeOffer.name);
                    invoiceListArray.add(iItem)
                }
                
            } else if(invoiceItem.billableItem == BillableItem.get(2)) {
                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                def diskOffer = DiskOffer.findByDiskOfferReferenceId(invoiceItem.referencePlanId)
                if(diskOffer && volume) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", volume.name + " (" + diskOffer.name + ")");
                    iItem.put("units",  messageSource.getMessage("common.gbHrs", null, new Locale(language)));
                    iItem.put("plan", diskOffer.name);               
                    invoiceListArray.add(iItem)
                }
                
            } else if(invoiceItem.billableItem == BillableItem.get(3)) {
                def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
                if(snapshot) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", snapshot.name + " (" + snapshot.volume.name + ")");
                    iItem.put("units", messageSource.getMessage("common.gbHrs", null, new Locale(language)));
                    iItem.put("plan", "-");
                    invoiceListArray.add(iItem)
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(4)) {
                def template = Template.findByTemplateReferenceId(invoiceItem.referenceItemId);
                if(template) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", invoiceItem.referenceItemName + "("+ template.name +")");
                    iItem.put("units", "-");
                    iItem.put("plan", "");
                    invoiceListArray.add(iItem) 
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(5)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", "Bandwidth" + " (" +vm.displayName + ")");
                    iItem.put("units", "/" + messageSource.getMessage("common.gb", null, new Locale(language)));
                    iItem.put("plan", computeOffer.name);
                    invoiceListArray.add(iItem) 
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(6)) {
                HashMap iItem = new HashMap();
                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("usageAmount", invoiceItem.usageAmount);
                iItem.put("taxPercent", invoiceItem.taxPercent); 
                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                iItem.put("totalAmount", invoiceItem.totalAmount);
                iItem.put("referenceItemId", invoiceItem.referenceItemId);
                iItem.put("discountPercent", invoiceItem.discountPercent);
                iItem.put("discountAmount", invoiceItem.discountAmount);
                iItem.put("referencePlanId", invoiceItem.referencePlanId);
                iItem.put("itemName", invoiceItem.referenceItemName);
                iItem.put("units", "%");
                iItem.put("plan", "");
                invoiceListArray.add(iItem)
            } else if(invoiceItem.billableItem == BillableItem.get(7)) {
                HashMap iItem = new HashMap();
                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("usageAmount", invoiceItem.usageAmount);
                iItem.put("taxPercent", invoiceItem.taxPercent); 
                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                iItem.put("totalAmount", invoiceItem.totalAmount);
                iItem.put("referenceItemId", invoiceItem.referenceItemId);
                iItem.put("discountPercent", invoiceItem.discountPercent);
                iItem.put("discountAmount", invoiceItem.discountAmount);
                iItem.put("referencePlanId", invoiceItem.referencePlanId);
                iItem.put("itemName", invoiceItem.referenceItemName);
                iItem.put("units", "-");
                iItem.put("plan", "");
                invoiceListArray.add(iItem)
                
            } else if(invoiceItem.billableItem == BillableItem.get(8)) {
                HashMap iItem = new HashMap();
                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("usageAmount", invoiceItem.usageAmount);
                iItem.put("taxPercent", invoiceItem.taxPercent); 
                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                iItem.put("totalAmount", invoiceItem.totalAmount);
                iItem.put("referenceItemId", invoiceItem.referenceItemId);
                iItem.put("discountPercent", invoiceItem.discountPercent);
                iItem.put("discountAmount", invoiceItem.discountAmount);
                iItem.put("referencePlanId", invoiceItem.referencePlanId);
                iItem.put("itemName", invoiceItem.referenceItemName);
                iItem.put("units", "-");
                iItem.put("plan", "");
                invoiceListArray.add(iItem)
                
            } else if(invoiceItem.billableItem == BillableItem.get(9)) {
                HashMap iItem = new HashMap();
                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("usageAmount", invoiceItem.usageAmount);
                iItem.put("taxPercent", invoiceItem.taxPercent); 
                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                iItem.put("totalAmount", invoiceItem.totalAmount);
                iItem.put("referenceItemId", invoiceItem.referenceItemId);
                iItem.put("discountPercent", invoiceItem.discountPercent);
                iItem.put("discountAmount", invoiceItem.discountAmount);
                iItem.put("referencePlanId", invoiceItem.referencePlanId);
                iItem.put("itemName", invoiceItem.referenceItemName);
                iItem.put("units", "-");
                iItem.put("plan", "");
                invoiceListArray.add(iItem)
                
            } else if(invoiceItem.billableItem == BillableItem.get(10)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", "Stopped Instance" + " (" + vm.displayName + ")");
                    iItem.put("units", messageSource.getMessage("common.hours", null, new Locale(language)));
                    iItem.put("plan", computeOffer.name);
                    invoiceListArray.add(iItem)
                }
                
            } else if(invoiceItem.billableItem == BillableItem.get(11)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", vm.displayName + " (Setup Cost)");
                    iItem.put("units", "-");
                    iItem.put("plan", computeOffer.name);
                    invoiceListArray.add(iItem) 
                
                }
            } else if(invoiceItem.billableItem == BillableItem.get(12)) {               
//                def vmip = VmIp.findByIpReferenceId(invoiceItem.referenceItemId);
//                if(vmip) {
//                    HashMap iItem = new HashMap();
//                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
//                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice));
//                    iItem.put("usageAmount", invoiceItem.usageAmount);
//                    iItem.put("taxPercent", invoiceItem.taxPercent); 
//                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount)); 
//                    iItem.put("totalAmount", invoiceItem.totalAmount);
//                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
//                    iItem.put("discountPercent", invoiceItem.discountPercent);
//                    iItem.put("discountAmount", invoiceItem.discountAmount);
//                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
//                    iItem.put("itemName", "Additional IP" + "("+  vmip.ipAddress +")");
//                    iItem.put("units", "-");
//                    iItem.put("plan", "");
//                    invoiceListArray.add(iItem) 
//                
//                } else if(invoiceItem.referenceItemName == "Secondary IP For Network") {
                    
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", "Additional IP" + "("+  invoiceItem.referenceItemId +")");
                    iItem.put("units", "-");
                    iItem.put("plan", "");
                    invoiceListArray.add(iItem) 
//                }
            } else if(invoiceItem.billableItem == BillableItem.get(13)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                def computeOffer = ComputingOffer.findByOfferReferenceId(invoiceItem.referencePlanId)
                if(vm && computeOffer) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", vm.displayName + " (" + computeOffer.name + ")");
                    iItem.put("units", messageSource.getMessage("common.months", null, new Locale(language)));
                    iItem.put("plan", computeOffer.name);
                    invoiceListArray.add(iItem) 
                
                }
            } else if(invoiceItem.billableItem == BillableItem.get(14)) {
                def volume = Volume.findByVolumeReferenceId(invoiceItem.referenceItemId);
                def diskOffer = DiskOffer.findByDiskOfferReferenceId(invoiceItem.referencePlanId)
                if(diskOffer && volume) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", volume.name + " (" + diskOffer.name + ")");
                    iItem.put("units", messageSource.getMessage("common.gbMonth", null, new Locale(language)));
                    iItem.put("plan", diskOffer.name);               
                    invoiceListArray.add(iItem)
                }
                
            } else if(invoiceItem.billableItem == BillableItem.get(15)) {
                def snapshot = Snapshot.findBySnapshotReferenceId(invoiceItem.referenceItemId);
                if(snapshot) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName", snapshot.name + " (" + snapshot.volume.name + ")");
                    iItem.put("units", messageSource.getMessage("common.gbMonth", null, new Locale(language)));
                    iItem.put("plan", "-");
                    invoiceListArray.add(iItem)
                }
                
                
            } else if(invoiceItem.billableItem == BillableItem.get(16)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                if(vm) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName",  "VM Snapshot"+ " (" + vm.displayName + ")");
                    iItem.put("units", messageSource.getMessage("common.gbMonth", null, new Locale(language)));
                    iItem.put("plan", "-");
                    invoiceListArray.add(iItem)
                }

            } else if(invoiceItem.billableItem == BillableItem.get(17)) {
                def vm = VirtualMachine.findByReferenceId(invoiceItem.referenceItemId);
                if(vm) {
                    HashMap iItem = new HashMap();
                    iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                    iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                    iItem.put("usageAmount", invoiceItem.usageAmount);
                    iItem.put("taxPercent", invoiceItem.taxPercent); 
                    iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                    iItem.put("totalAmount", invoiceItem.totalAmount);
                    iItem.put("referenceItemId", invoiceItem.referenceItemId);
                    iItem.put("discountPercent", invoiceItem.discountPercent);
                    iItem.put("discountAmount", invoiceItem.discountAmount);
                    iItem.put("referencePlanId", invoiceItem.referencePlanId);
                    iItem.put("itemName",  "VM Snapshot"+ " (" + vm.displayName + ")");
                    iItem.put("units", messageSource.getMessage("common.gbHrs", null, new Locale(language)));
                    iItem.put("plan", "-");
                    invoiceListArray.add(iItem)
                }
            } else if(invoiceItem.billableItem == BillableItem.get(18)) {                       
                    
                HashMap iItem = new HashMap();
                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("usageAmount", invoiceItem.usageAmount);
                iItem.put("taxPercent", invoiceItem.taxPercent); 
                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                iItem.put("totalAmount", invoiceItem.totalAmount);
                iItem.put("referenceItemId", invoiceItem.referenceItemId);
                iItem.put("discountPercent", invoiceItem.discountPercent);
                iItem.put("discountAmount", invoiceItem.discountAmount);
                iItem.put("referencePlanId", invoiceItem.referencePlanId);
                iItem.put("itemName", invoiceItem.referenceItemName);
                iItem.put("units", "-");
                iItem.put("plan", "");
                invoiceListArray.add(iItem) 
            } else if(invoiceItem.billableItem == BillableItem.get(19)) {                       
                    
                HashMap iItem = new HashMap();
                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("usageAmount", invoiceItem.usageAmount);
                iItem.put("taxPercent", invoiceItem.taxPercent); 
                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                iItem.put("totalAmount", invoiceItem.totalAmount);
                iItem.put("referenceItemId", invoiceItem.referenceItemId);
                iItem.put("discountPercent", invoiceItem.discountPercent);
                iItem.put("discountAmount", invoiceItem.discountAmount);
                iItem.put("referencePlanId", invoiceItem.referencePlanId);
                iItem.put("itemName", invoiceItem.referenceItemName);
                iItem.put("units", "-");
                iItem.put("plan", "");
                invoiceListArray.add(iItem) 
            } else if(invoiceItem.billableItem == BillableItem.get(20)) {                       
                    
                HashMap iItem = new HashMap();
                iItem.put("usageUnits", Math.ceil(invoiceItem.usageUnits * 100d) / 100d);
                iItem.put("usageUnitPrice", df.format(invoiceItem.usageUnitPrice).toDouble());
                iItem.put("usageAmount", invoiceItem.usageAmount);
                iItem.put("taxPercent", invoiceItem.taxPercent); 
                iItem.put("taxAmount", df.format(invoiceItem.taxAmount).toDouble()); 
                iItem.put("totalAmount", invoiceItem.totalAmount);
                iItem.put("referenceItemId", invoiceItem.referenceItemId);
                iItem.put("discountPercent", invoiceItem.discountPercent);
                iItem.put("discountAmount", invoiceItem.discountAmount);
                iItem.put("referencePlanId", invoiceItem.referencePlanId);
                def vpn = VPNConnection.findByReferenceId(invoiceItem.referenceItemId);
                iItem.put("itemName", "VPC:"+vpn.vpc.name +" (IP:"+vpn.vpnGateway.userIPAddress.publicIPAddress+")");
                iItem.put("units", "-");
                iItem.put("plan", "");
                invoiceListArray.add(iItem) 
            }
        }
        return invoiceListArray
    }
    
//    def invoiceSummary() {     
//        
////        type.name()
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        
//        
//        def invoice = Invoice.get(params.invoiceId)
//        def costCount;
//        ArrayList<ArrayList<String>> invoiceItemList = new ArrayList<ArrayList<String>>();  
//        def invoiceItems = InvoiceItem.findAllWhere(invoice : invoice)
//        for(int i=0; i < invoiceItems.size(); i++) { 
//            def item = invoiceItems[i];                
//            if(item.referenceItemName == "VirtualMachine") {
//                HashMap invoiceItem = new HashMap();
//                def vm = VirtualMachine.findByReferenceId(item.referenceItemId);
//                def computeOffer = ComputingOffer.findByOfferReferenceId(item.referencePlanId)
//                
//                if(vm && computeOffer) {
//                    invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                    invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                    invoiceItem.put("usageAmount", item.usageAmount);
//                    invoiceItem.put("taxPercent", item.taxPercent); 
//                    invoiceItem.put("taxAmount", item.taxAmount); 
//                    invoiceItem.put("totalAmount", item.totalAmount);
//                    invoiceItem.put("referenceItemId", item.referenceItemId);
//                    invoiceItem.put("discountPercent", item.discountPercent);
//                    invoiceItem.put("discountAmount", item.discountAmount);
//                    invoiceItem.put("referencePlanId", item.referencePlanId);
//                    invoiceItem.put("itemName", vm.displayName + " (" + computeOffer.name + ")");
//                    invoiceItem.put("units", "/hr");
//                    invoiceItem.put("plan", computeOffer.name);
//                    invoiceItemList.add(invoiceItem)
//                }
//                
//               
//            } else if(item.referenceItemName == "Volume") {
//                HashMap invoiceItem = new HashMap();
//                def volume = Volume.findByVolumeReferenceId(item.referenceItemId);
//                if(volume) {
//                    invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                    invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                    invoiceItem.put("usageAmount", item.usageAmount);
//                    invoiceItem.put("taxPercent", item.taxPercent); 
//                    invoiceItem.put("taxAmount", item.taxAmount); 
//                    invoiceItem.put("totalAmount", item.totalAmount);
//                    invoiceItem.put("referenceItemId", item.referenceItemId);
//                    invoiceItem.put("discountPercent", item.discountPercent);
//                    invoiceItem.put("discountAmount", item.discountAmount);
//                    invoiceItem.put("referencePlanId", item.referencePlanId);
//                    invoiceItem.put("itemName", volume.name + " (" + volume.diskOffer.name + ")");
//                    invoiceItem.put("units", "/GB/hr");
//                    invoiceItem.put("plan", volume.diskOffer.name);
//                    invoiceItemList.add(invoiceItem)
//                }                
//            } else if(item.referenceItemName == "Snapshot") {
//                HashMap invoiceItem = new HashMap();
//                def snap = Snapshot.findBySnapshotReferenceId(item.referenceItemId);
//                if(snap) {
//                    invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                    invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                    invoiceItem.put("usageAmount", item.usageAmount);
//                    invoiceItem.put("taxPercent", item.taxPercent); 
//                    invoiceItem.put("taxAmount", item.taxAmount); 
//                    invoiceItem.put("totalAmount", item.totalAmount);
//                    invoiceItem.put("referenceItemId", item.referenceItemId);
//                    invoiceItem.put("discountPercent", item.discountPercent);
//                    invoiceItem.put("discountAmount", item.discountAmount);
//                    invoiceItem.put("referencePlanId", item.referencePlanId);
//                    invoiceItem.put("itemName", snap.name + " (" + snap.volume.name + ")");
//                    invoiceItem.put("units", "/GB/hr");
//                    invoiceItem.put("plan", "-");
//                    invoiceItemList.add(invoiceItem)
//                } 
//            } else if(item.referenceItemName == "Bandwidth") {
//                HashMap invoiceItem = new HashMap();
//                def vm = VirtualMachine.findByReferenceId(item.referenceItemId);
//                def computeOffer = ComputingOffer.findByOfferReferenceId(item.referencePlanId);
//                
//                if(vm && computeOffer) {
//                    invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                    invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                    invoiceItem.put("usageAmount", item.usageAmount);
//                    invoiceItem.put("taxPercent", item.taxPercent); 
//                    invoiceItem.put("taxAmount", item.taxAmount); 
//                    invoiceItem.put("totalAmount", item.totalAmount);
//                    invoiceItem.put("referenceItemId", item.referenceItemId);
//                    invoiceItem.put("discountPercent", item.discountPercent);
//                    invoiceItem.put("discountAmount", item.discountAmount);
//                    invoiceItem.put("referencePlanId", item.referencePlanId);
//                    invoiceItem.put("itemName", "Bandwidth" + " (" + vm.displayName + ")");
//                    invoiceItem.put("units", "/GB");
//                    invoiceItem.put("plan", computeOffer.name);
//                    invoiceItemList.add(invoiceItem)
//                }
//            } else if(item.referenceItemName == "SetupCost") {
//                HashMap invoiceItem = new HashMap();
//                def vm = VirtualMachine.findByReferenceId(item.referenceItemId);
//                def computeOffer = ComputingOffer.findByOfferReferenceId(item.referencePlanId);
//                
//                if(vm && computeOffer) {
//                    
//                    invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                    invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                    invoiceItem.put("usageAmount", item.usageAmount);
//                    invoiceItem.put("taxPercent", item.taxPercent); 
//                    invoiceItem.put("taxAmount", item.taxAmount); 
//                    invoiceItem.put("totalAmount", item.totalAmount);
//                    invoiceItem.put("referenceItemId", item.referenceItemId);
//                    invoiceItem.put("discountPercent", item.discountPercent);
//                    invoiceItem.put("discountAmount", item.discountAmount);
//                    invoiceItem.put("referencePlanId", item.referencePlanId);
//                    invoiceItem.put("itemName", vm.displayName + " (setup cost)");
//                    invoiceItem.put("units", "-");
//                    invoiceItem.put("plan", computeOffer.name);
//                    invoiceItemList.add(invoiceItem)
//                }
//                
//                
//            } else if(item.referenceItemName == "Template") {
//                def template = Template.findByTemplateReferenceId(item.referenceItemId);
//                
//                HashMap invoiceItem = new HashMap();
//                invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                invoiceItem.put("usageAmount", item.usageAmount);
//                invoiceItem.put("taxPercent", item.taxPercent); 
//                invoiceItem.put("taxAmount", item.taxAmount); 
//                invoiceItem.put("totalAmount", item.totalAmount);
//                invoiceItem.put("referenceItemId", item.referenceItemId);
//                invoiceItem.put("discountPercent", item.discountPercent);
//                invoiceItem.put("discountAmount", item.discountAmount);
//                invoiceItem.put("referencePlanId", item.referencePlanId);
//                invoiceItem.put("itemName", item.referenceItemName + "("+ template.name +")");
//                invoiceItem.put("units", "-");
//                invoiceItem.put("plan", "");
//                invoiceItemList.add(invoiceItem)
//            } else if(item.referenceItemName == "SecondaryIPCost") {
//                def secIp = VmIp.findByIpReferenceId(item.referenceItemId);
//                
//                HashMap invoiceItem = new HashMap();
//                invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                invoiceItem.put("usageAmount", item.usageAmount);
//                invoiceItem.put("taxPercent", item.taxPercent); 
//                invoiceItem.put("taxAmount", item.taxAmount); 
//                invoiceItem.put("totalAmount", item.totalAmount);
//                invoiceItem.put("referenceItemId", item.referenceItemId);
//                invoiceItem.put("discountPercent", item.discountPercent);
//                invoiceItem.put("discountAmount", item.discountAmount);
//                invoiceItem.put("referencePlanId", item.referencePlanId);
//                invoiceItem.put("itemName", "Additional IP" + "("+ secIp.ipAddress +")");
//                invoiceItem.put("units", "-");
//                invoiceItem.put("plan", "");
//                invoiceItemList.add(invoiceItem)
//            } else if(item.referenceItemName == "StoppedInstance") {
//                HashMap invoiceItem = new HashMap();
//                def vm = VirtualMachine.findByReferenceId(item.referenceItemId);
//                def computeOffer = ComputingOffer.findByOfferReferenceId(item.referencePlanId);
//                
//                
//                if(vm && computeOffer) {
//                    invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                    invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                    invoiceItem.put("usageAmount", item.usageAmount);
//                    invoiceItem.put("taxPercent", item.taxPercent); 
//                    invoiceItem.put("taxAmount", item.taxAmount); 
//                    invoiceItem.put("totalAmount", item.totalAmount);
//                    invoiceItem.put("referenceItemId", item.referenceItemId);
//                    invoiceItem.put("discountPercent", item.discountPercent);
//                    invoiceItem.put("discountAmount", item.discountAmount);
//                    invoiceItem.put("referencePlanId", item.referencePlanId);
//                    invoiceItem.put("itemName", "Stopped Instance" + " (" + vm.displayName + ")");
//                    invoiceItem.put("units", "/hr");
//                    invoiceItem.put("plan", computeOffer.name);
//                    invoiceItemList.add(invoiceItem)
//                }
//                
//                
//            } else {
//                HashMap invoiceItem = new HashMap();
//                invoiceItem.put("usageUnits", Math.ceil(item.usageUnits * 100d) / 100d);
//                invoiceItem.put("usageUnitPrice", item.usageUnitPrice);
//                invoiceItem.put("usageAmount", item.usageAmount);
//                invoiceItem.put("taxPercent", item.taxPercent); 
//                invoiceItem.put("taxAmount", item.taxAmount); 
//                invoiceItem.put("totalAmount", item.totalAmount);
//                invoiceItem.put("referenceItemId", item.referenceItemId);
//                invoiceItem.put("discountPercent", item.discountPercent);
//                invoiceItem.put("discountAmount", item.discountAmount);
//                invoiceItem.put("referencePlanId", item.referencePlanId);
//                invoiceItem.put("itemName", item.referenceItemName);
//                invoiceItem.put("units", "-");
//                invoiceItem.put("plan", "");
//                invoiceItemList.add(invoiceItem)
//            }
//            
//        }
//        
//        def paymentHistory = getPayments(invoice);
////        def snapshot = getSnapshot(invoice)
////        invoiceItemList.add(snapshot)
//        
//        def dueDate;
//        if(invoice.dueDate == null || invoice.dueDate == "null") {
//            dueDate = "-"
//        } else {
//            dueDate = dateFormat.format(invoice.dueDate).toString()
//        }
//        if(invoice.status.name() == "DRAFT") {
//            invoiceService.updateDraftInvoice(invoice, invoice.account)
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
//            invoiceItemList: invoiceItemList, invoice: invoice, 
//            invoiceDate: dateFormat.format(invoice.date).toString(), 
//            dueDate: dueDate, payments: paymentHistory, 
//            orgAddressDetails: orgAddressDetails, orgName: Config.findByName(Config.ORGANISATION_NAME).value, lateFee: Config.findByName(Config.LATE_FEE_MINIMUM_AMOUNT).value]
//    }
    
    def getSnapshot(invoice) {
        def invoiceItemCriteria = InvoiceItem.createCriteria()
        def snapshotCriteria = Snapshot.createCriteria()
        def snapBillableItem = BillableItem.findByReferenceItemName("snapShot")
        ArrayList<ArrayList<String>> snapshotList = new ArrayList<ArrayList<String>>(); 
        HashMap invoiceItem = new HashMap();  
        def snapshotTotalUsageUnit = invoiceItemCriteria.get {
            eq("invoice", invoice)
            and{
               eq("billableItem", snapBillableItem)  
            }
            projections {
                avg("usageUnits")
                avg("usageUnitPrice")
                sum("usageAmount")
                avg("taxPercent")
                sum("taxAmount")
                sum("totalAmount")
                avg("discountPercent")
                sum("discountAmount")
             }
        }
        if(snapshotTotalUsageUnit[0] == null || snapshotTotalUsageUnit[0] == "null") {
            
        } else {
            invoiceItem.put("usageUnits", snapshotTotalUsageUnit[0]);
            invoiceItem.put("usageUnitPrice", snapshotTotalUsageUnit[1]);
            invoiceItem.put("usageAmount", snapshotTotalUsageUnit[2]);
            invoiceItem.put("taxPercent", snapshotTotalUsageUnit[3]); 
            invoiceItem.put("taxAmount", snapshotTotalUsageUnit[4]); 
            invoiceItem.put("totalAmount", snapshotTotalUsageUnit[5]);
            invoiceItem.put("referenceItemId", "snapshot");
            invoiceItem.put("discountPercent", snapshotTotalUsageUnit[6]);
            invoiceItem.put("discountAmount", snapshotTotalUsageUnit[7]);
            invoiceItem.put("referencePlanId", "snapshot");
            invoiceItem.put("itemName", "snapshot(all)");
            invoiceItem.put("units", "/GB/hr");
            invoiceItem.put("plan", "all snapshot");
        }
        return invoiceItem;
    }
    
    def getPayments(invoice) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        def paymentsCriteria = Payments.createCriteria()
        
        Calendar toDateCalendar = Calendar.getInstance(); 
        toDateCalendar.setTime(invoice.date);
        toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        toDateCalendar.set(Calendar.MINUTE, 59);
        toDateCalendar.set(Calendar.SECOND, 59);
        toDateCalendar.set(Calendar.MILLISECOND, 999);                   
        Date toDate = toDateCalendar.getTime() 

        Calendar fromDateCalendar = Calendar.getInstance(); 
        fromDateCalendar.setTime(invoice.previousInvoiceDate);
        fromDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        fromDateCalendar.set(Calendar.MINUTE, 00);
        fromDateCalendar.set(Calendar.SECOND, 00);
        fromDateCalendar.set(Calendar.MILLISECOND, 001);                   
        Date fromDate = fromDateCalendar.getTime() 

        def payments = paymentsCriteria.list {
            eq("account", invoice.account)
            and{
                ge("paymentDate", fromDate) and { le("paymentDate", toDate) }            
            }
        }
        
        ArrayList<ArrayList<String>> paymentList = new ArrayList<ArrayList<String>>();  
        for(int i=0; i < payments.size(); i++) { 
            def item = payments[i]; 
            HashMap payment = new HashMap();                
            payment.put("id", item.id);
            payment.put("date", dateFormat.format(item.paymentDate).toString());
            payment.put("amount", item.amount);
            payment.put("tokenNo", item.paymentToken); 
            payment.put("totalAmount", item.totalAmount); 
            payment.put("processingFee", item.processingFee); 
            paymentList.add(payment)
        }
        return paymentList
    }
    
    
    
    
    def creditLimitReport(params) {
        
        def language = "";
        def user = springSecurityService.currentUser 
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency 
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        def pattern = "###,##0.00"
        def currencyFormat = new DecimalFormat(pattern);
        Date fromDate;  //
        Date toDate;     
        
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        
        def dateFormatValue = configService.getDateFormat();        
        if(params.type == "pdf") {
            dateFormatValue = configService.getInvoiceDateFormat();
        } else {
            dateFormatValue = configService.getDateFormat();
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        Calendar tDateCalendar = Calendar.getInstance();               

        def fromDateString = ""
        def toDateString = ""

        if(params.fromDate == "undefined" || params.fromDate == " " || params.fromDate == "") {
            fromDateString = ""
            
        } else {
            fromDate = givenDateFormater.parse(params.fromDate);
            fromDateString = dateFormat.format(fromDate).toString();                
        }

        if(params.toDate == "undefined" || params.toDate == " " || params.toDate == "") {
            toDateString = ""            
        } else {            
            toDate = tDateCalendar.getTime()
            toDateString = dateFormat.format(toDate).toString();                
        }
        
        if(params.forDateRange == "SELECTIVE") {
            fromDate = givenDateFormater.parse(params.fromDate);
            Date givenToDate = givenDateFormater.parse(params.toDate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            toDate = toDateCalendar.getTime()
        }
        
        def accountList = Account.withCriteria {
             params.each { k, v ->
                  if(k == "forDateRange") {
                    if(v != "ALL") {
                       ge("nextBillingData", fromDate) and { le("nextBillingData", toDate) }    
                     }
                 }
            }
        }
        
        
        if(params.format == "csv") {
            
            def varContainingData = '"'+messageSource.getMessage('common.accountId', null, new Locale(language))+'", "'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.exceededAmount', null, new Locale(language))+'" \n';
            
            for(def account: accountList) {
                
                if(account.totalPayable > account.creditLimit) {
                    varContainingData += '"' + account.id + '", '
                    varContainingData += '"' + account.firstName + '", '
                    varContainingData += '"' + currencyFormat.format((account.totalPayable - account.creditLimit))  + '", '
                    varContainingData += "\n"
                }
                
            }
            response.setHeader("Content-disposition", "attachment; filename=credit-limit-report.csv")  
            render(contentType: "text/csv", text:varContainingData, fDate: fromDateString, tDate: toDateString) 
        } else {
            
            
            ArrayList<ArrayList<String>> accountListArray = new ArrayList<ArrayList<String>>(); 
            
            for(def account: accountList) {
                
                if(account.totalPayable > account.creditLimit) {
                   HashMap item = new HashMap();
                   item.put("accountId", account.id);
                   item.put("accountName", account.firstName);
                   item.put("exAmount", account.totalPayable - account.creditLimit);
                   accountListArray.add(item) 
                }
            }
            [accountList: accountListArray, params: params, currency:currency, fDate: fromDateString, tDate: toDateString] 
            
        }
    }
    
    
    def paymentPendingReport(params) { 
        
        Date fromDate; 
        Date toDate;     
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency 
        def pattern = "###,##0.00"
        def currencyFormat = new DecimalFormat(pattern);
        def language = "";
        def user = springSecurityService.currentUser 
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        
        
        if(params.forDateRange == "SELECTIVE") {
            fromDate = givenDateFormater.parse(params.fromDate);
            Date givenToDate = givenDateFormater.parse(params.toDate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            toDate = toDateCalendar.getTime()
        }
        
        
        def invoiceList = Invoice.withCriteria {
             params.each { k, v ->
                eq("status", InvoiceStatus.values()[6])
                if(k == "forDateRange") {
                    if(v != "ALL") {
                       ge("date", fromDate) and { le("date", toDate) }    
                    }
                }
            }
        }
        
        def dateFormatValue = configService.getDateFormat();
        
        if(params.type == "pdf") {
            dateFormatValue = configService.getInvoiceDateFormat();
        } else {
            dateFormatValue = configService.getDateFormat();
        }
        
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        Calendar tDateCalendar = Calendar.getInstance();               
        
        def fromDateString = ""
        def toDateString = ""

        if(params.fromDate == "undefined" || params.fromDate == " " || params.fromDate == "") {
            fromDateString = ""
            
        } else {
            fromDate = givenDateFormater.parse(params.fromDate);
            fromDateString = dateFormat.format(fromDate).toString();                
        }

        if(params.toDate == "undefined" || params.toDate == " " || params.toDate == "") {
            toDateString = ""            
        } else {            
            toDate = tDateCalendar.getTime()
            toDateString = dateFormat.format(toDate).toString();                
        }
        
        if(params.format == "csv") {
            
            def varContainingData = '"'+messageSource.getMessage('common.accountId', null, new Locale(language))+'", "'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.pendingAmount', null, new Locale(language))+'" \n';
            
            for(def invoice: invoiceList) {
                
                if((invoice.previousBalance - invoice.payment) > 0) {

                    varContainingData += '"' + invoice.account.id + '", '
                    varContainingData += '"' + invoice.account.firstName + '", '
                    varContainingData += '"' + currencyFormat.format((invoice.previousBalance - invoice.payment)) + '", '
                    varContainingData += "\n"
                }
            }
            response.setHeader("Content-disposition", "attachment; filename=payment-pending-report.csv")  
            render(contentType: "text/csv", text:varContainingData) 
        } else {
                        
            ArrayList<ArrayList<String>> paymentListArray = new ArrayList<ArrayList<String>>(); 
            
            for(def invoice: invoiceList) {
                
                if((invoice.previousBalance - invoice.payment) > 0) {
                  
                    HashMap item = new HashMap();  
                    item.put("accountId",  invoice.account.id);
                    item.put("accountName",  invoice.account.firstName);
                    item.put("amount", invoice.previousBalance - invoice.payment);            
                    paymentListArray.add(item)
                    
                    
                }
            }
            [paymentList: paymentListArray, params: params, currency:currency, fDate: fromDateString, tDate: toDateString] 
            
        }
        
        
    }
    
    def paymentReport() {
        def dateFormatValue = configService.getDateFormat(); 
        SimpleDateFormat formater = new SimpleDateFormat(dateFormatValue);
        SimpleDateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency 
        def user = springSecurityService.currentUser 
        def language = "";
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        ArrayList<ArrayList<String>> paymentItemArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList();       
        if(params.type == "pdf") {
            dateFormatValue = configService.getInvoiceDateFormat();
        } else {
            dateFormatValue = configService.getDateFormat();
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);                        
        Calendar tDateCalendar = Calendar.getInstance();               

        def fromDateString = ""
        def toDateString = ""

        if(params.fromDate == "undefined" || params.fromDate == " " || params.fromDate == "") {
            fromDateString = ""
            
        } else {
            Date fromDate = givenDateFormater.parse(params.fromDate);
            fromDateString = dateFormat.format(fromDate).toString();                
        }

        if(params.toDate == "undefined" || params.toDate == " " || params.toDate == "") {
            toDateString = ""            
        } else {
            
            Date toDate = tDateCalendar.getTime()
            toDateString = dateFormat.format(toDate).toString();                
        }
        
        if(params.format == "csv") {
            paymentReportInCsv(params)
        } else {
            if(params.forDateRange == "ALL" && params.forAccount == "ALL") {
                
                tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
                tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language))  + "("+currency+")")
                def accountList = Account.findAll()
                for (def account: accountList) {
                    def paymentCriteria = Payments.createCriteria()
                    def paymentAmount = paymentCriteria.list {
                        eq("account", account)
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(paymentAmount[0] == null || paymentAmount[0] == "null") {
                    } else {
                        HashMap paymentItem = new HashMap();
                        paymentItem.put("account", account.accountIdentifier);
                        paymentItem.put("totalAmount", paymentAmount[0]);
                        paymentItemArray.add(paymentItem)
                    }
                }
                render(view: "paymentAllReport", model: [fDate: fromDateString, tDate: toDateString,paymentItemArray: paymentItemArray, tableThArray: tableThArray]) 
            } else if (params.forDateRange != "ALL" && params.forAccount == "ALL") {
                
                if(params.fromDate != "" || params.toDate != "") {
                    Date fromDate = givenDateFormater.parse(params.fromDate);
                    Date givenToDate = givenDateFormater.parse(params.toDate);
                    Calendar toDateCalendar = Calendar.getInstance(); 
                    toDateCalendar.setTime(givenToDate);
                    toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                    toDateCalendar.set(Calendar.MINUTE, 59);
                    toDateCalendar.set(Calendar.SECOND, 59);
                    toDateCalendar.set(Calendar.MILLISECOND, 999);                   
                    Date toDate = toDateCalendar.getTime() 
                    tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
                    tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)) + "("+currency+")")
                    def accountList = Account.findAll()
                    for (def account: accountList) {
                        def paymentCriteria = Payments.createCriteria()
                        def paymentAmount = paymentCriteria.list {
                            eq("account", account)
                            and {
                               ge("paymentDate", fromDate) and { le("paymentDate", toDate) }  
                            }
                            projections {
                                 sum("totalAmount")
                            }
                        }
                        if(paymentAmount[0] == null || paymentAmount[0] == "null") {
                        } else {
                            HashMap paymentItem = new HashMap();
                            paymentItem.put("account", account.accountIdentifier);
                            paymentItem.put("totalAmount", paymentAmount[0]);
                            paymentItemArray.add(paymentItem)
                        }
                    }                    
                    render(view: "paymentAllReport", model: [fDate: fromDateString, tDate: toDateString,paymentItemArray: paymentItemArray, tableThArray: tableThArray]) 
                }
            } else if (params.forDateRange != "ALL" && params.forAccount != "ALL") {
                
                if(params.fromDate != "" || params.toDate != "") {
                    Date fromDate = givenDateFormater.parse(params.fromDate);
                    Date givenToDate = givenDateFormater.parse(params.toDate);
                    Calendar toDateCalendar = Calendar.getInstance(); 
                    toDateCalendar.setTime(givenToDate);
                    toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                    toDateCalendar.set(Calendar.MINUTE, 59);
                    toDateCalendar.set(Calendar.SECOND, 59);
                    toDateCalendar.set(Calendar.MILLISECOND, 999);                   
                    Date toDate = toDateCalendar.getTime() 
                    tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
                    tableThArray.add(messageSource.getMessage('common.date', null, new Locale(language)))
                    tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)) + "("+currency+")")
                    def accountList = Account.get(params.forAccount)
                    for (def account: accountList) {
                        def paymentCriteria = Payments.createCriteria()
                        def paymentAmount = paymentCriteria.list {
                            eq("account", account)
                            and {
                               ge("paymentDate", fromDate) and { le("paymentDate", toDate) }  
                            }
                        }
                        for(def payment: paymentAmount) {
                            Date inputdate = formater2.parse(payment.paymentDate.toString());
                            String outputDate = formater.format(inputdate);
                            HashMap paymentItem = new HashMap();
                            paymentItem.put("account", account.accountIdentifier);
                            paymentItem.put("date", outputDate);
                            paymentItem.put("totalAmount", payment.totalAmount);
                            paymentItemArray.add(paymentItem)
                        }
                    }
                    render(view: "paymentReport", model: [fDate: fromDateString, tDate: toDateString,paymentItemArray: paymentItemArray, tableThArray: tableThArray, account:Account.get(params.forAccount)]) 
                }
            } else if (params.forDateRange == "ALL" && params.forAccount != "ALL") {
                
                tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
                tableThArray.add(messageSource.getMessage('common.date', null, new Locale(language)))
                tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)))
                def accountList = Account.get(params.forAccount)
                for (def account: accountList) {
                    def paymentCriteria = Payments.createCriteria()
                    def paymentAmount = paymentCriteria.list {
                        eq("account", account)
                    }
                    for(def payment: paymentAmount) {
                        Date inputdate = formater2.parse(payment.paymentDate.toString());
                        String outputDate = formater.format(inputdate);
                        HashMap paymentItem = new HashMap();
                        paymentItem.put("account", account.accountIdentifier);
                        paymentItem.put("date", outputDate);
                        paymentItem.put("totalAmount", payment.totalAmount);
                        paymentItemArray.add(paymentItem)
                    }
                }                               
                                
                render(view: "paymentReport", model: [fDate: fromDateString, tDate: toDateString, currency:currency,paymentItemArray: paymentItemArray, tableThArray: tableThArray, account:Account.get(params.forAccount)])
            }
        }
    }
    
    def paymentReportInCsv(params) {
        def user = springSecurityService.currentUser 
        def language = "";
        def pattern = "###,##0.00"
        def currencyFormat = new DecimalFormat(pattern)

        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy"); 
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        def varContainingData;
        if(params.forDateRange == "ALL" && params.forAccount == "ALL") {            
            varContainingData = '"'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'" \n';
            def accountList = Account.findAll()
            for (def account: accountList) {
                def paymentCriteria = Payments.createCriteria()
                def paymentAmount = paymentCriteria.list {
                    eq("account", account)
                    projections {
                         sum("totalAmount")
                    }
                }
                if(paymentAmount[0] == null || paymentAmount[0] == "null") {
                } else {
                    varContainingData += '"' + account.accountIdentifier + '", '
                    varContainingData += '"' + currencyFormat.format(paymentAmount[0])  + '"'
                    varContainingData += "\n"
                }
            }
        } else if(params.forDateRange != "ALL" && params.forAccount == "ALL") {
            varContainingData = '"'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'" \n';
            if(params.fromDate != "" || params.toDate != "") {
                Date fromDate = givenDateFormater.parse(params.fromDate);
                Date givenToDate = givenDateFormater.parse(params.toDate);
                Calendar toDateCalendar = Calendar.getInstance(); 
                toDateCalendar.setTime(givenToDate);
                toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                toDateCalendar.set(Calendar.MINUTE, 59);
                toDateCalendar.set(Calendar.SECOND, 59);
                toDateCalendar.set(Calendar.MILLISECOND, 999);                   
                Date toDate = toDateCalendar.getTime() 
                

                def accountList = Account.findAll()
                for (def account: accountList) {
                    def paymentCriteria = Payments.createCriteria()
                    def paymentAmount = paymentCriteria.list {
                        eq("account", account)
                        and {
                            ge("paymentDate", fromDate) and { le("paymentDate", toDate) }  
                            }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(paymentAmount[0] == null || paymentAmount[0] == "null") {
                    } else {
                        varContainingData += '"' + account.accountIdentifier + '", '
                        varContainingData += '"' + currencyFormat.format(paymentAmount[0])  + '"'
                        varContainingData += "\n"
                    }
                }
            }
        } else if(params.forDateRange != "ALL" && params.forAccount != "ALL") {
            varContainingData = '"'+messageSource.getMessage('common.account', null, new Locale(language))+'", ""'+messageSource.getMessage('common.date', null, new Locale(language))+'", ""'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'" \n';;
            if(params.fromDate != "" || params.toDate != "") {
                Date fromDate = givenDateFormater.parse(params.fromDate);
                Date givenToDate = givenDateFormater.parse(params.toDate);
                Calendar toDateCalendar = Calendar.getInstance(); 
                toDateCalendar.setTime(givenToDate);
                toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
                toDateCalendar.set(Calendar.MINUTE, 59);
                toDateCalendar.set(Calendar.SECOND, 59);
                toDateCalendar.set(Calendar.MILLISECOND, 999);                   
                Date toDate = toDateCalendar.getTime() 

                def accountList = Account.get(params.forAccount)
                for (def account: accountList) {
                    def paymentCriteria = Payments.createCriteria()
                    def paymentAmount = paymentCriteria.list {
                        eq("account", account)
                        and {
                            ge("paymentDate", fromDate) and { le("paymentDate", toDate) }  
                            }
                    }
                    for(def payment: paymentAmount) {
                        Date inputdate = formater2.parse(payment.paymentDate.toString());
                        String outputDate = formater.format(inputdate);
                        varContainingData += '"' + account.accountIdentifier + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + currencyFormat.format(payment.totalAmount) + '"'
                        varContainingData += "\n"
                    }
                }
            }
        } else if(params.forDateRange == "ALL" && params.forAccount != "ALL") {
            varContainingData = '"'+messageSource.getMessage('common.account', null, new Locale(language))+'", ""'+messageSource.getMessage('common.date', null, new Locale(language))+'", ""'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+'" \n';
            def accountList = Account.get(params.forAccount)
            for (def account: accountList) {
                def paymentCriteria = Payments.createCriteria()
                def paymentAmount = paymentCriteria.list {
                    eq("account", account)
                }
                for(def payment: paymentAmount) {
                    Date inputdate = formater2.parse(payment.paymentDate.toString());
                    String outputDate = formater.format(inputdate);
                    varContainingData += '"' + account.accountIdentifier + '", '
                    varContainingData += '"' + outputDate + '", '
                    varContainingData += '"' + currencyFormat.format(payment.totalAmount) + '"'
                    varContainingData += "\n"
                }
            }
        }
           
        response.setHeader("Content-disposition", "attachment; filename=payment-report.csv")  
        render(contentType: "text/csv", text:varContainingData)  
    }
    
    
    def accountReport() {
         DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        def accountList;
        def accountCriteria = Account.createCriteria()
    
        if(params.forDateRange == "ALL") {

            if(params.forAccount == "ALL") {
                 accountList = Account.findAllWhere(accountType: AccountType.values()[Integer.parseInt(params.accountType)])
//                def accountListArray = [];
//
//                def accounts = params.accountList.split(",");
//                for(def i=0; i < accounts.length; i++) {
//                    accountListArray[i] = Account.get(Integer.parseInt(accounts[i]))
//                }
//
//                accountList = accountCriteria.list {
//                    'in'("account", accountListArray)
//                }
            } 

        } else {
           
            Date fromDate = formater.parse(params.fromDate);
            Date givenToDate = formater.parse(params.toDate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            Date toDate = toDateCalendar.getTime()

            accountList = accountCriteria.list {
                eq("accountType", AccountType.values()[Integer.parseInt(params.accountType)])
                and{
                  ge("signUpDate", fromDate) and { le("signUpDate", toDate) }  
                }
            }
        }        
        def dateFormatValue = configService.getInvoiceDateFormat();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        Calendar toDateCalendar = Calendar.getInstance();               

        def fromDateString = ""
        def toDateString = ""

        if(params.fromDate == "undefined" || params.fromDate == " ") {
            fromDateString = ""
        } else {
            Date fromDate = formater.parse(params.fromDate);
            fromDateString = dateFormat.format(fromDate).toString();                
        }

        if(params.toDate == "undefined" || params.toDate == " ") {
            toDateString = ""
        } else {
            Date toDate = toDateCalendar.getTime()
            toDateString = dateFormat.format(toDate).toString();                
        }
        ArrayList accountArrayList = new ArrayList();             
        for(def account: accountList) {                 
            HashMap accountItem = new HashMap();                          
            accountItem.put("id", account.id)
            accountItem.put("signUpDate", dateFormat.format(account.signUpDate).toString())
            accountItem.put("email", account.email)
            accountItem.put("status", account.status.name())
            accountItem.put("cardVerified", account.cardVerified)
            accountItem.put("accountType", account.accountType.name())
            accountItem.put("totalAmount", account.totalAmount)
            accountItem.put("totalPayable", account.totalPayable)
            accountItem.put("creditLimit", account.creditLimit)    
            accountArrayList.add(accountItem)
        }
                
        [accountList: accountArrayList, fdate: fromDateString, tdate: toDateString]
    
    }
    
    def graphReport(params) {
        Date fromDate;
        Date toDate;
        def invoiceItemList;
        def count = 0;
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
        
        
        def invoiceList = Invoice.withCriteria {
             params.each { k, v ->
                 if(k == "forAccount") {
                     if(v != "ALL") {
                       eq("account", Account.get(v));  
                     }
                     
                 } else if(k == "forDateRange") {
                    if(v != "ALL") {
                       ge("date", fromDate) and { le("date", toDate) }    
                     }
                 }
            }
        }
        
        for(def invoice: invoiceList) {
            Date inputdate = formater2.parse(invoice.date.toString());
            String outputDate = outPutFormater.format(inputdate);
             invoiceItemList = InvoiceItem.withCriteria {
                 eq("invoice", invoice);
                 and {
                    params.each { k, v ->
                        if(k == "forZone") {
                            if(v == "NONZONE") {
                            } else if(v == "ALL") {
                              def zoneList = Zone.findAll()
                              for(def zone: zoneList) {
                                  or {
                                     eq("zone", zone);  
                                  }
                              }
                            } else {
                              eq("zone", zone);   
                            }
                        } else if(k == "forZone") {
                            
                        }
                    } 
                 }
                
                
                
             }
            
            
            
            
            
            if(params.forZone == "ALL"){
                def zoneList = Zone.findAll()
                billableItemList = BillableItem.findAllWhere(hasZone: false)
                for(def zone: zoneList) {
                    for(def billableItem : billableItemList) {
                        invoiceItemList = InvoiceItem.withCriteria {
                            eq("invoice", invoice);
                            and {
                                eq("billableItem", billableItem) 
                                params.each { k, v ->
                                    if(k == "forZone") {
                                        if(v == "NONZONE") {
                                        } else if(v == "ALL") {
                                          eq("zone", zone); 
                                        }
                                    } 
        //                                if(params.) {
        //                                    
        //                                }
                                }
                            }
                            projections {
                                sum("totalAmount")
                            }
                        }
                        
                    }
                }
            }
            
        }
        
        
        
        
        
    }
    
    def billableItemReportNew(params) {
        Date fromDate;  
        Date toDate;
        def invoiceItemList;
        def count = 0;
        def language = "";
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency 
        def user = springSecurityService.currentUser 
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        def dateFormatValue = configService.getDateFormat();;
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat formater = new SimpleDateFormat(dateFormatValue);        
        if(params.type == "pdf") {
            dateFormatValue = configService.getInvoiceDateFormat();
        } else {
            dateFormatValue = configService.getDateFormat();
        }
        Console.print("test 1")
        DateFormat outPutFormater = new SimpleDateFormat(dateFormatValue);
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add(messageSource.getMessage('common.indexNo', null, new Locale(language)))
        tableThArray.add(messageSource.getMessage('common.account', null, new Locale(language)))
        tableThArray.add(messageSource.getMessage('menu.admin.report.billableItem', null, new Locale(language)))
        tableThArray.add(messageSource.getMessage('common.itemName', null, new Locale(language)))
        tableThArray.add(messageSource.getMessage('common.itemReferenceId', null, new Locale(language)))
        tableThArray.add(messageSource.getMessage('common.date', null, new Locale(language)))
        tableThArray.add(messageSource.getMessage('common.totalAmount', null, new Locale(language)) + "("+currency+")") 
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>(); 
        if(params.forDateRange == "SELECTIVE") {
            fromDate = givenDateFormater.parse(params.fromDate);
            Date givenToDate = givenDateFormater.parse(params.toDate);
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
            params.each { k, v ->
                if(k == "forAccount") {
                    if(v != "ALL") {
                        eq("account", Account.get(v));  
                    }                     
                } else if(k == "forDateRange") {
                    if(v != "ALL") {
                        ge("date", fromDate) and { le("date", toDate) }    
                    }
                }
            }
        }
                
        for(def invoice: invoiceList) {
            Date inputdate = formater2.parse(invoice.date.toString());
            String outputDate = outPutFormater.format(inputdate);
            if(params.forBillableItem == "ALL") {
                def billableItemList;
                if(params.forZone == "NONZONE") {
                    billableItemList = BillableItem.findAllWhere(hasZone: false)
                } else {
                    billableItemList = BillableItem.findAllWhere(hasZone: true)
                }
                for(def billableItem : billableItemList) {
                    invoiceItemList = InvoiceItem.withCriteria {
                        eq("invoice", invoice);
                        and {
                            eq("billableItem", billableItem) 
                            params.each { k, v ->
                                if(k == "forZone") {
                                    if(v == "NONZONE" || v == "ALL") {
                                    } else {
                                      eq("zone", Zone.get(params.forZone)); 
                                    }
                                } 
//                                if(params.) {
//                                    
//                                }
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
                            item.put("totalAmount", invoiceItem.totalAmount);
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
                            } else if(invoiceItem.referenceItemName == "VM Snapshot") {
                                def vmSnapshot = VMSnapshot.findByReferenceId(invoiceItem.referenceItemId);
                                 if(vmSnapshot) {
                                    item.put("itemName", vmSnapshot.name);
                                    invoiceItemListArray.add(item)
                                 }
                            } else {
                                item.put("itemName", invoiceItem.referenceItemName);
                                invoiceItemListArray.add(item)
                            }
                             
                        }
                    }
                }
            } else {
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    invoiceItemList = InvoiceItem.withCriteria {
                        eq("invoice", invoice);
                        and {
                            eq("billableItem", billableItem) 
                            params.each { k, v ->
                                if(k == "forZone") {
                                   if(v == "NONZONE" || v == "ALL") {
                                    } else {
                                      eq("zone", Zone.get(params.forZone)); 
                                    }
                                } 
                                else if(params.plan != "ALL" && billableItems.length == 1) {
                                   eq("referencePlanId", params.plan); 
                                } 
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
                            item.put("totalAmount", invoiceItem.totalAmount);
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
        if(params.format == "csv") {            
            def varContainingData = ''+messageSource.getMessage("common.account", null, new Locale(language))+', "'+messageSource.getMessage("menu.admin.report.billableItem", null, new Locale(language))+'", "'+messageSource.getMessage("common.itemName", null, new Locale(language))+'", "'+messageSource.getMessage("common.itemReferenceId", null, new Locale(language))+'", "'+messageSource.getMessage("common.date", null, new Locale(language))+'", "'+messageSource.getMessage("common.totalAmount", null, new Locale(language))+'" \n';
            for(def item: invoiceItemListArray) {
                varContainingData += '"'+item.account+'",'
                varContainingData += '"'+item.billableItem+'",'
                varContainingData += '"'+item.itemName+'",'
                varContainingData += '"'+item.itemReferenceId+'",'
                varContainingData += '"'+item.date+'",'
                varContainingData += '"'+item.totalAmount+'"'  
                varContainingData += "\n"
            }
            response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
            render(contentType: "text/csv", text:varContainingData) 
        } else {
            def zone;
            if(params.forZone == "NONZONE" || params.forZone == "ALL") { 
                zone = params.forZone; 
            } else {
                zone = Zone.get(params.forZone).aliasName; 
            }
            
            def planName = ComputingOffer.findByOfferReferenceId(params.plan) ? ComputingOffer.findByOfferReferenceId(params.plan).name : DiskOffer.findByDiskOfferReferenceId(params.plan)?.name
            def billableItemName = "";
            if(params.forBillableItem == "ALL") { 
                billableItemName = "ALL"
            } else {
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                        billableItemName += messageSource.getMessage(billableItems[i], null, new Locale(language))+","
                }
            }                       
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
            Calendar toDateCalendar = Calendar.getInstance();               
            
            def fromDateString = ""
            def toDateString = ""
            
            if(params.fromDate == "undefined" || params.fromDate == "" || params.fromDate == " ") {
                fromDateString = ""
            } else {
                Date newFromDate = givenDateFormater.parse(params.fromDate);
                fromDateString = dateFormat.format(newFromDate).toString();                
            }
            
            if(params.toDate == "undefined" || params.toDate == "" || params.toDate == " ") {
                toDateString = ""
            } else {
                Date newToDate = toDateCalendar.getTime()
                toDateString = dateFormat.format(newToDate).toString();                
            }
            
            if(params.forAccount == "ALL") { 
                [fDate: fromDateString, tDate: toDateString, currency:currency, invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray, params: params, zone: zone, plan:planName, billableItemName:billableItemName]  
            } else {
                [fDate: fromDateString, tDate: toDateString, currency:currency,invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray, params: params, zone: zone, plan: planName, account:Account.get(params.forAccount), billableItemName:billableItemName] 
            }
        }
    }
    
    def billableItemReport() {
        if (params.format == "csv") {
            if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
                billableItemReportInCsvForAll(params) 
            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
                billableItemReportInCsvForAccount(params) 
            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
                billableItemReportInCsvForZone(params) 
            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
                billableItemReportInCsvForBillableItem(params) 
            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInCsvForDateRange(params) 
               }
            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInCsvForDateRangeAndAccount(params) 
               }
            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInCsvForDateRangeAndBillableItem(params) 
               }
            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInCsvForDateRangeWithBillableItemAndAccount(params) 
               }
            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInCsvBillableItemAndAccount(params) 
               }
            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
               billableItemReportInCsvZoneAndAccount(params) 
            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
               billableItemReportInCsvZoneAndBillableItem(params) 
            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                billableItemReportInCsvDateRangeWithZoneAndBillableItem(params) 
                }
            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
                if(params.fromDate != "" || params.toDate != "") {
                    billableItemReportInCsvDateRangeWithZoneAndBillableItemAndAccount(params) 
                }
            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
                if(params.fromDate != "" || params.toDate != "") {
                    billableItemReportInCsvDateRangeWithZoneAndAccount(params) 
                }
            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
                if(params.fromDate != "" || params.toDate != "") {
                    billableItemReportInCsvDateRangeAndZone(params) 
                }
            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
               billableItemReportInCsvZoneAndBillableItemAndAccount(params) 
            }
        } else {
            if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
                billableItemReportInHtmlForAll(params) 
            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
                billableItemReportInHtmlForAccount(params) 
            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
                billableItemReportInHtmlForZone(params) 
            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
               billableItemReportInHtmlForBillableItem(params) 
            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInHtmlForDateRange(params) 
               }
            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem == "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInHtmlForDateRangeAndAccount(params) 
               }
            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInHtmlForDateRangeAndBillableItem(params) 
               }
            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInHtmlForDateRangeWithBillableItemAndAccount(params) 
               }
            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone == "ALL" && params.forBillableItem != "ALL") {
               if(params.fromDate != "" || params.toDate != "") {
                   billableItemReportInHtmlBillableItemAndAccount(params) 
               }
            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
               billableItemReportInHtmlZoneAndAccount(params) 
            } else if(params.forDateRange == "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
               billableItemReportInHtmlZoneAndBillableItem(params) 
            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
                if(params.fromDate != "" || params.toDate != "") {
                    billableItemReportInHtmlDateRangeWithZoneAndBillableItem(params) 
                }
            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
                if(params.fromDate != "" || params.toDate != "") {
                    billableItemReportInHtmlDateRangeWithZoneAndBillableItemAndAccount(params) 
                }
            } else if(params.forDateRange != "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
                if(params.fromDate != "" || params.toDate != "") {
                    billableItemReportInHtmlDateRangeWithZoneAndAccount(params) 
                }
            } else if(params.forDateRange != "ALL" && params.forAccount == "ALL" && params.forZone != "ALL" && params.forBillableItem == "ALL") {
                if(params.fromDate != "" || params.toDate != "") {
                    billableItemReportInHtmlDateRangeAndZone(params) 
                }
            } else if(params.forDateRange == "ALL" && params.forAccount != "ALL" && params.forZone != "ALL" && params.forBillableItem != "ALL") {
               billableItemReportInHtmlZoneAndBillableItemAndAccount(params) 
            }
           
        }
        
    }
    
    def billableItemReportInHtmlZoneAndBillableItemAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def zone = Zone.get(params.forZone);
                
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)
                            and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    } 
    
    def billableItemReportInCsvZoneAndBillableItemAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def zone = Zone.get(params.forZone);
                
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                          and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    def billableItemReportInHtmlDateRangeAndZone(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def zone = Zone.get(params.forZone);
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.findAll()
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and {
                   ge("date", fromDate) and { le("date", toDate) }    
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = BillableItem.findAll()
                for(def billableItem: billableItems) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)
                            and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    
    def billableItemReportInCsvDateRangeAndZone(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def zone = Zone.get(params.forZone);
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def accountList = Account.findAll()
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and {
                   ge("date", fromDate) and { le("date", toDate) }    
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = BillableItem.findAll()
                for(def billableItem: billableItems) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                          and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    def billableItemReportInHtmlDateRangeWithZoneAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def zone = Zone.get(params.forZone);
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and {
                   ge("date", fromDate) and { le("date", toDate) }    
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = BillableItem.findAll()
                for(def billableItem: billableItems) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)
                            and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    
    def billableItemReportInCsvDateRangeWithZoneAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def zone = Zone.get(params.forZone);
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and {
                   ge("date", fromDate) and { le("date", toDate) }    
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = BillableItem.findAll()
                for(def billableItem: billableItems) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                          and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    def billableItemReportInHtmlDateRangeWithZoneAndBillableItemAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def zone = Zone.get(params.forZone);
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and {
                   ge("date", fromDate) and { le("date", toDate) }    
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)
                            and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    def billableItemReportInCsvDateRangeWithZoneAndBillableItemAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def zone = Zone.get(params.forZone);
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and {
                   ge("date", fromDate) and { le("date", toDate) }    
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                          and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    
    def billableItemReportInHtmlDateRangeWithZoneAndBillableItem(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def zone = Zone.get(params.forZone);
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.findAll()
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and {
                   ge("date", fromDate) and { le("date", toDate) }    
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)
                            and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    } 
    
    
    def billableItemReportInCsvDateRangeWithZoneAndBillableItem(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def zone = Zone.get(params.forZone);
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def accountList = Account.findAll()
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and {
                   ge("date", fromDate) and { le("date", toDate) }    
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                          and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    def billableItemReportInHtmlZoneAndBillableItem(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def zone = Zone.get(params.forZone);
                
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.findAll()
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)
                            and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    } 
    
    def billableItemReportInCsvZoneAndBillableItem(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def zone = Zone.get(params.forZone);
                
        def accountList = Account.findAll()
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                          and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    def billableItemReportInHtmlZoneAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def zone = Zone.get(params.forZone);
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate); 
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem)  
                            and {
                                eq("zone", zone)
                            }
                        } 
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }

                }

            } 

        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    
    def billableItemReportInCsvZoneAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def zone = Zone.get(params.forZone);
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem)  
                          and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    def billableItemReportInHtmlForZone(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def zone = Zone.get(params.forZone);
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.findAll()
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem)  
                            and {
                                eq("zone", zone)
                            }
                        } 
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }

                }

            } 

        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    def billableItemReportInCsvForZone(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def zone = Zone.get(params.forZone);
        def accountList = Account.findAll()
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem)  
                          and {
                                eq("zone", zone)
                            }
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    def billableItemReportInHtmlBillableItemAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    } 
    
    def billableItemReportInCsvBillableItemAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    
    def billableItemReportInHtmlForDateRangeWithBillableItemAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and{
                  ge("date", fromDate) and { le("date", toDate) }   
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    } 
    
    
    def billableItemReportInCsvForDateRangeWithBillableItemAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and{
                  ge("date", fromDate) and { le("date", toDate) }   
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    
    def billableItemReportInHtmlForDateRangeAndBillableItem(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.findAll()
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and{
                  ge("date", fromDate) and { le("date", toDate) }   
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    } 
    
    
    def billableItemReportInCsvForDateRangeAndBillableItem(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def accountList = Account.findAll()
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and{
                  ge("date", fromDate) and { le("date", toDate) }   
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    def billableItemReportInHtmlForDateRangeAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and{
                  ge("date", fromDate) and { le("date", toDate) }   
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    } 
    
    
    def billableItemReportInCsvForDateRangeAndAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and{
                  ge("date", fromDate) and { le("date", toDate) }   
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    
    def billableItemReportInHtmlForDateRange(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.findAll()
        for (def account: accountList) {
            
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and{
                  ge("date", fromDate) and { le("date", toDate) }   
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }
                }
            } 
        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    def billableItemReportInCsvForDateRange(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        
        Date fromDate = givenDateFormater.parse(params.fromDate);
        Date toDate = givenDateFormater.parse(params.toDate);
        
        def accountList = Account.findAll()
        for (def account: accountList) {
            def invoiceCriteria = Invoice.createCriteria()
            def invoiceList = invoiceCriteria.list {
                eq("account", account)
                and{
                  ge("date", fromDate) and { le("date", toDate) }   
                }
            }
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                    def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    
    def billableItemReportInHtmlForBillableItem(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.findAll()
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                            eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }

                }

            } 

        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    def billableItemReportInCsvForBillableItem(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        
        def accountList = Account.findAll()
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItems = params.billableItemList.split(",");
                for(def i=0; i < billableItems.length; i++) {
                    def billableItem = BillableItem.findByName(billableItems[i])
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem) 
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
    
    
    def billableItemReportInHtmlForAll(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.findAll()
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }

                }

            } 

        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    def billableItemReportInHtmlForAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        
        def invoiceItemList;
        ArrayList<ArrayList<String>> invoiceItemListArray = new ArrayList<ArrayList<String>>();  
        ArrayList tableThArray = new ArrayList(); 
        tableThArray.add("Account")
        tableThArray.add("Item")
        tableThArray.add("Date")
        tableThArray.add("Total Amount")
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else {                   
                        HashMap invoiceItem = new HashMap();
                        invoiceItem.put("account", invoice.account.accountIdentifier);
                        invoiceItem.put("item", billableItem.name);
                        invoiceItem.put("date", outputDate);
                        invoiceItem.put("totalAmount", totalAmount[0]);
                        invoiceItemListArray.add(invoiceItem)
                    }

                }

            } 

        }
        [invoiceItemListArray: invoiceItemListArray, tableThArray: tableThArray]
    }
    
    
     def billableItemReportInCsvForAccount(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        
        def accountList = Account.get(params.forAccount)
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + totalAmount[0] + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
     
    def billableItemReportInCsvForAll(params) {
        DateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def varContainingData = '"Account", "Item", "Date", "TotalAmount" \n';
        def invoiceItemList;
        def pattern = "###,##0.00"
        def currencyFormat = new DecimalFormat(pattern);
        def accountList = Account.findAll()
        for (def account: accountList) {
        def invoiceList = Invoice.findAllWhere(account: account);
            for (def invoice: invoiceList) {
                Date inputdate = formater2.parse(invoice.date.toString());
                String outputDate = formater.format(inputdate);
                def billableItemList = BillableItem.findAll() 
                for(def billableItem : billableItemList) {
                    def invoiceItemCriteria = InvoiceItem.createCriteria()
                     def totalAmount = invoiceItemCriteria.list {
                        eq("invoice", invoice)
                        and {
                          eq("billableItem", billableItem)  
                        }
                        projections {
                             sum("totalAmount")
                        }
                    }
                    if(totalAmount[0] == null || totalAmount[0] == "null" || totalAmount[0] == 0) {
                    } else { 
                        varContainingData += '"' + invoice.account.accountIdentifier + '", '
                        varContainingData += '"' + billableItem.name + '", '
                        varContainingData += '"' + outputDate + '", '
                        varContainingData += '"' + currencyFormat.format(totalAmount[0]) + '"'  
                        varContainingData += "\n"
                    }

                }

            } 

        }
        response.setHeader("Content-disposition", "attachment; filename=billableItem-report.csv")  
        render(contentType: "text/csv", text:varContainingData) 
        
     }
     
    def getSSHKey(params) {
        
        def sshKey = SSHKeys.get(Long.parseLong(params.keyId));
        
        response.contentType = "application/octet-stream"
        response.setHeader("Content-disposition", "attachment; filename="+sshKey.name+".pem")  
        response.outputStream << sshKey.privatekey.getBytes() 
    }
    def clientUsageReport(params) { 
        
        Date fromDate; 
        Date toDate;     
        def currency = ApplicationHolder.getApplication().config.billing.defaultCurrency 
        def pattern = "###,##0.00"
        def currencyFormat = new DecimalFormat(pattern);
        def language = "";
        def user = springSecurityService.currentUser 
        if(user.account.preferredLanguage == "" || user.account.preferredLanguage == "null" || user.account.preferredLanguage == null) {
            Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
            language = defaultLanguage.value
        } else {
            language = user.account.preferredLanguage
        }
        
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat outPutFormater = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        
        
        if(params.forDateRange == "SELECTIVE") {
            fromDate = givenDateFormater.parse(params.fromDate);
            Date givenToDate = givenDateFormater.parse(params.toDate);
            Calendar toDateCalendar = Calendar.getInstance(); 
            toDateCalendar.setTime(givenToDate);
            toDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            toDateCalendar.set(Calendar.MINUTE, 59);
            toDateCalendar.set(Calendar.SECOND, 59);
            toDateCalendar.set(Calendar.MILLISECOND, 999);                   
            toDate = toDateCalendar.getTime()
        }
        
        
        
        def dateFormatValue = configService.getDateFormat();
        
        if(params.type == "pdf") {
            dateFormatValue = configService.getInvoiceDateFormat();
        } else {
            dateFormatValue = configService.getDateFormat();
        }        
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        Calendar toDateCalendar = Calendar.getInstance();               

        def fromDateString = ""
        def toDateString = ""
        
        Console.print(params.fromDate)
        
        if(params.fromDate == "undefined" || params.fromDate == " " || params.fromDate == "") {
            fromDateString = ""
        } else {
            
            fromDateString = dateFormat.format(fromDate).toString();                
        }

        if(params.toDate == "undefined" || params.toDate == " " || params.toDate == "") {
            toDateString = ""
        } else {
            
            toDateString = dateFormat.format(toDate).toString();                
        }
        
        
        def accountList = Account.withCriteria {
             params.each { k, v ->
                if(k == "forStatus") {
                    if(v != "ALL") {
                        eq("status", Status.valueOf(v))
                    }
                }
                if(k == "forDateRange") {
                    if(v != "ALL") {
                       ge("signUpDate", fromDate) and { le("signUpDate", toDate) }    
                    }
                }
            }
        }
        
        if(params.format == "csv") {
            
            
            def currency_d = '('+currency+')';
            
            def varContainingData = '"'+messageSource.getMessage('common.accountId', null, new Locale(language))+'", "'+messageSource.getMessage('common.account', null, new Locale(language))+'", "'+messageSource.getMessage('common.companyName', null, new Locale(language))+'", "'+messageSource.getMessage('common.status', null, new Locale(language))+'" , "'+messageSource.getMessage('common.instance', null, new Locale(language))+'" , "'+messageSource.getMessage('common.snapshot.VMSnapshot', null, new Locale(language))+'" , "'+messageSource.getMessage('common.storage', null, new Locale(language))+'" , "'+messageSource.getMessage('common.snapshotMemory', null, new Locale(language))+'" , "'+messageSource.getMessage('common.publicIp', null, new Locale(language))+'" , "'+messageSource.getMessage('common.portForwarding', null, new Locale(language))+'" , "'+messageSource.getMessage('common.loadBalancer', null, new Locale(language))+'" , "'+messageSource.getMessage('common.totalAmount', null, new Locale(language))+''+currency_d+'" , "'+messageSource.getMessage('common.totalPayable', null, new Locale(language))+''+currency_d+'" ,"'+messageSource.getMessage('common.totalpaid', null, new Locale(language))+''+currency_d+'" , "'+messageSource.getMessage('common.avlCredit', null, new Locale(language))+''+currency_d+'" \n';
            
            for(def account: accountList) {
                // TODO have to add the Account relation on Snapshot table
                List<User> currentAccountUser = User.findAllWhere(account:account);
                
                def avCL = account.totalPayable > 0 ? account.creditLimit - account.totalPayable : account.creditLimit - (account.totalPayable) ;
                
                varContainingData += '"' + account.id + '", '
                varContainingData += '"' + account.accountIdentifier + '", '
                varContainingData += '"' + account.companyName + '", '
                varContainingData += '"' + account.status + '", '
                varContainingData += '"' + VirtualMachine.findAllWhere(owner: account, deleted:false).size() + '", '
                varContainingData += '"' + VMSnapshot.findAllWhere(account: account, deleted: false).size()+ '", '
                varContainingData += '"' + Volume.findAllWhere(deleted: false, owner:account).size() + '", '
                varContainingData += '"' + Snapshot.findAllWhere(user: (User)currentAccountUser?.get(0), deleted: false).size() + '", '
                varContainingData += '"' + UserIPAddress.findAllWhere(account: account, removed:false).size() + '", '
                varContainingData += '"' + PortForwarding.findAllWhere(account: account).size() + '", '
                varContainingData += '"' + LoadBalancer.findAllWhere(account: account).size() + '", '
                varContainingData += '"' + currencyFormat.format(account.totalAmount) + '", '
                varContainingData += '"' + currencyFormat.format(account.totalPayable)  + '", '
                varContainingData += '"' + currencyFormat.format(account.totalPaid) + '", '
                varContainingData += '"' + currencyFormat.format(Math.ceil(avCL * 100d) / 100d) + '", '
                
                varContainingData += "\n"
                
            }
            response.setHeader("Content-disposition", "attachment; filename=client-usage-report.csv")  
            render(contentType: "text/csv", text:varContainingData) 
        } else {
                        
            ArrayList<ArrayList<String>> accountListArray = new ArrayList<ArrayList<String>>(); 
            
            for(def account: accountList) {
                HashMap item = new HashMap();  
                item.put("accountId",  account.id);
                item.put("accountName",  account.accountIdentifier);
                item.put("companyName",  account.companyName);
                item.put("status",  account.status);
                item.put("vmUsed", VirtualMachine.findAllWhere(owner: account, deleted:false).size());
                item.put("storageUsed", Volume.findAllWhere(deleted: false, owner:account).size());
                
                // TODO have to add the Account relation on Snapshot table
                List<User> currentAccountUser = User.findAllWhere(account:account);
                item.put("snapshotUsed", Snapshot.findAllWhere(user: (User)currentAccountUser?.get(0), deleted: false).size());
                
                item.put("vmSnapshotUsed", VMSnapshot.findAllWhere(account: account, deleted: false).size());
                item.put("ipUsed", UserIPAddress.findAllWhere(account: account, removed:false).size());
                item.put("portForwaded", PortForwarding.findAllWhere(account: account).size());
                item.put("loadBalanced", LoadBalancer.findAllWhere(account: account).size());
                item.put("totalAmount", account.totalAmount);
                item.put("totalPayable", account.totalPayable);
                item.put("totalPaid", account.totalPaid);
                def avCL = account.totalPayable > 0 ? account.creditLimit - account.totalPayable : account.creditLimit - (account.totalPayable) ;
                item.put("creditLimit", Math.ceil(avCL * 100d) / 100d);
                accountListArray.add(item)
                    
                    
                
            }
            [accountList: accountListArray, params: params, currency:currency,  fdate: fromDateString, tdate: toDateString] 
            
        }
        
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
