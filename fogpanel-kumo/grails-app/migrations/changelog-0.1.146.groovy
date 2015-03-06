databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1402206207754-1") {
		createTable(tableName: "network_offer_service_list") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "network_offerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime")

			column(name: "network_offer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "provider", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "service", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-2") {
		addColumn(tableName: "network_offer") {
			column(name: "availability", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-3") {
		addColumn(tableName: "network_offer") {
			column(name: "created", type: "datetime")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-4") {
		addColumn(tableName: "network_offer") {
			column(name: "guest_type", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-5") {
		addColumn(tableName: "network_offer") {
			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-6") {
		addColumn(tableName: "network_offer") {
			column(name: "specify_ip_ranges", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-7") {
		addColumn(tableName: "network_offer") {
			column(name: "state", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-8") {
		addColumn(tableName: "network_offer") {
			column(name: "traffic_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-9") {
		addColumn(tableName: "network_offer") {
			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-10") {
		addColumn(tableName: "network_offer_zone_cost") {
			column(name: "minimum_cost", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-11") {
		addColumn(tableName: "network_offer_zone_cost") {
			column(name: "setup_cost", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-12") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "network_rate", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-13") {
		modifyDataType(columnName: "specify_vlan", newDataType: "bit", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-14") {
		addNotNullConstraint(columnDataType: "bit", columnName: "specify_vlan", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-15") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "parent_name", tableName: "vmsnapshot")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-16") {
		dropForeignKeyConstraint(baseTableName: "network_offer_zone_cost", constraintName: "FK9269164CA2BF084B")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-19") {
		createIndex(indexName: "FK_s4l43x8bkbfpdnxb63dga90ao", tableName: "network_offer") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-20") {
		createIndex(indexName: "FK_ovi14ijjr3vuljtga2o0i3e1c", tableName: "network_offer_service_list") {
			column(name: "network_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-21") {
		dropColumn(columnName: "dhcp", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-22") {
		dropColumn(columnName: "dns", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-23") {
		dropColumn(columnName: "firewall", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-24") {
		dropColumn(columnName: "load_balancer", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-25") {
		dropColumn(columnName: "order_no", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-26") {
		dropColumn(columnName: "port_forwarding", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-27") {
		dropColumn(columnName: "security_groups", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-28") {
		dropColumn(columnName: "source_nat", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-29") {
		dropColumn(columnName: "static_nat", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-30") {
		dropColumn(columnName: "tags", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-31") {
		dropColumn(columnName: "user_data", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-32") {
		dropColumn(columnName: "vpn", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-33") {
		dropColumn(columnName: "zone_id", tableName: "network_offer_zone_cost")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-17") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "network_offer", constraintName: "FK_s4l43x8bkbfpdnxb63dga90ao", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1402206207754-18") {
		addForeignKeyConstraint(baseColumnNames: "network_offer_id", baseTableName: "network_offer_service_list", constraintName: "FK_ovi14ijjr3vuljtga2o0i3e1c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network_offer", referencesUniqueColumn: "false")
	}
}
