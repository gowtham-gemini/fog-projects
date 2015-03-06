define("FogPanel/WizardZoneInfo", [
   "dojo/_base/declare",   
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/text!FogPanel/templates/WizardZoneInfo.html"
], function( declare,  widget, templated, template) {
          return declare("FogPanel.WizardZoneInfo", [widget, templated], {
          templateString: template,
          zoneName:"",
          podName:"",
          clusterName:"",
          
          postCreate : function() {
            this.zoneNameNode.innerHTML = this.zoneName;
            this.podNameNode.innerHTML =  this.podName;
            this.clusterNameNode.innerHTML = this.clusterName;
                
          }      
     });
 });

            