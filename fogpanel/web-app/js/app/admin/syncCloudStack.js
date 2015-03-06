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
        name: "syncCloudStack",
        module: "admin",
        filePath: "/js/app/admin/syncCloudStack.js",
        layout: {
            name: "syncCloudStack",
            containerId: "content"
        },
        scaffold: false
    },
    {
        
    });
});
var ImportDataFromCloudstack = {
    'confirmPullAllData': function() {
        dijit.byId("pullAllDataConfirm").show();
    },
    
    'pullAllData' : function() {
        
        dijit.byId("pullAllDataConfirm").hide();
        
        var pullPlanRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/pullFromOpenstack"
        });

        pullPlanRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullDomainLoaderImage").style.display = "inline";
                    dijit.byId("pullDomainButton").set("disabled", true);
                    dojo.byId("pullDomainCheckImage").style.display = "none";
                   
                } else {
                    dijit.byId("pullDomainButton").set("disabled", false);
                    dojo.byId("pullDomainLoaderImage").style.display = "none";
                    dojo.byId("pullDomainCheckImage").style.display = "none";
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
            target: core.getContextPath() + "/api/zone/pullFromCloudStack"
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
    
    'cancelpullAllData': function() {
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
    "checkDataImportDomainStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_DOMAIN"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullDomainLoaderImage").style.display = "inline";
                dojo.byId("pullDomainCheckImage").style.display = "none";
                dijit.byId("pullDomainButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullDomainLoaderImage").style.display = "none";
                dojo.byId("pullDomainCheckImage").style.display = "inline";
                dijit.byId("pullDomainButton").set("disabled", false);
            }
        });        
    },
    "checkDataImportVPCStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_VPC"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullVPCLoaderImage").style.display = "inline";
                dojo.byId("pullVPCCheckImage").style.display = "none";
                dijit.byId("pullVPCButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullVPCLoaderImage").style.display = "none";
                dojo.byId("pullVPCCheckImage").style.display = "inline";
                dijit.byId("pullVPCButton").set("disabled", false);
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
    "checkDataImportPlanStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_COMPUTE_OFFER"}).then(function(data) {
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
    "checkDataImportStorageStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_DISK_OFFER"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullStorageLoaderImage").style.display = "inline";
                dojo.byId("pullStorageCheckImage").style.display = "none";
                dijit.byId("pullStorageButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullStorageLoaderImage").style.display = "none";
                dojo.byId("pullStorageCheckImage").style.display = "inline";
                dijit.byId("pullStorageButton").set("disabled", false);
            }
        });        
    },
    "checkDataImportTemplateStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_TEMPLATE"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullTemplateLoaderImage").style.display = "inline";
                dojo.byId("pullTemplateCheckImage").style.display = "none";
                dijit.byId("pullTemplateButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullTemplateLoaderImage").style.display = "none";
                dojo.byId("pullTemplateCheckImage").style.display = "inline";
                dijit.byId("pullTemplateButton").set("disabled", false);
            }
        });        
    },
    "checkDataImportVPCOfferingStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_VPC_OFFERING"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullVPCOfferingLoaderImage").style.display = "inline";
                dojo.byId("pullVPCOfferingCheckImage").style.display = "none";
                dijit.byId("pullVPCOfferingButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullVPCOfferingLoaderImage").style.display = "none";
                dojo.byId("pullVPCOfferingCheckImage").style.display = "inline";
                dijit.byId("pullVPCOfferingButton").set("disabled", false);
            }
        });        
    },
    "checkDataImportNetworkOfferingStatus" : function() {
         var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });
        
        asyncJob.query({jobType:"PULL_NETWORK_OFFERING"}).then(function(data) {
            if(data[0] === "OK") {
                dojo.byId("pullNetworkOfferingLoaderImage").style.display = "inline";
                dojo.byId("pullNetworkOfferingCheckImage").style.display = "none";
                dijit.byId("pullNetworkOfferingButton").set("disabled", true);
            } else if(data[0] === "FALSE") {
                dojo.byId("pullNetworkOfferingLoaderImage").style.display = "none";
                dojo.byId("pullNetworkOfferingCheckImage").style.display = "inline";
                dijit.byId("pullNetworkOfferingButton").set("disabled", false);
            }
        });        
    },
    "checkDataImportNetworkStatus" : function() {
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
         
       var zoneRestStore = new JsonRest({
           target: core.getContextPath() + "/api/zone/"
       });
        
       zoneRestStore.query().then(function (zoneData) {
           if(zoneData.length === 0 || zoneData === undefined || zoneData === "undefined" || zoneData === "") {
                dojo.byId("pullPlanContainer").style.display = "none";
                dojo.byId("pullDiskOfferContainer").style.display = "none";                
            } else {
                dojo.byId("pullPlanContainer").style.display = "block";
                dojo.byId("pullDiskOfferContainer").style.display = "block";
            }
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
    'conformPullDomain': function() {
        dijit.byId("pullDomainConform").show();
    },
    'cancelPullDomain': function() {
        dijit.byId("pullDomainConform").hide();
    },
    "pullDomain": function() {

        dijit.byId("pullDomainConform").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/domain/pullFromCloudStack"
        });
        
        restStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullDomainLoaderImage").style.display = "inline";
                    dijit.byId("pullDomainButton").set("disabled", true);
                    dojo.byId("pullDomainCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullDomainButton").set("disabled", false);
                    dojo.byId("pullDomainLoaderImage").style.display = "none";
                    dojo.byId("pullDomainCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
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
            target: core.getContextPath() + "/api/account/pullFromCloudStack"
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
            target: core.getContextPath() + "/api/user/pullFromCloudStack"
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
    "pullPlan": function() {

        dijit.byId("pullPlanConform").hide();

        var pullPlanRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/pullFromCloudStack"
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
    'confirmPullStorage': function() {
        dijit.byId("pullStorageConfirm").show();
    },
    'cancelPullStorage': function() {
        dijit.byId("pullStorageConfirm").hide();
    },
    "pullStorage": function() {

        dijit.byId("pullStorageConfirm").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/diskOffer/pullFromCloudStack"
        });
        
        restStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullStorageLoaderImage").style.display = "inline";
                    dijit.byId("pullStorageButton").set("disabled", true);
                    dojo.byId("pullStorageCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullStorageButton").set("disabled", false);
                    dojo.byId("pullStorageLoaderImage").style.display = "none";
                    dojo.byId("pullStorageCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    'confirmPullTemplate': function() {
        dijit.byId("pullTemplateConfirm").show();
    },
    'cancelPullTemplate': function() {
        dijit.byId("pullTemplateConfirm").hide();
    },
    "pullTemplate": function() {

        dijit.byId("pullTemplateConfirm").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/template/pullFromCloudStack"
        });
        
        restStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullTemplateLoaderImage").style.display = "inline";
                    dijit.byId("pullTemplateButton").set("disabled", true);
                    dojo.byId("pullTemplateCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullTemplateButton").set("disabled", false);
                    dojo.byId("pullTemplateLoaderImage").style.display = "none";
                    dojo.byId("pullTemplateCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    'confirmPullVPCOffering': function() {
        dijit.byId("pullVPCOfferingConfirm").show();
    },
    'cancelPullVPCOffering': function() {
        dijit.byId("pullVPCOfferingConfirm").hide();
    },
    "pullVPCOffering": function() {

        dijit.byId("pullVPCOfferingConfirm").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/vpcOffering/pullFromCloudStack"
        });
        
        restStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullVPCOfferingLoaderImage").style.display = "inline";
                    dijit.byId("pullVPCOfferingButton").set("disabled", true);
                    dojo.byId("pullVPCOfferingCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullVPCOfferingButton").set("disabled", false);
                    dojo.byId("pullVPCOfferingLoaderImage").style.display = "none";
                    dojo.byId("pullVPCOfferingCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    'confirmPullNetworkOffering': function() {
        dijit.byId("pullNetworkOfferingConfirm").show();
    },
    'cancelPullNetworkOffering': function() {
        dijit.byId("pullNetworkOfferingConfirm").hide();
    },
    "pullNetworkOffering": function() {

        dijit.byId("pullNetworkOfferingConfirm").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer/pullFromCloudStack"
        });
        
        restStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullNetworkOfferingLoaderImage").style.display = "inline";
                    dijit.byId("pullNetworkOfferingButton").set("disabled", true);
                    dojo.byId("pullNetworkOfferingCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullNetworkOfferingButton").set("disabled", false);
                    dojo.byId("pullNetworkOfferingLoaderImage").style.display = "none";
                    dojo.byId("pullNetworkOfferingCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    'confirmPullNetwork': function() {
        dijit.byId("pullNetworkConfirm").show();
    },
    'cancelPullNetwork': function() {
        dijit.byId("pullNetworkConfirm").hide();
    },
    "pullNetwork": function() {

        dijit.byId("pullNetworkConfirm").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/network/pullFromCloudStack"
        });
        
        restStore.query().then(function(data) {
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
            target: core.getContextPath() + "/api/zone/pullFromCloudStack"
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
   'conformPullVPC': function() {
        dijit.byId("pullVPCConform").show();
    },
    'cancelPullVPC': function() {
        dijit.byId("pullVPCConform").hide();
    },
    "pullVPC": function() {

        dijit.byId("pullVPCConform").hide();

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/pullFromCloudStack"
        });
        
        restStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullVPCLoaderImage").style.display = "inline";
                    dijit.byId("pullVPCButton").set("disabled", true);
                    dojo.byId("pullVPCCheckImage").style.display = "none";
                    
                } else {
                    dijit.byId("pullVPCButton").set("disabled", false);
                    dojo.byId("pullVPCLoaderImage").style.display = "none";
                    dojo.byId("pullVPCCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    
};
window.ImportDataFromCloudstack = ImportDataFromCloudstack;