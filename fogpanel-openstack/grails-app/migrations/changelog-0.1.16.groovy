databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1414126142569-1") {
		addColumn(tableName: "images") {
			column(name: "account_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414126142569-2") {
		addColumn(tableName: "images") {
			column(name: "user_id", type: "bigint")
		}
	}

        changeSet(author: "gowtham (generated)", id: "1414126142569-9") {
		createIndex(indexName: "FK_a7njc8t40e0k9u77qm293y5uf", tableName: "images") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414126142569-10") {
		createIndex(indexName: "FK_gn0kkmw9cx9tbd2bwc6xxbqr7", tableName: "images") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414126142569-11") {
		createIndex(indexName: "FK_s0yc5n7522hacknlrw8md1788", tableName: "images") {
			column(name: "virtual_machine_id")
		}
	}

    
	changeSet(author: "gowtham (generated)", id: "1414126142569-5") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "images", constraintName: "FK_a7njc8t40e0k9u77qm293y5uf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1414126142569-6") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "images", constraintName: "FK_gn0kkmw9cx9tbd2bwc6xxbqr7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1414126142569-7") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "images", constraintName: "FK_s0yc5n7522hacknlrw8md1788", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

    }
