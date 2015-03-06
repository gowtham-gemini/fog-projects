package com.assistanz.fogpanel.licensemanager

class InvoiceItem {

    Invoice invoice
    Double unitPrice
    Double quantity
    Double totalAmount = 0
    String itemName
    
    static constraints = {
        
        invoice blank: false, nullable: false
        unitPrice blank: false, nullable: false
        quantity blank: false, nullable: false
        totalAmount blank: false, nullable: false
        itemName blank: false, nullable: false
        
    }
}
