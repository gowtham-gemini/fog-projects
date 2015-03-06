databaseChangeLog = {

	changeSet(author: "deveops (generated)", id: "1425048583784-1") {
		createTable(tableName: "ldap_group") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ldap_groupPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-2") {
		createTable(tableName: "ldap_group_user") {
			column(name: "group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-3") {
		createTable(tableName: "ldap_group_users") {
			column(name: "ldap_group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-4") {
		createTable(tableName: "project_groups") {
			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "ldap_group_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-5") {
		createTable(tableName: "project_ldap_group") {
			column(name: "group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "project_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-6") {
		addColumn(tableName: "project") {
			column(name: "domain_name", type: "varchar(255)")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-7") {
		addColumn(tableName: "project") {
			column(name: "engine_id", type: "varchar(255)")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-8") {
		addColumn(tableName: "project") {
			column(name: "token", type: "varchar(255)")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-9") {
		addColumn(tableName: "project") {
			column(name: "url", type: "varchar(255)")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-10") {
		addPrimaryKey(columnNames: "group_id, user_id", constraintName: "ldap_group_usPK", tableName: "ldap_group_user")
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-11") {
		addPrimaryKey(columnNames: "ldap_group_id, user_id", tableName: "ldap_group_users")
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-12") {
		addPrimaryKey(columnNames: "project_id, ldap_group_id", tableName: "project_groups")
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-13") {
		addPrimaryKey(columnNames: "group_id, project_id", constraintName: "project_ldap_PK", tableName: "project_ldap_group")
	}

//	changeSet(author: "deveops (generated)", id: "1425048583784-76") {
//		dropIndex(indexName: "FK_2pggwcirlkx8ijnkwarkces1d", tableName: "group")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-77") {
//		dropIndex(indexName: "name_uniq_1424938952231", tableName: "group")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-78") {
//		dropIndex(indexName: "FK_ponf6el8r8qxoe212w6lxkej5", tableName: "group_users")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-79") {
//		dropIndex(indexName: "FK_q2r2qpupn1nesrnj23elvnaqf", tableName: "group_users")
//	}

	changeSet(author: "deveops (generated)", id: "1425048583784-80") {
		createIndex(indexName: "name_uniq_1425048583086", tableName: "ldap_group", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-81") {
		createIndex(indexName: "FK_4a6e0xcnrtb3kw32hd9sdycbf", tableName: "ldap_group_user") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-82") {
		createIndex(indexName: "FK_bmnf5jej77fob14xatmdvku84", tableName: "ldap_group_user") {
			column(name: "group_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-83") {
		createIndex(indexName: "FK_9ev7gont868yrd8c91x8be378", tableName: "ldap_group_users") {
			column(name: "user_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-84") {
		createIndex(indexName: "FK_hnmpjhf65uhfyf1h3j0k5ytk2", tableName: "ldap_group_users") {
			column(name: "ldap_group_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-85") {
		createIndex(indexName: "FK_5wvh0dj4iuo2ed4bdcetrbshx", tableName: "project_groups") {
			column(name: "project_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-86") {
		createIndex(indexName: "FK_ne1g5yi6q0pwieouuwcjhnjlt", tableName: "project_groups") {
			column(name: "ldap_group_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-87") {
		createIndex(indexName: "FK_e1bdmc0gcbxfdypw5mqk319w7", tableName: "project_ldap_group") {
			column(name: "project_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-88") {
		createIndex(indexName: "FK_fbn2t24i33ku78u9f2hidmucw", tableName: "project_ldap_group") {
			column(name: "group_id")
		}
	}

	changeSet(author: "deveops (generated)", id: "1425048583784-89") {
		dropColumn(columnName: "group_dn", tableName: "project")
	}

//	changeSet(author: "deveops (generated)", id: "1425048583784-90") {
//		dropTable(tableName: "group")
//	}

	changeSet(author: "deveops (generated)", id: "1425048583784-91") {
		dropTable(tableName: "group_users")
	}

//	changeSet(author: "deveops (generated)", id: "1425048583784-14") {
//		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FK_296wkqwal09diune025bhqmy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-15") {
//		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FK_e0e4fj7pb4j008n57dl563yg0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-16") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FK_5ta5bijgwndhkhsv98kv5i6js", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-17") {
//		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FK_99vgsqatibaqbaar8onngjijq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-18") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "api_user", constraintName: "FK_6amqsmb5hmim0r67uoums68m4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-19") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "asynchronous_jobs", constraintName: "FK_gh68tl37tghxw6il33ih4ym3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-20") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "asynchronous_jobs", constraintName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-21") {
//		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK_c0mjsdvfmtqmy4sumao1nyuko", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-22") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "credit", constraintName: "FK_2s9bu4qkeqw92ljwog2l2jbhc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-23") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "event_log_ip_address", constraintName: "FK_q50ke4gi3hj9vrp8tfhyj68ut", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-24") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "images", constraintName: "FK_a7njc8t40e0k9u77qm293y5uf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-25") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "images", constraintName: "FK_gn0kkmw9cx9tbd2bwc6xxbqr7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-26") {
//		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "images", constraintName: "FK_s0yc5n7522hacknlrw8md1788", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-27") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "invoice", constraintName: "FK_gqf8jc7qmukdqpr60mahwohkf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-28") {
//		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "invoice_item", constraintName: "FK_1fyctkp1kfh1hnjebvdv7509o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-29") {
//		addForeignKeyConstraint(baseColumnNames: "discount_id", baseTableName: "invoice_item", constraintName: "FK_sqwxdmari66hc2jvifdfndji5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "discount", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-30") {
//		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FK_qqpp7tk30sghhnj37eioxyu2e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-31") {
//		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "invoice_item", constraintName: "FK_n4tkekuqyf82cfbb27fq0m2nt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-32") {
//		addForeignKeyConstraint(baseColumnNames: "asynchronous_jobs_id", baseTableName: "job_properties", constraintName: "FK_c1l0okjaxj98xu2uqdox69f1k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "asynchronous_jobs", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-33") {
//		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "ldap_group_user", constraintName: "FK_bmnf5jej77fob14xatmdvku84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-34") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ldap_group_user", constraintName: "FK_4a6e0xcnrtb3kw32hd9sdycbf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-35") {
//		addForeignKeyConstraint(baseColumnNames: "ldap_group_id", baseTableName: "ldap_group_users", constraintName: "FK_hnmpjhf65uhfyf1h3j0k5ytk2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-36") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ldap_group_users", constraintName: "FK_9ev7gont868yrd8c91x8be378", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-37") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "log", constraintName: "FK_7vj7qw714561rssch090tw994", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-38") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "log", constraintName: "FK_8lt309ogyiek75y7v92jvl08j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-39") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "notification_email", constraintName: "FK_kt7hnqia904v6xyuhg8ehqhgo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-40") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "notification_topic", constraintName: "FK_by6p4a7fc0qymohyjnwk3ixhp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-41") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "payments", constraintName: "FK_c5tb3hb9rkqo2wknmh1vwjupp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-42") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "port", constraintName: "FK_9kw6ofdijmxu07iim08bhygy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-43") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "port", constraintName: "FK_dlh5lt412h1p6kfoa1t9hnlo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-44") {
//		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "pre_defined_reply", constraintName: "FK_3hgs3kmg1o1oixkoxvay4msol", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-45") {
//		addForeignKeyConstraint(baseColumnNames: "ldap_group_id", baseTableName: "project_groups", constraintName: "FK_ne1g5yi6q0pwieouuwcjhnjlt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-46") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_groups", constraintName: "FK_5wvh0dj4iuo2ed4bdcetrbshx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-47") {
//		addForeignKeyConstraint(baseColumnNames: "group_id", baseTableName: "project_ldap_group", constraintName: "FK_fbn2t24i33ku78u9f2hidmucw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ldap_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-48") {
//		addForeignKeyConstraint(baseColumnNames: "project_id", baseTableName: "project_ldap_group", constraintName: "FK_e1bdmc0gcbxfdypw5mqk319w7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "project", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-49") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "recurring_item", constraintName: "FK_il9q6akq4xtwq0rkffo16gfdf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-50") {
//		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "recurring_item", constraintName: "FK_q53ejpv2l160jaficmlntmlsp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-51") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "refund", constraintName: "FK_jy59hqg8ytdrt7jq5yo92l949", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-52") {
//		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "refund", constraintName: "FK_pkwls1sxj8t8gda8b02nfy853", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-53") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "security_group", constraintName: "FK_sl3jnp9vr046lmfqf5yg0np84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-54") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "security_group", constraintName: "FK_8cx8ylpdj6l7m6duniqxgomdh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-55") {
//		addForeignKeyConstraint(baseColumnNames: "security_group_id", baseTableName: "security_group_rules", constraintName: "FK_j47n7qdogdyxkhp86twuwgxqo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "security_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-56") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "sshkeys", constraintName: "FK_7c2bki05yhorcxv1inwqid1u6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-57") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sshkeys", constraintName: "FK_62rnb2hcgy2v38lp8mhrw1c5y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-58") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", constraintName: "FK_lxoqjm8644epv72af3k3jpalx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-59") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket", constraintName: "FK_rexowx31mw2y20hpir77jhvw9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-60") {
//		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "ticket", constraintName: "FK_95pw196xd8iy111lhdx7cqfo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-61") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket", constraintName: "FK_i0i7rws9vvh121bg8mibj73pe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-62") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket_details", constraintName: "FK_b5xfq2niwdc9o225oge7j6165", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-63") {
//		addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "ticket_details", constraintName: "FK_q4lalqxpdkp17sn9ays2i5kj0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ticket", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-64") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket_details", constraintName: "FK_7n880nh99fxukrji0ojak7o7u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-65") {
//		addForeignKeyConstraint(baseColumnNames: "topic_id", baseTableName: "topic_subscriber", constraintName: "FK_64takrjrph8hyxi3il62d2shq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "notification_topic", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-66") {
//		addForeignKeyConstraint(baseColumnNames: "event_log_ip_address_id", baseTableName: "user_event", constraintName: "FK_42m8yq0e7xufy32bqrd2cjfkn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event_log_ip_address", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-67") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_event", constraintName: "FK_rfoo0jivhbf3voqwj6nc8vllv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-68") {
//		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-69") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-70") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "useripaddress", constraintName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-71") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "useripaddress", constraintName: "FK_7jxo321w37v6bl30a4mpei2e1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-72") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "virtual_machine", constraintName: "FK_4jt39p05i8q4w4d3vabwrtadf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-73") {
//		addForeignKeyConstraint(baseColumnNames: "image_id", baseTableName: "virtual_machine", constraintName: "FK_7nk7ln6js0lj710qyq6v8ahi2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "images", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-74") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "virtual_machine", constraintName: "FK_tqtkdro59c0ovnlsyofket2sj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1425048583784-75") {
//		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "virtual_machine", constraintName: "FK_g7m5i040mh3pxr8onlhtqpkx7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
//	}
}
