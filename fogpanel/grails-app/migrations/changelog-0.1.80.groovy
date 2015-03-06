databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1384320882471-1") {
		addColumn(tableName: "support_department") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
