databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424495707636-1") {
		addColumn(tableName: "subnet") {
			column(name: "availability_zone", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
