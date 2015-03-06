define("FogPanel/WizardListItem", [
    "dojo/_base/declare",   
    "dijit/_Widget",     
    "dojo/query",
    "dojo/dom-class",
    "dojo/dnd/Source",
    "dijit/_Templated",
    "dojo/text!FogPanel/templates/WizardListItem.html"
], function(declare,  widget, query, domClass,  Source, templated, template) {
    
      return declare("FogPanel.WizardListItem", [widget, templated], {
          templateString: template,
          heading: "",
          description: "",
          cpu:"",
          ram:"",
          attachDiskNode:"Attach Disk",
          additionalProperties: { heading: '', description: '', zones: []},
          postCreate : function() {
            // Using the attributes defined via dojoattachpoint
            var widget = this;
            
            this.head_text.innerHTML = this.heading;
            this.desc_text.innerHTML = this.description;
            this.attachNode.innerHTML = this.attachDiskNode;
            this.ramNode.innerHTML = this.cpu + " Cpu";
            this.cpuNode.innerHTML = this.ram + " Ram";
            this.head_text.onclick = function() {
                widget.onClick();
            };
            this.desc_text.onclick = function() {
                widget.onClick();
            };
            this.upArrowNode.onclick = function() {
                widget.onUpArrowClick();
            };
            this.downArrowNode.onclick = function() {
                widget.onDownArrowClick();
            };           
            this.head_text.onfocus =  function() {
                widget.onFocus();
            }
            this.deleteNode.onclick = function() {
                widget.onDeleteTagClick();
//                widget.onClick();
            };
            this.summaryNode.onclick = function() { 
                widget.onSummaryTagClick();
            };
            
            this.startNode.onclick = this.onStartTagClick;
            this.stopNode.onclick = this.onStopTagClick;
            this.rebootNode.onclick = this.onRebootTagClick;
            this.attachNode.onclick = function() {
                widget.onAttachTagClick();
            };
            this.changeServiceNode.onclick = this.onChangeServiceTagClick;           
            
        },
        getData : function() {
             this.head_text.innerHTML = this.additionalProperties.heading;
             this.desc_text.innerHTML = this.additionalProperties.description;
        }, 
        onClick : function() {
            
        },
        onFocus : function() {  
            
        },
        onUpArrowClick : function() {
            
        },
        onDownArrowClick : function() {
            
        },
        getId: function() {
            return this.id;
        }, 
        deleteWidget: function() {
            this.listWidget.style.display = "none";
        },
         onDeleteTagClick: function() {},
         onSummaryTagClick : function() {},
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
        },
        setSelectState : function(status, widget) {            
            
                if(status == true) {
                      domClass.toggle(widget, "WidgetItemSelected", true);
                } else if(status == false) {                    
                    domClass.remove(widget, "WidgetItemSelected");
                }
                       
         },
         unSelectItem: function() {
            this.head_text.style.cursor = "auto";
            this.head_text.style.display = "block";
         },
         enableSummary : function() {
             this.summaryNode.style.display = "block";
             
         },
         showCpuRam : function() {
             this.ramNode.style.display = "block";
             this.cpuNode.style.display = "block";
         },
         showUpAndDownArrow : function() {
             this.upArrowNode.style.display = "block";
             this.downArrowNode.style.display = "block";
         }
      
     });
 });

            