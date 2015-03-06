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
    "dijit/layout/TabContainer",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/DateTextBox",
    "dijit/form/MultiSelect",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
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
    "dijit/Dialog"
],
        function (dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
                ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {
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
            window.DataGrid = DataGrid;
            window.EnhancedGrid = EnhancedGrid;
            window.Source = Source;
            window.MultiSelect = MultiSelect;
            window.dom = dom;
            window.win = win;
            window.computationCount = 0;

            controller({
                name: "network",
                module: "user",
                filePath: "/js/app/user/network.js",
                layout: {
                    name: "network",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "list": action(function () {
                    
                    if (dijit.byId("listNetworkForm")) {
                        dijit.byId("listNetworkForm").destroyRecursive();
                    }
                    if(dijit.byId("networkEditFormDialog")) {
                        dijit.byId("networkEditFormDialog").destroyRecursive();
                    }
                    if(dijit.byId("deleteNetworkDialog")) {
                        dijit.byId("deleteNetworkDialog").destroyRecursive();
                    }
                    if(dijit.byId("networkLoader")) {
                        dijit.byId("networkLoader").destroyRecursive();
                    }

                    core.ui.loadTemplate("userNetworkListPage", core.ui.getContentId());
                    Networks.init();
                    Networks.populateValues();
                }),
                "add": action(function () {

                    if (dijit.byId("adminNetworkAddForm")) {
                        dijit.byId("adminNetworkAddForm").destroyRecursive();
                    }
                    if (dijit.byId("pullNetworkLoader")) {
                        dijit.byId("pullNetworkLoader").destroyRecursive();
                    }

                    core.ui.loadTemplate("addNetwork", core.ui.getContentId());
                    NetworkInfo.init();

                }),
                "view": action (function(id) {
                    if (dijit.byId("adminNetworkAddForm")) {
                        dijit.byId("adminNetworkAddForm").destroyRecursive();
                    } 
                    if (dijit.byId("networkTabContainer")) {
                        dijit.byId("networkTabContainer").destroyRecursive();
                    }  
                    if (dijit.byId("networkContentPane")) {
                        dijit.byId("networkContentPane").destroyRecursive();
                    }  
                    if (dijit.byId("subnetContentPane")) {
                        dijit.byId("subnetContentPane").destroyRecursive();
                    } 
                    if (dijit.byId("pullNetworkLoader")) {
                        dijit.byId("pullNetworkLoader").destroyRecursive();
                    } 
                    if (dijit.byId("listSubnetForm")) {
                        dijit.byId("listSubnetForm").destroyRecursive();
                    } 
                    if (dijit.byId("deleteSubnetDialog")) {
                        dijit.byId("deleteSubnetDialog").destroyRecursive();
                    } 
                    if (dijit.byId("listPortForm")) {
                        dijit.byId("listPortForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("viewNetwork", core.ui.getContentId());
                    NetworkInfo.view(id);
                }),
                "createSubnet": action(function(id) {
                    
                    if (dijit.byId("subnetAddForm")) {
                        dijit.byId("subnetAddForm").destroyRecursive();
                    } 
                    if (dijit.byId("pullSubnetLoader")) {
                        dijit.byId("pullSubnetLoader").destroyRecursive();
                    } 
                    core.ui.loadTemplate("createSubnet", core.ui.getContentId());
                    SubnetInfo.createFormLoad(id);
                }),
                "editSubnet": action(function(id) {
                    
                    if (dijit.byId("subnetAddForm")) {
                        dijit.byId("subnetAddForm").destroyRecursive();
                    } 
                    if (dijit.byId("pullSubnetLoader")) {
                        dijit.byId("pullSubnetLoader").destroyRecursive();
                    } 
                    core.ui.loadTemplate("createSubnet", core.ui.getContentId());
                    SubnetInfo.editFormLoad(id);
                }),
                "viewSubnet": action(function(subnetId) {
                    core.ui.loadTemplate("viewSubnet", core.ui.getContentId());
                    SubnetInfo.View(subnetId);
                }),
                "subnetList": action(function (id) {
                    
                    if (dijit.byId("adminNetworkAddForm")) {
                        dijit.byId("adminNetworkAddForm").destroyRecursive();
                    } 
                    if (dijit.byId("networkTabContainer")) {
                        dijit.byId("networkTabContainer").destroyRecursive();
                    }  
                    if (dijit.byId("networkContentPane")) {
                        dijit.byId("networkContentPane").destroyRecursive();
                    }  
                    if (dijit.byId("subnetContentPane")) {
                        dijit.byId("subnetContentPane").destroyRecursive();
                    } 
                    if (dijit.byId("pullNetworkLoader")) {
                        dijit.byId("pullNetworkLoader").destroyRecursive();
                    } 
                    if (dijit.byId("listSubnetForm")) {
                        dijit.byId("listSubnetForm").destroyRecursive();
                    }
                    if (dijit.byId("listPortForm")) {
                        dijit.byId("listPortForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("viewNetwork", core.ui.getContentId());
                    NetworkInfo.view(id);
                    
                    setTimeout(function () {
                        var mainTab = dijit.byId("networkTabContainer"); //Tr
                        var subTab = dijit.byId("subnetContentPane"); //tab Id which you want to show
                        mainTab.selectChild(subTab);   
                    },100); 
                }),
                "viewPort": action(function(PortId) {
                    core.ui.loadTemplate("viewPort", core.ui.getContentId());
                    PortInfo.view(PortId);
                }),
                "portList": action(function (id) {
                    
                    if (dijit.byId("adminNetworkAddForm")) {
                        dijit.byId("adminNetworkAddForm").destroyRecursive();
                    } 
                    if (dijit.byId("networkTabContainer")) {
                        dijit.byId("networkTabContainer").destroyRecursive();
                    }  
                    if (dijit.byId("networkContentPane")) {
                        dijit.byId("networkContentPane").destroyRecursive();
                    }  
                    if (dijit.byId("subnetContentPane")) {
                        dijit.byId("subnetContentPane").destroyRecursive();
                    } 
                    if (dijit.byId("pullNetworkLoader")) {
                        dijit.byId("pullNetworkLoader").destroyRecursive();
                    } 
                    if (dijit.byId("listSubnetForm")) {
                        dijit.byId("listSubnetForm").destroyRecursive();
                    }
                    if (dijit.byId("listPortForm")) {
                        dijit.byId("listPortForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("viewNetwork", core.ui.getContentId());
                    NetworkInfo.view(id);
                    
                    setTimeout(function () {
                        var mainTab = dijit.byId("networkTabContainer"); //Tr
                        var subTab = dijit.byId("portContentPane"); //tab Id which you want to show
                        mainTab.selectChild(subTab);   
                    },100); 
                }),
            });
        });

var Networks = {
    init: function () {

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function (data) {
            dojo.forEach(data, function (cur) {
                dojo.byId("networkCurrencyValue").innerHTML = cur.currency;
            });
        });
    },
    populateValues: function () {
        
        dojo.byId("noNetworkMessageBox").style.display = "none";
        dojo.byId("userNetworkList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.networkLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true',},
                {'name': translator.common.name, 'fields':['id','name'], 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) { 
                        
                        return "<a href='#/user/network/view/" + data[0] + "' title='" + translator.common.view + "'>" + data[1] + "</a>";

                    }
                },
                {'name': translator.common.subnetsAssociated, 'field': 'subnetsAssociated', 'width': '210px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.shared, 'field': 'shared', 'width': '120px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.external, 'field': 'external', 'width': '120px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.status.name, 'field': 'status', 'width': '120px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.adminState, 'field': 'adminState', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function (data) {
                        
                        if(data.action == true) {
                            
                            var menu = new DropDownMenu({style: "display: none;"});
                        
                            var menuItem1 = new MenuItem({
                                label: translator.common.view,
                                onClick: function() {
                                    core.router.go("#/user/network/view/"+ data.referenceId + "");
                                }
                            });
                            menu.addChild(menuItem1);
                            menu.startup();

                            var menuItem2 = new MenuItem({
                                label: translator.common.edit,
                                onClick: function() {
                                    NetworkInfo.showEditNetworkDialog(data.referenceId);
                                }
                            });
                            menu.addChild(menuItem2);
                            menu.startup();

                            var menuItem3 = new MenuItem({
                                label: translator.common.deleteData,
                                onClick: function() {
                                    NetworkInfo.getDeleteConfirmationDialog(data.referenceId);
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
                        } else {
                            return "";
                        }
                        
                        
                    }, 'width': '190%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var networksRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });

        networksRestStore.query().then(function (data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userNetworkList").innerHTML = "";
                dojo.byId("noNetworkMessageBox").style.display = "block";

            } else {

                dojo.byId("noNetworkMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: resultData.name,
                        subnetsAssociated: resultData.subnetsAssociated,
                        shared: resultData.isShared === true ? translator.common.yes : translator.common.no,
                        external: resultData.isExternal === true ? translator.common.yes : translator.common.no,
                        status: resultData.status,
                        adminState: resultData.isAdminState === true ? translator.common.up : translator.common.down,
                        action: resultData
                    });
                });
                dojo.byId("userNetworkList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userNetworkList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    cancel: function () {
        core.router.go("#/user/network/list");
    },
};

var NetworkInfo = {
    cancel : function () {
        dijit.byId("adminNetworkAddForm").reset();
    },
    enableMonthlyCost: function() {
        var formElements = dojo.query("#networkBillingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        if(billingType === "monthly") {  
            dojo.query("#networkMonthCostDiv").removeClass("hide_text", true);                                                          
        } else {
            dojo.query("#networkMonthCostDiv").toggleClass("hide_text", true);                  
        }                
    },
    init: function () {

//        dojo.byId("networkPagehead").innerHTML = translator.common.head.createNetwork;
        
        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function (data) {
            dojo.forEach(data, function (cur) {
                if(dojo.byId("addNetworkCurrencyValue")) {
                    dojo.byId("addNetworkCurrencyValue").innerHTML = cur.currency;
                }
                dojo.byId("createVolumeCurrecy").innerHTML = cur.currency;                
            });
        });
        var miscRestStore = new JsonRest({
                target: core.getContextPath() + "/api/miscellaneousOffer"
        });
        miscRestStore.query().then(function (miscData) {
            currency.query().then(function (currencyData) {
                dojo.forEach(miscData, function (misc) {
                    if(misc.id === 3) {
                        dojo.byId("networkCost").innerHTML = currencyData[0].currency + "  "+ misc.cost + "   /   " + translator.common.perHour;
                        dojo.byId("networkMonthCost").innerHTML = currencyData[0].currency + "  "+ (misc.cost * 720).toFixed(2) + "   /   " + translator.common.perHMonth;                                        
                    } else if(misc.id === 4) {
                        dojo.byId("bandwidthRead").innerHTML = currencyData[0].currency + "  "+ misc.cost + "   /   " + translator.common.gb;
                    } else if(misc.id === 5) {
                        dojo.byId("bandwidthWrite").innerHTML = currencyData[0].currency + "  "+ misc.cost + "   /   " + translator.common.gb;
                    }
                });
            });
        });           
        
        var billingTypeConfigRestStore = new JsonRest({
                target: core.getContextPath() + "/api/config/billingType"
        });
        billingTypeConfigRestStore.query().then(function (data) {
            if (data[0].monthlyBillingEnabled === "true") {
                dojo.query("#networkBillingTypeDiv").removeClass("hide_text", true);
            } else {
                dojo.query("#networkBillingTypeDiv").toggleClass("hide_text", true);
            }
        });
    },
    'add': function () {
        dojo.byId("networkAddButtonDiv").style.display = "block";
        var status = true;
        var pageNode = dojo.byId("networkContent");
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

            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/"
            });

            var name = dijit.byId("networkName").value;
            var subnetName = dijit.byId("subnetName").value;
            var networkAddress = dijit.byId("networkAddress").value;
            var ipVersion = dijit.byId("ipVersions").value;
            var gatewayIp = dijit.byId("gatewayIp").value;
            var isAdminState = true;
            
            if(dijit.byId("networkAdminState").checked == false) {
                isAdminState = false;
            } 
            
            if (networkAddress.indexOf("/") == -1) {
                dijit.byId("networkAddress").focus();
                registry.byId("userToaster").setContent(translator.common.message.invalidCidr , "error");
                registry.byId("userToaster").show();
            } else {
                dijit.byId("pullNetworkLoader").show();
            
                var formElements = dojo.query("#networkBillingTypeDiv input:checked");
                var checkedRadioId = dijit.byId(formElements[0].id);        
                var billingType = dijit.byId(checkedRadioId).value;

                networkRestStore.add({
                    name: name,
                    subnetName: subnetName,
                    isAdminState:isAdminState,
                    networkAddress: networkAddress,
                    ipVersion: ipVersion,
                    gatewayIp: gatewayIp,
                    billingType : billingType

                }).then(function (data) {

                    if (data[0].result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.addedSuccess, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("pullNetworkLoader").hide();
                        core.router.go("#/user/network/list");
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cannotBeAdded, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("pullNetworkLoader").hide();
                    }
                });
            }
            
        }
    },
    'showEditNetworkDialog' : function(id) {
        dijit.byId("networkEditFormDialog").show();
        
        var viewNetworkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/"
        });
        
        viewNetworkRestStore.query({referenceId : id}).then(function (data) {
            dojo.forEach(data, function (resultData) {
                
                dijit.byId("editNetworkName").setValue(resultData.name);
                dijit.byId("editNetworkId").setValue(resultData.referenceId);
                if(resultData.isAdminState == true) {
                    dijit.byId("editNetworkAdminState").checked == true;
                } else {
                    dijit.byId("editNetworkAdminState").checked == false;
                }
            });
        });
    },
    'closeEditNetworkDialog' : function() {
        dijit.byId("networkEditFormDialog").hide();
    },
    'update' : function() {

        var pageNode = dojo.byId("editNetworkFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            var updateNetworkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/"
            });
            dijit.byId("networkEditFormDialog").hide();
            dijit.byId("networkLoader").show();
            
            var name = dijit.byId("editNetworkName").value;
            var id = dijit.byId("editNetworkId").value;
            var isAdminState = true;
            if(dijit.byId("editNetworkAdminState").checked == false) {
                isAdminState = false;
            }
            
            updateNetworkRestStore.put({
                name : name,
                id : id,
                isAdminState: isAdminState
            }).then(function (data) {
                dojo.forEach(data, function (resultData) {
                dijit.byId("networkLoader").hide();
                if (resultData.result === "OK") {
                    
                    registry.byId("userToaster").setContent(name + " N/W " + translator.common.message.updatedSuccess, "message");
                    registry.byId("userToaster").show();
                    
                } else {
                    registry.byId("userToaster").setContent(data.message, "error");
                    registry.byId("userToaster").show();
                }
                });
                
            });
        }
    },
    'getDeleteConfirmationDialog' : function(id) {
        dijit.byId("deleteNetworkDialog").show();
        dojo.byId("currentNetworkId").value = id;
    },
    'delete' : function() {
        dijit.byId("deleteNetworkDialog").hide();
        var id = dojo.byId("currentNetworkId").value;
        
        var deleteNetworkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/"
        });
        dijit.byId("networkLoader").show();
        deleteNetworkRestStore.remove(id).then(function (data) {
            
            if (data === "OK") {
                registry.byId("userToaster").setContent(translator.common.message.networkDeleted, "message");
                registry.byId("userToaster").show();
                dijit.byId("networkLoader").hide();
                Networks.populateValues();
            } else {
                registry.byId("userToaster").setContent(data, "error");
                registry.byId("userToaster").show();
                dijit.byId("networkLoader").hide();
                Networks.populateValues();
            }
        });
               
    },
    'closeDeleteNetworkDialog' : function() {
        dijit.byId("deleteNetworkDialog").hide();
    },
    'view' : function(id) {
        var viewNetworkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/"
        });
        
        dojo.byId("selectedNetworkId").value = id;
        
        viewNetworkRestStore.query({referenceId : id}).then(function (data) {
            dojo.forEach(data, function (resultData) {
                
                dojo.byId("viewNetworkName").innerHTML = resultData.name;
                dojo.byId("selectedNetworkName").value = resultData.name;
                dojo.byId("networkAction").value = resultData.action;
                
                dojo.byId("networkNameInfo").innerHTML = resultData.name;
                dojo.byId("networkIdInfo").innerHTML = resultData.referenceId;
//                dojo.byId("networkProjectIdInfo").innerHTML = resultData.projectId;
                dojo.byId("networkSubnetInfo").innerHTML = resultData.subnetsAssociated;
                dojo.byId("networkStatusInfo").innerHTML = resultData.status;
                dojo.byId("networkAdminStateInfo").innerHTML = resultData.isAdminState === true ? translator.common.up : translator.common.down;
                dojo.byId("networkSharedInfo").innerHTML = resultData.isShared === true ? translator.common.yes : translator.common.no;
                
                var mainTab  = dijit.byId('networkTabContainer');
                var networkSubnetContentPane = dijit.byId('subnetContentPane');
                var networkPortContentPane = dijit.byId('portContentPane');
//                if(resultData.action == true) {
//                    
//                } else {
//                    
//                    mainTab.removeChild(networkSubnetContentPane); 
//                    networkSubnetContentPane.destroyRecursive();
//                    mainTab.removeChild(networkPortContentPane); 
//                    networkPortContentPane.destroyRecursive();
//                }
            });
        });
    },
    'gotoList': function() {
        if(dijit.byId("pullNetworkLoader")) {
            
            dijit.byId("pullNetworkLoader").hide();
            core.router.go("#/user/network/list");
        }
        if(dijit.byId("networkLoader")) {
            dijit.byId("networkLoader").hide();
        }
    }
      
};

var SubnetInfo = {
  
    populateValues: function () {
        
        var networkId = dojo.byId("selectedNetworkId").value;
        var action = dojo.byId("networkAction").value;
        console.log("action"+action)
        if(action == "true") {
            dojo.byId("networkViewSubnetListDiv").style.display = "block";
        } else {
            dojo.byId("networkViewSubnetListDiv").style.display = "none";
        }
        
        dojo.byId("noSubnetMessageBox").style.display = "none";
        dojo.byId("userSubnetList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.subnetLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true',},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) { 
                        
                        return "<a href='#/user/network/viewSubnet/" + data.referenceId +"' title='" + translator.common.view + "'>" + data.name + "</a>";

                    }
                },
                {'name': translator.common.networkAddress, 'field': 'networkAddress', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ipVersion, 'field': 'ipVersion', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gatewayIp, 'field': 'gatewayIp', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},              
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function (data) {
                        
                        if(data.action == true) {
                            
                            var menu = new DropDownMenu({style: "display: none;"});
                        
                            var menuItem1 = new MenuItem({
                                label: translator.common.view,
                                onClick: function() {
                                    core.router.go("#/user/network/viewSubnet/"+ data.referenceId + "");
                                }
                            });
                            menu.addChild(menuItem1);
                            menu.startup();

                            var menuItem2 = new MenuItem({
                                label: translator.common.edit,
                                onClick: function() {
                                   core.router.go("#/user/network/editSubnet/"+ data.referenceId + "");
                                }
                            });
                            menu.addChild(menuItem2);
                            menu.startup();

                            var menuItem3 = new MenuItem({
                                label: translator.common.deleteData,
                                onClick: function() {
                                    SubnetInfo.getDeleteConfirmationDialog(data.referenceId);
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
                            
                        } else {
                            return "";
                        }
                        
                        
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var subnetsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/subnetList"
        });

        subnetsRestStore.query({networkId : networkId}).then(function (data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userSubnetList").innerHTML = "";
                dojo.byId("noSubnetMessageBox").style.display = "block";

            } else {

                dojo.byId("noSubnetMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: resultData,
                        networkAddress: resultData.cidr,
                        ipVersion: resultData.IPVersion,
                        gatewayIp: resultData.gatewayIP == null ? "-" : resultData.gatewayIP,                       
                        action: resultData
                    });
                });
                dojo.byId("userSubnetList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userSubnetList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    }, 
    'View': function (id) {
        
        var subnetViewRest = new JsonRest({
            target : core.getContextPath() + "/api/network/subnetList"
        });
        
        subnetViewRest.query({subnetId : id}).then(function(data) {
           dojo.forEach(data, function(subnetData) {
              dojo.byId("subnetNameInfo").innerHTML = subnetData.name; 
              dojo.byId("subnetIdInfo").innerHTML = subnetData.referenceId; 
              dojo.byId("subnetNetworkIdInfo").innerHTML = subnetData.network.referenceId; 
                             
              dojo.byId("subnetListLink").href = "#/user/network/subnetList/"+subnetData.network.referenceId;
              dojo.byId("networkName").innerHTML = subnetData.network.name;
              dojo.byId("networkName").href = "#/user/network/view/"+subnetData.network.referenceId;
              dojo.byId("subnetName").innerHTML = subnetData.name;

              dojo.byId("SubnetIpVersionInfo").innerHTML = subnetData.IPVersion; 
              dojo.byId("subnetCidrInfo").innerHTML = subnetData.cidr; 
              dojo.byId("subnetIpAllocationInfo").innerHTML = subnetData.allocationPools; 
              dojo.byId("subnetDhcpInfo").innerHTML = subnetData.DHCPEnable == 0 ? "No" : "Yes"; 
              dojo.byId("subnetGatewayIpInfo").innerHTML = subnetData.gatewayIP == null ? "none" : subnetData.gatewayIP; 
              dojo.byId("subnetAdditionalRoutesInfo").innerHTML = subnetData.hostRoutes == null ? "none" : subnetData.hostRoutes; 
              dojo.byId("subnetDnsNameServerInfo").innerHTML = subnetData.DNSNameServers ==  null ? "none" : subnetData.DNSNameServers; 
           });
        });
        
    },
    'redirectToCreateForm' : function() {
        var networkId = dojo.byId("selectedNetworkId").value;
        core.router.go("#/user/network/createSubnet/"+ networkId +""); 
    },
    'createFormLoad': function(id) {
        
        dojo.byId("subnetPagehead").innerHTML = translator.common.createSubnet;
        
        var subnetsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/subnetList"
        });
        
        subnetsRestStore.query({networkId : id}).then(function(data) {
           dojo.forEach(data, function(subnetData){
              dojo.byId("networkName").innerHTML = subnetData.network.name;
              dojo.byId("subnetNetworkId").value = subnetData.network.referenceId;
              dojo.byId("networkName").href = "#/user/network/view/"+subnetData.network.referenceId;
              dojo.byId("subnetListLink").href = "#/user/network/subnetList/"+subnetData.network.referenceId;
//              dojo.byId("subnetListLinkLoader").href = "#/user/network/subnetList/"+subnetData.network.referenceId;
           });
        });
    }, 
    'editFormLoad': function(id) {

        var subnetsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/subnetList"
        });
        dojo.byId("currentSubnetId").value = id;
        subnetsRestStore.query({subnetId : id}).then(function(data) {
           dojo.forEach(data, function(subnetData){
              dojo.byId("subnetPagehead").innerHTML = translator.common.editSubnet + " : " + subnetData.name;
              dojo.byId("networkName").innerHTML = subnetData.network.name;
              dojo.byId("editSubnetBreadCrumbs").innerHTML = translator.common.edit;
              dojo.byId("subnetNetworkId").value = subnetData.network.referenceId;
              dojo.byId("networkName").href = "#/user/network/view/"+subnetData.network.referenceId;
              dojo.byId("subnetListLink").href = "#/user/network/subnetList/"+subnetData.network.referenceId;
              dijit.byId("networkSubnetName").setValue(subnetData.name);
              dijit.byId("networkSubnetAddress").setValue(subnetData.cidr);
              dijit.byId("networkSubnetAddress").setAttribute('disabled', true);
              dijit.byId("subnetIpVersions").setValue(subnetData.IPVersion);
              dijit.byId("subnetIpVersions").setAttribute('disabled', true);
              dijit.byId("subnetGatewayIp").setValue(subnetData.gatewayIP);
//              dojo.byId("subnetListLinkLoader").href = "#/user/network/subnetList/"+subnetData.network.referenceId;
           });
        });
        
        dojo.byId("subnetNetworkAddButtonDiv").style.display = "none";
        dojo.byId("subnetNetworkUpdateButtonDiv").style.display = "block";
    }, 
    'cancel': function() {
        var networkId = dojo.byId("subnetNetworkId").value;
        core.router.go("#/user/network/subnetList/"+networkId+"");
    },
    'add': function() {
        var networkId = dojo.byId("subnetNetworkId").value;
        
        dojo.byId("subnetNetworkAddButtonDiv").style.display = "block";
        var status = true;
        var pageNode = dojo.byId("subnetContent");
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

            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/addSubnet"
            });

            var subnetName = dijit.byId("networkSubnetName").value;
            var networkAddress = dijit.byId("networkSubnetAddress").value;
            var ipVersion = dijit.byId("subnetIpVersions").value;
            var gatewayIp = dijit.byId("subnetGatewayIp").value;
            
            if (networkAddress.indexOf("/") == -1) {
                dijit.byId("networkSubnetAddress").focus();
                registry.byId("userToaster").setContent(translator.common.message.invalidCidr , "error");
                registry.byId("userToaster").show();
            } else {
                
                dijit.byId("pullSubnetLoader").show();

                networkRestStore.add({
                    networkId : networkId,
                    subnetName: subnetName,
                    networkAddress: networkAddress,
                    ipVersion: ipVersion,
                    gatewayIp: gatewayIp

                }).then(function (data) {
                    dojo.forEach(data, function (resultData) {
                        if (resultData.result === "OK") {
                            registry.byId("userToaster").setContent(translator.common.message.addedSuccess, "message");
                            registry.byId("userToaster").show();
                            dijit.byId("pullSubnetLoader").hide();
                            core.router.go("#/user/network/subnetList/"+ networkId +"");
                        } else {
                            registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                            registry.byId("userToaster").show();
                            dijit.byId("pullSubnetLoader").hide();
                        }
                    });
                });
                
            }
 
        }
    },
    'update': function () {
        
        var subnetId = dojo.byId("currentSubnetId").value;
        var networkId = dojo.byId("subnetNetworkId").value;
        
        var status = true;
        var pageNode = dojo.byId("subnetContent");
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

            var subnetUpdateRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/updateSubnet/"
            });

            var subnetName = dijit.byId("networkSubnetName").value;
            var gatewayIp = dijit.byId("subnetGatewayIp").value;

            dijit.byId("pullSubnetLoader").show();

            subnetUpdateRestStore.put({
                id: subnetId,
                name: subnetName,
                gateway: gatewayIp

            }).then(function (data) {
                dojo.forEach(data, function (resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("pullSubnetLoader").hide();
                        core.router.go("#/user/network/subnetList/"+ networkId +"");
                    } else {
                        registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("pullSubnetLoader").hide();
                    }
                });
            });
        }
    },
    'gotoList': function() {
       var networkId = dojo.byId("subnetNetworkId").value; 
       dijit.byId("pullSubnetLoader").hide();
       core.router.go("#/user/network/subnetList/"+ networkId +"");
    },
    'getDeleteConfirmationDialog': function(id) {
        dojo.byId("currentSubnetId").value = id;
        dijit.byId("deleteSubnetDialog").show();
    },
    'closeDeleteSubnetDialog': function() {
        dijit.byId("deleteSubnetDialog").hide();
    },
    'delete': function() {
        var id = dojo.byId("currentSubnetId").value; 
        dijit.byId("deleteSubnetDialog").hide();
        var deleteNetworkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/deleteSubnet/"
        });
//        dijit.byId("networkLoader").show();
        deleteNetworkRestStore.remove(id).then(function (data) {

                if (data === "OK") {
                registry.byId("userToaster").setContent(translator.common.message.subnetDeleted, "message");
                registry.byId("userToaster").show();
//                dijit.byId("networkLoader").hide();
                SubnetInfo.populateValues();
            } else {
                registry.byId("userToaster").setContent(data, "error");
                registry.byId("userToaster").show();
//                dijit.byId("networkLoader").hide();
                SubnetInfo.populateValues();
            }
        });
    },
};
var PortInfo = {
  
    'populateValues': function() {
      
        var networkId = dojo.byId("selectedNetworkId").value;
        
        dojo.byId("noPortMessageBox").style.display = "none";
        dojo.byId("userPortList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.portLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true',},
                {'name': translator.common.name, 'field': 'name', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) { 
                        if(data.name != "") {
                            return "<a href='#/user/network/viewPort/" + data.referenceId +"' title='" + translator.common.view + "'>" + data.name + "</a>";
                        } else {
                            return "<a href='#/user/network/viewPort/" + data.referenceId +"' title='" + translator.common.view + "'>" +"("+ data.referenceId +")"+"</a>";
                        }
                    }
                },
                {'name': translator.common.fixedIp, 'field': 'fixedIp', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true',  'formatter': function(data) {
                        
//                        return "<span class='bold'>" + "IP address: "+ data.ipAddress + " | " + "Subnet ID: "+ data.subnetId + "</span>";
                        return "<span class='bold'>" + data.ipAddress + "</span>";
                    }},
                {'name': translator.common.attachedDevice, 'field': 'attachedDevice', 'width' : '200px', 'dataType' : 'string' , 'autoComplete' : 'true'},
                {'name': translator.common.macAddress, 'field': 'macAddress', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.status.name, 'field': 'status', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},  
                {'name': translator.common.adminState, 'field': 'adminState', 'width': '149px', 'datatype': 'string', 'autoComplete': 'true'},                 
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function (data) {
                        
                        if(data.action == true) {
                            
                            var menu = new DropDownMenu({style: "display: none;"});
                        
                            var menuItem1 = new MenuItem({
                                label: translator.common.view,
                                onClick: function() {
                                    core.router.go("#/user/network/viewPort/"+ data.referenceId + "");
                                }
                            });
                            menu.addChild(menuItem1);
                            menu.startup();

                            var menuItem2 = new MenuItem({
                                label: translator.common.edit,
                                onClick: function() {
                                   PortInfo.editFormDialog(data.referenceId);
                                }
                            });
                            menu.addChild(menuItem2);
                            menu.startup();

                            var button = new ComboButton({
                                label: "More",
                                name: "More",
                                dropDown: menu
                            });
                            return button;
                        } else {
                            return "-";
                        }
                        
                        
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var nicRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/portList"
        });

        nicRestStore.query({networkId : networkId}).then(function (data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userPortList").innerHTML = "";
                dojo.byId("noPortMessageBox").style.display = "block";

            } else {

                dojo.byId("noPortMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: resultData,
                        fixedIp: resultData,
                        attachedDevice: resultData.deviceOwner,
                        macAddress: resultData.macAddress,
                        status: resultData.status.name,
                        adminState: resultData.adminState == true ? "UP" : "DOWN", 
                        action: resultData,
                    });
                });
                dojo.byId("userPortList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userPortList");
                dataGrid.startup();
                dataGrid.update();
            }
        });  
        
    },
    'view': function(portId) {
        
         var portViewRest = new JsonRest({
            target : core.getContextPath() + "/api/network/portView"
        });
        dojo.byId("viewPortFormLoader").style.display = "block";
        dojo.byId("viewPortFormLoader").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";
        
        portViewRest.query({portId : portId}).then(function(data) {
           dojo.forEach(data, function(portData) {
              dojo.byId("portNameInfo").innerHTML = portData.name == "" ? "None" : portData.name; 
              dojo.byId("portIdInfo").innerHTML = portData.referenceId; 
              dojo.byId("portNetworkIdInfo").innerHTML = portData.network.referenceId; 
                             
              dojo.byId("portListLink").href = "#/user/network/portList/"+portData.network.referenceId;
              dojo.byId("networkName").innerHTML = portData.network.name;
              dojo.byId("networkName").href = "#/user/network/view/"+portData.network.referenceId;
              dojo.byId("portName").innerHTML = portData.name;

              dojo.byId("portProjectIdInfo").innerHTML = portData.projectId; 
              dojo.byId("portIpAddressInfo").innerHTML = portData.ipAddress; 
              dojo.byId("portSubnetIdInfo").innerHTML = portData.subnetId; 
              dojo.byId("portMacAddressInfo").innerHTML = portData.macAddress; 
              dojo.byId("portStatusInfo").innerHTML = portData.status.name; 
              dojo.byId("portAdminStatusInfo").innerHTML = portData.adminState == true ? "UP" : "DOWN"; 
              dojo.byId("portDeviceIdInfo").innerHTML = portData.deviceId; 
              dojo.byId("portDeviceOwnerInfo").innerHTML = portData.deviceOwner; 
           });
           
           dojo.byId("viewPortFormLoader").style.display = "none";
           dojo.byId("viewPortFormDiv").style.display = "block";
        });
        
        
    },
    'editFormDialog': function(id) {
      
        dojo.byId("currentPortId").value = id;
        dijit.byId("portEditFormDialog").show();
    },
    'closeEditPortDialog': function() {
        
        dijit.byId("portEditFormDialog").hide();
    },
    'update': function() {
        
        var portId = dojo.byId("currentPortId").value;
        
        var status = true;
        var pageNode = dojo.byId("editNetworkPortFormPage");
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
            dijit.byId("portEditFormDialog").hide();
            
            var portUpdateRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/updatePort/"
            });

            var portName = dijit.byId("networkPortName").value;

            portUpdateRestStore.put({
                id: portId,
                name: portName,

            }).then(function (data) {
                dojo.forEach(data, function (resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("userToaster").show();
                        PortInfo.populateValues();
                    } else {
                        registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("userToaster").show();
                        PortInfo.populateValues();

                    }
                });
            });
        }
        
        
    },
};




