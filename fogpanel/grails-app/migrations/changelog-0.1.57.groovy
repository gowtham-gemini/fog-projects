databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "usernamechange-4") {
		modifyDataType(columnName: "user_name", newDataType: "varchar(50)", tableName: "account")
	}
}
