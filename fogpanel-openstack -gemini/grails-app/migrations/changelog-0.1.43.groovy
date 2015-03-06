databaseChangeLog = {
    changeSet(author: "Nandhini (generated)", id: "Initial-BillableItem_images") {
        insert(tableName: "billable_item") {                
            column(name: "id", valueNumeric: 10)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "common.billableItem.images")
            column(name: "reference_item_name", value: "images ")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
            column(name: "has_zone", valueNumeric: 0)
        }        
    }	
}
