databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1402557087743-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "security_group_reference_id", tableName: "virtual_machine")
	}
}
