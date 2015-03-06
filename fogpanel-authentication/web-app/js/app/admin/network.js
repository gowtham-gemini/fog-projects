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
    "FogPanel/ZonePrice",
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
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
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
                module: "admin",
                filePath: "/js/app/admin/network.js",
                layout: {
                    name: "network",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "list": action(function() {

                    if (dijit.byId("adminNetworkListForm")) {
                        dijit.byId("adminNetworkListForm").destroyRecursive();
                    }
                    if (dijit.byId("adminDeleteNetworkDialog")) {
                        dijit.byId("adminDeleteNetworkDialog").destroyRecursive();
                    }
                    if (dijit.byId("adminNetworkLoader")) {
                        dijit.byId("adminNetworkLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminNetworkEditFormDialog")) {
                        dijit.byId("adminNetworkEditFormDialog").destroyRecursive();
                    }
                    if (dijit.byId("adminNetworkUpdateLoader")) {
                        dijit.byId("adminNetworkUpdateLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("adminNetworkList", core.ui.getContentId());
                    Networks.init();
                    Networks.populateValues();
                }),
                "create": action(function() {

                    if (dijit.byId("adminNetworkAddForm")) {
                        dijit.byId("adminNetworkAddForm").destroyRecursive();
                    }
                    if (dijit.byId("adminCreateNetworkLoader")) {
                        dijit.byId("adminCreateNetworkLoader").destroyRecursive();
                    }

                    core.ui.loadTemplate("addNetwork", core.ui.getContentId());
                    NetworkInfo.init();

                }),
                "edit": action(function(id) {
                    if (dijit.byId("adminNetworkAddForm")) {
                        dijit.byId("adminNetworkAddForm").destroyRecursive();
                    }
                    if (dijit.byId("adminCreateNetworkLoader")) {
                        dijit.byId("adminCreateNetworkLoader").destroyRecursive();
                    }

                    core.ui.loadTemplate("addNetwork", core.ui.getContentId());
                    NetworkInfo.showEditNetworkDialog(id);
                }),
                "view": action(function(id) {
                    if (dijit.byId("adminNetworkTabContainer")) {
                        dijit.byId("adminNetworkTabContainer").destroyRecursive();
                    }
                    if (dijit.byId("adminNetworkContentPane")) {
                        dijit.byId("adminNetworkContentPane").destroyRecursive();
                    }
                    if (dijit.byId("adminuSbnetContentPane")) {
                        dijit.byId("adminuSbnetContentPane").destroyRecursive();
                    }
                    if (dijit.byId("adminListSubnetForm")) {
                        dijit.byId("adminListSubnetForm").destroyRecursive();
                    }
                    if (dijit.byId("adminDeleteSubnetDialog")) {
                        dijit.byId("adminDeleteSubnetDialog").destroyRecursive();
                    }
                    if (dijit.byId("adminDeleteSubnetLoader")) {
                        dijit.byId("adminDeleteSubnetLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminPortLoader")) {
                        dijit.byId("adminPortLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminPortEditFormDialog")) {
                        dijit.byId("adminPortEditFormDialog").destroyRecursive();
                    }
                    if (dijit.byId("adminListPortForm")) {
                        dijit.byId("adminListPortForm").destroyRecursive();
                    }
                    if (dijit.byId("adminDeletePortDialog")) {
                        dijit.byId("adminDeletePortDialog").destroyRecursive();
                    }
                    core.ui.loadTemplate("adminNetworkView", core.ui.getContentId());
                    NetworkInfo.view(id);
                }),
                "createSubnet": action(function(id) {

                    if (dijit.byId("adminSubnetAddForm")) {
                        dijit.byId("adminSubnetAddForm").destroyRecursive();
                    }
                    if (dijit.byId("adminCreateSubnetLoader")) {
                        dijit.byId("adminCreateSubnetLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminUpdateSubnetLoader")) {
                        dijit.byId("adminUpdateSubnetLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("adminCreateSubnet", core.ui.getContentId());
                    SubnetInfo.createFormLoad(id);
                }),
                "editSubnet": action(function(id) {

                    if (dijit.byId("adminSubnetAddForm")) {
                        dijit.byId("adminSubnetAddForm").destroyRecursive();
                    }
                    if (dijit.byId("adminCreateSubnetLoader")) {
                        dijit.byId("adminCreateSubnetLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminUpdateSubnetLoader")) {
                        dijit.byId("adminUpdateSubnetLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("adminCreateSubnet", core.ui.getContentId());
                    SubnetInfo.editFormLoad(id);
                }),
                "viewSubnet": action(function(subnetId) {
                    core.ui.loadTemplate("adminViewSubnet", core.ui.getContentId());
                    SubnetInfo.View(subnetId);
                }),
                "subnetList": action(function(id) {
                    if (dijit.byId("adminNetworkTabContainer")) {
                        dijit.byId("adminNetworkTabContainer").destroyRecursive();
                    }
                    if (dijit.byId("adminNetworkContentPane")) {
                        dijit.byId("adminNetworkContentPane").destroyRecursive();
                    }
                    if (dijit.byId("adminuSbnetContentPane")) {
                        dijit.byId("adminuSbnetContentPane").destroyRecursive();
                    }
                    if (dijit.byId("adminListSubnetForm")) {
                        dijit.byId("adminListSubnetForm").destroyRecursive();
                    }
                    if (dijit.byId("adminDeleteSubnetDialog")) {
                        dijit.byId("adminDeleteSubnetDialog").destroyRecursive();
                    }
                    if (dijit.byId("adminDeleteSubnetLoader")) {
                        dijit.byId("adminDeleteSubnetLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminPortLoader")) {
                        dijit.byId("adminPortLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminPortEditFormDialog")) {
                        dijit.byId("adminPortEditFormDialog").destroyRecursive();
                    }
                    if (dijit.byId("adminListPortForm")) {
                        dijit.byId("adminListPortForm").destroyRecursive();
                    }
                    if (dijit.byId("adminDeletePortDialog")) {
                        dijit.byId("adminDeletePortDialog").destroyRecursive();
                    }
                    core.ui.loadTemplate("adminNetworkView", core.ui.getContentId());
                    NetworkInfo.view(id);

                    setTimeout(function() {
                        var mainTab = dijit.byId("adminNetworkTabContainer"); //Tr
                        var subTab = dijit.byId("adminSubnetContentPane"); //tab Id which you want to show
                        mainTab.selectChild(subTab);
                    }, 100);

                }),
                "createPort": action(function(id) {

                    if (dijit.byId("adminNetworkPortAddForm")) {
                        dijit.byId("adminNetworkPortAddForm").destroyRecursive();
                    }
                    if (dijit.byId("adminCreatePortLoader")) {
                        dijit.byId("adminCreatePortLoader").destroyRecursive();
                    }

                    core.ui.loadTemplate("adminCreatePort", core.ui.getContentId());
                    PortInfo.createFormLoad(id);
                }),
                "viewPort": action(function(PortId) {
                    core.ui.loadTemplate("adminViewPort", core.ui.getContentId());
                    PortInfo.view(PortId);
                }),
                "portList": action(function(id) {

                    if (dijit.byId("adminNetworkTabContainer")) {
                        dijit.byId("adminNetworkTabContainer").destroyRecursive();
                    }
                    if (dijit.byId("adminNetworkContentPane")) {
                        dijit.byId("adminNetworkContentPane").destroyRecursive();
                    }
                    if (dijit.byId("adminuSbnetContentPane")) {
                        dijit.byId("adminuSbnetContentPane").destroyRecursive();
                    }
                    if (dijit.byId("adminListSubnetForm")) {
                        dijit.byId("adminListSubnetForm").destroyRecursive();
                    }
                    if (dijit.byId("adminDeleteSubnetDialog")) {
                        dijit.byId("adminDeleteSubnetDialog").destroyRecursive();
                    }
                    if (dijit.byId("adminDeleteSubnetLoader")) {
                        dijit.byId("adminDeleteSubnetLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminPortLoader")) {
                        dijit.byId("adminPortLoader").destroyRecursive();
                    }
                    if (dijit.byId("adminPortEditFormDialog")) {
                        dijit.byId("adminPortEditFormDialog").destroyRecursive();
                    }
                    if (dijit.byId("adminListPortForm")) {
                        dijit.byId("adminListPortForm").destroyRecursive();
                    }
                    if (dijit.byId("adminDeletePortDialog")) {
                        dijit.byId("adminDeletePortDialog").destroyRecursive();
                    }
                    core.ui.loadTemplate("adminNetworkView", core.ui.getContentId());
                    NetworkInfo.view(id);

                    setTimeout(function() {
                        var mainTab = dijit.byId("adminNetworkTabContainer"); //Tr
                        var subTab = dijit.byId("adminPortContentPane"); //tab Id which you want to show
                        mainTab.selectChild(subTab);
                    }, 100);
                }),
            });
        });
var Networks = {
    init: function() {


    },
    populateValues: function() {
        dojo.byId("adminNetworkList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
//                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {
                        var viewTag = "<a href='#/admin/network/view/" + data.referenceId + "' title='" + translator.common.edit + "'>" + data.name + "</a>";
                        return viewTag;
                    }},
                {'name': translator.common.projectName, 'field': 'projectName', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.subnetsAssociated, 'field': 'subnetsAssociated', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.shared, 'field': 'shared', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.status.name, 'field': 'status', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.adminState, 'field': 'adminState', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var menu = new DropDownMenu({style: "display: none;"});

                        var menuItem1 = new MenuItem({
                            label: translator.common.view,
                            onClick: function() {
                                core.router.go("#/admin/network/view/" + data.referenceId + "");
                            }
                        });
                        menu.addChild(menuItem1);
                        menu.startup();

                        var menuItem2 = new MenuItem({
                            label: translator.common.edit,
                            onClick: function() {
                                core.router.go("#/admin/network/edit/" + data.referenceId + "");
//                                NetworkInfo.showEditNetworkDialog(data.referenceId);
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

                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/listByAdmin"
        });

        networkRestStore.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("adminNetworkList").innerHTML = "";
                dojo.byId("noNetworkAdminMessageBox").style.display = "block";

            } else {

                dojo.byId("noNetworkAdminMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        name: resultData,
                        projectName: resultData.projectName,
                        subnetsAssociated: resultData.subnetsAssociated,
                        shared: resultData.isShared === true ? translator.common.yes : translator.common.no,
                        status: resultData.status,
                        adminState: resultData.isAdminState === true ? translator.common.up : translator.common.down,
                        action: resultData
                    });
                });
                dojo.byId("adminNetworkList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
//                        id: 'computDataGrid',
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminNetworkList");
                dataGrid.startup();
                dataGrid.update();

            }
        });
    },
};
var NetworkInfo = {
    'init': function() {
        var accountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });
        var accountOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.byId("accountListInNetwork").innerHTML = translator.common.loading;
        var accountList = new ItemFileWriteStore({data: accountOptions});
//        accountList.newItem({id: "", name: ""});
        accountRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
                accountList.newItem({id: el.uuid, name: el.userName});
            });
            dojo.byId("accountListInNetwork").innerHTML = "";
            var accountWidget = new FilteringSelect({
                store: accountList,
                id: "accountListWidget",
                required: true,
                placeHolder: translator.common.selectAccount,
                onChange: function() {
                }
            });
            accountWidget.placeAt("accountListInNetwork");
            accountWidget.startup();
        });
    },
    'add': function() {
        dojo.byId("adminNetworkAddButtonDiv").style.display = "block";
        var status = true;
        var pageNode = dojo.byId("adminNetworkContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function(el) {
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
                target: core.getContextPath() + "/api/network/createByAdmin"
            });

            var name = dijit.byId("adminNetworkName").value;
            var projectId = dijit.byId("accountListWidget").value;
            var isAdminState = true;
            var isShared = false;
            var isExternal = false;

            if (dijit.byId("networkAdminState").checked == false) {
                isAdminState = false;
            }
            if (dijit.byId("networkisShared").checked == true) {
                isShared = true;
            }
            if (dijit.byId("networkIsExternal").checked == true) {
                isExternal = true;
            }

            dijit.byId("adminCreateNetworkLoader").show();

            networkRestStore.add({
                name: name,
                projectId: projectId,
                isAdminState: isAdminState,
                isRouterExternal: isExternal,
                isShared: isShared,
            }).then(function(data) {

                if (data[0].result === "OK") {
                    registry.byId("appToaster").setContent(name + " N/W " + translator.common.message.addedSuccess, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("adminCreateNetworkLoader").hide();
                    core.router.go("#/admin/network/list");
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.cannotBeAdded, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("adminCreateNetworkLoader").hide();
                }
            });
        }
    },
    'update': function() {

        var pageNode = dojo.byId("adminNetworkContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
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
        } else {
            var updateNetworkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/"
            });
            dijit.byId("adminCreateNetworkLoader").show();
            var name = dijit.byId("adminNetworkName").value;
            var id = dojo.byId("currentAdminNetworkId").value;
            var isAdminState = true;
            var isShared = true;
            var isExternal = true;
            if (dijit.byId("networkAdminState").checked == false) {
                isAdminState = false;
            }
            if (dijit.byId("networkisShared").checked == false) {
                isShared = false;
            }
            if (dijit.byId("networkIsExternal").checked == false) {
                isExternal = false;
            }

            updateNetworkRestStore.put({
                name: name,
                id: id,
                isAdminState: isAdminState,
                isShared: isShared,
                isExternal: isExternal,
            }).then(function(data) {
                dijit.byId("adminCreateNetworkLoader").hide();
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {

                        registry.byId("appToaster").setContent(name + " N/W " + translator.common.message.updatedSuccess, "message");
                        registry.byId("appToaster").show();
                        core.router.go("#/admin/network/list");
//                        Networks.populateValues();
                    } else {
                        registry.byId("appToaster").setContent(data.message, "error");
                        registry.byId("appToaster").show();
//                        Networks.populateValues();

                    }
                })

            });
        }
    },
    'getDeleteConfirmationDialog': function(id) {
        dijit.byId("adminDeleteNetworkDialog").show();
        dojo.byId("currentNetworkId").value = id;
    },
    'delete': function() {
        dijit.byId("adminDeleteNetworkDialog").hide();
        var id = dojo.byId("currentNetworkId").value;

        var deleteNetworkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/deleteByAdmin/"
        });
        dijit.byId("adminNetworkLoader").show();
        deleteNetworkRestStore.remove(id).then(function(data) {

            if (data === "OK") {
                registry.byId("appToaster").setContent(translator.common.message.networkDeleted, "message");
                registry.byId("appToaster").show();
                dijit.byId("adminNetworkLoader").hide();
                Networks.populateValues();
            } else {
                registry.byId("appToaster").setContent(data, "error");
                registry.byId("appToaster").show();
                dijit.byId("adminNetworkLoader").hide();
                Networks.populateValues();
            }
        });

    },
    'closeDeleteNetworkDialog': function() {
        dijit.byId("adminDeleteNetworkDialog").hide();
    },
    'showEditNetworkDialog': function(id) {
//        dijit.byId("adminNetworkEditFormDialog").show();

        dojo.byId("editNetworkBreadCrumbs").innerHTML = translator.common.edit;
        dojo.byId("adminNetworkAddButtonDiv").style.display = "none";
        dojo.byId("adminNetworkUpdateButtonDiv").style.display = "block";

        var viewNetworkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/listByAdmin"
        });

        viewNetworkRestStore.query({referenceId: id}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("currentAdminNetworkId").value = id;
                dijit.byId("adminNetworkName").setValue(resultData.name);
                dojo.byId("accountListInNetwork").innerHTML = resultData.projectName;
                dojo.byId("accountListInNetwork").disabled = true;
                if (resultData.isAdminState == true) {
                    dijit.byId("networkAdminState").set('checked', true);
                } else {
                    dijit.byId("networkAdminState").set('checked', false);
                }

                if (resultData.isShared == true) {
                    dijit.byId("networkisShared").set('checked', true)
                } else {
                    dijit.byId("networkisShared").set('checked', false);
                }

                if (resultData.isExternal == true) {
                    dijit.byId("networkIsExternal").set('checked', true);
                } else {
                    dijit.byId("networkIsExternal").set('checked', false);
                }
            });
        });
    },
    'closeEditNetworkDialog': function() {
        dijit.byId("adminNetworkEditFormDialog").hide();
    },
    'view': function(id) {
        var viewNetworkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/listByAdmin"
        });

        dojo.byId("adminSelectedNetworkId").value = id;

        viewNetworkRestStore.query({referenceId: id}).then(function(data) {
            dojo.forEach(data, function(resultData) {

                dojo.byId("adminViewNetworkName").innerHTML = resultData.name;
                dojo.byId("adminSelectedNetworkName").value = resultData.name;

                dojo.byId("adminNetworkNameInfo").innerHTML = resultData.name;
                dojo.byId("adminNetworkIdInfo").innerHTML = resultData.referenceId;
//                dojo.byId("networkProjectIdInfo").innerHTML = resultData.projectId;
                dojo.byId("adminNetworkSubnetInfo").innerHTML = resultData.subnetsAssociated;
                dojo.byId("adminNetworkStatusInfo").innerHTML = resultData.status;
                dojo.byId("adminNetworkAdminStateInfo").innerHTML = resultData.isAdminState === true ? translator.common.up : translator.common.down;
                dojo.byId("adminNetworkSharedInfo").innerHTML = resultData.isShared === true ? translator.common.yes : translator.common.no;
            });
        });
    },
    'gotoList': function() {
        dijit.byId("adminCreateNetworkLoader").hide();
        core.router.go("#/admin/network/list");
    },
    'closeLoader': function() {
        if (dijit.byId("adminNetworkUpdateLoader")) {
            dijit.byId("adminNetworkUpdateLoader").hide();
        }
        if (dijit.byId("adminNetworkLoader")) {
            dijit.byId("adminNetworkLoader").hide();
        }
    },
    cancel : function () {
        dijit.byId("adminNetworkAddForm").reset();
    }
};
var SubnetInfo = {
    populateValues: function() {

        var networkId = dojo.byId("adminSelectedNetworkId").value;

        dojo.byId("adminNoSubnetMessageBox").style.display = "none";
        dojo.byId("adminSubnetList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.subnetLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true', },
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        return "<a href='#/admin/network/viewSubnet/" + data.referenceId + "' title='" + translator.common.view + "'>" + data.name + "</a>";

                    }
                },
                {'name': translator.common.networkAddress, 'field': 'networkAddress', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ipVersion, 'field': 'ipVersion', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gatewayIp, 'field': 'gatewayIp', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {

                        var menu = new DropDownMenu({style: "display: none;"});

                        var menuItem1 = new MenuItem({
                            label: translator.common.view,
                            onClick: function() {
                                core.router.go("#/admin/network/viewSubnet/" + data.referenceId + "");
                            }
                        });
                        menu.addChild(menuItem1);
                        menu.startup();

                        var menuItem2 = new MenuItem({
                            label: translator.common.edit,
                            onClick: function() {
                                core.router.go("#/admin/network/editSubnet/" + data.referenceId + "");
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
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var subnetsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/subnetList"
        });

        subnetsRestStore.query({networkId: networkId}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("adminSubnetList").innerHTML = "";
                dojo.byId("adminNoSubnetMessageBox").style.display = "block";

            } else {

                dojo.byId("adminNoSubnetMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: resultData,
                        networkAddress: resultData.cidr,
                        ipVersion: resultData.IPVersion,
                        gatewayIp: resultData.gatewayIP == null ? "-" : resultData.gatewayIP,
                        action: resultData
                    });
                });
                dojo.byId("adminSubnetList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminSubnetList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    'View': function(id) {

        var subnetViewRest = new JsonRest({
            target: core.getContextPath() + "/api/network/subnetList"
        });

        console.log("subnet id" + id)

        subnetViewRest.query({subnetId: id}).then(function(data) {
            dojo.forEach(data, function(subnetData) {
                dojo.byId("adminSubnetNameInfo").innerHTML = subnetData.name;
                dojo.byId("adminSubnetIdInfo").innerHTML = subnetData.referenceId;
                dojo.byId("adminSubnetNetworkIdInfo").innerHTML = subnetData.network.referenceId;

                dojo.byId("adminSubnetListLink").href = "#/admin/network/subnetList/" + subnetData.network.referenceId;
                dojo.byId("adminNetworkName").innerHTML = subnetData.network.name;
                dojo.byId("adminNetworkName").href = "#/admin/network/view/" + subnetData.network.referenceId;
                dojo.byId("adminSubnetName").innerHTML = subnetData.name;

                dojo.byId("adminSubnetIpVersionInfo").innerHTML = subnetData.IPVersion;
                dojo.byId("adminSubnetCidrInfo").innerHTML = subnetData.cidr;
                dojo.byId("adminSubnetIpAllocationInfo").innerHTML = subnetData.allocationPools;
                dojo.byId("adminSubnetDhcpInfo").innerHTML = subnetData.DHCPEnable == 0 ? "No" : "Yes";
                dojo.byId("adminSubnetGatewayIpInfo").innerHTML = subnetData.gatewayIP == null ? "none" : subnetData.gatewayIP;
                dojo.byId("adminSubnetAdditionalRoutesInfo").innerHTML = subnetData.hostRoutes == null ? "none" : subnetData.hostRoutes;
                dojo.byId("adminSubnetDnsNameServerInfo").innerHTML = subnetData.DNSNameServers == null ? "none" : subnetData.DNSNameServers;
            });
        });

    },
    'redirectToCreateForm': function() {
        var networkId = dojo.byId("adminSelectedNetworkId").value;
        core.router.go("#/admin/network/createSubnet/" + networkId + "");
    },
    'closeLoader': function() {
        dijit.byId("adminDeleteSubnetLoader").hide();
    },
    'createFormLoad': function(id) {

        dojo.byId("adminSubnetPagehead").innerHTML = translator.common.createSubnet;

        var subnetsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/listByAdmin"
        });

        subnetsRestStore.query({networkId: id}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("adminNetworkName").innerHTML = resultData.name;
                dojo.byId("adminSubnetNetworkId").value = resultData.referenceId;
                dojo.byId("adminNetworkName").href = "#/admin/network/view/" + resultData.referenceId;
                dojo.byId("adminSubnetListLink").href = "#/admin/network/subnetList/" + resultData.referenceId;
//              dojo.byId("subnetListLinkLoader").href = "#/user/network/subnetList/"+subnetData.network.referenceId;
            });
        });
    },
    'editFormLoad': function(id) {

        var subnetsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/subnetList"
        });
        dojo.byId("adminCurrentSubnetId").value = id;
        subnetsRestStore.query({subnetId: id}).then(function(data) {
            dojo.forEach(data, function(subnetData) {
                dojo.byId("adminSubnetPagehead").innerHTML = translator.common.editSubnet + " : " + subnetData.name;
                dojo.byId("adminNetworkName").innerHTML = subnetData.network.name;
                dojo.byId("editSubnetBreadCrumbs").innerHTML = translator.common.edit;
                dojo.byId("adminSubnetNetworkId").value = subnetData.network.referenceId;
                dojo.byId("adminNetworkName").href = "#/admin/network/view/" + subnetData.network.referenceId;
                dojo.byId("adminSubnetListLink").href = "#/admin/network/subnetList/" + subnetData.network.referenceId;
                dijit.byId("adminNetworkSubnetName").setValue(subnetData.name);
                dijit.byId("adminNetworkSubnetAddress").setValue(subnetData.cidr);
                dijit.byId("adminNetworkSubnetAddress").setAttribute('disabled', true);
                dijit.byId("adminSubnetIpVersions").setValue(subnetData.IPVersion);
                dijit.byId("adminSubnetIpVersions").setAttribute('disabled', true);
                dijit.byId("adminSubnetGatewayIp").setValue(subnetData.gatewayIP);
//              dojo.byId("subnetListLinkLoader").href = "#/user/network/subnetList/"+subnetData.network.referenceId;
            });
        });

        dojo.byId("adminSubnetNetworkAddButtonDiv").style.display = "none";
        dojo.byId("adminSubnetNetworkUpdateButtonDiv").style.display = "block";
    },
    'cancel': function() {
        var networkId = dojo.byId("adminSubnetNetworkId").value;
        core.router.go("#/admin/network/subnetList/" + networkId + "");
    },
    'add': function() {
        var networkId = dojo.byId("adminSubnetNetworkId").value;

        dojo.byId("adminSubnetNetworkAddButtonDiv").style.display = "block";
        var status = true;
        var pageNode = dojo.byId("adminSubnetContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function(el) {
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

            var subnetName = dijit.byId("adminNetworkSubnetName").value;
            var networkAddress = dijit.byId("adminNetworkSubnetAddress").value;
            var ipVersion = dijit.byId("adminSubnetIpVersions").value;
            var gatewayIp = dijit.byId("adminSubnetGatewayIp").value;

            if (!networkAddress.contains("/")) {
                dijit.byId("adminNetworkSubnetAddress").focus();
                registry.byId("appToaster").setContent(translator.common.message.invalidCidr , "error");
                registry.byId("appToaster").show();
            } else {
                dijit.byId("adminCreateSubnetLoader").show();

                networkRestStore.add({
                    networkId: networkId,
                    subnetName: subnetName,
                    networkAddress: networkAddress,
                    ipVersion: ipVersion,
                    gatewayIp: gatewayIp,
                }).then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        if (resultData.result === "OK") {
                            registry.byId("appToaster").setContent(name + " Subnet " + translator.common.message.addedSuccess, "message");
                            registry.byId("appToaster").show();
                            dijit.byId("adminCreateSubnetLoader").hide();
                            core.router.go("#/admin/network/subnetList/" + networkId + "");
                        } else {
                            registry.byId("appToaster").setContent(resultData.localizedMessage, "error");
                            registry.byId("appToaster").show();
                            dijit.byId("adminCreateSubnetLoader").hide();
                        }
                    });
                });
            }

        }
    },
    'update': function() {

        var subnetId = dojo.byId("adminCurrentSubnetId").value;
        var networkId = dojo.byId("adminSubnetNetworkId").value;

        var status = true;
        var pageNode = dojo.byId("adminSubnetContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function(el) {
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

            var subnetName = dijit.byId("adminNetworkSubnetName").value;
            var gatewayIp = dijit.byId("adminSubnetGatewayIp").value;

            dijit.byId("adminUpdateSubnetLoader").show();

            subnetUpdateRestStore.put({
                id: subnetId,
                name: subnetName,
                gateway: gatewayIp,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("appToaster").show();
                        dijit.byId("adminUpdateSubnetLoader").hide();
                        core.router.go("#/admin/network/subnetList/" + networkId + "");
                    } else {
                        registry.byId("appToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("adminUpdateSubnetLoader").hide();
                    }
                });
            });
        }
    },
    'gotoList': function() {
        var networkId = dojo.byId("adminSubnetNetworkId").value;
        if (dijit.byId("adminCreateSubnetLoader")) {
            dijit.byId("adminCreateSubnetLoader").hide();
        }
        if (dijit.byId("adminUpdateSubnetLoader")) {
            dijit.byId("adminUpdateSubnetLoader").hide();
        }
        core.router.go("#/admin/network/subnetList/" + networkId + "");
    },
    'getDeleteConfirmationDialog': function(id) {
        dojo.byId("currentSubnetId").value = id;
        dijit.byId("adminDeleteSubnetDialog").show();
    },
    'closeDeleteSubnetDialog': function() {
        dijit.byId("adminDeleteSubnetDialog").hide();
    },
    'delete': function() {
        var subnetId = dojo.byId("currentSubnetId").value;
        dijit.byId("adminDeleteSubnetDialog").hide();
        dijit.byId("adminDeleteSubnetLoader").show();
        var deleteNetworkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/deleteSubnet/"
        });
//        dijit.byId("networkLoader").show();
        deleteNetworkRestStore.remove(subnetId).then(function(data) {

            dijit.byId("adminDeleteSubnetLoader").hide();
            if (data === "OK") {
                registry.byId("appToaster").setContent(translator.common.message.subnetDeleted, "message");
                registry.byId("appToaster").show();
//                dijit.byId("networkLoader").hide();
                SubnetInfo.populateValues();
            } else {
                registry.byId("appToaster").setContent(data, "error");
                registry.byId("appToaster").show();
//                dijit.byId("networkLoader").hide();
                SubnetInfo.populateValues();
            }
        });
    },
};
var PortInfo = {
    'populateValues': function() {

        var networkId = dojo.byId("adminSelectedNetworkId").value;

        dojo.byId("adminNoPortMessageBox").style.display = "none";
        dojo.byId("adminPortList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.portLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true', },
                {'name': translator.common.name, 'field': 'name', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {
                        if (data.name != "") {
                            return "<a href='#/admin/network/viewPort/" + data.referenceId + "' title='" + translator.common.view + "'>" + data.name + "</a>";
                        } else {
                            return "<a href='#/admin/network/viewPort/" + data.referenceId + "' title='" + translator.common.view + "'>" + "(" + data.referenceId + ")" + "</a>";
                        }
                    }
                },
                {'name': translator.common.fixedIp, 'field': 'fixedIp', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

//                        return "<span class='bold'>" + "IP address: "+ data.ipAddress + " | " + "Subnet ID: "+ data.subnetId + "</span>";
                        return "<span class='bold'>" + data.ipAddress + "</span>";
                    }},
                {'name': translator.common.attachedDevice, 'field': 'attachedDevice', 'width': '200px', 'dataType': 'string', 'autoComplete': 'true'},
                {'name': translator.common.macAddress, 'field': 'macAddress', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.status.name, 'field': 'status', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.adminState, 'field': 'adminState', 'width': '149px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {

                        var menu = new DropDownMenu({style: "display: none;"});

                        var menuItem1 = new MenuItem({
                            label: translator.common.view,
                            onClick: function() {
                                core.router.go("#/admin/network/viewPort/" + data.referenceId + "");
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

                        var menuItem3 = new MenuItem({
                            label: translator.common.deleteData,
                            onClick: function() {
                                PortInfo.deleteFormDialog(data.referenceId);
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

        var nicRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/portList"
        });

        nicRestStore.query({networkId: networkId}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("adminPortList").innerHTML = "";
                dojo.byId("adminNoPortMessageBox").style.display = "block";

            } else {

                dojo.byId("adminNoPortMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
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
                dojo.byId("adminPortList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminPortList");
                dataGrid.startup();
                dataGrid.update();
            }
        });

    },
    'redirectToCreateForm': function() {
        var networkId = dojo.byId("adminSelectedNetworkId").value;
        core.router.go("#/admin/network/createPort/" + networkId + "");
    },
    'createFormLoad': function(id) {

        dojo.byId("adminNetworkPorthead").innerHTML = translator.common.createPort;

        var portRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/listByAdmin"
        });

        portRestStore.query({networkId: id}).then(function(data) {
            dojo.forEach(data, function(resultData) {

                dojo.byId("adminCurrentPortNetworkId").value = resultData.referenceId;

                dojo.byId("adminNetworkName").innerHTML = resultData.name;
                dijit.byId("adminPortNetworkName").setValue(resultData.name);
                dijit.byId("adminPortNetworkName").setAttribute('disabled', true);

                dijit.byId("adminPortNetworkId").setValue(resultData.referenceId);
                dijit.byId("adminPortNetworkId").setAttribute('disabled', true);

                dojo.byId("adminNetworkName").href = "#/admin/network/view/" + resultData.referenceId;
                dojo.byId("adminPortListLink").href = "#/admin/network/portList/" + resultData.referenceId;
//              dojo.byId("subnetListLinkLoader").href = "#/user/network/subnetList/"+subnetData.network.referenceId;
            });
        });
    },
    'gotoList': function() {
        var networkId = dojo.byId("adminCurrentPortNetworkId").value;
        core.router.go("#/admin/network/portList/" + networkId)
    },
    'add': function() {


        dojo.byId("adminPortAddButtonDiv").style.display = "block";
        var status = true;
        var pageNode = dojo.byId("networkPortContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function(el) {
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
                target: core.getContextPath() + "/api/network/createPortByAdmin"
            });

            var networkName = dijit.byId("adminPortNetworkName").value;
            var networkId = dijit.byId("adminPortNetworkId").value;
            var name = dijit.byId("adminPortName").value;
            var isAdminState = true;
            if (dijit.byId("portAdminState").checked == false) {
                isAdminState = false;
            }
            var deviceId = dijit.byId("adminPortDeviceId").value;
            var deviceOwner = dijit.byId("adminPortDeviceName").value;

            dijit.byId("adminCreatePortLoader").show();

            networkRestStore.add({
                networkId: networkId,
                networkName: networkName,
                name: name,
                isAdminState: isAdminState,
                deviceId: deviceId,
                deviceOwner: deviceOwner,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dijit.byId("adminCreatePortLoader").hide();
                    if (resultData.result === "OK") {
                        registry.byId("appToaster").setContent(name + " Port " + translator.common.message.addedSuccess, "message");
                        registry.byId("appToaster").show();

                        core.router.go("#/admin/network/portList/" + networkId + "");
                    } else {
                        registry.byId("appToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("appToaster").show();

                    }
                });
            });
        }
    },
    'cancel': function() {
        var networkId = dojo.byId("adminCurrentPortNetworkId").value;
        core.router.go("#/admin/network/portList/" + networkId + "");
    },
    'deleteFormDialog': function(id) {
        dojo.byId("adminCurrentPortId").value = id;
        dijit.byId("adminDeletePortDialog").show();
    },
    'closeDeleteDialog': function() {
        dijit.byId("adminDeletePortDialog").hide();
    },
    'delete': function() {
        var portId = dojo.byId("adminCurrentPortId").value;
        dijit.byId("adminDeletePortDialog").hide();
        dijit.byId("adminPortLoader").show();
        var deletePortRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/deletePortByAdmin/"
        });
//        dijit.byId("networkLoader").show();
        deletePortRestStore.remove(portId).then(function(data) {

            dijit.byId("adminPortLoader").hide();
            if (data === "OK") {
                registry.byId("appToaster").setContent(translator.common.message.portDeleted, "message");
                registry.byId("appToaster").show();
//                dijit.byId("networkLoader").hide();
                PortInfo.populateValues();
            } else {
                registry.byId("appToaster").setContent(data, "error");
                registry.byId("appToaster").show();
//                dijit.byId("networkLoader").hide();
                PortInfo.populateValues();
            }

        });
    },
    'view': function(portId) {

        var portViewRest = new JsonRest({
            target: core.getContextPath() + "/api/network/portView"
        });

        dojo.byId("viewPortFormLoader").style.display = "block";
        dojo.byId("viewPortFormLoader").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        portViewRest.query({portId: portId}).then(function(data) {
            dojo.forEach(data, function(portData) {
                dojo.byId("portNameInfo").innerHTML = portData.name == "" ? "None" : portData.name;
                dojo.byId("portIdInfo").innerHTML = portData.referenceId;
                dojo.byId("portNetworkIdInfo").innerHTML = portData.network.referenceId;

                dojo.byId("portListLink").href = "#/admin/network/portList/" + portData.network.referenceId;
                dojo.byId("networkName").innerHTML = portData.network.name;
                dojo.byId("networkName").href = "#/admin/network/view/" + portData.network.referenceId;
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
    'closeLoader': function() {
        dijit.byId("adminPortLoader").hide();
    },
    'editFormDialog': function(id) {

        dojo.byId("adminCurrentPortId").value = id;
        dijit.byId("adminPortEditFormDialog").show();
        var nicRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/portView"
        });

        nicRestStore.query({portId: id}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dijit.byId("adminEditPortName").setValue(resultData.name);

            });
        });
    },
    'closeEditPortDialog': function() {

        dijit.byId("adminPortEditFormDialog").hide();
    },
    'update': function() {

        var portId = dojo.byId("adminCurrentPortId").value;

        var status = true;
        var pageNode = dojo.byId("adminEditNetworkPortFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function(el) {
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
            dijit.byId("adminPortEditFormDialog").hide();
            dijit.byId("adminPortLoader").show();

            var portUpdateRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/updatePort/"
            });

            var portName = dijit.byId("adminEditPortName").value;

            portUpdateRestStore.put({
                id: portId,
                name: portName,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dijit.byId("adminPortLoader").hide();
                    if (resultData.result === "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("appToaster").show();
                        PortInfo.populateValues();
                    } else {
                        registry.byId("appToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("appToaster").show();
                        PortInfo.populateValues();

                    }
                });
            });
        }


    },
};


