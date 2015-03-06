databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1411463966541-1") {
		createTable(tableName: "s2scustomer_gateway") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "s2scustomer_gPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cidr", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "dead_peer_detection", type: "bit")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "esp_encryption", type: "varchar(255)")

			column(name: "esp_hash", type: "varchar(255)")

			column(name: "esp_lifetime", type: "varchar(255)")

			column(name: "gateway", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ikedh", type: "varchar(255)")

			column(name: "ike_encryption", type: "varchar(255)")

			column(name: "ike_hash", type: "varchar(255)")

			column(name: "ike_lifetime", type: "varchar(255)")

			column(name: "ipsec_preshared_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_used", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "job_id", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "perfect_forward_secrecy", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "removed_date", type: "datetime")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411463966541-3") {
		createIndex(indexName: "FK_84lq6pjc1wd7hhr72uppou3ks", tableName: "s2scustomer_gateway") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411463966541-4") {
		createIndex(indexName: "reference_id_uniq_1411463965713", tableName: "s2scustomer_gateway", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411463966541-2") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "s2scustomer_gateway", constraintName: "FK_84lq6pjc1wd7hhr72uppou3ks", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}
}
