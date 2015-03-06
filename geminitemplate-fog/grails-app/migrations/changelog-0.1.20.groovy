databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1414758086405-1") {
		addColumn(tableName: "volume_snapshot") {
			column(name: "size", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}
}
