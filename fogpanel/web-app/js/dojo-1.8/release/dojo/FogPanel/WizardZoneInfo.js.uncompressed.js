require({cache:{
'url:FogPanel/templates/WizardZoneInfo.html':"<div class=\"WizardZoneInfo\">\n    <label>Zone:<span data-dojo-attach-point=\"zoneNameNode\" class=\"zoneValues\">test</span></label> \n    <label>Pod:<span data-dojo-attach-point=\"podNameNode\"  class=\"zoneValues\">test</span> </label>\n    <label>Cluster:<span data-dojo-attach-point=\"clusterNameNode\" class=\"zoneValues\">test</span> </label> \n</div>\n\n"}});
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

            