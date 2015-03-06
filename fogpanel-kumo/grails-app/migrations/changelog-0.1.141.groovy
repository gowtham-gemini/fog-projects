databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1401353725658-1") {
		addColumn(tableName: "vmsnapshot") {
			column(name: "parent_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
    }
