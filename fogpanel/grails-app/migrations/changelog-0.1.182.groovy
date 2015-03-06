databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1409033878097-1") {
		createTable(tableName: "invitation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "invitationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "link", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1409033878097-2") {
		createIndex(indexName: "email_uniq_1409033877417", tableName: "invitation", unique: "true") {
			column(name: "email")
		}
	}
        changeSet(author: "Nandhini (generated)", id: "mailtemplate-invitationEmail") {    
            insert(tableName: "mail_template") {
                column(name: "id", valueNumeric: 34)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "invitationEmail")
                column(name: "subject", value: "Invitation Email")
                column(name: "has_header", valueNumeric: 1)
                column(name: "has_footer", valueNumeric: 1)
                column(name: "has_signature", valueNumeric: 1)                
                column(name: "title", value: "invitationEmail")
                column(name: "content", value: "<div class='mainarea' style='width:600px; height:auto; float:left; border:1px solid #ececec;'><div class='maincontent' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'><h1 style='font-family: Arial, Helvetica, sans-serif; font-size: 21px; font-weight: normal; color: rgb(234, 88, 0); margin: 0px; padding: 0px; height: auto; width: 400px;'>Signup invitation from {orgName} <br /> </h1><h2 style='font-family: Arial, Helvetica, sans-serif; font-size: 17px; color: rgb(102, 102, 102); margin: 15px 0px 0px; padding: 0px; height: auto; width: 400px;'> Hello [name] ,</h2><p style='margin-top: 15px; margin-bottom: 0px; font-size: 13px; padding: 0px; height: auto; width: 550px;'>{orgName} invites you to join its cloud services.<br /> <span style='line-height: 1.6666666666666667;'>you can signup by clicking on the following link</span> <a href='{verifyLink}'>{verifyLink}</a></p><p style='margin-top: 15px; margin-bottom: 0px; color: rgb(51, 51, 51); font-size: 13px; padding: 0px; height: auto; width: 550px;'>Regards, <br />{orgName}<br/> <b style='line-height: 1.6666666666666667;'>[signature]</b></p></div></div>")
        }
    }
}
