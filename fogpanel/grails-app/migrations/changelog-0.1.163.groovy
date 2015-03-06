databaseChangeLog = {

    changeSet(author: "lakshmi (generated)", id: "1406014072336-3") {
        modifyDataType(tableName: "payments", columnName: "payment_load", newDataType: "varchar(1200)")
    }

    changeSet(author: "lakshmi (generated)", id: "1406014072336-4") {
        modifyDataType(tableName: "payments", columnName: "payment_status", newDataType: "varchar(1200)")
    }
}
