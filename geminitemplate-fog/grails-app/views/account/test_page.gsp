
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>                                                                                       
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.8.5/dijit/themes/claro/claro.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/dojo/1.8.5/dojo/dojo.js" data-dojo-config="async: true"></script>
        
        <script>
            window.AppInit = function() {
            require([
                "dojo",    
                "dojo/parser",  
                "dijit/dijit",
                "dojo/dom",  
                "dojo/query" ,
                "dijit/registry",                                   
                "dijit/form/Button",
                "dijit/form/TextBox"
                           
                ], function(dojo, parser, dom, query, registry, Button, TextBox) {       

                var myTextBox = new dijit.form.TextBox({
                    name: "firstname",
                    value: "" /* no or empty value! */,
                    placeHolder: "Enter Email"
                }, "email");
                myTextBox.startup();
                });
        };
        </script>
        <script type="text/javascript"  src = "Email_validator/email_validation.js">
        </script>
    </head>
    <body onload="AppInit()" class="claro">  
        <label>Email</label>
        <input type="text" name="firstname" id="email"  />
    </body>
</html>
