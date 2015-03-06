databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1383804227322-1") {
		addColumn(tableName: "mail_template") {
			column(name: "has_footer", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1383804227322-2") {
		addColumn(tableName: "mail_template") {
			column(name: "has_header", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "nandhini (generated)", id: "1383804227322-3") {
		addColumn(tableName: "mail_template") {
			column(name: "has_signature", type: "bit")
		}
	}

	changeSet(author: "nandhini (generated)", id: "1383804227322-4") {
		addColumn(tableName: "mail_template") {
			column(name: "title", type: "varchar(5000)")
		}
	}		
    }
