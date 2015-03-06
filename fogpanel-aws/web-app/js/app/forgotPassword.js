"use strict";
var forgotPassword = {
    'getContextPath': function() {
            return forgetPasswordBaseConfig.context;
    },
    'setConfig': function(config) {
        for (var param in config) {
            forgetPasswordBaseConfig[param] = config[param];

        }
    }
};

var forgetPasswordBaseConfig = {
    'baseURL': "/FogPanel/",
    'homepage': "#/",
    'context': "FogPanel",
    locale: '${lang}'   
};

forgotPassword.userName = {       
    require: function() {
        var baseURL = forgetPasswordBaseConfig.baseURL;
        require([
            "dojo",
            "dojo/i18n",
            "dojo/i18n!"+baseURL+"/js/dojo-1.8/FogPanel/nls/translator.js",
            "dojo/dom",            
            "dojo/store/JsonRest",
            "dojo/data/ItemFileWriteStore",
            "dijit/registry",             
            "dojox/widget/Toaster",
            "dijit/form/Button",
            "dijit/dijit",            
            "dijit/form/TextBox",
            "dijit/form/Form",                       
            "dijit/form/ValidationTextBox",
            "dojox/form/PasswordValidator",
            "dojo/query",
            "dojox/validate",            
            "dojox/validate/regexp"], 
        function(dojo, i18n, translator, dom, JsonRestStore, ItemFileWriteStore, registry) {
            window.translator = translator;    
            window.JsonRestStore = JsonRestStore;       
            window.ItemFileWriteStore = ItemFileWriteStore;            
            window.registry = registry;                       
        });
    },
    resetPassword : function() {
        var email = dijit.byId("forgotPasswordEmail");
        var status = true;
        if (email.validate && !email.validate()) {
            email.focus();  
            dijit.byId('forgotPasswordButton').set('style', { display: 'inline'});   
            dijit.byId('cancelForgotPass').set('style', { display: 'inline'});   
            dojo.byId("forgotPasswordLoader").style.display = "none";
            status = false;            
        }
        if(status == true) {
            dijit.byId('forgotPasswordButton').set('style', { display: 'none'});   
            dijit.byId('cancelForgotPass').set('style', { display: 'none'});   
            dojo.byId("forgotPasswordLoader").style.display = "block";
            var EmailRestoreStore = new JsonRestStore({target: forgotPassword.getContextPath()+"/api/public/forgotPassword/sendEmail"});
            EmailRestoreStore.add({
                email: email.getValue()
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.Result == "OK") {                
                        registry.byId("forgotToaster").setContent(translator.common.account.mailSentSucessMessage, "message");
                        registry.byId("forgotToaster").show();
                        dijit.byId('forgotPasswordButton').set('style', { display: 'inline'});  
                        dijit.byId('cancelForgotPass').set('style', { display: 'inline'});                        
                        dojo.byId("forgotPasswordLoader").style.display = "none";
                        email.reset();
                    } else {
                        dijit.byId('forgotPasswordButton').set('style', { display: 'inline'}); 
                        dijit.byId('cancelForgotPass').set('style', { display: 'inline'});  
                        dojo.byId("forgotPasswordLoader").style.display = "none";
                        dojo.byId('cancelForgotPass').style.display = "inline";
                        registry.byId("forgotToaster").setContent(resultData.Result, "error");
                        registry.byId("forgotToaster").show();                    
                    }
                });
            });
        }
    },  
    resendconfirmMail: function() {
        var email = dijit.byId("forgotPasswordEmail");
        var status = true;
        if (email.validate && !email.validate()) {
            email.focus();  
            dijit.byId('forgotPasswordButton').set('style', { display: 'block'});             
            dojo.byId("forgotPasswordLoader").style.display = "none";
            status = false;
            
        }
        if(status == true) {
             dijit.byId('forgotPasswordButton').set('style', { display: 'none'});           
            dojo.byId("forgotPasswordLoader").style.display = "block";
           var EmailRestoreStore = new JsonRestStore({target: forgotPassword.getContextPath()+"/api/public/resendconfirmMail"});
           EmailRestoreStore.add({
            email: email.getValue()
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.Result == "OK") {                
                    registry.byId("forgotToaster").setContent(translator.common.account.mailSentSucessActivationMessage, "message");
                    registry.byId("forgotToaster").show();
                     dijit.byId('forgotPasswordButton').set('style', { display: 'block'});           
                    dojo.byId("forgotPasswordLoader").style.display = "none";
                    email.reset();
                } else {
                    dijit.byId('forgotPasswordButton').set('style', { display: 'block'});           
                    dojo.byId("forgotPasswordLoader").style.display = "none";
                    registry.byId("forgotToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("forgotToaster").show();
                    
                }  
            });
        });
        }
    }  
    };

window.forgotPassword = forgotPassword;