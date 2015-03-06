databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1425104350388-1") {
		createTable(tableName: "cloud_engine") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cloud_enginePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "engine_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1425104350388-2") {
		addColumn(tableName: "project") {
			column(name: "cloud_engine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

        changeSet(author: "developer (generated)", id: "1425104350388-13") {
		dropIndex(indexName: "name_uniq_1424938952231", tableName: "group")
	}

	changeSet(author: "developer (generated)", id: "1425104350388-14") {
		createIndex(indexName: "type_uniq_1425104349397", tableName: "cloud_engine", unique: "true") {
			column(name: "type")
		}
	}

	changeSet(author: "developer (generated)", id: "1425104350388-15") {
		createIndex(indexName: "FK_84gxn8eum3t7b0tjln0iysspm", tableName: "project") {
			column(name: "cloud_engine_id")
		}
	}

//	changeSet(author: "developer (generated)", id: "1425104350388-16") {
//		dropTable(tableName: "group")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-4") {
//		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "ldap_group_user", constraintName: "FK_bmnf5jej77fob14xatmdvku84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-5") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ldap_group_user", constraintName: "FK_4a6e0xcnrtb3kw32hd9sdycbf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-6") {
//		addForeignKeyConstraint(baseColumnNames: "ldap_group_id", baseTableName: "ldap_group_users", constraintName: "FK_hnmpjhf65uhfyf1h3j0k5ytk2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-7") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ldap_group_users", constraintName: "FK_9ev7gont868yrd8c91x8be378", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-8") {
//		addForeignKeyConstraint(baseColumnNames: "cloud_engine_id", baseTableName: "project", constraintName: "FK_84gxn8eum3t7b0tjln0iysspm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cloud_engine", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-9") {
//		addForeignKeyConstraint(baseColumnNames: "ldap_group_id", baseTableName: "project_groups", constraintName: "FK_ne1g5yi6q0pwieouuwcjhnjlt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-10") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_groups", constraintName: "FK_5wvh0dj4iuo2ed4bdcetrbshx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-11") {
//		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "project_ldap_group", constraintName: "FK_fbn2t24i33ku78u9f2hidmucw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425104350388-12") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_ldap_group", constraintName: "FK_e1bdmc0gcbxfdypw5mqk319w7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
}
