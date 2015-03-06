databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1407413610814-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "local_ip", tableName: "remote_access_vpn")
	}

	changeSet(author: "Nandhini (generated)", id: "1407413610814-2") {
		dropIndex(indexName: "reference_id_uniq_1407392231635", tableName: "remote_access_vpn")
	}
}
