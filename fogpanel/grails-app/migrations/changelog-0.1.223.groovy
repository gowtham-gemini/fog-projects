databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1417846142704-1") {
		dropForeignKeyConstraint(baseTableName: "vpc", constraintName: "FK_7kooikb0oscu7pom8t2mgcjld")
	}

	changeSet(author: "gowtham (generated)", id: "1417846142704-3") {
		dropColumn(columnName: "user_id", tableName: "vpc")
	}

	changeSet(author: "gowtham (generated)", id: "1417846142704-2") {
            
            sqlFile( path: "adminMigration.sql")     
	}
}
