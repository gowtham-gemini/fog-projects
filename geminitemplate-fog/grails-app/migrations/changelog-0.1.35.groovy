databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1418712826600-1") {
		modifyDataType(columnName: "private_key", newDataType: "longtext", tableName: "sshkeys")
	}

	changeSet(author: "Abdul (generated)", id: "1418712826600-2") {
		dropNotNullConstraint(columnDataType: "longtext", columnName: "private_key", tableName: "sshkeys")
	}

}
