"use strict";
var payment = {
    _loader: null,
    showLoader: function(message) {
        
        if (message) {
            this._loader.innerHTML = message;
        } else {
            this._loader.innerHTML = "<p>Please wait till the page loads</p>";
        }	
        this._loader.style.display = "block";
    },
    
    hideLoader: function() {
        this._loader.style.display = "none";
    },
    'getContextPath': function() {
            return paymentBaseConfig.context;
    },
    'setConfig': function(config) {
        for (var param in config) {
            paymentBaseConfig[param] = config[param];

        }
    },
    require : function() {
        var baseURL = paymentBaseConfig.baseURL;
        require([
            "dojo",
            "dojo/i18n",
            "dojo/i18n!"+baseURL+"/js/dojo-1.8/FogPanel/nls/translator.js",
            "dojo/dom",
            "dojo/ready",
            "dojo/store/JsonRest",
            "dojo/data/ItemFileWriteStore",
            "dijit/registry",    
            "dijit/form/Select",
            "dojox/grid/EnhancedGrid",
            "dijit/form/FilteringSelect",
            "dojo/dom-construct",
            "dojo/query",
            "dojo/dom-class",
            "dijit/form/Button",
            "dojox/grid/EnhancedGrid",
            "dojox/grid/enhanced/plugins/Filter",
            "dojo/on",
            "dojox/validate/regexp",
            "dojox/widget/Toaster",
            "dijit/form/Form",    
            "dojox/validate/regexp",
            "dijit/form/ValidationTextBox",
            "dijit/form/CheckBox",
            "dijit/form/NumberSpinner",
            "dijit/Dialog",
            "FogPanel/Navigator"
        ], 
        function(dojo, i18n, translator, dom, ready, JsonRest, ItemFileWriteStore, registry, Select, EnhancedGrid,  FilteringSelect) {        
            window.translator = translator;  
            window.JsonRest = JsonRest;
            window.Select = Select;    
            window.EnhancedGrid = EnhancedGrid;    
            window.ItemFileWriteStore = ItemFileWriteStore;		  
            window.registry = registry;
            payment._loader = dom.byId("loader");
//            payment.populateValues();
            payment.hideLoader();
        });    
    },  
    
};
var paymentBaseConfig = {
    'baseURL': "/FogPanel/",
    'homepage': "#/",
    'context': "FogPanel"
};