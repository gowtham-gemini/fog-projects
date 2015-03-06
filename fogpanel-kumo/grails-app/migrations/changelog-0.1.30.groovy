databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1376983104927-1") {
		addColumn(tableName: "template") {
			column(name: "account_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1376983104927-2") {
		addColumn(tableName: "template") {
			column(name: "user_id", type: "bigint")
		}
	}
        
        changeSet(author: "gowtham (generated)", id: "changeTempcost-1") {
		dropColumn(columnName: "cost", tableName: "template")
	}
    
        changeSet(author: "gowtham (generated)", id: "changeTempcost-2") {
                addColumn(tableName: "template") {
                    column(name: "cost", type: "double precision")
                        constraints(nullable: "false")
                }
        }

	changeSet(author: "gowtham (generated)", id: "1376983104927-8") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "template", constraintName: "FKB13ACC7AA6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1376983104927-9") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "template", constraintName: "FKB13ACC7AA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1376983104927-44") {
		createIndex(indexName: "FKB13ACC7AA0981FEB", tableName: "template") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1376983104927-45") {
		createIndex(indexName: "FKB13ACC7AA6E55AE9", tableName: "template") {
			column(name: "account_id")
		}
	}
}
