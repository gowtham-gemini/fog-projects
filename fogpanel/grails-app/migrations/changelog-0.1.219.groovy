databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1416898353607-1") {
		dropForeignKeyConstraint(baseTableName: "miscellaneous_offer_zone_cost",  constraintName: "FK6CE9600C5F412BC9")
	}

	changeSet(author: "Nandhini (generated)", id: "1416898353607-2") {
		dropForeignKeyConstraint(baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600C784BC369")
	}

	changeSet(author: "Nandhini (generated)", id: "1416898353607-3") {
		dropColumn(columnName: "cluster_id", tableName: "miscellaneous_offer_zone_cost")
	}

	changeSet(author: "Nandhini (generated)", id: "1416898353607-4") {
		dropColumn(columnName: "pod_id", tableName: "miscellaneous_offer_zone_cost")
	}
}
