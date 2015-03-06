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
    "dojo/currency",
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
    "FogPanel/ZonePrice",
    "FogPanel/MiscZonePrice",    
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
    ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win, localeCurrency) {    
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
        window.localeCurrency = localeCurrency;
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
                 if(dijit.byId("bandEditConformationDialog")) {                    
                     dijit.byId("bandEditConformationDialog").destroyRecursive();
                 }
                 if(dijit.byId("miscBandwidthButton")) {                    
                     dijit.byId("miscBandwidthButton").destroyRecursive();
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
    _zoneRestStore:"",    
    init: function() {
        this._misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
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
    closeUpdate : function() {  
         dijit.byId("bandEditConformationDialog").hide();
    },
    populateValues: function() {                    
        if(dijit.byId("bandwidthGrid")) {
            dijit.byId("bandwidthGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        dojo.byId("miscBandwidthInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = "";
        configRestStore.query().then(function (currencyData) {        
            summaryLayout = [
                [
                    {'field': 'id',  'hidden' : 'true'},
                    {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true, 'hidden' : 'true'},
                    {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string", 'hidden' : 'true',  autoComplete: true, 'formatter' : function(data){                        
                            var html = "<span class='adminBolder'>"+ data + "</span>"
                            return html;
                        }
                    },
                    {'name': translator.common.cost + " " + translator.common.gbPerMonth + " ("+ currencyData[0].currency +")", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                            var html = "<span class='adminBolder'>"+ data + "</span>"
                            return html;
                        }
                    }                    
                ]
            ];        
        });
        this._misclRestStore.get(1).then(function(data) {  
            if(data[0].miscellaneousOfferZoneCosts.length == 0 || data[0].miscellaneousOfferZoneCosts == undefined || data[0].miscellaneousOfferZoneCosts == 'undefined' || data[0].miscellaneousOfferZoneCosts == '' || data[0].miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("noMiscBandwidthMessageBox").style.display = "block";   
                dojo.byId("miscBandwidthInfo").innerHTML = "";
            } else {
                dojo.byId("noMiscBandwidthMessageBox").style.display = "none";   
                dojo.forEach(data[0].miscellaneousOfferZoneCosts, function(miscData) {
                    summaryDataList.newItem({
                        id:miscData.id, 
                        zone: miscData.zone.aliasName, 
                        pod: translator.common.allPod, 
                        cluster: translator.common.allCluster, 
                        cost: localeCurrency.format(miscData.cost)
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
        
        dojo.query("#bandwidthZoneLoader").removeClass("hide_text", true);
        dojo.query("#miscBandwidthCostInfo").toggleClass("hide_text", true);
        dojo.query("#bandwidthPriceLabelContainer").toggleClass("hide_text", true);        
        dojo.query("#bandwidthUpdateActionButton").toggleClass("hide_text", true);        
        
        this._zoneRestStore.query().then(function(data) {    
            dojo.forEach(data, function (el, index) {
                if(dijit.byId("bandwidth_zone_widget_"+el.name+"_"+index)) {
                    dijit.byId("bandwidth_zone_widget_"+el.name+"_"+index).destroyRecursive();
                }
                var currentZonePrice  = new FogPanel.MiscZonePrice({
                    id:"bandwidth_zone_widget_"+el.name+"_"+index,
                    zoneName: el.name,
                    zoneIdNode: el.id,
                    invalidMsg : translator.common.invalidCost
                });
                currentZonePrice.hideHrCost();
                currentZonePrice.placeAt("miscBandwidthCostInfo");                  
                dojo.query("#bandwidthZoneLoader").toggleClass("hide_text", true);
                dojo.query("#miscBandwidthCostInfo").removeClass("hide_text", true);
                dojo.query("#bandwidthPriceLabelContainer").removeClass("hide_text", true);
                dojo.query("#bandwidthUpdateActionButton").removeClass("hide_text", true);        
            });
        });                                
    },
    updateShow : function() {       
        var stat = true;
        var miscelZoneCostList = dojo.byId("miscBandwidthCostInfo");
            var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
               var currentState = el.validateField();
               if(currentState === false) {
                   stat = false;
               }
            }); 
        if(stat === true) {
            dijit.byId("bandEditConformationDialog").show();
        }         
    },
    update: function() {              
        var zoneCosts = [];    
        var status = true;
        var miscelZoneCostList = dojo.byId("miscBandwidthCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);
        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
               var currentState = el.validateField();
               if(currentState === false) {
                   status = false;
               }
            }); 
        if(status === true) {
            dijit.byId("bandEditConformationDialog").hide();                    
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneMonthCost()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }            
            dijit.byId('miscBandwidthButton').set('style', {'display': 'none'});
            dojo.byId('miscBandwidthLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 1,
                zoneCosts: zoneCosts                
            }).then(function(result)  { 
                dijit.byId('miscBandwidthButton').set('style', {'display': 'block'});
                dojo.byId('miscBandwidthLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("bandEditConformationDialog").hide();
                    BandwidthInfo.populateValues();                       
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
    _zoneRestStore:"",    
    init: function() {
        this._misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
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
    populateValues: function() {                           
        if(dijit.byId("snapshotGrid")) {
            dijit.byId("snapshotGrid").destroyRecursive();
        }        
        
        dojo.byId("miscSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = "";
        configRestStore.query().then(function (currencyData) {
            summaryLayout = [
                [
                    {'field': 'id',  'hidden' : 'true'},
                    {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true, 'hidden' : 'true'},
                    {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string", 'hidden' : 'true',  autoComplete: true, 'formatter' : function(data) {
                            var html = "<span class='adminBolder'>"+ data + "</span>"
                            return html;
                        }
                    },
                    {'name': translator.common.cost + " " + translator.common.gbPerHr + " ("+ currencyData[0].currency +")", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                           var html = "<span class='adminBolder'>"+ data + "</span>"
                           return html;
                    }
                }                    
            ]
        ];              
    });
                      
    this._misclRestStore.get(3).then(function(data) {
        if(data[0].miscellaneousOfferZoneCosts.length == 0 || data[0].miscellaneousOfferZoneCosts == undefined || data[0].miscellaneousOfferZoneCosts == 'undefined' || data[0].miscellaneousOfferZoneCosts == '' || data[0].miscellaneousOfferZoneCosts == " ") {
            dojo.byId("noMiscSnapshotMessageBox").style.display = "block";   
            dojo.byId("miscSnapshotInfo").innerHTML = "";
        } else {
            dojo.byId("noMiscSnapshotMessageBox").style.display = "none"; 

            dojo.forEach(data[0].miscellaneousOfferZoneCosts, function(miscData) {
                summaryDataList.newItem({
                    id:miscData.id, 
                    zone: miscData.zone.aliasName,
                    pod: translator.common.allPod,
                    cluster: translator.common.allCluster,
                    cost: localeCurrency.format(miscData.cost, {places: 5, locale: dojo.locale})
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
    dojo.query("#snapshotZoneLoader").removeClass("hide_text", true);
    dojo.query("#miscSnapshotCostInfo").toggleClass("hide_text", true);
    dojo.query("#snapshotPriceLabelContainer").toggleClass("hide_text", true);        
    dojo.query("#snapUpdateActionButton").toggleClass("hide_text", true);            
    
    this._zoneRestStore.query().then(function(data) {    
        dojo.forEach(data, function (el, index) {
            if(dijit.byId("snap_zone_widget_"+el.name+"_"+index)) {
                dijit.byId("snap_zone_widget_"+el.name+"_"+index).destroyRecursive();
            }
            var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                id:"snap_zone_widget_"+el.name+"_"+index,
                zoneName: el.name,
                invalidMsg : translator.common.invalidCost,
                zoneIdNode: el.id                
            });                
            currentSnapZonePrice.placeAt("miscSnapshotCostInfo");                  
            dojo.query("#snapshotZoneLoader").toggleClass("hide_text", true);
            dojo.query("#miscSnapshotCostInfo").removeClass("hide_text", true);
            dojo.query("#snapshotPriceLabelContainer").removeClass("hide_text", true);
            dojo.query("#snapUpdateActionButton").removeClass("hide_text", true);        
        });
    });                                 
    },
    updateShow : function() {          
        var stat = true;
        var miscelZoneCostList = dojo.byId("miscSnapshotCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                   stat = false;
               }
            }); 
        if(stat === true) {
            dijit.byId("snapshotEditConformationDialog").show();
        }  
        
    },
    closeUpdate : function() {  
         dijit.byId("snapshotEditConformationDialog").hide();
    },
update: function() {          
    var zoneCosts = [];
    var stat = true;
    var miscelZoneCostList = dojo.byId("miscSnapshotCostInfo");
    var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
    dojo.forEach(miscelZoneCostWidgets, function(el) {                      
        var currentState = el.validateField();
        if(currentState === false) {
            stat = false;
        }
    }); 
    
    if(stat == true) {
        dijit.byId("snapshotEditConformationDialog").hide();                
        dojo.forEach(miscelZoneCostWidgets, function(el) {          
            zoneCosts.push({
                zoneId: el.getZoneId(), 
                cost: el.getZoneHrCost()
            });
        });         
        for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
            zoneCosts.splice(index, miscelZoneCostWidgets.length);
        } 
        dijit.byId('miscSnapButton').set('style', {'display': 'none'});
        dojo.byId('miscSnapLoader').style.display = "block";
        this._misclRestStore.put({
            id: 3,
            zoneCosts: zoneCosts            
        }).then(function(result)  {        
            if(result == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                registry.byId("appToaster").show();
                dijit.byId("snapshotEditConformationDialog").hide();
                MiscSnapshotInfo.populateValues();                 
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
    _zoneRestStore:"",    
    init: function() {
        this._misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
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
    
    populateValues: function() {                                   
        if(dijit.byId("ipGrid")) {
            dijit.byId("ipGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        
        dojo.byId("micIpInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = ""
        configRestStore.query().then(function (currencyData) {
            summaryLayout = [
                [
                    {'field': 'id',  'hidden' : 'true'},
                    {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true , 'hidden' : 'true'},
                    {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string",  'hidden' : 'true', autoComplete: true, 'formatter' : function(data) {
                            var html = "<span class='adminBolder'>"+ data + "</span>"
                            return html;
                        }
                    },
                    {'name': translator.common.CostPerIPPerMonth  + " ("+ currencyData[0].currency +")", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                            var html = "<span class='adminBolder'>"+ data + "</span>";
                            return html;  
                        }
                    }                    
                ]
            ];             
        });                       
        this._misclRestStore.get(2).then(function(data) {  
            if(data[0].miscellaneousOfferZoneCosts.length == 0 || data[0].miscellaneousOfferZoneCosts == undefined || data[0].miscellaneousOfferZoneCosts == 'undefined' || data[0].miscellaneousOfferZoneCosts == '' || data[0].miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("nomicIpMessageBox").style.display = "block";   
                dojo.byId("micIpInfo").innerHTML = "";
             } else {
                 dojo.byId("nomicIpMessageBox").style.display = "none";   
                 dojo.forEach(data[0].miscellaneousOfferZoneCosts, function(miscData) {
                     summaryDataList.newItem({
                         id:miscData.id, 
                         zone: miscData.zone.aliasName, 
                         pod: translator.common.allPod, 
                         cluster: translator.common.allCluster, 
                         cost: localeCurrency.format(miscData.cost) 
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
         
         dojo.query("#ipZoneLoader").removeClass("hide_text", true);
         dojo.query("#micIpCostInfo").toggleClass("hide_text", true);
         dojo.query("#ipPriceLabelContainer").toggleClass("hide_text", true);    
         dojo.query("#ipUpdateActionButton").toggleClass("hide_text", true);             
         
         this._zoneRestStore.query().then(function(data) {    
            dojo.forEach(data, function (el, index) {
                if(dijit.byId("snap_ip_widget_"+el.name+"_"+index)) {
                    dijit.byId("snap_ip_widget_"+el.name+"_"+index).destroyRecursive();
                }
                var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                    id:"snap_ip_widget_"+el.name+"_"+index,
                    zoneName: el.name,
                    zoneIdNode: el.id,
                    invalidMsg : translator.common.invalidCost
                });                
                currentSnapZonePrice.hideHrCost();
                currentSnapZonePrice.placeAt("micIpCostInfo");                  
                dojo.query("#ipZoneLoader").toggleClass("hide_text", true);
                dojo.query("#micIpCostInfo").removeClass("hide_text", true);
                dojo.query("#ipPriceLabelContainer").removeClass("hide_text", true);
                dojo.query("#ipUpdateActionButton").removeClass("hide_text", true);    
         
            });
        });  
       
    },
    updateShow : function() {          
        var stat = true;
        var miscelZoneCostList = dojo.byId("micIpCostInfo");
            var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
               var currentState = el.validateField();
               if(currentState === false) {
                   stat = false;
               }
            }); 
        if(stat === true) {
            dijit.byId("ipEditConformationDialog").show();
        }    
         
    },
    closeUpdate : function() {  
         dijit.byId("ipEditConformationDialog").hide();
    },
    update: function() {               
        var zoneCosts = [];        
        var zoneSatus = true;
        var miscelZoneCostList = dojo.byId("micIpCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                zoneSatus = false;
            }
        })
        if(zoneSatus === true) {
            dijit.byId("ipEditConformationDialog").hide();                    
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneMonthCost()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micIpButton').set('style', {'display': 'none'});
            dojo.byId('micIpLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 2,
                zoneCosts: zoneCosts                
            }).then(function(result)  { 
                dijit.byId('micIpButton').set('style', {'display': 'block'});
                dojo.byId('micIpLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("ipEditConformationDialog").hide();
                    MiscIpInfo.populateValues();                       
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("ipEditConformationDialog").hide();
                }  
            });
        }        
    }     
    };    
    

var MiscVMSnapCostInfo = {
    _misclRestStore:"",    
    _zoneRestStore:"",    
    init: function() {
        this._misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
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
    
    populateValues: function() {                 
        if(dijit.byId("VMSnapshotGrid")) {
            dijit.byId("VMSnapshotGrid").destroyRecursive();
        }
        
        if(dijit.byId("miscVMSnapshotZoneCostWidget")) {
            dijit.byId("miscVMSnapshotZoneCostWidget").destroyRecursive();
        }
        dojo.byId("miscVMSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var summaryData = {
            items: []
        };
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = "";
        configRestStore.query().then(function (currencyData) {
            summaryLayout = [
                [
                    {'field': 'id',  'hidden' : 'true'},
                    {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true, 'hidden' : 'true'},
                    {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string", 'hidden' : 'true',  autoComplete: true, 'formatter' : function(data) {
                            var html = "<span class='adminBolder'>"+ data + "</span>";
                            return html;
                        }
                    },
                    {'name': translator.common.cost + " " + translator.common.gbPerHr + " ("+ currencyData[0].currency +")", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                            var html = "<span class='adminBolder'>"+ data + "</span>"
                            return html;
                        }
                    }                    
                ]
            ];  
        }); 
         this._misclRestStore.get(4).then(function(data) {
             if(data[0].miscellaneousOfferZoneCosts.length == 0 || data[0].miscellaneousOfferZoneCosts == undefined || data[0].miscellaneousOfferZoneCosts == 'undefined' || data[0].miscellaneousOfferZoneCosts == '' || data[0].miscellaneousOfferZoneCosts == " ") {
                 dojo.byId("noMiscVMSnapshotMessageBox").style.display = "block";   
                 dojo.byId("miscVMSnapshotInfo").innerHTML = "";
             } else {
                 dojo.byId("noMiscVMSnapshotMessageBox").style.display = "none"; 
                 
                 dojo.forEach(data[0].miscellaneousOfferZoneCosts, function(miscData) {
                     summaryDataList.newItem({
                         id:miscData.id, 
                         zone: miscData.zone.aliasName,
                         pod: "All Pod",
                         cluster: "All Cluster",
                         cost: localeCurrency.format(miscData.cost , {places: 5, locale: dojo.locale})
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
         
         dojo.query("#vmSnapshotZoneLoader").removeClass("hide_text", true);
         dojo.query("#miscVMSnapshotCostInfo").toggleClass("hide_text", true);
         dojo.query("#vmSnapshotPriceLabelContainer").toggleClass("hide_text", true);        
         dojo.query("#vmSnapUpdateActionButton").toggleClass("hide_text", true);        
         
         this._zoneRestStore.query().then(function(data) {    
            dojo.forEach(data, function (el, index) {
                if(dijit.byId("vmsnap_ip_widget_"+el.name+"_"+index)) {
                    dijit.byId("vmsnap_ip_widget_"+el.name+"_"+index).destroyRecursive();
                }
                var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                    id:"vmsnap_ip_widget_"+el.name+"_"+index,
                    zoneName: el.name,
                    zoneIdNode: el.id,
                    invalidMsg : translator.common.invalidCost
                });                                
                currentSnapZonePrice.placeAt("miscVMSnapshotCostInfo");                  
                dojo.query("#vmSnapshotZoneLoader").toggleClass("hide_text", true);
                dojo.query("#miscVMSnapshotCostInfo").removeClass("hide_text", true);
                dojo.query("#vmSnapshotPriceLabelContainer").removeClass("hide_text", true);
                dojo.query("#vmSnapUpdateActionButton").removeClass("hide_text", true);    
            });
        });           
    },
    updateShow : function() {  
        var stat = true;
        var miscelZoneCostList = dojo.byId("miscVMSnapshotCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                stat = false;
            }
        }); 
        if(stat === true) {
            dijit.byId("vmSnapshotEditConformationDialog").show();
        }        
    },
    closeUpdate : function() {  
         dijit.byId("vmSnapshotEditConformationDialog").hide();
    },
    update: function() {               
        var zoneCosts = [];   
        var zoneSatus = true;
        var miscelZoneCostList = dojo.byId("miscVMSnapshotCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                zoneSatus = false;
            }
        }); 
        
        
        if(zoneSatus === true) {
            dijit.byId("vmSnapshotEditConformationDialog").hide();                    
            dojo.forEach(miscelZoneCostWidgets, function(el) {          
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneHrCost()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            } 
            dijit.byId('miscVMSnapButton').set('style', {'display': 'none'});
            dojo.byId('miscVMSnapLoader').style.display = "block";
            this._misclRestStore.put({
                id: 4,
                zoneCosts: zoneCosts                
            }).then(function(result)  {        
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("vmSnapshotEditConformationDialog").hide();    
                    MiscVMSnapCostInfo.populateValues();                     
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


var MiscLoadBalancerInfo = {
    _misclRestStore:"",    
    _zoneRestStore:"",    
    init: function() {
        this._misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
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
    
    populateValues: function() {                   
        if(dijit.byId("loadBalancerGrid")) {
            dijit.byId("loadBalancerGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        dojo.byId("micLoadBalancerInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        
        var summaryLayout = "";
        configRestStore.query().then(function (currencyData) {            
            summaryLayout = [
                [
                    {'field': 'id',  'hidden' : 'true'},
                    {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true , 'hidden' : 'true'},
                    {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string",  'hidden' : 'true', autoComplete: true, 'formatter' : function(data) {
                            var html = "<span class='adminBolder'>"+ data + "</span>"
                            return html;
                        }
                    },
                    {'name': translator.common.CostPerPolicyPerMonth + " ("+ currencyData[0].currency +")", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                            var html = "<span class='adminBolder'>"+ data + "</span>"
                            return html;  
                        }
                    }                    
                ]
            ];              
        });        
              
         this._misclRestStore.get(5).then(function(data) {  
             if(data[0].miscellaneousOfferZoneCosts.length == 0 || data[0].miscellaneousOfferZoneCosts == undefined || data[0].miscellaneousOfferZoneCosts == 'undefined' || data[0].miscellaneousOfferZoneCosts == '' || data[0].miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("noMicLoadBalancerMessageBox").style.display = "block";   
                dojo.byId("micLoadBalancerInfo").innerHTML = "";
             } else {
                 dojo.byId("noMicLoadBalancerMessageBox").style.display = "none";   
                 dojo.forEach(data[0].miscellaneousOfferZoneCosts, function(miscData) {
                     if(miscData.zone.networkType === "Advanced") {
                         
                         summaryDataList.newItem({
                             id:miscData.id, 
                             zone: miscData.zone.aliasName, 
                             pod: translator.common.allPod, 
                             cluster: translator.common.allCluster, 
                             cost: localeCurrency.format(miscData.cost)  
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
         
         dojo.query("#lbZoneLoader").removeClass("hide_text", true);
         dojo.query("#micLoadbancerCostInfo").toggleClass("hide_text", true);
         dojo.query("#lbPriceLabelContainer").toggleClass("hide_text", true);  
         dojo.query("#lbUpdateActionButton").toggleClass("hide_text", true);  
         
         
         this._zoneRestStore.query().then(function(data) {    
            dojo.forEach(data, function (el, index) {                
                if(el.networkType === "Advanced") {
                    if(dijit.byId("lb_zone_cost_widget_"+el.name+"_"+index)) {
                        dijit.byId("lb_zone_cost_widget_"+el.name+"_"+index).destroyRecursive();
                    }                    
                    var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                        id:"lb_zone_cost_widget_"+el.name+"_"+index,
                        zoneName: el.name,
                        zoneIdNode: el.id,
                        invalidMsg : translator.common.invalidCost
                    });  
                    currentSnapZonePrice.hideHrCost();
                    currentSnapZonePrice.placeAt("micLoadbancerCostInfo");
                }                                                                                  
            });            
            dojo.query("#lbZoneLoader").toggleClass("hide_text", true);
            dojo.query("#micLoadbancerCostInfo").removeClass("hide_text", true);
            dojo.query("#lbPriceLabelContainer").removeClass("hide_text", true);
            dojo.query("#lbUpdateActionButton").removeClass("hide_text", true);  
         
        });               
    },
    updateShow : function() {  
        
        var stat = true;
        var miscelZoneCostList = dojo.byId("micLoadbancerCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                stat = false;
            }
        }); 
        if(stat === true) {
            dijit.byId("loadBalancerEditConformationDialog").show();
        }          
    },
    closeUpdate : function() {  
         dijit.byId("loadBalancerEditConformationDialog").hide();
    },
    update: function() {               
        var zoneCosts = [];        
        var zoneSatus = true;
        
        var miscelZoneCostList = dojo.byId("micLoadbancerCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                zoneSatus = false;
            }
        });         
        
        if(zoneSatus === true) {
            dijit.byId("loadBalancerEditConformationDialog").hide();                  
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneMonthCost()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micLoadBalancerButton').set('style', {'display': 'none'});
            dojo.byId('micLoadBalancerLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 5,
                zoneCosts: zoneCosts                             
            }).then(function(result)  { 
                dijit.byId('micLoadBalancerButton').set('style', {'display': 'block'});
                dojo.byId('micLoadBalancerLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("loadBalancerEditConformationDialog").hide();
                    MiscLoadBalancerInfo.populateValues();                       
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("loadBalancerEditConformationDialog").hide();
                }  
            });
        }        
    }     
    };

var MiscPortForwardingInfo = {
    _misclRestStore:"",    
    _zoneRestStore:"",   
    init: function() {
        this._misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
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
    populateValues: function() {           
        if(dijit.byId("portForwardingGrid")) {
            dijit.byId("portForwardingGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        
        dojo.byId("micPortForwardingInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = "";
        configRestStore.query().then(function (currencyData) {
            summaryLayout = [
                [
                    {'field': 'id',  'hidden' : 'true'},
                    {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true , 'hidden' : 'true'},
                    {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string",  'hidden' : 'true', autoComplete: true, 'formatter' : function(data) {
                           var html = "<span class='adminBolder'>"+ data + "</span>"
                           return html;
                    }},
                    {'name': translator.common.CostPerRulePerMonth + " ("+ currencyData[0].currency +")", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                          var html = "<span class='adminBolder'>"+ data + "</span>"
                          return html;  
                    }}                
            ]
        ]; 
    });
         
              
         this._misclRestStore.get(6).then(function(data) {  
             if(data[0].miscellaneousOfferZoneCosts.length == 0 || data[0].miscellaneousOfferZoneCosts == undefined || data[0].miscellaneousOfferZoneCosts == 'undefined' || data[0].miscellaneousOfferZoneCosts == '' || data[0].miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("noMicPortForwardingMessageBox").style.display = "block";   
                dojo.byId("micPortForwardingInfo").innerHTML = "";
             } else {
                 dojo.byId("noMicPortForwardingMessageBox").style.display = "none";   
                 dojo.forEach(data[0].miscellaneousOfferZoneCosts, function(miscData) {
                     if(miscData.zone.networkType === "Advanced") {
                         summaryDataList.newItem({
                             id:miscData.id, 
                             zone: miscData.zone.aliasName, 
                             pod: translator.common.allPod, 
                             cluster: translator.common.allCluster, 
                             cost: localeCurrency.format(miscData.cost) 
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
         
         dojo.query("#pfZoneLoader").removeClass("hide_text", true);
         dojo.query("#micpfCostInfo").toggleClass("hide_text", true);
         dojo.query("#pfPriceLabelContainer").toggleClass("hide_text", true);   
         dojo.query("#PFUpdateActionButton").toggleClass("hide_text", true);   
         
         this._zoneRestStore.query().then(function(data) {    
            dojo.forEach(data, function (el, index) {                
                if(el.networkType === "Advanced") {
                    if(dijit.byId("pf_zone_cost_widget_"+el.name+"_"+index)) {
                        dijit.byId("pf_zone_cost_widget_"+el.name+"_"+index).destroyRecursive();
                    }                    
                    var pfZonePrice  = new FogPanel.MiscZonePrice({
                        id:"pf_zone_cost_widget_"+el.name+"_"+index,
                        zoneName: el.name,
                        zoneIdNode: el.id,
                        invalidMsg : translator.common.invalidCost
                    });  
                    pfZonePrice.hideHrCost();
                    pfZonePrice.placeAt("micpfCostInfo");
                }                                                                                  
            });            
            dojo.query("#pfZoneLoader").toggleClass("hide_text", true);
            dojo.query("#micpfCostInfo").removeClass("hide_text", true);
            dojo.query("#pfPriceLabelContainer").removeClass("hide_text", true);
            dojo.query("#PFUpdateActionButton").removeClass("hide_text", true);   
        });   
    },
     updateShow : function() {  
        var stat = true;
        var miscelZoneCostList = dojo.byId("micpfCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                stat = false;
            }
        }); 
        if(stat === true) {
            dijit.byId("portForwardingEditConformationDialog").show();
        }            
    },
    closeUpdate : function() {  
         dijit.byId("portForwardingEditConformationDialog").hide();
    },
    update: function() {               
        var zoneCosts = [];        
        var zoneSatus = true;
        var miscelZoneCostList = dojo.byId("micpfCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                zoneSatus = false;
            }
        }); 
        if(zoneSatus === true) {
            dijit.byId("portForwardingEditConformationDialog").hide();                  
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneMonthCost()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micPortForwardingButton').set('style', {'display': 'none'});
            dojo.byId('micPortForwardingLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 6,
                zoneCosts: zoneCosts                
            }).then(function(result)  { 
                dijit.byId('micPortForwardingButton').set('style', {'display': 'block'});
                dojo.byId('micPortForwardingLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("portForwardingEditConformationDialog").hide();
                    MiscPortForwardingInfo.populateValues();                        
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("portForwardingEditConformationDialog").hide();
                }  
            });
        }        
    }     
};    


    
    var MiscVpnInfo = {
    _misclRestStore:"",    
    _zoneRestStore:"",    
    init: function() {
        this._misclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
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
    populateValues: function() {                   
        if(dijit.byId("vpnGrid")) {
            dijit.byId("vpnGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        
        dojo.byId("micVpnInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = "";
        configRestStore.query().then(function (currencyData) {
            summaryLayout = [
                [
                    {'field': 'id',  'hidden' : 'true'},
                    {'name': translator.common.zone, 'field': 'zone', 'width' : '450px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.pod, 'field': 'pod', 'width' : '250px', datatype:"string",  autoComplete: true , 'hidden' : 'true'},
                    {'name': translator.common.cluster, 'field': 'cluster', 'width' : '250px', datatype:"string",  'hidden' : 'true', autoComplete: true, 'formatter' : function(data) {
                           var html = "<span class='adminBolder'>"+ data + "</span>"
                           return html;
                    }},
                    {'name': translator.common.CostPerConnectionPerMonth + " ("+ currencyData[0].currency +")", 'field': 'cost', 'width' : '100%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                          var html = "<span class='adminBolder'>"+ data + "</span>"
                          return html;  
                    }}                    
            ]
        ];
        });
         
              
         this._misclRestStore.get(7).then(function(data) {  
             if(data[0].miscellaneousOfferZoneCosts.length == 0 || data[0].miscellaneousOfferZoneCosts == undefined || data[0].miscellaneousOfferZoneCosts == 'undefined' || data[0].miscellaneousOfferZoneCosts == '' || data[0].miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("noMicVpnMessageBox").style.display = "block";   
                dojo.byId("micVpnInfo").innerHTML = "";
             } else {
                 dojo.byId("noMicVpnMessageBox").style.display = "none";   
                 dojo.forEach(data[0].miscellaneousOfferZoneCosts, function(miscData) {
                     if(miscData.zone.networkType === "Advanced") {
                         summaryDataList.newItem({
                             id:miscData.id, 
                             zone: miscData.zone.aliasName, 
                             pod: translator.common.allPod, 
                             cluster: translator.common.allCluster, 
                             cost: localeCurrency.format(miscData.cost) 
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
         
         
         dojo.query("#vpnZoneLoader").removeClass("hide_text", true);
         dojo.query("#micVpnCostInfo").toggleClass("hide_text", true);
         dojo.query("#vpnPriceLabelContainer").toggleClass("hide_text", true);        
         dojo.query("#vpnUpdateActionButton").toggleClass("hide_text", true);        
         
         this._zoneRestStore.query().then(function(data) {    
            dojo.forEach(data, function (el, index) {                
                if(el.networkType === "Advanced") {
                    if(dijit.byId("vpn_zone_cost_widget_"+el.name+"_"+index)) {
                        dijit.byId("vpn_zone_cost_widget_"+el.name+"_"+index).destroyRecursive();
                    }                    
                    var currentSnapZonePrice  = new FogPanel.MiscZonePrice({
                        id:"vpn_zone_cost_widget_"+el.name+"_"+index,
                        zoneName: el.name,
                        zoneIdNode: el.id,
                        invalidMsg : translator.common.invalidCost
                    });  
                    currentSnapZonePrice.hideHrCost();
                    currentSnapZonePrice.placeAt("micVpnCostInfo");
                }                                                                                  
            });            
            dojo.query("#vpnZoneLoader").toggleClass("hide_text", true);
            dojo.query("#micVpnCostInfo").removeClass("hide_text", true);
            dojo.query("#vpnPriceLabelContainer").removeClass("hide_text", true);
            dojo.query("#vpnUpdateActionButton").removeClass("hide_text", true);      
         }) 
    },
    updateShow : function() {  
        var stat = true;
        var miscelZoneCostList = dojo.byId("micVpnCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                stat = false;
            }
        }); 
        if(stat === true) {
            dijit.byId("vpnEditConformationDialog").show();
        }      
         
    },
    closeUpdate : function() {  
         dijit.byId("vpnEditConformationDialog").hide();
    },
    update: function() {               
        var zoneCosts = [];
        var stat = true;
        var miscelZoneCostList = dojo.byId("micVpnCostInfo");
        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
        dojo.forEach(miscelZoneCostWidgets, function(el) {                      
            var currentState = el.validateField();
            if(currentState === false) {
                stat = false;
            }
        }); 
          
        if(stat == true) {
            dijit.byId("vpnEditConformationDialog").hide();                   
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneMonthCost()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micVpnButton').set('style', {'display': 'none'});
            dojo.byId('micVpnLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 7,
                zoneCosts: zoneCosts                
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
