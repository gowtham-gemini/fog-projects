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
    function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
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
        
        controller ({
            name: "support",
            module: "user",
            filePath: "/js/app/user/support.js",
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
            "addTicket": action(function() {
                if(dijit.byId("addTicketForm")) {
                    dijit.byId("addTicketForm").destroyRecursive();
                }
                core.ui.loadTemplate("addTicket", core.ui.getContentId()); 
                AddTicket.init();
                AddTicket.populateValues();
            }),
            "tickets": action(function() {
                if(dijit.byId("adminTicketsForm")) {
                    dijit.byId("adminTicketsForm").destroyRecursive();
                    dijit.byId("closeTicketDialog").destroyRecursive();
                }
                core.ui.loadTemplate("tickets", core.ui.getContentId()); 
                Tickets.init();
                Tickets.populateValues();
            }),       
            "viewTicket": action(function(id) {
                if(dijit.byId("replyTicketForm")) {
                    dijit.byId("replyTicketForm").destroyRecursive();
                    dijit.byId("supportAdminDepWidget").destroyRecursive();
                    dijit.byId("replyTicketSubject").destroyRecursive();
                    dijit.byId("userTicketPriority").destroyRecursive();
                }
                core.ui.loadTemplate("viewTicket", core.ui.getContentId()); 
                ViewTickets.init();
                ViewTickets.populateValues(id);
            })
            
          
        });
    });

var ViewTickets = {
    
    'init' : function() {
       
    },
    'populateValues': function(id) {          
        dojo.byId("ticketId").value = id;        
        var supportGetTicketStore = new JsonRest({
            target: core.getContextPath()+"/api/support/ticket/"
        });                
        var supportListDepartmentStore = new JsonRest({
            target: core.getContextPath()+"/api/support/department/list"
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
        });           
        var depWidget = new Select({
            id: "supportAdminDepWidget",
            name: "department",
            value: "option",        
            store: depList
        }, "supportAdminDepList");   
        
        var status;
        supportGetTicketStore.get(id).then(function(data) {
            dojo.forEach(data, function(ticket) {
                dojo.byId("ticAcName").innerHTML = ticket.account;
                dojo.byId("ticDate").innerHTML = ticket.date;               
                if(ticket.state == "IN_PROGRSS") {
                    status = translator.common.message.inProgress;                   
                    dojo.addClass(dojo.byId("ticState"), "viewticket_detail_status_progress");                   
                } else if(ticket.state == "OPEN") {          
                    status = translator.common.open;                   
                    dojo.addClass(dojo.byId("ticState"), "viewticket_detail_status_open");
                } else if(ticket.state == "CLOSE") {
                    status = translator.common.close;                   
                    dojo.addClass(dojo.byId("ticState"), "viewticket_detail_status_close");
                } else if(ticket.state == "ON_HOLD") {
                    status = translator.common.onHold;                   
                    dojo.addClass(dojo.byId("ticState"), "viewticket_detail_status_onhold");
                }               
                dojo.byId("ticState").innerHTML =  status;
                dojo.byId("ticId").innerHTML = ticket.id;                          
                dijit.byId("supportAdminDepWidget").set("value", ticket.departmentId);  
                dijit.byId("replyTicketSubject").set("value", ticket.subject);  
                dijit.byId("supportAdminDepWidget").set("value", ticket.departmentId.toString()); 
                dijit.byId("userTicketPriority").set("value", ticket.priority.toString()); 
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
                if(data.ticketDetailUserType == "Admin") {
                    userTypeValue = translator.common.staff;
                    div.className = 'chat_staff span12';
                } else if(data.ticketDetailUserType == "Client") {
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
                para.innerHTML = dojox.html.entities.encode(data.ticketDetailContent.replace(/\n/g, "<br />"));

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
    'reply': function() {          
        var supportTicketReplyStore = new JsonRest({
            target: core.getContextPath()+"/api/support/ticket/reply"
        });
        dijit.byId('viewTicketButton').set('style', {display: 'none'});
        dojo.byId("viewTicketLoader").style.display = "block";
        
        if(dojo.byId("replyTicketContent").value == ""|| dojo.byId("replyTicketContent").value == null) {
            registry.byId("userToaster").setContent(translator.common.message.enterContent, "error");
            registry.byId("userToaster").show();
            dijit.byId('viewTicketButton').set('style', {display: 'block'});
            dojo.byId("viewTicketLoader").style.display = "none";
        } else {
            supportTicketReplyStore.add({
                department:  dijit.byId("supportAdminDepWidget").getValue(),
                state:  "OPEN",
                subject:  dojox.html.entities.encode(dijit.byId("replyTicketSubject").getValue()),
                content:  dojox.html.entities.encode(dojo.byId("replyTicketContent").value),
                userType: "Client",
                ticketId: dojo.byId("ticketId").value,
                priority:  dijit.byId("userTicketPriority").getValue(),
            }).then(function(data) {                
                dojo.forEach(data, function(resultData) {
                    if(resultData == "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.added, "message");
                        registry.byId("userToaster").show();    
                        core.router.go("#/user/support/tickets");
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("userToaster").show();
                    }
                }); 
                dijit.byId('viewTicketButton').set('style', {display: 'block'});
                dojo.byId("viewTicketLoader").style.display = "none";
            });
        }           
    },
    'populateTicketDetails' : function() {  
        var supportGetTicketStore = new JsonRest({
            target: core.getContextPath()+"/api/support/ticket/"
        });        
        var id = dojo.byId("ticketId").value;
        
        supportGetTicketStore.get(id).then(function(data) {
            dojo.forEach(data, function(ticket) {
                dojo.byId("ticAcName").innerHTML = ticket.account;
                dojo.byId("ticDate").innerHTML = ticket.date;
                dojo.byId("ticId").innerHTML = ticket.id;
                dojo.byId("ticState").innerHTML =  ticket.state;               
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
                if(data.ticketDetailUserType == "Admin") {
                    userTypeValue = translator.common.staff;
                    div.className = 'chat_staff span12';
                } else if(data.ticketDetailUserType == "Client") {
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
                para.innerHTML = dojox.html.entities.encode(data.ticketDetailContent.replace(/\n/g, "<br />"));

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
    'init' : function() {
        var supportListDepartmentStore = new JsonRest({
            target: core.getContextPath()+"/api/support/department/list"
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
            onChange: function() {
                Tickets.getTicket();
            }
        }, "ticketDepList");   
        
    },
    'populateValues': function() {  
        
        if(dijit.byId("ticketGridWidget")) {
            dijit.byId("ticketGridWidget").destroyRecursive();
        }
        
        
        var supportListTicketStore = new JsonRest({
            target: core.getContextPath()+"/api/support/ticket/list"
        });
        
        dojo.byId("ticketGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>"; 
        
        
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': translator.common.ticket.ticketId, 'field': 'id', 'width': '100px', 'formatter': function(data) {
                        var node = "<a href='#/user/support/viewTicket/" + data + "'>" + data + "</a>";
                        return node;
                    }
                },
                {'name': translator.common.ticket.subject, 'field': 'subject', 'width': '200px'},
                {'name': translator.common.ticket.department, 'field': 'department', 'width': '200px', datatype:"string",
                            // Declare that we need the ComboBox for suggestions
                           autoComplete: true,
                           // Configure the ComboBox, so that it does not auto-complete our input
                           dataTypeArgs: {
                               autoComplete: false
                }},
                {'name': translator.user.grid.instance.layout.status, 'field': 'state', 'width': '100px',  datatype:"string",
                            // Declare that we need the ComboBox for suggestions
                           autoComplete: true,
                           // Configure the ComboBox, so that it does not auto-complete our input
                           dataTypeArgs: {
                               autoComplete: false}, 'formatter' : function(data) {
                           
                                if(data == "IN_PROGRSS") {
                                    return "<div class='ticket-stat-inprogress overflowLabel'>"+translator.common.message.inProgress+"</div>";
                                } else if(data == "OPEN") {
                                   return "<div class='ticket-stat-open overflowLabel'>"+translator.common.open+"</div>";
                                }else if(data == "CLOSE") {
                                    return "<div class='ticket-stat-closed overflowLabel'>"+translator.common.close+"</div>";
                                }else if(data == "ON_HOLD") {
                                    return "<div class='ticket-stat-onhold overflowLabel'>"+translator.common.onHold+"</div>";
                                }
                }}, 
                {'name': translator.common.ticket.priority, 'field': 'priority', 'width': '100px', datatype:"string",
                            // Declare that we need the ComboBox for suggestions
                           autoComplete: true,
                           // Configure the ComboBox, so that it does not auto-complete our input
                           dataTypeArgs: {
                               autoComplete: false
                }, 'formatter': function(data) {                         
                                             
                        var element =  "";                    
                        if(data == "LOW") {                         
                            element =  "<span class='greenColor'>"+translator.common.ticket.low+"</span>";
                        } else if(data == "HIGH") {                        
                            element =  "<span class='redColor'>"+translator.common.ticket.high+"</span>";
                        } else if(data  == "NORMAL") {
                            element =  "<span class='orangeColor'>"+translator.common.ticket.normal+"</span>";
                        }
                        return element;  
                }}, 
                {'name': translator.common.ticket.postedDate, 'field': 'date', 'width': '150px','formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
                }},
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter' : function(data) {
                        
                        if(data.state != "CLOSE") {
                            return new dijit.form.Button({label: translator.common.close, 
                                "class":"defaultbtn", onClick: function () {
                                    Tickets.showCloseTicket(data);
                            } });
                        }
                       
                }}
            ]
        ];
        
        supportListTicketStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                gridDataList.newItem({
                    id: el.id,
                    subject: el.subject,
                    date: el.date,
                    state: el.state,
                    priority: el.priority,
                    department : el.department,
                    action : {'ticketId' : el.id, 'department' : el.departmentId.toString(), 'subject': el.subject, 'state': el.state}
                });
            });
            
            dojo.byId("ticketGrid").innerHTML = "";
            
            var ticGrid = new EnhancedGrid({
                id: 'ticketGridWidget',
                "class":"span12",
                store: gridDataList,
                structure: gridLayout,
                autoHeight: true,
                plugins: {
                    pagination: {
                        pageSizes: ["3", "5", "10", translator.common.all],
                        description: true,
                        sizeSwitch: true,
                        pageStepper: true,
                        gotoButton: true,
                        /*page step to be displayed*/
                        maxPageStep: 4,
                        /*position of the pagination bar*/
                        position: translator.common.bottom
                    }
                }
            });
                
            ticGrid.placeAt("ticketGrid");
            ticGrid.startup();
                             
        });
        
        
    },
    'getTicket' : function() {  
        var instanceData = {
            items: []
        }; 
        var ticGrid = dijit.byId("ticketGridWidget");
        var ticDataList = new ItemFileWriteStore({data: instanceData}); 
        
        var supportListTicketStore = new JsonRest({
            target: core.getContextPath()+"/api/support/ticket/list"
        });
                
        supportListTicketStore.query({department: dijit.byId("ticketDepWidget").getValue(), state:dijit.byId("ticketState").getValue()}).then(function(data) {
            dojo.forEach(data, function(el) {
                ticDataList.newItem({
                    id: el.id,
                    subject: el.subject,
                    date: el.date,
                    state: el.state,
                    priority: el.priority ,
                    department : el.department,
                    action : {'ticketId' : el.id, 'department' : el.departmentId.toString(), 'subject': el.subject, 'state': el.state}
                });
            });
            
            ticGrid.setStore(ticDataList); 
            
        });
    },
    'closeTicket' : function() {                
        var supportTicketReplyStore = new JsonRest({
            target: core.getContextPath()+"/api/support/ticket/reply"
        });
        
        if(dojo.byId("closeTicketContent").value == "" ||  dojo.byId("closeTicketContent").value == null) {
            registry.byId("userToaster").setContent(translator.common.message.enterContent, "error");
            registry.byId("userToaster").show();
        } else {
            dijit.byId('sendTicketButton').set('style', {display: 'none'});
            dojo.byId("sendTicketLoader").style.display = "inline";
            supportTicketReplyStore.add({
                    department:  dojo.byId("departmentId").value,
                    state:  "CLOSE",
                    subject:  dojox.html.entities.encode(dojo.byId("subject").value),
                    content:  dojox.html.entities.encode(dojo.byId("closeTicketContent").value),
                    userType: "Client",
                    ticketId: dojo.byId("ticketId").value
            }).then(function(data) {
                dijit.byId('sendTicketButton').set('style', {display: 'inline'});
                dojo.byId("sendTicketLoader").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    if(resultData == "OK") {
                        dijit.byId("closeTicketDialog").hide();
                        registry.byId("userToaster").setContent(translator.common.ticket.ticketClosed, "message");
                        registry.byId("userToaster").show();

    //                    dojo.byId("replyTicketContent").value = "";
                         Tickets.populateValues();
                    } else {
                        dijit.byId("closeTicketDialog").hide();
                        registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("userToaster").show();
                    }
                });                
            });
        }
        
    },
    'showCloseTicket' : function(data) {
                
        dijit.byId("closeTicketDialog").show();
        dojo.byId("closeTicketContent").value = "";
        dojo.byId("ticketId").value = data.ticketId;
        dojo.byId("departmentId").value = data.department;
        dojo.byId("subject").value = data.subject;
    }, 
    'closeTicketDialog' : function() {
        dijit.byId("closeTicketDialog").hide();
    }
};
var AddTicket = {
    'init' : function() {
       
    },
    'populateValues': function() {  
        
        var supportListDepartmentStore = new JsonRest({
            target: core.getContextPath()+"/api/support/department/list"
        });
        
        var depOptions = {            
            identifier: 'id',
            label: 'name',
            items: []
        };
        var depList = new ItemFileWriteStore({data: depOptions});
        supportListDepartmentStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                depList.newItem({id: el.id, name: el.name});
            });
        });   
        
         var depWidget = new Select({
            id: "supportDepWidget",
            name: "department",
            value: "option",        
            store: depList
        }, "supportDepList");    
        
    },
    'add': function() {  
        
        var supportDefaultReplyStore = new JsonRest({
            target: core.getContextPath()+"/api/support/ticket/add"
        });
        
        var firstNode = ""
        var node = dojo.byId("addTicketForm");
        var widget = dijit.registry.findWidgets(node);        
        var status =  true;
        dojo.forEach(widget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
                status = false;
            }
        });
        if(status == true) {
        
                
            if(dojo.byId("addTicketContent").value == "" || dojo.byId("addTicketContent").value == null || dojo.byId("addTicketContent").value == "null") {

                    registry.byId("userToaster").setContent(translator.common.message.enterContent, "error");
                    registry.byId("userToaster").show();
            } else {
                
                dijit.byId('addTicketButton').set('style', {display: 'none'});
                dojo.byId("addTicketLoader").style.display = "inline";
                dijit.byId("cancelTicketButton").set("disabled", true);
                supportDefaultReplyStore.add({
                        department:  dijit.byId("supportDepWidget").getValue(),
                        subject:  dojox.html.entities.encode(dijit.byId("addTicketSubject").getValue()),
                        content:  dojox.html.entities.encode(dojo.byId("addTicketContent").value),
                        priority:  dijit.byId("ticketPriority").getValue(),
                        state: "OPEN"
                }).then(function(data) {

                    dojo.forEach(data, function(resultData) {
                        if(resultData == "OK") {
                            registry.byId("userToaster").setContent(translator.common.ticket.ticketAdded, "message");
                            registry.byId("userToaster").show();
        //                    dijit.byId("addTicketForm").reset();
        //                    dojo.byId("addTicketContent").value = "";
                            core.router.go("#/user/support/tickets");
                        } else {
                            registry.byId("userToaster").setContent(translator.common.ticket.addTicketError, "error");
                            registry.byId("userToaster").show();
                        }
                    });                
                    dijit.byId('addTicketButton').set('style', {display: 'inline'});
                    dojo.byId("addTicketLoader").style.display = "none";
                    dijit.byId("cancelTicketButton").set("disabled", false);
                });  
            }
        }
        
    
    
    },
    'cancel': function() {  
        dijit.byId("addTicketForm").reset();   
        dojo.byId("addTicketContent").value = "";
    }
              
    
}; 

window.AddTicket = AddTicket;
