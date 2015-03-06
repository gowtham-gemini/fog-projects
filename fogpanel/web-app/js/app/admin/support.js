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
    "dijit/layout/TabContainer",
    "dojox/html/entities"
],
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
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
                name: "support",
                module: "admin",
                filePath: "/js/app/admin/support.js",
                layout: {
                    name: "support",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "index": action(function() {
                    core.ui.loadTemplate("support", core.ui.getContentId());
                }),
                "department": action(function() {
                    if (dijit.byId("supportDepartmentForm")) {
                        dijit.byId("supportDepartmentForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("supportDepartmentPage", core.ui.getContentId());
                    AddDepartment.init();
                    AddDepartment.populateValues();
                }),
                "preDefinedReply": action(function() {
                    if (dijit.byId("definedReplyForm")) {
                        dijit.byId("definedReplyForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("preDefinedReply", core.ui.getContentId());
                    AddDefaultReply.init();
                    AddDefaultReply.populateValues();
                }),
                "tickets": action(function() {
                    if (dijit.byId("adminTicketsForm")) {
                        dijit.byId("adminTicketsForm").destroyRecursive();
                    }
                    core.ui.loadTemplate("tickets", core.ui.getContentId());
                    Tickets.init();
                    Tickets.populateValues();
                }),
                "viewTicket": action(function(id) {
                    if (dijit.byId("replyTicketForm")) {
                        dijit.byId("replyTicketForm").destroyRecursive();
                        dijit.byId("ticketAdminState").destroyRecursive();
                        dijit.byId("supportAdminDepWidget").destroyRecursive();
                        dijit.byId("replyTicketSubject").destroyRecursive();

                    }
                    core.ui.loadTemplate("viewTicket", core.ui.getContentId());
                    ViewTickets.init();
                    ViewTickets.populateValues(id);
                })
            });
        });

var ViewTickets = {
    'init': function() {

    },
    'populateValues': function(id) {

        dojo.byId("ticketId").value = id;

        var supportGetTicketStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/"
        });


        var supportListDepartmentStore = new JsonRest({
            target: core.getContextPath() + "/api/support/department/list"
        });


        var depOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var depList = new ItemFileWriteStore({data: depOptions});
        supportListDepartmentStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                depList.newItem({id: el.id.toString(), name: el.name});
            });

            var depWidget = new Select({
                id: "supportAdminDepWidget",
                name: "department",
                value: "option",
                sortByLabel: false,
                store: depList,
                onChange: function() {
                    ViewTickets.getReply();
                }
            }, "supportAdminDepList");

        });


        var supportListDefinedReplyStore = new JsonRest({
            target: core.getContextPath() + "/api/support/defaultReply/list"
        });


        var replyOptions = {
            identifier: 'id',
            label: 'name',
            items: [{'id': "select", 'name': translator.common.selectReply}]
        };
        var repList = new ItemFileWriteStore({data: replyOptions});
        supportListDefinedReplyStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                repList.newItem({id: el.id, name: el.subject});
            });

            var repWidget = new Select({
                id: "supportAdminDefinedReplyWidget",
                name: "defindeReply",
                sortByLabel: false,
                store: repList,
                onChange: function() {
                    ViewTickets.selectReply();
                }
            }, "supportAdminDefinedReplyList");

        });


        supportGetTicketStore.get(id).then(function(data) {
            dojo.forEach(data, function(ticket) {
                dojo.byId("ticAcName").innerHTML = ticket.account + " ( " + ticket.accountStatus + " )";
                dojo.byId("ticDate").innerHTML = ticket.date;
                dojo.byId("ticId").innerHTML = ticket.id;
                dijit.byId("ticketAdminState").set("value", ticket.state);
                dijit.byId("replyTicketSubject").set("value", ticket.subject);

                dijit.byId("supportAdminDepWidget").set("value", ticket.departmentId.toString());


            });

            dojo.forEach(data[0].ticketDetails, function(data) {

                var div = document.createElement("div");

                var leftDiv = document.createElement("div");
                var rightDiv = document.createElement("div");

                var sub = document.createElement("h2");
                var userType = document.createElement("h6");
                var date = document.createElement("h6");
                var para = document.createElement("p");

                var userTypeValue;
                if (data.ticketDetailUserType == "Admin") {
                    userTypeValue = translator.common.staff;
                    div.className = 'chat_staff span12';
                } else if (data.ticketDetailUserType == "Client") {
                    userTypeValue = translator.common.client;
                    div.className = 'chat_client span12';
                }

                leftDiv.className = 'ticket_respo_leftdiv';
                rightDiv.className = 'ticket_respo_rightdiv';

                var subnode = document.createTextNode(data.ticketDetailAccount);
                sub.appendChild(subnode);

                var userTypeNode = document.createTextNode(userTypeValue);
                userType.appendChild(userTypeNode);

                var datenode = document.createTextNode(data.ticketDetailDate);
                date.appendChild(datenode);
//                    var paranode = document.createTextNode(data.ticketDetailContent)
                para.innerHTML = data.ticketDetailContent.replace(/\n/g, "<br />");

                leftDiv.appendChild(sub);
                leftDiv.appendChild(userType);

                div.appendChild(leftDiv);

                rightDiv.appendChild(date);
                rightDiv.appendChild(para);
                div.appendChild(rightDiv);

                var rowfluid_div = document.createElement("div");
                rowfluid_div.appendChild(div);

                document.getElementById('chatDiv').appendChild(rowfluid_div);

            });
        });
    },
    'getReply': function() {


        var supportListDefinedReplyStore = new JsonRest({
            target: core.getContextPath() + "/api/support/defaultReply/list"
        });

        var replyOptions = {
            identifier: 'id',
            label: 'name',
            items: [{'id': "select", 'name': translator.common.selectReply}]
        };
        var repList = new ItemFileWriteStore({data: replyOptions});
        supportListDefinedReplyStore.query({department: dijit.byId("supportAdminDepWidget").getValue()}).then(function(data) {
            dojo.forEach(data, function(el) {
                repList.newItem({id: el.id, name: el.subject});
            });

            dijit.byId("supportAdminDefinedReplyWidget").setStore(repList);
            dijit.byId("supportAdminDefinedReplyWidget").startup();
        });


    },
    'selectReply': function() {


        var supportListDefinedReplyStore = new JsonRest({
            target: core.getContextPath() + "/api/support/defaultReply/"
        });


        if (dijit.byId("supportAdminDefinedReplyWidget").getValue() == "select") {
//            dijit.byId("replyTicketSubject").reset();
            dojo.byId("replyTicketContent").value = "";
        } else {

            supportListDefinedReplyStore.get(dijit.byId("supportAdminDefinedReplyWidget").getValue()).then(function(data) {
                dojo.forEach(data, function(definedReply) {
//                         dijit.byId("replyTicketSubject").set("value", definedReply.subject); 
                    dojo.byId("replyTicketContent").value = definedReply.content;
                });
            });

        }



    },
    'reply': function() {

        var supportTicketReplyStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/reply"
        });


        if (dojo.byId("replyTicketContent").value == "" || dojo.byId("replyTicketContent").value == null) {
            registry.byId("appToaster").setContent(translator.common.message.enterContent, "error");
            registry.byId("appToaster").show();
        } else {
            dijit.byId('viewTicketButton').set('style', {display: 'none'});
            dojo.byId("viewTicketLoader").style.display = "inline";
            supportTicketReplyStore.add({
                department: dijit.byId("supportAdminDepWidget").getValue(),
                state: dijit.byId("ticketAdminState").getValue(),
                subject: dijit.byId("replyTicketSubject").getValue(),
                content: dojo.byId("replyTicketContent").value,
                userType: "Admin",
                ticketId: dojo.byId("ticketId").value
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData == "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.added, "message");
                        registry.byId("appToaster").show();
                        dojo.byId("replyTicketContent").value = "";
                        core.router.go("#/admin/support/tickets");
                        //                    ViewTickets.populateTicketDetails();
                        //                    dijit.byId("replyTicketForm").reset();
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("appToaster").show();
                    }
                });

                dijit.byId('viewTicketButton').set('style', {display: 'none'});
                dojo.byId("viewTicketLoader").style.display = "inline";
            });
        }




    },
    'populateTicketDetails': function() {
        var supportGetTicketStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/"
        });

        var id = dojo.byId("ticketId").value;

        supportGetTicketStore.get(id).then(function(data) {
            dojo.forEach(data, function(ticket) {
                dojo.byId("ticAcName").innerHTML = ticket.account;
                dojo.byId("ticDate").innerHTML = ticket.date;
                dojo.byId("ticId").innerHTML = ticket.id;
            });


            dojo.forEach(data[0].ticketDetails, function(data) {

                var div = document.createElement("div");

                var leftDiv = document.createElement("div");
                var rightDiv = document.createElement("div");

                var sub = document.createElement("h2");
                var userType = document.createElement("h6");
                var date = document.createElement("h6");
                var para = document.createElement("p");

                var userTypeValue;
                if (data.ticketDetailUserType == "Admin") {
                    userTypeValue = translator.common.staff;
                    div.className = 'chat_staff span12';
                } else if (data.ticketDetailUserType == "Client") {
                    userTypeValue = translator.common.client;
                    div.className = 'chat_client span12';
                }

                leftDiv.className = 'ticket_respo_leftdiv';
                rightDiv.className = 'ticket_respo_rightdiv';

                var subnode = document.createTextNode(data.ticketDetailAccount);
                sub.appendChild(subnode);

                var userTypeNode = document.createTextNode(userTypeValue);
                userType.appendChild(userTypeNode);

                var datenode = document.createTextNode(data.ticketDetailDate);
                date.appendChild(datenode);
//                    var paranode = document.createTextNode(data.ticketDetailContent)
                para.innerHTML = data.ticketDetailContent.replace(/\n/g, "<br />");

                leftDiv.appendChild(sub);
                leftDiv.appendChild(userType);

                div.appendChild(leftDiv);

                rightDiv.appendChild(date);
                rightDiv.appendChild(para);
                div.appendChild(rightDiv);

                var rowfluid_div = document.createElement("div");
                rowfluid_div.appendChild(div);

                document.getElementById('chatDiv').appendChild(rowfluid_div);

            });


        });
    }

};


var Tickets = {
    'init': function() {
        var supportListDepartmentStore = new JsonRest({
            target: core.getContextPath() + "/api/support/department/list"
        });


        var depOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };
        var depList = new ItemFileWriteStore({data: depOptions});
        supportListDepartmentStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                depList.newItem({id: el.id, name: el.name});
            });
        });

        var depWidget = new Select({
            id: "ticketDepWidget",
            name: "department",
            value: "option",
            store: depList,
            sortByLabel: false,
            maxHeight: -1,
            onChange: function() {
                Tickets.getTicketStatusCount();
            }
        }, "ticketDepList");

        var supportStatusStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/status/count"
        });


        var statusOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };


        var statusList = new ItemFileWriteStore({data: statusOptions});
        supportStatusStore.query({department: "ALL", account: "ALL"}).then(function(data) {
            dojo.forEach(data, function(el) {
                statusList.newItem({id: "ALL", name: translator.common.all});
                statusList.newItem({id: "ACTIVE", name: translator.common.active + "(" + el.active + ")"});
                statusList.newItem({id: "OPEN", name: translator.common.open + "(" + el.open + ")"});
                statusList.newItem({id: "ON_HOLD", name: translator.common.onHold + "(" + el.onHold + ")"});
                statusList.newItem({id: "IN_PROGRSS", name: translator.common.inProgress + "(" + el.inProgress + ")"});
                statusList.newItem({id: "CLOSE", name: translator.common.closed + "(" + el.closed + ")"});
            });
        });

        var statusWidget = new Select({
            id: "statusDepWidget",
            name: "ststus",
            sortByLabel: false,
            store: statusList
        }, "statusList");



        var accountOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "ALL", name: translator.common.all}]
        };

        var accountListStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });


        var accountList = new ItemFileWriteStore({data: accountOptions});

        accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                accountList.newItem({id: el.id, name: el.userName});
            });
        });
        var accountWidget = new Select({
            id: "accountWidget",
            store: accountList,
            name: "forAccount",
            maxHeight: -1,
            sortByLabel: false,
            onChange: function() {
                Tickets.getTicketStatusCount();
            }
        }).placeAt("ticaccountList");
        accountWidget.startup();

        var supportCountTicketStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/count"
        });

        supportCountTicketStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("staffRep").innerHTML = el.staff;
                dojo.byId("clientRep").innerHTML = el.client;
                dojo.byId("newTic").innerHTML = el.newTic;
            });
        });

    },
    'populateValues': function() {

        if (dijit.byId("ticketGridWidget")) {
            dijit.byId("ticketGridWidget").destroyRecursive();
        }


        var supportListTicketStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/filter"
        });

        dojo.byId("ticketGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";


        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': translator.common.ticket.ticketId, 'field': 'id', 'width': '100px', 'formatter': function(data) {
                        var node = "<a href='#/admin/support/viewTicket/" + data + "'>" + data + "</a>";
                        return node;
                    }
                },
                {'name': translator.common.ticket.department, 'field': 'department', 'width': '200px'},
                {'name': translator.common.ticket.subject, 'field': 'subject', 'width': '100px'},
                {'name': translator.common.ticket.priority, 'field': 'priority', 'width': '100px', datatype: "string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false
                    }, 'formatter': function(data) {

                        var element = "";
                        if (data == "LOW") {
                            element = "<span class='greenColor'>" + translator.common.ticket.low + "</span>";
                        } else if (data == "HIGH") {
                            element = "<span class='redColor'>" + translator.common.ticket.high + "</span>";
                        } else if (data == "NORMAL") {
                            element = "<span class='orangeColor'>" + translator.common.ticket.normal + "</span>";
                        }
                        return element;
                    }},
                {'name': translator.user.grid.instance.layout.status, 'field': 'state', 'width': '100px', datatype: "string",
                    // Declare that we need the ComboBox for suggestions
                    autoComplete: true,
                    // Configure the ComboBox, so that it does not auto-complete our input
                    dataTypeArgs: {
                        autoComplete: false}, 'formatter': function(data) {

                        if (data == "IN_PROGRSS") {
                            return "<div class='ticket-stat-inprogress overflowLabel'>" + translator.common.message.inProgress + "</div>";
                        } else if (data == "OPEN") {
                            return "<div class='ticket-stat-open overflowLabel'>" + translator.common.open + "</div>";
                        } else if (data == "CLOSE") {
                            return "<div class='ticket-stat-closed overflowLabel'>" + translator.common.close + "</div>";
                        } else if (data == "ON_HOLD") {
                            return "<div class='ticket-stat-onhold overflowLabel'>" + translator.common.onHold + "</div>";
                        }
                    }},
                {'name': translator.common.account.name, 'field': 'user', 'width': '200px'},
                {'name': translator.common.ticket.postedDate, 'field': 'date', 'width': '100%', 'formatter': function(data) {

                        return  "<span class='bold'>" + data + "</span>";
                    }}
            ]
        ];

        supportListTicketStore.query({
            departmentId: "ALL",
            status: "ACTIVE",
            userId: "ALL",
            subject: ""
        }).then(function(data) {
            dojo.forEach(data, function(el) {
                gridDataList.newItem({
                    id: el.id,
                    user: el.account,
                    department: el.department,
                    subject: el.subject,
                    date: el.date,
                    state: el.state,
                    priority: el.priority
                });
            });

            dojo.byId("ticketGrid").innerHTML = "";

            var ticGrid = new EnhancedGrid({
                id: 'ticketGridWidget',
                store: gridDataList,
                structure: gridLayout,
                autoHeight: true,
                plugins: {
                    pagination: {
                        pageSizes: ["3", "5", "10", "All"],
                        description: true,
                        sizeSwitch: true,
                        pageStepper: true,
                        gotoButton: true,
                        /*page step to be displayed*/
                        maxPageStep: 4,
                        /*position of the pagination bar*/
                        position: "bottom"
                    }
                }
            });

            ticGrid.placeAt("ticketGrid");
            ticGrid.startup();

        });


    },
    'getTicketStatusCount': function() {

        var supportCountTicketStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/status/count"
        });

        var statusOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var statusList = new ItemFileWriteStore({data: statusOptions});
        supportCountTicketStore.query({department: dijit.byId("ticketDepWidget").getValue(), account: dijit.byId("accountWidget").getValue()}).then(function(data) {
            dojo.forEach(data, function(el) {
                statusList.newItem({id: "ALL", name: translator.common.all});
                statusList.newItem({id: "ACTIVE", name: translator.common.active + "(" + el.active + ")"});
                statusList.newItem({id: "OPEN", name: translator.common.open + "(" + el.open + ")"});
                statusList.newItem({id: "ON_HOLD", name: translator.common.onHold + "(" + el.onHold + ")"});
                statusList.newItem({id: "IN_PROGRSS", name: translator.common.inProgress + "(" + el.inProgress + ")"});
                statusList.newItem({id: "CLOSE", name: translator.common.closed + "(" + el.closed + ")"});
            });

            dijit.byId("statusDepWidget").setStore(statusList);
            dijit.byId("statusDepWidget").startup();

        });
    },
    'getTicket': function() {
        var instanceData = {
            items: []
        };
        var ticGrid = dijit.byId("ticketGridWidget");
        var ticDataList = new ItemFileWriteStore({data: instanceData});

        var supportListTicketStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/list"
        });


        supportListTicketStore.query({department: dijit.byId("ticketDepWidget").getValue(), state: dijit.byId("ticketState").getValue()}).then(function(data) {
            dojo.forEach(data, function(el) {
                ticDataList.newItem({
                    id: el.id,
                    user: el.account,
                    department: el.department,
                    subject: el.subject,
                    date: el.date,
                    state: el.state,
                    priority: el.priority
                });
            });

            ticGrid.setStore(ticDataList);

        });
    },
    'ticketFilter': function() {

        var instanceData = {
            items: []
        };
        var ticGrid = dijit.byId("ticketGridWidget");
        var ticDataList = new ItemFileWriteStore({data: instanceData});

        var supportListTicketStore = new JsonRest({
            target: core.getContextPath() + "/api/support/ticket/filter"
        });


        supportListTicketStore.query({
            departmentId: dijit.byId("ticketDepWidget").getValue(),
            status: dijit.byId("statusDepWidget").getValue(),
            userId: dijit.byId("accountWidget").getValue(),
            subject: dijit.byId("filterSubject").getValue()
        }).then(function(data) {
            dojo.forEach(data, function(el) {
                ticDataList.newItem({
                    id: el.id,
                    user: el.account,
                    department: el.department,
                    subject: el.subject,
                    date: el.date,
                    state: el.state,
                    priority: el.priority
                });
            });

            ticGrid.setStore(ticDataList);

        });

    }

};


var AddDepartment = {
    'init': function() {

    },
    'populateValues': function() {


        var supportListDepartmentStore = new JsonRest({
            target: core.getContextPath() + "/api/support/department/list"
        });


        dojo.byId("departmentGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";


        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': translator.common.idCaps, 'field': 'id', 'width': '200px'},
                {'name': translator.common.name, 'field': 'name', 'width': '600px'},
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter': function(data) {
                        return new dijit.form.Button({label: translator.common.view,
                            "class": "defaultbtn", onClick: function() {
                                AddDepartment.viewDep(data);
                            }});
                    }}
            ]
        ];

        supportListDepartmentStore.query().then(function(data) {

            if (data.length == 0) {
                dojo.byId("departmentGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>" + translator.common.noDepartment + "</div>";
            } else {


                dojo.forEach(data, function(el) {
                    gridDataList.newItem({
                        id: el.id,
                        name: el.name,
                        action: {'id': el.id, 'name': el.name}
                    });

                    dojo.byId("departmentGrid").innerHTML = "";


                    var depGrid = new EnhancedGrid({
                        store: gridDataList,
                        "class" : "span12",
                        structure: gridLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });

                    depGrid.placeAt("departmentGrid");
                    depGrid.startup();

                });

            }
        });


    },
    'add': function() {

        var supportDepartmentStore = new JsonRest({
            target: core.getContextPath() + "/api/support/department/add"
        });



        var pageNode = dojo.byId("supportDepartmentPage");
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
            dijit.byId('departButton').set('style', {display: 'none'});
            dojo.byId('departLoader').style.display = 'block';

            supportDepartmentStore.add({
                name: dijit.byId("departmentName").getValue()
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dijit.byId('departButton').set('style', {display: 'inline'});
                    dojo.byId('departLoader').style.display = 'none';
                    if (resultData == "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.departmentAdded, "message");
                        registry.byId("appToaster").show();
                        dijit.byId("supportDepartmentForm").reset();
                        AddDepartment.populateValues();
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("appToaster").show();
                    }

                });
            });

        }
    },
    'cancel': function() {
        dijit.byId("supportDepartmentForm").reset();
        dojo.byId("depEditDiv").style.display = "none";
        dojo.byId("depOpenDiv").style.display = "block";
    },
    'delete': function() {

        var supportDepartmentStore = new JsonRest({
            target: core.getContextPath() + "/api/support/department/delete/"
        });

        supportDepartmentStore.put({
            id: dojo.byId("depId").value
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.deletedSuccess, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("supportDepartmentForm").reset();
                    AddDepartment.populateValues();
                    dojo.byId("depEditDiv").style.display = "none";
                    dojo.byId("depOpenDiv").style.display = "block";
                    location.reload();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    'edit': function() {

        var supportDepartmentStore = new JsonRest({
            target: core.getContextPath() + "/api/support/department/update/"
        });

        var pageNode = dojo.byId("supportDepartmentPage");
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
            dijit.byId('departEditButton').set('style', {display: 'none'});
            dojo.byId('departEditLoader').style.display = 'block';
            supportDepartmentStore.put({
                id: dojo.byId("depId").value,
                name: dojox.html.entities.encode(dijit.byId("departmentName").getValue())
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dijit.byId('departEditButton').set('style', {display: 'inline'});
                    dojo.byId('departEditLoader').style.display = 'none';
                    if (resultData == "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.updateSuccess, "message");
                        registry.byId("appToaster").show();
                        dijit.byId("supportDepartmentForm").reset();
                        AddDepartment.populateValues();
                        dojo.byId("depEditDiv").style.display = "none";
                        dojo.byId("depOpenDiv").style.display = "block";
                        location.reload();
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("appToaster").show();
                    }
                });
            });

        }

    },
    'viewDep': function(data) {
        dojo.byId("depId").value = data.id;
        dijit.byId("departmentName").setValue(data.name);
        dojo.byId("depEditDiv").style.display = "block";
        dojo.byId("depOpenDiv").style.display = "none";

    }


};
var AddDefaultReply = {
    'init': function() {

        var supportListDepartmentStore = new JsonRest({
            target: core.getContextPath() + "/api/support/department/list"
        });

        var depOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var depList = new ItemFileWriteStore({data: depOptions});
        supportListDepartmentStore.query().then(function(data) {

            if (data.length == 0) {
                core.router.go("#/admin/support/department");
            } else {

                dojo.forEach(data, function(el) {
                    depList.newItem({id: el.id.toString(), name: el.name});
                });
            }
        });

        var depWidget = new Select({
            id: "supportDepWidget",
            name: "department",
            sortByLabel: false,
            store: depList
        }, "supportDepList");


    },
    'populateValues': function() {


        dojo.byId("preDefinedReplyGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";


        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': translator.common.idCaps, 'field': 'id', 'width': '10%'},
                {'name': translator.common.ticket.subject, 'field': 'subject', 'width': '20%'},
                {'name': translator.common.action, 'field': 'action', 'width': '20%', 'formatter': function(data) {
                        return new dijit.form.Button({label: translator.common.view,
                            "class": "defaultbtn", onClick: function() {
                                AddDefaultReply.viewRep(data);
                            }});
                    }}
            ]
        ];


        var supportListDefinedReplyStore = new JsonRest({
            target: core.getContextPath() + "/api/support/defaultReply/list"
        });


        supportListDefinedReplyStore.query().then(function(data) {

            if (data.length == 0) {
                dojo.byId("preDefinedReplyGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>" + translator.common.message.noPreDefinetReply + "</div>";
            } else {


                dojo.forEach(data, function(el) {

                    gridDataList.newItem({
                        id: el.id,
                        subject: el.subject,
                        action: {'id': el.id, 'subject': el.subject, 'departmentId': el.departmentId, 'content': el.content}
                    });

                });

                dojo.byId("preDefinedReplyGrid").innerHTML = "";


                var depGrid = new EnhancedGrid({
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });

                depGrid.placeAt("preDefinedReplyGrid");
                depGrid.startup();

            }
        });


    },
    'add': function() {

        var supportDefaultReplyStore = new JsonRest({
            target: core.getContextPath() + "/api/support/defaultReply/add"
        });

        var pageNode = dojo.byId("definedReplyPage");
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
            if (dojo.byId("preDefinedReplyContent").value == "" || dojo.byId("preDefinedReplyContent").value == null) {
                registry.byId("appToaster").setContent(translator.common.message.enterContent, "error");
                registry.byId("appToaster").show();
            } else {

                dijit.byId('preDepartButton').set('style', {display: 'none'});
                dojo.byId("preDepartLoader").style.display = "block";



                supportDefaultReplyStore.add({
                    department: dijit.byId("supportDepWidget").getValue(),
                    subject: dijit.byId("preDefinedReplySubject").getValue(),
                    content: dojo.byId("preDefinedReplyContent").value
                }).then(function(data) {
                    dijit.byId('preDepartButton').set('style', {display: 'block'});
                    dojo.byId("preDepartLoader").style.display = "none";
                    dojo.forEach(data, function(resultData) {
                        if (resultData == "OK") {
                            registry.byId("appToaster").setContent(translator.common.message.addedSuccess, "message");
                            registry.byId("appToaster").show();
                            dijit.byId("definedReplyForm").reset();
                            dojo.byId("preDefinedReplyContent").value = "";
                            AddDefaultReply.populateValues();
                        } else {
                            registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                            registry.byId("appToaster").show();
                        }
                    });
                });
            }
        }




    },
    'cancel': function() {
        dijit.byId("definedReplyForm").reset();
    },
    'viewRep': function(data) {
        dojo.byId("preDefRepId").value = data.id;
        dijit.byId("preDefinedReplySubject").setValue(data.subject);
        dojo.byId("preDefinedReplyContent").value = data.content;
        dijit.byId("supportDepWidget").set("value", data.departmentId.toString());
        dojo.byId("repEditDiv").style.display = "block";
        dojo.byId("repOpenDiv").style.display = "none";

    },
    'edit': function() {

        var supportDefaultReplyStore = new JsonRest({
            target: core.getContextPath() + "/api/support/defaultReply/update/"
        });

        var pageNode = dojo.byId("definedReplyPage");
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

            if (dojo.byId("preDefinedReplyContent").value == "" || dojo.byId("preDefinedReplyContent").value == null) {
                registry.byId("appToaster").setContent(translator.common.message.enterContent, "error");
                registry.byId("appToaster").show();
            } else {

                supportDefaultReplyStore.put({
                    id: dojo.byId("preDefRepId").value,
                    department: dijit.byId("supportDepWidget").getValue(),
                    subject: dojox.html.entities.encode(dijit.byId("preDefinedReplySubject").getValue()),
                    content: dojox.html.entities.encode(dojo.byId("preDefinedReplyContent").value)
                }).then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        if (resultData == "OK") {
                            registry.byId("appToaster").setContent(translator.common.message.updateSuccess, "message");
                            registry.byId("appToaster").show();
                            dijit.byId("definedReplyForm").reset();
                            dojo.byId("preDefinedReplyContent").value = "";
                            dojo.byId("repEditDiv").style.display = "none";
                            dojo.byId("repOpenDiv").style.display = "block";
                            AddDefaultReply.populateValues();
                        } else {
                            registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                            registry.byId("appToaster").show();
                        }
                    });
                });
            }
        }

    },
    'delete': function() {

        var supportDefaultReplyStore = new JsonRest({
            target: core.getContextPath() + "/api/support/defaultReply/delete/"
        });

        supportDefaultReplyStore.put({
            id: dojo.byId("preDefRepId").value
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.deletedSuccess, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("definedReplyForm").reset();
                    dojo.byId("preDefinedReplyContent").value = "";
                    dojo.byId("repEditDiv").style.display = "none";
                    dojo.byId("repOpenDiv").style.display = "block";
                    AddDefaultReply.populateValues();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    }
};

window.AddDepartment = AddDepartment;
