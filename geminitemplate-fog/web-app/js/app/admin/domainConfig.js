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
    "dojox/grid/DataGrid",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dojo/currency",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "dijit/form/DateTextBox",
    "dijit/layout/TabContainer",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dijit/Editor",
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
],
function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
ItemFileWriteStore, Memory, DataGrid, ContentPane, Source, MultiSelect, dom, win, localeCurrency) {
    window.query = query;
    window.translator = translator;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Memory = Memory;
    window.Select = Select;
    window.ContentPane = ContentPane;
    window.DataGrid = DataGrid;
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;
    window.isAdvGWSelected = 0;
    window.loadedADVGw = false;
    window.localeCurrency = localeCurrency;
    controller({
        name: "domain",
        module: "admin",
        filePath: "/js/app/admin/domainConfig.js",
        layout: {
            name: "domain",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {
            
            var language = dijit.byId("domainConfigForm");
            if (language) {
                dijit.byId("domainConfigForm").destroyRecursive();
            }
            if (dijit.byId("doaminUpdateConfirmationdialog")) {
                dijit.byId("doaminUpdateConfirmationdialog").destroyRecursive();
            }  
            
            
            core.ui.loadTemplate("domainConfig", core.ui.getContentId());
            DomainConfig.populateValues();
        }),
        
    });
});

var DomainConfig = {
    'init': function() {
    },
    'populateValues': function() {
        
        if(dijit.byId("tierNetworkOffering")) {
            dijit.byId("tierNetworkOffering").destroyRecursive();
        }
        
        var domainOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var domainFileStoreList = new ItemFileWriteStore({data: domainOptions});
        
        var domainRestStore = new JsonRest({
            target: core.getContextPath() + "/api/domain"
        });
        
        var defaultDomainId = "";
        
        domainRestStore.query().then(function(data) {
            if (!data || data.length == 0) {
                domainFileStoreList.newItem({id: "", name: translator.common.noDomain});
            } else {
                domainFileStoreList.newItem({id: "", name: ""});
                dojo.forEach(data, function(resultData) {
                    domainFileStoreList.newItem({id: resultData.referenceId, name: resultData.name});
                    
                    if(resultData.isDefault === true) {
                        defaultDomainId = resultData.referenceId;
                    }
                });
            }
            var domainWidget = new Select({
                id: "domainList",
                name: "domainList",
                store: domainFileStoreList,
                sortByLabel: false,
                required:true

            });
            domainWidget.placeAt("domainListDiv");
            domainWidget.startup();
            dijit.byId("domainList").set("value", defaultDomainId)
        });
              

    },
    'showUpdateConfirmationDialog': function() {
        
        if(dijit.byId("domainList").getValue()) {
            dijit.byId("doaminUpdateConfirmationdialog").show();
        } else {
            registry.byId('appToaster').setContent(translator.common.message.selectDomain, 'error');
            registry.byId('appToaster').show();
        }
        
    },
    'update': function() {        
        var domainRestStore = new JsonRest({
            target: core.getContextPath() + "/api/domain/updateDefaultDomain/"
        });
        dijit.byId("doaminUpdateConfirmationdialog").hide();
        domainRestStore.put({
            id: dijit.byId("domainList").getValue(),
        }).then(function(resultData) {
            if (resultData[0] === "OK") {
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    },
    'closeUpdate': function() {
        dijit.byId("doaminUpdateConfirmationdialog").hide();
    },
}; 
