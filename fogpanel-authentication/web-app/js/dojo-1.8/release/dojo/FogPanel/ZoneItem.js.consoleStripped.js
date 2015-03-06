require({cache:{
'url:FogPanel/templates/ZoneItem.html':"<div class=\"ZoneItem\" data-dojo-attach-point=\"zonewidgetNode\">\n    <span style=\"display: none\" data-dojo-attach-point=\"zoneIdNode\"></span>\n    <label>${optionNameItem}</label>\n    <input type=\"text\"  data-dojo-attach-point=\"zoneNameNode\">\n    <div data-dojo-attach-point=\"zoneDescriptionNode\">\n    </div>\n   <button type=\"button\" data-dojo-attach-point =\"zoneSubmitButton\"> ${optionSaveItem}</button>\n</div>\n"}});
define("FogPanel/ZoneItem", [
    "dojo/_base/lang",
    "dojo/_base/declare",
    "dojo/dom-class",
    "dijit/form/ValidationTextBox",
    "dijit/Editor",
    "dijit/form/Button",
    "dijit/_Widget",
    "dijit/_Templated",
    "dojo/i18n",
    "dojo/text!FogPanel/templates/ZoneItem.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator"
], function(lang, declare, domClass,  ValidationTextBox, Editor, Button, widget, templated, i18n,template) {
    
      return declare("FogPanel.ZoneItem", [widget, templated], {
          templateString: template,
          zoneName: "",
          zoneDescription: "",
          zoneId: "",
          warningMessage: "",
          postCreate : function() {
                          
              this.zoneIdNode.innerHTML = this.zoneId;
              this.zoneNameWidget = new ValidationTextBox({
                  name:"zoneName",
                  regExp: '[|a-z0-9A-Z- ]{4,25}',
                  value: this.zoneName,
                  required: true,                  
                  missingMessage: this.warningMessage
              }, this.zoneNameNode);
              
              this.zoneDescriptionWidget = new Editor({
                 value: this.zoneDescription,
                 height:'100px'
             }, this.zoneDescriptionNode);             
             
             var widget = this;
             
              this.zoneButton = new Button({
                  "class" : "defaultbtn",
                  onClick: function() {
                      widget.onZoneClick();
                  }
              }, this.zoneSubmitButton);
          },
          postMixInProperties: function() {
            this.inherited(arguments);
            var labels = lang.mixin({instanceStatus: i18n.getLocalization("FogPanel", "translator", this.lang).common},          
            i18n.getLocalization("FogPanel.themes.ZoneItem", "ZoneItemTranslator", this.lang));          
            var prop;          
            for(prop in labels) { 
                this[prop];
                if(!this[prop + "Item"]) {
                    this[prop + "Item"] = labels[prop];                
                }
            }
        },
          getZoneName : function() {
              this.zoneNameValue = this.zoneNameWidget.value;
              return this.zoneNameValue;
          },
          validateZoneName : function() {
              var status = true;
              var firstNode;
              if (this.zoneNameWidget.validate && !this.zoneNameWidget.validate()) {
                      this.zoneNameWidget.focus();
                      status =  false;
                if (!firstNode) {
                    firstNode = this.zoneNameWidget;
                }
            }
             return status; 
          },
          getDescription: function() {
              this.zoneNameDescription = this.zoneDescriptionWidget.value;
              return this.zoneNameDescription;
          },
          
          onZoneClick : function() {
          },
          
          getId: function() {
               return this.zoneId;
          },
          validateText : function () {
              this.zoneNameWidget.focus();
          },
          hideWidget: function() {
              this.zonewidgetNode.style.display = "none";
          },
          showWidget: function() {
              this.zonewidgetNode.style.display = "block";
          },
          disableZoneName : function() {
              this.zoneNameWidget.disabled =  true;
          }
      });
  });


