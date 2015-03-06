databaseChangeLog = {

	changeSet(author: "deveops (generated)", id: "1424777332627-1") {
		addColumn(tableName: "user") {
			column(name: "contact_number", type: "varchar(12)") {
				constraints(nullable: "false")
			}
		}
	}

//	changeSet(author: "deveops (generated)", id: "1424777332627-2") {
//		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FK_296wkqwal09diune025bhqmy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-3") {
//		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FK_e0e4fj7pb4j008n57dl563yg0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-4") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FK_5ta5bijgwndhkhsv98kv5i6js", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-5") {
//		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FK_99vgsqatibaqbaar8onngjijq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-6") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "api_user", constraintName: "FK_6amqsmb5hmim0r67uoums68m4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-7") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "asynchronous_jobs", constraintName: "FK_gh68tl37tghxw6il33ih4ym3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-8") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "asynchronous_jobs", constraintName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-9") {
//		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK_c0mjsdvfmtqmy4sumao1nyuko", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-10") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "credit", constraintName: "FK_2s9bu4qkeqw92ljwog2l2jbhc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-11") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "event_log_ip_address", constraintName: "FK_q50ke4gi3hj9vrp8tfhyj68ut", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-12") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "images", constraintName: "FK_a7njc8t40e0k9u77qm293y5uf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-13") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "images", constraintName: "FK_gn0kkmw9cx9tbd2bwc6xxbqr7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-14") {
//		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "images", constraintName: "FK_s0yc5n7522hacknlrw8md1788", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-15") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "invoice", constraintName: "FK_gqf8jc7qmukdqpr60mahwohkf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-16") {
//		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "invoice_item", constraintName: "FK_1fyctkp1kfh1hnjebvdv7509o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-17") {
//		addForeignKeyConstraint(baseColumnNames: "discount_id", baseTableName: "invoice_item", constraintName: "FK_sqwxdmari66hc2jvifdfndji5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "discount", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-18") {
//		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FK_qqpp7tk30sghhnj37eioxyu2e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-19") {
//		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "invoice_item", constraintName: "FK_n4tkekuqyf82cfbb27fq0m2nt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-20") {
//		addForeignKeyConstraint(baseColumnNames: "asynchronous_jobs_id", baseTableName: "job_properties", constraintName: "FK_c1l0okjaxj98xu2uqdox69f1k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "asynchronous_jobs", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-21") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "log", constraintName: "FK_7vj7qw714561rssch090tw994", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-22") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "log", constraintName: "FK_8lt309ogyiek75y7v92jvl08j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-23") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "notification_email", constraintName: "FK_kt7hnqia904v6xyuhg8ehqhgo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-24") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "notification_topic", constraintName: "FK_by6p4a7fc0qymohyjnwk3ixhp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-25") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "payments", constraintName: "FK_c5tb3hb9rkqo2wknmh1vwjupp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-26") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "port", constraintName: "FK_9kw6ofdijmxu07iim08bhygy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-27") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "port", constraintName: "FK_dlh5lt412h1p6kfoa1t9hnlo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-28") {
//		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "pre_defined_reply", constraintName: "FK_3hgs3kmg1o1oixkoxvay4msol", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-29") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "recurring_item", constraintName: "FK_il9q6akq4xtwq0rkffo16gfdf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-30") {
//		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "recurring_item", constraintName: "FK_q53ejpv2l160jaficmlntmlsp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-31") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "refund", constraintName: "FK_jy59hqg8ytdrt7jq5yo92l949", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-32") {
//		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "refund", constraintName: "FK_pkwls1sxj8t8gda8b02nfy853", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-33") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "security_group", constraintName: "FK_sl3jnp9vr046lmfqf5yg0np84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-34") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "security_group", constraintName: "FK_8cx8ylpdj6l7m6duniqxgomdh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-35") {
//		addForeignKeyConstraint(baseColumnNames: "security_group_id", baseTableName: "security_group_rules", constraintName: "FK_j47n7qdogdyxkhp86twuwgxqo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "security_group", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-36") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "sshkeys", constraintName: "FK_7c2bki05yhorcxv1inwqid1u6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-37") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sshkeys", constraintName: "FK_62rnb2hcgy2v38lp8mhrw1c5y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-38") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", constraintName: "FK_lxoqjm8644epv72af3k3jpalx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-39") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket", constraintName: "FK_rexowx31mw2y20hpir77jhvw9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-40") {
//		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "ticket", constraintName: "FK_95pw196xd8iy111lhdx7cqfo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-41") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket", constraintName: "FK_i0i7rws9vvh121bg8mibj73pe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-42") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket_details", constraintName: "FK_b5xfq2niwdc9o225oge7j6165", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-43") {
//		addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "ticket_details", constraintName: "FK_q4lalqxpdkp17sn9ays2i5kj0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ticket", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-44") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket_details", constraintName: "FK_7n880nh99fxukrji0ojak7o7u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-45") {
//		addForeignKeyConstraint(baseColumnNames: "topic_id", baseTableName: "topic_subscriber", constraintName: "FK_64takrjrph8hyxi3il62d2shq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "notification_topic", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-46") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "user", constraintName: "FK_nrrhhb0bsexvi8ch6wnon9uog", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-47") {
//		addForeignKeyConstraint(baseColumnNames: "event_log_ip_address_id", baseTableName: "user_event", constraintName: "FK_42m8yq0e7xufy32bqrd2cjfkn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event_log_ip_address", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-48") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_event", constraintName: "FK_rfoo0jivhbf3voqwj6nc8vllv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-49") {
//		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-50") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-51") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "useripaddress", constraintName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-52") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "useripaddress", constraintName: "FK_7jxo321w37v6bl30a4mpei2e1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-53") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "virtual_machine", constraintName: "FK_4jt39p05i8q4w4d3vabwrtadf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-54") {
//		addForeignKeyConstraint(baseColumnNames: "image_id", baseTableName: "virtual_machine", constraintName: "FK_7nk7ln6js0lj710qyq6v8ahi2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "images", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-55") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "virtual_machine", constraintName: "FK_tqtkdro59c0ovnlsyofket2sj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "deveops (generated)", id: "1424777332627-56") {
//		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "virtual_machine", constraintName: "FK_g7m5i040mh3pxr8onlhtqpkx7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
//	}
}
