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
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "dijit/layout/TabContainer",
    "dijit/form/DateTextBox",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane", 
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
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
    ],
    function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
    ItemFileWriteStore, DataGrid, ContentPane, Source, MultiSelect, dom, win) {             
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
        window.DataGrid = DataGrid;
        window.Source = Source;
        window.MultiSelect = MultiSelect;
        window.dom = dom;
        window.win = win;
        
        controller ({
            name: "billing",
            module: "admin",
            filePath: "/js/app/admin/billing.js",
            layout: {
                name: "billing",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {
                core.ui.loadTemplate("billing", core.ui.getContentId());
                
//                DraftInvoiceInfo.init();
//                DraftInvoiceInfo.populateValues();       
            }),
            
            "invoice": action(function() {
                core.ui.loadTemplate("invoiceUserList", core.ui.getContentId()); 
                
                DraftInvoiceInfo.init();
                DraftInvoiceInfo.populateValues();    
            }),
            "draftInvoice" : action(function() {
                core.ui.loadTemplate("invoiceUserList", core.ui.getContentId());
                
                DraftInvoiceInfo.init();
                DraftInvoiceInfo.populateValues();    
            }),
            "finalInvoice": action(function() {
               core.ui.loadTemplate("finalInvoiceList", core.ui.getContentId());
                
               FinalInvoiceInfo.init();
               FinalInvoiceInfo.populateValues();    

            }),
            "payment" : action(function() {
               core.ui.loadTemplate("paymentList", core.ui.getContentId());
                
               PaymentInfo.init();
               PaymentInfo.populateValues();    

            }),
            "currentMonth" : action(function() {
               core.ui.loadTemplate("paymentCurrentMonthList", core.ui.getContentId());
                
               CurrentMonthPaymentInfo.init();
               CurrentMonthPaymentInfo.populateValues();    

            }),
            "thisYear" : action(function() {
               core.ui.loadTemplate("paymentPreMonthList", core.ui.getContentId());
                
               PreMonthPaymentInfo.init();
               PreMonthPaymentInfo.populateValues();    

            }),
            "add": action(function() {
        
                var addForm = dijit.byId("addInvoiceItemForm");
                if (addForm) {
                    addForm.destroyRecursive();
                }
                if(dijit.byId("addItemButton")) {
                    dijit.byId("addItemButton").destroyRecursive();
                }
                if(dijit.byId("invoiceRecurringItemBillingCycles")) {
                    dijit.byId("invoiceRecurringItemBillingCycles").destroyRecursive();
                }
                
                core.ui.loadTemplate("addInvoiceItem", core.ui.getContentId());
                
               AddItemForInvoice.init();
               AddItemForInvoice.populateValues();    

            }),
            "recurringItem": action(function() {
                core.ui.loadTemplate("recurringItemListInBilling", core.ui.getContentId());
                
                RecurringItemInBilling.init();
                RecurringItemInBilling.populateValues();        

            }),
            "customItem": action(function() {
                core.ui.loadTemplate("customItemList", core.ui.getContentId());
                
                CustomItemInfo.init();
                CustomItemInfo.populateValues();        

            })
            
        });
    });

    
    
var RecurringItemInBilling = {
    _recurringItemStore : "",
    _customRestStore:"",
    init : function() {
    
        this._recurringItemStore = new JsonRest({
                target: core.getContextPath()+"/api/recurringItem"
        });
        this._customRestStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/customItem"
        });
        
    },     
   
    
    populateValues : function() {

        if(dijit.byId("recurrinItemGridWidgetInBilling")) {
            dijit.byId("recurrinItemGridWidgetInBilling").destroyRecursive();
        }
        var gridData = {
            items: []
        };
        dojo.byId("recurringItemListGridInBilling").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
        this._customRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("recAmt").innerHTML = resultData.recurringItemsTotalAmount.toFixed(2);
                dojo.byId("cusAmt").innerHTML =  resultData.customItemsTotalAmount.toFixed(2);                 

                dojo.byId("recDate").innerHTML = resultData.currMonth;
                dojo.byId("cusDate").innerHTML = resultData.currMonth;  
                
                dojo.byId("cusAmtCurrency").innerHTML = resultData.currency;
                dojo.byId("recAmtCurrency").innerHTML = resultData.currency;  
                
                
            });
        });
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [[
            {'name': 'Id', 'field': 'id', 'hidden': 'true'},
            {'name': 'actName', 'field': 'actName', 'hidden': 'true'},
            {'name': translator.common.name, 'field': 'name', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.account.name, 'field': 'account', 'width': '100px', 'datatype':'string',  'autoComplete': 'true' ,'formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
            }}, 
            {'name': translator.common.amount, 'field': 'amount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.taxPercent, 'field': 'taxPercent', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.billingCycle, 'field': 'cycle', 'width': '100px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
            }}, 
            {'name': translator.common.billing.noOfUses, 'field': 'uses', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.taxAmount, 'field': 'taxAmount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.grid.layout.totalAmount, 'field': 'totalAmount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {                         
                                             
                        return  "<span class='orangeColor'>" + data + "</span>";
                }
            },
            {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) {
                        var html = "<a href='#/admin/recurringItem/edit/"+data+"' title='Edit'><i class='icon-edit'></i></a>" + 
                                   "<a href='#/admin/recurringItem/delete/"+data+"' class='offset1' title='Delete'><i class='icon-remove'></i></a>";
                      
                       return html;
                },'width': '100%'}
        ]];     
        this._recurringItemStore.query().then(function(data) {                
            if(data.length == 0 || data == undefined || data == 'undefined' || data == "" || data == " ") {
                dojo.byId("recurringItemListGridInBilling").innerHTML  = "";
                dojo.byId("noRecurringItemMessageBox").style.display = "block";       
            } else {
                dojo.byId("noRecurringItemMessageBox").style.display = "none";       
                dojo.forEach(data, function(recurringItemData) {   
                    gridDataList.newItem({
                        id: recurringItemData.id,
                        name:recurringItemData.name,
                        account: recurringItemData.account,
                        amount: recurringItemData.amount, 
                        taxPercent: recurringItemData.taxPercent, 
                        cycle: recurringItemData.cycle, 
                        uses: recurringItemData.uses, 
                        taxAmount: recurringItemData.taxAmount,
                        totalAmount: recurringItemData.totalAmount,                     
                        actName:recurringItemData.name,
                        action: recurringItemData.id
                    });
                });
                dojo.byId("recurringItemListGridInBilling").innerHTML  = "";
                var recurringItemGrid = new EnhancedGrid({
                    id: 'recurrinItemGridWidgetInBilling',
                    "class" : "span12",
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                recurringItemGrid.placeAt("recurringItemListGridInBilling");
                recurringItemGrid.startup();
            }            
        });                         
    }  
};    
    
    
var CustomItemInfo = {
   _customRestStore:"",
    init : function() {
        this._customRestStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/customItem"
        });
    },
    populateValues : function() {
        if (dijit.byId("customItemList")) {
        dijit.byId("customItemList").destroyRecursive();
    }
    var customData = {
        items: []
    };
    dojo.byId("customItemListGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
    var customDataList = new ItemFileWriteStore({data: customData});
    var customDataLayout = [[
            {'field': 'id', 'hidden' : 'true'},
            {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},   
            {'name': translator.common.account.name, 'field': 'account', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            },    
            {'name': translator.common.amount, 'field': 'amount', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.taxAmount, 'field': 'taxAmount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.grid.layout.totalAmount, 'field': 'totalAmount', 'width': '100%', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {                         
                                             
                        return  "<span class='orangeColor'>" + data + "</span>";
                }
            }      
        ]
    ];

    this._customRestStore.query().then(function(data) {
        dojo.forEach(data, function(resultData) {

            dojo.byId("recAmt").innerHTML =  resultData.recurringItemsTotalAmount.toFixed(2);
            dojo.byId("cusAmt").innerHTML =   resultData.customItemsTotalAmount.toFixed(2);           

            dojo.byId("recDate").innerHTML = resultData.currMonth;
            dojo.byId("cusDate").innerHTML = resultData.currMonth;     
            
            dojo.byId("cusAmtCurrency").innerHTML = resultData.currency;
            dojo.byId("recAmtCurrency").innerHTML = resultData.currency;  
            if(resultData.customItemList.length == 0 || resultData.customItemList == undefined || resultData.customItemList == 'undefined' || resultData.customItemList == "" || resultData.customItemList == " ") {
                dojo.byId("customItemListGrid").innerHTML  = "";
                dojo.byId("noCustomItemMessageBox").style.display = "block"; 
            } else {
                dojo.byId("noCustomItemMessageBox").style.display = "none"; 
                
                dojo.forEach(resultData.customItemList, function(data) {
                    customDataList.newItem({
                        id: data.id,
                        taxAmount: data.taxAmount,
                        totalAmount: Math.round(data.totalAmount * 100) / 100,
                        amount: data.amount,
                        account: data.account,
                        name : data.name
                    });  
                });   
                dojo.byId("customItemListGrid").innerHTML  = "";
                var dataGrid = new EnhancedGrid({
                    id: 'customItemList',
                    store: customDataList,
                    structure: customDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("customItemListGrid");
                dataGrid.startup();
            }                       
        });
    });    
}
};
    
var AddItemForInvoice = {
    _invoiceStore : "",
    _configRestStore: "",
    _accountStore: "",
    _accountListStore: "",
    init : function() {
    
        this._invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/"
        }); 
        
        this._accountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"
        });
        
        this._accountListStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        
        var invoiceCountStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/count"
        });
        
        invoiceCountStore.query({status:"DRAFT"}).then(function(data) {             
            if(data[0].invoiceList.length == 0) {
                core.router.go("#/admin/billing/invoice");
                
                registry.byId("appToaster").setContent(translator.common.message.addInvoiceItem, "error");
                registry.byId("appToaster").show();
            } 
        });
    },
    populateValues : function() {                
        var sel = dojo.byId('invoiceItemAccountListInInvoicePage');
        this._accountListStore.query().then(function(data){
            dojo.forEach(data, function(account) {
                if(account.status.name == "ACTIVE" || account.status.name == "DISABLED" || account.status.name == "SUSPENDED") {
                    if(account.invoice == "yes") {
                        var opt = document.createElement('option');
                        opt.innerHTML = account.userName;
                        opt.value = account.id;
                        sel.appendChild(opt);
                    }
                }
                
            });
        });     
    },    cancel : function() { 
        dijit.byId("addInvoiceItemForm").reset();
        core.router.go("#/admin/billing/invoice");
    },
    showAccountList : function(value) {

        if(value == "ALL") {
            dojo.byId("accountListDiv").style.display = "none";
        } else {
            dojo.byId("accountListDiv").style.display = "block";
        }
    },
    enableTax :function(taxEnabled) {
        if(taxEnabled.checked == "true" || taxEnabled.checked== true) {
            dojo.byId("recurringItemTaxPercentageDiv").style.display = "block";
        } else {
            dojo.byId("recurringItemTaxPercentageDiv").style.display = "none";
        }
    },
    enableRecuringItem: function(data) {

        if (data.checked == "true" || data.checked == true) {
            dojo.byId("recurringItemBillingCyclesDiv").style.display = "block";
        } else {
            dojo.byId("recurringItemBillingCyclesDiv").style.display = "none";
        }
    },
    add: function() {
        var name = dijit.byId("invoiceItemNameInInvoicePage");
        var amount = dijit.byId("invoiceItemAmountInInvoicePage");
//        var taxable = dijit.byId("invoiceItemTaxEnabledInInvoicePage").checked;
        var isRecurring = dijit.byId("invoiceItemIsRecurringInInvoicePage").checked;
//        var taxPercentage = dijit.byId("invoiceItemTaxPercentageInInvoicePage");
        var invoiceItemForAccount = dijit.byId("forAccountInInvoicePage");
        var accountList = dojo.byId("invoiceItemAccountListInInvoicePage");
        var recurringItemBillingCycles = dijit.byId("invoiceRecurringItemBillingCycles");
        
//        if(taxable == true || taxable == 'true') {
//            taxPercentage.set("required", true);
//        } else {
//            taxPercentage.set("required", false);
//        }
        
        if(isRecurring === true || isRecurring === 'true') {
            recurringItemBillingCycles.set("required", true);
        } else {
            recurringItemBillingCycles.set("required", false);
        }
        
        var node = dojo.byId("addInvoiceItemForm");
        var status = true;
            var nodeWidget = dijit.registry.findWidgets(node);        
            var firstNode = "";
            dojo.forEach(nodeWidget, function(el) {
                if (el.validate && !el.validate()) {
                    el.focus();             
                    if (!firstNode) {
                        firstNode = el;
                    }
                    status = false;
                }
            });
        if (status === true) {        
            var selectedAccountArray = new Array();
            var j;
            var countUser = 0;
            for (j=0; j < accountList.options.length; j++) {
              if (accountList.options[j].selected) {
                selectedAccountArray[countUser] = accountList.options[j].value;
                countUser++;
              }
            }
        
            dijit.byId("invoiceItemButton").setAttribute('style', 'display: none');
            dojo.byId("invoiceItemLoader").style.display = "block";
            this._invoiceStore.add({
                amount: amount.value,
                name: name.value,
//                taxable: taxable,
//                taxPercentage: taxPercentage.value,
                accountId: selectedAccountArray.toString(),
                isRecurring: isRecurring,
                isAllAccount : invoiceItemForAccount.value,
                billingCycles : recurringItemBillingCycles.value
            }).then(function(data) {
                dijit.byId("invoiceItemButton").setAttribute('style', 'display: block');
                dojo.byId("invoiceItemLoader").style.display = "none";
                if (data == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.itemAdded, "message");
                    registry.byId("appToaster").show();
    //                    window.frames['cuurrentInvoiceFrame'].location.reload();
                    core.router.go("#/admin/billing/invoice");
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                }
            });        
        }
    } 
    };

var DraftInvoiceInfo = {
    _invoiceCountStore:"",
    init : function() {
        this._invoiceCountStore = new JsonRest({
        target: core.getContextPath()+"/api/invoice/count"
    });
    },
    populateValues : function() {
        if (dijit.byId("draftInvoiceGrid")) {
            dijit.byId("draftInvoiceGrid").destroyRecursive();
        }
        var invoiceData = {
            items: []        
        };
        dojo.byId("adminDraftInvoiceList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
        var taxDataList = new ItemFileWriteStore({data: invoiceData});
        var taxDataLayout = [
            [
                {'name': translator.common.billing.grid.layout.invoiceNo, 'field': 'invoiceNo', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.account.name, 'field': 'accountName', 'width': '150px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
                }},                 
                {'name': translator.common.billing.grid.layout.currentUsage, 'field': 'currentDue', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.billing.grid.layout.totalPayable, 'field': 'totalAmount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {                         
                                             
                        return  "<span class='orangeColor'>" + data + "</span>";
                    }
                }, 
                {'name': translator.common.billing.grid.layout.invoiceDate, 'field': 'invoiceDate', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
                }}, 
                {'name': translator.common.status.name, 'field': 'status', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                    'formatter': function(data) {
                        var html = "<a href='" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=invoiceSummary&invoiceId=" + data + "&filename=invoice-" + data + "'><i class='pdf_icon'></i></a>" +
                            "<a href='#/admin/account/viewInvoice/" + data + "'>View</a>";                       
                        return html;
                    }, 'width': '100%', 'hidden' : 'true'
                } 
            ]
        ];
        this._invoiceCountStore.query({status:"DRAFT"}).then(function(data) {             
            if(data[0].invoiceList.length == 0 || data[0].invoiceList == undefined || data[0].invoiceList == 'undefined' || data[0].invoiceList == "" || data[0].invoiceList == " ") {
                dojo.byId("adminDraftInvoiceList").innerHTML = "";
                dojo.byId("noInvoiceMessageBox").style.display = "block";
            } else {
                dojo.byId("noInvoiceMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {
                    dojo.byId("daily").innerHTML = resultData.currency + " " + Math.round((resultData.daily) * 100) / 100;                            
                    dojo.byId("monthly").innerHTML = resultData.currency + " " +  Math.round((resultData.monthly) * 100) / 100;
                    dojo.byId("preMonth").innerHTML = resultData.currency + " " +  Math.round((resultData.previousMonth) * 100) / 100;

                    dojo.byId("dailyDate").innerHTML = resultData.dailyDate;
                    dojo.byId("monthlyDate").innerHTML = resultData.currMonth;
                    dojo.byId("preMonthDate").innerHTML = resultData.preMonth;

                    dojo.forEach(resultData.invoiceList, function(invoiceData) {
                        taxDataList.newItem({
                            invoiceNo: "0000" + invoiceData.id,
                            currentDue: invoiceData.currentDue,
                            totalAmount: invoiceData.totalAmount < 0  ?  0 : Math.round(invoiceData.totalAmount * 100) / 100,
                            invoiceDate: invoiceData.invoiceDate,
                            status: invoiceData.status,
                            accountName: invoiceData.accountName,
                            action: invoiceData.id
                        });            
                    });
                });
                dojo.byId("adminDraftInvoiceList").innerHTML = "";
                var dataGrid = new EnhancedGrid({                
                    id: 'draftInvoiceGrid',                
                    "class" : "span12",
                    store: taxDataList,
                    structure: taxDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminDraftInvoiceList");
                dataGrid.startup();
            }
            dojo.forEach(data, function(resultData) {
                
                dojo.byId("daily").innerHTML =  resultData.daily.toFixed(2);                            
                dojo.byId("monthly").innerHTML =  resultData.monthly.toFixed(2); 
                dojo.byId("preMonth").innerHTML =  resultData.previousMonth.toFixed(2);
                
                dojo.byId("dailyDate").innerHTML = resultData.dailyDate;
                dojo.byId("monthlyDate").innerHTML = resultData.currMonth;
                dojo.byId("preMonthDate").innerHTML = resultData.preMonth;
           
                dojo.byId("monthlyUsd").innerHTML = resultData.currency;
                dojo.byId("dailyUsd").innerHTML = resultData.currency;
                dojo.byId("preMonthUsd").innerHTML = resultData.currency;
              
            });        
        });       
    }
};
    
var FinalInvoiceInfo = {
    _invoiceCountStore:"",
    init : function() {
        this._invoiceCountStore = new JsonRest({
        target: core.getContextPath()+"/api/invoice/count"
    });
    },
    populateValues : function() {
        if (dijit.byId("finalInvoiceGrid")) {
        dijit.byId("finalInvoiceGrid").destroyRecursive();
    }
    var invoiceData = {
        items: []
    };
    dojo.byId("adminFinalInvoiceList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
    var taxDataList = new ItemFileWriteStore({data: invoiceData});
    var taxDataLayout = [[
            {'name': translator.common.billing.grid.layout.invoiceNo, 'field': 'invoiceNo', 'width': '100px','datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.account.name, 'field': 'accountName', 'width': '150px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
            }},    
            {'name': translator.common.billing.grid.layout.currentUsage, 'field': 'currentDue', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.grid.layout.totalPayable, 'field': 'totalAmount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {                         
                                             
                        return  "<span class='orangeColor'>" + data + "</span>";
                }
            }, 
            {'name': translator.common.billing.grid.layout.invoiceDate, 'field': 'invoiceDate', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
            }}, 
            {'name': translator.common.status.name, 'field': 'status', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.action, 'field': 'action',
                'formatter': function(data) {

                     var html = "<a href='" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=invoiceSummary&invoiceId=" + data + "&filename=invoice-" + data + "'><i class='pdf_icon'></i></a>" +
                            "<a href='#/admin/account/viewInvoice/" + data + "'>View</a>";
                    return html;

            }, 'width': '100%', 'hidden' : 'true'}
        ]
    ];

    this._invoiceCountStore.query({status:"FINAL"}).then(function(data) {
        dojo.forEach(data, function(resultData) {
            dojo.byId("daily").innerHTML =  resultData.daily.toFixed(2);                            
            dojo.byId("monthly").innerHTML = resultData.monthly.toFixed(2);
            dojo.byId("preMonth").innerHTML = resultData.previousMonth.toFixed(2);

            dojo.byId("dailyDate").innerHTML = resultData.dailyDate;
            dojo.byId("monthlyDate").innerHTML = resultData.currMonth;
            dojo.byId("preMonthDate").innerHTML = resultData.preMonth;
            
            dojo.byId("monthlyUsd").innerHTML = resultData.currency;
            dojo.byId("dailyUsd").innerHTML = resultData.currency;
            dojo.byId("preMonthUsd").innerHTML = resultData.currency;
            
            if(resultData.invoiceList.length == 0 || resultData.invoiceList == undefined || resultData.invoiceList == 'undefined' || resultData.invoiceList == "" || resultData.invoiceList == " ") {
                 dojo.byId("noFinalInvoiceMessageBox").style.display = "block";  
                 dojo.byId("adminFinalInvoiceList").innerHTML = "";
            } else {
                dojo.byId("noFinalInvoiceMessageBox").style.display = "none";  
                
                dojo.forEach(resultData.invoiceList, function(invoiceData) {
                    taxDataList.newItem({
                        invoiceNo: "0000" + invoiceData.id,
                        accountName:invoiceData.accountName,
                        currentDue: invoiceData.currentDue,
                        totalAmount: invoiceData.totalAmount < 0  ?  0 : Math.round(invoiceData.totalAmount * 100) / 100,
                        invoiceDate: invoiceData.invoiceDate,
                        status: invoiceData.status,
                        action: invoiceData.id
                    });  
                });   
                
                dojo.byId("adminFinalInvoiceList").innerHTML = "";
                
                var dataGrid = new EnhancedGrid({
                    id: 'finalInvoiceGrid',
                    "class" : "span12",
                    store: taxDataList,
                    structure: taxDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminFinalInvoiceList");
                dataGrid.startup();
            }            
        });
    });    
}
};
    
var PaymentInfo = {
    _paymentCountRestStore:"",
    init : function() {
        this._paymentCountRestStore = new JsonRest({
        target: core.getContextPath()+"/api/payment/count"
    });
    },
    populateValues : function() {
        if (dijit.byId("todayPaymentGrid")) {
        dijit.byId("todayPaymentGrid").destroyRecursive();
    }
    var paymentData = {
        items: []
    };
    dojo.byId("adminTodayPaymentList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
    var paymentDataList = new ItemFileWriteStore({data: paymentData});
    var paymentDataLayout = [
        [
            {'field': 'id', 'hidden' : 'true'},
            {'name': translator.common.billing.grid.layout.tockenNo, 'field': 'tockenNo', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            },    
            {'name': translator.common.account.name, 'field': 'account', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                         
                                             
                                return  "<span class='bold'>" + data + "</span>";
            }},    
            {'name': translator.common.gateway, 'field': 'gateway', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.amount, 'field': 'amount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.grid.layout.processingFee, 'field': 'processingFee', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.grid.layout.totalAmount, 'field': 'totalAmount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {                                                                      
                    return  "<span class='orangeColor'>" + data + "</span>";
                }
            },
            {'name': translator.common.billing.grid.layout.paymentDate, 'field': 'paymentDate', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.status.name, 'field': 'status', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data){
                       if(data === "SUCCESS") {
                           return  "<span class='bold' style='color: #36bd55;'>" + data + "</span>";
                       } else if(data === "Pending") {
                           return  "<span class='bold' style='color: #49bcd0;'>" + data + "</span>";
                       } else if(data === "FAILED") {
                           return  "<span class='bold' style='color: #f2443b;'>" + data + "</span>";
                       } else {
                           return data;
                       }
                        
            }}
        ]
    ];

    this._paymentCountRestStore.query({month: "Today"}).then(function(data) {
        dojo.forEach(data, function(resultData) {            
            dojo.byId("daily").innerHTML =  resultData.dailyPaymentAmount.toFixed(2);
            dojo.byId("preMonth").innerHTML =  resultData.preMonthPaymentAmount.toFixed(2);
            dojo.byId("monthly").innerHTML =   resultData.currentMonthPaymentAmount.toFixed(2);           

            dojo.byId("dailyDate").innerHTML = resultData.dailyDate;
            dojo.byId("monthlyDate").innerHTML = resultData.currMonth;
            dojo.byId("preMonthDate").innerHTML = resultData.preMonth;    
            
            dojo.byId("dailycurrency").innerHTML = resultData.currency;
            dojo.byId("monthlyCurrency").innerHTML = resultData.currency;
            dojo.byId("preMonthCurrency").innerHTML = resultData.currency;
            
            
            if(resultData.paymentItems.length == 0 || resultData.paymentItems == undefined || resultData.paymentItems == 'undefined' || resultData.paymentItems == "" || resultData.paymentItems == " ") {
                dojo.byId("noPaymentMessageBox").style.display = "block"; 
                dojo.byId("adminTodayPaymentList").innerHTML = "";
            } else {
                dojo.byId("noPaymentMessageBox").style.display = "none"; 
                dojo.forEach(resultData.paymentItems, function(paymentData) {
                    paymentDataList.newItem({
                        id: paymentData.id,
                        tockenNo:paymentData.tokenNo,
                        paymentDate: paymentData.date,
                        totalAmount: Math.round(paymentData.totalAmount * 100) / 100,
                        processingFee: paymentData.processingFee,
                        amount: paymentData.amount,
                        account: paymentData.account,
                        status: paymentData.status,
                        gateway: paymentData.gateway
                    });  
                });  
                dojo.byId("adminTodayPaymentList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: 'todayPaymentGrid',
                    "class" : "span12",
                    store: paymentDataList,
                    structure: paymentDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminTodayPaymentList");
                dataGrid.startup();
            }                          
        });
    });    
}
};
var PreMonthPaymentInfo = {
    _paymentCountRestStore:"",
    init : function() {
        this._paymentCountRestStore = new JsonRest({
        target: core.getContextPath()+"/api/payment/count"
    });
    },
    populateValues : function() {
        if (dijit.byId("todayPaymentGrid")) {
        dijit.byId("todayPaymentGrid").destroyRecursive();
    }
    var patmentData = {
        items: []
    };
    dojo.byId("adminPreMonthPaymentList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
    var paymentDataList = new ItemFileWriteStore({data: patmentData});
    var paymentDataLayout = [[
            {'field': 'id', 'hidden' : 'true'},
            {'name': translator.common.billing.grid.layout.tockenNo, 'field': 'tockenNo', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            }, 
            {'name': translator.common.account.name, 'field': 'account', 'width': '100px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            }, 
            {'name': translator.common.gateway, 'field': 'gateway', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},            
            {'name': translator.common.amount, 'field': 'amount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.grid.layout.processingFee, 'field': 'processingFee', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.grid.layout.totalAmount, 'field': 'totalAmount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {                                                                      
                    return  "<span class='orangeColor'>" + data + "</span>";
                }
            },
            {'name': translator.common.billing.grid.layout.paymentDate, 'field': 'paymentDate', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.status.name, 'field': 'status', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data){
                    if(data === "SUCCESS") {
                        return  "<span class='bold' style='color: #36bd55;'>" + data + "</span>";
                    } else if(data === "Pending") {
                        return  "<span class='bold' style='color: #49bcd0;'>" + data + "</span>";
                    } else if(data === "FAILED") {
                        return  "<span class='bold' style='color: #f2443b;'>" + data + "</span>";
                    } else {
                        return data;
                    }                        
                }
            }
        ]
    ];

    this._paymentCountRestStore.query({month: "Pre"}).then(function(data) {
        dojo.forEach(data, function(resultData) {
            dojo.byId("daily").innerHTML =  resultData.dailyPaymentAmount.toFixed(2);
            dojo.byId("preMonth").innerHTML =  resultData.preMonthPaymentAmount.toFixed(2);
            dojo.byId("monthly").innerHTML =  resultData.currentMonthPaymentAmount.toFixed(2);           

            dojo.byId("dailyDate").innerHTML = resultData.dailyDate;
            dojo.byId("monthlyDate").innerHTML = resultData.currMonth;
            dojo.byId("preMonthDate").innerHTML = resultData.preMonth;      
            
            dojo.byId("dailycurrency").innerHTML = resultData.currency;
            dojo.byId("monthlyCurrency").innerHTML = resultData.currency;
            dojo.byId("preMonthCurrency").innerHTML = resultData.currency;
            if(resultData.preMonthPaymentList.length == 0 || resultData.preMonthPaymentList == undefined || resultData.preMonthPaymentList == 'undefined' || resultData.preMonthPaymentList == "" || resultData.preMonthPaymentList == " ") { 
                dojo.byId("adminPreMonthPaymentList").innerHTML = "";
                dojo.byId("noprePaymentMessageBox").style.display = "block";    
            } else {
                dojo.byId("noprePaymentMessageBox").style.display = "none"; 
                
                dojo.forEach(resultData.preMonthPaymentList, function(paymentData) {
                    paymentDataList.newItem({
                        id: paymentData.id,
                        tockenNo:paymentData.tokenNo,
                        paymentDate: paymentData.date,
                        totalAmount: Math.round(paymentData.totalAmount * 100) / 100,
                        processingFee: paymentData.processingFee,
                        amount: paymentData.amount,
                        account: paymentData.account,
                        status: paymentData.status,
                        gateway: paymentData.gateway
                    });  
                });  
                dojo.byId("adminPreMonthPaymentList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    id: 'todayPaymentGrid',
                    "class" : "span12",
                    store: paymentDataList,
                    structure: paymentDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("adminPreMonthPaymentList");
                dataGrid.startup();
            }            
        });
    });    
}
};
var CurrentMonthPaymentInfo = {
   _paymentCountRestStore:"",
    init : function() {
        this._paymentCountRestStore = new JsonRest({
        target: core.getContextPath()+"/api/payment/count"
    });
    },
    populateValues : function() {
        if (dijit.byId("todayPaymentGrid")) {
        dijit.byId("todayPaymentGrid").destroyRecursive();
    }
    var patmentData = {
        items: []
    };
    dojo.byId("adminCurrentMonthPaymentList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>"+translator.common.loader.loading+"</p>";
    var paymentDataList = new ItemFileWriteStore({data: patmentData});
    var paymentDataLayout = [
        [
            {'field': 'id', 'hidden' : 'true'},
            {'name':  translator.common.billing.grid.layout.tockenNo, 'field': 'tockenNo', 'width': '200px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            },    
            {'name': translator.common.account.name, 'field': 'account', 'width': '100px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {                                                                      
                    return  "<span class='bold'>" + data + "</span>";
                }
            },    
            {'name': translator.common.gateway, 'field': 'gateway', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.amount, 'field': 'amount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true' },
            {'name': translator.common.billing.grid.layout.processingFee, 'field': 'processingFee', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.billing.grid.layout.totalAmount, 'field': 'totalAmount', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {                                                                      
                    return  "<span class='orangeColor'>" + data + "</span>";
                }
            },
            {'name': translator.common.billing.grid.layout.paymentDate, 'field': 'paymentDate', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
            {'name': translator.common.status.name, 'field': 'status', 'width': '100%', datatype: "string", autoComplete: true, 'formatter': function(data){
                       if(data === "SUCCESS") {
                           return  "<span class='bold' style='color: #36bd55;'>" + data + "</span>";
                       } else if(data === "Pending") {
                           return  "<span class='bold' style='color: #49bcd0;'>" + data + "</span>";
                       } else if(data === "FAILED") {
                           return  "<span class='bold' style='color: #f2443b;'>" + data + "</span>";
                       } else {
                           return data;
                       }
                        
            }}
        ]
    ];

    this._paymentCountRestStore.query({month: "Current"}).then(function(data) {
        dojo.forEach(data, function(resultData) {

            dojo.byId("daily").innerHTML =  resultData.dailyPaymentAmount.toFixed(2);
            dojo.byId("preMonth").innerHTML =  resultData.preMonthPaymentAmount.toFixed(2);
            dojo.byId("monthly").innerHTML =  resultData.currentMonthPaymentAmount.toFixed(2);           

            dojo.byId("dailyDate").innerHTML = resultData.dailyDate;
            dojo.byId("monthlyDate").innerHTML = resultData.currMonth;
            dojo.byId("preMonthDate").innerHTML = resultData.preMonth;    

            dojo.byId("dailycurrency").innerHTML = resultData.currency;
            dojo.byId("monthlyCurrency").innerHTML = resultData.currency;
            dojo.byId("preMonthCurrency").innerHTML = resultData.currency;
            if(resultData.currentMonthPaymentList.length == 0 || resultData.currentMonthPaymentList == undefined || resultData.currentMonthPaymentList == 'undefined' || resultData.currentMonthPaymentList == "" || resultData.currentMonthPaymentList == " ") {
                dojo.byId("adminCurrentMonthPaymentList").innerHTML = "";
                dojo.byId("noCurrentPaymentMessageBox").style.display = "block";   
            } else {               
                dojo.byId("noCurrentPaymentMessageBox").style.display = "none";
                dojo.forEach(resultData.currentMonthPaymentList, function(paymentData) {
                    paymentDataList.newItem({
                        id: paymentData.id,
                        tockenNo:paymentData.tokenNo,
                        paymentDate: paymentData.date,
                        totalAmount: Math.round(paymentData.totalAmount * 100) / 100,
                        processingFee: paymentData.processingFee,
                        amount: paymentData.amount,
                        account: paymentData.account,
                        status: paymentData.status,
                        gateway: paymentData.gateway
                    });  
                });    
                dojo.byId("adminCurrentMonthPaymentList").innerHTML = "";
                
                 var dataGrid = new EnhancedGrid({
                     id: 'todayPaymentGrid',
                     "class" : "span12",
                     store: paymentDataList,
                     structure: paymentDataLayout,
                     autoHeight: true,
                     plugins: core.getGridConfig().plugins
                });

                dataGrid.placeAt("adminCurrentMonthPaymentList");
                dataGrid.startup();
            }
                        
        });
    });
   
    }
};
window.DraftInvoiceInfo = DraftInvoiceInfo;   
window.FinalInvoiceInfo = FinalInvoiceInfo;
window.PaymentInfo = PaymentInfo;