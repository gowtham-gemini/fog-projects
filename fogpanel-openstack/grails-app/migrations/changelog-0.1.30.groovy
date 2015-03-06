databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1418630300580-13") {
		dropIndex(indexName: "name_uniq_1418619851866", tableName: "sshkeys")
	}

	changeSet(author: "Abdul (generated)", id: "1418630300580-14") {
		createIndex(indexName: "unique_name", tableName: "sshkeys", unique: "true") {
			column(name: "account_id")

			column(name: "name")
		}
	}

}
