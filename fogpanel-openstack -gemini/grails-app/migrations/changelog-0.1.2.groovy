databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "Openstack Config") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 92)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "openstack endpoint url")
                column(name: "name", value: "openstack.endpoint.url")
                column(name: "value", value: "")
            } 
	}
        
	changeSet(author: "gowtham (generated)", id: "1413175828169-5") {
		createTable(tableName: "region") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "regionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
