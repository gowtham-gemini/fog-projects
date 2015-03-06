Window.AdminTierInfo = AdminTierInfo;
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
        module: "admin",
        filePath: "/js/app/admin/tier.js",
        layout: {
            name: "tier",
            containerId: "content"
        },	
        scaffold: false
    },
    {                     
        "view": action(function(id) {            
            
            if(dijit.byId("adminTierTabCointainer")) {
                dijit.byId("adminTierTabCointainer").destroyRecursive();
            }            
            if(dijit.byId("adminTierDetailContainer")) {
                dijit.byId("adminTierDetailContainer").destroyRecursive();
            }
            if(dijit.byId("adminTierPublicLoadBalancer")) {
                dijit.byId("adminTierPublicLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("adminTierInternalLoadBalancer")) {
                dijit.byId("adminTierInternalLoadBalancer").destroyRecursive();
            }
            if(dijit.byId("adminInfoMenuTierInstanceContentPane")) {
                dijit.byId("adminInfoMenuTierInstanceContentPane").destroyRecursive();
            }                                                  
            core.ui.loadTemplate("viewAdminTier", core.ui.getContentId());               
            AdminTierInfo.init(id);            
        })               
    });
});

var AdminTierInfo = {    
    'init': function(id) {
        
        dojo.byId("currentAdminTierId").value = id;

        var name = dojo.byId("adminViewTierName");
        
        var desc = dojo.byId("adminViewTierDesc");
        var zone = dojo.byId("adminViewTierZoneName");
        var networkOffer = dojo.byId("adminViewTierOfferName");
        var networkDomain = dojo.byId("adminViewTierDomain");
        var referenceId = dojo.byId("adminViewTierReferenceId");
        var netmask = dojo.byId("adminViewTierNetmask");

        var gateway = dojo.byId("adminViewTierGateway");
        var cidr = dojo.byId("adminViewTierCidr");

        var type = dojo.byId("adminViewTierType");
        var state = dojo.byId("adminViewTierState");

        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network/"
        });

        networkRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("currentAdminTierZoneId").value = resultData.zoneId;
                networkOffer.innerHTML = resultData.networkOffer;
                name.innerHTML = resultData.name;
                desc.innerHTML = resultData.description;
                zone.innerHTML = resultData.zone;
                cidr.innerHTML = resultData.cidr;
                gateway.innerHTML = resultData.gateway;
                type.innerHTML = resultData.tierType;
                dojo.byId("adminViewTierUser").innerHTML = resultData.account;
                networkDomain.innerHTML = resultData.networkDomain;
                referenceId.innerHTML = resultData.referenceId;
                dojo.byId("currentAdminTierReferenceId").value = resultData.referenceId;
                netmask.innerHTML = resultData.netmask;
                dojo.byId("currentVpcNameLink").href = "#/admin/vpc/view/" + resultData.vpcId;
                dojo.byId("currentTierListLink").href = "#/admin/vpc/view/" + resultData.vpcId;
                dojo.byId("currentTierName").innerHTML = resultData.name;
                dojo.byId("currentVpcName").innerHTML = resultData.vpcName;
                
                
                var mainTab = dijit.byId("adminTierTabCointainer"); 
                var subPortTab = dijit.byId("adminTierPortForwarding");
                var subLoadPublicTab = dijit.byId("adminTierPublicLoadBalancer");
                var subLoadInternalTab = dijit.byId("adminTierInternalLoadBalancer");                
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
                     subPortTab.destroyRecursive();
                     subLoadPublicTab.destroyRecursive();
                }
                state.innerHTML = resultData.state;
            });
        });
    },
    populateTierVMInfo : function () {
        if(dijit.byId("adminTierVMGrid")) {                                    
            dijit.byId("adminTierVMGrid").destroyRecursive();                    
        }        
        dojo.byId("adminTierInstanceGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vmData = {
            items: []
        }; 
        var vmDataList = new ItemFileWriteStore({data: vmData}); 
        var vmLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'width': '300px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.userLabel, 'field': 'user', 'width': '300px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.zone, 'field': 'zone', 'width': '300px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'status', 'width': '100%', 'datatype':'string',  'autoComplete': 'true','formatter' : function (data) {
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
                {'name': translator.common.action, 'field': 'action', 'hidden': 'true', 'datatype':'string',  'autoComplete': 'true'}
             ]
         ];
         var instanceRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/vm/list"
         });
         instanceRestStore.query({networkId: dojo.byId("currentAdminTierId").value}).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("adminTierInstanceGrid").innerHTML = "";
                 dojo.byId("noAdminTierVmMessageBox").style.display = "block";                 
             } else {
                 dojo.byId("noAdminTierVmMessageBox").style.display = "none";
                 dojo.forEach(data, function(vm) {
                     vmDataList.newItem({
                         id:vm.referenceId,
                         user : vm.account,
                         vmName: vm.name,
                         zone: vm.zone, 
                         status: vm.state,
                         action : {id: vm.id, stat: vm.state}
                     });
                 });                  
                 dojo.byId("adminTierInstanceGrid").innerHTML = "";
                 var tierVMGrid = new EnhancedGrid({
                     id: 'adminTierVMGrid',
                     "class" : "span12",
                      store: vmDataList,
                      structure: vmLayout,
                      autoHeight: true,
                      plugins: core.getGridConfig().plugins
                  });
                  tierVMGrid.placeAt("adminTierInstanceGrid");
                  tierVMGrid.startup();  
              }                                
          });   
    },
    populateStaticNatInfo : function () {
        if(dijit.byId("tierAdminStaicNatGrid")) {                                    
            dijit.byId("tierAdminStaicNatGrid").destroyRecursive();                    
        }
        dojo.byId("adminTierStaticNatGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vmData = {
            items: []
        }; 
        var vmDataList = new ItemFileWriteStore({data: vmData}); 
        var vmLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ipAddress', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {                        
                        return "<span>" + data.ipAdd + "</span>";                        
                    }
                },
                {'name': translator.common.userLabel, 'field': 'user', 'width': '300px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'datatype':'string',  'autoComplete': 'true', 'width': '300px'},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                
            ]
         ];
         var staticnatRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/ip/list/"
         });
         staticnatRestStore.get(dojo.byId("currentAdminTierId").value).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noAdminStaticNatMsgBox").style.display = "block";
                 dojo.byId("adminTierStaticNatGrid").innerHTML = "";
             } else {
                 dojo.byId("noAdminStaticNatMsgBox").style.display = "none";                 
                 dojo.forEach(data, function(currentIp) {
                     if(currentIp.isStaticNat === "true") {
                         vmDataList.newItem({
                             id:currentIp.id,
                             user: currentIp.account,
                             ipAddress: {ipAdd: currentIp.ip, id: currentIp.id},
                              vmName:currentIp.vmDisplayName?currentIp.vmDisplayName: "-",
                             zone: currentIp.zone, 
                             state: currentIp.state                             
                         });
                     }
                 });                  
                 dojo.byId("adminTierStaticNatGrid").innerHTML = "";
                 var staticnatGrid = new EnhancedGrid({
                     id: 'tierAdminStaicNatGrid',
                     "class" : "span12",
                     store: vmDataList,
                     structure: vmLayout,
                     autoHeight: true,
                     plugins: core.getGridConfig().plugins
                 });
                 staticnatGrid.placeAt("adminTierStaticNatGrid");
                 staticnatGrid.startup(); 
             }               
         });          
     },
     populatePFInfo : function () {
         if(dijit.byId("adminTierPfGrid")) {                                    
            dijit.byId("adminTierPfGrid").destroyRecursive();                    
        }
        dojo.byId("adminTierPortforwdrGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vmData = {
            items: []
        }; 
        var vmDataList = new ItemFileWriteStore({data: vmData}); 
        var vmLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '300px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {                        
                        if (data.isSourceNat === true || data.isSourceNat === "true") {
                            return "<span>" + data.ip + " [Source NAT]</span>";
                        } else if(data.isStaticNat === true || data.isStaticNat === "true") {
                            return "<span>" + data.ip + " [Static NAT] </span>";
                        } else {
                            return "<span>" + data.ip + "</span>";
                        }              
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '300px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.userLabel, 'field': 'user', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                
            ]
         ];
         var portforwardingRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/ip/list/"
         });
         portforwardingRestStore.get(dojo.byId("currentAdminTierId").value).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noAdminTierPFMsgBox").style.display = "block";
                 dojo.byId("adminTierPortforwdrGrid").innerHTML = "";
             } else {
                 dojo.byId("noAdminTierPFMsgBox").style.display = "none";                 
                 dojo.forEach(data, function(currentIp) {
                     if(currentIp.isVPCPFAdded === true) {
                         vmDataList.newItem({
                             id:currentIp.id,
                             user : currentIp.account,
                             ip: {ip: currentIp.ip, id: currentIp.id, isSourceNat: currentIp.isSourceNat, isStaticNat: currentIp.isStaticNat},
                             vmName:currentIp.vmDisplayName,
                             zone: currentIp.zone, 
                             state: currentIp.state                             
                         });
                     }
                 });                  
                 dojo.byId("adminTierPortforwdrGrid").innerHTML = "";
                 var pfGrid = new EnhancedGrid({
                     id: 'adminTierPfGrid',
                     "class" : "span12",
                     store: vmDataList,
                     structure: vmLayout,
                     autoHeight: true,
                     plugins: core.getGridConfig().plugins
                 });
                 pfGrid.placeAt("adminTierPortforwdrGrid");
                 pfGrid.startup(); 
             }               
         });          
     },
     populatePopulatePublicLBInfo : function () {
         if(dijit.byId("adminTierPublicLBGrid")) {                                    
            dijit.byId("adminTierPublicLBGrid").destroyRecursive();                    
        }
        dojo.byId("adminInfoMenuTierPublicLBGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var LBData = {
            items: []
        }; 
        var lbList = new ItemFileWriteStore({data: LBData}); 
        var lbLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '300px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        if (data.isSourceNat === true || data.isSourceNat === "true") {
                            return "<span>" + data.ip + " [Source NAT]</span>";
                        } else if(data.isStaticNat === true || data.isStaticNat === "true") {
                            return "<span>" + data.ip + " [Static NAT] </span>";
                        } else {
                            return "<span>" + data.ip + "</span>";
                        }   
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '300px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.userLabel, 'field': 'user', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},  
                {'name': translator.common.instance.vmName, 'field': 'vmName', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                
            ]
         ];
         var lbRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/ip/list/"
         });
         lbRestStore.get(dojo.byId("currentAdminTierId").value).then(function(data) {
             if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noAdminPublicLBMsgBox").style.display = "block";
                 dojo.byId("adminInfoMenuTierPublicLBGrid").innerHTML = "";
             } else {
                 dojo.byId("noAdminPublicLBMsgBox").style.display = "none";                 
                 dojo.forEach(data, function(currentIp) {
                     if(currentIp.isVPCLBAdded === true) {
                         lbList.newItem({
                             id:currentIp.id,     
                             user : currentIp.account,
                             ip: {ip: currentIp.ip, id: currentIp.id, isSourceNat: currentIp.isSourceNat, isStaticNat: currentIp.isStaticNat},
                             vmName:currentIp.vmDisplayName,
                             zone: currentIp.zone, 
                             state: currentIp.state                             
                         });
                     }
                 });                  
                 dojo.byId("adminInfoMenuTierPublicLBGrid").innerHTML = "";
                 var pfGrid = new EnhancedGrid({
                    id: 'adminTierPublicLBGrid',
                    "class" : "span12",
                    store: lbList,
                    structure: lbLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                pfGrid.placeAt("adminInfoMenuTierPublicLBGrid");
                pfGrid.startup();
              }                           
         }); 
     },
     populateInternalLBValues : function () {
             
        if(dijit.byId("adminTierInternalLBGridWidget")) {                                    
            dijit.byId("adminTierInternalLBGridWidget").destroyRecursive();                    
        }
        dojo.byId("adminTierInternalLBGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var internalLBData = {
            items: []
        }; 
        var internallbList = new ItemFileWriteStore({data: internalLBData}); 
        var internallbLayout = [
            [                
                {'name': translator.common.name, 'field': 'lbName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.userLabel, 'field': 'user', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.description, 'field': 'lbDescription', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.sourcePort, 'field': 'lbSourcePort', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.instancePort, 'field': 'lbInstancePort', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.algorithm, 'field': 'lbAlgorithm', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                                
                {'name': translator.common.instance.name, 'field': 'lbVm', 'width': '100%', 'datatype':'string',  'autoComplete': 'true'}               
            ]
         ];
         var lbRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/internal/loadBalancing/get"
         });
         
         var networkRestStore = new JsonRest({
             target: core.getContextPath()+"/api/network/"
         });
         
         networkRestStore.get(dojo.byId("currentAdminTierId").value).then(function (currentNW) {
             if(currentNW[0].length === 0 || currentNW[0] === "" || currentNW[0] === "undefined") {
                 dojo.byId("noAdminInternalLBMsgBox").style.display = "block";
                 dojo.byId("tierInternalLBGrid").innerHTML = "";
             } else {                
                 lbRestStore.query({networkId: currentNW[0].referenceId}).then(function(data) {
                     if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                         dojo.byId("noAdminInternalLBMsgBox").style.display = "block";
                         dojo.byId("adminTierInternalLBGrid").innerHTML = "";
                     } else {
                         dojo.byId("noAdminInternalLBMsgBox").style.display = "none";                 
                         dojo.forEach(data, function(el) {                             
                             internallbList.newItem({                                  
                                 lbName : el.loadBalancerName,
                                 user : el.account,
                                 lbDescription:el.description,
                                 lbSourcePort: el.sourcePort, 
                                 lbInstancePort: el.instancePort,
                                 lbAlgorithm : el.algorithm,
                                 lbVm:el.vm,
                                 action: {id: el.id, name: el.loadBalancerName, algorithm: el.algorithm}
                             });                             
                         });                  
                         dojo.byId("adminTierInternalLBGrid").innerHTML = "";
                         var lbGrid = new EnhancedGrid({
                             id: 'adminTierInternalLBGridWidget',
                             "class" : "span12",
                             store: internallbList,
                             structure: internallbLayout,
                             autoHeight: true,
                             plugins: core.getGridConfig().plugins
                         });
                         lbGrid.placeAt("adminTierInternalLBGrid");
                         lbGrid.startup();
                     }                           
                 });
             }
         });
     }
};

