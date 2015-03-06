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
                    var form = dijit.byId("adminVmActionForm");
                    if (form) {
                        form.destroyRecursive();
                        dijit.byId("migrateVMDialog").destroyRecursive();
                        dijit.byId("migrateVMLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("instancePage", core.ui.getContentId());
                    InstancesInfo.init();
                    InstancesInfo.populateValues();
                }),
                "cloud": action(function() {
                    core.ui.loadTemplate("cloud", core.ui.getContentId());
                }),
                "instance": action(function() {
                    var form = dijit.byId("adminVmActionForm");
                    if (form) {
                        form.destroyRecursive();
                        dijit.byId("migrateVMDialog").destroyRecursive();
                        dijit.byId("migrateVMLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("instancePage", core.ui.getContentId());
                    InstancesInfo.init();
                    InstancesInfo.populateValues();
                }),
                "storage": action(function() {
                    var currentStoragePage = dijit.byId("adminStorageActionForm");
                    if (currentStoragePage) {
                        currentStoragePage.destroyRecursive();
                    }
                    core.ui.loadTemplate("storage", core.ui.getContentId());
                    StorageInfo.init();
                    StorageInfo.populateValues();
                }),
                "snapShot": action(function() {
                    var currentPage = dijit.byId("adminSnapActionForm");
                    if (currentPage) {
                        currentPage.destroyRecursive();
                    }

                    core.ui.loadTemplate("snapshot", core.ui.getContentId());
                    SnapshotInfo.init();
                    SnapshotInfo.populateValues();
                }),
                "vmBandwidth": action(function() {
                    core.ui.loadTemplate("vmBandwidth", core.ui.getContentId());
                    VMBandwidthInfo.init();
                    VMBandwidthInfo.populateValues();
                }),
                "ipInfo": action(function() {
                    core.ui.loadTemplate("ipInfo", core.ui.getContentId());
                    IpInfo.init();
                    IpInfo.populateValues();
                }),
                "network" : action(function () {
                    
                    core.ui.loadTemplate("network", core.ui.getContentId());
                    
                    ListNetwork.init();
                    ListNetwork.populateValues();
                    
                })
            });
        });


var ListNetwork = {
    'init': function() {

        
    },
    'populateValues': function() {
        dojo.byId("userNetworkList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData});
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true', 'formatter': function(data) {
                        return data.name;                    
                    }
                },
                {'name': translator.common.account.name, 'field': 'account', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.state, 'field': 'state', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.type, 'field': 'type', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '100%', 'datatype': 'string', 'autoComplete': 'true'},
//                 {'name': translator.common.cost, 'field': 'cost', 'width': '10%', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
//                         var html = "<span class='fog_cost'>"+ data + "</span>"
//                        return html;
//                 }},
//                {'name': translator.common.action, 'field': 'action',
//                    'formatter': function(data) {
//                        var html;
////                            if(currentData[1] == true || currentData[1] == "true") {
//                        html = "<a href='#/user/network/edit/" + data.id + "' title='" + translator.common.edit + "'><i class='icon-edit'></i></a>" +
////                                        "<a href='#/admin/computation/list/' class='offset1' title='"+translator.common.disable+"' onclick='ComputaionListInfo.disablePlan("+currentData[0]+")'><i class='icon-eye-close'></i></a></li>"+
//                                "<a  onclick='ListNetwork.deleteNetworkShow(\"" + data.id + "\")' class='offset1' title='" + translator.common.deleteData + "'><i class='icon-remove'></i></a></li>";
////                            } else if(currentData[1] == false || currentData[1] == "false") {
////                                 html = "<a href='#/admin/computation/viewComputation/"+currentData[0]+"' title='"+translator.common.view+"'><i class='icon-edit'></i></a>" +                                       
////                                        "<a href='#/admin/computation/list/' class='offset1' title='"+translator.common.enable+"' onclick='ComputaionListInfo.enablePlan("+currentData[0]+")'><i class='icon-eye-open'></i></a></li>" + 
////                                        "<a href='#/admin/computation/deleteComputation/"+currentData[0]+"' class='offset1' title='"+translator.common.deleteData+"'><i class='icon-remove'></i></a></li>";
////                            }
//                        return html;
//                    }, 'width': '15%', 'datatype': 'string', 'autoComplete': 'true'}
            ]
        ];
       
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/network"
        });
        networkRestStore.query({zoneReferenceId: "All"}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("userNetworkList").innerHTML = "";
                dojo.byId("noOfferMessageBox").style.display = "block";
                dojo.byId("userTotalNetwork").innerHTML = 0;
                dojo.byId("userEnabledTotalNetwork").innerHTML = 0;
                dojo.byId("userDisabledTotalNetwork").innerHTML = 0;
            } else {
                dojo.byId("noOfferMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dataList.newItem({
                        id: resultData.referenceId,
                        name: {id: resultData.id, name: resultData.name},
                        zone: resultData.zone,
                        cidr: resultData.cidr,
                        state: resultData.state,
                        gateway: resultData.gateway,
                        type: resultData.type,
                        account:resultData.account,
//                            cost:  resultData.cost,
//                        action: {id: resultData.id}
                    });
                });
                dojo.byId("userNetworkList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userNetworkList");
                dataGrid.startup();
                dataGrid.update();

//                    var coumputingOfferCountRestStore = new JsonRest({                        
//                        target: core.getContextPath()+"/api/computingOffer/count"
//                    }); 
//                    coumputingOfferCountRestStore.query().then(function(data) {
//                        dojo.forEach(data, function(resultData) {
//                            dojo.byId("userTotalNetwork").innerHTML = 0;
//                            dojo.byId("userEnabledTotalNetwork").innerHTML = 0;
//                            dojo.byId("userDisabledTotalNetwork").innerHTML = 0;                                    
//                        });
//                    });
            }
        });
    }
};
var IpInfo = {
    'init': function() {
    },
    'populateValues': function(data) {

        if (dijit.byId("adminIpGridWidget")) {
            dijit.byId("adminIpGridWidget").destroyRecursive();
        }

        var ipListTicketStore = new JsonRest({
            target: core.getContextPath() + "/api/ipAddress/publicIp/"
        });

        dojo.byId("adminIPInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";


        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ip.name, 'field': 'ip', 'width': '300px', datatype: "string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false
                }},
                {'name': translator.common.account.name, 'field': 'account', 'width': '250px', datatype: "string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false
                }},
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.network, 'field': 'network', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.networkType, 'field': 'networkType', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.instance.name, 'field': 'instance', 'width': '100%', datatype: "string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false
                    }}
            ]
        ];

        ipListTicketStore.query().then(function(data) {
            dojo.byId("adminIPInfo").innerHTML = "";
            if (data.length == 0) {
                dojo.byId("adminIPInfo").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>"+translator.common.ip.userDidntAquireIP+"</div>";
            } else {
                dojo.forEach(data, function(el) {
                    gridDataList.newItem({
                        id: el.id,
                        account:el.account,
                        ip: el.ip,
                        instance: el.vm,
                        zone: el.zone,
                        network: el.network ? el.network : "Default",
                        networkType: el.networkType ? el.networkType : "Shared"
                    });
                });
                var ipGrid = new EnhancedGrid({
                    id: 'adminIpGridWidget',
                    "class" : "span12",
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });

                ipGrid.placeAt("adminIPInfo");
                ipGrid.startup();
            }
        });
    }
};

var CloudConfig = {
    openCloud: function() {
        var liElement = dojo.byId("cloudMenu");
        var varticalMenuBar = dijit.byId("verticalMenu");
        varticalMenuBar.onLiClick(liElement);
    }
};

var VMBandwidthInfo = {
    'init': function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        this._bandwidthRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/bandwidth"
        });
        this._instanceRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/"
        });
        this._instanceCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/count"
        });
    },
    'populateValues': function() {
        if (dijit.byId("adminBandDataGrid")) {
            dijit.byId("adminBandDataGrid").destroyRecursive();
        }

        var vmBandData = {
            items: []
        };
        var vmBandDataList = new ItemFileWriteStore({data: vmBandData});
        var vmBandDataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.user.userName, 'field': 'account', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.snapshot.layout.instanceName, 'field': 'name', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.message.usedBandwidth, 'field': 'totalBand', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.message.excedded, 'field': 'exBand', 'width': '100px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='redColor'>" + data + "</span>";
                    }
                },
                {'name': translator.common.template.grid.cost, 'field': 'exBandCost', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='orangeColor'>" + data + "</span>";
                    }
                }
            ]
        ];

        dojo.byId("adminBandwidthInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";

        this._bandwidthRestStore.query({'zoneReferenceId': "ALL"}).then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("adminTotalBandwidth").innerHTML = Math.round((el.totalBandwidth) * 100) / 100;
                dojo.byId("adminExceededBandwidth").innerHTML = Math.round((el.totalExBandwidth) * 100) / 100;
                dojo.byId("adminTotalBandwidthCost").innerHTML = Math.round((el.totalBandwidthCost) * 100) / 100;
                dojo.byId("adminTotalBandwidthUsd").innerHTML = el.currency;
                if (el.vmBandItems.length == 0 || el.vmBandItems == undefined || el.vmBandItems == "undefined" || el.vmBandItems == '' || el.vmBandItems == 'null' || el.vmBandItems == null) {
                    dojo.byId("noBandWidthMessageBox").style.display = "block";
                    dojo.byId("adminBandwidthInfo").innerHTML = "";
                } else {
                    dojo.forEach(el.vmBandItems, function(vmBand) {
                        vmBandDataList.newItem({
                            id: vmBand.vmId,
                            name: vmBand.vmName,
                            account: vmBand.account,
                            exBand: Math.round((vmBand.exBand) * 100) / 100,
                            exBandCost: Math.round((vmBand.bandCost) * 100) / 100,
                            totalBand: Math.round((vmBand.totalBand) * 100) / 100
                        });
                    });

                    dojo.byId("adminBandwidthInfo").innerHTML = "";
                    var bandDataGrid = new EnhancedGrid({
                        id: 'adminBandDataGrid',
                        "class" : "span12",
                        store: vmBandDataList,
                        structure: vmBandDataLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    bandDataGrid.placeAt("adminBandwidthInfo");
                    bandDataGrid.startup();
                }
            });
        });

        if (dijit.byId("adminBandZoneWidget")) {
            dijit.byId("adminBandZoneWidget").destroyRecursive();
        }

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.options.allZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });
        var zoneWidget = new Select({
            id: "adminBandZoneWidget",
            name: "zoneWidgets",
            value: "option",
            store: zoneList,
            onChange: function() {
                VMBandwidthInfo.getBandwidthByZone(this);
            }
        }, "adminBandZoneList");
    },
    'getBandwidthByZone': function(currentZone) {
        dojo.byId("noBandWidthMessageBox").style.display = "none";
        if (dijit.byId("adminBandDataGrid")) {
            dijit.byId("adminBandDataGrid").destroyRecursive();
        }

        dojo.byId("adminBandwidthInfo").innerHTML = "";
        dojo.byId("adminBandwidthInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";

        this._bandwidthRestStore.query({'zoneReferenceId': currentZone.value}).then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("adminTotalBandwidth").innerHTML = Math.round((el.totalBandwidth) * 100) / 100;
                dojo.byId("adminExceededBandwidth").innerHTML = Math.round((el.totalExBandwidth) * 100) / 100;
                dojo.byId("adminTotalBandwidthCost").innerHTML = Math.round((el.totalBandwidthCost) * 100) / 100;
                dojo.byId("adminTotalBandwidthUsd").innerHTML = el.currency;
                if (el.vmBandItems.length == 0 || el.vmBandItems == undefined || el.vmBandItems == 'undefined' || el.vmBandItems == '' || el.vmBandItems == 'null' || el.vmBandItems == null) {
                    dojo.byId("noBandWidthMessageBox").style.display = "block";
                    dojo.byId("adminBandwidthInfo").innerHTML = "";
                } else {
                    var vm2BandData = {
                        items: []
                    };
                    var vmBand2DataList = new ItemFileWriteStore({data: vm2BandData});
                    var vmBand2DataLayout = [
                        [
                            {'field': 'id', 'hidden': 'true'},
                            {'name': translator.common.account.name, 'field': 'account', 'width': '20%'},
                            {'name': translator.common.name, 'field': 'name', 'width': '20%'},
                            {'name': translator.common.message.usedBandwidth, 'field': 'totalBand', 'width': '10%', datatype: "string", autoComplete: true},
                            {'name': translator.common.message.excedded, 'field': 'exBand', 'width': '10%', datatype: "string", autoComplete: true, 'formatter': function(data) {

                                    return  "<span class='redColor'>" + data + "</span>";
                                }
                            },
                            {'name': translator.common.cost, 'field': 'exBandCost', 'width': '10%', datatype: "string", autoComplete: true, 'formatter': function(data) {
                                    return  "<span class='orangeColor'>" + data + "</span>";
                                }
                            }
                        ]
                    ];

                    dojo.forEach(el.vmBandItems, function(vmBand) {
                        vmBand2DataList.newItem({
                            id: vmBand.vmId,
                            name: vmBand.vmName,
                            account: vmBand.account,
                            exBand: Math.round((vmBand.exBand) * 100) / 100,
                            exBandCost: Math.round((vmBand.bandCost) * 100) / 100,
                            totalBand: Math.round((vmBand.totalBand) * 100) / 100
                        });
                    });

                    dojo.byId("adminBandwidthInfo").innerHTML = "";
                    var band2DataGrid = new EnhancedGrid({
                        id: 'adminBandDataGrid',
                        store: vmBand2DataList,
                        structure: vmBand2DataLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    band2DataGrid.placeAt("adminBandwidthInfo");
                    band2DataGrid.startup();
                }
            });
        });
    }
};

var InstancesInfo = {
    _zoneWidget: "",
    _instanceRestStore: "",
    _instanceCountRestStore: "",
    init: function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });
        this._instanceRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/"
        });
        this._instanceCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/count"
        });
    },
    populateValues: function() {
        if (dijit.byId("adminInstanceZoneWidget") && dijit.byId("adminInstanceDataGrid")) {
            dijit.byId("adminInstanceZoneWidget").destroyRecursive();
            dijit.byId("adminInstanceDataGrid").destroyRecursive();
        }
        if (dijit.byId("adminInstanceZoneWidget")) {
            dijit.byId("adminInstanceZoneWidget").destroyRecursive();
        }
        
         var hostOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var hostList = new ItemFileWriteStore({data: hostOptions});
        
        var hostListWidget = new Select({
                    id: "hostListWidget",
                    name: "hostList",      
                    store: hostList
        });
        hostListWidget.placeAt("hostListDiv");                     

        dojo.byId("adminInstanceInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        dojo.byId("adminTotalVM").innerHTML = 0;
        dojo.byId("adminRunningVM").innerHTML = 0;
        dojo.byId("adminStoppedVM").innerHTML = 0;
        var instanceData = {
            items: []
        };
        var instanceDataList = new ItemFileWriteStore({data: instanceData});
        var instanceDataLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.user.userName, 'field': 'user', 'width': '200px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='bold'>" + data + "</span>";
                    }},
                {'name': translator.user.grid.instance.layout.zone, 'field': 'zone', 'width': '150px', datatype: "string", autoComplete: true},
                {'name': translator.common.instance.instanceIP, 'field': 'ip', 'width': '100px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='bold'>" + data + "</span>";
                }},        
                {'name': translator.common.displayName, 'field': 'name', 'width': '150px', datatype:"string",  autoComplete: true},
                {'name': translator.user.grid.instance.layout.plane, 'field': 'plan', 'width': '100px', datatype:"string",  autoComplete: true, 'formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
                }},                 
                {'name': translator.common.status.name, 'field': 'status', 'width': '100px', datatype:"string",  autoComplete: true, 'formatter': function(data) {                         
                        var imageDiv = domConstruct.create("div", { innerHTML: "<img src='images/vmload.gif'  width='80' height='10' alt='"+translator.common.loader.loading+"'/>" });
                        var status;   
                        if(data != "in progress") {
                            if(data == "Running") {
                                status = "<div class='stat_running'>"+translator.common.instance.status.running+"</div>";
                            } else if(data == "Stopped") {
                                status = "<div class='stat_stopped'>"+translator.common.instance.status.stopped+"</div>";
                            } else if(data == "Destroyed") {
                                status = "<div class='stat_destroyed'>" + translator.common.instance.status.destroyed +"</div>";
                            } else {
                                if(data == "Starting") {
                                    status = translator.common.instance.status.starting;
                                } else if (data == "Stopping") {
                                    status = translator.common.instance.status.stopping;
                                } else if (data == "Expunging") {
                                    status = translator.common.instance.status.exbunging;
                                } else {
                                    status = data;
                                }
                            }
                        } else if (data == "in progress") {
                            status = imageDiv.innerHTML;
                        }
                        return status;
                    }
                },
                {'name': 'Action', 'width': '100%', 'field': 'action', 'formatter': function(layoutData, rowIndex) {
                        var vmData = layoutData.split(",");
                        var instanceStatus = new FogPanel.InstanceStatus({
                            onStartInstance: function() {
                                InstancesInfo.startVm(vmData[1]);
                            },
                            onStopInstance: function() {
                                InstancesInfo.stopVM(vmData[1]);
                            },
                            onRebootInstance: function() {
                                InstancesInfo.restartVM(vmData[1]);
                            },
                            onMigrateInstance : function() {
                                InstancesInfo.showMigrateVMDialog(vmData[1]);
                            }
                        });
                        if (vmData[0] == "Running") {
                            instanceStatus.adminEnableRunningState();
                        } else if (vmData[0] == "Stopped") {
                            instanceStatus.adminEnableStopState();
                        } else {
                            instanceStatus.disableAll();
                        }
                        return instanceStatus;
                    }
                }
            ]
        ];

        this._instanceRestStore.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == ' ' || data == 'null' || data == null) {
                dojo.byId("noVMMessageBox").style.display = "block";
                dojo.byId("adminInstanceInfo").innerHTML = "";
                dojo.byId("adminTotalVM").innerHTML = 0;
                dojo.byId("adminRunningVM").innerHTML = 0;
                dojo.byId("adminStoppedVM").innerHTML = 0;
            } else {
                dojo.byId("noVMMessageBox").style.display = "none";
                dojo.forEach(data, function(vmData) {
                    instanceDataList.newItem({
                        id: vmData.referenceId,
                        name: vmData.name,
                        plan: vmData.computingOffer,
                        zone: vmData.zoneName,
                        status: vmData.state,
                        ip: vmData.ip,
                        action: vmData.state + "," + vmData.referenceId,
                        user: vmData.user
                    });
                });

                dojo.byId("adminInstanceInfo").innerHTML = "";
                var instanceDataGrid = new EnhancedGrid({
                    id: 'adminInstanceDataGrid',
                    "class" : "span12",
                    store: instanceDataList,
                    structure: instanceDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                instanceDataGrid.placeAt("adminInstanceInfo");
                instanceDataGrid.startup();
                var instanceCountRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/virtualMachine/count"
                });
                instanceCountRestStore.query().then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        dojo.byId("adminTotalVM").innerHTML = resultData.totalVms;
                        dojo.byId("adminRunningVM").innerHTML = resultData.runningVms;
                        dojo.byId("adminStoppedVM").innerHTML = resultData.stoppedVms;
                    });
                });
            }
        });

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "All Zones"}]
        };

        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });

        var zoneWidget = new Select({
            id: "adminInstanceZoneWidget",
            name: "zoneWidgets",
            value: "option",
            store: zoneList,
            onChange: function() {
                InstancesInfo.getVMByZone(this);
            }
        }, "adminZoneList");
    },
    closeMigrateVMDialog: function() {
        dijit.byId("migrateVMDialog").hide();
    },
    showMigrateVMDialog: function(currentVMId) {
        dojo.byId("vmID").value = currentVMId;
        dijit.byId("migrateVMDialog").show();
        
        dojo.byId("hostListLoadingDiv").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>"; 
        dojo.byId("hostListWidDiv").style.display = "none";
        
        var hostOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var hostRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/getHost"
        });
        var hostList = new ItemFileWriteStore({data: hostOptions});
        hostRestStore.query({vmId:currentVMId}).then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == ' ' || data == 'null' || data == null) {
                dojo.byId("hostListLoadingDiv").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>"+translator.common.message.noHostAvailable+"</div>";
            } else {
                var type;
                dojo.forEach(data, function(el) {
                    type = el.type;
                    hostList.newItem({id: el.referenceId, name: el.name});
                });
                if(type == "host") {
                    dojo.byId("hostInfo").innerHTML = translator.common.message.noHostAvailable;
                    dojo.byId("lableValue").innerHTML = translator.common.host;
                } else if(type == "storage") {
                    dojo.byId("hostInfo").innerHTML = translator.common.message.confirmAnotherMigratVM;
                    dojo.byId("lableValue").innerHTML = translator.common.primaryStorage;
                }
                dojo.byId("hostListLoadingDiv").innerHTML = "";
                dijit.byId("hostListWidget").setStore(hostList); 
                dojo.byId("hostListWidDiv").style.display = "block";
            }
        });
    },
    migrateVM: function() {
         
        dijit.byId("migrateVMLoader").show();
        
        var instanceMigrateRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/migrate/"
        });

        instanceMigrateRestStore.add({
            vmId: dojo.byId("vmID").value,
            hostId: dijit.byId("hostListWidget").value
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result == "OK") {
                    dijit.byId("migrateVMDialog").hide();
                    var migrateVmJobStatus = setInterval(function() {
                        InstancesInfo.migrateVmJob(resultData.jobId, migrateVmJobStatus);
                    }, 3000);
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.cannotMigrateInstance, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("migrateVMLoader").hide();
                    dijit.byId("migrateVMDialog").hide();
                }
            });
        });
        
    },
    migrateVmJob: function(jobId, vmMigJobStatus) {
        var jobStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/migrate/job/"
        });
        jobStore.add({
            jobId: jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.jobResult == "OK") {
                    clearInterval(vmMigJobStatus);
                    registry.byId("appToaster").setContent(translator.common.message.vmMigratSucess, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("migrateVMLoader").hide();
                    
                } else if (resultData.jobResult == "Pending") {
                    
                } else if (resultData.jobResult == "FAILED") {
                    clearInterval(vmMigJobStatus);
                    registry.byId("appToaster").setContent(translator.common.cloudStack.errorMessage, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("migrateVMLoader").hide();
                }
            });
        });
    },
    restartVM: function(currentVMId) {
        var instanceRebootRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/reboot/"
        });

        instanceRebootRestStore.add({
            referenceId: currentVMId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result == "OK") {
                    var vmStartJobStatus = setInterval(function() {
                        InstancesInfo.vmJob(resultData.jobId, vmStartJobStatus, currentVMId, translator.common.instance.vmRebooted);
                    }, 3000);
                    var index = dijit.byId("adminInstanceDataGrid").selection.selectedIndex;
                    var item = dijit.byId("adminInstanceDataGrid").getItem(index);
                    var store = dijit.byId("adminInstanceDataGrid").store;
                    store.setValue(item, 'status', "in progress");
                    store.setValue(item, 'action', "in progress");
                    dijit.byId("adminInstanceDataGrid").update();
                } else {
                    registry.byId("appToaster").setContent(translator.common.instance.rebootVMError, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    startVm: function(currentVMId) {
        var instanceStartRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/start/"
        });

        instanceStartRestStore.add({
            referenceId: currentVMId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result == "OK") {
                    var vmStartJobStatus = setInterval(function() {
                        InstancesInfo.vmJob(resultData.jobId, vmStartJobStatus, currentVMId, "VM Started");
                    }, 3000);
                    var index = dijit.byId("adminInstanceDataGrid").selection.selectedIndex;
                    var item = dijit.byId("adminInstanceDataGrid").getItem(index);
                    var store = dijit.byId("adminInstanceDataGrid").store;
                    store.setValue(item, 'status', "in progress");
                    store.setValue(item, 'action', "in progress");
                    dijit.byId("adminInstanceDataGrid").update();
                } else if (resultData.result == "FAILED") {
                    registry.byId("appToaster").setContent(resultData.message, "error");
                    registry.byId("appToaster").show();
                } else {
                    registry.byId("appToaster").setContent(translator.common.instance.startVMError, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    stopVM: function(currentVMId) {
        var instanceStopRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/stop/"
        });
        instanceStopRestStore.add({
            referenceId: currentVMId,
            forced: true
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result == "OK") {
                    var vmStopJobStatus = setInterval(function() {
                        InstancesInfo.vmJob(resultData.jobId, vmStopJobStatus, currentVMId, translator.common.instance.vmStopped);
                    }, 3000);
                    var index = dijit.byId("adminInstanceDataGrid").selection.selectedIndex;
                    var item = dijit.byId("adminInstanceDataGrid").getItem(index);
                    var store = dijit.byId("adminInstanceDataGrid").store;
                    store.setValue(item, 'status', "in progress");
                    store.setValue(item, 'action', "in progress");

                    dijit.byId("adminInstanceDataGrid").update();
                } else {
                    registry.byId("appToaster").setContent(translator.common.instance.stopVMError, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    vmJob: function(jobId, vmStopJobStatus, currentVMId, sucessMessage) {
        var jobStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/job/"
        });
        var index = dijit.byId("adminInstanceDataGrid").selection.selectedIndex;
        var item = dijit.byId("adminInstanceDataGrid").getItem(index);
        var store = dijit.byId("adminInstanceDataGrid").store;
        jobStore.add({
            jobId: jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.jobResult == "OK") {
                    clearInterval(vmStopJobStatus);
                    registry.byId("appToaster").setContent(sucessMessage, "message");
                    registry.byId("appToaster").show();
                    store.setValue(item, 'status', resultData.state);
                    store.setValue(item, 'action', resultData.state + "," + currentVMId);
                    dijit.byId("adminInstanceDataGrid").update();
                } else if (resultData.jobResult == "Pending") {
                } else if (resultData.jobResult == "FAILED") {
                    clearInterval(vmStopJobStatus);
                    registry.byId("appToaster").setContent(translator.common.cloudStack.errorMessage, "error");
                    registry.byId("appToaster").show();
                    store.setValue(item, 'status', "Running");
                    store.setValue(item, 'action', "Running" + "," + currentVMId);
                    dijit.byId("adminInstanceDataGrid").update();
                }
            });
        });
    },
    showInstance: function(currentRadio) {
        var instanceRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/"
        });

        var instanceCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/count"
        });

        var instanceData = {
            items: []
        };
        var instanceGrid = dijit.byId("adminInstanceDataGrid");
        var instanceDataList = new ItemFileWriteStore({data: instanceData});
        var zoneWidget = dijit.byId("adminInstanceZoneWidget");
        if (zoneWidget.value == "option" && currentRadio.value == "All") {
            instanceRestStore.query().then(function(data) {
                dojo.forEach(data, function(vmData) {
                    instanceDataList.newItem({
                        id: vmData.referenceId,
                        name: vmData.name,
                        plan: vmData.computingOffer,
                        zone: vmData.zoneName,
                        status: vmData.state,
                        ip: vmData.ip,
                        action: vmData.state + "," + vmData.referenceId,
                        user: vmData.user
                    });
                });
                instanceGrid.setStore(instanceDataList);
            });
        } else if (zoneWidget.value == "option" && currentRadio.value != "All") {
            instanceCountRestStore.query({status: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.instanceData, function(vmData) {
                        instanceDataList.newItem({
                            id: vmData.referenceId,
                            name: vmData.name,
                            plan: vmData.computingOffer,
                            zone: vmData.zoneName,
                            ip: vmData.ip,
                            status: vmData.state,
                            action: vmData.state + "," + vmData.referenceId,
                            user: vmData.user
                        });
                    });
                });
                instanceGrid.setStore(instanceDataList);
            });
        } else if (zoneWidget.value != "option" && currentRadio.value != "All") {
            instanceCountRestStore.query({zoneReferenceId: zoneWidget.value, status: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.instanceData, function(vmData) {
                        instanceDataList.newItem({
                            id: vmData.referenceId,
                            name: vmData.name,
                            plan: vmData.computingOffer,
                            zone: vmData.zoneName,
                            ip: vmData.ip,
                            status: vmData.state,
                            action: vmData.state + "," + vmData.referenceId,
                            user: vmData.user
                        });
                    });
                });
                instanceGrid.setStore(instanceDataList);
            });
        } else if (zoneWidget.value != "option" && currentRadio.value == "All") {
            instanceCountRestStore.query({zoneReferenceId: zoneWidget.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.instanceData, function(vmData) {
                        instanceDataList.newItem({
                            id: vmData.referenceId,
                            name: vmData.name,
                            plan: vmData.computingOffer,
                            zone: vmData.zoneName,
                            ip: vmData.ip,
                            status: vmData.state,
                            action: vmData.state + "," + vmData.referenceId,
                            user: vmData.user
                        });
                    });
                });
                instanceGrid.setStore(instanceDataList);
            });
        }
    },
    getVMByZone: function(currentZone) {
        var formElements = dojo.query("#adminVmActionForm input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var currentStatus = dijit.byId(checkedRadioId).value;

        var instanceRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/"
        });

        var instanceCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/virtualMachine/count"
        });

        var instanceData = {
            items: []
        };

        var instanceGrid = dijit.byId("adminInstanceDataGrid");
        var instanceDataList = new ItemFileWriteStore({data: instanceData});
        if (currentZone.value == "option" && currentStatus == "All") {
            instanceRestStore.query().then(function(data) {
                dojo.forEach(data, function(vmData) {
                    instanceDataList.newItem({
                        id: vmData.referenceId,
                        name: vmData.name,
                        plan: vmData.computingOffer,
                        zone: vmData.zoneName,
                        status: vmData.state,
                        ip: vmData.ip,
                        action: vmData.state + "," + vmData.referenceId,
                        user: vmData.user
                    });
                });
                instanceGrid.setStore(instanceDataList);
                dojo.byId("adminTotalVM").innerHTML = vmData.totalVms;
                dojo.byId("adminRunningVM").innerHTML = vmData.runningVms;
                dojo.byId("adminStoppedVM").innerHTML = vmData.stoppedVms;
            });
        } else if (currentZone.value == "option" && currentStatus != "All") {
            instanceCountRestStore.query({status: currentStatus}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.instanceData, function(vmData) {
                        instanceDataList.newItem({
                            id: vmData.referenceId,
                            name: vmData.name,
                            plan: vmData.computingOffer,
                            zone: vmData.zoneName,
                            ip: vmData.ip,
                            status: vmData.state,
                            action: vmData.state + "," + vmData.referenceId,
                            user: vmData.user
                        });
                    });
                    dojo.byId("adminTotalVM").innerHTML = el.totalVms;
                    dojo.byId("adminRunningVM").innerHTML = el.runningVms;
                    dojo.byId("adminStoppedVM").innerHTML = el.stoppedVms;
                });
                instanceGrid.setStore(instanceDataList);
            });
        } else {
            if (currentStatus == "All") {
                instanceCountRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        dojo.forEach(el.instanceData, function(vmData) {
                            instanceDataList.newItem({
                                id: vmData.referenceId,
                                name: vmData.name,
                                plan: vmData.computingOffer,
                                zone: vmData.zoneName,
                                ip: vmData.ip,
                                status: vmData.state,
                                action: vmData.state + "," + vmData.referenceId,
                                user: vmData.user
                            });
                        });
                        dojo.byId("adminTotalVM").innerHTML = el.totalVms;
                        dojo.byId("adminRunningVM").innerHTML = el.runningVms;
                        dojo.byId("adminStoppedVM").innerHTML = el.stoppedVms;
                    });
                    instanceGrid.setStore(instanceDataList);
                });
            } else {
                instanceCountRestStore.query({zoneReferenceId: currentZone.value, status: currentStatus}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        dojo.forEach(el.instanceData, function(vmData) {
                            instanceDataList.newItem({
                                id: vmData.referenceId,
                                name: vmData.name,
                                plan: vmData.computingOffer,
                                zone: vmData.zoneName,
                                status: vmData.state,
                                ip: vmData.ip,
                                action: vmData.state + "," + vmData.referenceId,
                                user: vmData.user
                            });
                        });
                        dojo.byId("adminTotalVM").innerHTML = el.totalVms;
                        dojo.byId("adminRunningVM").innerHTML = el.runningVms;
                        dojo.byId("adminStoppedVM").innerHTML = el.stoppedVms;
                    });
                    instanceGrid.setStore(instanceDataList);
                });
            }
        }
    }
};

var StorageInfo = {
    _zoneRestStore: "",
    _diskOfferRestStore: "",
    _testRestStore: "",
    _instanceRestStore: "",
    _instanceCountRestStore: "",
    storageWidget: "",
    init: function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        this._volumeRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume"
        });

        this._volumeCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/count"
        });
    },
    populateValues: function() {
        if (dijit.byId("storageZoneWidget") && dijit.byId("adminVolumeDataGrid")) {
            dijit.byId("storageZoneWidget").destroyRecursive();
            dijit.byId("adminVolumeDataGrid").destroyRecursive();
        }

        if (dijit.byId("storageZoneWidget")) {
            dijit.byId("storageZoneWidget").destroyRecursive();
        }
        dojo.byId("adminVolumeInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";

        dojo.byId("adminAllocatedVolumes").innerHTML = 0;
        dojo.byId("adminAttachedVolumes").innerHTML = 0;
        dojo.byId("adminDetachedVolumes").innerHTML = 0;
        var volumeData = {
            items: []
        };
        var volumeDataList = new ItemFileWriteStore({data: volumeData});
        var volumeDataLayout = [            
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.user.userName, 'field': 'user', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.diskName, 'field': 'name', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.size, 'field': 'size', 'width': '200px', datatype: "string", autoComplete: true, 'formatter': function(data) {

                        return  "<span class='bold'>" + data + "</span>";
                    }},
                {'name': translator.user.grid.instance.layout.plane, 'field': 'offering', 'width': '200px', datatype: "string", autoComplete: true, 'formatter': function(data) {

                        return  "<span class='bold'>" + data + "</span>";
                    }},
                {'name': translator.user.grid.snapshot.layout.instanceName, 'field': 'vmName', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data) {

                        return  "<span class='bold'>" + data + "</span>";
                    }}
            ]
        ];
        this._volumeCountRestStore.query().then(function(data) {
            if (data[0].volumeData.length == 0 || data[0].volumeData == undefined || data[0].volumeData == 'undefined' || data[0].volumeData == '' || data[0].volumeData == ' ' || data[0].volumeData == 'null' || data[0].volumeData == null) {
                dojo.byId("adminVolumeInfo").innerHTML = "";
                dojo.byId("noStorageMessageBox").style.display = "block";
                dojo.byId("adminAllocatedVolumes").innerHTML = 0;
                dojo.byId("adminAttachedVolumes").innerHTML = 0;
                dojo.byId("adminDetachedVolumes").innerHTML = 0;
            } else {
                dojo.forEach(data, function(resultData) {
                    dojo.byId("adminAllocatedVolumes").innerHTML = resultData.totalStorage;
                    dojo.byId("adminAttachedVolumes").innerHTML = resultData.attachedStorage;
                    dojo.byId("adminDetachedVolumes").innerHTML = resultData.detachedStorage;
                    dojo.byId("noStorageMessageBox").style.display = "none";
                    dojo.forEach(resultData.volumeData, function(data) {
                        volumeDataList.newItem({
                            id: data.volumeReferenceId,
                            name: data.name,
                            size: data.size,
                            vmName: data.vmName,
                            offering: data.offer,
                            user: data.user
                        });
                    });
                });
                dojo.byId("adminVolumeInfo").innerHTML = "";
                var volumeDataGrid = new EnhancedGrid({
                    id: 'adminVolumeDataGrid',
                    "class" : "span12",
                    store: volumeDataList,
                    structure: volumeDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                volumeDataGrid.placeAt("adminVolumeInfo");
                volumeDataGrid.startup();
            }
        });

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

        this._zoneWidget = new Select({
            id: "storageZoneWidget",
            store: zoneList,
            onChange: function() {
                StorageInfo.getVMByZone(this);
            }
        }, "adminStorageList");
    },
    getVMByZone: function(currentZone) {
        var formElements = dojo.query("#adminStorageActionForm input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var currentStatus = dijit.byId(checkedRadioId).value;

        var volumeCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/count"
        });

        var dataInfo = {
            items: []
        };

        var grid = dijit.byId("adminVolumeDataGrid");
        var dataList = new ItemFileWriteStore({data: dataInfo});
        if (currentZone.value == "option" && currentStatus == "All") {
            volumeCountRestStore.query().then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.byId("adminAllocatedVolumes").innerHTML = resultData.totalStorage;
                    dojo.byId("adminAttachedVolumes").innerHTML = resultData.attachedStorage;
                    dojo.byId("adminDetachedVolumes").innerHTML = resultData.detachedStorage;

                    dojo.forEach(resultData.volumeData, function(data) {
                        dataList.newItem({
                            id: data.volumeReferenceId,
                            name: data.name,
                            size: data.size,
                            vmName: data.vmName,
                            offering: data.offer,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        } else if (currentZone.value == "option" && currentStatus != "All") {
            volumeCountRestStore.query({status: currentStatus}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.byId("adminAllocatedVolumes").innerHTML = el.totalStorage;
                    dojo.byId("adminAttachedVolumes").innerHTML = el.attachedStorage;
                    dojo.byId("adminDetachedVolumes").innerHTML = el.detachedStorage;
                    dojo.forEach(el.volumeData, function(data) {
                        dataList.newItem({
                            id: data.volumeReferenceId,
                            name: data.name,
                            size: data.size,
                            vmName: data.vmName,
                            offering: data.offer,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        } else {
            if (currentStatus == "All") {
                volumeCountRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                    dojo.forEach(data, function(el) {
                        dojo.forEach(el.volumeData, function(data) {
                            dataList.newItem({
                                id: data.volumeReferenceId,
                                name: data.name,
                                size: data.size,
                                vmName: data.vmName,
                                offering: data.offer,
                                user: data.user
                            });
                        });
                        dojo.byId("adminAllocatedVolumes").innerHTML = el.totalStorage;
                        dojo.byId("adminAttachedVolumes").innerHTML = el.attachedStorage;
                        dojo.byId("adminDetachedVolumes").innerHTML = el.detachedStorage;
                    });
                    grid.setStore(dataList);
                });
            } else {
                volumeCountRestStore.query({zoneReferenceId: currentZone.value, status: currentStatus}).then(function(resultData) {
                    dojo.forEach(resultData, function(el) {
                        dojo.forEach(el.volumeData, function(data) {
                            dataList.newItem({
                                id: data.volumeReferenceId,
                                name: data.name,
                                size: data.size,
                                vmName: data.vmName,
                                offering: data.offer,
                                user: data.user
                            });
                        });
                        dojo.byId("adminAllocatedVolumes").innerHTML = el.totalStorage;
                        dojo.byId("adminAttachedVolumes").innerHTML = el.attachedStorage;
                        dojo.byId("adminDetachedVolumes").innerHTML = el.detachedStorage;
                    });
                    grid.setStore(dataList);
                });
            }
        }
    },
    showVolumes: function(currentRadio) {
        var volumeCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volume/count"
        });

        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("adminVolumeDataGrid");
        var dataList = new ItemFileWriteStore({data: dataInfo});
        var zoneWidget = dijit.byId("storageZoneWidget");
        if (zoneWidget.value == "option" && currentRadio.value == "All") {
            volumeCountRestStore.query().then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.forEach(resultData.volumeData, function(data) {
                        dataList.newItem({
                            id: data.volumeReferenceId,
                            name: data.name,
                            size: data.size,
                            vmName: data.vmName,
                            offering: data.offer,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        } else if (zoneWidget.value == "option" && currentRadio.value != "All") {
            volumeCountRestStore.query({status: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.forEach(resultData.volumeData, function(data) {
                        dataList.newItem({
                            id: data.volumeReferenceId,
                            name: data.name,
                            size: data.size,
                            vmName: data.vmName,
                            offering: data.offer,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        } else if (zoneWidget.value != "option" && currentRadio.value != "All") {
            volumeCountRestStore.query({zoneReferenceId: zoneWidget.value, status: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.forEach(resultData.volumeData, function(data) {
                        dataList.newItem({
                            id: data.volumeReferenceId,
                            name: data.name,
                            size: data.size,
                            vmName: data.vmName,
                            offering: data.offer,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        } else if (zoneWidget.value != "option" && currentRadio.value == "All") {
            volumeCountRestStore.query({zoneReferenceId: zoneWidget.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.forEach(resultData.volumeData, function(data) {
                        dataList.newItem({
                            id: data.volumeReferenceId,
                            name: data.name,
                            size: data.size,
                            vmName: data.vmName,
                            offering: data.offer,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        }
    }
};

var SnapshotInfo = {
    _zoneRestStore: "",
    _snapshotCountRestStore: "",
    init: function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        this._snapshotCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/snapShot/count"
        });
    },
    populateValues: function() {
        if (dijit.byId("snapZoneWidget") && dijit.byId("snapDataGrid")) {
            dijit.byId("snapZoneWidget").destroyRecursive();
            dijit.byId("snapDataGrid").destroyRecursive();
        }
        if (dijit.byId("snapZoneWidget")) {
            dijit.byId("snapZoneWidget").destroyRecursive();
        }
        dojo.byId("adminSnapshotInfo").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        dojo.byId("adminManualSnap").innerHTML = 0;
        dojo.byId("adminAutoSnap").innerHTML = 0;
        dojo.byId("adminJobsSnap").innerHTML = 0;

        var data = {
            items: []
        };
        var dataList = new ItemFileWriteStore({data: data});
        var dataLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.user.userName, 'field': 'user', 'width': '200px', datatype: "string", autoComplete: true, 'formatter': function(data) {                        
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.user.grid.snapshot.layout.snapshotName, 'field': 'name', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.storage.layout.diskName, 'field': 'volumeName', 'width': '200px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.user.grid.snapshot.layout.type, 'field': 'type', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.user.grid.snapshot.layout.createdDate, 'field': 'createdDate', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='bold'>" + data + "</span>";
                    }
                }
            ]
        ];
        this._snapshotCountRestStore.query({status: "Manual"}).then(function(data) {
            if (data[0].snapshotInfo.length == 0 || data[0].snapshotInfo == undefined || data[0].snapshotInfo == 'undefined' || data[0].snapshotInfo == '' || data[0].snapshotInfo == ' ' || data[0].snapshotInfo == 'null' || data[0].snapshotInfo == null) {
                dojo.byId("adminSnapshotInfo").innerHTML = "";
                dojo.byId("noSnapshotMessageBox").style.display = "block";
                dojo.byId("adminManualSnap").innerHTML = 0;
                dojo.byId("adminAutoSnap").innerHTML = 0;
                dojo.byId("adminJobsSnap").innerHTML = 0;
            } else {
                dojo.byId("noSnapshotMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dojo.byId("adminManualSnap").innerHTML = resultData.totalManual;
                    dojo.byId("adminAutoSnap").innerHTML = resultData.totalAuto;
                    dojo.byId("adminJobsSnap").innerHTML = resultData.totalJobs;

                    dojo.forEach(resultData.snapshotInfo, function(data) {
                        dataList.newItem({
                            id: data.referenceId,
                            name: data.name,
                            volumeName: data.volumeName,
                            createdDate: data.createdDate,
                            type: data.type,
                            user: data.user
                        });
                    });
                });
                dojo.byId("adminSnapshotInfo").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: 'snapDataGrid',
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminSnapshotInfo");
                dataGrid.startup();
            }
        });
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

        this._zoneWidget = new Select({
            id: "snapZoneWidget",
            store: zoneList,
            onChange: function() {
                SnapshotInfo.getVMByZone(this);
            }
        }, "adminSnapZoneList");
    },
    getVMByZone: function(currentZone) {
        var formElements = dojo.query("#adminSnapActionForm input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var currentStatus = dijit.byId(checkedRadioId).value;

        var snapshotCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/snapShot/count"
        });
        var snapshotPolicyCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/snapShotPolicy/count"
        });

        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("snapDataGrid");
        var dataList = new ItemFileWriteStore({data: dataInfo});
        if (currentZone.value == "option") {
            snapshotCountRestStore.query({status: currentStatus}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.byId("adminManualSnap").innerHTML = resultData.totalManual;
                    dojo.byId("adminAutoSnap").innerHTML = resultData.totalAuto;
                    dojo.byId("adminJobsSnap").innerHTML = resultData.totalJobs;

                    dojo.forEach(resultData.snapshotInfo, function(data) {
                        dataList.newItem({
                            id: data.referenceId,
                            name: data.name,
                            volumeName: data.volumeName,
                            createdDate: dojo.date.locale.format(new Date(data.createdDate), {datePattern: "dd/MM/yyyy", selector: "date"}),
                            type: data.type,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        } else {
            snapshotCountRestStore.query({zoneReferenceId: currentZone.value, status: currentStatus}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.byId("adminManualSnap").innerHTML = el.totalManual;
                    dojo.byId("adminAutoSnap").innerHTML = el.totalAuto;

                    dojo.forEach(el.snapshotInfo, function(data) {
                        dataList.newItem({
                            id: data.referenceId,
                            name: data.name,
                            volumeName: data.volumeName,
                            createdDate: data.createdDate,
                            type: data.type,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
            snapshotPolicyCountRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.byId("adminJobsSnap").innerHTML = el.totalJobs;
                });
            });
        }
    },
    showVolumes: function(currentRadio) {
        var snapshotCountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/snapShot/count"
        });
        var dataInfo = {
            items: []
        };
        var grid = dijit.byId("snapDataGrid");
        var dataList = new ItemFileWriteStore({data: dataInfo});
        var zoneWidget = dijit.byId("snapZoneWidget");

        if (zoneWidget.value == "option") {
            snapshotCountRestStore.query({status: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.forEach(resultData.snapshotInfo, function(data) {
                        dataList.newItem({
                            id: data.referenceId,
                            name: data.name,
                            volumeName: data.volumeName,
                            createdDate: dojo.date.locale.format(new Date(data.createdDate), {datePattern: "dd/MM/yyyy", selector: "date"}),
                            type: data.type,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        } else if (zoneWidget.value != "option") {
            snapshotCountRestStore.query({zoneReferenceId: zoneWidget.value, status: currentRadio.value}).then(function(data) {
                dojo.forEach(data, function(el) {
                    dojo.forEach(el.snapshotInfo, function(data) {
                        dataList.newItem({
                            id: data.referenceId,
                            name: data.name,
                            volumeName: data.volumeName,
                            createdDate: dojo.date.locale.format(new Date(data.createdDate), {datePattern: "dd/MM/yyyy", selector: "date"}),
                            type: data.type,
                            user: data.user
                        });
                    });
                });
                grid.setStore(dataList);
            });
        }
    }
};

window.InstancesInfo = InstancesInfo;
window.StorageInfo = StorageInfo;
window.SnapshotInfo = SnapshotInfo;
window.VMBandwidthInfo = VMBandwidthInfo;
