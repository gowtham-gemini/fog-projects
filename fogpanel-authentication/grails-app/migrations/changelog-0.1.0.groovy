databaseChangeLog = {

//    changeSet(author: "deveops (generated)", id: "1418801802074-1") {
//		addColumn(tableName: "account") {
//			column(name: "account_identifier", type: "varchar(255)") {
//				constraints(nullable: "false")
//			}
//		}
//	}
//        
//    changeSet(author: "developer (generated)", id: "1418801802074-15") {
//		createIndex(indexName: "unique_account_identifier", tableName: "account", unique: "true") {
//			column(name: "account_identifier")
//		}
//	}
	changeSet(author: "deveops (generated)", id: "1423646573339-1") {
		createTable(tableName: "account") {                   
                    

			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "accountPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_identifier", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "account_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "activation_date", type: "datetime")

			column(name: "auto_payment_attempt", type: "integer")

			column(name: "bandwidth_limit", type: "varchar(255)")

			column(name: "billing_city", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "billing_country_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billing_phone_number", type: "varchar(20)") {
				constraints(nullable: "false")
			}

			column(name: "billing_state_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billing_street_address", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "billing_street_address1", type: "varchar(255)")

			column(name: "billing_zip", type: "varchar(10)") {
				constraints(nullable: "false")
			}

			column(name: "card_expiry_month", type: "double precision")

			column(name: "card_expiry_year", type: "double precision")

			column(name: "card_verified", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "company_name", type: "varchar(255)")

			column(name: "country_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "credit", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "credit_limit", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "credit_limit_level", type: "varchar(255)")

			column(name: "default_card", type: "varchar(255)")

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

			column(name: "last_usage_run_date", type: "datetime")

			column(name: "late_fee", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "next_billing_data", type: "datetime")

			column(name: "penalty_fee", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "phone_number", type: "varchar(20)") {
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

			column(name: "snapshot_limit", type: "varchar(255)")

			column(name: "state_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "storage_limit", type: "varchar(255)")

			column(name: "street_address", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "street_address1", type: "varchar(255)")

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_paid", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_payable", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "uuid", type: "varchar(255)")

			column(name: "vm_limit", type: "varchar(255)")

			column(name: "vm_snap_usage_date", type: "datetime")

			column(name: "zip", type: "varchar(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-2") {
		createTable(tableName: "api_user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "api_userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "api_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "secret", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-3") {
		createTable(tableName: "app_version") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "app_versionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "checksum", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "fog_version", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "initialize_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-4") {
		createTable(tableName: "asynchronous_jobs") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "asynchronous_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

			column(name: "completed_at", type: "datetime")

			column(name: "created_at", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "job_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "started_at", type: "datetime")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-5") {
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

			column(name: "discountable", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "has_zone", type: "bit") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-6") {
		createTable(tableName: "cloud_maintenance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cloud_maintenPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(5000)") {
				constraints(nullable: "false")
			}

			column(name: "mail_send", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-7") {
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

			column(name: "value", type: "longtext") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-8") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-9") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-10") {
		createTable(tableName: "discount") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "discountPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billing_cycles", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "discount_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "end_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "is_all", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "is_all_plan", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "is_all_user", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "start_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "sub_type", type: "varchar(255)")

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "value", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-11") {
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

			column(name: "is_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "lock_time", type: "datetime")

			column(name: "over_all_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "over_all_failure_count", type: "integer") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-12") {
		createTable(tableName: "flavors") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "flavorsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "clock_speed", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "family", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "instance_store", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_disabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "is_ebs_optimized", type: "varchar(255)") {
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

			column(name: "rootgb", type: "integer")

			column(name: "vcpus", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-13") {
		createTable(tableName: "images") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "imagesPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

			column(name: "architecture", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "description", type: "varchar(255)")

			column(name: "hypervisor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "image_owner_alias", type: "varchar(255)")

			column(name: "image_type", type: "varchar(255)")

			column(name: "is_public", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "isvmsnapshot", type: "bit")

			column(name: "kernel_id", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "one_time_chargeable", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "owner_id", type: "varchar(255)")

			column(name: "platform", type: "varchar(255)")

			column(name: "ramdisk_id", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "root_device_name", type: "varchar(255)")

			column(name: "root_device_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")

			column(name: "virtual_machine_id", type: "bigint")

			column(name: "virtualization_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-14") {
		createTable(tableName: "invitation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invitationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "link", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-15") {
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

			column(name: "credit", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "currency", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "current_due", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "customer_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "due_date", type: "datetime")

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

			column(name: "payment", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "previous_balance", type: "double precision")

			column(name: "previous_invoice_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "previous_invoice_id", type: "bigint")

			column(name: "reference_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "refund_amount", type: "double precision") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-16") {
		createTable(tableName: "invoice_item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invoice_itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billable_item_id", type: "bigint")

			column(name: "discount_id", type: "bigint")

			column(name: "discount_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "discount_percent", type: "double precision") {
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

			column(name: "reference_plan_id", type: "varchar(255)")

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

			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-17") {
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

			column(name: "start_ip", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "start_ip_value", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-18") {
		createTable(tableName: "job_properties") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "job_propertiePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "asynchronous_jobs_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "job_status", type: "varchar(255)") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-19") {
		createTable(tableName: "log") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "logPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "credit_limit_percentage", type: "varchar(255)")

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "mail_send", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "viewed", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-20") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-21") {
		createTable(tableName: "notification_topic") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "notification_PK")
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

			column(name: "name", type: "varchar(255)")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-22") {
		createTable(tableName: "payment_gateways") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "payment_gatewPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "gateway_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "gateway_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "gatewayurl", type: "varchar(255)")

			column(name: "include_exclude", type: "varchar(255)")

			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "processing_fee_amount", type: "double precision")

			column(name: "processing_fee_percent", type: "double precision")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-23") {
		createTable(tableName: "payments") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "paymentsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "double precision")

			column(name: "gateway_name", type: "varchar(10000)")

			column(name: "manual_payment_code", type: "varchar(255)")

			column(name: "payment_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "payment_load", type: "varchar(10000)")

			column(name: "payment_status", type: "varchar(10000)")

			column(name: "payment_token", type: "varchar(255)")

			column(name: "processing_fee", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-24") {
		createTable(tableName: "port") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "portPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "device_id", type: "varchar(255)")

			column(name: "device_owner", type: "varchar(255)")

			column(name: "fixed_ips", type: "varchar(255)")

			column(name: "is_admin_state", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "mac_address", type: "varchar(255)")

			column(name: "name", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-25") {
		createTable(tableName: "pre_defined_reply") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pre_defined_rPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "content", type: "varchar(5000)") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "department_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "subject", type: "varchar(5000)")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-26") {
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

			column(name: "notes", type: "varchar(1000)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "deveops (generated)", id: "1423646573339-27") {
		createTable(tableName: "recurring_item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "recurring_itePK")
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

			column(name: "billable_item_id", type: "bigint")

			column(name: "billing_cycles", type: "integer") {
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

			column(name: "tax_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "tax_percent", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "uses", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-28") {
		createTable(tableName: "refund") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "refundPK")
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

			column(name: "description", type: "varchar(5000)")

			column(name: "invoice_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-29") {
		createTable(tableName: "region") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "regionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-30") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-31") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-32") {
		createTable(tableName: "support_department") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "support_deparPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-33") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-34") {
		createTable(tableName: "ticket") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ticketPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "content", type: "varchar(5000)") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "department_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "priority", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "subject", type: "varchar(5000)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-35") {
		createTable(tableName: "ticket_details") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ticket_detailPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "content", type: "varchar(5000)") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "subject", type: "varchar(5000)")

			column(name: "ticket_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "viewed", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-36") {
		createTable(tableName: "topic_subscriber") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "topic_subscriPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "subscribed_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "topic_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "unsubscribed_date", type: "datetime")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-37") {
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

			column(name: "currency_format", type: "varchar(255)")

			column(name: "date_format", type: "varchar(255)")

			column(name: "email", type: "varchar(255)")

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "failure_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "varchar(150)") {
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

			column(name: "token_expiry_date", type: "datetime")

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "uuid", type: "varchar(255)")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-38") {
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

	changeSet(author: "deveops (generated)", id: "1423646573339-39") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-40") {
		createTable(tableName: "useripaddress") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "useripaddressPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

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

			column(name: "isvpclbadded", type: "bit")

			column(name: "isvpcpfadded", type: "bit")

			column(name: "job_id", type: "varchar(255)")

			column(name: "mac_address", type: "varchar(255)")

			column(name: "publicipaddress", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "removed", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "revoke_date", type: "datetime")

			column(name: "state", type: "varchar(255)")

			column(name: "user_id", type: "bigint")

			column(name: "vlan_name", type: "varchar(255)")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-41") {
		createTable(tableName: "virtual_machine") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "virtual_machiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit")

			column(name: "deleted_at", type: "datetime")

			column(name: "first_run", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "image_id", type: "bigint")

			column(name: "monitoring_enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-42") {
		createTable(tableName: "zone") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "zonePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "available", type: "bit") {
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
		}
                

	}

	changeSet(author: "deveops (generated)", id: "1423646573339-43") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-94") {
		createIndex(indexName: "FK_296wkqwal09diune025bhqmy3", tableName: "account") {
			column(name: "billing_country_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-95") {
		createIndex(indexName: "FK_5ta5bijgwndhkhsv98kv5i6js", tableName: "account") {
			column(name: "country_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-96") {
		createIndex(indexName: "FK_99vgsqatibaqbaar8onngjijq", tableName: "account") {
			column(name: "state_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-97") {
		createIndex(indexName: "FK_e0e4fj7pb4j008n57dl563yg0", tableName: "account") {
			column(name: "billing_state_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-98") {
		createIndex(indexName: "FK_6amqsmb5hmim0r67uoums68m4", tableName: "api_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-99") {
		createIndex(indexName: "fog_version_uniq_1423646573221", tableName: "app_version", unique: "true") {
			column(name: "fog_version")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-100") {
		createIndex(indexName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", tableName: "asynchronous_jobs") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-101") {
		createIndex(indexName: "FK_gh68tl37tghxw6il33ih4ym3y", tableName: "asynchronous_jobs") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-102") {
		createIndex(indexName: "FK_c0mjsdvfmtqmy4sumao1nyuko", tableName: "billable_item") {
			column(name: "tax_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-103") {
		createIndex(indexName: "name_uniq_1423646573227", tableName: "config", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-104") {
		createIndex(indexName: "country_alpha2code_uniq_1423646573228", tableName: "country", unique: "true") {
			column(name: "country_alpha2code")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-105") {
		createIndex(indexName: "country_alpha3code_uniq_1423646573228", tableName: "country", unique: "true") {
			column(name: "country_alpha3code")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-106") {
		createIndex(indexName: "country_code_uniq_1423646573228", tableName: "country", unique: "true") {
			column(name: "country_code")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-107") {
		createIndex(indexName: "country_name_uniq_1423646573228", tableName: "country", unique: "true") {
			column(name: "country_name")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-108") {
		createIndex(indexName: "FK_2s9bu4qkeqw92ljwog2l2jbhc", tableName: "credit") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-109") {
		createIndex(indexName: "FK_q50ke4gi3hj9vrp8tfhyj68ut", tableName: "event_log_ip_address") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-110") {
		createIndex(indexName: "FK_a7njc8t40e0k9u77qm293y5uf", tableName: "images") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-111") {
		createIndex(indexName: "FK_gn0kkmw9cx9tbd2bwc6xxbqr7", tableName: "images") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-112") {
		createIndex(indexName: "FK_s0yc5n7522hacknlrw8md1788", tableName: "images") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-113") {
		createIndex(indexName: "FK_gqf8jc7qmukdqpr60mahwohkf", tableName: "invoice") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-114") {
		createIndex(indexName: "FK_1fyctkp1kfh1hnjebvdv7509o", tableName: "invoice_item") {
			column(name: "billable_item_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-115") {
		createIndex(indexName: "FK_n4tkekuqyf82cfbb27fq0m2nt", tableName: "invoice_item") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-116") {
		createIndex(indexName: "FK_qqpp7tk30sghhnj37eioxyu2e", tableName: "invoice_item") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-117") {
		createIndex(indexName: "FK_sqwxdmari66hc2jvifdfndji5", tableName: "invoice_item") {
			column(name: "discount_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-118") {
		createIndex(indexName: "end_ip_uniq_1423646573245", tableName: "ip_address", unique: "true") {
			column(name: "end_ip")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-119") {
		createIndex(indexName: "end_ip_value_uniq_1423646573246", tableName: "ip_address", unique: "true") {
			column(name: "end_ip_value")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-120") {
		createIndex(indexName: "start_ip_uniq_1423646573246", tableName: "ip_address", unique: "true") {
			column(name: "start_ip")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-121") {
		createIndex(indexName: "start_ip_value_uniq_1423646573246", tableName: "ip_address", unique: "true") {
			column(name: "start_ip_value")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-122") {
		createIndex(indexName: "FK_c1l0okjaxj98xu2uqdox69f1k", tableName: "job_properties") {
			column(name: "asynchronous_jobs_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-123") {
		createIndex(indexName: "FK_7vj7qw714561rssch090tw994", tableName: "log") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-124") {
		createIndex(indexName: "FK_8lt309ogyiek75y7v92jvl08j", tableName: "log") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-125") {
		createIndex(indexName: "FK_kt7hnqia904v6xyuhg8ehqhgo", tableName: "notification_email") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-126") {
		createIndex(indexName: "email_uniq_1423646573249", tableName: "notification_email", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-127") {
		createIndex(indexName: "FK_by6p4a7fc0qymohyjnwk3ixhp", tableName: "notification_topic") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-128") {
		createIndex(indexName: "gateway_name_uniq_1423646573251", tableName: "payment_gateways", unique: "true") {
			column(name: "gateway_name")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-129") {
		createIndex(indexName: "FK_c5tb3hb9rkqo2wknmh1vwjupp", tableName: "payments") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-130") {
		createIndex(indexName: "FK_9kw6ofdijmxu07iim08bhygy3", tableName: "port") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-131") {
		createIndex(indexName: "FK_dlh5lt412h1p6kfoa1t9hnlo5", tableName: "port") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-132") {
		createIndex(indexName: "FK_3hgs3kmg1o1oixkoxvay4msol", tableName: "pre_defined_reply") {
			column(name: "department_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-133") {
		createIndex(indexName: "code_uniq_1423646573255", tableName: "promotion", unique: "true") {
			column(name: "code")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-134") {
		createIndex(indexName: "FK_il9q6akq4xtwq0rkffo16gfdf", tableName: "recurring_item") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-135") {
		createIndex(indexName: "FK_q53ejpv2l160jaficmlntmlsp", tableName: "recurring_item") {
			column(name: "billable_item_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-136") {
		createIndex(indexName: "FK_jy59hqg8ytdrt7jq5yo92l949", tableName: "refund") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-137") {
		createIndex(indexName: "FK_pkwls1sxj8t8gda8b02nfy853", tableName: "refund") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-138") {
		createIndex(indexName: "authority_uniq_1423646573258", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-139") {
		createIndex(indexName: "FK_lxoqjm8644epv72af3k3jpalx", tableName: "state") {
			column(name: "country_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-140") {
		createIndex(indexName: "name_uniq_1423646573259", tableName: "tax", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-141") {
		createIndex(indexName: "FK_95pw196xd8iy111lhdx7cqfo5", tableName: "ticket") {
			column(name: "department_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-142") {
		createIndex(indexName: "FK_i0i7rws9vvh121bg8mibj73pe", tableName: "ticket") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-143") {
		createIndex(indexName: "FK_rexowx31mw2y20hpir77jhvw9", tableName: "ticket") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-144") {
		createIndex(indexName: "FK_7n880nh99fxukrji0ojak7o7u", tableName: "ticket_details") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-145") {
		createIndex(indexName: "FK_b5xfq2niwdc9o225oge7j6165", tableName: "ticket_details") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-146") {
		createIndex(indexName: "FK_q4lalqxpdkp17sn9ays2i5kj0", tableName: "ticket_details") {
			column(name: "ticket_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-147") {
		createIndex(indexName: "FK_64takrjrph8hyxi3il62d2shq", tableName: "topic_subscriber") {
			column(name: "topic_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-148") {
		createIndex(indexName: "unique_email", tableName: "topic_subscriber", unique: "true") {
			column(name: "topic_id")

			column(name: "email")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-149") {
		createIndex(indexName: "FK_nrrhhb0bsexvi8ch6wnon9uog", tableName: "user") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-150") {
		createIndex(indexName: "api_key_uniq_1423646573269", tableName: "user", unique: "true") {
			column(name: "api_key")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-151") {
		createIndex(indexName: "secret_key_uniq_1423646573269", tableName: "user", unique: "true") {
			column(name: "secret_key")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-152") {
		createIndex(indexName: "uuid_uniq_1423646573270", tableName: "user", unique: "true") {
			column(name: "uuid")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-153") {
		createIndex(indexName: "FK_42m8yq0e7xufy32bqrd2cjfkn", tableName: "user_event") {
			column(name: "event_log_ip_address_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-154") {
		createIndex(indexName: "FK_rfoo0jivhbf3voqwj6nc8vllv", tableName: "user_event") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-155") {
		createIndex(indexName: "FK_apcc8lxk2xnug8377fatvbn04", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-156") {
		createIndex(indexName: "FK_it77eq964jhfqtu54081ebtio", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-157") {
		createIndex(indexName: "FK_7jxo321w37v6bl30a4mpei2e1", tableName: "useripaddress") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-158") {
		createIndex(indexName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", tableName: "useripaddress") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-159") {
		createIndex(indexName: "ip_reference_id_uniq_1423646573271", tableName: "useripaddress", unique: "true") {
			column(name: "ip_reference_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-160") {
		createIndex(indexName: "FK_4jt39p05i8q4w4d3vabwrtadf", tableName: "virtual_machine") {
			column(name: "account_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-161") {
		createIndex(indexName: "FK_7nk7ln6js0lj710qyq6v8ahi2", tableName: "virtual_machine") {
			column(name: "image_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-162") {
		createIndex(indexName: "FK_g7m5i040mh3pxr8onlhtqpkx7", tableName: "virtual_machine") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-163") {
		createIndex(indexName: "FK_tqtkdro59c0ovnlsyofket2sj", tableName: "virtual_machine") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-44") {
		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FK_296wkqwal09diune025bhqmy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-45") {
		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FK_e0e4fj7pb4j008n57dl563yg0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-46") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FK_5ta5bijgwndhkhsv98kv5i6js", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-47") {
		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FK_99vgsqatibaqbaar8onngjijq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-48") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "api_user", constraintName: "FK_6amqsmb5hmim0r67uoums68m4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-49") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "asynchronous_jobs", constraintName: "FK_gh68tl37tghxw6il33ih4ym3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-50") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "asynchronous_jobs", constraintName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-51") {
		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK_c0mjsdvfmtqmy4sumao1nyuko", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-52") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "credit", constraintName: "FK_2s9bu4qkeqw92ljwog2l2jbhc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-53") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "event_log_ip_address", constraintName: "FK_q50ke4gi3hj9vrp8tfhyj68ut", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-54") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "images", constraintName: "FK_a7njc8t40e0k9u77qm293y5uf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-55") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "images", constraintName: "FK_gn0kkmw9cx9tbd2bwc6xxbqr7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-56") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "images", constraintName: "FK_s0yc5n7522hacknlrw8md1788", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-57") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "invoice", constraintName: "FK_gqf8jc7qmukdqpr60mahwohkf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-58") {
		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "invoice_item", constraintName: "FK_1fyctkp1kfh1hnjebvdv7509o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-59") {
		addForeignKeyConstraint(baseColumnNames: "discount_id", baseTableName: "invoice_item", constraintName: "FK_sqwxdmari66hc2jvifdfndji5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "discount", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-60") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FK_qqpp7tk30sghhnj37eioxyu2e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-61") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "invoice_item", constraintName: "FK_n4tkekuqyf82cfbb27fq0m2nt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-62") {
		addForeignKeyConstraint(baseColumnNames: "asynchronous_jobs_id", baseTableName: "job_properties", constraintName: "FK_c1l0okjaxj98xu2uqdox69f1k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "asynchronous_jobs", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-63") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "log", constraintName: "FK_7vj7qw714561rssch090tw994", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-64") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "log", constraintName: "FK_8lt309ogyiek75y7v92jvl08j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-65") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "notification_email", constraintName: "FK_kt7hnqia904v6xyuhg8ehqhgo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-66") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "notification_topic", constraintName: "FK_by6p4a7fc0qymohyjnwk3ixhp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-67") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "payments", constraintName: "FK_c5tb3hb9rkqo2wknmh1vwjupp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-68") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "port", constraintName: "FK_9kw6ofdijmxu07iim08bhygy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-69") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "port", constraintName: "FK_dlh5lt412h1p6kfoa1t9hnlo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-70") {
		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "pre_defined_reply", constraintName: "FK_3hgs3kmg1o1oixkoxvay4msol", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-71") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "recurring_item", constraintName: "FK_il9q6akq4xtwq0rkffo16gfdf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-72") {
		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "recurring_item", constraintName: "FK_q53ejpv2l160jaficmlntmlsp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-73") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "refund", constraintName: "FK_jy59hqg8ytdrt7jq5yo92l949", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-74") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "refund", constraintName: "FK_pkwls1sxj8t8gda8b02nfy853", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-75") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", constraintName: "FK_lxoqjm8644epv72af3k3jpalx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-76") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket", constraintName: "FK_rexowx31mw2y20hpir77jhvw9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-77") {
		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "ticket", constraintName: "FK_95pw196xd8iy111lhdx7cqfo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-78") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket", constraintName: "FK_i0i7rws9vvh121bg8mibj73pe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-79") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket_details", constraintName: "FK_b5xfq2niwdc9o225oge7j6165", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-80") {
		addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "ticket_details", constraintName: "FK_q4lalqxpdkp17sn9ays2i5kj0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ticket", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-81") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket_details", constraintName: "FK_7n880nh99fxukrji0ojak7o7u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-82") {
		addForeignKeyConstraint(baseColumnNames: "topic_id", baseTableName: "topic_subscriber", constraintName: "FK_64takrjrph8hyxi3il62d2shq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "notification_topic", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-83") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "user", constraintName: "FK_nrrhhb0bsexvi8ch6wnon9uog", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-84") {
		addForeignKeyConstraint(baseColumnNames: "event_log_ip_address_id", baseTableName: "user_event", constraintName: "FK_42m8yq0e7xufy32bqrd2cjfkn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event_log_ip_address", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-85") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_event", constraintName: "FK_rfoo0jivhbf3voqwj6nc8vllv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-86") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-87") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-88") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "useripaddress", constraintName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-89") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "useripaddress", constraintName: "FK_7jxo321w37v6bl30a4mpei2e1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-90") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "virtual_machine", constraintName: "FK_4jt39p05i8q4w4d3vabwrtadf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-91") {
		addForeignKeyConstraint(baseColumnNames: "image_id", baseTableName: "virtual_machine", constraintName: "FK_7nk7ln6js0lj710qyq6v8ahi2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "images", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-92") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "virtual_machine", constraintName: "FK_tqtkdro59c0ovnlsyofket2sj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "deveops (generated)", id: "1423646573339-93") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "virtual_machine", constraintName: "FK_g7m5i040mh3pxr8onlhtqpkx7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
