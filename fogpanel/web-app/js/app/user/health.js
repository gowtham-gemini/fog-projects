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
function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
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
    controller ({
        name: "health",
        module: "user",
        filePath: "/js/app/user/health.js",
        layout: {
            name: "health",
            containerId: "content"
        },	
        scaffold: false
    },
    {
        "index": action(function() {
            var viewEventPage = dijit.byId("viewEventPage");
            if(viewEventPage) {
                dijit.byId("viewEventPage").destroyRecursive();
            } 
            core.ui.loadTemplate("healthInfo", core.ui.getContentId()); 
            CalendarInfo.init();
            CalendarInfo.populateValues();
        })               
    });
});

var CalendarInfo = {
    init : function() {
    },
    populateValues : function() {       
        var currentZoneID = dojo.byId("selectedANZoneID").value;        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {            
            var billingAlerts = new JsonRest({
                target: core.getContextPath()+"/api/notification/billingAlerts"
            }); 
            var  count = 0;   
            var eventNo = 1;
            var eventData = [];  
            billingAlerts.query().then(function(data) {
                dojo.forEach(data, function(alertData) {
                    dojo.forEach(alertData.notification, function(notificationData) {
                        var date = notificationData.calendarDate.split("-");  
                        var year =  parseInt(date[2]);
                        var month =  parseInt(date[1])-1;
                        var day =  parseInt(date[0]);
                        eventData[count] = {
                            id: notificationData.id,
                            summary: translator.common.event+"-" + eventNo,
                            startTime:  new Date(year, month, day, 0),
                            endTime: new Date(year, month, day, 23),
                            data: notificationData.description,
                            currentDate : notificationData.date
                        };
                        count++;  
                        eventNo++;
                    });
                    CalendarInfo.loadCalander(eventData);
                });
            });            
        } else {
            var notificationAlerts = new JsonRest({
                target: core.getContextPath()+"/api/notification/healthByZone"
            }); 
            var  count = 0;   
            var eventNo = 1;
            var eventListData = [];  
            notificationAlerts.query({zoneReferenceId: currentZoneID}).then(function(data) {                
                dojo.forEach(data, function(notificationData) {
                    var date = notificationData.calendarDate.split("-");  
                    var year =  parseInt(date[2]);
                    var month =  parseInt(date[1])-1;
                    var day =  parseInt(date[0]);
                    eventListData[count] = {
                        id: notificationData.id,
                        summary: translator.common.event+"-" + eventNo,
                        startTime:  new Date(year, month, day, 0),
                        endTime: new Date(year, month, day, 23),
                        data: notificationData.description,
                        currentDate : notificationData.date
                    };
                    count++;  
                    eventNo++;
                });
                CalendarInfo.loadCalander(eventListData);              
            });    
        }
    },
    loadCalander : function(eventData) {                     
        dojo.byId("maintainCalendarDiv").innerHTML = "";
        var  notifiCalendar = new Calendar({
            onItemClick:function (e) {
                CalendarInfo.openEvent(e);
            },        
            store: new Observable(new Memory({data: eventData})),
            dateInterval: "month",
            style: "position:relative;height:800px"
        }).placeAt("maintainCalendarDiv"); 
        notifiCalendar.startup();  
    },
    openEvent : function(event) {
        dijit.byId("viewEventPage").show();
        dojo.byId("eventDate").innerHTML = event.item.currentDate;
        dojo.byId("eventDescription").innerHTML = event.item.data;        
    }
};

window.CalendarInfo = CalendarInfo;