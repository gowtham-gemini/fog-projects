

<!doctype html>
<html>
  <head>
    <g:set var="entityName" value="${message(code: 'diskOffering.label', default: 'DiskOffering')}" />
    <title>
      <g:message code="default.list.label" args="[entityName]" />
    </title>
    <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
    <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/resources/', file: 'Grid.css')}" media="screen" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/enhanced/resources/claro/', file: 'EnhancedGrid.css')}" media="screen" type="text/css">
    <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
            data-dojo-config = "async: true, parseOnLoad:true">
    </script>
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
        "dojo/ready",
        "dojox/data/HtmlStore"], function() {
            var list = dojo.query('#disk_list');
           // alert(list.value);
          });
       //          ], function(ready) {
      //            var listItems = dojo.query('#list_values td');
      //            dojo.forEach(listItems, function(el) {
      ////               alert(el.innerHTML);
      //             
      //            });

      //        number.push('5','6');
      //        alert(number);
      //        diskData.items.push([{ DisplayText:'el.display', name:'John', Customized: 'true',
      //        DiskSize: '5', DomainId:'5', Tags: 'xxx'}]);
      //        alert(diskData.items.name.innerHTML);
           //    });

      //         var number = ['1','2', '3', '4'];
      //         var diskData = {
      //               identifier: 'name', 
      //               items: [
      //                { DisplayText:'el.display', name:'John', Customized: 'true',
      //                   DiskSize: '5', DomainId:'5', Tags: 'xxx'
      //                }          
      //               
      //              ]
      //            };
      //          
      var diskField = [ 
        [  
          { field: "displayText", name: "DisplayText", width: 10 },
          { field: "name", name: "Name", width: 10 },
          { field: "customized", name: "Customized", width: 10 },
          { field: "diskSize", name: "Disk Size", width: 10 },
          { field: "domainId", name: "Domain Id", width: 10 },
          { field: "tags", name: "Tags", width: 10 }
        ]
      ];
    </script>                
    </head>
    <body class="claro">
      <a href="#list-diskOffering" class="skip" tabindex="-1">
        <g:message code="default.link.skip.label"
                   default="Skip to content&hellip;"/>
      </a>
      <div class="nav" role="navigation">
        <ul>
          <li>
            <a class="home" href="${createLink(uri: '/')}">
              <g:message code="default.home.label"/>
            </a>
          </li>
          <li>
          <g:link class="create" action="create">
            <g:message code="default.new.label" args="[entityName]" />
          </g:link>
        </li>
        </ul>
      </div>
      <div id="list-diskOffering" class="content scaffold-list"
           role="main">
        <h1>
          <g:message code="default.list.label" args="[entityName]" />
        </h1>
        <g:if test="${flash.message}">
          <div class="meszsage" role="status">${flash.message}
          </div>
        </g:if>
        <table id="list_values">
          <thead>
            <tr>
          <g:sortableColumn property="displayText" title="${message(code: 'diskOffering.displayText.label',
              default: 'displayText')}" />
          <g:sortableColumn property="name" title="${message(code: 'diskOffering.name.label', default: 'name')}" />
					
          <g:sortableColumn property="customized" title="${message(code: 'diskOffering.customized.label', default: 'customized')}" />

          <g:sortableColumn property="diskSize" title="${message(code: 'diskOffering.diskSize.label', default: 'diskSize')}" />

          <g:sortableColumn property="domainId" title="${message(code: 'diskOffering.domainId.label', default: 'domainId')}" />

          <g:sortableColumn property="tags" title="${message(code: 'diskOffering.tags.label', default: 'tags')}" />
          </tr>
          </thead>
          <tbody>
          <g:each in="${diskOfferingInstanceList}" status="i" var="diskOfferingInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
              <td>
                  <g:link action="show" id="${diskOfferingInstance.id}">
                  ${fieldValue(bean: diskOfferingInstance, field: "displayText")}
                 
                  </g:link>
              </td>
              <td id="name">${fieldValue(bean: diskOfferingInstance, field: "name")}</td>

              <td><g:formatBoolean boolean="${diskOfferingInstance.customized}" /></td>
              
              <td>${fieldValue(bean: diskOfferingInstance, field: "diskSize")}</td>

              <td>${fieldValue(bean: diskOfferingInstance, field: "domainId")}</td>

              <td>${fieldValue(bean: diskOfferingInstance, field: "tags")}</td>
              </tr>
          </g:each>
          </tbody>
        </table>
        <div class="pagination">
          <g:paginate total="${diskOfferingInstanceTotal}"/>
        </div>      
      </div>
      <h1> Disk Offering List </h1>
      <div data-dojo-type="dojox.data.HtmlStore"
           data-dojo-props="dataId:'list_values'"
           data-dojo-id="gridStore">
      </div>
      <div id="grid"
            data-dojo-type="dojox.grid.DataGrid"
            data-dojo-props="store:gridStore,
            structure:diskField,
            query:{},
            rowsPerPage:40"
            style="width: 1000px">
      </div>
      
      <div id="disk_list">
          <div> </div>
    </body>
    </html>
