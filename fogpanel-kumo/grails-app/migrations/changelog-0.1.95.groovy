databaseChangeLog = {
    changeSet(author: "nandhini", id: "mail_template_3") {
        sqlFile( path: "mailTemplate1.sql")           
    }
    changeSet(author: "nandhini", id: "organization_billing_update") {
        sqlFile( path: "organizationInfoUpdate1.sql")           
    }        
}
