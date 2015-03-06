databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1376626174015-6") {
		dropIndex(indexName: "discount_name", tableName: "discount")
	}

	changeSet(author: "gowtham (generated)", id: "1376626174015-7") {
		dropIndex(indexName: "discount_name_unique_1374651484071", tableName: "discount")
	}

	changeSet(author: "gowtham (generated)", id: "stpovmcost-7") {
		insert(tableName: "config") {
                column(name: "id", valueNumeric: 70)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "instance.minimum.cost.applicable.hour")
                column(name: "description", value: "description")
                column(name: "value", value: "10")
            }
	}
}
