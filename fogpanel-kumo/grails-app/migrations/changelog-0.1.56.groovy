databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1382096097969-1") {
		addColumn(tableName: "account") {
			column(name: "uuid", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
