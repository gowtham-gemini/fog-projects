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
    "dojo/store/Memory",
    "dojox/grid/DataGrid",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dojo/currency",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "dijit/form/DateTextBox",
    "dijit/layout/TabContainer",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dijit/Editor",
    "dijit/layout/BorderContainer",
    "dijit/form/DropDownButton",
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
ItemFileWriteStore, Memory, DataGrid, ContentPane, Source, MultiSelect, dom, win, localeCurrency) {
    window.query = query;
    window.translator = translator;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Memory = Memory;
    window.Select = Select;
    window.ContentPane = ContentPane;
    window.DataGrid = DataGrid;
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;
    window.isAdvGWSelected = 0;
    window.loadedADVGw = false;
    window.localeCurrency = localeCurrency;
    controller({
        name: "syncAws",
        module: "admin",
        filePath: "/js/app/admin/syncAws.js",
        layout: {
            name: "syncAws",
            containerId: "content"
        },
        scaffold: false
    },
    {
        
    });
});
var ImportDataFromAws = {
    'confirmPullAllDataFromOpenstack': function() {
        dijit.byId("pullAllDataConfirm").show();
    },
    
    'pullAllDataFromOpenstack' : function() {  
        
        dijit.byId("pullAllDataConfirm").hide();       
        
        var pullImageRestStore = new JsonRest({
           target: core.getContextPath()+"/api/image/pullFromOpenstack"
        });

        pullImageRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    dojo.byId("pullImageLoaderImage").style.display = "inline";
                    dijit.byId("pullImageButton").set("disabled", true);
                    dojo.byId("pullImageCheckImage").style.display = "none";
                } else {
                    dojo.byId("pullImageLoaderImage").style.display = "inline";
                    dijit.byId("pullImageButton").set("disabled", true);
                    dojo.byId("pullImageCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });                       
        
    },
    
    'cancelpullAllDataFromOpenstack': function() {
       dijit.byId("pullAllDataConfirm").hide();
    },
    
    "checkDataImportPlanStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_FLAVOR"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullPlanLoaderImage").style.display = "inline";
                dojo.byId("pullPlanCheckImage").style.display = "none";
                dijit.byId("pullPlanButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullPlanLoaderImage").style.display = "none";
                dojo.byId("pullPlanCheckImage").style.display = "inline";
                dijit.byId("pullPlanButton").set("disabled", false);
            }
        });        
    },
    
    "checkDataImportImageStatus" : function() {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });  
                
        asyncJob.query({jobType:"PULL_IMAGE"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullImageLoaderImage").style.display = "inline";
                dojo.byId("pullImageCheckImage").style.display = "none";
                dijit.byId("pullImageButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullImageLoaderImage").style.display = "none";
                dojo.byId("pullImageCheckImage").style.display = "inline";
                dijit.byId("pullImageButton").set("disabled", false);
            }
        }); 
    },    
    'confirmPullImage': function () {
        dijit.byId("pullImageConfirm").show();
    },
    'cancelPullImages': function () {
        dijit.byId("pullImageConfirm").hide();
    },
    'pullImages' : function() {

        dijit.byId("pullImageConfirm").hide();

        var pullImageRestStore = new JsonRest({
           target: core.getContextPath()+"/api/image/pullFromAws"
        });

        pullImageRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    dojo.byId("pullImageLoaderImage").style.display = "inline";
                    dijit.byId("pullImageButton").set("disabled", true);
                    dojo.byId("pullImageCheckImage").style.display = "none";
                } else {
                    dojo.byId("pullImageLoaderImage").style.display = "inline";
                    dijit.byId("pullImageButton").set("disabled", true);
                    dojo.byId("pullImageCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
        
    'checkDataImportRegionStatus': function() {
            
            var asyncJob = new JsonRest({
                target: core.getContextPath() + "/api/asyncJob"
            });
            
            asyncJob.query({jobType:"PULL_REGION"}).then(function(data) {
                if(data[0] === "OK") {
                    dojo.byId("pullRegionLoaderImage").style.display = "inline";
                    dojo.byId("pullRegionCheckImage").style.display = "none";
                    dijit.byId("pullRegionButton").set("disabled", true);
                } else if(data[0] === "FALSE") {
                    dojo.byId("pullRegionLoaderImage").style.display = "none";
                    dojo.byId("pullRegionCheckImage").style.display = "inline";
                    dijit.byId("pullRegionButton").set("disabled", false);
                }
            })
            
    },  
    
    "checkDataImportZoneStatus" : function() {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });  
                
        asyncJob.query({jobType:"PULL_ZONE"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullZoneLoaderImage").style.display = "inline";
                dojo.byId("pullZoneCheckImage").style.display = "none";
                dijit.byId("pullZoneButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullZoneLoaderImage").style.display = "none";
                dojo.byId("pullZoneCheckImage").style.display = "inline";
                dijit.byId("pullZoneButton").set("disabled", false);
            }
        }); 
    },
    'confirmZonePull': function() {
       dijit.byId("pullZoneConfirm").show();
    },
    'pullAllZone': function() {
       
        dijit.byId("pullZoneConfirm").hide();
       
        var pullZoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/pullFromAws"
        });
        
        pullZoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullZoneLoaderImage").style.display = "inline";
                    dijit.byId("pullZoneButton").set("disabled", true);
                    dojo.byId("pullZoneCheckImage").style.display = "none";
                     
                   
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dojo.byId("pullZoneLoaderImage").style.display = "none";
                    dijit.byId("pullZoneButton").set("disabled", false);
                    dojo.byId("pullZoneCheckImage").style.display = "none";
                }
            });
        });
    },
    'cancelZonePull':function() {
       dijit.byId("pullZoneConfirm").hide();
    },
    
    'pullConfirmRegions': function() {
        
        ImportDataFromAws.confirmPullAlert();
    }, 

    'confirmPullAlert': function() {
        
        dijit.byId("pullRegionConfirm").show();
    },
    'pullRegions': function() {
        dijit.byId("pullRegionConfirm").hide();
       
        var pullRegionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/region/pullFromAws"
        });

        pullRegionRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullRegionLoaderImage").style.display = "inline";
                    dijit.byId("pullRegionButton").set("disabled", true);
                   
                } else {

                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    'cancelPull': function() {
        dijit.byId("pullRegionConfirm").hide();
    },
    'ConfirmPullVpc':function() {
        dijit.byId("pullVpcConfirm").show();
    },
    'pullVpc':function() {
        dijit.byId("pullVpcConfirm").hide();
        
        var pullVpcRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/pullFromAws"
        });
        
        pullVpcRestStore.query().then(function(data){
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullVpcLoaderImage").style.display = "inline";
                    dijit.byId("pullVpcButton").set("disabled", true);
                   
                } else {

                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        })
        
        
    },
    'cancelPullVpc':function() {
        dijit.byId("pullVpcConfirm").hide();
    },
    'checkDataImportVpcStatus':function() {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });  
                
        asyncJob.query({jobType:"PULL_VPC"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullVpcLoaderImage").style.display = "inline";
                dojo.byId("pullVpcCheckImage").style.display = "none";
                dijit.byId("pullVpcButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullVpcLoaderImage").style.display = "none";
                dojo.byId("pullVpcCheckImage").style.display = "inline";
                dijit.byId("pullVpcButton").set("disabled", false);
            }
        })
    },
    
    'importInstance':function() {
        console.log("Hi")
        
    }
    
    
     
};
window.ImportDataFromAws = ImportDataFromAws;
