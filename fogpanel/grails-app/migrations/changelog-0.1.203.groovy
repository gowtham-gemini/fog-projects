databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1415186042782-1") {
		addColumn(tableName: "network") {
			column(name: "network_domain", type: "varchar(255)")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1415186042782-2") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "network_domain", tableName: "vpc")
	}

	changeSet(author: "Nandhini (generated)", id: "1415186042782-3") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "network", constraintName: "FK_cxghlrl9wt67ncuyrb67ylsfy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}
}
