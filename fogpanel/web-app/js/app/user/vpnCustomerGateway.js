"use strict";
require([
    "dojo", 
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js", 
    "dijit/dijit",
    "dojo/dom-class",
    "FogPanel/VPNStatus",
    "dojo/dom-construct",
    "dojo/store/JsonRest",
    "dojo/query",
    "dijit/registry",    
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dojox/calendar/Calendar",
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
    "dijit/Dialog"
],
function(dojo, i18n, translator, dijit, VPNStatus, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
ItemFileWriteStore, Memory, Observable, DataGrid, EnhancedGrid, Calendar, ContentPane, Source, MultiSelect, dom, win) {             
    window.query = query;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.VPNStatus = VPNStatus;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Observable = Observable;
    window.Memory = Memory;
    window.Select = Select;
    window.ContentPane = ContentPane;
    window.DataGrid = DataGrid;
    window.EnhancedGrid = EnhancedGrid;
    window.Calendar = Calendar;
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;        
    controller ({
        name: "vpnCustomerGateway",
        module: "user",
        filePath: "/js/app/user/vpnCustomerGateway.js",
        layout: {
            name: "vpnCustomerGateway",
            containerId: "content"
        },	
        scaffold: false
    },
    {
        "index": action(function() {
            core.ui.loadTemplate("vpnCustomerGatewayIndex", core.ui.getContentId());
            VPNCutomerGatewayList.populateCounts();
            
        }),
        "list" : action(function () {
            if (dijit.byId("vpnCustomerGatewayLoader")) {
                dijit.byId("vpnCustomerGatewayLoader").destroyRecursive();
            }
            if (dijit.byId("vpnCustomerGatewayDeleteDialog")) {
                dijit.byId("vpnCustomerGatewayDeleteDialog").destroyRecursive();
            }
            core.ui.loadTemplate("vpnCustomerGatewayList", core.ui.getContentId());   
            VPNCutomerGatewayList.init();
            VPNCutomerGatewayList.populateValues();
        }),
        "add": action(function() {
            
            if (dijit.byId("vpnCutomerGatewayAddForm")) {
                dijit.byId("vpnCutomerGatewayAddForm").destroyRecursive();
            }
            if (dijit.byId("vpnCustomerGatewayLoader")) {
                dijit.byId("vpnCustomerGatewayLoader").destroyRecursive();
            }
            if (dijit.byId("vpnCustomerGatewayDeleteDialog")) {
                dijit.byId("vpnCustomerGatewayDeleteDialog").destroyRecursive();
            }
            core.ui.loadTemplate("vpnCustomerGatewayAdd", core.ui.getContentId());      
            VPNCutomerGatewayList.populateAddValues();           
        }),
        "vpnConnection" : action(function () {                
            if (dijit.byId("generalVpnConnectionAddDialog")) {
                dijit.byId("generalVpnConnectionAddDialog").destroyRecursive();
            }
            core.ui.loadTemplate("vpnConnectionListPage", core.ui.getContentId());    
            GeneralVPNConnectionInfo.populateValues();
        })        
    });
});

var GeneralVPNConnectionInfo = {
    addVPNConnection : function () {
        var zoneWidget = dijit.byId("generalVPNConnectionZoneWidget");
        var vpcWidget = dijit.byId("generalVPNConnectionVPCWidget");        
        if(zoneWidget.value === "option" || vpcWidget === "option" || zoneWidget.value === "" || vpcWidget === "") {            
            dojo.query("#vpnConnectionConditionExceptionMsg").removeClass("hide_text", true); 
        } else {      
            dijit.byId("generalVpnConnectionAddDialog").hide();
            dojo.query("#vpnConnectionConditionExceptionMsg").toggleClass("hide_text", true); 
            core.router.go("#/user/vpc/vpcConnection/" + vpcWidget.value);
        }
    },
    cancelVPNConnection : function () {
        dijit.byId("generalVpnConnectionAddDialog").hide();
    },
    showCreateVPNConnectionDialog : function () {        
        GeneralVPNConnectionInfo.loadCustomerGateway();
        dijit.byId("generalVpnConnectionAddDialog").show();
    },
    loadCustomerGateway : function () {
        dojo.query("#vpnConnectionConditionExceptionMsg").toggleClass("hide_text", true);
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOption});
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectVPC}]
        };
        var vpcList = new ItemFileWriteStore({data: vpcOption});        
        dojo.query("#generalVPNConncetionZoneList").toggleClass("hide_text", true);
        dojo.query("#generalVPNConncetionZoneLoader").removeClass("hide_text", true);
        
        dojo.query("#generalVPNConncetionVPCList").toggleClass("hide_text", true);
        dojo.query("#generalVPNConncetionVPCLoader").removeClass("hide_text", true);
        
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var currentVPC = dojo.byId("selectedANVPCID").value;
        var zoneRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/zone/"
        });
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc"
        });           
        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                              
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                zoneRestResource.query().then(function (data) {
                    dojo.forEach(data, function (el) {
                        if(el.networkType === "Advanced") {
                            zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
                        }                    
                    });
                    dijit.byId("generalVPNConnectionZoneWidget").set("store",zoneList);
                    dijit.byId("generalVPNConnectionZoneWidget").set("value","option");
                    
                    dijit.byId("generalVPNConnectionZoneWidget").onChange = function () {
                        GeneralVPNConnectionInfo.loadZoneData(dijit.byId("generalVPNConnectionZoneWidget"));
                    };   
                    dojo.query("#generalVPNConncetionZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVPNConncetionZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVPNConncetionVPCList").removeClass("hide_text", true);
                    dojo.query("#generalVPNConncetionVPCLoader").toggleClass("hide_text", true);
                });
            } else {                    
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone});
                        dijit.byId("generalVPNConnectionZoneWidget").onChange = function () {};
                        dijit.byId("generalVPNConnectionZoneWidget").set("store", zoneList);
                        dijit.byId("generalVPNConnectionZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalVPNConncetionZoneList").removeClass("hide_text", true);
                        dojo.query("#generalVPNConncetionZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;                                                                                                                                     
                    });
                dijit.byId("generalVPNConnectionVPCWidget").set("store", singleVPCList);
                dijit.byId("generalVPNConnectionVPCWidget").set("value", firstVPC);   
                dojo.query("#generalVPNConncetionVPCList").removeClass("hide_text", true);
                dojo.query("#generalVPNConncetionVPCLoader").toggleClass("hide_text", true);
            });                               
        }                                                
        } else {                                                
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                var singleZoneOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleZoneList = new ItemFileWriteStore({data: singleZoneOption});
                zoneRestResource.get(currentZoneID).then(function (data) {
                    if(data.networkType === "Advanced") {
                        singleZoneList.newItem({id: data.referenceZoneId, name: data.aliasName});
                    } 
                    dijit.byId("generalVPNConnectionZoneWidget").set("store", singleZoneList);
                    dijit.byId("generalVPNConnectionZoneWidget").set("value", data.referenceZoneId);                    
                    dijit.byId("generalVPNConnectionZoneWidget").onChange = function () {
                        GeneralVPNConnectionInfo.loadZoneData(dijit.byId("generalVPNConnectionZoneWidget"));
                    }; 
                    dojo.query("#generalVPNConncetionZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVPNConncetionZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVPNConncetionVPCList").removeClass("hide_text", true);
                    dojo.query("#generalVPNConncetionVPCLoader").toggleClass("hide_text", true);                    
                    
                });
            } else {            
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone});
                        dijit.byId("generalVPNConnectionZoneWidget").onChange = function () {};
                        dijit.byId("generalVPNConnectionZoneWidget").set("store", zoneList);
                        dijit.byId("generalVPNConnectionZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalVPNConncetionZoneList").removeClass("hide_text", true);
                        dojo.query("#generalVPNConncetionZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;                                                           
                                                                          
                });
                dijit.byId("generalVPNConnectionVPCWidget").set("store", singleVPCList);
                dijit.byId("generalVPNConnectionVPCWidget").set("value", firstVPC);   
                dojo.query("#generalVPNConncetionVPCList").removeClass("hide_text", true);
                dojo.query("#generalVPNConncetionVPCLoader").toggleClass("hide_text", true);
            });
        } 
    }
        
    },
    loadZoneData : function (currentZone) {        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.query("#generalVPNConncetionVPCList").toggleClass("hide_text", true);
        dojo.query("#generalVPNConncetionVPCLoader").removeClass("hide_text", true);
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        if(currentZone.value === "option") {
            vpcList.newItem({id: "option", name: translator.common.selectVPC});
            dijit.byId("generalVPNConnectionVPCWidget").set("store", vpcList);
            dijit.byId("generalVPNConnectionVPCWidget").set("value", "option");
            
            dojo.query("#generalVPNConncetionVPCList").removeClass("hide_text", true);
            dojo.query("#generalVPNConncetionVPCLoader").toggleClass("hide_text", true);
            
        } else {
            var vpcOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc"
            });
            
            vpcOfferRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                var firstVPC = ""
                dojo.forEach(data, function (el, index) {
                    vpcList.newItem({id: el.referenceId, name: el.name});
                    if(index === 0) {
                        firstVPC = el.referenceId;
                    }
                });                
                dijit.byId("generalVPNConnectionVPCWidget").set("store", vpcList);
                dijit.byId("generalVPNConnectionVPCWidget").set("value", firstVPC);    
                
                dojo.query("#generalVPNConncetionVPCList").removeClass("hide_text", true);
                dojo.query("#generalVPNConncetionVPCLoader").toggleClass("hide_text", true);
            });            
        } 
        
    },
    populateValues : function () {        
        if(dijit.byId("generalVPNConnectionZoneWidget")) {                                    
            dijit.byId("generalVPNConnectionZoneWidget").destroyRecursive();                    
        }
        if(dijit.byId("generalVPNConnectionVPCWidget")) {                                    
            dijit.byId("generalVPNConnectionVPCWidget").destroyRecursive();                    
        }
        
        dojo.byId("vpnGeneralConnectionList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };
        
        var vpcRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc"
        });
        
        vpcRestStore.query().then(function (data) {
           if(data.length === 0) {
               dojo.query("#vpnConnectionListActionButtonCollection").toggleClass("hide_text", true); 
           } else {
               dojo.query("#vpnConnectionListActionButtonCollection").removeClass("hide_text", true); 
           }
        });
        
        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ipsecPresharedKey, 'field': 'ipsecPresharedKey', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                'formatter': function(data) {
                    var vpnStatus = new FogPanel.VPNStatus({                           
                           onRebootVPN : function () {VPNConnection.showRestartDialog(data.referenceId);},
                           onDeleteVPN : function () {VPNConnection.showDeleteDialog(data.referenceId);},
                           enableVPNConnectionStat : true,                      
                    });
                    return vpnStatus;
                }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var vpnConnectionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/VpnCustomerGateway/vpnConnection/list"
        });
        
        var currentMainZoneID = "";
        var currentMainVPC = "";        
        if(dojo.byId("selectedANZoneID").value === "All" || dojo.byId("selectedANZoneID").value === " " || dojo.byId("selectedANZoneID").value === "undefind" || dojo.byId("selectedANZoneID").value === "") { 
            currentMainZoneID = "";
        } else {
            currentMainZoneID = dojo.byId("selectedANZoneID").value;            
        }
        
        if(dojo.byId("selectedANVPCID").value === "All" || dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }
        
        vpnConnectionRestStore.query({vpcId:currentMainVPC, zoneId: currentMainZoneID}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("vpnGeneralConnectionList").innerHTML = "";
                dojo.byId("noGeneralVpnConnectionMessage").style.display = "block";                 
            } else {
                dojo.byId("noGeneralVpnConnectionMessage").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: resultData.ip,
                        gateway: resultData.gateway,
                        name:resultData.name,
                        ipsecPresharedKey:resultData.ipsecPresharedKey,
                        state: resultData.state,
                        action:resultData
                    });
                });
                dojo.byId("vpnGeneralConnectionList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("vpnGeneralConnectionList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
        
        var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storeList = new ItemFileWriteStore({data: option});                        

        var zoneWidget = new FilteringSelect({
            id: "generalVPNConnectionZoneWidget",            
            store: storeList,   
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectZone,
            onChange: function() {
                GeneralVPNConnectionInfo.loadZoneData(this);
            }
        }).placeAt("generalVPNConncetionZoneList");
        var vpcWidget = new FilteringSelect({
            id: "generalVPNConnectionVPCWidget",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectVPC,
            onChange: function() {}
        }).placeAt("generalVPNConncetionVPCList");        
        
        GeneralVPNConnectionInfo.loadCustomerGateway();
    }
}

var VPNCutomerGatewayList = {
    populateAddValues : function () {
        dojo.query("#customerGatewayCloudstackException").toggleClass("hide_text", true);
        dojo.byId("customerGatewayExceptionMsg").innerHTML = "";
    },
    deleteVPNCustomerGateway: function() {        
        var id = dojo.byId('currentVPNCustomerGatewayId').value;        
        var s2sRestStore = new JsonRest({
                target: core.getContextPath() + "/api/VpnCustomerGateway/delete/"
        });

        dijit.byId('vpnCustomerGatewayLoader').show();
        dijit.byId('vpnCustomerGatewayDeleteDialog').hide();
                
        s2sRestStore.add({vpnGatewayId: id}).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if (resultData.result == "OK") {
                    var deletevpncgJobStatus = setInterval(function() {
                        VPNCutomerGatewayList.deletevpncgJob(resultData.jobId, deletevpncgJobStatus);
                    }, 3000);

                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpnCustomerGatewayLoader').hide();
                }

            });

        });
    },
    'deletevpncgJob': function(jobId, deletevpncgJobStatus) {
        var s2svpnJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/VpnCustomerGateway/delete/job/"
        });

        s2svpnJobRestStoreStore.add({jobId:jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deletevpncgJobStatus);
                    VPNCutomerGatewayList.populateValues();
                    registry.byId("userToaster").setContent(translator.common.message.deleteS2SCustomerGateway, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('vpnCustomerGatewayLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deletevpncgJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpnCustomerGatewayLoader').hide();
                }
            });
        });
    },
     populateCounts : function () {                
        
        var vpnStatRestResource = new JsonRest({
            target: core.getContextPath()+"/api/VpnCustomerGateway/count"
        });
        
        vpnStatRestResource.query().then(function (data) {
            dojo.forEach(data, function(resultData) {
                 dojo.byId("vpncgTotalCountNumber").innerHTML = resultData.vpnCustomerGateway;   
            });
        });                
        
        var currentZoneID = dojo.byId("selectedANZoneID").value;          
        var currentVPC = dojo.byId("selectedANVPCID").value;        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                       
            if(currentVPC === "All" || currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                vpnStatRestResource.query().then(function (data) {  
                    if(data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                        dojo.byId("vpnCgConnectionTotalCount").innerHTML = 0;                                                   
                    } else {
                        dojo.byId("vpnCgConnectionTotalCount").innerHTML = data[0].vpnConnection;                           
                    } 
                                                               
                });
            } else {                
                vpnStatRestResource.query({vpcId:currentVPC}).then(function (data) {     
                    if(data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                        dojo.byId("vpnCgConnectionTotalCount").innerHTML = 0;                           
                    } else {
                        dojo.byId("vpnCgConnectionTotalCount").innerHTML = data[0].vpnConnection;                           
                    }
                });
            }
        } else {                                                            
            if(currentVPC === "All" || currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                vpnStatRestResource.query({zoneId: currentZoneID}).then(function (data) {                    
                    if(data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                        dojo.byId("vpnCgConnectionTotalCount").innerHTML = 0;                           
                    } else {                    
                        dojo.byId("vpnCgConnectionTotalCount").innerHTML = data[0].vpnConnection; 
                    }
                });
            } else {
                vpnStatRestResource.query({zoneId: currentZoneID, vpcId:currentVPC}).then(function (data) {   
                    if(data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                        dojo.byId("vpnCgConnectionTotalCount").innerHTML = 0;                           
                    } else {
                        dojo.byId("vpnCgConnectionTotalCount").innerHTML = data[0].vpnConnection;        
                    }
                });
            } 
        }                
    },    
    'cancel' :  function () { 
       core.router.go("#/user/vpnCustomerGateway/list");
    },
    'init' :  function () { 
        
    },
    'showDeleteDialog' :  function (id) { 
        dojo.byId("currentVPNCustomerGatewayId").value = id;
        dijit.byId("vpnCustomerGatewayDeleteDialog").show();
    },
    'closeDeleteVPNCustomerGateway' :  function () { 
        dijit.byId("vpnCustomerGatewayDeleteDialog").hide();
    },
    'populateValues' :  function () { 
        
        dojo.byId("vpnCustomerGatewayList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.cidr, 'field': 'cidr', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ipsecPresharedKey, 'field': 'ipsecPresharedKey', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                'formatter': function(data) {
                    var vpnStatus = new FogPanel.VPNStatus({                           
                           onEditVPN : function () {VPNCutomerGatewayList.editVPNCustomerGateway(data);},
                           onDeleteVPN : function () {VPNCutomerGatewayList.showDeleteDialog(data.referenceId);},
                           enableACLRuleStat : true,
                           editTile : translator.common.edit,
                           deleteTile : translator.common.deleteData                           
                    });
                    return vpnStatus;
                }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var vpnCustomerGateway = new JsonRest({
            target: core.getContextPath() + "/api/VpnCustomerGateway"
        });
        vpnCustomerGateway.query({}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("vpnCustomerGatewayList").innerHTML = "";
                dojo.byId("noVPNCustomerMessageBox").style.display = "block";
                 
            } else {
                dojo.byId("noVPNCustomerMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        gateway: resultData.gateway,
                        name:resultData.name,
                        ipsecPresharedKey:resultData.ipsecPresharedKey,
                        cidr: resultData.cidr,
                        action:resultData
                    });
                });
                dojo.byId("vpnCustomerGatewayList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("vpnCustomerGatewayList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
    },
    'editVPNCustomerGateway' : function(data) {
      
        core.router.go("#/user/vpnCustomerGateway/add");
        
        setTimeout(function() { 
                                   
        
            dojo.byId("vpncgEditName").style.display = "block";
            dojo.byId("vpncgEditName").innerHTML = data.name;
            dojo.byId("vpncgEdit").innerHTML = translator.common.edit;
            dojo.byId("editVPNCustomerGateway").value = data.referenceId;
            dojo.byId("addButtonDiv").style.display = "none";
            dojo.byId("updateButtonDiv").style.display = "block";

            dijit.byId("vpncgName").setValue(data.name);
            dijit.byId("vpncgGateway").setValue(data.gateway);
            dijit.byId("vpncgCidr").setValue(data.cidr);
            dijit.byId("ipsecPresharedKey").setValue(data.ipsecPresharedKey);
            dijit.byId("ikeEncryption").set("value" , data.ikeEncryption);
            dijit.byId("ikeHash").set("value" , data.ikeHash);
            dijit.byId("ikeDH").set("value" , data.ikeDH);
            dijit.byId("espEncryption").set("value" , data.espEncryption);
            dijit.byId("espHash").set("value" , data.espHash);
            dijit.byId("perfectForwardSecrecy").set("value" , data.perfectForwardSecrecy);
            dijit.byId("ikeLifetime").setValue(data.ikeLifetime);
            dijit.byId("espLifetime").setValue(data.espLifetime);
            dijit.byId("deadPeerDetection").set("checked", data.deadPeerDetection);
        },500);
        
        
        
    },
    'addVPNCustomerGateway': function() {
          
        var vpncgName = dijit.byId("vpncgName").value;
        var vpncgGateway = dijit.byId("vpncgGateway").value;
        var vpncgCidr = dijit.byId("vpncgCidr").value;
        var ipsecPresharedKey = dijit.byId("ipsecPresharedKey").value;
        var ikeEncryption = dijit.byId("ikeEncryption").value;
        var ikeHash = dijit.byId("ikeHash").value;
        var ikeDH = dijit.byId("ikeDH").value;
        var espEncryption = dijit.byId("espEncryption").value;
        var espHash = dijit.byId("espHash").value;
        var perfectForwardSecrecy = dijit.byId("perfectForwardSecrecy").value;
        var ikeLifetime = dijit.byId("ikeLifetime").value;
        var espLifetime = dijit.byId("espLifetime").value;
        var deadPeerDetection = dijit.byId("deadPeerDetection").checked;


        var pageNode = dojo.byId("vpnCutomerGatewayAddFormPage");
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

            var tierRestStore = new JsonRest({
                target: core.getContextPath() + "/api/VpnCustomerGateway"
            });
            dijit.byId("vpnCustomerGatewayLoader").show();
            
            tierRestStore.add({
                vpncgName:vpncgName,
                vpncgGateway:vpncgGateway,
                vpncgCidr:vpncgCidr,
                ipsecPresharedKey:ipsecPresharedKey,
                ikeEncryption:ikeEncryption,
                ikeHash:ikeHash,
                ikeDH:ikeDH,
                espEncryption:espEncryption,
                espHash:espHash,
                perfectForwardSecrecy:perfectForwardSecrecy,
                ikeLifetime:ikeLifetime,
                espLifetime:espLifetime,
                deadPeerDetection:deadPeerDetection
                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var VPNCGJobStat = setInterval(function(){VPNCutomerGatewayList.VPNCGCreateJob(resultData.jobId, VPNCGJobStat);},2000); 
                         dojo.query("#customerGatewayCloudstackException").toggleClass("hide_text", true);
                         dojo.byId("customerGatewayExceptionMsg").innerHTML = "";
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dojo.query("#customerGatewayCloudstackException").removeClass("hide_text", true);
                        dojo.byId("customerGatewayExceptionMsg").innerHTML = resultData.message;
                        dijit.byId("vpnCustomerGatewayLoader").hide();
                    }
                });
            });
        }
    },
    'VPNCGCreateJob' : function(jobId, vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/VpnCustomerGateway/create/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.createS2SCustomerGateway, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpnCustomerGatewayLoader").hide();
                    dojo.query("#customerGatewayCloudstackException").toggleClass("hide_text", true);
                    dojo.byId("customerGatewayExceptionMsg").innerHTML = "";
                    core.router.go("#/user/vpnCustomerGateway/list");
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(resultData.message,"error");
                    registry.byId("userToaster").show();       
                    dojo.query("#customerGatewayCloudstackException").removeClass("hide_text", true);
                    dojo.byId("customerGatewayExceptionMsg").innerHTML = resultData.message;
                    dijit.byId("vpnCustomerGatewayLoader").hide();
                } else {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dojo.query("#customerGatewayCloudstackException").removeClass("hide_text", true);
                    dojo.byId("customerGatewayExceptionMsg").innerHTML = resultData.message;
                    dijit.byId("vpnCustomerGatewayLoader").hide(); 
                } 
            });
        });
    },
    'updateVPNCustomerGateway': function() {
          
        var vpncgName = dijit.byId("vpncgName").value;
        var vpncgGateway = dijit.byId("vpncgGateway").value;
        var vpncgCidr = dijit.byId("vpncgCidr").value;
        var ipsecPresharedKey = dijit.byId("ipsecPresharedKey").value;
        var ikeEncryption = dijit.byId("ikeEncryption").value;
        var ikeHash = dijit.byId("ikeHash").value;
        var ikeDH = dijit.byId("ikeDH").value;
        var espEncryption = dijit.byId("espEncryption").value;
        var espHash = dijit.byId("espHash").value;
        var perfectForwardSecrecy = dijit.byId("perfectForwardSecrecy").value;
        var ikeLifetime = dijit.byId("ikeLifetime").value;
        var espLifetime = dijit.byId("espLifetime").value;
        var deadPeerDetection = dijit.byId("deadPeerDetection").checked;


        var pageNode = dojo.byId("vpnCutomerGatewayAddFormPage");
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

            var tierRestStore = new JsonRest({
                target: core.getContextPath() + "/api/VpnCustomerGateway/edit/"
            });
            dijit.byId("vpnCustomerGatewayLoader").show();
            
            tierRestStore.add({
                id:dojo.byId("editVPNCustomerGateway").value,
                vpncgName:vpncgName,
                vpncgGateway:vpncgGateway,
                vpncgCidr:vpncgCidr,
                ipsecPresharedKey:ipsecPresharedKey,
                ikeEncryption:ikeEncryption,
                ikeHash:ikeHash,
                ikeDH:ikeDH,
                espEncryption:espEncryption,
                espHash:espHash,
                perfectForwardSecrecy:perfectForwardSecrecy,
                ikeLifetime:ikeLifetime,
                espLifetime:espLifetime,
                deadPeerDetection:deadPeerDetection
                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var VPNCGEditJobStat = setInterval(function(){VPNCutomerGatewayList.VPNCGEditJob(resultData.jobId, VPNCGEditJobStat);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpnCustomerGatewayLoader").hide();
                    }
                });
            });
        }
    },
    'VPNCGEditJob' : function(jobId, VPNCGEditJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/VpnCustomerGateway/edit/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(VPNCGEditJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.updateS2SCustomerGateway, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpnCustomerGatewayLoader").hide();
                    core.router.go("#/user/vpnCustomerGateway/list");
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(VPNCGEditJobStat);
                    registry.byId("userToaster").setContent(resultData.message,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("vpnCustomerGatewayLoader").hide();
                } else {
                    clearInterval(VPNCGEditJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpnCustomerGatewayLoader").hide(); 
                } 
            });
        });
    },
};
Window.VPNCutomerGatewayList=VPNCutomerGatewayList;
Window.GeneralVPNConnectionInfo = GeneralVPNConnectionInfo;
