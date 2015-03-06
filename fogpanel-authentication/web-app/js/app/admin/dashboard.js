"use strict";
var App = {
    _loader: null,
    hideLoader: function () {
        this._loader.style.display = "none";
    }
};
var AdminUpdateTimer = {
    sync: function () {

        var ticketCount = 0;
        var ticNotificationData = [];
        var ticketNotification = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/notification"
        });

        ticketNotification.query().then(function (data) {
            dojo.forEach(data, function (notificationDataItem) {
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

        usageNotification.query().then(function (data) {
            dojo.forEach(data, function (usageNotificationDataItem) {
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
    'LoginInit': function () {

        var sessionRest = new JsonRest({
            target: core.getContextPath() + "/api/cloudEngine/getSessionInfo"
        });
        var currentUserName;
        var currentPassword;
        var openstackUrl;

        sessionRest.query({
        }).then(function (data) {
            dojo.forEach(data, function (resultData) {

                currentUserName = resultData.userName;
                currentPassword = resultData.password;
                openstackUrl = resultData.openstackUrl;
            });

            console.log("openstackUrl:" + openstackUrl)
            console.log("userName:" + currentUserName)
            dojo.byId("openstackLoginDetailId").href = openstackUrl + "/login/ssoSuccess?userName=" + currentUserName + "&password=" + currentPassword;
        });

    },
    'defaultStatus': function () {

        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });

        asyncJob.query({jobType: "PULL_FLAVOR"}).then(function (data) {
            if (data[0] === "OK") {
                dojo.byId("pullPlanLoaderImage").style.display = "inline";
                dojo.byId("pullPlanCheckImage").style.display = "none";
                dijit.byId("pullPlanButton").set("disabled", true);
            } else if (data[0] === "FALSE") {
                dojo.byId("pullPlanLoaderImage").style.display = "none";
                dojo.byId("pullPlanCheckImage").style.display = "none";
                dijit.byId("pullPlanButton").set("disabled", false);
            }
        });

        asyncJob.query({jobType: "PULL_IMAGE"}).then(function (data) {
            if (data[0] === "OK") {
                dojo.byId("pullImageLoaderImage").style.display = "inline";
                dojo.byId("pullImageCheckImage").style.display = "none";
                dijit.byId("pullImageButton").set("disabled", true);
            } else if (data[0] === "FALSE") {
                dojo.byId("pullImageLoaderImage").style.display = "none";
                dojo.byId("pullImageCheckImage").style.display = "none";
                dijit.byId("pullImageButton").set("disabled", false);
            }
        });

        asyncJob.query({jobType: "PULL_VOLUME_TYPE"}).then(function (data) {
            if (data[0] === "OK") {
                dojo.byId("pullVolumeTypeLoaderImage").style.display = "inline";
                dojo.byId("pullVolumeTypeCheckImage").style.display = "none";
                dijit.byId("pullVolumeTypeButton").set("disabled", true);
            } else if (data[0] === "FALSE") {
                dojo.byId("pullVolumeTypeLoaderImage").style.display = "none";
                dojo.byId("pullVolumeTypeCheckImage").style.display = "none";
                dijit.byId("pullVolumeTypeButton").set("disabled", false);
            }
        });

    },
    "checkDataImportVolumeTypeStatus": function () {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });

        asyncJob.query({jobType: "PULL_VOLUME_TYPE"}).then(function (data) {
            if (data[0] === "OK") {
                dojo.byId("pullVolumeTypeLoaderImage").style.display = "inline";
                dojo.byId("pullVolumeTypeCheckImage").style.display = "none";
                dijit.byId("pullVolumeTypeButton").set("disabled", true);
            } else if (data[0] === "FALSE") {
                dojo.byId("pullVolumeTypeLoaderImage").style.display = "none";
                dojo.byId("pullVolumeTypeCheckImage").style.display = "inline";
                dijit.byId("pullVolumeTypeButton").set("disabled", false);
            }
        });

    },
    "checkDataImportPlanStatus": function () {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });

        asyncJob.query({jobType: "PULL_FLAVOR"}).then(function (data) {
            if (data[0] === "OK") {
                dojo.byId("pullPlanLoaderImage").style.display = "inline";
                dojo.byId("pullPlanCheckImage").style.display = "none";
                dijit.byId("pullPlanButton").set("disabled", true);
            } else if (data[0] === "FALSE") {
                dojo.byId("pullPlanLoaderImage").style.display = "none";
                dojo.byId("pullPlanCheckImage").style.display = "inline";
                dijit.byId("pullPlanButton").set("disabled", false);
            }
        });
    },
    "checkDataImportImageStatus": function () {
        var asyncJob = new JsonRest({
            target: core.getContextPath() + "/api/asyncJob"
        });

        asyncJob.query({jobType: "PULL_IMAGE"}).then(function (data) {
            if (data[0] === "OK") {
                dojo.byId("pullImageLoaderImage").style.display = "inline";
                dojo.byId("pullImageCheckImage").style.display = "none";
                dijit.byId("pullImageButton").set("disabled", true);
            } else if (data[0] === "FALSE") {
                dojo.byId("pullImageLoaderImage").style.display = "none";
                dojo.byId("pullImageCheckImage").style.display = "inline";
                dijit.byId("pullImageButton").set("disabled", false);
            }
        });
    },
    'conformPullPlan': function () {
        dijit.byId("pullPlanConform").show();
    },
    'cancelPullPlan': function () {
        dijit.byId("pullPlanConform").hide();
    },
    "pullPlan": function () {

        dijit.byId("pullPlanConform").hide();

        var pullPlanRestStore = new JsonRest({
            target: core.getContextPath() + "/api/flavor/pullFromOpenstack"
        });

        pullPlanRestStore.query().then(function (data) {
            dojo.forEach(data, function (resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullPlanLoaderImage").style.display = "inline";
                    dijit.byId("pullPlanButton").set("disabled", true);
                    dojo.byId("pullPlanCheckImage").style.display = "none";

                } else {
                    dijit.byId("pullPlanButton").set("disabled", false);
                    dojo.byId("pullPlanLoaderImage").style.display = "none";
                    dojo.byId("pullPlanCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    'confirmPullImage': function () {
        dijit.byId("pullImageConfirm").show();
    },
    'cancelPullImages': function () {
        dijit.byId("pullImageConfirm").hide();
    },
    'pullImages': function () {

        dijit.byId("pullImageConfirm").hide();

        var pullImageRestStore = new JsonRest({
            target: core.getContextPath() + "/api/image/pullFromOpenstack"
        });

        pullImageRestStore.query().then(function (data) {
            dojo.forEach(data, function (resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullImageLoaderImage").style.display = "inline";
                    dijit.byId("pullImageButton").set("disabled", true);
                    dojo.byId("pullImageCheckImage").style.display = "none";
                } else {
                    dojo.byId("pullImageLoaderImage").style.display = "inline";
                    dijit.byId("pullImageButton").set("disabled", true);
                    dojo.byId("pullImageCheckImage").style.display = "none";
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });

    },
    'confirmPullVolumeType': function () {
        dijit.byId("pullVolumeTypeConfirm").show();
    },
    'cancelPullVolumeType': function () {
        dijit.byId("pullVolumeTypeConfirm").hide();
    },
    'pullVolumeType': function () {

        dijit.byId("pullVolumeTypeConfirm").hide();

        var pullFromRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volumetypes/pullFromOpenstack"
        });

        pullFromRestStore.query().then(function (data) {
            dojo.forEach(data, function (resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullVolumeTypeLoaderImage").style.display = "inline";
                    dijit.byId("pullVolumeTypeButton").set("disabled", true);
                    dojo.byId("pullVolumeTypeCheckImage").style.display = "none";

                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dojo.byId("pullVolumeTypeLoaderImage").style.display = "none";
                    dojo.byId("pullVolumeTypeCheckImage").style.display = "none";
                    dijit.byId("pullVolumeTypeButton").set("disabled", false);
                }
            });
        });

    },
    'init': function () {
    },
    'populateValues': function () {

//        var usageInfo = new JsonRest({
//            target: core.getContextPath() + "/api/account/usage"
//        });

        usageInfo.query().then(function (data) {
            dojo.forEach(data, function (usageData) {
//                dojo.byId("totalDaily").innerHTML = usageData.daily.toFixed(2);
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

                if (usageData.enableSignup === "false" || usageData.zenossConfig === "false") {
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

                if (usageData.openstackConfig === "false") {
                    dojo.byId("openstackConfigLi").style.display = "block";
                } else {
                    dojo.byId("openstackConfigLi").style.display = "none";
                }

                if (usageData.zenossConfig === "false") {
                    dojo.byId("zenossConfigLi").style.display = "block";
                } else {
                    dojo.byId("zenossConfigLi").style.display = "none";
                }

                if (usageData.openstackConfig === "false") {

                    dojo.byId("importData").style.display = "none";

                } else {
                    if (usageData.regionConfig === "false"
                            || usageData.isFlavorEmpty === "false"
                            || usageData.isImageEmpty === "false"
                            || usageData.volumeTypeConfig === "false"
                            || usageData.isZoneEmpty === "false"
                            || usageData.isDomainEmpty === "false"
                            || usageData.isNetworkEmpty === "false") {
                        dojo.byId("importData").style.display = "block";
                    } else {

                        dojo.byId("importData").style.display = "none";
                        if (usageData.defaultDomainConfig === "false") {
                            dojo.byId("domainLi").style.display = "block";
                        } else {
                            dojo.byId("domainLi").style.display = "none";
                        }
                    }
                }

            });
        });

    },
    'loadBillableItemChart': function () {

        var bItemChartStore = new JsonRest({
            target: core.getContextPath() + "/api/invoice/chart/billableItem"
        });

        var chartData = [];
        bItemChartStore.query().then(function (data) {
            dojo.forEach(data, function (bItemData) {
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
    'loadDailyCostChart': function () {

//        <div class="alert alert-info hide text_gray" id="noNetworkMessageBox" style="display: none">
//                    <i class="icon-exclamation-sign text_gray"></i> 
//                    <g:message code="user.network.noNetworkMsg"/>&nbsp;&nbsp;<a href="#/user/network/add"><g:message code="common.createOneNow"/></a>
//                </div>
        dojo.byId("dailyCostChart").innerHTML = "<div class='alert alert-info text_gray'><i class='icon-exclamation-sign text_gray'></i>" + translator.common.noUsageMsg + "</div>";
//        dojo.byId("dailyCostChart").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
//        dojo.byId("dailylegende").innerHTML = "";  

//        var dailyUsageChartStore = new JsonRest({
//            target: core.getContextPath() + "/api/invoice/chart/dailyUsage"
//        });
//
//        var usageChartDataXAxisData = [];
//        var usageChartDataCost;
//        var refundChartDataCost;
//        var i = 1;
//        dailyUsageChartStore.query().then(function(data) {
//            dojo.forEach(data, function(usageData) {
//                usageChartDataCost = usageData.usageItemCostList;
//                refundChartDataCost = usageData.refundCostList
//                dojo.forEach(usageData.usageItemDateList, function(usageDate) {
//                    usageChartDataXAxisData.push({value: i, text: usageDate});
//                    i += 1;
//                });
//            });
//
//            dojo.byId("dailyCostChart").innerHTML = "";
//
//            // Create the chart within it's "holding" node
//            var chart = new Chart("dailyCostChart");
//
//            // Set the theme
//            chart.setTheme(theme);
//
//            // Add the only/default plot
//            chart.addPlot("default", {
//                type: "Lines",
//                markers: true
//            });
//
//            // Add axes
//            chart.addAxis("x", {labels: usageChartDataXAxisData});
//            chart.addAxis("y", {min: 5, vertical: true, fixLower: "major", fixUpper: "major"});
//
//            // Add the series of data
//            chart.addSeries("Usage", usageChartDataCost, {stroke: {color: "#87B84B"}});
//            chart.addSeries("Refund", refundChartDataCost, {stroke: {color: "#D30000"}});
//
//            new Magnify(chart, "default");
//            new Tooltip(chart, "default");
//
//            // Render the chart!
//            chart.render();
//
//            new Legend({chart: chart, horizontal: false}, "dailylegende");
//        });
    },
    'loadcreditLimitGrid': function () {

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

        creditLimitStore.query().then(function (data) {


            dojo.forEach(data, function (accountData) {

                totalAccount++;
                totalAmount = totalAmount + accountData.amount;
//                currency = accountData.currency;

            });

            if (data.length > 0) {
                dojo.forEach(data.slice(0, 5), function (accountData) {

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
    'loadPendingPaymentGrid': function () {

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

        paymentPendingStore.query().then(function (data) {

            dojo.forEach(data, function (accountData) {
                totalAccount++;
                totalAmount = totalAmount + accountData.amount;
//                currency = accountData.currency;
            });

            if (data.length > 0) {
                dojo.forEach(data.slice(0, 5), function (accountData) {

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
    'loadIncomeForecast': function () {
        var incomeForecastStore = new JsonRest({
            target: core.getContextPath() + "/api/invoice/incomeForecast"
        });

        incomeForecastStore.query().then(function (data) {
            dojo.forEach(data, function (incomeData) {
                dojo.byId("preMonth").innerHTML = incomeData.preMonth + " " + incomeData.currency;
                dojo.byId("quat").innerHTML = incomeData.qut + " " + incomeData.currency;
                dojo.byId("half").innerHTML = incomeData.half + " " + incomeData.currency;
                dojo.byId("annual").innerHTML = incomeData.year + " " + incomeData.currency;
            });
        });
    },
    'loadSystemOverview': function () {
        var systemStore = new JsonRest({
            target: core.getContextPath() + "/api/config/systemOverview"
        });

        systemStore.query().then(function (data) {
            dojo.forEach(data, function (systemData) {
                dojo.byId("version").innerHTML = systemData.version;
                if (systemData.licenseStatus === "EMERGENCY_TRIAL") {
                    dojo.byId("licenseStatus").innerHTML = "Emergency Trial";
                } else if (systemData.licenseStatus === "EXPIRED") {
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
    revalidateLicense: function () {
        var systemStore = new JsonRest({
            target: core.getContextPath() + "/api/config/revalidateLicense"
        });

        dojo.byId("systemOverviewLoading").style.display = "block";
        dojo.byId("systemOverviewInfo").style.display = "none";

        systemStore.query().then(function (data) {
            AdminDashboard.loadSystemOverview();
            dojo.byId("systemOverviewLoading").style.display = "none";
            dojo.byId("systemOverviewInfo").style.display = "block";
        });
    }
};

var CalendarInfo = {
    init: function () {
    },
    populateValues: function () {
        var billingAlerts = new JsonRest({
            target: core.getContextPath() + "/api/notification/billingAlerts"
        });
        var count = 0;
        var eventNo = 1;
        var eventData = [];
        billingAlerts.query().then(function (data) {
            dojo.forEach(data, function (alertData) {
                dojo.forEach(alertData.notification, function (notificationData) {
                    var date = notificationData.calendarDate.split("-");
                    var year = parseInt(date[2]);
                    var month = parseInt(date[1]) - 1;
                    var day = parseInt(date[0]);
                    eventData[count] = {
                        id: notificationData.id,
                        summary: "Event-" + eventNo,
                        startTime: new Date(year, month, day, 0),
                        endTime: new Date(year, month, day, 23),
                        data: notificationData.description,
                        currentDate: notificationData.date
                    };
                    count++;
                    eventNo++;
                });
                CalendarInfo.loadCalander(eventData);
            });
        });
    },
    loadCalander: function (eventData) {
        dojo.byId("maintainCalendarDiv").innerHTML = "";
        var notifiCalendar = new Calendar({
            onItemClick: function (e) {
                CalendarInfo.openEvent(e);
            },
            store: new Observable(new Memory({data: eventData})),
            dateInterval: "month",
            style: "position:relative;height:800px"
        }).placeAt("maintainCalendarDiv");
        notifiCalendar.startup();
    },
    openEvent: function (event) {
        dojo.byId("currentItemId").value = event.item.id;
        dojo.byId("maintenanceErrorMsg").style.display = "none";
        dijit.byId("viewEventPage").show();
        dojo.byId("eventDate").innerHTML = event.item.currentDate;
        dijit.byId("cloudMaintainDescription").setValue(event.item.data);
        dijit.byId("cloudMaintainDescription").setValue(event.item.data);
    },
    update: function () {
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
            }).then(function (result) {
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
    deleteItem: function () {
        var maintainRestore = new JsonRest({
            target: core.getContextPath() + "/api/cloudMaintenance/delete"
        });
        var itemId = dojo.byId("currentItemId").value;
        maintainRestore.put({
            itemId: itemId
        }).then(function (result) {
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
//    _zoneRestStore: "",
    _maintenanceStore: "",
    init: function () {
//        this._zoneRestStore = new JsonRest({
//            target: core.getContextPath() + "/api/zone/"
//        });

        this._maintenanceStore = new JsonRest({
            target: core.getContextPath() + "/api/cloudMaintenance/"
        });
    },
    populateValues: function () {

//        var sel = dojo.byId('maintenanceZoneList');
//        this._zoneRestStore.query().then(function(data) {
//            dojo.forEach(data, function(zone) {
//                var opt = document.createElement('option');
//                opt.innerHTML = zone.aliasName;
//                opt.value = zone.id;
//                sel.appendChild(opt);
//            });
//        });

        dijit.byId('cloudMaintenanceDate').set('value', new Date());
    },
    add: function () {

        var date = dojo.date.locale.format(dijit.byId("cloudMaintenanceDate").value, {datePattern: "dd/MM/yyyy", selector: "date"});
        var description = dijit.byId("cloudMaintenanceDateDescription");

        dijit.byId("maintainButton").setAttribute('style', 'display: none');
        dojo.byId("forgotPasswordLoader").style.display = "block";

        dijit.byId("cloudMaintenanceDate").set("disabled", true);
        dijit.byId("cloudMaintenanceDateDescription").set("disabled", true);

        this._maintenanceStore.add({
            date: date,
            description: description.value
        }).then(function (data) {
            dojo.forEach(data, function (data) {
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
            dijit.byId("cloudMaintenanceDate").set("disabled", false);
            dijit.byId("cloudMaintenanceDateDescription").set("disabled", false);
        });
    },
    cancel: function () {
        dijit.byId("cloudMaintenanceForm").reset();
    }
};

var DashboardWizard = {
    _count: 1,
    init: function () {

    },
    populateValues: function () {
        var wizard = dijit.byId("setupWizard");
    },
    go: function () {
    },
    activate: function () {
        var wizard = dijit.byId("setupWizard");
        wizard.setActive(3);
    },
    next: function () {
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
    previous: function () {
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
    closeDialogue: function () {
        dijit.popup.close(dijit.byId("adminSettingsDropdown"));
    }
};

var DashboardConfig = {
    _restStore: "",
    _configPageResult: "",
    configId: [],
    init: function () {
        this._restStore = new JsonRest({
            target: core.getContextPath() + "/api/config/",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });

    },
    populateValues: function () {
        var widgets = dojo.byId("dashboardConfigWidgets");
        var pageWidgets = dijit.registry.findWidgets(widgets);

        var cloudStackUrl = dijit.byId("dashboardUrl");
        var ssoUrl = dijit.byId("dashboardssoUrl");
        var cloudApiKey = dijit.byId("dashboardApi");
        var cloudSecretKey = dijit.byId("dashboardSecretKey");

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });

        currency.query().then(function (data) {
            dojo.forEach(data, function (cur) {
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

        this._configPageResult.query().then(function (data) {
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
    closeMessageBox: function () {
        var configPageResult = new JsonRest({
            target: core.getContextPath() + "/api/config/test/"
        });
        configPageResult.query().then(function (data) {
            if (data == "OK") {
//                core.router.go("#/admin/zone");
            }
        });
        dijit.byId("dashboardConfigLoader").hide();
    },
    edit: function () {
        dojo.query("#dashboardConfigWidgets .updatable").removeClass("non_updatable", true);
        dojo.query("#dashboardConfigWidgets .hide_lable").removeClass("show_lable", true);
        dijit.byId("dashboardVerifyButton").setAttribute("disabled", false);
    },
    update: function () {

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
        dojo.forEach(systemWidget, function (el) {
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
        }).then(function (data) {

            if (data == "OK") {
                configPageResult.query().then(function (data) {
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
    init: function () {

        var systemStore = new JsonRest({
            target: core.getContextPath() + "/api/config/systemOverview"
        });

        systemStore.query().then(function (data) {
            dojo.forEach(data, function (systemData) {
                if (systemData.licenseStatus === "EMERGENCY_TRIAL") {
                    dojo.byId("licenseErrorInfo").style.display = "block";
                    dojo.byId("messageInfo").innerHTML = "FogPanel License is in Emergency Trial, This may expire any minute. Contact support";
                } else if (systemData.licenseStatus === "EXPIRED") {
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
    init: function () {
        var navigator = dijit.byId("templateNavigator");
        navigator.enableNavigator();
        var varticalMenuBar = dijit.byId("verticalMenu");
        varticalMenuBar.enable();

        domClass.remove(dojo.byId("contentArea"), "diableSideMenu");

        var config = new JsonRest({
            target: core.getContextPath() + "/api/config/test/"
        });

        config.query().then(function (data) {
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
    configChangeCheck: function () {
        var configChangeCheckRest = new JsonRest({
            target: core.getContextPath() + "/api/config/configChangeCheck"
        });

        configChangeCheckRest.query().then(function (data) {
            console.log("value" + data)
            if (data == "true") {
                dojo.byId("configMsgId").style.display = "block";

            } else {
                dojo.byId("configMsgId").style.display = "none";
            }

        });
    },
    showSideMenu: function () {
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

var DashboardFogWizardDiskOffer = {
    init: function () {

    },
    populateValues: function () {

    }
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
    returnListData: function (id) {
        window.currentTempWidget = dijit.byId(id);
        this._tempRestStrore.query().then(function (data) {
            dojo.forEach(data, function (el) {
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

        dojo.forEach(tempListWidgets, function (el) {
            if (el.id == currentTempWidget.id) {
                currentTempWidget.setAttribute("style", 'border: 1px solid #62CCFE');
            } else {
                el.setAttribute("style", 'border: 1px solid #000000');
            }
        });
    },
    update: function () {
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
        }).then(function (results) {
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

        dojo.forEach(tempListWidgets, function (el) {
            el.setAttribute("style", 'border: 1px solid #000000');
        });

        dijit.byId("dashboardAppTempForm").reset();
    },
    cancel: function () {

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

        dojo.forEach(tempListWidgets, function (el) {
            el.setAttribute("style", 'border: 1px solid #000000');
        });

        dijit.byId("dashboardAppTempForm").reset();
    },
    authentication: function () {
        var pageNode = dojo.byId("tempPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var status = true;

        dojo.forEach(widgets, function (el) {
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
    add: function () {
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
            }).then(function (results) {
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
    init: function () {
        this._currentUserRestStore = new JsonRest({
            target: core.getContextPath() + "/api/user/currentUser/"
        });

        this._userRestStore = new JsonRest({
            target: core.getContextPath() + "/api/user/"
        });
    },
    populateValues: function () {
        this._currentUserRestStore.query().then(function (data) {
            dojo.forEach(data, function (el) {
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
    gotoResetPage: function () {
        dojo.byId("userProfile").style.display = "none";
        dojo.byId("userPasswordField").style.display = "block";
    },
    resetPassword: function () {
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
    init: function () {
        this._currentAccountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount/"
        });

        this._accountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });
    },
    populateValues: function () {
        this._currentAccountRestStore.query().then(function (data) {
            dojo.forEach(data, function (el) {
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
    update: function () {

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
    init: function () {
        this._accountListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account"
        });
    },
    populateValues: function () {

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
    init: function () {
    },
    add: function () {
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
    init: function () {
        this._computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer"
        });
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone"
        });
    },
    populateValues: function () {
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

        this._zoneRestStore.query().then(function (data) {
            dojo.forEach(data, function (zoneData) {
                zoneList.newItem({id: zoneData.referenceZoneId, name: zoneData.aliasName});
            });
        });

        var zoneWidget = new Select({
            id: "groupingZoneList",
            store: zoneList,
            onChange: function () {
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
            onChange: function () {
                dojo.query("#listCollection .WizardListItem").forEach(dojo.destroy);
                dojo.query("#selectedList .WizardListItem").forEach(dojo.destroy);
                var computingOfferRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/computingOffer"
                });
                var zoneWidget = dijit.byId("groupingZoneList");
                computingOfferRestStore.query({baseOs: this.value, zoneReferenceId: zoneWidget.value}).then(function (data) {
                    dijit.byId("selectAllButton").set("disabled", false);
                    dojo.forEach(data, function (offerData) {

                        var osListItem = new FogPanel.WizardListItem({
                            heading: offerData.name,
                            description: offerData.description,
                            cpu: offerData.cpuNumber,
                            ram: offerData.memory,
                            onClick: function () {
                                GroupingInfo.returnCurrentWidget(this);
                            },
//                          
                            onDeleteTagClick: function () {
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
    replaceCurrentWidget: function () {
        var currentSelectWidgetId = dojo.byId("selectWidgetId").value;
        var currentSelectWidget = dijit.byId(currentSelectWidgetId);
        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });

        computingOfferRestStore.get(currentSelectWidget.additionalProperties.id).then(function (data) {

            currentSelectWidget.destroyRecursive();
            var selectedListItem = new FogPanel.WizardListItem({
                heading: data.name,
                description: data.description,
                cpu: data.cpuNumber,
                ram: data.memory,
                onClick: function () {
                    GroupingInfo.returnCurrentWidget(this);
                },
//                          
                onDeleteTagClick: function () {
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
    placeWidget: function () {
        var currentWidgetId = dojo.byId("currentWidgetId").value;
        var currentWidget = dijit.byId(currentWidgetId);

        var computingOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });
        var computingOfferOrderRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/order/"
        });

        computingOfferRestStore.get(currentWidget.additionalProperties.id).then(function (data) {
            currentWidget.destroyRecursive();
            var selectedListItem = new FogPanel.WizardListItem({
                heading: data.name,
                description: data.description,
                cpu: data.cpuNumber,
                ram: data.memory,
                onClick: function () {
                    var currentId = this.id;
                    dojo.byId("selectWidgetId").value = currentId;
                    var listCollection = dojo.byId("selectedList");

                    var listWidgets = dijit.registry.findWidgets(listCollection);
                    dojo.forEach(listWidgets, function (el) {
                        if (el.id == currentId) {
                            currentWidget.setSelectState(true, currentId);
                        } else {
                            el.setSelectState(false, el.id);
                        }
                    });
                },
                onUpArrowClick: function () {
                    GroupingInfo.goUpWidget(this);
                },
                onDownArrowClick: function () {
                    GroupingInfo.goDownWidget(this);
                },
                onDeleteTagClick: function () {
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
                dojo.forEach(listWidgets, function (el, index) {
                    if (el.additionalProperties.id == data.id) {
                        computingOfferOrderRestStore.put({
                            id: data.id,
                            referenceId: data.offerReferenceId,
                            orderNo: index + 1
                        }).then(function (response) {
                            if (response[0].result == "OK") {
                                el.additionalProperties.count = response[0].computeOffer.orderNo;
                            }
                        });
                    }

                });
            }
        });
    },
    replaceAllWidget: function () {
        var listCollection = dojo.byId("selectedList");
        var listWidgets = dijit.registry.findWidgets(listCollection);
        dojo.forEach(listWidgets, function (el) {
            var selectedListItem = new FogPanel.WizardListItem({
                heading: el.additionalProperties.name,
                description: el.additionalProperties.description,
                cpu: el.additionalProperties.cpuNumber,
                ram: el.additionalProperties.memory,
                onClick: function () {
                    GroupingInfo.returnCurrentWidget(this);
                },
                onDeleteTagClick: function () {
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
    populateSelectedPlanes: function () {
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
        }).then(function (data) {
            dojo.forEach(data, function (offerData) {
                var osListItem = new FogPanel.WizardListItem({
                    heading: offerData.name,
                    description: offerData.description,
                    cpu: offerData.cpuNumber,
                    ram: offerData.memory,
                    onClick: function () {
                        var currentId = this.id;
                        var currentWidget = dijit.byId(currentId);
                        dojo.byId("selectWidgetId").value = currentId;
                        var listCollection = dojo.byId("selectedList");

                        var listWidgets = dijit.registry.findWidgets(listCollection);
                        dojo.forEach(listWidgets, function (el) {
                            if (el.id == currentId) {
                                currentWidget.setSelectState(true, currentId);
                            } else {
                                el.setSelectState(false, el.id);
                            }
                        });
                    },
                    onDeleteTagClick: function () {
                    },
                    onUpArrowClick: function () {
                        GroupingInfo.goUpWidget(this);
                    },
                    onDownArrowClick: function () {
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
    goDownWidget: function (currentWidget) {
        var currentCount = currentWidget.additionalProperties.count;
        var nextCount = currentWidget.additionalProperties.count + 1;

        var computingOfferOrderRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/order/"
        });

        var selectListCollection = dojo.byId("selectedList");
        var selectListWidgets = dijit.registry.findWidgets(selectListCollection);
        dojo.forEach(selectListWidgets, function (el) {
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
                }).then(function (response) {
                    if (response[0].result == "OK") {
                        GroupingInfo.populateSelectedPlanes();
                    }
                });


            }
        });
    },
    goUpWidget: function (currentWidget) {
        var currentCount = currentWidget.additionalProperties.count;
        var preCount = currentWidget.additionalProperties.count - 1;

        var computingOfferOrderRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/order/"
        });

        var selectListCollection = dojo.byId("selectedList");
        var selectListWidgets = dijit.registry.findWidgets(selectListCollection);
        dojo.forEach(selectListWidgets, function (el) {
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
                }).then(function (response) {
                    if (response[0].result == "OK") {
                        GroupingInfo.populateSelectedPlanes();
                    }
                });


            }
        });
    },
    placeAllWidget: function () {

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
            dojo.forEach(listWidgets, function (el) {
                computingOfferOrderRestStore.put({
                    id: el.additionalProperties.id,
                    referenceId: el.additionalProperties.referenceId,
                    orderNo: selectPageLength
                }).then(function (response) {
                    if (response[0].result == "OK") {
                    }
                });
                var selectedListItem = new FogPanel.WizardListItem({
                    heading: el.additionalProperties.name,
                    description: el.additionalProperties.description,
                    cpu: el.additionalProperties.cpuNumber,
                    ram: el.additionalProperties.memory,
                    onClick: function () {
                        var currentId = this.id;
                        var currentWidget = dijit.byId(currentId);
                        dojo.byId("selectWidgetId").value = currentId;
                        var listCollection = dojo.byId("selectedList");

                        var listWidgets = dijit.registry.findWidgets(listCollection);
                        dojo.forEach(listWidgets, function (el) {
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
                    onUpArrowClick: function () {
                        GroupingInfo.goUpWidget(this);
                    },
                    onDownArrowClick: function () {
                        GroupingInfo.goDownWidget(this);
                    },
                    onDeleteTagClick: function () {
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
            dojo.forEach(listWidgets, function (el) {
                var selectedListItem = new FogPanel.WizardListItem({
                    heading: el.additionalProperties.name,
                    description: el.additionalProperties.description,
                    cpu: el.additionalProperties.cpuNumber,
                    ram: el.additionalProperties.memory,
                    onClick: function () {
                        var currentId = this.id;
                        var currentWidget = dijit.byId(currentId);
                        dojo.byId("selectWidgetId").value = currentId;
                        var listCollection = dojo.byId("selectedList");

                        var listWidgets = dijit.registry.findWidgets(listCollection);
                        dojo.forEach(listWidgets, function (el) {
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
                    onUpArrowClick: function () {
                        GroupingInfo.goUpWidget(this);
                    },
                    onDownArrowClick: function () {
                        GroupingInfo.goDownWidget(this);
                    },
                    onDeleteTagClick: function () {
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
    returnCurrentWidget: function (currentWidget) {
        var listCollection = dojo.byId("listCollection");
        dojo.byId("currentWidgetId").value = currentWidget.id;
        var listWidgets = dijit.registry.findWidgets(listCollection);

        dojo.forEach(listWidgets, function (el) {
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
    showSlider: function () {
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
            onChange: function (value) {
                var selectedItemColl = dojo.byId("selectedList");
                var listWidgets = dijit.registry.findWidgets(selectedItemColl);
                dojo.forEach(listWidgets, function (listItem, index) {
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
    editPageButton: function () {
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
var DashboardTestForm = {
    init: function () {


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
        function (dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
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
            notificationTimer.onTick = function () {
                AdminUpdateTimer.sync();
            };
            notificationTimer.onStart = function () {


//        var ticketNotificationWidget = new Notification({
//            id: "ticketNotification"
//        }).placeAt("adminTicketNotification");
//        ticketNotificationWidget.startup();
//
//        ticketNotificationWidget.subscribe("/FogPanel/ticket/notifications");
//        ticketNotificationWidget.setLink("#/admin/support/tickets");

//        var cloudUsageNotificationWidget = new Notification({
//            id: "cloudUsageNotification"
//        }).placeAt("cloudUsageNotificationDiv");
//        cloudUsageNotificationWidget.startup();
//
//        cloudUsageNotificationWidget.subscribe("/FogPanel/cloudusage/notifications");
//        cloudUsageNotificationWidget.setLink("#/admin/activity/cloudUsage");

                var ticketCount = 0;
                var ticNotificationData = [];
                var ticketNotification = new JsonRest({
                    target: core.getContextPath() + "/api/support/ticket/notification"
                });

                ticketNotification.query().then(function (data) {
                    dojo.forEach(data, function (notificationDataItem) {
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

                usageNotification.query().then(function (data) {
                    dojo.forEach(data, function (usageNotificationDataItem) {
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
                "index": action(function () {
                    if (dijit.byId("configMsgId")) {
                        dijit.byId("configMsgId").destroyRecursive();
                    }
                    if (dijit.byId("pullPlanButton")) {
                        dijit.byId("pullPlanButton").destroyRecursive();
                    }
                    if (dijit.byId("pullImageButton")) {
                        dijit.byId("pullImageButton").destroyRecursive();
                    }
                    if (dijit.byId("pullVolumeTypeButton")) {
                        dijit.byId("pullVolumeTypeButton").destroyRecursive();
                    }
                    if (dijit.byId("pullPlanConform")) {
                        dijit.byId("pullPlanConform").destroyRecursive();
                    }
                    if (dijit.byId("pullImageConfirm")) {
                        dijit.byId("pullImageConfirm").destroyRecursive();
                    }
                    if (dijit.byId("pullVolumeTypeConfirm")) {
                        dijit.byId("pullVolumeTypeConfirm").destroyRecursive();
                    }
//            if (dijit.byId("importData")) {
//                dijit.byId("importData").destroyRecursive();
//            }
                    core.ui.loadTemplate("admin_index", core.ui.getContentId());
//                    AdminDashboard.LoginInit();
                    ProjectCloudEngineList.urlForming();
                    ProjectCloudEngineList.listUsers();
//            ConfigTest.init();
//            ConfigTest.configChangeCheck();
//            LicenseCheck.init();
//            AdminDashboard.init();
//            AdminDashboard.populateValues();
                }),
                "wizard": action(function () {
                    core.ui.loadTemplate("wizard_page", core.ui.getContentId());
                    DashboardWizard.init();
                    DashboardWizard.populateValues();

                }),
                "testWizardTempPage3": action(function () {
                    core.ui.loadTemplate("testWizardTempPage3", core.ui.getContentId());
                }),
                "form": action(function () {
                    core.ui.loadTemplate("formPage", core.ui.getContentId());
                    DashboardTestForm.init();
                }),
                "tempPage": action(function () {
                    var currentTempPage = dijit.byId("dashboardTempForm");
                    if (currentTempPage) {
                        dijit.byId("dashboardTempForm").destroyRecursive();
                    }


                    ConfigTest.init();
                    DashboardTemp.initZone();
                    DashboardTemp.init();
                    DashboardTemp.populateValues();
                }),
                "appTempPage": action(function () {
                    var currentTempPage = dijit.byId("dashboardAppTempForm");
                    if (currentTempPage) {
                        dijit.byId("dashboardAppTempForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("appTempPage", core.ui.getContentId());
                    ConfigTest.init();
                }),
                "userProfile": action(function () {
                    var currentUPage = dijit.byId("userProfileForm");
                    if (currentUPage) {
                        dijit.byId("userProfileForm").destroyRecursive();
                    }

                    core.ui.loadTemplate("userProfile", core.ui.getContentId());
                    ConfigTest.init();
                    DashboardUserProfile.init();
                    DashboardUserProfile.populateValues();
                }),
                "accountProfile": action(function () {
                    var currentAccountPage = dijit.byId("accountProfileForm");
                    if (currentAccountPage) {
                        dijit.byId("accountProfileForm").destroyRecursive();
                    }

                    core.ui.loadTemplate("accountProfile", core.ui.getContentId());
                    DashboardAccountProfile.init();
                    DashboardAccountProfile.populateValues();
                }),
                "securityGroups": action(function () {

                }),
                "accountList": action(function () {

                }),
                "invoice": action(function () {
                    core.ui.loadTemplate("invoice", core.ui.getContentId());
                    InvoiceList.init();
                    InvoiceList.populateValues();
                }),
                "loginAttemptSetup": action(function () {
                    var currentLoginSetupPage = dijit.byId("loginAttemptConfigForm");
                    if (currentLoginSetupPage) {
                        dijit.byId("loginAttemptConfigForm").destroyRecursive();
                    }

                    core.ui.loadTemplate("loginAttemptSetup", core.ui.getContentId());
                    LoginAttemptSetup.init();
                }),
                "grouping": action(function () {
                    var currentPage = dijit.byId("osTypeForm");
                    if (currentPage) {
                        dijit.byId("osTypeForm").destroyRecursive();
                    }

                    core.ui.loadTemplate("grouping", core.ui.getContentId());
                    GroupingInfo.init();
                    GroupingInfo.populateValues();

                }),
                "cloudConfig": action(function () {
                    var currentPage = dijit.byId("dashboardSystemConfigForm");
                    if (currentPage) {
                        dijit.byId("dashboardSystemConfigForm").destroyRecursive();
                        dijit.byId("dashboardConfigLoader").destroyRecursive();

                    }
                    core.ui.loadTemplate("cloudConfig", core.ui.getContentId());
                    DashboardConfig.init();
                    DashboardConfig.populateValues();
                }),
                "maintainance": action(function () {
                    var viewEventPage = dijit.byId("viewEventPage");
                    if (viewEventPage) {
                        dijit.byId("viewEventPage").destroyRecursive();
                    }
                    core.ui.loadTemplate("maintainance", core.ui.getContentId());
                    CalendarInfo.init();
                    CalendarInfo.populateValues();
                }),
                "cloudMaintenance": action(function () {
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


var ProjectCloudEngineList = {
    engineEncodedHrfUrl : null,
    urlForming : function() {
        var sessionRest = new JsonRest({
            target: core.getContextPath() + "/api/cloudEngine/getSessionInfo"
        });
        var currentUserName;
        var currentPassword;
        var openstackUrl;

        sessionRest.query({
        }).then(function (data) {
            dojo.forEach(data, function (resultData) {

                currentUserName = resultData.userName;
                currentPassword = resultData.password;
                openstackUrl = resultData.openstackUrl;
            });

            console.log("openstackUrl:" + openstackUrl)
            console.log("userName:" + currentUserName)
            ProjectCloudEngineList.engineEncodedHrfUrl = openstackUrl + "/login/ssoSuccess?userName=" + currentUserName + "&password=" + currentPassword;
        });
        
    },
    
    
    listUsers: function (id) {
        if (dijit.byId("projectCloudEngineDatas")) {
            dijit.byId("projectCloudEngineDatas").destroyRecursive();
        }
        var getUsersRestStore = new JsonRest({
            target: core.getContextPath() + "/api/project/assigned/list"
        });

        var projectCloudEngineData = {
            items: []
        };

        var projectCloudEngineDataList = new ItemFileWriteStore({data: projectCloudEngineData});
        var gridLayout = [
            [
                {'name': translator.user.userID, 'field': 'id', 'width': '10%', datatype: "string", 'hidden': true
                },
                {'name': translator.common.projectName, 'field': 'projectName', 'width': '10%', datatype: "string", autoComplete: true},
                {'name': "Cloud Engine", 'field': 'engineType', 'width': '10%', datatype: "string", autoComplete: true},
                {'name': translator.common.url, 'field': 'engineUrl', 'width': '10%', datatype: "string", autoComplete: true, 'formatter': function (engineUrl) {
                        var node = "<a href='" + ProjectCloudEngineList.engineEncodedHrfUrl + "'>" + engineUrl + "</a>";
                        return node;
                    }},
//                {'name': translator.common.email, 'field': 'projectName','width':'10%',datatype:"string",  autoComplete: true},

//                {'name': translator.common.billing.paid, 'field': 'paid', 'width': '100px', datatype:"string",  autoComplete: true, 'formatter': function(data) {                                                                      
//                        return data;
//                    }
//                },     
//                {'name': translator.common.usersCount, 'fields': ['userCount','id'], 'width': '100px', datatype:"string",  autoComplete: true, 'formatter': function(data) {                                                                      
//                        var node = "<a href='#/admin/account/users/" + data[1] + "'>" + data[0] + "</a>";
//                        return node;
//                    }
//                },     
//                {'name': translator.user.grid.instance.layout.status, 'field': 'status', 'width': '100px',  datatype:"string",
//                    // Declare that we need the ComboBox for suggestions
//                    autoComplete: true,
//                    // Configure the ComboBox, so that it does not auto-complete our input
//                    dataTypeArgs: {
//                        autoComplete: false
//                    }, 'formatter' : function(status) {
//                        var currentStatus = "";
//                        if(status === true) {
//                            currentStatus = "<div class='client-stat-active overflowLabel'>"+translator.common.account.status.ACTIVE+"</div>";
//                        } else {
//                            currentStatus = "<div class='client-stat-canceled overflowLabel'>"+translator.common.account.status.DISABLED+"</div>";
//                        }
//                        return currentStatus;
//                    }
//                }, 
            ]
        ];

        getUsersRestStore.query({id: id}).then(function (data) {
            console.log(data)
            if (data.length === 0) {
                dojo.byId("projectCloudListGrid").innerHTML = ""
                dojo.byId("noProjectList").style.display = "block"
            } else {
                dojo.byId("noProjectList").style.display = "none"

                dojo.forEach(data, function (project) {
                    projectCloudEngineDataList.newItem({
                        id: project.id,
                        project: project,
                        engineType: project.engineType,
                        engineUrl: project.engineUrl,
                        engineId: project.engineId,
                        projectName: project.projectName,
                    });
                });
                dojo.byId("projectCloudListGrid").innerHTML = "";
                var projectCloudEngineDataGrid = new EnhancedGrid({
                    id: 'projectCloudEngineDatas',
                    "class": "span12",
                    store: projectCloudEngineDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                projectCloudEngineDataGrid.placeAt("projectCloudListGrid");
                projectCloudEngineDataGrid.startup();
            }
        })


    }
}


window.ConfigTest = ConfigTest;
//window.DashboardTemp = DashboardTemp;   
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
window.ProjectCloudEngineList = ProjectCloudEngineList;
