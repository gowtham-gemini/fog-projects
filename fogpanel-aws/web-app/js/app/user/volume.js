'use strict';
require([
    "dojo",
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js",
    "dijit/dijit",
    "dojo/store/JsonRest",
    "dijit/registry",
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dijit/form/HorizontalSlider",
    "dojo/dom-construct",
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/Tufte",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojo/_base/connect",
    "dijit/Menu",
    "dijit/MenuItem",
    "dijit/form/ComboButton",
    "dijit/form/DropDownButton",
    "dijit/DropDownMenu",
    "dijit/Tooltip",
    "dojox/charting/plot2d/Markers",
    "dijit/form/HorizontalRule",
    "dijit/form/HorizontalRuleLabels",
    "dijit/form/HorizontalSlider",
    "dojo/query",
    "dojo/dom-class",
    "dijit/layout/TabContainer",
    "dojox/charting/widget/Chart2D",
    "dojox/charting/themes/Claro",
    "dijit/form/Button",
    "dijit/layout/ContentPane",
    "dojox/form/PasswordValidator",
    "dojo/on",
    "dojo/query",
    "dojox/validate/regexp",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "FogPanel/VolumeListItem",
    "FogPanel/InstanceStatus",
    "FogPanel/StorageAction",
    "dijit/TitlePane",
    "Zone/ZoneCost",
    "dojox/widget/rotator/Slide",
    "dojox/widget/Rotator",
    "dojox/widget/rotator/Pan",
    "FogPanel/WizardListItem",
    "dijit/form/Form",
    "dojox/validate/regexp",
    "dijit/form/ValidationTextBox",
    "dijit/form/CheckBox",
    "dijit/form/NumberSpinner",
    "dijit/Dialog",
    "dijit/layout/ContentPane",
    "List/ListItem",
], function(dojo, i18n, translator, dijit, JsonRest, registry, FilteringSelect, Select, ItemFileWriteStore, DataGrid,
        EnhancedGrid, HorizontalSlider, domConstruct, Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot,
        Highlight, connect, Menu, MenuItem, ComboButton, DropDownButton, DropDownMenu) {
    window.translator = translator;
    window.JsonRest = JsonRest;
    window.Magnify = Magnify;
    window.registry = registry;
    window.Menu = Menu;
    window.MenuItem = MenuItem;
    window.DropDownButton = DropDownButton;
    window.DropDownMenu = DropDownMenu;
    window.ComboButton = ComboButton;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Select = Select;
    window.DataGrid = DataGrid;
    window.domConstruct = domConstruct;
    window.domConstruct = domConstruct;
    window.Lines = Lines;
    window.HorizontalSlider = HorizontalSlider;
    window.Chart = Chart;
    window.Pie = Pie;
    window.PlotKitGreen = PlotKitGreen;
    window.Tooltip = Tooltip;
    window.MoveSlice = MoveSlice;
    window.theme = theme;
    window.ColumnsPlot = ColumnsPlot;
    window.Highlight = Highlight;
    window.connect = connect;
    window.currentRuleId = "";
    window.compCount = 0;
    window.createVMCurrentZone = "";
    window.zoneTempRefId = "";
    window.isTierOptionEnabled = false;
    controller({
        name: "volume",
        module: "user",
        filePath: "/js/app/user/volume.js",
        layout: {
            name: "",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "list": action(function() {

            if (dijit.byId("deleteDialog")) {
                dijit.byId("deleteDialog").destroyRecursive();
            }
            if (dijit.byId("volumeActionLoader")) {
                dijit.byId("volumeActionLoader").destroyRecursive();
            }
            if (dijit.byId("volumeListForm")) {
                dijit.byId("volumeListForm").destroyRecursive();
            }
            if(dijit.byId("attachToInstanceDialog")) {
               dijit.byId("attachToInstanceDialog").destroyRecursive();                                
            }
            if(dijit.byId("detachVolumeAlertDialog")) {
               dijit.byId("detachVolumeAlertDialog").destroyRecursive();                                
            }
            core.ui.loadTemplate("userVolumeList", core.ui.getContentId());
            ListVolume.populateValues();

        }),
        "add": action(function(id) {

            if (dijit.byId("volumeForm")) {
                dijit.byId("volumeForm").destroyRecursive();
            }
            if (dijit.byId("addVolumeLoader")) {
                dijit.byId("addVolumeLoader").destroyRecursive();
            }

            core.ui.loadTemplate("volumeCreateForm", core.ui.getContentId());

            AddVolume.init();
            AddVolume.populateValues(id);

        }),
        "edit": action(function(id) {
            if (dijit.byId("volumeForm")) {
                dijit.byId("volumeForm").destroyRecursive();
            }
            if (dijit.byId("addVolumeLoader")) {
                dijit.byId("addVolumeLoader").destroyRecursive();
            }
            core.ui.loadTemplate("volumeCreateForm", core.ui.getContentId());
            AddVolume.init();
            VolumeInfo.edit(id);
        }),
        "view": action(function(id){
            core.ui.loadTemplate("volumeViewForm", core.ui.getContentId());
            VolumeInfo.view(id);
        })
    });
});

var AddVolume = {
    'init': function() {
        var billingTypeConfigRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/billingType"
        });
        billingTypeConfigRestStore.query().then(function (data) {
            if (data[0].monthlyBillingEnabled === "true") {
                dojo.query("#addVolumeBillingTypeDiv").removeClass("hide_text", true);
            } else {
                dojo.query("#addVolumeBillingTypeDiv").toggleClass("hide_text", true);
            }
        });
    },
    enableMonthlyCost: function() {
       var formElements = dojo.query("#addVolumeBillingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        if(billingType === "monthly") {  
            dojo.query("#volumeMonDiv").removeClass("hide_text", true);                                                              
        } else {
            dojo.query("#volumeMonDiv").toggleClass("hide_text", true);                        
        }                
    },
    populateCurrentZone : function () {
        
        var volumeTypeRestStore = new JsonRest({
                target: core.getContextPath() + "/api/volumetypes"
        });
          
        var cost = 0.00;
        var volumeTypeOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var volumeTypeDataList = new ItemFileWriteStore({data: volumeTypeOptions});
        var availabilityZone = dijit.byId("zoneListWidget").value;
            volumeTypeRestStore.query().then(function(data) {   
                var volumeId = ""; 
                dojo.forEach(data, function(el) {                
                    cost = 0.00;                
                    dojo.forEach(el.volumeTypeZoneCosts, function (zoneData) {                                  
                        if(availabilityZone === zoneData.zone.id) {                      
                            cost = zoneData.cost;                        
                        }
                    });                 
                    volumeTypeDataList.newItem({id: el.referenceId, name: el.name, cost: cost});                
                    if(el.referenceId === "default") {
                        volumeId = el.referenceId; 
                        dojo.byId("volumeTypeCost").innerHTML = el.name + "   |    " + cost + "    " + translator.common.gbPerHr;
                        dojo.byId("monthlyVolumeTypeCost").innerHTML = (cost * 720).toFixed(2) + "     " + translator.common.gbPerMonth;    
                        dojo.query("#offeringInfo #volumeTypeInfo").removeClass("customLabel", true);
                    }                               
                }); 
                dijit.byId("volumeTypeWidget").set("store", volumeTypeDataList)
                dijit.byId("volumeTypeWidget").set("value", volumeId)    
//                dijit.byId("volumeTypeWidget").set("value", "");
            });
    },
    'populateValues': function(volumeSnapshotId) {

        if (dijit.byId("volumeTypeWidget")) {
            dijit.byId("volumeTypeWidget").destroyRecursive();
        }
        if (dijit.byId("imageListWidget")) {
            dijit.byId("imageListWidget").destroyRecursive();
        }
        if (dijit.byId("volumeListWidget")) {
            dijit.byId("volumeListWidget").destroyRecursive();
        }
        if (dijit.byId("volumeSnapshotListWidget")) {
            dijit.byId("volumeSnapshotListWidget").destroyRecursive();
        }
        if (dijit.byId("volumeSourceWidget")) {
            dijit.byId("volumeSourceWidget").destroyRecursive();
        }
        if (dijit.byId("zoneListWidget")) {
            dijit.byId("zoneListWidget").destroyRecursive();
        }

        if (volumeSnapshotId != null || volumeSnapshotId != undefined) {
//            dojo.byId("VolumeSourceDiv").style.display = "none";
            dojo.byId("imageSourceListDiv").style.display = "none";
            dojo.byId("volumeSourceListDiv").style.display = "none";
//            dojo.byId("zoneListDiv").style.display = "none";
            dojo.byId("snapshotAsSourceDiv").style.display = "block";
        } 

        var volumeSourceRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/availableResource"
        });
        
        var currencyRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currencyRestStore.query().then(function (data) {
           dojo.byId("createVolumeCurrecy").innerHTML = data[0].currency; 
        });
        var volumeSourceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var volumeSourcesList = new ItemFileWriteStore({data: volumeSourceOptions});
//        volumeSourcesList.newItem({id: "no_source_type", name: "No source, empty volume"});
        volumeSourceRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
                volumeSourcesList.newItem({id: el.value, name: el.name});
            });
            var volumeSourceWidget = new FilteringSelect({
                store: volumeSourcesList,
                id: "volumeSourceWidget",
                required: false,
                value: "no_source_type",
                onChange: function() {
                    VolumeInfo.showSourceSelect(this)
                }
            });
            volumeSourceWidget.placeAt("volumeSourceList");
            volumeSourceWidget.startup();

        if (volumeSnapshotId != null || volumeSnapshotId != undefined) {
            dijit.byId("volumeSourceWidget").set("value", "snapshot_source");
            dijit.byId("volumeSourceWidget").setAttribute('disabled', true);
        }
  
        });
        
        
        var imageRestStore = new JsonRest({
            target: core.getContextPath() + "/api/image/"
        });
        var imageOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var imageList = new ItemFileWriteStore({data: imageOptions});
//        imageList.newItem({id: "", name: ""});
        imageRestStore.query().then(function(data) {

            dojo.forEach(data, function(el) {
                imageList.newItem({id: el.referenceId, name: el.name});
            });
            var imageWidget = new FilteringSelect({
                store: imageList,
                id: "imageListWidget",
                required: false,
                placeHolder: translator.common.chooseImage,
                onChange: function() {
                }
            });
            imageWidget.placeAt("addVolumeImageList");
            imageWidget.startup();
        });

        var volumeRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/"
        });
        var volumeOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var volumeList = new ItemFileWriteStore({data: volumeOptions});
        volumeRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                volumeList.newItem({id: el.referenceId, name: el.name});
            });
            var volumeWidget = new FilteringSelect({
                store: volumeList,
                id: "volumeListWidget",
                required: false,
                placeHolder: translator.common.selectVolume,
                onChange: function() {
                }
            });
            volumeWidget.placeAt("volumeList");
            volumeWidget.startup();
        });                
        
        var volumeTypeOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var volumeTypeDataList = new ItemFileWriteStore({data: volumeTypeOptions});
        var volumeId = "";                
        dojo.byId("volumeTypeCost").innerHTML = "0.00";
        dojo.byId("monthlyVolumeTypeCost").innerHTML = "0.00";
        dojo.query("#offeringInfo #volumeTypeInfo").toggleClass("customLabel", true);                
        
        var volumeSnapshotRestStore = new JsonRest({
            target: core.getContextPath() + "/api/snapshot/listVolumeSnapshot/"
        });
        var volumeSnapshotOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var volumeSnapshotList = new ItemFileWriteStore({data: volumeSnapshotOptions});
        volumeSnapshotRestStore.query({referenceId: volumeSnapshotId === null || volumeSnapshotId === undefined ? null : volumeSnapshotId}).then(function(data) {
//            volumeSnapshotList.newItem({id: "", name: ""});
            dojo.forEach(data, function(el) {
                if (volumeSnapshotId != null || volumeSnapshotId != undefined) {
                    dijit.byId("volumeName").setValue(el.name);
                    dijit.byId("volumeDescription").setValue(el.description);
                    dijit.byId("volumeSize").setValue(el.size);
                    if(el.volumeType != null) {
                        dijit.byId("volumeTypeWidget").setValue(el.volumeType.referenceId);
                    }
                    volumeSnapshotList.newItem({id: el.referenceId, name: el.name + "(" + el.size + "GB" + ")"});
                } else {
                    volumeSnapshotList.newItem({id: el.referenceId, name: el.name});
                }
            });
            var volumeSnapshotWidget = new FilteringSelect({
                store: volumeSnapshotList,
                id: "volumeSnapshotListWidget",
                required: false,
                placeHolder: translator.common.selectSnapshot,
                onChange: function() {
                }
            });
            volumeSnapshotWidget.placeAt("volumeSnapshotList");
            volumeSnapshotWidget.startup();

            if (volumeSnapshotId != null || volumeSnapshotId != undefined) {
                dijit.byId("volumeSnapshotListWidget").set("value", volumeSnapshotId);
                dijit.byId("volumeSnapshotListWidget").setAttribute('disabled', true);
            }
        });
        
        
        var volumeTypeWidget = new FilteringSelect({                    
            id: "volumeTypeWidget",
            store: volumeTypeDataList,
            required: false,
            value : volumeId,
            placeHolder: translator.common.selectVolumeType,
            onChange: function() {
                AddVolume.showVolueCost(this);
            }
        });
        volumeTypeWidget.placeAt("volumeTypeList");
        volumeTypeWidget.startup();
        
        var availabilityZoneRestStore = new JsonRest({
                target: core.getContextPath() + "/api/zone/"
        });
        var availabilityZoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: []
        };

        var availabilityZoneList = new ItemFileWriteStore({data: availabilityZoneOptions});
        availabilityZoneRestStore.query().then(function(data) {
            var firstVal = "";
            dojo.forEach(data, function(el, index) {   
               availabilityZoneList.newItem({id: el.id, name: el.name});
               if(index === 0) {
                   firstVal = el.id;
               }
            });
            var availabilityZoneWidget = new FilteringSelect({
                store: availabilityZoneList,
                id:"zoneListWidget",
                required: false,
                value: firstVal,
                placeHolder: translator.common.anyAvailabilityZone,       
                onChange: function() {  
                    AddVolume.populateCurrentZone();
                }
            });
            availabilityZoneWidget.placeAt("listAvailabilityZone");
            availabilityZoneWidget.startup();
            
            setTimeout(function () {
                AddVolume.populateCurrentZone();
            }, 100);
        });
        
        
    },
    showVolueCost : function (currentVolume) {    
        if(currentVolume.value === "" || currentVolume.value === " "|| currentVolume.value === "option") {
            dojo.byId("volumeTypeCost").innerHTML = "";
            dojo.byId("monthlyVolumeTypeCost").innerHTML = "";
            dojo.query("#offeringInfo #volumeTypeInfo").toggleClass("customLabel", true);
        } else {
            currentVolume.store.fetch({query: {id: currentVolume.value},
                onItem: function(item) {            
                    if(item.id === "" || item.id === " "|| item.id === "option") {              
                        dojo.byId("volumeTypeCost").innerHTML = "";
                        dojo.byId("monthlyVolumeTypeCost").innerHTML = "";
                        dojo.query("#offeringInfo #volumeTypeInfo").toggleClass("customLabel", true);
                    } else {                                   
                        dojo.byId("volumeTypeCost").innerHTML = item.name + "   |    " + Number(item.cost).toFixed(5) + "    " + translator.common.gbPerHr;
                        var monthCost = Number(item.cost) * 720;
                        dojo.byId("monthlyVolumeTypeCost").innerHTML = localeCurrency.format(Math.ceil((monthCost) * 100.00) / 100.00, {places: 2, locale: dojo.locale})+ "     " + translator.common.gbPerMonth;    
                        dojo.query("#offeringInfo #volumeTypeInfo").removeClass("customLabel", true);
                    }                                                  
                }
            });
        }
        
    },
    'add': function() {

        var status = true;
        dojo.byId("volumeAddButtonDiv").style.display = "block";
        var pageNode = dojo.byId("volumeContent");
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
        } else {

            var volumeRestStore = new JsonRest({
                target: core.getContextPath() + "/api/volume/"
            });
            
            var formElements = dojo.query("#addVolumeBillingTypeDiv input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var billingType = dijit.byId(checkedRadioId).value;
            
            var zoneName = dojo.byId("selectedANZoneID").value;
            var volumeName = dijit.byId("volumeName").value;
            var volumeDescription = dijit.byId("volumeDescription").value;
            var volumeType = dijit.byId("volumeTypeWidget").value;
            var volumeSize = dijit.byId("volumeSize").value;
            var imageListId = dijit.byId("imageListWidget").value;
            var volumeListId = dijit.byId("volumeListWidget").value;
            var volumeSnapshotListId = dijit.byId("volumeSnapshotListWidget").value;

            dojo.style(dijit.byId("addVolumeLoader").closeButtonNode, "display", "none");
            dijit.byId("addVolumeLoader").show();

            volumeRestStore.add({
                name: volumeName,
                description: volumeDescription,
                zoneName: zoneName,
                volumeType: volumeType,
                size: volumeSize,
                volumeId: volumeListId,
                imageId: imageListId,
                volumeSnapshotId: volumeSnapshotListId,
                billingType : billingType
            }).then(function(data) {
                dojo.forEach(data, function(el) {
                    if (el.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.volumeAddedSuccesMsg, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("addVolumeLoader").hide();
                        core.router.go("#/user/volume/list");
                    } else {                        
                        registry.byId("userToaster").setContent(el.localizedMessage, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("addVolumeLoader").hide();
                    }
                });

            });

        }
    },
    'update': function() {

        var status = true;
        var volumeName = dijit.byId("volumeName").value;

        var pageNode = dojo.byId("volumeContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function(el) {
            if (!volumeName) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {

            var volumeRestStore = new JsonRest({
                target: core.getContextPath() + "/api/volume/"
            });


            var volumeDescription = dijit.byId("volumeDescription").value;
            var id = dojo.byId("currentVolumeId").value;

            dojo.style(dijit.byId("addVolumeLoader").closeButtonNode, "display", "none");
            dijit.byId("addVolumeLoader").show();

            volumeRestStore.put({
                name: volumeName,
                description: volumeDescription,
                id: id

            }).then(function(data) {
                dojo.forEach(data, function(el) {
                    if (el.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.volumeUpdatedSuccesMsg, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("addVolumeLoader").hide();
                        core.router.go("#/user/volume/list");
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.ItemUpdateError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("addVolumeLoader").hide();
                    }
                });

            });

        }
    },
    'cancel': function() {
        core.router.go("#/user/volume/list");
    }
}

var ListVolume = {
   
    'populateValues': function() {                

        dojo.byId("volumeListGrid").innerHTML = "<img src='images/vmload.gif' alt=" + translator.common.loader.imageLoadError + " height='36' width='100'/> </br> <p>" + translator.user.loader.volumeLoading + "</p>";
        var gridData = {
            items: []
        };
        var currentRegionId = dojo.byId("selectedRegionID").value ? dojo.byId("selectedRegionID").value : "";
        dojo.byId("noVolumeMessageBox").style.display = "none";

        var gridDataList = new ItemFileWriteStore({data: gridData});

        var gridLayout = [
            [
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '100px', datatype: "string", autoComplete: true,'formatter': function(data) {
                      return "<a href = '#/user/volume/view/"+ data.referenceId+"' title='" + translator.common.view + "'>" + data.name + "</a>";  
                }},
                             
                {'name': translator.common.description, 'field': 'description', 'width': '150px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.size, 'field': 'size', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.status, 'field': 'status', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.volumeType, 'field': 'volumeType', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.attachedTo, 'field': 'attachedTo', 'width': '150px', datatype: "string", autoComplete: true,'formatter': function(data) {

                    if(data.virtualMachineId != null) {
                        return "<a href='#/user/server/viewServer/" + data.virtualMachineId + "' title='" + translator.common.view + "'>" + data.virtualMachineId + "</a>";
                    } else {
                        return "-";
                    }                    
                }},
                {'name': translator.user.grid.storage.layout.availabilityZone, 'field': 'availabilityZone', 'width': '150px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.action, 'field': 'action', 'formatter': function(data, rowIndex) {

                        var menu = new DropDownMenu({style: "display: none;"});
                        
                        var viewMenu = new MenuItem({
                                label: translator.common.view,
                                onClick: function() {
                                    core.router.go("#/user/volume/view/" + data.referenceId);
                                }
                            });
                            menu.addChild(viewMenu);
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
            target: core.getContextPath() + "/api/volume"
        });

        restStore.query({currentRegionId: currentRegionId}).then(function(data) {
            if (data.length === 0 || data === "undefined" || data === undefined) {
                dojo.byId("noVolumeMessageBox").style.display = "block";
                dojo.byId("volumeListGrid").innerHTML = "";
            } else {
                dojo.byId("noVolumeMessageBox").style.display = "none";
                dojo.forEach(data, function(volumeData) {
                    gridDataList.newItem({
                        id: volumeData.referenceId,
                        name: volumeData,
                        description: volumeData.description == "" ? "-" : volumeData.description,
                        size: volumeData.size,
                        status: volumeData.status,
                        volumeType: volumeData.volumeType ==  null ? "-" : volumeData.volumeType,
                        attachedTo: volumeData,
                        vmReferenceId: volumeData.vmReferenceId != null || volumeData.vmReferenceId != "null" ? volumeData.vmReferenceId : "",
                        availabilityZone: volumeData.zone,
                        action: volumeData
                    });
                });
                dojo.byId("volumeListGrid").innerHTML = "";
                
                if (dijit.byId("volumeGridWidget")) {
                    dijit.byId("volumeGridWidget").destroyRecursive();
                }
                var volumeListGrid = new EnhancedGrid({
                    id: 'volumeGridWidget',
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
                volumeListGrid.placeAt("volumeListGrid");
                volumeListGrid.startup();
            }
        });

    },
    showAttachToInstance: function(volumeId) {
        dojo.byId("volumeId").value = volumeId;
        dijit.byId("attachToInstanceDialog").show();
        dijit.byId("attachToInstanceForm").reset();
    },
    createAttachment: function() {
                
        var volumeId = dojo.byId("volumeId").value;
        
        var pageNode = dojo.byId("attachToInstanceFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            
            dijit.byId("attachToInstanceDialog").hide();
            dijit.byId("volumeActionLoader").show();
        
            var volumeAttachmentRest = new JsonRest({
                target: core.getContextPath()+"/api/volume/attachedToInstance"         
            });
            
            volumeAttachmentRest.add({
                volumeId: volumeId,
                instanceId: dijit.byId("VMInstanceListForAttachment").value,
            }).then(function(data) {

                dojo.forEach(data, function(resultData) {                
                    if(resultData.result === "OK") {                      
                        registry.byId("userToaster").setContent(translator.user.instance.volumeAttached,"message");
                        registry.byId("userToaster").show(); 
                        dijit.byId("volumeActionLoader").hide();
                        ListVolume.populateValues();

                    } else {
                        registry.byId("userToaster").setContent(translator.user.instance.volumeNotAttached,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("volumeActionLoader").hide();
                        ListVolume.populateValues();
                    }
                });
            });
        }
    },
    closeCreateAttachemntDialog: function() {
        dijit.byId("attachToInstanceDialog").hide();
    },
    detachVolumeAlertDialog: function(volumeId,instanceId) {

        dojo.byId("deatchedVolumeId").value = volumeId;
        dojo.byId("deatchedInstanceId").value = instanceId;
        dijit.byId("detachVolumeAlertDialog").show();
    },
    detachVolume: function() {
        var volumeId = dojo.byId("deatchedVolumeId").value;
        var instanceId = dojo.byId("deatchedInstanceId").value;
        dijit.byId("detachVolumeAlertDialog").hide();
        dijit.byId("volumeActionLoader").show();

        var volumeAttachmentRest = new JsonRest({
                  target: core.getContextPath()+"/api/volume/detachVolume"         
        });

        volumeAttachmentRest.add({
            volumeId: volumeId,
            instanceId: instanceId,
        }).then(function(data) {

            dojo.forEach(data, function(resultData) {                
                if(resultData.result === "OK") {                      
                    registry.byId("userToaster").setContent(translator.user.instance.volumeDetached,"message");
                    registry.byId("userToaster").show(); 
                    dijit.byId("volumeActionLoader").hide();
                    ListVolume.populateValues();
                } else {
                    registry.byId("userToaster").setContent(translator.user.instance.volumeNotDetached,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("volumeActionLoader").hide();
                    ListVolume.populateValues();
                }
            });
        });
      
    },
    closeDetachVolumeAlertDialog: function() {
        dijit.byId("detachVolumeAlertDialog").hide();
    },
    editVolume: function(id) {
        core.router.go("#/user/volume/edit/" + id + "");
    },
    getDeleteConformation: function(data) {
        console.log("getDeleteConformation",+data)
        dojo.byId("seletedVolumeId").value = data;
        dijit.byId("deleteDialog").show();
    },
    delete: function() {
        dijit.byId("deleteDialog").hide();
        dijit.byId("volumeActionLoader").show();
        var currentId = dojo.byId("seletedVolumeId").value;

        var deleteRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/"
        });

        deleteRestStore.remove(currentId).then(function(data) {
            if (data == "OK") {

                setTimeout(function() {
                    registry.byId("userToaster").setContent(translator.common.message.volumeDeletedSuccesMsg, "message");
                    registry.byId("userToaster").show();
                    ListVolume.populateValues();
                    dijit.byId("volumeActionLoader").hide();
                }, 3000);

            } else {
                registry.byId("userToaster").setContent(data, "error");
                registry.byId("userToaster").show();
                dijit.byId("volumeActionLoader").hide();
            }
        });
        
    },
    closeDeleteDialog: function() {
        dijit.byId("deleteDialog").hide();
    }
};

var VolumeInfo = {
    'showSourceSelect': function(selectWidget) {

        if (selectWidget.value == "image_source") {
            dojo.byId("imageSourceListDiv").style.display = "";
            dojo.byId("volumeSourceListDiv").style.display = "none";
//            dojo.byId("zoneListDiv").style.display = "block";
            dojo.byId("snapshotAsSourceDiv").style.display = "none";

        } else if (selectWidget.value == "volume_source") {
            dojo.byId("imageSourceListDiv").style.display = "none";
            dojo.byId("volumeSourceListDiv").style.display = "";
//            dojo.byId("zoneListDiv").style.display = "none";
            dojo.byId("snapshotAsSourceDiv").style.display = "none";
        } else if (selectWidget.value == "snapshot_source") {
            dojo.byId("imageSourceListDiv").style.display = "none";
            dojo.byId("snapshotAsSourceDiv").style.display = "block";
            dojo.byId("volumeSourceListDiv").style.display = "none";
//            dojo.byId("zoneListDiv").style.display = "none";
        } else {
            dojo.byId("imageSourceListDiv").style.display = "none";
            dojo.byId("volumeSourceListDiv").style.display = "none";
//            dojo.byId("zoneListDiv").style.display = "block";
            dojo.byId("snapshotAsSourceDiv").style.display = "none";
        }

    },
    edit: function(id) {
        
        var editRest = new JsonRest({
            target: core.getContextPath() + "/api/volume/"
        });
        dojo.byId("currentVolumeId").value = id;
        dojo.byId("volumeEdit").innerHTML = translator.common.edit;
        editRest.query({referenceId: id}).then(function(resultData) {
            dojo.forEach(resultData, function(data) {
//                dojo.byId("volumePageHead").innerHTML = translator.common.head.editVolume + " - " + data.name;
                dijit.byId("volumeName").setValue(data.name);
                dijit.byId("volumeDescription").setValue(data.description);
            });
        });
        dojo.byId("volumeAddButtonDiv").style.display = "none";
        dojo.query(".volumeUpdateHide").style("display", "none");
        dojo.byId("volumeUpdateButtonDiv").style.display = "block";

    },
    'gotoList': function() {
        if(dijit.byId("volumeActionLoader")) {
            dijit.byId("volumeActionLoader").hide();
        }
        if(dijit.byId("addVolumeLoader")) {
            dijit.byId("addVolumeLoader").hide();
            core.router.go("#/user/volume/list");
        } 
        
    },
    'view': function(id) {
      
      var volumeDetailsRest =  new JsonRest ({
         target : core.getContextPath()+ "/api/volume/view" 
      });
      
      volumeDetailsRest.query({referenceId: id}).then(function (data){
          dojo.forEach(data, function (resultData){
             dojo.byId("currentVolumeId").value = resultData.referenceId;
             dojo.byId("volumeNameInfo").innerHTML =  resultData.name;
             dojo.byId("volumeIdInfo").innerHTML =  resultData.referenceId;
             dojo.byId("volumeZoneInfo").innerHTML =  resultData.availabilityZone == null ? "-" : resultData.availabilityZone;
             dojo.byId("volumeTypeInfo").innerHTML =  resultData.volumeType == null ? "-" : resultData.volumeType;
             dojo.byId("volumeStatusInfo").innerHTML =  resultData.status;
             dojo.byId("volumeSizeInfo").innerHTML =  resultData.size;
             dojo.byId("volumeCreatedInfo").innerHTML =  resultData.createdOn;
             dojo.byId("volumeAttachedInfo").innerHTML =  resultData.instanceName == null ? "-" : resultData.instanceName;
             dojo.byId("volumeIopsInfo").innerHTML =  resultData.iops == null ? "N/A" : resultData.iops;
          });
      });  
        
    },
    'refresh': function() {
        var id = dojo.byId("currentVolumeId").value;
        VolumeInfo.view(id);
    },
    'gotoVolumeList' : function() {
        core.router.go("#/user/volume/list");
    },
};


