databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1415854605571-1") {
		addColumn(tableName: "account") {
			column(name: "cpu_limit", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415854605571-2") {
		addColumn(tableName: "account") {
			column(name: "memory_limit", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415854605571-3") {
                addColumn(tableName: "account") {
                        column(name: "primary_storage_limit", type: "varchar(255)")
                }
	}

	changeSet(author: "gowtham (generated)", id: "1415854605571-4") {
		addColumn(tableName: "account") {
			column(name: "publiciplimit", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415854605571-5") {
		addColumn(tableName: "account") {
			column(name: "secondary_storage_limit", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415854605571-6") {
		addColumn(tableName: "account") {
			column(name: "vpc_limit", type: "varchar(255)")
		}
	}
        
        changeSet(author: "Gowtham (generated)", id: "retail-cpu-limits") {         
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 93)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.instance.limit")
                column(name: "name", value: "retail.instance.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 94)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.storage.limit")
                column(name: "name", value: "retail.storage.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 95)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.snapshot.limit")
                column(name: "name", value: "retail.snapshot.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 96)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.bandwidth.limit")
                column(name: "name", value: "retail.bandwidth.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 97)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.public.ip.limit")
                column(name: "name", value: "retail.public.ip.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 98)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.vpc.limit")
                column(name: "name", value: "retail.vpc.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 99)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.cpu.limit")
                column(name: "name", value: "retail.cpu.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 100)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.memory.limit")
                column(name: "name", value: "retail.memory.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 101)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.primary.storage.limit")
                column(name: "name", value: "retail.primary.storage.limit")
                column(name: "value", value: "-1")
            }
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 102)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.secondary.storage.limit")
                column(name: "name", value: "retail.secondary.storage.limit")
                column(name: "value", value: "-1")
            }
            
        }
}
