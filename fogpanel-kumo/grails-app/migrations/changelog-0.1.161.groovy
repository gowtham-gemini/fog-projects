databaseChangeLog = {
        
        changeSet(author: "lakshmi (generated)", id: "1405744646136-1") {
		createTable(tableName: "payment_gateways") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "payment_gatewPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "gateway_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1405744646136-2") {
		modifyDataType(columnName: "value", newDataType: "longtext", tableName: "config")
	}

	changeSet(author: "lakshmi (generated)", id: "1405744646136-3") {
		addNotNullConstraint(columnDataType: "longtext", columnName: "value", tableName: "config")
	}

	changeSet(author: "lakshmi (generated)", id: "1405744646136-4") {
		createIndex(indexName: "gateway_name_uniq_1405744645568", tableName: "payment_gateways", unique: "true") {
			column(name: "gateway_name")
                }
        }
    
    
	changeSet(author: "lakshmi (generated)", id: "1406014072336-1") {
		addColumn(tableName: "payments") {
			column(name: "payment_load", type: "varchar(255)")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1406014072336-2") {
		addColumn(tableName: "payments") {
			column(name: "payment_status", type: "varchar(255)")
		}
	}
}
