
package com.assistanz.licensor;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sujai
 */
public final class LicenseManager {
    
    private static Licensor licensor;
    private static final Logger LOGGER = 
            Logger.getLogger(LicenseManager.class.getName());
    
    /**
     * Constructor.
     */
    private LicenseManager() {
    }
    
    /**
     * 
     * @param initialVersion the initialVersion parameter
     * @param requestID the requestId of licensor 
     * @param licenseeEmail the licenseEmail of licensor 
     * @param properties the licensor properties 
     * @return the licensor
     */
    public static Licensor getLicensor(final String initialVersion, 
            final String requestID, final String licenseeEmail, 
            final Properties properties) {
        if (licensor == null) {
            LOGGER.log(Level.FINER, "Initializing Licensor");
            licensor = new FogLicensor(initialVersion, requestID, 
                    licenseeEmail, properties);
        }
        
        return licensor;
    }
    
    /**
     * 
     * @param initialVersion the initialVersion parameter
     * @param requestID the requestId of licensor 
     * @param licenseeEmail the licenseEmail of licensor 
     * @param properties the licensor properties 
     * @return the fogLicensor
     */
    public static Licensor getNewLicensor(final String initialVersion, 
            final String requestID, final String licenseeEmail, 
            final Properties properties) {
        LOGGER.log(Level.FINER, "Initializing New Licensor");
        return  new FogLicensor(initialVersion, requestID, licenseeEmail, 
                properties);
    }
}
