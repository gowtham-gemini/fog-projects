window.isAdvanceZoneAviliable = false;
window.isBasicZoneAvailable = false;
var App = {
    _loader: null,     
    hideLoader: function() {        
        this._loader.style.display = "none";
    }
};

var UpdateTimer = {
    sync: function() {        
        var billingAlerts = new JsonRest({
            target: core.getContextPath()+"/api/notification/billingAlerts"
        });                
        var count = 0;
        var cloudCount = 0;
        var notificationData = [];  
        var cloudNotificationData = [];  
        billingAlerts.query().then(function(data) {
            dojo.forEach(data, function(notificationDataItem) {
                dojo.forEach(notificationDataItem.nonViewed, function(nonViewedData) {
                    if(count <= 6) {
                        notificationData[count] = {link:nonViewedData.link,icon:nonViewedData.icon ,value:nonViewedData.description};
                    }
                    count ++ ;
                }); 
                connect.publish("/FogPanel/notifications", [notificationData]);
                dojo.byId("notificationCount").innerHTML = notificationDataItem.count;
            });
            dojo.forEach(data, function(notificationDataItem) {
                dojo.forEach(notificationDataItem.notification, function(notification) {
                    if(cloudCount <= 6) {
                        cloudNotificationData[cloudCount] = {link:notification.link,icon:notification.icon ,value:notification.description};
                    }                    
                    cloudCount ++ ;
                }); 
                connect.publish("/FogPanel/cloud/notifications", [cloudNotificationData]);
            });            
        });        
        var ticketCount = 0;
        var ticNotificationData = [];  
        var ticketNotification= new JsonRest({
            target: core.getContextPath()+"/api/support/ticket/notification"
        });                
        ticketNotification.query().then(function(data) {
            dojo.forEach(data, function(notificationDataItem) {
                if(ticketCount <= 5) {
                    ticNotificationData[ticketCount] = {link:notificationDataItem.link, icon:notificationDataItem.icon , value:"TicketId: "+notificationDataItem.ticId +" - "+ notificationDataItem.subject};
                }
                ticketCount ++;
            });
            connect.publish("/FogPanel/ticket/notifications", [ticNotificationData]);
            dojo.byId("ticketCount").innerHTML = ticketCount;
        });                
    }
};

var Login = {
    init: function() {
        var accountListStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"
        });
        var password;
        var userName;
        var singleSignOnUrl;        
        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                //password = el.cloudPassword;
                userName = el.userName;
//                singleSignOnUrl = el.singleSignOnUrl+"/api";
            });
//            dojo.xhrPost({                
//                url: singleSignOnUrl,
//                content: {
//                    command:"login",
//                    domain:"/",
//                    password:password,
//                    response:"json",
//                    username:userName
//                }
//            });            
        });        
    }
};

var Dashboard = {
    _invoiceStore: "",
    _accountListStore: "",
//    _instanceCountRestStore: "",
    _volumeCountRestStore: "",
    _snapCountRestStore: "",
    _setupVerificationRestStore: "",
    init: function() {     
        this._invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/chart/"
        });
        
        this._accountListStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"
        });
        
        this._setupVerificationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/user/setupVerification"
        });
        
//        this._instanceCountRestStore = new JsonRest({
//                target: core.getContextPath()+"/api/virtualMachine/count"
//        });
    },
    closeDialogue : function() {        
        dijit.popup.close(dijit.byId("settingsDropdown"));
    },
    loadZoneData : function() {
                        
//        dojo.byId("dashboardDivLoader").style.display = "none";
        dojo.byId("dashboardDiv").style.display = "block";
    },
    populateValues: function() { 
                 
        var password;
        var userName;
        var singleSignOnUrl;
        this._accountListStore.query().then(function(data) {
            dojo.forEach(data, function(el) { 
                dojo.byId("usageCost").innerHTML = LocaleNumber.format(el.daily.toFixed(2));
                dojo.byId("usageCurrency").innerHTML = el.currency ;
                
                dojo.byId("usagePeriod").innerHTML = el.dailyDate;
                dojo.byId("dueCurrency").innerHTML = el.currency;
                dojo.byId("currentDue").innerHTML =  LocaleNumber.format(el.currentDue.toFixed(2));
                
                dojo.byId("Payments").innerHTML = LocaleNumber.format(el.payments.toFixed(2));
                dojo.byId("paymentCurrency").innerHTML = el.currency;
                
                dojo.byId("PaymentPeriod").innerHTML = el.paymentPeriod;
                dojo.byId("currentDuePeriod").innerHTML = el.paymentPeriod;
                if(el.cloudHealth == "true") {
                    dojo.byId("health").style.background = "#DC0800";
                } else if(el.cloudHealth =="no vm") {
                    dojo.byId("health").style.display = "none";
                }
                if(el.accountType == "TRIAL") {
                    dojo.byId("creditLimitDiv").style.display = "block";
                    dojo.byId("creditLimitSpan").innerHTML = el.creditLimit;
                }                                                                
                
//                password = el.cloudPassword;
//                userName = el.userName;
//                singleSignOnUrl = el.singleSignOnUrl+"/api";                
//                if(el.invoice == "no") {
//                    dojo.byId("chartDivMaster").style.display = "none";
//                    dojo.byId("noDataDiv").style.display = "block";
//                }                 
            });            
//            dojo.byId("dashboardDivLoader").style.display = "none";
//            dojo.byId("dashboardDiv").style.display = "block";
            
//            Dashboard.loadZoneData();
            
//            dojo.xhrPost({
//                // The URL of the request
//                url: singleSignOnUrl,
//                content: {
//                    command:"login",
//                    domain:"/",
//                    password:password,
//                    response:"json",
//                    username:userName
//                }
//            });            
        });        
        
//        this._instanceCountRestStore.query().then(function(data) {
//            dojo.forEach(data, function(el) {                     
//                dojo.byId("vmRunningCount").innerHTML = el.runningVms;
//                dojo.byId("vmStopCount").innerHTML = el.stoppedVms;  
//                dojo.byId("diskCount").innerHTML = el.totalStorage;  
//                dojo.byId("snapCount").innerHTML = el.totalSnapshots;
//            });
//        });        
//        
        var legend = dijit.byId("legend");
        if(legend) {
            legend.destroyRecursive();
        }                        
        
        var paymentChartXAxisData = [];
        var invoiceChartXAxisData = [];
        var paymentChartData = [];
        var invoiceChartData = [];
        var creditLimitChartData = [];
        var customItemChartData = [];
        var recurringItemChartData = [];
        
        var paymentChartData2 = [];
        var customItemChartData2 = [];
        var recurringItemChartData2 = [];        
        
        var i = 1;
        this._invoiceStore.query().then(function(data) {
            dojo.forEach(data, function(invoiceData) {
                invoiceChartXAxisData.push({value: i, text: invoiceData.month}); 
                invoiceChartData.push(invoiceData.totalAmount);
                creditLimitChartData.push(invoiceData.creditLimit);
                customItemChartData.push(invoiceData.customItemTotal);
                recurringItemChartData.push(invoiceData.recurringItemTotal); 
                customItemChartData2.push(invoiceData.customItemTotal);
                recurringItemChartData2.push(invoiceData.recurringItemTotal);                                                
                i++;
            });            
            Dashboard.loadChart(invoiceChartXAxisData,  invoiceChartData, creditLimitChartData, customItemChartData, recurringItemChartData, recurringItemChartData2, customItemChartData2);                
        });        
        var paymentStore = new JsonRest({
            target: core.getContextPath()+"/api/payment/chart/month"
        });
        var j = 1;
        paymentStore.query().then(function(data) {
            dojo.forEach(data, function(payData) {
                paymentChartXAxisData.push({value: j, text: payData.month}); 
                paymentChartData.push(payData.payment);
                paymentChartData2.push(payData.payment);
                j++;
            });            
            Dashboard.loadPaymentChart(paymentChartXAxisData, paymentChartData, paymentChartData2);                
        });  
        
        this._setupVerificationRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {
                
                if (resultData.openstackSetup === "false") {
                    dojo.byId("openstackSetupCheckListDiv").style.display = "block";
                } else {
                    dojo.byId("openstackSetupCheckListDiv").style.display = "none";
                }
                
                if (resultData.isNetworkEmpty === "false") {
                    dojo.byId("networkEmptyAlert").style.display = "block";
                } else {
                    dojo.byId("networkEmptyAlert").style.display = "none";
                }
                
                if (resultData.isSshKeyEmpty === "false") {
                    dojo.byId("sshKeyEmptyAlert").style.display = "block";
                } else {
                    dojo.byId("sshKeyEmptyAlert").style.display = "none";
                }
                
                if (resultData.isSecurityGroupEmpty === "false") {
                    dojo.byId("securityGroupEmptyAlert").style.display = "block";
                } else {
                    dojo.byId("securityGroupEmptyAlert").style.display = "none";
                }
            }); 
        });
    },    
    loadPaymentChart: function(paymentChartXAxisData, paymentChartData, paymentChartData2) {                
        dojo.byId("paymentChartNoData").style.display = "none";
        dojo.byId("paymentChartDiv").style.display = "block";
        
        // Create the chart within it's "holding" node
        var paymentChart = new dojox.charting.Chart("paymentSummaryChart");
        // Set the theme
        paymentChart.setTheme(theme);

        // Add the only/default plot
        paymentChart.addPlot("default", {
            type: ColumnsPlot,
            markers: true
        });

        // Add axes
        paymentChart.addAxis("x", {labels: paymentChartXAxisData});
        paymentChart.addAxis("y", { vertical: true, fixLower: "major", fixUpper: "major" , min:20});

        // Add the series of data
        paymentChart.addSeries("Monthly Sales", paymentChartData);

        // Highlight!
        new Highlight(paymentChart,"default");

        //ToolTip
        new Tooltip(paymentChart,"default");

        // Render the chart!
        paymentChart.render();                 
    },        
    loadChart: function(invoiceChartXAxisData,  invoiceChartData, creditLimitChartData, customItemChartData, recurringItemChartData,  recurringItemChartData2, customItemChartData2) {                        
        var rdata1 = recurringItemChartData2.sort();
        var rdata2 = rdata1.reverse();
        
        var cdata1 = customItemChartData2.sort();
        var cdata2 = cdata1.reverse();       
        
        if(rdata2[0] == 0 && cdata2[0] == 0) {
            dojo.byId("recItemChartDiv").style.display = "block";
            dojo.byId("recItemChartDiv").style.display = "none";
        } else {
            dojo.byId("recItemChartDiv").style.display = "none";
            dojo.byId("recItemChartDiv").style.display = "block";
        }
        
        // Create the chart within it's "holding" node
        var invoiceChart = new dojox.charting.Chart("invoiceSummaryChart");
        // Set the theme
        invoiceChart.setTheme(theme); 

        // Add the only/default plot
        invoiceChart.addPlot("default", {
            type: ColumnsPlot,
            markers: true,
            gap: 5
        });

        invoiceChart.addPlot("verticalLine", {
            type: Lines,
            markers: false
        });

        invoiceChart.addSeries("verticalLine", creditLimitChartData, {plot: "verticalLine", stroke: "red"});
        invoiceChart.movePlotToFront("verticalLine");
        // Add axes
        invoiceChart.addAxis("x", {labels: invoiceChartXAxisData});
        invoiceChart.addAxis("y", {vertical: true, fixLower: "major", fixUpper: "major" , min:0});

        // Add the series of data
        invoiceChart.addSeries("Monthly Sales", invoiceChartData);

        // Highlight!
        new Highlight(invoiceChart,"default");

        //ToolTip
        new Tooltip(invoiceChart,"default");

        // Render the chart!
        invoiceChart.render();  
        
        // Create the chart within it's "holding" node
        var customItemChart = new dojox.charting.Chart("customItemChart");
        // Set the theme
        customItemChart.setTheme(theme);

        // Add the only/default plot
        customItemChart.addPlot("default", {
            type: ClusteredColumns,
            markers: true,
            gap: 2
        });

        // Add axes
        customItemChart.addAxis("x", {labels: invoiceChartXAxisData});
        customItemChart.addAxis("y", { vertical: true, fixLower: "major", fixUpper: "major" , min:0});

        // Add the series of data
        customItemChart.addSeries(translator.common.billing.customItem ,customItemChartData);
        customItemChart.addSeries(translator.common.billing.recurringItem ,recurringItemChartData);

        // Highlight!
        new Highlight(customItemChart,"default");

        //ToolTip
        new Tooltip(customItemChart,'default');                         

        // Render the chart!
        customItemChart.render(); 

        // Create the legend
        new Legend({ chart: customItemChart }, "legend");

        
        var supportStore = new JsonRest({
            target: core.getContextPath()+"/api/support/graph"
        });
               
        var supportChartXAxisData = [];
        var supportChartData = [];
        
        supportChartXAxisData.push({value: 1, text: translator.common.open}); 
        supportChartXAxisData.push({value: 2, text: translator.common.message.inProgress}); 
        supportChartXAxisData.push({value: 3, text: translator.common.onHold}); 
        supportChartXAxisData.push({value: 4, text: translator.common.closed}); 
                
        supportStore.query().then(function(data) {
            dojo.forEach(data, function(payData) {                
                if(payData.open == 0 && payData.inProgress == 0 && payData.onHold == 0 && payData.close == 0) {
                    dojo.byId("supportChartDiv").style.display = "none";
                    dojo.byId("suportChartNoData").style.display = "block";      
                } else {
                    dojo.byId("supportChartDiv").style.display = "block";
                    dojo.byId("suportChartNoData").style.display = "none";      
                    supportChartData.push(payData.open);
                    supportChartData.push(payData.inProgress);
                    supportChartData.push(payData.onHold);
                    supportChartData.push(payData.close); 
                }                                
            });             
            // Create the chart within it's "holding" node
            var supportChart = new dojox.charting.Chart("supportChart");
            // Set the theme
            supportChart.setTheme(theme);

            // Add the only/default plot
            supportChart.addPlot("default", {
                type: ColumnsPlot,
                markers: true,
                gap: 5
            });

            // Add axes
            supportChart.addAxis("x", {labels: supportChartXAxisData});
            supportChart.addAxis("y", { vertical: true, fixLower: "major", fixUpper: "major" , min:0});

            // Add the series of data
            supportChart.addSeries("Ticket Count", supportChartData);

            // Highlight!
            new Highlight(supportChart,"default");

            //ToolTip
            new Tooltip(supportChart,"default");

            // Render the chart!
            supportChart.render(); 
        });         
        dojo.byId("chartLodDiv").style.display = "none";
        dojo.byId("chartDiv").style.display = "block";            
    }
};                    

var UserMenuConfig = {   
    init : function() {
        var node = dojo.query("#accountDetail ul li");
        dojo.forEach(node, function(listNode) { 
            listNode.style.color = "red";
        });
    }
};

var ProfileDetail = {
    _currentAccRestStore: "",
    _countryStore:"",
    _stateStore:"", 
    init : function() {        
        this._currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"
        });
        
        this._countryStore = new JsonRest({
            target: core.getContextPath()+"/api/country" 
        });
        this._stateStore = new JsonRest({
            target: core.getContextPath()+"/api/state/"
        }); 
    },
    populateValues : function() {
        this._currentAccountRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("profileCurrentId").value = el.id;             
            });
        });  
    },    
    resetNewPassword : function() {
        var currentId = dojo.byId("profileCurrentId").value;
        var passwordRestStore = new JsonRest({
            target: core.getContextPath()+"/api/user/resetPassword/"
        });
        
        var pageNode = dojo.byId("profilePage");        
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });      
        
        var newPassword = dijit.byId("newPassword");
        var oldPassword = dijit.byId("oldPassword");
        var comPassword = dijit.byId("confirmPassword");
        if(state == true) {
            dijit.byId("resetBtn").setAttribute('style', 'display: none');
            dojo.byId("resetBtnLoader").style.display = "block";
            passwordRestStore.put({
                id: currentId,
                newPassword: newPassword.value,
                oldPassword: oldPassword.value,
                comPassword: comPassword.value
            }).then(function(result) {              
                dijit.byId("resetBtn").setAttribute('style', 'display: block');
                dojo.byId("resetBtnLoader").style.display = "none";
                if(result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.account.passworResetSuccess,"message");
                    registry.byId("userToaster").show();
                    dijit.byId("oldPassword").reset();
                    dijit.byId("newPassword").reset();
                    dijit.byId("confirmPassword").reset();                
                } else if(result == "OLD PASSWORD DONOT MATCH") {
                    registry.byId("userToaster").setContent(translator.common.account.oldPasswordNotMatch,"error");
                    registry.byId("userToaster").show();
                } else {
                    registry.byId("userToaster").setContent(translator.common.account.resetPasswordError,"error");
                    registry.byId("userToaster").show();
                }
            });
        }        
    },
    confirmPass: function() {
        var pass =  dijit.byId("newPassword").value;
        var confPass = dijit.byId("confirmPassword").value;
        if(pass != confPass) {
            dijit.byId("confirmPassword").validator = function() {
                dijit.byId("confirmPassword").set("invalidMessage", translator.common.account.passwordNotMatch)
                return false;
            }      
        } else if(pass == confPass) {
            dijit.byId("confirmPassword").validator = function() {
                return true;
            }    
        }
    },
    checkPasswordStrength :  function(password) {
        var pwd =  password.getValue();         
        password.validator =   function() {
            if(pwd.length == 0) { 
                password.set("invalidMessage", translator.common.account.passwordShouldEmpty);
                return false;
            } else if(pwd.length < 8) {
                password.set("invalidMessage", translator.common.account.passwordLengthConstrain)
                return false;
            } else if(pwd.length > 15) {
                password.set("invalidMessage", translator.common.account.passwordLengthConstrain)  
                return false;
            } else {
                return true;
            }
        }      
        dijit.byId("confirmPassword").reset();
    }          
}

var AccountInfo = {
    _currentAccountRestStore: "",
    _userRestStore: "",
    _currentUser : "",
    init: function() {
        this._currentAccountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"
        });
        
        this._userRestStore = new JsonRest({
            target: core.getContextPath()+"/api/user/"
        });
    },
    populateValues: function() {
        this._currentAccountRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {              
            });
        }); 
    }   
};

var UserProfile = {
    _currentUserRestStore: "",
    _userRestStore: "",
    _currentUser : "",
    init: function() {
        this._currentUserRestStore = new JsonRest({
            target: core.getContextPath()+"/api/user/currentUser/"
        });
        
        this._userRestStore = new JsonRest({
            target: core.getContextPath()+"/api/user/"
        });
    },
    populateValues: function() {
        this._currentUserRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dojo.byId("userProfileName").innerHTML = el.accountName;
                if(el.apiKey == null || el.apiKey == " ") {
                    dojo.byId("userProfileApiKey").innerHTML = "null";
                } else {
                    dojo.byId("userProfileApiKey").innerHTML = el.apiKey;
                }                
                
                if(el.secretKey == null || el.secretKey == "") {
                    dojo.byId("userProfileSecretKey").innerHTML = "null";
                } else {
                    dojo.byId("userProfileSecretKey").innerHTML = el.content.secretKey;
                }
                window.currentId = el.id;
            });
        }); 
    },
    gotoResetPage: function() {
        dojo.byId("userProfile").style.display =  "none";
        dojo.byId("userPasswordField").style.display = "block";
    },
    resetPassword: function() {
        var password = dijit.byId("passwordField").value;
        var userRestPasswordRestStore = new JsonRest({
            target: core.getContextPath()+"/api/user/resetPassword/"
        });
        userRestPasswordRestStore.put({
            id: currentId,
            password: password
        });
    }
};

var AccountProfile = {
    _currentAccountRestStore : "",
    _accountRestStore:"",
    name:"",
    id:"",
    init: function() {
        this._currentAccountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"
        });
        
        this._accountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
    },
    populateValues: function() {
        this._currentAccountRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                dijit.byId("accountUserName").setValue(el.fullName);
                dijit.byId("accountEmail").setValue(el.email);
                dijit.byId("accountStreetAddress").setValue(el.streetAddress);
                dijit.byId("accountCity").setValue(el.city);
                dijit.byId("accountState").setValue(el.state);
                dijit.byId("accountCountry").setValue(el.country);
                dijit.byId("accountZip").setValue(el.zip);
                window.id = el.id;   
            });
        });
    },
    update: function() {
        var fullName = dijit.byId("accountUserName");
        var email = dijit.byId("accountEmail");
        var street = dijit.byId("accountStreetAddress");
        var city = dijit.byId("accountCity");
        var state = dijit.byId("accountState");
        var country = dijit.byId("accountCountry");
        var zip = dijit.byId("accountZip");

        this._accountRestStore.put({
            id: id ,
            fullName: fullName.value,
            email: email.value,
            street: dojox.html.entities.encode(street.value),
            city: city.value,
            state: state.value,
            country: country.value,
            zip: zip.value             
        });
    }
};          

var UserStorage = {
    _zoneRestStore:"",
    _zoneWidget:"",
    _diskOfferWidget:"",
    _diskOfferRestStore:"",
    _volumeRestStore:"",
    _virtualMachineRestStore:"",
    _virtualMachineWidget:"",
    storageWidget:"", 
    init: function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
         
        this._diskOfferRestStore = new JsonRest({
            target: core.getContextPath()+"/api/diskOffer/"
        });                                                

        this._volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        });     

        this._virtualMachineRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/"
        });       
    },
    add: function() {
        var storageName = dijit.byId("storageName").value;
        this._volumeRestStore.add({
            name: storageName,
            zoneReferenceId: this._zoneWidget.value,
            diskOfferingReferenceId: this._diskOfferWidget.value            
        }).then(function(data) {
            alert(data)           
        });
    },
    returnCurrentWidget: function(id) {        
        this.storageWidget = dijit.byId(id);
        var storageName = dijit.byId("storageName");
       
        storageName.setAttribute("style", "display: none");
        this._zoneWidget.setAttribute("style", "display: none");
        this._diskOfferWidget.setAttribute("style", "display: none");
        
        dojo.byId("storageNameLabel").innerHTML = this.storageWidget.additionalProperties.name;
        dojo.byId("storageNameLabel").style.display = "block";
        
        dojo.byId("storageZonesLabel").innerHTML = this.storageWidget.additionalProperties.zone;
        dojo.byId("storageZonesLabel").style.display = "block";
        
        dojo.byId("storageDiskOfferLabel").innerHTML = this.storageWidget.additionalProperties.diskOffer;
        dojo.byId("storageDiskOfferLabel").style.display = "block";
    },
    cancel: function() {
        dijit.byId("storageName").set('style', {'display':'block',  'margin': '10px 0 0 160px' });
        dojo.byId("storageNameLabel").style.display = "none";
        
        this._zoneWidget.set('style', {'display': 'block', 'margin' : '0 0 0 160px', 'width' : '330px'});
        dojo.byId("storageZonesLabel").style.display = "none";
        
        this._diskOfferWidget.set('style', {'display': 'block', 'margin' : '0 0 0 160px', 'width' : '330px'});
        dojo.byId("storageDiskOfferLabel").style.display = "none";
    }, 
    attachDisk: function() {
        dijit.byId("attachDialog").show();
    },
    closeDiskDialog: function() {
        dijit.byId("attachDialog").hide();
    },
    addDisk: function() {
        var attachVolumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/attach/"
        });          
        
        attachVolumeRestStore.add({
            volumeReferenceId: this.storageWidget.additionalProperties.volumeReferenceId,
            virtualMachineReferenceId: this._virtualMachineWidget.value
        });
        dijit.byId("attachDialog").hide();
    },
    populateValues: function() {
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                var currentZoneCost  = new Zone.ZoneCost({
                    zoneName: el.aliasName,
                    zoneIdNode: el.id
                });
                currentZoneCost.placeAt("zoneCost"); 
                currentZoneCost.removeSetupCost();
            }); 
        });        
        
        var zoneOptions = {            
            identifier: 'id',
            label: 'name',
            items: []
        };
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
            });
        });                                      
        
        this._zoneWidget = new Select({
            id: "instanceZoneWidget1",
            name: "zoneWidgets1",
            value: "allZone",        
            store: zoneList 
        }, "storageZone");                              
        
        var diskOfferOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var diskOfferList = new ItemFileWriteStore({data: diskOfferOptions});
        
        this._diskOfferRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                diskOfferList.newItem({id: el.diskOfferReferenceId, name: el.name});
            });
        });
        
        this._diskOfferWidget = new Select ({
            id: "diskOfferWidget1",
            name: "diskOfferWidget1",
            value: "noThanks",         
            store: diskOfferList  
        }, "storageDiskOffer");        
        
        var virtualMachineOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        
        var virtualMachineList = new ItemFileWriteStore({data: virtualMachineOptions});
        
        this._virtualMachineRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                virtualMachineList.newItem({id: el.referenceId, name: el.name});
            });
        });
        
        this._virtualMachineWidget = new Select ({
            id: "virtualMachineWidget",
            name: "virtualMachineWidget",
            width: "100px",       
            store: virtualMachineList  
        }, "attachDisk");
        
        this._volumeRestStore.query().then(function(data) {
            dojo.forEach(data, function(storageData) {
                var storageListItem = new List.ListItem ({
                    onClick: "UserStorage.returnCurrentWidget(this.id)",
                    onAttachTagClick: UserStorage.attachDisk
                });                
                
                var storageListData = {
                    id: storageData.id,
                    name : storageData.name,
                    diskOffer: storageData.diskOffer,
                    volumeReferenceId: storageData.referenceId,
                    zone: storageData.zoneName
                };

                storageListItem.placeAt("storageList");
                storageListItem.startup();
                storageListItem.additionalProperties = storageListData;
                storageListItem.additionalProperties.heading = storageData.name;
                storageListItem.getData();
                storageListItem.removeDescription();
                storageListItem.enableAttachNode();
            });
        }); 
    }
};

var UserSecurityGroups = {
    _securityGroupsRestStore : "",
    _securityIngressRestStore:"",
    _securityEgressRestStore:"",
    _ingressGridWidget:"",
    _engressGridWidget:"",
    init : function() {        
        this._securityGroupsRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/"
        });
        
        this._securityIngressRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/ingress/"
        });
        
        this._securityEgressRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/egress/"
        });
    },
    populateValues :  function() {        
        if (dojo.query("#userSecurityCollection .WizardListItem").length != 0) {
            return;
        }
        this._securityGroupsRestStore.query().then(function(data) {
            dojo.forEach(data.securityGroups, function(securityData) {               
                var securityGroupItem = new FogPanel.WizardListItem({
                    heading: securityData.securityGroupName,
                    description: securityData.description,
                    onClick: function() {
                        UserSecurityGroups.returnSecurityGroups(this);
                    },
                    additionalProperties: {
                        id:securityData.securityGroupId,
                        name: securityData.securityGroupName,
                        description: securityData.description
                    }
                });        
                securityGroupItem.placeAt("userSecurityGroupsListItem");
                securityGroupItem.startup(); 
            });
        }); 
    },   
    authentication : function() {
        var pageNode = dojo.byId("userSecurityPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var status = true;
        
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        }
        return status;     
    },
    returnSecurityGroups : function(currentWidget) {
        dojo.byId("currentWidgetOfferId").value = currentWidget.id;
        dijit.byId("networkPageName").setValue(currentWidget.additionalProperties.name);
        dijit.byId("networkPageDescription").setValue(currentWidget.additionalProperties.description);
        
        dojo.byId("securityGroupNameLabel").innerHTML = currentWidget.additionalProperties.name;
        dojo.byId("securityGroupDescriptionLabel").innerHTML = currentWidget.additionalProperties.description;
        
        dojo.query("#securityPage .updatable").toggleClass("non_updatable", true);
        dojo.query("#securityPage .hide_lable").toggleClass("show_lable", true);       
        
        var securityIngressRestStore = this._securityIngressRestStore;
        if(dijit.byId("ingressGrid")) {
            dijit.byId("ingressGrid").destroyRecursive();
        }
        if(dijit.byId("egressGrid")) {
            dijit.byId("egressGrid").destroyRecursive();
        }
        var ingressData = {
            items: []
        };
        var ingressDataList = new ItemFileWriteStore({data: ingressData});         
        var egressData = {
            items: []
        };         
        
        var engressDataList = new ItemFileWriteStore({data: egressData});
        
        this._securityGroupsRestStore.get(currentWidget.additionalProperties.id).then(function(securityGroupData) {                
            dojo.forEach(securityGroupData.securityGroups, function(currentData){                 
                dojo.forEach(currentData.ingressRules, function(currentIngressRules) {
                    ingressDataList.newItem({id: currentIngressRules.securityGroupRuleId,protocol: currentIngressRules.protocol, startPort: currentIngressRules.startPort, endPort:currentIngressRules.endPort, cidr:currentIngressRules.cidr});                     
                });
                dojo.forEach(currentData.egressRules, function(currentEgressRules) {
                    engressDataList.newItem({id: currentEgressRules.securityGroupRuleId,protocol: currentEgressRules.protocol, startPort: currentEgressRules.startPort, endPort:currentEgressRules.endPort, cidr:currentEgressRules.cidr});                     
                });                
            });
        });
        var ingressRuleslayout = [
            [
                {'name': 'ID', 'field': 'id', 'width': '100px', 'hidden': 'true'},
                {'name': 'Protocol', 'field': 'protocol', 'width': '100px'},
                {'name': 'Start Port', 'field': 'startPort', 'width': '100px'},
                {'name': 'End Port', 'field': 'endPort', 'width': '100px'},
                {'name': 'CIDR', 'field': 'cidr', 'width': '100px'},
                {'name': 'Action', 'field': 'action',
                    'formatter': function() {
                        var deleteButton = new dijit.form.Button({
                            label: "Delete",
                            onClick : function() {
                                var items = grid.selection.getSelected();
                                dojo.forEach(items, function(selectedItem) {
                                    if(selectedItem) {
                                        securityIngressRestStore.remove(selectedItem.id).then(function() {
                                            ingressDataList.deleteItem(selectedItem);
                                        });                                   
                                    }
                                });
                            }
                        });
                        return deleteButton;
                    },'width': '100px'}
            ]
        ];         
        
        var egressRuleslayout = [
            [
                {'name': 'ID', 'field': 'id', 'width': '100px', 'hidden': 'true'},
                {'name': 'Protocol', 'field': 'protocol', 'width': '100px'},
                {'name': 'Start Port', 'field': 'startPort', 'width': '100px'},
                {'name': 'End Port', 'field': 'endPort', 'width': '100px'},
                {'name': 'CIDR', 'field': 'cidr', 'width': '100px'},
                {'name': 'Action', 'field': 'action',
                    'formatter': function(){
                        var deleteButton = new dijit.form.Button({
                            label: "Delete",
                            onClick : function() {
                            }                   
                        });
                        return deleteButton;
                    },'width': '100px'}
            ]
        ];         
        this._ingressGridWidget = new DataGrid({
            id: 'ingressGrid',
            store: ingressDataList,
            structure: ingressRuleslayout,
            height:  '200px'
        });
        this._ingressGridWidget.placeAt("networkGrid");
        this._ingressGridWidget.startup();         
        this._engressGridWidget = new DataGrid({
            id: 'egressGrid',
            store: engressDataList,
            structure: egressRuleslayout,
            height:  '200px'
        });
        this._engressGridWidget.placeAt("networkEgressGrid");
        this._engressGridWidget.startup(); 
    },
    addIngress: function() {
        var pageNode = dojo.byId("securityIngressRule");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        }        
        var currentListWidgetId =  dojo.byId("currentWidgetOfferId").value;
        var currentListWidget = dijit.byId(currentListWidgetId);
        var grid = this._ingressGridWidget;
        var protocol = dijit.byId('networkProtocol').get('displayedValue');
        var startPort = dijit.byId("networkStartPort").value;
        var endPort = dijit.byId("networkEndPort").value;
        var cidr = dijit.byId("networkCidr").value;                  
        this._securityIngressRestStore.add({
            protocol: protocol, 
            startPort: startPort, 
            endPort: endPort,
            cidr: cidr,
            securityGroupName: currentListWidget.additionalProperties.name,
            securityGroupId: currentListWidget.additionalProperties.id
        }).then(function(data) {
            if(data == "OK") {
                registry.byId("appToaster").setContent("Task In Progress!","message");
                registry.byId("appToaster").show();
            } else {
                registry.byId("appToaster").setContent("Failed !", "error");
                registry.byId("appToaster").show();
            }                       
        });
    },
    addEgress : function() {       
        var pageNode = dojo.byId("securityEgressRule");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;        
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if (firstNode) {
            firstNode.focus();
        }        
        var currentListWidgetId =  dojo.byId("currentWidgetOfferId").value;
        var currentListWidget = dijit.byId(currentListWidgetId);
        var egressGrid = this._egressGridWidget;
        var protocol = dijit.byId('networkEgressProtocol').get('displayedValue');
        var startPort = dijit.byId("networkEgressStartPort").value;
        var endPort = dijit.byId("networkEgressEndPort").value;
        var cidr = dijit.byId("networkEgressCidr").value;                  
        this._securityEgressRestStore.add({
            protocol: protocol, 
            startPort: startPort, 
            endPort: endPort,
            cidr: cidr,
            securityGroupName: currentListWidget.additionalProperties.name,
            securityGroupId: currentListWidget.additionalProperties.id
        }).then(function(data) {
            if(data == "OK") {
                registry.byId("appToaster").setContent("Task In Progress!","message");
                registry.byId("appToaster").show();
            } else {
                registry.byId("appToaster").setContent("Failed !", "error");
                registry.byId("appToaster").show();
            }                       
        })
    },
    add : function() {
        var status = UserSecurityGroups.authentication();
        if(status == true) {
            var name = dijit.byId("networkPageName");
            var description = dijit.byId("networkPageDescription");         
            this._securityGroupsRestStore.add({
                name: name.value,
                description: description.value
            }).then(function(resultData) {
                dojo.forEach(resultData, function(addResponseData) {
                    if(addResponseData.result == "OK") {
                        var securityGroupListItem = new FogPanel.WizardListItem({
                            heading: addResponseData.securityGroupName,
                            description: addResponseData.description,
                            onClick: function() {
                                UserSecurityGroups.returnSecurityGroups(this);
                            },
                            onDeleteTagClick: function() {
                                UserSecurityGroups.deleteCurrentWidget(this);
                            },
                            additionalProperties : {
                                id:addResponseData.securityGroupId,
                                description: addResponseData.description,
                                name: addResponseData.securityGroupName
                            }
                        });
                        securityGroupListItem.placeAt("networkPageList");
                        securityGroupListItem.startup();
                 
                        registry.byId("appToaster").setContent("Added!","message");
                        registry.byId("appToaster").show();
                        dijit.byId("securityGroupsForm").reset();
                    } else {
                        registry.byId("appToaster").setContent("Failed !", "error");
                        registry.byId("appToaster").show();
                    }  
                });                
            });
        }         
    },
    cancel : function() {
        dijit.byId("securityGroupsForm").reset();
        dojo.query("#securityPage .updatable").removeClass("non_updatable");
        dojo.query("#securityPage .hide_lable").removeClass("show_lable");
    },
    changeIngressLabel : function(selectWidget) {
        if(selectWidget.value == "ICMP"){
            dojo.byId("ingressStartPort").innerHTML = "ICMP Type";
            dojo.byId("ingressEndPort").innerHTML = "ICMP Code";
        } else {
            dojo.byId("ingressStartPort").innerHTML = "Start Port";
            dojo.byId("ingressEndPort").innerHTML = "End Port";
        }
    },
    changeEgressLabel : function(selectWidget) {
        if(selectWidget.value == "ICMP"){
            dojo.byId("egressStartPort").innerHTML = "ICMP Type";
            dojo.byId("egressEndPort").innerHTML = "ICMP Code";
        } else {
            dojo.byId("egressStartPort").innerHTML = "Start Port";
            dojo.byId("egressEndPort").innerHTML = "End Port";
        }
    }
};

var ContactDetail = {
    _currentAccRestStore : "",
    _countryStore : "",
    _stateStore : "",
    init: function() {
        this._currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"
        });
        this._countryStore = new JsonRest({
            target: core.getContextPath()+"/api/country" 
        });
        this._stateStore = new JsonRest({
            target: core.getContextPath()+"/api/state/"
        });              
    }, 
    populateValues : function() {
        dojo.byId("subMenu").innerHTML = "Contact";
        dojo.byId("lastMenu").innerHTML = "Company Info";
        dojo.query("#accountSetupBreadcrumb .hide_lable").toggleClass("show_lable", true);        
        this._currentAccRestStore.query().then(function(data) {
            dojo.forEach(data, function(currentAcc) {
                dojo.byId("contactCountryLable").innerHTML = currentAcc.country;
                dojo.byId("contactStateLable").innerHTML = currentAcc.state;
                dojo.byId("contactStreetLable").innerHTML = currentAcc.streetAddress;
                dojo.byId("contactCityLable").innerHTML = currentAcc.city;
                dojo.byId("contactStateLable").innerHTML = currentAcc.state;
                dojo.byId("contactZipLable").innerHTML = currentAcc.zip;                
                dijit.byId("contactStreet").setValue(currentAcc.streetAddress);
                dijit.byId("contactCity").setValue(currentAcc.city);
                dijit.byId("contactZip").setValue(currentAcc.zip);                                  
                dojo.byId("contactFirstName").innerHTML = currentAcc.firstName;
                dojo.byId("contactLastName").innerHTML = currentAcc.lastName;
                dojo.byId("contactUserName").innerHTML = currentAcc.userName;
                dojo.byId("contactEmail").innerHTML = currentAcc.email;
                dojo.byId("contactPhoneNo").innerHTML = currentAcc.phoneNumber;
                dojo.byId("currentContactId").value = currentAcc.id;                 
            });
        });         
        var countryOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Country"}]
        };                    
        var stateOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "", name: "Select state"}]
        };                                          
        var countryList = new ItemFileWriteStore({data: countryOptions});
        var stateList = new ItemFileWriteStore({data: stateOptions});
        this._countryStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                countryList.newItem({id: el.id, name: el.countryName})
            });
        });  
        var stateWidget = new Select({
            id: "contactStateWidget",                    
            labelAttr: "name",
            maxHeight: -1,             
            store: stateList 
        }).placeAt("contactState");
        stateWidget.startup();
        var countryWidget = new Select({
            id: "contactCountryWidget",
            store: countryList,                  
            onChange : function() {                                     
                this.get("displayedValue"); 
                var stateWidget = dijit.byId("contactStateWidget");
                stateOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                stateList = new ItemFileWriteStore({data: stateOptions});
                if(this.value == "") {
                    stateList.newItem({id: "", name: "Select state"});
                    stateWidget.setStore(stateList);
                }
                var stateStore = new JsonRest({target: core.getContextPath()+"/api/state/" });
                stateStore.get(this.value).then(function(stateListItems) {
                    dojo.forEach(stateListItems,function(currentState) {
                        stateList.newItem({id: currentState.id, name: currentState.stateName});
                    });
                    stateWidget.setStore(stateList);
                });
            }
        }).placeAt("contactCountry"); 
        countryWidget.startup();                
    },
    update: function() {
        var country = dijit.byId("contactCountryWidget");
        var state = dijit.byId("contactStateWidget");
        var street = dijit.byId("contactStreet");
        var city = dijit.byId("contactCity");
        var zip = dijit.byId("contactZip");
        var currentId = dojo.byId("currentContactId");
         
        var currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        currentAccRestStore.put({
            id: currentId.value,
            type: "contactInfo",
            country: country.value,
            state: state.value,
            street: dojox.html.entities.encode(street.value),
            city: city.value,
            zip: zip.value                           
        }).then(function(data) {
            dojo.forEach(data, function(result) {
                if(result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.message.updateSuccess,"message");
                    registry.byId("userToaster").show();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                    registry.byId("userToaster").show();
                }
            });
        });
    },
    getContentName : function(currentTab) {
        var currentState = currentTab.title;
        dojo.byId("lastMenu").innerHTML = currentState;
    },
    edit : function() {
        dojo.query("#companyInfo .updatable").toggleClass("non_updatable", true);
        dojo.query("#companyInfo .hide_lable").toggleClass("show_lable", true);
    },
    cancel : function() {        
        dojo.query("#companyInfo .hide_lable").removeClass("show_lable", true);
        dojo.query("#companyInfo .updatable").removeClass("non_updatable", true);
    }
};

var GeneralDetail = {
    _currentAccRestStore:"",
    _accountRestStore:"",
    init: function() {
        this._currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"
        });
        
        this._accountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
    }, 
    populateValues : function() {       
        var countryStore = new JsonRest({target: core.getContextPath()+"/api/public/country" });        
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
                       
        var stateWidget = new FilteringSelect({
            id: "state",
            labelAttr: "name",
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport     
            store: stateList,    
            placeHolder: translator.common.account.selectState,
        }).placeAt("stateList");
        stateWidget.startup();
        
        var countryWidget = new FilteringSelect({
            id: "country",
            labelAttr: "name",
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport     
            store: countryList,            
            placeHolder: translator.common.account.selectCountry,
            onChange: function() {
                GeneralDetail.getState(this);
            }
        },"countryList");
        countryWidget.startup();
        
        this._currentAccRestStore.query().then(function(data) {
            var newCountryOptions = {
                identifier: 'id',
                label: 'name',
                items: [] 
            };
            var newCountryList = new ItemFileWriteStore({data: newCountryOptions});                             
            dojo.forEach(data, function(currentAcc) {   
                
                dijit.byId("accountIdentifier").setValue(currentAcc.userName);
                dijit.byId("domainName").setValue(currentAcc.domainName);
                dijit.byId("firstName").setValue(currentAcc.firstName);
                dijit.byId("lastName").setValue(currentAcc.lastName);
                dijit.byId("streetAddress").setValue(currentAcc.streetAddress);
                dijit.byId("streetAddress2").setValue(currentAcc.streetAddress2);
                dijit.byId("city").setValue(currentAcc.city);
                dijit.byId("phoneNumber").setValue(currentAcc.phoneNumber);
                dijit.byId("zip").setValue(currentAcc.zip);
                
                if(currentAcc.dateFormat) {
                    dijit.byId("clientDateFormateWidget").set("value", currentAcc.dateFormat);
                } else {
                    dijit.byId("clientDateFormateWidget").set("value", "dd/MMM/yyyy");
                }
                
                dojo.byId("callingCode").innerHTML = currentAcc.countryCallingCode;                   
                dojo.byId("genaralStateId").value = currentAcc.stateId;
                dojo.byId("generalCountryId").value = currentAcc.countryId;                
                dojo.byId("currentId").value = currentAcc.id;                
                countryStore.query().then(function(data) {
                    dojo.forEach(data, function(el) {
                        newCountryList.newItem({id: el.id, name: el.countryName, callingCode: el.callingCode});
                    });
                    countryWidget.set("store", newCountryList);
                    countryWidget.set("value", currentAcc.countryId);
                });
            });
        });
    },
    getState : function(currentCountry) {
        var stateId = dojo.byId("genaralStateId").value; 
        var countryId =  dojo.byId("generalCountryId").value;
        
        var stateStore = new JsonRest({target: core.getContextPath()+"/api/public/state" });
        if(this.value == "option") {
            dojo.byId("callingCode").innerHTML = "code";
        } else {
            currentCountry.store.fetch( { query: { id:currentCountry.value },  
                onItem: function(item) {                    
                    dojo.byId("callingCode").innerHTML = item.callingCode; 
                }
            });
        }               
        var stateWidget = dijit.byId("state");
        var newStateOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };

        var newStateList = new ItemFileWriteStore({data: newStateOptions});
        if(this.value == "option") {
            stateWidget.store.newItem({id: "option", name: translator.common.account.selectState});
        } else {
            stateStore.query({code : currentCountry.value}).then(function(stateListItems) {
                var firstState = "";
                dojo.forEach(stateListItems,function(currentState, index) {
                    newStateList.newItem({id: currentState.id, name: currentState.stateName});    
                    if(index == 0) {                        
                        firstState =  currentState.id; 
                    }
                });
                stateWidget.set("store", newStateList);  
                if(countryId == currentCountry.value) {
                    stateWidget.set("value", stateId); 
                } else {
                    stateWidget.set("value", firstState); 
                }
            });        
        }  
    },
    update : function(currentCountry) {        
        var pageNode = dojo.byId("generalPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {               
                state = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if(state == true) {           
            var firstName = dijit.byId("firstName").value;
            var lastName = dijit.byId("lastName").value;
            var streetAddress = dijit.byId("streetAddress").value;
            var streetAddress2 = dijit.byId("streetAddress2").value;
            var city = dijit.byId("city").value;
            var dateFormat = dijit.byId("clientDateFormateWidget").value;
            var country = dijit.byId("country").value;
            var state = dijit.byId("state").value;
            var phoneNumber = dijit.byId("phoneNumber").value;
            var zip = dijit.byId("zip").value;
            var currentId = dojo.byId("currentId").value;
            dijit.byId('gereralButton').set('style', {display: 'none'});
            dojo.byId("generalInfoLoader").style.display = "block";
            this._accountRestStore.put({
                id: currentId,
                firstName : firstName,
                lastName : lastName,
                dateFormat : dateFormat,
                street : dojox.html.entities.encode(streetAddress),               
                street1 : dojox.html.entities.encode(streetAddress2),
                city : city,
                country : country,
                state : state,
                phone : phoneNumber,
                zip : zip,           
                type: "general"
            }).then(function(result) {
                dijit.byId('gereralButton').set('style', {display: 'block'});
                dojo.byId("generalInfoLoader").style.display = "none";
                dojo.forEach(result, function(resultData) {
                    if(resultData.result == "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.updateSuccess,"message");
                        registry.byId("userToaster").show();
                        AccountInfo.init();
                        AccountInfo.populateValues();
                        GeneralDetail.sendMail(resultData);
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                        registry.byId("userToaster").show();
                    }
                })
            });           
        }              
    },    
    sendMail : function(resultData) {
        var currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/settings/sendMail/"
        });
        currentAccRestStore.add({
            type: "accountSettingGeneralInfoUpdate",
            firstName: resultData.firstName,
            lastName: resultData.lastName,
            
            streetAddress: resultData.streetAddress,
            streetAddress1: resultData.streetAddress1,
            city: resultData.city,
            country: resultData.country.countryName,
            phoneNumber: resultData.phoneNumber,
            zip: resultData.zip,
            state: resultData.state.stateName            
        })
    }        
};

var BillingDetail = {
    _currentAccRestStore:"",
    _accountRestStore:"",
    init: function() {
        this._currentAccRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount/"
        });
        
        this._accountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
    }, 
    populateValues : function() {        
        var countryStore = new JsonRest({target: core.getContextPath()+"/api/public/country" });        
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
             
        var stateWidget = new FilteringSelect({
            id: "billingState",
            style: "width: 300px;",
            labelAttr: "name",
            placeHolder: translator.common.account.selectState,
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport     
            store: stateList
        },"billingStateList");
        stateWidget.startup();
        
        var countryWidget = new FilteringSelect({
            id: "billingCountry",                     
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport     
            store: countryList,     
            placeHolder: translator.common.account.selectCountry,
            onChange: function() { 
                BillingDetail.loadState(this);       
            }
        },"billingCountryList");
        countryWidget.startup();
        
        this._currentAccRestStore.query().then(function(data) {
            var newCountryOptions = {
                identifier: 'id',
                label: 'name',
                items: [] 
            };
            var newCountryList = new ItemFileWriteStore({data: newCountryOptions});                             
            dojo.forEach(data, function(currentAcc) {                
                dojo.byId("billingStateId").value = currentAcc.billingStateId;
                dojo.byId("billingCountryId").value = currentAcc.billingCountryId;
                dijit.byId("billingStreetAddress").setValue(currentAcc.billingAddress);
                dijit.byId("billingStreetAddress2").setValue(currentAcc.billingAddress2);
                dijit.byId("billingCity").setValue(currentAcc.billingCity);
                dijit.byId("userCompanyName").setValue(currentAcc.companyName);
                dijit.byId("billingPhoneNumber").setValue(currentAcc.billingPhoneNumber);
                dijit.byId("billingZip").setValue(currentAcc.billingZip);                               
                dojo.byId("billingCallingCode").innerHTML = currentAcc.billingCountryCallingCode;                       
                dojo.byId("billingCurrentId").value = currentAcc.id;  
                dijit.byId("email").setValue(currentAcc.email);                                     
                countryStore.query().then(function(data) {
                    dojo.forEach(data, function(el) { 
                        newCountryList.newItem({id: el.id, name: el.countryName, callingCode: el.callingCode});
                    });                    
                    dijit.byId("billingCountry").set("store", newCountryList);
                    dijit.byId("billingCountry").set("value", currentAcc.billingCountryId);                 
                });    
            });
        });      
    },
    loadState : function(currentCountry) {  
        var stateOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        var countryOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.account.selectCountry}] 
        };
        var stateStore = new JsonRest({target: core.getContextPath()+"/api/public/state" });
        var countryList = new ItemFileWriteStore({data: countryOptions});
        var stateList = new ItemFileWriteStore({data: stateOptions});
          if(currentCountry.value == "option") {
              dojo.byId("callingCode").innerHTML = "code";
          } else {
              currentCountry.store.fetch( { query: { id:currentCountry.value },  
                  onItem: function(item) {                   
                     dojo.byId("billingCallingCode").innerHTML = item.callingCode; 
                  }
              });
          }               
          var countryId = dojo.byId("billingCountryId").value;
          var billingStateId = dojo.byId("billingStateId").value ;
          var stateWidget = dijit.byId("billingState");
          stateOptions = {
              identifier: 'id',
              label: 'name',
              items: []
          };
          stateList = new ItemFileWriteStore({data: stateOptions});
          if(currentCountry.value == "option") {
              stateList.newItem({id: "option", name: translator.common.account.selectState});
              stateWidget.set("store", stateList);                         
          } else {
              stateStore.query({code : currentCountry.value}).then(function(stateListItems) {
                  var firstState = '';
                  dojo.forEach(stateListItems,function(currentState, index) {
                      stateList.newItem({id: currentState.id, name: currentState.stateName});
                      if(index == 0) {
                          firstState = currentState.id;
                      }
                  });  
                  stateWidget.set("store", stateList);  
                  if(currentCountry.value == countryId) {
                      stateWidget.set("value", billingStateId);
                  } else {
                      stateWidget.set("value", firstState);
                  }                 
              });
          }
      },
      update : function() {
          var pageNode = dojo.byId("billingPage");
          var widgets = dijit.registry.findWidgets(pageNode);
          var firstNode = null;
          var state = true;
          dojo.forEach(widgets, function(el) {
              if (el.validate && !el.validate()) {               
                  state = false;
                  if (!firstNode) {
                      firstNode = el;
                  }
              }
          });
          if(state == true) {
              var streetAddress = dijit.byId("billingStreetAddress").value;
              var streetAddress2 = dijit.byId("billingStreetAddress2").value;
              var city = dijit.byId("billingCity").value;
              var country = dijit.byId("billingCountry").value;
              var state = dijit.byId("billingState").value;
              var phoneNumber = dijit.byId("billingPhoneNumber").value;
              var companyName = dijit.byId("userCompanyName").value;              
              var zip = dijit.byId("billingZip").value;
              var currentId = dojo.byId("billingCurrentId").value;
              var email = dijit.byId("email").value;

              dijit.byId('billingButton').set('style', {display: 'none'});
              dojo.byId("billingInfoLoader").style.display = "block";
              this._accountRestStore.put({
                  id: currentId,
                  street : dojox.html.entities.encode(streetAddress),
                  street1 : dojox.html.entities.encode(streetAddress2),
                  city : city,
                  companyName: companyName,
                  email : email,
                  country : country,
                  state : state,
                  phone : phoneNumber,
                  zip : zip,           
                  type: "billingInfo"
              }).then(function(result) {
                  dijit.byId('billingButton').set('style', {display: 'block'});
                  dojo.byId("billingInfoLoader").style.display = "none";
                  dojo.forEach(result, function(resultData) {
                      if(resultData.result == "OK") {
                          registry.byId("userToaster").setContent(translator.common.message.updateSuccess,"message");
                          registry.byId("userToaster").show();
                          BillingDetail.sendMail(resultData);
                      } else {
                          registry.byId("userToaster").setContent(translator.common.message.failed,"error");
                          registry.byId("userToaster").show();
                      }
                  })
              });
          }              
      },
      sendMail : function(resultData) {
          var currentAccRestStore = new JsonRest({
              target: core.getContextPath()+"/api/account/settings/sendMail/"
          });
          currentAccRestStore.add({
              type: "accountSettingBillingInfoUpdate",                       
              billingStreetAddress: resultData.billingStreetAddress,
              billingStreetAddress1: resultData.billingStreetAddress1,
              city: resultData.billingCity,
              country: resultData.billingCountry.countryName,
              billingPhoneNumber: resultData.billingPhoneNumber,
              billingZip: resultData.billingZip,
              billingState: resultData.billingState.stateName,    
              email: resultData.email
          })
      }        
  };
  
var NotificationDetail = {
    emailGrid:"",
    _notificationRestStore:"",
    init: function() {
        this._notificationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/notification/listEmail/"
        });
    }, 
    populateValues : function() {
        dojo.byId("subMenu").innerHTML = "Notification Email Details";
        dojo.byId("lastMenu").innerHTML = "Email Notificatoin";
        dojo.query("#accountSetupBreadcrumb .hide_lable").toggleClass("show_lable", true);
        if(dijit.byId("emailgrid")) {
            dijit.byId("emailgrid").destroyRecursive();
        }                        
        var notificationData = {
            items: []
        };
        var notificationDataList = new ItemFileWriteStore({data: notificationData});
        this._notificationRestStore.query().then(function(data) {
            dojo.forEach(data, function(notificationData) {
                notificationDataList.newItem({type:"Additional Alert Email", emailAddress:notificationData.email, action:"gggg"})
            });
        });       
        var notificationLayout = [
            [
                {'name': 'Type', 'field': 'type', 'width': '350px'},
                {'name': 'Email Address', 'field': 'emailAddress', 'width': '350px'},
                {'name': 'Action', 'field': 'action', 'width': '350px',  'formatter': function() {
                        var deleteButton = new dijit.form.Button({
                            label: "Delete Email",
                            onClick: function() {
                                var emailGrid = dijit.byId("emailgrid");
                                var email;
                                var  dataItem = emailGrid.selection.getSelected();                                       
                                dojo.forEach(dataItem, function(el) {
                                    email  =  el.emailAddress;
                                });
                                var notificationDeleteRestStore = new JsonRest({
                                    target: core.getContextPath()+"/api/notification/deleteEmail/"
                                });
                                notificationDeleteRestStore.remove(email).then(function(data) {
                                    dojo.forEach(data, function(resultData) {
                                        if(resultData == "OK") {
                                            registry.byId("userToaster").setContent("Email Deleted Successfully!","message");
                                            registry.byId("userToaster").show();                  
                                        } else {
                                            registry.byId("userToaster").setContent("Cannot Delete Email !", "error");
                                            registry.byId("userToaster").show();
                                        }
                                    });
                                });
                            }
                        });
                        return deleteButton;
                    }}
            ]
        ];         
        this.emailGrid = new DataGrid({
            id: 'emailgrid',
            store: notificationDataList,
            structure: notificationLayout,
            height:  '150px'
        });
        this.emailGrid.placeAt("notificationInfoGrid");
        this.emailGrid.startup();
    },
    add : function() {               
        var notificationAddRestStore = new JsonRest({
            target: core.getContextPath()+"/api/notification/addEmail/"
        });
        var email = dijit.byId("emailBox");
        notificationAddRestStore.add({
            email : email.value
        }).then(function(result) {
            dojo.forEach(result, function(resultData) {
                if(resultData == "OK") {
                    dijit.byId("emailgrid").store.newItem({type:"Additional Alert Email", emailAddress:email.value, action:""});
                    registry.byId("userToaster").setContent("Email Added Successfully!","message");
                    registry.byId("userToaster").show();                  
                } else {
                    registry.byId("userToaster").setContent("Cannot Add Email !", "error");
                    registry.byId("userToaster").show();
                }
            });
        });
    }
};

var UserInfoAlert = {
    userGrid:"",
    _alertNotificationRestStore:"",
    init: function() {
        this._alertNotificationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/notification/"
        });        
    }, 
    populateValues : function() {
        if(dijit.byId("userAlertGrid")) {
            dijit.byId("userAlertGrid").destroyRecursive();
        }
        dojo.byId("userAlertInfoGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.alerts.alertLoading+"</p>";
        var userData = {
            items: []
        };
        var userDataList = new ItemFileWriteStore({data: userData});
        var userLayout = [
            [                 
                {'name': translator.common.alerts.grid.layout.ip, 'field': 'ip', 'width': '20%', datatype:"string",  autoComplete: true},
                {'name': translator.common.alerts.grid.layout.attempt, 'field': 'attempt', 'width': '10%', datatype:"string",  autoComplete: true},
                {'name': translator.common.alerts.grid.layout.attemptFail, 'field': 'attemptFail', 'width': '10%', datatype:"string",  autoComplete: true, 'formatter' : function(data) {
                        return "<span class='redColor'>" + data + "</span>";    
                    }
                },
                {'name': translator.common.alerts.grid.layout.attemptSuccess, 'field': 'attemptSuccess', 'width': '10%', datatype:"string", autoComplete: true},
                {'name': translator.common.locked, 'field': 'locked', 'width': '10%', datatype:"string",  autoComplete: true},
                {'name': translator.common.lockTime, 'field': 'time', 'width': '20%', datatype:"string",  autoComplete: true}
            ]
        ];
        this._alertNotificationRestStore.query().then(function(data) {
            if(data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noUserAlertsMessageBox").style.display = "block"; 
                dojo.byId("userAlertInfoGrid").innerHTML = "";
            } else {
                dojo.byId("noUserAlertsMessageBox").style.display = "none"; 
                dojo.forEach(data, function(notificationData) {
                    userDataList.newItem({
                        ip:notificationData.ipAddress, 
                        time: notificationData.date, 
                        attempt: notificationData.overAllCount, 
                        locked: notificationData.isLocked === true ? translator.common.yes : translator.common.no, 
                        attemptFail: notificationData.failureCount, 
                        attemptSuccess:notificationData.successCount});
                });
                dojo.byId("userAlertInfoGrid").innerHTML = "";    
                var userAlertInfoGrid = new EnhancedGrid({
                    id: 'userAlertGrid',
                    "class" : "span12",
                    store: userDataList,
                    structure: userLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                userAlertInfoGrid.placeAt("userAlertInfoGrid");
                userAlertInfoGrid.startup(); 
            }              
        });                                                 
    }
};
var BillingInfoAlert = {
    billingGrid:"",
    init: function() {        
    }, 
    populateValues : function() {
        if(dijit.byId("billingGrid")) {
            dijit.byId("billingGrid").destroyRecursive();
        }            
        var billingData = {
            items: []
        };
        dojo.byId("billingInfoGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.alerts.alertLoading+"</p>"
        
        var billingAlerts = new JsonRest({
            target: core.getContextPath()+"/api/notification/billingAlerts"
        });            
        
        var billingDataList = new ItemFileWriteStore({data: billingData});    
        billingAlerts.query().then(function(data) {
            if(data.length == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                dojo.byId("noBillingAlertsMessageBox").style.display = "block"; 
                dojo.byId("billingInfoGrid").innerHTML = "";
            } else {                
                dojo.byId("noBillingAlertsMessageBox").style.display = "none";
                dojo.forEach(data, function(alertData) {
                dojo.forEach(alertData.nonViewed, function(nonViewedData) {
                    billingDataList.newItem({
                        id:nonViewedData.id, 
                        date: nonViewedData.date, 
                        description: nonViewedData.description,
                        action: {'alertId':nonViewedData.id, 'aletrDes':nonViewedData.description, 'alertDate':nonViewedData.date, viewed: nonViewedData.viewed}
                    });
                }); 
                dojo.forEach(alertData.viewed, function(viewedData) {
                    billingDataList.newItem({
                        id:viewedData.id, 
                        date: viewedData.date, 
                        description: viewedData.description,
                        action: {'alertId':viewedData.id, 'aletrDes':viewedData.description, 'alertDate': viewedData.date, viewed: viewedData.viewed}
                    });
                }); 
                dojo.byId("billingInfoGrid").innerHTML = "";
                var billingGridWidget = new EnhancedGrid({
                    id: 'billingGrid',
                    "class" : "span12",
                    store: billingDataList,
                    structure: billingLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                billingGridWidget.placeAt("billingInfoGrid");
                billingGridWidget.startup();
            });             
        }             
    });            
    var billingLayout = [        
        [
            {'name': translator.common.idCaps, 'field': 'id',  'width': '50px'},
            {'name': translator.common.date, 'field': 'date', 'width': '200px', datatype:"date",                
                // Declare how the data in store should be parsed to a Date object.
                dataTypeArgs: {
                    datePattern: "yyyy-MM-dd"
                }
            },
            {'name': translator.common.description, 'field': 'description', 'width': '600px', datatype:"string",  autoComplete: true},
            {'name': translator.common.action, 'field': 'action', 'width' : '100%', 'formatter' : function(data) {
                    if(data.viewed == false || data.viewed == "false") {
                        return new dijit.form.Button({
                            label: translator.common.view,
                            "class":"failur",                        
                            onClick: function () {                            
                                BillingInfoAlert.view(data);                                                          
                            }
                        }); 
                    } else {
                        return new dijit.form.Button({
                            label:  translator.common.viewed,                         
                            "class":"defaultbtn", onClick: function () {                            
                                BillingInfoAlert.view(data);                                                          
                            }                         
                        }); 
                    }                
                }, datatype:"string",  autoComplete: true
            }
        ]
    ];                     
    },
    view: function(data) {               
        var billingAlerts = new JsonRest({
            target: core.getContextPath()+"/api/notification/billingAlerts/view/"
        });          
        dijit.byId("viewBillingAlertPage").show();
        
        dojo.byId("alertDescription").innerHTML = data.aletrDes;
        dojo.byId("alertDate").innerHTML = data.alertDate;
        
        billingAlerts.put({id:data.alertId}).then(function(data) {
        });
        
        BillingInfoAlert.populateValues();        
    }
};

var NotificationInfo = {
    init: function() {                
    }, 
    populateValues : function() {
        if(dijit.byId("notificationGrid")) {
            dijit.byId("notificationGrid").destroyRecursive();           
        }            
        var notifiData = {
            items: []
        };
        
        dojo.byId("notificationInfoGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.alerts.notificationLoader+"</p>";   
        var notifiAlerts = new JsonRest({
            target: core.getContextPath()+"/api/notification/billingAlerts"
        }); 
           
        var notifiDataList = new ItemFileWriteStore({data: notifiData});    
        notifiAlerts.query().then(function(data) {
            dojo.forEach(data, function(alertData) {
                if(alertData.notification.length == 0 || alertData.notification == undefined || alertData.notification == "undefined" || alertData.notification == "" || alertData.notification == " ") {
                    dojo.byId("noNotificationAlertsMessageBox").style.display = "block"; 
                    dojo.byId("notificationInfoGrid").innerHTML = "";
                } else {
                    dojo.byId("noNotificationAlertsMessageBox").style.display = "none"; 
                    dojo.byId("notificationInfoGrid").innerHTML = "";
                    dojo.forEach(alertData.notification, function(notification) {
                        notifiDataList.newItem({
                            id:notification.id + "," + notification.date,
                            date: notification.date, 
                            description: notification.description + "," + notification.date
                        });
                    }); 
                    dojo.byId("notificationInfoGrid").innerHTML = "";
                    var notifiGridWidget = new EnhancedGrid({
                        id: 'notificationGrid',
                        "class" : "span12",
                        store: notifiDataList,
                        structure: notifiLayout,
                        autoHeight: true,
                        plugins: {
                            pagination: {
                                pageSizes: ["3", "5", "10", translator.common.all],
                                description: true,
                                sizeSwitch: true,
                                pageStepper: true,
                                gotoButton: true,
                                /*page step to be displayed*/
                                maxPageStep: 4,
                                /*position of the pagination bar*/
                                position: "bottom"
                            }
                        }
                    });
                    notifiGridWidget.placeAt("notificationInfoGrid");
                    notifiGridWidget.startup();              
                }                
            });
        });              
        var date = dojo.date.locale.format(new Date(), {datePattern: "dd-MM-yyyy", selector: "date"})
        var notifiLayout = [
            [
                {'name': translator.common.idCaps, 'field': 'id',  'width': '100px', 'formatter' : function(data) {
                        var content = data.split(",");
                        if(content[1] == date) {
                            return "<span class='redColor'>" + content[0] + "</span>";
                        } else {
                            return "<span>" + content[0] + "</span>";
                        }
                    }
                },
                {'name': translator.common.date, 'field': 'date', 'width': '100px', 'formatter': function(data) {
                        if(data == date) {
                            return "<span class='redColor'>" + data + "</span>";
                        } else {
                            return "<span>" + data + "</span>";
                        }
                    }
                },
                {'name': translator.common.description, 'field': 'description', 'width': '100%', 'formatter': function(data) {
                        var content = data.split(",");
                        if(content[1] == date) {
                            return "<span class='redColor'>" + content[0] + "</span>";
                        } else {
                            return "<span>" + content[0] + "</span>";
                        }
                    }
                }             
            ]
        ];                     
    },
    view: function(data) {
        var billingAlerts = new JsonRest({
            target: core.getContextPath()+"/api/notification/billingAlerts/view/"
        });  
        
        dijit.byId("viewBillingAlertPage").show();
        
        dojo.byId("alertDescription").innerHTML = data.aletrDes;
        dojo.byId("alertDate").innerHTML = data.alertDate;
        
        billingAlerts.put({id:data.alertId}).then(function(data) {
        });        
    }
};


var ZoneConfigForMenu = {
    init : function () {      
    },    
    populateServiceMenu : function (currentZoneId, zoneType) {            
        var serviceMenuList = dojo.query(".srv-sub-menu-sub ul li a");          
        dojo.forEach(serviceMenuList, function (el) {
            var parent = dojo.query(el).parent()[0];  
            domClass.add(parent, "disable_cursor");
            if(zoneType == "Advanced") {                  
                if(el.id == "serviceMenuIPManager" || el.id == "serviceMenuPortForwarding" || el.id == "serviceMenuSSHKey"|| el.id == "serviceMenuFirewall"|| el.id == "serviceMenuLoadBalancer" || el.id == "serviceMenuVPN" || el.id === "serviceMenuVPC") {                
                    domClass.add(el, "enable_cursor");
                    domClass.remove(el, "disable_cursor");  
                    dojo.byId("serviceMenuIPManager").href = "#/user/service/ip";
                    dojo.byId("serviceMenuSSHKey").href = "#/user/service/sshKey";                   
                    dojo.byId("serviceMenuLoadBalancer").href = "#/user/service/loadBalancer";   
                    dojo.byId("serviceMenuPortForwarding").href = "#/user/service/portForwarding";   
                    dojo.byId("serviceMenuFirewall").href = "#/user/network/";       
                    dojo.byId("serviceMenuVPN").href = " #/user/service/vpn/";       
                    dojo.byId("serviceMenuVPC").href = " #/user/vpc/dashboard";                                                                                       
                    var adParentLink = dojo.query(el).parent()[0];  
                    var firstChild = adParentLink.firstChild;
                    if(firstChild.className) {
                        var currentClass = firstChild.className.split(" ");
                        if(currentClass[0] == "comingsoon") {
                            domConstruct.destroy(firstChild); 
                        }                                                    
                    }                
                } else {
                    domClass.add(el, "disable_cursor");
                    domClass.remove(el, "enable_cursor");  
                    var adParentLink = dojo.query(el).parent()[0];  
                    var firstChild = adParentLink.firstChild;
                    if(firstChild.className) {
                        var currentClass = firstChild.className.split(" ");
                        if(currentClass[0] == "comingsoon") {
                            domConstruct.destroy(firstChild); 
                        }                                                    
                    }
                    var msgSpan = "<div class='comingsoon disable_cursor'>" + translator.common.comingSoon + "</div>"
                    domConstruct.place(msgSpan, adParentLink, "first");
                }
            } else if(zoneType == "Basic") {                               
                dojo.byId("serviceMenuLoadBalancer").href = "#";   
                dojo.byId("serviceMenuPortForwarding").href = "#";    
                dojo.byId("serviceMenuVPN").href = "#";
                dojo.byId("serviceMenuVPC").href = "#";                 
                if(el.id == "serviceMenuIPManager" || el.id == "serviceMenuSSHKey"|| el.id == "serviceMenuFirewall") {                
                    domClass.add(el, "enable_cursor");
                    domClass.remove(el, "disable_cursor");  
                    dojo.byId("serviceMenuIPManager").href = "#/user/service/ip";
                    dojo.byId("serviceMenuSSHKey").href = "#/user/service/sshKey";   
                    dojo.byId("serviceMenuFirewall").href = "#/user/cloud/firewall";                           
                } else {
                    domClass.add(el, "disable_cursor");
                    domClass.remove(el, "enable_cursor"); 
                    var currentparentLink = dojo.query(el).parent()[0];                            
                    var firstChild = currentparentLink.firstChild;
                    if(firstChild.className) {
                        var currentClass = firstChild.className.split(" ");
                        if(currentClass[0] == "comingsoon") {
                            domConstruct.destroy(firstChild); 
                        }                                                    
                    }
                    var banerMsg = ""
                    if(el.id === "serviceMenuStorageContainer" || el.id === "serviceMenuAmazonS3" ||  el.id === "serviceMenuMaxCnd" || el.id === "serviceMenuCloudFlare" || el.id === "serviceMenuGoDaddySSL" || el.id === "serviceMenuComodoSSL") {
                        banerMsg = translator.common.comingSoon;
                    } else {
                        banerMsg = translator.common.notAvailable;
                    }
                    var msgSpan = "<div class='comingsoon disable_cursor'>" + banerMsg + "</div>";
                    domConstruct.place(msgSpan, currentparentLink, "first");
               }                
           }
       })
   },
    populateValue : function () {             
        dojo.query("#userZoneListAd .item").forEach(dojo.destroy); 
        
//        var availabilityZoneRestStore = new JsonRest({
//                target: core.getContextPath() + "/api/zone/"
//        });
//        
//        availabilityZoneRestStore.query().then(function(data){
//            
//           dojo.forEach(data,function(zoneData){
//               dojo.byId("currentAdZoneName").innerHTML = zoneData.name;
//           }); 
//        });
         
        if(dijit.byId("userVerticalMenuBarWidget")) {            
            dijit.byId("userVerticalMenuBarWidget").unsubscribe();
            dijit.byId("userVerticalMenuBarWidget").destroyRecursive();
        }              
        var allNetworkMenu = [
            {menuItemName: translator.common.menu.home, href: "#/user/home", iconClass: "icon-home", subMenu: false, dropdownIcon: "", aTagClasses: "singleMenu"},
            {menuItemName: translator.common.menu.cloud, href: "#/user/cloud/", iconClass: "icon-cloud", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent : [                        
                     {subMenuItemName : translator.common.menu.servers, href : "#/user/server"},
                     {subMenuItemName : translator.common.menu.volumes, href : "#/user/volume/list"},
                     {subMenuItemName : translator.common.menu.images, href : "#/user/image/list"},
                     {subMenuItemName : translator.common.menu.snapShots, href : "#/user/snapshot/list"},
                     {subMenuItemName : translator.common.menu.health, href : "#/user/health"},
                ]
            }, 
            {menuItemName: translator.common.menu.accessAndSecurity, href: "#/user/accessAndSecurity/", iconClass: "index_title_icons_accessAndSecurity", style: " width: 10px; height: 12px",subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent : [   
                    {subMenuItemName : translator.common.menu.securityGroups, href : "#/user/securityGroup/list"},    
                    {subMenuItemName : translator.common.menu.sshKeys, href : "#/user/cloud/sshKey"},
                    {subMenuItemName : translator.common.menu.floatingIps, href : "#/user/floatingIp/list"},                  
                ]
            },
            {menuItemName: translator.common.menu.virtualDataCenter, href: "#/user/virtualDataCenter/", iconClass: "index_title_icons_small_network", style: " width: 10px; height: 12px",subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent : [   
                    {subMenuItemName : translator.common.menu.networks, href : "#/user/network/list"},    
                    {subMenuItemName : translator.common.menu.routers, href : "#/user/router/list"}                    
                ]
            }, 
            {menuItemName: translator.common.menu.billing, href: "#/user/billing", iconClass: "icon-shopping-cart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.menu.currentUsage, href : "#/user/billing/currentUsage"},
                    {subMenuItemName : translator.common.menu.invoice, href : "#/user/billing/invoiceSummary"},      
                    {subMenuItemName : translator.common.menu.payments, href : "#/user/billing/payment"},
                    {subMenuItemName : translator.common.menu.recurringItems, href : "#/user/billing/recurringItem"}                        
                ]
            },
            {menuItemName: translator.common.menu.notificationManager, href: "#/user/notification/", iconClass: "index_title_icons_cldnotification", style: " width: 10px; height: 12px",subMenu: true, dropdownIcon: "", aTagClasses: "singleMenu",
                
            },
            {menuItemName: translator.common.menu.support, href: "#/user/support/tickets", iconClass: "icon-user", subMenu: false, dropdownIcon: "", aTagClasses: "singleMenu"},
//            {menuItemName: translator.common.menu.Stats, href: "#/user/reports", iconClass: "icon-bar-chart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
//                submenuContent : [
//                    {subMenuItemName : translator.common.menu.billableItem, href : "#/user/reports/billableItem"},
//                    {subMenuItemName : translator.common.menu.paymentReport, href : "#/user/reports/paymentReport"}                                                    
//                ]
//            },
//            {menuItemName: translator.common.menu.activity, href: "#/user/home/accountAlert", iconClass: "icon-exchange", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
//                submenuContent : [
//                    {subMenuItemName : translator.common.menu.alerts, href : "#/user/home/userAlert"},
//                    {subMenuItemName : translator.common.menu.notifications, href : "#/user/home/notification"}                        
//                ]
//            }             
        ];
        
        var menuContainer = []; 
        menuContainer = allNetworkMenu;
        
        var VerticalMenuBar = new FogPanel.VerticalMenuBar({
                id : "userVerticalMenuBarWidget"
            }).placeAt("userVerticalMenuBar");  
            VerticalMenuBar.subscribe("/FogPanel/event/route/changed");        
            VerticalMenuBar.populateMenu(menuContainer);                
//            core.router.go("#/user/home");                   
//            setTimeout(function () {Dashboard.loadZoneData();},1000);   
//            setTimeout(function () {ZoneConfigForMenu.populateServiceMenu(currentZone.additionalProperties.zoneId, networkType);},1000);
        
        var advanceNetworkMenu = [
            {menuItemName: translator.common.menu.home, href: "#/user/home", iconClass: "icon-home", subMenu: false, dropdownIcon: "", aTagClasses: "singleMenu"},
            {menuItemName: translator.common.menu.cloud, href: "#/user/cloud/", iconClass: "icon-cloud", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent : [                        
                     {subMenuItemName : translator.common.menu.servers, href : "#/user/server"},
                     {subMenuItemName : translator.common.menu.volumes, href : "#/user/volume/list"},
                     {subMenuItemName : translator.common.menu.networks, href : "#/user/network/list"},
                     {subMenuItemName : translator.common.menu.sshKey, href : "#/user/cloud/sshKey"},
                     {subMenuItemName : translator.common.menu.accessAndSecurity, href : "#/user/securityGroup/list"},
                     {subMenuItemName : translator.common.menu.snapShots, href : "#/user/snapshot/list"},
                     
                ]
            }, 
            {menuItemName: translator.common.menu.billing, href: "#/user/billing", iconClass: "icon-shopping-cart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.menu.currentUsage, href : "#/user/billing/currentUsage"},
                    {subMenuItemName : translator.common.menu.invoice, href : "#/user/billing/invoiceSummary"},      
                    {subMenuItemName : translator.common.menu.payments, href : "#/user/billing/payment"},
                    {subMenuItemName : translator.common.menu.recurringItems, href : "#/user/billing/recurringItem"}                        
                ]
            },
            {menuItemName: translator.common.menu.support, href: "#/user/support/tickets", iconClass: "icon-user", subMenu: false, dropdownIcon: "", aTagClasses: "singleMenu"},
            {menuItemName: translator.common.menu.Stats, href: "#/user/reports", iconClass: "icon-bar-chart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.menu.billableItem, href : "#/user/reports/billableItem"},
                    {subMenuItemName : translator.common.menu.paymentReport, href : "#/user/reports/paymentReport"}                                                    
                ]
            },
            {menuItemName: translator.common.menu.activity, href: "#/user/home/accountAlert", iconClass: "icon-exchange", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.menu.alerts, href : "#/user/home/userAlert"},
                    {subMenuItemName : translator.common.menu.notifications, href : "#/user/home/notification"}                        
                ]
            }              
        ];        
        
        var basicNetworkMenu = [    
            {menuItemName: translator.common.menu.home, href: "#/user/home", iconClass: "icon-home", subMenu: false, dropdownIcon: "", aTagClasses: "singleMenu"},
            {menuItemName: translator.common.menu.cloud, href: "#/user/cloud/", iconClass: "icon-cloud", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent : [      
                    {subMenuItemName : translator.common.menu.health, href : "#/user/health"}
                ]
            }, 
            {menuItemName: translator.common.menu.billing, href: "#/user/billing", iconClass: "icon-shopping-cart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.menu.currentUsage, href : "#/user/billing/currentUsage"},
                    {subMenuItemName : translator.common.menu.invoice, href : "#/user/billing/invoiceSummary"},      
                    {subMenuItemName : translator.common.menu.payments, href : "#/user/billing/payment"},
                    {subMenuItemName : translator.common.menu.recurringItems, href : "#/user/billing/recurringItem"}                        
                ]
            },
            {menuItemName: translator.common.menu.support, href: "#/user/support/tickets", iconClass: "icon-user", subMenu: false, dropdownIcon: "", aTagClasses: "singleMenu"},
            {menuItemName: translator.common.menu.Stats, href: "#/user/reports", iconClass: "icon-bar-chart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.menu.billableItem, href : "#/user/reports/billableItem"},
                    {subMenuItemName : translator.common.menu.paymentReport, href : "#/user/reports/paymentReport"}                                                    
                ]
            },
            {menuItemName: translator.common.menu.activity, href: "#/user/home/accountAlert", iconClass: "icon-exchange", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle", 
                submenuContent : [
                    {subMenuItemName : translator.common.menu.alerts, href : "#/user/home/userAlert"},
                    {subMenuItemName : translator.common.menu.notifications, href : "#/user/home/notification"}                        
                ]
            }                             
        ];     
        
        var zoneListStore = new JsonRest({
            target: core.getContextPath()+"/api/zone"
        });
        
        var regionListStore = new JsonRest({
            target: core.getContextPath()+"/api/region"
        });
        
        regionListStore.query().then(function(data) {                             
            var regionSelectorWidget  = "";
            if(data === "undefined" || data.length === 0 || data === "" || data === undefined) {
        
            } else {     
                dojo.byId("selectedRegionID").value = data[0].id;
                dojo.byId("currentRegionName").innerHTML = data[0].name;
                dojo.forEach(data, function(el) {                                               
                    regionSelectorWidget = new FogPanel.ZoneSelector({                        
                        zoneName: el.name,
                        onZoneItemClick : function () {                            
                            dojo.byId("selectedRegionID").value = this.additionalProperties.zoneId;
                            dojo.byId("currentRegionName").innerHTML = this.additionalProperties.name;
                            
//                            ZoneConfigForMenu.showMenuOnZone(this, allNetworkMenu, advanceNetworkMenu, basicNetworkMenu);       
                        },
                        additionalProperties : {
                            zoneId: el.id,
                            networkType : el.name,
                            name : el.name
                        }                        
                    }).placeAt("userRegionList");
                    regionSelectorWidget.startup();
                });                                                                                                        
                 
            } 
                                                                                                        
            
        });
        
//        zoneListStore.query().then(function(data) {                 
//            var menuContentList = [];
//            var zoneSelectorWidget  = "";
//            if(data == "undefined" || data.length == 0 || data == "") {
////                menuContentList = allNetworkMenu;                
//            } else {                
//                
//                dojo.forEach(data, function(el) {  
////                     dojo.byId("selectedANZoneID").value = data.name;
//                    if(el.name === "internal") { 
//                        dojo.byId("selectedANZoneID").value = el.name;
//                        dojo.byId("currentAdZoneName").innerHTML = el.name;
//                        isBasicZoneAvailable = true;
//                    } else if (el.name === "nova") {
//                        isAdvanceZoneAviliable = true;
//                        dojo.byId("selectedANZoneID").value = el.name;
//                        dojo.byId("currentAdZoneName").innerHTML = el.name;
//                    }                         
//                    zoneSelectorWidget = new FogPanel.ZoneSelector ({                        
//                        zoneName: el.name,
//                        onZoneItemClick : function () {
//                            ZoneConfigForMenu.showMenuOnZone(this, allNetworkMenu, advanceNetworkMenu, basicNetworkMenu);       
//                        },
//                        additionalProperties : {
//                            zoneId: el.name,
//                            networkType : el.name
//                        }                        
//                    }).placeAt("userZoneListAd");
//                    zoneSelectorWidget.startup();
//                });                                                                                                        
//                if(isAdvanceZoneAviliable === true && isBasicZoneAvailable === true) {
//                    menuContentList = allNetworkMenu;
//                } else if(isAdvanceZoneAviliable === true && isBasicZoneAvailable === false) {
//                    menuContentList = allNetworkMenu;                       
//                } else if(isAdvanceZoneAviliable === false && isBasicZoneAvailable === true) {
//                    menuContentList = basicNetworkMenu;
//                } else {
//                    menuContentList = allNetworkMenu;
//            } 
//            } 
//                else {                
//                    dojo.byId("currentAdZoneName").innerHTML = translator.common.allZone;
//                    dojo.byId("selectedANZoneID").value = "All";
//                    zoneSelectorWidget = new FogPanel.ZoneSelector ({                        
//                        zoneName: translator.common.allZone,
//                        onZoneItemClick : function () {
//                            ZoneConfigForMenu.showMenuOnZone(this, allNetworkMenu, advanceNetworkMenu, basicNetworkMenu);       
//                        },
//                        additionalProperties : {
//                            zoneId: "option",
//                            networkType : "All"
//                        }
//                        
//                    }).placeAt("userZoneListAd");
//                    zoneSelectorWidget.startup();
//                    dojo.forEach(data, function(el) {  
//                        
//                        // code for all zone
//                        if(el.name === "internal") {
//                            isBasicZoneAvailable = true;
//                        } else if (el.name === "nova") {
//                            isAdvanceZoneAviliable = true;
//                        }                         
//                        zoneSelectorWidget = new FogPanel.ZoneSelector ({                        
//                            zoneName: el.name,
//                            onZoneItemClick : function () {
//                                ZoneConfigForMenu.showMenuOnZone(this, allNetworkMenu, advanceNetworkMenu, basicNetworkMenu);       
//                            },
//                            additionalProperties : {
//                                zoneId: el.name,
//                                networkType : el.name
//                            }                        
//                        }).placeAt("userZoneListAd");
//                        zoneSelectorWidget.startup();
//                    });                                                                                                        
//                    if(isAdvanceZoneAviliable === true && isBasicZoneAvailable === true) {
//                        menuContentList = allNetworkMenu;
//                    } else if(isAdvanceZoneAviliable === true && isBasicZoneAvailable === false) {
//                        menuContentList = advanceNetworkMenu;                       
//                    } else if(isAdvanceZoneAviliable === false && isBasicZoneAvailable === true) {
//                        menuContentList = basicNetworkMenu;
//                    } else {
//                        menuContentList = allNetworkMenu;
//                    }              
//                }                                                                                                         
            
//        });                                              
    },
    showServiceMenuItems : function (currentService) {  
        if(dijit.byId("serviceTooltipDialogue")) {
            dijit.byId("serviceTooltipDialogue").destroyRecursive();
        }  
        var currentId = currentService.serviceMenuId;   
        var domNode = dojo.byId(currentId); //domNode to which the tooltip must point to                
        var content = dojo.byId("serviceMenuItemContentContent");        
        var myTooltipDialog = new dijit.TooltipDialog({
            id: 'serviceTooltipDialogue',                  
            content: content.innerHTML,    
            class: "service_tooltip_dialogue",            
            onMouseLeave : function () {
                dijit.popup.close(this);
            }
        });        
        var menuList = dojo.query(".dashboard-menu a");
        dojo.forEach(menuList, function (menuItem) { 
            var BaseMenuItemClassName  = menuItem.className.split(" ");
            if(menuItem.id === currentId) {                  
                var parentNode = dojo.query(menuItem).parent()[0];
                domClass.add(parentNode, "active");                  
            } else if(menuItem.id !== currentId) {                            
                if(BaseMenuItemClassName[0] === "mainMenu") { 
                    var otherParent = dojo.query(menuItem).parent()[0];
                    var sibLink = dojo.query(menuItem).next()[0];
                    if(sibLink) {
                        var wipeOutArgs = {
                            node: sibLink,                        
                            duration: 100,
                            onEnd : function() {                                
                                dijit.popup.open({
                                    popup: myTooltipDialog,
                                    around: domNode,
                                    orient: ["after-centered"]
                                }); 
                                domClass.remove(sibLink, "active");
                                domClass.remove(otherParent, "active"); 
                            }
                        };                            
                        fx.wipeOut(wipeOutArgs).play(); 
//                        domClass.remove(otherParent, "active"); 
                    } 
                }            
            }
        });                          
    },
    hideToolTip : function () {
        var verticalManubarWidget = dijit.byId("userVerticalMenuBarWidget");
        var setviceId = verticalManubarWidget.serviceMenuId;  
        var serviceNode = dojo.byId(setviceId);
        var parentNode = dojo.query(serviceNode).parent()[0];
        domClass.remove(parentNode, "active");
        dijit.popup.close(dijit.byId("serviceTooltipDialogue"));
        var menuList = dojo.query(".dashboard-menu a");
        dojo.forEach(menuList, function (menuItem) { 
            var BaseMenuItemClassName  = menuItem.className.split(" ");
            if(BaseMenuItemClassName[2] === "no_bold_text" && menuItem.id !== setviceId) { 
                var childrens = menuItem.childNodes;
                dojo.forEach(childrens, function (el) {
                    domClass.remove(el, "no_bold_text"); 
                });              
                domClass.remove(menuItem, "no_bold_text");                
            }
        });        
    },
    showMenuOnZone : function (currentZone, allNetworkMenu, advanceNetworkMenu, basicNetworkMenu) {      
        if(dijit.byId("userVerticalMenuBarWidget")) {           
            dijit.byId("userVerticalMenuBarWidget").unsubscribe();
            dijit.byId("userVerticalMenuBarWidget").destroyRecursive();
        }     
        var menuContainer = [];     
        var networkType = ""
        var vpcStatEnabled = false;
        dojo.byId("currentAdZoneName").innerHTML = currentZone.additionalProperties.zoneId; 
        
        if(currentZone.additionalProperties.networkType === "internal") {
            menuContainer = advanceNetworkMenu;
            dojo.byId("selectedANZoneID").value = currentZone.additionalProperties.zoneId;
            networkType = "internal";
//            vpcStatEnabled = false;
//            dojo.byId("selectedANVPCID").value = "";
        } else if(currentZone.additionalProperties.networkType === "nova") {
            menuContainer = allNetworkMenu;
            dojo.byId("selectedANZoneID").value = currentZone.additionalProperties.zoneId;
            console.log(currentZone.additionalProperties.zoneId)
            networkType = "nova";
//            if(dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "undefined" || dojo.byId("selectedANVPCID").value === "null" ) {
//                vpcStatEnabled = false;                
//            } else {
//                vpcStatEnabled = true;                
//            } 
        } else {
            menuContainer = allNetworkMenu;
//            dojo.byId("selectedANZoneID").value = "All";
//            if(dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "undefined" || dojo.byId("selectedANVPCID").value === "null" ) {
//                vpcStatEnabled = false;                
//            } else {
//                vpcStatEnabled = true;                
//            } 
        }     
        dojo.byId("userVerticalMenuBar").innerHTML = "";
//        if(vpcStatEnabled === false) {
            var VerticalMenuBar = new FogPanel.VerticalMenuBar({
                id : "userVerticalMenuBarWidget",
//                onServiceMenuItemClick : function () {
//                    ZoneConfigForMenu.showServiceMenuItems(this)               
//                },
//                onOtherMenuItemClick : function () {
//                    ZoneConfigForMenu.hideToolTip();
//                }
            }).placeAt("userVerticalMenuBar");  
            VerticalMenuBar.subscribe("/FogPanel/event/route/changed");        
            VerticalMenuBar.populateMenu(menuContainer);                
                   
//            setTimeout(function () {Dashboard.loadZoneData();},1000);   
//            setTimeout(function () {ZoneConfigForMenu.populateServiceMenu(currentZone.additionalProperties.zoneId, networkType);},1000);
//        } else {            
//            VPCMenuInfo.populateValues("All");
//            core.router.go("#/user/vpc/dashboard");    
//            VPCMenuInfo.init();
//        }         
    }
};

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
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/PlotKit/green",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojox/charting/plot2d/ClusteredColumns",
    "dojox/charting/widget/Legend",
    "dojo/_base/connect",
    'dojox/timing',
    "FogPanel/Notification",
    "FogPanel/VerticalMenuBar",
    "dojo/number",
    "dojo/fx",
    "FogPanel/LanguageSelector",
    "FogPanel/ZoneSelector",
    "dojox/charting/plot2d/Markers",
    "FogPanel/Navigator",       
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
    "dijit/Dialog",
    "dijit/DropDownMenu",
    "dijit/MenuItem",
    "dojox/grid/enhanced/plugins/Menu",
    "dijit/popup",
    "dojo/_base/lang",
    "dojox/charting/widget/Chart2D",
    "dojox/charting/themes/Claro",   
    "dojo/dom-construct",
    "dojo/on",
    "FogPanel/VolumeListItem",
    "FogPanel/InstanceStatus",
    "dojox/widget/rotator/Pan",
    "dijit/form/CheckBox",
    "dijit/form/NumberSpinner",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "dojo/dnd/Source",
    "dijit/layout/TabContainer",
    "dojox/html/entities"
],
function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win,
Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, 
ColumnsPlot, Highlight, ClusteredColumns, Legend, connect, timing, Notification, VerticalMenuBar, LocaleNumber, fx) {   
    window.translator = translator;
    window.query = query;
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
    window.connect = connect;
    window.timing = timing;
    window.win = win;
    window.Chart = Chart;
    window.Pie = Pie;
    window.Lines = Lines;
    window.Magnify = Magnify;
    window.PlotKitGreen = PlotKitGreen;
    window.Tooltip = Tooltip;
    window.MoveSlice = MoveSlice;
    window.theme = theme;
    window.ColumnsPlot = ColumnsPlot;
    window.ClusteredColumns = ClusteredColumns;
    window.Legend = Legend;
    window.Highlight = Highlight;
    window.VerticalMenuBar = VerticalMenuBar;
    window.LocaleNumber = LocaleNumber;
    window.fx = fx; 
    App._loader = dojo.byId("userLoader");
    App.hideLoader();             
    controller({
        name:"home",
        module: "user",
        filePath: "/js/app/user.js",
        layout: {
            name: "user_index",
            containerId: "content"
        },
        scaffold: false
    },
    {        
        "index": action(function() {
            core.ui.loadTemplate("user_index", core.ui.getContentId()); 
            Dashboard.init();
            Dashboard.populateValues();            
        }),
        "activity" : action(function() {
            core.ui.loadTemplate("activityMenuInfo", core.ui.getContentId()); 
        }),
        "accountAlert" : action(function() {
            core.ui.loadTemplate("accountAlert", core.ui.getContentId()); 
        }),
        "accountList" : action(function() {
            core.ui.loadTemplate("accountList", core.ui.getContentId()); 
        }),
        "dashboard": action(function() {
            core.ui.loadTemplate("user_index", core.ui.getContentId()); 
        }),
        "accountSetup": action(function() {
            var currentUserPage = dijit.byId("userProfileForm");
            if(currentUserPage) {
                dijit.byId("userProfileForm").destroyRecursive();
            }                                                                               
            core.ui.loadTemplate("accountSetup", core.ui.getContentId()); 
            UserProfile.init();
            UserProfile.populateValues();
        }),
        "alert" : action(function() {
            core.ui.loadTemplate("alert", core.ui.getContentId()); 
        }),
        "userAlert" : action(function() {
            core.ui.loadTemplate("userAlert", core.ui.getContentId()); 
            UserInfoAlert.init();
            UserInfoAlert.populateValues();    
        }),
        "notification" : action(function() {
            core.ui.loadTemplate("notification", core.ui.getContentId()); 
            NotificationInfo.init();
            NotificationInfo.populateValues();    
        }),
        "billingAlert" : action(function() {   
            if(dijit.byId("viewBillingAlertPage")) {
                dijit.byId("viewBillingAlertPage").destroyRecursive();
            }
            core.ui.loadTemplate("billingAlert", core.ui.getContentId()); 
            BillingInfoAlert.init();
            BillingInfoAlert.populateValues();                    
        }),                                        
        "accountProfile": action(function() {
            var currentAccountPage = dijit.byId("accountProfileForm");
            if(currentAccountPage) {
                dijit.byId("accountProfileForm").destroyRecursive();
            }                
            core.ui.loadTemplate("accountProfile", core.ui.getContentId()); 
            AccountProfile.init();
            AccountProfile.populateValues();    
        }),
        "storage": action(function() {
            var currentStoragePage = dijit.byId("storageForm");
            if(currentStoragePage) {
                dijit.byId("storageForm").destroyRecursive();
                dijit.byId("attachDialog").destroyRecursive();
            }
            core.ui.loadTemplate("storage", core.ui.getContentId()); 
            UserStorage.init();
            UserStorage.populateValues();
        }),
        "securityGroups": action(function() {
            var currentSecurityGroupsPage = dijit.byId("userSecurityGroupsForm");
            if(currentSecurityGroupsPage) {
                dijit.byId("userSecurityGroupsForm").destroyRecursive();
            }
            core.ui.loadTemplate("securityGroups", core.ui.getContentId()); 
            UserSecurityGroups.init();
            UserSecurityGroups.populateValues();
        }),
        "iso": action(function() {
            var currentSecurityGroupsPage = dijit.byId("userIsoForm");
            if(currentSecurityGroupsPage) {
                 dijit.byId("userIsoForm").destroyRecursive();
            }
            core.ui.loadTemplate("userIsoPage", core.ui.getContentId()); 
//                UserIso.init();
//                UserIso.populateValues();
        }),
        "general" : action(function() {
            UserMenuConfig.init();            
            var currentGeneralPage = dijit.byId("generalForm");
            if(currentGeneralPage) {
                dijit.byId("generalForm").destroyRecursive();
                dijit.byId("profileForm").destroyRecursive();
                dijit.byId("billingForm").destroyRecursive();
            }                   
            core.ui.loadTemplate("general", core.ui.getContentId());                    
            GeneralDetail.init();
            GeneralDetail.populateValues();
            BillingDetail.init();
            BillingDetail.populateValues();
            AccountInfo.init();
            AccountInfo.populateValues();                   
        }),
        "profile" : action(function() {
            UserMenuConfig.init();                
            var currentTabPage = dijit.byId("profileForm");
            if(currentTabPage) {
                dijit.byId("profileForm").destroyRecursive();
            }
            core.ui.loadTemplate("profile", core.ui.getContentId()); 
            //                   AccountInfo.init();
            //                   AccountInfo.populateValues();
            ProfileDetail.init();
            //                   ProfileDetail.populateValues();
        }),
        "contact" : action(function() {
            UserMenuConfig.init();
            var currentTabPage = dijit.byId("contactTabContainer");
            if(currentTabPage) {
                dijit.byId("contactTabContainer").destroyRecursive();
            }
            core.ui.loadTemplate("contact", core.ui.getContentId()); 
            ContactDetail.init();
            ContactDetail.populateValues();
        }),
        "billing" : action(function() {
            UserMenuConfig.init();
            var currentTabPage = dijit.byId("billingForm");
            if(currentTabPage) {
                 dijit.byId("billingForm").destroyRecursive();
            }
            core.ui.loadTemplate("billing", core.ui.getContentId()); 
            BillingDetail.init();
            BillingDetail.populateValues();
        }),
        "email" : action(function() {
            UserMenuConfig.init();
            var currentTabPage = dijit.byId("notificationTabContainer");
            if(currentTabPage) {
                dijit.byId("notificationTabContainer").destroyRecursive();
            }
            core.ui.loadTemplate("email", core.ui.getContentId()); 
            NotificationDetail.init();
            NotificationDetail.populateValues();                  
        }),
        "logout" : action(function() {
//            var singleSignOnUrl = core.getSingleSignOnURL()+"/api";
//            dojo.xhrPost({
//                // The URL of the request
//                url: singleSignOnUrl,
//                'content': {
//                    command:"logout"
//                }
//            });
            window.location = core.getContextPath()+"/logout/index";
        })                          
    });            
    ZoneConfigForMenu.populateValue();         
});
window.UpdateTimer = UpdateTimer;
window.App = App; 
window.UserProfile = UserProfile;
window.AccountProfile = AccountProfile;
//window.UserStorage = UserStorage;
window.UserSecurityGroups = UserSecurityGroups;
window.ProfileDetail = ProfileDetail;
window.ContactDetail = ContactDetail;
window.GeneralDetail = GeneralDetail;
window.BillingDetail = BillingDetail;
window.NotificationDetail = NotificationDetail;
window.UserInfoAlert = UserInfoAlert;
window.BillingInfoAlert = BillingInfoAlert;
//window.CurrentInstanceInfo = CurrentInstanceInfo;
window.UserMenuConfig = UserMenuConfig;
window.ZoneConfigForMenu = ZoneConfigForMenu;
