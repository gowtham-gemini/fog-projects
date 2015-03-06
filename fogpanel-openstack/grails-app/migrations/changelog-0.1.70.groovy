databaseChangeLog = {
    
    changeSet(author: "Nandhini (generated)", id: "1423820601080-14") {            
        
         insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 7)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Object Storage Cost")
            column(name: "name", value: "Object Storage")
            column(name: "unit", value: "per GB/hr")
            column(name: "cost", value: 0)
            
        }	
        
        insert(tableName: "billable_item") {
            column(name: "id", valueNumeric: 15)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "common.billableItem.objectStorage")
            column(name: "reference_item_name", value: "objectStorage")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
            column(name: "has_zone", valueNumeric: 0)                        
        }
    }	
}
