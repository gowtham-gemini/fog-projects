databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "mailtemplate-resizedisk") {
            insert(tableName: "mail_template") {
                column(name: "id", valueNumeric: 32)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "resizeDisk")                
                column(name: "subject", value: "resizeDisk")
                column(name: "has_header", valueNumeric: 1)
                column(name: "has_footer", valueNumeric: 1)
                column(name: "has_signature", valueNumeric: 1)                
                column(name: "title", value: "createDisk")                
                column(name: "content", value: '<div class="mainarea" style="width:600px; height:auto; float:left; border:1px solid #ececec;">'+
                                        '<div class="maincontent" style="width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;">'+
                                        '<h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">'+
                                        '<i><b> Volume Resize</b></i></h1>'+
                                        '<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Hello [userName] ,</h2>'+
                                        '<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Volume Resized , below is details of the volume.<br /></p>Volume Name: [volumeName]<br /><br /> Attached VM: [attachedVM]<p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">Regards,</p><p style=" font-weight: bolder; font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px"><b>[signature]</b></p></div><div><b></b></div>'+
                                        '</div>')   
            }

        }
}
