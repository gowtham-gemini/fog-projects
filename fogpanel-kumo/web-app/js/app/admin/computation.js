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
        window.computationCount = 0;
        
        controller ({
            name: "computation",
            module: "admin",
            filePath: "/js/app/admin/computation.js",
            layout: {
                name: "computation",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            
            "index": action(function() {
//               core.ui.loadTemplate("adminComputingOfferList", core.ui.getContentId()); 
//                ComputaionListInfo.init();
//                ComputaionListInfo.populateValues();
            }),
            "services" : action(function() {
                core.ui.loadTemplate("services", core.ui.getContentId()); 
            }),
            "list": action(function() {  
                var form = dijit.byId("computListForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("deleteComputingOfferDialog").destroyRecursive();
                }  
                
                if(dijit.byId("pullPlanConform")) {
                    dijit.byId("pullPlanConform").destroyRecursive();
                }  
                if(dijit.byId("pullPlanLoader")) {
                    dijit.byId("pullPlanLoader").destroyRecursive();
                }  
                core.ui.loadTemplate("adminComputingOfferList", core.ui.getContentId()); 
                ComputaionListInfo.init();
                ComputaionListInfo.populateValues();                
            }),
            "addComputation" : action(function() {   
                var currentComputingOfferItems = dijit.byId("adminComputingOfferZoneForm");
                if(currentComputingOfferItems) {
                    currentComputingOfferItems.destroyRecursive();
                    dijit.byId("adminComputingOfferContentForm").destroyRecursive();
                    dijit.byId("computeOfferEditConformationDialog").destroyRecursive();
                    
                }
                core.ui.loadTemplate("computingOffer", core.ui.getContentId()); 
                AddComputingOfferInfo.init();
                AddComputingOfferInfo.populateValues();
            }),
            "viewComputation" : action(function(id) {                
                var currentComputingOfferItems = dijit.byId("adminComputingOfferZoneForm");
                if(currentComputingOfferItems) {
                    currentComputingOfferItems.destroyRecursive();
                    dijit.byId("adminComputingOfferContentForm").destroyRecursive();
                    dijit.byId("computeOfferEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("computingOffer", core.ui.getContentId()); 
                ViewComputingOfferInfo.init();
                ViewComputingOfferInfo.populateValues(id);
            }),
            "deleteComputation" : action(function(id) {
                var form = dijit.byId("computListForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("deleteComputingOfferDialog").destroyRecursive();
                }
                if(dijit.byId("pullPlanConform")) {
                    dijit.byId("pullPlanConform").destroyRecursive();
                } 
                if(dijit.byId("pullPlanLoader")) {
                    dijit.byId("pullPlanLoader").destroyRecursive();
                }  
                core.ui.loadTemplate("adminComputingOfferList", core.ui.getContentId());
                ComputaionListInfo.init();
                ComputaionListInfo.populateValues();  
                
                DeleteComputingOffer.init();
                DeleteComputingOffer.populateValues(id);   
            }),
            "disableComputation" : action(function(id) {   
                var form = dijit.byId("computListForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("deleteComputingOfferDialog").destroyRecursive();
                }
                if(dijit.byId("pullPlanLoader")) {
                    dijit.byId("pullPlanLoader").destroyRecursive();
                }  
                if(dijit.byId("pullPlanConform")) {
                    dijit.byId("pullPlanConform").destroyRecursive();
                } 
                core.ui.loadTemplate("adminComputingOfferList", core.ui.getContentId()); 
                ComputaionListInfo.init();
                ComputaionListInfo.populateValues();                 
                
                ActivateStatus.init();
                ActivateStatus.populateValues(id, "disable");
            }),
            "enableComputation" : action(function(id) {
                var form = dijit.byId("computListForm");
                if(form) {
                    form.destroyRecursive();
                    dijit.byId("deleteComputingOfferDialog").destroyRecursive();
                }
                if(dijit.byId("pullPlanLoader")) {
                    dijit.byId("pullPlanLoader").destroyRecursive();
                }  
                if(dijit.byId("pullPlanConform")) {
                    dijit.byId("pullPlanConform").destroyRecursive();
                } 
                core.ui.loadTemplate("adminComputingOfferList", core.ui.getContentId()); 
                ComputaionListInfo.init();
                ComputaionListInfo.populateValues();                 
                
                ActivateStatus.init();
                ActivateStatus.populateValues(id, "enable");    
            }) 
        });
    });
    
var ActivateStatus = {
     _computingOfferDisableRestStore:"",
     _currentId:"",
     init: function() {
          this._computingOfferDisableRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/action"
        });
     },
     populateValues: function(currentId, status) {
         var currentStatus = "";
         if(status == "enable") {
             currentStatus = translator.common.enabled;
         } else if(status == "disable") {
             currentStatus = translator.common.disabled;
         } else {
             currentStatus = "";
         }
         this._currentId = currentId;
         this._computingOfferDisableRestStore.add({
             computingOfferId: currentId,
             status: status   
         }).then(function(result) {
             if(result == "OK") {
                 registry.byId("appToaster").setContent(currentStatus +"   " + translator.common.message.successfully, "message");
                 registry.byId("appToaster").show();
                 ComputaionListInfo.init();
                 ComputaionListInfo.populateValues();
//                 core.router.go("#/admin/computation/list");
             } else {
                 registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                 registry.byId("appToaster").show();
                 ComputaionListInfo.init();
                 ComputaionListInfo.populateValues(); 
             }
         });         
     }   
     
 };
 
var ComputaionListInfo = {
    _coumputingOfferRestStore:"",
    _coumputingOfferCountRestStore:"",
    
    init: function() {
        this._coumputingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/"
        }); 
        this._coumputingOfferCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/count"
        }); 
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("computCurrencyValue").innerHTML= cur.currency;
               });
        }); 
    },
    conformPull: function () {
        dijit.byId("pullPlanConform").show();
    },
    cancelPullPlan: function () {
        dijit.byId("pullPlanConform").hide();
    },
    pullPlan : function() {
        
        dijit.byId("pullPlanLoader").show();
        dijit.byId("pullPlanConform").hide();
        
        var pullPlanRestStore = new JsonRest({
           target: core.getContextPath()+"/api/computingOffer/pullFromCloudStack"
        });
        
        pullPlanRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    ComputaionListInfo.populateValues();
                    registry.byId("appToaster").setContent(translator.common.message.pullPlanSuccess, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("pullPlanLoader").hide();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("pullPlanLoader").hide();
                }
            });
        });
        
    },
    disablePlan :function (currentId) {
      ActivateStatus.init();
      ActivateStatus.populateValues(currentId, "disable");    
    },
    enablePlan :function (currentId) {
      ActivateStatus.init();
      ActivateStatus.populateValues(currentId, "enable");    
    },
    
    populateValues : function() {
        dojo.byId("adminComputingOfferList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        
//        if(dijit.byId("computDataGrid")) {                                    
//                dijit.byId("computDataGrid").destroyRecursive();                    
//        }
        
        dojo.style(dijit.byId("deleteComputingOfferDialog").closeButtonNode,"display","none");
        var gridData = {
            items: []
        }; 
        
        dojo.byId("adminTotalComputations").innerHTML = 0;
        dojo.byId("adminEnabledComputations").innerHTML = 0;
        dojo.byId("adminDisabledComputations").innerHTML = 0;
        var dataList = new ItemFileWriteStore({data: gridData}); 
        var dataLayout = [[
                 {'field': 'id', 'hidden': 'true'},
                 {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                 {'name': translator.common.baseOSName, 'field': 'baseOS', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                 
                 {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
                 {'name': translator.common.hypervisor, 'field': 'hypervisor', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                 {'name': translator.common.pod, 'field': 'pod', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true'},
                 {'name': translator.common.cluster, 'field': 'cluster', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true', 'formatter' : function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;  
                    }
                },
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.cost, 'field': 'cost', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
                         var html = "<span class='fog_cost'>"+ data + "</span>"
                        return html;
                 }},
             
             
                 
                 {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) { 
                         var currentData = data.split(",");
                         var html;
                         if(currentData[1] == true || currentData[1] == "true") {
                             html = "<a href='#/admin/computation/viewComputation/"+currentData[0]+"' title='"+translator.common.view+"'><i class='icon-edit'></i></a>" +                                      
                                    "<a href='#/admin/computation/list/' class='offset1' title='"+translator.common.disable+"' onclick='ComputaionListInfo.disablePlan("+currentData[0]+")'><i class='icon-eye-close'></i></a></li>"+
                                    "<a href='#/admin/computation/deleteComputation/"+currentData[0]+"' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
                         } else if(currentData[1] == false || currentData[1] == "false") {
                             html = "<a href='#/admin/computation/viewComputation/"+currentData[0]+"' title='"+translator.common.view+"'><i class='icon-edit'></i></a>" +                                       
                                    "<a href='#/admin/computation/list/' class='offset1' title='"+translator.common.enable+"' onclick='ComputaionListInfo.enablePlan("+currentData[0]+")'><i class='icon-eye-open'></i></a></li>" + 
                                    "<a href='#/admin/computation/deleteComputation/"+currentData[0]+"' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
                            }
                            return html;                                   
                        },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                ]
            ];         
            this._coumputingOfferRestStore.query().then(function(data) {
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    dojo.byId("adminComputingOfferList").innerHTML = "";
                    dojo.byId("noOfferMessageBox").style.display = "block";

                    dojo.byId("adminTotalComputations").innerHTML = 0;
                    dojo.byId("adminEnabledComputations").innerHTML = 0;
                    dojo.byId("adminDisabledComputations").innerHTML = 0;
                } else {
                    dojo.byId("noOfferMessageBox").style.display = "none";
                    dojo.forEach(data, function(resultData) {                 
                        dataList.newItem({
                            id:resultData.id,
                            name:resultData.name,
                            zone: resultData.zone.aliasName, 
                            pod: resultData.pod.name,
                            cluster: resultData.cluster.name,
                            type: resultData.storageType,
                            hypervisor:resultData.hypervisor,
                            baseOS: resultData.baseOs,
                            cost:  (resultData.computingOfferZoneCosts[0].cost * 720).toFixed(2),
                            action : resultData.id + "," +resultData.available 
                        });
                    });                                    
                    dojo.byId("adminComputingOfferList").innerHTML = "";
                    var dataGrid = new EnhancedGrid({                
//                        id: 'computDataGrid',
                        "class" : "span12",
                        store: dataList,
                        structure: dataLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    dataGrid.placeAt("adminComputingOfferList");
                    dataGrid.startup();  
                    dataGrid.update();
                    var coumputingOfferCountRestStore = new JsonRest({                        
                        target: core.getContextPath()+"/api/computingOffer/count"
                    }); 
                    coumputingOfferCountRestStore.query().then(function(data) {
                        dojo.forEach(data, function(resultData) {
                            dojo.byId("adminTotalComputations").innerHTML = resultData.total;
                            dojo.byId("adminEnabledComputations").innerHTML = resultData.enabled;
                            dojo.byId("adminDisabledComputations").innerHTML = resultData.disabled;                                            
                        });
                    });
                }             
            }); 
        }
    };
 
var AddComputingOfferInfo = {
    _computingOfferRestStore: "",
    _zoneRestStore:"",
    _podRestStore:"",
    _clusterRestStore: "",
    _storageTagRestStore:"",
    _hostTagRestStore:"",
    _zoneWidget : "",
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
        
//        this._storageTagRestStore = new JsonRest({
//            target: core.getContextPath()+"/api/storagePool/"
//        });
//        
//        this._hostTagRestStore = new JsonRest({
//            target: core.getContextPath()+"/api/host/"
//        });
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("currencyValue").innerHTML= cur.currency;
               });
        }); 
        
        
        var hyperviserItems = {
            identifier: "id",
            label: "name",
            items: []
        };
        var hyperviserList = new ItemFileWriteStore({data: hyperviserItems});
        var hyperviserRestStore = new JsonRest({
            target: core.getContextPath() + "/api/hypervisor/"
        });
       
        var hypervisorWidget = new Select({
            id: "computeHypervisorList",
            store: hyperviserList,
            maxHeight: -1
        }, "computeHypervisorListDiv");
        
        
     },
     populateValues : function() {
          if(dijit.byId("computingOfferZoneListItem")) {
            dijit.byId("computingOfferZoneListItem").destroyRecursive();
          }
          var clusterRestStore = this._clusterRestStore;
          var hostTagRestStore = this._hostTagRestStore;
          
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

         var storageTagOptions = {
             identifier: 'id',
             label: 'name',
             items: [{id: "option", name: translator.common.message.selectTag}]
         };
                    
        var hostTagOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.hostTag}]
        };
                                              
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
        var hostTagList = new ItemFileWriteStore({data: hostTagOptions});
        dojo.query("#serviceUpdateButtonDiv").removeClass("span3", true); 
        if(dijit.byId("computingOfferStorageTag") && dijit.byId("computingOfferHostTag") && dijit.byId("computingOfferClusterList") && dijit.byId("computingOfferPodList"))  {
            dijit.byId("computingOfferStorageTag").destroyRecursive();
            dijit.byId("computingOfferHostTag").destroyRecursive();
            dijit.byId("computingOfferClusterList").destroyRecursive();
            dijit.byId("computingOfferPodList").destroyRecursive();
        }
      
      this._clusterWidget = new FilteringSelect({
          id:"computingOfferClusterList",
          store: clusterList,
          value: "option",
          onChange: function() {
          }
      }).placeAt("computingOfferCluster");  
      this._podWidget = new FilteringSelect({
        id:"computingOfferPodList",
        store: podList,
        value: "option",
        onChange: function() {            
            var clusterWidget = dijit.byId("computingOfferClusterList");
            var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            };
            
            var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
             if(this.value == "option") {
                     currentClusterList.newItem({id: "option", name: translator.common.message.selectCluster});
                     clusterWidget.set("store",currentClusterList);
                     clusterWidget.set("value","option");
                     
            }
            clusterRestStore.get(this.value).then(function(clusterListItems) {
                var clusterValue = ""
                dojo.forEach(clusterListItems,function(currentcluster, index) {
                    
                    currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                    if(index == 0) {
                        clusterValue = currentcluster.clusterReferenceId;
                    }
                });
                clusterWidget.set("store",currentClusterList);
                clusterWidget.set("value",clusterValue);
                
            });
        }
    }).placeAt("computingOfferPod"); 
    
    var zoneOptions = { 
        identifier: 'id',
        label: 'name',
        items: [{id: "option", name: translator.common.message.selectZone}]
    };
    
    var zoneList = new ItemFileWriteStore({data: zoneOptions});

    this._zoneRestStore.query().then(function(data) {
        dojo.forEach(data, function(el) {
            zoneList.newItem({id: el.referenceZoneId, referenceId: el.referenceZoneId, name: el.aliasName})
        });
    });                        
    this._zoneWidget = new FilteringSelect({
        store: zoneList,
        id:"computingOfferZoneListItem",
        value: "option",
        onChange: function() {
            AddComputingOfferInfo.viewSelectedZone(this);   
        }
    }).placeAt("computingOfferZoneList"); 
    this._zoneWidget.startup();      
     },
     viewSelectedZone : function(currentZone) {             
        var podWidget = dijit.byId("computingOfferPodList");
        var currentZoneCost;
       
        if(dijit.byId("computingOfferCurrentZone")) {
            dijit.byId("computingOfferCurrentZone").destroyRecursive();
        }
        
        this._zoneRestStore.get(currentZone.value).then(function(selectedZoneInfo) {
            dojo.query("#billingInfo").toggleClass("show_lable", true);
             currentZoneCost  = new Zone.ZoneCost({
                    id:"computingOfferCurrentZone",
                    zoneName: selectedZoneInfo.aliasName,
                    zoneIdNode: selectedZoneInfo.id,
                    vmRunningCostLabel : translator.common.vmRunningCostPerMonth,
                    stopageCostLabel : translator.common.vmStopageCostPerMonth,
                    setupCostLabel : translator.common.setupCost,
                    runningCostPerHrLabel : translator.common.runningCostPerHr,
                    stopageCostPerHrLabel : translator.common.stopageCostPerHr,
                    invalidMsg : translator.common.zoneCostInvalidMsg
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
            currentPodList.newItem({id: "option", name: translator.common.message.selectPode});
            podWidget.set("store",currentPodList);
            podWidget.set("value","option");
            
            dijit.byId("serviceAddButton").set("disabled", true);     
//            dijit.byId("serviceCancelButton").set("disabled", true);     
            dojo.query("#billingInfo").removeClass("show_lable", true);
            
        } else {
            dijit.byId("serviceAddButton").set("disabled", false);
        }
        
        this._podRestStore.get(currentZone.value).then(function(podListItems) {
            var indexId = "";
            dojo.forEach(podListItems,function(currentPod, index) {                             
               currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
               if(index == 0) {
                   indexId = currentPod.podReferenceId;
               }
             });
            podWidget.set("store",currentPodList);
            podWidget.set("value",indexId);
            
        });
        
        if(currentZone.value != "option") {
            var hyperviserItems = {
                identifier: "id",
                label: "name",
                items: []
            };

            var hyperviserList = new ItemFileWriteStore({data: hyperviserItems});

            var hyperviserRestStore = new JsonRest({
                target: core.getContextPath() + "/api/hypervisor/"
            });
            hyperviserRestStore.get(currentZone.value).then(function(data) {
                dojo.forEach(data, function(el) {
                    hyperviserList.newItem({id: el.id, name: el.name});
                    //                            hypervisorWidget.set("value", el.name);
                });
                dijit.byId("computeHypervisorList").setStore(hyperviserList);
            });
        } else {
            var hyperviserItems = {
                identifier: "id",
                label: "name",
                items: []
            };

            var hyperviserList = new ItemFileWriteStore({data: hyperviserItems});
            dijit.byId("computeHypervisorList").setStore(hyperviserList);
        }                     
    },
    hideOfferHA : function(currentValue) {                       
        if(currentValue.value == "Local"){
            dojo.byId("computingOfferHA").style.display = "none";
        } else {
            dojo.byId("computingOfferHA").style.display = "block";
        }        
    },
    authetication : function() {
        var status = true;
        var pageNode = dojo.byId("adminComputingOfferPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
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
        var status = AddComputingOfferInfo.authetication();         
        if(status == true) {
            dojo.query("#computingOfferLoader").removeClass("hide_text", true);
            dojo.query("#serviceAddButtonDiv").toggleClass("hide_text", true);
            dojo.query("#serviceCancelButtonDiv").toggleClass("hide_text", true);
            
            dijit.byId("serviceAddButton").set("disabled", true);            
            var computingZoneCosts =[];
            var podWidget = dijit.byId("computingOfferPodList");
            var clusterWidget = dijit.byId("computingOfferClusterList");

            var diskReadRateBPS = dijit.byId("diskReadRateBPS");
            var diskWriteRateBPS = dijit.byId("diskWriteRateBPS");
            var diskReadRateIOPS = dijit.byId("diskReadRateIOPS");
            var diskWriteRateIOPS = dijit.byId("diskWriteRateIOPS");

            var hypervisor = dijit.byId("computeHypervisorList").value;
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
            var storageValue = dijit.byId("computingOfferStorageTag").value;  
            var hostValue = dijit.byId ("computingOfferHostTag").value;
            
            var diskIORate = dijit.byId ("diskIO").value;
            
            hostTag = hostValue;
            storageTag = storageValue;

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
                hypervisor:hypervisor,
                bandWidth: bandWidth,
                offerHa: offerHa.checked, 
                storageType: storageType.value,
                storageTag: storageTag,
                hostTag: hostTag,
                baseOs: baseOs,
                diskReadRateBPS:diskReadRateBPS.value,	
                diskWriteRateBPS:diskWriteRateBPS.value,	
                diskReadRateIOPS:diskReadRateIOPS.value,	
                diskWriteRateIOPS:diskWriteRateIOPS.value,	
                zoneCosts: computingZoneCosts,
                diskIO:diskIORate
            }).then(function(data) {
                dijit.byId("serviceAddButton").set("disabled", false);  
                dojo.query("#computingOfferLoader").toggleClass("hide_text", true);
                dojo.query("#serviceAddButtonDiv").removeClass("hide_text", true);
                dojo.query("#serviceCancelButtonDiv").removeClass("hide_text", true);                
                if(data[0].result == "OK") {         
                    var zoneNode = dojo.byId("currentComputingZoneInfo");
                    var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                    dojo.forEach(zoneWidgets, function(el) {
                        el.clearWidgets();
                    });
                    registry.byId("appToaster").setContent(translator.common.message.addedSuccess, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("adminComputingOfferContentForm").reset();                
                    dijit.byId("serviceDescription").set("value", "");
                    core.router.go("#/admin/computation/list");
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.cannotBeAdded, "error");
                    registry.byId("appToaster").show();
                }           
            });             
        } 
    },
    cancel: function() {
        core.router.go("#/admin/computation/list");
    }
 };
 
 var ServiceConfig = {
    openTab : function() {        
        var liElement = dojo.byId("serviceMenu");       
        var varticalMenuBar = dijit.byId("verticalMenu");
        varticalMenuBar.onLiClick(liElement);
    }
};
 var ViewComputingOfferInfo = {
     _computingOfferRestStore:"",
     _currentComputId:"",
     init: function() {        
         this._computingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/"
        });
        
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("currencyValue").innerHTML= cur.currency;
               });
        }); 
        
     },
     updateShow : function() {  
         dijit.byId("computeOfferEditConformationDialog").show();
     },
     closeUpdate : function() {  
         dijit.byId("computeOfferEditConformationDialog").hide();
     },
     populateValues : function(currentId) {  
         this._currentComputId = currentId;
         dojo.byId("editComputBreadcrum").innerHTML = "View";
        var zoneNode = dojo.byId("currentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
            dojo.forEach(zoneWidgets, function(el) {
                 el.clearWidgets();
        }); 
     
        this._computingOfferRestStore.get(currentId).then(function(computingOfferData) {  
            dojo.query("#adminComputingOfferPage .updatable").toggleClass("non_updatable", true);
            dojo.query("#adminComputingOfferPage .hide_lable").toggleClass("show_lable", true);
            dojo.byId("billingInfo").style.display = "block";
            
            
           dojo.byId("computingZoneNameLabel").innerHTML = computingOfferData.zone.aliasName;
           dojo.byId("computingPodNameLabel").innerHTML = computingOfferData.pod.name;
           dojo.byId("computingClusterNameLabel").innerHTML = computingOfferData.cluster.name;                       
            
            var name = dijit.byId ("serviceName");
            var description = dijit.byId ("serviceDescription"); 
            var diskIO = dijit.byId ("diskIO"); 
        
            name.setValue(computingOfferData.name);
            diskIO.setValue(computingOfferData.diskIO);
            description.setValue(computingOfferData.description);
            
            dojo.forEach(computingOfferData.computingOfferZoneCosts, function(zoneData){                  
               var currentZoneCost  = new Zone.ZoneCost({
                    id:"computingOfferCurrentZone",
                    zoneName: "zone",
                    zoneIdNode: zoneData.zone.id,
                    zoneCost : (zoneData.cost*720).toFixed(2),
                    setupCost : zoneData.setupCost,
                    minCost : (zoneData.minimumCost*720).toFixed(2),                      
                    vmRunningCostLabel : translator.common.vmRunningCostPerMonth,
                    stopageCostLabel : translator.common.vmStopageCostPerMonth,
                    setupCostLabel : translator.common.setupCost,
                    runningCostPerHrLabel : translator.common.runningCostPerHr,
                    stopageCostPerHrLabel : translator.common.stopageCostPerHr,
                    invalidMsg : translator.common.zoneCostInvalidMsg
                });
                currentZoneCost.showStopageCost();
                currentZoneCost.setMinCost(),
                currentZoneCost.setCost(),
                currentZoneCost.setSetupCost();
                currentZoneCost.placeAt("currentZoneCost");  
            });

        if(computingOfferData.bandWidth == "" ||
            computingOfferData.bandWidth == null || computingOfferData.bandWidth == "null") {
            dojo.byId("serviceBwLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceBwLabel").innerHTML = computingOfferData.bandWidth;
        }
        
        
        if(computingOfferData.diskReadRateBPS == "" ||
            computingOfferData.diskReadRateBPS == null || computingOfferData.diskReadRateBPS == "null") {
            dojo.byId("serviceDiskReadRateBPSLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceDiskReadRateBPSLabel").innerHTML = computingOfferData.diskReadRateBPS;
        }
        
        
        dojo.byId("serviceHyperLabel").innerHTML = computingOfferData.hypervisor;
        
        
        
        if(computingOfferData.diskWriteRateBPS == "" ||
            computingOfferData.diskWriteRateBPS == null || computingOfferData.diskWriteRateBPS == "null") {
            dojo.byId("serviceWriteRateBPSLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceWriteRateBPSLabel").innerHTML = computingOfferData.diskWriteRateBPS;
        }
        
        
        if(computingOfferData.diskReadRateIOPS == "" ||
            computingOfferData.diskReadRateIOPS == null || computingOfferData.diskReadRateIOPS == "null") {
            dojo.byId("serviceDiskReadRateIOPSLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceDiskReadRateIOPSLabel").innerHTML = computingOfferData.diskReadRateIOPS;
        }
        
        if(computingOfferData.diskWriteRateIOPS == "" ||
            computingOfferData.diskWriteRateIOPS == null || computingOfferData.diskWriteRateIOPS == "null") {
            dojo.byId("serviceWriteRateIOPSLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceWriteRateIOPSLabel").innerHTML = computingOfferData.diskWriteRateIOPS;
        }
                
        if (computingOfferData.tags == null || computingOfferData.tags == "" || computingOfferData.tags == " " || computingOfferData.tags == "null") {
            dojo.byId ("serviceStorageTagLabel").innerHTML = translator.common.message.noTag;
        } else {         
            dojo.byId ("serviceStorageTagLabel").innerHTML = computingOfferData.tags;
        }
        if(computingOfferData.hostTags == null || computingOfferData.hostTags == "" || computingOfferData.hostTags == "null" || computingOfferData.hostTags == " ") {
              dojo.byId("serviceHostTagLabel").innerHTML = translator.common.message.noTag;
        } else {
             dojo.byId("serviceHostTagLabel").innerHTML = computingOfferData.hostTags;
        }      
        
        dojo.byId ("serviceCpuNumberLabel").innerHTML = computingOfferData.cpuNumber+ " " + translator.common.core;
        dojo.byId ("serviceCpuSpeedLabel").innerHTML = computingOfferData.coreCpuSpeed ;
        dojo.byId ("serviceMemoryLabel").innerHTML =  computingOfferData.memory ;
        
        if(computingOfferData.networkRate == "" ||
        computingOfferData.networkRate == null || computingOfferData.networkRate == "null") {
            dojo.byId("serviceNetworkRateLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceNetworkRateLabel").innerHTML =
               computingOfferData.networkRate + translator.common.MBs;
        }
        
        if (computingOfferData.offerHA == "true" || computingOfferData.offerHA == true) {
             dojo.byId("serviceofferHaLabel").innerHTML = translator.common.yes;
        } else {
             dojo.byId("serviceofferHaLabel").innerHTML = translator.common.no;
        }
        if(computingOfferData.limitCpuUse == true ) {
              dojo.byId("serviceCpuCapLabel").innerHTML = translator.common.yes;
        } else {
             dojo.byId("serviceCpuCapLabel").innerHTML = translator.common.no;
        }       
        
     
       if(computingOfferData.baseOs == "" || computingOfferData.baseOs== null ||computingOfferData.baseOs == "null") {
            dojo.byId("serviceBaseOsLabel").innerHTML = "......";
        } else {
            dojo.byId("serviceBaseOsLabel").innerHTML = computingOfferData.baseOs;
        }
        
        var storageValue = dijit.byId('serviceStorageType').attr('displayedValue');
       
        if (storageValue == "") {
             dojo.byId("serviceStorageTypeLabel").innerHTML = "......";
        } else {
             dojo.byId("serviceStorageTypeLabel").innerHTML = storageValue;
        }      
        
        dojo.byId("serviceStorageTypeLabel").innerHTML = computingOfferData.storageType;
        });            
        
        dijit.byId('serviceAddButton').set('style', { display: 'none' });
        dijit.byId('serviceUpdateButton').set('style', { display: 'block',  "float": "left"});
     },
     update: function() {        
        var status = AddComputingOfferInfo.authetication();
        var computingZoneCosts =[];
        
        if(status == true) {
            var currentServiceWidgetId = dojo.byId("currentComputingOfferId").value;
            var currentServiceWidget = dijit.byId(currentServiceWidgetId);
           
            dojo.query("#computingOfferLoader").removeClass("hide_text", true);
            dojo.query("#serviceUpdateButtonDiv").toggleClass("hide_text", true);
            dojo.query("#serviceCancelButtonDiv").toggleClass("hide_text", true);
            dijit.byId("computeOfferEditConformationDialog").hide();
            dijit.byId("serviceUpdateButton").set("disabled", true);
            var name = dijit.byId ("serviceName");
            var diskIO = dijit.byId ("diskIO");
            var editValue = dijit.byId("serviceDescription").value.toString();
            var description = editValue.replace("\n"," ");             

            var zoneNode = dojo.byId("currentComputingZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);

            dojo.forEach(zoneWidgets, function(el) {
                computingZoneCosts.push ({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCost(),
                    setupCost: el.getSetupCost(),
                    minimumCost: el.getMinCost()
                });
            });               
            for(var index = 0; index < computingZoneCosts.length-zoneWidgets.length; index++) {
                computingZoneCosts.splice(index, zoneWidgets.length);
            }
            this._computingOfferRestStore.put({
                id: this._currentComputId,
                name: name.value,
                diskIO: diskIO.value,
                description: description,
                zoneCosts: computingZoneCosts,
                "class":"com.assistanz.fogpanel.ComputingOffer"
            }).then(function(result) {
                dijit.byId("serviceUpdateButton").set("disabled", false);
                
                dojo.query("#computingOfferLoader").toggleClass("hide_text", true);
                dojo.query("#serviceUpdateButtonDiv").removeClass("hide_text", true);
                dojo.query("#serviceCancelButtonDiv").removeClass("hide_text", true);
                if(result == "OK") {
                    var zoneNode = dojo.byId("currentComputingZoneInfo");
                    var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                    dojo.forEach(zoneWidgets, function(el) {
                        el.clearWidgets();
                    });    
                    dijit.byId("computeOfferEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    
                    core.router.go("#/admin/computation/list");
                    dijit.byId("adminComputingOfferContentForm").reset();
                    dijit.byId("serviceDescription").set("value", "");
                } else {
                    dijit.byId("computeOfferEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });
            }    
        
    }
 };
 var DeleteComputingOffer = {
     _computingOfferRestStore:"",
     _currentId:"",
     init: function() {
          this._computingOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/computingOffer/delete"
        });
     },
     populateValues: function(currentId) {
         this._currentId = currentId;
         dijit.byId("deleteComputingOfferDialog").show();
     },
     conformDelete: function() {
         dijit.byId("deleteComputingOfferDialog").hide();
         var confirmDelete = dijit.byId("computDeleteConfirm").checked;
         this._computingOfferRestStore.add({
            computingOfferId: this._currentId,
            forced : confirmDelete.toString()
        }).then(function(result) {
            if(result == "OK") {                               
                registry.byId("appToaster").setContent(translator.common.message.deletedSuccess, "message");
                registry.byId("appToaster").show();
                core.router.go("#/admin/computation/list");
            } else {                 
                var resultData = "";                
                if(result[0].cause.localizedMessage == translator.common.message.vmExistMsg) {
                    resultData = result[0].cause.localizedMessage;
                } else {
                    resultData = translator.common.cloudStack.errorMessage
                }
                registry.byId("appToaster").setContent(resultData, "error");
                registry.byId("appToaster").show();
                core.router.go("#/admin/computation/list");
            }
        });
     },
     closeDeleteDialog : function() {
         dijit.byId("deleteComputingOfferDialog").hide();
         core.router.go("#/admin/computation/list");
     }     
 };
 
 
 window.ComputaionListInfo  = ComputaionListInfo;
 window.AddComputingOfferInfo = AddComputingOfferInfo;
 window.ViewComputingOfferInfo = ViewComputingOfferInfo;
 window.ServiceConfig = ServiceConfig;
 window.DeleteComputingOffer = DeleteComputingOffer;
 window.ActivateStatus = ActivateStatus;
