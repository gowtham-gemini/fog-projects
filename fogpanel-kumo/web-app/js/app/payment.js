"use strict";
var payment = {
    _loader: null,
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
            return paymentBaseConfig.context;
    },
    'setConfig': function(config) {
        for (var param in config) {
            paymentBaseConfig[param] = config[param];

        }
    },
    require : function() {
        var baseURL = paymentBaseConfig.baseURL;
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
            "dojox/grid/EnhancedGrid",
            "dijit/form/FilteringSelect",
            "dojo/dom-construct",
            "dojo/query",
            "dojo/dom-class",
            "dijit/form/Button",
            "dojox/grid/EnhancedGrid",
            "dojox/grid/enhanced/plugins/Filter",
            "dojo/on",
            "dojox/validate/regexp",
            "dojox/widget/Toaster",
            "dijit/form/Form",    
            "dojox/validate/regexp",
            "dijit/form/ValidationTextBox",
            "dijit/form/CheckBox",
            "dijit/form/NumberSpinner",
            "dijit/Dialog",
            "FogPanel/Navigator"
        ], 
        function(dojo, i18n, translator, dom, ready, JsonRest, ItemFileWriteStore, registry, Select, EnhancedGrid,  FilteringSelect) {        
            window.translator = translator;  
            window.JsonRest = JsonRest;
            window.Select = Select;    
            window.EnhancedGrid = EnhancedGrid;    
            window.ItemFileWriteStore = ItemFileWriteStore;		  
            window.registry = registry;
            payment._loader = dom.byId("loader");
            payment.populateValues();
            payment.hideLoader();
        });    
    },  
    'ticketAdd': function() {          
        var supportDefaultReplyStore = new JsonRest({
            headers: { "X-FogPayment": "Payment" },
            target: payment.getContextPath()+"/api/support/ticket/add"
        });        
        var firstNode = "";
        var node = dojo.byId("addTicketForm");
        var widget = dijit.registry.findWidgets(node);        
        var status =  true;
        dojo.forEach(widget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
                status = false;
            }
        });
        if(status == true) {            
            if(dojo.byId("addTicketContent").value == "" || dojo.byId("addTicketContent").value == null || dojo.byId("addTicketContent").value == "null") {                
                registry.byId("paymentToaster").setContent(translator.common.message.enterContent, "error");
                registry.byId("paymentToaster").show();
            } else {
                dijit.byId('addTicketButton').set('style', {display: 'none'});
                dojo.byId("addTicketLoader").style.display = "inline";
                dijit.byId("cancelTicketButton").set("disabled", true);
                supportDefaultReplyStore.add({
                        department:  dijit.byId("supportDepWidget").getValue(),
                        subject:  dijit.byId("addTicketSubject").getValue(),
                        content:  dojo.byId("addTicketContent").value,
                        priority:  dijit.byId("ticketPriority").getValue(),
                        state: "OPEN"
                }).then(function(data) {
                    dojo.forEach(data, function(resultData) {
                        if(resultData == "OK") {
                            registry.byId("paymentToaster").setContent(translator.common.ticket.ticketAdded, "message");
                            registry.byId("paymentToaster").show();
                        } else {
                            registry.byId("paymentToaster").setContent(translator.common.ticket.addTicketError, "error");
                            registry.byId("paymentToaster").show();
                        }
                    });                
                    dijit.byId('addTicketButton').set('style', {display: 'inline'});
                    dojo.byId("addTicketLoader").style.display = "none";
                    dijit.byId("cancelTicketButton").set("disabled", false);
                });
            }                
        }                
    },
    'ticketCancel': function() {  
        dijit.byId("supportDialog").hide();
        dijit.byId("addTicketForm").reset();   
        dojo.byId("addTicketContent").value = "";
    },       
    'ticketPopulateValues': function() {          
        var supportListDepartmentStore = new JsonRest({
            target: payment.getContextPath()+"/api/support/department/list",
            headers: { "X-FogPayment": "Payment" }
        });
        
        var depOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var depList = new ItemFileWriteStore({data: depOptions});
        supportListDepartmentStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                depList.newItem({id: el.id, name: el.name});
            });
            dijit.byId("supportDialog").show();
        });   
            
        var depWidget = new Select({
            id: "supportDepWidget",
            name: "department",
            value: "option",        
            store: depList
        }, "supportDepList");  
        
    },
    'showTicketDialog' : function() {
         payment.ticketPopulateValues();
    },      
    updatePaymentGrid : function() {      
        var paymentStore = new JsonRest({
            target: payment.getContextPath()+"/api/payment/",
             headers: { "X-FogPayment": "Payment" }
        });

        if(dijit.byId("paymentGridWidget")) {
            dijit.byId("paymentGridWidget").destroyRecursive();
        }      
       
        var paymentGridData = {
            items: []
        };

        var paymentGridDataList = new ItemFileWriteStore({data: paymentGridData});

        paymentStore.query().then(function(data) {
             dojo.forEach(data, function(paymentData) {   
                paymentGridDataList.newItem({id: paymentData.id,
                tokenNo: paymentData.tokenNo, paymentDate: paymentData.date, totalAmount: paymentData.totalAmount, 
                processingFee: paymentData.processingFee, amount: paymentData.amount});

             });
        });
        var gridLayout = [[
                {'name': 'Id', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.billing.grid.layout.tokenNo, 'field': 'tokenNo', 'width': '400px'},
                {'name': translator.common.billing.grid.layout.paymentDate, 'field': 'paymentDate', 'width': '200px'},
                {'name': translator.common.billing.grid.layout.amount, 'field': 'amount', 'width': '150px'},
                {'name': translator.common.billing.grid.layout.processingFee, 'field': 'processingFee', 'width': '200px'},
                {'name': translator.common.billing.grid.layout.totalAmount, 'field': 'totalAmount', 'width': '100%'}
                
        ]];             
        var paymentGrid = new EnhancedGrid({
            id: 'paymentGridWidget',
            store: paymentGridDataList,
            structure: gridLayout,
            autoHeight: true
        });
        paymentGrid.placeAt("paymentListGrid");
        paymentGrid.startup();       
    },    
    populateValues : function() {
        payment.updatePaymentGrid();
        
        if(dijit.byId("expiryYear")) {
            dijit.byId("expiryYear").destroyRecursive();
        }
        
        var yearOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
                   
        var yearList = new ItemFileWriteStore({data: yearOptions});
        
        var currentTime = new Date();
        var j = currentTime.getFullYear() + 20;
        for(var i= currentTime.getFullYear(); i<=j; i++ ){
           yearList.newItem({id:i, name: i});
        }  
        var yearListWidget = new Select({
            id: "expiryYear",
            name: "expiryYear",    
            store: yearList
        });
        yearListWidget.placeAt("yearList");
        yearListWidget.startup();
        
        var accountStore = new JsonRest({
            target: payment.getContextPath()+"/api/account/currentAccount",
            headers: { "X-FogPayment": "Payment" }
        });
        
       accountStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("currentDue").innerHTML= Math.round(resultData.totalAmount*100)/100;  
                dojo.byId("credit").innerHTML= resultData.credit;
                dojo.byId("Payments").innerHTML= resultData.payments;
                dojo.byId("PaymentPeriod").innerHTML= resultData.paymentPeriod;
                
                dojo.byId("currentDueCurrency").innerHTML= resultData.currency;
                dojo.byId("creditCurrency").innerHTML= resultData.currency;
                dojo.byId("PaymentsCurrency").innerHTML= resultData.currency;
                if(resultData.invoice == "no") {
                    
                } else {
                    document.getElementById("currentInvoice").src = "pdf/currentUsage?invoiceId="+resultData.invoiceId;
                }
            });
        });
        
        setTimeout(function(){
            var accountVerifyRestStore = new JsonRest({
                target: payment.getContextPath()+"/api/account/cardVerified/",
                headers: { "X-FogPayment": "Payment" }
            });
            var useDefaultCard = dijit.byId("useDefaultCard");
            accountVerifyRestStore.query().then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if((resultData.cardVerified == "OK") && (resultData.defaultCardEnabled == "true")) {
                        dojo.byId("defaultCardDetails").style.display = "block";
                        dojo.byId("cardDetails").style.display = "none";
                    } else if((resultData.cardVerified == "OK") && (resultData.defaultCardEnabled == "false")) {
                        dojo.byId("cardDetails").style.display = "block";
                        dojo.byId("defaultCardDetails").style.display = "none";
                        useDefaultCard.setAttribute('checked',  false);
                    } else {
                        dojo.byId("defaultCardDetails").style.display = "none";
                        dojo.byId("cardDetails").style.display = "block"; 
                        useDefaultCard.setAttribute('checked',  false);
                    }

                });                
            });
        },500);
        var gatewayRestStore = new JsonRest({
            target: payment.getContextPath() + "/api/config/paymentGateways/",
            headers: { "X-FogPayment": "Payment" }
        });
        var radioButtonPane = new dijit.layout.ContentPane({
            splitter: true,
            region: "top",
            style: "background-color: white;width: 300px; height: 40px",
            content: "RadioButtons"
        });

        var myString = "";
        gatewayRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) {
                myString += "<input type='radio' checked='false' data-dojo-type='dijit.form.RadioButton' name='status' value='" + config.gatewayName + "' id='" + config.gatewayName + "' onclick='Gateway.loadGateway();'/>" + "<label for='" + config.gatewayName + "'>" + config.gatewayName + "</label>"
            });
            var radioOne = dojo.create("div", {
                innerHTML: myString
            });
            radioButtonPane.set('content', radioOne);
            radioButtonPane.placeAt("test");
        });
    },
    showSummary : function() {
         dijit.byId("summaryDialog").show();
    },       
    add : function() {        
       
        var paymentStore = new JsonRest({
            target: payment.getContextPath()+"/api/payment/",
            headers: { "X-FogPayment": "Payment" }
        });
        
        var cardType = dijit.byId("cardType");
        var paymentAmount = dijit.byId("paymentAmount");
        var useDefaultCard = dijit.byId("useDefaultCard");
        var cardNumber = dijit.byId("cardNumber");
        var expiryYear = dijit.byId("expiryYear");
        var expiryMonth = dijit.byId("expiryMonth");
        var cvvNumber = dijit.byId("cvvNumber");
        
        var pageNode1 = dojo.byId("payAmountDiv");
        var pageNode2 = dojo.byId("manualPaymentPage");

        var widgets;

        if (useDefaultCard.checked == true || useDefaultCard.checked == "true") {
            widgets = dijit.registry.findWidgets(pageNode1);
        } else {
            widgets = dijit.registry.findWidgets(pageNode2);
        }

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
             dijit.byId("paymentLoader").show();     
                
        paymentStore.add({
            amount: paymentAmount.value,  
            useDefaultCard: useDefaultCard.checked,  
            cardNumber: cardNumber.value,  
            expiryMonth: expiryMonth.value,  
            expiryYear: expiryYear.value,  
            cvv: cvvNumber.value,  
            cardType: cardType.value
            
        }).then(function(resultData) {
            if(resultData == "OK") {
                dijit.byId("paymentLoader").hide();
                registry.byId('paymentToaster').setContent(translator.common.billing.paymentSuccess + "!", 'message');
                registry.byId('paymentToaster').show();
                document.location.reload();
                dijit.byId("manualPaymentForm").reset();
                
            } else {
                dijit.byId("paymentLoader").hide();
                registry.byId('paymentToaster').setContent(translator.common.billing.paymentFailed+ "!", 'error');
                registry.byId('paymentToaster').show();
            }            
        });
      }
    },
    cancel: function() { 
//       dijit.byId("manualPaymentForm").reset(); 
      // dijit.byId("paymentDialog").hide(); 
        dojo.byId("makePaymentDiv").style.display = "none";  
        dojo.byId("paypalDiv").style.display = "none";
        dojo.byId("ccAvenueDiv").style.display = "none";
        dijit.byId("makePaymentBtn").setAttribute('style', 'display: inline-block');
    },
    showPaymentForm: function() { 
        dijit.byId("manualPaymentForm").reset(); 
        var accshowPaymentFormountVerifyRestStore = new JsonRest({
                target: payment.getContextPath()+"/api/account/cardVerified/",
                headers: { "X-FogPayment": "Payment" }
            });
            var useDefaultCard = dijit.byId("useDefaultCard");
            accountVerifyRestStore.query().then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if((resultData.cardVerified == "OK") && (resultData.defaultCardEnabled == "true")) {
                        dojo.byId("defaultCardDetails").style.display = "block";
                        dojo.byId("cardDetails").style.display = "none";
                    } else if((resultData.cardVerified == "OK") && (resultData.defaultCardEnabled == "false")) {
                        dojo.byId("cardDetails").style.display = "block";
                        dojo.byId("defaultCardDetails").style.display = "none";
                        useDefaultCard.setAttribute('checked',  false);
                    } else {
                        dojo.byId("defaultCardDetails").style.display = "none";
                        dojo.byId("cardDetails").style.display = "block"; 
                        useDefaultCard.setAttribute('checked',  false);
                    }

                });  
                dijit.byId("paymentDialog").show(); 
            });                               
    },
    showMakePaymentForm: function() {

        
       
        
        if (dijit.byId("makePaymentDiv")) {
            dijit.byId("makePaymentDiv").destroyRecursive();
        }
        if (dijit.byId("paymentGateways")) {
            dijit.byId("paymentGateways").destroyRecursive();
        }
        if (dijit.byId("test")) {
            dijit.byId("test").destroyRecursive();
        }
       
        dojo.byId("makePaymentDiv").style.display = "block";        
        
        if(dijit.byId("makePaymentRadioForm")){
            dijit.byId("makePaymentRadioForm").reset();
        }
        if(dijit.byId("manualPaymentForm")){
            dijit.byId("manualPaymentForm").reset();
            
        }
        dojo.byId("paypalDiv").style.display = "none";
        dojo.byId("ccAvenueDiv").style.display = "none";
        dijit.byId("makePaymentBtn").setAttribute('style', 'display: none');
        var bankWidget = dijit.byId("bankPaymentWidget");
        if (bankWidget) {
            dijit.byId("bankPaymentWidget").destroyRecursive();
        }
        var noBankOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var noBankOptionsList = new ItemFileWriteStore({data: noBankOptions});
        //noBankOptionsList.newItem({id: "option", name: translator.ccAvenue.none});
        var bankWidget = new Select({
            id: "bankPaymentWidget",
            name: "bankOption",
            sortByLabel: false,
            store: noBankOptionsList,
            value: "option"
        }).placeAt("bankDataList");        
        
    },
    enableCard: function(currentElement) {         
        if(currentElement.checked == "true" || currentElement.checked== true) {
            dojo.byId("cardDetails").style.display = "none";
        } else {
            dojo.byId("cardDetails").style.display = "block";
        }    
    }
};
var paymentBaseConfig = {
    'baseURL': "/FogPanel/",
    'homepage': "#/",
    'context': "FogPanel"
};

var Gateway = {
    loadGateway: function(){
        
        var formElements = dojo.query("#paymentGateways input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var paymentGWPaypal = dijit.byId(checkedRadioId).value;
        
        if (paymentGWPaypal == "PayPal") {
            dojo.byId("paypalDiv").style.display = "block";
            dojo.byId("ccAvenueDiv").style.display = "none";
        } else if (paymentGWPaypal == "CCAvenue") {
            
            dojo.byId("ccAvenueDiv").style.display = "block";
            dojo.byId("paypalDiv").style.display = "none";
        }
    }
};

var CCAvenueGateway = {
    _cardOption: "",
    loadBankInfo: function() {

        var bankWidget = dijit.byId("bankPaymentWidget");
        if (bankWidget) {
            dijit.byId("bankPaymentWidget").destroyRecursive();
        }
        //var cardOptionSelected = dojo.byId
        var bankOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var bankList = new ItemFileWriteStore({data: bankOptions});

        var formElements = dojo.query("#ccAvenuePaymentOption input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var cardOptionSelected = dijit.byId(checkedRadioId).value;
        this._cardOption = cardOptionSelected;

        if (cardOptionSelected == "NonMoto") {
            bankList.newItem({id: "ANDDB_N", name: translator.ccAvenue.andhraBankDebit});
            bankList.newItem({id: "CANVIS_N", name: translator.ccAvenue.canaraBankDebit});
            bankList.newItem({id: "CBIDEB_N", name: translator.ccAvenue.cityBankDebit});
            bankList.newItem({id: "IOBDB_N", name: translator.ccAvenue.iobDebit});
            bankList.newItem({id: "PNBVIS_N", name: translator.ccAvenue.pnbDebitVisa});
            bankList.newItem({id: "PNBM_N", name: translator.ccAvenue.pnbDebitMaster});
            bankList.newItem({id: "UNIDB_N", name: translator.ccAvenue.ubiDebit});
            bankList.newItem({id: "KMBR_Y", name: translator.ccAvenue.rupayDebit});
            bankList.newItem({id: "SBMDB_Y", name: translator.ccAvenue.sbiDebit});
        } else if (cardOptionSelected == "netBanking") {
            bankList.newItem({id: "AND_N", name: translator.ccAvenue.andhraNB});
            bankList.newItem({id: "UTI_N", name: translator.ccAvenue.axisNB});
            bankList.newItem({id: "BBK_N", name: translator.ccAvenue.bobahrainNB});
            bankList.newItem({id: "BOBCO_N", name: translator.ccAvenue.bobCorpNB});
            bankList.newItem({id: "BOB_N", name: translator.ccAvenue.bobRetailAcNB});
            bankList.newItem({id: "BOI_N", name: translator.ccAvenue.boiNB});
            bankList.newItem({id: "BOM_N", name: translator.ccAvenue.bomNB});
            bankList.newItem({id: "CAN_N", name: translator.ccAvenue.canNB});
            bankList.newItem({id: "CSB_N", name: translator.ccAvenue.catSyrNB});
            bankList.newItem({id: "CEN_N", name: translator.ccAvenue.cenBINB});
            bankList.newItem({id: "CBIBAN_N", name: translator.ccAvenue.cityBankNB});
            bankList.newItem({id: "CITIUB_N", name: translator.ccAvenue.cityUnionNB});
            bankList.newItem({id: "DBS_N", name: translator.ccAvenue.dbsBankNB});
            bankList.newItem({id: "DCB_N", name: translator.ccAvenue.dcbNB});
            bankList.newItem({id: "DEUNB_N", name: translator.ccAvenue.deutscheNB});
            bankList.newItem({id: "DLB_N", name: translator.ccAvenue.dhanaNB});
            bankList.newItem({id: "FDEB_N", name: translator.ccAvenue.federalNB});
            bankList.newItem({id: "HDEB_N", name: translator.ccAvenue.hdfcNB});
            bankList.newItem({id: "IDBI_N", name: translator.ccAvenue.idbiNB});
            bankList.newItem({id: "INB_N", name: translator.ccAvenue.indNB});
            bankList.newItem({id: "IOB_N", name: translator.ccAvenue.iobNB});
            bankList.newItem({id: "NIIB_N", name: translator.ccAvenue.indusNB});
            bankList.newItem({id: "ING_N", name: translator.ccAvenue.ingNB});
            bankList.newItem({id: "JKB_N", name: translator.ccAvenue.jkbNB});
            bankList.newItem({id: "KTKB_N", name: translator.ccAvenue.karnatakaNB});
            bankList.newItem({id: "KVB_N", name: translator.ccAvenue.kvbNB});
            bankList.newItem({id: "NKMB_N", name: translator.ccAvenue.kotakNB});
            bankList.newItem({id: "LVB_N", name: translator.ccAvenue.lakshmiVilasNB});
            bankList.newItem({id: "OBPRF_N", name: translator.ccAvenue.orientalNB});
            bankList.newItem({id: "PNBCO_N", name: translator.ccAvenue.pnbCorpNB});
            bankList.newItem({id: "NPNB_N", name: translator.ccAvenue.pnbRetailNB});
            bankList.newItem({id: "RBS_N", name: translator.ccAvenue.rbsNB});
            bankList.newItem({id: "SIB_N", name: translator.ccAvenue.sibNB});
            bankList.newItem({id: "SCB_N", name: translator.ccAvenue.scbNB});
            bankList.newItem({id: "SBJ_N", name: translator.ccAvenue.sbjNB});
            bankList.newItem({id: "SBH_N", name: translator.ccAvenue.sbhNB});
            bankList.newItem({id: "SBI_N", name: translator.ccAvenue.sbiNB});
            bankList.newItem({id: "SBM_N", name: translator.ccAvenue.sbmNB});
            bankList.newItem({id: "SBP_N", name: translator.ccAvenue.sbpNB});
            bankList.newItem({id: "SBT_N", name: translator.ccAvenue.sbtNB});
            bankList.newItem({id: "SYNBK_N", name: translator.ccAvenue.syndNB});
            bankList.newItem({id: "TNMB_N", name: translator.ccAvenue.tnmNB});
            bankList.newItem({id: "UNI_N", name: translator.ccAvenue.ubiNB});
            bankList.newItem({id: "UBI_N", name: translator.ccAvenue.unitedNB});
        } else if (cardOptionSelected == "CCRD") {
            bankList.newItem({id: "CCI_N", name: translator.ccAvenue.iCash});
            bankList.newItem({id: "ITZ_N", name: translator.ccAvenue.itzCash});
            bankList.newItem({id: "OXIG_N", name: translator.ccAvenue.oxiCash});
            bankList.newItem({id: "PCC_N", name: translator.ccAvenue.payCash});
        }
        var bankWidget = new Select({
            id: "bankPaymentWidget",
            name: "bankOption",
            sortByLabel: false,
            store: bankList,
            maxHeight: 100
        }).placeAt("bankDataList");
    },
    addPayment: function() {
        var formElements = dojo.query("#paymentGateways input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);
        var paymentGW = dijit.byId(checkedRadioId).value;
        if (this._cardOption == 'undefined' || this._cardOption == undefined || this._cardOption == '') {
            dojo.byId("cardExceptionMsg").style.display = "block";
        } else {
            dojo.byId("cardExceptionMsg").style.display = "none";
            var amount = dijit.byId("paymentAmountCCA");
            var bankName = dijit.byId("bankPaymentWidget").value;
            var pageNode2 = dojo.byId("ccAvenuePage");

            var widgets = dijit.registry.findWidgets(pageNode2);
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

            var _paymentStore = new JsonRest({
                target: payment.getContextPath() + "/api/payment/ccAvenue/",
                headers: { "X-FogPayment": "Payment" }
            });


            if (firstNode) {
                firstNode.focus();
            } else {
                _paymentStore.add({
                    amount: amount.value,
                    cardOption: this._cardOption,
                    bankName: bankName,
                    paymentGWName: paymentGW
                }).then(function(resultData) {
                    if (resultData == "OK") {
                        registry.byId('userToaster').setContent(translator.common.billing.paymentSuccess, 'message');
                        registry.byId('userToaster').show();
                    } else {
                        registry.byId('userToaster').setContent(translator.common.billing.paymentFailed, 'error');
                        registry.byId('userToaster').show();
                    }
                });
            }
        }
    },
    cancelPayment: function() {
      
        dojo.byId("makePaymentDiv").style.display = "none";  
        dojo.byId("paypalDiv").style.display = "none";
        dojo.byId("ccAvenueDiv").style.display = "none";
        dijit.byId("makePaymentBtn").setAttribute('style', 'display: inline-block');
        var bankWidget = dijit.byId("bankPaymentWidget");
        if (bankWidget) {
            dijit.byId("bankPaymentWidget").destroyRecursive();
        }
        var noBankOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var noBankOptionsList = new ItemFileWriteStore({data: noBankOptions});
        //noBankOptionsList.newItem({id: "option", name: translator.ccAvenue.none});
        var bankWidget = new Select({
            id: "bankPaymentWidget",
            name: "bankOption",
            sortByLabel: false,
            store: noBankOptionsList,
            value: "option"
        }).placeAt("bankDataList");
    }
}