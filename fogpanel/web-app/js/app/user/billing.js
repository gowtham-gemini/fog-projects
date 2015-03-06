"use strict";
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
    "dojo/query",
    "dojo/dom-class",
    "dijit/form/Button",
    "dojo/number",
    "dojo/currency",
    "dijit/layout/TabContainer",
    "dojox/grid/enhanced/plugins/Filter",
    "dijit/layout/ContentPane",
    "dojox/form/PasswordValidator",
    "FogPanel/StorageAction",
    "dojo/on",
    "dojox/validate/regexp",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
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
    "List/ListItem"],
        function(dojo, i18n, translator, dijit, JsonRest, registry, FilteringSelect, Select, ItemFileWriteStore, DataGrid, EnhancedGrid, HorizontalSlider, domConstruct, 
        query, domClass, Button, LocaleNumber, localeCurrency) {
            window.translator = translator;
            window.JsonRest = JsonRest;
            window.query = query;
            window.domClass = domClass;
            window.registry = registry;
            window.FilteringSelect = FilteringSelect;
            window.ItemFileWriteStore = ItemFileWriteStore;
            window.Select = Select;
            window.DataGrid = DataGrid;
            window.domConstruct = domConstruct;
            window.HorizontalSlider = HorizontalSlider;
            window.Button = Button;
            window.EnhancedGrid = EnhancedGrid;
            window.LocaleNumber = LocaleNumber;
            window.localeCurrency = localeCurrency;
            controller({
                name: "billing",
                module: "user",
                filePath: "/js/app/user/billing.js",
                layout: {
                    name: "",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "index": action(function() {
                    core.ui.loadTemplate("billingMenuInfo", core.ui.getContentId());
                }),
                "invoiceSummary": action(function() {
//            var currentPage = dijit.byId("invoiceSummaryForm");
//            if(currentPage) {
//                dijit.byId("invoiceSummaryForm").destroyRecursive();
//            }
                    core.ui.loadTemplate("invoiceSummary", core.ui.getContentId());
                    InvoiceSummary.init();
                    InvoiceSummary.populateValues();
                }),
                "currentUsage": action(function() {
                    core.ui.loadTemplate("currentUsage", core.ui.getContentId());
                    CurrentUsage.init();
                    CurrentUsage.populateValues();
                }),
                "payment": action(function() {
                    var currentPage = dijit.byId("paymentDialog");
                    if (currentPage) {
                        dijit.byId("paymentDialog").destroyRecursive();
                        dijit.byId("paymentLoader").destroyRecursive();
                    }
                    if (dijit.byId("manualPaymentForm")) {
                        dijit.byId("manualPaymentForm").destroyRecursive();
                    }

                    core.ui.loadTemplate("manualPayment", core.ui.getContentId());
                    var configRestStore = new JsonRest({
                        target: core.getContextPath() + "/api/config/"
                    });

                    configRestStore.query().then(function(resultData) {
                        dojo.forEach(resultData, function(config) {
                            if (config.name == "creditcard.processing") {
                                if (config.value == "true" || config.value == true) {
                                    ManualPayment.init();
                                    ManualPayment.populateValues();
                                    dojo.byId("paymentButtonDiv").style.display = "block";
                                } else {
                                    dojo.byId("paymentButtonDiv").style.display = "none";
                                    ManualPayment.init();
                                    ManualPayment.populateValues();
                                }
                            }
                        });
                    });

                }),
                "creditCard": action(function() {
                    var currentPage = dijit.byId("creditCardForm");
                    if (currentPage) {
                        dijit.byId("creditCardForm").destroyRecursive();
                        dijit.byId("cardVerifyLoader").destroyRecursive();
                    }
                    core.ui.loadTemplate("creditCard", core.ui.getContentId());


                    var configRestStore = new JsonRest({
                        target: core.getContextPath() + "/api/config/"
                    });

                    configRestStore.query().then(function(resultData) {
                        dojo.forEach(resultData, function(config) {
                            if (config.name == "creditcard.processing") {
                                if (config.value == "true" || config.value == true) {
                                    SaveCreditCard.init();
                                    SaveCreditCard.populateValues();
                                } else {
                                    registry.byId('userToaster').setContent('Credit Card Processing Disabled!', 'error');
                                    registry.byId('userToaster').show();
                                    core.router.go("#/user/billing/payment");
                                }
                            }
                        });
                    });
                }),
                "recurringItem": action(function() {
                    core.ui.loadTemplate("userRecurringItem", core.ui.getContentId());
                    RecurringItem.init();
                    RecurringItem.populateValues();

                }),
                "customItem": action(function() {
                    core.ui.loadTemplate("userCustomItem", core.ui.getContentId());
                    CustomItemInfo.init();
                    CustomItemInfo.populateValues();

                }),
                "makePayment": action(function() {

                    if (dijit.byId("ccAvenuPaymentForm")) {
                        dijit.byId("ccAvenuPaymentForm").destroyRecursive();
                    }

                    if (dijit.byId("PayPal")) {
                        dijit.byId("PayPal").destroyRecursive();
                    }
                    if (dijit.byId("Merchant_Id")) {
                        dijit.byId("Merchant_Id").destroyRecursive();
                    }
                    if (dijit.byId("CCAvenue")) {
                        dijit.byId("CCAvenue").destroyRecursive();
                    }

                    if (dijit.byId("manualPaymentForm")) {
                        dijit.byId("manualPaymentForm").destroyRecursive();
                    }
                    if (dijit.byId("paymentLoader")) {
                        dijit.byId("paymentLoader").destroyRecursive();
                    }

                    core.ui.loadTemplate("makePayment", core.ui.getContentId());
                    Gateway.init();
                })
            });
        });

var CustomItemInfo = {
    _customRestStore: "",
    init: function() {
        this._customRestStore = new JsonRest({
            target: core.getContextPath() + "/api/recurringItem/user/count"
        });
    },
    populateValues: function() {
        if (dijit.byId("userCustomItemList")) {
            dijit.byId("userCustomItemList").destroyRecursive();
        }
        dojo.byId("userCustomItemListGrid").innerHTML = "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/> </br> <p>Custom Item Loading</p>";
        var customData = {
            items: []
        };
        var customDataList = new ItemFileWriteStore({data: customData});
        var customDataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', datatype: "string", autoComplete: true, 'formatter': function(data) {

                        return  "<span class='bold'>" + data + "</span>";
                    }},
                {'name': translator.common.account.name, 'field': 'account', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.amount + " (" + core.getCurrency() +")", 'field': 'amount', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.taxAmount + " (" + core.getCurrency() +")", 'field': 'taxAmount', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.totalAmount + " (" + core.getCurrency() +")", 'field': 'totalAmount', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data) {

                        return  "<span class='orangeColor'>" + data + "</span>";
                    }
                }
            ]
        ];
        var customRestStore = new JsonRest({
            target: core.getContextPath() + "/api/recurringItem/user/count"
        });
        customRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.customItemList.length == 0 || resultData.customItemList == undefined || resultData.customItemList == "undefined" || resultData.customItemList == "" || resultData.customItemList == " ") {
                    dojo.byId("noCustomMessageBox").style.display = "block";
                    dojo.byId("recAmt").innerHTML = localeCurrency.format(0);
                    dojo.byId("cusAmt").innerHTML = 0;
                    dojo.byId("recDate").innerHTML = "-";
                    dojo.byId("cusDate").innerHTML = "-";
                    dojo.byId("userCustomItemListGrid").innerHTML = "";
                } else {
                    dojo.byId("noCustomMessageBox").style.display = "none";
                    dojo.forEach(resultData.customItemList, function(cusData) {

                        customDataList.newItem({
                            id: cusData.id,
                            taxAmount: localeCurrency.format(cusData.taxAmount),
                            totalAmount: localeCurrency.format(Math.round(cusData.totalAmount * 100) / 100),
                            amount: localeCurrency.format(cusData.amount),
                            account: cusData.account,
                            name: cusData.name
                        });

                    });
                    dojo.byId("userCustomItemListGrid").innerHTML = "";
                    var dataGrid = new EnhancedGrid({
                        id: 'userCustomItemList',
                        "class" :"span12",
                        store: customDataList,
                        structure: customDataLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    dataGrid.placeAt("userCustomItemListGrid");
                    dataGrid.startup();
                }
                dojo.forEach(data, function(countData) {
                    dojo.byId("recAmt").innerHTML = localeCurrency.format(countData.recurringItemCost.toFixed(2));
                    dojo.byId("recCurrency").innerHTML = countData.currency;

                    dojo.byId("cusAmt").innerHTML = localeCurrency.format(countData.customItemCost.toFixed(2));
                    dojo.byId("cusCurrency").innerHTML = countData.currency;

                    dojo.byId("recDate").innerHTML = countData.currMonth;
                    dojo.byId("cusDate").innerHTML = countData.currMonth;
                });
            });
        });
    }
};


var RecurringItem = {
    _recurringItemStore: "",
    init: function() {

        this._recurringItemStore = new JsonRest({
            target: core.getContextPath() + "/api/recurringItem/user/count"
        });
    },
    populateValues: function() {

        if (dijit.byId("userRecurrinItemGridWidget")) {
            dijit.byId("userRecurrinItemGridWidget").destroyRecursive();
        }
        var gridData = {
            items: []
        };
        dojo.byId("userRecurringItemListGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Recuring Item Loading</p>";
        var gridDataList = new ItemFileWriteStore({data: gridData});

        var gridLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'field': 'actName', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '100px', datatype: "string", autoComplete: true, 'formatter': function(data) {

                        return  "<span class='bold'>" + data + "</span>";
                    }},
                {'name': translator.common.account.name, 'field': 'account', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.amount + " (" + core.getCurrency() +")", 'field': 'amount', 'width': '150px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.taxPercent + "(%)", 'field': 'taxPercent', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.billingCycle, 'field': 'cycle', 'width': '100px', datatype: "string", autoComplete: true, 'formatter': function(data) {

                        return  "<span class='bold'>" + data + "</span>";
                    }},
                {'name': translator.common.billing.billingCycleUsed, 'field': 'uses', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.taxAmount + " (" + core.getCurrency() +")", 'field': 'taxAmount', 'width': '150px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.totalAmount + " (" + core.getCurrency() +")", 'field': 'totalAmount', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data) {

                        return  "<span class='orangeColor'>" + data + "</span>";
                    }
                }
            ]];
        this._recurringItemStore.query().then(function(data) {
            dojo.forEach(data, function(recurringItem) {
                if (recurringItem.recurringItemList.length == 0 || recurringItem.recurringItemList == undefined || recurringItem.recurringItemList == "undefined" || recurringItem.recurringItemList == "" || recurringItem.recurringItemList == " ") {
                    dojo.byId("noRecuringMessageBox").style.display = "block";
                    dojo.byId("recAmt").innerHTML = 0;
                    dojo.byId("cusAmt").innerHTML = 0;
                    dojo.byId("recDate").innerHTML = "-";
                    dojo.byId("cusDate").innerHTML = "-";
                    dojo.byId("userRecurringItemListGrid").innerHTML = "";
                } else {
                    dojo.byId("noRecuringMessageBox").style.display = "none";


                    dojo.forEach(recurringItem.recurringItemList, function(recurringItemData) {
                        gridDataList.newItem({
                            id: recurringItemData.id,
                            name: recurringItemData.name,
                            account: recurringItemData.account,
                            amount: localeCurrency.format(recurringItemData.amount),
                            taxPercent: localeCurrency.format(recurringItemData.taxPercent),
                            cycle: recurringItemData.cycle,
                            uses: recurringItemData.uses,
                            taxAmount: localeCurrency.format(recurringItemData.taxAmount),
                            totalAmount: localeCurrency.format(recurringItemData.totalAmount),
                            actName: recurringItemData.name
                        });
                    });

                    dojo.byId("userRecurringItemListGrid").innerHTML = "";
                    var recurringItemGrid = new EnhancedGrid({
                        id: 'userRecurrinItemGridWidget',
                        "class" : "span12",
                        store: gridDataList,
                        structure: gridLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    recurringItemGrid.placeAt("userRecurringItemListGrid");
                    recurringItemGrid.startup();
                }
            });

            dojo.forEach(data, function(countData) {
                dojo.byId("recAmt").innerHTML = localeCurrency.format(countData.recurringItemCost.toFixed(2));
                dojo.byId("reccurrency").innerHTML = countData.currency;

                dojo.byId("cusAmt").innerHTML = localeCurrency.format(countData.customItemCost.toFixed(2));
                dojo.byId("cusCurrency").innerHTML = countData.currency;

                dojo.byId("recDate").innerHTML = countData.currMonth;
                dojo.byId("cusDate").innerHTML = countData.currMonth;
            });
        });
    }
};


var SaveCreditCard = {
    _paymentStore: "",
    init: function() {

        this._paymentStore = new JsonRest({
            target: core.getContextPath() + "/api/payment/saveCard"
        });
    },
    populateValues: function() {
        if (dijit.byId("expiryYear")) {
            dijit.byId("expiryYear").destroyRecursive();
        }

        var yearOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var yearList = new ItemFileWriteStore({data: yearOptions});

        var currentTime = new Date();
        var j = currentTime.getFullYear() + 20;
        for (var i = currentTime.getFullYear(); i <= j; i++) {
            yearList.newItem({id: i, name: i});
        }
        var yearListWidget = new Select({
            id: "newExpiryYear",
            name: "newExpiryYear",
            store: yearList,
            maxHeight: 100
        });
        yearListWidget.placeAt("newYearList");
        yearListWidget.startup();
    },
    add: function() {

        var pageNode = dojo.byId("cardDetails");
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

            dijit.byId("cardVerifyLoader").show();

            var cardType = dijit.byId("newCardType");
            var cardNumber = dijit.byId("newCardNumber");
            var expiryYear = dijit.byId("newExpiryYear");
            var expiryMonth = dijit.byId("newExpiryMonth");
            var cvvNumber = dijit.byId("newCvvNumber");

            this._paymentStore.add({
                cardNumber: cardNumber.value,
                expiryMonth: expiryMonth.value,
                expiryYear: expiryYear.value,
                cvv: cvvNumber.value,
                cardType: cardType.value

            }).then(function(resultData) {
                if (resultData == "OK") {
                    registry.byId('userToaster').setContent(translator.common.billing.cardAdded, 'message');
                    registry.byId('userToaster').show();
                    dijit.byId("creditCardForm").reset();
                    dojo.byId("errorInfo").style.display = "none";
                    dojo.byId("createVmInfo").style.display = "block";
                    dijit.byId("cardVerifyLoader").hide();
                } else {
                    registry.byId('userToaster').setContent(translator.common.message.failed, 'error');
                    registry.byId('userToaster').show();
                    dojo.byId("errorInfo").style.display = "block";
                    dojo.byId("createVmInfo").style.display = "none";
                    dijit.byId("cardVerifyLoader").hide();
                }
            });

        }
    },
    cancel: function() {
        dijit.byId("creditCardForm").reset();
        core.router.go("#/user/billing/payment");
    }

};

var InvoiceSummary = {
    _invoiceStore: "",
    _configRestStore: "",
    init: function() {

        this._invoiceStore = new JsonRest({
            target: core.getContextPath() + "/api/invoice/"
        });

        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });
        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("invoiceCurrency").innerHTML = cur.currency;
            });
        });

    },
    populateValues: function() {
//        var pdfLink = "<a href='"+core.getBaseURL()+"/pdf/pdfLink?pdfController=pdf&pdfAction=invoiceSummary&invoiceId="+data+"&filename=invoice-"+data+"'>pdf</a>"
        if (dijit.byId("invoiceGridWidget")) {
            dijit.byId("invoiceGridWidget").destroyRecursive();
        }
        dojo.byId("invoceListGrid").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.billing.invoiceLoader + "</p>";
        var gridData = {
            items: []
        };

        var gridDataList = new ItemFileWriteStore({data: gridData});


        var gridLayout = [
            [
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.billing.grid.layout.invoiceNo, 'field': 'invoiceNo', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.previousBalance + " (" + core.getCurrency() +")", 'field': 'previousBalance', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.payments + " (" + core.getCurrency() +")", 'field': 'payments', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.currentUsage + " (" + core.getCurrency() +")", 'field': 'currentDue', 'width': '100px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.common.billing.grid.layout.totalPayable + " (" + core.getCurrency() +")", 'field': 'totalAmount', 'width': '100px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='orangeColor'>" + data + "</span>";
                    }
                },
                {'name': translator.common.billing.grid.layout.previousInvoiceDate, 'field': 'previousInvoiceDate', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.invoiceDate, 'field': 'invoiceDate', 'width': '200px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter': function(data) {
                        var pdfLink = "<a href='" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=invoiceSummary&invoiceId=" + data + "&filename=invoice-" + data + "'><i class='pdf_icon'></i></div></a>";
                        return pdfLink;
                    }
                }
            ]
        ];

        this._invoiceStore.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noInvoiceMessageBox").style.display = "block";
                dojo.byId("invoceListGrid").innerHTML = "";
            } else {
                dojo.byId("noInvoiceMessageBox").style.display = "none";

                dojo.forEach(data, function(invoiceData) {
                    gridDataList.newItem({
                        id: invoiceData.id, invoiceNo: "0000" + invoiceData.id,
                        previousBalance: LocaleNumber.format(invoiceData.previousBalance), payments: LocaleNumber.format(invoiceData.payments),
                        totalAmount: LocaleNumber.format(Math.round(invoiceData.totalAmount * 100) / 100), previousInvoiceDate: invoiceData.previousInvoiceDate,
                        invoiceDate: invoiceData.invoiceDate, action: invoiceData.id, currentDue: localeCurrency.format(invoiceData.currentDue)
                    });
                });
                dojo.byId("invoceListGrid").innerHTML = "";
                var invoiceGrid = new EnhancedGrid({
                    id: 'invoiceGridWidget',
                    "class":"span12",
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                invoiceGrid.placeAt("invoceListGrid");
                invoiceGrid.startup();
            }
        });
    }
};

var CurrentUsage = {
    _invoiceStore: "",
    _configRestStore: "",
    _accountStore: "",
    _accountListStore: "",
    init: function() {

        this._invoiceStore = new JsonRest({
            target: core.getContextPath() + "/api/invoice/"
        });

        this._accountStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount"
        });

        this._accountListStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });
    },
    populateValues: function() {
        var invoiceId;
        this._accountStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                
                if (resultData.invoice === "no") {
                    dojo.byId("noCurrentUsage").style.display = "block";
                    dojo.byId("currentPdf").style.display = "none";
                } else {
                    invoiceId = resultData.invoiceId;
                    dojo.byId("noCurrentUsage").style.display = "none";
                    document.getElementById("currentInvoice").src = "pdf/currentUsage?invoiceId=" + resultData.invoiceId;
                    document.getElementById("currentPdf").setAttribute("href", "" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=invoiceSummary&invoiceId=" + invoiceId + "&filename=invoice-" + invoiceId);
                }
            });
        });
    }
};

var ManualPayment = {
    _paymentStore: "",
    _configRestStore: "",
    _accountStore: "",
    init: function() {
        this._paymentStore = new JsonRest({
            target: core.getContextPath() + "/api/payment/"
        });

        this._accountStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount"
        });

        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
        
        var gatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/paymentGateways/"
        });
       
        gatewayRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(pay) {
               if(pay.gatewayName === "PayPal") {
                   dojo.byId("addCardButton").style.display = "block";
               }
            });
        });
        
        var accountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount/"
        });
        accountRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.invoice == "yes") {
                } else {
                    if (resultData.maxCreditLimit == 0) {
                    } else {
                        registry.byId('userToaster').setContent(translator.common.billing.createVMToPayment, 'error');
                        registry.byId('userToaster').show();
                        core.router.go("#/user/cloud/userInstancePage");
                    }
                }
            });
        });

        var accountVerifyRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/cardVerified/"
        });
        var useDefaultCard = dijit.byId("useDefaultCard");
        accountVerifyRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.cardVerified == "OK" && resultData.defaultCardEnabled == "true") {
                    dojo.byId("defaultCardDetails").style.display = "block";
                    dojo.byId("cardDetails").style.display = "none";
                } else if (resultData.cardVerified == "OK" && resultData.defaultCardEnabled == "false") {
                    useDefaultCard.setAttribute('checked', false);
                    dojo.byId("defaultCardDetails").style.display = "none";
                    dojo.byId("cardDetails").style.display = "block";
                } else {
                    useDefaultCard.setAttribute('checked', false);
                    dojo.byId("defaultCardDetails").style.display = "none";
                    dojo.byId("cardDetails").style.display = "block";
                }
            });
        });
    },
    updatePaymentGrid: function() {
        if (dijit.byId("paymentGridWidget")) {
            dijit.byId("paymentGridWidget").destroyRecursive();
        }
        dojo.byId("paymentListGrid").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.billing.paymentLoader + "</p>";

        var paymentGridData = {
            items: []
        };

        var paymentGridDataList = new ItemFileWriteStore({data: paymentGridData});
        var gridLayout = [
            [
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.billing.grid.layout.tokenNo, 'field': 'tokenNo', 'width': '300px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.common.gateway, 'field': 'gateway', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.amount + " (" + core.getCurrency() +")", 'field': 'amount', 'width': '150px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.processingFee + " (" + core.getCurrency() +")", 'field': 'processingFee', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.grid.layout.totalAmount + " (" + core.getCurrency() +")", 'field': 'totalAmount', 'width': '200px', datatype: "string", autoComplete: true, 'formatter': function(data) {
                        return  "<span class='orangeColor'>" + data + "</span>";
                    }
                },
                {'name': translator.common.billing.grid.layout.paymentDate, 'field': 'paymentDate', 'width': '100px', datatype: "string", autoComplete: true},
                {'name': translator.common.status.name, 'field': 'status', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data){
                       if(data === "SUCCESS") {
                           return  "<span class='bold' style='color: #36bd55;'>" + data + "</span>";
                       } else if(data === "Pending") {
                           return  "<span class='bold' style='color: #49bcd0;'>" + data + "</span>";
                       } else if(data === "FAILED") {
                           return  "<span class='bold' style='color: #f2443b;'>" + data + "</span>";
                       } else {
                           return data;
                       }
                        
                }}
                
            ]
        ];

        this._paymentStore.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noPaymentMessageBox").style.display = "block";
                dojo.byId("paymentListGrid").innerHTML = "";
            } else {
                dojo.byId("noPaymentMessageBox").style.display = "none";
                dojo.forEach(data, function(paymentData) {
                    paymentGridDataList.newItem({
                        id: paymentData.id,
                        tokenNo: paymentData.tokenNo,
                        paymentDate: paymentData.date,
                        totalAmount: localeCurrency.format(paymentData.totalAmount),
                        processingFee: localeCurrency.format(paymentData.processingFee),
                        amount: localeCurrency.format(paymentData.amount),
                        status: paymentData.status,
                        gateway: paymentData.gateway
                    });
                });
                dojo.byId("paymentListGrid").innerHTML = "";
                var paymentGrid = new EnhancedGrid({
                    id: 'paymentGridWidget',
                    "class" : "span12",
                    store: paymentGridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                paymentGrid.placeAt("paymentListGrid");
                paymentGrid.startup();
            }
        });
    },
    populateValues: function() {
        ManualPayment.updatePaymentGrid();

//        if (dijit.byId("expiryYear")) {
//            dijit.byId("expiryYear").destroyRecursive();
//        }
//
//        var yearOptions = {
//            identifier: 'id',
//            label: 'name',
//            items: []
//        };
//
//        var yearList = new ItemFileWriteStore({data: yearOptions});
//
//        var currentTime = new Date();
//        var j = currentTime.getFullYear() + 20;
//        for (var i = currentTime.getFullYear(); i <= j; i++) {
//            yearList.newItem({id: i, name: i});
//        }
//        var yearListWidget = new Select({
//            id: "expiryYear",
//            name: "expiryYear",
//            store: yearList,
//            maxHeight: 100
//        });
//        yearListWidget.placeAt("yearList");
//        yearListWidget.startup();

        this._accountStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
//                dojo.byId("paymentAccountName").innerHTML= resultData.userName;

                if(resultData.totalAmount <= 0) {
                    dojo.byId("currentDue").innerHTML=  localeCurrency.format(0);
                    dojo.byId("creditBalance").style.display = "block";
                    var creditBalanceAmount = -1 * resultData.totalAmount;
                    dojo.byId("creditBalanceAmount").innerHTML=  localeCurrency.format(creditBalanceAmount);
                                       
                } else {
                    dojo.byId("currentDue").innerHTML=  localeCurrency.format(resultData.totalAmount);  
                    dojo.byId("creditBalanceAmount").innerHTML=  0;
                    dojo.byId("creditBalance").style.display = "none";
                }
                
                dojo.byId("dueCurrency").innerHTML= resultData.currency ;
                
                dojo.byId("credit").innerHTML= localeCurrency.format(resultData.credit.toFixed(2));
                dojo.byId("creditCurrency").innerHTML= resultData.currency;
                
                dojo.byId("Payments").innerHTML=  localeCurrency.format(resultData.payments.toFixed(2));
                dojo.byId("paymentsCurrency").innerHTML= resultData.currency;
                
                dojo.byId("PaymentPeriod").innerHTML= resultData.paymentPeriod;
            });
        });
    },
    add: function() {
        var cardType = dijit.byId("cardType");
        var paymentAmount = dijit.byId("paymentAmount");
        var useDefaultCard = dijit.byId("useDefaultCard");
        var cardNumber = dijit.byId("cardNumber");
        var expiryYear = dijit.byId("expiryYear");
        var expiryMonth = dijit.byId("expiryMonth");
        var cvvNumber = dijit.byId("cvvNumber");

        var pageNode1 = dojo.byId("payAmountDiv");
        var pageNode2 = dojo.byId("manualPaymentPage");

        var widgets;

        if (useDefaultCard.checked == true || useDefaultCard.checked == "true") {
            widgets = dijit.registry.findWidgets(pageNode1);
        } else {
            widgets = dijit.registry.findWidgets(pageNode2);
        }

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
            dijit.byId("paymentLoader").show();
            
            var addPaymentStore = new JsonRest({
                target: core.getContextPath() + "/api/payment/"
            });
            
            addPaymentStore.add({
                amount: paymentAmount.value,
                useDefaultCard: useDefaultCard.checked,
                cardNumber: cardNumber.value,
                expiryMonth: expiryMonth.value,
                expiryYear: expiryYear.value,
                cvv: cvvNumber.value,
                cardType: cardType.value

            }).then(function(data) {
                
                dojo.forEach(data, function(resultData) {
                    if (resultData === "OK") {
                        dijit.byId("paymentLoader").hide();
                        registry.byId('userToaster').setContent(translator.common.billing.paymentSuccess, 'message');
                        registry.byId('userToaster').show();
                        dijit.byId("manualPaymentForm").reset();
                        dijit.byId("paymentDialog").hide();
                        core.router.go("#/user/billing/payment");
    //                    ManualPayment.updatePaymentGrid();
                        //                ManualPayment.populateValues();
                        //                document.location.reload();
                    } else {
                        dijit.byId("paymentLoader").hide();
                        registry.byId('userToaster').setContent(translator.common.billing.paymentFailed, 'error');
                        registry.byId('userToaster').show();
                    }
                });
            });
        }

    },
    cancel: function() {
//        dijit.byId("manualPaymentForm").reset(); 
//        
        core.router.go("#/user/billing/payment");

    },
    showPaymentForm: function() {
        dijit.byId("manualPaymentForm").reset();

        var accountVerifyRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/cardVerified/"
        });
        var useDefaultCard = dijit.byId("useDefaultCard");
        accountVerifyRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.cardVerified == "OK" && resultData.defaultCardEnabled == "true") {
                    dojo.byId("defaultCardDetails").style.display = "block";
                    dojo.byId("cardDetails").style.display = "none";
                } else if (resultData.cardVerified == "OK" && resultData.defaultCardEnabled == "false") {
                    useDefaultCard.setAttribute('checked', false);
                    dojo.byId("defaultCardDetails").style.display = "none";
                    dojo.byId("cardDetails").style.display = "block";
                } else {
                    useDefaultCard.setAttribute('checked', false);
                    dojo.byId("defaultCardDetails").style.display = "none";
                    dojo.byId("cardDetails").style.display = "block";
                }
            });
            dijit.byId("paymentDialog").show();
        });
    },
    enableCard: function(currentElement) {

        if (currentElement.checked == "true" || currentElement.checked == true) {
            dojo.byId("cardDetails").style.display = "none";
        } else {
            dojo.byId("cardDetails").style.display = "block";
        }

    }
};

var Gateway = {
    init: function() {
        
        
        if (dijit.byId("expiryYear")) {
            dijit.byId("expiryYear").destroyRecursive();
        }

        var yearOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var yearList = new ItemFileWriteStore({data: yearOptions});

        var currentTime = new Date();
        var j = currentTime.getFullYear() + 20;
        for (var i = currentTime.getFullYear(); i <= j; i++) {
            yearList.newItem({id: i, name: i});
        }
        var yearListWidget = new Select({
            id: "expiryYear",
            name: "expiryYear",
            store: yearList,
            maxHeight: 100
        });
        yearListWidget.placeAt("yearList");
        yearListWidget.startup()
        
        dojo.byId("paypalDiv").style.display = "none";
        dojo.byId("ccAvenueDiv").style.display = "none";

        var bankWidget = dijit.byId("bankPaymentWidget");
        if (bankWidget) {
            dijit.byId("bankPaymentWidget").destroyRecursive();
        }
        var noBankOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var noBankOptionsList = new ItemFileWriteStore({data: noBankOptions});
        //noBankOptionsList.newItem({id: "option", name: translator.ccAvenue.none});
        var bankWidget = new Select({
            id: "bankPaymentWidget",
            name: "bankOption",
            sortByLabel: false,
            store: noBankOptionsList,
            value: "option"
        }).placeAt("bankDataList");
        var gatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/paymentGateways/"
        });
        var radioButtonPane = new dijit.layout.ContentPane({
            splitter: true,
            region: "top",
            style: "background-color: white;height: 40px",
            content: "RadioButtons"
        });

        var myString = "";
         dojo.byId("paymentGatewaysList").innerHTML = "";
        gatewayRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                myString += "<input type='radio' data-dojo-type='dijit.form.RadioButton' name='status' value='" + config.gatewayName + "' id='" + config.gatewayName + "' onclick='Gateway.loadGateway();'/>" + "<label for='" + config.gatewayName + "'>" + config.gatewayName + "</label>"
            });
            var radioOne = dojo.create("div", {
                innerHTML: myString
            });
            radioButtonPane.set('content', radioOne);
            radioButtonPane.placeAt("paymentGatewaysList");
        });

    },
    loadGateway: function() {
        var formElements = dojo.query("#paymentGateways input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var paymentGWPaypal = dijit.byId(checkedRadioId).value;
        if (paymentGWPaypal === "PayPal") {
            dojo.byId("paypalDiv").style.display = "block";
            dojo.byId("ccAvenueDiv").style.display = "none";
        } else if (paymentGWPaypal === "CCAvenue") {
            dojo.byId("ccAvenueDiv").style.display = "block";
            dojo.byId("paypalDiv").style.display = "none";

        }
    }
};

var CCAvenueGateway = {
    _cardOption: "",
    loadBankInfo: function() {

        var bankWidget = dijit.byId("bankPaymentWidget");
        if (bankWidget) {
            dijit.byId("bankPaymentWidget").destroyRecursive();
        }
        //var cardOptionSelected = dojo.byId
        var bankOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var bankList = new ItemFileWriteStore({data: bankOptions});

        var formElements = dojo.query("#ccAvenuePaymentOption input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var cardOptionSelected = dijit.byId(checkedRadioId).value;
        this._cardOption = cardOptionSelected;

        if (cardOptionSelected == "NonMoto") {
            bankList.newItem({id: "ANDDB_N", name: translator.ccAvenue.andhraBankDebit});
            bankList.newItem({id: "CANVIS_N", name: translator.ccAvenue.canaraBankDebit});
            bankList.newItem({id: "CBIDEB_N", name: translator.ccAvenue.cityBankDebit});
            bankList.newItem({id: "IOBDB_N", name: translator.ccAvenue.iobDebit});
            bankList.newItem({id: "PNBVIS_N", name: translator.ccAvenue.pnbDebitVisa});
            bankList.newItem({id: "PNBM_N", name: translator.ccAvenue.pnbDebitMaster});
            bankList.newItem({id: "UNIDB_N", name: translator.ccAvenue.ubiDebit});
            bankList.newItem({id: "KMBR_Y", name: translator.ccAvenue.rupayDebit});
            bankList.newItem({id: "SBMDB_Y", name: translator.ccAvenue.sbiDebit});
        } else if (cardOptionSelected == "netBanking") {
            bankList.newItem({id: "AND_N", name: translator.ccAvenue.andhraNB});
            bankList.newItem({id: "UTI_N", name: translator.ccAvenue.axisNB});
            bankList.newItem({id: "BBK_N", name: translator.ccAvenue.bobahrainNB});
            bankList.newItem({id: "BOBCO_N", name: translator.ccAvenue.bobCorpNB});
            bankList.newItem({id: "BOB_N", name: translator.ccAvenue.bobRetailAcNB});
            bankList.newItem({id: "BOI_N", name: translator.ccAvenue.boiNB});
            bankList.newItem({id: "BOM_N", name: translator.ccAvenue.bomNB});
            bankList.newItem({id: "CAN_N", name: translator.ccAvenue.canNB});
            bankList.newItem({id: "CSB_N", name: translator.ccAvenue.catSyrNB});
            bankList.newItem({id: "CEN_N", name: translator.ccAvenue.cenBINB});
            bankList.newItem({id: "CBIBAN_N", name: translator.ccAvenue.cityBankNB});
            bankList.newItem({id: "CITIUB_N", name: translator.ccAvenue.cityUnionNB});
            bankList.newItem({id: "DBS_N", name: translator.ccAvenue.dbsBankNB});
            bankList.newItem({id: "DCB_N", name: translator.ccAvenue.dcbNB});
            bankList.newItem({id: "DEUNB_N", name: translator.ccAvenue.deutscheNB});
            bankList.newItem({id: "DLB_N", name: translator.ccAvenue.dhanaNB});
            bankList.newItem({id: "FDEB_N", name: translator.ccAvenue.federalNB});
            bankList.newItem({id: "HDEB_N", name: translator.ccAvenue.hdfcNB});
            bankList.newItem({id: "IDBI_N", name: translator.ccAvenue.idbiNB});
            bankList.newItem({id: "INB_N", name: translator.ccAvenue.indNB});
            bankList.newItem({id: "IOB_N", name: translator.ccAvenue.iobNB});
            bankList.newItem({id: "NIIB_N", name: translator.ccAvenue.indusNB});
            bankList.newItem({id: "ING_N", name: translator.ccAvenue.ingNB});
            bankList.newItem({id: "JKB_N", name: translator.ccAvenue.jkbNB});
            bankList.newItem({id: "KTKB_N", name: translator.ccAvenue.karnatakaNB});
            bankList.newItem({id: "KVB_N", name: translator.ccAvenue.kvbNB});
            bankList.newItem({id: "NKMB_N", name: translator.ccAvenue.kotakNB});
            bankList.newItem({id: "LVB_N", name: translator.ccAvenue.lakshmiVilasNB});
            bankList.newItem({id: "OBPRF_N", name: translator.ccAvenue.orientalNB});
            bankList.newItem({id: "PNBCO_N", name: translator.ccAvenue.pnbCorpNB});
            bankList.newItem({id: "NPNB_N", name: translator.ccAvenue.pnbRetailNB});
            bankList.newItem({id: "RBS_N", name: translator.ccAvenue.rbsNB});
            bankList.newItem({id: "SIB_N", name: translator.ccAvenue.sibNB});
            bankList.newItem({id: "SCB_N", name: translator.ccAvenue.scbNB});
            bankList.newItem({id: "SBJ_N", name: translator.ccAvenue.sbjNB});
            bankList.newItem({id: "SBH_N", name: translator.ccAvenue.sbhNB});
            bankList.newItem({id: "SBI_N", name: translator.ccAvenue.sbiNB});
            bankList.newItem({id: "SBM_N", name: translator.ccAvenue.sbmNB});
            bankList.newItem({id: "SBP_N", name: translator.ccAvenue.sbpNB});
            bankList.newItem({id: "SBT_N", name: translator.ccAvenue.sbtNB});
            bankList.newItem({id: "SYNBK_N", name: translator.ccAvenue.syndNB});
            bankList.newItem({id: "TNMB_N", name: translator.ccAvenue.tnmNB});
            bankList.newItem({id: "UNI_N", name: translator.ccAvenue.ubiNB});
            bankList.newItem({id: "UBI_N", name: translator.ccAvenue.unitedNB});
        } else if (cardOptionSelected == "CCRD") {
            bankList.newItem({id: "CCI_N", name: translator.ccAvenue.iCash});
            bankList.newItem({id: "ITZ_N", name: translator.ccAvenue.itzCash});
            bankList.newItem({id: "OXIG_N", name: translator.ccAvenue.oxiCash});
            bankList.newItem({id: "PCC_N", name: translator.ccAvenue.payCash});
        }
        var bankWidget = new Select({
            id: "bankPaymentWidget",
            name: "bankOption",
            sortByLabel: false,
            store: bankList,
            maxHeight: 100
        }).placeAt("bankDataList");
    },
    addPayment: function() {
        var formElements = dojo.query("#paymentGateways input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var paymentGW = dijit.byId(checkedRadioId).value;
        if (this._cardOption == 'undefined' || this._cardOption == undefined || this._cardOption == '') {
            dojo.byId("cardExceptionMsg").style.display = "block";
        } else {
            dojo.byId("cardExceptionMsg").style.display = "none";
            var amount = dijit.byId("paymentAmountCCA");
            var bankName = dijit.byId("bankPaymentWidget").value;
            var pageNode2 = dojo.byId("ccAvenuePage");

            var widgets = dijit.registry.findWidgets(pageNode2);
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

            var _paymentStore = new JsonRest({
                target: core.getContextPath() + "/api/payment/ccAvenue/"
            });


            if (firstNode) {
                firstNode.focus();
            } else {
                _paymentStore.add({
                    amount: amount.value,
                    cardOption: this._cardOption,
                    bankName: bankName,
                    paymentGWName: paymentGW
                }).then(function(resultData) {
                    dojo.forEach(resultData, function(el) {
                        if (el.result === "OK") {
//                        console.log(el.url);
                           window.open(el.url, '_self');
                        } else {
                            registry.byId('userToaster').setContent(translator.common.billing.paymentFailed, 'error');
                            registry.byId('userToaster').show();
                        }
                    });
                    
                    
                    
                });
            }
        }
    },
    cancelPayment: function() {
        var bankWidget = dijit.byId("bankPaymentWidget");
        if (bankWidget) {
            dijit.byId("bankPaymentWidget").destroyRecursive();
        }
        var noBankOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var noBankOptionsList = new ItemFileWriteStore({data: noBankOptions});
        //noBankOptionsList.newItem({id: "option", name: translator.ccAvenue.none});
        var bankWidget = new Select({
            id: "bankPaymentWidget",
            name: "bankOption",
            sortByLabel: false,
            store: noBankOptionsList,
            value: "option"
        }).placeAt("bankDataList");
        
        core.router.go("#/user/billing/payment");
        
    }
}
window.InvoiceSummary = InvoiceSummary;
window.ManualPayment = ManualPayment;
window.CurrentUsage = CurrentUsage;
