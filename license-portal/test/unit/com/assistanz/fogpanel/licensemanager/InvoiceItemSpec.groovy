package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class InvoiceItemSpec extends Specification {
    
    void 'Test invoice item with valid values'() { 
        
        given:
            def user = new User()
            def date = Calendar.getInstance();
            def invoice = new Invoice()
            def invoiceItem = new InvoiceItem()
            
        when: 
            user.username = "ramkumar@gmail.com"
            user.password = "ramkumar123"
            user.enabled = true
            user.accountExpired = true
            user.accountLocked = true
            user.passwordExpired = true
            user.firstName = "ram"
            user.companyName = "fpsolution"
            user.lastName = "kumar"
            user.address1 = "1/2 ram street chennai"
            user.address2 = "1/2 ram street chennai"
            user.state = "tamilnadu"
            user.country = "india"
            user.city = "coimbatore"
            user.zip = "642811"
            user.tokenExpiryDate = date.getTime()
            user.token = "tok123654"
            user.status = "ACTIVE"
            
            invoice.user = user
            invoice.totalAmount = 456.321
            invoice.invoiceDate = date.getTime()
            invoice.paymentDate = date.getTime()
            invoice.currency = "USD"
            invoice.status = "PAID"
            
            invoiceItem.invoice = invoice
            invoiceItem.unitPrice = 123.258
            invoiceItem.quantity = 654.213
            invoiceItem.totalAmount = 897.546
            invoiceItem.itemName = "computer"
            
        then: 
            invoiceItem.validate()
    }
    
    void 'Test invoice item with blank values'() { 
        
        when:
            def invoiceItem = new InvoiceItem()
            
        then: 
            !invoiceItem.validate()
    }
    
    void 'Test invoice item with null values'() { 
        
        given:
            def invoice = new Invoice()
            def invoiceItem = new InvoiceItem()
        
        when: 
            invoiceItem.invoice = null
        
        then: 
            !invoiceItem.validate()
    }
}
