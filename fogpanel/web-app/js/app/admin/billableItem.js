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
    "dojox/grid/EnhancedGrid",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dijit/form/DateTextBox",
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
    "dijit/Dialog"
],
        function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
                ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {
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
            window.EnhancedGrid = EnhancedGrid;
            window.Source = Source;
            window.MultiSelect = MultiSelect;
            window.dom = dom;
            window.win = win;

            controller({
                name: "billableItem",
                module: "admin",
                filePath: "/js/app/admin/billableItem.js",
                layout: {
                    name: "billableItem",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "index": action(function() {
                    core.ui.loadTemplate("billableItem", core.ui.getContentId());
                    BillableItem.init();
                    BillableItem.populateValues();
                }),
                "edit": action(function(id) {
                    var editForm = dijit.byId("billableItemForm");
                    if (editForm) {
                        editForm.destroyRecursive();
                    }
                    core.ui.loadTemplate("editBillableItems", core.ui.getContentId());
                    EditBillableItem.init(id);
                })

            });
        });
var EditBillableItem = {
    _billableItemStore: "",
    _taxStore: "",
    _currentPageId: "",
    init: function(currentPageId) {
        this._currentPageId = currentPageId;
        this._billableItemStore = new JsonRest({
            target: core.getContextPath() + "/api/billableItem/"
        });
        this._taxStore = new JsonRest({
            target: core.getContextPath() + "/api/tax/"
        });
//        var currentTaxId;
        this._billableItemStore.get(this._currentPageId).then(function(billableItemData) {
            dojo.forEach(billableItemData, function(data) {
                EditBillableItem.populateValues(data.tax.id, data.tax.name);
            });
        });
    },
    populateValues: function(currentTaxId, currentTaxName) {

        var taxOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: currentTaxId, name: currentTaxName}]
        };
        var taxListStore = new ItemFileWriteStore({data: taxOptions});
        this._taxStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                if (el.id == currentTaxId) {
                    return;
                } else {
                    taxListStore.newItem({id: el.id, name: el.name});
                }
            });
        });
        var taxWidget = new FilteringSelect({
            id: "taxList",
            value: currentTaxId,
            store: taxListStore

        }, "taxListContainer");
        taxWidget.startup();

        this._billableItemStore.get(this._currentPageId).then(function(billableItemData) {
            dojo.forEach(billableItemData, function(data) {
                if (data.id == "1" || data.id == "13") {
                    dijit.byId("isDiscountable").set("disabled", false);
                } else {
                    dojo.byId("isDiscountableDiv").style.display = "none";
                    dojo.byId("isDiscountableDivInfo").style.display = "none";
                    dijit.byId("isDiscountable").set("disabled", true);
                }
                dijit.byId("billableItemName").setValue(data.name);
                dijit.byId("billableItemEnabled").set("checked", data.enabled);
                dijit.byId("isDiscountable").set("checked", data.isDiscountable);
//              dijit.byId("hasZone").checked = data.hasZone;
//              dijit.byId("taxList").set("value", data.tax.id)   
            });
        });
    },
    update: function() {
        var name = dijit.byId("billableItemName");
        var enabled = dijit.byId("billableItemEnabled");
        var tax = dijit.byId("taxList");
        var isDiscountable = dijit.byId("isDiscountable");
//        var hasZone = dijit.byId("hasZone");
        dijit.byId('billableItemButton').set('style', {display: 'none'});
        dojo.byId("billableItemLoader").style.display = "block";
        this._billableItemStore.add({
            id: this._currentPageId,
            taxId: tax.value,
            enabled: enabled.checked,
            isDiscountable: isDiscountable.checked
//            hasZone: hasZone.checked
        }).then(function(resultData) {
            dojo.forEach(resultData, function(data) {
                dijit.byId('billableItemButton').set('style', {display: 'block'});
                dojo.byId("billableItemLoader").style.display = "none";
                if (data.result == "OK") {
                    registry.byId('appToaster').setContent(translator.common.message.updateSuccess, 'message');
                    registry.byId('appToaster').show();
                    core.router.go("#/admin/billableItem");
                } else {
                    registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                    registry.byId('appToaster').show();
                }

            });
        });

    },
    cancel: function() {
        core.router.go("#/admin/billableItem");
    }
};

var BillableItem = {
    _billableItemStore: "",
    _taxStore: "",
    init: function() {

        this._billableItemStore = new JsonRest({
            target: core.getContextPath() + "/api/billableItem/"
        });

        this._taxStore = new JsonRest({
            target: core.getContextPath() + "/api/tax/"
        });
    },
    populateValues: function() {

        if (dijit.byId("billDataGrid")) {
            dijit.byId("billDataGrid").destroyRecursive();
        }
        var billableItemData = {
            items: []
        };
        var billableItemList = new ItemFileWriteStore({data: billableItemData});
        var billDataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'field': 'taxId', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.taxName, 'field': 'taxName', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.billing.taxPercentage + "(%)", 'field': 'percentage', 'width': '300%', datatype: "string", autoComplete: true},
//                {'name': 'Customized', 'field': 'customized', 'width': '100%'},
//                {'name': 'Is Discountable', 'field': 'isDiscountable', 'width': '100%'},                 
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {

                        var html = "<a class='btn-flat success' href='#/admin/billableItem/edit/" + data + "'>" + translator.common.edit + "</a>";

                        return html;
                    }, 'width': '100%', datatype: "string", autoComplete: true}
            ]
        ];

        this._billableItemStore.query().then(function(data) {
            dojo.forEach(data, function(billData) {
                if(billData.nameKey == "common.billableItem.monthlySnapshot") {
                   
                } else if(billData.nameKey == "common.billableItem.monthlyVMSnapshot") {
                    
                } else {
                    billableItemList.newItem({
                        id: billData.id,
                        taxId: billData.taxId,
                        name: billData.name,
                        taxName: billData.taxName,
                        percentage: billData.taxPercentage,
    //                customized : billData.customized,
    //                isDiscountable : billData.isDiscountable,
                        action: billData.id
                    }); 
                }
            });
        });
        var billDataGrid = new EnhancedGrid({
            id: 'billDataGrid',
            "class" : "span12",
            store: billableItemList,
            structure: billDataLayout,
            autoHeight: true,
            plugins: core.getGridConfig().plugins
        });
        billDataGrid.placeAt("adminBillableItemList");
        billDataGrid.startup();
    }
};
window.BillableItem = BillableItem;
window.EditBillableItem = EditBillableItem;
