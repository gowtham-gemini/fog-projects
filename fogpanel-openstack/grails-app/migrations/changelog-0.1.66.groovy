databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1423117164069-1") {
		addColumn(tableName: "floating_ip") {
			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1423117164069-11") {
		dropIndex(indexName: "email_uniq_1412148545240", tableName: "invitation")
	}	
}
