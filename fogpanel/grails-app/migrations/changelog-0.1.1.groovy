databaseChangeLog = {

    changeSet(author: "Sujai (manual)", id: "changelog-0.1.1", runInTransaction: "true", failOnError: "true") {
        insert(tableName: "account") {
            column(name: "id", valueNumeric: 10001)
            column(name: "country_id", valueNumeric: 100)            
            column(name: "user_name", value: "admin")
            column(name: "company_name", value: "Administrator")
            column(name: "first_name", value: "Administrator")
            column(name: "last_name", value: "Administrator")
            column(name: "street_address", value: "Administratoraddress")
            column(name: "city", value: "admincity")
            column(name: "state_id", valueNumeric: 1)
            column(name: "zip", value: "00000")
            column(name: "password", value: "passw0rd")
            column(name: "sign_up_date", value: "1111-11-11 11:11:11")
            column(name: "activation_date", value: "1111-11-11 11:11:11")
            column(name: "credit_limit", valueNumeric: 0)
            column(name: "account_type", value: "RETAIL")
            column(name: "email", value: "admin@example.net")
            column(name: "version", valueNumeric: 1)
            column(name: "preferred_language",  value: "admin")
            column(name: "billing_country_id", valueNumeric: 100) 
            column(name: "billing_street_address", value: "Administratoraddress")
            column(name: "billing_city", value: "admincity")
            column(name: "billing_state_id", valueNumeric: 1)
            column(name: "billing_zip", value: "00000")
            column(name: "phone_number", value: "0000000000")
            column(name: "billing_phone_number", value: "0000000000")
            column(name: "credit", valueNumeric: 0)
            column(name: "last_billing_data", value: "2013-08-11 07:09:29")
            column(name: "next_billing_data", value: "2013-08-12 07:09:29")
            column(name: "late_fee", valueNumeric: 0)
            column(name: "penalty_fee", valueNumeric: 0)
            column(name: "previous_paid", valueNumeric: 0)
            column(name: "previous_balance", valueNumeric: 0)
            column(name: "status", value: "ACTIVE")
            column(name: "total_amount", valueNumeric: 0)
            column(name: "total_paid", valueNumeric: 0)
            column(name: "total_payable", valueNumeric: 0)    
            column(name: "card_verified", valueNumeric: 0)  
        }

        insert(tableName: "role") {
            column(name: "id", valueNumeric: 1)
            column(name: "authority", value: "ROLE_ADMIN")
            column(name: "version", valueNumeric: 1)
        }

        insert(tableName: "role") {
            column(name: "id", valueNumeric: 2)
            column(name: "authority", value: "ROLE_FREE_USER")
            column(name: "version", valueNumeric: 1)
        }

        insert(tableName: "role") {
            column(name: "id", valueNumeric: 3)
            column(name: "authority", value: "ROLE_PAID_USER")
            column(name: "version", valueNumeric: 1)
        }

        insert(tableName: "user") {
            column(name: "id", valueNumeric: 1)
            column(name: "username", value: "admin")
            column(name: "password", value: "passw0rd")
            column(name: "enabled", valueNumeric: 1)
            column(name: "account_expired", valueNumeric: 0)
            column(name: "account_locked", valueNumeric: 0)
            column(name: "password_expired", valueNumeric: 0)
            column(name: "account_id", valueNumeric: 10001)
            column(name: "version", valueNumeric: 1)
            column(name: "token", value: "admin")
            column(name: "failure_count", valueNumeric: 0)
        }

        insert(tableName: "user_role") {
            column(name: "user_id", valueNumeric: 1)
            column(name: "role_id", valueNumeric: 1)
        }  
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 1)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "URL")
            column(name: "name", value: "CLOUD_STACK_URL")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 2)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "API key")
            column(name: "name", value: "CLOUD_STACK_ROOT_API_KEY")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 3)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "SECRET key")
            column(name: "name", value: "CLOUD_STACK_ROOT_SECRET_KEY")
            column(name: "value", value: "")
        } 
        
         insert(tableName: "config") {
            column(name: "id", valueNumeric: 4)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "host")
            column(name: "name", value: "MAIL_CONFIG_HOST")
            column(name: "value", value: "")
        } 
        
         insert(tableName: "config") {
            column(name: "id", valueNumeric: 5)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "username")
            column(name: "name", value: "MAIL_CONFIG_USER_NAME")
            column(name: "value", value: "")
        } 
        
         insert(tableName: "config") {
            column(name: "id", valueNumeric: 6)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "port")
            column(name: "name", value: "MAIL_CONFIG_PORT")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 7)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "from")
            column(name: "name", value: "MAIL_CONFIG_FROM")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 8)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "ssl")
            column(name: "name", value: "MAIL_CONFIG_SSL")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 9)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "from")
            column(name: "name", value: "MAIL_CONFIG_SSL_PROPS")
            column(name: "value", value: "mail.smtp.socketFactory.class,javax.net.ssl.SSLSocketFactory,mail.smtp.socketFactory.port,mail.smtp.socketFactory.fallback")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 10)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "password")
            column(name: "name", value: "MAIL_CONFIG_PASSWORD")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 11)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "MAX LOGIN FAILURE")
            column(name: "name", value: "MAX_LOGIN_FAILURE")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 12)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "MAX LOGIN FAILURE PER_IP")
            column(name: "name", value: "MAX_LOGIN_FAILURE_PER_IP")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 13)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "ACCOUNT UNLOCK TIME")
            column(name: "name", value: "ACCOUNT_UNLOCK_TIME")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 14)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "APPLICATION URL")
            column(name: "name", value: "APPLICATION_URL")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 15)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "LINK ACTIVE TIME")
            column(name: "name", value: "LINK_ACTIVE_TIME")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 16)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "billing.period.days")
            column(name: "name", value: "billing.period.days")
            column(name: "value", value: "30")
        } 
        
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 17)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.name")
            column(name: "name", value: "organisation.name")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 18)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.email")
            column(name: "name", value: "organisation.email")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 19)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.legal.document")
            column(name: "name", value: "organisation.billing.legal.document")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 20)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.terms.conditions")
            column(name: "name", value: "organisation.billing.terms.conditions")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 21)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.terms.policy")
            column(name: "name", value: "organisation.billing.terms.policy")
            column(name: "value", value: "")
        } 
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 22)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.refund.service")
            column(name: "name", value: "organisation.billing.refund.service")
            column(name: "value", value: "")
        } 
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 23)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.phone.number1")
            column(name: "name", value: "organisation.billing.phone.number1")
            column(name: "value", value: "")
        } 
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 24)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.phone.number2")
            column(name: "name", value: "organisation.billing.phone.number2")
            column(name: "value", value: "")
        } 
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 25)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.phone.number3")
            column(name: "name", value: "organisation.billing.phone.number3")
            column(name: "value", value: "")
        } 
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 26)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.fax.number1")
            column(name: "name", value: "organisation.billing.fax.number1")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 27)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.fax.number2")
            column(name: "name", value: "organisation.billing.fax.number2")
            column(name: "value", value: "")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 28)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.fax.number3")
            column(name: "name", value: "organisation.billing.fax.number3")
            column(name: "value", value: "")
        }         
                
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 29)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "organisation.billing.logo")
            column(name: "name", value: "organisation.billing.logo")
            column(name: "value", value: "")
        }         
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 30)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "organisation.billing.signature")
            column(name: "description", value: "organisation.billing.signature")
            column(name: "value", value: "")
        } 

        insert(tableName: "config") {
            column(name: "id", valueNumeric: 31)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "signup.card.verification.enabled")
            column(name: "description", value: "signup card verification enabled des")
            column(name: "value", value: "false")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 32)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "signup.card.verification.value")
            column(name: "description", value: "signup card verification value des")
            column(name: "value", value: "1")
        }
        
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 33)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "signup.type.trial.enabled")
            column(name: "description", value: "signup trial enabled des")
            column(name: "value", value: "true")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 34)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "signup.type.trial.credit.limit")
            column(name: "description", value: "signup trial credit limit des")
            column(name: "value", value: "50")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 35)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "signup.type.retail.credit.limit")
            column(name: "description", value: "signup retail credit limit des")
            column(name: "value", value: "2000")
        } 
        
        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 1)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "BandWidth Cost")
            column(name: "name", value: "BandWidth Cost")
            column(name: "unit", value: "per GB")
        } 
        
        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 2)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "IP Cost")
            column(name: "name", value: "IP Cost")
            column(name: "unit", value: "per IP")
        } 
        
        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 3)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "SnapShot Cost")
            column(name: "name", value: "Snapshot Cost")
            column(name: "unit", value: "per GB")
        }
    }
}