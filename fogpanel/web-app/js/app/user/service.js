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
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dojo/json",
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
//    "dojo/currency",
    "dojox/charting/plot2d/Markers",
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
    "dojox/form/MultiComboBox",
    "dijit/Dialog",
    "dijit/form/DateTextBox",
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
    function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
    ItemFileWriteStore, DataGrid, ContentPane, Source, MultiSelect, dom, win, json,
    Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, ClusteredColumns, Legend) {             
        window.translator = translator;
        window.query = query;
        window.domClass = domClass;
//        window.LocaleNumber = LocaleNumber;
        window.Json = json;
        window.domConstruct = domConstruct;
        window.JsonRest = JsonRest;
        window.registry = registry;
        window.FilteringSelect = FilteringSelect;
        window.ItemFileWriteStore = ItemFileWriteStore;
        window.Select = Select;
        window.ContentPane = ContentPane;
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
        
        controller ({
            name: "service",
            module: "user",
            filePath: "/js/app/user/service.js",
            layout: {
                name: "service",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {                
                core.ui.loadTemplate("serviceIndex", core.ui.getContentId()); 
                var currentZoneID = dojo.byId("selectedANZoneID").value;
                var zoneRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/zone/"
                });                
                if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") { 
                    dojo.query("#serviceBandwidthContainer").removeClass("hide_text", true);                    
                } else {
                    zoneRestStore.get(currentZoneID).then(function (data) {                
                        if(data.networkType == "Advanced") {
                            dojo.query("#serviceBandwidthContainer").toggleClass("hide_text", true);              
                        } else if(data.networkType == "Basic") {
                           dojo.query("#serviceBandwidthContainer").removeClass("hide_text", true);              
                        } else {
                            dojo.query("#serviceBandwidthContainer").removeClass("hide_text", true);              
                        }
                });
            } 
                
            }),
            "sshKey": action(function() {
                if(dijit.byId("sshKeyForm")) {
                    dijit.byId("sshKeyForm").destroyRecursive();
                    dijit.byId("privateKeyDialog").destroyRecursive();                    
                }
                if(dijit.byId("serviceTooltipDialogue")) {
                    dijit.byId("serviceTooltipDialogue").destroyRecursive();
                } 
                core.ui.loadTemplate("SSHKeyPage", core.ui.getContentId()); 
                AddSSHKey.init();
                AddSSHKey.populateValues();
            }),
            "vmBandwidth" : action(function() {                                
                core.ui.loadTemplate("vmBandwidth", core.ui.getContentId()); 
                UserVMBandwidthInfo.init();
                UserVMBandwidthInfo.populateValues();
            }),
            "ip" : action(function() {                                                            
                if(dijit.byId("releaseIpDialog")) {
                    dijit.byId("releaseIpDialog").destroyRecursive();
                } 
                if(dijit.byId("publicIpReleaseIpDialog")) {
                    dijit.byId("publicIpReleaseIpDialog").destroyRecursive();
                } 
                if(dijit.byId("ipManagerLoader")) {
                    dijit.byId("ipManagerLoader").destroyRecursive();
                } 
                if(dijit.byId("ipDisableStaticNatDialog")) {
                    dijit.byId("ipDisableStaticNatDialog").destroyRecursive();
                } 
                if(dijit.byId("ipEnableStaticNatDialog")) {
                    dijit.byId("ipEnableStaticNatDialog").destroyRecursive();
                } 
                
                if(dijit.byId("serviceTooltipDialogue")) {
                    dijit.byId("serviceTooltipDialogue").destroyRecursive();
                }

                if(dijit.byId("ipManagerZone")) {
                    dijit.byId("ipManagerZone").destroyRecursive();
                } 
                if(dijit.byId("networkIpManager")) {
                    dijit.byId("networkIpManager").destroyRecursive();
                } 
                if(dijit.byId("basicInstanceIpManager")) {
                    dijit.byId("basicInstanceIpManager").destroyRecursive();
                } 
                if(dijit.byId("networkAcquireIpDialogInIpManager")) {
                    dijit.byId("networkAcquireIpDialogInIpManager").destroyRecursive();
                } 
                if(dijit.byId("vmAcquireIpDialogInIpManager")) {
                    dijit.byId("vmAcquireIpDialogInIpManager").destroyRecursive();
                } 
                if(dijit.byId("ipManagerAccquireIpForm")) {
                    dijit.byId("ipManagerAccquireIpForm").destroyRecursive();
                } 
                if(dijit.byId("ipManagerPrivateZone")) {
                    dijit.byId("ipManagerPrivateZone").destroyRecursive();
                } 
                if(dijit.byId("ipManagerAccquirePrivateIpForm")) {
                    dijit.byId("ipManagerAccquirePrivateIpForm").destroyRecursive();
                } 
                if(dijit.byId("privateNetworkIpManager")) {
                    dijit.byId("privateNetworkIpManager").destroyRecursive();
                } 
                if(dijit.byId("nicIpManager")) {
                    dijit.byId("nicIpManager").destroyRecursive();
                } 
                if(dijit.byId("nicAcquireIpDialogInIpManager")) {
                    dijit.byId("nicAcquireIpDialogInIpManager").destroyRecursive();
                } 
                if(dijit.byId("serviceAquireIPContent")) {
                    dijit.byId("serviceAquireIPContent").destroyRecursive();
                } 
                
                if(dijit.byId("ipTabContainer")) {
                    dijit.byId("ipTabContainer").destroyRecursive();
                } 
                if(dijit.byId("privateIPTab")) {
                    dijit.byId("privateIPTab").destroyRecursive();
                } 
                
                core.ui.loadTemplate("ipInfo", core.ui.getContentId()); 
                
                var panelZone = dojo.byId("selectedANZoneID").value;
                
                if(panelZone === "All" || panelZone === "") {
                    PublicIpInfo.init();
                    PublicIpInfo.populateValues();
                } else {
                    var zoneRestStore = new JsonRest({
                        target: core.getContextPath() + "/api/zone/"
                    });
                    zoneRestStore.get(panelZone).then(function(currentZone) {
                        if(currentZone.networkType === "Adavanced") {
                            PublicIpInfo.init();
                            PublicIpInfo.populateValues();
                        } else if(currentZone.networkType === "Basic") {
                            PublicIpInfo.init();
                            PublicIpInfo.populateValues();                                                        
                            var mainTab = dijit.byId("ipTabContainer"); //Tr
                            var subIpTab = dijit.byId("privateIPTab"); //tab Id which you want to show
                            mainTab.removeChild(subIpTab); 
                           
                        } else {
                            PublicIpInfo.init();
                            PublicIpInfo.populateValues();
                        }
                    });
                }
                                              
            }),
            'loadBalancer' : action(function() {                                       
                if(dijit.byId("serviceTooltipDialogue")) {
                    dijit.byId("serviceTooltipDialogue").destroyRecursive();
                }
                core.ui.loadTemplate("loadBalancerInfo", core.ui.getContentId());  
                LoadBalancerInfo.init();
                LoadBalancerInfo.populateValues();         
            }),
            'portForwarding' : action(function() {       
                if(dijit.byId("serviceTooltipDialogue")) {
                    dijit.byId("serviceTooltipDialogue").destroyRecursive();
                }
                core.ui.loadTemplate("portForwardingInfo", core.ui.getContentId());  
                PortForwardingInfo.init();
                PortForwardingInfo.populateValues();         
            }),
            'vpn' : action(function() {       
                
                if(dijit.byId("serviceTooltipDialogue")) {
                    dijit.byId("serviceTooltipDialogue").destroyRecursive();
                }
                if(dijit.byId("vpnInfoEnableDialog")) {
                    dijit.byId("vpnInfoEnableDialog").destroyRecursive();
                }
                if(dijit.byId("vpnInfoDisableDialog")) {
                    dijit.byId("vpnInfoDisableDialog").destroyRecursive();
                }
                core.ui.loadTemplate("vpnInfo", core.ui.getContentId());  
                VPNInfo.init();
                VPNInfo.populateValues();         
            })
        });
    });  
    
var VPNInfo = {
    
    'init' : function() {   
        
    },
    enableVPNShow : function (currentIp) {        
        dojo.byId("vpnInfoIPId").value = currentIp;        
        dojo.style(dijit.byId("vpnInfoEnableDialog").closeButtonNode,"display","none");
        dijit.byId("vpnInfoEnableCancelButton").set("disabled", false);
        dijit.byId("vpnInfoEnableDialog").show();        
    },
    disableVPNShow : function (currentIp) {
        dojo.byId("vpnInfoIPId").value = currentIp;    
        dojo.style(dijit.byId("vpnInfoDisableDialog").closeButtonNode,"display","none");
        dijit.byId("vnpInfoDisableCancelButton").set("disabled", false);
        dijit.byId("vpnInfoDisableDialog").show();        
    },
    cancelVPNDialogue : function () {
        dijit.byId("vpnInfoDisableDialog").hide();        
        dijit.byId("vpnInfoEnableDialog").hide();        
    },    
    disableVPN : function () {
        dijit.byId("vnpInfoDisableCancelButton").set("disabled", true);
        dojo.query("#vpnInfoDisableLoader").removeClass("hide_text", true); 
        dojo.query("#vpnInfoDisableOKButton").toggleClass("hide_text", true); 
        
        var curretnIPId = dojo.byId("vpnInfoIPId").value;        
        var disableVPNRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/disable"
        });
        disableVPNRestStore.add({
            ipAddressId : curretnIPId
        }).then(function (response) {            
            dojo.forEach(response, function (resultData) {               
                if(resultData.result === "OK") {
                    var vpnEnableJobStatus = setInterval(function(){VPNInfo.disableIPJob(resultData.jobId,  resultData.vpnReferenceId,  vpnEnableJobStatus);},1000);                   
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                    registry.byId("userToaster").show();    
                    
                    dijit.byId("vnpInfoDisableCancelButton").set("disabled", false);
                    dojo.query("#vpnInfoDisableLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnInfoDisableOKButton").removeClass("hide_text", true);
                    dijit.byId("vpnInfoDisableDialog").hide();
                }
            })
        })
    },
    disableIPJob : function (jobId, vpnReferenceId, vpnEnableStatus) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/remoteAccessVpn/vpnDisableJob"
        });        
            jobStore.add({
                jobId : jobId,
                vpnReferenceId : vpnReferenceId                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.jobResult === "OK") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnDisabled, "message");
                        registry.byId("userToaster").show();         
                        
                        dijit.byId("vnpInfoDisableCancelButton").set("disabled", false);
                        dojo.query("#vpnInfoDisableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnInfoDisableOKButton").removeClass("hide_text", true);
                        dijit.byId("vpnInfoDisableDialog").hide();
                        VPNInfo.populateValues();
                    } else if(resultData.jobResult === "Pending") {
                    } else  if(resultData.jobResult === "FAILED") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vnpInfoDisableCancelButton").set("disabled", false);
                        dojo.query("#vpnInfoDisableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnInfoDisableOKButton").removeClass("hide_text", true);
                        dijit.byId("vpnInfoDisableDialog").hide();
                    } else {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vnpInfoDisableCancelButton").set("disabled", false);
                        dojo.query("#vpnInfoDisableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnInfoDisableOKButton").removeClass("hide_text", true);
                        dijit.byId("vpnInfoDisableDialog").hide();
                    }
                });
            });
        },
    enableVPN : function () {
        dijit.byId("vpnInfoEnableCancelButton").set("disabled", true);
        dojo.query("#VPNInfoEnableLoader").removeClass("hide_text", true); 
        dojo.query("#vpnInfoListEnableOKButton").toggleClass("hide_text", true); 
        
        var curretnIPId = dojo.byId("vpnInfoIPId").value;        
         var enableVPNRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/enable"
        });
        enableVPNRestStore.add({
            ipAddressId : curretnIPId
        }).then(function (response) {            
            dojo.forEach(response, function (resultData) {               
                if(resultData.result === "OK") {
                        var vpnEnableJobStatus = setInterval(function(){VPNInfo.enableIPJob(resultData.jobId, resultData.ipAddressId, vpnEnableJobStatus);},1000);                   
                    } else {
                        registry.byId("userToaster").setContent("Failed","error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vpnInfoEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#VPNInfoEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnInfoListEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("vpnInfoEnableDialog").hide();
                        
                        
                    }
                })
            })
        },
        enableIPJob : function (jobId, ipId, vpnEnableStatus) {
            var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/remoteAccessVpn/vpnEnableJob"
        });         
            jobStore.add({
                jobId : jobId,
                ipId : ipId                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.jobResult === "OK") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbed, "message");
                        registry.byId("userToaster").show();   
                        
                        dijit.byId("vpnInfoEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#VPNInfoEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnInfoListEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("vpnInfoEnableDialog").hide();                
                        VPNInfo.populateValues();
                    } else if(resultData.jobResult === "Pending") {
                    } else  if(resultData.jobResult === "FAILED") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbedError,"error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vpnInfoEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#VPNInfoEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnInfoListEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("vpnInfoEnableDialog").hide();
                    } else {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbedError,"error");
                        registry.byId("userToaster").show(); 
                        dijit.byId("vpnInfoEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#VPNInfoEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnInfoListEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("vpnInfoEnableDialog").hide();
                    }
                });
            });
        },
    'populateValues': function() {          
        
       dojo.byId("vpnPublicIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if((data.isSourceNat === true || data.isSourceNat === "true") && (data.vpnEnabled === true || data.vpnEnabled === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/vpn/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if((data.isSourceNat === true || data.isSourceNat === "true") && (data.vpnEnabled === false || data.vpnEnabled === "false") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && (data.vpnEnabled === true || data.vpnEnabled === "true")  && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/vpn/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && (data.vpnEnabled === false || data.vpnEnabled === "false")  && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && (data.vpnEnabled === true || data.vpnEnabled === "true")  && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/vpn/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && (data.vpnEnabled === false || data.vpnEnabled === "false")  && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        } else {
                            return  data.ip;
                        }
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.network, 'field': 'network', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.networkType, 'field': 'networkType', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vm, 'hidden': 'true', 'field': 'vm', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
                        if(data.vpnEnabled === true && data.isEnabledVPN === true) {
                            html = "<a onclick='VPNInfo.disableVPNShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.vpnDisableTitle + "'><img src='images/ip_disable_icon.png'></img></a></li>"
                        } else if(data.vpnEnabled === false && data.isEnabledVPN === true) {
                            html = "";
                        } else if(data.vpnEnabled === false && data.isEnabledVPN === false) {
                            html = "<a onclick='VPNInfo.enableVPNShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.vpnEnableTitle + "'><img src='images/ip_enable_icon.png'></img></a></li>"
                        }
                        return html;                                                
                }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        if (panelZone === null || panelZone === "") {
            zoneId = "All";
        } else {
            zoneId = panelZone;
        }
                
        var ipCostRestStore = new JsonRest({
            target: core.getContextPath()+"/api/ipAddress/cost"
        });        
        ipCostRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("totalEnabledVPNCount").innerHTML = el.vpnList;
                dojo.byId("totalIPCount").innerHTML = el.advanceZoneIPCount;
                dojo.byId("ipManagerTotalNetwork").innerHTML = el.network;
            });
        }); 
        
        var userIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/ipAddress/publicIp/"
        });
        userIPAddressRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("vpnPublicIpList").innerHTML = "";
                dojo.byId("noVPNPublicIpAddressMessageBox").style.display = "block";
            } else {
                dojo.byId("noVPNPublicIpAddressMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    
                    if(resultData.zoneType === "Advanced") {
                        dataList.newItem({
                            id: resultData.referenceId,
                            ip: {vpnEnabled: resultData.vpnEnabled, ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat, zoneType:resultData.zoneType},
    //                        supportedService: {ip: resultData.ip, zoneType:resultData.zoneType, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat},
                            zone: resultData.zone,
                            vpnEnabled: resultData.vpnEnabled,
                            vm: resultData.vm ? resultData.vm : "",
                            network: resultData.network ? resultData.network : "Default",
                            networkType: resultData.networkType ? resultData.networkType : "Shared",
                            action: {isEnabledVPN:resultData.isEnabledVPN,vpnEnabled: resultData.vpnEnabled,id: resultData.id, networkId:resultData.networkId, zoneType:resultData.zoneType, ip: resultData.ip, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat}
                        });
                    }
                    
                });
                dojo.byId("vpnPublicIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("vpnPublicIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    }
};    
    
    
var PortForwardingInfo = {
    
    'init' : function() {   
        
    },
    'populateValues': function() {          
        
       dojo.byId("portForwardingPublicIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if ((data.isSourceNat === true || data.isSourceNat === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/portForwarding/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/portForwarding/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/portForwarding/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        } else {
                            return  data.ip;
                        }
                }},
                {'name': translator.common.rules, 'field': 'pfNo', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.network, 'field': 'network', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.networkType, 'field': 'networkType', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vm, 'hidden': 'true', 'field': 'vm', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action', 'hidden': 'true',
                    'formatter': function(data) {
                        var html;
                        if((data.isSourceNat === true || data.isSourceNat === "true") && (data.isStaticNat === false || data.isStaticNat === "false") && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></a></li>"+
                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
//                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
                                    "<a href='#/user/network/portForwarding/" + data.id + "' class='offset1' title='" + translator.common.portForwarding + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/port_foward_icon.png'></img></a></li>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && (data.isSourceNat === false || data.isSourceNat === "false")  && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.disableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.disableStaticNat + "'><img style='width: 17px; height: 17px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                                    
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></i></a></li>"+
                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
//                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
                                    "<a href='#/user/network/portForwarding/" + data.id + "' class='offset1' title='" + translator.common.portForwarding + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/port_foward_icon.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.enableStaticNatIPShow(\"" + data.id + "\", \"" + data.networkId +"\")' class='offset1' title='" + translator.common.enableStaticNat + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+       
                                    "<a style='margin-top: 50px;' onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                        } else {
                             html = "<a onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                        }
                        return html;
                                                
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        if (panelZone === null || panelZone === "") {
            zoneId = "All";
        } else {
            zoneId = panelZone;
        }
                
        var ipCostRestStore = new JsonRest({
            target: core.getContextPath()+"/api/ipAddress/cost"
        });        
        ipCostRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("portForwardingCost").innerHTML = el.pfCost.toFixed(2);
                dojo.byId("portForwardingCount").innerHTML = el.pfCount;
                dojo.byId("ipManagerTotalNetwork").innerHTML = el.network;
                dojo.byId("accquiredIPCurrency").innerHTML = el.currency;
            });
        }); 
        
        var userIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/ipAddress/publicIp/"
        });
        userIPAddressRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("portForwardingPublicIpList").innerHTML = "";
                dojo.byId("noPortForwardingPublicIpAddressMessageBox").style.display = "block";
            } else {
                dojo.byId("noPortForwardingPublicIpAddressMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    
                    if(resultData.isStaticNat === false && resultData.zoneType === "Advanced") {
                        dataList.newItem({
                            id: resultData.referenceId,
                            ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat, zoneType:resultData.zoneType},
    //                        supportedService: {ip: resultData.ip, zoneType:resultData.zoneType, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat},
                            zone: resultData.zone,
                            pfNo: resultData.pfNo,
                            vm: resultData.vm ? resultData.vm : "",
                            network: resultData.network ? resultData.network : "Default",
                            networkType: resultData.networkType ? resultData.networkType : "Shared",
                            action: {id: resultData.id, networkId:resultData.networkId, zoneType:resultData.zoneType, ip: resultData.ip, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat}
                        });
                    }
                    
                });
                dojo.byId("portForwardingPublicIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("portForwardingPublicIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    
    }
};     
    
var LoadBalancerInfo = {
    
    'init' : function() {   
        
    },
    'populateValues': function() {          
        
       dojo.byId("loadBalancerPublicIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if ((data.isSourceNat === true || data.isSourceNat === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/loadBalancing/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/loadBalancing/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/loadBalancing/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        } else {
                            return  data.ip;
                        }
                }},
                {'name': translator.common.policy, 'field': 'lbNo', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.network, 'field': 'network', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.networkType, 'field': 'networkType', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vm, 'hidden': 'true', 'field': 'vm', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action', 'hidden': 'true',
                    'formatter': function(data) {
                        var html;
                        if((data.isSourceNat === true || data.isSourceNat === "true") && (data.isStaticNat === false || data.isStaticNat === "false") && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></a></li>"+
                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
//                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
                                    "<a href='#/user/network/portForwarding/" + data.id + "' class='offset1' title='" + translator.common.portForwarding + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/port_foward_icon.png'></img></a></li>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && (data.isSourceNat === false || data.isSourceNat === "false")  && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.disableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.disableStaticNat + "'><img style='width: 17px; height: 17px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                                    
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></i></a></li>"+
                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
//                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
                                    "<a href='#/user/network/portForwarding/" + data.id + "' class='offset1' title='" + translator.common.portForwarding + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/port_foward_icon.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.enableStaticNatIPShow(\"" + data.id + "\", \"" + data.networkId +"\")' class='offset1' title='" + translator.common.enableStaticNat + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+       
                                    "<a style='margin-top: 50px;' onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                        } else {
                             html = "<a onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                        }
                        return html;
                                                
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        if (panelZone === null || panelZone === "") {
            zoneId = "All";
        } else {
            zoneId = panelZone;
        }
                
        var ipCostRestStore = new JsonRest({
            target: core.getContextPath()+"/api/ipAddress/cost"
        });        
        ipCostRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("loadBalancerCost").innerHTML = el.lbCost.toFixed(2);
                dojo.byId("loadBalancerCount").innerHTML = el.lbCount;
                dojo.byId("ipManagerTotalNetwork").innerHTML = el.network;
                dojo.byId("accquiredIPCurrency").innerHTML = el.currency;
            });
        }); 
        
        var userIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/ipAddress/publicIp/"
        });
        userIPAddressRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("loadBalancerPublicIpList").innerHTML = "";
                dojo.byId("noLoadBalancerPublicIpAddressMessageBox").style.display = "block";
            } else {
                dojo.byId("noLoadBalancerPublicIpAddressMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    
                    if(resultData.isStaticNat === false && resultData.zoneType === "Advanced") {
                        dataList.newItem({
                            id: resultData.referenceId,
                            ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat, zoneType:resultData.zoneType},
    //                        supportedService: {ip: resultData.ip, zoneType:resultData.zoneType, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat},
                            zone: resultData.zone,
                            lbNo: resultData.lbNo,
                            vm: resultData.vm ? resultData.vm : "",
                            network: resultData.network ? resultData.network : "Default",
                            networkType: resultData.networkType ? resultData.networkType : "Shared",
                            action: {id: resultData.id, networkId:resultData.networkId, zoneType:resultData.zoneType, ip: resultData.ip, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat}
                        });
                    }
                    
                });
                dojo.byId("loadBalancerPublicIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("loadBalancerPublicIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    
    }
};     
    
var PrivateIpInfo = {
    'init' : function() {   
    
    },
    'populateValues': function() {          
        
        PrivateIpInfo.list();
    
    },
    'list': function() {

        dojo.byId("userPrivateIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.network, 'field': 'network', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.networkType, 'field': 'networkType', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vm, 'field': 'vm', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        return "<a onclick='PrivateIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        if (panelZone === null || panelZone === "") {
            zoneId = "All";
        } else {
            zoneId = panelZone;
        }
        
        var userIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/ipAddress/privateIp/"
        });
        userIPAddressRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("userPrivateIpList").innerHTML = "";
                dojo.byId("noPrivateIpAddressMessageBox").style.display = "block";
            } else {
                dojo.byId("noPrivateIpAddressMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: resultData.ip,
                        zone: resultData.zone,
                        vm: resultData.vm,
                        network: resultData.network,
                        networkType: resultData.networkType,
                        action: {id: resultData.id, networkId:resultData.networkId, zoneType:resultData.zoneType, ip: resultData.ip}
                    });
                });
                dojo.byId("userPrivateIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userPrivateIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.publicIPLimit !== "-1") {                   
                    if((limitData.publicIPUsed >= limitData.publicIPLimit)) {
                        dojo.byId("privateIPLimitReachedMsg").style.display = "block";
                        dojo.query("#ipInfoAccquirePrivateIpButtom").toggleClass("hide_text", true);                                           
                    } else {
                        dojo.byId("privateIPLimitReachedMsg").style.display = "none";
                        dojo.query("#ipInfoAccquirePrivateIpButtom").removeClass("hide_text", true);    
                    }
                } else {
                    dojo.byId("privateIPLimitReachedMsg").style.display = "none";
                    dojo.query("#ipInfoAccquirePrivateIpButtom").removeClass("hide_text", true);
                }
            });
        });
    },
    'releaseIPShow': function(id) {
        dojo.byId("currentIPId").value = id;
        dijit.byId("releaseIpDialog").show();
    },
    'closeReleaseIPShow': function() {
        dijit.byId("releaseIpDialog").hide();
    },
    'releaseIP': function() {

        var ipReleaseRestStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/ip/remove"
        });

        dijit.byId('ipManagerLoader').show();
        dijit.byId('releaseIpDialog').hide();

        ipReleaseRestStore.add({ipId: dojo.byId("currentIPId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var releaseIPJobStatus = setInterval(function() {
                        PrivateIpInfo.releaseIPJob(resultData.jobId, releaseIPJobStatus);
                    }, 3000);
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();

                }
            });
        });
    },
    'releaseIPJob': function(jobId, releaseIPJobStatus) {
        var acquireIpJobStoreStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/ip/remove/job/"
        });

        acquireIpJobStoreStore.add({jobId: jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.releaseIPSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                    PrivateIpInfo.list();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                }
            });
        });
    },
    'getZoneData' : function(currentWidget) {
        
        currentWidget.store.fetch({query: {id: currentWidget.value},
            onItem: function(item) { 
                var advanceInstanceOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var advanceInstanceList = new ItemFileWriteStore({data: advanceInstanceOptions});

                var instanceCountRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/virtualMachine/count"
                });
                var firstVM = "";
                instanceCountRestStore.query({zoneReferenceId: item.id}).then(function(data) {
                   dojo.forEach(data[0].instanceData, function(resultData, index) {
                        advanceInstanceList.newItem({
                            id: resultData.referenceId,
                            name: resultData.name
                        });
                        if (index === 0) {
                            firstVM = resultData.referenceId;
                        }
                    });
                    dijit.byId("privateNetworkIpManager").reset();
                    dijit.byId("privateNetworkIpManager").set("store", advanceInstanceList);
                    dijit.byId("privateNetworkIpManager").set("value", firstVM);
                });
            }
        });
    },
    'getNicData' : function(currentWidgetId) {
        
        var nicRestStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/list"
        });  
        
         var nicOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var nicList = new ItemFileWriteStore({data: nicOptions});
        var firstNic;
        nicRestStore.query({vmReferenceId: currentWidgetId}).then(function (nicItem) {
            dojo.forEach(nicItem, function (el, index) { 
                if (index === 0) {
                        firstNic = el.referenceId;
                    }
                nicList.newItem({id: el.referenceId, name: "Nic-"+el.networkName});
            });
            dijit.byId("nicIpManager").reset();
            dijit.byId("nicIpManager").set("store", nicList);
            dijit.byId("nicIpManager").set("value", firstNic);
        });
        
    },
    'nicAcquireIpShow': function() {                
        var pageNode = dojo.byId("nicAcquireIpPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var wigetState = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                wigetState = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId("nicAcquireIpDialogInIpManager").show();    
        }
    },
    'closeAcquireIpDialog' : function() {            
        dijit.byId("nicAcquireIpDialogInIpManager").hide();    
    },
    'acquireIp': function() {            
           
        var nicStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/ip/acquire/"         
        });
        dijit.byId("ipManagerLoader").show();        
        dijit.byId("nicAcquireIpDialogInIpManager").hide();      
                
        nicStore.add({
            nicId:  dijit.byId("nicIpManager").value
        }).then(function(result) {
            dojo.forEach(result, function(resultData) {
               if(resultData.result == "OK") {
                   var acquireIpJobStatus = setInterval(function(){PrivateIpInfo.acquireIpJob(resultData.jobId, resultData.nicId,acquireIpJobStatus);},3000); 
               } else {
                    dijit.byId("ipManagerLoader").hide();  
                    registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                    registry.byId("userToaster").show();
               }                                        
           });                            
        });
        
    }, 
    'acquireIpJob': function(jobId, nicId, attachIsoJobStatus) {         
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/ip/acquire/job/"
        }); 
        jobStore.add({
            jobId : jobId,
            nicId:nicId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult === "OK") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.acquireIp,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("ipManagerLoader").hide();        
                    PrivateIpInfo.list();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("ipManagerLoader").hide();
                }
            });
        });
    }
    
};    
    
var PublicIpInfo = {
    
    'init' : function() {       
        
        var networkOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var networkList = new ItemFileWriteStore({data: networkOptions});
        
        var networkWidget = new FilteringSelect({
            id: "networkIpManager",
            store: networkList,
            placeHolder: translator.common.selectNetwotk,
            maxHeight: 100
        });
        networkWidget.startup();
        networkWidget.placeAt("networkDivInIpManager");
        
        var privateVMOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var privateVMList = new ItemFileWriteStore({data: privateVMOptions});
        
        var privateVMWidget = new FilteringSelect({
            id: "privateNetworkIpManager",
            store: privateVMList,
            placeHolder: translator.common.selectInstance,
            maxHeight: 100,
             onChange: function() {
                PrivateIpInfo.getNicData(this.value);
            }
        });
        privateVMWidget.startup();
        privateVMWidget.placeAt("privateVMDivInIpManager");
        
        
        
        var nicOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var nicList = new ItemFileWriteStore({data: nicOptions});
        
        var nicWidget = new FilteringSelect({
            id: "nicIpManager",
            store: nicList,
            placeHolder: translator.common.selectNic,
            maxHeight: 100
        });
        nicWidget.startup();
        nicWidget.placeAt("nicDivInIpManager");
        
        var basicInstanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var basicInstanceList = new ItemFileWriteStore({data: basicInstanceOptions});
        
        var basicInstanceWidget = new FilteringSelect({
            id: "basicInstanceIpManager",
            store: basicInstanceList,
            placeHolder: translator.common.message.selectInstance,
            maxHeight: 100
        });
        basicInstanceWidget.startup();
        basicInstanceWidget.placeAt("basicInstanceDivInIpManager");
        
        
        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        if (panelZone === null || panelZone === "") {
            zoneId = "All";
        } else {
            zoneId = panelZone;
        }
        
        var ipCostRestStore = new JsonRest({
            target: core.getContextPath()+"/api/ipAddress/cost"
        });        
        ipCostRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("accquiredIPCost").innerHTML = el.ipCost.toFixed(2);
                dojo.byId("accquiredIP").innerHTML = el.ipCount;
                dojo.byId("ipManagerTotalNetwork").innerHTML = el.network;
                dojo.byId("accquiredIPCurrency").innerHTML = el.currency;
            });
        }); 
        
        var currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"
        });        
        currentAccRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if(el.accountType == "TRIAL") {
//                    dojo.byId("trialIpMsg").style.display = "block";
//                    dojo.byId("ipViewPageDiv").style.display = "none";                    
                }          
            });
        }); 
        
        
        var ipOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var ipList = new ItemFileWriteStore({data: ipOptions});
        
        var ipWidget = new FilteringSelect({
            id: "staticNatVMIpManager",
            store: ipList,
            placeHolder: translator.common.message.selectIp
        });
        ipWidget.startup();
        ipWidget.placeAt("staticNatVMIpDivInIpManager");
        
        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var instanceList = new ItemFileWriteStore({data: instanceOptions});
        
        var instanceWidget = new FilteringSelect({
            id: "staticNatVMInIpManager",
            store: instanceList,
            placeHolder: translator.common.message.selectInstance,
            onChange: function() {
                PublicIpInfo.getVMIPList(this.value);
            }
        });
        instanceWidget.placeAt("staticNatVMListInIpManager");
        instanceWidget.startup();
        
        
        var zoneListStore = new JsonRest({
            target: core.getContextPath() + "/api/zone"
        });

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var zoneFileStoreList = new ItemFileWriteStore({data: zoneOptions});
        
        var privateZoneOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var privateZoneFileStoreList = new ItemFileWriteStore({data: privateZoneOptions});

        var zoneWidget = new Select({
            id: "ipManagerZone",
            name: "ipManagerZone",
            store: zoneFileStoreList,
            onChange: function() {
                PublicIpInfo.getZoneData(this);
            }
        }, "ipManagerZoneList");
        
        
        var privateZoneWidget = new Select({
            id: "ipManagerPrivateZone",
            name: "ipManagerPrivateZone",
            store: privateZoneFileStoreList,
            onChange: function() {
                PrivateIpInfo.getZoneData(this);
            }
        }, "ipManagerPrivateZoneList");
        

        var firstZone = "";
        zoneListStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {

                if (zoneId !== "All") {
                    if(resultData.referenceZoneId === zoneId) {
                        if (firstZone !== "") {
                            firstZone = resultData.referenceZoneId;
                        }
                        zoneFileStoreList.newItem({id: resultData.referenceZoneId, name: resultData.aliasName, networkType: resultData.networkType});
                        if (resultData.networkType === "Advanced") {
                            privateZoneFileStoreList.newItem({id: resultData.referenceZoneId, name: resultData.aliasName, networkType: resultData.networkType});
                        }
                    }
                } else {
                    if (firstZone !== "") {
                        firstZone = resultData.referenceZoneId;
                    }
                    zoneFileStoreList.newItem({id: resultData.referenceZoneId, name: resultData.aliasName, networkType: resultData.networkType});
                    if (resultData.networkType === "Advanced") {
                        privateZoneFileStoreList.newItem({id: resultData.referenceZoneId, name: resultData.aliasName, networkType: resultData.networkType});
                    }
                }
            });
            dijit.byId("ipManagerZone").set("store", zoneFileStoreList);
            dijit.byId("ipManagerZone").set("value", firstZone);
            dijit.byId("ipManagerPrivateZone").set("store", privateZoneFileStoreList);
            dijit.byId("ipManagerPrivateZone").set("value", firstZone);
        });                                
    },
    showCondition : function () {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/public/termsAndCondition"
        });                  
        dojo.style(dijit.byId("serviceAquireIPContent").closeButtonNode,"display","none");
        configRestStore.query().then(function(resultData) {
            dojo.byId("ipInfoContentArea").innerHTML = resultData.value;                   
        });
        dijit.byId("networkAcquireIpDialogInIpManager").hide();
        dijit.byId("serviceAquireIPContent").show();
    },
    cancelConditionBox : function () {
            dijit.byId("serviceAquireIPContent").hide();
            dijit.byId("networkAcquireIpDialogInIpManager").show();
        },
    'vmAcquireIPShow': function() {
        
        var pageNode = dojo.byId("vmListEnable");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var wigetState = true;        
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                wigetState = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId("vmAcquireIpDialogInIpManager").show();
        }
    },
    'networkAcquireIPShow': function() {
        
        var pageNode = dojo.byId("networkListEnable");
        dijit.byId("serviceIPInfoAgreement").set("checked", false);
        dojo.byId("ipConditionExceptionMsg").style.display = "none";
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var wigetState = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                wigetState = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId("networkAcquireIpDialogInIpManager").show();
        }
    },
    'closeAcquireIPShow': function() {
        dijit.byId("networkAcquireIpDialogInIpManager").hide();
        dijit.byId("vmAcquireIpDialogInIpManager").hide();
    },
    'acquireIp': function() {
        var conditionChecked = dijit.byId("serviceIPInfoAgreement").checked;
        if(conditionChecked == true) {
            dojo.byId("ipConditionExceptionMsg").style.display = "none";
            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/ip/acquire"
            });
            dijit.byId('ipManagerLoader').show();
            dijit.byId('networkAcquireIpDialogInIpManager').hide();
            networkRestStore.add({networkId: dijit.byId("networkIpManager").value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var acquireIpJobStatus = setInterval(function() {
                            PublicIpInfo.acquireJob(resultData.jobId, acquireIpJobStatus, resultData.networkReferenceId);
                        }, 3000);
                    } else if (resultData.result === "HASFIRSTIP") {
                        registry.byId("userToaster").setContent(translator.common.message.networkHasFirstIPMessage, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('ipManagerLoader').hide();
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("ipManagerLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('ipManagerLoader').hide();
                    }
                });
            });
        } else {
            dojo.byId("ipConditionExceptionMsg").style.display = "block";
        }
    },
    'acquireJob': function(jobId, acquireIpJobStatus, networkReferenceId) {
        var acquireIpJobStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/job/"
        });

        acquireIpJobStoreStore.add({jobId: jobId, networkReferenceId: networkReferenceId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult == "OK") {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.addIpToNetworkSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                    PublicIpInfo.list();
                    dijit.byId('ipManagerAccquireIpForm').reset();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                }
            });
        });
    },
    'vmAcquireIp': function() {            
       
        var instanceConsoleStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/ip/acquire/"         
        });
        
        dijit.byId("ipManagerLoader").show(); 
        dijit.byId("vmAcquireIpDialogInIpManager").hide();         
        instanceConsoleStore.add({
            vmId:  dijit.byId("basicInstanceIpManager").value
        }).then(function(result) {
            dojo.forEach(result, function(resultData) {
               if(resultData.result == "OK") {
                   var acquireIpJobStatus = setInterval(function(){PublicIpInfo.vmAcquireIpJob(resultData.jobId, acquireIpJobStatus, resultData.vmId);},3000); 
               } else {
                    dijit.byId("ipManagerLoader").hide();  
                    registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                    registry.byId("userToaster").show();
               }                                        
           });                            
        });
        
    }, 
    'vmAcquireIpJob': function(jobId, attachIsoJobStatus, vmId) {         
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/ip/acquire/job/"
        }); 
        jobStore.add({
            jobId : jobId,
            vmId : vmId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.acquireIp,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("ipManagerLoader").hide();
                    PublicIpInfo.list();   
                    dijit.byId('ipManagerAccquireIpForm').reset();
                } else if(resultData.jobResult == "Pending") {
                } else  if(resultData.jobResult == "FAILED") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("ipManagerLoader").hide();
                }
            });
        });
    },
    'getZoneData': function(currentWidget) {
      
      currentWidget.store.fetch({query: {id: currentWidget.value},
            onItem: function(item) { 
                if(item.networkType.toString() === "Advanced") {
                    dojo.byId("networkListEnable").style.display = "block";
                    dojo.byId("vmListEnable").style.display = "none";
                    dojo.byId("networkAccquireIpButtom").style.display = "block";
                    dojo.byId("vmAccquireIpButtom").style.display = "none";
                   
                    var networkOptions = {
                        identifier: 'id',
                        label: 'name',
                        items: []
                    };

                    var networkList = new ItemFileWriteStore({data: networkOptions});
                                      
                    var networkRestStore = new JsonRest({
                        target: core.getContextPath() + "/api/network"
                    });
                    var firstNetwork = "";
                    networkRestStore.query({zoneReferenceId: item.id}).then(function(data) {
                       dojo.forEach(data, function(resultData, index) {
                            networkList.newItem({
                                id: resultData.id,
                                name: resultData.name
                            });
                            if (index === 0) {
                                firstNetwork = resultData.id;
                            }
                        });
                        dijit.byId("networkIpManager").reset();
                        dijit.byId("networkIpManager").set("store", networkList);
                        dijit.byId("networkIpManager").set("value", firstNetwork);
                    });
                    
                    
                   
                } else {
                    
                    dojo.byId("networkListEnable").style.display = "none";
                    dojo.byId("vmListEnable").style.display = "block";
                    dojo.byId("networkAccquireIpButtom").style.display = "none";
                    dojo.byId("vmAccquireIpButtom").style.display = "block";
                    
                    var basicInstanceOptions = {
                        identifier: 'id',
                        label: 'name',
                        items: []
                    };

                    var basicInstanceList = new ItemFileWriteStore({data: basicInstanceOptions});
                                      
                    var instanceCountRestStore = new JsonRest({
                        target: core.getContextPath()+"/api/virtualMachine/count"
                    });
                    var firstVM = "";
                    instanceCountRestStore.query({zoneReferenceId: item.id}).then(function(data) {
                       dojo.forEach(data[0].instanceData, function(resultData, index) {
                            basicInstanceList.newItem({
                                id: resultData.referenceId,
                                name: resultData.name
                            });
                            if (index === 0) {
                                firstVM = resultData.referenceId;
                            }
                        });
                        dijit.byId("basicInstanceIpManager").reset();
                        dijit.byId("basicInstanceIpManager").set("store", basicInstanceList);
                        dijit.byId("basicInstanceIpManager").set("value", firstVM);
                    });
                    
                }
            }
        });   
    },
    'getVMIPList': function(vmId) {
        
        var networkId = dojo.byId("currentIPNetworkId").value;
        
        if (vmId == null || vmId == "" || vmId == "null") {
            return false;
        }
        
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/nic/ip/list/"
        });

        var ipOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var ipList = new ItemFileWriteStore({data: ipOptions});
        var firstValue;
        networkVMListRestStore.query({networkId: networkId, vmId: vmId}).then(function(data) {

            dojo.forEach(data, function(ip, index) {
                ipList.newItem({
                    id: ip.referenceId,
                    name: ip.ip
                });
                if (index === 0) {
                    firstValue = ip.referenceId;
                }
            });

            dijit.byId("staticNatVMIpManager").reset();
            dijit.byId("staticNatVMIpManager").set("store", ipList);
            dijit.byId("staticNatVMIpManager").set("value", firstValue);
        });
    },
    'populateValues': function() {                                  
        PublicIpInfo.list();    
    },
    'releaseIPShow': function(id) {
        dojo.byId("currentIPId").value = id;
        dijit.byId("publicIpReleaseIpDialog").show();
    },
    'closeReleaseIPShow': function() {
        dijit.byId("publicIpReleaseIpDialog").hide();
    },
    'releaseIP': function() {

        var ipReleaseRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/release"
        });

        dijit.byId('ipManagerLoader').show();
        dijit.byId('publicIpReleaseIpDialog').hide();

        ipReleaseRestStore.add({ipId: dojo.byId("currentIPId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var releaseIPJobStatus = setInterval(function() {
                        PublicIpInfo.releaseIPJob(resultData.jobId, releaseIPJobStatus);
                    }, 3000);
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();

                }
            });
        });
    },
    'releaseIPJob': function(jobId, releaseIPJobStatus) {
        var acquireIpJobStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/release/job/"
        });

        acquireIpJobStoreStore.get(jobId).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.releaseIPSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                    PublicIpInfo.list();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                }
            });
        });
    },
    'disableStaticNatIPShow': function(id) {
        dojo.byId("currentIPId").value = id;
        dijit.byId("ipDisableStaticNatDialog").show();
    },
    'closeDisableStaticNat': function() {
        dijit.byId("ipDisableStaticNatDialog").hide();
    },
    'disableStaticNat': function() {
        dijit.byId('ipManagerLoader').show();
        dijit.byId("ipDisableStaticNatDialog").hide();

        var ipEnableStaticRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/disableStaticNat"
        });

        ipEnableStaticRestStore.add({ipId: dojo.byId('currentIPId').value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var disableStaticNatJobStatus = setInterval(function() {
                        PublicIpInfo.disableStaticNatJob(resultData.jobId, disableStaticNatJobStatus);
                    }, 3000);
                } else if (resultData.result === "true") {
                    registry.byId("userToaster").setContent(translator.common.message.disableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                    PublicIpInfo.list();
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("ipManagerLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();

                }
            });
        });
    },
    'disableStaticNatJob': function(jobId, disableStaticNatJobStatus) {
        var enableStaticNatJobStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/disableStaticNat/job/"
        });

        enableStaticNatJobStoreStore.get(jobId).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(disableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.disableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                    PublicIpInfo.list();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(disableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                }
            });
        });
    },
    'enableStaticNat': function() {

        var vmId = dijit.byId("staticNatVMInIpManager");
        var vmIpId = dijit.byId("staticNatVMIpManager");

        var pageNode = dojo.byId("ipEnableStaticNatFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var wigetState = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                wigetState = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {

            dijit.byId('ipManagerLoader').show();
            dijit.byId("ipEnableStaticNatDialog").hide();

            var ipEnableStaticRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/ip/enableStaticNat"
            });

            ipEnableStaticRestStore.add({vmId: vmId.value, vmIpId: vmIpId.value, ipId: dojo.byId('currentIPId').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var enableStaticNatJobStatus = setInterval(function() {
                            PublicIpInfo.enableStaticNatJob(resultData.jobId, enableStaticNatJobStatus);
                        }, 3000);
                    } else if (resultData.result === "true") {
                        registry.byId("userToaster").setContent(translator.common.message.enableStaticNatSuccess, "message");
                        registry.byId("userToaster").show();
                        dijit.byId('ipManagerLoader').hide();
                        PublicIpInfo.list();
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("ipManagerLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('ipManagerLoader').hide();

                    }
                });
            });
        }
    },
    'enableStaticNatJob': function(jobId, enableStaticNatJobStatus) {
        var enableStaticNatJobStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/enableStaticNat/job/"
        });

        enableStaticNatJobStoreStore.get(jobId).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult == "OK") {
                    clearInterval(enableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.enableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                    PublicIpInfo.list();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(enableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipManagerLoader').hide();
                }
            });
        });
    },
    'enableStaticNatIPShow': function(id, networkId) {
                                
        dojo.byId("currentIPNetworkId").value = networkId;
                
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/vm/list/"
        });
        
        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var instanceList = new ItemFileWriteStore({data: instanceOptions});
        
        networkVMListRestStore.query({networkId: networkId}).then(function(data) {
            dojo.forEach(data, function(vm) {
                instanceList.newItem({
                    id: vm.referenceId,
                    name: vm.name
                });
            });
            dijit.byId("staticNatVMInIpManager").reset();
            dijit.byId("staticNatVMInIpManager").set("store", instanceList);
        });

        dojo.byId('currentIPId').value = id;

        dijit.byId("ipEnableStaticNatForm").reset();

        dijit.byId("ipEnableStaticNatDialog").show();
    },
    'closeEnableStaticNat': function() {
        dijit.byId("ipEnableStaticNatDialog").hide();
    },
    'list': function() {

        dojo.byId("userPublicIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if ((data.isSourceNat === true || data.isSourceNat === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        } else {
                            return  data.ip;
                        }
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.network, 'field': 'network', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.networkType, 'field': 'networkType', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vm, 'field': 'vm', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
                        if((data.isSourceNat === true || data.isSourceNat === "true") && (data.isStaticNat === false || data.isStaticNat === "false") && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></a></li>"+
                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
//                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
                                    "<a href='#/user/network/portForwarding/" + data.id + "' class='offset1' title='" + translator.common.portForwarding + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/port_foward_icon.png'></img></a></li>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && (data.isSourceNat === false || data.isSourceNat === "false")  && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.disableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.disableStaticNat + "'><img style='width: 17px; height: 17px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                                    
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></i></a></li>"+
                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
//                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
                                    "<a href='#/user/network/portForwarding/" + data.id + "' class='offset1' title='" + translator.common.portForwarding + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/port_foward_icon.png'></img></a></li>"+
                                    "<a onclick='PublicIpInfo.enableStaticNatIPShow(\"" + data.id + "\", \"" + data.networkId +"\")' class='offset1' title='" + translator.common.enableStaticNat + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+       
                                    "<a style='margin-top: 50px;' onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                        } else {
                             html = "<a onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                        }
                        return html;
                                                
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        if (panelZone === null || panelZone === "") {
            zoneId = "All";
        } else {
            zoneId = panelZone;
        }
        
        var userIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/ipAddress/publicIp/"
        });
        userIPAddressRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("userPublicIpList").innerHTML = "";
                dojo.byId("noPublicIpAddressMessageBox").style.display = "block";
            } else {
                dojo.byId("noPublicIpAddressMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat, zoneType:resultData.zoneType},
//                        supportedService: {ip: resultData.ip, zoneType:resultData.zoneType, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat},
                        zone: resultData.zone,
                        vm: resultData.vm ? resultData.vm : "",
                        network: resultData.network ? resultData.network : "Default",
                        networkType: resultData.networkType ? resultData.networkType : "Shared",
                        action: {id: resultData.id, networkId:resultData.networkId, zoneType:resultData.zoneType, ip: resultData.ip, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat}
                    });
                });
                dojo.byId("userPublicIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userPublicIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
        
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.publicIPLimit !== "-1") {                   
                    if((limitData.publicIPUsed >= limitData.publicIPLimit)) {
                        dojo.byId("publicIPLimitReachedMsg").style.display = "block";
                        dojo.query("#networkAccquireIpButtom").toggleClass("hide_text", true);                                           
                    } else {
                        dojo.byId("publicIPLimitReachedMsg").style.display = "none";
                        dojo.query("#networkAccquireIpButtom").removeClass("hide_text", true);    
                    }
                } else {
                    dojo.byId("publicIPLimitReachedMsg").style.display = "none";
                    dojo.query("#networkAccquireIpButtom").removeClass("hide_text", true);
                }
            });
        });

    },
    loadVPCMenu : function (currentID) {
        CurrentInstanceInfo.showVPCPage();
        core.router.go("#/user/vpc/viewIp/" + currentID)        
    },
    'populateVPCIPValues': function() {

        dojo.byId("userVpcIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if ((data.isSourceNat === true || data.isSourceNat === "true") && data.zoneType === "Advanced") {
                            return "<a onclick='PublicIpInfo.loadVPCMenu(\"" + data.id + "\")' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && data.zoneType === "Advanced") {
                            return "<a onclick='PublicIpInfo.loadVPCMenu(\"" + data.id + "\")' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
                            return "<a onclick='PublicIpInfo.loadVPCMenu(\"" + data.id + "\")' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        } else {
                            return  data.ip;
                        }
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vpcName, 'field': 'vpc', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.tiers, 'field': 'network', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vm, 'field': 'vm', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
//                        if((data.isSourceNat === true || data.isSourceNat === "true") && (data.isStaticNat === false || data.isStaticNat === "false") && data.zoneType === "Advanced") {
//                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></a></li>"+
//                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
////                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
//                                    "<a href='#/user/network/portForwarding/" + data.id + "' class='offset1' title='" + translator.common.portForwarding + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/port_foward_icon.png'></img></a></li>";
//                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && (data.isSourceNat === false || data.isSourceNat === "false")  && data.zoneType === "Advanced") {
//                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></a></li>"+
//                                    "<a onclick='PublicIpInfo.disableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.disableStaticNat + "'><img style='width: 17px; height: 17px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+
//                                    "<a onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
//                                    
//                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
//                            html = "<a href='#/user/network/firewall/" + data.id + "' class='offset1' title='" + translator.common.firewall.name + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/cloud_firewall.png'></img></i></a></li>"+
//                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
////                                    "<a href='#/user/network/loadBalancing/" + data.id + "' class='offset1' title='" + translator.common.loadBalancing + "'><img src='css/theme/fog-classic/images/service-submenu-icons/load_balance_icon.png'></img></a></li>"+
//                                    "<a href='#/user/network/portForwarding/" + data.id + "' class='offset1' title='" + translator.common.portForwarding + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/service-submenu-icons/port_foward_icon.png'></img></a></li>"+
//                                    "<a onclick='PublicIpInfo.enableStaticNatIPShow(\"" + data.id + "\", \"" + data.networkId +"\")' class='offset1' title='" + translator.common.enableStaticNat + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+       
//                                    "<a style='margin-top: 50px;' onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
//                        } else {
//                             html = "<a onclick='PublicIpInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.ip.releaseIP + "'><img style='width: 17px; height: 17px;' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
//                        }
                        return html;
                                                
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        var userIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/ipAddress/publicIp/vpc/"
        });
        var zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });        
        if (panelZone === null || panelZone === "" || panelZone === "All") {            
            zoneId = "All";            
            userIPAddressRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
                if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {                
                    dojo.byId("userVpcIpList").innerHTML = "";
                    dojo.byId("noVpcIpAddressMessageBox").style.display = "block";
                    dojo.byId("noVPCMsgInfo").innerHTML = translator.common.noVPCMsg;
                } else {                    
                    dojo.byId("noVpcIpAddressMessageBox").style.display = "none";
                    dojo.byId("noVPCMsgInfo").innerHTML = translator.common.noVPCMsg;
                    dojo.forEach(data, function(resultData) {
                        dataList.newItem({
                            id: resultData.referenceId,
                            ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat, zoneType:resultData.zoneType},
                            zone: resultData.zone,
                            vm: resultData.vm ? resultData.vm : "",
                            vpc: resultData.vpc,
                            network: resultData.network,
                            networkType: resultData.networkType ? resultData.networkType : "Shared",
                            action: {id: resultData.id, networkId:resultData.networkId, zoneType:resultData.zoneType, ip: resultData.ip, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat}
                        });
                    });
                    dojo.byId("userVpcIpList").innerHTML = "";
                    var dataGrid = new EnhancedGrid({
                        "class": "span12",
                        store: dataList,
                        structure: dataLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    dataGrid.placeAt("userVpcIpList");
                    dataGrid.startup();
                    dataGrid.update();
                }
            });
        } else {
            zoneId = panelZone;
            zoneRestStore.get(zoneId).then(function (zoneData) {
                if(zoneData.networkType === "Basic") {             
                    dojo.byId("userVpcIpList").innerHTML = "";
                    dojo.byId("noVPCMsgInfo").innerHTML = translator.common.noVPCAvalilableMsg;
                    dojo.byId("noVpcIpAddressMessageBox").style.display = "block";                            
                } else {
                    dojo.byId("noVpcIpAddressMessageBox").style.display = "none";
                    dojo.byId("noVPCMsgInfo").innerHTML = translator.common.noVPCMsg;
                    
                    userIPAddressRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
                        if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {                
                            dojo.byId("userVpcIpList").innerHTML = "";
                            dojo.byId("noVpcIpAddressMessageBox").style.display = "block";
                            dojo.byId("noVPCMsgInfo").innerHTML = translator.common.noVPCMsg;
                        } else {
                            dojo.byId("noVpcIpAddressMessageBox").style.display = "none";
                            dojo.byId("noVPCMsgInfo").innerHTML = translator.common.noVPCMsg;
                            dojo.forEach(data, function(resultData) {
                                dataList.newItem({
                                    id: resultData.referenceId,
                                    ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat, zoneType:resultData.zoneType},
                                    zone: resultData.zone,
                                    vm: resultData.vm ? resultData.vm : "",
                                    vpc: resultData.vpc,
                                    network: resultData.network,
                                    networkType: resultData.networkType ? resultData.networkType : "Shared",
                                    action: {id: resultData.id, networkId:resultData.networkId, zoneType:resultData.zoneType, ip: resultData.ip, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat}
                                });
                            });
                            dojo.byId("userVpcIpList").innerHTML = "";
                            var dataGrid = new EnhancedGrid({
                                "class": "span12",
                                store: dataList,
                                structure: dataLayout,
                                autoHeight: true,
                                plugins: core.getGridConfig().plugins
                            });
                            dataGrid.placeAt("userVpcIpList");
                            dataGrid.startup();
                            dataGrid.update();
                        }
                    });                    
                }
            });
        }                        
    }
};    
    
    
var IpInfo = {
    'init' : function() {        
        var currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"
        });        
        currentAccRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if(el.accountType === "TRIAL") {
                    dojo.byId("trialIpMsg").style.display = "block";
                    dojo.byId("ipViewPageDiv").style.display = "none";                    
                }          
            });
        });               
        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id:'ALL', name: translator.common.message.selectInstance}]
        }; 
        var instanceList = new ItemFileWriteStore({data: instanceOptions});        
        
        var networkOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id:'ALL', name: "Select Network"}]
        }; 
        var networkOptionList = new ItemFileWriteStore({data: networkOptions});        
        
        var virtualMachineRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });                  
        var virtualMachineCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/count"
        });
        var networkRestStore = new JsonRest({
            target: core.getContextPath()+"/api/network/"
        }); 
        var zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        }); 
        var currentZoneID = dojo.byId("selectedANZoneID").value;
        networkRestStore.query({zoneReferenceId: currentZoneID}).then(function (data) {
            dojo.forEach(data, function (el) {
                networkOptionList.newItem({id: el.referenceId, name: el.name})
            })
//            var networkWidget = new Select({
//                id: "ipNetworkWidget",  
//                store : networkOptionList,
//                sortByLabel: false,
//                onChange : function () {                    
//                }
//            }, "ipNetworkList");
//            networkWidget.startup();   
        })
        
        if(currentZoneID == "All" || currentZoneID == "undefined" || currentZoneID == "") {
            virtualMachineRestStore.query().then(function(data) {
                if(data.length == 0) {
                    dojo.byId("vmBlock").style.display = "none";
                    dojo.byId("createVmIpMsg").style.display = "block";
                }
                dojo.forEach(data, function(el) {  
                    if(el.state == "Running" || el.state == "Stopped") {
                        instanceList.newItem({                    
                            id: el.referenceId,
                            name: el.name,
                            currency: el.currency,
                            misceCost : el.ipMiscelCost
                        });  
                    }
                });
            });
        } else {
            virtualMachineCountRestStore.query({zoneReferenceId: currentZoneID}).then(function (data) {
                if(data[0].instanceData.length == 0) {
                    dojo.byId("vmBlock").style.display = "none";
                    dojo.byId("createVmIpMsg").style.display = "block";
                } 
                dojo.forEach(data[0].instanceData, function (el) {
                    if(el.state == "Running" || el.state == "Stopped") {
                        instanceList.newItem({                    
                            id: el.referenceId,
                            name: el.name,
                            currency: el.currency,
                            misceCost : el.ipMiscelCost
                        });  
                    }
                })
            })
            zoneRestStore.get(currentZoneID).then(function (data) {
                if(data.networkType == "Advanced") {
                    dojo.query("#ipManagementNetworkList").removeClass("hide_text", true);     
                    dojo.query("#ipManagementVMList").toggleClass("hide_text", true);                         
                } else {
                   dojo.query("#ipManagementNetworkList").toggleClass("hide_text", true);     
                   dojo.query("#ipManagementVMList").removeClass("hide_text", true);              
                }
            })
        }                          
        var instanceWidget = new Select({
            id: "ipInstanceWidget",  
            store : instanceList,
            sortByLabel: false,
            onChange : function () {
                this.store.fetch({query: {id: this.value},
                onItem: function(item) { 
                    dojo.byId("ipInfoMiscelCost").innerHTML = "*" + item.currency + " "+item.misceCost + "/" + translator.common.month;
                }
            }); 
        }
        }, "ipInstanceList");
        instanceWidget.startup();    
        zoneRestStore.get()
    },    
    showIPManagement : function () {
        var formElements = dojo.query("#ipManageTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var ipType = dijit.byId(checkedRadioId).value;   
        var zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });
        var currentZoneID = dojo.byId("selectedANZoneID").value;
        if(currentZoneID != "All") {
            
        }
    },
    showCondition : function () {
         var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/public/termsAndCondition"
        });

        configRestStore.query().then(function(resultData) {
            dojo.byId("ipInfoContentArea").innerHTML = resultData.value;                   
        });
        dojo.style(dijit.byId("ipInfoContentDialog").closeButtonNode,"display","none");
        dijit.byId("vmAcquireIpDialog").hide();
        dijit.byId("ipInfoContentDialog").show();
    },
    cancelConditionBox : function () {
        dijit.byId("ipInfoContentDialog").hide();
        dijit.byId("vmAcquireIpDialog").show();
    },
    'populateValues': function(data) {          
        if(dijit.byId("vmIpGridWidget")) {
            dijit.byId("vmIpGridWidget").destroyRecursive();                    
        }     
        var vmId;         
        if(data == null || data == "null") {
            vmId = dijit.byId("ipInstanceWidget").value;
        } else {
            vmId  = data;
        }
        var ipListTicketStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/ip/list/"
        });        
        dojo.byId("vmIpGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p> "+translator.common.loader.loadingPleaseWait+"</p>"; 
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [
            [
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '20%', datatype:"string",              
                    autoComplete: true,    
                    dataTypeArgs: {
                        autoComplete: false
                    }
                },
                {'name': translator.common.instance.name, 'field': 'instance', 'width': '10%', datatype:"string",                    
                    autoComplete: true,                   
                    dataTypeArgs: {
                        autoComplete: false
                    }
                },
                {'name': translator.common.action, 'field': 'action', 'width': '15%', 'formatter' : function(data) {                                               
                        return new dijit.form.Button({label: translator.common.ip.releaseIP, 
                            "class":"defaultbtn", onClick: function () {
                                IpInfo.showReleaseIp(data);
                            }
                        });                                               
                    }
                }
            ]
        ];        
        ipListTicketStore.get(vmId).then(function(data) {            
            dojo.byId("vmIpGrid").innerHTML = "";            
            if(data.length == 0) {
                dojo.byId("vmIpGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'><i class='icon-exclamation-sign'></i>"+translator.common.ip.noSecondaryIP+"</div>";
            } else {
                dojo.forEach(data, function(el) {
                    gridDataList.newItem({
                        id: el.id,
                        ip: el.ipAddress,
                        instance: el.virtualMachine,
                        action : el.id
                    });
                });
                var ipGrid = new EnhancedGrid({
                    id: 'vmIpGridWidget',
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                ipGrid.placeAt("vmIpGrid");
                ipGrid.startup();
            }             
        });
    },
    'vmAcquireIp': function() {            
        var currentVmId =  dijit.byId("ipInstanceWidget").value;   
        var agrementChecked =  dijit.byId("serviceIPAgreement").checked;
        if(currentVmId == "ALL") {
            registry.byId("userToaster").setContent(translator.common.instance.selectInstance,"error");
            registry.byId("userToaster").show();
        } else if(agrementChecked == true) {
            dijit.byId("vmAcquireIpDialog").hide(); 
            dijit.byId("vmIpLoader").show(); 
            var instanceConsoleStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/ip/acquire/"         
            });
            instanceConsoleStore.add({
                vmId:  currentVmId
            }).then(function(result) {
                dojo.forEach(result, function(resultData) {
                    if(resultData.result == "OK") {
                        var acquireIpJobStatus = setInterval(function(){IpInfo.vmAcquireIpJob(resultData.jobId, acquireIpJobStatus, resultData.vmId);},3000); 
                    } else {
                        dijit.byId("vmIpLoader").hide();  
                        registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                        registry.byId("userToaster").show();
                    }
                });
            });
        } else {
            dojo.byId("ipInfoConditionExceptionMsg").style.display = "block";
        }
    }, 
    'vmAcquireIpJob': function(jobId, attachIsoJobStatus, vmId) {        
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/ip/acquire/job/"
        }); 
        jobStore.add({
            jobId : jobId,
            vmId : vmId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.acquire,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("vmIpLoader").hide();
                    IpInfo.populateValues("ALL");                    
                } else if(resultData.jobResult == "Pending") {
                } else  if(resultData.jobResult == "FAILED") {
                    registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmIpLoader").hide();
                }
            });
        });
    },  
    'revokeIp' :function() {         
        var ipId = dojo.byId("currentIpId").value;
        dijit.byId("vmReleaseIpDialog").hide(); 
        dijit.byId("vmIpLoader").show(); 
        var instanceConsoleStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/ip/remove"         
        });
        instanceConsoleStore.add({
            ipId: ipId
        }).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if(resultData.result == "OK") {
                    var removeIpJobStatus = setInterval(function(){IpInfo.revokeIpJob(resultData.jobId, removeIpJobStatus);},3000); 
                } else {
                    dijit.byId("vmIpLoader").hide();  
                    registry.byId("userToaster").setContent(translator.common.ip.cannotReleaseIP,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },    
    'revokeIpJob' :function(jobId, removeIpJobStatus) { 
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/ip/remove/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(removeIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.ipReleased,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("vmIpLoader").hide();
                    IpInfo.populateValues("ALL");                    
                } else if(resultData.jobResult == "Pending") {
                } else  if(resultData.jobResult == "FAILED") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.cannotReleaseIP,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmIpLoader").hide();
                }
            });
        });    
    },
    showAcquireIp :function() {       
        dojo.byId("ipInfoConditionExceptionMsg").style.display = "none";
        dijit.byId("serviceIPAgreement").set("checked", false);
        var currentVmId =  dijit.byId("ipInstanceWidget").value;            
        if(currentVmId == "ALL") {
            registry.byId("userToaster").setContent(translator.common.instance.selectInstance,"error");
            registry.byId("userToaster").show();
        } else {
            dijit.byId("vmAcquireIpDialog").show(); 
        }        
    },
    closeAcquireIpDialog:function() {
        dijit.byId("vmAcquireIpDialog").hide(); 
    },
    showReleaseIp :function(currentIpId) {
        dojo.byId("currentIpId").value = currentIpId;  
        dijit.byId("vmReleaseIpDialog").show();          
    },
    closeReleaseIpDialog:function() {
        dijit.byId("vmReleaseIpDialog").hide(); 
    }
};

var AddSSHKey = {
    'init' : function() {                              
    },
    'sshKeyDelete' : function() {          
        var sshKeyStore = new JsonRest({
            target: core.getContextPath()+"/api/account/SSHKey/delete"
        });        
        sshKeyStore.add({
            sshId:  dojo.byId("sshId").value
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("privateKeyDialog").hide();
                    registry.byId("userToaster").setContent(translator.user.SSHKey.SSHKeyDeleted, "message");
                    registry.byId("userToaster").show();
                    AddSSHKey.populateValues();
                } else if(resultData.result == "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                } else {
                    registry.byId("userToaster").setContent(translator.user.SSHKey.SSHKeyDeletedError, "error");
                    registry.byId("userToaster").show();
                }
            });                
        });        
    }, 
    'populateValues': function() {      
        var sshKeyStore = new JsonRest({
            target: core.getContextPath()+"/api/account/SSHKey/list"
        });                
        dojo.byId("sshKeyGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";         
        
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', datatype:"string",  autoComplete: true,'formatter': function(data) {                                                 
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.user.SSHKey.fingerPrint , 'field': 'fingerprint', 'width': '600px', datatype:"string",  autoComplete: true},
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter' : function(data) {
                        if(data.isDefault != true) {
                            return new dijit.form.Button({label: translator.common.view, 
                                "class":"defaultbtn", onClick: function () {
                                    AddSSHKey.viewSSHKey(data);
                                } 
                            });
                        }                
                    }}
            ]
        ];        
        sshKeyStore.query().then(function(data) {
            dojo.forEach(data, function(el) {              
                gridDataList.newItem({
                    'id': el.id,
                    'name': el.name,
                    'fingerprint': el.fingerprint,
                    'action': {'id':el.id, 'name': el.name, 'isDefault': el.isDefault, 'key': el.privateKey}
                });                
                dojo.byId("sshKeyGrid").innerHTML = "";                                
                var depGrid = new EnhancedGrid({
                    "class" : "span12",
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });                
                depGrid.placeAt("sshKeyGrid");
                depGrid.startup();                
            });
        });         
    },
    'createSShKey' : function() {                        
        var sshKeyStore = new JsonRest({
            target: core.getContextPath()+"/api/account/SSHKey/create"
        });
        
        var pageNode = dojo.byId("sshKeyPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dojo.byId('createSSHKeyBtnDiv').style.display = 'none';
            dojo.byId('sshKeyLoader').style.display =  'block';
            sshKeyStore.add({
                name:  dijit.byId("sshKeyName").getValue()
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.byId('createSSHKeyBtnDiv').style.display = 'block';
                    dojo.byId('sshKeyLoader').style.display =  'none';
                    if(resultData.result == "OK") {
                        registry.byId("userToaster").setContent(translator.user.SSHKey.sshKeyCreated, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("sshKeyForm").reset();
                        AddSSHKey.populateValues();
                    } else if(resultData.result == "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                    } else {
                        registry.byId("userToaster").setContent(translator.user.SSHKey.cannotCreateSSHKey, "error");
                        registry.byId("userToaster").show();
                    }                    
                });                
            });
        } 
    },
    'cancel': function() {  
        dijit.byId("sshKeyForm").reset();
    },
    'viewSSHKey': function(data) {          
        document.getElementById("sshKeyDownload").setAttribute("href", ""+core.getBaseURL()+"/pdf/getSSHKey?keyId="+data.id+"");        
        dojo.byId("sshId").value = data.id;
        dojo.byId("sshPrivateKey").value = data.key;
        dijit.byId("privateKeyDialog").show();
    }
};
var UserVMBandwidthInfo = {
    'init': function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });         
        this._bandwidthRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/bandwidth"
        }); 
        this._instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });
        this._instanceCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/count"
        });
    },
    'populateValues': function() {        
        if(dijit.byId("userBandDataGrid")) {
            dijit.byId("userBandDataGrid").destroyRecursive();                         
        }          
        var vmBandData = {
            items: []
        }; 
        var vmBandDataList = new ItemFileWriteStore({data: vmBandData}); 
        var vmBandDataLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name':  translator.common.account.name, 'field': 'account', 'width': '20%', datatype:"string",  autoComplete: true},
                {'name': translator.common.instance.vmName, 'field': 'name', 'width': '20%', datatype:"string",  autoComplete: true},
                {'name': translator.common.message.usedBandwidth, 'field': 'totalBand', 'width': '10%', datatype:"string",  autoComplete: true},
                {'name': translator.common.message.excedded, 'field': 'exBand', 'width': '10%', datatype:"string",  autoComplete: true, 'formatter': function(data) {                                                                      
                        return  "<span class='redColor'>" + data + "</span>";
                    }
                },     
                {'name': translator.common.cost, 'field': 'exBandCost', 'width': '10%', datatype:"string",  autoComplete: true, 'formatter': function(data) {                                                                      
                        return  "<span class='orangeColor'>" + data + "</span>";
                    }
                }  
            ]
        ];                
        dojo.byId("userBandwidthInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";               
        this._bandwidthRestStore.query({'zoneReferenceId':"ALL"}).then(function(data) {            
            dojo.forEach(data, function(el) {
                dojo.byId("userTotalBandwidth").innerHTML =  Math.round((el.totalBandwidth) * 100) / 100;
                dojo.byId("userExceededBandwidth").innerHTML =  Math.round((el.totalExBandwidth) * 100) / 100;
                dojo.byId("userTotalBandwidthCost").innerHTML =  Math.round((el.totalBandwidthCost) * 100) / 100 ;
                dojo.byId("userBandwidthCurrency").innerHTML =  el.currency;                              
                if(el.accountType == "TRIAL") {
                    dojo.byId("trialTotalBandwidth").style.display = "block";
                    dojo.byId("trialTotalBandwidth").innerHTML = "*" + translator.common.trialLimit + el.bandLimit + translator.common.gb;
                } else {
                    dojo.byId("trialTotalBandwidth").style.display = "none";
                }                
                if(el.vmBandItems.length == 0 || el.vmBandItems == undefined || el.vmBandItems == "undefined" || el.vmBandItems == ''  || el.vmBandItems == 'null' || el.vmBandItems == null) {
                    dojo.byId("noBandWidthMessageBox").style.display = "block";
                    dojo.byId("userBandwidthInfo").innerHTML  = "";
                } else {
                    dojo.forEach(el.vmBandItems, function(vmBand) {
                        vmBandDataList.newItem({
                            id:vmBand.vmId,
                            name:vmBand.vmName,
                            account: vmBand.account, 
                            exBand: Math.round((vmBand.exBand) * 100) / 100,
                            exBandCost : Math.round((vmBand.bandCost) * 100) / 100,
                            totalBand : Math.round((vmBand.totalBand) * 100) / 100
                        });
                    });
                    dojo.byId("userBandwidthInfo").innerHTML  = "";
                    var bandDataGrid = new EnhancedGrid({                 
                        id: 'userBandDataGrid',
                        store: vmBandDataList,
                        structure: vmBandDataLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    bandDataGrid.placeAt("userBandwidthInfo");
                    bandDataGrid.startup(); 
                }
            });
        });        
        if(dijit.byId("userBandZoneWidget")) {
            dijit.byId("userBandZoneWidget").destroyRecursive();                         
        }          
        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.options.allZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });                                                                          
        var zoneWidget = new Select ({
            id: "userBandZoneWidget",
            name: "zoneWidgets",
            sortByLabel: false,
            value: "option",        
            store: zoneList,
            onChange: function() {
                UserVMBandwidthInfo.getBandwidthByZone(this);
            }
        }, "userBandZoneList"); 
    },
    'getBandwidthByZone': function(currentZone) {
        dojo.byId("noBandWidthMessageBox").style.display = "none";
        if(dijit.byId("userBandDataGrid")) {            
            dijit.byId("userBandDataGrid").destroyRecursive();                         
        }          
        dojo.byId("userBandwidthInfo").innerHTML  = "";
        dojo.byId("userBandwidthInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";          
        this._bandwidthRestStore.query({'zoneReferenceId':currentZone.value}).then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("userTotalBandwidth").innerHTML =  Math.round((el.totalBandwidth) * 100) / 100;
                dojo.byId("userExceededBandwidth").innerHTML =  Math.round((el.totalExBandwidth) * 100) / 100;
                dojo.byId("userTotalBandwidthCost").innerHTML =  Math.round((el.totalBandwidthCost) * 100) / 100 ;
                dojo.byId("userBandwidthCurrency").innerHTML =  el.currency;
                if(el.accountType == "TRIAL") {
                    dojo.byId("trialTotalBandwidth").style.display = "block";
                    dojo.byId("trialTotalBandwidth").innerHTML = "*" + translator.common.trialLimit + el.bandLimit + translator.common.gb;
                } else {
                   dojo.byId("trialTotalBandwidth").style.display = "none";
                }                             
                if(el.vmBandItems.length == 0 || el.vmBandItems == undefined || el.vmBandItems == 'undefined' || el.vmBandItems == ''  || el.vmBandItems == 'null' || el.vmBandItems == null) {
                    dojo.byId("noBandWidthMessageBox").style.display = "block";
                    dojo.byId("userBandwidthInfo").innerHTML  = "";                    
                } else {
                    var vm2BandData = {
                        items: []
                    }; 
                    var vmBand2DataList = new ItemFileWriteStore({data: vm2BandData});  
                    var vmBand2DataLayout = [
                        [
                            {'field': 'id', 'hidden': 'true'},
                            {'name': translator.common.account.name, 'field': 'account', 'width': '20%'},
                            {'name': translator.common.name, 'field': 'name', 'width': '20%'},
                            {'name': translator.common.message.totalBandwidth + "("+ translator.common.gb + ")", 'field': 'totalBand', 'width': '10%'},
                            {'name': translator.common.message.excedded, 'field': 'exBand', 'width': '10%', 'formatter': function(data) {                                                                      
                                    return  "<span class='redColor'>" + data + "</span>";
                                }
                            },      
                            {'name': translator.common.cost, 'field': 'exBandCost', 'width': '10%', 'formatter': function(data) {                                                                      
                                    return  "<span class='orangeColor'>" + data + "</span>";
                                }
                            }    
                        ]
                    ];                     
                    dojo.forEach(el.vmBandItems, function(vmBand) {
                        vmBand2DataList.newItem({
                            id:vmBand.vmId,
                            name:vmBand.vmName,
                            account: vmBand.account, 
                            exBand: Math.round((vmBand.exBand) * 100) / 100,
                            exBandCost : Math.round((vmBand.bandCost) * 100) / 100,
                            totalBand : Math.round((vmBand.totalBand) * 100) / 100
                        });
                    });
                     
                    dojo.byId("userBandwidthInfo").innerHTML  = "";
                    var band2DataGrid = new EnhancedGrid({                 
                         id: 'userBandDataGrid',
                         store: vmBand2DataList,
                         structure: vmBand2DataLayout,
                         autoHeight: true,
                         plugins: core.getGridConfig().plugins
                    });
                    band2DataGrid.placeAt("userBandwidthInfo");
                    band2DataGrid.startup(); 
                }                              
            });
        });
    }
};

window.AddSSHKey = AddSSHKey;
window.UserVMBandwidthInfo = UserVMBandwidthInfo;
