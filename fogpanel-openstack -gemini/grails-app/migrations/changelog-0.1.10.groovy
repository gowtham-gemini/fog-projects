databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1413871264316-1") {
		createTable(tableName: "sshkeys") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sshkeysPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "fingerprint", type: "varchar(5000)") {
				constraints(nullable: "false")
			}

			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "privatekey", type: "varchar(5000)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413871264316-33") {
		createIndex(indexName: "FK_7c2bki05yhorcxv1inwqid1u6", tableName: "sshkeys") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413871264316-34") {
		createIndex(indexName: "name_uniq_1413871263580", tableName: "sshkeys", unique: "true") {
			column(name: "name")
		}
	}

}
