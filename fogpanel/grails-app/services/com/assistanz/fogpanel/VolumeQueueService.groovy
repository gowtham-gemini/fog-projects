package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.volume.VolumeResponse
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
import com.assistanz.cloud.cloudstack.volume.CSVolumeService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional

@Transactional
class VolumeQueueService {
    
    private static final log = LogFactory.getLog(this)
     
    def volumeServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                 new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        CSVolumeService csVolumeService = new CSVolumeService(server); 
      
        return csVolumeService;
    }
    
    static rabbitQueue = [queues: 'cloudstack-events-volume-all']

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
        def size; 
        def volumeSize;
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("account", new String(account.accountIdentifier));
        optional.put("domainid", new String(account.domain.referenceId));
        def response = volumeServer().listVolumes(optional); 
        HashMap<String,String> cloudStackDiskList = new HashMap<String,String>(); 
        for(Iterator<VolumeResponse> iter = response.volumes.iterator(); iter.hasNext();) {
            def data = iter.next();
             
            cloudStackDiskList.put(data.id ,"referenceId");
            Volume volume = Volume.findByVolumeReferenceId(data.id);
            if (!volume) {
                if(data.type == "ROOT") {
                    volume  = new Volume();
                    volume.volumeReferenceId = data.id;
//                    volume.createdDate = Calendar.getInstance().getTime()
                    volume.name = data.name; 
                    volume.owner = Account.findByAccountIdentifier(data.account);
                    volume.zone = Zone.findByReferenceZoneId(data.zoneId);
                    volume.type = data.type;
                    volume.state = data.state;
                    size = Double.parseDouble(data.size);
                    volumeSize = size / (double) 1073741824;
                    volume.customDiskSize = volumeSize;
                    if(data.diskOfferingId == null) {
                        volume.diskOffer = null;
                    } else {
                        volume.diskOffer = DiskOffer.findByDiskOfferReferenceId(data.diskOfferingId);
                    }
                    if(data.virtualMachineId == null) {
                        volume.virtualMachine = null;
                    } else {
                        volume.virtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId);
                    }                    
                    volume.user = User.findWhere(username: data.account, domain: account.domain);

                    volume.deleted = false;  
                    volume.save(flush: true); 
                }
            } else if(volume) {
               if(data.virtualMachineId == null) {
                    volume.virtualMachine = null;
                } else {
                    volume.virtualMachine = VirtualMachine.findByReferenceId(data.virtualMachineId);
                }  
                size = Double.parseDouble(data.size);  
                volumeSize = size / (double) 1073741824;
                volume.customDiskSize = volumeSize;
                volume.type = data.type;
                volume.state = data.state;
                if(volume.diskOffer == null) {
                    if(data.diskOfferingId == null) {
                        volume.diskOffer = null;
                    } else {
                        volume.diskOffer = DiskOffer.findByDiskOfferReferenceId(data.diskOfferingId);
                    } 
                } 
                if(volume.type == "DATADISK") {

                    if(volume.source == "SNAPSHOT") {
                        def customDisk = DiskOffer.findWhere(cluster: volume.cluster, customized:true)
                        volume.diskOffer = customDisk
                        def costDisk = DiskOfferZoneCost.findWhere(diskOffer: customDisk, zone: volume.zone)
                        if (costDisk) {
                            def diskBillableItem
                            if(volume.billingType == "monthly") { 
                                diskBillableItem = BillableItem.get(14) 
                            } else {
                                diskBillableItem= BillableItem.get(2) 
                            }
                            if(diskBillableItem.enabled == true || diskBillableItem.enabled == "true") {
                                def invoice = Invoice.findWhere(account: volume.owner, status: InvoiceStatus.values()[6])
                                def invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId: volume.volumeReferenceId)
                                if(!invoiceItem) {
                                    invoiceItem = new InvoiceItem()
                                    invoiceItem.billableItem = diskBillableItem
                                    invoiceItem.taxPercent = diskBillableItem.tax.percentage
                                    invoiceItem.zone = volume.zone
                                    
                                    if(volume.billingType == "monthly") {
                                        
                                        double volsize = Double.parseDouble(volume.customDiskSize) 
                                        
                                        double monthlyAmount = costDisk.cost * 720.00 * volsize

                                        invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                                        invoiceItem.usageUnits = 1.0
                                        invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                                        double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                                        invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                                        double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)

                                        invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                                    } else {
                                        invoiceItem.usageUnitPrice = costDisk.cost
                                    }
                                    invoiceItem.invoice = invoice
                                    invoiceItem.referenceItemName = "Volume"
                                    invoiceItem.referenceItemId = volume.volumeReferenceId
                                    invoiceItem.referencePlanId = customDisk.diskOfferReferenceId
                                    invoiceItem.save(flush: true);  
                                    log.info("volume invoice item added for account: ${account.id} for volume: ${volume.id}")
                                    if (!invoiceItem.save()) {
                                        invoiceItem.errors.allErrors.each { Console.print(it) }
                                    }
                                }
                            }
                        }

                    } else { 
                        def disk = DiskOfferZoneCost.findWhere(diskOffer: volume.diskOffer, zone:volume.zone)
                        if (disk) {
                            def diskBillableItem
                            if(volume.billingType == "monthly") { 
                                diskBillableItem = BillableItem.get(14) 
                            } else {
                                diskBillableItem= BillableItem.get(2) 
                            }
                            if(diskBillableItem.enabled == true || diskBillableItem.enabled == "true") {
                                def invoice = Invoice.findWhere(account: volume.owner, status: InvoiceStatus.values()[6])
                                def invoiceItem = InvoiceItem.findWhere(invoice: invoice, referenceItemId: volume.volumeReferenceId)
                                if(!invoiceItem) {
                                    invoiceItem = new InvoiceItem()
                                    invoiceItem.billableItem = diskBillableItem
                                    invoiceItem.taxPercent = diskBillableItem.tax.percentage
                                    invoiceItem.zone = volume.zone
                                    
                                    if(volume.billingType == "monthly") {
                                        
                                        double volsize = Double.parseDouble(volume.customDiskSize) 
                                        
                                        double monthlyAmount =  disk.cost * 720.00 * volsize

                                        invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
                                        invoiceItem.usageUnits = 1.0
                                        invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
                                        double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
                                        invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   

                                        double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)

                                        invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                                    } else {
                                        invoiceItem.usageUnitPrice = disk.cost
                                    }
                                    
                                    invoiceItem.invoice = invoice
                                    invoiceItem.referenceItemName = "Volume"
                                    invoiceItem.referenceItemId = volume.volumeReferenceId
                                    invoiceItem.referencePlanId = volume.diskOffer.diskOfferReferenceId
                                    invoiceItem.save(flush: true);  
                                    log.info("volume invoice item added for account: ${account.id} for volume: ${volume.id}")
                                    if (!invoiceItem.save()) {
                                        invoiceItem.errors.allErrors.each { Console.print(it) }
                                    }
                                }
                            }
                        }
                    }
                }
                volume.save(flush: true);
            }
        } 
        def oldVolume = Volume.findAllWhere(owner: account); 
        for(int vm=0; vm < oldVolume.size(); vm++) { 
            def oldVolumeitem = oldVolume[vm]; 
            boolean blnExists = cloudStackDiskList.containsKey(oldVolumeitem.volumeReferenceId);
            if(blnExists.toString() == "false" || blnExists == false) {
               oldVolumeitem.deleted = true; 
               oldVolumeitem.save(flush: true); 
               log.info("volume delete success for account: ${account.id} for volume: ${oldVolumeitem.id}")
            }
        }
    }
}
