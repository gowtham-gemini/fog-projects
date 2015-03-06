databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1414762787905-1") {
		addColumn(tableName: "volume_snapshot") {
			column(name: "created_at", type: "datetime")
		}
	}
        
        changeSet(author: "Abdul (generated)", id: "1418216895026-12") {
		dropColumn(columnName: "cteated_at", tableName: "volume_snapshot")
	}

}
