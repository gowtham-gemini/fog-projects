databaseChangeLog = {
    changeSet(author: "nandhini (generated)", id: "1382596382904-1") {
        addColumn(tableName: "security_group_template") {
            column(name: "base_os", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }                
}
