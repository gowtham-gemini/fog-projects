databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1409898147881-1") {
		createTable(tableName: "vpc") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vpcPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cidr", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "vpc_offering_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-2") {
		createTable(tableName: "vpcoffering") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vpcofferingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-3") {
		createTable(tableName: "vpcoffering_service_list") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vpcoffering_sPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime")

			column(name: "provider", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "service", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "vpc_offering_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-4") {
		createTable(tableName: "vpcservice_list") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vpcservice_liPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime")

			column(name: "provider", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "service", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-11") {
		createIndex(indexName: "FK_4tvhr1npcmws4ck74yp1sidig", tableName: "vpc") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-12") {
		createIndex(indexName: "FK_7kooikb0oscu7pom8t2mgcjld", tableName: "vpc") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-13") {
		createIndex(indexName: "FK_h6x1vq69pja08w3rxdd8em9bp", tableName: "vpc") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-14") {
		createIndex(indexName: "FK_k29r1foon9901lhgvrjugr0ws", tableName: "vpc") {
			column(name: "vpc_offering_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-15") {
		createIndex(indexName: "reference_id_uniq_1409898146473", tableName: "vpc", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-16") {
		createIndex(indexName: "reference_id_uniq_1409898146474", tableName: "vpcoffering", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-17") {
		createIndex(indexName: "FK_ehr8yylw0fc0k2lopu184tuwt", tableName: "vpcoffering_service_list") {
			column(name: "vpc_offering_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-18") {
		createIndex(indexName: "FK_jfqk3iq9iyl62m7q6mvlgkr8f", tableName: "vpcservice_list") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-5") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "vpc", constraintName: "FK_h6x1vq69pja08w3rxdd8em9bp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-6") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "vpc", constraintName: "FK_7kooikb0oscu7pom8t2mgcjld", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-7") {
		addForeignKeyConstraint(baseColumnNames: "vpc_offering_id", baseTableName: "vpc", constraintName: "FK_k29r1foon9901lhgvrjugr0ws", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpcoffering", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-8") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "vpc", constraintName: "FK_4tvhr1npcmws4ck74yp1sidig", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-9") {
		addForeignKeyConstraint(baseColumnNames: "vpc_offering_id", baseTableName: "vpcoffering_service_list", constraintName: "FK_ehr8yylw0fc0k2lopu184tuwt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpcoffering", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1409898147881-10") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "vpcservice_list", constraintName: "FK_jfqk3iq9iyl62m7q6mvlgkr8f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}
}
