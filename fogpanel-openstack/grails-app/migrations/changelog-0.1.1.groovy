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
            column(name: "cloud_password", value: "admin")  
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
            column(name: "value", value: "5")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 12)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "MAX LOGIN FAILURE PER_IP")
            column(name: "name", value: "MAX_LOGIN_FAILURE_PER_IP")
            column(name: "value", value: "5")
        } 
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 13)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "ACCOUNT UNLOCK TIME")
            column(name: "name", value: "ACCOUNT_UNLOCK_TIME")
            column(name: "value", value: "5")
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
            column(name: "value", value: "10")
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
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 36)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "late.fee.minimum.amount")
                column(name: "description", value: "Late fee minimum amount")
                column(name: "value", value: "100")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 37)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "late.fee.percendage")
                column(name: "description", value: "Late fee Percentage")
                column(name: "value", value: "10")
            } 
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 38)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.due.days")
                column(name: "description", value: "payment due days description")
                column(name: "value", value: "5")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 39)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "billing.cycle.fixed.enabled")
                column(name: "description", value: "billing cycle fixed enabled description")
                column(name: "value", value: "false")
            } 
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 40)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "billing.period.fixed.day")
                column(name: "description", value: "fixed day description")
                column(name: "value", value: "28")
            } 
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 41)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address")
                column(name: "description", value: "organisation address description")
                column(name: "value", value: "")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 42)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address.city")
                column(name: "description", value: "organisation city description")
                column(name: "value", value: "")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 43)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address.state")
                column(name: "description", value: "organisation state description")
                column(name: "value", value: "")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 44)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address.counrty")
                column(name: "description", value: "organisation country description")
                column(name: "value", value: "")
            }  
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 45)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address.zip")
                column(name: "description", value: "organisation zip description")
                column(name: "value", value: "")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 46)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.ConnectionTimeOut")
                column(name: "description", value: "payment.gateway.paypal.http.ConnectionTimeOut")
                column(name: "value", value: "5000")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 47)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.Retry")
                column(name: "description", value: "payment.gateway.paypal.http.Retry")
                column(name: "value", value: "1")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 48)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.ReadTimeOut")
                column(name: "description", value: "payment.gateway.paypal.http.ReadTimeOut")
                column(name: "value", value: "30000")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 49)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.MaxConnection")
                column(name: "description", value: "payment.gateway.paypal.http.MaxConnection")
                column(name: "value", value: "100")
            }  
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 50)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.ProxyPort")
                column(name: "description", value: "payment.gateway.paypal.http.ProxyPort")
                column(name: "value", value: "8080")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 51)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.ProxyHost")
                column(name: "description", value: "payment.gateway.paypal.http.ProxyHost")
                column(name: "value", value: "127.0.0.1")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 52)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.ProxyUserName")
                column(name: "description", value: "payment.gateway.paypal.http.ProxyUserName")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 53)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.UseProxy")
                column(name: "description", value: "payment.gateway.paypal.http.UseProxy")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 54)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.ProxyPassword")
                column(name: "description", value: "payment.gateway.paypal.http.ProxyPassword")
                column(name: "value", value: "")
            }
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 55)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.http.GoogleAppEngine")
                column(name: "description", value: "payment.gateway.paypal.http.GoogleAppEngine")
                column(name: "value", value: "false")
            }
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 56)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.service.EndPoint")
                column(name: "description", value: "payment.gateway.paypal.service.EndPoint")
                column(name: "value", value: "https://api.sandbox.paypal.com")
            }
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 57)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.clientID")
                column(name: "description", value: "payment.gateway.paypal.clientID")
                column(name: "value", value: "")
            }
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 58)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.clientSecret")
                column(name: "description", value: "payment.gateway.paypal.clientSecret")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 59)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.processing.fee.percentage")
                column(name: "description", value: "description")
                column(name: "value", value: "2")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 60)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.processing.fee.amount")
                column(name: "description", value: "description")
                column(name: "value", value: "10")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 61)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.processing.fee.type")
                column(name: "description", value: "description")
                column(name: "value", value: "INCLUDE")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 62)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "late.fee.applicable.amount")
                column(name: "description", value: "description")
                column(name: "value", value: "100")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 63)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "credit.limit.notification.level1")
                column(name: "description", value: "description")
                column(name: "value", value: "50")
            }

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 64)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "credit.limit.notification.level2")
                column(name: "description", value: "description")
                column(name: "value", value: "80")
            }

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 65)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "credit.limit.notification.level3")
                column(name: "description", value: "description")
                column(name: "value", value: "90")
            }
        
             insert(tableName: "config") {
                column(name: "id", valueNumeric: 66)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.authorizenet.apiKey")
                column(name: "description", value: "description")
                column(name: "value", value: "")
            }
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 67)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.authorizenet.apiSecret")
                column(name: "description", value: "description")
                column(name: "value", value: "")
            }

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 68)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.authorizenet.environment")
                column(name: "description", value: "description")
                column(name: "value", value: "SANDBOX")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 69)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway")
                column(name: "description", value: "description")
                column(name: "value", value: "Paypal ")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 70)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "instance.minimum.cost.applicable.hour")
                column(name: "description", value: "description")
                column(name: "value", value: "10")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 71)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "cloud.stack.server.up.date")
                column(name: "description", value: "description")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 72)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "billing.default.currency")
                column(name: "description", value: "description")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                    column(name: "id", valueNumeric: 73)
                    column(name: "version", valueNumeric: 1)
                    column(name: "description", value: "description")
                    column(name: "name", value: "organisation.address.extension")
                    column(name: "value", value: "")
            } 
                
            insert(tableName: "config") {
                    column(name: "id", valueNumeric: 74)
                    column(name: "version", valueNumeric: 1)
                    column(name: "description", value: "single sign on url")
                    column(name: "name", value: "single.sign.on.url")
                    column(name: "value", value: "")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 75)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "instance limit for trial account")
                column(name: "name", value: "instance.limit")
                column(name: "value", value: "2")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 76)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "storage limit for trial account")
                column(name: "name", value: "storage.limit")
                column(name: "value", value: "2")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 77)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "snapshot limit for trial account")
                column(name: "name", value: "snapshot.limit")
                column(name: "value", value: "2")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 78)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "bandwidth limit for trial account")
                column(name: "name", value: "bandwidth.limit")
                column(name: "value", value: "5")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 79)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "creditcard.processing")
                column(name: "description", value: "credit card processing for panel")
                column(name: "value", value: "false")
            }   
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 80)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "creditcard.processing.in.createvm")
                column(name: "description", value: "credit card processing on create vm ")
                column(name: "value", value: "false")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 81)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "monthly.billing.enabled")
                column(name: "description", value: "Enable monthly billing")
                column(name: "value", value: "false")
            }   
            
            insert(tableName: "config") {
                    column(name: "id", valueNumeric: 82)
                    column(name: "version", valueNumeric: 1)
                    column(name: "description", value: "Default language")
                    column(name: "name", value: "default.language")
                    column(name: "value", value: "en")
            }
            
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 83)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "fog.instanceId")
                column(name: "description", value: "fog instance id")
                column(name: "value", value: "")
            }

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 84)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "licensee.email")
                column(name: "description", value: "to whom a license is granted or issued.")
                column(name: "value", value: "")
            }

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 85)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "update.checksum")
                column(name: "description", value: "checksum value")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 86)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "organisation.background.img.url")
                column(name: "name", value: "organisation.background.img.url")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 87)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "Merchant Id")
                column(name: "name", value: "payment.gateway.ccavenue.merchant")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 88)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "Working Key")
                column(name: "name", value: "payment.gateway.ccavenue.working.key")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 91)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "Sign up setting")
                column(name: "name", value: "signup.setting")
                column(name: "value", value: "PUBLIC")
            }
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 6)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "common.billableItem.discount")
                column(name: "reference_item_name", value: "discount ")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }

            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 7)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "common.billableItem.lateFee")
                column(name: "reference_item_name", value: "lateFee ")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }

            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 8)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "common.billableItem.custom")
                column(name: "reference_item_name", value: "custom ")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 1)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
        
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 9)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "common.billableItem.recurringItem")
                column(name: "reference_item_name", value: "RecurringItem")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
              
            insert(tableName: "payment_gateways") {
                column(name: "id", valueNumeric: 1)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "Paypal Payment Gateway")
                column(name: "gateway_name", value: "PayPal")
                column(name: "status", value: "ENABLE")
                column(name: "is_default", valueNumeric: 1)
                column(name: "gateway_type", value: "ADVANCED")
                column(name: "gatewayurl", value: "/admin/settings/paypal")
            }

            insert(tableName: "payment_gateways") {
                column(name: "id", valueNumeric: 2)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "CCAvenue Payment Gateway")
                column(name: "gateway_name", value: "CCAvenue")
                column(name: "status", value: "DISABLE")
                column(name: "gateway_type", value: "SEAMLESS")
                column(name: "is_default", valueNumeric: 0)
                column(name: "gatewayurl", value: "/admin/settings/ccAvenue")
            }
            
            insert(tableName: "payment_gateways") {
                column(name: "id", valueNumeric: 3)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "BrainTree Payment Gateway")
                column(name: "gateway_name", value: "BrainTree")
                column(name: "status", value: "DISABLE")
                column(name: "gateway_type", value: "ADVANCED")
                column(name: "include_exclude", value: "EXCLUDE")
                column(name: "is_default", valueNumeric: 0)
                column(name: "processing_fee_amount", valueNumeric: 10)
                column(name: "processing_fee_percent", valueNumeric: 2)
            }

            insert(tableName: "payment_gateways") {
                column(name: "id", valueNumeric: 4)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "MoneyBooker Payment Gateway")
                column(name: "gateway_name", value: "MoneyBooker")
                column(name: "status", value: "DISABLE")
                column(name: "gateway_type", value: "SEAMLESS")
                column(name: "include_exclude", value: "EXCLUDE")
                column(name: "is_default", valueNumeric: 0)
                column(name: "processing_fee_amount", valueNumeric: 10)
                column(name: "processing_fee_percent", valueNumeric: 2)
            }
            insert(tableName: "payment_gateways") {
                column(name: "id", valueNumeric: 5)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "NAB Payment Gateway")
                column(name: "gateway_name", value: "NAB")
                column(name: "status", value: "DISABLE")
                column(name: "gateway_type", value: "SEAMLESS")
                column(name: "include_exclude", value: "EXCLUDE")
                column(name: "is_default", valueNumeric: 0)
                column(name: "processing_fee_amount", valueNumeric: 10)
                column(name: "processing_fee_percent", valueNumeric: 2)
            }
        
    }
    
    changeSet(author: "gowtham", id: "country-sql-file") {
        sqlFile( path: "country.sql")           
    }

    changeSet(author: "gowtham", id: "state-sql-file") {
        sqlFile( path: "state.sql")
    }
    
}