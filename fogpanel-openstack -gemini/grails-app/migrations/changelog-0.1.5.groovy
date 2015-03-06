databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "Openstack Config update") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 93)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "openstack admin uuid")
                column(name: "name", value: "openstack.admin.uuid")
                column(name: "value", value: "")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 94)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "openstack admin password")
                column(name: "name", value: "openstack.admin.password")
                column(name: "value", value: "")
            } 
        
	}
}
