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
],function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
        ItemFileWriteStore, DataGrid, ContentPane, Source, MultiSelect, dom, win, json,
        Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, ClusteredColumns, Legend) {
    window.query = query;
    window.translator = translator;
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
        module: "admin",
        filePath: "/js/app/admin/reportings.js",
        layout: {
            name: "reports",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {
            core.ui.loadTemplate("report", core.ui.getContentId());
        }),
        "signUp": action(function() {
            if (dijit.byId("reportFilterForm")) {
                dijit.byId("reportFilterForm").destroyRecursive();
            }
            core.ui.loadTemplate("reportingPage", core.ui.getContentId());
            SignUpReport.init();
            SignUpReport.populateValues();

        }),
        "billableItem": action(function() {
            if (dijit.byId("billableItemReportChartForm")) {
                dijit.byId("billableItemReportChartForm").destroyRecursive();
            }
            if (dijit.byId("billableItemReportForm")) {
                dijit.byId("billableItemReportForm").destroyRecursive();
            }
            core.ui.loadTemplate("billableItemReportPage", core.ui.getContentId());
            BillableItemReport.init();
            BillableItemReport.populateValues();

        }),
        "paymentReport": action(function() {
            if (dijit.byId("paymentReportForm")) {
                dijit.byId("paymentReportForm").destroyRecursive();
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
            core.ui.loadTemplate("billableItemChartReportPage", core.ui.getContentId());
            BillableItemChartReport.init();
            BillableItemChartReport.populateValues();

        }),
        "creditLimitReport": action(function() {
            if (dijit.byId("creditLimitReportForm")) {
                dijit.byId("creditLimitReportForm").destroyRecursive();
            }
            core.ui.loadTemplate("creditLimitReportPage", core.ui.getContentId());
            CreditLimitReport.init();

        }),
        "pendingPaymentReport": action(function() {
            if (dijit.byId("paymentPendingReportForm")) {
                dijit.byId("paymentPendingReportForm").destroyRecursive();
            }
            core.ui.loadTemplate("paymentPendingReport", core.ui.getContentId());
            PaymentPendingReport.init();

        })

    });
});

var SignUpReport = {
    _accountListStore: "",
    init: function() {
        this._accountListStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("reportSignupCurrencyValue").innerHTML = cur.currency;
            });
        });
    },
    populateValues: function() {
        var sel = dojo.byId('reportAccountList');
        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(account) {
                var opt = document.createElement('option');
                opt.innerHTML = account.userName;
                opt.value = account.id;
                sel.appendChild(opt);
            });
        });
    },
    generate: function() {


        var formJson = dojo.formToJson("reportFilterForm");
//        alert(formJson);
        dojo.byId("actionButtonDiv").style.display = "block";

        var forAccount = "ALL";
        var accountType = dijit.byId("accountType").value;
        var forDateRange = dijit.byId("forDateRange").value;
        var accountList = dojo.byId("reportAccountList");

        var selectedAccountArray = new Array();
        var j;
        var countUser = 0;
        for (j = 0; j < accountList.options.length; j++) {
            if (accountList.options[j].selected) {
                selectedAccountArray[countUser] = accountList.options[j].value;
                countUser++;
            }
        }


        if (forDateRange == "SELECTIVE") {
            var pageNode = dojo.byId("dateRangeDiv");
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
                document.getElementById("currentReport").src = "";
                dojo.byId("actionButtonDiv").style.display = "none";
            } else {

                dojo.byId("actionButtonDiv").style.display = "block";


                var fromDate = dojo.date.locale.format(dijit.byId("reportFromDate").value, {datePattern: "dd/MM/yyyy", selector: "date"});
                var toDate = dojo.date.locale.format(dijit.byId("reportToDate").value, {datePattern: "dd/MM/yyyy", selector: "date"});

                document.getElementById("currentReport").src = "report/accountReport?fromDate=" + fromDate + "&toDate=" + toDate +
                        "&forDateRange=" + forDateRange + "&forAccount=" + forAccount + "&accountType=" + accountType + "&accountList=" + selectedAccountArray.toString();

                document.getElementById("currentCsv").setAttribute("href", "" + core.getBaseURL() + "/report/accountReport?format=csv&fromDate=" + fromDate + "&toDate=" + toDate +
                        "&forDateRange=" + forDateRange + "&forAccount=" + forAccount + "&accountType=" + accountType + "&accountList=" + selectedAccountArray.toString());

                document.getElementById("currentPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=accountReport&fromDate=" + fromDate + "&toDate=" + toDate +
                        "&forDateRange=" + forDateRange + "&forAccount=" + forAccount + "&accountType=" + accountType + "&accountList=" + selectedAccountArray.toString() + "&filename=signup-report");

            }
        } else {
            dojo.byId("actionButtonDiv").style.display = "block";

            var fromDate;
            var toDate;

            if (forDateRange == "ALL") {

            } else {
                fromDate = dojo.date.locale.format(dijit.byId("reportFromDate").value, {datePattern: "dd/MM/yyyy", selector: "date"});
                toDate = dojo.date.locale.format(dijit.byId("reportToDate").value, {datePattern: "dd/MM/yyyy", selector: "date"});
            }

            document.getElementById("currentReport").src = "report/accountReport?fromDate=" + fromDate + "&toDate=" + toDate +
                    "&forDateRange=" + forDateRange + "&forAccount=" + forAccount + "&accountType=" + accountType + "&accountList=" + selectedAccountArray.toString();

            document.getElementById("currentCsv").setAttribute("href", "" + core.getBaseURL() + "/report/accountReport?format=csv&fromDate=" + fromDate + "&toDate=" + toDate +
                    "&forDateRange=" + forDateRange + "&forAccount=" + forAccount + "&accountType=" + accountType + "&accountList=" + selectedAccountArray.toString());

            document.getElementById("currentPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=accountReport&fromDate=" + fromDate + "&toDate=" + toDate +
                    "&forDateRange=" + forDateRange + "&forAccount=" + forAccount + "&accountType=" + accountType + "&accountList=" + selectedAccountArray.toString() + "&filename=signup-report");

        }

    },
    cancel: function() {
        document.getElementById("currentReport").src = "";
        dijit.byId("reportFilterForm").reset();
        dojo.byId("signUpReportErrorDiv").style.display = "none";
        dojo.byId("actionButtonDiv").style.display = "none";
    },
//    showAccountList : function(value) {
//
//        if(value == "ALL") {
//            dojo.byId("accountListDiv").style.display = "none";
//        } else {
//            dojo.byId("accountListDiv").style.display = "block";
//        }
//    },
    showDate: function(value) {

        if (value == "ALL") {
            dojo.byId("dateRangeDiv").style.display = "none";
        } else {
            dojo.byId("dateRangeDiv").style.display = "block";
        }
    }

};

var BillableItemChartReport = {
    _accountListStore: "",
    _zoneListStore: "",
    _billableItemListStore: "",
    _reportStore: "",
    init: function() {
        this._accountListStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
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
                dojo.byId("billableItemChartReportCurrencyValue").innerHTML = cur.currency;
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
            maxHeight: -1 // tells _HasDropDown to fit menu within viewport   
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

        var accountOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        var accountList = new ItemFileWriteStore({data: accountOptions});

        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                accountList.newItem({id: el.id, name: el.userName});
            });
        });
        var accountWidget = new Select({
            store: accountList,
            sortByLabel: false,
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport   
            name: "forAccount"
        }).placeAt("billableItemAccountList");
        accountWidget.startup();

    },
    loadBillableItem: function(value) {
        if (value == "ALL") {

        } else {

        }
    },
    cancel: function() {
        dijit.byId("billableItemReportChartForm").reset();
        dojo.byId("amountSummary").innerHTML = "";
        dojo.byId("countSummary").innerHTML = "";
        dojo.byId("reportChart").innerHTML = "";
        dojo.byId("reportCountChart").innerHTML = "";
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

        var formJson = JSON.parse(dojo.formToJson("billableItemReportChartForm"), true);

        dojo.byId("amountSummary").innerHTML = "";
        dojo.byId("countSummary").innerHTML = "";
        dojo.byId("reportChart").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+ translator.common.loader.loadingPleaseWait +"</p>"; 
        dojo.byId("reportCountChart").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+ translator.common.loader.loadingPleaseWait +"</p>"; 

//        var fromDate = dojo.date.locale.format(formJson.reportFromDate, {datePattern: "dd/MM/yyyy", selector: "date"});
//        var toDate = dojo.date.locale.format(formJson.reportToDate, {datePattern: "dd/MM/yyyy", selector: "date"});
//        dojo.byId("reportChart").innerHTML = "Please wait! Loading";
//        dojo.byId("reportCountChart").innerHTML = "Please wait! Loading";

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
            } else {

                if (formJson.forBillableItem == "SELECTIVE" && (formJson.billableItemList.toString() == "")) {

                    dojo.byId("amountSummary").innerHTML = "";
                    dojo.byId("countSummary").innerHTML = "";
                    dojo.byId("reportChart").innerHTML = "";
                    dojo.byId("reportCountChart").innerHTML = "";

                    dojo.byId("billableItemErrorDiv").style.display = "block";
                    dojo.byId("errorContent").innerHTML = translator.common.report.selectBillableItem;
                } else {
                    dojo.byId("billableItemErrorDiv").style.display = "none";

                    var reportChartDataXAxisData = [];
                    var reportChartData = [];
                    var i = 1;

                    var reportCountChartDataXAxisData = [];
                    var reportCountChartData = [];
                    var j = 1;

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
                            dojo.forEach(el.billableCostItems.slice(0, 5), function(billableItem) {
                                if (billableItem == "null" || billableItem == null) {
                                    reportChartDataXAxisData.push({value: i, text: ""});
                                    i += 1;
                                } else {
                                    if (billableItem == "SetupCostForInstance") {
                                        reportChartDataXAxisData.push({value: i, text: "SetupCost"});
                                    } else if (billableItem == "SecondaryIPCost") {
                                        reportChartDataXAxisData.push({value: i, text: "IP"});
                                    } else if (billableItem == "StoppedInstance") {
                                        reportChartDataXAxisData.push({value: i, text: "Stopped VM"});
                                    } else {
                                        reportChartDataXAxisData.push({value: i, text: billableItem});
                                    }
                                    i += 1;
                                }

                            });
                            dojo.forEach(el.billableItemCostAmounts.slice(0, 5), function(billableItemAmount) {
                                reportChartData.push(Number(billableItemAmount));
                            });
                            BillableItemChartReport.loadChart(reportChartDataXAxisData, reportChartData);
                            dojo.forEach(el.billableItems.slice(0, 5), function(billableItem) {
                                if (billableItem == "null" || billableItem == null) {
                                    reportCountChartDataXAxisData.push({value: j, text: ""});
                                    j += 1;
                                } else {
                                    if (billableItem == "SetupCostForInstance") {
                                        reportCountChartDataXAxisData.push({value: j, text: "SetupCost"});
                                    } else if (billableItem == "SecondaryIPCost") {
                                        reportCountChartDataXAxisData.push({value: j, text: "IP"});
                                    } else if (billableItem == "StoppedInstance") {
                                        reportCountChartDataXAxisData.push({value: j, text: "Stopped VM"});
                                    } else {
                                        reportCountChartDataXAxisData.push({value: j, text: billableItem});
                                    }
                                    j += 1;
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
            if (formJson.forBillableItem == "SELECTIVE" && (formJson.billableItemList.toString() == "")) {

                dojo.byId("amountSummary").innerHTML = "";
                dojo.byId("countSummary").innerHTML = "";
                dojo.byId("reportChart").innerHTML = "";
                dojo.byId("reportCountChart").innerHTML = "";

                dojo.byId("billableItemErrorDiv").style.display = "block";
                dojo.byId("errorContent").innerHTML = translator.common.report.selectBillableItem;
            } else {
                dojo.byId("billableItemErrorDiv").style.display = "none";

                var reportChartDataXAxisData = [];
                var reportChartData = [];
                var i = 1;

                var reportCountChartDataXAxisData = [];
                var reportCountChartData = [];
                var j = 1;

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
                        dojo.forEach(el.billableCostItems.slice(0, 5), function(billableItem) {
                            if (billableItem == "null" || billableItem == null) {
                                reportChartDataXAxisData.push({value: i, text: ""});
                                i += 1;
                            } else {
                                if (billableItem == "SetupCostForInstance") {
                                    reportChartDataXAxisData.push({value: i, text: "SetupCost"});
                                } else {
                                    reportChartDataXAxisData.push({value: i, text: billableItem});
                                }
                                i += 1;
                            }

                        });
                        dojo.forEach(el.billableItemCostAmounts.slice(0, 5), function(billableItemAmount) {
                            reportChartData.push(Number(billableItemAmount));
                        });
                        BillableItemChartReport.loadChart(reportChartDataXAxisData, reportChartData);
                        dojo.forEach(el.billableItems.slice(0, 5), function(billableItem) {
                            if (billableItem == "null" || billableItem == null) {
                                reportCountChartDataXAxisData.push({value: j, text: ""});
                                j += 1;
                            } else {
                                if (billableItem == "SetupCostForInstance") {
                                    reportCountChartDataXAxisData.push({value: j, text: "SetupCost"});
                                } else {
                                    reportCountChartDataXAxisData.push({value: j, text: billableItem});
                                }
                                j += 1;
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
        reportChart.addAxis("x", {labels: reportChartDataXAxisData});
        reportChart.addAxis("y", {vertical: true, fixLower: "major", fixUpper: "major", min: 25});

        // Add the series of data
        reportChart.addSeries("Monthly Sales", reportChartData);

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
        reportChart.addAxis("x", {labels: reportCountChartDataXAxisData});
        reportChart.addAxis("y", {vertical: true, fixLower: "major", fixUpper: "major", min: 0});

        // Add the series of data
        reportChart.addSeries("Monthly Sales", reportCountChartData);

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
        var billableItem;
        dojo.forEach(value.split(","), function(el) {
           dijit.byId("billableItemListWidget").store.fetch({query: {name: el},
               onItem: function(item) {
                   billableItem = item.nameKey[0];
               }
           });
        }) 
        if (billableItem == "common.billableItem.computingOffer" && item.length == 2) {
            BillableItemChartReport.loadComputePlanList();
        } else if (billableItem == "common.billableItem.diskOffer" && item.length == 2) {
            BillableItemChartReport.loadDiskList();
        } else if (billableItem == "common.billableItem.discount" && item.length == 2) {
            BillableItemChartReport.loadComputePlanList();
        } else if (billableItem == "common.billableItem.stoppedInstance" && item.length == 2) {
            BillableItemChartReport.loadComputePlanList();
        } else if (billableItem == "common.billableItem.setupCostForInstance" && item.length == 2) {
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
            target: core.getContextPath() + "/api/account/"
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
                dojo.byId("reportBillableItemCurrencyValue").innerHTML = cur.currency;
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
            maxHeight: -1 // tells _HasDropDown to fit menu within viewport   
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

        var accountOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        var accountList = new ItemFileWriteStore({data: accountOptions});

        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                accountList.newItem({id: el.id, name: el.userName});
            });
        });
        var accountWidget = new Select({
            store: accountList,
            sortByLabel: false,
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport   
            name: "forAccount"

        }).placeAt("billableItemAccountList");
        accountWidget.startup();

    },
    loadBillableItem: function(value) {
        if (value == "ALL") {

        } else {

        }
    },
    cancel: function() {
        dijit.byId("billableItemReportForm").reset();
        document.getElementById("billableItemIframe").src = "";
        dojo.byId("billableItemErrorDiv").style.display = "none";
        dojo.byId("billableItemActionButtonDiv").style.display = "none";
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
//        var fromDate = dojo.date.locale.format(formJson.reportFromDate, {datePattern: "dd/MM/yyyy", selector: "date"});
//        var toDate = dojo.date.locale.format(formJson.reportToDate, {datePattern: "dd/MM/yyyy", selector: "date"});
        
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

                    document.getElementById("billableItemPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=billableItemReportNew&fromDate=" + formJson.reportFromDate +
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

                document.getElementById("billableItemPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=billableItemReportNew&fromDate=" + formJson.reportFromDate +
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
        var billableItem;
        dojo.forEach(value.split(","), function(el) {
           dijit.byId("billableItemListWidget").store.fetch({query: {name: el},
               onItem: function(item) {
                   billableItem = item.nameKey[0];
               }
           });
        }) 
        if (billableItem == "common.billableItem.computingOffer" && item.length == 2) {
            BillableItemReport.loadComputePlanList();
        } else if (billableItem == "common.billableItem.diskOffer" && item.length == 2) {
            BillableItemReport.loadDiskList();
        } else if (billableItem == "common.billableItem.discount" && item.length == 2) {
            BillableItemReport.loadComputePlanList();
        } else if (billableItem == "common.billableItem.stoppedInstance" && item.length == 2) {
            BillableItemReport.loadComputePlanList();
        } else if (billableItem == "common.billableItem.setupCostForInstance" && item.length == 2) {
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
            target: core.getContextPath() + "/api/account/"
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
        var accountOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        var accountList = new ItemFileWriteStore({data: accountOptions});

        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                accountList.newItem({id: el.id, name: el.userName});
            });
        });
        var accountWidget = new Select({
            store: accountList,
            sortByLabel: false,
            name: "forAccount",
            maxHeight: -1 // tells _HasDropDown to fit menu within viewport   
        }).placeAt("paymentAccountList");
        accountWidget.startup();
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
//        var fromDate = dojo.date.locale.format(formJson.reportFromDate, {datePattern: "dd/MM/yyyy", selector: "date"});
//        var toDate = dojo.date.locale.format(formJson.reportToDate, {datePattern: "dd/MM/yyyy", selector: "date"});



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

                document.getElementById("paymentReportPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=paymentReport&fromDate=" + formJson.reportFromDate +
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

            document.getElementById("paymentReportPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=paymentReport&fromDate=" + formJson.reportFromDate +
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

var CreditLimitReport = {
    init: function() {
        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("creditCurrencyValue").innerHTML = cur.currency;
            });
        });
    },
    populateValues: function() {

    },
    showDate: function(value) {

        if (value == "ALL") {
            dojo.byId("creditLimitReportDateRangeDiv").style.display = "none";
        } else {
            dojo.byId("creditLimitReportDateRangeDiv").style.display = "block";
        }
    },
    generate: function() {

        var formJson = JSON.parse(dojo.formToJson("creditLimitReportForm"), true);
        dojo.byId("creditLimitReportActionButtonDiv").style.display = "block";
//        var fromDate = dojo.date.locale.format(formJson.reportFromDate, {datePattern: "dd/MM/yyyy", selector: "date"});
//        var toDate = dojo.date.locale.format(formJson.reportToDate, {datePattern: "dd/MM/yyyy", selector: "date"});


        if (formJson.forDateRange == "SELECTIVE") {

            var pageNode = dojo.byId("creditLimitReportDateRangeDiv");
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
                document.getElementById("creditLimitReportIframe").src = "";
                dojo.byId("creditLimitReportActionButtonDiv").style.display = "none";
            } else {
                dojo.byId("creditLimitReportActionButtonDiv").style.display = "block";
                document.getElementById("creditLimitReportIframe").src = "pdf/creditLimitReport?fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange;

                document.getElementById("creditLimitReportCsv").setAttribute("href", "" + core.getBaseURL() + "/pdf/creditLimitReport?format=csv&fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange);

                document.getElementById("creditLimitReportPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=creditLimitReport&fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange +
                        "&filename=credit-limit-report");

            }


        } else {
            dojo.byId("creditLimitReportActionButtonDiv").style.display = "block";
            document.getElementById("creditLimitReportIframe").src = "pdf/creditLimitReport?fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange;

            document.getElementById("creditLimitReportCsv").setAttribute("href", "" + core.getBaseURL() + "/pdf/creditLimitReport?format=csv&fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange);

            document.getElementById("creditLimitReportPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=creditLimitReport&fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange +
                    "&filename=credit-limit-report");
        }
    },
    cancel: function() {
        dijit.byId("creditLimitReportForm").reset();
        document.getElementById("creditLimitReportIframe").src = "";
        dojo.byId("creditLimitReportActionButtonDiv").style.display = "none";
    }


};

var PaymentPendingReport = {
    init: function() {
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

    },
    showDate: function(value) {

        if (value == "ALL") {
            dojo.byId("paymentPendingReportDateRangeDiv").style.display = "none";
        } else {
            dojo.byId("paymentPendingReportDateRangeDiv").style.display = "block";
        }
    },
    generate: function() {

        var formJson = JSON.parse(dojo.formToJson("paymentPendingReportForm"), true);
        dojo.byId("paymentPendingReportActionButtonDiv").style.display = "block";
//        var fromDate = dojo.date.locale.format(formJson.reportFromDate, {datePattern: "dd/MM/yyyy", selector: "date"});
//        var toDate = dojo.date.locale.format(formJson.reportToDate, {datePattern: "dd/MM/yyyy", selector: "date"});



        if (formJson.forDateRange == "SELECTIVE") {

            var pageNode = dojo.byId("paymentPendingReportDateRangeDiv");
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
                document.getElementById("paymentPendingReportIframe").src = "";
                dojo.byId("paymentPendingReportActionButtonDiv").style.display = "none";
            } else {
                dojo.byId("paymentPendingReportActionButtonDiv").style.display = "block";
                document.getElementById("paymentPendingReportIframe").src = "pdf/paymentPendingReport?fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange;

                document.getElementById("paymentPendingReportCsv").setAttribute("href", "" + core.getBaseURL() + "/pdf/paymentPendingReport?format=csv&fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange);

                document.getElementById("paymentPendingReportPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=paymentPendingReport&fromDate=" + formJson.reportFromDate +
                        "&toDate=" + formJson.reportToDate +
                        "&forDateRange=" + formJson.forDateRange +
                        "&filename=Payment-Pending-report");

            }


        } else {
            dojo.byId("paymentPendingReportActionButtonDiv").style.display = "block";
            document.getElementById("paymentPendingReportIframe").src = "pdf/paymentPendingReport?fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange;

            document.getElementById("paymentPendingReportCsv").setAttribute("href", "" + core.getBaseURL() + "/pdf/paymentPendingReport?format=csv&fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange);

            document.getElementById("paymentPendingReportPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=paymentPendingReport&fromDate=" + formJson.reportFromDate +
                    "&toDate=" + formJson.reportToDate +
                    "&forDateRange=" + formJson.forDateRange +
                    "&filename=Payment-Pending-report");
        }

    },
    cancel: function() {
        document.getElementById("paymentPendingReportIframe").src = "";
        dijit.byId("paymentPendingReportForm").reset();
        dojo.byId("paymentPendingErrorDiv").style.display = "none";
        dojo.byId("paymentPendingReportActionButtonDiv").style.display = "none";
    }
};



window.BillableItemReport = BillableItemReport;
window.SignUpReport = SignUpReport;
window.PaymentReport = PaymentReport;
window.CreditLimitReport = CreditLimitReport;
window.PaymentPendingReport = PaymentPendingReport;