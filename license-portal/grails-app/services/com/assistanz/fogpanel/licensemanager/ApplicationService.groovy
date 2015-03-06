package com.assistanz.fogpanel.licensemanager

import grails.transaction.Transactional

@Transactional
class ApplicationService {
    
    def springSecurityService

    def buyLicense(params) {
        
        def invoice = new Invoice()
        invoice.invoiceDate = new Date()
        invoice.paymentDate = new Date()
        invoice.user = springSecurityService.currentUser
        invoice.save(flush: true)
        
        def productInstance = new ProductInstance()
        productInstance.user = springSecurityService.currentUser
         
        for(def i = 0; i<params.productId.length ; i++) {

             def quantity = params.quantity[i]
             def productId = params.productId[i]
             def product = Product.get(productId)
           
             if(quantity != 0) {
                                                
                if(product && (product.baseProduct == null || product.baseProduct == "null" || product.baseProduct == "")) {
                    
                    productInstance.product = product
                    double totalBaseLicense = product.licenseQuantity
                    productInstance.baseLicense = totalBaseLicense
                    
                    createInvoiceItem(product, invoice, Double.parseDouble(params.licenseExpiryMonth), Double.parseDouble(quantity))
                    
                } else if(product && product.baseProduct != null) {
                    
                    createInvoiceItem(product, invoice, Double.parseDouble(params.licenseExpiryMonth), Double.parseDouble(quantity))
                    double totalAdditionalLicense = Long.parseLong(quantity) * product.licenseQuantity
                    productInstance.additionalLicense += totalAdditionalLicense
                    
                    createInvoiceItem(product, invoice, Double.parseDouble(params.licenseExpiryMonth), Double.parseDouble(quantity))
                    
                }
             }
         }
        productInstance.inceptionDate = new Date()
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MONTH, params.licenseExpiryMonth.toInteger());  // number of months to add
        productInstance.expirationDate = calender.getTime()
        productInstance.secretKey = "XXXYYY"
//        productInstance.status = Status.ACTIVE

        productInstance.save flush:true
        if (!productInstance.save()) {
             productInstance.errors.allErrors.each { println it }
        }
    }
    
    def createInvoiceItem(item, invoice, licensePeriod, quantity) {
               
        def invoiceItem = new InvoiceItem()
        invoiceItem.invoice = invoice
        invoiceItem.unitPrice = item.price
        invoiceItem.itemName = item.name
        invoiceItem.quantity = quantity
        double totalAmount = quantity * item.price * licensePeriod;
        invoiceItem.totalAmount = totalAmount
        invoiceItem.save(flush: true)
        
        invoice.totalAmount += invoiceItem.totalAmount;
        invoice.save(flush: true)
    }
}
