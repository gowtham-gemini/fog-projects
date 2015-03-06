databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1386649992670-1") {
		addColumn(tableName: "sshkeys") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1386649992670-2") {
		addColumn(tableName: "virtual_machine") {
			column(name: "ssh_key_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
}
