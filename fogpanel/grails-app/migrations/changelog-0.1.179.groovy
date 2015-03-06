databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1407824734697-1") {
		createTable(tableName: "vpn_users") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vpn_usersPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "vpn_user_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1407824734697-3") {
		createIndex(indexName: "FK_r1r55dp3lyucpxax1as72lxti", tableName: "vpn_users") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1407824734697-4") {
		createIndex(indexName: "vpn_user_name_uniq_1407824734082", tableName: "vpn_users", unique: "true") {
			column(name: "vpn_user_name")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1407824734697-2") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "vpn_users", constraintName: "FK_r1r55dp3lyucpxax1as72lxti", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}
}
