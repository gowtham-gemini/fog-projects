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
    "dojo/currency",
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
    "List/ListItem"
], function(dojo, i18n, translator, dijit, JsonRest, registry, FilteringSelect, Select,ItemFileWriteStore, DataGrid, EnhancedGrid, HorizontalSlider, domConstruct, Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, connect, localeCurrency) {
    window.translator = translator;
    window.JsonRest = JsonRest;    
    window.Magnify = Magnify;
    window.registry = registry;
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
    window.localeCurrency = localeCurrency;
    window.connect = connect;
    window.currentRuleId = "";  
    window.compCount = 0;
    window.createVMCurrentZone = "";
    window.zoneTempRefId = "";
    window.isTierOptionEnabled = false;
    controller({ 
        name:"cloud",
        module: "user",
        filePath: "/js/app/cloud.js",
        layout: {
            name: "",
            containerId: "content"
        },
        scaffold: false
    },
    {   
        "index": action(function() {
            core.ui.loadTemplate("cloudMenuContainer", core.ui.getContentId());            
            allCountList.populateValues();            
        }),
        "storage": action(function() {                
            var currentStoragePage = dijit.byId("storageDeleteDisk");
            if(currentStoragePage) {
                dijit.byId("storageDeleteDisk").destroyRecursive();
                dijit.byId("storageDetachDisk").destroyRecursive();
                dijit.byId("storageConfirmSnapPage").destroyRecursive();
                dijit.byId("storageAttachDisk").destroyRecursive();
                dijit.byId("resizeDiskDialogue").destroyRecursive();
                dijit.byId("resizeVolumeLoader").destroyRecursive();
            } 
            core.ui.loadTemplate("storage", core.ui.getContentId()); 
            StorageInfo.init();
            StorageInfo.populateValue();
        }),            
        "addStorage" : action(function() {
            var currentStoragePage = dijit.byId("storageAddDiskForm");
            if(currentStoragePage) {
                dijit.byId("storageAddDiskForm").destroyRecursive();                
            } 
            if(dijit.byId("rateCardDialogue")) {
                dijit.byId("rateCardDialogue").destroyRecursive();  
            }                                                                    
            core.ui.loadTemplate("addDisk", core.ui.getContentId()); 
            AddStorageInfo.init();
            RateCardInfo.populateValues();  
            AddStorageInfo.populateValue();            
        }),
        "snapShot": action(function() {
            var currentSnapShotPage = dijit.byId("snapShotFrom");
            if(currentSnapShotPage) {
                dijit.byId("snapShotFrom").destroyRecursive();
                dijit.byId("createSnapShotPage").destroyRecursive();
                dijit.byId("createConformationSnapShotPage").destroyRecursive();
                dijit.byId("ConformationAutoSnapShotPage").destroyRecursive(); 
                dijit.byId("manualSnapshotAction").destroyRecursive();         
                dijit.byId("createStoragePage").destroyRecursive();
                dijit.byId("deleteSnapshotPage").destroyRecursive();
                dijit.byId("snapshotTempPage").destroyRecursive();
                dijit.byId("snapshotJobListPage").destroyRecursive();
            } 
            if(dijit.byId("rateCardDialogue")) {
                dijit.byId("rateCardDialogue").destroyRecursive();  
            }
            core.ui.loadTemplate("snapShot", core.ui.getContentId()); 
//            AddStorageInfo.init();
            SnapShotInfo.init();
            SnapShotInfo.populateValues();
        }),
        "vmSnapShot": action(function(vmId) {
            var accountListStore = new JsonRest({
                target: core.getContextPath()+"/api/account/currentAccount"         
            });
            if(dijit.byId("rateCardDialogue")) {
                dijit.byId("rateCardDialogue").destroyRecursive();  
            }
            accountListStore.query().then(function(data) {
                dojo.forEach(data, function(el) {  
                    if(el.accountType == "TRIAL") {
                        core.router.go("#/user/cloud/snapShot");
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotNotAvailable,"error");
                        registry.byId("userToaster").show();
                    } else {
                        var createVMSnapshotLoader = dijit.byId("createVMSnapshotLoader");
                        if(createVMSnapshotLoader) {
                            dijit.byId("createVMSnapshotLoader").destroyRecursive();
                            dijit.byId("addVMSnapshotDialog").destroyRecursive();
                            dijit.byId("deleteVMSnap").destroyRecursive();
                            dijit.byId("revertVMSnap").destroyRecursive();
                        } 
                    }
                });
                core.ui.loadTemplate("vmSnapshot", core.ui.getContentId()); 
//                AddStorageInfo.init();
                VMSnapshotInfo.init();
                VMSnapshotInfo.populateValues(vmId);
            });
        }),
        "userInstancePage": action(function() { 
            var currentInstancePage = dijit.byId("stopDialog");
            if(currentInstancePage) {
                currentInstancePage.destroyRecursive();
                dijit.byId("startDialog").destroyRecursive();
                dijit.byId("rebootDialog").destroyRecursive();
                dijit.byId("deleteDialog").destroyRecursive();
                dijit.byId("userVmActionForm").destroyRecursive();
                dijit.byId("attachIso").destroyRecursive();
            } 
            if(dijit.byId("vmRestoreDialog")) {
                dijit.byId("vmRestoreDialog").destroyRecursive();
            }
            core.ui.loadTemplate("userInstancePage", core.ui.getContentId()); 
            UserInstances.init();
            UserInstances.populateValues();
        }),
        "vmDetail" : action(function (id) {  
            var currentInstancePage = dijit.byId("changeServiceDialog");
            var isoList = dijit.byId("isoListWidget");
            if(currentInstancePage && isoList) {
                dijit.byId("showPasswordDialog").destroyRecursive();
                dijit.byId("instanceNetworkRead").destroyRecursive();
                dijit.byId("instanceNetworkWrite").destroyRecursive();
                dijit.byId("instanceCpuUsage").destroyRecursive();
                dijit.byId("isoListWidget").destroyRecursive();
                dijit.byId("changeServiceDialog").destroyRecursive();
                dijit.byId("startVMButton").destroyRecursive();
                dijit.byId("stop").destroyRecursive();
                dijit.byId("reboot").destroyRecursive();
                dijit.byId("changeHostNameDialog").destroyRecursive();
                dijit.byId("deleteVmDialog").destroyRecursive();
                dijit.byId("stopVmDialog").destroyRecursive();
                dijit.byId("changeSSHKeyDialog").destroyRecursive();
                dijit.byId("ipLoader").destroyRecursive();
                dijit.byId("acquireIpDialog").destroyRecursive();
                dijit.byId("currentVMSnapshotLoader").destroyRecursive();
                dijit.byId("currentVMSnapshotDialog").destroyRecursive();                                                                 
            } 
            if(dijit.byId("vmInfoRebootDialog")) {
                dijit.byId("vmInfoRebootDialog").destroyRecursive();  
            }
            if(dijit.byId("vmInfoStartDialog")) {
                dijit.byId("vmInfoStartDialog").destroyRecursive();
            }
            if(dijit.byId("vmInfoContentDialog")) {
                dijit.byId("vmInfoContentDialog").destroyRecursive();  
            }    
            if(dijit.byId("startVMButton")) {
                dijit.byId("startVMButton").destroyRecursive();  
            }    
            if(dijit.byId("nicAcquireIpDialog")) {
                dijit.byId("nicAcquireIpDialog").destroyRecursive();  
            }  
            if(dijit.byId("nicIpLoader")) {
                dijit.byId("nicIpLoader").destroyRecursive();  
            }  
            if(dijit.byId("nicReleaseIpDialog")) {
                dijit.byId("nicReleaseIpDialog").destroyRecursive();  
            }
                        
            if(dijit.byId("vmCreateSnapshot")) {
                dijit.byId("vmCreateSnapshot").destroyRecursive();
            }
            if(dijit.byId("addVolumeDialog")) {
                dijit.byId("addVolumeDialog").destroyRecursive();
            }  
            if(dijit.byId("addNetworkButton")) {
                dijit.byId("addNetworkButton").destroyRecursive();
            } 
            if(dijit.byId("nicProcessLoader")) {
                dijit.byId("nicProcessLoader").destroyRecursive();
            } 
             if(dijit.byId("vmInfoAddNetworkDialogue")) {
                dijit.byId("vmInfoAddNetworkDialogue").destroyRecursive();  
            }    
            if(dijit.byId("vmdefaultDialogue")) {
                dijit.byId("vmdefaultDialogue").destroyRecursive();  
            }    
            if(dijit.byId("vmNicDeleteDialogue")) {
                dijit.byId("vmNicDeleteDialogue").destroyRecursive();  
            } 
            if(dijit.byId("vmInfoattachDialog")) {
                dijit.byId("vmInfoattachDialog").destroyRecursive();  
            }
            if(dijit.byId("vmInfoResetDialog")) {
                dijit.byId("vmInfoResetDialog").destroyRecursive();  
            }                        
            if(dijit.byId("rateCardDialogue")) {
                dijit.byId("rateCardDialogue").destroyRecursive();  
            }   
            if(dijit.byId("vmSecondaryIPContentDialogue")) {
                dijit.byId("vmSecondaryIPContentDialogue").destroyRecursive();  
            }                           
            core.ui.loadTemplate("vmDetail", core.ui.getContentId()); 
            var collectionArray = id.split(",");
            var crrentId = collectionArray[0];              
            CurrentInstanceInfo.init();
            CurrentInstanceInfo.populateValues(crrentId);    
            RateCardInfo.populateValues();
            CurrentVMDetailInfo.populateValues(crrentId); 
//            if(collectionArray[1]) {
//                var currentVPC = dojo.byId("selectedANVPCID").value;
//                VPCMenuInfo.populateValues(currentVPC);
//            } else {
//                ZoneConfigForMenu.populateValue();
//            }
        }),
        "createVm" : action(function(identifiers) {            
            
            if(dijit.byId("createVMForm")) {
                dijit.byId("createVMForm").destroyRecursive();                                            
            }  
            if(dijit.byId("vmSummaryPage")) {
                dijit.byId("vmSummaryPage").destroyRecursive(); 
            }
            
            if(dijit.byId("createVMLoading")) {               
                dijit.byId("createVMLoading").destroyRecursive();          
            }
            if(dijit.byId("createTierVMLoadingDialog")) {               
                dijit.byId("createTierVMLoadingDialog").destroyRecursive();          
            }
            
            if(dijit.byId("rateCardDialogue")) {
                dijit.byId("rateCardDialogue").destroyRecursive();  
            }   
            if(dijit.byId("ceateVMNoNetworkDialogue")) {
                dijit.byId("ceateVMNoNetworkDialogue").destroyRecursive();  
            }   
            
             var accountResourceLimitStore = new JsonRest({
                target: core.getContextPath()+"/api/account/getCloudResourceStat"
            });
            zoneTempRefId = "";
            var currentIdArray = "";            
            accountResourceLimitStore.query().then(function(data) {
                 dojo.forEach(data, function(limitData) {  
                    if(limitData.vmLimit !== "-1" || limitData.cpuLimit !== "-1" || limitData.memoryLimit !== "-1" || limitData.primaryStorageLimit !== "-1" || limitData.storageLimit !== "-1" ) {
                        if(((Number(limitData.vmUsed) >= Number(limitData.vmLimit)) && (limitData.vmLimit !== "-1")) || ((Number(limitData.cpuUsed) >= Number(limitData.cpuLimit)) && (limitData.cpuLimit !== "-1")) || ((Number(limitData.memoryUsed) >= Number(limitData.memoryLimit)) && (limitData.memoryLimit !== "-1")) || ((Number(limitData.primaryStorageLimitUsage) >= Number(limitData.primaryStorageLimit)) && (limitData.primaryStorageLimit !== "-1")) || ((Number(limitData.storageUsed) >= Number(limitData.storageLimit)) && (limitData.storageLimit !== "-1"))) {                     
                            registry.byId("userToaster").setContent(translator.common.limitReachedMsg,"error");
                            registry.byId("userToaster").show();
                            core.router.go("#/user/home"); 
                            return;
                        } else {                            
                            core.ui.loadTemplate("createVm", core.ui.getContentId());                
                            if(identifiers) {
                                currentIdArray = identifiers.split(",");
                                var id = currentIdArray[0];               
                                var zoneId = currentIdArray[1];
                                zoneTempRefId = currentIdArray[1];
                                if(currentIdArray[2]) {
                                    isTierOptionEnabled = true;
                                    dojo.byId("currentTierId").value = id;
                                    dojo.query("#vmTemplateName").removeClass("template_text", true);                    
                                    dojo.byId("vmTemplateName").innerHTML = "";                           
                                    var currentVPC = dojo.byId("selectedANVPCID").value;
                                    VPCMenuInfo.populateValues(currentVPC);
                                } else {
                                    dojo.query("#vmTemplateName").toggleClass("template_text", true);                    
                                    isTierOptionEnabled = false;
                                    dojo.byId("currentTierId").value = "";
                                    ZoneConfigForMenu.populateValue();
                                }                        
                            } else {                
                                zoneTempRefId = ""
                                isTierOptionEnabled = false;
                                dojo.byId("currentTierId").value = "";
                                dojo.query("#vmTemplateName").removeClass("template_text", true);
                            }                        
                            var configRestStoreBilling = new JsonRest({
                                target: core.getContextPath()+"/api/config/"
                            });            
                            configRestStoreBilling.query().then(function(resultData) {
                                dojo.forEach(resultData, function(config) { 
                                    if(config.name == "monthly.billing.enabled") {
                                        if(config.value == "true" || config.value == true) {
                                            dojo.byId("billingTypeDiv").style.display = "block";
                                        } else {
                                            dojo.byId("billingTypeDiv").style.display = "none";
                                        }
                                    } 
                                });
                            });                        
                            var accountListStore = new JsonRest({
                                target: core.getContextPath()+"/api/account/currentAccount"         
                            });                         
                            var configRestStore = new JsonRest({
                                target: core.getContextPath()+"/api/config/"
                            });

                            var cardEnabled;
                            var vmCardEnabled;
                            var signupCardEnabled;            

                            configRestStore.query().then(function(resultData) {                     
                                dojo.forEach(resultData, function(config) { 
                                    if(config.name == "signup.card.verification.enabled") {
                                        if(config.value == "true" || config.value == true) {
                                            signupCardEnabled = "true";
                                        } else {
                                            signupCardEnabled = "false";
                                        }        
                                    } else if(config.name == "creditcard.processing") {
                                        if(config.value == "true" || config.value == true) {
                                            cardEnabled = "true";
                                        } else {
                                            cardEnabled = "false";
                                        }        
                                    } else if(config.name == "creditcard.processing.in.createvm") {
                                        if(config.value == "true" || config.value == true) {
                                            vmCardEnabled = "true";
                                        } else {
                                            vmCardEnabled = "false";
                                        }        
                                    }  
                                });
                                if(cardEnabled == "true") {
                                    accountListStore.query().then(function(data) {
                                        dojo.forEach(data, function(el) {
                                            if(el.accountType == "RETAIL" && el.cardVerified == false && (signupCardEnabled == "false" || signupCardEnabled == "true")) {                                
                                                registry.byId("userToaster").setContent(translator.common.billing.cardNotVerified,"error");
                                                registry.byId("userToaster").show();
                                                core.router.go("#/user/billing/makePayment"); 
                                            } else if(el.accountType == "TRIAL") {                                     
                                                var accountResourceLimitStore = new JsonRest({
                                                    target: core.getContextPath()+"/api/account/resourceLimit"
                                                });  
                                                accountResourceLimitStore.query().then(function(data) {
                                                    dojo.forEach(data, function(limitData) {  
                                                        if(limitData.accountType == "TRIAL") {
                                                            if((limitData.volumeCount >= limitData.storageLimitNo) || (limitData.vmCount >= limitData.vmLimitNo)) {
                                                                core.router.go("#/user/cloud/userInstancePage");
                                                                registry.byId("userToaster").setContent(translator.common.message.trialAccountResource,"error");
                                                                registry.byId("userToaster").show();
                                                            } else {
                                                                CreateVMInfo.init();
                                                                CreateVMInfo.populateValues();       
                                                                RateCardInfo.populateValues();
                                                                if(identifiers) { 
                                                                    if(identifiers) {                                         
                                                                        if(currentIdArray[2]) {                                            
                                                                            CreateVMInfo.populateTierValue(id, zoneId);
                                                                            RateCardInfo.populateValues();
                                                                            isTierOptionEnabled = true;                                       
                                                                        } else {
                                                                            CreateVMInfo.populateTemplateValue(id, zoneId);
                                                                            RateCardInfo.populateValues();
                                                                            isTierOptionEnabled = false;

                                                                        }                                        
                                                                    }                                                                                                                                                             
                                                                } 
                                                            }
                                                        } 
                                                    });
                                                });
                                            } else if(el.accountType == "RETAIL" && el.cardVerified == true) {  
                                                if(el.maxCreditLimit == 0 && el.totalAmount >= 0) {
                                                    registry.byId("userToaster").setContent(translator.common.prepaidPaymentMessage,"error");
                                                    registry.byId("userToaster").show();
                                                    core.router.go("#/user/billing/payment"); 
                                                } else {
                                                    CreateVMInfo.init();
                                                    CreateVMInfo.populateValues();   
                                                    RateCardInfo.populateValues();
                                                    if(identifiers) {                                         
                                                        if(currentIdArray[2]) {                                            
                                                            CreateVMInfo.populateTierValue(id, zoneId);
                                                            RateCardInfo.populateValues();
                                                            isTierOptionEnabled = true;                                            
                                                        } else {
                                                            CreateVMInfo.populateTemplateValue(id, zoneId);
                                                            RateCardInfo.populateValues();
                                                            isTierOptionEnabled = false;                                                            

                                                        }                                        
                                                    } 
                                                }                       
                                            }
                                        });
                                    });
                                } else {
                                    accountListStore.query().then(function(data) {
                                        dojo.forEach(data, function(el) {
                                            if(el.accountType == "RETAIL") {   
                                                CreateVMInfo.init();
                                                CreateVMInfo.populateValues();   
                                                RateCardInfo.populateValues();                               
                                                if(identifiers) {                                         
                                                    if(currentIdArray[2]) {                                            
                                                        CreateVMInfo.populateTierValue(id, zoneId);
                                                        RateCardInfo.populateValues();
                                                        isTierOptionEnabled = true;                                     
                                                    } else {
                                                        CreateVMInfo.populateTemplateValue(id, zoneId);
                                                        RateCardInfo.populateValues();
                                                        isTierOptionEnabled = false;
                                                    }                                        
                                                }                                                                                                        
                                            } else if(el.accountType == "TRIAL") {
                                                var accountResourceLimitStore = new JsonRest({
                                                    target: core.getContextPath()+"/api/account/resourceLimit"
                                                });
                                                accountResourceLimitStore.query().then(function(data) {
                                                    dojo.forEach(data, function(limitData) {  
                                                        if(limitData.accountType == "TRIAL") {
                                                            if((limitData.volumeCount >= limitData.storageLimitNo) || (limitData.vmCount >= limitData.vmLimitNo)) {
                                                                core.router.go("#/user/cloud/userInstancePage");
                                                                registry.byId("userToaster").setContent(translator.common.message.trialAccountResource,"error");
                                                                registry.byId("userToaster").show();
                                                            } else {
                                                                CreateVMInfo.init();
                                                                CreateVMInfo.populateValues();         
                                                                RateCardInfo.populateValues();
                                                                if(identifiers) {                                         
                                                                    if(currentIdArray[2]) {                                            
                                                                        CreateVMInfo.populateTierValue(id, zoneId);
                                                                        RateCardInfo.populateValues();
                                                                        isTierOptionEnabled = true;                                     
                                                                    } else {
                                                                        CreateVMInfo.populateTemplateValue(id, zoneId);
                                                                        RateCardInfo.populateValues();
                                                                        isTierOptionEnabled = false;
                                                                    }                                        
                                                                } 
                                                            }
                                                        } 
                                                    });
                                                });
                                            } 
                                        });
                                    });
                                }
                            });
                        }
                    } else {                        
                        core.ui.loadTemplate("createVm", core.ui.getContentId());                                        
                        if(identifiers) {
                            currentIdArray = identifiers.split(",");
                            var id = currentIdArray[0];               
                            var zoneId = currentIdArray[1];
                            zoneTempRefId = currentIdArray[1];
                            if(currentIdArray[2]) {
                                isTierOptionEnabled = true;
                                dojo.byId("currentTierId").value = id;
                                dojo.query("#vmTemplateName").removeClass("template_text", true);                    
                                dojo.byId("vmTemplateName").innerHTML = "";                           
                                var currentVPC = dojo.byId("selectedANVPCID").value;
                                VPCMenuInfo.populateValues(currentVPC);
                            } else {
                                dojo.query("#vmTemplateName").toggleClass("template_text", true);                    
                                isTierOptionEnabled = false;
                                dojo.byId("currentTierId").value = "";
                                ZoneConfigForMenu.populateValue();
                            }                        
                        } else {                
                            zoneTempRefId = ""
                            isTierOptionEnabled = false;
                            dojo.byId("currentTierId").value = "";
                            dojo.query("#vmTemplateName").removeClass("template_text", true);
                        }                        
                        var configRestStoreBilling = new JsonRest({
                            target: core.getContextPath()+"/api/config/"
                        });            
                        configRestStoreBilling.query().then(function(resultData) {
                            dojo.forEach(resultData, function(config) { 
                                if(config.name == "monthly.billing.enabled") {
                                    if(config.value == "true" || config.value == true) {
                                        dojo.byId("billingTypeDiv").style.display = "block";
                                    } else {
                                        dojo.byId("billingTypeDiv").style.display = "none";
                                    }
                                } 
                            });
                        });                        
                        var accountListStore = new JsonRest({
                            target: core.getContextPath()+"/api/account/currentAccount"         
                        });                         
                        var configRestStore = new JsonRest({
                            target: core.getContextPath()+"/api/config/"
                        });

                        var cardEnabled;
                        var vmCardEnabled;
                        var signupCardEnabled;            

                        configRestStore.query().then(function(resultData) {                     
                            dojo.forEach(resultData, function(config) { 
                                if(config.name == "signup.card.verification.enabled") {
                                    if(config.value == "true" || config.value == true) {
                                        signupCardEnabled = "true";
                                    } else {
                                        signupCardEnabled = "false";
                                    }        
                                } else if(config.name == "creditcard.processing") {
                                    if(config.value == "true" || config.value == true) {
                                        cardEnabled = "true";
                                    } else {
                                        cardEnabled = "false";
                                    }        
                                } else if(config.name == "creditcard.processing.in.createvm") {
                                    if(config.value == "true" || config.value == true) {
                                        vmCardEnabled = "true";
                                    } else {
                                        vmCardEnabled = "false";
                                    }        
                                }  
                            });
                            if(cardEnabled == "true") {
                                accountListStore.query().then(function(data) {
                                    dojo.forEach(data, function(el) {
                                        if(el.accountType == "RETAIL" && el.cardVerified == false && (signupCardEnabled == "false" || signupCardEnabled == "true")) {                                
                                            registry.byId("userToaster").setContent(translator.common.billing.cardNotVerified,"error");
                                            registry.byId("userToaster").show();
                                            core.router.go("#/user/billing/makePayment"); 
                                        } else if(el.accountType == "TRIAL") {                                     
                                            var accountResourceLimitStore = new JsonRest({
                                                target: core.getContextPath()+"/api/account/resourceLimit"
                                            });  
                                            accountResourceLimitStore.query().then(function(data) {
                                                dojo.forEach(data, function(limitData) {  
                                                    if(limitData.accountType == "TRIAL") {
                                                        if((limitData.volumeCount >= limitData.storageLimitNo) || (limitData.vmCount >= limitData.vmLimitNo)) {
                                                            core.router.go("#/user/cloud/userInstancePage");
                                                            registry.byId("userToaster").setContent(translator.common.message.trialAccountResource,"error");
                                                            registry.byId("userToaster").show();
                                                        } else {
                                                            CreateVMInfo.init();
                                                            CreateVMInfo.populateValues();       
                                                            RateCardInfo.populateValues();
                                                            if(identifiers) { 
                                                                if(identifiers) {                                         
                                                                    if(currentIdArray[2]) {                                            
                                                                        CreateVMInfo.populateTierValue(id, zoneId);
                                                                        RateCardInfo.populateValues();
                                                                        isTierOptionEnabled = true;                                       
                                                                    } else {
                                                                        CreateVMInfo.populateTemplateValue(id, zoneId);
                                                                        RateCardInfo.populateValues();
                                                                        isTierOptionEnabled = false;

                                                                    }                                        
                                                                }                                                                                                                                                             
                                                            } 
                                                        }
                                                    } 
                                                });
                                            });
                                        } else if(el.accountType == "RETAIL" && el.cardVerified == true) {  
                                            if(el.maxCreditLimit == 0 && el.totalAmount >= 0) {
                                                registry.byId("userToaster").setContent(translator.common.prepaidPaymentMessage,"error");
                                                registry.byId("userToaster").show();
                                                core.router.go("#/user/billing/payment"); 
                                            } else {
                                                CreateVMInfo.init();
                                                CreateVMInfo.populateValues();   
                                                RateCardInfo.populateValues();
                                                if(identifiers) {                                         
                                                    if(currentIdArray[2]) {                                            
                                                        CreateVMInfo.populateTierValue(id, zoneId);
                                                        RateCardInfo.populateValues();
                                                        isTierOptionEnabled = true;                                            
                                                    } else {
                                                        CreateVMInfo.populateTemplateValue(id, zoneId);
                                                        RateCardInfo.populateValues();
                                                        isTierOptionEnabled = false;                                                            

                                                    }                                        
                                                } 
                                            }                       
                                        }
                                    });
                                });
                            } else {
                                accountListStore.query().then(function(data) {
                                    dojo.forEach(data, function(el) {
                                        if(el.accountType == "RETAIL") {   
                                            CreateVMInfo.init();
                                            CreateVMInfo.populateValues();   
                                            RateCardInfo.populateValues();                               
                                            if(identifiers) {                                         
                                                if(currentIdArray[2]) {                                            
                                                    CreateVMInfo.populateTierValue(id, zoneId);
                                                    RateCardInfo.populateValues();
                                                    isTierOptionEnabled = true;                                     
                                                } else {
                                                    CreateVMInfo.populateTemplateValue(id, zoneId);
                                                    RateCardInfo.populateValues();
                                                    isTierOptionEnabled = false;
                                                }                                        
                                            }                                                                                                        
                                        } else if(el.accountType == "TRIAL") {
                                            var accountResourceLimitStore = new JsonRest({
                                                target: core.getContextPath()+"/api/account/resourceLimit"
                                            });
                                            accountResourceLimitStore.query().then(function(data) {
                                                dojo.forEach(data, function(limitData) {  
                                                    if(limitData.accountType == "TRIAL") {
                                                        if((limitData.volumeCount >= limitData.storageLimitNo) || (limitData.vmCount >= limitData.vmLimitNo)) {
                                                            core.router.go("#/user/cloud/userInstancePage");
                                                            registry.byId("userToaster").setContent(translator.common.message.trialAccountResource,"error");
                                                            registry.byId("userToaster").show();
                                                        } else {
                                                            CreateVMInfo.init();
                                                            CreateVMInfo.populateValues();         
                                                            RateCardInfo.populateValues();
                                                            if(identifiers) {                                         
                                                                if(currentIdArray[2]) {                                            
                                                                    CreateVMInfo.populateTierValue(id, zoneId);
                                                                    RateCardInfo.populateValues();
                                                                    isTierOptionEnabled = true;                                     
                                                                } else {
                                                                    CreateVMInfo.populateTemplateValue(id, zoneId);
                                                                    RateCardInfo.populateValues();
                                                                    isTierOptionEnabled = false;
                                                                }                                        
                                                            } 
                                                        }
                                                    } 
                                                });
                                            });
                                        } 
                                    });
                                });
                            }
                        });
                    }
                 });
             });                                                
        }),
        "firewall" : action(function() {
            var currentPage = dijit.byId("accountTabContainer");
            if(currentPage) {
                dijit.byId("accountTabContainer").destroyRecursive();
            }
            if(dijit.byId("serviceTooltipDialogue")) {
                dijit.byId("serviceTooltipDialogue").destroyRecursive();
            } 
            if(dijit.byId("serviceTooltipDialogue")) {
                dijit.byId("serviceTooltipDialogue").destroyRecursive();
            }
            core.ui.loadTemplate("firewall", core.ui.getContentId()); 
            UserSecurityGroupInfo.init();
            UserSecurityGroupInfo.populateValues();                    
        }),
        "ingress" : action(function(id) {     
            var currentPage = dijit.byId("userFirewallIngressForm");
            if(currentPage) {
                dijit.byId("userFirewallIngressForm").destroyRecursive();
            }                                       
            core.ui.loadTemplate("ingressRulePage", core.ui.getContentId());             
            if (id == "undefined" || id == undefined) {                       
                UserSecurityIngressInfo.init(currentRuleId);
                UserSecurityIngressInfo.populateValues(currentRuleId); 
            } else if(id){                        
                currentRuleId = id;
                UserSecurityIngressInfo.init(id);
                UserSecurityIngressInfo.populateValues(id); 
            } else if(currentRuleId == "" || currentRuleId == undefined || currentRuleId == "undefined") {
                core.router.go("#/user/cloud/firewall");
            }         
        }),
        "egress" : action(function() {
            var currentPage = dijit.byId("securityGroupEgressRuleForm");
            if(currentPage) {
                dijit.byId("securityGroupEgressRuleForm").destroyRecursive();
            }
            if(currentRuleId == "" || currentRuleId == undefined || currentRuleId == "undefined") {
                core.router.go("#/user/cloud/firewall");
            }
            core.ui.loadTemplate("egressRulePage", core.ui.getContentId()); 
            UserSecurityEgressInfo.init(currentRuleId);
            UserSecurityEgressInfo.populateValues(currentRuleId); 
        })
    });
}); 

var RateCardInfo = {
    populateValues : function () {
        var zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        var offerListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/rateCard"
        });
        if(dijit.byId("rateCardZone")) {
            dijit.byId("rateCardZone").destroyRecursive();
        }
        if(dijit.byId("rateCardGrid")) {                                    
            dijit.byId("rateCardGrid").destroyRecursive();                    
        }
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: [{id: "All", name: translator.common.allZone}]
        };        
        var zoneList = new ItemFileWriteStore({data: zoneOption});
        zoneRestStore.query().then(function (data) {
            dojo.forEach(data, function (el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });
        var rateCardZoneWidget = new Select({            
            id: "rateCardZone",            
            sortByLabel: false,
            store: zoneList,            
            maxHeight: 100,
            onChange: function() { 
              RateCardInfo.loadRateCardByZone(this);                          
            }
        }).placeAt("rateCardZoneList");                 
        var rateCardGridData = {
            items: []
        }; 
        var rateCardList = new ItemFileWriteStore({data: rateCardGridData}); 
        var rateCardLayout = [
            [                   
                {'name': translator.common.name, 'field': 'name', 'width': '400px', 'datatype':'string',  'autoComplete': 'true', "formatter": function (data) {
                        var response = data.split(",");
                        var result = "";
                        if(response[1]) {
                            result = "<span class='header_text_rteCrd'>" + response[0] + "</span>";
                        } else {
                            result = response[0]
                        }
                        return  result;   
                }},
                {'name': translator.common.cost + " ("+ core.getCurrency() +")", 'field': 'cost', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', "formatter": function (costData) {                        
                      var response = costData.split(",");
                      var result = "";
                      if(response[1]) {
                          result = "<span class='header_text_rteCrd'>" + response[0] + "</span>";
                      } else {
                          result = response[0]
                      }
                      return  result; 
                }},
                {'name': translator.common.unit, 'field': 'unit', 'width': '118px', 'datatype':'string',  'autoComplete': 'true', "formatter": function (unitData) {                       
                        var response = unitData.split(",");
                        var result = "";
                        if(response[1]) {
                            result = "<span class='header_text_rteCrd'>" + response[0] + "</span>"
                        } else {
                            result = response[0]
                        }
                        return  result;   
                }}                
            ]
         ];
         
         offerListRestStore.query().then(function(data) {
             if(data[0].ComputOfferInfo.length != 0) {
                 rateCardList.newItem({ id: 1, name: translator.common.computingOffers+ "," + "1", cost:  translator.common.cost + "," + "1", unit: translator.common.unit + "," + "1"});                 
                 dojo.forEach(data[0].ComputOfferInfo, function(el) {  
                     dojo.forEach(el.cost, function (offerCost) {
                         rateCardList.newItem({
                             id:el.id,
                             name:el.name,
                             cost: offerCost.cost.toString(),
                             unit: el.unit
                         });                         
                     });                     
                 });
             }
             
             if(data[0].diskOfferInfo.length != 0) {
                 rateCardList.newItem({ id: 2, name: translator.common.diskOffers + "," + "2", cost: translator.common.cost + "," + "2", unit: translator.common.unit + "," + "2"});
                 dojo.forEach(data[0].diskOfferInfo, function(el) {
                     dojo.forEach(el.cost, function (diskCost) {
                        rateCardList.newItem({
                            id:el.id,
                            name:el.name,
                            cost: diskCost.cost.toString(),
                            unit: el.unit
                        }); 
                     });                     
                 });
             }
             if(data[0].templateInfo.length !=0 ) { 
                 rateCardList.newItem({id: 3 , name: translator.common.templates+ "," + "3", cost: translator.common.cost + "," + "3", unit: translator.common.unit + "," + "3"});
                 dojo.forEach(data[0].templateInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 });
             }
             
             if(data[0].bandwidthMisceInfo != 0) {
                 rateCardList.newItem({ id: 4, name: translator.common.bandwidthMiscInfo+ "," + "4", cost: translator.common.cost + "," + "4", unit: translator.common.unit + "," + "4"});
                 dojo.forEach(data[0].bandwidthMisceInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 });
             }
             
             if(data[0].ipMisceInfo.length != 0) {
                 rateCardList.newItem({ id: 5, name: translator.common.ipMiscInfo+ "," + "5", cost: translator.common.cost + "," + "5", unit: translator.common.unit + "," + "5"});
                 dojo.forEach(data[0].ipMisceInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 });
             }
             
             if(data[0].snapshotMiscInfo.length != 0) {
                 rateCardList.newItem({ id: 6, name: translator.common.snapshotMiscInfo+ "," + "6", cost: translator.common.cost + "," + "6", unit: translator.common.unit + "," + "6"});
                 dojo.forEach(data[0].snapshotMiscInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 });
             }
             if(data[0].loadBalancerMiscInfo.length != 0) {
                 rateCardList.newItem({ id: 7, name: translator.common.loadBalancer+ "," + "7", cost: translator.common.cost + "," + "7", unit: translator.common.unit + "," + "7"});
                 dojo.forEach(data[0].loadBalancerMiscInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: translator.common.CostPerPolicyPerMonth
                     });
                 });
             }
             
             if(data[0].portForwardMiscInfo.length != 0) {
                 rateCardList.newItem({ id: 8, name: translator.common.portForward+ "," + "8", cost: translator.common.cost + "," + "8", unit: translator.common.unit + "," + "8"});
                 dojo.forEach(data[0].portForwardMiscInfo, function(el) { 
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: translator.common.CostPerRulePerMonth
                     });
                 });
             }
                  
             if(data[0].vmSnapshotMiscInfo.length != 0) { 
                 rateCardList.newItem({ id: 9, name: translator.common.vmSnapshotMiscInfo+ "," + "9", cost: translator.common.cost + "," + "9", unit: translator.common.unit + "," + "9"});
                 dojo.forEach(data[0].vmSnapshotMiscInfo, function(el) {
                     rateCardList.newItem({
                         id: el.id,
                         name: el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 }); 
             } 
         });
          var rateCardGrid = new DataGrid({
            id: 'rateCardGrid',
            store: rateCardList,
            "class": "span12",
            structure: rateCardLayout,                   
            autoHeight: true            
        });
        rateCardGrid.placeAt("offeringList");
        rateCardGrid.startup();   
    },
    loadRateCardByZone : function (currentZone) {
        var rateCardGridData = {
            items: []
        }; 
        var rateCardList = new ItemFileWriteStore({data: rateCardGridData}); 
        var offerListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/rateCard"
        });
        
        offerListRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
             if(data[0].ComputOfferInfo.length != 0) {
                 rateCardList.newItem({ id: 1, name: translator.common.computingOffers+ "," + "1", cost:  translator.common.cost + "," + "1", unit: translator.common.unit + "," + "1"});                 
                 dojo.forEach(data[0].ComputOfferInfo, function(comuptData) {     
                     dojo.forEach(comuptData.cost, function (offerCost) {      
                         if(currentZone.value === "All" || currentZone.value === "") {
                             rateCardList.newItem({
                                 id:comuptData.id,
                                 name:comuptData.name,
                                 cost: offerCost.cost.toString(),
                                 unit: comuptData.unit
                             });
                         } else {
                             if(offerCost.zoneReferenceId === currentZone.value) {
                                 rateCardList.newItem({
                                     id:comuptData.id,
                                     name:comuptData.name,
                                     cost: offerCost.cost.toString(),
                                     unit: comuptData.unit
                                 });
                             }
                         }                                                   
                     });
                 });
             }
             
             if(data[0].diskOfferInfo.length != 0) {
                 rateCardList.newItem({ id: 2, name: translator.common.diskOffers + "," + "2", cost: translator.common.cost + "," + "2", unit: translator.common.unit + "," + "2"});
                 dojo.forEach(data[0].diskOfferInfo, function(el) {
                     dojo.forEach(el.cost, function (diskCost) {    
                         if(currentZone.value === "All" || currentZone.value === "") {
                             rateCardList.newItem({
                                 id:el.id,
                                 name:el.name,
                                 cost: diskCost.cost.toString(),
                                 unit: el.unit
                             });
                         } else {
                             if(diskCost.zoneReferenceId === currentZone.value) {
                                 rateCardList.newItem({
                                     id:el.id,
                                     name:el.name,
                                     cost: diskCost.cost.toString(),
                                     unit: el.unit
                                 });
                             }
                         } 
                         
                     });                     
                 });
             }
             if(data[0].templateInfo.length !=0 ) { 
                 rateCardList.newItem({id: 3 , name: translator.common.templates+ "," + "3", cost: translator.common.cost + "," + "3", unit: translator.common.unit + "," + "3"});
                 dojo.forEach(data[0].templateInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 });
             }
             
             if(data[0].bandwidthMisceInfo != 0) {
                 rateCardList.newItem({ id: 4, name: translator.common.bandwidthMiscInfo+ "," + "4", cost: translator.common.cost + "," + "4", unit: translator.common.unit + "," + "4"});
                 dojo.forEach(data[0].bandwidthMisceInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 });
             }
             
             if(data[0].ipMisceInfo.length != 0) {
                 rateCardList.newItem({ id: 5, name: translator.common.ipMiscInfo+ "," + "5", cost: translator.common.cost + "," + "5", unit: translator.common.unit + "," + "5"});
                 dojo.forEach(data[0].ipMisceInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 });
             }
             
             if(data[0].snapshotMiscInfo.length != 0) {
                 rateCardList.newItem({ id: 6, name: translator.common.snapshotMiscInfo+ "," + "6", cost: translator.common.cost + "," + "6", unit: translator.common.unit + "," + "6"});
                 dojo.forEach(data[0].snapshotMiscInfo, function(el) {
                     rateCardList.newItem({
                         id:el.id,
                         name:el.name + "_" + el.zone,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 });
             }
//             if(data[0].networkOfferInfo.length != 0) { 
//                 rateCardList.newItem({ id: 8, name: translator.common.networkOffer+ "," + "8", cost: translator.common.cost + "," + "8", unit: translator.common.unit + "," + "8"});
//                 dojo.forEach(data[0].networkOfferInfo, function(el) {
//                     rateCardList.newItem({
//                         id:el.id,
//                         name:el.name,
//                         cost: el.cost.toString(),
//                         unit: el.unit
//                     });
//                 }); 
//             }  
             if(data[0].vmSnapshotMiscInfo.length != 0) { 
                 rateCardList.newItem({ id: 9, name: translator.common.vmSnapshotMiscInfo+ "," + "9", cost: translator.common.cost + "," + "8", unit: translator.common.unit + "," + "8"});
                 dojo.forEach(data[0].vmSnapshotMiscInfo, function(el) {
                     rateCardList.newItem({
                         id: el.id,
                         name: el.name,
                         cost: el.cost.toString(),
                         unit: el.unit
                     });
                 }); 
             }
             dijit.byId("rateCardGrid").setStore(rateCardList);
         });         
    },
    showRateCardDialogue : function () {
        dijit.byId("rateCardDialogue").show();
    }
}
var allCountList = {   
    populateValues : function () {
        var instanceCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/count"
        });
        
        var zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        
        var volumeCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/count"
        }); 
        
        var snapShotCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/count"
        }); 
        var currentZoneID = dojo.byId("selectedANZoneID").value;
        var currentZone = "";
        var zoneId;
        if (currentZoneID === null || currentZoneID === "") {
            zoneId = "All";
        } else {
            zoneId = currentZoneID;
        }
        var networkCount = new JsonRest({
            target: core.getContextPath() + "/api/network/count"
        });
        networkCount.query({zoneReferenceId: zoneId}).then(function(data) {
            if(data.length != 0 || data[0].total != undefined || data != undefined) {
                dojo.forEach(data, function(el) {                     
                    dojo.byId("totalNetwork").innerHTML = el.total;                                  
                });
            } else {
                dojo.byId("totalNetwork").innerHTML = 0;        
            } 
        });         
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            if(isAdvanceZoneAviliable === true && isBasicZoneAvailable === true) {
                //condition true for all
                dojo.query("#firewallContainer").removeClass("hide_text", true);
                dojo.query("#userHealthMenuContainer").removeClass("no_left_magin", true);                
                dojo.query("#networkContainer").removeClass("hide_text", true);
                dojo.query("#sshKeyContainer").toggleClass("no_left_magin", true);
                
            } else if(isAdvanceZoneAviliable === true && isBasicZoneAvailable === false) {
                // condition true for advance
                dojo.query("#firewallContainer").toggleClass("hide_text", true);
                dojo.query("#userHealthMenuContainer").toggleClass("no_left_magin", true);                
                dojo.query("#networkContainer").removeClass("hide_text", true);   
                dojo.query("#sshKeyContainer").removeClass("no_left_magin", true);
            } else if(isAdvanceZoneAviliable === false && isBasicZoneAvailable === true) {
                //condition true for basic
                dojo.query("#firewallContainer").removeClass("hide_text", true);
                dojo.query("#networkContainer").toggleClass("hide_text", true);
                dojo.query("#userHealthMenuContainer").removeClass("no_left_magin", true); 
                dojo.query("#sshKeyContainer").removeClass("no_left_magin", true);
            } else {
                // none other
                dojo.query("#firewallContainer").removeClass("hide_text", true);
                dojo.query("#userHealthMenuContainer").removeClass("no_left_magin", true);                
                dojo.query("#networkContainer").removeClass("hide_text", true);
                dojo.query("#sshKeyContainer").toggleClass("no_left_magin", true);
            }            
        } else {
            zoneRestStore.get(currentZoneID).then(function (data) {                
                if(data.networkType == "Advanced") {
                    dojo.query("#firewallContainer").toggleClass("hide_text", true);
                    dojo.query("#userHealthMenuContainer").toggleClass("no_left_magin", true);        
                    dojo.query("#networkContainer").removeClass("hide_text", true);
                    dojo.query("#sshKeyContainer").removeClass("no_left_magin", true);
                } else if(data.networkType == "Basic") {
                    dojo.query("#firewallContainer").removeClass("hide_text", true);
                    dojo.query("#userHealthMenuContainer").removeClass("no_left_magin", true);        
                    dojo.query("#networkContainer").toggleClass("hide_text", true);
                    dojo.query("#sshKeyContainer").removeClass("no_left_magin", true);
                } else {
                    dojo.query("#userHealthMenuContainer").removeClass("no_left_magin", true);        
                    dojo.query("#firewallContainer").removeClass("hide_text", true);
                    dojo.query("#networkContainer").removeClass("hide_text", true);
                    dojo.query("#sshKeyContainer").removeClass("no_left_magin", true);
                }
            })
        } 
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            instanceCountRestStore.query().then(function(data) {
                if(data.length != 0 || data[0].totalVms != undefined || data != undefined) {
                    dojo.forEach(data, function(el) {                     
                        dojo.byId("totalVMs").innerHTML = el.destroyedVms + el.stoppedVms + el.runningVms;                                  
                    });
                } else {
                    dojo.byId("totalVMs").innerHTML = 0;        
                }            
            });                
            volumeCountRestStore.query().then(function(data) {
                if(data.length != 0 || data[0].totalVms != undefined || data != undefined) {
                    dojo.forEach(data, function(el) {
                        dojo.byId("totalStorage").innerHTML = el.totalStorage;                
                    });
                } else {
                    dojo.byId("totalStorage").innerHTML = 0;
                }
            });                
            snapShotCountRestStore.query().then(function(data) {
                if(data.length != 0 || data[0].totalVms != undefined || data != undefined) {
                    dojo.forEach(data, function(el) {
                        dojo.byId("snapshotTotalCount").innerHTML = el.totalCount;                    
                    });                
                }            
            });  
            
        } else {
            currentZone = currentZoneID
            instanceCountRestStore.query({zoneReferenceId: currentZone}).then(function(data) {
                if(data.length != 0 || data[0].totalVms != undefined || data != undefined) {
                    dojo.forEach(data, function(el) {                     
                        dojo.byId("totalVMs").innerHTML =  el.destroyedVms + el.stoppedVms + el.runningVms;                                         
                    });
                } else {
                    dojo.byId("totalVMs").innerHTML = 0;        
                }            
            }); 
            
            volumeCountRestStore.query({zoneReferenceId: currentZone}).then(function(data) {
                if(data.length != 0 || data[0].totalVms != undefined || data != undefined) {
                    dojo.forEach(data, function(el) {
                        dojo.byId("totalStorage").innerHTML = el.totalStorage;                
                    });
                } else {
                    dojo.byId("totalStorage").innerHTML = 0;
                }
            });
            
            snapShotCountRestStore.query({zoneReferenceId: currentZone}).then(function(data) {
                if(data.length != 0 || data[0].totalVms != undefined || data != undefined) {
                    dojo.forEach(data, function(el) {
                        dojo.byId("snapshotTotalCount").innerHTML = el.totalCount;                    
                    });                
                }            
            }); 
        }             
    }
    };
var CurrentVMSecondaryIP = {
    populateValues : function () {        
        var currentVMId = dojo.byId("currentVMID").value;
                
        if(dijit.byId("vmInfoAquireIPNetworkWidget")) {
             dijit.byId("vmInfoAquireIPNetworkWidget").destroyRecursive();         
        }
        dojo.byId("vmInfoDetailTag").href = "#/user/cloud/vmDetail/"+currentVMId;
        dojo.byId("vmInfoStorageTag").href = "#/user/cloud/vmStorage/"+currentVMId;
        dojo.byId("vmInfoNicTag").href = "#/user/cloud/vmNic/"+currentVMId;
        dojo.byId("vmInfoAquireIPTag").href = "#/user/cloud/secondaryIP/"+currentVMId;         
        
        CurrentVMSecondaryIP.showIPForNic(null);  

        var nicOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var nicRestStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/list"
        }); 
        var instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/byVMId"
        });   
        var nicItemList = new ItemFileWriteStore({data: nicOption});
        var count = 0;
        var defaultTag = "";
        instanceRestStore.query({vmId: currentVMId}).then(function (currentVM) {
            var getVMRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/"
            });
            getVMRestStore.get(currentVM.referenceId).then(function(data) {
                dojo.forEach(data, function(instanceData) {
                    if(instanceData.networkType === "Advanced") {        
                    } else {
                        dojo.byId("acquireIpPageDiv").style.display = "none";  
                    } 
                });
            });
            nicRestStore.query({vmReferenceId: currentVM.referenceId}).then(function (nicList) {  
                dojo.forEach(nicList, function (el, index) {     
                    if(el.isDefault == true) {
                        defaultTag = "| ("+ translator.common.defaultVal +")";
                    } else {
                        defaultTag = "";
                    }
                    count = index + 1;
                    nicItemList.newItem({id: el.referenceId, name: "Nic"+ count + "|" + el.networkName  + defaultTag});
                });
                
                if(dijit.byId("vmInfoAquireIPNetworkWidget")) {
                    dijit.byId("vmInfoAquireIPNetworkWidget").destroyRecursive();  
                }  
                
                 var networkWidget = new FilteringSelect({
                    placeHolder: translator.common.selectNic,
                    id: "vmInfoAquireIPNetworkWidget",            
                    sortByLabel: false,
                    store: nicItemList,            
                    maxHeight: 100,
                    onChange: function() { 
                      CurrentVMSecondaryIP.showIPForNic(this.value);                          
                    }
                }).placeAt("vmInfoAquireIPNetworkList"); 
            });
        });      
        
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.publicIPLimit !== "-1") {                   
                    if((limitData.publicIPUsed >= limitData.publicIPLimit)) {
                        dojo.byId("vmInfoprivateIPLimitReachedMsg").style.display = "block";
                        dojo.query("#vmInfoPrivateIPActionButton").toggleClass("hide_text", true);                                           
                    } else {
                        dojo.byId("vmInfoprivateIPLimitReachedMsg").style.display = "none";
                        dojo.query("#vmInfoPrivateIPActionButton").removeClass("hide_text", true);    
                    }
                } else {
                    dojo.byId("vmInfoprivateIPLimitReachedMsg").style.display = "none";
                    dojo.query("#vmInfoPrivateIPActionButton").removeClass("hide_text", true);
                }
            });
        });
               
    },
    showCondition : function () {        
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/public/termsAndCondition"
        });                  
        dojo.style(dijit.byId("vmSecondaryIPContentDialogue").closeButtonNode,"display","none");
        configRestStore.query().then(function(resultData) {
            dojo.byId("vmDetailInfoContentArea").innerHTML = resultData.value;                   
        });
        dijit.byId("nicAcquireIpDialog").hide();
        dijit.byId("vmSecondaryIPContentDialogue").show();
    },
    cancelConditionBox : function () {
        dijit.byId("vmSecondaryIPContentDialogue").hide();
        dijit.byId("nicAcquireIpDialog").show();
    },
    showIPForNic :function (currentNicId) { 
        
       dojo.byId("nicSecondaryIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '20%', datatype:"string",              
                    autoComplete: true,    
                    dataTypeArgs: {
                        autoComplete: false
                    }
                },
                {'name': translator.common.network, 'field': 'network', 'width': '10%', datatype:"string",                    
                    autoComplete: true,                   
                    dataTypeArgs: {
                        autoComplete: false
                    }
                },
                {'name': translator.common.action, 'field': 'action', 'width': '15%', 'formatter' : function(data) {                                               
                        return new dijit.form.Button({label: translator.common.ip.releaseIP, 
                            "class":"defaultbtn", onClick: function () {
                                CurrentVMSecondaryIP.nicRevokeIpShow(data);
                            }
                        });                                               
                    }
                }
            ]
        ];
        var nicIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/nic/ip/list/"
        });
        nicIPAddressRestStore.query({nicId:currentNicId, vmId:dojo.byId("currentInstanceReferenceId").value}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("nicSecondaryIpList").innerHTML = "";               
                dojo.byId("noOfferMessageBox").style.display = "block";
            } else {                
                dojo.byId("noOfferMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: resultData.ip,
                        network: resultData.networkName,
                        action:resultData.referenceId
                    });
                });
                dojo.byId("nicSecondaryIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: {
                    pagination: {
                        pageSizes: ["3", "5", "10", translator.common.all],
                        description: true,
                        sizeSwitch: true,
                        pageStepper: true,
                        gotoButton: true,
                        /*page step to be displayed*/
                        maxPageStep: 4,
                        /*position of the pagination bar*/
                        position: "bottom"
                    }
                },
                });
                dataGrid.placeAt("nicSecondaryIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    'nicAcquireIpShow': function() {            
        var pageNode = dojo.byId("acquireIpPage");
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
            dijit.byId("nicAcquireIpDialog").show();    
        }
    },
    'closeAcquireIpDialog' : function() {               
        dijit.byId("nicAcquireIpDialog").hide();    
    },
    'acquireIp': function() {                    
        var nicStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/ip/acquire/"         
        });
        dijit.byId("nicIpLoader").show();        
        dijit.byId("nicAcquireIpDialog").hide();                      
        nicStore.add({
            nicId:  dijit.byId("vmInfoAquireIPNetworkWidget").value
        }).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if(resultData.result == "OK") {
                    var acquireIpJobStatus = setInterval(function(){CurrentVMSecondaryIP.acquireIpJob(resultData.jobId, resultData.nicId,acquireIpJobStatus);},3000); 
                } else {
                     dijit.byId("nicIpLoader").hide();  
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
                if(resultData.jobResult == "OK") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.acquireIp,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("nicIpLoader").hide();        
                    CurrentVMSecondaryIP.showIPForNic(dijit.byId("vmInfoAquireIPNetworkWidget").value);
                } else if(resultData.jobResult == "Pending") {
                } else  if(resultData.jobResult == "FAILED") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("nicIpLoader").hide();
                }
            });
        });
    },
    'revokeIp' :function() {         
        var ipId = dojo.byId("currentNicIpId").value;
        dijit.byId("nicReleaseIpDialog").hide(); 
        dijit.byId("nicIpLoader").show(); 
        var instanceConsoleStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/ip/remove"         
        });
        instanceConsoleStore.add({
            ipId: ipId
        }).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if(resultData.result == "OK") {
                    var removeIpJobStatus = setInterval(function(){CurrentVMSecondaryIP.revokeIpJob(resultData.jobId, removeIpJobStatus);},3000); 
                } else {
                    dijit.byId("nicIpLoader").hide();  
                    registry.byId("userToaster").setContent(translator.common.ip.cannotReleaseIP,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },    
    'revokeIpJob' :function(jobId, removeIpJobStatus) { 
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/nic/ip/remove/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(removeIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.ipReleased,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("nicIpLoader").hide();
                    CurrentVMSecondaryIP.showIPForNic(dijit.byId("vmInfoAquireIPNetworkWidget").value);                
                } else if(resultData.jobResult == "Pending") {
                } else  if(resultData.jobResult == "FAILED") {
                    clearInterval(removeIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.cannotReleaseIP,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("nicIpLoader").hide();
                }
            });
        });    
    },
    'nicRevokeIpShow': function(currentIpId) {        
        dojo.byId("currentNicIpId").value = currentIpId;
        dijit.byId("nicReleaseIpDialog").show(); 
    },
    'closeReleaseIpDialog': function(currentIpId) {        
        dijit.byId("nicReleaseIpDialog").hide(); 
    }
};
var CurrentVMNicInfo = {
    showVPCPage : function () {        
        var currentVPC = dojo.byId("selectedANVPCID").value;
        VPCMenuInfo.populateValues(currentVPC);        
    },
    populateValues : function () {                 
        var currentVMId = dojo.byId("currentVMID").value;
           
        dojo.byId("nicList").innerHTML = "";
        
//        dojo.query("#nicList .dijitTitlePane").forEach(dojo.destroy);
        
        dojo.byId("vmInfoDetailTag").href = "#/user/cloud/vmDetail/"+currentVMId;
        dojo.byId("vmInfoStorageTag").href = "#/user/cloud/vmStorage/"+currentVMId;
        dojo.byId("vmInfoNicTag").href = "#/user/cloud/vmNic/"+currentVMId;
        dojo.byId("vmInfoAquireIPTag").href = "#/user/cloud/secondaryIP/"+currentVMId;   
        var instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/byVMId"
        });
        var getVMRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });
        instanceRestStore.query({vmId: currentVMId}).then(function (currentVM) {            
            var nicRestStore = new JsonRest({
                target: core.getContextPath()+"/api/nic/list"
            });         
            getVMRestStore.get(currentVM.referenceId).then(function (data) {
                if(data[0].networkType == "Advanced") {
                    dojo.byId("vmNicButtonDiv").style.display = "block";
                    dojo.query("#aquireIPMenuItem").removeClass("hide_text", true); 
                } else {
                    dojo.byId("vmNicButtonDiv").style.display = "none";  
                    dojo.query("#aquireIPMenuItem").toggleClass("hide_text", true); 
                }
            });
            dojo.byId("currentVMRefId").value = currentVM.referenceId;
            dojo.byId("nicSetDefaultLoaderContainer").style.display = "block";
            nicRestStore.query({vmReferenceId: currentVM.referenceId}).then(function (nicList) {  
                if(nicList == "" || nicList == "undefined") {
                    dojo.byId("nicList").innerHTML = ""; 
                } else {
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.forEach(nicList, function (el, index) {                     
                        var defaultStat = "";
                        var count = index + 1;
                        var contentDiv = "";  
                        var viewNetwotkUrl = "#/user/network/view/" + el.networkID
                        var paneStat = false; 
                        var networkName = "";
                        if(el.zoneType === "Advanced") {
                            if(el.hasVPC === true) {
                                var currentTierUrl = "#/user/tier/view/" + el.networkID;
                                var currentVPCUrl = "#/user/vpc/view/" + el.vpcReferenceId;
                                networkName = "<a onclick='CurrentVMNicInfo.showVPCPage()' href=" + currentTierUrl + ">" + el.networkName + "</a>    " + "   |   " +"   <a onclick='CurrentVMNicInfo.showVPCPage()' href=" + currentVPCUrl + ">" + el.vpc + "  ( VPC )  "+ "</a>"
                            } else {
                                networkName = "<a href=" + viewNetwotkUrl + ">" + el.networkName + "</a>"
                            }                            
                        } else {
                            networkName = "<span>" + el.networkName + "</span>"; 
                        }
                        if(el.isDefault === true) {
                            defaultStat = "("+ translator.common.defaultVal +")";  
                            paneStat = true;
                            contentDiv = "<div class='row-fluid'><div class='span12'></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.idCaps+":</label><span id='nicId'>"+ el.referenceId + "</span></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.networkName+":</label>"+networkName+"</div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.type+":</label>"+                                                                               
                                        "<span>" + el.type + "</span></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.ipAddress+":</label>"+                                                                                
                                        "<span>" + el.ipAddress + "</span></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.gateway+":</label>"+                                        
                                        "<span>" + el.gateway + "</span></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.netmask+":</label>"+                                        
                                        "<span>" + el.netMask + "</span></div></div>";
                        } else {
                            contentDiv = "<div class='row-fluid'><div class='span12'><div class='span8'></div><div class='span4'><a class='btn-flat success' onclick="+'CurrentVMNicInfo.confirmDefaultNic()'+">"+ translator.common.setasDefault + "</a><img class='offset1 vmButton' src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png' onclick="+'CurrentVMNicInfo.confirmDeleteNic()'+"></img></div></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.idCaps+":</label><span id='nicId'>"+ el.referenceId + "</span></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.networkName+":</label><a href=" + viewNetwotkUrl+">" + el.networkName + "</a></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.type+":</label>"+                                                                               
                                        "<span>" + el.type + "</span></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.ipAddress+":</label>"+                                                                                
                                        "<span>" + el.ipAddress + "</span></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.gateway+":</label>"+                                        
                                        "<span>" + el.gateway + "</span></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.netmask+":</label>"+                                        
                                        "<span>" + el.netMask + "</span></div></div>";
                        }
                        var tp = new dijit.TitlePane({
                            "class": "",
                            style: "height: auto%; width: 60%; margin-left: 110px;",
                            title: "Nic"+ count + " " + "  |  " + el.networkName + "  " + defaultStat,
                            content: contentDiv,
                            open: paneStat,
                            onShow: function () {                            
                               CurrentVMNicInfo.onPanOpen(this, el.referenceId);
                            }
                        }).placeAt("nicList");
                        tp.startup();
                    }) 
                };                                                              
            });        
        });    
    },
    onPanOpen : function (currentPane, nicId) {
        dojo.byId("currentNicRefID").value = nicId;
        dojo.byId("currentPaneWidgetId").value = currentPane.id;        
    },
    closeDefaultDialogue : function () {
      dijit.byId("vmdefaultDialogue").hide();  
    },
    closeDeleteDialogue : function () {
        dijit.byId("vmNicDeleteDialogue").hide();  
    },
    confirmDefaultNic: function () {
        dijit.byId("vmdefaultDialogue").show();  
    },
    confirmDeleteNic : function () {
        dijit.byId("vmNicDeleteDialogue").show();  
    },
    setDefaultNic : function () {
        dijit.byId("vmdefaultDialogue").hide();         
        dijit.byId("nicProcessLoader").show();        
        dojo.byId("nicSetDefaultLoaderContainer").style.display = "block";
        dojo.byId("nicListContainer").style.display = "none";
        
        var currentNicId = dojo.byId("currentNicRefID").value;
        var vmId = dojo.byId("currentVMRefId").value;
        var nicDefaultRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/updateDefaultNic"
        });
        nicDefaultRestStore.add({
            vmId: vmId,
            nicId: currentNicId
        }).then(function (data) {
            dojo.forEach(data, function (resultData) {
                if(resultData.result === "OK") {                    
                    var setDefaultNicJobStatus = setInterval(function(){CurrentVMNicInfo.setDefaultNicJob(resultData.jobId, resultData.vmId, resultData.nicId ,setDefaultNicJobStatus);},3000);          
                } else{
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.byId("nicListContainer").style.display = "block";
                    registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("nicProcessLoader").hide();
                }   
            });
            
            
        });
    },
    setDefaultNicJob: function(jobId, vmId, nicId, setDefaultNicJobStatus) {
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/updateDefaultNic/job"
        });
        networkRestStore.add({
            jobId: jobId,
            vmId : vmId,
            nicId: nicId
        }).then(function (data) {     
            dojo.forEach(data, function (resultData) {
                if(resultData.jobResult === "OK") {
                    clearInterval(setDefaultNicJobStatus);
                    CurrentVMNicInfo.populateValues(resultData.vmId);
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.byId("nicListContainer").style.display = "block";
                    registry.byId("userToaster").setContent(translator.common.message.setDefaultNicsuccess,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("nicProcessLoader").hide();
                } else if(resultData.jobResult === "FAILED") {
                    clearInterval(setDefaultNicJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                    registry.byId("userToaster").show();
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.byId("nicListContainer").style.display = "block";
                    dijit.byId("nicProcessLoader").hide();

                }  
            });
        })   
    },
    deleteNic : function () {
        dijit.byId("vmNicDeleteDialogue").hide();
        var currentNicId = dojo.byId("currentNicRefID").value;
        var vmId = dojo.byId("currentVMRefId").value;
        dijit.byId("nicProcessLoader").show();
        dojo.byId("nicSetDefaultLoaderContainer").style.display = "block";
        dojo.byId("nicListContainer").style.display = "none";
        
        var nicDeleteRestStore = new JsonRest({
            target: core.getContextPath() + "/api/nic/remove"
        });
        nicDeleteRestStore.add({
            vmId: vmId,
            nicId: currentNicId
        }).then(function (data) {
            dojo.forEach(data, function (resultData) {
                if(resultData.result === "OK") {
                    var setDeleteNicJobStatus = setInterval(function(){CurrentVMNicInfo.deleteNicJob(resultData.jobId, resultData.vmId, resultData.nicId, setDeleteNicJobStatus);},3000);      
                } else if(resultData.result === "JOB_FAILED"){
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.byId("nicListContainer").style.display = "block";
                    registry.byId("userToaster").setContent(resultData.message,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("nicProcessLoader").hide();
                } else {
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.byId("nicListContainer").style.display = "block";
                    registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("nicProcessLoader").hide();
                }   
            });
        });
    },
    deleteNicJob: function(jobId, vmId, nicId, setDefaultNicJobStatus) {
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/nic/remove/job"
        });
        networkRestStore.add({
            jobId: jobId,
            vmId : vmId,
            nicId: nicId
        }).then(function (data) {     
            dojo.forEach(data, function (resultData) {
                if(resultData.jobResult === "OK") {
                    clearInterval(setDefaultNicJobStatus);
                    CurrentVMNicInfo.populateValues(resultData.vmId);
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.byId("nicListContainer").style.display = "block";
                    registry.byId("userToaster").setContent(translator.common.message.nicDeleted,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("nicProcessLoader").hide();
                } else if(resultData.jobResult === "JOB_FAILED") {
                    clearInterval(setDefaultNicJobStatus);
                    registry.byId("userToaster").setContent(resultData.message,"error");
                    registry.byId("userToaster").show();
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.byId("nicListContainer").style.display = "block";
                    dijit.byId("nicProcessLoader").hide();

                }  else if(resultData.jobResult === "FAILED") {
                    clearInterval(setDefaultNicJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                    registry.byId("userToaster").show();
                    dojo.byId("nicSetDefaultLoaderContainer").style.display = "none";
                    dojo.byId("nicListContainer").style.display = "block";
                    dijit.byId("nicProcessLoader").hide();

                }  
            });
        })   
    },
    addNetworktoVM : function () {
        var networkWidget = dijit.byId("vmNetworkWidget");
        var vmId = dojo.byId("currentVMRefId").value;
        if(networkWidget.value == "option") {
            dojo.byId("requiredMsgForNic").style.display = "block";
        } else {
            dojo.byId("requiredMsgForNic").style.display = "none";
            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/virtualMachine/addNicToVM"
            });
            dijit.byId("addNetworkButton").setAttribute("disabled", true);  
           
            networkRestStore.add({
                vmId : vmId,
                networkReferenceId : networkWidget.value
            }).then(function (data) {                
                if(data[0].result == "OK") {
                    dijit.byId("addNetworkButton").setAttribute("disable", true);
                    var addNWToVMStatus = setInterval(function(){CurrentVMNicInfo.addNetworkToVMJob(data[0].jobId, vmId, addNWToVMStatus);},1000);                     
                    dojo.byId("nicLoaderContainer").style.display = "block";
                } else {
                    dijit.byId("addNetworkButton").setAttribute("disable", false);
                    dojo.byId("nicLoaderContainer").style.display = "none";
                }
                dijit.byId("vmInfoAddNetworkDialogue").hide();                
            })
        }        
    },
    addNetworkToVMJob : function (jobId, vmId, addNWToVMStatus) {       
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/addNicToVM/job"
        });
        networkRestStore.add({
            jobId: jobId,
            vmId : vmId
        }).then(function (resultData) {                    
            if(resultData[0].jobResult == "OK") {
                dijit.byId("addNetworkButton").setAttribute("disabled", false);  
                clearInterval(addNWToVMStatus);                
                CurrentVMNicInfo.populateValues(resultData[0].vmId);
                dojo.byId("nicLoaderContainer").style.display = "none";
                dijit.byId("vmInfoAddNetworkDialogue").hide();
                registry.byId("userToaster").setContent(translator.common.message.nicCreated,"message");
                registry.byId("userToaster").show();
                   
            } else if(resultData[0].jobResult  == "FAILED" || resultData[0].jobResult == "Failed") {
                dijit.byId("addNetworkButton").setAttribute("disabled", false);  
                clearInterval(addNWToVMStatus);
                registry.byId("userToaster").setContent(translator.common.message.nicfailed,"error");
                registry.byId("userToaster").show();
                
                dijit.byId("vmInfoAddNetworkDialogue").hide();
                dojo.byId("nicLoaderContainer").style.display = "none";
                
            }       
        })        
    },
    confirmADDNetwork : function() {
        if(dijit.byId("vmNetworkWidget")) {
             dijit.byId("vmNetworkWidget").destroyRecursive();         
        }
        var currentRefId = dojo.byId("currentVMRefId").value;
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/byVM"
        });
        var networkOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
             
        var networkList = new ItemFileWriteStore({data: networkOption});
        networkRestStore.query({vmReferenceId: currentRefId}).then(function (data) {
            if(data.length === 0) {
                dojo.byId("noNetworkList").style.display = "block";
                dojo.byId("networkListForm").style.display = "none";
                dojo.byId("networkImage").style.display = "none";
                dojo.byId("networkListFormButton").style.display = "none";
            } else {
                dojo.byId("noNetworkList").style.display = "none";
                dojo.byId("networkListForm").style.display = "block";
                dojo.byId("networkImage").style.display = "block";
                dojo.byId("networkListFormButton").style.display = "block";
                dojo.forEach(data, function (el) {
                    networkList.newItem({id: el.referenceId, name: el.name});
                });
            }
            
        });
        
        var hypervisorWidget = new Select({
            id: "vmNetworkWidget",            
            sortByLabel: false,
            value: "option",
            store: networkList,            
            maxHeight: 100,
            onChange: function() {                                          
            }
        }).placeAt("nicInfoNetworkList"); 
        dijit.byId("vmInfoAddNetworkDialogue").show();
    },
    cancelAddNetwork : function () {
        dijit.byId("vmInfoAddNetworkDialogue").hide();
    }
}

var CurrentVMDetailInfo = {
    populateValues : function (currentVMId) {                    
        dojo.byId("currentVMID").value = currentVMId;
        
        dojo.byId("vmInfoDetailTag").href = "#/user/cloud/vmDetail/"+currentVMId;
        dojo.byId("vmInfoStorageTag").href = "#/user/cloud/vmStorage/"+currentVMId;
        dojo.byId("vmInfoNicTag").href = "#/user/cloud/vmNic/"+currentVMId;   
        dojo.byId("vmInfoAquireIPTag").href = "#/user/cloud/secondaryIP/"+currentVMId; 
        var instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/byVMId"
        });
        instanceRestStore.query({vmId: currentVMId}).then(function (currentVM) {
            var getInstanceRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/"
            });
            var getVMRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/"
            });
            getVMRestStore.get(currentVM.referenceId).then(function (data) {
                if(data[0].networkType == "Advanced") {                    
                    dojo.query("#aquireIPMenuItem").removeClass("hide_text", true); 
                } else {             
                    dojo.query("#aquireIPMenuItem").toggleClass("hide_text", true); 
                }
            });
            getInstanceRestStore.get(currentVM.referenceId).then(function (currentInstance) {
                dojo.byId("detailVMName").innerHTML = currentInstance[0].name;
                dojo.byId("detailVMCreatedDate").innerHTML = currentVM.upgradedDate;
                dojo.byId("detailVMOSType").innerHTML = currentInstance[0].osType;
                dojo.byId("detailVMRefeID").innerHTML = currentInstance[0].referenceId;
                if(currentInstance[0].offerHA == true) {
                    dojo.byId("detailVMIsEnabled").innerHTML = translator.common.yes;
                } else {
                    dojo.byId("detailVMIsEnabled").innerHTML = translator.common.yes;
                }                                
            })            
        });       
    }
};

var CurrentVMStorageInfo = {
    populateValues : function () {                  
        var currentVMId = dojo.byId("currentVMID").value;               
        dojo.byId("vmDetailDiskLimitReached").style.display = "none";
        dojo.byId("addVolume").style.display = "none";                 
        var currencyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"         
        }); 
        currencyRestStore.query().then(function(currency) {
            dojo.forEach(currency, function(el) {                
                dojo.byId("instanceInfoCurrencyValue").innerHTML = el.currency;               
            });            
        });  
        dojo.byId("volumeListWidget").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        dojo.byId("vmInfoDetailTag").href = "#/user/cloud/vmDetail/"+currentVMId;
        dojo.byId("vmInfoStorageTag").href = "#/user/cloud/vmStorage/"+currentVMId;
        dojo.byId("vmInfoNicTag").href = "#/user/cloud/vmNic/"+currentVMId;
        dojo.byId("vmInfoAquireIPTag").href = "#/user/cloud/secondaryIP/"+currentVMId;  
        var instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/byVMId"
        });
        instanceRestStore.query({vmId: currentVMId}).then(function (currentVM) {
            var getInstanceRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/"
            });
            
            getInstanceRestStore.get(currentVM.referenceId).then(function (currentInstance) {
                dojo.byId("computReferId").value = currentInstance[0].computingOfferClusterId;
                dojo.byId("computTag").value = currentInstance[0].computingOfferTags;
                dojo.byId("offerType").value = currentInstance[0].storageType;
                
                if(currentInstance[0].networkType == "Advanced") {                    
                    dojo.query("#aquireIPMenuItem").removeClass("hide_text", true); 
                } else {             
                    dojo.query("#aquireIPMenuItem").toggleClass("hide_text", true); 
                }
            });
            
            var instanceRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/"
            });
            
            var volumeRestStrore = new JsonRest({
                target: core.getContextPath()+"/api/volume/"
            }); 
        
            var volumeCount = 0;
            var currentVmId = currentVM.referenceId;
            volumeRestStrore.query({virtualMachineReferenceId:currentVM.referenceId}).then(function(data) {
                if(data == "" || data == "undefined") {
                    dojo.byId("volumeListWidget").innerHTML = "";
                } else {                                                             
                    dojo.byId("volumeListWidget").innerHTML = "";
                    dojo.forEach(data, function(volumeData) {                                                                                        
                        var snapshotLimitStat = true;
                        if(volumeData.snapshotLimit !== "-1") {                  
                            if((volumeData.snapshotUsed >= volumeData.snapshotLimit)) {
                                dojo.byId("vmDetailDiskLimitReached").style.display = "block";
                                snapshotLimitStat = false;                            
                            } 
                        }                         
                        var volumeListItem = new FogPanel.VolumeListItem ({
                            snapshotOption : snapshotLimitStat,
                            onSnapshotNodeClick: function() {
                                var widget = volumeListItem.getWidget();
                                CurrentVMStorageInfo.showSnapshot(widget);
                            },
                            onDetachNodeClick: function() {
                                var widget = volumeListItem.getWidget();
                                CurrentVMStorageInfo.detachVolume(widget);
                            }
                        });
                        var volumeItems = {
                            id: volumeData.id,
                            referenceId: volumeData.referenceId,
                            name:volumeData.name,
                            size:volumeData.size
                        };
                        volumeListItem.placeAt("volumeListWidget");
                        volumeListItem.startup();
                        volumeListItem.additionalProperties = volumeItems;
                        volumeListItem.additionalProperties.diskSize = volumeData.size + translator.common.gb;
                        volumeListItem.additionalProperties.diskName = volumeData.name;
                        volumeListItem.getData();
                        if(volumeData.type =="ROOT") {
                            dojo.byId("rootDiskId").value = volumeListItem.getWidget().id;
                            volumeListItem.enableRootState();
                        }
                        if(volumeData.hasVMSnapshot == "true") {
                            volumeListItem.disableOptions(); 
                        }
                        volumeCount++;                
                    });                        
                    var accountResourceLimitStore = new JsonRest({
                        target: core.getContextPath()+"/api/account/resourceLimit"
                    });                        
                    instanceRestStore.get(currentVmId).then(function(data) {
                        dojo.forEach(data, function(instanceData) {
                            if(instanceData.hasVMSnapshot == "true") {
                                dojo.byId("addVolume").style.display = "none";  
                                dojo.query("#addVolumeWarningMsg").removeClass("hide_text", true); 
                            } else {
                                dojo.query("#addVolumeWarningMsg").toggleClass("hide_text", true); 
                                accountResourceLimitStore.query().then(function(data) {
                                    dojo.forEach(data, function(limitData) {  
                                        if(limitData.accountType == "TRIAL") {
                                            if((limitData.volumeCount >= limitData.storageLimitNo )) {
                                                dojo.byId("addVolume").style.display = "none";
                                                registry.byId("userToaster").setContent(translator.common.instance.volumeCountReachedVm,"error");
                                                registry.byId("userToaster").show();
                                            } else {
                                                dojo.byId("addVolume").style.display = "block";
                                            }
                                        } else {
                                            if(volumeCount-1 >= 5) {
                                                dojo.byId("addVolume").style.display = "none";
                                                registry.byId("userToaster").setContent(translator.common.instance.volumeCountReachedVm,"error");
                                                registry.byId("userToaster").show();
                                            } else {
                                                dojo.byId("addVolume").style.display = "block"; 
                                            }
                                        }
                                    });
                                });
                            }
                        });
                    });
                }
            }); 
            
            var accountResourceLimitStore = new JsonRest({
                target: core.getContextPath()+"/api/account/getCloudResourceStat"
            });
            accountResourceLimitStore.query().then(function(data) {
                dojo.forEach(data, function(limitData) {                
                    if(limitData.storageLimit !== "-1") {                  
                        if((limitData.storageUsed >= limitData.storageLimit)) {
                            dojo.byId("vmDetailDiskLimitReached").style.display = "block";
                             setTimeout(function () {
                                 dojo.byId("addVolume").style.display = "none";
                             },1000);                                                                                
                        } 
                    } 
                });
            });  
        })         
    },
    enableMonthlyCost: function(currentstate) {
        if(currentstate.value === "monthly") {   
            dojo.byId("vmDiskMonthlyInfo").style.display = "block";                                  
        } else {
            dojo.byId("vmDiskMonthlyInfo").style.display = "none";                 
        }                
    },
    showAddVolume : function() {
        dijit.byId("vmVolumeName").reset();
        dojo.byId("currentVMStorageLoader").style.display = "block";          
        var configRestStoreBilling = new JsonRest({
            target: core.getContextPath()+"/api/config/"
        });

        configRestStoreBilling.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) { 
                if(config.name == "monthly.billing.enabled") {
                    if(config.value == "true" || config.value == true) {
                        dojo.byId("vm-disk-billingTypeDiv").style.display = "block";
                    } else {
                        dojo.byId("vm-disk-billingTypeDiv").style.display = "none";
                    }
                } 
            });
        });

        var accountResourceLimitStore = new JsonRest({
                target: core.getContextPath()+"/api/account/resourceLimit"
        });

        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {  
                if(limitData.accountType == "TRIAL") {
                    if((limitData.volumeCount >= limitData.storageLimitNo)) {
                        dojo.byId("addVolume").style.display = "none";
                    } else {
                        dojo.byId("addVolume").style.display = "block";
                        dijit.byId("addVolumeDialog").show();
                        CurrentVMStorageInfo.addVolumePopulateValues();
                    }
                } else {
                    var currentVmId =  dojo.byId("currentVMID").value;
                    var volumeRestStrore = new JsonRest({
                        target: core.getContextPath()+"/api/volume/"
                    }); 
                    var volumeCount = 0;
                    volumeRestStrore.query({virtualMachineReferenceId:currentVmId}).then(function(data) {
                        dojo.forEach(data, function(volumeData) {
                            volumeCount++;
                        });         
                        if(volumeCount-1 >= 5) {
                            dojo.byId("addVolume").style.display = "none";
                            registry.byId("userToaster").setContent(translator.common.instance.volumeCountReachedVm,"error");
                            registry.byId("userToaster").show();
                        } else {
                            dojo.byId("addVolume").style.display = "block"; 
                            dijit.byId("addVolumeDialog").show();
                            CurrentVMStorageInfo.addVolumePopulateValues();
                        }
                    }); 
                }
            });
        });                
    },
    addVolumePopulateValues: function() {                        
        if(dijit.byId("innerInstanceDiskWidget")) {
            dijit.byId("innerInstanceDiskWidget").destroyRecursive();
        }
//        dojo.byId("volumeDiskList").style.display = "none";
        dojo.byId("currentVMStorageLoader").style.display = "block";         
        dojo.byId("vmDiskErrorMessage").style.display = "none";
        var computClusterId = dojo.byId("computReferId").value;
        var tag = dojo.byId("computTag").value;
        var storageType = dojo.byId("offerType").value;
        var customWidget = ""
        
        var formElements = dojo.query("#planTypeContainer input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var planType = dijit.byId(checkedRadioId).value;  
        
        if(planType === "custom") {
            customWidget = true;
        } else if(planType === "plan") {
            customWidget = false;
        } else {
            customWidget = false;
        }        
        var diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/byComputingOffer"
        });
         
        var diskOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var diskList = new ItemFileWriteStore({data: diskOptions}); 
        var firstVal = "";
         diskOfferRestStore.query({clusterReferenceId:computClusterId, tags: tag, customized:customWidget, storageType: storageType}).then(function(data) {               
             if(data == "" || data == "" || data.length == 0) { 
                 firstVal = "option";
                 dojo.byId("currentVMStorageLoader").style.display = "none";     
                 dojo.byId("vmDiskSizeContent").style.display = "none";
                 dojo.byId("vmDiskInfo").style.display = "none";                        
                 dojo.byId("vmDiskCost").innerHTML = "....";
                 dojo.byId("vmDetailStorageUnit").innerHTML = "";                 
                 diskList.newItem({ id:"option", name:translator.common.message.noDisk});
             } else if(data) {    
                 firstVal = data[0].diskOfferReferenceId;
                 dojo.byId("vmDiskInfo").style.display = "block";                 
                 dojo.byId("vmDetailStorageUnit").innerHTML = "       " + translator.common.gbPerHr;   
                 if((data[0].diskReadRateIOPS == "" && data[0].diskWriteRateIOPS == "") || (data[0].diskReadRateIOPS == null && data[0].diskWriteRateIOPS == null)) {
                     dojo.byId("addDiskIO").innerHTML = "..."
                 } else if ((data[0].diskReadRateIOPS != "" && data[0].diskWriteRateIOPS == "") || (data[0].diskReadRateIOPS != null && data[0].diskWriteRateIOPS == null)) {
                     dojo.byId("addDiskIO").innerHTML = data[0].diskReadRateIOPS; 
                 } else if((data[0].diskReadRateIOPS == "" && data[0].diskWriteRateIOPS != "") || (data[0].diskReadRateIOPS == null && data[0].diskWriteRateIOPS != null)) {
                     dojo.byId("addDiskIO").innerHTML = data[0].diskWriteRateIOPS; 
                 } else {
                    dojo.byId("addDiskIO").innerHTML = data[0].diskReadRateIOPS +","+ data[0].diskWriteRateIOPS;
                 }                                                                         
                 dojo.byId("vmCurrentDiskSize").innerHTML = "  "+ data[0].size + translator.common.gb +",";                 
                 
                 var cost = 0.00;
                 var minCost = 0.00;
                 var currentZone = dojo.byId("locationId").value;
                 dojo.forEach(data, function(el) {
                     dojo.forEach(el.diskOfferZoneCosts, function (zoneCost) {
                         if(zoneCost.zone.referenceZoneId === currentZone) {
                             cost = zoneCost.cost;
                             minCost = zoneCost.minimumCost;
                             
                         }
                     });                     
                     
                     diskList.newItem({
                         id:el.diskOfferReferenceId,
                         name: el.name,
                         size:el.size,
                         type:el.tags,
                         cost: cost,
                         minCost: minCost, 
                         minSize:el.minSize,
                         maxSize: el.maxSize,
                         custom: el.customized,
                         diskWrite : el.diskWriteRateIOPS,
                         diskRead : el.diskReadRateIOPS                                
                     });                             
                 });    
                 
                 dojo.forEach(data[0].diskOfferZoneCosts, function (firstZoneCost) {
                    if(firstZoneCost.zone.referenceZoneId === currentZone) {
                        dojo.byId("vmDiskCost").innerHTML = localeCurrency.format(firstZoneCost.cost, {places: 5, locale: dojo.locale});                
                        var monthCost = Math.ceil((1 * Number(firstZoneCost.cost) * 720.00) * 100.00) / 100.00 ;
                        dojo.byId("vmDiskMonthlyCost").innerHTML = localeCurrency.format(monthCost, {places: 2, locale: dojo.locale})  + "/" + translator.common.month;                            
                    }
                });                 
                 
                 if(customWidget == false) {       
                     dojo.byId("vmDiskSizeContent").style.display = "none";  
                 } else if(customWidget == true || customWidget == "true") {
                     dojo.byId("vmDiskSizeContent").style.display = "block";
                 }                                      
//                 dojo.byId("volumeDiskList").style.display = "block";
                 dojo.byId("currentVMStorageLoader").style.display = "none";                                                                              
             }                        
             var diskWidget = new Select({	                           
                 id: "innerInstanceDiskWidget",          
                 store:diskList,
                 value:firstVal,
                 sortByLabel: false,
                 maxHeight: 100,
                 onChange: function() {	
                     CurrentVMStorageInfo.getDiskData(this);	                            
                 } 	
             }).placeAt("volumeDiskList");	                        
             diskWidget.startup();  
         });                                         
     },
     createVolume : function() {        
         var name = dijit.byId("vmVolumeName");
         var diskOffer =  dijit.byId("innerInstanceDiskWidget").value;
         var size = dijit.byId("vmStorageDiskSizeSpinner").value;     
         
         var formElements = dojo.query("#storageBillingTypeContainer input:checked");
         var checkedRadioId = dijit.byId(formElements[0].id);        
         var billingType = dijit.byId(checkedRadioId).value;  
         
         var volumeStore = new JsonRest({
             target: core.getContextPath()+"/api/volume"
         });         
         var stat = true;         
         if (name.validate && !name.validate()) {
             name.focus();            
             stat = false;             
         }         
         if(stat == true) {
             if(diskOffer == "option") {
                 dojo.byId("vmDiskErrorMessage").style.display = "block";
             } else {
                 dojo.byId("vmDiskErrorMessage").style.display = "none";
                 dijit.byId('addVolumeButton').set('style', {display: 'none'});
                 dojo.byId("addVolumeLoader").style.display = "inline";
                 volumeStore.add({
                     name : name.value,
                     diskOfferReferenceId : diskOffer,
                     customDiskSize : size.toString(),
                     billingType: billingType,
                     instanceId:dojo.byId("currentInstanceReferenceId").value
                 }).then(function(data) {            
                     dojo.forEach(data, function(resultData) {
                         if(resultData.result == "OK") {
                             dijit.byId("addVolumeDialog").hide();
                             var volumeListItem = new FogPanel.VolumeListItem ({
                                 onSnapshotNodeClick: function() {
                                     var widget = volumeListItem.getWidget();
                                     CurrentVMStorageInfo.showSnapshot(widget);
                                 },
                                 onDetachNodeClick: function() {
                                     var widget = volumeListItem.getWidget();
                                     CurrentVMStorageInfo.detachVolume(widget);
                                 }
                             });
                             volumeListItem.placeAt("volumeListWidget");
                             volumeListItem.startup();  
                             volumeListItem.enableloder();
                             var createVolumeStatus = setInterval(function(){CurrentVMStorageInfo.volumeJobCreate(resultData.jobId, createVolumeStatus, volumeListItem);},3000);                      
                         } else { 
                             dijit.byId('addVolumeButton').set('style', {display: 'inline'});
                             dojo.byId("addVolumeLoader").style.display = "none";                   
                             dijit.byId("addVolumeDialog").show();                    
                             registry.byId("userToaster").setContent(translator.common.instance.cannotAddVolume,"error");
                             registry.byId("userToaster").show();
                             volumeListItem.deleteWidget();                    
                         }                                
                     });
                 });
             }             
         }
     },
     volumeJobCreate : function(jobId, jobStatus, volumeListItem) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                
                if(resultData.jobResult == "OK") {
                    clearInterval(jobStatus);
                    CurrentVMStorageInfo.attachVolume(resultData.referenceId, volumeListItem);
                    dijit.byId("vmVolumeName").reset();
                } else if(resultData.jobResult == "Pending") {

                } else {
                    clearInterval(jobStatus);
                    dijit.byId("vmVolumeName").reset();
                    registry.byId("userToaster").setContent(translator.common.message.refreshPage,"error");
                    registry.byId("userToaster").show();
                    dijit.byId('addVolumeButton').set('style', {display: 'inline'});
                    dojo.byId("addVolumeLoader").style.display = "none";
                }
            });
        });
    },
    
    attachVolume: function(volumeId, volumeListItem) {
        dijit.byId("addVolumeDialog").hide();
        var volumeAddRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/attach/"
        });
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;
        volumeAddRestStore.add({
            volumeReferenceId : volumeId,
            virtualMachineReferenceId : currentVmId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {                    
                    var attachJobStatus = setInterval(function(){CurrentVMStorageInfo.volumeJobAttach(resultData.jobId, attachJobStatus, volumeListItem);},3000);                      
                } else { 
                    volumeListItem.deleteWidget();
                    dijit.byId("addVolumeDialog").show();                    
                    registry.byId("userToaster").setContent(translator.common.instance.cannotAddVolume,"error");
                    registry.byId("userToaster").show();
                    dijit.byId('addVolumeButton').set('style', {display: 'inline'});
                    dojo.byId("addVolumeLoader").style.display = "none";
                }
            });
        });
    },
    volumeJobAttach : function(jobId, attachJobStatus, volumeListItem) {                         
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(attachJobStatus);
                    var volumeItems = {
                        id: resultData.referenceId,
                        referenceId: resultData.referenceId,
                        name:resultData.name,
                        size:resultData.size
                    };
                    volumeListItem.additionalProperties = volumeItems;
                    volumeListItem.additionalProperties.diskSize = resultData.size + translator.common.gb;
                    volumeListItem.additionalProperties.diskName = resultData.name;
                    volumeListItem.getData();
                    volumeListItem.enableInfo();
                    registry.byId("userToaster").setContent(translator.user.storage.volumeAttached,"message");
                    registry.byId("userToaster").show();     
                    dijit.byId('addVolumeButton').set('style', {display: 'inline'});
                    dojo.byId("addVolumeLoader").style.display = "none";
                } else if(resultData.jobResult == "Pending") {

                } else  if(resultData.jobResult == "FAILED"){
                    clearInterval(attachJobStatus);
                    volumeListItem.deleteWidget();
                    registry.byId("userToaster").setContent(translator.common.message.refreshPage,"error");
                    registry.byId("userToaster").show();
                    
                    dijit.byId('addVolumeButton').set('style', {display: 'inline'});
                    dojo.byId("addVolumeLoader").style.display = "none";
                }
            });
        });
    },
    takeSnapShot : function() {
       
        var currentWidgetId = dojo.byId("currentWidgetId").value;
        var currentWidget = dijit.byId(currentWidgetId);
        var volumeId = currentWidget.additionalProperties.referenceId;
        var snapShotRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot"
        });   
        
        var formElements = dojo.query("#vm-add-snap-billingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        
        
        var pageNode = dojo.byId("snapNameDiv");
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
            dijit.byId("vmCreateSnapshot").hide(); 
            snapShotRestStore.add({
                volumeId : volumeId,
                snapshotName:  dojo.byId("vmSnapshotName").value,
                billingType: billingType
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
//                        dojo.byId("noSnapMessageBox").style.display = "none";
                        var snapshotJobStatus = setInterval(function(){CurrentVMStorageInfo.snapshotJob(resultData.jobId, snapshotJobStatus, currentWidget);},3000);                              
                        currentWidget.enableloder();                            
                    } else {
//                        dojo.byId("snapshotLoader").style.display = "none";
                        registry.byId("userToaster").setContent(translator.user.snapshot.snapshotError,"error");
                        registry.byId("userToaster").show();
                    }
                });
            });
        }
    },
    snapshotJob : function(jobId, snapshotJobStatus, currentWidget) {

        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/job/"
        });   
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(snapshotJobStatus);
                    currentWidget.enableInfo();
                    registry.byId("userToaster").setContent(translator.user.snapshot.created,"message");
                    registry.byId("userToaster").show();

                } else if(resultData.jobResult == "Pending") {

                } else {
                    clearInterval(snapshotJobStatus);
                    registry.byId("userToaster").setContent(translator.user.snapshot.snapshotError,"error");
                    registry.byId("userToaster").show();

                }
            });
        });
    }, 
    closeDetachdialog : function () {
        dijit.byId("vmInfoattachDialog").hide();      
    },
    vmInfoDetachSnapshot : function () {           
        var widgetId = dojo.byId("currentVolumeWidget").value;
        var currentWidget = dijit.byId(widgetId);
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;
        var volumeId = currentWidget.additionalProperties.referenceId;
        
        var volumeRestStore = new JsonRest({
        target: core.getContextPath()+"/api/volume/detach/"
        }); 
        dojo.byId("detachloader").style.display="block";
        dijit.byId("vmDetachButton").setAttribute('style', 'display: none');   
        dijit.byId("vmInfoSnapCancelButton").setAttribute('style', 'display: none');   
        
        
        volumeRestStore.add({
            volumeReferenceId: volumeId,  
            virtualMachineReferenceId : currentVmId
        }).then(function(result) {
            dojo.byId("detachloader").style.display="none";
            dijit.byId("vmDetachButton").setAttribute('style', 'display: block');    
            dijit.byId("vmInfoSnapCancelButton").setAttribute('style', 'display: block');                
            dijit.byId("vmInfoattachDialog").hide();      
            dojo.forEach(result, function(resultData) {
                dijit.byId("vmInfoattachDialog").hide();   
                if(resultData.result == "OK") {
                    currentWidget.enableloder();
                    var detachJobStatus = setInterval(function(){CurrentVMStorageInfo.volumeJobDetach(resultData.jobId, detachJobStatus, currentWidget);},3000);                      
                } else {
                    currentWidget.enableInfo();
                    registry.byId("userToaster").setContent(translator.user.storage.cannotDetached,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },
    detachVolume: function(currentWidget) {
        dijit.byId("vmInfoattachDialog").show();        
        dojo.byId("currentVolumeWidget").value = currentWidget.id;
    },
    volumeJobDetach : function(jobId, detachJobStatus, currentWidget) {                
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;        
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(detachJobStatus);
                    currentWidget.deleteWidget();
                    registry.byId("userToaster").setContent(translator.user.storage.storageDetached,"message");
                    registry.byId("userToaster").show();
                } else if(resultData.jobResult == "Pending") {

                } else if(resultData.jobResult == "FAILED") {
                    currentWidget.deleteWidget();
                    clearInterval(detachJobStatus);
                    registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                    registry.byId("userToaster").show();
                    core.router.go("#/user/cloud/userInstancePage");
                } else {
                    currentWidget.deleteWidget();
                    clearInterval(detachJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.refreshPage,"error");
                    registry.byId("userToaster").show();
                }
            });
            var volumeRestStrore = new JsonRest({
                target: core.getContextPath()+"/api/volume/"
            }); 

            var volumeCount = 0;

            volumeRestStrore.query({virtualMachineReferenceId:currentVmId}).then(function(data) {
                dojo.forEach(data, function(volumeData) {
                    volumeCount++;
                });         
                if(volumeCount-1 >= 5) {
                    dojo.byId("addVolume").style.display = "none";
                    registry.byId("userToaster").setContent(translator.common.instance.volumeCountReachedVm,"error");
                    registry.byId("userToaster").show();
                } else {
                    dojo.byId("addVolume").style.display = "block"; 
//                    dijit.byId("addVolumeDialog").show();
                    CurrentVMStorageInfo.addVolumePopulateValues();
                }
            }); 
        });
    },
    cancelSanpshot:function() {
       dijit.byId("vmCreateSnapshot").hide(); 
    },
    setVMDiskSliderSize : function(currentSpinner) {     
        dojo.byId("vmCurrentDiskSize").innerHTML = "   " + currentSpinner.value + translator.common.gb;
        var monDiskCost = dojo.byId("vmDiskCost").innerHTML;           
        var monthCost = Math.ceil((currentSpinner.value * Number(monDiskCost) * 720.00) * 100.00) / 100.00;
        dojo.byId("vmDiskMonthlyCost").innerHTML = localeCurrency.format(monthCost, {places: 2, locale: dojo.locale})  + "/" + translator.common.month;
//        dijit.byId("vmVolumeCustomSlider").set("value", currentSpinner.value);
    },
    getDiskData : function(currentDisk) {        
        var customWidget = "";            
        
        var formElements = dojo.query("#planTypeContainer input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var planType = dijit.byId(checkedRadioId).value;  
        
        if(planType === "custom") {
            customWidget = true;
        } else if(planType === "plan") {
            customWidget = false;
        } else {
            customWidget = false;
        }
        var maxSize;    
        if(currentDisk.value == "option") {            
            dojo.byId("vmDiskInfo").style.display = "none";    
            dojo.byId("vmDiskSizeContent").style.display = "none";    
            dojo.byId("vmDiskCost").innerHTML = "...";
            dojo.byId("vmDetailStorageUnit").innerHTML = "";
            
        } else {
            dojo.byId("vmDiskInfo").style.display = "block";     
            currentDisk.store.fetch({query: {id: currentDisk.value},
            onItem: function(item) {
            if(item) { 
                dojo.forEach(item.maxSize, function(el) {                    
                    maxSize = el;
                });                   
                if((item.diskRead == "" && item.diskWrite == "") || (item.diskRead == "undefined" && item.diskWrite == "undefined")) {
                    dojo.byId("addDiskIO").innerHTML = "..."
                } else if((item.diskRead != "" && item.diskWrite == "") || (item.diskRead != "undefined" && item.diskWrite == "undefined")) {
                    dojo.byId("addDiskIO").innerHTML = item.diskRead; 
                } else if((item.diskRead == "" && item.diskWrite != "")||(item.diskRead == "undefined" && item.diskWrite != "undefined")) {
                    dojo.byId("addDiskIO").innerHTML = item.diskWrite; 
                } else {
                    dojo.byId("addDiskIO").innerHTML = item.diskRead +","+item.diskWrite;
                }
                
                dojo.byId("vmDiskCost").innerHTML = localeCurrency.format(item.cost, {places: 5, locale: dojo.locale});
                dojo.byId("vmDetailStorageUnit").innerHTML = "     " + translator.common.gbPerHr;  
                if(item.custom == true || item.custom == "true") {
                    dojo.byId("vmCurrentDiskSize").innerHTML = "   " + dijit.byId("vmStorageDiskSizeSpinner").value + translator.common.gb;
                    
                    var monthCost = Math.ceil((dijit.byId("vmStorageDiskSizeSpinner").value * Number(item.cost) * 720.00) * 100.00) / 100.00;
                    dojo.byId("vmDiskMonthlyCost").innerHTML = localeCurrency.format(monthCost, {places: 2, locale: dojo.locale})  + "/" + translator.common.month;
                    
                } else if(item.custom == false || item.custom == "false") {
                    dojo.byId("vmCurrentDiskSize").innerHTML = "  " + item.size + translator.common.gb+",";
                    
                    var monthCost = Math.ceil((item.size * Number(item.cost) * 720.00) * 100.00) / 100.00;
                    dojo.byId("vmDiskMonthlyCost").innerHTML = localeCurrency.format(monthCost, {places: 2, locale: dojo.locale})  + "/" + translator.common.month;
                    
                }                 
            }          
        }
        }); 
            if(customWidget == true || customWidget == "true") {
                dijit.byId("vmStorageDiskSizeSpinner").attr("constraints", {max: maxSize, min: 1}); 
                dijit.byId("vmStorageDiskSizeSpinner").setValue(1);
                dojo.byId("vmDiskSizeContent").style.display = "block";
            } else if(customWidget == false || customWidget == "false") {
                dojo.byId("vmDiskSizeContent").style.display = "none";    
            }  
        }                      
     },
     showCustomDisk : function(customCheck) {   
         var customStat = "";
         if(customCheck.value === "plan") {
             customStat = false;
         } else if(customCheck.value === "custom") {
             customStat = true;
         } else {
             customStat = false;
         }
         
         var diskOfferRestStore = new JsonRest({
             target: core.getContextPath()+"/api/diskOffer/byComputingOffer"
         });    
         var diskData = {
             identifier: 'id',
             label: 'name',
             items: []
         };         
         var diskDataList = new ItemFileWriteStore({data: diskData});        
         var diskWidget = dijit.byId("innerInstanceDiskWidget");
         var computClusterId = dojo.byId("computReferId").value;
         var tag = dojo.byId("computTag").value;
         var storageType = dojo.byId("offerType").value;
         diskOfferRestStore.query({clusterReferenceId:computClusterId,tags: tag, customized: customStat, storageType: storageType}).then(function(data) {
            if(data == "" || data == "") {                                                              
                var noDiskData = {
                    identifier: 'id',
                    label: 'name',
                    items: [{id:"option", name: translator.common.message.noDisk}]
                };         
                var noDiskDataList = new ItemFileWriteStore({data: noDiskData});                
                dijit.byId("innerInstanceDiskWidget").setStore(noDiskDataList);                
                dojo.byId("vmDiskSizeContent").style.display = "none";
                dojo.byId("vmDiskInfo").style.display = "none";  
                dojo.byId("vmDetailStorageUnit").innerHTML = ""; 
                dojo.byId("vmDiskCost").innerHTML = "...";
            } else if(data) {                  
                var min =  Number(data[0].minSize);
                var max = Number(data[0].maxSize);                           
                var discretValue = max - min;                      
                dijit.byId("vmStorageDiskSizeSpinner").attr("constraints", {max: max, min: min}); 
                dojo.byId("vmDiskInfo").style.display = "block";                   
                dijit.byId("vmStorageDiskSizeSpinner").setValue(min);     
                
                dojo.byId("vmDetailStorageUnit").innerHTML = "    "  + translator.common.gbPerHr; 
                if((data[0].diskReadRateIOPS == "" && data[0].diskWriteRateIOPS== "") || (data[0].diskReadRateIOPS == null && data[0].diskWriteRateIOPS == null)) {
                    dojo.byId("addDiskIO").innerHTML = "..."
                } else if ((data[0].diskReadRateIOPS != "" && data[0].diskWriteRateIOPS == "") || (data[0].diskReadRateIOPS != null && data[0].diskWriteRateIOPS == null)) {
                    dojo.byId("addDiskIO").innerHTML = data[0].diskReadRateIOPS; 
                } else if((data[0].diskReadRateIOPS == "" && data[0].diskWriteRateIOPS != "") || (data[0].diskReadRateIOPS == null && data[0].diskWriteRateIOPS != null)) {
                    dojo.byId("addDiskIO").innerHTML = data[0].diskWriteRateIOPS; 
                } else {
                    dojo.byId("addDiskIO").innerHTML = data[0].diskReadRateIOPS +","+ data[0].diskWriteRateIOPS;
                }                               
                
                dojo.byId("vmCurrentDiskSize").innerHTML = "  " + min + translator.common.gb  +",";                                  
                var cost = 0.00;
                var minCost = 0.00;
                var currentZone = dojo.byId("locationId").value;
                dojo.forEach(data, function(el) {                                                                                         
                     dojo.forEach(el.diskOfferZoneCosts, function (zoneCost) {
                         if(zoneCost.zone.referenceZoneId === currentZone) {
                             cost = zoneCost.cost;
                             minCost = zoneCost.minimumCost;
                             
                         }
                     });
                        diskDataList.newItem({
                            id:el.diskOfferReferenceId,
                            name: el.name,
                            size:el.size,
                            type:el.tags,
                            cost: cost,
                            minCost: minCost,
                            minSize:el.minSize,
                            maxSize: el.maxSize,
                            custom: el.customized,
                            diskWrite : el.diskWriteRateIOPS,
                            diskRead : el.diskReadRateIOPS
                        });  
                    });
                diskWidget.setStore(diskDataList);   
                
                dojo.forEach(data[0].diskOfferZoneCosts, function (firstZoneCost) {
                    if(firstZoneCost.zone.referenceZoneId === currentZone) {
                        dojo.byId("vmDiskCost").innerHTML = localeCurrency.format(firstZoneCost.cost, {places: 5, locale: dojo.locale});                
                        var monthCost = Math.ceil((1 * Number(firstZoneCost.cost) * 720.00) * 100.00) / 100.00 ;
                        dojo.byId("vmDiskMonthlyCost").innerHTML = localeCurrency.format(monthCost, {places: 2, locale: dojo.locale})  + "/" + translator.common.month;                            
                    }
                });                                                  
            }                               
        }); 
        if(customStat == true || customStat == "true") {
            dojo.byId("vmDiskSizeContent").style.display = "block";
        } else if(customStat == "false" || customStat== false) {
            dojo.byId("vmDiskSizeContent").style.display = "none"; 
        }
    },    
    showCustomDiskSize : function(currentDisk) {            
            var currentDiskId = dojo.byId("volumeDiskId").value;
        
            var diskOfferRestStore = new JsonRest({
                target: core.getContextPath()+"/api/diskOffer/"
            });
            var customSize = currentDisk.getValue();
            diskOfferRestStore.query({diskOfferReferenceId:currentDiskId}).then(function(data) {
                dojo.forEach(data, function(diskData) {
                   var cost = (diskData.diskOfferZoneCosts[0].cost * customSize).toFixed(5);
                   dojo.byId("volumeDiskSize").innerHTML = "  " + translator.common.size.sizeName +":" + customSize + translator.common.gb+translator.user.storage.name+",";    
                   dojo.byId("volumeDiskCost").innerHTML = translator.common.cost + " :  " + (cost);
                   dojo.byId("cusDiskCost").innerHTML = (cost);  
                });
            });
        },            
        closeAddVolumeDialog :  function() {
            dijit.byId("addVolumeDialog").hide();
        },  
        showSnapshot :function(currentWidget) {
        dojo.byId("currentWidgetId").value = currentWidget.id;
        var configRestStoreBilling = new JsonRest({
            target: core.getContextPath()+"/api/config/"
        });                   
        configRestStoreBilling.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) { 
                if(config.name == "monthly.billing.enabled") {
                    if(config.value == "true" || config.value == true) {
                        dojo.byId("vm-add-snap-billingTypeTr").style.display = "none";
                    } else {
                        dojo.byId("vm-add-snap-billingTypeTr").style.display = "none";
                    }
                } 
            });
            dijit.byId("vmCreateSnapshot").show(); 
        });
    }
}

var VMSnapshotInfo = {
init : function() {                 
},   
populateGridValues : function() {
    if(dijit.byId("vmSnapShotListGridWidget")) {
        dijit.byId("vmSnapShotListGridWidget").destroyRecursive();                                
    }        
    dojo.byId("vmSnapShotList").innerHTML = "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/> </br> <p>Firewall Loading! Please wait</p>";
    var vmSnapshotData = {
        items: []
    };
    var vmSnapshotList = new ItemFileWriteStore({data: vmSnapshotData});
    var vmSnapshotLayout = [
        [
            {'field': 'id', 'hidden': 'true'},
            {'name': translator.user.grid.snapshot.autoSnapshot.layout.name, 'field': 'name', 'width': '200px', datatype:"string",  autoComplete: true,'formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            },
            {'name': translator.user.grid.snapshot.layout.instanceName, 'field': 'instanceName', 'width': '200px', datatype:"string",  autoComplete: true},
            {'name': translator.user.grid.snapshot.layout.parentName, 'field': 'parentName', 'width': '100px', datatype:"string",  autoComplete: true},
            {'name': translator.user.grid.snapshot.layout.isCurrent, 'field': 'isCurrent', 'width': '100px', datatype:"string",  autoComplete: true},
            {'name': translator.user.grid.instance.layout.zone, 'field': 'zone', 'width': '100px', datatype:"string",  autoComplete: true},  
            {'name': translator.common.date, 'field': 'date', 'width': '100px', datatype:"string",  autoComplete: true},  
            {'name': translator.user.grid.instance.layout.action, 'field': 'action', 'width': '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                    var btn = "<a class='btn-flat danger spacing' onclick='VMSnapshotInfo.showDeleteVMSnapshot("+data+")'>"+translator.common.deleteData+"</a>" 
                            + "<a class='btn-flat danger spacing' onclick='VMSnapshotInfo.showRevertVMSnapshotDialog("+data+")'>"+translator.common.reset+"</a>";
                    return btn;
                }
            }
        ]
    ];
    var vmSnapshotRest = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/listVMSnapshot/"
    });
    vmSnapshotRest.query().then(function(data) {
        if(data.length  == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
            dojo.byId("noVMSnapshotDiv").style.display = "block"; 
            dojo.byId("vmSnapShotList").innerHTML = "";
        } else {
            dojo.byId("noVMSnapshotDiv").style.display = "none";
            dojo.forEach(data, function(vmsnapData) {                    
                vmSnapshotList.newItem({  
                    id:vmsnapData.referenceId,
                    instanceName: vmsnapData.virtualMachineName,
                    date: vmsnapData.created,
                    name: vmsnapData.name,
                    zone:vmsnapData.zone,
                    parentName:vmsnapData.parentName,
                    isCurrent:vmsnapData.current,
                    action: vmsnapData.id
                });                                                    
            });
            dojo.byId("noVMSnapshotDiv").style.display = "none"; 
            dojo.byId("vmSnapShotList").innerHTML = "";
            var vmSnapshotGridWidget = new EnhancedGrid({                                
                id: 'vmSnapShotListGridWidget',
                "class": "span12",
                store: vmSnapshotList,                
                structure: vmSnapshotLayout,            
                autoHeight: true,
                plugins: core.getGridConfig().plugins
            });
            vmSnapshotGridWidget.placeAt("vmSnapShotList");
            vmSnapshotGridWidget.startup();                          
        }            
    });
},
populateValues : function(vmId) { 
    var instanceOptions = {
        identifier: 'id',
        label: 'name',
        items: []
    }; 
    var vmCount = 0;
    var instanceList = new ItemFileWriteStore({data: instanceOptions});    
    var virtualMachineRestStore = new JsonRest({
        target: core.getContextPath()+"/api/virtualMachine/byDisk"
    }); 
    var virtualMachineByZoneRestStore = new JsonRest({
        target: core.getContextPath()+"/api/virtualMachine/count"
    }); 
    
    var currentZoneID = dojo.byId("selectedANZoneID").value;
    if(currentZoneID === "All" || currentZoneID === "" || currentZoneID === "undefined") {
        virtualMachineRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {     
                if(el.state =="Running" || el.state == "Stopped") {
                    instanceList.newItem({                    
                        id: el.referenceId,
                        name: el.name, 
                        cluster:el.computingOfferClusterId,
                        type: el.computingOfferTags,
                        storageType: el.computingOfferType
                    }); 
                    vmCount++; 
                }
            });
            if(vmCount !== 0) {
                dojo.byId("createVMSnapshotDiv").style.display = "block"; 
            }        
        });
    } else {
        virtualMachineByZoneRestStore.query({zoneReferenceId: currentZoneID}).then(function(data) {
            dojo.forEach(data[0].instanceData, function(el) {                     
                if(el.state =="Running" || el.state == "Stopped") {                       
                    instanceList.newItem({                    
                        id: el.referenceId,
                        name: el.name, 
                        cluster:el.computingOfferClusterId,
                        type: el.computingOfferTags,
                        storageType: el.computingOfferType
                    }); 
                    vmCount++; 
                }
            });
            if(vmCount !== 0) {
                dojo.byId("createVMSnapshotDiv").style.display = "block"; 
            } 
        });
    }                           
    var instanceWidget = new FilteringSelect({
        store : instanceList,
        placeHolder: translator.common.message.selectInstance
    }, "VMsnapshotInstanceList");
    instanceWidget.startup();                        
        
    if(dijit.byId("vmSnapShotListGridWidget")) {
        dijit.byId("vmSnapShotListGridWidget").destroyRecursive();                                
    }        
    dojo.byId("vmSnapShotList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.user.snapshot.vmSnapshot.vmSnapshotLoading+"</p>";
    var vmSnapshotData = {
        items: []
    };
    var vmSnapshotList = new ItemFileWriteStore({data: vmSnapshotData});
    var vmSnapshotLayout = [
        [
            {'field': 'id', 'hidden': 'true'},
            {'name': translator.user.grid.snapshot.autoSnapshot.layout.name, 'field': 'name', 'width': '200px', datatype:"string",  autoComplete: true,'formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            },
            {'name': translator.user.grid.snapshot.layout.instanceName, 'field': 'instanceName', 'width': '200px', datatype:"string",  autoComplete: true},
            {'name': translator.user.grid.snapshot.layout.parentName, 'field': 'parentName', 'width': '100px', datatype:"string",  autoComplete: true},
            {'name': translator.user.grid.snapshot.layout.isCurrent, 'field': 'isCurrent', 'width': '100px', datatype:"string",  autoComplete: true},
            {'name': translator.user.grid.instance.layout.zone, 'field': 'zone', 'width': '70px', datatype:"string",  autoComplete: true},  
            {'name': translator.common.date, 'field': 'date', 'width': '150px', datatype:"string",  autoComplete: true},  
            {'name': translator.user.grid.instance.layout.action, 'field': 'action', 'width': '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                    var btn = "<a class='btn-flat danger spacing' onclick='VMSnapshotInfo.showDeleteVMSnapshot("+data+")'>"+translator.common.deleteData+"</a>" 
                            + "<a class='btn-flat danger spacing' onclick='VMSnapshotInfo.showRevertVMSnapshotDialog("+data+")'>"+translator.common.reset+"</a>";
                    return btn;
                }
            }
        ]
    ];
    var vmSnapshotRest = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/listVMSnapshot/"
    });    
    vmSnapshotRest.query({vmId:vmId, zoneReferenceId: dojo.byId("selectedANZoneID").value !== "All" ? dojo.byId("selectedANZoneID").value : null }).then(function(data) {
                if(data.length  == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                    dojo.byId("noVMSnapshotDiv").style.display = "block"; 
                    dojo.byId("vmSnapShotList").innerHTML = "";
                } else {
                    dojo.byId("noVMSnapshotDiv").style.display = "none";
                    dojo.forEach(data, function(vmsnapData) {                    
                        vmSnapshotList.newItem({  
                            id:vmsnapData.referenceId,
                            instanceName: vmsnapData.virtualMachineName,
                            date: vmsnapData.created,
                            name: vmsnapData.name,
                            zone:vmsnapData.zone,
                            parentName:vmsnapData.parentName,
                            isCurrent:vmsnapData.current,
                            action: vmsnapData.id
                        });                                                    
                    });
                    dojo.byId("noVMSnapshotDiv").style.display = "none"; 
                    dojo.byId("vmSnapShotList").innerHTML = "";
                    var vmSnapshotGridWidget = new EnhancedGrid({
                        id: "vmSnapShotListGridWidget",
                        store: vmSnapshotList,
                        "class" : "span12",
                        structure: vmSnapshotLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins            
                    });
                    vmSnapshotGridWidget.placeAt("vmSnapShotList");
                    vmSnapshotGridWidget.startup();                           
                }            
            });
},
showDeleteVMSnapshot: function (id) {
    dijit.byId("deleteVMSnap").show();
    dojo.byId("currentVMSnap").value = id;
},
closeDeleteVMSnapshot: function () {
    dijit.byId("deleteVMSnap").hide();
},
showCreateVMSnapshotDialog: function() {
    
    var accountListStore = new JsonRest({
        target: core.getContextPath()+"/api/account/currentAccount"         
    });
    accountListStore.query().then(function(data) {
        dojo.forEach(data, function(el) {  
            if(el.accountType == "TRIAL") {
                registry.byId("userToaster").setContent(translator.common.message.noVMSnapforTrial,"error");
                registry.byId("userToaster").show();
            } else {
                dojo.style(dijit.byId("addVMSnapshotDialog").closeButtonNode,"display","none");
                document.body.style.overflow = "hidden";        
                dojo.byId("VMSnapshotBillingTypeDiv").style.display = "none";
                dijit.byId("addVMSnapshotFormPage").reset();
                dijit.byId("addVMSnapshotDialog").show(); 
            }
        });
    });
},
closeCreateVMSnapshotDialog: function() {
    dijit.byId("addVMSnapshotDialog").hide();
    document.body.removeAttribute("style"); 
},
showRevertVMSnapshotDialog: function(id) {
    dojo.byId("currentVMSnap").value = id;
    dijit.byId("revertVMSnap").show();
}, 
closeRevertVMSnapshotDialog: function() {
    dijit.byId("revertVMSnap").hide();
},
revertVMSnapshot: function() {           
    var vmSnapshotRest = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/revertVMSnapshot/"
    });        
    dijit.byId("createVMSnapshotLoader").show();
    vmSnapshotRest.add({
        vmSnapId:dojo.byId("currentVMSnap").value
    }).then(function(data) {
        dojo.forEach(data, function(resultData) {                
            if(resultData.result == "OK") {                      
                var revertVMSnapJobStatus = setInterval(function(){VMSnapshotInfo.revertVMSnapJobStatus(resultData.jobId, revertVMSnapJobStatus);},3000);  
                dijit.byId("revertVMSnap").hide();
            } else if(resultData.result == "Pending") {
            } else  if(resultData.result == "FAILED") {
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotRevertVM,"error");
                registry.byId("userToaster").show();
                dijit.byId("createVMSnapshotLoader").hide();
                dijit.byId("revertVMSnap").hide();
            } else {
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotRevertVM,"error");
                registry.byId("userToaster").show();
                dijit.byId("createVMSnapshotLoader").hide(); 
                dijit.byId("revertVMSnap").hide();
            }
        });
    });
},
revertVMSnapJobStatus : function(jobId, revertVMSnapshotJobStatus) {
    var jobStore = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/revertVMSnapshotJob/"
    }); 
    jobStore.add({
        jobId : jobId
    }).then(function(data) {
        dojo.forEach(data, function(resultData) {                    
            if(resultData.jobResult == "OK") {
                clearInterval(revertVMSnapshotJobStatus);
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.revertVMSuccess,"message");
                registry.byId("userToaster").show();                                               
                dijit.byId('createVMSnapshotLoader').hide();
            } else if(resultData.jobResult == "Pending") {
            } else {
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.revertVMSuccess,"error");
                registry.byId("userToaster").show();
                clearInterval(revertVMSnapshotJobStatus);
                dijit.byId('createVMSnapshotLoader').hide();
            }
        });
    });
},
deleteVMSnapshot: function() {           
    var vmSnapshotRest = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/deleteVMSnapshot/"
    });        
    dijit.byId("createVMSnapshotLoader").show();
    vmSnapshotRest.add({
        vmSnapId:dojo.byId("currentVMSnap").value
    }).then(function(data) {
        dojo.forEach(data, function(resultData) {                
            if(resultData.result == "OK") {                      
                var deleteVMSnapJobStatus = setInterval(function(){VMSnapshotInfo.deleteVMSnapJobStatus(resultData.jobId, deleteVMSnapJobStatus);},3000); 
                dijit.byId("deleteVMSnap").hide();
            } else if(resultData.result == "Pending") {
            } else  if(resultData.result == "FAILED") {
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotDeleteVMSnapsot,"error");
                registry.byId("userToaster").show();
                dijit.byId("createVMSnapshotLoader").hide();
                dijit.byId("deleteVMSnap").hide();
            } else {
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotDeleteVMSnapsot,"error");
                registry.byId("userToaster").show();
                dijit.byId("createVMSnapshotLoader").hide(); 
                dijit.byId("deleteVMSnap").hide();                
            }
        });
    });
},
deleteVMSnapJobStatus : function(jobId, deleteVMSnapJobStatus) {
    var jobStore = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/deleteVMSnapshotJob/"
    }); 
    jobStore.add({
        jobId : jobId
    }).then(function(data) {
        dojo.forEach(data, function(resultData) {                    
            if(resultData.jobResult == "OK") {
                clearInterval(deleteVMSnapJobStatus);
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotDeletedSuccess,"message");
                registry.byId("userToaster").show();                                               
                dijit.byId('createVMSnapshotLoader').hide();
                VMSnapshotInfo.populateGridValues();
            } else if(resultData.jobResult == "Pending") {
            } else {
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotDeleteVMSnapsot,"error");
                registry.byId("userToaster").show();
                clearInterval(deleteVMSnapJobStatus);
                dijit.byId('createVMSnapshotLoader').hide();
            }
        });
    });
},
createVMSnapshot: function() {                   
    var formElements = dojo.query("#VMSnapshotBillingTypeDiv input:checked");
    var checkedRadioId = dijit.byId(formElements[0].id);        
    var billingType = dijit.byId(checkedRadioId).value;        
    var vmSnapshotRest = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/createVMSnapshot/"
    });        
    
    var snapshotMemory = dijit.byId("snapshotMemory").checked;
    
    var pageNode = dojo.byId("addVMSnapshotForm");
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
        dijit.byId("createVMSnapshotLoader").show();
        vmSnapshotRest.add({
           name:dijit.byId("VMSnapshotName").value,
           description:dijit.byId("vmSnapshotDescription").value,
           billingType:"hourly",
           vmId:dijit.byId("VMsnapshotInstanceList").value,
           snapshotMemory:snapshotMemory
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.result == "OK") {                      
                    var createVMSnapJobStatus = setInterval(function(){VMSnapshotInfo.createVMSnapshotJob(resultData.jobId, createVMSnapJobStatus);},3000);  
                } else if(resultData.result == "Pending") {
                } else  if(resultData.result == "FAILED") {
                    document.body.removeAttribute("style"); 
                    registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotDeletedSuccess,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("createVMSnapshotLoader").hide();
                    dijit.byId("addVMSnapshotDialog").hide();
                } else {
                    document.body.removeAttribute("style");
                    registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotCreateVmSnapshot,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("createVMSnapshotLoader").hide(); 
                    dijit.byId("addVMSnapshotDialog").hide();
                }
            });
        });
    }
},
createVMSnapshotJob : function(jobId, createVMSnapshotJobStatus) {
    var jobStore = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/createVMSnapshotJob/"
    }); 
    jobStore.add({
        jobId : jobId
    }).then(function(data) {
        dojo.forEach(data, function(resultData) {                    
            if(resultData.jobResult == "OK") {
                clearInterval(createVMSnapshotJobStatus);
                document.body.removeAttribute("style"); 
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotCreated,"message");
                registry.byId("userToaster").show();                                               
                dijit.byId('addVMSnapshotDialog').hide();
                dijit.byId("createVMSnapshotLoader").hide();
                VMSnapshotInfo.populateGridValues();
                
            } else if(resultData.jobResult == "Pending") {                    
            } else { 
                document.body.removeAttribute("style"); 
                registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotCreateVmSnapshot,"error");
                registry.byId("userToaster").show();
                clearInterval(createVMSnapshotJobStatus);
                dijit.byId('createVMSnapshotLoader').hide();
                dijit.byId("addVMSnapshotDialog").hide();
                
            }
        });
    });
}
};

var CreateVMInfo = {
    _zoneWidget:"",        
    _isoWidget: "",
    _tempWidget: "",
    _networkOfferWidget: "",
    _changeServiceWidget:"",
    _zoneRestStore:"",
    _tempRestStore: "",
    _computingOfferRestStore: "",
    _diskOfferRestStore:"",
    _networkOfferRestStore:"",
    _instanceRestStore:"",
    _currentInstanceWidget:"",
    _isoRestStore:"",
    _instanceGrid:"",
    _templateRestStore:"",
    _currencyRestStore : "",
    init : function() {     
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });        

        this._tempRestStrore = new JsonRest({
            target: core.getContextPath()+"/api/template/"
        });

        this._computingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/"
        });

        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        });

        this._networkOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkOffer/"
        });   

        this._instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });

        this._isoRestStore = new JsonRest({
           target: core.getContextPath()+"/api/iso/"
        });
        
        this._templateRestStore = new JsonRest({
            target: core.getContextPath()+"/api/template/"
        });         
                
        var sshKeyStore = new JsonRest({
            target: core.getContextPath()+"/api/account/SSHKey/list"
        });
        
        this._currencyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
                             
        var sshKeyOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var sshKeyList = new ItemFileWriteStore({data: sshKeyOptions});
        sshKeyStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                sshKeyList.newItem({id: el.name, name: el.name});
            });                      
            var sshKeyWidget = new Select({
                id: "instanceSSHkeyWidget",
                name: "sshKey",      
                store: sshKeyList
            }, "instanceSSHkeyList");             
        });         
    },
    resetTemplate : function () {          
        var currentTemplate = dojo.byId("currentTemplate").value;           
        dijit.byId("customCheckBox").set("checked", false);  
        var templvalNum = Number(dojo.byId("currentTemplate").value);
        if (currentTemplate === "" || currentTemplate === " " || currentTemplate === undefined || currentTemplate === "undefined" || currentTemplate === null || currentTemplate === "null" || ((!currentTemplate) && (templvalNum >= 0))) {                        
//            alert(1)
            var tempOptions = {
                identifier: 'id',            
                label: 'name',
                items: [{id:compCount, name: translator.common.message.selectTemplate}]
            };
            var computDataList = new ItemFileWriteStore({data: tempOptions}) 
            dijit.byId("instanceTempWidget").setStore(computDataList);   
            dijit.byId("instanceTempWidget").set("value", compCount);        
            compCount = compCount + 1;      
            CreateVMInfo.viewOptionsByZone(dijit.byId("instanceZoneWidget"));
        } else {   
            var computCost = 0;
            var avgCost = 0;
            var tempCost= 0;
            var computOptions = {
                identifier: 'id',            
                label: 'name',
                items: [{id:"option", name: translator.common.message.selectOffer}]
            };
            var computDataList = new ItemFileWriteStore({data: computOptions})                                    
            var compuringOfferRestStore = new JsonRest({                    
                target: core.getContextPath()+"/api/computingOffer/"                    
            });        
            var zoneId = dijit.byId("instanceZoneWidget").value;
            var tempId = dijit.byId("instanceTempWidget").value;
            
            var formElements = dojo.query("#storageTypeDiv input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var storageType = dijit.byId(checkedRadioId).value;                      
            compuringOfferRestStore.query({zoneReferenceId:zoneId, templateReferenceId:tempId, storageType: storageType}).then(function(data) {                                                 
                if(data.length == 0 || data == "undefined" || data == "" ) {     
                    dojo.query("#offeringInfo #computOfferInfo").removeClass("show_lable", true);
                    var noComputOptions = {
                        identifier: 'id',            
                        label: 'name',
                        items: [{id:compCount, name: translator.common.message.noPlan}]
                    };
                    
                    var noComputDataList = new ItemFileWriteStore({data: noComputOptions})          
                    dijit.byId("instanceComputOfferWidget").setStore(noComputDataList);
                    dijit.byId("instanceComputOfferWidget").set("value", compCount);
                    compCount = compCount + 1;
                } else { 
                    dojo.byId("computCost").innerHTML = "" + localeCurrency.format(data[0].computingOfferZoneCosts[0].cost, {places: 5, locale: dojo.locale}) + "/" + translator.common.hour;
                    computCost = Number(data[0].computingOfferZoneCosts[0].cost) * 720.00;
                    avgCost = tempCost + computCost;
                    
                    dojo.byId("generalAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + "/" + translator.common.month;  
                    dojo.byId("summaryAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + "/" + translator.common.month;  
                    dojo.forEach(data, function(resultData) {                            
                        computDataList.newItem({
                            id: resultData.offerReferenceId,                                                        
                            name: resultData.name,
                            type: resultData.tags,
//                            cluster: resultData.cluster.clusterReferenceId,
//                            baseOs: resultData.baseOs,
                            bandwidth: resultData.bandWidth,
                            memory: resultData.memory,
                            networkRate: resultData.networkRate,
                            cost: resultData.computingOfferZoneCosts[0].cost,
                            setupCost: resultData.computingOfferZoneCosts[0].setupCost,  
                            speed : resultData.coreCpuSpeed,
                            core: resultData.cpuNumber,    
//                            zoneId: resultData.zone.referenceZoneId
                        });               
                    });    
                    dijit.byId("instanceComputOfferWidget").setStore(computDataList);
                    dijit.byId("instanceComputOfferWidget").set("value", "option");
                }  
            });    
        }
    },
    populateTierValue : function (currentTier, currentZone) {
        var tierRestStore = new JsonRest({                    
            target: core.getContextPath()+"/api/network/"                    
        });
        tierRestStore.get(currentTier).then(function (data) {                        
            dojo.byId("createVMHomeMenuName").href = "#/user/vpc/dashboard";                    
            dojo.byId("createVMCloudMenuName").href = "#/user/vpc/list";
            dojo.byId("createVMCloudMenuName").innerHTML = translator.common.vpc.name;
            dojo.byId("vmBreadcrumb").href = "#/user/vpc/view/" + data[0].vpcId;
            dojo.byId("vmBreadcrumb").innerHTML = data[0].vpcName;
            dojo.byId("seperateSymboll").innerHTML = "/";
            dojo.byId("seperateSymbol2").innerHTML = "/";
            dojo.byId("seperateSymbol3").innerHTML = "/"            
            var instanceMenu1 = "<a href='#/user/vpc/view/"+data[0].vpcId+"' onclick='ViewVpc.showTierTab()'>" + translator.common.tiers + "</a>";           
            dojo.byId("createVMtitle1").innerHTML = "";
            domConstruct.place(instanceMenu1, dojo.byId("createVMtitle1"));
            
            var instanceMenu2 = "<a href='#/user/tier/view/"+currentTier+"'>" + data[0].name + "</a>"            
            dojo.byId("createVMTitle2").innerHTML = "";
            domConstruct.place(instanceMenu2, dojo.byId("createVMTitle2"));
            
            var instanceMenu3 = "<a href='#/user/tier/instanceList/"+currentTier+"'>" + translator.common.menu.instance + "</a>"            
            dojo.byId("createVMTitle3").innerHTML = "";
            domConstruct.place(instanceMenu3, dojo.byId("createVMTitle3"));                                   
            
            dojo.byId("vmName").innerHTML = translator.common.instance.createVM;
        })                
        dojo.query("#vmTemplateNameInfo .updatable").removeClass("non_updatable", true);
        if(dijit.byId("instanceZoneWidget")) {
            dijit.byId("instanceZoneWidget").destroyRecursive();
        }  
        
        var zoneOptions = {
            identifier: 'id',            
            label: 'name',
            items: []
        };
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions}) 
        var zoneRestStore = new JsonRest({                    
            target: core.getContextPath()+"/api/zone/byId"                    
        });
        zoneRestStore.query({zoneId: currentZone}).then(function (data) {
            zoneList.newItem({id: data[0].referenceId, name: data[0].name})
        })
        
        var zoneWidget = new Select({
            id: "instanceZoneWidget",                  
            sortByLabel: false,
            store: zoneList,
            maxHeight: 100,
            onChange: function() {                        
                CreateVMInfo.viewOptionsByZone(this);
            }
        });                                           
        zoneWidget.placeAt("instanceZone")                
        zoneWidget.startup();                                                           
    },
    populateTemplateValue : function(currentTemplate, currentZone) {  
        dojo.byId("currentTemplate").value = currentTemplate;         
        dojo.query("#vmTemplateNameInfo .updatable").toggleClass("non_updatable", true);
        dojo.byId("vmBreadcrumb").innerHTML = translator.common.template.name;
        dojo.byId("vmBreadcrumb").href = "#/user/template";     
        var currentZoneID = dojo.byId("selectedANZoneID").value;                        
        this._currencyRestStore.query().then(function(currency) {
            dojo.forEach(currency, function(el) {                
                dojo.byId("createVMCurrecy").innerHTML = el.currency;
                dojo.byId("summaryCurrencyValue").innerHTML = el.currency;
            })            
        });              
        
        var computCost = 0;
        var avgCost = 0;
        var tempCost= 0;
        var tempOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var currentZoneOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var currentZoneList = new ItemFileWriteStore({data: currentZoneOptions}); 
        var zoneWidget = ""
        var tempList = new ItemFileWriteStore({data: tempOptions});    
        var templateByZoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/template/byZone"
        });
        templateByZoneRestStore.query({tempId: currentTemplate, zoneId: currentZone}).then(function(data) {
            var firstTempVal = "";
            dojo.forEach(data, function(resultData, index) {
                dojo.byId("vmTemplateName").innerHTML = resultData.name;  
                tempList.newItem({
                    id:resultData.referenceId,                 
                    name:resultData.name, 
                    cost: resultData.cost, 
                    osType: resultData.osType,
                    osCategory: resultData.osCategory,
                    hypervisor:resultData.hypervisor,
                    size: resultData.size,
                    baseOs: resultData.baseOs                                        
                });
                if(index == 0) {
                    firstTempVal = resultData.referenceId
                }                 
                dijit.byId("instanceTempWidget").setStore(tempList);  
                
                dojo.forEach(resultData.zone,  function(zoneData) {                           
                    currentZoneList.newItem({id:zoneData.referenceId, name:zoneData.name, networkType: zoneData.networkType});                                                
                });                                          

                if(dijit.byId("instanceZoneWidget")) {
                    dijit.byId("instanceZoneWidget").destroyRecursive();
                }               
                zoneWidget = new Select({
                    id: "instanceZoneWidget",                  
                    sortByLabel: false,
                    store: currentZoneList,
                    maxHeight: 100,
                    onChange: function() {                        
                        CreateVMInfo.onZoneFromTemplate(this);
                    }
                });                                           
                zoneWidget.placeAt("instanceZone")                
                zoneWidget.startup();                
            });            
            CreateVMInfo.getComputingOffer(dijit.byId("instanceTempWidget"));       
            });
        },
       onZoneFromTemplate : function (currentZone) {
           var computOptions = {
                identifier: 'id',
                label: 'name',
                items: [{id:"option", name: translator.common.message.selectOffer}]
            };
            var computDataList = new ItemFileWriteStore({data: computOptions});                                    
            var compuringOfferRestStore = new JsonRest({                    
                target: core.getContextPath()+"/api/computingOffer/"                    
            });        

            var formElements = dojo.query("#storageTypeDiv input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var storageType = dijit.byId(checkedRadioId).value;                      
            compuringOfferRestStore.query({zoneReferenceId:currentZone.value,
                templateReferenceId:resultData.referenceId, storageType: storageType}).then(function(data) {                                 
                if(data == undefined || data == "undefined" || data == "") {                        
                    dojo.query("#offeringInfo #tempDataInfo").removeClass("show_lable", true);
                    dojo.query("#offeringInfo #computOfferInfo").removeClass("show_lable", true);
                    computDataList.newItem({                            
                        id:"option",
                        name: translator.common.message.noPlane                    
                    });
                    dijit.byId("instanceComputOfferWidget").setStore(computDataList);
                } else {                            
                    dojo.query("#offeringInfo #tempDataInfo").toggleClass("show_lable", true);
                    dojo.byId("computCost").innerHTML = "" + localeCurrency.format(data[0].computingOfferZoneCosts[0].cost, {places: 5, locale: dojo.locale}) + "/" +translator.common.hour ;
                    computCost = Number(data[0].computingOfferZoneCosts[0].cost) * 720.00;
                    avgCost = tempCost + computCost;
                    dojo.byId("generalAvgCost").innerHTML = "" +  localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + "/" + translator.common.month;  
                    dojo.byId("summaryAvgCost").innerHTML = "" +  localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + "/" + translator.common.month;  
                    dojo.forEach(data, function(resultData) {                            
                        computDataList.newItem({
                            id: resultData.offerReferenceId,                                
                            name: resultData.name,
                            type: resultData.tags,
//                            cluster: resultData.cluster.clusterReferenceId,
//                            baseOs: resultData.baseOs,
                            bandwidth: resultData.bandWidth,
                            memory: resultData.memory,
                            networkRate: resultData.networkRate,
                            cost: resultData.computingOfferZoneCosts[0].cost,
                            setupCost: resultData.computingOfferZoneCosts[0].setupCost,  
                            speed : resultData.coreCpuSpeed,
                            core: resultData.cpuNumber,
//                            zoneId: resultData.zone.referenceZoneId
                        });                
                    });     
                    dijit.byId("instanceComputOfferWidget").setStore(computDataList);
                    dijit.byId("instanceComputOfferWidget").set("value", "option");
                }                
            });
       },
    populateValues : function() {     
        if(dijit.byId("instanceZoneWidget")) {
            dijit.byId("instanceZoneWidget").destroyRecursive();
        }            
        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id:'option', name:translator.common.message.selectZone}]
        };        
        
        var tempOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id:'option', name:translator.common.message.selectTemplate}]
        };
        
        var firewalOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id:'option', name:translator.common.message.selectFirewall}]
        };                 
        var computingOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id:'option', name:translator.common.message.selectComputingOffer}]
        }; 
        
        var diskOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id:'option', name:translator.common.message.selectDiskOffer}]
        };         
        this._currencyRestStore.query().then(function(currency) {
            dojo.forEach(currency, function(el) {                
                dojo.byId("createVMCurrecy").innerHTML = el.currency;
                dojo.byId("summaryCurrencyValue").innerHTML = el.currency;
            })            
        });
        dojo.query("#vmTemplateNameInfo .updatable").removeClass("non_updatable", true);
        dojo.byId("vmBreadcrumb").innerHTML = translator.common.instance.name;
        dojo.byId("vmBreadcrumb").href = "#/user/cloud/userInstancePage";
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        var tempList = new ItemFileWriteStore({data: tempOptions});
        var firewalList = new ItemFileWriteStore({data: firewalOptions});          
        var computList = new ItemFileWriteStore({data: computingOptions});
        var diskList = new ItemFileWriteStore({data: diskOptions});   
        
        if(zoneTempRefId === "") {            
            var currentZoneID = dojo.byId("selectedANZoneID").value;     
//            alert(currentZoneID)
            if(currentZoneID === "All" || currentZoneID === "") {
                
//                alert(1)
                dojo.query("#createVMFirewallDiv").removeClass("hide_text", true);
                dojo.query("#createVMNetworkDiv").toggleClass("hide_text", true);        
                this._zoneRestStore.query().then(function(data) {                               
                    dojo.forEach(data, function(el) {
                        zoneList.newItem({id: el.referenceZoneId, name: el.aliasName, networkType: el.networkType});
                    });
                });   
                if(dijit.byId("instanceZoneWidget")) {
                    dijit.byId("instanceZoneWidget").destroyRecursive();
                }
                var zoneWidget = new Select({
                    id: "instanceZoneWidget",
                    name: "zoneWidgets",            
                    value: "option",       
                    sortByLabel: false,
                    store: zoneList,
                    maxHeight: 100,
                    onChange: function() { 
                        CreateVMInfo.viewOptionsByZone(this);
                    }
                });   
                zoneWidget.placeAt("instanceZone");
                zoneWidget.startup();
            } else {           
                var currentZoneOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };     
                var currentZoneList = new ItemFileWriteStore({data: currentZoneOptions});
                var zoneRestStore = new JsonRest({                    
                    target: core.getContextPath()+"/api/zone/"                                                                            
                }); 
                var networkRestStore = new JsonRest({                    
                    target: core.getContextPath()+"/api/network/"                    
                });                  
                zoneRestStore.get(currentZoneID).then(function(data) {  
                    if(data.networkType == "Advanced") {                   
                        dojo.query("#createVMFirewallDiv").toggleClass("hide_text", true);
                        dojo.query("#createVMNetworkDiv").removeClass("hide_text", true);
                            var currentNetworkOptions = {
                                identifier: 'id',
                                label: 'name',
                                items: [{id:'option', name:translator.common.message.selectNetwork}]
                            };
                            var currentNetworkList = new ItemFileWriteStore({data: currentNetworkOptions});
                            networkRestStore.query({zoneReferenceId: data.referenceZoneId}).then(function(netData) {
                                if((netData === "" || netData.length == 0) && (isTierOptionEnabled === false)) {
                                    dijit.byId("ceateVMNoNetworkDialogue").show();
                                    dojo.byId("gotoNetworkPageTag").href = "#/user/network/add/" + data.id;                                
                                } else {
                                    dijit.byId("ceateVMNoNetworkDialogue").hide();
                                    dojo.forEach(netData, function (currentNetwork) {
                                        currentNetworkList.newItem({id:currentNetwork.referenceId, name:currentNetwork.name});   
                                    });
                                }  
                                if(dijit.byId("instanceNetworkWidget")) {
                                    dijit.byId("instanceNetworkWidget").destroyRecursive();
                                }
                                var networkWidget = new Select({
                                    id: "instanceNetworkWidget",
                                    value: "option",                  
                                    store: currentNetworkList,
                                    sortByLabel: false,
                                    maxHeight: 100,
                                    onChange: function() {}                
                                });               
                                networkWidget.placeAt("instanceNetworkList");
                                networkWidget.startup(); 
                            });     
                    } else if(data.networkType == "Basic") {
                        dojo.query("#createVMFirewallDiv").removeClass("hide_text", true);
                        dojo.query("#createVMNetworkDiv").toggleClass("hide_text", true);
                    }                                 
                    if(dijit.byId("instanceZoneWidget")) {
                        dijit.byId("instanceZoneWidget").destroyRecursive();
                    }
                    currentZoneList.newItem({id:data.referenceZoneId, name:data.aliasName, networkType: data.networkType});                   
                    var zoneWidget = new Select({
                        id: "instanceZoneWidget",
                        name: "zoneWidgets",            
                        value: data.referenceZoneId,       
                        sortByLabel: false,
                        store: currentZoneList,
                        maxHeight: 100,
                        onChange: function() { 
                            CreateVMInfo.viewOptionsByZone(this);
                        }
                    });
                    zoneWidget.placeAt("instanceZone");
                    zoneWidget.startup();  
                    CreateVMInfo.getComputingOffer(dijit.byId("instanceTempWidget"));
                    var currentTemplate = dojo.byId("currentTemplate").value;   
                    if(currentTemplate == "") {
                        CreateVMInfo.viewOptionsByZone(zoneWidget);
                    }                  
                }) 
            }
        } else {
            
            var zoneRestStore = new JsonRest({
                target: core.getContextPath()+"/api/zone/"
            }); 
            zoneRestStore.query().then(function (data) {
                dojo.forEach(data, function (el) {
                    if(el.id == zoneTempRefId) {
                        var currentZoneOptions = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };     
                        var currentZoneList = new ItemFileWriteStore({data: currentZoneOptions});
                        var zoneRestStore = new JsonRest({                    
                            target: core.getContextPath()+"/api/zone/"                                                                            
                        }); 
                        var networkRestStore = new JsonRest({                    
                            target: core.getContextPath()+"/api/network/"                    
                        });              
                        zoneRestStore.get(el.referenceZoneId).then(function(data) {
                            var zoneid = data.id;
                            if(data.networkType === "Advanced") {                                
                                dojo.query("#createVMFirewallDiv").toggleClass("hide_text", true);
                                dojo.query("#createVMNetworkDiv").removeClass("hide_text", true);
                                var currentNetworkOptions = {
                                    identifier: 'id',
                                    label: 'name',
                                    items: [{id:'option', name:translator.common.message.selectNetwork}]
                                };                                
                                if (isTierOptionEnabled === true) {                                    
                                    
                                    var currentNetwork = dojo.byId("currentTierId").value;
                                    
                                    var networkOptions = {
                                        identifier: 'id',
                                        label: 'name',
                                        items: []
                                    };
                                    var networkList = new ItemFileWriteStore({data: networkOptions});
                                    networkRestStore.get(currentNetwork).then(function (data) {
                                        
                                        networkList.newItem({id:data[0].referenceId, name:data[0].name});      
                                        
                                        setTimeout(function () {
                                            if(dijit.byId("instanceNetworkWidget")) {
                                                dijit.byId("instanceNetworkWidget").destroyRecursive();
                                            }   
                                            
                                            var networkWidget = new Select({
                                            id: "instanceNetworkWidget",
                                            value: "option",                  
                                            store: networkList,
                                            sortByLabel: false,
                                            maxHeight: 100,
                                            onChange: function() {}                
                                        });               
                                        networkWidget.placeAt("instanceNetworkList");
                                        networkWidget.startup();
                                        });
                                         
                                    });
                                    
                                } else {                                      
                                    var currentNetworkList = new ItemFileWriteStore({data: currentNetworkOptions});
                                    networkRestStore.query({zoneReferenceId: data.referenceZoneId}).then(function(data) {
                                        if((data == "" || data.length == 0) && ( (isTierOptionEnabled === false))) {
                                            dijit.byId("ceateVMNoNetworkDialogue").show();
                                            dojo.byId("gotoNetworkPageTag").href = "#/user/network/add/" + zoneid;
                                        } else {
                                            dijit.byId("ceateVMNoNetworkDialogue").hide();
                                            dojo.forEach(data, function (currentNetwork) {
                                                currentNetworkList.newItem({id:currentNetwork.referenceId, name:currentNetwork.name});   
                                            }); 
                                        } 
                                        if(dijit.byId("instanceNetworkWidget")) {
                                            dijit.byId("instanceNetworkWidget").destroyRecursive();
                                        }
                                        var networkWidget = new Select({
                                            id: "instanceNetworkWidget",
                                            value: "option",                  
                                            store: currentNetworkList,
                                            sortByLabel: false,
                                            maxHeight: 100,
                                            onChange: function() {}                
                                        });               
                                        networkWidget.placeAt("instanceNetworkList");
                                        networkWidget.startup(); 
                                    });  
                                }
                                
                            } else if(data.networkType == "Basic") {
                                dojo.query("#createVMFirewallDiv").removeClass("hide_text", true);
                                dojo.query("#createVMNetworkDiv").toggleClass("hide_text", true);
                            }                                 
                            if(dijit.byId("instanceZoneWidget")) {
                                dijit.byId("instanceZoneWidget").destroyRecursive();
                            }
                            currentZoneList.newItem({id:data.referenceZoneId, name:data.aliasName, networkType: data.networkType});                   
                            var zoneWidget = new Select({
                                id: "instanceZoneWidget",
                                name: "zoneWidgets",            
                                value: data.referenceZoneId,       
                                sortByLabel: false,
                                store: currentZoneList,
                                maxHeight: 100,
                                onChange: function() { 
                                        CreateVMInfo.viewOptionsByZone(this);
                                }
                            });
                            zoneWidget.placeAt("instanceZone");
                            zoneWidget.startup();  
                            CreateVMInfo.getComputingOffer(dijit.byId("instanceTempWidget"));
                            var currentTemplate = dojo.byId("currentTemplate").value;   
                            if(currentTemplate == "") {
                                CreateVMInfo.viewOptionsByZone(zoneWidget);
                            }                  
                        }) 
                    }
                });
            });
        }            
        var firewallWidget = new Select({
            id: "instanceFirewalWidget",
            value: "option",                  
            store: firewalList,
            sortByLabel: false,
            maxHeight: 100,
            onChange: function() {}                
        });               
        firewallWidget.placeAt("instanceSecurityGroupList");
        firewallWidget.startup();                
        
        var computingOfferWidget = new Select({
            id: "instanceComputOfferWidget",               
            value: "option",        
            store: computList,
            sortByLabel: false,
            maxHeight: 100,
            onChange: function() {
                CreateVMInfo.getServices(this);
            } 
        });              
        computingOfferWidget.placeAt("instanceComputingOffer");
        computingOfferWidget.startup();
        
        var diskOfferWidget = new Select({
            id: "instanceDiskOfferWidget",
            value: "option",        
            store: diskList,
            sortByLabel: false,
            maxHeight: 100,
            onChange: function() {
                CreateVMInfo.getDisk(this);
            } 
        });              
        diskOfferWidget.placeAt("instanceDiskSlider");
        diskOfferWidget.startup();             
    
        var tempWidget = new Select({
            id: "instanceTempWidget",   
            value:"option",
            store: tempList,
            sortByLabel: false,            
            maxHeight: 100,
            onChange: function() {                 
                CreateVMInfo.getComputingOffer(this);            
            }                
        });
        tempWidget.placeAt("userTempSelect");
        tempWidget.startup();        
              
        var customDiskSlider = new dijit.form.HorizontalSlider({
            name: "customSlider",                  
            minimum: 1,            
            style: "width:55%;", 
            intermediateChanges: true,
            onChange: function(value) { 
                value = value | 0;                
                dijit.byId("storageDiskSizeWidget").setValue(value);
                var tempCost = dojo.byId("templateCost").value;
                var computCost = dojo.byId("computingOfferCost").value;
                var diskWidget = dijit.byId("instanceDiskOfferWidget");
                diskWidget.store.fetch({query: {id: diskWidget.value},
                    onItem: function(item) {
                        if(item) {                   
                            dojo.byId("diskCost").innerHTML =  value + translator.common.gb +"     |"+"        " +localeCurrency.format(item.cost, {places: 5, locale: dojo.locale}) + "  / "+translator.common.gb + "/" + translator.common.hour;
                            dojo.byId("monthlyDiskCost").innerHTML =localeCurrency.format(Math.ceil((value * item.cost * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + "/" + translator.common.month; 
                            var diskCeil = (Number(tempCost) + (Number(computCost)*720.00) + (Number(item.cost) * 720.00 * value))
                            dojo.byId("generalAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(diskCeil * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + "/" + translator.common.month;
                            dojo.byId("summaryAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(diskCeil * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + "/" + translator.common.month;  
                        }
                    }
                }); 
            }   
        }, "instanceCustomDiskSizeSlider");
        customDiskSlider.startup();      
        dojo.style(dijit.byId("createVMLoading").closeButtonNode,"display","none");
        dojo.style(dijit.byId("createTierVMLoadingDialog").closeButtonNode,"display","none");
        
    }, 
    enableMonthlyCost: function() {
        var formElements = dojo.query("#billingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        
        if(billingType == "monthly") {            
            dojo.byId("comMonDiv").style.display = "block";
            dojo.byId("diskMonDiv").style.display = "block";    
            dojo.query("#sumComMonDiv").removeClass("hide_text", true);             
            dojo.byId("sumDiskMonDiv").style.display = "block";             
        } else {
            dojo.byId("comMonDiv").style.display = "none";
            dojo.byId("diskMonDiv").style.display = "none";   
            dojo.query("#sumComMonDiv").toggleClass("hide_text", true);             
            dojo.byId("sumDiskMonDiv").style.display = "none"; 
        }                
    },    
    showSummary : function() {      
        var computWidget = dijit.byId("instanceComputOfferWidget");
        var diskWidget = dijit.byId("instanceDiskOfferWidget");
        var tempWidget = dijit.byId("instanceTempWidget");
        var customDiskSize = dijit.byId("storageDiskSizeWidget");
        var zone = dijit.byId("instanceZoneWidget").get("displayedValue");
        dojo.byId("summaryZone").innerHTML = zone;
        var diskCost = 0;
        var computCost = 0;
        var tempCost = 0;
        var size = 0;
        var diskNumValue = Number(diskWidget.value)        
        if(diskWidget.value === "option" || diskWidget.value === "empty"|| diskNumValue >= 0 || diskWidget.value === "") {          
            dojo.byId("summaryStorageInfo").style.display = "none";
            dojo.byId("summaryAvgCostInfo").style.display = "none"; 
//            dojo.byId("summaryAvgCost").innerHTML = "0";
        } else {
            dojo.byId("summaryStorageInfo").style.display = "block";
            dojo.byId("summaryAvgCostInfo").style.display = "block";
            diskWidget.store.fetch({query: {id: diskWidget.value}, onItem: function(item) {
                    if(item) {                   
                        dojo.byId("summaryDiskCost").innerHTML = " "+ localeCurrency.format(item.cost, {places: 5, locale: dojo.locale}) + " /"+translator.common.gb + "/" + translator.common.hour;
                        dojo.byId("summaryMonthlyDisCost").innerHTML = ""+  localeCurrency.format(Math.ceil((item.cost * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + " /" + translator.common.month;
                        if(item.custom == true || item.custom == "true") {
                            size = Number(customDiskSize.value);
                            dojo.byId("summaryDiskSize").innerHTML = customDiskSize.value + "  " + translator.common.gb;
                            dojo.byId("summaryMonthlyDisCost").innerHTML = ""+ localeCurrency.format(Math.ceil((size * item.cost * 720.00) * 100.00) / 100.00 , {places: 2, locale: dojo.locale})  + "   /" + translator.common.month;                                      
                        } else {
                            dojo.byId("summaryDiskSize").innerHTML = item.size + "  " + translator.common.gb;
                            size = Number(item.size);
                            dojo.byId("summaryMonthlyDisCost").innerHTML = ""+ localeCurrency.format(Math.ceil((size * item.cost * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale})  +  "   /" + translator.common.month;
                        }
                        diskCost = Number(item.cost) * 720.00 * size;
                    }
                }
            }); 
        }
        var compuNumValue = Number(computWidget.value)
        if(computWidget.value == "option" || compuNumValue >= 0) {
            dojo.byId("summaryComputOfferingInfo").style.display = "none";
            dojo.byId("summaryAvgCostInfo").style.display = "none";                 
            dojo.query("#sumBandwidthCost").toggleClass("hide_text", true); 
//            dojo.byId("summaryAvgCost").innerHTML = "0";
        } else {          
            dojo.byId("summaryComputOfferingInfo").style.display = "block";
            dojo.byId("summaryAvgCostInfo").style.display = "block";        
            dojo.query("#sumBandwidthCost").removeClass("hide_text", true); 
            computWidget.store.fetch({query: {id: computWidget.value},
                onItem: function(item) {
                    if(item) {                         
                        dojo.byId("summaryCpuCore").innerHTML = item.core;
                        dojo.byId("summarySpeed").innerHTML = item.speed + "  " + translator.common.mhz;
                        dojo.byId("summaryMemory").innerHTML = item.memory + "  " + translator.common.mb;                        
                        dojo.byId("summarySetupCost").innerHTML = ""+item.setupCost;
                        dojo.byId("summaryRunningCost").innerHTML =""+ localeCurrency.format(item.cost, {places: 5, locale: dojo.locale}) +  "   /" + translator.common.hour;            
                        dojo.byId("summaryMonthlyComCost").innerHTML = ""+ localeCurrency.format( Math.ceil((item.cost * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + "  /" + translator.common.month;
                        computCost = Number(item.cost) * 720.00;
                        dojo.byId("summaryBandwidth").innerHTML = item.bandwidth + " " + translator.common.gb + "(  " + translator.common.free + "   )";
                        var miscRestStore = new JsonRest({
                            target: core.getContextPath()+"/api/miscellaneousOffer/byZone"
                        });                        
                        miscRestStore.query({zoneReferenceId: dijit.byId("instanceZoneWidget").value, miscellaneousOfferId: 1}).then(function (data) {
                            dojo.byId("summaryBandwidthCost").innerHTML = localeCurrency.format(data[0].cost, {places: 2, locale: dojo.locale}) + "    /     " + translator.common.gb +"        /      "+ translator.common.month;
                        });                        
                    }
                }
            }); 
        }
        tempWidget.store.fetch({query: {id: tempWidget.value},
            onItem: function(item) {
                if(item) {                   
                    dojo.byId("summaryOsType").innerHTML = item.name;
                    dojo.byId("summaryTempSize").innerHTML = item.size + "  " + translator.common.gb;
                    dojo.byId("summaryTempCost").innerHTML = "" + localeCurrency.format(item.cost, {places: 2, locale: dojo.locale}) + "   /" + translator.common.month;
                    tempCost = Number(item.cost);                           
                }
            }
        });         
        
        var avgCost = diskCost + computCost +  tempCost;
//        dojo.byId("summaryAvgCost").innerHTML = "" + Math.ceil((avgCost * 100.00) / 100.00 ) + "   /" + translator.common.month; 
        dijit.byId("vmSummaryPage").show();  
    },
    setSliderSize : function(currentSpinner) {        
        dijit.byId("instanceCustomDiskSizeSlider").set("value", currentSpinner.value);
    },
    getDisk : function(currentDisk) {          
        var customWidget = dijit.byId("customCheckBox").checked;
        var tempCost = dojo.byId("templateCost").value;
        var computCost = dojo.byId("computingOfferCost").value;
        var avgCost = 0;
        var minSize;
        var maxSize;  
        var diskCost;
        
        if(currentDisk.value == "option") {            
            dojo.query("#offeringInfo #diskOfferInfo").removeClass("show_lable", true);            
            dojo.byId("vmDiskSize").style.display = "none";
            var totCost = Number(tempCost) + Number(computCost) * 720.00;
            dojo.byId("generalAvgCost").innerHTML = "" +  localeCurrency.format(Math.ceil(totCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + "   /" + translator.common.month;
            dojo.byId("summaryAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(totCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + "   /" + translator.common.month; 
        } else {
            dojo.query("#offeringInfo #diskOfferInfo").toggleClass("show_lable", true);       
            currentDisk.store.fetch({query: {id: currentDisk.value},        
                onItem: function(item) {
                    if(item) {                                       
                        dojo.forEach(item.minSize, function(el) {                    
                            minSize = el;                            
                        });
                        dojo.forEach(item.maxSize, function(el) {                    
                            maxSize = el;
                        });                        
                        if(item.custom == true || item.custom == "true") {
                            dojo.byId("diskCost").innerHTML = " 1 GB   |"+"        " +localeCurrency.format(item.cost, {places: 5, locale: dojo.locale}) + "  /" + translator.common.gb + "/" + translator.common.month;
                            dojo.byId("monthlyDiskCost").innerHTML = localeCurrency.format(Math.ceil((item.cost * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + " /" + translator.common.month; 
                            avgCost =  Number(tempCost) + Number(computCost) * 720.00 +  Number(item.cost) * 720.00 * 1;
                            dojo.byId("generalAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + " /" + translator.common.month;
                            dojo.byId("summaryAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + " /" + translator.common.month;
                        } else if(item.custom == false || item.custom == "false") {
                            dojo.byId("diskCost").innerHTML = item.size + "  " + translator.common.gb +  "  |"+"         " +localeCurrency.format(item.cost, {places: 5, locale: dojo.locale}) + "  /" + translator.common.gb + "/" + translator.common.hour;
                            dojo.byId("monthlyDiskCost").innerHTML = localeCurrency.format( Math.ceil((item.size * item.cost * 720.00) * 100.00) / 100.00 , {places: 2, locale: dojo.locale})+ "  /" + translator.common.gb + "/" + translator.common.month; 
                            avgCost = Number(tempCost) + Number(computCost) * 720.00 +  Number(item.cost) * 720.00 * Number(item.size );
                            dojo.byId("generalAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + " /" + translator.common.month;
                            dojo.byId("summaryAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + " /" + translator.common.month;
                        }                
                    }                          
                }
            }); 
            if(customWidget == true || customWidget == "true") {
                dijit.byId("instanceCustomDiskSizeSlider").set("minimum", 1);
                dijit.byId("instanceCustomDiskSizeSlider").set("discreteValues", maxSize);
                dijit.byId("instanceCustomDiskSizeSlider").set("maximum", maxSize);
                dijit.byId("instanceCustomDiskSizeSlider").set("value", 1);
                
                dijit.byId("storageDiskSizeWidget").attr("constraints", {max: maxSize, min: 1}); 
                dijit.byId("storageDiskSizeWidget").setValue(1);
                dojo.byId("vmDiskSize").style.display = "block";                
            } else if(customWidget == false || customWidget == "false") {
                dojo.byId("vmDiskSize").style.display = "none";
            }  
        }                      
    },
    showCustomDisk : function(customCheck) {   
        var clusterId;
        var type;
        var diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/byComputingOffer"
        });
        var tempCost = dojo.byId("templateCost").value;
        var computCost = dojo.byId("computingOfferCost").value;
        var diskCost = 0;
        var avgCost = 0;
        
        var diskData = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var diskNoThanksData = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.message.noThanks}]
        };
        
        var diskDataList = new ItemFileWriteStore({data: diskData});
        var diskNoThanksList = new ItemFileWriteStore({data: diskNoThanksData});
        
        var compWidget = dijit.byId("instanceComputOfferWidget");
        var diskWidget = dijit.byId("instanceDiskOfferWidget");
        compWidget.store.fetch({query: {id: compWidget.value},
        
        onItem: function(item) {
            if(item) {                    
                dojo.forEach(item.type, function(el) {
                    type = el;
                });                
                dojo.forEach(item.cluster, function(el) {                    
                    clusterId = el;
                });
            } else {
                diskCost = 0;
            }                        
        }
    });        
    var formElements = dojo.query("#storageTypeDiv input:checked");
    var checkedRadioId = dijit.byId(formElements[0].id);        
    var storageType = dijit.byId(checkedRadioId).value;           
    diskOfferRestStore.query({clusterReferenceId:clusterId,tags: type, customized:customCheck.checked, storageType: storageType}).then(function(data) {               
        if(data.length === 0 || data === "" || data === " " || data == "undefined") {                
            dojo.byId("instanceDiskName").innerHTML = translator.common.message.noDisk;
            dojo.byId("vmDiskSize").style.display = "none";                
            dojo.query("#offeringInfo #diskOfferInfo").removeClass("show_lable", true);                    
            dojo.byId("diskOfferCost").value = "";
            
//            dojo.byId("instanceDiskName").innerHTML = translator.common.message.noDisk;
            dojo.query("#storageDiskOfferContent .updatable").toggleClass("non_updatable", true);
            var noDiskThanksData = {
                identifier: 'id',
                label: 'name',
                items: [{id:"empty", name: translator.common.message.noDisk}]
            };
        
            var noDiskDataList = new ItemFileWriteStore({data: noDiskThanksData});
            diskWidget.setStore(noDiskDataList);
//            if(customCheck.checked == true || customCheck.checked == "true") {
//                                    
//            } else {            
//                dojo.byId("instanceDiskName").innerHTML = ""
//                dojo.query("#storageDiskOfferContent .updatable").removeClass("non_updatable", true);                                   
//            }                
        } else {     
            var min =  Number(data[0].minSize);
            var max = Number(data[0].maxSize);                        
            var discretValue = max - min;  

            dojo.byId("instanceDiskName").innerHTML = "";
            dijit.byId("instanceCustomDiskSizeSlider").set("minimum", min);
            dijit.byId("instanceCustomDiskSizeSlider").set("discreteValues", discretValue);
            dijit.byId("instanceCustomDiskSizeSlider").set("maximum", max);   
            dijit.byId("instanceCustomDiskSizeSlider").set("value", min);   

            dijit.byId("storageDiskSizeWidget").attr("constraints", {max: max, min: min}); 
            dijit.byId("storageDiskSizeWidget").setValue(1);   
            dojo.query("#storageDiskOfferContent .updatable").removeClass("non_updatable", true);  
            var cost = 0.00;
            var minCost = 0.00;
            
            dojo.forEach()
            if(customCheck.checked == true || customCheck.checked == "true") {                                  
                dojo.forEach(data, function(el) {                    
                    dojo.forEach(el.diskOfferZoneCosts, function (zoneCost) {
                        if(dijit.byId("instanceZoneWidget").value === zoneCost.zone.referenceZoneId) {
                            cost = zoneCost.cost;
                            minCost = zoneCost.minimumCost;
                        }
                    });
                    
                    diskDataList.newItem({
                        id:el.diskOfferReferenceId,
                        name: el.name,
                        size:el.size,
                        type:el.tags,
                        cost:cost,
                        minCost:minCost,
                        minSize:el.minSize,
                        maxSize: el.maxSize,
                        custom: el.customized
                    });                    
                });
                diskWidget.setStore(diskDataList);
                
                dojo.byId("diskCost").innerHTML = "  1" + translator.common.gb +"|     " +  localeCurrency.format(cost, {places: 5, locale: dojo.locale}) + "  /" + translator.common.gb + "/" + translator.common.hour;    
                dojo.byId("vmDiskSize").style.display = "block";
                dojo.query("#offeringInfo #diskOfferInfo").toggleClass("show_lable", true);        
                dojo.byId("diskOfferCost").value = cost;
                
            } else if(customCheck.checked == "false" || customCheck.checked == false) {                      
                               
                dojo.forEach(data, function(el) {                    
                    dojo.forEach(el.diskOfferZoneCosts, function (zoneCost) {
                        if(dijit.byId("instanceZoneWidget").value === zoneCost.zone.referenceZoneId) {
                            cost = zoneCost.cost;
                            minCost = zoneCost.minimumCost;
                        }                    
                    }); 
                    
                    diskNoThanksList.newItem({
                        id:el.diskOfferReferenceId,
                        name: el.name,
                        size:el.size,
                        type:el.tags,
                        cost: cost,
                        minCost: minCost,
                        minSize:el.minSize,
                        maxSize: el.maxSize,
                        custom: el.customized
                    });                    
                });
                diskWidget.setStore(diskNoThanksList);
                diskWidget.set("value", "option");    
                
                dojo.byId("diskCost").innerHTML = data[0].size + "  GB  " +  localeCurrency.format(cost, {places: 5, locale: dojo.locale}) + "  /" + translator.common.gb + "/" + translator.common.hour;
                dojo.byId("vmDiskSize").style.display = "none";                     
                dojo.query("#offeringInfo #diskOfferInfo").removeClass("show_lable", true); 
                avgCost = Number(tempCost) + Number(computCost) * 720 +  0;
                dojo.byId("generalAvgCost").innerHTML = "    " + localeCurrency.format(Math.ceil(avgCost), {places: 2, locale: dojo.locale}) + "   /"+translator.common.month;
                dojo.byId("summaryAvgCost").innerHTML = "    " + localeCurrency.format(Math.ceil(avgCost), {places: 2, locale: dojo.locale}) + "   /"+translator.common.month;
                
            }
            } 
    }); 
},
getHyperTemplate:function(currentHypervisor) {    
},
viewOptionsByZone : function(currentZone) {
    var zoneId =  currentZone.value;      
      
    dojo.byId("userTempSelect").style.display = "none";  
    dojo.byId("vmTempLoader").style.display = "block";
    
    var zoneWidget = dijit.byId("instanceZoneWidget");       
    dojo.query("#offeringInfo #computOfferInfo").removeClass("show_lable", true);   
    var tempWidget = dijit.byId("instanceTempWidget");
    if(zoneId === "option") {    
        
         var noTempGroupData = {
            identifier: 'id',
            label: 'name',
            items: [{id:compCount, name: translator.common.message.noTemplate}]
        };
        var noTempDataList = new ItemFileWriteStore({data: noTempGroupData});
        tempWidget.setStore(noTempDataList);  
        tempWidget.set("value", compCount);  
        compCount = compCount + 1;
        dojo.byId("userTempSelect").style.display = "block";  
        dojo.byId("vmTempLoader").style.display = "none";
        
        
        dijit.byId("instanceDiskOfferWidget").set("disabled", true);
        dijit.byId("instanceComputOfferWidget").set("disabled", true);
        dijit.byId("customCheckBox").set("disabled", true); 
        dojo.byId("vmTempLoader").style.display = "none";
        dojo.byId("userTempSelect").style.display = "block";        
        dojo.byId("vmFirewalLoader").style.display = "none";
        dojo.byId("instanceSecurityGroupList").style.display = "block";
        dijit.byId("createButton").set("disabled", true);         
        dijit.byId("instanceFirewalWidget").set("disabled", true); 
        tempWidget.set("disabled", true);         
        
        dojo.byId("vmDiskSize").style.display = "none";                           
        dojo.query("#offeringInfo #diskOfferInfo").removeClass("show_lable", true);
        dojo.query("#offeringInfo #tempDataInfo").removeClass("show_lable", true); 
        dojo.query("#offeringInfo #vmAvgCostInfo").removeClass("show_lable", true); 
        dojo.query("#offeringInfo #summaryInfo").removeClass("show_lable", true);  
//        dojo.byId("summaryAvgCost").innerHTML = "0"; 
        dojo.query("#createVMFirewallDiv").removeClass("hide_text", true);
        dojo.query("#createVMNetworkDiv").toggleClass("hide_text", true);
    } else {   
        var tempRestStrore = new JsonRest({
            target: core.getContextPath()+"/api/template/count"
        });
        
        //main point
        var tempGroupData = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.message.selectTemplate}]
        };
        
        var tempDataList = new ItemFileWriteStore({data: tempGroupData});     
        
        tempRestStrore.query({zoneReferenceId: zoneId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                        
                if(resultData.tempData == undefined || resultData.tempData == "undefined" || resultData.tempData == "") {
                    var noTempGroupData = {
                        identifier: 'id',
                        label: 'name',
                        items: [{id:compCount, name: translator.common.message.noTemplate}]
                    };
                    var noTempDataList = new ItemFileWriteStore({data: noTempGroupData});
                    tempWidget.setStore(noTempDataList);  
                    tempWidget.set("value", compCount);  
                    
                    compCount = compCount + 1;
                    dojo.byId("vmTempLoader").style.display = "none";
                    dojo.byId("userTempSelect").style.display = "block";               
                } else if(resultData.tempData) {
                    dojo.forEach(resultData.tempData, function(el, index) {                                    
                        tempDataList.newItem({
                            id:el.referenceId, 
                            name:el.name, 
                            cost: el.cost, 
                            osType: el.osType,
                            osCategory: el.osCategory,
                            hypervisor:el.hypervisor,
                            size: el.size,
                            baseOs: el.baseOs
                        });                     
                    });                              
                    tempWidget.setStore(tempDataList);
                    tempWidget.set("value", "option");  
                    dojo.byId("vmTempLoader").style.display = "none";
                    dojo.byId("userTempSelect").style.display = "block";
                }            
            });                   
        });
                
        dojo.byId("vmDiskSize").style.display = "none"; 
        dijit.byId("customCheckBox").set("disabled", false);    
        dijit.byId("instanceDiskOfferWidget").set("disabled", false);
        dijit.byId("instanceComputOfferWidget").set("disabled", false);
        dijit.byId("instanceFirewalWidget").set("disabled", false); 
        dijit.byId("createButton").set("disabled", false);         
        tempWidget.set("disabled", false);     
        
        var networkRestStore = new JsonRest({                    
            target: core.getContextPath()+"/api/network"                    
        }); 
        
        var zoneRestStore = new JsonRest({                    
            target: core.getContextPath()+"/api/zone/"                    
        }); 
        
        var networkOptions = {
            identifier: 'id',        
            label: 'name',
            items: [{id:'option', name:translator.common.message.selectNetwork}]
        };
        var networkList = new ItemFileWriteStore({data: networkOptions});
        zoneRestStore.get(zoneId).then(function (data) {
            var zoneID = data.id;
            if(data.networkType === "Advanced") {
                if(dijit.byId("diskSliderRule")) {
                    dijit.byId("diskSliderRule").destroyRecursive();
                }
                networkRestStore.query({zoneReferenceId: data.referenceZoneId}).then(function(data) {                    
                     if ((data === "" || data.length === 0) &&  (isTierOptionEnabled === false)) {                                  
                         dijit.byId("ceateVMNoNetworkDialogue").show();      
                         dojo.byId("gotoNetworkPageTag").href = "#/user/network/add/" + zoneID;
                     } else {         
                         dijit.byId("ceateVMNoNetworkDialogue").hide();
                         dojo.forEach(data, function (currentNetwork) {
                             networkList.newItem({id:currentNetwork.referenceId, name:currentNetwork.name});   
                         });                                               
                     }
                     
                     
                     
                     if(isTierOptionEnabled === false) {
                         
                         if(dijit.byId("instanceNetworkWidget")) {
                             dijit.byId("instanceNetworkWidget").destroyRecursive();
                         }
                         
                         var networkWidget = new Select({
                         id: "instanceNetworkWidget",
                         value: "option",                  
                         store: networkList,
                         sortByLabel: false,
                         maxHeight: 100,
                         onChange: function() {}                
                     });               
                     networkWidget.placeAt("instanceNetworkList");
                     networkWidget.startup(); 
                     }
                     
                 });
                dojo.query("#createVMFirewallDiv").toggleClass("hide_text", true);
                dojo.query("#createVMNetworkDiv").removeClass("hide_text", true);               
            } else {
                dojo.query("#createVMFirewallDiv").removeClass("hide_text", true);
                dojo.query("#createVMNetworkDiv").toggleClass("hide_text", true);
            }             
         })                                    
     }  
},
cancelNetworkOption : function () {
    dijit.byId("instanceZoneWidget").set("value", "option");
    dijit.byId("ceateVMNoNetworkDialogue").hide();
},
cancel : function() {  
    core.router.go("#/user/cloud/userInstancePage");
},       
getComputingOffer :function(template) {  
    var currentEmptyTemp = Number(template.value);       
    var compuringOfferRestStore = new JsonRest({
        target: core.getContextPath()+"/api/computingOffer/"
    }); 
    var securityGroupsTempRestStore = new JsonRest({
        target: core.getContextPath()+"/api/securityGroupTemplate"        
    }); 
    dojo.byId("vmOfferingLoader").style.display = "block";
    dojo.byId("instanceComputingOffer").style.display = "none";                 
    var securityGroupWidget = dijit.byId("instanceFirewalWidget");
    var securityGroupData = {        
        identifier: 'id',
        label: 'name',
        items: [{id:"option", name: translator.common.message.selectFirewall}]
    };
    var securityGroupList = new ItemFileWriteStore({data: securityGroupData});
    var computData = {
        identifier: 'id',
        label: 'name',
        items: [{id: "option", name :translator.common.message.selectOffer}]
    };        
    var diskData = {
        identifier: 'id',
        label: 'name',
        items: []
    };
    var tempCost = 0;
    var computCost = 0;
    var avgCost = 0;
    var computDataList = new ItemFileWriteStore({data: computData});    
    var diskDataList = new ItemFileWriteStore({data: diskData});
    var zoneWidgetValue = dijit.byId("instanceZoneWidget").value;
    
    var ComputingWidget = dijit.byId("instanceComputOfferWidget");    
    var diskWidget = dijit.byId("instanceDiskOfferWidget");
    var baseOs = "";
    dojo.query("#offeringInfo #summaryInfo").removeClass("show_lable", true);
    template.store.fetch({query: {id: template.value},
        onItem: function(item) {            
            if(item.id == "option" || currentEmptyTemp >= 0 || currentEmptyTemp === 0) {              
                dojo.query("#offeringInfo #tempDataInfo").removeClass("show_lable", true);
                dojo.query("#offeringInfo #vmAvgCostInfo").removeClass("show_lable", true);                                
                var noFirewallData = {        
                    identifier: 'id',
                    label: 'name',
                    items: [{id:"option", name: translator.common.message.selectFirewall }]
                };
                var noFirewallList = new ItemFileWriteStore({data: noFirewallData});
                securityGroupWidget.setStore(noFirewallList);
                dojo.byId("vmDiskSize").style.display = "none";                    
                dijit.byId("customCheckBox").set("disabled", true); 
                ComputingWidget.setStore(computDataList);                                            
                diskWidget.set("value","option");                        
                dojo.byId("vmOfferingLoader").style.display = "none";
                dojo.byId("instanceComputingOffer").style.display = "block";                        
                dojo.byId("vmFirewalLoader").style.display = "none";
                dojo.byId("instanceSecurityGroupList").style.display = "block";
                
                dijit.byId("instanceComputOfferWidget").set("disabled", true);
                dijit.byId("instanceDiskOfferWidget").set("disabled", true);
                dijit.byId("instanceFirewalWidget").set("disabled", true);
                dijit.byId("instanceSSHkeyWidget").set("disabled", true);
            } else {                
                dojo.query("#offeringInfo #tempDataInfo").toggleClass("show_lable", true);
                dojo.query("#offeringInfo #vmAvgCostInfo").removeClass("show_lable", true);
                dojo.byId("summaryAvgCost").innerHTML = item.cost; 
                dijit.byId("customCheckBox").set("disabled", false);                
                dojo.byId("tempCost").innerHTML = item.name + "    |    "+localeCurrency.format(item.cost, {places: 2, locale: dojo.locale}) + "      /" + translator.common.month;
                dojo.byId("templateCost").value = item.cost;                    
                tempCost = Number(item.cost);                  
                baseOs = item.baseOs;
                dojo.byId("vmFirewalLoader").style.display = "block";
                dojo.byId("instanceSecurityGroupList").style.display = "none";

                dijit.byId("instanceComputOfferWidget").set("disabled", false);
                dijit.byId("instanceDiskOfferWidget").set("disabled", false);
                dijit.byId("instanceFirewalWidget").set("disabled", false);
                dijit.byId("instanceSSHkeyWidget").set("disabled", false);
            }                                                  
        }
    });       
    var zoneId = dijit.byId("instanceZoneWidget").value;                
    var formElements = dojo.query("#storageTypeDiv input:checked");
    var checkedRadioId = dijit.byId(formElements[0].id);        
    var storageType = dijit.byId(checkedRadioId).value;            
    if(template.value == "option" || template.value == "empty" || currentEmptyTemp >= 0) {         
    } else {                      
        compuringOfferRestStore.query({zoneReferenceId:zoneId, templateReferenceId:template.value, storageType: storageType}).then(function(data) {
            var noComputData = {
                identifier: 'id',
                label: 'name',
                items: [{id: compCount, name :translator.common.message.noPlan}]
            }; 
            var noPlanList = new ItemFileWriteStore({data: noComputData});            
            if((data.length === 0)||(data === "null") || data[0].localizedMessage) {
                dojo.byId("vmOfferingLoader").style.display = "none";
                dojo.byId("instanceComputingOffer").style.display = "block";
                ComputingWidget.setStore(noPlanList);
                compCount = compCount + 1;
                ComputingWidget.set("value", compCount);                
                compCount = compCount + 1;
            } else if(data[0]) {                  
                if(data[0].computingOfferZoneCosts.length !== 0) {
                    dojo.byId("computCost").innerHTML = "|     " + data[0].computingOfferZoneCosts[0].cost + " /" + translator.common.hour;
                    computCost = Number(data[0].computingOfferZoneCosts[0].cost) * 720.00;
                }
                
                avgCost = tempCost;
                dojo.byId("generalAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + " /" + translator.common.month;     
                dojo.byId("summaryAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + " /" + translator.common.month;     
                dojo.forEach(data, function(resultData) {
                    var cost = 0.00;
                    var setupCost = 0.00;
                    if(resultData.computingOfferZoneCosts.length !== 0) {
                        dojo.forEach(resultData.computingOfferZoneCosts, function (zoneData) {
                            if(zoneData.zone.referenceZoneId === zoneWidgetValue) {
                                cost = zoneData.cost;
                                setupCost = zoneData.setupCost;
                            }
                        });
                        computDataList.newItem({
                            id: resultData.offerReferenceId,
                            name: resultData.name,
                            type: resultData.tags,
                            bandwidth: resultData.bandWidth,
                            memory: resultData.memory,
                            networkRate: resultData.networkRate,
                            cost: cost,
                            setupCost: setupCost,  
                            speed : resultData.coreCpuSpeed,
                            core: resultData.cpuNumber
                        });
                    }                    
                });
                ComputingWidget.setStore(computDataList);
                ComputingWidget.set("value", "option")
                dojo.byId("vmOfferingLoader").style.display = "none";
                dojo.byId("instanceComputingOffer").style.display = "block";
            } else { 
                dojo.byId("vmOfferingLoader").style.display = "none";
                dojo.byId("instanceComputingOffer").style.display = "block";  
                ComputingWidget.setStore(noPlanList);
                compCount = compCount + 1;
            }             
        });                       
        securityGroupsTempRestStore.query({baseOs: baseOs, disabled:false}).then(function(data) {
            dojo.forEach(data, function(securityData) {
                if(securityData== undefined || securityData == "undefined" || securityData == "") {                
                    securityGroupList.newItem({
                        id:"option",
                        name: translator.common.message.noFirewall                   
                    });
                    securityGroupWidget.setStore(securityGroupList);
                    securityGroupWidget.set("value","option");
                    dojo.byId("vmFirewalLoader").style.display = "none";
                    dojo.byId("instanceSecurityGroupList").style.display = "block";
                } else {
                    securityGroupList.newItem({
                        id:securityData.securityGroupId, 
                        name:securityData.securityGroupName, 
                        description: securityData.description
                    });
                }
            });
            securityGroupWidget.setStore(securityGroupList);
            securityGroupWidget.set("value","option");
            
            dojo.byId("vmFirewalLoader").style.display = "none";
            dojo.byId("instanceSecurityGroupList").style.display = "block";
        });
    }        
}, 
getServices : function(currentWidget) {            
    var clusterId;
    var type;
    var noThanksDiskData = {
        identifier: 'id',
        label: 'name',
        items: [{id:"option", name:translator.common.message.noThanks}]         
    };
    dojo.byId("vmDiskLoader").style.display = "block";
    dojo.byId("instanceDiskSlider").style.display = "none";
    var diskData = {
        identifier: 'id',
        label: 'name',
        items: []         
    };            
    var diskDataList = new ItemFileWriteStore({data: diskData});
    var noThanksDiskDataList = new ItemFileWriteStore({data: noThanksDiskData});
    var tempCost = 0;            
    var avgCost = 0;
            
    tempCost = dojo.byId("templateCost").value;
    currentWidget.store.fetch({query: {id: currentWidget.value},
        onItem: function(item) {
            var idNum = Number(item.id)
            if(item.id == "option" || item.id == "empty" || idNum >= 0) {
                dojo.query("#offeringInfo #computOfferInfo").removeClass("show_lable", true);   
                dojo.query("#offeringInfo #summaryInfo").removeClass("show_lable", true);                 
            } else {
                dojo.query("#offeringInfo #summaryInfo").toggleClass("show_lable", true); 
                dojo.byId("computingOfferCost").value = item.cost;                        
                avgCost = Number(tempCost) + Number(item.cost * 720.00);
                dojo.byId("generalAvgCost").innerHTML = "     " + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) +  "     /" + translator.common.month;
                dojo.byId("summaryAvgCost").innerHTML = "     " + localeCurrency.format(Math.ceil(avgCost * 100.00) / 100.00, {places: 2, locale: dojo.locale}) +  "     /" + translator.common.month;
                dojo.forEach(item.cluster, function(el) {
                    clusterId = el;
                });
                dojo.forEach(item.type, function(el) {
                    type = el;
                });
                dojo.byId("computCost").innerHTML = item.core + "x" + item.memory + " MB |        " + localeCurrency.format(item.cost, {places: 5, locale: dojo.locale}) + " /" + translator.common.hour;    
                dojo.byId("monthlyComputCost").innerHTML = localeCurrency.format(Math.ceil((item.cost * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale})  + " /" + translator.common.month;
            }                                      
        }
    });
    var idNum = Number(currentWidget.value)
    if(currentWidget.value == "option" || currentWidget.value == "empty" || idNum >= 0) {
        dijit.byId("instanceDiskOfferWidget").setStore(diskDataList);
        dojo.query("#offeringInfo #computOfferInfo").removeClass("show_lable", true);
        dojo.query("#offeringInfo #vmAvgCostInfo").removeClass("show_lable", true); 
//        dojo.byId("summaryAvgCost").innerHTML = "0"; 
        dojo.query("#offeringInfo #diskOfferInfo").removeClass("show_lable", true); 
//        dojo.byId("summaryAvgCost").innerHTML = ""; 
        dojo.byId("vmDiskLoader").style.display = "none";
        dojo.byId("instanceDiskSlider").style.display = "block";        
        dijit.byId("instanceDiskOfferWidget").store.newItem({id: "option", name: translator.common.message.selectDiskOffer});

        dojo.byId("instanceDiskSlider").style.display = "block";
        dojo.byId("vmDiskSize").style.display = "none";        
    } else {
        dijit.byId("instanceCustomDiskSizeSlider").set("disabled", false);
        dojo.query("#offeringInfo #computOfferInfo").toggleClass("show_lable", true);
        dojo.query("#offeringInfo #vmAvgCostInfo").toggleClass("show_lable", true);
        dijit.byId("createButton").set("disabled", false); 
        var customWidget = dijit.byId("customCheckBox").checked;
        var diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/byComputingOffer"
        });        
        var formElements = dojo.query("#storageTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var storageType = dijit.byId(checkedRadioId).value;             
        diskOfferRestStore.query({clusterReferenceId:clusterId, tags: type, customized:customWidget, storageType: storageType}).then(function(data) {                 
            if(data == "" || data == "") {
                dojo.byId("instanceDiskName").innerHTML = translator.common.message.noDisk;
                dojo.query("#storageDiskOfferContent .updatable").toggleClass("non_updatable", true);
                dojo.byId("vmDiskLoader").style.display = "none";
                dojo.byId("instanceDiskSlider").style.display = "block";
                dijit.byId("instanceDiskOfferWidget").set("value","option");                                               
            } else if(data) {
                dijit.byId("instanceCustomDiskSizeSlider").set("minimum", 1);
                dijit.byId("instanceCustomDiskSizeSlider").set("discreteValues", data[0].maxSize);
                dijit.byId("instanceCustomDiskSizeSlider").set("maximum", data[0].maxSize); 
                dojo.query("#storageDiskOfferContent .updatable").removeClass("non_updatable", true);
                dojo.byId("instanceDiskName").innerHTML = "";    
                var cost = 0.00;
                var minCost = 0.00;
                
                if(customWidget == false) {
                    dojo.byId("vmDiskSize").style.display = "none";                   
                    dojo.byId("diskOfferCost").value = "";
                    dojo.query("#offeringInfo #diskOfferInfo").removeClass("show_lable", true);                                                      
                    dojo.forEach(data, function(el) {
                        
                        dojo.forEach(el.diskOfferZoneCosts, function (zoneCost) {
                            if(dijit.byId("instanceZoneWidget").value === zoneCost.zone.referenceZoneId) {
                                cost = zoneCost.cost;
                                minCost = zoneCost.minimumCost;
                            }                    
                        }); 
                        noThanksDiskDataList.newItem({
                            id:el.diskOfferReferenceId,
                            name: el.name,
                            size:el.size,
                            type:el.tags,
                            cost: cost,
                            minCost:minCost,
                            minSize:el.minSize,
                            maxSize: el.maxSize,
                            custom: el.customized
                        });                    
                    });
                    dijit.byId("instanceDiskOfferWidget").setStore(noThanksDiskDataList);
                    dijit.byId("instanceDiskOfferWidget").set("value","option");
                    
                    dojo.byId("vmDiskLoader").style.display = "none";
                    dojo.byId("instanceDiskSlider").style.display = "block";
                } else if(customWidget == true || customWidget == "true") { 
                    dojo.byId("vmDiskSize").style.display = "block";
                    dojo.query("#offeringInfo #diskOfferInfo").toggleClass("show_lable", true);
                    var costCeil = (Number(avgCost) + (data[0].diskOfferZoneCosts[0].cost * 720.00 * data[0].minSize))                                                        
                    dojo.byId("generalAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil((costCeil * 100.00) / 100.00), {places: 2, locale: dojo.locale}) + " /" + translator.common.month;
                    dojo.byId("summaryAvgCost").innerHTML = "" + localeCurrency.format(Math.ceil((costCeil * 100.00) / 100.00), {places: 2, locale: dojo.locale}) + " /" + translator.common.month;
                    dojo.byId("diskOfferCost").value = data[0].diskOfferZoneCosts[0].cost;                            
                    var firstValue = ""
                    dojo.forEach(data, function(el, index) {
                        
                        dojo.forEach(el.diskOfferZoneCosts, function (zoneCost) {
                            if(dijit.byId("instanceZoneWidget").value === zoneCost.zone.referenceZoneId) {
                                cost = zoneCost.cost;
                                minCost = zoneCost.minimumCost;
                            }                    
                        }); 
                        
                        diskDataList.newItem({
                            id:el.diskOfferReferenceId,
                            name: el.name,
                            size:el.size,
                            type:el.tags,
                            cost:cost,
                            minCost:minCost,
                            minSize:el.minSize,
                            maxSize: el.maxSize,
                            custom: el.customized
                        });                            
                        if(index == 0) {
                            firstValue =  el.diskOfferReferenceId;
                        }
                    });
                    dijit.byId("instanceDiskOfferWidget").setStore(diskDataList);
                    dijit.byId("instanceDiskOfferWidget").set("value", firstValue);

                    dojo.byId("vmDiskLoader").style.display = "none";
                    dojo.byId("instanceDiskSlider").style.display = "block";
                }
            }                       
        }); 
    }
},
add: function() {     
    var securityGroupRestStoreStore = new JsonRest({
        target: core.getContextPath()+"/api/securityGroup/"         
    });
    var vmNameWidget = dijit.byId("instanceName");            
    var zoneWidget = dijit.byId("instanceZoneWidget");
    var tempWidget = dijit.byId("instanceTempWidget");
    var computWidget = dijit.byId("instanceComputOfferWidget");            
    var securityGroupWidget = dijit.byId("instanceFirewalWidget");
    var compuNumValue = Number(dijit.byId("instanceComputOfferWidget").value)
    var vmName = dijit.byId("instanceName").value; 
    var networkWidget = dijit.byId("instanceNetworkWidget")
    var currentEmptyTemp = Number(tempWidget.value);  
    if (vmNameWidget.validate && !vmNameWidget.validate()) {
        vmNameWidget.focus();                                     
    } else if(zoneWidget.value == "option" || zoneWidget.value == "empty" || tempWidget.value == "option" || tempWidget.value == "empty" ||  computWidget.value == "option" || computWidget.value == "empty" || compuNumValue >= 0 || currentEmptyTemp >= 0 || currentEmptyTemp === 0) {
        dojo.byId("createVMErrorMessage").style.display = "block";
    } else {        
        dojo.byId("createVMErrorMessage").style.display = "none";
        var status = ""
        zoneWidget.store.fetch({query: {id: zoneWidget.value},
            onItem: function(item) {
                if(item) {
                    status = item.networkType;                    
                }
            }
        }); 
        
        if(status == "Advanced") {
            if(networkWidget.value == "option" || networkWidget.value == "empty") {
                dojo.byId("createVMErrorMessage").style.display = "block";                
            } else {
                dojo.byId("createVMErrorMessage").style.display = "none";                
                dijit.byId("createButton").set("disabled", true);
                dijit.byId("instanceName").set("disabled", true);
                dijit.byId("instanceZoneWidget").set("disabled", true);
                dijit.byId("instanceTempWidget").set("disabled", true);
                dijit.byId("instanceComputOfferWidget").set("disabled", true);
                dijit.byId("customCheckBox").set("disabled", true);
                dijit.byId("instanceCustomDiskSizeSlider").set("disabled", true);
                dijit.byId("instanceFirewalWidget").set("disabled", true);
                dijit.byId("instanceDiskOfferWidget").set("disabled", true);   
                dijit.byId("instanceSSHkeyWidget").set("disabled", true); 

                dijit.byId("sharedStorageType").set("disabled", true); 
                dijit.byId("localStorageType").set("disabled", true); 

                // added for billing type in create vm by gowtham
                dijit.byId("hourlyBilling").set("disabled", true); 
                dijit.byId("monthlyBilling").set("disabled", true); 

//                dojo.byId("instanceLoader").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p class='span12'>"+translator.common.instance.creatingInstance+"</p>";       
                if(isTierOptionEnabled === true) {
                    dijit.byId("createTierVMLoadingDialog").show();   
                } else {
                    dijit.byId("createVMLoading").show();   
                }
                
                var networkId = networkWidget.value;
                CreateVMInfo.createVM(networkId, "Advanced");
            }
        } else { 
            if(securityGroupWidget.value == "option" || securityGroupWidget.value == "empty") {
                dojo.byId("createVMErrorMessage").style.display = "block";
            } else {
                dojo.byId("createVMErrorMessage").style.display = "none";
                
                dijit.byId("createButton").set("disabled", true);
                dijit.byId("instanceName").set("disabled", true);
                dijit.byId("instanceZoneWidget").set("disabled", true);
                dijit.byId("instanceTempWidget").set("disabled", true);
                dijit.byId("instanceComputOfferWidget").set("disabled", true);
                dijit.byId("customCheckBox").set("disabled", true);
                dijit.byId("instanceCustomDiskSizeSlider").set("disabled", true);
                dijit.byId("instanceFirewalWidget").set("disabled", true);
                dijit.byId("instanceDiskOfferWidget").set("disabled", true);   
                dijit.byId("instanceSSHkeyWidget").set("disabled", true); 

                dijit.byId("sharedStorageType").set("disabled", true); 
                dijit.byId("localStorageType").set("disabled", true); 

                // added for billing type in create vm by gowtham
                dijit.byId("hourlyBilling").set("disabled", true); 
                dijit.byId("monthlyBilling").set("disabled", true); 

//                dojo.byId("instanceLoader").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p class='span12'>"+translator.common.instance.creatingInstance+"</p>";       
                dijit.byId("createVMLoading").show();   
                
                var securityGroupId =  dijit.byId("instanceFirewalWidget").value;  
                var securityGroupName;
                var securityGroupDesc;
                securityGroupWidget.store.fetch({query: {id: securityGroupWidget.value},
                    onItem: function(item) {
                        dojo.forEach(item.name, function(el) {
                            securityGroupName = el;
                        }); 
                        dojo.forEach(item.description, function(el) {
                            securityGroupDesc = el;
                        });                             
                    }
                });            

                securityGroupRestStoreStore.add({
                    name: securityGroupName,
                    securityGroupTemplateId: securityGroupId,
                    description : securityGroupDesc,
                    vmName: vmName
                }).then(function(data) {                
                    dojo.forEach(data, function(resultData) {
                        if(resultData.result == "OK" || resultData.result == "Exist") { 
                            CreateVMInfo.createVM(resultData.securityGroupId, resultData.result);                        
                        } else if(resultData.result == "Exist") {                       
                            dijit.byId("createButton").set("disabled", true);                               
//                            dojo.byId("instanceLoader").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p class='span12'>"+translator.common.instance.creatingInstance+"</p>";
                            dijit.byId("createVMLoading").show(); 
                             dijit.byId("createTierVMLoadingDialog").hide(); 
                            CreateVMInfo.createVM(resultData.securityGroupId, resultData.result);
                        }  else {
                            registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                            registry.byId("userToaster").show();
                            dijit.byId("createButton").set("disabled", false);
                            dijit.byId("instanceName").set("disabled", false);
                            dijit.byId("instanceZoneWidget").set("disabled", false);
                            dijit.byId("instanceTempWidget").set("disabled", false);
                            dijit.byId("instanceComputOfferWidget").set("disabled", false);
                            dijit.byId("instanceDiskOfferWidget").set("disabled", false);   
                            dijit.byId("customCheckBox").set("disabled", false);
                            dijit.byId("instanceCustomDiskSizeSlider").set("disabled", false);
                            dijit.byId("instanceFirewalWidget").set("disabled", false);
                            dijit.byId("instanceSSHkeyWidget").set("disabled", false); 

                            dijit.byId("sharedStorageType").set("disabled", false); 
                            dijit.byId("localStorageType").set("disabled", false); 
//                            dojo.byId("instanceLoader").innerHTML = "";
                            dijit.byId("createVMLoading").hide();    
                            dijit.byId("createTierVMLoadingDialog").hide(); 
                            // added for billing type in create vm by gowtham
                            dijit.byId("hourlyBilling").set("disabled", false); 
                            dijit.byId("monthlyBilling").set("disabled", false); 
                        }                 
                    });      
                });
            }
        }                
    }
},
createVM : function(currentSecurityGroupId, securityGroupResult) {       
    var instanceName = dijit.byId("instanceName").value;   
    var networkId = "";    
    
    if(securityGroupResult == "Advanced") {
        networkId = currentSecurityGroupId        
    } else {
        networkId = "";        
    }
    var securityGroupRestStore = new JsonRest({
        target: core.getContextPath()+"/api/securityGroup/"         
    });        
    // added for billing type in create vm by gowtham
    var formElements = dojo.query("#billingTypeDiv input:checked");
    var checkedRadioId = dijit.byId(formElements[0].id);        
    var billingType = dijit.byId(checkedRadioId).value;        
    var customSize = "";              
    var diskOfferId =  dijit.byId("instanceDiskOfferWidget").value;
    var diskId = "";
    var diskSize;
    this._zoneWidget = dijit.byId("instanceZoneWidget");
    if(diskOfferId == "option" ||diskOfferId == "empty") {
        diskId = "";
        diskSize = "";
    } else {
        diskId = diskOfferId;
        customSize = dijit.byId("storageDiskSizeWidget").value; 
        diskSize = customSize.toString();
    }
    var computId = dijit.byId("instanceComputOfferWidget").value;           
        if(!dijit.byId("instanceName").validate()) {
                dijit.byId("instanceName").focus();
            } else {
                this._instanceRestStore.add({
                    instanceName : instanceName,
                    zoneId: this._zoneWidget.value,
                    networkId : networkId,                    
                    tempId: dijit.byId("instanceTempWidget").value,
                    computingOfferId: computId,
                    diskOfferId: diskId,
                    diskSize:diskSize,                    
                    securityGroupReferenceId: currentSecurityGroupId,
                    keyPair : dijit.byId("instanceSSHkeyWidget").value,
                    billingType : billingType
            }).then(function(result) {             
                dojo.forEach(result, function(resultData) {
                    if(resultData.result == "OK") {                                       
                        var vmCreateJobStatus = setInterval(function(){CreateVMInfo.vmCreateJob(resultData.jobId, vmCreateJobStatus, currentSecurityGroupId);},3000);                   
                        if(securityGroupResult == "OK") {
                            CreateVMInfo.createRules(currentSecurityGroupId);  
                        } else {                 
                            return;
                        }                    
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.vmError, "error");
                        registry.byId("userToaster").show();

                        dijit.byId("createButton").set("disabled", false);
                        dijit.byId("instanceName").set("disabled", false);
                        dijit.byId("instanceZoneWidget").set("disabled", false);
                        dijit.byId("instanceTempWidget").set("disabled", false);
                        dijit.byId("instanceComputOfferWidget").set("disabled", false);
                        dijit.byId("customCheckBox").set("disabled", false);
                        dijit.byId("instanceCustomDiskSizeSlider").set("disabled", false);
                        dijit.byId("instanceFirewalWidget").set("disabled", false);
                        dijit.byId("instanceDiskOfferWidget").set("disabled", false);    
                        dijit.byId("instanceSSHkeyWidget").set("disabled", false); 

                        dijit.byId("sharedStorageType").set("disabled", false); 
                        dijit.byId("localStorageType").set("disabled", false); 

                        // added for billing type in create vm by gowtham
                        dijit.byId("hourlyBilling").set("disabled", false); 
                        dijit.byId("monthlyBilling").set("disabled", false);                                        
//                        dojo.byId("instanceLoader").innerHTML = " ";
                        if(isTierOptionEnabled === true) {
                            dijit.byId("createTierVMLoadingDialog").hide();   
                        } else {
                            dijit.byId("createVMLoading").hide();   
                        }                   
                    }
                });                                                            
            });
        }            
    },
    vmCreateJob: function(jobId, vmCreateJobStatus, currentSecurityGroupId) {         
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/createVm/job/"
        });         
         var securityGroupRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/"         
        });
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult == "OK") {                      
                    clearInterval(vmCreateJobStatus);
                    registry.byId("userToaster").setContent(translator.common.instance.created,"message");
                    registry.byId("userToaster").show();                       
                    dijit.byId("createButton").set("disabled", false);
                    dojo.byId("instanceLoader").innerHTML = " ";
                    if(isTierOptionEnabled === true) {
                        dijit.byId("createTierVMLoadingDialog").hide();  
                        var currentTier = dojo.byId("currentTierId").value;
                        core.router.go("#/user/tier/instance");
                    } else {
                        dijit.byId("createVMLoading").hide();   
                        core.router.go("#/user/cloud/userInstancePage");
                    }
                    
                 } else if(resultData.jobResult == "Pending") {

                } else  if(resultData.jobResult == "FAILED") {
                    clearInterval(vmCreateJobStatus);                    
                    registry.byId("userToaster").setContent(translator.common.instance.vmError,"error");
                    registry.byId("userToaster").show();                    
//                    securityGroupRestStore.remove(currentSecurityGroupId).then(function(result) {
//                    });   
                    dijit.byId("createButton").set("disabled", false);
                    dojo.byId("instanceLoader").innerHTML = " ";
                    if(isTierOptionEnabled === true) {
                        dijit.byId("createTierVMLoadingDialog").hide();   
                    } else {
                        dijit.byId("createVMLoading").hide();   
                    }                 
                }
                
                if(resultData.jobResult == "OK" || resultData.jobResult == "FAILED") {
                    dijit.byId("createButton").set("disabled", false);
                    dijit.byId("instanceName").set("disabled", false);
                    dijit.byId("instanceZoneWidget").set("disabled", false);
                    dijit.byId("instanceTempWidget").set("disabled", false);
                    dijit.byId("instanceComputOfferWidget").set("disabled", false);
                    dijit.byId("customCheckBox").set("disabled", false);
                    dijit.byId("instanceCustomDiskSizeSlider").set("disabled", false);
                    dijit.byId("instanceFirewalWidget").set("disabled", false);
                    dijit.byId("instanceSSHkeyWidget").set("disabled", false); 
                    dijit.byId("instanceDiskOfferWidget").set("disabled", false);    
                    dijit.byId("sharedStorageType").set("disabled", false); 
                    dijit.byId("localStorageType").set("disabled", false); 
                    
                    
                    // added for billing type in create vm by gowtham
                    dijit.byId("hourlyBilling").set("disabled", false); 
                    dijit.byId("monthlyBilling").set("disabled", false);
                }
                
            });
        });
    },
    cancelPopup : function () {
        dijit.byId("createVMLoading").hide();    
    },
    gotoInstances : function () {
        dijit.byId("createVMLoading").hide(); 
        core.router.go("#/user/cloud/userInstancePage");
    },
    gotoTierInstances : function () {
        var currentTier = dojo.byId("currentTierId").value;
        dijit.byId("createTierVMLoadingDialog").hide(); 
        core.router.go("#/user/tier/instance");
    },
    gotoVPCDashboard : function () {
        dijit.byId("createTierVMLoadingDialog").hide(); 
        core.router.go("#/user/vpc/dashboard");
    },
    gotoDashboard : function () {
        dijit.byId("createVMLoading").hide();   
        core.router.go("#/user/home");
    },
    sendSuccessMail : function(vmInfo) {             
        var vmSendSuccessMailRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/sendEmail"    
        });
        var passoword; 
        var ipAddress;
        if(vmInfo.password == null || vmInfo.password == 'null' || vmInfo.password == "" || vmInfo.password == "undefined" || vmInfo.password == undefined) {
            passoword = "null";
        } else {
            passoword = vmInfo.password.toString();
        }
        if(vmInfo.ipAddress == null || vmInfo.ipAddress == 'null' || vmInfo.ipAddress == "" || vmInfo.ipAddress == "undefined" || vmInfo.ipAddress == undefined) {
            ipAddress = "null";
        } else {
            ipAddress = vmInfo.ipAddress.toString();
        }
        vmSendSuccessMailRestStore.add({
            vmName : vmInfo.instanceName,
            hostName : vmInfo.name,
            osType : vmInfo.osType,
            password: passoword,
            ipAddress: ipAddress,
            state: vmInfo.state,
            offerName: vmInfo.offerName,
            cpuNumber: vmInfo.cpuNumber.toString(),
            coreCpuSpeed: vmInfo.coreCpuSpeed.toString(),
            bandWidth: vmInfo.bandWidth.toString(),
            setupCost: vmInfo.setupCost[0].toString(),
            templateSize: vmInfo.templateSize.toString(),
            templateCost: vmInfo.templateCost.toString(),
            diskName: vmInfo.diskName,
            diskSize: vmInfo.diskSize.toString(),
            diskCost: vmInfo.diskCost.toString(),
            nextBillingDate: vmInfo.nextBillingDate.toString(),
            zoneName: vmInfo.zoneName,
            memory: vmInfo.memory.toString(),            
            runningCost : vmInfo.RunningCost[0].toString()
        });
    },
    createRules : function(currentSecurityGroupId) {
        
        var securityGroupIngressRestStoreStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/ingress"         
        });
        var securityGroupEgressRestStoreStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/egress"         
        });

        var securityGroupTempRestStoreStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/"         
        });
        var securityGroupId =  dijit.byId("instanceFirewalWidget").value;              

        securityGroupTempRestStoreStore.query({securityGroupTemplateId: securityGroupId, securityGroupType: "INGRESS"}).then(function(data) {
            if(data.length != 0) {
                dojo.forEach(data, function(ingressData) {
                    securityGroupIngressRestStoreStore.add({
                        protocol: ingressData.securityGroupPort.name, 
                        startPort: ingressData.startPort.toString(), 
                        endPort: ingressData.endPort.toString(),
                        cidr: ingressData.cidr,
                        securityGroupId: currentSecurityGroupId
                    });
                });
            }
        });                        

    securityGroupTempRestStoreStore.query({securityGroupTemplateId: securityGroupId, securityGroupType: "EGRESS"}).then(function(data) {
        if(data.length != 0) {
            dojo.forEach(data, function(egressData) {                
                securityGroupEgressRestStoreStore.add({
                    protocol: egressData.securityGroupPort.name, 
                    startPort: egressData.startPort.toString(), 
                    endPort: egressData.endPort.toString(),
                    cidr: egressData.cidr,
                    securityGroupId: currentSecurityGroupId
                }); 
            });
        }
    });            
    }    
    
};
        
    var UserInstances = {
        _zoneWidget:"",        
        _isoWidget: "",
        _tempWidget: "",
        _networkOfferWidget: "",
        _changeServiceWidget:"",
        _zoneRestStore:"",
        _tempRestStore: "",
        _computingOfferRestStore: "",
        _diskOfferRestStore:"",
        _networkOfferRestStore:"",
        _instanceRestStore:"",
        _currentInstanceWidget:"",
        _isoRestStore:"",
        _instanceGrid:"",
        _instanceCountRestStore:"",
        
        init: function() {
            
            this._zoneRestStore = new JsonRest({
                target: core.getContextPath()+"/api/zone/"
            });
            
            this._tempRestStrore = new JsonRest({
                target: core.getContextPath()+"/api/template/"
            });
            
            this._computingOfferRestStore = new JsonRest({
                target: core.getContextPath()+"/api/computingOffer/"
            });
            
            this._diskOfferRestStore = new JsonRest({
                target: core.getContextPath()+"/api/diskOffer/"
            });
            
            this._networkOfferRestStore = new JsonRest({
                target: core.getContextPath()+"/api/networkOffer/"
            });   
            
            this._instanceRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/"
            });
            
            this._instanceCountRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/count"
            });
            
             this._isoRestStore = new JsonRest({
                target: core.getContextPath()+"/api/iso/"
            });
                       
            
            var accountResourceLimitStore = new JsonRest({
                target: core.getContextPath()+"/api/account/getCloudResourceStat"
            });

            accountResourceLimitStore.query().then(function(data) {
                 dojo.forEach(data, function(limitData) {                       
                    if(limitData.vmLimit !== "-1" || limitData.cpuLimit !== "-1" || limitData.memoryLimit !== "-1" || limitData.primaryStorageLimit !== "-1" || limitData.storageLimit !== "-1" ) {                       
                        if(((Number(limitData.vmUsed) >= Number(limitData.vmLimit)) && (limitData.vmLimit !== "-1")) || ((Number(limitData.cpuUsed) >= Number(limitData.cpuLimit)) && (limitData.cpuLimit !== "-1")) || ((Number(limitData.memoryUsed) >= Number(limitData.memoryLimit)) && (limitData.memoryLimit !== "-1")) || ((Number(limitData.primaryStorageLimitUsage) >= Number(limitData.primaryStorageLimit)) && (limitData.primaryStorageLimit !== "-1")) || ((Number(limitData.storageUsed) >= Number(limitData.storageLimit)) && (limitData.storageLimit !== "-1"))) {                                
                            dojo.byId("vmLimitReached").style.display = "block";                            
                            dojo.query("#createVmButtom").toggleClass("hide_text", true);
                        } else {
                            dojo.byId("vmLimitReached").style.display = "none";
                            dojo.query("#createVmButtom").removeClass("hide_text", true);                            
                        }
                    } else {
                        dojo.byId("vmLimitReached").style.display = "none";
                        dojo.query("#createVmButtom").removeClass("hide_text", true);
                    }
                 });
             });            
        },
        
        getCardStatus : function() {
            core.router.go("#/user/cloud/createVm"); 
        },
        showCustomDisk : function(customCheck) {         
          var zoneWidget = dijit.byId("instanceZoneWidget").value;
          
            if(dijit.byId("diskSliderRule")) {
                  dijit.byId("diskSliderRule").destroyRecursive();
            }
            var diskOfferRestStore = new JsonRest({
                target: core.getContextPath()+"/api/diskOffer/"
            });
          if(customCheck.checked == true) {
               dojo.byId("vmDiskSize").style.display = "block";
               dojo.byId("instanceDiskCost").style.display = "none";   
                    diskOfferRestStore.query({zoneReferenceId:zoneWidget, customized:true}).then(function(data) {
                                      
                       dijit.byId("customDiskSize").attr("constraints", {max: data[0].maxSize, min: data[0].minSize}); 
                       dijit.byId("customDiskSize").setValue(data[0].minSize); 
                        
                        if(data.length == 1) {                             
                            dojo.byId("vmDiskOffer").style.display = "none";                                                     
                            var cost = (data[0].diskOfferZoneCosts[0].cost * 1).toFixed(5);
                            dojo.byId("instanceDiskCost").innerHTML = " " + (cost);
                            dojo.byId("instanceCustDiskCost").innerHTML = " " + (cost);
                            dojo.byId("vmDiskName").value = data[0].diskOfferReferenceId;
                            dojo.byId("vmDiskCost").innerHTML = (cost);

                            dojo.byId("instanceAvgCost").innerHTML = (Number(dojo.byId("vmCompCost").innerHTML) + Number(cost)).toFixed(5);                                                    
                        } else {                                                       
                            dijit.byId("instanceDiskSlider").set("minimum", data[0].minSize);
                            dijit.byId("instanceDiskSlider").set("discreteValues", data.length);
                            dijit.byId("instanceDiskSlider").set("maximum", data.length);   
                            dojo.byId("vmDiskOffer").style.display = "block";                               
                            dojo.byId("instanceDiskName").innerHTML = data[0].name;
                            dojo.byId("instanceDiskName").innerHTML = data[0].name;
                            dojo.byId("instanceDiskSize").innerHTML =translator.common.size.minSize +  data[0].minSize + translator.common.gb + translator.common.size.maxSize + data[0].maxSize + translator.common.gb;    
                            dojo.byId("instanceDiskCost").innerHTML = " " + (data[0].diskOfferZoneCosts[0].cost * data[0].size).toFixed(5) + "/"+translator.common.gb;
                            dojo.byId("vmDiskName").value = data[0].diskOfferReferenceId;
                            dojo.byId("vmDiskCost").innerHTML = (data[0].diskOfferZoneCosts[0].cost * data[0].size).toFixed(5);
                            dojo.byId("instanceAvgCost").innerHTML =  (Number(dojo.byId("vmCompCost").innerHTML) + Number(data[0].diskOfferZoneCosts[0].cost)).toFixed(5);
                            dojo.byId("vmDollarSymbol").innerHTML = "";                           
                            var rulesNode = domConstruct.create("div", {}, dojo.byId("instanceDiskSlider"), "first");
                            var sliderRules = new dijit.form.HorizontalRule({
                                id:"diskSliderRule",
                                count: data.length,                    
                                container: "bottomDecoration",
                                style: "width: 1800%; height: 10px; margin-left: 13px"
                            }, rulesNode);      
                            sliderRules.startup();                                             
                        }                        
                        var customDiskSlider = new dijit.form.HorizontalSlider({
                            name: "slider",                  
                            style: "width:100%;", 
                            minimum: data[0].minSize,
                            maximum: data[0].maxSize,
                            discreteValues: data[0].maxSize,
                            container:'bottomDecoration',
                            onChange : function(value) {
                                dijit.byId("customDiskSize").setValue(value);
                            }
                        },"instanceCustomDiskSizeSlider");
                        customDiskSlider.startup();
                    });                                                                     
                }  else if(customCheck.checked == "false" || customCheck.checked == false) {
                    dojo.byId("vmDiskSize").style.display = "none";
                    dojo.byId("instanceDiskCost").style.display = "block";   
                    dojo.byId("vmDiskOffer").style.display = "block";
                    diskOfferRestStore.query({zoneReferenceId:zoneWidget}).then(function(data) {                                                 
                        dijit.byId("instanceDiskSlider").set("minimum", data[0].minSize);
                        dijit.byId("instanceDiskSlider").set("discreteValues", data.length);
                        dijit.byId("instanceDiskSlider").set("maximum", data.length);                        
                        var rulesNode = domConstruct.create("div", {}, dojo.byId("instanceDiskSlider"), "first");
                        var sliderRules = new dijit.form.HorizontalRule({
                            id:"diskSliderRule",
                            count: data.length,                    
                            container: "bottomDecoration",
                            style: "width: 1800%; height: 10px; margin-left: 13px"
                        }, rulesNode);      
                        sliderRules.startup();
                        dojo.byId("instanceDiskName").innerHTML = data[0].name;
                        dojo.byId("instanceDiskName").innerHTML = data[0].name;
                        dojo.byId("instanceDiskSize").innerHTML = translator.common.size.minSize +  data[0].minSize + "GB" + translator.common.size.maxSize + data[0].maxSize + "GB"; 
                        dojo.byId("instanceDiskCost").innerHTML = " " + (data[0].diskOfferZoneCosts[0].cost * data[0].size).toFixed(5) + "/" + translator.common.gb;
                        dojo.byId("vmDiskName").value = data[0].diskOfferReferenceId;
                        dojo.byId("vmDiskCost").innerHTML = (data[0].diskOfferZoneCosts[0].cost * data[0].size).toFixed(5);
                        dojo.byId("instanceAvgCost").innerHTML =  (Number(dojo.byId("vmCompCost").innerHTML) + Number(data[0].diskOfferZoneCosts[0].cost)).toFixed(5);
                        dojo.byId("vmDollarSymbol").innerHTML = "";                      
                    }); 
                }
            },
            populateValues: function() {                
                if(dijit.byId("instanceGridWidget")) {
                    dijit.byId("instanceGridWidget").destroyRecursive();
                    dijit.byId("vmZoneListWidget").destroyRecursive();
                }                
                if(dijit.byId("vmZoneListWidget")) {
                    dijit.byId("vmZoneListWidget").destroyRecursive();
                }            
                dojo.byId("instanceGrid").innerHTML = "<img src='images/vmload.gif' alt="+translator.common.loader.imageLoadError+" height='36' width='100'/> </br> <p>"+translator.user.loader.instanceLoader+"</p>";   
                var gridData = {
                    items: []
                };           
                var gridDataList = new ItemFileWriteStore({data: gridData}); 
                var instanceCountRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/virtualMachine/count"
                });
                var gridLayout = [
                    [
                        {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                        {'field': 'firewalId', 'hidden': 'true'},
                        {'name': translator.user.grid.instance.layout.vmName, 'field': 'vmName', 'width': '200px','formatter': function(data) {                            
                                var vm = data.split(",");      
                                var widget = "";
                                var imageUrl= "";
                                var templateImage = "";                            
                                if (vm[2] == "CentOS") {
                                    imageUrl = "images/os/os_centos_logo.png" ;
                                    templateImage = "<img src='images/os/os_centos_logo.png' alt='"+translator.common.template.centOS+"' height='25' width='25'/>" + vm[0];
                                } else if(vm[2] == "Debian") {
                                    imageUrl = "images/os/os_debian_logo.png";
                                    templateImage = "<img src='images/os/os_debian_logo.png' alt='"+translator.common.template.debian+"' height='25' width='25'/>" + vm[0]
                                } else if(vm[2] == "Oracle") {
                                     imageUrl = "images/os/os_oracle_logo.png";
                                    templateImage = "<img src='images/os/os_oracle_logo.png' alt='"+translator.common.template.oracle+"' height='25' width='25'/>" + vm[0]
                                } else if(vm[2] == "RedHat") {
                                     imageUrl = "images/os/os_redhat_logo.png";
                                    templateImage = "<img src='images/os/os_redhat_logo.png' alt='"+translator.common.template.redHat+"' height='25' width='25'/>" + vm[0]
                                } else if(vm[2] == "SUSE") {
                                     imageUrl = "images/os/os_suse_logo.png";
                                    templateImage = "<img src='images/os/os_suse_logo.png' alt='"+translator.common.template.SUSE+"' height='25' width='25'/>" + vm[0]
                                } else if(vm[2] == "Windows") {
                                     imageUrl = "images/os/os_win_logo.png";
                                    templateImage = "<img src='images/os/os_win_logo.png' alt='"+translator.common.template.windows+"' height='25' width='25'/>" + vm[0]
                                } else if(vm[2] == "Novel") {
                                     imageUrl = "images/os/os_novell_logo.png";
                                    templateImage = "<img src='images/os/os_novell_logo.png' alt='"+translator.common.template.novel+"' height='25' width='25'/>" + vm[0]
                                } else if(vm[2] == "Unix") {
                                     imageUrl = "images/os/os_unix_logo.png";
                                    templateImage = "<img src='images/os/os_unix_logo.png' alt='"+translator.common.template.unix+"' height='25' width='25'/>" + vm[0]
                                } else if(vm[2] == "Ubuntu") {
                                     imageUrl = "images/os/os_ubuntu_logo.png"; 
                                    templateImage = "<img src='images/os/os_ubuntu_logo.png' alt='"+translator.common.template.ubuntu+"' height='25' width='25'/>" + vm[0]
                                } else {
                                    imageUrl = ""; 
                                    templateImage = "" + vm[0];
                                }                        
                                var instanceGrid = dijit.byId("instanceGridWidget");
                                instanceGrid.store.fetch({query: {id: vm[1]},
                                    onItem: function(item) {                                   
                                        if(item.status == "Running" || item.status == "Stopped") {
                                            var availableWidget = "";                                    
                                            if(vm[4] == "true") {                                         
                                                var availableWidget = new FogPanel.StorageAction({
                                                    storageTagPath : "#/user/cloud/vmDetail/" + vm[3],
                                                    nameTagClick : function() {  
                                                        CurrentInstanceInfo.showVPCPage();
                                                    }                            
                                                });
                                                availableWidget.setTagName(vm[0]);                                    
                                                availableWidget.setOsImage(imageUrl);
                                                availableWidget.disableAll();
                                                availableWidget.disableDelete();
                                                availableWidget.enableTageName();
                                            } else {                                          
                                                var availableWidget = new FogPanel.StorageAction({
                                                    storageTagPath : "#/user/cloud/vmDetail/" + vm[3],
                                                    nameTagClick : function() {}                            
                                                });
                                                availableWidget.setTagName(vm[0]);                                    
                                                availableWidget.setOsImage(imageUrl);
                                                availableWidget.disableAll();
                                                availableWidget.disableDelete();
                                                availableWidget.enableTageName();
                                            }
                                            widget =  availableWidget;                                                                                                                     
                                        } else {                                    
                                            widget = templateImage;
                                        }
                                    }
                                });    
                                return widget;
                            }, datatype:"string",  autoComplete: true},                 
                        {'name': translator.user.grid.instance.layout.plane, 'field': 'plan', 'width': '200px', datatype:"string",  autoComplete: true,'formatter': function(data) {                                                                      
                                return  "<span class='bold'>" + data + "</span>";
                            }
                        },
                        {'name': translator.user.grid.instance.layout.ip, 'field': 'ip', 'width': '100px', datatype:"string",  autoComplete: true},                                   
                        {'name': "VPC", 'field': 'vpc', 'width': '100px', datatype:"string",  autoComplete: true},                                                   
                        {'name': translator.user.grid.instance.layout.zone, 'field': 'zone', 'width': '200px', datatype:"string",  autoComplete: true},
                        {'name': translator.user.grid.instance.layout.status, 'field': 'status', 'width': '100px', 'formatter' : function(data) {                            
                                var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' class='vm_stat_image' alt='"+translator.common.loader.loading+"'/>" });
                                var status;                         
                                if((data != "in progress") && (data != "starting")) {
                                    if(data == "Running") {
                                        status = "<div class='stat_running overflowLabel'>"+translator.common.instance.status.running+"</div>";
                                    } else if(data == "Stopped") {
                                        status = "<div class='stat_stopped overflowLabel'>"+translator.common.instance.status.stopped+"</div>";
                                    } else if(data == "Destroyed") {
                                        status = "<div class='stat_destroyed overflowLabel'>"+translator.common.instance.status.destroyed+"</div>";
                                    } else if(data == "Error") {
                                        status = "<div class='stat_destroyed overflowLabel'>"+translator.common.error+"</div>";
                                    } else {
                                        if(data == "Starting") {
                                            status = imageDiv.innerHTML;
                                        } else if (data == "Stopping") {
                                            status = imageDiv.innerHTML;
                                        } else if (data == "Expunging") {
                                            status = imageDiv.innerHTML;
                                        } else {
                                            status = data;
                                        }                                   
                                    }                                                                                                 
                                } else if(data == "in progress") {
                                    status = imageDiv.innerHTML;
                                }
                                return status;
                            }, datatype:"string",  autoComplete: true},
                        {'name': translator.user.grid.instance.layout.action, 'field': 'action',   'formatter': function(data, rowIndex) {
                                var layoutData = data.split(",");
                                var instanceStatus =  new FogPanel.InstanceStatus({
                                    onStartInstance : function() {
                                        UserInstances.getStartConformation();
                                    },
                                    onStopInstance : function() {
                                        UserInstances.getStopConformation();
                                    },
                                    onRebootInstance : function() {
                                        UserInstances.getRebootConformation();
                                    },
                                    onRestoreInstance : function() {
                                        dojo.byId("currentVmID").value = layoutData[1];
                                        dijit.byId("vmRestoreDialog").show();        
                                    },
                                     onDeleteInstance : function() {
                                         UserInstances.getDeleteConformation();
                                    }

                                }); 
                                instanceStatus.disableMigrateHost();                        
                                if(layoutData[0] === "Running") {
                                    instanceStatus.enableRunningState();
                                } else if(layoutData[0] === "Stopped") {
                                     instanceStatus.enableStopState();
                                } else if(layoutData[0] === "Destroyed") {
                                    instanceStatus.enableRestoreState();
                                } else if(layoutData[0] === "Error") {                           
                                    instanceStatus.enableDeleteStat();
                                } else {
                                    instanceStatus.disableAll();
                                }
                                return instanceStatus;
                            },'width': '100%', datatype:"string",  autoComplete: true}           
                    ]
                ];        
    var instanceCountRestStore = new JsonRest({
        target: core.getContextPath()+"/api/virtualMachine/count"
    });
    var currentZoneID = dojo.byId("selectedANZoneID").value;      
    if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
        this._instanceRestStore.query().then(function(data) {
            if(data.length == 0 || data == "undefined" || data == undefined)  {                  
                dojo.byId("noVmMessageBox").style.display = "block";                    
                dojo.byId("userTotalVM").innerHTML = 0;
                dojo.byId("userRunningVM").innerHTML = 0;
                dojo.byId("userStoppedVM").innerHTML = 0;
                dojo.byId("instanceGrid").innerHTML = "";
            } else {                            
                dojo.byId("noVmMessageBox").style.display = "none";
                dojo.forEach(data, function(instanceData) {  
                gridDataList.newItem({
                    id: instanceData.referenceId,
                    firewalId: instanceData.securityGroupReferenceId,
                    vmName: instanceData.name + "," + instanceData.referenceId + "," + instanceData.osCategory + "," + instanceData.id + "," + instanceData.hasVpc,
                    plan: instanceData.computingOffer,
                    zone: instanceData.zoneName,
                    status: instanceData.state, 
                    vpc: instanceData.vpc,
                    ip:instanceData.ip,                             
                    action: instanceData.state + "," + instanceData.referenceId
                });                   
            });                
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
            instanceCountRestStore.query().then(function(data) {
                dojo.forEach(data, function(el) {                     
                    dojo.byId("userTotalVM").innerHTML = el.totalVms;
                    dojo.byId("userRunningVM").innerHTML = el.runningVms;
                    dojo.byId("userStoppedVM").innerHTML = el.stoppedVms;                     
                });
            });
        } 
    });  
    } else {
        instanceCountRestStore.query({zoneReferenceId: currentZoneID}).then(function(data) {
            if(data.length == 0 || data == "undefined" || data == undefined)  {                  
                dojo.byId("noVmMessageBox").style.display = "block";                    
                dojo.byId("userTotalVM").innerHTML = 0;
                dojo.byId("userRunningVM").innerHTML = 0;
                dojo.byId("userStoppedVM").innerHTML = 0;
                dojo.byId("instanceGrid").innerHTML = "";
            } else {                            
                dojo.forEach(data, function(el) {                     
                    dojo.byId("userTotalVM").innerHTML = el.totalVms;
                    dojo.byId("userRunningVM").innerHTML = el.runningVms;
                    dojo.byId("userStoppedVM").innerHTML = el.stoppedVms;                     
                });
                dojo.byId("noVmMessageBox").style.display = "none";
                dojo.forEach(data[0].instanceData, function(instanceData) {  
                gridDataList.newItem({
                    id: instanceData.referenceId,
                    firewalId: instanceData.securityGroupReferenceId,
                    vmName: instanceData.name + "," + instanceData.referenceId + "," + instanceData.osCategory + "," + instanceData.id + "," + instanceData.hasVpc,
                    plan: instanceData.computingOffer,
                    zone: instanceData.zoneName,
                    status: instanceData.state, 
                    ip:instanceData.ip,       
                    vpc: instanceData.vpc,
                    action: instanceData.state + "," + instanceData.referenceId
                });                   
            });                
            dojo.byId("instanceGrid").innerHTML = "";
            var instanceGrid = new EnhancedGrid({
                id: 'instanceGridWidget',
                store: gridDataList,
                structure: gridLayout,
                loadingMessage: translator.user.loader.instanceLoader,
                noDataMessageL:translator.user.grid.instance.noInstance,
                autoHeight: true,
                autoWidth: false,
                style: "overflow: hidden",
                plugins: core.getGridConfig().plugins
            });                     
            instanceGrid.placeAt("instanceGrid");        
            instanceGrid.startup();                         
        } 
    });  
} 
    
    
                                                                    
var zoneOptions = {
    identifier: 'id',
    label: 'name',
    items: [{id:'option', name:translator.common.options.allZone}]
};                

var zoneList = new ItemFileWriteStore({data: zoneOptions});                
this._zoneRestStore.query().then(function(data) {
    dojo.forEach(data, function(el) {
        zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
    });
});   
var zoneWidget = new Select({    
    id: "vmZoneListWidget",
    name: "zoneWidgets",
    value: "option",      
    sortByLabel: false,
    store: zoneList,
    onChange: function() {
        UserInstances.getVMByZone(this);
    }
}, "userZoneList");
},
showInstanceByRadio: function(currentRadio) {           
    var instanceRestStore = new JsonRest({
        target: core.getContextPath()+"/api/virtualMachine/"
    });
        var instanceCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/count"
        });
         var instanceData = {
            items: []
        }; 
        var currentZoneID = dojo.byId("selectedANZoneID").value;
        var zoneWidget = ""
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            zoneWidget = "null";
        } else {
            zoneWidget = currentZoneID
        }
        var instanceGrid = dijit.byId("instanceGridWidget");
        var instanceDataList = new ItemFileWriteStore({data: instanceData}); 
//        var zoneWidget = dijit.byId("vmZoneListWidget");
        if(zoneWidget == "null" && currentRadio.value == "All") {
            instanceRestStore.query().then(function(data) {
                dojo.forEach(data, function(vmData) {                   
                    instanceDataList.newItem({                        
                        id:vmData.referenceId,
                        vmName:vmData.name + "," + vmData.referenceId + "," + vmData.osCategory + "," + vmData.id + "," + vmData.hasVpc,
                        plan: vmData.computingOffer, 
                        zone: vmData.zoneName,
                        status : vmData.state,
                        vpc: vmData.vpc,
                        ip: vmData.ip,
                        action : vmData.state + "," + vmData.referenceId                                      
                    });
                });
                instanceGrid.setStore(instanceDataList); 
            });              
        }  else if(zoneWidget == "null" && currentRadio.value != "All") {            
            instanceCountRestStore.query({status: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.instanceData,  function(vmData) {
                        instanceDataList.newItem({
                        id:vmData.referenceId,
                        vmName:vmData.name + "," + vmData.referenceId + "," + vmData.osCategory +"," + vmData.id + "," + vmData.hasVpc,
                        plan: vmData.computingOffer, 
                        zone: vmData.zoneName,
                        status : vmData.state,
                        vpc: vmData.vpc,
                        ip: vmData.ip,
                        action :vmData.state + "," + vmData.referenceId     
                    });
                }) ;                       
            });
            instanceGrid.setStore(instanceDataList); 
        });  
        }  else if(zoneWidget.value != "null" && currentRadio.value != "All") {
            instanceCountRestStore.query({zoneReferenceId: zoneWidget, status:currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.instanceData, function(vmData) {
                        instanceDataList.newItem({
                        id:vmData.referenceId,
                        vmName:vmData.name + "," + vmData.referenceId + "," + vmData.osCategory+"," + vmData.id + "," + vmData.hasVpc,
                        plan: vmData.computingOffer, 
                        zone: vmData.zoneName,
                        status : vmData.state,
                        vpc: vmData.vpc,
                        ip: vmData.ip,
                        action : vmData.state + "," + vmData.referenceId     
                    });
                    });
                });
                instanceGrid.setStore(instanceDataList); 
            });  
        } else if(zoneWidget.value != "null" && currentRadio.value == "All") {
            instanceCountRestStore.query({zoneReferenceId: zoneWidget}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.instanceData, function(vmData) {
                        instanceDataList.newItem({
                            id:vmData.referenceId,
                            vmName:vmData.name + "," + vmData.referenceId + "," + vmData.osCategory+"," + vmData.id + "," + vmData.hasVpc,
                            plan: vmData.computingOffer, 
                            zone: vmData.zoneName,
                            status : vmData.state,
                            ip: vmData.ip,
                            vpc: vmData.vpc,
                            action : vmData.state + "," + vmData.referenceId     
                        });
                    }); 
                });
                instanceGrid.setStore(instanceDataList); 
                
            });    
        }
        },
        getVMByZone : function(currentZone) {
        },
        getGridData :  function() {
            var instanceGrid = dijit.byId("instanceGridWidget");
            var currentId;
            var  dataItem = instanceGrid.selection.getSelected();
            dataItem.disabled = 'true';
            dojo.forEach(dataItem, function(el) {
                currentId = el.id;
            });
            return currentId;
        },
        getStopConformation: function() {  
            dijit.byId("stopInstanceAgreement").set("checked", false);
            dijit.byId("stopDialog").show();
        },
        stopInstance: function() {     
            var currentId = UserInstances.getGridData();
            var referenceId;
            dojo.forEach(currentId, function(el) {
                referenceId = el;
            });
            var instanceStopRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/stop/"         
            });
            var successMessage = translator.common.instance.vmStoppedSuccessMessage;
            var force = dijit.byId("stopInstanceAgreement").checked;
            instanceStopRestStore.add({
                referenceId: referenceId,
                forced: force
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmStopJobStatus = setInterval(function(){UserInstances.vmJob(resultData.jobId, vmStopJobStatus, successMessage);},3000);  
                        var index = dijit.byId("instanceGridWidget").selection.selectedIndex;
                        var item = dijit.byId("instanceGridWidget").getItem(index);
                        var store = dijit.byId("instanceGridWidget").store;                        
                        store.setValue(item, 'status', "in progress");          
                        store.setValue(item, 'action', "in progress");          
                        
                        dijit.byId("instanceGridWidget").update();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.stopVMError, "error");
                        registry.byId("userToaster").show();
                    }
                });                
            });
            dijit.byId("stopDialog").hide();
        },
        deleteInstance : function() {
            var currentId = UserInstances.getGridData();
            var referenceId;
            var firewalId;
            dojo.forEach(currentId, function(el) {
                referenceId = el;
            });
            var instanceGrid = dijit.byId("instanceGridWidget");
            instanceGrid.store.fetch({query: {id: referenceId},
                onItem: function(item) {         
                    dojo.forEach(item.firewalId, function(el) {
                        firewalId = el;
                    });
                }
            });          
            
            var instanceDeleteRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/delete/"         
            });
            var successMessage = translator.common.instance.vmDeletedSuccessMessage;
            instanceDeleteRestStore.add({
                referenceId: referenceId,
                forced: "true"
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmStopJobStatus = setInterval(function(){UserInstances.vmJob(resultData.jobId, vmStopJobStatus, successMessage);},3000);  
                        var index = dijit.byId("instanceGridWidget").selection.selectedIndex;
                        var item = dijit.byId("instanceGridWidget").getItem(index);
                        var store = dijit.byId("instanceGridWidget").store;                        
                        store.setValue(item, 'status', "in progress");       
                        store.setValue(item, 'action', "in progress");       
                        
                        dijit.byId("instanceGridWidget").update();                          
                    } else {
                        registry.byId("userToaster").setContent(successMessage, "error");
                        registry.byId("userToaster").show();
                    }
                });     
            }); 
            dijit.byId("deleteDialog").hide();  
        },
        vmJob: function(jobId, vmJobStatus, vmSuccessMsg) {
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/job/"
            }); 
            
            var securityGroupRestStore = new JsonRest({
                target: core.getContextPath()+"/api/securityGroup/"         
            });
            var index = dijit.byId("instanceGridWidget").selection.selectedIndex;
            var item = dijit.byId("instanceGridWidget").getItem(index);
            var store = dijit.byId("instanceGridWidget").store;
            jobStore.add({
                jobId : jobId            
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult == "OK") {                        
                        clearInterval(vmJobStatus);                           
                         store.setValue(item, 'id',resultData.referenceId);
                         store.setValue(item, 'vmName',resultData.name + "," + resultData.referenceId + "," + resultData.osCategory);
                         store.setValue(item, 'plan',resultData.computingOffer);
                         store.setValue(item, 'zone',resultData.zoneName);
                         store.setValue(item, 'plan',resultData.computingOffer);
                         store.setValue(item, 'status',resultData.state);
                         store.setValue(item, 'action',resultData.state + "," + resultData.referenceId);
                         dijit.byId("instanceGridWidget").update();                   
                         registry.byId("userToaster").setContent(vmSuccessMsg, "message");
                         registry.byId("userToaster").show();
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else if(resultData.jobResult == "FAILED") {
                        store.setValue(item, 'status',"Running");
                        clearInterval(vmJobStatus);                        
                        registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                        registry.byId("userToaster").show();                        
                    }
                });
            });
        },
        getStartConformation: function() {           
            dijit.byId("startDialog").show();
        },
        startInstance : function() {
          var currentId = UserInstances.getGridData();
          var referenceId;
           dojo.forEach(currentId, function(el) {
               referenceId = el;
           });
            var instanceStartRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/start/"         
            });
            var successMessage = translator.common.instance.vmStartedSuccessMessage;
            instanceStartRestStore.add({
                referenceId:  referenceId
            }).then(function(data) {
                 dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmStartJobStatus = setInterval(function(){UserInstances.vmJob(resultData.jobId, vmStartJobStatus, successMessage);},3000);  
                        var index = dijit.byId("instanceGridWidget").selection.selectedIndex;
                        var item = dijit.byId("instanceGridWidget").getItem(index);
                        var store = dijit.byId("instanceGridWidget").store;
                        store.setValue(item, 'status', "in progress");
                        store.setValue(item, 'action', "in progress");       
                        
                        dijit.byId("instanceGridWidget").update();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.stopVMError, "error");
                        registry.byId("userToaster").show();
                    }
                });    
            });
            dijit.byId("startDialog").hide();  
        },        
        restoreInstance : function() {              
            var index = dijit.byId("instanceGridWidget").selection.selectedIndex;
            var item = dijit.byId("instanceGridWidget").getItem(index);
            var store = dijit.byId("instanceGridWidget").store;

            var instanceStopRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/restore/"         
            });  
            var referenceId = dojo.byId("currentVmID").value;
            dijit.byId("vmRestoreDialog").hide();            
            instanceStopRestStore.add({
                referenceId: referenceId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
//                        var vmRestoreJobStatus = setInterval(function(){UserInstances.vmJob(resultData.jobId, vmRestoreJobStatus);},3000);  
                        registry.byId("userToaster").setContent(translator.common.instance.restoreVMSucess, "message");
                        registry.byId("userToaster").show();                                                                                          
                        store.setValue(item, 'status', resultData.status); 
                        store.setValue(item, 'action',resultData.status + "," + resultData.referenceId);
                        dijit.byId("instanceGridWidget").update();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.restoreVMError, "error");
                        registry.byId("userToaster").show();
                        store.setValue(item, 'status', "Destroyed"); 
                        dijit.byId("instanceGridWidget").update();                        
                    }
                });                
            });
        },
        closeRestoreVMDialog : function () {
            dijit.byId("vmRestoreDialog").hide();
        },
        closeDeleteDialog : function() {
            dijit.byId("deleteDialog").hide();
        },
        getRebootConformation: function() {
            dijit.byId("rebootDialog").show();  
        },
        getDeleteConformation : function() {
            dijit.byId("deleteDialog").show();  
        },
        rebootInstance: function() {            
            var currentId = UserInstances.getGridData();
            var referenceId;
           dojo.forEach(currentId, function(el) {
               referenceId = el;
           });
                        
            var instanceRebootRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/reboot/"         
            });
            var successMessage = translator.common.instance.vmRebootedSuccessMessage;
            instanceRebootRestStore.add({
                referenceId: referenceId
            }).then(function(data) {
                 dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmStartJobStatus = setInterval(function(){UserInstances.vmJob(resultData.jobId, vmStartJobStatus, successMessage);},3000);  
                        var index = dijit.byId("instanceGridWidget").selection.selectedIndex;
                        var item = dijit.byId("instanceGridWidget").getItem(index);
                        var store = dijit.byId("instanceGridWidget").store;
                        store.setValue(item, 'status', "in progress");
                        store.setValue(item, 'action', "in progress");
                        
                        dijit.byId("storageGrid").update();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.rebootVMError, "error");
                        registry.byId("userToaster").show();
                    }
                });    
            });
             dijit.byId("rebootDialog").hide();
        },
        closeRebootDialog: function() {
            dijit.byId("rebootDialog").hide();
        },
        closeStartDialog: function() {
            dijit.byId("startDialog").hide();
        },
        closeStopDialog: function() {
            dijit.byId("stopDialog").hide();
        },
        returnCurrentWidget: function(id) {         
            dojo.byId("currentInstanceId").value = id;
            dijit.byId("consoleScreen").show();
        },
        viewConsole: function() {
            var url;
            var currentinstanceWidgetId = dojo.byId("currentInstanceId").value;
            var currentinstanceWidget = dijit.byId(currentinstanceWidgetId);
            
            var instanceConsoleStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/console/"         
            });
            instanceConsoleStore.add({
                referenceId:  currentinstanceWidget.additionalProperties.referenceId
            }).then(function(result) {
                window.open(result[0].url,currentinstanceWidget.additionalProperties.instanceName,'width=800,height=650');
                
            });
//            dijit.byId("consoleScreen").hide();
        }        
};



var UserSecurityEgressInfo = {
    _currentId:"",
    init : function() {        
    },
    populateValues : function(securityGroupId) {       
        this._currentId = securityGroupId;
        if(dijit.byId("userEgressGrid")) {
             dijit.byId("userEgressGrid").destroyRecursive();                                
        }
        dojo.byId("securityGroupEgressGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.firewall.outboundLoading+"</p>";
        var securityGroupRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/"         
        });
        var ingressData = {
            items: []
        };
        
        var ingressDataList = new ItemFileWriteStore({data: ingressData});
        var ingressRuleslayout = [
            [
                {'name': 'ID', 'field': 'id',  'hidden': 'true'},
                {'name': translator.common.firewall.protocol, 'field': 'protocol', 'width': '200px', datatype:"string",  autoComplete: true},
                {'name': translator.common.firewall.startPort, 'field': 'startPort','width': '200px', datatype:"string",  autoComplete: true},
                {'name': translator.common.firewall.endPort, 'field': 'endPort', 'width': '200px', datatype:"string",  autoComplete: true},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '200px', datatype:"string",  autoComplete: true},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {                        
                        var deleteButton = new dijit.form.Button({                       
                            "class": "delete_icon",
                            onClick : function() {   
                                var grid;
                                var securityGroupRuleRestStore = new JsonRest({
                                    target: core.getContextPath()+"/api/securityGroup/egress/"         
                                });
                                grid = dijit.byId("userEgressGrid");                           
                                securityGroupRuleRestStore.remove(data).then(function(result) {                                
                                    if(result == "OK") {
                                        var items = grid.selection.getSelected();
                                        dojo.forEach(items, function(selectedItem) {
                                            grid.store.deleteItem(selectedItem);
                                        });
                                        registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess,"message");
                                        registry.byId("userToaster").show();
                                    } else {
                                        registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteError,"error");
                                        registry.byId("userToaster").show();
                                    }                                                                                                
                                });                         
                            }
                        });                    
                        return deleteButton;
                    },'width': '100%', datatype:"string",  autoComplete: true}
            ]
        ];
        securityGroupRestStore.get(securityGroupId).then(function(data) {
            dojo.forEach(data.securityGroups, function(securityGroupsData) {
                dojo.byId("currentFirewal").innerHTML = securityGroupsData.name;
                dojo.forEach(securityGroupsData.egressRules, function(ingressData) {
                    if(ingressData) {
                        ingressDataList.newItem({
                            id: ingressData.ruleId,
                            protocol:ingressData.protocol,
                            startPort: ingressData.startPort,
                            endPort:ingressData.endPort,
                            cidr:ingressData.cidr,
                            action: ingressData.ruleId
                        });
                    }
                });
            });
            dojo.byId("securityGroupEgressGrid").innerHTML = " ";
            var ingressGridWidget = new EnhancedGrid({
                id: 'userEgressGrid',
                "class" : "span12",
                store: ingressDataList,
                structure: ingressRuleslayout,
                noDataMessage: translator.common.firewall.noOutboudData,
                plugins: {
                    pagination: {
                        pageSizes: ["3", "5", "10", translator.common.all],
                        description: true,
                        sizeSwitch: true,
                        pageStepper: true,
                        gotoButton: true,
                        /*page step to be displayed*/
                        maxPageStep: 4,
                        /*position of the pagination bar*/
                        position: "bottom"
                    }
                },
                autoHeight: true
            });
            ingressGridWidget.placeAt("securityGroupEgressGrid");
            ingressGridWidget.startup();       
        });                
    },
    showEgressIcmpCode : function(icmpType) {
        var icmpOptions = {}
        if(icmpType.value == "-1" || icmpType.value == "0" || icmpType.value == "4" || icmpType.value == "6" ||
                icmpType.value == "8" || icmpType.value == "9" || icmpType.value == "10" || icmpType.value == "13" || 
                icmpType.value == "14" || icmpType.value == "15" || icmpType.value == "16" || icmpType.value == "17" || 
                icmpType.value == "18") {            
            icmpOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            }            
            if(icmpType.value == "-1") {
                var AllIcmp = new ItemFileWriteStore({data: icmpOptions});
                AllIcmp.newItem({id: "-1", name:translator.common.empty})
                dijit.byId("egressIcmpCodeWidget").set("store", AllIcmp);
                dijit.byId("egressIcmpCodeWidget").set("value", "-1");
            } else {
                var noIcmp = new ItemFileWriteStore({data: icmpOptions});
                noIcmp.newItem({id: "0", name: translator.common.message.noCode});
                dijit.byId("egressIcmpCodeWidget").set("store", noIcmp);
                dijit.byId("egressIcmpCodeWidget").set("value", "0");
            }                                               
        } else {
            dojo.byId("egressEndportContainerList").style.display = "block";
            dojo.byId("egressEndPortList").style.display = "none";
            dojo.byId("egressIcmpCodeList").style.display = "block";               
            if(icmpType.value == "3") {              
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
                        {id: "0", name : translator.common.firewall.networkUnreachable},
                        {id: "1", name : translator.common.firewall.hostUnreachable},
                        {id: "2", name : translator.common.firewall.protocolUnreachable},
                        {id: "3", name : translator.common.firewall.portUnreachable},
                        {id: "4", name : translator.common.firewall.fragmentationNeeded},
                        {id: "5", name : translator.common.firewall.sourceRouteFailed},
                        {id: "6", name : translator.common.firewall.designationNetworkUnknown},
                        {id: "7", name : translator.common.firewall.designationHostUnknown},
                        {id: "8", name : translator.common.firewall.sourceHostIsolated},
                        {id: "9", name : translator.common.firewall.networkAdministrativelyProhibited},
                        {id: "10", name : translator.common.firewall.hostAdministrativelyProhibited},
                        {id: "11", name : translator.common.firewall.networkUnreachableForTos},
                        {id: "12", name : translator.common.firewall.hostUnreachableForTos}
                    ]
                };                
                var cidrCodefor3List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("egressIcmpCodeWidget").set("store", cidrCodefor3List);
                dijit.byId("egressIcmpCodeWidget").set("value","0");                
            } else if(icmpType.value == "5") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
                        {id: "0", name : translator.common.firewall.datagramForNetwork},
                        {id: "1", name : translator.common.firewall.datagramForHost},
                        {id: "2", name : translator.common.firewall.datagramForTosAndNetwork},
                        {id: "3", name : translator.common.firewall.datagramForTosAndHost}                       
                    ]
                };                
                var cidrCodefor5List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("egressIcmpCodeWidget").set("store", cidrCodefor5List);
                dijit.byId("egressIcmpCodeWidget").set("value","0");    
            } else if(icmpType.value == "11") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
                        {id: "0", name : translator.common.firewall.ttlExpiredTransit},
                        {id: "1", name : translator.common.firewall.fragmentationReassenblyExceeded}                                    
                    ]
                };                
                var cidrCodefor11List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("egressIcmpCodeWidget").set("store", cidrCodefor11List);
                dijit.byId("egressIcmpCodeWidget").set("value","0");    
            } else if(icmpType.value == "12") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
                        {id: "0", name : translator.common.firewall.pointerIndicatesError},
                        {id: "1", name : translator.common.firewall.missingRequiredOption},
                        {id: "2", name : translator.common.firewall.badLength}                                                          
                    ]
                };                
                var cidrCodefor12List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("egressIcmpCodeWidget").set("store", cidrCodefor12List);
                dijit.byId("egressIcmpCodeWidget").set("value","0");    
            }
        }
    },
    changeEgressRuleLabel : function(selectWidget) {    
        var allOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        }
        var AllIcmpCode = new ItemFileWriteStore({data: allOptions});
        AllIcmpCode.newItem({id: "-1", name: translator.common.no});        
        dijit.byId("egressIcmpCodeWidget").set("store", AllIcmpCode);        
        dijit.byId("egressIcmpCodeWidget").set("value", "-1");        
        dijit.byId("egressIcmpTypeWidget").set("value","-1"); 
        var span = "<span class='require'>*</span>" 
        if(selectWidget.value == "ICMP") {          
            dojo.byId("userEgressStartPort").innerHTML = translator.common.firewall.icmpType + span;
            dojo.byId("userEgressEndPort").innerHTML = translator.common.firewall.icmpCode+ span;                
            dojo.byId("egressStartportList").style.display = "none";
            dojo.byId("egressIcmpTypeList").style.display = "block";            
            dojo.byId("egressEndportContainerList").style.display = "none";            
            dijit.byId("securityEgressGroupEndPort").setValue("-1");
            dijit.byId("securityGroupEgressStartPort").setValue("-1");                  
        } else {  
            dojo.byId("egressEndportContainerList").style.display = "block";
            dojo.byId("egressEndPortList").style.display = "block";
            dojo.byId("userEgressStartPort").innerHTML = "Start Port" + span;
            dojo.byId("userEgressEndPort").innerHTML = "End Port" + span;                 
            dojo.byId("egressStartportList").style.display = "block";           
            dojo.byId("egressIcmpTypeList").style.display = "none";                                                                      
            dojo.byId("egressIcmpCodeList").style.display = "none";            
            dijit.byId("securityGroupEgressStartPort").setValue("");             
            dijit.byId("securityEgressGroupEndPort").setValue("");                                       
        }
    },
    addEngress : function() {        
        var securityGroupIngressRestStoreStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/egress"         
        });              
        var firstNode = "";
        var status = true;
        var node = dojo.byId("securityGroupEgressRuleForm");
        var widget = dijit.registry.findWidgets(node);        

        dojo.forEach(widget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
                status = false
            }
        });
        var securityGroupId = this._currentId;
        var protocol = dijit.byId("securityGroupEgressProtocol").value;        
        var cidr = dijit.byId("securityGroupEgressCidr").value;                                       
        
        var startPort = "";
        var endPort = "";                 
        if(protocol == "ICMP") {            
            startPort = dijit.byId("egressIcmpTypeWidget").value;
            endPort = dijit.byId("egressIcmpCodeWidget").value;             
         } else {        
             startPort = dijit.byId("securityGroupEgressStartPort").getValue().toString();
             endPort = dijit.byId("securityEgressGroupEndPort").getValue().toString();
         }
         if(status ==  true) {
             dijit.byId('egressAddButton').set('style', { display: 'none' });
             dojo.byId("egressLoader").style.display = "block";            
             dijit.byId("securityGroupEgressProtocol").set("disabled", true);
             dijit.byId("securityGroupEgressCidr").set("disabled", true);
             dijit.byId("securityGroupEgressStartPort").set("disabled", true);
             dijit.byId("securityEgressGroupEndPort").set("disabled", true);
             dijit.byId("egressIcmpTypeWidget").set("disabled", true);
             dijit.byId("egressIcmpCodeWidget").set("disabled", true);
             securityGroupIngressRestStoreStore.add({
                 protocol: protocol, 
                 startPort: startPort, 
                 endPort: endPort,
                 cidr: cidr,
                 securityGroupId: this._currentId
             }).then(function(result) {
                 dojo.forEach(result, function(resultData) {
                     if(resultData.result == "OK") {
                         var securityGroupJobStatus = setInterval(function(){UserSecurityEgressInfo.securityGroupEgressRuleJob(resultData.jobId, securityGroupJobStatus, resultData.ruleType, securityGroupId);},3000);                     
                         dijit.byId('egressAddButton').set('style', { display: 'none' });
                         dojo.byId("egressLoader").style.display = "block";
                     } else {
                         registry.byId("userToaster").setContent(translator.common.firewall.outboundAddError,"error");
                         registry.byId("userToaster").show();
                         dijit.byId('egressAddButton').set('style', { display: 'block' });
                         dojo.byId("egressLoader").style.display = "none";
                    
                         dijit.byId("securityGroupEgressProtocol").set("disabled", false);
                         dijit.byId("securityGroupEgressCidr").set("disabled", false);
                         dijit.byId("securityGroupEgressStartPort").set("disabled", false);
                         dijit.byId("securityEgressGroupEndPort").set("disabled", false);
                         dijit.byId("egressIcmpTypeWidget").set("disabled", false);
                         dijit.byId("egressIcmpCodeWidget").set("disabled", false);                
                     }
                 });                        
             });        
         }         
     },
     securityGroupEgressRuleJob : function(jobId, securityGroupJobStatus, ruleType, securityGroupId) {     
         var securityGroupJobRestStoreStore = new JsonRest({
             target: core.getContextPath()+"/api/securityGroup/job/"         
         });
         var  grid = dijit.byId("userEgressGrid");                
         securityGroupJobRestStoreStore.add({
             jobId: jobId,
             ruleType: ruleType
         }).then(function(response) {
             dojo.forEach(response, function(jobResultData) {
                 dijit.byId("securityGroupEgressProtocol").set("disabled", false);
                 dijit.byId("securityGroupEgressCidr").set("disabled", false);
                 dijit.byId("securityGroupEgressStartPort").set("disabled", false);
                 dijit.byId("securityEgressGroupEndPort").set("disabled", false);
                 dijit.byId("egressIcmpTypeWidget").set("disabled", false);
                 dijit.byId("egressIcmpCodeWidget").set("disabled", false);
                 if(jobResultData.jobResult == "OK") {
                     clearInterval(securityGroupJobStatus);
//                    grid.store.newItem({
//                        id: jobResultData.securityGroupRuleId,
//                        protocol:jobResultData.protocol,
//                        startPort: jobResultData.startPort,
//                        endPort:jobResultData.endPort,
//                        cidr:jobResultData.cidr,
//                        action: jobResultData.securityGroupRuleId
//                    });
                     UserSecurityEgressInfo.populateValues(securityGroupId);
                     dijit.byId("securityGroupEgressRuleForm").reset();                                           
                     registry.byId("userToaster").setContent(translator.common.firewall.outboundAddSuccess,"message");
                     registry.byId("userToaster").show();
                     dijit.byId('egressAddButton').set('style', { display: 'block' });
                     dojo.byId("egressLoader").style.display = "none";                    
                 } else {
                     clearInterval(securityGroupJobStatus);
                     dijit.byId("egressAddButton").set("disabled", false);
                     registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                     registry.byId("userToaster").show();
                     dijit.byId('egressAddButton').set('style', { display: 'block' });
                     dojo.byId("egressLoader").style.display = "none";    
                 }
             });
         });
     }
 };

var UserSecurityIngressInfo = {
    _currentId:"",
    init : function() {
        
    },
    populateValues : function(securityGroupId) {
       
        this._currentId = securityGroupId;
        if(dijit.byId("userIngressGrid")) {
             dijit.byId("userIngressGrid").destroyRecursive();                                
         }
        dojo.byId("securityGroupIngressGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.firewall.inboundLoading+"</p>";
        var securityGroupRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/"         
        });
        var ingressData = {
            items: []
        };
        
        var ingressDataList = new ItemFileWriteStore({data: ingressData});

        var ingressRuleslayout = [[
                 {'name': 'ID', 'field': 'id',  'hidden': 'true'},
                 {'name': translator.common.firewall.protocol, 'field': 'protocol', 'width': '200px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.firewall.startPort, 'field': 'startPort','width': '200px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.firewall.endPort, 'field': 'endPort', 'width': '200px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '200px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) {
                        
                        var deleteButton = new dijit.form.Button({                        
                        "class": "delete_icon",
                        onClick : function() {   
                            var grid;
                            var securityGroupRuleRestStore = new JsonRest({
                                target: core.getContextPath()+"/api/securityGroup/ingress/"         
                            });
                            grid = dijit.byId("userIngressGrid");
                           
                            securityGroupRuleRestStore.remove(data).then(function(result) {                                
                                    if(result == "OK") {
                                    var items = grid.selection.getSelected();
                                    dojo.forEach(items, function(selectedItem) {
                                    
                                        grid.store.deleteItem(selectedItem);
                                    });
                                    registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess,"message");
                                    registry.byId("userToaster").show();
                                } else {
                                    registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteError,"error");
                                    registry.byId("userToaster").show();
                                }
                                    
                                                                                 
                            });                         
                        }
                        });
                    return deleteButton;
                },'width': '100%', datatype:"string",  autoComplete: true}
             ]
         ];
         securityGroupRestStore.get(securityGroupId).then(function(data) {
             dojo.forEach(data.securityGroups, function(securityGroupsData) {
                 dojo.byId("currentFirewal").innerHTML = securityGroupsData.name;
                 dojo.forEach(securityGroupsData.ingressRules, function(ingressData) {
                     
                     if(ingressData) {
                        ingressDataList.newItem({
                        id: ingressData.ruleId,
                        protocol:ingressData.protocol,
                        startPort: ingressData.startPort,
                        endPort:ingressData.endPort,
                        cidr:ingressData.cidr,
                        action: ingressData.ruleId
                    });

                }
            });
             });
             dojo.byId("securityGroupIngressGrid").innerHTML  = "";
             var ingressGridWidget = new EnhancedGrid({
             id: 'userIngressGrid',
             "class" : "span12",
             store: ingressDataList,
             structure: ingressRuleslayout,
             noDataMessage:translator.common.firewall.noInbound,
             plugins: {
                    pagination: {
                        pageSizes: ["3", "5", "10", "All"],
                        description: true,
                        sizeSwitch: true,
                        pageStepper: true,
                        gotoButton: true,
                        /*page step to be displayed*/
                        maxPageStep: 4,
                        /*position of the pagination bar*/
                        position: "bottom"
                    }
                },
              autoHeight: true
         });
         ingressGridWidget.placeAt("securityGroupIngressGrid");
         ingressGridWidget.startup();
         });
       
         
    },
     showIcmpCode : function(icmpType) {        
        var icmpOptions = {}
        if(icmpType.value == "-1" || icmpType.value == "0" || icmpType.value == "4" || icmpType.value == "6" ||
            icmpType.value == "8" || icmpType.value == "9" || icmpType.value == "10" || icmpType.value == "13" || 
            icmpType.value == "14" || icmpType.value == "15" || icmpType.value == "16" || icmpType.value == "17" || 
            icmpType.value == "18") {
//            dojo.byId("endportContainer").style.display = "none";            
            icmpOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            }            
            if(icmpType.value == "-1") {               
                var AllIcmp = new ItemFileWriteStore({data: icmpOptions});
                AllIcmp.newItem({id: "0", name:translator.common.all})
                dijit.byId("icmpCodeWidget").set("store", AllIcmp);
                dijit.byId("icmpCodeWidget").set("value", "-1");
                dijit.byId("icmpTypeWidget").set("value", "-1");
                
            } else {
                var noIcmp = new ItemFileWriteStore({data: icmpOptions});
                noIcmp.newItem({id: "0", name: translator.common.message.noCode});
                dijit.byId("icmpCodeWidget").set("store", noIcmp);
                dijit.byId("icmpCodeWidget").set("value", "0");
            }                                               
        } else {
            dojo.byId("endportContainer").style.display = "block";
            dojo.byId("endPortList").style.display = "none";
            dojo.byId("icmpCodeList").style.display = "block";               
            if(icmpType.value == "3") {              
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                        {id: "0", name : translator.common.firewall.networkUnreachable},
                        {id: "1", name : translator.common.firewall.hostUnreachable},
                        {id: "2", name : translator.common.firewall.protocolUnreachable},
                        {id: "3", name : translator.common.firewall.portUnreachable},
                        {id: "4", name : translator.common.firewall.fragmentationNeeded},
                        {id: "5", name : translator.common.firewall.sourceRouteFailed},
                        {id: "6", name : translator.common.firewall.designationNetworkUnknown},
                        {id: "7", name : translator.common.firewall.designationHostUnknown},
                        {id: "8", name : translator.common.firewall.sourceHostIsolated},
                        {id: "9", name : translator.common.firewall.networkAdministrativelyProhibited},
                        {id: "10", name : translator.common.firewall.hostAdministrativelyProhibited},
                        {id: "11", name : translator.common.firewall.networkUnreachableForTos},
                        {id: "12", name : translator.common.firewall.hostUnreachableForTos}
                    ]
                };                
                var cidrCodefor3List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("icmpCodeWidget").set("store", cidrCodefor3List);
                dijit.byId("icmpCodeWidget").set("value","0");                
            } else if(icmpType.value == "5") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                        {id: "0", name : translator.common.firewall.datagramForNetwork},
                        {id: "1", name : translator.common.firewall.datagramForHost},
                        {id: "2", name : translator.common.firewall.datagramForTosAndNetwork},
                        {id: "3", name : translator.common.firewall.datagramForTosAndHost}                       
                    ]
                };                
                var cidrCodefor5List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("icmpCodeWidget").set("store", cidrCodefor5List);
                dijit.byId("icmpCodeWidget").set("value","0");    
            } else if(icmpType.value == "11") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                        {id: "0", name : translator.common.firewall.ttlExpiredTransit},
                        {id: "1", name : translator.common.firewall.fragmentationReassenblyExceeded}                                    
                    ]
                };                
                var cidrCodefor11List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("icmpCodeWidget").set("store", cidrCodefor11List);
                dijit.byId("icmpCodeWidget").set("value","0");    
            } else if(icmpType.value == "12") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
                        
//                        {id: "-1", name : "All"},
                        {id: "0", name : translator.common.firewall.pointerIndicatesError},
                        {id: "1", name : translator.common.firewall.missingRequiredOption},
                        {id: "2", name : translator.common.firewall.badLength}                                                          
                    ]
                };                
                var cidrCodefor12List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("icmpCodeWidget").set("store", cidrCodefor12List);
                dijit.byId("icmpCodeWidget").set("value","0");    
            }
        }
    },
    changeIngressLabel : function(selectWidget) {
      
        var allOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        }
        var AllIcmpCode = new ItemFileWriteStore({data: allOptions});
        AllIcmpCode.newItem({id: "-1", name: translator.common.message.noCode})
        
        dijit.byId("icmpCodeWidget").set("store", AllIcmpCode);
        
        dijit.byId("icmpCodeWidget").set("value", "-1");        

        dijit.byId("icmpTypeWidget").set("value","-1"); 
        var span = "<span class='require'>*</span>" 
        if(selectWidget.value == "ICMP") {
            
            dojo.byId("userIngressStartPort").innerHTML = translator.common.firewall.icmpType + span;
            dojo.byId("userIngressEndPort").innerHTML = translator.common.firewall.icmpCode + span;                


            dojo.byId("adminSecurityIngressStartPortList").style.display = "none";
            dojo.byId("icmpTypeList").style.display = "block";
            
            dojo.byId("endportContainer").style.display = "none";            
            dijit.byId("securityGroupStratPort").setValue("-1");
            dijit.byId("securityGroupEndPort").setValue("-1");                  
        } else {            
            dojo.byId("endportContainer").style.display = "block";
            dojo.byId("endPortList").style.display = "block";
            dojo.byId("userIngressStartPort").innerHTML = translator.common.firewall.startPort + span;
            dojo.byId("userIngressEndPort").innerHTML = translator.common.firewall.endPort + span;            
            dojo.byId("adminSecurityIngressStartPortList").style.display = "block";
            dojo.byId("icmpTypeList").style.display = "none";                        
            dojo.byId("icmpCodeList").style.display = "none";
            dijit.byId("securityGroupStratPort").setValue("");
            dijit.byId("securityGroupEndPort").setValue("");                           
        }
    },
    addIngress : function() {        
        var securityGroupIngressRestStoreStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/ingress"         
        });        
        var firstNode = ""
        var status = true;
        var node = dojo.byId("userFirewallIngressForm");
        var widget = dijit.registry.findWidgets(node);        

        dojo.forEach(widget, function(el) {

            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
                status = false
            }
        });
            
        var protocol = dijit.byId("securityGroupIngressProtocol").value;        
        var cidr = dijit.byId("securityGroupCidr").value;                  
        
        var startPort = "";
        var endPort = "";
        var securityGroupId =  this._currentId
         
         if(protocol == "ICMP") {              
             startPort = dijit.byId("icmpTypeWidget").value;
             endPort = dijit.byId("icmpCodeWidget").value;   
             dijit.byId("securityGroupStratPort").setValue(dijit.byId("icmpTypeWidget").value);
             dijit.byId("securityGroupEndPort").setValue(dijit.byId("icmpCodeWidget").value);
         } else {        
             startPort = dijit.byId("securityGroupStratPort").getValue().toString();
             endPort = dijit.byId("securityGroupEndPort").getValue().toString();
         }
        
        if(status == true) {
            dijit.byId('ingressAddButton').set('style', { display: 'none' });
            dojo.byId("ingressLoader").style.display = "block";
            
            dijit.byId("securityGroupIngressProtocol").set("disabled", true);
            dijit.byId("securityGroupCidr").set("disabled", true);
            dijit.byId("securityGroupStratPort").set("disabled", true);
            dijit.byId("securityGroupEndPort").set("disabled", true);
            dijit.byId("icmpTypeWidget").set("disabled", true);
            dijit.byId("icmpCodeWidget").set("disabled", true);
            
            
            securityGroupIngressRestStoreStore.add({
            protocol: protocol, 
            startPort: startPort, 
            endPort: endPort,
            cidr: cidr,
            securityGroupId: this._currentId
        }).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if(resultData.result == "OK") {                   
                    var securityGroupJobStatus = setInterval(function(){UserSecurityIngressInfo.securityGroupIngressRuleJob(resultData.jobId, securityGroupJobStatus, resultData.ruleType, securityGroupId);},3000); 
                    dijit.byId('ingressAddButton').set('style', { display: 'none' });
                    dojo.byId("ingressLoader").style.display = "block";
                } else {                  
                    registry.byId("userToaster").setContent(translator.common.firewall.inboundAddError,"error");
                    registry.byId("userToaster").show();
                    dijit.byId('ingressAddButton').set('style', { display: 'block' });
                    dojo.byId("ingressLoader").style.display = "none";                
                    
                    dijit.byId("securityGroupIngressProtocol").set("disabled", false);
                    dijit.byId("securityGroupCidr").set("disabled", false);
                    dijit.byId("securityGroupStratPort").set("disabled", false);
                    dijit.byId("securityGroupEndPort").set("disabled", false);
                    dijit.byId("icmpTypeWidget").set("disabled", false);
                    dijit.byId("icmpCodeWidget").set("disabled", false);
                }
            });                        
        });
        }
                
    },
    securityGroupIngressRuleJob : function(jobId, securityGroupJobStatus, ruleType, securityGroupId) {      
        var securityGroupJobRestStoreStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/job/"         
        });
        var  grid = dijit.byId("userIngressGrid");        
        
        securityGroupJobRestStoreStore.add({
            jobId: jobId,
            ruleType: ruleType
        }).then(function(response) {
            dojo.forEach(response, function(jobResultData) {
                dijit.byId("securityGroupIngressProtocol").set("disabled", false);
                dijit.byId("securityGroupCidr").set("disabled", false);
                dijit.byId("securityGroupStratPort").set("disabled", false);
                dijit.byId("securityGroupEndPort").set("disabled", false);
                dijit.byId("icmpTypeWidget").set("disabled", false);
                dijit.byId("icmpCodeWidget").set("disabled", false);
                if(jobResultData.jobResult == "OK") {
                    clearInterval(securityGroupJobStatus);

                    UserSecurityIngressInfo.populateValues(securityGroupId);
                    dijit.byId("userFirewallIngressForm").reset();  
                    
                    registry.byId("userToaster").setContent(translator.common.firewall.inboundAddSuccess,"message");
                    registry.byId("userToaster").show();
                    dijit.byId('ingressAddButton').set('style', { display: 'block' });
                    dojo.byId("ingressLoader").style.display = "none";
                    
                } else {
                    clearInterval(securityGroupJobStatus);
                    registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                    registry.byId("userToaster").show();                    
                    dijit.byId('ingressAddButton').set('style', { display: 'block' });
                    dojo.byId("ingressLoader").style.display = "none";
                }
            });
        });
    }
};

var UserSecurityGroupInfo = {
    _securityGroupRestStore:"",
    _securityTempRuleRestStore: "",
    _securityEgressRestStore:"",
    init : function() {
        this._securityGroupRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/"         
        });
        
        this._securityIngressRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/ingress/"
        });
        
        this._securityEgressRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/egress/"
        });
    },
    populateValues : function() {        
        if(dijit.byId("securityGroupGridWidget")) {
            dijit.byId("securityGroupGridWidget").destroyRecursive();                                
        }
        
        dojo.byId("securityGroupList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.firewall.firewallLoading+"</p>";
        var securityGroupData = {
            items: []
        };
        var securityGroupList = new ItemFileWriteStore({data: securityGroupData});
        var securityGroupLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.user.grid.snapshot.layout.instanceName, 'field': 'allocatedVm', 'width': '350px', datatype:"string",  autoComplete: true,'formatter': function(data) {                                                                      
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.common.firewall.name, 'field': 'firewall', 'width': '350px', datatype:"string",  autoComplete: true},                 
                {'name': translator.user.grid.instance.layout.action, 'field': 'action', 'width': '100%', datatype:"string",  autoComplete: true, 'formatter' : function(securityGroupId) {
                        var html = "<a title='"+translator.common.firewall.managePort+"' class='btn-flat success' href='#/user/cloud/ingress/"+securityGroupId+"'>"+translator.common.firewall.managePort+"</a>";
                        return html;
                    }
                }
            ]
        ]; 
        this._securityGroupRestStore.query().then(function(data) {
            if(data.length-1  == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noFirewallMessageBox").style.display = "block"; 
                dojo.byId("securityGroupList").innerHTML = "";
            } else {
                dojo.byId("noFirewallMessageBox").style.display = "none";
                dojo.forEach(data, function(securityGroupData) {
                    if(securityGroupData.securityGroupName == "default") {                  
                        return;
                    }  
                    securityGroupList.newItem({                        
                        id: securityGroupData.securityGroupId,
                        firewall: securityGroupData.securityGroupName,
                        allocatedVm: securityGroupData.allocatedVm.toString(),
                        action: securityGroupData.securityGroupId
                    });                                                    
                });
                dojo.byId("noFirewallMessageBox").style.display = "none"; 
                dojo.byId("securityGroupList").innerHTML = "";
                var securityGroupGridWidget = new EnhancedGrid({
                    id: "securityGroupGridWidget",
                    "class" : "span12",
                    store: securityGroupList,
                    structure: securityGroupLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins            
                });
                securityGroupGridWidget.placeAt("securityGroupList");
                securityGroupGridWidget.startup();                           
            }            
        });                
    },
    addEgress : function() {
        
        var securityGroupEgressRestStoreStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/egress"         
        });
        
        var items = dijit.byId("securityGroupGridWidget").selection.getSelected();
        var protocol = dijit.byId("securityGroupEgressProtocol").value;
        var startPort = dijit.byId("securityGroupEgressStartPort").value;
        var endPort = dijit.byId("securityEgressGroupEndPort").value;
        var cidr = dijit.byId("securityGroupEgressCidr").value;
        
        var securityGroupId;
        dojo.forEach(items, function(selectedItem) {
            if(selectedItem) {                
                dojo.forEach(selectedItem.id, function(el) {
                    securityGroupId = el;
                });
            }
        });
        securityGroupEgressRestStoreStore.add({
            protocol: protocol, 
            startPort: startPort, 
            endPort: endPort,
            cidr: cidr,
            securityGroupId: securityGroupId
        }).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if(resultData.result == "OK") {
                    var securityGroupJobStatus = setInterval(function(){UserSecurityGroupInfo.securityGroupRuleJob(resultData.jobId,resultData.ruleType, securityGroupJobStatus);},3000); 
                    
                } else {
                    registry.byId("userToaster").setContent(translator.common.firewall.addEgressError,"error");
                    registry.byId("userToaster").show();
                }
            });
                        
        }); 
    }
};
var CurrentInstanceInfo = {
    _accountListStore :"",
    _volumeRestStore : "",
    init : function() {
        this._volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"         
        }); 
        
        this._accountListStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"         
        }); 
        
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;                          
    },
    resetVMConfirmMsg : function () { 
        dojo.style(dijit.byId("vmInfoResetDialog").closeButtonNode,"display","none");
        dijit.byId("vmInfoResetDialog").show();        
    },
    resetVM: function () {  
        dijit.byId("vmDetailResetVMCancelButton").set("disabled", true);
        dojo.query("#vmDetailResetVMImgLoader").removeClass("hide_text", true); 
        dojo.query("#vmDetailResetVMButton").toggleClass("hide_text", true); 
        
        var instanceResetRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/reset/"         
        });  
           var currentVmId =  dojo.byId("currentInstanceReferenceId").value;           
            instanceResetRestStore.add({
                referenceId: currentVmId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                    
                    if(resultData.result === "OK") {
                        var resetVMJobStatus = setInterval(function(){CurrentInstanceInfo.resetVMJob(resultData.jobId, resultData.vmName, resetVMJobStatus);},3000);                   
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.resetVMError, "error");
                        registry.byId("userToaster").show();  
                        dijit.byId("vmDetailResetVMCancelButton").set("disabled", false);
                        dojo.query("#vmDetailResetVMImgLoader").toggleClass("hide_text", true); 
                        dojo.query("#vmDetailResetVMButton").removeClass("hide_text", true);
                        dijit.byId("vmInfoResetDialog").hide();
                    }
                });                
            });
    },
    resetVMJob : function (jobId, vmName, vmResetStatus) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/reset/job"
        }); 
        jobStore.add({
            jobId : jobId            
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult == "OK") {
                    clearInterval(vmResetStatus);
                    registry.byId("userToaster").setContent("VM:" + vmName + "  ,  " +translator.common.instance.resetVMSucess, "message");
                    registry.byId("userToaster").show();                            
                    dijit.byId("vmDetailResetVMCancelButton").set("disabled", false);
                    dojo.query("#vmDetailResetVMImgLoader").toggleClass("hide_text", true); 
                    dojo.query("#vmDetailResetVMButton").removeClass("hide_text", true);
                    dijit.byId("vmInfoResetDialog").hide();
                } else if(resultData.jobResult == "Pending") {
                } else  if(resultData.jobResult == "FAILED") {
                    clearInterval(vmResetStatus);
                    registry.byId("userToaster").setContent(translator.common.instance.resetVMError,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("vmDetailResetVMCancelButton").set("disabled", false);
                    dojo.query("#vmDetailResetVMImgLoader").toggleClass("hide_text", true); 
                    dojo.query("#vmDetailResetVMButton").removeClass("hide_text", true);
                    dijit.byId("vmInfoResetDialog").hide();
                }
            });
        });
    },
    cancelResetVMDialog : function () {
        dijit.byId("vmInfoResetDialog").hide();        
    },
    showCondition : function () {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/public/termsAndCondition"
        });  
        dojo.style(dijit.byId("vmInfoContentDialog").closeButtonNode,"display","none");
        configRestStore.query().then(function(resultData) {
            dojo.byId("vmInfoContentArea").innerHTML = resultData.value;                   
        });
        dijit.byId("acquireIpDialog").hide();
        dijit.byId("vmInfoContentDialog").show();
    },
    cancelPasswordReset : function () {
      dijit.byId("showPasswordDialog").hide()  
    },
    viewConsole: function() {            
            var currentVmId =  dojo.byId("currentInstanceReferenceId").value;
            
            var instanceConsoleStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/console/"         
            });
            instanceConsoleStore.add({
                referenceId:  currentVmId
            }).then(function(result) {
                window.open(result[0].url,currentVmId,'width=800,height=650');
                
            });
//            dijit.byId("consoleScreen").hide();
        }, 
        
    'acquireIp': function() {            
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;        
        var instanceConsoleStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/ip/acquire/"         
        });
        var agrementChecked =  dijit.byId("vmInfoAgreement").checked;
        if(agrementChecked == true) {
            dojo.byId("conditionExceptionMsg").style.display = "none";
            dijit.byId("ipLoader").show(); 
            dijit.byId("acquireIpDialog").hide();         
            instanceConsoleStore.add({
                vmId:  currentVmId
            }).then(function(result) {
                dojo.forEach(result, function(resultData) {
                   if(resultData.result == "OK") {
                       var acquireIpJobStatus = setInterval(function(){CurrentInstanceInfo.acquireIpJob(resultData.jobId, acquireIpJobStatus, resultData.vmId);},3000); 
                   } else {
                        dijit.byId("ipLoader").hide();  
                        registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                        registry.byId("userToaster").show();
                   }                                        
               });                            
            });
        } else {
            dojo.byId("conditionExceptionMsg").style.display = "block";
        }
        
    }, 
    'acquireIpJob': function(jobId, attachIsoJobStatus, vmId) {         
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
                    dijit.byId("ipLoader").hide();
                    dojo.byId("vmPassword").innerHTML = resultData.ip;
//                    dijit.byId("showPasswordDialog").show();                    
                } else if(resultData.jobResult == "Pending") {
                } else  if(resultData.jobResult == "FAILED") {
                    clearInterval(attachIsoJobStatus);
                    registry.byId("userToaster").setContent(translator.common.ip.invalidAcquireIpMessage,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("ipLoader").hide();
                }
            });
        });
    },  
    refresh: function() {        
        dojo.byId("instanceUsageLoad").style.display = "block";
        dojo.byId("instanceUsageDiv").style.display = "none";
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;
            
        var instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });                      
        
        instanceRestStore.get(currentVmId).then(function(data) {
            dojo.forEach(data, function(instanceData) {          
                var cpuused;               
                if(instanceData.cpuUsed == null || instanceData.cpuUsed == "null") {
                   cpuused = "0";
               } else {
                   cpuused = instanceData.cpuUsed;
               }
            
                var bandWidth = {
                    'totalBandwidth': instanceData.bandWidth,
                    'read': instanceData.read,
                    'write': instanceData.write,
                    'cpuUsed': cpuused                    
                };                
                CurrentInstanceInfo.loadPieChart(bandWidth);                           
            });
        });
            
        },   
        cancelConditionBox : function () {
            dijit.byId("vmInfoContentDialog").hide();
            dijit.byId("acquireIpDialog").show();
        },
        showCreateVMSnapshotDialog: function() {
        dojo.byId("currentVMSnapshotBillingTypeDiv").style.display = "none";
        
        var accountListStore = new JsonRest({
        target: core.getContextPath()+"/api/account/currentAccount"         
        });
        accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {  
                if(el.accountType == "TRIAL") {
                    registry.byId("userToaster").setContent(translator.common.message.noVMSnapforTrial,"error");
                    registry.byId("userToaster").show();
                } else {
                    dijit.byId("currentVMSnapshotFormPage").reset();
                    dijit.byId("currentVMSnapshotDialog").show();
                }
            });
        });
      
    },
    closeCreateVMSnapshotDialog: function() {
        dijit.byId("currentVMSnapshotDialog").hide();
    },
    createVMSnapshot: function() {           
        var formElements = dojo.query("#currentVMSnapshotBillingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        
        var currentVMSnapshotMemory = dijit.byId("currentVMSnapshotMemory").checked;
        
        var vmSnapshotRest = new JsonRest({
           target: core.getContextPath()+"/api/snapShot/createVMSnapshot/"
        });
        
        var pageNode = dojo.byId("currentVMSnapshotForm");
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
            dijit.byId("currentVMSnapshotLoader").show();
            vmSnapshotRest.add({
               name:dijit.byId("currentVMSnapshotName").value,
               description:dijit.byId("currentVMSnapshotDescription").value,
               billingType:"hourly",
               vmId: dojo.byId("currentInstanceReferenceId").value,
               snapshotMemory:currentVMSnapshotMemory
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.result == "OK") {                      
                        var createVMSnapJobStatus = setInterval(function(){CurrentInstanceInfo.createVMSnapshotJob(resultData.jobId, createVMSnapJobStatus);},3000);  
                        dijit.byId("currentVMSnapshotDialog").hide();
                    } else if(resultData.result == "Pending") {

                    } else  if(resultData.result == "FAILED") {
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotCreateVmSnapshot,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("currentVMSnapshotLoader").hide();
                        dijit.byId("currentVMSnapshotDialog").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotCreateVmSnapshot,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("currentVMSnapshotLoader").hide(); 
                        dijit.byId("currentVMSnapshotDialog").hide();
                    }

                });
            });
        }
    },
   createVMSnapshotJob : function(jobId, createVMSnapshotJobStatus) {
        var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/snapShot/createVMSnapshotJob/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                    
                    if(resultData.jobResult == "OK") {
                        clearInterval(createVMSnapshotJobStatus);
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotCreated,"message");
                        registry.byId("userToaster").show();                                               
                        dijit.byId('currentVMSnapshotLoader').hide();
                        dijit.byId("currentVMSnapshotDialog").hide();
                        core.router.go("#/user/cloud/userInstancePage");    
                    } else if(resultData.jobResult == "Pending") {

                    } else {
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotCreateVmSnapshot,"error");
                        registry.byId("userToaster").show();
                        clearInterval(createVMSnapshotJobStatus);
                        dijit.byId('currentVMSnapshotLoader').hide();
                        dijit.byId("currentVMSnapshotDialog").hide();
                    }
                });
            });
    },
    showVPCPage : function () {          
        var currentVPC = dojo.byId("selectedANVPCID").value;
        VPCMenuInfo.populateValues(currentVPC);        
    },
    populateValues : function(currentVM) {        
        var instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/byVMId"
        });

        instanceRestStore.query({vmId: currentVM}).then(function (currentVM) {              
            document.getElementById("revokeIpLink").href="#/user/service/ip/"+currentVM.referenceId; 
            document.getElementById("viewVMSnapLink").href="#/user/cloud/vmSnapShot/"+currentVM.referenceId; 
            var getVMRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/"
            });
            dojo.byId("currentInstanceReferenceId").value = currentVM.referenceId;
            getVMRestStore.get(currentVM.referenceId).then(function(data) {
            dojo.forEach(data, function(instanceData) {
                if(instanceData.networkType === "Advanced") {                    
                    dojo.query("#networkWriteDiv").toggleClass("hide_text", true); 
                    dojo.query("#networkInfo").removeClass("hide_text", true); 
                    dojo.query("#aquireIPContainer").toggleClass("hide_text", true);
                    dojo.query("#viewSecondIPAction").removeClass("hide_text", true); 
                    dojo.query("#acquireIpDiv").toggleClass("hide_text", true); 
                    dojo.query("#revokeIpDiv").toggleClass("hide_text", true); 
                    dojo.query("#vmBandwidthInfo").toggleClass("hide_text", true); 
                    dojo.query("#vmFirewallInfo").toggleClass("hide_text", true); 
                    dojo.byId("nicIp").innerHTML = instanceData.defaultNicIp; 
                    dojo.byId("networkName").innerHTML = instanceData.networkName + "  " + " ( " + " "+ translator.common.defaultVal +" "+ " ) ";
                    dojo.byId("networkName").href = "#/user/network/view/" + instanceData.networkID;
                    
                    if(instanceData.hasVPC === true) {
                      
                        dojo.byId("vmDetailMainMenuName").href= "#/user/vpc/dashboard";     
                        dojo.byId("vmDetailMainMenuName").onclick = function () {CurrentInstanceInfo.showVPCPage();}                        
                        dojo.byId("vmDetailSubMenu1").onclick = function () {CurrentInstanceInfo.showVPCPage()};
                        dojo.byId("vmDetailSubMenu2").onclick = function () {CurrentInstanceInfo.showVPCPage()};
                        
                        
                        dojo.byId("vmDetailSubMenu1").innerHTML = translator.common.vpc.name;   
                        
                        dojo.byId("vmDetailSubMenu1").href = "#/user/vpc/list";
                        dojo.byId("vmDetailSubMenu2").innerHTML = instanceData.vpcName;                                        
                        dojo.byId("vmDetailSeparator1").innerHTML = "/";
                        dojo.byId("vmDetailSeparator2").innerHTML = "/";
                        dojo.byId("vmDetailSeparator3").innerHTML = "/";                                                      
                        dojo.byId("vmDetailSubMenu2").href = "#/user/vpc/view/"+instanceData.vpcReferenceId;
                        
                        dojo.byId("vmDetailSubMenu3").innerHTML = "";
                        var instanceMenu1 = "<a href='#/user/vpc/view/"+instanceData.vpcReferenceId+"' onclick='CurrentInstanceInfo.showVPCPage(); ViewVpc.showTierTab()'>" + translator.common.tiers  + "</a>";
                        domConstruct.place(instanceMenu1, dojo.byId("vmDetailSubMenu3"));   
                        
                        dojo.byId("vmNameMenu").innerHTML = "";
                        var instanceMenu2 = "<a href='#/user/tier/view/"+instanceData.networkID+"' onclick='CurrentInstanceInfo.showVPCPage(); CurrentInstanceInfo.showVPCPage();'>" + instanceData.networkName  + "</a>";
                        domConstruct.place(instanceMenu2, dojo.byId("vmNameMenu")); 
                        
                        dojo.byId("vmDetailSubMenu4").innerHTML = "";
                        var instanceMenu3 = "<a href='#/user/tier/instanceList/"+instanceData.networkID+"' onclick='CurrentInstanceInfo.showVPCPage()'>" +  translator.common.menu.instance   + "</a>";
                        domConstruct.place(instanceMenu3, dojo.byId("vmDetailSubMenu4")); 
                        
                        dojo.byId("vmDetailSubMenu5").innerHTML = instanceData.name;
                        
                    }
                    
                } else {
                    dojo.query("#networkWriteDiv").removeClass("hide_text", true); 
                    dojo.query("#aquireIPContainer").removeClass("hide_text", true);
                    dojo.query("#networkInfo").toggleClass("hide_text", true); 
                    dojo.byId("nicIp").innerHTML = instanceData.nicIp; 
                    dojo.query("#viewSecondIPAction").toggleClass("hide_text", true); 
                    dojo.query("#acquireIpDiv").removeClass("hide_text", true); 
                    dojo.query("#revokeIpDiv").removeClass("hide_text", true); 
                    dojo.query("#vmBandwidthInfo").removeClass("hide_text", true); 
                    dojo.query("#vmFirewallInfo").removeClass("hide_text", true); 
                }
                dojo.byId("instanceBilingType").value = instanceData.instanceBilingType;
                if(instanceData.accountType == "TRIAL") {
                    dojo.byId("acquireIpDiv").innerHTML = translator.common.ip.acquire;
                    dojo.byId("revokeIpDiv").innerHTML = translator.common.ip.revoke;
                }                                
                if(instanceData.osCategory == "CentOS") {
                    dojo.byId("osImage").src = "images/os/os_centos_logo.png";
                } else if(instanceData.osCategory == "Debian") {
                    dojo.byId("osImage").src = "images/os/os_debian_logo.png";
                } else if(instanceData.osCategory == "Oracle") {
                    dojo.byId("osImage").src = "images/os/os_oracle_logo.png";
                } else if(instanceData.osCategory == "RedHat") {
                    dojo.byId("osImage").src = "images/os/os_redhat_logo.png";
                } else if(instanceData.osCategory == "SUSE") {
                    dojo.byId("osImage").src = "images/os/os_suse_logo.png";
                } else if(instanceData.osCategory == "Windows") {
                    dojo.byId("osImage").src = "images/os/os_win_logo.png";
                } else if(instanceData.osCategory == "Novel") {
                    dojo.byId("osImage").src = "images/os/os_novell_logo.png";
                } else if(instanceData.osCategory == "Unix") {
                    dojo.byId("osImage").src = "images/os/os_unix_logo.png";
                } else if(instanceData.osCategory == "Ubuntu") {
                    dojo.byId("osImage").src = "images/os/os_ubuntu_logo.png"; 
                }
                if(instanceData.isoName != "null") {
                    dojo.byId("isoEject").style.display = "block";
                    dojo.byId("isoListDiv").style.display = "none";
                    dojo.byId("isoAttach").style.display = "block";
                    dojo.byId("isoName").innerHTML = instanceData.isoName; 
                    dojo.byId("isoId").value = instanceData.isoId; 
                } else {
                    dojo.byId("isoEject").style.display = "none";
                    dojo.byId("isoListDiv").style.display = "block";
                    dojo.byId("isoAttach").style.display = "none";
                }
                if(instanceData.baseOs == translator.common.baseOS.linux) {
                     dojo.byId("user").innerHTML = translator.root;
                } else {
                    dojo.byId("user").innerHTML = translator.admin.administrator;
                }
                dojo.byId("osName").innerHTML = instanceData.osType;
                dojo.byId("vmName").innerHTML = instanceData.name;
                if(instanceData.state == "Running") {
                    dojo.query("#consoleDiv").toggleClass("console-editor", true); 
                    dojo.query("#consoleDiv").removeClass("console-editor-disable", true); 
                    dijit.byId("startVMButton").setAttribute('disabled', true);
                    dojo.byId('status').style.color = "#387C2C"; 
                    dojo.byId("viewCosoleImg").onclick = function () {
                        CurrentInstanceInfo.viewConsole()
                    }
                    dojo.byId("viewCosoleImg").style.cursor = "pointer"
                } else if(instanceData.state == "Stopped") {
                    dojo.query("#consoleDiv").removeClass("console-editor", true); 
                    dojo.query("#consoleDiv").toggleClass("console-editor-disable", true); 
                     dijit.byId("stop").setAttribute('disabled', true);
                    dojo.byId('status').style.color = "#E0420B"; 
                    dojo.byId("viewCosoleImg").onclick = function () {                        
                    }
                    dojo.byId("viewCosoleImg").style.cursor = "auto";
                } else {
                    dijit.byId("stop").setAttribute('disabled', true);
                    dijit.byId("reboot").setAttribute('disabled', true);
                    dijit.byId("startVMButton").setAttribute('disabled', true);
                    dojo.byId('status').style.color = "#E0420B"; 
                }
                if(instanceData.state == "Running") {
                    dojo.byId("status").innerHTML = translator.common.instance.status.running
                } else if(instanceData.state == "Stopped"){
                    dojo.byId("status").innerHTML = translator.common.instance.status.stopped
                }
                dojo.byId("vmInfoIPCost").innerHTML = "*" + instanceData.currency + " "+instanceData.miscelIPCost + "/" + translator.common.month;
                dojo.byId("sshKeyName").innerHTML = instanceData.sshKey;
                dojo.byId("plan").innerHTML = instanceData.computingOffer; 
                dojo.byId("location").innerHTML = instanceData.zoneName; 
                
                dojo.byId("totalBandWidth").innerHTML = instanceData.bandWidth + translator.common.gb; 
                dojo.byId("locationId").value = instanceData.zoneId; 
                dojo.byId("templateId").value = instanceData.templateId;
                dojo.byId("instancePlanTempSize").innerHTML = instanceData.templateDiskSize + translator.common.gb;
                dojo.byId("baseOs").value = instanceData.baseOs;
                dojo.byId("computReferId").value = instanceData.computingOfferClusterId;
                dojo.byId("computTag").value = instanceData.computingOfferTags;
                dojo.byId("offerType").value = instanceData.storageType;
                dojo.byId("fireWall").innerHTML = "<a title = '"+translator.common.firewall.name+"' href='#/user/cloud/ingress/"+instanceData.securityGroupId+"'>"+instanceData.securityGroupName+"<a/>";
                var cpuused;
                if(instanceData.cpuUsed == null || instanceData.cpuUsed == "null") {
                   cpuused = "0";
               } else {
                   cpuused = instanceData.cpuUsed;
               }
                var bandWidth = {
                    'totalBandwidth': instanceData.bandWidth,
                    'read': instanceData.read,
                    'write': instanceData.write,
                    'cpuUsed': cpuused                   
                };
                dojo.byId("offerId").value = instanceData.computingOfferId;                
                CurrentInstanceInfo.loadPieChart(bandWidth);                                                                      
            });                        
        });
        var instanceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });

         var volumeRestStrore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        });         
        var volumeCount = 0;
        var currentVmId = currentVM.referenceId;
        volumeRestStrore.query({virtualMachineReferenceId:currentVM.referenceId}).then(function(data) {                      
            var accountResourceLimitStore = new JsonRest({
                    target: core.getContextPath()+"/api/account/resourceLimit"
            });
            
            
            instanceRestStore.get(currentVmId).then(function(data) {
                dojo.forEach(data, function(instanceData) {
                    if(instanceData.hasVMSnapshot == "true") {
//                        dojo.byId("addVolume").style.display = "none";
                        dojo.byId("vmNameEditDiv").style.display = "none";
                        dojo.byId("viewVMSnapLinkDiv").style.display = "block";
                        document.getElementById("vmSnapCount").innerHTML = instanceData.VMSnapListCount;
                    } else {
                        accountResourceLimitStore.query().then(function(data) {
                             dojo.forEach(data, function(limitData) {  
                                 if(limitData.accountType == "TRIAL") {
                                    if((limitData.volumeCount >= limitData.storageLimitNo )) {
//                                        dojo.byId("addVolume").style.display = "none";
                                        registry.byId("userToaster").setContent(translator.common.instance.volumeCountReachedVm,"error");
                                        registry.byId("userToaster").show();
                                     } else {
//                                        dojo.byId("addVolume").style.display = "block";
                                     }
                                 } else {
                                    if(volumeCount-1 >= 5) {
//                                        dojo.byId("addVolume").style.display = "none";
                                        registry.byId("userToaster").setContent(translator.common.instance.volumeCountReachedVm,"error");
                                        registry.byId("userToaster").show();
                                    } else {
//                                        dojo.byId("addVolume").style.display = "block"; 
                                    }
                                 }
                             });
                         });
                    }
                });
            });
        }); 
        })     
        
        var singleSignOnUrl;
        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                singleSignOnUrl = el.singleSignOnUrl;
            });
        });                                                                               
        var isoRestStore = new JsonRest({
            target: core.getContextPath()+"/api/iso/"
        });     
        
         var isoOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "", name: translator.common.instance.selectDVDDrive}]
        };
            
        var isoList = new ItemFileWriteStore({data: isoOptions});
          
       isoRestStore.query().then(function(data) {
            dojo.forEach(data, function(isoData) {
                isoList.newItem({id: isoData.isoReferenceId, name: isoData.name});
            });  
        });  
          
        var iso = new Select({
           id:"isoListWidget",
           style: "width: 400px;",
           store: isoList,
           maxHeight: -1, // tells _HasDropDown to fit menu within viewport 
           onChange:  function(value) {      
                
                var isoRestStore = new JsonRest({
                     target: core.getContextPath()+"/api/virtualMachine/attach"
                }); 
                var currentVmId =  dojo.byId("currentInstanceReferenceId").value;
                
                if(value != "") {
                    dojo.byId("isoLoader").style.display = "block";
                    dojo.byId("isoEject").style.display = "none";
                    isoRestStore.add({
                        isoReferenceId : value,
                        virtualmachineReferenceId:  currentVmId
                    }).then(function(data) {
                        dojo.forEach(data, function(resultData) {
                            if(resultData.result == "OK") {
                                var attachIsoJobStatus = setInterval(function(){CurrentInstanceInfo.attachIsoJob(resultData.jobId, attachIsoJobStatus);},3000);                                      
                            } else {
                                dojo.byId("isoLoader").style.display = "none";
                                dojo.byId("isoEject").style.display = "block";
                                registry.byId("userToaster").setContent(translator.common.instance.connotAttachIso,"error");
                                registry.byId("userToaster").show();
                            }

                        });
                    });
                }
           }
       }).placeAt("isoListDiv"); 
       iso.startup();                
   },
    loadPieChart: function(bandWidthData) {
        
//        dojo.byId("bandWidthReadUsageChart").innerHTML = "";
//        dojo.byId("bandWidthWriteUsageChart").innerHTML = "";
//        dojo.byId("cpuUsageChart").innerHTML = "";
               
        var bandWidth = bandWidthData.totalBandwidth;
        var bandWidthReadUsed = ((bandWidthData.read / 1048576) * 100) / bandWidth;
        var bandWidthWriteUsed = ((bandWidthData.write / 1048576) * 100) / bandWidth;
        var cpuUsage =  parseFloat(bandWidthData.cpuUsed.replace('%',""));
        
        var read = Math.round((bandWidthData.read / 1048576) * 100) / 100;
        var write = Math.round((bandWidthData.write / 1048576) * 100) / 100;

        var bandWidthReadExceeded = (bandWidthData.read / 1048576) - bandWidth;
        var bandWidthWriteExceeded = (bandWidthData.write / 1048576) - bandWidth;
       
        if(read > bandWidth) { 
            dojo.byId("readTotalProgress").style.display = "none";
            dojo.byId("readTotalExe").style.display = "block";
            dojo.byId("readTotalExe").innerHTML = translator.common.account.exceededBy+ Math.round((bandWidthReadExceeded) * 100) / 100 +  translator.common.gb;
            dojo.removeClass("readTotal", "resource_items_allocation");
            domClass.add("readTotal", "resource_items_allocation_exceeded");            
        } else {            
        }
        if(write > bandWidth) {
            dojo.byId("writeTotalProgress").style.display = "none";
            dojo.byId("writeTotalExe").style.display = "block";
            dojo.byId("writeTotalExe").innerHTML = translator.common.account.exceededBy + Math.round((bandWidthWriteExceeded) * 100) / 100 + " " + translator.common.gb;
            dojo.removeClass("writeTotal", "resource_items_allocation");
            domClass.add("writeTotal", "resource_items_allocation_exceeded");
        } else {
            
        }
        
        dojo.byId("readTotal").innerHTML =  Math.round((bandWidthData.read / 1048576) * 100) / 100 + translator.common.gb+"/" + bandWidth + translator.common.gb; 
        dojo.byId("writeTotal").innerHTML =  Math.round((bandWidthData.write / 1048576) * 100) / 100 + translator.common.gb+ "/ " + bandWidth + translator.common.gb;
        dojo.byId("cpuTotal").innerHTML =  Math.round(cpuUsage * 100) / 100 + " % / 100 %";  
        
        dijit.byId("instanceNetworkRead").set("value", Math.round(bandWidthReadUsed * 100) / 100 % 100.00); 
        dijit.byId("instanceCpuUsage").set("value", Math.round(cpuUsage * 100) / 100 % 100.00); 
        dijit.byId("instanceNetworkWrite").set("value", Math.round(bandWidthWriteUsed * 100) / 100 % 100.00); 
        
        dojo.byId("instanceUsageLoad").style.display = "none";
        dojo.byId("instanceUsageDiv").style.display = "block";                

    },        
    attachIsoJob: function(jobId, attachIsoJobStatus) {         
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/job/attachIso"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(attachIsoJobStatus);
                    dojo.byId("isoListDiv").style.display = "none";
                    dojo.byId("isoAttach").style.display = "block";
                    dojo.byId("isoLoader").style.display = "none";
                    dojo.byId("isoName").innerHTML = resultData.isoName; 
                    dojo.byId("isoId").value = resultData.isoId; 
                    dojo.byId("isoEject").style.display = "block";
                    registry.byId("userToaster").setContent(translator.common.instance.isoAttached,"message");
                    registry.byId("userToaster").show();
                } else if(resultData.jobResult == "Pending") {

                } else  if(resultData.jobResult == "FAILED"){
                    clearInterval(attachIsoJobStatus);
                    dojo.byId("isoListDiv").style.display = "block";
                    dojo.byId("isoAttach").style.display = "none";
                    dojo.byId("isoLoader").style.display = "none";
                    dojo.byId("isoEject").style.display = "none";
                    registry.byId("userToaster").setContent(translator.common.instance.isoAttachedError,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },  
    detachIso: function() {
        dojo.byId("isoLoader").style.display = "block";
        dojo.byId("isoEject").style.display = "none";
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;
        var isoRestStore = new JsonRest({
             target: core.getContextPath()+"/api/virtualMachine/detach"
         }); 
         isoRestStore.add({
             virtualmachineReferenceId:  currentVmId
         }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                 if(resultData.result == "OK") {
                    var detachIsoJobStatus = setInterval(function(){CurrentInstanceInfo.detachIsoJob(resultData.jobId, detachIsoJobStatus);},3000);                                      
                 } else {
                     dojo.byId("isoLoader").style.display = "none";
                     dojo.byId("isoEject").style.display = "block";
                     registry.byId("userToaster").setContent(translator.common.instance.isoDettachedError,"error");
                     registry.byId("userToaster").show();
                 }
             });
         });
    },
    detachIsoJob: function(jobId, detachIsoJobStatus) {         
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(detachIsoJobStatus);
                    dojo.byId("isoLoader").style.display = "none";
                    dojo.byId("isoListDiv").style.display = "block";
                    dojo.byId("isoAttach").style.display = "none";
                    dojo.byId("isoName").innerHTML = ""; 
                    dojo.byId("isoId").value = ""; 
                    dojo.byId("isoEject").style.display = "none";
                    registry.byId("userToaster").setContent(translator.common.instance.isoDettached,"message");
                    registry.byId("userToaster").show();
                    setTimeout(function(){
                        dijit.byId("isoListWidget").reset();
                    },1000); 
//                    dijit.byId("isoListWidget").setAttribute("value", "");
//                    dijit.byId("isoList").reset();

                } else if(resultData.jobResult == "Pending") {

                } else  if(resultData.jobResult == "FAILED"){
                    clearInterval(detachIsoJobStatus);
                    dojo.byId("isoListDiv").style.display = "none";
                    dojo.byId("isoAttach").style.display = "block";
                    dojo.byId("isoLoader").style.display = "none";
                    dojo.byId("isoEject").style.display = "block";
                    registry.byId("userToaster").setContent(translator.common.instance.isoDettachedError,"error");

                    registry.byId("userToaster").show();
                }
            });
        });
    },      
    showAcquireIp :function() {
        
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.publicIPLimit !== "-1") {                   
                    if((limitData.publicIPUsed >= limitData.publicIPLimit)) {
                        dojo.byId("vmInfoAddIPLimitMsg").style.display = "block";
                        dojo.query("#vmInfoAddIPActionContainer").toggleClass("hide_text", true);                                           
                    } else {
                        dojo.byId("vmInfoAddIPLimitMsg").style.display = "none";
                        dojo.query("#vmInfoAddIPActionContainer").removeClass("hide_text", true);    
                    }
                } else {
                    dojo.byId("vmInfoAddIPLimitMsg").style.display = "none";
                    dojo.query("#vmInfoAddIPActionContainer").removeClass("hide_text", true);
                }
            });
        });
        
        
        dojo.byId("conditionExceptionMsg").style.display = "none";
        dijit.byId("vmInfoAgreement").set("checked", false);
        dijit.byId("acquireIpDialog").show(); 
    },
    vmSnapshot :function() {
        dojo.byId("currentWidgetId").value = dojo.byId("rootDiskId").value;
        dijit.byId("vmCreateSnapshot").show(); 
    },    
    closeAcquireIpDialog:function() {
       dijit.byId("acquireIpDialog").hide(); 
    },       
    showChangeHostName:  function() {
        var currentVMName = dojo.byId("vmName").innerHTML;
        dijit.byId("hostName").setValue(currentVMName);
        dijit.byId("changeHostNameDialog").show();
    },
    closeChangeHostName:  function() {
         dijit.byId("changeHostNameDialog").hide();
    },
    changeHostName:  function() {
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;   
        var virtualMachineStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        }); 
        
        var status = true;       
        if (dijit.byId("hostName").validate && !dijit.byId("hostName").validate()) {
            dijit.byId("hostName").focus(); 
            status = false;               
        }        
        if(status == true) {
            dijit.byId('changeName').set('style', {display: 'none'});
            dojo.byId("changeHostNameLoader").style.display = "inline";
            virtualMachineStore.put({
            id:currentVmId,
            virtualMachineName:  dijit.byId("hostName").value
        }).then(function(data) { 
            dijit.byId("changeHostNameDialog").hide();
            dojo.forEach(data, function(instanceData) {
                if(instanceData.result == "OK") {                    
                    dojo.byId("vmName").innerHTML = instanceData.name;                   
                    registry.byId("userToaster").setContent(translator.common.instance.vmNameChanged,"message");
                    registry.byId("userToaster").show();  
                    dijit.byId('changeName').set('style', {display: 'inline'});
                    dojo.byId("changeHostNameLoader").style.display = "none";
                } else {                        
                    registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                    registry.byId("userToaster").show();
                    dijit.byId('changeName').set('style', {display: 'inline'});
                    dojo.byId("changeHostNameLoader").style.display = "none";
                }                
            });
        });
    }                
},
    changeServicePopulateValues: function(instances) {
        if(dijit.byId("innerInstanceComputWidget")) {
            dijit.byId("innerInstanceComputWidget").destroyRecursive();
        } 
        dojo.byId("instancePlanCpu").innerHTML = "";
        dojo.byId("instancePlanRam").innerHTML = "";
        dojo.byId("instancePlanNwRate").innerHTML = "";
        dojo.byId("vmPlanIODisk").innerHTML = "";
//        dojo.byId("instancePlanTempSize").innerHTML = "";
        dojo.byId("instancePlanCost").innerHTML = "";
        dojo.byId("instancePlanSetupCost").innerHTML = "";
        dojo.byId("instancePlanBandwidth").innerHTML = "";                
        var baseOs = dojo.byId("baseOs").value;
        var clusterId =  dojo.byId("computReferId").value; 
        var offerId = dojo.byId("offerId").value;
        var templateReferenceId = dojo.byId("templateId").value;
        var tags = dojo.byId("computTag").value;                
        var type = dojo.byId("offerType").value;         
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/cluster"
        }); 
        var currentZone = dojo.byId("locationId").value;
        var computOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var computList = new ItemFileWriteStore({data: computOptions});                
        computingOfferRestStore.query({tags:tags, type:type, templateReferenceId:templateReferenceId}).then(function(data) {            
            var firstVal = "";
            var cost = 0.00;
            var setupCost = 0.00;
            dojo.forEach(data, function(el) {  
                if(el.offerReferenceId == offerId) {
                    firstVal = el.offerReferenceId
                } 
                dojo.forEach(el.computingOfferZoneCosts, function (zoneData) {
                    if(zoneData.zone.referenceZoneId === currentZone) {
                        cost = localeCurrency.format(zoneData.cost, {places: 5, locale: dojo.locale}),
                        setupCost = localeCurrency.format(zoneData.setupCost, {places: 5, locale: dojo.locale})
                    }
                });
                computList.newItem({
                    id:el.offerReferenceId,
                    name:el.name,
                    cpuNumber:el.cpuNumber,
                    memory:el.memory,
                    coreCpuSpeed:el.coreCpuSpeed,
                    cost: cost,
                    setupCost: setupCost,
                    currency: el.currency,
                    networkRate: el.networkRate,
                    bandWidth: el.bandWidth,
                    diskReadRateIOPS : el.diskReadRateIOPS,
                    diskIO : el.diskIO
                });  
            }); 
            dojo.byId("OfferingListCollection").style.display = "block";
            dojo.byId("currentVMOfferingLoader").style.display = "none";
            clearInterval(instances);
//            computList.fetch( { query: {},  
//                onComplete: function(item) {
//                    if(item.length == 1) {
//                        dojo.byId("OfferingListCollection").style.display = "none";
//                        dojo.byId("instancePlanName").innerHTML = translator.common.message.noPlan;
//                        dojo.byId("instancePlanName").className = translator.common.unitCost;
//                        dojo.byId("instancePlanCpu").innerHTML = "";
//                        dojo.byId("instancePlanRam").innerHTML = "";
//                        dojo.byId("instancePlanTempSize").innerHTML = "";
//                        dojo.byId("instancePlanCost").innerHTML = "";
//                        dojo.byId("instancePlanSetupCost").innerHTML = "";                        
//                        dojo.byId("changeplaneRunningCost").innerHTML = "";
//                        dojo.byId("changeplaneSetupCost").innerHTML = "";                        
//                    } else {
//                        dojo.byId("OfferingListCollection").style.display = "block";
//                        dojo.byId("instancePlanName").innerHTML = "";
//                        dojo.byId("instancePlanName").className = "";                        
//                        dojo.byId("changeplaneRunningCost").innerHTML = "";
//                        dojo.byId("changeplaneSetupCost").innerHTML = "";   
//                    }                                  
//                }
//            });         
        var computWidget = new Select({
            id: "innerInstanceComputWidget",          
            store:computList,
            value:firstVal,
            maxHeight: 100,
            onChange: function() {
                    CurrentInstanceInfo.onChangePlanWidget(this);                    
                } 
        }).placeAt("instancePlanList");
        computWidget.startup(); 
        CurrentInstanceInfo.onChangePlanWidget(dijit.byId("innerInstanceComputWidget"));  
    });      
    
    },
    onChangePlanWidget : function(currentWidget) {      
        if(currentWidget.value == "option") {
                    dojo.byId("instancePlanName").innerHTML = "";                    
                    dojo.byId("instancePlanCpu").innerHTML = "";
                    dojo.byId("instancePlanRam").innerHTML = "";
                dojo.byId("vmPlanIODisk").innerHTML =  " " ;
                dojo.byId("instancePlanBandwidth").innerHTML = "";
                dojo.byId("instancePlanNwRate").innerHTML = "";
                    dojo.byId("instancePlanCost").innerHTML = "";
                    dojo.byId("instancePlanSetupCost").innerHTML = "";
                    dojo.byId("changeplaneRunningCost").innerHTML = "";
                    dojo.byId("changeplaneSetupCost").innerHTML = "";
                } else {
                currentWidget.store.fetch({query: {id: currentWidget.value}, onItem: function(item) { 
                    dojo.byId("instancePlanCpu").innerHTML = item.cpuNumber + " "+translator.common.vcpus;    
                    dojo.byId("instancePlanRam").innerHTML = item.memory + translator.common.instance.mbOfMemory + ",";
                    dojo.byId("instancePlanNwRate").innerHTML = item.networkRate + translator.common.MBs;
                    dojo.byId("instancePlanBandwidth").innerHTML = item.bandWidth + translator.common.gb;
                    dojo.byId("vmPlanIODisk").style.fontWeight = "bold";
                    
                    if(item.diskIO === "" || item.diskIO === null) {
                        dojo.byId("vmPlanIODisk").innerHTML = "...";
                    } else {
                        if(item.diskIO.toString() === "Average") {
                            dojo.byId("vmPlanIODisk").style.color = "#f68e1b";
                            dojo.byId("vmPlanIODisk").innerHTML = translator.common.average;
                        } else if(item.diskIO.toString() === "Good") {
                            dojo.byId("vmPlanIODisk").style.color = "#21da6a";
                            dojo.byId("vmPlanIODisk").innerHTML = translator.common.good;
                        } else if(item.diskIO.toString() === "Excellent") {
                            dojo.byId("vmPlanIODisk").style.color = "#21da6a";
                            dojo.byId("vmPlanIODisk").innerHTML = translator.common.excellent;
                        } else {
                            dojo.byId("vmPlanIODisk").style.color = "black";
                            dojo.byId("vmPlanIODisk").innerHTML = "...";
                        }
                        
                    }
                        dojo.byId("instancePlanSetupCost").innerHTML = ""+ item.setupCost; 
                        if(dojo.byId("instanceBilingType").value === "monthly") {
                            dojo.byId("instancePlanCost").innerHTML = ""+  Math.ceil((Number(item.cost) * 720.00) * 100.00) / 100.00 + "/" + translator.common.month;
                            dojo.byId("changeplaneRunningCost").innerHTML = translator.common.cost +"("+ item.currency +"):";
                        } else {
                            dojo.byId("instancePlanCost").innerHTML = ""+ item.cost + "/" + translator.common.hour;
                            dojo.byId("changeplaneRunningCost").innerHTML = translator.common.cost +"("+ item.currency +"):";
                        }
                        dojo.byId("changeplaneSetupCost").innerHTML = translator.common.setupCost + ":";
                    }
                });
            }
    },
    changePlanShow : function() { 
//        var domNode = dojo.byId('changePlanOption'); //domNode to which the tooptip must point to
//        var content = dojo.byId("changePlantooltipContent");         
//        dijit.showTooltip(content.innerHTML, domNode,["after"]);       
         dojo.byId("OfferingListCollection").style.display = "none";
         dojo.byId("currentVMOfferingLoader").style.display = "block";
         var getInstance = setInterval(function(){CurrentInstanceInfo.changeServicePopulateValues(getInstance);},1000);
         dijit.byId("changeServiceDialog").show();
    },
    hideToolTip : function () {        
        var domNode = dojo.byId('changePlanOption');
        dijit.hideTooltip(domNode);
    },
    changePlan : function() { 
        dijit.byId("changeServiceDialog").hide();        
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;     
        var plan = dojo.byId("vmPlanId").value; 
        var computId = dijit.byId("innerInstanceComputWidget").value;
        var changeServiceRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/changeService/"
        });
        dijit.byId('changePlan').set('style', {display: 'none'});
        dojo.byId("changePlaneLoader").style.display = "inline";
        changeServiceRestStore.add({
            virtualMachineReferenceId:currentVmId,
            computingOfferReferenceId: computId
        }).then(function(data) {
            dijit.byId('changePlan').set('style', {display: 'inline'});
            dojo.byId("changePlaneLoader").style.display = "none";
            dojo.forEach(data, function(instanceData) {
                if(instanceData.result == "OK") {
                    dojo.byId("plan").innerHTML = instanceData.plan; 
                    dojo.byId("offerId").value = instanceData.offerReferenceId;
                    registry.byId("userToaster").setContent(translator.common.instance.planChanged,"message");
                    registry.byId("userToaster").show();   
                    dojo.byId('changePlanLoader').style.display = "none";        
                } else if(instanceData.result == "FAILED") {   
                    registry.byId("userToaster").setContent(instanceData.message,"error");
                    registry.byId("userToaster").show();
                    dojo.byId('changePlanLoader').style.display = "none"; 
                } else {   
                    registry.byId("userToaster").setContent(translator.common.instance.planChangedError,"error");
                    registry.byId("userToaster").show();
                    dojo.byId('changePlanLoader').style.display = "none"; 
                }
                
            });
        });
//      dijit.byId("changeServiceDialog").hide();  
    },
    closeChangeServiceDialog: function() {
        dijit.byId("changeServiceDialog").hide();
    },
    
    'showChangeSSHKey' : function() {         
        
        dijit.byId("changeSSHKeyDialog").show();
        
        dojo.byId("sshKeyPlanList").innerHTML = "";
        
        dojo.byId('changeSSHKeyLoader').style.display = "none";   
        dojo.byId('changeSSHKeyBtnDiv').style.display = "block";  
        
        var sshKeyStore = new JsonRest({
            target: core.getContextPath()+"/api/account/SSHKey/list"
        });
                
//        dojo.byId("sshKeyPlanList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait...</p>"; 
                
        var sshKeyOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var sshKeyList = new ItemFileWriteStore({data: sshKeyOptions});
        sshKeyStore.query().then(function(data) {
            
            if(data.length-1 == 0) {
                dojo.byId("sshKeyPlanList").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>"+translator.common.instance.noSSHKey+"</div>";
                
                     dojo.byId('changeSSHKeyBtnDiv').style.display = "none";  
                } else {
            
                dojo.forEach(data, function(el) {
                    if(dojo.byId("sshKeyName").innerHTML != el.name) {
                        sshKeyList.newItem({id: el.name, name: el.name});
                    }
                });

                dojo.byId("sshKeyPlanList").innerHTML = "";

                var sshKeyWidget = new Select({
                    id: "sshKeyWidget",
                    name: "sshKey",      
                    store: sshKeyList
                }, "sshKeyPlanList"); 
            
            }
            
        });           
    },
    'changeSSHKey' :  function() {
        var currentVmId =  dojo.byId("currentInstanceReferenceId").value;  
        
        var changeSSHKeyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/SSHKey/reset"
        });
        dojo.byId('changeSSHKeyLoader').style.display = "block";   
        dojo.byId('changeSSHKeyBtnDiv').style.display = "none";  
        
        changeSSHKeyRestStore.add({
            virtualMachineReferenceId:currentVmId,
            SSHKeyId: dijit.byId('sshKeyWidget').value
        }).then(function(data) {
            
            dojo.forEach(data, function(instanceData) {
                if(instanceData.result == "OK") {                    
                    registry.byId("userToaster").setContent(translator.common.instance.sshKeyChanged,"message");
                    registry.byId("userToaster").show();   
                    dojo.byId('changeSSHKeyBtnDiv').style.display = "block";  
                    dojo.byId('changeSSHKeyLoader').style.display = "none";   
                    dijit.byId("changeSSHKeyDialog").hide();
                    core.router.go("#/user/cloud/userInstancePage");    
                } else if(instanceData.result == "FAILED") {   
                    registry.byId("userToaster").setContent(instanceData.message,"error");
                    registry.byId("userToaster").show();
                    dojo.byId('changeSSHKeyBtnDiv').style.display = "block";  
                    dojo.byId('changeSSHKeyLoader').style.display = "none"; 
                } else {   
                    registry.byId("userToaster").setContent(translator.common.message.failedContactAdmin,"error");
                    registry.byId("userToaster").show();
                    dojo.byId('changeSSHKeyBtnDiv').style.display = "block";  
                    dojo.byId('changeSSHKeyLoader').style.display = "none"; 
                }
                
            });
        });
    },    
    'closeChangeSSHKeyDialog': function() {
        dijit.byId("changeSSHKeyDialog").hide();
    },
    getPassword: function() {
        var currentId =  dojo.byId("currentInstanceReferenceId").value;
        var vmPasswordGet = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/getPassword/"
        });         
        
        dojo.query("#resetPasswordImage").toggleClass("hide_text", true); 
        dojo.query("#showPasswordImage").removeClass("hide_text", true); 
        
        dojo.byId("vmPassword").innerHTML = "";
        dijit.byId("showPasswordDialog").set("title", "");
        
        vmPasswordGet.get(
           currentId
        ).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("vmPasswordField").setValue(resultData.password);
                    dojo.query("#showPasswordInfo").removeClass("hide_text", true); 
                    dojo.query("#passwordResetMsg").toggleClass("hide_text", true); 
                    dojo.query("#resetPassAction").toggleClass("hide_text", true);                    
                    dijit.byId("showPasswordDialog").show();
                } else {
                    dojo.query("#showPasswordInfo").toggleClass("hide_text", true); 
                    dojo.query("#passwordResetMsg").removeClass("hide_text", true); 
                    dojo.query("#resetPassAction").toggleClass("hide_text", true);
                    dojo.byId("vmPassword").innerHTML = translator.common.message.contactAdmin;                                      
                    dijit.byId("showPasswordDialog").show();
                }                      
            });
        });             
    },
    resetPassword: function() {
            dojo.byId("resetPaaLoader").style.display = "inline";
            dojo.byId("resetPasswordTag").style.display = "none";
            var currentId = dojo.byId("currentInstanceReferenceId").value;
            var vmPasswordReset = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/resetPassword/"
            });
            dojo.query("#showPasswordInfo").toggleClass("hide_text", true); 
            dojo.query("#passwordResetMsg").removeClass("hide_text", true); 
            dojo.query("#resetPassAction").removeClass("hide_text", true); 
            
            dojo.query("#resetPasswordImage").removeClass("hide_text", true); 
            dojo.query("#showPasswordImage").toggleClass("hide_text", true); 
            
            dijit.byId("showPasswordDialog").set("title", translator.common.password.resetVMPassword)
            vmPasswordReset.add({
                virtualMachineId : currentId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmPasswordResetJobStatus = setInterval(function(){CurrentInstanceInfo.resetPasswordJob(resultData.jobId, vmPasswordResetJobStatus);},3000);                      
                    } else {                        
                        dojo.byId("vmPassword").innerHTML = resultData.message;
                        dijit.byId("showPasswordDialog").show();
                        registry.byId("userToaster").setContent(resultData.message,"error");
                        registry.byId("userToaster").show();
                        dojo.byId("resetPaaLoader").style.display = "none";
                        dojo.byId("resetPasswordTag").style.display = "inline";
                    }
                });
            });
        },         
        resetPasswordJob: function(jobId, vmPasswordResetJobStatus) {
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/job/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult == "OK") {
                        clearInterval(vmPasswordResetJobStatus);
                        registry.byId("userToaster").setContent(translator.common.message.resetPassworSuccess,"message");
                        registry.byId("userToaster").show();
                        CurrentInstanceInfo.getPassword();
                        dojo.byId('resetPaaLoader').style.display = "none";  
                        dojo.byId('resetPasswordTag').style.display = "inline";                        
                    } else if(resultData.jobResult == "Pending") {
                    } else  if(resultData.jobResult == "FAILED") {
                        clearInterval(vmPasswordResetJobStatus);
                        registry.byId("userToaster").setContent(translator.common.message.refreshPage,"error");
                        registry.byId("userToaster").show();
                        dojo.byId('resetPaaLoader').style.display = "none";
                        dojo.byId('resetPasswordTag').style.display = "inline";  
                    }
                });
            });
        },
    getCurrentInstance : function(data) { 
        var instanceRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/byVMId"
            });
            instanceRestStore.query({vmId: data}).then(function (currentVM) {                              
                dojo.byId("currentInstanceReferenceId").value = currentVM.referenceId;
            })                                                
        },
        deleteInstanceShow :function() {
           dijit.byId("deleteVmDialog").show(); 
        },
        
        deleteInstance: function() {    
            dijit.byId("stop").setAttribute('disabled', true);
            dijit.byId("reboot").setAttribute('disabled', true);
            dijit.byId("startVMButton").setAttribute('disabled', true);
            dojo.byId('status').style.display = "none";
            dojo.byId('vmLoader').style.display = "block";
            var currentId = dojo.byId("currentInstanceReferenceId").value;
            var instanceStopRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/delete/"         
            });
            instanceStopRestStore.add({
                referenceId: currentId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                   if(resultData.result == "OK") {
                       var vmDeleteJobStatus = setInterval(function(){CurrentInstanceInfo.vmDeleteJob(resultData.jobId, vmDeleteJobStatus);},3000);                      
                   } else {   
//                        dojo.byId('vmButtons').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                        registry.byId("userToaster").setContent(translator.common.instance.deleteVMError,"error");
                        registry.byId("userToaster").show();
                   }
               });
            });
            dijit.byId("deleteVmDialog").hide();
        },
        vmDeleteJob: function(jobId, vmDeleteJobStatus) {         
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/job/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult == "OK") {
                        dijit.byId("stop").setAttribute('disabled', false);
                        dijit.byId("reboot").setAttribute('disabled', false);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
                        if(resultData.state == "Running") {
                            dojo.byId("status").innerHTML = translator.common.instance.status.running;
                        } else if(resultData.state == "Stopped"){
                            dojo.byId("status").innerHTML = translator.common.instance.status.stopped;
                        }
                        
                        dojo.byId('status').style.color = "#E0420B";
                        clearInterval(vmDeleteJobStatus);
                        registry.byId("userToaster").setContent(translator.common.instance.vmDeleted,"message");
                        registry.byId("userToaster").show();
                        dojo.byId('status').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                        core.router.go("#/user/cloud/userInstancePage");                      
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else  if(resultData.jobResult == "FAILED"){
                        clearInterval(vmDeleteJobStatus);
                        dijit.byId("stop").setAttribute('disabled', false);
                        dijit.byId("reboot").setAttribute('disabled', false);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
                        registry.byId("userToaster").setContent(translator.common.message.refreshPage,"error");
                        registry.byId("userToaster").show();
//                        dojo.byId('vmButtons').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                    }
                });
            });
        },
        closeDeleteDialog: function() {
            dijit.byId("deleteVmDialog").hide();
        },
        startVMConfirm : function() {
            dijit.byId("vmInfoStartDialog").show();
        },
        cancelStartStart : function () {
            dijit.byId("vmInfoStartDialog").hide();
        },
        startInstance: function() {
            dijit.byId("stop").setAttribute('disabled', true);
            dijit.byId("reboot").setAttribute('disabled', true);
            dijit.byId("startVMButton").setAttribute('disabled', true);
           dojo.byId('status').style.display = "none";
           dojo.byId('vmLoader').style.display = "block";
            var currentId =  dojo.byId("currentInstanceReferenceId").value;
            var instanceStartRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/start/"         
            });
            instanceStartRestStore.add({
                referenceId:  currentId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                   if(resultData.result == "OK") {
                       var vmStartJobStatus = setInterval(function(){CurrentInstanceInfo.vmStartJob(resultData.jobId, vmStartJobStatus);},3000);                      
                   } else {  
                        dijit.byId("stop").setAttribute('disabled', true);
                        dijit.byId("reboot").setAttribute('disabled', false);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
                        dojo.byId('status').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                       registry.byId("userToaster").setContent(translator.common.instance.startVMError,"error");
                       registry.byId("userToaster").show();
                   }
               });
               dijit.byId("vmInfoStartDialog").hide();
            });
        },        
        vmStartJob: function(jobId, vmStartJobStatus) {
         
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/job/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult == "OK") {
                        clearInterval(vmStartJobStatus);
                        dojo.byId('vmLoader').style.display = "none";
                        dijit.byId("stop").setAttribute('disabled', false);
                        dijit.byId("reboot").setAttribute('disabled', false);
                        dijit.byId("startVMButton").setAttribute('disabled', true);
                        dojo.byId('status').style.display = "block";

                        if(resultData.state == "Running") {
                            dojo.byId("status").innerHTML = translator.common.instance.status.running;
                            
                            dojo.query("#consoleDiv").toggleClass("console-editor", true); 
                            dojo.query("#consoleDiv").removeClass("console-editor-disable", true); 
                            dijit.byId("start").setAttribute('disabled', true);
                            dojo.byId('status').style.color = "#387C2C"; 
                            dojo.byId("viewCosoleImg").onclick = function () {
                                CurrentInstanceInfo.viewConsole()
                            }
                            dojo.byId("viewCosoleImg").style.cursor = "pointer"
                        } else if(resultData.state == "Stopped"){
                            dojo.byId("status").innerHTML = translator.common.instance.status.stopped;
                            
                            dojo.query("#consoleDiv").removeClass("console-editor", true); 
                            dojo.query("#consoleDiv").toggleClass("console-editor-disable", true); 
                            dijit.byId("stop").setAttribute('disabled', true);
                            dojo.byId('status').style.color = "#E0420B"; 
                            dojo.byId("viewCosoleImg").onclick = function () {                        
                            }
                            dojo.byId("viewCosoleImg").style.cursor = "auto";
                        }
                        
                        dojo.byId('status').style.color = "#387C2C";
                        registry.byId("userToaster").setContent(translator.common.instance.vmStarted,"message");
                        registry.byId("userToaster").show();
//                        dojo.byId('vmButtons').style.display = "block";   
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else  if(resultData.jobResult == "FAILED") {
                        clearInterval(vmStartJobStatus);
                        dijit.byId("stop").setAttribute('disabled', true);
                        dijit.byId("reboot").setAttribute('disabled', false);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
                        registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                        registry.byId("userToaster").show();
                        dojo.byId('status').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                    }
                });
            });
        },
        
        stopInstanceShow :function() {
           dijit.byId("stopVmDialog").show(); 
        },        
        stopInstance: function() {    
            dijit.byId("stop").setAttribute('disabled', true);
            dijit.byId("reboot").setAttribute('disabled', true);
            dijit.byId("startVMButton").setAttribute('disabled', true);
            dojo.byId('status').style.display = "none";
            dojo.byId('vmLoader').style.display = "block";
            var currentId = dojo.byId("currentInstanceReferenceId").value;
            var instanceStopRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/stop/"         
            });
            var force = dijit.byId("vmForce").checked;
            instanceStopRestStore.add({
                referenceId: currentId,
                forced: force
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                   if(resultData.result == "OK") {
                       var vmStopJobStatus = setInterval(function(){CurrentInstanceInfo.vmStopJob(resultData.jobId, vmStopJobStatus);},3000);                      
                   } else {   
                        dijit.byId("stop").setAttribute('disabled', true);
                        dijit.byId("reboot").setAttribute('disabled', true);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
//                        dojo.byId('vmButtons').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                       registry.byId("userToaster").setContent(translator.common.instance.stopVMError,"error");
                       registry.byId("userToaster").show();
                   }
               });
            });
            dijit.byId("stopVmDialog").hide();
        },
        vmStopJob: function(jobId, vmStopJobStatus) {
         
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/job/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult == "OK") {                      
                        dijit.byId("stop").setAttribute('disabled', true);
                        dijit.byId("reboot").setAttribute('disabled', true);
                        dijit.byId("startVMButton").setAttribute('disabled', false);

                        if(resultData.state == "Running") {
                            dojo.byId("status").innerHTML = translator.common.instance.status.running;
                            
                            dojo.query("#consoleDiv").toggleClass("console-editor", true); 
                            dojo.query("#consoleDiv").removeClass("console-editor-disable", true); 
                            dijit.byId("start").setAttribute('disabled', true);
                            dojo.byId('status').style.color = "#387C2C"; 
                            dojo.byId("viewCosoleImg").onclick = function () {
                                CurrentInstanceInfo.viewConsole()
                            }
                            dojo.byId("viewCosoleImg").style.cursor = "pointer"
                            
                        } else if(resultData.state == "Stopped") {
                            dojo.byId("status").innerHTML = translator.common.instance.status.stopped;
                            
                            dojo.query("#consoleDiv").removeClass("console-editor", true); 
                            dojo.query("#consoleDiv").toggleClass("console-editor-disable", true); 
                            dijit.byId("stop").setAttribute('disabled', true);
                            dojo.byId('status').style.color = "#E0420B"; 
                            dojo.byId("viewCosoleImg").onclick = function () {                        
                            }
                            dojo.byId("viewCosoleImg").style.cursor = "auto";
                        }                                                
                        dojo.byId('status').style.color = "#E0420B";
                        clearInterval(vmStopJobStatus);
                        registry.byId("userToaster").setContent(translator.common.instance.vmStopped,"message");
                        registry.byId("userToaster").show();
                        dojo.byId('status').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";                                                                                             
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else  if(resultData.jobResult == "FAILED"){
                        clearInterval(vmStopJobStatus);
                        dijit.byId("stop").setAttribute('disabled', true);
                        dijit.byId("reboot").setAttribute('disabled', true);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
                        registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                        registry.byId("userToaster").show();
//                        dojo.byId('vmButtons').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                    }
                });
            });
        },
        closeStopDialog: function() {
            dijit.byId("stopVmDialog").hide();
        },
        rebootInstanceConfirm : function () {        
            dijit.byId("vmInfoRebootDialog").show();
        },
        closeReboot : function () {
           dijit.byId("vmInfoRebootDialog").hide();
        },
        rebootInstance: function() {
            dijit.byId("vmInfoRebootDialog").hide();
            dojo.byId('status').style.display = "none";
            dijit.byId("stop").setAttribute('disabled', true);
            dijit.byId("reboot").setAttribute('disabled', true);
            dijit.byId("startVMButton").setAttribute('disabled', true);
//           dojo.byId('vmButtons').style.display = "none";
           dojo.byId('vmLoader').style.display = "block";
            var currentId =  dojo.byId("currentInstanceReferenceId").value;
            var instanceStartRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/reboot/"         
            });
            instanceStartRestStore.add({
                referenceId:  currentId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                   if(resultData.result == "OK") {
                       var vmRebootJobStatus = setInterval(function(){CurrentInstanceInfo.vmRebootJob(resultData.jobId, vmRebootJobStatus);},3000);                      
                   } else {
                       dijit.byId("stop").setAttribute('disabled', false);
                        dijit.byId("reboot").setAttribute('disabled', true);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
//                        dojo.byId('vmButtons').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                        dojo.byId('status').style.display = "block";
                        registry.byId("userToaster").setContent(translator.common.instance.rebootVMError,"error");
                        registry.byId("userToaster").show();
                   }
               });
            });
        },
        vmRebootJob: function(jobId, vmStartJobStatus) {
         
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/job/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult == "OK") {
                        dijit.byId("stop").setAttribute('disabled', true);
                        dijit.byId("reboot").setAttribute('disabled', false);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
                        if(resultData.state == "Running") {
                            dojo.byId("status").innerHTML = translator.common.instance.status.running;
                        } else if(resultData.state == "Stopped"){
                            dojo.byId("status").innerHTML = translator.common.instance.status.stopped;
                        }
                        
                        dojo.byId('status').style.color = "#387C2C";
                        clearInterval(vmStartJobStatus);
                        registry.byId("userToaster").setContent(translator.common.instance.vmRebooted,"message");
                        registry.byId("userToaster").show();
//                        dojo.byId('vmButtons').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                        dojo.byId('status').style.display = "block";
                                              
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else  if(resultData.jobResult == "FAILED"){
                        clearInterval(vmStartJobStatus);
                        dijit.byId("stop").setAttribute('disabled', false);
                        dijit.byId("reboot").setAttribute('disabled', true);
                        dijit.byId("startVMButton").setAttribute('disabled', false);
                        registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                        registry.byId("userToaster").show();
//                        dojo.byId('vmButtons').style.display = "block";
                        dojo.byId('vmLoader').style.display = "none";
                        dojo.byId('status').style.display = "block";
                    }
                });
            });
        },        
        getStartConformation: function() {           
        },
    getRebootConformation : function() {
        dijit.byId("instanceRebootDialog").show();
    },
    showCpuPage : function() {
          dijit.byId("cpuDialog").show();  
        },
        showRamPage : function() {
            dijit.byId("ramDialog").show();  
        },
        showIsoPage : function() {
            dijit.byId("isoDialog").show();  
        },
        showIpPage : function() {
            dijit.byId("ipDialog").show();  
        },
        storageDialog : function() {
            dijit.byId("storageDialog").show();  
            CurrentInstanceInfo.showDiskDetails();
            
        },
        showDiskDetails : function() {
//            var currentId = UserInstances.getGridData();
//            
//            this._volumeRestStore.query().then(function(data) {
//                dojo.forEach(data, function(el) {
//                    alert(el.name);
//                })
//
//            })
            if(dijit.byId("storageGrid")) {
                dijit.byId("storageGrid").destroyRecursive();
            }
            
            var billingData = {
                items: [{id: "192.1.2.1", plan:"Small", 
                        diskSize: "2", storageType: "custom",
                         status:"yes", snapShot:"yes", action: "test"},
                        {id: "192.1.2.1", plan:"Small", 
                        diskSize: "2", storageType: "custom",
                         status:"yes", snapShot:"yes", action: "test"},
                    {id: "192.1.2.1", plan:"Small", 
                        diskSize: "2", storageType: "custom",
                        status:"yes", snapShot:"yes", action: "test"},
                    {id: "192.1.2.1", plan:"Small", 
                        diskSize: "2", storageType: "custom",
                        status:"yes", snapShot:"yes", action: "test"}
                    
                ]
            };
            var billingDataList = new ItemFileWriteStore({data: billingData});
           
           
           var billingLayout = [[
                     {'name': 'ID', 'field': 'id',  'hidden' : 'true'},
                     {'name': 'Plan', 'field': 'plan', 'width': '100px'},
                     {'name': 'Disk Size', 'field': 'diskSize', 'width': '150px'},
                     {'name': 'Storage Type', 'field': 'storageType', 'width': '150px'},
                    
                     {'name': 'Status', 'field': 'status', 'width': '50px'},
                     {'name': 'Snap Shot', 'field': 'snapShot', 'width': '150px'},
                     {'name': 'Action', 'field': 'action', 'width': '250px','formatter': function(value, index){
                          var node = dojo.create("div");
                         
                         var detachButton = new dijit.form.Button({
                              label: "Detach",
                              onClick: CurrentInstanceInfo.detach()
                          });
                          
                          var deleteButton = new dijit.form.Button({
                              label: "Delete",
                              onClick : function() {
//                                  alert("Delete");
                              }
                          });
                          var snapShotButton = new dijit.form.Button({
                              label: "Snapshot",
                              onClick : function() {
//                                  alert("snapshot");
                              }
                          });
                          
                          node.appendChild(detachButton.domNode);
                         node.appendChild(deleteButton.domNode);
                         
                          node.appendChild(snapShotButton.domNode);
                       
                       return node.innerHTML;
                                                     
                     }}
                     
                     
                     
                     
            ]
            ];
             
              this.userGrid = new DataGrid({
                 id: 'storageGrid',
                 store: billingDataList,
                 structure: billingLayout,
                 height:  '250px'
             });
             this.userGrid.placeAt("userStorageGrid");
             this.userGrid.startup();
        },
        bandwidthDialog : function() {
            dijit.byId("bandwidthDialog").show();  
        },
        snapShotDialog : function() {
            dijit.byId("snapShotDialog").show();  
        },
        securityGroupsDialog : function () {
            dijit.byId("securityGroupsDialog").show();  
        }
    
};
var AddStorageInfo = {
    _zoneRestStore: "",
    _diskOfferRestStore : "",
    _volumeRestStore:"",
    _virtualMachineRestStore : "",
    _storageGrid:"",
    _zoneWidget:"",
    _diskWidget:"",
    _snapShotRestStore:"",
    _volumeId:"",
    _currencyRestStore:"",
    init : function() {        
        var configRestStoreBilling = new JsonRest({
            target: core.getContextPath()+"/api/config/"
        });
                   
        configRestStoreBilling.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) { 
                if(config.name == "monthly.billing.enabled") {
                    if(config.value == "true" || config.value == true) {
                        dojo.byId("add-disk-billingTypeDiv").style.display = "block";
                    } else {
                        dojo.byId("add-disk-billingTypeDiv").style.display = "none";
                    }
                } 
            });
        });
        
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        });                                                

        this._volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        });     

        this._virtualMachineRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });  
        
        this._currencyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        
        var vmCount = 0;
        
        this._virtualMachineRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {     
                if(el.state =="Running" || el.state == "Stopped") {
                   vmCount++;  
                }
            });
                        
            if(vmCount == 0) {
                core.router.go("#/user/cloud/userInstancePage");
                registry.byId("userToaster").setContent(translator.user.storage.createVMAddVolume,"error");
                registry.byId("userToaster").show();
            } else {
                var accountResourceLimitStore = new JsonRest({
                        target: core.getContextPath()+"/api/account/resourceLimit"
                });

                accountResourceLimitStore.query().then(function(data) {
                     dojo.forEach(data, function(limitData) {  
                         if(limitData.accountType == "TRIAL") {
                            if((limitData.volumeCount >= limitData.storageLimitNo)) {
                                    core.router.go("#/user/cloud/storage");
                                    registry.byId("userToaster").setContent(translator.user.storage.volumeLimitReached,"error");
                                    registry.byId("userToaster").show();
                             } else {
                                
                             }
                         }
                     });
                 });
            } 
        });
        
    },
    populateValue: function() {    
        var diskOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id:'option', name:translator.common.message.selectDisk}]
        };
        var diskList = new ItemFileWriteStore({data: diskOptions}); 
        var diskSlider = new Select({
              id: "storageDiskWidget",          
              store:diskList,
              maxHeight: 100,
              sortByLabel: false,
            onChange: function() {
                AddStorageInfo.getDisk(this);
                } 
            }).placeAt("storageDiskOffer");
            diskSlider.startup();
        this._currencyRestStore.query().then(function(currency) {
            dojo.forEach(currency, function(el) {                
                dojo.byId("addDiskCurrecy").innerHTML = el.currency;
            })            
        });
        var customDiskSlider = new dijit.form.HorizontalSlider({
            name: "slider",                  
            style: "width:50%;", 
            container:'bottomDecoration',
            intermediateChanges: true,
            disabled: "true",
            onChange : function(value) {
                console.log("slider")
                value = value | 0;                
                dojo.byId("storageDiskSize").innerHTML = dijit.byId("storageDiskSizeSpinner").value + translator.common.gb;                                
                var costItem = dojo.byId("storageDiskCost").innerHTML;                         
                dojo.byId("storageMonthlyCost").innerHTML = Math.ceil((dijit.byId("storageDiskSizeSpinner").value * Number(costItem) * 720.00) * 100.00) / 100.00 + " /" + translator.common.month;                                               
                dijit.byId("storageDiskSizeSpinner").setValue(value);       
            }
        },"storageCustomDiskSizeSlider");
        customDiskSlider.startup();                  
        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        }; 
        var instanceList = new ItemFileWriteStore({data: instanceOptions});
        var virtualMachineRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/byDisk"
        }); 
        var virtualMachineByZoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/count"
        }); 
             
        var currentZoneID = dojo.byId("selectedANZoneID").value;
        if(currentZoneID === "All" || currentZoneID === "" || currentZoneID === "undefined") {
            virtualMachineRestStore.query().then(function(data) {
                dojo.forEach(data, function(el) { 
                    if((el.state =="Running" || el.state == "Stopped") && el.hasVMSnapshot == "false") {
                        instanceList.newItem({                    
                            id: el.referenceId,
                            name: el.name, 
                            cluster:el.computingOfferClusterId,
                            type: el.computingOfferTags,
                            storageType: el.computingOfferType,
                            zoneId: el.zoneReferenceId
                        }); 
                    }
                });
            });
        } else {
            virtualMachineByZoneRestStore.query({zoneReferenceId: currentZoneID}).then(function(data) {
                dojo.forEach(data[0].instanceData, function(el) {                     
                    if((el.state =="Running" || el.state == "Stopped") && el.hasVMSnapshot == "false") {                       
                        instanceList.newItem({                    
                            id: el.referenceId,
                            name: el.name, 
                            cluster:el.computingOfferClusterId,
                            type: el.computingOfferTags,
                            storageType: el.computingOfferType,
                            zoneId: el.zoneReferenceId
                        }); 
                    }
                });
            });
        }
        var instanceWidget = new FilteringSelect({
            id: "storageVmWidget",  
            store : instanceList,
            placeHolder:translator.common.message.selectInstance,
            maxHeight: 100,
            onChange : function() {                           
                var clusterId;
                var type;        
                var storageType;
                dojo.byId("diskListContainer").style.display = "none";
                dojo.byId("storageLoader").style.display = "block";
            
                var diskData = {
                    identifier: 'id',
                    label: 'name',
                    items: []         
                };
            
                var diskDataList = new ItemFileWriteStore({data: diskData});
                var zoneId = "";
                instanceList.fetch({query: {id: this.value},
                    onItem: function(item) {                    
                        if(item.id != "option") {                      
                            dojo.forEach(item.cluster, function(el) {
                                clusterId = el;
                            });
                            dojo.forEach(item.type, function(el) {
                                type = el;
                            });                            
                            dojo.forEach(item.storageType, function (el) {
                                storageType = el;
                            });
                            dojo.byId("addDiskInfo").style.display = "block";     
                            zoneId = item.zoneId;
                        } else {
                            dijit.byId("storageDiskWidget").store.newItem({id:"option",name: translator.common.message.noDisk});
                            dijit.byId("storageDiskWidget").setStore(diskDataList);
                            dijit.byId("storageCustomDiskSizeSlider").set("disabled", true); 
                            dijit.byId("storageCustomDisk").set("disabled", true); 
                            dojo.byId("addDiskInfo").style.display = "none";                         
                        }                  
                    }
                });
                if(this.value == "option") {
                    dijit.byId("storageDiskWidget").store.newItem({id:"option",name: translator.common.message.noDisk});
                    dijit.byId("storageDiskWidget").setStore(diskDataList);
                    dijit.byId("storageCustomDiskSizeSlider").set("disabled", true); 
                    dijit.byId("storageCustomDisk").set("disabled", true); 

                    dojo.byId("storageLoader").style.display = "none";
                    dojo.byId("diskListContainer").style.display = "block";
                    dijit.byId("volumeAdd").set("disabled", true);
                } else {
                    dijit.byId("storageCustomDiskSizeSlider").set("disabled", false);     
                    dijit.byId("storageCustomDisk").set("disabled", false); 
                    dijit.byId("volumeAdd").set("disabled", false);
                    var customWidget = dijit.byId("storageCustomDisk").checked;
                    var diskOfferRestStore = new JsonRest({
                        target: core.getContextPath()+"/api/diskOffer/byComputingOffer"
                    });        
                    diskOfferRestStore.query({clusterReferenceId:clusterId, tags: type, customized:customWidget, storageType: storageType}).then(function(data) {       
                        if(data == "" || data == "") {                     
                            dojo.byId("storageDiskName").innerHTML  = translator.common.message.noDisk;
                            dojo.byId("addDiskInfo").style.display = "none";
                            dojo.query("#storageDiskOfferContent .updatable").toggleClass("non_updatable", true);
                            dojo.byId("storageLoader").style.display = "none";
                            dojo.byId("diskListContainer").style.display = "none";
                        } else if(data) { 
                            var firstDisk = data[0].diskOfferReferenceId;
                            dojo.byId("storageLoader").style.display = "none";
                            dojo.byId("diskListContainer").style.display = "block";
                            
                            dojo.byId("diskListContainer").style.display = "block";
                            dijit.byId("storageCustomDiskSizeSlider").set("minimum", 1);
                            dijit.byId("storageCustomDiskSizeSlider").set("discreteValues", data[0].maxSize);
                            dijit.byId("storageCustomDiskSizeSlider").set("maximum", data[0].maxSize);
                            dijit.byId("storageCustomDiskSizeSlider").set("value",1);
                            dojo.byId("addDiskInfo").style.display = "block";
                            dojo.byId("storageDiskSize").innerHTML = data[0].size + translator.common.gb;  
                            dojo.query("#storageDiskOfferContent .updatable").removeClass("non_updatable", true);                    
                            dojo.byId("storageDiskName").innerHTML = "";
                            var cost = 0.00;
                            var minCost = 0.00;       
                            
                            dojo.forEach(data, function(el) {                                
                                dojo.forEach(el.diskOfferZoneCosts, function (zoneCost) {
                                    if(zoneId == zoneCost.zone.referenceZoneId) {                                        
                                        cost = zoneCost.cost;
                                        minCost = zoneCost.minimumCost;
                                    }                    
                                });              
                                
                                diskDataList.newItem({
                                    id:el.diskOfferReferenceId,
                                    name: el.name,
                                    size:el.size,
                                    type:el.tags,
                                    cost: cost.toString(),
                                    minCost: minCost.toString(),
                                    minSize:el.minSize,
                                    maxSize: el.maxSize,
                                    custom: el.customized
                                });                    
                            });
                            
                            dijit.byId("storageDiskWidget").setStore(diskDataList);                            
                            dijit.byId("storageDiskWidget").set("value" , firstDisk);                                                        
                            
                            dojo.byId("storageDiskCost").innerHTML = ""+ localeCurrency.format(Number(cost), {places: 5, locale: dojo.locale})   + " ";                                                                                                                                  
                            dojo.byId("storageMonthlyCost").innerHTML = localeCurrency.format(Math.ceil((data[0].size * Number(cost) * 720.00) * 100.00) / 100.00, {places: 5, locale: dojo.locale})  + " /" + translator.common.month;                                                     
                            
                            if(customWidget == false) {                           
                                dojo.byId("storageDiskSizeContent").style.display = "none";                                  
                            } else if(customWidget == true || customWidget == "true") { 
                                dojo.byId("storageDiskSizeContent").style.display = "block";
                            }
                        }                       
                    }); 
                }                             
            }
        }).placeAt("storageInstance");
        instanceWidget.startup();
    },
    enableMonthlyCost: function() {
        var formElements = dojo.query("#add-disk-billingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        
        if(billingType == "monthly") {            
            dojo.byId("addDiskMonthly").style.display = "block";                        
        } else {
            dojo.byId("addDiskMonthly").style.display = "none";            
        }               
    },
        getDisk : function(currentDisk) {            
            var customWidget = dijit.byId("storageCustomDisk").checked;           
            var maxSize;    
            if(currentDisk.value == "option") {            
                dojo.byId("addDiskInfo").style.display = "none";
                dojo.byId("storageDiskSizeContent").style.display = "none";  
                dijit.byId("volumeAdd").set("disabled", true);
            } else {
                dijit.byId("volumeAdd").set("disabled", false);
               dojo.byId("addDiskInfo").style.display = "block"; 
               currentDisk.store.fetch({query: {id: currentDisk.value}, onItem: function(item) {
                       if(item) { 
                           dojo.forEach(item.maxSize, function(el) {                    
                               maxSize = el;
                           });  
                           dojo.byId("storageDiskCost").innerHTML = "" + localeCurrency.format(Number(item.cost), {places: 5, locale: dojo.locale}) + " ";
                           if(item.custom == true || item.custom == "true") {
                               dojo.byId("storageDiskSize").innerHTML = dijit.byId("storageDiskSizeSpinner").value + translator.common.gb;       
                               var monthCost = Math.ceil((dijit.byId("storageDiskSizeSpinner").value * Number(item.cost) * 720.00) * 100.00) / 100.00;                               
                               dojo.byId("storageMonthlyCost").innerHTML = localeCurrency.format(monthCost, {places: 2, locale: dojo.locale})  + " /" + translator.common.month;                         
                            } else if(item.custom == false || item.custom == "false") {
                                dojo.byId("storageDiskSize").innerHTML = item.size + translator.common.gb;    
                                var monthCost = Math.ceil((Number(item.size) * 720.00 * Number(item.cost)) * 100.00) / 100.00;
                                dojo.byId("storageMonthlyCost").innerHTML = localeCurrency.format(monthCost, {places: 2, locale: dojo.locale}) + " /" + translator.common.month; 
                            }                 
                        }          
                    }
                }); 
                if(customWidget == true || customWidget == "true") {
                    dijit.byId("storageCustomDiskSizeSlider").set("minimum", 1);
                    dijit.byId("storageCustomDiskSizeSlider").set("discreteValues", maxSize);
                    dijit.byId("storageCustomDiskSizeSlider").set("maximum", maxSize);
                    dijit.byId("storageCustomDiskSizeSlider").set("value", 1);
                    dijit.byId("storageDiskSizeSpinner").attr("constraints", {max: maxSize, min: 1}); 
                    dijit.byId("storageDiskSizeSpinner").setValue(1);
                    dojo.byId("storageDiskSizeContent").style.display = "block";
                } else if(customWidget == false || customWidget == "false") {
                    dojo.byId("storageDiskSizeContent").style.display = "none";    
                }  
            }                      
    },
    setSliderSize : function(currentSpinner) {            
        dijit.byId("storageCustomDiskSizeSlider").set("value", currentSpinner.value);
    },
    showCustomDisk : function(customCheck) {         
        var clusterId;
        var type;
        var storageType;
        var diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/byComputingOffer"
        });        

        var diskData = {
            identifier: 'id',
            label: 'name',
            items: []
        };        
        var diskDataList = new ItemFileWriteStore({data: diskData});
        var vmWidget = dijit.byId("storageVmWidget");
        var diskWidget = dijit.byId("storageDiskWidget");
        var zoneId = "";
        vmWidget.store.fetch({query: {id: vmWidget.value},
        
        onItem: function(item) {
            if(item) { 
                zoneId = item.zoneId;
                dojo.forEach(item.type, function(el) {
                    type = el;
                });                
                dojo.forEach(item.cluster, function(el) {                    
                    clusterId = el;
                });

                dojo.forEach(item.storageType, function (el) {
                    storageType = el;
                });
            }                     
        }
    });               
    diskOfferRestStore.query({clusterReferenceId:clusterId,tags: type, customized:customCheck.checked, storageType: storageType}).then(function(data) {        
        if(data == "" || data == "" || data == undefined || data == "undefined") {      
            dojo.byId("storageDiskName").innerHTML = translator.common.message.noDisk;
            dojo.byId("diskListContainer").style.display = "none";           
            dojo.byId("storageDiskSizeContent").style.display = "none";            
            dojo.byId("addDiskInfo").style.display = "none";         
            var noDiskData = {
                identifier: 'id',
                label: 'name',
                items: []
            };        
            var noDiskDataList = new ItemFileWriteStore({data: noDiskData});
             noDiskDataList.newItem({id:"option", name: translator.common.message.noDisk});
             dijit.byId("storageDiskWidget").setStore(noDiskDataList);             
         } else if(data) {            
             var cost = 0.00;
             var minCost = 0.00; 
             dojo.forEach(data, function(el) {                 
                 dojo.forEach(el.diskOfferZoneCosts, function (zoneCost) {
                     if(zoneId == zoneCost.zone.referenceZoneId) {
                        cost = zoneCost.cost;
                        minCost = zoneCost.minimumCost;
                    }                    
                });                
                diskDataList.newItem({
                    id:el.diskOfferReferenceId,
                    name: el.name,
                    size:el.size,
                    type:el.tags,
                    cost:cost.toString(),
                    minCost:minCost.toString(),
                    minSize:el.minSize,
                    maxSize: el.maxSize,
                    custom: el.customized,
                    diskWrite : el.diskWriteRateIOPS,
                    diskRead : el.diskReadRateIOPS
                });                    
            });
            diskWidget.setStore(diskDataList);
            
            var min =  Number(data[0].minSize);
            var max = Number(data[0].maxSize);                        
            var discretValue = max - min;  
            dojo.byId("storageDiskName").innerHTML = "";
            dojo.byId("diskListContainer").style.display = "block";                              
            dojo.byId("addDiskInfo").style.display = "block";                        
            dojo.byId("storageDiskCost").innerHTML = localeCurrency.format(Number(cost), {places: 5, locale: dojo.locale});            
            dojo.byId("storageMonthlyCost").innerHTML = localeCurrency.format(Math.ceil((1* Number(cost) * 720.00) * 100.00) / 100.00, {places: 2, locale: dojo.locale}) + " /" + translator.common.month;
            if(customCheck.checked == true || customCheck.checked == "true") {
                dojo.byId("storageDiskSizeContent").style.display = "block";                
                dijit.byId("storageDiskSizeSpinner").attr("constraints", {max: max, min: min}); 
                dijit.byId("storageDiskSizeSpinner").setValue(min);               
                dijit.byId("storageCustomDiskSizeSlider").set("minimum", min);
                dijit.byId("storageCustomDiskSizeSlider").set("discreteValues", discretValue);
                dijit.byId("storageCustomDiskSizeSlider").set("maximum", max); 
                dijit.byId("storageCustomDiskSizeSlider").set("value", min);   
            } else if(customCheck.checked == "false" || customCheck.checked == false) {
                dojo.byId("storageDiskSizeContent").style.display = "none";        
            }
            AddStorageInfo.getDisk(diskWidget);
        }     
    }); 
    },
    createVolume : function() {        
        var name = dijit.byId("addStorageName");
        var diskOffer =  dijit.byId("storageDiskWidget").value;
        var size = "";
        
        var volumeStore = new JsonRest({
            target: core.getContextPath()+"/api/volume"
        });
        var customCheck = dijit.byId("storageCustomDisk");
        if(customCheck.checked == true || customCheck.checked == "true") {
            size = dijit.byId("storageDiskSizeSpinner").value.toString();
        } else {
            if(dijit.byId("storageDiskWidget").value != "option") {
                dijit.byId("storageDiskWidget").store.fetch({query: {id: diskOffer}, onItem: function(item) {
                        if(item) { 
                            size = item.size.toString();                 
                        }          
                    }
                })
            }            
        }
        
        var formElements = dojo.query("#add-disk-billingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        
        var node = dojo.byId("storageAddDiskForm");
        var widget = dijit.registry.findWidgets(node);        
        var firstNode = "";
        var status = true;
        dojo.forEach(widget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
                status = false
            }
        });
        if(diskOffer == "option" || dijit.byId("storageVmWidget").value == "option") {
            dojo.byId("addStorageRequireField").style.display = "block";
            dijit.byId("volumeAdd").set("disabled", true);
        } else if(status == true) {
            dojo.byId("addStorageRequireField").style.display = "none";
            dijit.byId("addStorageName").set("disabled", true);
            dijit.byId("storageVmWidget").set("disabled", true);
            dijit.byId("storageCustomDisk").set("disabled", true);
            dijit.byId("storageDiskWidget").set("disabled", true);
            
            
            dijit.byId("add-disk-hourlyBilling").set("disabled", true);
            dijit.byId("add-disk-monthlyBilling").set("disabled", true);
            
            dijit.byId("storageDiskSizeSpinner").set("disabled", true);
            dijit.byId("storageCustomDiskSizeSlider").set("disabled", true);
            
            dijit.byId("volumeAdd").set("disabled", true);
            dojo.byId("addStorageLoader").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.user.loader.creatingVolume+"</p>";
        volumeStore.add({
            name : name.value,
            diskOfferReferenceId : diskOffer,
            customDiskSize : size,
            billingType: billingType,
            instanceId:dijit.byId("storageVmWidget").value
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var createVolumeStatus = setInterval(function(){AddStorageInfo.volumeJobCreate(resultData.jobId, createVolumeStatus);},3000);                                          
//                    dojo.byId("storageDiskSizeContent").style.display = "none";                                                                                
                } else {                                      
                    registry.byId("userToaster").setContent(translator.user.storage.addVolumeError,"error");
                    registry.byId("userToaster").show();
                    
                    dojo.byId("addStorageLoader").innerHTML = " ";
                    
                    dijit.byId("addStorageName").set("disabled", false);
                    dijit.byId("storageVmWidget").set("disabled", false);
                    dijit.byId("storageCustomDisk").set("disabled", false);
                    dijit.byId("storageDiskWidget").set("disabled", false);
                    
                    dijit.byId("add-disk-hourlyBilling").set("disabled", false);
                    dijit.byId("add-disk-monthlyBilling").set("disabled", false);
                    
                    dijit.byId("storageDiskSizeSpinner").set("disabled", false);
                    dijit.byId("storageCustomDiskSizeSlider").set("disabled", false);
                    dijit.byId("volumeAdd").set("disabled", false);
                }
            });
        });
        }
        
    },
    volumeJobCreate : function(jobId, jobStatus) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {                
                    if(resultData.state == "Allcocated") {
                        clearInterval(jobStatus);
                        AddStorageInfo.deleteVolume(resultData.referenceId);                              
                    } else {
                        clearInterval(jobStatus);
                        AddStorageInfo.attachVolume(resultData.referenceId);                        
                    }                    
                } else if(resultData.jobResult == "Pending") {
                } else {
                    clearInterval(jobStatus);
                    registry.byId("userToaster").setContent(resultData.message,"error");
                    registry.byId("userToaster").show();                    
                    dojo.byId("addStorageLoader").innerHTML = " ";    
                    
                    dijit.byId("add-disk-monthlyBilling").set("disabled", false);
                    dijit.byId("add-disk-hourlyBilling").set("disabled", false);
                    dijit.byId("addStorageName").set("disabled", false);
                    dijit.byId("storageVmWidget").set("disabled", false);
                    dijit.byId("storageCustomDisk").set("disabled", false);
                    dijit.byId("storageDiskWidget").set("disabled", false);                    
                    dijit.byId("storageDiskSizeSpinner").set("disabled", false);
                    dijit.byId("storageCustomDiskSizeSlider").set("disabled", false);                    
                    dijit.byId("volumeAdd").set("disabled", false);
                }
            });
        });
    },    
    deleteVolume : function(volumeId) {
        var volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        }); 
        volumeRestStore.remove(volumeId).then(function(deleteResult) {
            if(deleteResult == "OK") {
                registry.byId("userToaster").setContent("Volume Deleted","message");
                registry.byId("userToaster").show();
            } else if(deleteResult == "FAILED") {
                registry.byId("userToaster").setContent(translator.user.storage.volumeDeleted,"error");
                registry.byId("userToaster").show();
            } else if (deleteResult == "volume") {
                registry.byId("userToaster").setContent(translator.user.storage.notAttached,"error");
                registry.byId("userToaster").show();
            }
            dojo.byId("addStorageLoader").innerHTML = " ";                    
            dijit.byId("addStorageName").set("disabled", false);
            dijit.byId("storageVmWidget").set("disabled", false);
            dijit.byId("storageCustomDisk").set("disabled", false);
            dijit.byId("storageDiskWidget").set("disabled", false);
            
            dijit.byId("storageDiskSizeSpinner").set("disabled", false);
            dijit.byId("storageCustomDiskSizeSlider").set("disabled", false);
            dijit.byId("volumeAdd").set("disabled", false);
        });  
    },
    attachVolume: function(volumeId) {
//        dijit.byId("addVolumeDialog").hide();
        var volumeAddRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/attach/"
        });
        var currentVmId =  dijit.byId("storageVmWidget").value;
        volumeAddRestStore.add({
            volumeReferenceId : volumeId,
            virtualMachineReferenceId : currentVmId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {                    
                    var attachJobStatus = setInterval(function(){AddStorageInfo.volumeJobAttach(resultData.jobId, attachJobStatus);},3000);                      
                } else {                                      
                    registry.byId("userToaster").setContent(translator.common.instance.cannotAddVolume,"error");
                    registry.byId("userToaster").show();                    
                    
                    dojo.byId("addStorageLoader").innerHTML = " ";                    
                    dijit.byId("addStorageName").set("disabled", false);
                    dijit.byId("storageVmWidget").set("disabled", false);
                    dijit.byId("storageCustomDisk").set("disabled", false);
                    dijit.byId("storageDiskWidget").set("disabled", false);                    
                    dijit.byId("storageDiskSizeSpinner").set("disabled", false);
                    dijit.byId("storageCustomDiskSizeSlider").set("disabled", false);                    
                    dijit.byId("volumeAdd").set("disabled", false);
                    dijit.byId("add-disk-hourlyBilling").set("disabled", true);
                    dijit.byId("add-disk-monthlyBilling").set("disabled", true);
                }
            });
        });
    },    
    volumeJobAttach : function(jobId, attachJobStatus) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult == "OK") {
                    clearInterval(attachJobStatus);    
                    core.router.go("#/user/cloud/storage");
                    registry.byId("userToaster").setContent(translator.user.storage.createdAndAttached,"message");
                    registry.byId("userToaster").show();   
//                    AddStorageInfo.sendEmail(resultData)
                    dijit.byId("volumeAdd").set("disabled", false);                                       
                    dojo.byId("addStorageLoader").innerHTML = " ";                    
                    dijit.byId("addStorageName").set("disabled", false);
                    dijit.byId("storageVmWidget").set("disabled", false);
                    dijit.byId("storageCustomDisk").set("disabled", false);
                    dijit.byId("storageDiskWidget").set("disabled", false);                    
                    dijit.byId("storageDiskSizeSpinner").set("disabled", false);
                    dijit.byId("storageCustomDiskSizeSlider").set("disabled", false);                                                          
                } else if(resultData.jobResult == "Pending") {

                } else  if(resultData.jobResult == "FAILED"){                  
                    
                    registry.byId("userToaster").setContent(resultData.message,"error");
                    registry.byId("userToaster").show();
                    clearInterval(attachJobStatus);
                    dojo.byId("addStorageLoader").innerHTML= " ";                    
                    dijit.byId("addStorageName").set("disabled", false);
                    dijit.byId("storageVmWidget").set("disabled", false);
                    dijit.byId("storageCustomDisk").set("disabled", false);
                    dijit.byId("storageDiskWidget").set("disabled", false);
                    dijit.byId("storageDiskSizeSpinner").set("disabled", false);
                    dijit.byId("storageCustomDiskSizeSlider").set("disabled", false);
                    dijit.byId("volumeAdd").set("disabled", false);       
                    dijit.byId("add-disk-hourlyBilling").set("disabled", true);
                    dijit.byId("add-disk-monthlyBilling").set("disabled", true);
                } else {
                    registry.byId("userToaster").setContent(translator.admin.contactAdmin,"error");
                    registry.byId("userToaster").show();
                    clearInterval(attachJobStatus);
                    dojo.byId("addStorageLoader").innerHTML= " ";                    
                    dijit.byId("addStorageName").set("disabled", false);
                    dijit.byId("storageVmWidget").set("disabled", false);
                    dijit.byId("storageCustomDisk").set("disabled", false);
                    dijit.byId("storageDiskWidget").set("disabled", false);
                    dijit.byId("storageDiskSizeSpinner").set("disabled", false);
                    dijit.byId("storageCustomDiskSizeSlider").set("disabled", false);
                    dijit.byId("volumeAdd").set("disabled", false);      
                    dijit.byId("add-disk-hourlyBilling").set("disabled", true);
                    dijit.byId("add-disk-monthlyBilling").set("disabled", true);
                }
            });
        });
    },
    sendEmail: function(resultData) {
        var volumeMailRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/sendEmail"
        });         
        volumeMailRestStore.add({
            name: resultData.name,
            virtualMachine: resultData.virtualMachin
        });
    }
};
var StorageInfo = {
    _zoneRestStore: "",
    _diskOfferRestStore : "",
    _volumeRestStore:"",
    _virtualMachineRestStore : "",
    _storageGrid:"",
    _zoneWidget:"",
    _diskWidget:"",
    _snapShotRestStore:"",
    _volumeId:"",
    init : function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        });                                                

        this._volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        });     

        this._virtualMachineRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });  
        this._snapShotRestStore = new JsonRest({
                target: core.getContextPath()+"/api/snapShot/"
        }); 
        
        var accountVerifyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"         
        });
        accountVerifyRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.invoice == "yes") {
                    dojo.byId("addDiskButton").style.display = "block";
                } else {
                    dojo.byId("addDiskButton").style.display = "none";  
                }
            });
            var accountResourceLimitStore = new JsonRest({
                    target: core.getContextPath()+"/api/account/getCloudResourceStat"
            });

            accountResourceLimitStore.query().then(function(data) {
                 dojo.forEach(data, function(limitData) {  
                    if(limitData.storageLimit !== "-1") {
                        if((limitData.storageUsed >= limitData.storageLimit)) {
                            dojo.byId("storageLimitReached").style.display = "block";
                            dojo.byId("addDiskButton").style.display = "none";
                        } 
                    } 
                    
//                     if(limitData.accountType === "TRIAL") {
//                        if((limitData.volumeCount >= limitData.storageLimitNo)) {
//                             dojo.byId("storageLimitReached").style.display = "block";
//                             dojo.byId("addDiskButton").style.display = "none";
//                         } else if(limitData.accountType === "RETAIL") {
//                            dojo.byId("storageLimitReached").style.display = "none";
//                            dojo.byId("addDiskButton").style.display = "block";
//                         }
//                     }
                 });
             });
        });
    },
    setSliderSize : function(currentSpinner) {        
        dijit.byId("resizeDiskSlider").set("value", currentSpinner.value);
    },
    showGeneralDiskInfo : function() {
        var currentZoneID = dojo.byId("selectedANZoneID").value;
        var volumeCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/count"
        }); 
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            volumeCountRestStore.query().then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.byId("userTotalStorage").innerHTML = el.totalStorage;
                    dojo.byId("userAttachedStorage").innerHTML = el.attachedStorage;
                    dojo.byId("userDetachedStorage").innerHTML = el.detachedStorage;
                });
            });
        } else {
            volumeCountRestStore.query({zoneReferenceId: currentZoneID}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.byId("userTotalStorage").innerHTML = el.totalStorage;
                    dojo.byId("userAttachedStorage").innerHTML = el.attachedStorage;
                    dojo.byId("userDetachedStorage").innerHTML = el.detachedStorage;
                });
            });
        }        
    },
    populateValue : function() {
        if(dijit.byId("storageGrid")) {
            dijit.byId("storageGrid").destroyRecursive();            
          
        }    
        if(dijit.byId("storageVirtualMachineWidget")) {       
            dijit.byId("storageVirtualMachineWidget").destroyRecursive();    
        }         
        dojo.byId("storageListGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.user.loader.volumeLoading+"</p>";       
        var gridData = {
            items: []
        };               

        var gridDataList = new ItemFileWriteStore({data: gridData});       
        var diskOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var  diskList = new ItemFileWriteStore({data: diskOptions}); 
        
        var diskWidget = new Select({
            id: "diskWidget",  
            store : diskList,
            onChange : function() {
                StorageInfo.getDiskForResize(this);
            }
        }, "resizeStorage");
        diskWidget.startup();
        
                
        var customDiskSlider = new dijit.form.HorizontalSlider({
            name: "slider",                  
            style: "width:70%;", 
            container:'bottomDecoration',
            intermediateChanges: true,            
            onChange : function(value) {
                dijit.byId("storageResizeSpinner").setValue(value);
                dojo.byId("resizePlanSize").innerHTML =translator.common.size.sizeName+":  " +dijit.byId("storageResizeSpinner").getValue()+ translator.common.gb;
                if(dojo.byId("diskUnit").value == "/GB/month") {
                    dojo.byId("resizePlanCost").innerHTML =translator.common.cost+":  " + Math.round(dijit.byId("storageResizeSpinner").getValue() * dojo.byId("diskCost").value * 100)/100 + " " + translator.common.gbPerMonth; 
                }                 
            }
        },"resizeDiskSlider");
        customDiskSlider.startup();                            
        
        var gridLayout = [                                    
            {'field': 'id',   'hidden': 'true'},
            {'field': 'vmId',   'hidden': 'true'},     
            {'field': 'type','hidden': 'true'},    
            {'field': 'vmState','hidden': 'true'},    
            {'name': translator.user.grid.storage.layout.diskName, 'field': 'name','width': '200px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.user.grid.storage.layout.plan, 'field': 'plan', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            },
            {'name': translator.user.grid.storage.layout.instanceAttached, 'field': 'vmName','width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                         
                    var result;             
                    if(data == null || data == "null") {
                        result = "<span class=''> "+ translator.user.storage.notAttached +" </span>"
                    } else {
                        result = "<span class='bold'>" + data + "</span>";
                    }
                    return  result;                                
                }
            },                                    
            {'name': translator.user.grid.storage.layout.size, 'field': 'size','width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(currentData) {
                    var storageSize = parseInt(currentData);                   
                    return "<span class='bold'>" + storageSize + "</span>";
                }
            },                     
            {'name': translator.user.grid.storage.layout.status, 'field': 'status', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function(data) {
                    var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/>" });
                    var status;
                    if(data == "Visible" || data == "Creating") {
                        status = imageDiv.innerHTML;
                    } else if(data != "Visible" || data != "Creating") {
                        if(data == "Ready") {
                            status = "<div class='grid-status-green overflowLabel'>"+translator.common.status.ready+"</div>"; 
                        } else {
                            status = "";
                        }
                    } 
                    return status;
                }
            },                                       
            {'name': translator.user.grid.storage.layout.action, 'field': 'action', 'width': '100%', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(layoutData, rowIndex) {                
                    var grid = dijit.byId("storageGrid").store;
                    var type;
                    var vmName;
                    var status;
                    var vmStatus;
                    var hasVMSnapshot;
                    grid.fetch({ query: { action: layoutData},
                        onItem: function(item) {
                            var newType = item.type;
                            var newVm = item.vmName;
                            var loader = item.status;
                            var newVmState = item.vmState;
                            var newhasVMSnapshot = item.hasVMSnapshot;
                            dojo.forEach(newVm, function(el) {
                                vmName = el;
                            }); 
                            dojo.forEach(newhasVMSnapshot, function(el) {
                                hasVMSnapshot = el;
                            });
                            dojo.forEach(newType, function(el) {
                                type = el;
                            });     
                            dojo.forEach(loader, function(el) {
                                status = el;
                            });
                            dojo.forEach(newVmState, function(el) {
                                vmStatus = el;
                            });
                        }
                    });                    
                    var storageAction =  new FogPanel.StorageAction({
                        onDetachButtonClick : function() {
                            dojo.style(dijit.byId("storageDetachDisk").closeButtonNode,"display","none");
                            dijit.byId("storageDetachDisk").show();
                        },                    
                        onDeleteButtonClick : function() {
                            dojo.style(dijit.byId("storageDeleteDisk").closeButtonNode,"display","none");
                            dijit.byId("storageDeleteDisk").show();
                        },
                        onAttachButtonClick :function () {
                            dojo.style(dijit.byId("storageAttachDisk").closeButtonNode,"display","none");
                            dijit.byId("storageAttachDisk").show();
                            dojo.byId("strogeInstanceListCollection").style.display = "none";
                            dojo.byId("storageVmLoader").style.display = "block";
                            var getInstance = setInterval(function(){StorageInfo.getVmList(getInstance);},1000);             
                        },
                        onResizeButtonClick : function() {                           
                            dijit.byId("resizeDiskDialogue").show();
                            dojo.byId("resizeStorageCollection").style.display = "none";
                            dojo.byId("resizeStorageSliderCollection").style.display = "none";
                            dojo.byId("resizeStorageLoader").style.display = "block";
                            dojo.byId("resizePlanSize").innerHTML = "";
                            dojo.byId("resizePlanCost").innerHTML = "";
                            var getInstance = setInterval(function(){StorageInfo.getDiskOfferList(getInstance, layoutData);},1000); 

                        },
                        onSnapshotButtonClick : function() {
                            dojo.style(dijit.byId("storageConfirmSnapPage").closeButtonNode,"display","none");
                            dijit.byId("storageConfirmSnapPage").show();
                        }
                    });     
                    if(type == "ROOT" && vmName != null) {                    
                        storageAction = "";                  
                    } else if(vmName == null && type != "ROOT" && status != "Allocated") {
                        storageAction.enableAttachMode();
                    } else if((vmName != null && type != "ROOT" && status != "Allocated") && (vmStatus == "Running" || vmStatus == "Stopped")) {
                        storageAction.enableDetachMode();
                    } else if((vmName == null && type != "ROOT" && status == "Allocated") || (vmName != null && type != "ROOT" && status == "Allocated")) { 
                        storageAction = "";
                    } else {
                        storageAction = "";
                    }                 
                    if(status == "Visible" || status == "Creating") {
                        storageAction.disableAction();
                    }
                    if(hasVMSnapshot == "true") {
                        storageAction.enableVMSnapMode();
                    }
                    return storageAction;
                }
            }  
        ];    
        var currentZoneID = dojo.byId("selectedANZoneID").value;        
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            this._volumeRestStore.query().then(function(data) {        
            if(data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noStorageMessageBox").style.display = "block";
                dojo.byId("userTotalStorage").innerHTML = 0;
                dojo.byId("userAttachedStorage").innerHTML = 0;
                dojo.byId("userDetachedStorage").innerHTML = 0;
                dojo.byId("storageListGrid").innerHTML = "";
            } else {
                dojo.byId("noStorageMessageBox").style.display = "none";
                dojo.forEach(data, function(storageData) {
                    if(storageData.type == "ROOT") { 
                        dojo.byId("noStorageMessageBox").style.display = "block";
                        dojo.byId("userTotalStorage").innerHTML = 0;
                        dojo.byId("userAttachedStorage").innerHTML = 0;
                        dojo.byId("userDetachedStorage").innerHTML = 0;
                        dojo.byId("storageListGrid").innerHTML = "";                    
                        return;
                    } else if(storageData.type == "DATADISK" ) {
                        gridDataList.newItem({
                            id: storageData.referenceId,
                            vmId: storageData.virtualMachineReferenceId,
                            type : storageData.type,
                            vmState : storageData.virtualMachineState,
                            hasVMSnapshot : storageData.hasVMSnapshot,
                            name:storageData.name,                         
                            plan: storageData.diskOffer,                 
                            vmName: storageData.virtualMachin,                         
                            size: storageData.size,                         
                            status: storageData.state,   
                            action: storageData.referenceId
                        });  
                    }
                });                            
                dojo.byId("noStorageMessageBox").style.display = "none";
                dojo.byId("storageListGrid").innerHTML = " ";
                var storageGrid = new EnhancedGrid({
                    id: 'storageGrid',
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    "class": "span12",
                    noDataMessage:translator.user.grid.storage.noVolume,
                    plugins: core.getGridConfig().plugins
                });
                storageGrid.placeAt("storageListGrid");
                storageGrid.startup();  
                StorageInfo.showGeneralDiskInfo();             
            }
        });     
        } else {
            var volumeRestStore = new JsonRest({
                target: core.getContextPath()+"/api/volume"
            });   
            volumeRestStore.query({zoneReferenceId: currentZoneID}).then(function(data) {        
            if(data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noStorageMessageBox").style.display = "block";
                dojo.byId("userTotalStorage").innerHTML = 0;
                dojo.byId("userAttachedStorage").innerHTML = 0;
                dojo.byId("userDetachedStorage").innerHTML = 0;
                dojo.byId("storageListGrid").innerHTML = "";
            } else {
                dojo.byId("noStorageMessageBox").style.display = "none";
                dojo.forEach(data, function(storageData) {
                    if(storageData.type == "ROOT") { 
                        dojo.byId("noStorageMessageBox").style.display = "block";
                        dojo.byId("userTotalStorage").innerHTML = 0;
                        dojo.byId("userAttachedStorage").innerHTML = 0;
                        dojo.byId("userDetachedStorage").innerHTML = 0;
                        dojo.byId("storageListGrid").innerHTML = "";                    
                        return;
                    } else if(storageData.type == "DATADISK" ) {
                        gridDataList.newItem({
                            id: storageData.referenceId,
                            vmId: storageData.virtualMachineReferenceId,
                            type : storageData.type,
                            vmState : storageData.virtualMachineState,
                            hasVMSnapshot : storageData.hasVMSnapshot,
                            name:storageData.name,                         
                            plan: storageData.diskOffer,                 
                            vmName: storageData.virtualMachin,                         
                            size: storageData.size,                         
                            status: storageData.state,   
                            action: storageData.referenceId
                        });  
                    }
                });                            
                dojo.byId("noStorageMessageBox").style.display = "none";
                dojo.byId("storageListGrid").innerHTML = " ";
                var storageGrid = new EnhancedGrid({
                    id: 'storageGrid',
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    "class": "span12",
                    noDataMessage:translator.user.grid.storage.noVolume,
                    plugins: core.getGridConfig().plugins
                });
                storageGrid.placeAt("storageListGrid");
                storageGrid.startup();  
                StorageInfo.showGeneralDiskInfo();             
            }
        });     
        }     
        
        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var instanceList = new ItemFileWriteStore({data: instanceOptions}); 
        var vmWidget = new Select({        
            id: "storageVirtualMachineWidget",  
            store : instanceList,
            onChange : function() {}
        }, "strogeInstanceList");
        vmWidget.startup();    
    },       
    searchStorage : function(name) { 
        var volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        });

        var volumeGrid = dijit.byId("storageGrid"); 
        var gridData = {
            items: []
        };       
        
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var storagePlane;
        var storageTag;
        volumeRestStore.query({name:name}).then(function(data) {
            dojo.forEach(data, function(storageData) {
                if(storageData.type == "ROOT") {
                    storagePlane = "In Build";
                    storageTag = "Shared";
                } else {
                    storagePlane = storageData.diskOffer;
                    storageTag = storageData.tag;
                }
                gridDataList.newItem({
                    id: storageData.referenceId,vmId: storageData.virtualMachineReferenceId, name:storageData.name, 
                    plan: storagePlane,
                    zone: storageData.zoneName, vmName: storageData.virtualMachin, 
                    size: storageData.size, type: storageData.type, tag: storageTag,
                    status: storageData.state, snapShot: storageData.snapshot,
                    action: storageData.referenceId
                });      
            });
            volumeGrid.setStore(gridDataList);
        });
    },
    cancelSnapshot : function() {
        dijit.byId("storageConfirmSnapPage").hide(); 
    },   
    resizeDisk : function() {                
        var volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/resizeVolume/"
        }); 
        var diskOfferId = "";
        var size = "";
        var volumeId = dojo.byId("resizeVolumeId").value;
        
        var resizeDiskWidget = dijit.byId("diskWidget");       
        resizeDiskWidget.store.fetch({ query: {id: resizeDiskWidget.value},             
            onItem: function(item) {
                dojo.forEach(item.id, function(currentId) {
                    diskOfferId = currentId;
                }) 
                var custom = ""
                dojo.forEach(item.custom, function(currentCustom) {
                    custom = currentCustom;
                })
                if(custom== true ||custom == "true") {
                    var spinnerSize = dijit.byId("storageResizeSpinner").value.toString();
                    var newSize = spinnerSize.split(".");
                    size = newSize[0].toString();
                } else {
                    dojo.forEach(item.size, function(currentSize) {
                        size = currentSize.toString();
                    });
                }                                                                                        
            }
        });   
        
        dijit.byId('resizeButton').set('style', {display: 'none'});
        dojo.byId("resizeDiskLoader").style.display = "inline";
        dijit.byId('resizeCancelButton').set("disabled", true);
        
        dijit.byId('storageResizeSpinner').set("disabled", true);
        dijit.byId('resizeDiskSlider').set("disabled", true);
        resizeDiskWidget.set("disabled", true);                
        volumeRestStore.add({
            volumeReferenceId: volumeId,
            diskOfferId: diskOfferId,
            size: size            
        }).then(function(data) {
            dijit.byId('resizeVolumeLoader').show();
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var resizeJobStatus = setInterval(function(){StorageInfo.resizeJob(resultData.jobId, resizeJobStatus);},3000);                      
                } else {                        
                    registry.byId("userToaster").setContent(translator.user.storage.resizeError ,"error");
                    registry.byId("userToaster").show();
                    dijit.byId('resizeVolumeLoader').hide();
                    dijit.byId('resizeButton').set('style', {display: 'inline'});
                    dojo.byId("resizeDiskLoader").style.display = "none";
                    dijit.byId('resizeCancelButton').set("disabled", false);
                    
                    dijit.byId('storageResizeSpinner').set("disabled", false);
                    dijit.byId('resizeDiskSlider').set("disabled", false);
                    resizeDiskWidget.set("disabled", false);
                }
            });
        });        
    },
    resizeJob : function(jobId, resizeJobStatus) {
        var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/volume/resizeJob/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                    
                    if(resultData.jobResult == "OK") {
                        clearInterval(resizeJobStatus);
                        StorageInfo.showGeneralDiskInfo();
                        registry.byId("userToaster").setContent(translator.user.storage.upgradeSuccess,"message");
                        registry.byId("userToaster").show();                                               
                        dijit.byId('resizeVolumeLoader').hide();
                        var index = dijit.byId("storageGrid").selection.selectedIndex;
                        var item = dijit.byId("storageGrid").getItem(index);
                        var store = dijit.byId("storageGrid").store;                               
                        store.setValue(item, 'plan', resultData.diskOffer);
                        store.setValue(item, 'size', resultData.size);                                               
                        
                        dijit.byId("resizeDiskDialogue").hide();
                        dijit.byId('resizeButton').set('style', {display: 'inline'});
                        dojo.byId("resizeDiskLoader").style.display = "none";
                        dijit.byId('resizeCancelButton').set("disabled", false);
                        
                        dijit.byId('storageResizeSpinner').set("disabled", false);
                        dijit.byId('resizeDiskSlider').set("disabled", false);
                        dijit.byId("diskWidget").set("disabled", false);                                               
                       
                        
                                              
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else if(resultData.jobResult == "FAILED") {
                        registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                        registry.byId("userToaster").show();
                        clearInterval(resizeJobStatus);
                        dijit.byId('resizeVolumeLoader').hide();
                        
                        dijit.byId('resizeButton').set('style', {display: 'inline'});
                        dojo.byId("resizeDiskLoader").style.display = "none";
                        dijit.byId('resizeCancelButton').set("disabled", false);
                        
                        dijit.byId('storageResizeSpinner').set("disabled", false);
                        dijit.byId('resizeDiskSlider').set("disabled", false);
                        dijit.byId("diskWidget").set("disabled", false); 
                    } else {
                        var errorMessage = "";
                        if(resultData.localizedMessage == "Cannot get property 'volumeReferenceId' on null object") {
                            errorMessage = translator.user.storage.resizeError;
                        } else {
                            errorMessage = translator.user.storage.resizeError;
                        }
                        registry.byId("userToaster").setContent(errorMessage,"error");
                        registry.byId("userToaster").show();
                        clearInterval(resizeJobStatus);
                        dijit.byId('resizeVolumeLoader').hide();
                        
                        dijit.byId('resizeButton').set('style', {display: 'inline'});
                        dojo.byId("resizeDiskLoader").style.display = "none";
                        dijit.byId('resizeCancelButton').set("disabled", false);
                        
                        dijit.byId('storageResizeSpinner').set("disabled", false);
                        dijit.byId('resizeDiskSlider').set("disabled", false);
                        dijit.byId("diskWidget").set("disabled", false);                       
                        
                    }
                });
            });
    },
     getDiskForResize : function (currentDisk) {
        var resizeDiskWidget = dijit.byId("diskWidget");       
        resizeDiskWidget.store.fetch({ query: {id: currentDisk.value},             
            onItem: function(item) {
                dojo.forEach(item.custom, function(el) {
                    if(el == false || el == 'false') {
                        dojo.byId("resizePlanSize").innerHTML =  translator.common.size+":   " + item.size + translator.common.gb;
                        var resizeUnit = "";
                        if(item.unit == "/GB/month") {
                            resizeUnit = translator.common.gbPerMonth
                        } else {
                            resizeUnit = translator.common.gbPerHr
                        }
                        dojo.byId("resizePlanCost").innerHTML =translator.common.cost+":  " +  Math.round(item.size * item.cost * 100)/100 + " " + resizeUnit; 
                    }
                });
                                                                                                
            }
        });        
    },
    cancelResize : function() {
        dijit.byId("resizeDiskDialogue").hide();
    },
    getDiskOfferList : function(instance, currentVolumeId) {       
        dojo.byId("resizeStorageCollection").style.display = "none";
        dojo.byId("resizeStorageLoader").style.display = "block";
        dojo.byId("resizeVolumeId").value =  currentVolumeId;
        var diskOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var  diskList = new ItemFileWriteStore({data: diskOptions});        
        var volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/storageResizeList/"
        }); 
        volumeRestStore.query({volumeReferenceId: currentVolumeId}).then(function(diskData) {
            if(diskData == "" || diskData == undefined || diskData == 'undefined' || diskData == null || diskData == 'null') {
                dojo.byId("resizeStorageCollection").style.display = "none";
                dojo.byId("resizeStorageSliderCollection").style.display = "none";
                dojo.byId("resizePlanSize").innerHTML = translator.common.message.noPlan;
                dojo.byId("resizePlanCost").innerHTML = "";
                dijit.byId('resizeButton').set('style', {display: 'none'});                
            } else {   
                if(diskData[0].custom == true || diskData[0].custom == 'true') {
//                    dojo.byId("resizeStorageSliderCollection").style.display = "block";
                    dojo.byId("resizeStorageCollection").style.display = "none";
                    var size = Number(diskData[0].minSize);
                    if(size == diskData[0].maxSize) {
                        dojo.byId("resizeStorageSliderCollection").style.display = "none";
                        dojo.byId("resizePlanSize").innerHTML = translator.user.storage.upgradeVolumeError;
                        dojo.byId("resizePlanCost").innerHTML = "";
                        dijit.byId('resizeButton').set('style', {display: 'none'}); 
                    } else {
                        dojo.byId("resizeStorageSliderCollection").style.display = "block ";
                        dijit.byId('resizeButton').set('style', {display: 'inline'}); 
                        dojo.forEach(diskData, function(diskItem) {                
                            diskList.newItem({
                                id: diskItem.diskOfferReferenceId, 
                                name: diskItem.name,
                                size: diskItem.size, 
                                custom: diskItem.custom,
                                cost: diskItem.zoneCost
                            });
                        });
                        dijit.byId("diskWidget").setStore(diskList);                                              
                        
                        var min =  Number(size + 1);
                        var max = Number(diskData[0].maxSize);                        
                        var discretValue = max - min;                                            
                        
                        dijit.byId("resizeDiskSlider").set("discreteValues", Number(discretValue + 1));
                        dijit.byId("resizeDiskSlider").set("maximum", max);
                        dijit.byId("resizeDiskSlider").set("minimum", min);
                        dijit.byId("resizeDiskSlider").set("value", min);
                        
                        dijit.byId("storageResizeSpinner").attr("constraints", {max: max, min: min}); 
                        dijit.byId("storageResizeSpinner").setValue(min);
                        
                        dojo.byId("diskUnit").value = diskData[0].unit;
                        dojo.byId("diskCost").value = diskData[0].zoneCost;
                        
                        if(dojo.byId("diskUnit").value == "/GB/month") {                            
                            dojo.byId("resizePlanCost").innerHTML =translator.common.cost+":  " + Math.round(min * diskData[0].zoneCost * 100)/100 +" "+ translator.common.gbPerMonth; 
                        } else {
                            dojo.byId("resizePlanCost").innerHTML =translator.common.cost+":  " + diskData[0].zoneCost +" "+ translator.common.gbPerHr; 
                        }
                        dojo.byId("resizePlanSize").innerHTML =translator.common.size.sizeName + ":   " + min + translator.common.gb;
                        
                     }                    
                } else {
                    dojo.byId("resizeStorageCollection").style.display = "block";
                    dojo.byId("resizeStorageSliderCollection").style.display = "none";
                    dijit.byId('resizeButton').set('style', {display: 'inline'});
                    dojo.forEach(diskData, function(diskItem) {                
                        diskList.newItem({
                            id: diskItem.diskOfferReferenceId,
                            name: diskItem.name, 
                            size: diskItem.size,
                            custom: diskItem.custom, 
                            cost: diskItem.zoneCost,
                            unit: diskItem.unit
                        });
                    });                    
                    dijit.byId("diskWidget").setStore(diskList);                             
                    dojo.byId("resizePlanSize").innerHTML =  translator.common.size.sizeName+":   " + diskData[0].size + translator.common.gb;
                    var resizeUnit = "";
                    if(diskData[0].unit == "/GB/month") {
                        resizeUnit = translator.common.gbPerMonth;
                    } else {
                        resizeUnit = translator.common.gbPerHr;
                    }                   
                    dojo.byId("resizePlanCost").innerHTML =translator.common.cost + ":  " + Math.round(diskData[0].size * diskData[0].zoneCost * 100)/100 +" "+ resizeUnit;
                }
                
            }
            
        });
        
        dojo.byId("resizeStorageCollection").style.display = "block";
        dojo.byId("resizeStorageLoader").style.display = "none";
        clearInterval(instance);
    },
    getVmList : function(getInstance) {
        var vmWidget = dijit.byId("storageVirtualMachineWidget");
        dojo.byId("strogeInstanceListCollection").style.display = "none";
        dojo.byId("storageVmLoader").style.display = "block";
        
        var virtualMachineOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var  currentInstanceList = new ItemFileWriteStore({data: virtualMachineOptions});

        var virtualMachineRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/byDisk"
        }); 

        var storageGrid = dijit.byId("storageGrid");

        var  currentDataItem = storageGrid.selection.getSelected();
        var currentVolumeId;
        dojo.forEach(currentDataItem, function(el) {
            var volumeIdData = el.id;
            dojo.forEach(volumeIdData, function(el) {
                currentVolumeId = el;
            });
        });
        virtualMachineRestStore.query({volumeReferenceId:currentVolumeId}).then(function(vmListDate) {           
            if(vmListDate.length == 0 || vmListDate == "") {
                currentInstanceList.newItem({id:"option", name : translator.user.grid.instance.noInstance});
                vmWidget.setStore(currentInstanceList);   
            } else {
                dojo.forEach(vmListDate, function(vmData) {
                    if((vmData.state =="Running" || vmData.state == "Stopped") && vmData.hasVMSnapshot =="false") {
                        currentInstanceList.newItem({id: vmData.referenceId, name: vmData.name});
                    }                            
                });
                vmWidget.setStore(currentInstanceList);                            
            }                   
            dojo.byId("strogeInstanceListCollection").style.display = "block";  
            dojo.byId("storageVmLoader").style.display = "none";
        });
        clearInterval(getInstance);
    },
    takeSnapShot : function() {
        dijit.byId("storageConfirmSnapPage").hide(); 
        var storageGrid = dijit.byId("storageGrid");
        var  dataItem = storageGrid.selection.getSelected();
        var volumeId; 
        var name = dijit.byId("storageManualSnapshotName").value;
        dojo.forEach(dataItem, function(el) {
            var volumeIdData = el.id;
            dojo.forEach(volumeIdData, function(el) {
                volumeId = el;
            });
        });
        
//        var formElements = dojo.query("#add-snap-billingTypeDiv input:checked");
//        var checkedRadioId = dijit.byId(formElements[0].id);        
//        var billingType = dijit.byId(checkedRadioId).value;
        
        this._snapShotRestStore.add({
            volumeId : volumeId,
            snapshotName: name,
//            billingType:billingType
        }).then(function(data) {
            dijit.byId("storageManualSnapshotName").reset();
            core.router.go("#/user/cloud/snapShot");
        });
    },
    attachDisk :  function() {
        dijit.byId("storageAttachDisk").hide();
        var volumeAddRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/attach/"
        });
        var index = dijit.byId("storageGrid").selection.selectedIndex;
        var item = dijit.byId("storageGrid").getItem(index);
        var store = dijit.byId("storageGrid").store;
        var currentStatus = store.getValue(item, 'status');        
        store.setValue(item, 'status', "Visible");  
        store.setValue(item, 'action', "Visible");  

        var storageGrid = dijit.byId("storageGrid");
        var vmWidget = dijit.byId("storageVirtualMachineWidget");
        var  dataItem = storageGrid.selection.getSelected();
        var volumeId;
        dojo.forEach(dataItem, function(el) {
            var volumeIdData = el.id;
            dojo.forEach(volumeIdData, function(el) {
                volumeId = el;
            });
        });
        dijit.byId('attachDisk').set('style', {display: 'none'});
        dojo.byId("diskAttachLoader").style.display = "inline";
        volumeAddRestStore.add({
            volumeReferenceId : volumeId,
            virtualMachineReferenceId : vmWidget.value
        }).then(function(data) {
            dijit.byId('attachDisk').set('style', {display: 'inline'});
            dojo.byId("diskAttachLoader").style.display = "none";
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var attachJobStatus = setInterval(function(){StorageInfo.volumeJobAttach(resultData.jobId, attachJobStatus, currentStatus);},3000);                      
                } else {                        
                    registry.byId("userToaster").setContent(translator.user.storage.cannotAttached,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },
    volumeJobAttach : function(jobId, attachJobStatus, currentStatus) {   
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/volume/job/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                    
                    if(resultData.jobResult == "OK") {
                        clearInterval(attachJobStatus);
                        StorageInfo.showGeneralDiskInfo();
                        registry.byId("userToaster").setContent(translator.user.storage.volumeAttached,"message");
                        registry.byId("userToaster").show();
                        var index = dijit.byId("storageGrid").selection.selectedIndex;
                        var item = dijit.byId("storageGrid").getItem(index);
                        var store = dijit.byId("storageGrid").store;
                        store.setValue(item, 'vmName', resultData.virtualMachin);
                        store.setValue(item, 'vmId', resultData.virtualMachineReferenceId);
                        store.setValue(item, 'status', resultData.state);
                        
                        dijit.byId("storageGrid").update();
                                              
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else {
                        var errorMessage = "";
                        if(resultData.localizedMessage == "Cannot get property 'volumeReferenceId' on null object") {
                            errorMessage = "Failed  to attach volume , due to The VM rejected the attempt to attach the device."
                        } else if(resultData.message){
                            errorMessage = resultData.message;                           
                        } else {
                             errorMessage = translator.user.storage.volumeAttachedError;
                        }
                        registry.byId("userToaster").setContent(errorMessage,"error");
                        registry.byId("userToaster").show();
                        clearInterval(attachJobStatus);
                        var idx = dijit.byId("storageGrid").selection.selectedIndex;
                        var items = dijit.byId("storageGrid").getItem(idx); 
                        var volumeStore = dijit.byId("storageGrid").store;                            
                        volumeStore.setValue(items, 'status', currentStatus);  
                        dijit.byId("storageGrid").update();                       
                    }
                });
            });
        },
         volumeJobDetach : function(jobId, detachJobStatus, currentStatus) {
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/volume/job/"
            }); 
            jobStore.add({
                jobId : jobId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult == "OK") {
                        clearInterval(detachJobStatus);
                        StorageInfo.showGeneralDiskInfo();
                        registry.byId("userToaster").setContent(translator.user.storage.storageDetached,"message");
                        registry.byId("userToaster").show();
                        var index = dijit.byId("storageGrid").selection.selectedIndex;
                        var item = dijit.byId("storageGrid").getItem(index);
                        var store = dijit.byId("storageGrid").store;
                        store.setValue(item, 'vmName', null);
                        store.setValue(item, 'status', resultData.state);
                        dijit.byId("storageGrid").update();   
//                        dojo.byId("volumeLoaderDetach").style.display = "none";
                        dijit.byId("storageDetachDisk").hide();
                        dijit.byId("addDetachDisk").setAttribute('disabled', false);
                        dijit.byId("cancelDetachDisk").setAttribute('disabled', false);
                        dijit.byId("storageDetachDisk").hide();
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else {
                        clearInterval(detachJobStatus);
                        var errorMsg;
                        if(resultData.message == "Cannot get property 'volumeReferenceId' on null object") {
                            errorMsg = translator.user.storage.volumeDetachedFailed;
                        } else if(resultData.message != "Cannot get property 'volumeReferenceId' on null object") {
                            errorMsg = translator.common.cloudStack.errorMessage
                        } else {
                            errorMsg = translator.user.storage.cannotDetached;
                        }
                        registry.byId("userToaster").setContent(errorMsg, "error");
                        registry.byId("userToaster").show();
                        var indexData = dijit.byId("storageGrid").selection.selectedIndex;
                        var itemData = dijit.byId("storageGrid").getItem(indexData);
                        var gridStore = dijit.byId("storageGrid").store;
                        dijit.byId("storageGrid").update();   
                        gridStore.setValue(itemData, 'status', currentStatus);
                        dijit.byId("addDetachDisk").setAttribute('disabled', false);
                        dijit.byId("cancelDetachDisk").setAttribute('disabled', false);                        
                    }
                });
            });
        },
        cancelAttachDisk : function() {
            dijit.byId("storageAttachDisk").hide();
        },
        deleteStorage : function() {           
           dijit.byId("storageDeleteDisk").hide();
           var storageGrid = dijit.byId("storageGrid");
                   
           var index = storageGrid.selection.selectedIndex;
            var item = storageGrid.getItem(index);
            var store = storageGrid.store;
            var currentStatus = store.getValue(item, 'status');        
            store.setValue(item, 'status', "Visible");  
                       
           var  dataItem = storageGrid.selection.getSelected();
           var volumeId;
           dojo.forEach(dataItem, function(el) {
             var volumeIdData = el.id;
             dojo.forEach(volumeIdData, function(el) {
               volumeId = el;
           });
           });        
            
            var volumeRestStore = new JsonRest({
                target: core.getContextPath()+"/api/volume/"
            }); 
            volumeRestStore.remove(volumeId).then(function(deleteResult) {              
                    if(deleteResult == "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.deletedSuccess,"message");
                        registry.byId("userToaster").show();
                        StorageInfo.showGeneralDiskInfo();
                        dijit.byId("storageDeleteDisk").hide();
                        var items = dijit.byId("storageGrid").selection.getSelected();
                        dojo.forEach(items, function(selectedItem) {                            
                            dijit.byId("storageGrid").store.deleteItem(selectedItem);
                        });                             
                    } else if(deleteResult == "FAILED") {
                        registry.byId("userToaster").setContent(translator.user.storage.volumeDeleted,"error");
                        registry.byId("userToaster").show();
                        dojo.byId("volumeLoaderDelete").style.display = "none";
                        dijit.byId("storageDeleteAddDisk").setAttribute('disabled', false);
                        dijit.byId("storageDeleteCancelDisk").setAttribute('disabled', false);
                        dijit.byId("storageDeleteDisk").show();
                    } else if (deleteResult == "volume") {
                        registry.byId("userToaster").setContent(translator.user.storage.detachMessage,"error");
                        registry.byId("userToaster").show();
                        dojo.byId("volumeLoaderDelete").style.display = "none";
                        dijit.byId("storageDeleteAddDisk").setAttribute('disabled', false);
                        dijit.byId("storageDeleteCancelDisk").setAttribute('disabled', false);
                        dijit.byId("storageDeleteDisk").show();
                    } else {
                        registry.byId("userToaster").setContent(translator.user.storage.deleteVolumeError,"error");
                        registry.byId("userToaster").show();                        
                        dojo.byId("volumeLoaderDelete").style.display = "none";
                        dijit.byId("storageDeleteAddDisk").setAttribute('disabled', false);
                        dijit.byId("storageDeleteCancelDisk").setAttribute('disabled', false);                        
                        store.setValue(item, 'status', currentStatus);  
                        storageGrid.update();   
                    }
                });
            
        },
        DetachStorage : function() {
            var storageGrid = dijit.byId("storageGrid");
            dijit.byId("storageDetachDisk").hide();
            var index = dijit.byId("storageGrid").selection.selectedIndex;
            var item = dijit.byId("storageGrid").getItem(index);
            var store = dijit.byId("storageGrid").store;
            var currentStatus = store.getValue(item, 'status');  
            store.setValue(item, 'status', "Visible");
            
            var  dataItem = storageGrid.selection.getSelected();
            var volumeId;
            var vmId;
            dojo.forEach(dataItem, function(el) {
                var volumeIdData = el.id;
                var virtualMachineId = el.vmId;
                dojo.forEach(volumeIdData, function(el) {
                    volumeId = el;
                });
                dojo.forEach(virtualMachineId, function(el) {
                    vmId = el;
                });
            });
            var volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/detach/"
            }); 
            volumeRestStore.add({
                volumeReferenceId: volumeId,  
                virtualMachineReferenceId : vmId
            }).then(function(result) {
                dojo.forEach(result, function(resultData) {                    
                    if(resultData.result == "OK") {
                        var detachJobStatus = setInterval(function(){StorageInfo.volumeJobDetach(resultData.jobId, detachJobStatus, currentStatus);},3000);                      
                    } else {                        
                        registry.byId("userToaster").setContent(translator.user.storage.cannotDetached,"error");
                        registry.byId("userToaster").show();
                    }
                });
                 
            });                    
        },
        cancelDetachStorage : function() {
            dijit.byId("storageDetachDisk").hide();
        },
        cancelDeleteStorage : function() {
            dijit.byId("storageDeleteDisk").hide();
        },        
        showAddStorage : function() {
            dijit.byId("storageAddDisk").show();
        },
        showSize : function() {          

        }
    };
    
var SnapShotInfo = {
    _zoneRestStore : "",
    _diskOfferRestStore : "",
    _volumeRestStore : "",
    _virtualMachineRestStore : "",
    _snapShotRestStore : "",
    _volumeId:"",
    _snapShotPolicyRestStore:"",
    init : function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        });                                                

        this._volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        });     

        this._virtualMachineRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });  

        this._snapShotRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/"
        }); 
        this._snapShotPolicyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShotPolicy/"
        }); 
        
        var accountListStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"         
        });
        
        accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {  
                if(el.accountType == "TRIAL") {
                    dojo.byId("vmSnapshotLink").style.display = "none";
                }
            });
        });
    },     
    generalSnapshotCount : function() {
        var snapShotCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/count"
        }); 
        snapShotCountRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("userTotalSnap").innerHTML = el.totalCount;
                dojo.byId("userManualSnap").innerHTML = el.totalManual;
                dojo.byId("userAutoSnap").innerHTML = el.totalAuto;
                dojo.byId("userSnapshotJobs").innerHTML = el.totalJobs;                
            });
        });   
        
        
            var accountResourceLimitStore = new JsonRest({
                    target: core.getContextPath()+"/api/account/getCloudResourceStat"
            });

            accountResourceLimitStore.query().then(function(data) {
                 dojo.forEach(data, function(limitData) {  
                    if(limitData.snapshotLimit !== "-1") {
                        if((limitData.snapshotUsed >= limitData.snapshotLimit)) {
                            dojo.byId("snapshotLimitReached").style.display = "block";
                            dojo.byId("snapshotButton").style.display = "none";
                        } 
                    } 
                 });
             });
        
        
//        var accountResourceLimitStore = new JsonRest({
//            target: core.getContextPath()+"/api/account/resourceLimit"
//        });        
//        accountResourceLimitStore.query().then(function(data) {
//            dojo.forEach(data, function(limitData) {  
//                if(limitData.accountType == "TRIAL") {
//                    if(limitData.snapCount >= limitData.snapLimitNo) {
//                        dojo.byId("snapshotLimitReached").style.display = "block";
//                        dojo.byId("snapshotButton").style.display = "none";
//                    } else {
//                        dojo.byId("snapshotLimitReached").style.display = "none";
//                        dojo.byId("snapshotButton").style.display = "block";
//                    }
//                }
//            });
//        });        
    },
    populateValues : function() {
        if(dijit.byId("snapShotGrid") && dijit.byId("autoSnapshotGrid")) {    
            dijit.byId("snapShotGrid").destroyRecursive();      
            dijit.byId("autoSnapshotGrid").destroyRecursive();               
        }        
        if(dijit.byId("snapShotGrid")) {                 
            dijit.byId("snapShotGrid").destroyRecursive();                      
        }
        dojo.byId("snapShotList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.user.loader.snapshotLoading+"</p>";
        dojo.byId("autoSnapCollection").style.display = "none";        
        var snapShotData = {
            items: []
        };
        
        var snapShotDataList = new ItemFileWriteStore({data: snapShotData});        
        var snapShotLayout = [
            [
                {'field': 'id',   'hidden': 'true'},
                {'field': 'storageType',   'hidden': 'true'},
                {'name': translator.user.grid.snapshot.layout.snapshotName, 'field': 'snapshotName',  datatype:"string",  autoComplete: true, 'width': '200px'},
                {'name': translator.user.grid.snapshot.layout.volumeName, 'field': 'volumeName', datatype:"string",  autoComplete: true, 'width': '100px','formatter': function(data) {                                                                      
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.user.grid.snapshot.layout.instanceName, 'field': 'vmName', datatype:"string",  autoComplete: true, 'width': '200px','formatter': function(data) {                                                                      
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.user.grid.snapshot.layout.type, 'field': 'type', datatype:"string",  autoComplete: true, 'width': '100px'},
                {'name': translator.user.grid.snapshot.layout.createdDate, 'field': 'created', datatype:"string",  autoComplete: true, 'width': '200px','formatter': function(data) {                                                                      
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.user.grid.snapshot.layout.status, 'field': 'state', datatype:"string",  autoComplete: true, 'width': '100px', 'formatter' : function(data) {
                        var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' alt='"+translator.user.loader.volumeLoading+"' height='36' width='100'/> " });
                        var status;                                  
                        var grid = dijit.byId("snapShotGrid").store;
                        var stat;                       
                        grid.fetch({ query: {state: data},  
                            onItem: function(item) {  
                                var newStatus = item.state;
                                dojo.forEach(newStatus, function(el) {
                                    stat = el;
                                });                                  
                            }
                        });                      
                        if(stat == "BackedUp") {
                            status = "<div class='grid-status-blue'>"+translator.user.snapshot.backedup+"</div>";
                        } else if(stat == "BackingUp" || stat == "Creating") {
                            status = imageDiv.innerHTML;
                        }
                        return status;
                    }
                },
                {'name': translator.user.grid.snapshot.layout.action, 'field': 'action', datatype:"string",  autoComplete: true, 'width': '100%', 'formatter' : function(item) {
                        var grid = dijit.byId("snapShotGrid").store;
                        var state;
                        var type;
                        grid.fetch({ query: { action: item},  
                        onItem: function(item) {                           
                            var newStatus = item.state;
                            var newType = item.storageType;
                            dojo.forEach(newStatus, function(el) {
                                state = el;
                            });  
                            dojo.forEach(newType, function(el) {
                                type = el;
                            });   
                        }
                    });   
                    var snapShotAction =  new FogPanel.StorageAction({
                        onTempButtonClick : function() {       
                            dijit.byId("snapshotTempPage").show();                                  
                        },
                        onStorageButtonClick : function() {
                            dijit.byId("snapshotStorageName").reset();
                            dojo.style(dijit.byId("createStoragePage").closeButtonNode,"display","none");                                                        
                            var configRestStoreBilling = new JsonRest({
                                target: core.getContextPath()+"/api/config/"
                            });
                            configRestStoreBilling.query().then(function(resultData) {
                                dojo.forEach(resultData, function(config) { 
                                    if(config.name == "monthly.billing.enabled") {
                                        if(config.value == "true" || config.value == true) {
                                            dojo.byId("snap-add-disk-billingTypeDiv").style.display = "block";
                                        } else {
                                            dojo.byId("snap-add-disk-billingTypeDiv").style.display = "none";
                                        }
                                    } 
                                });
                                dijit.byId("createStoragePage").show();
                            });
                        },
                        onDeleteSnapShotButtonClick :function () {
                            dijit.byId("deleteSnapshotPage").show();
                        }                            
                    });
                    if(state == "BackingUp" || state == "Creating") {
                        snapShotAction.disableSnapshotAction();
                    }
                    var accountResourceLimitStore = new JsonRest({
                        target: core.getContextPath()+"/api/account/resourceLimit"
                    });                                
                    accountResourceLimitStore.query().then(function(data) {
                        dojo.forEach(data, function(limitData) {  
                            if(limitData.accountType == "TRIAL") {
                                if((limitData.volumeCount >= limitData.storageLimitNo)) {
                                    snapShotAction.hideAddVolume();
                                } 
                            } 
                        });
                    });
                    snapShotAction.enableSnapshotDataMode();
                    snapShotAction.showSnapShotAction();
                    return snapShotAction;
                }
            }
        ]
    ];           
    
        var currentZoneID = dojo.byId("selectedANZoneID").value;        
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            this._snapShotRestStore.query().then(function(data) {
                if(data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                    dojo.byId("noSnapMessageBox").style.display = "block";                    
                    dojo.byId("userTotalSnap").innerHTML = 0;
                    dojo.byId("userManualSnap").innerHTML = 0;
                    dojo.byId("userAutoSnap").innerHTML = 0;
                    dojo.byId("userSnapshotJobs").innerHTML = 0;
                    dojo.byId("snapShotList").innerHTML = "";
                } else {
                    dojo.forEach(data, function(snapShotData) { 
                        dojo.byId("noSnapMessageBox").style.display = "none";
                        snapShotDataList.newItem({
                            id: snapShotData.snapShotId,
                            storageType: snapShotData.diskVolumeType,
                            snapshotName:snapShotData.snapShotName,
                            volumeName: snapShotData.diskVolumeName, 
                            vmName: snapShotData.virtualMachineName,
                            type: snapShotData.snapShotType,
                            created:snapShotData.created, 
                            state: snapShotData.snapshotState,
                            action: snapShotData.snapShotId    
                        });               
                    });
                    dojo.byId("snapShotList").innerHTML = "";
                    var snapShotGrid = new EnhancedGrid({
                        id: 'snapShotGrid',
                        "class": "span12",
                        store: snapShotDataList,
                        noDataMessage: translator.user.grid.snapshot.noSnapshot,
                        structure: snapShotLayout,            
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    snapShotGrid.placeAt("snapShotList");
                    snapShotGrid.startup();
                    SnapShotInfo.generalSnapshotCount();                
                }                                               
            });  
        } else {
            var snapshotCountRestStore = new JsonRest({
                target: core.getContextPath()+"/api/snapShot/count"
            });
            snapshotCountRestStore.query({zoneReferenceId: currentZoneID}).then(function (data) {
                if(data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                    dojo.byId("noSnapMessageBox").style.display = "block";  
                } else {
                    dojo.byId("noSnapMessageBox").style.display = "none";  
                }
                dojo.forEach(data[0].snapshotInfo, function (snapShotData) {
                    snapShotDataList.newItem({
                        id: snapShotData.referenceId,
                        storageType: snapShotData.diskVolumeType,
                        snapshotName:snapShotData.name,
                        volumeName: snapShotData.volumeName, 
                        vmName: snapShotData.virtualMachineName,
                        type: snapShotData.snapShotType,
                        created:snapShotData.created, 
                        state: snapShotData.snapshotState,
                        action: snapShotData.referenceId    
                    });
                });
                dojo.byId("snapShotList").innerHTML = "";
                var snapShotGrid = new EnhancedGrid({
                    id: 'snapShotGrid',
                    "class": "span12",
                    store: snapShotDataList,
                    noDataMessage: translator.user.grid.snapshot.noSnapshot,
                    structure: snapShotLayout,            
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                snapShotGrid.placeAt("snapShotList");
                snapShotGrid.startup();
                SnapShotInfo.generalSnapshotCount(); 
            })
        } 
        var volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume"
        });  
        volumeRestStore.query().then(function (data) {
            var spanElement = "<ul>";
            dojo.forEach(data[0].miscelZoneCostList, function (zoneMiscelCost, index) {           
                spanElement += "<li>" + zoneMiscelCost.zoneName + "<span>" + ":" + "<span class='require'>" + "*" + zoneMiscelCost.currency + " "+ zoneMiscelCost.cost + "/" +translator.common.hour + "/" + translator.common.gb +"</li>"              
            });
            spanElement += "</ul>"
            dojo.byId("zoneMisceCostInfo").innerHTML = spanElement;
        })
                      
    var autoSnapshotData = {
        items: [{}]
    };
                                    
    var autoSnapshotList = new ItemFileWriteStore({
        data: autoSnapshotData
    });
       
    var autoSnapshotLayout = [            
        [
            {'field': 'id',   'hidden': 'true'},
            {'name': translator.user.grid.snapshot.autoSnapshot.layout.time, 'field': 'time', 'width' : '100px'},
            {'name': translator.user.grid.snapshot.autoSnapshot.layout.schedule, 'field': 'schedule', 'width' : '100px'},
            {'name': translator.user.grid.snapshot.autoSnapshot.layout.timeZone, 'field': 'timeZone', 'width': '200px'},
            {'name': translator.user.grid.snapshot.autoSnapshot.layout.keep, 'field': 'keep', 'width': '100px'},
            {'name': translator.user.grid.snapshot.autoSnapshot.layout.action, 'field': 'action', 'width': '100%', 'formatter' : function(items) {
                    var widget = new FogPanel.StorageAction({
                        deleteTagClick : function() {                                
                            SnapShotInfo.deleteAutoSnapshot(items);
                        }
                    });
                    widget.disableAll();
                    return widget;                        
                }
            }
        ]
    ];                           
    var autoJobData = {
        items: []        
    };                                            
    var autosnapShotGrid = new DataGrid({
        id: 'autoSnapshotGrid',
        store: autoSnapshotList,
        structure: autoSnapshotLayout,
        "class": "span12",
        height:  '150px'           
    });
    autosnapShotGrid.placeAt("autoSnapshotList");
    autosnapShotGrid.startup();         

    },
    searchSnapShot : function(name) { 
        var snapShotRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/"
        });
        var snapShotGrid = dijit.byId("snapShotGrid"); 
        var gridData = {
            items: []
        };                   
        var snapShotDataList = new ItemFileWriteStore({data: gridData});
        snapShotRestStore.query({name:name}).then(function(data) {
            dojo.forEach(data, function(snapShotData) { 
                snapShotDataList.newItem({
                    id: snapShotData.snapShotId,
                    storageType: snapShotData.diskVolumeType,
                    snapshotName:snapShotData.snapShotName,
                    volumeName: snapShotData.diskVolumeName, 
                    vmName: snapShotData.virtualMachineName,
                    type: snapShotData.snapShotType,
                    created:snapShotData.created, 
                    state: snapShotData.snapshotState,
                    action: snapShotData.snapShotId    
                });               
            });
            snapShotGrid.setStore(snapShotDataList);                
        });            
    },        
    createStorage: function() {
        var createStorageRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/createVolume/"
        });                            
            
        var formElements = dojo.query("#snap-add-disk-billingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;

        var snapshotGrid = dijit.byId("snapShotGrid");
        var  dataItem = snapshotGrid.selection.getSelected();
        var volumeId; 
        dojo.forEach(dataItem, function(el) {
            var volumeIdData = el.id;
            dojo.forEach(volumeIdData, function(el) {
                volumeId = el;                    
            });
        });
        var storageName = dijit.byId("snapshotStorageName").value;            
        var status =  true;          
        if (dijit.byId("snapshotStorageName").validate && !dijit.byId("snapshotStorageName").validate()) {
            dijit.byId("snapshotStorageName").focus();                             
            status = false;
        }          
        if(status == true) {                
            dojo.byId("createSnapshotLoader").style.display = "block";
            dijit.byId("storageAddButton").setAttribute('disabled', true);
            dijit.byId("storageCancelButton").setAttribute('disabled', true);
            createStorageRestStore.add({
            name: storageName,
            snapshotId : volumeId,
            billingType : billingType                
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var snapshotJobStatus = setInterval(function(){SnapShotInfo.volumeJob(resultData.jobId, snapshotJobStatus);},3000);                       
                } else {
                        dojo.byId("createSnapshotLoader").style.display = "none";
                        dijit.byId("storageAddButton").setAttribute('disabled', false);
                        dijit.byId("storageCancelButton").setAttribute('disabled', false);
                        registry.byId("userToaster").setContent(translator.user.storage.addVolumeError,"error");
                        registry.byId("userToaster").show();
                    }
                });
            });
        }                    
    },
    deleteAutoJob : function(data) {
        var autoSnapshot = new JsonRest({
            target: core.getContextPath()+"/api/snapShotPolicy/"
        });
        autoSnapshot.remove(data).then(function(result) {
            if(result == "OK") {
                registry.byId("userToaster").setContent(translator.common.message.deletedJobSuccess,"message");
                registry.byId("userToaster").show();
                var autoSnapshotGrid = dijit.byId("autoJobGrid");
                var items = autoSnapshotGrid.selection.getSelected();
                dojo.forEach(items, function(selectedItem) {
                    autoSnapshotGrid.store.deleteItem(selectedItem);
                });
            }  else {
                registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                registry.byId("userToaster").show();
            }
        });
    },        
    updateAutoSnapshot : function() {             
        var snapshotName = dijit.byId("autoSnapshotName").value;
        var schedule = dijit.byId("autoSchedule").value;
        var scheduleHr = dijit.byId("scheduleHr").value;
        var scheduleMin = dijit.byId("scheduleMin").value;
        var timeZone = dijit.byId("snapshotTimeZone").value;
        var keep = dijit.byId("snapshotKeep").value;
        var dayValue  = dijit.byId("snapshotDay").value;
        var dayName  = dijit.byId("snapshotDay").get("displayedValue");
        var month  = dijit.byId("snapshotMonth").value;
        var timeZoneName =  dijit.byId("snapshotTimeZone").get("displayedValue");
        var firstNode = "";
        var node = dojo.byId("autoSnapshotForm");
        var widget = dijit.registry.findWidgets(node);        

        dojo.forEach(widget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        var autoSnapshot = new JsonRest({
            target: core.getContextPath()+"/api/snapShotPolicy/"
        });
        var currentId = dojo.byId("currentAutoSnapId").value;
        dijit.byId('updateAutoSnapButton').set('style', {display: 'none'});
        dojo.byId("snapLoader").style.display = "inline";
        autoSnapshot.put({
            id:currentId,
            snapshotName: snapshotName,
            intervaltype: schedule,
            scheduleHour: scheduleHr,
            scheduleMin: scheduleMin,
            timeZone: timeZone,
            timeZoneName : timeZoneName,
            maxsnaps: keep,
            dayValue: dayValue,
            dayName: dayName,
            month: month
        }).then(function(data) {
            dijit.byId('updateAutoSnapButton').set('style', {display: 'inline'});
            dojo.byId("snapLoader").style.display = "none";
            dojo.forEach(data, function(resultData) {                    
                if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.user.snapshot.snapshotUpdateSuccess,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("ConformationAutoSnapShotPage").hide();                                               
                } else {
                    registry.byId("userToaster").setContent(translator.user.snapshot.snapshotUpdateError,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },
    returnAutoSnapshot : function(data) { 
        var currentId = data;
        dojo.byId("currentAutoSnapId").value = currentId;
            
        var autoSnapshot = new JsonRest({
            target: core.getContextPath()+"/api/snapShotPolicy/"
        });
        dijit.byId("ConformationAutoSnapShotPage").show();
        dijit.byId("snapshotJobListPage").hide();

        dijit.byId('updateAutoSnapButton').set('style', { display: 'block' });
        dijit.byId('addAutoSnapButton').set('style', { display: 'none' });            
        
        var snapshotName = dijit.byId("autoSnapshotName");
        var schedule = dijit.byId("autoSchedule");
        var scheduleHr = dijit.byId("scheduleHr");
        var scheduleMin = dijit.byId("scheduleMin");
        var timeZone = dijit.byId("snapshotTimeZone");
        var keep = dijit.byId("snapshotKeep");
        var dayValue  = dijit.byId("snapshotDay");
        var month  = dijit.byId("snapshotMonth");
        var snapshotPolicyIntervalType  = dijit.byId("autoSchedule");                       
        autoSnapshot.get(currentId).then(function(data) {
            dojo.forEach(data, function(result) {
                var time = result.snapshotSchedule.split(":");
                scheduleHr.setAttribute("value", time[0]);
                snapshotPolicyIntervalType.setAttribute("value", data.snapshotPolicyIntervalType);
                scheduleMin.setAttribute("value", time[1]);
                snapshotName.setAttribute("value", result.name);
                keep.setValue(result.maxSnaps);    
                schedule.setAttribute("value", result.snapshotPolicyIntervalType);
                schedule.setAttribute('disabled', true);
                dayValue.setAttribute("value", result.dayValue);
                timeZone.setAttribute("value", result.timeZoneValue);
                month.setAttribute("value", result.monthValue);
                dojo.byId("autoSnapDiskName").innerHTML = result.volumeName;
            });
        });
    },        
    deleteAutoSnapshot : function(data) {    
        var currentId = data;            
        var autoSnapshot = new JsonRest({
            target: core.getContextPath()+"/api/snapShotPolicy/"
        });
        var autoSnapshotGrid = dijit.byId("autoSnapshotGrid");          
        var scheduleOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };            
        var scheduleList = new ItemFileWriteStore({data: scheduleOption});
        autoSnapshot.remove(currentId).then(function(result) {
            if(result == "OK") {
                registry.byId("userToaster").setContent(translator.user.storage.addedStorage,"message");
                registry.byId("userToaster").show();
                var items = autoSnapshotGrid.selection.getSelected();
                    dojo.forEach(items, function(selectedItem) {
                        autoSnapshotGrid.store.deleteItem(selectedItem);
                });
                autoSnapshotGrid.store.fetch({onComplete: function(items, request) {
                        if(items.length != 0) {
                            if(items.length == 3) {
                                scheduleList.newItem({id: "option", name: "Select"});
                            }  else if(items.length == 2 && items[0].schedule == "DAILY" && items[1].schedule == "MONTHLY"){
                                 scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                            } else if(items.length == 2 && items[0].schedule == "DAILY" && items[1].schedule == "WEEKLY") {
                                scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                            }  else if(items.length == 2 && items[0].schedule == "WEEKLY" && items[1].schedule == "MONTHLY") {
                                scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                            } else if(items.length == 2 && items[0].schedule == "MONTHLY" && items[1].schedule == "WEEKLY") {
                                scheduleList.newItem({id: "DAILY", name: "Daily"});
                            } else if(items.length == 1 && items[0].schedule == "DAILY") {
                                scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                                scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                            } else if(items.length == 1 && items[0].schedule == "MONTHLY") {
                               scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                                scheduleList.newItem({id: "DAILY", name: "Daily"});
                            } else if(items.length == 1 && items[0].schedule == "WEEKLY") {
                                scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                                scheduleList.newItem({id: "DAILY", name: "Daily"});
                            }                                      
                        } else {
                            scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                            scheduleList.newItem({id: "DAILY", name: "Daily"});
                            scheduleList.newItem({id: "WEEKLY", name: "Weekly"});                            
                        }
                        dijit.byId("autoSchedule").setStore(scheduleList); 
                    }
                });
            } else {
                registry.byId("userToaster").setContent(translator.common.message.refreshPage,"error");
                registry.byId("userToaster").show();
            }
        });            
    },
    volumeJob : function(jobId, snapshotJobStatus) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/job/"
        }); 
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(snapshotJobStatus);

                    registry.byId("userToaster").setContent(translator.user.storage.addedStorage,"message");
                    registry.byId("userToaster").show();
                    dojo.byId("createSnapshotLoader").style.display = "none";
                    dijit.byId("storageAddButton").setAttribute('disabled', false);
                    dijit.byId("storageCancelButton").setAttribute('disabled', false);


                    dijit.byId("snapshotStorageName").reset();
                    dijit.byId("createStoragePage").hide();
                     core.router.go("#/user/cloud/storage");
                } else if(resultData.jobResult == "Pending") {

                } else {
                    clearInterval(snapshotJobStatus);
                    dojo.byId("createSnapshotLoader").style.display = "none";
                    dijit.byId("storageAddButton").setAttribute('disabled', false);
                    dijit.byId("storageCancelButton").setAttribute('disabled', false);
                    registry.byId("userToaster").setContent(translator.common.message.refreshPage,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("createStoragePage").hide();
                    dijit.byId("snapshotStorageName").reset();
                }
            });
        });
    },
    createTemp : function() {
        dijit.byId("snapshotTempPage").hide();
         var createStorageRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/createTemplate/"
        }); 

        var snapshotGrid = dijit.byId("snapShotGrid");
        var  dataItem = snapshotGrid.selection.getSelected();
        var volumeId;            

        var index = snapshotGrid.selection.selectedIndex;
        var item = snapshotGrid.getItem(index);
        var store = snapshotGrid.store;
        store.setValue(item, 'state', "BackingUp");  

        dojo.forEach(dataItem, function(el) {
            var volumeIdData = el.id;

            dojo.forEach(volumeIdData, function(el) {
                volumeId = el;                    
            });
        });
        var tempName = dijit.byId("snapshotTempName").value;
        var description = dijit.byId("snapshotTempDesc").value;
        var passwordEnabled = dijit.byId("snapshotTempPasswordEnabled").checked;

        createStorageRestStore.add({
            snapshotId : volumeId,
            tempName: tempName,
            description : description,
            passwordEnabled : passwordEnabled
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var snapshotTempStatus = setInterval(function(){SnapShotInfo.volumeTempJob(resultData.jobId, snapshotTempStatus);},3000);                       
                } else {
                    store.setValue(item, 'state', "BackedUp");  
                    registry.byId("userToaster").setContent(translator.common.message.addTemplateError,"error");
                    registry.byId("userToaster").show();
                }

            });
        });
    },
    volumeTempJob : function(jobId, snapshotTempStatus) {
         var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/template/job/"
        }); 

        var snapshotGrid = dijit.byId("snapShotGrid");

        var volumeId;            

        var index = snapshotGrid.selection.selectedIndex;
        var item = snapshotGrid.getItem(index);
        var store = snapshotGrid.store;
        store.setValue(item, 'state', "BackingUp");  

        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(snapshotTempStatus);

                    registry.byId("userToaster").setContent(translator.common.message.addTemplateSuccess,"message");
                    registry.byId("userToaster").show();                    
                    store.setValue(item, 'state', "BackedUp"); 

                    dijit.byId("snapshotStorageName").reset();

                } else if(resultData.jobResult == "Pending") {

                } else {
                    clearInterval(snapshotTempStatus);
                    var index = snapshotGrid.selection.selectedIndex;
                    var item = snapshotGrid.getItem(index);
                    var store = snapshotGrid.store;
                    store.setValue(item, 'state', "BackedUp");  
                    registry.byId("userToaster").setContent(translator.common.message.addTemplateError,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },
    cancelCreateTemp : function() {
        dijit.byId("snapshotTempPage").hide();
    },
    deleteSnapshot : function() {
        dijit.byId("deleteSnapshotPage").hide();
        var createStorageRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/delete/"
        }); 
        var snapshotGrid = dijit.byId("snapShotGrid");
        var  dataItem = snapshotGrid.selection.getSelected();

        var index = snapshotGrid.selection.selectedIndex;
        var item = snapshotGrid.getItem(index);
        var store = snapshotGrid.store;
        store.setValue(item, 'state', "BackingUp");  

        var snapshotId; 
        dojo.forEach(dataItem, function(el) {
            var volumeIdData = el.id;

            dojo.forEach(volumeIdData, function(el) {
                snapshotId = el;

            });
        });
        createStorageRestStore.add({
            snapshotId: snapshotId
        }).then(function(data) { 
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var snapshotDeleteStatus = setInterval(function(){SnapShotInfo.volumeDeleteJob(resultData.jobId, resultData.snapId, snapshotDeleteStatus);},3000);    
                } else {
                    registry.byId("userToaster").setContent(translator.user.snapshot.deleteSnapshotFailed,"error");
                    registry.byId("userToaster").show();

                    store.setValue(item, 'state', "BackedUp");  
                }
            });

        });

    },
    volumeDeleteJob : function(jobId, snapId, snapshotDeleteStatus) {
         var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/deleteJob/"
        }); 

        var snapshotGrid = dijit.byId("snapShotGrid");


        var index = snapshotGrid.selection.selectedIndex;
        var item = snapshotGrid.getItem(index);
        var store = snapshotGrid.store;
        store.setValue(item, 'state', "BackingUp");  

        jobStore.add({
            jobId : jobId,
            snapId: snapId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.jobResult == "OK") {
                    clearInterval(snapshotDeleteStatus);
                    SnapShotInfo.generalSnapshotCount();  
                    registry.byId("userToaster").setContent(translator.user.snapshot.deleted,"message");
                    registry.byId("userToaster").show();                    
                    var items = snapshotGrid.selection.getSelected();
                    dojo.forEach(items, function(selectedItem) {
                        snapshotGrid.store.deleteItem(selectedItem);
                    });
                } else if(resultData.jobResult == "Pending") {

                } else {
                    clearInterval(snapshotDeleteStatus);
                    var index = snapshotGrid.selection.selectedIndex;
                    var item = snapshotGrid.getItem(index);
                    var store = snapshotGrid.store;
                    store.setValue(item, 'state', "BackedUp");  
                    registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },
    cancelStorage : function() {
        dijit.byId("createStoragePage").hide();
    },
    cancelDeleteSnapshot : function() {
        dijit.byId("deleteSnapshotPage").hide();
    },
    cancelSanpshot : function() {
        dijit.byId("createConformationSnapShotPage").hide();
    },
    takeSnapShot : function() {
        var storageGrid = dijit.byId("snapShotStorageGrid");
        var name = dijit.byId("manualSnapshotName").value;

        var formElements = dojo.query("#add-snap-billingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;

        var status = true;
        var firstNode = '';
        var snapWidget = dijit.byId("manualSnapshotName");
        if (snapWidget.validate && !snapWidget.validate()) {
            snapWidget.focus();   
            if (!firstNode) {
                firstNode = el;
            }
            status = false;                
        }

        var  dataItem = storageGrid.selection.getSelected();
        var volumeId; 
        dojo.forEach(dataItem, function(el) {
            var volumeIdData = el.id;
            dojo.forEach(volumeIdData, function(el) {
                volumeId = el;
            });
        });
        if(status == true) {                
            dijit.byId('addManualSnapshotButton').set('style', {display: 'none'});
            dojo.byId("snapshotLoader").style.display = "inline";
            this._snapShotRestStore.add({
            volumeId : volumeId,
            snapshotName: name,
            billingType: billingType
        }).then(function(data) {
            dijit.byId('addManualSnapshotButton').set('style', {display: 'inline'});
            dojo.byId("snapshotLoader").style.display = "none";
            dijit.byId("createConformationSnapShotPage").hide();
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dojo.byId("noSnapMessageBox").style.display = "none";
                    var snapshotJobStatus = setInterval(function(){SnapShotInfo.snapshotJob(resultData.jobId, snapshotJobStatus);},3000); 
                    SnapShotInfo.populateValues();        
                } else {
                    registry.byId("userToaster").setContent(translator.user.snapshot.snapshotError,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    }            
},
snapshotJob : function(jobId, snapshotJobStatus) {             
    var jobStore = new JsonRest({
        target: core.getContextPath()+"/api/snapShot/job/"
    });   

            
    jobStore.add({
        jobId : jobId
    }).then(function(data) {
        dojo.forEach(data, function(resultData) {
            if(resultData.jobResult == "OK") {
                clearInterval(snapshotJobStatus);
                registry.byId("userToaster").setContent(translator.user.snapshot.created,"message");
                registry.byId("userToaster").show();
//                        SnapShotInfo.sendMail(resultData);
                SnapShotInfo.populateValues();
            } else if(resultData.jobResult == "Pending") {

            } else {
                clearInterval(snapshotJobStatus);
                registry.byId("userToaster").setContent(translator.common.message.failed,"erroe");
                registry.byId("userToaster").show();

            }
        });
    });
},
    sendMail : function(resultData) {
         var snapshotMailRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShot/sendEmail/"
        });   

        snapshotMailRestStore.add({
            name: resultData.snapShotName,
            volume: resultData.diskVolumeName,
            state: resultData.snapshotState,
            type: resultData.snapShotType
        })
    },
    getStorageValue : function() {
      var  grid = dijit.byId("snapShotStorageGrid");
      var  dataItem = grid.selection.getSelected();

      dojo.forEach(dataItem, function(el) {
          var volumeIdData = el.id;               
          dojo.forEach(volumeIdData, function(el) {
          dojo.byId("currentStorageId").value = el;                           
      });
  });             
  },
  showSnapShotConfromation : function(volumeId) {           
      var grid = dijit.byId("snapShotStorageGrid").store;
      var name;                        
      grid.fetch( { query: { name: volumeId},  
          onItem: function(item) {  
              dojo.byId("snapDiskCost").innerHTML = "*" + item.currency + item.snapshotCost + "/" + translator.common.hour + "/" + translator.common.gb;        
          }
      }); 
      var configRestStoreBilling = new JsonRest({      
          target: core.getContextPath()+"/api/config/"    
      });

    configRestStoreBilling.query().then(function(resultData) {
        dojo.forEach(resultData, function(config) { 
            if(config.name == "monthly.billing.enabled") {
                if(config.value == "true" || config.value == true) {
                    dojo.byId("add-snap-billingTypeTr").style.display = "none";
                } else {
                    dojo.byId("add-snap-billingTypeTr").style.display = "none";
                }
            } 
        });
    });
               
               
    var status = dojo.byId("snapShotButtonId").value;

    dijit.byId("snapShotStorageGrid").store.fetch( { query: { name: volumeId},  
        onItem: function(item) {  
            var newName = item.tempName;
            dojo.byId("autoSnapDiskName").innerHTML = newName;  
            dojo.byId("diskName").innerHTML = newName;
        }
    });   

    if(status == "manualSnapshotAction") {
        dojo.style(dijit.byId("createConformationSnapShotPage").closeButtonNode,"display","none");
        dijit.byId("manualSnapshotName").reset();
        dijit.byId("createConformationSnapShotPage").show();
        dijit.byId("createSnapShotPage").hide();  
        dijit.byId("ConformationAutoSnapShotPage").hide();


    } else if(status == "autoSnapshotAction") {
         dijit.byId("createSnapShotPage").hide();
         dijit.byId("ConformationAutoSnapShotPage").show();
         var autoSnapshot = new JsonRest({
             target: core.getContextPath()+"/api/snapShotPolicy"
        });
        var autoGrid = dijit.byId("autoSnapshotGrid");    
        var autoSnapshotData = {
            items: []
        };
        var autoSnapshotList = new ItemFileWriteStore({
            data: autoSnapshotData
        });                                               

        var scheduleOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var scheduleList = new ItemFileWriteStore({data: scheduleOption});               

        autoSnapshot.query({volumeReferenceId:volumeId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData) {                         
                    autoSnapshotList.newItem({
                        id: resultData.snapshotPolicyId, 
                        time: resultData.snapshotSchedule, 
                        schedule: resultData.snapshotPolicyIntervalType,
                        timeZone: resultData.snapshotPolicyTimeZone,
                        keep: resultData.maxSnaps,
                        action: resultData.snapshotPolicyId
                    });
                    dojo.byId("autoSnapCollection").style.display = "block";
                } 
            });                          
            autoGrid.setStore(autoSnapshotList);                 
            autoGrid.store.fetch({onComplete: function(items, request) {
                    if(items.length != 0) {
                        if(items.length == 3) {
                            scheduleList.newItem({id: "option", name: "Select"});                                        
                        }  else if(items.length == 2 && items[0].schedule == "DAILY" && items[1].schedule == "MONTHLY"){
                             scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                        } else if(items.length == 2 && items[0].schedule == "DAILY" && items[1].schedule == "WEEKLY") {
                            scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                        } else if(items.length == 2 && items[0].schedule == "MONTHLY" && items[1].schedule == "WEEKLY") {
                            scheduleList.newItem({id: "DAILY", name: "Daily"});
                        } else if(items.length == 1 && items[0].schedule == "DAILY") {
                            scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                            scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                        } else if(items.length == 1 && items[0].schedule == "MONTHLY") {
                           scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                            scheduleList.newItem({id: "DAILY", name: "Daily"});
                        } else if(items.length == 1 && items[0].schedule == "WEEKLY") {
                            scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                            scheduleList.newItem({id: "DAILY", name: "Daily"});
                        } 
                         dijit.byId("autoSchedule").setStore(scheduleList); 
                        } else {
                            scheduleList.newItem({id: "DAILY", name: "Daily"});
                            scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                            scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                            dijit.byId("autoSchedule").setStore(scheduleList); 
                        }
                    }
                });
            });
        }

    },    
    showAutoJobGrid : function() { 
        dojo.byId("snapshotAutoJobList").innerHTML = translator.user.loader.volumeLoading;       
        dojo.byId("snapshotJobListPageContent").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.jobLoading+"</p>";
        var autoJobData = {
            items: []
        };
        
        var snapShotPolicyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/snapShotPolicy/"
        });  
        
        var autoJobList = new ItemFileWriteStore({
            data: autoJobData
        });
        
        var autoJobLayout = [{
            noscroll: true,
            cells: [
                { name: "ID", field: "id", hidden: "true" },
                { name: translator.user.grid.snapshot.autoSnapshot.layout.name, field: "name", datatype:"string",  autoComplete: true, width: "100%"},
                { name: user.grid.snapshot.autoSnapshot.layout.name, field: "diskName",datatype:"string",  autoComplete: true, width: "100%"},
                { name: user.grid.snapshot.autoSnapshot.layout.type, field: "type", datatype:"string",  autoComplete: true, width: "100%"},
                { name: user.grid.snapshot.autoSnapshot.layout.time, field: "time", datatype:"string",  autoComplete: true, width: "100%"},
                { name: user.grid.snapshot.autoSnapshot.layout.action, field: "action",datatype:"string",  autoComplete: true, width: "100%", formatter: function(items)  {
                var widget = new FogPanel.StorageAction({
                    deleteTagClick : function() {
                        SnapShotInfo.deleteAutoJob(items);
                    },
                    updateTagClick : function() {
                        SnapShotInfo.returnAutoSnapshot(items);
                    }
                });
                widget.disableAll();
                widget.enableUpdate();
                return widget;

        }}
            ]
        }
        ];
        
        snapShotPolicyRestStore.query().then(function(data) {
        if(data.length == 0 || data == undefined || data == 'undefined' || data == "" || data == " ") {
            dojo.byId("noAutoSnapMessageBox").style.display = "block";
            dojo.byId("snapshotAutoJobList").innerHTML = "";
            dojo.byId("snapshotJobListPageContent").innerHTML = "";
        } else {
            dojo.byId("noAutoSnapMessageBox").style.display = "none";
            dojo.forEach(data, function(snapshotData) {
                autoJobList.newItem({
                    id:snapshotData.snapshotPolicyId,
                    name: snapshotData.snapshotName, 
                    diskName:snapshotData.volumeName, type: snapshotData.snapshotPolicyIntervalType, time: snapshotData.snapshotSchedule, 
                    action: snapshotData.snapshotPolicyId
                });
            });
            SnapShotInfo.loadAutoJobGrid(autoJobList, autoJobLayout);
        }
            
        });      
        },
        loadAutoJobGrid :function(autoJobList, autoJobLayout) {
            if(dijit.byId("autoJobGrid")) {                 
                dijit.byId("autoJobGrid").destroyRecursive();                      
            }
            dojo.byId("snapshotAutoJobList").innerHTML = "";       
            
            var autoJobGrid = new EnhancedGrid({
            id: 'autoJobGrid',
            store: autoJobList,
            structure: autoJobLayout,
            autoHeight: true,
            plugins: {
                    pagination: {
                        pageSizes: ["3", "5", "10", "All"],
                        description: true,
                        sizeSwitch: true,
                        pageStepper: true,
                        gotoButton: true,
                        /*page step to be displayed*/
                        maxPageStep: 4,
                        /*position of the pagination bar*/
                        position: "bottom"
                    }
                }
           });
        autoJobGrid.placeAt("snapshotAutoJobList");
        autoJobGrid.startup();           
         dojo.byId("snapshotJobListPageContent").innerHTML = translator.user.snapshot.snapshotMessage;   
        },    
        showJob : function() {
          SnapShotInfo.showAutoJobGrid();
          dojo.byId("noAutoSnapMessageBox").style.display = "none";
          dijit.byId("snapshotJobListPage").show();  
        },
        loadSnapGrig :function(snapShotStorageGridList, snapShotStorageLayout, snapshotStatus) {
            dojo.byId("snapShotStorageList").innerHTML = "";
            var snapShotStorageGrid = new EnhancedGrid({
            id: 'snapShotStorageGrid',
            store: snapShotStorageGridList,
            structure: snapShotStorageLayout,
            autoHeight: true,
            plugins: {
                    pagination: {
                        pageSizes: ["3", "5", "10", "All"],
                        description: true,
                        sizeSwitch: true,
                        pageStepper: true,
                        gotoButton: true,
                        /*page step to be displayed*/
                        maxPageStep: 4,
                        /*position of the pagination bar*/
                        position: "bottom"
                    }
                }
        }); 
            if(snapshotStatus == "Manual") {
                snapShotStorageGrid.layout.setColumnVisibility(6, 'false');
                dojo.byId("storagPageInfo").innerHTML = translator.user.snapshot.volumeSnapshotMessage; 
            } else if(snapshotStatus == "Auto") {
                snapShotStorageGrid.layout.setColumnVisibility(6, 'true');
                dojo.byId("storagPageInfo").innerHTML = translator.user.snapshot.volumeAutoSnapshotMessage;
            }
            
            snapShotStorageGrid.placeAt("snapShotStorageList");
            snapShotStorageGrid.startup();            
            snapShotStorageGrid.connect(snapShotStorageGrid, "onCellClick", SnapShotInfo.getStorageValue);
        },
        showStorageGrid : function(snapshotStatus) {        
            if(dijit.byId("snapShotStorageGrid")) {                 
                dijit.byId("snapShotStorageGrid").destroyRecursive();                      
            }        
            dojo.byId("snapShotStorageList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";       
         
            var snapShotStorageGridData = {
                items: []
            };

            var volumeRestStore = new JsonRest({
                target: core.getContextPath()+"/api/volume/"
            });  
            var volumeCountRestStore = new JsonRest({
                target: core.getContextPath()+"/api/volume/count"
            });  

            var snapShotStorageGridList = new ItemFileWriteStore({data: snapShotStorageGridData});
            var snapShotStorageLayout = [
                [
                    {'field': 'id',  'hidden': 'true' },
                    {'name': translator.user.grid.snapshot.autoSnapshot.layout.name, 'field': 'tempName',   'hidden': 'true'},
                    {'field': 'vmId', 'hidden': 'true'},    
                {'name': translator.user.grid.snapshot.layout.volumeName, 'field': 'name', 'width': '100px','formatter': function(item, index) {
                            var grid = dijit.byId("snapShotStorageGrid").store;
                            var name;                        
                            grid.fetch( { query: { name: item},  
                            onItem: function(item) {  
                                var newName = item.tempName;
                                dojo.forEach(newName, function(el) {
                                    name = el;
                                });          
                            }
                        });   
                        var widget = new FogPanel.StorageAction({
                            tagName:name,
                            tooltip: translator.user.snapshot.createSnapshot,
                            deleteTagClick : function() {
                                SnapShotInfo.showSnapShotConfromation(item);
                            }
                        });
                        widget.disableAll();
                        return widget;
                    }
                },
            {'name': translator.user.grid.snapshot.layout.instanceName, 'field': 'vmName', 'width': '100px'},
            {'name': translator.user.grid.snapshot.autoSnapshot.layout.volumeType, 'field': 'type', 'width': '100px'},
                {'name': translator.user.grid.snapshot.autoSnapshot.layout.snapshotConfig, 'field': 'snapshotConfig', 'hidden': 'true'}
            ]
        ];   
        var currentZoneID = dojo.byId("selectedANZoneID").value;        
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            volumeRestStore.query().then(function(data) {
                if(data.length == 0 || data == undefined || data == 'undefined' || data == "" || data == " ") {              
                    dojo.byId("noSnapStorageMessageBox").style.display = "block";
                    dojo.byId("snapShotStorageList").innerHTML = " ";
                } else {               
                    dojo.byId("noSnapStorageMessageBox").style.display = "none";                            
                    dojo.forEach(data, function(storageData) {   
                        if(storageData.type == "ROOT") {                      
                            if(storageData.virtualMachineState == "Stopped" || storageData.virtualMachineState == "Running") {                     
                                snapShotStorageGridList.newItem({                                              
                                    id: storageData.referenceId,
                                    vmId: storageData.virtualMachineReferenceId, 
                                    snapshotConfig:storageData.snapshotPolicy,
                                    name:storageData.referenceId, 
                                    tempName:storageData.name,
                                    vmName: storageData.virtualMachin,
                                    type: storageData.type,
                                    snapshotCost : storageData.miscelCost,
                                    currency : storageData.currency
                                });  
                            }                      
                        } else {                      
                            if(storageData.state == "Ready") {                        
                                snapShotStorageGridList.newItem({                                             
                                    id: storageData.referenceId,
                                    vmId: storageData.virtualMachineReferenceId, 
                                    snapshotConfig:storageData.snapshotPolicy,
                                    name:storageData.referenceId, 
                                    tempName:storageData.name,
                                    vmName: storageData.virtualMachin,
                                    type: storageData.type,
                                    snapshotCost : storageData.miscelCost,
                                    currency : storageData.currency
                                });  
                            }     
                        }              
                    }); 
                    SnapShotInfo.loadSnapGrig(snapShotStorageGridList, snapShotStorageLayout, snapshotStatus);              
                }          
            });
        } else {
            volumeRestStore.query({zoneReferenceId: currentZoneID}).then(function(data) {
                if(data.length == 0 || data == undefined || data == 'undefined' || data == "" || data == " ") {              
                    dojo.byId("noSnapStorageMessageBox").style.display = "block";
                    dojo.byId("snapShotStorageList").innerHTML = " ";
                } else {               
                    dojo.byId("noSnapStorageMessageBox").style.display = "none";                            
                    dojo.forEach(data, function(storageData) {   
                        if(storageData.type == "ROOT") {                      
                            if(storageData.virtualMachineState == "Stopped" || storageData.virtualMachineState == "Running") {                     
                                snapShotStorageGridList.newItem({                                              
                                    id: storageData.referenceId,
                                    vmId: storageData.virtualMachineReferenceId, 
                                    snapshotConfig:storageData.snapshotPolicy,
                                    name:storageData.referenceId, 
                                    tempName:storageData.name,
                                    vmName: storageData.virtualMachin,
                                    type: storageData.type,
                                    snapshotCost : storageData.miscelCost,
                                    currency : storageData.currency
                                });  
                            }                      
                        } else {                      
                            if(storageData.state == "Ready") {                        
                                snapShotStorageGridList.newItem({                                             
                                    id: storageData.referenceId,
                                    vmId: storageData.virtualMachineReferenceId, 
                                    snapshotConfig:storageData.snapshotPolicy,
                                    name:storageData.referenceId, 
                                    tempName:storageData.name,
                                    vmName: storageData.virtualMachin,
                                    type: storageData.type,
                                    snapshotCost : storageData.miscelCost,
                                    currency : storageData.currency
                                });  
                            }     
                        }              
                    }); 
                    SnapShotInfo.loadSnapGrig(snapShotStorageGridList, snapShotStorageLayout, snapshotStatus);              
                }          
            });
        }  
                
      return dojo.byId("snapShotStatus").value;       
  },
  showAddSnapShot : function(currentId) {
            dojo.byId("noSnapStorageMessageBox").style.display = "none";
            dijit.byId("createSnapShotPage").show();
            dojo.byId("snapShotButtonId").value = currentId;            
            dojo.byId("storagPageInfo").innerHTML = "";             
            SnapShotInfo.showStorageGrid("Manual");
        },
        showAddAutoSnapShot : function(currentId) {
            dojo.byId("noSnapStorageMessageBox").style.display = "none";
            dojo.byId("snapShotButtonId").value = currentId;
//            SnapShotInfo.populateValues();          
            
            dijit.byId("autoSchedule").setAttribute('disabled', false);
            dijit.byId("autoSnapshotForm").reset();
            dijit.byId('updateAutoSnapButton').set('style', { display: 'none' });
            dijit.byId('addAutoSnapButton').set('style', { display: 'block' });
             SnapShotInfo.showStorageGrid("Auto");
            dojo.byId("storagPageInfo").innerHTML = "";
            dijit.byId("createSnapShotPage").show();           
        },
        addAutoSnapshot : function() {
            var autoSnapshot = new JsonRest({
                target: core.getContextPath()+"/api/snapShotPolicy/"
            }); 
            var snapshotName = dijit.byId("autoSnapshotName").value;
            var schedule = dijit.byId("autoSchedule").value;
            var scheduleHr = dijit.byId("scheduleHr").value;
            var scheduleMin = dijit.byId("scheduleMin").value;
            var timeZone = dijit.byId("snapshotTimeZone").value;
            var keep = dijit.byId("snapshotKeep").value;
            var dayValue  = dijit.byId("snapshotDay").value;
            var dayName  = dijit.byId("snapshotDay").get("displayedValue");
            var month  = dijit.byId("snapshotMonth").value;
            var volumeId = dijit.byId("snapShotStorageGrid").value;
            var storageGrid = dijit.byId("snapShotStorageGrid");
            var dataItem = storageGrid.selection.getSelected();
            var timeZoneName =  dijit.byId("snapshotTimeZone").get("displayedValue");
            
            var node = dojo.byId("autoSnapshotForm");
            var widget = dijit.registry.findWidgets(node);        
        
            dojo.forEach(widget, function(el) {
                if (el.validate && !el.validate()) {
                    el.focus();             
                    if (!firstNode) {
                        firstNode = el;
                    }
                }
            });
            var scheduleOption = {
                identifier: 'id',
                label: 'name',
                items: []
            };
            
            var scheduleList = new ItemFileWriteStore({data: scheduleOption});
            
            dojo.forEach(dataItem, function(el) {
                
                var volumeIdData = el.id;               
                dojo.forEach(volumeIdData, function(el) {
                    volumeId = el;
                });
            });
            dijit.byId('addAutoSnapButton').set('style', {display: 'none'});
            dojo.byId("snapLoader").style.display = "inline";
            autoSnapshot.add({
                snapshotName: snapshotName,
                volumeId: volumeId,
                intervaltype: schedule,
                scheduleHour: scheduleHr,
                scheduleMin: scheduleMin,
                timeZone: timeZone,
                timeZoneName : timeZoneName,
                maxsnaps: keep,
                dayValue: dayValue,
                dayName: dayName,
                month: month
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {      
                    dijit.byId('addAutoSnapButton').set('style', {display: 'inline'});
                    dojo.byId("snapLoader").style.display = "none";
                    if(resultData.result == "OK") {
                        registry.byId("userToaster").setContent(translator.user.snapshot.snapshotSuccess,"message");
                        registry.byId("userToaster").show();
//                        SnapShotInfo.sendMail(resultData);
                        SnapShotInfo.generalSnapshotCount();  
                        dojo.byId("noSnapMessageBox").style.display = "none";
                        var autoGrid = dijit.byId("autoSnapshotGrid"); 
                        autoGrid.store.newItem({
                            id: resultData.snapshotPolicyId, 
                            time: resultData.snapshotSchedule, 
                            schedule: resultData.snapshotPolicyIntervalType,
                            timeZone: resultData.snapshotPolicyTimeZone,
                            keep: resultData.maxSnaps,
                            action: resultData.snapshotPolicyId
                        });
                        dojo.byId("autoSnapCollection").style.display = "block"; 
                        
                        autoGrid.store.fetch({onComplete: function(items, request) {
                                if(items.length != 0) {
                                    if(items.length == 3) {
                                        scheduleList.newItem({id: "option", name: "Select"});                                    
                                    }  else if(items.length == 2 && items[0].schedule == "DAILY" && items[1].schedule == "MONTHLY") {
                                        scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                                    } else if(items.length == 2 && items[0].schedule == "DAILY" && items[1].schedule == "WEEKLY") {
                                        scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                                    }  else if(items.length == 2 && items[0].schedule == "WEEKLY" && items[1].schedule == "MONTHLY") {
                                        scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                                    } else if(items.length == 2 && items[0].schedule == "MONTHLY" && items[1].schedule == "WEEKLY") {
                                        scheduleList.newItem({id: "DAILY", name: "Daily"});
                                    } else if(items.length == 1 && items[0].schedule == "DAILY") {
                                        scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                                        scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                                    } else if(items.length == 1 && items[0].schedule == "MONTHLY") {
                                       scheduleList.newItem({id: "WEEKLY", name: "Weekly"});
                                        scheduleList.newItem({id: "DAILY", name: "Daily"});
                                    } else if(items.length == 1 && items[0].schedule == "WEEKLY") {
                                        scheduleList.newItem({id: "MONTHLY", name: "Monthly"});
                                        scheduleList.newItem({id: "DAILY", name: "Daily"});
                                    } 
                                     dijit.byId("autoSchedule").setStore(scheduleList); 
                                    }
                        }
                        });                                    
                        dijit.byId("autoSnapshotName").reset();
                        dijit.byId("snapshotKeep").reset();
                        
                    } else {
                        registry.byId("userToaster").setContent(translator.user.snapshot.snapshotError,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("autoSnapshotName").reset();
                        dijit.byId("snapshotKeep").reset();
                    }
                });
            });
//       
        },
        showShedule : function(currentValue) {
            var currentStatus = currentValue.value;
            if(currentStatus == "DAILY") {
                dojo.byId("weeklyList").style.display = "none";
                dojo.byId("monthlyList").style.display = "none";
//                dijit.byId("snapshotDay").clear();
//                dijit.byId("snapshotMonth").clear();
                
            } else if(currentStatus == "WEEKLY") {
                dojo.byId("weeklyList").style.display = "block";
                dojo.byId("monthlyList").style.display = "none";
//                 dijit.byId("snapshotMonth").clear();
                
                
            } else if(currentStatus == "MONTHLY") {
                dojo.byId("monthlyList").style.display = "block";
                dojo.byId("weeklyList").style.display = "none";
//                dijit.byId("snapshotDay").clear();
            
            }
        }
    };

window.StorageInfo = StorageInfo;
window.SnapShotInfo = SnapShotInfo;
window.VMSnapshotInfo = VMSnapshotInfo;
window.UserInstances = UserInstances;
window.CurrentInstanceInfo = CurrentInstanceInfo;
window.UserSecurityGroupInfo = UserSecurityGroupInfo;
window.CreateVMInfo = CreateVMInfo;
window.AddStorageInfo = AddStorageInfo;
window.UserSecurityIngressInfo = UserSecurityIngressInfo;
window.UserSecurityEgressInfo = UserSecurityEgressInfo;
window.allCountList = allCountList;
window.CurrentVMDetailInfo = CurrentVMDetailInfo;
window.CurrentVMStorageInfo = CurrentVMStorageInfo;
window.CurrentVMNicInfo = CurrentVMNicInfo;
window.CurrentVMSecondaryIP = CurrentVMSecondaryIP;
window.RateCardInfo = RateCardInfo;


