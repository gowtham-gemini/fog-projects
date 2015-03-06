/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.fogpanel

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gowtham
 */
class APIArgumentValidation {
	
    
    def paymentCodeValidation(String code) {
        def payment = Payments.findByManualPaymentCode(code)
        if(payment) {
            throw new PaymentCodeAlreadyExistException("{'errorCode':'2000' ,'message':'payment Code already exists'}");
        }
    }
    
    def emailValidation(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if(mat.matches()){
            def account = Account.findByUserName(email)
            if(!account) {
                throw new UserNameNotFoundException("{'errorCode':'2000' ,'message':'userName does not exists'}");
            }
        }else{
            throw new InvalidUserNameException("{'errorCode':'2000' ,'message':'Invalid userName value'}");
        }
    }
    
    
    def dateValidation(String date) {
        try {            
            Pattern pattern = Pattern.compile( "((19|20)\\d\\d)\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])");
            
            Matcher mat = pattern.matcher(date);
            if(mat.matches()){
                DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");   
                Date toDate = givenDateFormater.parse(date);
            } else {
                throw new InvalidDateRangeException("{'errorCode':'2000' ,'message':'Invalid date value'}"); 
            }                       
        }  catch (Exception ex) {
            throw new InvalidDateRangeException("{'errorCode':'2000' ,'message':'Invalid date value'}");
        } 
        
    }
    
    def amountValidation(String amount) {
        try {
                   
            String[] parts = amount.split("\\.");
            
            if(parts[1].length() > 2) { 
                throw new InvalidAmountException("{'errorCode':'2000' ,'message':'Invalid amount value'}");
            } else {
               Double.parseDouble(amount); 
            }
        }  catch (Exception ex) {
            throw new InvalidAmountException("{'errorCode':'2000' ,'message':'Invalid amount value'}");
        } 
    }
    
    def countryValidation(String country) {
        try {
            def countryList = Country.findById(country)
            
            if(!countryList) {
                throw new InvalidCountryException("{'errorCode':'2000' ,'message':'Invalid country value'}");
            }
           
        }  catch (Exception ex) {
            throw new InvalidCountryException("{'errorCode':'2000' ,'message':'Invalid country value'}");
        } 
    }
    
    def stateValidation(String state) {
        try {
            def stateList = State.findById(state)
            if(!stateList) {
                throw new InvalidStatException("{'errorCode':'2000' ,'message':'Invalid state value'}");
            }
        }  catch (Exception ex) {
            throw new InvalidStatException("{'errorCode':'2000' ,'message':'Invalid state value'}");
        } 
    }
    
     def resourceTypeValidation(String referenceId, String resourceType) {
        def result = ""
        if(resourceType == "computingOffer") {
            result = ComputingOffer.findWhere(offerReferenceId: referenceId, deleted: false, available: true);            
        } else if(resourceType == "template") {
            result = Template.findWhere(templateReferenceId: referenceId, deleted: false, isReady: true)
        } else if(resourceType == "zone") {
            result = Zone.findByReferenceZoneId(referenceId)
        } else if(resourceType == "firewall")  {
            result = SecurityGroupTemplate.get(referenceId);
        } else if(resourceType == "virtualMachine")  {
            result = VirtualMachine.findByReferenceId(referenceId);
        } else {
            throw new InvalidFieldException("{'errorCode':'2000' ,'message':'Invalid field value'}");
        }        
        if(!result) {
             throw new InvalidFieldException("{'errorCode':'2000' ,'message':'Invalid field value'}");
        }
    }
    
    def baseOsValidation(String baseOs) {
        if(baseOs == "Linux" || baseOs == "Windows") {            
        } else {
            throw new InvalidBaseOSException("{'errorCode':'2000' ,'message':'Invalid baseOS value'}");
        }        
    }  
    
    
    def billingTypeValidation(String billingtype) {
        if(billingtype == "hourly" || billingtype == "monthly") {            
        } else {
            throw new InvalidBillingTypeException("{'errorCode':'2000' ,'message':'Invalid billingtype value'}");
        }        
    }  
    
    def appTemplateValidation(String appTemplate) {
        if((appTemplate == true || appTemplate == "true") || (appTemplate == false || appTemplate == "false")) {
        } else {
            throw new InvalidAppTemplateException("{'errorCode':'2000' ,'message':'Invalid appTemplate value'}");
        }        
    }
    
    def storageTypeValidation(String storageType) {
        if((storageType == "Local" || storageType == "Shared")) {
        } else {
             throw new InvalidStorageTypeException("{'errorCode':'2000' ,'message':'Invalid storageType value'}");
        } 
    }
    def dateValidation(String fdate, String tdate) {
        try {
            DateFormat givenDateFormater = new SimpleDateFormat("yyyy-MM-dd");   
            Date toDate = givenDateFormater.parse(fdate);
            Date fromDate = givenDateFormater.parse(tdate);
            
            if(toDate > fromDate) {
                throw new InvalidDateRangeException("{'errorCode':'2000' ,'message':'Invalid date value'}");
            }
            
        } catch (Exception ex) {
            throw new InvalidDateRangeException("{'errorCode':'2000' ,'message':'Invalid date value'}");
        } 
    }
    
    def accountStatusValidation(String status) {
        if(status == GeneralConstants.ACCOUNT_STATUS_ACTIVE || status == GeneralConstants.ACCOUNT_STATUS_BLOCKED || status == GeneralConstants.ACCOUNT_STATUS_LOCKED || status == GeneralConstants.ACCOUNT_STATUS_DISABLED|| status == GeneralConstants.ACCOUNT_STATUS_NOT_VERIFIED|| status == GeneralConstants.ACCOUNT_STATUS_SUSPENDED|| status == GeneralConstants.ACCOUNT_STATUS_CANCELED|| status == GeneralConstants.ACCOUNT_STATUS_CLOSED) {
        } else {
            throw new InvalidStatusException("{'errorCode':'2000' ,'message':'Invalid status value'}");
        }
    }
    
    def numberValidation(String number) {
        
        try {            
            Integer.parseInt(number);                        
        } catch (Exception e) {
            throw new PaginationException("{'errorCode':'2000' ,'message':'invalid page or recordPerPage'}");
        }
    }
}

