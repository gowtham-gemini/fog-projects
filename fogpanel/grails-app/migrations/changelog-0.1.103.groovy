databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1386828208524-1") {
		createTable(tableName: "vm_ip") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vm_ipPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "acquire_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "in_use", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "revoke_date", type: "datetime")

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1386828208524-2") {
		addColumn(tableName: "virtual_machine") {
			column(name: "nic_id", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "IPCOST-BillableItem") {
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 12)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "SecondaryIPCost")
                column(name: "reference_item_name", value: "SecondaryIPCost")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
                column(name: "has_zone", valueNumeric: 1)
                
            }
            
	}
}
