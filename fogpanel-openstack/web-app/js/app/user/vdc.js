'use strict';
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
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/Tufte",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojo/_base/connect",  
    "dijit/Menu", 
    "dijit/MenuItem", 
    "dijit/form/ComboButton", 
    "dijit/form/DropDownButton", 
    "dijit/DropDownMenu", 
    "dijit/Tooltip",
    "dojox/charting/plot2d/Markers",
    "dijit/form/HorizontalRule",
    "dijit/form/HorizontalRuleLabels",
    "dijit/form/HorizontalSlider",    
    "dojo/query",
    "dojo/dom-class",
    "dijit/layout/TabContainer",
    "dojox/charting/widget/Chart2D",
    "dojox/charting/themes/Claro",
    "dijit/form/Button",
    "dijit/layout/ContentPane",
    "dojox/form/PasswordValidator",
    "dojo/on",
    "dojo/query",
    "dojox/validate/regexp",    
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "FogPanel/VolumeListItem",
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
    "List/ListItem"
], function(dojo, i18n, translator, dijit, JsonRest, registry, FilteringSelect, Select,ItemFileWriteStore, DataGrid, EnhancedGrid, HorizontalSlider, domConstruct, Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, 
    Magnify, theme, ColumnsPlot, Highlight, connect, Menu, MenuItem, ComboButton, DropDownButton, DropDownMenu) {
    window.translator = translator;
    window.JsonRest = JsonRest;   
    window.Menu = Menu;
    window.MenuItem = MenuItem;
    window.DropDownButton = DropDownButton;
    window.DropDownMenu = DropDownMenu;
    window.Magnify = Magnify;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Select = Select;
    window.DataGrid = DataGrid;
    window.domConstruct = domConstruct;
    window.domConstruct = domConstruct;
    window.Lines = Lines;
    window.HorizontalSlider = HorizontalSlider;
    window.Chart = Chart;
    window.Pie = Pie;
    window.PlotKitGreen = PlotKitGreen;
    window.Tooltip = Tooltip;
    window.MoveSlice = MoveSlice;
    window.theme = theme;
    window.ColumnsPlot = ColumnsPlot;
    window.Highlight = Highlight;
    window.connect = connect;
    window.currentRuleId = "";  
    window.compCount = 0;
    window.createVMCurrentZone = "";
    window.zoneTempRefId = "";
    window.isTierOptionEnabled = false;
    controller({ 
        name:"vdc",
        module: "user",
        filePath: "/js/app/user/vdc.js",
        layout: {
            name: "",
            containerId: "content"
        },
        scaffold: false
    },
    {   
        "dashboard": action(function() {            
            core.ui.loadTemplate("vdcDashboard", core.ui.getContentId());                          
        })
    });     
});


