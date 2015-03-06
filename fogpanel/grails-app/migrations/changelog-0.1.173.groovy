databaseChangeLog = {

        changeSet(author: "lakshmi", id: "paymentGateways-sql-file-default-value") {
            sqlFile( path: "paymentGatewaysDefaultValueUpdate2.sql")           
        }
}
