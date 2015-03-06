databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1413356927013-1") {
		createTable(tableName: "domain") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "domainPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "extra", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-2") {
		createTable(tableName: "flavor_cost_info") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "flavor_cost_iPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "flavor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "region_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "running_cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "setup_cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "stop_cost", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-3") {
		createTable(tableName: "flavor_project") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "flavor_projecPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "flavor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "flavors_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-4") {
		createTable(tableName: "flavors") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "flavorsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "description", type: "varchar(255)")

			column(name: "ephemeralgb", type: "integer")

			column(name: "is_disabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "is_public", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "memory", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "rootgb", type: "integer")

			column(name: "rxtx_factor", type: "double precision")

			column(name: "swap", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "vcpu_weight", type: "integer")

			column(name: "vcpus", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-5") {
		createTable(tableName: "images") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "imagesPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "checksum", type: "varchar(255)")

			column(name: "container_format", type: "varchar(255)")

			column(name: "cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "description", type: "varchar(255)")

			column(name: "disk_format", type: "varchar(255)")

			column(name: "is_protected", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "is_public", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "min_disk", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "min_ram", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "size", type: "double precision")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "virtual_size", type: "double precision")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-6") {
		createTable(tableName: "project") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "projectPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "domain_id", type: "bigint")

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "extra", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-7") {
		createTable(tableName: "security_group") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "security_grouPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-8") {
		createTable(tableName: "security_group_rules") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "security_grouPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cidr", type: "varchar(255)")

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "from_port", type: "integer")

			column(name: "protocol", type: "varchar(255)")

			column(name: "security_group_id", type: "bigint")

			column(name: "to_port", type: "integer")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-53") {
		createIndex(indexName: "FK_nwd5fu0bpryglw05msb9stpx1", tableName: "flavor_cost_info") {
			column(name: "flavor_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-54") {
		createIndex(indexName: "FK_od4u31dh41r3mfl1yjtpgbvq6", tableName: "flavor_cost_info") {
			column(name: "region_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-55") {
		createIndex(indexName: "FK_3deppvrh2f9rbsnnxj98y21eu", tableName: "flavor_project") {
			column(name: "flavor_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-56") {
		createIndex(indexName: "FK_54wvbac0blb0ki8uhrp9m3jsb", tableName: "flavor_project") {
			column(name: "flavors_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-57") {
		createIndex(indexName: "FK_fl6bsyss2gr5cscv62dsrssmu", tableName: "flavor_project") {
			column(name: "project_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-58") {
		createIndex(indexName: "FK_8l1cccuisamgod349kdqw52e6", tableName: "project") {
			column(name: "domain_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-59") {
		createIndex(indexName: "FK_8cx8ylpdj6l7m6duniqxgomdh", tableName: "security_group") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-60") {
		createIndex(indexName: "FK_rim39616qje3nffpvt1ecnsmb", tableName: "security_group") {
			column(name: "project_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-61") {
		createIndex(indexName: "FK_j47n7qdogdyxkhp86twuwgxqo", tableName: "security_group_rules") {
			column(name: "security_group_id")
		}
	}

	

	changeSet(author: "gowtham (generated)", id: "1413356927013-17") {
		addForeignKeyConstraint(baseColumnNames: "flavor_id", baseTableName: "flavor_cost_info", constraintName: "FK_nwd5fu0bpryglw05msb9stpx1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flavors", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-18") {
		addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "flavor_cost_info", constraintName: "FK_od4u31dh41r3mfl1yjtpgbvq6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-19") {
		addForeignKeyConstraint(baseColumnNames: "flavor_id", baseTableName: "flavor_project", constraintName: "FK_3deppvrh2f9rbsnnxj98y21eu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flavors", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-20") {
		addForeignKeyConstraint(baseColumnNames: "flavors_id", baseTableName: "flavor_project", constraintName: "FK_54wvbac0blb0ki8uhrp9m3jsb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flavors", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413356927013-21") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "flavor_project", constraintName: "FK_fl6bsyss2gr5cscv62dsrssmu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}


	changeSet(author: "gowtham (generated)", id: "1413356927013-31") {
		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "project", constraintName: "FK_8l1cccuisamgod349kdqw52e6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
	}

        changeSet(author: "gowtham (generated)", id: "1413356927013-36") {
		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "security_group", constraintName: "FK_rim39616qje3nffpvt1ecnsmb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
	}
	
}
