databaseChangeLog = {

	changeSet(author: "operations (generated)", id: "1407318248970-1") {
		addColumn(tableName: "product") {
			column(name: "showcased_product", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

//	changeSet(author: "operations (generated)", id: "1407318248970-2") {
//		dropColumn(columnName: "show_cased_product", tableName: "product")
//	}
}
