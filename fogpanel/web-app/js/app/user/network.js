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
    "dojo/store/Observable",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dojox/grid/enhanced/plugins/IndirectSelection",
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
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
                ItemFileWriteStore, Memory, Observable, DataGrid, EnhancedGrid, IndirectSelection, Calendar, ContentPane, Source, MultiSelect, dom, win) {
            window.query = query;
            window.domClass = domClass;
            window.domConstruct = domConstruct;
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
            window.IndirectSelection = IndirectSelection;
            window.Calendar = Calendar;
            window.Source = Source;
            window.MultiSelect = MultiSelect;
            window.dom = dom;
            window.win = win;
            controller({
                name: "network",
                module: "user",
                filePath: "/js/app/user/network.js",
                layout: {
                    name: "network",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "index": action(function() {
                    if (dijit.byId("networkLoader")) {
                        dijit.byId("networkLoader").destroyRecursive();
                    }
                    if (dijit.byId("networkDeleteDialog")) {
                        dijit.byId("networkDeleteDialog").destroyRecursive();
                    }
                    if(dijit.byId("serviceTooltipDialogue")) {
                        dijit.byId("serviceTooltipDialogue").destroyRecursive();
                    }
                    core.ui.loadTemplate("networkList", core.ui.getContentId());
                    ListNetwork.init();
                    ListNetwork.populateValues();
                }),
                "add": action(function(id) {
                    var userNetworkZoneForm = dijit.byId("userNetworkZoneForm");
                    if (userNetworkZoneForm) {
                        userNetworkZoneForm.destroyRecursive();
                    }
                    if (dijit.byId("userNetworkContentForm")) {
                        dijit.byId("userNetworkContentForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("createNetwork", core.ui.getContentId());
                    AddNetwork.init(id);
                    AddNetwork.populateValues();
                }),
                "edit": action(function(id) {

                    var userEditNetworkZoneForm = dijit.byId("userEditNetworkZoneForm");
                    if (userEditNetworkZoneForm) {
                        userEditNetworkZoneForm.destroyRecursive();
                    }
                    if (dijit.byId("userEditNetworkContentForm")) {
                        dijit.byId("userEditNetworkContentForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("editNetwork", core.ui.getContentId());
                    EditNetwork.init();
                    EditNetwork.populateValues(id);
                }),
                "view": action(function(id) {

                    if (dijit.byId("userViewNetworkZoneForm")) {
                        dijit.byId("userViewNetworkZoneForm").destroyRecursive();
                    }
                    if (dijit.byId("userNetworkEgressRuleAddForm")) {
                        dijit.byId("userNetworkEgressRuleAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkAcquireIpDialog")) {
                        dijit.byId("networkAcquireIpDialog").destroyRecursive();
                    }
                    if (dijit.byId("neworkReleaseIpDialog")) {
                        dijit.byId("neworkReleaseIpDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpLoader")) {
                        dijit.byId("networkIpLoader").destroyRecursive();
                    }
                    if (dijit.byId("networkIPEnableStaticNatDialog")) {
                        dijit.byId("networkIPEnableStaticNatDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIPDisableStaticNatDialog")) {
                        dijit.byId("networkIPDisableStaticNatDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkLoader")) {
                        dijit.byId("networkLoader").destroyRecursive();
                    }
                    if (dijit.byId("networkDeleteDialog")) {
                        dijit.byId("networkDeleteDialog").destroyRecursive();
                    }
                    
                    if(dijit.byId('staticNatVM')) {
                        dijit.byId('staticNatVM').destroyRecursive();
                    }

                    if(dijit.byId('staticNatVMIp')) {
                        dijit.byId('staticNatVM').destroyRecursive();
                    }
                    if(dijit.byId('networkTab')) {
                        dijit.byId('networkTab').destroyRecursive();
                    }
                    if(dijit.byId('networkIpAddressTab')) {
                        dijit.byId('networkIpAddressTab').destroyRecursive();
                    }
                    if(dijit.byId('networkAquireIPContent')) {
                        dijit.byId('networkAquireIPContent').destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableVPN")) {
                        dijit.byId("networkIpEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDisableVPN")) {
                        dijit.byId("networkIpDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpListEnableVPN")) {
                        dijit.byId("networkIpListEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpListDisableVPN")) {
                        dijit.byId("networkIpListDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableWarningMsgDialog")) {
                        dijit.byId("networkIpEnableWarningMsgDialog").destroyRecursive();
                    }
                    if (dijit.byId("vpnUserForm")) {
                        dijit.byId("vpnUserForm").destroyRecursive();
                    } 
                    if (dijit.byId("networkIpDeleteVPNUserDialogue")) {
                        dijit.byId("networkIpDeleteVPNUserDialogue").destroyRecursive();
                    } 
                    if (dijit.byId("networkIPVPNUserDialogLoader")) {
                        dijit.byId("networkIPVPNUserDialogLoader").destroyRecursive();
                    } 
                    if (dijit.byId("lbRemoveVMDialog")) {
                        dijit.byId("lbRemoveVMDialog").destroyRecursive();
                    }                                         
                    core.ui.loadTemplate("viewNetwork", core.ui.getContentId());
                    ViewNetwork.init();
                    ViewNetwork.populateValues(id);
                }),
                viewIp: action(function(id) {

                    if (dijit.byId("userViewIPForm")) {
                        dijit.byId("userViewIPForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPFirewallAddForm")) {
                        dijit.byId("userNetworkIPFirewallAddForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPPortForwardingAddForm")) {
                        dijit.byId("userNetworkIPPortForwardingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkPortForwardingLoader")) {
                        dijit.byId("networkPortForwardingLoader").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPLoadBalancingAddForm")) {
                        dijit.byId("userNetworkIPLoadBalancingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkLoadBalancerLoader")) {
                        dijit.byId("networkLoadBalancerLoader").destroyRecursive();
                    }
                    if (dijit.byId("networkDeleteLBDialog")) {
                        dijit.byId("networkDeleteLBDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkEditLBDialog")) {
                        dijit.byId("networkEditLBDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("portForwardingVMIp")) {
                        dijit.byId("portForwardingVMIp").destroyRecursive();
                    }
                    
                    if (dijit.byId("ipPortForwardingVM")) {
                        dijit.byId("ipPortForwardingVM").destroyRecursive();
                    }

                    if (dijit.byId("networkIPDisableStaticNatDialog")) {
                        dijit.byId("networkIPDisableStaticNatDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("neworkReleaseIpDialog")) {
                        dijit.byId("neworkReleaseIpDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("networkIPEnableStaticNatDialog")) {
                        dijit.byId("networkIPEnableStaticNatDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpLoader")) {
                        dijit.byId("networkIpLoader").destroyRecursive();
                    }
                                    
                    if (dijit.byId("ipDetailsTabCointainer")) {
                        dijit.byId("ipDetailsTabCointainer").destroyRecursive();
                    }
                    if (dijit.byId("ipPortForwarding")) {
                        dijit.byId("ipPortForwarding").destroyRecursive();
                    }
                    if (dijit.byId("ipLoadBalancing")) {
                        dijit.byId("ipLoadBalancing").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableVPN")) {
                        dijit.byId("networkIpEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDisableVPN")) {
                        dijit.byId("networkIpDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableWarningMsgDialog")) {
                        dijit.byId("networkIpEnableWarningMsgDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpListEnableVPN")) {
                        dijit.byId("networkIpListEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpListDisableVPN")) {
                        dijit.byId("networkIpListDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("vpnUserForm")) {
                        dijit.byId("vpnUserForm").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDeleteVPNUserDialogue")) {
                        dijit.byId("networkIpDeleteVPNUserDialogue").destroyRecursive();
                    }
                    if (dijit.byId("networkIPVPNUserDialogLoader")) {
                        dijit.byId("networkIPVPNUserDialogLoader").destroyRecursive();
                    } 
                    if (dijit.byId("lbRemoveVMDialog")) {
                        dijit.byId("lbRemoveVMDialog").destroyRecursive();
                    } 
                    core.ui.loadTemplate("viewNetworkIPAddress", core.ui.getContentId());
                    ViewNetworkIPDetails.populateValues(id);
                }),
                firewall: action(function(id) {

                    if (dijit.byId("userViewIPForm")) {
                        dijit.byId("userViewIPForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPFirewallAddForm")) {
                        dijit.byId("userNetworkIPFirewallAddForm").destroyRecursive();
                   }
                   
                    if (dijit.byId("userNetworkIPPortForwardingAddForm")) {
                        dijit.byId("userNetworkIPPortForwardingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkPortForwardingLoader")) {
                        dijit.byId("networkPortForwardingLoader").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPLoadBalancingAddForm")) {
                        dijit.byId("userNetworkIPLoadBalancingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkLoadBalancerLoader")) {
                        dijit.byId("networkLoadBalancerLoader").destroyRecursive();
                    }
                    if (dijit.byId("networkDeleteLBDialog")) {
                        dijit.byId("networkDeleteLBDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkEditLBDialog")) {
                        dijit.byId("networkEditLBDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("portForwardingVMIp")) {
                        dijit.byId("portForwardingVMIp").destroyRecursive();
                    }
                    
                    if (dijit.byId("ipPortForwardingVM")) {
                        dijit.byId("ipPortForwardingVM").destroyRecursive();
                    }

                    if (dijit.byId("networkIPDisableStaticNatDialog")) {
                        dijit.byId("networkIPDisableStaticNatDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("neworkReleaseIpDialog")) {
                        dijit.byId("neworkReleaseIpDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("networkIPEnableStaticNatDialog")) {
                        dijit.byId("networkIPEnableStaticNatDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpLoader")) {
                        dijit.byId("networkIpLoader").destroyRecursive();
                    }
                                    
                    if (dijit.byId("ipDetailsTabCointainer")) {
                        dijit.byId("ipDetailsTabCointainer").destroyRecursive();
                    }
                    if (dijit.byId("ipPortForwarding")) {
                        dijit.byId("ipPortForwarding").destroyRecursive();
                    }
                    if (dijit.byId("ipLoadBalancing")) {
                        dijit.byId("ipLoadBalancing").destroyRecursive();
                    }
                    if (dijit.byId("ipFireWallTab")) {
                        dijit.byId("ipFireWallTab").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableVPN")) {
                        dijit.byId("networkIpEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDisableVPN")) {
                        dijit.byId("networkIpDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableWarningMsgDialog")) {
                        dijit.byId("networkIpEnableWarningMsgDialog").destroyRecursive();
                    }
                    if (dijit.byId("vpnUserForm")) {
                        dijit.byId("vpnUserForm").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDeleteVPNUserDialogue")) {
                        dijit.byId("networkIpDeleteVPNUserDialogue").destroyRecursive();
                    }
                    if (dijit.byId("networkIPVPNUserDialogLoader")) {
                        dijit.byId("networkIPVPNUserDialogLoader").destroyRecursive();
                    } 
                    if (dijit.byId("networkIpListEnableVPN")) {
                        dijit.byId("networkIpListEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpListDisableVPN")) {
                        dijit.byId("networkIpListDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("lbRemoveVMDialog")) {
                        dijit.byId("lbRemoveVMDialog").destroyRecursive();
                    } 
                    core.ui.loadTemplate("viewNetworkIPAddress", core.ui.getContentId());
                    ViewNetworkIPDetails.populateValues(id);
                    
                    setTimeout(function () {
                        var mainTab = dijit.byId("ipDetailsTabCointainer"); //Tr
                        var subIpTab = dijit.byId("ipFireWallTab"); //tab Id which you want to show
                        mainTab.selectChild(subIpTab);   
                    },500);
                    
                }),
                portForwarding: action(function(id) {

                    if (dijit.byId("userViewIPForm")) {
                        dijit.byId("userViewIPForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPFirewallAddForm")) {
                        dijit.byId("userNetworkIPFirewallAddForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPPortForwardingAddForm")) {
                        dijit.byId("userNetworkIPPortForwardingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkPortForwardingLoader")) {
                        dijit.byId("networkPortForwardingLoader").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPLoadBalancingAddForm")) {
                        dijit.byId("userNetworkIPLoadBalancingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkLoadBalancerLoader")) {
                        dijit.byId("networkLoadBalancerLoader").destroyRecursive();
                    }
                    if (dijit.byId("networkDeleteLBDialog")) {
                        dijit.byId("networkDeleteLBDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkEditLBDialog")) {
                        dijit.byId("networkEditLBDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("portForwardingVMIp")) {
                        dijit.byId("portForwardingVMIp").destroyRecursive();
                    }
                    
                    if (dijit.byId("ipPortForwardingVM")) {
                        dijit.byId("ipPortForwardingVM").destroyRecursive();
                    }

                    if (dijit.byId("networkIPDisableStaticNatDialog")) {
                        dijit.byId("networkIPDisableStaticNatDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("neworkReleaseIpDialog")) {
                        dijit.byId("neworkReleaseIpDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("networkIPEnableStaticNatDialog")) {
                        dijit.byId("networkIPEnableStaticNatDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpLoader")) {
                        dijit.byId("networkIpLoader").destroyRecursive();
                    }
                                    
                    if (dijit.byId("ipDetailsTabCointainer")) {
                        dijit.byId("ipDetailsTabCointainer").destroyRecursive();
                    }
                    if (dijit.byId("ipPortForwarding")) {
                        dijit.byId("ipPortForwarding").destroyRecursive();
                    }
                    if (dijit.byId("ipLoadBalancing")) {
                        dijit.byId("ipLoadBalancing").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableVPN")) {
                        dijit.byId("networkIpEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDisableVPN")) {
                        dijit.byId("networkIpDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableWarningMsgDialog")) {
                        dijit.byId("networkIpEnableWarningMsgDialog").destroyRecursive();
                    }
                    if (dijit.byId("vpnUserForm")) {
                        dijit.byId("vpnUserForm").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDeleteVPNUserDialogue")) {
                        dijit.byId("networkIpDeleteVPNUserDialogue").destroyRecursive();
                    }
                    if (dijit.byId("networkIPVPNUserDialogLoader")) {
                        dijit.byId("networkIPVPNUserDialogLoader").destroyRecursive();
                    } 
                    if (dijit.byId("networkIpListEnableVPN")) {
                        dijit.byId("networkIpListEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpListDisableVPN")) {
                        dijit.byId("networkIpListDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("lbRemoveVMDialog")) {
                        dijit.byId("lbRemoveVMDialog").destroyRecursive();
                    } 
                    core.ui.loadTemplate("viewNetworkIPAddress", core.ui.getContentId());
                    ViewNetworkIPDetails.populateValues(id);                                        
                    setTimeout(function () {
                        var mainTab = dijit.byId("ipDetailsTabCointainer"); //Tr
                        var subIpTab = dijit.byId("ipPortForwarding"); //tab Id which you want to show
                        mainTab.selectChild(subIpTab);   
                    },500);

                }),
                loadBalancing: action(function(id) {

                    if (dijit.byId("userViewIPForm")) {
                        dijit.byId("userViewIPForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPFirewallAddForm")) {
                        dijit.byId("userNetworkIPFirewallAddForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPPortForwardingAddForm")) {
                        dijit.byId("userNetworkIPPortForwardingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkPortForwardingLoader")) {
                        dijit.byId("networkPortForwardingLoader").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPLoadBalancingAddForm")) {
                        dijit.byId("userNetworkIPLoadBalancingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkLoadBalancerLoader")) {
                        dijit.byId("networkLoadBalancerLoader").destroyRecursive();
                    }
                    if (dijit.byId("networkDeleteLBDialog")) {
                        dijit.byId("networkDeleteLBDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkEditLBDialog")) {
                        dijit.byId("networkEditLBDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("portForwardingVMIp")) {
                        dijit.byId("portForwardingVMIp").destroyRecursive();
                    }
                    
                    if (dijit.byId("ipPortForwardingVM")) {
                        dijit.byId("ipPortForwardingVM").destroyRecursive();
                    }

                    if (dijit.byId("networkIPDisableStaticNatDialog")) {
                        dijit.byId("networkIPDisableStaticNatDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("neworkReleaseIpDialog")) {
                        dijit.byId("neworkReleaseIpDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("networkIPEnableStaticNatDialog")) {
                        dijit.byId("networkIPEnableStaticNatDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpLoader")) {
                        dijit.byId("networkIpLoader").destroyRecursive();
                    }
                                    
                    if (dijit.byId("ipDetailsTabCointainer")) {
                        dijit.byId("ipDetailsTabCointainer").destroyRecursive();
                    }
                    if (dijit.byId("ipPortForwarding")) {
                        dijit.byId("ipPortForwarding").destroyRecursive();
                    }
                    if (dijit.byId("ipLoadBalancing")) {
                        dijit.byId("ipLoadBalancing").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableVPN")) {
                        dijit.byId("networkIpEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDisableVPN")) {
                        dijit.byId("networkIpDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("vpnUserForm")) {
                        dijit.byId("vpnUserForm").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableWarningMsgDialog")) {
                        dijit.byId("networkIpEnableWarningMsgDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDeleteVPNUserDialogue")) {
                        dijit.byId("networkIpDeleteVPNUserDialogue").destroyRecursive();
                    }
                    if (dijit.byId("networkIPVPNUserDialogLoader")) {
                        dijit.byId("networkIPVPNUserDialogLoader").destroyRecursive();
                    } 
                    if (dijit.byId("networkIpListEnableVPN")) {
                        dijit.byId("networkIpListEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpListDisableVPN")) {
                        dijit.byId("networkIpListDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("lbRemoveVMDialog")) {
                        dijit.byId("lbRemoveVMDialog").destroyRecursive();
                    } 
                    core.ui.loadTemplate("viewNetworkIPAddress", core.ui.getContentId());
                    ViewNetworkIPDetails.populateValues(id);
                    
                     setTimeout(function () {
                        var mainTab = dijit.byId("ipDetailsTabCointainer"); //Tr
                        var subIpTab = dijit.byId("ipLoadBalancing"); //tab Id which you want to show
                        mainTab.selectChild(subIpTab);   
                    },500);

                }),
                vpn: action(function(id) {

                    if (dijit.byId("userViewIPForm")) {
                        dijit.byId("userViewIPForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPFirewallAddForm")) {
                        dijit.byId("userNetworkIPFirewallAddForm").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPPortForwardingAddForm")) {
                        dijit.byId("userNetworkIPPortForwardingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkPortForwardingLoader")) {
                        dijit.byId("networkPortForwardingLoader").destroyRecursive();
                    }
                    
                    if (dijit.byId("userNetworkIPLoadBalancingAddForm")) {
                        dijit.byId("userNetworkIPLoadBalancingAddForm").destroyRecursive();
                    }
                    if (dijit.byId("networkLoadBalancerLoader")) {
                        dijit.byId("networkLoadBalancerLoader").destroyRecursive();
                    }
                    if (dijit.byId("networkDeleteLBDialog")) {
                        dijit.byId("networkDeleteLBDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkEditLBDialog")) {
                        dijit.byId("networkEditLBDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("portForwardingVMIp")) {
                        dijit.byId("portForwardingVMIp").destroyRecursive();
                    }
                    
                    if (dijit.byId("ipPortForwardingVM")) {
                        dijit.byId("ipPortForwardingVM").destroyRecursive();
                    }

                    if (dijit.byId("networkIPDisableStaticNatDialog")) {
                        dijit.byId("networkIPDisableStaticNatDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableWarningMsgDialog")) {
                        dijit.byId("networkIpEnableWarningMsgDialog").destroyRecursive();
                    }
                    if (dijit.byId("neworkReleaseIpDialog")) {
                        dijit.byId("neworkReleaseIpDialog").destroyRecursive();
                    }
                    
                    if (dijit.byId("networkIPEnableStaticNatDialog")) {
                        dijit.byId("networkIPEnableStaticNatDialog").destroyRecursive();
                    }
                    if (dijit.byId("networkIpLoader")) {
                        dijit.byId("networkIpLoader").destroyRecursive();
                    }
                                    
                    if (dijit.byId("ipDetailsTabCointainer")) {
                        dijit.byId("ipDetailsTabCointainer").destroyRecursive();
                    }
                    if (dijit.byId("ipPortForwarding")) {
                        dijit.byId("ipPortForwarding").destroyRecursive();
                    }
                    if (dijit.byId("ipLoadBalancing")) {
                        dijit.byId("ipLoadBalancing").destroyRecursive();
                    }
                    if (dijit.byId("networkIpEnableVPN")) {
                        dijit.byId("networkIpEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDisableVPN")) {
                        dijit.byId("networkIpDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("vpnUserForm")) {
                        dijit.byId("vpnUserForm").destroyRecursive();
                    }
                    if (dijit.byId("networkIpDeleteVPNUserDialogue")) {
                        dijit.byId("networkIpDeleteVPNUserDialogue").destroyRecursive();
                    }
                    if (dijit.byId("networkIPVPNUserDialogLoader")) {
                        dijit.byId("networkIPVPNUserDialogLoader").destroyRecursive();
                    } 
                    if (dijit.byId("networkIpListEnableVPN")) {
                        dijit.byId("networkIpListEnableVPN").destroyRecursive();
                    }
                    if (dijit.byId("networkIpListDisableVPN")) {
                        dijit.byId("networkIpListDisableVPN").destroyRecursive();
                    }
                    if (dijit.byId("lbRemoveVMDialog")) {
                        dijit.byId("lbRemoveVMDialog").destroyRecursive();
                    } 
                    core.ui.loadTemplate("viewNetworkIPAddress", core.ui.getContentId());
                    ViewNetworkIPDetails.populateValues(id);
                    
                     setTimeout(function () {
                        var mainTab = dijit.byId("ipDetailsTabCointainer"); //Tr
                        var subIpTab = dijit.byId("ipVpnUsers"); //tab Id which you want to show
                        mainTab.selectChild(subIpTab);   
                    },500);

                })
            });
        });


var ViewNetworkIPPortForwardingDetails = {
    'init': function() {    
        if (dijit.byId("networkIPVMListGrid")) {
            dijit.byId("networkIPVMListGrid").destroyRecursive();
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
                {'name': translator.common.name, 'field': 'name', 'width': '300px', datatype: "string", formatter: function (data) {
                        var nameNode = "";                        
                        if(data.isEnable == true) {                          
                            var networkVMListRestStore = new JsonRest({
                                target: core.getContextPath() + "/api/network/nic/ip/list/"
                            });       
                            var ipOptions = {
                                identifier: 'id',
                                label: 'name',
                                items: []
                            };
                            var networkId = dojo.byId("currentNetworkId").value;
                            var ipList = new ItemFileWriteStore({data: ipOptions}); 
                            networkVMListRestStore.query({networkId: networkId, vmId: data.referenceId}).then(function(data) {
                                dojo.forEach(data , function(ip, index) {
                                    ipList.newItem({
                                        id: ip.referenceId,                                    
                                        name: ip.ip
                                    });   
                                    if(index == 0) {
                                        dojo.byId("portForwarCurrentIp").value = ip.referenceId
                                    }
                                });
                            });
                                                       
                            var ipWidget = new Select({
                                store: ipList,
                                placeHolder: translator.common.message.selectIp,
                                onChange : function () {                                    
                                    dojo.byId("portForwarCurrentIp").value = this.value;
                                }
                            });
                            ipWidget.startup();                                                        
                            nameNode = ipWidget;
                        } else if(data.isEnable == false) {                   
                            nameNode = "<span>" + data.vmName + "</span>"
                        }
                        return  nameNode;
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '300px', datatype: "string"},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', datatype: "string", "formatter" : function (data) {
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
                    }}                      
            ]
        ];
        
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/vm/list/"
        });
        
        networkVMListRestStore.query({networkId: dojo.byId("currentNetworkId").value}).then(function(data) {                            
            if(data.length == 0 || data == "" || data == "undefined") {
                dojo.byId("portforwardingNoVMList").style.display = "block";
            } else {
                dojo.forEach(data, function(vm) {
                    instanceList.newItem({id: vm.referenceId, name: {referenceId:vm.referenceId , vmName: vm.name, stat: vm.state, isEnable: false}, zone: vm.zone, state: vm.state});
                });
                
                var vmGrid = new dojox.grid.EnhancedGrid({
                    id: "networkIPVMListGrid",
                    "class" : "span12",
                    store: instanceList,
                    structure: vmDatalayout,
                    autoHeight: true,                                    
                    selectionMode: "single",
                    plugins: {
                        pagination: {
                            pageSizes: ["25", "50", "100", translator.common.all],
		            description: true,
		            sizeSwitch: true,
		            pageStepper: true,
		            gotoButton: true,		            
		            maxPageStep: 4,		            
		            position: "bottom"
                        },
                        'filter': {                        
                            'ruleCount': 5,                        
                            'itemsName': "filter"
                        },
                        'search': true,
                        indirectSelection: {headerSelector: false, name: translator.common.select,width:"50px",  styles:"text-align: center;margin-left:15px;"}
                    },
                    onRowDblClick: function(evnt) {
                    },
                    onApplyCellEdit: function(inValue, inRowIndex, inFieldIndex) {      
                        this.store.revert();
                        setTimeout(function() { 
                            grid._refresh();
                        },0);
                    }
                });
            vmGrid.placeAt("userIPPortForwardVMList");
            vmGrid.startup();
            vmGrid.connect(vmGrid.selection, 'onSelected', function(rowIndex) {
                ViewNetworkIPPortForwardingDetails.showVMSIP(rowIndex, "select");
            })
            vmGrid.connect(vmGrid.selection, 'onDeselected', function(rowIndex) {                
                ViewNetworkIPPortForwardingDetails.showVMSIP(rowIndex, "deselect");
            })
        }            
    });            
    },
    deselectVMSIP : function () {              
    },
    showVMSIP : function (rowIndex, status) {           
       dijit.byId("networkIPVMListGrid").store.fetch({onComplete: function(itemList, request) {                                                
               dojo.forEach(itemList,  function(items, idx) {
                   var item = dijit.byId("networkIPVMListGrid").getItem(idx);
                   var store = dijit.byId("networkIPVMListGrid").store;
                   if(idx == rowIndex && status == "select") {                                
                       var nameData = {referenceId: items.name[0].referenceId , vmName: items.name[0].vmName, stat: items.name[0].state, isEnable: true}
                       store.setValue(item, 'name', nameData);
                       dijit.byId("networkIPVMListGrid").update();
                   } else {                                        
                       var disableData = {referenceId: items.name[0].referenceId , vmName: items.name[0].vmName, stat: items.name[0].state, isEnable: false}
                       store.setValue(item, 'name', disableData);
                       dijit.byId("networkIPVMListGrid").update();
                   }
               }) 
           }
       });             
    },
    'list': function() {
        if (dijit.byId("networkIpPortForwardingGrid")) {
            dijit.byId("networkIpPortForwardingGrid").destroyRecursive();
        }
        dojo.byId("userIpPortForwardingRuleList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var loadBalancingData = {
            items: []
        };

        var loadBalancingDataList = new ItemFileWriteStore({data: loadBalancingData});
        var loadBalancingDatalayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.protocol, 'field': 'protocol', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.privatePort, 'field': 'privatePort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.publicPort, 'field': 'publicPort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.vmList, 'field': 'vm', 'width': '200px', datatype: "string", autoComplete: true, "formatter" : function (data) {
                        var divContainer = "<div class='row-fluid'><label>VM:</label><span>"+data.vmName+"</span></div><div class='row-fluid'><label>IP:</label><span>"+data.ip+"</span></div>"
                        return divContainer;
                }},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {
                        var deleteButton = new dijit.form.Button({
                            "class": "delete_icon",
                            onClick: function() {
                                ViewNetworkIPPortForwardingDetails.showDeletePortForwardingDialog(data.id)
                            }
                        });       
                        return deleteButton;
                    }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        var ipId = dojo.byId("currentIPId").value;

        var networkEgressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/portForwarding/list/"
        });                
        networkEgressRestStore.get(ipId).then(function(data) {
            if (!data || data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userIpPortForwardingRuleList").innerHTML = "";
                dojo.byId("noPortforwardingMessageBox").style.display = "block";
            } else {
                dojo.byId("noPortforwardingMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    loadBalancingDataList.newItem({
                        id: resultData.referenceId,
                        vm: {vmName: resultData.instanceName, ip: resultData.ip},
                        protocol: resultData.protocol,
                        publicPort: resultData.publicPort,
                        privatePort: resultData.privatePort,
                        action: {id: resultData.referenceId}
                    });
                });
                dojo.byId("userIpPortForwardingRuleList").innerHTML = "";                                             
                var dataGrid = new EnhancedGrid({
                    id: "networkIpPortForwardingGrid",
                    "class" : "span12",
                    store: loadBalancingDataList,
                    structure: loadBalancingDatalayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins  
                });
            dataGrid.placeAt("userIpPortForwardingRuleList");
            dataGrid.startup();
            dataGrid.update();
        }
    });
    },
     
    'showDeletePortForwardingDialog' : function(id) {
        dojo.byId('currentNetworkPortForwardingId').value = id;
         dijit.byId('networkDeletePortForwardingDialog').show();
    },
    'deletePortForwardingRuleClose' : function(id) {
        dojo.byId('currentNetworkPortForwardingId').value = id;
        dijit.byId('networkDeletePortForwardingDialog').hide();
    },
    deletePortForwardingRule: function() {        
        var id = dojo.byId('currentNetworkPortForwardingId').value;        
        var networkRuleRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/portForwarding/remove/"
        });

        dijit.byId('networkPortForwardingLoader').show();
        dijit.byId('networkDeletePortForwardingDialog').hide();
                
        networkRuleRestStore.add({ruleId: id}).then(function(result) {
        dojo.forEach(result, function(resultData) {
            if (resultData.result == "OK") {
                var deletePortForwardingJobStatus = setInterval(function() {
                    ViewNetworkIPPortForwardingDetails.deletePortForwardingRuleJob(resultData.jobId, id, deletePortForwardingJobStatus);
                }, 3000);

            } else {
                registry.byId("userToaster").setContent(translator.common.firewall.cloudStackError, "error");
                registry.byId("userToaster").show();
                dijit.byId('networkPortForwardingLoader').hide();
            }

        });

    });                
    },
    cancelVMGrid : function () {
        dojo.query("#networkPFCloudstackException").toggleClass("hide_text", true);
        dojo.byId("networkPFCloudstackExceptionMsg").innerHTML = "";
        dojo.byId("addIPButtonCopyDiv").style.display="none";        
        dijit.byId("addVMIPPageButton").set("disabled", false);        
        dojo.byId("vmListContainer").style.display="none";                        
        dojo.query("#userIpExistList").removeClass("hide_text", true); 
        dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
        dojo.query("#VMRequireCopyMsg").toggleClass("hide_text", true);        
        if(dijit.byId('networkIpPortForwardingGrid')) {      
            dijit.byId('networkIpPortForwardingGrid').render();
        } 
        dijit.byId('userNetworkIPPortForwardingAddForm').reset();        
        dojo.byId("addIPButtonDiv").style.display="none";     
    },
    showVMList : function () {               
        var pageNode = dojo.byId("userNetworkIPPortForwardingAddPage");
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
        }
        
         if(wigetState == true) {        
             dijit.byId("addVMIPPageButton").set("disabled", true);
//             dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
             dojo.query("#VMRequireCopyMsg").toggleClass("hide_text", true);       
             dojo.byId("vmListContainer").style.display="block";
//             dojo.byId("addIPButtonDiv").style.display="block";
             dojo.byId("addIPButtonCopyDiv").style.display="block";
             dojo.query("#userIpExistList").toggleClass("hide_text", true);                
             dijit.byId('networkIPVMListGrid').render();             
         } 
    },
    'addPortForwarding': function() {
        var gridItems = dijit.byId('networkIPVMListGrid').selection.getSelected();        
        var vmId = "";
        dojo.forEach(gridItems, function(selectedItem) {            
            dojo.forEach(selectedItem.id, function (el) {
                vmId = el;
            })
        });

        var privateStartPort = dijit.byId("ipPortForwardingPrivateStartPort");
        var publicStartPort = dijit.byId("ipPortForwardingPublicStartPort");
        var privateEndPort = dijit.byId("ipPortForwardingPrivateEndPort");
        var publicEndPort = dijit.byId("ipPortForwardingPublicEndPort");
        var protocol = dijit.byId("ipPortForwardingProtocol");
        var vmIp = dojo.byId("portForwarCurrentIp").value ;
        var networkId = dojo.byId("currentNetworkId");
        var currentIPId = dojo.byId("currentIPId");

//         if(wigetState == true) {                     
             if(gridItems.length == 0) {
//                 dojo.query("#VMRequireMsg").removeClass("hide_text", true);
                 dojo.query("#VMRequireCopyMsg").removeClass("hide_text", true);
             } else {
//                 dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
                 dojo.query("#VMRequireCopyMsg").toggleClass("hide_text", true);
                 dijit.byId('networkPortForwardingLoader').show();
                 var networkLoadBalancerRestStore = new JsonRest({
                     target: core.getContextPath() + "/api/network/portForwarding/add"
                 });

                networkLoadBalancerRestStore.add({ipId: currentIPId.value, networkId: networkId.value,
                    protocol: protocol.value, privateStartPort: privateStartPort.value,
                    publicStartPort: publicStartPort.value, privateEndPort: privateEndPort.value,
                    publicEndPort: publicEndPort.value, vmId: vmId, vmIp: vmIp
                }).then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        if (resultData.result === "OK") {
                            var addPortForwardingJobStatus = setInterval(function() {
                                ViewNetworkIPPortForwardingDetails.addPortForwardingJob(resultData.jobId, addPortForwardingJobStatus);
                            }, 3000);
                            dojo.query("#networkPFCloudstackException").toggleClass("hide_text", true);
                            dojo.byId("networkPFCloudstackExceptionMsg").innerHTML = "";
                        } else if (resultData.result === "FAILED") {                                   
                            registry.byId("userToaster").setContent(resultData.message, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('userNetworkIPPortForwardingAddForm').reset();
                            dijit.byId("networkPortForwardingLoader").hide();     
                            
                            dojo.query("#networkPFCloudstackException").removeClass("hide_text", true);
                            dojo.byId("networkPFCloudstackExceptionMsg").innerHTML = resultData.message;                                                        
                        } else {
                            registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('userNetworkIPPortForwardingAddForm').reset();
                            dijit.byId('networkPortForwardingLoader').hide();
                            dojo.query("#networkPFCloudstackException").removeClass("hide_text", true);
                            dojo.byId("networkPFCloudstackExceptionMsg").innerHTML = resultData.message;
                        }
                    });
                });
            }
//        }  
    },
    'addPortForwardingJob': function(jobId, addPortForwardingJobStatus) {
        var portForwardingJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/portForwarding/job/"
        });

        portForwardingJobRestStoreStore.get(jobId).then(function(response) {
            dojo.forEach(response, function(jobResultData) {
                if (jobResultData.jobResult === "OK") {
                    clearInterval(addPortForwardingJobStatus);                    
                    registry.byId("userToaster").setContent(translator.common.message.addPortForwardingRuleSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('userNetworkIPPortForwardingAddForm').reset();
                    dijit.byId('networkPortForwardingLoader').hide();
                    dijit.byId("addVMIPPageButton").set("disabled", false);
                    dojo.byId("vmListContainer").style.display="none";    
//                    dojo.byId("addIPButtonDiv").style.display="none";
                    dojo.byId("addIPButtonCopyDiv").style.display="none";
                    dojo.query("#userIpExistList").removeClass("hide_text", true); 
//                    dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
                    dojo.query("#VMRequireCopyMsg").toggleClass("hide_text", true);
                    dijit.byId('networkIPVMListGrid').render();
                    if(dijit.byId('networkIpPortForwardingGrid')) {      
                        dijit.byId('networkIpPortForwardingGrid').render();
                    }                    
                    ViewNetworkIPPortForwardingDetails.init();
                    ViewNetworkIPPortForwardingDetails.list();
                    dojo.query("#networkPFCloudstackException").toggleClass("hide_text", true);
                    dojo.byId("networkPFCloudstackExceptionMsg").innerHTML = "";
                } else if (jobResultData.jobResult === "Pending") {

                } else {                
                    clearInterval(addPortForwardingJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('userNetworkIPPortForwardingAddForm').reset();
                    dijit.byId('networkPortForwardingLoader').hide();        
                    
                    dojo.query("#networkPFCloudstackException").removeClass("hide_text", true);
                    dojo.byId("networkPFCloudstackExceptionMsg").innerHTML = resultData.message;
                }
            });
        });
    },
    'deletePortForwardingRuleJob': function(jobId, id, deleteLoadBalancingRuleJobStatus) {
        var portForwardingJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/portForwarding/delete/job/"
        });

        portForwardingJobRestStoreStore.add({portForwardId:id,jobId:jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deleteLoadBalancingRuleJobStatus);
                    ViewNetworkIPPortForwardingDetails.list();
                    registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('networkPortForwardingLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkPortForwardingLoader').hide();
                }
            });
        });
    },
    'getVMIPList': function(networkId, vmId) {
        if (vmId == null || vmId == "" || vmId == "null") {
            return false;
        }
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/nic/ip/list/"
        });

        var ipOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var ipList = new ItemFileWriteStore({data: ipOptions});
        var firstValue;
        networkVMListRestStore.query({networkId: networkId, vmId: vmId}).then(function(data) {

            dojo.forEach(data, function(ip, index) {
                ipList.newItem({
                    id: ip.referenceId,
                    name: ip.ip
                });
                if (index == 0) {
                    firstValue = ip.referenceId;
                }
            });

            dijit.byId("portForwardingVMIp").reset();
            dijit.byId("portForwardingVMIp").set("store", ipList);
            dijit.byId("portForwardingVMIp").set("value", firstValue);
        });
    },
    'populateValues': function() {
        dojo.query("#networkPFCloudstackException").toggleClass("hide_text", true);
        dojo.byId("networkPFCloudstackExceptionMsg").innerHTML = "";
        
        dijit.byId('userNetworkIPPortForwardingAddForm').reset();
        
        var id = dojo.byId("currentIPId").value;
        var networkId = dojo.byId("ipNetworkId").value;
        
        
        dojo.byId("ipFirewallLink").href = "#/user/network/firewall/" + id;
        dojo.byId("ipSummaryLink").href = "#/user/network/viewIp/" + id;
//        dojo.byId("currentIP").href = "#/user/network/viewIp/" + id;
//        dojo.byId("currentIPId").value = id;
        dojo.byId("ipPortForwardingLink").href = "#/user/network/portForwarding/" + id;
        dojo.byId("ipLoadBalancingLink").href = "#/user/network/loadBalancing/" + id;

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/get/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("currentNetworkId").value = resultData.networkId;
                dojo.byId("networkIPListLink").href = "#/user/network/view/" + resultData.networkId;
                dojo.byId("currentIP").innerHTML = resultData.ip;
                dojo.byId("currentNetworkName").innerHTML = resultData.networkName;
                dojo.byId("currentNetworkName").href = "#/user/network/view/" + resultData.networkId;

                if (resultData.isStaticNat === "false" || resultData.isStaticNat === false) {
                    dojo.byId("portForwardingDiv").style.display = "block";
                    dojo.byId("loadBalancingDiv").style.display = "block";
                }

            });
            ViewNetworkIPPortForwardingDetails.list();
            ViewNetworkIPPortForwardingDetails.cancelVMGrid()
        });
    }

};

var ViewNetworkIPLoadBalancingDetails = {
    'init': function() {

    },
    cancelVMGrid : function () {
        dojo.query("#networkLBCloudstackException").toggleClass("hide_text", true);
        dojo.byId("networkLBCloudstackExceptionMsg").innerHTML = "";
        dojo.byId("userLBVMContainer").style.display="none";                        
        dojo.query("#userLBExistContainer").removeClass("hide_text", true); 
        dojo.query("#LBVMRequireMsg").toggleClass("hide_text", true);
        dojo.query("#LBVMRequireMsgCopy").toggleClass("hide_text", true);               
        dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
        dijit.byId("addVMLBPageButton").set("disabled", false);
        dojo.byId("addLBButtonDiv").style.display="none";
        dojo.byId("addLBButtonDivCopy").style.display="none";                   
        if(dijit.byId('networkIpLoadBalancingGrid')) {
            dijit.byId('networkIpLoadBalancingGrid').render();
        }
        
        dojo.byId("LBAdditionalVMContainer").style.display="none";
        dojo.byId("addLBAdditionalVMButtonDiv").style.display="none";
        dojo.query("#userLBExistContainer").removeClass("hide_text", true);
        dojo.byId("addLBVMButtonDiv").style.display="block";
        
        dojo.byId("lbRemoveVMListContainer").style.display="none";
        dojo.byId("removeLBAdditionalVMButtonDiv").style.display="none";
                
        
    },
    showLBVMList : function () {      
        var pageNode = dojo.byId("userNetworkIPLoadBalancingAddPage");
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
        }
        
         if(wigetState == true) {         
//             dojo.query("#LBVMRequireMsg").toggleClass("hide_text", true); 
             dojo.query("#LBVMRequireMsgCopy").toggleClass("hide_text", true);       
             dojo.byId("userLBVMContainer").style.display="block";
//             dojo.byId("addLBButtonDiv").style.display="block";
             dojo.byId("addLBButtonDivCopy").style.display="block";
             dojo.query("#userLBExistContainer").toggleClass("hide_text", true);   
             dijit.byId("addVMLBPageButton").set("disabled", true);
             dijit.byId('LBIPVMListGrid').render();        
         }      
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

                        var html = "<a onclick='ViewNetworkIPLoadBalancingDetails.deleteVmFromLoadBalancerRuleShow(\"" + data + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
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
                    var selectedLbGrid = dijit.byId("networkIpLoadBalancingGrid");

                    var  currentDataItem = selectedLbGrid.selection.getSelected();
                    dojo.forEach(currentDataItem, function(el) {
                        dijit.byId("loadBalancingAlgorithm").set("value", el.algorithm);
                        dijit.byId("ipLoadBalancingName").setValue(el.name);
                        dijit.byId("loadBalancingPrivatePort").setValue(el.privatePort);
                        dijit.byId("loadBalancingPublicPort").setValue(el.publicPort);
                    });
                },300);
                dojo.byId("lbRemoveVMListContainer").style.display="block";
                dojo.byId("removeLBAdditionalVMButtonDiv").style.display="block";
                dojo.query("#userLBExistContainer").toggleClass("hide_text", true);
                dojo.byId("addLBVMButtonDiv").style.display="none";
            }
        });
        
    }, 
    'deleteVmFromLoadBalancerRuleShow' : function(vmId) {   
    
        dojo.byId("lbCurrentVMId").value = vmId;
        dijit.byId('lbRemoveVMDialog').show();
        
    },
    removeVMFromLb : function() { 
        
        dijit.byId('networkLoadBalancerLoader').show();

        var networkLoadBalancerRestStore = new JsonRest({
           target: core.getContextPath() + "/api/network/loadBalancing/removeVM"
        });
        networkLoadBalancerRestStore.add({lbId: dojo.byId("currentLoadBalancingId").value, vmId: dojo.byId("lbCurrentVMId").value }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var addLoadBalancingRuleJobStatus = setInterval(function() {
                        ViewNetworkIPLoadBalancingDetails.removeVMFromLbJob(resultData.jobId, dojo.byId("lbCurrentVMId").value, dojo.byId("currentLoadBalancingId").value, addLoadBalancingRuleJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoadBalancerLoader").hide();
                    dijit.byId('lbRemoveVMDialog').hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkLoadBalancerLoader').hide();    
                    dijit.byId('lbRemoveVMDialog').hide();
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
                    dojo.byId("lbRemoveVMListContainer").style.display="none";
                    dojo.byId("removeLBAdditionalVMButtonDiv").style.display="none";
                    dojo.query("#userLBExistContainer").removeClass("hide_text", true);
                    dojo.byId("addLBVMButtonDiv").style.display="block";
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    ViewNetworkIPLoadBalancingDetails.populateValues();
                    
                    dijit.byId('networkLoadBalancerLoader').hide();  
                    dijit.byId('lbRemoveVMDialog').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(removeVMLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dojo.byId("lbRemoveVMListContainer").style.display="none";
                    dojo.byId("removeLBAdditionalVMButtonDiv").style.display="none";
                    dojo.query("#userLBExistContainer").removeClass("hide_text", true);
                    dojo.byId("addLBVMButtonDiv").style.display="block";
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    ViewNetworkIPLoadBalancingDetails.populateValues();
                    dijit.byId('networkLoadBalancerLoader').hide();  
                    dijit.byId('lbRemoveVMDialog').hide();
                }
            });
        });
        
    },
    cancelRemoveVMFromLb : function() { 
        dojo.byId("lbRemoveVMListContainer").style.display="none";
        dojo.byId("removeLBAdditionalVMButtonDiv").style.display="none";
        dojo.query("#userLBExistContainer").removeClass("hide_text", true);
        dojo.byId("addLBVMButtonDiv").style.display="block";
        dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
    },
    cancelRemoveVMFromLbDialog : function() { 
        dijit.byId('lbRemoveVMDialog').hide();
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
                    var selectedLbGrid = dijit.byId("networkIpLoadBalancingGrid");

                    var  currentDataItem = selectedLbGrid.selection.getSelected();
                    dojo.forEach(currentDataItem, function(el) {
                        dijit.byId("loadBalancingAlgorithm").set("value", el.algorithm);
                        dijit.byId("ipLoadBalancingName").setValue(el.name);
                        dijit.byId("loadBalancingPrivatePort").setValue(el.privatePort);
                        dijit.byId("loadBalancingPublicPort").setValue(el.publicPort);
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
                dojo.query("#userLBExistContainer").toggleClass("hide_text", true);
                dojo.byId("addLBVMButtonDiv").style.display="none";
            }
        });
    },
    'cancelAddAdditionalVM' : function() { 
        dojo.byId("addLBVMButtonDiv").style.display="block";
        dojo.query("#userLBExistContainer").removeClass("hide_text", true);
        dojo.byId("LBAdditionalVMContainer").style.display="none";
        dojo.byId("addLBAdditionalVMButtonDiv").style.display="none";
        dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
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

           dijit.byId('networkLoadBalancerLoader').show();

           var networkLoadBalancerRestStore = new JsonRest({
              target: core.getContextPath() + "/api/network/loadBalancing/addVM"
           });
           networkLoadBalancerRestStore.add({lbId: lbId, vmList: vmListArray.toString()}).then(function(data) {
               dojo.forEach(data, function(resultData) {
                   if (resultData.result === "OK") {
                       var addLoadBalancingRuleJobStatus = setInterval(function() {
                           ViewNetworkIPLoadBalancingDetails.addLoadBalancingToVMJob(resultData.jobId, vmListArray.toString(), lbId,addLoadBalancingRuleJobStatus);
                       }, 3000);
                   } else if (resultData.result === "FAILED") {
                       registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                       registry.byId("userToaster").show();
                       dijit.byId("networkLoadBalancerLoader").hide();
                   } else {
                       registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                       registry.byId("userToaster").show();
                       dijit.byId('networkLoadBalancerLoader').hide();                    
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
                    dojo.byId("LBAdditionalVMContainer").style.display="none";
                    dojo.byId("addLBAdditionalVMButtonDiv").style.display="none";
                    dojo.query("#userLBExistContainer").removeClass("hide_text", true);
                    dojo.byId("addLBVMButtonDiv").style.display="block";
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    ViewNetworkIPLoadBalancingDetails.populateValues();
                    
                    dijit.byId('networkLoadBalancerLoader').hide();        
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(addLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    dijit.byId('networkLoadBalancerLoader').hide();        
                }
            });
        });
    },
    'addLoadBalancing': function() {                        
        var algorithm = dijit.byId("loadBalancingAlgorithm");
        var name = dijit.byId("ipLoadBalancingName");
        var privatePort = dijit.byId("loadBalancingPrivatePort");
        var publicPort = dijit.byId("loadBalancingPublicPort");
        var vmList = dojo.byId("loadBalancingVMList");
        var networkId = dojo.byId("currentNetworkId");
        var currentIPId = dojo.byId("currentIPId");                
        var vmListArray = new Array();
        var selectedGridItems = dijit.byId('LBIPVMListGrid').selection.getSelected();   
//        alert(selectedGridItems.length)
        if (selectedGridItems.length == 0) {         
//            dojo.query("#LBVMRequireMsg").removeClass("hide_text", true);
            dojo.query("#LBVMRequireMsgCopy").removeClass("hide_text", true);  
        } else {            
//            dojo.query("#LBVMRequireMsg").toggleClass("hide_text", true);
            dojo.query("#LBVMRequireMsgCopy").toggleClass("hide_text", true);  
            dojo.forEach(selectedGridItems, function(selectedItem, index) {    
                    dojo.forEach(selectedItem.id, function (el) {                        
                        vmListArray[index] = el;
                    });
                    });
                dijit.byId('networkLoadBalancerLoader').show();
                var networkLoadBalancerRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/network/loadBalancing/add"
                });
                networkLoadBalancerRestStore.add({ipId: currentIPId.value, networkId: networkId.value, algorithm: algorithm.value, name: name.value, privatePort: privatePort.value, publicPort: publicPort.value, vmList: vmListArray.toString()}).then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        if (resultData.result === "OK") {
                            var addLoadBalancingRuleJobStatus = setInterval(function() {
                                ViewNetworkIPLoadBalancingDetails.addLoadBalancingRuleJob(resultData.jobId, addLoadBalancingRuleJobStatus);
                            }, 3000);
                            
                            dojo.query("#networkLBCloudstackException").toggleClass("hide_text", true);
                            dojo.byId("networkLBCloudstackExceptionMsg").innerHTML = "";
                        } else if (resultData.result === "FAILED") {
                            registry.byId("userToaster").setContent(resultData.message, "error");
                            registry.byId("userToaster").show();
                            dijit.byId("networkLoadBalancerLoader").hide();
                            
                            dojo.query("#networkLBCloudstackException").removeClass("hide_text", true);
                            dojo.byId("networkLBCloudstackExceptionMsg").innerHTML = resultData.message;
                        } else {
                            registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('networkLoadBalancerLoader').hide();     
                            
                            dojo.query("#networkLBCloudstackException").removeClass("hide_text", true);
                            dojo.byId("networkLBCloudstackExceptionMsg").innerHTML = resultData.message;
                        }
                    });
                });
            }            
//        }
    },
    'addLoadBalancingRuleJob': function(jobId, addLoadBalancingRuleJobStatus) {
        var loadBalancerJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/job/"
        });

        loadBalancerJobRestStoreStore.get(jobId).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(addLoadBalancingRuleJobStatus);                    
                    registry.byId("userToaster").setContent(translator.common.message.addLoadBalancerRuleSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    dijit.byId('networkLoadBalancerLoader').hide();                                        
                   
                    dojo.byId("userLBVMContainer").style.display="none";                           
                    dojo.query("#userLBExistContainer").removeClass("hide_text", true); 
//                    dojo.query("#LBVMRequireMsg").toggleClass("hide_text", true);
                    dojo.query("#LBVMRequireMsgCopy").toggleClass("hide_text", true);
//                    dojo.byId("addLBButtonDiv").style.display="none";
                    dojo.byId("addLBButtonDivCopy").style.display="none";
                    dijit.byId("addVMLBPageButton").set("disabled", false);
                    ViewNetworkIPLoadBalancingDetails.populateValues();
                    ViewNetworkIPLoadBalancingDetails.list(); 
                    
                    dojo.query("#networkLBCloudstackException").toggleClass("hide_text", true);
                    dojo.byId("networkLBCloudstackExceptionMsg").innerHTML = "";
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(addLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    dijit.byId('networkLoadBalancerLoader').hide();
                    
                    dojo.query("#networkLBCloudstackException").removeClass("hide_text", true);
                    dojo.byId("networkLBCloudstackExceptionMsg").innerHTML = resultData.message
                }
            });
        });
    },
    'deleteLoadBalancingRuleJob': function(jobId, lbId,deleteLoadBalancingRuleJobStatus) {        
        var loadBalancerJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/delete/job"
        });

        loadBalancerJobRestStoreStore.add({jobId:jobId, lbId:lbId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deleteLoadBalancingRuleJobStatus);
                    ViewNetworkIPLoadBalancingDetails.list();
                    registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('networkLoadBalancerLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkLoadBalancerLoader').hide();
                }
            });
        });
    },
    'populateValues': function() {    
        dojo.query("#networkLBCloudstackException").toggleClass("hide_text", true);
        dojo.byId("networkLBCloudstackExceptionMsg").innerHTML = "";
        var id = dojo.byId("currentIPId").value;        
        dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
        dojo.byId("ipFirewallLink").href = "#/user/network/firewall/" + id;
        dojo.byId("ipSummaryLink").href = "#/user/network/viewIp/" + id;
        dojo.byId("ipPortForwardingLink").href = "#/user/network/portForwarding/" + id;
        dojo.byId("ipLoadBalancingLink").href = "#/user/network/loadBalancing/" + id;                        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/get/"
        });
        if (dijit.byId("LBIPVMListGrid")) {
            dijit.byId("LBIPVMListGrid").destroyRecursive();
        }
        if (dijit.byId("LBAdditionalVMListGrid")) {
            dijit.byId("LBAdditionalVMListGrid").destroyRecursive();
        }
        
        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("currentNetworkId").value = resultData.networkId;
                dojo.byId("networkIPListLink").href = "#/user/network/view/" + resultData.networkId;
                dojo.byId("currentIP").innerHTML = resultData.ip;
                dojo.byId("currentNetworkName").innerHTML = resultData.networkName;
                dojo.byId("currentNetworkName").href = "#/user/network/view/" + resultData.networkId;

                if (resultData.isStaticNat === "false" || resultData.isStaticNat === false) {
                    dojo.byId("portForwardingDiv").style.display = "block";
                    dojo.byId("loadBalancingDiv").style.display = "block";
                }

            });
        })
            ViewNetworkIPLoadBalancingDetails.list();                        
            var instanceOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            };
        var instanceList = new ItemFileWriteStore({data: instanceOptions});
        
        var vmDatalayout = [
            [   
                {'field': 'id', 'hidden': 'true'},             
                {'name': translator.common.name, 'field': 'name', 'width': '400px', datatype: "string"},
                {'name': translator.common.zone, 'field': 'zone', 'width': '400px', datatype: "string"},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', datatype: "string", "formatter" : function (data) {
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
        
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/vm/list/"
        });

        networkVMListRestStore.query({networkId: dojo.byId("currentNetworkId").value}).then(function(data) {            
            if(data.length == 0 || data == "" || data == "undefined") {
                dojo.byId("LBNoVMList").style.display = "block";
            } else {
                dojo.forEach(data, function(vm) {
                    instanceList.newItem({id: vm.referenceId, name: vm.name, zone: vm.zone, state: vm.state});
                });
                var vmGrid = new dojox.grid.EnhancedGrid({
                    id: "LBIPVMListGrid",
                    "class" : "span12",
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
                    },
                    onRowDblClick: function(evnt) {
//                        alert(evnt)
                    },
                    onApplyCellEdit: function(inValue, inRowIndex, inFieldIndex) {      
//                        alert(1)
                        this.store.revert();
                        setTimeout(function() { 
                            grid._refresh();
                        },0);
                    }
                });
                
            vmGrid.placeAt("UserLBVMList");
            vmGrid.startup();
        }
    })         
    },
    'list': function() {        
        dojo.byId("userIpLoadBalancingList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var loadBalancingData = {
            items: []
        };

        var loadBalancingDataList = new ItemFileWriteStore({data: loadBalancingData});
        var loadBalancingDatalayout = [
            [
                {'name': 'ID', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.algorithm, 'field': 'algorithm', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.privatePort, 'field': 'privatePort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.publicPort, 'field': 'publicPort', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.vmList, 'field': 'vm', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {

                        var html = "<a onclick='ViewNetworkIPLoadBalancingDetails.editLBShow(\"" + data.id + "," + data.name + "," + data.algorithm + "\")' title='" + translator.common.edit + "'><img src='images/edit.png'></img></a>" +
                                "<a onclick='ViewNetworkIPLoadBalancingDetails.showAddLoadBalancingAdditionalVM(\"" + data.id + "\")' class='offset1' title='" + translator.common.addVM + "'><img src='images/add_vm_icon.png'></img></a></li>"+
                                "<a onclick='ViewNetworkIPLoadBalancingDetails.showRemoveLoadBalancingVM(\"" + data.id + "\")' class='offset1' title='" + translator.common.removeVM + "'><img src='images/delete_vm_icon.png'></img></a></li>"+
                                "<a onclick='ViewNetworkIPLoadBalancingDetails.deleteLoadBalancerRuleShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
  

                        return html;
                    }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        var ipId = dojo.byId("currentIPId").value;

        var networkEgressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/list/"
        });

        networkEgressRestStore.get(ipId).then(function(data) {
            if (!data || data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userIpLoadBalancingList").innerHTML = "";
                dojo.byId("noLoadBalancingMessageBox").style.display = "block";
            } else {
                if (dijit.byId("networkIpLoadBalancingGrid")) {
                    dijit.byId("networkIpLoadBalancingGrid").destroyRecursive();
                }
                dojo.byId("noLoadBalancingMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    loadBalancingDataList.newItem({
                        id: resultData.referenceId,
                        name: resultData.name,
                        vm: resultData.vmList.toString(),
                        algorithm: resultData.algorithm,
                        publicPort: resultData.publicPort,
                        privatePort: resultData.privatePort,
                        action: {id: resultData.referenceId, name: resultData.name, algorithm: resultData.algorithm}
                    });
                });
                dojo.byId("userIpLoadBalancingList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: "networkIpLoadBalancingGrid",
                    class: "span12",
                    store: loadBalancingDataList,
                    structure: loadBalancingDatalayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins              
                });
                dataGrid.placeAt("userIpLoadBalancingList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },    
    'deleteLoadBalancerRuleShow': function(id) {
        dojo.byId("currentLBId").value = id;
        dijit.byId('networkDeleteLBDialog').show();
    },
    'deleteLoadBalancerRule': function() {

        var networkRuleRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/remove/"
        });

        dijit.byId('networkLoadBalancerLoader').show();
        dijit.byId('networkDeleteLBDialog').hide();

        networkRuleRestStore.add({lbId: dojo.byId("currentLBId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var deleteLoadBalancingRuleJobStatus = setInterval(function() {
                        ViewNetworkIPLoadBalancingDetails.deleteLoadBalancingRuleJob(resultData.jobId, dojo.byId("currentLBId").value,deleteLoadBalancingRuleJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoadBalancerLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkLoadBalancerLoader').hide();
                }
            });
        });
    },
    'deleteLoadBalancerRuleClose': function() {
        dijit.byId('networkDeleteLBDialog').hide();
    },
    'editLBShow': function(data) {

        var LBData = data.split(",");

        dojo.byId("currentLBId").value = LBData[0];

        dijit.byId('ipLoadBalancingEditName').setValue(LBData[1]);
        dijit.byId('loadBalancingEditAlgorithm').set("value", LBData[2]);
        dijit.byId('networkEditLBDialog').show();
    },
    'closeEditLB': function() {
        dojo.query("#networkLBCloudstackException").toggleClass("hide_text", true);
        dojo.byId("networkLBCloudstackExceptionMsg").innerHTML = "";
        dijit.byId('networkEditLBDialog').hide();
    },
    'editLBRule': function() {
        
        var pageNode = dojo.byId("lbEditPageDiv");
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
        
            var networkRuleRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/loadBalancing/edit/"
            });
            dijit.byId('networkLoadBalancerLoader').show();
            networkRuleRestStore.put({id: dojo.byId("currentLBId").value, name: dijit.byId('ipLoadBalancingEditName').value, algorithm: dijit.byId('loadBalancingEditAlgorithm').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.ruleUpdateSuccess, "message");
                        registry.byId("userToaster").show();
                        ViewNetworkIPLoadBalancingDetails.list();
                        dijit.byId('networkEditLBDialog').hide();
                        dijit.byId('networkLoadBalancerLoader').hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('networkLoadBalancerLoader').hide();
                    }
                });
            });
        }
    }

};
var ViewNetworkIPFirewallDetails = {
    'init': function() {

    },
    'populateValues': function() {
        
        dijit.byId('userNetworkIPFirewallAddForm').reset();
        
        var id = dojo.byId("currentIPId").value;
//        var networkId = dojo.byId("ipNetworkId").value;

        dojo.byId("ipFirewallLink").href = "#/user/network/firewall/" + id;
        dojo.byId("ipSummaryLink").href = "#/user/network/viewIp/" + id;
//        dojo.byId("currentIP").href = "#/user/network/viewIp/" + id;
//        dojo.byId("currentIPId").value = id;
        dojo.byId("ipPortForwardingLink").href = "#/user/network/portForwarding/" + id;
        dojo.byId("ipLoadBalancingLink").href = "#/user/network/loadBalancing/" + id;

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/get/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("networkIPListLink").href = "#/user/network/view/" + resultData.networkId;
                dojo.byId("currentIP").innerHTML = resultData.ip;
                dojo.byId("currentNetworkName").innerHTML = resultData.networkName;
                dojo.byId("currentNetworkName").href = "#/user/network/view/" + resultData.networkId;

                if (resultData.isStaticNat === "false" || resultData.isStaticNat === false) {
                    dojo.byId("portForwardingDiv").style.display = "block";
                    dojo.byId("loadBalancingDiv").style.display = "block";
                }

            });
            ViewNetworkIPFirewallDetails.list();
        });
    },
    'addFirewall': function() {

        var cidr = dijit.byId("ipFirewallCidr");
        var icmpType = dijit.byId("ipFirewallIcmpType");
        var icmpCode = dijit.byId("ipFirewallIcmpCode");

        var startPort = dijit.byId("ipFirewallStratPort");
        var endPort = dijit.byId("ipFirewallEndport");

        var protocol = dijit.byId("ipFirewallProtocol");

        var ipId = dojo.byId("currentIPId");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/firewall/add"
        });


        var pageNode = dojo.byId("userNetworkIPFirewallAddPage");
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

            dijit.byId('ipFirewallAddButton').set('style', {display: 'none'});
            dojo.byId("ipFirewallLoader").style.display = "block";


            networkRestStore.add({protocol: protocol.value, cidr: cidr.value, icmpType: icmpType.value, icmpCode: icmpCode.value,
                startPort: startPort.value, endPort: endPort.value, ipId: ipId.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var addFirewallJobStatus = setInterval(function() {
                            ViewNetworkIPFirewallDetails.addFirewallJob(resultData.jobId, addFirewallJobStatus);
                        }, 3000);
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('ipFirewallAddButton').set('style', {display: 'block'});
                        dojo.byId("ipFirewallLoader").style.display = "none";
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('ipFirewallAddButton').set('style', {display: 'block'});
                        dojo.byId("ipFirewallLoader").style.display = "none";
                    }
                });
            });
        }
    },
    'list': function() {

        if (dijit.byId("networkIpFirewallRuleGrid")) {
            dijit.byId("networkIpFirewallRuleGrid").destroyRecursive();
        }
        dojo.byId("userIpFirewallRuleList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var firewallData = {
            items: []
        };

        var firewallDataList = new ItemFileWriteStore({data: firewallData});
        var firewallRuleslayout = [
            [
                {'name': 'ID', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.firewall.protocol, 'field': 'protocol', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.firewall.startPort, 'field': 'startPort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.firewall.endPort, 'field': 'endPort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {
                        var deleteButton = new dijit.form.Button({
                            "class": "delete_icon",
                            onClick: function() {
                                var grid;
                                var networkRuleRestStore = new JsonRest({
                                    target: core.getContextPath() + "/api/network/firewall/remove/"
                                });
                                grid = dijit.byId("networkIpFirewallRuleGrid");
                                networkRuleRestStore.remove(data).then(function(result) {
                                    if (result == "OK") {
                                        var items = grid.selection.getSelected();
                                        dojo.forEach(items, function(selectedItem) {
                                            grid.store.deleteItem(selectedItem);
                                        });
                                        registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess, "message");
                                        registry.byId("userToaster").show();
                                        setTimeout(function() {
                                            ViewNetworkIPFirewallDetails.list();
                                        }, 3000);

                                    } else {
                                        registry.byId("userToaster").setContent(translator.common.firewall.cloudStackError, "error");
                                        registry.byId("userToaster").show();
                                    }
                                });
                            }
                        });
                        return deleteButton;
                    }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        var ipId = dojo.byId("currentIPId").value;

        var networkEgressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/firewall/list/"
        });

        networkEgressRestStore.get(ipId).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userIpFirewallRuleList").innerHTML = "";
                dojo.byId("noOfferMessageBox").style.display = "block";
            } else {
                dojo.byId("noOfferMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    firewallDataList.newItem({
                        id: resultData.id,
                        "class":"span12",
                        cidr: resultData.cidrList,
                        protocol: resultData.protocol,
                        endPort: resultData.endPort,
                        startPort: resultData.startPort,
                        action: resultData.id
                    });
                });
                dojo.byId("userIpFirewallRuleList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: "networkIpFirewallRuleGrid",
                    store: firewallDataList,
                    structure: firewallRuleslayout,
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
                    }
                });
                dataGrid.placeAt("userIpFirewallRuleList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    'changeEgressRuleLabel': function(protocol) {
        if (protocol.value === "ICMP") {
            dojo.byId("ipFirewallStratPortDiv").style.display = "none";
            dojo.byId("ipFirewallIcmpTypeDiv").style.display = "block";
            dojo.byId("ipFirewallEndportDiv").style.display = "none";
            dojo.byId("ipFirewallIcmpCodeDiv").style.display = "block";

            dijit.byId("ipFirewallIcmpType").set("required", true);
            dijit.byId("ipFirewallIcmpCode").set("required", true);

        } else if (protocol.value === "UDP" || protocol.value === "TCP" || protocol.value === "" || protocol.value === null) {
            dojo.byId("ipFirewallStratPortDiv").style.display = "block";
            dojo.byId("ipFirewallIcmpTypeDiv").style.display = "none";
            dojo.byId("ipFirewallEndportDiv").style.display = "block";
            dojo.byId("ipFirewallIcmpCodeDiv").style.display = "none";

            dijit.byId("ipFirewallIcmpType").set("required", false);
            dijit.byId("ipFirewallIcmpCode").set("required", false);
        } 
    },
    'addFirewallJob': function(jobId, addFirewallJobStatus) {
        var egressJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/firewall/get/job/"
        });

        egressJobRestStoreStore.get(jobId).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(addFirewallJobStatus);
                    ViewNetworkIPFirewallDetails.list();
                    registry.byId("userToaster").setContent(translator.common.message.addFirewallRuleSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('ipFirewallAddButton').set('style', {display: 'block'});
                    dojo.byId("ipFirewallLoader").style.display = "none";
                    dijit.byId('userNetworkIPFirewallAddForm').reset();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(addFirewallJobStatus);
                    dijit.byId('networkEgressAddButton').set('style', {display: 'block'});
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('ipFirewallAddButton').set('style', {display: 'block'});
                    dojo.byId("ipFirewallLoader").style.display = "none";
                }
            });
        });
    }
};
var ViewNetworkIPDetails = {
    
    'init': function() {        
        var id = dojo.byId("currentNetworkId").value;        
        var ipOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var ipList = new ItemFileWriteStore({data: ipOptions});
        
        var ipWidget = new FilteringSelect({
            id: "staticNatVMIpInViewIp",
            store: ipList,
            placeHolder: translator.common.message.selectIp
        });
        ipWidget.startup();
        ipWidget.placeAt("staticNatVMIpDivInViewIp");
        
        
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/vm/list/"
        });

        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var ipInstanceList = new ItemFileWriteStore({data: instanceOptions});

        networkVMListRestStore.query({networkId: id}).then(function(data) {
            dojo.forEach(data, function(vm) {
                ipInstanceList.newItem({
                    id: vm.referenceId,
                    name: vm.name
                });
            });
            var instanceWidget = new FilteringSelect({
                id: "staticNatVMInViewIp",
                store: ipInstanceList,
                placeHolder: translator.common.message.selectInstance,
                onChange: function() {
                    ViewNetworkIPDetails.getVMIPList(id, this.value);
                }
            });
            instanceWidget.placeAt("staticNatVMListInViewIp");
            instanceWidget.startup();
        });        
    },
    showEnableVPnWarningMsg : function () {
        dojo.style(dijit.byId("networkIpEnableWarningMsgDialog").closeButtonNode,"display","none");
        dijit.byId("networkIpEnableWarningMsgDialog").show();
    },
    enableVPNShow : function () {
        dojo.style(dijit.byId("networkIpEnableVPN").closeButtonNode,"display","none");
        dijit.byId("vnpEnableCancelButton").set("disabled", false);
        dijit.byId("networkIpEnableVPN").show();        
    },
    disableVPNShow : function () {
        dojo.style(dijit.byId("networkIpDisableVPN").closeButtonNode,"display","none");
        dijit.byId("vnpDisableCancelButton").set("disabled", false);
        dijit.byId("networkIpDisableVPN").show();        
    },
    cancelVPNDialogue : function () {
        dijit.byId("networkIpEnableVPN").hide();        
        dijit.byId("networkIpDisableVPN").hide();  
        dijit.byId("networkIpEnableWarningMsgDialog").hide();
    },
    
    disableVPN : function () {
        dijit.byId("vnpDisableCancelButton").set("disabled", true);
        dojo.query("#networkIPVPNDisableLoader").removeClass("hide_text", true); 
        dojo.query("#vpnDisableOKButton").toggleClass("hide_text", true); 
        
        var curretnIPId = dojo.byId("currentIPId").value;        
        var disableVPNRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/disable"
        });
        disableVPNRestStore.add({
            ipAddressId : curretnIPId
        }).then(function (response) {            
            dojo.forEach(response, function (resultData) {               
                if(resultData.result === "OK") {
                    var vpnEnableJobStatus = setInterval(function(){ViewNetworkIPDetails.disableIPJob(resultData.jobId,  resultData.vpnReferenceId,  vpnEnableJobStatus);},1000);                   
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                    registry.byId("userToaster").show();    
                    
                    dijit.byId("vnpDisableCancelButton").set("disabled", false);
                    dojo.query("#networkIPVPNDisableLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnDisableOKButton").removeClass("hide_text", true);
                    dijit.byId("networkIpDisableVPN").hide();
                }
            })
        })
    },
    disableIPJob : function (jobId, vpnReferenceId, vpnEnableStatus) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/remoteAccessVpn/vpnDisableJob"
        });        
        var networkId = dojo.byId("networkReferenceId").innerHTML;
        jobStore.add({
            jobId : jobId,
            vpnReferenceId : vpnReferenceId                
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpnEnableStatus);
                    registry.byId("userToaster").setContent(translator.common.message.vpnDisabled, "message");
                    registry.byId("userToaster").show();         

                    dijit.byId("vnpDisableCancelButton").set("disabled", false);
                    dojo.query("#networkIPVPNDisableLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnDisableOKButton").removeClass("hide_text", true);
                    dijit.byId("networkIpDisableVPN").hide();
                    core.router.go("#/user/network/view/"+dojo.byId("currentNetworkId").value);
                    ViewNetwork.showIpTab();
//                        dijit.byId("networkDetailContainer").set("selected", true);
//                        dijit.byId("ipDetailsTabCointainer_tablist_ipVpnUsers").setAttribute('style', 'display: none');
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpnEnableStatus);
                    registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                    registry.byId("userToaster").show(); 

                    dijit.byId("vnpDisableCancelButton").set("disabled", false);
                    dojo.query("#networkIPVPNDisableLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnDisableOKButton").removeClass("hide_text", true);
                    dijit.byId("networkIpDisableVPN").hide();
                } else {
//                        alert(resultData)
                    clearInterval(vpnEnableStatus);
                    registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                    registry.byId("userToaster").show(); 
                    dijit.byId("vnpDisableCancelButton").set("disabled", false);
                    dojo.query("#networkIPVPNDisableLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnDisableOKButton").removeClass("hide_text", true);
                    dijit.byId("networkIpDisableVPN").hide();
                }
            });
        });
    },
    enableVPN : function () {
        dijit.byId("vnpEnableCancelButton").set("disabled", true);
        dojo.query("#networkIPVPNEnableLoader").removeClass("hide_text", true); 
        dojo.query("#vpnEnableOKButton").toggleClass("hide_text", true); 
        
        var curretnIPId = dojo.byId("currentIPId").value;        
         var enableVPNRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/enable"
        });
        enableVPNRestStore.add({
            ipAddressId : curretnIPId
        }).then(function (response) {            
            dojo.forEach(response, function (resultData) {               
                if(resultData.result === "OK") {
                        var vpnEnableJobStatus = setInterval(function(){ViewNetworkIPDetails.enableIPJob(resultData.jobId, resultData.ipAddressId, vpnEnableJobStatus);},1000);                   
                    } else {
                        registry.byId("userToaster").setContent("Failed","error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vnpEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#networkIPVPNEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpEnableVPN").hide();
                        
                        
                    }
                })
            })
        },
        enableIPJob : function (jobId, ipId, vpnEnableStatus) {
            var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/remoteAccessVpn/vpnEnableJob"
        });         
            jobStore.add({
                jobId : jobId,
                ipId : ipId                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.jobResult === "OK") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbed, "message");
                        registry.byId("userToaster").show();                               
                        dijit.byId("vnpEnableCancelButton").set("disabled", false);
                        dojo.query("#networkIPVPNEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpEnableVPN").hide();
                        core.router.go("#/user/network/view/"+dojo.byId("currentNetworkId").value);
                        ViewNetwork.showIpTab();
                    } else if(resultData.jobResult === "Pending") {
                    } else  if(resultData.jobResult === "FAILED") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbedError,"error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vnpEnableCancelButton").set("disabled", false);
                        dojo.query("#networkIPVPNEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpEnableVPN").hide();
                    } else {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbedError,"error");
                        registry.byId("userToaster").show(); 
                        dijit.byId("vnpEnableCancelButton").set("disabled", false);
                        dojo.query("#networkIPVPNEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpEnableVPN").hide();
                    }
                });
            });
        },
    'getVMIPList': function(networkId, vmId) {

        if (vmId == null || vmId == "" || vmId == "null") {
            return false;
        }

        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/nic/ip/list/"
        });

        var ipOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var ipList = new ItemFileWriteStore({data: ipOptions});
        var firstValue;
        networkVMListRestStore.query({networkId: networkId, vmId: vmId}).then(function(data) {

            dojo.forEach(data, function(ip, index) {
                ipList.newItem({
                    id: ip.referenceId,
                    name: ip.ip
                });
                if (index == 0) {
                    firstValue = ip.referenceId;
                }
            });

            dijit.byId("staticNatVMIpInViewIp").reset();
            dijit.byId("staticNatVMIpInViewIp").set("store", ipList);
            dijit.byId("staticNatVMIpInViewIp").set("value", firstValue);
        });
    },
    'enableStaticNatIPShow': function() {

        dijit.byId("networkIPEnableStaticNatForm").reset();

        dijit.byId("networkIPEnableStaticNatDialog").show();
    },
    'disableStaticNatIPShow': function() {
      
        dijit.byId("networkIPDisableStaticNatDialog").show();
    },
    'closeDisableStaticNat': function() {
        dijit.byId("networkIPDisableStaticNatDialog").hide();
    },
    'closeEnableStaticNat': function() {
        dijit.byId("networkIPEnableStaticNatDialog").hide();
    },
    'enableStaticNat': function() {

        var vmId = dijit.byId("staticNatVMInViewIp");
        var vmIpId = dijit.byId("staticNatVMIpInViewIp");

        var pageNode = dojo.byId("networkIPEnableStaticNatFormPage");
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

            dijit.byId('networkIpLoader').show();
            dijit.byId("networkIPEnableStaticNatDialog").hide();

            var ipEnableStaticRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/ip/enableStaticNat"
            });

            ipEnableStaticRestStore.add({vmId: vmId.value, vmIpId: vmIpId.value, ipId: dojo.byId('currentIPId').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var enableStaticNatJobStatus = setInterval(function() {
                            ViewNetworkIPDetails.enableStaticNatJob(resultData.jobId, enableStaticNatJobStatus);
                        }, 3000);
                    } else if (resultData.result === "true") {
                        registry.byId("userToaster").setContent(translator.common.message.enableStaticNatSuccess, "message");
                        registry.byId("userToaster").show();
                        dijit.byId('networkIpLoader').hide();
                        core.router.go("#/user/network/view/"+dojo.byId("currentNetworkId").value);
                        ViewNetwork.showIpTab();
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("networkIpLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('networkIpLoader').hide();

                    }
                });
            });
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
                    dijit.byId('networkIpLoader').hide();
                    core.router.go("#/user/network/view/"+dojo.byId("currentNetworkId").value);
                    ViewNetwork.showIpTab();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(enableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                }
            });
        });
    },
    'disableStaticNat': function() {
        dijit.byId('networkIpLoader').show();
        dijit.byId("networkIPDisableStaticNatDialog").hide();

        var ipEnableStaticRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/disableStaticNat"
        });

        ipEnableStaticRestStore.add({ipId: dojo.byId('currentIPId').value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var disableStaticNatJobStatus = setInterval(function() {
                        ViewNetworkIPDetails.disableStaticNatJob(resultData.jobId, disableStaticNatJobStatus);
                    }, 3000);
                } else if (resultData.result === "true") {
                    registry.byId("userToaster").setContent(translator.common.message.disableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                    core.router.go("#/user/network/view/"+dojo.byId("currentNetworkId").value);
                    ViewNetwork.showIpTab();
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkIpLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();

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
                    dijit.byId('networkIpLoader').hide();
                    core.router.go("#/user/network/view/"+dojo.byId("currentNetworkId").value);
                    ViewNetwork.showIpTab();;
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(disableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                }
            });
        });
    },
    'releaseIPShow': function(id) {

        dijit.byId("neworkReleaseIpDialog").show();
    },
    'closeReleaseIPShow': function() {
        dijit.byId("neworkReleaseIpDialog").hide();
    },
    'releaseIP': function() {

        var ipReleaseRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/release"
        });

        dijit.byId('networkIpLoader').show();
        dijit.byId('neworkReleaseIpDialog').hide();

        ipReleaseRestStore.add({ipId: dojo.byId("currentIPId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var releaseIPJobStatus = setInterval(function() {
                        ViewNetworkIPDetails.releaseIPJob(resultData.jobId, releaseIPJobStatus);
                    }, 3000);
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();

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

                if (jobResultData.jobResult == "OK") {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.releaseIPSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                    core.router.go("#/user/network/view/"+dojo.byId("currentNetworkId").value);
                    ViewNetwork.showIpTab();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                }
            });
        });
    },
    'showFirewall': function() {
        var mainTab = dijit.byId("ipDetailsTabCointainer"); //Tr
        var subIpTab = dijit.byId("ipFireWallTab"); //tab Id which you want to show
        mainTab.selectChild(subIpTab);  
    },
    'showLoadBalancer': function() {
        var mainTab = dijit.byId("ipDetailsTabCointainer"); //Tr
        var subIpTab = dijit.byId("ipLoadBalancing"); //tab Id which you want to show
        mainTab.selectChild(subIpTab);   
    },
    'showPortForward': function() {
        var mainTab = dijit.byId("ipDetailsTabCointainer"); //Tr
        var subIpTab = dijit.byId("ipPortForwarding"); //tab Id which you want to show
        mainTab.selectChild(subIpTab);   
    },    
    'populateValues': function(id) {
        
        dojo.byId("currentIPId").value = id;
                
        dojo.byId("ipSummaryLink").href = "#/user/network/viewIp/" + id;
        dojo.byId("ipFirewallLink").href = "#/user/network/firewall/" + id;
//        dojo.byId("currentIP").href = "#/user/network/viewIp/" + id;
        dojo.byId("ipPortForwardingLink").href = "#/user/network/portForwarding/" + id;
        dojo.byId("ipLoadBalancingLink").href = "#/user/network/loadBalancing/" + id;


        var ip = dojo.byId("ipAddress");
        var network = dojo.byId("ipNetworkName");
        var zone = dojo.byId("ipZoneName");
        var networkReferenceId = dojo.byId("networkReferenceId");
        var ipAddressReferenceId = dojo.byId("ipAddressReferenceId");
        
        var ipTop = dojo.byId("ipAddressTop");
        var networkTop = dojo.byId("ipNetworkNameTop");
        var zoneTop = dojo.byId("ipZoneNameTop");
        

        var sourceNat = dojo.byId("ipSourceNat");
        var staticNat = dojo.byId("ipStaticeNat");

        var vlanName = dojo.byId("ipVLan");
        var state = dojo.byId("ipState");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/get/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("networkIPListLink").href = "#/user/network/view/" + resultData.networkId;
                dojo.byId("currentIP").innerHTML = resultData.ip;
                dojo.byId("currentNetworkName").innerHTML = resultData.networkName;
                dojo.byId("currentNetworkName").href = "#/user/network/view/" + resultData.networkId;
                dojo.byId("ipNetworkId").value = resultData.networkId;
                dojo.byId("currentNetworkId").value = resultData.networkId;
                ip.innerHTML = resultData.ip;
                network.innerHTML = resultData.networkName;
                zone.innerHTML = resultData.zone;
                ipTop.innerHTML = resultData.ip;
                networkTop.innerHTML = resultData.networkName;
                zoneTop.innerHTML = resultData.zone;
                networkReferenceId.innerHTML = resultData.networkReferenceId;
                vlanName.innerHTML = resultData.vlanName;
                ipAddressReferenceId.innerHTML = resultData.id;
                state.innerHTML = resultData.state;
                staticNat.innerHTML = (resultData.isStaticNat === "true" || resultData.isStaticNat === true) ? "Yes" : "No";
                sourceNat.innerHTML = (resultData.isSourceNat === "true" || resultData.isSourceNat === true) ? "Yes" : "No";
                
                if(resultData.vpnEnabled === true && resultData.isEnabledVPN === true) {
                    dojo.query("#networkIpInfoDisableDiv").removeClass("hide_text", true);  
                    dojo.query("#networkIpInfoEnableDiv").toggleClass("hide_text", true);  
                } else if(resultData.vpnEnabled === false && resultData.isEnabledVPN === true) {
                    dojo.query("#networkIpInfoDisableDiv").toggleClass("hide_text", true);  
                    dojo.query("#networkIpInfoEnableDiv").removeClass("hide_text", true);
                    var mainTab = dijit.byId("ipDetailsTabCointainer"); 
                    var subPortTab = dijit.byId("ipVpnUsers");                        
                    mainTab.removeChild(subPortTab);                         
                    subPortTab.destroyRecursive();                    
                    dojo.byId("ipDetailVPNEnableOption").onclick = function () {ViewNetworkIPDetails.showEnableVPnWarningMsg();};
                    
                } else if(resultData.vpnEnabled === false && resultData.isEnabledVPN === false) {
                    dojo.query("#networkIpInfoDisableDiv").toggleClass("hide_text", true);  
                    dojo.query("#networkIpInfoEnableDiv").removeClass("hide_text", true);
                    var mainTab = dijit.byId("ipDetailsTabCointainer"); 
                    var subPortTab = dijit.byId("ipVpnUsers");                        
                    mainTab.removeChild(subPortTab);                         
                    subPortTab.destroyRecursive();
                    dojo.byId("ipDetailVPNEnableOption").onclick = function () {ViewNetworkIPDetails.enableVPNShow();}                    
                }                
                if(resultData.isSourceNat === "true" || resultData.isSourceNat === true) {
                    dojo.query("#ipEnableStaticNat").toggleClass("hide_text", true);   
                    dojo.query("#ipDisableStaticNat").toggleClass("hide_text", true);   
                    dojo.query("#ipInfoDeleteIcon").toggleClass("hide_text", true);     
                    dojo.query("#sourceNatEnableDiv").toggleClass("vpn_staticnat_width", true);     
                    dojo.query("#networkIpInfoEnableDiv").removeClass("span4", true); 
                    dojo.query("#networkIpInfoEnableDiv").toggleClass("vpn_icon_staticnat", true); 
                    dojo.query("#networkIpInfoDisableDiv").toggleClass("vpn_icon_staticnat", true); 
                    dojo.query("#networkIpInfoDisableDiv").removeClass("span4", true);                     
                } else {
                    if (resultData.isStaticNat === "false" || resultData.isStaticNat === false) {
                        dojo.byId("portForwardingDiv").style.display = "block";
                        dojo.byId("loadBalancingDiv").style.display = "block";                        
                        dojo.query("#ipEnableStaticNat").removeClass("hide_text", true);                           
                        dojo.query("#ipDisableStaticNat").toggleClass("hide_text", true);                           
                        dojo.byId("enableStaticNatDiv").style.display = "none";                        
                        dojo.byId("disableStaticNatDiv").style.display = "block";
                        dojo.query("#ipInfoDeleteIcon").removeClass("hide_text", true); 
                        dojo.query("#sourceNatEnableDiv").removeClass("vpn_staticnat_width", true);  
                        dojo.query("#networkIpInfoEnableDiv").toggleClass("span4", true); 
                        dojo.query("#networkIpInfoDisableDiv").toggleClass("span4", true);
                        dojo.query("#networkIpInfoEnableDiv").removeClass("vpn_icon_staticnat", true); 
                        dojo.query("#networkIpInfoDisableDiv").removeClass("vpn_icon_staticnat", true); 
                    } else {
                        var mainTab = dijit.byId("ipDetailsTabCointainer"); 
                        var subPortTab = dijit.byId("ipPortForwarding");
                        var subLoadTab = dijit.byId("ipLoadBalancing");//tab Id which you want to show
                        mainTab.removeChild(subPortTab); 
                        mainTab.removeChild(subLoadTab); 
                        subLoadTab.destroyRecursive();
                        subPortTab.destroyRecursive();
                        dojo.query("#ipEnableStaticNat").toggleClass("hide_text", true);                         
                        dojo.query("#ipDisableStaticNat").removeClass("hide_text", true);              
                        dojo.query("#ipInfoDeleteIcon").removeClass("hide_text", true);                           
                        dojo.byId("enableStaticNatDiv").style.display = "block";
                        dojo.byId("disableStaticNatDiv").style.display = "none";                        
                        dojo.byId("staticNatEnabled").style.display = "block";
                        dojo.byId("staticNatVmName").innerHTML = resultData.vmDisplayName;
                        dojo.query("#sourceNatEnableDiv").removeClass("vpn_staticnat_width", true); 
                        dojo.query("#networkIpInfoEnableDiv").toggleClass("span4", true); 
                        dojo.query("#networkIpInfoDisableDiv").toggleClass("span4", true);
                        dojo.query("#networkIpInfoEnableDiv").removeClass("vpn_icon_staticnat", true); 
                        dojo.query("#networkIpInfoDisableDiv").removeClass("vpn_icon_staticnat", true); 
                    }
                }
            });
            ViewNetworkIPDetails.init();
            ViewNetworkIPPortForwardingDetails.init();
        });
    }
};


var ViewNetworkIP = {
    
    'init': function() {
        
        var id = dojo.byId("viewNetworkId").value;
        
        var ipOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var ipList = new ItemFileWriteStore({data: ipOptions});
        
        var ipWidget = new FilteringSelect({
            id: "staticNatVMIp",
            store: ipList,
            placeHolder: translator.common.message.selectIp
        });
        ipWidget.startup();
        ipWidget.placeAt("staticNatVMIpDiv");
        
        
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/vm/list/"
        });

        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var instanceList = new ItemFileWriteStore({data: instanceOptions});

        networkVMListRestStore.query({networkId: id}).then(function(data) {
            dojo.forEach(data, function(vm) {
                instanceList.newItem({
                    id: vm.referenceId,
                    name: vm.name
                });
            });
            var instanceWidget = new FilteringSelect({
                id: "staticNatVM",
                store: instanceList,
                placeHolder: translator.common.message.selectInstance,
                onChange: function() {
                    ViewNetworkIP.getVMIPList(id, this.value);
                }
            });
            instanceWidget.placeAt("staticNatVMList");
            instanceWidget.startup();
        });
        
    },
    enableVPNShow : function (currentIp) {        
        dojo.byId("ipListcurrentIPId").value = currentIp;        
        dojo.style(dijit.byId("networkIpListEnableVPN").closeButtonNode,"display","none");
        dijit.byId("vnpipListEnableCancelButton").set("disabled", false);
        dijit.byId("networkIpListEnableVPN").show();        
    },
    disableVPNShow : function (currentIp) {
        dojo.byId("ipListcurrentIPId").value = currentIp;    
        dojo.style(dijit.byId("networkIpListDisableVPN").closeButtonNode,"display","none");
        dijit.byId("vnpipListDisableCancelButton").set("disabled", false);
        dijit.byId("networkIpListDisableVPN").show();        
    },
    cancelVPNDialogue : function () {
        dijit.byId("networkIpListDisableVPN").hide();        
        dijit.byId("networkIpListEnableVPN").hide();        
    },    
    disableVPN : function () {
        dijit.byId("vnpipListDisableCancelButton").set("disabled", true);
        dojo.query("#networkIPListVPNDisableLoader").removeClass("hide_text", true); 
        dojo.query("#vpnipListDisableOKButton").toggleClass("hide_text", true); 
        
        var curretnIPId = dojo.byId("ipListcurrentIPId").value;        
        var disableVPNRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/disable"
        });
        disableVPNRestStore.add({
            ipAddressId : curretnIPId
        }).then(function (response) {            
            dojo.forEach(response, function (resultData) {               
                if(resultData.result === "OK") {
                    var vpnEnableJobStatus = setInterval(function(){ViewNetworkIP.disableIPJob(resultData.jobId,  resultData.vpnReferenceId,  vpnEnableJobStatus);},1000);                   
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                    registry.byId("userToaster").show();    
                    
                    dijit.byId("vnpipListDisableCancelButton").set("disabled", false);
                    dojo.query("#networkIPListVPNDisableLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnipListDisableOKButton").removeClass("hide_text", true);
                    dijit.byId("networkIpListDisableVPN").hide();
                }
            })
        })
    },
    disableIPJob : function (jobId, vpnReferenceId, vpnEnableStatus) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/remoteAccessVpn/vpnDisableJob"
        });        
            jobStore.add({
                jobId : jobId,
                vpnReferenceId : vpnReferenceId                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.jobResult === "OK") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnDisabled, "message");
                        registry.byId("userToaster").show();         
                        
                        dijit.byId("vnpipListDisableCancelButton").set("disabled", false);
                        dojo.query("#networkIPListVPNDisableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnipListDisableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpListDisableVPN").hide();                        
                        ViewNetworkIP.list(dojo.byId("viewIPNetworkId").value);
//                        dijit.byId("networkDetailContainer").set("selected", true);
//                        dijit.byId("ipDetailsTabCointainer_tablist_ipVpnUsers").setAttribute('style', 'display: none');
                    } else if(resultData.jobResult === "Pending") {
                    } else  if(resultData.jobResult === "FAILED") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vnpipListDisableCancelButton").set("disabled", false);
                        dojo.query("#networkIPListVPNDisableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnipListDisableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpListDisableVPN").hide();
                    } else {
//                        alert(resultData)
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnDisabledError,"error");
                        registry.byId("userToaster").show(); 
                        dijit.byId("vnpipListDisableCancelButton").set("disabled", false);
                        dojo.query("#networkIPListVPNDisableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnipListDisableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpListDisableVPN").hide();
                    }
                });
            });
        },
    enableVPN : function () {
        dijit.byId("vnpipListEnableCancelButton").set("disabled", true);
        dojo.query("#networkIPListVPNEnableLoader").removeClass("hide_text", true); 
        dojo.query("#vpnIpListEnableOKButton").toggleClass("hide_text", true); 
        
        var curretnIPId = dojo.byId("ipListcurrentIPId").value;        
         var enableVPNRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/enable"
        });
        enableVPNRestStore.add({
            ipAddressId : curretnIPId
        }).then(function (response) {            
            dojo.forEach(response, function (resultData) {               
                if(resultData.result === "OK") {
                        var vpnEnableJobStatus = setInterval(function(){ViewNetworkIP.enableIPJob(resultData.jobId, resultData.ipAddressId, vpnEnableJobStatus);},1000);                   
                    } else {
                        registry.byId("userToaster").setContent("Failed","error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vnpipListEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#networkIPListVPNEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnIpListEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpListEnableVPN").hide();
                        
                        
                    }
                })
            })
        },
        enableIPJob : function (jobId, ipId, vpnEnableStatus) {
            var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/remoteAccessVpn/vpnEnableJob"
        });         
            jobStore.add({
                jobId : jobId,
                ipId : ipId                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.jobResult === "OK") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbed, "message");
                        registry.byId("userToaster").show();                               
                        dijit.byId("vnpipListEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#networkIPListVPNEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnIpListEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpListEnableVPN").hide();                       
                        ViewNetworkIP.list(dojo.byId("viewIPNetworkId").value);
                    } else if(resultData.jobResult === "Pending") {
                    } else  if(resultData.jobResult === "FAILED") {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbedError,"error");
                        registry.byId("userToaster").show(); 
                        
                        dijit.byId("vnpipListEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#networkIPListVPNEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnIpListEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpListEnableVPN").hide();
                    } else {
                        clearInterval(vpnEnableStatus);
                        registry.byId("userToaster").setContent(translator.common.message.vpnEnalbedError,"error");
                        registry.byId("userToaster").show(); 
                        dijit.byId("vnpipListEnableCancelButton").set(translator.common.message.vpnEnalbedError, false);
                        dojo.query("#networkIPListVPNEnableLoader").toggleClass("hide_text", true); 
                        dojo.query("#vpnIpListEnableOKButton").removeClass("hide_text", true);
                        dijit.byId("networkIpListEnableVPN").hide();
                    }
                });
            });
        },
    showCondition : function () {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/public/termsAndCondition"
        });                  
        dojo.style(dijit.byId("networkAquireIPContent").closeButtonNode,"display","none");
        configRestStore.query().then(function(resultData) {
            dojo.byId("networkInfoContentArea").innerHTML = resultData.value;                   
        });
        dijit.byId("networkAcquireIpDialog").hide();
        dijit.byId("networkAquireIPContent").show();
    },
    cancelConditionBox : function () {
            dijit.byId("networkAquireIPContent").hide();
            dijit.byId("networkAcquireIpDialog").show();
        },
    'enableStaticNatIPShow': function(id) {

        dojo.byId('sourceNatIPId').value = id;

        dijit.byId("networkIPEnableStaticNatForm").reset();

        dijit.byId("networkIPEnableStaticNatDialog").show();
    },
    'disableStaticNatIPShow': function(id) {

        dojo.byId('sourceNatIPId').value = id;

        dijit.byId("networkIPDisableStaticNatDialog").show();
    },
    'closeDisableStaticNat': function() {
        dijit.byId("networkIPDisableStaticNatDialog").hide();
    },
    'closeEnableStaticNat': function() {
        dijit.byId("networkIPEnableStaticNatDialog").hide();
    },
    'enableStaticNat': function() {

        var vmId = dijit.byId("staticNatVM");
        var vmIpId = dijit.byId("staticNatVMIp");

        var pageNode = dojo.byId("networkIPEnableStaticNatFormPage");
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

            dijit.byId('networkIpLoader').show();
            dijit.byId("networkIPEnableStaticNatDialog").hide();

            var ipEnableStaticRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/ip/enableStaticNat"
            });

            ipEnableStaticRestStore.add({vmId: vmId.value, vmIpId: vmIpId.value, ipId: dojo.byId('sourceNatIPId').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if ((resultData.result === "OK" || resultData.result === "true"  || resultData.result === true) && (resultData.jobId)) {
                        var enableStaticNatJobStatus = setInterval(function() {
                            ViewNetworkIP.enableStaticNatJob(resultData.jobId, enableStaticNatJobStatus);
                        }, 3000);
                    } else if ((resultData.result === "OK" || resultData.result === "true"  || resultData.result === true) && (!resultData.jobId))  {
                        registry.byId("userToaster").setContent(translator.common.message.enableStaticNatSuccess, "message");
                        registry.byId("userToaster").show();
                        dijit.byId('networkIpLoader').hide();
                        ViewNetworkIP.list(dojo.byId("viewIPNetworkId").value);
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("networkIpLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('networkIpLoader').hide();

                    }
                });
            });
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
                    dijit.byId('networkIpLoader').hide();
                    ViewNetworkIP.list(dojo.byId("viewIPNetworkId").value);
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(enableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                }
            });
        });
    },
    'disableStaticNat': function() {
        dijit.byId('networkIpLoader').show();
        dijit.byId("networkIPDisableStaticNatDialog").hide();

        var ipEnableStaticRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/disableStaticNat"
        });

        ipEnableStaticRestStore.add({ipId: dojo.byId('sourceNatIPId').value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var disableStaticNatJobStatus = setInterval(function() {
                        ViewNetworkIP.disableStaticNatJob(resultData.jobId, disableStaticNatJobStatus);
                    }, 3000);
                } else if (resultData.result === "true") {
                    registry.byId("userToaster").setContent(translator.common.message.disableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                    ViewNetworkIP.list(dojo.byId("viewIPNetworkId").value);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkIpLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();

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
                    dijit.byId('networkIpLoader').hide();
                    ViewNetworkIP.list(dojo.byId("viewIPNetworkId").value);
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(disableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                }
            });
        });
    },
    'populateValues': function() {
        
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.publicIPLimit !== "-1") {                   
                    if((limitData.publicIPUsed >= limitData.publicIPLimit)) {
                        dojo.byId("nwInfoIPLimitReachedMsg").style.display = "block";
                        dojo.query("#nwInfoIPAddButton").toggleClass("hide_text", true);                                           
                    } else {
                        dojo.byId("nwInfoIPLimitReachedMsg").style.display = "none";
                        dojo.query("#nwInfoIPAddButton").removeClass("hide_text", true);    
                    }
                } else {
                    dojo.byId("nwInfoIPLimitReachedMsg").style.display = "none";
                    dojo.query("#nwInfoIPAddButton").removeClass("hide_text", true);
                }
            });
        });
        
        var id = dojo.byId("viewNetworkId").value;
        
//        var ipOptions = {
//            identifier: 'id',
//            label: 'name',
//            items: []
//        };
//
//        var ipList = new ItemFileWriteStore({data: ipOptions});
//        
//        var ipWidget = new FilteringSelect({
//            id: "staticNatVMIp",
//            store: ipList,
//            placeHolder: translator.common.message.selectIp
//        });
//        ipWidget.startup();
//        ipWidget.placeAt("staticNatVMIpDiv");

        dojo.byId("networkSummaryLink").href = "#/user/network/view/" + id;
        dojo.byId("networkEgressLink").href = "#/user/network/egress/" + id;
        dojo.byId("networkIPLink").href = "#/user/network/ip/" + id;
        dojo.byId("currentNetworkName").href = "#/user/network/view/" + id;

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("viewIPNetworkId").value = resultData.id;
                if (resultData.type === "Isolated") {
                    dojo.byId("networkIPTab").style.display = "block";
                }
                dojo.byId("currentNetworkName").innerHTML = resultData.name;
            });
            ViewNetworkIP.list(dojo.byId("viewNetworkId").value);
        });
    },
    'getVMIPList': function(networkId, vmId) {

        if (vmId == null || vmId == "" || vmId == "null") {
            return false;
        }

        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/nic/ip/list/"
        });

        var ipOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var ipList = new ItemFileWriteStore({data: ipOptions});
        var firstValue;
        networkVMListRestStore.query({networkId: networkId, vmId: vmId}).then(function(data) {

            dojo.forEach(data, function(ip, index) {
                ipList.newItem({
                    id: ip.referenceId,
                    name: ip.ip
                });
                if (index == 0) {
                    firstValue = ip.referenceId;
                }
            });

            dijit.byId("staticNatVMIp").reset();
            dijit.byId("staticNatVMIp").set("store", ipList);
            dijit.byId("staticNatVMIp").set("value", firstValue);
        });
    },
    'acquireIPShow': function() {
        dijit.byId("ipInfoAgreement").set("checked", false);
        dojo.byId("conditionExceptionMsg").style.display = "none";
        dijit.byId("networkAcquireIpDialog").show();
    },
    'closeAcquireIPShow': function() {
        dijit.byId("networkAcquireIpDialog").hide();
    },
    'releaseIPShow': function(id) {

        dojo.byId("currentIPId").value = id;

        dijit.byId("neworkReleaseIpDialog").show();
    },
    'closeReleaseIPShow': function() {
        dijit.byId("neworkReleaseIpDialog").hide();
    },
    'releaseIP': function() {

        var ipReleaseRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/release"
        });

        dijit.byId('networkIpLoader').show();
        dijit.byId('neworkReleaseIpDialog').hide();

        ipReleaseRestStore.add({ipId: dojo.byId("currentIPId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var releaseIPJobStatus = setInterval(function() {
                        ViewNetworkIP.releaseIPJob(resultData.jobId, releaseIPJobStatus);
                    }, 3000);
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();

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

                if (jobResultData.jobResult == "OK") {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.releaseIPSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                    ViewNetworkIP.list(dojo.byId("viewIPNetworkId").value);
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                }
            });
        });
    },
    'acquireIp': function() {

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/acquire"
        });
        var agrementChecked =  dijit.byId("ipInfoAgreement").checked;
//        alert(agrementChecked)
        if(agrementChecked == true) {                     
            var networkId = dojo.byId("viewIPNetworkId");
            dojo.byId("conditionExceptionMsg").style.display = "none";
            dijit.byId('networkIpLoader').show();
            dijit.byId('networkAcquireIpDialog').hide();
            networkRestStore.add({networkId: networkId.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var acquireIpJobStatus = setInterval(function() {
                            ViewNetworkIP.acquireJob(resultData.jobId, acquireIpJobStatus, resultData.networkReferenceId);
                        }, 3000);
                    } else if (resultData.result === "HASFIRSTIP") {
                        registry.byId("userToaster").setContent(translator.common.message.networkHasFirstIPMessage, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('networkIpLoader').hide();
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("networkIpLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('networkIpLoader').hide();
                    }
                });
            });
        } else {
            dojo.byId("conditionExceptionMsg").style.display = "block";
        }
    },
    'acquireJob': function(jobId, acquireIpJobStatus, networkReferenceId) {
        var acquireIpJobStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/job/"
        });

        acquireIpJobStoreStore.add({jobId: jobId, networkReferenceId: networkReferenceId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult == "OK") {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.addIpToNetworkSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                    ViewNetworkIP.list(dojo.byId("viewIPNetworkId").value);
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkIpLoader').hide();
                }
            });
        });
    },
    'list': function(id) {


        dojo.byId("userNetworkIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ips, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        if (data.isSourceNat == true || data.isSourceNat == "true") {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Source NAT]</a>";
                        } else if(data.isStaticNat == true || data.isStaticNat == "true") {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + " [Static NAT] </a>";
                        } else {
                            return "<a href='#/user/network/viewIp/" + data.id + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                        }


                    }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
                        var vpnTag = "";
                        if(data.vpnEnabled === true && data.isEnabledVPN === true) {
                            vpnTag = "<a onclick='ViewNetworkIP.disableVPNShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.vpnDisableTitle + "'><img src='images/ip_disable_icon.png'></img></a></li>"
                        } else if(data.vpnEnabled === false && data.isEnabledVPN === true) {
                            vpnTag = "";
                        } else if(data.vpnEnabled === false && data.isEnabledVPN === false) {
                            vpnTag = "<a onclick='ViewNetworkIP.enableVPNShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.vpnEnableTitle + "'><img src='images/ip_enable_icon.png'></img></a></li>"
                        }
                        if (data.isSourceNat === false || data.isSourceNat === "false") {
                            if (data.isStaticNat === false || data.isStaticNat === "false") {
                                html = "<a onclick='ViewNetworkIP.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>" +
                                        "<a onclick='ViewNetworkIP.enableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.enableStaticNat + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+vpnTag;
                            } else {
                                html = "<a onclick='ViewNetworkIP.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>" +
                                        "<a onclick='ViewNetworkIP.disableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.disableStaticNat + "'><img style='width: 17px; height: 17px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"+vpnTag;
                            } 
                        } else {
                            html = vpnTag;
                        }
                        return html;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];


        var networkIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/list/"
        });
        networkIPAddressRestStore.get(id).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userNetworkIpList").innerHTML = "";
                dojo.byId("noIpAddressMessageBox").style.display = "block";
            } else {
                dojo.byId("noIpAddressMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: {ip: resultData.ip, id: resultData.id, isSourceNat: resultData.isSourceNat, isStaticNat: resultData.isStaticNat},
                        zone: resultData.zone,
                        state: resultData.state,
                        action: {
                            id: resultData.id, 
                            ip: resultData.ip, 
                            isSourceNat: resultData.isSourceNat, 
                            isStaticNat: resultData.isStaticNat,
                            vpnEnabled: resultData.vpnEnabled,
                            isEnabledVPN: resultData.isEnabledVPN                            
                        }
                    });
                });
                dojo.byId("userNetworkIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userNetworkIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });

    }

};

var NetworkEgress = {
    'init': function() {

    },
    'populateValues': function() {
        
        var id = dojo.byId("viewNetworkId").value;

        dojo.byId("networkSummaryLink").href = "#/user/network/view/" + id;
        dojo.byId("networkEgressLink").href = "#/user/network/egress/" + id;
        dojo.byId("networkIPLink").href = "#/user/network/ip/" + id;
        dojo.byId("currentNetworkName").href = "#/user/network/view/" + id;

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("viewEgressNetworkId").value = resultData.id;
                if (resultData.type === "Isolated") {
                    dojo.byId("networkIPTab").style.display = "block";
                }
                dojo.byId("currentNetworkName").innerHTML = resultData.name;

            });
            NetworkEgress.list();
        });
    },
    'addEgress': function() {

        var cidr = dijit.byId("networkEgressCidr");
        var icmpType = dijit.byId("networkEgressIcmpType");
        var icmpCode = dijit.byId("networkEgressIcmpCode");

        var startPort = dijit.byId("networkEgressStratPort");
        var endPort = dijit.byId("networkEgressEndport");

        var protocol = dijit.byId("networkEgressProtocol");

        var networkId = dojo.byId("viewEgressNetworkId");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/egress/add"
        });

        dijit.byId('networkEgressAddButton').set('style', {display: 'none'});
        dojo.byId("egressLoader").style.display = "block";

        networkRestStore.add({protocol: protocol.value, cidr: cidr.value, icmpType: icmpType.value, icmpCode: icmpCode.value,
            startPort: startPort.value, endPort: endPort.value, networkId: networkId.value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var egressRuleJobStatus = setInterval(function() {
                        NetworkEgress.egressRuleJob(resultData.jobId, egressRuleJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkEgressAddButton').set('style', {display: 'block'});
                    dojo.byId("egressLoader").style.display = "none";
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkEgressAddButton').set('style', {display: 'block'});
                    dojo.byId("egressLoader").style.display = "none";
//                    dijit.byId('userNetworkEgressRuleAddForm').reset();

                }
            });
        });
    },
    'list': function() {

        if (dijit.byId("networkRuleGrid")) {
            dijit.byId("networkRuleGrid").destroyRecursive();
        }
        dojo.byId("userNetworkEgressRuleList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var egressData = {
            items: []
        };

        var egressDataList = new ItemFileWriteStore({data: egressData});
        var egressRuleslayout = [
            [
                {'name': 'ID', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.firewall.protocol, 'field': 'protocol', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.firewall.startPort, 'field': 'startPort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.firewall.endPort, 'field': 'endPort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {
                        var deleteButton = new dijit.form.Button({
                            "class": "delete_icon",
                            onClick: function() {
                                var grid;
                                var networkRuleRestStore = new JsonRest({
                                    target: core.getContextPath() + "/api/network/egress/"
                                });
                                grid = dijit.byId("networkRuleGrid");
                                networkRuleRestStore.remove(data).then(function(result) {
                                    if (result == "OK") {
                                        var items = grid.selection.getSelected();
                                        dojo.forEach(items, function(selectedItem) {
                                            grid.store.deleteItem(selectedItem);
                                        });
                                        registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess, "message");
                                        registry.byId("userToaster").show();
                                        setTimeout(function() {
                                            NetworkEgress.list();
                                        }, 3000);

                                    } else {
                                        registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteError, "error");
                                        registry.byId("userToaster").show();
                                    }
                                });
                            }
                        });
                        return deleteButton;
                    }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        var networkId = dojo.byId("viewNetworkId").value;

        var networkEgressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/egress/list/"
        });

        networkEgressRestStore.get(networkId).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userNetworkEgressRuleList").innerHTML = "";
                dojo.byId("noEgressRuleMessageBox").style.display = "block";
            } else {
                dojo.byId("noEgressRuleMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    egressDataList.newItem({
                        id: resultData.id,
                        cidr: resultData.cidrList,
                        protocol: resultData.protocol,
                        endPort: resultData.endPort,
                        startPort: resultData.startPort,
                        action: resultData.id
                    });
                });
                dojo.byId("userNetworkEgressRuleList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: "networkRuleGrid",
                    "class" : "span12",
                    store: egressDataList,
                    structure: egressRuleslayout,
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
                    }
                });
                dataGrid.placeAt("userNetworkEgressRuleList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    'deleteRule': function() {

    },
    'egressRuleJob': function(jobId, egressRuleJobStatus) {
        var egressJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/egress/job/"
        });

        egressJobRestStoreStore.get(jobId).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(egressRuleJobStatus);
                    NetworkEgress.list();
                    registry.byId("userToaster").setContent(translator.common.message.addEgressRuleSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('networkEgressAddButton').set('style', {display: 'block'});
                    dojo.byId("egressLoader").style.display = "none";
                    dijit.byId('userNetworkEgressRuleAddForm').reset();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(egressRuleJobStatus);
                    dijit.byId('networkEgressAddButton').set('style', {display: 'block'});
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('networkEgressAddButton').set('style', {display: 'block'});
                    dojo.byId("egressLoader").style.display = "none";
                    dijit.byId('userNetworkEgressRuleAddForm').reset();
                }
            });
        });
    },
    'changeEgressRuleLabel': function(protocol) {
        if (protocol.value === "ICMP") {
            dojo.byId("networkEgressStratPortDiv").style.display = "none";
            dojo.byId("networkEgressIcmpTypeDiv").style.display = "block";
            dojo.byId("networkEgressEndportDiv").style.display = "none";
            dojo.byId("networkEgressIcmpCodeDiv").style.display = "block";
        } else if (protocol.value === "UDP" || protocol.value === "TCP") {
            dojo.byId("networkEgressStratPortDiv").style.display = "block";
            dojo.byId("networkEgressIcmpTypeDiv").style.display = "none";
            dojo.byId("networkEgressEndportDiv").style.display = "block";
            dojo.byId("networkEgressIcmpCodeDiv").style.display = "none";
        } else {
            dojo.byId("networkEgressStratPortDiv").style.display = "none";
            dojo.byId("networkEgressIcmpTypeDiv").style.display = "none";
            dojo.byId("networkEgressEndportDiv").style.display = "none";
            dojo.byId("networkEgressIcmpCodeDiv").style.display = "none";
        }


    }
};




var ListNetwork = {
    'init': function() {

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("networkCurrencyValue").innerHTML = cur.currency;
            });
        });
        
        
        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        if (panelZone === null || panelZone === "") {
            zoneId = "All";
        } else {
            zoneId = panelZone;
        }
        
        var networkCount = new JsonRest({
            target: core.getContextPath() + "/api/network/count"
        });
        networkCount.query({zoneReferenceId: zoneId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("userTotalNetwork").innerHTML = resultData.total;
                if(resultData.Isolated) {
                    dojo.byId("userIsolatedNetwork").innerHTML = resultData.Isolated;
                } else {
                   dojo.byId("userIsolatedNetwork").innerHTML = 0; 
                }
                dojo.byId("userSharedNetwork").innerHTML = 0//resultData.Shared;
            });
        });
        
        var accountResourceLimitStore = new JsonRest({
            target: core.getContextPath()+"/api/account/getCloudResourceStat"
        });
        accountResourceLimitStore.query().then(function(data) {
            dojo.forEach(data, function(limitData) {                
                if(limitData.networkLimit !== "-1") {                  
                    if((limitData.networkUsed >= limitData.networkLimit)) {
                        dojo.byId("networkListLimitReached").style.display = "block";
                        dojo.byId("addNetworkAction").style.display = "none";                                                                                                 
                    } 
                } 
            });
        }); 
    },
    'deleteNetworkShow': function(id) {
        dojo.byId("currentNetworkId").value = id;
        dijit.byId("networkDeleteDialog").show();
    },
    'deleteNetworkClose': function() {
        dijit.byId("networkDeleteDialog").hide();
    },
    'deleteNetwork': function() {

        dijit.byId("networkLoader").show();
        dijit.byId("networkDeleteDialog").hide();

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/delete/"
        });

        networkRestStore.add({networkId: dojo.byId("currentNetworkId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var deleteNetworkJobStatus = setInterval(function() {
                        ListNetwork.deleteNetworkJob(resultData.jobId, resultData.networkId, deleteNetworkJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoader").hide();
                }
            });
        });
    },
    'deleteNetworkJob': function(jobId, networkId, deleteNetworkJobsStatus) {

        var portForwardingJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/delete/get/job/"
        });

        portForwardingJobRestStoreStore.add({jobId: jobId, networkId: networkId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deleteNetworkJobsStatus);
                    ListNetwork.populateValues();
                    registry.byId("userToaster").setContent(translator.common.message.networkDelete, "message");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoader").hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteNetworkJobsStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoader").hide();
                }
            });
        });
    },
    'populateValues': function() {
        dojo.byId("userNetworkList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        return "<a href='#/user/network/view/" + data.id + "' title='" + translator.common.view + "'>" + data.name + "</a>";
                    }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.type, 'field': 'type', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
//                 {'name': translator.common.cost, 'field': 'cost', 'width': '10%', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
//                         var html = "<span class='fog_cost'>"+ data + "</span>"
//                        return html;
//                 }},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
//                            if(currentData[1] == true || currentData[1] == "true") {
                        html = "<a href='#/user/network/edit/" + data.id + "' title='" + translator.common.edit + "'><img src='images/edit.png'></img></a>" +
//                                        "<a href='#/admin/computation/list/' class='offset1' title='"+translator.common.disable+"' onclick='ComputaionListInfo.disablePlan("+currentData[0]+")'><i class='icon-eye-close'></i></a></li>"+
                                "<a  onclick='ListNetwork.deleteNetworkShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
//                            } else if(currentData[1] == false || currentData[1] == "false") {
//                                 html = "<a href='#/admin/computation/viewComputation/"+currentData[0]+"' title='"+translator.common.view+"'><i class='icon-edit'></i></a>" +                                       
//                                        "<a href='#/admin/computation/list/' class='offset1' title='"+translator.common.enable+"' onclick='ComputaionListInfo.enablePlan("+currentData[0]+")'><i class='icon-eye-open'></i></a></li>" + 
//                                        "<a href='#/admin/computation/deleteComputation/"+currentData[0]+"' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
//                            }
                        return html;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var panelZone = dojo.byId("selectedANZoneID").value;
        var zoneId;
        if (panelZone === null || panelZone === "") {
            zoneId = "All";
        } else {
            zoneId = panelZone;
        }
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network"
        });
        networkRestStore.query({zoneReferenceId: zoneId}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userNetworkList").innerHTML = "";
                dojo.byId("noOfferMessageBox").style.display = "block";
                dojo.byId("userTotalNetwork").innerHTML = 0;
                dojo.byId("userIsolatedNetwork").innerHTML = 0;
                dojo.byId("userSharedNetwork").innerHTML = 0;
            } else {
                dojo.byId("noOfferMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: {id: resultData.id, name: resultData.name},
                        zone: resultData.zone,
                        cidr: resultData.cidr,
                        state: resultData.state,
                        gateway: resultData.gateway,
                        type: resultData.type,
//                            cost:  resultData.cost,
                        action: {id: resultData.id}
                    });
                });
                dojo.byId("userNetworkList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userNetworkList");
                dataGrid.startup();
                dataGrid.update();

//                    var coumputingOfferCountRestStore = new JsonRest({                        
//                        target: core.getContextPath()+"/api/computingOffer/count"
//                    }); 
//                    coumputingOfferCountRestStore.query().then(function(data) {
//                        dojo.forEach(data, function(resultData) {
//                            dojo.byId("userTotalNetwork").innerHTML = 0;
//                            dojo.byId("userEnabledTotalNetwork").innerHTML = 0;
//                            dojo.byId("userDisabledTotalNetwork").innerHTML = 0;                                    
//                        });
//                    });
            }
        });
    }
};

var ViewNetwork = {
    
    'showIpTab': function() {
        
        setTimeout(function () {
            var mainTab = dijit.byId("networkTab"); //Tr
            var subIpTab = dijit.byId("networkIpAddressTab"); //tab Id which you want to show
            mainTab.selectChild(subIpTab);   
        },800);
    },
    'init': function() {

    },
    'deleteNetworkShow': function() {
        dijit.byId("networkDeleteDialog").show();
    },
    'deleteNetworkClose': function() {
        dijit.byId("networkDeleteDialog").hide();
    },
    'deleteNetwork': function() {

        dijit.byId("networkLoader").show();
        dijit.byId("networkDeleteDialog").hide();

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/delete/"
        });

        networkRestStore.add({networkId: dojo.byId("viewNetworkId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var deleteNetworkJobStatus = setInterval(function() {
                        ViewNetwork.deleteNetworkJob(resultData.jobId, resultData.networkId, deleteNetworkJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoader").hide();
                }
            });
        });
    },
    'deleteNetworkJob': function(jobId, networkId, deleteNetworkJobsStatus) {

        var portForwardingJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/network/delete/get/job/"
        });

        portForwardingJobRestStoreStore.add({jobId: jobId, networkId: networkId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deleteNetworkJobsStatus);
                   core.router.go("#/user/network/");
                    registry.byId("userToaster").setContent(translator.common.message.networkDelete, "message");
                    registry.byId("userToaster").show();
                    dijit.byId("networkLoader").hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteNetworkJobsStatus);
                    if(jobResultData.jobResult === "FAILED") {
                        if(jobResultData.message) {
                            registry.byId("userToaster").setContent(jobResultData.message, "error");
                            registry.byId("userToaster").show();
                        } else {
                            registry.byId("userToaster").setContent(jobResultData.message, "error");
                            registry.byId("userToaster").show();
                        }
                        
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                    }                    
                    dijit.byId("networkLoader").hide();
                }
            });
        });
    },
    'populateValues': function(id) {
        
        dojo.byId("networkSummaryLink").href = "#/user/network/view/" + id;
        dojo.byId("networkEgressLink").href = "#/user/network/egress/" + id;
        dojo.byId("networkIPLink").href = "#/user/network/ip/" + id;
        dojo.byId("currentNetworkName").href = "#/user/network/view/" + id;
        dojo.byId("networkEdit").href = "#/user/network/edit/" + id;


        var name = dojo.byId("viewNetworkName");
        
        var nameTop = dojo.byId("viewNetworkNameTop");
         var zoneTop = dojo.byId("viewNetworkZoneNameTop");
        
        var desc = dojo.byId("viewNetworkDesc");
        var zone = dojo.byId("viewNetworkZoneName");
        var networkOffer = dojo.byId("viewNetworkOfferName");
        var networkDomain = dojo.byId("viewNetworkDomain");
        var referenceId = dojo.byId("viewNetworkReferenceId");
        var netmask = dojo.byId("viewNetworkNetmask");

        var gateway = dojo.byId("viewNetworkGateway");
        var cidr = dojo.byId("viewNetworkCidr");

        var type = dojo.byId("viewNetworkType");
        var state = dojo.byId("viewNetworkState");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("viewNetworkId").value = resultData.id;
                networkOffer.innerHTML = resultData.networkOffer;
                nameTop.innerHTML = resultData.name;
                zoneTop.innerHTML = resultData.zone;
                name.innerHTML = resultData.name;
                desc.innerHTML = resultData.description;
                zone.innerHTML = resultData.zone;
                cidr.innerHTML = resultData.cidr;
                gateway.innerHTML = resultData.gateway;
                type.innerHTML = resultData.type;
                networkDomain.innerHTML = resultData.networkDomain;
                referenceId.innerHTML = resultData.referenceId;
                netmask.innerHTML = resultData.netmask;
                
                
                
                if (resultData.type === "Isolated") {
                    dojo.byId("networkIPTab").style.display = "block";
                }
                dojo.byId("currentNetworkName").innerHTML = resultData.name;
                state.innerHTML = resultData.state;

            });
            ViewNetworkIP.init();
        });
        
        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var instanceList = new ItemFileWriteStore({data: instanceOptions});
        
        var vmDatalayout = [
            [   
                {'field': 'id', 'hidden': 'true'},             
                {'name': translator.common.name, 'field': 'name', 'width': '300px', datatype: "string", "formatter" : function (data) {
                        var html = "";
                        if(data.hasVPC === true) {
                            html = "<span>" + data.name + "                 |               " + data.vpc + "        (          VPC        )           " + "</span>"
                        } else {
                            html = "<span>" + data.name + "</span>"
                        }
                        return html;
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '300px', datatype: "string"},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', datatype: "string", "formatter" : function (data) {
                        var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif' class='vm_stat_image' alt='"+translator.common.loader.loading+"'/>" });
                        var status;                         
                        if((data !== "in progress") && (data !== "starting")) {
                            if(data === "Running") {
                                status = "<div class='stat_running overflowLabel'>"+translator.common.instance.status.running+"</div>";
                            } else if(data === "Stopped") {
                                status = "<div class='stat_stopped overflowLabel'>"+translator.common.instance.status.stopped+"</div>";
                            } else if(data === "Destroyed") {
                                status = "<div class='stat_destroyed overflowLabel'>"+translator.common.instance.status.destroyed+"</div>";
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
                        } else if(data == "in progress") {
                            status = imageDiv.innerHTML;
                        }
                        return status;                        
                    }
                }                             
            ]
        ];
        
        var networkVMListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/vm/list/"
        });

        networkVMListRestStore.query({networkId: id}).then(function(data) {            
            if(data.length === 0 || data === "" || data === "undefined") {
                dojo.byId("noInstanceNetwork").style.display = "block";
            } else {
                dojo.forEach(data, function(vm) {
                    instanceList.newItem({
                        id: vm.referenceId, 
                        name: {
                            name : vm.name, 
                            hasVPC: vm.hasVPC,
                            vpc: vm.vpc                            
                        }, 
                        zone: vm.zone, state: vm.state
                    });
                });
                var vmGrid = new dojox.grid.EnhancedGrid({
                    id: "networkAttachedVMList",
                    "class" : "span12",
                    store: instanceList,
                    structure: vmDatalayout,
                    autoHeight: true,                     
                    plugins: core.getGridConfig().plugins 
                });
                vmGrid.placeAt("userInstanceNetworkList");
                vmGrid.startup();
            }
        });
    }
};

var EditNetwork = {
    
    'init': function() {

    },
    'populateValues': function(id) {

        dojo.byId("currentNetworkName").href = "#/user/network/view/" + id;

        var name = dijit.byId("editNetworkName");
        var desc = dijit.byId("editNetworkDescription");
        var zone = dojo.byId("networkZoneName");
        var networkOffer = dojo.byId("networkOfferName");
        var gateway = dojo.byId("networkGateway");
        var cidr = dojo.byId("networkCidr");
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("networkId").value = resultData.id;
                networkOffer.innerHTML = resultData.networkOffer;
                name.set("value", resultData.name);
                desc.set("value", resultData.description);
                zone.innerHTML = resultData.zone;
                cidr.innerHTML = resultData.cidr;
                gateway.innerHTML = resultData.gateway;
                dojo.byId("currentNetworkName").innerHTML = resultData.name;

            });
        });
    },
    'cancel': function() {
        core.router.go("#/user/network");
    },
    'edit': function() {

        var networkId = dojo.byId("networkId");
        var name = dijit.byId("editNetworkName");
        var desc = dijit.byId("editNetworkDescription");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });

        dojo.byId("editNetworkLoader").style.display = "block";
        dojo.byId("editNetworkButtonDiv").style.display = "none";

        networkRestStore.put({id: networkId.value, name: name.value, desc: desc.value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                    registry.byId("userToaster").setContent(translator.common.message.networkUpdated, "message");
                    registry.byId("userToaster").show();
                    core.router.go("#/user/network");
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dojo.byId("editNetworkLoader").style.display = "none";
                    dojo.byId("editNetworkButtonDiv").style.display = "block";
                }
            });
        });

    }
};

var AddNetwork = {
    
    'init': function(currentId) {

        var zoneListStore = new JsonRest({
            target: core.getContextPath() + "/api/zone"
        });

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var zoneFileStoreList = new ItemFileWriteStore({data: zoneOptions});

        var zoneWidget = new Select({
            id: "networkZone",
            name: "networkZone",
            store: zoneFileStoreList,
            onChange: function() {
                AddNetwork.loadNetworkOffer(this);
            }
        }, "networkZoneList");

        var firstZone = "";
        zoneListStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if (resultData.networkType == "Advanced") {
                    if(currentId) { 
                        if (resultData.id == currentId) {
                            firstZone = resultData.referenceZoneId;
                        } else {
                            if (firstZone != "") {
                                firstZone = resultData.referenceZoneId;
                            }
                        }
                    }                    
                zoneFileStoreList.newItem({id: resultData.referenceZoneId, name: resultData.aliasName});
            }
        });
        dijit.byId("networkZone").set("store", zoneList);
        dijit.byId("networkZone").set("value", firstZone);        
    });

    var panelZone = dojo.byId("selectedANZoneID").value;

    if (panelZone !== "" && panelZone === "All") {

    } else if (panelZone !== "") {

        var zoneItems = {
            identifier: "id",
            label: "name",
            items: []
        };
        var zoneList = new ItemFileWriteStore({data: zoneItems});

        zoneListStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.referenceZoneId === panelZone) {
                    zoneList.newItem({id: resultData.referenceZoneId, name: resultData.aliasName});
                }
            });
            dijit.byId("networkZone").set("store", zoneList);
            dijit.byId("networkZone").set("value", panelZone);
            zoneWidget.startup();
            zoneWidget.setAttribute("disabled", true);
        });
    }
    },
    'add': function() {

        var name = dijit.byId("networkName").value;
        var desc = dijit.byId("networkDescription").value;
        var zone = dijit.byId("networkZone").value;
        var networkOffer = dijit.byId("networkOffer").value;


        var pageNode = dojo.byId("userNetworkPage");
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

            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network"
            });

            dojo.byId("createNetworkLoader").style.display = "block";
            dojo.byId("createNetworkButtonDiv").style.display = "none";

            networkRestStore.add({zone: zone, name: name, desc: desc, networkOffer: networkOffer}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.networkCreate, "message");
                        registry.byId("userToaster").show();
                        core.router.go("#/user/network");
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dojo.byId("createNetworkLoader").style.display = "none";
                        dojo.byId("createNetworkButtonDiv").style.display = "block";
                    }
                });
            });
        }
    },
    'populateValues': function() {

    },
    'loadNetworkOffer': function(data) {

        dojo.byId("networkOfferList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var networkOfferOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var networkFileStoreList = new ItemFileWriteStore({data: networkOfferOptions});

        var networkOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer"
        });

        networkOfferRestStore.query({zoneReferenceId: data.value}).then(function(data) {
            if (!data || data.length == 0) {
                networkFileStoreList.newItem({id: "", name: translator.common.noNetworkOffer});
            } else {
                dojo.forEach(data, function(resultData) {
                    networkFileStoreList.newItem({id: resultData.referenceId, name: resultData.name});
                });
            }
            var networkOfferWidget = new Select({
                id: "networkOffer",
                name: "networkOffer",
                store: networkFileStoreList
            }, "networkOfferList");
        });

    }
};
var ViewVPNUserInfo = {
    deleteVPNUserConfirmation : function () {
        dojo.style(dijit.byId("networkIpDeleteVPNUserDialogue").closeButtonNode,"display","none");
        dijit.byId("networkIpDeleteVPNUserDialogue").show();
    },
    populateValues : function () {      
        var currentIP = dojo.byId("currentIPId").value;
        dojo.byId("viewVPnUserGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vpnInfoRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/byPublicIp"
        });
        vpnInfoRestStore.query({ipAddressId: currentIP}).then(function (data) {
            dojo.byId("vpnUserInfoIP").innerHTML = data[0].publicIP;
            dojo.byId("vpnIPSpecKey").innerHTML = data[0].ipsecPsk;            
        });
        if(dijit.byId("vpnUserGrid")) {
            dijit.byId("vpnUserGrid").destroyRecursive();                                
        }
        var vpnUserOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var vpnUserList = new ItemFileWriteStore({data: vpnUserOptions});
        var vpnUserListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/listVPNUsers"
        });
        var vpnUserLayout = [
            [
                {'name': 'ID', 'field': 'id',  'hidden': 'true'},
                {'name': translator.user.userName, 'field': 'userName', 'width': '400px', datatype:"string",  autoComplete: true},     
                {'name': translator.common.state, 'field': 'stat', 'width': '400px', datatype:"string",  autoComplete: true},                      
                {'name': translator.common.action, 'field': 'action','width': '100%',
                    'formatter': function(data) {
                        var htmlDiv = "<a onclick= ViewVPNUserInfo.deleteVPNUserConfirmation();><img src='images/vm_delete_icon.png' title="+translator.common.deleteData+"></a>"                         
                        return htmlDiv;
                    }
                }
            ]
        ];
        vpnUserListRestStore.query().then(function(data) {
            dojo.forEach(data, function (el) {              
                vpnUserList.newItem({id: el.referenceId, userName: el.userName, stat:el.state, action: el.referenceId});
            });
            var vpnUserGridWidget = new EnhancedGrid({
                id: "vpnUserGrid",
                "class" : "span12",
                store: vpnUserList,
                structure: vpnUserLayout,
                autoHeight: true,
                plugins: core.getGridConfig().plugins            
            });
            dojo.byId("viewVPnUserGrid").innerHTML = "";
            vpnUserGridWidget.placeAt("viewVPnUserGrid");
            vpnUserGridWidget.startup(); 
        });         
    },
    deleteUser : function () {
        var addVPNUserRestStore = new JsonRest({
            target: core.getContextPath() + "/api/remoteAccessVpn/deleteVpnUser"
        });
        var vpnUserGrid = dijit.byId("vpnUserGrid");
        var vpnUserName;
        var  dataItem = vpnUserGrid.selection.getSelected();            
        dojo.forEach(dataItem, function(item) {
            dojo.forEach(item.userName, function (el) {
                vpnUserName = el;
            })
        });          
        dojo.query("#vpnUserDeleteLoader").removeClass("hide_text", true); 
        dojo.query("#vpnDeleteOKButton").toggleClass("hide_text", true);  
        dijit.byId("vnpUserDeleteCancelButton").set("disabled", true);
        
        addVPNUserRestStore.add({
            userName:vpnUserName
        }).then(function (response) {            
            dojo.forEach(response, function (resultData) {               
                if(resultData.result === "OK") {
                    var vpnDeleteUserJobStat = setInterval(function(){ViewVPNUserInfo.deleteVPNUserJob(resultData.jobId, vpnDeleteUserJobStat, resultData.vpnUserName);},1000);                   
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.vpndeleteUserError,"error");
                    registry.byId("userToaster").show();                        
                    dojo.query("#vpnUserDeleteLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnDeleteOKButton").removeClass("hide_text", true);  
                    dijit.byId("vnpUserDeleteCancelButton").set("disabled", false);
                    dijit.byId("networkIpDeleteVPNUserDialogue").hide();
                }
            })
        });
    },
    deleteVPNUserJob : function (jobId, vpnDeleteUserJobStat, vpnUserName) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/remoteAccessVpn/deleteVpnUser/job"
        });         
        jobStore.add({
            jobId : jobId,
            vpnUserName: vpnUserName
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpnDeleteUserJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpndeleteUserSuccess, "message");
                    registry.byId("userToaster").show();      
                    dojo.query("#vpnUserDeleteLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnDeleteOKButton").removeClass("hide_text", true);  
                    dijit.byId("vnpUserDeleteCancelButton").set("disabled", false);
                     dijit.byId("networkIpDeleteVPNUserDialogue").hide();
                     ViewVPNUserInfo.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpnDeleteUserJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpndeleteUserError,"error");
                    registry.byId("userToaster").show();       
                    dojo.query("#vpnUserDeleteLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnDeleteOKButton").removeClass("hide_text", true);  
                    dijit.byId("vnpUserDeleteCancelButton").set("disabled", false);
                     dijit.byId("networkIpDeleteVPNUserDialogue").hide();
                } else {
                    clearInterval(vpnDeleteUserJobStat);
                    dojo.query("#vpnUserDeleteLoader").toggleClass("hide_text", true); 
                    dojo.query("#vpnDeleteOKButton").removeClass("hide_text", true);  
                    dijit.byId("vnpUserDeleteCancelButton").set("disabled", false);
                    dijit.byId("networkIpDeleteVPNUserDialogue").hide();
                }
            });
        });
    },
    cancelDeleteUser : function () {
        dijit.byId("networkIpDeleteVPNUserDialogue").hide();
    },
    addVPNUser : function () {   
        var pageNode = dojo.byId("vpnUserForm");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        var stat = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                stat = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        
        if(stat == true) {
            var vpnUserValidateRestStore = new JsonRest({
                target: core.getContextPath() + "/api/remoteAccessVpn/vpnUsers/validate"
            });
            var userName = dijit.byId("vpnUserName").value;            
            var addVPNUserRestStore = new JsonRest({
                target: core.getContextPath() + "/api/remoteAccessVpn/addVPNUser"
            });
            vpnUserValidateRestStore.query({vpnUserName: userName}).then(function(data) {
                if(data[0].isUser === true) {
                    dojo.byId("vpnUserRequireMsg").style.display = "block"; 
                } else {
                    dijit.byId("networkIPVPNUserDialogLoader").show();
                    dojo.byId("vpnUserRequireMsg").style.display = "none"; 
                    var password = dijit.byId("newVPNUserPassword").value;            
                    addVPNUserRestStore.add({
                        userName : userName,
                        password : password
                    }).then(function (response) {            
                        dojo.forEach(response, function (resultData) {                                   
                            if(resultData.result === "OK") {
                                var vpnaddUserJobStat = setInterval(function(){ViewVPNUserInfo.addVPNUserJob(resultData.jobId, vpnaddUserJobStat, resultData.vpnUserId);},1000);                   
                            } else {
                                registry.byId("userToaster").setContent(translator.common.message.vpnUserFailed,"error");
                                registry.byId("userToaster").show();                        
                                dijit.byId("networkIPVPNUserDialogLoader").hide()
                                dijit.byId("vpnUserForm").reset();
                            }
                        })
                    })
                }            
            })            
        }        
    },
    addVPNUserJob :  function (jobId, vpnaddUserJobStat, vpnUserId) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/remoteAccessVpn/addVPNUser/job"
        });         
        jobStore.add({
            jobId : jobId,
            vpnUserId: vpnUserId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpnaddUserJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpnUserSucess, "message");
                    registry.byId("userToaster").show();     
                    dijit.byId("networkIpDisableVPN").hide();
                    dijit.byId("vpnUserForm").reset();
                    dijit.byId("networkIPVPNUserDialogLoader").hide();
                    ViewVPNUserInfo.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpnaddUserJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpnUserFailed,"error");
                    registry.byId("userToaster").show();     
                    dijit.byId("networkIpDisableVPN").hide();
                    dijit.byId("vpnUserForm").reset();
                    dijit.byId("networkIPVPNUserDialogLoader").hide();
                } else {
                    clearInterval(vpnaddUserJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpnUserFailed,"error");
                    registry.byId("userToaster").show();     
                    dijit.byId("networkIpDisableVPN").hide();
                    dijit.byId("vpnUserForm").reset();
                    dijit.byId("networkIPVPNUserDialogLoader").hide();
                }
            });
        });
    }
}

window.ListNetwork = ListNetwork;
window.AddNetwork = AddNetwork;
window.EditNetwork = EditNetwork;
window.NetworkEgress = NetworkEgress;
window.ViewNetworkIP = ViewNetworkIP;
window.ViewNetworkIPLoadBalancingDetails = ViewNetworkIPLoadBalancingDetails;
window.ViewVPNUserInfo = ViewVPNUserInfo;
