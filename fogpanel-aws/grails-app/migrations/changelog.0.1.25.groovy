databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424876833179-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "architecture", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424876833179-2") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "hypervisor", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424876833179-3") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "kernel_id", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424876833179-4") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "private_dns_name", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424876833179-5") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "private_ip_address", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424876833179-6") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "public_dns_name", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424876833179-7") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "ramdisk_id", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424876833179-8") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "root_device_name", tableName: "virtual_machine")
	}

	changeSet(author: "az (generated)", id: "1424876833179-9") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "root_device_type", tableName: "virtual_machine")
	}
        
}
