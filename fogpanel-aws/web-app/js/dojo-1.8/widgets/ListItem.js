define("widgets/ListItem", [
    "require",
    "dojo/_base/declare",
    "dojo/dom-class",
    "dijit/_Widget",
    "dijit/_Templated",
    "dojo/text!widgets/templates/List.html"
], function(require, declare, domClass, widget, templated, template) {
     
     
     return declare("widgets.ListItem", [widget, templated], {
        templateString: template,

        heading: {},
        description: {},
        additionalProperties: {},
        postCreate : function() {
            // Using the attributes defined via dojoattachpoint
            this.headingNode.innerHTML = this.heading;
            this.descriptionNode.innerHTML = this.description;

        },
        getData : function() {
            this.headingNode.innerHTML = this.additionalProperties.heading;
            this.descriptionNode.innerHTML = this.additionalProperties.description;
           
        },  
        
        onClick : function() {
            
        }
                                          
    });
   }
);



