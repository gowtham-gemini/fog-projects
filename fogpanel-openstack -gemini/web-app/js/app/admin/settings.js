"use strict";
require([
    "dojo",
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js",
    "dijit/dijit",
    "dojo/dom-class",
    "dojo/dom-construct",
    "dojo/store/JsonRest",
    "dojo/query",
    "dijit/registry",
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojo/store/Memory",
    "dojox/grid/DataGrid",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "dijit/form/DateTextBox",
    "dijit/layout/TabContainer",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dijit/Editor",
    "dijit/layout/BorderContainer",
    "dijit/form/DropDownButton",
    "dijit/TooltipDialog",
    "dijit/form/HorizontalRule",
    "dijit/form/HorizontalRuleLabels",
    "dijit/form/HorizontalSlider",
    "dijit/form/Textarea",
    "Zone/ZoneCost",
    "dojox/widget/rotator/Slide",
    "dojox/widget/Rotator",
    "dijit/form/Button",
    "dojox/validate/regexp",
    "dijit/form/ValidationTextBox",
    "dijit/form/RadioButton",
    "List/ListItem",
    "dijit/Dialog"
],
function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
ItemFileWriteStore, Memory, DataGrid, ContentPane, Source, MultiSelect, dom, win) {
    window.query = query;
    window.translator = translator;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Memory = Memory;
    window.Select = Select;
    window.ContentPane = ContentPane;
    window.DataGrid = DataGrid;
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;
    window.isAdvGWSelected = 0;
    window.loadedADVGw = false;
    controller({
        name: "settings",
        module: "admin",
        filePath: "/js/app/admin/settings.js",
        layout: {
            name: "settings",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {
            core.ui.loadTemplate("configuration", core.ui.getContentId());
        }),
        "general": action(function() {
            var generalConfigPage = dijit.byId("generalConfigForm");
            if (generalConfigPage) {
                dijit.byId("generalConfigForm").destroyRecursive();
            }
            core.ui.loadTemplate("generalConfig", core.ui.getContentId());
        }),
        "zenoss": action(function() {
            if (dijit.byId("zenossConfigForm")) {
                dijit.byId("zenossConfigForm").destroyRecursive();
            }
            if (dijit.byId("configPropertieReloadDialog")) {
                dijit.byId("configPropertieReloadDialog").destroyRecursive();
            }
            core.ui.loadTemplate("zenossConfig", core.ui.getContentId());
            zenossConfig.init();
        }),
        "payment": action(function() {
            var generalConfigPage = dijit.byId("paymentCardConfigForm");
            if (generalConfigPage) {
                dijit.byId("paymentCardConfigForm").destroyRecursive();
            }
            core.ui.loadTemplate("paymentCardConfig", core.ui.getContentId());
            PaymentCardConfig.init();
            PaymentCardConfig.populateValues();
        }),
        "paymentConfig": action(function() {
            var currentPage = dijit.byId("paymentSettingForm");
            if (currentPage) {
                dijit.byId("paymentSettingForm").destroyRecursive();
            }
            core.ui.loadTemplate("paymentConfig", core.ui.getContentId());
            PaymentConfig.init();
            PaymentConfig.populateValues();
        }),
        "billingTypeConfig": action(function() {
            var currentPage = dijit.byId("billingTypeConfigForm");
            if (currentPage) {
                dijit.byId("billingTypeConfigForm").destroyRecursive();
            }
            core.ui.loadTemplate("billingTypeConfig", core.ui.getContentId());
            BillingTypeConfig.init();
            BillingTypeConfig.populateValues();
        }),
        "paypal": action(function() {
            var currentPage = dijit.byId("paypalForm");
            if (currentPage) {
                dijit.byId("paypalForm").destroyRecursive();

            }
            core.ui.loadTemplate("paypalConfig", core.ui.getContentId());
            PaypalConfig.init();
            PaypalConfig.populateValues();
        }),
        "ccAvenue": action(function() {
            var currentPage = dijit.byId("ccAvenueForm");
            if (currentPage) {
                dijit.byId("ccAvenueForm").destroyRecursive();

            }
            core.ui.loadTemplate("ccAvenueConfig", core.ui.getContentId());

            CCAvenueConfig.populateValues();
        }),
        "authorizeNet": action(function() {
            var currentPage = dijit.byId("authorizeNetForm");
            if (currentPage) {
                dijit.byId("authorizeNetForm").destroyRecursive();
            }
            core.ui.loadTemplate("authorizeNetConfig", core.ui.getContentId());
            AuthorizeNetConfig.init();
            AuthorizeNetConfig.populateValues();
        }),
        "billing": action(function() {
            var currentBillingConfigPage = dijit.byId("billingConfigForm");
            if (currentBillingConfigPage) {
                dijit.byId("billingConfigForm").destroyRecursive();
            }
            core.ui.loadTemplate("billingConfig", core.ui.getContentId());
        }),
        "organizationBilling": action(function() {
            var currentPage = dijit.byId("organizationDetailForm");
            if (currentPage) {
                dijit.byId("organizationDetailForm").destroyRecursive();                
            }
            core.ui.loadTemplate("organizationBillingConfig", core.ui.getContentId());
            OrganizationBilling.init();
            OrganizationBilling.populateValues();
        }),
        "cloudStack": action(function() {
            core.ui.loadTemplate("cloudStack", core.ui.getContentId());
        }),
        "openstack": action(function() {
            core.ui.loadTemplate("openstack", core.ui.getContentId());
        }),
        "openstackConfig": action(function() {
            
            if (dijit.byId("openstackConfigForm")) {
                dijit.byId("openstackConfigForm").destroyRecursive();
            }
            if (dijit.byId("configPropertieReloadDialog")) {
                dijit.byId("configPropertieReloadDialog").destroyRecursive();
            }
            core.ui.loadTemplate("openstackConfig", core.ui.getContentId());
            openstackConfig.init();
        }),
        "regionConfig": action(function() {
                    
            if (dijit.byId("addRegionForm")) {
                dijit.byId("addRegionForm").destroyRecursive();
            }
            if (dijit.byId("regionListForm")) {
                dijit.byId("regionListForm").destroyRecursive();
            }
            if (dijit.byId("pullRegionConfirm")) {
                dijit.byId("pullRegionConfirm").destroyRecursive();
            }
            if (dijit.byId("showDeleteRegionDialog")) {
                dijit.byId("showDeleteRegionDialog").destroyRecursive();
            }
            
            core.ui.loadTemplate("regionConfig", core.ui.getContentId());
            RegionConfig.init();
            RegionConfig.populateValues();
        }),
        "configSync": action(function(){
           ConfigSync.propertieReload(); 
        }),
        "usage": action(function() {
            core.ui.loadTemplate("usage", core.ui.getContentId());
            UsageConfig.init();

        }),
        "adminUser": action(function() {

            var addAccountProcessLoader = dijit.byId("addAccountProcessLoader2");
            if (addAccountProcessLoader) {
                dijit.byId("addAccountProcessLoader2").destroyRecursive();
            }
            if (dijit.byId("resetPasswordDialog")) {
                dijit.byId("resetPasswordDialog").destroyRecursive();
            }
            if (dijit.byId("showAPIKeyDialog")) {
                dijit.byId("showAPIKeyDialog").destroyRecursive();
            }
            if (dijit.byId("showResetKeysDialog")) {
                dijit.byId("showResetKeysDialog").destroyRecursive();
            }
            if (dijit.byId("showRevokeAPIAccessDialog")) {
                dijit.byId("showRevokeAPIAccessDialog").destroyRecursive();
            }
            if (dijit.byId("showGrantAPIAccessDialog")) {
                dijit.byId("showGrantAPIAccessDialog").destroyRecursive();
            }

            core.ui.loadTemplate("adminUser", core.ui.getContentId());
            AdminUser.init();
            AdminUser.populateValues();

        }),
        "addAdmin": action(function() {

            var addAdminUserForm = dijit.byId("addAdminUserForm");
            if (addAdminUserForm) {
                dijit.byId("addAdminUserForm").destroyRecursive();
                dijit.byId("addAccountProcessLoader").destroyRecursive();
            }

            core.ui.loadTemplate("addAdminUser", core.ui.getContentId());
            AddAdmin.init();
            AddAdmin.populateValues();

        }),
        "support": action(function() {
            core.ui.loadTemplate("support", core.ui.getContentId());

        }),
        "paymentGatway": action(function() {
            if (dijit.byId("paymentGatewayButton")) {
                dijit.byId("paymentGatewayButton").destroyRecursive();
            }
            core.ui.loadTemplate("paymentGatway", core.ui.getContentId());
            PaymentGatewayList.init();
        }),
        "credit": action(function() {
            var currentPage = dijit.byId("creditForm");
            if (currentPage) {
                currentPage.destroyRecursive();
            }
            core.ui.loadTemplate("credit", core.ui.getContentId());
            CreditCardInfo.init();
            CreditCardInfo.populateValues();

        }),
        "retail": action(function() {
            var currentPage = dijit.byId("retailManagementForm");
            if (currentPage) {
                currentPage.destroyRecursive();
                dijit.byId("retailCreditLimitEditConformationDialog").destroyRecursive();
            }
            if (dijit.byId("retailCreditLimitEditConformationDialog")) {
                dijit.byId("retailCreditLimitEditConformationDialog").destroyRecursive();
            }
            core.ui.loadTemplate("retailCreditSetting", core.ui.getContentId());
            RetailManagement.init();
            RetailManagement.populateValues();

        }),
        "creditLimitNotification": action(function() {
            var currentPage = dijit.byId("creditLimitNotificationForm");
            if (currentPage) {
                currentPage.destroyRecursive();
            }
            core.ui.loadTemplate("creditLimitNotificationSetting", core.ui.getContentId());
            CreditLimitNotification.init();
            CreditLimitNotification.populateValues();

        }),
        "invoice": action(function() {
            var currentPage = dijit.byId("invoiceForm");
            if (currentPage) {
                currentPage.destroyRecursive();
            }
            core.ui.loadTemplate("invoiceInfo", core.ui.getContentId()); 
            InvoiceInfo.init();
            InvoiceInfo.populateValues();

        }),
        "lateFee": action(function() {
            var currentPage = dijit.byId("lateFeeForm");
            if (currentPage) {
                currentPage.destroyRecursive();
            }
            core.ui.loadTemplate("lateFee", core.ui.getContentId());
            LateFeeInfo.init();
            LateFeeInfo.populateValues();
        }),
        "currency": action(function() {
            core.ui.loadTemplate("currency", core.ui.getContentId());
            CurrencyValueConfig.init();
        }),
        "email": action(function() {
            var currentMailConfigPage = dijit.byId("mailConfigForm");
            if (currentMailConfigPage) {
                dijit.byId("mailConfigForm").destroyRecursive();
            }

            core.ui.loadTemplate("mailConfig", core.ui.getContentId());
            MailConfig.init();
            MailConfig.populateValues();
        }),
        "loginSecurity": action(function() {
            var currentPage = dijit.byId("loginSecurityForm");
            if (currentPage) {
                currentPage.destroyRecursive();
            } 
            core.ui.loadTemplate("loginSecurity", core.ui.getContentId());
            LoginSecurityInfo.init();
            LoginSecurityInfo.populateValues();
        }),
        "language": action(function() {
            var language = dijit.byId("languageConfigForm");
            if (language) {
                dijit.byId("languageConfigForm").destroyRecursive();
            }
            core.ui.loadTemplate("languageConfig", core.ui.getContentId());
            LanguageConfig.init();
            LanguageConfig.populateValues();

        }),
        "signupSetting": action(function() {
            var generalConfigPage = dijit.byId("signupConfigForm");
            if (generalConfigPage) {
                dijit.byId("signupConfigForm").destroyRecursive();
            }
            core.ui.loadTemplate("signupConfig", core.ui.getContentId());
            SignupConfig.init();
            SignupConfig.populateValues();
        }),
        "volumeTypes": action(function() {
            
            if(dijit.byId("pullVolumeTypeConfirm")) {
                dijit.byId("pullVolumeTypeConfirm").destroyRecursive();
            }
            
            if(dijit.byId("volumeTypeListListForm")) {
                dijit.byId("volumeTypeListListForm").destroyRecursive();
            }
            if(dijit.byId("pullVolumeTypeButton")) {
                dijit.byId("pullVolumeTypeButton").destroyRecursive();
            }
            if(dijit.byId("pullVolumeTypeLoader")) {
                dijit.byId("pullVolumeTypeLoader").destroyRecursive();
            }
            
            core.ui.loadTemplate("volumeTypesConfig", core.ui.getContentId());
            VolumeTypesConfig.populateValues();
        }),
        "availabilityZones": action(function (){
            if(dijit.byId("pullAvailabilityZoneConfirm")) {
                dijit.byId("pullAvailabilityZoneConfirm").destroyRecursive();
            }
            
            if(dijit.byId("availabilityZoneListForm")) {
                dijit.byId("availabilityZoneListForm").destroyRecursive();
            }
            if(dijit.byId("pullAvailabilityZoneButton")) {
                dijit.byId("pullAvailabilityZoneButton").destroyRecursive();
            }
            
            core.ui.loadTemplate("availabilityZones", core.ui.getContentId());
            AvailabilityZones.populateValues();
        }),
        "importData": action(function() {
            
            if (dijit.byId("pullAllDataConfirm")) {
                dijit.byId("pullAllDataConfirm").destroyRecursive();
            }
            if (dijit.byId("pullPlanButton")) {
                dijit.byId("pullPlanButton").destroyRecursive();
            }
            if (dijit.byId("pullImageButton")) {
                dijit.byId("pullImageButton").destroyRecursive();
            }
            if (dijit.byId("pullVolumeTypeButton")) {
                dijit.byId("pullVolumeTypeButton").destroyRecursive();
            }
            if (dijit.byId("pullZoneButton")) {
                dijit.byId("pullZoneButton").destroyRecursive();
            }
            if (dijit.byId("pullRegionButton")) {
                dijit.byId("pullRegionButton").destroyRecursive();
            }
            if (dijit.byId("pullRegionsButton")) {
                dijit.byId("pullRegionsButton").destroyRecursive();
            }
            if (dijit.byId("pullDomainsButton")) {
                dijit.byId("pullDomainsButton").destroyRecursive();
            }
            if (dijit.byId("pullPlanConform")) {
                dijit.byId("pullPlanConform").destroyRecursive();
            }
            if (dijit.byId("pullImageConfirm")) {
                dijit.byId("pullImageConfirm").destroyRecursive();
            }
            if (dijit.byId("pullVolumeTypeConfirm")) {
                dijit.byId("pullVolumeTypeConfirm").destroyRecursive();
            }
            if (dijit.byId("pullZoneConfirm")) {
                dijit.byId("pullZoneConfirm").destroyRecursive();
            }
            if (dijit.byId("pullRegionsConfirm")) {
                dijit.byId("pullRegionsConfirm").destroyRecursive();
            }
            if (dijit.byId("pullDomainsConfirm")) {
                dijit.byId("pullDomainsConfirm").destroyRecursive();
            }
            if (dijit.byId("pullAccountButton")) {
                dijit.byId("pullAccountButton").destroyRecursive();
            }
            if (dijit.byId("pullUserButton")) {
                dijit.byId("pullUserButton").destroyRecursive();
            }
            if (dijit.byId("pullAccountConfirm")) {
                dijit.byId("pullAccountConfirm").destroyRecursive();
            }
            if (dijit.byId("pullUserConfirm")) {
                dijit.byId("pullUserConfirm").destroyRecursive();
            }
            if (dijit.byId("pullNetworkConform")) {
                dijit.byId("pullNetworkConform").destroyRecursive();
            }              
            if (dijit.byId("pullNetworkButton")) {
                dijit.byId("pullNetworkButton").destroyRecursive();
            }            
            core.ui.loadTemplate("importDataFromOpenstackPage", core.ui.getContentId());
            OpenstackConfig.testInResourcesImportPage();
//            ImportDataFromOpenstack.init();
        })
    });
});
var OpenstackConfig = {
    'testInResourcesImportPage' : function() {
        
        var openStackConfigCheck = new JsonRest({
            target: core.getContextPath() + "/api/config/isOpenStackConfigured"
        })
        
        openStackConfigCheck.query().then(function(data) {
            
            if(data === false) {
                dojo.byId("openStackNotConfiguredMsgImport").style.display = "block";
                dojo.byId("importDataFromOpenStack").style.display = "none";
            } else{
                dojo.byId("openStackNotConfiguredMsgImport").style.display = "none";
                dojo.byId("importDataFromOpenStack").style.display = "block";
            }
        });
    },
    
    
};
var AvailabilityZones ={ 
   'populateValues': function() {
      
//       var asyncJob = new JsonRest({
//            target: core.getContextPath() + "/api/asyncJob"
//        });
//        
//        asyncJob.query({jobType:"PULL_ZONE"}).then(function(data) {
//            if(data[0] === "OK") {
//                dojo.byId("pullZoneLoaderImage").style.display = "inline";
//                dijit.byId("pullAvailabilityZoneButton").set("disabled", true);
//            } else if(data[0] === "FALSE") {
//                dojo.byId("pullZoneLoaderImage").style.display = "none";
//                dijit.byId("pullAvailabilityZoneButton").set("disabled", false);
//            }
//        }); 
        
        var listRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone"
        });
    
        dojo.byId("availabilityZoneListGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
         var gridLayout = [[
                {'name': translator.common.id, 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '20%'},
                {'name': translator.common.available, 'field': 'available', 'width': '10%'}
            ]
        ];
        
        listRestStore.query().then(function(data) {

            if (data.length === 0) {
                dojo.byId("availabilityZoneListGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>" + translator.common.message.noZone + "</div>";
            } else {


                dojo.forEach(data, function(el) {

                    gridDataList.newItem({
                        id: el.id,
                        name: el.name,
                        available: el.available,
                    });

                });

                dojo.byId("availabilityZoneListGrid").innerHTML = "";


                var availabilityZoneGrid = new EnhancedGrid({
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });

                availabilityZoneGrid.placeAt("availabilityZoneListGrid");
                availabilityZoneGrid.startup();

            }
        });
   },
   'confirmPull': function() {
       dijit.byId("pullAvailabilityZoneConfirm").show();
   },
   'pull': function() {
       
        dijit.byId("pullAvailabilityZoneConfirm").hide();
       
        var pullZoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/pullFromOpenstack"
        });

        pullZoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullZoneLoaderImage").style.display = "inline";
                    dijit.byId("pullAvailabilityZoneButton").set("disabled", true);
                   
                } else {

                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
   },
   'cancelPull':function() {
       dijit.byId("pullAvailabilityZoneConfirm").hide();
   }
   
};


var SignupConfig = {
    'init': function() {
    },
    'populateValues': function() {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
        configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name === "signup.setting") {
                    if(config.value === "PUBLIC") {
                    dijit.byId("signupSettingPublic").set("checked", true);
                    } else if(config.value === "INVITATION_ONLY") {
                        dijit.byId("signupSettingInvitationOnly").set("checked", true);
                    } else if(config.value === "DISABLED") {
                        dijit.byId("signupSettingDisabled").set("checked", true);
                    }
                }
            });
        });
    },
    'update': function() {        
        var signupSettingStore = new JsonRest({
            target: core.getContextPath() + "/api/config/signupSetting"
        });
        
        var formElements = dojo.query("#signupSettingValue input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var signupSettingValue = dijit.byId(checkedRadioId).value;
        
        signupSettingStore.add({
            signupSettingValue: signupSettingValue
        }).then(function(resultData) {
            if (resultData[0] === "OK") {
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
                dojo.byId("configMsgId").style.display = "block";
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    }
};
var LanguageConfig = {
    'init': function() {
    },
    'populateValues': function() {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
        configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "default.language") {
                    dijit.byId("panelLanguage").set("value", config.value);
                }
            });
        });
        
        var currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"
        });
        
        currentAccRestStore.query().then(function (currentAdmin) {
            dijit.byId("adminUserDateFormatWidget").set("value", currentAdmin[0].dateFormat);
        });
    },
    'update': function() {        
        var languageStore = new JsonRest({
            target: core.getContextPath() + "/api/config/language"
        });

        languageStore.add({
            language: dijit.byId("panelLanguage").getValue(),
            dateFormat : dijit.byId("adminUserDateFormatWidget").getValue()
        }).then(function(resultData) {
            if (resultData == "OK") {
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
                dojo.byId("configMsgId").style.display = "block";
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    }
};

var CurrencyValueConfig = {
    'init': function() {
        var currency = new JsonRest({
            target: core.getContextPath() + "/api/config/currency"
        });

        currency.query().then(function(data) {
            dojo.forEach(data, function(cur) {
                dojo.byId("cur").innerHTML = cur.currency;
            });
        });
    }
};


var AddAdmin = {
    'init': function() {
    },
    'populateValues': function() {
    },
    'addUser': function() {
        var addAdminUserStore = new JsonRest({
            target: core.getContextPath() + "/api/account/addAdmin"
        });

        var userName = dijit.byId("adminUserName");
        var password = dijit.byId("confirmPassword2");

        var pageNode = dojo.byId("addAdminUserPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId("addAccountProcessLoader").show();
            addAdminUserStore.add({
                userName: userName.getValue(),
                password: password.getValue()
            }).then(function(data) {
                if (data == "OK") {
                    dijit.byId("addAccountProcessLoader").hide();
                    dijit.byId("addAdminUserForm").reset();
                    registry.byId('appToaster').setContent(translator.common.message.addedSuccess, 'message');
                    registry.byId('appToaster').show();
                    core.router.go("#/admin/settings/adminUser");
                } else {
                    registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                    registry.byId('appToaster').show();
                    dijit.byId("addAccountProcessLoader").hide();
                }
            });
        }
    },
    'checkPasswordStrength': function(id) {
        var pwd = dijit.byId(id).getValue();
        var strength_text = dojo.byId('strength_text');
        var strength_id = dojo.byId('strength_id');
        var progress_bar = dojo.byId('progress_bar');

        var strong = new RegExp('^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$', 'g');
        var medium = new RegExp('^(?=.{6,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$', 'g');
        var enough = new RegExp('(?=.{6,}).*', 'g');

        if (strength_text == null) {
            return;
        }
        strength_id.value = 0;
        var width = pwd.length * 10;

        if (pwd.length == 0) {
            strength_text.innerHTML = '&nbsp;';
            progress_bar.style.backgroundColor = '#FFFFFF';
        } else if (false == enough.test(pwd)) {
            strength_text.innerHTML = translator.common.password.tooShort;
            progress_bar.style.backgroundColor = '#DC143C';
        } else if (strong.test(pwd)) {
            strength_text.innerHTML = translator.common.password.strong;
            width = 100;
            progress_bar.style.backgroundColor = '#228B22';
            strength_id.value = 3;
        } else if (medium.test(pwd)) {
            strength_text.innerHTML = translator.common.password.medium;
            width = 70;
            progress_bar.style.backgroundColor = '#FF8C30';
            strength_id.value = 2;
        } else {
            width = 60;
            strength_text.innerHTML = translator.common.password.weak;
            progress_bar.style.backgroundColor = '#FFD700';
            strength_id.value = 1;
        }
        progress_bar.style.width = width + '%';
        if (pwd.length == 0) {
            dojo.byId('password_strength').style.display = "none";
        } else {
            dojo.byId('password_strength').style.display = "block";
            if (pwd.length < 8) {
                dijit.byId("newPassword").set("invalidMessage", translator.common.password.lengthConstrain);
                dijit.byId("confirmPassword2").reset();
                dijit.byId("confirmPassword2").validator = function() {};
            }
        }
    },
    'confirmPassword': function(confirmPassword) {
        var pass = dijit.byId("newPassword2").getValue();
        var status = true;
        var confirmPasswordValue = confirmPassword.getValue();
        confirmPassword.validator = function() {
            if (pass == confirmPasswordValue) {
                confirmPassword.set("invalidMessage", " ");
                status = true;
                return status;
            } else if (pass != confirmPasswordValue) {
                confirmPassword.set("invalidMessage", translator.common.password.notMatch);                
                status = false;
                return status;
            } else {
                return false;
            }
        };
    },
    'validateUserName': function() {
        var AccountStore = new JsonRest({target: core.getContextPath() + "/api/account/validateAdmin"});
        var userName = dijit.byId("adminUserName");
        AccountStore.query({
            username: userName.getValue()
        }).then(function(resultData) {
            dojo.forEach(resultData, function(el) {
                if (el) {
                    userName.validator = function() {
                        if (el == "TRUE") {
                            userName.set("invalidMessage", translator.common.username.valid);
                            return true;
                        } else if (el == "FAILED") {
                            userName.set("invalidMessage", translator.common.username.exists);
                            return false;
                        } else if (el == "FALSE") {
                            userName.set("invalidMessage", translator.common.username.inValid);
                            return false;
                        } else {
                            return false;
                        }
                    };
                } else {
                    return;
                }
            });
        });
    }
};

var AdminUser = {
    'init': function() {
    },
    'populateValues': function() {
        if (dijit.byId("adminUserDataGrid")) {
            dijit.byId("adminUserDataGrid").destroyRecursive();
        }
        var data = {
            items: []
        };
        dojo.byId("adminUserList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";
        var taxDataList = new ItemFileWriteStore({data: data});
        var taxDataLayout = [
            [
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '500px', datatype: "string", autoComplete: true},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var btn = "";
                        if (data.curUser == "false") {
                            btn = "<a class='btn-flat danger spacing' href='#/admin/settings/adminUser' onclick='AdminUser.deleteUser(" + data.adminId + ")'>" + translator.common.deleteData + "</a>";
                        }
                        if (data.apiAccess == "true") {
                            btn += "<a class='btn-flat danger spacing' href='#/admin/settings/adminUser' onclick='AdminUser.showResetPasswordForAdmin(" + data.adminId + ")'>" + translator.common.resetPassword + "</a>"
                                    + "<a class='btn-flat danger spacing' href='#/admin/settings/adminUser' onclick='AdminUser.showKeysForAdmin(" + data.adminId + ")'>" + translator.common.showApiKey + "</a>"
                                    + "<a class='btn-flat danger spacing' href='#/admin/settings/adminUser' onclick='AdminUser.showResetKeysForAdmin(" + data.adminId + ")'>" + translator.common.resetApiKey + "</a>"
                                    + "<a class='btn-flat danger spacing' href='#/admin/settings/adminUser' onclick='AdminUser.showRevokeAPIAccessForAdmin(" + data.adminId + ")'>" + translator.common.revokeApiKey + "</a>";
                        } else if (data.apiAccess == "false") {
                            btn += "<a class='btn-flat danger spacing' href='#/admin/settings/adminUser' onclick='AdminUser.showResetPasswordForAdmin(" + data.adminId + ")'>" + translator.common.resetPassword + "</a>"
                                    + "<a class='btn-flat danger spacing' href='#/admin/settings/adminUser' onclick='AdminUser.showGrantAPIAccessForAdmin(" + data.adminId + ")'>" + translator.common.grantApiKey + "</a>";
                        }
                        return btn;
                    }, 'width': '100%'}
            ]
        ];
        
        var listAdminUserStore = new JsonRest({
            target: core.getContextPath() + "/api/account/listAdmin"
        });

        listAdminUserStore.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("adminUserList").innerHTML = "";
                dojo.byId("noAdminMessageBox").style.display = "block";
            } else {
                dojo.byId("noAdminMessageBox").style.display = "none";
                dojo.forEach(data, function(adminUserData) {
                    taxDataList.newItem({
                        id: adminUserData.id,
                        name: adminUserData.userName,
                        action: {'adminId': adminUserData.id, 'curUser': adminUserData.currentAadmin, 'apiAccess': adminUserData.apiAccess}
                    });
                });
                dojo.byId("adminUserList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: 'adminUserDataGrid',
                    "class" : "span12",
                    store: taxDataList,
                    structure: taxDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminUserList");
                dataGrid.startup();
            }
        });
    },
    'deleteUser': function(id) {
        var listAdminUserStore = new JsonRest({
            target: core.getContextPath() + "/api/account/delete/"
        });

        dijit.byId("addAccountProcessLoader2").show();

        listAdminUserStore.put({id: id}).then(function(data) {
            if (data == "OK") {
                dijit.byId("addAccountProcessLoader2").hide();
                registry.byId('appToaster').setContent(translator.common.message.deletedSuccess, 'message');
                registry.byId('appToaster').show();
                AdminUser.populateValues();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
                dijit.byId("addAccountProcessLoader2").hide();
            }
        });
    },
    'showResetPasswordForAdmin': function(data) {
        dijit.byId("resetPasswordDialog").show();
        dijit.byId("resetPasswordForm").reset();
        dojo.byId("userId").value = data;
    },
    'resetPassword': function() {
        var userId = dojo.byId("userId").value;
        var resetPasswordStore = new JsonRest({
            target: core.getContextPath() + "/api/account/admin/resetPassword"
        });

        var pageNode = dojo.byId("resetPasswordFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId("addAccountProcessLoader2").show();
            resetPasswordStore.add({
                userId: userId,
                confirmPassword: dijit.byId("confirmPassword").getValue()
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (resultData.result == "OK") {
                        dijit.byId("addAccountProcessLoader2").hide();
                        dijit.byId("resetPasswordDialog").hide();
                        registry.byId("appToaster").setContent(translator.common.account.resetPasswordSucess, "message");
                        registry.byId("appToaster").show();
                    } else {
                        dijit.byId("addAccountProcessLoader2").hide();
                        registry.byId("appToaster").setContent(translator.common.account.resetPasswordError, "error");
                        registry.byId("appToaster").show();
                    }
                });
            });
        }
    },
    'closeResetPasswordDialog': function() {
        dijit.byId("resetPasswordDialog").hide();
    },
    'checkPasswordStrength': function(id) {
        var pwd = dijit.byId(id).getValue();
        var strength_text = dojo.byId('strength_text');
        var strength_id = dojo.byId('strength_id');
        var progress_bar = dojo.byId('progress_bar');

        var strong = new RegExp('^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$', 'g');
        var medium = new RegExp('^(?=.{6,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$', 'g');
        var enough = new RegExp('(?=.{6,}).*', 'g');

        if (strength_text == null) {
            return;
        }
        strength_id.value = 0;
        var width = pwd.length * 10;

        if (pwd.length == 0) {
            strength_text.innerHTML = '&nbsp;';
            progress_bar.style.backgroundColor = '#FFFFFF';
        } else if (false == enough.test(pwd)) {
            strength_text.innerHTML = translator.common.password.tooShort;
            progress_bar.style.backgroundColor = '#DC143C';
        } else if (strong.test(pwd)) {
            strength_text.innerHTML = translator.common.password.strong;
            width = 100;
            progress_bar.style.backgroundColor = '#228B22';
            strength_id.value = 3;
        } else if (medium.test(pwd)) {
            strength_text.innerHTML = translator.common.password.medium;
            width = 70;
            progress_bar.style.backgroundColor = '#FF8C30';
            strength_id.value = 2;
        } else {
            width = 60;
            strength_text.innerHTML = translator.common.password.weak;
            progress_bar.style.backgroundColor = '#FFD700';
            strength_id.value = 1;
        }
        progress_bar.style.width = width + '%';
        if (pwd.length == 0) {
            dojo.byId('password_strength').style.display = "none";
        } else {
            dojo.byId('password_strength').style.display = "block";
            if (pwd.length < 8) {
                dijit.byId("newPassword").set("invalidMessage", translator.common.password.lengthConstrain);
                dijit.byId("confirmPassword").reset();
                dijit.byId("confirmPassword").validator = function() {};
            }
        }
    },
    'confirmPassword': function(confirmPassword) {
        var pass = dijit.byId("newPassword").getValue();
        var status = true;
        var confirmPasswordValue = confirmPassword.getValue();
        confirmPassword.validator = function() {
            if (pass == confirmPasswordValue) {
                confirmPassword.set("invalidMessage", " ");
                status = true;
                return status;
            } else if (pass != confirmPasswordValue) {
                confirmPassword.set("invalidMessage", translator.common.password.notMatch);                
                status = false;
                return status;
            } else {
                return false;
            }
        };
    },
    'showKeysForAdmin': function(data) {
        dojo.byId("userId").value = data;
        var APIKeyRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/getAPIKey/"
        });
        APIKeyRestStore.get(data).then(function(resultData) {
            dojo.forEach(resultData, function(api) {
                dojo.byId("apiKeyValue").value = api.apiKey;
                dojo.byId("secretKeyValue").value = api.secret;
            });
        });
        dijit.byId("showAPIKeyDialog").show();
    },
    'resetKeysForAdmin': function(data) {
        var userId = dojo.byId("userId").value;
        var APIKeyRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/resetAPIKey/"
        });
        APIKeyRestStore.put({id: userId}).then(function(resultData) {
            dojo.forEach(resultData, function(api) {
                if (api == "OK") {
                    dijit.byId("showResetKeysDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.apiMessage.resetKey, "message");
                    registry.byId("appToaster").show();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    'revokeAPIAccess': function() {
        var userId = dojo.byId("userId").value;
        var APIKeyRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/revokeAPIKey/"
        });
        
        APIKeyRestStore.put({id: userId}).then(function(resultData) {
            dojo.forEach(resultData, function(api) {
                if (api == "OK") {
                    dijit.byId("showRevokeAPIAccessDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.apiMessage.revokeKey, "message");
                    registry.byId("appToaster").show();
                    window.location.reload();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    'grantAPIKey': function() {
        var userId = dojo.byId("userId").value;
        var APIKeyRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/grantAPIKey/"
        });
        APIKeyRestStore.put({id: userId}).then(function(resultData) {
            dojo.forEach(resultData, function(api) {
                if (api == "OK") {
                    dijit.byId("showGrantAPIAccessDialog").hide();
                    registry.byId("appToaster").setContent(translator.common.apiMessage.grantKey, "message");
                    registry.byId("appToaster").show();
                    window.location.reload();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    'showResetKeysForAdmin': function(data) {
        dijit.byId("showResetKeysDialog").show();
        dojo.byId("userId").value = data;
    },
    'showRevokeAPIAccessForAdmin': function(data) {
        dijit.byId("showRevokeAPIAccessDialog").show();
        dojo.byId("userId").value = data;
    },
    'showGrantAPIAccessForAdmin': function(data) {
        dijit.byId("showGrantAPIAccessDialog").show();
        dojo.byId("userId").value = data;
    },
    'closeResetAPIKeyDialog': function() {
        dijit.byId("showResetKeysDialog").hide();
    },
    'closeRevokeAPIKeyDialog': function() {
        dijit.byId("showRevokeAPIAccessDialog").hide();
    },
    'closeGrantAPIKeyDialog': function() {
        dijit.byId("showGrantAPIAccessDialog").hide();
    }
};

var LoginSecurityInfo = {
    _loginSecurityRestStore: "",
    _configRestStore: "",
    init: function() {
        this._loginSecurityRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/loginSecurity/"
        });

        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
    },
    populateValues: function() {
        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "MAX_LOGIN_FAILURE") {
                    dijit.byId("perAccount").setValue(config.value);
                } else if (config.name == "MAX_LOGIN_FAILURE_PER_IP") {
                    dijit.byId("perIp").setValue(config.value);
                } else if (config.name == "ACCOUNT_UNLOCK_TIME") {
                    dijit.byId("unlockTime").setValue(config.value);
                } else if (config.name == "LINK_ACTIVE_TIME") {
                    dijit.byId("linkActiveTime").setValue(config.value);
                }
            });
        });
    },
    add: function() {
        var linkActiveTime = dijit.byId("linkActiveTime");
        var time = dijit.byId("unlockTime");
        var perAccount = dijit.byId("perAccount");
        var perIp = dijit.byId("perIp");
        dijit.byId("mailConfigUpdate").setAttribute('style', 'display: none');
        dojo.byId("loginSecurityLoader").style.display = "block";
        this._loginSecurityRestStore.add({
            linkActiveTime: linkActiveTime.value,
            time: time.value,
            perAccount: perAccount.value,
            perIp: perIp.value
        }).then(function(result) {
            dijit.byId("mailConfigUpdate").setAttribute('style', 'display: block');
            dojo.byId("loginSecurityLoader").style.display = "none";
            if (result == "OK") {
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
                dojo.byId("configMsgId").style.display = "block";
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    },
    calcel: function() {
        core.router.go("#/admin/settings/general");
    }
};

var MailConfig = {
    _mailConfigStore: "",
    _testMailConfigtStore: "",
    _configRestStore: "",
    init: function() {
        this._mailConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/mailConfig/"
        });

        this._testMailConfigtStore = new JsonRest({
            target: core.getContextPath() + "/api/config/mailConfig/test/"
        });

        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/mailConfig"
        });
    },
    populateValues: function() {
        var host = dijit.byId("host");
        var port = dijit.byId("port");
        var from = dijit.byId("from");
        var ssl = dijit.byId("ssl");
        var userName = dijit.byId("userName");
        var password = dijit.byId("password");
        var applicationUrl = dijit.byId("applicationUrl");
        var senderName = dijit.byId("senderName");

        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                host.setValue(config.host);
                userName.setValue(config.username);
                password.setValue(config.password);
                port.setValue(config.port);
                from.setValue(config.from);
                applicationUrl.setValue(config.applicationUrl);
                senderName.setValue(config.senderName);
                if (config.ssl == "true" || config.ssl == true) {
                    ssl.set("checked", true);
                } else {
                    ssl.set("checked", true);
                }
            });
        });
    },
    add: function() {
        var host = dijit.byId("host");
        var port = dijit.byId("port");
        var from = dijit.byId("from");
        var ssl = dijit.byId("ssl");
        var userName = dijit.byId("userName");
        var password = dijit.byId("password");
        var applicationUrl = dijit.byId("applicationUrl");

        this._mailConfigStore.add({
            host: host.value,
            port: port.value,
            from: from.value,
            ssl: ssl.checked,
            userName: userName.value,
            password: password.value,
            applicationUrl: applicationUrl.value
        }).then(function(resultData) {
            if (resultData == "OK") {
                registry.byId('appToaster').setContent('Succesfully Verified and added!', 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/general");
    },
    test: function() {
        var host = dijit.byId("host");
        var port = dijit.byId("port");
        var from = dijit.byId("from");
        var ssl = dijit.byId("ssl");
        var userName = dijit.byId("userName");
        var password = dijit.byId("password");
        var applicationUrl = dijit.byId("applicationUrl");

        var node = dojo.byId("mailConfigPage");
        var nodeWidget = dijit.registry.findWidgets(node);

        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });

        this._testMailConfigtStore.add({
            host: host.value,
            port: port.value,
            from: from.value,
            ssl: ssl.checked,
            userName: userName.value,
            password: password.value,
            applicationUrl: applicationUrl.value
        }).then(function(resultData) {
            if (resultData == "OK") {
                MailConfig.add();
            } else {
                registry.byId('appToaster').setContent('Varification Failed', 'error');
                registry.byId('appToaster').show();
            }
        });
    }
};

var LateFeeInfo = {
    _lateFeeConfigStore: "",
    _configRestStore: "",
    init: function() {
        this._lateFeeConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/lateFee/"
        });
        
        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
    },
    populateValues: function() {
        var lateFeeAmount = dijit.byId("lateFeeAmount");
        var lateFeeAmountDescription = dojo.byId("lateFeeAmountDescription");

        var lateFeePercentage = dijit.byId("lateFeePercentage");
        var lateFeePercentageDescription = dojo.byId("lateFeePercentageDescription");

        var lateFeeApplicableAmount = dijit.byId("lateFeeApplicableAmount");
        var lateFeeApplicableAmountDescription = dojo.byId("lateFeeApplicableAmountDescription");
        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "late.fee.minimum.amount") {
                    lateFeeAmount.setValue(config.value);
                } else if (config.name == "late.fee.percendage") {
                    lateFeePercentage.setValue(config.value);
                } else if (config.name == "late.fee.applicable.amount") {
                    lateFeeApplicableAmount.setValue(config.value);
                }
            });
        });
    },
    add: function() {
        var lateFeeAmount = dijit.byId("lateFeeAmount");
        var lateFeePercentage = dijit.byId("lateFeePercentage");
        var lateFeeApplicableAmount = dijit.byId("lateFeeApplicableAmount");

        var generalConfigNode = dojo.byId("lateFeeInfoPage");
        var generalConfigWidget = dijit.registry.findWidgets(generalConfigNode);

        dojo.forEach(generalConfigWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        dijit.byId('lateFeeButton').set('style', {display: 'none'});
        dojo.byId("lateFeeLoader").style.display = "block";
        this._lateFeeConfigStore.add({
            lateFeeAmount: lateFeeAmount.value,
            lateFeePercentage: lateFeePercentage.value,
            lateFeeApplicableAmount: lateFeeApplicableAmount.value
        }).then(function(resultData) {
            dijit.byId('lateFeeButton').set('style', {display: 'block'});
            dojo.byId("lateFeeLoader").style.display = "none";
            if (resultData == "OK") {
                dojo.byId("configMsgId").style.display = "block";
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/billing");
    }
};

var InvoiceInfo = {
    _billingConfigStore: "",
    _configRestStore: "",
    init: function() {        
        this._billingConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/billingConfig/"
        });
        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/billing");
    },
    populateValues: function() {
        var billingPeriodDays = dijit.byId("billingPeriodDays");
        var isFixed = dijit.byId("isFixed");
        var fixedDay = dijit.byId("fixedDay");
        var paymentDueDays = dijit.byId("paymentDueDays");

        var isFixedDescription = dojo.byId("isFixedDescription");
        var fixedDayDescription = dojo.byId("fixedDayDescription");
        var paymentDueDaysDescription = dojo.byId("paymentDueDaysDescription");
        var billingPeriodDaysDescription = dojo.byId("billingPeriodDaysDescription");

        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "billing.cycle.fixed.enabled") {
                    if (config.value == "true" || config.value == true) {
                        isFixed.set("checked", true);
                        dojo.byId("fixedDayDiv").style.display = "block";
                        dojo.byId("fixedDayContainer").style.display = "block";
                        dojo.byId("billingPeriodDaysDiv").style.display = "none";
                        dojo.byId("billingPeriodDaysDescriptionContainer").style.display = "none";
                    } else {
                        isFixed.set("checked", false);
                        dojo.byId("fixedDayDiv").style.display = "none";
                        dojo.byId("fixedDayContainer").style.display = "none";
                        dojo.byId("billingPeriodDaysDiv").style.display = "block";
                        dojo.byId("billingPeriodDaysDescriptionContainer").style.display = "block";
                    }
                } else if (config.name == "billing.period.days") {
                    billingPeriodDays.setValue(config.value);
                } else if (config.name == "billing.period.fixed.day") {
                    fixedDay.setValue(config.value);
                } else if (config.name == "payment.due.days") {
                    paymentDueDays.setValue(config.value);
                } else if (config.name == "date.formate") {
                    dijit.byId("adminDateFormatWidget").set("value", config.value)
                }
            });
        });
    },
    add: function() {
        var billingPeriodDays = dijit.byId("billingPeriodDays");
        var isFixed = dijit.byId("isFixed");
        var fixedDay = dijit.byId("fixedDay");
        var paymentDueDays = dijit.byId("paymentDueDays");
        var billingConfigNode = dojo.byId("invoiceInfoPage");
        var billingConfigWidget = dijit.registry.findWidgets(billingConfigNode);
        var dateFormat = dijit.byId("adminDateFormatWidget");
        dojo.forEach(billingConfigWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        dijit.byId('invoiceButton').set('style', {display: 'none'});
        dojo.byId("invoiceLoader").style.display = "block";
        this._billingConfigStore.add({
            billingPeriodDays: billingPeriodDays.value,
            fixedEnabled: isFixed.checked,
            billingPeriodFixedDay: fixedDay.value,
            dueDays: paymentDueDays.value,
            dateFormat : dateFormat.value
        }).then(function(resultData) {
            dijit.byId('invoiceButton').set('style', {display: 'block'});
            dojo.byId("invoiceLoader").style.display = "none";
            if (resultData == "OK") {
                dojo.byId("configMsgId").style.display = "block";
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
                dijit.byId("billingConfigForm").reset();
            }
        });
    },
    enableFixed: function(currentElement) {
        if (currentElement.checked == true || currentElement.checked == "true") {
            dojo.byId("fixedDayDiv").style.display = "block";
            dojo.byId("billingPeriodDaysDiv").style.display = "none";
            dojo.byId("billingPeriodDaysDescriptionContainer").style.display = "none";
            dojo.byId("fixedDayContainer").style.display = "block";
        } else if (currentElement.checked == false || currentElement.checked == false) {
            dojo.byId("fixedDayDiv").style.display = "none";
            dojo.byId("fixedDayContainer").style.display = "none";
            dojo.byId("billingPeriodDaysDiv").style.display = "block";
            dojo.byId("billingPeriodDaysDescriptionContainer").style.display = "block";
        }
    }
};

var BillingConfig = {
    _billingConfigStore: "",
    _configRestStore: "",
    init: function() {
        this._billingConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/billingConfig/"
        });
        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
    },
    populateValues: function() {
        var billingPeriodDays = dijit.byId("billingPeriodDays");
        var isFixed = dijit.byId("isFixed");
        var fixedDay = dijit.byId("fixedDay");
        var paymentDueDays = dijit.byId("paymentDueDays");
        var creditLimitNotificationLevel1 = dijit.byId("creditLimitNotificationLevel1");
        var creditLimitNotificationLevel2 = dijit.byId("creditLimitNotificationLevel2");
        var creditLimitNotificationLevel3 = dijit.byId("creditLimitNotificationLevel3");

        var isFixedDescription = dojo.byId("isFixedDescription");
        var fixedDayDescription = dojo.byId("fixedDayDescription");
        var paymentDueDaysDescription = dojo.byId("paymentDueDaysDescription");
        var billingPeriodDaysDescription = dojo.byId("billingPeriodDaysDescription");
        var creditLimitNotificationLevel1Description = dojo.byId("creditLimitNotificationLevel1Description");
        var creditLimitNotificationLevel2Description = dojo.byId("creditLimitNotificationLevel2Description");
        var creditLimitNotificationLevel3Description = dojo.byId("creditLimitNotificationLevel3Description");

        this._configRestStore.query().then(function(resultData) {

            dojo.forEach(resultData, function(config) {

                if (config.name == "billing.cycle.fixed.enabled") {
                    if (config.value == "true" || config.value == true) {
                        isFixed.set("checked", true);
                        dojo.byId("fixedDayDiv").style.display = "block";
                        dojo.byId("billingPeriodDaysDiv").style.display = "none";
                        dojo.byId("billingPeriodDaysDescriptionContainer").style.display = "none";
                    }
                    else {
                        isFixed.set("checked", false);
                        dojo.byId("fixedDayDiv").style.display = "none";
                        dojo.byId("billingPeriodDaysDiv").style.display = "block";
                        dojo.byId("billingPeriodDaysDescriptionContainer").style.display = "block";

                    }
                    isFixedDescription.innerHTML = config.description;
                } else if (config.name == "billing.period.days") {
                    billingPeriodDays.setValue(config.value);
                    billingPeriodDaysDescription.innerHTML = config.description;
                } else if (config.name == "billing.period.fixed.day") {
                    fixedDay.setValue(config.value);
                    fixedDayDescription.innerHTML = config.description;
                } else if (config.name == "payment.due.days") {
                    paymentDueDays.setValue(config.value);
                    paymentDueDaysDescription.innerHTML = config.description;
                } else if (config.name == "credit.limit.notification.level1") {
                    creditLimitNotificationLevel1.setValue(config.value);
//                    creditLimitNotificationLevel1Description.innerHTML = config.description;
                } else if (config.name == "credit.limit.notification.level2") {
                    creditLimitNotificationLevel2.setValue(config.value);
//                    creditLimitNotificationLevel2Description.innerHTML = config.description;
                } else if (config.name == "credit.limit.notification.level3") {
                    creditLimitNotificationLevel3.setValue(config.value);
//                    creditLimitNotificationLevel3Description.innerHTML = config.description;
                }
//                   
            });
        });
    }

};


var CreditCardInfo = {
    _generalConfigStore: "",
    _configRestStore: "",
    init: function() {
        this._creditConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/trialManagement/"
        });
        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig"
        });
    },
    populateValues: function() {
        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "signup.type.trial.enabled") {
                    if (config.value == "true" || config.value == true) {
                        dijit.byId("trialEnabled").setAttribute('checked', true);
                        dojo.byId("trialLimit").style.display = "block";
                        dojo.byId("trialCreditLimitDescriptionDiv").style.display = "block";

                    } else {
                        dijit.byId("trialEnabled").setAttribute('checked', false);
                        dojo.byId("trialLimit").style.display = "none";
                        dojo.byId("trialCreditLimitDescriptionDiv").style.display = "none";
                    }
//                        dojo.byId("trialEnabledDescription").innerHTML = config.description;
                } else if (config.name == "signup.type.trial.credit.limit") {
                    dijit.byId("trialCreditLimit").setValue(config.value);
//                        dojo.byId("trialCreditLimitDescription").innerHTML = config.description;
                } else if (config.name == "instance.limit") {
                    dijit.byId("instanceLimit").setValue(config.value);
                } else if (config.name == "storage.limit") {
                    dijit.byId("storageLimit").setValue(config.value);
                } else if (config.name == "snapshot.limit") {
                    dijit.byId("snapshotLimit").setValue(config.value);
                } else if (config.name == "bandwidth.limit") {
                    dijit.byId("bandwidthLimit").setValue(config.value);

                }

            });
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/billing");
    },
    update: function() {
        var trialEnabled = dijit.byId("trialEnabled");
        var trialCreditLimit = dijit.byId("trialCreditLimit");
        var instanceLimit = dijit.byId("instanceLimit");
        var snapshotLimit = dijit.byId("snapshotLimit");
        var storageLimit = dijit.byId("storageLimit");
        var bandwidthLimit = dijit.byId("bandwidthLimit");

        var pageNode = dojo.byId("creditInfoPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId('creditButtton').set('style', {display: 'none'});
            dojo.byId("creditLoader").style.display = "block";
            this._creditConfigStore.add({
                trialEnabled: trialEnabled.checked,
                trialCreditLimit: trialCreditLimit.value,
                instanceLimit: instanceLimit.value,
                snapshotLimit: snapshotLimit.value,
                storageLimit: storageLimit.value,
                bandwidthLimit: bandwidthLimit.value
            }).then(function(resultData) {
                dijit.byId('creditButtton').set('style', {display: 'block'});
                dojo.byId("creditLoader").style.display = "none";
                if (resultData == "OK") {
                    dojo.byId("configMsgId").style.display = "block";
                    registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                    registry.byId('appToaster').show();
                } else {
                    registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                    registry.byId('appToaster').show();
                }
            });
        }
    }
};
var RetailManagement = {
    _generalConfigStore: "",
    _configRestStore: "",
    init: function() {
        this._creditConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/retailManagement/"
        });
        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig"
        });
    },
    populateValues: function() {
        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "signup.type.retail.credit.limit") {
                    dijit.byId("retailCreditLimit").setValue(config.value);
//                        dojo.byId("retailCreditLimitDescription").innerHTML = config.description;
                }
            });
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/billing");
    },
    closeUpdate: function() {
        dijit.byId("retailCreditLimitEditConformationDialog").hide();
    },
    updateShow: function() {

        var retailCreditLimit = dijit.byId("retailCreditLimit");

        if (retailCreditLimit.value == 0) {
            this._configRestStore.query().then(function(resultData) {
                dojo.forEach(resultData, function(config) {
                    if (config.name == "creditcard.processing") {
                        if (config.value == "false" || config.value == false) {
                            dijit.byId("retailCreditLimitEditConformationDialog").show();
                        } else {
                            RetailManagement.update();
                        }
                    }
                });
            });
        } else {
            RetailManagement.update();
        }
    },
    update: function() {

        var retailCreditLimit = dijit.byId("retailCreditLimit");

        var pageNode = dojo.byId("retailManagementFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId('retailManagemntButtton').set('style', {display: 'none'});
            dojo.byId("creditLoader").style.display = "block";
            this._creditConfigStore.add({
                retailCreditLimit: retailCreditLimit.value
            }).then(function(resultData) {
                dijit.byId('retailManagemntButtton').set('style', {display: 'block'});
                dojo.byId("creditLoader").style.display = "none";
                if (resultData == "OK") {
                    dojo.byId("configMsgId").style.display = "block";
                    registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                    registry.byId('appToaster').show();
                    dijit.byId("retailCreditLimitEditConformationDialog").hide();
                } else {
                    registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                    registry.byId('appToaster').show();
                    dijit.byId("retailCreditLimitEditConformationDialog").hide();
                }
            });
        }
    }
};
var CreditLimitNotification = {
    _generalConfigStore: "",
    _configRestStore: "",
    init: function() {
        this._creditConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/creditLimitNotification/"
        });
        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig"
        });
    },
    populateValues: function() {

        var creditLimitNotificationLevel1 = dijit.byId("creditLimitNotificationLevel1");
        var creditLimitNotificationLevel2 = dijit.byId("creditLimitNotificationLevel2");
        var creditLimitNotificationLevel3 = dijit.byId("creditLimitNotificationLevel3");

        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "credit.limit.notification.level1") {
                    creditLimitNotificationLevel1.setValue(config.value);
                } else if (config.name == "credit.limit.notification.level2") {
                    creditLimitNotificationLevel2.setValue(config.value);
                } else if (config.name == "credit.limit.notification.level3") {
                    creditLimitNotificationLevel3.setValue(config.value);
                }
            });
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/billing");
    },
    update: function() {

        var creditLimitLevel1 = dijit.byId("creditLimitNotificationLevel1");
        var creditLimitLevel2 = dijit.byId("creditLimitNotificationLevel2");
        var creditLimitLevel3 = dijit.byId("creditLimitNotificationLevel3");

        var pageNode = dojo.byId("creditLimitNotificationPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            dijit.byId('creditLimitNotificationButtton').set('style', {display: 'none'});
            dojo.byId("creditLoader").style.display = "block";
            this._creditConfigStore.add({
                creditLimitLevel1: creditLimitLevel1.value,
                creditLimitLevel2: creditLimitLevel2.value,
                creditLimitLevel3: creditLimitLevel3.value
            }).then(function(resultData) {
                dijit.byId('creditLimitNotificationButtton').set('style', {display: 'block'});
                dojo.byId("creditLoader").style.display = "none";
                if (resultData == "OK") {
                    dojo.byId("configMsgId").style.display = "block";
                    registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                    registry.byId('appToaster').show();
                } else {
                    registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                    registry.byId('appToaster').show();
                }
            });
        }
    }
};
var GeneralConfig = {
    _generalConfigStore: "",
    _configRestStore: "",
    init: function() {

        this._generalConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/general/"
        });


        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/"
        });

    },
    populateValues: function() {

        var trialEnabled = dijit.byId("trialEnabled");
        var trialEnabledDescription = dojo.byId("trialEnabledDescription");

        var trialCreditLimit = dijit.byId("trialCreditLimit");
        var trialCreditLimitDescription = dojo.byId("trialCreditLimitDescription");

        var retailCreditLimit = dijit.byId("retailCreditLimit");
        var retailCreditLimitDescription = dojo.byId("retailCreditLimitDescription");

        var signupCardVerificationValue = dijit.byId("signupCardVerificationValue");
        var signupCardVerificationValueDescription = dojo.byId("signupCardVerificationValueDescription");

        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "signup.type.trial.enabled") {
                    if (config.value == "true" || config.value == true) {
                        trialEnabled.setAttribute('checked', true);
                    } else {
                        trialEnabled.setAttribute('checked', false);
                    }
                    trialEnabledDescription.innerHTML = config.description;

                } else if (config.name == "signup.type.trial.credit.limit") {
                    trialCreditLimit.setValue(config.value);
                    trialCreditLimitDescription.innerHTML = config.description;

                } else if (config.name == "signup.type.retail.credit.limit") {
                    retailCreditLimit.setValue(config.value);
                    retailCreditLimitDescription.innerHTML = config.description;

                } else if (config.name == "signup.card.verification.value") {
                    signupCardVerificationValue.setValue(config.value);
                    signupCardVerificationValueDescription.innerHTML = config.description;
                }
            });
        });
    },
    enableTrialAccountType: function(currentElement) {
        if (currentElement.checked == true || currentElement.checked == "true") {
            dojo.byId("trialLimit").style.display = "block";
            dojo.byId("trialCreditLimitDescriptionDiv").style.display = "block";
        } else if (currentElement.checked == false || currentElement.checked == false) {
            dojo.byId("trialLimit").style.display = "none";
            dojo.byId("trialCreditLimitDescriptionDiv").style.display = "none";
        }
    }


};


var UsageConfig = {
    init: function() {

    },
    getUsage: function() {

        var usage = new JsonRest({
            target: core.getContextPath() + "/api/account/currentUsage/"
        });


        usage.query().then(function() {

        });


    }
};

var PaymentConfig = {
    _paymentConfigStore: "",
    _configRestStore: "",
    init: function() {

        this._paymentConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/paymentConfig/"
        });


        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });

    },
    populateValues: function() {

        var processingFeeAmount = dijit.byId("processingFeeAmount");
        var processingFeePercentage = dijit.byId("processingFeePercentage");

        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "payment.processing.fee.type") {
                    if (config.value == "INCLUDE") {
                        dijit.byId("includeProcessingFee").attr("checked", true);
                    } else if (config.value == "EXCLUDE") {
                        dijit.byId("includeProcessingFee").attr("checked", true);
                    }
//                    dojo.byId("includeProcessingFeeDescription").innerHTML = config.description;
                } else if (config.name == "payment.processing.fee.amount") {
                    processingFeeAmount.setValue(config.value);
//                     dojo.byId("processingFeePercentageDescription").innerHTML = config.description;
                } else if (config.name == "payment.processing.fee.percentage") {
                    processingFeePercentage.setValue(config.value);
//                     dojo.byId("processingFeeAmountDescription").innerHTML = config.description;
                }
            });
        });
    },
    add: function() {

        var feeAmount = dijit.byId("processingFeeAmount");
        var feePercentage = dijit.byId("processingFeePercentage");
        var processingFeeType;
        if (dijit.byId("includeProcessingFee").checked == true || dijit.byId("includeProcessingFee").checked == "true") {
            processingFeeType = dijit.byId("includeProcessingFee").value;
        } else {
            processingFeeType = dijit.byId("excludeProcessingFee").value;
        }

        var node = dojo.byId("paymentSettingPage");
        var nodeWidget = dijit.registry.findWidgets(node);

        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        dijit.byId('paymentButton').set('style', {display: 'none'});
        dojo.byId("paymentLoader").style.display = "block";
        this._paymentConfigStore.add({
            processingFeeType: processingFeeType,
            processingFeeAmount: feeAmount.value,
            processingFeePercentage: feePercentage.value
        }).then(function(resultData) {
            dijit.byId('paymentButton').set('style', {display: 'block'});
            dojo.byId("paymentLoader").style.display = "none";
            if (resultData == "OK") {
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/paymentGatway");
    }
};



var PaypalConfig = {
    _paypalStore: "",
    _configRestStore: "",
    init: function() {

        this._paypalStore = new JsonRest({
            target: core.getContextPath() + "/api/config/paypal/"
        });

        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
    },
    populateValues: function() {

        var connectionTimeOut = dijit.byId("connectionTimeOut");
        var httpRetry = dijit.byId("httpRetry");
        var readTimeOut = dijit.byId("readTimeOut");
        var maxConnection = dijit.byId("maxConnection");
        var proxyPort = dijit.byId("proxyPort");
        var proxyHost = dijit.byId("proxyHost");
        var useProxy = dijit.byId("useProxy");
        var proxyUserName = dijit.byId("proxyUserName");
        var proxyPassword = dijit.byId("proxyPassword");
        var googleAppEngine = dijit.byId("googleAppEngine");
        var serviceEndPoint = dijit.byId("serviceEndPoint");
        var clientID = dijit.byId("clientID");
        var clientSecret = dijit.byId("clientSecret");

        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "payment.gateway.paypal.http.ConnectionTimeOut") {
                    connectionTimeOut.setValue(config.value);
//                     dojo.byId("connectionTimeOutDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.Retry") {
                    httpRetry.setValue(config.value);
//                    dojo.byId("httpRetryDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.ReadTimeOut") {
                    readTimeOut.setValue(config.value);
//                    dojo.byId("readTimeOutDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.MaxConnection") {
                    maxConnection.setValue(config.value);
//                    dojo.byId("maxConnectionDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.ProxyPort") {
                    proxyPort.setValue(config.value);
//                    dojo.byId("proxyPortDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.GoogleAppEngine") {
                    if (config.value == "true" || config.value == true) {
                        googleAppEngine.set("checked", true);
                    } else {
                        googleAppEngine.set("checked", false);
                    }
//                    dojo.byId("googleAppEngineDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.UseProxy") {
                    if (config.value == "true" || config.value == true) {
                        useProxy.set("checked", true);
                    } else {
                        useProxy.set("checked", false);
                    }
//                    dojo.byId("useProxyDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.ProxyHost") {
                    proxyHost.setValue(config.value);
//                    dojo.byId("proxyHostDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.ProxyUserName") {
                    proxyUserName.setValue(config.value);
//                    dojo.byId("proxyUserNameDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.http.ProxyPassword") {
                    proxyPassword.setValue(config.value);
//                    dojo.byId("proxyPasswordDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.service.EndPoint") {
                    serviceEndPoint.setValue(config.value);
//                    dojo.byId("serviceEndPointDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.clientID") {
                    clientID.setValue(config.value);
//                    dojo.byId("clientIdDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.paypal.clientSecret") {
                    clientSecret.setValue(config.value);
//                    dojo.byId("clientSecretDescription").innerHTML = config.description;
                }
            });
        });
    },
    add: function() {

        var connectionTimeOut = dijit.byId("connectionTimeOut");
        var httpRetry = dijit.byId("httpRetry");
        var readTimeOut = dijit.byId("readTimeOut");
        var maxConnection = dijit.byId("maxConnection");
        var proxyPort = dijit.byId("proxyPort");
        var proxyHost = dijit.byId("proxyHost");
        var useProxy = dijit.byId("useProxy");
        var proxyUserName = dijit.byId("proxyUserName");
        var proxyPassword = dijit.byId("proxyPassword");
        var googleAppEngine = dijit.byId("googleAppEngine");
        var serviceEndPoint = dijit.byId("serviceEndPoint");
        var clientID = dijit.byId("clientID");
        var clientSecret = dijit.byId("clientSecret");


        var node = dojo.byId("paypalCodePage");
        var nodeWidget = dijit.registry.findWidgets(node);
        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        dijit.byId('paypallButton').set('style', {display: 'none'});
        dojo.byId("paypallLoader").style.display = "block";

        this._paypalStore.add({
            connectionTimeOut: connectionTimeOut.value,
            httpRetry: httpRetry.value,
            readTimeOut: readTimeOut.value,
            maxConnection: maxConnection.value,
            proxyPort: proxyPort.value,
            proxyHost: proxyHost.value,
            useProxy: useProxy.checked,
            proxyUserName: proxyUserName.value,
            proxyPassword: proxyPassword.value,
            googleAppEngine: googleAppEngine.checked,
            serviceEndPoint: serviceEndPoint.value,
            clientID: clientID.value,
            clientSecret: clientSecret.value

        }).then(function(resultData) {
            dijit.byId('paypallButton').set('style', {display: 'block'});
            dojo.byId("paypallLoader").style.display = "none";
            if (resultData == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                registry.byId("appToaster").show();
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();
            }

        });

    },
    cancel: function() {
        core.router.go("#/admin/settings/paymentGatway");
    }

};


var AuthorizeNetConfig = {
    _authorizeNetStore: "",
    _configRestStore: "",
    init: function() {

        this._authorizeNetStore = new JsonRest({
            target: core.getContextPath() + "/api/config/authorizeNet/"
        });

        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/",
            idAttribute: "id",
            "class": "com.assistanz.fogpanel.Config"
        });
    },
    populateValues: function() {

        var apiKey = dijit.byId("authorizeNetApiKey");
        var apiSecret = dijit.byId("authorizeNetApiSecret");
        var environment = dijit.byId("authorizeNetEnvironment");

        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "payment.gateway.authorizenet.apiKey") {
                    apiKey.setValue(config.value);
//                     dojo.byId("authorizeNetApiKeyDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.authorizenet.apiSecret") {
                    apiSecret.setValue(config.value);
//                    dojo.byId("authorizeNetApiSecretDescription").innerHTML = config.description;
                } else if (config.name == "payment.gateway.authorizenet.environment") {
                    environment.attr("value", config.value);
//                    dojo.byId("authorizeNetEnvironmentDescription").innerHTML = config.description;
                }
            });
        });
    },
    add: function() {

        var apiKey = dijit.byId("authorizeNetApiKey");
        var apiSecret = dijit.byId("authorizeNetApiSecret");
        var environment = dijit.byId("authorizeNetEnvironment");

        var authorizedConfigNode = dojo.byId("authorizedPage");
        var authorizedConfigWidget = dijit.registry.findWidgets(authorizedConfigNode);

        dojo.forEach(authorizedConfigWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        dijit.byId("authorizedNetButton").set('style', {display: 'none'});
        dojo.byId("authorizedNetLoader").style.display = "block";
        this._authorizeNetStore.add({
            apiKey: apiKey.value,
            apiSecret: apiSecret.value,
            environment: environment.value
        }).then(function(resultData) {
            dijit.byId("authorizedNetButton").set('style', {display: 'block'});
            dojo.byId("authorizedNetLoader").style.display = "none";
            if (resultData == "OK") {

                registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                registry.byId("appToaster").show();
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();
                dijit.byId("authorizeNetForm").reset();
            }

        });

    },
    cancel: function() {
        core.router.go("#/admin/settings/paymentGatway");
    }

};


var OrganizationBilling = {
    _organizationConfigStore: "",
    _configRestStore: "",
    _countryStore: "",
    __stateStore: "",
    init: function() {
        this._organizationConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/oganizationConfig/"
        });

        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig"

        });
        this._countryStore = new JsonRest({target: core.getContextPath() + "/api/country/"});
        this._stateStore = new JsonRest({target: core.getContextPath() + "/api/state/"});
    },
    selectState: function(currentCountry) {
        var stateId = dojo.byId("stateId").value;
        var countryId = dojo.byId("countryId").value;
        var stateStore = new JsonRest({target: core.getContextPath() + "/api/public/state"});
        var stateWidget = dijit.byId("organisationStateWidget");
        var newStateOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var newStateList = new ItemFileWriteStore({data: newStateOptions});
        if (this.value == "option") {
            stateWidget.store.newItem({id: "option", name: translator.common.account.selectState});
        } else {
            stateStore.query({code: currentCountry.value}).then(function(stateListItems) {
                var firstState = "";
                dojo.forEach(stateListItems, function(currentState, index) {
                    newStateList.newItem({id: currentState.id, name: currentState.stateName});
                    if (index == 0) {
                        firstState = currentState.id;
                    }
                });
                stateWidget.set("store", newStateList);
                if (countryId == currentCountry.value) {
                    stateWidget.set("value", stateId);
                } else {
                    stateWidget.set("value", firstState);
                }
            });
        }
    },
    populateValues: function() {
        var countryOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var stateOptions = {
            identifier: 'name',
            label: 'name',
            items: []
        };

        var countryList = new ItemFileWriteStore({data: countryOptions});
        var stateList = new ItemFileWriteStore({data: stateOptions});


        var stateWidget = new FilteringSelect({
            id: 'organisationStateWidget',
            maxHeight: 100, // tells _HasDropDown to fit menu within viewport     
            store: stateList,
            missingMessage: translator.common.account.selectState,
            promptMessage: translator.common.account.selectState,
            invalidMessage: translator.common.account.selectState,
            placeHolder: translator.common.account.selectState,            
            onChange: function() {

            }
        }).placeAt("billingConfigStateList");
        stateWidget.startup();

        var countryWidget = new FilteringSelect({
            id: "organisationCountryWidget",
            style: "width: 210px;",
            maxHeight: 100, // tells _HasDropDown to fit menu within viewport     
            store: countryList,
            placeHolder: translator.common.account.selectCountry,
            missingMessage: translator.common.account.selectCountry,
            promptMessage: translator.common.account.selectCountry,
            invalidMessage: translator.common.account.selectCountry,
            onChange: function() {
                OrganizationBilling.selectState(this);

            }
        }).placeAt("billingConfigCountryList");
        countryWidget.startup();

        var name = dijit.byId("organizationName");
        var email = dijit.byId("organizationEmail");

        var phone1 = dijit.byId("organizationPhoneNumber1");
        var phone2 = dijit.byId("organizationPhoneNumber2");
        var phone3 = dijit.byId("organizationPhoneNumber3");

        var fax1 = dijit.byId("organizationFaxNumber1");
        var fax2 = dijit.byId("organizationFaxNumber2");
        var fax3 = dijit.byId("organizationFaxNumber3");

        var logoUrl = dijit.byId("organizationLogoURL");
        var signature = dijit.byId("organizationSignature");

        var termsCondition = dijit.byId("organizationTermsCondition");

        var address = dijit.byId("organizationAddress");
        var extensionAddress = dijit.byId("organizationExtensionAddress");
        var city = dijit.byId("organizationCity");
        var country = dijit.byId("organisationCountryWidget");
        var state = dijit.byId("organisationStateWidget");
        var zip = dijit.byId("organizationZip");


        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                var newCountryOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var newCountryList = new ItemFileWriteStore({data: newCountryOptions});
                var countryStore = new JsonRest({target: core.getContextPath() + "/api/public/country"});
                if (config.name == "organisation.address.counrty") {
                    countryStore.query().then(function(data) {
                        dojo.forEach(data, function(el) {
                            newCountryList.newItem({id: el.id, name: el.countryName, callingCode: el.callingCode});
                        });
                        country.set("store", newCountryList);
                        country.set("value", config.value);
                    });
                    dijit.byId("organisationCountryWidget").set("value", config.value);
                    dojo.byId("countryId").value = config.value;
                } else if (config.name == "organisation.name") {
                    name.setValue(config.value);
                } else if (config.name == "organisation.email") {
                    email.setValue(config.value);
                } else if (config.name == "organisation.billing.terms.conditions") {
                    termsCondition.setValue(config.value);
                } else if (config.name == "organisation.billing.phone.number1") {
                    phone1.setValue(config.value);
                } else if (config.name == "organisation.billing.phone.number2") {
                    phone2.setValue(config.value);
                } else if (config.name == "organisation.billing.phone.number3") {
                    phone3.setValue(config.value);
                } else if (config.name == "organisation.billing.fax.number1") {
                    fax1.setValue(config.value);
                } else if (config.name == "organisation.billing.fax.number2") {
                    fax2.setValue(config.value);
                } else if (config.name == "organisation.billing.fax.number3") {
                    fax3.setValue(config.value);
                } else if (config.name == "organisation.billing.logo") {
                    logoUrl.setValue(config.value);
                } else if (config.name == "organisation.billing.signature") {
                    signature.setValue(config.value);
                } else if (config.name == "organisation.address") {
                    address.setValue(config.value);
                } else if (config.name == "organisation.address.extension") {
                    extensionAddress.setValue(config.value);
                } else if (config.name == "organisation.address.city") {
                    city.setValue(config.value);
                } else if (config.name == "organisation.address.zip") {
                    zip.setValue(config.value);
                } else if (config.name == "organisation.address.state") {
                    dojo.byId("stateId").value = config.value;
                } else if (config.name == "organisation.background.img.url") {
                    dijit.byId("organizationBgnImgURL").setValue(config.value);
                }
            });
        });
    },
    add: function() {
        
        var configurationMissing = "false";
        var orgSettingConfiguredFirstTime = "false";
        
        var usageInfo = new JsonRest({
            target: core.getContextPath() + "/api/account/usage"
        });
        
        usageInfo.query().then(function(data){
            dojo.forEach(data,function (usageData) {
                if(usageData.openstackConfig == "false" || usageData.isFlavorEmpty === "false" || usageData.isImageEmpty === "false" || usageData.volumeTypeConfig === "false" || usageData.isZoneEmpty == "false") {
                   configurationMissing = "true";
                } 
                if(usageData.orgSetting == "false"){
                    orgSettingConfiguredFirstTime = "true";
                }
            });
        });    
        
        
        var pageNode = dojo.byId("organizationBillSettingsPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        }

        var name = dijit.byId("organizationName").value;
        var email = dijit.byId("organizationEmail").value;

        var phone1 = dijit.byId("organizationPhoneNumber1").value;
        var phone2 = dijit.byId("organizationPhoneNumber2").value;
        var phone3 = dijit.byId("organizationPhoneNumber3").value;

        var fax1 = dijit.byId("organizationFaxNumber1").value;
        var fax2 = dijit.byId("organizationFaxNumber2").value;
        var fax3 = dijit.byId("organizationFaxNumber3").value;

        var logoUrl = dijit.byId("organizationLogoURL").value;
        var signature = dijit.byId("organizationSignature").value;
        var termsCondition = dijit.byId("organizationTermsCondition").value;

        var address = dijit.byId("organizationAddress").value;
        var extensionAddress = dijit.byId("organizationExtensionAddress").value;
        var city = dijit.byId("organizationCity").value;
        var countryWidget = dijit.byId("organisationCountryWidget");
        var state = dijit.byId("organisationStateWidget");
        var bgnImgUrl = dijit.byId("organizationBgnImgURL").value;
        var zip = dijit.byId("organizationZip").value;
        var bgnImgUrl = dijit.byId("organizationBgnImgURL").value;
        dijit.byId("organizationBillingButton").setAttribute('style', 'display: none');
        dojo.byId("organizationBillingLoader").style.display = "inline";
        this._organizationConfigStore.add({
            name: name,
            email: email,
            address: address,
            extensionAddress: extensionAddress,
            city: city,
            country: countryWidget.value,
            phone1: phone1,
            phone2: phone2,
            phone3: phone3,
            fax1: fax1,
            fax2: fax2,
            fax3: fax3,
            logoUrl: logoUrl,
            signature: signature,
            state: state.value,
            zip: zip,
            termsCondition: termsCondition,
            bgnImgUrl: bgnImgUrl
        }).then(function(result) {
            dijit.byId("organizationBillingButton").setAttribute('style', 'display: inline');
            dojo.byId("organizationBillingLoader").style.display = "none";
            if (result == "OK") {
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
                
                //Auto property reload for first time 
                if(orgSettingConfiguredFirstTime == "true") {
                    var configSyncRest = new JsonRest({
                        target: core.getContextPath() + "/api/config/property/reload"
                    });
                    configSyncRest.query().then(function(data) {
            
                        if (data == "OK") {

                        } else {

                        }
                    });
                } else {
                    dojo.byId("configMsgId").style.display = "block";
                } 
                
                if(configurationMissing == "true") {
                   core.router.go("#/admin/dashboard");
                   registry.byId('appToaster').setContent(translator.common.message.configurationMissing, 'error');
                   registry.byId('appToaster').show();
                } 
 
                     
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }

        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/general");
    }
};

var BillingTypeConfig = {
    _billingTypeStore: "",
    _configRestStore: "",
    init: function() {
        this._billingTypeStore = new JsonRest({
            target: core.getContextPath() + "/api/config/billingType/"
        });
        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig"
        });
    },
    populateValues: function() {
        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "monthly.billing.enabled") {
                    if (config.value == "true" || config.value == true) {
                        dijit.byId("monthlyEnabled").setAttribute('checked', true);
                    } else {
                        dijit.byId("monthlyEnabled").setAttribute('checked', false);
                    }
                } else if(config.name === "usage.billing.calc.type") {
                    if(config.value === "ACTUAL") {
                        dijit.byId("usageTypeActual").set("checked", true);
                    } else {
                        dijit.byId("usageTypeHourly").set("checked", true);
                    } 
                }
            });
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/billing");
    },
    update: function() {

        var monthlyEnabled = dijit.byId("monthlyEnabled");
        
        var formElements = dojo.query("#usageCalcTypeValue input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var usageCalcTypeValue = dijit.byId(checkedRadioId).value;

        dijit.byId('billingTypeConfigButtton').set('style', {display: 'none'});
        dojo.byId("creditLoader").style.display = "block";
        this._billingTypeStore.add({
            monthlyEnabled: monthlyEnabled.checked,
            usageCalcType:usageCalcTypeValue
        }).then(function(resultData) {
            dijit.byId('billingTypeConfigButtton').set('style', {display: 'block'});
            dojo.byId("creditLoader").style.display = "none";
            if (resultData[0] === "OK") {
                dojo.byId("configMsgId").style.display = "block";
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    }
};

var PaymentCardConfig = {
    _generalConfigStore: "",
    _configRestStore: "",
    init: function() {
        this._creditCardProcessingConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/config/paymentCardConfig/"
        });
        this._configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/property/getConfig"
        });
    },
    populateValues: function() {
        this._configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name == "signup.card.verification.enabled") {
                    if (config.value == "true" || config.value == true) {
                        dijit.byId("signupCardVerificationEnabled").setAttribute('checked', true);
                        dojo.byId("createVMCardDiv").style.display = "none";
                        dojo.byId("createVmCardDis").style.display = "none";
                    } else {
                        dijit.byId("signupCardVerificationEnabled").setAttribute('checked', false);
                        dojo.byId("createVMCardDiv").style.display = "block";
                        dojo.byId("createVmCardDis").style.display = "block";
                    }
//                        dojo.byId("signupCardVerificationEnabledDescription").innerHTML = config.description;
                } else if (config.name == "creditcard.processing") {
                    if (config.value == "true" || config.value == true) {
                        dijit.byId("creditCardEnabled").setAttribute('checked', true);
                        dojo.byId("cardProDiv").style.display = "block";
                        dojo.byId("signupCardDis").style.display = "block";
                    } else {
                        dijit.byId("creditCardEnabled").setAttribute('checked', false);
                        dojo.byId("signupCardDis").style.display = "none";
                        dojo.byId("cardProDiv").style.display = "none";

                        dojo.byId("createVMCardDiv").style.display = "none";
                        dojo.byId("createVmCardDis").style.display = "none";
                    }
//                        dojo.byId("signupCardVerificationEnabledDescription").innerHTML = config.description;
                } else if (config.name == "creditcard.processing.in.createvm") {
                    if (config.value == "true" || config.value == true) {
                        dijit.byId("vmCardVerificationEnabled").setAttribute('checked', true);
                    } else {
                        dijit.byId("vmCardVerificationEnabled").setAttribute('checked', false);
                    }
//                        dojo.byId("signupCardVerificationEnabledDescription").innerHTML = config.description;
                }
            });
        });
    },
    cancel: function() {
        core.router.go("#/admin/settings/billing");
    },
    update: function() {

        var signupCardVerificationEnabled = dijit.byId("signupCardVerificationEnabled");
        var creditCardEnabled = dijit.byId("creditCardEnabled");
        var vmCardVerificationEnabled = dijit.byId("vmCardVerificationEnabled");

        dijit.byId('paymentCardConfigButtton').set('style', {display: 'none'});
        dojo.byId("creditLoader").style.display = "block";
        this._creditCardProcessingConfigStore.add({
            signupCardVerificationEnabled: signupCardVerificationEnabled.checked,
            vmCardVerificationEnabled: vmCardVerificationEnabled.checked,
            creditCardEnabled: creditCardEnabled.checked
        }).then(function(resultData) {
            dijit.byId('paymentCardConfigButtton').set('style', {display: 'block'});
            dojo.byId("creditLoader").style.display = "none";
            if (resultData == "OK") {
                dojo.byId("configMsgId").style.display = "block";
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    },
    enableCreditCard: function() {
        var creditCardEnabled = dijit.byId("creditCardEnabled");

        if (creditCardEnabled.checked == true) {
            dojo.byId("cardProDiv").style.display = "block";

            dojo.byId("signupCardDis").style.display = "block";

            dijit.byId("signupCardVerificationEnabled").setAttribute('checked', true);
        } else {
            dojo.byId("signupCardDis").style.display = "none";
            dojo.byId("cardProDiv").style.display = "none";

            dojo.byId("createVMCardDiv").style.display = "none";
            dojo.byId("createVmCardDis").style.display = "none";

            dijit.byId("signupCardVerificationEnabled").setAttribute('checked', false);
            dijit.byId("vmCardVerificationEnabled").setAttribute('checked', false);


        }
    },
    enableCreditCardSignup: function() {
        var signupCardVerificationEnabled = dijit.byId("signupCardVerificationEnabled");
        var creditCardEnabled = dijit.byId("creditCardEnabled");
        if (signupCardVerificationEnabled.checked == true && creditCardEnabled.checked == true) {
            dojo.byId("createVMCardDiv").style.display = "none";
            dojo.byId("createVmCardDis").style.display = "none";
            dijit.byId("vmCardVerificationEnabled").setAttribute('checked', false);
        } else if (signupCardVerificationEnabled.checked == false && creditCardEnabled.checked == false) {
            dojo.byId("createVMCardDiv").style.display = "none";
            dojo.byId("createVmCardDis").style.display = "none";
            dijit.byId("vmCardVerificationEnabled").setAttribute('checked', false);
        } else {
            dojo.byId("createVMCardDiv").style.display = "block";
            dojo.byId("createVmCardDis").style.display = "block";
            dijit.byId("vmCardVerificationEnabled").setAttribute('checked', true);
        }
    }
};


var CCAvenueConfig = {
    init: function() {

    },
    populateValues: function() {
        var configRestStore = new JsonRest({
            target: core.getContextPath() + "/api/config/ccAvenueConfig",
        });
        var gatewayType = "";
        var merchantId = dijit.byId("merchantId");
        var workingKey = dijit.byId("workingKey");
        configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                merchantId.setValue(config.merchantId);
                workingKey.setValue(config.workingKey);
                gatewayType = config.gatewayType;
                var gatewayTypeOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var gatewayTypeOptionsList = new ItemFileWriteStore({data: gatewayTypeOptions});
                gatewayTypeOptionsList.newItem({id: gatewayType, name: gatewayType});
                var gatewayTypeWidget = new Select({
                    id: "gatewayTypeWidget",
                    name: "gatewayOptionOption",
                    sortByLabel: false,
                    store: gatewayTypeOptionsList,
                    value: "option"
                }).placeAt("gatewayList");
                if (config.gatewayStatus === "ENABLE") {
                    dijit.byId("enableStatus").set('checked', true);
                } else {
                    dijit.byId("disableStatus").set('checked', true);
                }
            });
        });
    },
    add: function() {
        var merchantId = dijit.byId("merchantId");
        var workingKey = dijit.byId("workingKey");

        var formElements = dojo.query("#paymentStatus input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var statusSelected = dijit.byId(checkedRadioId).value;


        var node = dojo.byId("ccAvenueCodePage");
        var nodeWidget = dijit.registry.findWidgets(node);
        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });

        dijit.byId('ccAvenueButton').set('style', {display: 'none'});
        dojo.byId("ccAvenueLoader").style.display = "block";


        var ccAvenueStore = new JsonRest({
            target: core.getContextPath() + "/api/config/ccAvenue/"
        });

        ccAvenueStore.add({
            merchantId: merchantId.value,
            workingKey: workingKey.value,
            gatewayStatus: statusSelected
        }).then(function(resultData) {
            dijit.byId('ccAvenueButton').set('style', {display: 'block'});
            dojo.byId("ccAvenueLoader").style.display = "none";
            if (resultData == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                registry.byId("appToaster").show();
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();
            }
        });
    }
};

var PaymentGatewayList = {
    init: function() {
        var paymentGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/paymentGateways/listGateways"
        });

        dojo.byId("paymentGatewayGrid").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.common.loader.loadingPleaseWait + "</p>";

        if (dijit.byId("paymentGatewayGridWidget")) {
            dijit.byId("paymentGatewayGridWidget").destroyRecursive();
        }
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
                {'name': translator.common.gatewayName, 'field': 'gatewayName', 'width': '200px', datatype: "string", formatter: AddLink, autoComplete: true},
                {'name': translator.common.gatewayType, 'field': 'gatewayType', 'width': '200px', datatype: "string", autoComplete: true},
                {'name': translator.common.gatewayEnableDisable, 'field': 'gatewayStatus', 'width': '100px', formatter: AddGridCheckBox, autoComplete: true},
                {'name': translator.common.gatewayIsDefault, 'field': 'gatewayDefault', 'width': '100px', formatter: AddGridRadioButton, autoComplete: true},
                {'name': translator.common.gatewayIncludeExclude, 'field': 'includeExclude', 'width': '100px', formatter: AddGridIncludeExclude, autoComplete: true},
                {'name': translator.common.procFeePercent, 'field': 'procFeePercent', 'width': '100px', datatype: "number", autoComplete: true},
                {'name': translator.common.procFeeAmount, 'field': 'procFeeAmount', 'width': '100%', datatype: "number", autoComplete: true}
            ]];
        function AddLink(gatewayName) {
            return "<a href='#" + gatewayName.gwURL + "'>" + gatewayName.gwName + "</a>";
        }
        function AddGridIncludeExclude(includeExclude) {
            var include = false;
            if (includeExclude === "INCLUDE") {
                include = true;
            }

            var includeCBox = new dijit.form.CheckBox({
                name: "includeCheckBox",
                value: "agreed",
                checked: include,
                onChange: function() {
                    var rowIndex = dijit.byId("paymentGatewayGridWidget").selection.selectedIndex;
                    var store = dijit.byId("paymentGatewayGridWidget").store;
                    var item = dijit.byId("paymentGatewayGridWidget").getItem(rowIndex);
                    if (this.checked == false) {
                        store.setValue(item, 'includeExclude', "EXCLUDE");
                    } else if (this.checked == true) {
                        store.setValue(item, 'includeExclude', "INCLUDE");
                    }
                }
            });
            includeCBox.startup();
            return includeCBox;
        }
        function AddGridCheckBox(gatewayStatus) {
            var checkedOrNot = false;
            var disabledOption = false;

            if (gatewayStatus.status === "ENABLE") {
                checkedOrNot = true;
            } else {
                checkedOrNot = false;
            }
            if (gatewayStatus.paymentDone > 0) {
                if (gatewayStatus.gwType == "ADVANCED") {
                    if (gatewayStatus.defaultGWType == "ADVANCED") {
                        if (gatewayStatus.status === "ENABLE") {
                            disabledOption = false;
                        } else if (gatewayStatus.status === "DISABLE") {
                            disabledOption = true;
                        }
                    } else if (gatewayStatus.defaultGWType == "SEAMLESS") {
                        disabledOption = true;
                    }
                } else if (gatewayStatus.gwType == "SEAMLESS") {
                    if (gatewayStatus.status === "ENABLE") {
                        if (gatewayStatus.gwDefault.toString() == "true") {

                        }
                    }
                }
            } else if (gatewayStatus.paymentDone == 0) {
                if (gatewayStatus.gwType == "ADVANCED" && gatewayStatus.status == "ENABLE") {
                    loadedADVGw = true;
                }
                if (loadedADVGw == true) {
                    if (gatewayStatus.gwType == "ADVANCED" && gatewayStatus.status == "DISABLE") {
                        disabledOption = true;
                    }
                }
            }
            var flag;
            var checkbox = new dijit.form.CheckBox({
                name: "checkBox",
                value: "agreed",
                checked: checkedOrNot,
                disabled: disabledOption,
                onChange: function() {
                    var rowIndex = dijit.byId("paymentGatewayGridWidget").selection.selectedIndex;
                    var store = dijit.byId("paymentGatewayGridWidget").store;
                    var item = dijit.byId("paymentGatewayGridWidget").getItem(rowIndex);
                    var currentCheckBox = this;
                    var rowIndex = dijit.byId("paymentGatewayGridWidget").selection.selectedIndex;
                    dijit.byId("paymentGatewayGridWidget").store.fetch({
                        onComplete: function(itemList, request) {
                            var currentItem = dijit.byId("paymentGatewayGridWidget").getItem(rowIndex);

                            if (currentItem.gatewayType == "ADVANCED" && currentCheckBox.checked == true) {
                                isAdvGWSelected = isAdvGWSelected + 1
                            } else if (currentItem.gatewayType == "ADVANCED" && currentCheckBox.checked == false) {
                                isAdvGWSelected = isAdvGWSelected - 1
                            }
                            if (currentItem.gatewayType == "ADVANCED" && isAdvGWSelected > 1) {
                                currentCheckBox.checked = false;
                                isAdvGWSelected = isAdvGWSelected - 1
                                registry.byId("appToaster").setContent(translator.common.message.gwADVDuplicate, "error");
                                registry.byId("appToaster").show();
                            }
                            if (currentCheckBox.checked == false) {
                                gatewayStatus = {
                                    status: "DISABLE",
                                    paymentDone: item.paymentDone
                                },
                                store.setValue(item, 'gatewayStatus', gatewayStatus);
                            } else if (currentCheckBox.checked == true) {
                                gatewayStatus = {
                                    status: "ENABLE",
                                    paymentDone: item.paymentDone
                                },
                                store.setValue(item, 'gatewayStatus', gatewayStatus);
                            }
                        }
                    });
                }
            });
            checkbox.startup();
            return checkbox;
        }
        function AddGridRadioButton(gatewayDefault) {
            var radioChecked = false;
            var disableDefault = false;

            if (gatewayDefault.gwDefault == 1) {
                radioChecked = true;
            } else {
                radioChecked = false;
            }
            if (gatewayDefault.defaultGWName == gatewayDefault.gwName) {
                disableDefault = false
            } else if (gatewayDefault.defaultGWName != gatewayDefault.gwName) {
                disableDefault = true
            }
            if (gatewayDefault.paymentDone == 0) {
                if (gatewayDefault.defaultGWType == "none") {
                    disableDefault = false
                }
            }
            var isDefaultRadio = new dijit.form.RadioButton({
                name: "isDefaultGateway",
                checked: radioChecked,
                disabled: disableDefault,
                onChange: function() {
                    var rowIndex = dijit.byId("paymentGatewayGridWidget").selection.selectedIndex;
                    var store = dijit.byId("paymentGatewayGridWidget").store;
                    var item = dijit.byId("paymentGatewayGridWidget").getItem(rowIndex);
                    if (this.checked == false) {
                        gatewayDefault = {
                            gwDefault: false,
                            paymentDone: item.paymentDone
                        },
                        store.setValue(item, 'gatewayDefault', gatewayDefault);
                    } else if (this.checked == true) {
                        gatewayDefault = {
                            gwDefault: true,
                            paymentDone: item.paymentDone
                        },
                        store.setValue(item, 'gatewayDefault', gatewayDefault);
                    }
                }
            });
            isDefaultRadio.startup();
            return isDefaultRadio;
        }
        paymentGatewayRestStore.query().then(function(data) {
            dojo.forEach(data, function(gatewayList) {
                var paymentDone = gatewayList.isPaymentsAvailable
                var defaultGWType = gatewayList.defaultGWType
                var defaultGWName = gatewayList.defaultGWName
                dojo.forEach(gatewayList.paymentsGateways, function(gatewayList) {
                    gridDataList.newItem({
                        gatewayName: {
                            gwName: gatewayList.gatewayName,
                            gwURL: gatewayList.gatewayUrl
                        },
                        gatewayType: gatewayList.gatewayType,
                        gatewayStatus: {
                            status: gatewayList.status,
                            paymentDone: paymentDone,
                            gwType: gatewayList.gatewayType,
                            gwDefault: gatewayList.isDefault,
                            gwName: gatewayList.gatewayName,
                            defaultGWType: defaultGWType
                        },
                        gatewayDefault: {
                            status: gatewayList.status,
                            paymentDone: paymentDone,
                            gwType: gatewayList.gatewayType,
                            gwDefault: gatewayList.isDefault,
                            gwName: gatewayList.gatewayName,
                            defaultGWType: defaultGWType,
                            defaultGWName: defaultGWName
                        },
                        includeExclude: gatewayList.includeExclude,
                        procFeePercent: gatewayList.processingFeePercent,
                        procFeeAmount: gatewayList.processingFeeAmount,
                        gatewayUrl: gatewayList.gatewayUrl
                    });
                });
                dojo.byId("paymentGatewayGrid").innerHTML = "";
                var paymentGatewayGrid = new EnhancedGrid({
                    id: 'paymentGatewayGridWidget',
                    "class" : "span12",
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: {
                        pagination: {
                            pageSizes: ["All"],
                            description: true,
                            position: "bottom"
                        }
                    }
                });
                paymentGatewayGrid.placeAt("paymentGatewayGrid");
                paymentGatewayGrid.startup();
            });
        });

    },
    updatePaymentGateways: function() {
        var updatedGatewayRestStore = new JsonRest({
            target: core.getContextPath() + "/api/paymentGateways/gateways"
        });
        var gatewayListArray = [];
        dijit.byId("paymentGatewayGridWidget").store.fetch({
            onComplete: function(itemList, request) {
                dojo.forEach(itemList, function(el, index) {
                    gatewayListArray.push({
                        gatewayName: el.gatewayName[0].gwName,
                        gatewayStatus: el.gatewayStatus[0].status,
                        gatewayDefault: el.gatewayDefault[0].gwDefault,
                        includeExclude: el.includeExclude[0]
                    });
                });
            }
        });

        updatedGatewayRestStore.add({
            gridData: gatewayListArray
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData.result == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.gatewayAdded, "message");
                    registry.byId("appToaster").show();
                } else {
                    registry.byId("appToaster").setContent(resultData.message, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    }
};

var RegionConfig = {
    'init': function() {
        
    },
    'populateValues': function(){
        
        var regionListRestStore = new JsonRest({
            target: core.getContextPath() + "/api/region"
        });
    
        dojo.byId("regionListGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
         var gridLayout = [[
//                {'name': translator.common.id, 'field': 'id', 'width': '10%'},
                {'name': "Region Name", 'field': 'name', 'width': '100%'},
//                {'name': translator.common.action, 'field': 'action', 'width': '20%', 'formatter': function(data) {
//                       var btn = "";
//                       var btn = "<a class='btn-flat' onclick='RegionConfig.showRegion(" + data.id + ")'>" + translator.common.edit + "</a>"+"<a class='btn-flat danger spacing' onclick='RegionConfig.showDeleteRegion(" + data.id + ")'>" + translator.common.deleteData + "</a>";
//                       return btn; 
//                }}
            ]
        ];
        
        regionListRestStore.query().then(function(data) {

            if (data.length == 0) {
                dojo.byId("regionListGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>" + translator.common.message.noRegionAdded + "</div>";
            } else {


                dojo.forEach(data, function(el) {

                    gridDataList.newItem({
//                        id: el.id,
                        name: el.name,
//                        action: {'id': el.id, 'name': el.name}
                    });

                });

                dojo.byId("regionListGrid").innerHTML = "";


                var regionGrid = new EnhancedGrid({
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });

                regionGrid.placeAt("regionListGrid");
                regionGrid.startup();

            }
        });
    },
    'confirmPullAlert': function() {
        dijit.byId("pullRegionConfirm").show();
    },
    'pull': function() {
        dijit.byId("pullRegionConfirm").hide();
       
        var pullRegionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/region/pullFromOpenstack"
        });

        pullRegionRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (resultData === "OK") {
                    dojo.byId("pullRegionLoaderImage").style.display = "inline";
                    dijit.byId("pullRegionButton").set("disabled", true);
                   
                } else {

                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    'cancelPull': function() {
        dijit.byId("pullRegionConfirm").hide();
    },
    'addRegion': function() {
        var addRegionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/region/"
        });
        
        var regionName = dijit.byId("regionName");
        
        var pageNode = dojo.byId("regionsPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            addRegionRestStore.add({
                    regionName: regionName.getValue()
                }).then(function(data) {
                    dojo.forEach(data, function(el) {
                    if (el.result === "OK") {
                        dijit.byId("addRegionForm").reset();
                        registry.byId('appToaster').setContent(translator.common.message.addedSuccess, 'message');
                        registry.byId('appToaster').show();
                        RegionConfig.populateValues();
                    } else if(el.result === "Exists") {
                        registry.byId('appToaster').setContent(translator.common.message.regionExists, 'error');
                        registry.byId('appToaster').show();
                    }  else {
                        registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                        registry.byId('appToaster').show();
                    }
                });  
            });   
            
        }
    },
    
    'showRegion': function(id) {
        
        dojo.byId("regionId").value = id;
        var getRegionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/region/get/"
        });
        getRegionRestStore.get(id).then(function(resultData) {
            dojo.forEach(resultData, function(data) {
            dijit.byId("regionName").setValue(data.regionName);
            });
        });
        dojo.byId("addRegionButton").style.display = "none";
        dojo.byId("updateCancelRegionButton").style.display = "block";
        
    },
    
    'updateRegion': function() {

        var id = dojo.byId("regionId").value;
        var name = dijit.byId("regionName").value;
        var updateRegionStore = new JsonRest({
            target: core.getContextPath() + "/api/region/update/"
        });
        var pageNode = dojo.byId("regionsPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            updateRegionStore.put({
                id: id,
                regionName: name
            }).then(function(data) {
               dojo.forEach(data, function(el) {
                    if (el.result === "OK") {
                        dijit.byId("addRegionForm").reset();
                        registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("appToaster").show();
                        RegionConfig.populateValues();
                    } else {
                        registry.byId("appToaster").setContent(el.message, "error");
                        registry.byId("appToaster").show();
                    }
                });
            });
        }
    },
    'cancelRegion': function() {
        dijit.byId("addRegionForm").reset();
        dojo.byId("addRegionButton").style.display = "block";
        dojo.byId("updateCancelRegionButton").style.display = "none";
    },
    'showDeleteRegion': function(currentId) {
        dijit.byId("showDeleteRegionDialog").show();
        dojo.byId("regionId").value = currentId;
    },
    'deleteRegion': function() {
        var currentId = dojo.byId("regionId").value;
        var deleteRegionRestStore = new JsonRest({
            target: core.getContextPath() + "/api/region/"
        });

        deleteRegionRestStore.remove(currentId).then(function(data) {

            if (data === "[\"OK\"]") {

                dijit.byId("showDeleteRegionDialog").hide();
                registry.byId("appToaster").setContent(translator.common.message.deletedSuccess, "message");
                registry.byId("appToaster").show();
                RegionConfig.populateValues();


            } else {

                dijit.byId("showDeleteRegionDialog").hide();
                registry.byId("appToaster").setContent(translator.common.message.cannotDelete, "error");
                registry.byId("appToaster").show();
            }

        });

    },
    
    'closeDeleteRegionDialog': function() {
        dijit.byId("showDeleteRegionDialog").hide();
    }
};
var zenossConfig = {
    'init' : function() {
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/property/getConfig/"
        });
        
        configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name === "zenoss.endpoint.url") {
                    dijit.byId("zenossEndpointUrl").setValue(config.value);
                } else if (config.name === "zenoss.username") {
                    dijit.byId("zenossUsername").setValue(config.value);
                } else if (config.name === "zenoss.password") {
                    dijit.byId("zenossPassword").setValue(config.value);
                }
            });
        });
    },
    'update': function() {
        
        var configurationMissing = "false";
        var openstackFirstTimeConfigured = "false";
        
        var endpointUrl = dijit.byId("zenossEndpointUrl");
        var zenossUsername = dijit.byId("zenossUsername");
        var zenossPassword = dijit.byId("zenossPassword");
        
        var zenossConfigRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/zenossConfig/update"
        });

        var pageNode = dojo.byId("zenossConfigWidgets");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        } else {
            zenossConfigRestStore.add({
                endpointUrl: endpointUrl.getValue(),
                zenossUsername: zenossUsername.getValue(),
                zenossPassword: zenossPassword.getValue()
            }).then(function(data){
                if (data[0] === "OK") {
                    registry.byId('appToaster').setContent(translator.common.message.addedSuccess, 'message');
                    registry.byId('appToaster').show();
//                        dijit.byId("configPropertieReloadDialog").show();

                    //Auto property reload for first time 
                    if(openstackFirstTimeConfigured === "true") {
//                        var configSyncRest = new JsonRest({
//                            target: core.getContextPath() + "/api/config/property/reload"
//                        });
//                        configSyncRest.query().then(function(data) {
//
//                            if (data == "OK") {
//
//                            } else {
//
//                            }
//                        });
                    } else {
                        dojo.byId("configMsgId").style.display = "block";
                    } 
                    //if config missing it redirect to home page
                    if(configurationMissing === "true") {
                       core.router.go("#/admin/dashboard");
                       registry.byId('appToaster').setContent(translator.common.message.configurationMissing, 'error');
                       registry.byId('appToaster').show();
                    } 

                } else {
                    registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                    registry.byId('appToaster').show();
                }
            });
        }
    },
    'cancel': function() {
        zenossConfig.init();
    }
}
var openstackConfig = {
    'init' : function() {
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/property/getConfig/"
        });
        
        configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                if (config.name === "openstack.endpoint.url") {
                    dijit.byId("endpointUrl").setValue(config.value);
                } else if (config.name === "openstack.admin.uuid") {
                    dijit.byId("adminUuid").setValue(config.value);
                } else if (config.name === "openstack.admin.password") {
                    dijit.byId("adminPassword").setValue(config.value);
                }
            });
        });
    },
    'update': function() {
        
        var configurationMissing = "false";
        var openstackFirstTimeConfigured = "false";
        
        var endpointUrl = dijit.byId("endpointUrl");
        var adminUuid = dijit.byId("adminUuid");
        var adminPassword = dijit.byId("adminPassword");
        
        var isOpenStackAuthenticated = new JsonRest({
           target: core.getContextPath() + "/api/config/isOpenStackAuthenticated" 
        });
        
        var roleCreationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/roleCreation"
        });
                
        isOpenStackAuthenticated.query({
            endpointUrl : endpointUrl.getValue(),
            adminUuid : adminUuid.getValue(),
            adminPassword : adminPassword.getValue(),
        }).then(function(data){
            if(data == true) {
                
                var usageInfo = new JsonRest({
                    target: core.getContextPath() + "/api/account/usage"
                });

                  usageInfo.query().then(function(data){
                      dojo.forEach(data,function (usageData) {
                          if(usageData.orgSetting == "false" || usageData.isFlavorEmpty === "false" || usageData.isImageEmpty === "false" || usageData.volumeTypeConfig === "false" 
                                  || usageData.isZoneEmpty == "false"
                                  || usageData.isDomainEmpty === "false") {
                             configurationMissing = "true";
                          } 
                          if(usageData.openstackConfig == "false"){
                              openstackFirstTimeConfigured = "true";
                          }
                      });
                  });

                  var cloudstackConfigRestStore = new JsonRest({
                      target: core.getContextPath()+"/api/config/openstackConfig/update"
                  });

                  var pageNode = dojo.byId("openstackConfigWidgets");
                  var widgets = dijit.registry.findWidgets(pageNode);
                  var firstNode = null;
                  var state = true;
                  dojo.forEach(widgets, function(el) {
                      if (el.validate && !el.validate()) {
                          el.focus();
                          state = false;
                          if (!firstNode) {
                              firstNode = el;
                          }
                      }
                  });
                  if (firstNode) {
                      firstNode.focus();
                  } else {
                      cloudstackConfigRestStore.add({
                          endpointUrl: endpointUrl.getValue(),
                          adminUuid: adminUuid.getValue(),
                          adminPassword: adminPassword.getValue()
                      }).then(function(data){
                          if (data[0] === "OK") {
                              registry.byId('appToaster').setContent(translator.common.message.addedSuccess, 'message');
                              registry.byId('appToaster').show();
        //                        dijit.byId("configPropertieReloadDialog").show();

                              //Auto property reload for first time 
                              if(openstackFirstTimeConfigured == "true") {
                                  var configSyncRest = new JsonRest({
                                      target: core.getContextPath() + "/api/config/property/reload"
                                  });
                                  configSyncRest.query().then(function(data) {

                                      if (data == "OK") {

                                      } else {

                                      }
                                  });
                              } else {
                                  dojo.byId("configMsgId").style.display = "block";
                              } 
                              //if config missing it redirect to home page
                              if(configurationMissing == "true") {
                                 core.router.go("#/admin/dashboard");
                                 registry.byId('appToaster').setContent(translator.common.message.configurationMissing, 'error');
                                 registry.byId('appToaster').show();
                              } 
                              
                          } else {
                              registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                              registry.byId('appToaster').show();
                          }
                      });
                    }
                     
            
            //If user role not exist , new role will created for first time 
            roleCreationRestStore.query().then(function(data) {

                if(data == "true") {
                }
            });
    
            } else {
               
               registry.byId('appToaster').setContent(translator.common.message.openStackNotAuthenticated, 'error');
               registry.byId('appToaster').show();
               
           }
        });
        
        
    },
    'cancel': function() {
        openstackConfig.init();
    }
}

var ConfigSync = {
    'propertieReload': function() {
        var configSyncRest = new JsonRest({
            target: core.getContextPath() + "/api/config/property/reload"
        });

        configSyncRest.query().then(function(data) {
            
            if (data == "OK") {
//                dijit.byId("configPropertieReloadDialog").hide();
                dojo.byId("configMsgId").style.display = "none";
                registry.byId('appToaster').setContent(translator.common.message.configSyncSuccess, 'message');
                registry.byId('appToaster').show();
                core.router.go("#/admin/settings/general")
            } else {
//                dijit.byId("configPropertieReloadDialog").hide();
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
                core.router.go("#/admin/settings/general")
            }
        });
    },
    'propertieReloadAlertHomePage': function() {
        var configSyncRest = new JsonRest({
            target: core.getContextPath() + "/api/config/property/reload"
        });

        configSyncRest.query().then(function(data) {
            
            if (data == "OK") {
                core.router.go("#/admin/dashboard");
                registry.byId('appToaster').setContent(translator.common.message.configSyncSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                dijit.byId("configPropertieReloadDialog").hide();
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
    },
    'cancelReload': function() {
        dijit.byId("configPropertieReloadDialog").hide();
        core.router.go("#/admin/settings");
        registry.byId('appToaster').setContent(translator.common.message.configNotSynced, 'error');
        registry.byId('appToaster').show();
        }
}

var VolumeTypesConfig = {
    
    'populateValues': function() {
        
//        var asyncJob = new JsonRest({
//            target: core.getContextPath() + "/api/asyncJob"
//        });
//        
//        asyncJob.query({jobType:"PULL_VOLUME_TYPE"}).then(function(data) {
//            if(data[0] === "OK") {
//                dojo.byId("pullVolumeTypeLoaderImage").style.display = "inline";
//                dijit.byId("pullVolumeTypeButton").set("disabled", true);
//            } else if(data[0] === "FALSE") {
//                dojo.byId("pullVolumeTypeLoaderImage").style.display = "none";
//                dijit.byId("pullVolumeTypeButton").set("disabled", false);
//            }
//        }); 
        
        var listRestStore = new JsonRest({
            target: core.getContextPath() + "/api/volumetypes"
        });
    
        dojo.byId("volumeTypesListGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
         var gridLayout = [[
                {'name': translator.common.idCaps, 'field': 'referenceId', 'width': '10%'},
                {'name': translator.common.name, 'field': 'name', 'width': '20%'}
            ]
        ];
        
        listRestStore.query().then(function(data) {

            if (data.length === 0) {
                dojo.byId("volumeTypesListGrid").innerHTML = "<div class='alert alert-info hide' style='display: block;'>\n\
                   <i class='icon-exclamation-sign'></i>" + translator.common.message.noVolumeTypes + "</div>";
            } else {


                dojo.forEach(data, function(el) {

                    gridDataList.newItem({
                        referenceId: el.referenceId,
                        name: el.name
                    });

                });

                dojo.byId("volumeTypesListGrid").innerHTML = "";


                var regionGrid = new EnhancedGrid({
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });

                regionGrid.placeAt("volumeTypesListGrid");
                regionGrid.startup();

            }
        });
    },
    confirmPull: function () {
        dijit.byId("pullVolumeTypeConfirm").show();
    },
    cancelPull: function () {
        dijit.byId("pullVolumeTypeConfirm").hide();
    },
    pull : function() {

//        dijit.byId("pullVolumeTypeLoader").show();
        dijit.byId("pullVolumeTypeConfirm").hide();

        var pullFromRestStore = new JsonRest({
           target: core.getContextPath()+"/api/volumetypes/pullFromOpenstack"
        });

        pullFromRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    dojo.byId("pullVolumeTypeLoaderImage").style.display = "inline";
                    dijit.byId("pullVolumeTypeButton").set("disabled", true);
//                    VolumeTypesConfig.populateValues();
//                    registry.byId("appToaster").setContent(translator.common.message.pullVolumeTypeSuccess, "message");
//                    registry.byId("appToaster").show();
//                    dijit.byId("pullVolumeTypeLoader").hide();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
//                    dijit.byId("pullVolumeTypeLoader").hide();
                }
            });
        });

    }
};

window.GeneralConfig = GeneralConfig;
window.PaymentConfig = PaymentConfig;
window.PaypalConfig = PaypalConfig;
window.AuthorizeNetConfig = AuthorizeNetConfig;
window.BillingConfig = BillingConfig;
window.OrganizationBilling = OrganizationBilling;
window.InvoiceInfo = InvoiceInfo;
window.CreditCardInfo = CreditCardInfo;
window.LateFeeInfo = LateFeeInfo;
window.MailConfig = MailConfig;
window.LoginSecurityInfo = LoginSecurityInfo;
window.UsageConfig = UsageConfig;
window.CurrencyValueConfig = CurrencyValueConfig;
