define("List/ListItem", [
    "dojo/_base/declare",   
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/text!List/templates/ListItem.html"
], function( declare,  widget,  templated, template) {
    
      return declare("List.ListItem", [widget, templated], {
          templateString: template,
          heading: "",
          description: "",
          additionalProperties: { heading: '', description: '', zones: []},
          postCreate : function() {
            // Using the attributes defined via dojoattachpoint
            this.head_text.innerHTML = this.heading;
            this.desc_text.innerHTML = this.description;
            this.deleteNode.onclick = this.onDeleteTagClick;
            this.attachIsoNode.onclick = this.onAttachIsoTagClick;
            this.startNode.onclick = this.onStartTagClick;
            this.stopNode.onclick = this.onStopTagClick;
            this.rebootNode.onclick = this.onRebootTagClick;
            this.attachNode.onclick = this.onAttachTagClick;
            this.changeServiceNode.onclick = this.onChangeServiceTagClick;
            
        },
        getData : function() {
             this.head_text.innerHTML = this.additionalProperties.heading;
             this.desc_text.innerHTML = this.additionalProperties.description;
        }, 
        onClick : function() {},
         
         getId: function() {
           return this.id;  
         },
         
         deleteWidget: function() {
             this.listWidget.style.display = "none";
         },
         onDeleteTagClick: function() {},
         onAttachIsoTagClick: function() {},
         deleteTag: function() {
             this.deleteNode.style.display = "none";
         },
         removeDescription: function() {
             this.desc_text.style.display = "none";
         },
         onStartTagClick: function() {
             
         },
         onStopTagClick : function() {
             
         },
         onRebootTagClick: function() {
             
         },
         onAttachTagClick: function() {
             
         },
         onChangeServiceTagClick: function() {
             
         },
         enableRunningState: function() {
             this.stopNode.style.display = "block";
             this.stopNode.style.margin = "-17px 0px 0 0px";

             this.rebootNode.style.display = "block";
             this.rebootNode.style.margin = "-17px 0px 0 90px";
         },
         enableStopState: function() {
             this.startNode.style.display = "block";
             this.stopNode.style.margin = "-17px 0px 0 0px";

        },
        enableStartState: function() {
            this.stopNode.style.display = "block";
            this.rebootNode.style.display = "none";
            this.startNode.style.display = "none";
            this.deleteNode.style.display = "none";
        },
        enableAttachNode : function() {
            this.attachNode.style.display = "block";
        },
        enableChangeService: function() {
            this.changeServiceNode.style.display = "block";
        },
        disableStates: function() {
            this.stopNode.style.display = "none";
            this.rebootNode.style.display = "none";
            this.startNode.style.display = "none";
            this.deleteNode.style.display = "none";
        }
     });
 });

            