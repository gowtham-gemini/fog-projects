databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1402324847185-1") {
		createTable(tableName: "product_release") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "releasePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "product_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "product_version", type: "varchar(255)") {
				constraints(nullable: "false")
			}
                        
                        column(name: "bundle_path", type: "varchar(255)")

			column(name: "vhd_path", type: "varchar(255)")
                        
                        column(name: "release_notes", type: "longtext") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402324847185-11") {
		createIndex(indexName: "FK41012807412B3B49", tableName: "product_release") {
			column(name: "product_id")
		}
	}
	
	changeSet(author: "gowtham (generated)", id: "1402324847185-7") {
		addForeignKeyConstraint(baseColumnNames: "product_id", baseTableName: "product_release", constraintName: "FK41012807412B3B49", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}
}
