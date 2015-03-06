

<!doctype html>
<html>
  <head>
  <g:set var="entityName" value="${message(code: 'diskOffering.label', default: 'DiskOffering')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
  <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
  <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/resources/', file: 'Grid.css')}" media="screen" type="text/css">
  <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/enhanced/resources/claro/', file: 'EnhancedGrid.css')}" media="screen" type="text/css">

  <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
            data-dojo-config = "async: true, parseOnLoad:true"></script>
 <script type="text/javascript">
   require([
      "dojo/parser",
      "dojo/dom",
      "dijit/form/ValidationTextBox",
      "dijit/form/Button",
      "dijit/form/CheckBox",
      "dijit/form/RadioButton",
      "dojox/grid/DataGrid",
      "dojo/data/ItemFileWriteStore",
      "dojo/query",
      "dojo/NodeList-traverse",
      "dojo/store/JsonRest",
      "dojo/store/Memory",
      "dojo/store/Cache",
      "dojo/data/ObjectStore",
      "dojo/ready"
    ], function(ready, JsonRest){
      var myStore = new dojo.store.JsonRest({target:"jdbc:mysql://localhost/az_cloud/disk_offering"});
      grid = new dojox.grid.DataGrid({
        store: dataStore = dojo.data.ObjectStore({objectStore: myStore}),
        structure: [
          { field: "displayText", name: "Display Text", width: 10 },
          { field: "name", name: "Name", width: 10 },
          { field: "customized", name: "Customized", width: 10 },
          { field: "diskSize", name: "Disk Size", width: 10 },
          { field: "domainId", name: "Domain Id", width: 10 },
          { field: "tags", name: "Tags", width: 10 }   
        ]
      }, "target-node-id"); // make sure you have a target HTML element with this id
      grid.startup(); 
    });
    </script>              
  </head>
  <body class="claro">
    <div id="target-node-id" data-dojo-props="store:dataStore">
    </div>
  </body>
  </html>
