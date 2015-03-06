databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1420176744666-1") {
		addColumn(tableName: "images") {
			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1420176744666-2") {
		addColumn(tableName: "network") {
			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1420176744666-3") {
		addColumn(tableName: "virtual_machine") {
			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1420176744666-4") {
		addColumn(tableName: "volume") {
			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1420176744666-5") {
		addColumn(tableName: "volume_snapshot") {
			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}	
	
}
