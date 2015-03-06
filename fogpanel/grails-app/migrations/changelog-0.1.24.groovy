databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1376713330156-1") {
		createTable(tableName: "mail_template") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "mail_templatePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "content", type: "varchar(5000)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

//	changeSet(author: "gowtham (generated)", id: "1376713330156-25") {
//		createIndex(indexName: "content_unique_1376713328789", tableName: "mail_template", unique: "true") {
//			column(name: "content")
//		}
//	}
}
