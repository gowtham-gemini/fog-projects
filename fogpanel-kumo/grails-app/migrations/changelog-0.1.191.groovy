databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1410417955568-1") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "vpc_id", tableName: "useripaddress")
	}

//	changeSet(author: "gowtham (generated)", id: "1410417955568-2") {
//		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "useripaddress", constraintName: "FK_lk720evr622xs34a40uj7hctx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
//	}
}
