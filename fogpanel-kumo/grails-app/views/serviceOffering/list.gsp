

<!DOCTYPE html>
<html>
	<head>
		
		<g:set var="entityName" value="${message(code: 'serviceOffering.label', default: 'ServiceOffering')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                
		<link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}"/>
		<link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/resources/', file: 'Grid.css')}" media="screen" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/enhanced/resources/claro/', file: 'EnhancedGrid.css')}" media="screen" type="text/css">
		
		<script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
		       data-dojo-config = "async: true, parseOnLoad:true"></script>
        
        <script type="text/javascript">
        require([
                 "dojo/parser",
                 "dojo/dom",
                 "dojo/ready",
                 "dojox/grid/DataGrid",
                 "dojo/data/ItemFileReadStore",
                 "dojo/data/ItemFileWriteStore",
                 "dojo/store/JsonRest",
                 "dojo/store/Memory",
                 "dojo/store/Cache",
                 "dojo/data/ObjectStore"
                 ]);
               
		var serviceOffering = [
		   [
		    { field: "cpuNumber", name: "Cpu Number"},
		    { field: "cpuSpeed", name: "Cpu Speed"},
		    { field: "displayText", name: "Display Text"  },
		    { field: "memory", name: "Memory"  },
		    { field: "name", name: "Name" },
		    { field: "domainId", name: "Domain Id"},
		    { field: "hostTags", name: "Host Tags"},
		    { field: "isSystem", name: "Is System"},
		    { field: "limitCpuUse", name: "Limit Cpu Use"},
		    { field: "networkRate", name: "Network Rate"},
		    { field: "offerHA", name: "Offer HA"},
		    { field: "storageType", name: "Storage Type"},
		    { field: "systemViruralMachineType", name: "System Virural Machine Type"},
		    { field: "tags", name: "Tags"}
		    ]
		  ];
       /* var diskData = {
                identifier: 'name', 
                items: [
                  { cpuNumber:'xyz', cpuSpeed:'John', displayText: 'true',
                      memory: '5', name:'5', domainId: 'xxx'
                  }
               ]  
        }
        function(ready){
        	
        		myStore = new dojo.store.JsonRest({target:"jdbc:mysql://localhost/az_cloud/service_offering"});
        	    grid = new dojox.grid.DataGrid({
                store: dataStore = dojo.data.ObjectStore({objectStore: myStore}),
                structure: [
                            { field: "cpuNumber", name: "Cpu Number"},
                            { field: "cpuSpeed", name: "Cpu Speed"},
                            { field: "displayText", name: "Display Text"  },
                            { field: "memory", name: "Memory"  },
                            { field: "name", name: "Name" },
                            { field: "domainId", name: "Domain Id"},
                            { field: "hostTags", name: "Host Tags"},
                            { field: "isSystem", name: "Is System"},
                            { field: "limitCpuUse", name: "Limit Cpu Use"},
                            { field: "networkRate", name: "Network Rate"},
                            { field: "offerHA", name: "Offer HA"},
                            { field: "storageType", name: "Storage Type"},
                            { field: "systemViruralMachineType", name: "System Virural Machine Type"},
                            { field: "tags", name: "Tags"}
                ]
            }, "grid_id"); 
            grid.startup();
        });*/
        </script>

	</head>
	<body class="claro">
		<a href="#list-serviceOffering" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-serviceOffering" class="content scaffold-list" role="main" >
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="cpuNumber" title="${message(code: 'serviceOffering.cpuNumber.label', default: 'cpuNumber')}" field="cpuNumber"/>
					
						<g:sortableColumn property="cpuSpeed" title="${message(code: 'serviceOffering.cpuSpeed.label', default: 'cpuSpeed')}" field="cpuSpeed"/>
					
						<g:sortableColumn property="displayText" title="${message(code: 'serviceOffering.displayText.label', default: 'displayText')}" field="displayText"/>
					
						<g:sortableColumn property="memory" title="${message(code: 'serviceOffering.memory.label', default: 'memory')}" field="memory"/>
					
						<g:sortableColumn property="name" title="${message(code: 'serviceOffering.name.label', default: 'name')}" field="name"/>
					
						<g:sortableColumn property="domainId" title="${message(code: 'serviceOffering.domainId.label', default: 'domainId')}" field="domainId"/>
						
						<g:sortableColumn property="hostTags" title="${message(code: 'serviceOffering.hostTags.label', default: 'hostTags')}" field="hostTags"/>
						
						<g:sortableColumn property="isSystem" title="${message(code: 'serviceOffering.isSystem.label', default: 'isSystem')}" field="isSystemm"/>
						
						<g:sortableColumn property="limitCpuUse" title="${message(code: 'serviceOffering.limitCpuUse.label', default: 'limitCpuUse')}" field="limitCpuUse"/>
						
						<g:sortableColumn property="networkRate" title="${message(code: 'serviceOffering.networkRate.label', default: 'networkRate')}" field="networkRate"/>
						
						<g:sortableColumn property="offerHA" title="${message(code: 'serviceOffering.offerHA.label', default: 'offerHA')}" field="offerHA"/>
						
						<g:sortableColumn property="storageType" title="${message(code: 'serviceOffering.storageType.label', default: 'storageType')}" field="storageType"/>
						
						<g:sortableColumn property="systemViruralMachineType" title="${message(code: 'serviceOffering.systemViruralMachineType.label', default: 'systemViruralMachineType')}" field="systemViruralMachineType"/>
						
						<g:sortableColumn property="tags" title="${message(code: 'serviceOffering.tags.label', default: 'tags')}" field="tags"/>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${serviceOfferingInstanceList}" status="i" var="serviceOfferingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${serviceOfferingInstance.id}">${fieldValue(bean: serviceOfferingInstance, field: "cpuNumber")}</g:link></td>
					
						<td>${fieldValue(bean: serviceOfferingInstance, field: "cpuSpeed")}</td>
					
						<td>${fieldValue(bean: serviceOfferingInstance, field: "displayText")}</td>
					
						<td>${fieldValue(bean: serviceOfferingInstance, field: "memory")}</td>
					
						<td>${fieldValue(bean: serviceOfferingInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: serviceOfferingInstance, field: "domainId")}</td>
						
						<td>${fieldValue(bean: serviceOfferingInstance, field: "hostTags")}</td>
						
						<td>${fieldValue(bean: serviceOfferingInstance, field: "isSystem")}</td>
						
						<td>${fieldValue(bean: serviceOfferingInstance, field: "limitCpuUse")}</td>
						
						<td>${fieldValue(bean: serviceOfferingInstance, field: "networkRate")}</td>
						
						<td>${fieldValue(bean: serviceOfferingInstance, field: "offerHA")}</td>
						
						<td>${fieldValue(bean: serviceOfferingInstance, field: "storageType")}</td>
						
						<td>${fieldValue(bean: serviceOfferingInstance, field: "systemViruralMachineType")}</td>
						
						<td>${fieldValue(bean: serviceOfferingInstance, field: "tags")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${serviceOfferingInstanceTotal}" />
			</div>
		</div>
		<!-- data grid -->	
		<h1>data grid</h1>
	    <div id="grid"
	        style="width: 800px; height: 300px;"
	        data-dojo-type="dojox.grid.DataGrid"
	        data-dojo-props="structure:serviceOffering">
	    </div>

	</body>
</html>
