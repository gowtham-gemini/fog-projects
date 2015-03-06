databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424444999869-1") {
		addColumn(tableName: "subnet") {
			column(name: "referenceid", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424444999869-2") {
		dropForeignKeyConstraint(baseTableName: "subnet", baseTableSchemaName: "aws_fog", constraintName: "FK_tdqe4vxedelnkkwiw7rms3oto")
	}

	changeSet(author: "santhosh (generated)", id: "1424444999869-3") {
		dropColumn(columnName: "availability_zone_id", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424444999869-4") {
		dropColumn(columnName: "subnet_id", tableName: "subnet")
	}
}
