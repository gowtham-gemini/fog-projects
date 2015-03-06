databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1400654770581-1") {
		addColumn(tableName: "template") {
			column(name: "detailed_description", type: "varchar(10000)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-2") {
		addColumn(tableName: "template") {
			column(name: "os_referenceurl", type: "varchar(255)")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-3") {
		addNotNullConstraint(columnDataType: "varchar(20)", columnName: "billing_phone_number", tableName: "account")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-4") {
		addNotNullConstraint(columnDataType: "varchar(20)", columnName: "phone_number", tableName: "account")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-5") {
		modifyDataType(columnName: "cpu_number", newDataType: "double precision", tableName: "computing_offer")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-6") {
		modifyDataType(columnName: "memory", newDataType: "double precision", tableName: "computing_offer")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-7") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "billing_type", tableName: "virtual_machine")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-8") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "reference_id", tableName: "virtual_machine")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-20") {
		createIndex(indexName: "FK_ga0axi27am7109j49nj52mgwf", tableName: "disk_offer") {
			column(name: "domain_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-21") {
		createIndex(indexName: "FK_3hgs3kmg1o1oixkoxvay4msol", tableName: "pre_defined_reply") {
			column(name: "department_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-22") {
		createIndex(indexName: "FK_95pw196xd8iy111lhdx7cqfo5", tableName: "ticket") {
			column(name: "department_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-23") {
		createIndex(indexName: "FK_i0i7rws9vvh121bg8mibj73pe", tableName: "ticket") {
			column(name: "user_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-24") {
		createIndex(indexName: "FK_rexowx31mw2y20hpir77jhvw9", tableName: "ticket") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-25") {
		createIndex(indexName: "FK_7n880nh99fxukrji0ojak7o7u", tableName: "ticket_details") {
			column(name: "user_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-26") {
		createIndex(indexName: "FK_b5xfq2niwdc9o225oge7j6165", tableName: "ticket_details") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-27") {
		createIndex(indexName: "FK_q4lalqxpdkp17sn9ays2i5kj0", tableName: "ticket_details") {
			column(name: "ticket_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-28") {
		createIndex(indexName: "FK_k46k6hjxytitvc5xi3alrjpgj", tableName: "virtual_machine") {
			column(name: "ssh_key_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-29") {
		createIndex(indexName: "FK_f2vot80xitg27l0c01vqrflg4", tableName: "vm_ip") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-30") {
		createIndex(indexName: "FK_knkrp10l9pkpubnjcythfj3gh", tableName: "vm_ip") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-9") {
		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "disk_offer", constraintName: "FK_ga0axi27am7109j49nj52mgwf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-10") {
		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "pre_defined_reply", constraintName: "FK_3hgs3kmg1o1oixkoxvay4msol", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-11") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket", constraintName: "FK_rexowx31mw2y20hpir77jhvw9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-12") {
		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "ticket", constraintName: "FK_95pw196xd8iy111lhdx7cqfo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket", constraintName: "FK_i0i7rws9vvh121bg8mibj73pe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-14") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket_details", constraintName: "FK_b5xfq2niwdc9o225oge7j6165", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-15") {
		addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "ticket_details", constraintName: "FK_q4lalqxpdkp17sn9ays2i5kj0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ticket", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-16") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket_details", constraintName: "FK_7n880nh99fxukrji0ojak7o7u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-17") {
		addForeignKeyConstraint(baseColumnNames: "ssh_key_id", baseTableName: "virtual_machine", constraintName: "FK_k46k6hjxytitvc5xi3alrjpgj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sshkeys", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-18") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "vm_ip", constraintName: "FK_knkrp10l9pkpubnjcythfj3gh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1400654770581-19") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "vm_ip", constraintName: "FK_f2vot80xitg27l0c01vqrflg4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
