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
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dijit/form/DateTextBox",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dijit/layout/BorderContainer",
    "dijit/form/DropDownButton",
    "dijit/layout/TabContainer",
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
], function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
        ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {
    window.query = query;
    window.translator = translator;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Select = Select;
    window.ContentPane = ContentPane;
    window.EnhancedGrid = EnhancedGrid;
    window.DataGrid = DataGrid;
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;

    controller({
        name: "discount",
        module: "admin",
        filePath: "/js/app/admin/discount.js",
        layout: {
            name: "discount",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {

            core.ui.loadTemplate("discount", core.ui.getContentId());
            DiscountManagement.init();
            DiscountManagement.populateValues();
        }),
        "add": action(function(id) {
            var addForm = dijit.byId("discountForm");
            if (addForm) {
                addForm.destroyRecursive();
            }
            core.ui.loadTemplate("addDiscount", core.ui.getContentId());
            AddDiscountManagement.init();
            AddDiscountManagement.populateValues();

        }),
        "delete": action(function(id) {
            var editForm = dijit.byId("billableItemForm");
            if (editForm) {
                editForm.destroyRecursive();
            }
            DiscountManagement.init();
            DiscountManagement.populateValues();
            DeleteDiscountManagement.init(id);
            DeleteDiscountManagement.populateValues();

        })

    });
});

var AddDiscountManagement = {
    _discountStore: "",
    _discountValidateStore: "",
    _discountValidateDateRangeStore: "",
    init: function() {
        this._discountStore = new JsonRest({
            target: core.getContextPath() + "/api/discount/"
        });

        this._discountValidateStore = new JsonRest({
            target: core.getContextPath() + "/api/discount/validate"
        });

        this._discountValidateDateRangeStore = new JsonRest({
            target: core.getContextPath() + "/api/discount/validateDateRange"
        });

        dojo.byId("discountPlanList").style.display = "none";
        dojo.byId("discountUserList").style.display = "none";
        dojo.byId("isAllUserDiv").style.display = "none";
        dojo.byId("isAllPlanDiv").style.display = "none";
        dojo.byId("discountApplyToValue").value = "ALL";

        var computeOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });

        var accountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });
        
        var sel = dojo.byId('discountUsers');
        accountRestStore.query().then(function(data) {
            dojo.forEach(data, function(account) {
                if(account.status.name === "ACTIVE") {
                    var opt = document.createElement('option');
                    opt.innerHTML = account.userName;
                    opt.value = account.id;
                    sel.appendChild(opt); 
                }
                
            });
            var planSel = dojo.byId('discountPlans');
            computeOfferRestStore.query().then(function(data) {
                dojo.forEach(data, function(computeOffer) {
                    var planOpt = document.createElement('option');
                    planOpt.innerHTML = "(" + computeOffer.zone.aliasName + ")" + computeOffer.name;
                    planOpt.value = computeOffer.id;
                    planSel.appendChild(planOpt);
                });
                dijit.byId('discountStartDate').set('value', new Date());
                dijit.byId('discountEndDate').set('value', new Date());
            });
        });


    },
    populateValues: function() {

    },
    enablePlanList: function(currentElement) {
        var applyType = dojo.byId("discountApplyToValue").value;
        if ((currentElement.checked == "true" || currentElement.checked == true) && applyType == "SELECTIVE") {
            dojo.byId("discountPlanList").style.display = "none";
        } else {
            dojo.byId("discountPlanList").style.display = "block";
        }
    },
    enableUserList: function(currentElement) {
        var applyType = dojo.byId("discountApplyToValue").value;
        if ((currentElement.checked == "true" || currentElement.checked == true) && applyType == "SELECTIVE") {
            dojo.byId("discountUserList").style.display = "none";
        } else {
            dojo.byId("discountUserList").style.display = "block";
        }
    },
    changeDiscountType: function(type) {
        
        if(type === "PARTICULAR") {
            
            var billableItemStore = new JsonRest({
                target: core.getContextPath() + "/api/billableItem/"
            });
            
            billableItemStore.get(1).then(function(billableItemData) {
                dojo.forEach(billableItemData, function(data) {
                    if (data.isDiscountable === false || data.isDiscountable === "false") {
                        registry.byId("appToaster").setContent(translator.common.message.discountDisableVMInfo, "error");
                        registry.byId("appToaster").show();
                    } else {
                        billableItemStore.get(13).then(function(billableItemData2) {
                            dojo.forEach(billableItemData2, function(data2) {
                                if (data2.isDiscountable === false || data2.isDiscountable === "false") {
                                    registry.byId("appToaster").setContent(translator.common.message.discountDisableInfo, "error");
                                    registry.byId("appToaster").show();
                                }
                            });
                        });
                    }
                });

            });
            dojo.byId("discountStartDateDiv").style.display = "block";
            dojo.byId("discountEndDateDiv").style.display = "block";
            
            
            
            dijit.byId('discountStartDate').set('value', new Date());
            dijit.byId('discountEndDate').set('value', new Date());
            
        } else {
            dijit.byId('discountStartDate').set('value', new Date());
            dijit.byId('discountEndDate').set('value', new Date());
            
            dojo.byId("discountStartDateDiv").style.display = "none";
            dojo.byId("discountEndDateDiv").style.display = "none";
        }
        
        dijit.byId("discountApplyTo").set("value", "ALL");
        var discountUsers = dojo.byId("discountUsers");
        var i;
        for (i = discountUsers.options.length - 1; i >= 0; i--) {
            discountUsers.remove(i);
        }

        var accountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });

        accountRestStore.query().then(function(data) {
            dojo.forEach(data, function(account) {
                if(account.status.name === "ACTIVE") {
                    var opt = document.createElement('option');
                    opt.innerHTML = account.userName;
                    opt.value = account.id;
                    discountUsers.appendChild(opt); 
                }
            });


            var discountPlans = dojo.byId("discountPlans");
            var j;

            for (j = discountPlans.options.length - 1; j >= 0; j--) {
                discountPlans.remove(j);
            }

            var computeOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/computingOffer/"
            });

            computeOfferRestStore.query().then(function(data) {
                dojo.forEach(data, function(computeOffer) {
                    var planOpt = document.createElement('option');
                    planOpt.innerHTML = "(" + computeOffer.zone.aliasName + ")" + computeOffer.name;
                    planOpt.value = computeOffer.id;
                    discountPlans.appendChild(planOpt);
                });
                AddDiscountManagement.validateDateRange();
            });
        });

        var applyType = dijit.byId('discountApplyTo').value;
        if (type == "USER") {
            dojo.byId("applyToLable").innerHTML = "Apply to user";
//            dojo.byId("subTypeDiv").style.display = "none";
            dojo.byId("discountPlanList").style.display = "none";
            dojo.byId("discountApplyToDiv").style.display = "block";
            dojo.byId("isAllPlanDiv").style.display = "none";
            dojo.byId("isAllUserDiv").style.display = "none";
            dijit.byId('discountStartDate').set('value', new Date());
            dijit.byId('discountEndDate').set('value', new Date());
            dojo.byId("discountUserList").style.display = "block";
        } else if (type == "PARTICULAR") {
            dojo.byId("discountApplyToDiv").style.display = "block";
            dojo.byId("applyToLable").innerHTML = "Apply to plan and user";
            dijit.byId("discountApplyTo").setAttribute("disabled", false);
            if (applyType == "ALL") {
                dojo.byId("discountPlanList").style.display = "none";
                dojo.byId("discountUserList").style.display = "none";
                dojo.byId("isAllUserDiv").style.display = "none";
                dojo.byId("isAllPlanDiv").style.display = "none";
            } else {
                dojo.byId("isAllUserDiv").style.display = "block";
                dojo.byId("isAllPlanDiv").style.display = "block";
                dojo.byId("discountPlanList").style.display = "block";
                dojo.byId("discountUserList").style.display = "block";
            }
        }
    },
    showList: function(type) {
        dojo.byId("discountApplyToDiv").style.display = "block";
        dojo.byId("discountApplyToValue").value = type;
        var discountType = dijit.byId('discountType').value;
        var discountUsers = dojo.byId("discountUsers");
        var i;
        for (i = discountUsers.options.length - 1; i >= 0; i--) {
            discountUsers.remove(i);
        }

        var discountPlans = dojo.byId("discountPlans");
        var j;

        for (j = discountPlans.options.length - 1; j >= 0; j--) {
            discountPlans.remove(j);
        }

        var computeOfferRestStore = new JsonRest({
            target: core.getContextPath() + "/api/computingOffer/"
        });

        computeOfferRestStore.query().then(function(data) {
            dojo.forEach(data, function(computeOffer) {
                var planOpt = document.createElement('option');
                planOpt.innerHTML = "(" + computeOffer.zone.aliasName + ")" + computeOffer.name;
                planOpt.value = computeOffer.id;
                discountPlans.appendChild(planOpt);
            });
        });

        var accountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/account/"
        });

        accountRestStore.query().then(function(data) {
            dojo.forEach(data, function(account) {
               if(account.status.name === "ACTIVE" && account.invoice === "yes" && discountType === "USER") {
                    var opt = document.createElement('option');
                    opt.innerHTML = account.userName;
                    opt.value = account.id;
                    discountUsers.appendChild(opt); 
                } else if(account.status.name === "ACTIVE"  && discountType === "PARTICULAR") {
                    var opt = document.createElement('option');
                    opt.innerHTML = account.userName;
                    opt.value = account.id;
                    discountUsers.appendChild(opt);  
                }
            });
        });

        
        if (type == "ALL") {
            dojo.byId("discountUserList").style.display = "none";
            dojo.byId("discountPlanList").style.display = "none";
            dojo.byId("isAllUserDiv").style.display = "none";
            dojo.byId("isAllPlanDiv").style.display = "none";
        } else if (type == "SELECTIVE" && discountType == "USER") {
            dojo.byId("discountUserList").style.display = "block";
            dojo.byId("discountPlanList").style.display = "none";
            dojo.byId("isAllUserDiv").style.display = "none";
            dojo.byId("isAllPlanDiv").style.display = "none";
            dijit.byId("isAllUser").set("checked", false);
            dijit.byId("isAllPlan").set("checked", false);
        } else if (type == "SELECTIVE" && discountType == "PARTICULAR") {
            dojo.byId("discountUserList").style.display = "block";
            dojo.byId("discountPlanList").style.display = "block";
            dojo.byId("isAllUserDiv").style.display = "block";
            dojo.byId("isAllPlanDiv").style.display = "block";
            dijit.byId("isAllUser").set("checked", false);
            dijit.byId("isAllPlan").set("checked", false);
        }
    },
    validateEndDate: function(date) {
        var start = dojo.date.locale.format(dijit.byId("discountStartDate").value, {datePattern: "dd/MM/yyyy", selector: "date"});
        var end = dojo.date.locale.format(date, {datePattern: "dd/MM/yyyy", selector: "date"});


        if (dijit.byId("discountStartDate").value != "Invalid Date") {
            var endDate = Date.parse(date);
            var startDate = Date.parse(dijit.byId("discountStartDate").value);
        }
        if (dijit.byId("discountStartDate").value == "Invalid Date") {
            registry.byId("appToaster").setContent("Select start date first", "error");
            registry.byId("appToaster").show();
        } else if (startDate > endDate) {
            dojo.byId("discountUserList").style.display = "none";
            dojo.byId("discountPlanList").style.display = "none";
            dojo.byId("isAllUserDiv").style.display = "none";
            dojo.byId("isAllPlanDiv").style.display = "none";
            dijit.byId("discountAddButton").setAttribute("disabled", true);

            registry.byId("appToaster").setContent("Please ensure that the End Date is greater than or equal to the Start Date.", "error");
            registry.byId("appToaster").show();
        } else if (startDate <= endDate) {
            AddDiscountManagement.validateDateRange();
        }
    },
    validateStartDate: function(date) {

        var start = dojo.date.locale.format(date, {datePattern: "dd/MM/yyyy", selector: "date"});
        var end = dojo.date.locale.format(dijit.byId("discountEndDate").value, {datePattern: "dd/MM/yyyy", selector: "date"});
        if (dijit.byId("discountEndDate").value != "Invalid Date") {
            var endDate = Date.parse(dijit.byId("discountEndDate").value);
            var startDate = Date.parse(date);
        }
        if (dijit.byId("discountEndDate").value == "Invalid Date") {
            AddDiscountManagement.validate(date);
        } else if (startDate > endDate) {
            dojo.byId("discountUserList").style.display = "none";
            dojo.byId("discountPlanList").style.display = "none";
            dojo.byId("isAllUserDiv").style.display = "none";
            dojo.byId("isAllPlanDiv").style.display = "none";
            dijit.byId("discountAddButton").setAttribute("disabled", true);

            registry.byId("appToaster").setContent("Please ensure that the End Date is greater than or equal to the Start Date.", "error");
            registry.byId("appToaster").show();
        } else if (startDate <= endDate) {
            AddDiscountManagement.validateDateRange();
        }
    },
    validateDateRange: function() {

        var startDate = dijit.byId("discountStartDate");
        var endDate = dijit.byId("discountEndDate");
        var type = dijit.byId("discountType");
        var applyTo = dijit.byId("discountApplyTo");
        var isAllPlan = dijit.byId("isAllPlan");
        var isAllUser = dijit.byId("isAllUser");

        this._discountValidateDateRangeStore.add({
            startDate: dojo.date.locale.format(startDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
            endDate: dojo.date.locale.format(endDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
            type: type.value,
            applyTo: applyTo.value,
            isAllPlan: isAllPlan.checked,
            isAllUser: isAllUser.checked
        }).then(function(data) {
            dojo.forEach(data, function(discountData) {
                if (discountData.result == "OK") {
                    dijit.byId("discountAddButton").setAttribute("disabled", false);
                    dijit.byId("discountApplyTo").setAttribute("disabled", false);
                    dijit.byId("isAllUser").setAttribute("disabled", false);
                    dojo.byId("discountApplyToDiv").style.display = "block";
                    dijit.byId("isAllPlan").setAttribute("disabled", false);
                    dojo.byId("discountUserList").style.display = "none";
                    dojo.byId("discountPlanList").style.display = "none";
                } else if (discountData.result == "available") {
                    dijit.byId("discountAddButton").setAttribute("disabled", false);
                    dojo.byId("discountApplyToValue").value = "SELECTIVE";
                    dojo.byId("discountApplyToDiv").style.display = "none";
                    if (type.value == "PARTICULAR") {
                        if (discountData.isAllPlan == "true" || discountData.isAllPlan == true) {
                            dijit.byId("isAllPlan").set("checked", true);
                            dijit.byId("isAllPlan").setAttribute("disabled", true);
                            dojo.byId("discountPlanList").style.display = "none";
                            dojo.byId("isAllPlanDiv").style.display = "block";

                        } else {
                            dijit.byId("isAllPlan").set("checked", false);
                            dijit.byId("isAllPlan").setAttribute("disabled", true);
                            dojo.byId("isAllPlanDiv").style.display = "none";
                            dojo.byId("discountPlanList").style.display = "block";
//                            var discountPlans = dojo.byId("discountPlans");
//                            var j;
//                            for (j = discountPlans.options.length - 1; j >= 0; j--) {
//                                discountPlans.remove(j);
//                                if (j == 0) {
//                                    for (var compute = 0; compute < discountData.computeOffer.length; compute++) {
//                                        var computeDate = discountData.computeOffer[compute];
//                                        var computeOpt = document.createElement('option');
//                                        computeOpt.innerHTML = "(" + computeDate.aliasZoneName + ")" + computeDate.name;
//                                        computeOpt.value = computeDate.id;
//                                        discountPlans.appendChild(computeOpt);
//                                    }
//                                }
//                            }
                        }
                        if (discountData.isAllUser == "true" || discountData.isAllUser == true) {
                            dijit.byId("isAllUser").set("checked", true);
                            dijit.byId("isAllUser").setAttribute("disabled", true);
                            dojo.byId("discountUserList").style.display = "none";
                            dojo.byId("isAllUserDiv").style.display = "block";

                        } else {
                            dijit.byId("isAllUser").set("checked", false);
                            dijit.byId("isAllUser").setAttribute("disabled", true);
                            dojo.byId("isAllUserDiv").style.display = "none";
                            dojo.byId("discountUserList").style.display = "block";
//                            var discountUsers = dojo.byId("discountUsers");
//                            var i;
//                            for (i = discountUsers.options.length - 1; i >= 0; i--) {
//                                discountUsers.remove(i);
//                                if (i == 0) {
//                                    for (var acc = 0; acc < discountData.accounts.length; acc++) {
//                                        var account = discountData.accounts[acc];
//                                        var opt = document.createElement('option');
//                                        opt.innerHTML = account.userName;
//                                        opt.value = account.id;
//                                        discountUsers.appendChild(opt);
//                                    }
//                                }
//                            }
                        }
                    } else if (type.value == "USER") {
                        dijit.byId("discountAddButton").setAttribute("disabled", false);
                        dijit.byId("discountApplyTo").setAttribute("disabled", true);
                        dojo.byId("discountApplyToDiv").style.display = "none";
                        dojo.byId("discountApplyToValue").value = "SELECTIVE";
                        dojo.byId("discountUserList").style.display = "block";
                        var discountUsers = dojo.byId("discountUsers");
                        var i;
                        for (i = discountUsers.options.length - 1; i >= 0; i--) {
                            discountUsers.remove(i);
                            if (i == 0) {
                                for (var acc = 0; acc < discountData.accounts.length; acc++) {
                                    var account = discountData.accounts[acc];
                                    var opt = document.createElement('option');
                                    opt.innerHTML = account.userName;
                                    opt.value = account.id;
                                    discountUsers.appendChild(opt);
                                }
                            }
                        }
                    }
                } else if (discountData.result == "FAILED") {
                    registry.byId("appToaster").setContent(translator.common.message.discountErrorInfo, "error");
                    dijit.byId("discountAddButton").setAttribute("disabled", true);
                    dijit.byId("discountApplyTo").setAttribute("disabled", true);
                    dojo.byId("discountApplyToDiv").style.display = "none";
                    dijit.byId("discountApplyTo").setValue("ALL");
                    dojo.byId("discountUserList").style.display = "none";
                    dojo.byId("discountPlanList").style.display = "none";
                    dojo.byId("isAllUserDiv").style.display = "none";
                    dojo.byId("isAllPlanDiv").style.display = "none";
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    validate: function(date) {

        var startDate = dijit.byId("discountStartDate");
        var endDate = dijit.byId("discountEndDate");
        var type = dijit.byId("discountType");
        var applyTo = dijit.byId("discountApplyTo");
        var isAllPlan = dijit.byId("isAllPlan");
        var isAllUser = dijit.byId("isAllUser");

        this._discountValidateStore.add({
            date: dojo.date.locale.format(date, {datePattern: "dd/MM/yyyy", selector: "date"}),
            type: type.value,
            applyTo: applyTo.value,
            isAllPlan: isAllPlan.checked,
            isAllUser: isAllUser.checked
        }).then(function(data) {
            dojo.forEach(data, function(discountData) {
                if (discountData.result == "OK") {
                    dijit.byId("discountAddButton").setAttribute("disabled", false);
                    dijit.byId("discountApplyTo").setAttribute("disabled", false);
                    dojo.byId("discountApplyToDiv").style.display = "block";
                } else if (discountData.result == "available") {
                    if (type.value == "PARTICULAR") {
                        dojo.byId("discountPlanList").style.display = "block";
                        dojo.byId("isAllUserDiv").style.display = "none";
                        dojo.byId("isAllPlanDiv").style.display = "none";
                        var discountPlans = dojo.byId("discountPlans");
                        var j;
                        for (j = discountPlans.options.length - 1; j >= 0; j--) {
                            discountPlans.remove(j);
                        }
                        for (var compute = 0; compute < discountData.computeOffer.length; compute++) {
                            var computeData = discountData.computeOffer[compute];
                            var computeOpt = document.createElement('option');
                            computeOpt.innerHTML = "(" + computeData.aliasZoneName + ")" + computeData.name;
                            computeOpt.value = computeData.id;
                            discountPlans.appendChild(computeOpt);
                        }
                    } else if (type.value == "USER") {
                        dijit.byId("discountAddButton").setAttribute("disabled", false);
                        dijit.byId("discountApplyTo").setAttribute("disabled", true);
                        dojo.byId("discountApplyToDiv").style.display = "none";
                        dojo.byId("discountApplyToValue").value = "SELECTIVE";
                        dojo.byId("discountUserList").style.display = "block";
                        var discountUsers = dojo.byId("discountUsers");
                        var i;
                        for (i = discountUsers.options.length - 1; i >= 0; i--) {
                            discountUsers.remove(i);
                        }
                        for (var acc = 0; acc < discountData.accounts.length; acc++) {
                            var account = discountData.accounts[acc];
                            var opt = document.createElement('option');
                            opt.innerHTML = account.userName;
                            opt.value = account.id;
                            discountUsers.appendChild(opt);
                        }
                    }

                } else {
                    registry.byId("appToaster").setContent(translator.common.message.discountErrorInfo, "error");
                    dijit.byId("discountAddButton").setAttribute("disabled", true);
                    dijit.byId("discountApplyTo").setAttribute("disabled", true);
                    dojo.byId("discountApplyToDiv").style.display = "none";
                    dijit.byId("discountApplyTo").setValue("ALL");
                    dojo.byId("discountUserList").style.display = "none";
                    dojo.byId("discountPlanList").style.display = "none";
                    dojo.byId("isAllUserDiv").style.display = "none";
                    dojo.byId("isAllPlanDiv").style.display = "none";
                    registry.byId("appToaster").show();
                }
            });
        });
    },
    add: function() {
        var applyTo = dojo.byId("discountApplyToValue").value;
        var startDate = dijit.byId("discountStartDate");
        var discountName = dijit.byId("discountName");
        var endDate = dijit.byId("discountEndDate");
        var type = dijit.byId("discountType");
        var discountValue = dijit.byId("discountValue");
        var discountPlans = dojo.byId("discountPlans");
        var discountUsers = dojo.byId("discountUsers");
        var subType;
        var pageNode = dojo.byId("discountForm");
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

            if (dijit.byId("createVmRadioButton").checked == true || dijit.byId("createVmRadioButton").checked == "true") {
                subType = dijit.byId("createVmRadioButton").value;
            } else {
                subType = dijit.byId("invoiceDiscountRadioButton").value;
            }

            var billingCycles = dijit.byId("billingCycles");
            var isAllPlan = dijit.byId("isAllPlan");
            var isAllUser = dijit.byId("isAllUser");

            var selectedPlanArray = new Array();
            var selectedUserArray = new Array();
            var i;
            var countPlan = 0;
            for (i = 0; i < discountPlans.options.length; i++) {
                if (discountPlans.options[i].selected) {
                    selectedPlanArray[countPlan] = discountPlans.options[i].value;
                    countPlan++;
                }
            }

            var j;
            var countUser = 0;
            for (j = 0; j < discountUsers.options.length; j++) {
                if (discountUsers.options[j].selected) {
                    selectedUserArray[countUser] = discountUsers.options[j].value;
                    countUser++;
                }
            }

            if (type.value == "USER" && applyTo == "SELECTIVE" && selectedUserArray.length == 0) {
                registry.byId("appToaster").setContent("Select user to add discount!", "error");
                registry.byId("appToaster").show();
            } else if (type.value == "PARTICULAR" && applyTo == "SELECTIVE" && isAllUser.checked == false && selectedUserArray.length == 0) {
                registry.byId("appToaster").setContent("Select user to add discount!", "error");
                registry.byId("appToaster").show();
            } else if (type.value == "PARTICULAR" && applyTo == "SELECTIVE" && isAllPlan.checked == false && selectedPlanArray.length == 0) {
                registry.byId("appToaster").setContent("Select plan to add discount!", "error");
                registry.byId("appToaster").show();
            } else {
                dijit.byId('discountAddButton').set('style', {display: 'none'});
                dojo.byId("addDiscountLoader").style.display = "block";
                this._discountStore.add({
                    startDate: dojo.date.locale.format(startDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
                    endDate: dojo.date.locale.format(endDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
                    type: type.value,
                    discountName: discountName.value,
                    discountValue: discountValue.value,
                    applyTo: applyTo,
                    discountPlans: selectedPlanArray.toString(),
                    discountUsers: selectedUserArray.toString(),
                    subType: subType,
                    billingCycles: billingCycles.value,
                    isAllPlan: isAllPlan.checked,
                    isAllUser: isAllUser.checked
                }).then(function(data) {
                    dojo.forEach(data, function(discountData) {
                        dijit.byId('discountAddButton').set('style', {display: 'block'});
                        dojo.byId("addDiscountLoader").style.display = "none";
                        if (discountData.result == "OK") {
                            dijit.byId("discountForm").reset();
                            registry.byId("appToaster").setContent(translator.common.message.addedSuccess, "message");
                            registry.byId("appToaster").show();
                            core.router.go("#/admin/discount");
                        } else {
                            registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                            registry.byId("appToaster").show();
                        }
                        ;
                    });
                });
            }
        }
    },
    validateDiscountName: function(name) {

        this._discountStore.query({
            name: name
        }).then(function(data) {
            if (data == "FAILED") {
                dojo.byId("discountNameValidationMessage").style.display = "block";

                dijit.byId("discountName").validator = function() {
                    return false;
                };
            } else {
                dojo.byId("discountNameValidationMessage").style.display = "none";
                dijit.byId("discountName").validator = function() {
                    return true;
                };
            }
        });
    },
    getUser: function() {
        
        
        var startDate = dijit.byId("discountStartDate");
        var endDate = dijit.byId("discountEndDate");
        var type = dijit.byId("discountType");
        var applyTo = dijit.byId("discountApplyTo");
        var isAllPlan = dijit.byId("isAllPlan");
        var isAllUser = dijit.byId("isAllUser");
        
        
        var discountPlans = dojo.byId("discountPlans");
        var selectedPlanArray = new Array();
        var i;
        var countPlan = 0;
        for (i = 0; i < discountPlans.options.length; i++) {
            if (discountPlans.options[i].selected) {
                selectedPlanArray[countPlan] = discountPlans.options[i].value;
                countPlan++;
            }
        }

        var discountUsers = dojo.byId("discountUsers");
        var i;
        for (i = discountUsers.options.length - 1; i >= 0; i--) {
            discountUsers.remove(i);
        }

        var userRestStore = new JsonRest({
            target: core.getContextPath() + "/api/discount/getUser/"
        });
       
        userRestStore.add({plans: selectedPlanArray.toString(), 
            startDate: dojo.date.locale.format(startDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
            endDate: dojo.date.locale.format(endDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
            type: type.value,
        }).then(function(data) {
            dojo.forEach(data, function(userData) {
                var opt = document.createElement('option');
                opt.innerHTML = userData.userName;
                opt.value = userData.id;
                discountUsers.appendChild(opt);
            });
        });


    }
};
var DiscountManagement = {
    _discountStore: "",
    _discountValidateStore: "",
    _discountValidateDateRangeStore: "",
    init: function() {
        this._discountStore = new JsonRest({
            target: core.getContextPath() + "/api/discount/"
        });

        this._discountValidateStore = new JsonRest({
            target: core.getContextPath() + "/api/discount/validate"
        });

        this._discountValidateDateRangeStore = new JsonRest({
            target: core.getContextPath() + "/api/discount/validateDateRange"
        });

        var billableItemStore = new JsonRest({
            target: core.getContextPath() + "/api/billableItem/"
        });
        
        billableItemStore.get(6).then(function(billableItemData) {
            dojo.forEach(billableItemData, function(data) {
                if (data.enabled === false || data.enabled === "false") {
                    dojo.byId("didcountDidable").style.display = "block";
                    dojo.byId("discountButton").style.display = "none";
                } else {
                    dojo.byId("didcountDidable").style.display = "none";
                    dojo.byId("discountButton").style.display = "block";
                }
            });
        });
    },
    populateValues: function() {

        var discountRestStore = this._discountStore;

        if (dijit.byId("adminDiscountDataGrid")) {
            dijit.byId("adminDiscountDataGrid").destroyRecursive();
        }
        dojo.byId("discountList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>" + translator.common.loader.loading + "</p>";
        var discountGridData = {
            items: []
        };
        var discountDataList = new ItemFileWriteStore({data: discountGridData});
        var discountDataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.value, 'field': 'discountValue', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.billing.billingCycle, 'field': 'billingCycles', 'width': '150px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.startDate, 'field': 'startData', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.endDate, 'field': 'endData', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.allPlan, 'field': 'allPlan', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.allUser, 'field': 'allUser', 'width': '100px', 'datatype': 'string', 'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {

                        var html = "<a class='btn-flat danger' href='#/admin/discount/delete/" + data + "'>"+translator.common.deleteData+"</a>";


                        return html;
                    }, 'width': '100%'}
            ]
        ];
        var status = true;
        this._discountStore.query().then(function(data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                dojo.byId("discountList").innerHTML = "";
                dojo.byId("noDiscountMessageBox").style.display = "block";
            } else {
                dojo.byId("noDiscountMessageBox").style.display = "none";
                dojo.forEach(data, function(discountData) {
                    if (discountData.applyTo == "ALL") {
                        discountDataList.newItem({
                            id: discountData.id,
                            name: discountData.name,
                            type: discountData.type,
                            discountValue: discountData.discountValue,
                            startData: discountData.startDate,
                            endData: discountData.endDate,
                            allPlan: status,
                            allUser: status,
                            billingCycles: discountData.billingCycles,
                            action: discountData.id
                        });
                    } else {

                        var allPlans;
                        if (discountData.isAllPlan == true) {
                            allPlans = true;
                        } else {
                            allPlans = discountData.planNames;
                        }
                        var allUser;
                        if (discountData.isAllUser == true) {
                            allUser = true;
                        } else {
                            allUser = discountData.userName;
                        }

                        discountDataList.newItem({
                            id: discountData.id,
                            name: discountData.name,
                            type: discountData.type,
                            discountValue: discountData.discountValue,
                            startData: discountData.startDate,
                            endData: discountData.endDate,
                            allPlan: allPlans,
                            allUser: allUser,
                            billingCycles: discountData.billingCycles,
                            action: discountData.id
                        });
                    }

                });
                dojo.byId("discountList").innerHTML = "";
                var taxDataGrid = new EnhancedGrid({
                    id: 'adminDiscountDataGrid',
                    "class" : "span12",
                    store: discountDataList,
                    structure: discountDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                taxDataGrid.placeAt("discountList");
                taxDataGrid.startup();
            }

        });

    }

};
var DeleteDiscountManagement = {
    _currentId: "",
    init: function(currentId) {
        this._currentId = currentId;
    },
    populateValues: function() {

        var discountRestStore = new JsonRest({
            target: core.getContextPath() + "/api/discount/"
        });

        discountRestStore.remove(this._currentId).then(function(result) {
            if (result == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.deletedSuccess, "message");
                registry.byId("appToaster").show();
                core.router.go("#/admin/discount");
                window.location.reload();


            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();
                core.router.go("#/admin/discount");
                window.location.reload();

            }
        });
    }
};
window.DiscountManagement = DiscountManagement;
window.DeleteDiscountManagement = DeleteDiscountManagement;
window.AddDiscountManagement = AddDiscountManagement;