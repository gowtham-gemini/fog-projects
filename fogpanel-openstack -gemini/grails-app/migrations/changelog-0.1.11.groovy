databaseChangeLog = {

	changeSet(author: "ds (generated)", id: "1413876365419-1") {
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

			column(name: "image_id", type: "bigint")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "size", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "source_volume_id", type: "varchar(255)")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "volume_type_id", type: "bigint")

			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "ds (generated)", id: "1413876365419-2") {
		createTable(tableName: "zone") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "zonePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ds (generated)", id: "1413876365419-38") {
		createIndex(indexName: "FK_aqufe2os3v581j6ss0i3cplh9", tableName: "volume") {
			column(name: "image_id")
		}
	}

	changeSet(author: "ds (generated)", id: "1413876365419-39") {
		createIndex(indexName: "FK_fvrw5qjb69r3oraedl12spqhe", tableName: "volume") {
			column(name: "user_id")
		}
	}

	changeSet(author: "ds (generated)", id: "1413876365419-40") {
		createIndex(indexName: "FK_lrh52t8rg5yem7nj2323wg07h", tableName: "volume") {
			column(name: "account_id")
		}
	}

	changeSet(author: "ds (generated)", id: "1413876365419-41") {
		createIndex(indexName: "FK_sjcd2fjm5og7ovj103eky0c1f", tableName: "volume") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "ds (generated)", id: "1413876365419-42") {
		createIndex(indexName: "FK_sxe2a5os3ef5rno7nalk80ad0", tableName: "volume") {
			column(name: "volume_type_id")
		}
	}

	/*changeSet(author: "ds (generated)", id: "1413876365419-3") {
		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK_c0mjsdvfmtqmy4sumao1nyuko", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
	}*/

	changeSet(author: "ds (generated)", id: "1413876365419-4") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "credit", constraintName: "FK_2s9bu4qkeqw92ljwog2l2jbhc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-5") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "event_log_ip_address", constraintName: "FK_q50ke4gi3hj9vrp8tfhyj68ut", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-6") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "invoice", constraintName: "FK_gqf8jc7qmukdqpr60mahwohkf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-7") {
		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "invoice_item", constraintName: "FK_1fyctkp1kfh1hnjebvdv7509o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-8") {
		addForeignKeyConstraint(baseColumnNames: "discount_id", baseTableName: "invoice_item", constraintName: "FK_sqwxdmari66hc2jvifdfndji5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "discount", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-9") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FK_qqpp7tk30sghhnj37eioxyu2e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-10") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "log", constraintName: "FK_7vj7qw714561rssch090tw994", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-11") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "log", constraintName: "FK_8lt309ogyiek75y7v92jvl08j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-12") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "notification_email", constraintName: "FK_kt7hnqia904v6xyuhg8ehqhgo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-13") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "payments", constraintName: "FK_c5tb3hb9rkqo2wknmh1vwjupp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-14") {
		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "pre_defined_reply", constraintName: "FK_3hgs3kmg1o1oixkoxvay4msol", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-15") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "recurring_item", constraintName: "FK_il9q6akq4xtwq0rkffo16gfdf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-16") {
		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "recurring_item", constraintName: "FK_q53ejpv2l160jaficmlntmlsp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-17") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "refund", constraintName: "FK_jy59hqg8ytdrt7jq5yo92l949", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-18") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "refund", constraintName: "FK_pkwls1sxj8t8gda8b02nfy853", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-19") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", constraintName: "FK_lxoqjm8644epv72af3k3jpalx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-20") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket", constraintName: "FK_rexowx31mw2y20hpir77jhvw9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-21") {
		addForeignKeyConstraint(baseColumnNames: "department_id", baseTableName: "ticket", constraintName: "FK_95pw196xd8iy111lhdx7cqfo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "support_department", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-22") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket", constraintName: "FK_i0i7rws9vvh121bg8mibj73pe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-23") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "ticket_details", constraintName: "FK_b5xfq2niwdc9o225oge7j6165", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-24") {
		addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "ticket_details", constraintName: "FK_q4lalqxpdkp17sn9ays2i5kj0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ticket", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-25") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "ticket_details", constraintName: "FK_7n880nh99fxukrji0ojak7o7u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-26") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "user", constraintName: "FK_nrrhhb0bsexvi8ch6wnon9uog", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-27") {
		addForeignKeyConstraint(baseColumnNames: "event_log_ip_address_id", baseTableName: "user_event", constraintName: "FK_42m8yq0e7xufy32bqrd2cjfkn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event_log_ip_address", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-28") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_event", constraintName: "FK_rfoo0jivhbf3voqwj6nc8vllv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-29") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-30") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-31") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "useripaddress", constraintName: "FK_s1vcihhoeyu2bs88tjqlnj4ex", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-32") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "useripaddress", constraintName: "FK_7jxo321w37v6bl30a4mpei2e1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-33") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "volume", constraintName: "FK_lrh52t8rg5yem7nj2323wg07h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-34") {
		addForeignKeyConstraint(baseColumnNames: "image_id", baseTableName: "volume", constraintName: "FK_aqufe2os3v581j6ss0i3cplh9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "images", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-35") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "volume", constraintName: "FK_fvrw5qjb69r3oraedl12spqhe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-36") {
		addForeignKeyConstraint(baseColumnNames: "volume_type_id", baseTableName: "volume", constraintName: "FK_sxe2a5os3ef5rno7nalk80ad0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume_type", referencesUniqueColumn: "false")
	}

	changeSet(author: "ds (generated)", id: "1413876365419-37") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "volume", constraintName: "FK_sjcd2fjm5og7ovj103eky0c1f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
