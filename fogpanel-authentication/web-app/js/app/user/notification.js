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
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dojox/calendar/Calendar",
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
    "dijit/Dialog"
],
        function (dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
                ItemFileWriteStore, Memory, Observable, DataGrid, EnhancedGrid, Calendar, ContentPane, Source, MultiSelect, dom, win) {
            window.query = query;
            window.domClass = domClass;
            window.domConstruct = domConstruct;
            window.JsonRest = JsonRest;
            window.registry = registry;
            window.FilteringSelect = FilteringSelect;
            window.ItemFileWriteStore = ItemFileWriteStore;
            window.Observable = Observable;
            window.Memory = Memory;
            window.Select = Select;
            window.ContentPane = ContentPane;
            window.DataGrid = DataGrid;
            window.EnhancedGrid = EnhancedGrid;
            window.Calendar = Calendar;
            window.Source = Source;
            window.MultiSelect = MultiSelect;
            window.dom = dom;
            window.win = win;
            controller({
                name: "notification",
                module: "user",
                filePath: "/js/app/user/notification.js",
                layout: {
                    name: "notification",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "index": action(function () {
                    if (dijit.byId("addTopicForm")) {
                        dijit.byId("addTopicForm").destroyRecursive();
                        dijit.byId("editTopicDialog").destroyRecursive();
                    }
                    core.ui.loadTemplate("topic", core.ui.getContentId());
                    TopicInfo.init();
                    TopicInfo.populateValues();
                }),
                "viewTopic": action(function (id) {

                    if (dijit.byId("addSubscribersForm")) {
                        dijit.byId("addSubscribersForm").destroyRecursive();
//                dijit.byId("closeTicketDialog").destroyRecursive();
                    }

                    core.ui.loadTemplate("viewTopic", core.ui.getContentId());
                    viewTopic.init(id);
//                    viewTopic.populateValues();
                })
            });
        });

var viewTopic = {
    init: function (id) {

        var topicListStore = new JsonRest({
            target: core.getContextPath() + "/api/notificationTopic/"
        });

        dojo.byId("subscribersGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";

        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': translator.common.idCaps, 'field': 'id', 'width': '150px', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '150px'},
                {'name': translator.common.status.name, 'field': 'status', 'width': '150px'},
                {'name': translator.common.createdDate, 'field': 'date', 'width': '150px'},
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter': function (data) {
                        var menu = new DropDownMenu({style: "display: none;"});

                        var menuItem3 = new MenuItem({
                            label: translator.common.deleteData,
                            onClick: function () {

                            }
                        });
                        menu.addChild(menuItem3);
                        menu.startup();

                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });
                        return button;
                    }}
            ]
        ];


        topicListStore.get(id).then(function (data) {
            dojo.forEach(data, function (el) {
                dojo.byId("topicId").innerHTML = el.id;
                dojo.byId("topicName").innerHTML = el.name;
                dojo.byId("topicStatus").innerHTML = el.status;
                dojo.byId("topicCreatedDate").innerHTML = el.date;
                dojo.byId("viewTopicName").innerHTML = el.name;
                dojo.byId("selectedTopicId").value = el.id;
                if (el.subscribers.length === 0) {
                    dojo.byId("subscribersGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                       <i class='icon-exclamation-sign'></i>" + translator.common.message.noSubscribers + "</div>";
                } else {
                    dojo.forEach(el.subscribers, function (subscriber) {
                        gridDataList.newItem({
                            id: subscriber.id,
                            name: subscriber.name,
                            date: subscriber.subscribedDate,
                            action: subscriber
                        });
                        dojo.byId("subscribersGrid").innerHTML = "";

                        var depGrid = new EnhancedGrid({
                            store: gridDataList,
                            "class": "span12",
                            structure: gridLayout,
                            autoHeight: true,
                            plugins: core.getGridConfig().plugins
                        });

                        depGrid.placeAt("subscribersGrid");
                        depGrid.startup();

                    });
                }
            });
        });
    },
    populateSubscribersValues: function (id) {
        
        dijit.byId("addSubscribersForm").reset();
        var topicListStore = new JsonRest({
            target: core.getContextPath() + "/api/notificationTopic/"
        });

        dojo.byId("subscribersGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";

        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': translator.common.idCaps, 'field': 'id', 'width': '150px', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '150px'},
                {'name': translator.common.createdDate, 'field': 'date', 'width': '150px'},
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter': function (data) {
                        var menu = new DropDownMenu({style: "display: none;"});

                        var menuItem3 = new MenuItem({
                            label: translator.common.remove,
                            onClick: function () {
                                
                                var unsubscribeRestStore = new JsonRest({
                                    target: core.getContextPath() + "/api/notificationTopic/removeSubscriber/"
                                });
                                
                                unsubscribeRestStore.remove(data.id).then(function (resultData) {
                                    if (resultData === "SUCCESS") {
                                        registry.byId("userToaster").setContent(translator.common.message.subscriberDeleted, "message");
                                        registry.byId("userToaster").show();
                                        viewTopic.populateSubscribersValues();
                                    } else {
                                        registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                                        registry.byId("userToaster").show();
                                    }
                                });
                            }
                        });
                        menu.addChild(menuItem3);
                        menu.startup();

                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });
                        return button;
                    }}
            ]
        ];

        topicListStore.get(dojo.byId("selectedTopicId").value).then(function (data) {
            dojo.forEach(data, function (el) {

                if (el.subscribers.length === 0) {
                    dojo.byId("subscribersGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                       <i class='icon-exclamation-sign'></i>" + translator.common.message.noSubscribers + "</div>";
                } else {
                    dojo.forEach(el.subscribers, function (subscriber) {
                        gridDataList.newItem({
                            id: subscriber.id,
                            name: subscriber.name,
                            date: subscriber.subscribedDate,
                            action: subscriber
                        });
                        dojo.byId("subscribersGrid").innerHTML = "";

                        var depGrid = new EnhancedGrid({
                            store: gridDataList,
                            "class": "span12",
                            structure: gridLayout,
                            autoHeight: true,
                            plugins: core.getGridConfig().plugins
                        });

                        depGrid.placeAt("subscribersGrid");
                        depGrid.startup();

                    });
                }
            });
        });
    },
    addSubscriber : function() {
        
        var topicRestStore = new JsonRest({
            target: core.getContextPath() + "/api/notificationTopic/addSubscriber"
        });

        var pageNode = dojo.byId("subscriberPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function (el) {
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

            topicRestStore.add({
                email: dijit.byId("subscriberEmail").getValue(),
                topicId: dojo.byId("selectedTopicId").value
            }).then(function (data) {
                dojo.forEach(data, function (resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.subscriberAdded, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("addSubscribersForm").reset();
                        viewTopic.populateSubscribersValues();
                    } else if(resultData.result === "EXISTS") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("userToaster").show();
                    }

                });
            });

        }
        
    }
};

var TopicInfo = {
    init: function () {


    },
    editTopicShow : function(data) {
        dijit.byId("editTopicDialog").show();
        dojo.byId("topicId").value = data.id;
        dijit.byId("topicEditName").setValue(data.name);
    },
    closeEditDialog :function() {
        dijit.byId("editTopicDialog").hide();
    },
    editTopic : function() {
        
        var editStore = new JsonRest({
            target: core.getContextPath() + "/api/notificationTopic/"
        });
                
        var pageNode = dojo.byId("editPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function (el) {
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
        
            editStore.put({
                id: dojo.byId("topicId").value,
                name:dijit.byId("topicEditName").getValue()
            }).then(function (data) {
                dojo.forEach(data, function (resultData) {                    
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.topicEdited, "message");
                        registry.byId("userToaster").show();
                        TopicInfo.populateValues();
                        dijit.byId("editTopicDialog").hide();
                    } else if(resultData.result === "EXISTS") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("userToaster").show();
                    }
                });
            });
        }
        
    },
    populateValues: function () {

        var topicListStore = new JsonRest({
            target: core.getContextPath() + "/api/notificationTopic"
        });

        dojo.byId("topicGrid").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.topicLoader + "</p>";

        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': translator.common.idCaps, 'field': 'id', 'width': '150px', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '150px','formatter': function (data) {
                        return "<a href='#/user/notification/viewTopic/" + data.id + "' title='" + translator.common.view + "'>" + data.name + "</a>";
                }},
                {'name': translator.common.status.name, 'field': 'status', 'width': '150px'},
                {'name': translator.common.createdDate, 'field': 'date', 'width': '150px'},
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter': function (data) {
                        var menu = new DropDownMenu({style: "display: none;"});

                        var menuItem1 = new MenuItem({
                            label: translator.common.view,
                            onClick: function () {
                                core.router.go("#/user/notification/viewTopic/" + data.id + "");
                            }
                        });
                        menu.addChild(menuItem1);
                        menu.startup();

                        var menuItem2 = new MenuItem({
                            label: translator.common.edit,
                            onClick: function () {
                                TopicInfo.editTopicShow(data);
                            }
                        });
                        menu.addChild(menuItem2);
                        menu.startup();

                       var menuItem3 = new MenuItem({
                            label: translator.common.remove,
                            onClick: function () {
                                
                                var removeRestStore = new JsonRest({
                                    target: core.getContextPath() + "/api/notificationTopic/"
                                });
                                
                                removeRestStore.remove(data.id).then(function (resultData) {
                                    if (resultData === "SUCCESS") {
                                        registry.byId("userToaster").setContent(translator.common.message.topicDeleted, "message");
                                        registry.byId("userToaster").show();
                                        TopicInfo.populateValues();
                                    } else if(resultData === "ALARM_EXISTS") {
                                        registry.byId("userToaster").setContent(translator.common.message.topicAlarmExists, "error");
                                        registry.byId("userToaster").show();
                                    } else {
                                        registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                                        registry.byId("userToaster").show();
                                    }
                                });
                            }
                        });
                        menu.addChild(menuItem3);
                        menu.startup();

                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });
                        return button;
                    }}
            ]
        ];
        topicListStore.query().then(function (data) {
            if (data.length === 0) {
                dojo.byId("topicGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>" + translator.common.message.noTopic + "</div>";
            } else {
                dojo.forEach(data, function (el) {
                    gridDataList.newItem({
                        id: el.id,
                        name: el,
                        status: el.status,
                        date: el.date,
                        action: el
                    });
                    dojo.byId("topicGrid").innerHTML = "";

                    var depGrid = new EnhancedGrid({
                        store: gridDataList,
                        "class": "span12",
                        structure: gridLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });

                    depGrid.placeAt("topicGrid");
                    depGrid.startup();

                });
            }
        });
    },
    addTopic: function () {
        var topicRestStore = new JsonRest({
            target: core.getContextPath() + "/api/notificationTopic/"
        });

        var pageNode = dojo.byId("topicPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function (el) {
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

            topicRestStore.add({
                name: dijit.byId("topicName").getValue()
            }).then(function (data) {
                dojo.forEach(data, function (resultData) {
                    if (resultData.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.topicAdded, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("addTopicForm").reset();
                        TopicInfo.populateValues();
                    } else if(resultData.result === "EXISTS") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("userToaster").show();
                    }

                });
            });

        }
    }

};

window.NotificationInfo = NotificationInfo;