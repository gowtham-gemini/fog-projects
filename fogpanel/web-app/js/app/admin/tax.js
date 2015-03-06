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
    "dojo/NodeList-traverse",
    "dijit/form/DateTextBox",
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
        window.DataGrid = DataGrid;
        window.EnhancedGrid = EnhancedGrid;
        window.Source = Source;
        window.MultiSelect = MultiSelect;
        window.dom = dom;
        window.win = win;
        
        controller ({
            name: "tax",
            module: "admin",
            filePath: "/js/app/admin/tax.js",
            layout: {
                name: "tax",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {
                core.ui.loadTemplate("tax", core.ui.getContentId()); 
                TaxInfo.init();
                TaxInfo.populateValues();
            }),
            "add": action(function() {
                var addForm = dijit.byId("taxAddForm");
                if(addForm) {
                    addForm.destroyRecursive();
                }
                core.ui.loadTemplate("addTax", core.ui.getContentId()); 
            }),
            "edit": action(function(id) { 
                var editForm = dijit.byId("taxEditForm");
                if(editForm) {
                    editForm.destroyRecursive();
                }
                core.ui.loadTemplate("editTax", core.ui.getContentId()); 
                EditTax.init(id);
                EditTax.populateValues();
                
            }),
            "delete": action(function(id) {                
                core.ui.loadTemplate("tax", core.ui.getContentId()); 
                TaxInfo.init();
                TaxInfo.populateValues();
                DeleteTax.init(id)
                DeleteTax.populateValues();
            })          
            
            
        });
    });
   
var TaxInfo = {
    _taxRestStore:"",
    init: function() {
        this._taxRestStore = new JsonRest({
            target: core.getContextPath()+"/api/tax/"
        });  
    },
    populateValues : function() {
        if(dijit.byId("adminTaxDataGrid")) {                                    
                dijit.byId("adminTaxDataGrid").destroyRecursive();                    
        }
        var taxGridData = {
            items: []
        }; 
        var taxDataList = new ItemFileWriteStore({data: taxGridData}); 
        var taxDataLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.billing.taxName, 'field': 'taxName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.description, 'field': 'description', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.billing.taxPercentage + "(%)", 'field': 'taxPercentage', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) { 
                       
                       if(data != 1) {
                           var html = "<a class='btn-flat success' href='#/admin/tax/edit/"+data+"'>"+translator.common.edit+"</a>" +
                                       "<a class='btn-flat danger spacing'  href='#/admin/tax/delete/"+data+"'>"+translator.common.deleteData+"</a></li>";
                       
                            return html;
                        } else {
                           return ""; 
                        } 
                },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
             ]
         ];
         
         this._taxRestStore.query().then(function(data) {
             dojo.forEach(data, function(taxData) {
                 taxDataList.newItem({
                     id:taxData.id,
                     taxName:taxData.name,
                     description: taxData.description, 
                     taxPercentage: taxData.percentage,
                     action : taxData.id});
             });
         });
          var taxDataGrid = new EnhancedGrid({
            id: 'adminTaxDataGrid',
            "class" : "span12",
            store: taxDataList,
            structure: taxDataLayout,
            autoHeight: true,
            plugins: core.getGridConfig().plugins
        });
        taxDataGrid.placeAt("adminTaxList");
        taxDataGrid.startup();         
     },
     deleteTax : function(data) {
       alert(data); 
     },
     addTax : function() {        
         var name = dijit.byId("adminTaxName").value;
         var description = dijit.byId("adminTaxDescription").value;
         var percentage = dijit.byId("adminTaxPercentage").value;
//         var taxGrid = dijit.byId("adminTaxDataGrid");
         var taxRestStore = new JsonRest({
            target: core.getContextPath()+"/api/tax/"
        });
        
        var node = dojo.byId("addTaxPage");
        var nodeWidget = dijit.registry.findWidgets(node);
        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        dijit.byId('taxButton').set('style', {display: 'none'});
        dojo.byId("taxLoader").style.display = "block";
         taxRestStore.add({
             name : name,
             description : description,
             percentage : percentage
         }).then(function(data) {
             dojo.forEach(data, function(resultData) {
                 dijit.byId('taxButton').set('style', {display: 'block'});
                 dojo.byId("taxLoader").style.display = "none";
                 if(resultData.result == "OK") {
                     registry.byId("appToaster").setContent(translator.common.message.added,"message");
                     registry.byId("appToaster").show();
                     dijit.byId("taxAddForm").reset();
                      core.router.go("#/admin/tax");
                 } else {
                     registry.byId("appToaster").setContent(translator.common.message.failed,"error");
                     registry.byId("appToaster").show();
                 }
             });
         });
         
     }
    
    
};
var EditTax = {
    _taxRestStore:"",
    _currentPageId : "",
    init: function(currentPageId) {        
        this._currentPageId = currentPageId;
        this._taxRestStore = new JsonRest({
            target: core.getContextPath()+"/api/tax/"
        }); 
        
    },
    populateValues : function() {
        this._taxRestStore.get(this._currentPageId).then(function(data) {  
            dijit.byId("EditTaxName").setValue(data.name);
            dijit.byId("EditTaxDescription").setValue(data.description);
            dijit.byId("EditTaxPercentage").setValue(data.percentage);  
        });        
    },
    updateTax : function() {         
         var name = dijit.byId("EditTaxName").value;
         var description = dijit.byId("EditTaxDescription").value;
         var percentage = dijit.byId("EditTaxPercentage").value;
                
         var taxRestStore = new JsonRest({
            target: core.getContextPath()+"/api/tax/"
         });        
         dijit.byId('editTaxButton').set('style', {display: 'none'});
         dojo.byId("editTaxLoader").style.display = "block";
         taxRestStore.put({
             id: this._currentPageId,
             name : name,
             description : description,
             percentage : percentage
         }).then(function(data) {
             dijit.byId('editTaxButton').set('style', {display: 'block'});
             dojo.byId("editTaxLoader").style.display = "none";
             dojo.forEach(data, function(resultData){
                 if(resultData.result == "OK") {
                     registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                     registry.byId("appToaster").show();                     
                     dijit.byId("taxEditForm").reset();
                     core.router.go("#/admin/tax");
                    

                 } else {
                     registry.byId("appToaster").setContent(translator.common.message.failed,"error");
                     registry.byId("appToaster").show();
                 }
             }); 
         });
     },
     resetPage : function() {   
         core.router.go("#/admin/tax");
     }
};

var DeleteTax = {
    _currentId:"",
    init : function(currentId) {
       this._currentId = currentId;
       this._taxRestStore = new JsonRest({
            target: core.getContextPath()+"/api/tax/"
        });
    },
    populateValues : function() {
        this._taxRestStore.remove(this._currentId).then(function(result) {
            if(result == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.deletedSuccess,"message");
                registry.byId("appToaster").show();                     
                core.router.go("#/admin/tax");
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();                     
                core.router.go("#/admin/tax");
            }
        });
    }
};   
 


window.TaxInfo = TaxInfo;
//window.AddTax = AddTax;
window.EditTax = EditTax;
window.DeleteTax = DeleteTax;