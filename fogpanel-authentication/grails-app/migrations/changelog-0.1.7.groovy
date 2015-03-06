databaseChangeLog = {
        
	changeSet(author: "developer (generated)", id: "1424938952725-1") {
		createTable(tableName: "group") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "groupPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "member_dn", type: "tinyblob") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-2") {
		createTable(tableName: "group_users") {
			column(name: "group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-3") {
		createTable(tableName: "project") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "projectPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "group_dn", type: "tinyblob") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-4") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "username", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-5") {
		addPrimaryKey(columnNames: "group_id, user_id", tableName: "group_users")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-6") {
		dropForeignKeyConstraint(baseTableName: "user",  constraintName: "FK_nrrhhb0bsexvi8ch6wnon9uog")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-10") {
		dropIndex(indexName: "secret_key_uniq_1423646573269", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-11") {
		createIndex(indexName: "FK_2pggwcirlkx8ijnkwarkces1d", tableName: "group") {
			column(name: "project_id")
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-12") {
		createIndex(indexName: "name_uniq_1424938952231", tableName: "group", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-13") {
		createIndex(indexName: "FK_ponf6el8r8qxoe212w6lxkej5", tableName: "group_users") {
			column(name: "user_id")
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-14") {
		createIndex(indexName: "FK_q2r2qpupn1nesrnj23elvnaqf", tableName: "group_users") {
			column(name: "group_id")
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-15") {
		createIndex(indexName: "name_uniq_1424938952251", tableName: "project", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-16") {
		createIndex(indexName: "username_uniq_1424938952265", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "developer (generated)", id: "1424938952725-17") {
		dropColumn(columnName: "account_id", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-18") {
		dropColumn(columnName: "currency_format", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-19") {
		dropColumn(columnName: "date_format", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-20") {
		dropColumn(columnName: "failure_count", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-21") {
		dropColumn(columnName: "lock_time", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-22") {
		dropColumn(columnName: "password", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-23") {
		dropColumn(columnName: "secret_key", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-24") {
		dropColumn(columnName: "token", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-25") {
		dropColumn(columnName: "token_expiry_date", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-7") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "group", constraintName: "FK_2pggwcirlkx8ijnkwarkces1d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-8") {
		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "group_users", constraintName: "FK_q2r2qpupn1nesrnj23elvnaqf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "group", referencesUniqueColumn: "false")
	}

	changeSet(author: "developer (generated)", id: "1424938952725-9") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "group_users", constraintName: "FK_ponf6el8r8qxoe212w6lxkej5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
