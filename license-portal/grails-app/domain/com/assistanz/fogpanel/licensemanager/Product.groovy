package com.assistanz.fogpanel.licensemanager

class Product {
    
    String name
    Boolean showcasedProduct = false
    String code
    String description
    String url
    Long licenseQuantity
    Double price
    Product baseProduct
    Status status = Status.ACTIVE
    
    enum Status {
        ACTIVE,
        DISABLED
    }
    
    static mapping = {
        description type: "text"
    }

    static constraints = {
        name blank: false, nullable: false, unique: true
        showcasedProduct blank: false, nullable: false
        code blank: false, unique: true
        description blank: false, nullable: false
        url blank: false, nullable: false
        licenseQuantity blank: false, nullable: false
        price blank: false, nullable: false
        baseProduct blank: true, nullable: true
    }
    
    static namedQueries = {
       activeProducts {
           eq 'status', Status.ACTIVE
       }
       
       activeBaseProducts {
            eq 'status', Status.ACTIVE
            isNull 'baseProduct'
       }
       
   }
    
    String toString() {
        name
    }
}
