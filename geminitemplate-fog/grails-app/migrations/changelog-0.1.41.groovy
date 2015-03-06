databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1418969374776-1") {
		addColumn(tableName: "images") {
			column(name: "one_time_chargeable", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}	
	
}
