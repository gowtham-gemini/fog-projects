databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1424244372096-2") {
		dropIndex(indexName: "email_uniq_1409033877417", tableName: "invitation")
	}

	changeSet(author: "Nandhini (generated)", id: "1424244372096-3") {
		createIndex(indexName: "name_uniq_1424244371215", tableName: "invitation", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1424244372096-1") {
		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "user", constraintName: "FK_p10kanw1ubd833ui091tkhw2d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
	}
}
