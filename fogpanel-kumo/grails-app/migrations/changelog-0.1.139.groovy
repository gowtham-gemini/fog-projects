databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1400835456392-1") {
                addColumn(tableName: "template") {
                        column(name: "hypervisor", type: "varchar(255)") {
                                constraints(nullable: "false")
                        }
                }
        }

	changeSet(author: "gowtham (generated)", id: "1400835456392-2") {
		addColumn(tableName: "virtual_machine") {
			column(name: "hypervisor", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
	
	changeSet(author: "gowtham (generated)", id: "1400835456392-7") {
		dropForeignKeyConstraint(baseTableName: "template", constraintName: "FKB13ACC7AB5EBBAB")
	}

	changeSet(author: "gowtham (generated)", id: "1400835456392-8") {
		dropForeignKeyConstraint(baseTableName: "virtual_machine", constraintName: "FKA8EBCF13B5EBBAB")
	}
	
	changeSet(author: "gowtham (generated)", id: "1400835456392-19") {
		dropColumn(columnName: "hypervisor_id", tableName: "template")
	}
        
	changeSet(author: "gowtham (generated)", id: "1400835456392-20") {
                dropColumn(columnName: "hypervisor_id", tableName: "virtual_machine")
        }
}
