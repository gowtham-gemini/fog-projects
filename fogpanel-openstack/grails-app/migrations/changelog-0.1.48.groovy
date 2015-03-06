databaseChangeLog = {
    changeSet(author: "Nandhini (generated)", id: "billable-migration-1") {            
        sqlFile( path: "billableItem1.sql")     
    }	
    
    changeSet(author: "Nandhini (generated)", id: "billable-update-1") {
        insert(tableName: "billable_item") {                
            column(name: "id", valueNumeric: 14)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "common.billableItem.floatingIP")
            column(name: "reference_item_name", value: "floatingIP")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
            column(name: "has_zone", valueNumeric: 1)
        } 
    }
}
