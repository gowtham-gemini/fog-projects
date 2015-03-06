databaseChangeLog = {

        changeSet(author: "lakshmi", id: "paymentGateways-sql-file") {
            sqlFile( path: "paymentGateways.sql")           
        }
}
