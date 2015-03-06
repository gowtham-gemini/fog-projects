"use strict";
var app = {
    _loader: null,
    _count: 1,
    showLoader: function(message) {
        if (message) {
            this._loader.innerHTML = message;
        } else {
            this._loader.innerHTML = "<p>Please wait till the page loads</p>";
        }
        this._loader.style.display = "block";
    },
    hideLoader: function() {
        this._loader.style.display = "none";
    },
    'getContextPath': function() {
        return signupBaseConfig.context;
    },
    'setConfig': function(config) {
        for (var param in config) {
            signupBaseConfig[param] = config[param];

        }
    }

};

var signupBaseConfig = {
    'baseURL': "/FogPanel/",
    'homepage': "#/",
    'context': "FogPanel"
};


app.signup = {
    _store: null,
    require: function() {
        var baseURL = signupBaseConfig.baseURL;
        require([
            "dojo",
            "dojo/i18n",
            "dojo/i18n!"+baseURL+"/js/dojo-1.8/FogPanel/nls/translator.js",
            "dojo/dom",
            "dojo/ready",
            "dojo/store/JsonRest",
            "dojo/data/ItemFileWriteStore",
            "dijit/registry",
            "dijit/form/Select",
            "dijit/form/FilteringSelect",
            "dojo/on",
            "dojo/dom-form",
            "dojo/parser",
            "dojox/widget/Toaster",
            "dijit/Dialog",
            "dijit/form/Button",
            "dijit/dijit",
            "dijit/layout/ContentPane",
            "dijit/layout/BorderContainer",
            "dojox/widget/Wizard",
            "dojox/widget/WizardPane",
            "dijit/form/TextBox",
            "dijit/form/Form",
            "FogPanel/Wizard",
            "dojox/widget/rotator/Slide",
            "dojox/widget/Rotator",
            "dojox/widget/rotator/Pan",
            "dojox/widget/rotator/Fade",
            "dijit/form/ValidationTextBox",
            "dojox/form/PasswordValidator",
            "dijit/form/DateTextBox",
            "dijit/form/ComboBox",
            "dojo/query",
            "dojox/validate",
            "dojo/NodeList-traverse",
            "dojox/validate/regexp",
            "dijit/form/CheckBox",
            "dijit/Dialog"
        ], function(dojo, i18n, translator, dom, ready, JsonRestStore, ItemFileWriteStore, registry, Select, FilteringSelect, on) {
            window.translator = translator;            
            window.JsonRestStore = JsonRestStore;
            window.Select = Select;
            window.on = on;
            window.FilteringSelect = FilteringSelect;
            window.ItemFileWriteStore = ItemFileWriteStore;
            app._loader = dom.byId("loader");
            window.count = 1;
            window.registry = registry;
            app.signup.init();
            app.hideLoader();
        });
    },
    submit: function() {
        var firstName = dijit.byId("firstName");
        var lastName = dijit.byId("lastName");
        var email = dijit.byId("newEmail");
        var accountIdentifier = dijit.byId("userName")
        var password = dijit.byId("newPassword");
        var city = dijit.byId("city");
        var state = dijit.byId("state");
        var country = dijit.byId("country");
        var zip = dijit.byId("zip");
        var accountType = dojo.byId("accountType").value;
        var promotionalCode;
        var cardType;
        var cardNumber;
        var expiryYear;
        var expiryMonth;
        var cvvNumber;
        if (accountType == "TRIAL") {
            promotionalCode = dijit.byId("promotionalCode").getValue();
        } else if (accountType == "RETAIL") {
            promotionalCode = dijit.byId("promotionalCode").getValue();

            if (dojo.byId("cardRequired").value == "TRUE") {
                cardType = dijit.byId("newCardType").getValue();
                cardNumber = dijit.byId("newCardNumber").getValue();
                expiryYear = dijit.byId("expiryYear").getValue();
                expiryMonth = dijit.byId("newExpiryMonth").getValue();
                cvvNumber = dijit.byId("newCvvNumber").getValue();
            }
        }
        var address = dijit.byId("streetAddress");
        var address1 = dijit.byId("streetAddress2");

//        var userName =  dijit.byId("userName");
        var companyName = dijit.byId("companyName");
        var phoneNumber = dijit.byId("phoneNumber");



        var accountStore = new JsonRestStore({target: app.getContextPath() + "/api/public/accountSignup"});
        accountStore.add({
            firstName: firstName.getValue(),
            lastName: lastName.getValue(),
            accountIdentifier :accountIdentifier.getValue(),
            email: email.getValue(),
            password: password.getValue(),
            preferredLanguage: "en",
            city: city.getValue(),
            state: state.getValue(),
            country: country.getValue(),
            zip: zip.getValue(),
            promotionalCode: promotionalCode,
            address: address.getValue(),
            address1: address1.getValue(),
            companyName: companyName.getValue(),
            phoneNumber: phoneNumber.getValue(),
            accountType: accountType,
            cardNumber: cardNumber,
            expiryMonth: expiryMonth,
            expiryYear: expiryYear,
            cvv: cvvNumber,
            cardType: cardType
        }).then(function(data) {
            if (data == "OK") {
                dojo.byId("signupLoader").style.display = "none";
                dojo.byId("signupLoadingMessanger").innerHTML = "";
                window.location = app.getContextPath() + "/account/successPage"
                dijit.byId("signup_wizard").doneButton.set("disabled", true);
                dojo.byId("cancelButton").disabled = true;
                dojo.byId("signupFailedMsg").style.display = "none";
            } else {
                dojo.byId("signupFailedMsg").style.display = "block";
                dojo.byId("signupLoader").style.display = "none";
                dojo.byId("signupLoadingMessanger").innerHTML = "";
                dijit.byId("signup_wizard").doneButton.set("disabled", false);
                dojo.byId("cancelButton").disabled = false;
                dijit.byId("promotionalCode").set("disabled", false);
                dijit.byId("captcha").set("disabled", false);
                dijit.byId("agreement").set("disabled", false);
                var wizard = dijit.byId("signupWizard");
                dojo.forEach(data, function(result) {
                    if (result.Exception == "ValidationException") {
                        var errorList = "<ul>";
                        dojo.forEach(result.ExceptionResult.errors, function(errorInfo) {
                            errorList += "<li>" + errorInfo.field + "   " + errorInfo.defaultMessage + "</li>";
                        });
                        errorList += "</ul>";
                        dojo.byId("signupFailedMsg").innerHTML = "<i class='icon-remove-sign'> </i>" + translator.common.exceptionMsg.validationException + "</br>" + errorList;
                    } else if (result.Exception == "other") {
                        if (result.ExceptionResult.localizedMessage) {
                            if (result.ExceptionResult.localizedMessage == "CARD_VERIFICATION_FAILED") {
                                dojo.byId("signupFailedMsg").innerHTML = "<i class='icon-remove-sign'> </i>"+ translator.common.exceptionMsg.cardVerificationFailed +"</br>" + translator.common.exceptionMsg.unabletoVerifyCard;
                            } else {
                                dojo.byId("signupFailedMsg").innerHTML = "<i class='icon-remove-sign'> </i>" + translator.common.exceptionMsg.unabletoCreateAccount + "</br>" + result.ExceptionResult.localizedMessage;
                            }
                        } else if (result.ExceptionResult.cause.localizedMessage) {
                            if (result.ExceptionResult.cause.localizedMessage == "CARD_VERIFICATION_FAILED") {
                                dojo.byId("signupFailedMsg").innerHTML = "<i class='icon-remove-sign'> </i>"+ translator.common.exceptionMsg.cardVerificationFailed +"</br>" + translator.common.exceptionMsg.unabletoVerifyCard;
                            } else {
                                dojo.byId("signupFailedMsg").innerHTML = "<i class='icon-remove-sign'> </i>" + translator.common.exceptionMsg.unabletoCreateAccount +  "</br>" + result.ExceptionResult.cause.localizedMessage;
                            }
                        } else {
                            dojo.byId("signupFailedMsg").innerHTML = "<i class='icon-remove-sign'> </i>" +  translator.common.exceptionMsg.unabletoCreateAccount; 
                        }

                    }
                });

                dojo.query("#wizardSteps li").forEach(function(node, index) {
                    wizard.enable(index + 1);
                    wizard.onActive(app.signup.onItemClick);
                });


                dijit.byId("newCardNumber").set("disabled", false);
                dijit.byId("newCvvNumber").set("disabled", false);
                dijit.byId("newCardType").set("disabled", false);
                dijit.byId("newExpiryMonth").set("disabled", false);
                dijit.byId("expiryYear").set("disabled", false);
            }
        });
    },
    onItemClick: function(currentItem) {
        var wizard = dijit.byId("signupWizard");
        if (currentItem == "signupWizard_step_1") {
            dijit.byId("signup_wizard").selectChild("wizard_page_1", false);
            wizard.disable(2);
            wizard.disable(3);
            count = 1
        } else if (currentItem == "signupWizard_step_2") {
            dijit.byId("signup_wizard").selectChild("wizard_page_2", false);
            wizard.disable(3);
            count = 2;
        } else if (currentItem == "signupWizard_step_3") {
            dijit.byId("signup_wizard").selectChild("wizard_page_3", false);
            count = 3
        }
    },
    authetication: function() {
        var status = true;
        var pageNode = dojo.byId(this.id);
        dojo.byId("signupFailedMsg").style.display = "none";
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        var wizard = dijit.byId("signupWizard");
        if (status == true) {
            count = count + 1;
            wizard.enableStateWithoutAction(count);
            wizard.deactivateTabClick();
        }
        return status;
    },
    init: function() {
        var countryStore = new JsonRestStore({target: app.getContextPath() + "/api/public/country"});
        var stateStore = new JsonRestStore({target: app.getContextPath() + "/api/public/state"});

        var accountType = dojo.byId("accountType").value;
        if (accountType == "RETAIL") {
            if (dojo.byId("cardRequired").value == "TRUE") {
                var yearOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                var yearList = new ItemFileWriteStore({data: yearOptions});

                var currentTime = new Date()
                var j = currentTime.getFullYear() + 20;
                for (var i = currentTime.getFullYear(); i <= j; i++) {
                    yearList.newItem({id: i, name: i});
                }
                var yearListWidget = new Select({
                    id: "expiryYear",
                    name: "expiryYear",
                    store: yearList
                });
                yearListWidget.placeAt("newYearList");
                yearListWidget.startup();
            }
        }

        var stateOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var countryOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var countryList = new ItemFileWriteStore({data: countryOptions});
        var stateList = new ItemFileWriteStore({data: stateOptions});

        countryStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                countryList.newItem({id: el.id, name: el.countryName, callingCode: el.callingCode})
            });
        });
        var stateWidget = new FilteringSelect({
            id: "state",
            style: "width: 210px;",
            missingMessage: "State Is Required",
            invalidMessage: "State is required",
            promptMessage: "Select State",
            labelAttr: "name",
            require: "true",
            maxHeight: 100, // tells _HasDropDown to fit menu within viewport     
            store: stateList,
            placeHolder: "Select State"
        }).placeAt("stateList");
        stateWidget.startup();
        var countryWidget = new FilteringSelect({
            id: "country",
            style: "width: 210px;",
            missingMessage: "Country Is Required",
            invalidMessage: "Country Is Required",
            promptMessage: "Select Country",
            labelAttr: "name",
            require: "true",
            maxHeight: 100, // tells _HasDropDown to fit menu within viewport     
            store: countryList,
            placeHolder: "Select Country",
            onChange: function() {
                if (this.value == "option") {
                    dojo.byId("callingCode").innerHTML = "code"
                } else {
                    countryList.fetch({query: {id: this.value},
                        onItem: function(item) {
                            dojo.byId("callingCode").innerHTML = countryList.getValue(item, 'callingCode');
                        }
                    });
                }
                dojo.byId("countryErrorMsg").style.display = "none";
                this.get("displayedValue");
                var stateWidget = dijit.byId("state");
                stateOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };

                stateList = new ItemFileWriteStore({data: stateOptions});
                if (this.value == "option") {
                    stateWidget.set("value", "option")
                } else {
                    stateStore.query({code: this.value}).then(function(stateListItems) {
                        var firstValue = "";
                        dojo.forEach(stateListItems, function(currentState, index) {
                            stateList.newItem({id: currentState.id, name: currentState.stateName})
                            if (index == 0) {
                                firstValue = currentState.id;
                            }
                        });
                        stateWidget.set("store", stateList);
//                        stateWidget.set("value", firstValue);    
                    });
                }
            }
        }).placeAt("countryList");
        countryWidget.startup();
        var promoCode = dijit.byId("promotionalCode")
        dojo.connect(promoCode, "onChange", function(value) {
            // Now do something with the value...            
            app.signup.validatePromoCode(promoCode);
        });
    },
    done_func: function() {

        var cap_store = new JsonRestStore({target: app.getContextPath() + "/api/public/captcha"});
        var promoCode = dijit.byId("promotionalCode");
        var status = app.signup.validatePromoCode(promoCode)
        var promotionlStore = new JsonRestStore({target: app.getContextPath() + "/api/public/promotionValidate"});
        var accountType = dojo.byId("accountType").value;
        var captcha = dijit.byId("captcha").getValue();
        var captachWidget = dijit.byId("captcha");
        cap_store.add({captchaValue: captcha}).then(function(result) {
            if (result == "true") {
                var status = true;
                var firstNode;
                captachWidget.validator = function() {
                    return true;
                }
                var pageNode = dojo.byId("signupForm");
                var widgets = dijit.registry.findWidgets(pageNode);

                dojo.forEach(widgets, function(el) {
                    if (el.validate && !el.validate()) {
                        el.focus();
                        status = false;
                        if (!firstNode) {
                            firstNode = el;
                        }
                    }
                    if (status == true) {
                        if (el.type == "checkbox" && el.checked == true) {
                            promotionlStore.query({
                                code: promoCode.getValue()
                            }).then(function(data) {
                                dojo.forEach(data, function(element) {
                                    if (element == "OK" && accountType == "TRIAL") {
                                        dojo.byId("signupLoader").style.display = "block";
                                        promoCode.set("disabled", true)
                                        captachWidget.set("disabled", true);
                                        dijit.byId("agreement").set("disabled", true)

                                        app.signup.submit();

                                        dojo.byId("conditionExceptionMsg").style.display = "none";
                                        dojo.byId("signupLoadingMessanger").innerHTML = "Signup is processing, Please wait...";
                                        dijit.byId("signup_wizard").doneButton.set("disabled", true);
                                        dojo.byId("cancelButton").disabled = true;
                                    } else if ((accountType == "RETAIL" && element != "OK") || (accountType == "RETAIL" && element == "OK")) {
                                        promoCode.set("disabled", true)
                                        captachWidget.set("disabled", true);


                                        dijit.byId("agreement").set("disabled", true)
                                        dojo.byId("signupLoader").style.display = "block";
                                        app.signup.submit();
                                        dojo.byId("signupLoadingMessanger").innerHTML = "Signup is processing, Please wait...";
                                        dijit.byId("signup_wizard").doneButton.set("disabled", true);
                                        dojo.byId("cancelButton").disabled = true;
                                        dojo.byId("conditionExceptionMsg").style.display = "none";

                                        dijit.byId("newCardNumber").set("disabled", true);
                                        dijit.byId("newCvvNumber").set("disabled", true);
                                        dijit.byId("newCardType").set("disabled", true);
                                        dijit.byId("newExpiryMonth").set("disabled", true);
                                        dijit.byId("expiryYear").set("disabled", true);

                                    }
                                })
                            });
                        } else if (el.type == "checkbox" && el.checked == false) {
                            dojo.byId("signupLoader").style.display = "none";
                            dojo.byId("signupLoadingMessanger").innerHTML = "";
                            dojo.byId("conditionExceptionMsg").style.display = "block";
                        }
                    }

                });
                return status;
            } else {
                captachWidget.set("invalidMessage", "Please enter the correct letters");
                captachWidget.focus();
                captachWidget.validator = function() {
                    return false;
                }
//                 alert("Please enter the correct letters");
                dojo.byId("signupLoader").style.display = "none";
                dojo.byId("signupLoadingMessanger").innerHTML = "";
            }
        });
    },
    cancelSignup: function() {
//         core.router.go("#/admin/dashboard");        
    },
    show_condition: function() {
        var configRestStore = new JsonRestStore({
            target: app.getContextPath() + "/api/public/termsAndCondition"
        });

        configRestStore.query().then(function(resultData) {
//            var domElement = "<div class='span8'>" + resultData.value +"</div>"
            dojo.byId("contentArea").innerHTML = resultData.value;
//            dijit.byId("dialog").set("content", resultData.value);                         
        });
        dijit.byId("dialog").show();

    },
    test: function() {
        registry.byId('successMessage').setContent('Success!', 'Successfully signed up');
        registry.byId('successMessage').show();
    },
    clearEmail: function(email) {

        var AccountStore = new JsonRestStore({target: app.getContextPath() + "/api/public/accountValidate"});

        AccountStore.query({
            email: email.getValue()
        }).then(function(data) {
            dojo.forEach(data, function(el) {
                email.validator = function() {
                    if (el == "FAILED") {
//                        email.focus();  
                        email.set("invalidMessage", "Email Already Exist");
                        return false;
                    } else if (el == "FALSE") {
//                        email.focus();  
                        email.set("invalidMessage", "Invalid Email");
                        return false;
                    } else {
                        email.set("invalidMessage", "Invalid Email");
                        return true;
                    }
                }
            });
        });
//        dijit.byId("confirmEmail").reset();
    },
    validateEmail: function(email) {
        var AccountStore = new JsonRestStore({target: app.getContextPath() + "/api/public/accountValidate"});

        AccountStore.query({
            email: email.getValue()
        }).then(function(data) {
            dojo.forEach(data, function(el) {
                email.validator = function() {
                    if (el == "FAILED") {
//                        email.focus();  
                        email.set("invalidMessage", "Email Already Exist");
                        return false;
                    } else if (el == "FALSE") {
//                        email.focus();  
                        email.set("invalidMessage", "Invalid Email");
                        return false;
                    } else {
                        email.set("invalidMessage", "Invalid Email");
                        return true;
                    }
                }
            })
        });
    },
    validateUserName: function(userName) {
        var AccountStore = new JsonRestStore({target: app.getContextPath() + "/api/public/accountValidate"});
        var userName = userName;
        AccountStore.query({
            username: userName.getValue()
        }).then(function(resultData) {
            dojo.forEach(resultData, function(el) {
                if (el.result) {
                    userName.validator = function() {
                        if (el.result == "TRUE") {
                            userName.set("invalidMessage", " ");
                            return true;
                        } else if (el.result == "FAILED") {
                            console.log(" failed msg"+el.msg);
                            userName.set("invalidMessage", el.msg);
                            return false;
                        } else if (el.result == "FALSE") {
                            userName.set("invalidMessage", "Invalid user name or user name length is Invalid");
                            return false;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return;
                }
            })
        });
    },
    validateCaptcha: function() {
        var captcha = dijit.byId("captcha")

        var cap_store = new JsonRestStore({target: app.getContextPath() + "/api/public/captcha"});
        cap_store.add({captchaValue: captcha.getValue()}).then(function(result) {
            dojo.forEach(result, function(el) {
                captcha.validator = function() {
                    if (el == true || el == "true") {
//                        captcha.set("promptMessage", " ");
                        return true;
                    } else {
                        captcha.set("invalidMessage", "Invalid Captcha");
                        return false;
                    }
                }
            });
        });
    },
    validatePromoCode: function(code) {
        var promoCode = dijit.byId("promotionalCode");
        var promotionlStore = new JsonRestStore({target: app.getContextPath() + "/api/public/promotionValidate"});
        var accType = dojo.byId("accountType").value;
        if (promoCode.getValue() == "" && accType == "RETAIL") {
            promoCode.validator = function() {
                return true;
            };
        } else {
            promotionlStore.query({
                code: promoCode.getValue()
            }).then(function(data) {
                dojo.forEach(data, function(el) {
                    promoCode.validator = function() {
                        if (el == "OK") {
                            dojo.byId("promoCodeValid").style.display = "block";
                            return true;
                        } else if (el == "FAILED") {
                            promoCode.set("invalidMessage", "Invalid Promotion Code");
                            dojo.byId("promoCodeValid").style.display = "none";
                            return false;
                        } else if (el == "EXPIRED") {
                            promoCode.set("invalidMessage", "Promotion Code Expired");
                            dojo.byId("promoCodeValid").style.display = "none";
                            return false;
                        } else {
                            promoCode.set("invalidMessage", "Invalid Promotion Code")
                            dojo.byId("promoCodeValid").style.display = "none";
                            return false;
                        }
                    };
                })
            });
        }
    }

};
window.app = app;

