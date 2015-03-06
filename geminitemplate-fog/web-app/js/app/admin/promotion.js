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
    "dijit/Dialog",
    "dojox/widget/Calendar"
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
            name: "promotion",
            module: "admin",
            filePath: "/js/app/admin/promotion.js",
            layout: {
                name: "promotion",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {
//                var generalConfigPage = dijit.byId("promotionCodeForm");
//                if(generalConfigPage) {
//                    dijit.byId("promotionCodeForm").destroyRecursive();
//                } 
                core.ui.loadTemplate("promotion", core.ui.getContentId()); 
                PromotionCodeManagement.init();
                PromotionCodeManagement.populateValues();
            }),
            "add": action(function() {
                var addForm = dijit.byId("promotionCodeForm");
                if(addForm) {
                    addForm.destroyRecursive();
                }
                core.ui.loadTemplate("addPromotionalCode", core.ui.getContentId()); 
                
                dijit.byId('promotionStartDate').set('value', new Date());
                dijit.byId('promotionEndDate').set('value', new Date());
                
            }),
            "edit": action(function(id) { 
                var editForm = dijit.byId("editPromotionCodeForm");
                if(editForm) {
                    editForm.destroyRecursive();
                }
                core.ui.loadTemplate("editPromotionCode", core.ui.getContentId()); 
                EditPromotionalCode.init(id);
                EditPromotionalCode.populateValues();
                
            }),
            "delete": action(function(id) {                
                core.ui.loadTemplate("promotion", core.ui.getContentId()); 
                PromotionCodeManagement.init();
                PromotionCodeManagement.populateValues();
                DeletePromotionalCode.init(id);
                DeletePromotionalCode.populateValues();
            })          
            
            
        });
    });
    
var DeletePromotionalCode = {
    _currentId:"",
    _promotionRestStore:"",
    init : function(currentId) {
       this._currentId = currentId;
       this._promotionRestStore = new JsonRest({
            target: core.getContextPath()+"/api/promotion/"
        });
    },
    populateValues : function() {
        this._promotionRestStore.remove(this._currentId).then(function(result) {
            if(result == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.deletedSuccess,"message");
                registry.byId("appToaster").show();                     
                core.router.go("#/admin/promotion");
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed,"error");
                registry.byId("appToaster").show();                     
                core.router.go("#/admin/promotion");
            }
        });
    }
};   
 
var EditPromotionalCode = {
    _promotionRestStore : "",
    _currentId:"",
    init: function(currentId) {
      this._currentId = currentId;  
      this._promotionRestStore = new JsonRest({
            target: core.getContextPath()+"/api/promotion/"
      });
    },
    populateValues : function() {        
        var code = dijit.byId ("editPromotionCode");
        var startDate = dijit.byId ("editPromotionStartDate");
        var endDate = dijit.byId ("editPromotionEndDate");
        var type = dijit.byId("editPromotionType");
        var promotionValue = dijit.byId("editPromotionValue");
        var maxUses = dijit.byId("editPromotionMaximumUses");
        var notes = dijit.byId("editPromotionNotes");
        var uses = dojo.byId("editPromotionCodeUses");
        this._promotionRestStore.get(this._currentId).then(function(data) {
            code.setValue(data.code);
            
            promotionValue.setValue(data.value);
            startDate.setValue(data.startDate);   
            endDate.setValue(data.endDate);
            maxUses.setValue(data.maxUses);
            notes.setValue(data.notes);            
            type.set("value", data.type.name);
            uses.innerHTML = data.uses;
        });
        
    },
    update : function() {        
        var promotionNode = dojo.byId("editPromotionCodePage");
        var promotionWidget = dijit.registry.findWidgets(promotionNode);
        
        dojo.forEach(promotionWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                };
            };
        });
        
        var code = dijit.byId ("editPromotionCode");
        var startDate = dijit.byId ("editPromotionStartDate");
        var endDate = dijit.byId ("editPromotionEndDate");
        var type = dijit.byId("editPromotionType");
        var promotionValue = dijit.byId("editPromotionValue");
        var maxUses = dijit.byId("editPromotionMaximumUses");
        var notes = dijit.byId("editPromotionNotes");
        var uses = dojo.byId("editPromotionCodeUses");
        
        this._promotionRestStore.put({
            code : code.value,
            startDate : dojo.date.locale.format(startDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
            endDate : dojo.date.locale.format(endDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
            type : type.value,
            promotionValue : promotionValue.value,
            maxUses: maxUses.value,
            notes:notes.value
        }).then(function(result) {
            if(result == "OK") {                
//                dojo.byId("usesNo").style.display = "none";
               
                dijit.byId("editPromotionCodeForm").reset();
                registry.byId("appToaster").setContent(translator.common.message.updatedSuccess,"message");
                registry.byId("appToaster").show();
                 core.router.go("#/admin/promotion");
                
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();
            };  
        });
          
    },
    cancel: function() {
        core.router.go("#/admin/promotion");
    }
};   
    
var PromotionCodeManagement = {
    _promotionRestStore : "",
    init : function() {
        this._promotionRestStore = new JsonRest({
            target: core.getContextPath()+"/api/promotion/"
        });        
    }, 
    populateValues : function() {
        if(dijit.byId("promotionDataGrid")) {                                    
                dijit.byId("promotionDataGrid").destroyRecursive();                    
        }
        var promotionGridData = {
                    items: []
        }; 
        var promotionDataList = new ItemFileWriteStore({data: promotionGridData}); 
        var promotionDataLayout = [[
                 {'field': 'id', 'hidden': 'true'},
                 {'name': translator.common.startDate, 'field': 'startDate', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                 
                 {'name': translator.common.endDate, 'field': 'endDate', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                 {'name': translator.common.maxUses, 'field': 'maxUses', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                 
                 {'name': translator.common.promotionValue, 'field': 'promotionValue', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
                 {'name': translator.common.uses, 'field': 'uses', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                 
                 {'name': translator.common.promotionType, 'field': 'type', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                 {'name': translator.common.notes, 'field': 'notes', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},     
                 {'name': translator.common.code, 'field': 'code', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},           
                 {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) { 
                       var html = "<a class='btn-flat danger' href='#/admin/promotion/delete/"+data+"'>"+translator.common.deleteData+"</a>";
                        return html;
                },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
             ]
         ];
         
         this._promotionRestStore.query().then(function(data) {
             dojo.forEach(data, function(promotionData) {
                 promotionDataList.newItem({
                     id:promotionData.id,
                     startDate: dojo.date.locale.format(new Date(promotionData.startDate), {datePattern: "dd/MM/yyyy", selector: "date"}),                     
                     endDate: dojo.date.locale.format(new Date(promotionData.endDate), {datePattern: "dd/MM/yyyy", selector: "date"}), 
                     maxUses: promotionData.maxUses,                     
                     promotionValue: promotionData.promotionValue,
                     uses: promotionData.uses,                     
                     type: promotionData.type,
                     notes: promotionData.notes,                     
                     code: promotionData.code,             
                     action : promotionData.id});
             });
         });
          var promotionDataGrid = new EnhancedGrid({
            id: 'promotionDataGrid',
            "class" : "span12",
            store: promotionDataList,
            structure: promotionDataLayout,
            autoHeight: true,
            plugins: core.getGridConfig().plugins
        });
        promotionDataGrid.placeAt("adminPromotionList");
        promotionDataGrid.startup(); 
    },
    add : function() {
        var promotionPageNode = dojo.byId("promotionCodePage");
        var promotionWidget = dijit.registry.findWidgets(promotionPageNode);
        dojo.forEach(promotionWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        
        var code = dijit.byId ("promotionCode");
        var startDate = dijit.byId ("promotionStartDate");
        var endDate = dijit.byId ("promotionEndDate");
        var type = dijit.byId("promotionType");
        var promotionValue = dijit.byId("promotionValue");
        var maxUses = dijit.byId("promotionMaximumUses");
        var notes = dijit.byId("promotionNotes");
        var uses = dojo.byId("promotionCodeUses");
        dijit.byId("promocodeButton").setAttribute('style', 'display: none');
        dojo.byId("promoCodeLoader").style.display = "block";
        var promotionRestStore = new JsonRest({
            target: core.getContextPath()+"/api/promotion/"
        });
        promotionRestStore.add({
            code : code.value,
            startDate : dojo.date.locale.format(startDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
            endDate : dojo.date.locale.format(endDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
            type : type.value,
            promotionValue : promotionValue.value,
            maxUses: maxUses.value,
            notes:notes.value
        }).then(function (data) {
            dojo.forEach(data, function(promotionData) {
                dijit.byId("promocodeButton").setAttribute('style', 'display: block');
                dojo.byId("promoCodeLoader").style.display = "none";
                if(promotionData.result == "OK") {                    
                    dijit.byId("promotionCodeForm").reset();
                    registry.byId("appToaster").setContent(translator.common.message.added, "message");
                    registry.byId("appToaster").show(); 
                    core.router.go("#/admin/promotion");
                } else {
                     registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                     registry.byId("appToaster").show();
                };
            });
        });
    },
    cancel : function () {
       
        dojo.byId("promotionCodeForm").reset();
        
    },
    deleteCurrentWidget : function(currentDiskListWidget) {
        
      var promotionRestStore = new JsonRest({
            target: core.getContextPath()+"/api/promotion/"
      });
      promotionRestStore.remove(currentDiskListWidget.additionalProperties.id);
    },
    returnPromotionCode : function(currentWidget) {
        dojo.byId("usesNo").style.display = "block";
        dijit.byId('promotionAddButton').set('style', {display: 'none', "float": 'left'});
        dijit.byId('promotionUpdateButton').set('style', {display: 'block'});

        dojo.byId("currentPromotionCodeListItem").value = currentWidget.id;
        dijit.byId("promotionCode").setValue(currentWidget.additionalProperties.code);
        dijit.byId("promotionStartDate").setValue(currentWidget.additionalProperties.startDate);
        dijit.byId("promotionEndDate").setValue(currentWidget.additionalProperties.endDate);
        dijit.byId("promotionType").setValue(currentWidget.additionalProperties.type);
        dijit.byId("promotionValue").setValue(currentWidget.additionalProperties.promotionValue);
        dijit.byId("promotionMaximumUses").setValue(currentWidget.additionalProperties.maxUses);
        dijit.byId("promotionNotes").setValue(currentWidget.additionalProperties.notes);
        dojo.byId("promotionCodeUses").innerHTML = currentWidget.additionalProperties.uses;
        
        var promotionListCollection = dojo.byId ("promotionCodeListCollection");
        var promotionListWidgets = dijit.registry.findWidgets (promotionListCollection);
        
         dojo.forEach (promotionListWidgets, function(el) {
             if (currentWidget.id == el.id) {
                 currentWidget.setSelectState(true, currentWidget.id);
            }
            else {
                 el.setSelectState(false, el.id);
             };
         }); 
    }
    
}; 
window.PromotionCodeManagement = PromotionCodeManagement;
window.EditPromotionalCode = EditPromotionalCode;
window.DeletePromotionalCode = DeletePromotionalCode;
