require({cache:{
'url:FogPanel/templates/AccountStatus.html':"<div class=\"row-fluid AccountStatusContainer\">\n<div class=\"span12\">\n    <div class=\"span1\" data-dojo-attach-point=\"lockAccountNode\"><img title=\"Lock\" src=\"js/dojo-1.8/FogPanel/themes/AccountStatus/images/user_lock_icon.png\"></div>\n    <div class=\"span1\" data-dojo-attach-point=\"disableAccountNode\"><img title=\"Disable Account\" src=\"js/dojo-1.8/FogPanel/themes/AccountStatus/images/user_disable_icon.png\"/></div>\n    <div class=\"span1\" data-dojo-attach-point=\"enableAccountNode\"><img title=\"Enable Account\" src=\"js/dojo-1.8/FogPanel/themes/AccountStatus/images/user_enable_icon.png\"/></div>\n    <div class=\"span1\" data-dojo-attach-point=\"cancelAccountNode\"><img title=\"Cancel Account\" src=\"js/dojo-1.8/FogPanel/themes/AccountStatus/images/user_cancel_icon.png\"/></div>\n    <div class=\"span1\" data-dojo-attach-point=\"suspendAccountNode\"><img title=\"Suspend Account\" src=\"js/dojo-1.8/FogPanel/themes/AccountStatus/images/user_suspend_icon.png\"></div>\n    <div class=\"span1\" data-dojo-attach-point=\"resetPasswordNode\"><a><img title=\"Reset Password\" src=\"js/dojo-1.8/FogPanel/themes/AccountStatus/images/user_passrest_icon.png\"></a>\n    </div>\n    <div class=\"span1\" data-dojo-attach-point=\"unlockAccountNode\"><img title=\"Unlock\" src=\"js/dojo-1.8/FogPanel/themes/AccountStatus/images/user_unlock_icon.png\"></div>\n    <div class=\"span1\" ><a href=\"#\" data-dojo-attach-point=\"loginAccountNode\"><img title=\"Login Account\" src=\"js/dojo-1.8/FogPanel/themes/AccountStatus/images/user_login_icon.png\"/></a></div>\n</div>\n</div>"}});
define("FogPanel/AccountStatus", [
    "dojo/_base/declare",   
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/text!FogPanel/templates/AccountStatus.html"
], function( declare,  widget, templated, template) {
          return declare("FogPanel.AccountStatus", [widget, templated], {
          templateString: template,
                  
          postCreate : function() {
            
            this.disableAccountNode.onclick = this.onDisableAccount;    
            this.enableAccountNode.onclick = this.onEnableAccount;    
            this.cancelAccountNode.onclick = this.onCancelAccount;    
            this.suspendAccountNode.onclick = this.onSuspendAccount;    
            this.loginAccountNode.onclick = this.onLoginAccount; 
            this.resetPasswordNode.onclick = this.onResetPassword;
            this.lockAccountNode.onclick = this.onLockAccount;
            this.unlockAccountNode.onclick = this.onUnLockAccount;
                          
            this.disableAccountNode.style.display = "none";
            this.enableAccountNode.style.display = "none";
            this.cancelAccountNode.style.display = "none";
            this.suspendAccountNode.style.display = "none"; 
              
          } ,
          onDisableAccount : function() {
              
          },
          onEnableAccount : function() {
              
          },
          onCancelAccount : function() {
              
          },
          onSuspendAccount : function() {
              
          },
          onResetPassword : function() {
              
          },
          onLoginAccount : function() {
              
          },
          onLockAccount : function() {
              
          },
          onUnLockAccount : function() {
              
          },
          disableState : function() {
            this.disableAccountNode.style.display = "none";
//            this.enableAccountNode.style.display = "none";
            this.suspendAccountNode.style.display = "none"; 
//            this.loginAccountNode.style.display = "none"; 
//            this.resetPasswordNode.style.display = "none"; 
          },
          
          activeState : function() {
            this.enableAccountNode.style.display = "none";
          },
          
          cancelState : function() {
            this.disableAccountNode.style.display = "none";
            this.cancelAccountNode.style.display = "none";
            this.suspendAccountNode.style.display = "none"; 
//            this.loginAccountNode.style.display = "none";  
//            this.resetPasswordNode.style.display = "none"; 
          },
          suspendState : function() {
            this.disableAccountNode.style.display = "none";
            this.suspendAccountNode.style.display = "none"; 
//            this.loginAccountNode.style.display = "none";   
//            this.resetPasswordNode.style.display = "none"; 
          },
          disableAll: function() {
              this.disableAccountNode.style.display = "none";
              this.enableAccountNode.style.display = "none";
              this.cancelAccountNode.style.display = "none";
              this.suspendAccountNode.style.display = "none"; 
              this.loginAccountNode.style.display = "none";
              this.resetPasswordNode.style.display = "none";
          },
          setLoginLink: function(data){
            this.loginAccountNode.href = data;
          },
          lock: function() {
            this.unlockAccountNode.style.display = "none";
            this.loginAccountNode.style.display = "none";   
          },
          unLock: function() {
            this.lockAccountNode.style.display = "none";
            this.unlockAccountNode.style.display = "none";
          } 
          
     });
 });

          