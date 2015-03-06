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
    "dojo/NodeList-traverse",
    "dijit/form/DateTextBox",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane", 
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
    function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
    ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {             
        window.query = query;
        window.translator = translator;
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
        
        controller ({
            name: "vpc",
            module: "admin",
            filePath: "/js/app/admin/vpc.js",
            layout: {
                name: "vpc",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {                
            }),
            "list": action(function() {                
                core.ui.loadTemplate("vpcPage", core.ui.getContentId()); 
                VPCInfo.populateValues();
            }),
            "viewVPCPrivateGateway": action(function(id) {
                if (dijit.byId("vpcPrivateGatewayLoader")) {
                    dijit.byId("vpcPrivateGatewayLoader").destroyRecursive();
                }
                if (dijit.byId("staticRouteAddForm")) {
                    dijit.byId("staticRouteAddForm").destroyRecursive();
                }
                if (dijit.byId("deleteStaticRouteDialog")) {
                    dijit.byId("deleteStaticRouteDialog").destroyRecursive();
                }
                if (dijit.byId("privateGatewayDelete")) {
                    dijit.byId("privateGatewayDelete").destroyRecursive();
                }
                core.ui.loadTemplate("viewPrivateGateway", core.ui.getContentId());
                AdminVPCPrivateGateway.populateDetails(id);

            }),
            "view": action(function(id) {
                if (dijit.byId("adminVpcTabCointainer")) {
                    dijit.byId("adminVpcTabCointainer").destroyRecursive();
                }
                if (dijit.byId("adminViewIPForm")) {
                    dijit.byId("adminViewIPForm").destroyRecursive();
                }
                if (dijit.byId("adminVpcDetailContainer")) {
                    dijit.byId("adminVpcDetailContainer").destroyRecursive();
                }
                if (dijit.byId("adminVpcTierContainer")) {
                    dijit.byId("adminVpcTierContainer").destroyRecursive();
                }

                if (dijit.byId("adminvpcIpAddressTab")) {
                    dijit.byId("adminvpcIpAddressTab").destroyRecursive();
                }
                if (dijit.byId("addVPCPriateGatewayDialog")) {
                    dijit.byId("addVPCPriateGatewayDialog").destroyRecursive();
                }
                if (dijit.byId("addVPCPriateGatewayForm")) {
                    dijit.byId("addVPCPriateGatewayForm").destroyRecursive();
                }
                if (dijit.byId("vpcLoader")) {
                    dijit.byId("vpcLoader").destroyRecursive();
                }
                if (dijit.byId("privateGatewayReplaceACLDialog")) {
                    dijit.byId("privateGatewayReplaceACLDialog").destroyRecursive();
                }
                if (dijit.byId("vpcPrivateGatewayAdminTab")) {
                    dijit.byId("vpcPrivateGatewayAdminTab").destroyRecursive();
                }
                if (dijit.byId("privateGatewayDelete")) {
                    dijit.byId("privateGatewayDelete").destroyRecursive();
                }
                core.ui.loadTemplate("viewAdminVpc", core.ui.getContentId());
                ViewAdminVpc.init(id);
            })
        });
    });

var AdminVPCPrivateGateway = {
    
    'replaceACLTierShow'  : function(currentId, aclId) {
        dojo.byId("currentVPCPrivateGatewayId").value = currentId;
        dijit.byId("privateGatewayReplaceACLDialog").show();
        dijit.byId("privateGatewayReplaceAcl").set("value", aclId);
   },
   'closeReplaceAcl'  : function() {
       
        dijit.byId("privateGatewayReplaceACLDialog").hide();
   },
   'replaceAclTier': function() {
        
        var currentId = dojo.byId("currentVPCPrivateGatewayId").value;
        var privategatewayReplaceAcl = dijit.byId("privateGatewayReplaceAcl");
        
        var privateGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkAcl/change/"
        });
        
        dijit.byId("vpcLoader").show();
        dijit.byId("privateGatewayReplaceACLDialog").hide();
        
         var pageNode = dojo.byId("privateGatewayRepalceAClPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
        
            privateGatewayRestStore.add({privateGatewayId:currentId, aclId:privategatewayReplaceAcl.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var replaceAclTierJobStat = setInterval(function(){AdminVPCPrivateGateway.replaceAclTierJob(resultData.jobId, replaceAclTierJobStat);},2000);  
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("vpcLoader").hide();
                    }
                });
            });
        }
    },
    'replaceAclTierJob' : function(jobId, replaceAclTierJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job"
        });         
        jobStore.add({jobId: jobId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(replaceAclTierJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.aclChanged, "message");
                    registry.byId("appToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    AdminVPCPrivateGateway.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(replaceAclTierJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("appToaster").show();       
                    dijit.byId("vpcLoader").hide();
                } else {
                    clearInterval(replaceAclTierJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("vpcLoader").hide(); 
                } 
            });
        });
    },
    'deletePrivateGatewayShow'  : function(currentId, aclId) {
        dojo.byId("currentVPCPrivateGatewayId").value = currentId;
        dijit.byId("privateGatewayDelete").show();
    },
   'closeDeletePrivateGateway'  : function() {
       
        dijit.byId("privateGatewayDelete").hide();
   },
   'deletePrivateGateway': function() {
        
        var currentId = dojo.byId("currentVPCPrivateGatewayId").value;
                
        var privateGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/delete/"
        });
        
        dijit.byId("vpcLoader").show();
        dijit.byId("privateGatewayDelete").hide();
        
         var pageNode = dojo.byId("privateGatewayRepalceAClPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
        
            privateGatewayRestStore.add({privateGatewayId:currentId}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var deltePrivateGatewayJobStat = setInterval(function(){AdminVPCPrivateGateway.deletePrivateGatewayJob(resultData.jobId, deltePrivateGatewayJobStat);},2000);  
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("vpcLoader").hide();
                    }
                });
            });
        }
    },
    'deletePrivateGatewayJob' : function(jobId, deltePrivateGatewayJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpcPrivateGateway/delete/job"
        });         
        jobStore.add({jobId: jobId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(deltePrivateGatewayJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.privateGatewayDelete, "message");
                    registry.byId("appToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    AdminVPCPrivateGateway.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(deltePrivateGatewayJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("appToaster").show();       
                    dijit.byId("vpcLoader").hide();
                } else {
                    clearInterval(deltePrivateGatewayJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("vpcLoader").hide(); 
                } 
            });
        });
    },
    'addStaticRoute': function() {
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/staticRoute/create/"
        });
                
        var pageNode = dojo.byId("staticRouteAddFormAddPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId("vpcPrivateGatewayLoader").show();
            var cidr = dijit.byId("staticRouteCidr");
            
            networkRestStore.add({privateGatewayId:dojo.byId("currentVpcPrivateGatewayId").value, cidr:cidr.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var createStaticRouteJobStat = setInterval(function(){AdminVPCPrivateGateway.createStaticRouteJob(resultData.jobId, createStaticRouteJobStat);},2000);  
                    } else if(resultData.result === "FAILED") {
                        registry.byId("appToaster").setContent(resultData.message,"error");
                        registry.byId("appToaster").show();       
                        dijit.byId("vpcPrivateGatewayLoader").hide();
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("vpcPrivateGatewayLoader").hide();
                    }
                });
            });
        }
    },
    'createStaticRouteJob' : function(jobId, createStaticRouteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpcPrivateGateway/staticRoute/create/job/"
        });         
        jobStore.add({jobId: jobId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(createStaticRouteJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.createStaticRoute, "message");
                    registry.byId("appToaster").show();      
                    dijit.byId("vpcPrivateGatewayLoader").hide();
                    AdminVPCPrivateGateway.populateStaticRoutList();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(createStaticRouteJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("appToaster").show();       
                    dijit.byId("vpcPrivateGatewayLoader").hide();
                } else {
                    clearInterval(createStaticRouteJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("vpcPrivateGatewayLoader").hide(); 
                } 
            });
        });
    },
    
    'populateStaticRoutList' :  function () { 
                
        dijit.byId("staticRouteAddForm").reset();
        dojo.byId("staticRouteList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.cidr, 'field': 'cidr', 'width': '400px', 'datatype': 'string', 'autoComplete': 'true',},
                {'name': translator.common.state, 'field': 'state', 'width': '400px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {
                        var deleteButton = new dijit.form.Button({
                            "class": "delete_icon",
                            onClick: function() {
                                AdminVPCPrivateGateway.showDeleteStaticRoute(data.referenceId);
                            }
                        });       
                        return deleteButton;
                }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        var vpnPrivateGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/staticRoute/list/"
        });
        vpnPrivateGatewayRestStore.query({vpcPrivateGatewayId:dojo.byId("currentVpcPrivateGatewayId").value}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("staticRouteList").innerHTML = "";
                dojo.byId("noStaticRouteMessageBox").style.display = "block";
                 
            } else {
                dojo.byId("noStaticRouteMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        state: resultData.state,
                        cidr: resultData.cidr,
                        action:resultData
                    });
                });
                dojo.byId("staticRouteList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("staticRouteList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
    },
    
    'populateDetails' :  function (id) { 
                
        var vpcPrivateGatewayRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpcPrivateGateway"
        });
    
        vpcPrivateGatewayRestStore.query({referenceId:id}).then(function(data) {
            dojo.forEach(data, function(vpcData) {
                dojo.byId("currentVpcName").innerHTML = vpcData.vpc;
                dojo.byId("vpcName").innerHTML = vpcData.vpc;
                dojo.byId("privategatewayIP").innerHTML = vpcData.ip;
                dojo.byId("vpcPrivateGatewayIp").innerHTML = vpcData.ip;
                dojo.byId("gateway").innerHTML = vpcData.gateway;
                dojo.byId("vpcZone").innerHTML = vpcData.zone;
                dojo.byId("currentVpcNameLink").href = "#/admin/vpc/view/" + vpcData.vpcId;
                dojo.byId("currentVpcPrivateGatewayLink").href = "#/admin/vpc/view/" + vpcData.vpcId;
                dojo.byId("currentVpcId").value = vpcData.vpcId;
                dojo.byId("currentVpcPrivateGatewayId").value = vpcData.referenceId;
                 
                dojo.byId("netmask").innerHTML = vpcData.netmask;
                dojo.byId("state").innerHTML = vpcData.state;
                dojo.byId("vlan").innerHTML = vpcData.vlan;
                 
            });
        });
    
    },
    
    'populateValues' :  function () { 
        
        dojo.byId("vpcAdminPrivateGatewayList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true','formatter': function(data) {
                    return "<a href='#/admin/vpc/viewVPCPrivateGateway/" + data.referenceId + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                }},
                {'name': translator.common.userLabel, 'field': 'user', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.netmask, 'field': 'netmask', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vlan, 'field': 'vlan', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {
                        var vpnStatus = new FogPanel.VPNStatus({
                           onAclVPN : function () {AdminVPCPrivateGateway.replaceACLTierShow(data.referenceId, data.aclId);},                           
                           onDeleteVPN : function () {AdminVPCPrivateGateway.deletePrivateGatewayShow(data.referenceId);},
                           enableVPCPrivateGateway : true, 
                           changeAcl : translator.common.changeAcl,
                           aclStat : true,
                        });
                    return vpnStatus;
                }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        var vpnPrivateGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway"
        });
        vpnPrivateGatewayRestStore.query({vpcId:dojo.byId("currentAdminVpcId").value}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("vpcAdminPrivateGatewayList").innerHTML = "";
                dojo.byId("noAdminVpcPrivateGatewayMessage").style.display = "block";
                 
            } else {
                dojo.byId("noAdminVpcPrivateGatewayMessage").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        user: resultData.account,
                        ip: {referenceId:resultData.referenceId, ip:resultData.ip},
                        gateway: resultData.gateway,
                        vlan:resultData.vlan,
                        netmask:resultData.netmask,
                        state: resultData.state,
                        action:resultData
                    });
                });
                dojo.byId("vpcAdminPrivateGatewayList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("vpcAdminPrivateGatewayList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
    },
    'closeDeleteStaticRoute' : function() {
         dijit.byId('deleteStaticRouteDialog').hide();
    },
    'showDeleteStaticRoute' : function(id) {
        dojo.byId('currentStaticRoute').value = id;
         dijit.byId('deleteStaticRouteDialog').show();
    },
    'deleteStaticRoute': function() {  
         
        var id = dojo.byId('currentStaticRoute').value;        
        var staticRouteRest = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/staticRoute/delete/"
        });

        dijit.byId('vpcPrivateGatewayLoader').show();
        dijit.byId('deleteStaticRouteDialog').hide();
                
        staticRouteRest.add({staticRouteId: id}).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if (resultData.result == "OK") {
                    var deleteStaticRouteJobStatus = setInterval(function() {
                        AdminVPCPrivateGateway.deleteStaticRouteJob(resultData.jobId, deleteStaticRouteJobStatus);
                    }, 3000);

                } else if(resultData.result === "FAILED") {
                    registry.byId("appToaster").setContent(resultData.message, "error");
                    registry.byId("appToaster").show();
                    dijit.byId('vpcPrivateGatewayLoader').hide();
                } else {
                    registry.byId("appToaster").setContent(translator.common.firewall.cloudStackError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId('vpcPrivateGatewayLoader').hide();
                }

            });

        });
    },
    'deleteStaticRouteJob': function(jobId, deleteStaticRouteJobStatus) {
        var staticRouteRest = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/staticRoute/delete/job/"
        });

        staticRouteRest.add({jobId:jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deleteStaticRouteJobStatus);
                    AdminVPCPrivateGateway.populateStaticRoutList();
                    registry.byId("appToaster").setContent(translator.common.message.deleteStaticRoute, "message");
                    registry.byId("appToaster").show();
                    dijit.byId('vpcPrivateGatewayLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteStaticRouteJobStatus);
                    registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId('vpcPrivateGatewayLoader').hide();
                }
            });
        });
    },
    'showPrivateGatweayTab': function() {
        
        setTimeout(function () {
            var mainTab = dijit.byId("adminVpcTabCointainer"); //Tr
            var subIpTab = dijit.byId("vpcPrivateGatewayAdminTab"); //tab Id which you want to show
            mainTab.selectChild(subIpTab);   
        },800);
    },
};
    
var ViewAdminVpc = {
    init : function (id) {
        
        dojo.byId("currentAdminVpcId").value = id;
        
        var vpcListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc"
        });
    
        vpcListRestStore.query({referenceId:id}).then(function(data) {
            dojo.forEach(data, function(vpcData) {
                 dojo.byId("adminVpcName").innerHTML = vpcData.name;
                 dojo.byId("adminVpcDesc").innerHTML = vpcData.description;
                 dojo.byId("adminVpcNetworkDomain").innerHTML = vpcData.networkDomain;
                 dojo.byId("adminVpcZone").innerHTML = vpcData.zone;
                 dojo.byId("adminvpcCidr").innerHTML = vpcData.cidr;
                 dojo.byId("adminVpcState").innerHTML = vpcData.state;
                 dojo.byId("adminVpcRestartRequred").innerHTML = vpcData.vpcRestartRequred = true ? "Yes":"No";
                 dojo.byId("adminVpcReferenceId").innerHTML = vpcData.referenceId;
                 dojo.byId("currentVpcName").innerHTML = vpcData.name;     
                 dojo.byId("adminVpcUser").innerHTML = vpcData.account;
                 dojo.byId("currentAdminVpcId").value = vpcData.referenceId;
            });
        });
        var aclOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var aclFileStoreList = new ItemFileWriteStore({data: aclOptions});

        var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/"
        });
        
       var firstValue;
       aclRestStore.query({vpcId: id}).then(function(data) {
            if (!data || data.length == 0) {
                aclFileStoreList.newItem({id: "noAcl", name: translator.common.noAcl});
            } else {
//                aclFileStoreList.newItem({id: "select", name: translator.common.select});
                dojo.forEach(data, function(resultData, index) {
                    aclFileStoreList.newItem({id: resultData.referenceId, name: resultData.name});
                    if (index === 0) {
                        firstValue = resultData.referenceId;
                    }
                });
            }
            
            var aclListWidget = new FilteringSelect({
                id: "privateGatewayAcl",
                name: "privateGatewayAcl",
                sortByLabel: false,
                store: aclFileStoreList
            }).placeAt("privateGatewayAclList");
            
            
            var aclReplaceListWidgetForVPCPrivategateway = new Select({
                id: "privateGatewayReplaceAcl",
                name: "privateGatewayReplaceAcl",
                sortByLabel: false,
                store: aclFileStoreList
            }, "privateGatewayReplaceAclList");               
        });
        var physicalNetworkOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var physicalNetworkFileStoreList = new ItemFileWriteStore({data: physicalNetworkOptions});

        var physicalNetworkRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpcPrivateGateway/physicalNetwork/list/"
        });

         physicalNetworkRestStore.query({vpcId: id}).then(function(data) {
              dojo.forEach(data, function(resultData, index) {
                physicalNetworkFileStoreList.newItem({id: resultData.referenceId, name: resultData.name});
            });
         });

        var physicalNetworkWidget = new FilteringSelect({
            id: "physicalNetwork",
            name: "physicalNetwork",
            sortByLabel: false,
            store: physicalNetworkFileStoreList
        }, "physicalNetworkList"); 
    },
    showTierTab : function () {
        setTimeout(function () {
            var mainTab = dijit.byId("adminVpcTabCointainer"); //Tr
            var subIpTab = dijit.byId("adminVpcTierContainer"); //tab Id which you want to show
            mainTab.selectChild(subIpTab);   
        },800);
    },
    'showAddVPCPriateGatewayDialog' :  function () { 
        dojo.query("#vpcPrivateGwayCloudstackException").toggleClass("hide_text", true);
        dojo.byId("vpcPrivateGwayCloudstackExceptionMsg").innerHTML = "";
        dijit.byId("addVPCPriateGatewayDialog").show();
        dijit.byId("addVPCPriateGatewayForm").reset();
    },
    'closeAddVPCPriateGatewayDialog' :  function () { 
        dojo.query("#vpcPrivateGwayCloudstackException").toggleClass("hide_text", true);
        dojo.byId("vpcPrivateGwayCloudstackExceptionMsg").innerHTML = "";
        dijit.byId("addVPCPriateGatewayDialog").hide();
    },
    'addVPCPriateGateway': function() {
               
        var vpcId = dojo.byId("currentAdminVpcId").value;
        
        var pageNode = dojo.byId("addVPCPriateGatewayPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            
            var physicalNetwork = dijit.byId("physicalNetwork").value;
            var privateGatevlan = dijit.byId("privateGatevlan").value;
            var ipAddress = dijit.byId("privateGatewayIp").value;
            var netmask = dijit.byId("privateGatewayNetmask").value;
            var gateway = dijit.byId("privateGateway").value;
            var acl = dijit.byId("privateGatewayAcl").value;
            var privateGatewaySourceNat = dijit.byId("privateGatewaySourceNat").checked;
            
            
//            dijit.byId("addVPCPriateGatewayDialog").hide();
            var tierRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpcPrivateGateway"
            });
            dijit.byId("vpcLoader").show();
            
            tierRestStore.add({
                netmask: netmask, aclid:acl, physicalNetworkId: physicalNetwork, 
                gateway: gateway, vlan: privateGatevlan, 
                vpcId: dojo.byId("currentAdminVpcId").value,
                sourceNat:privateGatewaySourceNat,
                ipAddress:ipAddress
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
//                        registry.byId("appToaster").setContent(translator.common.message.createVPCGateway, "message");
//                        registry.byId("appToaster").show();      
//                        dijit.byId("vpcLoader").hide();
//                        ViewAdminVpc.populateValuesPrivateGateway();
                        var VPCPrivateGatewayJobStat = setInterval(function(){ViewAdminVpc.addVPCPriateGatewayJob(resultData.jobId, VPCPrivateGatewayJobStat);},2000);  
                        dojo.query("#vpcPrivateGwayCloudstackException").toggleClass("hide_text", true);
                        dojo.byId("vpcPrivateGwayCloudstackExceptionMsg").innerHTML = "";
                    } else if(resultData.result === "FAILED") {
                        registry.byId("appToaster").setContent(resultData.message, "error");
                        registry.byId("appToaster").show(); 
                        dijit.byId("vpcLoader").hide();
                        dojo.query("#vpcPrivateGwayCloudstackException").removeClass("hide_text", true);
                        dojo.byId("vpcPrivateGwayCloudstackExceptionMsg").innerHTML = resultData.message; 
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("vpcLoader").hide();
                        dojo.query("#vpcPrivateGwayCloudstackException").removeClass("hide_text", true);
                        dojo.byId("vpcPrivateGwayCloudstackExceptionMsg").innerHTML = resultData.message;
                    }
                });
            });
        }
    },
    'addVPCPriateGatewayJob' : function(jobId, vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpcPrivateGateway/create/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.createVPCGateway, "message");
                    registry.byId("appToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    dijit.byId("addVPCPriateGatewayDialog").hide();
                    ViewAdminVpc.populateValuesPrivateGateway();
                    dojo.query("#vpcPrivateGwayCloudstackException").toggleClass("hide_text", true);
                    dojo.byId("vpcPrivateGwayCloudstackExceptionMsg").innerHTML = "";
                } else if(resultData.jobResult === "Pending") {
                    
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("appToaster").setContent(resultData.message,"error");
                    registry.byId("appToaster").show();       
                    dijit.byId("vpcLoader").hide();
                    dojo.query("#vpcPrivateGwayCloudstackException").removeClass("hide_text", true);
                    dojo.byId("vpcPrivateGwayCloudstackExceptionMsg").innerHTML = resultData.message;
                } else {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("appToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("vpcLoader").hide(); 
                    dojo.query("#vpcPrivateGwayCloudstackException").removeClass("hide_text", true);
                    dojo.byId("vpcPrivateGwayCloudstackExceptionMsg").innerHTML = resultData.message;
                } 
            });
        });
    },
    populateValuesPrivateGateway : function () {
        AdminVPCPrivateGateway.populateValues();
    },
    populateIPListValues : function () {
        var vpcId = dojo.byId("currentAdminVpcId").value;

        dojo.byId("adminVpcIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ips, 'field': 'ip', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if (data.isSourceNat == true || data.isSourceNat == "true") {
                            return "<span>" + data.ip + " [Source NAT]</span>";
                        } else if(data.isStaticNat == true || data.isStaticNat == "true") {
                            return "<span>" + data.ip + " [Static NAT] </span>";
                        } else {
                            return "<span>" + data.ip + "</span>";
                        }


                    }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.userLabel, 'field': 'user', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},     
                {'name': translator.common.state, 'field': 'state', 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
                       
                        if (data.isSourceNat === false || data.isSourceNat === "false") {
                            if (data.isStaticNat === false || data.isStaticNat === "false") {
                                if(data.isVPCLBAdded === true || data.isVPCPFAdded === true) {
                                    html = "<a onclick='ViewVpc.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                                } else {
                                   html = "<a onclick='ViewVpc.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>" +
                                        "<a onclick='ViewVpc.enableStaticNatShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.enableStaticNat + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"; 
                                }
                                
                            } else {
                                html = "<a onclick='ViewVpc.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>" +
                                "<a onclick='ViewVpc.disableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.disableStaticNat + "'><img style='width: 17px; height: 17px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>";
                            } 
                        } 
                        return html;
                    }, 'datatype': 'string', 'autoComplete': 'true', 'hidden': 'true'}
            ]
        ];


        var networkIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/ip/list/"
        });
        networkIPAddressRestStore.query({vpcId:vpcId}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("adminVpcIpList").innerHTML = "";
                dojo.byId("adminNoIpAddressMessageBox").style.display = "block";
            } else {
                dojo.byId("adminNoIpAddressMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat},
                        zone: resultData.zone,
                        state: resultData.state,
                        user : resultData.account,
                        action: {
                            id: resultData.id, 
                            ip: resultData.ip, 
                            isSourceNat: resultData.isSourceNat, 
                            isStaticNat: resultData.isStaticNat,
                            vpnEnabled: resultData.vpnEnabled,
                            isEnabledVPN: resultData.isEnabledVPN,
                            isVPCLBAdded: resultData.isVPCLBAdded,
                            isVPCPFAdded: resultData.isVPCPFAdded,
                        }
                    });
                });
                dojo.byId("adminVpcIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminVpcIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    populateTierValues : function () {
        dojo.byId("adminTierList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        return "<a href='#/admin/tier/view/" + data.id + "' title='" + translator.common.view + "'>" + data.name + "</a>";
                    }},
                {'name': translator.common.user, 'field': 'user', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.supportedService, 'field': 'supportedService', 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'},               
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                         var vpnStatus = new FogPanel.VPNStatus({
                           onConfigVPN : function () {ViewVpc.replaceACLTierShow(data.referenceId);},
                           onRebootVPN : function () {VPCTierInfo.showRestart(data.referenceId);},
                           onEditVPN : function () {VPCTierInfo.showEdit(data);},
                           onDeleteVPN : function () {VPCTierInfo.showDelete(data.referenceId);}                           
                       });
                       return vpnStatus;
                    }, 'hidden': 'true', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];
      
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/list/tier"
        });
        networkRestStore.query({vpcId: dojo.byId("currentAdminVpcId").value}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("adminTierList").innerHTML = "";
                dojo.byId("noAdminTierMessageBox").style.display = "block";
            } else {
                dojo.byId("noAdminTierMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: {id: resultData.id, name: resultData.name},
                        user: resultData.user,
                        cidr: resultData.cidr,
                        state: resultData.state,
                        gateway: resultData.gateway,
                        type: resultData.tierType,
                        supportedService: resultData.supportedService,
//                            cost:  resultData.cost,
                        action: resultData
                    });
                });
                dojo.byId("adminTierList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminTierList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    populateSite2siteValues : function () {
        var vpcId = dojo.byId("currentAdminVpcId").value;

        dojo.byId("adminS2sGatewayList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ips, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.userLabel, 'field': 'user', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},                
                {'name': translator.common.vpcName, 'field': 'vpcName', 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                'formatter': function(data) {
                    var html = "<a onclick='SiteToSiteVPN.showDeleteS2SVPNDialog(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";

                    return html;
                }, 'hidden': 'true', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var networkIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/s2s/list/"
        });
        networkIPAddressRestStore.query({vpcId:vpcId}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("adminS2sGatewayList").innerHTML = "";
                dojo.byId("noS2SVPNMessageBox").style.display = "block";               
            } else {
                dojo.byId("noS2SVPNMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.id,
                        user : resultData.account,
                        ip:resultData.ip,
                        vpcName:resultData.vpc,
                        zone: resultData.zone,
                        action:resultData
                    });
                });
                dojo.byId("adminS2sGatewayList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminS2sGatewayList");
                dataGrid.startup();
                dataGrid.update();                
            }
        });    
    },
    populateVPNConnection : function () {
        dojo.byId("adminVpnConnectionList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ip.userLabel, 'field': 'user', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},                
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ipsecPresharedKey, 'field': 'ipsecPresharedKey', 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                'formatter': function(data) {
                    var vpnStatus = new FogPanel.VPNStatus({                           
                           onRebootVPN : function () {VPNConnection.showRestartDialog(data.referenceId);},
                           onDeleteVPN : function () {VPNConnection.showDeleteDialog(data.referenceId);},
                           enableVPNConnectionStat : true,                      
                    });
                    return vpnStatus;
                }, 'hidden': 'true', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var vpnConnectionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/VpnCustomerGateway/vpnConnection/list"
        });
        vpnConnectionRestStore.query({vpcId:dojo.byId("currentAdminVpcId").value}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("adminVpnConnectionList").innerHTML = "";
                dojo.byId("noAdminVpnConnectionMessage").style.display = "block";
                 
            } else {
                dojo.byId("noAdminVpnConnectionMessage").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: resultData.ip,
                        user: resultData.account,
                        gateway: resultData.gateway,
                        name:resultData.name,
                        ipsecPresharedKey:resultData.ipsecPresharedKey,
                        state: resultData.state,
                        action:resultData
                    });
                });
                dojo.byId("adminVpnConnectionList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminVpnConnectionList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
    }
}
var VPCInfo = {
    populateValues : function () {
        if(dijit.byId("adminVPCListGrid")) {                                    
            dijit.byId("adminVPCListGrid").destroyRecursive();                    
        }
        dojo.byId("adminVpcList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vpcGridData = {
            items: []
        }; 
        var vpcDataList = new ItemFileWriteStore({data: vpcGridData}); 
        var vpnLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'vpcName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                    var html = "<a href='#/admin/vpc/view/"+data.referenceId+"'>" + data.name + "</a>";
                    return html;
                }},
                {'name': translator.common.description, 'field': 'description', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.userLabel, 'field': 'user', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.cidr, 'field': 'cidr','width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state','width': '100%', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true', 'formatter' : function (data) {
                       var vpnStatus = new FogPanel.VPNStatus({
                           onConfigVPN : function () {core.router.go("#/admin/vpc/view/"+data.referenceId);},
                           onRebootVPN : function () {ListVpc.showRestartVpc(data.referenceId);},
                           onEditVPN : function () {ListVpc.showEdit(data);},
                           onDeleteVPN : function () {ListVpc.showDeleteVpc(data.referenceId);}                           
                       });
                       return vpnStatus;
                    }
                }                
             ]
         ];
         var vpcListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc"
        });              
        vpcListRestStore.query().then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noAdminVpcMessageBox").style.display = "block";
                 dojo.byId("adminVpcList").innerHTML = "";
             } else {
                 dojo.byId("noAdminVpcMessageBox").style.display = "none";
                 dojo.forEach(data, function(vpcData) {
                     vpcDataList.newItem({
                         id:vpcData.referenceId,
                         vpcName:{'name':vpcData.name, 'referenceId':vpcData.referenceId},
                         description: vpcData.description, 
                         zone: vpcData.zone,
                         user : vpcData.account,
                         cidr: vpcData.cidr,
                         state: vpcData.state,                     
                         action : {'referenceId':vpcData.referenceId, 'description':vpcData.description, 'name':vpcData.name}
                     });
                 });
                 dojo.byId("adminVpcList").innerHTML = "";
                 var vpcDataGrid = new EnhancedGrid({
                     id: 'adminVPCListGrid',
                    "class" : "span12",
                    store: vpcDataList,
                    structure: vpnLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                vpcDataGrid.placeAt("adminVpcList");
                vpcDataGrid.startup(); 
            }             
        }); 
    }
};

Window.VPCInfo = VPCInfo;
Window.ViewAdminVpc = ViewAdminVpc;
Window.AdminVPCPrivateGateway = AdminVPCPrivateGateway;
