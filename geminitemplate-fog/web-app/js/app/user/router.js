'use strict';
require([
    "dojo",
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js", 
    "dijit/dijit",
    "dojo/store/JsonRest",
    "dijit/registry",    
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dijit/form/HorizontalSlider",    
    "dojo/dom-construct",
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/Tufte",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojo/_base/connect",  
    "dijit/Menu", 
    "dijit/MenuItem", 
    "dijit/form/ComboButton", 
    "dijit/form/DropDownButton", 
    "dijit/DropDownMenu", 
    "dijit/Tooltip",
    "dojox/charting/plot2d/Markers",
    "dijit/form/HorizontalRule",
    "dijit/form/HorizontalRuleLabels",
    "dijit/form/HorizontalSlider",    
    "dojo/query",
    "dojo/dom-class",
    "dijit/layout/TabContainer",
    "dojox/charting/widget/Chart2D",
    "dojox/charting/themes/Claro",
    "dijit/form/Button",
    "dijit/layout/ContentPane",
    "dojox/form/PasswordValidator",
    "dojo/on",
    "dojo/query",
    "dojox/validate/regexp",    
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "FogPanel/VolumeListItem",
    "FogPanel/InstanceStatus",
    "FogPanel/StorageAction",
    "dijit/TitlePane", 
    "Zone/ZoneCost",
    "dojox/widget/rotator/Slide",
    "dojox/widget/Rotator",
    "dojox/widget/rotator/Pan",
    "FogPanel/WizardListItem",
    "dijit/form/Form",        
    "dojox/validate/regexp",
    "dijit/form/ValidationTextBox",
    "dijit/form/CheckBox",
    "dijit/form/NumberSpinner",
    "dijit/Dialog",
    "dijit/layout/ContentPane",   
    "List/ListItem"
], function(dojo, i18n, translator, dijit, JsonRest, registry, FilteringSelect, Select,ItemFileWriteStore, DataGrid, EnhancedGrid, HorizontalSlider, domConstruct, Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, 
    Magnify, theme, ColumnsPlot, Highlight, connect, Menu, MenuItem, ComboButton, DropDownButton, DropDownMenu) {
    window.translator = translator;
    window.JsonRest = JsonRest;   
    window.Menu = Menu;
    window.MenuItem = MenuItem;
    window.DropDownButton = DropDownButton;
    window.DropDownMenu = DropDownMenu;
    window.Magnify = Magnify;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Select = Select;
    window.DataGrid = DataGrid;
    window.domConstruct = domConstruct;
    window.domConstruct = domConstruct;
    window.Lines = Lines;
    window.HorizontalSlider = HorizontalSlider;
    window.Chart = Chart;
    window.Pie = Pie;
    window.PlotKitGreen = PlotKitGreen;
    window.Tooltip = Tooltip;
    window.MoveSlice = MoveSlice;
    window.theme = theme;
    window.ColumnsPlot = ColumnsPlot;
    window.Highlight = Highlight;
    window.connect = connect;
    window.currentRuleId = "";  
    window.compCount = 0;
    window.createVMCurrentZone = "";
    window.zoneTempRefId = "";
    window.isTierOptionEnabled = false;
    controller({ 
        name:"router",
        module: "user",
        filePath: "/js/app/user/router.js",
        layout: {
            name: "",
            containerId: "content"
        },
        scaffold: false
    },
    {   
        "list": action(function() {
            if (dijit.byId("listRouterForm")) {
                dijit.byId("listRouterForm").destroyRecursive();
            } 
            if (dijit.byId("createRouterForm")) {
                dijit.byId("createRouterForm").destroyRecursive();
            } 
            if (dijit.byId("deleteRouterAlert")) {
                dijit.byId("deleteRouterAlert").destroyRecursive();
            }  
            if (dijit.byId("routerDeleteLoader")) {
                dijit.byId("routerDeleteLoader").destroyRecursive();
            } 
            if (dijit.byId("routerGatewayLoader")) {
                dijit.byId("routerGatewayLoader").destroyRecursive();
            } 
            if (dijit.byId("setGatewayDialog")) {
                dijit.byId("setGatewayDialog").destroyRecursive();
            } 
            if (dijit.byId("editRouterDialog")) {
                dijit.byId("editRouterDialog").destroyRecursive();
            }
            if (dijit.byId("clearGatewayConfirmAlert")) {
                dijit.byId("clearGatewayConfirmAlert").destroyRecursive();
            }
            core.ui.loadTemplate("routerList", core.ui.getContentId());  
            Routers.populateValues();
            Routers.setExternalGatewayList();
           
        }),
        "view": action(function(id) {
            if(dijit.byId("routerTabContainer")) {
                dijit.byId("routerTabContainer").destroyRecursive();
            } 
            if(dijit.byId("listrouterInterfaceForm")) {
                dijit.byId("listrouterInterfaceForm").destroyRecursive();
            } 
            if(dijit.byId("addRouterInterfaceDialog")) {
                dijit.byId("addRouterInterfaceDialog").destroyRecursive();
            } 
            if(dijit.byId("interfaceCreateLoader")) {
                dijit.byId("interfaceCreateLoader").destroyRecursive();
            }  
            if(dijit.byId("detachInterfaceAlert")) {
                dijit.byId("detachInterfaceAlert").destroyRecursive();
            }  
            if(dijit.byId("interfaceDeleteLoader")) {
                dijit.byId("interfaceDeleteLoader").destroyRecursive();
            }  
            core.ui.loadTemplate("routerView", core.ui.getContentId()); 
            Routers.view(id);
            RouterInfo.init();
        })
        
        
    });
}); 
var Routers = {
    'setExternalGatewayList': function() {
        
        if(dijit.byId("externalNetworkListWidget")){
            dijit.byId("externalNetworkListWidget").destroyRecursive();
        }
      
        var externalRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/isExternal"
        });
        var networkOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var networkList = new ItemFileWriteStore({data: networkOptions});
//        volumeList.newItem({id: "", name: ""});
        externalRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
                networkList.newItem({id: el.referenceId, name: el.name});
            });
            var networkWidget = new FilteringSelect({
                store: networkList,
                id: "externalNetworkListWidget",
                required: true,
                placeHolder: translator.common.selectExternalNetwork,
                onChange: function() {
                }
            });
            networkWidget.placeAt("externalNetworkList");
            networkWidget.startup();
        });
        
    },

    'populateValues': function() {

        dojo.byId("noRouterMessageBox").style.display = "none";
        dojo.byId("userRouterList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.routerLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true',},
                {'name': translator.common.name, 'field':'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) { 
                        
                        return "<a href='#/user/router/view/" + data.referenceId + "' title='" + translator.common.view + "'>" + data.name + "</a>";

                    }
                },
                {'name': translator.common.status.name, 'field': 'status', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.externalNetwork, 'field': 'externalNetwork', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function (data) {
                        
                        var menu = new DropDownMenu({style: "display: none;"});
                        
                        var menuItem1 = new MenuItem({
                            label: translator.common.view,
                            onClick: function() {
                                core.router.go("#/user/router/view/"+ data.referenceId + "");
                            }
                        });
                        menu.addChild(menuItem1);
                        menu.startup();
                        
                        var edit = new MenuItem({
                            label: translator.common.edit,
                            onClick: function() {
                                RouterInfo.showEditDialog(data.referenceId);
                            }
                        });
                        menu.addChild(edit);
                        menu.startup();

                        if(data.externalNetworkId == null) {
                            
                            var menuItem2 = new MenuItem({
                                label: translator.common.setGateway,
                                onClick: function() {
                                    RouterInfo.showGatewayDialog(data.referenceId);
                                }
                            });
                            menu.addChild(menuItem2);
                            menu.startup();
                            
                        } else {
                            
                            var menuItem3 = new MenuItem({
                                label: translator.common.clearGateway,
                                onClick: function() {
                                    RouterInfo.showClearGatewayDialog(data.referenceId);
                                }
                            });
                            menu.addChild(menuItem3);
                            menu.startup();
                            
                        }
     
                        var menuItem4 = new MenuItem({
                            label: translator.common.deleteData,
                            onClick: function() {
                                Routers.getDeleteConfirmationDialog(data.referenceId);
                            }
                        });
                        menu.addChild(menuItem4);
                        menu.startup();

                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });
                        return button;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var networksRestStore = new JsonRest({
            target: core.getContextPath() + "/api/router/"
        });

        networksRestStore.query().then(function (data) {                                                                
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userRouterList").innerHTML = "";
                dojo.byId("noRouterMessageBox").style.display = "block";

            } else {

                dojo.byId("noRouterMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: resultData,
                        status: resultData.status,
                        externalNetwork: resultData.externalNetworkName,
                        action: resultData
                    });
                });
                dojo.byId("userRouterList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userRouterList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    'getDeleteConfirmationDialog': function(id) {
        dojo.byId("currentRouterId").value = id;
        dijit.byId("deleteRouterAlert").show();
    },
    'delete': function() {
      
        var id = dojo.byId("currentRouterId").value;
      
        var routerRestStore = new JsonRest({
            target: core.getContextPath() + "/api/router/"
        });
        
        dijit.byId("deleteRouterAlert").hide();
        dijit.byId("routerDeleteLoader").show()

        routerRestStore.remove(id).then(function (data) {
            dijit.byId("routerDeleteLoader").hide();
 
            if (data === "OK") {
            registry.byId("userToaster").setContent(translator.common.message.routerDeletedSuccess, "message");
            registry.byId("userToaster").show();
            Routers.populateValues()

            } else {
                
                registry.byId("userToaster").setContent(data, "error");
                registry.byId("userToaster").show();
            }

        });
        
    },
    'closeDeleteDialog': function() {
        if(dijit.byId("routerDeleteLoader")) {
            dijit.byId("routerDeleteLoader").hide();
        }
        if(dijit.byId("routerGatewayLoader")) {
            dijit.byId("routerGatewayLoader").hide();
        }
        
    },
    'cancelDelete': function() {
        dijit.byId("deleteRouterAlert").hide();
    },
    'view': function(id) {
 
        var networksRestStore = new JsonRest({
            target: core.getContextPath() + "/api/router/"
        });
        dojo.byId("selectedRouterId").value = id;
        networksRestStore.query({referenceId: id}).then(function (data) {
           dojo.forEach(data, function (resultData) {
              
              dojo.byId("interfaceNetworkId").value = resultData.networkId
              dojo.byId("viewRouterName").innerHTML  = resultData.name
              dojo.byId("routerNameInfo").innerHTML = resultData.name;
              dojo.byId("routerIdInfo").innerHTML = resultData.referenceId;
              dojo.byId("routerStatusInfo").innerHTML = resultData.status;
              dojo.byId("externalNetwork").innerHTML = resultData.externalNetworkName;
           });
        });
    },
}
var RouterInfo = {

    'init': function() {
        
        if(dijit.byId("subnetListWithNetworkWidget")) {
            dijit.byId("subnetListWithNetworkWidget").destroyRecursive();
        }
        
        var subnetsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/subnetListWithDetail"
        });
        var subnetOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var subnetList = new ItemFileWriteStore({data: subnetOptions});
        subnetsRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
                subnetList.newItem({id: el.referenceId, name: el.name});
            });
            var subnetWidget = new FilteringSelect({
                store: subnetList,
                id: "subnetListWithNetworkWidget",
                required: true,
                placeHolder: translator.common.selectSubnet,
                onChange: function() {
                }
            });
            subnetWidget.placeAt("interfaceSubnetList");
            subnetWidget.startup();
        });
        
    },
    'showClearGatewayDialog': function(id) {
        dojo.byId("currentRouterId").value = id;
        dijit.byId("clearGatewayConfirmAlert").show();
    },
    'clearGateway': function() {
        var clearGatewayRest  = new JsonRest({
           target: core.getContextPath() + "/api/router/clearGateway" 
        });
        dijit.byId("clearGatewayConfirmAlert").hide();
        dijit.byId("routerGatewayLoader").show();
        var routerId = dojo.byId("currentRouterId").value;
        clearGatewayRest.add({routerId: routerId}).then(function(data) {
            dijit.byId("routerGatewayLoader").hide();
            dojo.forEach(data, function(resultData) {
                if(resultData.result === "OK"){
                   registry.byId("userToaster").setContent(translator.common.message.routerGateWayClearSuccess, "message");
                   registry.byId("userToaster").show();
                   Routers.populateValues();
                } else {
                   registry.byId("userToaster").setContent(translator.common.message.routerGateWayNotCleared, "error");
                   registry.byId("userToaster").show();
                }
            });
        });
    },
    'cancelClearGatewayConfirmDialog': function() {
        dijit.byId("clearGatewayConfirmAlert").hide(); 
    },
     'showEditDialog': function(id) {
       dijit.byId("editRouterDialog").show();
       dojo.byId("currentRouterId").value= id;
       
        var getRouterRest =  new JsonRest({
            target: core.getContextPath() + "/api/router/"
        });
        getRouterRest.query({referenceId: id}).then(function(data){
           dojo.forEach(data, function(resultData){
              dijit.byId("updatedRouterName").setValue(resultData.name); 
           });
        });
        
    },
    'update': function() {
        
        var routerId = dojo.byId("currentRouterId").value;
        var status = true;
        var pageNode = dojo.byId("routerEditPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            
            var name = dijit.byId("updatedRouterName").value;
            var updateRouterRest =  new JsonRest({
                target: core.getContextPath() + "/api/router/"
            });
            dijit.byId("editRouterDialog").hide();
            dijit.byId("routerGatewayLoader").show();
            updateRouterRest.put({
                id: routerId,
                name: name,
            }).then(function(data){
                dojo.forEach(data, function(resultData) {
                    dijit.byId("routerGatewayLoader").hide(); 
                  if(resultData.result == "OK"){
                     
                     registry.byId("userToaster").setContent(translator.common.message.routerUpdateSuccess, "message");
                     registry.byId("userToaster").show();
                     Routers.populateValues();
                  } else {
                     registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                     registry.byId("userToaster").show();
                  }

                });
            });
        
        }
        
    },
    'cancelEditDialog': function() {
        dijit.byId("editRouterDialog").hide();
    },
    'showGatewayDialog': function(id) {
       dojo.byId("selectedSubnetId").value = id;
       dijit.byId("setGatewayDialog").show(); 
       dijit.byId("routerGatewayForm").reset();
    },
    'setGateway': function() {
        
        var status = true;
        var pageNode = dojo.byId("routerGatewayFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
        
            var setGatewayRest =  new JsonRest({
                target: core.getContextPath() + "/api/router/setGateway"
            });

            dijit.byId("setGatewayDialog").hide(); 
            dijit.byId("routerGatewayLoader").show(); 
            var externalNetwork = dijit.byId("externalNetworkListWidget").value;
            var routerId = dojo.byId("selectedSubnetId").value;
            setGatewayRest.add({
                externalNetwork: externalNetwork,
                routerId: routerId,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                  if(resultData.result == "OK"){
                     dijit.byId("routerGatewayLoader").hide(); 
                     registry.byId("userToaster").setContent(translator.common.message.setRouterGateway, "message");
                     registry.byId("userToaster").show();
                     Routers.populateValues();
                  } else {
                     registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                     registry.byId("userToaster").show();
                  }

                });

            });
        }
    },
    'cancelGateway': function() {
        dijit.byId("setGatewayDialog").hide(); 
    },
    'create' : function() {
        
        var status = true;
        var pageNode = dojo.byId("routerPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {

            var routerRestStore = new JsonRest({
                target: core.getContextPath() + "/api/router/"
            });

            var name = dijit.byId("routerName").value;
            dojo.byId("routerButtonDiv").style.display = "none";
            dojo.byId("routerCreateLoader").style.display = "block";

            routerRestStore.add({
                name: name

            }).then(function (data) {
                dojo.byId("routerCreateLoader").style.display = "none";
                dojo.byId("routerButtonDiv").style.display = "block";
                dojo.forEach(data, function (resultData) {
                   
                    if (resultData.result === "OK") {
                    registry.byId("userToaster").setContent(translator.common.message.routerAddedSuccess, "message");
                    registry.byId("userToaster").show();
                    Routers.populateValues()
                    
                    } else {
                        registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("userToaster").show();
                    }
                    
                });
                
            });
        }
        
    },
 
    'cancelRouter': function() {
        
        dijit.byId("createRouterForm").reset();  
    },
    'populateInterfaceValues': function() {
        var routerId = dojo.byId("selectedRouterId").value;
        
        dojo.byId("noRouterInterfaceMessageBox").style.display = "none";
        dojo.byId("userRouterInterfaceList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.interfaceLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true',},
                {'name': translator.common.name, 'field':'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) { 
                        
                        return "<a href='#/user/network/viewPort/" + data.referenceId + "' title='" + translator.common.view + "'>" + data.name + "</a>";

                    }
                },
                {'name': translator.common.fixedIps, 'field': 'fixedIps', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.status.name, 'field': 'status', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.adminState, 'field': 'adminState', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function (data) {
                        
                        var menu = new DropDownMenu({style: "display: none;"});
                        
                        var menuItem1 = new MenuItem({
                            label: translator.common.view,
                            onClick: function() {
                                core.router.go("#/user/network/viewPort/"+ data.referenceId + "");
                            }
                        });
                        menu.addChild(menuItem1);
                        menu.startup();

//                        var menuItem2 = new MenuItem({
//                            label: translator.common.edit,
//                            onClick: function() {
//                                NetworkInfo.showEditNetworkDialog(data.referenceId);
//                            }
//                        });
//                        menu.addChild(menuItem2);
//                        menu.startup();

                        var menuItem3 = new MenuItem({
                            label: translator.common.deleteData,
                            onClick: function() {
                                RouterInfo.getInterfaceDeleteConfirmationDialog(data.referenceId, data.subnetId, data.deviceId);
                            }
                        });
                        menu.addChild(menuItem3);
                        menu.startup();

                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });
                        return button;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var networksRestStore = new JsonRest({
            target: core.getContextPath() + "/api/router/interfaceList/"
        });

        networksRestStore.query({routerId: routerId}).then(function (data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userRouterInterfaceList").innerHTML = "";
                dojo.byId("noRouterInterfaceMessageBox").style.display = "block";

            } else {

                dojo.byId("noRouterInterfaceMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: resultData,
                        fixedIps: resultData.fixedIps,
                        type: resultData.type,
                        adminState: resultData.adminState,
                        status: resultData.status.name,
                        action: resultData
                    });
                });
                dojo.byId("userRouterInterfaceList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userRouterInterfaceList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
        
        
    },
    'interfaceCreateFormDialog': function() {
        
        dijit.byId("addRouterInterfaceDialog").show();
        dijit.byId("addRouterInterfaceForm").reset();
    },
    'addInterface': function() {
        
        var status = true;
        var pageNode = dojo.byId("addRouterInterfaceFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {

            var routerRestStore = new JsonRest({
                target: core.getContextPath() + "/api/router/addInterface"
            });

            var subnetId = dijit.byId("subnetListWithNetworkWidget").value;
            var routerId = dojo.byId("selectedRouterId").value;
            var networkId = dojo.byId("interfaceNetworkId").value;
            var ipaddress = dijit.byId("InterfaceIpAddress").value;
            dijit.byId("addRouterInterfaceDialog").hide();
            dijit.byId("interfaceCreateLoader").show()

            routerRestStore.add({
                subnetId: subnetId,
                networkId: networkId,
                routerId: routerId,
                ipaddress: ipaddress

            }).then(function (data) {
                dijit.byId("interfaceCreateLoader").hide();
                dojo.forEach(data, function (resultData) {
                   
                    if (resultData.result === "OK") {
                    registry.byId("userToaster").setContent(translator.common.message.interfaceAddedSuccess, "message");
                    registry.byId("userToaster").show();
                    RouterInfo.populateInterfaceValues()
                    
                    } else {
                        registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("userToaster").show();
                    }
                    
                });
                
            });
        }
    },
    'getInterfaceDeleteConfirmationDialog': function(portId, subnetId, routerId) {
        
        dojo.byId("interfacePortId").value = portId;
        dojo.byId("interfaceSubnetId").value = subnetId;
        dojo.byId("interfaceRouterId").value = routerId;
        dijit.byId("detachInterfaceAlert").show();
    },
    'deleteInterface': function() {
          
        var portId = dojo.byId("interfacePortId").value;
        var subnetId = dojo.byId("interfaceSubnetId").value;
        var routerId = dojo.byId("interfaceRouterId").value;
          
        var routerRestStore = new JsonRest({
            target: core.getContextPath() + "/api/router/deleteInterface"
        });
        
        dijit.byId("detachInterfaceAlert").hide();
        dijit.byId("interfaceDeleteLoader").show()

        routerRestStore.add({
            portId: portId,
            subnetId: subnetId,
            routerId: routerId

        }).then(function (data) {
            dijit.byId("interfaceDeleteLoader").hide();
            dojo.forEach(data, function (resultData) {

                if (resultData.result === "OK") {
                registry.byId("userToaster").setContent(translator.common.message.interfaceDeletedSuccess, "message");
                registry.byId("userToaster").show();
                RouterInfo.populateInterfaceValues()

                } else {
                    registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                    registry.byId("userToaster").show();
                }

            });

        });
    },
    'closeAddInterfaceDialog': function() {
        dijit.byId("addRouterInterfaceDialog").hide();
    },
    'closeDeateInterfaceDialog': function() {
        dijit.byId("detachInterfaceAlert").hide();
    },
    'closeDeleteInterfaceDialog': function() {
        dijit.byId("interfaceDeleteLoader").hide();
    }

}



