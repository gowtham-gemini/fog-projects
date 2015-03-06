databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1384319784978-1") {
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

			column(name: "department_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "subject", type: "varchar(5000)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1384319784978-2") {
		createTable(tableName: "support_department") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "support_deparPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1384319784978-65") {
		dropColumn(columnName: "created_date", tableName: "zone")
	}
}
