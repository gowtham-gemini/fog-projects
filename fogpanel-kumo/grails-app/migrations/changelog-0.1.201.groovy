databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1411734433376-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "job_id", tableName: "vpcgateways")
	}
}
