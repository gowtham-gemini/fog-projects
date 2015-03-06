databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1409633930640-1") {
		addColumn(tableName: "event_log_ip_address") {
			column(name: "is_locked", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409633930640-2") {
		addColumn(tableName: "event_log_ip_address") {
			column(name: "lock_time", type: "datetime")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409633930640-3") {
		addColumn(tableName: "event_log_ip_address") {
			column(name: "over_all_failure_count", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}
}
