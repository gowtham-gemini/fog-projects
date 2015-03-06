/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.licensor;

import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Sujai
 */
public class FogLicensor implements Licensor {
    
    /** Logger is to provide log messages. */
    private static final Logger LOGGER = 
            Logger.getLogger(FogLicensor.class.getName());

    private static final String SERVER_VALIDATION_URL = 
            "https://portal.fogpanel.com/verify";
    private static final String SERVER_REGISTRATION_URL = 
            "https://portal.fogpanel.com/register";

    private static final int SEVENTEEN_DAYS = 17;
    private static final int TWO_DAYS_TIME = 2 * 1000 * 60 * 60 * 24;
    private static final int SEVENTEEN_DAYS_TIME = 17 * 1000 * 60 * 60 * 24;
    private static final int HEXADECIMAL_VALUE = 0xFF;
    
    private static final int HASH_PREFIX_SUBSTRING_START = 2;
    private static final int HASH_PREFIX_SUBSTRING_END = 10;
    private static final int HASH_SUFFIX_SUBSTRING_START = 40;
    private static final int HASH_SUFFIX_SUBSTRING_END = 48;
    
    private String key;
    private byte[] salt = "FogPanel".getBytes();

    private HttpConnector connector;
    private HttpConnector registrationConnector;

    /**
     * 
     * @param initialVersion the initialVersion parameter
     * @param requestID the requestID parameter
     * @param licenseeEmail the licenseEmail parameter
     * @param properties the properties parameter
     */
    public FogLicensor(final String initialVersion, 
            final String requestID, final String licenseeEmail, 
            final Properties properties) {
        key = buildKey(initialVersion, requestID, licenseeEmail);
        try {
            connector = new HttpConnector(SERVER_VALIDATION_URL, 
                    initialVersion, properties);
            registrationConnector = new HttpConnector(SERVER_REGISTRATION_URL,
                    initialVersion, properties);
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, "FogLicensor URL is not valid ", ex);
        }
    }

    /**
     * 
     * @param initialVersion the initialVersion parameter
     * @param requestID the requestID parameter
     * @param licenseeEmail the licenseeEmail parameter
     */
    public FogLicensor(final String initialVersion, 
            final String requestID, final String licenseeEmail) {
        this(initialVersion, requestID, licenseeEmail, new Properties());
    }

    /**
     * 
     * @param initialVersion the initialVersion parameter
     * @param requestID the requestID parameter
     * @param licenseeEmail the licenseEmail Parameter
     * @return the build key
     */
    private String buildKey(final String initialVersion, 
            final String requestID, final String licenseeEmail) {
        String[] params = {initialVersion, requestID, licenseeEmail};

        LOGGER.log(Level.SEVERE, 
                "Initial Version {0}: RequestID {1}: Email {2}", params);
        //x = SHA512 (initialVersion)
        String versionDigest = digest("SHA-512", initialVersion);
        //y = SHA256 (requestID)
        String requestDigest = digest("SHA-384", requestID);
        //z = SHA128 (licenseeEmail)
        String emailDigest = digest("SHA-256", licenseeEmail);
        //key = x+y+z
        String mix = versionDigest + requestDigest + emailDigest;

        return mix.substring(HASH_PREFIX_SUBSTRING_START, 
                HASH_PREFIX_SUBSTRING_END) + mix.substring(
                        HASH_SUFFIX_SUBSTRING_START, HASH_SUFFIX_SUBSTRING_END);
    }

    /**
     * 
     * @param algorithim the algorithm parameter
     * @param data the data parameter
     * @return the digest
     */
    private String digest(final String algorithim, final String data) {

        StringBuilder digestBuffer = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance(algorithim);
            md.update(data.getBytes());
            byte[] digestBytes = md.digest();

            for (int i = 0; i < digestBytes.length; i++) {
                digestBuffer.append(Integer.toHexString(
                        HEXADECIMAL_VALUE & digestBytes[i]));
            }

        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return digestBuffer.toString();
    }

    /**
     * 
     * @param data the data parameter
     * @return the plain text
     */
    private String decrypt(final String data) {

        String plainText = "";
        try {
            LOGGER.log(Level.FINER, "Begin decrypt");

            byte[] keyData = key.getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(keyData, "Blowfish");

            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
//            byte[] original = cipher.doFinal(new Base64().decode(data));
            byte[] original = cipher.doFinal(new BASE64Decoder().decodeBuffer(data));
            plainText = new String(original);

            LOGGER.log(Level.FINER, "End decrypt");

        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return plainText;
    }

    /**
     * 
     * @param data the data parameter
     * @return the plain text
     */
    private String encrypt(final String data) {

        String plainText = "";
        try {

            LOGGER.log(Level.FINER, "Begin encrypt");

            byte[] keyData = key.getBytes();

            SecretKeySpec keySpec = new SecretKeySpec(keyData, "Blowfish");

            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] forEncryption = data.getBytes();
            byte[] result = cipher.doFinal(forEncryption);
//            plainText = new Base64().encodeAsString(result);
            plainText = new BASE64Encoder().encode(result);

            LOGGER.log(Level.FINER, " End encrypt");

        } catch (IllegalBlockSizeException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return plainText;
    }

    @Override
    public License validate(final License license, 
            final Long activeHosts) throws LicenseException {

        Payload payload = new Payload(decrypt(license.getChecksum()));
        license.setLastUpdatedDate(payload.getLastUpdatedDate());

        Gson gson = new Gson();
        String json = gson.toJson(license);
        String response = connector.simplePost(json);
        License clientLicense = gson.fromJson(response, License.class);

        if (!clientLicense.isValid()) {
            throw new LicenseException(clientLicense.getErrorMessage());
        }

        return clientLicense;
    }

    @Override
    public Boolean isValid(final String checksum) {

        Payload payload = new Payload(decrypt(checksum));
        //If the app can run say true else false

        if (payload.getStatus().equals(Status.EXPIRED)) {
            LOGGER.log(Level.SEVERE, "License Expired: Status EXPIRED");
            return false;
        }

        if (payload.getStatus().equals(Status.LOCKED)) {
            LOGGER.log(Level.SEVERE, "License Expired: Status LOCKED");
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(payload.getLastUpdatedDate()));
        calendar.add(Calendar.DATE, SEVENTEEN_DAYS); //add 17 days
        Date date = calendar.getTime();
        Date now = new Date();

        Long[] params = {payload.getLastUpdatedDate(), date.getTime()};

        if (date.getTime() < now.getTime()) {
            LOGGER.log(Level.SEVERE,
                    "License Expired: EMERGENCY LICENSE TIME OUT", params);
            return false;
        }
        // if expired status is false

        return true;
    }

    @Override
    public Status getStatus(final String checksum, final Long activeHosts) {

        LOGGER.log(Level.FINER, "Call to get status for license");

        Payload payload = new Payload(decrypt(checksum));
        //calculate the status

        if (payload.getExpirationDate().before(new Date())) {
            return Status.EXPIRED;
        }

        Date extendedTrialDays = new Date();
        extendedTrialDays.setTime(
                extendedTrialDays.getTime() + SEVENTEEN_DAYS_TIME);

        if (payload.getLastUpdatedDate() > (extendedTrialDays.getTime())) {
            return Status.EXPIRED;
        }

        Date extendedEmergencyDays = new Date();
        extendedEmergencyDays.setTime(
                extendedEmergencyDays.getTime() + TWO_DAYS_TIME);

        if (payload.getLastUpdatedDate() > (extendedEmergencyDays.getTime())) {
            return Status.EMERGENCY_TRIAL;
        }

        //if last update + 17 days < today
        // status = EXPIRED
        //if last update + 48 hours < today
        // status = emergency_trial
        if (payload.getMaxSockets() < activeHosts) {
            return Status.EMERGENCY_TRIAL;
        }
        //TODO more logic left

        return payload.getStatus();
    }

    @Override
    public License register(final License license, final Long activeHosts) 
            throws LicenseException {

        LOGGER.log(Level.FINER, "Call to register for license");

        Gson gson = new Gson();
        String json = gson.toJson(license);

        LOGGER.log(Level.FINER, "Call to registrationConnector");

        String response = registrationConnector.simplePost(json);
        License clientLicense = gson.fromJson(response, License.class);

        LOGGER.log(Level.FINER, "Check license valid");

        if (!clientLicense.isValid()) {
            throw new LicenseException(clientLicense.getErrorMessage());
        }

        LOGGER.log(Level.FINER, "create new payload");

//        Payload payload = new Payload();
//        String payloadData = payload.toString();
//        
//        LOGGER.log(Level.FINER, "Payload: {0}", payloadData);
//        
//        String encryptedPayload = encrypt(payloadData);
//        LOGGER.log(Level.SEVERE, "Encrypted Payload: {0}", encryptedPayload);
//        clientLicense.setChecksum(encryptedPayload);
        LOGGER.log(Level.FINER, "return license after register");

        return clientLicense;
    }

    @Override
    public String buildPayload(final License license, final Long maxHosts, 
            final Long activeHosts, final String status, 
            final Long expirationTimestamp, final Long freeDays, 
            final String product) {

        LOGGER.log(Level.FINER, "Payload Built Start");

        Payload payload = new Payload();
        payload.setExpirationDate(new Date(expirationTimestamp));
        payload.setMaxSockets(maxHosts);
        payload.setFreeDays(freeDays);
        payload.setStatus(Status.valueOf(status));
        payload.setProduct(product);
        payload.setLastUpdatedDate(license.getLastUpdatedDate());
        payload.setRequestIDChecksum(digest("MD5", license.getRequestID()));

        LOGGER.log(Level.FINER, "payoad buid and returned to encrypt");

        return encrypt(payload.toString());
    }

    @Override
    public String getProduct(final String checksum) {
        Payload payload = new Payload(decrypt(checksum));
        //If the app can run say true else false
        return payload.getProduct();
    }
    
    /**
     * 
     * @author Sujai
     */
    class Payload {

        private String requestIDChecksum;
        private String product;
        private Long lastUpdatedDate;
        private Date expirationDate;
        private Long maxSockets;
        private Licensor.Status status;
        private Long freeDays;
        
        /**
         * Constructor.
         */
        public Payload() {

        }
        
        /**
         * 
         * @param xml 
         */
        public Payload(final String xml) {
            //Logic to split data and set in the variables
            //MD5(REQID) + '_' +
            //lastupdatedTimeStamp + '_' +
            //maxSockets + '_' +
            //status + '_' +
            //expirationTimestamp + '_'
            //freeDays

            LOGGER.log(Level.FINER, "Enter payload form XML");
            LOGGER.log(Level.FINER, "XML data : {0}", xml);

            Properties prop = new Properties();
            try {
                prop.load(new ByteArrayInputStream(xml.getBytes()));
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            LOGGER.log(Level.FINER, "requestIDChecksum : {0}", 
                    prop.getProperty("requestIDChecksum"));
            LOGGER.log(Level.FINER, "product : {0}",
                    prop.getProperty("product"));
            LOGGER.log(Level.FINER, "lastUpdatedDate : {0}", 
                    prop.getProperty("lastUpdatedDate"));
            LOGGER.log(Level.FINER, "expirationDate : {0}", 
                    prop.getProperty("expirationDate"));
            LOGGER.log(Level.FINER, "maxSockets : {0}", 
                    prop.getProperty("maxSockets"));
            LOGGER.log(Level.FINER, "status : {0}",
                    prop.getProperty("status"));
            LOGGER.log(Level.FINER, "freeDays : {0}", 
                    prop.getProperty("freeDays"));

            requestIDChecksum = prop.getProperty("requestIDChecksum");
            product = prop.getProperty("product");
            lastUpdatedDate = Long.parseLong(
                    prop.getProperty("lastUpdatedDate"));
            expirationDate = new Date(Long.parseLong(
                    prop.getProperty("expirationDate")));
            maxSockets = Long.parseLong(prop.getProperty("maxSockets"));
            status = Licensor.Status.valueOf(prop.getProperty("status"));
            freeDays = Long.parseLong(prop.getProperty("freeDays"));

            LOGGER.log(Level.FINER, "End create payload form XML");

        }
        
        /**
         * 
         * @return the product
         */
        public String getProduct() {
            return product;
        }

        /**
         * 
         * @param products the products parameter
         */
        public void setProduct(final String products) {
            this.product = products;
        }

        /**
         * 
         * @return the requestIdChecksum
         */
        public String getRequestIDChecksum() {
            return requestIDChecksum;
        }
        
        /**
         * 
         * @param requestIDChecksum the requestIDChecksum parameter
         */
        public void setRequestIDChecksum(final String requestIDChecksum) {
            this.requestIDChecksum = requestIDChecksum;
        }

        /**
         * 
         * @return the last updated date
         */
        public Long getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        /**
         * 
         * @param lastUpdatedDate the lastUpdatedDate parameter
         */
        public void setLastUpdatedDate(final Long lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        /**
         * 
         * @return the expiration date
         */
        public Date getExpirationDate() {
            return expirationDate;
        }

        /**
         * 
         * @param expirationDate the date of expiration 
         */
        public void setExpirationDate(final Date expirationDate) {
            this.expirationDate = expirationDate;
        }

        /**
         * 
         * @return the maxSockets
         */
        public Long getMaxSockets() {
            return maxSockets;
        }

        /**
         * 
         * @param maxSockets the maxSockets parameter
         */
        public void setMaxSockets(final Long maxSockets) {
            this.maxSockets = maxSockets;
        }

        /**
         * 
         * @return the licensor status
         */
        public Licensor.Status getStatus() {
            return status;
        }

        /**
         * 
         * @param status the licensor status 
         */
        public void setStatus(final Licensor.Status status) {
            this.status = status;
        }

        /**
         * 
         * @return the number of freeDays
         */
        public Long getFreeDays() {
            return freeDays;
        }

        /**
         * 
         * @param freeDays to set number of freeDays 
         */
        public void setFreeDays(final Long freeDays) {
            this.freeDays = freeDays;
        }

        /**
         * 
         * @return StringWriter reference as a String
         */
        @Override
        public String toString() {

            if (requestIDChecksum == null) {
                return "";
            }

            Properties prop = new Properties();
            prop.setProperty("requestIDChecksum", requestIDChecksum);
            prop.setProperty("product", product);
            prop.setProperty("lastUpdatedDate", Long.toString(lastUpdatedDate));
            prop.setProperty("expirationDate", 
                    Long.toString(expirationDate.getTime()));
            prop.setProperty("maxSockets", Long.toString(maxSockets));
            prop.setProperty("status", status.toString());
            prop.setProperty("freeDays", Long.toString(freeDays));

            StringWriter writer = new StringWriter();

            try {
                prop.store(writer, "");
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

            LOGGER.log(Level.FINER, "XML payload {0}", writer.toString());

            return writer.toString();
        }

    }

}
