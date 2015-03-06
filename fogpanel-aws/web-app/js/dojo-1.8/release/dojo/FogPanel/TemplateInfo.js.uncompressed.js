require({cache:{
'url:FogPanel/templates/TemplateInfo.html':"<li class=\"tpl-box-cont span3\">\n    <div class=\"tpl-box-logo\"><img src=\"\" data-dojo-attach-point=\"osLogo\"/></div>\n    <div class=\"tpl-box-zone\" data-dojo-attach-point=\"templateZoneInfo\"><span>@</span><span data-dojo-attach-point=\"templateZoneName\"></span></div>\n    <div class=\"tpl-box-price\">\n        <sup data-dojo-attach-point=\"tempCurrency\"></sup>  \n        <span data-dojo-attach-point=\"tempCost\"></span>\n    </div>\n    <div class=\"tpl-box-title overflow_label_tmpl\" data-dojo-attach-point=\"templateNameLabel\"></div>\n        \n    <div class=\"tpl-box-desc\" ><span class=\"desc_tag\">${tempDescInfo} : </span><span class=\"overflow_label_tmpl desc-text\" data-dojo-attach-point=\"tempDesc\"></span></div>\n    <div class=\"tpl-box-launchbtn\"><a title=\"${launchVMLabelInfo}\" data-dojo-attach-point=\"createVMLabel\" class=\"btn-glow primary new-product\" href=\"\"><img src=\"images/vm_quicklch_icon.png\"></img>${launchVMLabelInfo}</a></div>\n    <div class=\"tpl-box-morebtn\" data-dojo-attach-point=\"moreOptionTag\">${moreOptionInfo} &#43;</div>\n</li>"}});
define("FogPanel/TemplateInfo", [
    "dojo/_base/lang",
    "dojo/_base/declare",  
    "dojo/_base/connect",
    "dijit/_TemplatedMixin",
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/i18n",
    "dojo/text!FogPanel/templates/TemplateInfo.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator",
    "dijit/form/Button"
], function(lang, declare, connect, _TemplatedMixin, widget, templated, i18n, template) { 
    return declare("FogPanel.TemplateInfo", [_TemplatedMixin, widget, templated ], {
        templateString: template,       
        osLogoPath : "",        
        templateCost : "",
        templateCurrency : "",
        templateName : "",
        templateDesc : "",
        path : "",
        zoneName : "",
        postCreate : function() { 
            this.osLogo.src = this.osLogoPath;
            this.tempCost.innerHTML = this.templateCost;
            this.tempCurrency.innerHTML = this.templateCurrency;
            this.templateNameLabel.innerHTML = this.templateName;
            this.tempDesc.innerHTML = this.templateDesc;
            this.moreOptionTag.onclick = this.onMoreOptionClick;
            this.createVMLabel.href = this.path;
            this.templateZoneName.innerHTML = this.zoneName;
        },
        postMixInProperties: function() {
            this.inherited(arguments);
            var labels = lang.mixin({instanceStatus: i18n.getLocalization("FogPanel", "translator", this.lang).greeting},          
            i18n.getLocalization("FogPanel.themes.TemplateInfo", "TempateInfoTranslator", this.lang));          
            var prop;          
            for(prop in labels) { 
                this[prop];
                if(!this[prop + "info"]) {
                    this[prop + "Info"] = labels[prop];                
                }
            }
        },
        onMoreOptionClick : function () {
            
        }
    });
    });

          