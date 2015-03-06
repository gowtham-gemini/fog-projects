databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1382593086206-1") {
		addColumn(tableName: "account") {
			column(name: "auto_payment_attempt", type: "integer") {
                            constraints(nullable: "true")
                        }
                        
		}
	}
}
