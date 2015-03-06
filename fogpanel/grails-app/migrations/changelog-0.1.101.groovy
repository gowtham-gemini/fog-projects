databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1386132300472-152") {
		createIndex(indexName: "name_unique_1386132298478", tableName: "sshkeys", unique: "true") {
			column(name: "name")
		}
	}
}
