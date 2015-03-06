databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424941114199-1") {
            createTable(tableName: "internet_gateway") {
                    column(autoIncrement: "true", name: "id", type: "bigint") {
                            constraints(nullable: "false", primaryKey: "true", primaryKeyName: "internet_gatePK")
                    }

                    column(name: "version", type: "bigint") {
                            constraints(nullable: "false")
                    }

                    column(name: "name", type: "varchar(255)")

                    column(name: "reference_id", type: "varchar(255)") {
                            constraints(nullable: "false")
                    }

                    column(name: "region_id", type: "integer") {
                            constraints(nullable: "false")
                    }

                    column(name: "state", type: "varchar(255)")

                    column(name: "vpc_id", type: "varchar(255)")
            }
        }    
}


