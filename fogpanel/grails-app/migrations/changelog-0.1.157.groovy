databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1405673340965-1") {
		createTable(tableName: "useripaddress") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "useripaddressPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "acquire_date", type: "datetime")

			column(name: "ip_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_basic_vm_default_ip", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "is_first_ip", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "is_source_nat", type: "bit")

			column(name: "is_static_nat", type: "bit")

			column(name: "job_id", type: "varchar(255)")

			column(name: "mac_address", type: "varchar(255)")

			column(name: "network_id", type: "bigint")

			column(name: "publicipaddress", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "removed", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "revoke_date", type: "datetime")

			column(name: "state", type: "varchar(255)")

			column(name: "static_nat_virtual_machine_id", type: "bigint")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint")

			column(name: "vlan_name", type: "varchar(255)")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-2") {
		modifyDataType(columnName: "value", newDataType: "longtext", tableName: "config")
	}
	
	changeSet(author: "gowtham (generated)", id: "1405673340965-10") {
		createIndex(indexName: "FK_7jxo321w37v6bl30a4mpei2e1", tableName: "useripaddress") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-11") {
		createIndex(indexName: "FK_be9g08vg59578ojeln2md3qwy", tableName: "useripaddress") {
			column(name: "static_nat_virtual_machine_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-12") {
		createIndex(indexName: "FK_ifaat626s9vbm0o3br8m155wb", tableName: "useripaddress") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-13") {
		createIndex(indexName: "FK_pt0pofj92tym7end86t4gx8dx", tableName: "useripaddress") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-14") {
		createIndex(indexName: "FK_qce1d8i1su609r0itckwcaa9s", tableName: "useripaddress") {
			column(name: "network_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-15") {
		createIndex(indexName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", tableName: "useripaddress") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-16") {
		createIndex(indexName: "ip_reference_id_uniq_1405673340124", tableName: "useripaddress", unique: "true") {
			column(name: "ip_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-4") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "useripaddress", constraintName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-5") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "useripaddress", constraintName: "FK_qce1d8i1su609r0itckwcaa9s", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-6") {
		addForeignKeyConstraint(baseColumnNames: "static_nat_virtual_machine_id", baseTableName: "useripaddress", constraintName: "FK_be9g08vg59578ojeln2md3qwy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-7") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "useripaddress", constraintName: "FK_7jxo321w37v6bl30a4mpei2e1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-8") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "useripaddress", constraintName: "FK_pt0pofj92tym7end86t4gx8dx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405673340965-9") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "useripaddress", constraintName: "FK_ifaat626s9vbm0o3br8m155wb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
