databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1400830831749-1") {
               addColumn(tableName: "computing_offer") {
                       column(name: "hypervisor", type: "varchar(255)") {
                               constraints(nullable: "false")
                       }
               }
       }      

	changeSet(author: "gowtham (generated)", id: "1400830831749-6") {
               dropForeignKeyConstraint(baseTableName: "computing_offer", constraintName: "FK_b1tv76pvjwn1kxtd1sxip9ycg")
        }
}
