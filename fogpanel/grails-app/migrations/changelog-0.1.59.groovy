databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1382339793260log-1") {
		addColumn(tableName: "log") {
			column(name: "credit_limit_percentage", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1382339793260log-2") {
		addColumn(tableName: "log") {
			column(name: "mail_send", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
