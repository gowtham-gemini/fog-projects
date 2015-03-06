databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1403593293911-1") {
		addColumn(tableName: "invoice") {
			column(name: "discount", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1403593293911-2") {
		addColumn(tableName: "invoice") {
			column(name: "retention", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1403593293911-3") {
		addColumn(tableName: "invoice") {
			column(name: "sub_total1", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1403593293911-4") {
		addColumn(tableName: "invoice") {
			column(name: "sub_total2", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1403593293911-5") {
		addColumn(tableName: "invoice") {
			column(name: "sub_total3", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1403593293911-6") {
		addColumn(tableName: "invoice") {
			column(name: "tax", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}
        
        changeSet(author: "gowtham (generated)", id: "config-add-kumo") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 87)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "IVA Percentage")
                column(name: "name", value: "invoice.iva.percentage")
                column(name: "value", value: "16")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 88)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "Retention percentage")
                column(name: "name", value: "invoice.retention.percentage")
                column(name: "value", value: "4")
            }
	}
}
