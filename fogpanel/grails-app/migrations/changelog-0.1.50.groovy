databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1380782915433-1") {
		addColumn(tableName: "volume") {
			column(name: "from", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1380782915433-2") {
		addColumn(tableName: "volume") {
			column(name: "size", type: "double precision")
		}
	}
}
