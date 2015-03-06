databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1425376930386-1") {
		addColumn(tableName: "cloud_engine") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
