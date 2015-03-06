databaseChangeLog = {
    changeSet(author: "Nandhini (generated)", id: "1419417092414-1") {
        createTable(tableName: "miscellaneous_offer") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "miscellaneousPK")
            }
            column(name: "version", type: "bigint") {
                    constraints(nullable: "false")
            }

            column(name: "cost", type: "double precision") {
                    constraints(nullable: "false")
            }

            column(name: "description", type: "varchar(255)") {
                    constraints(nullable: "false")
            }

            column(name: "name", type: "varchar(255)") {
                    constraints(nullable: "false")
            }

            column(name: "unit", type: "varchar(255)") {
                    constraints(nullable: "false")
            }
        }
    }
    changeSet(author: "Nandhini (generated)", id: "1419417092414-2") {
        createTable(tableName: "miscellaneous_offer_region_cost") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "miscellaneousPK")
            }
            column(name: "version", type: "bigint") {
                constraints(nullable: "false")
            }

            column(name: "cost", type: "double precision") {
                    constraints(nullable: "false")
            }

            column(name: "miscellaneous_offer_id", type: "bigint") {
                    constraints(nullable: "false")
            }

            column(name: "region_id", type: "bigint") {
                    constraints(nullable: "false")
            }
        }
    }
    changeSet(author: "Nandhini (generated)", id: "1419417092414-3") {
        createTable(tableName: "miscellaneous_offer_zone_cost") {
            column(autoIncrement: "true", name: "id", type: "bigint") {
                    constraints(nullable: "false", primaryKey: "true", primaryKeyName: "miscellaneousPK")
            }

            column(name: "version", type: "bigint") {
                    constraints(nullable: "false")
            }

            column(name: "cost", type: "double precision") {
                    constraints(nullable: "false")
            }

            column(name: "miscellaneous_offer_id", type: "bigint") {
                    constraints(nullable: "false")
            }

            column(name: "zone_id", type: "bigint") {
                    constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Nandhini (generated)", id: "1419417092414-17") {
            createIndex(indexName: "FK_89s5oelut842lucue6shqig16", tableName: "miscellaneous_offer_region_cost") {
                    column(name: "miscellaneous_offer_id")
            }
    }

    changeSet(author: "Nandhini (generated)", id: "1419417092414-18") {
            createIndex(indexName: "FK_iryovyc3ubb2hqblrm9jhr8ts", tableName: "miscellaneous_offer_region_cost") {
                    column(name: "region_id")
            }
    }

    changeSet(author: "Nandhini (generated)", id: "1419417092414-19") {
            createIndex(indexName: "FK_ikbrwcmrh3vpends73gleaor9", tableName: "miscellaneous_offer_zone_cost") {
                    column(name: "zone_id")
            }
    }

    changeSet(author: "Nandhini (generated)", id: "1419417092414-20") {
            createIndex(indexName: "FK_qfu3l8lga9kd72mc852wbn5v7", tableName: "miscellaneous_offer_zone_cost") {
                    column(name: "miscellaneous_offer_id")
            }
    }    

    changeSet(author: "Nandhini (generated)", id: "1419417092414-11") {
            addForeignKeyConstraint(baseColumnNames: "miscellaneous_offer_id", baseTableName: "miscellaneous_offer_region_cost", constraintName: "FK_89s5oelut842lucue6shqig16", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "miscellaneous_offer", referencesUniqueColumn: "false")
    }

    changeSet(author: "Nandhini (generated)", id: "1419417092414-12") {
            addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "miscellaneous_offer_region_cost", constraintName: "FK_iryovyc3ubb2hqblrm9jhr8ts", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
    }

    changeSet(author: "Nandhini (generated)", id: "1419417092414-13") {
            addForeignKeyConstraint(baseColumnNames: "miscellaneous_offer_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK_qfu3l8lga9kd72mc852wbn5v7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "miscellaneous_offer", referencesUniqueColumn: "false")
    }

    changeSet(author: "Nandhini (generated)", id: "1419417092414-14") {
            addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "miscellaneous_offer_zone_cost", constraintName: "FK_ikbrwcmrh3vpends73gleaor9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
    }
        
    changeSet(author: "Nandhini (generated)", id: "1419408584937-17") {
        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 1)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Image Snapshot Cost")
            column(name: "name", value: "Image Snapshot")
            column(name: "unit", value: "per GB/Hr")
            column(name: "cost", valueNumeric: 0)                                            
        }
        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 2)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Volume Snapshot Cost")
            column(name: "name", value: "Volume Snapshot")
            column(name: "unit", value: "per GB/Hr")
            column(name: "cost", valueNumeric: 0)   
        }
        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 3)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Network Cost")
            column(name: "name", value: "Network")
            column(name: "unit", value: "per Hr")
            column(name: "cost", valueNumeric: 0)   
        }
        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 4)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Bandwidth Read Cost")
            column(name: "name", value: "Bandwidth Read")
            column(name: "unit", value: "per GB")
            column(name: "cost", valueNumeric: 0)   
        }
        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 5)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Bandwidth Write Cost")
            column(name: "name", value: "Bandwidth Write")
            column(name: "cost", valueNumeric: 0)   
            column(name: "unit", value: "per GB")
        }

        insert(tableName: "miscellaneous_offer") {
            column(name: "id", valueNumeric: 6)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Floating IP Cost")
            column(name: "name", value: "Floating IP")
            column(name: "unit", value: "per Hr")
            column(name: "cost", valueNumeric: 0)   
        }
    }
    
    changeSet(author: "Nandhini (generated)", id: "1419408584937-18") {
        insert(tableName: "billable_item") {                
            column(name: "id", valueNumeric: 11)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "common.billableItem.bandwidthRead")
            column(name: "reference_item_name", value: "bandwidthRead")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
            column(name: "has_zone", valueNumeric: 0)
        }    
        insert(tableName: "billable_item") {                
            column(name: "id", valueNumeric: 12)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "common.billableItem.bandwidthWrite")
            column(name: "reference_item_name", value: "bandwidthWrite")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
            column(name: "has_zone", valueNumeric: 0)
        }
        
        insert(tableName: "billable_item") {                
            column(name: "id", valueNumeric: 13)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "common.billableItem.imageSnapshot")
            column(name: "reference_item_name", value: "imageSnapshot")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
            column(name: "has_zone", valueNumeric: 0)
        }
        
        insert(tableName: "billable_item") {                
            column(name: "id", valueNumeric: 14)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "common.billableItem.network")
            column(name: "reference_item_name", value: "network")
            column(name: "tax_id", valueNumeric: 1)
            column(name: "customized", valueNumeric: 0)
            column(name: "enabled", valueNumeric: 1)
            column(name: "discountable", valueNumeric: 0)
            column(name: "has_zone", valueNumeric: 1)
        }
        insert(tableName: "billable_item") {                
            column(name: "id", valueNumeric: 15)
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
