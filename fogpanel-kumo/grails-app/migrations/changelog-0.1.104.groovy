databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1386843822966-1") {
		addColumn(tableName: "vm_ip") {
			column(name: "ip_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1386843822966-2") {
		addColumn(tableName: "vm_ip") {
			column(name: "job_id", type: "varchar(255)")
		}
	}
}
