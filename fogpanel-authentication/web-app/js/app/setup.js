"use strict";
require([ 
    "dojo",
    "dijit/dijit", 
    "dojo/query",
    "dojo/store/JsonRest",
    "dijit/registry",
    "dojo/dom-construct",
    "dojo/data/ItemFileWriteStore",
    "dijit/form/Select",
    "dojox/grid/DataGrid",
    "dijit/form/RadioButton",
    "dojo/dnd/Source",
    "dijit/Editor",
    "FogPanel/WizardZoneInfo",
    "dojo/dom-class",       
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dojox/layout/ScrollPane",
    "dijit/layout/BorderContainer",
    "dijit/form/DropDownButton",
    "dijit/layout/TabContainer",
    "dijit/TooltipDialog",
    "dijit/InlineEditBox",
    "dojox/validate/regexp",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "dojox/widget/Toaster",
    "dijit/form/FilteringSelect",
    "Zone/ZoneCost",
    "dojox/widget/rotator/Slide",
    "dojox/widget/Rotator",
    "dojox/widget/rotator/Pan",
    "dojox/widget/rotator/Fade",
    "FogPanel/WizardListItem",
    "dijit/form/Button",
    "dijit/form/SimpleTextarea",
    "dijit/form/ToggleButton",     
    "dojox/validate/regexp",
    "dijit/form/ValidationTextBox",
    "dojo/NodeList-traverse",
    "dijit/form/NumberTextBox",
    "dijit/Dialog",
    "dijit/form/NumberSpinner",
    "dojo/NodeList-dom",
    "dojo/dom-class",
    "dijit/form/Form",
    "dijit/layout/TabContainer",
    "dijit/layout/ContentPane"
],function(dojo, dijit, query, JsonRest, registry, domConstruct, ItemFileWriteStore, Select,
DataGrid, RadioButton, Source, Editor, WizardZoneInfo, domClass) {   
    window.JsonRest = JsonRest;
    window.domConstruct = domConstruct;
    window.registry = registry;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.DataGrid = DataGrid;
    window.RadioButton = RadioButton;
    window.Select = Select;
    window.Source = Source;
    window.Editor = Editor;
    window.WizardZoneInfo = WizardZoneInfo; 
    window.domClass = domClass;
    controller ({ 
        name: "setup",
        module: "admin",
        filePath: "/js/app/setup.js",
        layout: {
            name: "setup_index",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "welcome": action(function() {
            FogSetupWizard.init();
            FogSetupWizard.go(0);
          }),

        "config": action(function() {
            
            FogSetupWizard.init();            
            FogWizardConfig.init();
            FogWizardConfig.populateValues();
            FogSetupWizard.go(1);            
        }),

        "zone": action(function() {
            FogSetupWizard.init();
            FogWizardZone.init();
            FogWizardZone.populateValues();
            FogSetupWizard.go(2);
        }),

        "computing": action(function() {
            FogSetupWizard.init();
            FogWizardComputingOffer.init();
            FogWizardComputingOffer.populateValues();
            FogSetupWizard.go(3);
        }),

        "disk": action(function() {
            FogSetupWizard.init();
            FogWizardDiskOffer.init();
            FogWizardDiskOffer.populateValues();
            FogSetupWizard.go(4);
        }),
       "misc": action(function() {
           
            FogSetupWizard.init();
            FogWizardMiscellaneousCost.init();
            FogWizardMiscellaneousCost.populateValues();
            FogSetupWizard.go(5);
        }),
        "end" : action(function() {
            FogSetupWizard.init();
            FogSetupWizard.go(6);
        }) 
    });
});

var FogSetupWizard = {
    _steps: 
        [
        "welcome", "config", "zone", "computing", 
        "disk","misc"
    ],
    _count:1,
    init: function() {  
        if(typeof rotator == "undefined") {
            core.ui.loadTemplate("setup_index", core.ui.getContentId());
        }
        
        var varticalMenuBar = dijit.byId("verticalMenu");
        var navigator = dijit.byId("templateNavigator");
        varticalMenuBar.disable();
        navigator.disableNavigator();
        
        domClass.add(dojo.byId("contentArea"), "diableSideMenu");
        var wizard = dijit.byId("setupWizard");
        wizard.onActive(FogSetupWizard.gotoPage);         
    }, 
    gotoPage: function(currentId) {
        var steps = ["welcome", "config", "zone", "computing", "disk","misc" ];
        dojo.query("#wizardSteps li.enabled").forEach(function(node, index, arr) {
            if(node.id == currentId) {             
                window.location.hash = "#/admin/setup/" + steps[index];
            } 
        });       
    },
    showSideMenu : function() {
      
    },
    go: function( slideNo ) {
        this._count = slideNo;       
        if (slideNo == 0) {
            dijit.byId("wizardPrevButton").setAttribute('disabled', true);
            dijit.byId("wizardNextButton").setAttribute('disabled', false);
        } else if (slideNo == 5) {           
            dijit.byId("wizardPrevButton").setAttribute('disabled', false);
            dijit.byId("wizardNextButton").setAttribute('disabled', true);
        }  else {
            dijit.byId("wizardPrevButton").setAttribute('disabled', false);
            dijit.byId("wizardNextButton").setAttribute('disabled', false);
        }
        var wizard = dijit.byId("setupWizard");
        wizard.enable(slideNo+1);         
        rotator.go(slideNo);        
    },
    next: function() {    
        FogSetupWizard.go(this._count+1);    
    },
    previous: function() {
        var wizard = dijit.byId("setupWizard");
        window.location.hash = "#/admin/setup/" + this._steps[this._count-1];
        rotator.go(this._count-1);
        wizard.disable(this._count);       
    }, 
    skip: function() {
        core.router.go("#/admin/dashboard");
    },
    closeMessageBox : function() {
        var configPageResult = new JsonRest({
            target: core.getContextPath()+"/api/config/test/"
        });
        
        configPageResult.query().then(function(data) {
            if(data == "OK") {
                dijit.byId("thankyouDialog").hide();
                core.router.go("#/admin/dashboard");
            }
        });
    }
};

var FogWizardConfig = {
    _restStore: "",
    _configPageResult:"",
    configId:[],
    init: function() {
        
        this._restStore = new JsonRest({
            target: core.getContextPath()+"/api/config/",
            idAttribute: "id",
            "class" : "com.assistanz.fogpanel.Config"
        });
    
        dojo.query("#wizardSteps li").forEach(function(node, index, arr){
            node.className = "";
            if (index == 1) {
                node.className = "active";
            }
        });
    },
    populateValues: function() {
        var widgets = dojo.byId("configWidgets");
        var pageWidgets = dijit.registry.findWidgets(widgets);
        
        var ssoUrl = dijit.byId("ssoUrl");
        var cloudStackUrl = dijit.byId("cloudStackUrl");
        var cloudApiKey = dijit.byId("cloudApi");
        var cloudSecretKey = dijit.byId("cloudSecretKey"); 
        this._restStore.query().then(function(data) {
            dojo.forEach(data, function(config) {  
                if(config.name == "single.sign.on.url") {
                    ssoUrl.setValue(config.value);
                }else if(config.name == "CLOUD_STACK_URL") {
                    cloudStackUrl.setValue(config.value);
                }else if(config.name == "CLOUD_STACK_ROOT_API_KEY") {
                   cloudApiKey.setValue(config.value);
                }  else if(config.name == "CLOUD_STACK_ROOT_SECRET_KEY") {
                    cloudSecretKey.setValue(config.value);
                }
            });
        });
        this._configPageResult = new JsonRest({
            target: core.getContextPath()+"/api/config/test/"
        });
        
        this._configPageResult.query().then(function(data) {
            if(data == "OK") {                
                dojo.byId("invalidMessage").style.display = "none"; 
                dojo.byId("wizardSteps").style.display = "block";
                
                dijit.byId("wizardPrevButton").setAttribute('disabled', false);
                dijit.byId("wizardNextButton").setAttribute('disabled', false);
                     
                dijit.byId("verifyButton").set("iconClass", "dijitCheckBoxIcon true");
                dijit.byId("verifyButton").setAttribute("disabled", "true");
                dojo.byId("cloudUrlLabel").innerHTML = cloudStackUrl.value;
                dojo.byId("cloudApiLabel").innerHTML = cloudApiKey.value;
                dojo.byId("ssoUrlLabel").innerHTML = ssoUrl.value;
                dojo.byId("cloudSecretKeyLabel").innerHTML = cloudSecretKey.value;
                dojo.query("#configWidgets .updatable").toggleClass("non_updatable", true);
                dojo.query("#configWidgets .hide_lable").toggleClass("show_lable", true);
                
                            
            } else {                
                dojo.byId("invalidMessage").style.display = "block";
                dojo.byId("wizardSteps").style.display = "none";
//                registry.byId("appToaster").setContent("Config Missing!", "error");
//                registry.byId("appToaster").show();
                dijit.byId("wizardPrevButton").setAttribute('disabled', true);
                dijit.byId("wizardNextButton").setAttribute('disabled', true);
                dijit.byId("wizardSkipButton").setAttribute('style', 'display: block');
                dijit.byId("verifyButton").set("iconClass", "dijitEditorIcon dijitEditorIconDelete");
            }
        });
    },
    closeMessageBox : function() {
        var configPageResult = new JsonRest({
            target: core.getContextPath()+"/api/config/test/"
        });
        configPageResult.query().then(function(data) {
            if(data == "OK") {
                core.router.go("#/admin/setup/zone");
            }
        })
        dijit.byId("configLoader").hide();
    },
    edit : function() {
        dojo.query("#configWidgets .updatable").removeClass("non_updatable", true);
        dojo.query("#configWidgets .hide_lable").removeClass("show_lable", true); 
        dijit.byId("verifyButton").setAttribute("disabled", false);
    },
    update: function() {
       
       var configPageResult = new JsonRest({
            target: core.getContextPath()+"/api/config/test/"
        });
        
        var restStore = new JsonRest({
            target: core.getContextPath()+"/api/config/",
            idAttribute: "id",
            "class" : "com.assistanz.fogpanel.Config"
        });
      
        var systemNode = dojo.byId("systemConfigPage");
        var systemWidget = dijit.registry.findWidgets(systemNode);
        dojo.forEach(systemWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        var ssoUrl = dijit.byId("ssoUrl");      
        var cloudStackUrl = dijit.byId("cloudStackUrl");
        var cloudApiKey = dijit.byId("cloudApi");
        var cloudSecretKey = dijit.byId("cloudSecretKey"); 
        dijit.byId("configLoader").show();
        dojo.byId("successMessage").style.display = "none";
        dojo.byId("processMessage").style.display = "block";
        dojo.byId("failMessage").style.display = "none";
        dojo.byId("messageAction").style.display = "none";
        
        restStore.add({
            url: cloudStackUrl.value,
            apiKey: cloudApiKey.value,
            secretKey: cloudSecretKey.value,
            ssoUrl: ssoUrl.value,
            "class" : "com.assistanz.fogpanel.Config"
            }).then(function (data) {                
                
                if(data == "OK") {                    
                    configPageResult.query().then(function(data) {
                        if(data == "OK") {
                            dijit.byId("configLoader").hide();
                            dojo.byId("invalidMessage").style.display = "none"; 
//                            dojo.byId("wizardSteps").style.display = "block";
//                            registry.byId("appToaster").setContent("Valid Config!", "fatal");
//                            registry.byId("appToaster").show();
//                            dijit.byId("prevButton").setAttribute('disabled', false);
//                            dijit.byId("nextButton").setAttribute('disabled', false);
//                            dojo.byId("skipButton").style.display = "block";  
                            dojo.byId("cloudUrlLabel").innerHTML = cloudStackUrl.value;
                            dojo.byId("cloudApiLabel").innerHTML = cloudApiKey.value;
                            dojo.byId("cloudSecretKeyLabel").innerHTML = cloudSecretKey.value;
                            dojo.byId("ssoUrlLabel").innerHTML = ssoUrl.value;
                            
                            dijit.byId("verifyButton").set("iconClass", "dijitCheckBoxIcon true");
                            dijit.byId("verifyButton").setAttribute("disabled", true);
                            
                            dojo.query("#configWidgets .updatable").toggleClass("non_updatable", true);
                            dojo.query("#configWidgets .hide_lable").toggleClass("show_lable", true);
                            
                            dojo.byId("successMessage").style.display = "block";
                            dojo.byId("processMessage").style.display = "none";
                            dojo.byId("failMessage").style.display = "none";
                           dojo.byId("messageAction").style.display = "block";
                           
//                           dijit.byId("verifyButton").setAttribute("disabled", true);
                            
                            
                    } else {
                            dijit.byId("configLoader").hide();
                            dojo.byId("invalidMessage").style.display = "block";
//                            dojo.byId("setupWizardSteps").style.display = "none";

//                            dijit.byId("prevButton").setAttribute('disabled', true);
//                            dijit.byId("nextButton").setAttribute('disabled', true);
//                            dojo.byId("skipButton").style.display = "none";
                            dijit.byId("verifyButton").setAttribute("disabled", false); 
//                            registry.byId("appToaster").setContent("Config Missing!", "error");
//                            registry.byId("appToaster").show();
                            dijit.byId("verifyButton").set("iconClass", "dijitEditorIcon dijitEditorIconDelete");
                            dijit.byId("verifyButton").set("label", "Reverify");
//                             cloudStackUrl.reset();
//                             cloudApiKey.reset();
//                             cloudSecretKey.reset();
//                             
                            dojo.byId("successMessage").style.display = "none";
                            dojo.byId("processMessage").style.display = "none";
                            dojo.byId("failMessage").style.display = "block";
                            dojo.byId("messageAction").style.display = "block";
                        }
                    });
               
                } else {
                    dojo.byId("invalidMessage").style.display = "block";
                    dojo.byId("wizardSteps").style.display = "none";
//                    registry.byId("appToaster").setContent("Config Missing!", "error");
//                    registry.byId("appToaster").show();
                    dijit.byId("wizardPrevButton").setAttribute('disabled', true);
                    dijit.byId("wizardNextButton").setAttribute('disabled', true);
                    dojo.byId("skipButton").style.display = "none";
                    dijit.byId("verifyButton").setAttribute("disabled", false);
                    
                    dojo.byId("successMessage").style.display = "none";
                    dojo.byId("processMessage").style.display = "none";
                    dojo.byId("failMessage").style.display = "block";
                    dojo.byId("messageAction").style.display = "block";
                }
        });
    }
};

var FogWizardZone = {
    zoneWidgetPane: "",
    _zoneRestStore: "",
    _podRestStore : "",
    _clusterRestStore : "",
    _hostRestStore : "",
    
    init : function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        
        this._podRestStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        });
        
        this._clusterRestStore = new JsonRest({
            target: core.getContextPath()+"/api/cluster/"
        });
        
        this._hostRestStore = new JsonRest({
            target: core.getContextPath()+"/api/host/"
        });
           

        dojo.query("#wizardSteps li").forEach(function(node, index, arr) {
            node.className = "";
            if (index == 2) {
                node.className = "active";
            }
        });
    },
    populateValues: function() { 
        //Check - Zone List loaded already? if yes exit from this function
        if (dojo.query("#zoneList .WizardListItem").length != 0) {
            return;
        }
             
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(zoneData) {
                var zoneListItem = new FogPanel.WizardListItem({
                    heading: zoneData.aliasName,
                    description: zoneData.aliasDescription,
                    
                    onClick: function() {
                        FogWizardZone.returnZone(this);
                    },
                    
                    onDeleteTagClick: function() {
                        FogWizardZone.deleteCurrentWidget(this, "false");
                    },
                    
                    additionalProperties : {
                        id: zoneData.id,
                        name: zoneData.name,
                        aliasName: zoneData.aliasName,
                        description : zoneData.aliasDescription,
                        referenceZoneId : zoneData.referenceZoneId
                    }
                });
                zoneListItem.placeAt("zoneItem");
                zoneListItem.startup();
                zoneListItem.disableStates();
            });
        });
                
    },
    returnZone : function(currentZone) {
        dojo.byId("currentZoneInfo").className = "active";
        dojo.byId("podListDetails").removeAttribute("class", "active");
        dojo.byId("zoneDetails").style.display = "block";
        dojo.byId("podDetails").style.display = "none";
        dojo.byId("clusterDetails").style.display = "none";
               
        dojo.query("#zoneCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#podCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#clusterListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.byId("zoneCollection").style.display = "block";
        dojo.byId("podListCollection").style.display = "none";
        
        dojo.byId("currentZoneId").value = currentZone.id;
        var zoneWidgetPane =  new FogPanel.ZoneItem ({
                    zoneName: currentZone.additionalProperties.aliasName,
                    zoneDescription: currentZone.additionalProperties.description,
                    zoneId: currentZone.additionalProperties.referenceZoneId,
                    onZoneClick: function() {
                        FogWizardZone.updateZone(this);
                    }
                });
                
                zoneWidgetPane.placeAt("zoneInfo"); 
                
       var zoneListCollection = dojo.byId("zoneList");
       var zoneListWidgets = dijit.registry.findWidgets(zoneListCollection);
       dojo.forEach(zoneListWidgets, function(el) {
           if (el.id == currentZone.id ) {
               currentZone.setSelectState(true, currentZone.id);
           } else {
               currentZone.setSelectState(false, el.id);
           }
       });  
       
    },
     updateZone: function(currentZoneWidget) {
       var status = currentZoneWidget.validateZoneName();
       if(status == true) {
           var currentZoneWidgetId = dojo.byId("currentZoneId").value;
       var currentZone = dijit.byId(currentZoneWidgetId);
       this._zoneRestStore.put ({
            aliasName : currentZoneWidget.getZoneName(),
            id: currentZoneWidget.getId(),
            aliasDescription: currentZoneWidget.getDescription(),
            "class":"com.assistanz.fogpanel.Zone"
        }).then(function(result) {
            dojo.forEach(result, function(zoneData) {
                if(zoneData.result == "OK") {
                 currentZone.additionalProperties.heading = zoneData.zoneName;
                 currentZone.additionalProperties.description = zoneData.zoneDescription; 
                 currentZone.additionalProperties.aliasName =  zoneData.zoneName;
                 currentZone.getData();
                 registry.byId("appToaster").setContent("updated Successfully!","message");
                 registry.byId("appToaster").show();
                 FogWizardZone.showPod();
             } else {
                 registry.byId("appToaster").setContent("Cannot be updated !", "error");
                 registry.byId("appToaster").show();
             }  
         });
     });
       }
   },
    updatePod :  function(currentPodWidget) {
       var currentPodId = dojo.byId("currentPodId").value;
       var currentPod = dijit.byId(currentPodId);
      this._podRestStore.put ({
            id: currentPodWidget.getId(),
            description: currentPodWidget.getDescription()           
        }).then(function(data) {
            dojo.forEach(data, function(podData) {
                if(podData.result == "OK") {
                    currentPod.additionalProperties.heading = podData.podName;
                    currentPod.additionalProperties.description = podData.podDescription; 
                    currentPod.getData();
                    registry.byId("appToaster").setContent("updated Successfully!","message");
                    registry.byId("appToaster").show();
                    FogWizardZone.showCluster();
             } else {
                    registry.byId("appToaster").setContent("Cannot be updated !", "error");
                    registry.byId("appToaster").show();
             }  
            });
        });
              
    },
    updateCluster :  function(currentClusterWidget) {
        var currentclusterId = dojo.byId("currentClusterId").value;
       var currentCluster = dijit.byId(currentclusterId);
        this._clusterRestStore.put ({
            id: currentClusterWidget.getId(),
            
            description: currentClusterWidget.getDescription(),
            "class":"com.assistanz.fogpanel.Zone"
        }).then(function(data) {
            dojo.forEach(data, function(clusterata) {
                if(clusterata.result == "OK") {
                    currentCluster.additionalProperties.heading = clusterata.clusterName;
                    currentCluster.additionalProperties.description = clusterata.clusterDescription; 
                    currentCluster.getData();
                    registry.byId("appToaster").setContent("updated Successfully!","message");
                    registry.byId("appToaster").show();
                    FogWizardZone.showHost();
             } else {
                    registry.byId("appToaster").setContent("Cannot be updated !", "error");
                    registry.byId("appToaster").show();
             }  
            });
        });
    },
    returnPod : function (currentPod) {
        dojo.byId("currentPodInfo").className = "active";
        dojo.byId("clusterListDetails").removeAttribute("class", "active");
        
        dojo.byId("zoneDetails").style.display = "block";
        dojo.byId("podDetails").style.display = "block";
        dojo.byId("clusterDetails").style.display = "none";
        
        dojo.query("#podCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("podCollection").style.display = "block";
        dojo.byId("clusterListCollection").style.display = "none";
        dojo.byId("currentPodId").value = currentPod.id;
        var zoneWidgetPane =  new FogPanel.ZoneItem ({
                    zoneName: currentPod.additionalProperties.name,
                    zoneDescription: currentPod.additionalProperties.description,
                    zoneId: currentPod.additionalProperties.podReferenceId,
                    onZoneClick: function() {
                        FogWizardZone.updatePod(this);
                    }
                });
                zoneWidgetPane.disableZoneName();
                zoneWidgetPane.placeAt("podInfo"); 
                
       var podListCollection = dojo.byId("podListCollection");
       var podListWidgets = dijit.registry.findWidgets(podListCollection);
       dojo.forEach(podListWidgets, function(el) {
           if (el.id == currentPod.id ) {
               currentPod.setSelectState(true, currentPod.id);
           } else {
               currentPod.setSelectState(false, el.id);
           }
       });  
    },
    returnCluster : function(currentCluster) {
        dojo.byId("currentClusterInfo").className = "active";
        dojo.byId("hostListDetails").removeAttribute("class", "active");
        dojo.byId("clusterDetails").style.display = "block";
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("clusterCollection").style.display = "block";
        dojo.byId("hostListCollection").style.display = "none";
        dojo.byId("currentClusterId").value = currentCluster.id;
        var zoneWidgetPane =  new FogPanel.ZoneItem ({
                    zoneName: currentCluster.additionalProperties.name,
                    zoneDescription: currentCluster.additionalProperties.description,
                    zoneId: currentCluster.additionalProperties.clusterReferenceId,
                    onZoneClick: function() {
                        FogWizardZone.updateCluster(this);
                    }
                });
                zoneWidgetPane.disableZoneName();
                zoneWidgetPane.placeAt("clusterInfo"); 
                
        var clusterListCollection = dojo.byId("clusterListCollection");
       var clusterListWidgets = dijit.registry.findWidgets(clusterListCollection);
       dojo.forEach(clusterListWidgets, function(el) {
           if (el.id == currentCluster.id ) {
               currentCluster.setSelectState(true, currentCluster.id);
           } else {
               currentCluster.setSelectState(false, el.id);
           }
       });  
             
    },
    zoneInfo :  function() {
       dojo.byId("currentZoneInfo").className = "active";
       dojo.byId("podListDetails").removeAttribute("class", "active");
       
        dojo.byId("zoneDetails").style.display = "block";
        dojo.byId("podDetails").style.display = "none";
        dojo.byId("clusterDetails").style.display = "none";
               
        dojo.query("#zoneCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#podCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#clusterListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.byId("zoneCollection").style.display = "block";
        dojo.byId("podListCollection").style.display = "none";
       
       
        var currentZoneId = dojo.byId("currentZoneId").value;
        var currentZoneWidget = dijit.byId(currentZoneId);
        
        dojo.query("#zoneList .ZoneItem").forEach(dojo.destroy);
        dojo.byId("zoneCollection").style.display = "block";
        dojo.byId("podListCollection").style.display = "none";
        
        var zoneWidgetPane =  new FogPanel.ZoneItem({
                    zoneName: currentZoneWidget.additionalProperties.aliasName,
                    zoneDescription: currentZoneWidget.additionalProperties.description,
                    zoneId: currentZoneWidget.additionalProperties.referenceZoneId,
                    onZoneClick: function() {
                        FogWizardZone.updateZone(this);
                    }
                });
                zoneWidgetPane.placeAt("zoneInfo"); 
    },
    podInfo : function() {
        dojo.byId("currentPodInfo").className = "active";
       dojo.byId("clusterListDetails").removeAttribute("class", "active");
        var currentPodId = dojo.byId("currentPodId").value;
        
        dojo.byId("zoneDetails").style.display = "block";
        dojo.byId("podDetails").style.display = "block";
        dojo.byId("clusterDetails").style.display = "none";
        
        dojo.query("#podCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
        
        var currentPodWidget = dijit.byId(currentPodId);
        dojo.query("#podCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("podCollection").style.display = "block";
        dojo.byId("clusterListCollection").style.display = "none";
        
        
        var zoneWidgetPane =  new FogPanel.ZoneItem({
                    zoneName: currentPodWidget.additionalProperties.name,
                    zoneDescription: currentPodWidget.additionalProperties.description,
                    zoneId: currentPodWidget.additionalProperties.podReferenceId,
                    onZoneClick: function() {
                        FogWizardZone.updatePod(this);
                    }
                });
                zoneWidgetPane.disableZoneName();
                zoneWidgetPane.placeAt("podInfo"); 
    },
    clusterInfo : function() {
        dojo.byId("currentClusterInfo").className = "active";
       dojo.byId("hostListDetails").removeAttribute("class", "active");
        var currentClusterId = dojo.byId("currentClusterId").value;
        
        var currentClusterWidget = dijit.byId(currentClusterId);
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("clusterCollection").style.display = "block";
        dojo.byId("hostListCollection").style.display = "none";
        
        
        var zoneWidgetPane =  new FogPanel.ZoneItem({
                    zoneName: currentClusterWidget.additionalProperties.name,
                    zoneDescription: currentClusterWidget.additionalProperties.description,
                    zoneId: currentClusterWidget.additionalProperties.clusterReferenceId,
                    onZoneClick: function() {
                        FogWizardZone.updateCluster(this);
                    }
                });
                zoneWidgetPane.disableZoneName();
                zoneWidgetPane.placeAt("clusterInfo"); 
    },
    returnHost : function(currentHostWidget) {
        currentHostWidget.unSelectItem(currentHostWidget.id);
    },
    showCluster : function() {
        
        dojo.byId("clusterListDetails").className = "active";
       dojo.byId("currentPodInfo").removeAttribute("class", "active");
       
       dojo.byId("zoneDetails").style.display = "block";
        dojo.byId("podDetails").style.display = "block";
        dojo.byId("clusterDetails").style.display = "none";
        
        dojo.query("#podCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
       
        dojo.query("#clusterListCollection .WizardListItem").forEach(dojo.destroy);
        dojo.byId("podCollection").style.display = "none";
        dojo.byId("clusterListCollection").style.display = "block";
        var currentPodId = dojo.byId("currentPodId").value;
        var currentPodWidget = dijit.byId(currentPodId);
        this._clusterRestStore.get(currentPodWidget.additionalProperties.podReferenceId).then(function(data) {
            dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
            dojo.forEach(data, function(clusterInfo) {
               var clusterListItem = new FogPanel.WizardListItem({
                    heading: clusterInfo.clusterName,
                    description: clusterInfo.clusterDescription,
                    
                    onClick: function() {
                        FogWizardZone.returnCluster(this);
                    },
                    additionalProperties : {
                        
                        name: clusterInfo.clusterName,
                        description : clusterInfo.clusterDescription,
                        clusterReferenceId : clusterInfo.clusterReferenceId
                    }
                });
                clusterListItem.placeAt("clusterListItem");
                clusterListItem.startup();
                clusterListItem.disableStates();
            });
        });
    },
     showHost : function() {                
        dojo.byId("hostListDetails").className = "active";
        dojo.byId("currentClusterInfo").removeAttribute("class", "active");
        dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("clusterCollection").style.display = "none";
        dojo.byId("hostListCollection").style.display = "block";
        var currentClusterId = dojo.byId("currentClusterId").value;
        var currentClusterWidget = dijit.byId(currentClusterId);
        this._hostRestStore.get(currentClusterWidget.additionalProperties.clusterReferenceId).then(function(data) {
            dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
            dojo.forEach(data, function(hostInfo) {
              
                    var hostListItem = new FogPanel.WizardListItem({
                    heading: hostInfo.hostName,
                    description : hostInfo.hostDescription,
                    onClick: function() {
                        FogWizardZone.returnHost(this);
                    },
                    additionalProperties : {                        
                        name: hostInfo.hostTag,                        
                        hostReferenceId : hostInfo.hostReferenceId
                    }
                    
                });
                hostListItem.placeAt("hostListItem");
                hostListItem.startup();
                hostListItem.unSelectItem();
                hostListItem.disableStates();
            
                
            });
        });
    },
    showPod : function() {
       dojo.byId("podListDetails").className = "active";
       dojo.byId("currentZoneInfo").removeAttribute("class", "active");
       
       dojo.byId("zoneDetails").style.display = "block";
        dojo.byId("podDetails").style.display = "none";
        dojo.byId("clusterDetails").style.display = "none";
               
        dojo.query("#zoneCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#podCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#clusterListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#clusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#hostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.byId("zoneCollection").style.display = "block";
        dojo.byId("podListCollection").style.display = "none";
       
       
        dojo.query("#podListCollection .WizardListItem").forEach(dojo.destroy);
        dojo.byId("zoneCollection").style.display = "none";
        dojo.byId("podListCollection").style.display = "block";
        var currentZoneId = dojo.byId("currentZoneId").value;
        var currentZoneWidget = dijit.byId(currentZoneId);
        this._podRestStore.get(currentZoneWidget.additionalProperties.referenceZoneId).then(function(data) {
            dojo.query("#clusterListCollection .WizardListItem").forEach(dojo.destroy);
            dojo.forEach(data, function(zoneInfo) {
               var podListItem = new FogPanel.WizardListItem({
                    heading: zoneInfo.podName,
                    description: zoneInfo.podDescription,
                    
                    onClick: function() {
                        FogWizardZone.returnPod(this);
                    },
                    additionalProperties : {
                        
                        name: zoneInfo.podName,
                        podReferenceId : zoneInfo.podReferenceId,
                        description: zoneInfo.podDescription
                    }
                });
                podListItem.placeAt("podList");
                podListItem.startup();
                podListItem.disableStates();
            });
        });
    }  
   
};

var FogWizardComputingOffer = {
    name : "",
    id: "",
    _zoneWidget:"",
    _podWidget:"",
    _clusterWidget:"",
    description : "",
    cpuNumber : "",
    cpuSpeed : "",
    cpuMemory : "",
    offerHa : "",
    isSystem : "",
    networkRate : "",
    hostTag : "",
    storageTags : "",
    storageType: "",
    limitCpuUse: "",
    serviceWidget: "",
    _podList:"",
    computingOfferNode: "",
    computingOfferWidget:"",
    _computingOfferRestStore: "",
    computingZoneWidgets:"", 
    _zoneRestStore:"",
    _podRestStore:"",
    _clusterRestStore:"",
    _storageTagRestStore:"",
    _hostTagRestStore:"",
    _storageTagWidget: "",
    init : function() {
        this._computingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/"
        });
        
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        
        this._podRestStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        });
        
        this._clusterRestStore = new JsonRest({
            target: core.getContextPath()+"/api/cluster/"
        });
        
        this._storageTagRestStore = new JsonRest({
            target: core.getContextPath()+"/api/storagePool/"
        });
        
        this._hostTagRestStore = new JsonRest({
            target: core.getContextPath()+"/api/host/"
        });
        
        
    },
    populateValues: function() {   
        
        if(dijit.byId("computingOfferZoneListItem")) {
            dijit.byId("computingOfferZoneListItem").destroyRecursive();
        }

        dojo.query("#serviceListItems .WizardListItem").forEach(dojo.destroy);
        dojo.query("#serviceListItems .WizardZoneInfo").forEach(dojo.destroy);
        var clusterRestStore = this._clusterRestStore;
        
        var hostTagRestStore = this._hostTagRestStore;
                     
        FogWizardZone.init();
       
        var podOptions = {
             identifier: 'id',
             label: 'name',
             items: [{id: "option", name: "Select Pod"}]
         };

         var clusterOptions = {
             identifier: 'id',
             label: 'name',
             items: [{id: "option", name: "Select Cluster"}]
         };

         var storageTagOptions = {
             identifier: 'id',
             label: 'name',
             items: [{id: "option", name: "Storage Tag"}]
         };
                    
        var hostTagOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Host Tag"}]
        };
                                              
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
        var hostTagList = new ItemFileWriteStore({data: hostTagOptions});

        if(dijit.byId("computingOfferStorageTag") && dijit.byId("computingOfferHostTag") && dijit.byId("computingOfferClusterList") && dijit.byId("computingOfferPodList"))  {
            dijit.byId("computingOfferStorageTag").destroyRecursive();
            dijit.byId("computingOfferHostTag").destroyRecursive();
            dijit.byId("computingOfferClusterList").destroyRecursive();
            dijit.byId("computingOfferPodList").destroyRecursive();
        }

        this._storageTagWidget = new Select({
           id: "computingOfferStorageTag",
           store: storageTagList
           
       }).placeAt("computingOfferStorageTagList");
       this._storageTagWidget.startup();
                   
      this._hostTagWidge = new Select({
          id:"computingOfferHostTag",
         store: hostTagList,
         onChange: function() {
            
            if(this.value != "option") {
                var hostTagCapacityRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/host/capacity/"
                });
                hostTagCapacityRestStore.get(this.value).then(function(hostListItems) {
                    dojo.forEach(hostListItems,function(currentHost) { 
                        dijit.byId("serviceCpuNumber").attr("value", 1);
                        dijit.byId("serviceCpuNumber").attr("constraints", {max: currentHost.cpuNumberTotal, min: 1, pattern:'#'});
                        dijit.byId("serviceCpuSpeed").attr("constraints", {max: currentHost.coreCpuSpeedTotal, min: 128, pattern:'#'});
                        dijit.byId("serviceMemory").attr("constraints", {max: currentHost.memoryTotal, min: 512, pattern:'#'});
                    });
                }); 
            }
            
         }
      }).placeAt("computingOfferHostTagList"); 
      this._hostTagWidge.startup(); 
      
      this._clusterWidget = new Select({
        id:"computingOfferClusterList",
        store: clusterList,
        onChange: function() {
            dojo.byId("computingClusterName").innerHTML = this.get("displayedValue");
            dojo.query("#serviceListItems .WizardListItem").forEach(dojo.destroy);
            dojo.query("#serviceListItems .WizardZoneInfo").forEach(dojo.destroy);
            dojo.byId("wizardComputViewAllTag").style.display = "block";
            dijit.byId("serviceCpuNumber").attr("value", 1);
            dijit.byId("serviceCpuNumber").attr("constraints", {max:100, min: 1, pattern:'#'});
            dijit.byId("serviceCpuSpeed").attr("constraints", {max: 20000, min: 128, pattern:'#'});
            dijit.byId("serviceMemory").attr("constraints", {max: 1024000, min: 512, pattern:'#'});
            
            var computingOfferRestStore = new JsonRest({
                   target: core.getContextPath()+"/api/computingOffer/"
             });
                
            var hostTagWidget = dijit.byId("computingOfferHostTag");
            var storageTagWidget = dijit.byId("computingOfferStorageTag");
            var hostOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            };
            
            var storageTagOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            };
            
            var currentHostTagList = new ItemFileWriteStore({data: hostOptions});
            var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
            if(this.value == "option") {
                currentHostTagList.newItem({id: "option", name: "Host Tag"});
                hostTagWidget.setStore(currentHostTagList);
                storageTagList.newItem({id: "option", name: "Storage Tag"});
                storageTagWidget.setStore(storageTagList);
                dojo.byId("wizardComputViewAllTag").style.display = "none";
                dojo.query("#serviceListItems").removeClass("show_lable", true);                  
            } else {
                var tableData = dojo.byId("listItemTable");
                dojo.query("#serviceListItems").toggleClass("show_lable", true);
                computingOfferRestStore.query({clusterReferenceId: this.value}).then(function(data){
                 
                 dojo.forEach(data, function(computingOfferData) { 
                     
                     var computingOfferListItem = new FogPanel.WizardListItem({
                         heading: computingOfferData.name,
                         description: computingOfferData.description,
                         onClick: function() {
                             FogWizardComputingOffer.returnComputingOffer(this);
                         },
                         onDeleteTagClick: function() {
                             FogWizardComputingOffer.deleteCurrentWidget(this, "false");
                         },
                         
                         additionalProperties : {
                            id: computingOfferData.id,
                            serviceDescription: computingOfferData.description,
                            serviceName:computingOfferData.name,
                            baseOs: computingOfferData.baseOs,
                            cpuNumber: computingOfferData.cpuNumber,
                            bandWidth: computingOfferData.bandWidth,
                            cpuSpeed: computingOfferData.coreCpuSpeed, 
                            cpuMemory: computingOfferData.memory,
                            networkRate: computingOfferData.networkRate, 
                            hostTag: computingOfferData.hostTags,
                            storageTags: computingOfferData.tags,
                            offerHa: computingOfferData.offerHA, 
                            isSystem: computingOfferData.isSystem,
                            storageType: computingOfferData.storageType,
                            cpuCap: computingOfferData.limitCpuUse,
                            zoneCosts: [],
                            heading: computingOfferData.name,
                            description: computingOfferData.description,
                            widgetId: computingOfferData.id
                        }
                        
                    });                 
                     
                    computingOfferListItem.placeAt("serviceList");
                   
                });
               
              
            });
            }
            
            hostTagRestStore.get(this.value).then(function(hostListItems) {
                    dojo.forEach(hostListItems, function(currentHostTag) {                        
                        if(currentHostTag.hostTag == null || currentHostTag.hostTag == "null"){
                            currentHostTagList.newItem({id: "option",  name: "No Tags"});   
                             hostTagWidget.setStore(currentHostTagList);
                         } else {
                             currentHostTagList.newItem({id: currentHostTag.hostReferenceId, name: currentHostTag.hostTag});
                         }
                        
                    });
                    hostTagWidget.setStore(currentHostTagList);
            });
            var storageTagRestData = new JsonRest({
                target: core.getContextPath()+"/api/storagePool"
            });
            var storageType = dijit.byId("serviceStorageType").attr('displayedValue');
            
            storageTagRestData.query({
                clusterReferenceId: this.value,
                storageType: storageType
            }).then(function(data) {
                if(data.length != 0) {
                   dojo.forEach(data, function(currentStorageTag) {
                       if(currentStorageTag.storageTag == null || currentStorageTag.storageTag == "null"){
                            storageTagList.newItem({id: "option",  name: "No Tags"});   
                            storageTagWidget.setStore(storageTagList);
                         } else {
                            storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId,  name: currentStorageTag.storageTag});
                         }                         
                    }); 
                    
                } else {
                    storageTagList.newItem({id: "option",  name: "No Tags"});
                }
                storageTagWidget.setStore(storageTagList);
            });
        }
    }).placeAt("computingOfferCluster");  
    this._podWidget = new Select({
        id:"computingOfferPodList",
        store: podList,
        onChange: function() {
            
            dojo.byId("computingPodName").innerHTML = this.get("displayedValue");
            var clusterWidget = dijit.byId("computingOfferClusterList");
            var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            };
            
            var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
             if(this.value == "option") {
                     currentClusterList.newItem({id: "option", name: "Select Cluster"});
                     clusterWidget.setStore(currentClusterList);
            }
            clusterRestStore.get(this.value).then(function(clusterListItems) {
                
                dojo.forEach(clusterListItems,function(currentcluster) {
                    
                    currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                });
                clusterWidget.setStore(currentClusterList);
            });
        }
    }).placeAt("computingOfferPod"); 
    
    var zoneOptions = { 
        identifier: 'id',
        label: 'name',
        items: [{id: "option", name: "Select Zone"}]
    };
    
    var zoneList = new ItemFileWriteStore({data: zoneOptions});
//    if(this.value == "option") {
//        
//    }
    this._zoneRestStore.query().then(function(data) {
        dojo.forEach(data, function(el) {
            zoneList.newItem({id: el.referenceZoneId, referenceId: el.referenceZoneId, name: el.aliasName});
        });
    });                        
    this._zoneWidget = new Select({
        store: zoneList,
        id:"computingOfferZoneListItem",
        onChange: function() {
            FogWizardComputingOffer.viewSelectedZone(this);   
        }
    }).placeAt("computingOfferZoneList"); 
    this._zoneWidget.startup();
},
viewAllZoneInfo : function() {
        dojo.query("#serviceListItems .WizardListItem").forEach(dojo.destroy);
        dojo.query("#serviceListItems .WizardZoneInfo").forEach(dojo.destroy);
        
        
        var podRestStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        });
        
        var clusterRestStore = new JsonRest({
            target: core.getContextPath()+"/api/cluster/"
        });
        
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/"
        });
        
        this._zoneRestStore.query().then(function(zoneData) {
            dojo.forEach(zoneData, function(zoneInfo) {
                podRestStore.get(zoneInfo.referenceZoneId).then(function(podData) {
                    if(podData) {
                        dojo.query("#serviceListItems").toggleClass("show_lable", true);
                    } else {
                        dojo.query("#serviceListItems").removeClass("show_lable", true);
                    }
                    dojo.forEach(podData, function(podInfo) {
                        clusterRestStore.get(podInfo.podReferenceId).then(function(clusterData) {
                            dojo.forEach(clusterData, function(clusterInfo) {                           
                                var count =0;
                                computingOfferRestStore.query({clusterReferenceId: clusterInfo.clusterReferenceId, referenceZoneId: zoneInfo.referenceZoneId, podReferenceId: podInfo.podReferenceId}).
                                    then(function(data){
                                        dojo.forEach(data, function(computingOfferData) {
                                            if(count == 0) {
                                                var computingZoneInfo = new FogPanel.WizardZoneInfo({
                                                zoneName: computingOfferData.zone.name,
                                                podName: computingOfferData.pod.name,
                                                clusterName:computingOfferData.cluster.name});
                                                computingZoneInfo.placeAt("serviceList");
                                                computingZoneInfo.startup();
                                                count++;
                                            }
                                            var computingOfferListItem = new FogPanel.WizardListItem({
                                                heading: computingOfferData.name,
                                                description: computingOfferData.description,

                                                onClick: function() {
                                                    FogWizardComputingOffer.returnComputingOffer(this);
                                                },

                                                onDeleteTagClick: function() {
                                                    FogWizardComputingOffer.deleteCurrentWidget(this, "false");
                                                },

                                                additionalProperties : {
                                                    id: computingOfferData.id,
                                                    serviceDescription: computingOfferData.description,
                                                    serviceName:computingOfferData.name,
                                                    cpuNumber: computingOfferData.cpuNumber,
                                                    cpuSpeed: computingOfferData.coreCpuSpeed, 
                                                    cpuMemory: computingOfferData.memory,
                                                    baseOs: computingOfferData.baseOs,
                                                    bandWidth: computingOfferData.bandWidth,
                                                    networkRate: computingOfferData.networkRate, 
                                                    hostTag: computingOfferData.hostTags,
                                                    storageTags: computingOfferData.tags,
                                                    offerHa: computingOfferData.offerHA, 
                                                    isSystem: computingOfferData.isSystem,
                                                    storageType: computingOfferData.storageType,
                                                    cpuCap: computingOfferData.limitCpuUse,
                                                    zoneCosts: [],
                                                    heading: computingOfferData.name,
                                                    description: computingOfferData.description,
                                                    widgetId: computingOfferData.id
                                                }
                                            });
                                            computingOfferListItem.placeAt("serviceList");
                                            computingOfferListItem.startup();
                                        }); 
                                    });
                            });
                        });
                    });
                }); 
            });
        });
    },
    viewSelectedZone : function(currentZone) {        
        
        dojo.byId("computingZoneName").innerHTML = currentZone.get("displayedValue");
        var podWidget = dijit.byId("computingOfferPodList");
        var currentZoneCost;
       
        if(dijit.byId("computingOfferCurrentZone")) {
            dijit.byId("computingOfferCurrentZone").destroyRecursive();
        }
        
        this._zoneRestStore.get(currentZone.value).then(function(selectedZoneInfo) {
            
             currentZoneCost  = new Zone.ZoneCost({
                    id:"computingOfferCurrentZone",
                    zoneName: selectedZoneInfo.aliasName,
                    zoneIdNode: selectedZoneInfo.id     
                });
                currentZoneCost.showStopageCost();
                currentZoneCost.placeAt("currentZoneCost"); 
                
        });
              
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
    
        var currentPodList = new ItemFileWriteStore({data: podOptions});
        if(currentZone.value == "option") {
            currentPodList.newItem({id: "option", name: "Select Pod"});
            podWidget.setStore(currentPodList);
            dijit.byId("serviceAddButton").set("disabled", true);     
            dijit.byId("serviceCancelButton").set("disabled", true);     
            
            
        } else {
            dijit.byId("serviceAddButton").set("disabled", false);
        }
        
        this._podRestStore.get(currentZone.value).then(function(podListItems) {
            dojo.forEach(podListItems,function(currentPod) {              
               
               currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
             });
            podWidget.setStore(currentPodList);
        });
        
    },
    hideOfferHA : function(currentValue) {
               
        if(currentValue.value == "Local"){
           dojo.byId("computingOfferHA").style.display = "none";
        } else {
            dojo.byId("computingOfferHA").style.display = "block";
        }
        var storageTagRestData = new JsonRest({
            target: core.getContextPath()+"/api/storagePool"
        });

        var storageType = dijit.byId("serviceStorageType").attr('displayedValue');
        var storageTagOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
        var clusterWidget  = dijit.byId("computingOfferClusterList");
        var storageTagWidget = dijit.byId("computingOfferStorageTag");
        storageTagRestData.query({
            clusterReferenceId: clusterWidget.value,
            storageType: storageType
        }).then(function(data) {
            if(data.length != 0) {
                dojo.forEach(data, function(currentStorageTag) {
                    if(currentStorageTag.storageTag == null || currentStorageTag.storageTag == "null") {
                        storageTagList.newItem({id: "option",  name: "No Tag"});
                    } else {
                        storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId,  name: currentStorageTag.storageTag});
                    }
                    
                });
            } else {
                storageTagList.newItem({id: "option",  name: "No Tag"});
            }
            storageTagWidget.setStore(storageTagList);
        });
    },
    deleteCurrentWidget: function(currentServiceWidget, forced) {
        
        var status = forced;
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/delete"
        });
        dojo.byId("currentComputingOfferId").value = currentServiceWidget.id; 
        computingOfferRestStore.add({
            computingOfferId: currentServiceWidget.additionalProperties.id, 
            forced : status
        }).then(function(result) {
            if(result == "OK") {                
                currentServiceWidget.deleteWidget();
                dijit.byId("serviceCancelButton").set("disabled", true);
                dojo.query("#wizardComputingOfferPage .updatable").removeClass("non_updatable");
                dojo.query("#wizardComputingOfferPage .hide_lable").removeClass("show_lable"); 
                dijit.byId('serviceAddButton').set('style', { display: 'block', "float": "left" });  
                dijit.byId('serviceUpdateButton').set('style', { display: 'none' });
                var serviceListCollection = dojo.byId ("serviceListItems");
                var serviceListWidgets = dijit.registry.findWidgets (serviceListCollection);
                dojo.forEach (serviceListWidgets, function(el) {
                    if (el.id == currentServiceWidget.id ) {
                        currentServiceWidget.setSelectState(true, currentServiceWidget.id);
                    } else {
                        el.setSelectState(false, el.id);
                    }
                });  
                var zoneNode = dojo.byId("currentComputingZoneInfo");
                var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                dojo.forEach(zoneWidgets, function(el) {
                    el.clearWidgets();
                }); 
                dijit.byId("computingOfferContentForm").reset();
//                dijit.byId("serviceDescription").set("value", "");
                registry.byId("appToaster").setContent("Deleted Successfully!", "message");
                registry.byId("appToaster").show();
                dijit.byId("computingOfferContentForm").reset();
                dojo.byId("computingZoneName").innerHTML ="";
                dojo.byId("computingPodName").innerHTML = "";
                dojo.byId("computingClusterName").innerHTML = "";
            } else {
                var errorMessage;
                dojo.forEach(result, function(el) {
                    
                    errorMessage = el.cause.localizedMessage;
                });
                dijit.byId("deleteComputingOfferDialog").show();
                registry.byId("appToaster").setContent(errorMessage, "error");
                registry.byId("appToaster").show();
            }
        });
        
    },
    conformDelete: function() {
        var currentServiceWidgetId = dojo.byId("currentComputingOfferId").value;
        var currentServiceWidget = dijit.byId(currentServiceWidgetId);
        FogWizardComputingOffer.deleteCurrentWidget(currentServiceWidget, "true");
        dijit.byId("deleteComputingOfferDialog").hide();
    },
    closeDeleteDialog: function() {
        dijit.byId("deleteComputingOfferDialog").hide();
    },
    authetication : function() {
        var status = true;
        var pageNode = dojo.byId("wizardComputingOfferPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
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
        }
        
        var zoneNode = dojo.byId("currentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
        dojo.forEach(zoneWidgets, function(el) {
                       
            status = el.showErrorMsg();
        });
        
        return status; 
    },
    add: function() {
        var status = FogWizardComputingOffer.authetication();
         
        if(status == true) {
            dijit.byId("serviceAddButton").set("disabled", true);
            
        var computingZoneCosts =[];
        var podWidget = dijit.byId("computingOfferPodList");
        var clusterWidget = dijit.byId("computingOfferClusterList");
        
        var name = dijit.byId ("serviceName");
       
        var editValue = dijit.byId("serviceDescription").value.toString();
        var description = editValue.replace("\n"," ");
        
        var cpuNumber = dijit.byId ("serviceCpuNumber");
        var cpuSpeed = dijit.byId ("serviceCpuSpeed");
        
        var cpuMemory = dijit.byId ("serviceMemory");
        var networkRate = dijit.byId ("serviceNetworkRate");
       
        var cpuCap = dijit.byId("serviceCpuCap");
        var bandWidth = dijit.byId ("computingOfferBandwidthCost").value;
        
        var offerHa = dijit.byId ("serviceOfferHa");
        var storageType = dijit.byId("serviceStorageType");
        var baseOs = dijit.byId("computingOfferBaseOs").attr('displayedValue');
        var storageTag;
        var hostTag;
        
        if(dijit.byId("computingOfferStorageTag").value == "option") {
            storageTag = "";
        } else {
             storageTag = dijit.byId("computingOfferStorageTag").attr('displayedValue');
        }
        
        if(dijit.byId ("computingOfferHostTag").value == "option") {
            hostTag = ""
        } else {
            hostTag = dijit.byId ("computingOfferHostTag").attr('displayedValue');
        }
                    
        var zoneNode = dojo.byId("currentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
        dojo.forEach(zoneWidgets, function(el) {
            
            computingZoneCosts.push({
                zoneId: el.getZoneId(), 
                cost: el.getZoneCost(), 
                setupCost: el.getSetupCost(),
                minimumCost: el.getMinCost()
            });         
        });
        for(var index = 0; index < computingZoneCosts.length-zoneWidgets.length; index++) {
            computingZoneCosts.splice(index, zoneWidgets.length);
        } 
       
            this._computingOfferRestStore.add({
            referenceZoneId: this._zoneWidget.value,
            podReferenceId: podWidget.value,
            clusterReferenceId : clusterWidget.value,
            name: name.value,
            description: description,
            cpuNumber: cpuNumber.value,
            cpuSpeed: cpuSpeed.value, 
            memory: cpuMemory.value,
            networkRate: networkRate.value, 
            cpuCap: cpuCap.checked,
            bandWidth: bandWidth,
            offerHa: offerHa.checked, 
            storageType: storageType.value,
            storageTag: storageTag,
            hostTag: hostTag,
            baseOs: baseOs,
            zoneCosts: computingZoneCosts
        }).then(function(data) {
            dijit.byId("serviceAddButton").set("disabled", false);  
            if(data[0].result == "OK") {        
                
                dojo.forEach(data, function(computingOfferData){
                    var computingOfferListItem = new FogPanel.WizardListItem({
                        heading: computingOfferData.computeOffer.name,
                        description: computingOfferData.computeOffer.description,
                        onClick: function() {
                            FogWizardComputingOffer.returnComputingOffer(this);
                        },
                        onDeleteTagClick: function() {
                            FogWizardComputingOffer.deleteCurrentWidget(this, "false");
                        },

                        additionalProperties : {
                            id: computingOfferData.computeOffer.id,
                            serviceDescription: computingOfferData.computeOffer.description,
                            serviceName:computingOfferData.computeOffer.name,
                            baseOs: computingOfferData.computeOffer.baseOs,
                            cpuNumber: computingOfferData.computeOffer.cpuNumber,
                            cpuSpeed: computingOfferData.computeOffer.coreCpuSpeed, 

                            cpuMemory: computingOfferData.computeOffer.memory,
                            networkRate: computingOfferData.computeOffer.networkRate, 

                            hostTag: computingOfferData.computeOffer.hostTags,
                            storageTags: computingOfferData.computeOffer.tags,

                            offerHa: computingOfferData.computeOffer.offerHA, 
                            bandwidth: computingOfferData.computeOffer.bandWidth,

                            storageType: computingOfferData.computeOffer.storageType,
                            cpuCap: computingOfferData.computeOffer.limitCpuUse,

                           zoneCosts: computingOfferData.computeOffer.computingOfferZoneCosts,
                            heading: computingOfferData.computeOffer.name,
                            description: computingOfferData.computeOffer.description,
                            widgetId: computingOfferData.computeOffer.id,
                            urlPath: core.getContextPath()+"/api/computingOffer/"
                        }
                    });
                    computingOfferListItem.placeAt("serviceList");
                    computingOfferListItem.startup();
                }); 
           
                var zoneNode = dojo.byId("currentComputingZoneInfo");
                var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                dojo.forEach(zoneWidgets, function(el) {
                    el.clearWidgets();
                });
                registry.byId("appToaster").setContent("Added Successfully!", "message");
                registry.byId("appToaster").show();
                dijit.byId("computingOfferContentForm").reset();
//                dijit.byId("computingOfferZoneForm").reset();
                dijit.byId("serviceDescription").set("value", "");
//                dijit.byId("serviceAddButton").set("disabled", false);  
            } else {
                registry.byId("appToaster").setContent("Cannot be Added!", "error");
                registry.byId("appToaster").show();
//                dijit.byId("serviceAddButton").set("disabled", false);  
            }
           
        });     
        
        } 
        
        
    },
    update: function() {
        var status = FogWizardComputingOffer.authetication();
        if(status == true) {
            var currentServiceWidgetId = dojo.byId("currentComputingOfferId").value;
            var currentServiceWidget = dijit.byId(currentServiceWidgetId);
            
            dijit.byId("serviceUpdateButton").set("disabled", true);
            var name = dijit.byId ("serviceName");
            var editValue = dijit.byId("serviceDescription").value.toString();
            var description = editValue.replace("\n"," ");             

            dojo.byId("computingZoneName").innerHTML ="";
            dojo.byId("computingPodName").innerHTML = "";
            dojo.byId("computingClusterName").innerHTML = "";

            currentServiceWidget.additionalProperties.heading = name.value;
            currentServiceWidget.additionalProperties.description = description; 

            var zoneNode = dojo.byId("currentComputingZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);

            dojo.forEach(zoneWidgets, function(el) {
                currentServiceWidget.additionalProperties.zoneCosts.push ({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCost(),
                    setupCost: el.getSetupCost(),
                    minimumCost: el.getMinCost()
                });

            });               
            for(var index = 0; index < currentServiceWidget.additionalProperties.zoneCosts.length-zoneWidgets.length; index++) {
                currentServiceWidget.additionalProperties.zoneCosts.splice(index, zoneWidgets.length);
            }

                this._computingOfferRestStore.put({
                id: currentServiceWidget.additionalProperties.id,
                name: name.value,
                description: description,
                zoneCosts: currentServiceWidget.additionalProperties.zoneCosts,
                "class":"com.assistanz.fogpanel.ComputingOffer"
            }).then(function(result) {
                dijit.byId("serviceUpdateButton").set("disabled", false);
                if(result == "OK") {
                    currentServiceWidget.getData();
                    dojo.query("#wizardComputingOfferPage .updatable").removeClass("non_updatable");
                    dojo.query("#wizardComputingOfferPage .hide_lable").removeClass("show_lable");
                    dijit.byId('serviceAddButton').set('style', { display: 'block', "float": "left" });  
                    dijit.byId('serviceUpdateButton').set('style', { display: 'none' });
                    currentServiceWidget.setSelectState(false, currentServiceWidget.id);

                    var zoneNode = dojo.byId("currentComputingZoneInfo");
                    var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                    dojo.forEach(zoneWidgets, function(el) {
                        el.clearWidgets();
                    });    

                    registry.byId("appToaster").setContent("updated Successfully!","message");
                    registry.byId("appToaster").show();
                    dijit.byId("computingOfferContentForm").reset();
                    dijit.byId("serviceDescription").set("value", "");
                    dijit.byId("serviceCancelButton").set("disabled", true);
                } else {
                     registry.byId("appToaster").setContent("Cannot be updated !", "error");
                     registry.byId("appToaster").show();
    //                 dijit.byId("serviceCancelButton").set("disabled", true);
                 }  
            });
            }    
        
    },
    cancel: function() {
        
        dijit.byId("serviceName").reset();
        var currentServiceWidgetId = dojo.byId("currentComputingOfferId").value;
        var currentServiceWidget = dijit.byId(currentServiceWidgetId);
        
//        dojo.query("#serviceListItems .WizardListItem").forEach(dojo.destroy);
//        dojo.query("#serviceListItems .WizardZoneInfo").forEach(dojo.destroy);
//        dojo.byId("wizardComputViewAllTag").style.display = "none"
        dojo.byId("computingZoneName").innerHTML ="";
        dojo.byId("computingPodName").innerHTML = "";
        dojo.byId("computingClusterName").innerHTML = "";
        dijit.byId("serviceDescription").set("value", "");
        dojo.query("#wizardComputingOfferPage .updatable").removeClass("non_updatable");
        dojo.query("#wizardComputingOfferPage .hide_lable").removeClass("show_lable");
        dijit.byId('serviceAddButton').set('style', { display: 'block', "float": "left" });  
        dijit.byId('serviceUpdateButton').set('style', { display: 'none' });
        
        var zoneNode = dojo.byId("currentComputingZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);
            dojo.forEach(zoneWidgets, function(el) {
                 el.clearWidgets();
            }); 
        currentServiceWidget.setSelectState(false, currentServiceWidget.id);
        dijit.byId("computingOfferContentForm").reset();
        dijit.byId("serviceCancelButton").set("disabled", true);
        
//        dijit.byId("computingOfferZoneForm").reset();
        
    },
    returnComputingOffer: function(currentWidget) {        

        var zoneNode = dojo.byId("currentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
            dojo.forEach(zoneWidgets, function(el) {
                 el.clearWidgets();
        }); 
        dijit.byId("serviceCancelButton").set("disabled", false);
        var currentServiceWidget  = currentWidget;
       
        dojo.byId("currentComputingOfferId").value = currentWidget.id;   
        this._computingOfferRestStore.get(currentWidget.additionalProperties.id).then(function(computingOfferData) {
                      
           dojo.byId("computingZoneNameLabel").innerHTML = computingOfferData.zone.aliasName;
           dojo.byId("computingPodNameLabel").innerHTML = computingOfferData.pod.name;
           dojo.byId("computingClusterNameLabel").innerHTML = computingOfferData.cluster.name;
           
           dojo.byId("computingZoneName").innerHTML = computingOfferData.zone.aliasName;
            dojo.byId("computingPodName").innerHTML = computingOfferData.pod.name;
            dojo.byId("computingClusterName").innerHTML = computingOfferData.cluster.name;
            
            var computingOfferZoneInfo = dojo.byId("currentComputingZoneInfo");
            var computingOfferZonewidgets = dijit.registry.findWidgets(computingOfferZoneInfo);
            dojo.forEach(computingOfferData.computingOfferZoneCosts, function(zoneData){
                dojo.forEach(computingOfferZonewidgets, function(el) {                    
                    if(zoneData.zone.id == el.getZoneId()) {
                        el.zoneCost = (zoneData.cost*720).toFixed(2);
                        el.setupCost = zoneData.setupCost;
                        el.minCost = zoneData.minimumCost;
                        el.setMinCost();
                        el.setCost();
                        el.setSetupCost();
                    }   
                });
            });

        if(computingOfferData.bandWidth == "" ||
            computingOfferData.bandWidth == null || computingOfferData.bandWidth == "null") {
            dojo.byId("serviceBwLabel").innerHTML = "......";
        } else {
            dojo.byId("serviceBwLabel").innerHTML = computingOfferData.bandWidth;
        }
        
        
        if (computingOfferData.tags == null || computingOfferData.tags == "" || computingOfferData.tags == " " || computingOfferData.tags == "null") {
            dojo.byId ("serviceStorageTagLabel").innerHTML = "No Tag";
        } else {         
            dojo.byId ("serviceStorageTagLabel").innerHTML = computingOfferData.tags;
        }
        if(computingOfferData.hostTags == null || computingOfferData.hostTags == "" || computingOfferData.hostTags == "null" || computingOfferData.hostTags == " ") {
              dojo.byId("serviceHostTagLabel").innerHTML = "No Tag";
        } else {
             dojo.byId("serviceHostTagLabel").innerHTML = computingOfferData.hostTags;
        }
        
        });
            
        var name = dijit.byId ("serviceName");
        var description = dijit.byId ("serviceDescription"); 
        
        name.setValue(currentServiceWidget.additionalProperties.heading);
        description.setValue(currentServiceWidget.additionalProperties.description);
      
        
        dojo.query("#wizardComputingOfferPage .updatable").toggleClass("non_updatable", true);
        dojo.query("#wizardComputingOfferPage .hide_lable").toggleClass("show_lable", true);
        dojo.byId ("serviceCpuNumberLabel").innerHTML = currentServiceWidget.additionalProperties.cpuNumber+ " Core";
        dojo.byId ("serviceCpuSpeedLabel").innerHTML = currentServiceWidget.additionalProperties.cpuSpeed / 1000 + " GHz";
        dojo.byId ("serviceMemoryLabel").innerHTML = Math.round(( currentServiceWidget.additionalProperties.cpuMemory / 1024)*100)/100 + " GB";
        
        if(currentServiceWidget.additionalProperties.networkRate == "" ||
        currentServiceWidget.additionalProperties.networkRate == null || currentServiceWidget.additionalProperties.networkRate == "null") {
            dojo.byId("serviceNetworkRateLabel").innerHTML = "......";
        } else {
            dojo.byId("serviceNetworkRateLabel").innerHTML =
               currentServiceWidget.additionalProperties.networkRate + "MB/s";
        }
        
        if (currentServiceWidget.additionalProperties.offerHa == "true" || currentServiceWidget.additionalProperties.offerHa == true) {
             dojo.byId("serviceofferHaLabel").innerHTML = "yes";
        } else {
             dojo.byId("serviceofferHaLabel").innerHTML = "no";
        }
        if(currentServiceWidget.additionalProperties.cpuCap == true ) {
              dojo.byId("serviceCpuCapLabel").innerHTML = "yes";
        } else {
             dojo.byId("serviceCpuCapLabel").innerHTML = "no";
        }
       
        if(currentServiceWidget.additionalProperties.networkRate == "" ||
            currentServiceWidget.additionalProperties.networkRate == null || currentServiceWidget.additionalProperties.networkRate == "null") {
            dojo.byId("serviceNetworkRateLabel").innerHTML = "......";
        } else {
            dojo.byId("serviceNetworkRateLabel").innerHTML =
            currentServiceWidget.additionalProperties.networkRate + "MB/s";
        }
     
       if(currentWidget.additionalProperties.baseOs == "" ||
        currentWidget.additionalProperties.baseOs == null || currentWidget.additionalProperties.baseOs == "null") {
            dojo.byId("serviceBaseOsLabel").innerHTML = "......";
        } else {
            dojo.byId("serviceBaseOsLabel").innerHTML = currentWidget.additionalProperties.baseOs;
        }
        
        var storageValue = dijit.byId('serviceStorageType').attr('displayedValue');
       
        if (storageValue == "") {
             dojo.byId("serviceStorageTypeLabel").innerHTML = "......";
        } else {
             dojo.byId("serviceStorageTypeLabel").innerHTML = storageValue;
        } 
        if (currentServiceWidget.additionalProperties.cpuCap == true ) {
             dojo.byId("serviceCpuCapLabel").innerHTML = "yes";
        } else {
             dojo.byId("serviceCpuCapLabel").innerHTML = "no";
        }

        var serviceListCollection = dojo.query("#serviceList .WizardListItem");
        
        dojo.forEach(serviceListCollection, function(el) {
            if (el.id == currentWidget.id) {
                     currentWidget.setSelectState(true, currentWidget.id);
            } else {
                var otherWidget = dijit.byId(el.id);
                otherWidget.setSelectState(false, el.id);
            }
        });
        dijit.byId('serviceAddButton').set('style', { display: 'none' });
        dijit.byId('serviceUpdateButton').set('style', { display: 'block',  "float": "left"});
    },
    search: function() {
        var searchValue = dojo.byId('search2').value;
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/search/"
        });
        
      computingOfferRestStore.add({
            value:searchValue
        }).then(function(data) {
            dojo.forEach(data, function(computingOfferData) {
                alert(computingOfferData.name);
            });
        });
    
    }
};

var FogWizardDiskOffer = {
    _diskOfferRestStore: "",
    name:"",
    description:"",
    diskSize:"",
    storageTags:"",
    diskZoneWidgets: "",
    _zoneRestStore:"",
    _podRestStore:"",
    _clusterRestStore:"",
    _storageTagRestStore:"",
    _hostTagRestStore:"",
    init : function() {
        
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        });       
        
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        
        this._podRestStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        });
        
        this._clusterRestStore = new JsonRest({
            target: core.getContextPath()+"/api/cluster/"
        });
        
        this._storageTagRestStore = new JsonRest({
            target: core.getContextPath()+"/api/storagePool/"
        });
        
        this._hostTagRestStore = new JsonRest({
            target: core.getContextPath()+"/api/host/"
        });
    },
    populateValues: function() {
        if(dijit.byId("diskOfferZoneListItem")) {
            dijit.byId("diskOfferZoneListItem").destroyRecursive();
        }
        
        if(dijit.byId("diskOfferStorageTagList") && dijit.byId("diskOfferCluster") &&  dijit.byId("diskOfferPod"))  {
            dijit.byId("diskOfferStorageTagList").destroyRecursive();
            dijit.byId("diskOfferCluster").destroyRecursive();
            dijit.byId("diskOfferPod").destroyRecursive();  
        }        
        
        dojo.query("#diskListCollection .WizardListItem").forEach(dojo.destroy);
        dojo.query("#diskListCollection .WizardZoneInfo").forEach(dojo.destroy);
        var clusterRestStore = this._clusterRestStore;
             
        FogWizardZone.init();
        
        var capabilitiesRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/listCSCapabilities/"
        });
        
        capabilitiesRestStore.query().then(function(data) {
            dojo.forEach(data,function(capabilitiyData) {                    
                dijit.byId("diskMaxSize").attr("constraints", {max: capabilitiyData.customDiskMaxSize, min: 1, pattern:'#'});
                dijit.byId("diskMaxSize").attr("value", capabilitiyData.customDiskMaxSize);
            });
        });
        
        
        var csConfigRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/CSConfig/"
        });
        
        csConfigRestStore.get("storage.max.volume.size").then(function(data) {
            dojo.forEach(data,function(configData) {                    
                dijit.byId("diskSize").attr("constraints", {max: parseInt(configData.value), min: 1, pattern:'#'});
            });
        });
        

        var podOptions = {
            identifier: 'id',
                    label: 'name',
                    items: [{id: "option", name: "Select Pod"}]
                };

                var clusterOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [{id: "option", name: "Select Cluster"}]
                };

                var storageTagOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [{id: "option", name: "Select Tag"}]
                };

                                           
                var podList = new ItemFileWriteStore({data: podOptions});
                var clusterList = new ItemFileWriteStore({data: clusterOptions});
                var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
                
                this._storageTagWidget = new Select({
                    id: "diskOfferStorageTagList",
                    store: storageTagList,
                    onChange: function() {
            
                        var hostTagCapacityRestStore = new JsonRest({
                            target: core.getContextPath()+"/api/storagePool/capacity/"
                        });
                        
                        if(this.value != "option") {
                           hostTagCapacityRestStore.get(this.value).then(function(data) {
                                dojo.forEach(data,function(storageData) {                    
                                    dijit.byId("diskSize").attr("constraints", {max: storageData.totalSize, min: 1, pattern:'#'});
                                    dijit.byId("diskMinSize").attr("constraints", {max: storageData.totalSize, min: 1, pattern:'#'});
                                    dijit.byId("diskMaxSize").attr("constraints", {max: storageData.totalSize, min: 1, pattern:'#'});
                                    dijit.byId("diskMaxSize").attr("value", storageData.totalSize);
                                });
                            }); 
                        }
                     }
                }).placeAt("diskOfferStorageTags"); 
                this._storageTagWidget.startup();
                this._clusterWidget = new Select({
                    
                id: "diskOfferCluster",
                store: clusterList,
                onChange: function() {
                    dojo.byId("diskClusterName").innerHTML = this.get("displayedValue");
                    dojo.query("#diskListCollection .WizardListItem").forEach(dojo.destroy);
                    dojo.query("#diskListCollection .WizardZoneInfo").forEach(dojo.destroy);
                    dojo.byId("wizardDiskViewAllTag").style.display = "block";
                    var diskOfferRestStore = new JsonRest({
                        target: core.getContextPath()+"/api/diskOffer/"
                    }); 
                    

                    var storageTagRestData = new JsonRest({
                        target: core.getContextPath()+"/api/storagePool"
                    });
                    var storageTagOptions = {
                                identifier: 'id',
                                label: 'name',
                                items: []
                            };
                    var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
                    
                    var storageTagWidget = dijit.byId("diskOfferStorageTagList");
                    if(this.value == "option") {
                    storageTagList.newItem({id: "option", name: "Select Tags"});
                    storageTagWidget.setStore(storageTagList);
                    dojo.byId("wizardDiskViewAllTag").style.display = "none";
                    dojo.query("#diskListCollection").removeClass("show_lable", true);    
                   } else {
                       dojo.query("#diskListCollection").toggleClass("show_lable", true);   
                       diskOfferRestStore.query({clusterReferenceId: this.value}).then(function(data){
                    dojo.forEach(data, function(diskOfferData) {

                        var diskOfferListItem = new FogPanel.WizardListItem({
                           heading: diskOfferData.name,
                           description: diskOfferData.description,

                           onClick: function() {
                               FogWizardDiskOffer.returnDiskOffer(this);
                           },

                           onDeleteTagClick: function() {
                               FogWizardDiskOffer.deleteCurrentWidget(this, "false");
                           },

                           additionalProperties : {
                               customDisk:diskOfferData.customized,
                               id:diskOfferData.id,
                               description: diskOfferData.description,
                               diskSize: diskOfferData.size,
                               name:diskOfferData.name,
                               storageTags: diskOfferData.tags,
                               zoneCosts: [],
                               storageType : diskOfferData.storageType,
                               currentZoneCost: diskOfferData.diskOfferZoneCosts
                           }
                       });
                       diskOfferListItem.placeAt("diskList");
                       diskOfferListItem.startup();
                    });
                });
                   }
                   var storageType = dijit.byId("diskStorageType").attr('displayedValue');
                    storageTagRestData.query({
                        clusterReferenceId: this.value,
                        storageType: storageType
                    }).then(function(data) {
                        if(data.length == 0) {
                            storageTagList.newItem({id: "option",  name: "No Tag"});  
                            storageTagWidget.setStore(storageTagList);
                        } else {
                            dojo.forEach(data, function(currentStorageTag) {
                                if(currentStorageTag.storageTag == null || currentStorageTag.storageTag == "null") {
                                    storageTagList.newItem({id: "option",  name: "No Tag"});                           
                                } else {
                                    storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId,  name: currentStorageTag.storageTag});
                                }                          
                            });
                            storageTagWidget.setStore(storageTagList);
                        }
                    });
                    
                    
                     var capabilitiesRestStore = new JsonRest({
                        target: core.getContextPath()+"/api/config/listCSCapabilities/"
                    });
        
                    capabilitiesRestStore.query().then(function(data) {
                        dojo.forEach(data,function(capabilitiyData) {                    
                            dijit.byId("diskMaxSize").attr("constraints", {max: capabilitiyData.customDiskMaxSize, min: 1, pattern:'#'});
                            dijit.byId("diskMaxSize").attr("value", capabilitiyData.customDiskMaxSize);
                        });
                    });
                    
                    var csConfigRestStore = new JsonRest({
                        target: core.getContextPath()+"/api/config/CSConfig/"
                    });
        
                    csConfigRestStore.get("storage.max.volume.size").then(function(data) {
                        dojo.forEach(data,function(configData) {                    
                            dijit.byId("diskSize").attr("constraints", {max: parseInt(configData.value), min: 1, pattern:'#'});
                        });
                    });
                    
                }
            }).placeAt("diskOfferClusterList"); 
            
            this._podWidget = new Select({
                id: "diskOfferPod",
                store: podList,
                onChange: function() {
                    dojo.byId("diskPodName").innerHTML = this.get("displayedValue");
                    var clusterWidget = dijit.byId("diskOfferCluster");
                    var clusterOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                
                var currentClusterList  = new ItemFileWriteStore({data: clusterOptions});
                if(this.value == "option") {
                    currentClusterList.newItem({id: "option", name: "Select Cluster"});
                    clusterWidget.setStore(currentClusterList);
                }
                clusterRestStore.get(this.value).then(function(clusterListItems) {
                    dojo.forEach(clusterListItems,function(currentcluster) {
                        
                        currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                    });
                    clusterWidget.setStore(currentClusterList);
                });
            }
        }).placeAt("diskOfferPodList"); 
      this._podWidget.startup();

var zoneOptions = {
    identifier: 'id',
    label: 'name',
    items: [{id: "option", name: "Select zone"}]
    };
    
    var zoneList = new ItemFileWriteStore({data: zoneOptions});
    this._zoneRestStore.query().then(function(data) {
        dojo.forEach(data, function(el) {
            zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
        });
    });                       
    this._zoneWidget = new Select({
        store: zoneList,
        id:"diskOfferZoneListItem",
        onChange: function() {
            FogWizardDiskOffer.viewSelectedZone(this);   
        }
    }).placeAt("diskOfferZoneList"); 
       this._zoneWidget.startup(); 
     
    },
    viewAllZoneInfo : function() {
        dojo.query("#diskListCollection .WizardListItem").forEach(dojo.destroy);
        dojo.query("#diskListCollection .WizardZoneInfo").forEach(dojo.destroy);
        
        
        var podRestStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        });
        
        var clusterRestStore = new JsonRest({
            target: core.getContextPath()+"/api/cluster/"
        });
        
        var diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        }); 
        
        this._zoneRestStore.query().then(function(zoneData) {
            dojo.forEach(zoneData, function(zoneInfo) {
                podRestStore.get(zoneInfo.referenceZoneId).then(function(podData) {
                    if(podData) {
                        dojo.query("#diskListCollection").toggleClass("show_lable", true);
                    } else {
                        dojo.query("#diskListCollection").removeClass("show_lable", true);
                    }
                    dojo.forEach(podData, function(podInfo) {
                        clusterRestStore.get(podInfo.podReferenceId).then(function(clusterData) {
                            dojo.forEach(clusterData, function(clusterInfo) {
                                                              
                                var count = 0;
                                diskOfferRestStore.query({clusterReferenceId: clusterInfo.clusterReferenceId,
                                        referenceZoneId: zoneInfo.referenceZoneId, podReferenceId: podInfo.podReferenceId}).then(function(data){
                                        dojo.forEach(data, function(diskOfferData) {
                                            if(count == 0) {
                                                 var diskZoneInfo = new FogPanel.WizardZoneInfo({
                                                 zoneName: diskOfferData.zone.name,
                                                 podName: diskOfferData.pod.name,
                                                 clusterName:diskOfferData.cluster.name 
                                                 
                                           
                                        });
                                        diskZoneInfo.placeAt("diskList");
                                        diskZoneInfo.startup();
                                       
                                        count++;
                                            }
                                            var diskOfferListItem = new FogPanel.WizardListItem({
                                            heading: diskOfferData.name,
                                            description: diskOfferData.description,

                                            onClick: function() {
                                                FogWizardDiskOffer.returnDiskOffer(this);
                                            },

                                            onDeleteTagClick: function() {
                                                FogWizardDiskOffer.deleteCurrentWidget(this, "false");
                                            },

                                            additionalProperties : {
                                                customDisk:diskOfferData.customized,
                                                id:diskOfferData.id,
                                                description: diskOfferData.description,
                                                diskSize: diskOfferData.size,
                                                name:diskOfferData.name,
                                                storageTags: diskOfferData.tags,
                                                zoneCosts: [],
                                                currentZoneCost: diskOfferData.diskOfferZoneCosts,
                                                storageType : diskOfferData.storageType
                                            }
                                        });
                                        diskOfferListItem.placeAt("diskList");
                                        diskOfferListItem.startup();
                                }); 
                            });
                           });
                       });
                   });
               }); 
            });
        });
    },
    viewSelectedZone : function(currentZone) {
        
        dojo.byId("diskZoneName").innerHTML = currentZone.get("displayedValue");
        var podWidget = dijit.byId("diskOfferPod");
        var currentZoneCost;
        
        if(dijit.byId("diskOfferCurrentZone")) {
            dijit.byId("diskOfferCurrentZone").destroyRecursive();
        }
        
        this._zoneRestStore.get(currentZone.value).then(function(selectedZoneInfo) {
           
            currentZoneCost  = new Zone.ZoneCost({
                id:"diskOfferCurrentZone",
                zoneIdNode: selectedZoneInfo.id,
                costRate : "Per Hour"                        
            });
            currentZoneCost.placeAt("diskZoneCost"); 
            currentZoneCost.removeCosts();
            
            currentZoneCost.setCostRate();
        }); 
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
    
        var currentPodList =  new ItemFileWriteStore({data: podOptions});
        if(currentZone.value == "option") {
            currentPodList.newItem({id: "option", name: "Select Pod"});
            podWidget.setStore(currentPodList);
            dijit.byId("diskAddButton").set("disabled", true);
            dijit.byId("diskCancelButton").set("disabled", true);
            
        } else {
            dijit.byId("diskAddButton").set("disabled", false);
        }
        this._podRestStore.get(currentZone.value).then(function(podListItems) {
            
            dojo.forEach(podListItems, function(currentPod) {
               
               currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
               
//               podWidget.set("value", currentPod.podReferenceId);
            });
            podWidget.setStore(currentPodList);
        });
    },
    getTags : function(currentValue) {
        var storageTagRestData = new JsonRest({
            target: core.getContextPath()+"/api/storagePool"
        });

        var storageType = currentValue.attr('displayedValue');
        var storageTagOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
        var clusterWidget  = dijit.byId("diskOfferCluster");
        var storageTagWidget = dijit.byId("diskOfferStorageTagList");
        storageTagRestData.query({
            clusterReferenceId: clusterWidget.value,
            storageType: storageType
        }).then(function(data) {
            if(data.length != 0) {
                dojo.forEach(data, function(currentStorageTag) {
                    if(currentStorageTag.storageTag == null || currentStorageTag.storageTag == "null") {
                        storageTagList.newItem({id: "option",  name: "No Tag"});
                    } else {
                        storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId,  name: currentStorageTag.storageTag});
                    }
                    
                });
            } else {
                storageTagList.newItem({id: "option",  name: "Select Tag"});
            }
            storageTagWidget.setStore(storageTagList);
        });
    },
    deleteCurrentWidget : function(currentDiskListWidget, forced) {

       var diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/delete/"
       });       
       dojo.byId("currentDiskOfferId").innerHTML = currentDiskListWidget.id;
       var status = forced;
       diskOfferRestStore.add({
           diskOfferId: currentDiskListWidget.additionalProperties.id,    
           forced: status
       }).then(function(result) {
       if(result == "OK") {
           currentDiskListWidget.deleteWidget();  
           dojo.query("#diskOfferingPage .updatable").removeClass("non_updatable", true);
           dojo.query("#diskOfferingPage .hide_lable").removeClass("show_lable", true);
           dijit.byId("diskCancelButton").set("disabled", true);
           dijit.byId('diskAddButton').set('style', {display: 'block', "float": 'left'});
           dijit.byId('diskUpdateButton').set('style', {display: 'none'});

            dojo.byId("diskZoneName").innerHTML = "";
            dojo.byId("diskPodName").innerHTML = "";
            dojo.byId("diskClusterName").innerHTML = "";
           var zoneNode = dojo.byId("currentDiskZoneInfo");
           var zoneWidgets = dijit.registry.findWidgets(zoneNode);
           dojo.forEach(zoneWidgets, function(el) {
               el.clearWidgets();
           });
           
           registry.byId("appToaster").setContent("Deleted Successfully!", "message");
           registry.byId("appToaster").show();
           dijit.byId("diskOfferContentForm").reset();
           dijit.byId("diskDescription").set("value", "");
       } else {
           var errorMessage;
           dojo.forEach(result, function(el) {
                    
            errorMessage = el.cause.localizedMessage;
        });
        dijit.byId("deleteDiskOfferDialog").show();
        registry.byId("appToaster").setContent(errorMessage, "error");
        registry.byId("appToaster").show();
    }
});
   },
    conformDelete : function() {
        var currentWidgetId = dojo.byId("currentDiskOfferId").innerHTML;
         var currentDiskListWidget = dijit.byId(currentWidgetId);
        FogWizardDiskOffer.deleteCurrentWidget(currentDiskListWidget, "true");
        dijit.byId("deleteDiskOfferDialog").hide();
    },
    closeDeleteDialog:function() {
        dijit.byId("deleteDiskOfferDialog").hide();
    },
    returnDiskOffer: function(currentWidget) {
        dojo.byId("diskSizeHide").style.display = "block";
        var zoneNode = dojo.byId("currentDiskZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        dojo.forEach(zoneWidgets, function(el) {
            el.clearWidgets();
        });    
        dijit.byId("diskCancelButton").set("disabled", false);
        var currentDiskListWidget  = currentWidget;
        dojo.byId("currentDiskOfferId").innerHTML = currentWidget.id;
        this._diskOfferRestStore.get(currentDiskListWidget.additionalProperties.id).then(function(diskOfferData) {
            
            dojo.byId("zoneNameLabel").innerHTML = diskOfferData.zone.aliasName;
            dojo.byId("podNameLabel").innerHTML = diskOfferData.pod.name;
            dojo.byId("clusterNameLabel").innerHTML = diskOfferData.cluster.name;
            
            dojo.byId("diskZoneName").innerHTML = diskOfferData.zone.aliasName;
            dojo.byId("diskPodName").innerHTML = diskOfferData.pod.name;
            dojo.byId("diskClusterName").innerHTML = diskOfferData.cluster.name;
            
           var diskOfferZoneInfo = dojo.byId("currentDiskZoneInfo");
            var diskOfferZonewidgets = dijit.registry.findWidgets(diskOfferZoneInfo);
           
               
            dojo.forEach(diskOfferData.diskOfferZoneCosts, function(el) {
                dojo.forEach(diskOfferZonewidgets, function(zoneId) {
                    if(el.zone.id == zoneId.getZoneId()) {
                        zoneId.zoneCost = (el.cost*720).toFixed(2);
                        zoneId.setCost();                        
                    }  
                });
            });  
            
            
        if(diskOfferData.minSize == ""|| diskOfferData.minSize == null || diskOfferData.minSize == "null") {
            dojo.byId("diskMinSizeLabel").innerHTML = ".......";
        } else {
            dojo.byId("diskMinSizeLabel").innerHTML = diskOfferData.minSize;  
        }
        
        
        if(diskOfferData.maxSize == ""|| diskOfferData.maxSize == null || diskOfferData.maxSize == "null") {
            dojo.byId("diskMaxSizeLabel").innerHTML = ".......";
        } else {
            dojo.byId("diskMaxSizeLabel").innerHTML = diskOfferData.maxSize;  
        }
        
        if(diskOfferData.customized == "true" || diskOfferData.customized == true) {
             dojo.byId("diskSizeHide").style.display = "none";
             dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").toggleClass("updatableContent ", true);
        } else if(diskOfferData.customized == "false" || diskOfferData.customized == false) {
             dojo.byId("diskSizeHide").style.display = "block";
              dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
        }       
        
        
        if(diskOfferData.tags == "null"|| diskOfferData == null || diskOfferData.tags == "" || diskOfferData.tags == " ") {
            dojo.byId("diskStorageTagsLabel").innerHTML = "No Tag";
        } else {
            dojo.byId("diskStorageTagsLabel").innerHTML = diskOfferData.tags; 
        }
        
        if(diskOfferData.storageType == "null"|| diskOfferData.storageType == null || diskOfferData.storageType == "") {
            dojo.byId("diskStorageTypeLabel").innerHTML = ".......";
        } else {
            dojo.byId("diskStorageTypeLabel").innerHTML = diskOfferData.storageType; 
        }
        
        
        });
        
        
        dojo.query("#diskOfferingPage .updatable").toggleClass("non_updatable", true);
        dojo.query("#diskOfferingPage .hide_lable").toggleClass("show_lable", true);
        this.customDisk = dijit.byId("customDisk");
        this.name = dijit.byId ("diskName");
        this.description = dijit.byId ("diskDescription");
        this.diskSize = dijit.byId ("diskSize");
        this.storageTags = dijit.byId ("diskStorageTags");
        this.name.setValue(currentDiskListWidget.additionalProperties.name);
        this.description.setValue(currentDiskListWidget.additionalProperties.description);

        dojo.byId("diskSizeLabel").innerHTML = currentDiskListWidget.additionalProperties.diskSize +  " GB";
        
        
        if(currentDiskListWidget.additionalProperties.customDisk == "false"|| currentDiskListWidget.additionalProperties.customDisk == false) {
           dojo.byId("customDiskLabel").innerHTML = "no"; 
        } else if(currentDiskListWidget.additionalProperties.customDisk == "true"|| currentDiskListWidget.additionalProperties.customDisk == true) {
            dojo.byId("customDiskLabel").innerHTML = "yes"; 
        }
            
        
        var diskCol = dojo.query("#diskList .WizardListItem");
        
        dojo.forEach(diskCol, function(el) {
            if (el.id == currentWidget.id) {
                     currentWidget.setSelectState(true, currentWidget.id);
            } else {
                var otherWidget = dijit.byId(el.id);
                otherWidget.setSelectState(false, el.id);
            }
        });
      
        dijit.byId('diskAddButton').set('style', {display: 'none'});
        dijit.byId('diskUpdateButton').set('style', {display: 'block', "float": 'left'});
    },
    authetication : function() {
        var status = true;
        var pageNode = dojo.byId("diskOfferingPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
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
        }          
        
        var zoneNode = dojo.byId("currentDiskZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
        dojo.forEach(zoneWidgets, function(el) {
                       
            status = el.showError();
        });
        
        return status; 
    },
    add: function() { 
        var status = FogWizardDiskOffer.authetication();
        if(status == true) {
            var diskZoneCosts = [];
            dijit.byId("diskAddButton").set("disabled", true);
            var name = dijit.byId("diskName");
            var editValue = dijit.byId("diskDescription").value.toString();
            var description = editValue.replace("\n"," ");
            var diskSize = dijit.byId("diskSize");
            var storageTag;
            var storageType = dijit.byId("diskStorageType");
            var customDisk = dijit.byId("customDisk");
            var podWidget = dijit.byId("diskOfferPod");
            var clusterWidget = dijit.byId("diskOfferCluster");
    
            var minSize = dijit.byId("diskMinSize").value;
            var maxSize = dijit.byId("diskMaxSize").value;
            if(dijit.byId("diskOfferStorageTagList").value == "option") {
                storageTag = "";
            } else {
                storageTag = dijit.byId("diskOfferStorageTagList").attr('displayedValue');
            }

              if(customDisk.checked == true) {
                    diskSize = 0;
                } else {
                    diskSize =  dijit.byId("diskSize").value;
                }



            var diskZoneNode = dojo.byId("currentDiskZoneInfo");
            this.diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
            dojo.forEach(this.diskZoneWidgets, function(el) {
                diskZoneCosts.push ({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCost(),
                    minimumCost: "5"
                });
    //             status = el.showError();
            });

            for (var index = 0; index < diskZoneCosts.length-this.diskZoneWidgets.length; index++) {
                diskZoneCosts.splice(index, this.diskZoneWidgets.length);
            }         

            var diskOfferListItem;

               this._diskOfferRestStore.add({
                name: name.value,
                referenceZoneId : this._zoneWidget.value,
                podReferenceId :  podWidget.value,
                clusterReferenceId: clusterWidget.value,
                customDisk:customDisk.checked,
                description: description,
                storageType : storageType.value,
                diskSize: diskSize, 
                minSize: minSize,
                maxSize: maxSize,
                storageTag: storageTag, 
                zoneCosts: diskZoneCosts
            }).then(function (data) {
                dijit.byId("diskAddButton").set("disabled", false);
                if(data[0].result == "OK") {
                    dojo.byId("diskSizeHide").style.display = "block";
                    diskOfferListItem = new FogPanel.WizardListItem({
                        heading: data[0].diskOffer.name,
                        description: data[0].diskOffer.description,
                        onClick: function() {
                            FogWizardDiskOffer.returnDiskOffer(this);
                        },
                        onDeleteTagClick: function() {
                            FogWizardDiskOffer.deleteCurrentWidget(this, "false");
                        },
                        additionalProperties : {
                            customDisk: data[0].diskOffer.customized,
                            id:data[0].diskOffer.id,
                            description: data[0].diskOffer.description,
                            diskSize: data[0].diskOffer.size,
                            name: data[0].diskOffer.name,
                            storageTags: data[0].diskOffer.tags,
                            zoneCosts: data[0].diskOffer.diskOfferZoneCosts,
                            storageType : data[0].storageType
                        }
                    });
                    diskOfferListItem.placeAt("diskList");
                    diskOfferListItem.startup();
                    var diskZoneNode = dojo.byId("currentDiskZoneInfo");
                    var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
                    dojo.forEach(diskZoneWidgets, function(el) {
                        el.clearWidgets();
                    });            
                    registry.byId("appToaster").setContent("Added Successfully!", "message");
                    registry.byId("appToaster").show();        

                    dijit.byId("diskDescription").set("value", "");
                    dijit.byId("diskOfferContentForm").reset();
                    dojo.byId("diskSizeHide").style.display = "block";
                    dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
                 } else {
                     registry.byId("appToaster").setContent("Cannot be Added!", "error");
                     registry.byId("appToaster").show();
                 }
             }); 
      
        }         
     },  
     update: function() {
         
        var status = FogWizardDiskOffer.authetication();
        if(status == true) {
            var currentWidgetId = dojo.byId("currentDiskOfferId").innerHTML;
         var currentDiskListWidget = dijit.byId(currentWidgetId);
         this.name = dijit.byId ("diskName");
         dijit.byId("diskUpdateButton").set("disabled", true);
        var editValue = dijit.byId("diskDescription").value.toString();
        var description = editValue.replace("\n"," ");
     
             currentDiskListWidget.additionalProperties.heading = this.name.value;
             currentDiskListWidget.additionalProperties.description = this.description.value; 
             
             var zoneNode = dojo.byId("currentDiskZoneInfo");
             var zoneWidgets = dijit.registry.findWidgets(zoneNode);
             dojo.forEach(zoneWidgets, function(el) {
                 currentDiskListWidget.additionalProperties.zoneCosts.push({
                     zoneId: el.getZoneId(), 
                     cost: el.getZoneCost(),
                     minimumCost: "6"
                 });
//                status = el.showError();
             });   
            for(var index=0; index < currentDiskListWidget.additionalProperties.zoneCosts.length-zoneWidgets.length; index++) {
                 currentDiskListWidget.additionalProperties.zoneCosts.splice(index, zoneWidgets.length);
             }
            
                 this._diskOfferRestStore.put({
                 id: currentDiskListWidget.additionalProperties.id,
                 name: this.name.value,
                 description: description,
                 zoneCosts: currentDiskListWidget.additionalProperties.zoneCosts
                 
             }).then(function(result) {
                 dijit.byId("diskUpdateButton").set("disabled", false);
                 if(result == "OK") {
                         
                     currentDiskListWidget.getData();
                     
                     dojo.query("#diskOfferingPage .updatable").removeClass("non_updatable", true);
                     dojo.query("#diskOfferingPage .hide_lable").removeClass("show_lable", true);

                     dijit.byId('diskAddButton').set('style', {display: 'block', "float": 'left'});
                     dijit.byId('diskUpdateButton').set('style', {display: 'none'});
                     
                     var diskZoneNode = dojo.byId("currentDiskZoneInfo");
                    var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
                    dojo.forEach(diskZoneWidgets, function(el) {
                            el.clearWidgets();
                            
                    });
                    registry.byId("appToaster").setContent("updated Successfully!","message");
                    registry.byId("appToaster").show();
                    dijit.byId("diskOfferContentForm").reset();
                    dijit.byId("diskDescription").set("value", "");
                     
                     dojo.byId("diskZoneName").innerHTML = "";
                    dojo.byId("diskPodName").innerHTML = "";
                    dojo.byId("diskClusterName").innerHTML = "";
                    dijit.byId("diskCancelButton").set("disabled", true);
                     
                 } else {
                     registry.byId("appToaster").setContent("Cannot be updated !", "error");
                     registry.byId("appToaster").show();
                 }  
             });           
             
             currentDiskListWidget.setSelectState(false, currentDiskListWidget.id); 
        }
          
        
     },
    cancel: function() {    
        
         dojo.byId("diskSizeHide").style.display = "block";
         var currentWidgetId = dojo.byId("currentDiskOfferId").innerHTML;
         var currentDiskListWidget = dijit.byId(currentWidgetId);
        dijit.byId("diskDescription").set("value", "");
         dojo.byId("diskZoneName").innerHTML = "";
        dojo.byId("diskPodName").innerHTML = "";
        dojo.byId("diskClusterName").innerHTML = "";
         currentDiskListWidget.setSelectState(false, currentDiskListWidget.id);  
         dojo.query("#diskOfferingPage .updatable").removeClass("non_updatable", true);
         dojo.query("#diskOfferingPage .hide_lable").removeClass("show_lable", true);
         dojo.byId("wizardDiskViewAllTag").style.display = "none";      
         dijit.byId('diskAddButton').set('style', {display: 'block', "float": 'left' });
         dijit.byId('diskUpdateButton').set('style', {display: 'none'});
//         dijit.byId("diskOfferZoneForm").reset();
         dijit.byId("diskOfferContentForm").reset();
//         dojo.query("#diskListCollection .WizardListItem").forEach(dojo.destroy);
//        dojo.query("#diskListCollection .WizardZoneInfo").forEach(dojo.destroy);
         var zoneNode = dojo.byId("currentDiskZoneInfo");
         var zoneWidgets = dijit.registry.findWidgets(zoneNode);
         dojo.forEach(zoneWidgets, function(el) {
             el.clearWidgets();
         });       
         dijit.byId("diskCancelButton").set("disabled", true);
        },
        
    enableContent: function(currentElement) {
            
        var customDisk = dijit.byId("customDisk");
        if(currentElement.checked == true) {
             dojo.byId("diskSizeHide").style.display = "none";
             dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").toggleClass("updatableContent ", true);
        } else if(customDisk.checked == false) {
             dojo.byId("diskSizeHide").style.display = "block";
              dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
        } 
    } 
        
};

var FogWizardIpManagement = {
    _ipManagementRestStore : "",
    _zoneRestStore: "",
    _podRestStore : "",
    _zoneWidget : "",
    _podWidget : "",
    init : function() {
        this._ipManagementRestStore = new JsonRest({
            target: core.getContextPath()+"/api/ipAddress/"
        });
        
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        
        this._podRestStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        });
    }, 
    populateValues : function() {
        var podRestStore = this._podRestStore;
        var ipManagementRestStore = this._ipManagementRestStore;   
        
        if (dojo.query("#ipListCollection .WizardListItem").length != 0) {
            return;
        }               
         if(dijit.byId("ipZone") && dijit.byId("ipPod")) {
                dijit.byId("ipZone").destroyRecursive();
                dijit.byId("ipPod").destroyRecursive();
        }
        FogWizardZone.init();
               
         var podOptions = {
                        identifier: 'id',
                        label: 'name',
                        items: [{id: "option", name: "Select Pod"}]
                    };
                    
                    var podList = new ItemFileWriteStore({data: podOptions});
                    
                    this._podWidget = new Select({
                        id: "ipPod",
                        
                        store: podList,
                        onChange : function() {
                            
                            dojo.query("#ipListCollection .WizardListItem").forEach(dojo.destroy);
                
                            if(this.value != "option") {
                            var ipManagementRestStore = new JsonRest({
                                target: core.getContextPath()+"/api/ipAddress/"
                            });
                            
                             ipManagementRestStore.query({podReferenceId: this.value}).then(function(data){
                    dojo.forEach(data, function(ipData) {
                        var ipListItem = new FogPanel.WizardListItem({
                            heading: ipData.name,
                            onClick: function() {
                                FogWizardIpManagement.returnIpManagement(this);
                            },
                            onDeleteTagClick: function() {
                                FogWizardIpManagement.deleteCurrentWidget(this, "false");
                            },
                            additionalProperties : {
                                id: ipData.ipId,
                                ipBlockName : ipData.name,
                                startIp : ipData.startIp,
                                endIp : ipData.endIp,
                                netMask : ipData.netMask,
                                gateWay: ipData.gateWay,
                                dns1 : ipData.dns1,
                                dns2 : ipData.dns2,
                                podReferenceId:ipData.podReferenceId,
                                referenceZoneId:ipData.referenceZoneId,
                                zoneName: ipData.zoneName
                            }
                        });
                        ipListItem.placeAt("ipListItem");
                        ipListItem.startup();
                    });
                });
                            }
                        }
                    }).placeAt("ipPodList");  
                   
        
        var zoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Zone"}]
        };
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
            });
        });                       
        this._zoneWidget = new Select({
            id: "ipZone",
            store: zoneList,
            onChange: function() {
                dijit.byId("ipManagementPageForm").reset();
                var podWidget = dijit.byId("ipPod");              
                var podOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
            
                var currentPodList = "";
                currentPodList = new ItemFileWriteStore({data: podOptions});
                if(this.value == "option") {
                    currentPodList.newItem({id: "option", name: "Select Pod"});
                     podWidget.setStore(currentPodList);
                } else {
                    
                }
                var podRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/pod/"
                });
                podRestStore.get(this.value).then(function(podListItems) {
                    dojo.forEach(podListItems, function(currentPod) {
                        
                        currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
                    });
                    podWidget.setStore(currentPodList);
                });
            }
        }).placeAt("ipZoneList"); 
    },
    add : function() {
        var diskOfferNode = dojo.byId("ipManagementPage");
        var diskOfferWidget = dijit.registry.findWidgets(diskOfferNode);
        dojo.forEach(diskOfferWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                };
            };
        });
        
        var ipBlockName = dijit.byId ("ipName");
        var startIp = dijit.byId ("startIp");
        var endIp = dijit.byId ("endIp");
        var netMask = dijit.byId("ipNetmask");
        var gateWay = dijit.byId("ipGateway");
        var dns1 = dijit.byId("ipDns1");
        var dns2 = dijit.byId("ipDns2");
        var podWidget = dijit.byId("ipPod");

        var ipListItem;
        this._ipManagementRestStore.add({
            ipBlockName : ipBlockName.value,
            startIp : startIp.value,
            endIp : endIp.value,
            netMask : netMask.value,
            gateWay: gateWay.value,
            dns1 : dns1.value,
            dns2 : dns2.value,
            zoneReferenceId: this._zoneWidget.value,
            podReferenceId: podWidget.value
        }).then(function (data) {
            dojo.forEach(data, function(ipData) {
                if(ipData.result == "OK") {
                    var ipListItem = new FogPanel.WizardListItem({
                        heading: ipData.name,
                        onClick: function() {
                            FogWizardIpManagement.returnIpManagement(this);
                        },
                        onDeleteTagClick: function() {
                            FogWizardIpManagement.deleteCurrentWidget(this, "false");
                        },
                        additionalProperties : {
                            id: ipData.ipId,
                            ipBlockName : ipData.name,
                            startIp : ipData.startIp,
                            endIp : ipData.endIp,
                            netMask : ipData.netMask,
                            gateWay: ipData.gateWay,
                            dns1 : ipData.dns1,
                            dns2 : ipData.dns2,
                            podReferenceId:ipData.podReferenceId,
                            referenceZoneId:ipData.referenceZoneId,
                            zoneName: ipData.zoneName
                        }
                    });
                    ipListItem.placeAt("ipListItem");
                    ipListItem.startup();
                    dijit.byId("ipManagementPageForm").reset();
                    registry.byId("appToaster").setContent("Added Successfully!", "message");
                    registry.byId("appToaster").show(); 
                } else {
                     registry.byId("appToaster").setContent("Cannot be Added!", "error");
                     registry.byId("appToaster").show();
                };
            });
        });
    },
    cancel : function () {
        dijit.byId('ipAddButton').set('style', {display: 'block', "float": 'left'});
        dijit.byId('ipUpdateButton').set('style', {display: 'none'});
        
         var ipListCollection = dojo.byId ("ipListCollection");
                var ipListWidgets = dijit.registry.findWidgets (ipListCollection);
        
                dojo.forEach (ipListWidgets, function(el) {
                   
                        el.setSelectState(false, el.id);
                   
                }); 
        dojo.byId("ipManagementForm").reset();
        dojo.byId("ipManagementPageForm").reset();
    },
    deleteCurrentWidget : function(currentDiskListWidget) {
        
      var ipManagementRestStore = new JsonRest({
            target: core.getContextPath()+"/api/ipAddress/"
      });
      ipManagementRestStore.remove(currentDiskListWidget.additionalProperties.id);
    },
    returnIpManagement : function(currentWidget) {
        dojo.byId("currentIpListItem").value = currentWidget.id;
        dijit.byId("ipName").setValue(currentWidget.additionalProperties.ipBlockName);
        dijit.byId("startIp").setValue(currentWidget.additionalProperties.startIp);
        dijit.byId("endIp").setValue(currentWidget.additionalProperties.endIp);
        dijit.byId("ipGateway").setValue(currentWidget.additionalProperties.gateWay);
        dijit.byId("ipDns1").setValue(currentWidget.additionalProperties.dns1);
        dijit.byId("ipDns2").setValue(currentWidget.additionalProperties.dns2);
        dijit.byId("ipNetmask").setValue(currentWidget.additionalProperties.netMask);
        dijit.byId('ipAddButton').set('style', {display: 'none'});
        dijit.byId('ipUpdateButton').set('style', {display: 'block', "float": 'left'});
        
        var ipListCollection = dojo.byId ("ipListCollection");
        var ipListWidgets = dijit.registry.findWidgets (ipListCollection);
        
         dojo.forEach (ipListWidgets, function(el) {
             if (currentWidget.id == el.id) {
                 currentWidget.setSelectState(true, currentWidget.id);
            }
            else {
                 el.setSelectState(false, el.id);
             };
         }); 
    },
    update : function() {
        var currentWidgetId = dojo.byId("currentIpListItem").value;
        var currentIpListWidget = dijit.byId(currentWidgetId);
        var diskOfferNode = dojo.byId("ipManagementPage");
        var diskOfferWidget = dijit.registry.findWidgets(diskOfferNode);
        
        dojo.forEach(diskOfferWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                };
            };
        });
        
        var ipBlockName = dijit.byId ("ipName");
        var startIp = dijit.byId ("startIp");
        var endIp = dijit.byId ("endIp");
        var netMask = dijit.byId("ipNetmask");
        var gateWay = dijit.byId("ipGateway");
        var dns1 = dijit.byId("ipDns1");
        var dns2 = dijit.byId("ipDns2");
        var podWidget = dijit.byId("ipPodList");
        currentIpListWidget.additionalProperties.heading = ipBlockName.value;
          
        this._ipManagementRestStore.put({
            id: currentIpListWidget.additionalProperties.id,
            ipBlockName : ipBlockName.value,
            startIp : startIp.value,
            endIp : endIp.value,
            netMask : netMask.value,
            gateWay: gateWay.value,
            dns1 : dns1.value,
            dns2 : dns2.value,
            zoneReferenceId: this._zoneWidget.value,
            podReferenceId: podWidget.value
        }).then(function(result) {
            if(result == "OK") {
                currentIpListWidget.getData();
                dijit.byId('ipAddButton').set('style', {display: 'block', "float": 'left'});
                dijit.byId('ipUpdateButton').set('style', {display: 'none'});
                dijit.byId("ipManagementPageForm").reset();
                registry.byId("appToaster").setContent("updated Successfully!","message");
                registry.byId("appToaster").show();
                
                var ipListCollection = dojo.byId ("ipListCollection");
                var ipListWidgets = dijit.registry.findWidgets (ipListCollection);
        
                dojo.forEach (ipListWidgets, function(el) {
                   
                        el.setSelectState(false, el.id);
                   
                }); 
            } else {
                registry.byId("appToaster").setContent("Cannot be updated !", "error");
                registry.byId("appToaster").show();
            };  
        });
        currentIpListWidget.setSelectState(false, currentIpListWidget.id);  
    },
    ipValidate: function(ip, widgetId) {
        var currentWidget = dijit.byId(widgetId);
        var ipParts = ip.split(".");
        currentWidget.validator = function(ip) {
            if(ipParts.length==4){ 
                var ipPart1 = parseInt(ipParts[0]);  
                if(ipPart1 >= 224 && ipPart1 <= 255){
                    currentWidget.set("invalidMessage", "this range is allocated for unicat and multicast");
                    return false;
                }  
                else if(ipPart1 ==127){
                   currentWidget.set("invalidMessage", "this range is allocated for loopback");
                   return false;
                } else if(ipPart1 ==127){
                   currentWidget.set("invalidMessage", "this range is allocated for loopback");
                   return false;
                } else{
                  return true;
                }; 
            }; 
        };
    },
    validateNetmask: function(ip, widgetId) {
        var currentWidget = dijit.byId(widgetId);
        var ipParts = ip.split(".");
        currentWidget.validator = function(ip) {
            if(ipParts.length==4){ 
                var ipPart1 = parseInt(ipParts[0]);  
                if(ipPart1 < 255 ){
                    currentWidget.set("invalidMessage", "invalid netmask");
                    return false;
                } else{
                    return true;
                }; 
            };
        };
    }
};

var FogWizardMiscellaneousCost = { 
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
    _summaryGridWidget:"",
    init: function() {
        this._misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });

        this._podRestStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        });
        
        this._clusterRestStore = new JsonRest({
            target: core.getContextPath()+"/api/cluster/"
        });
    },
    populateValues: function() {  
        if (dojo.query("#miscellaneousListContainer .WizardListItem").length != 0) {
            return;
        } 
        if(dijit.byId("summaryGrid")) {
            dijit.byId("summaryGrid").destroyRecursive();
        }
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var misclRestStore = this._misclRestStore;
        if(dijit.byId("miscZone") && dijit.byId("miscPod") && dijit.byId("miscCluster")) {
            dijit.byId("miscZone").destroyRecursive();
            dijit.byId("miscPod").destroyRecursive();
            dijit.byId("miscCluster").destroyRecursive();
        }

        var zoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Zone"}]
        }; 
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
            });
        });                        
        
        this._zoneWidget = new Select({
            id : "miscZone",
            store: zoneList,
            onChange: function() {  
                var podWidget = dijit.byId("miscPod");
                var currentZoneCost;
                if(dijit.byId("misclCurrentZone")) {
                    dijit.byId("misclCurrentZone").destroyRecursive();
                }
                
                var zoneRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/zone/"
                });
            
                zoneRestStore.get(this.value).then(function(selectedZoneInfo) {
                    currentZoneCost  = new Zone.ZoneCost({
                        id:"misclCurrentZone",
                        zoneName: "Cost/Month $",
                        zoneIdNode: selectedZoneInfo.id
                    });
                    currentZoneCost.placeAt("misceZoneCost"); 
//                    currentZoneCost.showStopageCost();
            
                    currentZoneCost.setCostRate();
                });
                
                var podOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                
                var currentPodList = "";
                var podRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/pod/"
                });
                currentPodList = new ItemFileWriteStore({data: podOptions});
                if(this.value == "option") {
                    currentPodList.newItem({id: "empty", name: "Select Pod"});
                    podWidget.setStore(currentPodList);
                } else {
                    podRestStore.get(this.value).then(function(podListItems) {
                    dojo.forEach(podListItems, function(currentPod) {
                        currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
                    });
                    podWidget.setStore(currentPodList);
                });
            }
        }
    }).placeAt("miscZoneList"); 
    misclRestStore.query().then(function(data) {
        dojo.forEach(data, function(misclData) {
            var misclListItem = new FogPanel.WizardListItem({
                heading: misclData.name,
                description: misclData.description,
                onClick: function() {   
                    FogWizardMiscellaneousCost.returnMiscellaneousCost(this);
                },
                onDeleteTagClick: function() {
                    FogWizardMiscellaneousCost.deleteCurrentWidget(this, "false");
                },
                onSummaryTagClick : function() {
                    FogWizardMiscellaneousCost.planSummary(this);
                },
                additionalProperties : {
                    id:misclData.id,
                    name: misclData.name,
                    description: misclData.unit,
                    zoneCosts: []
                }
            });
            misclListItem.placeAt("misceList");
            misclListItem.startup();
            misclListItem.disableStates();
            misclListItem.enableSummary();
        });
    });
    FogWizardZone.init();
    var podOptions = {
        identifier: 'id',
        label: 'name',
        items: [{id: "option", name: "Select Pod"}]
    };
    var clusterOptions = {
        identifier: 'id',
        label: 'name',
        items: [{id: "option", name: "Select Cluster"}]
    };
    var podList = new ItemFileWriteStore({data: podOptions});
    var clusterList = new ItemFileWriteStore({data: clusterOptions});
             
    this._podWidget = new Select({
        id: "miscPod",
        store: podList,
        onChange: function() {
            var clusterWidget = dijit.byId("miscCluster");
            var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
        if(this.value == "empty") {
            currentClusterList.newItem({id: "empty", name: "Select Cluster"});
            clusterWidget.setStore(currentClusterList);
        } else {
            clusterRestStore.get(this.value).then(function(clusterListItems) {
                dojo.forEach(clusterListItems, function(currentcluster) {
                    currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                });
                clusterWidget.setStore(currentClusterList);
            });
        }
    }
}).placeAt("miscPodList");   

this._clusterWidget = new Select({
    id: "miscCluster",
    store: clusterList,
    onChange : function() {
        var miscId = dojo.byId("currentMisscellaneousOfferId").value;
        var miscWidget = dijit.byId(miscId);
            
        var misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });  
            
        var miscelZoneNode = dojo.byId("currentMisceZoneCostList");
        var miscelZoneWidgets = dijit.registry.findWidgets(miscelZoneNode);
            
        misclRestStore.query({miscellaneousOfferId: miscWidget.additionalProperties.id, 
        clusterReferenceId: this.value}).then(function(data) {
            dojo.forEach(data, function(miscData) {
                dojo.forEach(miscelZoneWidgets, function(el) {
                    el.zoneCost = (miscData.cost*720).toFixed(2);
                    el.costRate = miscData.miscellaneousOffer.unit;
                    el.setCostRate();
                    el.setCost();                 
                });                                     
            });
        });
        
        dojo.forEach(miscelZoneWidgets, function(el) {
             el.clearWidgets();
         }); 
    }
}).placeAt("miscClusterList");


var summaryData = {
            items: []
        };
var summaryDataList = new ItemFileWriteStore({data: summaryData});

var summaryLayout = [[
                 {'field': 'id', 'hidden' : 'true'},
                 {'name': 'Zone', 'field': 'zone', 'width' : '200px'},
                 {'name': 'Pod', 'field': 'pod', 'width' : '200px'},
                 {'name': 'Cluster', 'field': 'cluster', 'width' : '150px'},
                 {'name': 'Cost', 'field': 'cost', 'width' : '174px'}                
             ]
         ];  
         
         
         this._summaryGridWidget = new DataGrid({
             
             store: summaryDataList,
             structure: summaryLayout,
             height: '130px',
             noDataMessage: "There is no data"            
         });
         this._summaryGridWidget.placeAt("summaryGrid");
         this._summaryGridWidget.startup();
},
planSummary : function(currentWidget) {
    var summaryGridWidget = this._summaryGridWidget;
    
    var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        
         var misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
         });      
         misclRestStore.get(currentWidget.additionalProperties.id).then(function(data) {
             dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                 if(miscData) {
                     summaryDataList.newItem({id:miscData.id, zone: miscData.zone.aliasName, pod: miscData.pod.name, cluster: miscData.cluster.name, cost:miscData.cost });
                 }         
             });
             summaryGridWidget.setStore(summaryDataList);
             
         });
            
          
          
         dijit.byId("summaryDialog").show();
        },
        closeSummaryDialogue  : function() {
            dijit.byId("summaryDialog").hide();
        },
        update: function() {
    
        var podWidget = dijit.byId("miscPod");
        var clusterWidget = dijit.byId("miscCluster");
        
        var currentMiscellaneousWidgetId = dojo.byId("currentMisscellaneousOfferId").value;
        var currentMiscellaneousWidget = dijit.byId(currentMiscellaneousWidgetId);
        var miscelZoneCostList = dojo.byId("currentMisceZoneCostList");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);
        var status;
        dojo.forEach(miscelZoneCostWidgets, function(el) {
            currentMiscellaneousWidget.additionalProperties.zoneCosts.push({
                zoneId: el.getZoneId(), 
                cost: el.getZoneCost()
            });
           status = el.showError();
        }); 
        
        for(var index=0; index < currentMiscellaneousWidget.additionalProperties.zoneCosts.length-miscelZoneCostWidgets.length; index++) {
            currentMiscellaneousWidget.additionalProperties.zoneCosts.splice(index, miscelZoneCostWidgets.length);
        } 
        if(status == true || status == "true") {
            this._misclRestStore.put({
            id: currentMiscellaneousWidget.additionalProperties.id,
            zoneCosts: currentMiscellaneousWidget.additionalProperties.zoneCosts,
            zoneReferenceId : this._zoneWidget.value,
            clusterReferenceId : clusterWidget.value,
            podReferenceId : podWidget.value
         }).then(function(result)  {
             if(result == "OK") {
                 
                  var miscelZoneCostList = dojo.byId("currentMisceZoneCostList");
                var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);

                dojo.forEach(miscelZoneCostWidgets, function(el) {
                    
                    el.clearWidgets();
                }); 
                
                registry.byId("appToaster").setContent("updated Successfully!","message");
                 registry.byId("appToaster").show();
             } else {
                 registry.byId("appToaster").setContent("Cannot be updated !", "error");
                 registry.byId("appToaster").show();
             }  
         });
        }
        
         currentMiscellaneousWidget.setSelectState(false, currentMiscellaneousWidget.id); 
     }, 
     returnMiscellaneousCost: function(currentMisceWidget) {
         var zoneNode = dojo.byId("currentMisceZoneCostList");
         var zoneWidgets = dijit.registry.findWidgets(zoneNode);
         dojo.forEach(zoneWidgets, function(el) {
             el.clearWidgets();
         }); 
         var clusterWidget = dijit.byId("miscCluster");
         dojo.byId("currentMisscellaneousOfferId").value = currentMisceWidget.id; 
         this._misclRestStore.query({miscellaneousOfferId: currentMisceWidget.additionalProperties.id, 
        clusterReferenceId: clusterWidget.value}).then(function(data) {
             var miscelZoneNode = dojo.byId("currentMisceZoneCostList");
             var miscelZoneWidgets = dijit.registry.findWidgets(miscelZoneNode);
            
             dojo.forEach(data, function(miscelZoneCost) {
                 dojo.forEach(miscelZoneWidgets, function(el) {
                     
                     if(miscelZoneCost.zone.id == el.getZoneId()) {
                         el.zoneCost = (miscelZoneCost.cost*720).toFixed(2);
                         el.setCost();
                         el.costRate = currentMisceWidget.additionalProperties.description;
                         el.setCostRate();
                     }
                 });
             });          
         });         
         
         var miscelPane = dijit.byId("miscelZoneCostInfo");
         miscelPane.setAttribute("Title", currentMisceWidget.heading);
         miscelPane.setAttribute("open", "true");
          
       
        var miscelListCollection = dojo.query("#misceList .WizardListItem");
        
        dojo.forEach(miscelListCollection, function(el) {
            if (el.id == currentMisceWidget.id) {
                     currentMisceWidget.setSelectState(true, currentMisceWidget.id);
            } else {
                var otherWidget = dijit.byId(el.id);
                otherWidget.setSelectState(false, el.id);
            }
        });
     },
     cancel: function() {
         
         var currentMiscellaneousWidgetId = dojo.byId("currentMisscellaneousOfferId").value;
         var currentMiscellaneousWidget = dijit.byId(currentMiscellaneousWidgetId);
              
         currentMiscellaneousWidget.setSelectState(false, currentMiscellaneousWidget.id);         
        
         var zoneNode = dojo.byId("currentMisceZoneCostList");
         var zoneWidgets = dijit.registry.findWidgets(zoneNode);
         dojo.forEach(zoneWidgets, function(el) {
             el.clearWidgets();
         }); 
     }
 };


window.FogWizardConfig = FogWizardConfig;
window.FogSetupWizard = FogSetupWizard;
window.FogWizardZone = FogWizardZone;
window.FogWizardMiscellaneousCost = FogWizardMiscellaneousCost;
window.FogWizardIpManagement = FogWizardIpManagement;