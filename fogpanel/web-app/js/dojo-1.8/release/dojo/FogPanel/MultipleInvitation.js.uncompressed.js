require({cache:{
'url:FogPanel/templates/MultipleInvitation.html':"<div class=\"row-fluid multiple_invitation\" data-dojo-attach-point=\"mulipleWidgetContainer\">\n    <div class=\"span4 field-box control-group zone-cost-boxcont\">\n        <label class=\"control-label\">${inviUserNameText}<span class=\"require\">*</span></label>\n        <div class=\"controls\">\n            <input type=\"text\" data-dojo-attach-point=\"invitationName\">\n        </div>\n    </div>\n    <div class=\"span4 field-box control-group zone-cost-boxcont\">\n        <label class=\"control-label\">${inviEmailText}<span class=\"require\">*</span></label>\n        <div class=\"controls\">\n            <input type=\"text\" data-dojo-attach-point=\"invitationEmail\">\n        </div>\n    </div>\n    <div class=\"span4 field-box control-group zone-cost-boxcont signupRadionList\" data-dojo-attach-point=\"signupTypeContainer\">\n        <label for=\"instanceZone\" class=\"control-label\">${inviSignTypeText}:<span class=\"require\">*</span></label>\n        <div class=\"controls\">\n            <input type=\"radio\" data-dojo-attach-point=\"signupTypeTrial\"/>\n            <label style=\"margin-right: 35px;\">${inviTrialText}</label> \n            <input type=\"radio\" data-dojo-attach-point=\"signupTypeRetail\"/> \n            <label class=\"\">${inviRetailText}</label>  \n        </div>\n        <div class=\"pull-right span1 hide_text\" data-dojo-attach-point=\"invitationDeleteContent\"><a title=\"${inviDaleteText}\" data-dojo-attach-point=\"invitationDeleteOption\"><img src=\"images/errorMessage.jpg\" alt=\"loading\"/></a></div>\n    </div>   \n</div>\n"}});
define("FogPanel/MultipleInvitation", [
    "dojo/_base/lang",
    "dojo/_base/declare",  
    "dojo/_base/connect",
    "dijit/_TemplatedMixin",
    "dojo", 
    "dijit/dijit",
    "dojo/query",
    "dijit/form/RadioButton",
    "dijit/form/ValidationTextBox", 
    "dojo/dom-construct", 
    "dojo/dom-class",
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/i18n",
    "dojo/text!FogPanel/templates/MultipleInvitation.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator",
    "dijit/form/Button"
], function(lang, declare, connect, _TemplatedMixin, dojo, dijit, query, RadioButton, ValidationTextBox, domConstruct, domClass, widget, templated, i18n, template) { 
    return declare("FogPanel.MultipleInvitation", [_TemplatedMixin, widget, templated ], {
        templateString: template,  
        trialRadioWidget : "",
        retailRadioWidget : "",
        emailWidget : "",
        userNameWidget : "",   
        deleteOption : false,
        postCreate : function() { 
            var widget = this;
            var trialRadion = new RadioButton({
                checked: true,
                value: "trial",
                name: this.id+ "_" +"storageType"
            }, this.signupTypeTrial);
            
            var retailRadion = new RadioButton({                
                value: "retail",
                name: this.id+ "_" +"storageType"
            }, this.signupTypeRetail);
            
            var email = new ValidationTextBox({
                required: true,       
                regExp: dojox.validate.regexp.emailAddress,
                onChange : function () {
                    widget.onEmailWidgetChange(this, widget);
                },
                onKeyUp : function () {widget.onEmailWidgetChange(this, widget);}
            }, this.invitationEmail);
            
            var userName = new ValidationTextBox({
                required: true,       
                regExp: '[A-za-z 0-9-./%]{2,20}'
            }, this.invitationName); 
            
            if(this.deleteOption === true) {
                domClass.remove(this.invitationDeleteContent, "hide_text");
            } else {
                domClass.add(this.invitationDeleteContent, "hide_text");
            }
            this.invitationDeleteOption.onclick = function () {
                widget.destroyCurrentWidget();
            }            
            domClass.add(this.signupTypeContainer, this.id+ "_" + "signup_type");
            this.trialRadioWidget = trialRadion;
            this.retailRadioWidget = retailRadion;
            this.emailWidget = email;
            this.userNameWidget = userName;            
        },
        postMixInProperties: function() {
            this.inherited(arguments);
            var labels = lang.mixin({instanceStatus: i18n.getLocalization("FogPanel", "translator", this.lang).greeting},          
            i18n.getLocalization("FogPanel.themes.MultipleInvitation", "MultipleInvitationTranslator", this.lang));          
            var prop;          
            for(prop in labels) { 
                this[prop];
                if(!this[prop + "text"]) {
                    this[prop + "Text"] = labels[prop];                
                }
            }
        },
        showEmailError : function () {             
            this.emailWidget.validator = function() {return false;}; 
            this.emailWidget.set("invalidMessage" , "Email must be unique"); 
        },
        hideEmailError : function () {            
            this.emailWidget.validator = function() {return true;};
        },
        onEmailWidgetChange : function () {            
        },
        getCurrentEmailWidget : function () {
            return this.emailWidget;
        },
        getFieldsValue : function () {             
            var formElements = query("."+this.id + "_"+"signup_type"+" " +"input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var signupType = dijit.byId(checkedRadioId).value;                     
            var widgetFieldsList = {email: this.emailWidget.value, userName: this.userNameWidget.value, signupType: signupType};
            return widgetFieldsList;
        },
        validateFields : function () {          
            var widgets = dijit.registry.findWidgets(this.mulipleWidgetContainer);
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
        clearEmail : function () {
            this.emailWidget.reset();
        },
        destroyCurrentWidget : function () {          
            this.destroyRecursive();    
        }
    });
});

          