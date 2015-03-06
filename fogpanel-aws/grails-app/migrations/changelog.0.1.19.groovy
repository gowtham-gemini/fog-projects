databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424841650727-1") {
		addColumn(tableName: "security_group") {
			column(name: "region_id", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424841650727-2") {
		addColumn(tableName: "subnet") {
			column(name: "region_id", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}
}
