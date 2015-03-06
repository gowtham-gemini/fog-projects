databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1415768556464-1") {
		addColumn(tableName: "flavor_cost_info") {
			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}


	changeSet(author: "Abdul (generated)", id: "1415768556464-4") {
		dropForeignKeyConstraint(baseTableName: "flavor_cost_info", constraintName: "FK_od4u31dh41r3mfl1yjtpgbvq6")
	}

	changeSet(author: "Abdul (generated)", id: "1415768556464-13") {
		createIndex(indexName: "FK_lc0nvqnpx45bocg8xjf06mb0f", tableName: "flavor_cost_info") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1415768556464-14") {
		dropColumn(columnName: "region_id", tableName: "flavor_cost_info")
	}

	changeSet(author: "Abdul (generated)", id: "1415768556464-11") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "flavor_cost_info", constraintName: "FK_lc0nvqnpx45bocg8xjf06mb0f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

}
