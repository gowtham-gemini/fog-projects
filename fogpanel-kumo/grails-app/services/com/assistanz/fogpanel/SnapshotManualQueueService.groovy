package com.assistanz.fogpanel

import org.springframework.amqp.core.Message;
import grails.converters.deep.JSON
import java.sql.Timestamp
import com.assistanz.fogpanel.SnapShotService
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.ApplicationHolder

@Transactional
class SnapshotManualQueueService {
    
    def snapShotService = new SnapShotService()
    NotificationService notificationService
    private static final log = LogFactory.getLog(this)
    
    static rabbitQueue = [queues: 'cloudstack-events-snapshot-manual']

    void handleMessage(msg) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
          
        def msgData = JSON.parse(msg)            
        
        Snapshot snapshot = Snapshot.findBySnapshotReferenceId(msgData.id);
        
        if(!snapshot) {
            
        } else {
            
            if(msgData.'new-state' == "BackedUp" && snapshot.state != "BackedUp") {
                snapshot.state = msgData.'new-state'
                snapshot.save(flush: true);
                
                def snapShotBillableItem
                if(snapshot.billingType == "monthly") { 
                    snapShotBillableItem= BillableItem.get(15) 
                } else {
                    snapShotBillableItem= BillableItem.get(3) 
                }
                def invoice = Invoice.findWhere(account: snapshot.user.account, status: InvoiceStatus.values()[6])
                def oldInvoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId: snapshot.snapshotReferenceId)
                if(!oldInvoiceItem) {
                    if(snapShotBillableItem.enabled == true || snapShotBillableItem.enabled == "true") {
                        def invoiceItem = new InvoiceItem()
                        invoiceItem.billableItem = snapShotBillableItem
                        invoiceItem.taxPercent = snapShotBillableItem.tax.percentage
                        invoiceItem.zone = snapshot.volume.zone
                        
                        
                        if(snapshot.billingType == "monthly") {
                                        
                            double volsize = Double.parseDouble(snapshot.volume.customDiskSize) 

                            double monthlyAmount =  MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(3), zone: snapshot.volume.zone).cost * 720.00 * volsize

                            invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                            invoiceItem.usageUnits = 1.0
                            invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                            double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                            invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                            double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)

                            invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                        } else {
                             invoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(3), zone: snapshot.volume.zone).cost
                        }
                        
                        invoiceItem.invoice = Invoice.findWhere(account: snapshot.user.account, status: InvoiceStatus.values()[6])
                        invoiceItem.referenceItemName = "Snapshot"
                        invoiceItem.referenceItemId = snapshot.snapshotReferenceId
                        invoiceItem.save(flush: true); 
                        log.info("InvoiceItem for Snapshot : ${snapshot.id} for account:${snapshot.user.account.id}");
                    }
                }
                // Manual Snapshot mail 
               sendSnapManualMail(snapshot);
            } else {
                snapshot.state = msgData.'new-state'
                snapshot.save(flush: true);
            }
        }
    }
    
    
    def sendSnapManualMail(def snapshot) {
     try {
       
        def account = snapshot.user.account            
        Date date = new Date()
        def time = new Timestamp(date.getTime())                                          
        
        Map tempalteMap = notificationService.getDefaultMailTemplateMap()                
        tempalteMap.put("snapshotName", snapshot.name)
        tempalteMap.put("user", snapshot.user)        
        tempalteMap.put("attachedVolume", snapshot.volume.name)     
        tempalteMap.put("status", snapshot.state)     
        tempalteMap.put("snapshotType", snapshot.snapshotType)     
        tempalteMap.put("createdDate", time.toString())                                     
        notificationService.send(account.email.toString(), "createSnapshot.subject.ftl", tempalteMap, "createSnapshot.ftl")                                        
     } catch(Exception ex) {
         ex.printStackTrace(System.err);
        throw ex;
     }
    }
    
    
    def serviceMethod() {

    }
}
