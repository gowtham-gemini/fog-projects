databaseChangeLog = {
	    
        changeSet(author: "gowtham (generated)", id: "paypalconfig-78") {
		        
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
                column(name: "value", value: "false")
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
                column(name: "value", value: "EBWKjlELKMYqRNQ6sYvFo64FtaRLRR5BdHEESmha49TM")
            }
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 58)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.paypal.clientSecret")
                column(name: "description", value: "payment.gateway.paypal.clientSecret")
                column(name: "value", value: "EO422dn3gQLgDbuwqTjzrFgFtaRLRR5BdHEESmha49TM")
            }
        
        
        
	}
}
