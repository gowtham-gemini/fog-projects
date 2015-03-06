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
    "dojox/grid/enhanced/plugins/IndirectSelection",
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
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
                ItemFileWriteStore, Memory, Observable, DataGrid, EnhancedGrid, IndirectSelection, Calendar, ContentPane, Source, MultiSelect, dom, win) {
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
            window.IndirectSelection = IndirectSelection;
            window.Calendar = Calendar;
            window.Source = Source;
            window.MultiSelect = MultiSelect;
            window.dom = dom;
            window.win = win;
            controller({
                name: "internetGateway",
                module: "user",
                filePath: "/js/app/user/internetGateway.js",
                layout: {
                    name: "internetGateway",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "list": action(function() {
                    core.ui.loadTemplate("internetGatewayList", core.ui.getContentId());
                    GatewayInfo.list();
                })
            });
        });

var GatewayInfo = {
    'list' : function() {
        
        var currentRegionId = dojo.byId("selectedRegionID").value ? dojo.byId("selectedRegionID").value : null;
        var currentMainVPC = ""
        if (dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "undefined") {
            currentMainVPC = ""
        } else {
            currentMainVPC = dojo.byId("selectedANVPCID").value;
        }
        
        var gateWaysListStore = new JsonRest({
            target: core.getContextPath() + "/api/internetGateway/list"
        });
        
        gateWaysListStore.query({id:currentRegionId}).then(function(data){
            dojo.forEach(data,function(resultData) {
                
            })
        })
        
        
    }
}
        
        