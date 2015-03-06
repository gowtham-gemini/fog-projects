databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424253311526-1") {
		createTable(tableName: "volume_type") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "volume_typePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "baseline_iops", type: "integer")

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "iops_max", type: "integer")

			column(name: "iops_min", type: "integer")

			column(name: "is_iops", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "size_max", type: "integer")

			column(name: "size_min", type: "integer")
		}
	}
}
