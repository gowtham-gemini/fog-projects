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
        window.localeCurrency = localeCurrency;
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
                if(dijit.byId("adminComputingOfferZoneForm")) {
                    dijit.byId("adminComputingOfferZoneForm").destroyRecursive();                                                        
                }
                if(dijit.byId("computeOfferEditConformationDialog")) {
                    dijit.byId("computeOfferEditConformationDialog").destroyRecursive();                                                        
                }
                core.ui.loadTemplate("computingOffer", core.ui.getContentId()); 
                AddComputingOfferInfo.init();
                AddComputingOfferInfo.populateValues();
            }),
            "viewComputation" : action(function(id) {                                
                if(dijit.byId("adminComputingOfferZoneForm")) {                    
                    dijit.byId("adminComputingOfferZoneForm").destroyRecursive();                    
                }                
                if(dijit.byId("computeOfferEditConformationDialog")) {                                        
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
                 {'name': translator.common.vCPUCore, 'field': 'vcpuCore', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},                 
                 {'name': translator.common.speed + "(" + translator.common.mhz + ")", 'field': 'speed', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
                 {'name': translator.common.memory + "(" + translator.common.mb + ")", 'field': 'memory', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                 
                 {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                                           
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
                            vcpuCore: resultData.cpuNumber, 
                            speed: resultData.coreCpuSpeed,
                            memory: resultData.memory,
                            type: resultData.storageType,                            
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
     },
     populateValues : function() {  
        dojo.query("#serviceUpdateButtonDiv").removeClass("span3", true);     
        
        dojo.query("#addComputOfferZoneLoader").removeClass("hide_text", true);
        dojo.query("#currentZoneCost").toggleClass("hide_text", true);
        dojo.query("#zonePriceLabelContainer").toggleClass("hide_text", true);
        
        
        this._zoneRestStore.query().then(function(data) {             
            dojo.forEach(data, function (el, index) {
                if(dijit.byId("zone_widget_"+el.name+"_"+index)) {
                    dijit.byId("zone_widget_"+el.name+"_"+index).destroyRecursive();
                }
                
                var currentZonePrice  = new FogPanel.ZonePrice({
                    id:"zone_widget_"+el.name+"_"+index,
                    zoneName: el.name,
                    zoneIdNode: el.id     
                });
                currentZonePrice.placeAt("currentZoneCost");  
                dojo.query("#addComputOfferZoneLoader").toggleClass("hide_text", true);
                dojo.query("#currentZoneCost").removeClass("hide_text", true);
                dojo.query("#zonePriceLabelContainer").removeClass("hide_text", true);
            });
        });                            
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
        return status; 
    },
    add: function() {        
        var status = AddComputingOfferInfo.authetication();    
        var zoneStat = true;  
        var zoneNode = dojo.byId("currentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
        dojo.forEach(zoneWidgets, function(el) {                       
            var stat = el.showErrorMsg();
            if(stat === false) {
                zoneStat = false;
            }
        });            
        
        if(status === true && zoneStat === true) {
            dojo.query("#computingOfferLoader").removeClass("hide_text", true);
            dojo.query("#serviceAddButtonDiv").toggleClass("hide_text", true);
            dojo.query("#serviceCancelButtonDiv").toggleClass("hide_text", true);
            
            dijit.byId("serviceAddButton").set("disabled", true);            
            var computingZoneCosts =[];                        
            var diskReadRateBPS = dijit.byId("diskReadRateBPS");
            var diskWriteRateBPS = dijit.byId("diskWriteRateBPS");
            var diskReadRateIOPS = dijit.byId("diskReadRateIOPS");
            var diskWriteRateIOPS = dijit.byId("diskWriteRateIOPS");
            
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
         var status = AddComputingOfferInfo.authetication();
         var zoneStat = true;
         var zoneNode = dojo.byId("currentComputingZoneInfo");
         var zoneWidgets = dijit.registry.findWidgets(zoneNode);
         dojo.forEach(zoneWidgets, function(el) {                       
             var stat = el.showErrorMsg();
             if(stat === false) {
                 zoneStat = false;
             }
         });
         if(status === true && zoneStat === true) {
             dijit.byId("computeOfferEditConformationDialog").show();
         }
     },
     closeUpdate : function() {  
         dijit.byId("computeOfferEditConformationDialog").hide();
     },
     populateValues : function(currentId) {  
         dojo.byId("computingOfferTitle").innerHTML = translator.common.editOfferTitle;
         this._currentComputId = currentId;
         dojo.byId("editComputBreadcrum").innerHTML = "View";
        var zoneNode = dojo.byId("currentComputingZoneInfo");
        
        
        dojo.query("#addComputOfferZoneLoader").removeClass("hide_text", true);
        dojo.query("#currentZoneCost").toggleClass("hide_text", true);
        dojo.query("#zonePriceLabelContainer").toggleClass("hide_text", true);
        
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
            dojo.forEach(zoneWidgets, function(el) {
                 el.clearWidgets();
        }); 
        var zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });
        this._computingOfferRestStore.get(currentId).then(function(computingOfferData) {  
            dojo.query("#adminComputingOfferPage .updatable").toggleClass("non_updatable", true);
            dojo.query("#adminComputingOfferPage .hide_lable").toggleClass("show_lable", true);
            dojo.byId("billingInfo").style.display = "block";
            
            var name = dijit.byId ("serviceName");
            var description = dijit.byId ("serviceDescription"); 
            var diskIO = dijit.byId ("diskIO"); 
        
            name.setValue(computingOfferData[0].name);
            diskIO.setValue(computingOfferData[0].diskIO);
            description.setValue(computingOfferData[0].description);
            zoneRestStore.query().then(function (data) {
                dojo.forEach(data, function (zoneItem, index) {
                    var zoneCost = 0.0;
                    var setupCostValue = 0.0;
                    var minCost = 0.0;
                    
                    dojo.forEach(computingOfferData[0].computingOfferZoneCosts, function(zoneData) { 
                        if(zoneItem.referenceZoneId === zoneData.zone.referenceZoneId) {                                 
                                zoneCost = (zoneData.cost*720).toFixed(2);
                                setupCostValue = zoneData.setupCost,
                                minCost = (zoneData.minimumCost*720).toFixed(2);
                            }
                    });
                    if(dijit.byId("zone_widget_"+zoneItem.aliasName+"_"+index)) {
                        dijit.byId("zone_widget_"+zoneItem.aliasName+"_"+index).destroyRecursive();
                    }
                
                    var currentZonePrice  = new FogPanel.ZonePrice({
                        id:"zone_widget_"+zoneItem.aliasName+"_"+index,
                        zoneName: zoneItem.aliasName,
                        zoneIdNode: zoneItem.id,
                        zoneCost : zoneCost,
                        setupCostValue : setupCostValue,
                        minCost : minCost
                    });
                    currentZonePrice.setMinCost(),
                    currentZonePrice.setCost(),
                    currentZonePrice.setSetupCost();
                    currentZonePrice.placeAt("currentZoneCost");                     
                });
            });
            

        if(computingOfferData[0].bandWidth == "" ||
            computingOfferData[0].bandWidth == null || computingOfferData[0].bandWidth == "null") {
            dojo.byId("serviceBwLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceBwLabel").innerHTML = computingOfferData[0].bandWidth;
        }
        
        
        if(computingOfferData[0].diskReadRateBPS == "" ||
            computingOfferData[0].diskReadRateBPS == null || computingOfferData[0].diskReadRateBPS == "null") {
            dojo.byId("serviceDiskReadRateBPSLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceDiskReadRateBPSLabel").innerHTML = computingOfferData[0].diskReadRateBPS;
        }
        
        if(computingOfferData[0].diskWriteRateBPS == "" ||
            computingOfferData[0].diskWriteRateBPS == null || computingOfferData[0].diskWriteRateBPS == "null") {
            dojo.byId("serviceWriteRateBPSLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceWriteRateBPSLabel").innerHTML = computingOfferData[0].diskWriteRateBPS;
        }
        
        
        if(computingOfferData[0].diskReadRateIOPS == "" ||
            computingOfferData[0].diskReadRateIOPS == null || computingOfferData[0].diskReadRateIOPS == "null") {
            dojo.byId("serviceDiskReadRateIOPSLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceDiskReadRateIOPSLabel").innerHTML = computingOfferData[0].diskReadRateIOPS;
        }
        
        if(computingOfferData[0].diskWriteRateIOPS == "" ||
            computingOfferData[0].diskWriteRateIOPS == null || computingOfferData[0].diskWriteRateIOPS == "null") {
            dojo.byId("serviceWriteRateIOPSLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceWriteRateIOPSLabel").innerHTML = computingOfferData[0].diskWriteRateIOPS;
        }
                
        if (computingOfferData[0].tags == null || computingOfferData[0].tags == "" || computingOfferData[0].tags == " " || computingOfferData[0].tags == "null") {
            dojo.byId ("serviceStorageTagLabel").innerHTML = translator.common.message.noTag;
        } else {         
            dojo.byId ("serviceStorageTagLabel").innerHTML = computingOfferData[0].tags;
        }
        if(computingOfferData[0].hostTags == null || computingOfferData[0].hostTags == "" || computingOfferData[0].hostTags == "null" || computingOfferData[0].hostTags == " ") {
              dojo.byId("serviceHostTagLabel").innerHTML = translator.common.message.noTag;
        } else {
             dojo.byId("serviceHostTagLabel").innerHTML = computingOfferData[0].hostTags;
        }      
        
        dojo.byId ("serviceCpuNumberLabel").innerHTML = computingOfferData[0].cpuNumber+ " " + translator.common.core;
        dojo.byId ("serviceCpuSpeedLabel").innerHTML = computingOfferData[0].coreCpuSpeed ;
        dojo.byId ("serviceMemoryLabel").innerHTML =  computingOfferData[0].memory ;
        
        if(computingOfferData[0].networkRate == "" ||
        computingOfferData[0].networkRate == null || computingOfferData[0].networkRate == "null") {
            dojo.byId("serviceNetworkRateLabel").innerHTML = "...";
        } else {
            dojo.byId("serviceNetworkRateLabel").innerHTML =
               computingOfferData[0].networkRate + translator.common.MBs;
        }
        
        if (computingOfferData[0].offerHA == "true" || computingOfferData[0].offerHA == true) {
             dojo.byId("serviceofferHaLabel").innerHTML = translator.common.yes;
        } else {
             dojo.byId("serviceofferHaLabel").innerHTML = translator.common.no;
        }
        if(computingOfferData[0].limitCpuUse == true ) {
              dojo.byId("serviceCpuCapLabel").innerHTML = translator.common.yes;
        } else {
             dojo.byId("serviceCpuCapLabel").innerHTML = translator.common.no;
        }               
     
        var storageValue = dijit.byId('serviceStorageType').attr('displayedValue');
       
        if (storageValue == "") {
             dojo.byId("serviceStorageTypeLabel").innerHTML = "......";
        } else {
             dojo.byId("serviceStorageTypeLabel").innerHTML = storageValue;
        }      
        
        dojo.byId("serviceStorageTypeLabel").innerHTML = computingOfferData[0].storageType;
        
        
        dojo.query("#addComputOfferZoneLoader").toggleClass("hide_text", true);
        dojo.query("#currentZoneCost").removeClass("hide_text", true);
        dojo.query("#zonePriceLabelContainer").removeClass("hide_text", true);
        });            
        
        dijit.byId('serviceAddButton').set('style', { display: 'none' });
        dijit.byId('serviceUpdateButton').set('style', { display: 'block',  "float": "left"});
     },
     update: function() {        
        var status = AddComputingOfferInfo.authetication();
        var zoneStat = true;
        var zoneNode = dojo.byId("currentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        dojo.forEach(zoneWidgets, function(el) {                       
            var stat = el.showErrorMsg();
            if(stat === false) {
                zoneStat = false;
            }
        });
        
        var computingZoneCosts =[];
        
        if(status === true && zoneStat === true) {
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
