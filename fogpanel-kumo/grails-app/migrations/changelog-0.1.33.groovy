databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1377329407776-1") {
		createTable(tableName: "cloud_maintenance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cloud_maintenPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(5000)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1377329407776-2") {
		createTable(tableName: "cloud_maintenance_zones") {
			column(name: "cloud_maintenance_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
        
        changeSet(author: "gowtham (generated)", id: "defaultCurrency-1") {
            
             insert(tableName: "config") {
                column(name: "id", valueNumeric: 72)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "billing.default.currency")
                column(name: "description", value: "description")
                column(name: "value", value: "")
            }
        }

	changeSet(author: "gowtham (generated)", id: "1377329407776-8") {
		addPrimaryKey(columnNames: "cloud_maintenance_id, zone_id", tableName: "cloud_maintenance_zones")
	}

	changeSet(author: "gowtham (generated)", id: "1377329407776-9") {
		addForeignKeyConstraint(baseColumnNames: "cloud_maintenance_id", baseTableName: "cloud_maintenance_zones", constraintName: "FKD65E529189F7EF40", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cloud_maintenance", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1377329407776-10") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "cloud_maintenance_zones", constraintName: "FKD65E5291A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
	
	changeSet(author: "gowtham (generated)", id: "1377329407776-12") {
		createIndex(indexName: "FKD65E529189F7EF40", tableName: "cloud_maintenance_zones") {
			column(name: "cloud_maintenance_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1377329407776-13") {
		createIndex(indexName: "FKD65E5291A2BF084B", tableName: "cloud_maintenance_zones") {
			column(name: "zone_id")
		}
	}
}
