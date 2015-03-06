databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1410765694999-1") {
		addColumn(tableName: "useripaddress") {
			column(name: "isvpclbadded", type: "bit") 
		}
	}

	changeSet(author: "gowtham (generated)", id: "1410765694999-2") {
		addColumn(tableName: "useripaddress") {
			column(name: "isvpcpfadded", type: "bit") 
		}
	}

//	changeSet(author: "gowtham (generated)", id: "1410765694999-3") {
//		addNotNullConstraint(columnDataType: "bigint", columnName: "vpc_id", tableName: "virtual_machine")
//	}

//	changeSet(author: "gowtham (generated)", id: "1410765694999-4") {
//		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "useripaddress", constraintName: "FK_lk720evr622xs34a40uj7hctx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1410765694999-5") {
//		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "virtual_machine", constraintName: "FK_s92l1kjcrwx9k3y7s4klbo8f8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
//	}
}
