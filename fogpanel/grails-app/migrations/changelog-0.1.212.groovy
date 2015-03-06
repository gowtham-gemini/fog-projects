databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1416291253069-1") {
		addColumn(tableName: "computing_offer") {
			column(name: "is_public", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1416291253069-2") {
		dropForeignKeyConstraint(baseTableName: "computing_offer", constraintName: "FKBC30B9915F412BC9")
	}

	changeSet(author: "gowtham (generated)", id: "1416291253069-3") {
		dropForeignKeyConstraint(baseTableName: "computing_offer", constraintName: "FKBC30B991784BC369")
	}

	changeSet(author: "gowtham (generated)", id: "1416291253069-4") {
		dropForeignKeyConstraint(baseTableName: "computing_offer", constraintName: "FKBC30B991A2BF084B")
	}

	changeSet(author: "gowtham (generated)", id: "1416291253069-5") {
		dropColumn(columnName: "base_os", tableName: "computing_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1416291253069-6") {
		dropColumn(columnName: "cluster_id", tableName: "computing_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1416291253069-7") {
		dropColumn(columnName: "pod_id", tableName: "computing_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1416291253069-8") {
		dropColumn(columnName: "zone_id", tableName: "computing_offer")
	}
}
