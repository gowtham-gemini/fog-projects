databaseChangeLog = {

	changeSet(author: "gowtham", id: "country-sql-update-file") {
            sqlFile( path: "countryUpdate.sql")           
        }
}
