define("FogPanel/MiscZonePrice", [
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
    "dojo/text!FogPanel/templates/MiscZonePrice.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator"
], function(lang, declare, domClass,  ValidationTextBox, NumberTextBox, Editor, Button, widget, templated, i18n,template) {
    
      return declare("FogPanel.MiscZonePrice", [widget, templated], {
          templateString: template,
          zoneName: "",
          zoneIdNode: "",
          zoneCost: "",     
          invalidMsg : "",
          postCreate : function() {              
              var widget = this;
              var unitCost = this.miscRunnigCostPerHrLabel;                                  
              var invalidMessage = this.invalidMsg;                                          
              this.zoneNameNode.innerHTML = this.zoneName;              
              this.zoneId.innerHTML = this.zoneIdNode;
              
              this.zoneCostWidget = new NumberTextBox({
                  required: true, 
                  regExp:'[0-9.]{1,200}',
                  constraints: {pattern: "#.##"},                  
                  onKeyUp: function() {  
                      var val_status = true;
                      if (this.validate && !this.validate()) {
                          val_status = false;
                      }
                      if(val_status === true) {
                          if((this.getValue()/720).toFixed(5) === "NaN") {
                              unitCost.innerHTML = " 0.00000";
                          } else {
                              unitCost.innerHTML = "" + (this.getValue()/720).toFixed(5);
                          }                        
                      } else  {
                          unitCost.innerHTML = " 0.00000";
                      } 
                  },
                  onChange: function() {                                         
                      var val_status = true;                  
                      if (this.validate && !this.validate()) {
                          val_status = false;
                      }
                      if(val_status === true) {                      
                          if((this.getValue()/720).toFixed(5) === "NaN") {
                              unitCost.innerHTML = " 0.00000";
                          } else {
                              unitCost.innerHTML = "" + (this.getValue()/720).toFixed(5);
                          }                                        
                      } else {
                          unitCost.innerHTML = " 0.00000";
                      }
                  }
              }, this.miscRunningCostPerMonth);              
          },   
          setCost: function() {         
              this.zoneCostWidget.setValue(this.zoneCost);
          },
          getZoneHrCost : function() {
              return (this.zoneCostWidget.getValue()/720).toFixed(5);
          },  
          getZoneMonthCost : function() {
              return this.zoneCostWidget.getValue();
          },                                
          getZoneId: function() {
              return this.zoneId.innerHTML;
          },
          setZoneId : function(zoneId) {
              this.zoneId.innerHTML = zoneId;
          },
          onClick : function() {},
          clearWidget: function() {
              this.zoneCostWidget.reset();              
              this.miscRunnigCostPerHrLabel.innerHTML = " 0.00000";                         
          },
          setZoneCost: function() {         
              this.zoneCostWidget.setValue(this.zoneCost);
          },   
          hideHrCost : function () {
             this.miscRunnigCostPerHrLabel.style.display = "none";
          },
          validateField: function() {
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
          showOnlyMonthCost : function () {
              this.miscRunnigCostPerHrLabel.style.display = "none";
          }
      });
  });


