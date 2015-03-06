databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1414044100609-1") {
		addColumn(tableName: "sshkeys") {
			column(name: "publickey", type: "longtext") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414044100609-4") {
		dropColumn(columnName: "privatekey", tableName: "sshkeys")
	}
}
