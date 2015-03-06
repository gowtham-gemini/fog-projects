databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1407412845455-1") {
		addColumn(tableName: "load_balancer") {
			column(name: "netwotk_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407412845455-2") {
		addColumn(tableName: "load_balancer") {
			column(name: "useripaddress_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407412845455-5") {
		createIndex(indexName: "FK_d8lam3198v0mo3js18o9b6shu", tableName: "load_balancer") {
			column(name: "netwotk_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407412845455-6") {
		createIndex(indexName: "FK_hwjfj4ebt4qhrs5x1nwgnvtgw", tableName: "load_balancer") {
			column(name: "useripaddress_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407412845455-3") {
		addForeignKeyConstraint(baseColumnNames: "netwotk_id", baseTableName: "load_balancer", constraintName: "FK_d8lam3198v0mo3js18o9b6shu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407412845455-4") {
		addForeignKeyConstraint(baseColumnNames: "useripaddress_id", baseTableName: "load_balancer", constraintName: "FK_hwjfj4ebt4qhrs5x1nwgnvtgw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "useripaddress", referencesUniqueColumn: "false")
	}
}
