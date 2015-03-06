databaseChangeLog = {

        changeSet(author: "gowtham (generated)", id: "VPNConnection-BillableItem") {
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 20)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "common.billableItem.vpnConnection")
                column(name: "reference_item_name", value: "VPN Connection")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
                column(name: "has_zone", valueNumeric: 1)
                
            }
                    
            insert(tableName: "miscellaneous_offer") {
                column(name: "id", valueNumeric: 7)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "VPN Connection Cost")
                column(name: "name", value: "VPN Connection Cost")
                column(name: "unit", value: "per Hr")
            }
            
	}
}
