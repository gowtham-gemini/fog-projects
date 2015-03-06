databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "quartzConfg-1") {
		sqlFile( path: "quartz_tables.sql")
	}
}