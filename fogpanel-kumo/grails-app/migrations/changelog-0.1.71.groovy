databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1383282436725-63") {
		dropColumn(columnName: "created_by", tableName: "event_log_ip_address")
	}

	changeSet(author: "gowtham (generated)", id: "1383282436725-64") {
		dropColumn(columnName: "created_date", tableName: "event_log_ip_address")
	}

	changeSet(author: "gowtham (generated)", id: "1383282436725-65") {
		dropColumn(columnName: "edited_by", tableName: "event_log_ip_address")
	}

	changeSet(author: "gowtham (generated)", id: "1383282436725-66") {
		dropColumn(columnName: "edited_date", tableName: "event_log_ip_address")
	}

	changeSet(author: "gowtham (generated)", id: "1383282436725-67") {
		dropColumn(columnName: "created_by", tableName: "user_event")
	}

	changeSet(author: "gowtham (generated)", id: "1383282436725-68") {
		dropColumn(columnName: "created_date", tableName: "user_event")
	}

	changeSet(author: "gowtham (generated)", id: "1383282436725-69") {
		dropColumn(columnName: "edited_by", tableName: "user_event")
	}

	changeSet(author: "gowtham (generated)", id: "1383282436725-70") {
		dropColumn(columnName: "edited_date", tableName: "user_event")
	}
}
