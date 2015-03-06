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
        name:"floatingIp",
        module: "user",
        filePath: "/js/app/user/floatingIp.js",
        layout: {
            name: "",
            containerId: "content"
        },
        scaffold: false
    },
    {   
        "list": action(function() {
            if (dijit.byId("listFloatingIpForm")) {
                dijit.byId("listFloatingIpForm").destroyRecursive();
            } 
            if (dijit.byId("createFloatingIpDialog")) {
                dijit.byId("createFloatingIpDialog").destroyRecursive();
            } 
            if (dijit.byId("releaseFloatingIpAlert")) {
                dijit.byId("releaseFloatingIpAlert").destroyRecursive();
            } 
            if (dijit.byId("releaseFloatingIpLoader")) {
                dijit.byId("releaseFloatingIpLoader").destroyRecursive();
            } 
            if (dijit.byId("disassociateFloatingIpAlert")) {
                dijit.byId("disassociateFloatingIpAlert").destroyRecursive();
            } 
            if (dijit.byId("associateDialog")) {
                dijit.byId("associateDialog").destroyRecursive();
            } 
           
            core.ui.loadTemplate("floatingIpList", core.ui.getContentId());  
            FloatingIp.init();
            FloatingIp.populateValues();
           
        }), 
        "view": action(function(id) {
            if(dijit.byId("floatingTabContainer")) {
                dijit.byId("floatingTabContainer").destroyRecursive();
            } 
            
            core.ui.loadTemplate("floatingIpView", core.ui.getContentId()); 
            FloatingIp.view(id);
        })
        
    });
}); 
var FloatingIp = {
    'init': function() {
        
        if(dijit.byId("externalNetworksWidget")){
            dijit.byId("externalNetworksWidget").destroyRecursive();
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
            
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("externalNetworksIPList").innerHTML = "<span class= 'require'>" + translator.common.message.noExternalNetworks + "</span>";
                dojo.byId("externalNetworksListIPHelp").style.display = "none";
            } else {
                dojo.byId("externalNetworksListIPHelp").style.display = "block";
                dojo.forEach(data, function(el) {
                    networkList.newItem({id: el.referenceId, name: el.name});
                });
                console.log("external network found")
                var networkWidget = new FilteringSelect({
                    store: networkList,
                    id: "externalNetworksWidget",
                    required: true,
                    placeHolder: translator.common.selectExternalNetwork,
                    onChange: function() {
                    }
                });
                networkWidget.placeAt("externalNetworksIPList");
                networkWidget.startup();
            }
        });
        
    },

    'populateValues': function() {

        dojo.byId("noFloatingIpMessageBox").style.display = "none";
        dojo.byId("userFloatingIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.floatingIpLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) { 
                        
                        return "<a href='#/user/floatingIp/view/" + data.referenceId + "' title='" + translator.common.view + "'>" + "(" + data.referenceId +  ")" + "</a>";

                    }
                },
                {'name': translator.common.ipAddress, 'field': 'ipAddress', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.mappedFixedIpAddress, 'field':'mappedFixedIpAddress', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) { 
                        if(data.instanceName != null) {
                            return "<a href='#/user/server/viewServer/" + data.instanceId + "' title='" + translator.common.view + "'>" + data.instanceName + ": "+ data.fixedIpAddress + "</a>";
                        } else {
                            return "-"
                        }
                        

                    }
                },
//                {'name': translator.common.status.name, 'field': 'status', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.floatingIpPool, 'field': 'floatingIpPool', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function (data) {
                        
                        var menu = new DropDownMenu({style: "display: none;"});

                        var menuItem1 = new MenuItem({
                            label: translator.common.view,
                            onClick: function() {
                                core.router.go("#/user/floatingIp/view/"+ data.referenceId + "");
                            }
                        });
                        menu.addChild(menuItem1);
                        menu.startup();
                        
                        if(data.fixedIpAddress == null) {
                            
                            var menuItem2 = new MenuItem({
                                label: translator.common.associate,
                                onClick: function() {
                                    FloatingIp.associateDialog(data.referenceId);
                                }
                            });
                            menu.addChild(menuItem2);
                            menu.startup();
                            
                        } else {
                            
                            var menuItem3 = new MenuItem({
                            label: translator.common.disAssociate,
                            onClick: function() {
                                FloatingIp.disassociateDialog(data.referenceId);
                            }
                            });
                            menu.addChild(menuItem3);
                            menu.startup();
                            
                        }
                        
                        var menuItem4 = new MenuItem({
                            label: translator.common.releaseFloatingIp,
                            onClick: function() {
                                FloatingIp.getReleaseConfirmationDialog(data.referenceId);
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

        var floatingIpRestStore = new JsonRest({
            target: core.getContextPath() + "/api/floatingIp/"
        });

        floatingIpRestStore.query().then(function(data) {

            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userFloatingIpList").innerHTML = "";
                dojo.byId("noFloatingIpMessageBox").style.display = "block";

            } else {

                dojo.byId("noFloatingIpMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData,
                        ipAddress: resultData.floatingIpAddress,
                        mappedFixedIpAddress: resultData,
//                        status: resultData.status,
                        floatingIpPool: resultData.networkName,
                        action: resultData
                    });
                });
                dojo.byId("userFloatingIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userFloatingIpList");
                dataGrid.startup();
                
                FloatingIp.manageIp();
            }
        });
    },
    'manageIp': function() {
        
//        if(dijit.byId("floatingIpAddressWidget")){
//            dijit.byId("floatingIpAddressWidget").destroyRecursive();
//        }
        if(dijit.byId("floatingIpPortWidget")){
            dijit.byId("floatingIpPortWidget").destroyRecursive();
        }
      
//        var floatingIpRestStore = new JsonRest({
//            target: core.getContextPath() + "/api/floatingIp"
//        });
//        var floatingIpOptions = {
//            identifier: 'id',
//            label: 'name',
//            items: []
//        };
//
//        var floatingIpList = new ItemFileWriteStore({data: floatingIpOptions});
////        volumeList.newItem({id: "", name: ""});
//        floatingIpRestStore.query().then(function(data) {
//
//            dojo.forEach(data, function(el) {
//                floatingIpList.newItem({id: el.referenceId, name: el.floatingIpAddress});
//            });
//            var floatingIpWidget = new FilteringSelect({
//                store: floatingIpList,
//                id: "floatingIpAddressWidget",
//                required: true,
//                placeHolder: translator.common.selectIpAddress,
//                onChange: function() {
//                }
//            });
//            floatingIpWidget.placeAt("floatIpAddressList");
//            floatingIpWidget.startup();
//        });
        
        var floatingPortRestStore = new JsonRest({
            target: core.getContextPath() + "/api/floatingIp/portList"
        });
        var floatingPortOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var floatingPortList = new ItemFileWriteStore({data: floatingPortOptions});
//        volumeList.newItem({id: "", name: ""});
        floatingPortRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {               
                floatingPortList.newItem({id: el.referenceId, name: el.mappedIpAddress});
            });
            var floatingPortWidget = new FilteringSelect({
                id: "floatingIpPortWidget",
                store: floatingPortList,                
                required: true,
                placeHolder: translator.common.selectPort,
                onChange: function() {
                }
            });
            floatingPortWidget.placeAt("floatIPPortsList");
            floatingPortWidget.startup();            
        });
        
    },
    'associateDialog': function(id) {
        dojo.byId("selectedFloatingIpId").value = id;
        dijit.byId("associateDialog").show();
        dijit.byId("associateFloatingIpForm").reset();
    },
    'cancelAssociateDialog': function() {
        dijit.byId("associateDialog").hide();
    },
    'disassociateDialog': function(id) {
        dojo.byId("selectedFloatingIpId").value = id;
        dijit.byId("disassociateFloatingIpAlert").show();
    },
    'cancelDisassociate': function() {
        dijit.byId("disassociateFloatingIpAlert").hide();
    },
    'getReleaseConfirmationDialog': function(id) {
        dojo.byId("selectedFloatingIpId").value = id;
        dijit.byId("releaseFloatingIpAlert").show();
    },
    'associate': function() {
        var status = true;
        var pageNode = dojo.byId("associateFloatingIpListPage");
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
        
            var floatingAssociateRest =  new JsonRest({
                target: core.getContextPath() + "/api/floatingIp/associate/"
            });

            dijit.byId("associateDialog").hide(); 
            dijit.byId("releaseFloatingIpLoader").show(); 
//            var ipAddress = dijit.byId("floatingIpAddressWidget").value;
            var floatingIpId = dojo.byId("selectedFloatingIpId").value;
            var portId = dijit.byId("floatingIpPortWidget").value;
            floatingAssociateRest.put({
                id: floatingIpId,
                portId: portId,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                  dijit.byId("releaseFloatingIpLoader").hide();
                  if(resultData.result == "OK"){
                     registry.byId("userToaster").setContent(translator.common.message.floatingAssociate, "message");
                     registry.byId("userToaster").show();
                     FloatingIp.populateValues();
                  } else if(resultData.result == "FAILED"){
                     registry.byId("userToaster").setContent(translator.common.message.floatingIpNotAssociated, "error");
                     registry.byId("userToaster").show();

                  } else {
                     registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                     registry.byId("userToaster").show();
                  }

                });

            });
        }  
    },
    'disassociate': function() {
       
        var id = dojo.byId("selectedFloatingIpId").value;
        
        var floatingDisAssociateRest =  new JsonRest({
            target: core.getContextPath() + "/api/floatingIp/disAssociate/"
        });

        dijit.byId("disassociateFloatingIpAlert").hide(); 
        dijit.byId("releaseFloatingIpLoader").show(); 
        floatingDisAssociateRest.put({
            id: id,
        }).then(function(data) {
            dijit.byId("releaseFloatingIpLoader").hide(); 
            dojo.forEach(data, function(resultData) {
              if(resultData.result == "OK"){
                 
                 registry.byId("userToaster").setContent(translator.common.message.floatingdisAssociate, "message");
                 registry.byId("userToaster").show();
                 FloatingIp.populateValues();
              } else if(resultData.result == "DEVICE_ENABLED") {
                  
                 registry.byId("userToaster").setContent(resultData.message, "error");
                 registry.byId("userToaster").show();
              } else {
                 registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                 registry.byId("userToaster").show();
              }

            });

        });
         
    },
    'delete': function() {
      
        var id = dojo.byId("selectedFloatingIpId").value;
      
       var floatingIpRestStore = new JsonRest({
            target: core.getContextPath() + "/api/floatingIp/delete"
        });

        dijit.byId("releaseFloatingIpAlert").hide();
        dijit.byId("releaseFloatingIpLoader").show()

        floatingIpRestStore.add({
            referenceId : id
        }).then(function (data) {
            dijit.byId("releaseFloatingIpLoader").hide();
           dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK"){
                    registry.byId("userToaster").setContent(translator.common.message.floatingIpReleaseSuccess, "message");
                    registry.byId("userToaster").show();
                    FloatingIp.populateValues()

                } else if(resultData.result == "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();

                } else if(resultData.result == "DEVICE_ENABLED") {
                  
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.floatingIpNotReleased, "error");
                    registry.byId("userToaster").show();
                }
               
               
           });
            

        });
        
    },
    'closeDeleteDialog': function() {
        if(dijit.byId("releaseFloatingIpLoader")) {
            dijit.byId("releaseFloatingIpLoader").hide();
        }
        
    },
    'cancelDelete': function() {
        dijit.byId("releaseFloatingIpAlert").hide();
    },
    'view': function(id) {
 
        var networksRestStore = new JsonRest({
            target: core.getContextPath() + "/api/floatingIp/"
        });
        dojo.byId("selectedFloatingIpId").value = id;
        networksRestStore.query({referenceId: id}).then(function (data) {
           dojo.forEach(data, function (resultData) {
              
              dojo.byId("floatingIpId").innerHTML = resultData.referenceId
              dojo.byId("floatingIpAddress").innerHTML  = resultData.floatingIpAddress
              dojo.byId("floatingInstance").innerHTML = resultData.instanceName != null ? resultData.instanceName : "-";
              dojo.byId("floatingNetworkId").innerHTML = resultData.network.id;
              dojo.byId("floatingIpPool").innerHTML = resultData.network.name;
              dojo.byId("fixedPortId").innerHTML = resultData.portId == null ? "-" : resultData.portId;
              dojo.byId("fixedIpAddress").innerHTML = resultData.fixedIpAddress == null ? "-" : resultData.fixedIpAddress;
           });
        });
    },
}
var FloatingIpInfo = {
     enableMonthlyCost : function () {
         var formElements = dojo.query("#listFloatingIPBillingTypeDiv input:checked");
         var checkedRadioId = dijit.byId(formElements[0].id);        
         var billingType = dijit.byId(checkedRadioId).value;
        
        var misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });
        misclRestStore.query({id: 6}).then(function(data) {
            if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
                dojo.byId("listFloatingIPCostInfo").style.display = "none";                
            } else {                                                   
                dojo.forEach(data, function(ipCost) {
                    var monthCost = ipCost.cost * 720;
                    if(billingType === "monthly") {
                        dojo.byId("listFloatingIPCost").innerHTML = ipCost.currency + "  "+ localeCurrency.format(monthCost, {places: 2, locale: dojo.locale}) + "   /  " + translator.common.floatingIPMonth; 
                    } else {
                        dojo.byId("listFloatingIPCost").innerHTML = ipCost.currency + "  "+ localeCurrency.format(ipCost.cost, {places: 5, locale: dojo.locale}) + "   /  " + translator.common.floatingIPHr;    
                    }                                      
                });
                dojo.byId("listFloatingIPCostInfo").style.display = "block";
            }
        });        
     },
    'createFloatingIpDialog': function() {        
        var misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });
        
        var billingTypeConfigRestStore = new JsonRest({
                target: core.getContextPath() + "/api/config/billingType"
        });
        billingTypeConfigRestStore.query().then(function (data) {
            if (data[0].monthlyBillingEnabled === "true") {
                dojo.query("#listFloatingIPBillingTypeDiv").removeClass("hide_text", true);
            } else {
                dojo.query("#listFloatingIPBillingTypeDiv").toggleClass("hide_text", true);
            }
        });
        
        misclRestStore.query({id: 6}).then(function(data) {
            if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
                dojo.byId("listFloatingIPCostInfo").style.display = "none";                
            } else {                                                   
                dojo.forEach(data, function(ipCost) {                    
                    dojo.byId("listFloatingIPCost").innerHTML = ipCost.currency + "  "+ localeCurrency.format(ipCost.cost, {places: 5, locale: dojo.locale}) + "   /  " + translator.common.floatingIPHr;                   
                });
                dojo.byId("listFloatingIPCostInfo").style.display = "block";
            }
        });                
        
        dijit.byId("createFloatingIpDialog").show();
        dijit.byId("createFloatingIpForm").reset();
    },
    'create': function() {
        
        var status = true;
        var pageNode = dojo.byId("floatingIpListPage");
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
        
            var floatingAssociateRest =  new JsonRest({
                target: core.getContextPath() + "/api/floatingIp"
            });
             var formElements = dojo.query("#listFloatingIPBillingTypeDiv input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var billingType = dijit.byId(checkedRadioId).value;
            dijit.byId("createFloatingIpDialog").hide(); 
            dijit.byId("releaseFloatingIpLoader").show(); 
            var networkId = dijit.byId("externalNetworksWidget").value;
            floatingAssociateRest.add({
                networkId: networkId,
                billingType : billingType
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                  if(resultData.result == "OK"){
                     dijit.byId("releaseFloatingIpLoader").hide(); 
                     registry.byId("userToaster").setContent(translator.common.message.floatingIpAllocated, "message");
                     registry.byId("userToaster").show();
                     FloatingIp.populateValues();
                  } else {
                     registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                     registry.byId("userToaster").show();
                  }

                });

            });
        }                 
    },
    'cancelFloatingIp': function() {
        dijit.byId("createFloatingIpDialog").hide();
    }
}




