databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "discountname-1") {
		addColumn(tableName: "discount") {
			column(name: "discount_name", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "discountname-14") {
		createIndex(indexName: "discount_name_unique_1374651484071", tableName: "discount", unique: "true") {
			column(name: "discount_name")
		}
	}

}
