databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424342609128-1") {
		modifyDataType(columnName: "memory", newDataType: "double precision", tableName: "flavors")
	}
}
