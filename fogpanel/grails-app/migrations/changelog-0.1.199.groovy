databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1411647461049-1") {
		addColumn(tableName: "vpnconnection") {
			column(name: "billing_updated_date", type: "datetime")
		}
	}
}
