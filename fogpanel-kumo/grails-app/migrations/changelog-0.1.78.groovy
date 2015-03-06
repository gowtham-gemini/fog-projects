databaseChangeLog = {   
    changeSet(author: "gowtham (generated)", id: "mailtemplate-passwordreset") {    
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 1)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "verificationEmail")
            column(name: "subject", value: "verificationEmail")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "verificationEmail")
            column(name: "content", value: "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></meta><title>FogPanel- E-mail Verification</title></head><body><div id='main' style='width:600px; height:auto; margin:0 auto; padding:0;'><div class='mainarea' style='width:600px; height:auto; float:left; border:1px solid #ececec;'><div class='header' style='width:600px; height:55px; float:left; background:url(https://cloud.contegix.com/portal/custom/images/header.gif) repeat-x top left; margin:0; padding:0;'><img src='applicationUrl/FogPanel/static/images/fogpanel_logo.png' border='0' style='margin:5px 0 0 20px; padding:0;'/></div><div class='maincontent' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'><h1 style='font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px'> Please verify your email address</h1><h2 style='font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px'>Hello userName ,</h2><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>There is just one more step before you can access the full range of cloud services from FogPanel.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Your user name is  <B> userName </B>.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Please verify your email by clicking on <a style='color:#2c8bbc; font-weight:bold;' href='#verifyLink'>confirm</a> link: or verifyLink </p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Regards,</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>The FogPanel team</p></div><div id='footer' style='width:600px; height:auto; float:left; background:#f2f2f2; margin:15px 0 0 0; padding:0;'><div class='footer_content' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>This email message is related to your ongoing services with<a style='color:#2c8bbc; font-weight:bold;' href='#'> FogPanel </a>. Service messages contain important information related to the administration and smooth running of your account and cannot be unsubscribed.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>For further assistance, please contact our support desk by e-mailing<a style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:bold;color:#2c8bbc' href='mailto:support@cloud.FogPanel.com'>support@cloud.FogPanel.com</a> or calling 1-877-426-6834.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Copyright &copy; 2011-2013 FogPanel LLC</p></div></div></div></div></body></html>")
        }
                
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 2)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "creditLimit")
            column(name: "subject", value: "creditLimit")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "creditLimit")
            column(name: "content", value: '<meta content="text/html; charset=utf-8" http-equiv="Content-Type" /><title>FogPanel- Notification</title><div id="main" style="width:600px; height:auto; margin:0 auto; padding:0;"><div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;"><div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;"><h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">Credit limit Reached<br /></h1><h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Your account has reached [creditLimit]</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,<br /><b>[signature] </b><br /></p></div><div id="footer" style="width:600px; height:auto; float:left; background:#f2f2f2; margin:15px 0 0 0; padding:0;"><div class="footer_content" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;"><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">This email message is related to your ongoing services with<a href="#" style="color:#2c8bbc; font-weight:bold;"> FogPanel </a>. Service messages contain important information related to the administration and smooth running of your account and cannot be unsubscribed.</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">For further assistance, please contact our support desk by e-mailing<a href="mailto:support@cloud.FogPanel.com" style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:bold;color:#2c8bbc">support@cloud.FogPanel.com</a> or calling 1-877-426-6834.</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Copyright © 2011-2013 FogPanel LLC</p></div></div></div></div>')
        }

        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 3)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "suspended")
            column(name: "subject", value: "suspended")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "suspended")
            column(name: "content", value: "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></meta><title>FogPanel- Notification</title></head><body><div id='main' style='width:600px; height:auto; margin:0 auto; padding:0;'><div class='mainarea' style='width:600px; height:auto; float:left; border:1px solid #ececec;'><div class='header' style='width:600px; height:55px; float:left; background:url(https://cloud.contegix.com/portal/custom/images/header.gif) repeat-x top left; margin:0; padding:0;'><img src='applicationUrl/FogPanel/static/images/fogpanel_logo.png' border='0' style='margin:5px 0 0 20px; padding:0;'/></div><div class='maincontent' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'><h1 style='font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px'> Invoice Notification</h1><h2 style='font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px'>Hello userName ,</h2><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Your account is suspended due to credit over limit</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Regards,</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>The FogPanel team</p></div><div id='footer' style='width:600px; height:auto; float:left; background:#f2f2f2; margin:15px 0 0 0; padding:0;'><div class='footer_content' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>This email message is related to your ongoing services with<a style='color:#2c8bbc; font-weight:bold;' href='#'> FogPanel </a>. Service messages contain important information related to the administration and smooth running of your account and cannot be unsubscribed.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>For further assistance, please contact our support desk by e-mailing<a style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:bold;color:#2c8bbc' href='mailto:support@cloud.FogPanel.com'>support@cloud.FogPanel.com</a> or calling 1-877-426-6834.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Copyright &copy; 2011-2013 FogPanel LLC</p></div></div></div></div></body></html>")
        }    
                
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 4)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "passwordResetEmail")
            column(name: "subject", value: "passwordResetEmail")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "passwordResetEmail")
            column(name: "content", value: "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></meta><title>FogPanel- Password Reset</title></head><body><div id='main' style='width:600px; height:auto; margin:0 auto; padding:0;'><div class='mainarea' style='width:600px; height:auto; float:left; border:1px solid #ececec;'><div class='header' style='width:600px; height:55px; float:left; background:url(https://cloud.contegix.com/portal/custom/images/header.gif) repeat-x top left; margin:0; padding:0;'><img src='applicationUrl/FogPanel/static/images/fogpanel_logo.png' border='0' style='margin:5px 0 0 20px; padding:0;'/></div><div class='maincontent' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'><h1 style='font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px'> Password Reset</h1><h2 style='font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px'>Hello userName ,</h2><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Please visit this URL by clicking or copying it into your browser to reset your password.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Your user name is  <B> userName </B>.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Please Reset Password by clicking on <a style='color:#2c8bbc; font-weight:bold;' href='#verifyLink'>confirm</a> link: or verifyLink </p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Regards,</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>The FogPanel team</p></div><div id='footer' style='width:600px; height:auto; float:left; background:#f2f2f2; margin:15px 0 0 0; padding:0;'><div class='footer_content' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>This email message is related to your ongoing services with<a style='color:#2c8bbc; font-weight:bold;' href='#'> FogPanel </a>. Service messages contain important information related to the administration and smooth running of your account and cannot be unsubscribed.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>For further assistance, please contact our support desk by e-mailing<a style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:bold;color:#2c8bbc' href='mailto:support@cloud.FogPanel.com'>support@cloud.FogPanel.com</a> or calling 1-877-426-6834.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>Copyright &copy; 2011-2013 FogPanel LLC</p></div></div></div></div></body></html>")
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 5)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "header")                
            column(name: "subject", value: "header")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "header")                
            column(name: "content", value: 'Header Templates<br />')
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 6)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "footer")                
            column(name: "subject", value: "footer")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "header")                
            column(name: "content", value: "<div id='footer' style='width:600px; height:auto; float:left; background:#f2f2f2; margin:0px 0 0 0; padding:0;'><div class='footer_content' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'>	<p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0	0;padding:0;height:auto;width:550px'>This email message is related to your ongoing services with<a 	     style='color:#2c8bbc; font-weight:bold;' href='#'> FogPanel </a>. Service messages contain important information related to the administration and smooth running of your account and cannot be unsubscribed.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>For further assistance, please contact our support desk by e-mailing<a style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:bold;color:#2c8bbc' href='mailto:support@cloud.FogPanel.com'>support@cloud.FogPanel.com</a> or calling 1-877-426-6834.</p><p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:0px 0 0 0;padding:0;height:auto;width:550px'>Copyright &copy; 2011-2013 FogPanel LLC</p></div></div>")                
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 7)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountActiviation")                
            column(name: "subject", value: "accountActiviation")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountActiviation")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;"><div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;"> <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><i><b> Please verify your email address</b></i></h1>      <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">There is just one more step before you can access the full range of cloud services from FogPanel.</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Your user name is <b>[userName]</b>.</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Please verify your email by clicking on <a href="#verifyLink" style="color:#2c8bbc; font-weight:bold;">confirm</a> link: or <b>[verifyLink]</b> </p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>/signature/</b></p></div><div><b></b></div></div>')                
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 8)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountSettingGeneralInfoUpdate")                
            column(name: "subject", value: "accountSettingGeneralInfoUpdate")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountSettingGeneralInfoUpdate")                
            column(name: "content", value: '<br /><div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">' +
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h2 style="margin: 15px 0px 0px; padding: 0px; height: auto; width: 400px;"><font color="#666666" face="Arial, Helvetica, sans-serif">'+
                                            '<span style="font-size: 17px; line-height: 28px;">Account  General Setting   updated Info</span>'+
                                            '</font><br /><font color="#666666" face="Arial, Helvetica, sans-serif"><span style="font-size: 17px;">Hello [userName] ,</span></font></h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">'+
                                            'Your account general setting information has updated, verify updated info below. '+
                                            '</p><p style="font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: rgb(51, 51, 51); margin: 15px 0px 0px; padding: 0px; height: auto; width: 550px;">'+
                                            '<b>Account General settings Info</b><br /><br />First Name : [generalFirstName]<b> <br /></b>Last Name : [generalLastName]<b> <br /></b>Email : [generalEmail]<b> <br /></b>Address1 : [generalAddress1]<b>'+
                                            '<br /></b>Address2 : [generalAddress2]<b> <br /></b>City : [generalCity]<b> <br /></b>Country : [generalCountry]<b> <br /></b>State : [generalState]<b> <br /></b>Phone No : [generalPhone]<b> </b><br />Zip<b>  : </b> [generalZip]<b> '+
                                            '</b><b style="line-height: 1.6666666666666667;"> </b><b style="line-height: 1.6666666666666667;"> <br /></b><span style="line-height: 1.6666666666666667;"><br />Regards,</span></p>'+
                                            '<p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p>'+
                                            '</div><div><b></b></div></div>')               
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 9)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountSettingBillingInfoUpdate")                
            column(name: "subject", value: "accountSettingBillingInfoUpdate")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountSettingBillingInfoUpdate")               
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+    
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                            '<i><b> Account Billing Settings Updated Successfully...</b></i></h1>'+
                                            '<h2 style="font-family: Arial, Helvetica, sans-serif; font-size: 17px; color: rgb(102, 102, 102); margin: 15px 0px 0px; padding: 0px; height: auto; width: 400px;">'+
                                            'Hello <span>[userName]</span><span style="line-height: 1.6666666666666667;"> </span><span style="font-weight: bold; line-height: 1.6666666666666667;">,</span></h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Your account Billing setting information has updated, verify updated info below. </p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Billing Address1 : [billingAddress1]<b> <br /></b>Billing Address2 : [billingAddress2]<b> <br /></b>Billing City : [billingCity]<b> <br /></b>Billing Country : [billingCountry]<b> <br /></b>Billing State : [billingState]<b> <br /></b>Billing Phone No : [billingPhone]<b style="line-height: 1.6666666666666667;"> <br /></b><span style="line-height: 1.6666666666666667;">Billing Zip</span><b style="line-height: 1.6666666666666667;">  : </b><span style="line-height: 1.6666666666666667;"> [billingZip]</span><b style="line-height: 1.6666666666666667;"> </b><b style="line-height: 1.6666666666666667;"> </b><b style="line-height: 1.6666666666666667;"> </b></p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><span style="line-height: 1.6666666666666667;">Regards,<br /> </span><b style="line-height: 1.6666666666666667;">[signature]</b></p></div><div><b></b></div>'+
                                            '</div>')                
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 10)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountPasswordReset")                
            column(name: "subject", value: "accountPasswordReset")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountPasswordReset")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family: Arial, Helvetica, sans-serif; font-size: 21px; color: rgb(234, 88, 0); margin: 0px; padding: 0px; height: auto; width: 400px;"><i>Password Reset Successfully</i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Your password has reset successfully, try to login your account with new password</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                            '</div>')                
        }
        
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 11)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountCancelled")                
            column(name: "subject", value: "accountCancelled")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountCancelled")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family: Arial, Helvetica, sans-serif; font-size: 21px; color: rgb(234, 88, 0); margin: 0px; padding: 0px; height: auto; width: 400px;"><i>Account cancelled</i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Your account has cancelled ,'+
                                            'please contact admin to enable your account</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">'+ 
                                            '<b>[signature]</b></p></div><div><b></b></div></div>')                
        }    
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 12)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountRefund")                
            column(name: "subject", value: "accountRefund")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountRefund")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+    
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">  <i><b>Account refund</b></i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Account Refund<br /></p>Regards,<b><br />[signature]</b></div><div><b></b></div>'+
                                            '</div>')               
        }        
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 13)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "suspendPage")                
            column(name: "subject", value: "suspendPage")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountRefund")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                            '<i><b> Suspend Ac</b></i></h1><h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Account suspended</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><span style="line-height: 1.6666666666666667;">Regards,</span></p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                            '</div>')                
        }        
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 14)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountDisabled")                
            column(name: "subject", value: "accountDisabled")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountDisabled")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                            '<i><b> Account Disabled<br /></b></i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>Your Account has disabled, please contact FogPanel admin<br /><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                            '</div>')               
        }
    
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 15)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountEnabled")                
            column(name: "subject", value: "accountEnabled")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountEnabled")                
            column(name: "content", value: '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                            '<i><b> Account Disabled<br /></b></i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>Your Account has Enabled.<br />'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div>')                
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 16)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "loginLocked")                
            column(name: "subject", value: "loginLocked")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "loginLocked")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><i><b>Account Locked<br /></b></i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Your account has locked please contact admin to unlock your account<br /></p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                            '</div>')                
        }         
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 17)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "loginUnlock")                
            column(name: "subject", value: "loginUnlock")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "loginUnlock")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">  <i><b>Account Unlocked</b></i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Account Unlocked<br /></p>Regards,<b><br />[signature]</b></div><div><b></b></div>'+
                                            '</div>')               
        } 
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 18)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "accountClose")                
            column(name: "subject", value: "accountClose")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "accountClose")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                            '<i><b> Please verify your email address</b></i></h1><h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello /userName/ ,</h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">There is just one more step before you can access the full range of cloud services from FogPanel.</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Your user name is  <b>/userName/</b>.</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Please verify your email by clicking on <a href="#verifyLink" style="color:#2c8bbc; font-weight:bold;">confirm</a> link: or <b>/verifyLink/</b> </p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>/signature/</b></p></div><div><b></b></div>'+
                                            '</div>')               
        } 
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 19)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "creditLimitReached")                
            column(name: "subject", value: "creditLimitReached")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "creditLimitReached")                
            column(name: "content", value: '')            
        } 
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 20)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "invoiceGenerated")                
            column(name: "subject", value: "invoiceGenerated")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "invoiceGenerated")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                            '<i><b> Invoice Generated<br /></b></i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                            '<br />Invoice generated to this account on [invoiceGeneratedDate]<br /> Next Billing Date: [nextBillingDate]<br />Regards,<b><br />[signature]</b></div><div><b></b></div>'+
                                            '</div>')                
        } 
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 21)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "paymentCreated")                
            column(name: "subject", value: "paymentCreated")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "paymentCreated")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                        '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                        '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">Payment Create  <br />Hello [userName] ,</h1>'+
                                        '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Paid</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Process Fee: [processingFee]<br />Amount Paid: [amount]<br />Total Amount: [totalAmount]</p>Regards,<b><br />[signature]</b></div><div><b></b></div>'+
                                        '</div>')               
        } 
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 22)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "paymentFailed")                
            column(name: "subject", value: "paymentFailed")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "paymentFailed")                
            column(name: "content", value: '<i><b><br /></b></i><div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;"><div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Payment Failed<br />Hello [userName] ,</h2><b>Payment failed<br /></b><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                            '</div>')                
        }         
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 23)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "recurringItemAdded")                
            column(name: "subject", value: "recurringItemAdded")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "recurringItemAdded")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                        '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                        '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                        '<i><b> Custom Item Addede<br /></b></i></h1>'+
                                        '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                        '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Recurring Item Added</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Name: [ItemName]<br />Amount: [amount]<br />Created Date: [createdDate]</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div>'+
                                        '</div>')              
        } 
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 24)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "customItemAdded")                
            column(name: "subject", value: "customItemAdded")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "customItemAdded")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                            '<i><b> Custom Item Addede<br /></b></i></h1>'+
                                            '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                            '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Custom Item Added</p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Name: [customItemName]<br />Amount: [amount]<br />Created Date: [createdDate]<br /></p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                            '</div>')              
        } 
        
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 25)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "defaultCardChanged")                
            column(name: "subject", value: "defaultCardChanged")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "customItemAdded")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                            '<i><b> Default Card Info<br /></b></i></h1> <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2><b>Your default card information  has been updated</b><br /> <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                            '</div>')             
        }     
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 26)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "createVm")                
            column(name: "subject", value: "createVm")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "createVm")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                    '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                    '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                    '<i><b> Create VM Info<br /></b></i></h1><h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                    '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Your Vm - [vmName] is created successfully.Please find below details of the VM.</p><p style="margin: 15px 0px 0px; padding: 0px; height: auto; width: 550px;"><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;">Virtual Machine Infra Summary </b><br /><font face="Arial, Helvetica, sans-serif" size="2">Zone: [vmZone]</font><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;"> <br /></b><font face="Arial, Helvetica, sans-serif" size="2">Host Name - [vmHostName]</font><br /><font face="Arial, Helvetica, sans-serif" size="2">Password: [vmPassword], </font><br /><font face="Arial, Helvetica, sans-serif" size="2">Main IP Address: [vmIp] </font><br /><font face="Arial, Helvetica, sans-serif" size="2">OS Template -[vmOsType]</font><br /><font face="Arial, Helvetica, sans-serif" size="2">Status - [vmStatus] </font><br /><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;">Virtual Machine Billing Summary</b><br /><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;">Computation      -  </b><font face="Arial, Helvetica, sans-serif" size="2">[vmOfferName], [vmCpuCore] CPU Core, Speed: [vmCpuSpeed] GHZ, </font><br /><font face="Arial, Helvetica, sans-serif" size="2">Memory:[vmOfferMemory] , Bandwidth: [vmBandwidth] GB,  <br />                              Setup Cost: $ [vmSetupCost], Running Cost: $ [vmRunningCost] per/Hr,</span></p><p style="margin: 15px 0px 0px; padding: 0px; height: auto; width: 550px;"><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;">Storage              -</b><font face="Arial, Helvetica, sans-serif" size="2">  [vmDiskName] [vmDiskSize]   [vmDiskCost]  </font><br /><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;">Template            -</b><font face="Arial, Helvetica, sans-serif" size="2">  [vmOsType], Root Disk Size:  [vmTemplateSize] GB, </font><br /><font face="Arial, Helvetica, sans-serif" size="2">                              Cost : [vmTemplateCost],</font><br /><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px; line-height: 1.6666666666666667;">Next Billing Date </b><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px; line-height: 1.6666666666666667;">-</b><font face="Arial, Helvetica, sans-serif" size="2"><span style="line-height: 1.6666666666666667;"> [vmNextBillingDate] <br /> </span></font><span style="font-family: Arial, Helvetica, sans-serif; font-size: small; line-height: 1.6666666666666667;">Regards,</span></p><p style="margin: 15px 0px 0px; padding: 0px; height: auto; width: 550px;"><font face="Arial, Helvetica, sans-serif" size="2"> </font><b style="color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px; line-height: 1.6666666666666667;">[signature]</b></p></div><div><b></b></div>'+
                    '</div>')              
        } 
        
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 27)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "createDisk")                
            column(name: "subject", value: "createDisk")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "createDisk")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                    '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                    '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                    '<i><b> Please verify your email address</b></i></h1>'+
                                    '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                    '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Volume has created , below is details of the volume.<br /></p>Volume Name: [volumeName]<br /><br /> Attached VM: [attachedVM]<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                    '</div>')              
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 28)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "createSnapshot")                
            column(name: "subject", value: "createSnapshot")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "createSnapshot")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                    '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                    '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                    '<i><b> Snapshot Created<br /></b></i></h1>'+
                    '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                    '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Snapshop created below is the information<br /></p><p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Name: [snapshotName]<br />Attached Volume:[attachedVolume]<br />Status: [status]<br />Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                    '</div>')              
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 29)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "scheduleMaintaince")                
            column(name: "subject", value: "scheduleMaintaince")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "scheduleMaintaince")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                    '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                      '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><i><b>sechdule maintaince <br /></b></i></h1>'+
                      '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                      '<p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">New scedule has created.<br />information:<br />Maintenance Date: [maintainDate]<br />Sescription: [description]<br />Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                        '</div>')             
        }
        insert(tableName: "mail_template") {
            column(name: "id", valueNumeric: 30)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "forgotPassword")                
            column(name: "subject", value: "forgotPassword")
            column(name: "has_header", valueNumeric: 1)
            column(name: "has_footer", valueNumeric: 1)
            column(name: "has_signature", valueNumeric: 1)                
            column(name: "title", value: "forgotPassword")                
            column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                            '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                            '<h1 style="font-family: Arial, Helvetica, sans-serif; font-size: 21px; font-weight: normal; color: rgb(234, 88, 0); margin: 0px; padding: 0px; height: auto; width: 400px;">Password Reset Email <br /></h1><h2 style="font-family: Arial, Helvetica, sans-serif; font-size: 17px; color: rgb(102, 102, 102); margin: 15px 0px 0px; padding: 0px; height: auto; width: 400px;">Hello [userName] ,</h2><h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><p style="margin-top: 15px; margin-bottom: 0px; color: rgb(51, 51, 51); font-size: 13px; padding: 0px; height: auto; width: 550px;">To reset password click this link below.<br /><span style="line-height: 1.6666666666666667;">Please verify your email by clicking on</span><span style="line-height: 1.6666666666666667;"> </span><a href="#verifyLink" style="line-height: 1.6666666666666667; color: rgb(44, 139, 188); font-weight: bold;">confirm</a><span style="line-height: 1.6666666666666667;"> </span><span style="line-height: 1.6666666666666667;">link: or</span><span style="line-height: 1.6666666666666667;"> [verifyLink]</b></p><p style="margin-top: 15px; margin-bottom: 0px; color: rgb(51, 51, 51); font-size: 13px; padding: 0px; height: auto; width: 550px;">Regards,<br /><b style="line-height: 1.6666666666666667;">[signature]</b></p></h1></div><div><b></b></div>'+
                                            '</div>')              
        }                
    }            
}