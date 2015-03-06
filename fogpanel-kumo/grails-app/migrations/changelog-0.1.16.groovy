databaseChangeLog = {

    changeSet(author: "gowtham (generated)", id: "billableItem-2") {
		

	insert(tableName: "billable_item") {
            column(name: "id", valueNumeric: 6)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "Discount")
            column(name: "reference_item_name", value: "discount ")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
        }
        
        insert(tableName: "billable_item") {
            column(name: "id", valueNumeric: 7)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "Late Fee")
            column(name: "reference_item_name", value: "lateFee ")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
        }
        
        insert(tableName: "billable_item") {
            column(name: "id", valueNumeric: 8)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "Custom Item")
            column(name: "reference_item_name", value: "custom ")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 1)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
        }
        
    }
}
