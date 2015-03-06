databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424341499745-1") {
		addColumn(tableName: "flavors") {
			column(name: "is_ebs_storage", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
