databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1411382071895-1") {
		createTable(tableName: "s2svpngateways") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "s2svpngatewayPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "useripaddress_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-8") {
		createIndex(indexName: "FK_5qxl8mwvmu5b6fb4oq0ssjje5", tableName: "s2svpngateways") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-9") {
		createIndex(indexName: "FK_ahh7yvd41d6wkfd98shfjnhqp", tableName: "s2svpngateways") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-10") {
		createIndex(indexName: "FK_qgi9kp00sqc9uvly6msh6c7rx", tableName: "s2svpngateways") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-11") {
		createIndex(indexName: "FK_sl6pifad37u37dnp7sfxs23lc", tableName: "s2svpngateways") {
			column(name: "useripaddress_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-12") {
		createIndex(indexName: "reference_id_uniq_1411382070838", tableName: "s2svpngateways", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-2") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "s2svpngateways", constraintName: "FK_5qxl8mwvmu5b6fb4oq0ssjje5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-3") {
		addForeignKeyConstraint(baseColumnNames: "useripaddress_id", baseTableName: "s2svpngateways", constraintName: "FK_sl6pifad37u37dnp7sfxs23lc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "useripaddress", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-4") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "s2svpngateways", constraintName: "FK_qgi9kp00sqc9uvly6msh6c7rx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-5") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "s2svpngateways", constraintName: "FK_ahh7yvd41d6wkfd98shfjnhqp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-6") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "useripaddress", constraintName: "FK_lk720evr622xs34a40uj7hctx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411382071895-7") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "virtual_machine", constraintName: "FK_s92l1kjcrwx9k3y7s4klbo8f8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}
}
