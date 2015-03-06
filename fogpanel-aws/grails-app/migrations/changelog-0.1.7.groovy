databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424267935882-1") {
		createTable(tableName: "network") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "networkPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

			column(name: "cidr", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "region_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-2") {
		createTable(tableName: "subnet") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "subnetPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

			column(name: "cidr", type: "varchar(255)")

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "region_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-3") {
		createTable(tableName: "tag") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tagPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-4") {
		createTable(tableName: "volume") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "volumePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "description", type: "varchar(255)")

			column(name: "encrypted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "iops", type: "integer")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "region_id", type: "bigint")

			column(name: "size", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "snapshot_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint")

			column(name: "volume_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-5") {
		addColumn(tableName: "virtual_machine") {
			column(name: "architecture", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-6") {
		addColumn(tableName: "virtual_machine") {
			column(name: "ebs_optimized", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-7") {
		addColumn(tableName: "virtual_machine") {
			column(name: "flavor_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-8") {
		addColumn(tableName: "virtual_machine") {
			column(name: "hypervisor", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-9") {
		addColumn(tableName: "virtual_machine") {
			column(name: "kernel_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-10") {
		addColumn(tableName: "virtual_machine") {
			column(name: "launch_time", type: "datetime")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-11") {
		addColumn(tableName: "virtual_machine") {
			column(name: "network_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-12") {
		addColumn(tableName: "virtual_machine") {
			column(name: "private_dns_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-13") {
		addColumn(tableName: "virtual_machine") {
			column(name: "private_ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-14") {
		addColumn(tableName: "virtual_machine") {
			column(name: "public_dns_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-15") {
		addColumn(tableName: "virtual_machine") {
			column(name: "public_ip_address", type: "varchar(255)")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-16") {
		addColumn(tableName: "virtual_machine") {
			column(name: "ramdisk_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-17") {
		addColumn(tableName: "virtual_machine") {
			column(name: "region_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-18") {
		addColumn(tableName: "virtual_machine") {
			column(name: "root_device_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-19") {
		addColumn(tableName: "virtual_machine") {
			column(name: "root_device_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-20") {
		addColumn(tableName: "virtual_machine") {
			column(name: "security_group_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-21") {
		addColumn(tableName: "virtual_machine") {
			column(name: "sshkeys_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-22") {
		addColumn(tableName: "virtual_machine") {
			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-23") {
		addColumn(tableName: "virtual_machine") {
			column(name: "subnet_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-24") {
		dropForeignKeyConstraint(baseTableName: "virtual_machine", baseTableSchemaName: "aws_fog", constraintName: "FK_g7m5i040mh3pxr8onlhtqpkx7")
	}

	changeSet(author: "az (generated)", id: "1424267935882-48") {
		createIndex(indexName: "FK_37thpll3yx9nsv9qhyeborr90", tableName: "network") {
			column(name: "user_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-49") {
		createIndex(indexName: "FK_6dos8079g5b706dhgnu2k6983", tableName: "network") {
			column(name: "account_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-50") {
		createIndex(indexName: "FK_kidomsebws89wnods7fmsm70p", tableName: "network") {
			column(name: "region_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-51") {
		createIndex(indexName: "FK_3tfo7qjhbgfn1lhxx0qg0nqe7", tableName: "subnet") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-52") {
		createIndex(indexName: "FK_fbp3uf1w4mf6bn0ywlrbjq9g1", tableName: "subnet") {
			column(name: "network_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-53") {
		createIndex(indexName: "FK_knjwb581rdkv8e0lmtglgid33", tableName: "subnet") {
			column(name: "account_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-54") {
		createIndex(indexName: "FK_p0yw8jplntgglc0l2xmy8x3w0", tableName: "subnet") {
			column(name: "region_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-55") {
		createIndex(indexName: "FK_qt22dgwak8k9i9s6sww9jspie", tableName: "subnet") {
			column(name: "user_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-56") {
		createIndex(indexName: "FK_151ooxskkpn49blwsh7mjwysx", tableName: "tag") {
			column(name: "user_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-57") {
		createIndex(indexName: "FK_h1bk6me6i352nmwkr7bjx41cq", tableName: "tag") {
			column(name: "account_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-58") {
		createIndex(indexName: "FK_hh2wamljk2tui3j8aued3nrp8", tableName: "tag") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-59") {
		createIndex(indexName: "FK_9iwcc8gyi1cicifyg15ihkemx", tableName: "virtual_machine") {
			column(name: "security_group_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-60") {
		createIndex(indexName: "FK_af3lte75yu6ih16y6wqqspqj", tableName: "virtual_machine") {
			column(name: "flavor_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-61") {
		createIndex(indexName: "FK_b8y7clgrjgx609kn5luw9yswn", tableName: "virtual_machine") {
			column(name: "subnet_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-62") {
		createIndex(indexName: "FK_ge8d1kswtgoqhqbwdrhwvvsf3", tableName: "virtual_machine") {
			column(name: "sshkeys_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-63") {
		createIndex(indexName: "FK_ikfkj13rn6vlextyr7phtvjek", tableName: "virtual_machine") {
			column(name: "network_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-64") {
		createIndex(indexName: "FK_t934hb8qv3iex9qyxqgewb407", tableName: "virtual_machine") {
			column(name: "region_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-65") {
		createIndex(indexName: "FK_fvrw5qjb69r3oraedl12spqhe", tableName: "volume") {
			column(name: "user_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-66") {
		createIndex(indexName: "FK_ii5m1nevpuu8xgcc2axrvtx4r", tableName: "volume") {
			column(name: "region_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-67") {
		createIndex(indexName: "FK_lrh52t8rg5yem7nj2323wg07h", tableName: "volume") {
			column(name: "account_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-68") {
		createIndex(indexName: "FK_q8ha6vbmru7dxjbqqj2ra64wm", tableName: "volume") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-69") {
		createIndex(indexName: "FK_sjcd2fjm5og7ovj103eky0c1f", tableName: "volume") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-70") {
		createIndex(indexName: "FK_sxe2a5os3ef5rno7nalk80ad0", tableName: "volume") {
			column(name: "volume_type_id")
		}
	}

	changeSet(author: "az (generated)", id: "1424267935882-71") {
		dropColumn(columnName: "billing_type", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424267935882-72") {
		dropColumn(columnName: "first_run", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424267935882-73") {
		dropColumn(columnName: "name", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424267935882-74") {
		dropColumn(columnName: "status", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424267935882-75") {
		dropColumn(columnName: "zone_id", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424267935882-25") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "network", constraintName: "FK_6dos8079g5b706dhgnu2k6983", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-26") {
		addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "network", constraintName: "FK_kidomsebws89wnods7fmsm70p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-27") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "network", constraintName: "FK_37thpll3yx9nsv9qhyeborr90", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-28") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "subnet", constraintName: "FK_knjwb581rdkv8e0lmtglgid33", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-29") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "subnet", constraintName: "FK_fbp3uf1w4mf6bn0ywlrbjq9g1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-30") {
		addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "subnet", constraintName: "FK_p0yw8jplntgglc0l2xmy8x3w0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-31") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "subnet", constraintName: "FK_qt22dgwak8k9i9s6sww9jspie", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-32") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "subnet", constraintName: "FK_3tfo7qjhbgfn1lhxx0qg0nqe7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-33") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "tag", constraintName: "FK_h1bk6me6i352nmwkr7bjx41cq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-34") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "tag", constraintName: "FK_151ooxskkpn49blwsh7mjwysx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-35") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "tag", constraintName: "FK_hh2wamljk2tui3j8aued3nrp8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-36") {
		addForeignKeyConstraint(baseColumnNames: "flavor_id", baseTableName: "virtual_machine", constraintName: "FK_af3lte75yu6ih16y6wqqspqj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flavors", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-37") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "virtual_machine", constraintName: "FK_ikfkj13rn6vlextyr7phtvjek", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-38") {
		addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "virtual_machine", constraintName: "FK_t934hb8qv3iex9qyxqgewb407", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-39") {
		addForeignKeyConstraint(baseColumnNames: "security_group_id", baseTableName: "virtual_machine", constraintName: "FK_9iwcc8gyi1cicifyg15ihkemx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "security_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-40") {
		addForeignKeyConstraint(baseColumnNames: "sshkeys_id", baseTableName: "virtual_machine", constraintName: "FK_ge8d1kswtgoqhqbwdrhwvvsf3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sshkeys", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-41") {
		addForeignKeyConstraint(baseColumnNames: "subnet_id", baseTableName: "virtual_machine", constraintName: "FK_b8y7clgrjgx609kn5luw9yswn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "subnet", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-42") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "volume", constraintName: "FK_lrh52t8rg5yem7nj2323wg07h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-43") {
		addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "volume", constraintName: "FK_ii5m1nevpuu8xgcc2axrvtx4r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-44") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "volume", constraintName: "FK_fvrw5qjb69r3oraedl12spqhe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-45") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "volume", constraintName: "FK_q8ha6vbmru7dxjbqqj2ra64wm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-46") {
		addForeignKeyConstraint(baseColumnNames: "volume_type_id", baseTableName: "volume", constraintName: "FK_sxe2a5os3ef5rno7nalk80ad0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1424267935882-47") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "volume", constraintName: "FK_sjcd2fjm5og7ovj103eky0c1f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
