databaseChangeLog = {

	changeSet(author: "operations (generated)", id: "1407740698971-1") {
		addColumn(tableName: "product") {
			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
