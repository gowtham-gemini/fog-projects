databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424336787567-1") {
		addColumn(tableName: "flavors") {
			column(name: "network_performance", type: "varchar(255)")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424336787567-2") {
		addColumn(tableName: "flavors") {
			column(name: "processor_architecture", type: "varchar(255)")
		}
	}
        
	changeSet(author: "santhosh (generated)", id: "1424336787567-3") {
		addColumn(tableName: "flavors") {
			column(name: "ecu", type: "varchar(255)")
		}
	}
        
	changeSet(author: "santhosh (generated)", id: "1424336787567-6") {
		addColumn(tableName: "flavors") {
			column(name: "physical_processor", type: "varchar(255)")
		}
	}
        
    
	changeSet(author: "santhosh (generated)", id: "1424336787567-7") {
		addColumn(tableName: "flavors") {
			column(name: "intel_aes_ni", type: "varchar(255)")
		}
	}
        
	changeSet(author: "santhosh (generated)", id: "1424336787567-8") {
		addColumn(tableName: "flavors") {
			column(name: "intel_avx", type: "varchar(255)")
		}
	}
	changeSet(author: "santhosh (generated)", id: "1424336787567-9") {
		addColumn(tableName: "flavors") {
			column(name: "intel_turbo", type: "varchar(255)")
		}
	}
	
//	changeSet(author: "santhosh (generated)", id: "1424336787567-3") {
//		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "ecu", tableName: "flavors")
//	}

	
	changeSet(author: "santhosh (generated)", id: "1424336787567-5") {
		dropColumn(columnName: "rootgb", tableName: "flavors")
	}
}
