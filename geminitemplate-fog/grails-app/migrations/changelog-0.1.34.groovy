databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1418640610428-1") {
		createTable(tableName: "volume_type_zone_cost") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "volume_type_zPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "volume_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1418640610428-2") {
		addColumn(tableName: "volume_type") {
			column(name: "is_disabled", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	

	changeSet(author: "Nandhini (generated)", id: "1418640610428-17") {
		createIndex(indexName: "FK_kvcyk25baaqh6kmbcvsmhs99e", tableName: "volume_type_zone_cost") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1418640610428-18") {
		createIndex(indexName: "FK_r7hhmnc0kaifo9vvq8g1o0uqx", tableName: "volume_type_zone_cost") {
			column(name: "volume_type_id")
		}
	}	

	changeSet(author: "Nandhini (generated)", id: "1418640610428-15") {
		addForeignKeyConstraint(baseColumnNames: "volume_type_id", baseTableName: "volume_type_zone_cost", constraintName: "FK_r7hhmnc0kaifo9vvq8g1o0uqx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1418640610428-16") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "volume_type_zone_cost", constraintName: "FK_kvcyk25baaqh6kmbcvsmhs99e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
