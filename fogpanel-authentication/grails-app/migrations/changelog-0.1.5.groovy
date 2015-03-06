databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1424863190178-1") {
		createTable(tableName: "persistent_session") {
			column(name: "id", type: "varchar(255)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "persistent_sePK")
			}

			column(name: "creation_time", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "invalidated", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "last_accessed_time", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "max_inactive_interval", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}
}
