define("Zone/ZoneCost", [
    "dojo/_base/declare",    
    "dojo/dom-class",
    "dijit/form/ValidationTextBox", 
    "dijit/form/NumberTextBox",
    "dijit/_Widget",
    "dijit/_Templated",
    "dojo/text!Zone/templates/ZoneCost.html"
], function(declare, domClass,  ValidationTextBox, NumberTextBox, widget, templated, template) {
          return declare("Zone.ZoneCost", [widget, templated], {
          templateString: template,
          zoneName: "",
          zoneIdNode: "",
          zoneCost: "",
          setupCost:"",
          minCost:"",
          costRate:"",
          labelName:"VM Running Cost/Month",
          vmRunningCostLabel : "",
          stopageCostLabel : "",
          setupCostLabel : "",              
          runningCostPerHrLabel : "",     
          stopageCostPerHrLabel : "",     
          invalidMsg : "",
          calcType : "",
          diskSize : "",
          postCreate : function() {      
              var widget = this;
              var unitCost = this.unitCostNode;              
              var stopageCost = this.stopageCostNode;
              this.costLabelNode.innerHTML = this.vmRunningCostLabel;
              this.vmStopageLabel.innerHTML = this.stopageCostLabel;
              this.vmSetupLabel.innerHTML = this.setupCostLabel;
              
              this.runningCost.innerHTML = this.runningCostPerHrLabel;
              this.stopCost.innerHTML = this.stopageCostPerHrLabel;
              var invalidMessage = this.invalidMsg;
              this.zoneCostWidget = new NumberTextBox({
                  required: true,                  
                  missingMessage: invalidMessage,
                  constraints: {pattern: "#.##"},
                  invalidMessage:invalidMessage,
                  onKeyUp: function() {                    
                      if(widget.calcType == "") {
                          unitCost.innerHTML = "" + (this.getValue()/720).toFixed(5);
                          if((this.getValue()/720).toFixed(5) == "NaN") {
                              unitCost.innerHTML = " 0.00000";
                          }
                      } else if(widget.calcType == "sizeBase") {                          
                          if((this.getValue()/720).toFixed(5) == "NaN") {
                              unitCost.innerHTML = " 0.00000";
                          } else {                               
                              var resultData = (this.getValue()/widget.diskSize) / 720;
                              unitCost.innerHTML = "" + (resultData).toFixed(5);                                
                          }
                      }                    
                  },
                  onChange: function() { 
                      if(widget.calcType == "") { 
                          unitCost.innerHTML = "" + (this.getValue()/720).toFixed(5);                     
                          if((this.getValue()/720).toFixed(5) == "NaN") {
                              unitCost.innerHTML = " 0.00000";
                          }
                      } else if(widget.calcType == "sizeBase") {
                          if((this.getValue()/720).toFixed(5) == "NaN") {
                              unitCost.innerHTML = " 0.00000";
                          } else {
                              var resultData = (this.getValue()/widget.diskSize) / 720;
                              unitCost.innerHTML = "" + (resultData).toFixed(5); 
                          }                                                        
                      }                    
                  }
              }, this.zoneCostNode);
              
              this.setupCostWidget = new NumberTextBox({
                  required: true,
                  constraints: {pattern: "#.##"},
                  onChange: function() {
                  }
              }, this.setupCostNode);
              
              this.minCostWidget = new NumberTextBox({
                required: true,
                constraints: {pattern: "#.##"}, 
                onKeyUp: function() {                      
                      stopageCost.innerHTML = "" + (this.getValue()/720).toFixed(5);
                      if((this.getValue()/720).toFixed(5) == "NaN") {
                          stopageCost.innerHTML = " 0.00000";
                      }
//                      alert(this.getValue())
                  },
                  onChange: function() {                     
                     stopageCost.innerHTML = "" + (this.getValue()/720).toFixed(5);                     
                     if((this.getValue()/720).toFixed(5) == "NaN") {
                          stopageCost.innerHTML = " 0.00000";
                     }
                  }
              }, this.minCostNode);
              
              this.zoneNameNode.innerHTML = this.zoneName;
            
              this.zoneId.innerHTML = this.zoneIdNode;
              
          },                    
          changeDiskLabelValue : function (currenDiskSize) {             
              this.costLabelNode.innerHTML = this.vmRunningCostLabel;
              this.diskSize = currenDiskSize;
          },
          setCalcType : function (currentCalcType) {
              this.calcType = currentCalcType;
          },
          removeCosts : function() {
              this.setupCostContainer.setAttribute("style", 'display: none');
              this.minCostContainer.setAttribute("style", 'display: none');
          },
          removeUnitCost : function() {
              this.unitCostNode.setAttribute("style", 'display: none');
          },
          showStopageCost : function() {
              this.stopageCostNode.setAttribute("style", 'display: block');
              this.runningCost.style.display = "block";
              this.stopCost.style.display = "block";                            
          },
          showOnlyZoneCost : function () {
              this.runningCost.style.display = "block";
              this.setupCostContainer.setAttribute("style", 'display: none');
              this.minCostContainer.setAttribute("style", 'display: none');                   
          },
          getZoneCost : function() {
              return (this.zoneCostWidget.getValue()/720).toFixed(5);
          },          
          getZoneCostValue: function() {
              return this.zoneCostWidget.getValue();
          },
          getSetupCost: function() {
               return this.setupCostWidget.getValue().toString();
          },
          getMinCost: function() {
              return (this.minCostWidget.getValue()/720).toFixed(5);
          },
          getZoneId: function() {
              return this.zoneId.innerHTML;
          },
          setZoneId : function(zoneId) {
              this.zoneId.innerHTML = zoneId;
          },
          onClick : function() {},
          clearWidgets: function() {
              this.zoneCostWidget.reset();
              this.setupCostWidget.reset();
              this.minCostWidget.reset();
              this.unitCostNode.innerHTML = " 0.00000";
              this.stopageCostNode.innerHTML = " 0.00000";              
          },
          setCost: function() {         
              this.zoneCostWidget.setValue(this.zoneCost);
          },
          setZoneCostValue : function (currentVal) {
              this.zoneCostWidget.setValue(currentVal);
          },
          setMinCost: function() {
              this.tempMinCost.innerHTML = this.minCost;
              this.minCostWidget.setValue(this.tempMinCost.innerHTML);
          },
          
          setSetupCost: function() {
              this.tempSetupCost.innerHTML = this.setupCost;
              this.setupCostWidget.setValue(this.tempSetupCost.innerHTML);
          },
          showErrorMsg: function() {
              var widgets = dijit.registry.findWidgets(this.zoneCostList);
              var firstNode = "";
              var status = true;
              dojo.forEach(widgets, function(el) {
                  if (el.validate && !el.validate()) {
                      el.focus();
                      status =  false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
            });        
              return status;
          },
          showError: function() {
              var status = true;
              var firstNode;
              if (this.zoneCostWidget.validate && !this.zoneCostWidget.validate()) {
                      this.zoneCostWidget.focus();
                      status =  false;
                if (!firstNode) {
                    firstNode = this.zoneCostWidget;
                }
            }
             return status; 
             
          },
          setCostRate : function() {
              this.tempZoneCost.innerHTML = this.costRate;
          }
      });
  });




