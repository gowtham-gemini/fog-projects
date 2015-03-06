package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestFor(Product)
@TestMixin(GrailsUnitTestMixin)
class ProductSpec extends Specification {
    
    void 'Test Product with valid values'() { 
        
        given:
            def product = new Product()

        when: 
            product.name = "computer"
            product.description = "computer used for do a calculation "
            product.licenseQuantity = 124563
            product.price = 541.235
            product.code = "12365"
            product.showcasedProduct = false
            product.url = "http://www.fogpanel.com/pricing/"
            product.baseProduct = null
            product.status = "ACTIVE"
        
        then: 
            product.validate()
    }
    
    void 'Test Product name with unique value'() { 
        
        given:
            def product = new Product()

        when: 
            product.name = "computer"
            
        then: 
            !product.validate()
    }
    
    void 'Test Product with null values'() { 
        
        when:             
            def product = new Product()
        
        then:
            !product.validate()
    }
    
    void 'Test Product with blank values'() { 
        
        when: 
            def product = new Product()
        
        then: 
            !product.validate()
    }
}
