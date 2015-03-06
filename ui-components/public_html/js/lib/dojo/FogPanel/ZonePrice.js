define("FogPanel/ZonePrice", [
    "dojo/_base/lang",
    "dojo/_base/declare",
    "dojo/dom-class",
    "dijit/form/ValidationTextBox",
    "dijit/form/NumberTextBox",
    "dijit/Editor",
    "dijit/form/Button",
    "dijit/_Widget",
    "dijit/_Templated",
    "dojo/i18n",
    "dojo/text!FogPanel/templates/ZonePrice.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator"
], function(lang, declare, domClass,  ValidationTextBox, NumberTextBox, Editor, Button, widget, templated, i18n,template) {
    
      return declare("FogPanel.ZonePrice", [widget, templated], {
          templateString: template,
          zoneName: "",
          zoneIdNode: "",
          zoneCost: "",
          setupCost:"",
          setupCostValue : "",
          minCost:"",
          costRate:"",
          calcType : "",
          diskSize : "",
          postCreate : function() {
              
              var widget = this;
              var unitCost = this.instanceRunningCostPerHour;       
              var secondCost = this.runnigCostSecondLabel;
              var stopageCost = this.instanceStopageCostPerHour;
              
              var invalidMessage = this.invalidMsg;
                            
//              this.zoneNameWidget = new ValidationTextBox({
//                  name:"zoneName",
//                  regExp: '[|a-z0-9A-Z- ]{4,25}',
//                  value: this.zoneName,
//                  required: true,                  
//                  missingMessage: this.warningMessage
//              }, this.zoneNameNode);
              
              this.zoneNameNode.innerHTML = this.zoneName;
              
              this.zoneId.innerHTML = this.zoneIdNode;
              
              this.zoneCostWidget = new NumberTextBox({
                  required: true,                  
                  missingMessage: invalidMessage,
                  constraints: {pattern: "#.##"},
                  invalidMessage:invalidMessage,
                  onKeyUp: function() {                        
                      if((this.getValue()/720).toFixed(5) === "NaN") {
                          unitCost.innerHTML = " 0.00000";
                      } else {
                          unitCost.innerHTML = "" + (this.getValue()/720).toFixed(5);
                      }
                      if(widget.calcType === "sizeBase") {                          
                          if((this.getValue()/720).toFixed(5) === "NaN") {                              
                              secondCost.innerHTML = " 0.00000";
                          } else {                               
                              var resultData = (this.getValue()/widget.diskSize) / 720;
                              secondCost.innerHTML = "" + (resultData).toFixed(5);                                
                          }
                      }                    
                  },
                  onChange: function() { 
                      if((this.getValue()/720).toFixed(5) === "NaN") {
                          unitCost.innerHTML = " 0.00000";
                      } else {
                          unitCost.innerHTML = "" + (this.getValue()/720).toFixed(5);
                      }
                      if(widget.calcType === "sizeBase") {                          
                          if((this.getValue()/720).toFixed(5) === "NaN") {                              
                              secondCost.innerHTML = " 0.00000";
                          } else {                               
                              var resultData = (this.getValue()/widget.diskSize) / 720;
                              secondCost.innerHTML = "" + (resultData).toFixed(5);                                
                          }
                      }                    
                  }
              }, this.instanceRunningCostPerMonth);
              
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
              }, this.instanceStopageCostPerMonth);
              
              this.setupCostWidget = new NumberTextBox({
                  required: true,
                  constraints: {pattern: "#.##"},
                  onChange: function() {
                  }
              }, this.setupCost);
          },
          
          setCalcType : function (currentCalcType) {
              this.calcType = currentCalcType;
          },          
          getZoneCost : function() {
              return (this.zoneCostWidget.getValue()/720).toFixed(5);
          },  
          showOnlyZoneCost : function () {              
              this.minCostWidget.setAttribute("style", 'display: none');
              this.instanceStopageCostPerHour.setAttribute("style", 'display: none');
              this.setupCostContainer.setAttribute("style", 'display: none');      
              this.runnigCostSecondLabel.setAttribute("style", 'display: block');               
          },
          customDiskActionInfo : function (customElement) {
              if(customElement === true) {
                  this.runnigCostSecondLabel.setAttribute("style", 'display: none');     
              } else {
                  this.runnigCostSecondLabel.setAttribute("style", 'display: block'); 
              }              
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
              this.instanceRunningCostPerHour.innerHTML = " 0.00000";
              this.instanceStopageCostPerHour.innerHTML = " 0.00000";              
          },
          setCost: function() {         
              this.zoneCostWidget.setValue(this.zoneCost);
          },
          setZoneCostValue : function (currentVal) {
              this.zoneCostWidget.setValue(currentVal);
          },
          changeDiskLabelValue : function (currenDiskSize) {                           
              this.diskSize = currenDiskSize;
          },
          setMinCost: function() {
              this.tempMinCost.innerHTML = this.minCost;
              this.minCostWidget.setValue(this.tempMinCost.innerHTML);
          },
          
          setSetupCost: function() {
              this.tempSetupCost.innerHTML = this.setupCostValue;
              this.setupCostWidget.setValue(this.tempSetupCost.innerHTML);
          },
          showErrorMsg: function() {
              var widgets = dijit.registry.findWidgets(this.zonePriceList);
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
             
          }          
      });
  });


