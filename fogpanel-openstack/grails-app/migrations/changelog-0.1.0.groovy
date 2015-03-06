databaseChangeLog = {

	changeSet(author: "lakshmi (generated)", id: "1412148545335-1") {
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

			column(name: "cloud_password", type: "varchar(255)") {
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

			column(name: "password", type: "varchar(15)") {
				constraints(nullable: "false")
			}

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

			column(name: "user_name", type: "varchar(50)") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-2") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-3") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-4") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-5") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-6") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-7") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-8") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-9") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-10") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-11") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-12") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-13") {
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
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-14") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-15") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-16") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-17") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-18") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-19") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-20") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-21") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-22") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-23") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-24") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-25") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-26") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-27") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-28") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-29") {
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

			column(name: "token_expiry_date", type: "datetime")

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "uuid", type: "varchar(255)")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-30") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-31") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-32") {
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

	changeSet(author: "lakshmi (generated)", id: "1412148545335-33") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-69") {
		createIndex(indexName: "FK_296wkqwal09diune025bhqmy3", tableName: "account") {
			column(name: "billing_country_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-70") {
		createIndex(indexName: "FK_5ta5bijgwndhkhsv98kv5i6js", tableName: "account") {
			column(name: "country_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-71") {
		createIndex(indexName: "FK_99vgsqatibaqbaar8onngjijq", tableName: "account") {
			column(name: "state_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-72") {
		createIndex(indexName: "FK_e0e4fj7pb4j008n57dl563yg0", tableName: "account") {
			column(name: "billing_state_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-73") {
		createIndex(indexName: "user_name_uniq_1412148545228", tableName: "account", unique: "true") {
			column(name: "user_name")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-74") {
		createIndex(indexName: "FK_6amqsmb5hmim0r67uoums68m4", tableName: "api_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-75") {
		createIndex(indexName: "fog_version_uniq_1412148545233", tableName: "app_version", unique: "true") {
			column(name: "fog_version")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-76") {
		createIndex(indexName: "FK_c0mjsdvfmtqmy4sumao1nyuko", tableName: "billable_item") {
			column(name: "tax_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-77") {
		createIndex(indexName: "name_uniq_1412148545235", tableName: "config", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-78") {
		createIndex(indexName: "country_alpha2code_uniq_1412148545236", tableName: "country", unique: "true") {
			column(name: "country_alpha2code")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-79") {
		createIndex(indexName: "country_alpha3code_uniq_1412148545236", tableName: "country", unique: "true") {
			column(name: "country_alpha3code")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-80") {
		createIndex(indexName: "country_code_uniq_1412148545236", tableName: "country", unique: "true") {
			column(name: "country_code")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-81") {
		createIndex(indexName: "country_name_uniq_1412148545236", tableName: "country", unique: "true") {
			column(name: "country_name")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-82") {
		createIndex(indexName: "FK_2s9bu4qkeqw92ljwog2l2jbhc", tableName: "credit") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-83") {
		createIndex(indexName: "FK_q50ke4gi3hj9vrp8tfhyj68ut", tableName: "event_log_ip_address") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-84") {
		createIndex(indexName: "email_uniq_1412148545240", tableName: "invitation", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-85") {
		createIndex(indexName: "FK_gqf8jc7qmukdqpr60mahwohkf", tableName: "invoice") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-86") {
		createIndex(indexName: "FK_1fyctkp1kfh1hnjebvdv7509o", tableName: "invoice_item") {
			column(name: "billable_item_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-87") {
		createIndex(indexName: "FK_qqpp7tk30sghhnj37eioxyu2e", tableName: "invoice_item") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-88") {
		createIndex(indexName: "FK_sqwxdmari66hc2jvifdfndji5", tableName: "invoice_item") {
			column(name: "discount_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-89") {
		createIndex(indexName: "end_ip_uniq_1412148545245", tableName: "ip_address", unique: "true") {
			column(name: "end_ip")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-90") {
		createIndex(indexName: "end_ip_value_uniq_1412148545245", tableName: "ip_address", unique: "true") {
			column(name: "end_ip_value")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-91") {
		createIndex(indexName: "start_ip_uniq_1412148545245", tableName: "ip_address", unique: "true") {
			column(name: "start_ip")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-92") {
		createIndex(indexName: "start_ip_value_uniq_1412148545246", tableName: "ip_address", unique: "true") {
			column(name: "start_ip_value")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-93") {
		createIndex(indexName: "FK_7vj7qw714561rssch090tw994", tableName: "log") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-94") {
		createIndex(indexName: "FK_8lt309ogyiek75y7v92jvl08j", tableName: "log") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-95") {
		createIndex(indexName: "FK_kt7hnqia904v6xyuhg8ehqhgo", tableName: "notification_email") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-96") {
		createIndex(indexName: "email_uniq_1412148545249", tableName: "notification_email", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-97") {
		createIndex(indexName: "gateway_name_uniq_1412148545250", tableName: "payment_gateways", unique: "true") {
			column(name: "gateway_name")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-98") {
		createIndex(indexName: "FK_c5tb3hb9rkqo2wknmh1vwjupp", tableName: "payments") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-99") {
		createIndex(indexName: "FK_3hgs3kmg1o1oixkoxvay4msol", tableName: "pre_defined_reply") {
			column(name: "department_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-100") {
		createIndex(indexName: "code_uniq_1412148545253", tableName: "promotion", unique: "true") {
			column(name: "code")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-101") {
		createIndex(indexName: "FK_il9q6akq4xtwq0rkffo16gfdf", tableName: "recurring_item") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-102") {
		createIndex(indexName: "FK_q53ejpv2l160jaficmlntmlsp", tableName: "recurring_item") {
			column(name: "billable_item_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-103") {
		createIndex(indexName: "FK_jy59hqg8ytdrt7jq5yo92l949", tableName: "refund") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-104") {
		createIndex(indexName: "FK_pkwls1sxj8t8gda8b02nfy853", tableName: "refund") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-105") {
		createIndex(indexName: "authority_uniq_1412148545257", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-106") {
		createIndex(indexName: "FK_lxoqjm8644epv72af3k3jpalx", tableName: "state") {
			column(name: "country_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-107") {
		createIndex(indexName: "name_uniq_1412148545260", tableName: "tax", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-108") {
		createIndex(indexName: "FK_95pw196xd8iy111lhdx7cqfo5", tableName: "ticket") {
			column(name: "department_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-109") {
		createIndex(indexName: "FK_i0i7rws9vvh121bg8mibj73pe", tableName: "ticket") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-110") {
		createIndex(indexName: "FK_rexowx31mw2y20hpir77jhvw9", tableName: "ticket") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-111") {
		createIndex(indexName: "FK_7n880nh99fxukrji0ojak7o7u", tableName: "ticket_details") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-112") {
		createIndex(indexName: "FK_b5xfq2niwdc9o225oge7j6165", tableName: "ticket_details") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-113") {
		createIndex(indexName: "FK_q4lalqxpdkp17sn9ays2i5kj0", tableName: "ticket_details") {
			column(name: "ticket_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-114") {
		createIndex(indexName: "FK_nrrhhb0bsexvi8ch6wnon9uog", tableName: "user") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-115") {
		createIndex(indexName: "api_key_uniq_1412148545264", tableName: "user", unique: "true") {
			column(name: "api_key")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-116") {
		createIndex(indexName: "secret_key_uniq_1412148545265", tableName: "user", unique: "true") {
			column(name: "secret_key")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-117") {
		createIndex(indexName: "username_uniq_1412148545265", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-118") {
		createIndex(indexName: "uuid_uniq_1412148545265", tableName: "user", unique: "true") {
			column(name: "uuid")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-119") {
		createIndex(indexName: "FK_42m8yq0e7xufy32bqrd2cjfkn", tableName: "user_event") {
			column(name: "event_log_ip_address_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-120") {
		createIndex(indexName: "FK_rfoo0jivhbf3voqwj6nc8vllv", tableName: "user_event") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-121") {
		createIndex(indexName: "FK_apcc8lxk2xnug8377fatvbn04", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-122") {
		createIndex(indexName: "FK_it77eq964jhfqtu54081ebtio", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-123") {
		createIndex(indexName: "FK_7jxo321w37v6bl30a4mpei2e1", tableName: "useripaddress") {
			column(name: "user_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-124") {
		createIndex(indexName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", tableName: "useripaddress") {
			column(name: "account_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1412148545335-125") {
		createIndex(indexName: "ip_reference_id_uniq_1412148545267", tableName: "useripaddress", unique: "true") {
			column(name: "ip_reference_id")
		}
	}

////	changeSet(author: "lakshmi (generated)", id: "1412148545335-34") {
////		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FK_296wkqwal09diune025bhqmy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
////	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-35") {
//		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FK_e0e4fj7pb4j008n57dl563yg0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-36") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FK_5ta5bijgwndhkhsv98kv5i6js", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-37") {
//		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FK_99vgsqatibaqbaar8onngjijq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-38") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "api_user", constraintName: "FK_6amqsmb5hmim0r67uoums68m4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-39") {
//		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK_c0mjsdvfmtqmy4sumao1nyuko", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-40") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "credit", constraintName: "FK_2s9bu4qkeqw92ljwog2l2jbhc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-41") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "event_log_ip_address", constraintName: "FK_q50ke4gi3hj9vrp8tfhyj68ut", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-42") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "invoice", constraintName: "FK_gqf8jc7qmukdqpr60mahwohkf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-43") {
//		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "invoice_item", constraintName: "FK_1fyctkp1kfh1hnjebvdv7509o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-44") {
//		addForeignKeyConstraint(baseColumnNames: "discount_id", baseTableName: "invoice_item", constraintName: "FK_sqwxdmari66hc2jvifdfndji5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "discount", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-45") {
//		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FK_qqpp7tk30sghhnj37eioxyu2e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-46") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "log", constraintName: "FK_7vj7qw714561rssch090tw994", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-47") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "log", constraintName: "FK_8lt309ogyiek75y7v92jvl08j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-48") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "notification_email", constraintName: "FK_kt7hnqia904v6xyuhg8ehqhgo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-49") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "payments", constraintName: "FK_c5tb3hb9rkqo2wknmh1vwjupp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-50") {
//		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "pre_defined_reply", constraintName: "FK_3hgs3kmg1o1oixkoxvay4msol", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-51") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "recurring_item", constraintName: "FK_il9q6akq4xtwq0rkffo16gfdf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-52") {
//		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "recurring_item", constraintName: "FK_q53ejpv2l160jaficmlntmlsp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-53") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "refund", constraintName: "FK_jy59hqg8ytdrt7jq5yo92l949", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-54") {
//		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "refund", constraintName: "FK_pkwls1sxj8t8gda8b02nfy853", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-55") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", constraintName: "FK_lxoqjm8644epv72af3k3jpalx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-56") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket", constraintName: "FK_rexowx31mw2y20hpir77jhvw9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-57") {
//		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "ticket", constraintName: "FK_95pw196xd8iy111lhdx7cqfo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-58") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket", constraintName: "FK_i0i7rws9vvh121bg8mibj73pe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-59") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket_details", constraintName: "FK_b5xfq2niwdc9o225oge7j6165", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-60") {
//		addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "ticket_details", constraintName: "FK_q4lalqxpdkp17sn9ays2i5kj0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ticket", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-61") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket_details", constraintName: "FK_7n880nh99fxukrji0ojak7o7u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-62") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "user", constraintName: "FK_nrrhhb0bsexvi8ch6wnon9uog", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-63") {
//		addForeignKeyConstraint(baseColumnNames: "event_log_ip_address_id", baseTableName: "user_event", constraintName: "FK_42m8yq0e7xufy32bqrd2cjfkn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event_log_ip_address", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-64") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_event", constraintName: "FK_rfoo0jivhbf3voqwj6nc8vllv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-65") {
//		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-66") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-67") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "useripaddress", constraintName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "lakshmi (generated)", id: "1412148545335-68") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "useripaddress", constraintName: "FK_7jxo321w37v6bl30a4mpei2e1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
}
