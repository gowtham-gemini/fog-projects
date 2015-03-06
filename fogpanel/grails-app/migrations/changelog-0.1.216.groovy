databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1416483371062-1") {
		dropForeignKeyConstraint(baseTableName: "disk_offer", constraintName: "FKAB8C213A5F412BC9")
	}

	changeSet(author: "gowtham (generated)", id: "1416483371062-2") {
		dropForeignKeyConstraint(baseTableName: "disk_offer", constraintName: "FKAB8C213A784BC369")
	}

	changeSet(author: "gowtham (generated)", id: "1416483371062-3") {
		dropForeignKeyConstraint(baseTableName: "disk_offer", constraintName: "FKAB8C213AA2BF084B")
	}

	changeSet(author: "gowtham (generated)", id: "1416483371062-4") {
		dropColumn(columnName: "cluster_id", tableName: "disk_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1416483371062-5") {
		dropColumn(columnName: "pod_id", tableName: "disk_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1416483371062-6") {
		dropColumn(columnName: "zone_id", tableName: "disk_offer")
	}
}
