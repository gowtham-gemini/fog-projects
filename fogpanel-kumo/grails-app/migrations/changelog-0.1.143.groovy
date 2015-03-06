databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1400500390392-1") {
		createTable(tableName: "app_version") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "app_versionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "checksum", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "fog_version", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "initialize_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1400500390392-132") {
		createIndex(indexName: "fog_version_uniq_1400500389179", tableName: "app_version", unique: "true") {
			column(name: "fog_version")
		}
	}
        
        changeSet(author: "gowtham (generated)", id: "liceseConfig-33") {

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 83)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "fog.instanceId")
                column(name: "description", value: "fog instance id")
                column(name: "value", value: "")
            }

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 84)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "licensee.email")
                column(name: "description", value: "to whom a license is granted or issued.")
                column(name: "value", value: "")
            }

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 85)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "update.checksum")
                column(name: "description", value: "checksum value")
                column(name: "value", value: "")
            }
    }
    
    
    }
