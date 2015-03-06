databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1401873438325-1") {
		createTable(tableName: "invoice") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invoicePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "currency", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "invoice_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "payment_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-2") {
		createTable(tableName: "invoice_item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invoice_itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "invoice_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "item_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "quantity", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "unit_price", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-3") {
		createTable(tableName: "product") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "productPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "base_product_id", type: "bigint")

			column(name: "description", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "license_quantity", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "price", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-4") {
		createTable(tableName: "product_instance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "product_instaPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "active_license", type: "bigint")

			column(name: "additional_license", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "auto_update_comment", type: "varchar(255)")

			column(name: "base_license", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "emergency_expiry_date", type: "datetime")

			column(name: "expiration_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "inception_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "initial_version_major", type: "tinyint")

			column(name: "initial_version_minor", type: "tinyint")

			column(name: "initial_version_patch", type: "tinyint")

			column(name: "instance_path", type: "varchar(255)")

			column(name: "last_updated_on", type: "bigint")

			column(name: "max_version_major", type: "tinyint")

			column(name: "max_version_minor", type: "tinyint")

			column(name: "max_version_patch", type: "tinyint")

			column(name: "name", type: "varchar(255)")

			column(name: "product_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "secret_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "time_zone", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-5") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-6") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "address1", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "address2", type: "varchar(255)")

			column(name: "city", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "company_name", type: "varchar(255)")

			column(name: "country", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)")

			column(name: "token_expiry_date", type: "datetime")

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zip", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-7") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-8") {
		createTable(tableName: "validation_log") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "validation_loPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "additional_data", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "checksum", type: "varchar(255)")

			column(name: "current_version", type: "varchar(255)")

			column(name: "deployed_date", type: "datetime")

			column(name: "email", type: "varchar(255)")

			column(name: "error_message", type: "varchar(255)")

			column(name: "generated_key", type: "varchar(255)")

			column(name: "hostip", type: "varchar(255)")

			column(name: "host_name", type: "varchar(255)")

			column(name: "initial_version", type: "varchar(255)")

			column(name: "last_updated_date", type: "datetime")

			column(name: "registration_date", type: "datetime")

			column(name: "requestid", type: "varchar(255)")

			column(name: "sockets", type: "bigint")

			column(name: "time_zone", type: "varchar(255)")

			column(name: "valid", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-9") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-17") {
		createIndex(indexName: "FK74D6432DB6FA544B", tableName: "invoice") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-18") {
		createIndex(indexName: "FKC7D1E4E5D1EBAA89", tableName: "invoice_item") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-19") {
		createIndex(indexName: "FKED8DCCEFAB03CD77", tableName: "product") {
			column(name: "base_product_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-20") {
		createIndex(indexName: "name_uniq_1401873438247", tableName: "product", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-21") {
		createIndex(indexName: "FKBE4415E5412B3B49", tableName: "product_instance") {
			column(name: "product_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-22") {
		createIndex(indexName: "FKBE4415E5B6FA544B", tableName: "product_instance") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-23") {
		createIndex(indexName: "authority_uniq_1401873438255", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-24") {
		createIndex(indexName: "username_uniq_1401873438260", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-25") {
		createIndex(indexName: "FK143BF46A11CF906B", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-26") {
		createIndex(indexName: "FK143BF46AB6FA544B", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-10") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "invoice", constraintName: "FK74D6432DB6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-11") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FKC7D1E4E5D1EBAA89", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-12") {
		addForeignKeyConstraint(baseColumnNames: "base_product_id", baseTableName: "product", constraintName: "FKED8DCCEFAB03CD77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-13") {
		addForeignKeyConstraint(baseColumnNames: "product_id", baseTableName: "product_instance", constraintName: "FKBE4415E5412B3B49", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-14") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "product_instance", constraintName: "FKBE4415E5B6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-15") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A11CF906B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1401873438325-16") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AB6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
