databaseChangeLog = {

	changeSet(author: "Gowtham (generated)", id: "chnage-config-value-datatype") {
		modifyDataType(columnName: "value", newDataType: "longtext", tableName: "config")
	}
}
