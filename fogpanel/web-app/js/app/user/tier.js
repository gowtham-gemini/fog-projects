Window.VMTierInfo = VMTierInfo;
Window.TierInfo = TierInfo;
Window.TierPortforwardingInfo = TierPortforwardingInfo;
Window.TierPublicLoadBalancerInfo = TierPublicLoadBalancerInfo;
Window.TierInternalLoadBalancerInfo = TierInternalLoadBalancerInfo;
Window.TierStatiscNatInfo = TierStatiscNatInfo;
Window.ListTierInfo = ListTierInfo;
Window.ListAllVPCVMInfo = ListAllVPCVMInfo;
Window.ListAllPublicLBInfo = ListAllPublicLBInfo;
Window.ListAllInternalLBInfo = ListAllInternalLBInfo;
Window.ListAllPublicIPInfo = ListAllPublicIPInfo;
Window.ListAllStaticNatInfo = ListAllStaticNatInfo;
Window.AddVPCInfo = AddVPCInfo;
Window.AddInternalLBInfo = AddInternalLBInfo;

"use strict";
require([
    "dojo", 
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js", 
    "dijit/dijit",
    "dojo/dom-class",
    "FogPanel/VPNStatus",
    "dojo/dom-construct",
    "dojo/store/JsonRest",
    "dojo/query",
    "dijit/registry",    
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dojox/calendar/Calendar",
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
    "dijit/Dialog"
],
function(dojo, i18n, translator, dijit, VPNStatus, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
ItemFileWriteStore, Memory, Observable, DataGrid, EnhancedGrid, Calendar, ContentPane, Source, MultiSelect, dom, win) {             
    window.query = query;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.VPNStatus = VPNStatus;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Observable = Observable;
    window.Memory = Memory;
    window.Select = Select;
    window.ContentPane = ContentPane;
    window.DataGrid = DataGrid;
    window.EnhancedGrid = EnhancedGrid;
    window.Calendar = Calendar;
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;         
    controller ({
        name: "tier",
        module: "user",
        filePath: "/js/app/user/tier.js",
        layout: {
            name: "tier",
            containerId: "content"
        },	
        scaffold: false
    },
    {
        "list": action(function() {            
            if(dijit.byId("generalTierReplaceACLDialog")) {
                dijit.byId("generalTierReplaceACLDialog").destroyRecursive();
            }
            if(dijit.byId("generalListTierLoader")) {
                dijit.byId("generalListTierLoader").destroyRecursive();
            }
            if(dijit.byId("generalTierRestartDialog")) {
                dijit.byId("generalTierRestartDialog").destroyRecursive();
            }
            if(dijit.byId("vpcGeneralEditTierDialog")) {
                dijit.byId("vpcGeneralEditTierDialog").destroyRecursive();
            }
            if(dijit.byId("generalTierEditConformDialog")) {
                dijit.byId("generalTierEditConformDialog").destroyRecursive();
            }
            if(dijit.byId("generalTierDeleteDialog")) {
                dijit.byId("generalTierDeleteDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoDeleteDialog")) {
                dijit.byId("tierInfoDeleteDialog").destroyRecursive();
            }            
            
            if(dijit.byId("vpcGeneralAddTierDialog")) {
                dijit.byId("vpcGeneralAddTierDialog").destroyRecursive();
            }         
            if(dijit.byId("vpcTierListLoader")) {
                dijit.byId("vpcTierListLoader").destroyRecursive();
            }                                 
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            core.ui.loadTemplate("listTier", core.ui.getContentId());   
            ListTierInfo.populateValues();
        }),
         "addTier": action(function() {             
             if(dijit.byId("userCreateTierForm")) {
                 dijit.byId("userCreateTierForm").destroyRecursive();
             }              
             if(dijit.byId("addTierLoader")) {
                 dijit.byId("addTierLoader").destroyRecursive();
             } 
             core.ui.loadTemplate("addTier", core.ui.getContentId());   
             AddVPCInfo.populateValues();
         }),
        "instance": action(function() {
            if(dijit.byId("generalStartTierVMDialog")) {
                dijit.byId("generalStartTierVMDialog").destroyRecursive();
            }
            if(dijit.byId("stopGenereralTierDialog")) {
                dijit.byId("stopGenereralTierDialog").destroyRecursive();
            }
            if(dijit.byId("rebootGeneralVMTierDialog")) {
                dijit.byId("rebootGeneralVMTierDialog").destroyRecursive();
            }
            if(dijit.byId("vmGeneralTierRestoreDialog")) {
                dijit.byId("vmGeneralTierRestoreDialog").destroyRecursive();
            }
            if(dijit.byId("deleteGeneralTierVMDialog")) {
                dijit.byId("deleteGeneralTierVMDialog").destroyRecursive();
            }
            if(dijit.byId("generalVMConfirmDialog")) {
                dijit.byId("generalVMConfirmDialog").destroyRecursive();
            }
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
//            
            core.ui.loadTemplate("listVPCVM", core.ui.getContentId());   
            ListAllVPCVMInfo.populateValues();
        }),    
        "publicLB" : action(function () {
            if(dijit.byId("generalVpcAcquireIpDialog")) {
                dijit.byId("generalVpcAcquireIpDialog").destroyRecursive();
            } 
            if(dijit.byId("generalPublicLBContent")) {
                dijit.byId("generalPublicLBContent").destroyRecursive();
            } 
            
            if(dijit.byId("generalPublicLBLoader")) {
                dijit.byId("generalPublicLBLoader").destroyRecursive();
            } 
            
            
            core.ui.loadTemplate("vpcPublicLBGeneralInfo", core.ui.getContentId());   
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            ListAllPublicLBInfo.populateValues();
        }),
        "internalLB" : action(function () {
            if(dijit.byId("addGeneralInternalLbDialog")) {
                dijit.byId("addGeneralInternalLbDialog").destroyRecursive();
            }
            if(dijit.byId("generalInternalLBLoader")) {
                dijit.byId("generalInternalLBLoader").destroyRecursive();
            }
            
            core.ui.loadTemplate("vpcInternalLBGeneralInfo", core.ui.getContentId());   
//            var currentVPC = dojo.byId("selectedANVPCID").value;
////            VPCMenuInfo.populateValues(currentVPC);
            ListAllInternalLBInfo.populateValues();
        }),
        "addInternalLB" : action(function () {
            if(dijit.byId("addGeneralInternalLBPageForm")) {
                dijit.byId("addGeneralInternalLBPageForm").destroyRecursive();
            }
            if(dijit.byId("addInternalLBLoader")) {
                dijit.byId("addInternalLBLoader").destroyRecursive();
            }
            core.ui.loadTemplate("addInternalLBGeneral", core.ui.getContentId());
            AddInternalLBInfo.populateValues();
        }),
        "publicIP" : action(function () {
            if(dijit.byId("vpcGenerealReleaseIpDialog")) {
                dijit.byId("vpcGenerealReleaseIpDialog").destroyRecursive();
            }
            if(dijit.byId("vpcGenerealIpEnableStaticNatDialog")) {
                dijit.byId("vpcGenerealIpEnableStaticNatDialog").destroyRecursive();
            }
            if(dijit.byId("vpcGenerealIPPageDisableStaticNatDialog")) {
                dijit.byId("vpcGenerealIPPageDisableStaticNatDialog").destroyRecursive();
            }
            if(dijit.byId("viewGeneralIpLoader")) {
                dijit.byId("viewGeneralIpLoader").destroyRecursive();
            }       
            
            if(dijit.byId("generalPublicIPAcquireDialog")) {
                dijit.byId("generalPublicIPAcquireDialog").destroyRecursive();
            } 
            if(dijit.byId("generalPublicIPContent")) {
                dijit.byId("generalPublicIPContent").destroyRecursive();
            } 
            
            if(dijit.byId("generalPublicIPLoader")) {
                dijit.byId("generalPublicIPLoader").destroyRecursive();
            }
            
            core.ui.loadTemplate("vpcPublicIPGeneralnfo", core.ui.getContentId());   
//            var currentVPC = dojo.byId("selectedANVPCID").value;
////            VPCMenuInfo.populateValues(currentVPC);
            ListAllPublicIPInfo.populateValues();            
        }),
        "staticNat" : action(function () {
            core.ui.loadTemplate("vpcStativNatIPGeneralnfo", core.ui.getContentId());   
//            var currentVPC = dojo.byId("selectedANVPCID").value;
////            VPCMenuInfo.populateValues(currentVPC);
            ListAllStaticNatInfo.populateValues();            
        }),                
        "view": action(function(id) {
            
            if(dijit.byId("tierLoader")) {
                dijit.byId("tierLoader").destroyRecursive();
            }
            if(dijit.byId("tierTabCointainer")) {
                dijit.byId("tierTabCointainer").destroyRecursive();
            }
            if(dijit.byId("tierDetailContainer")) {
                dijit.byId("tierDetailContainer").destroyRecursive();
            }
            if(dijit.byId("tierStaticNat")) {
                dijit.byId("tierStaticNat").destroyRecursive();
            }
            if(dijit.byId("tierPortForwarding")) {
                dijit.byId("tierPortForwarding").destroyRecursive();
            }
            if(dijit.byId("tierPublicLoadBalancer")) {
                dijit.byId("tierPublicLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("tierInternalLoadBalancer")) {
                dijit.byId("tierInternalLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("startTierVMDialog")) {
                dijit.byId("startTierVMDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoReplaceACLDialog")) {
                dijit.byId("tierInfoReplaceACLDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoRestartDialog")) {
                dijit.byId("tierInfoRestartDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoDeleteDialog")) {
                dijit.byId("tierInfoDeleteDialog").destroyRecursive();
            }            
            if(dijit.byId("tierInfoEditDialog")) {
                dijit.byId("tierInfoEditDialog").destroyRecursive();
            }            
            if(dijit.byId("tierInfoEditConformDialog")) {
                dijit.byId("tierInfoEditConformDialog").destroyRecursive();
            }            
            
            
            if(dijit.byId("stopTierDialog")) {
                dijit.byId("stopTierDialog").destroyRecursive();
            }
            if(dijit.byId("rebootVMTierDialog")) {
                dijit.byId("rebootVMTierDialog").destroyRecursive();
            }
            if(dijit.byId("vmTierRestoreDialog")) {
                dijit.byId("vmTierRestoreDialog").destroyRecursive();
            }
            if(dijit.byId("deleteTierVMDialog")) {
                dijit.byId("deleteTierVMDialog").destroyRecursive();
            }     
            if(dijit.byId("addInternalLbDialog")) {
                dijit.byId("addInternalLbDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBDeleteLDialog")) {
                dijit.byId("internalLBDeleteLDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBRemoveVMDialog")) {
                dijit.byId("internalLBRemoveVMDialog").destroyRecursive();
            }                                       
            core.ui.loadTemplate("viewTier", core.ui.getContentId());   
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            TierInfo.init(id);            
        }),
        "viewInternalLB" : action(function (id) {
            if(dijit.byId("tierLoader")) {
                dijit.byId("tierLoader").destroyRecursive();
            }
            if(dijit.byId("tierTabCointainer")) {
                dijit.byId("tierTabCointainer").destroyRecursive();
            }
            if(dijit.byId("tierDetailContainer")) {
                dijit.byId("tierDetailContainer").destroyRecursive();
            }
            if(dijit.byId("tierPortForwarding")) {
                dijit.byId("tierPortForwarding").destroyRecursive();
            }
            if(dijit.byId("tierPublicLoadBalancer")) {
                dijit.byId("tierPublicLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("tierInternalLoadBalancer")) {
                dijit.byId("tierInternalLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("startTierVMDialog")) {
                dijit.byId("startTierVMDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoReplaceACLDialog")) {
                dijit.byId("tierInfoReplaceACLDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoRestartDialog")) {
                dijit.byId("tierInfoRestartDialog").destroyRecursive();
            }
            if(dijit.byId("stopTierDialog")) {
                dijit.byId("stopTierDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoDeleteDialog")) {
                dijit.byId("tierInfoDeleteDialog").destroyRecursive();
            }  
            if(dijit.byId("tierInfoEditConformDialog")) {
                dijit.byId("tierInfoEditConformDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoEditDialog")) {
                dijit.byId("tierInfoEditDialog").destroyRecursive();
            } 
            if(dijit.byId("rebootVMTierDialog")) {
                dijit.byId("rebootVMTierDialog").destroyRecursive();
            }
            if(dijit.byId("vmTierRestoreDialog")) {
                dijit.byId("vmTierRestoreDialog").destroyRecursive();
            }
            if(dijit.byId("deleteTierVMDialog")) {
                dijit.byId("deleteTierVMDialog").destroyRecursive();
            }     
            if(dijit.byId("addInternalLbDialog")) {
                dijit.byId("addInternalLbDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBDeleteLDialog")) {
                dijit.byId("internalLBDeleteLDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBRemoveVMDialog")) {
                dijit.byId("internalLBRemoveVMDialog").destroyRecursive();
            }                                       
            core.ui.loadTemplate("viewTier", core.ui.getContentId());   
            TierInfo.init(id);  
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            setTimeout(function () {
                var mainTab = dijit.byId("tierTabCointainer"); //Tr
                var subIpTab = dijit.byId("tierInternalLoadBalancer"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },500);
        }),
        "publicLBIPList" : action(function (id) {
            if(dijit.byId("tierLoader")) {
                dijit.byId("tierLoader").destroyRecursive();
            }
            if(dijit.byId("tierTabCointainer")) {
                dijit.byId("tierTabCointainer").destroyRecursive();
            }
            if(dijit.byId("tierDetailContainer")) {
                dijit.byId("tierDetailContainer").destroyRecursive();
            }
            if(dijit.byId("tierPortForwarding")) {
                dijit.byId("tierPortForwarding").destroyRecursive();
            }
            if(dijit.byId("tierPublicLoadBalancer")) {
                dijit.byId("tierPublicLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("tierInternalLoadBalancer")) {
                dijit.byId("tierInternalLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("startTierVMDialog")) {
                dijit.byId("startTierVMDialog").destroyRecursive();
            }
            if(dijit.byId("stopTierDialog")) {
                dijit.byId("stopTierDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoReplaceACLDialog")) {
                dijit.byId("tierInfoReplaceACLDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoRestartDialog")) {
                dijit.byId("tierInfoRestartDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoDeleteDialog")) {
                dijit.byId("tierInfoDeleteDialog").destroyRecursive();
            }  
            if(dijit.byId("tierInfoEditConformDialog")) {
                dijit.byId("tierInfoEditConformDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoEditDialog")) {
                dijit.byId("tierInfoEditDialog").destroyRecursive();
            } 
            if(dijit.byId("rebootVMTierDialog")) {
                dijit.byId("rebootVMTierDialog").destroyRecursive();
            }
            if(dijit.byId("vmTierRestoreDialog")) {
                dijit.byId("vmTierRestoreDialog").destroyRecursive();
            }
            if(dijit.byId("deleteTierVMDialog")) {
                dijit.byId("deleteTierVMDialog").destroyRecursive();
            }     
            if(dijit.byId("addInternalLbDialog")) {
                dijit.byId("addInternalLbDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBDeleteLDialog")) {
                dijit.byId("internalLBDeleteLDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBRemoveVMDialog")) {
                dijit.byId("internalLBRemoveVMDialog").destroyRecursive();
            }                                       
            core.ui.loadTemplate("viewTier", core.ui.getContentId());   
            TierInfo.init(id);  
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            setTimeout(function () {
                var mainTab = dijit.byId("tierTabCointainer"); //Tr
                var subIpTab = dijit.byId("tierPublicLoadBalancer"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },500);
        }),
        "staticNatList" : action(function (id) {
            if(dijit.byId("tierLoader")) {
                dijit.byId("tierLoader").destroyRecursive();
            }
            if(dijit.byId("tierTabCointainer")) {
                dijit.byId("tierTabCointainer").destroyRecursive();
            }
            if(dijit.byId("tierDetailContainer")) {
                dijit.byId("tierDetailContainer").destroyRecursive();
            }
            if(dijit.byId("tierPortForwarding")) {
                dijit.byId("tierPortForwarding").destroyRecursive();
            }
            if(dijit.byId("tierPublicLoadBalancer")) {
                dijit.byId("tierPublicLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("tierInternalLoadBalancer")) {
                dijit.byId("tierInternalLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("startTierVMDialog")) {
                dijit.byId("startTierVMDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoReplaceACLDialog")) {
                dijit.byId("tierInfoReplaceACLDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoDeleteDialog")) {
                dijit.byId("tierInfoDeleteDialog").destroyRecursive();
            }            
            if(dijit.byId("tierInfoEditDialog")) {
                dijit.byId("tierInfoEditDialog").destroyRecursive();
            } 
            if(dijit.byId("tierInfoRestartDialog")) {
                dijit.byId("tierInfoRestartDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoEditConformDialog")) {
                dijit.byId("tierInfoEditConformDialog").destroyRecursive();
            }
            if(dijit.byId("stopTierDialog")) {
                dijit.byId("stopTierDialog").destroyRecursive();
            }
            if(dijit.byId("rebootVMTierDialog")) {
                dijit.byId("rebootVMTierDialog").destroyRecursive();
            }
            if(dijit.byId("vmTierRestoreDialog")) {
                dijit.byId("vmTierRestoreDialog").destroyRecursive();
            }
            if(dijit.byId("deleteTierVMDialog")) {
                dijit.byId("deleteTierVMDialog").destroyRecursive();
            }     
            if(dijit.byId("addInternalLbDialog")) {
                dijit.byId("addInternalLbDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBDeleteLDialog")) {
                dijit.byId("internalLBDeleteLDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBRemoveVMDialog")) {
                dijit.byId("internalLBRemoveVMDialog").destroyRecursive();
            }                                       
            core.ui.loadTemplate("viewTier", core.ui.getContentId());   
            TierInfo.init(id);  
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            setTimeout(function () {
                var mainTab = dijit.byId("tierTabCointainer"); //Tr
                var subIpTab = dijit.byId("tierStaticNat"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },500);
        }),
        "portForwardingList" : action(function (id) {
            if(dijit.byId("tierLoader")) {
                dijit.byId("tierLoader").destroyRecursive();
            }
            if(dijit.byId("tierTabCointainer")) {
                dijit.byId("tierTabCointainer").destroyRecursive();
            }
            if(dijit.byId("tierDetailContainer")) {
                dijit.byId("tierDetailContainer").destroyRecursive();
            }
            if(dijit.byId("tierPortForwarding")) {
                dijit.byId("tierPortForwarding").destroyRecursive();
            }
            if(dijit.byId("tierPublicLoadBalancer")) {
                dijit.byId("tierPublicLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("tierInternalLoadBalancer")) {
                dijit.byId("tierInternalLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("startTierVMDialog")) {
                dijit.byId("startTierVMDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoReplaceACLDialog")) {
                dijit.byId("tierInfoReplaceACLDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoRestartDialog")) {
                dijit.byId("tierInfoRestartDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoDeleteDialog")) {
                dijit.byId("tierInfoDeleteDialog").destroyRecursive();
            } 
            if(dijit.byId("tierInfoEditConformDialog")) {
                dijit.byId("tierInfoEditConformDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoEditDialog")) {
                dijit.byId("tierInfoEditDialog").destroyRecursive();
            } 
            if(dijit.byId("stopTierDialog")) {
                dijit.byId("stopTierDialog").destroyRecursive();
            }
            if(dijit.byId("rebootVMTierDialog")) {
                dijit.byId("rebootVMTierDialog").destroyRecursive();
            }
            if(dijit.byId("vmTierRestoreDialog")) {
                dijit.byId("vmTierRestoreDialog").destroyRecursive();
            }
            if(dijit.byId("deleteTierVMDialog")) {
                dijit.byId("deleteTierVMDialog").destroyRecursive();
            }     
            if(dijit.byId("addInternalLbDialog")) {
                dijit.byId("addInternalLbDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBDeleteLDialog")) {
                dijit.byId("internalLBDeleteLDialog").destroyRecursive();
            }   
            if(dijit.byId("internalLBRemoveVMDialog")) {
                dijit.byId("internalLBRemoveVMDialog").destroyRecursive();
            }                                       
            core.ui.loadTemplate("viewTier", core.ui.getContentId());   
            TierInfo.init(id);  
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            setTimeout(function () {
                var mainTab = dijit.byId("tierTabCointainer"); //Tr
                var subIpTab = dijit.byId("tierPortForwarding"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },500);
        }),
        "instanceList" : action(function (id) {
            if(dijit.byId("tierLoader")) {
                dijit.byId("tierLoader").destroyRecursive();
            }
            if(dijit.byId("tierTabCointainer")) {
                dijit.byId("tierTabCointainer").destroyRecursive();
            }
            if(dijit.byId("tierDetailContainer")) {
                dijit.byId("tierDetailContainer").destroyRecursive();
            }
            if(dijit.byId("startTierVMDialog")) {
                dijit.byId("startTierVMDialog").destroyRecursive();
            }
            if(dijit.byId("stopTierDialog")) {
                dijit.byId("stopTierDialog").destroyRecursive();
            }            
            if(dijit.byId("rebootVMTierDialog")) {
                dijit.byId("rebootVMTierDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoRestartDialog")) {
                dijit.byId("tierInfoRestartDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoReplaceACLDialog")) {
                dijit.byId("tierInfoReplaceACLDialog").destroyRecursive();
            }
            if(dijit.byId("vmTierRestoreDialog")) {
                dijit.byId("vmTierRestoreDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoEditConformDialog")) {
                dijit.byId("tierInfoEditConformDialog").destroyRecursive();
            }
            if(dijit.byId("tierInfoDeleteDialog")) {
                dijit.byId("tierInfoDeleteDialog").destroyRecursive();
            }            
            if(dijit.byId("tierInfoEditDialog")) {
                dijit.byId("tierInfoEditDialog").destroyRecursive();
            } 
            if(dijit.byId("deleteTierVMDialog")) {
                dijit.byId("deleteTierVMDialog").destroyRecursive();
            } 
            if(dijit.byId("addInternalLbDialog")) {
                dijit.byId("addInternalLbDialog").destroyRecursive();
            }  
            if(dijit.byId("internalLBDeleteLDialog")) {
                dijit.byId("internalLBDeleteLDialog").destroyRecursive();
            }  
            if(dijit.byId("internalLBRemoveVMDialog")) {
                dijit.byId("internalLBRemoveVMDialog").destroyRecursive();
            } 
            
            core.ui.loadTemplate("viewTier", core.ui.getContentId());   
            TierInfo.init(id);     
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            setTimeout(function () {
                var mainTab = dijit.byId("tierTabCointainer"); //Tr
                var subIpTab = dijit.byId("tierInstanceContentPane"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },500);
        })
    });
});
var ListAllStaticNatInfo = {
    populateValues : function () {
        if(dijit.byId("generalStaticNatGrid")) {                                    
            dijit.byId("generalStaticNatGrid").destroyRecursive();                    
        }
        dojo.byId("vpcGeneralStaicNat").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var LBData = {
            items: []
        };                 
        var publicIPList = new ItemFileWriteStore({data: LBData}); 
        var publicIPLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ips, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if (data.isSourceNat == true || data.isSourceNat == "true") {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if(data.isStaticNat == true || data.isStaticNat == "true") {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        }
                    }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vpc.name, 'field': 'vpc', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.tier, 'field': 'tier', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},                
                {'name': translator.common.state, 'field': 'state', 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
               
            ]
        ];
         var ipRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/overall/listIP"
         });
         
         var vpcRestStore = new JsonRest({
             target: core.getContextPath()+"/api/vpc"
         });
         var currentMainZoneID = ""  
         var currentMainVPC = ""

        if(dojo.byId("selectedANZoneID").value === "All" || dojo.byId("selectedANZoneID").value === " " || dojo.byId("selectedANZoneID").value === "undefind" || dojo.byId("selectedANZoneID").value === "") { 
            currentMainZoneID = "";
        } else {
            currentMainZoneID = dojo.byId("selectedANZoneID").value;            
        }

        if(dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }
         ipRestStore.query({zoneReferenceId:currentMainZoneID, vpcId:currentMainVPC, isStatic: true}).then(function(data) {
             if(data.length === 0 || data === "undefined" || data[0].ip === "undefined" || data === "" || data === " ") {
                 dojo.byId("noStaticNatIPMsg").style.display = "block";
                 dojo.byId("vpcGeneralStaicNat").innerHTML = "";
                 
                 vpcRestStore.query({referenceId: currentMainVPC}).then(function (dataCheck) {
                    if(dataCheck.length === 0 || dataCheck[0].localizedMessage) {
                        dojo.byId("noPublicIPMsg").innerHTML = translator.common.noVPCandLBMsg;
                    } else {
                        dojo.byId("noPublicIPMsg").innerHTML = translator.common.noLBMsgInfo;
                    }
                });
                 
             } else {
                 dojo.byId("noStaticNatIPMsg").style.display = "none";                 
                 dojo.forEach(data, function(resultData) {                     
                     publicIPList.newItem({
                         id: resultData.referenceId,
                        ip: {ip: resultData.ip, id: resultData.referenceId, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat},
                        zone: resultData.zone,
                        state: resultData.state,
                        vpc: resultData.vpc,
                        tier : resultData.network
                     });                     
                 });                  
                 dojo.byId("vpcGeneralStaicNat").innerHTML = "";
                 var ipGrid = new EnhancedGrid({
                    id: 'generalStaticNatGrid',
                    "class" : "span12",
                    store: publicIPList,
                    structure: publicIPLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                ipGrid.placeAt("vpcGeneralStaicNat");
                ipGrid.startup();
              }                           
         });    
    }
}

var ListAllPublicIPInfo = {
    'getTierVMList' : function(data) {  
        
        if(data.value === "option" || data.value === "" || data.value === undefined) {
            var noVMOptions = {
                identifier: 'id',
                label: 'name',
                items: [{id: "option" , name : translator.common.selectVM}]                
            };            
            var noVmList = new ItemFileWriteStore({data: noVMOptions});
            dijit.byId("vpcGeneralTierVmWidget").set("store", noVmList);
            dijit.byId("vpcGeneralTierVmWidget").set("value", "option");
        } else {        
            var neworkid;      
            data.store.fetch({query: {id: data.value},
                onItem: function(item) {
                    neworkid=item.networkId;
                }            
            });                             
            var networkVMListRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/vm/list/"
            });

            var vmOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            };

            var vmList = new ItemFileWriteStore({data: vmOptions});
            var firstValue;
            networkVMListRestStore.query({networkId: neworkid}).then(function(data) {
                if(data.length === 0 || data === "" || data === undefined) {
                    var noVMOptions = {
                        identifier: 'id',
                        label: 'name',
                        items: [{id: "option" , name : translator.common.selectVM}]                
                    };            
                    var noVmList = new ItemFileWriteStore({data: noVMOptions});
                    dijit.byId("vpcGeneralTierVmWidget").set("store", noVmList);
                    dijit.byId("vpcGeneralTierVmWidget").set("value", "option");
                } else {
                    dojo.forEach(data, function(vm, index) {
                        vmList.newItem({
                            id: vm.referenceId,
                            name: vm.name
                        });
                        if (index === 0) {
                            firstValue = vm.referenceId;
                        }
                    });    
                    dijit.byId("vpcGeneralTierVmWidget").set("store", vmList);
                    dijit.byId("vpcGeneralTierVmWidget").set("value", firstValue);
                }
            });
        }        
    },
    'releaseIPShow': function(id) {
        dojo.byId("currentGeneralIPId").value = id;
        dijit.byId("vpcGenerealReleaseIpDialog").show();
    },
    'closeReleaseIPShow': function() {
        dijit.byId("vpcGenerealReleaseIpDialog").hide();
    },
    'closeEnableStaticNat' : function() {
      dijit.byId("vpcGenerealIpEnableStaticNatDialog").hide();
    },
    'closeDisableStaticNat': function() {
        dijit.byId("vpcGenerealIPPageDisableStaticNatDialog").hide();
    },
    'releaseIP': function() {

        var ipReleaseRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/release"
        });

        dijit.byId('viewGeneralIpLoader').show();
        dijit.byId('vpcGenerealReleaseIpDialog').hide();

        ipReleaseRestStore.add({ipId: dojo.byId("currentGeneralIPId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var releaseIPJobStatus = setInterval(function() {
                        ListAllPublicIPInfo.releaseIPJob(resultData.jobId, releaseIPJobStatus);
                    }, 3000);
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewGeneralIpLoader').hide();

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
                    registry.byId("userToaster").setContent(translator.common.message.releaseVpcIPSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('viewGeneralIpLoader').hide();
                    ListAllPublicIPInfo.populateValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewGeneralIpLoader').hide();
                }
            });
        });
    },
    'enableStaticNatShow' : function(id) {
        dojo.query("#enableStaticNatErrorMessage").toggleClass("hide_text", true); 
        dojo.byId("currentGeneralIPId").value = id;      
        dojo.byId('currentGeneralEnableStaticNatIpId').value= id;
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/list/tier"
        });
        
         var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var vpcReferId;
        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
        dijit.byId("generalPublicIPGrid").store.fetch({query: {id: id},
            onItem: function(item) {
                vpcReferId=item.vpcId;
            }            
        });         
            
         networkRestStore.query({vpcId: vpcReferId}).then(function(data) {
            
             if(data.length === 0 || data === "" || data === undefined) {
                 var noTierOptions = {
                     identifier: 'id',
                     label: 'name',
                     items: [{id: "option", name: translator.common.selectTier}]
                 };
                 var noTierList = new ItemFileWriteStore({data: noTierOptions}); 
                 dijit.byId("vpcGeneralTierWidget").set("store", noTierList);                 
                 dijit.byId("vpcGeneralTierWidget").set("value", "option");    
             } else {  
                 var firstVal = "";
                 var count = 0;
                 dojo.forEach(data, function(tier) {
                     if(tier.tierType === "WEB" || tier.tierType === "DB") {
                         tierList.newItem({
                             id: tier.referenceId,
                             name: tier.name,
                             networkId : tier.id
                         });
                         
                         count = count + 1;                    
                         if(count === 1) {
                             firstVal = tier.referenceId;
                         }
                     }
                 });            
                 dijit.byId("vpcGeneralTierWidget").set("store", tierList);        
                 dijit.byId("vpcGeneralTierWidget").set("value", firstVal);        
                 
                 dijit.byId("vpcGenerealIpEnableStaticNatDialog").show();
//                 dijit.byId("vpcGeneralEnableStaticNatForm").reset();
             }            
         });         
     },
    'enableStaticNat': function() {

        var vmId = dijit.byId("vpcGeneralTierVmWidget");
        var networkId = dijit.byId("vpcGeneralTierWidget");

        var pageNode = dojo.byId("vpcGeneralEnableStaticNatForm");
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
            if(vmId.value === "option" || vmId.value === "" || networkId.value === "option" || networkId.value === "") {
                dojo.query("#enableStaticNatErrorMessage").removeClass("hide_text", true); 
            } else {
                dojo.query("#enableStaticNatErrorMessage").toggleClass("hide_text", true); 
                dijit.byId('viewGeneralIpLoader').show();
                dijit.byId("vpcGenerealIpEnableStaticNatDialog").hide();
                var ipEnableStaticRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/network/ip/enableStaticNat"
                });

                ipEnableStaticRestStore.add({vmId: vmId.value, networkId: networkId.value, ipId: dojo.byId('currentGeneralIPId').value}).then(function(data) {
                    dojo.forEach(data, function(resultData) {                       
                        if ((resultData.result === "OK" || resultData.result === "true"  || resultData.result === true) && (resultData.jobId)) {
                            var enableStaticNatJobStatus = setInterval(function() {
                                ListAllPublicIPInfo.enableStaticNatJob(resultData.jobId, enableStaticNatJobStatus);
                            }, 3000);
                        } else if ((resultData.result === "OK" || resultData.result === "true"  || resultData.result === true) && (!resultData.jobId)) {
                            registry.byId("userToaster").setContent(translator.common.message.enableStaticNatSuccess, "message");
                            registry.byId("userToaster").show();
                            dijit.byId('viewGeneralIpLoader').hide();

                            ListAllPublicIPInfo.populateValues();
                        } else if (resultData.result === "FAILED") {
                            registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                            registry.byId("userToaster").show();
                            dijit.byId("viewGeneralIpLoader").hide();
                        } else {
                            registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('viewGeneralIpLoader').hide();
                        }
                    });
                });
            }
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
                    dijit.byId('viewGeneralIpLoader').hide();
                    
                    ListAllPublicIPInfo.populateValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(enableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewGeneralIpLoader').hide();
                }
            });
        });
    },
    'disableStaticNatIPShow': function(id) {    
        dojo.byId("currentGeneralIPId").value = id;
        dojo.byId('currentGeneralEnableStaticNatIpId').value = id;
        dijit.byId("vpcGenerealIPPageDisableStaticNatDialog").show();
    },
    'disableStaticNat': function() {
        dijit.byId('viewGeneralIpLoader').show();
        dijit.byId("vpcGenerealIPPageDisableStaticNatDialog").hide();

        var ipEnableStaticRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/disableStaticNat"
        });

        ipEnableStaticRestStore.add({ipId: dojo.byId('currentGeneralEnableStaticNatIpId').value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var disableStaticNatJobStatus = setInterval(function() {
                        ListAllPublicIPInfo.disableStaticNatJob(resultData.jobId, disableStaticNatJobStatus);
                    }, 3000);
                } else if (resultData.result === "true") {
                    registry.byId("userToaster").setContent(translator.common.message.disableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('viewGeneralIpLoader').hide();
                    ViewVpc.populateIPListValues();
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("viewGeneralIpLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewGeneralIpLoader').hide();

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

                if (jobResultData.jobResult == "OK") {
                    clearInterval(disableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.disableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('viewGeneralIpLoader').hide();
                    ListAllPublicIPInfo.populateValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(disableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewGeneralIpLoader').hide();
                }
            });
        });
    },
    loadAddIPInfo : function () {
        
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOption});
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectVPC}]
        };
        var vpcList = new ItemFileWriteStore({data: vpcOption});        
        dojo.query("#generalPublcLBZoneList").toggleClass("hide_text", true);
        dojo.query("#generalPublcLBZoneLoader").removeClass("hide_text", true);
        
        dojo.query("#generalPublicLBVPCList").toggleClass("hide_text", true);
        dojo.query("#generalPublcLBVPCLoader").removeClass("hide_text", true);
        
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var currentVPC = dojo.byId("selectedANVPCID").value;
        var zoneRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/zone/"
        });
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc"
        });   
        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                              
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                zoneRestResource.query().then(function (data) {
                    dojo.forEach(data, function (el) {
                        if(el.networkType === "Advanced") {
                            zoneList.newItem({id: el.referenceZoneId, name: el.aliasName, zoneId: el.id});
                        }                    
                    });
                    dijit.byId("generalPublicIPZoneWidget").set("store",zoneList);
                    dijit.byId("generalPublicIPZoneWidget").set("value","option");
                    
                    dijit.byId("generalPublicIPZoneWidget").onChange = function () {
                        ListAllPublicIPInfo.loadZoneData(dijit.byId("generalPublicIPZoneWidget"));
                    };   
                    dojo.query("#generalPublcIPZoneList").removeClass("hide_text", true);
                    dojo.query("#generalPublcIPZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalPublicIPVPCList").removeClass("hide_text", true);
                    dojo.query("#generalPublcIPVPCLoader").toggleClass("hide_text", true);
                });
            } else {                    
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone, zoneId: el.id});
                        dijit.byId("generalPublicIPZoneWidget").onChange = function () {};
                        dijit.byId("generalPublicIPZoneWidget").set("store", zoneList);
                        dijit.byId("generalPublicIPZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalPublcIPZoneList").removeClass("hide_text", true);
                        dojo.query("#generalPublcIPZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;                                                                                                                                     
                    });
                dijit.byId("generalPublicIPVPCWidget").set("store", singleVPCList);
                dijit.byId("generalPublicIPVPCWidget").set("value", firstVPC);   
                dojo.query("#generalPublicIPVPCList").removeClass("hide_text", true);
                dojo.query("#generalPublcIPVPCLoader").toggleClass("hide_text", true);
            });                               
        }                                                
        } else {                                                
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                var singleZoneOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleZoneList = new ItemFileWriteStore({data: singleZoneOption});
                zoneRestResource.get(currentZoneID).then(function (data) {
                    if(data.networkType === "Advanced") {
                        singleZoneList.newItem({id: data.referenceZoneId, name: data.aliasName, zoneId: data.zoneId});
                    } 
                    dijit.byId("generalPublicIPZoneWidget").set("store", singleZoneList);
                    dijit.byId("generalPublicIPZoneWidget").set("value", data.referenceZoneId);                    
                    dijit.byId("generalPublicIPZoneWidget").onChange = function () {
                        ListAllPublicIPInfo.loadZoneData(dijit.byId("generalPublicIPZoneWidget"));
                    }; 
                    dojo.query("#generalPublcIPZoneList").removeClass("hide_text", true);
                    dojo.query("#generalPublcIPZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalPublicIPVPCList").removeClass("hide_text", true);
                    dojo.query("#generalPublcIPVPCLoader").toggleClass("hide_text", true);                    
                    
                });
            } else {      
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone, zoneId: el.zoneId});
                        dijit.byId("generalPublicIPZoneWidget").onChange = function () {};
                        dijit.byId("generalPublicIPZoneWidget").set("store", zoneList);
                        dijit.byId("generalPublicIPZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalPublcIPZoneList").removeClass("hide_text", true);
                        dojo.query("#generalPublcIPZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;                                                                                                                                     
                    });
                dijit.byId("generalPublicIPVPCWidget").set("store", singleVPCList);
                dijit.byId("generalPublicIPVPCWidget").set("value", firstVPC);   
                dojo.query("#generalPublicIPVPCList").removeClass("hide_text", true);
                dojo.query("#generalPublcIPVPCLoader").toggleClass("hide_text", true);
            });
        } 
    }       
    },
    loadZoneData : function (currentZone) {
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.query("#generalPublicIPVPCList").toggleClass("hide_text", true);
        dojo.query("#generalPublcIPVPCLoader").removeClass("hide_text", true);
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        if(currentZone.value === "option") {
            vpcList.newItem({id: "option", name: translator.common.selectVPC});
            dijit.byId("generalPublicIPVPCWidget").set("store", vpcList);
            dijit.byId("generalPublicIPVPCWidget").set("value", "option");
            
            dojo.query("#generalPublicIPVPCList").removeClass("hide_text", true);
            dojo.query("#generalPublcIPVPCLoader").toggleClass("hide_text", true);
            
        } else {
            var vpcOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc"
            });
            
            vpcOfferRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                var firstVPC = ""
                dojo.forEach(data, function (el, index) {
                    vpcList.newItem({id: el.referenceId, name: el.name});
                    if(index === 0) {
                        firstVPC = el.referenceId;
                    }
                });                
                dijit.byId("generalPublicIPVPCWidget").set("store", vpcList);
                dijit.byId("generalPublicIPVPCWidget").set("value", firstVPC);    
                
                dojo.query("#generalPublicIPVPCList").removeClass("hide_text", true);
                dojo.query("#generalPublcIPVPCLoader").toggleClass("hide_text", true);
            });            
        }         
    },
    showCondition : function () {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/public/termsAndCondition"
        });                  
        dojo.style(dijit.byId("generalPublicIPContent").closeButtonNode,"display","none");
        configRestStore.query().then(function(resultData) {
            dojo.byId("generalPublicIPContentArea").innerHTML = resultData.value;                   
        });
        dijit.byId("generalPublicIPContent").show();
    },
    cancelConditionBox : function () {
            dijit.byId("generalPublicIPContent").hide();
    },
    'acquireIPShow': function() {
        ListAllPublicIPInfo.loadAddIPInfo();
        dijit.byId("generalPublicIPInfoAgreement").set("checked", false);
        dojo.byId("conditionExceptionMsg").style.display = "none";
       
        dijit.byId("generalPublicIPAcquireDialog").show();
    },
    'closeAcquireIPShow': function() {
        dijit.byId("generalPublicIPAcquireDialog").hide();
    },
    'acquireIp': function() {        
        var vpcId = dijit.byId("generalPublicIPVPCWidget").value;
        var agrementChecked =  dijit.byId("generalPublicIPInfoAgreement").checked;        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/ip/acquire"
        });        

        if((vpcId === "option" || vpcId === "") || (agrementChecked === false)) {     
            dojo.byId("conditionExceptionMsg").style.display = "block";            
        } else {
            dojo.byId("conditionExceptionMsg").style.display = "none";
            dijit.byId('generalPublicIPLoader').show();
            dijit.byId('generalPublicIPAcquireDialog').hide();
            networkRestStore.add({vpcId: vpcId}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var acquireIpJobStatus = setInterval(function() {
                            ListAllPublicIPInfo.acquireJob(resultData.jobId, acquireIpJobStatus, resultData.vpcId);
                        }, 3000);
                    } else if (resultData.result === "HASFIRSTIP") {
                        registry.byId("userToaster").setContent(translator.common.message.networkHasFirstIPMessage, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('generalPublicIPLoader').hide();
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("generalPublicIPLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('generalPublicIPLoader').hide();
                    }
                });
            });
        }
    },
    'acquireJob': function(jobId, acquireIpJobStatus, vpcId) {
        var acquireIpJobStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/ip/acquire/job"
        });
        acquireIpJobStoreStore.add({jobId: jobId, vpcId: vpcId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult == "OK") {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.addIpToVpcSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('generalPublicIPLoader').hide();                    
                    ListAllPublicIPInfo.populateValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('generalPublicIPLoader').hide();
                }
            });
        });
    },    
    populateValues : function () {        
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.publicIPLimit !== "-1") {                   
                    if((limitData.publicIPUsed >= limitData.publicIPLimit)) {
                        dojo.byId("vpcIPListRachedLimitMsg").style.display = "block";
                        dojo.query("#vpcAddIPButton").toggleClass("hide_text", true);                                           
                    } else {
                        dojo.byId("vpcIPListRachedLimitMsg").style.display = "none";
                        dojo.query("#vpcAddIPButton").removeClass("hide_text", true);    
                    }
                } else {
                    dojo.byId("vpcIPListRachedLimitMsg").style.display = "none";
                    dojo.query("#vpcAddIPButton").removeClass("hide_text", true);
                }
            });
        });
        
        if(dijit.byId("generalPublicIPGrid")) {                                    
            dijit.byId("generalPublicIPGrid").destroyRecursive();                    
        }        
        if(dijit.byId("generalPublicIPZoneWidget")) {                                    
            dijit.byId("generalPublicIPZoneWidget").destroyRecursive();                    
        }
        if(dijit.byId("generalPublicIPVPCWidget")) {                                    
            dijit.byId("generalPublicIPVPCWidget").destroyRecursive();                    
        }
        dojo.byId("vpcGeneralPublicIP").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var LBData = {
            items: []
        }; 
        var publicIPList = new ItemFileWriteStore({data: LBData}); 
        var publicIPLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'field': 'vpcId', 'hidden': 'true'},                
                {'name': translator.common.ips, 'field': 'ip', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if (data.isSourceNat == true || data.isSourceNat == "true") {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if(data.isStaticNat == true || data.isStaticNat == "true") {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        }


                    }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vpc.name, 'field': 'vpc', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.tier, 'field': 'tier', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},     
                {'name': translator.common.virtualMachine, 'field': 'virtualMachine', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},                     
                {'name': translator.common.state, 'field': 'state', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
                       
                        if (data.isSourceNat === false || data.isSourceNat === "false") {
                            if (data.isStaticNat === false || data.isStaticNat === "false") {
                                if(data.isVPCLBAdded === true || data.isVPCPFAdded === true) {
                                    html = "<a onclick='ListAllPublicIPInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                                } else {
                                   html = "<a onclick='ListAllPublicIPInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>" +
                                        "<a onclick='ListAllPublicIPInfo.enableStaticNatShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.enableStaticNat + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"; 
                                }
                                
                            } else {
                                html = "<a onclick='ListAllPublicIPInfo.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>" +
                                "<a onclick='ListAllPublicIPInfo.disableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.disableStaticNat + "'><img style='width: 17px; height: 17px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>";
                            } 
                        } 
                        return html;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];
         var ipRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/overall/listIP"
         });
         var vpcRestStore = new JsonRest({
             target: core.getContextPath()+"/api/vpc"
         });
         
         var currentMainZoneID = ""  
         var currentMainVPC = ""

        if(dojo.byId("selectedANZoneID").value === "All" || dojo.byId("selectedANZoneID").value === " " || dojo.byId("selectedANZoneID").value === "undefind" || dojo.byId("selectedANZoneID").value === "") { 
            currentMainZoneID = "";
        } else {
            currentMainZoneID = dojo.byId("selectedANZoneID").value;            
        }

        if(dojo.byId("selectedANVPCID").value === "All" || dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }
        vpcRestStore.query().then(function (data) {
           if(data.length === 0) {
               dojo.query("#publicIPListActionButtonCollection").toggleClass("hide_text", true); 
           } else {
               dojo.query("#publicIPListActionButtonCollection").removeClass("hide_text", true); 
           }
        });
         ipRestStore.query({zoneReferenceId:currentMainZoneID, vpcId:currentMainVPC}).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noGeneralPublicIPMsg").style.display = "block";
                 dojo.byId("vpcGeneralPublicIP").innerHTML = "";
             } else {
                 dojo.byId("noGeneralPublicIPMsg").style.display = "none";                 
                 dojo.forEach(data, function(resultData) {                     
                     publicIPList.newItem({
                         id: resultData.referenceId,
                         vpcId : resultData.vpcId,
                        ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat},
                        zone: resultData.zone,
                        vpc: resultData.vpc,
                        tier : resultData.network,
                        state: resultData.state,
                        virtualMachine : resultData.vms,
                        action: {
                            id: resultData.id, 
                            ip: resultData.ip, 
                            isSourceNat: resultData.isSourceNat, 
                            isStaticNat: resultData.isStaticNat,
                            vpnEnabled: resultData.vpnEnabled,
//                            isEnabledVPN: resultData.isEnabledVPN,
                            isVPCLBAdded: resultData.isVPCLBAdded,
                            isVPCPFAdded: resultData.isVPCPFAdded
                        }
                     });                     
                 });                  
                 dojo.byId("vpcGeneralPublicIP").innerHTML = "";
                 var ipGrid = new EnhancedGrid({
                    id: 'generalPublicIPGrid',
                    "class" : "span12",
                    store: publicIPList,
                    structure: publicIPLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                ipGrid.placeAt("vpcGeneralPublicIP");
                ipGrid.startup();
              }                           
         });    
         
         var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierList = new ItemFileWriteStore({data: tierOptions});  
        if(dijit.byId("vpcGeneralTierWidget")) {                                    
            dijit.byId("vpcGeneralTierWidget").destroyRecursive();                    
        }
        if(dijit.byId("vpcGeneralTierVmWidget")) {                                    
            dijit.byId("vpcGeneralTierVmWidget").destroyRecursive();                    
        }
        var tierWidget = new FilteringSelect({
            id: "vpcGeneralTierWidget",
            store: tierList,
            placeHolder: translator.common.message.selectTier,
            onChange: function() {
                ListAllPublicIPInfo.getTierVMList(this);
            }
        });
        tierWidget.placeAt("vpcGeneralTierList");
        tierWidget.startup();
        
        var tierVMOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierVMList = new ItemFileWriteStore({data: tierVMOptions});  
        
        var tierVMWidget = new FilteringSelect({
            id: "vpcGeneralTierVmWidget",
            store: tierVMList,
            placeHolder: translator.common.message.selectInstance,
            onChange: function() {
//                viewVpcIp.getTierVMList(this.value);
            }
        });
        tierVMWidget.placeAt("vpcGeneralTierVmList");
        tierVMWidget.startup();
        
        
         
         var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storeList = new ItemFileWriteStore({data: option});                        

        var zoneWidget = new FilteringSelect({
            id: "generalPublicIPZoneWidget",            
            store: storeList,   
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectZone,
            onChange: function() {
                ListAllPublicIPInfo.loadZoneData(this);
            }
        }).placeAt("generalPublcIPZoneList");
        var vpcWidget = new FilteringSelect({
            id: "generalPublicIPVPCWidget",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectVPC,
            onChange: function() {  
//                ListAllVPCVMInfo.loadVPCData(this);
            }
        }).placeAt("generalPublicIPVPCList");        
        
        ListAllPublicIPInfo.loadAddIPInfo();
    }
}
var ListAllInternalLBInfo = {
    'showAddInternalLB': function () {
        core.router.go("#/user/tier/addInternalLB");
//        dojo.query("#generalInternalLBCloudStackException").toggleClass("hide_text", true);
//        dojo.byId("generalInternalLBCloudstackExceptionMsg").innerHTML = "";
//        ListAllInternalLBInfo.loadAddInternalLBInfo();
//         dijit.byId("addGeneralInternalLbDialog").show();
//         dijit.byId("addInternalLBPageForm").reset();
    },      
    populateValues : function () {
        if(dijit.byId("generalInternalLBGrid")) {                                    
            dijit.byId("generalInternalLBGrid").destroyRecursive();                    
        }
        
        
        dojo.byId("vpcGeneralInternalLB").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var LBData = {
            items: []
        };         
        var lbList = new ItemFileWriteStore({data: LBData}); 
        var internallbLayout = [
            [                
                {'name': translator.common.name, 'field': 'lbName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        var html = "<a href='#/user/tier/viewInternalLB/"+data.tierId+"'>" + data.name +"</a>";
                        return html;
                }},
                {'name': translator.common.vpc.name, 'field': 'vpc', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.tier, 'field': 'tier', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.description, 'field': 'lbDescription', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.sourcePort, 'field': 'lbSourcePort', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.instancePort, 'field': 'lbInstancePort', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.algorithm, 'field': 'lbAlgorithm', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                                
                {'name': translator.common.instance.name, 'field': 'lbVm', 'width': '100%', 'datatype':'string',  'autoComplete': 'true'},               
                {'name': translator.common.action, 'field': 'action', 'hidden': true, 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        var html = "<a onclick='TierInternalLoadBalancerInfo.showAddLoadBalancingAdditionalVM(\"" + data.id + "\")' class='offset1' title='" + translator.common.addVM + "'><img src='images/add_vm_icon.png'></img></a></li>"+
                                   "<a onclick='TierInternalLoadBalancerInfo.showRemoveLoadBalancingVM(\"" + data.id + "\")' class='offset1' title='" + translator.common.removeVM + "'><img src='images/delete_vm_icon.png'></img></a></li>"+
                                   "<a onclick='TierInternalLoadBalancerInfo.deleteLoadBalancerRuleShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";  
                           return html;
                       }
                   }                 
            ]
         ];
         var lbRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/overall/listLB"
         });
         var currentMainZoneID = ""  
         var currentMainVPC = ""

        if(dojo.byId("selectedANZoneID").value === "All" || dojo.byId("selectedANZoneID").value === " " || dojo.byId("selectedANZoneID").value === "undefind" || dojo.byId("selectedANZoneID").value === "") { 
            currentMainZoneID = "";
        } else {
            currentMainZoneID = dojo.byId("selectedANZoneID").value;            
        }

        if(dojo.byId("selectedANVPCID").value === "All"  || dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }
        
         var tierRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/tier/list"
         });
         lbRestStore.query({zoneReferenceId:currentMainZoneID, vpcId:currentMainVPC, scheme: "Internal"}).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noGeneralInternalLBMsg").style.display = "block";
                 dojo.byId("vpcGeneralInternalLB").innerHTML = "";
                 
                 tierRestStore.query({vpcId: currentMainVPC}).then(function (checkData) {                    
                    if(checkData.length === 0 || checkData[0].localizedMessage) {
                        dojo.byId("noInternalLBMsg").innerHTML = translator.common.noVPCandInternalLBMsg;
                        dojo.query("#internalLBListActionButtonCollection").toggleClass("hide_text", true); 
                    } else {
                        dojo.byId("noInternalLBMsg").innerHTML = translator.common.noInternalLBMsgInfo;
                        dojo.query("#internalLBListActionButtonCollection").removeClass("hide_text", true); 
                    }
                });
                 
                 
             } else {
                 dojo.byId("noGeneralInternalLBMsg").style.display = "none";                 
                 dojo.forEach(data, function(el) {                     
                     lbList.newItem({
                         lbName : {name:el.name, tierId: el.tierId},
                         vpc : el.vpcName,
                         tier : el.tierName,
                         lbDescription:el.description,
                         lbSourcePort: el.sourcePort, 
                         lbInstancePort: el.instancePort,
                         lbAlgorithm : el.algorithm,
                         lbVm:el.vmList,
                         action: {id: el.id, name: el.loadBalancerName, algorithm: el.algorithm}
                     });                     
                 });                  
                 dojo.byId("vpcGeneralInternalLB").innerHTML = "";
                 var lbGrid = new EnhancedGrid({
                    id: 'generalInternalLBGrid',
                    "class" : "span12",
                    store: lbList,
                    structure: internallbLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                lbGrid.placeAt("vpcGeneralInternalLB");
                lbGrid.startup();
              }                           
         });                              
     }
}

var AddInternalLBInfo = {
    'closeAddInternalLB': function () {
         AddInternalLBInfo.loadAddInternalLBInfo();
         dijit.byId("generalInternalLBName").reset();
         dijit.byId("generalInternalLBDescription").reset();
         dijit.byId("generalInternalLBAlgorithm").reset();
         dijit.byId("generalInternalLBSourcePort").reset();
         dijit.byId("generalInternalLBInstancePort").reset();         
    },
    'addLoadBalancing': function() {          
        var zone = dijit.byId("generalInternalLBZoneWidget").value;
        var vpc = dijit.byId("generalInternalLBVPCWidget").value;
        var tier = dijit.byId("generalInternalLBTierWidget").value;
        
        if(zone === "option" || vpc === "option" || tier === "option" || zone === "" || vpc === "" || tier === "") {
            dojo.byId("internalLBErrorMessage").style.display = "block";            
        } else {
            dojo.byId("internalLBErrorMessage").style.display = "none";  
            var algorithm = dijit.byId("generalInternalLBAlgorithm");
            var name = dijit.byId("generalInternalLBName");
            var description = dijit.byId("generalInternalLBDescription");
            var sourcePort = dijit.byId("generalInternalLBSourcePort");
            var instancePort = dijit.byId("generalInternalLBInstancePort");
            var networkid = dijit.byId("generalInternalLBTierWidget");

             var pageNode = dojo.byId("addGeneralInternalLBPageDiv");
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
//                dijit.byId("addGeneralInternalLbDialog").hide();
                dijit.byId("addInternalLBLoader").show();
                var networkLoadBalancerRestStore = new JsonRest({
                   target: core.getContextPath() + "/api/network/internal/loadBalancing/add"
                });
                networkLoadBalancerRestStore.add({networkId: networkid.value, description: description.value, algorithm: algorithm.value, name: name.value, sourcePort: sourcePort.value, instancePort: instancePort.value}).then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        if (resultData.result === "OK") {
                            var addLoadBalancingRuleJobStatus = setInterval(function() {
                                AddInternalLBInfo.addLoadBalancingRuleJob(resultData.jobId, addLoadBalancingRuleJobStatus);
                            }, 3000);
                            dojo.query("#generalInternalLBCloudStackException").toggleClass("hide_text", true);
                            dojo.byId("generalInternalLBCloudstackExceptionMsg").innerHTML = "";
                        } else if (resultData.result === "FAILED") {
                            registry.byId("userToaster").setContent(resultData.message, "error");
                            registry.byId("userToaster").show();
                            dijit.byId("addInternalLBLoader").hide();
                            dojo.query("#generalInternalLBCloudStackException").removeClass("hide_text", true);
                            dojo.byId("generalInternalLBCloudstackExceptionMsg").innerHTML = resultData.message;
                        } else {
                            registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('addInternalLBLoader').hide();  
                            dojo.query("#generalInternalLBCloudStackException").removeClass("hide_text", true);
                            dojo.byId("generalInternalLBCloudstackExceptionMsg").innerHTML = resultData.message;
                        }
                    });
                });
            }
        }        
    },
    'addLoadBalancingRuleJob': function(jobId, addLoadBalancingRuleJobStatus) {
        var loadBalancerJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/internal/loadBalancing/job"
        });

        loadBalancerJobRestStoreStore.add({jobId:jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(addLoadBalancingRuleJobStatus);                    
                    registry.byId("userToaster").setContent(translator.common.message.addLoadBalancerRuleSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('addInternalLBLoader').hide();   
                     dijit.byId("addGeneralInternalLbDialog").hide();
                    core.router.go("#/user/tier/internalLB");
                    dojo.query("#generalInternalLBCloudStackException").toggleClass("hide_text", true);
                    dojo.byId("generalInternalLBCloudstackExceptionMsg").innerHTML = "";
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(addLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('addInternalLBLoader').hide();                  
                    dojo.query("#generalInternalLBCloudStackException").removeClass("hide_text", true);
                    dojo.byId("generalInternalLBCloudstackExceptionMsg").innerHTML = resultData.message;
                }
            });
        });
    },  
    populateValues : function () {
        if(dijit.byId("generalInternalLBZoneWidget")) {                                    
            dijit.byId("generalInternalLBZoneWidget").destroyRecursive();                    
        }
        if(dijit.byId("generalInternalLBVPCWidget")) {                                    
            dijit.byId("generalInternalLBVPCWidget").destroyRecursive();                    
        }          
        if(dijit.byId("generalInternalLBTierWidget")) {                                    
            dijit.byId("generalInternalLBTierWidget").destroyRecursive();                    
        }  
        
        var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storeList = new ItemFileWriteStore({data: option});                        

        var zoneWidget = new FilteringSelect({
            id: "generalInternalLBZoneWidget",            
            store: storeList,   
            sortByLabel: false,
            placeHolder : translator.common.selectZone,
            autoHeight: true,
            onChange: function() {
                AddInternalLBInfo.loadZoneData(this);
            }
        }).placeAt("generalInternalLbZoneList");
        var vpcWidget = new FilteringSelect({
            id: "generalInternalLBVPCWidget",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectVPC,
            onChange: function() {  
                AddInternalLBInfo.loadVPCData(this);
            }
        }).placeAt("generalInternalLbVPCList");
        
        var tierWidget = new FilteringSelect({
            id: "generalInternalLBTierWidget",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,            
            onChange: function() {               
            }
        }).placeAt("generalInternalLbTierList");
        
        AddInternalLBInfo.loadAddInternalLBInfo();
    },
    loadAddInternalLBInfo : function () {
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOption});
        dojo.query("#generalInternalLbZoneList").toggleClass("hide_text", true);
        dojo.query("#generalInternalLbVPCList").toggleClass("hide_text", true);
        dojo.query("#generalInternalLbTierList").toggleClass("hide_text", true);        
        
        dojo.query("#generalInternalLBZoneLoader").removeClass("hide_text", true);
        dojo.query("#generalInternalLBVPCLoader").removeClass("hide_text", true);
        dojo.query("#generalInternalLBTierLoader").removeClass("hide_text", true);         
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var currentVPC = dojo.byId("selectedANVPCID").value;
        var zoneRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/zone/"
        });
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc"
        });                      
        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                              
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                zoneRestResource.query().then(function (data) {
                    dojo.forEach(data, function (el) {
                        if(el.networkType === "Advanced") {
                            zoneList.newItem({id: el.referenceZoneId, name: el.aliasName, zoneId: el.id});
                        }                    
                    });
                    dijit.byId("generalInternalLBZoneWidget").set("store",zoneList);
                    dijit.byId("generalInternalLBZoneWidget").set("value","option");
                    
                    dijit.byId("generalInternalLBZoneWidget").onChange = function () {
                        AddInternalLBInfo.loadZoneData(dijit.byId("generalInternalLBZoneWidget"));
                    };   
                    dojo.query("#generalInternalLbZoneList").removeClass("hide_text", true);
                    dojo.query("#generalInternalLBZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                    dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalInternalLbVPCList").removeClass("hide_text", true);
                    dojo.query("#generalInternalLBVPCLoader").toggleClass("hide_text", true);
                });
            } else {                    
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone, zoneId: el.zoneId});
                        dijit.byId("generalInternalLBZoneWidget").onChange = function () {};
                        dijit.byId("generalInternalLBZoneWidget").set("store", zoneList);
                        dijit.byId("generalInternalLBZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalInternalLbZoneList").removeClass("hide_text", true);
                        dojo.query("#generalInternalLBZoneLoader").toggleClass("hide_text", true);
                        
                        dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                        dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true);
                        
                        dojo.query("#generalInternalLbVPCList").removeClass("hide_text", true);
                        dojo.query("#generalInternalLBVPCLoader").toggleClass("hide_text", true);
                        
                        
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;  
                        
                        var tierOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };                        
                        var tierList = new ItemFileWriteStore({data: tierOption});
                        var tierOfferRestStore = new JsonRest({
                            target: core.getContextPath() + "/api/network/tier/list"
                        });            
                        tierOfferRestStore.query({vpcId: el.referenceId}).then(function(data) {
                            if(data === "" || data.length === 0 || data === "undefined" || data[0].name === "undefined") {
                                tierList.newItem({id: "option", name: translator.common.selectTier});
                                dijit.byId("generalInternalLBTierWidget").set("store", tierList);
                                dijit.byId("generalInternalLBTierWidget").set("value", "option");
                                
                                dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                                dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true);
                            } else {
                                var firstTier = ""
                                dojo.forEach(data, function (el, index) {
                                    if(el.tierType === "APP") {
                                        tierList.newItem({id: el.referenceId, name: el.name});
                                        if(index === 0) {
                                            firstTier = el.referenceId;
                                        }
                                    }                                    
                                });                
                                dijit.byId("generalInternalLBTierWidget").set("store", tierList);
                                dijit.byId("generalInternalLBTierWidget").set("value", firstTier);
                                
                                dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                                dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true);
                        }                
                    });                                                   
                });
                dijit.byId("generalInternalLBVPCWidget").set("store", singleVPCList);
                dijit.byId("generalInternalLBVPCWidget").set("value", firstVPC);   
                dojo.query("#generalInternalLbVPCList").removeClass("hide_text", true);
                dojo.query("#generalInternalLBVPCLoader").toggleClass("hide_text", true);
            });                               
        }                                                
        } else {                                                
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                var singleZoneOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleZoneList = new ItemFileWriteStore({data: singleZoneOption});
                zoneRestResource.get(currentZoneID).then(function (data) {
                    if(data.networkType === "Advanced") {
                        singleZoneList.newItem({id: data.referenceZoneId, name: data.aliasName, zoneId: data.id});
                    } 
                    dijit.byId("generalInternalLBZoneWidget").set("store", singleZoneList);
                    dijit.byId("generalInternalLBZoneWidget").set("value", data.referenceZoneId);                    
                    dijit.byId("generalInternalLBZoneWidget").onChange = function () {
                        AddInternalLBInfo.loadZoneData(dijit.byId("generalInternalLBZoneWidget"));
                    }; 
                    dojo.query("#generalInternalLbZoneList").removeClass("hide_text", true);
                    dojo.query("#generalInternalLBZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                    dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true);
                        
                    dojo.query("#generalInternalLbVPCList").removeClass("hide_text", true);
                    dojo.query("#generalInternalLBVPCLoader").toggleClass("hide_text", true);
                });
            } else {      
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone, zoneId: el.zoneId});
                        dijit.byId("generalInternalLBZoneWidget").onChange = function () {};
                        dijit.byId("generalInternalLBZoneWidget").set("store", zoneList);
                        dijit.byId("generalInternalLBZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalInternalLbZoneList").removeClass("hide_text", true);
                        dojo.query("#generalInternalLBZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;  
                        
                        var tierOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };                        
                        var tierList = new ItemFileWriteStore({data: tierOption});
                        var tierOfferRestStore = new JsonRest({
                            target: core.getContextPath() + "/api/network/tier/list"
                        });            
                        tierOfferRestStore.query({vpcId: el.referenceId}).then(function(data) {
                            if(data === "" || data.length === 0 || data === "undefined" || data[0].name === "undefined") {
                                tierList.newItem({id: "option", name: translator.common.selectTier});
                                dijit.byId("generalInternalLBTierWidget").set("store", tierList);
                                dijit.byId("generalInternalLBTierWidget").set("value", "option");
                                
                                dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                                dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true);
                            } else {
                                var firstTier = ""
                                dojo.forEach(data, function (el, index) {
                                    if(el.tierType === "APP") {
                                        tierList.newItem({id: el.referenceId, name: el.name});
                                        if(index === 0) {
                                            firstTier = el.referenceId;
                                        }
                                    } 
                                });                
                                dijit.byId("generalInternalLBTierWidget").set("store", tierList);
                                dijit.byId("generalInternalLBTierWidget").set("value", firstTier);
                                
                                dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                                dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true);
                        }                
                    });                                                   
                });
                dijit.byId("generalInternalLBVPCWidget").set("store", singleVPCList);
                dijit.byId("generalInternalLBVPCWidget").set("value", firstVPC);   
                dojo.query("#generalInternalLbVPCList").removeClass("hide_text", true);
                dojo.query("#generalInternalLBVPCLoader").toggleClass("hide_text", true);
            });
        } 
    }  
        
    },
    loadZoneData : function (currentZone) {
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.query("#generalInternalLbVPCList").toggleClass("hide_text", true);
        dojo.query("#generalInternalLBVPCLoader").removeClass("hide_text", true);
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        if(currentZone.value === "option") {
            vpcList.newItem({id: "option", name: translator.common.selectVPC});
            dijit.byId("generalInternalLBVPCWidget").set("store", vpcList);
            dijit.byId("generalInternalLBVPCWidget").set("value", "option");
            
            dojo.query("#generalInternalLbVPCList").removeClass("hide_text", true);
            dojo.query("#generalInternalLBVPCLoader").toggleClass("hide_text", true);
            
        } else {
            var vpcOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc"
            });
            
            vpcOfferRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                var firstVPC = ""
                dojo.forEach(data, function (el, index) {
                    vpcList.newItem({id: el.referenceId, name: el.name});
                    if(index === 0) {
                        firstVPC = el.referenceId;
                    }
                });                
                dijit.byId("generalInternalLBVPCWidget").set("store", vpcList);
                dijit.byId("generalInternalLBVPCWidget").set("value", firstVPC);    
                
                dojo.query("#generalInternalLbVPCList").removeClass("hide_text", true);
                dojo.query("#generalInternalLBVPCLoader").toggleClass("hide_text", true);
            });            
        }              
    },
    loadVPCData : function (currentVPC) {
        
        dojo.query("#generalInternalLBTierLoader").removeClass("hide_text", true);
        dojo.query("#generalInternalLbTierList").toggleClass("hide_text", true);                        
        var tierOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var tierList = new ItemFileWriteStore({data: tierOption});
        if(currentVPC.value === "option") {
            tierList.newItem({id: "option", name: translator.common.selectTier});            
            dijit.byId("generalInternalLBTierWidget").set("store", tierList);                
            dijit.byId("generalInternalLBTierWidget").set("value", "option");                
            
            dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
            dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true); 
            
        } else {    
            var firstTier = "";                        
            if(currentVPC.value === "option") {
                tierList.newItem({id: "option", name: translator.common.selectTier});
                dijit.byId("generalInternalLBTierWidget").set("store", tierList);
                dijit.byId("generalInternalLBTierWidget").set("value", "option");

                dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true); 
            } else {
                var tierOfferRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/network/tier/list"
                });            
                tierOfferRestStore.query({vpcId: currentVPC.value}).then(function(data) {
                    if(data === "" || data.length === 0 || data === "undefined" || data[0].name === "undefined") {
                        tierList.newItem({id: "option", name: translator.common.selectTier});
                        dijit.byId("generalInternalLBTierWidget").set("store",tierList);
                        dijit.byId("generalInternalLBTierWidget").set("value","option");
                        dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                        dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true); 
                    } else {
                        var count = 0;
                        dojo.forEach(data, function (el, index) {
                            if(el.tierType === "APP") {
                                count = count + 1;
                                tierList.newItem({id: el.referenceId, name: el.name});
                                if(count === 1) {
                                    firstTier = el.referenceId;
                                }
                            } 
                        });                
                        dijit.byId("generalInternalLBTierWidget").set("store", tierList);
                        dijit.byId("generalInternalLBTierWidget").set("value", firstTier);
                        dojo.query("#generalInternalLbTierList").removeClass("hide_text", true);
                        dojo.query("#generalInternalLBTierLoader").toggleClass("hide_text", true); 
                    }                
                });                                
            }
        }       
    }
};

var ListAllPublicLBInfo = {    
    loadAddLBInfo : function () {
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOption});
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectVPC}]
        };
        var vpcList = new ItemFileWriteStore({data: vpcOption});        
        dojo.query("#generalPublcLBZoneList").toggleClass("hide_text", true);
        dojo.query("#generalPublcLBZoneLoader").removeClass("hide_text", true);
        
        dojo.query("#generalPublicLBVPCList").toggleClass("hide_text", true);
        dojo.query("#generalPublcLBVPCLoader").removeClass("hide_text", true);
        
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var currentVPC = dojo.byId("selectedANVPCID").value;
        var zoneRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/zone/"
        });
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc"
        });   
        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                              
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                zoneRestResource.query().then(function (data) {
                    dojo.forEach(data, function (el) {
                        if(el.networkType === "Advanced") {
                            zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
                        }                    
                    });
                    dijit.byId("generalPublicLBZoneWidget").set("store",zoneList);
                    dijit.byId("generalPublicLBZoneWidget").set("value","option");
                    
                    dijit.byId("generalPublicLBZoneWidget").onChange = function () {
                        ListAllPublicLBInfo.loadZoneData(dijit.byId("generalPublicLBZoneWidget"));
                    };   
                    dojo.query("#generalPublcLBZoneList").removeClass("hide_text", true);
                    dojo.query("#generalPublcLBZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalPublicLBVPCList").removeClass("hide_text", true);
                    dojo.query("#generalPublcLBVPCLoader").toggleClass("hide_text", true);
                });
            } else {                    
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone});
                        dijit.byId("generalPublicLBZoneWidget").onChange = function () {};
                        dijit.byId("generalPublicLBZoneWidget").set("store", zoneList);
                        dijit.byId("generalPublicLBZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalPublcLBZoneList").removeClass("hide_text", true);
                        dojo.query("#generalPublcLBZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;                                                                                                                                     
                    });
                dijit.byId("generalPublicLBVPCWidget").set("store", singleVPCList);
                dijit.byId("generalPublicLBVPCWidget").set("value", firstVPC);   
                dojo.query("#generalPublicLBVPCList").removeClass("hide_text", true);
                dojo.query("#generalPublcLBVPCLoader").toggleClass("hide_text", true);
            });                               
        }                                                
        } else {                                                
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                var singleZoneOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleZoneList = new ItemFileWriteStore({data: singleZoneOption});
                zoneRestResource.get(currentZoneID).then(function (data) {
                    if(data.networkType === "Advanced") {
                        singleZoneList.newItem({id: data.referenceZoneId, name: data.aliasName});
                    } 
                    dijit.byId("generalPublicLBZoneWidget").set("store", singleZoneList);
                    dijit.byId("generalPublicLBZoneWidget").set("value", data.referenceZoneId);                    
                    dijit.byId("generalPublicLBZoneWidget").onChange = function () {
                        ListAllPublicLBInfo.loadZoneData(dijit.byId("generalPublicLBZoneWidget"));
                    }; 
                    dojo.query("#generalPublcLBZoneList").removeClass("hide_text", true);
                    dojo.query("#generalPublcLBZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalPublicLBVPCList").removeClass("hide_text", true);
                    dojo.query("#generalPublcLBVPCLoader").toggleClass("hide_text", true);                    
                    
                });
            } else {      
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone});
                        dijit.byId("generalPublicLBZoneWidget").onChange = function () {};
                        dijit.byId("generalPublicLBZoneWidget").set("store", zoneList);
                        dijit.byId("generalPublicLBZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalPublcLBZoneList").removeClass("hide_text", true);
                        dojo.query("#generalPublcLBZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;                                                           
                                                                          
                });
                dijit.byId("generalPublicLBVPCWidget").set("store", singleVPCList);
                dijit.byId("generalPublicLBVPCWidget").set("value", firstVPC);   
                dojo.query("#generalPublicLBVPCList").removeClass("hide_text", true);
                dojo.query("#generalPublcLBVPCLoader").toggleClass("hide_text", true);
            });
        } 
    }
        
    },
    loadZoneData : function (currentZone) {
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.query("#generalPublicLBVPCList").toggleClass("hide_text", true);
        dojo.query("#generalPublcLBVPCLoader").removeClass("hide_text", true);
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        if(currentZone.value === "option") {
            vpcList.newItem({id: "option", name: translator.common.selectVPC});
            dijit.byId("generalPublicLBVPCWidget").set("store", vpcList);
            dijit.byId("generalPublicLBVPCWidget").set("value", "option");
            
            dojo.query("#generalPublicLBVPCList").removeClass("hide_text", true);
            dojo.query("#generalPublcLBVPCLoader").toggleClass("hide_text", true);
            
        } else {
            var vpcOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc"
            });
            
            vpcOfferRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                var firstVPC = ""
                dojo.forEach(data, function (el, index) {
                    vpcList.newItem({id: el.referenceId, name: el.name});
                    if(index === 0) {
                        firstVPC = el.referenceId;
                    }
                });                
                dijit.byId("generalPublicLBVPCWidget").set("store", vpcList);
                dijit.byId("generalPublicLBVPCWidget").set("value", firstVPC);    
                
                dojo.query("#generalPublicLBVPCList").removeClass("hide_text", true);
                dojo.query("#generalPublcLBVPCLoader").toggleClass("hide_text", true);
            });            
        } 
        
    },
    showCondition : function () {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/public/termsAndCondition"
        });                  
        dojo.style(dijit.byId("generalPublicLBContent").closeButtonNode,"display","none");
        configRestStore.query().then(function(resultData) {
            dojo.byId("generalPublicLBContentArea").innerHTML = resultData.value;                   
        });
        dijit.byId("generalPublicLBContent").show();
    },
    cancelConditionBox : function () {
            dijit.byId("generalPublicLBContent").hide();
    },
    'acquireIPShow': function() {
        ListAllPublicLBInfo.loadAddLBInfo();
        dijit.byId("generalPublicLBInfoAgreement").set("checked", false);
        dojo.byId("publicLBConditionExceptionMsg").style.display = "none";
       
        dijit.byId("generalVpcAcquireIpDialog").show();
    },
    'closeAcquireIPShow': function() {
        dijit.byId("generalVpcAcquireIpDialog").hide();
    },
    'acquireIp': function() {     
        
        var formElements = dojo.query("#ipConfigContainer input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var ipConfig = dijit.byId(checkedRadioId).value;        
        
        var vpcId = dijit.byId("generalPublicLBVPCWidget").value;
        var zoneID = dijit.byId("generalPublicLBZoneWidget").value;
        var agrementChecked =  dijit.byId("generalPublicLBInfoAgreement").checked;        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/ip/acquire"
        });        

        if(vpcId === "option" || vpcId === "" || zoneID === "option" || zoneID === "") {     
            dojo.byId("publicLBConditionExceptionMsg").style.display = "block";            
        } else {
            if(ipConfig === "newIP" && agrementChecked === true) {
                dojo.byId("acquireIPContainer").style.display = "block";
                dojo.byId("publicLBConditionExceptionMsg").style.display = "none";
                dijit.byId('generalPublicLBLoader').show();
                dijit.byId('generalVpcAcquireIpDialog').hide();
                networkRestStore.add({vpcId: vpcId}).then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        if (resultData.result === "OK") {
                            var acquireIpJobStatus = setInterval(function() {
                                ListAllPublicLBInfo.acquireJob(resultData.jobId, acquireIpJobStatus, resultData.vpcId, resultData.ipId);
                            }, 3000);
                        } else if (resultData.result === "HASFIRSTIP") {
                            registry.byId("userToaster").setContent(translator.common.message.networkHasFirstIPMessage, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('generalPublicLBLoader').hide();
                        } else if (resultData.result === "FAILED") {
                            registry.byId("userToaster").setContent(resultData.message, "error");
                            registry.byId("userToaster").show();
                            dijit.byId("generalPublicLBLoader").hide();
                        } else {
                            registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('generalPublicLBLoader').hide();
                        }
                    });
                });
            } else if(ipConfig === "vpcIP") {
                dojo.byId("acquireIPContainer").style.display = "none";
                dijit.byId('generalVpcAcquireIpDialog').hide();
                core.router.go("#/user/vpc/ipList/"+vpcId);
            } else {
                dojo.byId("publicLBConditionExceptionMsg").style.display = "block";       
            }        
        }
    },
    'acquireJob': function(jobId, acquireIpJobStatus, vpcId, ipId) {
        var acquireIpJobStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/ip/acquire/job"
        });
        acquireIpJobStoreStore.add({jobId: jobId, vpcId: vpcId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult == "OK") {                    
                    clearInterval(acquireIpJobStatus);
                    ListAllPublicLBInfo.refreshIPs(vpcId, ipId);                    
//                    ViewVpc.populateIPListValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('generalPublicLBLoader').hide();
                }
            });
        });
    },
    refreshIPs : function (vpcId, ipId) {
        var networkIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/ip/list/"
        });
        networkIPAddressRestStore.query({vpcId:vpcId}).then(function(data) { 
            if(data[0].result === "OK") {
                registry.byId("userToaster").setContent(translator.common.message.addIpToVpcSuccess, "message");
                registry.byId("userToaster").show();
                dijit.byId('generalPublicLBLoader').hide();
                core.router.go("#/user/vpc/viewLB/"+ipId);
            }
        });
    },
    populateValues : function () {
        if(dijit.byId("generalPublicLBGrid")) {                                    
            dijit.byId("generalPublicLBGrid").destroyRecursive();                    
        }
        if(dijit.byId("generalPublicLBZoneWidget")) {                                    
            dijit.byId("generalPublicLBZoneWidget").destroyRecursive();                    
        }
        if(dijit.byId("generalPublicLBVPCWidget")) {                                    
            dijit.byId("generalPublicLBVPCWidget").destroyRecursive();                    
        }
        
        
        dojo.byId("vpcGeneralPublicLB").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var LBData = {
            items: []
        }; 
        var lbList = new ItemFileWriteStore({data: LBData}); 
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if ((data.isSourceNat === true || data.isSourceNat === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/vpc/viewLB/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if((data.isStaticNat === true || data.isStaticNat === "true") && data.zoneType === "Advanced") {
                            return "<a href='#/user/vpc/viewLB/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else if((data.isStaticNat === false || data.isStaticNat === "false") && (data.isSourceNat === false || data.isSourceNat === "false") && data.zoneType === "Advanced") {
                            return "<a href='#/user/vpc/viewLB/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        } else {
                            return  data.ip;
                        }
                }},
                {'name': translator.common.vpc.name, 'field': 'vpc', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.tier, 'field': 'network', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.policy, 'field': 'lbNo', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},                
                {'name': translator.common.zone, 'field': 'zone', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},                
                {'name': translator.common.networkType, 'field': 'networkType', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vm, 'hidden': 'true', 'field': 'vm', 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];
         var lbRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/overall/listPublicLB"
         });
         
          var vpcRestStore = new JsonRest({
             target: core.getContextPath()+"/api/vpc"
         });
         var currentMainZoneID = ""  
         var currentMainVPC = ""

        if(dojo.byId("selectedANZoneID").value === "All" || dojo.byId("selectedANZoneID").value === " " || dojo.byId("selectedANZoneID").value === "undefind" || dojo.byId("selectedANZoneID").value === "") { 
            currentMainZoneID = "";
        } else {
            currentMainZoneID = dojo.byId("selectedANZoneID").value;            
        }

        if(dojo.byId("selectedANVPCID").value === "All" || dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }
        
         lbRestStore.query({zoneReferenceId:currentMainZoneID, vpcId:currentMainVPC}).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noGeneralPublicLBMsg").style.display = "block";
                 dojo.byId("vpcGeneralPublicLB").innerHTML = "";
                 
                 vpcRestStore.query({referenceId: currentMainVPC}).then(function (dataCheck) {
                    if(dataCheck.length === 0 || dataCheck[0].localizedMessage) {
                        dojo.byId("noLBMsg").innerHTML = translator.common.noVPCandLBMsg;
                        dojo.query("#publicLBListActionButtonCollection").toggleClass("hide_text", true); 
                    } else {
                        dojo.byId("noLBMsg").innerHTML = translator.common.noLBMsgInfo;
                        dojo.query("#publicLBListActionButtonCollection").removeClass("hide_text", true); 
                    }
                });
             } else {
                 dojo.byId("noGeneralPublicLBMsg").style.display = "none";                 
                 dojo.forEach(data, function(resultData) {                     
                     if(resultData.isStaticNat === false && resultData.zoneType === "Advanced") {
                        lbList.newItem({
                            id: resultData.referenceId,
                            ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat, zoneType:resultData.zoneType},
                            vpc: resultData.vpc,    
                            zone: resultData.zone,
                            lbNo: resultData.lbNo,
                            vm: resultData.vm ? resultData.vm : "",
                            network: resultData.network ? resultData.network : "Default",
                            networkType: resultData.networkType ? resultData.networkType : "Shared"
                            
                        });
                    }                     
                 });                  
                 dojo.byId("vpcGeneralPublicLB").innerHTML = "";
                 var lbGrid = new EnhancedGrid({
                    id: 'generalPublicLBGrid',
                    "class" : "span12",
                    store: lbList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                lbGrid.placeAt("vpcGeneralPublicLB");
                lbGrid.startup();
              }                           
         });    
         
         
         var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storeList = new ItemFileWriteStore({data: option});                        

        var zoneWidget = new FilteringSelect({
            id: "generalPublicLBZoneWidget",            
            store: storeList,   
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectZone,
            onChange: function() {
                ListAllPublicLBInfo.loadZoneData(this);
            }
        }).placeAt("generalPublcLBZoneList");
        var vpcWidget = new FilteringSelect({
            id: "generalPublicLBVPCWidget",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectVPC,
            onChange: function() {  
//                ListAllVPCVMInfo.loadVPCData(this);
            }
        }).placeAt("generalPublicLBVPCList");        
        
        ListAllPublicLBInfo.loadAddLBInfo();
         
    }
}


var ListAllVPCVMInfo = {
    getStartConformation : function () {
        dijit.byId("generalStartTierVMDialog").show();
    },
    closeStartDialog : function () {
        dijit.byId("generalStartTierVMDialog").hide();
    },
    getGridData :  function() {
        var instanceGrid = dijit.byId("generalVMGrid");
        var currentId;
        var  dataItem = instanceGrid.selection.getSelected();
        dataItem.disabled = 'true';
        dojo.forEach(dataItem, function(el) {
            currentId = el.id;
        });
        return currentId;
    },
    startInstance : function () {
        var currentId = ListAllVPCVMInfo.getGridData();
          var referenceId;
           dojo.forEach(currentId, function(el) {
               referenceId = el;
           });
            var instanceStartRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/start/"         
            });
            instanceStartRestStore.add({
                referenceId:  referenceId
            }).then(function(data) {
                 dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmStartJobStatus = setInterval(function(){ListAllVPCVMInfo.vmJob(resultData.jobId, vmStartJobStatus);},3000);  
                        var index = dijit.byId("generalVMGrid").selection.selectedIndex;
                        var item = dijit.byId("generalVMGrid").getItem(index);
                        var store = dijit.byId("generalVMGrid").store;
                        var data = {id: currentId, stat:"in progress"};    
                        store.setValue(item, 'status', "in progress");
                        store.setValue(item, 'action', data);                                                                             
                        dijit.byId("generalVMGrid").update();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.stopVMError, "error");
                        registry.byId("userToaster").show();
                    }
                });    
            });
            dijit.byId("generalStartTierVMDialog").hide();  
    },
    vmJob: function(jobId, vmJobStatus, firewalId) {
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/job/"
            });             
            
            var index = dijit.byId("generalVMGrid").selection.selectedIndex;
            var item = dijit.byId("generalVMGrid").getItem(index);
            var store = dijit.byId("generalVMGrid").store;
            jobStore.add({
                jobId : jobId            
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult === "OK") {                        
                        clearInterval(vmJobStatus);                               
                         store.setValue(item, 'id',resultData.referenceId);
                         store.setValue(item, 'vmName',{vmName: resultData.name, osCategory: resultData.osCategory, id: resultData.vmId, state: resultData.state, referenceId: resultData.referenceId});                         
                         store.setValue(item, 'status',resultData.state);
                         store.setValue(item, 'action',{id: resultData.referenceId, stat: resultData.state});
                         dijit.byId("generalVMGrid").update();                   
                         registry.byId("userToaster").setContent(translator.common.message.success, "message");
                         registry.byId("userToaster").show();
                    } else if(resultData.jobResult == "Pending") {
                        
                    } else if(resultData.jobResult == "FAILED") {
                        store.setValue(item, 'status',"Running");
                        clearInterval(vmJobStatus);     
                        dijit.byId("generalVMGrid").update();    
                        registry.byId("userToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                        registry.byId("userToaster").show();                        
                    }
                });
            });
    },
    getStopConformation: function() {  
            dijit.byId("stopGeneralTierInstanceAgreement").set("checked", false);
            dijit.byId("stopGenereralTierDialog").show();
    },
    closeStopDialog : function () {
        dijit.byId("stopGenereralTierDialog").hide();
    },
    getRebootConformation : function () {
        dijit.byId("rebootGeneralVMTierDialog").show();  
    },
    closeRebootDialog : function () {
        dijit.byId("rebootGeneralVMTierDialog").hide();
    },
    rebootInstance: function() {                                     
        var currentId = ListAllVPCVMInfo.getGridData();
        var referenceId;
        var vmStat = "";
        dojo.forEach(currentId, function(el) {
            referenceId = el;
        });
        var  dataItem = dijit.byId("generalVMGrid").selection.getSelected();
        var instanceRebootRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/reboot/"         
        });
        instanceRebootRestStore.add({
            referenceId: referenceId
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var vmStartJobStatus = setInterval(function(){ListAllVPCVMInfo.vmJob(resultData.jobId, vmStartJobStatus);},3000);  
                    var index = dijit.byId("generalVMGrid").selection.selectedIndex;
                    var item = dijit.byId("generalVMGrid").getItem(index);
                    var store = dijit.byId("generalVMGrid").store;
                    var data = {id: currentId, stat:"in progress"};    
                    store.setValue(item, 'status', "in progress");
                    store.setValue(item, 'action', data);                                                            
                    dijit.byId("generalVMGrid").update();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.rebootVMError, "error");
                    registry.byId("userToaster").show();
                    dojo.forEach(dataItem, function(el) {
                        
                        
                        var vmNameData = {vmName: el.vmName, osCategory: el.osCategory, id: el.id, state: vmStat, referenceId: el.referenceId}
                        store.setValue(item, 'vmName', vmNameData);                                        
                    });
                }
            });    
        });
         dijit.byId("rebootGeneralVMTierDialog").hide();
    },
    stopInstance: function() {     
        var currentId = ListAllVPCVMInfo.getGridData();
        var referenceId;
        dojo.forEach(currentId, function(el) {
            referenceId = el;
        });
        var instanceStopRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/stop/"         
        });

        var force = dijit.byId("stopGeneralTierInstanceAgreement").checked;
        instanceStopRestStore.add({
            referenceId: referenceId,
            forced: force
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var vmStartJobStatus = setInterval(function(){ListAllVPCVMInfo.vmJob(resultData.jobId, vmStartJobStatus);},3000);  
                        var index = dijit.byId("generalVMGrid").selection.selectedIndex;
                        var item = dijit.byId("generalVMGrid").getItem(index);
                        var store = dijit.byId("generalVMGrid").store;
                        var data = {id: currentId, stat:"in progress"};    
                        store.setValue(item, 'status', "in progress");
                        store.setValue(item, 'action', data);                                                                             
                        dijit.byId("generalVMGrid").update();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.stopVMError, "error");
                    registry.byId("userToaster").show();
                }
            });                
        });
        dijit.byId("stopGenereralTierDialog").hide();
        },
        closeRestoreVMDialog : function () {
            dijit.byId("vmGeneralTierRestoreDialog").hide();
        },
        restoreInstance : function() {    
           var currentId = ListAllVPCVMInfo.getGridData();                      
            var index = dijit.byId("generalVMGrid").selection.selectedIndex;
            var item = dijit.byId("generalVMGrid").getItem(index);
            var store = dijit.byId("generalVMGrid").store;                      
            
            var instanceStopRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/restore/"         
            });  
            var referenceId = dojo.byId("currentGeneralTierVmID").value;
            dijit.byId("vmGeneralTierRestoreDialog").hide();
            instanceStopRestStore.add({
                referenceId: referenceId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
//                        var vmRestoreJobStatus = setInterval(function(){UserInstances.vmJob(resultData.jobId, vmRestoreJobStatus);},3000);  
                        registry.byId("userToaster").setContent(translator.common.instance.restoreVMSucess, "message");
                        registry.byId("userToaster").show();                                                                                                                      
                         store.setValue(item, 'status', "Stopped");
                         var data = {id: currentId, stat:"Stopped"}; 
                         store.setValue(item, 'action', data);   
                         dijit.byId("generalVMGrid").update();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.restoreVMError, "error");
                        registry.byId("userToaster").show();
                        store.setValue(item, 'status', "Destroyed"); 
                        dijit.byId("generalVMGrid").update();                        
                    }
                });                
            });
        },
        getDeleteConformation : function() {
             dijit.byId("deleteGeneralTierVMDialog").show();  
    },
    closeDeleteDialog : function () {
         dijit.byId("deleteGeneralTierVMDialog").hide();
    },
    deleteInstance : function () {
        var currentId = ListAllVPCVMInfo.getGridData();
            var referenceId;
            var firewalId;
            dojo.forEach(currentId, function(el) {
                referenceId = el;
            });
            var instanceGrid = dijit.byId("generalVMGrid");
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
            
            instanceDeleteRestStore.add({
                referenceId: referenceId,
                forced: "true"
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmStopJobStatus = setInterval(function(){ListAllVPCVMInfo.vmJob(resultData.jobId, vmStopJobStatus, firewalId);},3000);  
                        var index = dijit.byId("generalVMGrid").selection.selectedIndex;
                        var item = dijit.byId("generalVMGrid").getItem(index);
                        var store = dijit.byId("generalVMGrid").store;                        
                        store.setValue(item, 'status', "in progress");       
                        var data = {id: currentId, stat:"in progress"}; 
                        store.setValue(item, 'action', data);                        
                        dijit.byId("generalVMGrid").update();                          
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.deleteVMError, "error");
                        registry.byId("userToaster").show();
                    }
                });     
            }); 
            dijit.byId("deleteGeneralTierVMDialog").hide();  
    },
    showLaunchVMDialog : function () {
        ListAllVPCVMInfo.loadAddVMInfo();
        dijit.byId("generalVMConfirmDialog").show();        
    },
    addVM : function () {
        var zoneWidget = dijit.byId("generalVMZoneWidget").value;
        var vpcWidget = dijit.byId("generalVMVPCWidget").value;
        var tierWidget = dijit.byId("generalVMTierWidget").value;
        if(zoneWidget === "option" || zoneWidget === "" || vpcWidget === "option" || vpcWidget === "" || tierWidget === "option" || tierWidget === "") {
            dojo.byId("addVMErrorMessage").style.display = "block";            
        } else {
            var zoneId;
            dijit.byId("generalVMZoneWidget").store.fetch({query: {id: zoneWidget},
                onItem: function(item) {
                    zoneId = item.zoneId;
                }
            }); 
            dojo.byId("addVMErrorMessage").style.display = "none";   
            dijit.byId("generalVMConfirmDialog").hide();  
            var tierId = tierWidget;
            var zoneId = zoneId;
            var collections = tierId + "," + zoneId + "," + "tierOption";
            core.router.go("#/user/cloud/createVm/" + collections);
        }    
    },
    cancelVM : function () {
        dijit.byId("generalVMConfirmDialog").hide();
    },
    loadAddVMInfo : function () {                
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOption});
        dojo.query("#generalTierZoneList").toggleClass("hide_text", true);
        dojo.query("#generalVPCZoneList").toggleClass("hide_text", true);
        dojo.query("#generalVMTierList").toggleClass("hide_text", true);        
        
        dojo.query("#generalTierZoneLoader").removeClass("hide_text", true);
        dojo.query("#generalTierVPCLoader").removeClass("hide_text", true);
        dojo.query("#generalVMTierLoader").removeClass("hide_text", true);         
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var currentVPC = dojo.byId("selectedANVPCID").value;
        var zoneRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/zone/"
        });
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc"
        });                      
        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                              
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                zoneRestResource.query().then(function (data) {
                    dojo.forEach(data, function (el) {
                        if(el.networkType === "Advanced") {
                            zoneList.newItem({id: el.referenceZoneId, name: el.aliasName, zoneId: el.id});
                        }                    
                    });
                    dijit.byId("generalVMZoneWidget").set("store",zoneList);
                    dijit.byId("generalVMZoneWidget").set("value","option");
                    
                    dijit.byId("generalVMZoneWidget").onChange = function () {
                        ListAllVPCVMInfo.loadZoneData(dijit.byId("generalVMZoneWidget"));
                    };   
                    dojo.query("#generalVMZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVMZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVMTierList").removeClass("hide_text", true);
                    dojo.query("#generalVMTierLoader").toggleClass("hide_text", true);
                });
            } else {                    
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone, zoneId: el.zoneId});
                        dijit.byId("generalVMZoneWidget").onChange = function () {};
                        dijit.byId("generalVMZoneWidget").set("store", zoneList);
                        dijit.byId("generalVMZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalVMZoneList").removeClass("hide_text", true);
                        dojo.query("#generalVMZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;  
                        
                        var tierOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };                        
                        var tierList = new ItemFileWriteStore({data: tierOption});
                        var tierOfferRestStore = new JsonRest({
                            target: core.getContextPath() + "/api/network/tier/list"
                        });            
                        tierOfferRestStore.query({vpcId: el.referenceId}).then(function(data) {
                            if(data === "" || data.length === 0 || data === "undefined" || data[0].name === "undefined") {
                                tierList.newItem({id: "option", name: translator.common.selectTier});
                                dijit.byId("generalVMTierWidget").set("store", tierList);
                                dijit.byId("generalVMTierWidget").set("value", "option");
                                
                                dojo.query("#generalVMTierList").removeClass("hide_text", true);
                                dojo.query("#generalVMTierLoader").toggleClass("hide_text", true);
                            } else {
                                var firstTier = ""
                                dojo.forEach(data, function (el, index) {
                                    tierList.newItem({id: el.id, name: el.name});
                                    if(index === 0) {
                                        firstTier = el.id;
                                    }
                                });                
                                dijit.byId("generalVMTierWidget").set("store", tierList);
                                dijit.byId("generalVMTierWidget").set("value", firstTier);
                                
                                dojo.query("#generalVMTierList").removeClass("hide_text", true);
                                dojo.query("#generalVMTierLoader").toggleClass("hide_text", true);
                        }                
                    });                                                   
                });
                dijit.byId("generalVMVPCWidget").set("store", singleVPCList);
                dijit.byId("generalVMVPCWidget").set("value", firstVPC);   
                dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
                dojo.query("#generalVMVPCLoader").toggleClass("hide_text", true);
            });                               
        }                                                
        } else {                                                
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                var singleZoneOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleZoneList = new ItemFileWriteStore({data: singleZoneOption});
                zoneRestResource.get(currentZoneID).then(function (data) {
                    if(data.networkType === "Advanced") {
                        singleZoneList.newItem({id: data.referenceZoneId, name: data.aliasName, zoneId: data.id});
                    } 
                    dijit.byId("generalVMZoneWidget").set("store", singleZoneList);
                    dijit.byId("generalVMZoneWidget").set("value", data.referenceZoneId);                    
                    dijit.byId("generalVMZoneWidget").onChange = function () {
                        ListAllVPCVMInfo.loadZoneData(dijit.byId("generalVMZoneWidget"));
                    }; 
                    dojo.query("#generalVMZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVMZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVMTierList").removeClass("hide_text", true);
                    dojo.query("#generalVMTierLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
                    dojo.query("#generalVMVPCLoader").toggleClass("hide_text", true);
                });
            } else {      
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone, zoneId: el.zoneId});
                        dijit.byId("generalVMZoneWidget").onChange = function () {};
                        dijit.byId("generalVMZoneWidget").set("store", zoneList);
                        dijit.byId("generalVMZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalVMZoneList").removeClass("hide_text", true);
                        dojo.query("#generalVMZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;  
                        
                        var tierOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };                        
                        var tierList = new ItemFileWriteStore({data: tierOption});
                        var tierOfferRestStore = new JsonRest({
                            target: core.getContextPath() + "/api/network/tier/list"
                        });            
                        tierOfferRestStore.query({vpcId: el.referenceId}).then(function(data) {
                            if(data === "" || data.length === 0 || data === "undefined" || data[0].name === "undefined") {
                                tierList.newItem({id: "option", name: translator.common.selectTier});
                                dijit.byId("generalVMTierWidget").set("store", tierList);
                                dijit.byId("generalVMTierWidget").set("value", "option");
                                
                                dojo.query("#generalVMTierList").removeClass("hide_text", true);
                                dojo.query("#generalVMTierLoader").toggleClass("hide_text", true);
                            } else {
                                var firstTier = ""
                                dojo.forEach(data, function (el, index) {
                                    tierList.newItem({id: el.id, name: el.name});
                                    if(index === 0) {
                                        firstTier = el.id;
                                    }
                                });                
                                dijit.byId("generalVMTierWidget").set("store", tierList);
                                dijit.byId("generalVMTierWidget").set("value", firstTier);
                                
                                dojo.query("#generalVMTierList").removeClass("hide_text", true);
                                dojo.query("#generalVMTierLoader").toggleClass("hide_text", true);
                        }                
                    });                                                   
                });
                dijit.byId("generalVMVPCWidget").set("store", singleVPCList);
                dijit.byId("generalVMVPCWidget").set("value", firstVPC);   
                dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
                dojo.query("#generalVMVPCLoader").toggleClass("hide_text", true);
            });
        } 
    }                          
    },
    loadZoneData : function (currentZone) {        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.query("#generalVMVPCList").toggleClass("hide_text", true);
        dojo.query("#generalVMVPCLoader").removeClass("hide_text", true);
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        if(currentZone.value === "option") {
            vpcList.newItem({id: "option", name: translator.common.selectVPC});
            dijit.byId("generalVMVPCWidget").set("store", vpcList);
            dijit.byId("generalVMVPCWidget").set("value", "option");
            
            dojo.query("#generalVMVPCList").removeClass("hide_text", true);
            dojo.query("#generalVMVPCLoader").toggleClass("hide_text", true);
            
        } else {
            var vpcOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc"
            });
            
            vpcOfferRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                var firstVPC = ""
                dojo.forEach(data, function (el, index) {
                    vpcList.newItem({id: el.referenceId, name: el.name});
                    if(index === 0) {
                        firstVPC = el.referenceId;
                    }
                });                
                dijit.byId("generalVMVPCWidget").set("store", vpcList);
                dijit.byId("generalVMVPCWidget").set("value", firstVPC);    
                
                dojo.query("#generalVMVPCList").removeClass("hide_text", true);
                dojo.query("#generalVMVPCLoader").toggleClass("hide_text", true);
            });            
        }        
    },
    loadVPCData : function (currentVPC) {                               
        dojo.query("#generalVMTierLoader").removeClass("hide_text", true);
        dojo.query("#generalVMTierList").toggleClass("hide_text", true);                        
        var tierOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var tierList = new ItemFileWriteStore({data: tierOption});
        if(currentVPC.value === "option") {
            tierList.newItem({id: "option", name: translator.common.selectTier});            
            dijit.byId("generalVMTierWidget").set("store", tierList);                
            dijit.byId("generalVMTierWidget").set("value", "option");                
            
            dojo.query("#generalVMTierList").removeClass("hide_text", true);
            dojo.query("#generalVMTierLoader").toggleClass("hide_text", true); 
            
        } else {    
            var firstTier = "";
            
            
        if(currentVPC.value === "option") {
            tierList.newItem({id: "option", name: translator.common.selectTier});
            dijit.byId("generalVMTierWidget").set("store", tierList);
            dijit.byId("generalVMTierWidget").set("value", "option");
            
            dojo.query("#generalVMTierList").removeClass("hide_text", true);
            dojo.query("#generalVMTierLoader").toggleClass("hide_text", true); 
        } else {
            var tierOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/tier/list"
            });            
            tierOfferRestStore.query({vpcId: currentVPC.value}).then(function(data) {
                if(data === "" || data.length === 0 || data === "undefined" || data[0].name === "undefined") {
                    tierList.newItem({id: "option", name: translator.common.selectTier});
                    dijit.byId("generalVMTierWidget").set("store",tierList);
                    dijit.byId("generalVMTierWidget").set("value","option");
                    dojo.query("#generalVMTierList").removeClass("hide_text", true);
                    dojo.query("#generalVMTierLoader").toggleClass("hide_text", true); 
                } else {
                    dojo.forEach(data, function (el, index) {
                        tierList.newItem({id: el.id, name: el.name});
                        if(index === 0) {
                            firstTier = el.id;
                        }
                    });                
                    dijit.byId("generalVMTierWidget").set("store", tierList);
                    dijit.byId("generalVMTierWidget").set("value", firstTier);
                    dojo.query("#generalVMTierList").removeClass("hide_text", true);
                    dojo.query("#generalVMTierLoader").toggleClass("hide_text", true); 
                }                
            });                                
        }
    }
    },
    populateValues : function () {
        
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.vmLimit !== "-1") {                   
                    if((limitData.vmUsed >= limitData.vmLimit)) {
                        dojo.byId("vpcvmListRachedLimitMsg").style.display = "block";
                        dojo.query("#vpcVMListAddButton").toggleClass("hide_text", true);                                           
                    } else {
                        dojo.byId("vpcvmListRachedLimitMsg").style.display = "none";
                        dojo.query("#vpcVMListAddButton").removeClass("hide_text", true);    
                    }
                } else {
                   dojo.byId("vpcvmListRachedLimitMsg").style.display = "none";
                   dojo.query("#vpcVMListAddButton").removeClass("hide_text", true);
                }
            });
        });
        
        if(dijit.byId("generalVMGrid")) {                                    
            dijit.byId("generalVMGrid").destroyRecursive();                    
        }
        if(dijit.byId("generalVMZoneWidget")) {                                    
            dijit.byId("generalVMZoneWidget").destroyRecursive();                    
        }
        if(dijit.byId("generalVMVPCWidget")) {                                    
            dijit.byId("generalVMVPCWidget").destroyRecursive();                    
        }          
        if(dijit.byId("generalVMTierWidget")) {                                    
            dijit.byId("generalVMTierWidget").destroyRecursive();                    
        }          
        dojo.byId("vpcGeneralVMList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vmData = {
            items: []
        };         
        
        var vmDataList = new ItemFileWriteStore({data: vmData}); 
        var vmLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function(data) {                                             
                        var widget = "";
                        var imageUrl= "";
                        var templateImage = "";    
                        if (data.osCategory === "CentOS") {
                            imageUrl = "images/os/os_centos_logo.png" ;
                            templateImage = "<img src='images/os/os_centos_logo.png' alt='"+translator.common.template.centOS+"' height='25' width='25'/>" + data.vmName;
                        } else if(data.osCategory === "Debian") {
                            imageUrl = "images/os/os_debian_logo.png";
                            templateImage = "<img src='images/os/os_debian_logo.png' alt='"+translator.common.template.debian+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Oracle") {
                             imageUrl = "images/os/os_oracle_logo.png";
                            templateImage = "<img src='images/os/os_oracle_logo.png' alt='"+translator.common.template.oracle+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "RedHat") {
                             imageUrl = "images/os/os_redhat_logo.png";
                            templateImage = "<img src='images/os/os_redhat_logo.png' alt='"+translator.common.template.redHat+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "SUSE") {
                             imageUrl = "images/os/os_suse_logo.png";
                            templateImage = "<img src='images/os/os_suse_logo.png' alt='"+translator.common.template.SUSE+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Windows") {
                             imageUrl = "images/os/os_win_logo.png";
                            templateImage = "<img src='images/os/os_win_logo.png' alt='"+translator.common.template.windows+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Novel") {
                             imageUrl = "images/os/os_novell_logo.png";
                            templateImage = "<img src='images/os/os_novell_logo.png' alt='"+translator.common.template.novel+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Unix") {
                             imageUrl = "images/os/os_unix_logo.png";
                            templateImage = "<img src='images/os/os_unix_logo.png' alt='"+translator.common.template.unix+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Ubuntu") {
                             imageUrl = "images/os/os_ubuntu_logo.png"; 
                            templateImage = "<img src='images/os/os_ubuntu_logo.png' alt='"+translator.common.template.ubuntu+"' height='25' width='25'/>" + data.vmName
                        } else {
                            imageUrl = ""; 
                            templateImage = "" + data.vmName;
                        }   
                        
                        var instanceGrid = dijit.byId("generalVMGrid");
                         var collections = data.id + "," + "tier";    
                          instanceGrid.store.fetch({query: {id: data.referenceId},
                              onItem: function(item) { 
                                  var currentSate = "";
                                  dojo.forEach(item.status, function(stat) {
                                      currentSate = stat;
                                  })                                 
                                  if(currentSate === "Running" || currentSate === "Stopped") {
                                      var availableWidget = new FogPanel.StorageAction({
                                          storageTagPath : "#/user/cloud/vmDetail/" + collections,
                                          nameTagClick : function() {                                                                                         
                                          }                            
                                      });
                                      availableWidget.setTagName(data.vmName);                                    
                                      availableWidget.setOsImage(imageUrl);
                                      availableWidget.disableAll();
                                      availableWidget.disableDelete();
                                      availableWidget.enableTageName();
                                     widget =  availableWidget;
                                 } else {                                    
                                     widget = templateImage;
                                 }
                             }
                         });                                                                   
                      return widget;
                }},
                {'name': translator.common.vpc.name, 'field': 'vpc', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.tier, 'field': 'tier', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.zone, 'field': 'zone', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'status', 'width': '100px', 'datatype':'string',  'autoComplete': 'true','formatter' : function (data) {
                        var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' class='vm_stat_image' alt='"+translator.common.loader.loading+"'/>" });
                        var status;                         
                        if((data != "in progress") && (data != "starting")) {
                                if(data === "Running") {
                                    status = "<div class='stat_running overflowLabel'>"+translator.common.instance.status.running+"</div>";
                                } else if(data === "Stopped") {
                                    status = "<div class='stat_stopped overflowLabel'>"+translator.common.instance.status.stopped+"</div>";
                                } else if(data === "Destroyed") {
                                    status = "<div class='stat_destroyed overflowLabel'>"+translator.common.instance.status.destroyed+"</div>";
                                } else if(data === "Error") {
                                    status = "<div class='stat_destroyed overflowLabel'>"+translator.common.error+"</div>";
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
                                status = imageDiv.innerHTML;
                            }
                            return status;
                }},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {                        
                        var instanceStatus =  new FogPanel.InstanceStatus({
                            onStartInstance : function() {
                                ListAllVPCVMInfo.getStartConformation();
                            },
                            onStopInstance : function() {
                                ListAllVPCVMInfo.getStopConformation();
                            },
                            onRebootInstance : function() {
                                ListAllVPCVMInfo.getRebootConformation();
                            },
                            onRestoreInstance : function() {
                                dojo.byId("currentGeneralTierVmID").value = data.id;
                                dijit.byId("vmGeneralTierRestoreDialog").show();
                            },
                             onDeleteInstance : function() {
                                 ListAllVPCVMInfo.getDeleteConformation();
                            }
          
                        }); 
                        instanceStatus.disableMigrateHost();                        
                        if(data.stat === "Running") {
                            instanceStatus.enableRunningState();
                        } else if(data.stat === "Stopped") {
                             instanceStatus.enableStopState();
                        } else if(data.stat === "Destroyed") {
                            instanceStatus.enableRestoreState();
                        } else if(data.stat === "Error") {
                            instanceStatus.enableDeleteStat();
                        } else {
                            instanceStatus.disableAll();
                        }
                        return instanceStatus;                       
                       
                },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
             ]
         ];
         var instanceRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/overall/vmList"
         });
         var tierRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/tier/list"
         });
         
        var currentMainZoneID = ""  
        var currentMainVPC = ""
        
        if(dojo.byId("selectedANZoneID").value === "All" || dojo.byId("selectedANZoneID").value === " " || dojo.byId("selectedANZoneID").value === "undefind" || dojo.byId("selectedANZoneID").value === "") { 
            currentMainZoneID = "";
        } else {
            currentMainZoneID = dojo.byId("selectedANZoneID").value;            
        }
        
        if(dojo.byId("selectedANVPCID").value === "All" || dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }
         instanceRestStore.query({zoneReferenceId: currentMainZoneID, vpcId: currentMainVPC}).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("vpcGeneralVMList").innerHTML = "";
                 dojo.byId("noGeneralVPCMVMsg").style.display = "block";      
                 
                 tierRestStore.query({vpcId: currentMainVPC}).then(function (checkData) {                    
                    if(checkData.length === 0 || checkData[0].localizedMessage) {
                        dojo.byId("noVMMsg").innerHTML = translator.common.noVPCandVMMsg;
                        dojo.query("#vmListActionButtonCollection").toggleClass("hide_text", true); 
                    } else {
                        dojo.byId("noVMMsg").innerHTML = translator.common.noVMListMsg;
                        dojo.query("#vmListActionButtonCollection").removeClass("hide_text", true); 
                    }
                });
                 
             } else {
                 dojo.byId("noGeneralVPCMVMsg").style.display = "none";
                 dojo.forEach(data, function(vm) {
                     vmDataList.newItem({
                         id:vm.referenceId,
                         vpc: vm.vpc,
                         tier: vm.tierList,
                         vmName:{vmName: vm.name, osCategory: vm.osCategory, id: vm.vmId, state: vm.state, referenceId: vm.referenceId},
                         zone: vm.zone, 
                         status: vm.state,
                         action : {id: vm.referenceId, stat: vm.state}
                     });
                 });                  
                 dojo.byId("vpcGeneralVMList").innerHTML = "";
                 var tierVMGrid = new EnhancedGrid({
                     id: 'generalVMGrid',
                     "class" : "span12",
                      store: vmDataList,
                      structure: vmLayout,
                      autoHeight: true,
                      plugins: core.getGridConfig().plugins
                  });
                  tierVMGrid.placeAt("vpcGeneralVMList");
                  tierVMGrid.startup();  
              }                                
          });         
        var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storeList = new ItemFileWriteStore({data: option});                        

        var zoneWidget = new FilteringSelect({
            id: "generalVMZoneWidget",            
            store: storeList,   
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectZone,
            onChange: function() {
                ListAllVPCVMInfo.loadZoneData(this);
            }
        }).placeAt("generalVMZoneList");
        var vpcWidget = new FilteringSelect({
            id: "generalVMVPCWidget",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectVPC,
            onChange: function() {  
                ListAllVPCVMInfo.loadVPCData(this);
            }
        }).placeAt("generalVMVPCList");
        
        var tierWidget = new FilteringSelect({
            id: "generalVMTierWidget",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectVPC,
            onChange: function() {               
            }
        }).placeAt("generalVMTierList");
        ListAllVPCVMInfo.loadAddVMInfo();
    }    
};

var AddVPCInfo = {
    cancelTier : function () {
        AddVPCInfo.loadAddTierInfo();
        dijit.byId("generalTierName").reset();
        dijit.byId("generalTierGateway").reset();
        dijit.byId("generalTierNetmask").reset();
        dijit.byId("generalTierName").reset();
        
    },
    'addTier': function() {
        var name = dijit.byId("generalTierName").value;
        var gateway = dijit.byId("generalTierGateway").value;
        var networkOffer = dijit.byId("generalTierNetworkOfferWidget").value;
        var netmask = dijit.byId("generalTierNetmask").value;
        var acl = dijit.byId("generalTierAcl").value;
        var currentZone = dijit.byId("generalTierZoneWidget").value;
        var currentVPC = dijit.byId("generalTierVPCWidget").value;        

        var pageNode = dojo.byId("vpcGeneralAddTierPageDiv");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            if(currentZone === "option" || currentVPC === "option" || networkOffer === "" || networkOffer === "option") {
                dojo.byId("addTierErrorMessage").style.display = "block";
            } else {
                var tierType;
                dijit.byId("generalTierNetworkOfferWidget").store.fetch({query: {id: networkOffer},
                    onItem: function(item) {
                        tierType=item.tierType;
                    }
                });                 
                if(tierType.toString() === "APP") {                    
                    dojo.byId("addTierErrorMessage").style.display = "none";
                    AddVPCInfo.createGeneralTier();
                } else {                     
                    if(acl === "" || acl === "noAcl" || acl === "select" || acl === " ") {                        
                        dojo.byId("addTierErrorMessage").style.display = "block";
                    } else {                       
                        dojo.byId("addTierErrorMessage").style.display = "none";
                        AddVPCInfo.createGeneralTier();
                    }                    
                }
            }            
        }
    },
    createGeneralTier : function () {
        var name = dijit.byId("generalTierName").value;
        var gateway = dijit.byId("generalTierGateway").value;
        var networkOffer = dijit.byId("generalTierNetworkOfferWidget").value;
        var netmask = dijit.byId("generalTierNetmask").value;
        var acl = dijit.byId("generalTierAcl").value;
        var currentZone = dijit.byId("generalTierZoneWidget").value;
        var currentVPC = dijit.byId("generalTierVPCWidget").value;   
        
        var tierRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/tier/create"
        });
        dijit.byId("addTierLoader").show();        
        tierRestStore.add({
            netmask: netmask, 
            acl:acl,
            name: name, 
            gateway: gateway, 
            networkOffer: networkOffer, 
            vpcId: currentVPC
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                    registry.byId("userToaster").setContent(translator.common.message.tierCreate, "message");
                    registry.byId("userToaster").show();
                    dijit.byId("addTierLoader").hide();
                    dijit.byId("vpcGeneralAddTierDialog").hide();
                    dojo.query("#listAddTierCloudstackException").toggleClass("hide_text", true);
                    dojo.byId("listAddTierCloudstackExceptionMsg").innerHTML = "";
                    core.router.go("#/user/tier/list");
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("addTierLoader").hide();       
                    dojo.query("#listAddTierCloudstackException").removeClass("hide_text", true);
                    dojo.byId("listAddTierCloudstackExceptionMsg").innerHTML = resultData.message;
                }
            });
        });
    },
    populateValues : function () {
        dojo.query("#listAddTierCloudstackException").toggleClass("hide_text", true);
        dojo.byId("listAddTierCloudstackExceptionMsg").innerHTML = "";
        
        if(dijit.byId("generalTierZoneWidget")) {
            dijit.byId("generalTierZoneWidget").destroyRecursive();
        }
        if(dijit.byId("generalTierVPCWidget")) {
            dijit.byId("generalTierVPCWidget").destroyRecursive();
        }
        if(dijit.byId("generalTierNetworkOfferWidget")) {
            dijit.byId("generalTierNetworkOfferWidget").destroyRecursive();
        }
        if(dijit.byId("generalTierAcl")) {
            dijit.byId("generalTierAcl").destroyRecursive();
        }
        
        
        var tierRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/tier/list"
        });
        var vpcRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc"
        });        
        
        var currentMainZoneID = "";
        var currentMainVPC = "";
        
        if(dojo.byId("selectedANZoneID").value === "All" || dojo.byId("selectedANZoneID").value === " " || dojo.byId("selectedANZoneID").value === "undefind" || dojo.byId("selectedANZoneID").value === "") { 
            currentMainZoneID = "";
        } else {
            currentMainZoneID = dojo.byId("selectedANZoneID").value;            
        }
        
        if(dojo.byId("selectedANVPCID").value === "All" || dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }        
        
        var listData = {
            items: []
        }; 
        var list = new ItemFileWriteStore({data: listData}); 
         

       
        var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storeList = new ItemFileWriteStore({data: option});                        

        var zoneWidget = new FilteringSelect({
            id: "generalTierZoneWidget",            
            store: storeList,   
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectZone,
            onChange: function() {                
            }
        }).placeAt("generalTierZoneList");
        var vpcWidget = new FilteringSelect({
            id: "generalTierVPCWidget",            
            store: storeList,  
            sortByLabel: false,
            placeHolder : translator.common.selectVPC,
            autoHeight: true,
            onChange: function() {
                AddVPCInfo.loadVPCData(this);
            }
        }).placeAt("generalVPCZoneList");
        var networkOfferWidget = new FilteringSelect({
            id: "generalTierNetworkOfferWidget",            
            store: storeList,    
            placeHolder : translator.common.selectNetworkOffer,
            autoHeight: true,
            onChange: function() {
                AddVPCInfo.showACLList(this);
            }
        }).placeAt("GeneralNetworkOfferingList");        

        var aclListWidget = new FilteringSelect({
            id: "generalTierAcl",            
            sortByLabel: false,  
            autoHeight: true,
            required : false,
            placeHolder : translator.common.selectACL,
            store: storeList
        }).placeAt("generalTierAclList");                 
        AddVPCInfo.loadAddTierInfo();
    },
    loadZoneData : function (currentZone) {
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.query("#generalVPCZoneList").toggleClass("hide_text", true);
        dojo.query("#generalTierVPCLoader").removeClass("hide_text", true);
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        if(currentZone.value === "option") {
            vpcList.newItem({id: "option", name: translator.common.selectVPC});
            dijit.byId("generalTierVPCWidget").set("store", vpcList);
            dijit.byId("generalTierVPCWidget").set("value", "option");
            
            dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
            dojo.query("#generalTierVPCLoader").toggleClass("hide_text", true);
            
        } else {
            var vpcOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc"
            });
            
            vpcOfferRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                var firstVPC = ""
                dojo.forEach(data, function (el, index) {
                    vpcList.newItem({id: el.referenceId, name: el.name});
                    if(index === 0) {
                        firstVPC = el.referenceId;
                    }
                });                
                dijit.byId("generalTierVPCWidget").set("store", vpcList);
                dijit.byId("generalTierVPCWidget").set("value", firstVPC);    
                
                dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
                dojo.query("#generalTierVPCLoader").toggleClass("hide_text", true);
            });            
        }
    },
    loadVPCData : function (currentVPC) {    
        
        dojo.query("#GeneralNetworkOfferingList").toggleClass("hide_text", true);
        dojo.query("#generalTierNWOfferLoader").removeClass("hide_text", true);
        dojo.query("#generalTierAclList").toggleClass("hide_text", true);
        dojo.query("#generalTierACLLoader").removeClass("hide_text", true);
        var singleOfferOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var singleACLOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/"
        });
        var networkOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer/forVpc/"
        });
       
        var singleNOfferList = new ItemFileWriteStore({data: singleOfferOption});
        var singleACLList = new ItemFileWriteStore({data: singleACLOption});            
        if(currentVPC.value === "option") {
             singleNOfferList.newItem({id: "", name: translator.common.noNetworkOffer, tierType: ""});
             singleACLList.newItem({id: "noAcl", name: translator.common.noAcl});
            dijit.byId("generalTierNetworkOfferWidget").set("store", singleNOfferList);                
            dijit.byId("generalTierNetworkOfferWidget").set("value", "");                
            
            dijit.byId("generalTierAcl").set("store", singleACLList);
            dijit.byId("generalTierAcl").set("value", "noAcl");
            
            dojo.query("#generalTierAclList").removeClass("hide_text", true);
            dojo.query("#generalTierACLLoader").toggleClass("hide_text", true);
            
            dojo.query("#GeneralNetworkOfferingList").removeClass("hide_text", true);
            dojo.query("#generalTierNWOfferLoader").toggleClass("hide_text", true);
            
        } else {    
            var firstNetwork = "";
            networkOfferRestStore.query({vpcId: currentVPC.value}).then(function(data) {
                if (!data || data.length === 0) {
                    singleNOfferList.newItem({id: "", name: translator.common.noNetworkOffer, tierType: ""});
                    firstNetwork = "";
                } else {
                    dojo.forEach(data, function(resultData, index) {
                        singleNOfferList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});
                        if(index === 0) {
                            firstNetwork = resultData.referenceId;
                        }
                    });
                }            
                dijit.byId("generalTierNetworkOfferWidget").set("store", singleNOfferList);    
                dijit.byId("generalTierNetworkOfferWidget").set("value", firstNetwork); 
                dojo.query("#GeneralNetworkOfferingList").removeClass("hide_text", true);
                dojo.query("#generalTierNWOfferLoader").toggleClass("hide_text", true);
            });    
            aclRestStore.query({vpcId: currentVPC.value}).then(function(data) {
                if (!data || data.length === 0) {
                    singleACLList.newItem({id: "noAcl", name: translator.common.noAcl});
                } else {
                    singleACLList.newItem({id: "select", name: translator.common.select});
                    dojo.forEach(data, function(resultData) {
                        singleACLList.newItem({id: resultData.referenceId, name: resultData.name});                                 
                    });
                } 
                dijit.byId("generalTierAcl").set("store", singleACLList);
                dijit.byId("generalTierAcl").set("value", "select");       
                dojo.query("#generalTierAclList").removeClass("hide_text", true);
                dojo.query("#generalTierACLLoader").toggleClass("hide_text", true);
            });                                                                                       
        }                            
        },
        'showACLList' : function(data) {
          var tierType;

          data.store.fetch({query: {id: data.value},
                onItem: function(item) {
                    tierType=item.tierType;
                }
            });  
            if(tierType.toString() === "APP") {
                dojo.byId("generalAclDiv").style.display = "none";
                dijit.byId("generalTierAcl").set("value", "");
            } else {
                dojo.byId("generalAclDiv").style.display = "block";
                dijit.byId("generalTierAcl").set("value", "select");
            }
    },
    loadAddTierInfo : function () {                
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOption});
        dojo.query("#generalTierZoneList").toggleClass("hide_text", true);
        dojo.query("#generalVPCZoneList").toggleClass("hide_text", true);
        dojo.query("#GeneralNetworkOfferingList").toggleClass("hide_text", true);
        dojo.query("#generalTierAclList").toggleClass("hide_text", true);
        
        dojo.query("#generalTierZoneLoader").removeClass("hide_text", true);
        dojo.query("#generalTierVPCLoader").removeClass("hide_text", true);
        dojo.query("#generalTierNWOfferLoader").removeClass("hide_text", true);
        dojo.query("#generalTierACLLoader").removeClass("hide_text", true);                
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectVPC}]
        };
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        
        var networkOfferOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectNetworkOffer}]
        };
        var networkOfferList = new ItemFileWriteStore({data: networkOfferOption});
        
        var nwACLOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectACL}]
        };
        var nwACLList = new ItemFileWriteStore({data: nwACLOption});                       
        
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var currentVPC = dojo.byId("selectedANVPCID").value;
        var zoneRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/zone/"
        });
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc"
        });
        
        var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/"
        });
        var networkOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer/forVpc/"
        });        
        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                              
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                zoneRestResource.query().then(function (data) {
                    dojo.forEach(data, function (el) {
                        if(el.networkType === "Advanced") {
                            zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
                        }                    
                    });
                    dijit.byId("generalTierZoneWidget").set("store",zoneList);
                    dijit.byId("generalTierZoneWidget").set("value","option");
                    
                    dijit.byId("generalTierZoneWidget").onChange = function () {
                        AddVPCInfo.loadZoneData(dijit.byId("generalTierZoneWidget"));
                    };   
                    dojo.query("#generalTierZoneList").removeClass("hide_text", true);
                    dojo.query("#generalTierZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
                    dojo.query("#generalTierVPCLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#GeneralNetworkOfferingList").removeClass("hide_text", true);
                    dojo.query("#generalTierNWOfferLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalTierAclList").removeClass("hide_text", true);
                    dojo.query("#generalTierACLLoader").toggleClass("hide_text", true);
                });
            } else {                    
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleOfferOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleACLOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                var singleNOfferList = new ItemFileWriteStore({data: singleOfferOption});
                var singleACLList = new ItemFileWriteStore({data: singleACLOption});

                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";
                    var firstNw = "";
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});

                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone});
                        dijit.byId("generalTierZoneWidget").set("store", zoneList);
                        dijit.byId("generalTierZoneWidget").set("value",el.zoneReferenceId);
                        dijit.byId("generalTierZoneWidget").onChange = function () {};
                        dojo.query("#generalTierZoneList").removeClass("hide_text", true);
                        dojo.query("#generalTierZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});   
                        firstVPC = el.referenceId;
                         networkOfferRestStore.query({vpcId: el.referenceId}).then(function(data) {
                             if (!data || data.length === 0) {
                                 singleNOfferList.newItem({id: "", name: translator.common.noNetworkOffer, tierType: ""});
                                 firstNw = "";
                             } else {
                                 dojo.forEach(data, function(resultData, index) {
                                     singleNOfferList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});
                                     if(index === 0) {
                                         firstNw = resultData.referenceId;
                                     }
                                 });
                             } 
                             dijit.byId("generalTierNetworkOfferWidget").set("store", singleNOfferList);  
                             dijit.byId("generalTierNetworkOfferWidget").set("value", firstNw); 
                             dojo.query("#GeneralNetworkOfferingList").removeClass("hide_text", true);
                             dojo.query("#generalTierNWOfferLoader").toggleClass("hide_text", true);
                         });    
                         aclRestStore.query({vpcId: el.referenceId}).then(function(data) {
                             if (!data || data.length === 0) {
                                 singleACLList.newItem({id: "noAcl", name: translator.common.noAcl});
                             } else {
                                 singleACLList.newItem({id: "select", name: translator.common.select});
                                 dojo.forEach(data, function(resultData) {
                                     singleACLList.newItem({id: resultData.referenceId, name: resultData.name});                                 
                                 });
                             } 
                             dijit.byId("generalTierAcl").set("store", singleACLList);
                             dijit.byId("generalTierAcl").set("value", "select");
                             dojo.query("#generalTierAclList").removeClass("hide_text", true);
                             dojo.query("#generalTierACLLoader").toggleClass("hide_text", true);
                         });                     
                    });
                    dijit.byId("generalTierVPCWidget").set("store", singleVPCList);
                    dijit.byId("generalTierVPCWidget").set("value", firstVPC);    
                    dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
                    dojo.query("#generalTierVPCLoader").toggleClass("hide_text", true);
                });
            }
        } else {                                                
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                var singleZoneOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleZoneList = new ItemFileWriteStore({data: singleZoneOption});
                zoneRestResource.get(currentZoneID).then(function (data) {
                    if(data.networkType === "Advanced") {
                        singleZoneList.newItem({id: data.referenceZoneId, name: data.aliasName});
                    } 
                    dijit.byId("generalTierZoneWidget").set("store", singleZoneList);
                    dijit.byId("generalTierZoneWidget").set("value", data.referenceZoneId);                    
                    dijit.byId("generalTierZoneWidget").onChange = function () {
                        AddVPCInfo.loadZoneData(dijit.byId("generalTierZoneWidget"));
                    }; 
                    dojo.query("#generalTierZoneList").removeClass("hide_text", true);
                    dojo.query("#generalTierZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
                    dojo.query("#generalTierVPCLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#GeneralNetworkOfferingList").removeClass("hide_text", true);
                    dojo.query("#generalTierNWOfferLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#generalTierAclList").removeClass("hide_text", true);
                    dojo.query("#generalTierACLLoader").toggleClass("hide_text", true);
                });
            } else {      
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleOfferOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleACLOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                var singleNOfferList = new ItemFileWriteStore({data: singleOfferOption});
                var singleACLList = new ItemFileWriteStore({data: singleACLOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";
                    var firstNW = ""
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone});
                        dijit.byId("generalTierZoneWidget").onChange = function () {};
                        dijit.byId("generalTierZoneWidget").set("store", zoneList);
                        dijit.byId("generalTierZoneWidget").set("value", el.zoneReferenceId);     
                        dojo.query("#generalTierZoneList").removeClass("hide_text", true);
                        dojo.query("#generalTierZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;
                         networkOfferRestStore.query({vpcId: el.referenceId}).then(function(data) {
                             if (!data || data.length === 0) {
                                 singleNOfferList.newItem({id: "", name: translator.common.noNetworkOffer, tierType: ""});
                                 firstNW = "";
                             } else {
                                 dojo.forEach(data, function(resultData, index) {
                                     singleNOfferList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});
                                     if(index === 0) {
                                         firstNW = resultData.referenceId;
                                     }
                                 });
                             }  
                             dijit.byId("generalTierNetworkOfferWidget").set("store", singleNOfferList);  
                             dijit.byId("generalTierNetworkOfferWidget").set("value", firstNW);  
                             dojo.query("#GeneralNetworkOfferingList").removeClass("hide_text", true);
                             dojo.query("#generalTierNWOfferLoader").toggleClass("hide_text", true);
                         });    
                         aclRestStore.query({vpcId: el.referenceId}).then(function(data) {
                             if (!data || data.length === 0) {
                                 singleACLList.newItem({id: "noAcl", name: translator.common.noAcl});
                             } else {
                                 singleACLList.newItem({id: "select", name: translator.common.select});
                                 dojo.forEach(data, function(resultData) {
                                     singleACLList.newItem({id: resultData.referenceId, name: resultData.name});                                 
                                 });
                             }
                             dijit.byId("generalTierAcl").set("store", singleACLList);
                             dijit.byId("generalTierAcl").set("value", "select"); 
                             dojo.query("#generalTierAclList").removeClass("hide_text", true);
                             dojo.query("#generalTierACLLoader").toggleClass("hide_text", true);
                         });                     
                    });
                    dijit.byId("generalTierVPCWidget").set("store", singleVPCList);
                    dijit.byId("generalTierVPCWidget").set("value", firstVPC);   
                    dojo.query("#generalVPCZoneList").removeClass("hide_text", true);
                    dojo.query("#generalTierVPCLoader").toggleClass("hide_text", true);
                });
            } 
        }  
    }
}

var ListTierInfo = {
    populateValues : function () {                                
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.networkLimit !== "-1") {                  
                    if((limitData.networkUsed >= limitData.networkLimit)) {
                        dojo.byId("tierListLimitReached").style.display = "block";
                        dojo.byId("addTierButton").style.display = "none";                                                                                                 
                    } 
                } 
            });
        }); 
        if(dijit.byId("tierListGrid")) {                                    
            dijit.byId("tierListGrid").destroyRecursive();                    
        }
        if(dijit.byId("vpcGeneralACLWidget")) {                                    
            dijit.byId("vpcGeneralACLWidget").destroyRecursive();                    
        }
        if(dijit.byId("tierGeneralEditNetworkOffering")) {                                    
            dijit.byId("tierGeneralEditNetworkOffering").destroyRecursive();                    
        }                
        dojo.byId("vpcTierList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'field': 'vpcId', 'hidden': 'true'},                
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        return "<a href='#/user/tier/view/" + data.id + "' title='" + translator.common.view + "'>" + data.name + "</a>";
                    }
                },
                {'name': translator.common.vpc.name, 'field': 'vpc', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.supportedService, 'hidden': 'true', 'field': 'supportedService', 'datatype': 'string', 'autoComplete': 'true'},                 
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var vpnStatus = "";
                        vpnStatus = new FogPanel.VPNStatus({
                            onAclVPN : function () {ListTierInfo.replaceACLTierShow(data.referenceId);},
                            onRebootVPN : function () {
                                dijit.byId("Generalcleanup").set("checked", false);
                                ListTierInfo.showRestart(data.referenceId);
                            },
                            onEditVPN : function () {ListTierInfo.showEdit(data);},
                            onDeleteVPN : function () {ListTierInfo.showDelete(data.referenceId);},
                            forTier : true,
                            aclStat : true,
                            changeAcl : translator.common.changeAcl
                        });
                        vpnStatus.removeAclMode(data.tierType);
                        return vpnStatus;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];
        var tierRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/tier/list"
        });
        var vpcRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc"
        });        
        
        var currentMainZoneID = "";
        var currentMainVPC = "";
        
        if(dojo.byId("selectedANZoneID").value === "All" || dojo.byId("selectedANZoneID").value === " " || dojo.byId("selectedANZoneID").value === "undefind" || dojo.byId("selectedANZoneID").value === "") { 
            currentMainZoneID = "";
        } else {
            currentMainZoneID = dojo.byId("selectedANZoneID").value;            
        }
        
        if(dojo.byId("selectedANVPCID").value === "All" || dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }
        tierRestStore.query({vpcId: currentMainVPC, zoneReferenceId: currentMainZoneID}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " " || data[0].name === undefined) {
                dojo.byId("vpcTierList").innerHTML = "";
                dojo.byId("noTierListMsg").style.display = "block";
                vpcRestStore.query({referenceId: currentMainVPC}).then(function (data) {
                    if(data.length === 0 || data[0].localizedMessage) {
                        dojo.byId("noTierMsg").innerHTML = translator.common.noVPCandTierMsg;
                        dojo.query("#tierListActionButtonCollection").toggleClass("hide_text", true); 
                    } else {
                        dojo.byId("noTierMsg").innerHTML = translator.common.noTierMsg;
                        dojo.query("#tierListActionButtonCollection").removeClass("hide_text", true); 
                    }
                });
            } else {
                dojo.byId("noTierListMsg").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        vpcId : resultData.vpcId,
                        name: {id: resultData.id, name: resultData.name},
                        vpc: resultData.vpcName,
                        cidr: resultData.cidr,
                        state: resultData.state,
                        gateway: resultData.gateway,
                        type: resultData.tierType,
                        supportedService: resultData.supportedService,//                      
                        action: resultData
                    });
                });
                dojo.byId("vpcTierList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: "tierListGrid",
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("vpcTierList");
                dataGrid.startup();        
                dataGrid.update();
            }            
        });

        
        var listData = {
            items: []
        }; 
        var list = new ItemFileWriteStore({data: listData}); 
        var aclReplaceListWidget = new Select({
            id: "vpcGeneralACLWidget",        
            sortByLabel: false,
            store: list
        }, "vpcGeneralReplaceAclList");       
//
        var networkOfferEditWidget = new Select({
            id: "tierGeneralEditNetworkOffering",        
            store: list,
            onChange: function() {            
            }
        }).placeAt("networkOfferingGeneralEditList");               
    },        
    showAddTierDialogu : function () {
        core.router.go("#/user/tier/addTier");   
    },
    closeReplaceAcl : function () {
        dijit.byId("generalTierReplaceACLDialog").hide();
    },
    'replaceACLTierShow'  : function(currentId) {
       dojo.byId("generalCurrentTierId").value = currentId;
       
       var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/"
        });
        var currentVpcId;      
        dijit.byId("tierListGrid").store.fetch({query: {id: currentId},
            onItem: function(item) {
                currentVpcId=item.vpcId;
            }            
        }); 
        
       var firstValue;
       var aclOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var aclFileStoreList = new ItemFileWriteStore({data: aclOptions});
       aclRestStore.query({vpcId: currentVpcId}).then(function(data) {
            if (!data || data.length == 0) {
                aclFileStoreList.newItem({id: "noAcl", name: translator.common.noAcl});
            } else {
                aclFileStoreList.newItem({id: "select", name: translator.common.select});
                dojo.forEach(data, function(resultData, index) {
                    aclFileStoreList.newItem({id: resultData.referenceId, name: resultData.name});
                    if (index === 0) {
                        firstValue = resultData.referenceId;
                    }
                });
            }
           dijit.byId("vpcGeneralACLWidget").setStore(aclFileStoreList);
           dijit.byId("vpcGeneralACLWidget").set("value", firstValue);                                    
       });
       
       dijit.byId("generalTierReplaceACLDialog").show();
   },
   'replaceAclTier': function() {
        
        var currentId = dojo.byId("generalCurrentTierId").value;
        var tierReplaceAcl = dijit.byId("vpcGeneralACLWidget");
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkAcl/change/"
        });
        
        dijit.byId("generalListTierLoader").show();
        dijit.byId("generalTierReplaceACLDialog").hide();
        
         var pageNode = dojo.byId("generalRepalceAClPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
        
            networkRestStore.add({networkId:currentId, aclId:tierReplaceAcl.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var replaceAclTierJobStat = setInterval(function(){ListTierInfo.replaceAclTierJob(resultData.jobId, replaceAclTierJobStat);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("generalListTierLoader").hide();
                    }
                });
            });
        }
    },
    'replaceAclTierJob' : function(jobId, vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job"
        });         
        jobStore.add({jobId: jobId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.aclChanged, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("generalListTierLoader").hide();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("generalListTierLoader").hide();
                } else {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("generalListTierLoader").hide(); 
                } 
            });
        });
    },
   'showRestart' : function(currentId) {
        dojo.byId("generalCurrentTierId").value = currentId;
        dijit.byId("generalTierRestartDialog").show();        
    },
    'restartTier': function() {                
        var currentId = dojo.byId("generalCurrentTierId").value;
        var cleanup = dijit.byId("Generalcleanup");
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/tier/restart"
        });
        
        dijit.byId("generalListTierLoader").show();
        dijit.byId("generalTierRestartDialog").hide();

        networkRestStore.add({tierId:currentId, cleanup:cleanup.checked}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcRestartJobStat = setInterval(function(){ListTierInfo.restartJobVpc(resultData.jobId, vpcRestartJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("generalListTierLoader").hide();
                }
            });
        });
    },
    'restartJobVpc' : function(jobId, vpcRestartJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/restart/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpcRestartSuccess, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("generalListTierLoader").hide();
                    ListTierInfo.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("generalListTierLoader").hide();
                } else {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("generalListTierLoader").hide(); 
                } 
            });
        });
    },
    'closeRestart' : function() {        
        dijit.byId("generalTierRestartDialog").hide();        
    },
    'showEdit' : function(data) {      
        var currentVpcId;              
//         tierGeneralEditNetworkOffering
       var networkOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer/forVpc/"
        });
        var networkOfferOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var networkFileStoreList = new ItemFileWriteStore({data: networkOfferOptions});       
       networkOfferRestStore.query({vpcId: data.vpcId}).then(function(data) {
            if (!data || data.length === 0) {
                networkFileStoreList.newItem({id: "", name: translator.common.noNetworkOffer, tierType: ""});
            } else {
                dojo.forEach(data, function(resultData) {
                    networkFileStoreList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});                   
                });                
            }                     
            dijit.byId("tierGeneralEditNetworkOffering").setStore(networkFileStoreList);
            setTimeout(function () {dijit.byId("tierGeneralEditNetworkOffering").set("value",data.networkOfferId);},300); 
        });   
        
        
        dojo.byId("generalEditNetworkId").value = data.id;        
        dijit.byId("vpcGeneralTierEditDesc").setValue(data.description);
        dijit.byId("vpcGeneralTierEditName").setValue(data.name);
        dijit.byId("generalTierEditNWDomain").setValue(data.networkDomain);
        
        dijit.byId("vpcGeneralEditTierDialog").show();
    }, 
    'editTierShow' : function() {
        dijit.byId("generalTierEditConformDialog").show();
        dijit.byId("generalCirdchange").reset();
    },
    'cancelEditTier' : function() {
        dijit.byId("vpcGeneralEditTierDialog").hide();
    },
    'closEditTierConform' : function() {
        dijit.byId("generalTierEditConformDialog").hide();
    },
    'editTier': function() {

        var networkId = dojo.byId("generalEditNetworkId");
        var name = dijit.byId("vpcGeneralTierEditName");
        var desc = dijit.byId("vpcGeneralTierEditDesc");
        var networkOffer = dijit.byId("tierGeneralEditNetworkOffering");
        var NWDomain = dijit.byId("generalTierEditNWDomain");
        var cirdchange = dijit.byId("generalCirdchange");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/tier/edit/"
        });
        dijit.byId("vpcGeneralEditTierDialog").hide();
        dijit.byId("generalTierEditConformDialog").hide();
        dijit.byId("generalListTierLoader").show();
        networkRestStore.put({id: networkId.value, name: name.value, desc: desc.value, networkOffer:networkOffer.value, NWDomain:NWDomain.value, cirdchange:cirdchange.checked}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcEditJobStat = setInterval(function(){ListTierInfo.vpcEditJob(resultData.jobId, vpcEditJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("generalListTierLoader").hide();
                }
            });
        });

    },
    'vpcEditJob' : function(jobId, vpcRestartJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/tier/restart/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.tierEdidtedSuccess, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("generalListTierLoader").hide();
                    ListTierInfo.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("generalListTierLoader").hide();
                } else {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("generalListTierLoader").hide(); 
                } 
            });
        });
    },
    'showDelete' : function(currentId) {
        dojo.byId("generalCurrentTierId").value = currentId;
        dijit.byId("generalTierDeleteDialog").show();        
    },    
    'closeDelete' : function() {        
        dijit.byId("generalTierDeleteDialog").hide();        
    },
    'deleteTier': function() {
            var currentId = dojo.byId("generalCurrentTierId").value;
            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/tier/delete"
            });
            
            dijit.byId("generalListTierLoader").show();
            dijit.byId("generalTierDeleteDialog").hide();
            
            networkRestStore.add({tierId:currentId}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var vpcDeleteJobStat = setInterval(function(){ListTierInfo.deleteJobTier(resultData.jobId, resultData.tierId,vpcDeleteJobStat);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("generalListTierLoader").hide();
                    }
                });
            });
        
    },
    'deleteJobTier' : function(jobId, tierId ,vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/tier/delete/job/"
        });         
        jobStore.add({jobId: jobId, tierId: tierId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.tierDeleted, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("generalListTierLoader").hide();
                    ListTierInfo.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("generalListTierLoader").hide();
                } else {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("generalListTierLoader").hide(); 
                } 
            });
        });
    }    
};


var TierInternalLoadBalancerInfo = {
     'deleteVmFromLoadBalancerRuleShow' : function(vmId) {   
    
        dojo.byId("lbCurrentVMId").value = vmId;
        dijit.byId('internalLBRemoveVMDialog').show();
        
    },
    removeVMFromLb : function() { 
        
        dijit.byId('tierLoader').show();

        var networkLoadBalancerRestStore = new JsonRest({
           target: core.getContextPath() + "/api/network/loadBalancing/removeVM"
        });
        networkLoadBalancerRestStore.add({lbId: dojo.byId("currentLoadBalancingId").value, vmId: dojo.byId("lbCurrentVMId").value }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var addLoadBalancingRuleJobStatus = setInterval(function() {
                        TierInternalLoadBalancerInfo.removeVMFromLbJob(resultData.jobId, dojo.byId("lbCurrentVMId").value, dojo.byId("currentLoadBalancingId").value, addLoadBalancingRuleJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("tierLoader").hide();
                    dijit.byId('internalLBRemoveVMDialog').hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('tierLoader').hide();    
                    dijit.byId('internalLBRemoveVMDialog').hide();
                }
            });
        });
    },
    removeVMFromLbJob : function(jobId, vmId, lbId, removeVMLoadBalancingRuleJobStatus) { 
        var removeLoadBalancerJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/removeVM/job/"
        });

        removeLoadBalancerJobRestStoreStore.add({jobId:jobId, lbId: lbId, vmId: vmId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(removeVMLoadBalancingRuleJobStatus);                    
                    registry.byId("userToaster").setContent(translator.common.message.removeVMFromLoadBalancerRuleSuccess, "message");
                    registry.byId("userToaster").show();
                   
                    TierInternalLoadBalancerInfo.populateValues();
                    
                    dijit.byId('tierLoader').hide();  
                    dijit.byId('internalLBRemoveVMDialog').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(removeVMLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    TierInternalLoadBalancerInfo.populateValues();
                    dijit.byId('tierLoader').hide();  
                    dijit.byId('internalLBRemoveVMDialog').hide();
                }
            });
        });
        
    },
    'addLoadBalancingAditionalVM' : function() { 
        
        var lbId = dojo.byId("currentLoadBalancingId").value;
        
        var selectedGridItems = dijit.byId('LBAdditionalVMListGrid').selection.getSelected();   
                
        if (selectedGridItems.length === 0) {         
            registry.byId("userToaster").setContent(translator.common.message.selectVM, "error");
            registry.byId("userToaster").show();
                
        } else { 
            var vmListArray = new Array();
            dojo.forEach(selectedGridItems, function(selectedItem, index) {    
                dojo.forEach(selectedItem.id, function (el) {                        
                    vmListArray[index] = el;
                });
            });

           dijit.byId('tierLoader').show();

           var networkLoadBalancerRestStore = new JsonRest({
              target: core.getContextPath() + "/api/network/loadBalancing/addVM"
           });
           networkLoadBalancerRestStore.add({lbId: lbId, vmList: vmListArray.toString()}).then(function(data) {
               dojo.forEach(data, function(resultData) {
                   if (resultData.result === "OK") {
                       var addLoadBalancingRuleJobStatus = setInterval(function() {
                           TierInternalLoadBalancerInfo.addLoadBalancingToVMJob(resultData.jobId, vmListArray.toString(), lbId,addLoadBalancingRuleJobStatus);
                       }, 3000);
                   } else if (resultData.result === "FAILED") {
                       registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                       registry.byId("userToaster").show();
                       dijit.byId("tierLoader").hide();
                   } else {
                       registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                       registry.byId("userToaster").show();
                       dijit.byId('tierLoader').hide();                    
                   }
               });
           });
        
        }
    },
    'addLoadBalancingToVMJob': function(jobId, vmList, lbId, addLoadBalancingRuleJobStatus) {
        var loadBalancerJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/addVM/job/"
        });

        loadBalancerJobRestStoreStore.add({jobId:jobId, lbId: lbId, vmList: vmList}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(addLoadBalancingRuleJobStatus);                    
                    registry.byId("userToaster").setContent(translator.common.message.addAdditionalVMToLoadBalancerRuleSuccess, "message");
                    registry.byId("userToaster").show();
                    
                    TierInternalLoadBalancerInfo.populateValues();
                    
                    dijit.byId('tierLoader').hide();        
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(addLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('tierLoader').hide();        
                }
            });
        });
    },
    'populateValues' : function () {                
        dojo.byId("lbRemoveVMListContainer").style.display="none";
        dojo.byId("removeLBAdditionalVMButtonDiv").style.display="none";
        dojo.query("#internalLBExistingDiv").removeClass("hide_text", true);
        dojo.byId("lbDetailPage").style.display="none";
        dojo.byId("addInternalLBButton").style.display="block";
        dojo.byId("LBAdditionalVMContainer").style.display="none";
        dojo.byId("addLBAdditionalVMButtonDiv").style.display="none";
                
        if(dijit.byId("tierInternalLBGridWidget")) {                                    
            dijit.byId("tierInternalLBGridWidget").destroyRecursive();                    
        }
        dojo.byId("tierInternalLBGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var internalLBData = {
            items: []
        }; 
        var internallbList = new ItemFileWriteStore({data: internalLBData}); 
        var internallbLayout = [
            [                
                {'name': translator.common.name, 'field': 'lbName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.description, 'field': 'lbDescription', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.sourcePort, 'field': 'lbSourcePort', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.instancePort, 'field': 'lbInstancePort', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.algorithm, 'field': 'lbAlgorithm', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                                
                {'name': translator.common.instance.name, 'field': 'lbVm', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},               
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        var html = "<a onclick='TierInternalLoadBalancerInfo.showAddLoadBalancingAdditionalVM(\"" + data.id + "\")' class='offset1' title='" + translator.common.addVM + "'><img src='images/add_vm_icon.png'></img></a></li>"+
                                   "<a onclick='TierInternalLoadBalancerInfo.showRemoveLoadBalancingVM(\"" + data.id + "\")' class='offset1' title='" + translator.common.removeVM + "'><img src='images/delete_vm_icon.png'></img></a></li>"+
                                   "<a onclick='TierInternalLoadBalancerInfo.deleteLoadBalancerRuleShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";  
                           return html;
                       }
                   }                
            ]
         ];
         var lbRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/internal/loadBalancing/get"
         });
         
         var networkRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/"
         });
         
         networkRestStore.get(dojo.byId("currentTierId").value).then(function (currentNW) {
             if(currentNW[0].length === 0 || currentNW[0] === "" || currentNW[0] === "undefined") {
                 dojo.byId("noInternalLBMsgBox").style.display = "block";
                 dojo.byId("tierInternalLBGrid").innerHTML = "";
             } else {                
                 lbRestStore.query({networkId: currentNW[0].referenceId}).then(function(data) {
                     if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                         dojo.byId("noInternalLBMsgBox").style.display = "block";
                         dojo.byId("tierInternalLBGrid").innerHTML = "";
                     } else {
                         dojo.byId("noInternalLBMsgBox").style.display = "none";                 
                         dojo.forEach(data, function(el) {                             
                             internallbList.newItem({                                  
                                 lbName : el.loadBalancerName,
                                 lbDescription:el.description,
                                 lbSourcePort: el.sourcePort, 
                                 lbInstancePort: el.instancePort,
                                 lbAlgorithm : el.algorithm,
                                 lbVm:el.vm,
                                 action: {id: el.id, name: el.loadBalancerName, algorithm: el.algorithm}
                             });                             
                         });                  
                         dojo.byId("tierInternalLBGrid").innerHTML = "";
                         var lbGrid = new EnhancedGrid({
                             id: 'tierInternalLBGridWidget',
                             "class" : "span12",
                             store: internallbList,
                             structure: internallbLayout,
                             autoHeight: true,
                             plugins: core.getGridConfig().plugins
                         });
                         lbGrid.placeAt("tierInternalLBGrid");
                         lbGrid.startup();
                     }                           
                 });
             }
         });
            
    },
    'showAddLoadBalancingAdditionalVM': function(loadBalancingId) {   
                
        dojo.byId("currentLoadBalancingId").value = loadBalancingId;
                  
        if (dijit.byId("LBAdditionalVMListGrid")) {
            dijit.byId("LBAdditionalVMListGrid").destroyRecursive();
        }
                
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/vm/list/"
        });
        
        networkVMListRestStore.query({loadBalancingId: loadBalancingId}).then(function(data) {            
            if(data.length == 0 || data == "" || data == "undefined") {
                registry.byId("userToaster").setContent(translator.common.message.allVMAddedToLB, "error");
                registry.byId("userToaster").show();
            } else {
                
                var instanceOptions = {
                        identifier: 'id',
                        label: 'name',
                        items: []
                };
                var instanceList = new ItemFileWriteStore({data: instanceOptions});

                var vmDatalayout = [
                    [   
                        {'field': 'id', 'hidden': 'true'},             
                        {'name': translator.common.name, 'field': 'name', 'width': '30%', datatype: "string"},
                        {'name': translator.common.zone, 'field': 'zone', 'width': '30%', datatype: "string"},
                        {'name': translator.common.state, 'field': 'state', 'width': '30%', datatype: "string", "formatter" : function (data) {
                                var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' class='vm_stat_image' alt='"+translator.common.loader.loading+"'/>" });
                                var status;                         
                                if((data != "in progress") && (data != "starting")) {
                                    if(data == "Running") {
                                        status = "<div class='stat_running overflowLabel'>"+translator.common.instance.status.running+"</div>";
                                    } else if(data == "Stopped") {
                                        status = "<div class='stat_stopped overflowLabel'>"+translator.common.instance.status.stopped+"</div>";
                                    } else if(data == "Destroyed") {
                                        status = "<div class='stat_destroyed overflowLabel'>"+translator.common.instance.status.destroyed+"</div>";
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

                        }
                    }                             
                    ]
                ];
                                
                setTimeout(function() { 
                    var selectedLbGrid = dijit.byId("tierInternalLBGridWidget");

                    var  currentDataItem = selectedLbGrid.selection.getSelected();
                    dojo.forEach(currentDataItem, function(el) {
                        dojo.byId("internalLoadBalancingName").innerHTML = el.lbName;
                        dojo.byId("internalLoadBalancingDesc").innerHTML = el.lbDescription;
                        dojo.byId("internalLoadBalancingSourcePort").innerHTML = el.lbSourcePort;
                        dojo.byId("internalLoadBalancingInstancePort").innerHTML = el.lbInstancePort;
                        dojo.byId("internalLoadBalancingAlgorithm").innerHTML = el.lbAlgorithm;
                        
                    });
                },300);
                
                dojo.forEach(data, function(vm) {
                    instanceList.newItem({id: vm.referenceId, name: vm.name, zone: vm.zone, state: vm.state});
                });
                var vmGrid = new dojox.grid.EnhancedGrid({
                    id: "LBAdditionalVMListGrid",
                    store: instanceList,
                    structure: vmDatalayout,
                    autoHeight: true,                  
                    editable: true,          
                    plugins: {
                        pagination: {
                            pageSizes: ["5", "10", "15", translator.common.all],
		            description: true,
		            sizeSwitch: true,
		            pageStepper: true,
		            gotoButton: true,
		                      /*page step to be displayed*/
		            maxPageStep: 4,
		                      /*position of the pagination bar*/
		            position: "bottom"
                        },
                        'filter': {                        
                            'ruleCount': 5,                        
                            'itemsName': "filter"
                        },
                        'search': true,
                        indirectSelection: {headerSelector:true, width:"30px", styles:"text-align: center;"} 
                    }
                });
                vmGrid.placeAt("additionalLBVMList");
                vmGrid.startup();
                setTimeout(function() { 
                    dijit.byId('LBAdditionalVMListGrid').render(); 
                },300);
                dojo.byId("LBAdditionalVMContainer").style.display="block";
                dojo.byId("addLBAdditionalVMButtonDiv").style.display="block";
                dojo.query("#internalLBExistingDiv").toggleClass("hide_text", true);
                dojo.byId("addInternalLBButton").style.display="none";
                dojo.byId("lbDetailPage").style.display="block";
            }
        });
    },
    'cancelAddAdditionalVM' : function() { 
        dojo.byId("lbDetailPage").style.display="none";
        dojo.byId("addInternalLBButton").style.display="block";
        dojo.query("#internalLBExistingDiv").removeClass("hide_text", true);
        dojo.byId("LBAdditionalVMContainer").style.display="none";
        dojo.byId("addLBAdditionalVMButtonDiv").style.display="none";
    },
    cancelRemoveVMFromLb : function() { 
        dojo.byId("lbRemoveVMListContainer").style.display="none";
        dojo.byId("removeLBAdditionalVMButtonDiv").style.display="none";
        dojo.query("#internalLBExistingDiv").removeClass("hide_text", true);
        dojo.byId("lbDetailPage").style.display="none";
        dojo.byId("addInternalLBButton").style.display="block";
    },
    cancelRemoveVMFromLbDialog : function() { 
        dijit.byId('lbRemoveVMDialog').hide();
    },
    'showRemoveLoadBalancingVM' : function(loadBalancingId) {
        
        dojo.byId("currentLoadBalancingId").value = loadBalancingId;
                
        if (dijit.byId("LBRemoveVMListGrid")) {
            dijit.byId("LBRemoveVMListGrid").destroyRecursive();
        }
        
        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var instanceList = new ItemFileWriteStore({data: instanceOptions});
        
        var vmDatalayout = [
            [   
                {'field': 'id', 'hidden': 'true'},             
                {'name': translator.common.name, 'field': 'name', 'width': '30%', datatype: "string"},
                {'name': translator.common.zone, 'field': 'zone', 'width': '30%', datatype: "string"},
                {'name': translator.common.state, 'field': 'state', 'width': '30%', datatype: "string", "formatter" : function (data) {
                        var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' class='vm_stat_image' alt='"+translator.common.loader.loading+"'/>" });
                        var status;                         
                        if((data != "in progress") && (data != "starting")) {
                            if(data == "Running") {
                                status = "<div class='stat_running overflowLabel'>"+translator.common.instance.status.running+"</div>";
                            } else if(data == "Stopped") {
                                status = "<div class='stat_stopped overflowLabel'>"+translator.common.instance.status.stopped+"</div>";
                            } else if(data == "Destroyed") {
                                status = "<div class='stat_destroyed overflowLabel'>"+translator.common.instance.status.destroyed+"</div>";
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
                        
                }
                
            },
            {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {

                        var html = "<a onclick='TierInternalLoadBalancerInfo.deleteVmFromLoadBalancerRuleShow(\"" + data + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                        return html;
                }, 'width': '20%', datatype: "string", autoComplete: true}
            ]
        ];
        
        var networkEgressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/getVM"
        });

        networkEgressRestStore.query({loadBalancingId: loadBalancingId}).then(function(data) {
            if (!data || data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
               
                registry.byId("userToaster").setContent(translator.common.message.noVMAddedToLB, "error");
                registry.byId("userToaster").show();
                
                
            } else {
                if (dijit.byId("LBRemoveVMListGrid")) {
                    dijit.byId("LBRemoveVMListGrid").destroyRecursive();
                }
                dojo.forEach(data, function(resultData) {
                    instanceList.newItem({id: resultData.referenceId, name: resultData.name, zone: resultData.zone, state: resultData.state, action:resultData.referenceId});
                });
                dojo.byId("removeLBVMList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: "LBRemoveVMListGrid",
                    store: instanceList,
                    structure: vmDatalayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins              
                });
                dataGrid.placeAt("removeLBVMList");
                dataGrid.startup();
                dataGrid.update();
                
                setTimeout(function() { 
                    dijit.byId('LBRemoveVMListGrid').render(); 
                    var selectedLbGrid = dijit.byId("tierInternalLBGridWidget");

                    var  currentDataItem = selectedLbGrid.selection.getSelected();
                    dojo.forEach(currentDataItem, function(el) {
                        
                        dojo.byId("internalLoadBalancingName").innerHTML = el.lbName;
                        dojo.byId("internalLoadBalancingDesc").innerHTML = el.lbDescription;
                        dojo.byId("internalLoadBalancingSourcePort").innerHTML = el.lbSourcePort;
                        dojo.byId("internalLoadBalancingInstancePort").innerHTML = el.lbInstancePort;
                        dojo.byId("internalLoadBalancingAlgorithm").innerHTML = el.lbAlgorithm;
                    });
                },300);
                dojo.byId("lbRemoveVMListContainer").style.display="block";
                dojo.byId("removeLBAdditionalVMButtonDiv").style.display="block";
                dojo.query("#internalLBExistingDiv").toggleClass("hide_text", true);
                dojo.byId("addInternalLBButton").style.display="none";
                dojo.byId("lbDetailPage").style.display="block";
            }
        });
        
    },
    'showAddInternalLB': function () {
        dojo.query("#vpcInfoInternalLBCloudStackException").toggleClass("hide_text", true);
        dojo.byId("vpcInfoInternalLBCloudstackExceptionMsg").innerHTML = "";
        dijit.byId("addInternalLbDialog").show();
        dijit.byId("addInternalLBPageForm").reset();
    },
    'closeAddInternalLB': function () {
         dijit.byId("addInternalLbDialog").hide();
    },
    'addLoadBalancing': function() {                        
        var algorithm = dijit.byId("internalLBAlgorithm");
        var name = dijit.byId("internalLBName");
        var description = dijit.byId("internalLBDescription");
        var sourcePort = dijit.byId("internalLBSourcePort");
        var instancePort = dijit.byId("internalLBInstancePort");
        var networkid = dojo.byId("currentTierReferenceId");
      
         var pageNode = dojo.byId("addInternalLBPageDiv");
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
            dijit.byId("tierLoader").show();
            var networkLoadBalancerRestStore = new JsonRest({
               target: core.getContextPath() + "/api/network/internal/loadBalancing/add"
            });
            networkLoadBalancerRestStore.add({networkId: networkid.value, description: description.value, algorithm: algorithm.value, name: name.value, sourcePort: sourcePort.value, instancePort: instancePort.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var addLoadBalancingRuleJobStatus = setInterval(function() {
                            TierInternalLoadBalancerInfo.addLoadBalancingRuleJob(resultData.jobId, addLoadBalancingRuleJobStatus);
                        }, 3000);
                        dojo.query("#vpcInfoInternalLBCloudStackException").toggleClass("hide_text", true);
                        dojo.byId("vpcInfoInternalLBCloudstackExceptionMsg").innerHTML = "";
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("tierLoader").hide();
                        dojo.query("#vpcInfoInternalLBCloudStackException").removeClass("hide_text", true);
                        dojo.byId("vpcInfoInternalLBCloudstackExceptionMsg").innerHTML = resultData.message;
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('tierLoader').hide();     
                        dojo.query("#vpcInfoInternalLBCloudStackException").removeClass("hide_text", true);
                        dojo.byId("vpcInfoInternalLBCloudstackExceptionMsg").innerHTML = resultData.message;
                    }
                });
            });
        }
    },
    'addLoadBalancingRuleJob': function(jobId, addLoadBalancingRuleJobStatus) {
        var loadBalancerJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/internal/loadBalancing/job"
        });

        loadBalancerJobRestStoreStore.add({jobId:jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(addLoadBalancingRuleJobStatus);                    
                    registry.byId("userToaster").setContent(translator.common.message.addLoadBalancerRuleSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('tierLoader').hide();   
                    dijit.byId("addInternalLbDialog").hide();
                    TierInternalLoadBalancerInfo.populateValues();
                    dojo.query("#vpcInfoInternalLBCloudStackException").toggleClass("hide_text", true);
                    dojo.byId("vpcInfoInternalLBCloudstackExceptionMsg").innerHTML = "";
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(addLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('tierLoader').hide();
                    dojo.query("#vpcInfoInternalLBCloudStackException").removeClass("hide_text", true);
                    dojo.byId("vpcInfoInternalLBCloudstackExceptionMsg").innerHTML = resultData.message;
                }
            });
        });
    },
    'deleteLoadBalancerRuleShow': function(id) {
        dojo.byId("currentLBId").value = id;
        dijit.byId('internalLBDeleteLDialog').show();
    },
    'deleteLoadBalancerRule': function() {

        var networkRuleRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/internal/loadBalancing/remove"
        });

        dijit.byId('tierLoader').show();
        dijit.byId('internalLBDeleteLDialog').hide();

        networkRuleRestStore.add({lbId: dojo.byId("currentLBId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var deleteLoadBalancingRuleJobStatus = setInterval(function() {
                        TierInternalLoadBalancerInfo.deleteLoadBalancingRuleJob(resultData.jobId, dojo.byId("currentLBId").value,deleteLoadBalancingRuleJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("tierLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('tierLoader').hide();
                }
            });
        });
    },
    'deleteLoadBalancingRuleJob': function(jobId, lbId,deleteLoadBalancingRuleJobStatus) {        
        var loadBalancerJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/internal/loadBalancing/delete/job"
        });

        loadBalancerJobRestStoreStore.add({jobId:jobId, lbId:lbId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deleteLoadBalancingRuleJobStatus);
                    TierInternalLoadBalancerInfo.populateValues();
                    registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('tierLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('tierLoader').hide();
                }
            });
        });
    },
    'deleteLoadBalancerRuleClose': function() {
        dijit.byId('internalLBDeleteLDialog').hide();
    },
};
var TierPublicLoadBalancerInfo = {
   populateValues : function () {
       if(dijit.byId("tierLBGrid")) {                                    
            dijit.byId("tierLBGrid").destroyRecursive();                    
        }
        dojo.byId("tierPublicLBGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var LBData = {
            items: []
        }; 
        var lbList = new ItemFileWriteStore({data: LBData}); 
        var lbLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '300px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        if (data.isSourceNat === true || data.isSourceNat === "true") {
                            return "<a href='#/user/vpc/viewLB/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if(data.isStaticNat === true || data.isStaticNat === "true") {
                            return "<a href='#/user/vpc/viewLB/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else {
                            return "<a href='#/user/vpc/viewLB/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        }   
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '400px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                
            ]
         ];
         var lbRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/ip/list/"
         });
         lbRestStore.get(dojo.byId("currentTierId").value).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noPublicLBMsgBox").style.display = "block";
                 dojo.byId("tierPublicLBGrid").innerHTML = "";
             } else {
                 dojo.byId("noPublicLBMsgBox").style.display = "none";                 
                 dojo.forEach(data, function(currentIp) {
                     if(currentIp.isVPCLBAdded === true) {
                         lbList.newItem({
                             id:currentIp.id,                             
                             ip: {ip: currentIp.ip, id: currentIp.id, isSourceNat: currentIp.isSourceNat, isStaticNat: currentIp.isStaticNat},
                             vmName:currentIp.vmDisplayName,
                             zone: currentIp.zone, 
                             state: currentIp.state                             
                         });
                     }
                 });                  
                 dojo.byId("tierPublicLBGrid").innerHTML = "";
                 var pfGrid = new EnhancedGrid({
                    id: 'tierLBGrid',
                    "class" : "span12",
                    store: lbList,
                    structure: lbLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                pfGrid.placeAt("tierPublicLBGrid");
                pfGrid.startup();
              }                           
         });          
     } 
}

var TierStatiscNatInfo = {
    
    acquireIp : function () {
        core.router.go("#/user/vpc/ipList/"+ dojo.byId("currentVpcId").value);
    },    
    populateValues : function () {
        if(dijit.byId("tierStaicNatGrid")) {                                    
            dijit.byId("tierStaicNatGrid").destroyRecursive();                    
        }
        dojo.byId("tierStaticNatGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vmData = {
            items: []
        }; 
        var vmDataList = new ItemFileWriteStore({data: vmData}); 
        var vmLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ipAddress', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {                        
                        return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ipAdd + "</a>";                        
                    }
                },
                {'name': translator.common.zone, 'field': 'zone', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'datatype':'string',  'autoComplete': 'true', 'width': '300px'},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                
            ]
         ];
         var staticnatRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/ip/list/"
         });
         staticnatRestStore.get(dojo.byId("currentTierId").value).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noStaticNatMsgBox").style.display = "block";
                 dojo.byId("tierStaticNatGrid").innerHTML = "";
             } else {
                 dojo.byId("noStaticNatMsgBox").style.display = "none";                 
                 dojo.forEach(data, function(currentIp) {
                     if(currentIp.isStaticNat === "true") {
                         vmDataList.newItem({
                             id:currentIp.id,
                             ipAddress: {ipAdd: currentIp.ip, id: currentIp.id},
                             vmName:currentIp.vmDisplayName?currentIp.vmDisplayName: "-",
                             zone: currentIp.zone, 
                             state: currentIp.state                             
                         });
                     }
                 });                  
                 dojo.byId("tierStaticNatGrid").innerHTML = "";
                 var staticnatGrid = new EnhancedGrid({
                     id: 'tierStaicNatGrid',
                     "class" : "span12",
                     store: vmDataList,
                     structure: vmLayout,
                     autoHeight: true,
                     plugins: core.getGridConfig().plugins,
                     noDataMessage: translator.common.noStaticMsg
                 });
                 staticnatGrid.placeAt("tierStaticNatGrid");
                 staticnatGrid.startup(); 
             }               
         });          
     }    
}

var TierPortforwardingInfo = {
    populateValues : function () {
        if(dijit.byId("tierPfGrid")) {                                    
            dijit.byId("tierPfGrid").destroyRecursive();                    
        }
        dojo.byId("tierPortforwdrGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vmData = {
            items: []
        }; 
        var vmDataList = new ItemFileWriteStore({data: vmData}); 
        var vmLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '400px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {                        
                        if (data.isSourceNat === true || data.isSourceNat === "true") {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if(data.isStaticNat === true || data.isStaticNat === "true") {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else {
                            return "<a href='#/user/vpc/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        }              
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '400px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                
            ]
         ];
         var portforwardingRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/ip/list/"
         });
         portforwardingRestStore.get(dojo.byId("currentTierId").value).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noTierPFMsgBox").style.display = "block";
                 dojo.byId("tierPortforwdrGrid").innerHTML = "";
             } else {
                 dojo.byId("noTierPFMsgBox").style.display = "none";                 
                 dojo.forEach(data, function(currentIp) {
                     if(currentIp.isVPCPFAdded === true) {
                         vmDataList.newItem({
                             id:currentIp.id,
                             ip: {ip: currentIp.ip, id: currentIp.id, isSourceNat: currentIp.isSourceNat, isStaticNat: currentIp.isStaticNat},
                             vmName:currentIp.vmDisplayName,
                             zone: currentIp.zone, 
                             state: currentIp.state                             
                         });
                     }
                 });                  
                 dojo.byId("tierPortforwdrGrid").innerHTML = "";
                 var pfGrid = new EnhancedGrid({
                     id: 'tierPfGrid',
                     "class" : "span12",
                     store: vmDataList,
                     structure: vmLayout,
                     autoHeight: true,
                     plugins: core.getGridConfig().plugins,
                     noDataMessage: translator.common.nopfMessage
                 });
                 pfGrid.placeAt("tierPortforwdrGrid");
                 pfGrid.startup(); 
             }               
         });          
     }
}

var VMTierInfo = {
    populateValues : function () {
        if(dijit.byId("tierVMGrid")) {                                    
            dijit.byId("tierVMGrid").destroyRecursive();                    
        }        
        dojo.byId("tierInstanceGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vmData = {
            items: []
        }; 
        var vmDataList = new ItemFileWriteStore({data: vmData}); 
        var vmLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'width': '300px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function(data) {                                             
                        var widget = "";
                        var imageUrl= "";
                        var templateImage = "";    
                        if (data.osCategory === "CentOS") {
                            imageUrl = "images/os/os_centos_logo.png" ;
                            templateImage = "<img src='images/os/os_centos_logo.png' alt='"+translator.common.template.centOS+"' height='25' width='25'/>" + data.vmName;
                        } else if(data.osCategory === "Debian") {
                            imageUrl = "images/os/os_debian_logo.png";
                            templateImage = "<img src='images/os/os_debian_logo.png' alt='"+translator.common.template.debian+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Oracle") {
                             imageUrl = "images/os/os_oracle_logo.png";
                            templateImage = "<img src='images/os/os_oracle_logo.png' alt='"+translator.common.template.oracle+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "RedHat") {
                             imageUrl = "images/os/os_redhat_logo.png";
                            templateImage = "<img src='images/os/os_redhat_logo.png' alt='"+translator.common.template.redHat+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "SUSE") {
                             imageUrl = "images/os/os_suse_logo.png";
                            templateImage = "<img src='images/os/os_suse_logo.png' alt='"+translator.common.template.SUSE+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Windows") {
                             imageUrl = "images/os/os_win_logo.png";
                            templateImage = "<img src='images/os/os_win_logo.png' alt='"+translator.common.template.windows+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Novel") {
                             imageUrl = "images/os/os_novell_logo.png";
                            templateImage = "<img src='images/os/os_novell_logo.png' alt='"+translator.common.template.novel+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Unix") {
                             imageUrl = "images/os/os_unix_logo.png";
                            templateImage = "<img src='images/os/os_unix_logo.png' alt='"+translator.common.template.unix+"' height='25' width='25'/>" + data.vmName
                        } else if(data.osCategory === "Ubuntu") {
                             imageUrl = "images/os/os_ubuntu_logo.png"; 
                            templateImage = "<img src='images/os/os_ubuntu_logo.png' alt='"+translator.common.template.ubuntu+"' height='25' width='25'/>" + data.vmName
                        } else {
                            imageUrl = ""; 
                            templateImage = "" + data.vmName;
                        }                                                
                        if(data.state === "Running" || data.state === "Stopped") {                            
                            var collections = data.id + "," + "tier";                       
                            var availableWidget = new FogPanel.StorageAction({
                                storageTagPath : "#/user/cloud/vmDetail/" + collections,
                                nameTagClick : function() {                                                                                         
                                }                            
                            });
                            availableWidget.setTagName(data.vmName);                                    
                            availableWidget.setOsImage(imageUrl);
                            availableWidget.disableAll();
                            availableWidget.disableDelete();
                            availableWidget.enableTageName();
                           widget =  availableWidget;
                       } else {                                    
                           widget = templateImage;
                       }                                            
                      return widget;
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'status', 'width': '100px', 'datatype':'string',  'autoComplete': 'true','formatter' : function (data) {
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
                }},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {                        
                        var instanceStatus =  new FogPanel.InstanceStatus({
                            onStartInstance : function() {
                                VMTierInfo.getStartConformation();
                            },
                            onStopInstance : function() {
                                VMTierInfo.getStopConformation();
                            },
                            onRebootInstance : function() {
                                VMTierInfo.getRebootConformation();
                            },
                            onRestoreInstance : function() {
                                dojo.byId("currentTierVmID").value = data.id;
                                 dijit.byId("vmTierRestoreDialog").show();
                            },
                             onDeleteInstance : function() {
                                 VMTierInfo.getDeleteConformation();
                            }
          
                        }); 
                        instanceStatus.disableMigrateHost();
                        if(data.stat === "Running") {
                            instanceStatus.enableRunningState();
                        } else if(data.stat === "Stopped") {
                             instanceStatus.enableStopState();
                        } else if(data.stat === "Destroyed") {
                            instanceStatus.enableRestoreState();
                        } else {
                            instanceStatus.disableAll();
                        }
                        return instanceStatus;                       
                       
                },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
             ]
         ];
         var instanceRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/vm/list"
         });
         instanceRestStore.query({networkId: dojo.byId("currentTierId").value}).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("tierInstanceGrid").innerHTML = "";
                 dojo.byId("noTierVmMessageBox").style.display = "block";                 
             } else {
                 dojo.byId("noTierVmMessageBox").style.display = "none";
                 dojo.forEach(data, function(vm) {
                     vmDataList.newItem({
                         id:vm.referenceId,
                         vmName:{vmName: vm.name, osCategory: vm.osCategory, id: vm.vmId, state: vm.state, referenceId: vm.referenceId},
                         zone: vm.zone, 
                         status: vm.state,
                         action : {id: vm.id, stat: vm.state}
                     });
                 });                  
                 dojo.byId("tierInstanceGrid").innerHTML = "";
                 var tierVMGrid = new EnhancedGrid({
                     id: 'tierVMGrid',
                     "class" : "span12",
                      store: vmDataList,
                      structure: vmLayout,
                      autoHeight: true,
                      plugins: core.getGridConfig().plugins
                  });
                  tierVMGrid.placeAt("tierInstanceGrid");
                  tierVMGrid.startup();  
              }                                
          });         
    },
    getDeleteConformation : function() {
        dijit.byId("deleteTierVMDialog").show();  
    },
    closeRestoreVMDialog : function () {
         dijit.byId("vmTierRestoreDialog").hide();
    },
    closeDeleteDialog : function () {
         dijit.byId("deleteTierVMDialog").hide();
    },
    deleteInstance : function () {
        var currentId = VMTierInfo.getGridData();
            var referenceId;
            var firewalId;
            dojo.forEach(currentId, function(el) {
                referenceId = el;
            });
            var instanceGrid = dijit.byId("tierVMGrid");
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
            
            instanceDeleteRestStore.add({
                referenceId: referenceId,
                forced: "true"
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmStopJobStatus = setInterval(function(){VMTierInfo.vmJob(resultData.jobId, vmStopJobStatus, firewalId);},3000);  
                        var index = dijit.byId("tierVMGrid").selection.selectedIndex;
                        var item = dijit.byId("tierVMGrid").getItem(index);
                        var store = dijit.byId("tierVMGrid").store;                        
                        store.setValue(item, 'status', "in progress");       
                        var data = {id: currentId, stat:"in progress"}; 
                        store.setValue(item, 'action', data);                        
                        dijit.byId("tierVMGrid").update();                          
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.deleteVMError, "error");
                        registry.byId("userToaster").show();
                    }
                });     
            }); 
            dijit.byId("deleteTierVMDialog").hide();  
    },
    getRebootConformation : function () {
        dijit.byId("rebootVMTierDialog").show();  
    },
    getGridData :  function() {
        var instanceGrid = dijit.byId("tierVMGrid");
        var currentId;
        var  dataItem = instanceGrid.selection.getSelected();
        dataItem.disabled = 'true';
        dojo.forEach(dataItem, function(el) {
            currentId = el.id;
        });
        return currentId;
    },
    getStartConformation : function () {
        dijit.byId("startTierVMDialog").show();
    },
    getStopConformation: function() {  
            dijit.byId("stopTierInstanceAgreement").set("checked", false);
            dijit.byId("stopTierDialog").show();
    },
    closeStopDialog : function () {
        dijit.byId("stopTierDialog").hide();
    },
    closeRebootDialog : function () {
        dijit.byId("rebootVMTierDialog").hide();
    },
     restoreInstance : function() {    
           var currentId = VMTierInfo.getGridData();
            var index = dijit.byId("tierVMGrid").selection.selectedIndex;
            var item = dijit.byId("tierVMGrid").getItem(index);
            var store = dijit.byId("tierVMGrid").store;

            var instanceStopRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/restore/"         
            });  
            var referenceId = dojo.byId("currentTierVmID").value;
            dijit.byId("vmTierRestoreDialog").hide();
            instanceStopRestStore.add({
                referenceId: referenceId
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
//                        var vmRestoreJobStatus = setInterval(function(){UserInstances.vmJob(resultData.jobId, vmRestoreJobStatus);},3000);  
                        registry.byId("userToaster").setContent(translator.common.instance.restoreVMSucess, "message");
                        registry.byId("userToaster").show();                                                                                                                      
                         store.setValue(item, 'status', "Stopped");
                         var data = {id: currentId, stat:"Stopped"}; 
                         store.setValue(item, 'action', data);   
                         dijit.byId("tierVMGrid").update();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.restoreVMError, "error");
                        registry.byId("userToaster").show();
                        store.setValue(item, 'status', "Destroyed"); 
                        dijit.byId("tierVMGrid").update();                        
                    }
                });                
            });
        },
    rebootInstance: function() {            
        var currentId = VMTierInfo.getGridData();
        var referenceId;
       dojo.forEach(currentId, function(el) {
           referenceId = el;
       });

        var instanceRebootRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/reboot/"         
        });
        instanceRebootRestStore.add({
            referenceId: referenceId
        }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var vmStartJobStatus = setInterval(function(){VMTierInfo.vmJob(resultData.jobId, vmStartJobStatus);},3000);  
                    var index = dijit.byId("tierVMGrid").selection.selectedIndex;
                    var item = dijit.byId("tierVMGrid").getItem(index);
                    var store = dijit.byId("tierVMGrid").store;
                    var data = {id: currentId, stat:"in progress"};    
                    store.setValue(item, 'status', "in progress");
                    store.setValue(item, 'action', data);                                                                             
                    dijit.byId("tierVMGrid").update();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.rebootVMError, "error");
                    registry.byId("userToaster").show();
                }
            });    
        });
         dijit.byId("rebootVMTierDialog").hide();
    },
    stopInstance: function() {     
        var currentId = VMTierInfo.getGridData();
        var referenceId;
        dojo.forEach(currentId, function(el) {
            referenceId = el;
        });
        var instanceStopRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/stop/"         
        });

        var force = dijit.byId("stopTierInstanceAgreement").checked;
        instanceStopRestStore.add({
            referenceId: referenceId,
            forced: force
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    var vmStartJobStatus = setInterval(function(){VMTierInfo.vmJob(resultData.jobId, vmStartJobStatus);},3000);  
                        var index = dijit.byId("tierVMGrid").selection.selectedIndex;
                        var item = dijit.byId("tierVMGrid").getItem(index);
                        var store = dijit.byId("tierVMGrid").store;
                        var data = {id: currentId, stat:"in progress"};    
                        store.setValue(item, 'status', "in progress");
                        store.setValue(item, 'action', data);                                                                             
                        dijit.byId("tierVMGrid").update();
                } else {
                    registry.byId("userToaster").setContent(translator.common.instance.stopVMError, "error");
                    registry.byId("userToaster").show();
                }
            });                
        });
        dijit.byId("stopTierDialog").hide();
        },
    startInstance : function () {
        var currentId = VMTierInfo.getGridData();
          var referenceId;
           dojo.forEach(currentId, function(el) {
               referenceId = el;
           });
            var instanceStartRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/start/"         
            });
            instanceStartRestStore.add({
                referenceId:  referenceId
            }).then(function(data) {
                 dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        var vmStartJobStatus = setInterval(function(){VMTierInfo.vmJob(resultData.jobId, vmStartJobStatus);},3000);  
                        var index = dijit.byId("tierVMGrid").selection.selectedIndex;
                        var item = dijit.byId("tierVMGrid").getItem(index);
                        var store = dijit.byId("tierVMGrid").store;
                        var data = {id: currentId, stat:"in progress"};    
                        store.setValue(item, 'status', "in progress");
                        store.setValue(item, 'action', data);                                                                             
                        dijit.byId("tierVMGrid").update();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.instance.stopVMError, "error");
                        registry.byId("userToaster").show();
                    }
                });    
            });
            dijit.byId("startTierVMDialog").hide();  
    },
    vmJob: function(jobId, vmJobStatus, firewalId) {
            var jobStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/job/"
            });             
            
            var index = dijit.byId("tierVMGrid").selection.selectedIndex;
            var item = dijit.byId("tierVMGrid").getItem(index);
            var store = dijit.byId("tierVMGrid").store;
            jobStore.add({
                jobId : jobId            
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.jobResult === "OK") {                        
                        clearInterval(vmJobStatus);                               
                         store.setValue(item, 'id',resultData.referenceId);
                         store.setValue(item, 'vmName',{vmName: resultData.name, osCategory: resultData.osCategory, id: resultData.vmId, state: resultData.state, referenceId: resultData.referenceId});                         
                         store.setValue(item, 'status',resultData.state);
                         store.setValue(item, 'action',{id: resultData.referenceId, stat: resultData.state});
                         dijit.byId("tierVMGrid").update();                   
                         registry.byId("userToaster").setContent(translator.common.message.success, "message");
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
    closeStartDialog : function () {
         dijit.byId("startTierVMDialog").hide();
    },
    launchTierVM : function () {
        var tierId = dojo.byId("currentTierId").value;
        var zoneId = dojo.byId("currentTierZoneId").value;
        var collections = tierId + "," + zoneId + "," + "tierOption";
        core.router.go("#/user/cloud/createVm/" + collections);
    }
}

var TierInfo = {       
    'showEditTierInfo' : function() {       

       var networkOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer/forVpc/"
        });
        var networkOfferOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var networkFileStoreList = new ItemFileWriteStore({data: networkOfferOptions});       
       networkOfferRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function(data) {
            if (!data || data.length === 0) {
                networkFileStoreList.newItem({id: "", name: translator.common.noNetworkOffer, tierType: ""});
            } else {
                dojo.forEach(data, function(resultData) {
                    networkFileStoreList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});                   
                });                
            }                     
            dijit.byId("tierInfoNetworkOfferWidget").setStore(networkFileStoreList);
        });   
        setTimeout(function () {dijit.byId("tierInfoNetworkOfferWidget").set("value", dojo.byId("currentTierNwOfferID").value);},300);                 
        
        dijit.byId("tierInfoEditDialog").show();
    },     
    'cancelEditTierInfo' : function() {
        dijit.byId("tierInfoEditDialog").hide();
        dijit.byId("tierInfoEditConformDialog").hide();
    },  
    showTierInfoEditConfirmation : function () {
//        dijit.byId("tierInfoEditDialog").hide();
        dijit.byId("tierInfoEditConformDialog").show();
    },
    'editTierInfo': function() {

        var networkId = dojo.byId("currentTierId");
        var name = dijit.byId("tierInfoEditName").value;
        var desc = dijit.byId("tierInfoEditDesc").value;
        var cidr = dijit.byId("tierInfoCirdchange").checked;
        dijit.byId("tierInfoEditDialog").hide();
        dijit.byId("tierInfoEditConformDialog").hide();
        dijit.byId("tierLoader").show();
        
        var networkOffer = dijit.byId("tierInfoNetworkOfferWidget");
        var NWDomain = dijit.byId("tierInfoEditNWDomain");        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/tier/edit/"
        });      
        networkRestStore.put({id: networkId.value, name: name, desc: desc, networkOffer:networkOffer.value, NWDomain:NWDomain.value, cirdchange: cidr}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcEditJobStat = setInterval(function(){TierInfo.editTierJob(resultData.jobId, vpcEditJobStat, networkId.value);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("tierLoader").hide();
                }
            });
        });

    },
    'editTierJob' : function(jobId, vpcRestartJobStat, networkId) {        
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/tier/restart/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.tierEdidtedSuccess, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("tierLoader").hide();
                    var tierListRestStore = new JsonRest({
                        target: core.getContextPath()+"/api/vpc/list/tier"
                    }); 
                    tierListRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function (){
                        TierInfo.updateTierInfoFromAction(networkId);
                    });
                    
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("tierLoader").hide();
                } else {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("tierLoader").hide(); 
                } 
            });
        });
    },    
    'showTierInfoDelete' : function() {        
        dijit.byId("tierInfoDeleteDialog").show();        
    },    
    'closeTierInfoDelete' : function() {        
        dijit.byId("tierInfoDeleteDialog").hide();        
    },
    'deleteTierInfo': function() {           
         var currentId = dojo.byId("currentTierReferenceId").value;                                       
            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/tier/delete"
            });
            
            dijit.byId("tierLoader").show();
            dijit.byId("tierInfoDeleteDialog").hide();
            
            networkRestStore.add({tierId:currentId}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var vpcDeleteJobStat = setInterval(function(){TierInfo.deleteTierInfoJob(resultData.jobId, resultData.tierId,vpcDeleteJobStat);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("tierLoader").hide();
                    }
                });
            });        
    },
    'deleteTierInfoJob' : function(jobId, tierId ,vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/tier/delete/job/"
        });         
        jobStore.add({jobId: jobId, tierId: tierId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.tierDeleted, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("tierLoader").hide();
                    core.router.go("#/user/tier/list");                    
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("tierLoader").hide();
                } else {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("tierLoader").hide(); 
                } 
            });
        });
    },   
    'replaceACLTierShow'  : function() {              
       var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/"
        });                 
       var firstValue;
       var aclOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var aclFileStoreList = new ItemFileWriteStore({data: aclOptions});
       aclRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function(data) {
            if (!data || data.length == 0) {
                aclFileStoreList.newItem({id: "noAcl", name: translator.common.noAcl});
            } else {
                aclFileStoreList.newItem({id: "select", name: translator.common.select});
                dojo.forEach(data, function(resultData, index) {
                    aclFileStoreList.newItem({id: resultData.referenceId, name: resultData.name});
                    if (index === 0) {
                        firstValue = resultData.referenceId;
                    }
                });
            }
           dijit.byId("tierInfoACLWidget").setStore(aclFileStoreList);
           dijit.byId("tierInfoACLWidget").set("value", firstValue);                                    
       });
       
       dijit.byId("tierInfoReplaceACLDialog").show();
   },
   closeReplaceAcl : function () {
        dijit.byId("tierInfoReplaceACLDialog").hide();
    },
   'replaceAclTier': function() {
        
        var currentId = dojo.byId("currentTierReferenceId").value;
        var tierReplaceAcl = dijit.byId("tierInfoACLWidget");
        var currentTierId = dojo.byId("currentTierId").value;
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkAcl/change/"
        });
        
        dijit.byId("tierLoader").show();
        dijit.byId("tierInfoReplaceACLDialog").hide();
        
         var pageNode = dojo.byId("generalRepalceAClPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
        
            networkRestStore.add({networkId:currentId, aclId:tierReplaceAcl.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var replaceAclTierJobStat = setInterval(function(){TierInfo.replaceAclTierJob(resultData.jobId, replaceAclTierJobStat, currentTierId);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("tierLoader").hide();
                    }
                });
            });
        }
    },
    'replaceAclTierJob' : function(jobId, vpcDeleteJobStat, currentId) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job"
        });         
        jobStore.add({jobId: jobId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.aclChanged, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("tierLoader").hide();
                    var tierListRestStore = new JsonRest({
                        target: core.getContextPath()+"/api/vpc/list/tier"
                    }); 
                    tierListRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function (){
                        TierInfo.updateTierInfoFromAction(currentId);
                    });
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("tierLoader").hide();
                } else {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("tierLoader").hide(); 
                } 
            });
        });
    },
    'showTierInfoRestart' : function() {    
        dijit.byId("tierInfocleanup").set("checked", false);
        dijit.byId("tierInfoRestartDialog").show();        
    },
    'restartTierInfo': function() {                
        var currentId = dojo.byId("currentTierReferenceId").value;
        var cleanup = dijit.byId("tierInfocleanup");
        var currentTierId = dojo.byId("currentTierId").value;
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/tier/restart"
        });
        
        dijit.byId("tierLoader").show();
        dijit.byId("tierInfoRestartDialog").hide();

        networkRestStore.add({tierId:currentId, cleanup:cleanup.checked}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcRestartJobStat = setInterval(function(){TierInfo.restartTierInfoJob(resultData.jobId, vpcRestartJobStat, currentTierId);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("tierLoader").hide();
                }
            });
        });
    },
    'restartTierInfoJob' : function(jobId, vpcRestartJobStat, currentTierId) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/restart/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpcRestartSuccess, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("tierLoader").hide();
                    var tierListRestStore = new JsonRest({
                        target: core.getContextPath()+"/api/vpc/list/tier"
                    }); 
                    tierListRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function (){
                        TierInfo.updateTierInfoFromAction(currentTierId);
                    });
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("tierLoader").hide();
                } else {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("tierLoader").hide(); 
                } 
            });
        });
    },
    'closeTierInfoRestart' : function() {        
        dijit.byId("tierInfoRestartDialog").hide();        
    },
    updateTierInfoFromAction : function (id) {
        dojo.byId("currentTierId").value = id;
        var name = dojo.byId("viewTierName");        
        var desc = dojo.byId("viewTierDesc");
        var zone = dojo.byId("viewTierZoneName");
        var networkOffer = dojo.byId("viewTierOfferName");
        var networkDomain = dojo.byId("viewTierDomain");
        var referenceId = dojo.byId("viewTierReferenceId");
        var netmask = dojo.byId("viewTierNetmask");

        var gateway = dojo.byId("viewTierGateway");
        var cidr = dojo.byId("viewTierCidr");

        var type = dojo.byId("viewTierType");
        var state = dojo.byId("viewTierState");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });
        
        if(dijit.byId("tierInfoACLWidget")) {                                    
            dijit.byId("tierInfoACLWidget").destroyRecursive();                    
        }
        if(dijit.byId("tierInfoNetworkOfferWidget")) {                                    
            dijit.byId("tierInfoNetworkOfferWidget").destroyRecursive();                    
        }
        var listData = {
            items: []
        }; 
        var list = new ItemFileWriteStore({data: listData}); 
        var aclReplaceListWidget = new Select({
            id: "tierInfoACLWidget",        
            sortByLabel: false,
            store: list
        }).placeAt("tierInfoReplaceAclList"); 
        
        var networkOffer = new Select({
            id: "tierInfoNetworkOfferWidget",        
            sortByLabel: false,
            store: list
        }).placeAt("tierInfoNetworkOfferingEditList");
        
        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                
                dijit.byId("tierInfoEditName").setValue(resultData.name);
                dijit.byId("tierInfoEditDesc").setValue(resultData.description);
                dijit.byId("tierInfoEditNWDomain").setValue(resultData.networkDomain);                                
                dojo.byId("currentTierNwOfferID").value = resultData.networkOfferReferenceId;
                
                dojo.byId("currentTierZoneId").value = resultData.zoneId;
                
                networkOffer.innerHTML = resultData.networkOffer;
                name.innerHTML = resultData.name;                
                dojo.byId("viewTierNameTop").innerHTML = resultData.name;
                dojo.byId("viewTierZoneNameTop").innerHTML = resultData.zone;
                
                desc.innerHTML = resultData.description;
                zone.innerHTML = resultData.zone;
                cidr.innerHTML = resultData.cidr;
                gateway.innerHTML = resultData.gateway;
                type.innerHTML = resultData.tierType;
                networkDomain.innerHTML = resultData.networkDomain;
                referenceId.innerHTML = resultData.referenceId;
                dojo.byId("currentTierReferenceId").value = resultData.referenceId;
                dojo.byId("currentVpcId").value = resultData.vpcId;
                netmask.innerHTML = resultData.netmask;
                dojo.byId("currentVpcNameLink").href = "#/user/vpc/view/" + resultData.vpcId;
                dojo.byId("currentTierListLink").href = "#/user/vpc/view/" + resultData.vpcId;
                dojo.byId("currentTierName").innerHTML = resultData.name;
                dojo.byId("currentVpcName").innerHTML = resultData.vpcName;
                state.innerHTML = resultData.state;                                                                
            });
        }); 
    },
    'init': function(id) {        
        dojo.byId("currentTierId").value = id;
        var name = dojo.byId("viewTierName");        
        var desc = dojo.byId("viewTierDesc");
        var zone = dojo.byId("viewTierZoneName");
        var networkOfferTag = dojo.byId("viewTierOfferName");
        var networkDomain = dojo.byId("viewTierDomain");
        var referenceId = dojo.byId("viewTierReferenceId");
        var netmask = dojo.byId("viewTierNetmask");

        var gateway = dojo.byId("viewTierGateway");
        var cidr = dojo.byId("viewTierCidr");

        var type = dojo.byId("viewTierType");
        var state = dojo.byId("viewTierState");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });
        
        if(dijit.byId("tierInfoACLWidget")) {                                    
            dijit.byId("tierInfoACLWidget").destroyRecursive();                    
        }
        if(dijit.byId("tierInfoNetworkOfferWidget")) {                                    
            dijit.byId("tierInfoNetworkOfferWidget").destroyRecursive();                    
        }
        var listData = {
            items: []
        }; 
        var list = new ItemFileWriteStore({data: listData}); 
        var aclReplaceListWidget = new Select({
            id: "tierInfoACLWidget",        
            sortByLabel: false,
            store: list
        }).placeAt("tierInfoReplaceAclList"); 
        
        var networkOffer = new Select({
            id: "tierInfoNetworkOfferWidget",        
            sortByLabel: false,
            store: list
        }).placeAt("tierInfoNetworkOfferingEditList");
        
        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.tierType === "APP") {
                    dojo.byId("changeAclOptionDiv").style.display = "none";
                    dojo.byId("changeAclOptionDiv").className = "";
                    dojo.query("#refreshOptionDiv").toggleClass("offset1", true);
                }
                dijit.byId("tierInfoEditName").setValue(resultData.name);
                dijit.byId("tierInfoEditDesc").setValue(resultData.description);
                dijit.byId("tierInfoEditNWDomain").setValue(resultData.networkDomain);                                
                dojo.byId("currentTierNwOfferID").value = resultData.networkOfferReferenceId;
                
                dojo.byId("currentTierZoneId").value = resultData.zoneId;
                
                networkOfferTag.innerHTML = resultData.networkOffer;
                name.innerHTML = resultData.name;                
                dojo.byId("viewTierNameTop").innerHTML = resultData.name;
                dojo.byId("viewTierZoneNameTop").innerHTML = resultData.zone;
                
                desc.innerHTML = resultData.description;
                zone.innerHTML = resultData.zone;
                cidr.innerHTML = resultData.cidr;
                gateway.innerHTML = resultData.gateway;
                type.innerHTML = resultData.tierType;
                networkDomain.innerHTML = resultData.networkDomain;
                referenceId.innerHTML = resultData.referenceId;
                dojo.byId("currentTierReferenceId").value = resultData.referenceId;
                dojo.byId("currentVpcId").value = resultData.vpcId;
                netmask.innerHTML = resultData.netmask;
                dojo.byId("currentVpcNameLink").href = "#/user/vpc/view/" + resultData.vpcId;
                dojo.byId("currentTierListLink").href = "#/user/vpc/view/" + resultData.vpcId;
                dojo.byId("currentTierName").innerHTML = resultData.name;
                dojo.byId("currentVpcName").innerHTML = resultData.vpcName;
                state.innerHTML = resultData.state;
                
                var mainTab = dijit.byId("tierTabCointainer"); 
                var subPortTab = dijit.byId("tierPortForwarding");
                var subLoadPublicTab = dijit.byId("tierPublicLoadBalancer");
                var subLoadInternalTab = dijit.byId("tierInternalLoadBalancer");
                var tierStaticNat = dijit.byId("tierStaticNat");

                if(resultData.tierType === "WEB") {
                     mainTab.removeChild(subLoadInternalTab); 
                     subLoadInternalTab.destroyRecursive();
                }
                if(resultData.tierType === "DB") {
                     mainTab.removeChild(subLoadInternalTab); 
                     mainTab.removeChild(subLoadPublicTab); 
                     subLoadInternalTab.destroyRecursive();
                     subLoadPublicTab.destroyRecursive();
                }
                if(resultData.tierType === "APP") {
                     mainTab.removeChild(subPortTab); 
                     mainTab.removeChild(subLoadPublicTab); 
                     mainTab.removeChild(tierStaticNat); 
                     subPortTab.destroyRecursive();
                     subLoadPublicTab.destroyRecursive();
                     tierStaticNat.destroyRecursive();
                }
                
            });
        });         
    },
    'populateValues': function(id) {
        
    },
    'showDelete' : function(currentId) {
        dojo.byId("currentTierId").value = currentId;
        dijit.byId("tierDeleteDialog").show();
        
    },
    'closeDelete' : function() {
        
        dijit.byId("tierDeleteDialog").hide();
        
    },
    'showRestart' : function(currentId) {
        dojo.byId("currentTierId").value = currentId;
        dijit.byId("tierRestartDialog").show();
        
    },
    'closeRestart' : function() {
        
        dijit.byId("tierRestartDialog").hide();
        
    },
    'deleteTier': function() {
            var currentId = dojo.byId("currentTierId").value;
            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/tier/delete"
            });
            
            dijit.byId("vpcLoader").show();
            dijit.byId("tierDeleteDialog").hide();
            
            networkRestStore.add({tierId:currentId}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var vpcDeleteJobStat = setInterval(function(){TierInfo.deleteJobTier(resultData.jobId, resultData.tierId,vpcDeleteJobStat);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
                    }
                });
            });
        
    },
    'restartTier': function() {
        
        
        var currentId = dojo.byId("currentVpcId").value;
        var cleanup = dijit.byId("cleanup");
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/tier/restart"
        });
        
        dijit.byId("vpcLoader").show();
        dijit.byId("tierRestartDialog").hide();

        networkRestStore.add({tierId:currentId, cleanup:cleanup.checked}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcRestartJobStat = setInterval(function(){ListVpc.restartJobVpc(resultData.jobId, vpcRestartJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide();
                }
            });
        });
    },
    'deleteJobTier' : function(jobId, tierId ,vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/tier/delete/job/"
        });         
        jobStore.add({jobId: jobId, tierId: tierId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.tierDeleted, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    ViewVpc.populateTierValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("vpcLoader").hide();
                } else {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide(); 
                } 
            });
        });
    },
    'restartJobTier' : function(jobId, vpcRestartJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/tier/restart/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.tierRestartSuccess, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpcLoader").hide();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("vpcLoader").hide();
                } else {
                    clearInterval(vpcRestartJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide(); 
                } 
            });
        });
    }
};

