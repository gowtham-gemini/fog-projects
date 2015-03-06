databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1397128273974-1") {
		addColumn(tableName: "template") {
			column(name: "minimum_cpu", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1397128273974-2") {
		addColumn(tableName: "template") {
			column(name: "minimum_ram", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1397128273974-3") {
		addColumn(tableName: "template") {
			column(name: "one_time_chargeable", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

}
