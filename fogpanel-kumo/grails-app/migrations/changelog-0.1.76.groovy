databaseChangeLog = {	
    changeSet(author: "nandhini (generated)", id: "1383804759389-3") {
        addNotNullConstraint(columnDataType: "bit", columnName: "has_signature", tableName: "mail_template")
    }	
}
