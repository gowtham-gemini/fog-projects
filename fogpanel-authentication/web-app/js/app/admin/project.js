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
    "dojox/form/CheckedMultiSelect",
    "dojo/data/ItemFileWriteStore",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
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
    "FogPanel/MultipleInvitation",
    "FogPanel/Wizard",
    "FogPanel/AccountStatus",
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
    "dijit/Dialog",
    "dijit/DropDownMenu",
    "dijit/MenuItem",
    "dojox/grid/enhanced/plugins/Menu",
    "dijit/popup",
    "dojo/_base/lang",
    "dojox/charting/widget/Chart2D",
    "dojox/charting/themes/Claro",   
    "dojo/dom-construct",
    "dojo/on",
    "FogPanel/VolumeListItem",
    "FogPanel/InstanceStatus",
    "dojox/widget/rotator/Pan",
    "dijit/form/CheckBox",
    "dijit/form/NumberSpinner",
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
],
function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,CheckedMultiSelect, ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win, 
Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, ClusteredColumns, Legend) {
    window.translator = translator;            
    window.query = query;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Select = Select;
    window.CheckedMultiSelect = CheckedMultiSelect;
    window.ContentPane = ContentPane;
    window.EnhancedGrid = EnhancedGrid;
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
    window.currentUserId = "";
    window.currentInvoiceId = "";
    controller({
        name: "project",
        module: "admin",
        filePath: "/js/app/admin/project.js",
        layout: {
            name: "project",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {
            if(dijit.byId("assignCloudEngineDialog")) {
               dijit.byId("assignCloudEngineDialog").destroyRecursive() 
            }
            core.ui.loadTemplate("projectList", core.ui.getContentId());
            projectInfo.init();
        }),
        
        
   });
});

var projectInfo = {
    
    init : function() {
        
        if (dijit.byId("groupDatas")) {
            dijit.byId("groupDatas").destroyRecursive();
        }
        
        var projectListStore = new JsonRest({
            target: core.getContextPath() + "/api/project/list"
        });
        
        var projectData = {
                items: []
        };
        
        dojo.byId("projectGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var projectDataList = new ItemFileWriteStore({data: projectData});
        var projectLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '10%'},
                {'name': translator.common.action, 'field': 'action', 'width': '10%','formatter':function(data) {
                        var menu = new DropDownMenu({style: "display: none;"});
                        var menuItem3 = new MenuItem({
                            label: translator.common.assignCloudEngine,
                            onClick: function() {
                                projectInfo.showDialog(data);
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
                    }
                },
                
            ]
        ];
        
        projectListStore.query().then(function(data){
            if(data.length === 0) {
                dojo.byId("noProjectsMessageBox").style.display = 'block'
                dojo.byId("projectGrid").innerHTML = ''
            } else {
                dojo.byId("noProjectsMessageBox").style.display = 'none'
                dojo.forEach(data, function(group) {
                    projectDataList.newItem({
                        id: group.id,
                        name: group.name,
                        action: group,
                        
                    });
                });
                dojo.byId("projectGrid").innerHTML = "";
                    var groupDataGrid = new EnhancedGrid({
                        id: 'groupDatas',
                        "class":"span12",
                        store: projectDataList,
                        structure: projectLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    groupDataGrid.placeAt("projectGrid");
                    groupDataGrid.startup();
            }
        })
        
    },
    
    showDialog : function(data) {
        var cloudEngineListStore = new JsonRest({
            target: core.getContextPath() + "/api/project/cloudEngines"
        });
        dojo.byId('projectId').value = data.id
        
        if(dijit.byId("cloudEngineWidget")){
           dijit.byId("cloudEngineWidget").destroyRecursive() 
        }
        
        var cloudEngineOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var cloudEngineList = new ItemFileWriteStore({data: cloudEngineOptions});
        
        cloudEngineListStore.query().then(function(data){
            dojo.forEach(data, function(el) {
                cloudEngineList.newItem({id: el.id, name: el.name})
            })
        })
        
        var cloudEngineWidget = new CheckedMultiSelect({
            id: "cloudEngineWidget",                    
            store: cloudEngineList,
            multiple:"true",
            
//            onChange: function() {
//                    cloudEngineInfo.setList(this);   
//                }
        })
        
        cloudEngineWidget.placeAt("cloudEngine");
        cloudEngineWidget.startup();
        
        dijit.byId("assignCloudEngineDialog").show();
        dijit.byId("projectName").setValue(data.name)
        
    }
}
        
var cloudEngineInfo = {
    assignProject: function() {
        var cloudEngine = dijit.byId("cloudEngineWidget").value
        var projectId = dojo.byId("projectId").value
        
        
        dijit.byId("assignCloudEngineDialog").hide();
        
        var cloudEngineAssignStore = new JsonRest({
            target: core.getContextPath() + "/api/project/assign/cloudEngine"
        });
        
        cloudEngineAssignStore.add({
                projectId: projectId,
                cloudEngine:cloudEngine
            }).then(function(data) {
                if(data[0] === "OK"){
                    registry.byId("appToaster").setContent(translator.common.message.addedSuccess, "message");
                }
            }) 
        
    },
    
    closeCloudEngineDialog : function() {
        dijit.byId("assignCloudEngineDialog").hide();
    },
    
    
    
}


