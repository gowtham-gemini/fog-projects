define("FogPanel/Wizard", [
	"require",
	"dojo/_base/declare", // declare
	"dojo/has",
	"dojo/keys", // keys.LEFT_ARROW keys.RIGHT_ARROW
	"dojo/ready",
	"dijit/_Widget",
        "dojo/dom-construct",
        "dojo/dom-class",
        "dijit/registry",
        "dojo",
	"dijit/_KeyNavContainer",
	"dijit/_TemplatedMixin",
        "dijit/_Templated"
//    "dojo/text!FogPanel/templates/ZoneCost.html"
], function(require, declare, has, keys, ready, _Widget, domConstruct, domClass,registry, dojo,_KeyNavContainer, _TemplatedMixin, _Templated){	
	return declare("FogPanel.Wizard", [_Widget, _TemplatedMixin, _KeyNavContainer], {
        templateString:
            '<div class="wizard" tabIndex="${tabIndex}" data-dojo-attach-point="containerNode">' +
            '</div>',    	
        baseClass: "",
        onActiveEvent:"",
        additionalProperties: {steps:[]},
        attrib:{steps:{"1" : "", "2": ""}},
        deSelect:"",
		postCreate: function() {    
            this._initSteps();
            
            this.inherited(arguments); 
            
        }, 
        _initSteps: function() {
            var attrib = this.attrib;
            var widget = this;            
            var containerNode = this.containerNode;
            
            var wizardStepsNode = domConstruct.create("div");
            wizardStepsNode.className = "wizard row-fluid fogPanelWizard";
            var wizardStepsHTML = "<ul class='wizard-steps' id='wizardSteps'>";
            for (var stepId in attrib.steps) {
                 
                wizardStepsHTML += "<li id="+widget.id+"_step_"+ stepId+ ">"+
                "<span id='' class='step'>"+ stepId + "</span>"+
                "<span class='title'>"+ attrib.steps[stepId] + "</span>"+
                "</li>" ;
            
            }            
            wizardStepsHTML += "</ul>"; 
           
            wizardStepsNode.innerHTML = wizardStepsHTML;
            
            if(containerNode.childNodes.length > 0) {
                containerNode.insertBefore(wizardStepsNode, containerNode.firstChild);
            } else {
                containerNode.appendChild(wizardStepsNode);
            }                     
            dojo.query("#wizardSteps li").forEach(function(node, index, arr) {
                var currentTabId = widget.id + "_step_" + "1";                
                if(currentTabId == node.id) {
                    node.className = "active";
                }
            });
        },
        enable: function(stepId) {             
            var currentTab = this.id + "_step_" + stepId;
            var widget = this;            
            dojo.query("#wizardSteps li").forEach(function(node, index, arr) {
                domClass.remove(node, "deSelect");
                if(node.id == currentTab) {
                    node.className = "active enabled";
                    widget.setActive(node.id);
                    node.onclick = function() {
                      widget.setActive(node.id);
                    };
                } else if(index < stepId){
                    node.className = "enabled";
                }                
            });          
      },
      
      enableStateWithoutAction : function(stepId) {
          var currentTab = this.id + "_step_" + stepId;
            var widget = this;            
            dojo.query("#wizardSteps li").forEach(function(node, index, arr) {
                if(node.id == currentTab) {
                    node.className = "active enabled deSelect";
//                    widget.setActive(node.id);                    
                } else if(index < stepId){
                    node.className = "enabled deSelect";
                }                
            });     
      },
      disable: function(stepId) {
          var count;
          var currentTab = this.id + "_step_" + stepId;
          var prevTab = this.id + "_step_" + count;
          dojo.query("#wizardSteps li").forEach(function(node, index, arr) {
              if(node.id == currentTab) {
                  domClass.remove(node, "active enabled");
                  node.onclick = function() {
                  };                 
              }
              if(node.id == prevTab) {
                  node.className = "active enabled";
              } 
          });
      },
      defaultDeselectMenu : function() {
          dojo.query("#wizardSteps li").forEach(function(node, index, arr) {             
              domClass.add(node, "deSelect");        
          })
      },
      onActive: function(testFunction) {
          this.onActiveEvent = testFunction;
      },
      setActive: function(stepId) {
        dojo.query("#wizardSteps li.enabled").forEach(function(node, index, arr) {        
            if(stepId == node.id) {
                node.className = "active enabled";
               
            } else if(stepId != node.id && node.className == "active enabled"){
                domClass.remove(node, "active");      
            }
        }); 
        if(typeof this.onActiveEvent  == "function") {
           this.onActiveEvent(stepId);
        }      
      },
      deactivateTabClick : function() {
          dojo.query("#wizardSteps li").forEach(function(node, index, arr) {
              node.style.cursor = "text";
            node.onclick = function() {
                
            };
        }); 
      }
     
    });
});
