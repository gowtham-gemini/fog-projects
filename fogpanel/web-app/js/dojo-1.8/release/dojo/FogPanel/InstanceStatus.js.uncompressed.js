require({cache:{
'url:FogPanel/templates/InstanceStatus.html':"<div class=\"InstanceStatusContainer\"> \n    <div class=\"row-fluid grind-icons\">\n        <div class=\"span2\" data-dojo-attach-point=\"startInstanceContainer\">\n            <img title=\"${instanceStartStatus}\" data-dojo-attach-point=\"startInstanceNode\" src=\"js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_play_icon.png\"/>  \n        </div>\n        <div class=\"span2\" data-dojo-attach-point=\"stopInstanceContainer\">\n            <img title=\"${instanceStopStatus}\" data-dojo-attach-point=\"stopInstanceNode\" src=\"js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_stop_icon.png\"/>\n        </div>\n        <div class=\"span2\" data-dojo-attach-point=\"rebootInstanceContainer\">\n            <img title=\"${instanceRebootStatus}\" data-dojo-attach-point=\"rebootInstanceNode\" src=\"js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_reboot_icon.png\"/>\n        </div>\n        <div class=\"span2\" data-dojo-attach-point=\"deleteInstanceContainer\">\n            <img title=\"${instanceDeleteStatus}\" data-dojo-attach-point=\"deleteInstanceNode\" src=\"js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png\">\n        </div>\n        <div class=\"span2\" data-dojo-attach-point=\"restoreInstanceContainer\">\n            <img  title=\"${instanceRestoreStatus}\" data-dojo-attach-point=\"restoreInstanceNode\" src=\"js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_restore_icon.png\"/>\n        </div>\n        <div class=\"span2\" data-dojo-attach-point=\"instanceMigrateContainer\">\n            <img title=\"${instanceMigratStatus}\" data-dojo-attach-point=\"instanceMigrateNode\" src=\"js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_migrate.png\"/> \n        </div>                                                       \n    </div>    \n</div>"}});
define("FogPanel/InstanceStatus", [
    "dojo/_base/lang",
    "dojo/_base/declare",  
    "dojo/_base/connect",
    "dijit/_TemplatedMixin",
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/i18n",
    "dojo/text!FogPanel/templates/InstanceStatus.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator",
    "dijit/form/Button"
], function(lang, declare, connect, _TemplatedMixin, widget, templated, i18n, template) { 
    return declare("FogPanel.InstanceStatus", [_TemplatedMixin, widget, templated ], {
        templateString: template,             
        postCreate : function() {
          this.startInstanceNode.onclick = this.onStartInstance;    
          this.stopInstanceNode.onclick = this.onStopInstance;    
          this.rebootInstanceNode.onclick = this.onRebootInstance;    
          this.restoreInstanceNode.onclick = this.onRestoreInstance;    
          this.deleteInstanceNode.onclick = this.onDeleteInstance;   
          this.instanceMigrateNode.onclick = this.onMigrateInstance;
      },
      postMixInProperties: function() {
          this.inherited(arguments);
          var labels = lang.mixin({instanceStatus: i18n.getLocalization("FogPanel", "translator", this.lang).greeting},          
          i18n.getLocalization("FogPanel.themes.InstanceStatus", "InstanceStatusTranslator", this.lang));          
          var prop;          
          for(prop in labels) { 
              this[prop];
              if(!this[prop + "status"]) {
                  this[prop + "Status"] = labels[prop];                
              }
          }
      },
      onStartInstance : function() {          
      },
      onStopInstance : function() {          
      },
      onRebootInstance : function() {
      },
      onRestoreInstance : function() {
      },        
      onDeleteInstance : function() {
      },
      onMigrateInstance : function() {
      },
      enableRunningState : function() {
          this.startInstanceContainer.style.display = "none";
          this.restoreInstanceContainer.style.display = "none";
      },          
      enableStopState : function() {
//          this.startInstanceNode.className = "span2 spaceIcon";
          this.restoreInstanceContainer.style.display = "none";
          this.stopInstanceContainer.style.display = "none";
          this.rebootInstanceContainer.style.display = "none";              
      },          
      enableRestoreState : function() {
          this.startInstanceContainer.style.display = "none";
          this.restoreInstanceContainer.style.display = "block";
          this.stopInstanceContainer.style.display = "none";
          this.rebootInstanceContainer.style.display = "none";     
          this.deleteInstanceContainer.style.display = "none";
      },
      adminEnableStopState : function() {
//         this.startInstanceNode.className = "span2 spaceIcon";
         this.restoreInstanceContainer.style.display = "none";
         this.stopInstanceContainer.style.display = "none";
         this.deleteInstanceContainer.style.display = "none";
         this.rebootInstanceContainer.style.display = "none";  
     },
     adminEnableRunningState : function() {
         this.startInstanceContainer.style.display = "none";
         this.restoreInstanceContainer.style.display = "none";
         this.deleteInstanceContainer.style.display = "none";
     },
     disableAll: function() {
         this.startInstanceContainer.style.display = "none";
         this.restoreInstanceContainer.style.display = "none";
         this.stopInstanceContainer.style.display = "none";
         this.rebootInstanceContainer.style.display = "none"; 
         this.deleteInstanceContainer.style.display = "none";
         this.instanceMigrateContainer.style.display = "none";
     },
     enableDeleteStat : function () {
         this.startInstanceContainer.style.display = "none";
         this.restoreInstanceContainer.style.display = "none";
         this.stopInstanceContainer.style.display = "none";
         this.rebootInstanceContainer.style.display = "none";         
         this.instanceMigrateContainer.style.display = "none";
     },
     disableMigrateHost:function() {
         this.instanceMigrateContainer.style.display = "none";
     } 
 });
 });

          