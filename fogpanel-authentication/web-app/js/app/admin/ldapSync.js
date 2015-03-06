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
    "FogPanel/ZonePrice",
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
        function (dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
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

            controller({
                name: "ldapSync",
                module: "admin",
                filePath: "/js/app/admin/ldapSync.js",
                layout: {
                    name: "ldapSync",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "services": action(function () {
                    core.ui.loadTemplate("services", core.ui.getContentId());
                }),
                "list": action(function () {
                    var form = dijit.byId("flavorListForm");
                    if (form) {
                        form.destroyRecursive();
                    }
                    if (dijit.byId("deleteFlavorDialog")) {
                        dijit.byId("deleteFlavorDialog").destroyRecursive();
                    }
                    if (dijit.byId("pullPlanConform")) {
                        dijit.byId("pullPlanConform").destroyRecursive();
                    }
                    if (dijit.byId("flavorLoaderFromDelete")) {
                        dijit.byId("flavorLoaderFromDelete").destroyRecursive();
                    }
                    if (dijit.byId("pullPlanButton")) {
                        dijit.byId("pullPlanButton").destroyRecursive();
                    }
                    if (dijit.byId("flavorStatChangeDialog")) {
                        dijit.byId("flavorStatChangeDialog").destroyRecursive();
                    }

                    core.ui.loadTemplate("accountUserList", core.ui.getContentId());
                    ListAppUsers.init();
                }),
                "createFlavor": action(function () {

                    if (dijit.byId("adminFlavorContentForm")) {
                        dijit.byId("adminFlavorContentForm").destroyRecursive();
                    }
                    if (dijit.byId("pullPlanConform")) {
                        dijit.byId("pullPlanConform").destroyRecursive();
                    }
                    if (dijit.byId("pullPlanLoader")) {
                        dijit.byId("pullPlanLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("addFlavors", core.ui.getContentId());
                    AddFlavorInfo.init();
                }),
                "edit": action(function (id) {
                    if (dijit.byId("adminFlavorContentForm")) {
                        dijit.byId("adminFlavorContentForm").destroyRecursive();
                    }
                    if (dijit.byId("pullPlanConform")) {
                        dijit.byId("pullPlanConform").destroyRecursive();
                    }
                    if (dijit.byId("pullPlanLoader")) {
                        dijit.byId("pullPlanLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("addFlavors", core.ui.getContentId());
                    FlavorInfo.populateValues(id);
                    FlavorInfo.populateEditElement();
                }),
                "view": action(function (id) {
                    core.ui.loadTemplate("viewFlavors", core.ui.getContentId());
                    ListFlavors.view(id)

                })
            });
        });

var ListAppUsers = {
    init: function () {
//
        var countRest = new JsonRest({
            target: core.getContextPath() + "/api/flavors/count"
        });

//        countRest.query().then(function (data) {
//            dojo.forEach(data, function (resultData) {
//                dojo.byId("adminTotalFlavor").innerHTML = resultData.totalFlavors;
//                dojo.byId("adminEnabledFlavor").innerHTML = resultData.enabledFlavors;
//                dojo.byId("adminDisabledFlavor").innerHTML = resultData.disabledFlavors;
//            });
//        });

        ListAppUsers.populateValues();

    },
    view: function (id) {
        dojo.byId("flavorsDetailsId").style.display = "none";
        dojo.byId("adminFlavorsDetails").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";
        var flavorDetails = new JsonRest({
            target: core.getContextPath() + "/api/flavor/details"
        });

        flavorDetails.query({id: id}).then(function (data) {
            dojo.forEach(data, function (resultData) {
                dojo.byId("flavorNameInfo").innerHTML = resultData.name
                dojo.byId("flavorIdInfo").innerHTML = resultData.id
                dojo.byId("flavorClockSpeedInfo").innerHTML = resultData.clockSpeed
                dojo.byId("flavorFamilyInfo").innerHTML = resultData.family
                dojo.byId("flavorIsPublicInfo").innerHTML = (resultData.isPublic === "Yes") ? "<span class='fog_cost_blue'>" + resultData.isPublic + "</span>" : "<span class='fog_cost_red overflowLabel'>" + resultData.isPublic + "</span>"
                dojo.byId("flavorMemoryInfo").innerHTML = resultData.memory
                dojo.byId("flavorInstanceStoreInfo").innerHTML = resultData.instanceStore
                dojo.byId("flavorVcpusInfo").innerHTML = resultData.vcpus
                dojo.byId("flavorEbsInfo").innerHTML = (resultData.isEbsOptimized === "Yes") ? "<span class='fog_cost_blue'>" + resultData.isEbsOptimized + "</span>" : "<span class='fog_cost_red overflowLabel'>" + resultData.isEbsOptimized + "</span>"
                dojo.byId("flavorRootDiskInfo").innerHTML = resultData.rootgb
                dojo.byId("flavorStatusInfo").innerHTML = (resultData.status === "Enabled") ? "<span class='fog_cost_blue'>" + resultData.status + "</span>" : "<span class='fog_cost_red overflowLabel'>" + resultData.status + "</span>"
            })
            dojo.byId("adminFlavorsDetails").innerHTML = ""
            dojo.byId("flavorsDetailsId").style.display = "block";
        })
    },
    closeDialogue: function () {
        dijit.byId("flavorStatChangeDialog").hide();
    },
    changeFlavorDialog: function (currentVTId, status) {
        dojo.byId("currentFlavorStatUpdateId").value = currentVTId;
        dojo.byId("currentFlavorStatus").value = status;

        if (status === "enable") {
            dojo.byId("flavorAvailableConfimMsg").innerHTML = translator.common.message.flavorEnableMsg;
        } else if (status === "disable") {
            dojo.byId("flavorAvailableConfimMsg").innerHTML = translator.common.message.flavorDisableMsg;
        }
        dijit.byId("flavorStatChangeDialog").show();
    },
    flavorAvailableAction: function () {
        var volumeTypeRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/status"
        });
        var successMessage = "";
        var errorMessage = "";
        var status = "";
        if (dojo.byId("currentFlavorStatus").value === "enable") {
            successMessage = translator.common.message.flavorEnabledSuccessMsg;
            errorMessage = translator.common.message.flavorEnabledErrorMsg;
        } else if (dojo.byId("currentFlavorStatus").value === "disable") {
            successMessage = translator.common.message.flavorDisabledSuccessMsg;
            errorMessage = translator.common.message.flavorDisableErrorMsg;
        }
        volumeTypeRestStore.add({
            flavorId: dojo.byId("currentFlavorStatUpdateId").value,
            status: dojo.byId("currentFlavorStatus").value
        }).then(function (data) {
            dijit.byId("flavorStatChangeDialog").hide();
            if (data[0].result === "OK") {
                registry.byId("appToaster").setContent(successMessage, "message");
                registry.byId("appToaster").show();
                ListFlavors.populateValues()
                ListFlavors.init();
            } else {
                registry.byId("appToaster").setContent(errorMessage, "error");
                registry.byId("appToaster").show();
            }
        });
    },
    populateValues: function () {
        dojo.byId("adminFlavorsList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'name': translator.common.name, 'field': 'id', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.vcpu, 'field': 'uuid', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ram, 'field': 'email', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.ram, 'field': 'contactNumber', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
            ]
        ];

        var flavorsRestStore = new JsonRest({
            target: core.getContextPath() + "/api/user/"
        });

        flavorsRestStore.query().then(function (data) {
            
            console.log(" ******************** Data  *******************" + data)            
            
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("adminFlavorsList").innerHTML = "";
                dojo.byId("noOfferMessageBox").style.display = "block";

            } else {

                dojo.byId("noOfferMessageBox").style.display = "none";
                dojo.forEach(data, function (resultData) {
                    dataList.newItem({
                        name: resultData,
                        public: resultData.isPublic === true ? "Yes" : "No",
                        rootDisk: resultData.rootDisk + " " + translator.common.gb,
                        ram: resultData.ram + " " + translator.common.mb,
                        vcpu: resultData.vcpu,
                        memory: resultData.memory,
                        rootgb: resultData.rootgb,
                        action: resultData
                    });
                });
                dojo.byId("adminFlavorsList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
//                        id: 'computDataGrid',
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminFlavorsList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    conformPull: function () {
        dijit.byId("pullPlanConform").show();
    },
    cancelPullPlan: function () {
        dijit.byId("pullPlanConform").hide();
    },
    'pullPlanJob': function (pullPlanJobStatus) {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });

        asyncJob.query({jobType: "PULL_FLAVOR"}).then(function (data) {
            if (data[0] === "OK") {
                dojo.byId("pullPlanLoaderImage").style.display = "inline";
                dijit.byId("pullPlanButton").set("disabled", true);
            } else if (data[0] === "FALSE") {
                dojo.byId("pullPlanLoaderImage").style.display = "none";
                dijit.byId("pullPlanButton").set("disabled", false);
            }
        });
    },
    pullPlan: function () {

//        dijit.byId("pullPlanLoader").show();
        dijit.byId("pullPlanConform").hide();

        var pullPlanRestStore = new JsonRest({
            target: core.getContextPath() + "/api/user/pullFromLdap"
        });

        pullPlanRestStore.query().then(function (data) {
            dojo.forEach(data, function (resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullPlanLoaderImage").style.display = "inline";
                    dijit.byId("pullPlanButton").set("disabled", true);

//                    var pullPlanJobStatus = setInterval(function(){ListFlavors.pullPlanJob(pullPlanJobStatus);},3000); 


//                    ListFlavors.populateValues();
//                    registry.byId("appToaster").setContent(translator.common.message.pullPlanSuccess, "message");
//                    registry.byId("appToaster").show();
//                    dijit.byId("pullPlanLoader").hide();
                } else {
                    dijit.byId("pullPlanButton").set("disabled", false);
                    dojo.byId("pullPlanLoaderImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("pullPlanLoader").hide();
                }
            });
        });

    },
};



var AddFlavorInfo = {
    init: function () {

        var zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone"
        });

        zoneRestStore.query().then(function (data) {
            if (data.length === 0 || data === undefined || data === "undefined") {
                registry.byId("appToaster").setContent(translator.common.instance.flavorSetupNeeded, 'warning');
                registry.byId("appToaster").show();
                core.router.go("#/admin/dashboard");
            }
        });

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function (data) {
            dojo.forEach(data, function (cur) {
                dojo.byId("currencyValue").innerHTML = cur.currency;
            });
        });
        dojo.byId("addFlavorhead").innerHTML = translator.common.head.createFlavor;

        if (dijit.byId("availabilityZonesListWidget")) {
            dijit.byId("availabilityZonesListWidget").destroyRecursive();
        }

        var availabilityZoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        dojo.byId("currentZoneCostDiv").innerHTML = "";

        availabilityZoneRestStore.query().then(function (data) {
            dojo.forEach(data, function (zoneInfo) {
                var currentZonePrice = new FogPanel.ZonePrice({
                    id: "flavorOfferCurrentZonePrice-" + zoneInfo.name,
                    zoneName: zoneInfo.name,
                    zoneIdNode: zoneInfo.name
                });
                currentZonePrice.placeAt("currentZoneCostDiv");
            });
        });
//        var regionListRestStore = new JsonRest({
//            target: core.getContextPath() + "/api/region"
//        });
//        var regionOptions = { 
//            identifier: 'id',
//            label: 'name',
//            items: []
//        };
//    
//        var regionList = new ItemFileWriteStore({data: regionOptions});
//        regionListRestStore.query().then(function(data) {
//
//                dojo.forEach(data, function(el) {
//                   regionList.newItem({id: el.id, name: el.name});
//                });
//                var regionWidget = new FilteringSelect({
//                    store: regionList,
//                    id:"regionListWidget",
//                    onChange: function() {
////                        AddComputingOfferInfo.viewSelectedZone(this);   
//                    }
//                });
//                regionWidget.placeAt("regionList");
//                regionWidget.startup();
//
//        });

    },
    'cancel': function () {
        core.router.go("#/admin/flavors/list");
    },
    'add': function () {
        dojo.byId("flavorAddButtonDiv").style.display = "block";
        var status = true;
        var pageNode = dojo.byId("flavorContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        var secondNode = "";
        var flavorZoneCosts = [];
        var zoneStatus = true;
        var nodeStatus = true;

        dojo.forEach(widgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });

        var widgetNode = dojo.byId("currentFlavorZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(widgetNode);

        dojo.forEach(zoneWidgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!secondNode) {
                    secondNode = el;
                }

            }
            if (el.showErrorMsg() == false) {
                zoneStatus = false;
            }
        });

        if (firstNode) {
            firstNode.focus();
            nodeStatus = false;
        } else if (secondNode) {
            secondNode.focus();
        }

        if (nodeStatus == true && zoneStatus == true) {

            var widgetNode = dojo.byId("currentFlavorZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(widgetNode);
            dojo.forEach(zoneWidgets, function (el) {

                flavorZoneCosts.push({
                    zoneName: el.getZoneId(),
                    instanceRunningCost: el.getZoneCost(),
                    instanceStopageCost: el.getMinCost(),
                    setupCost: el.getSetupCost(),
                })
            });
            var flavorsRestStore = new JsonRest({
                target: core.getContextPath() + "/api/flavor/"
            });

            var name = dijit.byId("flavorName").value;
            var description = dijit.byId("flavorDescription").value;
            var flavorID = dijit.byId("flavorID").value;
            var ram = dijit.byId("flavorMemory").value;
            var vcpus = dijit.byId("flavorCpuNumber").value;
            var disk = dijit.byId("flavorRoodDisk").value;
            var ephemeral = dijit.byId("flavorEphemeralDisk").value;
            var swap = dijit.byId("flavorSwapDisk").value;
            //            var rxtxFactor = dijit.byId("rxtxFactor").value;
            //            var isPublic = dijit.byId("isPublic").value;

            dijit.byId("pullPlanLoader").show();

            flavorsRestStore.add({
                flavorID: flavorID,
                description: description,
                name: name,
                ram: ram,
                vcpus: vcpus,
                disk: disk,
                ephemeral: ephemeral,
                swap: swap,
                rxtxFactor: 1,
                isPublic: true,
                flavorZoneCosts: flavorZoneCosts,
            }).then(function (data) {
                dojo.forEach(data, function (resultData) {
                    if (resultData.result == "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.addedSuccess, "message");
                        registry.byId("appToaster").show();
                        core.router.go("#/admin/flavors/list");
                        dijit.byId("pullPlanLoader").hide();
                    } else {
                        registry.byId("appToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("pullPlanLoader").hide();
                    }
                })

            });

        }
    }
};
var FlavorInfo = {
    populateEditElement: function () {
        dojo.byId("flavorDecsDiv").style.display = "none";
        dijit.byId("flavorName").set("disabled", true);
        dijit.byId("flavorName").set("required", false);

        dijit.byId("flavorDescription").set("disabled", true);
        dijit.byId("flavorDescription").set("required", false);

        dijit.byId("flavorID").set("disabled", true);
        dijit.byId("flavorID").set("required", false);

        dijit.byId("flavorCpuNumber").set("disabled", true);
        dijit.byId("flavorCpuNumber").set("required", false);

        dijit.byId("flavorMemory").set("disabled", true);
        dijit.byId("flavorMemory").set("required", false);

        dijit.byId("flavorRoodDisk").set("disabled", true);
        dijit.byId("flavorRoodDisk").set("required", false);

        dijit.byId("flavorEphemeralDisk").set("disabled", true);
        dijit.byId("flavorEphemeralDisk").set("required", false);
        dijit.byId("flavorSwapDisk").set("disabled", true);
        dijit.byId("flavorSwapDisk").set("required", false);


    },
    'deleteFlavorDialogAlert': function (currentId) {
        dijit.byId("deleteFlavorDialog").show();
        dojo.byId("flavorDeleteId").value = currentId;

    },
    'confirmDelete': function () {
        var currentId = dojo.byId("flavorDeleteId").value;
        dijit.byId("deleteFlavorDialog").hide();
        var deleteFlavorRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/"
        });
        dijit.byId("flavorLoaderFromDelete").show();
        deleteFlavorRestStore.remove(currentId).then(function (data) {

            if (data === "OK") {

                dijit.byId("flavorLoaderFromDelete").hide();
                registry.byId("appToaster").setContent(translator.common.message.deletedSuccess, "message");
                registry.byId("appToaster").show();
                ListFlavors.populateValues();


            } else {

                dijit.byId("flavorLoaderFromDelete").hide();
                registry.byId("appToaster").setContent(translator.common.message.cannotDelete, "error");
                registry.byId("appToaster").show();
            }

        });

    },
    'closeDeleteFlavorDialog': function () {
        dijit.byId("deleteFlavorDialog").hide();
    },
    'populateValues': function (id) {

        dojo.byId("editFlavorBreadcrum").innerHTML = translator.common.edit;

//        var zoneRestStore = new JsonRest({
//            target: core.getContextPath()+"/api/zone"
//        });  
//        
//        zoneRestStore.query().then(function(data) {          
//           if(data.length === 0 || data === undefined || data === "undefined") {                                      
//               registry.byId("appToaster").setContent(translator.common.instance.flavorSetupNeeded, 'warning');
//                registry.byId("appToaster").show();
//                core.router.go("#/admin/dashboard");
//            } 
//        });

        var getFlavorRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/"
        });
        dojo.byId("currentFlavorId").value = id;

        var availabilityZoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });
        getFlavorRestStore.query({referenceId: id}).then(function (resultData) {
            dojo.forEach(resultData, function (data) {
                dojo.byId("addFlavorhead").innerHTML = translator.common.head.editFlavor + " - " + data.name;
                dijit.byId("flavorName").setValue(data.name);
                dijit.byId("flavorDescription").setValue(data.description);
                dijit.byId("flavorID").setValue(data.referenceId);
                dijit.byId("flavorMemory").setValue(data.ram);
                dijit.byId("flavorCpuNumber").setValue(data.vcpu);
                dijit.byId("flavorRoodDisk").setValue(data.rootDisk);
                dijit.byId("flavorEphemeralDisk").setValue(data.ephemeralDisk);
                dijit.byId("flavorSwapDisk").setValue(data.swapDisk);
                availabilityZoneRestStore.query().then(function (allZoneData) {
                    dojo.forEach(allZoneData, function (el, index) {
                        var zoneCost = 0.0;
                        var setupCostValue = 0.0;
                        var stopCost = 0.0;
                        dojo.forEach(data.flavorCostList, function (zoneCostData) {
                            if (el.id === zoneCostData.zone.id) {
                                zoneCost = (zoneCostData.runningCost * 720).toFixed(2);
                                setupCostValue = zoneCostData.setupCost,
                                        stopCost = (zoneCostData.stopCost * 720).toFixed(2);
                            }
                        });
                        if (dijit.byId("zone_widget_" + el.name + "_" + index)) {
                            dijit.byId("zone_widget_" + el.name + "_" + index).destroyRecursive();
                        }
                        var currentZonePrice = new FogPanel.ZonePrice({
                            id: "zone_widget_" + el.name + "_" + index,
                            zoneName: el.name,
                            zoneIdNode: el.id,
                            zoneCost: zoneCost,
                            setupCostValue: setupCostValue,
                            minCost: stopCost
                        });
                        currentZonePrice.setMinCost(),
                                currentZonePrice.setCost(),
                                currentZonePrice.setSetupCost();
                        currentZonePrice.placeAt("currentZoneCostDiv");
                    });
                });
            });
        });
        dojo.byId("flavorAddButtonDiv").style.display = "none";
        dojo.byId("flavorUpdateButtonDiv").style.display = "block";
    },
    'update': function () {
        var id = dojo.byId("currentFlavorId").value;
        var name = dijit.byId("flavorName").value;
        var description = dijit.byId("flavorDescription").value;
        var flavorID = dijit.byId("flavorID").value;
        var ram = dijit.byId("flavorMemory").value;
        var vcpus = dijit.byId("flavorCpuNumber").value;
        var disk = dijit.byId("flavorRoodDisk").value;
        var ephemeral = dijit.byId("flavorEphemeralDisk").value;
        var swap = dijit.byId("flavorSwapDisk").value;

        var status = true;
        var pageNode = dojo.byId("flavorContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        var secondNode = "";
        var flavorZoneCosts = [];
        var zoneStatus = true;
        var nodeStatus = true;

        dojo.forEach(widgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });

        var widgetNode = dojo.byId("currentFlavorZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(widgetNode);

        dojo.forEach(zoneWidgets, function (el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!secondNode) {
                    secondNode = el;
                }

            }
            if (el.showErrorMsg() == false) {
                zoneStatus = false;
            }
        });

        if (firstNode) {
            firstNode.focus();
            nodeStatus = false;
        } else if (secondNode) {
            secondNode.focus();
        }

        if (nodeStatus == true && zoneStatus == true) {

            var widgetNode = dojo.byId("currentFlavorZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(widgetNode);
            dojo.forEach(zoneWidgets, function (el) {

                flavorZoneCosts.push({
                    zoneId: el.getZoneId(),
                    runningCost: el.getZoneCost(),
                    stopageCost: el.getMinCost(),
                    setupCost: el.getSetupCost()
                });
            });

            var updateFlavorRestStore = new JsonRest({
                target: core.getContextPath() + "/api/flavor/"
            });
            updateFlavorRestStore.put({
                id: id,
                name: name,
                description: description,
                memory: ram,
                vcpus: vcpus,
                rootgb: disk,
                ephemeralgb: ephemeral,
                swap: swap,
                flavorZoneCosts: flavorZoneCosts,
            }).then(function (resultData) {
                dojo.forEach(resultData, function (data) {
                    if (data.result === "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("appToaster").show();
//                        ListFlavors.populateValues();
                    } else if (data.result === "Exists") {
                        registry.byId('appToaster').setContent(translator.common.message.exists, 'error');
                        registry.byId('appToaster').show();
                    } else {
                        registry.byId("appToaster").setContent(data.message, "error");
                        registry.byId("appToaster").show();
                    }
                });
            });
        }
    },
    'gotoList': function () {
        if (dijit.byId("pullPlanLoader")) {
            dijit.byId("pullPlanLoader").hide();
            core.router.go("#/admin/flavors/list");
        }
        dijit.byId("flavorLoaderFromDelete").hide();

    },
};
