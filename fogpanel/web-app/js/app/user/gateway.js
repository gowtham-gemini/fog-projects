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
        name: "gateway",
        module: "user",
        filePath: "/js/app/user/gateway.js",
        layout: {
            name: "gateway",
            containerId: "content"
        },	
        scaffold: false
    },
    {
        "index": action(function() {            
            core.ui.loadTemplate("gatewayContainer", core.ui.getContentId());
            VPNGatewayInfo.populateGatewayCounts();
        }),
        "vpnGateway" : action(function () {
            if(dijit.byId("generals2svpnDeleteDialog")) {
                dijit.byId("generals2svpnDeleteDialog").destroyRecursive();
            }
            if(dijit.byId("generalGatewayvpnLoader")) {
                dijit.byId("generalGatewayvpnLoader").destroyRecursive();
            }  
            if(dijit.byId("generalVpnGatewayAddDialog")) {
                dijit.byId("generalVpnGatewayAddDialog").destroyRecursive();
            }                          
            core.ui.loadTemplate("vpnGatewayListPage", core.ui.getContentId());
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            VPNGatewayInfo.populateValues();
        }),
        "privateGateway": action(function() {            
            core.ui.loadTemplate("privateGatewayListPage", core.ui.getContentId());
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            GeneralPrivateGatewayInfo.populateValues();
        })
    });
});


var GeneralPrivateGatewayInfo = {
    populateValues : function () {
        dojo.byId("userVpcGeneralPrivateGatewayList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true','formatter': function(data) {
                
                        return "<a href='#/user/vpc/viewVPCPrivateGateway/" + data.referenceId + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                    }},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.netmask, 'field': 'netmask', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vlan, 'field': 'vlan', 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'},
            ]
        ];

        var vpnPrivateGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway"
        });
        
        var currentMainZoneID = "";
        var currentMainVPC = "";
        var networkIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/s2s/list/"
        });
        
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
        
        vpnPrivateGatewayRestStore.query({vpcId:currentMainVPC, zoneReferenceId: currentMainZoneID}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("userVpcGeneralPrivateGatewayList").innerHTML = "";
                dojo.byId("noVpcGeneralPrivateGatewayMsgMessageBox").style.display = "block";                 
            } else {
                dojo.byId("noVpcGeneralPrivateGatewayMsgMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: {referenceId:resultData.referenceId, ip:resultData.ip},
                        gateway: resultData.gateway,
                        vlan:resultData.vlan,
                        netmask:resultData.netmask,
                        state: resultData.state,
                        action:resultData
                    });
                });
                dojo.byId("userVpcGeneralPrivateGatewayList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userVpcGeneralPrivateGatewayList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
    }
}




var VPNGatewayInfo = {
    cancelVPNGateway : function () {
        dijit.byId("generalVpnGatewayAddDialog").hide();        
    },
    addVPNGateway : function () {
        var zoneWidget = dijit.byId("generalVPNGatewayZoneWidget");
        var vpcWidget = dijit.byId("generalVPNGatewayVPCWidget");        
        if(zoneWidget.value === "option" || vpcWidget === "option" || zoneWidget.value === "" || vpcWidget === "") {            
            dojo.query("#generalVPNGatewayConditionExceptionMsg").removeClass("hide_text", true); 
        } else {      
            dijit.byId("generalVpnGatewayAddDialog").hide();
            dojo.query("#generalVPNGatewayConditionExceptionMsg").toggleClass("hide_text", true); 
            core.router.go("#/user/vpc/s2sVpn/" + vpcWidget.value);
        }
    },
    showVPNConnectionDialog : function () {
        VPNGatewayInfo.loadVPNGateway();
        dijit.byId("generalVpnGatewayAddDialog").show();        
    },
    loadVPNGateway : function () {                
        dojo.query("#generalVPNGatewayConditionExceptionMsg").toggleClass("hide_text", true);
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
        dojo.query("#generalVPNGatewayZoneList").toggleClass("hide_text", true);
        dojo.query("#generalVPNGatewayZoneLoader").removeClass("hide_text", true);
        
        dojo.query("#generalVPNGatewayVPCList").toggleClass("hide_text", true);
        dojo.query("#generalVPNGatewayVPCLoader").removeClass("hide_text", true);
        
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var currentVPC = dojo.byId("selectedANVPCID").value;
        var zoneRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/zone/"
        });
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc/list/site2siteGatewayBase"
        });           
        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                              
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                zoneRestResource.query().then(function (data) {
                    if(data.length === 0 || data === "" || data === "undefined") {
                        zoneList.newItem({id: "option", name: translator.common.noZone});                                               
                    } else {
                        dojo.forEach(data, function (el) {
                            if(el.networkType === "Advanced") {
                                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
                            }                    
                        });
                    }
                    
                    dijit.byId("generalVPNGatewayZoneWidget").set("store",zoneList);
                    dijit.byId("generalVPNGatewayZoneWidget").set("value","option");
                    
                    dijit.byId("generalVPNGatewayZoneWidget").onChange = function () {
                        VPNGatewayInfo.loadZoneData(dijit.byId("generalVPNGatewayZoneWidget"));
                    };   
                    dojo.query("#generalVPNGatewayZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVPNGatewayVPCList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayVPCLoader").toggleClass("hide_text", true);
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
                    var zoneOption = {
                        identifier: 'id',
                        label: 'name',
                        items: []
                    };
                    var zoneId = "";
                    var zoneList = new ItemFileWriteStore({data: zoneOption});                    
                    if(data.length === 0 || data === "" || data === "undefined") {
                        zoneList.newItem({id: "option", name: translator.common.noZone});                        
                        singleVPCList.newItem({id: "option", name: translator.common.noVPC}); 
                        zoneId = "option";
                        firstVPC = "option";
                    } else {                        
                        dojo.forEach(data, function (el) {                                                
                            zoneList.newItem({id: el.zoneReferenceId, name: el.zone});                        
                            singleVPCList.newItem({id: el.referenceId, name: el.name});    
                            firstVPC = el.referenceId;       
                            zoneId = el.zoneReferenceId;
                        });                                                
                    }
                    
                    dijit.byId("generalVPNGatewayZoneWidget").onChange = function () {};
                    dijit.byId("generalVPNGatewayZoneWidget").set("store", zoneList);
                    dijit.byId("generalVPNGatewayZoneWidget").set("value", zoneId);     
                    dojo.query("#generalVPNGatewayZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayZoneLoader").toggleClass("hide_text", true);
                    
                    dijit.byId("generalVPNGatewayVPCWidget").set("store", singleVPCList);
                    dijit.byId("generalVPNGatewayVPCWidget").set("value", firstVPC);   
                    dojo.query("#generalVPNGatewayVPCList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayVPCLoader").toggleClass("hide_text", true);
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
                    if(data.length === 0 || data === "" || data === "undefined" || data.networkType === "Advanced") { 
                        zoneList.newItem({id: "option", name: translator.common.noZone});    
                    } else {
                        if(data.networkType === "Advanced") {
                            singleZoneList.newItem({id: data.referenceZoneId, name: data.aliasName});
                        }
                    }
                     
                    dijit.byId("generalVPNGatewayZoneWidget").set("store", singleZoneList);
                    dijit.byId("generalVPNGatewayZoneWidget").set("value", data.referenceZoneId);                    
                    dijit.byId("generalVPNGatewayZoneWidget").onChange = function () {
                        VPNGatewayInfo.loadZoneData(dijit.byId("generalVPNGatewayZoneWidget"));
                    }; 
                    dojo.query("#generalVPNGatewayZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVPNGatewayVPCList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayVPCLoader").toggleClass("hide_text", true);                    
                    
                });
            } else {            
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    if(data.length === 0 || data === "" || data === "undefined" || data.networkType === "Advanced") { 
                        zoneList.newItem({id: "option", name: translator.common.noZone}); 
                        singleVPCList.newItem({id: el.referenceId, name: translator.common.noVPC});    
                    } else {
                        var firstVPC = "";                    
                        dojo.forEach(data, function (el) {
                            var zoneOption = {
                                identifier: 'id',
                                label: 'name',
                                items: []
                            };
                            var zoneList = new ItemFileWriteStore({data: zoneOption});                            
                            zoneList.newItem({id: el.zoneReferenceId, name: el.zone});                        
                            singleVPCList.newItem({id: el.referenceId, name: el.name});    
                            firstVPC = el.referenceId;
                        });
                    }                                                  
                    dijit.byId("generalVPNGatewayZoneWidget").onChange = function () {};
                    dijit.byId("generalVPNGatewayZoneWidget").set("store", zoneList);
                    dijit.byId("generalVPNGatewayZoneWidget").set("value", el.zoneReferenceId);     
                    dojo.query("#generalVPNGatewayVPCList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayVPCLoader").toggleClass("hide_text", true);

                    dojo.query("#generalVPNGatewayZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayZoneLoader").toggleClass("hide_text", true);

                    dijit.byId("generalVPNGatewayVPCWidget").set("store", singleVPCList);
                    dijit.byId("generalVPNGatewayVPCWidget").set("value", firstVPC);   
                    dojo.query("#generalVPNGatewayVPCList").removeClass("hide_text", true);
                    dojo.query("#generalVPNGatewayVPCLoader").toggleClass("hide_text", true);
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
        dojo.query("#generalVPNGatewayVPCList").toggleClass("hide_text", true);
        dojo.query("#generalVPNGatewayVPCLoader").removeClass("hide_text", true);
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        if(currentZone.value === "option") {
            vpcList.newItem({id: "option", name: translator.common.selectVPC});
            dijit.byId("generalVPNGatewayVPCWidget").set("store", vpcList);
            dijit.byId("generalVPNGatewayVPCWidget").set("value", "option");
            
            dojo.query("#generalVPNGatewayVPCList").removeClass("hide_text", true);
            dojo.query("#generalVPNGatewayVPCLoader").toggleClass("hide_text", true);
            
        } else {
            var vpcOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/list/site2siteGatewayBase"
            });
            
            vpcOfferRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                var firstVPC = ""
                dojo.forEach(data, function (el, index) {
                    vpcList.newItem({id: el.referenceId, name: el.name});
                    if(index === 0) {
                        firstVPC = el.referenceId;
                    }
                });                
                dijit.byId("generalVPNGatewayVPCWidget").set("store", vpcList);
                dijit.byId("generalVPNGatewayVPCWidget").set("value", firstVPC);    
                
                dojo.query("#generalVPNGatewayVPCList").removeClass("hide_text", true);
                dojo.query("#generalVPNGatewayVPCLoader").toggleClass("hide_text", true);
            });            
        } 
        
    },
    populateGatewayCounts : function () {
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var vpcStatRestResource = new JsonRest({
                target: core.getContextPath()+"/api/vpc/stat"
        });
        var currentVPC = dojo.byId("selectedANVPCID").value;        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                       
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                vpcStatRestResource.query().then(function (data) {  
                    if(data[0].s2sGateway === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                        dojo.byId("vpnGatewayTotalCount").innerHTML = 0;   
                        dojo.byId("vpnPrivateGatewayTotalCount").innerHTML = 0;                                                
                    } else {
                        dojo.byId("vpnGatewayTotalCount").innerHTML = data[0].s2sGateway;   
                        dojo.byId("vpnPrivateGatewayTotalCount").innerHTML = data[0].vpcPrivateGw;                        
                    } 
                                                               
                });
            } else {                
                vpcStatRestResource.query({vpcId:currentVPC}).then(function (data) {     
                    if(data[0].s2sGateway === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {                         
                        dojo.byId("vpnGatewayTotalCount").innerHTML = 0;   
                        dojo.byId("vpnPrivateGatewayTotalCount").innerHTML = 0;  
                    } else {                        
                        dojo.byId("vpnGatewayTotalCount").innerHTML = data[0].s2sGateway;   
                        dojo.byId("vpnPrivateGatewayTotalCount").innerHTML = data[0].vpcPrivateGw;  
                }
                });
            }
        } else {                                                            
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID}).then(function (data) {                    
                    if(data[0].s2sGateway === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {                          
                        dojo.byId("vpnGatewayTotalCount").innerHTML = 0;   
                        dojo.byId("vpnPrivateGatewayTotalCount").innerHTML = 0;  
                    } else {                                            
                        ddojo.byId("vpnGatewayTotalCount").innerHTML = data[0].s2sGateway;   
                        dojo.byId("vpnPrivateGatewayTotalCount").innerHTML = data[0].vpcPrivateGw;  
                    }
                });
            } else {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID, vpcId:currentVPC}).then(function (data) {   
                    if(data[0].s2sGateway === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {                          
                        dojo.byId("vpnGatewayTotalCount").innerHTML = 0; 
                        dojo.byId("vpnPrivateGatewayTotalCount").innerHTML = 0; 
                    } else {                           
                        dojo.byId("vpnGatewayTotalCount").innerHTML = data[0].s2sGateway;   
                        dojo.byId("vpnPrivateGatewayTotalCount").innerHTML = data[0].vpcPrivateGw;  
                    }
                });
            } 
        }
    },
    'showDeleteS2SVPNDialog' : function(id) {
        dojo.byId('currentGeneralS2SVPNID').value = id;
        dijit.byId('generals2svpnDeleteDialog').show();
    },
    deleteS2SVPN :  function () {
        var id = dojo.byId('currentGeneralS2SVPNID').value;        
        var s2sRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/s2s/remove/"
        });

        dijit.byId('generalGatewayvpnLoader').show();
        dijit.byId('generals2svpnDeleteDialog').hide();
                
        s2sRestStore.add({vpnId: id}).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if (resultData.result == "OK") {
                    var deletes2svpnJobStatus = setInterval(function() {
                        VPNGatewayInfo.deletes2svpnJob(resultData.jobId, id,deletes2svpnJobStatus);
                    }, 3000);

                } else {
                    registry.byId("userToaster").setContent(translator.common.firewall.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('generalGatewayvpnLoader').hide();
                }

            });

        });
    },
    'deletes2svpnJob': function(jobId, id,deletes2svpnJobStatus) {
        var s2svpnJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/s2s/remove/job/"
        });

        s2svpnJobRestStoreStore.add({jobId:jobId, vpnId:id}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {                                
                if (jobResultData.jobResult === "OK") {
                    clearInterval(deletes2svpnJobStatus);                    
                    registry.byId("userToaster").setContent(translator.common.message.deleteS2SvpnSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('generalGatewayvpnLoader').hide();
                    VPNGatewayInfo.populateValues();                    
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deletes2svpnJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('generalGatewayvpnLoader').hide();
                }
            });
        });
    },
    closeS2SGatewayShow : function () {
        dijit.byId("generals2svpnDeleteDialog").hide()
    },
    populateValues : function () {    
        
        if(dijit.byId("generalVPNGatewayZoneWidget")) {                                    
            dijit.byId("generalVPNGatewayZoneWidget").destroyRecursive();                    
        }
        if(dijit.byId("generalVPNGatewayVPCWidget")) {                                    
            dijit.byId("generalVPNGatewayVPCWidget").destroyRecursive();                    
        }
        
        dojo.byId("userVpcGeneralGatewayList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";
        
        var vpcRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc"
        });
        
        vpcRestStore.query().then(function (data) {
           if(data.length === 0) {
               dojo.query("#vpnGatewayListActionButtonCollection").toggleClass("hide_text", true); 
           } else {
               dojo.query("#vpnGatewayListActionButtonCollection").removeClass("hide_text", true); 
           }
        });
        
        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ips, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vpcName, 'field': 'vpcName', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                'formatter': function(data) {
                    var html = "<a onclick='VPNGatewayInfo.showDeleteS2SVPNDialog(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";

                    return html;
                }, 'width': '20%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];
        var currentMainZoneID = "";
        var currentMainVPC = "";
        var networkIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/s2s/list/"
        });
        
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
        
        networkIPAddressRestStore.query({zoneReferenceId: currentMainZoneID, vpcId: currentMainVPC}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userVpcGeneralGatewayList").innerHTML = "";
                dojo.byId("noVpcGeneralGatewayMsgMessageBox").style.display = "block";                
            } else {
                dojo.byId("noVpcGeneralGatewayMsgMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.id,
                        ip:resultData.ip,
                        vpcName:resultData.vpc,
                        zone: resultData.zone,
                        action:resultData
                    });
                });
                dojo.byId("userVpcGeneralGatewayList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userVpcGeneralGatewayList");
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
            id: "generalVPNGatewayZoneWidget",            
            store: storeList,   
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectZone,
            onChange: function() {
                VPNGatewayInfo.loadZoneData(this);
            }
        }).placeAt("generalVPNGatewayZoneList");
        var vpcWidget = new FilteringSelect({
            id: "generalVPNGatewayVPCWidget",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectVPC,
            onChange: function() {}
        }).placeAt("generalVPNGatewayVPCList");        
        
        VPNGatewayInfo.loadVPNGateway();
    }    
};

Window.VPNGatewayInfo = VPNGatewayInfo;
Window.GeneralPrivateGatewayInfo = GeneralPrivateGatewayInfo;
