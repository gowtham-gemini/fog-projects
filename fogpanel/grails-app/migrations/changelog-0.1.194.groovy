databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1410939715740-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "protocol", tableName: "firewall_rules")
	}

	changeSet(author: "gowtham (generated)", id: "1410939715740-2") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "useripaddress_id", tableName: "firewall_rules")
	}

	changeSet(author: "gowtham (generated)", id: "1410939715740-3") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "useripaddress_id", tableName: "load_balancer")
	}
}
