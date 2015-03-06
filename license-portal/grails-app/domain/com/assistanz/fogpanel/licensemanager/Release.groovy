package com.assistanz.fogpanel.licensemanager

class Release {
    
    Product product
    String productVersion
    String vhdPath
    String bundlePath
    String releaseNotes
    
    static mapping = {
        table "product_release"
        releaseNotes type: "text"
    }

    static constraints = {
        product blank: false, nullable: false
        productVersion blank: false, nullable: false
        vhdPath blank: true, nullable: true
        bundlePath blank: true, nullable: true
        releaseNotes blank: false, nullable: false
    }
    
}
