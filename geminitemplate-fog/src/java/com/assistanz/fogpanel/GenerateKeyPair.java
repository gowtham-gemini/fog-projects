/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.fogpanel;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMWriter;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Abdul
 */
public class GenerateKeyPair {
    
    private KeyPair keyPair;
    
    public GenerateKeyPair() throws NoSuchAlgorithmException {
        
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        this.keyPair = keyGen.genKeyPair();
    }
    
    public String getPublicKey() throws IOException {
  
        PublicKey publicKey = keyPair.getPublic();

        BASE64Encoder encoder = new BASE64Encoder();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        String encodedPublicKey = "ssh-rsa " + encoder.encode(encodePublicKey(rsaPublicKey)) ;
        encodedPublicKey = encodedPublicKey.replaceAll("\\n", "");
        
        return encodedPublicKey;
        

    }
    
    public String getPrivateKey() throws IOException { 
        
        PrivateKey privateKey = keyPair.getPrivate();
        StringWriter stringWriter = new StringWriter();
        PEMWriter writer = new PEMWriter(stringWriter);
        
        writer.writeObject(privateKey);
        writer.close();
        
        return stringWriter.toString();
    }

    public static byte[] encodePublicKey(RSAPublicKey key) throws IOException {
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        /* encode the "ssh-rsa" string */
        byte[] sshrsa = new byte[]{0, 0, 0, 7, 's', 's', 'h', '-', 'r', 's', 'a'};
        out.write(sshrsa);
        /* Encode the public exponent */
        BigInteger e = key.getPublicExponent();
        byte[] data = e.toByteArray();
        encodeUInt32(data.length, out);
        out.write(data);
        /* Encode the modulus */
        BigInteger m = key.getModulus();
        data = m.toByteArray();
        encodeUInt32(data.length, out);
        out.write(data);
        
        return out.toByteArray();
        
    }

    public static void encodeUInt32(int value, OutputStream out) throws IOException {
        
        byte[] tmp = new byte[4];
        tmp[0] = (byte) ((value >>> 24) & 0xff);
        tmp[1] = (byte) ((value >>> 16) & 0xff);
        tmp[2] = (byte) ((value >>> 8) & 0xff);
        tmp[3] = (byte) (value & 0xff);
        out.write(tmp);
    }

}
