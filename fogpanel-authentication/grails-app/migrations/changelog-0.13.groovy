databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1425127321491-11") {
		dropColumn(columnName: "domain_name", tableName: "project")
	}

	changeSet(author: "developer (generated)", id: "1425127321491-12") {
		dropColumn(columnName: "engine_id", tableName: "project")
	}

	changeSet(author: "developer (generated)", id: "1425127321491-13") {
		dropColumn(columnName: "token", tableName: "project")
	}

	changeSet(author: "developer (generated)", id: "1425127321491-14") {
		dropColumn(columnName: "url", tableName: "project")
	}

//	changeSet(author: "developer (generated)", id: "1425127321491-15") {
//		dropTable(tableName: "group")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-1") {
//		addForeignKeyConstraint(baseColumnNames: "cloud_engine_id", baseTableName: "cloud_engine_projects", constraintName: "FK_ic4bxxtvtq6j2eqnkxinb86s7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cloud_engine", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-2") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "cloud_engine_projects", constraintName: "FK_pjfaepxwobym3fnmp962f1wr3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-3") {
//		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "ldap_group_user", constraintName: "FK_bmnf5jej77fob14xatmdvku84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-4") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ldap_group_user", constraintName: "FK_4a6e0xcnrtb3kw32hd9sdycbf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-5") {
//		addForeignKeyConstraint(baseColumnNames: "ldap_group_id", baseTableName: "ldap_group_users", constraintName: "FK_hnmpjhf65uhfyf1h3j0k5ytk2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-6") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ldap_group_users", constraintName: "FK_9ev7gont868yrd8c91x8be378", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-7") {
//		addForeignKeyConstraint(baseColumnNames: "ldap_group_id", baseTableName: "project_groups", constraintName: "FK_ne1g5yi6q0pwieouuwcjhnjlt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-8") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_groups", constraintName: "FK_5wvh0dj4iuo2ed4bdcetrbshx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-9") {
//		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "project_ldap_group", constraintName: "FK_fbn2t24i33ku78u9f2hidmucw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1425127321491-10") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_ldap_group", constraintName: "FK_e1bdmc0gcbxfdypw5mqk319w7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
}
