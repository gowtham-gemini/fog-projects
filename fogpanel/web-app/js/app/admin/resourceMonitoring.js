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
    "dojo/store/Memory",
    "dijit/tree/ObjectStoreModel", 
    "dijit/Tree",
    "dojox/grid/LazyTreeGrid",
    "dijit/tree/ForestStoreModel",
    "dijit/layout/AccordionContainer",
    "dijit/layout/AccordionPane",
    "dijit/TitlePane",
    "dijit/ProgressBar",
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
    "dijit/Dialog",
    "dijit/DropDownMenu",
    "dijit/MenuItem",
    "dojox/grid/enhanced/plugins/Menu",
    "dijit/popup",
    "dojo/_base/lang",
    "dojox/charting/widget/Chart2D",
    "dojox/charting/themes/Claro",   
    "dojo/dom-construct",
    "dojo/on",
    "FogPanel/VolumeListItem",
    "FogPanel/InstanceStatus",
    "dojox/widget/rotator/Pan",
    "dijit/form/CheckBox",
    "dijit/form/NumberSpinner",
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
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
                ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win,
                Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, 
                Highlight, ClusteredColumns, Legend, Memory, ObjectStoreModel, Tree, LazyTreeGrid, ForestStoreModel,AccordionContainer,
            AccordionPane, TitlePane, ProgressBar) {
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
    window.EnhancedGrid = EnhancedGrid;
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
    window.Memory = Memory; 
    window.ObjectStoreModel = ObjectStoreModel; 
    window.Tree = Tree;
    window.Highlight = Highlight;
    window.LazyTreeGrid = LazyTreeGrid;
    window.AccordionContainer = AccordionContainer;
    window.ForestStoreModel = ForestStoreModel;
    window.AccordionPane = AccordionPane;
    window.TitlePane = TitlePane;
    window.ProgressBar = ProgressBar;
    window.currentUserId = "";
    window.currentInvoiceId = "";
    controller({
        name: "resourceMonitoring",
        module: "admin",
        filePath: "/js/app/admin/resourceMonitoring.js",
        layout: {
            name: "resourceMonitoring",
            containerId: "content"
        },
        scaffold: false
    },
            {
                "index": action(function() {
                    if(dijit.byId("resourceForm")) {
                       dijit.byId("resourceForm").destroyRecursive(); 
                    }
                    if(dijit.byId("resourceHostForm")) {
                       dijit.byId("resourceHostForm").destroyRecursive(); 
                    }                    
                    core.ui.loadTemplate("resourceMonitoring", core.ui.getContentId());
                    ResourceMonitoring.init();
                    ResourceMonitoring.populateValues();
                })
            });
    });
            
var ResourceMonitoring = {
    _zoneListStore: "",
    _capListStore: "",
    _hostResourceStore: "",
    _podListStore: "",
    _clusterListStore: "",
    _hostListStore: "",
    init: function() {
        this._zoneListStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        }); 
        
        this._capListStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/capacity"
        }); 
        
        this._podListStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        }); 
                
        this._clusterListStore = new JsonRest({
            target: core.getContextPath()+"/api/cluster/"
        });
        
        this._hostListStore = new JsonRest({
            target: core.getContextPath()+"/api/host/"
        });
        
         this._hostResourceStore = new JsonRest({
            target: core.getContextPath()+"/api/host/resource/"
        });
        
    },
    populateValues: function() {
    
    
    this._zoneListStore.query().then(function(data) {
       dojo.forEach(data, function(el) {
        if(dijit.byId(el.referenceZoneId)) {
            dijit.byId(el.referenceZoneId).destroyRecursive();
        } 
        var tp = new TitlePane({
               title: el.aliasName,
               id:el.referenceZoneId,
               open:false,
               onShow: function () {
                  ResourceMonitoring.loadZoneContent(this); 
               }
           }).placeAt("zoneListAccordion");
            tp.startup();
       });
       
   }); 
   
   
    },
     test: function(t) {
         
//       dojo.empty("reportChart");
       
               
        t.addChild(new dijit.layout.AccordionPane({
            title: "pod",
            content:"hello",
            onShow: function () {

            }
        }));
        
        
    },
    
    loadResorce: function(type, ItemId) {
        this._capListStore.query({type:type, itemId:ItemId}).then(function(data) {
            dojo.forEach(data, function(el) {
               

                if(el.memoryTotal =="null" || el.memoryTotal == null || el.memoryTotal =="") {
                    dojo.byId("memeoryTotal").innerHTML = translator.common.allocated + ": 0 / 0"; 
                    dijit.byId("memeoryBar").set("value", 0 % 100.00); 
                } else {
                    dojo.byId("memeoryTotal").innerHTML = el.memoryTotal; 
                    dijit.byId("memeoryBar").set("value", el.memoryPercentage % 100.00);   
                }

                if(el.cpuTotal =="null" || el.cpuTotal == null || el.cpuTotal =="") {
                    dojo.byId("cpuTotal").innerHTML = translator.common.allocated + ": 0 / 0"; 
                    dijit.byId("cpuBar").set("value", 0 % 100.00); 
                } else {
                    dojo.byId("cpuTotal").innerHTML = el.cpuTotal; 
                    dijit.byId("cpuBar").set("value", el.cpuPercentage % 100.00);  
                }

                if(el.primaryStorageTotal =="null" || el.primaryStorageTotal == null || el.primaryStorageTotal =="") {
                    dojo.byId("pStorageTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("pStorageBar").set("value", 0 % 100.00); 
                } else {
                    dojo.byId("pStorageTotal").innerHTML = el.primaryStorageTotal; 
                    dijit.byId("pStorageBar").set("value", el.primaryStoragePercentage % 100.00);  
                }

                if(el.storageTotal =="null" || el.storageTotal == null || el.storageTotal =="") {
                    dojo.byId("storageTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("storageBar").set("value", 0 % 100.00); 
                } else {
                    dojo.byId("storageTotal").innerHTML = el.storageTotal; 
                    dijit.byId("storageBar").set("value", el.storagePercentage % 100.00);  
                }
                if(el.publicIpTotal =="null" || el.publicIpTotal == null || el.publicIpTotal =="") {
                    dojo.byId("publicIpTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("publicIpBar").set("value", 0 % 100.00); 
                } else {
                    dojo.byId("publicIpTotal").innerHTML = el.publicIpTotal; 
                    dijit.byId("publicIpBar").set("value", el.publicIpPercentage % 100.00);  
                }

                if(el.publicIpTotal =="null" || el.publicIpTotal == null || el.publicIpTotal =="") {
                    dojo.byId("publicIpTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("publicIpBar").set("value", 0 % 100.00); 
                } else {
                    dojo.byId("publicIpTotal").innerHTML = el.publicIpTotal; 
                    dijit.byId("publicIpBar").set("value", el.publicIpPercentage % 100.00);  
                }
                if(el.managementIpTotal =="null" || el.managementIpTotal == null || el.managementIpTotal =="") {
                    dojo.byId("managementIpTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("managementIpBar").set("value", 0 % 100.00);  
                } else {
                    dojo.byId("managementIpTotal").innerHTML = el.managementIpTotal; 
                    dijit.byId("managementIpBar").set("value", el.managementIpPercentage % 100.00); 
                }

                if(el.secodaryTotal =="null" || el.secodaryTotal == null || el.secodaryTotal =="") {
                    dojo.byId("secodaryTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("secodaryBar").set("value", 0 % 100.00);  
                } else {
                    dojo.byId("secodaryTotal").innerHTML = el.secodaryTotal; 
                    dijit.byId("secodaryBar").set("value", el.secodaryPercentage % 100.00);    
                }

                if(el.VLANTotal =="null" || el.VLANTotal == null || el.VLANTotal =="") {
                    dojo.byId("VLANTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("VLANBar").set("value", 0 % 100.00);  
                } else {
                    dojo.byId("VLANTotal").innerHTML = el.VLANTotal; 
                    dijit.byId("VLANBar").set("value", el.VLANPercentage % 100.00);    
                }

                if(el.directPublicIpTotal =="null" || el.directPublicIpTotal == null || el.directPublicIpTotal =="") {
                    dojo.byId("directPublicIpTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("directPublicIpBar").set("value", 0 % 100.00);  
                } else {
                    dojo.byId("directPublicIpTotal").innerHTML = el.directPublicIpTotal; 
                    dijit.byId("directPublicIpBar").set("value", el.directPublicIpPercentage % 100.00);
                }
                if(el.localStorageTotal =="null" || el.localStorageTotal == null || el.localStorageTotal =="") {
                    dojo.byId("localStorageTotal").innerHTML = translator.common.allocated +": 0 / 0"; 
                    dijit.byId("localStorageBar").set("value", 0 % 100.00);  
                } else {
                    dojo.byId("localStorageTotal").innerHTML = el.localStorageTotal; 
                    dijit.byId("localStorageBar").set("value", el.localStoragePercentage % 100.00);  
                }
            });

        }); 
    },
    
    loadZoneContent: function(t) {
    
        dojo.byId("resourceFormDiv").style.display = "block";
        dojo.byId("resourceHostFormDiv").style.display = "none";

        ResourceMonitoring.loadResorce("zoneid", t.id); 

        this._zoneListStore.query().then(function(data) {
               dojo.forEach(data, function(el) {
                if(t.id != el.referenceZoneId) {
                    dijit.byId(el.referenceZoneId).set("open", false);
                }
               });

           }); 
        this._podListStore.get(t.id).then(function(data) {
                dojo.forEach(data, function(el) {
                    if(dijit.byId(el.podReferenceId)) {
                    dijit.byId(el.podReferenceId).destroyRecursive();
                } 
                var tp = new TitlePane({
                    title: el.podName,
                    id:el.podReferenceId,
                    open:false,
                    onShow: function () {
                      ResourceMonitoring.loadPodContent(this, t.id); 
                   }
                });
                t.addChild(tp);
                tp.startup();
            });         
        });



        dojo.byId("contentAccordion").innerHTML = translator.common.zone + ":" + t.title;
    },
    loadPodContent: function(t, zoneId) {
        
        dojo.byId("resourceFormDiv").style.display = "block";
        dojo.byId("resourceHostFormDiv").style.display = "none";
        
        ResourceMonitoring.loadResorce("podid", t.id); 
        
        this._podListStore.get(zoneId).then(function(data) {
            dojo.forEach(data, function(el) {
                if(t.id != el.podReferenceId) {
                    dijit.byId(el.podReferenceId).set("open", false);
                }
            });
           
        }); 
        
        
        this._clusterListStore.get(t.id).then(function(data) {
            dojo.forEach(data, function(el) {
                if(dijit.byId(el.clusterReferenceId)) {
                    dijit.byId(el.clusterReferenceId).destroyRecursive();
                }    
                var tp = new TitlePane({
                title: el.clusterName,
                id:el.clusterReferenceId,
                open:false,
                onShow: function () {
                  ResourceMonitoring.loadClusterContent(this, t.id); 
                }
                });
                t.addChild(tp);
                tp.startup();
                
            });
            
        }); 
        
        dojo.byId("contentAccordion").innerHTML = translator.common.pod + ":" + t.title;
                
        
    },
    loadClusterContent: function(t, podId) {
        
        ResourceMonitoring.loadResorce("clusterid", t.id); 
        
        dojo.byId("resourceFormDiv").style.display = "block";
        dojo.byId("resourceHostFormDiv").style.display = "none";
        
        this._clusterListStore.get(podId).then(function(data) {
            dojo.forEach(data, function(el) {
                if(t.id != el.clusterReferenceId) {
                    dijit.byId(el.clusterReferenceId).set("open", false);
                }
            });
           
        });
        
        
        this._hostListStore.get(t.id).then(function(data) {
            dojo.forEach(data, function(el) {
                if(dijit.byId(el.hostReferenceId)) {
                    dijit.byId(el.hostReferenceId).destroyRecursive();
                }    
                var tp = new TitlePane({
                title: el.hostName,
                id:el.hostReferenceId,
                open:false,
                onShow: function () {
                  ResourceMonitoring.loadHostContent(this, t.id); 
                }
                });
                t.addChild(tp);
                tp.startup();
                
            });
            
        }); 
        
        dojo.byId("contentAccordion").innerHTML = translator.common.cluster +":" + t.title;
        
    
    },
    loadHostContent: function(t, clusterId) {
        
        dojo.byId("resourceFormDiv").style.display = "none";
        dojo.byId("resourceHostFormDiv").style.display = "block";
         
         this._hostListStore.get(clusterId).then(function(data) {
            dojo.forEach(data, function(el) {
                if(t.id != el.hostReferenceId) {
                    dijit.byId(el.hostReferenceId).set("open", false);
                }
            });
           
        });
        
        this._hostResourceStore.get(t.id).then(function(data) {
            dojo.forEach(data, function(el) {                
                dojo.byId("totalCpu").innerHTML = el.totalCpu; 
                dojo.byId("cpuUtilized").innerHTML = el.cpuUsed; 
                dojo.byId("hostUsedTotal").innerHTML = el.memoryUsed; 
                dojo.byId("cpuAllocated").innerHTML = el.cpuAllocated; 
                dojo.byId("hostMemoryTotal").innerHTML = el.memory; 
                dojo.byId("hostAllocatedTotal").innerHTML = el.memoryAllocated; 
                dojo.byId("networkRead").innerHTML = el.networkkbsRead; 
                dojo.byId("networkWrite").innerHTML = el.networkkbsWrite; 
            });          
        });                             
        dojo.byId("contentAccordion").innerHTML = translator.common.host+":" + t.title;
    }    
};
