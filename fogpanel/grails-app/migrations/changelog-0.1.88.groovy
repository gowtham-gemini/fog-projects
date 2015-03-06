databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "account-update-1") {
		modifyDataType(columnName: "billing_phone_number", newDataType: "varchar(20)", tableName: "account")
	}  
          
        changeSet(author: "nandhini (generated)", id: "account-update-3") {
		modifyDataType(columnName: "phone_number", newDataType: "varchar(20)", tableName: "account")
	}
}
