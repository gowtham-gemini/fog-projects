
require([ "dojo", "dijit/dijit" ], function(dojo, dijit) { 
	//"use strict";
	
	controller({ 
		name: "config",
		module: "admin",
		filePath: "/js/app/config/config.js",
		layout: {
                    name: "config_index",
                    containerId: "content"
		},			
		scaffold: true,
		scaffoldParams: {
			restUrl: "/api/config/",	
			listDetails: {
				id: 'configList',
				layout: [[
					      {name: 'Id', field: 'id', editable: false},
					      {name: 'Name', field: 'name', editable: false},
					      {name: 'Value', field: 'value', editable: false},
					      {name: "...", field: "id", formatter: function(item) {
					    	  	return core.ui.createButton("Edit", "#/admin/config/edit/" + item);			    	  
					      }, type: dojox.grid.cells._Widget,  editable: false},
					      {name: "...", field: "id", formatter: function(item) { 
					    	   return core.ui.createButton("Delete", "#/admin/config/remove/" + item);
					      }, type: dojox.grid.cells._Widget,  editable: false}
					    ]],
				listConfig: ""				
			}
		}
	},
	{
		"auth": action(function() {
			dojo.byId(core.ui.getContentId()).innerHTML = "<h1>TEST</h1>";
		}),
		
		"login": action(function() {
			dojo.byId(core.ui.getContentId()).innerHTML = "<h1>Login</h1>";
		})
	});
});