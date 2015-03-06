package com.assistanz.fogpanel.licensemanager

class Invoice {
    
    User user
    Double totalAmount = 0
    Date invoiceDate
    Date paymentDate
    String currency = "USD"
    Status status = Status.DUE
    
    
    static constraints = {
        
        user blank: false, nullable: false
        totalAmount blank: false, nullable: false
        invoiceDate blank: false, nullable: false
        paymentDate blank: false, nullable: false
        currency blank: false, nullable: false
        status blank: false, nullable: false
        
    }
    
    
    enum Status {
        PAID("PAID"),
        DUE("DUE"),
        
        private String name
        
        private Status(String name) {
            this.name = name
        }

        static Status valueOfName( String name ) {
            values().find { it.name == name }
        }
        
        static Status valueOfName( Status value ) {
            value.name
        }
    }
    
}


