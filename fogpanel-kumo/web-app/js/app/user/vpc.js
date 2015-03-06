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
        name: "vpc",
        module: "user",
        filePath: "/js/app/user/vpc.js",
        layout: {
            name: "vpc",
            containerId: "content"
        },	
        scaffold: false
    },
    {
        "index": action(function() {
            core.ui.loadTemplate("vpcDashboard", core.ui.getContentId());   
            if(dijit.byId("serviceTooltipDialogue")) {
                dijit.byId("serviceTooltipDialogue").destroyRecursive();
            }
//            VPCMenuInfo.populateValues();
            VPCMenuInfo.init();
            
        }),
        "help": action(function() {            
            if(dijit.byId("vpcHelpTab")) {
                dijit.byId("vpcHelpTab").destroyRecursive();
            }   
            core.ui.loadTemplate("vpcHelpMenu", core.ui.getContentId());   
        }),
        "topology" : action(function () {            
            if(dijit.byId("vpcTopologyAddTierDialog")) {
                dijit.byId("vpcTopologyAddTierDialog").destroyRecursive();
            }
            if(dijit.byId("topoVpcLoader")) {
                dijit.byId("topoVpcLoader").destroyRecursive();
            }                        
            core.ui.loadTemplate("topology", core.ui.getContentId());   
            NetworkTopology.populateValues();
        }),
        "dashboard" : action(function () {
            if(dijit.byId("serviceTooltipDialogue")) {
                dijit.byId("serviceTooltipDialogue").destroyRecursive();
            }
            var vpcRestStore = new JsonRest({            
                target: core.getContextPath()+"/api/vpc/"
            });
            vpcRestStore.query().then(function (vpcList) {
                if(vpcList.length === 0 || vpcList === "" || vpcList === undefined) {
                    if(dijit.byId("vpcHelpTab")) {
                        dijit.byId("vpcHelpTab").destroyRecursive();
                    }   
                    core.ui.loadTemplate("vpcHelpMenu", core.ui.getContentId()); 
                    VPCMenuInfo.populateValues("All"); 
                } else {
                    core.ui.loadTemplate("vpcDashboard", core.ui.getContentId()); 
                    VPCMenuInfo.populateValues("All");             
                    VPCMenuInfo.init();
                }
            });            
        }),
        "addVpc": action(function(id) {
            var userNetworkZoneForm = dijit.byId("userCreateVpcForm");
            if (userNetworkZoneForm) {
                userNetworkZoneForm.destroyRecursive();
            }
            if (dijit.byId("vpcLoader")) {
                dijit.byId("vpcLoader").destroyRecursive();
            }
            core.ui.loadTemplate("createVpc", core.ui.getContentId());         
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            AddVpc.init(id);
        }),
        "list": action(function() {
            if (dijit.byId("vpcLoader")) {
                dijit.byId("vpcLoader").destroyRecursive();
            }
            if (dijit.byId("vpcDeleteDialog")) {
                dijit.byId("vpcDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpcRestartDialog")) {
                dijit.byId("vpcRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpcListEditDialog")) {
                dijit.byId("vpcListEditDialog").destroyRecursive();
            }
            core.ui.loadTemplate("vpcList", core.ui.getContentId());
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            ListVpc.populateValues();
        }),
        "vpcContainer" : action(function(id) {
            core.ui.loadTemplate("vpcContainer", core.ui.getContentId());  
            VPCMenuInfo.populateCounts();
        }),
        "viewVPCPrivateGateway": action(function(id) {
            if (dijit.byId("vpcPrivateGatewayLoader")) {
                dijit.byId("vpcPrivateGatewayLoader").destroyRecursive();
            }
            if (dijit.byId("staticRoutesTab")) {
                dijit.byId("staticRoutesTab").destroyRecursive();
            }
            if (dijit.byId("privateGatewayContainer")) {
                dijit.byId("privateGatewayContainer").destroyRecursive();
            }
            if (dijit.byId("staticRouteAddForm")) {
                dijit.byId("staticRouteAddForm").destroyRecursive();
            }
            if (dijit.byId("deleteStaticRouteDialog")) {
                dijit.byId("deleteStaticRouteDialog").destroyRecursive();
            }
            core.ui.loadTemplate("viewPrivateGateway", core.ui.getContentId());
            VPCPrivateGateway.populateDetails(id);
            
        }),
        "showVpc" : action(function (data) {
            if (dijit.byId("vpcLoader")) {
                dijit.byId("vpcLoader").destroyRecursive();
            }
            if (dijit.byId("ipVpcTabCointainer")) {
                dijit.byId("ipVpcTabCointainer").destroyRecursive();
            }
            if (dijit.byId("vpcDetailContainer")) {
                dijit.byId("vpcDetailContainer").destroyRecursive();
            }
            if (dijit.byId("vpcEditDialog")) {
                dijit.byId("vpcEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcTierContainer")) {
                dijit.byId("vpcTierContainer").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierDialog")) {
                dijit.byId("vpcAddTierDialog").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierPageForm")) {
                dijit.byId("vpcAddTierPageForm").destroyRecursive();
            }
            if (dijit.byId("tierDeleteDialog")) {
                dijit.byId("tierDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("tierRestartDialog")) {
                dijit.byId("tierRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpcReleaseIpDialog")) {
                dijit.byId("vpcReleaseIpDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIpAddressTab")) {
                dijit.byId("vpcIpAddressTab").destroyRecursive();
            }
            if (dijit.byId("vpcAcquireIpDialog")) {
                dijit.byId("vpcAcquireIpDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpcInfoDeleteDialog")) {
                dijit.byId("vpcInfoDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoListEditDialog")) {
                dijit.byId("vpcInfoListEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoRestartDialog")) {
                dijit.byId("vpcInfoRestartDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpckAquireIPContent")) {
                dijit.byId("vpckAquireIPContent").destroyRecursive();
            }
            if (dijit.byId("ipVpcInfoAgreement")) {
                dijit.byId("ipVpcInfoAgreement").destroyRecursive();
            }
            if (dijit.byId("vpcIpEnableStaticNatDialog")) {
                dijit.byId("vpcIpEnableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIPDisableStaticNatDialog")) {
                dijit.byId("vpcIPDisableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcEditTierDialog")) {
                dijit.byId("vpcEditTierDialog").destroyRecursive();
            }
            if (dijit.byId("tierEditConformDialog")) {
                dijit.byId("tierEditConformDialog").destroyRecursive();
            }
            if (dijit.byId("tierReplaceACLDialog")) {
                dijit.byId("tierReplaceACLDialog").destroyRecursive();
            }
            if (dijit.byId("userViewIPForm")) {
                dijit.byId("userViewIPForm").destroyRecursive();
            }
            if (dijit.byId("s2svpnDeleteDialog")) {
                dijit.byId("s2svpnDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createS2SGatewayDialog")) {
                dijit.byId("createS2SGatewayDialog").destroyRecursive();
            }
            if (dijit.byId("addVPNConnectionForm")) {
                dijit.byId("addVPNConnectionForm").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionDeleteDialog")) {
                dijit.byId("vpnConnectionDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionRestartDialog")) {
                dijit.byId("vpnConnectionRestartDialog").destroyRecursive();
            }
            if (dijit.byId("createVPNConnecionPagePage")) {
                dijit.byId("createVPNConnecionPagePage").destroyRecursive();
            }
            if (dijit.byId("vpcPrivateGatewayTab")) {
                dijit.byId("vpcPrivateGatewayTab").destroyRecursive();
            }            
            if (dijit.byId("vpcNetworkACLTab")) {
                dijit.byId("vpcNetworkACLTab").destroyRecursive();
            }
            if (dijit.byId("createAclConnectionPage")) {
                dijit.byId("createAclConnectionPage").destroyRecursive();
            }
            
            if (dijit.byId("vpcNetworkAclDeleteDialog")) {
                dijit.byId("vpcNetworkAclDeleteDialog").destroyRecursive();
            }                        
            if (dijit.byId("vpcVpnConnectionTab")) {
                dijit.byId("vpcVpnConnectionTab").destroyRecursive();
            }
            if (dijit.byId("vpcs2sVpnTab")) {
                dijit.byId("vpcs2sVpnTab").destroyRecursive();
            }
            var currentItem = data.split(",");
            core.ui.loadTemplate("viewVpc", core.ui.getContentId());
            ViewVpc.init(currentItem[0]);
            setTimeout(function () {
                var currentItem = data.split(",");
                var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr
                var subIpTab = dijit.byId(currentItem[1]); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },800);
        }),
        "view": action(function(id) {
            if (dijit.byId("vpcLoader")) {
                dijit.byId("vpcLoader").destroyRecursive();
            }
            if (dijit.byId("ipVpcTabCointainer")) {
                dijit.byId("ipVpcTabCointainer").destroyRecursive();
            }
            if (dijit.byId("vpcDetailContainer")) {
                dijit.byId("vpcDetailContainer").destroyRecursive();
            }
            if (dijit.byId("vpcEditDialog")) {
                dijit.byId("vpcEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcTierContainer")) {
                dijit.byId("vpcTierContainer").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierDialog")) {
                dijit.byId("vpcAddTierDialog").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierPageForm")) {
                dijit.byId("vpcAddTierPageForm").destroyRecursive();
            }
            if (dijit.byId("tierDeleteDialog")) {
                dijit.byId("tierDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("tierRestartDialog")) {
                dijit.byId("tierRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpcReleaseIpDialog")) {
                dijit.byId("vpcReleaseIpDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIpAddressTab")) {
                dijit.byId("vpcIpAddressTab").destroyRecursive();
            }
            if (dijit.byId("vpcAcquireIpDialog")) {
                dijit.byId("vpcAcquireIpDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpcInfoDeleteDialog")) {
                dijit.byId("vpcInfoDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoListEditDialog")) {
                dijit.byId("vpcInfoListEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoRestartDialog")) {
                dijit.byId("vpcInfoRestartDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpckAquireIPContent")) {
                dijit.byId("vpckAquireIPContent").destroyRecursive();
            }
            if (dijit.byId("ipVpcInfoAgreement")) {
                dijit.byId("ipVpcInfoAgreement").destroyRecursive();
            }
            if (dijit.byId("vpcIpEnableStaticNatDialog")) {
                dijit.byId("vpcIpEnableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIPDisableStaticNatDialog")) {
                dijit.byId("vpcIPDisableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcEditTierDialog")) {
                dijit.byId("vpcEditTierDialog").destroyRecursive();
            }
            if (dijit.byId("tierEditConformDialog")) {
                dijit.byId("tierEditConformDialog").destroyRecursive();
            }
            if (dijit.byId("tierReplaceACLDialog")) {
                dijit.byId("tierReplaceACLDialog").destroyRecursive();
            }
            if (dijit.byId("userViewIPForm")) {
                dijit.byId("userViewIPForm").destroyRecursive();
            }
            if (dijit.byId("s2svpnDeleteDialog")) {
                dijit.byId("s2svpnDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createS2SGatewayDialog")) {
                dijit.byId("createS2SGatewayDialog").destroyRecursive();
            }
            if (dijit.byId("addVPNConnectionForm")) {
                dijit.byId("addVPNConnectionForm").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionDeleteDialog")) {
                dijit.byId("vpnConnectionDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionRestartDialog")) {
                dijit.byId("vpnConnectionRestartDialog").destroyRecursive();
            }
            if (dijit.byId("createVPNConnecionPagePage")) {
                dijit.byId("createVPNConnecionPagePage").destroyRecursive();
            }
            if (dijit.byId("vpcPrivateGatewayTab")) {
                dijit.byId("vpcPrivateGatewayTab").destroyRecursive();
            }            
            if (dijit.byId("vpcNetworkACLTab")) {
                dijit.byId("vpcNetworkACLTab").destroyRecursive();
            }
            if (dijit.byId("createAclConnectionPage")) {
                dijit.byId("createAclConnectionPage").destroyRecursive();
            }
            
            if (dijit.byId("vpcNetworkAclDeleteDialog")) {
                dijit.byId("vpcNetworkAclDeleteDialog").destroyRecursive();
            }                        
            if (dijit.byId("vpcVpnConnectionTab")) {
                dijit.byId("vpcVpnConnectionTab").destroyRecursive();
            }
            if (dijit.byId("vpcs2sVpnTab")) {
                dijit.byId("vpcs2sVpnTab").destroyRecursive();
            }
            core.ui.loadTemplate("viewVpc", core.ui.getContentId());
            ViewVpc.init(id);
        }),
        "ipList": action(function(id) {
            if (dijit.byId("vpcLoader")) {
                dijit.byId("vpcLoader").destroyRecursive();
            }
            if (dijit.byId("ipVpcTabCointainer")) {
                dijit.byId("ipVpcTabCointainer").destroyRecursive();
            }
            if (dijit.byId("vpcDetailContainer")) {
                dijit.byId("vpcDetailContainer").destroyRecursive();
            }
            if (dijit.byId("vpcEditDialog")) {
                dijit.byId("vpcEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcTierContainer")) {
                dijit.byId("vpcTierContainer").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierDialog")) {
                dijit.byId("vpcAddTierDialog").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierPageForm")) {
                dijit.byId("vpcAddTierPageForm").destroyRecursive();
            }
            if (dijit.byId("tierDeleteDialog")) {
                dijit.byId("tierDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("tierRestartDialog")) {
                dijit.byId("tierRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpcReleaseIpDialog")) {
                dijit.byId("vpcReleaseIpDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIpAddressTab")) {
                dijit.byId("vpcIpAddressTab").destroyRecursive();
            }
            if (dijit.byId("vpcAcquireIpDialog")) {
                dijit.byId("vpcAcquireIpDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpcInfoDeleteDialog")) {
                dijit.byId("vpcInfoDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoListEditDialog")) {
                dijit.byId("vpcInfoListEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoRestartDialog")) {
                dijit.byId("vpcInfoRestartDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpckAquireIPContent")) {
                dijit.byId("vpckAquireIPContent").destroyRecursive();
            }
            if (dijit.byId("ipVpcInfoAgreement")) {
                dijit.byId("ipVpcInfoAgreement").destroyRecursive();
            }
            if (dijit.byId("vpcIpEnableStaticNatDialog")) {
                dijit.byId("vpcIpEnableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIPDisableStaticNatDialog")) {
                dijit.byId("vpcIPDisableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcEditTierDialog")) {
                dijit.byId("vpcEditTierDialog").destroyRecursive();
            }
            if (dijit.byId("tierEditConformDialog")) {
                dijit.byId("tierEditConformDialog").destroyRecursive();
            }
            if (dijit.byId("tierReplaceACLDialog")) {
                dijit.byId("tierReplaceACLDialog").destroyRecursive();
            }
            if (dijit.byId("userViewIPForm")) {
                dijit.byId("userViewIPForm").destroyRecursive();
            }
            if (dijit.byId("s2svpnDeleteDialog")) {
                dijit.byId("s2svpnDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createS2SGatewayDialog")) {
                dijit.byId("createS2SGatewayDialog").destroyRecursive();
            }
            if (dijit.byId("addVPNConnectionForm")) {
                dijit.byId("addVPNConnectionForm").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionDeleteDialog")) {
                dijit.byId("vpnConnectionDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionRestartDialog")) {
                dijit.byId("vpnConnectionRestartDialog").destroyRecursive();
            }
            if (dijit.byId("createVPNConnecionPagePage")) {
                dijit.byId("createVPNConnecionPagePage").destroyRecursive();
            }
            if (dijit.byId("vpcPrivateGatewayTab")) {
                dijit.byId("vpcPrivateGatewayTab").destroyRecursive();
            }
            if (dijit.byId("vpcNetworkACLTab")) {
                dijit.byId("vpcNetworkACLTab").destroyRecursive();
            }
            if (dijit.byId("vpcNetworkAclDeleteDialog")) {
                dijit.byId("vpcNetworkAclDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createAclConnectionPage")) {
                dijit.byId("createAclConnectionPage").destroyRecursive();
            }
            if (dijit.byId("vpcVpnConnectionTab")) {
                dijit.byId("vpcVpnConnectionTab").destroyRecursive();
            }
            if (dijit.byId("vpcs2sVpnTab")) {
                dijit.byId("vpcs2sVpnTab").destroyRecursive();
            }
            core.ui.loadTemplate("viewVpc", core.ui.getContentId());
            ViewVpc.init(id);
            
            setTimeout(function () {
                var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr
                var subIpTab = dijit.byId("vpcIpAddressTab"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },800);
            
        }),
        "privateGateway": action(function(id) {
            if (dijit.byId("vpcLoader")) {
                dijit.byId("vpcLoader").destroyRecursive();
            }
            if (dijit.byId("ipVpcTabCointainer")) {
                dijit.byId("ipVpcTabCointainer").destroyRecursive();
            }
            if (dijit.byId("vpcDetailContainer")) {
                dijit.byId("vpcDetailContainer").destroyRecursive();
            }
            if (dijit.byId("vpcEditDialog")) {
                dijit.byId("vpcEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcTierContainer")) {
                dijit.byId("vpcTierContainer").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierDialog")) {
                dijit.byId("vpcAddTierDialog").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierPageForm")) {
                dijit.byId("vpcAddTierPageForm").destroyRecursive();
            }
            if (dijit.byId("tierDeleteDialog")) {
                dijit.byId("tierDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("tierRestartDialog")) {
                dijit.byId("tierRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpcReleaseIpDialog")) {
                dijit.byId("vpcReleaseIpDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIpAddressTab")) {
                dijit.byId("vpcIpAddressTab").destroyRecursive();
            }
            if (dijit.byId("vpcAcquireIpDialog")) {
                dijit.byId("vpcAcquireIpDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpcInfoDeleteDialog")) {
                dijit.byId("vpcInfoDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoListEditDialog")) {
                dijit.byId("vpcInfoListEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoRestartDialog")) {
                dijit.byId("vpcInfoRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpckAquireIPContent")) {
                dijit.byId("vpckAquireIPContent").destroyRecursive();
            }
            if (dijit.byId("ipVpcInfoAgreement")) {
                dijit.byId("ipVpcInfoAgreement").destroyRecursive();
            }
            if (dijit.byId("vpcIpEnableStaticNatDialog")) {
                dijit.byId("vpcIpEnableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIPDisableStaticNatDialog")) {
                dijit.byId("vpcIPDisableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcEditTierDialog")) {
                dijit.byId("vpcEditTierDialog").destroyRecursive();
            }
            if (dijit.byId("tierEditConformDialog")) {
                dijit.byId("tierEditConformDialog").destroyRecursive();
            }
            if (dijit.byId("tierReplaceACLDialog")) {
                dijit.byId("tierReplaceACLDialog").destroyRecursive();
            }
            if (dijit.byId("userViewIPForm")) {
                dijit.byId("userViewIPForm").destroyRecursive();
            }
            if (dijit.byId("s2svpnDeleteDialog")) {
                dijit.byId("s2svpnDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createS2SGatewayDialog")) {
                dijit.byId("createS2SGatewayDialog").destroyRecursive();
            }
            if (dijit.byId("addVPNConnectionForm")) {
                dijit.byId("addVPNConnectionForm").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionDeleteDialog")) {
                dijit.byId("vpnConnectionDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionRestartDialog")) {
                dijit.byId("vpnConnectionRestartDialog").destroyRecursive();
            }
            if (dijit.byId("createVPNConnecionPagePage")) {
                dijit.byId("createVPNConnecionPagePage").destroyRecursive();
            }
            if (dijit.byId("vpcPrivateGatewayTab")) {
                dijit.byId("vpcPrivateGatewayTab").destroyRecursive();
            }
            if (dijit.byId("vpcNetworkACLTab")) {
                dijit.byId("vpcNetworkACLTab").destroyRecursive();
            }
            if (dijit.byId("vpcNetworkAclDeleteDialog")) {
                dijit.byId("vpcNetworkAclDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createAclConnectionPage")) {
                dijit.byId("createAclConnectionPage").destroyRecursive();
            }
            if (dijit.byId("vpcVpnConnectionTab")) {
                dijit.byId("vpcVpnConnectionTab").destroyRecursive();
            }
            if (dijit.byId("vpcs2sVpnTab")) {
                dijit.byId("vpcs2sVpnTab").destroyRecursive();
            }
            
            core.ui.loadTemplate("viewVpc", core.ui.getContentId());
            ViewVpc.init(id);
            
            setTimeout(function () {
                var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr
                var subIpTab = dijit.byId("vpcPrivateGatewayTab"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },800);
            
        }),
        "s2sVpn": action(function(id) {
            if (dijit.byId("vpcLoader")) {
                dijit.byId("vpcLoader").destroyRecursive();
            }
            if (dijit.byId("ipVpcTabCointainer")) {
                dijit.byId("ipVpcTabCointainer").destroyRecursive();
            }
            if (dijit.byId("vpcDetailContainer")) {
                dijit.byId("vpcDetailContainer").destroyRecursive();
            }
            if (dijit.byId("vpcEditDialog")) {
                dijit.byId("vpcEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcTierContainer")) {
                dijit.byId("vpcTierContainer").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierDialog")) {
                dijit.byId("vpcAddTierDialog").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierPageForm")) {
                dijit.byId("vpcAddTierPageForm").destroyRecursive();
            }
            if (dijit.byId("tierDeleteDialog")) {
                dijit.byId("tierDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("tierRestartDialog")) {
                dijit.byId("tierRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpcReleaseIpDialog")) {
                dijit.byId("vpcReleaseIpDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIpAddressTab")) {
                dijit.byId("vpcIpAddressTab").destroyRecursive();
            }
            if (dijit.byId("vpcAcquireIpDialog")) {
                dijit.byId("vpcAcquireIpDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpcInfoDeleteDialog")) {
                dijit.byId("vpcInfoDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoListEditDialog")) {
                dijit.byId("vpcInfoListEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoRestartDialog")) {
                dijit.byId("vpcInfoRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpckAquireIPContent")) {
                dijit.byId("vpckAquireIPContent").destroyRecursive();
            }
            if (dijit.byId("ipVpcInfoAgreement")) {
                dijit.byId("ipVpcInfoAgreement").destroyRecursive();
            }
            if (dijit.byId("vpcIpEnableStaticNatDialog")) {
                dijit.byId("vpcIpEnableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIPDisableStaticNatDialog")) {
                dijit.byId("vpcIPDisableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcEditTierDialog")) {
                dijit.byId("vpcEditTierDialog").destroyRecursive();
            }
            if (dijit.byId("tierEditConformDialog")) {
                dijit.byId("tierEditConformDialog").destroyRecursive();
            }
            if (dijit.byId("tierReplaceACLDialog")) {
                dijit.byId("tierReplaceACLDialog").destroyRecursive();
            }
            if (dijit.byId("userViewIPForm")) {
                dijit.byId("userViewIPForm").destroyRecursive();
            }
            if (dijit.byId("s2svpnDeleteDialog")) {
                dijit.byId("s2svpnDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createS2SGatewayDialog")) {
                dijit.byId("createS2SGatewayDialog").destroyRecursive();
            }
            if (dijit.byId("addVPNConnectionForm")) {
                dijit.byId("addVPNConnectionForm").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionDeleteDialog")) {
                dijit.byId("vpnConnectionDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionRestartDialog")) {
                dijit.byId("vpnConnectionRestartDialog").destroyRecursive();
            }
            if (dijit.byId("createVPNConnecionPagePage")) {
                dijit.byId("createVPNConnecionPagePage").destroyRecursive();
            }
            if (dijit.byId("vpcPrivateGatewayTab")) {
                dijit.byId("vpcPrivateGatewayTab").destroyRecursive();
            }
            if (dijit.byId("vpcNetworkACLTab")) {
                dijit.byId("vpcNetworkACLTab").destroyRecursive();
            }
            if (dijit.byId("vpcNetworkAclDeleteDialog")) {
                dijit.byId("vpcNetworkAclDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createAclConnectionPage")) {
                dijit.byId("createAclConnectionPage").destroyRecursive();
            }
            if (dijit.byId("vpcVpnConnectionTab")) {
                dijit.byId("vpcVpnConnectionTab").destroyRecursive();
            }
            if (dijit.byId("vpcs2sVpnTab")) {
                dijit.byId("vpcs2sVpnTab").destroyRecursive();
            }
            core.ui.loadTemplate("viewVpc", core.ui.getContentId());
            ViewVpc.init(id);
            
            setTimeout(function () {
                var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr
                var subIpTab = dijit.byId("vpcs2sVpnTab"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },800);
            
        }),
        "networkAcl": action(function(id) {
            if (dijit.byId("vpcLoader")) {
                dijit.byId("vpcLoader").destroyRecursive();
            }
            if (dijit.byId("ipVpcTabCointainer")) {
                dijit.byId("ipVpcTabCointainer").destroyRecursive();
            }
            if (dijit.byId("vpcDetailContainer")) {
                dijit.byId("vpcDetailContainer").destroyRecursive();
            }
            if (dijit.byId("vpcEditDialog")) {
                dijit.byId("vpcEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcTierContainer")) {
                dijit.byId("vpcTierContainer").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierDialog")) {
                dijit.byId("vpcAddTierDialog").destroyRecursive();
            }
            if (dijit.byId("vpcAddTierPageForm")) {
                dijit.byId("vpcAddTierPageForm").destroyRecursive();
            }
            if (dijit.byId("tierDeleteDialog")) {
                dijit.byId("tierDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("tierRestartDialog")) {
                dijit.byId("tierRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpcReleaseIpDialog")) {
                dijit.byId("vpcReleaseIpDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIpAddressTab")) {
                dijit.byId("vpcIpAddressTab").destroyRecursive();
            }
            if (dijit.byId("vpcAcquireIpDialog")) {
                dijit.byId("vpcAcquireIpDialog").destroyRecursive();
            }
            
            if (dijit.byId("vpcInfoDeleteDialog")) {
                dijit.byId("vpcInfoDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoListEditDialog")) {
                dijit.byId("vpcInfoListEditDialog").destroyRecursive();
            }
            if (dijit.byId("vpcInfoRestartDialog")) {
                dijit.byId("vpcInfoRestartDialog").destroyRecursive();
            }
            if (dijit.byId("vpckAquireIPContent")) {
                dijit.byId("vpckAquireIPContent").destroyRecursive();
            }
            if (dijit.byId("ipVpcInfoAgreement")) {
                dijit.byId("ipVpcInfoAgreement").destroyRecursive();
            }
            if (dijit.byId("vpcIpEnableStaticNatDialog")) {
                dijit.byId("vpcIpEnableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIPDisableStaticNatDialog")) {
                dijit.byId("vpcIPDisableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcEditTierDialog")) {
                dijit.byId("vpcEditTierDialog").destroyRecursive();
            }
            if (dijit.byId("tierEditConformDialog")) {
                dijit.byId("tierEditConformDialog").destroyRecursive();
            }
            if (dijit.byId("tierReplaceACLDialog")) {
                dijit.byId("tierReplaceACLDialog").destroyRecursive();
            }
            if (dijit.byId("userViewIPForm")) {
                dijit.byId("userViewIPForm").destroyRecursive();
            }
            if (dijit.byId("s2svpnDeleteDialog")) {
                dijit.byId("s2svpnDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createS2SGatewayDialog")) {
                dijit.byId("createS2SGatewayDialog").destroyRecursive();
            }
            if (dijit.byId("addVPNConnectionForm")) {
                dijit.byId("addVPNConnectionForm").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionDeleteDialog")) {
                dijit.byId("vpnConnectionDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("vpnConnectionRestartDialog")) {
                dijit.byId("vpnConnectionRestartDialog").destroyRecursive();
            }
            if (dijit.byId("createVPNConnecionPagePage")) {
                dijit.byId("createVPNConnecionPagePage").destroyRecursive();
            }
            if (dijit.byId("vpcPrivateGatewayTab")) {
                dijit.byId("vpcPrivateGatewayTab").destroyRecursive();
            }
            if (dijit.byId("vpcNetworkACLTab")) {
                dijit.byId("vpcNetworkACLTab").destroyRecursive();
            }
            if (dijit.byId("vpcNetworkAclDeleteDialog")) {
                dijit.byId("vpcNetworkAclDeleteDialog").destroyRecursive();
            }
            if (dijit.byId("createAclConnectionPage")) {
                dijit.byId("createAclConnectionPage").destroyRecursive();
            }
            if (dijit.byId("vpcVpnConnectionTab")) {
                dijit.byId("vpcVpnConnectionTab").destroyRecursive();
            }
            if (dijit.byId("vpcs2sVpnTab")) {
                dijit.byId("vpcs2sVpnTab").destroyRecursive();
            }
            
            core.ui.loadTemplate("viewVpc", core.ui.getContentId());
            ViewVpc.init(id);
            
            setTimeout(function () {
                var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr
                var subIpTab = dijit.byId("vpcIpAddressTab"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },800);
            
        }),
        "viewIp": action(function(id) {
            if (dijit.byId("vpcIpDetailsTabCointainer")) {
                dijit.byId("vpcIpDetailsTabCointainer").destroyRecursive();
            }
            if (dijit.byId("vpcIpDetailContainer")) {
                dijit.byId("vpcIpDetailContainer").destroyRecursive();
            }
            if (dijit.byId("vpcIpPortForwarding")) {
                dijit.byId("vpcIpPortForwarding").destroyRecursive();
            }
            if (dijit.byId("vpcIpLoadBalancing")) {
                dijit.byId("vpcIpLoadBalancing").destroyRecursive();
            }
            if (dijit.byId("userViewVpcIPForm")) {
                dijit.byId("userViewVpcIPForm").destroyRecursive();
            }
            if (dijit.byId("vpcIpLoadBalancing")) {
                dijit.byId("vpcIpLoadBalancing").destroyRecursive();
            }
            if (dijit.byId("vpcIpPortForwarding")) {
                dijit.byId("vpcIpPortForwarding").destroyRecursive();
            }
            if (dijit.byId("vpcIPDisableStaticNatDialog")) {
                dijit.byId("vpcIPDisableStaticNatDialog").destroyRecursive();
            }
            
            if (dijit.byId("userNetworkIPLoadBalancingAddForm")) {
                dijit.byId("userNetworkIPLoadBalancingAddForm").destroyRecursive();
            }
            if (dijit.byId("viewIpLoader")) {
                dijit.byId("viewIpLoader").destroyRecursive();
            }
            if (dijit.byId("vpcDeleteLBDialog")) {
                dijit.byId("vpcDeleteLBDialog").destroyRecursive();
            }
            if (dijit.byId("lbRemoveVMDialog")) {
                dijit.byId("lbRemoveVMDialog").destroyRecursive();
            }
            if (dijit.byId("networkEditLBDialog")) {
                dijit.byId("networkEditLBDialog").destroyRecursive();
            }
            if (dijit.byId("userNetworkIPPortForwardingAddForm")) {
                dijit.byId("userNetworkIPPortForwardingAddForm").destroyRecursive();
            }
            if (dijit.byId("vpcIPPageEnableStaticNatDialog")) {
                dijit.byId("vpcIPPageEnableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIPPageDisableStaticNatDialog")) {
                dijit.byId("vpcIPPageDisableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("tierReplaceACLDialog")) {
                dijit.byId("tierReplaceACLDialog").destroyRecursive();
            }
                       
            core.ui.loadTemplate("viewVpcIPAddress", core.ui.getContentId());
            viewVpcIp.populateValues(id);
            vpcLBDetails.init();
            vpcPFDetails.init();
        }),
        "viewLB" : action(function (id) {
            if (dijit.byId("vpcIpDetailsTabCointainer")) {
                dijit.byId("vpcIpDetailsTabCointainer").destroyRecursive();
            }
            if (dijit.byId("vpcIpDetailContainer")) {
                dijit.byId("vpcIpDetailContainer").destroyRecursive();
            }
            if (dijit.byId("vpcIpPortForwarding")) {
                dijit.byId("vpcIpPortForwarding").destroyRecursive();
            }
            if (dijit.byId("vpcIpLoadBalancing")) {
                dijit.byId("vpcIpLoadBalancing").destroyRecursive();
            }
            if (dijit.byId("userViewVpcIPForm")) {
                dijit.byId("userViewVpcIPForm").destroyRecursive();
            }
            if (dijit.byId("vpcIpLoadBalancing")) {
                dijit.byId("vpcIpLoadBalancing").destroyRecursive();
            }
            if (dijit.byId("vpcIpPortForwarding")) {
                dijit.byId("vpcIpPortForwarding").destroyRecursive();
            }
            if (dijit.byId("vpcIPDisableStaticNatDialog")) {
                dijit.byId("vpcIPDisableStaticNatDialog").destroyRecursive();
            }
            
            if (dijit.byId("userNetworkIPLoadBalancingAddForm")) {
                dijit.byId("userNetworkIPLoadBalancingAddForm").destroyRecursive();
            }
            if (dijit.byId("viewIpLoader")) {
                dijit.byId("viewIpLoader").destroyRecursive();
            }
            if (dijit.byId("vpcDeleteLBDialog")) {
                dijit.byId("vpcDeleteLBDialog").destroyRecursive();
            }
            if (dijit.byId("lbRemoveVMDialog")) {
                dijit.byId("lbRemoveVMDialog").destroyRecursive();
            }
            if (dijit.byId("networkEditLBDialog")) {
                dijit.byId("networkEditLBDialog").destroyRecursive();
            }
            if (dijit.byId("userNetworkIPPortForwardingAddForm")) {
                dijit.byId("userNetworkIPPortForwardingAddForm").destroyRecursive();
            }
            if (dijit.byId("vpcIPPageEnableStaticNatDialog")) {
                dijit.byId("vpcIPPageEnableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("vpcIPPageDisableStaticNatDialog")) {
                dijit.byId("vpcIPPageDisableStaticNatDialog").destroyRecursive();
            }
            if (dijit.byId("tierReplaceACLDialog")) {
                dijit.byId("tierReplaceACLDialog").destroyRecursive();
            }
                       
            core.ui.loadTemplate("viewVpcIPAddress", core.ui.getContentId());
            viewVpcIp.populateValues(id);
            vpcLBDetails.init();
            vpcPFDetails.init();            
                                                      
            setTimeout(function () {
                var mainTab = dijit.byId("vpcIpDetailsTabCointainer"); //Tr
                var subIpTab = dijit.byId("vpcIpLoadBalancing"); //tab Id which you want to show
                mainTab.selectChild(subIpTab);   
            },500);
        })
    });
});

var NetworkTopology = {
    addTopoTier : function () {
        var name = dijit.byId("topoTierName").value;
        var currentVPC = dijit.byId("generaTopologyVpcWidget");
        var gateway = dijit.byId("topologyTierGateway").value;
        var networkOffer = dijit.byId("generaTopologyNwOfferWidget").value;
        var netmask = dijit.byId("topologyTierNetmask").value;
        var acl = dijit.byId("generaTopologyNwAclWidget").value;

        var pageNode = dojo.byId("topoAddTierPageDiv");
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
            var tierRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/tier/create"
            });
            dijit.byId("topoVpcLoader").show();
            
            tierRestStore.add({netmask: netmask, acl:acl, name: name, gateway: gateway, networkOffer: networkOffer, vpcId: currentVPC.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.tierCreate, "message");
                        registry.byId("userToaster").show();
                         dijit.byId("topoVpcLoader").hide();
                         dijit.byId("vpcTopologyAddTierDialog").hide();
                         NetworkTopology.populateValues();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                         dijit.byId("topoVpcLoader").hide();
                    }
                });
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
        
        var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var aclRestStoreList = new ItemFileWriteStore({data: option});  
        var currentVPC = dijit.byId("generaTopologyVpcWidget");
        if(tierType.toString() === "APP") {
            dojo.byId("topolAclDiv").style.display = "none";
            aclRestStoreList.newItem({id: "option", name: translator.common.noAcl});  
            dijit.byId("generaTopologyNwAclWidget").set("store", aclRestStoreList);
            dijit.byId("generaTopologyNwAclWidget").set("value", "option");
        } else {
            dojo.byId("topolAclDiv").style.display = "block";            
            var aclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/networkAcl/"
            });       
            aclRestStore.query({vpcId: currentVPC.value}).then(function(data) {
                var firstAcl = "";
                if (!data || data.length === 0) {
                    aclRestStoreList.newItem({id: "option", name: translator.common.noAcl});
                    firstAcl = "option";
                } else {                     
                    dojo.forEach(data, function(resultData, index) {
                        aclRestStoreList.newItem({id: resultData.referenceId, name: resultData.name});       
                        if(index === 0) {
                            firstAcl = resultData.referenceId;
                        }
                    });
                }     
                dijit.byId("generaTopologyNwAclWidget").set("store", aclRestStoreList);
                dijit.byId("generaTopologyNwAclWidget").set("value", firstAcl);                 
            });            
        }        
    },
    cancelTier : function () {
        dijit.byId("vpcTopologyAddTierDialog").hide();
    },
    addTierShow : function () {     
        var currentVPC = dijit.byId("generaTopologyVpcWidget");
        var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var networkFileStoreList = new ItemFileWriteStore({data: option});  
        var aclRestStoreList = new ItemFileWriteStore({data: option});  
        
        var networkOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer/forVpc/"
        });
        if(currentVPC.value === "option") {
            networkFileStoreList.newItem({id: "option", name: translator.common.noNetworkOffer, tierType: ""});
            aclRestStoreList.newItem({id: "option", name: translator.common.noAcl});
        } else {
            networkOfferRestStore.query({vpcId: currentVPC.value}).then(function(data) {
                var firstNw = "";
                if (!data || data.length === 0) {
                    networkFileStoreList.newItem({id: "option", name: translator.common.noNetworkOffer, tierType: ""});
                    firstNw = "option";
                } else {
                    dojo.forEach(data, function(resultData, index) {
                        networkFileStoreList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});
                        if(index === 0) {
                            firstNw = resultData.referenceId;
                        }
                    });                    
                }
                dijit.byId("generaTopologyNwOfferWidget").set("store", networkFileStoreList);
                dijit.byId("generaTopologyNwOfferWidget").set("value", firstNw);                
            });         
            var aclRestStore = new JsonRest({
                target: core.getContextPath()+"/api/networkAcl/"
            });
       
            aclRestStore.query({vpcId: currentVPC.value}).then(function(data) {
                var firstAcl = "";
                 if (!data || data.length === 0) {
                     aclRestStoreList.newItem({id: "option", name: translator.common.noAcl});
                     firstAcl = "option";
                 } else {                     
                     dojo.forEach(data, function(resultData, index) {
                         aclRestStoreList.newItem({id: resultData.referenceId, name: resultData.name});       
                         if(index === 0) {
                             firstAcl = resultData.referenceId;
                         }
                     });
                 }     
                 dijit.byId("generaTopologyNwAclWidget").set("store", aclRestStoreList);
                 dijit.byId("generaTopologyNwAclWidget").set("value", firstAcl);                 
             });
         }
         dijit.byId("topoTierName").reset();
         dijit.byId("topologyTierGateway").reset();        
         dijit.byId("topologyTierNetmask").reset();      
         dijit.byId("vpcTopologyAddTierDialog").show();
     },
    loadCurrentVPC : function (currentVPC) {
        var id = currentVPC; 
        var vpcTopologyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/vpcTopology/"
        });
        dojo.query("#generalWebTierList .dijitTitlePane").forEach(dojo.destroy);
        dojo.query("#generalAppTierList .dijitTitlePane").forEach(dojo.destroy);
        dojo.query("#generalDbTierList .dijitTitlePane").forEach(dojo.destroy);
        dojo.byId("generalWebTierList").innerHTML = "";
        dojo.byId("generalAppTierList").innerHTML = "";
        dojo.byId("generalDbTierList").innerHTML = "";
        
        var tierRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/list/tier"
        });
        dojo.query("#generalTopologyTierMenuList").toggleClass("hide_text", true);                
        dojo.query("#generalTopologyTierLoader").removeClass("hide_text", true);
        if(currentVPC.value === "option") {            
            dojo.query("#generalTopologyTierMenuList").removeClass("hide_text", true);                
            dojo.query("#generalTopologyTierLoader").toggleClass("hide_text", true);            
            dojo.query("#GeneralTopologyPage").toggleClass("hide_text", true);            
            dojo.byId("topologyNoVpcMessageBox").style.display = "block";
        } else {    
            dojo.query("#GeneralTopologyPage").removeClass("hide_text", true);            
            dojo.byId("topologyNoVpcMessageBox").style.display = "none";
            
            tierRestStore.query({vpcId: id}).then(function (vpcList) {
                vpcTopologyRestStore.query({vpcId:id}).then(function(data) {
                dojo.forEach(data, function(vpcData) {                
                    if(vpcData.name) {                
                        dojo.byId("vpcGeneralPrivageGatewayLink").href = "#/user/vpc/showVpc/" + vpcData.referenceId + "," + "vpcPrivateGatewayTab";
                        dojo.byId("vpcGeneralPublicIpLink").href = "#/user/vpc/showVpc/" + vpcData.referenceId + "," + "vpcIpAddressTab";
                        dojo.byId("vpcGenerals2sVpnLink").href = "#/user/vpc/showVpc/" + vpcData.referenceId + "," + "vpcs2sVpnTab";
                        dojo.byId("vpcGeneralNetworkAclLink").href = "#/user/vpc/showVpc/" + vpcData.referenceId + "," + "vpcNetworkACLTab";

                        dojo.byId("generalPublicIp").innerHTML = vpcData.publicIp;
                        dojo.byId("generalS2sVpn").innerHTML = vpcData.s2svpn;
                        dojo.byId("generalNetworkAcl").innerHTML =  vpcData.networkAcl;
                        dojo.byId("generlPrivateGateway").innerHTML = vpcData.privateGateway;
                        dojo.byId("vpcGeneralCidrValue").innerHTML = vpcData.cidr;  
                        
                        if(vpcData.appTier.length === 0 || vpcData.appTier === "" || vpcData.appTier === undefined) {
                            dojo.byId("generalAppTierList").innerHTML = "";
                            dojo.byId("generalTopoAppTierCount").innerHTML = "(" + vpcData.appTier.length + ")";
                        } else {
                            dojo.byId("generalTopoAppTierCount").innerHTML = "(" + vpcData.appTier.length + ")";
                            dojo.forEach(vpcData.appTier, function (tier, index) {   
                            var appTierSize = vpcData.appTier.length;
                            var tierContent = "";    
                            tierContent = "<div class='row-fluid'>"+ 
                                    "<div class='tiers-info-desc'>"+
                                    "<div class='span12 router-info-boxlst'>" +                                        
                                    "<div class='span6'>" + 
                                    "<a href=#/user/tier/instanceList/"+tier.id+"><div class='info-boxlist-value'>"+tier.vm+"</div><div class='info-boxlist-title'>"+translator.common.virtualMachine+"</div></a>"+
                                    "</div>"+
                                    "<div class='span6'>" + 
                                    "<a href=#/user/tier/viewInternalLB/"+tier.id+"><div class='info-boxlist-value'>"+tier.internalLb+"</div><div class='info-boxlist-title'>"+translator.common.internalLb+"</div></a>"+
                                   "</div></div>"+
                                   "<div class='span12 router-info-boxlstodd'><div class='row-fluid'><div class='ipcont span12'>"+translator.common.cidr+':'+tier.cidr+"</div></div></div>"+
                                   "</div></div>";                                                                                               
                           var tierWidget = new dijit.TitlePane({
                               "class": "",
                                style: "height: auto%; width: 100%;",
                                title: tier.name,
                                content: tierContent,
                                open: true,
                                onShow: function () {                            
                                }
                            });
                            var pinNode = document.createElement('div');   
                            pinNode.className = 'tiers-pin';
                            var tierNode = document.createElement('div'); 
                            if(appTierSize === index + 1) {
                                tierNode.className = 'tiers-cont-last row-fluid';
                            } else {
                                tierNode.className = 'tiers-cont row-fluid';
                            }
                            tierNode.appendChild(pinNode);
                            tierNode.appendChild(tierWidget.domNode);
                            dojo.byId("generalAppTierList").appendChild(tierNode); 
                            tierWidget.startup();                    
                        });
                    }                
                    if(vpcData.dbTier.length === 0 || vpcData.dbTier === "" || vpcData.dbTier === undefined) {
                        dojo.byId("generalDbTierList").innerHTML = "";
                        dojo.byId("generalTopoDBTierCount").innerHTML = "(" + vpcData.dbTier.length + ")";
                    } else {
                        dojo.byId("generalTopoDBTierCount").innerHTML = "(" + vpcData.dbTier.length + ")";
                        dojo.forEach(vpcData.dbTier, function (tier, index) {                       
                            var tierContent = "";                    
                            tierContent = "<div class='row-fluid'>"+ 
                                    "<div class='tiers-info-desc'>"+
                                    "<div class='span12 router-info-boxlst'>" +                                                                                    
                                    "<div class='span6'>" + 
                                    "<a href=#/user/tier/staticNatList/"+tier.id+"><div class='info-boxlist-value'>"+tier.staticNat+"</div><div class='info-boxlist-title'>"+translator.common.staticNat+"</div></a>"+
                                    "</div>"+
                                    "<div class='span6'>" + 
                                    "<a href=#/user/tier/portForwardingList/"+tier.id+"><div class='info-boxlist-value'>"+tier.portForwarding+"</div><div class='info-boxlist-title'>"+translator.common.portForwardingIP+"</div></a>"+
                                    "</div></div>"+
                                    "<div class='span12 router-info-boxlstodd'>" + 
                                    "<div class='field_box'>"+
                                    "<a href=#/user/tier/instanceList/"+tier.id+"><div class='info-boxlist-value'>"+tier.vm+"</div><div class='info-boxlist-title'>"+translator.common.virtualMachine+"</div></a>"+
                                    "</div>"+   
                                    "<div class='span12 router-info-boxlstodd'><div class='row-fluid'><div class='ipcont span12'>"+translator.common.cidr+':'+tier.cidr+"</div></div></div>"+
                                "</div></div>";                                                        
                        var tierWidget = new dijit.TitlePane({
                            "class": "",
                            style: "height: auto%; width: 100%;",
                            title: tier.name,
                            content: tierContent,
                            open: true,
                            onShow: function () {}
                        });
                        var pinNode = document.createElement('div');   
                        pinNode.className = 'tiers-pin';
                        var tierNode = document.createElement('div');  
                        if(vpcData.dbTier.length === index + 1) {
                            tierNode.className = 'tiers-cont-last row-fluid';
                        } else {
                            tierNode.className = 'tiers-cont row-fluid';
                        }
                        tierNode.appendChild(pinNode);
                        tierNode.appendChild(tierWidget.domNode);
                        dojo.byId("generalDbTierList").appendChild(tierNode); 
                        tierWidget.startup();
                    });
                }        
                if(vpcData.webTier.length === 0 || vpcData.webTier === "" || vpcData.webTier === undefined) { 
                    dojo.byId("generalWebTierList").innerHTML = "";         
                    dojo.byId("generalTopoWebTierCount").innerHTML = "(" + vpcData.webTier.length + ")";
                } else {   
                    dojo.byId("generalTopoWebTierCount").innerHTML = "(" + vpcData.webTier.length + ")";
                    dojo.forEach(vpcData.webTier, function (tier, index) {                       
                        var tierContent = "";                                        
                        tierContent = "<div class='row-fluid'>"+ 
                                "<div class='tiers-info-desc'>"+
                                "<div class='span12 router-info-boxlst'>" +                                        
                                    "<div class='span6'>" + 
                                    "<a href=#/user/tier/staticNatList/"+tier.id+"><div class='info-boxlist-value'>"+tier.staticNat+"</div><div class='info-boxlist-title'>"+translator.common.staticNat+"</div></a>"+
                                    "</div>"+
                                    "<div class='span6'>" + 
                                    "<a href=#/user/tier/portForwardingList/"+tier.id+"><div class='info-boxlist-value'>"+tier.portForwarding+"</div><div class='info-boxlist-title'>"+translator.common.portForwardingIP+"</div></a>"+
                                    "</div></div>"+
                                    "<div class='span12 router-info-boxlstodd'><div class='span6'>" + 
                                    "<a href=#/user/tier/instanceList/"+tier.id+"><div class='info-boxlist-value'>"+tier.vm+"</div><div class='info-boxlist-title'>"+translator.common.virtualMachine+"</div></a>"+
                                    "</div>"+
                                    "<div class='span6'>" + 
                                    "<a href=#/user/tier/publicLBIPList/"+tier.id+"><div class='info-boxlist-value'>"+tier.publicLbIp+"</div><div class='info-boxlist-title'>"+translator.common.publicLbIP+"</div></a>"+
                                    "</div> </div>"+
                                    "<div class='span12 router-info-boxlstodd'><div class='row-fluid'><div class='ipcont span12'>"+translator.common.cidr+':'+tier.cidr+"</div></div></div>"+
                                    "</div></div>";                                                                 
                            var tierWidget = new dijit.TitlePane({
                                "class": "",
                                style: "height: auto%; width: 100%;",
                                title: tier.name,
                                content: tierContent,
                                open: true,
                                onShow: function () {}
                            });
                            var pinNode = document.createElement('div');   
                            pinNode.className = 'tiers-pin';
                            var tierNode = document.createElement('div');  
                            if(vpcData.webTier.length === index + 1) {
                                tierNode.className = 'tiers-cont-last row-fluid';
                            } else {
                                tierNode.className = 'tiers-cont row-fluid';
                            }
                            tierNode.appendChild(pinNode);
                            tierNode.appendChild(tierWidget.domNode);        
                            dojo.byId("generalWebTierList").appendChild(tierNode); 
                            tierWidget.startup();
                        });
                    }        
                }                            
            });     
            dojo.query("#generalTopologyTierMenuList").removeClass("hide_text", true);                
            dojo.query("#generalTopologyTierLoader").toggleClass("hide_text", true);
        });
    });
            
            
    }        
},
    populateValues : function () {
        if(dijit.byId("generaTopologyVpcWidget")) {                                    
            dijit.byId("generaTopologyVpcWidget").destroyRecursive();                    
        }        
        var option = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storeList = new ItemFileWriteStore({data: option});                        
        
        var vpcListResourceService = new JsonRest({
             target: core.getContextPath()+"/api/vpc"
         });
        vpcListResourceService.query().then(function (data) {
            var firstValue = "";
            if(data.length === 0 || data === "" || data === undefined) {
                storeList.newItem({id: "option", name : translator.common.noVPC});
                firstValue = "option";
            } else {
                
                dojo.forEach(data,  function (el, index) {
                    storeList.newItem({id: el.referenceId, name : el.name});
                    if(index === 0) {
                        firstValue = el.referenceId;
                    }
                });
            }                                                    
            var vpcWidget = new FilteringSelect({
                id: "generaTopologyVpcWidget",            
                store: storeList,                   
                sortByLabel: false,
                autoHeight: true,
                onChange: function() {
                    NetworkTopology.loadCurrentVPC(this);
                }
            }).placeAt("generalTopologyList");
            dijit.byId("generaTopologyVpcWidget").set("value", firstValue);
        });       
        var nwOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var nwStoreList = new ItemFileWriteStore({data: nwOption});  
        var nwOfferWidget = new FilteringSelect({
            id: "generaTopologyNwOfferWidget",
            store: nwStoreList,
            sortByLabel: false,
            autoHeight: true,
            maxHeight: 100,
            onChange: function() {
                NetworkTopology.showACLList(this);
            }
        }).placeAt("topologyNetworkOfferingList");
        
        var nwACLWidget = new FilteringSelect({
            id: "generaTopologyNwAclWidget",
            store: nwStoreList,                   
            sortByLabel: false,
            autoHeight: true,            
            maxHeight: 100,
        }).placeAt("topologyTierAclList");
            }
};


var SiteToSiteVPN = {
    'showDeleteS2SVPNDialog' : function(id) {
        dojo.byId('currentS2SVPNID').value = id;
        dijit.byId('s2svpnDeleteDialog').show();
    },
    'createS2SGatewayShow' : function(id) {
        dijit.byId('createS2SGatewayDialog').show();
    },
    'closeCreateS2SGatewayShow' : function() {
        dijit.byId('createS2SGatewayDialog').hide();
    },
    'deleteDeleteS2SVPNClose' : function(id) {
       
        dijit.byId('s2svpnDeleteDialog').hide();
    },
    deleteS2SVPN: function() {        
        var id = dojo.byId('currentS2SVPNID').value;        
        var s2sRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/s2s/remove/"
        });

        dijit.byId('vpcLoader').show();
        dijit.byId('s2svpnDeleteDialog').hide();
                
        s2sRestStore.add({vpnId: id}).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if (resultData.result == "OK") {
                    var deletes2svpnJobStatus = setInterval(function() {
                        SiteToSiteVPN.deletes2svpnJob(resultData.jobId, id,deletes2svpnJobStatus);
                    }, 3000);

                } else {
                    registry.byId("userToaster").setContent(translator.common.firewall.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }

            });

        });
    },
    'deletes2svpnJob': function(jobId, id,deletes2svpnJobStatus) {
        var s2svpnJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/s2s/remove/job/"
        });

        s2svpnJobRestStoreStore.add({jobId:jobId, vpnId:id}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deletes2svpnJobStatus);
                    SiteToSiteVPN.list();
                    registry.byId("userToaster").setContent(translator.common.message.deleteS2SvpnSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deletes2svpnJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    },
    'creates2svpn': function() {

        var vpcId = dojo.byId("currentVpcId").value;

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/s2s/create"
        });
        dijit.byId('vpcLoader').show();
        dijit.byId('createS2SGatewayDialog').hide();
        networkRestStore.add({vpcId: vpcId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var createJobStatus = setInterval(function() {
                        SiteToSiteVPN.createJob(resultData.jobId, createJobStatus);
                    }, 3000);
                } else if (resultData.result === "HASFIRSTIP") {
                    registry.byId("userToaster").setContent(translator.common.message.networkHasFirstIPMessage, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    },
    'createJob': function(jobId, acquireIpJobStatus) {
        var acquireIpJobStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/s2s/job/"
        });

        acquireIpJobStoreStore.add({jobId: jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult == "OK") {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.addS2SvpnSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                    SiteToSiteVPN.list();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    },
    list : function () {   
        var vpcId = dojo.byId("currentVpcId").value;

        dojo.byId("s2sGatewayList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ips, 'field': 'ip', 'width': '20%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '30%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vpcName, 'field': 'vpcName', 'width': '30%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                'formatter': function(data) {
                    var html = "<a onclick='SiteToSiteVPN.showDeleteS2SVPNDialog(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";

                    return html;
                }, 'width': '20%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var networkIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/s2s/list/"
        });
        networkIPAddressRestStore.query({vpcId:vpcId}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("s2sGatewayList").innerHTML = "";
                dojo.byId("noS2SVPNMessageBox").style.display = "block";
                 dojo.byId("createVPNS2S").style.display = "block";
            } else {
                dojo.byId("noS2SVPNMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.id,
                        ip:resultData.ip,
                        vpcName:resultData.vpc,
                        zone: resultData.zone,
                        action:resultData
                    });
                });
                dojo.byId("s2sGatewayList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("s2sGatewayList");
                dataGrid.startup();
                dataGrid.update();
                dojo.byId("createVPNS2S").style.display = "none";
            }
        });
    }
    
};

var vpcPFDetails = {
    deselectVMSIP : function () {              
    },
   
    'showDeletePortForwardingDialog' : function(id) {
        dojo.byId('currentNetworkPortForwardingId').value = id;
         dijit.byId('vpcDeletePortForwardingDialog').show();
    },
    'deletePortForwardingRuleClose' : function(id) {
        dojo.byId('currentNetworkPortForwardingId').value = id;
        dijit.byId('vpcDeletePortForwardingDialog').hide();
    },
    deletePortForwardingRule: function() {        
        var id = dojo.byId('currentNetworkPortForwardingId').value;        
        var networkRuleRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/portForwarding/remove/"
        });

        dijit.byId('viewIpLoader').show();
        dijit.byId('vpcDeletePortForwardingDialog').hide();
                
        networkRuleRestStore.add({ruleId: id}).then(function(result) {
        dojo.forEach(result, function(resultData) {
            if (resultData.result == "OK") {
                var deletePortForwardingJobStatus = setInterval(function() {
                    vpcPFDetails.deletePortForwardingRuleJob(resultData.jobId, id, deletePortForwardingJobStatus);
                }, 3000);

            } else {
                registry.byId("userToaster").setContent(translator.common.firewall.cloudStackError, "error");
                registry.byId("userToaster").show();
                dijit.byId('viewIpLoader').hide();
            }

        });

    });                
    },
    cancelVMGrid : function () {
         
        dojo.byId("vmListContainer").style.display="none";                        
        dojo.byId("addVMIPPageButton").style.display="block";                        
        dojo.query("#vpcPFExistList").removeClass("hide_text", true); 
        dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
        dojo.query("#VMRequireCopyMsg").toggleClass("hide_text", true);        
        
        dijit.byId('userNetworkIPPortForwardingAddForm').reset();        
        dojo.byId("addIPButtonDiv").style.display="none";     
    },
    showVMList : function () {               
        var pageNode = dojo.byId("vpcIPPortForwardingAddPage");
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
             
             dojo.byId("addVMIPPageButton").style.display="none";
             dojo.byId("vmListContainer").style.display="block";
//             dojo.byId("addIPButtonDiv").style.display="block";
             dojo.byId("addIPButtonDiv").style.display="block";
             dojo.query("#vpcPFExistList").toggleClass("hide_text", true);                
             dijit.byId('vpcPFIPVMListGrid').render();             
        }
        
    },
    'addPortForwarding': function() {
        var gridItems = dijit.byId('vpcPFIPVMListGrid').selection.getSelected();        
        var vmId = "";
        dojo.forEach(gridItems, function(selectedItem) {            
            dojo.forEach(selectedItem.id, function (el) {
                vmId = el;
            });
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
                 dojo.query("#VMRequireMsg").removeClass("hide_text", true);
             } else {
//                 dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
                 dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
                 dijit.byId('viewIpLoader').show();
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
                                vpcPFDetails.addPortForwardingJob(resultData.jobId, addPortForwardingJobStatus);
                            }, 3000);
                        } else if (resultData.result === "FAILED") {                                   
                            registry.byId("userToaster").setContent(resultData.message, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('userNetworkIPPortForwardingAddForm').reset();
                            dijit.byId("viewIpLoader").hide();                            
                        } else {
                            registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('userNetworkIPPortForwardingAddForm').reset();
                            dijit.byId('viewIpLoader').hide();
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
                    dijit.byId('viewIpLoader').hide();
                    dojo.byId("vmListContainer").style.display="none";    
//                    dojo.byId("addIPButtonDiv").style.display="none";
                    dojo.byId("addIPButtonDiv").style.display="none";
                    dojo.query("#vpcPFExistList").removeClass("hide_text", true); 
//                    dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
                    dojo.query("#VMRequireMsg").toggleClass("hide_text", true);
                  
                    vpcPFDetails.list();
                    setTimeout(function () {
                         viewVpcIp.populateValues(dojo.byId("currentIPId").value);
                    },800);
                   
                } else if (jobResultData.jobResult === "Pending") {

                } else {                
                    clearInterval(addPortForwardingJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('userNetworkIPPortForwardingAddForm').reset();
                    dijit.byId('viewIpLoader').hide();                                       
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
                    vpcPFDetails.list();
                    registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();
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
    'init': function() {
        
        
        var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
        var tierWidget = new FilteringSelect({
            id: "vpcPFTier",
            store: tierList,
            placeHolder: translator.common.message.selectTier,
            onChange: function() {
                vpcPFDetails.getTierVMList(this);
            }
        });
        tierWidget.placeAt("vpcPFTierList");
        tierWidget.startup();
        
    },
    'populateValues' : function() {
        
        dijit.byId('userNetworkIPPortForwardingAddForm').reset();        
        dojo.byId("addIPButtonDiv").style.display="none";     
        dojo.byId("addVMIPPageButton").style.display="block";     
        
        if (dijit.byId("vpcPFIPVMListGrid")) {
            dijit.byId("vpcPFIPVMListGrid").destroyRecursive();
        }
                
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/list/tier"
        });
        
         var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
         networkRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function(data) {
            dojo.forEach(data, function(tier) {
                if(tier.tierType === "WEB" || tier.tierType === "DB") {
                    tierList.newItem({
                        id: tier.referenceId,
                        name: tier.name,
                        networkId : tier.id
                    });
                }
            });
            dijit.byId("vpcPFTier").reset();
            dijit.byId("vpcPFTier").set("store", tierList);
            
        });
        vpcPFDetails.list();
    },
    'getTierVMList' : function(data) {
              
        if (dijit.byId("vpcPFIPVMListGrid")) {
            dijit.byId("vpcPFIPVMListGrid").destroyRecursive();
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
        
        var neworkid;
      
        data.store.fetch({query: {id: data.value},
            onItem: function(item) {
                neworkid=item.networkId;
            }
            
        });                 
        dojo.byId("currentNetworkId").value = neworkid;
        if(neworkid) {
           
           var networkVMListRestStore = new JsonRest({
              target: core.getContextPath() + "/api/network/vm/list/"
          });

          networkVMListRestStore.query({networkId: neworkid}).then(function(data) {

              if(data.length == 0 || data == "" || data == "undefined") {
                   dojo.byId("portforwardingNoVMList").style.display = "block";
               } else {
                   dojo.byId("portforwardingNoVMList").style.display = "none";
                   dojo.forEach(data, function(vm) {
                       instanceList.newItem({id: vm.referenceId, name: vm.name, zone: vm.zone, state: vm.state});
                   });
                  var vmGrid = new dojox.grid.EnhancedGrid({
                      id: "vpcPFIPVMListGrid",
                      "class" : "span12",
                      store: instanceList,
                      structure: vmDatalayout,
                      autoHeight: true,                  
                      editable: true,          
                      selectionMode: "single",
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
                          indirectSelection: {headerSelector: false, name: translator.common.select,width:"50px",  styles:"text-align: center;margin-left:15px;"}
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
                  vmGrid.placeAt("userIPPortForwardVMList");
                  vmGrid.startup();
                }
          }); 
        } else {
            dojo.byId("vmListContainer").style.display = "none";
            dojo.byId("addIPButtonDiv").style.display = "none";
                        
            dojo.byId("addVMIPPageButton").style.display="block";                        
            dojo.query("#vpcPFExistList").removeClass("hide_text", true);
        }
        
    },
    'list': function() {
        if (dijit.byId("vpcIpPortForwardingGrid")) {
            dijit.byId("vpcIpPortForwardingGrid").destroyRecursive();
        }
        dojo.byId("vpcIpPortForwardingRuleList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var loadBalancingData = {
            items: []
        };

        var loadBalancingDataList = new ItemFileWriteStore({data: loadBalancingData});
        var loadBalancingDatalayout = [
            [
                {'name': 'ID', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.protocol, 'field': 'protocol', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.privatePort, 'field': 'privatePort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.publicPort, 'field': 'publicPort', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.vm, 'field': 'vm', 'width': '200px', datatype: "string", autoComplete: true, "formatter" : function (data) {
                        var divContainer = "<div class='row-fluid'><label>VM:</label><span>"+data.vmName+"</span></div><div class='row-fluid'><label>IP:</label><span>"+data.ip+"</span></div>"
                        return divContainer;
                }},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {
                        var deleteButton = new dijit.form.Button({
                            "class": "delete_icon",
                            onClick: function() {
                                vpcPFDetails.showDeletePortForwardingDialog(data.id);
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
                dojo.byId("vpcIpPortForwardingRuleList").innerHTML = "";
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
                dojo.byId("vpcIpPortForwardingRuleList").innerHTML = "";                                             
                var dataGrid = new EnhancedGrid({
                    id: "vpcIpPortForwardingGrid",
                    "class" : "span12",
                    store: loadBalancingDataList,
                    structure: loadBalancingDatalayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins  
                });
            dataGrid.placeAt("vpcIpPortForwardingRuleList");
            dataGrid.startup();
            dataGrid.update();
        }
    });
    }

};

var vpcLBDetails = {
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

                        var html = "<a onclick='vpcLBDetails.deleteVmFromLoadBalancerRuleShow(\"" + data + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
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
                    var selectedLbGrid = dijit.byId("vpcIpLoadBalancingGrid");

                    var  currentDataItem = selectedLbGrid.selection.getSelected();
                    dojo.forEach(currentDataItem, function(el) {
                        dijit.byId("loadBalancingAlgorithm").set("value", el.algorithm);
                        dijit.byId("ipLoadBalancingName").setValue(el.name);
                        dijit.byId("loadBalancingPrivatePort").setValue(el.privatePort);
                        dijit.byId("loadBalancingPublicPort").setValue(el.publicPort);
                        dijit.byId("vpcLBTier").set("value", el.tierId);
                    });
                },300);
                dojo.byId("lbRemoveVMListContainer").style.display="block";
                dojo.byId("removeLBAdditionalVMButtonDiv").style.display="block";
                dojo.query("#vpcLBExistContainer").toggleClass("hide_text", true);
                dojo.byId("addLBVMButtonDiv").style.display="none";
            }
        });
        
    }, 
    'deleteVmFromLoadBalancerRuleShow' : function(vmId) {   
    
        dojo.byId("lbCurrentVMId").value = vmId;
        dijit.byId('lbRemoveVMDialog').show();
        
    },
    removeVMFromLb : function() { 
        
        dijit.byId('viewIpLoader').show();

        var networkLoadBalancerRestStore = new JsonRest({
           target: core.getContextPath() + "/api/network/loadBalancing/removeVM"
        });
        networkLoadBalancerRestStore.add({lbId: dojo.byId("currentLoadBalancingId").value, vmId: dojo.byId("lbCurrentVMId").value }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var addLoadBalancingRuleJobStatus = setInterval(function() {
                        vpcLBDetails.removeVMFromLbJob(resultData.jobId, dojo.byId("lbCurrentVMId").value, dojo.byId("currentLoadBalancingId").value, addLoadBalancingRuleJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("viewIpLoader").hide();
                    dijit.byId('lbRemoveVMDialog').hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();    
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
                    dojo.query("#vpcLBExistContainer").removeClass("hide_text", true);
                    dojo.byId("addLBVMButtonDiv").style.display="block";
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    vpcLBDetails.list();
                    
                    dijit.byId('viewIpLoader').hide();  
                    dijit.byId('lbRemoveVMDialog').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(removeVMLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dojo.byId("lbRemoveVMListContainer").style.display="none";
                    dojo.byId("removeLBAdditionalVMButtonDiv").style.display="none";
                    dojo.query("#vpcLBExistContainer").removeClass("hide_text", true);
                    dojo.byId("addLBVMButtonDiv").style.display="block";
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    vpcLBDetails.list();
                    dijit.byId('viewIpLoader').hide();  
                    dijit.byId('lbRemoveVMDialog').hide();
                }
            });
        });
        
    },
    cancelRemoveVMFromLb : function() { 
        dojo.byId("lbRemoveVMListContainer").style.display="none";
        dojo.byId("removeLBAdditionalVMButtonDiv").style.display="none";
        dojo.query("#vpcLBExistContainer").removeClass("hide_text", true);
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
                    var selectedLbGrid = dijit.byId("vpcIpLoadBalancingGrid");

                    var  currentDataItem = selectedLbGrid.selection.getSelected();
                    dojo.forEach(currentDataItem, function(el) {
                        dijit.byId("loadBalancingAlgorithm").set("value", el.algorithm);
                        dijit.byId("ipLoadBalancingName").setValue(el.name);
                        dijit.byId("loadBalancingPrivatePort").setValue(el.privatePort);
                        dijit.byId("loadBalancingPublicPort").setValue(el.publicPort);
                        dijit.byId("vpcLBTier").set("value", el.tierId);
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
                dojo.query("#vpcLBExistContainer").toggleClass("hide_text", true);
                dojo.byId("addLBVMButtonDiv").style.display="none";
            }
        });
    },
    'cancelAddAdditionalVM' : function() { 
        dojo.byId("addLBVMButtonDiv").style.display="block";
        dojo.query("#vpcLBExistContainer").removeClass("hide_text", true);
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

           dijit.byId('viewIpLoader').show();

           var networkLoadBalancerRestStore = new JsonRest({
              target: core.getContextPath() + "/api/network/loadBalancing/addVM"
           });
           networkLoadBalancerRestStore.add({lbId: lbId, vmList: vmListArray.toString()}).then(function(data) {
               dojo.forEach(data, function(resultData) {
                   if (resultData.result === "OK") {
                       var addLoadBalancingRuleJobStatus = setInterval(function() {
                           vpcLBDetails.addLoadBalancingToVMJob(resultData.jobId, vmListArray.toString(), lbId,addLoadBalancingRuleJobStatus);
                       }, 3000);
                   } else if (resultData.result === "FAILED") {
                       registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                       registry.byId("userToaster").show();
                       dijit.byId("viewIpLoader").hide();
                   } else {
                       registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                       registry.byId("userToaster").show();
                       dijit.byId('viewIpLoader').hide();                    
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
                    dojo.query("#vpcLBExistContainer").removeClass("hide_text", true);
                    dojo.byId("addLBVMButtonDiv").style.display="block";
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    vpcLBDetails.list();
                    
                    dijit.byId('viewIpLoader').hide();        
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(addLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    dijit.byId('viewIpLoader').hide();        
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
                    vpcLBDetails.list();
                    registry.byId("userToaster").setContent(translator.common.firewall.ruleDeleteSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();
                }
            });
        });
    },
    'deleteLoadBalancerRuleShow': function(id) {
        dojo.byId("currentLBId").value = id;
        dijit.byId('vpcDeleteLBDialog').show();
    },
    'deleteLoadBalancerRule': function() {

        var networkRuleRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/remove/"
        });

        dijit.byId('viewIpLoader').show();
        dijit.byId('vpcDeleteLBDialog').hide();

        networkRuleRestStore.add({lbId: dojo.byId("currentLBId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var deleteLoadBalancingRuleJobStatus = setInterval(function() {
                        vpcLBDetails.deleteLoadBalancingRuleJob(resultData.jobId, dojo.byId("currentLBId").value,deleteLoadBalancingRuleJobStatus);
                    }, 3000);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("viewIpLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();
                }
            });
        });
    },
    'deleteLoadBalancerRuleClose': function() {
        dijit.byId('vpcDeleteLBDialog').hide();
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
        var selectedGridItems = dijit.byId('vpcLBIPVMListGrid').selection.getSelected();   
//        alert(selectedGridItems.length)
        if (selectedGridItems.length == 0) {         
//            dojo.query("#LBVMRequireMsg").removeClass("hide_text", true);
            dojo.query("#vpcLBVMRequireMsg").removeClass("hide_text", true);  
        } else {            
//            dojo.query("#LBVMRequireMsg").toggleClass("hide_text", true);
            dojo.query("#vpcLBVMRequireMsg").toggleClass("hide_text", true);  
            dojo.forEach(selectedGridItems, function(selectedItem, index) {    
                    dojo.forEach(selectedItem.id, function (el) {                        
                        vmListArray[index] = el;
                    });
                    });
                dijit.byId('viewIpLoader').show();
                var networkLoadBalancerRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/network/loadBalancing/add"
                });
                networkLoadBalancerRestStore.add({ipId: currentIPId.value, networkId: networkId.value, algorithm: algorithm.value, name: name.value, privatePort: privatePort.value, publicPort: publicPort.value, vmList: vmListArray.toString()}).then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        if (resultData.result === "OK") {
                            var addLoadBalancingRuleJobStatus = setInterval(function() {
                                vpcLBDetails.addLoadBalancingRuleJob(resultData.jobId, addLoadBalancingRuleJobStatus);
                            }, 3000);
                        } else if (resultData.result === "FAILED") {
                            registry.byId("userToaster").setContent(resultData.message, "error");
                            registry.byId("userToaster").show();
                            dijit.byId("viewIpLoader").hide();
                        } else {
                            registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                            registry.byId("userToaster").show();
                            dijit.byId('viewIpLoader').hide();                    
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
                    dijit.byId('viewIpLoader').hide();                                        
                   
                    dojo.byId("vpcLBVMContainer").style.display="none";                           
                    dojo.query("#vpcLBExistContainer").removeClass("hide_text", true); 
//                    dojo.query("#LBVMRequireMsg").toggleClass("hide_text", true);
                    dojo.query("#vpcLBVMRequireMsg").toggleClass("hide_text", true);
//                    dojo.byId("addLBButtonDiv").style.display="none";
                                     
                     vpcLBDetails.list();
                     setTimeout(function () {
                         viewVpcIp.populateValues(dojo.byId("currentIPId").value);
                    },800);
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(addLoadBalancingRuleJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('userNetworkIPLoadBalancingAddForm').reset();
                    dijit.byId('viewIpLoader').hide();
                }
            });
        });
    },
    'showLBVMList' : function() {
        
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
        } else {
            dojo.byId("vpcLBVMContainer").style.display="block";          
            dojo.byId("vpcAddLBButtonDiv").style.display = "block";
            dojo.byId("vpcLBExistContainer").style.display = "none";
            dojo.byId("addLBVMButtonDiv").style.display = "none";
            dijit.byId('vpcLBIPVMListGrid').render();        
        }
    },
    'getTierVMList' : function(data) {
              
        if (dijit.byId("vpcLBIPVMListGrid")) {
            dijit.byId("vpcLBIPVMListGrid").destroyRecursive();
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
        
         var neworkid;
      
        data.store.fetch({query: {id: data.value},
            onItem: function(item) {
                neworkid=item.networkId;
            }
            
        });                 
        dojo.byId("currentNetworkId").value = neworkid;
        if(neworkid) {
           
//            dojo.byId("vpcLBVMContainer").style.display="none";          
//            dojo.byId("vpcAddLBButtonDiv").style.display = "none";
//            dojo.byId("vpcLBExistContainer").style.display = "none";
                       
           var networkVMListRestStore = new JsonRest({
              target: core.getContextPath() + "/api/network/vm/list/"
          });

          networkVMListRestStore.query({networkId: neworkid}).then(function(data) {

              if(data.length == 0 || data == "" || data == "undefined") {
                   dojo.byId("vpcLBNoVMList").style.display = "block";
               } else {
                   dojo.byId("vpcLBNoVMList").style.display = "none";
                   dojo.forEach(data, function(vm) {
                       instanceList.newItem({id: vm.referenceId, name: vm.name, zone: vm.zone, state: vm.state});
                   });
                  var vmGrid = new dojox.grid.EnhancedGrid({
                      id: "vpcLBIPVMListGrid",
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
                  vmGrid.placeAt("vpcLBVMList");
                  vmGrid.startup();
                }
          }); 
          if(dijit.byId('vpcLBIPVMListGrid')) {
             dijit.byId('vpcLBIPVMListGrid').render();   
          }
        } else {
            dojo.byId("vpcLBVMContainer").style.display = "none";
            dojo.byId("vpcAddLBButtonDiv").style.display = "none";
            dojo.byId("vpcLBExistContainer").style.display = "block";
        }
        
    },
    'cancelVMGrid' : function() {
        
        dijit.byId("userNetworkIPLoadBalancingAddForm").reset();
        dojo.byId("vpcLBExistContainer").style.display = "block";
        dojo.byId("addLBVMButtonDiv").style.display = "block";
    },
    'list': function() {   
        dojo.query("#vpcLBExistContainer").removeClass("hide_text", true);
        dojo.byId("vpcLBExistContainer").style.display = "block";
        dojo.byId("addLBVMButtonDiv").style.display = "block";
        dijit.byId("userNetworkIPLoadBalancingAddForm").reset();
        dojo.byId("vpcLBVMContainer").style.display = "none";
        dojo.byId("vpcAddLBButtonDiv").style.display = "none";       
        dojo.byId("lbRemoveVMListContainer").style.display = "none";
        dojo.byId("removeLBAdditionalVMButtonDiv").style.display = "none";
        
        dojo.byId("vpcIpLoadBalancingList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var loadBalancingData = {
            items: []
        };

        var loadBalancingDataList = new ItemFileWriteStore({data: loadBalancingData});
        var loadBalancingDatalayout = [
            [
                {'name': 'ID', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '10%', datatype: "string", autoComplete: true},
                {'name': translator.common.algorithm, 'field': 'algorithm', 'width': '10%', datatype: "string", autoComplete: true},
                {'name': translator.common.privatePort, 'field': 'privatePort', 'width': '10%', datatype: "string", autoComplete: true},
                {'name': translator.common.publicPort, 'field': 'publicPort', 'width': '10%', datatype: "string", autoComplete: true},
                {'name': translator.common.vmList, 'field': 'vm', 'width': '30%', datatype: "string", autoComplete: true},
                {'name': 'tierId', 'field': 'tierId', 'hidden': 'true'},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {

                        var html = "<a onclick='vpcLBDetails.editLBShow(\"" + data.id + "," + data.name + "," + data.algorithm + "\")' title='" + translator.common.edit + "'><img src='images/edit.png'></img></a>" +
                                "<a onclick='vpcLBDetails.showAddLoadBalancingAdditionalVM(\"" + data.id + "\")' class='offset1' title='" + translator.common.addVM + "'><img src='images/add_vm_icon.png'></img></a></li>"+
                                "<a onclick='vpcLBDetails.showRemoveLoadBalancingVM(\"" + data.id + "\")' class='offset1' title='" + translator.common.removeVM + "'><img src='images/delete_vm_icon.png'></img></a></li>"+
                                "<a onclick='vpcLBDetails.deleteLoadBalancerRuleShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
  

                        return html;
                    }, 'width': '20%', datatype: "string", autoComplete: true}
            ]
        ];

        var ipId = dojo.byId("currentIPId").value;

        var networkEgressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/loadBalancing/list/"
        });

        networkEgressRestStore.get(ipId).then(function(data) {
            if (!data || data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("vpcIpLoadBalancingList").innerHTML = "";
                dojo.byId("noLoadBalancingMessageBox").style.display = "block";
            } else {
                if (dijit.byId("vpcIpLoadBalancingGrid")) {
                    dijit.byId("vpcIpLoadBalancingGrid").destroyRecursive();
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
                        tierId: resultData.networkId,
                        action: {id: resultData.referenceId, name: resultData.name, algorithm: resultData.algorithm}
                    });
                });
                dojo.byId("vpcIpLoadBalancingList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: "vpcIpLoadBalancingGrid",
                    class: "span12",
                    store: loadBalancingDataList,
                    structure: loadBalancingDatalayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins              
                });
                dataGrid.placeAt("vpcIpLoadBalancingList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },  
    'populateValues' : function() {
        dojo.query("#vpcLBExistContainer").removeClass("hide_text", true);
        dojo.byId("vpcLBExistContainer").style.display = "block";
        dojo.byId("addLBVMButtonDiv").style.display = "block";
        
        if (dijit.byId("vpcLBIPVMListGrid")) {
            dijit.byId("vpcLBIPVMListGrid").destroyRecursive();
        }
        
        dijit.byId("userNetworkIPLoadBalancingAddForm").reset();
        dojo.byId("vpcLBVMContainer").style.display = "none";
        dojo.byId("vpcAddLBButtonDiv").style.display = "none";      
        
        dojo.byId("lbRemoveVMListContainer").style.display = "none";
        dojo.byId("removeLBAdditionalVMButtonDiv").style.display = "none";
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/list/tier"
        });
        
         var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
         networkRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function(data) {
            dojo.forEach(data, function(tier) {
                if(tier.tierType === "WEB") {
                    tierList.newItem({
                        id: tier.referenceId,
                        name: tier.name,
                        networkId : tier.id
                    });
                }
                
            });
            dijit.byId("vpcLBTier").reset();
            dijit.byId("vpcLBTier").set("store", tierList);
            
        });
        vpcLBDetails.list();
    },
    'editLBShow': function(data) {

        var LBData = data.split(",");

        dojo.byId("currentLBId").value = LBData[0];

        dijit.byId('ipLoadBalancingEditName').setValue(LBData[1]);
        dijit.byId('loadBalancingEditAlgorithm').set("value", LBData[2]);
        dijit.byId('networkEditLBDialog').show();
    },
    'closeEditLB': function() {
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
            dijit.byId('viewIpLoader').show();
            networkRuleRestStore.put({id: dojo.byId("currentLBId").value, name: dijit.byId('ipLoadBalancingEditName').value, algorithm: dijit.byId('loadBalancingEditAlgorithm').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.ruleUpdateSuccess, "message");
                        registry.byId("userToaster").show();
                        vpcLBDetails.list();
                        dijit.byId('networkEditLBDialog').hide();
                        dijit.byId('viewIpLoader').hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('viewIpLoader').hide();
                    }
                });
            });
        }
    },
  
    'init': function() {
        
        
        var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
        var tierWidget = new FilteringSelect({
            id: "vpcLBTier",
            store: tierList,
            placeHolder: translator.common.message.selectTier,
            onChange: function() {
                vpcLBDetails.getTierVMList(this);
            }
        });
        tierWidget.placeAt("vpcLBTierList");
        tierWidget.startup();
        
    }
};



var viewVpcIp = {
    'enableStaticNat': function() {

        var vmId = dijit.byId("vpcIPPageTierVm");
        var networkId = dijit.byId("vpcIpPageTier");

        var pageNode = dojo.byId("vpcIPPageEnableStaticNatFormPage");
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

            dijit.byId('viewIpLoader').show();
            dijit.byId("vpcIPPageEnableStaticNatDialog").hide();

            var ipEnableStaticRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/ip/enableStaticNat"
            });

            ipEnableStaticRestStore.add({vmId: vmId.value, networkId: networkId.value, ipId: dojo.byId('currentIPId').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var enableStaticNatJobStatus = setInterval(function() {
                            viewVpcIp.enableStaticNatJob(resultData.jobId, enableStaticNatJobStatus);
                        }, 3000);
                    } else if (resultData.result === "true") {
                        registry.byId("userToaster").setContent(translator.common.message.enableStaticNatSuccess, "message");
                        registry.byId("userToaster").show();
                        dijit.byId('viewIpLoader').hide();
                        
                        viewVpcIp.populateValues(dojo.byId("currentIPId").value);
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("viewIpLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('viewIpLoader').hide();

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
                    dijit.byId('viewIpLoader').hide();
                    
                    viewVpcIp.populateValues(dojo.byId("currentIPId").value);
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(enableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();
                }
            });
        });
    },
    'enableStaticNatShow' : function() {
              
        var id = dojo.byId('currentIPId').value;
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/list/tier"
        });
        
         var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
         networkRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function(data) {
            dojo.forEach(data, function(tier) {
                if(tier.tierType === "WEB" || tier.tierType === "DB") {
                    tierList.newItem({
                        id: tier.referenceId,
                        name: tier.name,
                        networkId : tier.id
                    });
                }
            });
            dijit.byId("vpcIpPageTier").reset();
            dijit.byId("vpcIpPageTier").set("store", tierList);
            
            dijit.byId("vpcIPPageEnableStaticNatDialog").show();
            dijit.byId("vpcIPPageEnableStaticNatForm").reset();
            
            
        });
    },
    'closeEnableStaticNat' : function() {
         
        dijit.byId("vpcIPPageEnableStaticNatDialog").hide();
    },
    'disableStaticNatIPShow': function() {
         
        dijit.byId("vpcIPPageDisableStaticNatDialog").show();
    },
    'closeDisableStaticNat': function() {
        dijit.byId("vpcIPPageDisableStaticNatDialog").hide();
    },
    'disableStaticNat': function() {
        dijit.byId('viewIpLoader').show();
        dijit.byId("vpcIPPageDisableStaticNatDialog").hide();

        var ipEnableStaticRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/disableStaticNat"
        });

        ipEnableStaticRestStore.add({ipId: dojo.byId('currentIPId').value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var disableStaticNatJobStatus = setInterval(function() {
                        viewVpcIp.disableStaticNatJob(resultData.jobId, disableStaticNatJobStatus);
                    }, 3000);
                } else if (resultData.result === "true") {
                    registry.byId("userToaster").setContent(translator.common.message.disableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();
                    viewVpcIp.populateValues(dojo.byId("currentIPId").value);
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("viewIpLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();

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
                    dijit.byId('viewIpLoader').hide();
                    viewVpcIp.populateValues(dojo.byId("currentIPId").value);
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(disableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('viewIpLoader').hide();
                }
            });
        });
    },
    'getTierVMList' : function(data) {
              
      var neworkid = "";
      
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
        if(neworkid ||  neworkid !== "undefined" || neworkid !== "" || neworkid !== null ) {
            networkVMListRestStore.query({networkId: neworkid}).then(function(data) {

                dojo.forEach(data, function(vm, index) {
                    vmList.newItem({
                        id: vm.referenceId,
                        name: vm.name
                    });
                    if (index === 0) {
                        firstValue = vm.referenceId;
                    }
                });
                dijit.byId("vpcIPPageTierVm").reset();
                dijit.byId("vpcIPPageTierVm").set("store", vmList);
                dijit.byId("vpcIPPageTierVm").set("value", firstValue);
            });
        }
    },
    'init': function() {
        var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
        var tierWidget = new FilteringSelect({
            id: "vpcIpPageTier",
            store: tierList,
            placeHolder: translator.common.message.selectTier,
            onChange: function() {
                viewVpcIp.getTierVMList(this);
            }
        });
        tierWidget.placeAt("vpcIpPageTierList");
        tierWidget.startup();
        
        var tierVMOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierVMList = new ItemFileWriteStore({data: tierVMOptions});  
        
        var tierVMWidget = new FilteringSelect({
            id: "vpcIPPageTierVm",
            store: tierVMList,
            placeHolder: translator.common.message.selectInstance,
            onChange: function() {
//                viewVpcIp.getTierVMList(this.value);
            }
        });
        tierVMWidget.placeAt("vpcIPPageTierVmList");
        tierVMWidget.startup();
        
    },
    
  'populateValues': function(id) {
        
        dojo.byId("currentIPId").value = id;
               
        
        var ip = dojo.byId("ipAddress");
        var ipTop = dojo.byId("ipAddressTop");
        var network = dojo.byId("ipNetworkName");
        var networkTop = dojo.byId("ipNetworkNameTop");
        
        
        var zone = dojo.byId("ipZoneName");
        var networkReferenceId = dojo.byId("networkReferenceId");
        var ipAddressReferenceId = dojo.byId("ipAddressReferenceId");
       
        var sourceNat = dojo.byId("ipSourceNat");
        var staticNat = dojo.byId("ipStaticeNat");

        var vlanName = dojo.byId("ipVLan");
        var state = dojo.byId("ipState");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/get/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("currentVpcNameLink").href = "#/user/vpc/view/" + resultData.vpcId;
                dojo.byId("currentIpAddress").innerHTML = resultData.ip;
                dojo.byId("currentVpcName").innerHTML = resultData.vpcName;
                                             
                dojo.byId("currentVpcId").value = resultData.vpcId;
                dojo.byId("vpcIPAddressLink").href = "#/user/vpc/view/" + resultData.vpcId;
                dojo.byId("staticNatVMLink").href = "#/user/cloud/vmDetail/" + resultData.vmId+",tier";
                dojo.byId("currentNetworkId").value = resultData.networkId;
                dojo.byId("currentNetworkReferenceId").value = resultData.networkReferenceId;
                ip.innerHTML = resultData.ip;
                ipTop.innerHTML = resultData.ip;
                networkTop.innerHTML = resultData.vpcName;
                network.innerHTML = resultData.vpcName;
                zone.innerHTML = resultData.zone;
               
                networkReferenceId.innerHTML = resultData.vpcId;
                vlanName.innerHTML = resultData.vlanName;
                ipAddressReferenceId.innerHTML = resultData.id;
                state.innerHTML = resultData.state;
                staticNat.innerHTML = (resultData.isStaticNat === "true" || resultData.isStaticNat === true) ? "Yes" : "No";
                sourceNat.innerHTML = (resultData.isSourceNat === "true" || resultData.isSourceNat === true) ? "Yes" : "No";
                                
                if(resultData.isSourceNat === true || resultData.isSourceNat === "true") {
                    var mainTab = dijit.byId("vpcIpDetailsTabCointainer"); 
                    var subPortTab = dijit.byId("vpcIpPortForwarding");
                    var subLoadTab = dijit.byId("vpcIpLoadBalancing");
                    mainTab.removeChild(subPortTab); 
                    mainTab.removeChild(subLoadTab); 
                    subLoadTab.destroyRecursive();
                    subPortTab.destroyRecursive();
                     dojo.byId("staticNatEnabled").style.display = "none";
                     dojo.byId("ipOtheActionInfo").style.display = "none";
                     dojo.byId("staticNatVmName").innerHTML = "";
                    
                } else if(resultData.isSourceNat === false || resultData.isSourceNat === "false") {
                    
                    if(resultData.isStaticNat === true || resultData.isStaticNat === "true") {
                        var mainTab = dijit.byId("vpcIpDetailsTabCointainer"); 
                        var subPortTab = dijit.byId("vpcIpPortForwarding");
                        var subLoadTab = dijit.byId("vpcIpLoadBalancing");
                        mainTab.removeChild(subPortTab); 
                        mainTab.removeChild(subLoadTab); 
                        subLoadTab.destroyRecursive();
                        subPortTab.destroyRecursive();
                        dojo.byId("ipOtheActionInfo").style.display = "block";
                        dojo.byId("staticNatEnabled").style.display = "block";
                        dojo.byId("ipEnableStaticNat").style.display = "none";
                        dojo.byId("ipDisableStaticNat").style.display = "block";
                        dojo.byId("staticNatVmName").innerHTML = resultData.vmDisplayName;
                        
                    } else {
                        var mainTab = dijit.byId("vpcIpDetailsTabCointainer"); 
                        var subPortTab = dijit.byId("vpcIpPortForwarding");
                        var subLoadTab = dijit.byId("vpcIpLoadBalancing");
                        
                        if(resultData.isVPCLBAdded === true || resultData.isVPCPFAdded === true) {
                            dojo.byId("ipEnableStaticNat").style.display = "none";
                            dojo.byId("ipDisableStaticNat").style.display = "none";
                            dojo.byId("ipOtheActionInfo").style.display = "none";
                            dojo.byId("staticNatEnabled").style.display = "none";
                            dojo.byId("staticNatVmName").innerHTML = "";
                        } else {
                            dojo.byId("ipEnableStaticNat").style.display = "block";
                            dojo.byId("ipDisableStaticNat").style.display = "none";
                            dojo.byId("ipOtheActionInfo").style.display = "block";
                            dojo.byId("staticNatEnabled").style.display = "none";
                            dojo.byId("staticNatVmName").innerHTML = "";
                        }
                        
                        if(resultData.isVPCLBAdded === true || resultData.isVPCLBAdded === "true") {
                             mainTab.removeChild(subPortTab); 
                             subPortTab.destroyRecursive();
                        }
                        if(resultData.isVPCPFAdded === true || resultData.isVPCPFAdded === "true") {
                            mainTab.removeChild(subLoadTab);  
                            subLoadTab.destroyRecursive();
                        }
                    }
                }
            });
        });
        viewVpcIp.init();
    },
    'loadNetworkVmGrid': function(networkId) {
        
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

        networkVMListRestStore.query({networkId: networkId}).then(function(data) {            
                if(data.length == 0 || data == "" || data == "undefined") {
                    dojo.byId("tierNoVMList").style.display = "block";
                } else {
                    dojo.forEach(data, function(vm) {
                        instanceList.newItem({id: vm.referenceId, name: vm.name, zone: vm.zone, state: vm.state});
                    });
                    var vmGrid = new dojox.grid.EnhancedGrid({
                        id: "tierVMListGrid",
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

                vmGrid.placeAt("tierLBVMList");
                vmGrid.startup();
            }
        });   
    }
};

var VPCTierInfo = {
    'cancelEditTier' : function() {
        dijit.byId("vpcEditTierDialog").hide();
    },
    'showEdit' : function(data) {
                     
        dojo.byId("editNetworkId").value = data.id;        
        dijit.byId("tierEditDesc").setValue(data.description);
        dijit.byId("tierEditName").setValue(data.name);
        dijit.byId("tierEditNWDomain").setValue(data.networkDomain);
               
        
        var networkOfferOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var vpnCustomerGatewayOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var networkFileStoreList = new ItemFileWriteStore({data: networkOfferOptions});

        var networkOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer/forVpc/"
        });
        if(data.tierType === "WEB") {
            networkFileStoreList.newItem({id: data.networkOfferId, name: data.networkOffer, tierType: data.tierType});
        }
        
        networkOfferRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function(nmdata) {
            if (!nmdata || nmdata.length === 0) {
                networkFileStoreList.newItem({id: "", name: translator.common.noNetworkOffer, tierType: ""});
            } else {
                dojo.forEach(nmdata, function(resultData) {
                    if(data.tierType === "APP" || data.tierType === "WEB") {
                        if(resultData.tierType === "DB" || resultData.referenceId === data.networkOfferId) {
                            networkFileStoreList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});
                        }
                    } else if(data.tierType === "DB") {
                        networkFileStoreList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});
                    }
                });
                dijit.byId("tierEditNetworkOffering").setStore(networkFileStoreList);
                dijit.byId("tierEditNetworkOffering").startup();
                dijit.byId("tierEditNetworkOffering").set("value", data.networkOfferId);
            }
        });
        
        
        
        dijit.byId("vpcEditTierDialog").show();
    }, 
    'editTierShow' : function() {
        dijit.byId("tierEditConformDialog").show();
        dijit.byId("cirdchange").reset();
    },
    'closEditTierConform' : function() {
        dijit.byId("tierEditConformDialog").hide();
    },
    'editTier': function() {

        var networkId = dojo.byId("editNetworkId");
        var name = dijit.byId("tierEditName");
        var desc = dijit.byId("tierEditDesc");
        var networkOffer = dijit.byId("tierEditNetworkOffering");
        var NWDomain = dijit.byId("tierEditNWDomain");
        var cirdchange = dijit.byId("cirdchange");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/tier/edit/"
        });
        dijit.byId("vpcEditTierDialog").hide();
        dijit.byId("tierEditConformDialog").hide();
        dijit.byId("vpcLoader").show();
        networkRestStore.put({id: networkId.value, name: name.value, desc: desc.value, networkOffer:networkOffer.value, NWDomain:NWDomain.value, cirdchange:cirdchange.checked}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcEditJobStat = setInterval(function(){VPCTierInfo.vpcEditJob(resultData.jobId, vpcEditJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide();
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
                    dijit.byId("vpcLoader").hide();
                     ViewVpc.populateTierValues();
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
                         var vpcDeleteJobStat = setInterval(function(){VPCTierInfo.deleteJobTier(resultData.jobId, resultData.tierId,vpcDeleteJobStat);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
                    }
                });
            });
        
    },
    'restartTier': function() {
        
        
        var currentTierId = dojo.byId("currentTierId").value;
        var cleanup = dijit.byId("cleanup");
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/tier/restart"
        });
        
        dijit.byId("vpcLoader").show();
        dijit.byId("tierRestartDialog").hide();

        networkRestStore.add({tierId:currentTierId, cleanup:cleanup.checked}).then(function(data) {
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

var VPCNetworkACLInfo = {
    showAddACLDialog : function () {
        VPCNetworkACLInfo.loadVpcData();
        dijit.byId("vpcNetworkAclDescription").reset();        
        var vpc = dijit.byId("vpcNetworkAclName").reset();
        dijit.byId("createAclConnectionPage").show();        
    },
    cancel : function () {
        dijit.byId("createAclConnectionPage").hide();        
    },
    'add': function() {
        var name = dijit.byId("vpcNetworkAclName").value;
        var desc = dijit.byId("vpcNetworkAclDescription").value;        
        var vpc = dijit.byId("vpcNwAclWidget").value;
       
        var pageNode = dojo.byId("createNetworkAclPage");
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
                target: core.getContextPath() + "/api/networkAcl"
            });
            
            dijit.byId("vpcLoader").show();
            
            networkRestStore.add({name: name, desc: desc, vpcId: vpc}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var networkAclCreateJobStat = setInterval(function(){VPCNetworkACLInfo.createNetworkAclJob(resultData.jobId, networkAclCreateJobStat);},1000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
                    }
                });
            });
        }
    },
    createNetworkAclJob : function (jobId, networkAclCreateJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job/"
        });         
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(networkAclCreateJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.networkAclCreated, "message");
                    registry.byId("userToaster").show();      
                    VPCNetworkACLInfo.populateValues();
                    dijit.byId("vpcLoader").hide();
                    dijit.byId("createAclConnectionPage").hide();        
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(networkAclCreateJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("vpcLoader").hide();
                } else {
                    clearInterval(networkAclCreateJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide(); 
                } 
            });
        });
    },
    loadVpcData : function () {        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        dojo.query("#vpcNetworkAclVpcList").toggleClass("hide_text", true);
        dojo.query("#vpcNetworkAclVPCLoader").removeClass("hide_text", true);
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc"
        });  
        vpcRestResource.query({referenceId: dojo.byId("currentVpcId").value}).then(function (data) {
            dojo.forEach(data, function (el) {
                vpcList.newItem({id: el.referenceId, name: el.name});  
            }); 
            dijit.byId("vpcNwAclWidget").setStore(vpcList);   
            dojo.query("#vpcNetworkAclVpcList").removeClass("hide_text", true);
            dojo.query("#vpcNetworkAclVPCLoader").toggleClass("hide_text", true);
        });
    },
    'showDeleteAcl' : function(currentId) {
        dojo.byId("vpcNetworkAclId").value = currentId;
        dijit.byId("vpcNetworkAclDeleteDialog").show();        
    },
    'deleteAclClose' : function() {        
        dijit.byId("vpcNetworkAclDeleteDialog").hide();        
    },
    'deleteAcl': function() {
        var currentId = dojo.byId("vpcNetworkAclId").value;
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkAcl/delete"
        });

        dijit.byId("vpcLoader").show();
        dijit.byId("vpcNetworkAclDeleteDialog").hide();

        networkRestStore.add({aclId:currentId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var deleteNWAclJobStat = setInterval(function(){VPCNetworkACLInfo.deleteJobVpc(resultData.jobId, deleteNWAclJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide();
                }
            });
        });
    },
    'deleteJobVpc' : function(jobId, deleteNWAclJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job/"
        });         
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.networkAclDeleted, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    VPCNetworkACLInfo.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("vpcLoader").hide();
                } else {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide(); 
                } 
            });
        });
    },
    populateValues : function () {
        if(dijit.byId("vpcNetworkAclGrid")) {                                    
            dijit.byId("vpcNetworkAclGrid").destroyRecursive();                    
        }
        if(dijit.byId("vpcNwAclWidget")) {
            dijit.byId("vpcNwAclWidget").destroyRecursive();
        }
        dojo.byId("vpcNetworkAclListGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vpcGridData = {
            items: []
        }; 
        var networkACLList = new ItemFileWriteStore({data: vpcGridData}); 
        var aclLayout = [
            [               
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.aclName, 'field': 'aclName', 'width': '150px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                   var html = ""
                    if(data.vpcName) {
                        html= "<a href='#/user/vpcSecurity/view/" +data.aclId+ "'>"+data.name+"</a>"
                        return html;
                    } 
                        html= data.name;
                        return html;
                    }
                }
                ,    
                {'name': translator.common.zone, 'field': 'zone', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.vpcName, 'field': 'vpcName', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.description, 'field': 'description', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.tiers, 'field': 'tiers','width': '100px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.action, 'field': 'action','width': '100%', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        if(data.vpcName) {
                            var html = "<a onclick='VPCNetworkACLInfo.showDeleteAcl(\"" + data.aclId + "\")' title="+translator.common.deleteData+" class='span2'><img  src='images/vm_delete_icon.png'></a>"
                            return html;
                        }
                       
                    }
                },
                
             ]
         ];
        var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl"
        });        
        var currentVPC = dojo.byId("currentVpcId").value;
                              
        aclRestStore.query({vpcId: currentVPC}).then(function(data) {
            if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noVPCNetworkAclMsgBox").style.display = "block";
                 dojo.byId("vpcNetworkAclListGrid").innerHTML = "";
             } else {                 
                 dojo.forEach(data, function(aclData) {
                     networkACLList.newItem({
                         id:aclData.referenceId,
                         zone: aclData.zone,
                         vpcName:aclData.vpc,
                         description: aclData.description, 
                         aclName: {vpcName:aclData.vpc, name:aclData.name, aclId: aclData.referenceId},
                         tiers: aclData.tiers,                                          
                         action : {vpcName:aclData.vpc, aclId: aclData.referenceId}
                     });
                 });
                 dojo.byId("vpcNetworkAclListGrid").innerHTML = "";
                 var vpcGrid = new EnhancedGrid({
                     id: 'vpcNetworkAclGrid',
                    "class" : "span12",
                    store: networkACLList,
                    structure: aclLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                vpcGrid.placeAt("vpcNetworkAclListGrid");
                vpcGrid.startup(); 
                dojo.byId("noVPCNetworkAclMsgBox").style.display = "none";
            }             
        }); 
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        var vpcWidget = new Select({
            id: "vpcNwAclWidget",            
            store: vpcList,  
            sortByLabel: false,
            autoHeight: true                        
        }).placeAt("vpcNetworkAclVpcList"); 
    }
    
};
var ViewVpc = {
    
    'enableStaticNat': function() {

        var vmId = dijit.byId("vpcTierVm");
        var networkId = dijit.byId("vpcTier");

        var pageNode = dojo.byId("vpcIpEnableStaticNatFormPage");
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

            dijit.byId('vpcLoader').show();
            dijit.byId("vpcIpEnableStaticNatDialog").hide();

            var ipEnableStaticRestStore = new JsonRest({
                target: core.getContextPath() + "/api/network/ip/enableStaticNat"
            });

            ipEnableStaticRestStore.add({vmId: vmId.value, networkId: networkId.value, ipId: dojo.byId('currentEnableStaticNatIpId').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var enableStaticNatJobStatus = setInterval(function() {
                            ViewVpc.enableStaticNatJob(resultData.jobId, enableStaticNatJobStatus);
                        }, 3000);
                    } else if (resultData.result === "true") {
                        registry.byId("userToaster").setContent(translator.common.message.enableStaticNatSuccess, "message");
                        registry.byId("userToaster").show();
                        dijit.byId('vpcLoader').hide();
                        
                        ViewVpc.populateIPListValues();
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('vpcLoader').hide();

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
                    dijit.byId('vpcLoader').hide();
                    
                    ViewVpc.populateIPListValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(enableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    },
    'disableStaticNatIPShow': function(id) {
         dojo.byId('currentEnableStaticNatIpId').value= id;
        dijit.byId("vpcIPDisableStaticNatDialog").show();
    },
    'closeDisableStaticNat': function() {
        dijit.byId("vpcIPDisableStaticNatDialog").hide();
    },
    'disableStaticNat': function() {
        dijit.byId('vpcLoader').show();
        dijit.byId("vpcIPDisableStaticNatDialog").hide();

        var ipEnableStaticRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/disableStaticNat"
        });

        ipEnableStaticRestStore.add({ipId: dojo.byId('currentEnableStaticNatIpId').value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var disableStaticNatJobStatus = setInterval(function() {
                        ViewVpc.disableStaticNatJob(resultData.jobId, disableStaticNatJobStatus);
                    }, 3000);
                } else if (resultData.result === "true") {
                    registry.byId("userToaster").setContent(translator.common.message.disableStaticNatSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                    ViewVpc.populateIPListValues();
                } else if (resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();

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
                    dijit.byId('vpcLoader').hide();
                    ViewVpc.populateIPListValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(disableStaticNatJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    },
    'enableStaticNatShow' : function(id) {
              
        dojo.byId('currentEnableStaticNatIpId').value= id;
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/list/tier"
        });
        
         var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
         networkRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function(data) {
            dojo.forEach(data, function(tier) {
                if(tier.tierType === "WEB" || tier.tierType === "DB") {
                    tierList.newItem({
                        id: tier.referenceId,
                        name: tier.name,
                        networkId : tier.id
                    });
                }
            });
            dijit.byId("vpcTier").reset();
            dijit.byId("vpcTier").set("store", tierList);
            
            dijit.byId("vpcIpEnableStaticNatDialog").show();
            dijit.byId("vpcIpEnableStaticNatForm").reset();
            
            
        });
    },
       
    'populateTierValues': function() {
        dojo.byId("userTierList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '10%', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {

                        return "<a href='#/user/tier/view/" + data.id + "' title='" + translator.common.view + "'>" + data.name + "</a>";
                    }},
//                {'name': translator.common.zone, 'field': 'zone', 'width': '10%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '10%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '10%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.type, 'field': 'type', 'width': '10%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '10%', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.supportedService, 'field': 'supportedService', 'width': '20%', 'datatype': 'string', 'autoComplete': 'true'},
//                 {'name': translator.common.cost, 'field': 'cost', 'width': '10%', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
//                         var html = "<span class='fog_cost'>"+ data + "</span>"
//                        return html;
//                 }},
                {'name': translator.common.action, 'field': 'action',                                        
                    'formatter': function(data) {                                                                        
                         var vpnStatus = new FogPanel.VPNStatus({
                           onAclVPN : function () {ListTierInfo.replaceACLTierShow(data.referenceId);},
                           onRebootVPN : function () {
                               dijit.byId("cleanup").set("checked", false);
                               VPCTierInfo.showRestart(data.referenceId);},
                           onEditVPN : function () {VPCTierInfo.showEdit(data);},
                           onDeleteVPN : function () {VPCTierInfo.showDelete(data.referenceId);},    
                           forTier : true,
                           aclStat : true,
                           changeAcl : translator.common.changeAcl
                           
                       });
                       return vpnStatus;
                    }, 'width': '20%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];
      
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/list/tier"
        });
        networkRestStore.query({vpcId: dojo.byId("currentVpcId").value}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userTierList").innerHTML = "";
                dojo.byId("noTierMessageBox").style.display = "block";
            } else {
                dojo.byId("noTierMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: {id: resultData.id, name: resultData.name},
//                        zone: resultData.zone,
                        cidr: resultData.cidr,
                        state: resultData.state,
                        gateway: resultData.gateway,
                        type: resultData.tierType,
                        supportedService: resultData.supportedService,
//                            cost:  resultData.cost,
                        action: resultData
                    });
                });
                dojo.byId("userTierList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userTierList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    showCondition : function () {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/public/termsAndCondition"
        });                  
        dojo.style(dijit.byId("vpckAquireIPContent").closeButtonNode,"display","none");
        configRestStore.query().then(function(resultData) {
            dojo.byId("vpcInfoContentArea").innerHTML = resultData.value;                   
        });
        dijit.byId("vpckAquireIPContent").show();
    },
    cancelConditionBox : function () {
            dijit.byId("vpckAquireIPContent").hide();
    },
    'acquireIPShow': function() {
        dijit.byId("ipVpcInfoAgreement").set("checked", false);
        dojo.byId("conditionExceptionMsg").style.display = "none";
        dijit.byId("vpcAcquireIpDialog").show();
    },
    'closeAcquireIPShow': function() {
        dijit.byId("vpcAcquireIpDialog").hide();
    },
    'releaseIPShow': function(id) {

        dojo.byId("currentIPId").value = id;

        dijit.byId("vpcReleaseIpDialog").show();
    },
    'closeReleaseIPShow': function() {
        dijit.byId("vpcReleaseIpDialog").hide();
    },
    'releaseIP': function() {

        var ipReleaseRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/ip/release"
        });

        dijit.byId('vpcLoader').show();
        dijit.byId('vpcReleaseIpDialog').hide();

        ipReleaseRestStore.add({ipId: dojo.byId("currentIPId").value}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result === "OK") {
                    var releaseIPJobStatus = setInterval(function() {
                        ViewVpc.releaseIPJob(resultData.jobId, releaseIPJobStatus);
                    }, 3000);
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();

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
                    registry.byId("userToaster").setContent(translator.common.message.releaseVpcIPSuccess, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                    ViewVpc.populateIPListValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(releaseIPJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    },
    'acquireIp': function() {


        var vpcId = dojo.byId("currentVpcId").value;

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/ip/acquire"
        });
        var agrementChecked =  dijit.byId("ipVpcInfoAgreement").checked;
//        alert(agrementChecked)
        if(agrementChecked == true) {                     
            
            dojo.byId("conditionExceptionMsg").style.display = "none";
            dijit.byId('vpcLoader').show();
            dijit.byId('vpcAcquireIpDialog').hide();
            networkRestStore.add({vpcId: vpcId}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        var acquireIpJobStatus = setInterval(function() {
                            ViewVpc.acquireJob(resultData.jobId, acquireIpJobStatus, resultData.vpcId);
                        }, 3000);
                    } else if (resultData.result === "HASFIRSTIP") {
                        registry.byId("userToaster").setContent(translator.common.message.networkHasFirstIPMessage, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('vpcLoader').hide();
                    } else if (resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('vpcLoader').hide();
                    }
                });
            });
        } else {
            dojo.byId("conditionExceptionMsg").style.display = "block";
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
                    dijit.byId('vpcLoader').hide();
                    ViewVpc.populateIPListValues();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(acquireIpJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    },
    'populateIPListValues': function() {
        
        var vpcId = dojo.byId("currentVpcId").value;

        dojo.byId("vpcIpList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
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
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
                       
                        if (data.isSourceNat === false || data.isSourceNat === "false") {
                            if (data.isStaticNat === false || data.isStaticNat === "false") {
                                if(data.isVPCLBAdded === true || data.isVPCPFAdded === true) {
                                    html = "<a onclick='ViewVpc.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>";
                                } else {
                                   html = "<a onclick='ViewVpc.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>" +
                                        "<a onclick='ViewVpc.enableStaticNatShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.enableStaticNat + "'><img style='width: 17px; height: 17px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>"; 
                                }
                                
                            } else {
                                html = "<a onclick='ViewVpc.releaseIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'></img></a></li>" +
                                "<a onclick='ViewVpc.disableStaticNatIPShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.disableStaticNat + "'><img style='width: 17px; height: 17px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img></a></li>";
                            } 
                        } 
                        return html;
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];


        var networkIPAddressRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/ip/list/"
        });
        networkIPAddressRestStore.query({vpcId:vpcId}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("vpcIpList").innerHTML = "";
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
                            isEnabledVPN: resultData.isEnabledVPN,
                            isVPCLBAdded: resultData.isVPCLBAdded,
                            isVPCPFAdded: resultData.isVPCPFAdded,
                        }
                    });
                });
                dojo.byId("vpcIpList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("vpcIpList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    
    'showIpTab': function() {
        
        setTimeout(function () {
            var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr
            var subIpTab = dijit.byId("vpcIpAddressTab"); //tab Id which you want to show
            mainTab.selectChild(subIpTab);   
        },800);
    },
    'showTierTab': function() {
        
        setTimeout(function () {
            var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr
            var subIpTab = dijit.byId("vpcTierContainer"); //tab Id which you want to show
            mainTab.selectChild(subIpTab);   
        },800);
    },    
    
    'showDeleteVpc' : function() {        
        dijit.byId("vpcInfoDeleteDialog").show();
    },
    'closeDeleteVpc' : function() {
        dijit.byId("vpcInfoDeleteDialog").hide();
    },
    'showRestartVpc' : function() {        
        dijit.byId("vpcInfoRestartDialog").show();
    },
    'closeRestartVpc' : function() {
        dijit.byId("vpcInfoRestartDialog").hide();
    },
    'deleteVpc': function() {        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/delete"
        });
        
        var currentId = dojo.byId("currentVpcId").value;
        dijit.byId("vpcLoader").show();
        dijit.byId("vpcInfoDeleteDialog").hide();

        networkRestStore.add({vpcId:currentId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcDeleteJobStat = setInterval(function(){ViewVpc.deleteJobVpc(resultData.jobId, vpcDeleteJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide();
                }
            });
        });        
    },    
    'restartVpc': function() {

        var currentId = dojo.byId("currentVpcId").value;
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/restart"
        });
        
        dijit.byId("vpcLoader").show();
        dijit.byId("vpcInfoRestartDialog").hide();

        networkRestStore.add({vpcId:currentId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcRestartJobStat = setInterval(function(){ViewVpc.restartJobVpc(resultData.jobId, vpcRestartJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide();
                }
            });
        });
    },
    'deleteJobVpc' : function(jobId, vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/delete/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpcDeleted, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    core.router.go("#/user/vpc/list");
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
                    dijit.byId("vpcLoader").hide();
                    ViewVpc.init(dojo.byId("currentVpcId").value);
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
    },
    'showVPCEdit' : function() {
        dijit.byId("vpcInfoListEditDialog").show();                           
    },
    'cancelVPCEdit' : function() {
        dijit.byId("vpcInfoListEditDialog").hide();
    },
    'editVpcInfo': function() {           
        var pageNode = dojo.byId("vpcInfoEditPageDiv");
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
        if (wigetState === true) {
            var networkRuleRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/edit/"
            });
            dijit.byId('vpcLoader').show();
            networkRuleRestStore.put({id: dojo.byId("currentVpcId").value, name: dijit.byId('editVpcInfoName').value, description: dijit.byId('editVpcInfoDescription').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.vpcUpdateSuccess, "message");
                        registry.byId("userToaster").show();                        
                        dijit.byId('vpcInfoListEditDialog').hide();
                        dijit.byId('vpcLoader').hide();
                        ViewVpc.init(dojo.byId("currentVpcId").value);
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('vpcLoader').hide();
                    }
                });
            });
        } 
    },
    'init':  function (id) {                        
        
        dojo.byId("currentVpcId").value = id;
        var networkOfferOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var vpnCustomerGatewayOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var vpnCustomerGatewayStoreList = new ItemFileWriteStore({data: vpnCustomerGatewayOptions});
        if(dijit.byId("vpnCustomerGateway")) {
            dijit.byId("vpnCustomerGateway").destroyRecursive();
        }        
        if(dijit.byId("tierNetworkOffering")) {
            dijit.byId("tierNetworkOffering").destroyRecursive();
        }
        
        if(dijit.byId("tierAcl")) {
            dijit.byId("tierAcl").destroyRecursive();
        }
        
        if(dijit.byId("tierReplaceAcl")) {
            dijit.byId("tierReplaceAcl").destroyRecursive();
        }
        
        if(dijit.byId("vpcTierVm")) {
            dijit.byId("vpcTierVm").destroyRecursive();
        }
        if(dijit.byId("tierEditNetworkOffering")) {
            dijit.byId("tierEditNetworkOffering").destroyRecursive();
        }
        
        if(dijit.byId("vpcTier")) {
            dijit.byId("vpcTier").destroyRecursive();
        }
        
        
        var vpnCustomerGateway = new FilteringSelect({
            id: "vpnCustomerGateway",
            name: "vpnCustomerGateway",
            store: vpnCustomerGatewayStoreList,
            placeHolder: translator.common.selectVPNCustomer, 
            onChange: function() {

            }
        }, "vpnCustomerGatewayList");
        
        setTimeout(function () {
            ViewVpc.vpcTopology();
        },900);
        
        var networkFileStoreList = new ItemFileWriteStore({data: networkOfferOptions});

        var networkOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkOffer/forVpc/"
        });

        networkOfferRestStore.query({vpcId: id}).then(function(data) {
            if (!data || data.length == 0) {
                networkFileStoreList.newItem({id: "", name: translator.common.noNetworkOffer, tierType: ""});
            } else {
                dojo.forEach(data, function(resultData) {
                    networkFileStoreList.newItem({id: resultData.referenceId, name: resultData.name, tierType: resultData.tierType});
                });
            }
            var networkOfferWidget = new Select({
                id: "tierNetworkOffering",
                name: "tierNetworkOffering",
                store: networkFileStoreList,
                onChange: function() {
                    ViewVpc.showACLList(this);
                }
            }, "networkOfferingList");
                        
            var networkOfferEditWidget = new Select({
                id: "tierEditNetworkOffering",
                name: "tierEditNetworkOffering",
//                store: networkFileStoreList,
                onChange: function() {
                   
                }
            });
            networkOfferEditWidget.placeAt("networkOfferingEditList");
            networkOfferEditWidget.startup();
            
            
        });
        
        var aclOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var aclFileStoreList = new ItemFileWriteStore({data: aclOptions});

       var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/"
        });
       var firstValue;
       aclRestStore.query({vpcId: id}).then(function(data) {
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
            var aclListWidget = new Select({
                id: "tierAcl",
                name: "tierAcl",
                sortByLabel: false,
                store: aclFileStoreList
            }, "tierAclList"); 
            
            
            var aclReplaceListWidget = new Select({
                id: "tierReplaceAcl",
                name: "tierReplaceAcl",
                sortByLabel: false,
                store: aclFileStoreList
            }, "tierReplaceAclList");   
            dijit.byId("tierReplaceAcl").set("value", firstValue);
            
        });
      
      
        var vpcListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc"
        });
    
        vpcListRestStore.query({referenceId:id}).then(function(data) {
            dojo.forEach(data, function(vpcData) {
                 dijit.byId("editVpcInfoName").setValue(vpcData.name);
                 dijit.byId("editVpcInfoDescription").setValue(vpcData.description);   
                 
                 dojo.byId("viewVPCNameTop").innerHTML = vpcData.name;
                 dojo.byId("viewVPCZoneNameTop").innerHTML = vpcData.zone;
                 
                 dojo.byId("vpcName").innerHTML = vpcData.name;
                 dojo.byId("vpcDesc").innerHTML = vpcData.description;
                 dojo.byId("vpcNetworkDomain").innerHTML = vpcData.networkDomain;
                 dojo.byId("vpcZone").innerHTML = vpcData.zone;
                 dojo.byId("vpcCidr").innerHTML = vpcData.cidr;
                 dojo.byId("vpcState").innerHTML = vpcData.state;
                 dojo.byId("vpcRestartRequred").innerHTML = vpcData.vpcRestartRequred = true ? "Yes":"No";
                 dojo.byId("vpcReferenceId").innerHTML = vpcData.referenceId;
                 dojo.byId("currentVpcName").innerHTML = vpcData.name;
                 dijit.byId("editVpcName").setValue(vpcData.name);
                 dijit.byId("editVpcDescription").setValue(vpcData.description);
                 dojo.byId("VPNConnectionCost").innerHTML = "*" + vpcData.currency + " "+ vpcData.cost + "  /" + translator.common.month;

            });
        });
        
        var tierOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierList = new ItemFileWriteStore({data: tierOptions});  
        
        var tierWidget = new FilteringSelect({
            id: "vpcTier",
            store: tierList,
            placeHolder: translator.common.message.selectTier,
            onChange: function() {
                ViewVpc.getTierVMList(this);
            }
        });
        tierWidget.placeAt("vpcTierList");
        tierWidget.startup();
        
        var tierVMOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var tierVMList = new ItemFileWriteStore({data: tierVMOptions});  
        
        var tierVMWidget = new FilteringSelect({
            id: "vpcTierVm",
            store: tierVMList,
            placeHolder: translator.common.message.selectInstance,
            onChange: function() {
//                viewVpcIp.getTierVMList(this.value);
            }
        });
        tierVMWidget.placeAt("vpcTierVmList");
        tierVMWidget.startup();                
    },  
    'vpcTopology' : function() {   
        var id = dojo.byId("currentVpcId").value; 
        var vpcTopologyRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/vpcTopology/"
        });        
        dojo.byId("webTierList").innerHTML = "";
        dojo.byId("appTierList").innerHTML = "";
        dojo.byId("dbTierList").innerHTML = "";     
        
        dojo.query("#vpcTopologyTierMenuList").toggleClass("hide_text", true);                
        dojo.query("#vpcTopologyTierLoader").removeClass("hide_text", true);
        var tierRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/list/tier"
        });
        if(id) {
        tierRestStore.query({vpcId:id}).then(function(tierList) {
            vpcTopologyRestStore.query({vpcId:id}).then(function(data) {
            dojo.forEach(data, function(vpcData) {                
                if(vpcData.name) {                
                    dojo.byId("vpcPrivageGatewayLink").href = "#/user/vpc/view/" + vpcData.referenceId;
                    dojo.byId("vpcPublicIpLink").href = "#/user/vpc/view/" + vpcData.referenceId;
                    dojo.byId("vpcs2sVpnLink").href = "#/user/vpc/view/" + vpcData.referenceId;

                    dojo.byId("publicIp").innerHTML = vpcData.publicIp;
                    dojo.byId("s2sVpn").innerHTML = vpcData.s2svpn;
                    dojo.byId("networkAcl").innerHTML =  vpcData.networkAcl;
                    dojo.byId("privateGateway").innerHTML = vpcData.privateGateway;
                    dojo.byId("vpcCIDRValue").innerHTML = vpcData.cidr;
                    
                
//                 var vpcContentDiv = "<div class='row-fluid'><div class='span12'></div><div class='span12 field-box'><label class='headerLabel'>"+translator.common.privateGateways+":</label>"+                                                                                
//                    "<span>" + vpcData.privateGateway + "</span></div>\n\
//                    <div class='span12 field-box'><label class='headerLabel'>"+translator.common.publicIP+":</label>"+                                        
//                    "<span>" + vpcData.publicIp + "</span></div>\n\
//                    <div class='span12 field-box'><label class='headerLabel'>"+translator.common.s2sVpn+":</label>"+                                        
//                    "<span>" + vpcData.s2svpn + "</span></div>\n\
//                    <div class='span12 field-box'><label class='headerLabel'>"+translator.common.networkAcl+":</label>"+                                        
//                    "<span>" + vpcData.networkAcl + "</span></div></div>";
                if(vpcData.appTier.length === 0 || vpcData.appTier === "" || vpcData.appTier === undefined) {
                    dojo.byId("appTierList").innerHTML = "";
                     dojo.byId("vpcTopoAppTierCount").innerHTML = "(" + vpcData.appTier.length + ")";
                } else {
                     dojo.byId("vpcTopoAppTierCount").innerHTML = "(" + vpcData.appTier.length + ")";
                    dojo.forEach(vpcData.appTier, function (tier, index) {   
                    var appTierSize = vpcData.appTier.length;
                    var tierContent = "";    
                        tierContent = "<div class='row-fluid'>"+ 
                                        "<div class='tiers-info-desc'>"+
                                        "<div class='span12 router-info-boxlst'>" +                                        
                                            "<div class='span6'>" + 
                                            "<a href=#/user/tier/instanceList/"+tier.id+"><div class='info-boxlist-value'>"+tier.vm+"</div><div class='info-boxlist-title'>"+translator.common.virtualMachine+"</div></a>"+
                                            "</div>"+
                                            "<div class='span6'>" + 
                                            "<a href=#/user/tier/viewInternalLB/"+tier.id+"><div class='info-boxlist-value'>"+tier.internalLb+"</div><div class='info-boxlist-title'>"+translator.common.internalLb+"</div></a>"+
                                            "</div></div>"+
                                            "<div class='span12 router-info-boxlstodd'><div class='row-fluid'><div class='ipcont span12'>"+translator.common.cidr+':'+tier.cidr+"</div></div></div>"+
                                        "</div></div>";                                                                                               
                    var tierWidget = new dijit.TitlePane({
                        "class": "",
                        style: "height: auto%; width: 100%;",
                        title: tier.name,
                        content: tierContent,
                        open: true,
                        onShow: function () {                            
//                               CurrentVMNicInfo.onPanOpen(this, el.referenceId);
                        }
                    });
                    var pinNode = document.createElement('div');   
                    pinNode.className = 'tiers-pin';
                    var tierNode = document.createElement('div'); 
                    if(appTierSize === index + 1) {
                        tierNode.className = 'tiers-cont-last row-fluid';
                    } else {
                        tierNode.className = 'tiers-cont row-fluid';
                    }
                    
                    tierNode.appendChild(pinNode);
                    tierNode.appendChild(tierWidget.domNode);
                    dojo.byId("appTierList").appendChild(tierNode); 
                    tierWidget.startup();
                    
                });
                }                
                
                if(vpcData.dbTier.length === 0 || vpcData.dbTier === "" || vpcData.dbTier === undefined) {
                    dojo.byId("dbTierList").innerHTML = "";
                     dojo.byId("vpcTopoDBTierCount").innerHTML = "(" + vpcData.dbTier.length + ")";
                } else {
                    dojo.byId("vpcTopoDBTierCount").innerHTML = "(" + vpcData.dbTier.length + ")";
                    dojo.forEach(vpcData.dbTier, function (tier, index) {                       
                    var tierContent = "";
                    
                    tierContent = "<div class='row-fluid'>"+ 
                                        "<div class='tiers-info-desc'>"+
                                        "<div class='span12 router-info-boxlst'>" +                                                                                    
                                            "<div class='span6'>" + 
                                            "<a href=#/user/tier/staticNatList/"+tier.id+"><div class='info-boxlist-value'>"+tier.staticNat+"</div><div class='info-boxlist-title'>"+translator.common.staticNat+"</div></a>"+
                                            "</div>"+
                                            "<div class='span6'>" + 
                                            "<a href=#/user/tier/portForwardingList/"+tier.id+"><div class='info-boxlist-value'>"+tier.portForwarding+"</div><div class='info-boxlist-title'>"+translator.common.portForwardingIP+"</div></a>"+
                                            "</div></div>"+
                                            "<div class='span12 router-info-boxlstodd'>" + 
                                            "<div class='field_box'>"+
                                            "<a href=#/user/tier/instanceList/"+tier.id+"><div class='info-boxlist-value'>"+tier.vm+"</div><div class='info-boxlist-title'>"+translator.common.virtualMachine+"</div></a>"+
                                            "</div>"+   
                                            "<div class='span12 router-info-boxlstodd'><div class='row-fluid'><div class='ipcont span12'>"+translator.common.cidr+':'+tier.cidr+"</div></div></div>"+
                                        "</div></div>"; 
                    
                                   
                    var tierWidget = new dijit.TitlePane({
                        "class": "",
                        style: "height: auto%; width: 100%;",
                        title: tier.name,
                        content: tierContent,
                        open: true,
                        onShow: function () {                            
//                               CurrentVMNicInfo.onPanOpen(this, el.referenceId);
                        }
                    });
                    var pinNode = document.createElement('div');   
                    pinNode.className = 'tiers-pin';
                    var tierNode = document.createElement('div');  
                    if(vpcData.dbTier.length === index + 1) {
                        tierNode.className = 'tiers-cont-last row-fluid';
                    } else {
                        tierNode.className = 'tiers-cont row-fluid';
                    }
                    tierNode.appendChild(pinNode);
                    tierNode.appendChild(tierWidget.domNode);
                    
                    dojo.byId("dbTierList").appendChild(tierNode); 
                    tierWidget.startup();
                });
                }
                
                if(vpcData.webTier.length === 0 || vpcData.webTier === "" || vpcData.webTier === undefined) {
                    dojo.byId("webTierList").innerHTML = "";
                     dojo.byId("vpcTopoWebTierCount").innerHTML = "(" + vpcData.webTier.length + ")";
                } else {
                    dojo.byId("vpcTopoWebTierCount").innerHTML = "(" + vpcData.webTier.length + ")";
                    dojo.forEach(vpcData.webTier, function (tier, index) {
                        var tierContent = "";                                        
                        tierContent = "<div class='row-fluid'>"+ 
                                        "<div class='tiers-info-desc'>"+
                                        "<div class='span12 router-info-boxlst'>" +                                        
                                            "<div class='span6'>" + 
                                            "<a href=#/user/tier/staticNatList/"+tier.id+"><div class='info-boxlist-value'>"+tier.staticNat+"</div><div class='info-boxlist-title'>"+translator.common.staticNat+"</div></a>"+
                                            "</div>"+
                                            "<div class='span6'>" + 
                                            "<a href=#/user/tier/portForwardingList/"+tier.id+"><div class='info-boxlist-value'>"+tier.portForwarding+"</div><div class='info-boxlist-title'>"+translator.common.portForwardingIP+"</div></a>"+
                                            "</div></div>"+
                                            "<div class='span12 router-info-boxlstodd'><div class='span6'>" + 
                                            "<a href=#/user/tier/instanceList/"+tier.id+"><div class='info-boxlist-value'>"+tier.vm+"</div><div class='info-boxlist-title'>"+translator.common.virtualMachine+"</div></a>"+
                                            "</div>"+
                                            "<div class='span6'>" + 
                                            "<a href=#/user/tier/publicLBIPList/"+tier.id+"><div class='info-boxlist-value'>"+tier.publicLbIp+"</div><div class='info-boxlist-title'>"+translator.common.publicLbIP+"</div></a>"+
                                            "</div> </div>"+
                                            "<div class='span12 router-info-boxlstodd'><div class='row-fluid'><div class='ipcont span12'>"+translator.common.cidr+':'+tier.cidr+"</div></div></div>"+
                                        "</div></div>";                                                                 
                    var tierWidget = new dijit.TitlePane({
                        "class": "",
                        style: "height: auto%; width: 100%;",
                        title: tier.name,
                        content: tierContent,
                        open: true,
                        onShow: function () {                            
//                               CurrentVMNicInfo.onPanOpen(this, el.referenceId);
                        }
                    });
                    var pinNode = document.createElement('div');   
                    pinNode.className = 'tiers-pin';
                    var tierNode = document.createElement('div');  
                    if(vpcData.webTier.length === index + 1) {
                        tierNode.className = 'tiers-cont-last row-fluid';
                    } else {
                        tierNode.className = 'tiers-cont row-fluid';
                    }
                    tierNode.appendChild(pinNode);
                    tierNode.appendChild(tierWidget.domNode);
                    
                    dojo.byId("webTierList").appendChild(tierNode); 
                    tierWidget.startup();
                });
                }                                
            }                
            
            });
             dojo.query("#vpcTopologyTierLoader").toggleClass("hide_text", true);                
            dojo.query("#vpcTopologyTierMenuList").removeClass("hide_text", true);
        });
        });
    } else {
        return ;
    }
  },
  'showIpaddress':function() {
      
    var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr 
    var subIpTab = dijit.byId("vpcIpAddressTab"); //tab Id which you want to show
    mainTab.selectChild(subIpTab);     
  },
  'showPrivateGateway':function() {          
    var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr     
    var subIpTab = dijit.byId("vpcPrivateGatewayTab"); //tab Id which you want to show
    mainTab.selectChild(subIpTab);     
  },
  'shows2sVpn':function() {      
    var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr 
    var subIpTab = dijit.byId("vpcs2sVpnTab"); //tab Id which you want to show
    mainTab.selectChild(subIpTab);     
  },
   showNetworkACL : function () {
       var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr 
        var subACLTab = dijit.byId("vpcNetworkACLTab"); //tab Id which you want to show
        mainTab.selectChild(subACLTab);   
   },
  'showsNetworkAcl':function() {
      
//    var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr 
//    var subIpTab = dijit.byId("vpcs2sVpnTab"); //tab Id which you want to show
//    mainTab.selectChild(subIpTab);     
  },
  
  
  'closeEnableStaticNat' : function() {
      dijit.byId("vpcIpEnableStaticNatDialog").hide();
  },
  'showACLList' : function(data) {
      var tierType;
      
      data.store.fetch({query: {id: data.value},
            onItem: function(item) {
                tierType=item.tierType;
            }
        });  
        if(tierType.toString() === "APP") {
            dojo.byId("aclDiv").style.display = "none";
            dijit.byId("tierAcl").set("value", "");
        } else {
            dojo.byId("aclDiv").style.display = "block";
            dijit.byId("tierAcl").set("value", "");
        }
        
  },
  'getTierVMList' : function(data) {
              
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

            dojo.forEach(data, function(vm, index) {
                vmList.newItem({
                    id: vm.referenceId,
                    name: vm.name
                });
                if (index === 0) {
                    firstValue = vm.referenceId;
                }
            });
            dijit.byId("vpcTierVm").reset();
            dijit.byId("vpcTierVm").set("store", vmList);
            dijit.byId("vpcTierVm").set("value", firstValue);
        });
      
  },
  'addTierShow'  : function() {
      dijit.byId("vpcAddTierDialog").show();
      dijit.byId("vpcAddTierPageForm").reset();
  },
  'replaceACLTierShow'  : function(currentId) {
       dojo.byId("currentTierId").value = currentId;
      dijit.byId("tierReplaceACLDialog").show();
   },
   'closeReplaceAcl'  : function() {
       
        dijit.byId("tierReplaceACLDialog").hide();
   },
   'replaceAclTier': function() {
        
        var currentId = dojo.byId("currentTierId").value;
        var tierReplaceAcl = dijit.byId("tierReplaceAcl");
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkAcl/change/"
        });
        
        dijit.byId("vpcLoader").show();
        dijit.byId("tierReplaceACLDialog").hide();
        
         var pageNode = dojo.byId("repalceAClPage");
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
                         var replaceAclTierJobStat = setInterval(function(){ViewVpc.replaceAclTierJob(resultData.jobId, replaceAclTierJobStat);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
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
                    dijit.byId("vpcLoader").hide();
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
  'addTier': function() {        

        var name = dijit.byId("tierName").value;
        var gateway = dijit.byId("tierGateway").value;
        var networkOffer = dijit.byId("tierNetworkOffering").value;
        var netmask = dijit.byId("tierNetmask").value;
        var acl = dijit.byId("tierAcl").value;


        var pageNode = dojo.byId("vpcAddTierPageDiv");
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

            var tierRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc/tier/create"
            });
            dijit.byId("vpcLoader").show();
            
            tierRestStore.add({netmask: netmask, acl:acl, name: name, gateway: gateway, networkOffer: networkOffer, vpcId: dojo.byId("currentVpcId").value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.tierCreate, "message");
                        registry.byId("userToaster").show();
                         dijit.byId("vpcLoader").hide();
                         dijit.byId("vpcAddTierDialog").hide();
                         ViewVpc.populateTierValues();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                         dijit.byId("vpcLoader").hide();
                    }
                });
            });
        }
    },
  'showEdit' : function() {
      dijit.byId("vpcEditDialog").show();
  },
  'cancelEdit' : function() {
      dijit.byId("vpcEditDialog").hide();
  },
  'cancelTier' : function() {
      dijit.byId("vpcAddTierDialog").hide();
  },
  'editVpc': function() {
        
        var pageNode = dojo.byId("vpcEditPageDiv");
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
                target: core.getContextPath() + "/api/vpc/edit/"
            });
            dijit.byId('vpcLoader').show();
            networkRuleRestStore.put({id: dojo.byId("currentVpcId").value, name: dijit.byId('editVpcName').value, description: dijit.byId('editVpcDescription').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.vpcUpdateSuccess, "message");
                        registry.byId("userToaster").show();
                        ViewVpc.init();
                        dijit.byId('vpcEditDialog').hide();
                        dijit.byId('vpcLoader').hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('vpcLoader').hide();
                    }
                });
            });
        }
    }
};

var ListVpc = {
    populateValues : function () {
        if(dijit.byId("vpcListGrid")) {                                    
            dijit.byId("vpcListGrid").destroyRecursive();                    
        }
        dojo.byId("userVpcList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vpcGridData = {
            items: []
        }; 
        var vpcDataList = new ItemFileWriteStore({data: vpcGridData}); 
        var vpnLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'vpcName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                    var html = "<a href='#/user/vpc/view/"+data.referenceId+"'>" + data.name + "</a>";
                    return html;
                }},
                {'name': translator.common.description, 'field': 'description', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.cidr, 'field': 'cidr','width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state','width': '50px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action','width': '100%', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                       var vpnStatus = new FogPanel.VPNStatus({
                           onConfigVPN : function () {core.router.go("#/user/vpc/view/"+data.referenceId);},
                           onRebootVPN : function () {ListVpc.showRestartVpc(data.referenceId);},
                           onEditVPN : function () {ListVpc.showEdit(data);},
                           onDeleteVPN : function () {ListVpc.showDeleteVpc(data.referenceId);}                           
                       });
                       return vpnStatus;
                    }
                },
                
             ]
         ];
         var vpcListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc"
        }); 
        var currentZoneID = dojo.byId("selectedANZoneID").value?dojo.byId("selectedANZoneID").value:"";   
        var currentMainVPC = ""
        if(dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
             currentMainVPC = ""   
        } else {
             currentMainVPC = dojo.byId("selectedANVPCID").value; 
        }        
        vpcListRestStore.query({zoneReferenceId:currentZoneID, referenceId: currentMainVPC}).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noVpcMessageBox").style.display = "block";
                 dojo.byId("userVpcList").innerHTML = "";
             } else {
                 dojo.byId("noVpcMessageBox").style.display = "none";
                 dojo.forEach(data, function(vpcData) {
                     vpcDataList.newItem({
                         id:vpcData.referenceId,
                         vpcName:{'name':vpcData.name, 'referenceId':vpcData.referenceId},
                         description: vpcData.description, 
                         zone: vpcData.zone,
                         cidr: vpcData.cidr,
                         state: vpcData.state,                     
                         action : {'referenceId':vpcData.referenceId, 'description':vpcData.description, 'name':vpcData.name}
                     });
                 });
                 dojo.byId("userVpcList").innerHTML = "";
                 var vpcDataGrid = new EnhancedGrid({
                     id: 'vpcListGrid',
                    "class" : "span12",
                    store: vpcDataList,
                    structure: vpnLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                vpcDataGrid.placeAt("userVpcList");
                vpcDataGrid.startup(); 
            }             
        });         
    },
    'showDeleteVpc' : function(currentId) {
        dojo.byId("currentVpcId").value = currentId;
        dijit.byId("vpcDeleteDialog").show();
    },
    'closeDeleteVpc' : function() {
        dijit.byId("vpcDeleteDialog").hide();
    },
    'showRestartVpc' : function(currentId) {
        dojo.byId("currentVpcId").value = currentId;
        dijit.byId("vpcRestartDialog").show();
    },
    'closeRestartVpc' : function() {
        dijit.byId("vpcRestartDialog").hide();
    },
    'deleteVpc': function() {
        var currentId = dojo.byId("currentVpcId").value;
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/delete"
        });

        dijit.byId("vpcLoader").show();
        dijit.byId("vpcDeleteDialog").hide();

        networkRestStore.add({vpcId:currentId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var vpcDeleteJobStat = setInterval(function(){ListVpc.deleteJobVpc(resultData.jobId, vpcDeleteJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide();
                }
            });
        });        
    },    
    'restartVpc': function() {

        var currentId = dojo.byId("currentVpcId").value;
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/restart"
        });
        
        dijit.byId("vpcLoader").show();
        dijit.byId("vpcRestartDialog").hide();

        networkRestStore.add({vpcId:currentId}).then(function(data) {
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
    'deleteJobVpc' : function(jobId, vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/delete/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpcDeleted, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    ListVpc.populateValues();
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
                    dijit.byId("vpcLoader").hide();
                    core.router.go("#/user/vpc/list");
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
    },
    'showEdit' : function(vpcData) {
        dijit.byId("vpcListEditDialog").show();
        
        dojo.byId("currentVpcId").value = vpcData.referenceId;
        dijit.byId("editListVpcName").setValue(vpcData.name);
        dijit.byId("editListVpcDescription").setValue(vpcData.description);
      
    },
    'cancelEdit' : function() {
        dijit.byId("vpcListEditDialog").hide();
    },
    'editVpc': function() {
        
        var pageNode = dojo.byId("vpcListEditPageDiv");
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
                target: core.getContextPath() + "/api/vpc/edit/"
            });
            dijit.byId('vpcLoader').show();
            networkRuleRestStore.put({id: dojo.byId("currentVpcId").value, name: dijit.byId('editListVpcName').value, description: dijit.byId('editListVpcDescription').value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.vpcUpdateSuccess, "message");
                        registry.byId("userToaster").show();
                        ListVpc.populateValues();
                        dijit.byId('vpcListEditDialog').hide();
                        dijit.byId('vpcLoader').hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId('vpcLoader').hide();
                    }
                });
            });
        }
    }
};

var AddVpc = {
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
            id: "vpcZone",
            name: "vpcZone",
            store: zoneFileStoreList,
            onChange: function() {
                
            }
        }, "vpcZoneList");

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
            dijit.byId("vpcZone").set("store", zoneList);
            dijit.byId("vpcZone").set("value", firstZone);        
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
                dijit.byId("vpcZone").set("store", zoneList);
                dijit.byId("vpcZone").set("value", panelZone);
                zoneWidget.startup();
                zoneWidget.setAttribute("disabled", true);
            });
        }
        
        var vpcOfferingListStore = new JsonRest({
            target: core.getContextPath() + "/api/vpc/vpcOffering/"
        });

        var vpcOfferingOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var vpcOfferingFileStoreList = new ItemFileWriteStore({data: vpcOfferingOptions});
        
        var vpcOfferingWidget = new Select({
            id: "vpcOffering",
            name: "vpcOffering",
            store: vpcOfferingFileStoreList,
            onChange: function() {

                }
            }, "vpcOfferList");
                
        vpcOfferingListStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                vpcOfferingFileStoreList.newItem({id: resultData.referenceId, name: resultData.name});
            });
            vpcOfferingWidget.startup();
        });
    },
    'cancel': function() {
        core.router.go("#/user/vpc/list");
    },
    gotoVPCDashboard : function () {
        dijit.byId("vpcLoader").hide(); 
        core.router.go("#/user/vpc/dashboard");
    },
    gotoVPCList : function () {
        dijit.byId("vpcLoader").hide();   
        core.router.go("#/user/vpc/list");
    },
    'add': function() {
        var name = dijit.byId("vpcName").value;
        var desc = dijit.byId("vpcDescription").value;
        var zone = dijit.byId("vpcZone").value;
        var vpcOffering = dijit.byId("vpcOffering").value;
        var vpcCidr = dijit.byId("vpcCidr").value;
        var networkDomain = dijit.byId("vpcDomain").value;
        var pageNode = dojo.byId("createVpcPage");
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
                target: core.getContextPath() + "/api/vpc"
            });            
        dijit.byId("vpcLoader").show(); 
            networkRestStore.add({zone: zone, name: name, desc: desc, cidr: vpcCidr, vpcOffering:vpcOffering,networkDomain:networkDomain}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                        var vpcCreateJobStat = setInterval(function(){AddVpc.createVPCJob(resultData.jobId, vpcCreateJobStat);},2000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
                    }
                });
            });
        }
    },
    createVPCJob : function (jobId, vpcCreateUserJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc/create/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcCreateUserJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.vpcCreated, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    core.router.go("#/user/vpc/list");
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcCreateUserJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("vpcLoader").hide();
                } else {
                    clearInterval(vpcCreateUserJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcLoader").hide(); 
                } 
            });
        });
    }
};

var VPCMenuInfo = {
    populateCounts : function () {                
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var vpcStatRestResource = new JsonRest({
                target: core.getContextPath()+"/api/vpc/stat"
        });
        var currentVPC = dojo.byId("selectedANVPCID").value;        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                       
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                vpcStatRestResource.query().then(function (data) {                    
                    if(data[0].vpc === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                        dojo.byId("vpcTotalCountNumber").innerHTML = 0;   
                        dojo.byId("vpcTopoTotalCountNumber").innerHTML = 0;   
                        
                        dojo.byId("vpcTierMenuCount").innerHTML = 0;
                        dojo.byId("vpcMenuVMCount").innerHTML = 0;                    
                        dojo.byId("vpcMenuPublicIPAddr").innerHTML = 0;
                        dojo.byId("vpcMenuStaicNat").innerHTML = 0;    

                        dojo.byId("vpcMenuInternetLB").innerHTML = 0;    
                        dojo.byId("vpcMenuPublicLBCount").innerHTML = 0; 
                        dojo.byId("vpcPrivateGatewayCount").innerHTML = 0; 
                        
                    } else {
                        dojo.byId("vpcTotalCountNumber").innerHTML = data[0].vpc;  
                        dojo.byId("vpcTopoTotalCountNumber").innerHTML = data[0].vpc;
                        dojo.byId("vpcTierMenuCount").innerHTML = data[0].tier;
                        dojo.byId("vpcMenuVMCount").innerHTML = data[0].vpcInstance;                    
                        dojo.byId("vpcMenuPublicIPAddr").innerHTML = data[0].publicIP;
                        dojo.byId("vpcMenuStaicNat").innerHTML = data[0].staticNat;    

                        dojo.byId("vpcMenuInternetLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcMenuPublicLBCount").innerHTML = data[0].publicLB; 
                        dojo.byId("vpcPrivateGatewayCount").innerHTML = data[0].vpcPrivateGw; 
                    } 
                                                               
                });
            } else {                
                vpcStatRestResource.query({vpcId:currentVPC}).then(function (data) {     
                    if(data[0].vpc === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {                        
                        dojo.byId("vpcTotalCountNumber").innerHTML = 0;   
                        dojo.byId("vpcTopoTotalCountNumber").innerHTML = 0;
                        dojo.byId("vpcTierMenuCount").innerHTML = 0;
                        dojo.byId("vpcMenuVMCount").innerHTML = 0;                    
                        dojo.byId("vpcMenuPublicIPAddr").innerHTML = 0;
                        dojo.byId("vpcMenuStaicNat").innerHTML = 0;    

                        dojo.byId("vpcMenuInternetLB").innerHTML = 0;    
                        dojo.byId("vpcMenuPublicLBCount").innerHTML = 0; 
                        dojo.byId("vpcPrivateGatewayCount").innerHTML = 0; 
                    } else {
                        dojo.byId("vpcTotalCountNumber").innerHTML = data[0].vpc;   
                        dojo.byId("vpcTopoTotalCountNumber").innerHTML = data[0].vpc;
                        dojo.byId("vpcTierMenuCount").innerHTML = data[0].tier;
                        dojo.byId("vpcMenuVMCount").innerHTML = data[0].vpcInstance;                    
                        dojo.byId("vpcMenuPublicIPAddr").innerHTML = data[0].publicIP;
                        dojo.byId("vpcMenuStaicNat").innerHTML = data[0].staticNat; 

                        dojo.byId("vpcMenuInternetLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcMenuPublicLBCount").innerHTML = data[0].publicLB;    
                        dojo.byId("vpcPrivateGatewayCount").innerHTML = data[0].vpcPrivateGw; 
                }
                });
            }
        } else {                                                            
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID}).then(function (data) {
                    
                    if(data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                        dojo.byId("vpcTotalCountNumber").innerHTML = 0;   
                        dojo.byId("vpcTierMenuCount").innerHTML = 0;
                        dojo.byId("vpcTopoTotalCountNumber").innerHTML = 0;
                        dojo.byId("vpcMenuVMCount").innerHTML = 0;                    
                        dojo.byId("vpcMenuPublicIPAddr").innerHTML = 0;
                        dojo.byId("vpcMenuStaicNat").innerHTML = 0;    

                        dojo.byId("vpcMenuInternetLB").innerHTML = 0;    
                        dojo.byId("vpcMenuPublicLBCount").innerHTML = 0; 
                        dojo.byId("vpcPrivateGatewayCount").innerHTML = 0; 
                    } else {
                    
                    dojo.byId("vpcTotalCountNumber").innerHTML = data[0].vpc;  
                    dojo.byId("vpcTopoTotalCountNumber").innerHTML = data[0].vpc;
//                    dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                    dojo.byId("vpcTierMenuCount").innerHTML = data[0].tier;
                    dojo.byId("vpcMenuVMCount").innerHTML = data[0].vpcInstance;
                    
                    dojo.byId("vpcMenuPublicIPAddr").innerHTML = data[0].publicIP;
                    dojo.byId("vpcMenuStaicNat").innerHTML = data[0].staticNat;   
                    
                    dojo.byId("vpcMenuInternetLB").innerHTML = data[0].internalLB;    
                    dojo.byId("vpcMenuPublicLBCount").innerHTML = data[0].publicLB;  
                    dojo.byId("vpcPrivateGatewayCount").innerHTML = data[0].vpcPrivateGw; 
                }
                });
            } else {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID, vpcId:currentVPC}).then(function (data) {   
                    if(data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                        dojo.byId("vpcTotalCountNumber").innerHTML = 0;   
                        dojo.byId("vpcTierMenuCount").innerHTML = 0;
                        dojo.byId("vpcMenuVMCount").innerHTML = 0;      
                        dojo.byId("vpcTopoTotalCountNumber").innerHTML = 0;
                        dojo.byId("vpcMenuPublicIPAddr").innerHTML = 0;
                        dojo.byId("vpcMenuStaicNat").innerHTML = 0;    

                        dojo.byId("vpcMenuInternetLB").innerHTML = 0;    
                        dojo.byId("vpcMenuPublicLBCount").innerHTML = 0; 
                        dojo.byId("vpcPrivateGatewayCount").innerHTML = 0; 
                    } else {
                        dojo.byId("vpcTotalCountNumber").innerHTML = data[0].vpc;   
                        dojo.byId("vpcTopoTotalCountNumber").innerHTML = data[0].vpc;
    //                    dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTierMenuCount").innerHTML = data[0].tier;
                        dojo.byId("vpcMenuVMCount").innerHTML = data[0].vpcInstance;
                        dojo.byId("vpcMenuPublicIPAddr").innerHTML = data[0].publicIP;
                        dojo.byId("vpcMenuStaicNat").innerHTML = data[0].staticNat;     

                        dojo.byId("vpcMenuInternetLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcMenuPublicLBCount").innerHTML = data[0].publicLB;    
                        dojo.byId("vpcPrivateGatewayCount").innerHTML = data[0].vpcPrivateGw; 
                }
                });
            } 
        }        
    },
    init :  function () { 
        var accountListStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"
        });        
        accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) { 
                dojo.byId("usageCost").innerHTML = LocaleNumber.format(el.daily.toFixed(2));
                dojo.byId("usageCurrency").innerHTML = el.currency ;
                
                dojo.byId("usagePeriod").innerHTML = el.dailyDate;
                dojo.byId("dueCurrency").innerHTML = el.currency;
                dojo.byId("currentDue").innerHTML =  LocaleNumber.format(el.currentDue.toFixed(2));
                
                dojo.byId("Payments").innerHTML = LocaleNumber.format(el.payments.toFixed(2));
                dojo.byId("paymentCurrency").innerHTML = el.currency;
                
                dojo.byId("PaymentPeriod").innerHTML = el.paymentPeriod;
                dojo.byId("currentDuePeriod").innerHTML = el.paymentPeriod;
                
                if(el.accountType === "TRIAL") {
                    dojo.byId("creditLimitDiv").style.display = "block";
                    dojo.byId("creditLimitSpan").innerHTML = el.creditLimit;
                }                                                                                
            });
        });                
        
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var vpcStatRestResource = new JsonRest({
                target: core.getContextPath()+"/api/vpc/stat"
        });
        var currentVPC = dojo.byId("selectedANVPCID").value;        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {
            dojo.byId("vpnDashboardZoneInfoText").innerHTML = "";
            dojo.byId("vpcDataCenter").innerHTML = "";                                    
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                vpcStatRestResource.query().then(function (data) {  
                    if(data[0].vpc === undefined || data === "undefined" || data === "" || data.length === 0 || data[0] === "undefined") { 
                        dojo.byId("vpcCount").innerHTML = 0; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = 0;
                        dojo.byId("vpcTotalTierCount").innerHTML = 0;
                        dojo.byId("vpcTotalVMCount").innerHTML = 0;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = 0;
                        dojo.byId("vpcStaticNatCount").innerHTML = 0;    
                        dojo.byId("vpcInternalLB").innerHTML = 0;    
                        dojo.byId("vpcPublicLB").innerHTML = 0;  
                        dojo.byId("vpcVPNConnection").innerHTML = 0;
                        dojo.byId("vpcVpnGateWay").innerHTML = 0;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = 0;
                        dojo.byId("vpcCustomerGateway").innerHTML = 0;
                        
                    } else {
                        dojo.byId("vpcCount").innerHTML = data[0].vpc; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTotalTierCount").innerHTML = data[0].tier;
                        dojo.byId("vpcTotalVMCount").innerHTML = data[0].vpcInstance;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = data[0].publicIP;
                        dojo.byId("vpcStaticNatCount").innerHTML = data[0].staticNat;    
                        dojo.byId("vpcInternalLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcPublicLB").innerHTML = data[0].publicLB;  
                        dojo.byId("vpcVPNConnection").innerHTML = data[0].vpnConnection;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].vpnConnection;;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = data[0].vpcPrivateGw;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].s2sGateway;
                        dojo.byId("vpcCustomerGateway").innerHTML = data[0].s2sCustomerGateway;                        
                    }
                });
            } else {                    
                vpcStatRestResource.query({vpcId:currentVPC}).then(function (data) {                                            
                    if(data[0].vpc === undefined || data === "undefined" || data === "" || data.length === 0 || data[0] === "undefined") { 
                        dojo.byId("vpcCount").innerHTML = 0; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = 0;
                        dojo.byId("vpcTotalTierCount").innerHTML = 0;
                        dojo.byId("vpcTotalVMCount").innerHTML = 0;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = 0;
                        dojo.byId("vpcStaticNatCount").innerHTML = 0;    
                        dojo.byId("vpcInternalLB").innerHTML = 0;    
                        dojo.byId("vpcPublicLB").innerHTML = 0;  
                        dojo.byId("vpcVPNConnection").innerHTML = 0;
                        dojo.byId("vpcVpnGateWay").innerHTML = 0;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = 0;
                        dojo.byId("vpcCustomerGateway").innerHTML = 0;     
                    } else {
                        dojo.byId("vpcCount").innerHTML = data[0].vpc; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTotalTierCount").innerHTML = data[0].tier;
                        dojo.byId("vpcTotalVMCount").innerHTML = data[0].vpcInstance;
                        dojo.byId("vpcPublicIPCount").innerHTML = data[0].publicIP;
                        dojo.byId("vpcStaticNatCount").innerHTML = data[0].staticNat;     
                        dojo.byId("vpcInternalLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcPublicLB").innerHTML = data[0].publicLB; 
                        dojo.byId("vpcVPNConnection").innerHTML = data[0].vpnConnection;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = data[0].vpcPrivateGw;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].s2sGateway;
                        dojo.byId("vpcCustomerGateway").innerHTML = data[0].s2sCustomerGateway;     
                    }
                });
            }
        } else {            
            var zoneResource = new JsonRest({
                target: core.getContextPath()+"/api/zone/"
            });              
            zoneResource.get(currentZoneID).then(function (data) {
                dojo.byId("vpnDashboardZoneInfoText").innerHTML = translator.common.intheData;
                dojo.byId("vpcDataCenter").innerHTML =  data.aliasName               
            });             
            
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID}).then(function (data) {
                    if(data[0].vpc === undefined || data === "undefined" || data === "" || data.length === 0 || data[0] === "undefined") { 
                        dojo.byId("vpcCount").innerHTML = 0; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = 0;
                        dojo.byId("vpcTotalTierCount").innerHTML = 0;
                        dojo.byId("vpcTotalVMCount").innerHTML = 0;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = 0;
                        dojo.byId("vpcStaticNatCount").innerHTML = 0;    
                        dojo.byId("vpcInternalLB").innerHTML = 0;    
                        dojo.byId("vpcPublicLB").innerHTML = 0;  
                        dojo.byId("vpcVPNConnection").innerHTML = 0;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = 0;
                        dojo.byId("vpcVpnGateWay").innerHTML = 0;
                        dojo.byId("vpcCustomerGateway").innerHTML = 0;     
                    } else {
                        dojo.byId("vpcCount").innerHTML = data[0].vpc; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTotalTierCount").innerHTML = data[0].tier;
                        dojo.byId("vpcTotalVMCount").innerHTML = data[0].vpcInstance;
                        dojo.byId("vpcPublicIPCount").innerHTML = data[0].publicIP;
                        dojo.byId("vpcStaticNatCount").innerHTML = data[0].staticNat;   
                        dojo.byId("vpcInternalLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcPublicLB").innerHTML = data[0].publicLB;    
                        dojo.byId("vpcVPNConnection").innerHTML = data[0].vpnConnection;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = data[0].vpcPrivateGw;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].s2sGateway;
                        dojo.byId("vpcCustomerGateway").innerHTML = data[0].s2sCustomerGateway;     
                    }
                });
            } else {      
                vpcStatRestResource.query({zoneReferenceId: currentZoneID, vpcId:currentVPC}).then(function (data) {    
                    if(data[0].vpc === undefined || data === "undefined" || data === "" || data.length === 0 || data[0] === "undefined") { 
                        dojo.byId("vpcCount").innerHTML = 0; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = 0;
                        dojo.byId("vpcTotalTierCount").innerHTML = 0;
                        dojo.byId("vpcTotalVMCount").innerHTML = 0;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = 0;
                        dojo.byId("vpcStaticNatCount").innerHTML = 0;    
                        dojo.byId("vpcInternalLB").innerHTML = 0;    
                        dojo.byId("vpcPublicLB").innerHTML = 0; 
                        dojo.byId("vpcVPNConnection").innerHTML = 0;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = 0;
                        dojo.byId("vpcVpnGateWay").innerHTML = 0;
                        dojo.byId("vpcCustomerGateway").innerHTML = 0;     
                    } else {
                        dojo.byId("vpcCount").innerHTML = data[0].vpc; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTotalTierCount").innerHTML = data[0].tier;
                        dojo.byId("vpcTotalVMCount").innerHTML = data[0].vpcInstance;
                        dojo.byId("vpcPublicIPCount").innerHTML = data[0].publicIP;
                        dojo.byId("vpcStaticNatCount").innerHTML = data[0].staticNat;     
                        dojo.byId("vpcInternalLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcPublicLB").innerHTML = data[0].publicLB; 
                        
                        dojo.byId("vpcVPNConnection").innerHTML = data[0].vpnConnection;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = data[0].vpcPrivateGw;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].s2sGateway;
                        dojo.byId("vpcCustomerGateway").innerHTML = data[0].s2sCustomerGateway;     
                    }
                });
            } 
        }
    },
    populateCountOnVPCStat: function () {
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var vpcStatRestResource = new JsonRest({
                target: core.getContextPath()+"/api/vpc/stat"
        });
        var currentVPC = dojo.byId("selectedANVPCID").value;
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {
            dojo.byId("vpcDataCenter").innerHTML = "";
            dojo.byId("vpnDashboardZoneInfoText").innerHTML = "";
            var vpcRestStore = new JsonRest({
                target: core.getContextPath()+"/api/vpc"
            });                             
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                vpcStatRestResource.query().then(function (data) {
                    if(data[0].vpc === "undefined" || data === "undefined" || data === "" || data.length === 0 || data[0] === "undefined") {
                        dojo.byId("vpcCount").innerHTML = 0; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = 0;
                        dojo.byId("vpcTotalTierCount").innerHTML = 0;
                        dojo.byId("vpcTotalVMCount").innerHTML = 0;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = 0;
                        dojo.byId("vpcStaticNatCount").innerHTML = 0;    
                        dojo.byId("vpcInternalLB").innerHTML = 0;    
                        dojo.byId("vpcPublicLB").innerHTML = 0;                          
                        dojo.byId("vpcVPNConnection").innerHTML = 0;
                        dojo.byId("vpcVpnGateWay").innerHTML = 0;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = 0;
                        dojo.byId("vpcCustomerGateway").innerHTML = 0;     
                    } else {
                        dojo.byId("vpcCount").innerHTML = data[0].vpc; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTotalTierCount").innerHTML = data[0].tier;
                        dojo.byId("vpcTotalVMCount").innerHTML = data[0].vpcInstance;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = data[0].publicIP;
                        dojo.byId("vpcStaticNatCount").innerHTML = data[0].staticNat;    
                        dojo.byId("vpcInternalLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcPublicLB").innerHTML = data[0].publicLB;                          
                        dojo.byId("vpcVPNConnection").innerHTML = data[0].vpnConnection;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = data[0].vpcPrivateGw;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].s2sGateway;
                        dojo.byId("vpcCustomerGateway").innerHTML = data[0].s2sCustomerGateway;     
                    }                    
                });
            } else {
                vpcStatRestResource.query({vpcId:currentVPC}).then(function (data) {                    
                    if(data[0].networkAcl == undefined || data === "undefined" || data === "" || data.length === 0 || data[0] === "undefined") {                        
                        dojo.byId("vpcCount").innerHTML = 0; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = 0;
                        dojo.byId("vpcTotalTierCount").innerHTML = 0;
                        dojo.byId("vpcTotalVMCount").innerHTML = 0;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = 0;
                        dojo.byId("vpcStaticNatCount").innerHTML = 0;    
                        dojo.byId("vpcInternalLB").innerHTML = 0;    
                        dojo.byId("vpcPublicLB").innerHTML = 0;                          
                        dojo.byId("vpcCustomerGateway").innerHTML = 0;     
                        
                        dojo.byId("vpcVPNConnection").innerHTML = 0;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = 0;
                        dojo.byId("vpcVpnGateWay").innerHTML = 0;      
//                        return;
                    } else {                        
                        dojo.byId("vpcCount").innerHTML = data[0].vpc; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTotalTierCount").innerHTML = data[0].tier;
                        dojo.byId("vpcTotalVMCount").innerHTML = data[0].vpcInstance;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = data[0].publicIP;
                        dojo.byId("vpcStaticNatCount").innerHTML = data[0].staticNat;    

                        dojo.byId("vpcInternalLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcPublicLB").innerHTML = data[0].publicLB;  
                        
                        dojo.byId("vpcVPNConnection").innerHTML = data[0].vpnConnection;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = data[0].vpcPrivateGw;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].s2sGateway;
                        
                        dojo.byId("vpcCustomerGateway").innerHTML = data[0].s2sCustomerGateway;     
                    } 
                });
            }  
        } else {            
            var zoneResource = new JsonRest({
                target: core.getContextPath()+"/api/zone/"
            });  
            var currentVPC = dojo.byId("selectedANVPCID").value;
            zoneResource.get(currentZoneID).then(function (data) {
                dojo.byId("vpcDataCenter").innerHTML = "" + data.aliasName;     
                dojo.byId("vpnDashboardZoneInfoText").innerHTML = translator.common.intheData;
            });                        
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID}).then(function (data) {
                    if(data[0].vpc === "undefined" ||data === "undefined" || data === "" || data.length === 0 || data[0] === "undefined") {
                        dojo.byId("vpcCount").innerHTML = 0; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = 0;
                        dojo.byId("vpcTotalTierCount").innerHTML = 0;
                        dojo.byId("vpcTotalVMCount").innerHTML = 0;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = 0;
                        dojo.byId("vpcStaticNatCount").innerHTML = 0;    

                        dojo.byId("vpcInternalLB").innerHTML = 0;    
                        dojo.byId("vpcPublicLB").innerHTML = 0;  
                        
                        dojo.byId("vpcVPNConnection").innerHTML = 0;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = 0;
                        dojo.byId("vpcVpnGateWay").innerHTML = 0;
                        dojo.byId("vpcCustomerGateway").innerHTML = 0;     
                    } else {
                        dojo.byId("vpcCount").innerHTML = data[0].vpc; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTotalTierCount").innerHTML = data[0].tier;
                        dojo.byId("vpcTotalVMCount").innerHTML = data[0].vpcInstance;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = data[0].publicIP;
                        dojo.byId("vpcStaticNatCount").innerHTML = data[0].staticNat;    

                        dojo.byId("vpcInternalLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcPublicLB").innerHTML = data[0].publicLB;  
                        
                        dojo.byId("vpcVPNConnection").innerHTML = data[0].vpnConnection;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = data[0].vpcPrivateGw;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].s2sGateway;
                        dojo.byId("vpcCustomerGateway").innerHTML = data[0].s2sCustomerGateway;     
                    } 
                });
            } else {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID, vpcId:currentVPC}).then(function (data) {                    
                    if(data[0].vpc === "undefined" ||data === "undefined" || data === "" || data.length === 0 || data[0] === "undefined") {
                        dojo.byId("vpcCount").innerHTML = 0; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = 0;
                        dojo.byId("vpcTotalTierCount").innerHTML = 0;
                        dojo.byId("vpcTotalVMCount").innerHTML = 0;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = 0;
                        dojo.byId("vpcStaticNatCount").innerHTML = 0;    

                        dojo.byId("vpcInternalLB").innerHTML = 0;    
                        dojo.byId("vpcPublicLB").innerHTML = 0;   
                        
                        dojo.byId("vpcVPNConnection").innerHTML = 0;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = 0;
                        dojo.byId("vpcVpnGateWay").innerHTML = 0;
                        dojo.byId("vpcCustomerGateway").innerHTML = 0;     
                    } else {
                        dojo.byId("vpcCount").innerHTML = data[0].vpc; 
                        dojo.byId("vpcNetworkAclCount").innerHTML = data[0].networkAcl;
                        dojo.byId("vpcTotalTierCount").innerHTML = data[0].tier;
                        dojo.byId("vpcTotalVMCount").innerHTML = data[0].vpcInstance;                    
                        dojo.byId("vpcPublicIPCount").innerHTML = data[0].publicIP;
                        dojo.byId("vpcStaticNatCount").innerHTML = data[0].staticNat;    

                        dojo.byId("vpcInternalLB").innerHTML = data[0].internalLB;    
                        dojo.byId("vpcPublicLB").innerHTML = data[0].publicLB;  
                        
                        dojo.byId("vpcVPNConnection").innerHTML = data[0].vpnConnection;
                        dojo.byId("vpcVpnPrivateGateway").innerHTML = data[0].vpcPrivateGw;
                        dojo.byId("vpcVpnGateWay").innerHTML = data[0].s2sGateway;
                        dojo.byId("vpcCustomerGateway").innerHTML = data[0].s2sCustomerGateway;     
                    }
                });
            } 
        }
    },
    populateValues : function (currentVPC) { 
        var vpcMenuList = [
            {menuItemName: translator.common.menu.home, href: "#/user/vpc/dashboard", iconClass: "icon-home", subMenu: false, dropdownIcon: "", aTagClasses: "singleMenu"},
            {menuItemName: translator.common.vpc.yourVpc, href: "#/user/vpc/vpcContainer", iconClass: "index_title_icons_vpc", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent : [
                    {subMenuItemName : translator.common.topology, href : "#/user/vpc/topology"},
                    {subMenuItemName : translator.common.vpc.vpcs, href : "#/user/vpc/list"},
                    {subMenuItemName : translator.common.vpc.tiers, href : "#/user/tier/list"},
                    {subMenuItemName : translator.common.vpc.instance, href : "#/user/tier/instance"},
                    {subMenuItemName : translator.common.vpc.publicLB, href : "#/user/tier/publicLB"},
                    {subMenuItemName : translator.common.vpc.internalLB, href : "#/user/tier/internalLB"},
                    {subMenuItemName : translator.common.vpc.publicIP, href : "#/user/tier/publicIP"}                    
//                    {subMenuItemName : translator.common.vpc.staticNat, href : "#/user/tier/staticNat"}
                ]
            },                        
            {menuItemName: translator.common.security, href: "#/user/vpcSecurity", iconClass: "icon-bar-chart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.networkACL, href : "#/user/vpcSecurity/listNetworkAcl"}                                                            
                ]
            },                                     
            {menuItemName: translator.common.vpn, href: "#/user/vpnCustomerGateway", iconClass: "srv-submenu-title-vpn", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.vpnCustomerGateway, href : "#/user/vpnCustomerGateway/list"},
//                    {subMenuItemName : translator.common.vpnPrivateGW, href : "#/user/vpc12"},      
                    {subMenuItemName : translator.common.vpnConnection, href : "#/user/vpnCustomerGateway/vpnConnection"}                     
                ]
            },            
            {menuItemName: translator.common.gateway, href: "#/user/gateway", iconClass: "icon-bar-chart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.vpnGateway, href : "#/user/gateway/vpnGateway"},
//                    {subMenuItemName : translator.common.publicGateway, href : "#/user/vpc15"},
                    {subMenuItemName : translator.common.privateGateway, href : "#/user/gateway/privateGateway"}                                                    
                ]
            }
            
//            {menuItemName: translator.common.network, href: "#/user/vpc17", iconClass: "icon-exchange", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
//                submenuContent : [
//                    {subMenuItemName : translator.common.publicNw, href : "#/user/vpc18"},
//                    {subMenuItemName : translator.common.menu.alerts, href : "#/user/vpc19"},
//                    {subMenuItemName : translator.common.menu.notifications, href : "#/user/vpc20"}                        
//                ]
//            }             
        ];  
        var vpcOptions = {            
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name : translator.common.allVPC}]
        };
        var vpcList = new ItemFileWriteStore({data: vpcOptions});
        var vpcListResource = new JsonRest({
            target: core.getContextPath()+"/api/vpc"
        });
        var vpnListSat = true;
        var currentZoneID = dojo.byId("selectedANZoneID").value;           
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {              
            vpcListResource.query().then(function (data) {
                if(data.length === 1 || data.length === 0 || data === "" || data === "undefined") {
                    vpnListSat = false;                    
                }                 
                var vpcId = ""
                if(currentVPC === "All" || currentVPC === "" || currentVPC === "undefined") {
                    dojo.byId("selectedANVPCID").value = "option";
                    vpcId = "option";
                } else {
                    dojo.byId("selectedANVPCID").value = currentVPC;
                    vpcId = currentVPC;
                }                                                
                dojo.forEach(data, function (el) {
                    vpcList.newItem({
                        id:el.referenceId,
                        name:el.name                         
                    });
                });
                
                if(dijit.byId("userVerticalMenuBarWidget")) { 
                    dijit.byId("userVerticalMenuBarWidget").unsubscribe();
                    dijit.byId("userVerticalMenuBarWidget").destroyRecursive();                  
                }
                
                var VerticalMenuBar = new FogPanel.VerticalMenuBar({
                    id : "userVerticalMenuBarWidget",
                    options : true,
                    filterOption : vpnListSat,
                    backtoDashboardLabel :translator.common.backtoDashboard,
                    selectBoxValue : vpcId,
                    onBackButtonclick : function () {
                        dojo.byId("selectedANVPCID").value = "";                        
                        ZoneConfigForMenu.populateValue();
                        core.router.go("#/user/home");},
                    onSelectBoxChange : function (currentVPN) {
                        dojo.byId("selectedANVPCID").value = currentVPN.value; 
                        core.router.go("#/user/vpc");
                            VPCMenuInfo.populateCountOnVPCStat();
                    },
                    selectOptionContent: vpcList
                }).placeAt("userVerticalMenuBar");     
                VerticalMenuBar.populateMenu(vpcMenuList);                         
                VerticalMenuBar.subscribe("/FogPanel/event/route/changed");           
            });
        } else {
            vpcListResource.query({zoneReferenceId: currentZoneID}).then(function (data) {
                if(data.length === 1 || data.length === 0 || data === "" || data === "undefined") {
                    vpnListSat = false;
                }
                var vpcId = ""
                if(currentVPC === "All" || currentVPC === "" || currentVPC === "undefined") {
                    dojo.byId("selectedANVPCID").value = "option";
                    vpcId = "option";
                } else {
                    dojo.byId("selectedANVPCID").value = currentVPC;
                    vpcId = currentVPC;
                }                
                dojo.forEach(data, function (el) {
                    vpcList.newItem({
                        id:el.referenceId,
                        name:el.name                         
                    });
                });                 
                
                if(dijit.byId("userVerticalMenuBarWidget")) { 
                    dijit.byId("userVerticalMenuBarWidget").unsubscribe();
                    dijit.byId("userVerticalMenuBarWidget").destroyRecursive();                  
                }
                
                var VerticalMenuBar = new FogPanel.VerticalMenuBar({
                    id : "userVerticalMenuBarWidget",
                    options : true,
                    filterOption : vpnListSat,
                    backtoDashboardLabel :translator.common.backtoDashboard,
                    onBackButtonclick : function () {
                        dojo.byId("selectedANVPCID").value = "";
                        ZoneConfigForMenu.populateValue();
                        core.router.go("#/user/home")
                    },
                    onSelectBoxChange : function (currentVPN) {
                        dojo.byId("selectedANVPCID").value = currentVPN.value; 
                        core.router.go("#/user/vpc");
                            VPCMenuInfo.populateCountOnVPCStat();
                    },
                    selectOptionContent: vpcList,
                    selectBoxValue : vpcId
                }).placeAt("userVerticalMenuBar");  
                VerticalMenuBar.populateMenu(vpcMenuList);
                VerticalMenuBar.subscribe("/FogPanel/event/route/changed");                                                                  
            });                       
        }                                             
    }
};
var VPNConnection = {
    'showDeleteDialog' :  function (id) { 
        dojo.byId("currentVPNConnection").value = id;
        dijit.byId("vpnConnectionDeleteDialog").show();
    },
    'closeDeleteVPNCustomerGateway' :  function () { 
        dijit.byId("vpnConnectionDeleteDialog").hide();
    },
    'showCreateDialog' :  function () { 
        var pageNode = dojo.byId("addVPNConnectionPage");
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
            dijit.byId("createVPNConnecionPagePage").show();
        }       
        
    },
    'closeCreateVPNConnection' :  function () { 
        dijit.byId("createVPNConnecionPagePage").hide();
    },
    'showRestartDialog' :  function (id) { 
        dojo.byId("currentVPNConnection").value = id;
        dijit.byId("vpnConnectionRestartDialog").show();
    },
    'closeRestartVPNCustomerGateway' :  function () { 
        dijit.byId("vpnConnectionRestartDialog").hide();
    },
    'populateValues' :  function () { 
                
        dojo.byId("vpnConnectionList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ipsecPresharedKey, 'field': 'ipsecPresharedKey', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                'formatter': function(data) {
                    var vpnStatus = new FogPanel.VPNStatus({                           
                           onRebootVPN : function () {VPNConnection.showRestartDialog(data.referenceId);},
                           onDeleteVPN : function () {VPNConnection.showDeleteDialog(data.referenceId);},
                           enableVPNConnectionStat : true,                      
                    });
                    return vpnStatus;
                }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];

        var vpnConnectionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/VpnCustomerGateway/vpnConnection/list"
        });
        vpnConnectionRestStore.query({vpcId:dojo.byId("currentVpcId").value}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("vpnConnectionList").innerHTML = "";
                dojo.byId("noVpnConnectionMessage").style.display = "block";                 
            } else {
                dojo.byId("noVpnConnectionMessage").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: resultData.ip,
                        gateway: resultData.gateway,
                        name:resultData.name,
                        ipsecPresharedKey:resultData.ipsecPresharedKey,
                        state: resultData.state,
                        action:resultData
                    });
                });
                dojo.byId("vpnConnectionList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("vpnConnectionList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
        
        var vpnCustomerGatewayOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var vpnCustomerGatewayStoreList = new ItemFileWriteStore({data: vpnCustomerGatewayOptions});  
        
        var vpnCustomerGateway = new JsonRest({
            target: core.getContextPath() + "/api/VpnCustomerGateway"
        });
        
        setTimeout(function () {
            var vpncgCount = 0;
            var vpncgOverallCount = 0;
            vpnCustomerGateway.query({}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    vpncgOverallCount = data.length;
                    if(resultData.isUsed === false) {
                        vpnCustomerGatewayStoreList.newItem({
                            id: resultData.referenceId,
                            gateway: resultData.gateway,
                            name:resultData.name,
                            ipsecPresharedKey:resultData.ipsecPresharedKey,
                            cidr: resultData.cidr,
                            action:resultData
                        });
                    } else {
                        vpncgCount++;
                    }
                    if(vpncgCount === vpncgOverallCount) {
                        dojo.byId("addVPNConnectionNoPage").style.display = "block";
                        dojo.byId("addVPNConnectionPage").style.display = "none";
                    } else {
                        dojo.byId("addVPNConnectionNoPage").style.display = "none";
                        dojo.byId("addVPNConnectionPage").style.display = "block";
                    }
                    dijit.byId("vpnCustomerGateway").set("store", vpnCustomerGatewayStoreList);
                    dijit.byId("vpnCustomerGateway").startup();
                    dijit.byId("vpnCustomerGateway").reset();
                });
            });
        },500);
    },
    'addVPNConnection': function() {
        dijit.byId("createVPNConnecionPagePage").hide();
        var VPNCustomerGateway = dijit.byId("vpnCustomerGateway").value;
        var vpcId = dojo.byId("currentVpcId").value;

        var pageNode = dojo.byId("addVPNConnectionPage");
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

            var tierRestStore = new JsonRest({
                target: core.getContextPath() + "/api/VpnCustomerGateway/vpnConnection/create/"
            });
            dijit.byId("vpcLoader").show();
            
            tierRestStore.add({
                VPNCustomerGateway:VPNCustomerGateway,
                vpcId:vpcId                                
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.createVPNConnection, "message");
                        registry.byId("userToaster").show();      
                        dijit.byId("vpcLoader").hide();
                        VPNConnection.populateValues();
//                        var VPNConnectionJobStat = setInterval(function(){VPNConnection.VPNConnectionJob(resultData.jobId, VPNConnectionJobStat);},2000);  
                    } else if(resultData.result === "FAILED")  {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
                    }else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcLoader").hide();
                    }
                });
            });
        }
    },
    'VPNConnectionJob' : function(jobId, vpcDeleteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/VpnCustomerGateway/vpnConnection/create/job/"
        });         
        jobStore.add({
            jobId : jobId,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.createVPNConnection, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpcLoader").hide();
                    VPNConnection.populateValues();
                } else if(resultData.jobResult === "Pending") {
                    
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(vpcDeleteJobStat);
                    registry.byId("userToaster").setContent(resultData.message,"error");
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
    deleteVPNConnection: function() {        
        var id = dojo.byId('currentVPNConnection').value;        
        var s2sRestStore = new JsonRest({
                target: core.getContextPath() + "/api/VpnCustomerGateway/vpnConnection/delete/"
        });

        dijit.byId('vpcLoader').show();
        dijit.byId('vpnConnectionDeleteDialog').hide();
                
        s2sRestStore.add({vpnConnectionId: id}).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if (resultData.result == "OK") {
                    var deleteVPNConnectionJobStatus = setInterval(function() {
                        VPNConnection.deleteVPNConnectionJob(resultData.jobId, deleteVPNConnectionJobStatus);
                    }, 3000);

                } else if(resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.firewall.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }

            });

        });
    },
    'deleteVPNConnectionJob': function(jobId, deleteVPNConnectionJobStatus) {
        var s2svpnJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/VpnCustomerGateway/vpnConnection/delete/job/"
        });

        s2svpnJobRestStoreStore.add({jobId:jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deleteVPNConnectionJobStatus);
                    VPNConnection.populateValues();
                    registry.byId("userToaster").setContent(translator.common.message.deleteVPNConnection, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteVPNConnectionJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    },
    restartVPNConnection: function() {        
        var id = dojo.byId('currentVPNConnection').value;        
        var s2sRestStore = new JsonRest({
                target: core.getContextPath() + "/api/VpnCustomerGateway/vpnConnection/restart/"
        });

        dijit.byId('vpcLoader').show();
        dijit.byId('vpnConnectionRestartDialog').hide();
                
        s2sRestStore.add({vpnConnectionId: id}).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if (resultData.result == "OK") {
                    var restartVPNConnectionJobStatus = setInterval(function() {
                        VPNConnection.restartVPNConnectionJob(resultData.jobId, restartVPNConnectionJobStatus);
                    }, 3000);

                } else if(resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.firewall.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }

            });

        });
    },
    'restartVPNConnectionJob': function(jobId, restartVPNConnectionJobStatus) {
        var s2svpnJobRestStoreStore = new JsonRest({
            target: core.getContextPath() + "/api/VpnCustomerGateway/vpnConnection/restart/job/"
        });

        s2svpnJobRestStoreStore.add({jobId:jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(restartVPNConnectionJobStatus);
                    VPNConnection.populateValues();
                    registry.byId("userToaster").setContent(translator.common.message.restartVPNConnection, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(restartVPNConnectionJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcLoader').hide();
                }
            });
        });
    }
};
var VPCPrivateGateway = {
    
    'addStaticRoute': function() {
        
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/staticRoute/create/"
        });
                
        var pageNode = dojo.byId("staticRouteAddFormAddPage");
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
            dijit.byId("vpcPrivateGatewayLoader").show();
            var cidr = dijit.byId("staticRouteCidr");
            
            networkRestStore.add({privateGatewayId:dojo.byId("currentVpcPrivateGatewayId").value, cidr:cidr.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var createStaticRouteJobStat = setInterval(function(){VPCPrivateGateway.createStaticRouteJob(resultData.jobId, createStaticRouteJobStat);},2000);  
                    } else if(resultData.result === "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message,"error");
                        registry.byId("userToaster").show();       
                        dijit.byId("vpcPrivateGatewayLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("vpcPrivateGatewayLoader").hide();
                    }
                });
            });
        }
    },
    'createStaticRouteJob' : function(jobId, createStaticRouteJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/vpcPrivateGateway/staticRoute/create/job/"
        });         
        jobStore.add({jobId: jobId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(createStaticRouteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.createStaticRoute, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("vpcPrivateGatewayLoader").hide();
                    VPCPrivateGateway.populateStaticRoutList();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(createStaticRouteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("vpcPrivateGatewayLoader").hide();
                } else {
                    clearInterval(createStaticRouteJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("vpcPrivateGatewayLoader").hide(); 
                } 
            });
        });
    },
    
    'populateStaticRoutList' :  function () { 
                
        dijit.byId("staticRouteAddForm").reset();
        dojo.byId("staticRouteList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.cidr, 'field': 'cidr', 'width': '400px', 'datatype': 'string', 'autoComplete': 'true',},
                {'name': translator.common.state, 'field': 'state', 'width': '400px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {
                        var deleteButton = new dijit.form.Button({
                            "class": "delete_icon",
                            onClick: function() {
                                VPCPrivateGateway.showDeleteStaticRoute(data.referenceId);
                            }
                        });       
                        return deleteButton;
                }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        var vpnPrivateGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/staticRoute/list/"
        });
        vpnPrivateGatewayRestStore.query({vpcPrivateGatewayId:dojo.byId("currentVpcPrivateGatewayId").value}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("staticRouteList").innerHTML = "";
                dojo.byId("noStaticRouteMessageBox").style.display = "block";
                 
            } else {
                dojo.byId("noStaticRouteMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        state: resultData.state,
                        cidr: resultData.cidr,
                        action:resultData
                    });
                });
                dojo.byId("staticRouteList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("staticRouteList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
    },
    
    'populateDetails' :  function (id) { 
                
        var vpcPrivateGatewayRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpcPrivateGateway"
        });
    
        vpcPrivateGatewayRestStore.query({referenceId:id}).then(function(data) {
            dojo.forEach(data, function(vpcData) {
                dojo.byId("currentVpcName").innerHTML = vpcData.vpc;
                dojo.byId("vpcName").innerHTML = vpcData.vpc;
                dojo.byId("privategatewayIP").innerHTML = vpcData.ip;
                dojo.byId("vpcPrivateGatewayIp").innerHTML = vpcData.ip;
                dojo.byId("gateway").innerHTML = vpcData.gateway;
                dojo.byId("vpcZone").innerHTML = vpcData.zone;
                dojo.byId("currentVpcNameLink").href = "#/user/vpc/view/" + vpcData.vpcId;
                dojo.byId("currentVpcPrivateGatewayLink").href = "#/user/vpc/view/" + vpcData.vpcId;
                dojo.byId("currentVpcId").value = vpcData.vpcId;
                dojo.byId("currentVpcPrivateGatewayId").value = vpcData.referenceId;
                 
                dojo.byId("netmask").innerHTML = vpcData.netmask;
                dojo.byId("state").innerHTML = vpcData.state;
                dojo.byId("vlan").innerHTML = vpcData.vlan;
                 
            });
        });
    
    },
    
    'populateValues' :  function () { 
                
        dojo.byId("vpcPrivateGatewayList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true','formatter': function(data) {
                
                        return "<a href='#/user/vpc/viewVPCPrivateGateway/" + data.referenceId + "' title='" + translator.common.view + "'>" + data.ip + "</a>";
                    }},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.netmask, 'field': 'netmask', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vlan, 'field': 'vlan', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action', 'formatter': function(data) {

                    var html = "<a onclick='VPCPrivateGateway.showStaticRoutTab(\"" + data.referenceId + "\")' href='#/user/vpc/viewVPCPrivateGateway/" + data.referenceId + "' title='" + translator.common.view + "'><img src='images/edit.png'></img></a>";
                            
                    return html;
                }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        var vpnPrivateGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway"
        });
        vpnPrivateGatewayRestStore.query({vpcId:dojo.byId("currentVpcId").value}).then(function(data) {
            if (data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === " ") {
                dojo.byId("vpcPrivateGatewayList").innerHTML = "";
                dojo.byId("noVpcPrivateGatewayMessage").style.display = "block";
                 
            } else {
                dojo.byId("noVpcPrivateGatewayMessage").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        ip: {referenceId:resultData.referenceId, ip:resultData.ip},
                        gateway: resultData.gateway,
                        vlan:resultData.vlan,
                        netmask:resultData.netmask,
                        state: resultData.state,
                        action:resultData
                    });
                });
                dojo.byId("vpcPrivateGatewayList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class":"span12",
                    store: dataList,                    
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("vpcPrivateGatewayList");
                dataGrid.startup();
                dataGrid.update();
                
            }
        });
    },
    'closeDeleteStaticRoute' : function() {
         dijit.byId('deleteStaticRouteDialog').hide();
    },
    'showDeleteStaticRoute' : function(id) {
        dojo.byId('currentStaticRoute').value = id;
         dijit.byId('deleteStaticRouteDialog').show();
    },
    'deleteStaticRoute': function() {  
         
        var id = dojo.byId('currentStaticRoute').value;        
        var staticRouteRest = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/staticRoute/delete/"
        });

        dijit.byId('vpcPrivateGatewayLoader').show();
        dijit.byId('deleteStaticRouteDialog').hide();
                
        staticRouteRest.add({staticRouteId: id}).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if (resultData.result == "OK") {
                    var deleteStaticRouteJobStatus = setInterval(function() {
                        VPCPrivateGateway.deleteStaticRouteJob(resultData.jobId, deleteStaticRouteJobStatus);
                    }, 3000);

                } else if(resultData.result === "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcPrivateGatewayLoader').hide();
                } else {
                    registry.byId("userToaster").setContent(translator.common.firewall.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcPrivateGatewayLoader').hide();
                }

            });

        });
    },
    'deleteStaticRouteJob': function(jobId, deleteStaticRouteJobStatus) {
        var staticRouteRest = new JsonRest({
            target: core.getContextPath() + "/api/vpcPrivateGateway/staticRoute/delete/job/"
        });

        staticRouteRest.add({jobId:jobId}).then(function(response) {
            dojo.forEach(response, function(jobResultData) {

                if (jobResultData.jobResult === "OK") {
                    clearInterval(deleteStaticRouteJobStatus);
                    VPCPrivateGateway.populateStaticRoutList();
                    registry.byId("userToaster").setContent(translator.common.message.deleteStaticRoute, "message");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcPrivateGatewayLoader').hide();
                } else if (jobResultData.jobResult === "Pending") {

                } else {
                    clearInterval(deleteStaticRouteJobStatus);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId('vpcPrivateGatewayLoader').hide();
                }
            });
        });
    },
    'showPrivateGatweayTab': function() {
        
        setTimeout(function () {
            var mainTab = dijit.byId("ipVpcTabCointainer"); //Tr
            var subIpTab = dijit.byId("vpcPrivateGatewayTab"); //tab Id which you want to show
            mainTab.selectChild(subIpTab);   
        },800);
    },
    'showStaticRoutTab': function() {
        
        setTimeout(function () {
            var mainTab = dijit.byId("privateGatewayContainer"); //Tr
            var subIpTab = dijit.byId("staticRoutesTab"); //tab Id which you want to show
            mainTab.selectChild(subIpTab);   
        },800);
    },
};
Window.VPNConnection=VPNConnection;
Window.VPCPrivateGateway=VPCPrivateGateway;
Window.VPCMenuInfo = VPCMenuInfo;
Window.ListVpc=ListVpc;
Window.VPCNetworkACLInfo = VPCNetworkACLInfo;
Window.NetworkTopology = NetworkTopology;
