databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1425126523870-1") {
		createTable(tableName: "cloud_engine_projects") {
			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cloud_engine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1425126523870-2") {
		addPrimaryKey(columnNames: "cloud_engine_id, project_id", tableName: "cloud_engine_projects")
	}

//	changeSet(author: "developer (generated)", id: "1425126523870-13") {
//		dropIndex(indexName: "FK_2pggwcirlkx8ijnkwarkces1d", tableName: "group")
//	}

	changeSet(author: "developer (generated)", id: "1425126523870-14") {
		dropIndex(indexName: "FK_84gxn8eum3t7b0tjln0iysspm", tableName: "project")
	}

	changeSet(author: "developer (generated)", id: "1425126523870-15") {
		createIndex(indexName: "FK_ic4bxxtvtq6j2eqnkxinb86s7", tableName: "cloud_engine_projects") {
			column(name: "cloud_engine_id")
		}
	}

	changeSet(author: "developer (generated)", id: "1425126523870-16") {
		createIndex(indexName: "FK_pjfaepxwobym3fnmp962f1wr3", tableName: "cloud_engine_projects") {
			column(name: "project_id")
		}
	}

	changeSet(author: "developer (generated)", id: "1425126523870-17") {
		dropColumn(columnName: "cloud_engine_id", tableName: "project")
	}

//	changeSet(author: "developer (generated)", id: "1425126523870-18") {
//		dropTable(tableName: "group")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-3") {
//		addForeignKeyConstraint(baseColumnNames: "cloud_engine_id", baseTableName: "cloud_engine_projects", constraintName: "FK_ic4bxxtvtq6j2eqnkxinb86s7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cloud_engine", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-4") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "cloud_engine_projects", constraintName: "FK_pjfaepxwobym3fnmp962f1wr3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-5") {
//		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "ldap_group_user", constraintName: "FK_bmnf5jej77fob14xatmdvku84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-6") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ldap_group_user", constraintName: "FK_4a6e0xcnrtb3kw32hd9sdycbf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-7") {
//		addForeignKeyConstraint(baseColumnNames: "ldap_group_id", baseTableName: "ldap_group_users", constraintName: "FK_hnmpjhf65uhfyf1h3j0k5ytk2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-8") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ldap_group_users", constraintName: "FK_9ev7gont868yrd8c91x8be378", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-9") {
//		addForeignKeyConstraint(baseColumnNames: "ldap_group_id", baseTableName: "project_groups", constraintName: "FK_ne1g5yi6q0pwieouuwcjhnjlt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-10") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_groups", constraintName: "FK_5wvh0dj4iuo2ed4bdcetrbshx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-11") {
//		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "project_ldap_group", constraintName: "FK_fbn2t24i33ku78u9f2hidmucw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425126523870-12") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_ldap_group", constraintName: "FK_e1bdmc0gcbxfdypw5mqk319w7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
}
