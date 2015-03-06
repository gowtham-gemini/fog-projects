require({cache:{
'url:FogPanel/templates/VPNStatus.html':"<div class=\"VPNStatusContainer\"> \n    <div class=\"row-fluid grind-icons\">\n        <a class=\"span2\" data-dojo-attach-point=\"configVPNContainer\" >              \n            <img title=\"${configStatus}\" data-dojo-attach-point=\"configNode\"  src=\"images/config.png\"/>\n        </a>        \n        <a class=\"span2\" data-dojo-attach-point=\"rebootInstanceContainer\">\n            <img title=\"${rebootStatus}\" data-dojo-attach-point=\"rebootInstanceNode\" src=\"js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_reboot_icon.png\"/>\n        </a>\n        <a class=\"span2\" data-dojo-attach-point=\"vpnEditContainer\">\n            <img title=\"${editStatus}\" data-dojo-attach-point=\"editVpnNode\" src=\"images/edit.png\"/>\n        </a>\n        <a class=\"span2\" data-dojo-attach-point=\"vpnDeleteContainer\">\n            <img title=\"${deleteItemStatus}\" data-dojo-attach-point=\"deleteVpnNode\" src=\"js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png\">\n        </a> \n        <a class=\"span2 hide_text\" data-dojo-attach-point=\"vpnAclContainer\">\n            <img data-dojo-attach-point=\"changeACLNode\" src=\"images/network_acl_icon.png\">\n        </a> \n    </div>    \n</div>"}});
define("FogPanel/VPNStatus", [
    "dojo/_base/lang",
    "dojo/_base/declare",  
    "dojo/_base/connect",
    "dijit/_TemplatedMixin",
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/i18n",
    "dojo/text!FogPanel/templates/VPNStatus.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator",
    "dijit/form/Button"
], function(lang, declare, connect, _TemplatedMixin, widget, templated, i18n, template) { 
    return declare("FogPanel.VPNStatus", [_TemplatedMixin, widget, templated ], {
        templateString: template,   
        enableACLRuleStat : false,
        editTile : "",
        deleteTile : "",
        aclStat : "",
        postCreate : function() {
          this.configVPNContainer.onclick = this.onConfigVPN;    
          this.rebootInstanceContainer.onclick = this.onRebootVPN;    
          this.vpnEditContainer.onclick = this.onEditVPN;    
          this.vpnDeleteContainer.onclick = this.onDeleteVPN;  
          this.vpnAclContainer.onclick = this.onAclVPN;  
          
          if(this.enableACLRuleStat === true) {              
              this.configVPNContainer.style.display = "none";
              this.rebootInstanceContainer.style.display = "none";

              this.vpnEditContainer.className = "span3";    
              this.vpnDeleteContainer.className = "span3"; 
              this.editVpnNode.title = this.editTile;  
              this.deleteVpnNode.title = this.deleteTile;
          }
          if(this.enableVPNConnectionStat === true) {              
              this.configVPNContainer.style.display = "none";
              this.vpnEditContainer.style.display = "none";

              this.rebootInstanceContainer.className = "span3";    
              this.vpnDeleteContainer.className = "span3 offset3"; 
            }
            if(this.enableVPCPrivateGateway === true) {              
              this.rebootInstanceContainer.style.display = "none";
              this.vpnEditContainer.style.display = "none";

              this.configVPNContainer.className = "span3";    
              this.vpnDeleteContainer.className = "span3 offset3"; 
            }
            if(this.forTier === true) { 
                this.configNode.title = this.changeAcl;  
            }
            if(this.aclStat === true) {
                this.configVPNContainer.className = "hide_text";                
                this.vpnAclContainer.className = "span3"; 
                this.vpnAclContainer.title = this.changeAcl;                  
            }
      },
      postMixInProperties: function() {
          this.inherited(arguments);
          var labels = lang.mixin({instanceStatus: i18n.getLocalization("FogPanel", "translator", this.lang).greeting},          
          i18n.getLocalization("FogPanel.themes.VPNStatus", "VPNStatusTranslator", this.lang));          
          var prop;          
          for(prop in labels) { 
              this[prop];
              if(!this[prop + "status"]) {
                  this[prop + "Status"] = labels[prop];                
              }
          }
      },
      onConfigVPN : function () {},           
      onRebootVPN : function() {},              
      onDeleteVPN : function() {},      
      onEditVPN : function() {},
      onAclVPN : function () {},
      enableOnlyDeleteMode : function () {
            this.configVPNContainer.style.display = "none";
            this.rebootInstanceContainer.style.display = "none";
            this.vpnEditContainer.style.display = "none";
            this.vpnAclContainer.style.display = "none";
            
            this.configVPNContainer.className = "";   
            this.rebootInstanceContainer.className = "";   
            this.vpnEditContainer.className = "";   
            this.vpnAclContainer.className = "";   
            
      },
      removeAclMode : function (aclStat) {
          if(aclStat === "APP") {
              this.vpnAclContainer.style.display = "none";
              this.vpnAclContainer.className = "";  
          }         
      }
  });
 });

          