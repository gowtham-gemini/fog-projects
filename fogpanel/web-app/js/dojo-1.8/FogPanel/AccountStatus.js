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

          