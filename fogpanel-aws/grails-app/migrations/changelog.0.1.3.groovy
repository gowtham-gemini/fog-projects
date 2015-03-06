databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1423907585351-1") {
		addColumn(tableName: "region") {
			column(name: "end_point", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
