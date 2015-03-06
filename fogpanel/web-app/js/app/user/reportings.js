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
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dojo/json",
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/PlotKit/green",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojox/charting/plot2d/ClusteredColumns",
    "dojox/charting/widget/Legend",
    "dojox/charting/plot2d/Markers",
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
    "dojox/form/MultiComboBox",
    "dijit/Dialog",
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
    "dijit/layout/TabContainer"
],
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
                ItemFileWriteStore, DataGrid, ContentPane, Source, MultiSelect, dom, win, json,
                Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, ClusteredColumns, Legend) {
            window.translator = translator;
            window.query = query;
            window.domClass = domClass;
            window.Json = json;
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
            window.Chart = Chart;
            window.Pie = Pie;
            window.Lines = Lines;
            window.Magnify = Magnify;
            window.PlotKitGreen = PlotKitGreen;
            window.Tooltip = Tooltip;
            window.MoveSlice = MoveSlice;
            window.theme = theme;
            window.ColumnsPlot = ColumnsPlot;
            window.ClusteredColumns = ClusteredColumns;
            window.Legend = Legend;
            window.Highlight = Highlight;

            controller({
                name: "reports",
                module: "user",
                filePath: "/js/app/user/reportings.js",
                layout: {
                    name: "reports",
                    containerId: "content"
                },
                scaffold: false
            },
            {"index": action(function() {
                    core.ui.loadTemplate("stateMenuInfo", core.ui.getContentId());
                }),
                "billableItem": action(function() {
                    if (dijit.byId("billableItemReportChartForm")) {
                        dijit.byId("billableItemReportChartForm").destroyRecursive();
                    }
                    if (dijit.byId("billableItemReportForm")) {
                        dijit.byId("billableItemReportForm").destroyRecursive();
                    }
                    if (dijit.byId("reportFromDate")) {
                        dijit.byId("reportFromDate").destroyRecursive();
                        dijit.byId("reportToDate").destroyRecursive();
                    }
                    core.ui.loadTemplate("billableItemReportPage", core.ui.getContentId());
                    BillableItemReport.init();
                    BillableItemReport.populateValues();

                }),
                "paymentReport": action(function() {
                    if (dijit.byId("paymentReportForm")) {
                        dijit.byId("paymentReportForm").destroyRecursive();
                    }

                    if (dijit.byId("reportFromDate")) {
                        dijit.byId("reportFromDate").destroyRecursive();
                        dijit.byId("reportToDate").destroyRecursive();
                    }

                    core.ui.loadTemplate("paymentReportPage", core.ui.getContentId());
                    PaymentReport.init();
                    PaymentReport.populateValues();

                }),
                "billableItemChartReport": action(function() {
                    if (dijit.byId("billableItemReportForm")) {
                        dijit.byId("billableItemReportForm").destroyRecursive();
                    }
                    if (dijit.byId("billableItemReportChartForm")) {
                        dijit.byId("billableItemReportChartForm").destroyRecursive();
                    }

                    if (dijit.byId("reportFromDate")) {
                        dijit.byId("reportFromDate").destroyRecursive();
                        dijit.byId("reportToDate").destroyRecursive();
                    }
                    core.ui.loadTemplate("billableItemChartReportPage", core.ui.getContentId());
                    BillableItemChartReport.init();
                    BillableItemChartReport.populateValues();

                })

            });
        });

var BillableItemChartReport = {
    _accountListStore: "",
    _zoneListStore: "",
    _billableItemListStore: "",
    _reportStore: "",
    init: function() {
        this._accountListStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount"
        });

        this._billableItemListStore = new JsonRest({
            target: core.getContextPath() + "/api/billableItem/"
        });

        this._zoneListStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        this._reportStore = new JsonRest({
            target: core.getContextPath() + "/api/report/billableItemChartReport"
        });

        this._reportCountStore = new JsonRest({
            target: core.getContextPath() + "/api/report/billableItemCountChartReport"
        });

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("billableItemChartValue").innerHTML = cur.currency;
            });
        });
    },
    populateValues: function() {

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "NONZONE", name: translator.common.options.nonZone}, {id: "ALL", name: translator.common.options.allZone}]
        };

        var zoneList = new ItemFileWriteStore({data: zoneOptions});

        this._zoneListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {                
                zoneList.newItem({id: el.id, name: el.aliasName});
            });
        });

        var computeListStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });

        var planOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        var planList = new ItemFileWriteStore({data: planOptions});

        computeListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                planList.newItem({id: el.offerReferenceId, name: el.name});

            });
        });

        var planListWidget = new Select({
            id: "planListWidget",
            store: planList,
            name: "plan",            
            sortByLabel: false,
            maxHeight: 100  
        }).placeAt("billableItemPlanList");
        planListWidget.startup();

        var zoneWidget = new Select({
            store: zoneList,
            sortByLabel: false,
            name: "forZone",
            id: "zone",
            onChange: function() {
                var billableItemOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                if (this.value == "NONZONE") {
                    dijit.byId("forBillableItem").reset();
                    dojo.byId("billableItemLable").innerHTML = translator.common.report.nonZoneBased;
                    dojo.byId("planListDiv").style.display = "none";
                } else if (this.value == "ALL") {
                    dijit.byId("forBillableItem").reset();
                    dojo.byId("billableItemLable").innerHTML = translator.common.report.zoneBased;
                } else {
                    dijit.byId("forBillableItem").reset();
                    dojo.byId("billableItemLable").innerHTML = translator.common.report.zoneBased;
                    BillableItemChartReport.loadComPlanByZone(this.value);
                    BillableItemChartReport.loadDiskByZone(this.value);
                }


                var billableItemListWidget = dijit.byId("billableItemListWidget");

                if (billableItemListWidget) {
                    billableItemListWidget.destroyRecursive();
                }
                var billableItemList = new ItemFileWriteStore({data: billableItemOptions});

                var billableItemListStore = new JsonRest({
                    target: core.getContextPath() + "/api/billableItem/"
                });
                if (this.value == "NONZONE") {

                    billableItemListStore.query().then(function(data) {
                        dojo.forEach(data, function(el) {
                            if (el.hasZone == false || el.hasZone == "false") {
                                billableItemList.newItem({id: el.id, name: el.name, nameKey: el.nameKey});
                            }
                        });
                    });
                } else {
                    billableItemListStore.query().then(function(data) {
                        dojo.forEach(data, function(el) {
                            if (el.hasZone == true || el.hasZone == "true") {
                                billableItemList.newItem({id: el.id, name: el.name, nameKey: el.nameKey});
                            }
                        });
                    });
                }
                billableItemListWidget = new dojox.form.MultiComboBox({
                    store: billableItemList,
                    id: "billableItemListWidget",
                    name: "billableItemList",
                    onChange: function() {
                        BillableItemChartReport.loadPlan(this.value);
                    }
                }).placeAt("billableItemList");
                billableItemListWidget.startup();
            }
        }).placeAt("billableItemZoneList");
        zoneWidget.startup();

        var billableItemOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var billableItemList = new ItemFileWriteStore({data: billableItemOptions});

        this._billableItemListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if (el.hasZone == false || el.hasZone == "false") {
                    billableItemList.newItem({id: el.id, name: el.name, nameKey: el.nameKey});
                }
            });
        });

        var billableItemListWidget = new dojox.form.MultiComboBox({
            store: billableItemList,
            id: "billableItemListWidget",
            name: "billableItemList",
            onChange: function() {
                BillableItemChartReport.loadPlan(this.value);
            }
        }).placeAt("billableItemList");
        billableItemListWidget.startup();
        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("forAccount").value = el.id;
            });
        });
    },
    loadBillableItem: function(value) {
        if (value == "ALL") {

        } else {

        }
    },
    cancel: function() {
        dojo.byId("noReportDataDiv").style.display = "none";
        dojo.byId("reportDataChartDiv").style.display = "none";
        dijit.byId("billableItemReportChartForm").reset();
        dojo.byId("amountSummary").innerHTML = "";
        dojo.byId("countSummary").innerHTML = "";
        dojo.byId("reportChart").innerHTML = "";
        dojo.byId("reportCountChart").innerHTML = "";
        dojo.byId("billableItemErrorDiv").style.display = "none";
    },
    showAccountList: function(value) {

        if (value == "ALL") {

            dojo.byId("billableItemAccountListDiv").style.display = "none";
        } else {
            dojo.byId("billableItemAccountListDiv").style.display = "block";
        }
    },
    showDate: function(value) {

        if (value == "ALL") {
            dojo.byId("billableItemDateRangeDiv").style.display = "none";
        } else {
            dojo.byId("billableItemDateRangeDiv").style.display = "block";
        }
    },
    generate : function() {
        
        dojo.byId("amountSummary").innerHTML = "";  
        dojo.byId("countSummary").innerHTML = "";  
        dojo.byId("reportChart").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+ translator.common.loader.loadingPleaseWait +"</p>"; 
        dojo.byId("reportCountChart").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+ translator.common.loader.loadingPleaseWait +"</p>"; 
        
        var formJson =  JSON.parse(dojo.formToJson("billableItemReportChartForm"), true);
        
        var billableItemList = "";
             
        var selectedBillableItem = formJson.billableItemList;       
        
        dojo.forEach(selectedBillableItem.split(","), function(el) {
           dijit.byId("billableItemListWidget").store.fetch({query: {name: el},
               onItem: function(item) {
                   billableItemList += item.nameKey[0]+",";
               }
           });
        })  
        if (formJson.forDateRange == "SELECTIVE") {
            var pageNode = dojo.byId("billableItemDateRangeDiv");
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
                dojo.byId("amountSummary").innerHTML = "";
                dojo.byId("countSummary").innerHTML = "";
                dojo.byId("reportChart").innerHTML = "";
                dojo.byId("reportCountChart").innerHTML = "";
                dojo.byId("billableItemErrorDiv").style.display = "none";
            } else {


                if (formJson.forBillableItem == "SELECTIVE" && (formJson.billableItemList.toString() == "")) {

                    dojo.byId("amountSummary").innerHTML = "";
                    dojo.byId("countSummary").innerHTML = "";
                    dojo.byId("reportChart").innerHTML = "";
                    dojo.byId("reportCountChart").innerHTML = "";

                    dojo.byId("billableItemErrorDiv").style.display = "block"; 
                    dojo.byId("errorContent").innerHTML = translator.common.report.selectBillableItem

                } else {
                    dojo.byId("billableItemErrorDiv").style.display = "none";

                    var reportChartDataXAxisData = [];
                    var reportChartData = [];


                    var reportCountChartDataXAxisData = [];
                    var reportCountChartData = [];

                    this._reportCountStore.add({
                        forDateRange: formJson.forDateRange,
                        fromDate: formJson.reportFromDate,
                        toDate: formJson.reportToDate,
                        forAccount: formJson.forAccount,
                        forZone: formJson.forZone,
                        billableItemList: billableItemList,
                        forBillableItem: formJson.forBillableItem,
                        plan: formJson.plan
                    }).then(function(data) {
                        dojo.forEach(data, function(el) {
                            var i = el.billableCostItems.slice(0, 5).length;
                            dojo.forEach(el.billableCostItems.slice(0, 5), function(billableItem) {
                                if (billableItem == "null" || billableItem == null) {
                                    reportChartDataXAxisData.push({value: i, text: ""});
                                    i -= 1;
                                } else {
                                    if (billableItem == "SetupCostForInstance") {
                                        reportChartDataXAxisData.push({value: i, text: "SetupCost"});
                                    } else if (billableItem == "SecondaryIPCost") {
                                        reportChartDataXAxisData.push({value: j, text: "IP Cost"});
                                    } else {
                                        reportChartDataXAxisData.push({value: i, text: billableItem});
                                    }
                                    i -= 1;
                                }

                            });
                            dojo.forEach(el.billableItemCostAmounts.slice(0, 5), function(billableItemAmount) {
                                reportChartData.push(Number(billableItemAmount));
                            });
                            BillableItemChartReport.loadChart(reportChartDataXAxisData, reportChartData);
                            var j = el.billableItems.slice(0, 5).length;
                            dojo.forEach(el.billableItems.slice(0, 5), function(billableItem) {
                                if (billableItem == "null" || billableItem == null) {
                                    reportCountChartDataXAxisData.push({value: j, text: ""});
                                    j -= 1;
                                } else {
                                    if (billableItem == "SetupCostForInstance") {
                                        reportCountChartDataXAxisData.push({value: j, text: "SetupCost"});
                                    } else if (billableItem == "SecondaryIPCost") {
                                        reportCountChartDataXAxisData.push({value: j, text: "IP Cost"});
                                    } else {
                                        reportCountChartDataXAxisData.push({value: j, text: billableItem});
                                    }
                                    j -= 1;
                                }

                            });
                            dojo.forEach(el.billableItemAmounts.slice(0, 5), function(billableItemAmount) {
                                reportCountChartData.push(Number(billableItemAmount));
                            });
                        });
                        BillableItemChartReport.loadCountChart(reportCountChartDataXAxisData, reportCountChartData);
                    });
                }

            }
        } else {
            if(formJson.forBillableItem == "SELECTIVE" && (formJson.billableItemList.toString() == "")) {
                    
                    dojo.byId("amountSummary").innerHTML = "";  
                    dojo.byId("countSummary").innerHTML = "";  
                    dojo.byId("reportChart").innerHTML = ""; 
                    dojo.byId("reportCountChart").innerHTML = "";
                    dojo.byId("billableItemErrorDiv").style.display = "block"; 
                    dojo.byId("errorContent").innerHTML = translator.common.report.selectBillableItem
                } else {
                    dojo.byId("billableItemErrorDiv").style.display = "none"; 
                    
                    var reportChartDataXAxisData = [];
                    var reportChartData = [];  

                var reportCountChartDataXAxisData = [];
                var reportCountChartData = [];

                this._reportCountStore.add({
                    forDateRange: formJson.forDateRange,
                    fromDate: formJson.reportFromDate,
                    toDate: formJson.reportToDate,
                    forAccount: formJson.forAccount,
                    forZone: formJson.forZone,
                    billableItemList: billableItemList,
                    forBillableItem: formJson.forBillableItem,
                    plan: formJson.plan
                }).then(function(data) {
                    dojo.forEach(data, function(el) {
                        var i = el.billableCostItems.slice(0, 5).length;
                        dojo.forEach(el.billableCostItems.slice(0, 5), function(billableItem) {
                            if (billableItem == "null" || billableItem == null) {
                                reportChartDataXAxisData.push({value: i, text: ""});
                                i -= 1;
                            } else {
                                if (billableItem == "SetupCostForInstance") {
                                    reportChartDataXAxisData.push({value: i, text: "SetupCost"});
                                } else {
                                    reportChartDataXAxisData.push({value: i, text: billableItem});
                                }
                                i -= 1;
                            }

                        });
                        dojo.forEach(el.billableItemCostAmounts.slice(0, 5), function(billableItemAmount) {
                            reportChartData.push(Number(billableItemAmount));
                        });
                        BillableItemChartReport.loadChart(reportChartDataXAxisData, reportChartData);
                        var j = el.billableItems.slice(0, 5).length;
                        dojo.forEach(el.billableItems.slice(0, 5), function(billableItem) {
                            if (billableItem == "null" || billableItem == null) {
                                reportCountChartDataXAxisData.push({value: j, text: ""});
                                j -= 1;
                            } else {
                                if (billableItem == "SetupCostForInstance") {
                                    reportCountChartDataXAxisData.push({value: j, text: "SetupCost"});
                                } else {
                                    reportCountChartDataXAxisData.push({value: j, text: billableItem});
                                }
                                j -= 1;
                            }

                        });
                        dojo.forEach(el.billableItemAmounts.slice(0, 5), function(billableItemAmount) {
                            reportCountChartData.push(Number(billableItemAmount));
                        });
                    });
                    BillableItemChartReport.loadCountChart(reportCountChartDataXAxisData, reportCountChartData);
                });
            }
        }

    },
    showBillableItemList: function(value) {
        var zoneWidget = dijit.byId("zone");
        if (value == "ALL") {
            dojo.byId("planListDiv").style.display = "none";
            dojo.byId("billableItemListDiv").style.display = "none";
        } else {
            if (zoneWidget.value == "NONZONE") {
                dojo.byId("planListDiv").style.display = "none";
            } else {
                dojo.byId("planListDiv").style.display = "block";
            }
            dojo.byId("billableItemListDiv").style.display = "block";
        }
        dijit.byId("billableItemListWidget").reset();
        dojo.byId("planListDiv").style.display = "none";
    },
    loadChart: function(reportChartDataXAxisData, reportChartData) {

        if (reportChartDataXAxisData.length == 0) {
            dojo.byId("noReportDataDiv").style.display = "block";
            dojo.byId("reportDataChartDiv").style.display = "none";
        } else {
            dojo.byId("noReportDataDiv").style.display = "none";
            dojo.byId("reportDataChartDiv").style.display = "block";
        }


        dojo.byId("amountSummary").innerHTML = translator.common.amountSummary;    

        dojo.empty("reportChart");

        // Create the chart within it's "holding" node
        var reportChart = new dojox.charting.Chart("reportChart");
        // Set the theme
        reportChart.setTheme(theme);

        // Add the only/default plot
        reportChart.addPlot("default", {
            type: ColumnsPlot,
            markers: true,
            gap: 5
        });

        // Add axes
        reportChart.addAxis("x", {labels: reportChartDataXAxisData.reverse()});
        reportChart.addAxis("y", {vertical: true, fixLower: "major", fixUpper: "major", min: 25});

        // Add the series of data
        reportChart.addSeries("Monthly Sales", reportChartData.reverse());

        // Highlight!
        new Highlight(reportChart, "default");

        //ToolTip
        new Tooltip(reportChart, "default");

        // Render the chart!
        reportChart.render();

    },
    loadCountChart: function(reportCountChartDataXAxisData, reportCountChartData) {
        dojo.byId("countSummary").innerHTML = translator.common.countSummary;       
        dojo.empty("reportCountChart");

        // Create the chart within it's "holding" node
        var reportChart = new dojox.charting.Chart("reportCountChart");
        // Set the theme
        reportChart.setTheme(theme);

        // Add the only/default plot
        reportChart.addPlot("default", {
            type: ColumnsPlot,
            markers: true,
            gap: 5
        });

        // Add axes
        reportChart.addAxis("x", {labels: reportCountChartDataXAxisData.reverse()});
        reportChart.addAxis("y", {vertical: true, fixLower: "major", fixUpper: "major", min: 0});

        // Add the series of data
        reportChart.addSeries("Monthly Sales", reportCountChartData.reverse());

        // Highlight!
        new Highlight(reportChart, "default");

        //ToolTip
        new Tooltip(reportChart, "default");

        // Render the chart!
        reportChart.render();

    },
    showZoneList: function(value) {

        if (value == "NONZONE") {
            dojo.byId("billableItemLable").innerHTML = translator.common.report.nonZoneBased;
        } else {
            dojo.byId("billableItemLable").innerHTML = translator.common.report.zoneBased;
        }
    },
    loadPlan: function(value) {
        var item = value.split(',');
        if (item[0] == "ComputingOffer" && item.length == 2) {
            BillableItemChartReport.loadComputePlanList();
        } else if (item[0] == "DiskOffer" && item.length == 2) {
            BillableItemChartReport.loadDiskList();
        } else if (item[0] == "Discount" && item.length == 2) {
            BillableItemChartReport.loadComputePlanList();
        } else if (item[0] == "StoppedInstance" && item.length == 2) {
            BillableItemChartReport.loadComputePlanList();
        } else if (item[0] == "SetupCostForInstance" && item.length == 2) {
            BillableItemChartReport.loadComputePlanList();
        } else {
            dojo.byId("planListDiv").style.display = "none";
        }
    },
    loadComPlanByZone: function(zoneId) {
        var planListWidget = dijit.byId("planListWidget");
        var computeListStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/zone"
        });

        var planOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        var planList = new ItemFileWriteStore({data: planOptions});

        computeListStore.query({zoneId: zoneId}).then(function(data) {
            dojo.forEach(data, function(el) {
                planList.newItem({id: el.offerReferenceId, name: el.name});
            });
        });

        planListWidget.setStore(planList);
    },
    loadDiskByZone: function(zoneId) {

        var planListWidget = dijit.byId("planListWidget");

        var diskListStore = new JsonRest({
            target: core.getContextPath() + "/api/diskOffer/zone"
        });

        var planOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        diskListStore.query({zoneId: zoneId}).then(function(data) {
            dojo.forEach(data, function(el) {
                planList.newItem({id: el.diskOfferReferenceId, name: el.name});

            });
        });

        var planList = new ItemFileWriteStore({data: planOptions});
        planListWidget.setStore(planList);

    },
    loadComputePlanList: function() {
        dojo.byId("planListDiv").style.display = "block";
        var planListWidget = dijit.byId("planListWidget");

        var zoneWidget = dijit.byId("zone");
        if (zoneWidget.value == "NONZONE") {

        } else if (zoneWidget.value == "ALL") {
            var computeListStore = new JsonRest({
                target: core.getContextPath() + "/api/computingOffer/"
            });

            var planOptions = {
                identifier: 'id',
                label: 'name',
                items: [{id: "ALL", name: translator.common.all}]
            };

            var planList = new ItemFileWriteStore({data: planOptions});

            computeListStore.query().then(function(data) {
                dojo.forEach(data, function(el) {
                    planList.newItem({id: el.offerReferenceId, name: el.name});

                });
            });

            planListWidget.setStore(planList);
        } else {
            BillableItemChartReport.loadComPlanByZone(zoneWidget.value);
        }


    },
    loadDiskList: function() {
        dojo.byId("planListDiv").style.display = "block";
        var planListWidget = dijit.byId("planListWidget");


        var zoneWidget = dijit.byId("zone");
        if (zoneWidget.value == "NONZONE") {

        } else if (zoneWidget.value == "ALL") {
            var diskListStore = new JsonRest({
                target: core.getContextPath() + "/api/diskOffer/"
            });

            var planOptions = {
                identifier: 'id',
                label: 'name',
                items: [{id: "ALL", name: translator.common.all}]
            };

            diskListStore.query().then(function(data) {
                dojo.forEach(data, function(el) {
                    planList.newItem({id: el.diskOfferReferenceId, name: el.name});

                });
            });

            var planList = new ItemFileWriteStore({data: planOptions});
            planListWidget.setStore(planList);
        } else {
            BillableItemChartReport.loadDiskByZone(zoneWidget.value);
        }
    }
};


var BillableItemReport = {
    _accountListStore: "",
    _zoneListStore: "",
    _billableItemListStore: "",
    init: function() {
        this._accountListStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount"
        });

        this._billableItemListStore = new JsonRest({
            target: core.getContextPath() + "/api/billableItem/"
        });

        this._zoneListStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("billableItemCurrencyValue").innerHTML = cur.currency;
            });
        });
    },
    populateValues: function() {
        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "NONZONE", name: translator.common.options.nonZone}, {id: "ALL", name: translator.common.options.allZone}]
        };

        var zoneList = new ItemFileWriteStore({data: zoneOptions});

        this._zoneListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.id, name: el.aliasName});
            });
        });

        var computeListStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });

        var planOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        var planList = new ItemFileWriteStore({data: planOptions});

        computeListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                planList.newItem({id: el.offerReferenceId, name: el.name});

            });
        });

        var planListWidget = new Select({
            store: planList,
            name: "plan",
            sortByLabel: false,
            id: "planListWidget",
            maxHeight: 100
        }).placeAt("billableItemPlanList");
        planListWidget.startup();

        var zoneWidget = new Select({
            store: zoneList,
            name: "forZone",
            sortByLabel: false,
            id: "zone",
            onChange: function() {
                var billableItemOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                if (this.value == "NONZONE") {
                    dijit.byId("forBillableItem").reset();
                    dojo.byId("billableItemLable").innerHTML = translator.common.report.nonZoneBased;
                    dojo.byId("planListDiv").style.display = "none";
                } else if (this.value == "ALL") {
                    dijit.byId("forBillableItem").reset();
                    dojo.byId("billableItemLable").innerHTML = translator.common.report.zoneBased;
                } else {
                    dijit.byId("forBillableItem").reset();
                    dojo.byId("billableItemLable").innerHTML = translator.common.report.zoneBased;
                    BillableItemReport.loadComPlanByZone(this.value);
                    BillableItemReport.loadDiskByZone(this.value);
                }


                var billableItemListWidget = dijit.byId("billableItemListWidget");

                if (billableItemListWidget) {
                    billableItemListWidget.destroyRecursive();
                }
                var billableItemList = new ItemFileWriteStore({data: billableItemOptions});

                var billableItemListStore = new JsonRest({
                    target: core.getContextPath() + "/api/billableItem/"
                });

                if (this.value == "NONZONE") {
                    billableItemListStore.query().then(function(data) {
                        dojo.forEach(data, function(el) {
                            if (el.hasZone == false || el.hasZone == "false") {
                                billableItemList.newItem({id: el.id, name: el.name, nameKey: el.nameKey});
                            }
                        });
                    });
                } else {
                    billableItemListStore.query().then(function(data) {
                        dojo.forEach(data, function(el) {
                            if (el.hasZone == true || el.hasZone == "true") {
                                billableItemList.newItem({id: el.id, name: el.name, nameKey: el.nameKey});
                            }
                        });
                    });
                }
                billableItemListWidget = new dojox.form.MultiComboBox({
                    store: billableItemList,
                    id: "billableItemListWidget",
                    name: "billableItemList",
                    onChange: function() {
                        BillableItemReport.loadPlan(this.value);
                    }
                }).placeAt("billableItemList");
                billableItemListWidget.startup();
            }
        }).placeAt("billableItemZoneList");
        zoneWidget.startup();

        var billableItemOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var billableItemList = new ItemFileWriteStore({data: billableItemOptions});

        this._billableItemListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if (el.hasZone == false || el.hasZone == "false") {
                    billableItemList.newItem({id: el.id, name: el.name, nameKey: el.nameKey});
                }
            });
        });

        var billableItemListWidget = new dojox.form.MultiComboBox({
            store: billableItemList,
            id: "billableItemListWidget",
            name: "billableItemList",
            onChange: function() {
                BillableItemReport.loadPlan(this.value);
            }
        }).placeAt("billableItemList");
        billableItemListWidget.startup();

        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("forAccount").value = el.id;
            });
        });


    },
    loadBillableItem: function(value) {
        if (value == "ALL") {

        } else {

        }
    },
    cancel: function() {
        dijit.byId("billableItemReportForm").reset();
        document.getElementById("billableItemIframe").src = "";
        dojo.byId("billableItemActionButtonDiv").style.display = "none";
        dojo.byId("billableItemErrorDiv").style.display = "none";
    },
    showAccountList: function(value) {

        if (value == "ALL") {
            dojo.byId("billableItemAccountListDiv").style.display = "none";
        } else {
            dojo.byId("billableItemAccountListDiv").style.display = "block";
        }
    },
    showDate: function(value) {

        if (value == "ALL") {
            dojo.byId("billableItemDateRangeDiv").style.display = "none";
        } else {
            dojo.byId("billableItemDateRangeDiv").style.display = "block";
        }
    },
    generate: function() {
        var formJson = JSON.parse(dojo.formToJson("billableItemReportForm"), true);
        dojo.byId("billableItemActionButtonDiv").style.display = "block";
                
        var billableItemList = "";
             
        var selectedBillableItem = formJson.billableItemList;       
               
        dojo.forEach(selectedBillableItem.split(","), function(el) {
           dijit.byId("billableItemListWidget").store.fetch({query: {name: el},
               onItem: function(item) {
                   billableItemList += item.nameKey[0]+",";
               }
           });
        }) 
        
        if (formJson.forDateRange == "SELECTIVE") {
            var pageNode = dojo.byId("billableItemDateRangeDiv");
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
                document.getElementById("billableItemIframe").src = "";
                dojo.byId("billableItemErrorDiv").style.display = "none";
                dojo.byId("billableItemActionButtonDiv").style.display = "none";
            } else {
                if (formJson.forBillableItem == "SELECTIVE" && (formJson.billableItemList.toString() == "")) {
                    document.getElementById("billableItemIframe").src = "";
                    dojo.byId("billableItemErrorDiv").style.display = "block";
                    dojo.byId("errorContent").innerHTML = translator.common.report.selectBillableItem;
                    dojo.byId("billableItemActionButtonDiv").style.display = "none";
                } else {
                    dojo.byId("billableItemErrorDiv").style.display = "none";
                    dojo.byId("billableItemActionButtonDiv").style.display = "block";
                    document.getElementById("billableItemIframe").src = "pdf/billableItemReportNew?fromDate=" + formJson.reportFromDate +
                            "&toDate=" + formJson.reportToDate +
                            "&forDateRange=" + formJson.forDateRange +
                            "&forAccount=" + formJson.forAccount +
                            "&forZone=" + formJson.forZone +
                            "&billableItemList=" + billableItemList +
                            "&forBillableItem=" + formJson.forBillableItem +
                            "&plan=" + formJson.plan;
                    document.getElementById("billableItemCsv").setAttribute("href", "" + core.getBaseURL() + "/pdf/billableItemReportNew?format=csv&fromDate=" + formJson.reportFromDate +
                            "&toDate=" + formJson.reportToDate +
                            "&forDateRange=" + formJson.forDateRange +
                            "&forAccount=" + formJson.forAccount +
                            "&forZone=" + formJson.forZone +
                            "&billableItemList=" + billableItemList +
                            "&plan=" + formJson.plan +
                            "&forBillableItem=" + formJson.forBillableItem);
                    document.getElementById("billableItemPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&type=pdf&pdfAction=billableItemReportNew&fromDate=" + formJson.reportFromDate +
                            "&toDate=" + formJson.reportToDate +
                            "&forDateRange=" + formJson.forDateRange +
                            "&forAccount=" + formJson.forAccount +
                            "&forZone=" + formJson.forZone +
                            "&billableItemList=" + billableItemList +
                            "&forBillableItem=" + formJson.forBillableItem +
                            "&plan=" + formJson.plan +
                            "&filename=billableItem-report");
                }                
            }
        } else {
            if (formJson.forBillableItem == "SELECTIVE" && (formJson.billableItemList.toString() == "")) {
                document.getElementById("billableItemIframe").src = "";
                dojo.byId("billableItemErrorDiv").style.display = "block";
                dojo.byId("errorContent").innerHTML = translator.common.report.selectBillableItem;
                dojo.byId("billableItemActionButtonDiv").style.display = "none";
            } else {
                dojo.byId("billableItemErrorDiv").style.display = "none";
                dojo.byId("billableItemActionButtonDiv").style.display = "block";
                document.getElementById("billableItemIframe").src = "pdf/billableItemReportNew?fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange +
                        "&forAccount=" + formJson.forAccount +
                        "&forZone=" + formJson.forZone +
                        "&billableItemList=" + billableItemList +
                        "&forBillableItem=" + formJson.forBillableItem +
                        "&plan=" + formJson.plan;

                document.getElementById("billableItemCsv").setAttribute("href", "" + core.getBaseURL() + "/pdf/billableItemReportNew?format=csv&fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange +
                        "&forAccount=" + formJson.forAccount +
                        "&forZone=" + formJson.forZone +
                        "&billableItemList=" + billableItemList +
                        "&plan=" + formJson.plan +
                        "&forBillableItem=" + formJson.forBillableItem);

                document.getElementById("billableItemPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&type=pdf&pdfAction=billableItemReportNew&fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange +
                        "&forAccount=" + formJson.forAccount +
                        "&forZone=" + formJson.forZone +
                        "&billableItemList=" + billableItemList +
                        "&forBillableItem=" + formJson.forBillableItem +
                        "&plan=" + formJson.plan +
                        "&filename=billableItem-report");
            }
        }
    },
    showBillableItemList: function(value) {
        var zoneWidget = dijit.byId("zone");
        if (value == "ALL") {
            dojo.byId("planListDiv").style.display = "none";
            dojo.byId("billableItemListDiv").style.display = "none";
        } else {
            if (zoneWidget.value == "NONZONE") {
                dojo.byId("planListDiv").style.display = "none";
            } else {
                dojo.byId("planListDiv").style.display = "block";
            }
            dojo.byId("billableItemListDiv").style.display = "block";
        }
        dijit.byId("billableItemListWidget").reset();
        dojo.byId("planListDiv").style.display = "none";
    },
    showZoneList: function(value) {
        if (value == "NONZONE") {
            dojo.byId("billableItemLable").innerHTML = translator.common.report.nonZoneBased;
        } else {
            dojo.byId("billableItemLable").innerHTML = translator.common.report.zoneBased;
        }
    },
    loadPlan: function(value) {
        var item = value.split(',');
        if (item[0] == "ComputingOffer" && item.length == 2) {
            BillableItemReport.loadComputePlanList();
        } else if (item[0] == "DiskOffer" && item.length == 2) {
            BillableItemReport.loadDiskList();
        } else if (item[0] == "Discount" && item.length == 2) {
            BillableItemReport.loadComputePlanList();
        } else if (item[0] == "StoppedInstance" && item.length == 2) {
            BillableItemReport.loadComputePlanList();
        } else if (item[0] == "SetupCostForInstance" && item.length == 2) {
            BillableItemReport.loadComputePlanList();
        } else {
            dojo.byId("planListDiv").style.display = "none";
        }
    },
    loadComPlanByZone: function(zoneId) {
        var planListWidget = dijit.byId("planListWidget");
        var computeListStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/zone"
        });

        var planOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        var planList = new ItemFileWriteStore({data: planOptions});

        computeListStore.query({zoneId: zoneId}).then(function(data) {
            dojo.forEach(data, function(el) {
                planList.newItem({id: el.offerReferenceId, name: el.name});
            });
        });

        planListWidget.setStore(planList);
    },
    loadDiskByZone: function(zoneId) {

        var planListWidget = dijit.byId("planListWidget");

        var diskListStore = new JsonRest({
            target: core.getContextPath() + "/api/diskOffer/zone"
        });

        var planOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        diskListStore.query({zoneId: zoneId}).then(function(data) {
            dojo.forEach(data, function(el) {
                planList.newItem({id: el.diskOfferReferenceId, name: el.name});

            });
        });

        var planList = new ItemFileWriteStore({data: planOptions});
        planListWidget.setStore(planList);

    },
    loadComputePlanList: function() {
        dojo.byId("planListDiv").style.display = "block";
        var planListWidget = dijit.byId("planListWidget");

        var zoneWidget = dijit.byId("zone");
        if (zoneWidget.value == "NONZONE") {

        } else if (zoneWidget.value == "ALL") {
            var computeListStore = new JsonRest({
                target: core.getContextPath() + "/api/computingOffer/"
            });

            var planOptions = {
                identifier: 'id',
                label: 'name',
                items: [{id: "ALL", name: translator.common.all}]
            };

            var planList = new ItemFileWriteStore({data: planOptions});

            computeListStore.query().then(function(data) {
                dojo.forEach(data, function(el) {
                    planList.newItem({id: el.offerReferenceId, name: el.name});

                });
            });

            planListWidget.setStore(planList);
        } else {
            BillableItemReport.loadComPlanByZone(zoneWidget.value);
        }


    },
    loadDiskList: function() {
        dojo.byId("planListDiv").style.display = "block";
        var planListWidget = dijit.byId("planListWidget");


        var zoneWidget = dijit.byId("zone");
        if (zoneWidget.value == "NONZONE") {

        } else if (zoneWidget.value == "ALL") {
            var diskListStore = new JsonRest({
                target: core.getContextPath() + "/api/diskOffer/"
            });

            var planOptions = {
                identifier: 'id',
                label: 'name',
                items: [{id: "ALL", name: translator.common.all}]
            };

            diskListStore.query().then(function(data) {
                dojo.forEach(data, function(el) {
                    planList.newItem({id: el.diskOfferReferenceId, name: el.name});

                });
            });

            var planList = new ItemFileWriteStore({data: planOptions});
            planListWidget.setStore(planList);
        } else {
            BillableItemReport.loadDiskByZone(zoneWidget.value);
        }
    }

};
var PaymentReport = {
    _accountListStore: "",
    _zoneListStore: "",
    _billableItemListStore: "",
    init: function() {
        this._accountListStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount"
        });

        this._billableItemListStore = new JsonRest({
            target: core.getContextPath() + "/api/billableItem/"
        });

        this._zoneListStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("paymentCurrencyValue").innerHTML = cur.currency;
            });
        });
    },
    populateValues: function() {


        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("forAccount").value = el.id;
            });
        });

    },
    showDate: function(value) {

        if (value == "ALL") {
            dojo.byId("paymentReportDateRangeDiv").style.display = "none";
        } else {
            dojo.byId("paymentReportDateRangeDiv").style.display = "block";
        }
    },
    generate: function() {

        var formJson = JSON.parse(dojo.formToJson("paymentReportForm"), true);
        dojo.byId("paymentReportActionButtonDiv").style.display = "block";



        if (formJson.forDateRange == "SELECTIVE") {
            var pageNode = dojo.byId("paymentReportDateRangeDiv");
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
                document.getElementById("paymentReportIframe").src = "";
                dojo.byId("paymentReportActionButtonDiv").style.display = "none";
            } else {
                dojo.byId("paymentReportActionButtonDiv").style.display = "block";
                document.getElementById("paymentReportIframe").src = "pdf/paymentReport?fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange +
                        "&forAccount=" + formJson.forAccount;

                document.getElementById("paymentReportCsv").setAttribute("href", "" + core.getBaseURL() + "/pdf/paymentReport?format=csv&fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange +
                        "&forAccount=" + formJson.forAccount);

                document.getElementById("paymentReportPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&type=pdf&pdfAction=paymentReport&fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange +
                        "&forAccount=" + formJson.forAccount +
                        "&filename=payment-report");
            }
        } else {
            dojo.byId("paymentReportActionButtonDiv").style.display = "block";
            document.getElementById("paymentReportIframe").src = "pdf/paymentReport?fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange +
                    "&forAccount=" + formJson.forAccount;

            document.getElementById("paymentReportCsv").setAttribute("href", "" + core.getBaseURL() + "/pdf/paymentReport?format=csv&fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange +
                    "&forAccount=" + formJson.forAccount);

            document.getElementById("paymentReportPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&type=pdf&pdfAction=paymentReport&fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange +
                    "&forAccount=" + formJson.forAccount +
                    "&filename=payment-report");
        }


    },
    cancel: function() {
        dijit.byId("paymentReportForm").reset();
        document.getElementById("paymentReportIframe").src = "";
        dojo.byId("paymentReportActionButtonDiv").style.display = "none";
    }

};

window.BillableItemReport = BillableItemReport;
window.PaymentReport = PaymentReport;
