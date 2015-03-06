databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1423317170402-1") {
		createTable(tableName: "container") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "containerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "access", type: "varchar(255)") {
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

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "object_count", type: "integer")

			column(name: "publicurl", type: "varchar(255)")

			column(name: "size", type: "double precision")

			column(name: "updated_at", type: "datetime")
		}
	}

	changeSet(author: "developer (generated)", id: "1423317170402-2") {
		createTable(tableName: "resource_limit") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "resource_limiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

			column(name: "cores", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "floating_ip", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "gigabytes", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "injected_file_content_bytes", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "injected_files", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "instances", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "metadata_items", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "network", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "port", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "ram", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "router", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "security_group_rules", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "security_groups", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "snapshots", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "subnet", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "updated_at", type: "datetime")

			column(name: "volumes", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1423317170402-3") {
		addColumn(tableName: "account") {
			column(name: "resource_limit_id", type: "bigint")
		}
	}

//	changeSet(author: "developer (generated)", id: "1423317170402-16") {
//		createIndex(indexName: "FK_ld4sqcxlxgyltsp5cfip0cgsq", tableName: "account") {
//			column(name: "resource_limit_id")
//		}
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-17") {
//		createIndex(indexName: "FK_oa5fmlr2hkuj8giiqugqrj3am", tableName: "container") {
//			column(name: "account_id")
//		}
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-18") {
//		createIndex(indexName: "unique_account_id", tableName: "container", unique: "true") {
//			column(name: "name")
//
//			column(name: "account_id")
//		}
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-19") {
//		createIndex(indexName: "FK_l6olnweay8xcmg19klyu5hnaa", tableName: "resource_limit") {
//			column(name: "account_id")
//		}
//	}

//	changeSet(author: "developer (generated)", id: "1423317170402-4") {
//		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FK_296wkqwal09diune025bhqmy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-5") {
//		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FK_e0e4fj7pb4j008n57dl563yg0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-6") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FK_5ta5bijgwndhkhsv98kv5i6js", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-7") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "account", constraintName: "FK_asmmv3yhdupfaev30onvqm5lg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-8") {
//		addForeignKeyConstraint(baseColumnNames: "resource_limit_id", baseTableName: "account", constraintName: "FK_ld4sqcxlxgyltsp5cfip0cgsq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "resource_limit", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-9") {
//		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FK_99vgsqatibaqbaar8onngjijq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-10") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "api_user", constraintName: "FK_6amqsmb5hmim0r67uoums68m4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-11") {
//		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK_c0mjsdvfmtqmy4sumao1nyuko", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-12") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "container", constraintName: "FK_oa5fmlr2hkuj8giiqugqrj3am", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-13") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "resource_limit", constraintName: "FK_l6olnweay8xcmg19klyu5hnaa", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-14") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "user", constraintName: "FK_p10kanw1ubd833ui091tkhw2d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1423317170402-15") {
//		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "volume_snapshot", constraintName: "FK_hqxwu7wnx0i17ow51mvpsqes", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
//	}
}
