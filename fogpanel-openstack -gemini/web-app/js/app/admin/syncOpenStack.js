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
        name: "syncOpenStack",
        module: "admin",
        filePath: "/js/app/admin/syncOpenStack.js",
        layout: {
            name: "syncOpenStack",
            containerId: "content"
        },
        scaffold: false
    },
    {
        
    });
});
var ImportDataFromOpenstack = {
    'confirmPullAllDataFromOpenstack': function() {
        dijit.byId("pullAllDataConfirm").show();
    },
    
    'pullAllDataFromOpenstack' : function() {
        
        dijit.byId("pullAllDataConfirm").hide();
        
        var pullPlanRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/pullFromOpenstack"
        });

        pullPlanRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullPlanLoaderImage").style.display = "inline";
                    dijit.byId("pullPlanButton").set("disabled", true);
                    dojo.byId("pullPlanCheckImage").style.display = "none";
                   
                } else {
                    dijit.byId("pullPlanButton").set("disabled", false);
                    dojo.byId("pullPlanLoaderImage").style.display = "none";
                    dojo.byId("pullPlanCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
        
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
        
        
        var pullFromRestStore = new JsonRest({
           target: core.getContextPath()+"/api/volumetypes/pullFromOpenstack"
        });

        pullFromRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    dojo.byId("pullVolumeTypeLoaderImage").style.display = "inline";
                    dijit.byId("pullVolumeTypeButton").set("disabled", true);
                    dojo.byId("pullVolumeTypeCheckImage").style.display = "none";

                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dojo.byId("pullVolumeTypeLoaderImage").style.display = "none";
                    dojo.byId("pullVolumeTypeCheckImage").style.display = "none";
                    dijit.byId("pullVolumeTypeButton").set("disabled", false);
                }
            });
        });
        
        var pullZoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/pullFromOpenstack"
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
        
        var pullRegionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/region/pullFromOpenstack"
        });
        
        pullRegionRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullRegionsLoaderImage").style.display = "inline";
                    dijit.byId("pullRegionsButton").set("disabled", true);
                    dojo.byId("pullRegionsCheckImage").style.display = "none";
                   
                } else {

                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dojo.byId("pullRegionsLoaderImage").style.display = "none";
                    dijit.byId("pullRegionsButton").set("disabled", false);
                    dojo.byId("pullRegionsCheckImage").style.display = "none";
                }
            });
        });
        
//        var pullDomainRestStore = new JsonRest({
//            target: core.getContextPath() + "/api/domain/pullFromOpenStack"
//        });
//        
//        pullDomainRestStore.query().then(function(data) {
//            dojo.forEach(data, function(resultData) {
//                if (resultData === "OK") {
//                    dojo.byId("pullDomainsLoaderImage").style.display = "inline";
//                    dijit.byId("pullDomainsButton").set("disabled", true);
//                    dojo.byId("pullDomainsCheckImage").style.display = "none";
//                   
//                } else {
//
//                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
//                    registry.byId("appToaster").show();
//                    dojo.byId("pullDomainsLoaderImage").style.display = "none";
//                    dijit.byId("pullDomainsButton").set("disabled", false);
//                    dojo.byId("pullDomainsCheckImage").style.display = "none";
//                }
//            });
//        });
//        
//         var pullAccountRestStore = new JsonRest({
//            target: core.getContextPath() + "/api/account/pullFromOpenStack"
//        });
//        
//        pullAccountRestStore.query().then(function(data) {
//            dojo.forEach(data, function(resultData) {
//                if (resultData === "OK") {
//                    dojo.byId("pullAccountLoaderImage").style.display = "inline";
//                    dijit.byId("pullAccountButton").set("disabled", true);
//                    dojo.byId("pullAccountCheckImage").style.display = "none";
//                    
//                } else {
//                    dijit.byId("pullAccountButton").set("disabled", false);
//                    dojo.byId("pullAccountLoaderImage").style.display = "none";
//                    dojo.byId("pullAccountCheckImage").style.display = "none";
//                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
//                    registry.byId("appToaster").show();
//                }
//            });
//        });
//        var pullUserRestStore = new JsonRest({
//            target: core.getContextPath() + "/api/user/pullFromOpenStack"
//        });
//        
//        pullUserRestStore.query().then(function(data) {
//            dojo.forEach(data, function(resultData) {
//                if (resultData === "OK") {
//                    dojo.byId("pullUserLoaderImage").style.display = "inline";
//                    dijit.byId("pullUserButton").set("disabled", true);
//                    dojo.byId("pullUserCheckImage").style.display = "none";
//                    
//                } else {
//                    dijit.byId("pullUserButton").set("disabled", false);
//                    dojo.byId("pullUserLoaderImage").style.display = "none";
//                    dojo.byId("pullUserCheckImage").style.display = "none";
//                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
//                    registry.byId("appToaster").show();
//                }
//            });
//        });
        
    },
    
    'cancelpullAllDataFromOpenstack': function() {
       dijit.byId("pullAllDataConfirm").hide();
    },
    
    "checkDataImportVolumeTypeStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });  
        
        asyncJob.query({jobType:"PULL_VOLUME_TYPE"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullVolumeTypeLoaderImage").style.display = "inline";
                dojo.byId("pullVolumeTypeCheckImage").style.display = "none";
                dijit.byId("pullVolumeTypeButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullVolumeTypeLoaderImage").style.display = "none";
                dojo.byId("pullVolumeTypeCheckImage").style.display = "inline";
                dijit.byId("pullVolumeTypeButton").set("disabled", false);
            }
        }); 
        
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
     "checkDataImportRegionsStatus" : function() {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });  
                
        asyncJob.query({jobType:"PULL_REGION"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullRegionsLoaderImage").style.display = "inline";
                dojo.byId("pullRegionsCheckImage").style.display = "none";
                dijit.byId("pullRegionsButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullRegionsLoaderImage").style.display = "none";
                dojo.byId("pullRegionsCheckImage").style.display = "inline";
                dijit.byId("pullRegionsButton").set("disabled", false);
            }
        }); 
    },
     "checkDataImportDomainsStatus" : function() {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });  
                
        asyncJob.query({jobType:"PULL_DOMAIN"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullDomainsLoaderImage").style.display = "inline";
                dojo.byId("pullDomainsCheckImage").style.display = "none";
                dijit.byId("pullDomainsButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullDomainsLoaderImage").style.display = "none";
                dojo.byId("pullDomainsCheckImage").style.display = "inline";
                dijit.byId("pullDomainsButton").set("disabled", false);
            }
        }); 
    },
    "checkDataImportAccountStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_ACCOUNT"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullAccountLoaderImage").style.display = "inline";
                dojo.byId("pullAccountCheckImage").style.display = "none";
                dijit.byId("pullAccountButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullAccountLoaderImage").style.display = "none";
                dojo.byId("pullAccountCheckImage").style.display = "inline";
                dijit.byId("pullAccountButton").set("disabled", false);
            }
        });        
    },
    "checkDataImportUserStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_USER"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullUserLoaderImage").style.display = "inline";
                dojo.byId("pullUserCheckImage").style.display = "none";
                dijit.byId("pullUserButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullUserLoaderImage").style.display = "none";
                dojo.byId("pullUserCheckImage").style.display = "inline";
                dijit.byId("pullUserButton").set("disabled", false);
            }
        });        
    },
    'confirmPullAccount': function() {
        dijit.byId("pullAccountConfirm").show();
    },
    'cancelPullAccount': function() {
        dijit.byId("pullAccountConfirm").hide();
    },
    "pullAccount": function() {

        dijit.byId("pullAccountConfirm").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/account/pullFromOpenStack"
        });
        
        restStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullAccountLoaderImage").style.display = "inline";
                    dijit.byId("pullAccountButton").set("disabled", true);
                    dojo.byId("pullAccountCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullAccountButton").set("disabled", false);
                    dojo.byId("pullAccountLoaderImage").style.display = "none";
                    dojo.byId("pullAccountCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    'confirmPullUser': function() {
        dijit.byId("pullUserConfirm").show();
    },
    'cancelPullUser': function() {
        dijit.byId("pullUserConfirm").hide();
    },
    "pullUser": function() {

        dijit.byId("pullUserConfirm").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/user/pullFromOpenStack"
        });
        
        restStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullUserLoaderImage").style.display = "inline";
                    dijit.byId("pullUserButton").set("disabled", true);
                    dojo.byId("pullUserCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullUserButton").set("disabled", false);
                    dojo.byId("pullUserLoaderImage").style.display = "none";
                    dojo.byId("pullUserCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    'conformPullPlan': function() {
        dijit.byId("pullPlanConform").show();
    },
    'cancelPullPlan': function() {
        dijit.byId("pullPlanConform").hide();
    },
    'confirmPullNetwork' : function () {
        dijit.byId("pullNetworkConform").show();
    },
    'pullNetwork' : function () {
        dijit.byId("pullNetworkConform").hide();

        var pullPlanRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/pullFromOpenstack"
        });
        
        pullPlanRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    
                    dojo.byId("pullNetworkLoaderImage").style.display = "inline";
                    dijit.byId("pullNetworkButton").set("disabled", true);
                    dojo.byId("pullNetworkCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullNetworkButton").set("disabled", false);
                    dojo.byId("pullNetworkLoaderImage").style.display = "none";
                    dojo.byId("pullNetworkCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    checkDataImportNetworkStatus : function () {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_NETWORK"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullNetworkLoaderImage").style.display = "inline";
                dojo.byId("pullNetworkCheckImage").style.display = "none";
                dijit.byId("pullNetworkButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullNetworkLoaderImage").style.display = "none";
                dojo.byId("pullNetworkCheckImage").style.display = "inline";
                dijit.byId("pullNetworkButton").set("disabled", false);
            }
        });  
        
    },
    'cancelPullNetwork' : function () {
        dijit.byId("pullNetworkConform").hide();
    },
    "pullPlan": function() {

        dijit.byId("pullPlanConform").hide();

        var pullPlanRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/pullFromOpenstack"
        });
        
        pullPlanRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullPlanLoaderImage").style.display = "inline";
                    dijit.byId("pullPlanButton").set("disabled", true);
                    dojo.byId("pullPlanCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullPlanButton").set("disabled", false);
                    dojo.byId("pullPlanLoaderImage").style.display = "none";
                    dojo.byId("pullPlanCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
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
    'confirmPullVolumeType': function () {
        dijit.byId("pullVolumeTypeConfirm").show();
    },
    'cancelPullVolumeType': function () {
        dijit.byId("pullVolumeTypeConfirm").hide();
    },
    'pullVolumeType' : function() {

        dijit.byId("pullVolumeTypeConfirm").hide();

        var pullFromRestStore = new JsonRest({
           target: core.getContextPath()+"/api/volumetypes/pullFromOpenstack"
        });

        pullFromRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    dojo.byId("pullVolumeTypeLoaderImage").style.display = "inline";
                    dijit.byId("pullVolumeTypeButton").set("disabled", true);
                    dojo.byId("pullVolumeTypeCheckImage").style.display = "none";

                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dojo.byId("pullVolumeTypeLoaderImage").style.display = "none";
                    dojo.byId("pullVolumeTypeCheckImage").style.display = "none";
                    dijit.byId("pullVolumeTypeButton").set("disabled", false);
                }
            });
        });

    },
    
    'confirmZonePull': function() {
       dijit.byId("pullZoneConfirm").show();
    },
    'pullAllZone': function() {
       
        dijit.byId("pullZoneConfirm").hide();
       
        var pullZoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/pullFromOpenstack"
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
    'confirmPullRegion': function() {
       dijit.byId("pullRegionsConfirm").show();
    },
    'pullAllRegion': function() {
       
        dijit.byId("pullRegionsConfirm").hide();
       
        var pullRegionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/region/pullFromOpenstack"
        });
        
        pullRegionRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullRegionsLoaderImage").style.display = "inline";
                    dijit.byId("pullRegionsButton").set("disabled", true);
                    dojo.byId("pullRegionsCheckImage").style.display = "none";
                   
                } else {

                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dojo.byId("pullRegionsLoaderImage").style.display = "none";
                    dijit.byId("pullRegionsButton").set("disabled", false);
                    dojo.byId("pullRegionsCheckImage").style.display = "none";
                }
            });
        });
    },
    'cancelRegionPull':function() {
       dijit.byId("pullRegionsConfirm").hide();
    },
    'confirmPullDomain': function() {
       dijit.byId("pullDomainsConfirm").show();
    },
    'pullAllDomain': function() {
       
        dijit.byId("pullDomainsConfirm").hide();
       
        var pullDomainRestStore = new JsonRest({
            target: core.getContextPath() + "/api/domain/pullFromOpenStack"
        });
        
        pullDomainRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullDomainsLoaderImage").style.display = "inline";
                    dijit.byId("pullDomainsButton").set("disabled", true);
                    dojo.byId("pullDomainsCheckImage").style.display = "none";
                   
                } else {

                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dojo.byId("pullDomainsLoaderImage").style.display = "none";
                    dijit.byId("pullDomainsButton").set("disabled", false);
                    dojo.byId("pullDomainsCheckImage").style.display = "none";
                }
            });
        });
    },
    'cancelDomainPull':function() {
       dijit.byId("pullDomainsConfirm").hide();
    },
    
};
window.ImportDataFromOpenstack = ImportDataFromOpenstack;