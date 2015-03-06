databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1419586610380-1") {
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

	changeSet(author: "Abdul (generated)", id: "1419586610380-2") {
		createTable(tableName: "router") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "routerPK")
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

			column(name: "enable_snat", type: "bit")

			column(name: "gateway_port_id", type: "varchar(255)")

			column(name: "is_admin_state", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1419586610380-16") {
		createIndex(indexName: "FK_9kw6ofdijmxu07iim08bhygy3", tableName: "port") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1419586610380-17") {
		createIndex(indexName: "FK_dlh5lt412h1p6kfoa1t9hnlo5", tableName: "port") {
			column(name: "user_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1419586610380-18") {
		createIndex(indexName: "FK_am1uqa4ijxvisuhvcqy2j8wb4", tableName: "router") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1419586610380-19") {
		createIndex(indexName: "FK_lw4u1kev57fmi4amryx0lctsk", tableName: "router") {
			column(name: "user_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1419586610380-10") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "port", constraintName: "FK_9kw6ofdijmxu07iim08bhygy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Abdul (generated)", id: "1419586610380-11") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "port", constraintName: "FK_dlh5lt412h1p6kfoa1t9hnlo5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Abdul (generated)", id: "1419586610380-12") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "router", constraintName: "FK_am1uqa4ijxvisuhvcqy2j8wb4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Abdul (generated)", id: "1419586610380-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "router", constraintName: "FK_lw4u1kev57fmi4amryx0lctsk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

}
