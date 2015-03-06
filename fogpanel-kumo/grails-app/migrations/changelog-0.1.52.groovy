databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1380790751413-1") {
		addColumn(tableName: "template") {
			column(name: "size", type: "double precision")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1380790751413-2") {
		addColumn(tableName: "volume") {
			column(name: "cluster_id", type: "bigint")
		}
	}
}
