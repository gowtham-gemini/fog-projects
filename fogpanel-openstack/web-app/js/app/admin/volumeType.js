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
    "dijit/Menu", 
    "dijit/MenuItem", 
    "dijit/form/ComboButton", 
    "dijit/form/DropDownButton", 
    "dijit/DropDownMenu", 
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
    "dijit/Dialog",
    "dojox/form/FileInput",
    "dojox/form/FileInputAuto",
    "dojox/form/Uploader"
    ],
    function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, Menu, MenuItem, ComboButton, DropDownButton, DropDownMenu, registry, FilteringSelect, Select,
    ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {              
        window.translator = translator;
        window.query = query;
        window.domClass = domClass;
        window.domConstruct = domConstruct;
        window.Menu = Menu;
        window.MenuItem = MenuItem;
        window.DropDownButton = DropDownButton;
        window.DropDownMenu = DropDownMenu;
        window.ComboButton = ComboButton;
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
            name: "volumeType",
            module: "admin",
            filePath: "/js/app/admin/volumeType.js",
            layout: {
                name: "volumeType",
                containerId: "content"
            },	
            scaffold: false
        },
        {           
            "index" : action(function() {
                
            }),
            "list": action(function() {  
                var form = dijit.byId("volumeTypeListForm");
                if(form) {
                    form.destroyRecursive();
                }  
                if(dijit.byId("deleteImageDialog")) {
                    dijit.byId("deleteImageDialog").destroyRecursive();
                }  
                if(dijit.byId("pullImageConfirm")) {
                    dijit.byId("pullImageConfirm").destroyRecursive();
                }
                if(dijit.byId("deleteImageLoader")) {
                    dijit.byId("deleteImageLoader").destroyRecursive();
                }
                if(dijit.byId("pullImageButton")) {
                    dijit.byId("pullImageButton").destroyRecursive();
                }
                if(dijit.byId("deleteVolumeTypeDialog")) {
                    dijit.byId("deleteVolumeTypeDialog").destroyRecursive();
                }                
                if(dijit.byId("disableVolumeTypeDialog")) {
                    dijit.byId("disableVolumeTypeDialog").destroyRecursive();
                }      
                if(dijit.byId("deleteVolumeTypeLoader")) {
                    dijit.byId("deleteVolumeTypeLoader").destroyRecursive();
                } 
                
                core.ui.loadTemplate("volumeTypeList", core.ui.getContentId()); 
                VolumeTypeList.init();
//                VolumeTypeList.count();
            }),
            "add" : action(function() {
                if (dijit.byId("adminVolumeTypeContentForm")) {
                    dijit.byId("adminVolumeTypeContentForm").destroyRecursive();
                }                    
                if (dijit.byId("pullVolumeTypePlanLoader")) {
                    dijit.byId("pullVolumeTypePlanLoader").destroyRecursive();
                }
                if (dijit.byId("volumeTypeAddConformationDialog")) {
                    dijit.byId("volumeTypeAddConformationDialog").destroyRecursive();
                }
                if (dijit.byId("volumeTypeUpdateConformationDialog")) {
                    dijit.byId("volumeTypeUpdateConformationDialog").destroyRecursive();
                } 
                if (dijit.byId("addVolumeTypeLoader")) {
                    dijit.byId("addVolumeTypeLoader").destroyRecursive();
                }
                core.ui.loadTemplate("addVolumeType", core.ui.getContentId());
                AddVolumeTypeInfo.init();
            }),
            "edit" : action(function(currentVTId) {
                if (dijit.byId("adminVolumeTypeContentForm")) {
                    dijit.byId("adminVolumeTypeContentForm").destroyRecursive();
                }                    
                if (dijit.byId("pullVolumeTypePlanLoader")) {
                    dijit.byId("pullVolumeTypePlanLoader").destroyRecursive();
                }
                if (dijit.byId("volumeTypeAddConformationDialog")) {
                    dijit.byId("volumeTypeAddConformationDialog").destroyRecursive();
                }
                if (dijit.byId("volumeTypeUpdateConformationDialog")) {
                    dijit.byId("volumeTypeUpdateConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("addVolumeType", core.ui.getContentId());
                UpdateVolumeTypeInfo.init(currentVTId);
            })
        });
    });
    
    var UpdateVolumeTypeInfo = {
        cancelConfirm : function () {
            dijit.byId("volumeTypeAddConformationDialog").hide();
            dijit.byId("volumeTypeUpdateConformationDialog").hide();            
        },
        init : function (currentVTId) {  
            
            var zoneRestStore = new JsonRest({
                target: core.getContextPath()+"/api/zone"
            });  
        
            zoneRestStore.query().then(function(data) {               
               if(data.length === 0 || data === undefined || data === "undefined") {                                      
                   registry.byId("appToaster").setContent(translator.common.instance.volumeTypeSetupNeeded, 'warning');
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/dashboard");
                } 
            }); 
            
            dijit.byId("volumeTypeName").set("disabled", true);
            dojo.query("#volueTypeUpdateButtonDiv").removeClass("hide_text", true);
            dojo.query("#volueTypeAddButtonDiv").toggleClass("hide_text", true); 
            var volumeTypeRestStore = new JsonRest({
                target: core.getContextPath() + "/api/volumetypes/"
            });
            var availabilityZoneRestStore = new JsonRest({
                target: core.getContextPath() + "/api/zone/"
            });
            
            dojo.query("#volumeTypeZoneLoader").removeClass("hide_text", true);
            dojo.query("#currentVolumeTypeZoneCost").toggleClass("hide_text", true);
            dojo.query("#volumeTypePriceHeadLabel").toggleClass("hide_text", true);
            
            volumeTypeRestStore.get(currentVTId).then(function (volumeTypeData) {
                dojo.byId("currentVolumeTypeId").value=volumeTypeData[0].referenceId;
                dijit.byId("volumeTypeName").setValue(volumeTypeData[0].name);
                dojo.byId("volumeTypeBreadcrumName").innerHTML = volumeTypeData[0].name;
                availabilityZoneRestStore.query().then(function(data) {    
                    dojo.forEach(data, function (el, index) {
                        var zoneCost = 0.0;
                        dojo.forEach(volumeTypeData[0].volumeTypeZoneCosts, function (volumeZoneCost) {                            
                            if(el.id === volumeZoneCost.zone.id) {                                
                                zoneCost = (volumeZoneCost.cost)*720;
                            } 
                        });
                        if(dijit.byId("disk_zone_widget_"+el.name+"_"+index)) {
                            dijit.byId("disk_zone_widget_"+el.name+"_"+index).destroyRecursive();
                        }
                        var currentZonePrice  = new FogPanel.ZonePrice({
                            id:"disk_zone_widget_"+el.name+"_"+index,
                            zoneName: el.name,
                            zoneIdNode: el.id,
                            zoneCost : zoneCost
                        });
                        currentZonePrice.placeAt("currentVolumeTypeZoneCost");  
                        currentZonePrice.showOnlyZoneCost();
                        currentZonePrice.setCost(); 
                        dojo.query("#volumeTypeZoneLoader").toggleClass("hide_text", true);
                        dojo.query("#currentVolumeTypeZoneCost").removeClass("hide_text", true);
                        dojo.query("#volumeTypePriceHeadLabel").removeClass("hide_text", true);
                    });
                });                
            });                                                            
        },
        updateConfirm : function () {
            var status = AddVolumeTypeInfo.authetication();                
            var zoneCostStat = true;  
            var zoneNode = dojo.byId("currentVolumeTypeZoneCost");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
            dojo.forEach(zoneWidgets, function(el) {                       
                var stat = el.showError();
                if(stat === false) {
                    zoneCostStat = false;
                }
            });         
            if(status === true && zoneCostStat === true) {
                dijit.byId("volumeTypeUpdateConformationDialog").show();         
            }
        },
        update : function () {
            var status = AddVolumeTypeInfo.authetication();                
            var zoneCostStat = true;  
            var zoneNode = dojo.byId("currentVolumeTypeZoneCost");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
            dojo.forEach(zoneWidgets, function(el) {                       
                var stat = el.showError();
                if(stat === false) {
                    zoneCostStat = false;
                }
            });         
            if(status === true && zoneCostStat === true) {
                dojo.query("#volumeTypeLoader").removeClass("hide_text", true);
                dojo.query("#volueTypeAddButtonDiv").toggleClass("hide_text", true);
                dojo.query("#volueTypeCancelButtonDiv").toggleClass("hide_text", true);

                var diskZoneCosts = [];
                dijit.byId("volueTypeUpdateButton").set("disabled", true);                

                var diskZoneNode = dojo.byId("currentVolumeTypeZoneCost");
                var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
                dojo.forEach(diskZoneWidgets, function(el) {                     
                    diskZoneCosts.push ({
                        zoneId: el.getZoneId(), 
                        cost: el.getZoneCost()         
                    });                                                                        
                });

                for (var index = 0; index < diskZoneCosts.length-diskZoneWidgets.length; index++) {
                    diskZoneCosts.splice(index, diskZoneWidgets.length);
                }                                
                var volumeTypeRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/volumetypes/"
                });    
                volumeTypeRestStore.put({                                                 
                    zoneCosts: diskZoneCosts,
                    id: dojo.byId("currentVolumeTypeId").value
                }).then(function (data) {   
                    dijit.byId("volueTypeUpdateButton").set("disabled", false);
                    dijit.byId("volumeTypeUpdateConformationDialog").hide(); 
                    dojo.query("#volumeTypeLoader").toggleClass("hide_text", true);
                    dojo.query("#volueTypeUpdateButtonDiv").removeClass("hide_text", true);
                    dojo.query("#volueTypeCancelButtonDiv").removeClass("hide_text", true);                
                    if(data[0].result == "OK") {                                                                    
                        registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("appToaster").show();                                                                 
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                        registry.byId("appToaster").show();
                     }
                });             
            }            
        }
    };
    
    var AddVolumeTypeInfo = {
        init : function () {
            
            var zoneRestStore = new JsonRest({
                target: core.getContextPath()+"/api/zone"
            });  
        
            zoneRestStore.query().then(function(data) {
               if(data.length === 0 || data === undefined || data === "undefined") {                                      
                   registry.byId("appToaster").setContent(translator.common.zoneConfigMissingMsg, 'warning');
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/settings/importData");
                } else {
                    dojo.byId("volumeTypeBreadcrumName").innerHTML = translator.common.add;
                    dijit.byId("volumeTypeName").set("disabled", false);
                    dojo.query("#volumeTypeZoneLoader").removeClass("hide_text", true);
                    dojo.query("#currentVolumeTypeZoneCost").toggleClass("hide_text", true);
                    dojo.query("#volumeTypePriceHeadLabel").toggleClass("hide_text", true);
                    dojo.query("#volueTypeUpdateButtonDiv").toggleClass("hide_text", true);
                    dojo.query("#volueTypeAddButtonDiv").removeClass("hide_text", true);            
                    var availabilityZoneRestStore = new JsonRest({
                        target: core.getContextPath() + "/api/zone/"
                    });
                    availabilityZoneRestStore.query().then(function(data) {    
                        dojo.forEach(data, function (el, index) {
                            if(dijit.byId("disk_zone_widget_"+el.name+"_"+index)) {
                                dijit.byId("disk_zone_widget_"+el.name+"_"+index).destroyRecursive();
                            }
                            var currentZonePrice  = new FogPanel.ZonePrice({
                                id:"disk_zone_widget_"+el.name+"_"+index,
                                zoneName: el.name,
                                zoneIdNode: el.id                                
                            });
                            currentZonePrice.placeAt("currentVolumeTypeZoneCost");  
                            currentZonePrice.showOnlyZoneCost();
                            dojo.query("#volumeTypeZoneLoader").toggleClass("hide_text", true);
                            dojo.query("#currentVolumeTypeZoneCost").removeClass("hide_text", true);
                            dojo.query("#volumeTypePriceHeadLabel").removeClass("hide_text", true);
                        });
                    });
                }
            });                                                                     
        },
        authetication : function() {
            var status = true;
            var pageNode = dojo.byId("adminVolumeTypeContentForm");
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
        cancel : function () {
            core.router.go("#/admin/volumeType/list");
        },
        addConfirm : function () {
            var status = AddVolumeTypeInfo.authetication();                
            var zoneCostStat = true;  
            var zoneNode = dojo.byId("currentVolumeTypeZoneCost");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        
            dojo.forEach(zoneWidgets, function(el) {                       
                var stat = el.showError();
                if(stat === false) {
                    zoneCostStat = false;
                }
            });         
            if(status === true && zoneCostStat === true) {
                dijit.byId("volumeTypeAddConformationDialog").show();         
            }
        },
        add : function () {
            var status = AddVolumeTypeInfo.authetication();                
            var zoneCostStat = true;  
            var zoneNode = dojo.byId("currentVolumeTypeZoneCost");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);
            
            dojo.forEach(zoneWidgets, function(el) {                       
                var stat = el.showError();
                if(stat === false) {
                    zoneCostStat = false;
                }
            }); 

        
        if(status === true && zoneCostStat === true) {
            dojo.query("#volumeTypeLoader").removeClass("hide_text", true);
            dojo.query("#volueTypeAddButtonDiv").toggleClass("hide_text", true);
            dojo.query("#volueTypeCancelButtonDiv").toggleClass("hide_text", true);
            
            var diskZoneCosts = [];
            dijit.byId("volueTypeAddButton").set("disabled", true);
            var name = dijit.byId("volumeTypeName");
            
            var diskZoneNode = dojo.byId("currentVolumeTypeZoneCost");
            var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
            dojo.forEach(diskZoneWidgets, function(el) {                     
                diskZoneCosts.push ({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCost()         
                });                                                                        
            });
            
            for (var index = 0; index < diskZoneCosts.length-diskZoneWidgets.length; index++) {
                diskZoneCosts.splice(index, diskZoneWidgets.length);
            }                                
            var volumeTypeRestStore = new JsonRest({
                target: core.getContextPath()+"/api/volumetypes/"
            });  
            dijit.byId("volumeTypeAddConformationDialog").hide();      
            
            volumeTypeRestStore.add({
                name: name.value,                                
                zoneCosts: diskZoneCosts                
            }).then(function (data) {   
                dijit.byId("volueTypeAddButton").set("disabled", false);                   
                dojo.query("#volumeTypeLoader").toggleClass("hide_text", true);
                dojo.query("#volueTypeAddButtonDiv").removeClass("hide_text", true);
                dojo.query("#volueTypeCancelButtonDiv").removeClass("hide_text", true);                
                if(data[0].result == "OK") {                    
                    var diskZoneNode = dojo.byId("currentVolumeTypeZoneCost");
                    var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
                    dojo.forEach(diskZoneWidgets, function(el) {
                        el.clearWidgets();
                    });            
                    registry.byId("appToaster").setContent(translator.common.message.addedSuccess, "message");
                    registry.byId("appToaster").show();    
                    dijit.byId("volumeTypeName").set("value", "");
                    dijit.byId("adminVolumeTypeContentForm").reset();                   
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.cannotBeAdded, "error");
                    registry.byId("appToaster").show();
                 }
                 core.router.go("#/admin/volumeType/list");
            });             
        }    
        },
        gotoList: function() {
            
        },
    };
    var VolumeTypeList = {
        init : function () {            
            var zoneRestStore = new JsonRest({
                target: core.getContextPath()+"/api/zone"
            });  
            zoneRestStore.query().then(function (zoneData) {
               if (zoneData.length === 0 || zoneData === undefined || zoneData === "undefined") {                
                   registry.byId("appToaster").setContent(translator.common.zoneConfigMissingMsg, "error");
                   registry.byId("appToaster").show();
                   core.router.go("#/admin/settings/importData");
               } else {
                   var openStackConfigCheck = new JsonRest({
                       target: core.getContextPath() + "/api/config/isOpenStackConfigured"
                   })                           
                   openStackConfigCheck.query().then(function(data) {            
                       if(data === false) {                            
                           dojo.byId("adminVolumeTypePullDiv").style.display = "none";
                           dojo.byId("openStackNotConfiguredMsgVolumeType").style.display = "block";
                       } else {
                           
                            //volume type count
                            var countRest = new JsonRest({
                                target: core.getContextPath() + "/api/volumetypes/count"
                            });
                            countRest.query().then(function(data) {
                               dojo.forEach(data, function(resultData) {
                                  dojo.byId("adminTotalVolumeType").innerHTML =  resultData.totalVolumeType;
                                  dojo.byId("adminEnabledVolumeType").innerHTML =  resultData.enabledVolumeType;
                                  dojo.byId("adminDisabledVolumeType").innerHTML =  resultData.disabledVolumeType;
                               });
                            });

                            //volume type grid
                            VolumeTypeList.populateValues();
                        }
                    });
                }
            });        
        },
        gotoList: function() {
            dijit.byId("deleteVolumeTypeLoader").hide();
        },
        deleteVolumeTypeDialogAlert : function (currentVolumeTypeId) {
            dojo.byId("currentVolumeTypeDeleteId").value = currentVolumeTypeId;
            dijit.byId("deleteVolumeTypeDialog").show();
        },
        confirmDelete : function () {           
            var volumeTypeRestStore = new JsonRest({
                target: core.getContextPath()+"/api/volumetypes/delete"
            });    
            dijit.byId("deleteVolumeTypeDialog").hide();
            dijit.byId("deleteVolumeTypeLoader").show();
            volumeTypeRestStore.add({                                       
                volumeTypeId: dojo.byId("currentVolumeTypeDeleteId").value
            }).then(function (data) {     

                if(data[0].result == "OK") {                                                  
                    registry.byId("appToaster").setContent(translator.common.message.volumeTypeDeleted, "message");
                    registry.byId("appToaster").show();    
                    VolumeTypeList.populateValues()                  
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.cannotDeleteVolumeType, "error");
                    registry.byId("appToaster").show();
                 }
                 dijit.byId("deleteVolumeTypeLoader").hide();
            });
        },
        closeDeleteVolumeTypeDialog : function () {
            dijit.byId("deleteVolumeTypeDialog").hide();
        },
        closeDialogue : function () {
            dijit.byId("deleteVolumeTypeDialog").hide();
            dijit.byId("disableVolumeTypeDialog").hide();
        },
        changeVolumeTypeDialog : function (currentVTId, status) {
            dojo.byId("currentVolumeTypeStatUpdateId").value = currentVTId;
            dojo.byId("currentVolumeTypeStatus").value = status;
            
            if(status === "enable") {
                dojo.byId("volumeTypeAvailableConfimMsg").innerHTML = translator.common.message.vtEnableMsg;
            } else if(status === "disable") {
                dojo.byId("volumeTypeAvailableConfimMsg").innerHTML = translator.common.message.vtDisableMsg;
            }
            dijit.byId("disableVolumeTypeDialog").show();
        },
        volumeTypeAvailableAction : function () {
            var volumeTypeRestStore = new JsonRest({
                target: core.getContextPath()+"/api/volumetypes/status"
            });  
            var successMessage = "";
            var errorMessage = "";
            var status = "";
            if(dojo.byId("currentVolumeTypeStatus").value === "enable") {
                successMessage = translator.common.message.vtEnabledSuccessMsg;
                errorMessage = translator.common.message.vtEnabledErrorMsg;                     
            } else if(dojo.byId("currentVolumeTypeStatus").value === "disable") {
                successMessage = translator.common.message.vtDisabledSuccessMsg;
                errorMessage = translator.common.message.vtDisableErrorMsg;                  
            }
            volumeTypeRestStore.add({                                       
                volumeTypeId: dojo.byId("currentVolumeTypeStatUpdateId").value,
                status : dojo.byId("currentVolumeTypeStatus").value
            }).then(function (data) {    
                dijit.byId("disableVolumeTypeDialog").hide();
                if(data[0].result === "OK") {                                                  
                    registry.byId("appToaster").setContent(successMessage, "message");
                    registry.byId("appToaster").show();    
                    VolumeTypeList.populateValues()    
                    VolumeTypeList.init();
                } else {
                    registry.byId("appToaster").setContent(errorMessage, "error");
                    registry.byId("appToaster").show();
                 }
            });
        },
        populateValues : function() {
            dojo.byId("adminVolumeTypeList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        
            var gridData = {
                items: []
            };  
            var dataList = new ItemFileWriteStore({data: gridData}); 
            var dataLayout = [[                
                {'name': translator.common.name, 'field': 'name', 'width': '300px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {
                        var editTag = "<a href='#/admin/volumeType/edit/"+data.referenceId+"' title='"+translator.common.edit+"'>"+data.name+"</a>";
                        return editTag;
                }}, 
                {'name': translator.common.enabled, 'field': 'enabled', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
                        var stat = "";
                        if(data.isDisabled === false) {
                            stat = translator.common.yes;                            
                        } else {
                            stat = translator.common.no;
                        }
                        return stat;
                }},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html;
                        if(data.referenceId === "default") {
                            html = "<a href='#/admin/volumeType/edit/"+data.referenceId+"' title='"+translator.common.edit+"'><i class='icon-edit'></i></a>"; 
                        } else {
                            if(data.isDisabled === true || data.isDisabled === "true") {
                                html = "<a href='#/admin/volumeType/edit/"+data.referenceId+"' title='"+translator.common.edit+"'><i class='icon-edit'></i></a>" + 
                                        "<a class='offset1' title='"+translator.common.enable+"' onclick='VolumeTypeList.changeVolumeTypeDialog(\"" + data.referenceId + "\", \"enable\")'><i class='icon-eye-open'></i></a></li>" +                                     
                                        "<a onclick='VolumeTypeList.deleteVolumeTypeDialogAlert(\"" + data.referenceId + "\")' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
                            } else if(data.isDisabled === false || data.isDisabled === "false") {
                                html = "<a href='#/admin/volumeType/edit/"+data.referenceId+"' title='"+translator.common.edit+"'><i class='icon-edit'></i></a>" +                                      
                                    "<a class='offset1' title='"+translator.common.disable+"' onclick='VolumeTypeList.changeVolumeTypeDialog(\"" + data.referenceId + "\", \"disable\")'><i class='icon-eye-close'></i></a></li>"+
                                    "<a onclick='VolumeTypeList.deleteVolumeTypeDialogAlert(\"" + data.referenceId + "\")' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
                        }
                    }                        
                    return html;   
                                                
                    }, 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];    

                var volumeTypeRestStore = new JsonRest({                        
                    target: core.getContextPath()+"/api/volumetypes/"
                }); 

                volumeTypeRestStore.query().then(function(data) {
                    if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                        dojo.byId("adminVolumeTypeList").innerHTML = "";
                        dojo.byId("noVolumeTypeMessageBox").style.display = "block";

    //                    dojo.byId("adminTotalFlavor").innerHTML = 0;
    //                    dojo.byId("adminEnabledFlavor").innerHTML = 0;
    //                    dojo.byId("adminDisabledFlavor").innerHTML = 0;
                    } else {

                        dojo.byId("noVolumeTypeMessageBox").style.display = "none";
                        dojo.forEach(data, function(resultData) {                 
                            dataList.newItem({                                
                                name:resultData,
                                enabled:resultData,                                
                                action: resultData
                            });
                        });                                    
                        dojo.byId("adminVolumeTypeList").innerHTML = "";
                        var dataGrid = new EnhancedGrid({                
                            "class" : "span12",
                            store: dataList,
                            structure: dataLayout,
                            autoHeight: true,
                            plugins: core.getGridConfig().plugins
                        });
                        dataGrid.placeAt("adminVolumeTypeList");
                        dataGrid.startup();  
                        dataGrid.update();    
                    }             
                }); 
            }
    };
    
    
window.VolumeTypeList = VolumeTypeList;
window.AddVolumeTypeInfo = AddVolumeTypeInfo;
window.UpdateVolumeTypeInfo=UpdateVolumeTypeInfo;