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
    "dojo/currency",
    "dojox/form/CheckedMultiSelect",
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
    "List/ListItem",
], function(dojo, i18n, translator, dijit, JsonRest, registry, FilteringSelect, Select,ItemFileWriteStore, DataGrid,
            EnhancedGrid, HorizontalSlider, domConstruct, Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, 
            Highlight, connect, Menu, MenuItem,ComboButton, DropDownButton, DropDownMenu, localeCurrency, CheckedMultiSelect) {
        window.translator = translator;
        window.JsonRest = JsonRest;    
        window.Magnify = Magnify;
        window.registry = registry;
        window.Menu = Menu;
        window.MenuItem = MenuItem;
        window.DropDownButton = DropDownButton;
        window.DropDownMenu = DropDownMenu;
        window.CheckedMultiSelect = CheckedMultiSelect;
        window.ComboButton = ComboButton;
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
        window.localeCurrency = localeCurrency;
        window.GraphUpdateInterval  = "";
        controller({ 
            name:"server",
            module: "user",
            filePath: "/js/app/user/server.js",
            layout: {
                name: "",
                containerId: "content"
            },
            scaffold: false
        },
        {   
            "index": action(function() {                
                ListServer.init();            
            }),
            "list": action(function() {
                
                if(dijit.byId("startDialog")) {
                    dijit.byId("startDialog").destroyRecursive();                                            
                }    
                if(dijit.byId("softRebootDialog")) {
                    dijit.byId("softRebootDialog").destroyRecursive();                                            
                }    
                if(dijit.byId("hardRebootDialog")) {
                    dijit.byId("hardRebootDialog").destroyRecursive();                                            
                } 
                if(dijit.byId("deleteDialog")) {
                    dijit.byId("deleteDialog").destroyRecursive();                                            
                }      
                if(dijit.byId("vmActionLoader")) {
                    dijit.byId("vmActionLoader").destroyRecursive();                                            
                }      
                if(dijit.byId("stopDialog")) {
                    dijit.byId("stopDialog").destroyRecursive();                                            
                }      
                if(dijit.byId("currentVMSnapshotDialog")) {
                    dijit.byId("currentVMSnapshotDialog").destroyRecursive();                                            
                }     
                if(dijit.byId("currentVMSnapshotForm")) {
                    dijit.byId("currentVMSnapshotForm").destroyRecursive();                                            
                }  
                if(dijit.byId("resizeInstancedialog")) {
                    dijit.byId("resizeInstancedialog").destroyRecursive();                                            
                } 
                if(dijit.byId("resizeInstanceForm")) {
                    dijit.byId("resizeInstanceForm").destroyRecursive();                                            
                } 
                if(dijit.byId("rebuildInstanceDialog")) {
                    dijit.byId("rebuildInstanceDialog").destroyRecursive();                                            
                } 
                if(dijit.byId("rebuildInstanceForm")) {
                    dijit.byId("rebuildInstanceForm").destroyRecursive();                                            
                } 
                if(dijit.byId("instanceAssociateDialog")) {
                    dijit.byId("instanceAssociateDialog").destroyRecursive();                                            
                } 
                if(dijit.byId("instanceCreateFloatingIpDialog")) {
                    dijit.byId("instanceCreateFloatingIpDialog").destroyRecursive();                                            
                } 
                if(dijit.byId("instanceDisassociateFloatingIpAlert")) {
                    dijit.byId("instanceDisassociateFloatingIpAlert").destroyRecursive();                                            
                } 
                if(dijit.byId("instanceFloatingIpLoader")) {
                    dijit.byId("instanceFloatingIpLoader").destroyRecursive();                                            
                } 
                if(dijit.byId("instanceDisAssociateDialog")) {
                    dijit.byId("instanceDisAssociateDialog").destroyRecursive();                                            
                }  
                if(dijit.byId("vmListmonitoringDisableAlert")) {
                    dijit.byId("vmListmonitoringDisableAlert").destroyRecursive();                                            
                } 
                core.ui.loadTemplate("serverList", core.ui.getContentId());    
                ListServer.populateValues();
                ListServer.getFlavorList();
                ListServer.getImageList();
                InstanceFloatingIp.externalNetworkList();
//                InstanceFloatingIp.portList();
                InstanceFloatingIp.ipAddressList();
//                InstanceFloatingIp.disAssociatePortList();

            }),
            "add": action(function(id) {

                if(dijit.byId("createVMForm")) {
                    dijit.byId("createVMForm").destroyRecursive();                                            
                }  

                if(dijit.byId("createVMLoading")) {               
                    dijit.byId("createVMLoading").destroyRecursive();          
                }
                if(dijit.byId("vmSummaryPage")) {               
                    dijit.byId("vmSummaryPage").destroyRecursive();          
                }

                core.ui.loadTemplate("createVM", core.ui.getContentId()); 
                AddServer.init();
                AddServer.populateValues(id);

            }),
            "viewServer" : action(function(id) {

                if(dijit.byId("instaceInfoLoader")) {               
                    dijit.byId("instaceInfoLoader").destroyRecursive();          
                } 

                if(dijit.byId("startVMButton")) {               
                    dijit.byId("startVMButton").destroyRecursive();          
                }          

                if(dijit.byId("stop")) {               
                    dijit.byId("stop").destroyRecursive();          
                }  

                if(dijit.byId("reboot")) {               
                    dijit.byId("reboot").destroyRecursive();          
                } 

                if(dijit.byId("attachVolumeFormList")) {               
                    dijit.byId("attachVolumeFormList").destroyRecursive();          
                }

                if(dijit.byId("attachVolumeDialog")) {               
                    dijit.byId("attachVolumeDialog").destroyRecursive();          
                } 

                if(dijit.byId("AttachedVolumeActionLoader")) {               
                    dijit.byId("AttachedVolumeActionLoader").destroyRecursive();          
                } 

                if(dijit.byId("detachVolumeAlert")) {               
                    dijit.byId("detachVolumeAlert").destroyRecursive();          
                }

                if(dijit.byId("createVMSnapshotDialog")) {               
                    dijit.byId("createVMSnapshotDialog").destroyRecursive();          
                } 

                if(dijit.byId("currentVMSnapshotForm")) {               
                    dijit.byId("currentVMSnapshotForm").destroyRecursive();          
                } 

                if(dijit.byId("vmSnapshotLoader")) {               
                    dijit.byId("vmSnapshotLoader").destroyRecursive();          
                }  
                if(dijit.byId("listNicForm")) {               
                    dijit.byId("listNicForm").destroyRecursive();          
                }
                if(dijit.byId("vmInfoTabContainer")) {               
                    dijit.byId("vmInfoTabContainer").destroyRecursive();          
                }
                if(dijit.byId("vmMonitoringTab")) {               
                    dijit.byId("vmMonitoringTab").destroyRecursive();          
                } 
                if(dijit.byId("listMonitorAlertForm")) {               
                    dijit.byId("listMonitorAlertForm").destroyRecursive();          
                }

                if(dijit.byId("graphMonitoringTab")) {               
                    dijit.byId("graphMonitoringTab").destroyRecursive();          
                }
                if(dijit.byId("graphFilterForm")) {               
                    dijit.byId("graphFilterForm").destroyRecursive();          
                }            
                if(dijit.byId("graphZoomDialogBox")) {               
                    dijit.byId("graphZoomDialogBox").destroyRecursive();          
                } 
                if(dijit.byId("monitoringDisableAlert")) {               
                    dijit.byId("monitoringDisableAlert").destroyRecursive();          
                }

                if(dijit.byId("availablity")) {               
                    dijit.byId("availablity").destroyRecursive();          
                }

                if(dijit.byId("cpu")) {               
                    dijit.byId("cpu").destroyRecursive();          
                }
                if(dijit.byId("memory")) {               
                    dijit.byId("memory").destroyRecursive();          
                }
                if(dijit.byId("io")) {               
                    dijit.byId("io").destroyRecursive();          
                }
                if(dijit.byId("throughput")) {               
                    dijit.byId("throughput").destroyRecursive();          
                }         
                if(dijit.byId("deleteAlarmDialog")) {               
                    dijit.byId("deleteAlarmDialog").destroyRecursive();          
                } 
                if(dijit.byId("disableAlarmDialog")) {               
                    dijit.byId("disableAlarmDialog").destroyRecursive();          
                } 

                if(dijit.byId("vmInfoStopDialog")) {               
                    dijit.byId("vmInfoStopDialog").destroyRecursive();          
                } 
                if(dijit.byId("vmInfoStartDialog")) {               
                    dijit.byId("vmInfoStartDialog").destroyRecursive();          
                } 
                if(dijit.byId("vmInfoHardRebootDialog")) {               
                    dijit.byId("vmInfoHardRebootDialog").destroyRecursive();          
                } 
                if(dijit.byId("vmInfoDeleteDialog")) {               
                    dijit.byId("vmInfoDeleteDialog").destroyRecursive();          
                } 
                if(dijit.byId("vmInfoActionLoader")) {               
                    dijit.byId("vmInfoActionLoader").destroyRecursive();          
                } 

                core.ui.loadTemplate("instanceInfoPage", core.ui.getContentId()); 
                ViewServer.init(id);
                 setTimeout(function () {                                      
                     IPServices.populateValues();                      
                 },1000);
            })        
        });
}); 

var IPServices =  {
    populateValues : function () {        
        var IPRestStore = new JsonRest({
            target: core.getContextPath()+"/api/monitoring/ipServices"
        });  
        
        dojo.byId("ipServiceGridNode").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";         
        
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [
            [
                {'name': translator.common.events, 'field': 'events', 'width': '50px', datatype:"string",  autoComplete: true,'formatter': function(data) {                                                
                        if(data === "clear") {
                            return "<div class='severity-icon-small clear'></div>";                            
                        } else if(data === "critical") {
                            return "<div class='severity-icon-small critical'></div>";                             
                        } else if(data === "error") {
                            return "<div class='severity-icon-small error'></div>";                                     
                        } else if(data === "warning") {
                            return "<div class='severity-icon-small warning'></div>";                            
                        } else if(data === "info") {
                            return "<div class='severity-icon-small info'></div>";                                
                        } else if(data === "debug") {
                            return "<div class='severity-icon-small debug'></div>";                               
                        } else {
                            return "<div></div>";    
                        }                                                
                }},
                {'name':  translator.common.name , 'field': 'name', 'width': '100px', datatype:"string",  autoComplete: true},
                {'name': translator.common.protocol, 'field': 'protocol', 'width': '100px', datatype:"string",  autoComplete: true},
                {'name': translator.common.port, 'field': 'port', 'width': '100px', datatype:"string",  autoComplete: true},
                {'name': translator.common.ips, 'field': 'ips', 'width': '100px', datatype:"string",  autoComplete: true},
                {'name': translator.common.description, 'field': 'description', 'width': '100px', datatype:"string",  autoComplete: true},
                {'name': translator.common.monitored, 'field': 'monitored', 'width': '100%', datatype:"string",  autoComplete: true, 'formatter': function(data) {
                        var html = "";
                        if(Boolean(data) === true) {
                            html = "<span>" + translator.common.yes + "</span>";
                        } else {
                            html = "<span>" + translator.common.no + "</span>"
                        }
                        return html;
                    }
                }                                
            ]
        ];
        var deviceId = dojo.byId("instanceDeviceId").value;
//        var deviceId = "/zport/dmd/Devices/Server/Windows/devices/192.168.1.105";
        IPRestStore.query({deviceId: deviceId}).then(function(data) {
            if(data.length === 0 || data[0].name === "undefined" ) {
                dojo.byId("ipServiceGridNode").innerHTML = "";
                dojo.byId("noIPListMessageBox").style.display = "block";
            } else {
                dojo.forEach(data, function(el) {
                if(el.name) {                                        
                    dojo.byId("noIPListMessageBox").style.display = "none";                    
                    gridDataList.newItem({
                        'events': el.severity,
                        'name': el.name,
                        'protocol': el.protocol,
                        'port': el.port,
                        'ips' : el.ipAddresses,
                        'description' : el.description,
                        'monitored' : el.monitored                                                
                    });                
                    dojo.byId("ipServiceGridNode").innerHTML = ""; 
                    
                    if(dijit.byId("monitoringIPListGridWidget")) {               
                        dijit.byId("monitoringIPListGridWidget").destroyRecursive();          
                    }
        
                    dojo.byId("ipServiceGridNode").innerHTML = "";
                    var ipGrid = new EnhancedGrid({
                        id : "monitoringIPListGridWidget",
                        "class" : "span12",
                        store: gridDataList,
                        structure: gridLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });                
                    
                    ipGrid.placeAt("ipServiceGridNode");
                    ipGrid.startup();  
                } else {
                    dojo.byId("ipServiceGridNode").innerHTML = "";
                    dojo.byId("noIPListMessageBox").style.display = "block";
                }
                               
            });
            }
            
        });    
    }
};

var GraphInfo = {
    clearGraphSession : function () {
        if(window.GraphUpdateInterval) {
            clearInterval(window.GraphUpdateInterval);
        }               
    },
    showFirstGraph : function () {
        setTimeout(function () {
            var mainTab = dijit.byId("graphMonitoringTab"); //Tr
            var subIpTab = dijit.byId("availablity"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },100);
            var shutOffState = dojo.byId("shutOffState").value;
            if(shutOffState === "false") {
                dojo.byId("monitoringInstanceStopMsgDiv").style.display = "block";
                dojo.byId("monitoringMsgDiv").style.display = "none";
                dojo.byId("instanceCompleteMonitoringDiv").style.display = "none";
            } else if(shutOffState === "true") {
                dojo.byId("monitoringInstanceStopMsgDiv").style.display = "none";
                dojo.byId("monitoringMsgDiv").style.display = "none";
                dojo.byId("instanceCompleteMonitoringDiv").style.display = "none";
            } 
    },
    updateGraph : function (currentGraph) {
        var currentgraphName = currentGraph;
        window.GraphUpdateInterval =  setInterval(function () {
            GraphInfo.showGraph(currentgraphName);
        },300000);   
    },
    chnageGraphRange : function (currentGraphRange) {
        var tabContainer = dojo.byId("graphMonitoringTab");
        var panWidgets = dijit.registry.findWidgets(tabContainer);        
        dojo.forEach(panWidgets, function(el) {
            if(el.get("title") || el.get("title") !== "") {
                if(el.get("selected") === true) {
                    GraphInfo.showGraph(el.id);
                }                                
            }
        });
    },
    showGraph : function (graphType) { 
        
        var vmId = dojo.byId("instanceId").value;                       
        
        var graphRestStore = new JsonRest({
            target: core.getContextPath()+"/api/monitoring"
        });
        var graphImageTag = "";
        var graphTypeText = "";        
        if(graphType === "memory") {
            
            graphImageTag = dojo.byId("monitoringMemoryGraphImage");
            dijit.byId("graphZoomDialogBox").set("title", translator.common.memoryUtilGraph);
            graphTypeText = translator.common.memoryUtilGraph;
            
        } else if(graphType === "cpu") {
            
            graphImageTag = dojo.byId("monitoringCpuGraphImage");
            dijit.byId("graphZoomDialogBox").set("title", translator.common.cpuUtilGraph);
            graphTypeText = translator.common.cpuUtilGraph;
            
        } else if(graphType === "availablity") {
            
            graphImageTag = dojo.byId("monitoringAvailGraphImage");
            dijit.byId("graphZoomDialogBox").set("title", translator.common.systemUptimeGraph);
            graphTypeText = translator.common.systemUptimeGraph;
            
        } else if(graphType === "io") {
            
            graphImageTag = dojo.byId("monitoringDiskIOGraphImage");
            dijit.byId("graphZoomDialogBox").set("title", translator.common.diskIOGraph);
            graphTypeText = translator.common.diskIOGraph;
            
        }  else if(graphType === "throughput") {
            
            graphImageTag = dojo.byId("throughputGraphDiv");
            dijit.byId("graphZoomDialogBox").set("title", translator.common.networkInboundOutboundGraph);
            graphTypeText = translator.common.networkInboundOutboundGraph;
        }       
        graphImageTag.innerHTML = "<img src='images/vmload.gif' alt="+translator.common.loader.imageLoadError+" height='36' width='100'/> </br> <p>"+"Loading please wait..."+"</p>";  
        var dRange = dijit.byId("drangeSelectBox").value;
        var deviceId = dojo.byId("instanceDeviceId").value;
        
        if(deviceId) {
            
            graphRestStore.query({deviceId: deviceId  , size:500, dRange:Number(dRange), graphType:graphType})
                    .then(function (data) {
                if(data[0].imageURL) {
                    graphImageTag.innerHTML = "";
                    dojo.forEach(data, function (el) {
                        graphImageTag.innerHTML = "<div class='span12 max_width'><img width='100%' src="+"data:png;base64," + el.imageURL+" /> </div>"; 
                        dojo.byId("zoomGraphImage").innerHTML = "<div class='span12 '><img width='100%' class='' src="+"data:png;base64," + el.imageURL+" /> </div>"             
                    });   
                    graphImageTag.onclick = function () {
                        GraphInfo.zoomGraph();
                    };
                    graphImageTag.style.cursor = "pointer";
                } else {                    
                    graphImageTag.innerHTML  = "";
                    graphImageTag.innerHTML = "<div class='alert alert-info overflowLabel text_gray'> <i class='icon-exclamation-sign text_gray'></i>"+ translator.common.thereIsNo + "  " + graphTypeText + "  "+ translator.common.noGraphAvailMsg + "</div>"; 
                    dojo.byId("zoomGraphImage").innerHTML = "";
                    graphImageTag.onclick = {
                        
                    };
                     graphImageTag.style.cursor = "text";
                }                           
            }); 
            
        } else {
            console.log("Device not yet created")
        }

              
    },
    zoomGraph : function () {
        dijit.byId("graphZoomDialogBox").show();
    }
};

var ViewServer = {    
    'deleteInstance' : function() {
        dijit.byId("vmInfoDeleteDialog").hide();
        dijit.byId("vmInfoActionLoader").show();   
        var instanceDeleteRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/delete/"         
        });       
        instanceDeleteRestStore.add({
            referenceId: dojo.byId("instanceId").value
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dijit.byId("vmInfoActionLoader").hide(); 
                if(resultData.result === "OK") {

                    registry.byId("userToaster").setContent(translator.common.instance.deleteSuccess, "message");
                    registry.byId("userToaster").show();
                    ViewServer.init(dojo.byId("instanceId").value);   

                } else if(resultData.result == "DEVICE_ENABLED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.deleteVMError, "error");
                    registry.byId("userToaster").show();
                }
            });     
        }); 
          
    },
    'stopInstance': function() {     
        dijit.byId("vmInfoActionLoader").show(); 
        var instanceStopRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/stop/"         
        });

        instanceStopRestStore.add({
            referenceId: dojo.byId("instanceId").value,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result === "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.stopSuccess, "message");
                    registry.byId("userToaster").show();
                    ViewServer.init(dojo.byId("instanceId").value);
                    dijit.byId("vmInfoActionLoader").hide(); 
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.stopError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmInfoActionLoader").hide(); 
                }
            });                
        });
        dijit.byId("vmInfoStopDialog").hide();
    },
    'hardRebootInstance': function() {            
        dijit.byId("vmInfoActionLoader").show(); 
        var instanceRebootRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/reboot/"         
        });
        
        var rebootType = translator.common.instance.rebootTypeHard;
        instanceRebootRestStore.add({
            virtualMachineId: dojo.byId("instanceId").value,
            rebootType: rebootType
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.hardRebootSuccess, "message");
                    registry.byId("userToaster").show();
                    ViewServer.init(dojo.byId("instanceId").value);
                    dijit.byId("vmInfoActionLoader").hide(); 
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.hardRebootError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmInfoActionLoader").hide(); 
                }
            });    
        });
        dijit.byId("vmInfoHardRebootDialog").hide();
    },
    'startInstance' : function() {
        dijit.byId("vmInfoActionLoader").show(); 
        var instanceStartRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/start/"         
        });
        instanceStartRestStore.add({
            referenceId:  dojo.byId("instanceId").value
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result === "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.startSuccess, "message");
                    registry.byId("userToaster").show();
                    ViewServer.init(dojo.byId("instanceId").value)
                    dijit.byId("vmInfoActionLoader").hide(); 
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.startError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmInfoActionLoader").hide(); 
                }
            });    
        });
        dijit.byId("vmInfoStartDialog").hide();  
    },
    'startVMConfirm': function() {            
        dijit.byId("vmInfoStartDialog").show();
    },
    'stopInstanceShow': function() {  
        dijit.byId("vmInfoStopDialog").show();  
    },
    'getHardRebootConformation': function() {       
        dijit.byId("vmInfoHardRebootDialog").show();  
    },
    'getDeleteConformation' : function() {        
        dijit.byId("vmInfoDeleteDialog").show();  
    },
    'closeDeleteDialog' : function() {
        dijit.byId("vmInfoDeleteDialog").hide();
    },                
    'closeHardRebootDialog': function() {
        dijit.byId("vmInfoHardRebootDialog").hide();
    },
    'closeStartDialog': function() {
        dijit.byId("vmInfoStartDialog").hide();
    },
    'closeStopDialog': function() {
        dijit.byId("vmInfoStopDialog").hide();
    },
    'getSreverLog' : function() {
        
        
        var serverRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/getLog"
        });
        
//        dojo.byId("instanceLogInfo").innerHTML = "<img src='images/vmload.gif' alt="+translator.common.loader.imageLoadError+" height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>"; 
            
         serverRestStore.query({referenceId:dojo.byId("instanceId").value}).then(function(data) {
            dojo.forEach(data, function(instanceData) {  
                if(instanceData.result === "OK") {
                    dojo.byId("instanceLogInfo").innerHTML = instanceData.instanceLogInfo;
                } else {
                    dojo.byId("instanceLogInfo").innerHTML = "";
                }
            
            });
        });
    },
    
    'init': function(referenceId) {    
        
        dojo.byId("instanceId").value = referenceId;
        dojo.byId("currentInstanceDetailPage").style.display = "none";
        dojo.byId("vmDetailPageLoader").style.display = "block";        
        var usageInfo = new JsonRest({
            target: core.getContextPath() + "/api/account/usage"
        });

        usageInfo.query().then(function(data) {
            dojo.forEach(data, function(usageData) {
                if (usageData.zenossConfig === "false") {
                    var mainTab = dijit.byId("vmInfoTabContainer"); 
                    var monitorTab = dijit.byId("vmMonitoringTab");
                    mainTab.removeChild(monitorTab); 
                    monitorTab.destroyRecursive();
                } else {                   

                    var monitoringIpRestStore = new JsonRest({
                        target: core.getContextPath() + "/api/floatingIp/instanceIp"
                    });
                    
                    monitoringIpRestStore.query({referenceId: referenceId}).then(function (data) {
                       
                          if(data.length === 0) {
//                              var mainTab = dijit.byId("vmInfoTabContainer"); 
//                              var monitorTab = dijit.byId("vmMonitoringTab");
//                              mainTab.removeChild(monitorTab); 
//                              monitorTab.destroyRecursive();
                                dojo.byId("monitoringMsgDiv").style.display = "block";
                                dojo.byId("instanceCompleteMonitoringDiv").style.display = "none";
                                dojo.byId("monitoringInstanceStopMsgDiv").style.display = "none";
                          } else {                              
                              
                              dojo.byId("monitoringMsgDiv").style.display = "none";
                              dojo.byId("instanceCompleteMonitoringDiv").style.display = "block";
                              dojo.byId("monitoringInstanceStopMsgDiv").style.display = "none";
                              
                              //Check if monitoring device is enabled for current instance 
                              InstanceMonitoring.checkIsDeviceEnabled(referenceId);
                          } 
                        
                    });
                   
                }    
        });                               
        
        dojo.byId("instanceId").value = referenceId;

        });

        
        var serverRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/view"
        });
         
//         dojo.byId("instanceInfoLoader").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";
         
        serverRestStore.query({referenceId:referenceId}).then(function(data) {
            dojo.forEach(data, function(instanceData) {  
                dojo.byId("consoleUrl").value = instanceData.consoleUrl;
                dojo.byId("shutOffState").value = instanceData.instanceShutOff;
//                document.getElementById("consoleLink").href = instanceData.consoleUrl;
//                document.getElementById("consoleIframe").src = instanceData.consoleUrl;
                console.log("instanceData.consoleUrl" + instanceData.consoleUrl)
                dojo.byId("osName").innerHTML = instanceData.image;
                dojo.byId("vmName").innerHTML = instanceData.name;
                dojo.byId("serverName").innerHTML = instanceData.name;
                dojo.byId("location").innerHTML = instanceData.zone;
                dojo.byId("nicIp").innerHTML = instanceData.ip;
                dojo.byId("status").innerHTML = instanceData.state;
                dojo.byId("plan").innerHTML = instanceData.flavor;
                dojo.byId("sshKeyName").innerHTML = instanceData.keypair;
                                                
                                                   
            }); 
//           dojo.byId("instnaceInfoPageDiv").style.display = "block";
//           dojo.byId("instanceInfoTabContainerdiv").style.display = "block";
//           dojo.byId("instanceInfoLoader").innerHTML = "";

            dojo.byId("currentInstanceDetailPage").style.display = "block";
            dojo.byId("vmDetailPageLoader").style.display = "none"; 
            dijit.byId("vmInfoTabContainer").resize();
            GraphInfo.showGraph('availablity');
        });
        
        
        
    }, 
    'viewConsole' : function() {
        
        window.open(dojo.byId("consoleUrl").value,dojo.byId("vmName").innerHTML,'width=800,height=650');
        
    },
    'attachedVolumeList': function() {
        
        if(dijit.byId("attachedVolumeListWidget")) {
            dijit.byId("attachedVolumeListWidget").destroyRecursive();
        }
        
        var serverId = dojo.byId("instanceId").value;
        
        dojo.byId("attachedVolumeListGrid").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.attchedVolumeLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '100px', 'datatype': 'string', 'hidden': 'true',},
                {'name': translator.common.attachedvolume, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true',},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function (data) {
                        
                        var menu = new DropDownMenu({style: "display: none;"});
                        
                        var menuItem1 = new MenuItem({
                            label: translator.common.detachVolume,
                            onClick: function() {
                                ViewServer.detachVolumeAlertDialog(data.referenceId);
                            }
                        });
                        menu.addChild(menuItem1);
                        menu.startup();

                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });
                        return button;
                    }, 'width': '40%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var networksRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/attachedVolumes/"
        });

        networksRestStore.query({serverId : serverId}).then(function (data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("attachedVolumeListGrid").innerHTML = "";
                dojo.byId("noAttachedVolumeMessageBox").style.display = "block";

            } else {

                dojo.byId("noAttachedVolumeMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: resultData.name,
                        action: resultData
                    });
                });
                dojo.byId("attachedVolumeListGrid").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("attachedVolumeListGrid");
                dataGrid.startup();
                dataGrid.update();
            }
        });  
        
       //get detached volume list
       
        var volumeRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/detachedVolumeList"
        });
        var volumeOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var volumeList = new ItemFileWriteStore({data: volumeOptions});
//        volumeList.newItem({id: "", name: ""});
        volumeRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
                volumeList.newItem({id: el.referenceId, name: el.name});
            });
            var volumeWidget = new FilteringSelect({
                store: volumeList,
                id: "attachedVolumeListWidget",
                required: true,
                placeHolder: translator.common.selectVolume,
                onChange: function() {
                }
            });
            volumeWidget.placeAt("volumeListForAttachment");
            volumeWidget.startup();
        }); 
        
        
    },
    'showCreateVMSnapshotDialog' : function() {
        dijit.byId("createVMSnapshotDialog").show(); 
    },
    'createVMSnapshot' : function() {
       
        var vmSnapshotRest = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/takeVMSnap/"         
        });
        var pageNode = dojo.byId("createVMSnapshotFormPage");
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
            dijit.byId('createVMSnapshotDialog').hide();
            dijit.byId("vmSnapshotLoader").show();
            vmSnapshotRest.add({
                referenceId: dojo.byId("instanceId").value,
                name: dijit.byId("currentVMSnapshotName").value,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.result === "OK") {                      
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotCreated,"message");
                        registry.byId("userToaster").show();                                                 
                        dijit.byId("vmSnapshotLoader").hide();
                    } else if(resultData.result == "Pending") {
                        
                    } else  if(resultData.result == "FAILED") {
                        document.body.removeAttribute("style"); 
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotDeletedSuccess,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("vmSnapshotLoader").hide();
                        
                    } else {
                        document.body.removeAttribute("style");
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotCreateVmSnapshot,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("vmSnapshotLoader").hide(); 
                        
                    }
                });
            });
        }
    },
    'closeCreateVMSnapshotDialog' : function() {
        dijit.byId("createVMSnapshotDialog").hide();  
    },
    'gotoInstances': function() {
        var instanceId = dojo.byId("instanceId").value;
        dijit.byId("vmSnapshotLoader").hide(); 
    }, 
    'attachVolumeDialog': function() {
        dijit.byId("attachVolumeDialog").show();
        dijit.byId("attachVolumeForm").reset();
    },
    'attachVolume': function() {
                      
        var instanceId = dojo.byId("instanceId").value;
        var volumeId = dijit.byId("attachedVolumeListWidget").value; 
        
        var pageNode = dojo.byId("attachVolumeFormPage");
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
            
            dijit.byId("attachVolumeDialog").hide();
            dijit.byId("AttachedVolumeActionLoader").show();

            var volumeAttachmentRest = new JsonRest({
                target: core.getContextPath()+"/api/volume/attachedToInstance"         
            });
            
            volumeAttachmentRest.add({
                volumeId: volumeId,
                instanceId: instanceId,
            }).then(function(data) {

                dojo.forEach(data, function(resultData) {                
                    if(resultData.result === "OK") {                      
                        registry.byId("userToaster").setContent(translator.user.instance.volumeAttached,"message");
                        registry.byId("userToaster").show(); 
                        dijit.byId("AttachedVolumeActionLoader").hide();
                        ViewServer.attachedVolumeList();

                    } else {
                        registry.byId("userToaster").setContent(translator.user.instance.volumeNotAttached,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("AttachedVolumeActionLoader").hide();
                        ViewServer.attachedVolumeList();
                    }
                });
            });
        }
    },
    'gotoAttachmentList': function() {
        dijit.byId("AttachedVolumeActionLoader").hide();
    },
    'detachVolumeAlertDialog': function(deatchedVolumeId) {
        dojo.byId("deatchedVolumeId").value = deatchedVolumeId;
        dijit.byId("detachVolumeAlert").show();
    },
    
    'detachVolume': function(deatchedVolumeId) {
        
        var volumeId = dojo.byId("deatchedVolumeId").value;
        var instanceId = dojo.byId("instanceId").value;
        dijit.byId("detachVolumeAlert").hide();
        dijit.byId("AttachedVolumeActionLoader").show();

        var volumeAttachmentRest = new JsonRest({
                  target: core.getContextPath()+"/api/volume/detachVolume"         
        });

        volumeAttachmentRest.add({
            volumeId: volumeId,
            instanceId: instanceId,
        }).then(function(data) {

            dojo.forEach(data, function(resultData) {                
                if(resultData.result === "OK") {                      
                    registry.byId("userToaster").setContent(translator.user.instance.volumeDetached,"message");
                    registry.byId("userToaster").show(); 
                    dijit.byId("AttachedVolumeActionLoader").hide();
                    ViewServer.attachedVolumeList();
                } else {
                    registry.byId("userToaster").setContent(translator.user.instance.volumeNotDetached,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("AttachedVolumeActionLoader").hide();
                    ViewServer.attachedVolumeList();
                }
            });
        });
    },
    'closeAttachVolumeDialog': function() {
        dijit.byId("attachVolumeDialog").hide();
    },
    'closeDetachVolumeAlertDialog': function() {
        dijit.byId("detachVolumeAlert").hide();
    },

};

var AddServer = {
     _imageRestStore: "",
    
    'init': function() {      
        var currencyRestStore = new JsonRest({
                  target: core.getContextPath()+"/api/config/currency"         
        });
        currencyRestStore.query().then(function (data) {
            dojo.byId("createVMCurrecy").innerHTML = data[0].currency;
            dojo.byId("summaryCurrencyValue").innerHTML = data[0].currency;            
        });
    }, 
    showSummary : function () {
        var imageWidget = dijit.byId("imageListWidget");
        var flavorWidget = dijit.byId("flavorListWidget");                        
        var flavorCost = dojo.byId("flavorCostTemp").value;
        var imageCost = dojo.byId("imageCostTemp").value;                         
        if(flavorWidget.value === "option" || flavorWidget.value === "" ||flavorWidget.value === " " ) {
            dojo.byId("summaryFlavorInfo").style.display = "none";
            dojo.byId("summaryAvgCostInfo").style.display = "none";                 
        } else {          
            dojo.byId("summaryFlavorInfo").style.display = "block";
            dojo.byId("summaryAvgCostInfo").style.display = "block";                   
            flavorWidget.store.fetch({query: {id: flavorWidget.value},
                onItem: function(item) {
                    if(item) {                                                 
                        dojo.byId("summaryCpuCore").innerHTML = item.vcpu;
                        dojo.byId("rootDisk").innerHTML = item.rootDisk + "  " + translator.common.gb;
                        dojo.byId("summaryMemory").innerHTML = item.memory + "  " + translator.common.mb;       
                        dojo.byId("swapDisk").innerHTML = item.swapDisk;                                                  
                        dojo.byId("summarySetupCost").innerHTML = ""+item.setupCost;
                        dojo.byId("summaryRunningCost").innerHTML =""+ localeCurrency.format(item.cost, {places: 5, locale: dojo.locale}) +  "   /" + translator.common.hour;            
                        dojo.byId("summaryMonthlyFlavorCost").innerHTML = ""+ localeCurrency.format( Math.ceil((Number(item.cost) * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + "  /" + translator.common.month;
//                        flavorCost = Number(item.cost) * 720.00;                                              
                    }
                }
            }); 
        }
        imageWidget.store.fetch({query: {id: imageWidget.value}, onItem: function(item) {
                if(item) {                                       
                    dojo.byId("imageName").innerHTML = item.name;
                    dojo.byId("imageSummarySize").innerHTML = Number(item.size).toFixed(2) + "  " + translator.common.gb;
                    dojo.byId("summaryImageCost").innerHTML = "" + localeCurrency.format(item.cost, {places: 2, locale: dojo.locale}) + "   /" + translator.common.month;
//                    imageCost = Number(item.cost);                           
                }
            }
        });         
        var flavorMnthCost = Number(flavorCost) * 720;
        var avgCost = Number(flavorMnthCost) +  Number(imageCost);        
        
        dojo.byId("summaryAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + "   /" + translator.common.month; 
        dijit.byId("vmSummaryPage").show(); 
    },
    loadFlavorZoneCostOnZoneBase : function () {         
        var availabilityZone = "";
        if(dijit.byId("availabilityZoneListWidget")) {
            availabilityZone = dijit.byId("availabilityZoneListWidget").value;
        } else {
            availabilityZone = "";  
        }
        var flavorRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/"
        });
        var flavorOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };
        var imageId = dijit.byId("imageListWidget");
        var flavorList = new ItemFileWriteStore({data: flavorOptions});
        flavorRestStore.query({imageId: imageId}).then(function(data) {
            dojo.forEach(data, function(el) {               
                var cost = 0.00;
                var setupCost = 0.00;
                dojo.forEach(el.flavorCostList, function (zoneData) {                       

                    if(availabilityZone === zoneData.zone.id) {                      
                        cost = zoneData.runningCost;
                        setupCost = zoneData.setupCost;
                    } else {
                        cost = 0.00;
                        setupCost = 0.00;
                    }                                       
                });                 
                flavorList.newItem({
                    id: el.referenceId, 
                    name: el.name, 
                    cost: cost, 
                    setupCost: setupCost,
                    memory: el.memory,
                    vcpu : el.vcpu,
                    rootDisk : el.rootDisk,                       
                    swapDisk : el.swapDisk                       
                });
            });
           dijit.byId("flavorListWidget").set("store", flavorList)
           dijit.byId("flavorListWidget").reset();
//           dijit.byId("flavorListWidget").set("store", flavorList)           
        });
    },
    enableMonthlyCost: function() {
       var formElements = dojo.query("#billingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        if(billingType === "monthly") {  
            dojo.query("#flavorMonDiv").removeClass("hide_text", true);
            dojo.query("#flavorAvgMonthlyCost").removeClass("hide_text", true);                                                   
        } else {
            dojo.query("#flavorMonDiv").toggleClass("hide_text", true);
            dojo.query("#flavorAvgMonthlyCost").toggleClass("hide_text", true);                 
        }                
    },
    'populateValues': function(referenceId) {              
        
        if(dijit.byId("flavorListWidget")) {               
            dijit.byId("flavorListWidget").destroyRecursive();          
        } 
        if(dijit.byId("imageListWidget")) {               
            dijit.byId("imageListWidget").destroyRecursive();          
        } 
        if(dijit.byId("networkListWidget")) {               
            dijit.byId("networkListWidget").destroyRecursive();          
        } 
        if(dijit.byId("keypairWidget")) {               
            dijit.byId("keypairWidget").destroyRecursive();          
        } 
        if(dijit.byId("keypairWidget")) {               
            dijit.byId("keypairWidget").destroyRecursive();          
        } 
        if(dijit.byId("securityGroupListWidget")) {               
            dijit.byId("securityGroupListWidget").destroyRecursive();          
        }
        if(dijit.byId("availabilityZoneListWidget")) {               
            dijit.byId("availabilityZoneListWidget").destroyRecursive();          
        }        
        
        var availabilityZoneRestStore = new JsonRest({
                target: core.getContextPath() + "/api/zone/"
        });
        
        var billingTypeConfigRestStore = new JsonRest({
                target: core.getContextPath() + "/api/config/billingType"
        });
        billingTypeConfigRestStore.query().then(function (data) {
            if (data[0].monthlyBillingEnabled === "true") {
                dojo.query("#billingTypeDiv").removeClass("hide_text", true);
            } else {
                dojo.query("#billingTypeDiv").toggleClass("hide_text", true);
            }
        });
        var availabilityZoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var flavorOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        dojo.byId("availabilityZoneList").style.display = "none";  
        dojo.byId("vmZoneLoader").style.display = "block";
        
        dojo.byId("instanceImageList").style.display = "none";  
        dojo.byId("vmImageLoader").style.display = "block";        
        
        dojo.byId("instanceFlavorList").style.display = "none";  
        dojo.byId("vmFlavorLoader").style.display = "block";
        
        dojo.byId("instanceSSHkeyList").style.display = "none";  
        dojo.byId("vmsshKeyLoader").style.display = "block";
        
        
        
        var flavorList = new ItemFileWriteStore({data: flavorOptions});                      
        var availabilityZoneList = new ItemFileWriteStore({data: availabilityZoneOptions});

        availabilityZoneRestStore.query().then(function(data) {
            var firstValue = "";
            dojo.forEach(data, function(el, index) {
                availabilityZoneList.newItem({id: el.id, name: el.name});
                if (index === 0) {
                    firstValue = el.id;
                }
            });
            var availabilityZoneWidget = new FilteringSelect({
                store: availabilityZoneList,
                id: "availabilityZoneListWidget",
                required: true,
                value: firstValue,
                placeHolder: translator.common.anyAvailabilityZone,
                onChange: function() {
                    AddServer.loadFlavorZoneCostOnZoneBase();
                }
            });
            availabilityZoneWidget.placeAt("availabilityZoneList");
            availabilityZoneWidget.startup();

//            setTimeout(function() {
//                AddServer.loadFlavorZoneCostOnZoneBase();
//            });
            
            dojo.byId("availabilityZoneList").style.display = "block";  
            dojo.byId("vmZoneLoader").style.display = "none";
            
            dojo.byId("instanceFlavorList").style.display = "block";  
            dojo.byId("vmFlavorLoader").style.display = "none";
        });

        var securityGroupRestStore = new JsonRest({
                target: core.getContextPath() + "/api/securityGroup/"
        });
        var securityGroupOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };

        var securityGroupList = new ItemFileWriteStore({data: securityGroupOptions});
        securityGroupRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
               securityGroupList.newItem({id: el.name, name: el.name});
            });
            var securityGroupWidget = new CheckedMultiSelect({
                store: securityGroupList,
                multiple:"true",
                id:"securityGroupListWidget",
                placeHolder: translator.common.selectSecurityGroup,       
                onChange: function() {  
                }
            });
            securityGroupWidget.placeAt("vmSecurityGroupList");
            securityGroupWidget.startup();            
        });                
        
        
        var flavorWidget = new FilteringSelect({
            store: flavorList,
            id:"flavorListWidget",
            placeHolder: translator.common.selectFlavor,       
            onChange: function() {
                AddServer.showflavorCost(this);
            }
        });
        flavorWidget.placeAt("instanceFlavorList");
        flavorWidget.startup();                        
        
        
        if(referenceId === null || referenceId === undefined) {            
            this._imageRestStore = new JsonRest({
                target: core.getContextPath() + "/api/image/"
            });
        } else {           
            this._imageRestStore = new JsonRest({
                target: core.getContextPath() + "/api/snapshot/listVMSnapshot/"
            });
        }
        var imageOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };
    
        var imageList = new ItemFileWriteStore({data: imageOptions});
        this._imageRestStore.query({referenceId : referenceId === null || referenceId === undefined ? null : referenceId}).then(function(data) {
            dojo.forEach(data, function(el) {              
               imageList.newItem({
                   id: el.referenceId,
                   name: el.name, 
                   cost: el.cost, 
                   oneTimeChargeable: el.oneTimeChargeable,
                   size : el.size
               });
            });
            var imageWidget = new FilteringSelect({
                store: imageList,
                id:"imageListWidget",
                placeHolder: translator.common.selectImage,       
                onChange: function() {
                    AddServer.showImageCost(this);
                }
            });
            imageWidget.placeAt("instanceImageList");
            imageWidget.startup();            
            
            dojo.byId("instanceImageList").style.display = "block";  
            dojo.byId("vmImageLoader").style.display = "none";     
            if(referenceId != null || referenceId != undefined) {
                dijit.byId("imageListWidget").set("value",referenceId);
                dijit.byId("imageListWidget").setAttribute('disabled', true);
            } 
        });
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });
        var networkOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };
    
        var networkList = new ItemFileWriteStore({data: networkOptions});
        networkRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
               networkList.newItem({id: el.referenceId, name: el.name});
            });
            var networkWidget = new CheckedMultiSelect({
                store: networkList,
                id:"networkListWidget",
                multiple:"true",
                placeHolder: translator.common.selectNetwork,       
                onChange: function() {
//                        AddComputingOfferInfo.viewSelectedZone(this);   
                }
            });
            networkWidget.placeAt("networkImageList");
            networkWidget.startup();
        });
                
        var keypairRestStore = new JsonRest({
            target: core.getContextPath() + "/api/keypair/list"
        });
        
        dojo.byId("instanceSSHkeyList").innerHTML = translator.common.loading;
        var keypairOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };
    
        var keypairList = new ItemFileWriteStore({data: keypairOptions});
        keypairRestStore.query().then(function(data) {
        
            dojo.forEach(data, function(el) {
               keypairList.newItem({id: el.name, name: el.name});
            });
            dojo.byId("instanceSSHkeyList").innerHTML = "";
            var keypairWidget = new FilteringSelect({
                store: keypairList,
                id:"keypairWidget",
                placeHolder: translator.common.selectSSKKey,       
                onChange: function() {
//                        AddComputingOfferInfo.viewSelectedZone(this);   
                }
            });
            keypairWidget.placeAt("instanceSSHkeyList");
            keypairWidget.startup();
            
            dojo.byId("instanceSSHkeyList").style.display = "block";  
            dojo.byId("vmsshKeyLoader").style.display = "none";
        });
        
    }, 
    "showflavorCost": function (currentFlavor) {                        
        if(currentFlavor.value === " " || currentFlavor.value === ""|| currentFlavor.value === "option") {
            dojo.query("#offeringInfo #vmAvgCostInfo").removeClass("show_lable", true);
            dojo.query("#offeringInfo #summaryInfo").removeClass("show_lable", true);            
            dojo.byId("flavorCostTemp").value = 0;
        } else if(currentFlavor.value !== "option" || currentFlavor.value === ""|| currentFlavor.value === " ") {
            dojo.query("#offeringInfo #vmAvgCostInfo").toggleClass("show_lable", true);
            dojo.query("#offeringInfo #summaryInfo").toggleClass("show_lable", true);    
        }
        if(currentFlavor.value === "option" || currentFlavor.value === ""|| currentFlavor.value === " ") {
            dojo.query("#offeringInfo #flavorInfo").removeClass("show_lable", true);
            dojo.byId("flavorCostTemp").value = 0;
        } else {
            currentFlavor.store.fetch({query: {id: currentFlavor.value},
                onItem: function(item) {            
                    if(item.id === "option" || item.id === ""|| item.id === " ") {              
                        dojo.query("#offeringInfo #flavorInfo").removeClass("show_lable", true);    
                        dojo.byId("flavorCostTemp").value = 0;
                    } else {  
                        dojo.byId("imageCost").value = Number(item.cost);
                        dojo.query("#offeringInfo #flavorInfo").toggleClass("show_lable", true);
                        var imgCost = dojo.byId("imageCostTemp").value;
                        dojo.byId("flavorCostTemp").value = Number(item.cost);
                        var avgCost = Number(imgCost) + (Number(item.cost)*720.00);
                        dojo.byId("generalAvgCost").innerHTML = "     " + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) +  "     /" + translator.common.month;                        
                        dojo.byId("flavorCost").innerHTML = item.memory + " MB |        " + localeCurrency.format(item.cost, {places: 5, locale: dojo.locale}) + " /" + translator.common.hour;    
                        dojo.byId("monthlyFlavorCost").innerHTML = localeCurrency.format(Math.ceil((item.cost * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + " /" + translator.common.month;            
                    }                                                  
                }
            });
        }                   
    },
     'showImageCost' : function (currentImage) {      
         if(currentImage.value === "" || currentImage.value === " " || currentImage.value === null || currentImage.value === "null") {
             dojo.query("#imageDataInfo").removeClass("show_lable", true);
             dojo.byId("imageCost").innerHTML = "";
             dojo.byId("imageCostTemp").value = 0;
         } else {
             currentImage.store.fetch({query: {id: currentImage.value}, onItem: function(item) {            
                     if(item.id === "" || item.id === " " || item.id === null || item.id === "null") {              
                         dojo.query("#imageDataInfo").removeClass("show_lable", true);
                         dojo.byId("imageCost").innerHTML = "";
                         dojo.byId("imageCostTemp").value = 0;
                    } else {   
                        AddServer.loadFlavorZoneCostOnZoneBase();
                        var flavorCost = dojo.byId("flavorCostTemp").value;                        
                        var avgCost = Number(flavorCost * 720.00) + Number(item.cost);
                        dojo.byId("generalAvgCost").innerHTML = "     " + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) +  "     /" + translator.common.month;    
                        dojo.byId("imageCostTemp").value = Number(item.cost);
                        dojo.query("#imageDataInfo").toggleClass("show_lable", true);
                        dojo.byId("imageCost").innerHTML = item.name + "    |    "+localeCurrency.format(item.cost, {places: 2, locale: dojo.locale}) + "      /" + translator.common.month;               
                    }                                                  
                }
            });
         }                  
     },
    'gotoInstances' : function() {
        core.router.go("#/user/server/list");
        dijit.byId("createVMLoading").hide(); 
    },
    'cancel' : function() {
      core.router.go("#/user/server/list");
    },
    'add' : function() {
                
        var status = true;
        var pageNode = dojo.byId("createVMPage");
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

            var vmRestStore = new JsonRest({
                target: core.getContextPath() + "/api/virtualMachine/"
            });
            var availabilityZone = dijit.byId("availabilityZoneListWidget").attr('displayedValue');
            
            var name = dijit.byId("instanceName").value;
//            var availabilityZone = dijit.byId("availabilityZoneListWidget").value;
            var flavor = dijit.byId("flavorListWidget").value;
            var image = dijit.byId("imageListWidget").value;
            var networks = dijit.byId("networkListWidget").value;
            
            var securityGroups = dijit.byId("securityGroupListWidget").value;

            var keypair = dijit.byId("keypairWidget").value;

            dojo.style(dijit.byId("createVMLoading").closeButtonNode,"display","none");                      
            dijit.byId("createVMLoading").show(); 
            
            var formElements = dojo.query("#billingTypeDiv input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var billingType = dijit.byId(checkedRadioId).value;
            
            vmRestStore.add({
                name: name,
                availabilityZone: availabilityZone,
                flavor: flavor,
                image: image,
                networks: networks,
                securityGroups: securityGroups,
                keypair:keypair,
                billingType : billingType
            }).then(function(data) {
                dojo.forEach(data, function(el) {
                    if (el.result === "OK") {
                         registry.byId("userToaster").setContent(translator.common.message.createVMSuccess, "message");
                         registry.byId("userToaster").show();
                         dijit.byId("createVMLoading").hide(); 
                         core.router.go("#/user/server/list");
                         registry.byId("userToaster").setContent(translator.common.message.refreshVMList, "message");
                     } else {
                         registry.byId("userToaster").setContent(translator.common.message.cannotBeAdded, "error");
                         registry.byId("userToaster").show();
                         dijit.byId("createVMLoading").hide(); 
                     }
                });
                
            });
        }
    }
};

var ListServer = {

    gotoMonitorTab : function (currentVMId) {
        core.router.go("#/user/server/viewServer/" + currentVMId);
        
        setTimeout(function () {
            var mainTab = dijit.byId("vmInfoTabContainer"); //Tr
            var monitorTab = dijit.byId("vmMonitoringTab"); //tab Id which you want to show
            mainTab.selectChild(monitorTab);                         
        },100)        
    },
    'gotoInstances' : function() {
        dijit.byId("vmActionLoader").hide(); 
    },
    'init': function() {
        
        var setupVerificationRestStore = new JsonRest({
              target: core.getContextPath()+"/api/user/setupVerification"
        });  
        
        setupVerificationRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                
                if (resultData.openstackSetup === "false") {
                    registry.byId("userToaster").setContent(translator.common.instance.setupNeeded, 'warning');
                    registry.byId("userToaster").show();
                    core.router.go("#/user/home");
                } else {
                    core.router.go("#/user/server/list");
                }
            });
        });
    },
    'populateValues': function() {                
            if(dijit.byId("instanceGridWidget")) {
                dijit.byId("instanceGridWidget").destroyRecursive();
            }
                        
            dojo.byId("noVmMessageBox").style.display = "none";
            
            dojo.byId("instanceGrid").innerHTML = "<img src='images/vmload.gif' alt="+translator.common.loader.imageLoadError+" height='36' width='100'/> </br> <p>"+translator.user.loader.instanceLoader+"</p>";   
            var gridData = {
                items: []
            };
           
           var gridDataList = new ItemFileWriteStore({data: gridData}); 
           
           var gridLayout = [
               [
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '100px', datatype:"string",  autoComplete: true,'formatter': function(data) {
                        
                        return "<a href='#/user/server/viewServer/" + data.referenceId + "' title='" + translator.common.view + "'>" + data.name + "</a>";
                }},                 
                {'name': translator.common.ImageName, 'field': 'image', 'width': '100px', datatype:"string",  autoComplete: true,'formatter': function(data) {                                                                      
                         return  "<span class='bold'>" + data +"</span>";
                     }
                },
                {'name': translator.user.grid.instance.layout.ip, 'field': 'ip', 'width': '100px', datatype:"string",  autoComplete: true},
                {'name': translator.common.size.sizeName, 'field': 'plan', 'width': '200px', datatype:"string",  autoComplete: true },
                {'name': translator.common.keypair, 'field': 'keypair', 'width': '100px', datatype:"string",  autoComplete: true},
                {'name': translator.user.grid.instance.layout.zone, 'field': 'zone', 'width': '100px', datatype:"string",  autoComplete: true},
                {'name': translator.user.grid.instance.layout.status, 'field': 'status', 'width': '80px', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        if(data.state == "HARD_REBOOT") {
                            return "Rebooting..."
                        } else if(data.state == "REBOOT") {
                            return "Rebooting..."
                        }  else if(data.state == "REBUILD") {
                            return "Rebuilding..."
                        }else {
                            return data.state;
                        }
                }},
                {'name': translator.common.task, 'field': 'task', 'width': '80px', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                    var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' class='vm_stat_image' alt='"+translator.common.loader.loading+"'/>" });
                    if(data === "spawning" || data === "ACTIVE" || data === "1") {
                       return imageDiv.innerHTML;
                    } else{
                        return "None";
                    }
                }},
                {'name': translator.common.powerState, 'field': 'powerState', 'width': '80px', 'formatter' : function(data) {                            
                        var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' class='vm_stat_image' alt='"+translator.common.loader.loading+"'/>" });
                        var status;                         
                        if((data !== "in progress") && (data !== "starting")) {
                                if(data === "Running" || data === "ACTIVE" || data === "1") {
                                    status = "<div class='stat_running overflowLabel'>"+translator.common.instance.status.running+"</div>";
                                } else if(data === "Shutdown" || data === "4") {
                                    status = "<div class='stat_stopped overflowLabel'>"+translator.common.instance.status.stopped+"</div>";
                                }  else if(data === "3") {
                                    status = "<div class='stat_stopped overflowLabel'>"+ translator.common.instance.status.paused +"</div>";
                                } else if(data === "Destroyed") {
                                    status = "<div class='stat_destroyed overflowLabel'>"+translator.common.instance.status.destroyed+"</div>";
                                } else if(data === "Error") {
                                    status = "<div class='stat_destroyed overflowLabel'>"+translator.common.error+"</div>";
                                } else if(data === "0") {
                                    status = "<div class='stat_stopped overflowLabel'>"+translator.common.noState+"</div>";
                                } else {
                                    if(data === "Starting") {
                                        status = imageDiv.innerHTML;
                                    } else if (data === "Stopping") {
                                        status = imageDiv.innerHTML;
                                    } else if (data === "Expunging") {
                                        status = imageDiv.innerHTML;
                                    } else {
                                        status = data;
                                    }                                   
                                }                                                                 
                                
                            } else if(data === "in progress") {
                                status = imageDiv.innerHTML; //
                            }
                            return status;
                        }, datatype:"string",  autoComplete: true},
                    {'name': translator.common.action, 'field': 'action',   'formatter': function(data, rowIndex) {
//                        var layoutData = data.split(",");
//                        var instanceStatus =  new FogPanel.InstanceStatus({
//                            onStartInstance : function() {
////                                UserInstances.getStartConformation();
//                            },
//                            onStopInstance : function() {
////                                UserInstances.getStopConformation();
//                            },
//                            onRebootInstance : function() {
////                                UserInstances.getRebootConformation();
//                            },
//                            onRestoreInstance : function() {
////                                dojo.byId("currentVmID").value = layoutData[1];
////                                 dijit.byId("vmRestoreDialog").show();
////                                UserInstances.restoreInstance();
//                            },
//                             onDeleteInstance : function() {
////                                 UserInstances.getDeleteConformation();
//                            }
//          
//                        }); 
//                        instanceStatus.disableMigrateHost();                        
//                        if(layoutData[0] === "Running" || layoutData[0] === "ACTIVE") {
//                            instanceStatus.enableRunningState();
//                        } else if(layoutData[0] === "Stopped") {
//                             instanceStatus.enableStopState();
//                        } else if(layoutData[0] === "Destroyed") {
//                            instanceStatus.enableRestoreState();
//                        } else if(layoutData[0] === "Error") {                           
//                            instanceStatus.enableDeleteStat();
//                        } else {
//                            instanceStatus.disableAll();
//                        }
                                                        
                            var menu = new DropDownMenu({ style: "display: none;"});
                            
                            var viewMenu = new MenuItem({
                                    label: translator.common.view,
                                    onClick: function(){ core.router.go("#/user/server/viewServer/" + data.referenceId ); }
                                });
                                menu.addChild(viewMenu);
                                menu.startup();
                                
                            var associateMenu = new MenuItem({
                                label: translator.common.associateFloating,
                                onClick: function() { 
                                    
                                InstanceFloatingIp.associateDialog(data.referenceId);
                            }
                            });
                            menu.addChild(associateMenu);
                            menu.startup();
                            
                            var disAssociateMenu = new MenuItem({
                            label: translator.common.disAssociateFloating,
                            onClick: function() {
                                InstanceFloatingIp.disassociateDialog(data.referenceId);
                            }
                            });
                            menu.addChild(disAssociateMenu);
                            menu.startup();
                            
                            if(data.powerState !== "1" && data.powerState !== "0" && data.powerState !== "3" && data.state !== translator.common.instance.actionTypeSuspended) {
                                var menuItem1 = new MenuItem({
                                label: translator.common.instance.start,
                                onClick: function(){ ListServer.getStartConformation(data.referenceId); }
                                });
                                menu.addChild(menuItem1);
                                menu.startup();
                            }
                            
                            if(data.powerState !== "4" && data.powerState !== "0") {
                                var menuItem2 = new MenuItem({
                                    label: translator.common.instance.stop,
                                    onClick: function(){ ListServer.getStopConformation(data.referenceId); }
                                });
                                menu.addChild(menuItem2);
                                menu.startup();
                            }
                            
                            if(data.powerState !== "0" && data.powerState !== "3" && data.powerState !== "4") {
                                var actionTypePause = translator.common.instance.actionTypePause;
                                var pauseMenu = new MenuItem({
                                    label: translator.common.instance.pause,
                                    onClick: function(){ ListServer.pauseInstance(data.referenceId, actionTypePause); }
                                });
                                menu.addChild(pauseMenu);
                                menu.startup();
                            }
                            
                            if(data.powerState !== "0" && data.powerState !== "4" && data.state !== translator.common.instance.actionTypePaused) {
                                var actionTypeSuspend = translator.common.instance.actionTypeSuspend;
                                var suspend = new MenuItem({
                                    label: translator.common.instance.suspend,
                                    onClick: function(){ ListServer.suspendInstance(data.referenceId, actionTypeSuspend); }
                                });
                                menu.addChild(suspend);
                                menu.startup();
                            }
                            
                            if((data.powerState !== "0") && data.powerState === "3" || (data.powerState === "4" && data.state === translator.common.instance.actionTypeSuspended)) {
                                
                                var actionType;
                                if(data.state == translator.common.instance.actionTypeSuspended) {
                                    actionType = translator.common.instance.actionTypeResume;
                                } else {
                                    actionType = translator.common.instance.actionTypeUnpause;
                                }

                                var resume = new MenuItem({
                                    label: translator.common.instance.resume,
                                    onClick: function(){ ListServer.resumeInstance(data.referenceId, actionType); }
                                });
                                menu.addChild(resume);
                                menu.startup();
                            }
                            
                            if(data.powerState !== "0" && data.powerState !== "3" && data.state !== translator.common.instance.actionTypeSuspended) {
                                var menuItem3 = new MenuItem({
                                    label: translator.common.instance.softReboot,
                                    onClick: function(){ ListServer.getSoftRebootConformation(data.referenceId); }
                                });
                                menu.addChild(menuItem3);
                                menu.startup();
                            }
                            
                            if(data.powerState !== "0" && data.powerState !== "3" && data.state !== translator.common.instance.actionTypeSuspended) {
                                var hardReboot = new MenuItem({
                                    label: translator.common.instance.hardReboot,
                                    onClick: function(){ ListServer.getHardRebootConformation(data.referenceId); }
                                });
                                menu.addChild(hardReboot);
                                menu.startup();
                            }
                            
                            if(data.powerState !== "0" && data.powerState !== "3" && data.state !== translator.common.instance.actionTypeSuspended) {
                                var resizeInstance = new MenuItem({
                                    label: translator.common.instance.resizeInstance,
                                    onClick: function(){ ListServer.showResizeInstanceDialog(data.referenceId,data.flavorId); }
                                });
                                menu.addChild(resizeInstance);
                                menu.startup();
                            }
                            
                            if(data.powerState !== "0" && data.powerState !== "3" && data.state !== translator.common.instance.actionTypeSuspended) {
                                var rebuildInstance = new MenuItem({
                                    label: translator.common.instance.rebuildInstance,
                                    onClick: function(){ ListServer.showRebuildInstanceDialog(data.referenceId,data.name,data.imageId); }
                                });
                                menu.addChild(rebuildInstance);
                                menu.startup();
                            }
                                                        
                            if(data.powerState !== "0") {
                                var vmSnap = new MenuItem({
                                    label: "Take Snapshot",
                                    onClick: function(){ ListServer.showTakeSnapshot(data.referenceId); }
                                });
                                menu.addChild(vmSnap);
                                menu.startup();
                            }
                            //Disable enable monitoring action menu
                            if((data.instanceShutOff == false) && (data.powerState === "Shutdown" || data.powerState === "4")) {

                            } else {
                                if(data.monitoringDevice == null) {
//                                    var enableMonitoringMenu = new MenuItem({
//                                       label: translator.common.enableMonitoring,
//                                       onClick: function(){ 
//
//                                       }
//                                       });
//                                   menu.addChild(enableMonitoringMenu);
//                                   menu.startup();
                                }
                            }
                            dojo.byId("instanceId").value = data.referenceId;
                            if(data.monitoringDevice != null) {
//                                var disableMonitoringMenu = new MenuItem({
//                                    label: translator.common.disableMonitoring,
//                                    onClick: function(){  
//                                          ListServer.disableMonitoring();
//                                    }
//                                    });
//                                menu.addChild(disableMonitoringMenu);
//                                menu.startup();
                            }
                            
                            var monitorMenuItem = new MenuItem({
                                label: "Monitoring",
                                onClick: function() { ListServer.gotoMonitorTab(data.referenceId); }
                            });
                            menu.addChild(monitorMenuItem);
                            menu.startup();
                            
                            var menuItem4 = new MenuItem({
                                label: "Delete",
                                onClick: function(){ ListServer.getDeleteConformation(data.referenceId); }
                            });
                            menu.addChild(menuItem4);
                            menu.startup();                                                        
                            
                            var button = new ComboButton({
                                label: "More",
                                name: "More",
                                dropDown: menu
                            });
                                                        
                            return button;
                },'width': '100%', datatype:"string",  autoComplete: 'true'}           
        ]
    ];        
    var serverRestStore = new JsonRest({
        target: core.getContextPath()+"/api/virtualMachine/"
    });
    
    serverRestStore.query().then(function(data) {
            if(data.length == 0 || data == "undefined" || data == undefined)  {                  
                dojo.byId("noVmMessageBox").style.display = "block";                    
//                dojo.byId("userTotalVM").innerHTML = 0;
//                dojo.byId("userRunningVM").innerHTML = 0;
//                dojo.byId("userStoppedVM").innerHTML = 0;
                dojo.byId("instanceGrid").innerHTML = "";
            } else {                            
                dojo.byId("noVmMessageBox").style.display = "none";
                dojo.forEach(data, function(instanceData) {  
                gridDataList.newItem({
                    id: instanceData.referenceId,
                    name: instanceData,
                    image: instanceData.image,
                    plan: instanceData.instanceSize,
                    keypair: instanceData.keypair,
                    zone: instanceData.zone,
                    status: instanceData, 
                    task: instanceData.task, 
                    powerState: instanceData.powerState, 
                    ip:instanceData.ip,                             
                    action: instanceData
                });                   
            });  
            
                if(dijit.byId("instanceGridWidget")) {
                dijit.byId("instanceGridWidget").destroyRecursive();
            }
              
            dojo.byId("instanceGrid").innerHTML = "";
            var instanceGrid = new EnhancedGrid({
                id: 'instanceGridWidget',
                store: gridDataList,
                structure: gridLayout,
                loadingMessage: translator.user.loader.instanceLoader,
                noDataMessageL:translator.user.grid.instance.noInstance,
                autoHeight: true,
                autoWidth: false,                
                class:"span12",
                style: "overflow: hidden",
                plugins: core.getGridConfig().plugins
            });                     
            instanceGrid.placeAt("instanceGrid");        
            instanceGrid.startup();             
//            instanceCountRestStore.query().then(function(data) {
//                dojo.forEach(data, function(el) {                     
//                    dojo.byId("userTotalVM").innerHTML = el.totalVms;
//                    dojo.byId("userRunningVM").innerHTML = el.runningVms;
//                    dojo.byId("userStoppedVM").innerHTML = el.stoppedVms;                     
//                });
//            });
        } 
    });
    
    },
    'disableMonitoring': function() {
      dijit.byId("vmListmonitoringDisableAlert").show();  
    },
    'getStartConformation': function(data) {    
        dojo.byId("seletedServerId").value = data;
        dijit.byId("startDialog").show();
    },
    'getStopConformation': function(data) {  
        dojo.byId("seletedServerId").value = data;
        dijit.byId("stopDialog").show();
    },
    'closeDeleteDialog' : function() {
        dijit.byId("deleteDialog").hide();
    },
    'getSoftRebootConformation': function(data) {
        dojo.byId("seletedServerId").value = data;
        dijit.byId("softRebootDialog").show();  
    },
    'getHardRebootConformation': function(data) {
        dojo.byId("seletedServerId").value = data;
        dijit.byId("hardRebootDialog").show();  
    },
    'getDeleteConformation' : function(data) {
        dojo.byId("seletedServerId").value = data;
        dijit.byId("deleteDialog").show();  
    },
    'closeSoftRebootDialog': function() {
        dijit.byId("softRebootDialog").hide();
    },
    'closeHardRebootDialog': function() {
        dijit.byId("hardRebootDialog").hide();
    },
    'closeStartDialog': function() {
        dijit.byId("startDialog").hide();
    },
    'closeStopDialog': function() {
        dijit.byId("stopDialog").hide();
    },
    'pauseInstance': function(virtualMachineId, actionType) {
        
        var pauseInstanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/actions/"         
        });
        
        pauseInstanceRestStore.add({
            virtualMachineId: virtualMachineId,
            actionType: actionType,
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.pauseSuccess, "message");
                    registry.byId("userToaster").show(); 
                    ListServer.populateValues();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.pauseError, "error");
                    registry.byId("userToaster").show(); 
                }
            });    
        });
    },
    'suspendInstance': function(virtualMachineId, actionType) {
        
        var suspendInstanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/actions/"         
        });
        
        suspendInstanceRestStore.add({
            virtualMachineId: virtualMachineId,
            actionType: actionType,
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.suspendSuccess, "message");
                    registry.byId("userToaster").show();
                    ListServer.populateValues();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.suspendError, "error");
                    registry.byId("userToaster").show();
                }
            });    
        });
    },
    'resumeInstance': function(virtualMachineId, actionType) {
        
        var suspendInstanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/actions/"         
        });
        
        suspendInstanceRestStore.add({
            virtualMachineId: virtualMachineId,
            actionType: actionType,
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.resumeSuccess, "message");
                    registry.byId("userToaster").show();
                    ListServer.populateValues();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.resumeError, "error");
                    registry.byId("userToaster").show();
                }
            });    
        });
    },
    'softRebootInstance': function() {            
        dijit.byId("vmActionLoader").show(); 
        var instanceRebootRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/reboot/"         
        });
        
        var rebootType = translator.common.instance.rebootTypeSoft;
        instanceRebootRestStore.add({
            virtualMachineId: dojo.byId("seletedServerId").value,
            rebootType: rebootType,
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.softRebootSuccess, "message");
                    registry.byId("userToaster").show();
                    ListServer.populateValues();
                    dijit.byId("vmActionLoader").hide(); 
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.softRebootError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmActionLoader").hide(); 
                }
            });    
        });
        dijit.byId("softRebootDialog").hide();
    },
    'hardRebootInstance': function() {            
        dijit.byId("vmActionLoader").show(); 
        var instanceRebootRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/reboot/"         
        });
        
        var rebootType = translator.common.instance.rebootTypeHard;
        instanceRebootRestStore.add({
            virtualMachineId: dojo.byId("seletedServerId").value,
            rebootType: rebootType,
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.hardRebootSuccess, "message");
                    registry.byId("userToaster").show();
                    ListServer.populateValues();
                    dijit.byId("vmActionLoader").hide(); 
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.hardRebootError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmActionLoader").hide(); 
                }
            });    
        });
        dijit.byId("hardRebootDialog").hide();
    },
    'startInstance' : function() {
        dijit.byId("vmActionLoader").show(); 
        var instanceStartRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/start/"         
        });
        instanceStartRestStore.add({
            referenceId:  dojo.byId("seletedServerId").value
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result === "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.startSuccess, "message");
                    registry.byId("userToaster").show();
                    ListServer.populateValues();
                    dijit.byId("vmActionLoader").hide(); 
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.startError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmActionLoader").hide(); 
                }
            });    
        });
        dijit.byId("startDialog").hide();  
    },
    
    'getFlavorList': function() {
        
        if (dijit.byId("flavorListsWidget")) {
            dijit.byId("flavorListsWidget").destroyRecursive();
        }
        
        var flavorRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/"
        });
        var flavorOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };
    
        var flavorList = new ItemFileWriteStore({data: flavorOptions});
        flavorRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
               flavorList.newItem({id: el.referenceId, name: el.name});
            });
            var flavorWidget = new FilteringSelect({
                store: flavorList,
                id:"flavorListsWidget",
                placeHolder: translator.common.selectNewFlavor,       
                onChange: function() {
//                        AddComputingOfferInfo.viewSelectedZone(this);   
                }
            });
            flavorWidget.placeAt("flavorListForResize");
            flavorWidget.startup();

        });
    },
    getImageList: function() {
        
        if (dijit.byId("imageListWidget")) {
            dijit.byId("imageListWidget").destroyRecursive();
        } 
        if (dijit.byId("assignCloudEngineDialog")) {
            dijit.byId("assignCloudEngineDialog").destroyRecursive();
        } 
        var imageRestStore = new JsonRest({
            target: core.getContextPath() + "/api/image/"
        });
        var imageOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var imageList = new ItemFileWriteStore({data: imageOptions});
//        imageList.newItem({id: "", name: ""});
        imageRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
                imageList.newItem({id: el.referenceId, name: el.name});
            });
            var imageWidget = new FilteringSelect({
                store: imageList,
                id: "imageListWidget",
                required: false,
                placeHolder: translator.common.chooseImage,
                onChange: function() {
                }
            });
            imageWidget.placeAt("imageListToRebuild");
            imageWidget.startup();
        });  
    },
    'showResizeInstanceDialog': function(instanceId, flavorId) {
        dojo.byId("seletedServerId").value = instanceId; 
        dijit.byId("resizeInstancedialog").show();
        dijit.byId("flavorListsWidget").setValue(flavorId);
    },
    'resizeInstance': function() {
        var instanceId = dojo.byId("seletedServerId").value;
        var flavorId = dijit.byId("flavorListsWidget").value;
        
        var pageNode = dojo.byId("resizeInstanceFormPage");
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
        
            dijit.byId("resizeInstancedialog").hide();
            dijit.byId("vmActionLoader").show(); 
            var instanceResizeRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/resize/"         
            });

            instanceResizeRestStore.add({
                instanceId: instanceId,
                flavorId: flavorId,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.resizeInstanceSuccess, "message");
                        registry.byId("userToaster").show();
                        ListServer.populateValues();
                        dijit.byId("vmActionLoader").hide(); 
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.notResizeInstance, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vmActionLoader").hide(); 
                    }
                });                
            });
        }

    },
    'closeResizeInstanceDialog': function() {
        dijit.byId("resizeInstancedialog").hide();
    },
    'showRebuildInstanceDialog': function(instanceId, instanceName, imageId) {
        
        dojo.byId("seletedServerId").value = instanceId;
        dojo.byId("imageId").value = imageId;
        dijit.byId("rebuildInstanceDialog").show(); 
        dijit.byId("imageListWidget").setValue(imageId);
        dijit.byId("newInstanceName").setValue(instanceName);
//        dijit.byId("newInstanceName").setAttribute('disabled', true);
    },
    'rebuildInstance': function() {
        var instanceId = dojo.byId("seletedServerId").value;
        var imageId = dijit.byId("imageListWidget").value;
        var instanceName = dijit.byId("newInstanceName").value;
        
        var pageNode = dojo.byId("rebuildInstanceFormPage");
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
        
            dijit.byId("rebuildInstanceDialog").hide();
            dijit.byId("vmActionLoader").show(); 
            var instanceRebuildRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/rebuild/"         
            });

            instanceRebuildRestStore.add({
                instanceId: instanceId,
                instanceName: instanceName,
                imageId: imageId,
                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.instance.rebuildInstanceSuccess, "message");
                        registry.byId("userToaster").show();
                        ListServer.populateValues();
                        dijit.byId("vmActionLoader").hide(); 
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.notRebuilInstance, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vmActionLoader").hide(); 
                    }
                });                
            });
        }
    },
    'closeRebuildInstanceDialog': function() {
        dijit.byId("rebuildInstanceDialog").hide();
    },
    'deleteInstance' : function() {
        dijit.byId("deleteDialog").hide();
        dijit.byId("vmActionLoader").show();   
        var instanceDeleteRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/delete/"         
        });

        instanceDeleteRestStore.add({
            referenceId: dojo.byId("seletedServerId").value,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dijit.byId("vmActionLoader").hide(); 
                if(resultData.result === "OK") {

                    registry.byId("userToaster").setContent(translator.common.instance.deleteSuccess, "message");
                    registry.byId("userToaster").show();
                    ListServer.populateValues();   

                } else if(resultData.result == "DEVICE_ENABLED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.deleteVMError, "error");
                    registry.byId("userToaster").show();
                }
            });     
        }); 
          
    },
    'stopInstance': function() {     
        dijit.byId("vmActionLoader").show(); 
        var instanceStopRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/stop/"         
        });

        instanceStopRestStore.add({
            referenceId: dojo.byId("seletedServerId").value,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result === "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.stopSuccess, "message");
                    registry.byId("userToaster").show();
                    ListServer.populateValues();
                    dijit.byId("vmActionLoader").hide(); 
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.stopError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmActionLoader").hide(); 
                }
            });                
        });
        dijit.byId("stopDialog").hide();
    },
    'showTakeSnapshot' : function(data) {     
        dojo.byId("seletedServerId").value = data;
        dijit.byId("currentVMSnapshotDialog").show();
        dijit.byId("currentVMSnapshotForm").reset();
    },
    'closeCreateVMSnapshotDialog' : function() {     
     dijit.byId("currentVMSnapshotDialog").hide();
    },
    'createVMSnapshot' : function() {
       
        var vmSnapshotRest = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/takeVMSnap/"         
        });
        var pageNode = dojo.byId("currentVMSnapshotFormPage");
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
            dijit.byId("vmActionLoader").show();
            vmSnapshotRest.add({
                referenceId: dojo.byId("seletedServerId").value,
                name:dijit.byId("currentVMSnapshotName").value,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.result === "OK") {                      
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotCreated,"message");
                        registry.byId("userToaster").show();                                               
                        dijit.byId('currentVMSnapshotDialog').hide();
                        dijit.byId("vmActionLoader").hide();
                    } else if(resultData.result == "Pending") {
                        
                    } else  if(resultData.result == "FAILED") {
                        document.body.removeAttribute("style"); 
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotDeletedSuccess,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("vmActionLoader").hide();
                        dijit.byId("currentVMSnapshotDialog").hide();
                    } else {
                        document.body.removeAttribute("style");
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotCreateVmSnapshot,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("vmActionLoader").hide(); 
                        dijit.byId("currentVMSnapshotDialog").hide();
                    }
                });
            });
        }
    }
};
var NicInfo = {

   'populateValues' : function() {
       
       var serverId = dojo.byId("instanceId").value;
        
        dojo.byId("noNicMessageBox").style.display = "none";
        dojo.byId("userNicList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.nicLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true',},
                {'name': translator.common.networkName, 'field': 'networkName', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) { 
                        
                        return "<a href='#/user/network/view/" + data.network.referenceId +"' title='" + translator.common.view + "'>" + data.network.name + "</a>";

                    }
                },
                {'name': translator.common.fixedIp, 'field': 'fixedIp', 'width': '205px', 'datatype': 'string', 'autoComplete': 'true',  'formatter': function(data) {
                        
//                        return "<span class='bold'>" + "IP address: "+ data.ipAddress + " | " + "Subnet ID: "+ data.subnetId + "</span>";
                        return "<span class='bold'>" + data.ipAddress + "</span>";
                }},
                {'name': translator.common.macAddress, 'field': 'macAddress', 'width': '210px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.status.name, 'field': 'status', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},  
                {'name': translator.common.adminState, 'field': 'adminState', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},                 
            ]
        ];

        var nicRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/nicList"
        });

        nicRestStore.query({serverId : serverId}).then(function (data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userNicList").innerHTML = "";
                dojo.byId("noNicMessageBox").style.display = "block";

            } else {

                dojo.byId("noNicMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        networkName: resultData,
                        fixedIp: resultData,
                        macAddress: resultData.macAddress,
                        status: resultData.status.name,
                        adminState: resultData.adminState == true ? "UP" : "DOWN",                       
                    });
                });
                dojo.byId("userNicList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userNicList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
   }

};
var InstanceFloatingIp = {
   'externalNetworkList': function() {   
 
        if(dijit.byId("instanceExternalNetworksWidget")){
            dijit.byId("instanceExternalNetworksWidget").destroyRecursive();
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

                dojo.byId("externalNetworksList").innerHTML = "<span class= 'require'>" + translator.common.message.noExternalNetworks + "</span>";
                dojo.byId("externalNetworksListHelp").style.display = "none";
            } else {
                
                dojo.byId("externalNetworksListHelp").style.display = "block";
                dojo.forEach(data, function(el) {
                    networkList.newItem({id: el.referenceId, name: el.name});
                });
                var networkWidget = new FilteringSelect({
                    store: networkList,
                    id: "instanceExternalNetworksWidget",
                    required: true,
                    placeHolder: translator.common.selectExternalNetwork,
                    onChange: function() {
                    }
                });
                networkWidget.placeAt("externalNetworksList");
                networkWidget.startup();
            }
        });
        
    },
    'portList': function(id) {
        
        if(dijit.byId("instanceFloatingIpPortWidget")){
            dijit.byId("instanceFloatingIpPortWidget").destroyRecursive();
        }
        dojo.byId("associatePortLoader").style.display = "block";
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
        floatingPortRestStore.query({instanceId: id}).then(function(data) {
            dojo.byId("associatePortLoader").style.display = "none";
            dojo.forEach(data, function(el) {
                floatingPortList.newItem({id: el.referenceId, name: el.mappedIpAddress});
            });
            var floatingPortWidget = new FilteringSelect({
                store: floatingPortList,
                id: "instanceFloatingIpPortWidget",
                required: true,
                placeHolder: translator.common.selectPort,
                onChange: function() {
                }
            });
            floatingPortWidget.placeAt("portsList");
            floatingPortWidget.startup();
        });
        
    },
    'ipAddressList': function(id) {
        
        if(dijit.byId("instanceFloatingIpAddressWidget")){
            dijit.byId("instanceFloatingIpAddressWidget").destroyRecursive();
        }
      
        var floatingIpRestStore = new JsonRest({
            target: core.getContextPath() + "/api/floatingIp"
        });
        var floatingIpOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var floatingIpList = new ItemFileWriteStore({data: floatingIpOptions});
//        volumeList.newItem({id: "", name: ""});
        floatingIpRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
                floatingIpList.newItem({id: el.floatingIpAddress, name: el.floatingIpAddress});
            });
            var floatingIpWidget = new FilteringSelect({
                store: floatingIpList,
                id: "instanceFloatingIpAddressWidget",
                required: true,
                placeHolder: translator.common.selectIpAddress,
                onChange: function() {
                }
            });
            floatingIpWidget.placeAt("ipAddressList");
            floatingIpWidget.startup();
            if(id) {
               dijit.byId("instanceAssociateDialog").show();
            }
        });
        
    },
    'disAssociatePortList': function(id) {
        
        if(dijit.byId("instanceFloatingIpWidget")){
            dijit.byId("instanceFloatingIpWidget").destroyRecursive();
        }
      
        var floatingIpRestStore = new JsonRest({
            target: core.getContextPath() + "/api/floatingIp/instanceIp"
        });
        var floatingIpOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var floatingIpList = new ItemFileWriteStore({data: floatingIpOptions});
//        volumeList.newItem({id: "", name: ""});
        floatingIpRestStore.query({referenceId: id}).then(function(data) {

            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("disassociatePortsList").innerHTML = "<span class= 'require'>" + translator.common.message.noFloatingIps + "</span>";
                dojo.byId("ipsListHelpDiv").style.display = "none";
            } else {
                dojo.byId("ipsListHelpDiv").style.display = "block";
                dojo.byId("disassociatePortsList").innerHTML = "";
                dojo.forEach(data, function(el) {
                    floatingIpList.newItem({id: el.referenceId, name: el.fixedIpAddress});
                });
                var floatingIpsWidget = new FilteringSelect({
                    store: floatingIpList,
                    id: "instanceFloatingIpWidget",
                    required: true,
                    placeHolder: translator.common.selectIpAddress,
                    onChange: function() {
                    }
                });
                floatingIpsWidget.placeAt("disassociatePortsList");
                floatingIpsWidget.startup();
            }
            
        });
    },
    'associateDialog': function(id) {
        dojo.byId("seletedServerId").value = id;
        dijit.byId("instanceAssociateDialog").show();
        InstanceFloatingIp.portList(id);
        dijit.byId("instanceAssociateFloatingIpForm").reset();
    },
    'cancelAssociateDialog': function() {
        dijit.byId("instanceAssociateDialog").hide();
    },
    'disassociateDialog': function(id) {
        dojo.byId("seletedServerId").value = id;
        dijit.byId("instanceDisAssociateDialog").show(); 
        InstanceFloatingIp.disAssociatePortList(id);
        dijit.byId("instanceDisassociateFloatingIpForm").reset();
    },
    'cancelDisassociateDialog': function() {
         dijit.byId("instanceDisAssociateDialog").hide();    
    },
   'diassociateConfirm': function() {
       
       var status = true;
        var pageNode = dojo.byId("disassociateFloatingIpPage");
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
            
            dijit.byId("instanceDisAssociateDialog").hide(); 
            dijit.byId("instanceDisassociateFloatingIpAlert").show();
        }
       
       
   },
    'cancelDisassociate': function() {
        dijit.byId("instanceDisassociateFloatingIpAlert").hide();
        dijit.byId("instanceDisAssociateDialog").show(); 
    },
    'associate': function() {
            var status = true;
            var pageNode = dojo.byId("associateFloatingIpPage");
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

            dijit.byId("instanceAssociateDialog").hide(); 
            dijit.byId("instanceFloatingIpLoader").show(); 
            var ipAddress = dijit.byId("instanceFloatingIpAddressWidget").value;
            var instanceId = dojo.byId("seletedServerId").value;
            var portId = dijit.byId("instanceFloatingIpPortWidget").value;
            var id = dojo.byId("seletedServerId").value;
            floatingAssociateRest.put({
                ipAddress: ipAddress,
                id: id,
                instanceId: instanceId,
                portId: portId,
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                  dijit.byId("instanceFloatingIpLoader").hide(); 
                  if(resultData.result == "OK"){
                     registry.byId("userToaster").setContent(translator.common.message.floatingAssociate, "message");
                     registry.byId("userToaster").show();
                     ListServer.populateValues();
                  } else if(resultData.result == "FAILED"){
                     registry.byId("userToaster").setContent(translator.common.message.floatingIpNotAssociated, "error");
                     registry.byId("userToaster").show();

                  } else {
                     registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                     registry.byId("userToaster").show();
                  }

                });
                InstanceFloatingIp.disAssociatePortList();
            });
        }  
    },
    'disassociate': function() {
       
//        var id = dojo.byId("seletedServerId").value;
        
        var floatingDisAssociateRest =  new JsonRest({
            target: core.getContextPath() + "/api/floatingIp/disAssociate/"
        });
        
        var instanceId = dojo.byId("seletedServerId").value;
        var id = dijit.byId("instanceFloatingIpWidget").value;

        dijit.byId("instanceDisassociateFloatingIpAlert").hide(); 
        dijit.byId("instanceFloatingIpLoader").show(); 
        floatingDisAssociateRest.put({
            id: id,
            instanceId: instanceId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dijit.byId("instanceFloatingIpLoader").hide(); 
              if(resultData.result === "OK") { 
                 registry.byId("userToaster").setContent(translator.common.message.floatingdisAssociate, "message");
                 registry.byId("userToaster").show();
                 ListServer.populateValues();
              } else if(resultData.result == "DEVICE_ENABLED"){
                  
                 registry.byId("userToaster").setContent(resultData.message, "error");
                 registry.byId("userToaster").show();
              } else {
                 registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                 registry.byId("userToaster").show();
              }
          });
          InstanceFloatingIp.disAssociatePortList();
      });         
    }
};
var InstanceFloatingIpInfo = {
    'closeDialog': function() {
        dijit.byId("instanceFloatingIpLoader").hide(); 
    },
    enableMonthlyCost : function () {
         var formElements = dojo.query("#vmFloatingIPBillingTypeDiv input:checked");
         var checkedRadioId = dijit.byId(formElements[0].id);        
         var billingType = dijit.byId(checkedRadioId).value;
        
        var misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });
        misclRestStore.query({id: 6}).then(function(data) {
            if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
                dojo.byId("vmFloatingIPCostInfo").style.display = "none";                
            } else {                                                   
                dojo.forEach(data, function(ipCost) {
                    var monthCost = ipCost.cost * 720;
                    if(billingType === "monthly") {
                        dojo.byId("vmFloatingIPCost").innerHTML = ipCost.currency + "  "+ localeCurrency.format(monthCost, {places: 2, locale: dojo.locale}) + "   /  " + translator.common.floatingIPMonth; 
                    } else {
                        dojo.byId("vmFloatingIPCost").innerHTML = ipCost.currency + "  "+ localeCurrency.format(ipCost.cost, {places: 5, locale: dojo.locale}) + "   /  " + translator.common.floatingIPHr;    
                    }                                      
                });
                dojo.byId("vmFloatingIPCostInfo").style.display = "block";
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
                dojo.query("#vmFloatingIPBillingTypeDiv").removeClass("hide_text", true);
            } else {
                dojo.query("#vmFloatingIPBillingTypeDiv").toggleClass("hide_text", true);
            }
        });
        
        misclRestStore.query({id: 6}).then(function(data) {
            if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
                dojo.byId("vmFloatingIPCostInfo").style.display = "none";                
            } else {                                                   
                dojo.forEach(data, function(ipCost) {                    
                    dojo.byId("vmFloatingIPCost").innerHTML = ipCost.currency + "  "+ localeCurrency.format(ipCost.cost, {places: 5, locale: dojo.locale}) + "   /  " + translator.common.floatingIPHr;                   
                });
                dojo.byId("vmFloatingIPCostInfo").style.display = "block";
            }
        });  
        
        dijit.byId("instanceAssociateDialog").hide();
        dijit.byId("instanceCreateFloatingIpDialog").show();
        dijit.byId("instaceCreateFloatingIpForm").reset();
    },
    'create': function() {
        
        var status = true;
        var pageNode = dojo.byId("floatingIpPage");
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
            
            var formElements = dojo.query("#vmFloatingIPBillingTypeDiv input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var billingType = dijit.byId(checkedRadioId).value;
            
            dijit.byId("instanceCreateFloatingIpDialog").hide(); 
            dijit.byId("instanceFloatingIpLoader").show(); 
            var networkId = dijit.byId("instanceExternalNetworksWidget").value;
            floatingAssociateRest.add({
                networkId: networkId,
                billingType : billingType
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                  if(resultData.result == "OK"){
                     dijit.byId("instanceFloatingIpLoader").hide(); 
                     registry.byId("userToaster").setContent(translator.common.message.floatingIpAllocated, "message");
                     registry.byId("userToaster").show();
                     InstanceFloatingIp.ipAddressList(networkId);
                     
                  } else {
                     registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                     registry.byId("userToaster").show();
                  }

                });

            });
        } 
        
        
    },
    'cancelFloatingIp': function() {
        dijit.byId("instanceCreateFloatingIpDialog").hide();
        dijit.byId("instanceAssociateDialog").show();
    },
};
var InstanceMonitoring = {

    'init': function() {
        var serverId = dojo.byId("instanceId").value;
        
        if(dijit.byId("instanceMonitoringIpListWidget")) {
            dijit.byId("instanceMonitoringIpListWidget").destroyRecursive();                                            
        }
        if(dijit.byId("instanceCollectorListWidget")) {
            dijit.byId("instanceCollectorListWidget").destroyRecursive();                                            
        }

        var monitoringIpRestStore = new JsonRest({
            target: core.getContextPath() + "/api/floatingIp/instanceIp"
        });
        var monitoringIpOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var monitoringIpList = new ItemFileWriteStore({data: monitoringIpOptions});
//        imageList.newItem({id: "", name: ""});
        monitoringIpRestStore.query({referenceId: serverId}).then(function(data) {

            dojo.forEach(data, function(el) {
                monitoringIpList.newItem({id: el.floatingIpAddress, name: el.floatingIpAddress});
            });
            var monitoringIpWidget = new FilteringSelect({
                store: monitoringIpList,
                id: "instanceMonitoringIpListWidget",
                required: false,
                placeHolder: translator.common.chooseIp,
                onChange: function() {
                }
            });
            monitoringIpWidget.placeAt("instanceMonitoringIpList");
            monitoringIpWidget.startup();
        });
        
        var collectorsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/monitoring/collectorList"
        });
        var collectorsOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var collectorList = new ItemFileWriteStore({data: collectorsOptions});
//        imageList.newItem({id: "", name: ""});
        collectorsRestStore.query().then(function(data) {
            
            dojo.forEach(data, function(el) {
                console.log("el "+el[0])
                collectorList.newItem({id: el[0], name: el[0]});
            });
            var collectorWidget = new FilteringSelect({
                store: collectorList,
                id: "instanceCollectorListWidget",
                required: true,
                placeHolder: translator.common.chooseCollector,
                onChange: function() {
                }
            });
            collectorWidget.placeAt("instanceCollectorList");
            collectorWidget.startup();
        });   
        
        
        var devicesEnableStateStore = new JsonRest({
            target: core.getContextPath() + "/api/monitoring/deviceStatus"
        });
        var deviceId = dojo.byId("instanceDeviceId").value;
        GraphInfo.showFirstGraph();
         if(deviceId)  {
             devicesEnableStateStore.query({deviceId: deviceId}).then(function(deviceResult) {                    
                 if(deviceResult[0].deviceStatus === "RUNNING") {
                     dojo.query("#instanceMonitoringDiv").toggleClass("hide_text", true);
                    dojo.query("#monitoringContainerLoader").removeClass("hide_text", true);   
                } else if(deviceResult[0].deviceStatus === "COMPLETED") {
                    dojo.query("#instanceMonitoringDiv").removeClass("hide_text", true);
                    dojo.query("#monitoringContainerLoader").toggleClass("hide_text", true);   
                } else {
                    dojo.query("#instanceMonitoringDiv").removeClass("hide_text", true);
                    dojo.query("#monitoringContainerLoader").toggleClass("hide_text", true);   
                }
            });
         } else {
             dojo.query("#instanceMonitoringDiv").toggleClass("hide_text", true);
             dojo.query("#monitoringContainerLoader").toggleClass("hide_text", true);  
         }          
                      
   },
   'validateEmail': function(elem) {
       
       if(elem.value) {                                    
            var email= elem.value;
            var emails = email.split(",");  
            console.log("emails: "+emails)

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
           
           for(var i=0;i < emails.length; i++) {
               
                if(emails[i].trim().length > 0 && ! re.test(emails[i])) {
                    console.log("false-----")
                    dijit.byId("alarmReciepient").focus();
                    registry.byId("userToaster").setContent(translator.common.message.invalidEmailInList, "error");
                    registry.byId("userToaster").show(); 
                }
 
            } 
        }
           
   },
   'checkIsDeviceEnabled': function(referenceId) {              
       
       var devicesRestStore = new JsonRest({
           target: core.getContextPath() + "/api/monitoring/deviceList"
       });                        
       devicesRestStore.query({instanceId: referenceId}).then(function(data) {            
           
           if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                    
                dojo.byId("monitoringDeviceFormDiv").style.display = "block";
                dojo.byId("instanceDeviceViewDiv").style.display = "none";
                dojo.query("#instanceMonitoringDiv").toggleClass("hide_text", true);
                dojo.query("#monitoringContainerLoader").toggleClass("hide_text", true);    
                InstanceMonitoring.init();
                dijit.byId("vmInfoTabContainer").resize();

            } else {
                
                dojo.forEach(data, function(resultData) {
                    
                if((resultData.jobId !== null && resultData.jobStatus === "SUCCESS") ||(resultData.jobId !== null && resultData.jobStatus === "STARTED")) {
                    var osType = resultData.osType;
                    var deviceId = resultData.refrenceId;
                    dojo.byId("deviceOsType").value = osType;
                    dojo.byId("instanceDeviceId").value = deviceId;
                    console.log("resultData.osType"+resultData.osType)
                    dojo.byId("monitoringDeviceFormDiv").style.display = "none";
                    dojo.byId("instanceDeviceViewDiv").style.display = "block";

                    dojo.byId("monitorCollectorInfo").innerHTML = resultData.collector;
                    dojo.byId("monitorIpInfo").innerHTML = resultData.deviceName;
                    dojo.byId("monitorSnmpPortInfo").innerHTML = resultData.snmpPort;
                    dojo.byId("monitorSnmpCommunityInfo").innerHTML = resultData.snmpCommunity;
                    dojo.byId("monitorOsTypeInfo").innerHTML = resultData.osType;


                    //Enable monitoring                   
                    
                    var devicesEnableStateStore = new JsonRest({
                        target: core.getContextPath() + "/api/monitoring/deviceStatus"
                    });
                    
                    devicesEnableStateStore.query({deviceId: deviceId}).then(function(deviceResult) {                    
                        if(deviceResult[0].deviceStatus === "RUNNING") {
                            dojo.query("#instanceMonitoringDiv").toggleClass("hide_text", true);
                            dojo.query("#monitoringContainerLoader").removeClass("hide_text", true); 
                            dijit.byId("vmInfoTabContainer").resize();
                        } else if(deviceResult[0].deviceStatus === "COMPLETED") {
                            dojo.query("#instanceMonitoringDiv").removeClass("hide_text", true);
                            dojo.query("#monitoringContainerLoader").toggleClass("hide_text", true);   
                            dijit.byId("vmInfoTabContainer").resize();
                        } else {
                            dojo.query("#instanceMonitoringDiv").removeClass("hide_text", true);
                            dojo.query("#monitoringContainerLoader").toggleClass("hide_text", true);   
                            dijit.byId("vmInfoTabContainer").resize();
                        }
                    });
                    
                    InstanceMonitoring.alarmInit();
                    InstanceMonitoring.alarmFileSystemInit(osType, deviceId);
                    InstanceAlarm.populateAlertValues();

                } 
                
            });
            
            }
            
        });
   },
   'alarmInit': function() {
       
       if(dijit.byId("notificationTopicListWidget")) {
            dijit.byId("notificationTopicListWidget").destroyRecursive();                                            
       }  
       
//       var device = "/zport/dmd/Devices/Server/Linux/devices/192.168.1.154";
//       var os = "LINUX";
       
       dojo.byId("alarmThresholdMinValueDiv").style.marginLeft = "12px";
//       dojo.byId("alarmThresholdMaxValueDiv").style.marginLeft = "12px";
       dojo.byId("monitorAlarmPortDiv").style.marginLeft = "12px";
       dijit.byId("instanceAlertName").set("required", false);
       dijit.byId("monitorAlarmPort").set("required", false);

       var topicRestStore = new JsonRest({
           target: core.getContextPath() + "/api/notificationTopic"
       });
       var topicOptions = {
            identifier: 'id',
            label: 'name',
            items: []
       };

       var topicList = new ItemFileWriteStore({data: topicOptions});
//        imageList.newItem({id: "", name: ""});
       topicRestStore.query().then(function(data) {

           dojo.forEach(data, function(el) {
              topicList.newItem({id: el.id, name: el.name});
           });
           var topicWidget = new FilteringSelect({
               store: topicList,
               id: "notificationTopicListWidget",
               required: false,
               placeHolder: translator.common.chooseTopic,
               onChange: function() {
               }
           });
           topicWidget.placeAt("alarmNameList");
           topicWidget.startup();
       });
       dijit.byId("vmInfoTabContainer").resize();

   },
   'alarmFileSystemInit': function(osType, deviceId) {
       
       if(dijit.byId("fileSystemPartitionListWidget")) {
            dijit.byId("fileSystemPartitionListWidget").destroyRecursive();                                            
       }
       
       var fsPartitionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/alarm/fileSystem/PartitionList"
        });
        var partitionOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
//       var deviceId = "/zport/dmd/Devices/Server/Linux/devices/192.168.1.154";
//       var osType = "LINUX";

        var partitionList = new ItemFileWriteStore({data: partitionOptions});
//        imageList.newItem({id: "", name: ""});
        fsPartitionRestStore.query({
           osType: osType,
           deviceId: deviceId
        }).then(function(data) {

            dojo.forEach(data, function(el) {
                partitionList.newItem({id: el[0], name: el[1]});
            });
            var partitionWidget = new FilteringSelect({
                store: partitionList,
                id: "fileSystemPartitionListWidget",
                required: false,
                placeHolder: translator.common.choosePartition,
                onChange: function() {
                }
            });
            partitionWidget.placeAt("fileSystemPartitionList");
            partitionWidget.startup();
        }); 
        dijit.byId("vmInfoTabContainer").resize();
   },
   'PartitionList': function() {
       
   },     
    'enableDevice': function() {
       
       var serverId = dojo.byId("instanceId").value;
       var status = true;
        var pageNode = dojo.byId("monitoringDeviceContent");
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
                            
            var collector = dijit.byId("instanceCollectorListWidget").value;
            var monitoringIp = dijit.byId("instanceMonitoringIpListWidget").value;
            var snmpPort = dijit.byId("instanceSnmpPort").value;
            var snmpCommunity = dijit.byId("snmpCommunity").value;
            var monitorOsType = dijit.byId("monitorOsType").value;
            var deviceRest = new JsonRest({
                target : core.getContextPath() +"/api/monitoring/addDevice"
            });
            dojo.byId("monitorDeviceAddButtonDiv").style.display = "none";
            dojo.byId("monitorAddButtonLoader").style.display = "block"; 
            
            dojo.query("#instanceMonitoringDiv").toggleClass("hide_text", true);
            dojo.query("#monitoringContainerLoader").removeClass("hide_text", true);
                    
            deviceRest.add({
                collector: collector,
                instanceId: serverId,
                monitoringIp: monitoringIp,
                snmpPort: snmpPort,
                snmpCommunity: snmpCommunity,
                monitorOsType: monitorOsType
            }).then(function (data) {
               dojo.forEach(data, function(resultData) {
                   if(resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.instance.monitorDeviceAdded, "message");
                        registry.byId("userToaster").show(); 
                        
                        var deviceStatusInterval = setInterval (function() {
                            
                            InstanceMonitoring.deviceEnabledStatus(resultData.deviceId, resultData.uuid, deviceStatusInterval);
                        }, 5000);
                    } else if(resultData.localizedMessage) {
                        registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("userToaster").show();
                        
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.monitorDeviceNotAdded, "error");
                        registry.byId("userToaster").show(); 
                    }
               }); 
            });
        }
    },
    'deviceEnabledStatus': function(deviceId, jobId, deviceStatusInterval) {
        var deviceStore = new JsonRest({
            target: core.getContextPath()+"/api/monitoring/getDeviceStatus"
        });
        var serverId = dojo.byId("instanceId").value;
        dojo.byId("instanceDeviceId").value = deviceId;
        
        deviceStore.add({
            jobId: jobId,
            deviceId: deviceId,
            instanceId: serverId,
        }).then(function(data){
            dojo.forEach(data, function(resultData) {                
               if(resultData.status === "SUCCESS" || resultData.status === "STARTED") {
//                    dijit.byId("instanceMonitoringLoaderDiv").hide();
                   dojo.byId("monitorAddButtonLoader").style.display = "none";
                   dojo.byId("monitorDeviceAddButtonDiv").style.display = "block";
                   dojo.query("#instanceMonitoringDiv").toggleClass("hide_text", true);
                   dojo.query("#monitoringContainerLoader").removeClass("hide_text", true);
                   dijit.byId("graphMonitoringTab").resize();
                   clearInterval(deviceStatusInterval);
                   registry.byId("userToaster").setContent(translator.common.instance.monitorDeviceSuccess, "message");
                   registry.byId("userToaster").show();
                   dijit.byId("vmInfoTabContainer").resize();
                   
                    //get alarm intial values
                   InstanceMonitoring.checkIsDeviceEnabled(serverId);
               } else {
//                   dojo.query("#instanceMonitoringDiv").toggleClass("hide_text", true);
//                   dojo.query("#monitoringContainerLoader").toggleClass("hide_text", true);   
                   dijit.byId("vmInfoTabContainer").resize();
//                   dijit.byId("instanceMonitoringLoaderDiv").show();
               }
            });
        });
    },
    'diableDeviceAlert': function() {
       dijit.byId("monitoringDisableAlert").show();  
    },
    'diableDevice': function() {
        
        var disableDeviceStore = new JsonRest({
            target: core.getContextPath()+"/api/monitoring/disbleDevice"
        }); 
        if(dijit.byId("monitoringDisableAlert")) {
            dijit.byId("monitoringDisableAlert").hide(); 
        } 
        if(dijit.byId("vmListmonitoringDisableAlert")) {
            dijit.byId("vmListmonitoringDisableAlert").hide()
        }
        
        var serverId = dojo.byId("instanceId").value;
        
        dojo.byId("instanceMonitoringDiv").style.display = "none";
        dojo.byId("monitorDisbleDeviceBtn").style.display = "none";
        dojo.byId("monitorDisableButtonLoader").style.display = "block";
        
        disableDeviceStore.add({
            instanceId: serverId,          
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
               if(resultData.status === "SUCCESS") {
                    dojo.byId("monitorDisbleDeviceBtn").style.display = "block";
                    dojo.byId("monitorDisableButtonLoader").style.display = "none";
                    registry.byId("userToaster").setContent(translator.common.instance.monitorDevicedisabled, "message");
                    registry.byId("userToaster").show(); 
                    InstanceMonitoring.checkIsDeviceEnabled(serverId);  
                    dojo.byId("instanceDeviceId").value = "";
                } else if(resultData.result === "FALSE"){
                    registry.byId("userToaster").setContent(translator.common.message.zenossDown, "error");
                    registry.byId("userToaster").show(); 
                } else {
                    registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                    registry.byId("userToaster").show(); 
                }
            });
        });
    },
    'cancelDiableDevice': function() {
        if(dijit.byId("monitoringDisableAlert")) {
             dijit.byId("monitoringDisableAlert").hide();
        } 
        if(dijit.byId("vmListmonitoringDisableAlert")) {
             dijit.byId("vmListmonitoringDisableAlert").hide();
        }
       
    },
    
};
var InstanceAlarm = {

   'getDeviceDetails': function() {
      var deviceRest = new JsonRest({
         target: core.getContextPath()+ "/api/device" 
      });  
      var serverId = dojo.byId("instanceId").value;
      deviceRest.query({serverId: serverId}).then(function (data) {
          dojo.forEach(data, function (resultData){
            dojo.byId("monitorCollectorInfo").innerHTML = resultData.collector;  
            dojo.byId("monitorSnmpPortInfo").innerHTML = resultData.snmpPort;  
            dojo.byId("monitorSnmpCommunityInfo").innerHTML = resultData.snmpCommunity;  
            dojo.byId("monitorOsTypeInfo").innerHTML = resultData.osType;  
          });
      });
   },
   'createTopicDiv': function() {
       dojo.byId("alarmThresholdMinValueDiv").style.marginLeft = "20px";
       dojo.byId("alarmThresholdMaxValueDiv").style.marginLeft = "10px";
       dojo.byId("monitorAlarmPortDiv").style.marginLeft = "20px";
       dojo.byId("instanceAlarmNameListDiv").style.display = "none";
       dojo.byId("instanceAlarmNameDiv").style.display = "block";
       dojo.byId("alarmReciepientDiv").style.display = "block";
       dijit.byId("instanceAlertName").set("required", true);
       dijit.byId("notificationTopicListWidget").set("required", false);
   },
   'changeAlertType': function(selectWidget) {
       
       var osType = dojo.byId("deviceOsType").value;
       if(selectWidget.value == "cpu" && osType == "LINUX") {
           
           dojo.byId("monitorAlertTypeCpuDiv").style.display = "block";
           dojo.byId("monitorAlarmPortDiv").style.display = "none";
           dojo.byId("alarmThresholdMinValueDiv").style.display = "block";
           dojo.byId("alarmThresholdMaxValueDiv").style.display = "block";
           dojo.byId("monitorAlertTypeDiskDiv").style.display = "none";
           dojo.byId("fileSystemPartitionListDiv").style.display = "none";
           dijit.byId("monitorAlarmPort").setValue("");
           dijit.byId("monitorAlarmPort").set("required", false);
           dijit.byId("alarmThresholdMinValue").set("required", true);
           dijit.byId("alarmThresholdMaxValue").set("required", true);
           if(dijit.byId("fileSystemPartitionListWidget")) {
               dijit.byId("fileSystemPartitionListWidget").set("required", false);
           }
           
           
       } else if(selectWidget.value == "ipService") {
           
           dojo.byId("monitorAlarmPortDiv").style.display = "block";
           dojo.byId("monitorAlertTypeCpuDiv").style.display = "none";
           dojo.byId("alarmThresholdMinValueDiv").style.display = "none";
           dojo.byId("alarmThresholdMaxValueDiv").style.display = "none";
           dojo.byId("monitorAlertTypeDiskDiv").style.display = "none";
           dojo.byId("fileSystemPartitionListDiv").style.display = "none";
           dojo.byId("monitorAlertFileTypeDiv").style.display = "none";
           dijit.byId("alarmThresholdMinValue").setValue("");
           dijit.byId("alarmThresholdMaxValue").setValue("");
           dijit.byId("monitorAlarmPort").set("required", true);
           dijit.byId("alarmThresholdMinValue").set("required", false);
           dijit.byId("alarmThresholdMaxValue").set("required", false);
           if(dijit.byId("fileSystemPartitionListWidget")) {
               dijit.byId("fileSystemPartitionListWidget").set("required", false);
           }
           
           
       } else if(selectWidget.value == "disk") {
           
           dojo.byId("monitorAlarmPortDiv").style.display = "none";
           dojo.byId("monitorAlertTypeCpuDiv").style.display = "none";
           dojo.byId("alarmThresholdMinValueDiv").style.display = "block";
           dojo.byId("alarmThresholdMaxValueDiv").style.display = "block";
           dojo.byId("monitorAlertTypeDiskDiv").style.display = "block";
           dojo.byId("fileSystemPartitionListDiv").style.display = "none";
           dojo.byId("monitorAlertFileTypeDiv").style.display = "none";
           dijit.byId("monitorAlarmPort").setValue("");
           dijit.byId("monitorAlarmPort").set("required", false);
           dijit.byId("alarmThresholdMinValue").set("required", true);
           dijit.byId("alarmThresholdMaxValue").set("required", true); 
           if(dijit.byId("fileSystemPartitionListWidget")) {
               dijit.byId("fileSystemPartitionListWidget").set("required", false);
           }
           
       } else if(selectWidget.value == "fileSystem") {
           
           dojo.byId("monitorAlarmPortDiv").style.display = "none";
           dojo.byId("monitorAlertTypeCpuDiv").style.display = "none";
           dojo.byId("alarmThresholdMinValueDiv").style.display = "block";
           dojo.byId("alarmThresholdMaxValueDiv").style.display = "block";
           dojo.byId("fileSystemPartitionListDiv").style.display = "block";
           dojo.byId("monitorAlertTypeDiskDiv").style.display = "none";
           dojo.byId("monitorAlertFileTypeDiv").style.display = "block";
           dijit.byId("monitorAlarmPort").setValue("");
           dijit.byId("monitorAlarmPort").set("required", false);
           dijit.byId("alarmThresholdMinValue").set("required", true);
           dijit.byId("alarmThresholdMaxValue").set("required", true); 
           if(dijit.byId("fileSystemPartitionListWidget")) {
               dijit.byId("fileSystemPartitionListWidget").set("required", true);
           }
           
       } else {
           
           dojo.byId("monitorAlertTypeCpuDiv").style.display = "none";
           dojo.byId("alarmThresholdMinValueDiv").style.display = "block";
           dojo.byId("alarmThresholdMaxValueDiv").style.display = "block";
           dojo.byId("monitorAlarmPortDiv").style.display = "none";
           dojo.byId("monitorAlertTypeDiskDiv").style.display = "none";
           dojo.byId("fileSystemPartitionListDiv").style.display = "none";
           dojo.byId("monitorAlertFileTypeDiv").style.display = "none";
           dijit.byId("monitorAlarmPort").setValue("");
           dijit.byId("monitorAlarmPort").set("required", false);
           dijit.byId("alarmThresholdMinValue").set("required", true);
           dijit.byId("alarmThresholdMaxValue").set("required", true);
           if(dijit.byId("fileSystemPartitionListWidget")) {
               dijit.byId("fileSystemPartitionListWidget").set("required", false);
           }
           
       }
       
   },
   'populateAlertValues' : function() {
       
       var serverId = dojo.byId("instanceId").value;
        
        dojo.byId("noMonitorAlertMessageBox").style.display = "none";
        dojo.byId("moniteringAlertList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.alertLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': "ID", 'field': 'id', 'width': '200px', 'datatype': 'string', 'hidden': 'true',},
                {'name': translator.common.alarmIdentifier, 'field': 'alertName', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.topic, 'field': 'topic', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.alertType, 'field': 'alertType', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true','formatter': function (data) {
                        if(data.type === "MEMORY_WINDOWS" || data.type === "MEMORY_LINUX") {
                            return "Memory Utilization";
                        } else if(data.type === "CPU_WINDOWS" || data.subType === "CPU_LINUX_SYSTEM" || data.subType === "CPU_LINUX_USER") {
                            return "CPU Utilization";
                        } else if(data.type === "disk") {
                            return "Disk I/O utilization";
                        } else if(data.type === "fileSystem") {
                            return "File System Utilization";
                        } else if(data.type === "ipService") {
                            return "Port Monitoring";
                        } else {
                            return data.type;
                        }
                    }
                },
                {'name': translator.common.thresholdValue, 'field': 'thresholdValue', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true','formatter': function (data) {
                    
                        if(data.type === "ipService") {
                            return data.portNumber;
                        } else {
                            return data.threshold;
                        }
                    }
                }, 
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function (data) {
                        
                        var menu = new DropDownMenu({style: "display: none;"});
                                                
                        if(data.type == "ipService") {
                            console.log("data.portEnabled"+data.portEnabled)
                            if(data.portEnabled == true) {
                                
                                var disableMenu = new MenuItem({
                                    label: translator.common.disable,
                                    onClick: function() {
                                       InstanceAlarm.disableAlertDialog(data.referenceId)
                                    }
                                });
                                menu.addChild(disableMenu);
                                menu.startup(); 
                            } else {
                                var enableMenu = new MenuItem({
                                    label: translator.common.enable,
                                    onClick: function() {
                                       InstanceAlarm.enable(data.referenceId)
                                    }
                                });
                                menu.addChild(enableMenu);
                                menu.startup(); 
                            }
                            
                            
                        } else {
                            
                            var editMenu = new MenuItem({
                                label: translator.common.edit,
                                onClick: function() {
                                    InstanceAlarm.edit(data);
                                }
                            });
                            menu.addChild(editMenu);
                            menu.startup();
                            
                            var deleteMenu = new MenuItem({
                                label: translator.common.deleteData,
                                onClick: function() {
                                   InstanceAlarm.deleteDialog(data.referenceId)
                                }
                            });
                            menu.addChild(deleteMenu);
                            menu.startup(); 
                        }
                        
                                          
                        
                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });
                        return button;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
                                
            ]
        ];

        var alarmRestStore = new JsonRest({
            target: core.getContextPath() + "/api/alarm"
        });

        alarmRestStore.query({serverId : serverId}).then(function (data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("moniteringAlertList").innerHTML = "";
                dojo.byId("noMonitorAlertMessageBox").style.display = "block";

            } else {

                dojo.byId("noMonitorAlertMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        alertName: resultData.name,
                        topic: resultData.topic.name,
                        alertType: resultData,
                        thresholdValue: resultData,
                        action: resultData,
                                              
                    });
                });
                dojo.byId("moniteringAlertList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("moniteringAlertList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
        dijit.byId("vmInfoTabContainer").resize();
   },
   'cancelAlert': function() {
       
       dojo.byId("monitorAlertAddButtonDiv").style.display = "block";
       dojo.byId("monitorAlertUpdateButtonDiv").style.display = "none";
       dojo.byId("alarmThresholdMinValueDiv").style.marginLeft = "12px";
//       dojo.byId("alarmThresholdMaxValueDiv").style.marginLeft = "12px";
       dojo.byId("monitorAlarmPortDiv").style.marginLeft = "12px";
       dijit.byId("instanceAlarmContentForm").reset();
       dijit.byId("instanceAlertName").set("required", false);
       dojo.byId("instanceAlarmNameListDiv").style.display = "block";
       dojo.byId("instanceAlarmNameDiv").style.display = "none";
       dojo.byId("alarmReciepientDiv").style.display = "none";
       dojo.byId("monitorAlertFileTypeDiv").style.display = "none";
       dojo.byId("fileSystemPartitionListDiv").style.display = "none";
       dijit.byId("notificationTopicListWidget").set("required", true);
       if(dijit.byId("fileSystemPartitionListWidget")) {
            dijit.byId("fileSystemPartitionListWidget").set("required", false);
        }
       dijit.byId("monitorAlarmPort").set("required", false);
       
       //remove disable after update
       dijit.byId("monitorAlertType").setAttribute('disabled', false);
       dijit.byId("monitorAlarmPort").setAttribute('disabled', false);
       dijit.byId("monitorAlertTypeCpu").setAttribute('disabled', false);
       dijit.byId("monitorAlertTypeDisk").setAttribute('disabled', false);
   },
   'addAlert': function() {
        var instanceId = dojo.byId("instanceId").value;
        var status = true;
        var pageNode = dojo.byId("instanceAlarmContentPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        
        var thresholdMinValue = dijit.byId("alarmThresholdMinValue").value;
        var thresholdMaxValue = dijit.byId("alarmThresholdMaxValue").value;
        
        var reciepient = dijit.byId("alarmReciepient").value;
        var elem = dijit.byId("alarmReciepient");
        
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
            
            if(thresholdMinValue > thresholdMaxValue) {
                console.log("min greater")
               dijit.byId("alarmThresholdMinValue").focus();
//               dijit.byId("alarmThresholdMaxValue").set("value not allowed", "error");
               dijit.byId("alarmThresholdMaxValue").focus();
               registry.byId("userToaster").setContent(translator.common.minGreater, "error");
               registry.byId("userToaster").show(); 
               
               InstanceMonitoring.validateEmail(elem)
               
            } else {
                
                dojo.byId("alarmButtonDiv").style.display = "none";
                dojo.byId("addAlarmButtonLoader").style.display = "block";
                
                var name = dijit.byId("instanceAlertName").value;
                var alarmListName = dijit.byId("notificationTopicListWidget").value;
                var topicListName;
                var topicName;
                if(alarmListName !== "") {
                    topicListName = alarmListName;
                }
                if(name !== "") {
                    topicName = name;
                }
                var type = dijit.byId("monitorAlertType").value;
                var fileType = dijit.byId("monitorAlertFileType").value;
                console.log("alert type :"+type)
                var alertType = "";
                var subCpuType = "";
                var osType = dojo.byId("deviceOsType").value;
                if(type === "memory" && osType === "LINUX") {
                    alertType = "MEMORY_LINUX";
                } else if(type === "memory" && osType == "WINDOWS") {
                    console.log("windows memory")
                    alertType = "MEMORY_WINDOWS";
                } else if(type == "cpu" && osType == "LINUX") {
                    alertType = "CPU_LINUX";
                    subCpuType = dijit.byId("monitorAlertTypeCpu").value;
                } else if(type == "cpu" && osType == "WINDOWS") {
                    alertType = "CPU_WINDOWS";
                } else {
                    alertType = type;
                } 
                console.log("alertType" +alertType)
                
                var subDiskType = dijit.byId("monitorAlertTypeDisk").value;
                var alertSubDiskType;
                
                //alarm disk read and write
                if(subDiskType == "ioRead" && osType == "LINUX") {
                    alertSubDiskType = "DISK_IO_READ_LINUX";
                } else if(subDiskType == "ioWrite" && osType == "LINUX") {
                    alertSubDiskType = "DISK_IO_WRITE_LINUX";
                } else if(subDiskType == "ioRead" && osType == "WINDOWS") {
                    alertSubDiskType = "DISK_IO_READ_WINDOWS";
                } else if(subDiskType == "ioWrite" && osType == "WINDOWS") {
                    alertSubDiskType = "DISK_IO_WRITE_WINDOWS";
                }
                
                //Alarm file system read and write
                var subFileType = dijit.byId("monitorAlertFileType").value;
                var alarmSubFileType;
                
                if((subFileType == "read" || subFileType == "write")  && osType == "LINUX") {
                    alarmSubFileType = "FILE_LINUX";
                } else if(subFileType == "read" && osType == "WINDOWS") {
                    alarmSubFileType = "FILE_WINDOWS_READ";
                } else if(subFileType == "write" && osType == "WINDOWS") {
                    alarmSubFileType = "FILE_WINDOWS_WRITE";
                }
                
                var partitionId;
                var partition;
                if(alertType === "fileSystem") {
                    partitionId = dijit.byId("fileSystemPartitionListWidget").value;
                    
                    dijit.byId("fileSystemPartitionListWidget").store.fetch({query: {id: partitionId},
                        onItem: function(item) {
                            if(item) { 
                                partition = item.name;
                                console.log("partitionName"+partition)
                            }
                        }
                    });
                }    

                var port = dijit.byId("monitorAlarmPort").value;
                
                var alertRest = new JsonRest({
                    target: core.getContextPath() + "/api/alarm"
                });

                alertRest.add({
                    name: topicName,
                    topicListName: topicListName, 
                    osType: osType,
                    type: alertType,
                    subCpuType: subCpuType,
                    subDiskType: alertSubDiskType,
                    subFileType: alarmSubFileType,
                    reciepient: reciepient,
                    thresholdMinValue: thresholdMinValue,
                    thresholdMaxValue: thresholdMaxValue,
                    partitionId: partitionId,
                    mountPoint: partition,
                    port: port,
                    instanceId: instanceId

                }).then(function(data) {
                    dojo.forEach(data, function (resultData) {
                        dojo.byId("alarmButtonDiv").style.display = "block";
                        dojo.byId("addAlarmButtonLoader").style.display = "none";
                        if(resultData.result === "OK") {
                            registry.byId("userToaster").setContent(translator.common.instance.monitorAlertSuccess, "message");
                            registry.byId("userToaster").show(); 
                            dijit.byId("instanceAlarmContentForm").reset();
                            InstanceAlarm.populateAlertValues();
                            
                        } else {
                            registry.byId("userToaster").setContent(translator.common.instance.monitorNotAlert, "error");
                            registry.byId("userToaster").show(); 
                        }
                    });
                });
                
            }
            
            
        }
   },
   'edit': function(resultData) {
       
        var instanceId = dojo.byId("instanceId").value;
        
        dojo.byId("currentAlarmId").value = resultData.referenceId;

        var osType = dojo.byId("deviceOsType").value;
        var deviceId = dojo.byId("instanceDeviceId").value;
        
        //Get file system partition list
//        InstanceMonitoring.alarmFileSystemInit(osType, deviceId);

        dojo.byId("monitorAlertAddButtonDiv").style.display = "none";
        dojo.byId("monitorAlertUpdateButtonDiv").style.display = "block";

        dijit.byId("notificationTopicListWidget").setValue(resultData.topic.id);
        dijit.byId("alarmThresholdMinValue").setValue(resultData.minValue);
        dijit.byId("alarmThresholdMaxValue").setValue(resultData.maxValue);

        if(resultData.type == "MEMORY_LINUX" || resultData.type == "MEMORY_WINDOWS") {
             dijit.byId("monitorAlertType").setValue("memory"); 
             dijit.byId("monitorAlertType").setAttribute('disabled', true);
             
        } else if(resultData.type == "MEMORY_LINUX") {
             dijit.byId("monitorAlertType").setValue("memory");
             dijit.byId("monitorAlertType").setAttribute('disabled', true);

        } else if(resultData.type == "ipService") {

             dijit.byId("monitorAlertType").setValue("ipService"); 
             dojo.byId("monitorAlarmPortDiv").style.display = "block";
             dijit.byId("monitorAlarmPort").setValue(resultData.portNumber);
             dijit.byId("monitorAlarmPort").setAttribute('disabled', true);

        } else if(resultData.type == "fileSystem") {
             dijit.byId("monitorAlertType").setValue("fileSystem");
             dijit.byId("monitorAlertType").setAttribute('disabled', true);
             dojo.byId("fileSystemPartitionListDiv").style.display = "block";
             dijit.byId("fileSystemPartitionListWidget").setValue(resultData.partition);
             dijit.byId("fileSystemPartitionListWidget").setAttribute('disabled', true);
             
             if(resultData.subType == "FILE_WINDOWS_READ") {
                 dojo.byId("monitorAlertFileTypeDiv").style.display = "block";
                 dijit.byId("monitorAlertFileType").setValue("read");
                 dijit.byId("monitorAlertFileType").setAttribute('disabled', true);
             } else if(resultData.subType == "FILE_WINDOWS_WRITE") {
                 dojo.byId("monitorAlertFileTypeDiv").style.display = "block";
                 dijit.byId("monitorAlertFileType").setValue("write");
                 dijit.byId("monitorAlertFileType").setAttribute('disabled', true);
             } else {
                 dojo.byId("monitorAlertFileTypeDiv").style.display = "none";
             }

        } else if(resultData.type == "CPU_WINDOWS") {
             dijit.byId("monitorAlertType").setValue("cpu"); 
             dijit.byId("monitorAlertType").setAttribute('disabled', true);

        } else {

        }

        if(resultData.subType == "CPU_LINUX_SYSTEM" || resultData.subType == "CPU_LINUX_USER") {

            dijit.byId("monitorAlertType").setValue("cpu");
            dijit.byId("monitorAlertType").setAttribute('disabled', true);
            dojo.byId("monitorAlertTypeCpuDiv").style.display = "block";
            dijit.byId("monitorAlertTypeCpu").setValue(resultData.subType);
            dijit.byId("monitorAlertTypeCpu").setAttribute('disabled', true);

        } else if(resultData.subType == "DISK_IO_READ_LINUX" || resultData.subType == "DISK_IO_READ_WINDOWS") {

            dijit.byId("monitorAlertType").setValue("disk");
            dijit.byId("monitorAlertType").setAttribute('disabled', true);
            dojo.byId("monitorAlertTypeDiskDiv").style.display = "block";
            dijit.byId("monitorAlertTypeDisk").setValue("ioRead");
            dijit.byId("monitorAlertTypeDisk").setAttribute('disabled', true);

        } else if(resultData.subType == "DISK_IO_WRITE_LINUX" || resultData.subType == "DISK_IO_WRITE_WINDOWS") {

            dijit.byId("monitorAlertType").setValue("disk");
            dijit.byId("monitorAlertType").setAttribute('disabled', true);
            dojo.byId("monitorAlertTypeDiskDiv").style.display = "block";
            dijit.byId("monitorAlertTypeDisk").setValue("ioWrite");
            dijit.byId("monitorAlertTypeDisk").setAttribute('disabled', true);
        } else if(resultData.subType == "FILE_LINUX") {
            
            dijit.byId("monitorAlertType").setValue("fileSystem");
            dijit.byId("monitorAlertType").setAttribute('disabled', true);
        }
   },
   'update': function() {
       
       var instanceId = dojo.byId("instanceId").value;
       var alarmId = dojo.byId("currentAlarmId").value;
              
       var status = true;
        var pageNode = dojo.byId("instanceAlarmContentPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        
        var thresholdMinValue = dijit.byId("alarmThresholdMinValue").value;
        var thresholdMaxValue = dijit.byId("alarmThresholdMaxValue").value;
        
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
            
            if(thresholdMinValue > thresholdMaxValue) {
               console.log("min greater")
               dijit.byId("alarmThresholdMinValue").focus();
               dijit.byId("alarmThresholdMaxValue").focus();
            } else {
                
                dojo.byId("alarmButtonDiv").style.display = "none";
                dojo.byId("addAlarmButtonLoader").style.display = "block";
                
                var name = dijit.byId("instanceAlertName").value;
                var osType = dojo.byId("deviceOsType").value;
                var alarmListName = dijit.byId("notificationTopicListWidget").value;
                var topicListName;
                var topicName;
                if(alarmListName !== "") {
                    topicListName = alarmListName;
                }
                if(name !== "") {
                    topicName = name;
                }

                var reciepient = dijit.byId("alarmReciepient").value;
                               
                var alarmUpdateRest = new JsonRest({
                    target : core.getContextPath() + "/api/alarm/update" 
                });

                alarmUpdateRest.add({
                    alarmId: alarmId,
                    name: topicName,
                    topicListName: topicListName, 
                    osType: osType,
                    reciepient: reciepient,
                    thresholdMinValue: thresholdMinValue,
                    thresholdMaxValue: thresholdMaxValue,
                    instanceId: instanceId

                }).then(function(data) {
                    dojo.forEach(data, function (resultData) {
                        
                        dojo.byId("alarmButtonDiv").style.display = "block";
                        dojo.byId("addAlarmButtonLoader").style.display = "none";
                
                        if(resultData.result === "OK") {
                            registry.byId("userToaster").setContent(translator.common.instance.monitorAlarmUpdateSuccess, "message");
                            registry.byId("userToaster").show(); 
                            dijit.byId("instanceAlarmContentForm").reset();
                            InstanceAlarm.populateAlertValues();
                            //remove disable after update
                            dijit.byId("monitorAlertType").setAttribute('disabled', false);
                            dijit.byId("monitorAlarmPort").setAttribute('disabled', false);
                            dijit.byId("monitorAlertTypeCpu").setAttribute('disabled', false);
                            dijit.byId("monitorAlertTypeDisk").setAttribute('disabled', false);
                            
                            dojo.byId("monitorAlertAddButtonDiv").style.display = "block";
                            dojo.byId("monitorAlertUpdateButtonDiv").style.display = "none";
                            
                        } else {
                            registry.byId("userToaster").setContent(translator.common.instance.monitorAlarmNotUpdated, "error");
                            registry.byId("userToaster").show(); 
                        }
                    });
                });
                
            }
            
            
        }
   },
   'disableAlertDialog': function(id) {
      dojo.byId("currentAlarmId").value = id;
      console.log("currentAlarmId"+id);
      dijit.byId("disableAlarmDialog").show();
   },
   'disable': function() {
       
       var id = dojo.byId("currentAlarmId").value;
       var alarmRest =  new JsonRest({
          target : core.getContextPath()  + "/api/alarm/disable"
       });
       dijit.byId("disableAlarmDialog").hide();
       alarmRest.add({alarmId: id}).then(function (data) {
           dojo.forEach(data, function(resultData) {
               if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.alarmDisableSuccess, "message");
                    registry.byId("userToaster").show(); 
                    InstanceAlarm.populateAlertValues();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.alarmNotDisabled, "error");
                    registry.byId("userToaster").show();
                }
           });
           
       });
   },
   'enable': function(id) {

       var alarmRest =  new JsonRest({
          target : core.getContextPath()  + "/api/alarm/enable"
       });

       alarmRest.add({alarmId: id}).then(function (data) {
           dojo.forEach(data, function(resultData) {
               if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.alarmEnableSuccess, "message");
                    registry.byId("userToaster").show(); 
                    InstanceAlarm.populateAlertValues();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.alarmNotEnabled, "error");
                    registry.byId("userToaster").show();
                }
           });
           
       });
   },
   'closeDisableDialog': function() {
      dijit.byId("disableAlarmDialog").show(); 
   },
   'deleteDialog': function(id) {
      dojo.byId("currentAlarmId").value = id;
      console.log("currentAlarmId"+id);
      dijit.byId("deleteAlarmDialog").show();
   },
   delete: function() {
       
       var id = dojo.byId("currentAlarmId").value;
       var alarmRest =  new JsonRest({
          target : core.getContextPath()  + "/api/alarm/delete"
       });
       dijit.byId("deleteAlarmDialog").hide();
       alarmRest.add({thresholdId: id}).then(function (data) {
           dojo.forEach(data, function(resultData) {
               if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.instance.alarmDeleteSuccess, "message");
                    registry.byId("userToaster").show(); 
                    InstanceAlarm.populateAlertValues();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.alarmNotDeleted, "error");
                    registry.byId("userToaster").show();
                }
           });
           
       });
   },
   'closeDeleteDialog': function() {
       dijit.byId("deleteAlarmDialog").hide();
   }      
};


window.GraphInfo = GraphInfo;
window.InstanceFloatingIp = InstanceFloatingIp;
window.InstanceFloatingIpInfo = InstanceFloatingIpInfo;
window.InstanceMonitoring = InstanceMonitoring;
window.InstanceAlarm = InstanceAlarm;
window.IPServices = IPServices;
