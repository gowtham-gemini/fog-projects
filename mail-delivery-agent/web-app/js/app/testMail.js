/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var mail = {
    testMail: function () {

        var form = document.getElementById("mailConfigForm");
        console.log("**************Form");
        console.log("form" + form);
        var jsonData = {};

        var formData = $("#mailConfigForm").serializeArray();

        $.each(formData, function () {

            if (jsonData[this.name]) {

                if (!jsonData[this.name].push) {

                    jsonData[this.name] = [jsonData[this.name]];

                }

                jsonData[this.name].push(this.value || '');

            } else {

                jsonData[this.name] = this.value || '';

            }



        });

        console.log(jsonData);
        $.ajax(  
                {  
                    type: "POST",  
                    url: 'http://localhost:8080/fogpanel-mail-manager/config/testMailConfig',  
                    data: JSON.stringify(jsonData),  
                    contentType: "application/json; charset=utf-8",  
                    dataType: "json",  
                    beforeSend: function(x) {  
  
                                if (x && x.overrideMimeType) {  
                                 x.overrideMimeType("application/json;charset=UTF-8?");  
                                }  
                        },  
                    success: function() {  
                                alert('hi');  
                        }  
        });  
        
        


    }
};
window.mail = mail;
