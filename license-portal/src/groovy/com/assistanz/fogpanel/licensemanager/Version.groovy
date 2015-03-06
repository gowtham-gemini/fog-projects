
package com.assistanz.fogpanel.licensemanager

/**
 *
 * @author developer
 */
class Version {
    
    Byte major = 0
    Byte minor = 0
    Byte patch = 0
    
    @Override
    String toString() {
        return major + "." + minor  + "." + patch
    }
	
}

