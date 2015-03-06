
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Disk Offering</title>
    <link rel = "stylesheet" href = "${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
    
    <style type="text/css">
      .element {
        margin-top: 5px;
      }
      
      .element .dijitTextBox {
        width: 330px;
/*            border-radius: 5px;*/
        margin-top: 10px;
      }
      
      .element label {
        float: left;
        margin-top: 10px; 
        width: 150px;
        text-align: right;
        margin-right: 10px;
      }
      
      .element .dijitCheckBox {
        margin-top: 10px;
      }
      
       .element .dijitButton {
          margin: 15px 0 0 100px;
       }
    </style>
    <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
            data-dojo-configs = "async: true, parseOnLoad:true">
    </script>
    <script type="text/javascript">
      require([
        "dojo",
        "dojo/parser",
        "dojo/dom",
        "dijit/form/Button",
        "dijit/form/ValidationTextBox",
        "dijit/form/CheckBox",
        "dojo/query",
        "dojox/validate/regexp"]);
      
      function validate() {
        var disk_data = dojo.byId("disk_offering");
        var widgets = dijit.registry.findWidgets(disk_data);
        var firstNode = null;
            dojo.forEach(widgets, function(el) {
                if (el.validate && !el.validate()) {
                    el.focus();
                    if (!firstNode) {
                    	firstNode = el;
                    }
                }
            });
            if (firstNode) {
              firstNode.focus();
            }
          }
  </script>
  </head>
  <body class="claro">
    <h1>Create Disk Offering</h1>
    <div  id = "disk_offering">
      <div class="element">
      <label for="text_name">Display Text </label>
      <input type="text" 
             data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="promptMessage:'Enter text here', 
             invalidMessage: 'invalid Text',
             required: 'required', placeHolder: 'Enter Text', 
             regExp: '[a-z0-9]{4,20}'" 
             name="lastname" id="text_name"> 
      </div>
      <div class="element"> 
        <label for="name">Name</label>
        <input type="text" 
               data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="promptMessage:'Enter name', 
               invalidMessage: 'invalid name',
               required: 'required', placeHolder: 'Enter name', 
               regExp: '[a-z0-9]{4,20}'" 
               name="name" id="name">
      </div>
      <div class="element">
        <label for="disk_size"> Disk Size</label>
        <input type="text" 
               data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="promptMessage:'Enter disk Size', 
               invalidMessage: 'invalid size',
               required: 'required', placeHolder: 'Enter disk size', 
               regExp: '[0-9]{1,2}'" 
               name="disk_size" id="disk_size"> 
      </div>
      <div class="element">
        <label for="domain_id"> Domain id</label>
        <input type="text" 
               data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="promptMessage:'Enter domain id', 
               invalidMessage: 'invalid id',
               required: 'required', placeHolder: 'Enter domain id', 
               regExp: '[a-zA-Z]{3,5}'" 
               name="domain_id" id="domain_id"> 
      </div>
      <div class="element">
        <label for="tags">Tags</label>
        <input type="text" 
               data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="promptMessage:'Enter tag', 
               invalidMessage: 'invalid tag',
               required: 'required', placeHolder: 'Enter tag', 
               regExp: '[a-zA-z]{3,20}'" 
               name="tags" id="tags">
      </div>
      <div class="element">
        <label for="custom"> Customized</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
               data-dojo-props="checked: false" id="custom"
               name="custom" value= "">
      </div>
      <div class="element">
        <button type="submit" data-dojo-type= "dijit/form/Button" 
                onclick="validate()">
          Create
        </button>
      </div>
    </div>
  </body>
</html>
