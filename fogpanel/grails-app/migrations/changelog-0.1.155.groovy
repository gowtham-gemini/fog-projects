databaseChangeLog = {

        changeSet(author: "gowtham (generated)", id: "1405401006317-3") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "zone_id", tableName: "snapshot")
	}

	changeSet(author: "gowtham (generated)", id: "1405401006317-4") {
		dropForeignKeyConstraint(baseTableName: "network_offer", constraintName: "FK_s4l43x8bkbfpdnxb63dga90ao")
	}

	changeSet(author: "gowtham (generated)", id: "1405401006317-5") {
		dropColumn(columnName: "zone_id", tableName: "network_offer")
	}
}
