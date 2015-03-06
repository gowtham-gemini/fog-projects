databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1423495101095-6") {
		createIndex(indexName: "unique_name", tableName: "container", unique: "true") {
			column(name: "account_id")

			column(name: "name")
		}
	}

}
