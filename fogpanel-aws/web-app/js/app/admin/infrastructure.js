'use strict';
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
    "FogPanel/InstanceStatus",
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
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select, ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {
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
            controller({
                name: "infrastructure",
                module: "admin",
                filePath: "/js/app/admin/infrastructure.js",
                layout: {
                    name: "infrastructure",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "index": action(function() {
                    
                    if (dijit.byId("volumesPane")) {
                        dijit.byId("volumesPane").destroyRecursive();
                    } 
                    if (dijit.byId("adminVolumeDeleteDialog")) {
                        dijit.byId("adminVolumeDeleteDialog").destroyRecursive();
                    } 
                    if (dijit.byId("adminVolumeListForm")) {
                        dijit.byId("adminVolumeListForm").destroyRecursive();
                    }
                    if (dijit.byId("adminVolumeActionLoader")) {
                        dijit.byId("adminVolumeActionLoader").destroyRecursive();
                    } 
                    if (dijit.byId("infrastructureTabContainer")) {
                        dijit.byId("infrastructureTabContainer").destroyRecursive();
                    } 
                    if (dijit.byId("infraVolumeTab")) {
                        dijit.byId("infraVolumeTab").destroyRecursive();
                    } 
//                    var form = dijit.byId("adminVmActionForm");
//                    if (form) {
//                        form.destroyRecursive();
//                        dijit.byId("migrateVMDialog").destroyRecursive();
//                        dijit.byId("migrateVMLoader").destroyRecursive();
//                    }
                    core.ui.loadTemplate("infrastructurePage", core.ui.getContentId());
                    Instance.init();
//                    InstancesInfo.init();
//                    InstancesInfo.populateValues();
                }),
                "cloud": action(function() {
                    core.ui.loadTemplate("cloud", core.ui.getContentId());
                }),
                "instance": action(function() {
                    var form = dijit.byId("adminVmActionForm");
                    if (form) {
                        form.destroyRecursive();
//                        dijit.byId("migrateVMDialog").destroyRecursive();
//                        dijit.byId("migrateVMLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("infrastructurePage", core.ui.getContentId());
//                    InstancesInfo.init();
//                    InstancesInfo.populateValues();
                }),
                "volumes": action(function() {
                    
                    if (dijit.byId("volumesPane")) {
                        dijit.byId("volumesPane").destroyRecursive();
                    } 
                    if (dijit.byId("adminVolumeDeleteDialog")) {
                        dijit.byId("adminVolumeDeleteDialog").destroyRecursive();
                    } 
                    if (dijit.byId("adminVolumeListForm")) {
                        dijit.byId("adminVolumeListForm").destroyRecursive();
                    }
                    if (dijit.byId("adminVolumeActionLoader")) {
                        dijit.byId("adminVolumeActionLoader").destroyRecursive();
                    } 
                    if (dijit.byId("infrastructureTabContainer")) {
                        dijit.byId("infrastructureTabContainer").destroyRecursive();
                    } 
                    if (dijit.byId("infraVolumeTab")) {
                        dijit.byId("infraVolumeTab").destroyRecursive();
                    }
                    
                    core.ui.loadTemplate("infrastructurePage", core.ui.getContentId());
                    
                    setTimeout(function () {
                        var mainTab = dijit.byId("infrastructureTabContainer"); //Tr
                        var subTab = dijit.byId("infraVolumeTab"); //tab Id which you want to show
                        mainTab.selectChild(subTab);   
                    },100); 
                }),
                "volumeDetails": action(function(id) {
                    if (dijit.byId("adminPageVolumeDeleteDialog")) {
                        dijit.byId("adminPageVolumeDeleteDialog").destroyRecursive();
                    } 
                    if (dijit.byId("adminVolumePageActionLoader")) {
                        dijit.byId("adminVolumePageActionLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("adminVolumeDetails", core.ui.getContentId());
                    ListVolume.view(id);
                }),
            });
        });
var Instance = {
   'init': function() {
        
       var instanceCountRest = new JsonRest({
           target: core.getContextPath() + "/api/virtualMachine/count"
       });
       
       instanceCountRest.query().then(function(data) {
          dojo.forEach(data, function(resultData) {
             dojo.byId("adminTotalVM").innerHTML = resultData.totalInstance;
             dojo.byId("adminRunningVM").innerHTML = resultData.runningInstance;
             dojo.byId("adminStoppedVM").innerHTML = resultData.stoppedInstance;
          });
       });
       
       Instance.populateValues();
    },  
    'populateValues': function() {
        
    },
};
var ListVolume = {
    
    'init': function() {
       var volumeCountRest = new JsonRest({
           target : core.getContextPath()+ "/api/volume/count"
       });
       
       volumeCountRest.query().then(function(data){
           dojo.forEach(data,function (volumeCount){
               dojo.byId("adminTotalVolumes").innerHTML = volumeCount.totalVolumes;
               dojo.byId("adminAttachedVolumes").innerHTML = volumeCount.attachedVolumes;
               dojo.byId("adminDetachedVolumes").innerHTML = volumeCount.detachedVolumes;
           })
       });
       ListVolume.populateValues();
    },
    
    'populateValues': function() {
        
        if (dijit.byId("adminVolumeGridWidget")) {
            dijit.byId("adminVolumeGridWidget").destroyRecursive();
        }

        dojo.byId("adminVolumesList").innerHTML = "<img src='images/vmload.gif' alt=" + translator.common.loader.imageLoadError + " height='36' width='100'/> </br> <p>" + translator.user.loader.volumeLoading + "</p>";
        var gridData = {
            items: []
        };
        
        dojo.byId("noVolumeAdminMessageBox").style.display = "none";

        var gridDataList = new ItemFileWriteStore({data: gridData});

        var gridLayout = [
            [
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.account.name, 'field': 'project', 'width': '150px', datatype: "string", autoComplete: true},
                {'name': translator.common.name, 'field': 'name', 'width': '100px', datatype: "string", autoComplete: true,'formatter': function(data) {
                    return "<a href = '#/admin/infrastructure/volumeDetails/"+ data.referenceId+"' title='" + translator.common.view + "'>" + data.name + "</a>";
                }},               
                {'name': translator.user.grid.storage.layout.size, 'field': 'size', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.status, 'field': 'status', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.volumeType, 'field': 'volumeType', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.attachedTo, 'field': 'attachedTo', 'width': '100px', datatype: "string", autoComplete: true,'formatter': function(data) {
                    
                    var attachTo = data.instanceName != null ? data.instanceName : "";
//                    return "<a href='#/user/server/viewServer/" + data.instanceReferenceId + "' title='" + translator.common.view + "'>" + attachTo + "</a>";
                    return attachTo;
                }},
                {'name': translator.user.grid.storage.layout.availabilityZone, 'field': 'availabilityZone', 'width': '150px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.action, 'field': 'action', 'formatter': function(data, rowIndex) {

                        var menu = new DropDownMenu({style: "display: none;"});
                        
//                        var menuItem1 = new MenuItem({
//                            label: "Delete",
//                            onClick: function() {
//                                ListVolume.getDeleteConformation(data);
//                            }
//                        });
//                        menu.addChild(menuItem1);
//                        menu.startup();
                        var menuItem2 = new MenuItem({
                            label: translator.common.view,
                            onClick: function() {
                                core.router.go("#/admin/infrastructure/volumeDetails/"+ data.referenceId +"");
                            }
                        });
                        menu.addChild(menuItem2);
                        menu.startup();

                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });
                        return button;
                    }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];
        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/listForAdmin"
        });

        restStore.query().then(function(data) {
            if (data.length === 0 || data === "undefined" || data === undefined) {
                dojo.byId("noVolumeAdminMessageBox").style.display = "block";
                dojo.byId("adminVolumesList").innerHTML = "";
            } else {
                dojo.byId("noVolumeAdminMessageBox").style.display = "none";
                dojo.forEach(data, function(volumeData) {
                    gridDataList.newItem({
                        id: volumeData.referenceId,
                        project: volumeData.projectName,
                        name: volumeData,
                        description: volumeData.description,
                        size: volumeData.size,
                        status: volumeData.status,
                        volumeType: volumeData.volumeType == null ? "-" : volumeData.volumeType,
                        attachedTo: volumeData,
                        availabilityZone: volumeData.availabilityZone == null ? "-" : volumeData.availabilityZone,
                        action: volumeData
                    });
                });
                dojo.byId("adminVolumesList").innerHTML = "";
                var volumeListGrid = new EnhancedGrid({
                    id: 'adminVolumeGridWidget',
                    store: gridDataList,
                    structure: gridLayout,
//                loadingMessage: translator.user.loader.instanceLoader,
//                noDataMessageL:translator.user.grid.instance.noInstance,
                    autoHeight: true,
                    autoWidth: false,
                    class: "span12",
                    style: "overflow: hidden",
                    plugins: core.getGridConfig().plugins
                });
                volumeListGrid.placeAt("adminVolumesList");
                volumeListGrid.startup();
            }
        });

    },
    view: function(id) {
      
      var volumeDetailsRest =  new JsonRest ({
         target : core.getContextPath()+ "/api/volume/listForAdmin" 
      });
      
      volumeDetailsRest.query({referenceId: id}).then(function (data){
          dojo.forEach(data, function (resultData){
             dojo.byId("currentVolumeId").value = resultData.referenceId;
             dojo.byId("currentVolumeUserUuid").value = resultData.user.uuid;
             dojo.byId("currentVolumeUserPassword").value = resultData.user.password;
             dojo.byId("volumeNameInfo").innerHTML =  resultData.name;
             dojo.byId("volumeIdInfo").innerHTML =  resultData.referenceId;
             dojo.byId("volumeProjectInfo").innerHTML =  resultData.projectName;
             dojo.byId("volumeZoneInfo").innerHTML =  resultData.availabilityZone == null ? "-" : resultData.availabilityZone;
             dojo.byId("volumeTypeInfo").innerHTML =  resultData.volumeType == null ? "-" : resultData.volumeType;
             dojo.byId("volumeStatusInfo").innerHTML =  resultData.status;
             dojo.byId("volumeSizeInfo").innerHTML =  resultData.size;
             dojo.byId("volumeCreatedInfo").innerHTML =  resultData.createdOn;
             dojo.byId("volumeAttachedInfo").innerHTML =  resultData.instanceName == null ? "-" : resultData.instanceName;
          });
      });
        
    },
    getDeleteConformation: function(data) {
        dojo.byId("currentVolumeId").value = data.referenceId;
        dojo.byId("currentVolumeUserUuid").value = data.user.uuid;
        dojo.byId("currentVolumeUserPassword").value = data.user.password;
        dijit.byId("adminVolumeDeleteDialog").show();
    },
    delete: function() {
        dijit.byId("adminVolumeDeleteDialog").hide();
        dijit.byId("adminVolumeActionLoader").show();
        var volumeId = dojo.byId("currentVolumeId").value;
        var userUuid = dojo.byId("currentVolumeUserUuid").value;
        var userPassword = dojo.byId("currentVolumeUserPassword").value;

        var deleteRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/deleteVolumeByAdmin"
        });

        deleteRestStore.add({
            volumeId : volumeId,
            userUuid : userUuid,
            userPassword : userPassword,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {    
                if (resultData.result == "OK") {
                    dijit.byId("adminVolumeActionLoader").hide();
                    registry.byId("appToaster").setContent(translator.user.storage.volumeDeleted, "message");
                    registry.byId("appToaster").show();
                    ListVolume.populateValues();

                } else {
                    dijit.byId("adminVolumeActionLoader").hide();
                    registry.byId("appToaster").setContent(translator.user.storage.deleteVolumeError, "error");
                    registry.byId("appToaster").show();
                    
                }
            });
        });
        
    },
    closeDeleteDialog: function() {
        dijit.byId("adminVolumeDeleteDialog").hide();
    },
    gotoList: function() {
       dijit.byId("adminVolumeActionLoader").hide(); 
    },
};
var VolumeInfo = {
    
   getDeleteConformation: function(data) {
        
        dijit.byId("adminPageVolumeDeleteDialog").show();
    },
   delete : function () {
       
       dijit.byId("adminPageVolumeDeleteDialog").hide();
        dijit.byId("adminVolumePageActionLoader").show();
        var volumeId = dojo.byId("currentVolumeId").value;
        var userUuid = dojo.byId("currentVolumeUserUuid").value;
        var userPassword = dojo.byId("currentVolumeUserPassword").value;

        var deleteRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/deleteVolumeByAdmin"
        });

        deleteRestStore.add({
            volumeId : volumeId,
            userUuid : userUuid,
            userPassword : userPassword,
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {    
                if (resultData.result == "OK") {
                    dijit.byId("adminVolumePageActionLoader").hide();
                    registry.byId("appToaster").setContent(translator.user.storage.volumeDeleted, "message");
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/infrastructure/volumes");

                } else {
                    dijit.byId("adminVolumePageActionLoader").hide();
                    registry.byId("appToaster").setContent(translator.user.storage.deleteVolumeError, "error");
                    registry.byId("appToaster").show();
                    
                }
            });
        });
    },  
    closeDeleteDialog: function() {
        dijit.byId("adminPageVolumeDeleteDialog").hide();
    },
    gotoList: function() {
        
        if(dijit.byId("adminVolumePageActionLoader")) {
            dijit.byId("adminVolumePageActionLoader").hide();
        }
       core.router.go("#/admin/infrastructure/volumes");
    },
};

