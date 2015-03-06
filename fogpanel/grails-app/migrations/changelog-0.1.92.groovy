databaseChangeLog = {
    changeSet(author: "nandhini", id: "mail_template") {
        sqlFile( path: "mailTemplate.sql")           
    }
    
    changeSet(author: "nandhini", id: "organization_billing_info") {
        sqlFile( path: "organizationInfoUpdate.sql")           
    }
}
