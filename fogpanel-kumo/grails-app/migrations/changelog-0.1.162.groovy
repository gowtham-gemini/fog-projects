databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1406013742302-1") {
		addColumn(tableName: "nic_secondaryip") {
			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1406013742302-2") {
		modifyDataType(columnName: "value", newDataType: "longtext", tableName: "config")
	}

	changeSet(author: "gowtham (generated)", id: "1406013742302-3") {
		addNotNullConstraint(columnDataType: "longtext", columnName: "value", tableName: "config")
	}

	changeSet(author: "gowtham (generated)", id: "1406013742302-5") {
		createIndex(indexName: "FK_aog6txank4nq93hsmrm9fupug", tableName: "nic_secondaryip") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1406013742302-4") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "nic_secondaryip", constraintName: "FK_aog6txank4nq93hsmrm9fupug", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
