databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1400764180649-1") {
		addColumn(tableName: "computing_offer") {
			column(name: "hypervisor_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
        
	changeSet(author: "gowtham (generated)", id: "1400764180649-17") {
		createIndex(indexName: "FK_b1tv76pvjwn1kxtd1sxip9ycg", tableName: "computing_offer") {
			column(name: "hypervisor_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1400764180649-11") {
		addForeignKeyConstraint(baseColumnNames: "hypervisor_id", baseTableName: "computing_offer", constraintName: "FK_b1tv76pvjwn1kxtd1sxip9ycg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "hypervisor", referencesUniqueColumn: "false")
	}
}
