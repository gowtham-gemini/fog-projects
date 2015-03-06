databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424433609222-1") {
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

			column(name: "cidr_block", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit")

			column(name: "deleted_at", type: "datetime")

			column(name: "dhcp_options_id", type: "varchar(255)")

			column(name: "instance_tenancy", type: "varchar(255)")

			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "region_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "vpc_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-2") {
		addColumn(tableName: "subnet") {
			column(name: "availability_zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-3") {
		addColumn(tableName: "subnet") {
			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-4") {
		addColumn(tableName: "subnet") {
			column(name: "subnet_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-5") {
		addColumn(tableName: "subnet") {
			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-6") {
		dropForeignKeyConstraint(baseTableName: "subnet", baseTableSchemaName: "aws_fog", constraintName: "FK_knjwb581rdkv8e0lmtglgid33")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-7") {
		dropForeignKeyConstraint(baseTableName: "subnet", baseTableSchemaName: "aws_fog", constraintName: "FK_fbp3uf1w4mf6bn0ywlrbjq9g1")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-8") {
		dropForeignKeyConstraint(baseTableName: "subnet", baseTableSchemaName: "aws_fog", constraintName: "FK_p0yw8jplntgglc0l2xmy8x3w0")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-9") {
		dropForeignKeyConstraint(baseTableName: "subnet", baseTableSchemaName: "aws_fog", constraintName: "FK_qt22dgwak8k9i9s6sww9jspie")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-10") {
		dropForeignKeyConstraint(baseTableName: "subnet", baseTableSchemaName: "aws_fog", constraintName: "FK_3tfo7qjhbgfn1lhxx0qg0nqe7")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-16") {
		createIndex(indexName: "FK_ssocekb7d85okkel6i411iwry", tableName: "subnet") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-17") {
		createIndex(indexName: "FK_tdqe4vxedelnkkwiw7rms3oto", tableName: "subnet") {
			column(name: "availability_zone_id")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-18") {
		createIndex(indexName: "FK_7kooikb0oscu7pom8t2mgcjld", tableName: "vpc") {
			column(name: "user_id")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-19") {
		createIndex(indexName: "FK_f1d9m11bffgl3er62dde7pcye", tableName: "vpc") {
			column(name: "region_id")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-20") {
		createIndex(indexName: "FK_h6x1vq69pja08w3rxdd8em9bp", tableName: "vpc") {
			column(name: "account_id")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-21") {
		createIndex(indexName: "vpc_id_uniq_1424433606688", tableName: "vpc", unique: "true") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-22") {
		dropColumn(columnName: "account_id", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-23") {
		dropColumn(columnName: "name", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-24") {
		dropColumn(columnName: "network_id", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-25") {
		dropColumn(columnName: "reference_id", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-26") {
		dropColumn(columnName: "region_id", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-27") {
		dropColumn(columnName: "user_id", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-28") {
		dropColumn(columnName: "zone_id", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-11") {
		addForeignKeyConstraint(baseColumnNames: "availability_zone_id", baseTableName: "subnet", constraintName: "FK_tdqe4vxedelnkkwiw7rms3oto", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-12") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "subnet", constraintName: "FK_ssocekb7d85okkel6i411iwry", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-13") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "vpc", constraintName: "FK_h6x1vq69pja08w3rxdd8em9bp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-14") {
		addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "vpc", constraintName: "FK_f1d9m11bffgl3er62dde7pcye", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
	}

	changeSet(author: "santhosh (generated)", id: "1424433609222-15") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "vpc", constraintName: "FK_7kooikb0oscu7pom8t2mgcjld", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
