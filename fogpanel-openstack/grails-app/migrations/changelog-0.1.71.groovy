databaseChangeLog = {	    
    
    changeSet(author: "Nandhini (generated)", id: "1424258099933-16") {
        createIndex(indexName: "name_uniq_1424258098744", tableName: "invitation", unique: "true") {
            column(name: "name")
        }    
    }	
}
