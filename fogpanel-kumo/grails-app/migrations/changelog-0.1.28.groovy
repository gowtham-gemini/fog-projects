databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1376906093549-24") {
		createIndex(indexName: "name_unique_1376906089215", tableName: "mail_template", unique: "true") {
			column(name: "name")
		}
	}
}
