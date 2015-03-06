databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1368703711525-1") {
		createTable(tableName: "account") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "accountPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "activation_date", type: "datetime")

			column(name: "billing_city", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "billing_country_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billing_phone_number", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "billing_state_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billing_street_address", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "billing_zip", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "company_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "country_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "domain_id", type: "bigint")

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "varchar(150)") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(15)") {
				constraints(nullable: "false")
			}

			column(name: "phone_number", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "preferred_language", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "promotional_code", type: "varchar(255)")

			column(name: "sign_up_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "state_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "street_address", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "user_name", type: "varchar(15)") {
				constraints(nullable: "false")
			}

			column(name: "zip", type: "varchar(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-2") {
		createTable(tableName: "cluster") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "clusterPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cluster_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pod_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-3") {
		createTable(tableName: "computing_offer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "computing_offPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "available", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "band_width", type: "integer")

			column(name: "cluster_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "core_cpu_speed", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "cpu_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "default_use", type: "bit")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "host_tags", type: "varchar(255)")

			column(name: "is_system", type: "bit")

			column(name: "limit_cpu_use", type: "bit")

			column(name: "memory", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_rate", type: "varchar(255)")

			column(name: "offerha", type: "bit")

			column(name: "offer_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "order_no", type: "integer")

			column(name: "pod_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "storage_type", type: "varchar(255)")

			column(name: "tags", type: "varchar(255)")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-4") {
		createTable(tableName: "computing_offer_zone_cost") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "computing_offPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "computing_offer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "minimum_cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "setup_cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-5") {
		createTable(tableName: "config") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "configPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {    
				constraints(nullable: "false")
			}

			column(name: "value", type: "varchar(5000)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-6") {
		createTable(tableName: "country") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "countryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "calling_code", type: "varchar(255)")

			column(name: "country_alpha2code", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "country_alpha3code", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "country_code", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "country_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-7") {
		createTable(tableName: "disk_offer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "disk_offerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cluster_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "customized", type: "bit")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "disk_offer_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "order_no", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "pod_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "size", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "tags", type: "varchar(255)")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-8") {
		createTable(tableName: "disk_offer_zone_cost") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "disk_offer_zoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "disk_offer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "minimum_cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-9") {
		createTable(tableName: "domain") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "domainPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "has_child", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "level", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_domain", type: "varchar(255)")

			column(name: "parent_domain_id", type: "varchar(255)")

			column(name: "parent_domain_name", type: "varchar(255)")

			column(name: "path", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-10") {
		createTable(tableName: "event_log_ip_address") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "event_log_ip_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "failure_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "over_all_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "success_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-11") {
		createTable(tableName: "host") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "hostPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cluster_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "host_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "host_tag", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pod_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-12") {
		createTable(tableName: "hypervisor") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "hypervisorPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-13") {
		createTable(tableName: "ip_address") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ip_addressPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "dns1", type: "varchar(255)")

			column(name: "dns2", type: "varchar(255)")

			column(name: "end_ip", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "end_ip_value", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "gate_way", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ip_block_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "net_mask", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pod_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "start_ip", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "start_ip_value", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-14") {
		createTable(tableName: "miscellaneous_offer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "miscellaneousPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "unit", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-15") {
		createTable(tableName: "miscellaneous_offer_zone_cost") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "miscellaneousPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cluster_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "miscellaneous_offer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "pod_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-16") {
		createTable(tableName: "network_offer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "network_offerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "conserve_mode", type: "bit")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "dhcp", type: "bit")

			column(name: "dns", type: "bit")

			column(name: "firewall", type: "bit")

			column(name: "load_balancer", type: "bit")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_rate", type: "varchar(255)")

			column(name: "network_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "order_no", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "port_forwarding", type: "bit")

			column(name: "security_groups", type: "bit")

			column(name: "source_nat", type: "bit")

			column(name: "specify_vlan", type: "bit")

			column(name: "static_nat", type: "bit")

			column(name: "tags", type: "varchar(255)")

			column(name: "user_data", type: "bit")

			column(name: "vpn", type: "bit")
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-17") {
		createTable(tableName: "network_offer_zone_cost") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "network_offerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "network_offer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-18") {
		createTable(tableName: "notification_email") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "notification_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-19") {
		createTable(tableName: "os_category") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "os_categoryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "os_category_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-20") {
		createTable(tableName: "os_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "os_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "os_category_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "reference_os_type_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-21") {
		createTable(tableName: "pod") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "podPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pod_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-22") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-23") {
		createTable(tableName: "snapshot") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "snapshotPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "varchar(255)")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "job", type: "varchar(255)")

			column(name: "name", type: "varchar(255)")

			column(name: "snapshot_policy_id", type: "bigint")

			column(name: "snapshot_reference_id", type: "varchar(255)")

			column(name: "snapshot_type", type: "varchar(255)")

			column(name: "state", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "volume_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-24") {
		createTable(tableName: "snapshot_policy") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "snapshot_poliPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "day_name", type: "varchar(255)")

			column(name: "day_value", type: "varchar(255)")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "interval_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "max_snaps", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "month_value", type: "varchar(255)")

			column(name: "schedule_time", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "snapshot_name", type: "varchar(255)")

			column(name: "snapshot_policy_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "time_zone", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "time_zone_value", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "volume_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-25") {
		createTable(tableName: "state") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "statePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "country_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "country_code", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "state_code", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-26") {
		createTable(tableName: "storage_pool") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "storage_poolPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cluster_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "storage_pool_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "storage_pool_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "tag", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-27") {
		createTable(tableName: "template") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "templatePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "app_template", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "base_os", type: "varchar(255)")

			column(name: "cost", type: "integer")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "extractable", type: "bit")

			column(name: "featured", type: "bit")

			column(name: "format", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "hypervisor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "is_public", type: "bit")

			column(name: "job", type: "varchar(255)")

			column(name: "my_template", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "os_category_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "os_type_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "password_enabled", type: "bit")

			column(name: "template_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-28") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "api_key", type: "varchar(255)")

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "failure_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "lock_time", type: "datetime")

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "secret_key", type: "varchar(255)")

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "uuid", type: "varchar(255)")
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-29") {
		createTable(tableName: "user_event") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "user_eventPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "event_log_ip_address_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "event_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "failed_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-30") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-31") {
		createTable(tableName: "virtual_machine") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "virtual_machiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "computing_offer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "disk_offer_id", type: "bigint")

			column(name: "display_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "group_name", type: "varchar(255)")

			column(name: "hypervisor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "job", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_offer_id", type: "bigint")

			column(name: "network_read", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "network_write", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "owner_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)")

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "template_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-32") {
		createTable(tableName: "volume") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "volumePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "custom_disk_size", type: "varchar(255)")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "disk_offer_id", type: "bigint")

			column(name: "job", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "owner_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)")

			column(name: "type", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint")

			column(name: "volume_reference_id", type: "varchar(255)")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-33") {
		createTable(tableName: "zone") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "zonePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alias_description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "alias_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "available", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "order_no", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "reference_zone_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-34") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-35") {
		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FKB9D38A2DFAA15E8D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-36") {
		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FKB9D38A2D5F80016D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-37") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FKB9D38A2D1965F649", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-38") {
		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "account", constraintName: "FKB9D38A2DD087110B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-39") {
		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FKB9D38A2DD0BF5829", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-40") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "cluster", constraintName: "FK33FB11FA784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-41") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "cluster", constraintName: "FK33FB11FAA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-42") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "computing_offer", constraintName: "FKBC30B9915F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-43") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "computing_offer", constraintName: "FKBC30B991784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-44") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "computing_offer", constraintName: "FKBC30B991A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-45") {
		addForeignKeyConstraint(baseColumnNames: "computing_offer_id", baseTableName: "computing_offer_zone_cost", constraintName: "FKA217635255513EE2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-46") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "computing_offer_zone_cost", constraintName: "FKA2176352A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-47") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "disk_offer", constraintName: "FKAB8C213A5F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-48") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "disk_offer", constraintName: "FKAB8C213A784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-49") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "disk_offer", constraintName: "FKAB8C213AA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-50") {
		addForeignKeyConstraint(baseColumnNames: "disk_offer_id", baseTableName: "disk_offer_zone_cost", constraintName: "FKAA980BBBDB8B018E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "disk_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-51") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "disk_offer_zone_cost", constraintName: "FKAA980BBBA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-52") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "event_log_ip_address", constraintName: "FK5A632CFCA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-53") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "host", constraintName: "FK30F5A85F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-54") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "host", constraintName: "FK30F5A8784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-55") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "host", constraintName: "FK30F5A8A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-56") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "hypervisor", constraintName: "FK6CC03317A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-57") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "ip_address", constraintName: "FK583738DC784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-58") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "ip_address", constraintName: "FK583738DCA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-59") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600C5F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-60") {
		addForeignKeyConstraint(baseColumnNames: "miscellaneous_offer_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600C4F48A26E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "miscellaneous_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-61") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600C784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-62") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600CA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-63") {
		addForeignKeyConstraint(baseColumnNames: "network_offer_id", baseTableName: "network_offer_zone_cost", constraintName: "FK9269164CDDC6F6AE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-64") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "network_offer_zone_cost", constraintName: "FK9269164CA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-65") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "notification_email", constraintName: "FKA6AB9B48A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-66") {
		addForeignKeyConstraint(baseColumnNames: "os_category_id", baseTableName: "os_type", constraintName: "FKB98248B54322274", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "os_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-67") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "pod", constraintName: "FK1B245A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-68") {
		addForeignKeyConstraint(baseColumnNames: "snapshot_policy_id", baseTableName: "snapshot", constraintName: "FK10FAD5C46BB07B74", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "snapshot_policy", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-69") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "snapshot", constraintName: "FK10FAD5C4A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-70") {
		addForeignKeyConstraint(baseColumnNames: "volume_id", baseTableName: "snapshot", constraintName: "FK10FAD5C4497CE94B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-71") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "snapshot_policy", constraintName: "FKB5F880DA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-72") {
		addForeignKeyConstraint(baseColumnNames: "volume_id", baseTableName: "snapshot_policy", constraintName: "FKB5F880D497CE94B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-73") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", constraintName: "FK68AC4911965F649", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-74") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "storage_pool", constraintName: "FK357C63605F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-75") {
		addForeignKeyConstraint(baseColumnNames: "hypervisor_id", baseTableName: "template", constraintName: "FKB13ACC7AB5EBBAB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "hypervisor", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-76") {
		addForeignKeyConstraint(baseColumnNames: "os_category_id", baseTableName: "template", constraintName: "FKB13ACC7A4322274", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "os_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-77") {
		addForeignKeyConstraint(baseColumnNames: "os_type_id", baseTableName: "template", constraintName: "FKB13ACC7A9CDFF5F4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "os_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-78") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "template", constraintName: "FKB13ACC7AA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-79") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "user", constraintName: "FK36EBCBA6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-80") {
		addForeignKeyConstraint(baseColumnNames: "event_log_ip_address_id", baseTableName: "user_event", constraintName: "FK728E7D265F6F2670", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event_log_ip_address", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-81") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_event", constraintName: "FK728E7D26A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-82") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46AFB6D5C0B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-83") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-84") {
		addForeignKeyConstraint(baseColumnNames: "computing_offer_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF1355513EE2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-85") {
		addForeignKeyConstraint(baseColumnNames: "disk_offer_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13DB8B018E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "disk_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-86") {
		addForeignKeyConstraint(baseColumnNames: "hypervisor_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13B5EBBAB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "hypervisor", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-87") {
		addForeignKeyConstraint(baseColumnNames: "network_offer_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13DDC6F6AE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-88") {
		addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF1339E8DCA3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-89") {
		addForeignKeyConstraint(baseColumnNames: "template_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF139C383D0B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "template", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-90") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-91") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-92") {
		addForeignKeyConstraint(baseColumnNames: "disk_offer_id", baseTableName: "volume", constraintName: "FKCFAAE71ADB8B018E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "disk_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-93") {
		addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "volume", constraintName: "FKCFAAE71A39E8DCA3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-94") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "volume", constraintName: "FKCFAAE71AA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-95") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "volume", constraintName: "FKCFAAE71AF6AB5554", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "nandhini (generated)", id: "1368703711525-96") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "volume", constraintName: "FKCFAAE71AA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

//	changeSet(author: "nandhini (generated)", id: "1368703711525-97") {
//		createIndex(indexName: "FKB9D38A2D1965F649", tableName: "account") {
//			column(name: "country_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-98") {
//		createIndex(indexName: "FKB9D38A2D5F80016D", tableName: "account") {
//			column(name: "billing_state_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-99") {
//		createIndex(indexName: "FKB9D38A2DD087110B", tableName: "account") {
//			column(name: "domain_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-100") {
//		createIndex(indexName: "FKB9D38A2DD0BF5829", tableName: "account") {
//			column(name: "state_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-101") {
//		createIndex(indexName: "FKB9D38A2DFAA15E8D", tableName: "account") {
//			column(name: "billing_country_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-102") {
//		createIndex(indexName: "user_name_unique_1368703711373", tableName: "account", unique: "true") {
//			column(name: "user_name")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-103") {
//		createIndex(indexName: "FK33FB11FA784BC369", tableName: "cluster") {
//			column(name: "pod_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-104") {
//		createIndex(indexName: "FK33FB11FAA2BF084B", tableName: "cluster") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-105") {
//		createIndex(indexName: "cluster_reference_id_unique_1368703711380", tableName: "cluster", unique: "true") {
//			column(name: "cluster_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-106") {
//		createIndex(indexName: "FKBC30B9915F412BC9", tableName: "computing_offer") {
//			column(name: "cluster_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-107") {
//		createIndex(indexName: "FKBC30B991784BC369", tableName: "computing_offer") {
//			column(name: "pod_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-108") {
//		createIndex(indexName: "FKBC30B991A2BF084B", tableName: "computing_offer") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-109") {
//		createIndex(indexName: "offer_reference_id_unique_1368703711384", tableName: "computing_offer", unique: "true") {
//			column(name: "offer_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-110") {
//		createIndex(indexName: "order_no_unique_1368703711385", tableName: "computing_offer", unique: "true") {
//			column(name: "order_no")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-111") {
//		createIndex(indexName: "FKA217635255513EE2", tableName: "computing_offer_zone_cost") {
//			column(name: "computing_offer_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-112") {
//		createIndex(indexName: "FKA2176352A2BF084B", tableName: "computing_offer_zone_cost") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-113") {
//		createIndex(indexName: "name_unique_1368703711388", tableName: "config", unique: "true") {
//			column(name: "name")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-114") {
//		createIndex(indexName: "country_alpha2code_unique_1368703711389", tableName: "country", unique: "true") {
//			column(name: "country_alpha2code")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-115") {
//		createIndex(indexName: "country_alpha3code_unique_1368703711390", tableName: "country", unique: "true") {
//			column(name: "country_alpha3code")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-116") {
//		createIndex(indexName: "country_code_unique_1368703711390", tableName: "country", unique: "true") {
//			column(name: "country_code")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-117") {
//		createIndex(indexName: "country_name_unique_1368703711390", tableName: "country", unique: "true") {
//			column(name: "country_name")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-118") {
//		createIndex(indexName: "FKAB8C213A5F412BC9", tableName: "disk_offer") {
//			column(name: "cluster_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-119") {
//		createIndex(indexName: "FKAB8C213A784BC369", tableName: "disk_offer") {
//			column(name: "pod_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-120") {
//		createIndex(indexName: "FKAB8C213AA2BF084B", tableName: "disk_offer") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-121") {
//		createIndex(indexName: "disk_offer_reference_id_unique_1368703711391", tableName: "disk_offer", unique: "true") {
//			column(name: "disk_offer_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-122") {
//		createIndex(indexName: "FKAA980BBBA2BF084B", tableName: "disk_offer_zone_cost") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-123") {
//		createIndex(indexName: "FKAA980BBBDB8B018E", tableName: "disk_offer_zone_cost") {
//			column(name: "disk_offer_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-124") {
//		createIndex(indexName: "reference_id_unique_1368703711395", tableName: "domain", unique: "true") {
//			column(name: "reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-125") {
//		createIndex(indexName: "FK5A632CFCA0981FEB", tableName: "event_log_ip_address") {
//			column(name: "user_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-126") {
//		createIndex(indexName: "FK30F5A85F412BC9", tableName: "host") {
//			column(name: "cluster_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-127") {
//		createIndex(indexName: "FK30F5A8784BC369", tableName: "host") {
//			column(name: "pod_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-128") {
//		createIndex(indexName: "FK30F5A8A2BF084B", tableName: "host") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-129") {
//		createIndex(indexName: "host_reference_id_unique_1368703711398", tableName: "host", unique: "true") {
//			column(name: "host_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-130") {
//		createIndex(indexName: "FK6CC03317A2BF084B", tableName: "hypervisor") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-131") {
//		createIndex(indexName: "FK583738DC784BC369", tableName: "ip_address") {
//			column(name: "pod_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-132") {
//		createIndex(indexName: "FK583738DCA2BF084B", tableName: "ip_address") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-133") {
//		createIndex(indexName: "end_ip_unique_1368703711400", tableName: "ip_address", unique: "true") {
//			column(name: "end_ip")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-134") {
//		createIndex(indexName: "end_ip_value_unique_1368703711400", tableName: "ip_address", unique: "true") {
//			column(name: "end_ip_value")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-135") {
//		createIndex(indexName: "start_ip_unique_1368703711401", tableName: "ip_address", unique: "true") {
//			column(name: "start_ip")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-136") {
//		createIndex(indexName: "start_ip_value_unique_1368703711401", tableName: "ip_address", unique: "true") {
//			column(name: "start_ip_value")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-137") {
//		createIndex(indexName: "FK6CE9600C4F48A26E", tableName: "miscellaneous_offer_zone_cost") {
//			column(name: "miscellaneous_offer_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-138") {
//		createIndex(indexName: "FK6CE9600C5F412BC9", tableName: "miscellaneous_offer_zone_cost") {
//			column(name: "cluster_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-139") {
//		createIndex(indexName: "FK6CE9600C784BC369", tableName: "miscellaneous_offer_zone_cost") {
//			column(name: "pod_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-140") {
//		createIndex(indexName: "FK6CE9600CA2BF084B", tableName: "miscellaneous_offer_zone_cost") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-141") {
//		createIndex(indexName: "network_reference_id_unique_1368703711409", tableName: "network_offer", unique: "true") {
//			column(name: "network_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-142") {
//		createIndex(indexName: "FK9269164CA2BF084B", tableName: "network_offer_zone_cost") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-143") {
//		createIndex(indexName: "FK9269164CDDC6F6AE", tableName: "network_offer_zone_cost") {
//			column(name: "network_offer_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-144") {
//		createIndex(indexName: "FKA6AB9B48A0981FEB", tableName: "notification_email") {
//			column(name: "user_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-145") {
//		createIndex(indexName: "email_unique_1368703711413", tableName: "notification_email", unique: "true") {
//			column(name: "email")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-146") {
//		createIndex(indexName: "os_category_id_unique_1368703711413", tableName: "os_category", unique: "true") {
//			column(name: "os_category_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-147") {
//		createIndex(indexName: "FKB98248B54322274", tableName: "os_type") {
//			column(name: "os_category_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-148") {
//		createIndex(indexName: "reference_os_type_id_unique_1368703711414", tableName: "os_type", unique: "true") {
//			column(name: "reference_os_type_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-149") {
//		createIndex(indexName: "FK1B245A2BF084B", tableName: "pod") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-150") {
//		createIndex(indexName: "pod_reference_id_unique_1368703711415", tableName: "pod", unique: "true") {
//			column(name: "pod_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-151") {
//		createIndex(indexName: "authority_unique_1368703711416", tableName: "role", unique: "true") {
//			column(name: "authority")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-152") {
//		createIndex(indexName: "FK10FAD5C4497CE94B", tableName: "snapshot") {
//			column(name: "volume_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-153") {
//		createIndex(indexName: "FK10FAD5C46BB07B74", tableName: "snapshot") {
//			column(name: "snapshot_policy_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-154") {
//		createIndex(indexName: "FK10FAD5C4A0981FEB", tableName: "snapshot") {
//			column(name: "user_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-155") {
//		createIndex(indexName: "job_unique_1368703711417", tableName: "snapshot", unique: "true") {
//			column(name: "job")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-156") {
//		createIndex(indexName: "snapshot_reference_id_unique_1368703711417", tableName: "snapshot", unique: "true") {
//			column(name: "snapshot_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-157") {
//		createIndex(indexName: "FKB5F880D497CE94B", tableName: "snapshot_policy") {
//			column(name: "volume_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-158") {
//		createIndex(indexName: "FKB5F880DA0981FEB", tableName: "snapshot_policy") {
//			column(name: "user_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-159") {
//		createIndex(indexName: "snapshot_policy_reference_id_unique_1368703711420", tableName: "snapshot_policy", unique: "true") {
//			column(name: "snapshot_policy_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-160") {
//		createIndex(indexName: "FK68AC4911965F649", tableName: "state") {
//			column(name: "country_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-161") {
//		createIndex(indexName: "FK357C63605F412BC9", tableName: "storage_pool") {
//			column(name: "cluster_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-162") {
//		createIndex(indexName: "storage_pool_reference_id_unique_1368703711422", tableName: "storage_pool", unique: "true") {
//			column(name: "storage_pool_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-163") {
//		createIndex(indexName: "FKB13ACC7A4322274", tableName: "template") {
//			column(name: "os_category_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-164") {
//		createIndex(indexName: "FKB13ACC7A9CDFF5F4", tableName: "template") {
//			column(name: "os_type_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-165") {
//		createIndex(indexName: "FKB13ACC7AA2BF084B", tableName: "template") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-166") {
//		createIndex(indexName: "FKB13ACC7AB5EBBAB", tableName: "template") {
//			column(name: "hypervisor_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-167") {
//		createIndex(indexName: "template_reference_id_unique_1368703711426", tableName: "template", unique: "true") {
//			column(name: "template_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-168") {
//		createIndex(indexName: "FK36EBCBA6E55AE9", tableName: "user") {
//			column(name: "account_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-169") {
//		createIndex(indexName: "api_key_unique_1368703711428", tableName: "user", unique: "true") {
//			column(name: "api_key")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-170") {
//		createIndex(indexName: "secret_key_unique_1368703711429", tableName: "user", unique: "true") {
//			column(name: "secret_key")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-171") {
//		createIndex(indexName: "username_unique_1368703711429", tableName: "user", unique: "true") {
//			column(name: "username")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-172") {
//		createIndex(indexName: "uuid_unique_1368703711429", tableName: "user", unique: "true") {
//			column(name: "uuid")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-173") {
//		createIndex(indexName: "FK728E7D265F6F2670", tableName: "user_event") {
//			column(name: "event_log_ip_address_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-174") {
//		createIndex(indexName: "FK728E7D26A0981FEB", tableName: "user_event") {
//			column(name: "user_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-175") {
//		createIndex(indexName: "FK143BF46AA0981FEB", tableName: "user_role") {
//			column(name: "user_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-176") {
//		createIndex(indexName: "FK143BF46AFB6D5C0B", tableName: "user_role") {
//			column(name: "role_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-177") {
//		createIndex(indexName: "FKA8EBCF1339E8DCA3", tableName: "virtual_machine") {
//			column(name: "owner_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-178") {
//		createIndex(indexName: "FKA8EBCF1355513EE2", tableName: "virtual_machine") {
//			column(name: "computing_offer_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-179") {
//		createIndex(indexName: "FKA8EBCF139C383D0B", tableName: "virtual_machine") {
//			column(name: "template_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-180") {
//		createIndex(indexName: "FKA8EBCF13A0981FEB", tableName: "virtual_machine") {
//			column(name: "user_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-181") {
//		createIndex(indexName: "FKA8EBCF13A2BF084B", tableName: "virtual_machine") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-182") {
//		createIndex(indexName: "FKA8EBCF13B5EBBAB", tableName: "virtual_machine") {
//			column(name: "hypervisor_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-183") {
//		createIndex(indexName: "FKA8EBCF13DB8B018E", tableName: "virtual_machine") {
//			column(name: "disk_offer_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-184") {
//		createIndex(indexName: "FKA8EBCF13DDC6F6AE", tableName: "virtual_machine") {
//			column(name: "network_offer_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-185") {
//		createIndex(indexName: "reference_id_unique_1368703711434", tableName: "virtual_machine", unique: "true") {
//			column(name: "reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-186") {
//		createIndex(indexName: "FKCFAAE71A39E8DCA3", tableName: "volume") {
//			column(name: "owner_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-187") {
//		createIndex(indexName: "FKCFAAE71AA0981FEB", tableName: "volume") {
//			column(name: "user_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-188") {
//		createIndex(indexName: "FKCFAAE71AA2BF084B", tableName: "volume") {
//			column(name: "zone_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-189") {
//		createIndex(indexName: "FKCFAAE71ADB8B018E", tableName: "volume") {
//			column(name: "disk_offer_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-190") {
//		createIndex(indexName: "FKCFAAE71AF6AB5554", tableName: "volume") {
//			column(name: "virtual_machine_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-191") {
//		createIndex(indexName: "job_unique_1368703711436", tableName: "volume", unique: "true") {
//			column(name: "job")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-192") {
//		createIndex(indexName: "volume_reference_id_unique_1368703711437", tableName: "volume", unique: "true") {
//			column(name: "volume_reference_id")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-193") {
//		createIndex(indexName: "alias_name_unique_1368703711438", tableName: "zone", unique: "true") {
//			column(name: "alias_name")
//		}
//	}
//
//	changeSet(author: "nandhini (generated)", id: "1368703711525-194") {
//		createIndex(indexName: "reference_zone_id_unique_1368703711439", tableName: "zone", unique: "true") {
//			column(name: "reference_zone_id")
//		}
//	}
        
    changeSet(author: "gowtham", id: "country-sql-file") {
        sqlFile( path: "country.sql")           
    }

    changeSet(author: "gowtham", id: "state-sql-file") {
        sqlFile( path: "state.sql")
    }
    
}
