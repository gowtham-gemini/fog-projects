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
            name: "networkOffer",
            module: "admin",
            filePath: "/js/app/admin/networkOffer.js",
            layout: {
                name: "networkOffer",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {
                core.ui.loadTemplate("adminNetworkOfferList", core.ui.getContentId()); 
                ListNetworkOffer.init();
                ListNetworkOffer.populateValues();    
            }),
            "services" : action(function() {
                core.ui.loadTemplate("services", core.ui.getContentId()); 
            }),
            "list": action(function() {  
                core.ui.loadTemplate("adminNetworkOfferList", core.ui.getContentId()); 
                ListNetworkOffer.init();
//                ListNetworkOffer.populateValues();      
            }),
            "view" : action(function(id) {                
                
                var form = dijit.byId("adminNetworkOfferZoneForm");
                if(form) {
                    form.destroyRecursive();
                }
                core.ui.loadTemplate("adminNetworkOfferView", core.ui.getContentId()); 
                ViewNetworkofferInfo.init();
                ViewNetworkofferInfo.populateValues(id);
            })
        });
    });
    
var ViewNetworkofferInfo = {
 
    init: function() {
          
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data,function(cur) { 
                dojo.byId("currencyValue").innerHTML= cur.currency;
            });
        }); 
     },
     populateValues : function(id) {
         
        var networkOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkOffer/"
        });
        networkOfferRestStore.get(id).then(function(data) {
            dojo.forEach(data, function(resultData) {                 
                dojo.byId("zoneNameLabel").innerHTML = resultData.zone;
                dojo.byId("networkOfferNameLabel").innerHTML = resultData.name;
                dojo.byId("networkOfferDesLabel").innerHTML = resultData.description;
                dojo.byId("networkOfferNetWorkRate").innerHTML = resultData.networkRate;
                dojo.byId("networkOfferService").innerHTML = resultData.service;
            }); 
        });
     }
};  
    
    
var ListNetworkOffer = {
     
     init: function() {
          
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("networkOfferCurrencyValue").innerHTML= cur.currency;
               });
        }); 
          
     },
     populateValues : function() {
        dojo.byId("adminNetworkOfferList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
       
        var gridData = {
            items: []
        }; 
                        
        var dataList = new ItemFileWriteStore({data: gridData}); 
        var dataLayout = [[
                 {'field': 'id', 'hidden': 'true'},
                 {'name': translator.common.name, 'field': 'name', 'width': '400px', 'datatype':'string',  'autoComplete': 'true'},
                 {'name': translator.common.zone, 'field': 'zone', 'width': '400px', 'datatype':'string',  'autoComplete': 'true'},
//                 {'name': translator.common.cost, 'field': 'cost', 'width': '10%', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
//                         var html = "<span class='fog_cost'>"+ data + "</span>"
//                        return html;
//                 }},
                 {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) { 
                       var html;
//                            if(currentData[1] == true || currentData[1] == "true") {
                                html = "<a href='#/admin/networkOffer/view/"+data.id+"' title='"+translator.common.view+"'><i class='icon-edit'></i></a>";                                  
//                                        "<a href='#/admin/computation/list/' class='offset1' title='"+translator.common.disable+"' onclick='ComputaionListInfo.disablePlan("+currentData[0]+")'><i class='icon-eye-close'></i></a></li>"+
//                                        "<a href='#/admin/computation/deleteComputation/"+currentData[0]+"' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
//                            } else if(currentData[1] == false || currentData[1] == "false") {
//                                 html = "<a href='#/admin/computation/viewComputation/"+currentData[0]+"' title='"+translator.common.view+"'><i class='icon-edit'></i></a>" +                                       
//                                        "<a href='#/admin/computation/list/' class='offset1' title='"+translator.common.enable+"' onclick='ComputaionListInfo.enablePlan("+currentData[0]+")'><i class='icon-eye-open'></i></a></li>" + 
//                                        "<a href='#/admin/computation/deleteComputation/"+currentData[0]+"' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
//                            }
                           return html;                                   
                       },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                ]
            ]; 
            
            var networkOfferRestStore = new JsonRest({
                target: core.getContextPath()+"/api/networkOffer"
            });
            networkOfferRestStore.query().then(function(data) {
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    dojo.byId("adminNetworkOfferList").innerHTML = "";
                    dojo.byId("noOfferMessageBox").style.display = "block";
                    dojo.byId("adminTotalNetworkoffer").innerHTML = 0;
                    dojo.byId("adminEnabledTotalNetworkoffer").innerHTML = 0;
                    dojo.byId("adminDisabledTotalNetworkoffer").innerHTML = 0;
                } else {
                    dojo.byId("noOfferMessageBox").style.display = "none";
                    dojo.forEach(data, function(resultData) {                 
                        dataList.newItem({
                            id:resultData.referenceId,
                            name:resultData.name,
                            zone: resultData.zone, 
//                            cost:  resultData.cost,
                            action : {id: resultData.id} 
                        });
                    });                                    
                    dojo.byId("adminNetworkOfferList").innerHTML = "";
                    var dataGrid = new EnhancedGrid({   
                        "class" : "span12",
                        store: dataList,
                        structure: dataLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    dataGrid.placeAt("adminNetworkOfferList");
                    dataGrid.startup();  
                    dataGrid.update();
                    
                    var coumputingOfferCountRestStore = new JsonRest({                        
                        target: core.getContextPath()+"/api/computingOffer/count"
                    }); 
                    coumputingOfferCountRestStore.query().then(function(data) {
                        dojo.forEach(data, function(resultData) {
                            dojo.byId("adminTotalNetworkoffer").innerHTML = resultData.total;
                            dojo.byId("adminEnabledTotalNetworkoffer").innerHTML = resultData.enabled;
                            dojo.byId("adminDisabledTotalNetworkoffer").innerHTML = resultData.disabled;                                            
                        });
                    });
                }             
            }); 
        }  
 };
 window.ListNetworkOffer = ListNetworkOffer;
 window.ViewNetworkofferInfo = ViewNetworkofferInfo;