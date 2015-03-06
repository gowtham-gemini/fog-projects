define("FogPanel/LanguageSelector", [    
    "dojo/_base/declare",         
    "dijit/_Widget",
    "dojo/dom-construct", 
    "dijit/_Templated",        
    "dojo/text!FogPanel/templates/LanguageSelector.html"    
], function(declare, widget, domConstruct, templated , template) { 
    return declare("FogPanel.LanguageSelector", [widget, templated], {
        templateString: template,        
        postCreate : function() {           
            this.showLanguageList();                             
        },
        postMixInProperties: function() {
//            this.inherited(arguments);
//            var labels = lang.mixin({instanceStatus: i18n.getLocalization("FogPanel", "translator", this.lang).common},          
//            i18n.getLocalization("FogPanel.themes.Notification", "NotificationTranslator", this.lang));          
//            var prop;          
//            for(prop in labels) { 
//                this[prop];
//                if(!this[prop + "Item"]) {
//                    this[prop + "Item"] = labels[prop];                
//                }
//            }
        },   
        showLanguageList : function () {           
            var itemList = [
                {link : core.getBaseURL() + "/account/setDefaultLanguage?language=en" , icon: "lng_eng_icon", value:"English"},
                {link : core.getBaseURL() + "/account/setDefaultLanguage?language=es" , icon: "lng_spain_icon", value:"Spanish"}                
            ]              
            this.addItems(itemList);
        },
        addItems: function(data) {
            var widget = this;
            this.languageContainer.innerHTML = "";
            dojo.forEach(data, function(item) { 
                var row = domConstruct.toDom(
                    "<div><a href='"+item.link+"' class='${baseClass} item custom'>"+  
                    "<span>"+item.value+"</span>"+
                    "<i class='"+item.icon+"'></i>"+                        
                    "</a></div>");
                domConstruct.place(row, widget.languageContainer);
            });
        }              
     });
 });





