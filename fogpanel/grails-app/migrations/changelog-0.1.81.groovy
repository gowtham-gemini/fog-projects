databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1384337207135-1") {
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
}
