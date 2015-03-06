package com.assistanz.fogpanel

import com.amazonaws.services.ec2.AmazonEC2AsyncClient;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.auth.BasicAWSCredentials;
import grails.transaction.Transactional
import com.assistanz.cloud.config.ConfigLoader
import grails.transaction.Transactional

@Transactional
class AwsAuthService {

    def springSecurityService
    
    // authenticate with AWS API
    def authenticateEC2() {
        
        ConfigLoader configLoader = ConfigLoader.getInstance();
        Properties props = configLoader.getProperties();
        
        def user = springSecurityService?.currentUser;
        
        println("Inside authentication");
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAI75ANWYNQM47MXRA", "VWPpiEh7nqrOQYBzz+C3iqO/HXpTxhqshll/Cibf");            
        AmazonEC2 ec2 = new AmazonEC2AsyncClient(awsCreds);
        ec2.setEndpoint("https://ec2.us-west-2.amazonaws.com");
        
        return ec2;
    }
}
