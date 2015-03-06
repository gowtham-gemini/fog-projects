databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1380608076737-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "cluster_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
}
