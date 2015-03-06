databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1373905898508-1") {
		createTable(tableName: "account") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "accountPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_type", type: "varchar(255)") {
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

			column(name: "card_verified", type: "bit") {
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

			column(name: "credit", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "credit_limit", type: "double precision") {
				constraints(nullable: "false")
			}
                        
			column(name: "default_card", type: "varchar(255)")

			column(name: "domain_id", type: "bigint")

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_billing_data", type: "datetime")

			column(name: "last_name", type: "varchar(150)") {
				constraints(nullable: "false")
			}

			column(name: "late_fee", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "next_billing_data", type: "datetime")

			column(name: "password", type: "varchar(15)") {
				constraints(nullable: "false")
			}

			column(name: "penalty_fee", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "phone_number", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "preferred_language", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "previous_balance", type: "double precision")

			column(name: "previous_paid", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "promotional_code", type: "varchar(255)")

			column(name: "sign_up_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "state_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "street_address", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_paid", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_payable", type: "double precision") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-2") {
		createTable(tableName: "billable_item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "billable_itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "customized", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_item_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "tax_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-3") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-4") {
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

			column(name: "base_os", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "gowtham (generated)", id: "1373905898508-5") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-6") {
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

			column(name: "value", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-7") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-8") {
		createTable(tableName: "credit") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "creditPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "rel_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-9") {
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

			column(name: "max_size", type: "integer")

			column(name: "min_size", type: "integer")

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

			column(name: "storage_type", type: "varchar(255)")

			column(name: "tags", type: "varchar(255)")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-10") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-11") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-12") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-13") {
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

			column(name: "core_cpu_speed_total", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "cpu_number_total", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "host_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "host_tag", type: "varchar(255)")

			column(name: "memory_total", type: "double precision") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-14") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-15") {
		createTable(tableName: "invoice") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invoicePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billing_address_city", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "billing_address_country", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "billing_address_state", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "billing_address_street", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "billing_address_zip", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "currency", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "customer_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "due_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "last_payment", type: "double precision")

			column(name: "organisation_address_city", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "organisation_address_country", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "organisation_address_state", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "organisation_address_street", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "organisation_address_zip", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "previous_balance", type: "double precision")

			column(name: "reference_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-16") {
		createTable(tableName: "invoice_item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invoice_itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billable_item_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "invoice_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "reference_item_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_item_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "tax_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "tax_percent", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "usage_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "usage_unit_price", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "usage_units", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-17") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-18") {
		createTable(tableName: "iso") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "isoPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "base_os", type: "varchar(255)")

			column(name: "bootable", type: "bit")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "extractable", type: "bit")

			column(name: "featured", type: "bit")

			column(name: "is_public", type: "bit")

			column(name: "iso_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "job", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "os_category_id", type: "bigint")

			column(name: "os_type_id", type: "bigint")

			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-19") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-20") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-21") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-22") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-23") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-24") {
		createTable(tableName: "offering_usage") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "offering_usagPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "double precision")

			column(name: "description", type: "varchar(255)")

			column(name: "domain_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "end_date", type: "timestamp")

			column(name: "hours", type: "double precision")

			column(name: "netwotk_id", type: "varchar(255)")

			column(name: "offering_id", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)")

			column(name: "size", type: "varchar(255)")

			column(name: "start_date", type: "timestamp")

			column(name: "template_id", type: "varchar(255)")

			column(name: "usage_display", type: "varchar(255)")

			column(name: "usage_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "varchar(255)")

			column(name: "virtual_machine_name", type: "varchar(255)")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-25") {
		createTable(tableName: "os_category") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "os_categoryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "base_os", type: "varchar(255)") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-26") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-27") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-28") {
		createTable(tableName: "promotion") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "promotionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "code", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "end_date", type: "datetime")

			column(name: "max_uses", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "new_signups", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "notes", type: "varchar(1000)") {
				constraints(nullable: "false")
			}

			column(name: "recurring", type: "integer")

			column(name: "start_date", type: "datetime")

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "uses", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "value", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-29") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-30") {
		createTable(tableName: "security_group_template") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "security_grouPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "disabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "security_group_template_reference_id", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-31") {
		createTable(tableName: "security_group_template_rule") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "security_grouPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cidr", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "end_port", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "security_group_port", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "security_group_template_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "security_group_template_rule_reference_id", type: "varchar(255)")

			column(name: "security_group_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "start_port", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-32") {
		createTable(tableName: "security_group_template_zones") {
			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "security_group_template_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-33") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-34") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-35") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-36") {
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

			column(name: "tag", type: "varchar(255)")

			column(name: "total_size", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-37") {
		createTable(tableName: "tax") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "taxPK")
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

			column(name: "percentage", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-38") {
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
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-39") {
		createTable(tableName: "template_zones") {
			column(name: "template_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-40") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-41") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-42") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-43") {
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

			column(name: "iso_id", type: "bigint")

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

			column(name: "nic_ip", type: "varchar(255)")

			column(name: "owner_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)")

			column(name: "security_group_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "template_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "vm_internal_name", type: "varchar(255)")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-44") {
		createTable(tableName: "vm_usage") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vm_usagePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cpu_util", type: "double precision")

			column(name: "date", type: "datetime")

			column(name: "disk_read", type: "double precision")

			column(name: "disk_write", type: "double precision")

			column(name: "memeory_util", type: "double precision")

			column(name: "network_read", type: "double precision")

			column(name: "network_write", type: "double precision")

			column(name: "virtual_machine_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-45") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-46") {
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

	changeSet(author: "gowtham (generated)", id: "1373905898508-47") {
		addPrimaryKey(columnNames: "security_group_template_id, zone_id", tableName: "security_group_template_zones")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-48") {
		addPrimaryKey(columnNames: "template_id, zone_id", tableName: "template_zones")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-49") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

//	changeSet(author: "gowtham (generated)", id: "1373905898508-50") {
//		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FKB9D38A2DFAA15E8D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1373905898508-51") {
//		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FKB9D38A2D5F80016D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1373905898508-52") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FKB9D38A2D1965F649", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1373905898508-53") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "account", constraintName: "FKB9D38A2DD087110B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1373905898508-54") {
//		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FKB9D38A2DD0BF5829", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-55") {
		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK4A845AB17E6301A9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-56") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "cluster", constraintName: "FK33FB11FA784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-57") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "cluster", constraintName: "FK33FB11FAA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-58") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "computing_offer", constraintName: "FKBC30B9915F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-59") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "computing_offer", constraintName: "FKBC30B991784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-60") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "computing_offer", constraintName: "FKBC30B991A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-61") {
		addForeignKeyConstraint(baseColumnNames: "computing_offer_id", baseTableName: "computing_offer_zone_cost", constraintName: "FKA217635255513EE2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-62") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "computing_offer_zone_cost", constraintName: "FKA2176352A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-63") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "credit", constraintName: "FKAF65AAF9A6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-64") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "disk_offer", constraintName: "FKAB8C213A5F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-65") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "disk_offer", constraintName: "FKAB8C213A784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-66") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "disk_offer", constraintName: "FKAB8C213AA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-67") {
		addForeignKeyConstraint(baseColumnNames: "disk_offer_id", baseTableName: "disk_offer_zone_cost", constraintName: "FKAA980BBBDB8B018E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "disk_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-68") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "disk_offer_zone_cost", constraintName: "FKAA980BBBA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-69") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "event_log_ip_address", constraintName: "FK5A632CFCA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-70") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "host", constraintName: "FK30F5A85F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-71") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "host", constraintName: "FK30F5A8784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-72") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "host", constraintName: "FK30F5A8A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-73") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "hypervisor", constraintName: "FK6CC03317A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-74") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "invoice", constraintName: "FK74D6432DA6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-75") {
		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "invoice_item", constraintName: "FKC7D1E4E54C5E670E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-76") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FKC7D1E4E53BEBAE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-77") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "ip_address", constraintName: "FK583738DC784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-78") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "ip_address", constraintName: "FK583738DCA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-79") {
		addForeignKeyConstraint(baseColumnNames: "os_category_id", baseTableName: "iso", constraintName: "FK198854322274", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "os_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-80") {
		addForeignKeyConstraint(baseColumnNames: "os_type_id", baseTableName: "iso", constraintName: "FK198859CDFF5F4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "os_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-81") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "iso", constraintName: "FK19885A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-82") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600C5F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-83") {
		addForeignKeyConstraint(baseColumnNames: "miscellaneous_offer_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600C4F48A26E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "miscellaneous_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-84") {
		addForeignKeyConstraint(baseColumnNames: "pod_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600C784BC369", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pod", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-85") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK6CE9600CA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-86") {
		addForeignKeyConstraint(baseColumnNames: "network_offer_id", baseTableName: "network_offer_zone_cost", constraintName: "FK9269164CDDC6F6AE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-87") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "network_offer_zone_cost", constraintName: "FK9269164CA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-88") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "notification_email", constraintName: "FKA6AB9B48A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-89") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "offering_usage", constraintName: "FKF34D5428A6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-90") {
		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "offering_usage", constraintName: "FKF34D5428D087110B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-91") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "offering_usage", constraintName: "FKF34D5428A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-92") {
		addForeignKeyConstraint(baseColumnNames: "os_category_id", baseTableName: "os_type", constraintName: "FKB98248B54322274", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "os_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-93") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "pod", constraintName: "FK1B245A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-94") {
		addForeignKeyConstraint(baseColumnNames: "security_group_template_id", baseTableName: "security_group_template_rule", constraintName: "FKE1632262E6EF70C9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "security_group_template", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-95") {
		addForeignKeyConstraint(baseColumnNames: "security_group_template_id", baseTableName: "security_group_template_zones", constraintName: "FK4B6F33A1E6EF70C9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "security_group_template", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-96") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "security_group_template_zones", constraintName: "FK4B6F33A1A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-97") {
		addForeignKeyConstraint(baseColumnNames: "snapshot_policy_id", baseTableName: "snapshot", constraintName: "FK10FAD5C46BB07B74", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "snapshot_policy", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-98") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "snapshot", constraintName: "FK10FAD5C4A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-99") {
		addForeignKeyConstraint(baseColumnNames: "volume_id", baseTableName: "snapshot", constraintName: "FK10FAD5C4497CE94B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-100") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "snapshot_policy", constraintName: "FKB5F880DA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-101") {
		addForeignKeyConstraint(baseColumnNames: "volume_id", baseTableName: "snapshot_policy", constraintName: "FKB5F880D497CE94B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-102") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", constraintName: "FK68AC4911965F649", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-103") {
		addForeignKeyConstraint(baseColumnNames: "cluster_id", baseTableName: "storage_pool", constraintName: "FK357C63605F412BC9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cluster", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-104") {
		addForeignKeyConstraint(baseColumnNames: "hypervisor_id", baseTableName: "template", constraintName: "FKB13ACC7AB5EBBAB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "hypervisor", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-105") {
		addForeignKeyConstraint(baseColumnNames: "os_category_id", baseTableName: "template", constraintName: "FKB13ACC7A4322274", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "os_category", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-106") {
		addForeignKeyConstraint(baseColumnNames: "os_type_id", baseTableName: "template", constraintName: "FKB13ACC7A9CDFF5F4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "os_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-107") {
		addForeignKeyConstraint(baseColumnNames: "template_id", baseTableName: "template_zones", constraintName: "FKB3B1BE429C383D0B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "template", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-108") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "template_zones", constraintName: "FKB3B1BE42A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-109") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "user", constraintName: "FK36EBCBA6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-110") {
		addForeignKeyConstraint(baseColumnNames: "event_log_ip_address_id", baseTableName: "user_event", constraintName: "FK728E7D265F6F2670", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event_log_ip_address", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-111") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_event", constraintName: "FK728E7D26A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-112") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46AFB6D5C0B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-113") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-114") {
		addForeignKeyConstraint(baseColumnNames: "computing_offer_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF1355513EE2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-115") {
		addForeignKeyConstraint(baseColumnNames: "disk_offer_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13DB8B018E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "disk_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-116") {
		addForeignKeyConstraint(baseColumnNames: "hypervisor_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13B5EBBAB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "hypervisor", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-117") {
		addForeignKeyConstraint(baseColumnNames: "iso_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF136C971B69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "iso", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-118") {
		addForeignKeyConstraint(baseColumnNames: "network_offer_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13DDC6F6AE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-119") {
		addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF1339E8DCA3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-120") {
		addForeignKeyConstraint(baseColumnNames: "template_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF139C383D0B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "template", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-121") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-122") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "virtual_machine", constraintName: "FKA8EBCF13A2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-123") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "vm_usage", constraintName: "FK1094CA19F6AB5554", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-124") {
		addForeignKeyConstraint(baseColumnNames: "disk_offer_id", baseTableName: "volume", constraintName: "FKCFAAE71ADB8B018E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "disk_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-125") {
		addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "volume", constraintName: "FKCFAAE71A39E8DCA3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-126") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "volume", constraintName: "FKCFAAE71AA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-127") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "volume", constraintName: "FKCFAAE71AF6AB5554", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-128") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "volume", constraintName: "FKCFAAE71AA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

/*	changeSet(author: "gowtham (generated)", id: "1373905898508-129") {
		createIndex(indexName: "FKB9D38A2D1965F649", tableName: "account") {
			column(name: "country_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-130") {
		createIndex(indexName: "FKB9D38A2D5F80016D", tableName: "account") {
			column(name: "billing_state_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-131") {
		createIndex(indexName: "FKB9D38A2DD087110B", tableName: "account") {
			column(name: "domain_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-132") {
		createIndex(indexName: "FKB9D38A2DD0BF5829", tableName: "account") {
			column(name: "state_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-133") {
		createIndex(indexName: "FKB9D38A2DFAA15E8D", tableName: "account") {
			column(name: "billing_country_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-134") {
		createIndex(indexName: "user_name_unique_1373905898107", tableName: "account", unique: "true") {
			column(name: "user_name")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-135") {
		createIndex(indexName: "FK4A845AB17E6301A9", tableName: "billable_item") {
			column(name: "tax_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-136") {
		createIndex(indexName: "FK33FB11FA784BC369", tableName: "cluster") {
			column(name: "pod_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-137") {
		createIndex(indexName: "FK33FB11FAA2BF084B", tableName: "cluster") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-138") {
		createIndex(indexName: "cluster_reference_id_unique_1373905898119", tableName: "cluster", unique: "true") {
			column(name: "cluster_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-139") {
		createIndex(indexName: "FKBC30B9915F412BC9", tableName: "computing_offer") {
			column(name: "cluster_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-140") {
		createIndex(indexName: "FKBC30B991784BC369", tableName: "computing_offer") {
			column(name: "pod_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-141") {
		createIndex(indexName: "FKBC30B991A2BF084B", tableName: "computing_offer") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-142") {
		createIndex(indexName: "offer_reference_id_unique_1373905898124", tableName: "computing_offer", unique: "true") {
			column(name: "offer_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-143") {
		createIndex(indexName: "FKA217635255513EE2", tableName: "computing_offer_zone_cost") {
			column(name: "computing_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-144") {
		createIndex(indexName: "FKA2176352A2BF084B", tableName: "computing_offer_zone_cost") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-145") {
		createIndex(indexName: "name_unique_1373905898127", tableName: "config", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-146") {
		createIndex(indexName: "country_alpha2code_unique_1373905898128", tableName: "country", unique: "true") {
			column(name: "country_alpha2code")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-147") {
		createIndex(indexName: "country_alpha3code_unique_1373905898128", tableName: "country", unique: "true") {
			column(name: "country_alpha3code")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-148") {
		createIndex(indexName: "country_code_unique_1373905898128", tableName: "country", unique: "true") {
			column(name: "country_code")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-149") {
		createIndex(indexName: "country_name_unique_1373905898129", tableName: "country", unique: "true") {
			column(name: "country_name")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-150") {
		createIndex(indexName: "FKAF65AAF9A6E55AE9", tableName: "credit") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-151") {
		createIndex(indexName: "FKAB8C213A5F412BC9", tableName: "disk_offer") {
			column(name: "cluster_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-152") {
		createIndex(indexName: "FKAB8C213A784BC369", tableName: "disk_offer") {
			column(name: "pod_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-153") {
		createIndex(indexName: "FKAB8C213AA2BF084B", tableName: "disk_offer") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-154") {
		createIndex(indexName: "disk_offer_reference_id_unique_1373905898132", tableName: "disk_offer", unique: "true") {
			column(name: "disk_offer_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-155") {
		createIndex(indexName: "FKAA980BBBA2BF084B", tableName: "disk_offer_zone_cost") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-156") {
		createIndex(indexName: "FKAA980BBBDB8B018E", tableName: "disk_offer_zone_cost") {
			column(name: "disk_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-157") {
		createIndex(indexName: "reference_id_unique_1373905898136", tableName: "domain", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-158") {
		createIndex(indexName: "FK5A632CFCA0981FEB", tableName: "event_log_ip_address") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-159") {
		createIndex(indexName: "FK30F5A85F412BC9", tableName: "host") {
			column(name: "cluster_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-160") {
		createIndex(indexName: "FK30F5A8784BC369", tableName: "host") {
			column(name: "pod_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-161") {
		createIndex(indexName: "FK30F5A8A2BF084B", tableName: "host") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-162") {
		createIndex(indexName: "host_reference_id_unique_1373905898139", tableName: "host", unique: "true") {
			column(name: "host_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-163") {
		createIndex(indexName: "FK6CC03317A2BF084B", tableName: "hypervisor") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-164") {
		createIndex(indexName: "FK74D6432DA6E55AE9", tableName: "invoice") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-165") {
		createIndex(indexName: "FKC7D1E4E53BEBAE9", tableName: "invoice_item") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-166") {
		createIndex(indexName: "FKC7D1E4E54C5E670E", tableName: "invoice_item") {
			column(name: "billable_item_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-167") {
		createIndex(indexName: "FK583738DC784BC369", tableName: "ip_address") {
			column(name: "pod_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-168") {
		createIndex(indexName: "FK583738DCA2BF084B", tableName: "ip_address") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-169") {
		createIndex(indexName: "end_ip_unique_1373905898154", tableName: "ip_address", unique: "true") {
			column(name: "end_ip")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-170") {
		createIndex(indexName: "end_ip_value_unique_1373905898155", tableName: "ip_address", unique: "true") {
			column(name: "end_ip_value")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-171") {
		createIndex(indexName: "start_ip_unique_1373905898156", tableName: "ip_address", unique: "true") {
			column(name: "start_ip")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-172") {
		createIndex(indexName: "start_ip_value_unique_1373905898156", tableName: "ip_address", unique: "true") {
			column(name: "start_ip_value")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-173") {
		createIndex(indexName: "FK198854322274", tableName: "iso") {
			column(name: "os_category_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-174") {
		createIndex(indexName: "FK198859CDFF5F4", tableName: "iso") {
			column(name: "os_type_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-175") {
		createIndex(indexName: "FK19885A2BF084B", tableName: "iso") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-176") {
		createIndex(indexName: "iso_reference_id_unique_1373905898159", tableName: "iso", unique: "true") {
			column(name: "iso_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-177") {
		createIndex(indexName: "FK6CE9600C4F48A26E", tableName: "miscellaneous_offer_zone_cost") {
			column(name: "miscellaneous_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-178") {
		createIndex(indexName: "FK6CE9600C5F412BC9", tableName: "miscellaneous_offer_zone_cost") {
			column(name: "cluster_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-179") {
		createIndex(indexName: "FK6CE9600C784BC369", tableName: "miscellaneous_offer_zone_cost") {
			column(name: "pod_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-180") {
		createIndex(indexName: "FK6CE9600CA2BF084B", tableName: "miscellaneous_offer_zone_cost") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-181") {
		createIndex(indexName: "network_reference_id_unique_1373905898166", tableName: "network_offer", unique: "true") {
			column(name: "network_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-182") {
		createIndex(indexName: "FK9269164CA2BF084B", tableName: "network_offer_zone_cost") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-183") {
		createIndex(indexName: "FK9269164CDDC6F6AE", tableName: "network_offer_zone_cost") {
			column(name: "network_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-184") {
		createIndex(indexName: "FKA6AB9B48A0981FEB", tableName: "notification_email") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-185") {
		createIndex(indexName: "email_unique_1373905898170", tableName: "notification_email", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-186") {
		createIndex(indexName: "FKF34D5428A2BF084B", tableName: "offering_usage") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-187") {
		createIndex(indexName: "FKF34D5428A6E55AE9", tableName: "offering_usage") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-188") {
		createIndex(indexName: "FKF34D5428D087110B", tableName: "offering_usage") {
			column(name: "domain_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-189") {
		createIndex(indexName: "os_category_id_unique_1373905898177", tableName: "os_category", unique: "true") {
			column(name: "os_category_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-190") {
		createIndex(indexName: "FKB98248B54322274", tableName: "os_type") {
			column(name: "os_category_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-191") {
		createIndex(indexName: "reference_os_type_id_unique_1373905898178", tableName: "os_type", unique: "true") {
			column(name: "reference_os_type_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-192") {
		createIndex(indexName: "FK1B245A2BF084B", tableName: "pod") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-193") {
		createIndex(indexName: "pod_reference_id_unique_1373905898179", tableName: "pod", unique: "true") {
			column(name: "pod_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-194") {
		createIndex(indexName: "code_unique_1373905898180", tableName: "promotion", unique: "true") {
			column(name: "code")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-195") {
		createIndex(indexName: "authority_unique_1373905898183", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-196") {
		createIndex(indexName: "name_unique_1373905898184", tableName: "security_group_template", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-197") {
		createIndex(indexName: "security_group_template_reference_id_unique_1373905898184", tableName: "security_group_template", unique: "true") {
			column(name: "security_group_template_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-198") {
		createIndex(indexName: "FKE1632262E6EF70C9", tableName: "security_group_template_rule") {
			column(name: "security_group_template_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-199") {
		createIndex(indexName: "security_group_template_rule_reference_id_unique_1373905898185", tableName: "security_group_template_rule", unique: "true") {
			column(name: "security_group_template_rule_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-200") {
		createIndex(indexName: "FK4B6F33A1A2BF084B", tableName: "security_group_template_zones") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-201") {
		createIndex(indexName: "FK4B6F33A1E6EF70C9", tableName: "security_group_template_zones") {
			column(name: "security_group_template_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-202") {
		createIndex(indexName: "FK10FAD5C4497CE94B", tableName: "snapshot") {
			column(name: "volume_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-203") {
		createIndex(indexName: "FK10FAD5C46BB07B74", tableName: "snapshot") {
			column(name: "snapshot_policy_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-204") {
		createIndex(indexName: "FK10FAD5C4A0981FEB", tableName: "snapshot") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-205") {
		createIndex(indexName: "job_unique_1373905898191", tableName: "snapshot", unique: "true") {
			column(name: "job")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-206") {
		createIndex(indexName: "snapshot_reference_id_unique_1373905898192", tableName: "snapshot", unique: "true") {
			column(name: "snapshot_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-207") {
		createIndex(indexName: "FKB5F880D497CE94B", tableName: "snapshot_policy") {
			column(name: "volume_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-208") {
		createIndex(indexName: "FKB5F880DA0981FEB", tableName: "snapshot_policy") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-209") {
		createIndex(indexName: "snapshot_policy_reference_id_unique_1373905898194", tableName: "snapshot_policy", unique: "true") {
			column(name: "snapshot_policy_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-210") {
		createIndex(indexName: "FK68AC4911965F649", tableName: "state") {
			column(name: "country_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-211") {
		createIndex(indexName: "FK357C63605F412BC9", tableName: "storage_pool") {
			column(name: "cluster_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-212") {
		createIndex(indexName: "storage_pool_reference_id_unique_1373905898198", tableName: "storage_pool", unique: "true") {
			column(name: "storage_pool_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-213") {
		createIndex(indexName: "name_unique_1373905898200", tableName: "tax", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-214") {
		createIndex(indexName: "FKB13ACC7A4322274", tableName: "template") {
			column(name: "os_category_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-215") {
		createIndex(indexName: "FKB13ACC7A9CDFF5F4", tableName: "template") {
			column(name: "os_type_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-216") {
		createIndex(indexName: "FKB13ACC7AB5EBBAB", tableName: "template") {
			column(name: "hypervisor_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-217") {
		createIndex(indexName: "template_reference_id_unique_1373905898204", tableName: "template", unique: "true") {
			column(name: "template_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-218") {
		createIndex(indexName: "FKB3B1BE429C383D0B", tableName: "template_zones") {
			column(name: "template_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-219") {
		createIndex(indexName: "FKB3B1BE42A2BF084B", tableName: "template_zones") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-220") {
		createIndex(indexName: "FK36EBCBA6E55AE9", tableName: "user") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-221") {
		createIndex(indexName: "api_key_unique_1373905898206", tableName: "user", unique: "true") {
			column(name: "api_key")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-222") {
		createIndex(indexName: "secret_key_unique_1373905898207", tableName: "user", unique: "true") {
			column(name: "secret_key")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-223") {
		createIndex(indexName: "username_unique_1373905898208", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-224") {
		createIndex(indexName: "uuid_unique_1373905898208", tableName: "user", unique: "true") {
			column(name: "uuid")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-225") {
		createIndex(indexName: "FK728E7D265F6F2670", tableName: "user_event") {
			column(name: "event_log_ip_address_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-226") {
		createIndex(indexName: "FK728E7D26A0981FEB", tableName: "user_event") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-227") {
		createIndex(indexName: "FK143BF46AA0981FEB", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-228") {
		createIndex(indexName: "FK143BF46AFB6D5C0B", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-229") {
		createIndex(indexName: "FKA8EBCF1339E8DCA3", tableName: "virtual_machine") {
			column(name: "owner_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-230") {
		createIndex(indexName: "FKA8EBCF1355513EE2", tableName: "virtual_machine") {
			column(name: "computing_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-231") {
		createIndex(indexName: "FKA8EBCF136C971B69", tableName: "virtual_machine") {
			column(name: "iso_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-232") {
		createIndex(indexName: "FKA8EBCF139C383D0B", tableName: "virtual_machine") {
			column(name: "template_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-233") {
		createIndex(indexName: "FKA8EBCF13A0981FEB", tableName: "virtual_machine") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-234") {
		createIndex(indexName: "FKA8EBCF13A2BF084B", tableName: "virtual_machine") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-235") {
		createIndex(indexName: "FKA8EBCF13B5EBBAB", tableName: "virtual_machine") {
			column(name: "hypervisor_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-236") {
		createIndex(indexName: "FKA8EBCF13DB8B018E", tableName: "virtual_machine") {
			column(name: "disk_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-237") {
		createIndex(indexName: "FKA8EBCF13DDC6F6AE", tableName: "virtual_machine") {
			column(name: "network_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-238") {
		createIndex(indexName: "reference_id_unique_1373905898214", tableName: "virtual_machine", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-239") {
		createIndex(indexName: "FK1094CA19F6AB5554", tableName: "vm_usage") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-240") {
		createIndex(indexName: "FKCFAAE71A39E8DCA3", tableName: "volume") {
			column(name: "owner_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-241") {
		createIndex(indexName: "FKCFAAE71AA0981FEB", tableName: "volume") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-242") {
		createIndex(indexName: "FKCFAAE71AA2BF084B", tableName: "volume") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-243") {
		createIndex(indexName: "FKCFAAE71ADB8B018E", tableName: "volume") {
			column(name: "disk_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-244") {
		createIndex(indexName: "FKCFAAE71AF6AB5554", tableName: "volume") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-245") {
		createIndex(indexName: "job_unique_1373905898218", tableName: "volume", unique: "true") {
			column(name: "job")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-246") {
		createIndex(indexName: "volume_reference_id_unique_1373905898220", tableName: "volume", unique: "true") {
			column(name: "volume_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-247") {
		createIndex(indexName: "alias_name_unique_1373905898221", tableName: "zone", unique: "true") {
			column(name: "alias_name")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1373905898508-248") {
		createIndex(indexName: "reference_zone_id_unique_1373905898222", tableName: "zone", unique: "true") {
			column(name: "reference_zone_id")
		}
	} */
        
    
        changeSet(author: "gowtham", id: "country-sql-file") {
            sqlFile( path: "country.sql")           
        }

        changeSet(author: "gowtham", id: "state-sql-file") {
            sqlFile( path: "state.sql")
        }
}
