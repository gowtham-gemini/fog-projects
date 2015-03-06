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
    "dojox/calendar/Calendar",
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/PlotKit/blue",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojo/_base/connect",
    'dojox/timing',
    "FogPanel/Notification",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dojox/layout/ScrollPane",
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
    "dijit/form/MultiSelect",
    "dijit/layout/TabContainer"
],
function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
ItemFileWriteStore, DataGrid, ContentPane, Source, MultiSelect, dom, win, Calendar, Memory, Observable,
Chart, Pie, Tom, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, connect, timing, Notification) {
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
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;
    window.Calendar = Calendar;
    window.Memory = Memory;
    window.Observable = Observable;
    window.Chart = Chart;
    window.Pie = Pie;
    window.Tom = Tom;
    window.Tooltip = Tooltip;
    window.MoveSlice = MoveSlice;
    window.theme = theme;
    window.ColumnsPlot = ColumnsPlot;
    window.Highlight = Highlight;
    window.Lines = Lines;
    window.timing = timing;
    window.connect = connect;       
    controller({
        name:"limitSummary",
        module: "user",
        filePath: "/js/app/user/limitSummary.js",
        layout: {
            name: "limitSummary",
            containerId: "content"
        },
        scaffold: false
    },
    {        
        "index": action(function() {
                          
            if(dijit.byId("instanceLimitLoader")) {
                dijit.byId("instanceLimitLoader").destroyRecursive();
            }
            if(dijit.byId("volumeLimitLoader")) {
                dijit.byId("volumeLimitLoader").destroyRecursive();
            }
            if(dijit.byId("snapshotLimitLoader")) {
                dijit.byId("snapshotLimitLoader").destroyRecursive();
            }
            if(dijit.byId("pulicIpLimitLoader")) {
                dijit.byId("pulicIpLimitLoader").destroyRecursive();
            }
            if(dijit.byId("vpcLimitLoader")) {
                dijit.byId("vpcLimitLoader").destroyRecursive();
            }
            if(dijit.byId("networkLimitLoader")) {
                dijit.byId("networkLimitLoader").destroyRecursive();
            }

            if(dijit.byId("primaryStorageLimitLoader")) {
                dijit.byId("primaryStorageLimitLoader").destroyRecursive();
            }
            if(dijit.byId("secondaryStorageLimitLoader")) {
                dijit.byId("secondaryStorageLimitLoader").destroyRecursive();
            }
            if(dijit.byId("vcpuLimitLoader")) {
                dijit.byId("vcpuLimitLoader").destroyRecursive();
            }
            if(dijit.byId("memoryLimitLoader")) {
                dijit.byId("memoryLimitLoader").destroyRecursive();
            }
            
            core.ui.loadTemplate("limitSummaryPage", core.ui.getContentId()); 
            ResourceLimit.init();
                    
        })
        
    });
                               
}); 

var ResourceLimit = {
  
    'init' : function() {
        
        var limitResource = new JsonRest({
            target: core.getContextPath() + "/api/account/getCloudResourceStat"
        });
        
        dojo.byId("resourceLimitDivLoad").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        
        
        limitResource.query().then(function(data) {
            dojo.forEach(data, function(itemData) {
                
                
                if(itemData.vmLimit === "-1") {
                    dojo.byId("InstanceLimitDiv").style.display = "none";
                }
                 if(itemData.storageLimit === "-1") {
                    dojo.byId("VolumeLimitDiv").style.display = "none";
                }
                 if(itemData.snapshotLimit === "-1") {
                    dojo.byId("SnapshotLimitDiv").style.display = "none";
                }
                 if(itemData.publicIPLimit === "-1") {
                    dojo.byId("PublicIPLimitDiv").style.display = "none";
                }
                 if(itemData.vpcLimit === "-1") {
                    dojo.byId("VPCLimitDiv").style.display = "none";
                }
                 if(itemData.primaryStorageLimit === "-1") {
                    dojo.byId("PrimaryStorageLimitDiv").style.display = "none";
                }
                 if(itemData.secondaryStorageLimit === "-1") {
                    dojo.byId("SecondaryStorageLimitDiv").style.display = "none";
                }
                 if(itemData.cpuLimit === "-1") {
                    dojo.byId("vCPULimitDiv").style.display = "none";
                }
                if(itemData.memoryLimit === "-1") {
                    dojo.byId("MemoryLimitDiv").style.display = "none";
                }  
                if(itemData.networkLimit === "-1") {
                    dojo.byId("NetworkLimitDiv").style.display = "none";
                }  
                if(itemData.vmLimit === "-1" && itemData.storageLimit === "-1" && itemData.snapshotLimit === "-1" && itemData.publicIPLimit === "-1" &&
                        itemData.vpcLimit === "-1" && itemData.primaryStorageLimit === "-1" && itemData.secondaryStorageLimit === "-1" &&
                        itemData.cpuLimit === "-1" && itemData.memoryLimit === "-1" && itemData.networkLimit === "-1") { 
                    dojo.byId("resourceLimitDivLoad").innerHTML = "";
                    dojo.byId("unlimitedResourceLimitMessagBox").style.display = "block";
                }
                
                dijit.byId("instanceLimitLoader").set("value", Math.round(( itemData.vmUsed / itemData.vmLimit) * 100) % 100.00); 
                dijit.byId("volumeLimitLoader").set("value", Math.round(( itemData.storageUsed / itemData.storageLimit) * 100)  % 100.00); 
                dijit.byId("snapshotLimitLoader").set("value", Math.round(( itemData.snapshotUsed / itemData.snapshotLimit) * 100)  % 100.00); 
                dijit.byId("pulicIpLimitLoader").set("value", Math.round(( itemData.publicIPUsed / itemData.publicIPLimit) * 100)  % 100.00); 
                dijit.byId("vpcLimitLoader").set("value", Math.round(( itemData.vpcUsed / itemData.vpcLimit) * 100)  % 100.00);                 
                dijit.byId("primaryStorageLimitLoader").set("value", Math.round(( itemData.primaryStorageLimitUsage / itemData.primaryStorageLimit) * 100)  % 100.00); 
                dijit.byId("secondaryStorageLimitLoader").set("value", Math.round(( itemData.secondaryStorageLimitUsage / itemData.secondaryStorageLimit) * 100)  % 100.00); 
                dijit.byId("vcpuLimitLoader").set("value", Math.round(( itemData.cpuUsed / itemData.cpuLimit) * 100)  % 100.00); 
                dijit.byId("memoryLimitLoader").set("value", Math.round(( itemData.memoryUsed / itemData.memoryLimit) * 100)  % 100.00); 
                
                
                   
                   
                var vmData = itemData.vmLimit === 0 || itemData.vmLimit === null ? "0" : itemData.vmLimit;
                var storageData = itemData.storageLimit === 0 || itemData.storageLimit === null ? "0" : itemData.storageLimit;
                var snapData = itemData.snapshotLimit === 0 || itemData.snapshotLimit === null ? "0" : itemData.snapshotLimit;
                var ipData = itemData.publicIPLimit === 0 || itemData.publicIPLimit === null ? 0 : itemData.publicIPLimit;
                var vpcData = itemData.vpcLimit === 0 || itemData.vpcLimit === null ? 0 : itemData.vpcLimit;
                var psData = itemData.primaryStorageLimit === 0 || itemData.primaryStorageLimit === null ? 0 : itemData.primaryStorageLimit;
                var ssData = itemData.secondaryStorageLimit === 0 || itemData.secondaryStorageLimit === null ? 0 : itemData.secondaryStorageLimit;
                var cpuData = itemData.cpuLimit === 0 || itemData.cpuLimit === null ? 0 : itemData.cpuLimit;
                var ramData = itemData.memoryLimit === 0 || itemData.memoryLimit === null ? 0 : itemData.memoryLimit;
                
                dijit.byId("networkLimitLoader").set("value", Math.round(( itemData.networkUsed / itemData.networkLimit) * 100)  % 100.00); 
                var networkData = itemData.networkLimit === 0 || itemData.networkLimit === null ? 0 : itemData.networkLimit;
                dojo.byId("networkLimitInfo").innerHTML =  itemData.networkUsed + " / " +  networkData + " Network" ;
                   
                 dojo.byId("instanceLimitInfo").innerHTML =  itemData.vmUsed + " / " + vmData + " Instance";
                 dojo.byId("volumeLimitInfo").innerHTML =  itemData.storageUsed + " / " + storageData + " Volume";
                 dojo.byId("snapshotLimitInfo").innerHTML =  itemData.snapshotUsed + " / " + snapData + " Snapshot";
                 dojo.byId("pulicIpInfo").innerHTML =  itemData.publicIPUsed + " / " + ipData + " IP" ;
                 dojo.byId("vpcLimitInfo").innerHTML =  itemData.vpcUsed + " / " + vpcData + " VPC";
                 dojo.byId("primaryStorageInfo").innerHTML =  itemData.primaryStorageLimitUsage + " / " + psData + " GB" ;
                 dojo.byId("secondaryStorageInfo").innerHTML =  itemData.secondaryStorageLimitUsage + " / " + ssData + " GB" ;
                 dojo.byId("vcpuLimitInfo").innerHTML =  itemData.cpuUsed + " / " + cpuData + " Core";
                 dojo.byId("memoryLimitInfo").innerHTML =  itemData.memoryUsed + " / " +  ramData + " MB" ;
                 
                 
                
                   
                
//                if(itemData.vmLimit !== "-1") {
//                    
//                    var chartData = [];
//                    
//                    chartData.push({y: itemData.vmUsed,
//                        tooltip: "Instance resource Used"  + "(" + itemData.vmUsed  + ")"
//                    });
//                    
//                    var resourceAvl = itemData.vmLimit - itemData.vmUsed;
//                    
//                    chartData.push({y: itemData.vmLimit - itemData.vmUsed,
//                        tooltip: "Resource Aviliable" + "(" + resourceAvl.toString() + ")"
//                    });
//
//                    dojo.byId("instanceLimitChart").innerHTML = "";
//
//                    var pieChart = new Chart("instanceLimitChart");
//                    pieChart.setTheme(Tom);
//                    pieChart.addPlot("default", {
//                        type: "Pie",
//                        font: "normal normal 10pt Tahoma",
//                        fontColor: "#2C3742",
//                        labelWiring: "#2C3742",
//                        radius: 70,
//                        labelStyle: "columns",
//                        htmlLabels: true,
//                        startAngle: -10
//                    });
//
//                    pieChart.addSeries("Instance Limit ", chartData);
//                    new MoveSlice(pieChart, "default");
//                    new Tooltip(pieChart, "default");
//                    pieChart.render();
//                }
//                
//                if(itemData.storageLimit !== "-1") {
//                    
//                    var chartData = [];
//
//                    chartData.push({y: itemData.storageUsed,
//                        tooltip: "Storage resource Used"  + "(" + itemData.storageUsed  + ")"
//                    });
//                    
//                    var resourceAvl = itemData.storageLimit - itemData.storageUsed;
//                    
//                    chartData.push({y: itemData.storageLimit - itemData.storageUsed,
//                        tooltip: "Resource Aviliable" + "(" + resourceAvl.toString() + ")"
//                    });
//
//                    dojo.byId("storageLimitChart").innerHTML = "";
//
//                    var pieChart = new Chart("storageLimitChart");
//                    pieChart.setTheme(Tom);
//                    pieChart.addPlot("default", {
//                        type: "Pie",
//                        font: "normal normal 10pt Tahoma",
//                        fontColor: "#2C3742",
//                        labelWiring: "#2C3742",
//                        radius: 70,
//                        labelStyle: "columns",
//                        htmlLabels: true,
//                        startAngle: -10
//                    });
//
//                    pieChart.addSeries("Storage Limit ", chartData);
//                    new MoveSlice(pieChart, "default");
//                    new Tooltip(pieChart, "default");
//                    pieChart.render();
//                                    
//                }
//                                
//                if(itemData.snapshotLimit !== "-1") {
//                    
//                    var chartData = [];
//                    
//                    chartData.push({y: itemData.snapshotUsed,
//                        tooltip: "Snapshot resource Used"  + "(" + itemData.snapshotUsed  + ")"
//                    });
//                    
//                    var resourceAvl = itemData.snapshotLimit - itemData.snapshotUsed;
//                    
//                    chartData.push({y: itemData.snapshotLimit - itemData.snapshotUsed,
//                        tooltip: "Resource Aviliable" + "(" + resourceAvl.toString() + ")"
//                    });
//
//                    dojo.byId("snapshotLimitChart").innerHTML = "";
//
//                    var pieChart = new Chart("snapshotLimitChart");
//                    pieChart.setTheme(Tom);
//                    pieChart.addPlot("default", {
//                        type: "Pie",
//                        font: "normal normal 10pt Tahoma",
//                        fontColor: "#2C3742",
//                        labelWiring: "#2C3742",
//                        radius: 70,
//                        labelStyle: "columns",
//                        htmlLabels: true,
//                        startAngle: -10
//                    });
//
//                    pieChart.addSeries("Snapshot Limit ", chartData);
//                    new MoveSlice(pieChart, "default");
//                    new Tooltip(pieChart, "default");
//                    pieChart.render();
//                }
//                
//                if(itemData.publicIPLimit !== "-1") {
//                    
//                    var chartData = [];
//                    
//                    chartData.push({y: itemData.publicIPUsed,
//                        tooltip: "Public IP Used"  + "(" + itemData.publicIPUsed  + ")"
//                    });
//                    
//                    var resourceAvl = itemData.publicIPLimit - itemData.publicIPUsed;
//                    
//                    chartData.push({y: itemData.publicIPLimit - itemData.publicIPUsed,
//                        tooltip: "Resource Aviliable" + "(" + resourceAvl.toString() + ")"
//                    });
//
//                    dojo.byId("publicIPLimitChart").innerHTML = "";
//
//                    var pieChart = new Chart("publicIPLimitChart");
//                    pieChart.setTheme(Tom);
//                    pieChart.addPlot("default", {
//                        type: "Pie",
//                        font: "normal normal 10pt Tahoma",
//                        fontColor: "#2C3742",
//                        labelWiring: "#2C3742",
//                        radius: 70,
//                        labelStyle: "columns",
//                        htmlLabels: true,
//                        startAngle: -10
//                    });
//
//                    pieChart.addSeries("Public IP Limit ", chartData);
//                    new MoveSlice(pieChart, "default");
//                    new Tooltip(pieChart, "default");
//                    pieChart.render();
//                }
//                
//                
//                if(itemData.primaryStorageLimit !== "-1") {
//                    
//                    var chartData = [];
//                    
//                    chartData.push({y: itemData.primaryStorageLimitUsage,
//                        tooltip: "Primary Storage Used"  + "(" + itemData.primaryStorageLimitUsage  + ")"
//                    });
//                    
//                    var resourceAvl = itemData.primaryStorageLimit - itemData.primaryStorageLimitUsage;
//                    
//                    chartData.push({y: itemData.primaryStorageLimit - itemData.primaryStorageLimitUsage,
//                        tooltip: "Resource Aviliable" + "(" + resourceAvl.toString() + ")"
//                    });
//
//                    dojo.byId("primaryStorageLimitChart").innerHTML = "";
//
//                    var pieChart = new Chart("primaryStorageLimitChart");
//                    pieChart.setTheme(Tom);
//                    pieChart.addPlot("default", {
//                        type: "Pie",
//                        font: "normal normal 10pt Tahoma",
//                        fontColor: "#2C3742",
//                        labelWiring: "#2C3742",
//                        radius: 70,
//                        labelStyle: "columns",
//                        htmlLabels: true,
//                        startAngle: -10
//                    });
//
//                    pieChart.addSeries("Primary Storage Limit ", chartData);
//                    new MoveSlice(pieChart, "default");
//                    new Tooltip(pieChart, "default");
//                    pieChart.render();
//                }
//                
//                if(itemData.secondaryStorageLimit !== "-1") {
//                    
//                    var chartData = [];
//                    
//                    chartData.push({y: itemData.secondaryStorageLimitUsage,
//                        tooltip: "Secondary Storage Used"  + "(" + itemData.secondaryStorageLimitUsage  + ")"
//                    });
//                    
//                    var resourceAvl = itemData.secondaryStorageLimit - itemData.secondaryStorageLimitUsage;
//                    
//                    chartData.push({y: itemData.secondaryStorageLimit - itemData.secondaryStorageLimitUsage,
//                        tooltip: "Resource Aviliable" + "(" + resourceAvl.toString() + ")"
//                    });
//
//                    dojo.byId("secondaryStorageLimitChart").innerHTML = "";
//
//                    var pieChart = new Chart("secondaryStorageLimitChart");
//                    pieChart.setTheme(Tom);
//                    pieChart.addPlot("default", {
//                        type: "Pie",
//                        font: "normal normal 10pt Tahoma",
//                        fontColor: "#2C3742",
//                        labelWiring: "#2C3742",
//                        radius: 70,
//                        labelStyle: "columns",
//                        htmlLabels: true,
//                        startAngle: -10
//                    });
//
//                    pieChart.addSeries("Secondary Storage Limit ", chartData);
//                    new MoveSlice(pieChart, "default");
//                    new Tooltip(pieChart, "default");
//                    pieChart.render();
//                }
//                
//                
//                if(itemData.vpcLimit !== "-1") {
//                    
//                    var chartData = [];
//                    
//                    chartData.push({y: itemData.cpuUsed,
//                        tooltip: "vCPU Used"  + "(" + itemData.cpuUsed  + ")"
//                    });
//                    
//                    var resourceAvl = itemData.vpcLimit - itemData.cpuUsed;
//                    
//                    chartData.push({y: itemData.vpcLimit - itemData.cpuUsed,
//                        tooltip: "Resource Aviliable" + "(" + resourceAvl.toString() + ")"
//                    });
//
//                    dojo.byId("vCPULimitChart").innerHTML = "";
//
//                    var pieChart = new Chart("vCPULimitChart");
//                    pieChart.setTheme(Tom);
//                    pieChart.addPlot("default", {
//                        type: "Pie",
//                        font: "normal normal 10pt Tahoma",
//                        fontColor: "#2C3742",
//                        labelWiring: "#2C3742",
//                        radius: 70,
//                        labelStyle: "columns",
//                        htmlLabels: true,
//                        startAngle: -10
//                    });
//
//                    pieChart.addSeries("vCPU Limit ", chartData);
//                    new MoveSlice(pieChart, "default");
//                    new Tooltip(pieChart, "default");
//                    pieChart.render();
//                }
//                
//                if(itemData.memoryLimit !== "-1") {
//                    
//                    var chartData = [];
//                    
//                    chartData.push({y: itemData.memoryUsed,
//                        tooltip: "Memory Used"  + "(" + itemData.memoryUsed  + ")"
//                    });
//                    
//                    var resourceAvl = itemData.memoryLimit - itemData.memoryUsed;
//                    
//                    chartData.push({y: itemData.memoryLimit - itemData.memoryUsed,
//                        tooltip: "Resource Aviliable" + "(" + resourceAvl.toString() + ")"
//                    });
//
//                    dojo.byId("memoryLimitChart").innerHTML = "";
//
//                    var pieChart = new Chart("memoryLimitChart");
//                    pieChart.setTheme(Tom);
//                    pieChart.addPlot("default", {
//                        type: "Pie",
//                        font: "normal normal 10pt Tahoma",
//                        fontColor: "#2C3742",
//                        labelWiring: "#2C3742",
//                        radius: 70,
//                        labelStyle: "columns",
//                        htmlLabels: true,
//                        startAngle: -10
//                    });
//
//                    pieChart.addSeries("Memory Limit ", chartData);
//                    new MoveSlice(pieChart, "default");
//                    new Tooltip(pieChart, "default");
//                    pieChart.render();
//                }
                
                
                
            });
            dojo.byId("resourceLimitDivLoad").innerHTML = "";
            dojo.byId("resourceLimitDataDiv").style.display = "block";
        });
        
    },
    'enhanceLimitRequestPageRedirect': function() {
        core.router.go("#/user/support/addTicket")
    }
    
};
