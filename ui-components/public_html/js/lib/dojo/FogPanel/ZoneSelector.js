define("FogPanel/ZoneSelector", [    
    "dojo/_base/declare",         
    "dijit/_Widget",
    "dojo/dom-construct", 
    "dijit/_Templated",        
    "dojo/text!FogPanel/templates/ZoneSelector.html"    
], function(declare, widget, domConstruct, templated , template) { 
    return declare("FogPanel.ZoneSelector", [widget, templated], {
        templateString: template,
        zoneName: "",
        additionalProperties : {},
        postCreate : function() {    
            var widget = this;
            this.zoneNameNode.innerHTML = this.zoneName;
            this.zoneItemNode.onclick = function () {
                widget.onZoneItemClick()
            };
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
        onZoneItemClick : function () {           
        },
        addItems: function(data) {
//            var widget = this;
//            this.languageContainer.innerHTML = "";
//            dojo.forEach(data, function(item) { 
//                var row = domConstruct.toDom(
//                    "<div><a href='"+item.link+"' class='${baseClass} item'>"+
//                        "<i class='"+item.icon+"'></i>"+
//                        "<span>"+item.value+"</span>"+
//                    "</a></div>");
//                domConstruct.place(row, widget.languageContainer);
//            });
        }              
     });
 });





