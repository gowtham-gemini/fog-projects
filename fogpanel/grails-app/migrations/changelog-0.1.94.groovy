databaseChangeLog = {

	changeSet(author: "gowtham", id: "suspend-mail-sql-file") {
            sqlFile( path: "mailsuspendtemplateupdate.sql")           
        }
}
