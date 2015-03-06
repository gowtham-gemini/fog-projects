define("FogPanel/Breadcrumb", [
    "dojo/_base/declare",   
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/text!FogPanel/templates/Breadcrumb.html"
], function( declare,  widget, templated, template) {
          return declare("FogPanel.Breadcrumb", [widget, templated], {
          templateString: template,
          additionalProperties: {},       
          postCreate : function() {    
              var widget = this;
              this.breadcrumpName.innerHTML = this.additionalProperties.name;
              this.breadcrumpName.onclick = function() {
                  widget.onClick();
              };
              
          },
          addDiableClassTag : function () {
              this.breadcrumpName.className = "disable";
          },
          removeDisableClass : function () {
              this.breadcrumpName.className = "";
          },
          onClick : function() {            
          }
     });
 });

          