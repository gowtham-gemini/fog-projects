define("FogPanel/Notification", [
    "dojo/_base/lang",
    "dojo/_base/declare",
    "dojo/_base/connect",
    "dijit/_TemplatedMixin",
    "dojo",
    "dijit/_Widget",
    "dijit/_Templated",
    "dojo/dom-construct", 
    "dojo/i18n",
    "dojo/text!FogPanel/templates/Notification.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator"
], function(lang, declare, connect, _TemplatedMixin, dojo, widget, templated, domConstruct, i18n, template) { 
    return declare("FogPanel.Notification", [widget, templated], {
        templateString: template,
        path:"",        
        _langEnabled : false,
        postCreate : function() {
            if(this._langEnabled == true) {
                this.showLanguageList();
            }                  
        },
        postMixInProperties: function() {
            this.inherited(arguments);
            var labels = lang.mixin({instanceStatus: i18n.getLocalization("FogPanel", "translator", this.lang).common},          
            i18n.getLocalization("FogPanel.themes.Notification", "NotificationTranslator", this.lang));          
            var prop;          
            for(prop in labels) { 
                this[prop];
                if(!this[prop + "Item"]) {
                    this[prop + "Item"] = labels[prop];                
                }
            }
        },   
        showLanguageList : function () {
            var widget = this;            
            var itemList = [
                {link : core.getBaseURL() + "/account/setDefaultLanguage?language=en" , icon: "icon-warning-sign", value:"English"},
                {link : core.getBaseURL() + "/account/setDefaultLanguage?language=es" , icon: "icon-warning-sign", value:"Spanish"}                
            ]              
            this.addItems(itemList);
        },
        addItems: function(data) {
            var widget = this;
            this.notificationContainer.innerHTML = "";
            dojo.forEach(data, function(item) { 
                var row = domConstruct.toDom(
                    "<div><a href='"+item.link+"' class='${baseClass} item'>"+
                        "<i class='"+item.icon+"'></i>"+
                        "<span>"+item.value+"</span>"+
                    "</a></div>");
                domConstruct.place(row, widget.notificationContainer);
            });
        },
        clear :function() {
            this.notificationContainer.innerHTML = "";
        },
        removeItem: function(data) {
              this.notificationContainer.parentNode.removeChild(data);
        },
        subscribe: function(path) {
            
            if(this._subscriptionHandle) {
                connect.unsubscribe(this._subscriptionHandle);
            }
            
            var widget = this;
            
            this._subscriptionHandle = connect.subscribe(path, function(data){
                widget.addItems(data);
            });
        },
        unsubscribe: function() {
            if(this._subscriptionHandle) {
                connect.unsubscribe(this._handle);
            }
        },
        setLink: function(data){
            this.viewAll.href = data;
        }
        
     });
 });





