databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1411562796893-1") {
		createTable(tableName: "vpnconnection") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vpnconnectionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "job_id", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "removed", type: "datetime")

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "vpn_customer_gateway_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "vpn_gateway_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-7") {
		createIndex(indexName: "FK_3f64ubts5rdmrbhr4vcfhp7pi", tableName: "vpnconnection") {
			column(name: "vpn_customer_gateway_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-8") {
		createIndex(indexName: "FK_b8qq4rt7xx6bmvxtlc38lx3rg", tableName: "vpnconnection") {
			column(name: "vpn_gateway_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-9") {
		createIndex(indexName: "FK_fd5yeu9xqg5tii2a8snckldu2", tableName: "vpnconnection") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-10") {
		createIndex(indexName: "FK_n58meu2yx21ovmaenmpuntnnp", tableName: "vpnconnection") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-11") {
		createIndex(indexName: "FK_o5xiadvetwbujd1jaqxljjadk", tableName: "vpnconnection") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-12") {
		createIndex(indexName: "reference_id_uniq_1411562796055", tableName: "vpnconnection", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-2") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "vpnconnection", constraintName: "FK_n58meu2yx21ovmaenmpuntnnp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-3") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "vpnconnection", constraintName: "FK_fd5yeu9xqg5tii2a8snckldu2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-4") {
		addForeignKeyConstraint(baseColumnNames: "vpn_customer_gateway_id", baseTableName: "vpnconnection", constraintName: "FK_3f64ubts5rdmrbhr4vcfhp7pi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s2scustomer_gateway", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-5") {
		addForeignKeyConstraint(baseColumnNames: "vpn_gateway_id", baseTableName: "vpnconnection", constraintName: "FK_b8qq4rt7xx6bmvxtlc38lx3rg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s2svpngateways", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411562796893-6") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "vpnconnection", constraintName: "FK_o5xiadvetwbujd1jaqxljjadk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
