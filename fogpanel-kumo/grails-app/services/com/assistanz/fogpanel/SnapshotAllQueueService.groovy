package com.assistanz.fogpanel

import org.springframework.amqp.core.Message;
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.snapshot.CSSnapshotService
import com.assistanz.cloud.cloudstack.snapshot.SnapShotResponse
import com.assistanz.cloud.cloudstack.snapshot.SnapShotPolicyResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.fogpanel.SnapShotService
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional

@Transactional
class SnapshotAllQueueService {
    
    SnapshotManualQueueService snapshotManualQueueService
    private static final log = LogFactory.getLog(this)
    
    def snapShotServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        CSSnapshotService csSnapshotService = new CSSnapshotService(server);
        
        return csSnapshotService;
        
    }
    
    static rabbitQueue = [queues: 'cloudstack-events-snapshot-all']

    void handleMessage(msg) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
            // Do something with the message headers
    //        println "Received message with content type ${msg.contentType};${msg.encoding}"
//            println "-- MESSAGE BEGINS --"
//            println msg
//            println "-- MESSAGE ENDS --"

        def msgData = JSON.parse(msg)    
    
        def  account = Account.findByUuid(msgData.'account')
        
        if(account) {
            list(account)
        }
    }
    
    def list(account) {
        def user = User.findByUsername(account.userName);
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("account", new String(account.userName));
        optional.put("domainid", new String(account.domain.referenceId));
        def response = snapShotServer().listSnapshots(optional)
        ArrayList<ArrayList<String>> snapShotList = new ArrayList<ArrayList<String>>(); 
        HashMap<String,String> cloudStackSnapList = new HashMap<String,String>(); 
        for(Iterator<SnapShotResponse> iter = response.snapShots.iterator(); iter.hasNext();) {
            def data = iter.next();
            cloudStackSnapList.put(data.snapshotId ,"referenceId");
            Snapshot snapshot = Snapshot.findBySnapshotReferenceId(data.snapshotId);
            if (!snapshot) {
                if(data.snapshotType != "MANUAL") {
                    snapshot  = new Snapshot();
                    snapshot.snapshotReferenceId = data.snapshotId
                    snapshot.deleted = false
                    snapshot.user = User.findByUsername(data.snapshotAccount);
                    snapshot.state = data.snapshotState
                    snapshot.snapshotType = data.snapshotType
                    snapshot.created = data.snapshotCreated
                    snapshot.name = data.snapshotName
                    snapshot.volume = Volume.findByVolumeReferenceId(data.diskVolumeId);
                    newSnapshot.zone = Volume.findByVolumeReferenceId(requestData.volumeId)?.zone;
                    snapshot.size = Double.parseDouble(snapshot.volume.customDiskSize) 
                    snapshot.save(flush: true);
                    log.info("Added Auto Snapshot : ${snapshot.id} for account:${account.id}");
                    def invoice = Invoice.findWhere(account: snapshot.user.account, status: InvoiceStatus.values()[6])
                    def invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId: snapshot.snapshotReferenceId)
                    if(!invoiceItem) {
                        def snapShotBillableItem
                        if(snapshot.billingType == "monthly") { 
                            snapShotBillableItem= BillableItem.get(15) 
                        } else {
                            snapShotBillableItem= BillableItem.get(3) 
                        }
                        if(snapShotBillableItem.enabled == true || snapShotBillableItem.enabled == "true") {
                            invoiceItem = new InvoiceItem()
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
                            invoiceItem.invoice = invoice
                            invoiceItem.referenceItemName = "Snapshot"
                            invoiceItem.referenceItemId = snapshot.snapshotReferenceId
                            invoiceItem.save(flush: true); 
                            log.info("InvoiceItem for Snapshot : ${snapshot.id} for account:${account.id}");
                        }
                    }
                    // Auto snapshot mail
                    snapshotManualQueueService.sendSnapManualMail(snapshot);
                }               
                
            } else if(snapshot) {
                snapshot.state = data.snapshotState
                snapshot.snapshotType = data.snapshotType
                snapshot.created = data.snapshotCreated
                snapshot.volume = Volume.findByVolumeReferenceId(data.diskVolumeId);
                snapshot.save(flush: true);   
//                def invoice = Invoice.findWhere(account: snapshot.user.account, status: InvoiceStatus.values()[6])
//                def invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId: snapshot.snapshotReferenceId)
//                if(!invoiceItem) {
//                    def snapShotBillableItem = BillableItem.findByReferenceItemName("snapShot")
//                    if(snapShotBillableItem.enabled == true || snapShotBillableItem.enabled == "true") {
//                        invoiceItem = new InvoiceItem()
//                        invoiceItem.billableItem = snapShotBillableItem
//                        invoiceItem.taxPercent = snapShotBillableItem.tax.percentage
//                        invoiceItem.zone = snapshot.volume.zone
//            //            if(diskBillableItem.discountable == true || diskBillableItem.discountable == "true") {
//            //                //code for discount
//            //            }
//                        invoiceItem.invoice = invoice
//                        invoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(3), zone: snapshot.volume.zone).cost
//                        invoiceItem.referenceItemName = "Snapshot"
//                        invoiceItem.referenceItemId = snapshot.snapshotReferenceId
//                        invoiceItem.save(flush: true); 
//                    }
//                }
            }
        }
        def oldSnapshot = Snapshot.findAllWhere(user: user); 
        for(int snap=0; snap < oldSnapshot.size(); snap++) { 
            def oldSnapItem = oldSnapshot[snap]; 
            boolean blnExists = cloudStackSnapList.containsKey(oldSnapItem.snapshotReferenceId);
            if(blnExists.toString() == "false" || blnExists == false) {
               oldSnapItem.deleted = true;
               oldSnapItem.save(flush: true); 
               log.info("Snapshot : ${oldSnapItem.id} deleted for account:${account.id}");
            }
        }
    }
}
