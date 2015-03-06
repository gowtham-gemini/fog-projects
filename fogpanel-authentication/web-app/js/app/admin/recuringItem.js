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
    ],
    function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
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
        window.EnhancedGrid = EnhancedGrid;
        window.DataGrid = DataGrid;
        window.Source = Source;
        window.MultiSelect = MultiSelect;
        window.dom = dom;
        window.win = win;
        
        controller ({
            name: "recurringItem",
            module: "admin",
            filePath: "/js/app/admin/recuringItem.js",
            layout: {
                name: "recurringItem",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {
                
//                var currentPage = dijit.byId("updateRecurringItem");
//                if(currentPage) {
//                    dijit.byId("updateRecurringItem").destroyRecursive();            
//                } 
//                core.ui.loadTemplate("recurringItem", core.ui.getContentId()); 
//                RecurringItem.init();
//                RecurringItem.populateValues();
            
            }),
//            "add": action(function() {
//                var addRecurringItemForm = dijit.byId("addRecurringItemForm");
//                if(addRecurringItemForm) {
//                    addRecurringItemForm.destroyRecursive();
//                }
//                core.ui.loadTemplate("addRecurringItem", core.ui.getContentId()); 
//                AddRecurringItem.init();
//                AddRecurringItem.populateValues();
//            }),
            "edit": action(function(id) { 
                var editForm = dijit.byId("updateRecurringItemForm");
                if(editForm) {
                    editForm.destroyRecursive();
                    dijit.byId("recItemEditConformationDialog").destroyRecursive();
                }
                core.ui.loadTemplate("editRecurringItem", core.ui.getContentId()); 
                EditRecurringItem.init(id);
                EditRecurringItem.populateValues();
                
            }),
            "delete": action(function(id) {                
//                core.ui.loadTemplate("recurringItem", core.ui.getContentId()); 
//                RecurringItem.init();
//                RecurringItem.populateValues();
                DeleteRecurringItem.init(id);
                DeleteRecurringItem.populateValues();
            })          
            
            
        });
    });
    
var DeleteRecurringItem = {
     _currentId:"",
     _recurringItemStore:"",
    init : function(currentId) {
       this._currentId = currentId;
       this._recurringItemStore = new JsonRest({
           target: core.getContextPath()+"/api/recurringItem/"
        });
    },
    populateValues : function() {                
        this._recurringItemStore.remove(this._currentId).then(function(data) {
            if(data == "OK") {                    
                    registry.byId("appToaster").setContent(translator.common.message.itemDeleted,"message");
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/billing/recurringItem");
            } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
            } 
        });        
    }      
};
var EditRecurringItem = {
    _currentId:"",
    _recurringItemStore:"",
    init: function(currentId) {
        this._currentId = currentId;
        this._recurringItemStore = new JsonRest({
            target: core.getContextPath()+"/api/recurringItem/"
        });
        
    },
    populateValues : function() {
        this._recurringItemStore.get(this._currentId).then(function(resultData) {
            dojo.forEach(resultData, function(data) {
                dojo.byId("recurringItemAccountName").innerHTML =  data.account;
                dijit.byId("recurringItemName").setValue(data.name);
                dijit.byId("recurringItemAmount").setValue(data.amount);
                dijit.byId("recurringItemTaxPercentage").setValue(data.taxPercent); 
            });
                  
        });
    },
    updateShow : function() {  
         dijit.byId("recItemEditConformationDialog").show();
    },
    closeUpdate : function() {  
         dijit.byId("recItemEditConformationDialog").hide();
    },
     update : function() {          
        var name = dijit.byId("recurringItemName");
        var amount = dijit.byId("recurringItemAmount");
        var taxPercentage = dijit.byId("recurringItemTaxPercentage");
        
        var pageNode = dojo.byId("updateRecurringItemPage");
        var nodeWidget = dijit.registry.findWidgets(pageNode);
        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        var recurringItemUpdateStore = new JsonRest({
            target: core.getContextPath()+"/api/recurringItem/"
        });
        
        recurringItemUpdateStore.put({
            id:this._currentId,
            amount: amount.value,
            name: name.value,
            taxPercentage: taxPercentage.value
        }).then(function(data) {
            dojo.forEach(data, function(recurringItemData) {   
                if(recurringItemData.result == "OK") {
                    core.router.go("#/admin/billing/recurringItem");
                    dijit.byId("recItemEditConformationDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                    registry.byId("appToaster").show();                        
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("recItemEditConformationDialog").hide();
                    dijit.byId("updateRecurringItemForm").reset();
                } 
            });        
        });
    },
    cancel : function() {
//        dijit.byId("updateRecurringItem").hide();
        dijit.byId("updateRecurringItemForm").reset();
    }
};


var RecurringItem = {
    _recurringItemStore : "",
    init : function() {
    
        this._recurringItemStore = new JsonRest({
            target: core.getContextPath()+"/api/recurringItem/"
        });
        
    },     
   
    
    populateValues : function() {

        if(dijit.byId("recurrinItemGridWidget")) {
            dijit.byId("recurrinItemGridWidget").destroyRecursive();
        }
        var gridData = {
            items: []
        };

        var gridDataList = new ItemFileWriteStore({data: gridData});

        this._recurringItemStore.query().then(function(data) {
             dojo.forEach(data, function(recurringItemData) {   
                gridDataList.newItem({
                    id: recurringItemData.id,
                    name:recurringItemData.name,
                    account: recurringItemData.account,
                    amount: recurringItemData.amount, 
                    taxPercent: recurringItemData.taxPercent, 
                    cycle: recurringItemData.cycle, 
                    uses: recurringItemData.uses, 
                    taxAmount: recurringItemData.taxAmount,
                    totalAmount: recurringItemData.totalAmount,                     
                    actName:recurringItemData.name,
                    action: recurringItemData.id
                });
            });
        }); 
        var gridLayout = [[
            {'name': 'Id', 'field': 'id', 'hidden': 'true'},
            {'name': 'actName', 'field': 'actName', 'hidden': 'true'},
            {'name': 'Name', 'field': 'name', 'width': '100%'},
            {'name': 'Account', 'field': 'account', 'width': '100%'},
            {'name': 'Amount', 'field': 'amount', 'width': '100%'},
            {'name': 'Tax Percent', 'field': 'taxPercent', 'width': '100%'},
            {'name': 'Billing Cycle', 'field': 'cycle', 'width': '100%'},
            {'name': 'No. of Uses', 'field': 'uses', 'width': '100%'},
            {'name': 'Tax Amount', 'field': 'taxAmount', 'width': '100%'},
            {'name': 'Total Amount', 'field': 'totalAmount', 'width': '100%', 'formatter': function(data) {                         
                                             
                        return  "<span class='orangeColor'>" + data + "</span>";
                }
            },
            {'name': 'Action', 'field': 'action',
                     'formatter': function(data) {
                        var html = "<a href='#/admin/recurringItem/edit/"+data+"' title='Edit'><i class='icon-edit'></i></a>" +
                                    "<a href='#/admin/recurringItem/delete/"+data+"' class='offset1' title='Delete'><i class='icon-remove'></i></a>";
                      
                       return html;
                },'width': '100%'}
        ]];             
        var recurringItemGrid = new EnhancedGrid({
            id: 'recurrinItemGridWidget',
            store: gridDataList,
            structure: gridLayout,
            autoHeight: true,
            plugins: core.getGridConfig().plugins
        });
        recurringItemGrid.placeAt("recurringItemListGrid");
        recurringItemGrid.startup();
    }  
    
    
        
        
};
var AddRecurringItem = {
    _invoiceStore : "",
    _configRestStore: "",
    _accountStore: "",
    _accountListStore: "",
    init : function() {
    
        this._invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/"
        }); 
        
        this._accountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"
        });
        
        this._accountListStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
    },
    populateValues : function() {                
        var sel = dojo.byId('invoiceItemAccountList');
        this._accountListStore.query().then(function(data){
            dojo.forEach(data, function(account) {
                var opt = document.createElement('option');
                opt.innerHTML = account.userName;
                opt.value = account.id;
                sel.appendChild(opt);
            });
        });     
    },  
    
    add : function() { 
        var name = dijit.byId("recurringItemName");
        var amount = dijit.byId("recurringItemAmount");
        var taxable = dijit.byId("recurringItemTaxEnabled").checked;
        var isRecurring = true;
        var taxPercentage = dijit.byId("recurringItemTaxPercentage");
        var invoiceItemForAccount = dijit.byId("forAccount");
        var accountList = dojo.byId("invoiceItemAccountList");
        var recurringItemBillingCycles = dijit.byId("recurringItemBillingCycles");
        
        var selectedAccountArray = new Array();
        var j;
        var countUser = 0;
        for (j=0; j < accountList.options.length; j++) {
          if (accountList.options[j].selected) {
            selectedAccountArray[countUser] = accountList.options[j].value;
            countUser++;
          }
        }
        this._invoiceStore.add({
            amount: amount.value,
            name: name.value,
            taxable: taxable,
            taxPercentage: taxPercentage.value,
            account: selectedAccountArray.toString(),
            isRecurring: isRecurring,
            isAllAccount : invoiceItemForAccount.value,
            billingCycles : recurringItemBillingCycles.value
        }).then(function(data) {
            if(data == "OK") {
                registry.byId("appToaster").setContent("Item Added","message");
                registry.byId("appToaster").show();
                dijit.byId("addRecurringItemForm").reset();
                core.router.go("#/admin/recurringItem");
            } else {
                registry.byId("appToaster").setContent("Failed!", "error");
                registry.byId("appToaster").show();
            }             
        });
    },
    cancel : function() { 
        dijit.byId("addRecurringItemForm").reset();
        core.router.go("#/admin/recurringItem");
    },
    showAccountList : function(value) {

        if(value == "ALL") {
            dojo.byId("accountListDiv").style.display = "none";
        } else {
            dojo.byId("accountListDiv").style.display = "block";
        }
    },
    enableTax :function(taxEnabled) {
        if(taxEnabled.checked == "true" || taxEnabled.checked== true) {
            dojo.byId("recurringItemTaxPercentageDiv").style.display = "block";
        } else {
            dojo.byId("recurringItemTaxPercentageDiv").style.display = "none";
        }
    }
};


window.RecurringItem = RecurringItem;
window.EditRecurringItem = EditRecurringItem;
window.DeleteRecurringItem = DeleteRecurringItem;