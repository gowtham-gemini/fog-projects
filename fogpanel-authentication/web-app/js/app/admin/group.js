"use strict";
require([
    "dojo",
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js",
    "dijit/dijit",
    "dojo/dom-class",
    "dojo/dom-construct",
    "dojo/store/JsonRest",
    "dojo/query",
    "dijit/registry",
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/PlotKit/green",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojox/charting/plot2d/ClusteredColumns",
    "dojox/charting/widget/Legend",
    "dojox/charting/plot2d/Markers",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/MultipleInvitation",
    "FogPanel/Wizard",
    "FogPanel/AccountStatus",
    "dijit/layout/BorderContainer",
    "dijit/form/DropDownButton",
    "dijit/layout/TabContainer",
    "dijit/TooltipDialog",
    "dijit/form/HorizontalRule",
    "dijit/form/HorizontalRuleLabels",
    "dijit/form/HorizontalSlider",
    "dijit/form/Textarea",
    "Zone/ZoneCost",
    "dojox/widget/rotator/Slide",
    "dojox/widget/Rotator",
    "dijit/form/Button",
    "dojox/validate/regexp",
    "dijit/form/ValidationTextBox",
    "dijit/form/RadioButton",
    "List/ListItem",
    "dijit/Dialog",
    "dijit/DropDownMenu",
    "dijit/MenuItem",
    "dojox/grid/enhanced/plugins/Menu",
    "dijit/popup",
    "dojo/_base/lang",
    "dojox/charting/widget/Chart2D",
    "dojox/charting/themes/Claro",   
    "dojo/dom-construct",
    "dojo/on",
    "FogPanel/VolumeListItem",
    "FogPanel/InstanceStatus",
    "dojox/widget/rotator/Pan",
    "dijit/form/CheckBox",
    "dijit/form/NumberSpinner",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "dojo/dnd/Source",
    "dijit/layout/TabContainer"
],
function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select, ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win, 
Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, ClusteredColumns, Legend) {
    window.translator = translator;            
    window.query = query;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Select = Select;
    window.ContentPane = ContentPane;
    window.EnhancedGrid = EnhancedGrid;
    window.DataGrid = DataGrid;
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;
    window.Chart = Chart;
    window.Pie = Pie;
    window.Lines = Lines;
    window.Magnify = Magnify;
    window.PlotKitGreen = PlotKitGreen;
    window.Tooltip = Tooltip;
    window.MoveSlice = MoveSlice;
    window.theme = theme;
    window.ColumnsPlot = ColumnsPlot;
    window.ClusteredColumns = ClusteredColumns;
    window.Legend = Legend;
    window.Highlight = Highlight;
    window.currentUserId = "";
    window.currentInvoiceId = "";
    controller({
        name: "groups",
        module: "admin",
        filePath: "/js/app/admin/group.js",
        layout: {
            name: "groups",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {
            
            core.ui.loadTemplate("groups", core.ui.getContentId());
            GroupsInfo.init();
        }),
        
        "view": action(function(id){
            
            core.ui.loadTemplate("accountList", core.ui.getContentId());
            GroupsInfo.listUsers(id);
        })
   });
});
var GroupsInfo = {
    init : function() {
        
        var getGroupsRestStore = new JsonRest({
            target: core.getContextPath()+"/api/group/list"
        });
        if (dijit.byId("groupDatas")) {
            dijit.byId("groupDatas").destroyRecursive();
        }
       
        var groupData = {
                items: []
            };

                    dojo.byId("groupListGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
                    var groupDataList = new ItemFileWriteStore({data: groupData});
                    var groupLayout = [[
                            {'field': 'id', 'hidden': 'true'},
                            {'name': translator.common.name, 'field': 'name', 'width': '10%','formatter':function(data) {
                                    var link = "<a href='#/admin/groups/view/" + data.id + "'>" + data.name + "</a>";
                                    return link;
                                    
                                }
                            },
                        ]
                    ];
        
        
        getGroupsRestStore.query().then(function(data){
            if(data.length === 0) {
                dojo.byId("groupListGrid").innerHTML = ""
                dojo.byId("noGroupList").style.display = "block"
            } else {
                dojo.byId("noGroupList").style.display = "none"
                dojo.forEach(data, function(group) {
                    groupDataList.newItem({
                        id: group.id,
                        name: group,
                        
                    });
                });
                dojo.byId("groupListGrid").innerHTML = "";
                    var groupDataGrid = new EnhancedGrid({
                        id: 'groupDatas',
                        "class":"span12",
                        store: groupDataList,
                        structure: groupLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    groupDataGrid.placeAt("groupListGrid");
                    groupDataGrid.startup();
            }
        })
        
    },
    listUsers : function(id) {
        if (dijit.byId("groupDatas")) {
            dijit.byId("groupDatas").destroyRecursive();
        }
       var getUsersRestStore = new JsonRest({
            target: core.getContextPath()+"/api/group/user/list"
        });
        
        var groupData = {
                items: []
            };
        
        var groupDataList = new ItemFileWriteStore({data: groupData});
                    var gridLayout = [
            [
                {'name': translator.user.userID,'field': 'id', 'width': '10%', datatype:"string",'hidden':true
                },
                {'name': translator.user.userName, 'field': 'userName', 'width': '10%', datatype:"string",  autoComplete: true, 'formatter': function(data) {
                        var node = "<a href='#/admin/users/view/" + data.id + "'>" + data.name + "</a>";
                        return node;
                    }}, 
                {'name': translator.common.firstName, 'field': 'firstName', 'width': '10%', datatype:"string",  autoComplete: true},
                {'name': translator.common.lastName, 'field': 'lastName', 'width': '10%', datatype:"string",  autoComplete: true},
                {'name': translator.common.email, 'field': 'email','width':'10%',datatype:"string",  autoComplete: true},
                 
//                {'name': translator.common.billing.paid, 'field': 'paid', 'width': '100px', datatype:"string",  autoComplete: true, 'formatter': function(data) {                                                                      
//                        return data;
//                    }
//                },     
//                {'name': translator.common.usersCount, 'fields': ['userCount','id'], 'width': '100px', datatype:"string",  autoComplete: true, 'formatter': function(data) {                                                                      
//                        var node = "<a href='#/admin/account/users/" + data[1] + "'>" + data[0] + "</a>";
//                        return node;
//                    }
//                },     
                {'name': translator.user.grid.instance.layout.status, 'field': 'status', 'width': '100px',  datatype:"string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false
                    }, 'formatter' : function(status) {
                        var currentStatus = "";
                        if(status === true) {
                            currentStatus = "<div class='client-stat-active overflowLabel'>"+translator.common.account.status.ACTIVE+"</div>";
                        } else {
                            currentStatus = "<div class='client-stat-canceled overflowLabel'>"+translator.common.account.status.DISABLED+"</div>";
                        }
                        return currentStatus;
                    }
                }, 
          ]
        ];
        
        getUsersRestStore.query({id:id}).then(function(data){
            console.log(data)
            if(data.length === 0) {
                dojo.byId("accountGrid").innerHTML = ""
                dojo.byId("noClientMessageBox").style.display = "block"
            } else {
                dojo.byId("noClientMessageBox").style.display = "none"
               
                dojo.forEach(data, function(accountList) {
                    groupDataList.newItem({
                        id: accountList.id,
                        userName: accountList,  
                        domainName: accountList.accountLocked,  
                        email: accountList.email,
                        firstName: accountList.firstName,
                        status: accountList.enabled, 
                        lastName:accountList.lastName,
                    });
                });
                dojo.byId("accountGrid").innerHTML = "";
                    var groupDataGrid = new EnhancedGrid({
                        id: 'groupDatas',
                        "class":"span12",
                        store: groupDataList,
                        structure: gridLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    groupDataGrid.placeAt("accountGrid");
                    groupDataGrid.startup();
            }
        })
        
        
    }
}


