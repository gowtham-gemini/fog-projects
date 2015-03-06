/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.fogpanel;

import java.util.HashMap;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author Az-PHP
 */
public class MailManager {
            
    private Session session;

    private static MailManager mailManager = new MailManager( );
   
    /* A private Constructor prevents any other 
     * class from instantiating.
     */
    private MailManager(){ }

    /* Static 'instance' method */
    public static MailManager getInstance( ) {
       return mailManager;
    }
    /* Other methods protected by singleton-ness */
    protected static void demoMethod( ) {
       System.out.println("demoMethod for singleton"); 
    }
    
    protected void setConfig(String from, String host, 
            final String userName, final String password, Properties props) throws Exception {
            
            
        session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
            }
        });
    }
    
    protected void sendMail(String to, String subject, String message)throws Exception {
            
        try {
            // Instantiatee a message 
            Message msg = new MimeMessage(session);

            //Set message attributes
//            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // Set message content
            msg.setText(message);
            msg.setContent(message, "text/html; charset=utf-8");
            //Send the message
            Transport.send(msg); 
        }
        catch (MessagingException mex) {
            // Prints all nested (chained) exceptions as well
            mex.printStackTrace();
            throw mex;
        }
    }
}
