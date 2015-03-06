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
    "FogPanel/InstanceStatus",
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
    "FogPanel/ZonePrice",
    "FogPanel/MiscZonePrice",
    "dijit/form/Button",
    "dojox/validate/regexp",
    "dijit/form/ValidationTextBox",
    "dijit/form/RadioButton",
    "List/ListItem",
    "dijit/Dialog"
    ],
    function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select, ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {    
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
        controller ({
            name: "misc",
            module: "admin",
            filePath: "/js/app/admin/misc.js",
            layout: {
                name: "misc",
                containerId: "content"
            },	
            scaffold: false
        },
        {   
            "index": action(function() {                    
                if (dijit.byId("miscTabWidget")) {
                    dijit.byId("miscTabWidget").destroyRecursive();
                }
                if (dijit.byId("adminMiscSnapshotForm")) {
                    dijit.byId("adminMiscSnapshotForm").destroyRecursive();
                }    
                if (dijit.byId("adminMiscImgSnapshotForm")) {
                    dijit.byId("adminMiscImgSnapshotForm").destroyRecursive();
                }   
                if (dijit.byId("imageSnapshotEditConformationDialog")) {
                    dijit.byId("imageSnapshotEditConformationDialog").destroyRecursive();
                }    
                if (dijit.byId("networkSnapshotEditConformationDialog")) {
                    dijit.byId("networkSnapshotEditConformationDialog").destroyRecursive();
                }    
                if (dijit.byId("adminMiscNetworkSnapshotForm")) {
                    dijit.byId("adminMiscNetworkSnapshotForm").destroyRecursive();
                }
                if (dijit.byId("networkWriteSnapshotEditConformationDialog")) {
                    dijit.byId("networkWriteSnapshotEditConformationDialog").destroyRecursive();
                }  
                if (dijit.byId("networkReadSnapshotEditConformationDialog")) {
                    dijit.byId("networkReadSnapshotEditConformationDialog").destroyRecursive();
                }  
                if (dijit.byId("adminMiscNetworkReadSnapshotForm")) {
                    dijit.byId("adminMiscNetworkReadSnapshotForm").destroyRecursive();
                }
                if (dijit.byId("floatingIPSnapshotEditConformationDialog")) {
                    dijit.byId("floatingIPSnapshotEditConformationDialog").destroyRecursive();
                }
                if (dijit.byId("adminMiscFloatingIPSnapshotForm")) {
                    dijit.byId("adminMiscFloatingIPSnapshotForm").destroyRecursive();
                }    
                if (dijit.byId("volumeSnapshotEditConformationDialog")) {
                    dijit.byId("volumeSnapshotEditConformationDialog").destroyRecursive();
                }                    
                if (dijit.byId("objectStorageConformationDialog")) {
                    dijit.byId("objectStorageConformationDialog").destroyRecursive();
                }  
                if (dijit.byId("networkMonitoringAlarmConfirmationDialog")) {
                    dijit.byId("networkMonitoringAlarmConfirmationDialog").destroyRecursive();
                }  
                if (dijit.byId("adminMonitoringAlamForm")) {
                    dijit.byId("adminMonitoringAlamForm").destroyRecursive();
                }  
                
                
                core.ui.loadTemplate("miscPage", core.ui.getContentId());   
                SnapshotInfo.init();
             })                         
         });
    });
var ObjectStorageInfo = {
    updateObjectStorageShow : function () {
        var stat = true;        
        var miscelZoneCostWidgets = dijit.byId("objectStoreCostWidget");                             
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            stat = false;
        }       
        if(stat === true) {
            dijit.byId("objectStorageConformationDialog").show();
        } 
    },
    updateObjectStorage : function () {        
        var status = true;        
        var miscelZoneCostWidgets = dijit.byId("objectStoreCostWidget");                               
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            status = false;
        }
        
        if(status === true) {
            dijit.byId("objectStorageConformationDialog").hide(); 
            var cost = 0;
            var objectStorageCostWidget = dijit.byId("objectStoreCostWidget");             
            cost = objectStorageCostWidget.getZoneHrCost()                                       
            
            dijit.byId('objectStorageButton').set('style', {'display': 'none'});
            dojo.byId('miscObjectStoreLoader').style.display = "block";
            
            var misclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/miscellaneousOffer/"
            });
            
            misclRestStore.put({            
                id: 7,
                cost : cost                              
            }).then(function(result)  { 
                dijit.byId('objectStorageButton').set('style', {'display': 'block'});
                dojo.byId('miscObjectStoreLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("objectStorageConformationDialog").hide();
                    ObjectStorageInfo.populateValues();                       
                } else {
                    dijit.byId("objectStorageConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }
    },
    populateValues : function () {
        if(dijit.byId("objectStoreSnapshotGrid")) {
            dijit.byId("objectStoreSnapshotGrid").destroyRecursive();
        }                       
        dojo.byId("miscObjectStoreGridDiv").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'name': translator.common.name +  "", 'field': 'name',  'width' : '400px'},                           
                {'name': translator.common.CostPerGBHr + "", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                    }
                }                    
            ]
        ];       
        
        var misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });
    
        misclRestStore.query({id: 7}).then(function(data) {
            if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
                dojo.byId("noObjectStoreMsgBox").style.display = "block";   
                dojo.byId("miscObjectStoreGridDiv").innerHTML = "";
            } else {    
                dojo.byId("noObjectStoreMsgBox").style.display = "none"; 
                dojo.forEach(data, function(networkData) {
                    summaryDataList.newItem({
                        name: translator.common.objectStoreCost,                                  
                        cost: localeCurrency.format(networkData.cost, {places: 5, locale: dojo.locale})
                    });
                });
                dojo.byId("miscObjectStoreGridDiv").innerHTML = "";
                var gridWidget = new EnhancedGrid({
                    id: "objectStoreSnapshotGrid",
                    "class" : "span12",
                    store: summaryDataList,
                    structure: summaryLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins                     
                });
                gridWidget.placeAt("miscObjectStoreGridDiv");
                gridWidget.startup();
            
                if(dijit.byId("objectStoreCostWidget")) {
                    dijit.byId("objectStoreCostWidget").destroyRecursive();
                }
                var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                    id:"objectStoreCostWidget",
                    zoneName: translator.common.objectStoreCost,
                    invalidMsg : translator.common.invalidCost,
                    zoneIdNode: 7,          
                    zoneCost : (data[0].cost) * 720
                });                         
                currentSnapZonePrice.setCost(); 
                currentSnapZonePrice.placeAt("objectStorageMiscSnapshotCostInfo");                  
                dojo.query("#objectStoreZoneLoader").toggleClass("hide_text", true);
                dojo.query("#miscObjectStoreGridDiv").removeClass("hide_text", true);
                dojo.query("#miscObjectStorePriceLabelContainer").removeClass("hide_text", true);
                dojo.query("#objectStorageButton").removeClass("hide_text", true); 
            }             
        });                       
        dojo.query("#objectStoreZoneLoader").removeClass("hide_text", true);
        dojo.query("#miscObjectStorePriceLabelContainer").toggleClass("hide_text", true);        
        dojo.query("#objectStorageButton").toggleClass("hide_text", true);  
    }
}    


var MonitoringAlarmInfo = {
    populateValues : function () {
        
        if(dijit.byId("monitoringAlarmGrid")) {
            dijit.byId("monitoringAlarmGrid").destroyRecursive();
        }                       
        
        dojo.byId("monitoringAlramGridDivInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        
        var summaryData = {
            items: []
        };
        
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'name': translator.common.name +  "", 'field': 'name',  'width' : '400px'},                           
                {'name': translator.common.cost + " / " + translator.common.monitoringAlarm + "", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>";
                        return html;
                    }
                }                    
            ]
        ];       
        var misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });

        misclRestStore.query({id: 8}).then(function(data) {
            if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
                dojo.byId("noMonitoringAlarmMessageBox").style.display = "block";   
                dojo.byId("monitoringAlramGridDivInfo").innerHTML = "";
            } else {            
                dojo.forEach(data, function(alrmData) {
                    summaryDataList.newItem({
                        name : translator.common.monitoringAlarmLabel,                                  
                        cost: localeCurrency.format(alrmData.cost, {places: 2, locale: dojo.locale})
                    });
                });
                dojo.byId("monitoringAlramGridDivInfo").innerHTML = "";
                var gridWidget = new EnhancedGrid({
                    id: "monitoringAlarmGrid",
                    "class" : "span12",
                    store: summaryDataList,
                    structure: summaryLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins                     
                });
                gridWidget.placeAt("monitoringAlramGridDivInfo");
                gridWidget.startup();


                if(dijit.byId("monitoringAlramCostWidget")) {
                    dijit.byId("monitoringAlramCostWidget").destroyRecursive();
                }
                var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                    id:"monitoringAlramCostWidget",
                    zoneName: translator.common.monitoringAlarmCost,
                    invalidMsg : translator.common.invalidCost,
                    zoneIdNode: 3,
                    zoneCost : data[0].cost
                });                
                currentSnapZonePrice.placeAt("monitoringAlarmCostInfo");      
                currentSnapZonePrice.setCost(); 
                currentSnapZonePrice.showOnlyMonthCost();
                dojo.query("#monitoringAlarmZoneLoader").toggleClass("hide_text", true);
                dojo.query("#monitoringAlarmCostInfo").removeClass("hide_text", true);
                dojo.query("#monitoringAlarmPriceLabelContainer").removeClass("hide_text", true);
                dojo.query("#monitoringAlarmOKButton").removeClass("hide_text", true);
            }             
        });                       
        dojo.query("#monitoringAlarmZoneLoader").removeClass("hide_text", true);    
        dojo.query("#monitoringAlarmPriceLabelContainer").toggleClass("hide_text", true);        
        dojo.query("#monitoringAlarmOKButton").toggleClass("hide_text", true); 
    }, 
    updateMonitoringAlramShow : function () {
        var stat = true;        
        var miscelZoneCostWidgets = dijit.byId("monitoringAlramCostWidget");                             
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            stat = false;
        }       
        if(stat === true) {
            dijit.byId("networkMonitoringAlarmConfirmationDialog").show();
        }
    },
    updateCost : function () {
        var status = true;        
        var miscelZoneCostWidgets = dijit.byId("monitoringAlramCostWidget");                               
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            status = false;
        }
        
        if(status === true) {
            dijit.byId("networkMonitoringAlarmConfirmationDialog").hide(); 
            var cost = 0;
            var alarmWidget = dijit.byId("monitoringAlramCostWidget");             
            cost = alarmWidget.getZoneMonthCost()               
                        
            dijit.byId('monitoringAlarmOKButton').set('style', {'display': 'none'});
            dojo.byId('monitoringAlarmLoader').style.display = "block";
            
            var misclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/miscellaneousOffer/"
            });
            
            misclRestStore.put({            
                id: 8,
                cost : cost                              
            }).then(function(result)  { 
                dijit.byId('monitoringAlarmOKButton').set('style', {'display': 'block'});
                dojo.byId('monitoringAlarmLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("networkMonitoringAlarmConfirmationDialog").hide();
                    MonitoringAlarmInfo.populateValues();                       
                } else {
                    dijit.byId("networkMonitoringAlarmConfirmationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }
    }
}

var FloatingIPSnapshotInfo = {
    updateFloatingIPShow : function () {
        var stat = true;        
        var miscelZoneCostWidgets = dijit.byId("floatingIPCostWidget");                             
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            stat = false;
        }       
        if(stat === true) {
            dijit.byId("floatingIPSnapshotEditConformationDialog").show();
        } 
    },
    updateFloatingIPSnapshot : function () {        
        var status = true;        
        var miscelZoneCostWidgets = dijit.byId("floatingIPCostWidget");                               
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            status = false;
        }
        
        if(status === true) {
            dijit.byId("floatingIPSnapshotEditConformationDialog").hide(); 
            var cost = 0;
            var networkCostWidget = dijit.byId("floatingIPCostWidget");             
            cost = networkCostWidget.getZoneHrCost()               
                        
            dijit.byId('floatingIPReadSnapButton').set('style', {'display': 'none'});
            dojo.byId('miscFloatingIPSnapLoader').style.display = "block";
            
            var misclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/miscellaneousOffer/"
            });
            
            misclRestStore.put({            
                id: 6,
                cost : cost                              
            }).then(function(result)  { 
                dijit.byId('floatingIPReadSnapButton').set('style', {'display': 'block'});
                dojo.byId('miscFloatingIPSnapLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("floatingIPSnapshotEditConformationDialog").hide();
                    FloatingIPSnapshotInfo.populateValues();                       
                } else {
                    dijit.byId("floatingIPSnapshotEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }
    },
    populateValues : function () {
        if(dijit.byId("floatingIPSnapshotGrid")) {
            dijit.byId("floatingIPSnapshotGrid").destroyRecursive();
        }                       
        dojo.byId("floatingIPSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'name': translator.common.name +  "", 'field': 'name',  'width' : '400px'},                           
                {'name': translator.common.cost + " / " + translator.common.floatingIPHr + "", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>";
                        return html;
                }
            }                    
        ]
    ];       
    var misclRestStore = new JsonRest({
        target: core.getContextPath()+"/api/miscellaneousOffer/"
    });
    
    misclRestStore.query({id: 6}).then(function(data) {
        if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
            dojo.byId("noFlaotingIPMiscSnapshotMessageBox").style.display = "block";   
            dojo.byId("floatingIPSnapshotInfo").innerHTML = "";
        } else {            
            dojo.forEach(data, function(networkData) {
                summaryDataList.newItem({
                    name : translator.common.floatingIP,                                  
                    cost: localeCurrency.format(networkData.cost, {places: 5, locale: dojo.locale})
                });
            });
            dojo.byId("floatingIPSnapshotInfo").innerHTML = "";
            var gridWidget = new EnhancedGrid({
                id: "floatingIPSnapshotGrid",
                "class" : "span12",
                store: summaryDataList,
                structure: summaryLayout,
                autoHeight: true,
                plugins: core.getGridConfig().plugins                     
            });
            gridWidget.placeAt("floatingIPSnapshotInfo");
            gridWidget.startup();
            
            
            if(dijit.byId("floatingIPCostWidget")) {
                dijit.byId("floatingIPCostWidget").destroyRecursive();
            }
            var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                id:"floatingIPCostWidget",
                zoneName: translator.common.floatingIPCost,
                invalidMsg : translator.common.invalidCost,
                zoneIdNode: 3,
                zoneCost : (data[0].cost) * 720
            });                
            currentSnapZonePrice.placeAt("floatingIPMiscSnapshotCostInfo");      
            currentSnapZonePrice.setCost(); 
            dojo.query("#floatingIPSnapshotZoneLoader").toggleClass("hide_text", true);
            dojo.query("#floatingIPSnapshotInfo").removeClass("hide_text", true);
            dojo.query("#floatingIPPriceLabelContainer").removeClass("hide_text", true);
            dojo.query("#floatingIPReadSnapButton").removeClass("hide_text", true);
        }             
    });                       
    dojo.query("#floatingIPSnapshotZoneLoader").removeClass("hide_text", true);
//    dojo.query("#floatingIPSnapshotInfo").toggleClass("hide_text", true);
    dojo.query("#floatingIPPriceLabelContainer").toggleClass("hide_text", true);        
    dojo.query("#floatingIPReadSnapButton").toggleClass("hide_text", true);                
    
}             
};

var NetworkReadSnapshotInfo = {
    updateNetworkWiteShow : function () {
        var stat = true;        
        var miscelZoneCostWidgets = dijit.byId("networkReadCostWidget");                             
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            stat = false;
        }       
        if(stat === true) {
            dijit.byId("networkReadSnapshotEditConformationDialog").show();
        } 
    },
    updateNwWriteSnapshot : function () {        
        var status = true;        
        var miscelZoneCostWidgets = dijit.byId("networkReadCostWidget");                               
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            status = false;
        }
        
        if(status === true) {
            dijit.byId("networkReadSnapshotEditConformationDialog").hide(); 
            var cost = 0;
            var networkCostWidget = dijit.byId("networkReadCostWidget");             
            cost = networkCostWidget.getZoneMonthCost()               
                        
            dijit.byId('miscNetworkReadSnapButton').set('style', {'display': 'none'});
            dojo.byId('miscNetworkReadSnapLoader').style.display = "block";
            
            var misclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/miscellaneousOffer/"
            });
            
            misclRestStore.put({            
                id: 4,
                cost : cost                              
            }).then(function(result)  { 
                dijit.byId('miscNetworkReadSnapButton').set('style', {'display': 'block'});
                dojo.byId('miscNetworkReadSnapLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("networkReadSnapshotEditConformationDialog").hide();
                    NetworkReadSnapshotInfo.populateValues();                       
                } else {
                    dijit.byId("networkReadSnapshotEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }
    },
    populateValues : function () {
        if(dijit.byId("networkReadSnapshotGrid")) {
            dijit.byId("networkReadSnapshotGrid").destroyRecursive();
        }                       
        dojo.byId("miscNetworkReadSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'name': translator.common.name +  "", 'field': 'name',  'width' : '400px'},                           
                {'name': translator.common.CostPerGB + "", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                }
            }                    
        ]
    ];       
    var misclRestStore = new JsonRest({
        target: core.getContextPath()+"/api/miscellaneousOffer/"
    });
    
    misclRestStore.query({id: 4}).then(function(data) {
        if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
            dojo.byId("noNetworkWriteMiscSnapshotMessageBox").style.display = "block";   
            dojo.byId("miscNetworkReadSnapshotInfo").innerHTML = "";
        } else {            
            dojo.forEach(data, function(networkData) {
                summaryDataList.newItem({
                    name: translator.common.bandwidthRead,                                  
                    cost: localeCurrency.format(networkData.cost, {places: 2, locale: dojo.locale})
                   });
            });
            dojo.byId("miscNetworkReadSnapshotInfo").innerHTML = "";
            var gridWidget = new EnhancedGrid({
                id: "networkReadSnapshotGrid",
                "class" : "span12",
                store: summaryDataList,
                structure: summaryLayout,
                autoHeight: true,
                plugins: core.getGridConfig().plugins                     
            });
            gridWidget.placeAt("miscNetworkReadSnapshotInfo");
            gridWidget.startup();
            
            if(dijit.byId("networkReadCostWidget")) {
                dijit.byId("networkReadCostWidget").destroyRecursive();
            }
            var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                id:"networkReadCostWidget",
                zoneName: translator.common.bandwidthReadCost,
                invalidMsg : translator.common.invalidCost,
                zoneIdNode: 3,          
                zoneCost : data[0].cost
            });         
            currentSnapZonePrice.showOnlyMonthCost();
            currentSnapZonePrice.setCost(); 
            currentSnapZonePrice.placeAt("networkReadMiscSnapshotCostInfo");                  
            dojo.query("#networkReadSnapshotZoneLoader").toggleClass("hide_text", true);
            dojo.query("#miscNetworkReadSnapshotInfo").removeClass("hide_text", true);
            dojo.query("#networkReadSnapshotPriceLabelContainer").removeClass("hide_text", true);
            dojo.query("#miscNetworkReadSnapButton").removeClass("hide_text", true); 
        }             
    });                       
    dojo.query("#networkReadSnapshotZoneLoader").removeClass("hide_text", true);
//    dojo.query("#miscNetworkReadSnapshotInfo").toggleClass("hide_text", true);
    dojo.query("#networkReadSnapshotPriceLabelContainer").toggleClass("hide_text", true);        
    dojo.query("#miscNetworkReadSnapButton").toggleClass("hide_text", true);                
                 
    }
} 
    
var NetworkWriteSnapshotInfo = {
    updateNetworkWiteShow : function () {
        var stat = true;        
        var miscelZoneCostWidgets = dijit.byId("networkwriteCostWidget");                             
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            stat = false;
        }       
        if(stat === true) {
            dijit.byId("networkWriteSnapshotEditConformationDialog").show();
        } 
    },
    updateNwWriteSnapshot : function () {        
        var status = true;        
        var miscelZoneCostWidgets = dijit.byId("networkwriteCostWidget");                               
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            status = false;
        }
        
        if(status === true) {
            dijit.byId("networkWriteSnapshotEditConformationDialog").hide(); 
            var cost = 0;
            var networkCostWidget = dijit.byId("networkwriteCostWidget");             
            cost = networkCostWidget.getZoneMonthCost()               
                        
            dijit.byId('miscNetworkWriteSnapButton').set('style', {'display': 'none'});
            dojo.byId('miscNetworkWriteSnapLoader').style.display = "block";
            
            var misclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/miscellaneousOffer/"
            });
            
            misclRestStore.put({            
                id: 5,
                cost : cost                              
            }).then(function(result)  { 
                dijit.byId('miscNetworkWriteSnapButton').set('style', {'display': 'block'});
                dojo.byId('miscNetworkWriteSnapLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("networkWriteSnapshotEditConformationDialog").hide();
                    NetworkWriteSnapshotInfo.populateValues();                       
                } else {
                    dijit.byId("networkWriteSnapshotEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }
    },
    populateValues : function () {
        if(dijit.byId("networkWriteSnapshotGrid")) {
            dijit.byId("networkWriteSnapshotGrid").destroyRecursive();
        }                       
        dojo.byId("miscNetworkSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'name': translator.common.name +  "", 'field': 'name',  'width' : '400px'},                           
                {'name':  translator.common.CostPerGB + "", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                }
            }                    
        ]
    ];       
    var misclRestStore = new JsonRest({
        target: core.getContextPath()+"/api/miscellaneousOffer/"
    });
    
    misclRestStore.query({id: 5}).then(function(data) {
        if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
            dojo.byId("noNetworkWriteMiscSnapshotMessageBox").style.display = "block";   
            dojo.byId("miscNetworkWriteSnapshotInfo").innerHTML = "";
        } else {            
            dojo.forEach(data, function(networkData) {
                summaryDataList.newItem({
                    name : translator.common.bandwidthWrite,                                  
                    cost: localeCurrency.format(networkData.cost, {places: 2, locale: dojo.locale})
                   });
            });
            dojo.byId("miscNetworkWriteSnapshotInfo").innerHTML = "";
            var gridWidget = new EnhancedGrid({
                id: "networkWriteSnapshotGrid",
                "class" : "span12",
                store: summaryDataList,
                structure: summaryLayout,
                autoHeight: true,
                plugins: core.getGridConfig().plugins                     
            });
            gridWidget.placeAt("miscNetworkWriteSnapshotInfo");
            gridWidget.startup();
            
            
            if(dijit.byId("networkwriteCostWidget")) {
                dijit.byId("networkwriteCostWidget").destroyRecursive();
            }
            var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                id:"networkwriteCostWidget",
                zoneName: translator.common.bandwidthWriteCost,
                invalidMsg : translator.common.invalidCost,
                zoneIdNode: 3,
                zoneCost : data[0].cost
            });           
            currentSnapZonePrice.showOnlyMonthCost();
            currentSnapZonePrice.setCost(); 
            currentSnapZonePrice.placeAt("networkWriteMiscSnapshotCostInfo");                  
            dojo.query("#networkWriteSnapshotZoneLoader").toggleClass("hide_text", true);
            dojo.query("#miscNetworkWriteSnapshotInfo").removeClass("hide_text", true);
            dojo.query("#networkWriteSnapshotPriceLabelContainer").removeClass("hide_text", true);
            dojo.query("#miscNetworkWriteSnapButton").removeClass("hide_text", true);  
        }             
    });                       
    dojo.query("#networkWriteSnapshotZoneLoader").removeClass("hide_text", true);
//    dojo.query("#miscNetworkWriteSnapshotInfo").toggleClass("hide_text", true);
    dojo.query("#networkWriteSnapshotPriceLabelContainer").toggleClass("hide_text", true);        
    dojo.query("#miscNetworkWriteSnapButton").toggleClass("hide_text", true);                
          
      
    }
}    

var NetworkSnapshotInfo = {
    updateNetworkShow : function () {
        var stat = true;        
        var miscelZoneCostWidgets = dijit.byId("networkCostWidget");                             
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            stat = false;
        }       
        if(stat === true) {
            dijit.byId("networkSnapshotEditConformationDialog").show();
        } 
    },
    updateImageSnapshot : function () {        
        var status = true;        
        var miscelZoneCostWidgets = dijit.byId("networkCostWidget");                               
        var currentState = miscelZoneCostWidgets.validateField();
        if(currentState === false) {
            status = false;
        }
        
        if(status === true) {
            dijit.byId("networkSnapshotEditConformationDialog").hide(); 
            var cost = 0;
            var networkCostWidget = dijit.byId("networkCostWidget");             
            cost = networkCostWidget.getZoneHrCost()               
                        
            dijit.byId('miscNetworkSnapButton').set('style', {'display': 'none'});
            dojo.byId('miscNetworkSnapLoader').style.display = "block";
            
            var misclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/miscellaneousOffer/"
            });
            
            misclRestStore.put({            
                id: 3,
                cost : cost                              
            }).then(function(result)  { 
                dijit.byId('miscNetworkSnapButton').set('style', {'display': 'block'});
                dojo.byId('miscNetworkSnapLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("networkSnapshotEditConformationDialog").hide();
                    NetworkSnapshotInfo.populateValues();                       
                } else {
                    dijit.byId("networkSnapshotEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }
    },
    populateValues : function () {
        if(dijit.byId("networkSnapshotGrid")) {
            dijit.byId("networkSnapshotGrid").destroyRecursive();
        }                
        dojo.byId("miscNetworkSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'name': translator.common.name +  "", 'field': 'name',  'width' : '400px'},                           
                {'name': translator.common.costPerHr + "", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                }
            }                    
        ]
    ];       
    var misclRestStore = new JsonRest({
        target: core.getContextPath()+"/api/miscellaneousOffer/"
    });
    
    misclRestStore.query({id: 3}).then(function(data) {
        if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
            dojo.byId("noNetworkMiscSnapshotMessageBox").style.display = "block";   
            dojo.byId("miscImgSnapshotInfo").innerHTML = "";
        } else {                 
            dojo.forEach(data, function(networkData) {
                summaryDataList.newItem({
                    name: translator.common.network,                                  
                    cost: localeCurrency.format(networkData.cost, {places: 5, locale: dojo.locale})
                });
            });
            dojo.byId("miscNetworkSnapshotInfo").innerHTML = "";
            var gridWidget = new EnhancedGrid({
                id: "networkSnapshotGrid",
                "class" : "span12",
                store: summaryDataList,
                structure: summaryLayout,
                autoHeight: true,
                plugins: core.getGridConfig().plugins                     
            });
            gridWidget.placeAt("miscNetworkSnapshotInfo");
            gridWidget.startup();
            
            if(dijit.byId("networkCostWidget")) {
                dijit.byId("networkCostWidget").destroyRecursive();
            }
            var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                id:"networkCostWidget",
                zoneName: translator.common.networkCost,
                invalidMsg : translator.common.invalidCost,
                zoneIdNode: 3,
                zoneCost : (data[0].cost)*720
            });                
            currentSnapZonePrice.placeAt("networkMiscSnapshotCostInfo");   
            currentSnapZonePrice.setCost(); 
            dojo.query("#networkSnapshotZoneLoader").toggleClass("hide_text", true);
            dojo.query("#networkMiscSnapshotCostInfo").removeClass("hide_text", true);
            dojo.query("#networkSnapshotPriceLabelContainer").removeClass("hide_text", true);
            dojo.query("#networkSnapUpdateActionButton").removeClass("hide_text", true);
        }             
    });                       
    dojo.query("#networkSnapshotZoneLoader").removeClass("hide_text", true);
    dojo.query("#networkMiscSnapshotCostInfo").toggleClass("hide_text", true);
    dojo.query("#networkSnapshotPriceLabelContainer").toggleClass("hide_text", true);        
    dojo.query("#networkSnapUpdateActionButton").toggleClass("hide_text", true);                                  
    }
};

var VolumeSnapshotInfo = {
    populateValues : function () {
        if(dijit.byId("snapshotGrid")) {
            dijit.byId("snapshotGrid").destroyRecursive();
        }                
        dojo.byId("miscSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'field': 'id',  'hidden' : 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},                
                {'name': translator.common.cost + " " + translator.common.gbPerHr + "", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                    }
                }                    
            ]
        ];       
        var misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });
    
        var zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        misclRestStore.query({id: 2}).then(function(data) {
            if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
                dojo.byId("noMiscSnapshotMessageBox").style.display = "block";   
                dojo.byId("miscSnapshotInfo").innerHTML = "";
            } else {            
                if(data[0].miscellaneousOfferZoneCosts.length === 0 || data[0].miscellaneousOfferZoneCosts === undefined || data[0].miscellaneousOfferZoneCosts === 'undefined' || data[0].miscellaneousOfferZoneCosts === '' || data[0].miscellaneousOfferZoneCosts === " ") {
                    dojo.byId("noMiscSnapshotMessageBox").style.display = "block";   
                    dojo.byId("miscSnapshotInfo").innerHTML = "";                
                } else {                
                    dojo.byId("noMiscSnapshotMessageBox").style.display = "none"; 
                    dojo.forEach(data[0].miscellaneousOfferZoneCosts, function(miscData) {
                        summaryDataList.newItem({
                            id:miscData.id, 
                            zone: miscData.zone.name,                    
                            cost: localeCurrency.format(miscData.cost, {places: 5, locale: dojo.locale})
                        });
                    });
                    dojo.byId("miscSnapshotInfo").innerHTML = "";
                    var gridWidget = new EnhancedGrid({
                        id: "snapshotGrid",
                        "class" : "span12",
                        store: summaryDataList,
                        structure: summaryLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins                     
                    });
                    gridWidget.placeAt("miscSnapshotInfo");
                    gridWidget.startup();            
                }
                zoneRestStore.query().then(function(zoneData) {    
                    dojo.forEach(zoneData, function (el, index) {
                        if(dijit.byId("snap_zone_widget_"+el.name+"_"+index)) {
                            dijit.byId("snap_zone_widget_"+el.name+"_"+index).destroyRecursive();
                        }                    
                        var zoneCost = 0.0;
                        dojo.forEach(data[0].miscellaneousOfferZoneCosts, function (imgZoneCost) {                            
                            if(el.id === imgZoneCost.zone.id) {                                
                                zoneCost = (imgZoneCost.cost)*720;
                            } 
                        });

                        var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                            id:"snap_zone_widget_"+el.name+"_"+index,
                            zoneName: el.name,
                            invalidMsg : translator.common.invalidCost,
                            zoneIdNode: el.id,
                            zoneCost : zoneCost
                        });                
                        currentSnapZonePrice.placeAt("miscSnapshotCostInfo");
                        currentSnapZonePrice.setCost(); 
                        dojo.query("#snapshotZoneLoader").toggleClass("hide_text", true);
                        dojo.query("#miscSnapshotCostInfo").removeClass("hide_text", true);
                        dojo.query("#snapshotPriceLabelContainer").removeClass("hide_text", true);
                        dojo.query("#snapUpdateActionButton").removeClass("hide_text", true);        
                    });
                });
            }             
        });                       
        dojo.query("#snapshotZoneLoader").removeClass("hide_text", true);
        dojo.query("#miscSnapshotCostInfo").toggleClass("hide_text", true);
        dojo.query("#snapshotPriceLabelContainer").toggleClass("hide_text", true);        
        dojo.query("#snapUpdateActionButton").toggleClass("hide_text", true);                        
    }, 
    updateShow : function () {
        var stat = true;
        var miscelZoneCostList = dojo.byId("miscSnapshotCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                   
            var currentState = el.validateField();
            if(currentState === false) {
                stat = false;
            }
        }); 
        if(stat === true) {
            dijit.byId("volumeSnapshotEditConformationDialog").show();
        } 
    },
    updateSnapshot : function () {
        var zoneCosts = [];    
        var status = true;
        var miscelZoneCostList = dojo.byId("miscSnapshotCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);
        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
               var currentState = el.validateField();
               if(currentState === false) {
                   status = false;
               }
            }); 
        if(status === true) {
            dijit.byId("volumeSnapshotEditConformationDialog").hide();                    
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneHrCost()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }            
            dijit.byId('miscSnapButton').set('style', {'display': 'none'});
            dojo.byId('miscSnapLoader').style.display = "block";
            
            var misclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/miscellaneousOffer/"
            });
            
            misclRestStore.put({            
                id: 2,
                cost : 0,
                zoneCosts: zoneCosts                
            }).then(function(result)  { 
                dijit.byId('miscSnapButton').set('style', {'display': 'block'});
                dojo.byId('miscSnapLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("volumeSnapshotEditConformationDialog").hide();
                    VolumeSnapshotInfo.populateValues();                       
                } else {
                    dijit.byId("volumeSnapshotEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }
    }
}

var SnapshotInfo = {
    init : function () {        
        var zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone"
        });          
        
        zoneRestStore.query().then(function(data) {
            if(data.length === 0 || data === undefined || data === "undefined") {                                      
                registry.byId("appToaster").setContent(translator.common.zoneConfigMissingMsg, 'error');
                registry.byId("appToaster").show();
                core.router.go("#/admin/settings/importData");
            } else {                
                var configRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/config/currency"
                });                
                
                configRestStore.query().then(function (data) {
                    dojo.byId("miscCurrencyValue").innerHTML = data[0].currency;
                    dojo.byId("networkCurrencyValue").innerHTML = data[0].currency;          
                    dojo.byId("networkWriteCurrencyValue").innerHTML = data[0].currency;       
                    dojo.byId("networkReadCurrencyValue").innerHTML = data[0].currency; 
                    dojo.byId("monitoringInfoCurrencyValue").innerHTML = data[0].currency;                         
                    dojo.byId("floatingIPCurrencyValue").innerHTML = data[0].currency;     
                    dojo.byId("volumeSnapCurrencyValue").innerHTML = data[0].currency;                                         
                });
            }
        })
    },
    populateValues : function () {
        if(dijit.byId("imgSnapshotGrid")) {
            dijit.byId("imgSnapshotGrid").destroyRecursive();
        }                
        dojo.byId("miscImgSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'field': 'id',  'hidden' : 'true'},
                {'name': translator.common.region, 'field': 'region', 'width' : '450px', datatype:"string",  autoComplete: true},                
                {'name': translator.common.cost + " " + translator.common.gbPerHr + "", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                }
            }                    
        ]
    ];   
    
    var misclRestStore = new JsonRest({
        target: core.getContextPath()+"/api/miscellaneousOffer/"
    });
    
    var regionRestStore = new JsonRest({
        target: core.getContextPath()+"/api/region/"
    });
    misclRestStore.query({id: 1}).then(function(data) {
        if(data.length === 0 || data[0] === undefined || data[0] === 'undefined' || data[0] === '' || data[0] === " ") {
            dojo.byId("noImgMiscSnapshotMessageBox").style.display = "block";   
            dojo.byId("miscImgSnapshotInfo").innerHTML = "";
        } else {
            
            if(data[0].miscellaneousOfferRegionCosts.length === 0 || data[0].miscellaneousOfferZoneCosts === undefined || data[0].miscellaneousOfferZoneCosts === 'undefined' || data[0].miscellaneousOfferZoneCosts === '' || data[0].miscellaneousOfferZoneCosts === " ") {
                dojo.byId("noImgMiscSnapshotMessageBox").style.display = "block";   
                dojo.byId("miscImgSnapshotInfo").innerHTML = "";
            } else {
                dojo.byId("noImgMiscSnapshotMessageBox").style.display = "none"; 
                dojo.forEach(data[0].miscellaneousOfferRegionCosts, function(miscData) {
                    summaryDataList.newItem({
                        id:miscData.id, 
                        region: miscData.region.name,                    
                        cost: localeCurrency.format(miscData.cost, {places: 5, locale: dojo.locale})
                    });
                });
                dojo.byId("miscImgSnapshotInfo").innerHTML = "";
                var gridWidget = new EnhancedGrid({
                    id: "imgSnapshotGrid",
                    "class" : "span12",
                    store: summaryDataList,
                    structure: summaryLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins                     
                });
                gridWidget.placeAt("miscImgSnapshotInfo");
                gridWidget.startup();            
            }
            regionRestStore.query().then(function(zoneData) {    
                dojo.forEach(zoneData, function (el, index) {
                    if(dijit.byId("snap_zone_widget_"+el.name+"_"+index)) {
                        dijit.byId("snap_zone_widget_"+el.name+"_"+index).destroyRecursive();
                    }                    
                    var zoneCost = 0.0;
                    dojo.forEach(data[0].miscellaneousOfferRegionCosts, function (imgZoneCost) {                            
                        if(el.id === imgZoneCost.region.id) {                                
                            zoneCost = (imgZoneCost.cost)*720;
                        } 
                    });
                    
                    var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                        id:"snap_zone_widget_"+el.name+"_"+index,
                        zoneName: el.name,
                        invalidMsg : translator.common.invalidCost,
                        zoneIdNode: el.id,
                        zoneCost : zoneCost
                    });                
                    currentSnapZonePrice.placeAt("imgMiscSnapshotCostInfo");
                    currentSnapZonePrice.setCost(); 
                    dojo.query("#imgSnapshotZoneLoader").toggleClass("hide_text", true);
                    dojo.query("#imgMiscSnapshotCostInfo").removeClass("hide_text", true);
                    dojo.query("#imgSnapshotPriceLabelContainer").removeClass("hide_text", true);
                    dojo.query("#imgSnapUpdateActionButton").removeClass("hide_text", true);        
                });
            });
        }             
    });                       
    dojo.query("#imgSnapshotZoneLoader").removeClass("hide_text", true);
    dojo.query("#imgMiscSnapshotCostInfo").toggleClass("hide_text", true);
    dojo.query("#imgSnapshotPriceLabelContainer").toggleClass("hide_text", true);        
    dojo.query("#imgSnapUpdateActionButton").toggleClass("hide_text", true);            
    
     
    }, 
    updateImageShow : function () {
        var stat = true;
        var miscelZoneCostList = dojo.byId("imgMiscSnapshotCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                   
            var currentState = el.validateField();
            if(currentState === false) {
                stat = false;
            }
        }); 
        if(stat === true) {
            dijit.byId("imageSnapshotEditConformationDialog").show();
        } 
    },
    closeImageSnapshot : function () {
        dijit.byId("imageSnapshotEditConformationDialog").hide();
        dijit.byId("networkSnapshotEditConformationDialog").hide();
        dijit.byId("networkWriteSnapshotEditConformationDialog").hide();    
        dijit.byId("networkReadSnapshotEditConformationDialog").hide(); 
        dijit.byId("floatingIPSnapshotEditConformationDialog").hide();  
        dijit.byId("volumeSnapshotEditConformationDialog").hide();      
        dijit.byId("objectStorageConformationDialog").hide(); 
        dijit.byId("networkMonitoringAlarmConfirmationDialog").hide();         
    },
    updateImageSnapshot : function () {
        var regionCosts = [];    
        var status = true;
        var miscelZoneCostList = dojo.byId("imgMiscSnapshotCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);
        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
               var currentState = el.validateField();
               if(currentState === false) {
                   status = false;
               }
            }); 
        if(status === true) {
            dijit.byId("imageSnapshotEditConformationDialog").hide();                    
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                regionCosts.push({
                    regionId: el.getZoneId(), 
                    cost: el.getZoneHrCost()
                });
            });         
            for(var index=0; index < regionCosts.length-miscelZoneCostWidgets.length; index++) {
                regionCosts.splice(index, miscelZoneCostWidgets.length);
            }            
            dijit.byId('miscImgSnapButton').set('style', {'display': 'none'});
            dojo.byId('miscImgSnapLoader').style.display = "block";
            
            var misclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/miscellaneousOffer/"
            });
            
            misclRestStore.put({            
                id: 1,
                cost : 0,
                regionCosts: regionCosts                
            }).then(function(result)  { 
                dijit.byId('miscImgSnapButton').set('style', {'display': 'block'});
                dojo.byId('miscImgSnapLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("imageSnapshotEditConformationDialog").hide();
                    SnapshotInfo.populateValues();                       
                } else {
                    dijit.byId("imageSnapshotEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }
    }
};

window.SnapshotInfo = SnapshotInfo;
window.NetworkSnapshotInfo = NetworkSnapshotInfo;
window.NetworkWriteSnapshotInfo = NetworkWriteSnapshotInfo;
window.NetworkReadSnapshotInfo = NetworkReadSnapshotInfo;
window.FloatingIPSnapshotInfo = FloatingIPSnapshotInfo;
window.VolumeSnapshotInfo = VolumeSnapshotInfo;
window.ObjectStorageInfo=ObjectStorageInfo;
window.MonitoringAlarmInfo = MonitoringAlarmInfo;