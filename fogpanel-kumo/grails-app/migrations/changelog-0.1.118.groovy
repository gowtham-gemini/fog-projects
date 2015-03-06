databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "payment-settings") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 81)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "monthly.billing.enabled")
                column(name: "description", value: "Enable monthly billing")
                column(name: "value", value: "false")
            }   
	}

        changeSet(author: "gowtham (generated)", id: "paymentsettings-update-1") {
		sqlFile( path: "volumeBillingTypeUpdate.sql")       
        }
    
	
}
