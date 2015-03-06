databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424861600535-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424861600535-2") {
		addColumn(tableName: "vpc") {
			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424861600535-3") {
		dropForeignKeyConstraint(baseTableName: "virtual_machine", baseTableSchemaName: "aws_fog", constraintName: "FK_ikfkj13rn6vlextyr7phtvjek")
	}

	changeSet(author: "az (generated)", id: "1424861600535-5") {
		dropIndex(indexName: "vpc_id_uniq_1424433606688", tableName: "vpc")
	}

	changeSet(author: "az (generated)", id: "1424861600535-6") {
		createIndex(indexName: "FK_s92l1kjcrwx9k3y7s4klbo8f8", tableName: "virtual_machine") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424861600535-7") {
		createIndex(indexName: "reference_id_uniq_1424861599773", tableName: "vpc", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424861600535-8") {
		dropColumn(columnName: "network_id", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424861600535-9") {
		dropColumn(columnName: "vpc_id", tableName: "vpc")
	}

	changeSet(author: "az (generated)", id: "1424861600535-4") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "virtual_machine", constraintName: "FK_s92l1kjcrwx9k3y7s4klbo8f8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}
}
