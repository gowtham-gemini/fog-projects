databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1377749294660-1") {
		addColumn(tableName: "template") {
			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
	
}
