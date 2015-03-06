databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1407736786394-1") {
		createTable(tableName: "port_forwarding") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "port_forwardiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "dest_ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "firewall_rules_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "netwotk_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "private_end_port", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "private_start_port", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "useripaddress_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-8") {
		createIndex(indexName: "FK_1xia9wksfjnm4w5iqkec031hf", tableName: "port_forwarding") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-9") {
		createIndex(indexName: "FK_6bsb7f9784cln0y9vi1by8xu0", tableName: "port_forwarding") {
			column(name: "firewall_rules_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-10") {
		createIndex(indexName: "FK_6c2j14o1cnprtujow6jg2s6vq", tableName: "port_forwarding") {
			column(name: "useripaddress_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-11") {
		createIndex(indexName: "FK_ixkelr0jwn3wwno6afjwx5aw7", tableName: "port_forwarding") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-12") {
		createIndex(indexName: "FK_mrtfe4b0oq314xk22bofwndwq", tableName: "port_forwarding") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-13") {
		createIndex(indexName: "FK_rpmkhxjgjc6mo303e52x6bgts", tableName: "port_forwarding") {
			column(name: "netwotk_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-2") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "port_forwarding", constraintName: "FK_1xia9wksfjnm4w5iqkec031hf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-3") {
		addForeignKeyConstraint(baseColumnNames: "firewall_rules_id", baseTableName: "port_forwarding", constraintName: "FK_6bsb7f9784cln0y9vi1by8xu0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "firewall_rules", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-4") {
		addForeignKeyConstraint(baseColumnNames: "netwotk_id", baseTableName: "port_forwarding", constraintName: "FK_rpmkhxjgjc6mo303e52x6bgts", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-5") {
		addForeignKeyConstraint(baseColumnNames: "useripaddress_id", baseTableName: "port_forwarding", constraintName: "FK_6c2j14o1cnprtujow6jg2s6vq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "useripaddress", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-6") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "port_forwarding", constraintName: "FK_ixkelr0jwn3wwno6afjwx5aw7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407736786394-7") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "port_forwarding", constraintName: "FK_mrtfe4b0oq314xk22bofwndwq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
        
        changeSet(author: "gowtham (generated)", id: "PortForwarding-BillableItem") {
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 19)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "common.billableItem.portForwarding")
                column(name: "reference_item_name", value: "PortForwarding")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
                column(name: "has_zone", valueNumeric: 1)
                
            }
                    
            insert(tableName: "miscellaneous_offer") {
               column(name: "id", valueNumeric: 6)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "PortForwarding Cost")
                column(name: "name", value: "PortForwarding Cost")
                column(name: "unit", value: "per Month")
            }
            
	}
}
