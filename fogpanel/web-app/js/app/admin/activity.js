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
    "dijit/form/DateTextBox",
    "dijit/layout/TabContainer",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dijit/layout/BorderContainer",
    "dijit/form/DropDownButton",
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
], function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
        ItemFileWriteStore, DataGrid, ContentPane, Source, MultiSelect, dom, win) {
    window.query = query;
    window.translator = translator;
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
        name: "activity",
        module: "admin",
        filePath: "/js/app/admin/activity.js",
        layout: {
            name: "activity",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {
            core.ui.loadTemplate("activity", core.ui.getContentId());

//                DraftInvoiceInfo.init();
//                DraftInvoiceInfo.populateValues();       
        }),
        "events": action(function() {
            core.ui.loadTemplate("events", core.ui.getContentId());

            Events.init();
            Events.populateValues();
        }),
        "alerts": action(function() {
            core.ui.loadTemplate("alerts", core.ui.getContentId());

            Alerts.init();
            Alerts.populateValues();
        }),
        "cloudUsage": action(function() {
            core.ui.loadTemplate("cloudUsage", core.ui.getContentId());

            CloudUsage.init();
            CloudUsage.populateValues();
        })

    });
});

var Events = {
    init: function() {

    },
    populateValues: function() {
        if (dijit.byId("eventGrid")) {
            dijit.byId("eventGrid").destroyRecursive();
        }
        dojo.byId("eventsAlertInfoGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
        var eventData = {
            items: []
        };

        var eventAlerts = new JsonRest({
            target: core.getContextPath() + "/api/notification/event"
        });

        var eventDataList = new ItemFileWriteStore({data: eventData});
        eventAlerts.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noEventsMessageBox").style.display = "block";

                dojo.byId("eventsAlertInfoGrid").innerHTML = "";
            } else {
                dojo.byId("noEventsMessageBox").style.display = "none";
                dojo.forEach(data, function(alertData) {
                    eventDataList.newItem({
                        id: alertData.id,
                        date: alertData.date,
                        description: alertData.description,
                        level: alertData.level,
                        state: alertData.state,
                        account: alertData.account,
                        type: alertData.type + "," + alertData.level
                    });
                });
                dojo.byId("eventsAlertInfoGrid").innerHTML = "";
                var eventGridWidget = new EnhancedGrid({
                    id: 'eventGrid',
                    "class" : "span12",
                    store: eventDataList,
                    structure: eventLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                eventGridWidget.placeAt("eventsAlertInfoGrid");
                eventGridWidget.startup();
            }
        });

        var eventLayout = [[
                {'name': 'ID', 'field': 'id', 'hidden': true},
                {'name': translator.common.account.name, 'field': 'account', 'width': '200px', datatype: "string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false
                    }},
                {'name': translator.common.date, 'field': 'date', 'width': '200px', datatype: "date",
                    // Declare how the data in store should be parsed to a Date object.
                    dataTypeArgs: {
                        datePattern: "yyyy-MM-dd"
                    }
                },
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'formatter': function(data) {
                        var lavelInfo = data.split(",");
                        var element = "";
                        if (lavelInfo[1] == "INFO") {
                            element = "<span class='greenColor'>" + lavelInfo[0] + "</span>";
                        } else if (lavelInfo[1] == "ERROR") {
                            element = "<span class='redColor'>" + lavelInfo[0] + "</span>";
                        } else if (lavelInfo[1] == "WARN") {
                            element = "<span class='orangeColor'>" + lavelInfo[0] + "</span>";
                        }
                        return element;

                    }},
                {'name': translator.common.level, 'field': 'level', 'width': '100px', datatype: "string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false
                    }},
                {'name': translator.common.status.name, 'field': 'state', 'width': '100px', datatype: "string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false
                    }},
                {'name': translator.common.description, 'field': 'description', 'width': '100%'}

            ]];
    }
};

var CloudUsage = {
    _alertNotificationRestStore: "",
    init: function() {

    },
    populateValues: function() {
        if (dijit.byId("cloudUsageGrid")) {
            dijit.byId("cloudUsageGrid").destroyRecursive();
        }
        dojo.byId("cloudUsageInfoGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
        var alertData = {
            items: []
        };


        var csAlerts = new JsonRest({
            target: core.getContextPath() + "/api/notification/cloudUsage"
        });

        var alertDataList = new ItemFileWriteStore({data: alertData});
        csAlerts.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noCloudUsageMessageBox").style.display = "block";

                dojo.byId("cloudUsageInfoGrid").innerHTML = "";
            } else {
                dojo.byId("noCloudUsageMessageBox").style.display = "none";
                dojo.forEach(data, function(csalertData) {
                    alertDataList.newItem({
                        id: csalertData.id,
                        date: csalertData.date,
                        description: csalertData.description,
                        action: {'alertId': csalertData.id, viewed: csalertData.viewed}
                    });
                });
                dojo.byId("cloudUsageInfoGrid").innerHTML = "";
                var eventGridWidget = new EnhancedGrid({
                    id: 'cloudUsageGrid',
                    "class" : "span12",
                    store: alertDataList,
                    structure: alertsLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                eventGridWidget.placeAt("cloudUsageInfoGrid");
                eventGridWidget.startup();
            }



        });

        var alertsLayout = [[
                {'name': 'ID', 'field': 'id', 'hidden': true},
                {'name': translator.common.description, 'field': 'description', 'width': '800px'},
                {'name': translator.common.date, 'field': 'date', 'width': '100px', datatype: "date",
                    // Declare how the data in store should be parsed to a Date object.
                    dataTypeArgs: {
                        datePattern: "yyyy-MM-dd"
                    }
                },
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter': function(data) {
                        if (data.viewed == false || data.viewed == "false") {
                            return new dijit.form.Button({
                                label: translator.common.view,
                                "class": "failur",
                                onClick: function() {
                                    CloudUsage.view(data);
                                }
                            });
                        } else {
                            return new dijit.form.Button({
                                label: translator.common.viewed,
                                "class": "defaultbtn", onClick: function() {

                                }
                            });
                        }
                    }, datatype: "string", autoComplete: true}
            ]];

    },
    view: function(data) {

        var cloudUsageAlerts = new JsonRest({
            target: core.getContextPath() + "/api/notification/cloudUsage/view/"
        });

        cloudUsageAlerts.put({id: data.alertId}).then(function(data) {
        });

        CloudUsage.populateValues();
    }
};
var Alerts = {
    _alertNotificationRestStore: "",
    init: function() {

    },
    populateValues: function() {
        if (dijit.byId("alertGrid")) {
            dijit.byId("alertGrid").destroyRecursive();
        }
        dojo.byId("csAlertInfoGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
        var alertData = {
            items: []
        };


        var csAlerts = new JsonRest({
            target: core.getContextPath() + "/api/notification/alerts"
        });

        var alertDataList = new ItemFileWriteStore({data: alertData});
        csAlerts.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noAlertsMessageBox").style.display = "block";

                dojo.byId("csAlertInfoGrid").innerHTML = "";
            } else {
                dojo.byId("noAlertsMessageBox").style.display = "none";
                dojo.forEach(data, function(csalertData) {
                    alertDataList.newItem({
                        id: csalertData.id,
                        date: csalertData.date,
                        description: csalertData.description
                    });
                });
                dojo.byId("csAlertInfoGrid").innerHTML = "";
                var eventGridWidget = new EnhancedGrid({
                    id: 'alertGrid',
                    "class" : "span12",
                    store: alertDataList,
                    structure: alertsLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                eventGridWidget.placeAt("csAlertInfoGrid");
                eventGridWidget.startup();
            }
        });

        var alertsLayout = [[
            {'name': 'ID', 'field': 'id', 'hidden': true},
            {'name': translator.common.description, 'field': 'description', 'width': '800px'},
            {'name': translator.common.date, 'field': 'date', 'width': '100%', datatype: "date",
                // Declare how the data in store should be parsed to a Date object.
                dataTypeArgs: {
                    datePattern: "yyyy-MM-dd"
                }
            }
        ]];

    }
};