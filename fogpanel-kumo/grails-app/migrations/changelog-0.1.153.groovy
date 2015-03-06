databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1403605340828-1") {
		createIndex(indexName: "reference_id_uniq_1403605340093", tableName: "nic", unique: "true") {
			column(name: "reference_id")
		}
	}
}
