databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1374483457887-1") {
		addColumn(tableName: "discount") {
			column(name: "billing_cycles", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374483457887-2") {
		addColumn(tableName: "discount") {
			column(name: "is_all_plan", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374483457887-3") {
		addColumn(tableName: "discount") {
			column(name: "is_all_user", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374483457887-4") {
		addColumn(tableName: "discount") {
			column(name: "sub_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
