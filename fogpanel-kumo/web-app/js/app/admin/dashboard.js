"use strict";
var App = {
    _loader: null,
    hideLoader: function() {
        this._loader.style.display = "none";
    }
};
var AdminUpdateTimer = {
    sync: function() {

        var ticketCount = 0;
        var ticNotificationData = [];
        var ticketNotification = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/notification"
        });

        ticketNotification.query().then(function(data) {
            dojo.forEach(data, function(notificationDataItem) {
                if (ticketCount <= 5) {
                    ticNotificationData[ticketCount] = {link: notificationDataItem.link, icon: notificationDataItem.icon, value: "TicketId: " + notificationDataItem.ticId + " - " + notificationDataItem.subject};
                }
                ticketCount++;
            });
            connect.publish("/FogPanel/ticket/notifications", [ticNotificationData]);
            dojo.byId("ticketCount").innerHTML = ticketCount;
        });

        var usageCount = 0;
        var usageNotificationData = [];
        var usageNotification = new JsonRest({
            target: core.getContextPath() + "/api/notification/cloudUsage"
        });

        usageNotification.query().then(function(data) {
            dojo.forEach(data, function(usageNotificationDataItem) {
                if (usageNotificationDataItem.viewed == false) {
                    if (usageCount <= 5) {
                        usageNotificationData[usageCount] = {link: usageNotificationDataItem.link, icon: usageNotificationDataItem.icon, value: usageNotificationDataItem.description};
                    }
                    usageCount++;
                }
            });
            connect.publish("/FogPanel/cloudusage/notifications", [usageNotificationData]);
            dojo.byId("cloudUsageStatusCount").innerHTML = usageCount;
        });
    }
};



var AdminDashboard = {
    'init': function() {
    },
    'populateValues': function() {

        var usageInfo = new JsonRest({
            target: core.getContextPath() + "/api/account/usage"
        });

        usageInfo.query().then(function(data) {
            dojo.forEach(data, function(usageData) {
                dojo.byId("totalDaily").innerHTML = usageData.daily.toFixed(2);
                dojo.byId("totalMonthly").innerHTML = usageData.currMonthCost.toFixed(2);
                dojo.byId("totalPayment").innerHTML = usageData.currMonthPayment.toFixed(2);

                dojo.byId("dailyUsd").innerHTML = usageData.currency;
                dojo.byId("monthlyUsd").innerHTML = usageData.currency;
                dojo.byId("paymentUsd").innerHTML = usageData.currency;

                dojo.byId("dailyDate").innerHTML = usageData.dailyDate;
                dojo.byId("monDate").innerHTML = usageData.currMonth;
                dojo.byId("monPayDate").innerHTML = usageData.currMonth;
                if (usageData.activeAccount) {
                    dojo.byId("active").innerHTML = usageData.activeAccount - 1;
                } else {
                    dojo.byId("active").innerHTML = 0;
                }
                if (usageData.disableAccount) {
                    dojo.byId("disable").innerHTML = usageData.disableAccount;
                } else {
                    dojo.byId("disable").innerHTML = 0;
                }
                if (usageData.inactiveAccount) {
                    dojo.byId("inactive").innerHTML = usageData.inactiveAccount;
                } else {
                    dojo.byId("inactive").innerHTML = 0;
                }
                if (usageData.suspendAccount) {
                    dojo.byId("suspend").innerHTML = usageData.suspendAccount;
                } else {
                    dojo.byId("suspend").innerHTML = 0;
                }
                if (usageData.cancelAccount) {
                    dojo.byId("cancel").innerHTML = usageData.cancelAccount;
                } else {
                    dojo.byId("cancel").innerHTML = 0;
                }
                if (usageData.closedAccount) {
                    dojo.byId("closed").innerHTML = usageData.closedAccount;
                } else {
                    dojo.byId("closed").innerHTML = 0;
                }
                                
                if (usageData.enableSignup === "false") {
                    dojo.byId("adminCostCheckListDiv").style.display = "block";
                } else {
                    dojo.byId("adminCostCheckListDiv").style.display = "none";
                }
                
                if (usageData.orgSetting === "false") {
                    dojo.byId("orgLi").style.display = "block";
                } else {
                    dojo.byId("orgLi").style.display = "none";
                }
                
                if (usageData.snap === "false") {
                    dojo.byId("snapLi").style.display = "block";
                } else {
                    dojo.byId("snapLi").style.display = "none";
                }
                if (usageData.vmSnap === "false") {
                    dojo.byId("vmsnapLi").style.display = "block";
                } else {
                    dojo.byId("vmsnapLi").style.display = "none";
                }
                if (usageData.ip === "false") {
                    dojo.byId("ipLi").style.display = "block";
                } else {
                    dojo.byId("ipLi").style.display = "none";
                }
                if (usageData.band === "false") {
                    dojo.byId("bandLi").style.display = "block";
                } else {
                    dojo.byId("bandLi").style.display = "none";
                }
            
                if (usageData.firewall === "false") {
                    dojo.byId("firewallLi").style.display = "block";
                } else {
                    dojo.byId("firewallLi").style.display = "none";
                }
                

            });
        });

    },
    'loadBillableItemChart': function() {

        var bItemChartStore = new JsonRest({
            target: core.getContextPath() + "/api/invoice/chart/billableItem"
        });

        var chartData = [];
        bItemChartStore.query().then(function(data) {
            dojo.forEach(data, function(bItemData) {
                if (bItemData.computeOffer == 0 && bItemData.template == 0 && bItemData.diskOffer == 0 && bItemData.snapshot == 0 && bItemData.recurring == 0 && bItemData.custom == 0 && bItemData.bandWidth == 0
                        && bItemData.mSnap == 0 && bItemData.mVM == 0 && bItemData.mDisk == 0 && bItemData.mSnap == 0 && bItemData.mVmSnap == 0 && bItemData.vmSnap == 0) {

                    dojo.byId("billableItemChart").style.height = "150px";

                } else {

                    dojo.byId("billableItemChart").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";

                    if (bItemData.computeOffer > 0) {
                        chartData.push({y: bItemData.computeOffer,
                            text: "RunningVm",
                            tooltip: Math.round((bItemData.computeOffer) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.mSnap > 0) {
                        chartData.push({y: bItemData.mSnap,
                            text: "Monthly Snapshot",
                            tooltip: Math.round((bItemData.mSnap) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.mVM > 0) {
                        chartData.push({y: bItemData.mVM,
                            text: "Monthly VM",
                            tooltip: Math.round((bItemData.mVM) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.mDisk > 0) {
                        chartData.push({y: bItemData.mDisk,
                            text: "Monthly Disk",
                            tooltip: Math.round((bItemData.mDisk) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.template > 0) {
                        chartData.push({y: bItemData.template,
                            text: "Template",
                            tooltip: Math.round((bItemData.template) * 100) / 100 + " " + bItemData.currency
                        });
                    }

                    if (bItemData.mVmSnap > 0) {
                        chartData.push({y: bItemData.mVmSnap,
                            text: "Monthly VM Snapshot",
                            tooltip: Math.round((bItemData.mVmSnap) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.vmSnap > 0) {
                        chartData.push({y: bItemData.vmSnap,
                            text: "VM Snapshot",
                            tooltip: Math.round((bItemData.vmSnap) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.diskOffer > 0) {
                        chartData.push({y: bItemData.diskOffer,
                            text: "Volume",
                            tooltip: Math.round((bItemData.diskOffer) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.snapshot > 0) {
                        chartData.push({y: bItemData.snapshot,
                            text: "Snapshot",
                            tooltip: Math.round((bItemData.snapshot) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.recurring > 0) {
                        chartData.push({y: bItemData.recurring,
                            text: "Recurring Item",
                            tooltip: Math.round((bItemData.recurring) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.custom > 0) {
                        chartData.push({y: bItemData.custom,
                            text: "Custom Item",
                            tooltip: Math.round((bItemData.custom) * 100) / 100 + " " + bItemData.currency
                        });
                    }
                    if (bItemData.bandWidth > 0) {
                        chartData.push({y: bItemData.bandWidth,
                            text: "BandWidth",
                            tooltip: Math.round((bItemData.bandWidth) * 100) / 100 + " " + bItemData.currency
                        });
                    }


                    dojo.byId("billableItemChart").innerHTML = "";

                    var pieChart = new Chart("billableItemChart");
                    pieChart.setTheme(Tom);
                    pieChart.addPlot("default", {
                        type: "Pie",
                        font: "normal normal 10pt Tahoma",
                        fontColor: "#2C3742",
                        labelWiring: "#2C3742",
                        radius: 100,
                        labelStyle: "columns",
                        htmlLabels: true,
                        startAngle: -10
                    });

                    pieChart.addSeries("Series A", chartData);
                    new MoveSlice(pieChart, "default");
                    new Tooltip(pieChart, "default");
                    pieChart.render();



                }

            });



        });
    },
    'loadDailyCostChart': function() {

        dojo.byId("dailyCostChart").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
//        dojo.byId("dailylegende").innerHTML = "";  

        var dailyUsageChartStore = new JsonRest({
            target: core.getContextPath() + "/api/invoice/chart/dailyUsage"
        });

        var usageChartDataXAxisData = [];
        var usageChartDataCost;
        var refundChartDataCost;
        var i = 1;
        dailyUsageChartStore.query().then(function(data) {
            dojo.forEach(data, function(usageData) {
                usageChartDataCost = usageData.usageItemCostList;
                refundChartDataCost = usageData.refundCostList
                dojo.forEach(usageData.usageItemDateList, function(usageDate) {
                    usageChartDataXAxisData.push({value: i, text: usageDate});
                    i += 1;
                });
            });

            dojo.byId("dailyCostChart").innerHTML = "";

            // Create the chart within it's "holding" node
            var chart = new Chart("dailyCostChart");

            // Set the theme
            chart.setTheme(theme);

            // Add the only/default plot
            chart.addPlot("default", {
                type: "Lines",
                markers: true
            });

            // Add axes
            chart.addAxis("x", {labels: usageChartDataXAxisData});
            chart.addAxis("y", {min: 5, vertical: true, fixLower: "major", fixUpper: "major"});

            // Add the series of data
            chart.addSeries("Usage", usageChartDataCost, {stroke: {color: "#87B84B"}});
            chart.addSeries("Refund", refundChartDataCost, {stroke: {color: "#D30000"}});

            new Magnify(chart, "default");
            new Tooltip(chart, "default");

            // Render the chart!
            chart.render();

            new Legend({chart: chart, horizontal: false}, "dailylegende");
        });
    },
    'loadcreditLimitGrid': function() {

        dojo.byId("creditLimitGrid").style.display = "none";
        dojo.byId("creditLimitGridLoad").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";

        var creditLimitStore = new JsonRest({
            target: core.getContextPath() + "/api/account/creditLimit"
        });

//        var gridData = {
//            items: []
//        };
//        var gridDataList = new ItemFileWriteStore({data: gridData});
//        var gridLayout = [[
//                {'name': 'Account Id', 'field': 'accountId', 'width': '50%'},
//                {'name': 'User Name', 'field': 'userName', 'width': '50%'},
//                {'name': 'Exceeded Amount', 'field': 'amount', 'width': '50%'}
//            ]
//        ];
        var creditLimitTable = document.getElementById("creditLimitTable");

        var rowLength = document.getElementById("creditLimitTable").rows.length;

        if (rowLength - 1 > 0) {

            var clTable = document.getElementById("creditLimitTable");
            var rowCount = clTable.rows.length;
            for (var x = rowCount - 1; x > 0; x--) {
                clTable.deleteRow(x);
            }


//            for (var j = 1; j < rowLength; j++) {
//                document.getElementById("creditLimitTable").deleteRow(j);
//            }
        }

        var totalAccount = 0;
        var totalAmount = 0;
        var currency;

        creditLimitStore.query().then(function(data) {


            dojo.forEach(data, function(accountData) {

                totalAccount++;
                totalAmount = totalAmount + accountData.amount;
//                currency = accountData.currency;

            });

            if (data.length > 0) {
                dojo.forEach(data.slice(0, 5), function(accountData) {

                    var row = creditLimitTable.insertRow(1);
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    var cell3 = row.insertCell(2);
                    cell1.innerHTML = accountData.accountId;
                    cell2.innerHTML = accountData.account;
                    cell3.innerHTML = accountData.amount;
                });

                dojo.byId("creditLimitGridLoad").innerHTML = "";
                dojo.byId("creditLimitGrid").style.display = "block";

            } else {
                dojo.byId("creditLimitGridLoad").innerHTML = "";
            }

            dojo.byId("creditAccTotal").innerHTML = totalAccount;
            dojo.byId("exAmount").innerHTML = Math.round((totalAmount) * 100) / 100;
//            dojo.byId("clCurrency").innerHTML = currency;


//            var creditLimitGrid = new EnhancedGrid({
//                store: gridDataList,
//                structure: gridLayout,
//                autoHeight: true,
//                plugins: {
//                    pagination: {
//                        pageSizes: ["3", "10", "All"],
//                        description: true,
//                        sizeSwitch: true,
//                        pageStepper: true,
//                        gotoButton: true,
//                        /*page step to be displayed*/
//                        maxPageStep: 4,
//                        /*position of the pagination bar*/
//                        position: "bottom"
//                    }
//                }
//            });
//            creditLimitGrid.placeAt("creditLimitGrid");
//            creditLimitGrid.startup();
        });

    },
    'loadPendingPaymentGrid': function() {

        dojo.byId("pendingPaymentGrid").style.display = "none";
        dojo.byId("pendingPaymentGridLoad").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";


        var paymentPendingStore = new JsonRest({
            target: core.getContextPath() + "/api/payment/pending"
        });

//        var gridData = {
//            items: []
//        };
//        var gridDataList = new ItemFileWriteStore({data: gridData});
//        var gridLayout = [[
//                {'name': 'User Name', 'field': 'userName', 'width': '50%'},
//                {'name': 'Exceeded Amount', 'field': 'amount', 'width': '50%'}
//            ]
//        ];

        var pendingPaymentTable = document.getElementById("pendingPaymentTable");

        var rowLength = document.getElementById("pendingPaymentTable").rows.length;

        if (rowLength - 1 > 0) {



            var payTable = document.getElementById("pendingPaymentTable");
            var rowCount = payTable.rows.length;
            for (var x = rowCount - 1; x > 0; x--) {
                payTable.deleteRow(x);
            }

//            for (var j = 1; j < rowLength; j++) {
//                document.getElementById("pendingPaymentTable").deleteRow(j);
//            }
        }

        var totalAccount = 0;
        var totalAmount = 0;
//        var currency; 

        paymentPendingStore.query().then(function(data) {

            dojo.forEach(data, function(accountData) {
                totalAccount++;
                totalAmount = totalAmount + accountData.amount;
//                currency = accountData.currency;
            });

            if (data.length > 0) {
                dojo.forEach(data.slice(0, 5), function(accountData) {

                    var row = pendingPaymentTable.insertRow(1);
                    var cell1 = row.insertCell(0);
                    var cell2 = row.insertCell(1);
                    var cell3 = row.insertCell(2);
                    cell1.innerHTML = accountData.accountId;
                    cell2.innerHTML = accountData.account;
                    cell3.innerHTML = Math.round((accountData.amount) * 100) / 100;
                });

                dojo.byId("pendingPaymentGridLoad").innerHTML = "";
                dojo.byId("pendingPaymentGrid").style.display = "block";

            } else {
                dojo.byId("pendingPaymentGridLoad").innerHTML = "";
            }

            dojo.byId("payAccTotal").innerHTML = totalAccount;
            dojo.byId("payAmount").innerHTML = Math.round((totalAmount) * 100) / 100;
//            dojo.byId("payCurrency").innerHTML = currency;



//            var creditLimitGrid = new EnhancedGrid({
//                store: gridDataList,
//                structure: gridLayout,
//                autoHeight: true,
//                plugins: {
//                    pagination: {
//                        pageSizes: ["3", "10", "All"],
//                        description: true,
//                        sizeSwitch: true,
//                        pageStepper: true,
//                        gotoButton: true,
//                        /*page step to be displayed*/
//                        maxPageStep: 4,
//                        /*position of the pagination bar*/
//                        position: "bottom"
//                    }
//                }
//            });
//            creditLimitGrid.placeAt("creditLimitGrid");
//            creditLimitGrid.startup();
        });
    },
    'loadIncomeForecast': function() {
        var incomeForecastStore = new JsonRest({
            target: core.getContextPath() + "/api/invoice/incomeForecast"
        });

        incomeForecastStore.query().then(function(data) {
            dojo.forEach(data, function(incomeData) {
                dojo.byId("preMonth").innerHTML = incomeData.preMonth + " " + incomeData.currency;
                dojo.byId("quat").innerHTML = incomeData.qut + " " + incomeData.currency;
                dojo.byId("half").innerHTML = incomeData.half + " " + incomeData.currency;
                dojo.byId("annual").innerHTML = incomeData.year + " " + incomeData.currency;
            });
        });
    },
    'loadSystemOverview': function() {
        var systemStore = new JsonRest({
            target: core.getContextPath() + "/api/config/systemOverview"
        });

        systemStore.query().then(function(data) {
            dojo.forEach(data, function(systemData) {
                dojo.byId("version").innerHTML = systemData.version;
                if(systemData.licenseStatus === "EMERGENCY_TRIAL") {
                    dojo.byId("licenseStatus").innerHTML = "Emergency Trial";
                } else if(systemData.licenseStatus === "EXPIRED") {
                    dojo.byId("licenseStatus").innerHTML = "Expired";
                } else {
                    dojo.byId("licenseStatus").innerHTML = systemData.licenseStatus;
                }
                dojo.byId("activeSockets").innerHTML = systemData.activeSockets;
                dojo.byId("instanceID").innerHTML = systemData.instanceID;
                dojo.byId("licenseeEmail").innerHTML = systemData.licenseeEmail;
//                dojo.byId("availableHost").innerHTML = systemData.availableHost;
            });
        });
    },
    revalidateLicense : function() {
        var systemStore = new JsonRest({
            target: core.getContextPath() + "/api/config/revalidateLicense"
        });
        
        dojo.byId("systemOverviewLoading").style.display = "block";
        dojo.byId("systemOverviewInfo").style.display = "none";
        
        systemStore.query().then(function(data) {
            AdminDashboard.loadSystemOverview();
            dojo.byId("systemOverviewLoading").style.display = "none";
            dojo.byId("systemOverviewInfo").style.display = "block";
        });
    }
};

var CalendarInfo = {
    init: function() {
    },
    populateValues: function() {
        var billingAlerts = new JsonRest({
            target: core.getContextPath() + "/api/notification/billingAlerts"
        });
        var count = 0;
        var eventNo = 1;
        var eventData = [];
        billingAlerts.query().then(function(data) {
            dojo.forEach(data, function(alertData) {
                dojo.forEach(alertData.notification, function(notificationData) {
                    var date = notificationData.date.split("-");
                    var year = parseInt(date[2]);
                    var month = parseInt(date[1]) - 1;
                    var day = parseInt(date[0]);
                    eventData[count] = {
                        id: notificationData.id,
                        summary: "Event-" + eventNo,
                        startTime: new Date(year, month, day, 0),
                        endTime: new Date(year, month, day, 23),
                        data: notificationData.description
                    };
                    count++;
                    eventNo++;
                });
                CalendarInfo.loadCalander(eventData);
            });
        });
    },
    loadCalander: function(eventData) {
        dojo.byId("maintainCalendarDiv").innerHTML = "";
        var notifiCalendar = new Calendar({
            onItemClick: function(e) {
                CalendarInfo.openEvent(e);
            },
            store: new Observable(new Memory({data: eventData})),
            dateInterval: "month",
            style: "position:relative;height:800px"
        }).placeAt("maintainCalendarDiv");
        notifiCalendar.startup();
    },
    openEvent: function(event) {
        dojo.byId("currentItemId").value = event.item.id;
        dojo.byId("maintenanceErrorMsg").style.display = "none";
        dijit.byId("viewEventPage").show();
        dojo.byId("eventDate").innerHTML = dojo.date.locale.format(event.item.startTime, {datePattern: "dd/MM/yyyy", selector: "date"});
        dijit.byId("cloudMaintainDescription").setValue(event.item.data);
    },
    update: function() {
        var maintainRestore = new JsonRest({
            target: core.getContextPath() + "/api/cloudMaintenance/"
        });
        var itemId = dojo.byId("currentItemId").value;
        var description = dijit.byId("cloudMaintainDescription").get("value");
        if (description === " " || description === "\n      " || description == "\n\n\n\n" || description === "" || description === "    " || description == " ... \n\n\n" || description == " ... " || description == " \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") {
            dojo.byId("maintenanceErrorMsg").style.display = "block";
        } else {
            dojo.byId("maintenanceErrorMsg").style.display = "none";
            maintainRestore.put({
                id: itemId,
                description: dijit.byId("cloudMaintainDescription").getValue()
            }).then(function(result) {
                if (result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.ItemUpdated, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("viewEventPage").hide();
                    CalendarInfo.populateValues();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.ItemUpdateError, "error");
                    registry.byId("appToaster").show();
                }
            })
        }
    },
    deleteItem: function() {
        var maintainRestore = new JsonRest({
            target: core.getContextPath() + "/api/cloudMaintenance/delete"
        });
        var itemId = dojo.byId("currentItemId").value;
        maintainRestore.put({
            itemId: itemId
        }).then(function(result) {
            if (result == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.ItemDeleted, "message");
                registry.byId("appToaster").show();
                dijit.byId("cloudMaintainDescription").reset();
                dijit.byId("viewEventPage").hide();
                CalendarInfo.populateValues();
            } else {
                registry.byId("appToaster").setContent(translator.common.message.cannotDelete, "error");
                registry.byId("appToaster").show();
            }
        });
    }
};



var CloudMaintenance = {
    _zoneRestStore: "",
    _maintenanceStore: "",
    init: function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        this._maintenanceStore = new JsonRest({
            target: core.getContextPath() + "/api/cloudMaintenance/"
        });
    },
    populateValues: function() {

        var sel = dojo.byId('maintenanceZoneList');
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(zone) {
                var opt = document.createElement('option');
                opt.innerHTML = zone.aliasName;
                opt.value = zone.id;
                sel.appendChild(opt);
            });
        });

        dijit.byId('cloudMaintenanceDate').set('value', new Date());
    },
    add: function() {

        var zoneList = dojo.byId("maintenanceZoneList");
        var date = dojo.date.locale.format(dijit.byId("cloudMaintenanceDate").value, {datePattern: "dd/MM/yyyy", selector: "date"});
        var description = dijit.byId("cloudMaintenanceDateDescription");

        var selectedZoneArray = new Array();
        var j;
        var countUser = 0;
        for (j = 0; j < zoneList.options.length; j++) {
            if (zoneList.options[j].selected) {
                selectedZoneArray[countUser] = zoneList.options[j].value;
                countUser++;
            }
        }
        dijit.byId("maintainButton").setAttribute('style', 'display: none');
        dojo.byId("forgotPasswordLoader").style.display = "block";

        dojo.byId("maintenanceZoneList").disabled = true;
        dijit.byId("cloudMaintenanceDate").set("disabled", true);
        dijit.byId("cloudMaintenanceDateDescription").set("disabled", true);

        this._maintenanceStore.add({
            zones: selectedZoneArray.toString(),
            date: date,
            description: description.value
        }).then(function(data) {
            dojo.forEach(data, function(data) {
                if (data.result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.itemAdded, "message");
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/dashboard/maintainance");

                    dijit.byId("cloudMaintenanceForm").reset();
                    dijit.byId("maintainButton").setAttribute('style', 'display: block');
                    dojo.byId("forgotPasswordLoader").style.display = "none";
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("maintainButton").setAttribute('style', 'display: block');
                    dojo.byId("forgotPasswordLoader").style.display = "none";
                }
            });
            dojo.byId("maintenanceZoneList").disabled = false;
            dijit.byId("cloudMaintenanceDate").set("disabled", false);
            dijit.byId("cloudMaintenanceDateDescription").set("disabled", false);
        });
    },
    cancel: function() {
        dijit.byId("cloudMaintenanceForm").reset();
    }
};

var DashboardWizard = {
    _count: 1,
    init: function() {

    },
    populateValues: function() {
        var wizard = dijit.byId("setupWizard");
    },
    go: function() {
    },
    activate: function() {
        var wizard = dijit.byId("setupWizard");
        wizard.setActive(3);
    },
    next: function() {
        dijit.byId("wizardNextButton").setAttribute('disabled', false);
        dijit.byId("wizardPrevButton").setAttribute('disabled', false);
        this._count = this._count + 1;
        var wizard = dijit.byId("wizard");
        wizard.enable(this._count);
        if (this._count == 6) {
            dijit.byId("wizardPrevButton").setAttribute('disabled', false);
            dijit.byId("wizardNextButton").setAttribute('disabled', true);
        }
    },
    previous: function() {
        var wizard = dijit.byId("wizard");
        if (this._count == 6) {
            wizard.disable(this._count);
            dijit.byId("wizardNextButton").setAttribute('disabled', false);
            dijit.byId("wizardPrevButton").setAttribute('disabled', false);
            this._count--;
        } else if (this._count != 1 && this._count < 6) {
            wizard.disable(this._count);

            if (this._count == 2) {
                dijit.byId("wizardNextButton").setAttribute('disabled', false);
                dijit.byId("wizardPrevButton").setAttribute('disabled', true);
            } else {
                dijit.byId("wizardNextButton").setAttribute('disabled', false);
                dijit.byId("wizardPrevButton").setAttribute('disabled', false);
            }
            this._count = this._count - 1;
        }
    },
    closeDialogue: function() {
        dijit.popup.close(dijit.byId("adminSettingsDropdown"));
    }
};

var DashboardConfig = {
    _restStore: "",
    _configPageResult: "",
    configId: [],
    init: function() {
        this._restStore = new JsonRest({
            target: core.getContextPath() + "/api/config/",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });

    },
    populateValues: function() {
        var widgets = dojo.byId("dashboardConfigWidgets");
        var pageWidgets = dijit.registry.findWidgets(widgets);

        var cloudStackUrl = dijit.byId("dashboardUrl");
        var ssoUrl = dijit.byId("dashboardssoUrl");
        var cloudApiKey = dijit.byId("dashboardApi");
        var cloudSecretKey = dijit.byId("dashboardSecretKey");

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });

        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                ssoUrl.setValue(cur.singleSignOnUrl);
                cloudStackUrl.setValue(cur.cloudStackUrl);
                cloudApiKey.setValue(cur.cloudStackApiKey);
                cloudSecretKey.setValue(cur.cloudStackSecretKey);
            });
        });


//        this._restStore.query().then(function(data) {
//            dojo.forEach(data, function(config) {  
//                if(config.name == "single.sign.on.url") {
//                    ssoUrl.setValue(config.value);
//                }else if(config.name == "CLOUD_STACK_URL") {
//                    cloudStackUrl.setValue(config.value);
//                }else if(config.name == "CLOUD_STACK_ROOT_API_KEY") {
//                   cloudApiKey.setValue(config.value);
//                }  else if(config.name == "CLOUD_STACK_ROOT_SECRET_KEY") {
//                    cloudSecretKey.setValue(config.value);
//                }
//            });
//        });
        this._configPageResult = new JsonRest({
            target: core.getContextPath() + "/api/config/test/"
        });

        this._configPageResult.query().then(function(data) {
            if (data == "OK") {

                dojo.byId("dashboardInvalidMessage").style.display = "none";

                dijit.byId("dashboardVerifyButton").set("iconClass", "dijitCheckBoxIcon true");
//                dijit.byId("dashboardVerifyButton").setAttribute("disabled", "true");
                dojo.byId("dashboardUrlLabel").innerHTML = cloudStackUrl.value;
                dojo.byId("dashboardssoUrlLabel").innerHTML = ssoUrl.value;
                dojo.byId("dashboardApiLabel").innerHTML = cloudApiKey.value;
                dojo.byId("dashboarSecretKeyLabel").innerHTML = cloudSecretKey.value;
//                dojo.query("#dashboardConfigWidgets .updatable").toggleClass("non_updatable", true);
//                dojo.query("#dashboardConfigWidgets .hide_lable").toggleClass("show_lable", true);


            } else {
                dojo.byId("dashboardInvalidMessage").style.display = "block";
                dijit.byId("dashboardVerifyButton").set("iconClass", "dijitEditorIcon dijitEditorIconDelete");
            }
        });
    },
    closeMessageBox: function() {
        var configPageResult = new JsonRest({
            target: core.getContextPath() + "/api/config/test/"
        });
        configPageResult.query().then(function(data) {
            if (data == "OK") {
//                core.router.go("#/admin/zone");
            }
        });
        dijit.byId("dashboardConfigLoader").hide();
    },
    edit: function() {
        dojo.query("#dashboardConfigWidgets .updatable").removeClass("non_updatable", true);
        dojo.query("#dashboardConfigWidgets .hide_lable").removeClass("show_lable", true);
        dijit.byId("dashboardVerifyButton").setAttribute("disabled", false);
    },
    update: function() {

        var configPageResult = new JsonRest({
            target: core.getContextPath() + "/api/config/test/"
        });

        var restStore = new JsonRest({
            target: core.getContextPath() + "/api/config/",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });

        var systemNode = dojo.byId("dashboardSystemConfigPage");
        var systemWidget = dijit.registry.findWidgets(systemNode);
        dojo.forEach(systemWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        var ssoUrl = dijit.byId("dashboardssoUrl");
        var cloudStackUrl = dijit.byId("dashboardUrl");
        var cloudApiKey = dijit.byId("dashboardApi");
        var cloudSecretKey = dijit.byId("dashboardSecretKey");
        dijit.byId("dashboardConfigLoader").show();
        dojo.byId("dashboardSuccessMessage").style.display = "none";
        dojo.byId("dashboardProcessMessage").style.display = "block";
        dojo.byId("dashboardFailMessage").style.display = "none";
        dojo.byId("dashboardMessageAction").style.display = "none";

        restStore.add({
            url: cloudStackUrl.value,
            apiKey: cloudApiKey.value,
            secretKey: cloudSecretKey.value,
            ssoUrl: ssoUrl.value,
            "class": "com.assistanz.fogpanel.Config"
        }).then(function(data) {

            if (data == "OK") {
                configPageResult.query().then(function(data) {
                    if (data == "OK") {
                        dojo.byId("dashboardInvalidMessage").style.display = "none";
                        dojo.byId("dashboardssoUrlLabel").innerHTML = ssoUrl.value;
                        dojo.byId("dashboardUrlLabel").innerHTML = cloudStackUrl.value;
                        dojo.byId("dashboardApiLabel").innerHTML = cloudApiKey.value;
                        dojo.byId("dashboarSecretKeyLabel").innerHTML = cloudSecretKey.value;

                        dijit.byId("dashboardVerifyButton").set("iconClass", "dijitCheckBoxIcon true");
//                            dijit.byId("dashboardVerifyButton").setAttribute("disabled", true);

//                            dojo.query("#dashboardConfigWidgets .updatable").toggleClass("non_updatable", true);
//                            dojo.query("#dashboardConfigWidgets .hide_lable").toggleClass("show_lable", true);

                        dojo.byId("dashboardSuccessMessage").style.display = "block";
                        dojo.byId("dashboardProcessMessage").style.display = "none";
                        dojo.byId("dashboardFailMessage").style.display = "none";
                        dojo.byId("dashboardMessageAction").style.display = "block";

                    } else {

                        dojo.byId("dashboardInvalidMessage").style.display = "block";

                        dijit.byId("dashboardVerifyButton").setAttribute("disabled", false);

                        dijit.byId("dashboardVerifyButton").set("iconClass", "dijitEditorIcon dijitEditorIconDelete");
                        dijit.byId("dashboardVerifyButton").set("label", "Reverify");
                        cloudStackUrl.reset();
                        cloudApiKey.reset();
                        cloudSecretKey.reset();

                        dojo.byId("dashboardSuccessMessage").style.display = "none";
                        dojo.byId("dashboardProcessMessage").style.display = "none";
                        dojo.byId("dashboardFailMessage").style.display = "block";
                        dojo.byId("dashboardMessageAction").style.display = "block";
                    }
                });

            } else {
                dojo.byId("dashboardInvalidMessage").style.display = "block";

                dijit.byId("dashboardVerifyButton").setAttribute("disabled", false);

                dojo.byId("dashboardSuccessMessage").style.display = "none";
                dojo.byId("dashboardProcessMessage").style.display = "none";
                dojo.byId("dashboardFailMessage").style.display = "block";
                dojo.byId("dashboardMessageAction").style.display = "block";
            }
        });
    }
};
var LicenseCheck = {
    init: function() {
        
        var systemStore = new JsonRest({
            target: core.getContextPath() + "/api/config/systemOverview"
        });

        systemStore.query().then(function(data) {
            dojo.forEach(data, function(systemData) {
                if(systemData.licenseStatus === "EMERGENCY_TRIAL") {
                    dojo.byId("licenseErrorInfo").style.display = "block";
                    dojo.byId("messageInfo").innerHTML = "FogPanel License is in Emergency Trial, This may expire any minute. Contact support";
                } else if(systemData.licenseStatus === "EXPIRED") {
                    dojo.byId("licenseErrorInfo").style.display = "block";
                    dojo.byId("messageInfo").innerHTML = "FogPanel License has Expired! Renew your license or contact support";
                } else {
                    dojo.byId("licenseErrorInfo").style.display = "none";
                }
            });
        });
    }
};

var ConfigTest = {
    init: function() {
        var navigator = dijit.byId("templateNavigator");
        navigator.enableNavigator();
        var varticalMenuBar = dijit.byId("verticalMenu");
        varticalMenuBar.enable();

        domClass.remove(dojo.byId("contentArea"), "diableSideMenu");

        var config = new JsonRest({
            target: core.getContextPath() + "/api/config/test/"
        });

        config.query().then(function(data) {
            if (data == "OK") {
            } else {
                registry.byId('appToaster').setContent(translator.common.message.cloudStackConfigMissing, 'error');
                registry.byId('appToaster').show();
                dojo.byId("errorInfo").style.display = "block";
            }

//            var configRest = new JsonRest({
//                target: core.getContextPath()+"/api/config"
//            });
//            configRest.query().then(function(data) {
//                dojo.forEach(data, function(resultData) {                    
//                    if(resultData.name == "organisation.billing.logo") {                       
//                        var logo = dojo.byId("fogLogo");                    
//                        navigator.setLogo(logo, resultData.value);
//                    }
//                })
//            })

        });


    },
    showSideMenu: function() {
        var varticalMenuBar = dojo.byId("verticalMenu");
        var newClass = "";
        newClass = varticalMenuBar.className.split(' ')[1]; // first class
        if (newClass == "display") {
            domClass.remove(varticalMenuBar, "display");
        } else if (newClass == "" || newClass == undefined || newClass != "display") {
            domClass.add(varticalMenuBar, "display");
        }
    }
};

var DashboardFogWizardComputingOffer = {
    name: "",
    id: "",
    _zoneWidget: "",
    _podWidget: "",
    _clusterWidget: "",
    description: "",
    cpuNumber: "",
    cpuSpeed: "",
    cpuMemory: "",
    offerHa: "",
    isSystem: "",
    networkRate: "",
    hostTag: "",
    storageTags: "",
    storageType: "",
    limitCpuUse: "",
    serviceWidget: "",
    _podList: "",
    computingOfferNode: "",
    computingOfferWidget: "",
    _computingOfferRestStore: "",
    computingZoneWidgets: "",
    _zoneRestStore: "",
    _podRestStore: "",
    _clusterRestStore: "",
    _storageTagRestStore: "",
    _hostTagRestStore: "",
    _storageTagWidget: "",
    init: function() {
        this._computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        this._podRestStore = new JsonRest({
            target: core.getContextPath() + "/api/pod/"
        });

        this._clusterRestStore = new JsonRest({
            target: core.getContextPath() + "/api/cluster/"
        });

        this._storageTagRestStore = new JsonRest({
            target: core.getContextPath() + "/api/storagePool/"
        });

        this._hostTagRestStore = new JsonRest({
            target: core.getContextPath() + "/api/host/"
        });


    },
    populateValues: function() {

        if (dijit.byId("dashboardComputingOfferZoneListItem")) {
            dijit.byId("dashboardComputingOfferZoneListItem").destroyRecursive();
        }

        if (dijit.byId("dashboardDeleteComputingOfferDialog")) {
            dijit.byId("dashboardDeleteComputingOfferDialog").destroyRecursive();
        }

        var clusterRestStore = this._clusterRestStore;

        var hostTagRestStore = this._hostTagRestStore;

        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Pod"}]
        };

        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Cluster"}]
        };

        var storageTagOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Storage Tag"}]
        };

        var hostTagOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Host Tag"}]
        };

        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
        var hostTagList = new ItemFileWriteStore({data: hostTagOptions});

        if (dijit.byId("dashboardComputingOfferStorageTag") && dijit.byId("dashboardComputingOfferHostTag") &&
                dijit.byId("dashboardComputingOfferClusterList") && dijit.byId("dashboardComputingOfferPodList")) {
            dijit.byId("dashboardComputingOfferStorageTag").destroyRecursive();
            dijit.byId("dashboardComputingOfferHostTag").destroyRecursive();
            dijit.byId("dashboardComputingOfferClusterList").destroyRecursive();
            dijit.byId("dashboardComputingOfferPodList").destroyRecursive();
        }
        this._storageTagWidget = new Select({
            id: "dashboardComputingOfferStorageTag",
            store: storageTagList
        }).placeAt("dashboardComputingOfferStorageTagList");
        this._storageTagWidget.startup();

        this._hostTagWidge = new Select({
            id: "dashboardComputingOfferHostTag",
            store: hostTagList,
            onChange: function() {
                var hostTagCapacityRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/host/capacity/"
                });
                hostTagCapacityRestStore.get(this.value).then(function(hostListItems) {
                    dojo.forEach(hostListItems, function(currentHost) {
                        dijit.byId("dashboardServiceCpuNumber").attr("constraints", {max: currentHost.cpuNumberTotal, min: 1, pattern: '#'});
                        dijit.byId("dashboardServiceCpuSpeed").attr("constraints", {max: currentHost.coreCpuSpeedTotal, min: 128, pattern: '#'});
                        dijit.byId("dashboardServiceMemory").attr("constraints", {max: currentHost.memoryTotal, min: 512, pattern: '#'});
                    });
                });
            }
        }).placeAt("dashboardComputingOfferHostTagList");
        this._hostTagWidge.startup();

        this._clusterWidget = new Select({
            id: "dashboardComputingOfferClusterList",
            store: clusterList,
            onChange: function() {
                dojo.byId("dashboardComputingClusterName").innerHTML = this.get("displayedValue");
                dojo.query("#dashboardServiceListItems .WizardListItem").forEach(dojo.destroy);
                dojo.query("#dashboardServiceListItems .WizardZoneInfo").forEach(dojo.destroy);
                dojo.byId("computViewAllTag").style.display = "block";
                dijit.byId("dashboardServiceCpuNumber").attr("constraints", {max: 100, min: 1, pattern: '#'});
                dijit.byId("dashboardServiceCpuSpeed").attr("constraints", {max: 20000, min: 128, pattern: '#'});
                dijit.byId("dashboardServiceMemory").attr("constraints", {max: 1024000, min: 512, pattern: '#'});

                var computingOfferRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/computingOffer/"
                });

                var hostTagWidget = dijit.byId("dashboardComputingOfferHostTag");
                var storageTagWidget = dijit.byId("dashboardComputingOfferStorageTag");
                var hostOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var storageTagOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var currentHostTagList = new ItemFileWriteStore({data: hostOptions});
                var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
                if (this.value == "option") {
                    currentHostTagList.newItem({id: "option", name: "Select Host"});
                    hostTagWidget.setStore(currentHostTagList);
                    storageTagList.newItem({id: "option", name: "Select Storage"});
                    storageTagWidget.setStore(currentHostTagList);
                    dojo.byId("computViewAllTag").style.display = "none";

                    dijit.byId("dashboardServiceAddButton").set("disabled", true);
                    dijit.byId("dashboardServiceCancelButton").set("disabled", true);


                } else {
                    dijit.byId("dashboardServiceAddButton").set("disabled", false);
                    computingOfferRestStore.query({clusterReferenceId: this.value}).then(function(data) {
                        dojo.forEach(data, function(computingOfferData) {

                            var computingOfferListItem = new FogPanel.WizardListItem({
                                heading: computingOfferData.name,
                                description: computingOfferData.description,
                                onClick: function() {
                                    DashboardFogWizardComputingOffer.returnComputingOffer(this);
                                },
                                onDeleteTagClick: function() {
                                    DashboardFogWizardComputingOffer.deleteCurrentWidget(this, "false");
                                },
                                additionalProperties: {
                                    id: computingOfferData.id,
                                    serviceDescription: computingOfferData.description,
                                    serviceName: computingOfferData.name,
                                    baseOs: computingOfferData.baseOs,
                                    cpuNumber: computingOfferData.cpuNumber,
                                    bandWidth: computingOfferData.bandWidth,
                                    cpuSpeed: computingOfferData.coreCpuSpeed,
                                    cpuMemory: computingOfferData.memory,
                                    networkRate: computingOfferData.networkRate,
                                    hostTag: computingOfferData.hostTags,
                                    storageTags: computingOfferData.tags,
                                    offerHa: computingOfferData.offerHA,
                                    isSystem: computingOfferData.isSystem,
                                    storageType: computingOfferData.storageType,
                                    cpuCap: computingOfferData.limitCpuUse,
                                    zoneCosts: [],
                                    heading: computingOfferData.name,
                                    description: computingOfferData.description,
                                    widgetId: computingOfferData.id
                                }
                            });
                            computingOfferListItem.placeAt("dashboardServiceList");
                            computingOfferListItem.startup();
                        });
                    });
                }
                hostTagRestStore.get(this.value).then(function(hostListItems) {
                    dojo.forEach(hostListItems, function(currentHostTag) {

                        if (currentHostTag.hostTag == null || currentHostTag.hostTag == "null") {
                            currentHostTagList.newItem({id: "option", name: "No Tag"});

                        } else {
                            currentHostTagList.newItem({id: currentHostTag.hostReferenceId, name: currentHostTag.hostTag});
                        }

                    });
                    hostTagWidget.setStore(currentHostTagList);
                });
                var storageTagRestData = new JsonRest({
                    target: core.getContextPath() + "/api/storagePool"
                });
                var storageType = dijit.byId("dashboardServiceStorageType").attr('displayedValue');



                storageTagRestData.query({
                    clusterReferenceId: this.value,
                    storageType: storageType
                }).then(function(data) {
                    if (data.length != 0) {
                        dojo.forEach(data, function(currentStorageTag) {
                            if (currentStorageTag.storageTag == null || currentStorageTag.storageTag == "null") {
                                storageTagList.newItem({id: "option", name: "No Tag"});

                            } else {
                                storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId, name: currentStorageTag.storageTag});
                            }

                        });
                    } else {
                        storageTagList.newItem({id: "option", name: "No Tag"});
                    }
                    storageTagWidget.setStore(storageTagList);
                });
            }
        }).placeAt("dashboardComputingOfferCluster");
        this._podWidget = new Select({
            id: "dashboardComputingOfferPodList",
            store: podList,
            onChange: function() {

                dojo.byId("dashboardComputingPodName").innerHTML = this.get("displayedValue");
                var clusterWidget = dijit.byId("dashboardComputingOfferClusterList");
                var clusterOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
                if (this.value == "option") {
                    currentClusterList.newItem({id: "option", name: "Select Cluster"});
                    clusterWidget.setStore(currentClusterList);
                }
                clusterRestStore.get(this.value).then(function(clusterListItems) {

                    dojo.forEach(clusterListItems, function(currentcluster) {

                        currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                    });
                    clusterWidget.setStore(currentClusterList);
                });
            }
        }).placeAt("dashboardComputingOfferPod");

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Zone"}]
        };

        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        //    if(this.value == "option") {
        //        
        //    }
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, referenceId: el.referenceZoneId, name: el.aliasName});
            });
        });
        this._zoneWidget = new Select({
            store: zoneList,
            id: "dashboardComputingOfferZoneListItem",
            onChange: function() {
                DashboardFogWizardComputingOffer.viewSelectedZone(this);
            }
        }).placeAt("dashboardComputingOfferZoneList");
        this._zoneWidget.startup();
    },
    viewAllZoneInfo: function() {
        dojo.query("#dashboardServiceListItems .WizardListItem").forEach(dojo.destroy);
        dojo.query("#dashboardServiceListItems .WizardZoneInfo").forEach(dojo.destroy);


        var podRestStore = new JsonRest({
            target: core.getContextPath() + "/api/pod/"
        });

        var clusterRestStore = new JsonRest({
            target: core.getContextPath() + "/api/cluster/"
        });

        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });

        this._zoneRestStore.query().then(function(zoneData) {
            dojo.forEach(zoneData, function(zoneInfo) {
                podRestStore.get(zoneInfo.referenceZoneId).then(function(podData) {
                    dojo.forEach(podData, function(podInfo) {
                        clusterRestStore.get(podInfo.podReferenceId).then(function(clusterData) {
                            dojo.forEach(clusterData, function(clusterInfo) {
                                var count = 0;
                                computingOfferRestStore.query({clusterReferenceId: clusterInfo.clusterReferenceId,
                                    referenceZoneId: zoneInfo.referenceZoneId,
                                    podReferenceId: podInfo.podReferenceId
                                }).then(function(data) {
                                    dojo.forEach(data, function(computingOfferData) {
                                        if (count == 0) {
                                            var computingZoneInfo = new FogPanel.WizardZoneInfo({
                                                zoneName: computingOfferData.zone.name,
                                                podName: computingOfferData.pod.name,
                                                clusterName: computingOfferData.cluster.name
                                            });
                                            computingZoneInfo.placeAt("dashboardServiceList");
                                            computingZoneInfo.startup();

                                            count++;
                                        }
                                        var computingOfferListItem = new FogPanel.WizardListItem({
                                            heading: computingOfferData.name,
                                            description: computingOfferData.description,
                                            onClick: function() {
                                                DashboardFogWizardComputingOffer.returnComputingOffer(this);
                                            },
                                            onDeleteTagClick: function() {
                                                DashboardFogWizardComputingOffer.deleteCurrentWidget(this, "false");
                                            },
                                            additionalProperties: {
                                                id: computingOfferData.id,
                                                serviceDescription: computingOfferData.description,
                                                serviceName: computingOfferData.name,
                                                cpuNumber: computingOfferData.cpuNumber,
                                                cpuSpeed: computingOfferData.coreCpuSpeed,
                                                cpuMemory: computingOfferData.memory,
                                                baseOs: computingOfferData.baseOs,
                                                bandWidth: computingOfferData.bandWidth,
                                                networkRate: computingOfferData.networkRate,
                                                hostTag: computingOfferData.hostTags,
                                                storageTags: computingOfferData.tags,
                                                offerHa: computingOfferData.offerHA,
                                                isSystem: computingOfferData.isSystem,
                                                storageType: computingOfferData.storageType,
                                                cpuCap: computingOfferData.limitCpuUse,
                                                zoneCosts: [],
                                                heading: computingOfferData.name,
                                                description: computingOfferData.description,
                                                widgetId: computingOfferData.id
                                            }
                                        });
                                        computingOfferListItem.placeAt("dashboardServiceList");
                                        computingOfferListItem.startup();
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });
    },
    viewSelectedZone: function(currentZone) {
        dojo.byId("dashboardComputingZoneName").innerHTML = currentZone.get("displayedValue");
        var podWidget = dijit.byId("dashboardComputingOfferPodList");
        var currentZoneCost;

        if (dijit.byId("dashboardComputingOfferCurrentZone")) {
            dijit.byId("dashboardComputingOfferCurrentZone").destroyRecursive();
        }

        this._zoneRestStore.get(currentZone.value).then(function(selectedZoneInfo) {

            currentZoneCost = new Zone.ZoneCost({
                id: "dashboardComputingOfferCurrentZone",
                zoneName: selectedZoneInfo.aliasName,
                zoneIdNode: selectedZoneInfo.id
            });
            currentZoneCost.removeUnitCost();
            currentZoneCost.placeAt("dashboardComputingOfferCurrentZoneCost");

        });

        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var currentPodList = new ItemFileWriteStore({data: podOptions});
        if (currentZone.value == "option") {
            currentPodList.newItem({id: "option", name: "Select Pod"});
            podWidget.setStore(currentPodList);
        }

        this._podRestStore.get(currentZone.value).then(function(podListItems) {
            dojo.forEach(podListItems, function(currentPod) {

                currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
            });
            podWidget.setStore(currentPodList);
        });

    },
    hideOfferHA: function(currentValue) {

        if (currentValue.value == "Local") {
            dojo.byId("dashboardComputingOfferHA").style.display = "none";
        } else {
            dojo.byId("dashboardComputingOfferHA").style.display = "block";
        }


        var storageTagRestData = new JsonRest({
            target: core.getContextPath() + "/api/storagePool"
        });

        var storageType = dijit.byId("dashboardServiceStorageType").attr('displayedValue');
        var storageTagOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
        var clusterWidget = dijit.byId("dashboardComputingOfferClusterList");
        var storageTagWidget = dijit.byId("dashboardComputingOfferStorageTag");
        storageTagRestData.query({
            clusterReferenceId: clusterWidget.value,
            storageType: storageType
        }).then(function(data) {
            if (data.length != 0) {
                dojo.forEach(data, function(currentStorageTag) {
                    if (currentStorageTag.storageTag == null || currentStorageTag.storageTag == "null") {
                        storageTagList.newItem({id: "option", name: "No Tag"});
                    } else {
                        storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId, name: currentStorageTag.storageTag});
                    }
                });
            } else {
                storageTagList.newItem({id: "option", name: "No Tag"});
            }
            storageTagWidget.setStore(storageTagList);
        });
    },
    deleteCurrentWidget: function(currentServiceWidget, forced) {
        var status = forced;
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/delete"
        });

        computingOfferRestStore.add({
            computingOfferId: currentServiceWidget.additionalProperties.id,
            forced: status}).then(function(result) {

            if (result == "OK") {
                currentServiceWidget.deleteWidget();
                dojo.query("#computingOfferPage .updatable").removeClass("non_updatable");
                dojo.query("#computingOfferPage .hide_lable").removeClass("show_lable");
                dijit.byId("dashboardServiceCancelButton").set("disabled", true);
                dijit.byId('dashboardServiceAddButton').set('style', {display: 'block', "float": "left"});
                dijit.byId('dashboardServiceUpdateButton').set('style', {display: 'none'});
                var serviceListCollection = dojo.byId("dashboardServiceListItems");
                var serviceListWidgets = dijit.registry.findWidgets(serviceListCollection);
                dojo.forEach(serviceListWidgets, function(el) {
                    if (el.id == currentServiceWidget.id) {
                        currentServiceWidget.setSelectState(true, currentServiceWidget.id);
                    } else {
                        el.setSelectState(false, el.id);
                    }
                });
                var zoneNode = dojo.byId("dashboardCurrentComputingZoneInfo");
                var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                dojo.forEach(zoneWidgets, function(el) {
                    el.clearWidgets();
                });
//                dijit.byId("dashboardComputingOfferForm").reset();
                dijit.byId("dashboardServiceDescription").set("value", "");
                registry.byId("appToaster").setContent("Deleted Successfully!", "message");
                registry.byId("appToaster").show();
                dijit.byId("dashboardComputingOfferContentForm").reset();
                dojo.byId("dashboardComputingZoneName").innerHTML = "";
                dojo.byId("dashboardComputingPodName").innerHTML = "";
                dojo.byId("dashboardComputingClusterName").innerHTML = "";

            } else {
                var errorMessage;
                dojo.forEach(result, function(el) {

                    errorMessage = el.cause.localizedMessage;
                });
                dijit.byId("dashboardDeleteComputingOfferDialog").show();
                registry.byId("appToaster").setContent(errorMessage, "error");
                registry.byId("appToaster").show();
            }
        });

    },
    conformDelete: function() {
        var currentServiceWidgetId = dojo.byId("dashboardCurrentComputingOfferId").value;
        var currentServiceWidget = dijit.byId(currentServiceWidgetId);
        DashboardFogWizardComputingOffer.deleteCurrentWidget(currentServiceWidget, "true");
        dijit.byId("dashboardCurrentComputingOfferId").hide();
    },
    closeDeleteDialog: function() {
        dijit.byId("dashboardCurrentComputingOfferId").hide();
    },
    authetication: function() {
        var status = true;
        var pageNode = dojo.byId("dashboardcomputingOfferContent");
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

        var zoneNode = dojo.byId("dashboardCurrentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);

        dojo.forEach(zoneWidgets, function(el) {

            status = el.showErrorMsg();
        });

        return status;
    },
    add: function() {

        var status = DashboardFogWizardComputingOffer.authetication();
        if (status == true) {
            var computingZoneCosts = [];
            dijit.byId("dashboardServiceAddButton").set("disabled", true);
            var podWidget = dijit.byId("dashboardComputingOfferPodList");
            var clusterWidget = dijit.byId("dashboardComputingOfferClusterList");
            var name = dijit.byId("dashboardServiceName");
            var descriptionValue = dijit.byId("dashboardServiceDescription").getValue();
            var description = descriptionValue.replace("\n", " ");
            var cpuNumber = dijit.byId("dashboardServiceCpuNumber");
            var cpuSpeed = dijit.byId("dashboardServiceCpuSpeed");
            var cpuMemory = dijit.byId("dashboardServiceMemory");
            var networkRate = dijit.byId("dashboardServiceNetworkRate");
            var cpuCap = dijit.byId("dashboardServiceCpuCap");
            var bandWidth = dijit.byId("dashboardComputingOfferBandwidthCost").value;
            var offerHa = dijit.byId("dashboardServiceOfferHa");
            var storageType = dijit.byId("dashboardServiceStorageType");
            var baseOs = dijit.byId("dashboardComputingOfferBaseOs").attr('displayedValue');
            var storageTag = dijit.byId("dashboardComputingOfferStorageTag").attr('displayedValue');
            var hostTag = dijit.byId("dashboardComputingOfferHostTag").attr('displayedValue');

            var zoneNode = dojo.byId("dashboardCurrentComputingZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);

            dojo.forEach(zoneWidgets, function(el) {

                computingZoneCosts.push({
                    zoneId: el.getZoneId(),
                    cost: el.getZoneCost(),
                    setupCost: el.getSetupCost(),
                    minimumCost: el.getMinCost()
                });

            });
            for (var index = 0; index < computingZoneCosts.length - zoneWidgets.length; index++) {
                computingZoneCosts.splice(index, zoneWidgets.length);
            }

            this._computingOfferRestStore.add({
                referenceZoneId: this._zoneWidget.value,
                podReferenceId: podWidget.value,
                clusterReferenceId: clusterWidget.value,
                name: name.value,
                description: description,
                cpuNumber: cpuNumber.value,
                cpuSpeed: cpuSpeed.value,
                memory: cpuMemory.value,
                networkRate: networkRate.value,
                cpuCap: cpuCap.checked,
                bandWidth: bandWidth,
                offerHa: offerHa.checked,
                storageType: storageType.value,
                storageTag: storageTag,
                hostTag: hostTag,
                baseOs: baseOs,
                zoneCosts: computingZoneCosts
            }).then(function(data) {
                dijit.byId("dashboardServiceAddButton").set("disabled", false);
                if (data[0].result == "OK") {
                    dojo.forEach(data, function(computingOfferData) {
                        var computingOfferListItem = new FogPanel.WizardListItem({
                            heading: computingOfferData.computeOffer.name,
                            description: computingOfferData.computeOffer.description,
                            onClick: function() {
                                DashboardFogWizardComputingOffer.returnComputingOffer(this);
                            },
                            onDeleteTagClick: function() {
                                DashboardFogWizardComputingOffer.deleteCurrentWidget(this, "false");
                            },
                            additionalProperties: {
                                id: computingOfferData.computeOffer.id,
                                serviceDescription: computingOfferData.computeOffer.description,
                                serviceName: computingOfferData.computeOffer.name,
                                baseOs: computingOfferData.computeOffer.baseOs,
                                cpuNumber: computingOfferData.computeOffer.cpuNumber,
                                cpuSpeed: computingOfferData.computeOffer.coreCpuSpeed,
                                cpuMemory: computingOfferData.computeOffer.memory,
                                networkRate: computingOfferData.computeOffer.networkRate,
                                hostTag: computingOfferData.computeOffer.hostTags,
                                storageTags: computingOfferData.computeOffer.tags,
                                offerHa: computingOfferData.computeOffer.offerHA,
                                bandwidth: computingOfferData.computeOffer.bandWidth,
                                storageType: computingOfferData.computeOffer.storageType,
                                cpuCap: computingOfferData.computeOffer.limitCpuUse,
                                zoneCosts: computingOfferData.computeOffer.computingOfferZoneCosts,
                                heading: computingOfferData.computeOffer.name,
                                description: computingOfferData.computeOffer.description,
                                widgetId: computingOfferData.computeOffer.id,
                                urlPath: core.getContextPath() + "/api/computingOffer/"
                            }
                        });
                        computingOfferListItem.placeAt("dashboardServiceList");
                        computingOfferListItem.startup();
                    });
                    var zoneNode = dojo.byId("dashboardCurrentComputingZoneInfo");
                    var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                    dojo.forEach(zoneWidgets, function(el) {
                        el.clearWidgets();
                    });
                    registry.byId("appToaster").setContent("Added Successfully!", "message");
                    registry.byId("appToaster").show();
                    dijit.byId("dashboardComputingOfferContentForm").reset();

                } else {
                    registry.byId("appToaster").setContent("Cannot be Added!", "error");
                    registry.byId("appToaster").show();
                }
            });

        }
    },
    update: function() {
        var status = DashboardFogWizardComputingOffer.authetication();
        if (status == true) {
            var currentServiceWidgetId = dojo.byId("dashboardCurrentComputingOfferId").value;
            var currentServiceWidget = dijit.byId(currentServiceWidgetId);
            dijit.byId("dashboardServiceUpdateButton").set("disabled", true);
            var name = dijit.byId("dashboardServiceName");
            var descriptionValue = dijit.byId("dashboardServiceDescription").value.toString();
            var description = descriptionValue.replace("\n", " ");
            dojo.byId("dashboardComputingZoneName").innerHTML = "";
            dojo.byId("dashboardComputingPodName").innerHTML = "";
            dojo.byId("dashboardComputingClusterName").innerHTML = "";

            currentServiceWidget.additionalProperties.heading = name.value;
            currentServiceWidget.additionalProperties.description = description;

            var zoneNode = dojo.byId("dashboardCurrentComputingZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);

            dojo.forEach(zoneWidgets, function(el) {
                currentServiceWidget.additionalProperties.zoneCosts.push({
                    zoneId: el.getZoneId(),
                    cost: el.getZoneCost(),
                    setupCost: el.getSetupCost(),
                    minimumCost: el.getMinCost()
                });
                //                status = el.showErrorMsg();
            });
            for (var index = 0; index < currentServiceWidget.additionalProperties.zoneCosts.length - zoneWidgets.length; index++) {
                currentServiceWidget.additionalProperties.zoneCosts.splice(index, zoneWidgets.length);
            }

            this._computingOfferRestStore.put({
                id: currentServiceWidget.additionalProperties.id,
                name: name.value,
                description: description,
                zoneCosts: currentServiceWidget.additionalProperties.zoneCosts,
                "class": "com.assistanz.fogpanel.ComputingOffer"
            }).then(function(result) {
                dijit.byId("dashboardServiceUpdateButton").set("disabled", false);
                if (result == "OK") {
                    currentServiceWidget.getData();
                    dojo.query("#computingOfferPage .updatable").removeClass("non_updatable");
                    dojo.query("#computingOfferPage .hide_lable").removeClass("show_lable");
                    dijit.byId('dashboardServiceAddButton').set('style', {display: 'block', "float": "left"});
                    dijit.byId('dashboardServiceUpdateButton').set('style', {display: 'none'});
                    currentServiceWidget.setSelectState(false, currentServiceWidget.id);

                    var zoneNode = dojo.byId("dashboardCurrentComputingZoneInfo");
                    var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                    dojo.forEach(zoneWidgets, function(el) {
                        el.clearWidgets();
                    });

                    registry.byId("appToaster").setContent("updated Successfully!", "message");
                    registry.byId("appToaster").show();
                    dijit.byId("dashboardComputingOfferContentForm").reset();
                    dijit.byId("dashboardServiceCancelButton").set("disabled", true);
                    //                dijit.byId("serviceDescription").set("value", "");
                } else {
                    registry.byId("appToaster").setContent("Cannot be updated !", "error");
                    registry.byId("appToaster").show();
                }
            });
        }
    },
    cancel: function() {
        var currentServiceWidgetId = dojo.byId("dashboardCurrentComputingOfferId").value;
        var currentServiceWidget = dijit.byId(currentServiceWidgetId);
//        dijit.byId("dashboardComputingOfferForm").reset();
        dijit.byId("dashboardComputingOfferContentForm").reset();

//        dojo.query("#dashboardServiceListItems .WizardListItem").forEach(dojo.destroy);
//        dojo.query("#dashboardServiceListItems .WizardZoneInfo").forEach(dojo.destroy);
//        dojo.byId("computViewAllTag").style.display = "none";
        dojo.byId("dashboardComputingZoneName").innerHTML = "";
        dojo.byId("dashboardComputingPodName").innerHTML = "";
        dojo.byId("dashboardComputingClusterName").innerHTML = "";
//        dijit.byId("serviceDescription").set("value", "");
        dojo.query("#computingOfferPage .updatable").removeClass("non_updatable");
        dojo.query("#computingOfferPage .hide_lable").removeClass("show_lable");
        dijit.byId('dashboardServiceAddButton').set('style', {display: 'block', "float": "left"});
        dijit.byId('dashboardServiceUpdateButton').set('style', {display: 'none'});

        var zoneNode = dojo.byId("dashboardCurrentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        dojo.forEach(zoneWidgets, function(el) {
            el.clearWidgets();
        });
        currentServiceWidget.setSelectState(false, currentServiceWidget.id);
//        dijit.byId("dashboardComputingOfferForm").reset();
        dijit.byId("dashboardServiceCancelButton").set("disabled", true);
    },
    returnComputingOffer: function(currentWidget) {

        dijit.byId("dashboardServiceCancelButton").set("disabled", false);
        var zoneNode = dojo.byId("dashboardCurrentComputingZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        dojo.forEach(zoneWidgets, function(el) {
            el.clearWidgets();
        });
        var currentServiceWidget = currentWidget;
        dojo.byId("dashboardCurrentComputingOfferId").value = currentWidget.id;
        this._computingOfferRestStore.get(currentWidget.additionalProperties.id).then(function(computingOfferData) {

            dojo.byId("dashboardComputingZoneNameLabel").innerHTML = computingOfferData.zone.aliasName;
            dojo.byId("dashboardComputingPodNameLabel").innerHTML = computingOfferData.pod.name;
            dojo.byId("dashboardComputingClusterNameLabel").innerHTML = computingOfferData.cluster.name;

            dojo.byId("dashboardComputingZoneName").innerHTML = computingOfferData.zone.aliasName;
            dojo.byId("dashboardComputingPodName").innerHTML = computingOfferData.pod.name;
            dojo.byId("dashboardComputingClusterName").innerHTML = computingOfferData.cluster.name;

            var computingOfferZoneInfo = dojo.byId("dashboardCurrentComputingZoneInfo");
            var computingOfferZonewidgets = dijit.registry.findWidgets(computingOfferZoneInfo);
            dojo.forEach(computingOfferData.computingOfferZoneCosts, function(zoneData) {
                dojo.forEach(computingOfferZonewidgets, function(el) {
                    if (zoneData.zone.id == el.getZoneId()) {
                        el.zoneCost = (zoneData.cost * 720).toFixed();
                        el.setupCost = zoneData.setupCost;
                        el.minCost = zoneData.minimumCost;
                        el.setMinCost();
                        el.setCost();
                        el.setSetupCost();
                    }
                });
            });

            if (computingOfferData.bandWidth == "" ||
                    computingOfferData.bandWidth == null || computingOfferData.bandWidth == "null") {
                dojo.byId("dashboardServiceBwLabel").innerHTML = "......";
            } else {
                dojo.byId("dashboardServiceBwLabel").innerHTML = computingOfferData.bandWidth;
            }

        });

        var name = dijit.byId("dashboardServiceName");
        var description = dijit.byId("dashboardServiceDescription");

        name.setValue(currentServiceWidget.additionalProperties.heading);
        description.setValue(currentServiceWidget.additionalProperties.description);

        dojo.query("#computingOfferPage .updatable").toggleClass("non_updatable", true);
        dojo.query("#computingOfferPage .hide_lable").toggleClass("show_lable", true);
        dojo.byId("dashboardServiceCpuNumberLabel").innerHTML = currentServiceWidget.additionalProperties.cpuNumber + " Core";
        dojo.byId("dashboardServiceCpuSpeedLabel").innerHTML = currentServiceWidget.additionalProperties.cpuSpeed / 1000 + " GHz";
        dojo.byId("dashboardServiceMemoryLabel").innerHTML = Math.round((currentServiceWidget.additionalProperties.cpuMemory / 1024) * 100) / 100 + " GB";

        if (currentServiceWidget.additionalProperties.networkRate == "" ||
                currentServiceWidget.additionalProperties.networkRate == null || currentServiceWidget.additionalProperties.networkRate == "null") {
            dojo.byId("dashboardServiceNetworkRateLabel").innerHTML = "......";
        } else {
            dojo.byId("dashboardServiceNetworkRateLabel").innerHTML =
                    currentServiceWidget.additionalProperties.networkRate + "MB/s";
        }
        if (currentServiceWidget.additionalProperties.storageTags == null
                || currentServiceWidget.additionalProperties.storageTags == "" || currentServiceWidget.additionalProperties.storageTags == "null") {
            dojo.byId("dashboardServiceStorageTagLabel").innerHTML = "......";
        } else {
            dojo.byId("dashboardServiceStorageTagLabel").innerHTML =
                    currentServiceWidget.additionalProperties.storageTags;
        }
        if (currentServiceWidget.additionalProperties.hostTag == null || currentServiceWidget.additionalProperties.hostTag == "null"
                || currentServiceWidget.additionalProperties.hostTag == "") {
            dojo.byId("dashboardServiceHostTagLabel").innerHTML = "......";
        } else {
            dojo.byId("dashboardServiceHostTagLabel").innerHTML = currentServiceWidget.additionalProperties.hostTag;
        }
        if (currentServiceWidget.additionalProperties.offerHa == "true" || currentServiceWidget.additionalProperties.offerHa == true) {
            dojo.byId("dashboardServiceofferHaLabel").innerHTML = "yes";
        } else {
            dojo.byId("dashboardServiceofferHaLabel").innerHTML = "no";
        }
        if (currentServiceWidget.additionalProperties.cpuCap == true) {
            dojo.byId("dashboardServiceCpuCapLabel").innerHTML = "yes";
        } else {
            dojo.byId("dashboardServiceCpuCapLabel").innerHTML = "no";
        }
        if (currentWidget.additionalProperties.baseOs == "" ||
                currentWidget.additionalProperties.baseOs == null || currentWidget.additionalProperties.baseOs == "null") {
            dojo.byId("dashboardComputingOfferBaseOsLabel").innerHTML = "......";
        } else {
            dojo.byId("dashboardComputingOfferBaseOsLabel").innerHTML = currentWidget.additionalProperties.baseOs;
        }

        var storageValue = dijit.byId('dashboardServiceStorageType').attr('displayedValue');

        if (storageValue == "") {
            dojo.byId("dashboardServiceStorageTypeLabel").innerHTML = "......";
        } else {
            dojo.byId("dashboardServiceStorageTypeLabel").innerHTML = storageValue;
        }

        var serviceListCollection = dojo.query("#dashboardServiceList .WizardListItem");

        dojo.forEach(serviceListCollection, function(el) {
            if (el.id == currentWidget.id) {
                currentWidget.setSelectState(true, currentWidget.id);
            } else {
                var otherWidget = dijit.byId(el.id);
                otherWidget.setSelectState(false, el.id);
            }
        });
        dijit.byId('dashboardServiceAddButton').set('style', {display: 'none'});
        dijit.byId('dashboardServiceUpdateButton').set('style', {display: 'block', "float": "left"});
    },
    search: function() {
        var searchValue = dojo.byId('search2').value
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/search/"
        });

        computingOfferRestStore.add({
            value: searchValue
        }).then(function(data) {

            dojo.forEach(data, function(computingOfferData) {

                alert(computingOfferData.name);
            });
        });

    }
};

var DashboardFogWizardDiskOffer = {
    init: function() {

    },
    populateValues: function() {

    }
};



var DashboardFogWizardMiscellaneousCost = {
//    _misclRestStore:"",
//    _zoneRestZone:"",
//    _zoneRestStore:"",
//    _podRestStore : "",
//    _clusterRestStore : "",
//    _podWidget:"",
//    _clusterWidget : "",
//    _zoneWidget : "",
//    init: function() {
//        this._misclRestStore = new JsonRest({
//            target: "/FogPanel/api/miscellaneousOffer/"
//        });
//
//        this._zoneRestStore = new JsonRest({
//            target: "/FogPanel/api/zone/"
//        });
//
//        this._podRestStore = new JsonRest({
//            target: "/FogPanel/api/pod/"
//        });
//        
//        this._clusterRestStore = new JsonRest({
//            target: "/FogPanel/api/cluster/"
//        });
//    },
//    populateValues: function() {  
//        if (dojo.query("#dashboardMiscellaneousListContainer .WizardListItem").length != 0) {
//            return;
//        }; 
//
//        var podRestStore = this._podRestStore;
//        var clusterRestStore = this._clusterRestStore;
//        var misclRestStore = this._misclRestStore;
//        if(dijit.byId("dashboardMiscZone") && dijit.byId("dashboardMiscPod") && dijit.byId("dashboardMiscCluster")) {
//            dijit.byId("dashboardMiscZone").destroyRecursive();
//            dijit.byId("dashboardMiscPod").destroyRecursive();
//            dijit.byId("dashboardMiscCluster").destroyRecursive();
//        };
//        var podOptions = {
//            identifier: 'id',
//            label: 'name',
//            items: [{id: "option", name: "Select Pod"}]
//        };
//        var clusterOptions = {
//            identifier: 'id',
//            label: 'name',
//            items: [{id: "option", name: "Select Cluster"}]
//        };
//        var podList = new ItemFileWriteStore({data: podOptions});
//        var clusterList = new ItemFileWriteStore({data: clusterOptions});
//          
//        this._clusterWidget = new Select({
//            id: "dashboardMiscCluster",
//            store: clusterList,
//            onChange : function() {
//                var miscId = dojo.byId("dashboardCurrentMisscellaneousOfferId").value;
//                var miscWidget = dijit.byId(miscId);
//
//                var misclRestStore = new JsonRest({
//                    target: "/FogPanel/api/miscellaneousOffer/"
//                });  
//
//                var miscelZoneNode = dojo.byId("dashboardCurrentMisceZoneCostList");
//                var miscelZoneWidgets = dijit.registry.findWidgets(miscelZoneNode);
//
//                misclRestStore.query({miscellaneousOfferId: miscWidget.additionalProperties.id, 
//                clusterReferenceId: this.value}).then(function(data) {
//                    dojo.forEach(data, function(miscData) {
//                        dojo.forEach(miscelZoneWidgets, function(el) {
//                            el.zoneCost = (miscData.cost*720).toFixed();
//                            el.setCost(); 
//                            el.costRate = miscData.miscellaneousOffer.unit;
//                            el.setCostRate();
//                        });                                     
//                    });
//                });
//            }
//        }).placeAt("dashboardMiscClusterList"); 
//        
//        this._podWidget = new Select({
//            id: "dashboardMiscPod",
//            store: podList,
//            onChange: function() {
//                var clusterWidget = dijit.byId("dashboardMiscCluster");
//                var clusterOptions = {
//                identifier: 'id',
//                label: 'name',
//                items: []
//                };
//                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
//                if(this.value == "empty") {
//                    currentClusterList.newItem({id: "empty", name: "Select Cluster"});
//                    clusterWidget.setStore(currentClusterList);
//                } else {
//                    clusterRestStore.get(this.value).then(function(clusterListItems) {
//                        dojo.forEach(clusterListItems, function(currentcluster) {
//                            currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
//                        });
//                        clusterWidget.setStore(currentClusterList);
//                    });
//                };
//            }
//        }).placeAt("dashboardMiscPodList");   
//        var zoneOptions = { 
//            identifier: 'id',
//            label: 'name',
//            items: [{id: "option", name: "Select Zone"}]
//        }; 
//        
//        var zoneList = new ItemFileWriteStore({data: zoneOptions});
//        this._zoneRestStore.query().then(function(data) {
//            dojo.forEach(data, function(el) {
//                zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
//            });
//        });                        
//        
//        this._zoneWidget = new Select({
//            id : "dashboardMiscZone",
//            store: zoneList,
//            onChange: function() {  
//                var podWidget = dijit.byId("dashboardMiscPod");
//                var currentZoneCost;
//                if(dijit.byId("dashboardMisclCurrentZone")) {
//                    dijit.byId("dashboardMisclCurrentZone").destroyRecursive();
//                };
//                
//                var zoneRestStore = new JsonRest({
//                    target: "/FogPanel/api/zone/"
//                });
//            
//                zoneRestStore.get(this.value).then(function(selectedZoneInfo) {
//                    currentZoneCost  = new Zone.ZoneCost({
//                        id:"dashboardMisclCurrentZone",
//                        zoneName: "Cost/Month $",
//                        zoneIdNode: selectedZoneInfo.id
//                    });
//                    currentZoneCost.placeAt("dashboardMisceZoneCost"); 
//                    currentZoneCost.removeCosts();
//            
//                    currentZoneCost.setCostRate();
//                });
//                
//                var podOptions = {
//                    identifier: 'id',
//                    label: 'name',
//                    items: []
//                };
//                
//                var currentPodList = "";
//                var podRestStore = new JsonRest({
//                    target: "/FogPanel/api/pod/"
//                });
//                currentPodList = new ItemFileWriteStore({data: podOptions});
//                if(this.value == "option") {
//                    currentPodList.newItem({id: "empty", name: "Select Pod"});
//                    podWidget.setStore(currentPodList);
//                } else {
//                    podRestStore.get(this.value).then(function(podListItems) {
//                        dojo.forEach(podListItems, function(currentPod) {
//                            currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
//                        });
//                        podWidget.setStore(currentPodList);
//                    });
//                };
//            }
//        }).placeAt("dashboardMiscZoneList"); 
//        misclRestStore.query().then(function(data) {
//            dojo.forEach(data, function(misclData) {
//                var misclListItem = new FogPanel.WizardListItem({
//                    heading: misclData.name,
//                    description: misclData.description,
//                    onClick: function() {   
//                        DashboardFogWizardMiscellaneousCost.returnMiscellaneousCost(this);
//                    },
//                    onDeleteTagClick: function() {
//                        DashboardFogWizardMiscellaneousCost.deleteCurrentWidget(this, "false");
//                    },
//                    onSummaryTagClick : function() {
//                        DashboardFogWizardMiscellaneousCost.planSummary(this);
//                    },
//                    additionalProperties : {
//                        id:misclData.id,
//                        name: misclData.name,
//                        description: misclData.unit,
//                        zoneCosts: []
//                    }
//                });
//                misclListItem.placeAt("dashboardMisceList");
//                misclListItem.startup();
//                misclListItem.disableStates();
//                misclListItem.enableSummary();
//            });
//        });
//    },
//    planSummary : function(currentWidget) {
//        var summaryGridWidget ="";
//        if(dijit.byId("dashboardGrid")) {
//            dijit.byId("dashboardGrid").destroyRecursive();
//        }
//        var summaryData = {
//            items: []
//        };
//        var summaryDataList = new ItemFileWriteStore({data: summaryData});
//        var summaryLayout = [[
//                 {'field': 'id', 'width' : '200px', 'hidden' : 'true'},
//                 {'name': 'Zone', 'field': 'zone', 'width' : '200px'},
//                 {'name': 'Pod', 'field': 'pod', 'width' : '200px'},
//                 {'name': 'Cluster', 'field': 'cluster', 'width' : '150px'},
//                 {'name': 'Cost', 'field': 'cost', 'width' : '150px'}
//                    
//             ]
//         ];      
//         
//         var misclRestStore = new JsonRest({
//            target: "/FogPanel/api/miscellaneousOffer/"
//         });        
//         misclRestStore.get(currentWidget.additionalProperties.id).then(function(data) {    
//             dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
//                summaryDataList.newItem({id:miscData.id, zone: miscData.zone.aliasName, pod: miscData.pod.name, cluster: miscData.cluster.name, cost:miscData.cost });
//             });
//         });
//        summaryGridWidget = new DataGrid({
//             id: "dashboardGrid",
//             store: summaryDataList,
//             structure: summaryLayout,
//             height: '130px'
//            
//         });
//         summaryGridWidget.placeAt("dashboardSummaryGrid");
//         summaryGridWidget.startup();
//         dijit.byId("dashboardSummaryDialog").show();
//    },
//    closeSummaryDialogue  : function() {
//        dijit.byId("dashboardSummaryDialog").hide();
//    },
//    update: function() {
//    
//        var podWidget = dijit.byId("dashboardMiscPod");
//        var clusterWidget = dijit.byId("dashboardMiscCluster");
//        var currentMiscellaneousWidgetId = dojo.byId("dashboardCurrentMisscellaneousOfferId").value;
//        var currentMiscellaneousWidget = dijit.byId(currentMiscellaneousWidgetId);
//        var miscelZoneCostList = dojo.byId("dashboardCurrentMisceZoneCostList");
//        var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);
//        
//        dojo.forEach(miscelZoneCostWidgets, function(el) {
//            currentMiscellaneousWidget.additionalProperties.zoneCosts.push({
//                zoneId: el.getZoneId(), 
//                cost: el.getZoneCost()
//            });
////            el.clearWidgets();
//        }); 
//        
//        for(var index=0; index < currentMiscellaneousWidget.additionalProperties.zoneCosts.length-miscelZoneCostWidgets.length; index++) {
//            currentMiscellaneousWidget.additionalProperties.zoneCosts.splice(index, miscelZoneCostWidgets.length);
//        }; 
//        
//        this._misclRestStore.put({
//            id: currentMiscellaneousWidget.additionalProperties.id,
//            zoneCosts: currentMiscellaneousWidget.additionalProperties.zoneCosts,
//            zoneReferenceId : this._zoneWidget.value,
//            clusterReferenceId : clusterWidget.value,
//            podReferenceId : podWidget.value
//         }).then(function(result)  {
//             if(result == "OK") {
//                 
//                  var miscelZoneCostList = dojo.byId("dashboardCurrentMisceZoneCostList");
//                var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);
//
//                dojo.forEach(miscelZoneCostWidgets, function(el) {
//                    
//                    el.clearWidgets();
//                }); 
//                
//                registry.byId("appToaster").setContent("updated Successfully!","message");
//                 registry.byId("appToaster").show();
//             } else {
//                 registry.byId("appToaster").setContent("Cannot be updated !", "error");
//                 registry.byId("appToaster").show();
//             }  
//         });
//         currentMiscellaneousWidget.setSelectState(false, currentMiscellaneousWidget.id); 
//     }, 
//     returnMiscellaneousCost: function(currentMisceWidget) {
//         var zoneNode = dojo.byId("dashboardCurrentMisceZoneCostList");
//         var zoneWidgets = dijit.registry.findWidgets(zoneNode);
//         dojo.forEach(zoneWidgets, function(el) {
//             el.clearWidgets();
//         }); 
//         dojo.byId("dashboardCurrentMisscellaneousOfferId").value = currentMisceWidget.id; 
//         this._misclRestStore.get(currentMisceWidget.additionalProperties.id).then(function(miscelData) {
//             var miscelZoneNode = dojo.byId("dashboardCurrentMisceZoneCostList");
//             var miscelZoneWidgets = dijit.registry.findWidgets(miscelZoneNode);
//            
//             dojo.forEach(miscelData.miscellaneousOfferZoneCosts, function(miscelZoneCost) {
//                 dojo.forEach(miscelZoneWidgets, function(el) {
//                     if(miscelZoneCost.zone.id == el.getZoneId()) {
//                         el.zoneCost = (miscelZoneCost.cost*720).toFixed();
//                         el.setCost();
//                         el.costRate = currentMisceWidget.additionalProperties.description;
//                         el.setCostRate();
//                     }
//                 });
//             });          
//         });           
//         var miscelPane = dijit.byId("dashboardMiscelZoneCostInfo");
//         miscelPane.setAttribute("Title", currentMisceWidget.heading);
//         miscelPane.setAttribute("open", "true");
//        var miscelListCollection = dojo.query("#dashboardMisceList .WizardListItem");
//        
//        dojo.forEach(miscelListCollection, function(el) {
//            if (el.id == currentMisceWidget.id) {
//                     currentMisceWidget.setSelectState(true, currentMisceWidget.id);
//            } else {
//                var otherWidget = dijit.byId(el.id);
//                otherWidget.setSelectState(false, el.id);
//            }
//        });
//     },
//     cancel: function() {
//         
//         var currentMiscellaneousWidgetId = dojo.byId("dashboardCurrentMisscellaneousOfferId").value;
//         var currentMiscellaneousWidget = dijit.byId(currentMiscellaneousWidgetId);
//              
//         currentMiscellaneousWidget.setSelectState(false, currentMiscellaneousWidget.id);         
//        
//         var zoneNode = dojo.byId("dashboardCurrentMisceZoneCostList");
//         var zoneWidgets = dijit.registry.findWidgets(zoneNode);
//         dojo.forEach(zoneWidgets, function(el) {
//             el.clearWidgets();
//         }); 
//     }
};


var DashboardAppTemp = {
    _zoneRestStore: "",
    _osTypeRestStore: "",
    _tempRestStrore: "",
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
    },
    populateValues: function() {
        this._tempRestStrore.query().then(function(data) {
            dojo.forEach(data, function(tempData) {
                var tempListItem = new List.ListItem({
                    onClick: "DashboardAppTemp.returnListData(this.id)",
                    onDeleteTagClick: DashboardTemp.deleteCurrentWidget
                });

                var tempItems = {
                    id: tempData.id,
                    referenceId: tempData.referenceId,
                    name: tempData.name,
                    description: tempData.description,
                    url: tempData.url,
                    zone: tempData.zoneName,
                    hypervisor: tempData.hypervisor,
                    format: tempData.format,
                    osType: tempData.osType,
                    osReferenceId: tempData.osTypeReferenceId,
                    extractable: tempData.extractable,
                    passwordEnabled: tempData.passwordEnabled,
                    isPublic: tempData.isPublic,
                    featured: tempData.featured
                };

                tempListItem.placeAt("dashboardAppTempList");
                tempListItem.startup();
                tempListItem.additionalProperties = tempItems;
                tempListItem.additionalProperties.heading = tempData.name;
                tempListItem.additionalProperties.description = tempData.description;
                tempListItem.getData();
            });
        });
    },
    returnListData: function(id) {
        window.currentTempWidget = dijit.byId(id);
        this._tempRestStrore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if (currentTempWidget.additionalProperties.id == ''
                        && currentTempWidget.additionalProperties.name == el.content.name) {
                    currentTempWidget.additionalProperties.id = el.content.id;
                }
            });
        });

        dijit.byId("dashboardAppTempName").setValue(currentTempWidget.additionalProperties.name);
        dijit.byId("dashboardAppTempDescription").setValue(currentTempWidget.additionalProperties.description);

        dojo.byId("urlWidget").style.display = "none";
        dojo.byId("formatDiv").style.display = "none";

        appZoneWidget.setAttribute("style", "display: none");
        dojo.byId("dashboardAppTempZoneLabel").innerHTML = currentTempWidget.additionalProperties.zone;
        dojo.byId("dashboardAppTempZoneLabel").style.display = "block";

        appHypervisorWidget.setAttribute("style", "display: none");
        dojo.byId("dashboardAppTempHypervisorLabel").innerHTML = currentTempWidget.additionalProperties.hypervisor;
        dojo.byId("dashboardAppTempHypervisorLabel").style.display = "block";

        if (currentTempWidget.additionalProperties.extractable == true) {
            dijit.byId("dashboardAppTempExtractable").setAttribute('checked', true);
        } else {
            dijit.byId("dashboardAppTempExtractable").setAttribute('checked', false);
        }
        if (currentTempWidget.additionalProperties.passwordEnabled == true) {
            dijit.byId("dashboardAppTempPasswordEnabled").setAttribute('checked', true);
        } else {
            dijit.byId("dashboardAppTempPasswordEnabled").setAttribute('checked', false);
        }
        if (currentTempWidget.additionalProperties.isPublic == true) {
            dijit.byId("dashboardAppTempPublic").setAttribute('checked', true);
        } else {
            dijit.byId("dashboardAppTempPublic").setAttribute('checked', false);
        }
        if (currentTempWidget.additionalProperties.featured == true) {
            dijit.byId("dashboardAppTempFeatured").setAttribute('checked', true);
        } else {
            dijit.byId("dashboardAppTempFeatured").setAttribute('checked', false);
        }
        dijit.byId("dashboardAppTempUpdateButton").set('style', {display: 'block', "float": "left"});
        dijit.byId("dashboardAppTempAddButton").set("style", "display: none");


//            osType.set("value", currentTempWidget.additionalProperties.osReferenceId)
        //        alert(currentTempWidget.additionalProperties.osType);
        var tempListCollection = dojo.byId("dashboardAppTempCollection");
        var tempListWidgets = dijit.registry.findWidgets(tempListCollection);

        dojo.forEach(tempListWidgets, function(el) {
            if (el.id == currentTempWidget.id) {
                currentTempWidget.setAttribute("style", 'border: 1px solid #62CCFE');
            } else {
                el.setAttribute("style", 'border: 1px solid #000000');
            }
        });
    },
    update: function() {
        this.name = dijit.byId("dashboardAppTempName");
        this.description = dijit.byId("dashboardAppTempDescription");
        this.osTypeId = osType.value;
        this.extractable = dijit.byId("dashboardAppTempExtractable").checked;
        this.passwordEnabled = dijit.byId("dashboardAppTempPasswordEnabled").checked;
        this.isPublic = dijit.byId("dashboardAppTempPublic").checked;
        this.featured = dijit.byId("dashboardAppTempFeatured").checked;

        this._tempRestStrore.put({
            id: currentTempWidget.additionalProperties.id,
            name: this.name.value,
            description: this.description.value,
            osType: this.osTypeId,
            extractable: this.extractable,
            passwordEnabled: this.passwordEnabled,
            isPublic: this.isPublic,
            featured: this.featured
        }).then(function(results) {
        });
        dojo.byId("urlWidget").style.display = "block";
        dojo.byId("formatDiv").style.display = "block";

        dojo.byId("dashboardAppTempZoneLabel").style.display = "none";
        dojo.byId("dashboardAppTempHypervisorLabel").style.display = "none";

        appZoneWidget.set('style', {'display': 'block', 'margin': '0 0 0 160px', 'width': '330px'});
        appHypervisorWidget.set('style', {'display': 'block', 'margin': '0 0 0 160px', 'width': '330px'});

        dijit.byId("dashboardAppTempAddButton").set('style', {display: 'block', "float": "left"});
        dijit.byId("dashboardAppTempUpdateButton").set("style", "display: none");

        var tempListCollection = dojo.byId("dashboardAppTempCollection");
        var tempListWidgets = dijit.registry.findWidgets(tempListCollection);

        dojo.forEach(tempListWidgets, function(el) {
            el.setAttribute("style", 'border: 1px solid #000000');
        });

        dijit.byId("dashboardAppTempForm").reset();
    },
    cancel: function() {

        dojo.byId("urlWidget").style.display = "block";
        dojo.byId("formatDiv").style.display = "block";

        dojo.byId("dashboardAppTempZoneLabel").style.display = "none";
        dojo.byId("dashboardAppTempHypervisorLabel").style.display = "none";

        appZoneWidget.set('style', {'display': 'block', 'margin': '0 0 0 160px', 'width': '330px'});
        appHypervisorWidget.set('style', {'display': 'block', 'margin': '0 0 0 160px', 'width': '330px'});

        dijit.byId("dashboardAppTempAddButton").set('style', {display: 'block', "float": "left"});
        dijit.byId("dashboardAppTempUpdateButton").set("style", "display: none");

        var tempListCollection = dojo.byId("dashboardAppTempCollection");
        var tempListWidgets = dijit.registry.findWidgets(tempListCollection);

        dojo.forEach(tempListWidgets, function(el) {
            el.setAttribute("style", 'border: 1px solid #000000');
        });

        dijit.byId("dashboardAppTempForm").reset();
    },
    initZone: function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/",
            idAttribute: "id",
            "class": "com.assistanz.cloud.portal.Zone"
        });

        var osRestStore = new JsonRest({
            target: core.getContextPath() + "/api/osType/"
        });

        var osTypeOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "", name: "Select OS"}]
        };

        var osCategoryOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "", name: "Select Category"}]
        };

        var osTypeList = new ItemFileWriteStore({data: osTypeOptions});
        var osCategoryList = new ItemFileWriteStore({data: osCategoryOptions});

        osRestStore.query().then(function(osCategorytDate) {
            dojo.forEach(osCategorytDate, function(data) {
                osCategoryList.newItem({id: data.osCategoryId, name: data.osCategoryName});
            });
        });

        var osWidget = new Select({
            id: "appOsList",
            store: osTypeList,
            maxHeight: -1 // tells _HasDropDown to fit menu within viewport 
        }).placeAt("dashboardAppTempOsTypeList");
        osWidget.startup();


        var osCategory = new Select({
            id: "appOsCategoryList",
            store: osCategoryList,
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport 
            onChange: function() {

//                    dojo.byId("computingPodName").innerHTML = this.get("displayedValue");
                var osTypeWidget = dijit.byId("appOsList");
                var osTypeOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var currentOsList = new ItemFileWriteStore({data: osTypeOptions});
                if (this.value == "") {
                    currentOsList.newItem({id: "", name: "Select OS"});
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
        }).placeAt("dashboardAppTempOsCategoryList");
        osCategory.startup();

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "-1", name: "All Zones"}]
        };

        var zoneList = new ItemFileWriteStore({data: zoneOptions});

        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });

        var hyperviserItems = {
            identifier: "name",
            label: "name",
            items: [{id: "hyperviserId", name: ""}]
        };
        var appHyperviserList = new ItemFileWriteStore({data: hyperviserItems});

        var appZoneWidget = new Select({
            id: "appZoneWidget",
            name: "test",
            store: zoneList,
            onChange: function() {
                var hyperviserRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/hypervisor/"
                });

                hyperviserRestStore.get(this.value).then(function(data) {
                    dojo.forEach(data, function(el) {
                        appHyperviserList.newItem({id: el.name, name: el.name});
//                             appHypervisorWidget.set("value", el.name);
                    });
                });
            }
        }, "dashboardAppTempZone");
        appZoneWidget.startup();

        window.appHypervisorWidget = new Select({
            id: "appHypervisorWidget",
            store: appHyperviserList,
            maxHeight: -1

        }, "dashboardAppTempHypervisor");
    },
    authentication: function() {
        var pageNode = dojo.byId("tempPage");
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
        if (firstNode) {
            firstNode.focus();
        }
        return status;
    },
    add: function() {
        var status = DashboardTemp.authentication();
        if (status == true) {
            this.name = dijit.byId("dashboardAppTempName");
            this.description = dijit.byId("dashboardAppTempDescription");
            this.url = dijit.byId("dashboardAppTempURL");
            this.zone = dijit.byId("appZoneWidget").value;
            this.hypervisor = appHypervisorWidget.value;

            this.format = dijit.byId("dashboardAppTempFormat").get('displayedValue');
            var osTypeId = dijit.byId("appOsList").value;
            this.extractable = dijit.byId("dashboardAppTempExtractable").checked;
            this.passwordEnabled = dijit.byId("dashboardAppTempPasswordEnabled").checked;
            this.isPublic = dijit.byId("dashboardAppTempPublic").checked;
            this.featured = dijit.byId("dashboardAppTempFeatured").checked;

            var tempData = {
                id: '',
                name: this.name.value,
                description: this.description.value,
                url: this.url.value,
                zone: dijit.byId("appZoneWidget").get('displayedValue'),
                hypervisor: appHypervisorWidget.value,
                format: this.format,
                osType: osTypeId,
                osReferenceId: this.osTypeId,
                extractable: this.extractable,
                passwordEnabled: this.passwordEnabled,
                isPublic: this.isPublic,
                featured: this.featured
            };

            this._tempRestStrore = new JsonRest({
                target: core.getContextPath() + "/api/template/"
            });

            this._tempRestStrore.add({
                name: this.name.value,
                appTemplate: true,
                myTemplate: false,
                cost: dijit.byId("dashboardAppTempCost").value,
                description: this.description.value,
                url: this.url.value,
                zone: dijit.byId("appZoneWidget").value,
                hypervisor: appHypervisorWidget.value,
                format: this.format,
                osType: dijit.byId("appOsList").value,
                extractable: this.extractable,
                passwordEnabled: this.passwordEnabled,
                isPublic: this.isPublic,
                featured: this.featured
            }).then(function(results) {
                if (results == "OK") {
                    var tempListItem = new List.ListItem({
                        onClick: "DashboardTemp.returnListData(this.id);",
                        onDeleteTagClick: DashboardTemp.deleteCurrentWidget
                    });
                    tempListItem.placeAt("dashboardAppTempList");
                    tempListItem.startup();
                    tempListItem.additionalProperties = tempData;
                    tempListItem.additionalProperties.heading = tempData.name;
                    tempListItem.additionalProperties.description = tempData.description;
                    tempListItem.getData();
                    dijit.byId("dashboardAppTempForm").reset();
                }
            });
        }
    }
};



var DashboardUserProfile = {
    _currentUserRestStore: "",
    _userRestStore: "",
    _currentUser: "",
    init: function() {
        this._currentUserRestStore = new JsonRest({
            target: core.getContextPath() + "/api/user/currentUser/"
        });

        this._userRestStore = new JsonRest({
            target: core.getContextPath() + "/api/user/"
        });
    },
    populateValues: function() {
        this._currentUserRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("userProfileName").innerHTML = el.accountName;
                if (el.apiKey == null || el.apiKey == " ") {
                    dojo.byId("userProfileApiKey").innerHTML = "null";
                } else {
                    dojo.byId("userProfileApiKey").innerHTML = el.apiKey;
                }

                if (el.secretKey == null || el.secretKey == "") {
                    dojo.byId("userProfileSecretKey").innerHTML = "null";
                } else {
                    dojo.byId("userProfileSecretKey").innerHTML = el.content.secretKey;
                }
                window.currentId = el.id;
            });
        });
    },
    gotoResetPage: function() {
        dojo.byId("userProfile").style.display = "none";
        dojo.byId("userPasswordField").style.display = "block";
    },
    resetPassword: function() {
        var password = dijit.byId("passwordField").value;
        var userRestPasswordRestStore = new JsonRest({
            target: core.getContextPath() + "/api/user/resetPassword/"
        });
        userRestPasswordRestStore.put({
            id: currentId,
            password: password
        });
    }
};
var DashboardAccountProfile = {
    _currentAccountRestStore: "",
    _accountRestStore: "",
    name: "",
    id: "",
    init: function() {
        this._currentAccountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount/"
        });

        this._accountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });
    },
    populateValues: function() {
        this._currentAccountRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dijit.byId("accountUserName").setValue(el.fullName);
                dijit.byId("accountEmail").setValue(el.email);
                dijit.byId("accountStreetAddress").setValue(el.streetAddress);
                dijit.byId("accountCity").setValue(el.city);
                dijit.byId("accountState").setValue(el.state);
                dijit.byId("accountCountry").setValue(el.country);
                dijit.byId("accountZip").setValue(el.zip);
                window.id = el.id;
            });
        });
    },
    update: function() {

        var fullName = dijit.byId("accountUserName");
        var email = dijit.byId("accountEmail");
        var street = dijit.byId("accountStreetAddress");
        var city = dijit.byId("accountCity");
        var state = dijit.byId("accountState");
        var country = dijit.byId("accountCountry");
        var zip = dijit.byId("accountZip");

        this._accountRestStore.put({
            id: id,
            fullName: fullName.value,
            email: email.value,
            street: street.value,
            city: city.value,
            state: state.value,
            country: country.value,
            zip: zip.value
        });
    }
};




var InvoiceList = {
    _accountGrid: "",
    _accountListRestStore: "",
    init: function() {
        this._accountListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account"
        });
    },
    populateValues: function() {

        if (dijit.byId("invoiceGridWidget")) {
            dijit.byId("invoiceGridWidget").destroyRecursive();
        }

        var gridData = {
            items: [{id: "1", domain: "101711/IP Address", freq: "Monthly", period: "07/01/12 - 07/31/12 ",
                    unitPrice: "1.00", qty: "1.00", price: "1.00", setupFee: "$0.00",
                    tax: "0.00", taxAmount: "$0.00", totalAmount: "$1.00"},
                {id: "1", domain: "101711/Templates", freq: "Monthly", period: "07/01/12 - 07/31/12 ",
                    unitPrice: "1.00", qty: "1.00", price: "1.00", setupFee: "$0.00",
                    tax: "0.00", taxAmount: "$0.00", totalAmount: "$1.00"},
                {id: "1", domain: "101711/ISOs ", freq: "Monthly", period: "07/01/12 - 07/31/12 ",
                    unitPrice: "1.00", qty: "1.00", price: "1.00", setupFee: "$0.00",
                    tax: "0.00", taxAmount: "$0.00", totalAmount: "$1.00"},
                {id: "1", domain: "101711/Snapshots", freq: "Monthly", period: "07/01/12 - 07/31/12 ",
                    unitPrice: "1.00", qty: "1.00", price: "1.00", setupFee: "$0.00",
                    tax: "0.00", taxAmount: "$0.00", totalAmount: "$1.00"},
                {id: "1", domain: "101711/i-136-693-instance ", freq: "Monthly", period: "07/01/12 - 07/31/12 ",
                    unitPrice: "1.00", qty: "1.00", price: "1.00", setupFee: "$0.00",
                    tax: "0.00", taxAmount: "$0.00", totalAmount: "$1.00"}


            ]
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': 'Id', 'field': 'id', 'width': '50px', 'hidden': 'true'},
                {'name': 'Domain/Server Name or Product Description', 'field': 'domain', 'width': '310px'},
                {'name': 'Freq.', 'field': 'freq', 'width': '100px'},
                {'name': 'Period', 'field': 'period', 'width': '150px'},
                {'name': 'Unit Price', 'field': 'unitPrice', 'width': '100px'},
                {'name': 'Qty', 'field': 'qty', 'width': '50px'},
                {'name': 'Price', 'field': 'price', 'width': '50px'},
                {'name': 'Setup Fee', 'field': 'setupFee', 'width': '100px'},
                {'name': 'Tax%', 'field': 'tax', 'width': '30px'},
                {'name': 'Tax Amount', 'field': 'taxAmount', 'width': '100px'},
                {'name': 'Total Amount', 'field': 'totalAmount', 'width': '100px'}
            ]
        ];
        this._accountGrid = new DataGrid({
            id: 'invoiceGridWidget',
            store: gridDataList,
            structure: gridLayout,
            height: '200px'
        });
        this._accountGrid.placeAt("invoiceGrid");
        this._accountGrid.startup();


    }

};

var LoginAttemptSetup = {
    init: function() {
    },
    add: function() {
        var time = dijit.byId("unlockTime");
        var perAccount = dijit.byId("perAccount");
        var perIp = dijit.byId("perIp");
    }
};


var GroupingInfo = {
    _osWidget: "",
    _computingOfferRestStore: "",
    _zoneRestStore: "",
    _diskLength: "",
    _count: 1,
    init: function() {
        this._computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer"
        });
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone"
        });
    },
    populateValues: function() {
        if (dijit.byId("groupingOsList") && dijit.byId("slider") && dijit.byId("groupingZoneList") && dijit.byId("sliderRule")) {
            dijit.byId("osList").destroyRecursive();
            dijit.byId("slider").destroyRecursive();
            dijit.byId("groupingZoneList").destroyRecursive();
            dijit.byId("sliderRule").destroyRecursive();
        }
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var zoneList = new ItemFileWriteStore({data: zoneOption});

        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(zoneData) {
                zoneList.newItem({id: zoneData.referenceZoneId, name: zoneData.aliasName});
            });
        });

        var zoneWidget = new Select({
            id: "groupingZoneList",
            store: zoneList,
            onChange: function() {
            }
        }, "groupingZoneList");

        var osOption = {
            identifier: 'id',
            label: 'name',
            items: [
                {id: "default", name: "Select Os"},
                {id: 'Linux', name: 'Linux'},
                {id: 'Windows', name: 'Windows'}
            ]
        };
        var osList = new ItemFileWriteStore({data: osOption});

        this._osWidget = new Select({
            id: "groupingOsList",
            store: osList,
            onChange: function() {
                dojo.query("#listCollection .WizardListItem").forEach(dojo.destroy);
                dojo.query("#selectedList .WizardListItem").forEach(dojo.destroy);
                var computingOfferRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/computingOffer"
                });
                var zoneWidget = dijit.byId("groupingZoneList");
                computingOfferRestStore.query({baseOs: this.value, zoneReferenceId: zoneWidget.value}).then(function(data) {
                    dijit.byId("selectAllButton").set("disabled", false);
                    dojo.forEach(data, function(offerData) {

                        var osListItem = new FogPanel.WizardListItem({
                            heading: offerData.name,
                            description: offerData.description,
                            cpu: offerData.cpuNumber,
                            ram: offerData.memory,
                            onClick: function() {
                                GroupingInfo.returnCurrentWidget(this);
                            },
//                          
                            onDeleteTagClick: function() {
                            },
                            additionalProperties: {
                                count: offerData.orderNo,
                                id: offerData.id,
                                referenceId: offerData.offerReferenceId,
                                description: offerData.description,
                                name: offerData.name,
                                cpuNumber: offerData.cpuNumber,
                                coreCpuSpeed: offerData.coreCpuSpeed,
                                memory: offerData.memory,
                                cost: offerData.computingOfferZoneCosts[0].cost,
                                minimumCost: offerData.computingOfferZoneCosts[0].minimumCost,
                                setupCost: offerData.computingOfferZoneCosts[0].setupCost
                            }
                        });
                        osListItem.placeAt("osListItem");
                        osListItem.showCpuRam();
                        osListItem.startup();

                    });
                });
            }
        }, "osTypeStorageList");
    },
    replaceCurrentWidget: function() {
        var currentSelectWidgetId = dojo.byId("selectWidgetId").value;
        var currentSelectWidget = dijit.byId(currentSelectWidgetId);
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });

        computingOfferRestStore.get(currentSelectWidget.additionalProperties.id).then(function(data) {

            currentSelectWidget.destroyRecursive();
            var selectedListItem = new FogPanel.WizardListItem({
                heading: data.name,
                description: data.description,
                cpu: data.cpuNumber,
                ram: data.memory,
                onClick: function() {
                    GroupingInfo.returnCurrentWidget(this);
                },
//                          
                onDeleteTagClick: function() {
                },
                additionalProperties: {
                    count: data.orderNo,
                    id: data.id,
                    referenceId: data.offerReferenceId,
                    name: data.name,
                    cpuNumber: data.cpuNumber,
                    coreCpuSpeed: data.coreCpuSpeed,
                    memory: data.memory,
                    cost: data.computingOfferZoneCosts[0].cost,
                    widgetId: ""

                }
            });
            selectedListItem.placeAt("osListItem");
            selectedListItem.showCpuRam();
            selectedListItem.startup();
        });
    },
    placeWidget: function() {
        var currentWidgetId = dojo.byId("currentWidgetId").value;
        var currentWidget = dijit.byId(currentWidgetId);

        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });
        var computingOfferOrderRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/order/"
        });

        computingOfferRestStore.get(currentWidget.additionalProperties.id).then(function(data) {
            currentWidget.destroyRecursive();
            var selectedListItem = new FogPanel.WizardListItem({
                heading: data.name,
                description: data.description,
                cpu: data.cpuNumber,
                ram: data.memory,
                onClick: function() {
                    var currentId = this.id;
                    dojo.byId("selectWidgetId").value = currentId;
                    var listCollection = dojo.byId("selectedList");

                    var listWidgets = dijit.registry.findWidgets(listCollection);
                    dojo.forEach(listWidgets, function(el) {
                        if (el.id == currentId) {
                            currentWidget.setSelectState(true, currentId);
                        } else {
                            el.setSelectState(false, el.id);
                        }
                    });
                },
                onUpArrowClick: function() {
                    GroupingInfo.goUpWidget(this);
                },
                onDownArrowClick: function() {
                    GroupingInfo.goDownWidget(this);
                },
                onDeleteTagClick: function() {
                },
                additionalProperties: {
                    count: 0,
                    id: data.id,
                    referenceId: data.offerReferenceId,
                    name: data.name,
                    cpuNumber: data.cpuNumber,
                    coreCpuSpeed: data.coreCpuSpeed,
                    memory: data.memory,
                    cost: data.computingOfferZoneCosts[0].cost,
                    widgetId: ""

                }
            });
            selectedListItem.placeAt("selectedItems");
            selectedListItem.showCpuRam();
            selectedListItem.showUpAndDownArrow();
            selectedListItem.startup();

            if (selectedListItem) {
                dojo.query("#reArrange").removeClass("non_updatable");
                var selectedItemColl = dojo.byId("selectedList");
                var listWidgets = dijit.registry.findWidgets(selectedItemColl);
                dojo.forEach(listWidgets, function(el, index) {
                    if (el.additionalProperties.id == data.id) {
                        computingOfferOrderRestStore.put({
                            id: data.id,
                            referenceId: data.offerReferenceId,
                            orderNo: index + 1
                        }).then(function(response) {
                            if (response[0].result == "OK") {
                                el.additionalProperties.count = response[0].computeOffer.orderNo;
                            }
                        });
                    }

                });
            }
        });
    },
    replaceAllWidget: function() {
        var listCollection = dojo.byId("selectedList");
        var listWidgets = dijit.registry.findWidgets(listCollection);
        dojo.forEach(listWidgets, function(el) {
            var selectedListItem = new FogPanel.WizardListItem({
                heading: el.additionalProperties.name,
                description: el.additionalProperties.description,
                cpu: el.additionalProperties.cpuNumber,
                ram: el.additionalProperties.memory,
                onClick: function() {
                    GroupingInfo.returnCurrentWidget(this);
                },
                onDeleteTagClick: function() {
                },
                additionalProperties: {
                    count: el.additionalProperties.count,
                    id: el.additionalProperties.id,
                    referenceId: el.additionalProperties.referenceId,
                    description: el.additionalProperties.description,
                    name: el.additionalProperties.name,
                    cpuNumber: el.additionalProperties.cpuNumber,
                    coreCpuSpeed: el.additionalProperties.coreCpuSpeed,
                    memory: el.additionalProperties.memory,
                    cost: el.additionalProperties.cost,
                    widgetId: ""
                }
            });
            selectedListItem.placeAt("osListItem");
            selectedListItem.showCpuRam();
            selectedListItem.startup();
            el.destroyRecursive();
        });
    },
    populateSelectedPlanes: function() {
//       dojo.query("#listCollection .WizardListItem").forEach(dojo.destroy);

        dojo.query("#selectedList .WizardListItem").forEach(dojo.destroy);
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer"
        });
        var zoneWidget = dijit.byId("groupingZoneList");
        var osType = dijit.byId("groupingOsList");

        computingOfferRestStore.query({
            baseOs: osType.value,
            zoneReferenceId: zoneWidget.value
        }).then(function(data) {
            dojo.forEach(data, function(offerData) {
                var osListItem = new FogPanel.WizardListItem({
                    heading: offerData.name,
                    description: offerData.description,
                    cpu: offerData.cpuNumber,
                    ram: offerData.memory,
                    onClick: function() {
                        var currentId = this.id;
                        var currentWidget = dijit.byId(currentId);
                        dojo.byId("selectWidgetId").value = currentId;
                        var listCollection = dojo.byId("selectedList");

                        var listWidgets = dijit.registry.findWidgets(listCollection);
                        dojo.forEach(listWidgets, function(el) {
                            if (el.id == currentId) {
                                currentWidget.setSelectState(true, currentId);
                            } else {
                                el.setSelectState(false, el.id);
                            }
                       });
                    },
                    onDeleteTagClick: function() {
                    },
                    onUpArrowClick: function() {
                        GroupingInfo.goUpWidget(this);
                    },
                    onDownArrowClick: function() {
                        GroupingInfo.goDownWidget(this);
                    },
                    additionalProperties: {
                        count: offerData.orderNo,
                        id: offerData.id,
                        referenceId: offerData.offerReferenceId,
                        description: offerData.description,
                        name: offerData.name,
                        cpuNumber: offerData.cpuNumber,
                        coreCpuSpeed: offerData.coreCpuSpeed,
                        memory: offerData.memory,
                        cost: offerData.computingOfferZoneCosts[0].cost,
                        widgetId: ""
                    }
                });
                osListItem.placeAt("selectedItems");
                osListItem.showCpuRam();
                osListItem.showUpAndDownArrow();
                osListItem.startup();

            });
        });
    },
    goDownWidget: function(currentWidget) {
        var currentCount = currentWidget.additionalProperties.count;
        var nextCount = currentWidget.additionalProperties.count + 1;

        var computingOfferOrderRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/order/"
        });

        var selectListCollection = dojo.byId("selectedList");
        var selectListWidgets = dijit.registry.findWidgets(selectListCollection);
        dojo.forEach(selectListWidgets, function(el) {
            if (el.additionalProperties.count == nextCount) {
                computingOfferOrderRestStore.put({
                    id: el.additionalProperties.id,
                    referenceId: el.additionalProperties.referenceId,
                    orderNo: currentCount
                });
                computingOfferOrderRestStore.put({
                    id: currentWidget.additionalProperties.id,
                    referenceId: currentWidget.additionalProperties.referenceId,
                    orderNo: nextCount
                }).then(function(response) {
                    if (response[0].result == "OK") {
                        GroupingInfo.populateSelectedPlanes();
                    }
                });


            }
        });
    },
    goUpWidget: function(currentWidget) {
        var currentCount = currentWidget.additionalProperties.count;
        var preCount = currentWidget.additionalProperties.count - 1;

        var computingOfferOrderRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/order/"
        });

        var selectListCollection = dojo.byId("selectedList");
        var selectListWidgets = dijit.registry.findWidgets(selectListCollection);
        dojo.forEach(selectListWidgets, function(el) {
            if (el.additionalProperties.count == preCount) {
                computingOfferOrderRestStore.put({
                    id: el.additionalProperties.id,
                    referenceId: el.additionalProperties.referenceId,
                    orderNo: currentCount
                });
                computingOfferOrderRestStore.put({
                    id: currentWidget.additionalProperties.id,
                    referenceId: currentWidget.additionalProperties.referenceId,
                    orderNo: preCount
                }).then(function(response) {
                    if (response[0].result == "OK") {
                        GroupingInfo.populateSelectedPlanes();
                    }
                });


            }
        });
    },
    placeAllWidget: function() {

        dijit.byId("deselectAllButton").set("disabled", false);

        var listCollection = dojo.byId("listCollection");
        var selectListCollection = dojo.byId("selectedList");

        var computingOfferOrderRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/order/"
        });
        var listWidgets = dijit.registry.findWidgets(listCollection);
        var selectListWidgets = dijit.registry.findWidgets(selectListCollection);
        if (selectListWidgets.length != 0) {
            var selectPageLength = selectListWidgets.length + 1;
            dojo.forEach(listWidgets, function(el) {
                computingOfferOrderRestStore.put({
                    id: el.additionalProperties.id,
                    referenceId: el.additionalProperties.referenceId,
                    orderNo: selectPageLength
                }).then(function(response) {
                    if (response[0].result == "OK") {
                    }
                });
                var selectedListItem = new FogPanel.WizardListItem({
                    heading: el.additionalProperties.name,
                    description: el.additionalProperties.description,
                    cpu: el.additionalProperties.cpuNumber,
                    ram: el.additionalProperties.memory,
                    onClick: function() {
                        var currentId = this.id;
                        var currentWidget = dijit.byId(currentId);
                        dojo.byId("selectWidgetId").value = currentId;
                        var listCollection = dojo.byId("selectedList");

                        var listWidgets = dijit.registry.findWidgets(listCollection);
                        dojo.forEach(listWidgets, function(el) {
                            if (el.id == currentId) {
                                currentWidget.setSelectState(true, currentId);
                            } else {
                                el.setSelectState(false, el.id);
                            }
                        });

                        dijit.byId("selectAllButton").set("disabled", false);
                        dijit.byId("selectButton").set("disabled", false);
                        dijit.byId("deselectButton").set("disabled", false);
                        dijit.byId("deselectAllButton").set("disabled", false);
                    },
                    onUpArrowClick: function() {
                        GroupingInfo.goUpWidget(this);
                    },
                    onDownArrowClick: function() {
                        GroupingInfo.goDownWidget(this);
                    },
                    onDeleteTagClick: function() {
                    },
                    additionalProperties: {
                        count: selectPageLength,
                        id: el.additionalProperties.id,
                        referenceId: el.additionalProperties.referenceId,
                        name: el.additionalProperties.name,
                        cpuNumber: el.additionalProperties.cpuNumber,
                        coreCpuSpeed: el.additionalProperties.coreCpuSpeed,
                        memory: el.additionalProperties.memory,
                        cost: el.additionalProperties.cost,
                        description: el.additionalProperties.description
                    }
                });
                selectedListItem.placeAt("selectedItems");
                selectedListItem.showCpuRam();
                selectedListItem.showUpAndDownArrow();
                selectedListItem.startup();
                el.destroyRecursive();
                selectPageLength++;
            });
        } else if (selectListWidgets == 0) {
            dojo.forEach(listWidgets, function(el) {
                var selectedListItem = new FogPanel.WizardListItem({
                    heading: el.additionalProperties.name,
                    description: el.additionalProperties.description,
                    cpu: el.additionalProperties.cpuNumber,
                    ram: el.additionalProperties.memory,
                    onClick: function() {
                        var currentId = this.id;
                        var currentWidget = dijit.byId(currentId);
                        dojo.byId("selectWidgetId").value = currentId;
                        var listCollection = dojo.byId("selectedList");

                        var listWidgets = dijit.registry.findWidgets(listCollection);
                        dojo.forEach(listWidgets, function(el) {
                            if (el.id == currentId) {
                                currentWidget.setSelectState(true, currentId);
                            } else {
                                el.setSelectState(false, el.id);
                            }
                        });
                        dijit.byId("selectAllButton").set("disabled", false);
                        dijit.byId("selectButton").set("disabled", false);
                        dijit.byId("deselectButton").set("disabled", false);
                        dijit.byId("deselectAllButton").set("disabled", false);
                    },
                    onUpArrowClick: function() {
                        GroupingInfo.goUpWidget(this);
                    },
                    onDownArrowClick: function() {
                        GroupingInfo.goDownWidget(this);
                    },
                    onDeleteTagClick: function() {
                    },
                    additionalProperties: {
                        count: el.additionalProperties.count,
                        id: el.additionalProperties.id,
                        description: el.additionalProperties.description,
                        referenceId: el.additionalProperties.referenceId,
                        name: el.additionalProperties.name,
                        cpuNumber: el.additionalProperties.cpuNumber,
                        coreCpuSpeed: el.additionalProperties.coreCpuSpeed,
                        memory: el.additionalProperties.memory,
                        cost: el.additionalProperties.cost,
                        widgetId: ""
                    }
                });
                selectedListItem.placeAt("selectedItems");
                selectedListItem.showCpuRam();
                selectedListItem.startup();
                selectedListItem.showUpAndDownArrow();
                el.destroyRecursive();
            });
        }
    },
    returnCurrentWidget: function(currentWidget) {
        var listCollection = dojo.byId("listCollection");
        dojo.byId("currentWidgetId").value = currentWidget.id;
        var listWidgets = dijit.registry.findWidgets(listCollection);

        dojo.forEach(listWidgets, function(el) {
            if (el.id == currentWidget.id) {
                currentWidget.setSelectState(true, currentWidget.id);
            } else {
                el.setSelectState(false, el.id);
            }
        });

        dijit.byId("selectAllButton").set("disabled", false);
        dijit.byId("selectButton").set("disabled", false);
        dijit.byId("deselectButton").set("disabled", false);
        dijit.byId("deselectAllButton").set("disabled", false);
    },
    showSlider: function() {
        if (dijit.byId("slid")) {
            dijit.byId("slid").destroyRecursive();
            dijit.byId("rule").destroyRecursive();
        }

        dojo.byId("planName").innerHTML = "";
        dojo.byId("cpuNo").innerHTML = "";
        dojo.byId("cpuSpeed").innerHTML = "";
        dojo.byId("cpuMemory").innerHTML = "";
        dojo.byId("groupingCost").innerHTML = "";

        var selectedItemColl = dojo.byId("selectedList");
        var listWidgets = dijit.registry.findWidgets(selectedItemColl);

        var rulesNode = domConstruct.create("div", {}, dojo.byId("osSlider"), "first");
        var sliderRules = new dijit.form.HorizontalRule({
            id: "rule",
            count: listWidgets.length,
            container: "bottomDecoration",
            style: "width: 100%; height: 10px"
        }, rulesNode);

        var slider = new dijit.form.HorizontalSlider({
            id: "slid",
            name: "slider",
            minimum: 1,
            maximum: listWidgets.length,
            style: "width:100%;",
            discreteValues: listWidgets.length,
            onChange: function(value) {
                var selectedItemColl = dojo.byId("selectedList");
                var listWidgets = dijit.registry.findWidgets(selectedItemColl);
                dojo.forEach(listWidgets, function(listItem, index) {
                    if (index + 1 == value) {
                        dojo.byId("planName").innerHTML = listItem.additionalProperties.name + ", ";
                        dojo.byId("cpuNo").innerHTML = listItem.additionalProperties.cpuNumber + " CPU";
                        dojo.byId("cpuSpeed").innerHTML = listItem.additionalProperties.coreCpuSpeed + " CPU Speed";
                        dojo.byId("cpuMemory").innerHTML = listItem.additionalProperties.memory + " Memory";
                        dojo.byId("groupingCost").innerHTML = listItem.additionalProperties.cost + " Per Hour";
                    }
                });
            }
        }).placeAt("osSlider");
        slider.startup();
        sliderRules.startup();

        dojo.query("#planListResult").removeClass("non_updatable");
        dojo.query("#osSliderList").removeClass("non_updatable");
        dojo.query("#editPageButton").removeClass("non_updatable");
        dojo.query("#previewPageButton").toggleClass("non_updatable", true);

        dojo.query("#listCollection").toggleClass("non_updatable", true);
        dojo.query("#listArrow").toggleClass("non_updatable", true);
        dojo.query("#selectedContentInfo").toggleClass("non_updatable", true);

        dojo.query("#sliderInfo").toggleClass("span7", true);
        dojo.query("#sliderInfo").removeClass("span3", true);
    },
    editPageButton: function() {
        dojo.query("#editPageButton").toggleClass("non_updatable", true);
        dojo.query("#selectedContentInfo").removeClass("non_updatable");
        dojo.query("#previewPageButton").removeClass("non_updatable");

        dojo.query("#listCollection").removeClass("non_updatable");
        dojo.query("#listArrow").removeClass("non_updatable", true);
        dojo.query("#sliderInfo").toggleClass("span3", true);
        dojo.query("#sliderInfo").removeClass("span7", true);
        dojo.query("#osSliderList").toggleClass("non_updatable", true);
        dojo.query("#planListResult").toggleClass("non_updatable");
    }

};








var DashboardDiskOffer = {
    _diskOfferRestStore: "",
    name: "",
    description: "",
    diskSize: "",
    storageTags: "",
    diskZoneWidgets: "",
    _zoneRestStore: "",
    _podRestStore: "",
    _clusterRestStore: "",
    _storageTagRestStore: "",
    _hostTagRestStore: "",
    init: function() {

        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/diskOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });

        this._podRestStore = new JsonRest({
            target: core.getContextPath() + "/api/pod/"
        });

        this._clusterRestStore = new JsonRest({
            target: core.getContextPath() + "/api/cluster/"
        });

        this._storageTagRestStore = new JsonRest({
            target: core.getContextPath() + "/api/storagePool/"
        });

        this._hostTagRestStore = new JsonRest({
            target: core.getContextPath() + "/api/host/"
        });
    },
    populateValues: function() {

        if (dijit.byId("dashboardDiskOfferStorageTagList") && dijit.byId("dashboardDiskOfferCluster")
                && dijit.byId("dashboardDiskOfferPod") && dijit.byId("dashboardDiskOfferZoneListItem")) {
            dijit.byId("dashboardDiskOfferStorageTagList").destroyRecursive();
            dijit.byId("dashboardDiskOfferCluster").destroyRecursive();
            dijit.byId("dashboardDiskOfferPod").destroyRecursive();
            dijit.byId("dashboardDiskOfferZoneListItem").destroyRecursive();

        }

        var clusterRestStore = this._clusterRestStore;

        var capabilitiesRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/listCSCapabilities/"
        });

        capabilitiesRestStore.query().then(function(data) {
            dojo.forEach(data, function(capabilitiyData) {
                dijit.byId("dashboardDiskMaxSize").attr("constraints", {max: capabilitiyData.customDiskMaxSize, min: 1, pattern: '#'});
                dijit.byId("dashboardDiskMaxSize").attr("value", capabilitiyData.customDiskMaxSize);
            });
        });


        var csConfigRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/CSConfig/"
        });

        csConfigRestStore.get("storage.max.volume.size").then(function(data) {
            dojo.forEach(data, function(configData) {
                dijit.byId("dashboardDiskMinSize").attr("constraints", {max: parseInt(configData.value), min: 1, pattern: '#'});
            });
        });

        FogWizardZone.init();
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Pod"}]
        };

        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Cluster"}]
        };

        var storageTagOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Tag"}]
        };


        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});

        this._storageTagWidget = new Select({
            id: "dashboardDiskOfferStorageTagList",
            store: storageTagList,
            onChange: function() {
                var hostTagCapacityRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/storagePool/capacity/"
                });
                hostTagCapacityRestStore.get(this.value).then(function(data) {
                    dojo.forEach(data, function(storageData) {
                        dijit.byId("dashboardDiskSize").attr("constraints", {max: storageData.totalSize, min: 1, pattern: '#'});
                        dijit.byId("dashboardDiskMinSize").attr("constraints", {max: storageData.totalSize, min: 1, pattern: '#'});
                        dijit.byId("dashboardDiskMaxSize").attr("value", storageData.totalSize);
                    });
                });
            }
        }).placeAt("dashboardDiskOfferStorageTags");
        this._storageTagWidget.startup();
        this._clusterWidget = new Select({
            id: "dashboardDiskOfferCluster",
            store: clusterList,
            onChange: function() {
                dojo.byId("dashboardDiskClusterName").innerHTML = this.get("displayedValue");
                dojo.query("#dashboardDiskListCollection .WizardListItem").forEach(dojo.destroy);
                dojo.query("#dashboardDiskListCollection .WizardZoneInfo").forEach(dojo.destroy);
                dojo.byId("diskViewAllTag").style.display = "block";
                var diskOfferRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/diskOffer/"
                });


                var storageTagRestData = new JsonRest({
                    target: core.getContextPath() + "/api/storagePool"
                });
                var storageTagOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var storageTagList = new ItemFileWriteStore({data: storageTagOptions});

                var storageTagWidget = dijit.byId("dashboardDiskOfferStorageTagList");
                if (this.value == "option") {
                    storageTagList.newItem({id: "option", name: "Select Tags"});
                    storageTagWidget.setStore(storageTagList);
                    dojo.byId("diskViewAllTag").style.display = "none";
                    dijit.byId("dashboardDiskAddButton").set("disabled", true);
                    dijit.byId("dashboardDiskCancelButton").set("disabled", true);
                } else {
                    dijit.byId("dashboardDiskAddButton").set("disabled", false);
                    diskOfferRestStore.query({clusterReferenceId: this.value}).then(function(data) {
                        dojo.forEach(data, function(diskOfferData) {

                            var diskOfferListItem = new FogPanel.WizardListItem({
                                heading: diskOfferData.name,
                                description: diskOfferData.description,
                                onClick: function() {
                                    DashboardDiskOffer.returnDiskOffer(this);
                                },
                                onDeleteTagClick: function() {
                                    DashboardDiskOffer.deleteCurrentWidget(this, "false");
                                },
                                additionalProperties: {
                                    customDisk: diskOfferData.customized,
                                    id: diskOfferData.id,
                                    description: diskOfferData.description,
                                    diskSize: diskOfferData.size,
                                    name: diskOfferData.name,
                                    storageTags: diskOfferData.tags,
                                    zoneCosts: [],
                                    storageType: diskOfferData.storageType,
                                    currentZoneCost: diskOfferData.diskOfferZoneCosts
                                }
                            });
                            diskOfferListItem.placeAt("dashboardDiskList");
                            diskOfferListItem.startup();
                        });
                    });
                }
                var storageType = dijit.byId("dashboardDiskStorageType").attr('displayedValue');
                storageTagRestData.query({
                    clusterReferenceId: this.value,
                    storageType: storageType
                }).then(function(data) {
                    if (data.length == 0) {
                        storageTagList.newItem({id: "option", name: "No Tag"});
                        storageTagWidget.setStore(storageTagList);
                    } else {
                        dojo.forEach(data, function(currentStorageTag) {
                            if (currentStorageTag.storageTag == null || currentStorageTag.storageTag == "null") {
                                storageTagList.newItem({id: "option", name: "No Tag"});
                            } else {
                                storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId, name: currentStorageTag.storageTag});
                            }
                        });
                        storageTagWidget.setStore(storageTagList);
                    }
                });


                var capabilitiesRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/config/listCSCapabilities/"
                });

                capabilitiesRestStore.query().then(function(data) {
                    dojo.forEach(data, function(capabilitiyData) {
                        dijit.byId("dashboardDiskMaxSize").attr("constraints", {max: capabilitiyData.customDiskMaxSize, min: 1, pattern: '#'});
                        dijit.byId("dashboardDiskMaxSize").attr("value", capabilitiyData.customDiskMaxSize);
                    });
                });


                var csConfigRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/config/CSConfig/"
                });

                csConfigRestStore.get("storage.max.volume.size").then(function(data) {
                    dojo.forEach(data, function(configData) {
                        dijit.byId("dashboardDiskMinSize").attr("constraints", {max: parseInt(configData.value), min: 1, pattern: '#'});
                    });
                });
            }
        }).placeAt("dashboardDiskOfferClusterList");

        this._podWidget = new Select({
            id: "dashboardDiskOfferPod",
            store: podList,
            onChange: function() {
                dojo.byId("dashboardDiskPodName").innerHTML = this.get("displayedValue");
                var clusterWidget = dijit.byId("dashboardDiskOfferCluster");
                var clusterOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
                if (this.value == "option") {
                    currentClusterList.newItem({id: "option", name: "Select Cluster"});
                    clusterWidget.setStore(currentClusterList);
                }
                clusterRestStore.get(this.value).then(function(clusterListItems) {
                    dojo.forEach(clusterListItems, function(currentcluster) {

                        currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                    });
                    clusterWidget.setStore(currentClusterList);
                });
            }
        }).placeAt("dashboardDiskOfferPodList");
        this._podWidget.startup();

        var zoneOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select zone"}]
        };

        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });
        this._zoneWidget = new Select({
            store: zoneList,
            id: "dashboardDiskOfferZoneListItem",
            onChange: function() {
                DashboardDiskOffer.viewSelectedZone(this);
            }
        }).placeAt("dashboardDiskOfferZoneList");
        this._zoneWidget.startup();

    },
    viewAllZoneInfo: function() {
        dojo.query("#dashboardDiskListCollection .WizardListItem").forEach(dojo.destroy);
        dojo.query("#dashboardDiskListCollection .WizardZoneInfo").forEach(dojo.destroy);


        var podRestStore = new JsonRest({
            target: core.getContextPath() + "/api/pod/"
        });

        var clusterRestStore = new JsonRest({
            target: core.getContextPath() + "/api/cluster/"
        });

        var diskOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/diskOffer/"
        });

        this._zoneRestStore.query().then(function(zoneData) {
            dojo.forEach(zoneData, function(zoneInfo) {
                podRestStore.get(zoneInfo.referenceZoneId).then(function(podData) {
                    dojo.forEach(podData, function(podInfo) {
                        clusterRestStore.get(podInfo.podReferenceId).then(function(clusterData) {
                            dojo.forEach(clusterData, function(clusterInfo) {

                                var count = 0;
                                diskOfferRestStore.query({clusterReferenceId: clusterInfo.clusterReferenceId,
                                    referenceZoneId: zoneInfo.referenceZoneId, podReferenceId: podInfo.podReferenceId}).then(function(data) {
                                    dojo.forEach(data, function(diskOfferData) {
                                        if (count == 0) {
                                            var diskZoneInfo = new FogPanel.WizardZoneInfo({
                                                zoneName: diskOfferData.zone.name,
                                                podName: diskOfferData.pod.name,
                                                clusterName: diskOfferData.cluster.name


                                            });
                                            diskZoneInfo.placeAt("dashboardDiskList");
                                            diskZoneInfo.startup();

                                            count++;
                                        }
                                        var diskOfferListItem = new FogPanel.WizardListItem({
                                            heading: diskOfferData.name,
                                            description: diskOfferData.description,
                                            onClick: function() {
                                                DashboardDiskOffer.returnDiskOffer(this);
                                            },
                                            onDeleteTagClick: function() {
                                                DashboardDiskOffer.deleteCurrentWidget(this, "false");
                                            },
                                            additionalProperties: {
                                                customDisk: diskOfferData.customized,
                                                id: diskOfferData.id,
                                                description: diskOfferData.description,
                                                diskSize: diskOfferData.size,
                                                name: diskOfferData.name,
                                                storageTags: diskOfferData.tags,
                                                zoneCosts: [],
                                                storageType: diskOfferData.storageType,
                                                currentZoneCost: diskOfferData.diskOfferZoneCosts
                                            }
                                        });
                                        diskOfferListItem.placeAt("dashboardDiskList");
                                        diskOfferListItem.startup();
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });
    },
    viewSelectedZone: function(currentZone) {

        dojo.byId("dashboardDiskZoneName").innerHTML = currentZone.get("displayedValue");
        var podWidget = dijit.byId("dashboardDiskOfferPod");
        var currentZoneCost;


        if (dijit.byId("diskOfferCurrentZone")) {
            dijit.byId("diskOfferCurrentZone").destroyRecursive();
        }

        this._zoneRestStore.get(currentZone.value).then(function(selectedZoneInfo) {

            currentZoneCost = new Zone.ZoneCost({
                id: "diskOfferCurrentZone",
                zoneIdNode: selectedZoneInfo.id,
                costRate: "Per Hour"
            });
            currentZoneCost.placeAt("dashboardDiskZoneCost");
            currentZoneCost.removeCosts();

            currentZoneCost.setCostRate();
        });
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var currentPodList = new ItemFileWriteStore({data: podOptions});
        if (currentZone.value == "option") {
            currentPodList.newItem({id: "option", name: "Select Pod"});
            podWidget.setStore(currentPodList);
        }
        this._podRestStore.get(currentZone.value).then(function(podListItems) {

            dojo.forEach(podListItems, function(currentPod) {

                currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});

//               podWidget.set("value", currentPod.podReferenceId);
            });
            podWidget.setStore(currentPodList);
        });
    },
    deleteCurrentWidget: function(currentDiskListWidget, forced) {

        var diskOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/diskOffer/delete/"
        });

        var status = forced;
        diskOfferRestStore.add({diskOfferId: currentDiskListWidget.additionalProperties.id,
            forced: status}).then(function(result) {
            if (result == "OK") {
                currentDiskListWidget.deleteWidget();
                dojo.query("#dashboardDiskOfferingPage .updatable").removeClass("non_updatable", true);
                dojo.query("#dashboardDiskOfferingPage .hide_lable").removeClass("show_lable", true);
                dijit.byId("dashboardDiskCancelButton").set("disabled", true);
                dijit.byId('dashboardDiskAddButton').set('style', {display: 'block', "float": 'left'});
                dijit.byId('dashboardDiskUpdateButton').set('style', {display: 'none'});

                dojo.byId("dashboardDiskZoneName").innerHTML = "";
                dojo.byId("dashboardDiskPodName").innerHTML = "";
                dojo.byId("dashboardDiskClusterName").innerHTML = "";
                var zoneNode = dojo.byId("dashboardCurrentDiskZoneInfo");
                var zoneWidgets = dijit.registry.findWidgets(zoneNode);
                dojo.forEach(zoneWidgets, function(el) {
                    el.clearWidgets();
                });

                registry.byId("appToaster").setContent("Deleted Successfully!", "message");
                registry.byId("appToaster").show();
                dijit.byId("dashboardDiskOfferContentForm").reset();
                dijit.byId("dashboardDiskDescription").set("value", "");
            } else {
                var errorMessage;
                dojo.forEach(result, function(el) {

                    errorMessage = el.cause.localizedMessage;
                });
//        dijit.byId("deleteDiskOfferDialog").show();
                registry.byId("appToaster").setContent(errorMessage, "error");
                registry.byId("appToaster").show();
            }
        });
    },
    conformDelete: function() {
        var currentWidgetId = dojo.byId("dashboardCurrentDiskOfferId").innerHTML;
        var currentDiskListWidget = dijit.byId(currentWidgetId);
        FogWizardDiskOffer.deleteCurrentWidget(currentDiskListWidget, "true");
        dijit.byId("deleteDiskOfferDialog").hide();
    },
    closeDeleteDialog: function() {
        dijit.byId("deleteDiskOfferDialog").hide();
    },
    returnDiskOffer: function(currentWidget) {
        dojo.byId("dashboardDiskSizeHide").style.display = "block";
        var zoneNode = dojo.byId("dashboardCurrentDiskZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        dojo.forEach(zoneWidgets, function(el) {
            el.clearWidgets();
        });
        dijit.byId("dashboardDiskCancelButton").set("disabled", false);
        var currentDiskListWidget = currentWidget;
        dojo.byId("dashboardCurrentDiskOfferId").innerHTML = currentWidget.id;
        this._diskOfferRestStore.get(currentDiskListWidget.additionalProperties.id).then(function(diskOfferData) {

            dojo.byId("dashboardZoneNameLabel").innerHTML = diskOfferData.zone.aliasName;
            dojo.byId("dashboardPodNameLabel").innerHTML = diskOfferData.pod.name;
            dojo.byId("dashboardClusterNameLabel").innerHTML = diskOfferData.cluster.name;

            dojo.byId("dashboardDiskZoneName").innerHTML = diskOfferData.zone.aliasName;
            dojo.byId("dashboardDiskPodName").innerHTML = diskOfferData.pod.name;
            dojo.byId("dashboardDiskClusterName").innerHTML = diskOfferData.cluster.name;

            var diskOfferZoneInfo = dojo.byId("dashboardCurrentDiskZoneInfo");
            var diskOfferZonewidgets = dijit.registry.findWidgets(diskOfferZoneInfo);


            dojo.forEach(diskOfferData.diskOfferZoneCosts, function(el) {
                dojo.forEach(diskOfferZonewidgets, function(zoneId) {
                    if (el.zone.id == zoneId.getZoneId()) {
                        zoneId.zoneCost = (el.cost * 720).toFixed();
                        zoneId.setCost();
                    }
                });
            });


            if (diskOfferData.minSize == "" || diskOfferData.minSize == null || diskOfferData.minSize == "null") {
                dojo.byId("dashboardDiskMinSizeLabel").innerHTML = ".......";
            } else {
                dojo.byId("dashboardDiskMinSizeLabel").innerHTML = diskOfferData.minSize;
            }


            if (diskOfferData.maxSize == "" || diskOfferData.maxSize == null || diskOfferData.maxSize == "null") {
                dojo.byId("dashboardDiskMaxSizeLabel").innerHTML = ".......";
            } else {
                dojo.byId("dashboardDiskMaxSizeLabel").innerHTML = diskOfferData.maxSize;
            }

            if (diskOfferData.customized == "true" || diskOfferData.customized == true) {
                dojo.byId("dashboardDiskSizeHide").style.display = "none";
                dojo.query("#dashboardDiskOfferContent .form-horizontal .diskCustomSize").toggleClass("updatableContent ", true);
            } else if (diskOfferData.customized == "false" || diskOfferData.customized == false) {
                dojo.byId("dashboardDiskSizeHide").style.display = "block";
                dojo.query("#dashboardDiskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
            }

            if (diskOfferData.storageType == "null" || diskOfferData.storageType == null || diskOfferData.storageType == "") {
                dojo.byId("dashboardDiskStorageTypeLabel").innerHTML = ".......";
            } else {
                dojo.byId("dashboardDiskStorageTypeLabel").innerHTML = diskOfferData.storageType;
            }
        });


        dojo.query("#dashboardDiskOfferingPage .updatable").toggleClass("non_updatable", true);
        dojo.query("#dashboardDiskOfferingPage .hide_lable").toggleClass("show_lable", true);
        this.customDisk = dijit.byId("dashboardCustomDisk");
        this.name = dijit.byId("dashboardDiskName");
        this.description = dijit.byId("dashboardDiskDescription");
        this.diskSize = dijit.byId("dashboardDiskSize");
        this.storageTags = dijit.byId("diskStorageTags");
        this.name.setValue(currentDiskListWidget.additionalProperties.name);
        this.description.setValue(currentDiskListWidget.additionalProperties.description);

        dojo.byId("dashboardDiskSizeLabel").innerHTML = currentDiskListWidget.additionalProperties.diskSize + " GB";
        if (currentDiskListWidget.additionalProperties.storageTags == "" || currentDiskListWidget.additionalProperties.storageTags == null) {
            dojo.byId("dashboardDiskStorageTagsLabel").innerHTML = ".......";
        } else {
            dojo.byId("dashboardDiskStorageTagsLabel").innerHTML =
                    currentDiskListWidget.additionalProperties.storageTags;
        }

        if (currentDiskListWidget.additionalProperties.customDisk == "false" || currentDiskListWidget.additionalProperties.customDisk == false) {
            dojo.byId("dashboardCustomDiskLabel").innerHTML = "no";
        } else if (currentDiskListWidget.additionalProperties.customDisk == "true" || currentDiskListWidget.additionalProperties.customDisk == true) {
            dojo.byId("dashboardCustomDiskLabel").innerHTML = "yes";
        }
        var diskCol = dojo.query("#dashboardDiskList .WizardListItem");
        dojo.forEach(diskCol, function(el) {
            if (el.id == currentWidget.id) {
                currentWidget.setSelectState(true, currentWidget.id);
            } else {
                var otherWidget = dijit.byId(el.id);
                otherWidget.setSelectState(false, el.id);
            }
        });


        dijit.byId('dashboardDiskAddButton').set('style', {display: 'none'});
        dijit.byId('dashboardDiskUpdateButton').set('style', {display: 'block', "float": 'left'});
    },
    authetication: function() {
        var status = true;
        var pageNode = dojo.byId("dashboardDiskOfferingPage");
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

        var zoneNode = dojo.byId("dashboardCurrentDiskZoneInfo");
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);

        dojo.forEach(zoneWidgets, function(el) {

            status = el.showError();
        });

        return status;
    },
    add: function() {
        var status = DashboardDiskOffer.authetication();
        if (status == true) {
            var diskZoneCosts = [];
            var name = dijit.byId("dashboardDiskName");
            dijit.byId("dashboardDiskAddButton").set("disabled", true);
            var editValue = dijit.byId("dashboardDiskDescription").value.toString();
            var description = editValue.replace("\n", " ");
            var diskSize = dijit.byId("dashboardDiskSize");
            var storageTag;
            var storageType = dijit.byId("dashboardDiskStorageType");
            var customDisk = dijit.byId("dashboardCustomDisk");
            var podWidget = dijit.byId("dashboardDiskOfferPod");
            var clusterWidget = dijit.byId("dashboardDiskOfferCluster");

            var minSize = dijit.byId("dashboardDiskMinSize").value;
            var maxSize = dijit.byId("dashboardDiskMaxSize").value;
            if (dijit.byId("dashboardDiskOfferStorageTagList").value == "option") {
                storageTag = "";
            } else {
                storageTag = dijit.byId("dashboardDiskOfferStorageTagList").attr('displayedValue');
            }

            if (customDisk.checked == true) {
                diskSize = 0;
            } else {
                diskSize = dijit.byId("dashboardDiskSize").value;
            }


            var diskZoneNode = dojo.byId("dashboardCurrentDiskZoneInfo");
            this.diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
            dojo.forEach(this.diskZoneWidgets, function(el) {
                diskZoneCosts.push({
                    zoneId: el.getZoneId(),
                    cost: el.getZoneCost(),
                    minimumCost: "5"
                });
            });

            for (var index = 0; index < diskZoneCosts.length - this.diskZoneWidgets.length; index++) {
                diskZoneCosts.splice(index, this.diskZoneWidgets.length);
            }

            var diskOfferListItem;
            this._diskOfferRestStore.add({
                name: name.value,
                referenceZoneId: this._zoneWidget.value,
                podReferenceId: podWidget.value,
                clusterReferenceId: clusterWidget.value,
                customDisk: customDisk.checked,
                description: description,
                diskSize: diskSize,
                minSize: minSize,
                storageType: storageType.value,
                maxSize: maxSize,
                storageTag: storageTag,
                zoneCosts: diskZoneCosts
            }).then(function(data) {
                dijit.byId("dashboardDiskAddButton").set("disabled", false);
                if (data[0].result == "OK") {
                    dojo.byId("dashboardDiskSizeHide").style.display = "block";
                    diskOfferListItem = new FogPanel.WizardListItem({
                        heading: data[0].diskOffer.name,
                        description: data[0].diskOffer.description,
                        onClick: function() {
                            DashboardDiskOffer.returnDiskOffer(this);
                        },
                        onDeleteTagClick: function() {
                            DashboardDiskOffer.deleteCurrentWidget(this, "false");
                        },
                        additionalProperties: {
                            customDisk: data[0].diskOffer.customized,
                            id: data[0].diskOffer.id,
                            description: data[0].diskOffer.description,
                            diskSize: data[0].diskOffer.size,
                            name: data[0].diskOffer.name,
                            storageTags: data[0].diskOffer.tags,
                            zoneCosts: data[0].diskOffer.diskOfferZoneCosts,
                            storageType: data[0].storageType
                        }
                    });
                    diskOfferListItem.placeAt("dashboardDiskList");
                    diskOfferListItem.startup();
                    var diskZoneNode = dojo.byId("dashboardCurrentDiskZoneInfo");
                    var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
                    dojo.forEach(diskZoneWidgets, function(el) {
                        el.clearWidgets();
                    });
                    registry.byId("appToaster").setContent("Added Successfully!", "message");
                    registry.byId("appToaster").show();

                    dijit.byId("dashboardDiskDescription").set("value", "");
                    dijit.byId("dashboardDiskOfferContentForm").reset();

                } else {
                    registry.byId("appToaster").setContent("Cannot be Added!", "error");
                    registry.byId("appToaster").show();
                }
            });
        }

    },
    update: function() {

        var status = DashboardDiskOffer.authetication();
        if (status == true) {
            dijit.byId("dashboardDiskUpdateButton").set("disabled", true);
            var currentWidgetId = dojo.byId("dashboardCurrentDiskOfferId").innerHTML;
            var currentDiskListWidget = dijit.byId(currentWidgetId);
            this.name = dijit.byId("dashboardDiskName");

            var editValue = dijit.byId("dashboardDiskDescription").value.toString();
            var description = editValue.replace("\n", " ");
            currentDiskListWidget.additionalProperties.heading = this.name.value;
            currentDiskListWidget.additionalProperties.description = this.description.value;

            var zoneNode = dojo.byId("dashboardCurrentDiskZoneInfo");
            var zoneWidgets = dijit.registry.findWidgets(zoneNode);
            dojo.forEach(zoneWidgets, function(el) {
                currentDiskListWidget.additionalProperties.zoneCosts.push({
                    zoneId: el.getZoneId(),
                    cost: el.getZoneCost(),
                    minimumCost: "6"
                });

            });
            for (var index = 0; index < currentDiskListWidget.additionalProperties.zoneCosts.length - zoneWidgets.length; index++) {
                currentDiskListWidget.additionalProperties.zoneCosts.splice(index, zoneWidgets.length);
            }

            this._diskOfferRestStore.put({
                id: currentDiskListWidget.additionalProperties.id,
                name: this.name.value,
                description: description,
                zoneCosts: currentDiskListWidget.additionalProperties.zoneCosts

            }).then(function(result) {
                dijit.byId("dashboardDiskUpdateButton").set("disabled", false);
                if (result == "OK") {

                    currentDiskListWidget.getData();

                    dojo.query("#dashboardDiskOfferingPage .updatable").removeClass("non_updatable", true);
                    dojo.query("#dashboardDiskOfferingPage .hide_lable").removeClass("show_lable", true);

                    dijit.byId('dashboardDiskAddButton').set('style', {display: 'block', "float": 'left'});
                    dijit.byId('dashboardDiskUpdateButton').set('style', {display: 'none'});

                    var diskZoneNode = dojo.byId("dashboardCurrentDiskZoneInfo");
                    var diskZoneWidgets = dijit.registry.findWidgets(diskZoneNode);
                    dojo.forEach(diskZoneWidgets, function(el) {
                        el.clearWidgets();

                    });
                    registry.byId("appToaster").setContent("updated Successfully!", "message");
                    registry.byId("appToaster").show();
                    dijit.byId("dashboardDiskOfferContentForm").reset();
                    dijit.byId("dashboardDiskDescription").set("value", "");

                    dojo.byId("dashboardDiskZoneName").innerHTML = "";
                    dojo.byId("dashboardDiskPodName").innerHTML = "";
                    dojo.byId("dashboardDiskClusterName").innerHTML = "";
                    dijit.byId("dashboardDiskCancelButton").set("disabled", true);
                } else {
                    registry.byId("appToaster").setContent("Cannot be updated !", "error");
                    registry.byId("appToaster").show();
                }
            });
            currentDiskListWidget.setSelectState(false, currentDiskListWidget.id);
        }
    },
    getTags: function(currentValue) {
        var storageTagRestData = new JsonRest({
            target: core.getContextPath() + "/api/storagePool"
        });

        var storageType = currentValue.attr('displayedValue');
        var storageTagOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var storageTagList = new ItemFileWriteStore({data: storageTagOptions});
        var clusterWidget = dijit.byId("dashboardDiskOfferCluster");
        var storageTagWidget = dijit.byId("dashboardDiskOfferStorageTagList");
        storageTagRestData.query({
            clusterReferenceId: clusterWidget.value,
            storageType: storageType
        }).then(function(data) {
            if (data.length != 0) {
                dojo.forEach(data, function(currentStorageTag) {
                    if (currentStorageTag.storageTag == null || currentStorageTag.storageTag == "null") {
                        storageTagList.newItem({id: "option", name: "No Tag"});
                    } else {
                        storageTagList.newItem({id: currentStorageTag.storagePoolReferenceId, name: currentStorageTag.storageTag});
                    }

                });
            } else {
                storageTagList.newItem({id: "option", name: "Select Tag"});
            }
            storageTagWidget.setStore(storageTagList);
        });
    },
    cancel: function() {
        dojo.byId("dashboardDiskSizeHide").style.display = "block";
        var currentWidgetId = dojo.byId("dashboardCurrentDiskOfferId").innerHTML;
        var currentDiskListWidget = dijit.byId(currentWidgetId);
        dijit.byId("dashboardDiskDescription").set("value", "");
        dojo.byId("dashboardDiskZoneName").innerHTML = "";
        dojo.byId("dashboardDiskPodName").innerHTML = "";
        dojo.byId("dashboardDiskClusterName").innerHTML = "";
        currentDiskListWidget.setSelectState(false, currentDiskListWidget.id);
        dojo.query("#dashboardDiskOfferingPage .updatable").removeClass("non_updatable", true);
        dojo.query("#dashboardDiskOfferingPage .hide_lable").removeClass("show_lable", true);

        dijit.byId('dashboardDiskAddButton').set('style', {display: 'block', "float": 'left'});
        dijit.byId('dashboardDiskUpdateButton').set('style', {display: 'none'});
//         dijit.byId("dashboardDiskOfferZoneForm").reset();
        dijit.byId("dashboardDiskOfferContentForm").reset();
//         dojo.query("#dashboardDiskListCollection .WizardListItem").forEach(dojo.destroy);
//        dojo.query("#dashboardDiskListCollection .WizardZoneInfo").forEach(dojo.destroy);
//        dojo.byId("diskViewAllTag").style.display = "none";
        var zoneNode = dojo.byId("dashboardCurrentDiskZoneInfo");
        dijit.byId("dashboardDiskCancelButton").set("disabled", true);
        var zoneWidgets = dijit.registry.findWidgets(zoneNode);
        dojo.forEach(zoneWidgets, function(el) {
            el.clearWidgets();
        });
    },
    enableContent: function(currentElement) {

        var customDisk = dijit.byId("dashboardCustomDisk");
        if (currentElement.checked == true) {
            dojo.byId("dashboardDiskSizeHide").style.display = "none";
            dojo.query("#dashboardDiskOfferContent .form-horizontal .diskCustomSize").toggleClass("updatableContent ", true);
        } else if (customDisk.checked == false) {
            dojo.byId("dashboardDiskSizeHide").style.display = "block";
            dojo.query("#dashboardDiskOfferContent .form-horizontal .diskCustomSize").removeClass("updatableContent ", true);
        }
    }

};
var DashboardTestForm = {
    init: function() {


        var summaryData = {
            items: []
        };
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [[
                {'field': 'id', 'width': '200px', 'hidden': 'true'},
                {'name': 'Zone', 'field': 'zone', 'width': '200px'},
                {'name': 'Pod', 'field': 'pod', 'width': '200px'},
                {'name': 'Cluster', 'field': 'cluster', 'width': '200px'},
                {'name': 'Cost', 'field': 'cost', 'width': '150px'}

            ]
        ];


        summaryDataList.newItem({id: "1", zone: "zone", pod: "zone", cluster: "zone", cost: "zone"});
        summaryDataList.newItem({id: "2", zone: "zone", pod: "zone", cluster: "zone", cost: "zone"});
        summaryDataList.newItem({id: "3", zone: "zone", pod: "zone", cluster: "zone", cost: "zone"});
        summaryDataList.newItem({id: "4", zone: "zone", pod: "zone", cluster: "zone", cost: "zone"});
        summaryDataList.newItem({id: "5", zone: "zone", pod: "zone", cluster: "zone", cost: "zone"});
        summaryDataList.newItem({id: "6", zone: "zone", pod: "zone", cluster: "zone", cost: "zone"});
        summaryDataList.newItem({id: "7", zone: "zone", pod: "zone", cluster: "zone", cost: "zone"});

        var summaryGridWidget = new DataGrid({
            id: "dashboardGrid",
            store: summaryDataList,
            structure: summaryLayout,
            height: '350px'

        });
        summaryGridWidget.placeAt("testDataGrid");
        summaryGridWidget.startup();
    }

};
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
    "dojox/calendar/Calendar",
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/PlotKit/blue",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojo/_base/connect",
    'dojox/timing',
    "FogPanel/Notification",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dojox/layout/ScrollPane",
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
    "dijit/form/MultiSelect",
    "dijit/layout/TabContainer"
],
function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
ItemFileWriteStore, DataGrid, ContentPane, Source, MultiSelect, dom, win, Calendar, Memory, Observable,
Chart, Pie, Tom, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, connect, timing, Notification) {
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
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;
    window.Calendar = Calendar;
    window.Memory = Memory;
    window.Observable = Observable;
    window.Chart = Chart;
    window.Pie = Pie;
    window.Tom = Tom;
    window.Tooltip = Tooltip;
    window.MoveSlice = MoveSlice;
    window.theme = theme;
    window.ColumnsPlot = ColumnsPlot;
    window.Highlight = Highlight;
    window.Lines = Lines;
    window.timing = timing;
    window.connect = connect;

    App._loader = dojo.byId("adminLoader");
    App.hideLoader();


    var notificationTimer = new timing.Timer(180000);
    notificationTimer.onTick = function() {
        AdminUpdateTimer.sync();
    };
    notificationTimer.onStart = function() {


        var ticketNotificationWidget = new Notification({
            id: "ticketNotification"
        }).placeAt("adminTicketNotification");
        ticketNotificationWidget.startup();

        ticketNotificationWidget.subscribe("/FogPanel/ticket/notifications");
        ticketNotificationWidget.setLink("#/admin/support/tickets");

        var cloudUsageNotificationWidget = new Notification({
            id: "cloudUsageNotification"
        }).placeAt("cloudUsageNotificationDiv");
        cloudUsageNotificationWidget.startup();

        cloudUsageNotificationWidget.subscribe("/FogPanel/cloudusage/notifications");
        cloudUsageNotificationWidget.setLink("#/admin/activity/cloudUsage");

        var ticketCount = 0;
        var ticNotificationData = [];
        var ticketNotification = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/notification"
        });

        ticketNotification.query().then(function(data) {
            dojo.forEach(data, function(notificationDataItem) {
                if (ticketCount <= 5) {
                    ticNotificationData[ticketCount] = {link: notificationDataItem.link, icon: notificationDataItem.icon, value: "TicketId: " + notificationDataItem.ticId + " - " + notificationDataItem.subject};
                }
                ticketCount++;
            });
            connect.publish("/FogPanel/ticket/notifications", [ticNotificationData]);
            dojo.byId("ticketCount").innerHTML = ticketCount;
        });

        var usageCount = 0;
        var usageNotificationData = [];
        var usageNotification = new JsonRest({
            target: core.getContextPath() + "/api/notification/cloudUsage"
        });

        usageNotification.query().then(function(data) {
            dojo.forEach(data, function(usageNotificationDataItem) {
                if (usageNotificationDataItem.viewed == false) {
                    if (usageCount <= 5) {
                        usageNotificationData[usageCount] = {link: usageNotificationDataItem.link, icon: usageNotificationDataItem.icon, value: usageNotificationDataItem.description};
                    }
                    usageCount++;
                }
            });
            connect.publish("/FogPanel/cloudusage/notifications", [usageNotificationData]);
            dojo.byId("cloudUsageStatusCount").innerHTML = usageCount;
        });
    };
    notificationTimer.start();

    controller({
        name: "dashboard",
        module: "admin",
        filePath: "/js/app/admin/dashboard.js",
        layout: {
            name: "admin_index",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {
            core.ui.loadTemplate("admin_index", core.ui.getContentId());
            ConfigTest.init();
            LicenseCheck.init();
            AdminDashboard.init();
            AdminDashboard.populateValues();
        }),
        "wizard": action(function() {
            core.ui.loadTemplate("wizard_page", core.ui.getContentId());
            DashboardWizard.init();
            DashboardWizard.populateValues();

        }),
        "testWizardTempPage3": action(function() {
            core.ui.loadTemplate("testWizardTempPage3", core.ui.getContentId());
        }),
        "computingOffer": action(function() {
            var currentComputingOfferItems = dijit.byId("dashboardComputingOfferForm");
            if (currentComputingOfferItems && dijit.byId("dashboardComputingOfferContentForm")) {
                dijit.byId("dashboardComputingOfferForm").destroyRecursive();
                dijit.byId("dashboardComputingOfferContentForm").destroyRecursive();
            }
            core.ui.loadTemplate("computingOffer", core.ui.getContentId());
            DashboardFogWizardComputingOffer.init();
            DashboardFogWizardComputingOffer.populateValues();
            ConfigTest.init();
        }),
        "form": action(function() {
            core.ui.loadTemplate("formPage", core.ui.getContentId());
            DashboardTestForm.init();
        }),
        "diskOffer": action(function() {
            var currentDiskOfferItems = dijit.byId("dashboardDiskOfferZoneForm");
            if (currentDiskOfferItems) {
                dijit.byId("dashboardDiskOfferContentForm").destroyRecursive();
                dijit.byId("dashboardDiskOfferZoneForm").destroyRecursive();
            }

            core.ui.loadTemplate("diskOffer", core.ui.getContentId());
            DashboardDiskOffer.init();
            DashboardDiskOffer.populateValues();
            ConfigTest.init();
        }),
        "miscelPage": action(function() {
            var currentMiscelPage = dijit.byId("dashboardMicelPage");
            if (dijit.byId("dashboardMiscelZoneCostInfo") && dijit.byId("dashboardSummaryDialog")) {
                dijit.byId("dashboardMiscelZoneCostInfo").destroyRecursive();
                dijit.byId("dashboardSummaryDialog").destroyRecursive();
            }
            core.ui.loadTemplate("miscelPage", core.ui.getContentId());
            DashboardFogWizardMiscellaneousCost.init();
            DashboardFogWizardMiscellaneousCost.populateValues();
            ConfigTest.init();
        }),
        "tempPage": action(function() {
            var currentTempPage = dijit.byId("dashboardTempForm");
            if (currentTempPage) {
                dijit.byId("dashboardTempForm").destroyRecursive();
            }


            ConfigTest.init();
            DashboardTemp.initZone();
            DashboardTemp.init();
            DashboardTemp.populateValues();
        }),
        "appTempPage": action(function() {
            var currentTempPage = dijit.byId("dashboardAppTempForm");
            if (currentTempPage) {
                dijit.byId("dashboardAppTempForm").destroyRecursive();
            }
            core.ui.loadTemplate("appTempPage", core.ui.getContentId());
            ConfigTest.init();
            DashboardAppTemp.initZone();
            DashboardAppTemp.init();
            DashboardAppTemp.populateValues();
        }),
        "userProfile": action(function() {
            var currentUPage = dijit.byId("userProfileForm");
            if (currentUPage) {
                dijit.byId("userProfileForm").destroyRecursive();
            }

            core.ui.loadTemplate("userProfile", core.ui.getContentId());
            ConfigTest.init();
            DashboardUserProfile.init();
            DashboardUserProfile.populateValues();
        }),
        "accountProfile": action(function() {
            var currentAccountPage = dijit.byId("accountProfileForm");
            if (currentAccountPage) {
                dijit.byId("accountProfileForm").destroyRecursive();
            }

            core.ui.loadTemplate("accountProfile", core.ui.getContentId());
            DashboardAccountProfile.init();
            DashboardAccountProfile.populateValues();
        }),
        "securityGroups": action(function() {

        }),
        "accountList": action(function() {

        }),
        "invoice": action(function() {
            core.ui.loadTemplate("invoice", core.ui.getContentId());
            InvoiceList.init();
            InvoiceList.populateValues();
        }),
        "loginAttemptSetup": action(function() {
            var currentLoginSetupPage = dijit.byId("loginAttemptConfigForm");
            if (currentLoginSetupPage) {
                dijit.byId("loginAttemptConfigForm").destroyRecursive();
            }

            core.ui.loadTemplate("loginAttemptSetup", core.ui.getContentId());
            LoginAttemptSetup.init();
        }),
        "grouping": action(function() {
            var currentPage = dijit.byId("osTypeForm");
            if (currentPage) {
                dijit.byId("osTypeForm").destroyRecursive();
            }

            core.ui.loadTemplate("grouping", core.ui.getContentId());
            GroupingInfo.init();
            GroupingInfo.populateValues();

       }),
        "cloudConfig": action(function() {
            var currentPage = dijit.byId("dashboardSystemConfigForm");
            if (currentPage) {
                dijit.byId("dashboardSystemConfigForm").destroyRecursive();
                dijit.byId("dashboardConfigLoader").destroyRecursive();

            }
            core.ui.loadTemplate("cloudConfig", core.ui.getContentId());
            DashboardConfig.init();
            DashboardConfig.populateValues();
        }),
        "maintainance": action(function() {
            var viewEventPage = dijit.byId("viewEventPage");
            if (viewEventPage) {
                dijit.byId("viewEventPage").destroyRecursive();
            }
            core.ui.loadTemplate("maintainance", core.ui.getContentId());
            CalendarInfo.init();
            CalendarInfo.populateValues();
        }),
        "cloudMaintenance": action(function() {
            var currentPage = dijit.byId("cloudMaintenanceForm");
            if (currentPage) {
                dijit.byId("cloudMaintenanceForm").destroyRecursive();
            }
            core.ui.loadTemplate("cloudMaintenance", core.ui.getContentId());
            CloudMaintenance.init();
            CloudMaintenance.populateValues();
        })
    });

    var verticalMenuBarWidget = dijit.byId("verticalMenu");
    verticalMenuBarWidget.subscribe("/FogPanel/event/route/changed");
});


window.ConfigTest = ConfigTest;
window.DashboardDiskOffer = DashboardDiskOffer;
window.DashboardFogWizardComputingOffer = DashboardFogWizardComputingOffer;
window.DashboardFogWizardMiscellaneousCost = DashboardFogWizardMiscellaneousCost;
//window.DashboardTemp = DashboardTemp;   
//window.DashboardInstances = DashboardInstances;
window.DashboardAccountProfile = DashboardAccountProfile;
window.DashboardUserProfile = DashboardUserProfile;
//window.DashboardStorage = DashboardStorage;

//window.AccountList = AccountList;
window.InvoiceList = InvoiceList;

window.DashboardConfig = DashboardConfig;
window.DashboardWizard = DashboardWizard;
window.DashboardTestForm = DashboardTestForm;
window.CloudMaintenance = CloudMaintenance;
window.App = App;
window.CalendarInfo = CalendarInfo;
window.AdminUpdateTimer = AdminUpdateTimer;
