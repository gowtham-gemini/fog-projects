databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1407392232275-1") {
		createTable(tableName: "remote_access_vpn") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "remote_accessPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "ip_range", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ipsec_psk", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "local_ip", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "publicipaddress_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1407392232275-5") {
		createIndex(indexName: "FK_ecfphvryts2p6m7nyjrkhp5ii", tableName: "remote_access_vpn") {
			column(name: "publicipaddress_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1407392232275-6") {
		createIndex(indexName: "FK_im06qt81hevqcc0kochgxmy9i", tableName: "remote_access_vpn") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1407392232275-7") {
		createIndex(indexName: "FK_oahuomx13i89p30g77bkmis9l", tableName: "remote_access_vpn") {
			column(name: "network_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1407392232275-8") {
		createIndex(indexName: "reference_id_uniq_1407392231635", tableName: "remote_access_vpn", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1407392232275-2") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "remote_access_vpn", constraintName: "FK_im06qt81hevqcc0kochgxmy9i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1407392232275-3") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "remote_access_vpn", constraintName: "FK_oahuomx13i89p30g77bkmis9l", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1407392232275-4") {
		addForeignKeyConstraint(baseColumnNames: "publicipaddress_id", baseTableName: "remote_access_vpn", constraintName: "FK_ecfphvryts2p6m7nyjrkhp5ii", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "useripaddress", referencesUniqueColumn: "false")
	}
}
