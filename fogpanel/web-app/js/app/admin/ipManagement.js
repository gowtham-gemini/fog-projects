"use strict";
require([
    "dojo", 
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
    "dijit/form/RadioButton",
    "List/ListItem",
    "dijit/Dialog"
    ],
    function(dojo, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
    ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {             
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
        
        controller ({
            name: "ipManagement",
            module: "admin",
            filePath: "/js/app/admin/ipManagement.js",
            layout: {
                name: "ipManagement",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {
                core.ui.loadTemplate("ipManagement", core.ui.getContentId()); 
                DashboardIpManagement.init();
                DashboardIpManagement.populateValues();
                ConfigTest.init();
            }),
            "add": action(function() {
                var addForm = dijit.byId("taxAddForm");
                if(addForm) {
                    addForm.destroyRecursive();
                }
                core.ui.loadTemplate("addIp", core.ui.getContentId()); 
            }),
            "edit": action(function() { 
                var editForm = dijit.byId("taxEditForm");
                if(editForm) {
                    editForm.destroyRecursive();
                }
                core.ui.loadTemplate("ipManagement", core.ui.getContentId()); 
            }),
            "delete": action(function() {                
                core.ui.loadTemplate("deleteIp", core.ui.getContentId()); 
            })          
            
            
        });
    });
    
//var ConfigTest = {    
//    init: function() {
//        
//        var varticalMenuBar = dijit.byId("verticalMenu");
//        varticalMenuBar.enable();
//        var navigator = dijit.byId("templateNavigator");
//        domClass.remove(dojo.byId("contentArea"), "diableSideMenu");
//        navigator.onToggleButtonClick = function() {           
//            ConfigTest.showSideMenu();
//        };
//        navigator.enableNavigator();
//        var config = new JsonRest({
//            target: core.getContextPath()+"/api/config/test/"
//        });
//        
//        config.query().then(function(data) {               
//            if(data == "OK") { 
//            } else {     
//                registry.byId('appToaster').setContent('Config missing!', 'error');
//                registry.byId('appToaster').show();
//                dojo.byId("errorInfo").style.display = "block";                     
//            }                    
//        }); 
//    },
//    showSideMenu : function() {
//        var varticalMenuBar = dojo.byId("verticalMenu");        
//        var newClass = "";
//        newClass = varticalMenuBar.className.split(' ')[1]; // first class
//        if(newClass == "display") {
//            domClass.remove(varticalMenuBar, "display"); 
//        } else if(newClass == "" || newClass == undefined || newClass != "display"){
//            domClass.add(varticalMenuBar, "display");
//        }
//    }
//};


var DashboardIpManagement = {
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
        if(dijit.byId("adminIpDataGrid")) {                                    
                dijit.byId("adminIpDataGrid").destroyRecursive();                    
        }
        var ipGridData = {
                    items: []
        }; 
        var ipDataList = new ItemFileWriteStore({data: ipGridData}); 
        var ipDataLayout = [[
                 {'field': 'id', 'hidden': 'true'},
                 {'name': 'Ip Block Name', 'field': 'ipBlockName', 'width': '100%'},
                 {'name': 'Start Ip', 'field': 'startIp', 'width': '100%'},
                 {'name': 'End Ip', 'field': 'endIp', 'width': '100%'},
                 {'name': 'Netmask','field': 'netmask', 'width': '100%'},
                 {'name': 'Gateway','field': 'gateway', 'width': '100%'},
                 {'name': 'DNS1', 'field': 'dns1', 'width': '100%'},
                 {'name': 'DNS2', 'field': 'dns2', 'width': '100%'},                 
                 {'name': 'Action', 'field': 'action',
                     'formatter': function(data) { 
                         
                         
                       var html = "<a href='#/admin/tax/edit/"+data+"'>Edit</a>" + "<a href='#/admin/tax/delete/"+data+"'>Delete</a>";
                        
                       return html;
                },'width': '100%'}
             ]
         ];
         
         this._ipManagementRestStore.query().then(function(data) {
             dojo.forEach(data, function(ipData) {
                 ipDataList.newItem({
                     id:ipData.ipId,                     
                     ipBlockName:ipData.name,
                     startIp: ipData.startIp,                      
                     endIp: ipData.endIp,
                     netmask : ipData.netMask,                     
                     gateway : ipData.gateWay,
                     dns1 : ipData.dns1,                     
                     dns2 : ipData.dns2,
                     action : ipData.ipId                     
                     });
                 });
             });
             var ipDataGrid = new EnhancedGrid({
                 id: 'adminIpDataGrid',
                 store: ipDataList,
                structure: ipDataLayout,
                autoHeight: true,
                plugins: core.getGridConfig().plugins
        });
        ipDataGrid.placeAt("adminIpList");
        ipDataGrid.startup();       
        
                    
         if(dijit.byId("dashboardIpZone") && dijit.byId("dashboardIpPod")) {
                dijit.byId("dashboardIpZone").destroyRecursive();
                dijit.byId("dashboardIpPod").destroyRecursive();
        }
               
         var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Pod"}]
        };
                    
        var podList = new ItemFileWriteStore({data: podOptions});
                    
        this._podWidget = new Select({
            id: "dashboardIpPod",

            store: podList,
            onChange : function() {               
            }
        }).placeAt("dashboardIpPodList");  
                   
        
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
            id: "dashboardIpZone",
            store: zoneList,
            onChange: function() {
//                dijit.byId("dashboardIpManagementPageForm").reset();
                var podWidget = dijit.byId("dashboardIpPod");              
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
        }).placeAt("dashboardIpZoneList"); 
    },
    add : function() {
        var ipNode = dojo.byId("dashboardIpManagementPage");
        var ipWidget = dijit.registry.findWidgets(ipNode);
        dojo.forEach(ipWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                };
            };
        });
        
        var ipBlockName = dijit.byId ("dashboardIpName");
        var startIp = dijit.byId ("dashboardStartIp");
        var endIp = dijit.byId ("dashboardEndIp");
        var netMask = dijit.byId("dashboardIpNetmask");
        var gateWay = dijit.byId("dashboardIpGateway");
        var dns1 = dijit.byId("dashboardIpDNS1");
        var dns2 = dijit.byId("dashboardIpDNS2");
        var podWidget = dijit.byId("dashboardIpPod");

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
                            DashboardIpManagement.returnIpManagement(this);
                        },
                        onDeleteTagClick: function() {
                            DashboardIpManagement.deleteCurrentWidget(this, "false");
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
                    ipListItem.placeAt("dashboardIpListItem");
                    ipListItem.startup();
                    dijit.byId("dashboardIpManagementPageForm").reset();
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
        dijit.byId('dashboardIpAddButton').set('style', {display: 'block', "float": 'left'});
        dijit.byId('dashboardIpUpdateButton').set('style', {display: 'none'});
        
         var ipListCollection = dojo.byId ("dashboardIpListCollection");
                var ipListWidgets = dijit.registry.findWidgets (ipListCollection);
        
                dojo.forEach (ipListWidgets, function(el) {
                   
                        el.setSelectState(false, el.id);
                   
                }); 
        dojo.byId("dashboardIpManagementForm").reset();
        dojo.byId("dashboardIpManagementPageForm").reset();
    },
    deleteCurrentWidget : function(currentDiskListWidget) {
        
      var ipManagementRestStore = new JsonRest({
            target: core.getContextPath()+"/api/ipAddress/"
      });
      ipManagementRestStore.remove(currentDiskListWidget.additionalProperties.id);
    },
    returnIpManagement : function(currentWidget) {
        dojo.byId("dashboardCurrentIpListItem").value = currentWidget.id;
        dijit.byId("dashboardIpName").setValue(currentWidget.additionalProperties.ipBlockName);
        dijit.byId("dashboardStartIp").setValue(currentWidget.additionalProperties.startIp);
        dijit.byId("dashboardEndIp").setValue(currentWidget.additionalProperties.endIp);
        dijit.byId("dashboardIpGateway").setValue(currentWidget.additionalProperties.gateWay);
        dijit.byId("dashboardIpDNS1").setValue(currentWidget.additionalProperties.dns1);
        dijit.byId("dashboardIpDNS2").setValue(currentWidget.additionalProperties.dns2);
        dijit.byId("dashboardIpNetmask").setValue(currentWidget.additionalProperties.netMask);
        dijit.byId('dashboardIpAddButton').set('style', {display: 'none'});
        dijit.byId('dashboardIpUpdateButton').set('style', {display: 'block', "float": 'left'});
        
        var ipListCollection = dojo.byId ("dashboardIpListCollection");
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
        var currentWidgetId = dojo.byId("dashboardCurrentIpListItem").value;
        var currentIpListWidget = dijit.byId(currentWidgetId);
        var diskOfferNode = dojo.byId("dashboardIpManagementPage");
        var diskOfferWidget = dijit.registry.findWidgets(diskOfferNode);
        
        dojo.forEach(diskOfferWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                };
            };
        });
        
        var ipBlockName = dijit.byId ("dashboardIpName");
        var startIp = dijit.byId ("dashboardStartIp");
        var endIp = dijit.byId ("dashboardEndIp");
        var netMask = dijit.byId("dashboardIpNetmask");
        var gateWay = dijit.byId("dashboardIpGateway");
        var dns1 = dijit.byId("dashboardIpDNS1");
        var dns2 = dijit.byId("dashboardIpDNS2");
        var podWidget = dijit.byId("dashboardIpPod");
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
                dijit.byId('dashboardIpAddButton').set('style', {display: 'block', "float": 'left'});
                dijit.byId('dashboardIpUpdateButton').set('style', {display: 'none'});
                dijit.byId("dashboardIpManagementPageForm").reset();
                registry.byId("appToaster").setContent("updated Successfully!","message");
                registry.byId("appToaster").show();
                
                var ipListCollection = dojo.byId ("dashboardIpListCollection");
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
                } 
            }
        };
    }
};

//window.ConfigTest = ConfigTest;
window.DashboardIpManagement = DashboardIpManagement;