package com.assistanz.fogpanel

import com.assistanz.fogpanel.Invoice

//@gorm.AuditStamp
class Account {
	
	String firstName
        String companyName
        String lastName
        String email
        Date signUpDate
        Date activationDate
        String userName
        String password
        String preferredLanguage
	String streetAddress
        String streetAddress1        
	String city
        String zip
        String phoneNumber
        State state
	Country country
        String billingStreetAddress
        String billingStreetAddress1
        String cloudPassword
	String billingCity
        String billingZip
        State billingState
	Country billingCountry
        String  billingPhoneNumber
        String promotionalCode
        Domain domain
        Date nextBillingData
        Date lastBillingData
        Date vmSnapUsageDate
        Double previousBalance
        Double creditLimit
        AccountType accountType
        String defaultCard
        Double credit
        Double totalAmount
        Double totalPayable
        Double totalPaid
        Double previousPaid
        Boolean cardVerified = false
        Boolean lateFee
        Double penaltyFee
        Status status
        Date lastUsageRunDate
        String creditLimitLevel
        String uuid
        Integer autoPaymentAttempt = 0;
        Double cardExpiryMonth 
        Double cardExpiryYear
      
        String vmLimit
        String storageLimit
        String snapshotLimit
        String bandwidthLimit
        Boolean isUsageRunningFailed = false
    
    
//        Integer createdBy
//        Integer editedBy
//        Date editedDate
//        Date createdDate
    
	static constraints = {
		firstName (nullable: false, blank: false)
                signUpDate (nullable: false, blank: false) 
                cloudPassword (nullable: false, blank: false)
                activationDate (nullable: true, blank: true)
                companyName (nullable: true, blank: true)
                lastName (maxSize: 150, nullable: false, blank: false)
                email (email: true, blank: false)
		userName (minSize: 5, maxSize: 50, nullable: false, blank: false, unique: true)
                phoneNumber (minSize: 4, maxSize: 20, nullable: false, blank: false)
                billingPhoneNumber (minSize: 4, maxSize: 20, nullable: false, blank: false)
		password (minSize: 6, maxSize: 15, nullable: false, blank: false)
                preferredLanguage (maxSize: 100, nullable: false, blank: false)
		streetAddress (maxSize: 100, nullable: false, blank: false)
                streetAddress1(nullable: true, blank: true)
                billingStreetAddress1(nullable: true, blank: true)
                
		city (maxSize: 100, nullable: false, blank: false)
		state (maxSize: 100, nullable: false, blank: false)
		zip (minSize: 1, maxSize: 10, nullable: false, blank: false)
		country (maxSize: 100, nullable: false, blank: false)
                billingStreetAddress (maxSize: 100, nullable: false, blank: false)
		billingCity (maxSize: 100, nullable: false, blank: false)
		billingState (maxSize: 100, nullable: false, blank: false)
		billingZip (minSize: 1, maxSize: 10, nullable: false, blank: false)
		billingCountry (maxSize: 100, nullable: false, blank: false)
                promotionalCode(nullable: true, blank: true)
                domain(nullable: true, blank: true)
                previousBalance(nullable: true, blank: true)
                nextBillingData(nullable: true, blank: true)
                lastBillingData(nullable: true, blank: true)
                creditLimit(nullable: false, blank: false)
                defaultCard(nullable: true, blank: true)
                accountType(nullable: false, blank: false)
                credit(nullable: false, blank: false)
                totalAmount(nullable: false, blank: false)
                totalPayable(nullable: false, blank: false)
                totalPaid(nullable: false, blank: false)
                previousPaid(nullable: false, blank: false)
                cardVerified(nullable: false, blank: false)
                lateFee(nullable: false, blank: false)
                penaltyFee(nullable: false, blank: false)
                status(nullable: false, blank: false)
                vmSnapUsageDate(nullable: true, blank: true)
                lastUsageRunDate(nullable: true, blank: true)
                creditLimitLevel(nullable: true, blank: true)
                uuid(nullable: true, blank: true)
                autoPaymentAttempt(nullable: true, blank: true)
                cardExpiryMonth(nullable: true, blank: true)
                cardExpiryYear(nullable: true, blank: true)
                
                vmLimit(nullable: true, blank: true)
                storageLimit(nullable: true, blank: true)
                snapshotLimit(nullable: true, blank: true)
                bandwidthLimit(nullable: true, blank: true)
                isUsageRunningFailed(nullable: false, blank: false)
                
        
//                createdBy   nullable:true,display:false,editable:false
//                editedBy    nullable:true,display:false,editable:false
//                editedDate  nullable:true,display:false,editable:false
//                createdDate nullable:true,display:false,editable:false
            } 
            static hasMany = [invoices: Invoice, offeringUsages: OfferingUsage, discounts: Discount, sshKeys: SSHKeys]
            static belongsTo = [Discount]
            
    } 
        
    enum AccountType  {
        TRIAL,RETAIL,ADMIN
    }
    
    enum Status  {
        ACTIVE, //0
        BLOCKED, //1
        LOCKED, //2
        DISABLED, //3
        NOT_VERIFIED, //4 
        SUSPENDED, //5
        CANCELED, //6
        CLOSED //7
    }

    class Address { 
        String street
        String state
        String country
        String city
        String zip
    
        static constraints = {
            street (maxSize: 100, nullable: false, blank: false)
            city (maxSize: 100, nullable: false, blank: false)
            state (maxSize: 100, nullable: false, blank: false)
            country (maxSize: 100, nullable: false, blank: false)
            zip (minSize: 1, maxSize: 10, nullable: false, blank: false)
        }
    }
