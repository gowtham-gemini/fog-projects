/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.licensor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lakshmi
 */
public class FogLicensorTest {
    
    public FogLicensorTest() {
    }
    
        /**
     * Test of validate method, of class FogLicensor.
     */
//    @Test
//    public void testEncrypt() throws Exception {
//       System.out.println("encrypt"); 
//       String data = "";
//       FogLicensor fogLicensor = null;
//       License expResult = null;
//       License result = fogLicensor
//    }

    /**
     * Test of validate method, of class FogLicensor.
     */
    @Test
    public void testValidate() throws Exception {
        System.out.println("validate");
        License license = null;
        Long activeHosts = null;
        FogLicensor instance = null;
        License expResult = null;
//        License result = instance.validate(license, activeHosts);
//        assertEquals(expResult, 8);        
    }

    /**
     * Test of isValid method, of class FogLicensor.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        String checksum = "";
        FogLicensor instance = null;
        Boolean expResult = null;
//        Boolean result = instance.isValid(checksum);
//        assertEquals(expResult, 3);   
    }

    /**
     * Test of getStatus method, of class FogLicensor.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        String checksum = "";
        Long activeHosts = null;
        FogLicensor instance = null;
        Licensor.Status expResult = null;
//        Licensor.Status result = instance.getStatus(checksum, activeHosts);
//        assertEquals(expResult, 2);
    }

    /**
     * Test of register method, of class FogLicensor.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        License license = null;
        Long activeHosts = null;
        FogLicensor instance = null;
        License expResult = null;
//        License result = instance.register(license, activeHosts);
//        assertEquals(expResult, 5);
    }

    /**
     * Test of buildPayload method, of class FogLicensor.
     */
    @Test
    public void testBuildPayload() {
        System.out.println("buildPayload");
        License license = null;
        Long maxHosts = null;
        Long activeHosts = null;
        String status = "";
        Long expirationTimestamp = null;
        Long freeDays = null;
        String product = "";
        FogLicensor instance = null;
        String expResult = "";
        //String result = instance.buildPayload(license, maxHosts, activeHosts, status, expirationTimestamp, freeDays, product);
//        assertEquals(expResult, 1);
    }

    /**
     * Test of getProduct method, of class FogLicensor.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");
        String checksum = "";
        FogLicensor instance = null;
        String expResult = "";
//        String result = instance.getProduct(checksum);
//        assertEquals(expResult, 4);
    }
    
}
