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
    "dijit/layout/TabContainer",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",    
    "dijit/form/MultiSelect",
    "dojo/currency",
    "dijit/form/DateTextBox",    
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
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select, ItemFileWriteStore, 
        DataGrid, ContentPane, Source, MultiSelect, localeCurrency, dom, win) {
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
            window.Source = Source;
            window.MultiSelect = MultiSelect;
            window.dom = dom;
            window.win = win;

            controller({
                name: "template",
                module: "admin",
                filePath: "/js/app/admin/template.js",
                layout: {
                    name: "template",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "index": action(function() {
                    if (dijit.byId("adminOSTempActionForm")) {
                        dijit.byId("adminOSTempActionForm").destroyRecursive();
                        dijit.byId("deleteOsTemplateDialog").destroyRecursive();
                    }
                    
                    if(dijit.byId("pullTemplateConform")) {
                        dijit.byId("pullTemplateConform").destroyRecursive();
                    }  
                    if(dijit.byId("pullTemplateLoader")) {
                        dijit.byId("pullTemplateLoader").destroyRecursive();
                    } 
                    
                    core.ui.loadTemplate("tempPage", core.ui.getContentId());
                    OsTemplateInfo.init();
                    OsTemplateInfo.populateValues();
                }),
                "osTemplate": action(function() {
                    if (dijit.byId("adminOSTempActionForm")) {
                        dijit.byId("adminOSTempActionForm").destroyRecursive();
                        dijit.byId("deleteOsTemplateDialog").destroyRecursive();
                    }
                    
                    if(dijit.byId("pullTemplateConform")) {
                        dijit.byId("pullTemplateConform").destroyRecursive();
                    }  
                    if(dijit.byId("pullTemplateLoader")) {
                        dijit.byId("pullTemplateLoader").destroyRecursive();
                    } 
                    
                    
                    core.ui.loadTemplate("tempPage", core.ui.getContentId());
                    OsTemplateInfo.init();
                    OsTemplateInfo.populateValues();
                }),
                "addOsTemplate": action(function() {
                    if (dijit.byId("osTempAddForm")) {
                        dijit.byId("osTempAddForm").destroyRecursive();
                    }

                    core.ui.loadTemplate("addOsTemplate", core.ui.getContentId());
                    AddOsTempInfo.init();
                    AddOsTempInfo.populateValues();
                }),
                "viewOsTemp": action(function(id) {
                    if (dijit.byId("osTempEditForm")) {
                        dijit.byId("osTempEditForm").destroyRecursive();
                        dijit.byId("osTemplateEditConformationDialog").destroyRecursive();
                    }
                    core.ui.loadTemplate("viewOsTemplate", core.ui.getContentId());
                    ViewOsTemplate.init();
                    ViewOsTemplate.populateValues(id);
                }),
                "deleteOsTemp": action(function(id) {
                    if (dijit.byId("adminOSTempActionForm")) {
                        dijit.byId("adminOSTempActionForm").destroyRecursive();
                        dijit.byId("deleteOsTemplateDialog").destroyRecursive();
                    }

                    core.ui.loadTemplate("tempPage", core.ui.getContentId());
                    OsTemplateInfo.init();
                    OsTemplateInfo.populateValues();

                    DeleteTemplate.init();
                    DeleteTemplate.populateValues(id, "osTemp");
                }),
                "appTemplate": action(function() {
                    if (dijit.byId("adminAppTempActionForm")) {
                        dijit.byId("adminAppTempActionForm").destroyRecursive();
                        dijit.byId("deleteAppTemplateDialog").destroyRecursive();
                    }

                    core.ui.loadTemplate("appTemplate", core.ui.getContentId());
                    AppTemplateInfo.init();
                    AppTemplateInfo.populateValues();
                }),
                "addAppTemplate": action(function() {
                    if (dijit.byId("osTempAddForm")) {
                        dijit.byId("osTempAddForm").destroyRecursive();
                    }

                    core.ui.loadTemplate("addAppTemplate", core.ui.getContentId());
                    AddAppTemplateInfo.init();
                    AddAppTemplateInfo.populateValues();
                }),
                "viewAppTemp": action(function(id) {
                    if (dijit.byId("appTempEditForm")) {
                        dijit.byId("appTempEditForm").destroyRecursive();
                        dijit.byId("appTemplateEditConformationDialog").destroyRecursive();
                    }
                    core.ui.loadTemplate("viewAppTemplate", core.ui.getContentId());
                    ViewsAppTemplate.init();
                    ViewsAppTemplate.populateValues(id);
                }),
                "deleteAppTemp": action(function(id) {
                    if (dijit.byId("adminAppTempActionForm")) {
                        dijit.byId("adminAppTempActionForm").destroyRecursive();
                        dijit.byId("deleteAppTemplateDialog").destroyRecursive();
                    }

                    core.ui.loadTemplate("appTemplate", core.ui.getContentId());
                    AppTemplateInfo.init();
                    AppTemplateInfo.populateValues();

                    DeleteTemplate.init();
                    DeleteTemplate.populateValues(id, "appTemp");
                }),
                "deleteMyTemp": action(function(id) {
                    if (dijit.byId("adminUserTempActionForm")) {
                        dijit.byId("adminUserTempActionForm").destroyRecursive();
                    }

                    core.ui.loadTemplate("userTemplate", core.ui.getContentId());
                    UserTemplateInfo.init();
                    UserTemplateInfo.populateValues();
                    DeleteTemplate.init();
                    DeleteTemplate.populateValues(id, "userTemp");
                }),
                "userTemplate": action(function() {
                    if (dijit.byId("adminUserTempActionForm")) {
                        dijit.byId("adminUserTempActionForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("userTemplate", core.ui.getContentId());
                    UserTemplateInfo.init();
                    UserTemplateInfo.populateValues();
                }),
                "viewMyTemp": action(function(id) {
                    if (dijit.byId("myTempEditForm")) {
                        dijit.byId("myTempEditForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("viewMyTemp", core.ui.getContentId());
                    ViewsMyTemplate.init();
                    ViewsMyTemplate.populateValues(id);
                })
            });
        });

var UserTemplateInfo = {
    _zoneRestStore: "",
    _osTypeRestStore: "",
    _tempRestStrore: "",
    _tempCountRestStrore: "",
    name: "",
    description: "",
    url: "",
    zone: "",
    hypervisor: "",
    format: "",
    osTypeId: "",
    extractable: "",
    passwordEnabled: "",
    isPublic: "",
    featured: "",
    hyperviserItems: "",
    init: function() {
        this._tempRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/"
        });

        this._tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });
    },
    populateValues: function() {
        if (dijit.byId("adminUserTempZoneWidget") && dijit.byId("adminUserTempDataGrid")) {
            dijit.byId("adminUserTempZoneWidget").destroyRecursive();
            dijit.byId("adminUserTempDataGrid").destroyRecursive();
        }

        if (dijit.byId("adminUserTempZoneWidget")) {
            dijit.byId("adminUserTempZoneWidget").destroyRecursive();
        }
        dojo.byId("adminUserTemplateInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.options.allZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName})
            });
        });
        var zoneWidget = new Select({
            id: "adminUserTempZoneWidget",
            store: zoneList,
            onChange: function() {
                UserTemplateInfo.getVMByZone(this);
            }
        }, "adminUserTempZoneList");
        var data = {
            items: []
        };
        var dataList = new ItemFileWriteStore({data: data});
        var dataLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.template.grid.templateName, 'field': 'name', 'width': '100%', 'formatter': function(data) {
                        var os = data.split(",");
                        switch (os[0]) {
                            case "CentOS":
                                return "<img src='images/os/centos.JPG' alt='"+translator.common.template.centOS+"' height='25' width='25'/>" + os[1];
                            case "Debian":
                                return "<img src='images/os/debian.JPG' alt='"+translator.common.template.debian+"' height='25' width='25'/>" + os[1];
                            case "Oracle":
                                return "<img src='images/os/oracle.JPG' alt='"+translator.common.template.oracle+"' height='25' width='25'/>" + os[1];
                            case "RedHat":
                                return "<img src='images/os/redhat.JPG' alt='"+translator.common.template.redHat+"' height='25' width='25'/>" + os[1];
                            case "SUSE":
                                return "<img src='images/os/suse.JPG' alt='"+translator.common.template.SUSE+"' height='25' width='25'/>" + os[1];
                            case "Windows":
                                return "<img src='images/os/windows.JPG' alt='"+translator.common.template.windows+"' height='25' width='25'/>" + os[1];
                            case "Novel":
                                return "<img src='images/os/novel.JPG' alt='"+translator.common.template.novel+"' height='25' width='25'/>" + os[1];
                            case "Unix":
                                return "<img src='images/os/uinx.JPG' alt='"+translator.common.template.unix+"' height='25' width='25'/>" + os[1];
                            case "Ubuntu":
                                return "<img src='images/os/ubuntu.JPG' alt='"+translator.common.template.ubuntu+"' height='25' width='25'/>" + os[1];
                        }
                    }
                },
                {'name': translator.common.operatinSystem, 'field': 'os', 'width': '100%'},
                {'name': translator.common.template.grid.osType, 'field': 'osType', 'width': '100%'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '100%'},
                {'name': translator.common.cost  + " ("+ core.getCurrency() +")", 'field': 'cost', 'width': '100%'},
                {'name': translator.common.status.name, 'field': 'status', 'width': '100%', 'formatter': function(data) {
                        var resultData = data.split(",")
                        var imageDiv = domConstruct.create("div", {innerHTML: "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"'/>"});
                        var status;
                        if (resultData[1] == true || resultData[1] == 'true') {
                            status = resultData[0];
                        } else {
                            status = imageDiv.innerHTML;
                        }
                        return status;
                    }
                },
                {'name': 'Action', 'width': '100%', 'field': 'action', 'formatter': function(data) {
                        var dataList = data.split(",")
                        var html;
                        if (dataList[1] == true || dataList[1] == 'true') {
                            html = "<a class='btn-flat success' href='#/admin/template/viewMyTemp/" + data + "'>"+translator.common.view+"</a>" +
                                    "<a class='btn-flat danger spacing' href='#/admin/template/deleteMyTemp/" + data + "'>"+translator.common.deleteData+"</a>";
                        } else {
                            html = "";
                        }
                        return html;
                    }
                }
            ]
        ];

        this._tempCountRestStrore.query({myTemplate: true}).then(function(resultData) {
            if (resultData[0].tempData.length == 0 || resultData[0].tempData == "" || resultData[0].tempData == " " || resultData[0].tempData == undefined || resultData[0].tempData == 'undefined') {
                dojo.byId("adminUserTemplateInfo").innerHTML = "";
                dojo.byId("noUserTempMessageBox").style.display = "block";
            } else {
                dojo.byId("noUserTempMessageBox").style.display = "none";
                dojo.forEach(resultData, function(data) {
                    dojo.byId("adminTotalUserTemp").innerHTML = data.totalTemplates;
                    dojo.byId("adminTotalUserLinuxTemp").innerHTML = data.linuxTotal;
                    dojo.byId("adminTotalUserWindowsTemp").innerHTML = data.windowsTotal;
                    dojo.forEach(data.tempData, function(temp) {
                        if (temp.status == " " || temp.status == "" || temp.status == "undefined" || temp.status == undefined) {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.osCategory + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osType,
                                cost: localeCurrency.format(temp.cost),
                                action: temp.id + "," + temp.isReady,
                                status: translator.common.message.connecting + "," + temp.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.osCategory + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osType,
                                cost: localeCurrency.format(temp.cost),
                                action: temp.id + "," + temp.isReady,
                                status: temp.status + "," + temp.isReady
                            });
                        }
                    });
                });

                dojo.byId("adminUserTemplateInfo").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: 'adminUserTempDataGrid',
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminUserTemplateInfo");
                dataGrid.startup();
            }
        });
    },
    getVMByZone: function(currentZone) {
        var formElements = dojo.query("#adminUserTempActionForm input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var currentStatus = dijit.byId(checkedRadioId).value;

        var tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });

        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("adminUserTempDataGrid");
        var dataList = new ItemFileWriteStore({data: dataInfo});
        if (currentZone.value == "option" && currentStatus == "All") {
            tempCountRestStrore.query({myTemplate: true}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData) {
                        dojo.byId("adminTotalUserTemp").innerHTML = resultData.totalTemplates;
                        dojo.byId("adminTotalUserLinuxTemp").innerHTML = resultData.linuxTotal;
                        dojo.byId("adminTotalUserWindowsTemp").innerHTML = resultData.windowsTotal;
                    } else {
                        dojo.byId("adminTotalUserTemp").innerHTML = 0;
                        dojo.byId("adminTotalUserLinuxTemp").innerHTML = 0;
                        dojo.byId("adminTotalUserWindowsTemp").innerHTML = 0;
                    }
                    dojo.forEach(resultData.tempData, function(temp) {
                        if (temp.status == " " || temp.status == "" || temp.status == "undefined" || temp.status == undefined) {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.osCategory + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osType,
                                cost: localeCurrency.format(temp.cost),
                                action: temp.id + "," + temp.isReady,
                                status: translator.common.message.connecting + "," + temp.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.osCategory + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osType,
                                cost: localeCurrency.format(temp.cost),
                                action: temp.id + "," + temp.isReady,
                                status: temp.status + "," + temp.isReady
                            });
                        }
                    });
                    grid.setStore(dataList);
                });
            });
        } else if (currentZone.value == "option" && currentStatus != "All") {
            tempCountRestStrore.query({baseOs: currentStatus, myTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    if (el) {
                        dojo.byId("adminTotalUserTemp").innerHTML = el.totalTemplates;
                        dojo.byId("adminTotalUserLinuxTemp").innerHTML = el.linuxTotal;
                        dojo.byId("adminTotalUserWindowsTemp").innerHTML = el.windowsTotal;
                    } else {
                        dojo.byId("adminTotalUserTemp").innerHTML = 0;
                        dojo.byId("adminTotalUserLinuxTemp").innerHTML = 0;
                        dojo.byId("adminTotalUserWindowsTemp").innerHTML = 0;
                    }

                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }
                    });
                    grid.setStore(dataList);
                });
            });
        } else {
            if (currentStatus == "All") {
                tempCountRestStrore.query({zoneReferenceId: currentZone.value, myTemplate: true}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        if (el) {
                            dojo.byId("adminTotalUserTemp").innerHTML = el.totalTemplates;
                            dojo.byId("adminTotalUserLinuxTemp").innerHTML = el.linuxTotal;
                            dojo.byId("adminTotalUserWindowsTemp").innerHTML = el.windowsTotal;
                        } else {
                            dojo.byId("adminTotalUserTemp").innerHTML = 0;
                            dojo.byId("adminTotalUserLinuxTemp").innerHTML = 0;
                            dojo.byId("adminTotalUserWindowsTemp").innerHTML = 0;
                        }
                        dojo.forEach(el.tempData, function(tempData) {
                            if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.osCategory + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    action: tempData.id + "," + tempData.isReady,
                                    status: translator.common.message.connecting+ "," + tempData.isReady
                                });
                            } else {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.osCategory + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    action: tempData.id + "," + tempData.isReady,
                                    status: tempData.status + "," + tempData.isReady
                                });
                            }
                        });
                        grid.setStore(dataList);
                    });
                });
            } else {
                tempCountRestStrore.query({zoneReferenceId: currentZone.value, baseOs: currentStatus, myTemplate: true}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        if (el) {
                            dojo.byId("adminTotalUserTemp").innerHTML = el.totalTemplates;
                            dojo.byId("adminTotalUserLinuxTemp").innerHTML = el.linuxTotal;
                            dojo.byId("adminTotalUserWindowsTemp").innerHTML = el.windowsTotal;
                        } else {
                            dojo.byId("adminTotalUserTemp").innerHTML = 0;
                            dojo.byId("adminTotalUserLinuxTemp").innerHTML = 0;
                            dojo.byId("adminTotalUserWindowsTemp").innerHTML = 0;
                        }

                        dojo.forEach(el.tempData, function(tempData) {
                            if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.osCategory + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    action: tempData.id + "," + tempData.isReady,
                                    status: translator.common.message.connecting + "," + tempData.isReady
                                });
                            } else {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.osCategory + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    action: tempData.id + "," + tempData.isReady,
                                    status: tempData.status + "," + tempData.isReady
                                });
                            }
                        });
                        grid.setStore(dataList);
                    });
                });
            }
        }
    },
    showTemplate: function(currentRadio) {
        var tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });

        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("adminUserTempDataGrid")
        var dataList = new ItemFileWriteStore({data: dataInfo});
        var zoneWidget = dijit.byId("adminUserTempZoneWidget");
        if (zoneWidget.value == "option" && currentRadio.value == "All") {
            tempCountRestStrore.query({myTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }
                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value == "option" && currentRadio.value != "All") {
            tempCountRestStrore.query({baseOs: currentRadio.value, myTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }
                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value != "option" && currentRadio.value != "All") {
            tempCountRestStrore.query({zoneReferenceId: zoneWidget.value, baseOs: currentRadio.value, myTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }
                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value != "option" && currentRadio.value == "All") {
            tempCountRestStrore.query({zoneReferenceId: zoneWidget.value, myTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }
                    });
                    grid.setStore(dataList);
                });
            });
        }
    }
};

var ViewsMyTemplate = {
    _tempRestStrore: "",
    _tempCountRestStrore: "",
    _currentId: "",
    init: function() {
        this._tempRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/"
        });

        this._tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count/"
        });
    },
    populateValues: function(currentId) {
        this._currentId = currentId;
        this._tempRestStrore.get(currentId).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dijit.byId("EditUserTempName").setValue(resultData.name);
                dijit.byId("EditUserTempDescription").setValue(resultData.description);
                dijit.byId("EditUserTempCost").setValue(resultData.cost);

                dijit.byId("EditUserTempPasswordEnabled").set("checked", resultData.passwordEnabled);
                dijit.byId("EditUserTempExtractable").set("checked", resultData.extractable);
                dijit.byId("EditUserTempPublic").set("checked", resultData.isPublic);
                dijit.byId("EditUserTempFeatured").set("checked", resultData.featured);

                dojo.byId("editUserTempUrl").innerHTML = resultData.url;
                dojo.byId("editUserTempHypervisor").innerHTML = resultData.hypervisor;
                dojo.byId("editUserTempFormat").innerHTML = resultData.format;
                dojo.byId("editUserTempOsCategory").innerHTML = resultData.osCategory;
                dojo.byId("editUserTempOsType").innerHTML = resultData.osType;
                dojo.byId("editUserTempZone").innerHTML = resultData.zone;
                dojo.byId("currentMyTempName").innerHTML = resultData.name;
                dojo.byId("muTempOsType").innerHTML = resultData.osType;
            });
        });
    },
    update: function() {
        var name = dijit.byId("EditUserTempName");
        var description = dijit.byId("EditUserTempDescription");
        var cost = dijit.byId("EditUserTempCost");

        var extractable = dijit.byId("EditUserTempExtractable").checked;
        var passwordEnabled = dijit.byId("EditUserTempPasswordEnabled").checked;
        var isPublic = dijit.byId("EditUserTempPublic").checked;
        var featured = dijit.byId("EditUserTempFeatured").checked;

        this._tempRestStrore.put({
            id: this._currentId,
            name: name.value,
            description: description.value,
            cost: cost.value,
            extractable: extractable,
            passwordEnabled: passwordEnabled,
            isPublic: isPublic,
            featured: featured
        }).then(function(results) {
            if (results == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                registry.byId("appToaster").show();
                core.router.go("#/admin/template/userTemplate");
                dijit.byId("myTempEditForm").reset();
            } else {
                registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                registry.byId("appToaster").show();
            }
        });
    }
};

var ViewOsTemplate = {
    _tempRestStrore: "",
    _tempCountRestStrore: "",
    _currentId: "",
    init: function() {
        this._tempRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/"
        });

        this._tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count/"
        });
    },
    checkTemplateType : function (currentTempType) {        
        if(currentTempType.value === "APP") {           
            dojo.query("#appTemplateURLDiv").removeClass("hide_text", true);            
            dijit.byId("osTemplateImageURL").set("required", true);
        } else {           
            dojo.query("#appTemplateURLDiv").toggleClass("hide_text", true);  
            dijit.byId("osTemplateImageURL").set("required", false);
        }  
    },
    updateShow: function() {
        var cost = dijit.byId("editOsTempCost");
        
        var pageNode = dojo.byId("osTempEditForm");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            if((dojo.byId("editOsTemOneTime").innerHTML == translator.common.yes) && (cost == 0)) {
                registry.byId("appToaster").setContent(translator.common.message.oneTimeChargeTemp, "error");
                registry.byId("appToaster").show();
            } else {
                dijit.byId("osTemplateEditConformationDialog").show();
            }
        }
    },
    closeUpdate: function() {
        dijit.byId("osTemplateEditConformationDialog").hide();
    },
    populateValues: function(currentId) {
        this._currentId = currentId;
        var container;
        this._tempRestStrore.get(currentId).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dijit.byId("dashboardEditOsName").setValue(resultData.name);
                dijit.byId("editOsTempDescription").setValue(resultData.description);
                dijit.byId("editOsTempCost").setValue(resultData.cost);

                dijit.byId("editTempOsPasswordEnabled").set("checked", resultData.passwordEnabled);
                dijit.byId("editTempOsExtractable").set("checked", resultData.extractable);
                dijit.byId("editTempOsPublic").set("checked", resultData.isPublic);
                dijit.byId("editTempOsFeatured").set("checked", resultData.featured);
                dijit.byId("osTemplateDetailDescEdit").set("value", resultData.detailedDescription);
                dijit.byId("editOsTemCpu").setValue(resultData.minimumCpu);
                dijit.byId("editOsTemRam").setValue(resultData.minimumRam);
                dijit.byId("editOsTemOneTime").set("checked", resultData.oneTimeChargeable);
                dojo.byId("editOsTempUrl").innerHTML = resultData.url;
                dojo.byId("editOsTempHypervisor").innerHTML = resultData.hypervisor;
                dojo.byId("editOsTempFormat").innerHTML = resultData.format;
                dojo.byId("editOsTempOsCategory").innerHTML = resultData.osCategory;
                dojo.byId("editOsTempOsType").innerHTML = resultData.osType;
                dijit.byId("osTemplateReferelUrl").setValue(resultData.referelUrl);
                dojo.byId("currentTemplateName").innerHTML = resultData.name;           
                if(resultData.architecture === "64bit") {
                    dijit.byId("editOSTemp64BitRedio").set("checked", true);
                } else {
                    dijit.byId("editOSTemp32BitRedio").set("checked", true);
                }       
                dojo.forEach(resultData.zone, function(zoneResult) {
                    container = "<span>" + zoneResult.name + "</span>";
                });
            });
            dojo.byId("editOsTempZone").innerHTML = container;
        });
    },
    update: function() {
        
        var name = dijit.byId("dashboardEditOsName");
        var description = dijit.byId("editOsTempDescription");
        var cost = dijit.byId("editOsTempCost");
        var detailDescr = dijit.byId("osTemplateDetailDescEdit").attr("value");
        var extractable = dijit.byId("editTempOsExtractable").checked;
        var passwordEnabled = dijit.byId("editTempOsPasswordEnabled").checked;
        var isPublic = dijit.byId("editTempOsPublic").checked;
        var featured = dijit.byId("editTempOsFeatured").checked;
        
        var referenceURL = dijit.byId("osTemplateReferelUrl");
        
        var editCpuNumber = dijit.byId("editOsTemCpu").value;
        var editCpuMemory = dijit.byId("editOsTemRam").value;
       
        var oneTimeChargeable = dijit.byId("editOsTemOneTime").checked;
       
       var formElements = dojo.query("#osArchDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var architecture = dijit.byId(checkedRadioId).value;
        
       var tempFormElements = dojo.query("#tempTypeDiv input:checked");
       var checkedTempRadioId = dijit.byId(tempFormElements[0].id);        
       var tempType = dijit.byId(checkedTempRadioId).value;
       var imageURL = "";
       if(tempType === "OS") {
           imageURL = "";
       } else {
           imageURL = dijit.byId("osTemplateImageURL").value;
       }
       
       if((oneTimeChargeable == true || oneTimeChargeable == "true") && (cost.value == 0)) {
           registry.byId("appToaster").setContent(translator.common.message.oneTimeChargeTemp, "error");
           registry.byId("appToaster").show();
       } else {       
           dijit.byId("osTemplateEditConformationDialog").hide();
           dojo.query("#viewOSTempLoader").removeClass("hide_text", true);
           dijit.byId("viewOSTempUpdateButton").setAttribute('style', 'display: none');              
           this._tempRestStrore.put({
               id: this._currentId,
               architecture:architecture,
               name: name.value,
               appTempImageUrl : imageURL,
               tempType : tempType,
               minRam: editCpuMemory,
               minCpu: editCpuNumber,
               description: description.value,
               cost: cost.value,
               detailedDescription : detailDescr,
               extractable: extractable,
               passwordEnabled: passwordEnabled,
               isPublic: isPublic,
               featured: featured,
               oneTimeChargeable: oneTimeChargeable,
               referenceURL : referenceURL.value
           }).then(function(results) {
               dijit.byId("osTemplateEditConformationDialog").hide();
               dojo.query("#viewOSTempLoader").toggleClass("hide_text", true);
               dijit.byId("viewOSTempUpdateButton").setAttribute('style', 'display: block');
               if (results == "OK") {
                   registry.byId("appToaster").setContent(translator.common.message.templUpdated, "message");
                   registry.byId("appToaster").show();
                   dijit.byId("osTemplateEditConformationDialog").hide();
                   core.router.go("#/admin/template/osTemplate");
                   dijit.byId("osTempEditForm").reset();
               } else {
                   registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                   registry.byId("appToaster").show();
                   dijit.byId("osTemplateEditConformationDialog").hide();
               }
           });
        }
    }
};

var ViewsAppTemplate = {
    _tempRestStrore: "",
    _tempCountRestStrore: "",
    _currentId: "",
    init: function() {
        this._tempRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/"
        });

        this._tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count/"
        });
    },
    updateShow: function() {
        var cost = dijit.byId("EditAppTempCost");
        
        var pageNode = dojo.byId("appTempEditForm");
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
            if((dojo.byId("editAppTemOneTime").innerHTML == translator.common.yes) && (cost == 0)) {
                registry.byId("appToaster").setContent(translator.common.message.oneTimeChargeTemplate, "error");
                registry.byId("appToaster").show();
            } else {
                dijit.byId("appTemplateEditConformationDialog").show();
            }
        }
    },
    closeUpdate: function() {
        dijit.byId("appTemplateEditConformationDialog").hide();
    },
    populateValues: function(currentId) {
        this._currentId = currentId;
        var container;
        this._tempRestStrore.get(currentId).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dijit.byId("EditAppTempName").setValue(resultData.name);
                dijit.byId("EditAppTempDescription").setValue(resultData.description);
                dijit.byId("EditAppTempCost").setValue(resultData.cost);
                dijit.byId("appTemplateDetailDescEdit").set("value", resultData.detailedDescription)
                dijit.byId("EditAppTempPasswordEnabled").set("checked", resultData.passwordEnabled);
                dijit.byId("EditAppTempExtractable").set("checked", resultData.extractable);
                dijit.byId("EditAppTempPublic").set("checked", resultData.isPublic);
                dijit.byId("EditAppTempFeatured").set("checked", resultData.featured);

                dijit.byId("editAppTemCpu").setValue(resultData.minimumCpu);
                dijit.byId("editAppTemRam").setValue(resultData.minimumRam);
                dijit.byId("appTemplateReferelUrlEdit").setValue(resultData.referelUrl);
                dijit.byId("editAppTemOneTime").set("checked", resultData.oneTimeChargeable);
                dijit.byId("appTemplateImageURL").setValue(resultData.AppTempImageUrl);                
                if(resultData.architecture === "64bit") {
                    dijit.byId("editAppTemp64BitRedio").set("checked", true);
                } else {
                    dijit.byId("editAppTemp32BitRedio").set("checked", true);
                }  
                
                dojo.byId("editAppTempUrl").innerHTML = resultData.url;
                dojo.byId("editAppTempHypervisor").innerHTML = resultData.hypervisor;
                dojo.byId("editAppTempFormat").innerHTML = resultData.format;
                dojo.byId("editAppTempOsCategory").innerHTML = resultData.osCategory;
                dojo.byId("editAppTempOsType").innerHTML = resultData.osType;
                dojo.byId("currentAppTempName").innerHTML = resultData.name;
                dojo.byId("appTempOs").innerHTML = resultData.osType;
                dojo.forEach(resultData.zone, function(zoneResult) {
                    container = "<span>" + zoneResult.name + "</span>";
                });

            });
            dojo.byId("editAppTempZone").innerHTML = container;
        });
    },
    update: function() {
        
        var name = dijit.byId("EditAppTempName");
        var description = dijit.byId("EditAppTempDescription");
        var cost = dijit.byId("EditAppTempCost");
        var detailDescr = dijit.byId("appTemplateDetailDescEdit").attr("value");     
        var extractable = dijit.byId("EditAppTempExtractable").checked;
        var passwordEnabled = dijit.byId("EditAppTempPasswordEnabled").checked;
        var isPublic = dijit.byId("EditAppTempPublic").checked;
        var featured = dijit.byId("EditAppTempFeatured").checked;

        var appTemplateReferelUrlEdit = dijit.byId("appTemplateReferelUrlEdit").value;
        
        var editAppTemCpu = dijit.byId("editAppTemCpu").value;
        var editAppTemRam = dijit.byId("editAppTemRam").value;
        var appTempImageUrl = dijit.byId("appTemplateImageURL").value;
              
        var editAppTemOneTime = dijit.byId("editAppTemOneTime").checked;
       
        var formElements = dojo.query("#appArchDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var architecture = dijit.byId(checkedRadioId).value;

        var appTempRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/"
        });
         if((editAppTemOneTime == true || editAppTemOneTime == "true") && (cost.value == 0)) {
             registry.byId("appToaster").setContent(translator.common.message.oneTimeChargeTemp, "error");
             registry.byId("appToaster").show();                                                                
         } else {
            dojo.query("#viewAppTempLoader").removeClass("hide_text", true);
            dijit.byId("viewAppTempUpdateButton").setAttribute('style', 'display: none');
            dijit.byId("appTemplateEditConformationDialog").hide();

            appTempRestStrore.put({
                id: this._currentId,
                name: name.value,
                architecture:architecture,
                oneTimeChargeable:editAppTemOneTime,
                referenceURL:appTemplateReferelUrlEdit,
                description: description.value,
                minRam: editAppTemRam,
                minCpu: editAppTemCpu,
                detailedDescription : detailDescr,
                cost: cost.value,
                extractable: extractable,
                passwordEnabled: passwordEnabled,
                isPublic: isPublic,
                appTempImageUrl: appTempImageUrl,
                featured: featured
            }).then(function(results) {
                dojo.query("#viewAppTempLoader").toggleClass("hide_text", true);
                dijit.byId("viewAppTempUpdateButton").setAttribute('style', 'display: block');
                if (results == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.templUpdated, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("appTemplateEditConformationDialog").hide();
                    core.router.go("#/admin/template/appTemplate");
                    dijit.byId("appTempEditForm").reset();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.updatedError, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("appTemplateEditConformationDialog").hide();
                }
            });
        }
    }
};

var AddOsTempInfo = {
    _zoneRestStore: "",
    _osRestStore: "",
    init: function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone"
        });

        this._osRestStore = new JsonRest({
            target: core.getContextPath() + "/api/osType/"
        });
    },
    populateValues: function() {
        var osTypeOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.selectOS}]
        };

        var osCategoryOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.selectCategory}]
        };

        var osTypeList = new ItemFileWriteStore({data: osTypeOptions});
        var osCategoryList = new ItemFileWriteStore({data: osCategoryOptions});

        this._osRestStore.query().then(function(osCategorytDate) {
            dojo.forEach(osCategorytDate, function(data) {
                osCategoryList.newItem({id: data.osCategoryId, name: data.osCategoryName});
            });
        });

        var osWidget = new Select({
            id: "osList",
            store: osTypeList,
            maxHeight: -1 // tells _HasDropDown to fit menu within viewport 
        }).placeAt("dashboardTempOsTypeList");
        osWidget.startup();


        var osCategory = new Select({
            id: "osCategoryList",
            store: osCategoryList,
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport 
            onChange: function() {

//                    dojo.byId("computingPodName").innerHTML = this.get("displayedValue");
                var osTypeWidget = dijit.byId("osList");
                var osTypeOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var currentOsList = new ItemFileWriteStore({data: osTypeOptions});
                if (this.value == "option") {
                    currentOsList.newItem({id: "option", name: translator.common.selectOS});
                    osTypeWidget.setStore(currentOsList);
                }
                var osListRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/osType/"
                });
                osListRestStore.get(this.value).then(function(osListDate) {
                    dojo.forEach(osListDate, function(osData) {
                        currentOsList.newItem({id: osData.osId, name: osData.osName});
                        osTypeWidget.setStore(currentOsList);
                    });
                });
            }
        }).placeAt("dashboardTempOsCategoryList");
        osCategory.startup();

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "-1", name: translator.common.allZone}]
        };

        var zoneList = new ItemFileWriteStore({data: zoneOptions});

        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });

        var zoneWidget = new Select({
            id: "osTempzoneWidget",
            name: "test",
            store: zoneList,
            onChange: function() {

                var hyperviserItems = {
                    identifier: "name",
                    label: "name",
                    items: []
                };

                var hyperviserList = new ItemFileWriteStore({data: hyperviserItems});

                var hyperviserRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/hypervisor/"
                });
                hyperviserRestStore.get(zoneWidget.value).then(function(data) {
                    dojo.forEach(data, function(el) {
                        hyperviserList.newItem({id: el.name, name: el.name});
                        //                            hypervisorWidget.set("value", el.name);
                    });
                    dijit.byId("hypervisorTest").setStore(hyperviserList);
                });
            }
        }).placeAt("dashboardTempZone");
//            zoneWidget.startup();      

        var hyperviserItems = {
            identifier: "name",
            label: "name",
            items: [{id: "option", name: translator.common.message.selectHypervisor}]
        };
        var hyperviserList = new ItemFileWriteStore({data: hyperviserItems});
        var hyperviserRestStore = new JsonRest({
            target: core.getContextPath() + "/api/hypervisor/"
        });
        hyperviserRestStore.get("-1").then(function(data) {
            dojo.forEach(data, function(el) {
                hyperviserList.newItem({id: el.name, name: el.name});
            });
            dijit.byId("dashboardTempFormat").set("value","option");
        });
        var hypervisorWidget = new Select({
            id: "hypervisorTest",
            store: hyperviserList,
            maxHeight: -1,
            onChange: function() {
                AddOsTempInfo.loadTemplateFormat(this);
            }
        }, "dashboardTempHypervisor");
        
        
        var formatItems = {
            identifier: "id",
            label: "name",
            items: []
        };

        var formatList = new ItemFileWriteStore({data: formatItems});
        
        var formatWidget = new FilteringSelect({
            id: "dashboardTempFormat",
            store: formatList,
            placeHolder: "Select Format",
            maxHeight: -1
        }, "dashboardTempFormatDiv");        
        
        
    },
    cancel: function() {
        core.router.go("#/admin/template/osTemplate");
    },
    loadTemplateFormat : function(currentHyper) {
        
        var formatItems = {
            identifier: "id",
            label: "name",
            items: []
        };

        var formatList = new ItemFileWriteStore({data: formatItems});
        
        if(currentHyper.value == "XenServer") {
            formatList.newItem({id: "VHD", name: "VHD"});
            dijit.byId("dashboardTempFormat").set("store", formatList);
            dijit.byId("dashboardTempFormat").set("value", "VHD");
        } else if(currentHyper.value == "KVM") {
            formatList.newItem({id: "QCOW2", name: "QCOW2"});
            dijit.byId("dashboardTempFormat").set("store", formatList);
            dijit.byId("dashboardTempFormat").set("value", "QCOW2");
        } else {
            formatList.newItem({id: "option", name: translator.common.message.noHypervisor});
            dijit.byId("dashboardTempFormat").set("store", formatList);
            dijit.byId("dashboardTempFormat").set("value", "option");
        }
        
    },
    add: function() {                
        var pageNode = dojo.byId("addOsTempPage");
        
            var formElements = dojo.query("#osArchDiv input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var architecture = dijit.byId(checkedRadioId).value;
            
            var name = dijit.byId("dashboardTempName");
            var description = dijit.byId("dashboardTempDescription");
            var url = dijit.byId("dashboardTempURL");
            var cost = dijit.byId("osTemplateCost");
            var zone = dijit.byId("osTempzoneWidget").value;
            var hypervisor = dijit.byId("hypervisorTest").value;
            var detailDesc = dijit.byId("osTemplateDetailDesc").attr("value");
            var referenceURL = dijit.byId("dashboardTempReferenceURL");
            
            var format = dijit.byId("dashboardTempFormat").get('displayedValue');
            var formatValue = dijit.byId("dashboardTempFormat").value;
            
            var osTypeId = dijit.byId("osList").value;
            var osCategoryId = dijit.byId("osCategoryList").value;
            
            var extractable = dijit.byId("dashboardTempExtractable").checked;

            var passwordEnabled = dijit.byId("dashboardTempPasswordEnabled").checked;
            var isPublic = dijit.byId("dashboardTempPublic").checked;
            var featured = dijit.byId("dashboardTempFeatured").checked;

            var cpuNumber = dijit.byId("minCpu");
            var cpuMemory = dijit.byId("minRam");
            var oneTimeChargeable = dijit.byId("oneTimeChargeable").checked;
            
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
            } else if(hypervisor == "option" || format == "" || formatValue == "option" || osCategoryId == "option" || osTypeId == "option") {
                dojo.byId("addOSTemplateErrorMsg").style.display = "block"
            } else {            
                dojo.byId("addOSTemplateErrorMsg").style.display = "none"            
                if((oneTimeChargeable == true || oneTimeChargeable == "true") && (cost == 0)) {
                    registry.byId("appToaster").setContent(translator.common.message.oneTimeChargeTemp, "error");
                    registry.byId("appToaster").show();
                } else {
                    this._tempRestStrore = new JsonRest({
                        target: core.getContextPath() + "/api/template/"
                    });
                    dijit.byId("osTempButton").setAttribute('style', 'display: none');
                    dojo.byId("osTempLoader").style.display = "block";
                    this._tempRestStrore.add({
                        name: name.value,
                        appTemplate: false,
                        myTemplate: false,
                        architecture : architecture,
                        description: description.value,
                        detailedDesc : detailDesc,
                        referenceURL : referenceURL.value,
                        url: url.value,
                        cost: cost.value,
                        zone: zone,
                        hypervisor: hypervisor,
                        format: format,
                        osType: osTypeId,
                        extractable: extractable,
                        passwordEnabled: passwordEnabled,
                        isPublic: isPublic,
                        featured: featured,
                        minRam: cpuMemory.value,
                        minCpu: cpuNumber.value,
                        oneTimeChargeable: oneTimeChargeable
                    }).then(function(results) {
                        dijit.byId("osTempButton").setAttribute('style', 'display: block');
                        dojo.byId("osTempLoader").style.display = "none";
                        if (results == "OK") {
                            registry.byId("appToaster").setContent(translator.common.message.created, "message");
                            registry.byId("appToaster").show();
                            core.router.go("#/admin/template/osTemplate");
                        } else {
                            registry.byId("appToaster").setContent(translator.common.message.createdError, "error");
                            registry.byId("appToaster").show();
                        }
                    });
                }
            }
        }
    };

var DeleteTemplate = {
    init: function() {
        this._tempDeleteRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/delete"
        });

        this._tempDeleteJobRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/deleteJob"
        });
    },
    populateValues: function(currentTempId, currentTemplate) {
        this._tempDeleteRestStrore.add({
            tempId: currentTempId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result == "OK") {
                    var tempStopJobStatus = setInterval(function() {
                        DeleteTemplate.templateJob(resultData.jobId, tempStopJobStatus, currentTemplate);
                    }, 3000);
                } else {
                    registry.byId("appToaster").setContent(translator.common.template.deleteTempFailed, "error");
                    registry.byId("appToaster").show();
                    if (currentTemplate == "osTemp") {
                        dijit.byId("deleteOsTempButton").setAttribute('style', 'display: inline');
                        dijit.byId("deleteOsTempCancelButton").setAttribute('style', 'display: inline');
                        dojo.byId("deleteOsTempLoader").style.display = "none";
                        dijit.byId("deleteOsTemplateDialog").hide();
                    } else if (currentTemplate == "appTemp") {
                        dijit.byId("deleteAppTempButton").setAttribute('style', 'display: inline');
                        dijit.byId("deleteAppTempCancelButton").setAttribute('style', 'display: inline');
                        dojo.byId("deleteAppTempLoader").style.display = "none";
                        dijit.byId("deleteAppTemplateDialog").hide();
                    }
                }
            });
        });
    },
    templateJob: function(jobId, tempStopJobStatus, currentTemplate) {
        var tempDeleteJobRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/deleteJob"
        });

        tempDeleteJobRestStrore.add({
            jobId: jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.jobResult == "OK") {
                    registry.byId("appToaster").setContent(translator.common.template.deleteTempSuccess, "message");
                    registry.byId("appToaster").show();
                    clearInterval(tempStopJobStatus);
                    if (currentTemplate == "appTemp") {
                        AppTemplateInfo.init();
                        AppTemplateInfo.populateValues();
                    } else if (currentTemplate == "osTemp") {
                        OsTemplateInfo.init();
                        OsTemplateInfo.populateValues();
                    } else if (currentTemplate == "userTemp") {
                        core.router.go("#/admin/template/userTemplate");
                    }
                } else if (resultData.jobResult == "Pending") {

                } else if (resultData.jobResult == "FAILED") {
                    registry.byId("appToaster").setContent(translator.common.message.cannotDelete, "error");
                    registry.byId("appToaster").show();
                    clearInterval(tempStopJobStatus);
                }

                if (resultData.jobResult == "OK" || resultData.jobResult == "FAILED" || resultData.jobResult == 'undefined' || resultData.jobResult == " ") {
                    if (currentTemplate == "osTemp") {
                        dijit.byId("deleteOsTempButton").setAttribute('style', 'display: inline');
                        dijit.byId("deleteOsTempCancelButton").setAttribute('style', 'display: inline');
                        dojo.byId("deleteOsTempLoader").style.display = "none";
                        dijit.byId("deleteOsTemplateDialog").hide();
                    } else if (currentTemplate == "appTemp") {
                        dijit.byId("deleteAppTempButton").setAttribute('style', 'display: inline');
                        dijit.byId("deleteAppTempCancelButton").setAttribute('style', 'display: inline');
                        dojo.byId("deleteAppTempLoader").style.display = "none";
                        dijit.byId("deleteAppTemplateDialog").hide();
                    }
                }
            });
        });
    }
};

var OsTemplateInfo = {
    _zoneRestStore: "",
    _osTypeRestStore: "",
    _tempRestStrore: "",
    _tempCountRestStrore: "",
    name: "",
    description: "",
    url: "",
    zone: "",
    hypervisor: "",
    format: "",
    osTypeId: "",
    extractable: "",
    passwordEnabled: "",
    isPublic: "",
    featured: "",
    hyperviserItems: "",
    init: function() {
        this._tempRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/"
        });

        this._tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("tempOsTempCurrencyValue").innerHTML = cur.currency;
            });
        });
    },
    deleteOsTempConfirm: function(currentTemplete) {
        dojo.byId("currentOsTempId").value = currentTemplete;
        dijit.byId("deleteOsTemplateDialog").show();
    },
    deleteOsTemp: function() {
        var currentTempId = dojo.byId("currentOsTempId").value;
        dijit.byId("deleteOsTempButton").setAttribute('style', 'display: none');
        dijit.byId("deleteOsTempCancelButton").setAttribute('style', 'display: none');
        dojo.byId("deleteOsTempLoader").style.display = "inline";
        DeleteTemplate.init();
        DeleteTemplate.populateValues(currentTempId, "osTemp");

    },
    closeDeleteDialog: function() {
        dijit.byId("deleteOsTemplateDialog").hide();
    },
    conformPull: function () {
        dijit.byId("pullTemplateConform").show();
    },
    cancelPullTemplate: function () {
        dijit.byId("pullTemplateConform").hide();
    },
    pullTemplate : function() {
        
        dijit.byId("pullTemplateLoader").show();
        dijit.byId("pullTemplateConform").hide();
        
        var pullPlanRestStore = new JsonRest({
           target: core.getContextPath()+"/api/template/pullFromCloudStack"
        });
        
        pullPlanRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    OsTemplateInfo.populateValues();
                    registry.byId("appToaster").setContent(translator.common.message.pullTemplateSuccess, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("pullTemplateLoader").hide();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("pullTemplateLoader").hide();
                }
            });
        });
        
    },
    populateValues: function() {

        if (dijit.byId("adminOsTempDataGrid")) {
            dijit.byId("adminOsTempDataGrid").destroyRecursive();
        }
        if (dijit.byId("adminOsTempZoneWidget")) {
            dijit.byId("adminOsTempZoneWidget").destroyRecursive();
        }

        dojo.byId("adminInstanceInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.options.allZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });
        var zoneWidget = new Select({
            id: "adminOsTempZoneWidget",
            store: zoneList,
            onChange: function() {
                OsTemplateInfo.getVMByZone(this);
            }
        }).placeAt("adminOsTempZoneList");
//        zoneWidget.startup();
        var data = {
            items: []
        };
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        var dataLayout  = "";
        var dataList = new ItemFileWriteStore({data: data});
        configRestStore.query().then(function (currencyResult) {
            dataLayout = [
                [ 
                    {'field': 'id', 'hidden': 'true'},
                    {'name': translator.common.template.grid.templateName, 'field': 'name', 'width': '200px', datatype: "string", autoComplete: true,
                        'formatter': function(data) {
                            var os = data.split(",");
                            switch (os[0]) {
                                case "CentOS":
                                    return "<img src='images/os/centos.JPG' alt='"+translator.common.template.centOS+"' height='25' width='25'/>" + os[1];
                                case "Debian":
                                    return "<img src='images/os/debian.JPG' alt='"+translator.common.template.debian+"' height='25' width='25'/>" + os[1];
                                case "Oracle":
                                    return "<img src='images/os/oracle.JPG' alt='"+translator.common.template.oracle+"' height='25' width='25'/>" + os[1];
                                case "RedHat":
                                    return "<img src='images/os/redhat.JPG' alt='"+translator.common.template.redHat+"' height='25' width='25'/>" + os[1];
                                case "SUSE":
                                    return "<img src='images/os/suse.JPG' alt='"+translator.common.template.SUSE+"' height='25' width='25'/>" + os[1];
                                case "Windows":
                                    return "<img src='images/os/windows.JPG' alt='"+translator.common.template.windows+"' height='25' width='25'/>" + os[1];
                                case "Novel":
                                    return "<img src='images/os/novel.JPG' alt='"+translator.common.template.novel+"' height='25' width='25'/>" + os[1];
                                case "Unix":
                                    return "<img src='images/os/uinx.JPG' alt='"+translator.common.template.unix+"' height='25' width='25'/>" + os[1];
                                case "Ubuntu":
                                    return "<img src='images/os/ubuntu.JPG' alt='"+translator.common.template.ubuntu+"' height='25' width='25'/>" + os[1];
                            }
                        }
                    },
                    {'name': translator.common.operatinSystem, 'field': 'os', 'width': '100px', datatype: "string", autoComplete: true},
                    {'name': translator.common.template.grid.osType, 'field': 'osType', 'width': '200px', datatype: "string", autoComplete: true},
                    {'name': translator.common.zone, 'field': 'zone', 'width': '100px', datatype: "string", autoComplete: true},
                    {'name': translator.common.hypervisor, 'field': 'hypervisor', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                    {'name': translator.common.cost  + " ("+ currencyResult[0].currency +")", 'field': 'cost', 'width': '100px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                            var html = "<span class='fog_cost'>" + data + "</span>"
                            return html;
                        }
                    },
                    {'name': translator.common.status.name, 'field': 'status', 'width': '100px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                            var resultData = data.split(",")
                            var imageDiv = domConstruct.create("div", {innerHTML: "<img src='images/vmload.gif' alt='loading'/>"});
                            var status;
                            if (resultData[0] == "Download Complete") {
                                status = "Ready"
                            } else {
                                status = resultData[0];
                            }
                            return status;
                        }
                    },
                    {'name': translator.common.action, 'width': '100%', 'field': 'action',
                        'formatter': function(data) {
                            var dataList = data.split(",");
                            var html;
    //                         if(dataList[1] == true || dataList[1] == 'true') {                             
                            html = "<a class='btn-flat success' href='#/admin/template/viewOsTemp/" + dataList[0] + "'>"+translator.common.edit+"</a>" +
                                    "<a class='btn-flat danger spacing' onclick='OsTemplateInfo.deleteOsTempConfirm(" + dataList[0] + ")'>"+translator.common.deleteData+"</a>";
    //                             
    //                         } else {                            
    //                             html = "";
    //                         }
                            return html;
                        }
                    }
                ]
            ];
        });
        

        this._tempCountRestStrore.query().then(function(data) {
            if (data[0].tempData.length == 0 || data[0].tempData == undefined || data[0].tempData == 'undefined' || data[0].tempData == '' || data[0].tempData == ' ') {
                dojo.byId("noOsTempMessageBox").style.display = "block";
                dojo.byId("adminInstanceInfo").innerHTML = "";

                dojo.byId("adminTotalOsTemp").innerHTML = 0;
                dojo.byId("adminTotalLinuxTemp").innerHTML = 0;
                dojo.byId("adminTotalWindowsTemp").innerHTML = 0;
            } else {
                dojo.byId("noOsTempMessageBox").style.display = "none";
                dojo.forEach(data, function(data) {
                    dojo.byId("adminTotalOsTemp").innerHTML = data.totalTemplates;
                    dojo.byId("adminTotalLinuxTemp").innerHTML = data.linuxTotal;
                    dojo.byId("adminTotalWindowsTemp").innerHTML = data.windowsTotal;
                    dojo.forEach(data.tempData, function(temp) {
                        var cost = "";
                        if(temp.oneTimeCharge === true) {
                            cost = localeCurrency.format(temp.cost) + "/" + translator.common.oneTime;
                        } else {
                            cost = localeCurrency.format(temp.cost) + "/" + translator.common.month;
                        }
                        if (temp.status == " " || temp.status == "" || temp.status == "undefined" || temp.status == undefined) {                            
                            dataList.newItem({
                                id: temp.id,
                                name: temp.osCategory + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osType,
                                cost: cost,
                                hypervisor:temp.hypervisor,
                                action: temp.id + "," + temp.isReady,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.osCategory + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osType,
                                cost: cost,
                                hypervisor:temp.hypervisor,
                                action: temp.id + "," + temp.isReady,
                                status: temp.status + "," + temp.isReady
                            });
                        }
                    });
                });
                dojo.byId("adminInstanceInfo").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: 'adminOsTempDataGrid',
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminInstanceInfo");
                dataGrid.startup();
            }
        });
    },
    getVMByZone: function(currentZone) {
        var formElements = dojo.query("#adminOSTempActionForm input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var currentStatus = dijit.byId(checkedRadioId).value;

        var tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });

        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("adminOsTempDataGrid");
        var dataList = new ItemFileWriteStore({data: dataInfo});
        if (currentZone.value == "option" && currentStatus == "All") {
            tempCountRestStrore.query().then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData) {
                        dojo.byId("adminTotalOsTemp").innerHTML = resultData.totalTemplates;
                        dojo.byId("adminTotalLinuxTemp").innerHTML = resultData.linuxTotal;
                        dojo.byId("adminTotalWindowsTemp").innerHTML = resultData.windowsTotal;
                    } else {
                        dojo.byId("adminTotalOsTemp").innerHTML = 0;
                        dojo.byId("adminTotalLinuxTemp").innerHTML = 0;
                        dojo.byId("adminTotalWindowsTemp").innerHTML = 0;
                    }

                    dojo.forEach(resultData.tempData, function(temp) {
                        if (temp.status == " " || temp.status == "" || temp.status == "undefined" || temp.status == undefined) {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.osCategory + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osType,
                                cost: localeCurrency.format(temp.cost),
                                hypervisor:temp.hypervisor,
                                action: temp.id + "," + temp.isReady,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.osCategory + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osType,
                                cost: localeCurrency.format(temp.cost),
                                hypervisor:temp.hypervisor,
                                action: temp.id + "," + temp.isReady,
                                status: temp.status + "," + temp.isReady
                            });
                        }

                    });
                    grid.setStore(dataList)
                });
            });

        } else if (currentZone.value == "option" && currentStatus != "All") {
            tempCountRestStrore.query({baseOs: currentStatus}).then(function(data) {
                dojo.forEach(data, function(el) {
                    if (el) {
                        dojo.byId("adminTotalOsTemp").innerHTML = el.totalTemplates;
                        dojo.byId("adminTotalLinuxTemp").innerHTML = el.linuxTotal;
                        dojo.byId("adminTotalWindowsTemp").innerHTML = el.windowsTotal;
                    } else {
                        dojo.byId("adminTotalOsTemp").innerHTML = 0;
                        dojo.byId("adminTotalLinuxTemp").innerHTML = 0;
                        dojo.byId("adminTotalWindowsTemp").innerHTML = 0;
                    }

                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }

                    });
                    grid.setStore(dataList);
                });
            });

        } else {
            if (currentStatus == "All") {
                tempCountRestStrore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        if (el) {
                            dojo.byId("adminTotalOsTemp").innerHTML = el.totalTemplates;
                            dojo.byId("adminTotalLinuxTemp").innerHTML = el.linuxTotal;
                            dojo.byId("adminTotalWindowsTemp").innerHTML = el.windowsTotal;
                        } else {
                            dojo.byId("adminTotalOsTemp").innerHTML = 0;
                            dojo.byId("adminTotalLinuxTemp").innerHTML = 0;
                            dojo.byId("adminTotalWindowsTemp").innerHTML = 0;
                        }

                        dojo.forEach(el.tempData, function(tempData) {
                            if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.osCategory + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    hypervisor:tempData.hypervisor,
                                    action: tempData.id + "," + tempData.isReady,
                                    status: translator.common.message.connecting + "," + translator.common.falseData
                                });
                            } else {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.osCategory + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    hypervisor:tempData.hypervisor,
                                    action: tempData.id + "," + tempData.isReady,
                                    status: tempData.status + "," + tempData.isReady
                                });
                            }

                        });
                        grid.setStore(dataList);
                    });
                });

            } else {
                tempCountRestStrore.query({zoneReferenceId: currentZone.value, baseOs: currentStatus}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        if (el) {
                            dojo.byId("adminTotalOsTemp").innerHTML = el.totalTemplates;
                            dojo.byId("adminTotalLinuxTemp").innerHTML = el.linuxTotal;
                            dojo.byId("adminTotalWindowsTemp").innerHTML = el.windowsTotal;
                        } else {
                            dojo.byId("adminTotalOsTemp").innerHTML = 0;
                            dojo.byId("adminTotalLinuxTemp").innerHTML = 0;
                            dojo.byId("adminTotalWindowsTemp").innerHTML = 0;
                        }

                        dojo.forEach(el.tempData, function(tempData) {
                            if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.osCategory + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    hypervisor:tempData.hypervisor,
                                    action: tempData.id + "," + tempData.isReady,
                                    status: translator.common.message.connecting + "," + translator.common.falseData
                                });
                            } else {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.osCategory + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    hypervisor:tempData.hypervisor,
                                    action: tempData.id + "," + tempData.isReady,
                                    status: tempData.status + "," + tempData.isReady
                                });
                            }

                        });
                        grid.setStore(dataList);
                    });
                });

            }
        }
    },
    showTemplate: function(currentRadio) {
        var tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });
        var tempRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template"
        });
        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("adminOsTempDataGrid")
        var dataList = new ItemFileWriteStore({data: dataInfo});
        var zoneWidget = dijit.byId("adminOsTempZoneWidget");
        if (zoneWidget.value == "option" && currentRadio.value == "All") {
            tempCountRestStrore.query().then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }


                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value == "option" && currentRadio.value != "All") {
            tempCountRestStrore.query({baseOs: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: tempData.cost,
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }

                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value != "option" && currentRadio.value != "All") {
            tempCountRestStrore.query({zoneReferenceId: zoneWidget.value, baseOs: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }

                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value != "option" && currentRadio.value == "All") {
            tempCountRestStrore.query({zoneReferenceId: zoneWidget.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {

                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                hypervisor:tempData.hypervisor,
                                cost: localeCurrency.format(tempData.cost),
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.osCategory + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }
                    });
                    grid.setStore(dataList);
                });
            });
        }
    }

};



var AppTemplateInfo = {
    _zoneRestStore: "",
    _osTypeRestStore: "",
    _tempRestStrore: "",
    _tempCountRestStrore: "",
    name: "",
    description: "",
    url: "",
    zone: "",
    hypervisor: "",
    format: "",
    osTypeId: "",
    extractable: "",
    passwordEnabled: "",
    isPublic: "",
    featured: "",
    hyperviserItems: "",
    init: function() {
        this._tempRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/"
        });

        this._tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("appTempCurrencyValue").innerHTML = cur.currency;
            });
        });
    },
    deleteAppTempConfirm: function(currentTemplete) {
        dojo.byId("currentAppTempId").value = currentTemplete;
        dijit.byId("deleteAppTemplateDialog").show();
    },
    deleteOsTemp: function() {
        var currentTempId = dojo.byId("currentAppTempId").value;
        dijit.byId("deleteAppTempButton").setAttribute('style', 'display: none');
        dijit.byId("deleteAppTempCancelButton").setAttribute('style', 'display: none');
        dojo.byId("deleteAppTempLoader").style.display = "inline"
        DeleteTemplate.init();
        DeleteTemplate.populateValues(currentTempId, "appTemp");

    },
    closeDeleteDialog: function() {
        dijit.byId("deleteAppTemplateDialog").hide();
    },
    populateValues: function() {
        if (dijit.byId("adminAppTempZoneWidget") && dijit.byId("adminAppTemplateGrid")) {
            dijit.byId("adminAppTempZoneWidget").destroyRecursive();
            dijit.byId("adminAppTemplateGrid").destroyRecursive();
        }
        if (dijit.byId("adminAppTempZoneWidget")) {
            dijit.byId("adminAppTempZoneWidget").destroyRecursive();
        }
        dojo.byId("adminAppTemplateInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>"
        dojo.byId("adminTotalAppTemp").innerHTML = 0;
        dojo.byId("adminTotalAppLinuxTemp").innerHTML = 0;
        dojo.byId("adminTotalAppWindowsTemp").innerHTML = 0;
        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "All Zones"}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName})
            });
        });
        var zoneWidget = new Select({
            id: "adminAppTempZoneWidget",
            store: zoneList,
            onChange: function() {
                AppTemplateInfo.getVMByZone(this);
            }
        }, "adminAppTempZoneList");
        var data = {
            items: []
        };
        var dataList = new ItemFileWriteStore({data: data});
       
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        var dataLayout = "";
        configRestStore.query().then(function (currencyResult) {
            
            dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.template.grid.templateName, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {
                        var TemplateInfo = data.split(",");
                        return "<img src='" + TemplateInfo[0] + "' alt='Image Loading' height='25' width='25'/>" + TemplateInfo[1];
                    }},
                {'name': translator.common.operatinSystem, 'field': 'os', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.template.grid.osType, 'field': 'osType', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {
                        var os = data.split(",");
                        switch (os[0]) {
                            case "CentOS":
                                return "<img src='images/os/centos.JPG' alt='"+translator.common.template.centOS+"' height='25' width='25'/>" + os[1];
                            case "Debian":
                                return "<img src='images/os/debian.JPG' alt='"+translator.common.template.debian+"' height='25' width='25'/>" + os[1];
                            case "Oracle":
                                return "<img src='images/os/oracle.JPG' alt='"+translator.common.template.oracle+"' height='25' width='25'/>" + os[1];
                            case "RedHat":
                                return "<img src='images/os/redhat.JPG' alt='"+translator.common.template.redHat+"' height='25' width='25'/>" + os[1];
                            case "SUSE":
                                return "<img src='images/os/suse.JPG' alt='"+translator.common.template.SUSE+"' height='25' width='25'/>" + os[1];
                            case "Windows":
                                return "<img src='images/os/windows.JPG' alt='"+translator.common.template.windows+"' height='25' width='25'/>" + os[1];
                            case "Novel":
                                return "<img src='images/os/novel.JPG' alt='"+translator.common.template.novel+"' height='25' width='25'/>" + os[1];
                            case "Unix":
                                return "<img src='images/os/uinx.JPG' alt='"+translator.common.template.unix+"' height='25' width='25'/>" + os[1];
                            case "Ubuntu":
                                return "<img src='images/os/ubuntu.JPG' alt='"+translator.common.template.ubuntu+"' height='25' width='25'/>" + os[1];
                        }
                    }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.hypervisor, 'field': 'hypervisor', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.cost  + " ("+ currencyResult[0].currency +")", 'field': 'cost', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {
                        var html = "<span class='fog_cost'>" + data + "</span>"
                        return html;
                    }},
                {'name': translator.common.status.name, 'field': 'status', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {
                        var resultData = data.split(",");
                        var imageDiv = domConstruct.create("div", {innerHTML: "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"'/>"});
                        var status; 
                        if (resultData[0] == "Download Complete") {
                            status = "Ready";
                        } else {
                            status = resultData[0];
                        }
                        return status;
                    }
                },
                {'name': translator.common.action, 'width': '100%', 'field': 'action', 'datatype': 'string', 'autoComplete': 'true',
                    'formatter': function(data) {
                        var dataList = data.split(",");
                        var html;
//                         if(dataList[1] == true || dataList[1] == 'true') {                             
                        html = "<a class='btn-flat success' href='#/admin/template/viewAppTemp/" + dataList[0] + "'>"+translator.common.edit+"</a>" +
                                "<a class='btn-flat danger spacing' onclick='AppTemplateInfo.deleteAppTempConfirm(" + dataList[0] + ")'>"+translator.common.deleteData+"</a>";
//                         } else {                            
//                             html = "";
//                         }
                        return html;
                    }
                }
            ]
        ];
            
        });        

        this._tempCountRestStrore.query({appTemplate: true}).then(function(resultData) {
            if (resultData[0].tempData.length == 0 || resultData[0].tempData == undefined || resultData[0].tempData == 'undefined' || resultData[0].tempData == '' || resultData[0].tempData == " ") {
                dojo.byId("adminAppTemplateInfo").innerHTML = " ";
                dojo.byId("noAppTempMessageBox").style.display = "block";
            } else {
                dojo.byId("noAppTempMessageBox").style.display = "none";
                dojo.forEach(resultData, function(data) {
                    dojo.byId("adminTotalAppTemp").innerHTML = data.totalTemplates;
                    dojo.byId("adminTotalAppLinuxTemp").innerHTML = data.linuxTotal;
                    dojo.byId("adminTotalAppWindowsTemp").innerHTML = data.windowsTotal;
                    dojo.forEach(data.tempData, function(temp) {
                        var cost = "";
                        if(temp.oneTimeCharge === true) {
                            cost = localeCurrency.format(temp.cost) + "/" + translator.common.oneTime;
                        } else {
                            cost = localeCurrency.format(temp.cost) + "/" + translator.common.month;
                        }
                        if (temp.status == " " || temp.status == "" || temp.status == "undefined" || temp.status == undefined) {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.path + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osCategory + "," + temp.osType,
                                cost: cost,
                                hypervisor:temp.hypervisor,
                                action: temp.id + "," + temp.isReady,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.path + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osCategory + "," + temp.osType,
                                cost: cost,
                                hypervisor:temp.hypervisor,
                                action: temp.id + "," + temp.isReady,
                                status: temp.status + "," + temp.isReady
                            });
                        }
                    });
                });
                dojo.byId("adminAppTemplateInfo").innerHTML = " ";
                var dataGrid = new EnhancedGrid({
                    id: 'adminAppTemplateGrid',
                    "class" : "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminAppTemplateInfo");
                dataGrid.startup();
            }
        });
    },
    getVMByZone: function(currentZone) {
        var formElements = dojo.query("#adminAppTempActionForm input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var currentStatus = dijit.byId(checkedRadioId).value;

        var tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });

        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("adminAppTemplateGrid");
        var dataList = new ItemFileWriteStore({data: dataInfo});
        if (currentZone.value == "option" && currentStatus == "All") {
            tempCountRestStrore.query({appTemplate: true}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData) {
                        dojo.byId("adminTotalAppTemp").innerHTML = resultData.totalTemplates;
                        dojo.byId("adminTotalAppLinuxTemp").innerHTML = resultData.linuxTotal;
                        dojo.byId("adminTotalAppWindowsTemp").innerHTML = resultData.windowsTotal;
                    } else {
                        dojo.byId("adminTotalAppTemp").innerHTML = 0;
                        dojo.byId("adminTotalAppTemp").innerHTML = 0;
                        dojo.byId("adminTotalAppTemp").innerHTML = 0;
                    }

                    dojo.forEach(resultData.tempData, function(temp) {
                        if (temp.status == " " || temp.status == "" || temp.status == "undefined" || temp.status == undefined) {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.path + "," + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osCategory + "," + temp.osType,
                                cost: localeCurrency.format(temp.cost),
                                hypervisor:temp.hypervisor,
                                action: temp.id + "," + temp.isReady,
                                status: translator.common.message.connecting + "," + translator.common.falseData
                            });
                        } else {
                            dataList.newItem({
                                id: temp.id,
                                name: temp.path + "," + temp.name,
                                os: temp.baseOs,
                                zone: temp.zoneName,
                                osType: temp.osCategory + "," + temp.osType,
                                cost: localeCurrency.format(temp.cost),
                                hypervisor:temp.hypervisor,
                                action: temp.id + "," + temp.isReady,
                                status: temp.status + "," + temp.isReady
                            });
                        }

                    });
                    grid.setStore(dataList);
                });
            });

        } else if (currentZone.value == "option" && currentStatus != "All") {
            tempCountRestStrore.query({appTemplate: true, baseOs: currentStatus}).then(function(data) {
                dojo.forEach(data, function(el) {
                    if (el) {
                        dojo.byId("adminTotalAppTemp").innerHTML = el.totalTemplates;
                        dojo.byId("adminTotalAppLinuxTemp").innerHTML = el.linuxTotal;
                        dojo.byId("adminTotalAppWindowsTemp").innerHTML = el.windowsTotal;
                    } else {
                        dojo.byId("adminTotalAppTemp").innerHTML = 0;
                        dojo.byId("adminTotalAppTemp").innerHTML = 0;
                        dojo.byId("adminTotalAppTemp").innerHTML = 0;
                    }

                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }

                    });
                    grid.setStore(dataList);
                });
            });

        } else {
            if (currentStatus == "All") {
                tempCountRestStrore.query({zoneReferenceId: currentZone.value, appTemplate: true}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        if (el) {
                            dojo.byId("adminTotalAppTemp").innerHTML = el.totalTemplates;
                            dojo.byId("adminTotalAppLinuxTemp").innerHTML = el.linuxTotal;
                            dojo.byId("adminTotalAppWindowsTemp").innerHTML = el.windowsTotal;
                        } else {
                            dojo.byId("adminTotalAppTemp").innerHTML = 0;
                            dojo.byId("adminTotalAppTemp").innerHTML = 0;
                            dojo.byId("adminTotalAppTemp").innerHTML = 0;
                        }

                        dojo.forEach(el.tempData, function(tempData) {
                            if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.path + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osCategory + "," + tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    hypervisor:tempData.hypervisor,
                                    action: tempData.id + "," + tempData.isReady,
                                    status: translator.common.message.connecting + "," + tempData.isReady
                                });
                            } else {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.path + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osCategory + "," + tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    hypervisor:tempData.hypervisor,
                                    action: tempData.id + "," + tempData.isReady,
                                    status: tempData.status + "," + tempData.isReady
                                });
                            }

                        });
                        grid.setStore(dataList);
                    });
                });

            } else {
                tempCountRestStrore.query({zoneReferenceId: currentZone.value, baseOs: currentStatus, appTemplate: true}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        if (el) {
                            dojo.byId("adminTotalAppTemp").innerHTML = el.totalTemplates;
                            dojo.byId("adminTotalAppLinuxTemp").innerHTML = el.linuxTotal;
                            dojo.byId("adminTotalAppWindowsTemp").innerHTML = el.windowsTotal;
                        } else {
                            dojo.byId("adminTotalAppTemp").innerHTML = 0;
                            dojo.byId("adminTotalAppTemp").innerHTML = 0;
                            dojo.byId("adminTotalAppTemp").innerHTML = 0;
                        }

                        dojo.forEach(el.tempData, function(tempData) {
                            if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.path + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osCategory + "," + tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    hypervisor:tempData.hypervisor,
                                    action: tempData.id + "," + tempData.isReady,
                                    status: translator.common.message.connecting  + "," + tempData.isReady
                                });
                            } else {
                                dataList.newItem({
                                    id: tempData.id,
                                    name: tempData.path + "," + tempData.name,
                                    os: tempData.baseOs,
                                    zone: tempData.zoneName,
                                    osType: tempData.osCategory + "," + tempData.osType,
                                    cost: localeCurrency.format(tempData.cost),
                                    hypervisor:tempData.hypervisor,
                                    action: tempData.id + "," + tempData.isReady,
                                    status: tempData.status + "," + tempData.isReady
                                });
                            }

                        });
                        grid.setStore(dataList);
                    });
                });

            }
        }
    },
    showTemplate: function(currentRadio) {
        var tempCountRestStrore = new JsonRest({
            target: core.getContextPath() + "/api/template/count"
        });

        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("adminAppTemplateGrid");
        var dataList = new ItemFileWriteStore({data: dataInfo});
        var zoneWidget = dijit.byId("adminAppTempZoneWidget");
        if (zoneWidget.value == "option" && currentRadio.value == "All") {
            tempCountRestStrore.query({appTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting  + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }


                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value == "option" && currentRadio.value != "All") {
            tempCountRestStrore.query({baseOs: currentRadio.value, appTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting  + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }

                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value != "option" && currentRadio.value != "All") {
            tempCountRestStrore.query({zoneReferenceId: zoneWidget.value, baseOs: currentRadio.value, appTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {
                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting  + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }

                    });
                    grid.setStore(dataList);
                });
            });
        } else if (zoneWidget.value != "option" && currentRadio.value == "All") {
            tempCountRestStrore.query({zoneReferenceId: zoneWidget.value, appTemplate: true}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.tempData, function(tempData) {

                        if (tempData.status == " " || tempData.status == "" || tempData.status == "undefined" || tempData.status == undefined) {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: translator.common.message.connecting  + "," + tempData.isReady
                            });
                        } else {
                            dataList.newItem({
                                id: tempData.id,
                                name: tempData.path + "," + tempData.name,
                                os: tempData.baseOs,
                                zone: tempData.zoneName,
                                osType: tempData.osCategory + "," + tempData.osType,
                                cost: localeCurrency.format(tempData.cost),
                                hypervisor:tempData.hypervisor,
                                action: tempData.id + "," + tempData.isReady,
                                status: tempData.status + "," + tempData.isReady
                            });
                        }
                    });
                    grid.setStore(dataList);
                });
            });
        }
    }
};
var AddAppTemplateInfo = {
    _zoneRestStore: "",
    _osRestStore: "",
    init: function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone"
        });

        this._osRestStore = new JsonRest({
            target: core.getContextPath() + "/api/osType/"
        });
    },
    populateValues: function() {
        var osTypeOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.selectOS}]
        };

        var osCategoryOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.selectCategory}]
        };

        var osTypeList = new ItemFileWriteStore({data: osTypeOptions});
        var osCategoryList = new ItemFileWriteStore({data: osCategoryOptions});

        this._osRestStore.query().then(function(osCategorytDate) {
            dojo.forEach(osCategorytDate, function(data) {
                osCategoryList.newItem({id: data.osCategoryId, name: data.osCategoryName});
            });
        });

        var osWidget = new Select({
            id: "appOsList",
            store: osTypeList,
            maxHeight: -1 // tells _HasDropDown to fit menu within viewport 
        }).placeAt("appTempOsTypeList");
        osWidget.startup();


        var osCategory = new Select({
            id: "appOsCategoryList",
            store: osCategoryList,
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport 
            onChange: function() {
                var osTypeWidget = dijit.byId("appOsList");
                var osTypeOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var currentOsList = new ItemFileWriteStore({data: osTypeOptions});
                if (this.value == "option") {
                    currentOsList.newItem({id: "option", name: translator.common.selectOS});
                    osTypeWidget.setStore(currentOsList);
                }
                var osListRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/osType/"
                });
                osListRestStore.get(this.value).then(function(osListDate) {
                    dojo.forEach(osListDate, function(osData) {
                        currentOsList.newItem({id: osData.osId, name: osData.osName});
                        osTypeWidget.setStore(currentOsList);
                    });
                });
            }
        }).placeAt("appTempOsCategoryList");
        osCategory.startup();

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "-1", name: translator.common.allZone}]
        };

        var zoneList = new ItemFileWriteStore({data: zoneOptions});

        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });

        var zoneWidget = new Select({
            id: "appTempZone",
            name: "test",
            store: zoneList,
            onChange: function() {

                var hyperviserItems = {
                    identifier: "name",
                    label: "name",
                    items: []
                };

                var hyperviserList = new ItemFileWriteStore({data: hyperviserItems});

                var hyperviserRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/hypervisor/"
                });
                hyperviserRestStore.get(zoneWidget.value).then(function(data) {
                    dojo.forEach(data, function(el) {
                        hyperviserList.newItem({id: el.name, name: el.name});              
                    });
                    dijit.byId("appHypervisorTest").setStore(hyperviserList);
                });
            }
        }, "appTempZone");
        zoneWidget.startup();

        var hyperviserItems = {
            identifier: "name",
            label: "name",
            items: [{id: "option", name: translator.common.message.selectHypervisor}]
        };
        var hyperviserList = new ItemFileWriteStore({data: hyperviserItems});
        var hyperviserRestStore = new JsonRest({
            target: core.getContextPath() + "/api/hypervisor/"
        });
        hyperviserRestStore.get("-1").then(function(data) {
            dojo.forEach(data, function(el) {
                hyperviserList.newItem({id: el.name, name: el.name});
            });
            dijit.byId("appHypervisorTest").set("value","option");
        });
        var hypervisorWidget = new Select({
            id: "appHypervisorTest",
            store: hyperviserList,
            maxHeight: -1,
            onChange: function() {
                AddAppTemplateInfo.loadTemplateFormat(this)
            }
        }, "appTempHypervisor");
        
        var formatItems = {
            identifier: "id",
            label: "name",
            items: []
        };

        var formatList = new ItemFileWriteStore({data: formatItems});
        
        var formatWidget = new FilteringSelect({
            id: "appTempFormat",
            store: formatList,
            maxHeight: -1
        }, "appTempFormatDiv");
        
        
    },
    cancel: function() {
        core.router.go("#/admin/template/appTemplate");
    },
    loadTemplateFormat : function(currentHyper) {
        
        var formatItems = {
            identifier: "id",
            label: "name",
            items: []
        };

        var formatList = new ItemFileWriteStore({data: formatItems});
        
        if(currentHyper.value == "XenServer") {
            formatList.newItem({id: "VHD", name: "VHD"});
            dijit.byId("appTempFormat").set("store", formatList);
            dijit.byId("appTempFormat").set("value", "VHD");
        } else if(currentHyper.value == "KVM") {
            formatList.newItem({id: "QCOW2", name: "QCOW2"});
            dijit.byId("appTempFormat").set("store", formatList);
            dijit.byId("appTempFormat").set("value", "QCOW2");
        } else {
            formatList.newItem({id: "option", name: translator.common.message.noHypervisor});
            dijit.byId("appTempFormat").set("store", formatList);
            dijit.byId("appTempFormat").set("value", "option");
        }
        
        
    },
    add: function() {
        var pageNode = dojo.byId("addAppTempPage");
        
        var formElements = dojo.query("#appTempOSArchDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var architecture = dijit.byId(checkedRadioId).value;
        var name = dijit.byId("appTempName");
        var description = dijit.byId("appTempDescription");
        var url = dijit.byId("appTempURL");
        var cost = dijit.byId("appTemplateCost");
        var zone = dijit.byId("appTempZone").value;
        var hypervisor = dijit.byId("appHypervisorTest").value;
        var detailDesc = dijit.byId("appTemplateDetailDesc").attr("value");
        var referenceURL = dijit.byId("appTempReferenceURL");
        var format = dijit.byId("appTempFormat").get('displayedValue');
        var formatValue = dijit.byId("appTempFormat").value;
        
        var osTypeId = dijit.byId("appOsList").value;
        var osCategoryId = dijit.byId("appOsCategoryList").value;        
        
        var extractable = dijit.byId("appTempExtractable").checked;

        var passwordEnabled = dijit.byId("appTempPasswordEnabled").checked;
        var isPublic = dijit.byId("appTempPublic").checked;
        var featured = dijit.byId("appTempFeatured").checked;
        var path = dijit.byId("appTempUrl").getValue();

        var cpuNumber = dijit.byId("appMinCpu");
        var cpuMemory = dijit.byId("appMinRam");
        var oneTimeChargeable = dijit.byId("appOneTimeChargeable").checked;
        
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var status = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if(firstNode) {
            firstNode.focus();
        } else if(hypervisor == "option" || format == "" || formatValue == "option" || osCategoryId == "option" || osTypeId == "option") {
            dojo.byId("addAppTemplateErrorMsg").style.display = "block";
        } else if((oneTimeChargeable == true || oneTimeChargeable == "true") && (cost == 0))  {
            dojo.byId("addAppTemplateErrorMsg").style.display = "none";
            registry.byId("appToaster").setContent(translator.common.message.oneTimeChargeTemp, "error");
            registry.byId("appToaster").show();                              
        } else {
            dojo.byId("addAppTemplateErrorMsg").style.display = "none";
            this._tempRestStrore = new JsonRest({
                target: core.getContextPath() + "/api/template/"
            });
            dijit.byId("appTempButton").setAttribute('style', 'display: none');
            dojo.byId("appTempLoader").style.display = "block";
            this._tempRestStrore.add({
                name: name.value,
                appTemplate: true,
                myTemplate: false,
                path: path,
                architecture: architecture,
                detailedDesc : detailDesc,
                referenceURL : referenceURL.value,
                description: description.value,
                url: url.value,
                cost: cost.value,
                zone: zone,
                hypervisor: hypervisor,
                format: format,
                osType: osTypeId,
                extractable: extractable,
                passwordEnabled: passwordEnabled,
                isPublic: isPublic,
                featured: featured,
                minRam: cpuMemory.value,
                minCpu: cpuNumber.value,
                oneTimeChargeable: oneTimeChargeable
            }).then(function(results) {
                dijit.byId("appTempButton").setAttribute('style', 'display: block');
                dojo.byId("appTempLoader").style.display = "none";
                if (results == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.created, "message");
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/template/appTemplate");
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.createdError, "error");
                    registry.byId("appToaster").show();
                }
            });
        }
    }
};

window.OsTemplateInfo = OsTemplateInfo;
window.AddOsTempInfo = AddOsTempInfo;
window.ViewOsTemplate = ViewOsTemplate;
window.AppTemplateInfo = AppTemplateInfo;
window.AddAppTemplateInfo = AddAppTemplateInfo;
window.ViewsAppTemplate = ViewsAppTemplate;
window.DeleteTemplate = DeleteTemplate;
window.UserTemplateInfo = UserTemplateInfo;
window.ViewsMyTemplate = ViewsMyTemplate;
