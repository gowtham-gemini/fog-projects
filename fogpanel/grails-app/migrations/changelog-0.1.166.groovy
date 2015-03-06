databaseChangeLog = {

	changeSet(author: "lakshmi (generated)", id: "1406109700921-1") {
		addColumn(tableName: "payment_gateways") {
			column(name: "include_exclude", type: "varchar(255)")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1406109700921-2") {
		addColumn(tableName: "payment_gateways") {
			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1406109700921-3") {
		addColumn(tableName: "payment_gateways") {
			column(name: "processing_fee_amount", type: "double precision")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1406109700921-4") {
		addColumn(tableName: "payment_gateways") {
			column(name: "processing_fee_percent", type: "double precision")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1406109700921-5") {
		addColumn(tableName: "payments") {
			column(name: "gateway_name", type: "varchar(10000)")
		}
	}
}
