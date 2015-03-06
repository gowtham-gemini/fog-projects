databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424952391131-1") {
		addColumn(tableName: "region") {
			column(name: "alias_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424952391131-3") {
		dropColumn(columnName: "region_id", tableName: "route_table")
	}

	changeSet(author: "az (generated)", id: "1424952391131-2") {
		addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "sshkeys", constraintName: "FK_cu4y8t1m5m0ye3k2hdu4n3qyt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
	}
}
