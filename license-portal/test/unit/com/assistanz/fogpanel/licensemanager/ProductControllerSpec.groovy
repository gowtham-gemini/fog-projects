package com.assistanz.fogpanel.licensemanager

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ProductController)
@Mock([Product])
class ProductControllerSpec extends Specification {
    
    void "Test Product controller list method"() {
        
            def product = null
        given:
            product = new Product()
            product.name = "computer"
            product.description = "computer used for do a calculation "
            product.licenseQuantity = 124563
            product.price = 541.235
            product.code = "12365"
            product.showcasedProduct = false
            product.baseProduct = product
            product.url = "http://www.fogpanel.com/pricing/"
            product.status = "ACTIVE"
            product.save(flush: true)
            
        when:
            def model = controller.list()
                
        then:
            product.id == model.products.id[0]
    }
}
