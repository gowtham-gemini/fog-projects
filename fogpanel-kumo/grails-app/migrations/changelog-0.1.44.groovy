databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "configDataType-1") {
		modifyDataType(columnName: "value", newDataType: "longtext", tableName: "config")
	}
}
