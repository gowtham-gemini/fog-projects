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
    window.tagCount = 0;
    controller ({
        name: "disk",
        module: "admin",
        filePath: "/js/app/admin/diskOffer.js",
        layout: {
            name: "disk",
            containerId: "content"
        },	
        scaffold: false
    },
    {
        "index": action(function() {            
        }),
        "list": action(function() {   
            var form = dijit.byId("diskListForm");
            if(form) {
                form.destroyRecursive();
                dijit.byId("deleteDiskOfferDialog").destroyRecursive();
            }
            
            if(dijit.byId("pullPlanConform")) {
                dijit.byId("pullPlanConform").destroyRecursive();
            }  
            if(dijit.byId("pullPlanLoader")) {
                dijit.byId("pullPlanLoader").destroyRecursive();
            }  
            
            core.ui.loadTemplate("adminDiskList", core.ui.getContentId()); 
            DiskOfferListInfo.init();
            DiskOfferListInfo.populateValues();                
        }),
        "addDisk" : action(function() {               
            var currentOfferItems = dijit.byId("adminDiskOfferZoneForm");
            if(currentOfferItems && dijit.byId("diskOfferContentForm")) {
                currentOfferItems.destroyRecursive();
                dijit.byId("diskOfferContentForm").destroyRecursive();
                dijit.byId("diskOfferEditConformationDialog").destroyRecursive();
            }
            core.ui.loadTemplate("diskOffer", core.ui.getContentId()); 
            AddDiskOfferInfo.init();
            AddDiskOfferInfo.populateValues();
        }),
        "viewDisk" : action(function(id) {
            var currentOfferItems = dijit.byId("adminDiskOfferZoneForm");
            if(currentOfferItems) {
                currentOfferItems.destroyRecursive();
                dijit.byId("diskOfferContentForm").destroyRecursive();
                dijit.byId("diskOfferEditConformationDialog").destroyRecursive();
            }
            core.ui.loadTemplate("diskOffer", core.ui.getContentId()); 
            ViewDiskOfferInfo.init();
            ViewDiskOfferInfo.populateValues(id);
        }),
        "deleteDisk" : action(function(id) {
            var form = dijit.byId("diskListForm");
            if(form) {
                form.destroyRecursive();
                dijit.byId("deleteDiskOfferDialog").destroyRecursive();
            }
            if(dijit.byId("pullPlanConform")) {
                dijit.byId("pullPlanConform").destroyRecursive();
            }  
            if(dijit.byId("pullPlanLoader")) {
                dijit.byId("pullPlanLoader").destroyRecursive();
            }  
            core.ui.loadTemplate("adminDiskList", core.ui.getContentId()); 
            DiskOfferListInfo.init();
            DiskOfferListInfo.populateValues();                   

            DeleteDiskOffer.init();
            DeleteDiskOffer.populateValues(id);                
        }),
        "disableDisk" : action(function(id) {
            var form = dijit.byId("diskListForm");
            if(form) {
                form.destroyRecursive();
                dijit.byId("deleteDiskOfferDialog").destroyRecursive();
            }
            if(dijit.byId("pullPlanConform")) {
                dijit.byId("pullPlanConform").destroyRecursive();
            }  
            if(dijit.byId("pullPlanLoader")) {
                dijit.byId("pullPlanLoader").destroyRecursive();
            }  
            core.ui.loadTemplate("adminDiskList", core.ui.getContentId()); 
            DiskOfferListInfo.init();
            DiskOfferListInfo.populateValues();               

            ActivateDiskStatus.init();
            ActivateDiskStatus.populateValues(id, "disable");     
        }),
        "enableDisk" : action(function(id) {
            var form = dijit.byId("diskListForm");
            if(form) {
                form.destroyRecursive();
                dijit.byId("deleteDiskOfferDialog").destroyRecursive();
            }
            if(dijit.byId("pullPlanConform")) {
                dijit.byId("pullPlanConform").destroyRecursive();
            }  
            if(dijit.byId("pullPlanLoader")) {
                dijit.byId("pullPlanLoader").destroyRecursive();
            }  
            core.ui.loadTemplate("adminDiskList", core.ui.getContentId()); 
            DiskOfferListInfo.init();
            DiskOfferListInfo.populateValues();                                    
            ActivateDiskStatus.init();
            ActivateDiskStatus.populateValues(id, "enable");    
        }) 
    });
});

var ActivateDiskStatus = {
    _diskOfferDisableRestStore:"",
    _currentId:"",
    init: function() {
        this._diskOfferDisableRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/action"
        });
    },
    populateValues: function(currentId, status) {         
        this._currentId = currentId;
        this._diskOfferDisableRestStore.add({
            computingOfferId: currentId,
            status: status
        }).then(function(result) {
            var currentStatus = "";
            if(status == "enable") {
             currentStatus = translator.common.enabled;
            } else if(status == "disable") {
                currentStatus = translator.common.disabled;
            } else {
                currentStatus = "";
            }              
            if(result == "OK") {
                registry.byId("appToaster").setContent(currentStatus +translator.common.message.successfully, "message");
                registry.byId("appToaster").show();
                DiskOfferListInfo.init();
                DiskOfferListInfo.populateValues();    
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();
                DiskOfferListInfo.init();
                DiskOfferListInfo.populateValues();    
            }
        })                      
    }       
};

var DiskOfferListInfo = {
    _diskOfferRestStore:"",
    _diskOfferCountRestStore : "",
    init: function() {
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        });
        this._diskOfferCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/count"
        });        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data,function(cur) { 
                dojo.byId("diskCurrencyValue").innerHTML= cur.currency;
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
           target: core.getContextPath()+"/api/diskOffer/pullFromCloudStack"
        });
        
        pullPlanRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    DiskOfferListInfo.populateValues();
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
        ActivateDiskStatus.init();
        ActivateDiskStatus.populateValues(currentId, "disable");    
    },
    enablePlan :function (currentId) {
        ActivateDiskStatus.init();
        ActivateDiskStatus.populateValues(currentId, "enable");    
    },
    populateValues : function() {             
        dojo.style(dijit.byId("deleteDiskOfferDialog").closeButtonNode,"display","none");
        var gridData = {
            items: []
        };         
        dojo.byId("adminDiskList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        dojo.byId("adminTotalDisk").innerHTML = 0;
        dojo.byId("adminEnabledDisk").innerHTML = 0;
        dojo.byId("adminDisabledDisk").innerHTML = 0; 
        var dataList = new ItemFileWriteStore({data: gridData}); 
        var dataLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '300px', 'datatype':'string',  'autoComplete': 'true'},                                    
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},   
                {'name': translator.common.size.sizeName + "    (    " + translator.common.gb + "   )   " , 'field': 'size', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter' : function(data) {
                        var html = "<span class='fog_cost'>"+ data + "</span>"
                        return html;
                    }
                },    
                {'name': translator.common.custom, 'field': 'custom', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                                    
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) { 
                        var currentData = data.split(",");
                        var html
                        if(currentData[1] == true || currentData[1] == "true") {
                            html = "<a href='#/admin/disk/viewDisk/"+currentData[0]+"' title='"+translator.common.view+"'><i class='icon-edit'></i></a>" +                                       
                                    "<a href='#/admin/disk/list' class='offset1' title='"+translator.common.disable+"' onclick='DiskOfferListInfo.disablePlan("+currentData[0]+")'><i class='icon-eye-close'></i></a></li>"+
                                    "<a href='#/admin/disk/deleteDisk/"+currentData[0]+"' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>" ;                                      
                        } else if(currentData[1] == false || currentData[1] == "false") {
                            html = "<a href='#/admin/disk/viewDisk/"+currentData[0]+"' title='"+translator.common.view+"'><i class='icon-edit'></i></a>" + 
                                    "<a href='#/admin/disk/list' class='offset1' title='"+translator.common.enable+"' onclick='DiskOfferListInfo.enablePlan("+currentData[0]+")'><i class='icon-eye-open'></i></a></li>" + 
                                    "<a href='#/admin/disk/deleteDisk/"+currentData[0]+"' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
                        }
                        return html;                                   
                    },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
            ]
        ];         
        this._diskOfferRestStore.query().then(function(data) {
            if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("adminDiskList").innerHTML = "";
                dojo.byId("noDiskMessageBox").style.display = "block";   
                 
                dojo.byId("adminTotalDisk").innerHTML = 0;
                dojo.byId("adminEnabledDisk").innerHTML = 0;
                dojo.byId("adminDisabledDisk").innerHTML = 0;              
            } else {
                dojo.byId("noDiskMessageBox").style.display = "none";                         
                dojo.forEach(data, function(resultData) {   
                    var custom
                    if(resultData.customized == true || resultData.customized == 'true') {
                        custom = translator.common.yes;
                    } else {
                        custom = translator.common.no;
                    }
                    dataList.newItem({
                        id:resultData.id,
                        name:resultData.name,
                        type: resultData.storageType,                        
                        size: resultData.size,
                        custom: custom,
                        action : resultData.id + "," +resultData.available 
                    });
                });                 
                dojo.byId("adminDiskList").innerHTML = "";
                var dataGrid = new EnhancedGrid({                                              
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminDiskList");
                dataGrid.startup();
                dataGrid.update();
                var diskOfferCountRestStore = new JsonRest({                
                    target: core.getContextPath()+"/api/diskOffer/count"
                });
                diskOfferCountRestStore.query().then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        dojo.byId("adminTotalDisk").innerHTML = resultData.total;
                        dojo.byId("adminEnabledDisk").innerHTML = resultData.enabled;
                        dojo.byId("adminDisabledDisk").innerHTML = resultData.disabled;                                
                    });
                });
            }                                         
        });          
    }     
}; 

var AddDiskOfferInfo = {
    _diskOfferRestStore: "",
    name:"",
    description:"",
    diskSize:"",
    storageTags:"",
    diskZoneWidgets: "",
    _zoneRestStore:"",
    _podRestStore:"",
    _clusterRestStore:"",
    _storageTagRestStore:"",
    _hostTagRestStore:"",
    init : function() {
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
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
        this._storageTagRestStore = new JsonRest({
            target: core.getContextPath()+"/api/storagePool/"
        });        
        this._hostTagRestStore = new JsonRest({
            target: core.getContextPath()+"/api/host/"
        });        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });        
        currency.query().then(function(data) {
            dojo.forEach(data,function(cur) { 
                dojo.byId("currencyValue").innerHTML= cur.currency;
            });
        });   
        
         var domainOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };    
        
        var domainListRest = new JsonRest({
            target: core.getContextPath()+"/api/domain/"
        });
        
        var domainList = new ItemFileWriteStore({data: domainOptions});
        domainListRest.query().then(function(data) {
            dojo.forEach(data,function(cur) { 
                domainList.newItem({id:  cur.referenceId, name: cur.name});
            });
        }); 
        
        var domainWidget = new Select({
            store: domainList,
            id:"diskOfferDomain"
        }).placeAt("diskOfferDomainList"); 
        domainWidget.startup(); 
        
        
    },
    enableQoSType: function (currentQos) {
        if(currentQos.value === "hypervisor") {
            dojo.byId("qOSTypeHypervisorDiv").style.display = "block";
            dojo.byId("qOSTypeStorageDiv").style.display = "none";
            dijit.byId("isCustomizedIops").set("checked", false);
        } else if(currentQos.value === "storage") {
            dojo.byId("qOSTypeHypervisorDiv").style.display = "none";
            dojo.byId("qOSTypeStorageDiv").style.display = "block";
            dijit.byId("isCustomizedIops").set("checked", false);
        } else {
            dojo.byId("qOSTypeHypervisorDiv").style.display = "none";
            dojo.byId("qOSTypeStorageDiv").style.display = "none";
            dijit.byId("isCustomizedIops").set("checked", false);
        }
    },
    enableCustomizedIops: function (currentCustomizedIops) {
        if(currentCustomizedIops.checked === true || currentCustomizedIops.checked === "true") {
            dojo.byId("customizedIopsDiv").style.display = "none";
        } else {
            dojo.byId("customizedIopsDiv").style.display = "block";
        }
    },
    enablePublic: function (currentItem) {
        if(currentItem.checked === true || currentItem.checked === "true") {
            dojo.byId("diskDomainDiv").style.display = "none";
        } else {
            dojo.byId("diskDomainDiv").style.display = "block";
        }
    },
    getTags : function(currentValue) {
        var storageTagRestData = new JsonRest({
            target: core.getContextPath()+"/api/storagePool"
        });                      
        var storageType = currentValue.attr('displayedValue');
        var storageTagOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
        var clusterWidget  = dijit.byId("diskOfferCluster");
        var storageTagWidget = dijit.byId("diskOfferStorageTagList");
        storageTagRestData.query({
            clusterReferenceId: clusterWidget.value,            
            storageType: storageType
        }).then(function(data) {            
            if(data.length == 0 || data == "" || data == " " || data == null || data == 'null' || data == undefined || data == 'undefined') {                                              
                var noTagOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var noTagList = new ItemFileWriteStore({data: noTagOptions});
                noTagList.newItem({id: tagCount,  name: translator.common.message.selectTag, tag:""});
                storageTagWidget.set("store",noTagList);
                storageTagWidget.set("value",tagCount);                
                tagCount = tagCount + 1;                
            } else if(data[0].storageTag == null || data[0].storageTag == "null") {                       
                var nullTagOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var nullTagList = new ItemFileWriteStore({data: nullTagOptions});
                nullTagList.newItem({id: tagCount,  name: translator.common.message.noTag, tag:""});
                storageTagWidget.set("store",nullTagList);
                storageTagWidget.set("value",tagCount);                  
                tagCount = tagCount + 1;                
            } else {                  
                var firstTagVal = "";
                dojo.forEach(data, function(currentStorageTag, index) {
                    storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId,  name: currentStorageTag.storageTag, tag: currentStorageTag.storageTag});
                    if(index == 0) {
                        firstTagVal = currentStorageTag.storagePoolReferenceId;
                    }
                });
                storageTagWidget.set("store",storageTagList);
                storageTagWidget.set("value",firstTagVal);                     
            }                                             
        });
    },
    populateValues : function() {                                                                                     
        dojo.query("#diskUpdateButtonDiv").removeClass("span3", true);
        var size = dijit.byId("diskSize").value;
        dojo.byId("diskCostforSizePerMonth").innerHTML = translator.common.costFor + " " + size + translator.common.gb + "/" + translator.common.month;                          
        dojo.byId("diskCostforSizePerHr").innerHTML = translator.common.costFor + " " + size + translator.common.gb + "/" + translator.common.hour;                          
        dojo.byId("diskCostPerHrPerGb").innerHTML = translator.common.cost + " / " + translator.common.gb + "/" + translator.common.hour;  
        
        dojo.query("#addDiskOfferZoneLoader").removeClass("hide_text", true);
        dojo.query("#diskZoneCost").toggleClass("hide_text", true);
        dojo.query("#diskZonePriceLabelContainer").toggleClass("hide_text", true);
        
        var diskSizeSpinnersize = dijit.byId("diskSize").value;  
        this._zoneRestStore.query().then(function(data) {    
            dojo.forEach(data, function (el, index) {
                if(dijit.byId("disk_zone_widget_"+el.name+"_"+index)) {
                    dijit.byId("disk_zone_widget_"+el.name+"_"+index).destroyRecursive();
                }
                var currentZonePrice  = new FogPanel.ZonePrice({
                    id:"disk_zone_widget_"+el.name+"_"+index,
                    zoneName: el.name,
                    zoneIdNode: el.id,
                    calcType: "sizeBase",
                    diskSize : diskSizeSpinnersize          
                });
                currentZonePrice.placeAt("diskZoneCost");  
                currentZonePrice.showOnlyZoneCost();
                dojo.query("#addDiskOfferZoneLoader").toggleClass("hide_text", true);
                dojo.query("#diskZoneCost").removeClass("hide_text", true);
                dojo.query("#diskZonePriceLabelContainer").removeClass("hide_text", true);
            });
        });                            
    },
    enableContent: function(currentElement) {    
        var customDisk = dijit.byId("customDisk");    
        var size = dijit.byId("diskSize").value;
         var zoneNode = dojo.byId("diskZoneCost");
         var zoneWidgets = dijit.registry.findWidgets(zoneNode);        
         dojo.forEach(zoneWidgets, function(el) {                       
             el.customDiskActionInfo(currentElement.checked);             
         });  
        if(currentElement.checked === true) {
            dojo.byId("diskSizeHide").style.display = "none";
            dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").toggleClass("updatableContent ", true);             
            dojo.byId("diskCostforSizePerMonth").innerHTML = translator.common.cost + " / " + translator.common.gb + "/" + translator.common.month;                          
            dojo.byId("diskCostforSizePerHr").innerHTML = translator.common.cost + " / " + translator.common.gb + "/" + translator.common.hour;                          
            dojo.byId("diskCostPerHrPerGb").innerHTML = "";
        } else if(customDisk.checked === false) {
            dojo.byId("diskSizeHide").style.display = "block";
            dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true); 
            dojo.byId("diskCostforSizePerMonth").innerHTML = translator.common.costFor + " " + size + translator.common.gb + "/" + translator.common.month;                          
            dojo.byId("diskCostforSizePerHr").innerHTML = translator.common.costFor + " " + size + translator.common.gb + "/" + translator.common.hour;                          
            dojo.byId("diskCostPerHrPerGb").innerHTML = translator.common.cost + " / " + translator.common.gb + "/" + translator.common.hour;
            
        }                   
    },
    showZoneCost : function (currentSpinner) {                        
            var size = ""
            if(currentSpinner.value == "NaN" || currentSpinner.value == "undefined") {
                size = "0"
            } else {
                size = currentSpinner.value;
                dojo.byId("diskCostforSizePerMonth").innerHTML = translator.common.costFor + " " + size + translator.common.gb + "/" + translator.common.month;                          
                dojo.byId("diskCostforSizePerHr").innerHTML = translator.common.costFor + " " + size + translator.common.gb + "/" + translator.common.hour;                          
                dojo.byId("diskCostPerHrPerGb").innerHTML = translator.common.cost + " / " + translator.common.gb + "/" + translator.common.hour;                          
                
                var zoneNode = dojo.byId("diskZoneCost");
                var zoneWidgets = dijit.registry.findWidgets(zoneNode);        
                dojo.forEach(zoneWidgets, function(el) {                       
                    el.clearWidgets();
                    el.changeDiskLabelValue(size);
                });                                   
        }        
    },          
    authetication : function() {
        var status = true;
        var pageNode = dojo.byId("adminDiskOfferPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
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
        var status = AddDiskOfferInfo.authetication();                
        var zoneCostStat = true;  
        var zoneNode = dojo.byId("diskZoneCost");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
        dojo.forEach(zoneWidgets, function(el) {                       
            var stat = el.showError();
            if(stat === false) {
                zoneCostStat = false;
            }
        }); 
        
        
        if(status === true && zoneCostStat === true) {
            dojo.query("#diskOfferLoader").removeClass("hide_text", true);
            dojo.query("#diskAddButtonDiv").toggleClass("hide_text", true);
            dojo.query("#diskCancelButtonDiv").toggleClass("hide_text", true);
            
            var diskZoneCosts = [];
            dijit.byId("diskAddButton").set("disabled", true);
            var name = dijit.byId("diskName");
            var editValue = dijit.byId("diskDescription").value.toString();
            var description = editValue.replace("\n"," ");
            var diskSize = dijit.byId("diskSize");
            var storageTag = dijit.byId("diskStorageTags").value;
            var storageType = dijit.byId("diskStorageType");
            var customDisk = dijit.byId("customDisk");            
            
            var diskReadRateBPS = dijit.byId("diskOfferDiskReadRateBPS");
            var diskWriteRateBPS = dijit.byId("diskOfferDiskWriteRateBPS");
            var diskReadRateIOPS = dijit.byId("diskOfferDiskReadRateIOPS");
            var diskWriteRateIOPS = dijit.byId("diskOfferDiskWriteRateIOPS");
            var qoSType = dijit.byId("qoSType");
            var isCustomizedIops = dijit.byId("isCustomizedIops");
            
            
            var isPublic = dijit.byId("isPublic");
            var diskDomain = dijit.byId("diskOfferDomain");
            
            var minIOPS = dijit.byId("minIOPS");
            var maxIOPS = dijit.byId("maxIOPS");
            var hypervisorSnapReserve = dijit.byId("hypervisorSnapReserve");
            
                
            var minSize = dijit.byId("diskMinSize").value;
                        
            var maxSize = dijit.byId("diskMaxSize").value;
            
            if(customDisk.checked === true) {
                diskSize = 0;
            } else {
                diskSize =  dijit.byId("diskSize").value;
            }
            
            var diskZoneNode = dojo.byId("diskZoneCost");
            this.diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
            dojo.forEach(this.diskZoneWidgets, function(el) {      
                if(customDisk.checked == true) {
                    diskZoneCosts.push ({
                        zoneId: el.getZoneId(), 
                        cost: el.getZoneCost(),
                        minimumCost: "5"                
                    });                    
                } else {
                    var currentcost = el.getZoneCostValue();
                    var cost = ((currentcost/diskSize)/720).toFixed(5);                        
                     diskZoneCosts.push ({
                        zoneId: el.getZoneId(), 
                        cost: cost,
                        minimumCost: "5"                
                    }); 
                }                   
            });
            
            for (var index = 0; index < diskZoneCosts.length-this.diskZoneWidgets.length; index++) {
                diskZoneCosts.splice(index, this.diskZoneWidgets.length);
            }                                
            
            this._diskOfferRestStore.add({
                name: name.value,                
                customDisk:customDisk.checked,
                description: description,
                storageType : storageType.value,
                diskSize: diskSize, 
                minSize: minSize,
                maxSize: maxSize,
                isPublic: isPublic.checked,
                domainId: diskDomain.value,
                storageTag: storageTag, 
                zoneCosts: diskZoneCosts,
                qoSType:qoSType.value,
                diskReadRateBPS:diskReadRateBPS.value,	
                diskWriteRateBPS:diskWriteRateBPS.value,	
                diskReadRateIOPS:diskReadRateIOPS.value,	
                diskWriteRateIOPS:diskWriteRateIOPS.value,
                isCustomizedIops:isCustomizedIops.checked,
                minIOPS:minIOPS.value,
                maxIOPS:maxIOPS.value,
                hypervisorSnapReserve:hypervisorSnapReserve.value
            }).then(function (data) {   
                dijit.byId("diskAddButton").set("disabled", false);
                
                dojo.query("#diskOfferLoader").toggleClass("hide_text", true);
                dojo.query("#diskAddButtonDiv").removeClass("hide_text", true);
                dojo.query("#diskCancelButtonDiv").removeClass("hide_text", true);                
                if(data[0].result == "OK") {                    
                    var diskZoneNode = dojo.byId("currentDiskZoneInfo");
                    var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
                    dojo.forEach(diskZoneWidgets, function(el) {
                        el.clearWidgets();
                    });            
                    registry.byId("appToaster").setContent(translator.common.message.addedSuccess, "message");
                    registry.byId("appToaster").show();        

                    dijit.byId("diskDescription").set("value", "");
                    dijit.byId("diskOfferContentForm").reset();
                    core.router.go("#/admin/disk/list");
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.cannotBeAdded, "error");
                    registry.byId("appToaster").show();
                 }
            });             
        }     
    },
    cancel: function() {
        core.router.go("#/admin/disk/list");
    }
        
};
 
var ServiceConfig = {
    openTab : function() {        
        var liElement = dojo.byId("serviceMenu");       
        var varticalMenuBar = dijit.byId("verticalMenu");
        varticalMenuBar.onLiClick(liElement);
    }
};

var ViewDiskOfferInfo = {
    _diskOfferRestStore:"",
    _currentDiskId:"",
    init: function() {
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        });                    
        var currency = new JsonRest ({
            target: core.getContextPath()+"/api/config/currency"
        });        
           
        currency.query().then(function(data) {
            dojo.forEach(data,function(cur) { 
                dojo.byId("currencyValue").innerHTML= cur.currency;
            });
        });         
    },    
    populateValues : function(currentId) {
        this._currentDiskId = currentId;  
                         
        dojo.query("#addDiskOfferZoneLoader").removeClass("hide_text", true);
        dojo.query("#diskZoneCost").toggleClass("hide_text", true);
        dojo.query("#diskZonePriceLabelContainer").toggleClass("hide_text", true);
        
        dojo.byId("editComputBreadcrum").innerHTML = "View";
        dojo.byId("diskSizeHide").style.display = "block";
        var zoneNode = dojo.byId("currentDiskZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        dojo.forEach(zoneWidgets, function(el) {
            el.clearWidgets();
        });       
        var zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });
        this._diskOfferRestStore.get(currentId).then(function(diskOfferData) {            
            dojo.byId("diskBillingInfo").style.display = "block";
            dojo.query("#diskOfferingPage .updatable").toggleClass("non_updatable", true);
            dojo.query("#diskOfferingPage .hide_lable").toggleClass("show_lable", true);       
            dijit.byId("diskName").setValue(diskOfferData[0].name);
            dijit.byId("diskDescription").setValue(diskOfferData[0].description);    
            var calcType = "";
            var diskSize = "";                                                        
            
            zoneRestStore.query().then(function (data) {
                dojo.forEach(data, function (zoneItem, index) {                    
                    var cost = "";                    
                    dojo.forEach(diskOfferData[0].diskOfferZoneCosts, function(zoneData) {
                        if(diskOfferData[0].customized === true) {
                            calcType = "";
                            diskSize = "";
                            cost = zoneData.cost * 720;

                            dojo.byId("diskCostforSizePerMonth").innerHTML = translator.common.cost + " / " + translator.common.gb + "/" + translator.common.month;                          
                            dojo.byId("diskCostforSizePerHr").innerHTML = translator.common.cost + " / " + translator.common.gb + "/" + translator.common.hour;                          
                            dojo.byId("diskCostPerHrPerGb").innerHTML = "";                    
                        } else {
                            calcType = "sizeBase";
                            diskSize = diskOfferData[0].size;
                            dojo.byId("CurrentDiskSizeRef").value = diskOfferData[0].size;
                            cost = (zoneData.cost * diskOfferData[0].size)*720;                    

                            dojo.byId("diskCostforSizePerMonth").innerHTML = translator.common.costFor + " " + diskOfferData[0].size + translator.common.gb + "/" + translator.common.month;                          
                            dojo.byId("diskCostforSizePerHr").innerHTML = translator.common.costFor + " " + diskOfferData[0].size + translator.common.gb + "/" + translator.common.hour;                          
                            dojo.byId("diskCostPerHrPerGb").innerHTML = translator.common.cost + " / " + translator.common.gb + "/" + translator.common.hour; 
                        }                                                                 
                    });
                    if(dijit.byId("disk_zone_widget_"+zoneItem.aliasName +"_"+index)) {
                        dijit.byId("disk_zone_widget_"+zoneItem.aliasName +"_"+index).destroyRecursive();
                    }
                    var currentZonePrice  = new FogPanel.ZonePrice({
                        id:"disk_zone_widget_"+zoneItem.aliasName +"_"+index,
                        zoneName: zoneItem.aliasName,
                        zoneIdNode: zoneItem.id,
                        calcType: calcType,
                        diskSize : diskSize,
                        zoneCost : cost,
                    });
                    currentZonePrice.placeAt("diskZoneCost");  
                    currentZonePrice.setCost(); 
                    currentZonePrice.showOnlyZoneCost();
                    currentZonePrice.customDiskActionInfo(diskOfferData[0].customized);                                        
                });                                
                dojo.query("#addDiskOfferZoneLoader").toggleClass("hide_text", true);
                dojo.query("#diskZoneCost").removeClass("hide_text", true);
                dojo.query("#diskZonePriceLabelContainer").removeClass("hide_text", true);                                                             
            });                      
            if(diskOfferData[0].minSize == ""|| diskOfferData[0].minSize == null || diskOfferData[0].minSize == "null") {
                dojo.byId("diskMinSizeLabel").innerHTML = ".......";
            } else {
                dojo.byId("diskMinSizeLabel").innerHTML = diskOfferData[0].minSize;  
            }                
            
            if(diskOfferData[0].maxSize == ""|| diskOfferData[0].maxSize == null || diskOfferData[0].maxSize == "null") {
                dojo.byId("diskMaxSizeLabel").innerHTML = ".......";
            } else {
                dojo.byId("diskMaxSizeLabel").innerHTML = diskOfferData[0].maxSize;  
            }        
            
            if(diskOfferData[0].customized == "true" || diskOfferData[0].customized == true) {
                dojo.byId("diskSizeHide").style.display = "none";
                dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").toggleClass("updatableContent ", true);
            } else if(diskOfferData[0].customized == "false" || diskOfferData[0].customized == false) {
                dojo.byId("diskSizeHide").style.display = "block";
                dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
            }                       
            
            if(diskOfferData[0].tags == "null"|| diskOfferData[0].tags == null || diskOfferData[0].tags == "" || diskOfferData[0].tags == " ") {
                dojo.byId("diskStorageTagsLabel").innerHTML = translator.common.message.noTag;
            } else {
                dojo.byId("diskStorageTagsLabel").innerHTML = diskOfferData[0].tags; 
            }
        
            if(diskOfferData[0].storageType == "null"|| diskOfferData[0].storageType == null || diskOfferData[0].storageType == "") {
                dojo.byId("diskStorageTypeLabel").innerHTML = ".......";
            } else {
                dojo.byId("diskStorageTypeLabel").innerHTML = diskOfferData[0].storageType; 
            }           

            dojo.byId("diskSizeLabel").innerHTML = diskOfferData[0].size + " "+translator.common.gb;        
        
            if(diskOfferData[0].customized == "false"|| diskOfferData[0].customized == false) {
               dojo.byId("customDiskLabel").innerHTML = translator.common.no; 
            } else if(diskOfferData[0].customized == "true"|| diskOfferData[0].customized == true) {
                dojo.byId("customDiskLabel").innerHTML = translator.common.yes; 
            }
            
            if(diskOfferData[0].isPublic == "false"|| diskOfferData[0].isPublic == false) {
               dojo.byId("diskOfferIsPublicLabel").innerHTML =  translator.common.no; 
               dojo.byId("diskDomainDiv").style.display = "block";
            } else if(diskOfferData[0].isPublic == "true"|| diskOfferData[0].isPublic == true) {
                dojo.byId("diskOfferIsPublicLabel").innerHTML =  translator.common.yes; 
                dojo.byId("diskDomainDiv").style.display = "none";
            }
            
            if(diskOfferData[0].domainId == ""|| diskOfferData[0].domainId == null ||  diskOfferData[0].domainId == "null") {
                dojo.byId("diskOfferDomainLabel").innerHTML = "..."; 
            } else { 
                dojo.byId("diskOfferDomainLabel").innerHTML = diskOfferData[0].domainId; 
            } 
            
            
            if(diskOfferData[0].qoSType == ""|| diskOfferData[0].qoSType == null ||  diskOfferData[0].qoSType == "null") {
                dojo.byId("qoSTypeLabel").innerHTML = "..."; 
            } else { 
                dojo.byId("qoSTypeLabel").innerHTML = diskOfferData[0].qoSType; 
            } 
            
            if(diskOfferData[0].qoSType === "hypervisor") {
                dojo.byId("qOSTypeHypervisorDiv").style.display = "block";
                dojo.byId("qOSTypeStorageDiv").style.display = "none";
            } else if(diskOfferData[0].qoSType === "storage") {
                dojo.byId("qOSTypeHypervisorDiv").style.display = "none";
                dojo.byId("qOSTypeStorageDiv").style.display = "block";
            } else {
                dojo.byId("qOSTypeHypervisorDiv").style.display = "none";
                dojo.byId("qOSTypeStorageDiv").style.display = "none";
            }
            
            if(diskOfferData[0].diskReadRateBPS == "" ||
                diskOfferData[0].diskReadRateBPS == null || diskOfferData[0].diskReadRateBPS == "null") {
                dojo.byId("diskOfferDiskReadRateBPSLabel").innerHTML = "...";
            } else {
                dojo.byId("diskOfferDiskReadRateBPSLabel").innerHTML = diskOfferData[0].diskReadRateBPS;
            }


            if(diskOfferData[0].diskWriteRateBPS == "" ||
                diskOfferData[0].diskWriteRateBPS == null || diskOfferData[0].diskWriteRateBPS == "null") {
                dojo.byId("diskOfferWriteRateBPSLabel").innerHTML = "...";
            } else {
                dojo.byId("diskOfferWriteRateBPSLabel").innerHTML = diskOfferData[0].diskWriteRateBPS;
            }


            if(diskOfferData[0].diskReadRateIOPS == "" ||
                diskOfferData[0].diskReadRateIOPS == null || diskOfferData[0].diskReadRateIOPS == "null") {
                dojo.byId("diskOfferDiskReadRateIOPSLabel").innerHTML = "...";
            } else {
                dojo.byId("diskOfferDiskReadRateIOPSLabel").innerHTML = diskOfferData[0].diskReadRateIOPS;
            }

            if(diskOfferData[0].diskWriteRateIOPS == "" ||
                diskOfferData[0].diskWriteRateIOPS == null || diskOfferData[0].diskWriteRateIOPS == "null") {
                dojo.byId("diskOfferWriteRateIOPSLabel").innerHTML = "...";
            } else {
                dojo.byId("diskOfferWriteRateIOPSLabel").innerHTML = diskOfferData[0].diskWriteRateIOPS;
            }
            
            if(diskOfferData[0].isCustomizedIops === true || diskOfferData[0].isCustomizedIops === "true") {
                dojo.byId("customizedIopsDiv").style.display = "none";
            } else {
                dojo.byId("customizedIopsDiv").style.display = "block";
            }
            
            if(diskOfferData[0].isCustomizedIops == "false"|| diskOfferData[0].isCustomizedIops == false) {
               dojo.byId("diskOfferCustomizedIopsLabel").innerHTML = translator.common.no; 
            } else if(diskOfferData[0].isCustomizedIops == "true"|| diskOfferData[0].isCustomizedIops == true) {
                dojo.byId("diskOfferCustomizedIopsLabel").innerHTML = translator.common.yes; 
            }
            
            if(diskOfferData[0].minIOPS == "" ||
                diskOfferData[0].minIOPS == null || diskOfferData[0].minIOPS == "null") {
                dojo.byId("minIOPSLabel").innerHTML = "...";
            } else {
                dojo.byId("minIOPSLabel").innerHTML = diskOfferData[0].minIOPS;
            }
            
            if(diskOfferData[0].maxIOPS == "" ||
                diskOfferData[0].maxIOPS == null || diskOfferData[0].maxIOPS == "null") {
                dojo.byId("maxIOPSLabel").innerHTML = "...";
            } else {
                dojo.byId("maxIOPSLabel").innerHTML = diskOfferData[0].maxIOPS;
            }
        });      
        dijit.byId('diskAddButton').set('style', {display: 'none'});
        dijit.byId('diskUpdateButton').set('style', {display: 'block', "float": 'left'});
    },
    updateShow : function() { 
        var status = AddDiskOfferInfo.authetication();     
        
        var zoneCostStat = true;  
        var zoneNode = dojo.byId("diskZoneCost");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
        dojo.forEach(zoneWidgets, function(el) {                       
            var stat = el.showError();
            if(stat === false) {
                zoneCostStat = false;
            }
        }); 
        
        if(status === true && zoneCostStat === true) {  
            dijit.byId("diskOfferEditConformationDialog").show();
        }                 
    },
    closeUpdate : function() {  
         dijit.byId("diskOfferEditConformationDialog").hide();
    },
    update: function() {
        var status = AddDiskOfferInfo.authetication();     
        
        var zoneCostStat = true;  
        var zoneNode = dojo.byId("diskZoneCost");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
        dojo.forEach(zoneWidgets, function(el) {                       
            var stat = el.showError();
            if(stat === false) {
                zoneCostStat = false;
            }
        }); 
        
        if(status === true && zoneCostStat === true) {          
            dojo.query("#diskOfferLoader").removeClass("hide_text", true);
            dojo.query("#diskUpdateButtonDiv").toggleClass("hide_text", true);
            dojo.query("#diskCancelButtonDiv").toggleClass("hide_text", true);
            dijit.byId("diskOfferEditConformationDialog").hide();
            var name = dijit.byId ("diskName");
            var diskZoneCosts =[];
            dijit.byId("diskUpdateButton").set("disabled", true);
            var editValue = dijit.byId("diskDescription").value.toString();
            var description = editValue.replace("\n"," ");                                 
            
            var zoneNode = dojo.byId("currentDiskZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);
            dojo.forEach(zoneWidgets, function(el) {
                if(dojo.byId("customDiskLabel").innerHTML == "Yes") {
                    diskZoneCosts.push({
                        zoneId: el.getZoneId(), 
                        cost: el.getZoneCostValue()/720,
                        minimumCost: "6"
                    }); 
                } else if(dojo.byId("customDiskLabel").innerHTML == "No") {                  
                    var diskSize = dojo.byId("CurrentDiskSizeRef").value;
                    var currentcost = el.getZoneCostValue();
                    var cost = ((currentcost/diskSize)/720).toFixed(5); 
                    diskZoneCosts.push({
                        zoneId: el.getZoneId(), 
                        cost: cost,
                        minimumCost: "6"
                    }); 
                }                              
            });   
            for(var index=0; index < diskZoneCosts.length-zoneWidgets.length; index++) {
                diskZoneCosts.splice(index, zoneWidgets.length);
            }            
            this._diskOfferRestStore.put({
                id: this._currentDiskId ,
                name: name.value,
                description: description,
                zoneCosts: diskZoneCosts                 
            }).then(function(result) {
                dijit.byId("diskUpdateButton").set("disabled", false);
                
                dojo.query("#diskOfferLoader").toggleClass("hide_text", true);
                dojo.query("#diskUpdateButtonDiv").removeClass("hide_text", true);
                dojo.query("#diskCancelButtonDiv").removeClass("hide_text", true);
                if(result == "OK") {                                      
                    dojo.query("#diskOfferingPage .updatable").removeClass("non_updatable", true);
                    dojo.query("#diskOfferingPage .hide_lable").removeClass("show_lable", true);
                    dijit.byId('diskAddButton').set('style', {display: 'block', "float": 'left'});
                    dijit.byId('diskUpdateButton').set('style', {display: 'none'});                     
                    var diskZoneNode = dojo.byId("currentDiskZoneInfo");
                    var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
                    dojo.forEach(diskZoneWidgets, function(el) {
                        el.clearWidgets();                            
                    });
                    dijit.byId("diskOfferEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();
                    dijit.byId("diskOfferContentForm").reset();
                    dijit.byId("diskDescription").set("value", "");                     
                    core.router.go("#/admin/disk/list");                     
                } else {
                    dijit.byId("diskOfferEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                }  
            });                                              
        }                  
    }
};

var DeleteDiskOffer = {
    _diskOfferRestStore:"",
    _currentId:"",
    init: function() {
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/delete/"
        });
     },
     populateValues: function(currentId) {
         this._currentId = currentId;
         dijit.byId("deleteDiskOfferDialog").show();
     },
     conformDelete: function() {
         dijit.byId("deleteDiskOfferDialog").hide();
         var confirmDelete = dijit.byId("diskDeleteConfirm").checked;
         this._diskOfferRestStore.add({
             diskOfferId: this._currentId,
             forced : confirmDelete.toString()
         }).then(function(result) {
             if(result == "OK") {                               
                 registry.byId("appToaster").setContent(translator.common.message.deletedSuccess, "message");
                 registry.byId("appToaster").show();
                 core.router.go("#/admin/disk/list");
             } else { 
                 var resultData = "";                
                if(result[0].cause.localizedMessage == translator.common.message.volumeExist) {
                    resultData = result[0].cause.localizedMessage;
                } else {
                    resultData = translator.common.cloudStack.errorMessage
                }
                 registry.byId("appToaster").setContent(resultData, "error");
                 registry.byId("appToaster").show();
                 core.router.go("#/admin/disk/list");
             }
         });
     },
     closeDeleteDialog : function() {
         dijit.byId("deleteDiskOfferDialog").hide();
         core.router.go("#/admin/disk/list");
     }     
 };

 
 window.DiskOfferListInfo  = DiskOfferListInfo; 
 window.AddDiskOfferInfo = AddDiskOfferInfo; 
 window.ViewDiskOfferInfo = ViewDiskOfferInfo;
 window.ServiceConfig = ServiceConfig; 
 window.DeleteDiskOffer = DeleteDiskOffer;
 window.ActivateDiskStatus = ActivateDiskStatus;