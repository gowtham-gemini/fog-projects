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
    function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
    ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {    
        window.translator = translator;
        window.query = query;
        window.domClass = domClass;
        window.domConstruct = domConstruct;
        window.JsonRest = JsonRest;
        window.registry = registry;
        window.FilteringSelect = FilteringSelect;
        window.ItemFileWriteStore = ItemFileWriteStore;
        window.Select = Select;
        window.ContentPane = ContentPane;
        window.DataGrid = DataGrid;
        window.EnhancedGrid = EnhancedGrid;
        window.Source = Source;
        window.MultiSelect = MultiSelect;
        window.dom = dom;
        window.win = win;
        
        controller ({
            name: "miscellaneous",
            module: "admin",
            filePath: "/js/app/admin/misc.js",
            layout: {
                name: "miscellaneous",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {   
                var form = dijit.byId("adminMiscBandwidthForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("bandEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("bandwidth", core.ui.getContentId()); 
                BandwidthInfo.init();
                BandwidthInfo.populateValues();
             
             }),
             "bandwidth": action(function() { 
                var form = dijit.byId("adminMiscBandwidthForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("bandEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("bandwidth", core.ui.getContentId()); 
                BandwidthInfo.init();
                BandwidthInfo.populateValues();
             }),
             "snapshot" : action(function() {
                 var form = dijit.byId("adminMiscSnapshotForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("snapshotEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("miscSnapshot", core.ui.getContentId()); 
                MiscSnapshotInfo.init();
                MiscSnapshotInfo.populateValues();
             }),
             "ipCost" : action(function() {
                var form = dijit.byId("adminmicIpForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("ipEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("miscelPage", core.ui.getContentId()); 
                MiscIpInfo.init();
                MiscIpInfo.populateValues();
             }),
             "vmSnapCost" : action(function() {
                var form = dijit.byId("adminMiscVMSnapshotForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("vmSnapshotEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("miscVMSnapshot", core.ui.getContentId()); 
                MiscVMSnapCostInfo.init();
                MiscVMSnapCostInfo.populateValues();
             }),
             "loadBalancer" : action(function() {
                var form = dijit.byId("adminMiscLoadBalancerForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("loadBalancerEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("miscLoadBalancer", core.ui.getContentId()); 
                MiscLoadBalancerInfo.init();
                MiscLoadBalancerInfo.populateValues();
             }),
             "portForwarding" : action(function() {
                var form = dijit.byId("adminMiscPortForwardingForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("portForwardingEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("miscPortForwarding", core.ui.getContentId()); 
                MiscPortForwardingInfo.init();
                MiscPortForwardingInfo.populateValues();
             }),
             "vpn" : action(function() {
                var form = dijit.byId("adminMiscVpnForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("vpnEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("miscVPNCost", core.ui.getContentId()); 
                MiscVpnInfo.init();
                MiscVpnInfo.populateValues();
             })
        });
    });
   
var BandwidthInfo = {
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
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
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("miscBandwidthCurrencyValue").innerHTML= cur.currency;
               });
        }); 
    },
    updateShow : function() {  
         dijit.byId("bandEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("bandEditConformationDialog").hide();
    },
    populateValues: function() {   
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var span = "<span class='require'>*</span>";
        if(dijit.byId("bandwidthZone")) {
            dijit.byId("bandwidthZone").destroyRecursive();
            dijit.byId("bandwidthPod").destroyRecursive();
            dijit.byId("bandwidthCluster").destroyRecursive();
        }
        if(dijit.byId("miscBandwidthCostWidget")) {
            dijit.byId("miscBandwidthCostWidget").destroyRecursive();
        }
        if(dijit.byId("bandwidthGrid")) {
            dijit.byId("bandwidthGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        dojo.byId("miscBandwidthInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [
            [
                {'field': 'id',  'hidden' : 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true, 'hidden' : 'true'},
                {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string", 'hidden' : 'true',  autoComplete: true, 'formatter' : function(data){                        
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                    }
                },
                {'name': translator.common.cost, 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                    }
                }                    
            ]
        ];                
        this._misclRestStore.get(1).then(function(data) {  
            if(data.miscellaneousOfferZoneCosts.length == 0 || data.miscellaneousOfferZoneCosts == undefined || data.miscellaneousOfferZoneCosts == 'undefined' || data.miscellaneousOfferZoneCosts == '' || data.miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("noMiscBandwidthMessageBox").style.display = "block";   
                dojo.byId("miscBandwidthInfo").innerHTML = "";
            } else {
                dojo.byId("noMiscBandwidthMessageBox").style.display = "none";   
                dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                    summaryDataList.newItem({
                        id:miscData.id, 
                        zone: miscData.zone.aliasName, 
                        pod: translator.common.allPod, 
                        cluster: translator.common.allCluster, 
                        cost: miscData.cost 
                    });
                });
                dojo.byId("miscBandwidthInfo").innerHTML = "";
                var summaryGridWidget = new EnhancedGrid({
                    id: "bandwidthGrid",
                    "class" : "span12",
                    store: summaryDataList,
                    structure: summaryLayout,
                    plugins: core.getGridConfig().plugins,
                    autoHeight: true           
                });
                summaryGridWidget.placeAt("miscBandwidthInfo");
                summaryGridWidget.startup();
            }             
        });               
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectPode}]
        };
        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectCluster}]
        };
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
          
        this._clusterWidget = new FilteringSelect({
            id: "bandwidthCluster",
            store: clusterList,
            value: "option",
            onChange : function() {    
                var misclRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/miscellaneousOffer/"
                });    
                
                var miscelWidget = dijit.byId("miscBandwidthCostWidget");
                
                misclRestStore.query({miscellaneousOfferId: 1, 
                clusterReferenceId: this.value}).then(function(data) {                
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    miscelWidget.clearWidgets();                
                } else {                    
                    dojo.forEach(data, function(miscData) {                        
                        miscelWidget.zoneCost =  miscData.cost;
                        miscelWidget.labelName =  translator.common.message.excesBandwidthCostMsg + span;      
                        miscelWidget.setZoneId(miscData.zone.id);
                        miscelWidget.setCost();                        
                    })   
                }                                    
            });
        }        
    }).placeAt("miscBandwidthCluster"); 
        
        this._podWidget = new FilteringSelect({
            id: "bandwidthPod",
            store: podList,
            value: "option",
            onChange: function() {
                var clusterWidget = dijit.byId("bandwidthCluster");
                var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
                };
                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
                if(this.value == "empty") {
                    currentClusterList.newItem({id: "empty", name: translator.common.message.selectCluster});
                    clusterWidget.set("store",currentClusterList);
                    clusterWidget.set("value","empty");
                    
                } else {
                    clusterRestStore.get(this.value).then(function(clusterListItems) {
                        var firstClusterVal = "";
                        dojo.forEach(clusterListItems, function(currentcluster, index) {
                            currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                            if(index == 0) {
                                firstClusterVal =  currentcluster.clusterReferenceId;
                            }
                        });
                        clusterWidget.set("store", currentClusterList);
                        clusterWidget.set("value",firstClusterVal);
                    });
                }
            }
        }).placeAt("miscBandwidthPod");   
        var zoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectZone}]
        }; 
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
            });
        });                        
        var currentZoneCost  = new Zone.ZoneCost({
            id:"miscBandwidthCostWidget",                                    
            vmRunningCostLabel : translator.common.exceedBandwidthCost  + span,
            invalidMsg : translator.common.zoneCostInvalidMsg
        });     
        currentZoneCost.removeCosts();    
        currentZoneCost.removeUnitCost();   
        currentZoneCost.placeAt("miscBandwidthCostInfo");
        currentZoneCost.startup();
        
        this._zoneWidget = new FilteringSelect({
            id : "bandwidthZone",
            store: zoneList,
            value: "option",
            onChange: function() {                 
                var podWidget = dijit.byId("bandwidthPod");                 
                
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
                    currentPodList.newItem({id: "empty", name: translator.common.message.selectPod});
                    podWidget.set("store",currentPodList);
                    podWidget.set("value", "empty");                    
                    dijit.byId("miscBandwidthButton").set("disabled", true);
                } else {
                    var firstPodVal = "";
                    dijit.byId("miscBandwidthButton").set("disabled", false);
                    podRestStore.get(this.value).then(function(podListItems) {
                        dojo.forEach(podListItems, function(currentPod, index) {
                            currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
                            if(index == 0) {
                                firstPodVal = currentPod.podReferenceId;
                            }
                        });
                        podWidget.set("store", currentPodList);
                        podWidget.set("value", firstPodVal);
                        
                    });
                }
                
                var zoneRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/zone/"
                });
                
                zoneRestStore.get(this.value).then(function(zoneDate) {
                    var miscelWidget = dijit.byId("miscBandwidthCostWidget")
                    miscelWidget.setZoneId(zoneDate.id);
                });
            }
        }).placeAt("miscBandwidthZone"); 
        
    },
    update: function() {       
        var podWidget = dijit.byId("bandwidthPod");
        var zone = dijit.byId("bandwidthZone");
        var zoneCosts = [];
        var clusterWidget = dijit.byId("bandwidthCluster");
        var miscelWidget = dijit.byId("miscBandwidthCostWidget");
        var status = miscelWidget.showError();
        var zoneSatus = true;
        if (zone.validate && !zone.validate()) {
            zone.focus();
            zoneSatus = false;                   
        }
        if(status == true && zoneSatus == true) {
            dijit.byId("bandEditConformationDialog").hide();
            var miscelZoneCostList = dojo.byId("bandwidthZoneCostList");
            var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCostValue()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }            
            dijit.byId('miscBandwidthButton').set('style', {'display': 'none'});
            dojo.byId('miscBandwidthLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 1,
                zoneCosts: zoneCosts,
                zoneReferenceId : this._zoneWidget.value,
                clusterReferenceId : clusterWidget.value,
                podReferenceId : podWidget.value
            }).then(function(result)  { 
                dijit.byId('miscBandwidthButton').set('style', {'display': 'block'});
                dojo.byId('miscBandwidthLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("bandEditConformationDialog").hide();
                    BandwidthInfo.populateValues();    
                    dijit.byId("miscSnapButton").set("disabled", true);
                } else {
                    dijit.byId("bandEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
        }        
    }     
    };


var MiscSnapshotInfo = {
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
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
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data,function(cur) { 
                dojo.byId("miscSnapshotCurrencyValue").innerHTML= cur.currency;
            });
        }); 
    },
    updateShow : function() {  
         dijit.byId("snapshotEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("snapshotEditConformationDialog").hide();
    },
    populateValues: function() {   
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var span = "<span class='require'>*</span>";
        if(dijit.byId("snapshotGrid")) {
            dijit.byId("snapshotGrid").destroyRecursive();
        }
        if(dijit.byId("snapshotZone")) {
            dijit.byId("snapshotZone").destroyRecursive();
            dijit.byId("snapshotPod").destroyRecursive();
            dijit.byId("snapshotCluster").destroyRecursive();
        }
        if(dijit.byId("miscSnapshotZoneCostWidget")) {
            dijit.byId("miscSnapshotZoneCostWidget").destroyRecursive();
        }
        dojo.byId("miscSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [[
                 {'field': 'id',  'hidden' : 'true'},
                 {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true, 'hidden' : 'true'},
                 {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string", 'hidden' : 'true',  autoComplete: true, 'formatter' : function(data) {
                         var html = "<span class='adminBolder'>"+ data + "</span>"
                         return html;
                     }
                 },
                 {'name': translator.common.cost, 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                 }
             }                    
         ]
     ];  
              
         this._misclRestStore.get(3).then(function(data) {
             if(data.miscellaneousOfferZoneCosts.length == 0 || data.miscellaneousOfferZoneCosts == undefined || data.miscellaneousOfferZoneCosts == 'undefined' || data.miscellaneousOfferZoneCosts == '' || data.miscellaneousOfferZoneCosts == " ") {
                 dojo.byId("noMiscSnapshotMessageBox").style.display = "block";   
                 dojo.byId("miscSnapshotInfo").innerHTML = "";
             } else {
                 dojo.byId("noMiscSnapshotMessageBox").style.display = "none"; 
                 
                 dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                     summaryDataList.newItem({
                         id:miscData.id, 
                         zone: miscData.zone.aliasName,
                         pod: translator.common.allPod,
                         cluster: translator.common.allCluster,
                         cost:miscData.cost 
                        });
                 });
                 dojo.byId("miscSnapshotInfo").innerHTML = "";
                 var gridWidget = new EnhancedGrid({
                     id: "snapshotGrid",
                     "class" : "span12",
                     store: summaryDataList,
                     structure: summaryLayout,
                     autoHeight: true,
                     plugins: core.getGridConfig().plugins                     
                 });
                 gridWidget.placeAt("miscSnapshotInfo");
                 gridWidget.startup();
             }             
         });               
         var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectPode}]
        };
        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectCluster}]
        };
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
          
        this._clusterWidget = new FilteringSelect({
            id: "snapshotCluster",
            store: clusterList,
            value : "option",
            onChange : function() {
                var misclRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/miscellaneousOffer/"
                });  

                var miscelZoneNode = dojo.byId("SnapshotZoneCostList");
                var miscelZoneWidgets = dijit.registry.findWidgets(miscelZoneNode);
                var miscWidget = dijit.byId("miscSnapshotZoneCostWidget");
                
                misclRestStore.query({miscellaneousOfferId: 3, 
                clusterReferenceId: this.value}).then(function(data) {
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                    miscWidget.clearWidgets();                    
                } else {
                    dojo.forEach(data, function(miscData) {              
                        if(miscData) {       
                            miscWidget.zoneIdNode = miscData.zone.id,
                            miscWidget.zoneCost = miscData.cost * 720,
                            miscWidget.labelName = translator.common.cost + "/" + translator.common.gb + "/" + translator.common.month,
//                            miscWidget.costRate = translator.common.billingCostPeGBHR;                       
                            miscWidget.setCost(); 
//                            miscWidget.setCostRate();   
                            miscWidget.setZoneId(miscData.zone.id);
                        } else {                    
                            miscWidget.clearWidgets();
                        }
                    }); 
                }
            });             
        }
    }).placeAt("miscSnapshotCluster");     
    this._podWidget = new FilteringSelect({
        id: "snapshotPod",
        store: podList,
        value : "option",
        onChange: function() {
            var clusterWidget = dijit.byId("snapshotCluster");
            var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            };
            var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
            if(this.value == "empty") {
                currentClusterList.newItem({id: "empty", name: translator.common.message.selectCluster});
                clusterWidget.set("store", currentClusterList);
                clusterWidget.set("value", translator.common.empty);
                
            } else {
                var firstClusterVal = "";
                clusterRestStore.get(this.value).then(function(clusterListItems) {
                    dojo.forEach(clusterListItems, function(currentcluster, index) {
                        currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                        if(index == 0) {
                            firstClusterVal = currentcluster.clusterReferenceId;
                        }
                    });
                    clusterWidget.set("store", currentClusterList);
                    clusterWidget.set("value", firstClusterVal);
                    
                });
            };
        }
    }).placeAt("miscSnapshotPod");              
    
    var currentZoneCost  = new Zone.ZoneCost({
        id:"miscSnapshotZoneCostWidget",
        vmRunningCostLabel: translator.common.cost + "/" + translator.common.gb + "/" + translator.common.month + span,
        runningCostPerHrLabel: translator.common.billingCostPeGBHR,
//        costRate: translator.common.billingCostPeGBHR,
        invalidMsg : translator.common.zoneCostInvalidMsg
    });       
//    currentZoneCost.removeCosts();   
    currentZoneCost.showOnlyZoneCost();     
//    currentZoneCost.setCostRate();   
    currentZoneCost.placeAt("miscSnapshotCostInfo");
    currentZoneCost.startup();
    var zoneOptions = { 
        identifier: 'id',
        label: 'name',
        items: [{id: "option", name: translator.common.message.selectZone}]
    };         
    
    var zoneList = new ItemFileWriteStore({data: zoneOptions});
    this._zoneRestStore.query().then(function(data) {
        dojo.forEach(data, function(el) {
            zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
        });
    });                            
    
    this._zoneWidget = new FilteringSelect({
        id : "snapshotZone",
        store: zoneList,
        value : "option",
        onChange: function() {                  
            var podWidget = dijit.byId("snapshotPod");                                     
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
                currentPodList.newItem({id: "empty", name: translator.common.message.selectPode});
                podWidget.set("store", currentPodList);
                podWidget.set("value", "empty");
                dijit.byId("miscSnapButton").set("disabled", true);
            } else {
                dijit.byId("miscSnapButton").set("disabled", false);
                var firstPodVal = "";
                podRestStore.get(this.value).then(function(podListItems) {
                    dojo.forEach(podListItems, function(currentPod, index) {
                        currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});                        
                        if(index == 0) {
                            firstPodVal =  currentPod.podReferenceId;
                        }
                    });
                    podWidget.set("store", currentPodList);
                    podWidget.set("value", firstPodVal);
                    
                });
            }                
            var zoneRestStore = new JsonRest({
                target: core.getContextPath()+"/api/zone/"
            });                
            zoneRestStore.get(this.value).then(function(zoneData) {                  
                var zoneCostWidget = dijit.byId("miscSnapshotZoneCostWidget");
                zoneCostWidget.zoneIdNode = zoneData.id,                       
                zoneCostWidget.setZoneId(zoneData.id);
            })
        }
    }).placeAt("miscSnapshotZone"); 
},
update: function() {       
    var podWidget = dijit.byId("snapshotPod");
    var zoneCosts = [];
    var clusterWidget = dijit.byId("snapshotCluster");
    var zoneCostWidget = dijit.byId("miscSnapshotZoneCostWidget");
    var status = zoneCostWidget.showError();
    var zoneSatus = true;
    var zone = dijit.byId("snapshotZone");
    if (zone.validate && !zone.validate()) {
        zone.focus();
        zoneSatus = false;                   
    }
    if(status == true && zoneSatus == true) {
        dijit.byId("snapshotEditConformationDialog").hide();
        var miscelZoneCostList = dojo.byId("SnapshotZoneCostList");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {          
            zoneCosts.push({
                zoneId: el.getZoneId(), 
                cost: el.getZoneCost()
            });
        });         
        for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
            zoneCosts.splice(index, miscelZoneCostWidgets.length);
        } 
        dijit.byId('miscSnapButton').set('style', {'display': 'none'});
        dojo.byId('miscSnapLoader').style.display = "block";
        this._misclRestStore.put({
            id: 3,
            zoneCosts: zoneCosts,
            zoneReferenceId : this._zoneWidget.value,
            clusterReferenceId : clusterWidget.value,
            podReferenceId : podWidget.value
        }).then(function(result)  {        
            if(result == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                registry.byId("appToaster").show();
                dijit.byId("snapshotEditConformationDialog").hide();
//                  core.router.go("#/admin/miscellaneous/snapshot");
                MiscSnapshotInfo.populateValues(); 
                dijit.byId("miscSnapButton").set("disabled", true);
            } else {
                registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                registry.byId("appToaster").show();
                dijit.byId("snapshotEditConformationDialog").hide();
            }              
            dijit.byId('miscSnapButton').set('style', {'display': 'block'});
            dojo.byId('miscSnapLoader').style.display = "none";
        }); 
    }        
    }     
    };
    
var MiscIpInfo = {
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
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
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("micIpCurrencyValue").innerHTML= cur.currency;
               });
        }); 
    },
    updateShow : function() {  
         dijit.byId("ipEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("ipEditConformationDialog").hide();
    },
    populateValues: function() {   
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var span = "<span class='require'>*</span>";
        if(dijit.byId("ipZone")) {
            dijit.byId("ipZone").destroyRecursive();
            dijit.byId("ipPod").destroyRecursive();
            dijit.byId("ipCluster").destroyRecursive();
        }
        if(dijit.byId("micIpCostWidget")) {
            dijit.byId("micIpCostWidget").destroyRecursive();
        }
        if(dijit.byId("ipGrid")) {
            dijit.byId("ipGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        dojo.byId("micIpInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [[
                 {'field': 'id',  'hidden' : 'true'},
                 {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true , 'hidden' : 'true'},
                 {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string",  'hidden' : 'true', autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                 }},
                 {'name': translator.common.cost, 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                       var html = "<span class='adminBolder'>"+ data + "</span>"
                       return html;  
                 }}
                    
             ]
         ];  
              
         this._misclRestStore.get(2).then(function(data) {  
             if(data.miscellaneousOfferZoneCosts.length == 0 || data.miscellaneousOfferZoneCosts == undefined || data.miscellaneousOfferZoneCosts == 'undefined' || data.miscellaneousOfferZoneCosts == '' || data.miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("nomicIpMessageBox").style.display = "block";   
                dojo.byId("micIpInfo").innerHTML = "";
             } else {
                 dojo.byId("nomicIpMessageBox").style.display = "none";   
                 dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                     summaryDataList.newItem({
                         id:miscData.id, 
                         zone: miscData.zone.aliasName, 
                         pod: translator.common.allPod, 
                         cluster: translator.common.allCluster, 
                         cost: miscData.cost 
                     });
                 });
                 dojo.byId("micIpInfo").innerHTML = "";
                 var summaryGridWidget = new EnhancedGrid({
                     id: "ipGrid",
                     "class" : "span12",
                     store: summaryDataList,
                     structure: summaryLayout,
                     plugins: core.getGridConfig().plugins,
                     autoHeight: true           
                 });
                 summaryGridWidget.placeAt("micIpInfo");
                 summaryGridWidget.startup();
             }             
         });
       
        
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectPode}]
        };
        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectCluster}]
        };
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
          
        this._clusterWidget = new FilteringSelect({
            id: "ipCluster",
            store: clusterList,
            value : "option",
            onChange : function() {                             
                var misclRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/miscellaneousOffer/"
                });    
                
                var miscelWidget = dijit.byId("micIpCostWidget");
                
                misclRestStore.query({miscellaneousOfferId: 2, 
                clusterReferenceId: this.value}).then(function(data) {                
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    miscelWidget.clearWidgets();                
                } else {                    
                    dojo.forEach(data, function(miscData) {                        
                        miscelWidget.zoneCost =  miscData.cost;
                        miscelWidget.labelName =  translator.common.CostPerIPPerMonth + span;      
                        miscelWidget.setZoneId(miscData.zone.id);
                        miscelWidget.setCost();                        
                    })   
                }                                    
            });
        }        
    }).placeAt("micIpCluster"); 
        
        this._podWidget = new FilteringSelect({
            id: "ipPod",
            store: podList,
            value : "option",
            onChange: function() {
                var clusterWidget = dijit.byId("ipCluster");
                var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
                };
                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
                if(this.value == "empty") {
                    currentClusterList.newItem({id: "empty", name: translator.common.message.selectCluster});
                    clusterWidget.set("store", currentClusterList);
                    clusterWidget.set("value", "empty");
                    
                } else {
                    var firstPodVal = ""
                    clusterRestStore.get(this.value).then(function(clusterListItems) {
                        dojo.forEach(clusterListItems, function(currentcluster, index) {
                            currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                            
                            if(index == 0) {
                                firstPodVal = currentcluster.clusterReferenceId;
                            }
                        });
                        clusterWidget.set("store", currentClusterList);
                        clusterWidget.set("value", firstPodVal);
                        
                    });
                }
            }
        }).placeAt("micIpPod");   
        var zoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectZone}]
        }; 
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
            });
        });                        
        var currentZoneCost  = new Zone.ZoneCost({
            id:"micIpCostWidget",                        
            vmRunningCostLabel: translator.common.CostPerIPPerMonth + span,
            invalidMsg : translator.common.zoneCostInvalidMsg
        });     
        currentZoneCost.removeCosts();    
        currentZoneCost.removeUnitCost();   
        currentZoneCost.placeAt("micIpCostInfo");
        currentZoneCost.startup();
        
        this._zoneWidget = new FilteringSelect({
            
            id : "ipZone",
            store: zoneList,
            value : "option",
            onChange: function() {  
                
                var podWidget = dijit.byId("ipPod");                 
                
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
                    currentPodList.newItem({id: "empty", name: translator.common.message.selectPode});
                    podWidget.set("store", currentPodList);
                    podWidget.set("value", "empty");
                    
                    dijit.byId("micIpButton").set("disabled", true);
                } else {
                    var firstPodVal = "";
                    dijit.byId("micIpButton").set("disabled", false);
                    podRestStore.get(this.value).then(function(podListItems) {
                        dojo.forEach(podListItems, function(currentPod, index) {
                            currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
                            if(index == 0) {
                                firstPodVal = currentPod.podReferenceId;
                            }
                        });
                        podWidget.set("store", currentPodList);
                        podWidget.set("value", firstPodVal);
                        
                    });
                }
                
                var zoneRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/zone/"
                });
                
                zoneRestStore.get(this.value).then(function(zoneDate) {
                    var miscelWidget = dijit.byId("micIpCostWidget")
                    miscelWidget.setZoneId(zoneDate.id);
                })
            }
        }).placeAt("micIpZone"); 
        
    },
    update: function() {       
        var podWidget = dijit.byId("ipPod");
        var zoneCosts = [];
        var clusterWidget = dijit.byId("ipCluster");
        var miscelWidget = dijit.byId("micIpCostWidget");
        var status = miscelWidget.showError();
        var zoneSatus = true;
        var zone = dijit.byId("ipZone");
        if (zone.validate && !zone.validate()) {
            zone.focus();
            zoneSatus = false;                   
        }
        if(status == true && zoneSatus == true) {
            dijit.byId("ipEditConformationDialog").hide();
            var miscelZoneCostList = dojo.byId("ipZoneCostList");
            var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCostValue()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micIpButton').set('style', {'display': 'none'});
            dojo.byId('micIpLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 2,
                zoneCosts: zoneCosts,
                zoneReferenceId : this._zoneWidget.value,
                clusterReferenceId : clusterWidget.value,
                podReferenceId : podWidget.value
            }).then(function(result)  { 
                dijit.byId('micIpButton').set('style', {'display': 'block'});
                dojo.byId('micIpLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("ipEditConformationDialog").hide();
                    MiscIpInfo.populateValues();    
                    dijit.byId("miscSnapButton").set("disabled", true);
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("ipEditConformationDialog").hide();
                }  
            });
        }        
    }     
    };    
var MiscPortForwardingInfo = {
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
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
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("micPortForwardingCurrencyValue").innerHTML= cur.currency;
               });
        }); 
    },
    updateShow : function() {  
         dijit.byId("portForwardingEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("portForwardingEditConformationDialog").hide();
    },
    populateValues: function() {   
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var span = "<span class='require'>*</span>";
        if(dijit.byId("portForwardingZone")) {
            dijit.byId("portForwardingZone").destroyRecursive();
            dijit.byId("portForwardingPod").destroyRecursive();
            dijit.byId("portForwardingCluster").destroyRecursive();
        }
        if(dijit.byId("micPortForwardingCostWidget")) {
            dijit.byId("micPortForwardingCostWidget").destroyRecursive();
        }
        if(dijit.byId("portForwardingGrid")) {
            dijit.byId("portForwardingGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        dojo.byId("micPortForwardingInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [[
                 {'field': 'id',  'hidden' : 'true'},
                 {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true , 'hidden' : 'true'},
                 {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string",  'hidden' : 'true', autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                 }},
                 {'name': translator.common.cost, 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                       var html = "<span class='adminBolder'>"+ data + "</span>"
                       return html;  
                 }}
                    
             ]
         ];  
              
         this._misclRestStore.get(6).then(function(data) {  
             if(data.miscellaneousOfferZoneCosts.length == 0 || data.miscellaneousOfferZoneCosts == undefined || data.miscellaneousOfferZoneCosts == 'undefined' || data.miscellaneousOfferZoneCosts == '' || data.miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("noMicPortForwardingMessageBox").style.display = "block";   
                dojo.byId("micPortForwardingInfo").innerHTML = "";
             } else {
                 dojo.byId("noMicPortForwardingMessageBox").style.display = "none";   
                 dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                     if(miscData.zone.networkType === "Advanced") {
                         summaryDataList.newItem({
                             id:miscData.id, 
                             zone: miscData.zone.aliasName, 
                             pod: translator.common.allPod, 
                             cluster: translator.common.allCluster, 
                             cost: miscData.cost 
                         });
                     }                     
                 });
                 dojo.byId("micPortForwardingInfo").innerHTML = "";
                 var summaryGridWidget = new EnhancedGrid({
                     id: "portForwardingGrid",
                     "class" : "span12",
                     store: summaryDataList,
                     structure: summaryLayout,
                     plugins: core.getGridConfig().plugins,
                     autoHeight: true           
                 });
                 summaryGridWidget.placeAt("micPortForwardingInfo");
                 summaryGridWidget.startup();
             }             
         });
       
        
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectPode}]
        };
        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectCluster}]
        };
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
          
        this._clusterWidget = new FilteringSelect({
            id: "portForwardingCluster",
            store: clusterList,
            value : "option",
            onChange : function() {                             
                var misclRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/miscellaneousOffer/"
                });    
                
                var miscelWidget = dijit.byId("micPortForwardingCostWidget");
                
                misclRestStore.query({miscellaneousOfferId: 6, 
                clusterReferenceId: this.value}).then(function(data) {                
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    miscelWidget.clearWidgets();                
                } else {                    
                    dojo.forEach(data, function(miscData) {                        
                        miscelWidget.zoneCost =  miscData.cost;
                        miscelWidget.labelName =  translator.common.CostPerConnectionPerMonth + span;      
                        miscelWidget.setZoneId(miscData.zone.id);
                        miscelWidget.setCost();                        
                    })   
                }                                    
            });
        }        
    }).placeAt("micPortForwardingCluster"); 
        
        this._podWidget = new FilteringSelect({
            id: "portForwardingPod",
            store: podList,
            value : "option",
            onChange: function() {
                var clusterWidget = dijit.byId("portForwardingCluster");
                var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
                };
                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
                if(this.value == "empty") {
                    currentClusterList.newItem({id: "empty", name: translator.common.message.selectCluster});
                    clusterWidget.set("store", currentClusterList);
                    clusterWidget.set("value", "empty");
                    
                } else {
                    var firstPodVal = "";
                    clusterRestStore.get(this.value).then(function(clusterListItems) {
                        dojo.forEach(clusterListItems, function(currentcluster, index) {
                            currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                            
                            if(index == 0) {
                                firstPodVal = currentcluster.clusterReferenceId;
                            }
                        });
                        clusterWidget.set("store", currentClusterList);
                        clusterWidget.set("value", firstPodVal);
                        
                    });
                }
            }
        }).placeAt("micPortForwardingPod");   
        var zoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectZone}]
        }; 
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if(el.networkType === "Advanced") {             
                    zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
                }
            });
        });                        
        var currentZoneCost  = new Zone.ZoneCost({
            id:"micPortForwardingCostWidget",                        
            vmRunningCostLabel: translator.common.CostPerConnectionPerMonth + span,
            invalidMsg : translator.common.zoneCostInvalidMsg
        });     
        currentZoneCost.removeCosts();    
        currentZoneCost.removeUnitCost();   
        currentZoneCost.placeAt("micLoadbancerCostInfo");
        currentZoneCost.startup();
        
        this._zoneWidget = new FilteringSelect({
            
            id : "portForwardingZone",
            store: zoneList,
            value : "option",
            onChange: function() {  
                
                var podWidget = dijit.byId("portForwardingPod");                 
                
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
                    currentPodList.newItem({id: "empty", name: translator.common.message.selectPode});
                    podWidget.set("store", currentPodList);
                    podWidget.set("value", "empty");
                    
                    dijit.byId("micPortForwardingButton").set("disabled", true);
                } else {
                    var firstPodVal = "";
                    dijit.byId("micPortForwardingButton").set("disabled", false);
                    podRestStore.get(this.value).then(function(podListItems) {
                        dojo.forEach(podListItems, function(currentPod, index) {
                            currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
                            if(index == 0) {
                                firstPodVal = currentPod.podReferenceId;
                            }
                        });
                        podWidget.set("store", currentPodList);
                        podWidget.set("value", firstPodVal);
                        
                    });
                }
                
                var zoneRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/zone/"
                });
                
                zoneRestStore.get(this.value).then(function(zoneDate) {
                    var miscelWidget = dijit.byId("micPortForwardingCostWidget");
                    miscelWidget.setZoneId(zoneDate.id);
                });
            }
        }).placeAt("micPortForwardingZone"); 
        
    },
    update: function() {       
        var podWidget = dijit.byId("portForwardingPod");
        var zoneCosts = [];
        var clusterWidget = dijit.byId("portForwardingCluster");
        var miscelWidget = dijit.byId("micPortForwardingCostWidget");
        var status = miscelWidget.showError();
        var zoneSatus = true;
        var zone = dijit.byId("portForwardingZone");
        if (zone.validate && !zone.validate()) {
            zone.focus();
            zoneSatus = false;                   
        }
        if(status == true && zoneSatus == true) {
            dijit.byId("portForwardingEditConformationDialog").hide();
            var miscelZoneCostList = dojo.byId("portForwardingZoneCostList");
            var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCostValue()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micPortForwardingButton').set('style', {'display': 'none'});
            dojo.byId('micPortForwardingLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 6,
                zoneCosts: zoneCosts,
                zoneReferenceId : this._zoneWidget.value,
                clusterReferenceId : clusterWidget.value,
                podReferenceId : podWidget.value
            }).then(function(result)  { 
                dijit.byId('micPortForwardingButton').set('style', {'display': 'block'});
                dojo.byId('micPortForwardingLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("portForwardingEditConformationDialog").hide();
                    MiscPortForwardingInfo.populateValues();    
                    dijit.byId("miscSnapButton").set("disabled", true);
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("portForwardingEditConformationDialog").hide();
                }  
            });
        }        
    }     
};    

var MiscLoadBalancerInfo = {
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
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
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("micLoadbalancerCurrencyValue").innerHTML= cur.currency;
               });
        }); 
    },
    updateShow : function() {  
         dijit.byId("loadBalancerEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("loadBalancerEditConformationDialog").hide();
    },
    populateValues: function() {           
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var span = "<span class='require'>*</span>";
        if(dijit.byId("loadBalancerZone")) {
            dijit.byId("loadBalancerZone").destroyRecursive();
            dijit.byId("loadBalancerPod").destroyRecursive();
            dijit.byId("loadBalancerCluster").destroyRecursive();
        }
        if(dijit.byId("micLoadBalancerCostWidget")) {
            dijit.byId("micLoadBalancerCostWidget").destroyRecursive();
        }
        if(dijit.byId("loadBalancerGrid")) {
            dijit.byId("loadBalancerGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        dojo.byId("micLoadBalancerInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [[
                 {'field': 'id',  'hidden' : 'true'},
                 {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true , 'hidden' : 'true'},
                 {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string",  'hidden' : 'true', autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                 }},
                 {'name': translator.common.cost, 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                       var html = "<span class='adminBolder'>"+ data + "</span>"
                       return html;  
                 }}
                    
             ]
         ];  
              
         this._misclRestStore.get(5).then(function(data) {  
             if(data.miscellaneousOfferZoneCosts.length == 0 || data.miscellaneousOfferZoneCosts == undefined || data.miscellaneousOfferZoneCosts == 'undefined' || data.miscellaneousOfferZoneCosts == '' || data.miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("noMicLoadBalancerMessageBox").style.display = "block";   
                dojo.byId("micLoadBalancerInfo").innerHTML = "";
             } else {
                 dojo.byId("noMicLoadBalancerMessageBox").style.display = "none";   
                 dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                     if(miscData.zone.networkType === "Advanced") {
                         summaryDataList.newItem({
                             id:miscData.id, 
                             zone: miscData.zone.aliasName, 
                             pod: translator.common.allPod, 
                             cluster: translator.common.allCluster, 
                             cost: miscData.cost 
                         });                         
                     }                         
                 });
                 dojo.byId("micLoadBalancerInfo").innerHTML = "";
                 var summaryGridWidget = new EnhancedGrid({
                     id: "loadBalancerGrid",
                     "class": "span12",
                     store: summaryDataList,
                     structure: summaryLayout,
                     plugins: core.getGridConfig().plugins,
                     autoHeight: true           
                 });
                 summaryGridWidget.placeAt("micLoadBalancerInfo");
                 summaryGridWidget.startup();
             }             
         });
       
        
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectPode}]
        };
        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectCluster}]
        };
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
          
        this._clusterWidget = new FilteringSelect({
            id: "loadBalancerCluster",
            store: clusterList,
            value : "option",
            onChange : function() {                             
                var misclRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/miscellaneousOffer/"
                });    
                
                var miscelWidget = dijit.byId("micLoadBalancerCostWidget");
                
                misclRestStore.query({miscellaneousOfferId: 5, 
                clusterReferenceId: this.value}).then(function(data) {                
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    miscelWidget.clearWidgets();                
                } else {                    
                    dojo.forEach(data, function(miscData) {                        
                        miscelWidget.zoneCost =  miscData.cost;
                        miscelWidget.labelName =  translator.common.CostPerPolicyPerMonth + span;      
                        miscelWidget.setZoneId(miscData.zone.id);
                        miscelWidget.setCost();                        
                    })   
                }                                    
            });
        }        
    }).placeAt("micLoadBalancerCluster"); 
        
        this._podWidget = new FilteringSelect({
            id: "loadBalancerPod",
            store: podList,
            value : "option",
            onChange: function() {
                var clusterWidget = dijit.byId("loadBalancerCluster");
                var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
                };
                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
                if(this.value == "empty") {
                    currentClusterList.newItem({id: "empty", name: translator.common.message.selectCluster});
                    clusterWidget.set("store", currentClusterList);
                    clusterWidget.set("value", "empty");
                    
                } else {
                    var firstPodVal = "";
                    clusterRestStore.get(this.value).then(function(clusterListItems) {
                        dojo.forEach(clusterListItems, function(currentcluster, index) {
                            currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                            
                            if(index == 0) {
                                firstPodVal = currentcluster.clusterReferenceId;
                            }
                        });
                        clusterWidget.set("store", currentClusterList);
                        clusterWidget.set("value", firstPodVal);
                        
                    });
                }
            }
        }).placeAt("micLoadBalancerPod");   
        var zoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectZone}]
        }; 
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if(el.networkType === "Advanced") {
                    zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
                }                
            });
        });                        
        var currentZoneCost  = new Zone.ZoneCost({
            id:"micLoadBalancerCostWidget",                        
            vmRunningCostLabel: translator.common.CostPerPolicyPerMonth + span,
            invalidMsg : translator.common.zoneCostInvalidMsg
        });     
        currentZoneCost.removeCosts();    
        currentZoneCost.removeUnitCost();   
        currentZoneCost.placeAt("micLoadbancerCostInfo");
        currentZoneCost.startup();
        
        this._zoneWidget = new FilteringSelect({
            
            id : "loadBalancerZone",
            store: zoneList,
            value : "option",
            onChange: function() {  
                
                var podWidget = dijit.byId("loadBalancerPod");                 
                
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
                    currentPodList.newItem({id: "empty", name: translator.common.message.selectPode});
                    podWidget.set("store", currentPodList);
                    podWidget.set("value", "empty");
                    
                    dijit.byId("micLoadBalancerButton").set("disabled", true);
                } else {
                    var firstPodVal = "";
                    dijit.byId("micLoadBalancerButton").set("disabled", false);
                    podRestStore.get(this.value).then(function(podListItems) {
                        dojo.forEach(podListItems, function(currentPod, index) {
                            currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
                            if(index == 0) {
                                firstPodVal = currentPod.podReferenceId;
                            }
                        });
                        podWidget.set("store", currentPodList);
                        podWidget.set("value", firstPodVal);
                        
                    });
                }
                
                var zoneRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/zone/"
                });
                
                zoneRestStore.get(this.value).then(function(zoneDate) {
                    var miscelWidget = dijit.byId("micLoadBalancerCostWidget");
                    miscelWidget.setZoneId(zoneDate.id);
                });
            }
        }).placeAt("micLoadBalancerZone"); 
        
    },
    update: function() {       
        var podWidget = dijit.byId("loadBalancerPod");
        var zoneCosts = [];
        var clusterWidget = dijit.byId("loadBalancerCluster");
        var miscelWidget = dijit.byId("micLoadBalancerCostWidget");
        var status = miscelWidget.showError();
        var zoneSatus = true;
        var zone = dijit.byId("loadBalancerZone");
        if (zone.validate && !zone.validate()) {
            zone.focus();
            zoneSatus = false;                   
        }
        if(status == true && zoneSatus == true) {
            dijit.byId("loadBalancerEditConformationDialog").hide();
            var miscelZoneCostList = dojo.byId("loadBalancerZoneCostList");
            var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCostValue()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micLoadBalancerButton').set('style', {'display': 'none'});
            dojo.byId('micLoadBalancerLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 5,
                zoneCosts: zoneCosts,
                zoneReferenceId : this._zoneWidget.value,
                clusterReferenceId : clusterWidget.value,
                podReferenceId : podWidget.value
            }).then(function(result)  { 
                dijit.byId('micLoadBalancerButton').set('style', {'display': 'block'});
                dojo.byId('micLoadBalancerLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("loadBalancerEditConformationDialog").hide();
                    MiscLoadBalancerInfo.populateValues();    
                    dijit.byId("miscSnapButton").set("disabled", true);
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("loadBalancerEditConformationDialog").hide();
                }  
            });
        }        
    }     
    };
    var MiscVMSnapCostInfo = {
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
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
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data,function(cur) { 
                dojo.byId("miscVMSnapshotCurrencyValue").innerHTML= cur.currency;
            });
        }); 
    },
    updateShow : function() {  
         dijit.byId("vmSnapshotEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("vmSnapshotEditConformationDialog").hide();
    },
    populateValues: function() {   
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var span = "<span class='require'>*</span>";
        if(dijit.byId("VMSnapshotGrid")) {
            dijit.byId("VMSnapshotGrid").destroyRecursive();
        }
        if(dijit.byId("VMSnapshotZone")) {
            dijit.byId("VMSnapshotZone").destroyRecursive();
            dijit.byId("VMSnapshotPod").destroyRecursive();
            dijit.byId("VMSnapshotCluster").destroyRecursive();
        }
        
        if(dijit.byId("miscVMSnapshotZoneCostWidget")) {
            dijit.byId("miscVMSnapshotZoneCostWidget").destroyRecursive();
        }
        dojo.byId("miscVMSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [[
                 {'field': 'id',  'hidden' : 'true'},
                 {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true, 'hidden' : 'true'},
                 {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string", 'hidden' : 'true',  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                 }},
                 {'name': translator.common.cost, 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                 }}
                    
             ]
         ];  
              
         this._misclRestStore.get(4).then(function(data) {
             if(data.miscellaneousOfferZoneCosts.length == 0 || data.miscellaneousOfferZoneCosts == undefined || data.miscellaneousOfferZoneCosts == 'undefined' || data.miscellaneousOfferZoneCosts == '' || data.miscellaneousOfferZoneCosts == " ") {
                 dojo.byId("noMiscVMSnapshotMessageBox").style.display = "block";   
                 dojo.byId("miscVMSnapshotInfo").innerHTML = "";
             } else {
                 dojo.byId("noMiscVMSnapshotMessageBox").style.display = "none"; 
                 
                 dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                     summaryDataList.newItem({
                         id:miscData.id, 
                         zone: miscData.zone.aliasName,
                         pod: "All Pod",
                         cluster: "All Cluster",
                         cost:miscData.cost 
                        });
                 });
                 dojo.byId("miscVMSnapshotInfo").innerHTML = "";
                 var gridWidget = new EnhancedGrid({
                     id: "VMSnapshotGrid",
                     "class" : "span12",
                     store: summaryDataList,
                     structure: summaryLayout,
                     autoHeight: true,
                     plugins: core.getGridConfig().plugins                     
                 });
                 gridWidget.placeAt("miscVMSnapshotInfo");
                 gridWidget.startup();
             }             
         });               
         var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectPode}]
        };
        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectCluster}]
        };
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
          
        this._clusterWidget = new FilteringSelect({
            id: "VMSnapshotCluster",
            store: clusterList,
            value : "option",
            onChange : function() {
                var misclRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/miscellaneousOffer/"
                });  

                var miscelZoneNode = dojo.byId("VMSnapshotZoneCostList");
                var miscelZoneWidgets = dijit.registry.findWidgets(miscelZoneNode);
                var miscWidget = dijit.byId("miscVMSnapshotZoneCostWidget");
                
                misclRestStore.query({miscellaneousOfferId: 4, 
                clusterReferenceId: this.value}).then(function(data) {
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                    miscWidget.clearWidgets();                    
                } else {
                    dojo.forEach(data, function(miscData) {              
                        if(miscData) {                      
                            miscWidget.zoneIdNode = miscData.zone.id;
                            miscWidget.zoneCost = miscData.cost * 720;
                            miscWidget.labelName = translator.common.cost + "/" +translator.common.gb + "/" + translator.common.month;
//                            miscWidget.costRate =translator.common.billingCostPeGBHR;                        
                            miscWidget.setCost(); 
//                            miscWidget.setCostRate();   
                            miscWidget.setZoneId(miscData.zone.id);
                        } else {                    
                            miscWidget.clearWidgets();
                        }
                    }); 
                }
            });             
        }
    }).placeAt("miscVMSnapshotCluster");     
    this._podWidget = new FilteringSelect({
        id: "VMSnapshotPod",
        store: podList,
        value : "option",
        onChange: function() {
            var clusterWidget = dijit.byId("VMSnapshotCluster");
            var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            };
            var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
            if(this.value == "empty") {
                currentClusterList.newItem({id: "empty", name:translator.common.message.selectCluster});
                clusterWidget.set("store", currentClusterList);
                clusterWidget.set("value", "empty");
                
            } else {
                var firstClusterVal = "";
                clusterRestStore.get(this.value).then(function(clusterListItems) {
                    dojo.forEach(clusterListItems, function(currentcluster, index) {
                        currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                        if(index == 0) {
                            firstClusterVal = currentcluster.clusterReferenceId;
                        }
                    });
                    clusterWidget.set("store", currentClusterList);
                    clusterWidget.set("value", firstClusterVal);
                    
                });
            };
        }
    }).placeAt("miscVMSnapshotPod");         
   
    var currentZoneCost  = new Zone.ZoneCost({
        id:"miscVMSnapshotZoneCostWidget",
        vmRunningCostLabel: translator.common.CostPerGBPerMonth + span,
        runningCostPerHrLabel: translator.common.billingCostPeGBHR,
        invalidMsg : translator.common.zoneCostInvalidMsg
    });       
    currentZoneCost.setCostRate();   
    currentZoneCost.showOnlyZoneCost();     
    currentZoneCost.placeAt("miscVMSnapshotCostInfo");
    currentZoneCost.startup();       
    
    var zoneOptions = { 
        identifier: 'id',
        label: 'name',
        items: [{id: "option", name: translator.common.message.selectZone}]
    };         
    
    var zoneList = new ItemFileWriteStore({data: zoneOptions});
    this._zoneRestStore.query().then(function(data) {
        dojo.forEach(data, function(el) {
            zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
        });
    });                            
    
    this._zoneWidget = new FilteringSelect({
        id : "VMSnapshotZone",
        store: zoneList,
        value : "option",
        onChange: function() {                  
            var podWidget = dijit.byId("VMSnapshotPod");                                     
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
                currentPodList.newItem({id: "empty", name: translator.common.message.selectPode});
                podWidget.set("store", currentPodList);
                podWidget.set("value", "empty");
                dijit.byId("miscVMSnapButton").set("disabled", true);
            } else {
                dijit.byId("miscVMSnapButton").set("disabled", false);
                var firstPodVal = "";
                podRestStore.get(this.value).then(function(podListItems) {
                    dojo.forEach(podListItems, function(currentPod, index) {
                        currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});                        
                        if(index == 0) {
                            firstPodVal =  currentPod.podReferenceId;
                        }
                    });
                    podWidget.set("store", currentPodList);
                    podWidget.set("value", firstPodVal);
                    
                });
            }                
            var zoneRestStore = new JsonRest({
                target: core.getContextPath()+"/api/zone/"
            });                
            zoneRestStore.get(this.value).then(function(zoneData) {                  
                var zoneCostWidget = dijit.byId("miscVMSnapshotZoneCostWidget");
                zoneCostWidget.zoneIdNode = zoneData.id,                       
                zoneCostWidget.setZoneId(zoneData.id);
            });
        }
    }).placeAt("miscVMSnapshotZone"); 
},
update: function() {       
    var podWidget = dijit.byId("VMSnapshotPod");
    var zoneCosts = [];
    var clusterWidget = dijit.byId("VMSnapshotCluster");
    var zoneCostWidget = dijit.byId("miscVMSnapshotZoneCostWidget");
    var status = zoneCostWidget.showError();
    var zoneSatus = true;
    var zone = dijit.byId("VMSnapshotZone");
    if (zone.validate && !zone.validate()) {
        zone.focus();
        zoneSatus = false;                   
    }
    if(status == true && zoneSatus == true) {
        dijit.byId("vmSnapshotEditConformationDialog").hide();
        var miscelZoneCostList = dojo.byId("VMSnapshotZoneCostList");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {          
            zoneCosts.push({
                zoneId: el.getZoneId(), 
                cost: el.getZoneCost()
            });
        });         
        for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
            zoneCosts.splice(index, miscelZoneCostWidgets.length);
        } 
        dijit.byId('miscVMSnapButton').set('style', {'display': 'none'});
        dojo.byId('miscVMSnapLoader').style.display = "block";
        this._misclRestStore.put({
            id: 4,
            zoneCosts: zoneCosts,
            zoneReferenceId : this._zoneWidget.value,
            clusterReferenceId : clusterWidget.value,
            podReferenceId : podWidget.value
        }).then(function(result)  {        
            if(result == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                registry.byId("appToaster").show();
                dijit.byId("vmSnapshotEditConformationDialog").hide();
//                  core.router.go("#/admin/miscellaneous/snapshot");
                MiscVMSnapCostInfo.populateValues(); 
                dijit.byId("miscVMSnapButton").set("disabled", true);
            } else {
                registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                registry.byId("appToaster").show();
                dijit.byId("vmSnapshotEditConformationDialog").hide();
            }              
            dijit.byId('miscVMSnapButton').set('style', {'display': 'block'});
            dojo.byId('miscVMSnapLoader').style.display = "none";
        }); 
    }        
    }     
    };
    var MiscVpnInfo = {
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
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
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("micVpnCurrencyValue").innerHTML= cur.currency;
               });
        }); 
    },
    updateShow : function() {  
         dijit.byId("vpnEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("vpnEditConformationDialog").hide();
    },
    populateValues: function() {   
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var span = "<span class='require'>*</span>";
        if(dijit.byId("vpnZone")) {
            dijit.byId("vpnZone").destroyRecursive();
            dijit.byId("vpnPod").destroyRecursive();
            dijit.byId("vpnCluster").destroyRecursive();
        }
        if(dijit.byId("micVpnCostWidget")) {
            dijit.byId("micVpnCostWidget").destroyRecursive();
        }
        if(dijit.byId("vpnGrid")) {
            dijit.byId("vpnGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        dojo.byId("micVpnInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [[
                 {'field': 'id',  'hidden' : 'true'},
                 {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true , 'hidden' : 'true'},
                 {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string",  'hidden' : 'true', autoComplete: true, 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;
                 }},
                 {'name': translator.common.cost, 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                       var html = "<span class='adminBolder'>"+ data + "</span>"
                       return html;  
                 }}
                    
             ]
         ];  
              
         this._misclRestStore.get(7).then(function(data) {  
             if(data.miscellaneousOfferZoneCosts.length == 0 || data.miscellaneousOfferZoneCosts == undefined || data.miscellaneousOfferZoneCosts == 'undefined' || data.miscellaneousOfferZoneCosts == '' || data.miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("noMicVpnMessageBox").style.display = "block";   
                dojo.byId("micVpnInfo").innerHTML = "";
             } else {
                 dojo.byId("noMicVpnMessageBox").style.display = "none";   
                 dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                     if(miscData.zone.networkType === "Advanced") {
                         summaryDataList.newItem({
                             id:miscData.id, 
                             zone: miscData.zone.aliasName, 
                             pod: translator.common.allPod, 
                             cluster: translator.common.allCluster, 
                             cost: miscData.cost 
                         });
                     }                     
                 });
                 dojo.byId("micVpnInfo").innerHTML = "";
                 var summaryGridWidget = new EnhancedGrid({
                     id: "vpnGrid",
                     "class" : "span12",
                     store: summaryDataList,
                     structure: summaryLayout,
                     plugins: core.getGridConfig().plugins,
                     autoHeight: true           
                 });
                 summaryGridWidget.placeAt("micVpnInfo");
                 summaryGridWidget.startup();
             }             
         });
       
        
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectPode}]
        };
        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectCluster}]
        };
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
          
        this._clusterWidget = new FilteringSelect({
            id: "vpnCluster",
            store: clusterList,
            value : "option",
            onChange : function() {                             
                var misclRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/miscellaneousOffer/"
                });    
                
                var miscelWidget = dijit.byId("micVpnCostWidget");
                
                misclRestStore.query({miscellaneousOfferId: 7, 
                clusterReferenceId: this.value}).then(function(data) {                
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    miscelWidget.clearWidgets();                
                } else {                    
                    dojo.forEach(data, function(miscData) {                        
                        miscelWidget.zoneCost =  miscData.cost;
                        miscelWidget.labelName =  translator.common.CostPerConnectionPerMonth + span;      
                        miscelWidget.setZoneId(miscData.zone.id);
                        miscelWidget.setCost();                        
                    })   
                }                                    
            });
        }        
    }).placeAt("micVpnCluster"); 
        
        this._podWidget = new FilteringSelect({
            id: "vpnPod",
            store: podList,
            value : "option",
            onChange: function() {
                var clusterWidget = dijit.byId("vpnCluster");
                var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
                };
                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
                if(this.value == "empty") {
                    currentClusterList.newItem({id: "empty", name: translator.common.message.selectCluster});
                    clusterWidget.set("store", currentClusterList);
                    clusterWidget.set("value", "empty");
                    
                } else {
                    var firstPodVal = "";
                    clusterRestStore.get(this.value).then(function(clusterListItems) {
                        dojo.forEach(clusterListItems, function(currentcluster, index) {
                            currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                            
                            if(index == 0) {
                                firstPodVal = currentcluster.clusterReferenceId;
                            }
                        });
                        clusterWidget.set("store", currentClusterList);
                        clusterWidget.set("value", firstPodVal);
                        
                    });
                }
            }
        }).placeAt("micVpnPod");   
        var zoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectZone}]
        }; 
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if(el.networkType === "Advanced") {             
                    zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
                }
            });
        });                        
        var currentZoneCost  = new Zone.ZoneCost({
            id:"micVpnCostWidget",                        
            vmRunningCostLabel: translator.common.CostPerConnectionPerMonth + span,
            invalidMsg : translator.common.zoneCostInvalidMsg
        });     
        currentZoneCost.removeCosts();    
        currentZoneCost.removeUnitCost();   
        currentZoneCost.placeAt("micLoadbancerCostInfo");
        currentZoneCost.startup();
        
        this._zoneWidget = new FilteringSelect({
            
            id : "vpnZone",
            store: zoneList,
            value : "option",
            onChange: function() {  
                
                var podWidget = dijit.byId("vpnPod");                 
                
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
                    currentPodList.newItem({id: "empty", name: translator.common.message.selectPode});
                    podWidget.set("store", currentPodList);
                    podWidget.set("value", "empty");
                    
                    dijit.byId("micVpnButton").set("disabled", true);
                } else {
                    var firstPodVal = "";
                    dijit.byId("micVpnButton").set("disabled", false);
                    podRestStore.get(this.value).then(function(podListItems) {
                        dojo.forEach(podListItems, function(currentPod, index) {
                            currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
                            if(index == 0) {
                                firstPodVal = currentPod.podReferenceId;
                            }
                        });
                        podWidget.set("store", currentPodList);
                        podWidget.set("value", firstPodVal);
                        
                    });
                }
                
                var zoneRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/zone/"
                });
                
                zoneRestStore.get(this.value).then(function(zoneDate) {
                    var miscelWidget = dijit.byId("micVpnCostWidget");
                    miscelWidget.setZoneId(zoneDate.id);
                });
            }
        }).placeAt("micVpnZone"); 
        
    },
    update: function() {       
        var podWidget = dijit.byId("vpnPod");
        var zoneCosts = [];
        var clusterWidget = dijit.byId("vpnCluster");
        var miscelWidget = dijit.byId("micVpnCostWidget");
        var status = miscelWidget.showError();
        var zoneSatus = true;
        var zone = dijit.byId("vpnZone");
        if (zone.validate && !zone.validate()) {
            zone.focus();
            zoneSatus = false;                   
        }
        if(status == true && zoneSatus == true) {
            dijit.byId("vpnEditConformationDialog").hide();
            var miscelZoneCostList = dojo.byId("vpnZoneCostList");
            var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCostValue()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micVpnButton').set('style', {'display': 'none'});
            dojo.byId('micVpnLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 7,
                zoneCosts: zoneCosts,
                zoneReferenceId : this._zoneWidget.value,
                clusterReferenceId : clusterWidget.value,
                podReferenceId : podWidget.value
            }).then(function(result)  { 
                dijit.byId('micVpnButton').set('style', {'display': 'block'});
                dojo.byId('micVpnLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("vpnEditConformationDialog").hide();
                    MiscVpnInfo.populateValues();    
//                    dijit.byId("miscSnapButton").set("disabled", true);
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("vpnEditConformationDialog").hide();
                }  
            });
        }        
    }     
};
window.BandwidthInfo = BandwidthInfo;
window.MiscSnapshotInfo = MiscSnapshotInfo;
window.MiscIpInfo = MiscIpInfo;
window.MiscVMSnapCostInfo = MiscVMSnapCostInfo;
