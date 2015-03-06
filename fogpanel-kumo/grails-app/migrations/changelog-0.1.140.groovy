databaseChangeLog = {

        changeSet(author: "Nandhini (generated)", id: "14009254453191") {
                addColumn(tableName: "template") {
                        column(name: "architecture", type: "varchar(255)") {
                                constraints(nullable: "false")
                        }
                }
        }

        changeSet(author: "Nandhini (generated)", id: "14009254453192") {
                modifyDataType(columnName: "cpu_number", newDataType: "double precision", tableName: "computing_offer")
        }

        changeSet(author: "Nandhini (generated)", id: "14009254453193") {
                addNotNullConstraint(columnDataType: "double precision", columnName: "cpu_number", tableName: "computing_offer")
        }

        changeSet(author: "Nandhini (generated)", id: "14009254453194") {
                modifyDataType(columnName: "memory", newDataType: "double precision", tableName: "computing_offer")
        }

        changeSet(author: "Nandhini (generated)", id: "14009254453195") {
                addNotNullConstraint(columnDataType: "double precision", columnName: "memory", tableName: "computing_offer")
        }

        changeSet(author: "Nandhini (generated)", id: "140092544531911") {
                createIndex(indexName: "FK_296wkqwal09diune025bhqmy3", tableName: "account") {
                        column(name: "billing_country_id")
                }
        }

        changeSet(author: "Nandhini (generated)", id: "140092544531912") {
                createIndex(indexName: "FK_5ta5bijgwndhkhsv98kv5i6js", tableName: "account") {
                        column(name: "country_id")
                }
        }

        changeSet(author: "Nandhini (generated)", id: "140092544531913") {
                createIndex(indexName: "FK_99vgsqatibaqbaar8onngjijq", tableName: "account") {
                        column(name: "state_id")
                }
        }

        changeSet(author: "Nandhini (generated)", id: "140092544531914") {
                createIndex(indexName: "FK_asmmv3yhdupfaev30onvqm5lg", tableName: "account") {
                        column(name: "domain_id")
                }
        }

        changeSet(author: "Nandhini (generated)", id: "140092544531915") {
                createIndex(indexName: "FK_e0e4fj7pb4j008n57dl563yg0", tableName: "account") {
                        column(name: "billing_state_id")
                }
        }

        changeSet(author: "Nandhini (generated)", id: "14009254453196") {
                addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FK_296wkqwal09diune025bhqmy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
        }

        changeSet(author: "Nandhini (generated)", id: "14009254453197") {
                addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FK_e0e4fj7pb4j008n57dl563yg0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
        }

        changeSet(author: "Nandhini (generated)", id: "14009254453198") {
                addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FK_5ta5bijgwndhkhsv98kv5i6js", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
        }

        changeSet(author: "Nandhini (generated)", id: "14009254453199") {
                addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "account", constraintName: "FK_asmmv3yhdupfaev30onvqm5lg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
        }

        changeSet(author: "Nandhini (generated)", id: "140092544531910") {
                addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FK_99vgsqatibaqbaar8onngjijq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
        }
}