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
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.pod, 'field': 'pod', 'hidden': 'true', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.cluster, 'field': 'cluster', 'hidden': 'true', 'width': '150px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
                        var html = "<span class='adminBolder'>"+ data + "</span>"
                        return html;  
                    }
                },    
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},   
                {'name': translator.common.cost, 'field': 'cost', 'width': '100px', 'datatype':'string',  'autoComplete': 'true','formatter' : function(data) {
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
                        zone: resultData.zone.aliasName, 
                        pod: resultData.pod.name,
                        cluster: resultData.cluster.name,
                        type: resultData.storageType,
                        cost: (resultData.diskOfferZoneCosts[0].cost).toFixed(5),
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
        if(dijit.byId("diskOfferZoneListItem")) {             
            dijit.byId("diskOfferZoneListItem").destroyRecursive();
        }        
        if(dijit.byId("diskOfferStorageTagList") && dijit.byId("diskOfferCluster") &&  dijit.byId("diskOfferPod"))  {
            dijit.byId("diskOfferStorageTagList").destroyRecursive();
            dijit.byId("diskOfferCluster").destroyRecursive();
            dijit.byId("diskOfferPod").destroyRecursive();  
        }                        
        var clusterRestStore = this._clusterRestStore;                      
        dojo.query("#diskUpdateButtonDiv").removeClass("span3", true); 
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
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});                 
        
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});                

        this._clusterWidget = new FilteringSelect({                    
            id: "diskOfferCluster",
            store: clusterList,
            value: "option"

        }).placeAt("diskOfferClusterList");             
        
        this._podWidget = new FilteringSelect({     
            id: "diskOfferPod",
            store: podList,
            value: "option",
            onChange: function() {                   
                var clusterWidget = dijit.byId("diskOfferCluster");
                var clusterOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                
                var currentClusterList  = new ItemFileWriteStore({data: clusterOptions});
                if(this.value == "option") {
                    currentClusterList.newItem({id: "option", name: translator.common.message.selectCluster});
                    clusterWidget.set("store",currentClusterList);
                    clusterWidget.set("value","option");                         
                }
                clusterRestStore.get(this.value).then(function(clusterListItems) {
                    var firstclusterVal = "";
                    dojo.forEach(clusterListItems,function(currentcluster, index) {                         
                        currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName})
                        if(index == 0) {
                            firstclusterVal = currentcluster.clusterReferenceId;
                        }
                    });    
                    clusterWidget.set("store",currentClusterList);
                    clusterWidget.set("value",firstclusterVal);   
                });
            }
        }).placeAt("diskOfferPodList"); 
        this._podWidget.startup();
        
        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.message.selectZone}]
        };        
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName})
            });
        });        
        
        this._zoneWidget = new FilteringSelect({
            store: zoneList,
            id:"diskOfferZoneListItem",
            value: "option",
            onChange: function() {
                AddDiskOfferInfo.viewSelectedZone(this);   
            }
        }).placeAt("diskOfferZoneList"); 
        this._zoneWidget.startup();      
    },
    enableContent: function(currentElement) {    
        var customDisk = dijit.byId("customDisk");    
        var diskSize = dijit.byId("diskSize")
        var currentZoneWidget = dijit.byId("diskOfferCurrentZone");    
        if(currentZoneWidget) {
            currentZoneWidget.setZoneCostValue(0);
            if(currentElement.checked == true) {
                dojo.byId("diskSizeHide").style.display = "none";
                dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").toggleClass("updatableContent ", true);
                if(currentZoneWidget) {
                    var calcyType = ""                                                                    
                    currentZoneWidget.setCalcType(calcyType);   
                    currentZoneWidget.vmRunningCostLabel = translator.common.costPerGBMonth                        
                    currentZoneWidget.changeDiskLabelValue(1);                
                }            
            } else if(customDisk.checked == false) {
                dojo.byId("diskSizeHide").style.display = "block";
                dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
                if(currentZoneWidget) {
                    var calcyType = "sizeBase"                                                                    
                    currentZoneWidget.setCalcType(calcyType);    
                    currentZoneWidget.vmRunningCostLabel = translator.common.costFor + " " + diskSize.value + translator.common.gb + "/" + translator.common.month                 
                    currentZoneWidget.changeDiskLabelValue(diskSize.value);  
                } 
            }            
        }         
    },
    showZoneCost : function (currentSpinner) {        
        var currentZoneWidget = dijit.byId("diskOfferCurrentZone");
        if(currentZoneWidget) {
            var size = ""
            if(currentSpinner.value == "NaN" || currentSpinner.value == "undefined") {
                size = "0"
            } else {
                size = currentSpinner.value;
                currentZoneWidget.vmRunningCostLabel = translator.common.costFor + " " + size + translator.common.gb + "/" + translator.common.month                          
                currentZoneWidget.changeDiskLabelValue(size);                
                currentZoneWidget.zoneCost = 0;
                currentZoneWidget.setCost();
            }            
        }        
    },    
    viewSelectedZone : function(currentZone) { 
        var podWidget = dijit.byId("diskOfferPod");
        
        var currentZoneCost;                
        if(dijit.byId("diskOfferCurrentZone")) {
            dijit.byId("diskOfferCurrentZone").destroyRecursive();
        }        
        var diskSizeSpinnersize = dijit.byId("diskSize").value;       
        this._zoneRestStore.get(currentZone.value).then(function(selectedZoneInfo) { 
            currentZoneCost  = new Zone.ZoneCost({
                id:"diskOfferCurrentZone",
                labelName: translator.common.storageCostPerMonth,
                zoneIdNode: selectedZoneInfo.id,
                calcType: "sizeBase",
                runningCostPerHrLabel: translator.common.cost + translator.common.perGBPerHr,
                diskSize : diskSizeSpinnersize,                
                vmRunningCostLabel : translator.common.costFor + " " + diskSizeSpinnersize + translator.common.gb + "/" + translator.common.month ,  
                invalidMsg : translator.common.zoneCostInvalidMsg                 
            });
            currentZoneCost.placeAt("diskZoneCost"); 
            currentZoneCost.showOnlyZoneCost();            
//            currentZoneCost.removeCosts();            
//            currentZoneCost.setCostRate();
        }) 
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };    
        
        var currentPodList =  new ItemFileWriteStore({data: podOptions});
        if(currentZone.value == "option") {
            currentPodList.newItem({id: "option", name: translator.common.message.selectPode});           
            podWidget.set("store",currentPodList);
            podWidget.set("value","option"); 
            dijit.byId("diskAddButton").set("disabled", true);
            dijit.byId("diskCancelButton").set("disabled", true);            
        } else {
            dijit.byId("diskAddButton").set("disabled", false);
        }
        this._podRestStore.get(currentZone.value).then(function(podListItems) {  
            var firstPodVal = ""
            dojo.forEach(podListItems, function(currentPod, index) {               
                currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName})               
                if(index == 0) {
                    firstPodVal = currentPod.podReferenceId
                }
            });
            podWidget.set("store", currentPodList);
            podWidget.set("value", firstPodVal);            
        });    
        dijit.byId("customDisk").set("checked", false);    
        dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
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
        var zoneNode = dojo.byId("currentDiskZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
        dojo.forEach(zoneWidgets, function(el) {                       
            status = el.showError();
        });        
        return status;
    },
    add: function() {
        var status = AddDiskOfferInfo.authetication();   
        if(status == true) {
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
            var podWidget = dijit.byId("diskOfferPod");
            var clusterWidget = dijit.byId("diskOfferCluster");
            
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
            
            if(customDisk.checked == true) {
                diskSize = 0;
            } else {
                diskSize =  dijit.byId("diskSize").value;
            }
            
            var diskZoneNode = dojo.byId("currentDiskZoneInfo");
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
                referenceZoneId : this._zoneWidget.value,
                podReferenceId :  podWidget.value,
                clusterReferenceId: clusterWidget.value,
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
    updateShow : function() {  
         dijit.byId("diskOfferEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("diskOfferEditConformationDialog").hide();
    },
    populateValues : function(currentId) {
        this._currentDiskId = currentId;        
        dojo.byId("editComputBreadcrum").innerHTML = "View";
        dojo.byId("diskSizeHide").style.display = "block";
        var zoneNode = dojo.byId("currentDiskZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        dojo.forEach(zoneWidgets, function(el) {
            el.clearWidgets();
        });            
        this._diskOfferRestStore.get(currentId).then(function(diskOfferData) {            
            dojo.byId("zoneNameLabel").innerHTML = diskOfferData.zone.aliasName;
            dojo.byId("podNameLabel").innerHTML = diskOfferData.pod.name;
            dojo.byId("clusterNameLabel").innerHTML = diskOfferData.cluster.name;
            dojo.byId("billingInfo").style.display = "block";
            dojo.query("#diskOfferingPage .updatable").toggleClass("non_updatable", true);
            dojo.query("#diskOfferingPage .hide_lable").toggleClass("show_lable", true);       
            dijit.byId("diskName").setValue(diskOfferData.name);
            dijit.byId("diskDescription").setValue(diskOfferData.description);           
            dojo.forEach(diskOfferData.diskOfferZoneCosts, function(zoneData) {    
                if(diskOfferData.customized == true) {
                    var currentZoneCost  = new Zone.ZoneCost({
                        id:"diskOfferCurrentZone",
                        labelName: translator.common.storageCostPerMonth,                    
                        zoneIdNode: zoneData.zone.id,
                        zoneCost : zoneData.cost,
                        runningCostPerHrLabel: translator.common.cost + " " + translator.common.perGBPerHr,
//                        costRate : translator.common.perGBPerHr,
                        calcType: "",
                        vmRunningCostLabel : translator.common.storageCostPerMonth,
                        invalidMsg : translator.common.zoneCostInvalidMsg                                            
                    });                
                    currentZoneCost.setCost(),               
                    currentZoneCost.showOnlyZoneCost();   
                    currentZoneCost.placeAt("diskZoneCost"); 
                } else {                    
                    dojo.byId("CurrentDiskSizeRef").value = diskOfferData.size;
                    var cost = (zoneData.cost * diskOfferData.size)*720
                    currentZoneCost  = new Zone.ZoneCost({
                        id:"diskOfferCurrentZone",
                        labelName: translator.common.storageCostPerMonth,
                        zoneIdNode: zoneData.zone.id,
                        runningCostPerHrLabel: translator.common.cost + "" + translator.common.perGBPerHr,
                        zoneCost : cost,
                        calcType: "sizeBase",
                        diskSize : diskOfferData.size,                        
                        vmRunningCostLabel : translator.common.costFor + " " + diskOfferData.size + translator.common.gb + "/" + translator.common.month ,  
                        invalidMsg : translator.common.zoneCostInvalidMsg                 
                    });
                    currentZoneCost.placeAt("diskZoneCost"); 
                    currentZoneCost.setCost();    
                    currentZoneCost.showOnlyZoneCost();   
                }                 
            });                      
            if(diskOfferData.minSize == ""|| diskOfferData.minSize == null || diskOfferData.minSize == "null") {
                dojo.byId("diskMinSizeLabel").innerHTML = ".......";
            } else {
                dojo.byId("diskMinSizeLabel").innerHTML = diskOfferData.minSize;  
            }                
            
            if(diskOfferData.maxSize == ""|| diskOfferData.maxSize == null || diskOfferData.maxSize == "null") {
                dojo.byId("diskMaxSizeLabel").innerHTML = ".......";
            } else {
                dojo.byId("diskMaxSizeLabel").innerHTML = diskOfferData.maxSize;  
            }        
            
            if(diskOfferData.customized == "true" || diskOfferData.customized == true) {
                dojo.byId("diskSizeHide").style.display = "none";
                dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").toggleClass("updatableContent ", true);
            } else if(diskOfferData.customized == "false" || diskOfferData.customized == false) {
                dojo.byId("diskSizeHide").style.display = "block";
                dojo.query("#diskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
            }                       
            
            if(diskOfferData.tags == "null"|| diskOfferData.tags == null || diskOfferData.tags == "" || diskOfferData.tags == " ") {
                dojo.byId("diskStorageTagsLabel").innerHTML = translator.common.message.noTag;
            } else {
                dojo.byId("diskStorageTagsLabel").innerHTML = diskOfferData.tags; 
            }
        
            if(diskOfferData.storageType == "null"|| diskOfferData.storageType == null || diskOfferData.storageType == "") {
                dojo.byId("diskStorageTypeLabel").innerHTML = ".......";
            } else {
                dojo.byId("diskStorageTypeLabel").innerHTML = diskOfferData.storageType; 
            }           

            dojo.byId("diskSizeLabel").innerHTML = diskOfferData.size + " "+translator.common.gb;        
        
            if(diskOfferData.customized == "false"|| diskOfferData.customized == false) {
               dojo.byId("customDiskLabel").innerHTML = translator.common.no; 
            } else if(diskOfferData.customized == "true"|| diskOfferData.customized == true) {
                dojo.byId("customDiskLabel").innerHTML = translator.common.yes; 
            }
            
            if(diskOfferData.isPublic == "false"|| diskOfferData.isPublic == false) {
               dojo.byId("diskOfferIsPublicLabel").innerHTML =  translator.common.no; 
               dojo.byId("diskDomainDiv").style.display = "block";
            } else if(diskOfferData.isPublic == "true"|| diskOfferData.isPublic == true) {
                dojo.byId("diskOfferIsPublicLabel").innerHTML =  translator.common.yes; 
                dojo.byId("diskDomainDiv").style.display = "none";
            }
            
            if(diskOfferData.domainId == ""|| diskOfferData.domainId == null ||  diskOfferData.domainId == "null") {
                dojo.byId("diskOfferDomainLabel").innerHTML = "..."; 
            } else { 
                dojo.byId("diskOfferDomainLabel").innerHTML = diskOfferData.domainId; 
            } 
            
            
            if(diskOfferData.qoSType == ""|| diskOfferData.qoSType == null ||  diskOfferData.qoSType == "null") {
                dojo.byId("qoSTypeLabel").innerHTML = "..."; 
            } else { 
                dojo.byId("qoSTypeLabel").innerHTML = diskOfferData.qoSType; 
            } 
            
            if(diskOfferData.qoSType === "hypervisor") {
                dojo.byId("qOSTypeHypervisorDiv").style.display = "block";
                dojo.byId("qOSTypeStorageDiv").style.display = "none";
            } else if(diskOfferData.qoSType === "storage") {
                dojo.byId("qOSTypeHypervisorDiv").style.display = "none";
                dojo.byId("qOSTypeStorageDiv").style.display = "block";
            } else {
                dojo.byId("qOSTypeHypervisorDiv").style.display = "none";
                dojo.byId("qOSTypeStorageDiv").style.display = "none";
            }
            
            if(diskOfferData.diskReadRateBPS == "" ||
                diskOfferData.diskReadRateBPS == null || diskOfferData.diskReadRateBPS == "null") {
                dojo.byId("diskOfferDiskReadRateBPSLabel").innerHTML = "...";
            } else {
                dojo.byId("diskOfferDiskReadRateBPSLabel").innerHTML = diskOfferData.diskReadRateBPS;
            }


            if(diskOfferData.diskWriteRateBPS == "" ||
                diskOfferData.diskWriteRateBPS == null || diskOfferData.diskWriteRateBPS == "null") {
                dojo.byId("diskOfferWriteRateBPSLabel").innerHTML = "...";
            } else {
                dojo.byId("diskOfferWriteRateBPSLabel").innerHTML = diskOfferData.diskWriteRateBPS;
            }


            if(diskOfferData.diskReadRateIOPS == "" ||
                diskOfferData.diskReadRateIOPS == null || diskOfferData.diskReadRateIOPS == "null") {
                dojo.byId("diskOfferDiskReadRateIOPSLabel").innerHTML = "...";
            } else {
                dojo.byId("diskOfferDiskReadRateIOPSLabel").innerHTML = diskOfferData.diskReadRateIOPS;
            }

            if(diskOfferData.diskWriteRateIOPS == "" ||
                diskOfferData.diskWriteRateIOPS == null || diskOfferData.diskWriteRateIOPS == "null") {
                dojo.byId("diskOfferWriteRateIOPSLabel").innerHTML = "...";
            } else {
                dojo.byId("diskOfferWriteRateIOPSLabel").innerHTML = diskOfferData.diskWriteRateIOPS;
            }
            
            if(diskOfferData.isCustomizedIops === true || diskOfferData.isCustomizedIops === "true") {
                dojo.byId("customizedIopsDiv").style.display = "none";
            } else {
                dojo.byId("customizedIopsDiv").style.display = "block";
            }
            
            if(diskOfferData.isCustomizedIops == "false"|| diskOfferData.isCustomizedIops == false) {
               dojo.byId("diskOfferCustomizedIopsLabel").innerHTML = translator.common.no; 
            } else if(diskOfferData.isCustomizedIops == "true"|| diskOfferData.isCustomizedIops == true) {
                dojo.byId("diskOfferCustomizedIopsLabel").innerHTML = translator.common.yes; 
            }
            
            if(diskOfferData.minIOPS == "" ||
                diskOfferData.minIOPS == null || diskOfferData.minIOPS == "null") {
                dojo.byId("minIOPSLabel").innerHTML = "...";
            } else {
                dojo.byId("minIOPSLabel").innerHTML = diskOfferData.minIOPS;
            }
            
            if(diskOfferData.maxIOPS == "" ||
                diskOfferData.maxIOPS == null || diskOfferData.maxIOPS == "null") {
                dojo.byId("maxIOPSLabel").innerHTML = "...";
            } else {
                dojo.byId("maxIOPSLabel").innerHTML = diskOfferData.maxIOPS;
            }
        });      
        dijit.byId('diskAddButton').set('style', {display: 'none'});
        dijit.byId('diskUpdateButton').set('style', {display: 'block', "float": 'left'});
    },
    update: function() {
        var status = AddDiskOfferInfo.authetication();      
        if(status == true) {          
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
                        cost: el.getZoneCostValue(),
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