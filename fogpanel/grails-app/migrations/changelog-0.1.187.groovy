databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1409900549264-1") {
		addColumn(tableName: "vpc") {
			column(name: "job_id", type: "varchar(255)")
		}
	}
}
