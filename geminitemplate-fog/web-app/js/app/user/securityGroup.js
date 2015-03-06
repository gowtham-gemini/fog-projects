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
                name: "securityGroup",
                module: "user",
                filePath: "/js/app/user/securityGroup.js",
                layout: {
                    name: "securityGroup",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "list": action(function() {
                    
                    if (dijit.byId("securityGroupListForm")) {
                        dijit.byId("securityGroupListForm").destroyRecursive();
                    }

                    if (dijit.byId("deleteSecurityGroupDialog")) {
                        dijit.byId("deleteSecurityGroupDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("securityGroupListLoader")) {
                        dijit.byId("securityGroupListLoader").destroyRecursive();
                    }

                    core.ui.loadTemplate("securityGroupList", core.ui.getContentId());
                    SecurityGroup.list();
                }),
                "add": action(function() {

                    if (dijit.byId("securityGroupAddForm")) {
                        dijit.byId("securityGroupAddForm").destroyRecursive();
                    }
                    if (dijit.byId("securityGroupLoader")) {
                        dijit.byId("securityGroupLoader").destroyRecursive();
                    }
                    if (dijit.byId("securityGroupUpdateLoader")) {
                        dijit.byId("securityGroupUpdateLoader").destroyRecursive();
                    }

                    core.ui.loadTemplate("securityGroupForm", core.ui.getContentId());
                    SecurityGroup.init();

                }),
                "edit": action(function(id) {
                    if (dijit.byId("securityGroupAddForm")) {
                        dijit.byId("securityGroupAddForm").destroyRecursive();
                    }
                    if (dijit.byId("securityGroupLoader")) {
                        dijit.byId("securityGroupLoader").destroyRecursive();
                    }
                    if (dijit.byId("securityGroupUpdateLoader")) {
                        dijit.byId("securityGroupUpdateLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("securityGroupForm", core.ui.getContentId());
                    SecurityGroup.init();
                    SecurityGroup.edit(id);
                }),
                "rulesList": action(function(id) {                    
                    if (dijit.byId("securityGroupRulesListForm")) {
                        dijit.byId("securityGroupRulesListForm").destroyRecursive();
                    }

                    if (dijit.byId("deleteSecurityGroupRuleDialog")) {
                        dijit.byId("deleteSecurityGroupRuleDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("securityGroupRuleListLoader")) {
                        dijit.byId("securityGroupRuleListLoader").destroyRecursive();
                    }
                    
                    if (dijit.byId("securityGroupRuleAddForm")) {
                        dijit.byId("securityGroupRuleAddForm").destroyRecursive();
                    }
                    if (dijit.byId("securityGroupRuleLoader")) {
                        dijit.byId("securityGroupRuleLoader").destroyRecursive();
                    }                    
                    if(id) {
                        core.ui.loadTemplate("rulesList", core.ui.getContentId());
                        SecurityGroupRule.populateSecurityGroupList(id);
                        SecurityGroupRule.list(id);                    
                    } else {                        
                        core.router.go("#/user/securityGroup/list");
                    }
                }),
                "addRule": action(function(id) {

                    if (dijit.byId("securityGroupRuleAddForm")) {
                        dijit.byId("securityGroupRuleAddForm").destroyRecursive();
                    }
                    if (dijit.byId("securityGroupRuleLoader")) {
                        dijit.byId("securityGroupRuleLoader").destroyRecursive();
                    }
                    
                    if(id) {                        
                        core.ui.loadTemplate("securityGroupRuleForm", core.ui.getContentId());
                        SecurityGroupRule.populateSecurityGroupList(id);
                    } else {                        
                        core.router.go("#/user/securityGroup/list")
                    }

                }),
            });
        });

var SecurityGroup = {
    init: function() {        
        dojo.byId("securityGroupPage").innerHTML = translator.common.head.createSecurityGroup;
        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("securityGroupCurrencyValue").innerHTML = cur.currency;                
            });
        });
    },
    list: function() {
        dojo.byId("userSecurityGroupList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.securityGroupLoader + "</p>";

        var gridData = {
            items: []
        };
        
        dojo.byId("nosecurityGroupMessageBox").style.display = "none";

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': translator.common.idCaps, 'field': 'id', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.description, 'field': 'discription', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        
//                        var btn1 = "<a class='btn-flat' href='#/user/securityGroup/rulesList/" + data.referenceId + "'>" + translator.common.manageRules + "</a>";
                        
                        var menu = new DropDownMenu({ style: "display: none;"});
                        
                            var menuItem1 = new MenuItem({
                                label: translator.common.manageRules,
                                onClick: function(){ SecurityGroup.manageRules(data.referenceId); }
                            });
                            menu.addChild(menuItem1);
                            menu.startup();
                        
                            var menuItem2 = new MenuItem({
                                label: translator.common.edit,
                                onClick: function(){ SecurityGroup.editGroup(data.referenceId); }
                            });
                            menu.addChild(menuItem2);
                            menu.startup();
                            
                            var menuItem3 = new MenuItem({
                                label: translator.common.deleteData,
                                onClick: function(){ SecurityGroup.deleteDialogAlert(data.referenceId); }
                            });
                            menu.addChild(menuItem3);
                            menu.startup();

                           var btn = new ComboButton({
                                label: "More",
                                name: "More",
                                dropDown: menu
                            });
                            
                        return btn;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var securityGroupsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/securityGroup/"
        });

        securityGroupsRestStore.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userSecurityGroupList").innerHTML = "";
                dojo.byId("nosecurityGroupMessageBox").style.display = "block";

            } else {

                dojo.byId("nosecurityGroupMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: resultData.name,
                        discription: resultData.description,
                        action: resultData
                    });
                });
                dojo.byId("userSecurityGroupList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userSecurityGroupList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    manageRules: function(id) {
        core.router.go("#/user/securityGroup/rulesList/" + id + "");
    },
    editGroup: function(id) {
        core.router.go("#/user/securityGroup/edit/" + id + "");
    },
    add: function() {
        dojo.byId("securityGroupAddButtonDiv").style.display = "block";

        var pageNode = dojo.byId("securityGroupContent");
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
                target: core.getContextPath() + "/api/securityGroup"
            });

            var name = dijit.byId("securityGroupName").value;
            var description = dijit.byId("securityGroupDescription").value;
            
            dijit.byId("securityGroupLoader").show();

            networkRestStore.add({
                name: name,
                description: description

            }).then(function(data) {
                if (data[0].result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.message.addedSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId("securityGroupLoader").hide();
                    core.router.go("#/user/securityGroup/list");

                } else if (data[0].result === "Exists") {
                    registry.byId('userToaster').setContent(translator.common.message.securityGroupNameExists, 'error');
                    registry.byId('userToaster').show();
                    dijit.byId("securityGroupLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cannotBeAdded, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("securityGroupLoader").hide();

                }
            });
        }
    },
    cancel: function() {
        core.router.go("#/user/securityGroup/list");
    },
    gotoList: function() {
        
        if(dijit.byId("securityGroupLoader")) {
          dijit.byId("securityGroupLoader").hide();  
        }
        if(dijit.byId("securityGroupUpdateLoader")) {
          dijit.byId("securityGroupUpdateLoader").hide();  
        }
        if(dijit.byId("securityGroupListLoader")) {
          dijit.byId("securityGroupListLoader").hide();  
        }
        core.router.go("#/user/securityGroup/list");
    },
    edit: function(id) {
        var securityGroupEditRest = new JsonRest({
            target: core.getContextPath() + "/api/securityGroup/"
        });
        dojo.byId("currentSecurityGroupId").value = id;
        dojo.byId("securityGroupEdit").innerHTML = translator.common.edit;
        securityGroupEditRest.query({referenceId: id}).then(function(resultData) {
            dojo.forEach(resultData, function(data) {
                dojo.byId("securityGroupPage").innerHTML = translator.common.head.editSecurityGroup + " - " + data.name;
                dijit.byId("securityGroupName").setValue(data.name);
                dijit.byId("securityGroupDescription").setValue(data.description);
            });
        });
        dojo.byId("securityGroupAddButtonDiv").style.display = "none";
        dojo.byId("securityGroupUpdateButtonDiv").style.display = "block";

    },
    'update': function() {
        var id = dojo.byId("currentSecurityGroupId").value;
        var name = dijit.byId("securityGroupName").value;
        var description = dijit.byId("securityGroupDescription").value;
        
        var pageNode = dojo.byId("securityGroupContent");
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
            var updateSecurityGroupRestStore = new JsonRest({
                target: core.getContextPath() + "/api/securityGroup/update"
            });
            
            dijit.byId("securityGroupUpdateLoader").show();
            
            updateSecurityGroupRestStore.put({
                referenceId: id,
                name: name,
                description: description
                
            }).then(function(resultData) {
                dojo.forEach(resultData, function(data) {
                    if (data.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.updatedSuccess, 'message');
                        registry.byId("userToaster").show();
                        dijit.byId("securityGroupUpdateLoader").hide();
                        core.router.go("#/user/securityGroup/list");
                    } else if (data.result === "Exists") {
                        registry.byId('userToaster').setContent(translator.common.message.securityGroupNameExists, 'error');
                        registry.byId('userToaster').show();
                        dijit.byId("securityGroupUpdateLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(data.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("securityGroupUpdateLoader").hide();
                    }
                });
            });
        }
    },
    deleteDialogAlert: function(currentId) {
        dijit.byId("deleteSecurityGroupDialog").show();
        dojo.byId("SecurityGroupId").value = currentId;
    },
    confirmDelete: function() {
        var currentId = dojo.byId("SecurityGroupId").value;
        dijit.byId("deleteSecurityGroupDialog").hide();
        dijit.byId("securityGroupListLoader").show();
        var deleteRestStore = new JsonRest({
            target: core.getContextPath() + "/api/securityGroup/"
        });

        deleteRestStore.remove(currentId).then(function(data) {
            if (data == "[\"OK\"]") {

                dijit.byId("securityGroupListLoader").hide();
                registry.byId("userToaster").setContent(translator.common.message.deletedSuccess, "message");
                registry.byId("userToaster").show();
                SecurityGroup.list();

                } else {

                dijit.byId("securityGroupListLoader").hide();
                registry.byId("userToaster").setContent(translator.common.message.cannotDelete, "error");
                registry.byId("userToaster").show();
                }

        });

    },
    closeDeleteDialog: function() {
        dijit.byId("deleteSecurityGroupDialog").hide();
    },
};

var SecurityGroupRule = {
    init: function(id) {

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("ruleCurrencyValue").innerHTML = cur.currency;
            });
        });
        
        dojo.byId("securityGroupRuleCreateLink").href = "#/user/securityGroup/addRule/"+id;
        
    },
    list: function(id) {
        dojo.byId("userSecurityGroupRulesList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.securityGroupRuleLoader + "</p>";

        dojo.byId("seletedSecurityGroupId").value = id;
        
        dojo.byId("nosecurityGroupRulesMessageBox").style.display = "none";
        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': translator.user.grid.securityGroupRule.layout.direction, 'field': 'direction', 'width': '180px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.user.grid.securityGroupRule.layout.etherType, 'field': 'etherType', 'width': '180px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.user.grid.securityGroupRule.layout.ipProtocol, 'field': 'ipProtocol', 'width': '180px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.user.grid.securityGroupRule.layout.portRange, 'field': 'portRange', 'width': '180px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.user.grid.securityGroupRule.layout.remote, 'field': 'remote', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var btn = "<a class='btn-flat danger spacing' onclick='SecurityGroupRule.deleteDialogAlert(\"" + data.referenceId + "\")'>" + translator.common.deleteData + "</a>";
                        return btn;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var securityGroupRulesRestStore = new JsonRest({
            target: core.getContextPath() + "/api/securityGroup/rule"
        });

        securityGroupRulesRestStore.query({referenceId: id}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userSecurityGroupRulesList").innerHTML = "";
                dojo.byId("nosecurityGroupRulesMessageBox").style.display = "block";

            } else {

                dojo.byId("nosecurityGroupRulesMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        direction: resultData.direction,
                        etherType: resultData.etherType,
                        ipProtocol: resultData.ipProtocol == null ? "Any" : resultData.ipProtocol,
                        portRange: resultData.portRange == 0 ? "-" : resultData.portRange,
                        remote: resultData.remote,
                        action: resultData
                    });
                });
                dojo.byId("userSecurityGroupRulesList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userSecurityGroupRulesList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    showRuleFilteringSelect: function(selectWidget) {
        
        if(selectWidget.value == "dns" || selectWidget.value == "http" || selectWidget.value == "https" || selectWidget.value == "imap"
                || selectWidget.value == "imaps" || selectWidget.value == "ldap" || selectWidget.value == "ms_sql"
                || selectWidget.value == "mysql" || selectWidget.value == "pop3" || selectWidget.value == "pop3s"
                || selectWidget.value == "rdp" || selectWidget.value == "smtp" || selectWidget.value == "smtps" 
                || selectWidget.value == "ssh") {

            dojo.byId("directionDiv").style.display = "none";
            dojo.byId("ipProtocolDiv").style.display = "none";
            dojo.byId("openPortDiv").style.display = "none";
            dojo.byId("portDiv").style.display = "none";
            dojo.byId("fromPortDiv").style.display = "none";
            dojo.byId("toPortDiv").style.display = "none";
            dojo.byId("icmpTypeDiv").style.display = "none";
            dojo.byId("icmpCodeDiv").style.display = "none";
            SecurityGroupRule.resetAllValues();
            dijit.byId("ipProtocol").set("required", false);
            dijit.byId("icmpType").set("required", false);
            dijit.byId("icmpCode").set("required", false);
            dijit.byId("portRange").set("required", false);
            dijit.byId("fromPort").set("required", false);
            dijit.byId("toPort").set("required", false);
            
        } if(selectWidget.value == "icmp") {
            
            dojo.byId("directionDiv").style.display = "block";
            dojo.byId("ipProtocolDiv").style.display = "none";
            dojo.byId("icmpTypeDiv").style.display = "block";
            dojo.byId("icmpCodeDiv").style.display = "block";
            dojo.byId("openPortDiv").style.display = "none";
            dojo.byId("portDiv").style.display = "none";
            dojo.byId("fromPortDiv").style.display = "none";
            dojo.byId("toPortDiv").style.display = "none";
            SecurityGroupRule.resetAllValues();
            dijit.byId("icmpType").set("required", true);
            dijit.byId("icmpCode").set("required", true);
            dijit.byId("ipProtocol").set("required", false);
            dijit.byId("portRange").set("required", false);
            dijit.byId("fromPort").set("required", false);
            dijit.byId("toPort").set("required", false);
        
        } else if(selectWidget.value == "custom") {
            
            dojo.byId("directionDiv").style.display = "block";
            dojo.byId("ipProtocolDiv").style.display = "block";
            dojo.byId("openPortDiv").style.display = "none";
            dojo.byId("icmpTypeDiv").style.display = "none";
            dojo.byId("icmpCodeDiv").style.display = "none";
            dojo.byId("portDiv").style.display = "none";
            dojo.byId("fromPortDiv").style.display = "none";
            dojo.byId("toPortDiv").style.display = "none";
            SecurityGroupRule.resetAllValues();
            dijit.byId("ipProtocol").set("required", true);
            dijit.byId("icmpType").set("required", false);
            dijit.byId("icmpCode").set("required", false);
            dijit.byId("portRange").set("required", false);
            dijit.byId("fromPort").set("required", false);
            dijit.byId("toPort").set("required", false);
            
        } else if(selectWidget.value == "all_icmp" || selectWidget.value == "all_tcp" || selectWidget.value == "all_udp") {
            
            dojo.byId("directionDiv").style.display = "block";
            dojo.byId("ipProtocolDiv").style.display = "none";
            dojo.byId("openPortDiv").style.display = "none";
            dojo.byId("icmpTypeDiv").style.display = "none";
            dojo.byId("icmpCodeDiv").style.display = "none";
            dojo.byId("portDiv").style.display = "none";
            dojo.byId("fromPortDiv").style.display = "none";
            dojo.byId("toPortDiv").style.display = "none";
            SecurityGroupRule.resetAllValues();
            dijit.byId("ipProtocol").set("required", false);
            dijit.byId("icmpType").set("required", false);
            dijit.byId("icmpCode").set("required", false);
            dijit.byId("portRange").set("required", false);
            dijit.byId("fromPort").set("required", false);
            dijit.byId("toPort").set("required", false);
            
        } else if(selectWidget.value == "tcp" || selectWidget.value == "udp"){

            dojo.byId("directionDiv").style.display = "block";
            dojo.byId("ipProtocolDiv").style.display = "none";
            dojo.byId("openPortDiv").style.display = "block";
            dijit.byId("openPort").setValue("port");
            dojo.byId("fromPortDiv").style.display = "none";
            dojo.byId("toPortDiv").style.display = "none";
            dojo.byId("portDiv").style.display = "block";
            dojo.byId("icmpTypeDiv").style.display = "none";
            dojo.byId("icmpCodeDiv").style.display = "none";
            SecurityGroupRule.resetAllValues();
            dijit.byId("portRange").set("required", true);
            dijit.byId("ipProtocol").set("required", false);
            dijit.byId("icmpType").set("required", false);
            dijit.byId("icmpCode").set("required", false);

        }
    },
    showOpenPortSelect: function(selectWidget) {
        if(selectWidget.value == "range") {
            dojo.byId("portDiv").style.display = "none";
            dojo.byId("fromPortDiv").style.display = "block";
            dojo.byId("toPortDiv").style.display = "block";
            dijit.byId("fromPort").set("required", true);
            dijit.byId("toPort").set("required", true);
            SecurityGroupRule.resetAllValues();
            dijit.byId("portRange").set("required", false);

        } else if(selectWidget.value == "port"){
            dojo.byId("portDiv").style.display = "block";
            dojo.byId("fromPortDiv").style.display = "none";
            dojo.byId("toPortDiv").style.display = "none";
            SecurityGroupRule.resetAllValues(); 
            dijit.byId("fromPort").set("required", false);
            dijit.byId("toPort").set("required", false);
            dijit.byId("portRange").set("required", true);

        }
    },
    showRemoteSelect: function(selectWidget) {
        if(selectWidget.value == "sg") {
            dojo.byId("cidrDiv").style.display = "none";
            dojo.byId("securityGroupsList").style.display = "block";
            dojo.byId("etherTypeDiv").style.display = "block";
            dijit.byId("securityGroupListWidget").reset();
            dijit.byId("cidr").reset();
            dijit.byId("ipVersion").reset();
            dijit.byId("securityGroupListWidget").set("required", true);
            dojo.query("#ipProtocolDiv").removeClass("no-left-space", true); 
        } else {
            dojo.query("#ipProtocolDiv").toggleClass("no-left-space", true); 
            dojo.byId("cidrDiv").style.display = "block";
            dojo.byId("securityGroupsList").style.display = "none";
            dojo.byId("etherTypeDiv").style.display = "none";
            dijit.byId("securityGroupListWidget").reset();
            dijit.byId("cidr").reset();
            dijit.byId("ipVersion").reset();
            dijit.byId("securityGroupListWidget").set("required", false);
        }
    },
    resetAllValues: function() {
        
        dijit.byId("portRange").reset();
        dijit.byId("fromPort").reset();
        dijit.byId("toPort").reset();
        dijit.byId("ipProtocol").reset();
        dijit.byId("icmpType").reset();
        dijit.byId("icmpCode").reset();

    },
    populateSecurityGroupList: function(id) {
        
        dojo.byId("securityGrouprulePage").innerHTML = translator.common.head.createSecurityGroupRule;
        
        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                if(dojo.byId("addRuleCurrencyValue")) {
                    dojo.byId("addRuleCurrencyValue").innerHTML = cur.currency;
                }
            });
        });
        
        dijit.byId("portRange").set("required", true);
        
        dojo.byId("ruleCurrentId").value = id;
        
        if(dijit.byId("securityGroupListWidget")) {               
            dijit.byId("securityGroupListWidget").destroyRecursive();          
        }
        
         var securityGroupRestStore = new JsonRest({
            target: core.getContextPath() + "/api/securityGroup/"
        });
        var securityGroupRulesOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var securityGroupList = new ItemFileWriteStore({data: securityGroupRulesOptions});
        securityGroupRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
               securityGroupList.newItem({id: el.referenceId, name: el.name});
            });
            var securityGroupWidget = new FilteringSelect({
                store: securityGroupList,
                id:"securityGroupListWidget",
                required: false,
                onChange: function() {  
                }
            });
            securityGroupWidget.placeAt("securityGroupsLists");
            securityGroupWidget.startup();

        });
    },
    createRule: function() {
        
        var securityGroupId = dojo.byId("seletedSecurityGroupId").value;
                
        core.router.go("#/user/securityGroup/addRule/" + securityGroupId + "");
        
    },
    add: function() {
        
        var securityGroupIdForRule = dojo.byId("ruleCurrentId").value;
                
        dojo.byId("securityGroupRuleAddButton").style.display = "block";
        var status = true;
        var pageNode = dojo.byId("securityGroupRuleContent");
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
            firstNode.focus();console.log("required");
        } else {

            var securityGroupRuleStore = new JsonRest({
                target: core.getContextPath() + "/api/securityGroup/rule"
            });

            var rule = dijit.byId("rule").value;
            var direction = dijit.byId("direction").value;
            var openPort = dijit.byId("openPort").value;
            var port = dijit.byId("portRange").value;
            var remote = dijit.byId("remote").value;
            var cidr = dijit.byId("cidr").value;
            var ipProtocol = dijit.byId("ipProtocol").value;
            var icmpType = dijit.byId("icmpType").value;
            var icmpCode = dijit.byId("icmpCode").value;
            var fromPort = dijit.byId("fromPort").value;
            var toPort = dijit.byId("toPort").value;
            var securityGroup = dijit.byId("securityGroupListWidget").value;
            var ipVersion = dijit.byId("ipVersion").value;
            
            dijit.byId("securityGroupRuleLoader").show();

            securityGroupRuleStore.add({
                securityGroupId: securityGroupIdForRule,
                rule: rule,
                direction: direction,
                openPort: openPort,
                port: port,
                remote: remote,
                cidr: cidr,
                ipProtocol: ipProtocol,
                icmpType: icmpType,
                icmpCode: icmpCode,
                fromPort: fromPort,
                toPort: toPort,
                securityGroup: securityGroup,
                ipVersion: ipVersion,
                
            }).then(function(data) {

                if (data[0].result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.message.addedSuccess, "message");
                    registry.byId("userToaster").show();
//                    core.router.go("#/user/securityGroup/rulesList/" + securityGroupIdForRule + "");
                    dijit.byId("securityGroupRuleLoader").hide();
                    SecurityGroupRule.list(securityGroupIdForRule);
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cannotBeAdded, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("securityGroupRuleLoader").hide();
                }
            });
        }
    },
    cancel: function() {
        dijit.byId("securityGroupRuleAddForm").reset();
//        core.router.go("#/user/securityGroup/rulesList");
//        var securityGroupId = dojo.byId("ruleCurrentId").value;
//                
//        core.router.go("#/user/securityGroup/rulesList/" + securityGroupId + "");
    },
    deleteDialogAlert: function(currentId) {
        dijit.byId("deleteSecurityGroupRuleDialog").show();
        dojo.byId("SecurityGroupRuleId").value = currentId;
    },
    confirmDelete: function() {
        var currentId = dojo.byId("SecurityGroupRuleId").value;
        dijit.byId("deleteSecurityGroupRuleDialog").hide();
        dijit.byId("securityGroupRuleListLoader").show();
        var deleteRestStore = new JsonRest({
            target: core.getContextPath() + "/api/securityGroup/rule/"
        });
        
        var securityGroupId = dojo.byId("seletedSecurityGroupId").value;

        deleteRestStore.remove(currentId).then(function(data) {

            if (data == "[\"OK\"]") {

                dijit.byId("securityGroupRuleListLoader").hide();
                registry.byId("userToaster").setContent(translator.common.message.deletedSuccess, "message");
                registry.byId("userToaster").show();
                SecurityGroupRule.list(securityGroupId);


            } else {

                dijit.byId("securityGroupRuleListLoader").hide();
                registry.byId("userToaster").setContent(translator.common.message.cannotDelete, "error");
                registry.byId("userToaster").show();
            }

        });

    },
    closeDeleteDialog: function() {
        dijit.byId("deleteSecurityGroupRuleDialog").hide();
    },
    gotoListFromCreatePage: function() {

        var id = dojo.byId("ruleCurrentId").value ;
        dijit.byId("securityGroupRuleLoader").hide(); 
        core.router.go("#/user/securityGroup/rulesList/" +id);

    },
    gotoListFromdelete: function() {
        dijit.byId("securityGroupRuleListLoader").hide(); 
    },
    'refresh': function() {
        var securityGroupId = dojo.byId("seletedSecurityGroupId").value ; 
        SecurityGroupRule.list(securityGroupId);
    },
};





