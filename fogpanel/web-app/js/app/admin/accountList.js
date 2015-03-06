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
    "dojo/currency",
    "dojox/charting/plot2d/Markers",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/MultipleInvitation",
    "FogPanel/Wizard",
    "FogPanel/AccountStatus",
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
    "dijit/layout/TabContainer"
],
function(dojo, i18n, translator, dijit, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select, ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win, 
Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, Highlight, ClusteredColumns, Legend, localeCurrency) {
    window.translator = translator;            
    window.query = query;
    window.localeCurrency = localeCurrency;
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
    window.currentUserId = "";
    window.currentInvoiceId = "";
    controller({
        name: "account",
        module: "admin",
        filePath: "/js/app/admin/accountList.js",
        layout: {
            name: "account",
            containerId: "content"
        },
        scaffold: false
    },
    {
        "index": action(function() {
            if(dijit.byId("accountProcessLoader")) {
                dijit.byId("accountProcessLoader").destroyRecursive();               
            }
            if(dijit.byId("resetPasswordDialog")) {
                dijit.byId("resetPasswordDialog").destroyRecursive();
            }
            if(dijit.byId("unLockDialog")) {
                dijit.byId("unLockDialog").destroyRecursive();
            }
            if(dijit.byId("lockDialog")) {
                dijit.byId("lockDialog").destroyRecursive();
            }
            if(dijit.byId("manualPayForm")){
                dijit.byId("manualPayForm").destroyRecursive();
            }            
            core.ui.loadTemplate("accountList", core.ui.getContentId());
            AccountList.init();
            AccountList.populateValues();
        }),
        "add": action(function() {
            var addForm = dijit.byId("taxAddForm");
            if (addForm) {
                addForm.destroyRecursive();
            }
            core.ui.loadTemplate("addTax", core.ui.getContentId());
        }),
        "summary": action(function(id) {            
            if(dijit.byId("accountProcessLoader2")) {
                dijit.byId("accountProcessLoader2").destroyRecursive();
                dijit.byId("cancelDialog").destroyRecursive();
                dijit.byId("enableDialog").destroyRecursive();
                dijit.byId("disableDialog").destroyRecursive();
                dijit.byId("suspendDialog").destroyRecursive();
                dijit.byId("refundDialog").destroyRecursive();
            }
           
            if (dijit.byId("enableThresholdLimit")) {
                dijit.byId("enableThresholdLimit").destroyRecursive();
            }
            if (dijit.byId("accountSummaryDetailsTabCointainer")) {
                dijit.byId("accountSummaryDetailsTabCointainer").destroyRecursive();
            }
            if (dijit.byId("accountSummaryInfraDetailsTab")) {
                dijit.byId("accountSummaryInfraDetailsTab").destroyRecursive();
            }
            if (dijit.byId("accountSummaryInfraUpdateTab")) {
                dijit.byId("accountSummaryInfraUpdateTab").destroyRecursive();
            }
            
            if (dijit.byId("secondaryStorageLimit")) {
                dijit.byId("secondaryStorageLimit").destroyRecursive();
            }
            
            if (dijit.byId("primaryStorageLimit")) {
                dijit.byId("primaryStorageLimit").destroyRecursive();
            }
            
            if(dijit.byId("enableAccountBtn")) {
                dijit.byId("enableAccountBtn").destroyRecursive();
            }
            if(dijit.byId("vpcUpdateCreditConfirm")) {
                dijit.byId("vpcUpdateCreditConfirm").destroyRecursive();
            }                        
            if(dijit.byId("cancelAccountBtn")) {
                dijit.byId("cancelAccountBtn").destroyRecursive();
            }
            if(dijit.byId("cancelAccountBtn")) {
                dijit.byId("cancelAccountBtn").destroyRecursive();
            }
            if(dijit.byId("resourceUpdateButton")) {
                dijit.byId("resourceUpdateButton").destroyRecursive();
            }
            if(dijit.byId("refundBtn")) {
                dijit.byId("refundBtn").destroyRecursive();
            }
            if(dijit.byId("suspendAccountBtn")) {
                dijit.byId("suspendAccountBtn").destroyRecursive();
            }
            if(dijit.byId("invoiceAddForm")) {
                dijit.byId("invoiceAddForm").destroyRecursive();
            }
            if(dijit.byId("editRecurringItemForm")) {
                dijit.byId("editRecurringItemForm").destroyRecursive();
            }
            if(dijit.byId("editRecuButton")) {
                dijit.byId("editRecuButton").destroyRecursive();
            }
            if(dijit.byId("recurringItemDeleteDialog")) {
                dijit.byId("recurringItemDeleteDialog").destroyRecursive();
            }
            if(dijit.byId("updateCreditLimitDialog")) {
                dijit.byId("updateCreditLimitDialog").destroyRecursive();
            }                        
            if(dijit.byId("updateResourceLimitDialog")) {
                dijit.byId("updateResourceLimitDialog").destroyRecursive();
            }                        
            if(dijit.byId("manualPayForm")){
                dijit.byId("manualPayForm").destroyRecursive();
            }
            if(dijit.byId("paymentLoader")){
                dijit.byId("paymentLoader").destroyRecursive();
            }
            
            if (dijit.byId("retailMemoryLimit")) {
                dijit.byId("retailMemoryLimit").destroyRecursive();
            }
            
            if (dijit.byId("retailCpuLimit")) {
                dijit.byId("retailCpuLimit").destroyRecursive();
            }
            
            if (dijit.byId("retailVpcLimit")) {
                dijit.byId("retailVpcLimit").destroyRecursive();
            }
            if (dijit.byId("retailPublicIPLimit")) {
                dijit.byId("retailPublicIPLimit").destroyRecursive();
            }
            if (dijit.byId("retailSnapshotLimit")) {
                dijit.byId("retailSnapshotLimit").destroyRecursive();
            }
            if (dijit.byId("retailNetworkLimit")) {
                dijit.byId("retailNetworkLimit").destroyRecursive();
            }
            if (dijit.byId("retailStorageLimit")) {
                dijit.byId("retailStorageLimit").destroyRecursive();
            }
            if (dijit.byId("retailInstanceLimit")) {
                dijit.byId("retailInstanceLimit").destroyRecursive();
            }
            if (dijit.byId("enableThresholdLimit")) {
                dijit.byId("enableThresholdLimit").destroyRecursive();
            }
            
            if (dijit.byId("secondaryStorageLimit")) {
                dijit.byId("secondaryStorageLimit").destroyRecursive();
            }
            
            if (dijit.byId("primaryStorageLimit")) {
                dijit.byId("primaryStorageLimit").destroyRecursive();
            }
            
            if(dijit.byId("instanceLimitLoader")) {
                dijit.byId("instanceLimitLoader").destroyRecursive();
            }
            if(dijit.byId("volumeLimitLoader")) {
                dijit.byId("volumeLimitLoader").destroyRecursive();
            }
            if(dijit.byId("snapshotLimitLoader")) {
                dijit.byId("snapshotLimitLoader").destroyRecursive();
            }
            if(dijit.byId("pulicIpLimitLoader")) {
                dijit.byId("pulicIpLimitLoader").destroyRecursive();
            }
            if(dijit.byId("vpcLimitLoader")) {
                dijit.byId("vpcLimitLoader").destroyRecursive();
            }
            if(dijit.byId("networkLimitLoader")) {
                dijit.byId("networkLimitLoader").destroyRecursive();
            }

            if(dijit.byId("primaryStorageLimitLoader")) {
                dijit.byId("primaryStorageLimitLoader").destroyRecursive();
            }
            if(dijit.byId("secondaryStorageLimitLoader")) {
                dijit.byId("secondaryStorageLimitLoader").destroyRecursive();
            }
            if(dijit.byId("vcpuLimitLoader")) {
                dijit.byId("vcpuLimitLoader").destroyRecursive();
            }
            if(dijit.byId("memoryLimitLoader")) {
                dijit.byId("memoryLimitLoader").destroyRecursive();
            }
                        
            core.ui.loadTemplate("summary", core.ui.getContentId());
            ViewCurrentAccountInfo.init(id);
            ViewCurrentAccountInfo.populateValues(id);
            ResourceLimitForUser.populateValues(id);
            CurrentInvoiceInfo.init();
            PyamentsInfo.init();
            RecurringListInfo.init();
            Summary.init();
            Summary.populateValues(id); 
        }),
        "infrastructure": action(function() { 
            if(currentUserId == "" || currentUserId == undefined || currentUserId == "undefined") {
                core.router.go("#/admin/account");
            }
            core.ui.loadTemplate("infrastructure", core.ui.getContentId());
            ViewCurrentAccountInfo.init(currentUserId);
            ViewCurrentAccountInfo.populateValues(currentUserId);
            ViewCurrentAccountInfo.loadPage(currentUserId);
            InfrastructureInfo.init(currentUserId);
            InfrastructureInfo.populateValues();         
        }),
        "invoice": action(function() {
            core.ui.loadTemplate("userInvoiceInfo", core.ui.getContentId());                
            ViewCurrentAccountInfo.init(currentUserId);
            ViewCurrentAccountInfo.populateValues(currentUserId);  
            ViewCurrentAccountInfo.loadPage(currentUserId);
            CurrentInvoiceInfo.init(currentUserId);
            CurrentInvoiceInfo.populateValues(currentUserId);                
        }),
        "payment": action(function() {           
            core.ui.loadTemplate("payment", core.ui.getContentId()); 
            ViewCurrentAccountInfo.init(currentUserId);
            ViewCurrentAccountInfo.populateValues(currentUserId);
            ViewCurrentAccountInfo.loadPage(currentUserId);
            PyamentsInfo.init(currentUserId);
            PyamentsInfo.populateValues();                
        }),
        "recurringItem": action(function() {            
            core.ui.loadTemplate("currentRecurringItem", core.ui.getContentId()); 
            ViewCurrentAccountInfo.init(currentUserId);
            ViewCurrentAccountInfo.populateValues(currentUserId);
            ViewCurrentAccountInfo.loadPage(currentUserId);
            RecurringListInfo.init(currentUserId);
            RecurringListInfo.populateValues();                              
        }),
        "editRecurring" : action(function(id) {
            var editForm = dijit.byId("editRecurringItemForm");
            if(editForm) {
                editForm.destroyRecursive();
            }
            if(currentUserId == "" || currentUserId == undefined || currentUserId == "undefined") {
                core.router.go("#/admin/account/invoice");
            } 
            core.ui.loadTemplate("editRecurring", core.ui.getContentId()); 
            ViewCurrentAccountInfo.init(currentUserId);
            ViewCurrentAccountInfo.populateValues(currentUserId);                
            ViewCurrentAccountInfo.loadPage(currentUserId);
            EditCurrentRecurringItem.init(id);
            EditCurrentRecurringItem.populateValues();
        }),
        "deleteRecurring" : action(function(id) {
            core.ui.loadTemplate("currentRecurringItem", core.ui.getContentId());  
            ViewCurrentAccountInfo.init(currentUserId);
            ViewCurrentAccountInfo.populateValues(currentUserId);
            RecurringListInfo.init(currentUserId);
            RecurringListInfo.populateValues();
            DeleteCurrentRecurringItem.init(id)
            DeleteCurrentRecurringItem.populateValues();
        }) ,
        "viewInvoice" : action(function(id) {
            core.ui.loadTemplate("viewInvoice", core.ui.getContentId()); 
            if(id == "undefined" || id==undefined) {
                ViewInvoiceDetails.init(currentInvoiceId); 
            } else  if(id){
                currentInvoiceId = id;
                ViewInvoiceDetails.init(id); 
            } if(currentInvoiceId == "" || currentInvoiceId == undefined || currentInvoiceId == "undefined") {
                    core.router.go("#/admin/account/invoice");
            }              
            ViewCurrentAccountInfo.init(currentUserId);
            ViewCurrentAccountInfo.populateValues(currentUserId);
            ViewCurrentAccountInfo.loadPage(currentUserId);  
        }),
        "addInvoice" : action(function() {
            var addForm = dijit.byId("invoiceAddForm");
            if(addForm) {
                addForm.destroyRecursive();
            }
            if(dijit.byId("addItemButton")) {
                dijit.byId("addItemButton").destroyRecursive();
            }
            if(dijit.byId("invoiceRecurringItemBillingCycles")) {
                dijit.byId("invoiceRecurringItemBillingCycles").destroyRecursive()
            }
            if(currentInvoiceId == "" || currentInvoiceId == undefined || currentInvoiceId == "undefined") {
                    core.router.go("#/admin/account/invoice");
            }  
            core.ui.loadTemplate("addInvoice", core.ui.getContentId()); 
            ViewCurrentAccountInfo.init(currentUserId);
            ViewCurrentAccountInfo.populateValues(currentUserId);
            ViewCurrentAccountInfo.loadPage(currentUserId);
            AddInvoiceItem.init(currentUserId, currentInvoiceId);
        }),
        'invitation' : action(function() {            
            if(dijit.byId("invitationInfoLoader")) {
                dijit.byId("invitationInfoLoader").destroyRecursive()
            }
            if(dijit.byId("resendAddInvitationConfirmDialog")) {
                dijit.byId("resendAddInvitationConfirmDialog").destroyRecursive()
            }
            
            core.ui.loadTemplate("invitation", core.ui.getContentId()); 
            InvitationInfo.populateValues();
        }),
        'addInvitation' : action(function() {
            if(dijit.byId("addInvitationForm")) {
                dijit.byId("addInvitationForm").destroyRecursive()
            }
            if(dijit.byId("invitationLoader")) {
                dijit.byId("invitationLoader").destroyRecursive()
            }
            if(dijit.byId("adminAddInvitationConfirmDialog")) {
                dijit.byId("adminAddInvitationConfirmDialog").destroyRecursive()
            }
            
            core.ui.loadTemplate("addInvitation", core.ui.getContentId()); 
            AddInvitationInfo.populateValues();
        })
        
    });
});
var InvitationInfo = {
    resetMail : function () {
        dijit.byId("invitationInfoLoader").show();
        dijit.byId("resendAddInvitationConfirmDialog").hide();
        var updateInvitationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/invitation/update"
        });
        var currentID = dojo.byId("currentInvitationID").value;
        updateInvitationRestStore.add({
           invitationId :  currentID
        }).then(function (resultData) {
            dijit.byId("invitationInfoLoader").hide();              
            if(resultData[0].result === "OK") {
                registry.byId("appToaster").setContent(translator.common.resendInvitationSucess,"message");
                registry.byId("appToaster").show();
                 InvitationInfo.populateValues();
            } else {
                registry.byId("appToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                registry.byId("appToaster").show();
            }
        });
    },
    resetconfirmMail : function (currentID) {
        dojo.byId("currentInvitationID").value = currentID;
        dijit.byId("resendAddInvitationConfirmDialog").show();
    },
    cancelDialogu : function () {
        dijit.byId("invitationInfoLoader").hide();
        dijit.byId("resendAddInvitationConfirmDialog").hide();
    },
    populateValues : function () {
        if(dijit.byId("invitationGridWidget")) {
            dijit.byId("invitationGridWidget").destroyRecursive();
        }
        var InvitationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/invitation/"
        });
        
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [
            [   
                {'field': 'id', datatype:"string",  autoComplete: true, 'hidden': true},
                {'name': translator.common.email, 'field': 'userName', 'width': '300px', datatype:"string",  autoComplete: true},
                {'name': translator.user.userName, 'field': 'name', 'width': '250px', datatype:"string",  autoComplete: true},  
                {'name': translator.common.state, 'field': 'stat', 'width': '150px', datatype:"string",  autoComplete: true},  
                {'name': translator.common.type, 'field': 'type', 'width': '80px', datatype:"string",  autoComplete: true},  
                {'name': translator.common.createdDate, 'field': 'createdDate', 'width': '200px', datatype:"string",  autoComplete: true},                  
                {'name': 'Action', 'field': 'action', 'width': '100%', 'formatter': function(data) {      
                        var htmlElement = ""
                        if(data.stat === "in progress") {
                            htmlElement = "<a class='offset4' title="+translator.common.resend+">" + "<img height='20' width='20' onclick='InvitationInfo.resetconfirmMail(\"" + data.id + "\")' src='images/popup-icons/vm_reset_icon.png'/>" + "</a>"
                        } else {
                            htmlElement = ""
                        }
                        return htmlElement;
                    }
                }
            ]
        ];          
        InvitationRestStore.query().then(function(data) {            
            if(data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === ' ' || data === 'null' || data === null) {
                dojo.byId("noInvitationMessageBox").style.display = "block";
                dojo.byId("invitationGridList").innerHTML = "";
            } else {
                dojo.byId("noInvitationMessageBox").style.display = "none";            
                dojo.forEach(data,function(invitation) { 
                    gridDataList.newItem({
                        id: invitation.id,                    
                        userName: invitation.email,
                        name: invitation.name,
                        stat: invitation.status,
                        type: invitation.type,
                        createdDate: invitation.createdDate,                        
                        action: {id: invitation.id, stat: invitation.status}
                    });
                });
                dojo.byId("invitationGridList").innerHTML = "";
                var invitationGrid = new EnhancedGrid({
                    id: 'invitationGridWidget',
                    "class":"span12",
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });                
                invitationGrid.placeAt("invitationGridList");
                invitationGrid.startup();  
            }
    });
}
};

var AddInvitationInfo = {
    populateValues : function () {
        var multipleInvitationWidget = new FogPanel.MultipleInvitation({
            onUserNameChange : function (currentWidget, widget) {
                 AddInvitationInfo.validateUserName(currentWidget, widget);
            }
        })
        multipleInvitationWidget.placeAt("multipleInvitationDiv");        
    },
    addMore : function () {
        var multipleInvitationWidget = new FogPanel.MultipleInvitation({
            deleteOption : true,
            onUserNameChange : function (currentWidget, widget) {
                 AddInvitationInfo.validateUserName(currentWidget, widget);
            }
        })
        multipleInvitationWidget.placeAt("multipleInvitationDiv");   
    },
    validateUserName : function (currentEmail, currentWidget) {         
        var node = dojo.byId("multipleInvitationDiv");
        var widget = dijit.registry.findWidgets(node);   
        currentEmail.set("regExp", dojox.validate.regexp.emailAddress);      
        dojo.forEach(widget, function (el) {                                                  
            var ValidateInvitationRestStore = new JsonRest({
                target: core.getContextPath()+"/api/account/invitation/user/validate"
            });
            ValidateInvitationRestStore.query({userName: currentEmail.value}).then(function (data) {  
                currentEmail.validator = function () {
                    if(data[0].result === "invalid") {
                        currentEmail.set("invalidMessage", translator.common.invalidUser);   
                        dijit.byId("addInvitationButton").set("disabled", true);
                        return false;
                    } else if(data[0].result === "exist")  {
                        dijit.byId("addInvitationButton").set("disabled", true);
                        currentEmail.set("invalidMessage",  translator.common.userExist);
                        return false;
                    } else if(el.getFieldsValue().userName === currentEmail.value &&  el.id !== currentWidget.id) {
                        dijit.byId("addInvitationButton").set("disabled", true);                        
                        currentEmail.set("invalidMessage", translator.common.duplacateUserMsg);
                        return false;
                    } else {
                        dijit.byId("addInvitationButton").set("disabled", false);
                        return true;
                    }                    
                }                                             
            });                        
        })    
    },   
    cancelDialogu:  function () {
        dijit.byId("invitationLoader").hide();       
        dijit.byId("adminAddInvitationConfirmDialog").hide(); 
    },
    sendInvitation : function () {
        var node = dojo.byId("multipleInvitationDiv");
        var widget = dijit.registry.findWidgets(node);
        var invitationInfo = [];
        dojo.forEach(widget, function (el) {     
            var currentInvitation = el.getFieldsValue();                    
            invitationInfo.push({
                email: currentInvitation.email,
                userName: currentInvitation.userName,
                signupType: currentInvitation.signupType
            });
        });
        var sendInvitationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/invitation/send"
        });
        dijit.byId("invitationLoader").show();       
        dijit.byId("adminAddInvitationConfirmDialog").hide();      
        sendInvitationRestStore.add({
            invitationInfo : invitationInfo
        }).then(function (resultData) {
            dijit.byId("invitationLoader").hide();              
            if(resultData[0].result === "OK") {
                registry.byId("appToaster").setContent(translator.common.ivitationMailSucess,"message");
                registry.byId("appToaster").show();
                core.router.go("#/admin/account/invitation");
            } else if(resultData[0].localizedMessage === "duplicateEmail") {
                registry.byId("appToaster").setContent(translator.common.duplacateUserMsg, "error");
                registry.byId("appToaster").show();
            } else if(resultData[0].localizedMessage === "promoCodeMissing") {
                registry.byId("appToaster").setContent(translator.common.promoCodeMissing, "error");
                registry.byId("appToaster").show();
            } else {
                registry.byId("appToaster").setContent(translator.common.cloudStack.errorMessage,"error");
                registry.byId("appToaster").show();
            }
        });    
    },
    addInvitationConfirm : function () {                   
        var node = dojo.byId("multipleInvitationDiv");
        var widget = dijit.registry.findWidgets(node);            
        var overallStat = true;      
        var stat = true;         
        dojo.forEach(widget, function (el, count) {                                          
            if(el.validateFields() === false) {                
                overallStat = false;
            }
            var ValidateInvitationRestStore = new JsonRest({
                target: core.getContextPath()+"/api/account/invitation/user/validate"
            });                 
            ValidateInvitationRestStore.query({userName: el.getFieldsValue().userName}).then(function (data) {  
                var currentUser = el.getCurrentUserNameWidget();                  
                if(data[0].result === "invalid") {                     
                    currentUser.set("invalidMessage", translator.common.invalidUser);   
                    dojo.query("#invitationErrorMsg").toggleClass("hide_text", true);
                    stat = false;                    
                } else if(data[0].result === "exist") {                                                           
                    dojo.query("#invitationErrorMsg").removeClass("hide_text", true);
                    currentUser.set("invalidMessage",  translator.common.userExist);
                    currentUser.reset();
                    stat = false;                    
                } else {                         
                    stat = true;
//                    dojo.query("#invitationErrorMsg").toggleClass("hide_text", true);                    
                }                            
            });  
            
            dojo.forEach(widget, function (element, index) {
                if(el.getFieldsValue().userName === element.getFieldsValue().userName && count !== index) {
                    dojo.query("#invitationErrorMsg").removeClass("hide_text", true);
                    el.getCurrentEmailWidget().set("invalidMessage",  translator.common.userExist);
                    stat = false;  
                    return ;
                } 
            });
        });                  
        
        
        if(overallStat === true && stat === true) {                        
            dijit.byId("adminAddInvitationConfirmDialog").show();            
            dojo.query("#invitationErrorMsg").toggleClass("hide_text", true);                                                                                
        }
    }
}
var Invitation = {    
    'init': function() {                        
        var gridData = {            
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [
            [
                {'name': translator.user.userName, 'field': 'userName', 'width': '50%', datatype:"string",  autoComplete: true},
                {'name': 'Action', 'field': 'action', 'width': '50%', 'formatter': function(data) {                    
                        return new dijit.form.Button({label: translator.common.resend, 
                            "class":"defaultbtn", onClick: function () {
                                Invitation.showResendInvitation(data);
                            }
                        });                             
                    }
                }
            ]
        ];  
        
        var invitationService = new JsonRest({
            target: core.getContextPath()+"/api/account/invitation"
        });
        invitationService.query().then(function(data) {
            
            if(data.length === 0 || data === undefined || data === 'undefined' || data === '' || data === ' ' || data === 'null' || data === null) {
                dojo.byId("noInvitationMessageBox").style.display = "block";
                dojo.byId("invitationGridList").innerHTML = "";
            } else {
                dojo.byId("noInvitationMessageBox").style.display = "none";
                dojo.forEach(data,function(invitation) { 
                     gridDataList.newItem({
                        id: invitation.id,
                        userName: invitation.userName,
                        action: invitation.userName

                    });
                });
                dojo.byId("invitationGridList").innerHTML = "";
                var invitationGrid = new EnhancedGrid({
                    id: 'invitationGridWidget',
                    "class":"span12",
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });                
                invitationGrid.placeAt("invitationGridList");
                invitationGrid.startup();  
            }
        });
    },
    'populateValues': function() {       
        
    },
    'showResendInvitation': function() {       
         dijit.byId("resendInvitationDialog").show();
    },
    'cancelResendInvitation': function() {       
         dijit.byId("resendInvitationDialog").hide();
    },
    'resendInvitation' : function(invitationEmail) {
        var invitationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/invitation"
        });   
        
        invitationRestStore.add({
            email: invitationEmail
        }).then(function(resultData) {
            if (resultData[0] === "OK") {
                registry.byId('appToaster').setContent(translator.common.message.invitationSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
        
    },
    'sendInvitation' : function() {
         var invitationRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/invitation"
        });   
        
        var invitationEmail = dijit.byId("invitationEmail").value;
        
        invitationRestStore.add({
            email: invitationEmail
        }).then(function(resultData) {
            if (resultData[0] === "OK") {
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
            }
        });
        
    }
    
};

var Summary = {
    _invoiceStore: "",
    _paymentStore: "",
    init: function() {                
        this._invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/chart/"
        });
        
        this._paymentStore = new JsonRest({
            target: core.getContextPath()+"/api/payment/chart/"
        });
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("summaryCurrencyValue").innerHTML= cur.currency;
               });
        });
    },            
    populateAccountStatus :function(id) {             
        var accountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });        
        accountRestStore.get(id).then(function(reponseData) {
            dojo.forEach(reponseData, function(data) {
                if(data.invoice == "yes") {
                    dijit.byId("refundBtn").set("disabled", false);
                } else {
                    dijit.byId("refundBtn").set("disabled", true);                     
                }                 
            });             
        });                
        accountRestStore.get(id).then(function(reponseData) {
            dojo.forEach(reponseData, function(data) {
                if(data.status.name == "ACTIVE") {                    
                    dijit.byId("enableAccountBtn").set("disabled", true);
                    dijit.byId("cancelAccountBtn").set("disabled", false);
                    dijit.byId("suspendAccountBtn").set("disabled", false);
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.ACTIVE;
                } else if(data.status.name == "DISABLED") {                    
                    dijit.byId("enableAccountBtn").set("disabled", true);
                    dijit.byId("cancelAccountBtn").set("disabled", false);
                    dijit.byId("suspendAccountBtn").set("disabled", true);        
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.DISABLED;
                } else if(data.status.name == "SUSPENDED") {                    
                    dijit.byId("enableAccountBtn").set("disabled", false);
                    dijit.byId("cancelAccountBtn").set("disabled", false);
                    dijit.byId("suspendAccountBtn").set("disabled", true);                    
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.SUSPENDED;
                }else if(data.status.name == "CANCELED") {                    
                    dijit.byId("enableAccountBtn").set("disabled", false);
                    dijit.byId("cancelAccountBtn").set("disabled", true);
                    dijit.byId("suspendAccountBtn").set("disabled", true);                   
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.CANCELED;                    
                    dijit.byId("refundAmount").set("value", data.totalPayable * -1);   
                    dijit.byId("refundAmount").set("disabled", true);                                                         
                } else {  
                    var currentStatus = "";                                                          
                    if(data.status.name == "BLOCKED") {
                        currentStatus = translator.common.account.status.BLOCKED
                    } else if (data.status.name == "LOCKED") {
                        currentStatus = translator.common.account.status.LOCKED
                    } else if (data.status.name == "NOT_VERIFIED") {
                        currentStatus = translator.common.account.status.NOT_VERIFIED
                    } else if (data.status.name == "CLOSED") {
                        currentStatus = translator.common.account.status.CLOSED
                    } else {
                        currentStatus = ""
                    }
                    dijit.byId("enableAccountBtn").set("disabled", true);
                    dijit.byId("cancelAccountBtn").set("disabled", true);
                    dijit.byId("suspendAccountBtn").set("disabled", true);                    
                    dojo.byId("currentAcStatus").innerHTML = currentStatus;
                }
            });
        });
    },            
    populateValues: function(id) {        
        dojo.byId("currentAccountId").value = id;        
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
        var i = 1;
        this._invoiceStore.query({accountId: id}).then(function(data) {
            dojo.forEach(data, function(invoiceData) {
                invoiceChartXAxisData.push({value: i, text: invoiceData.month}); 
                invoiceChartData.push(invoiceData.totalAmount);
//                paymentChartData.push(invoiceData.payment);
                creditLimitChartData.push(invoiceData.creditLimit);
                customItemChartData.push(invoiceData.customItemTotal);
                recurringItemChartData.push(invoiceData.recurringItemTotal);
                i++;
            });
            
            
            if(dijit.byId("legend")) {
              dijit.byId("legend").destroyRecursive();
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
            invoiceChart.addAxis("y", { vertical: true, fixLower: "major", fixUpper: "major" , min:0});

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
            customItemChart.addSeries("CustomItem",customItemChartData);
            customItemChart.addSeries("RecurringItem",recurringItemChartData);

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
                supportChartData.push(payData.open);
                supportChartData.push(payData.inProgress);
                supportChartData.push(payData.onHold);
                supportChartData.push(payData.close); 
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

    //         Add the series of data
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
                            
        });
        
        
        var paymentStore = new JsonRest({
                target: core.getContextPath()+"/api/payment/chart/month"
        });
        var j = 1;
        paymentStore.query({accountId: id}).then(function(data) {
            dojo.forEach(data, function(payData) {
                paymentChartXAxisData.push({value: j, text: payData.month}); 
                paymentChartData.push(payData.payment);
                j++;
            });
            
            // Create the chart within it's "holding" node
            var paymentChart = new dojox.charting.Chart("paymentSummaryChart");
            // Set the theme
            paymentChart.setTheme(theme);

            // Add the only/default plot
            paymentChart.addPlot("default", {
                type: ColumnsPlot,
                markers: true,
                gap: 5
            });

            // Add axes
            paymentChart.addAxis("x", {labels: paymentChartXAxisData});
            paymentChart.addAxis("y", { vertical: true, fixLower: "major", fixUpper: "major" , min:0});

            // Add the series of data
            paymentChart.addSeries("Monthly Sales", paymentChartData);

            // Highlight!
            new Highlight(paymentChart,"default");
            
            //ToolTip
            new Tooltip(paymentChart,"default");
            
            // Render the chart!
            paymentChart.render(); 
                
        });
        
        
        var accountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        
        accountRestStore.get(id).then(function(reponseData) {
             dojo.forEach(reponseData, function(data) {
                if(data.refund == "yes") {
                    dijit.byId("refundBtn").set("disabled", false);
//                    dojo.byId("refund").style.display = "block";
                } else {
                    dijit.byId("refundBtn").set("disabled", true);
//                    dojo.byId("refund").style.display = "none";
                }
                 
             });
             
         });
        
        
        accountRestStore.get(id).then(function(reponseData) {
            dojo.forEach(reponseData, function(data) {    
                 
                if(data.status.name == "ACTIVE") {                                        
                    dijit.byId("enableAccountBtn").set("disabled", true);
                    dijit.byId("cancelAccountBtn").set("disabled", false);
                    dijit.byId("suspendAccountBtn").set("disabled", false);               
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.ACTIVE;                  
                } else if(data.status.name == "DISABLED") {                    
                    dijit.byId("enableAccountBtn").set("disabled", true);
                    dijit.byId("cancelAccountBtn").set("disabled", false);
                    dijit.byId("suspendAccountBtn").set("disabled", true);
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.DISABLED;
                } else if(data.status.name == "SUSPENDED") {                    
                    dijit.byId("enableAccountBtn").set("disabled", false);
                    dijit.byId("cancelAccountBtn").set("disabled", false);
                    dijit.byId("suspendAccountBtn").set("disabled", true); 
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.SUSPENDED;
                } else if(data.status.name == "CANCELED") {                    
                    dijit.byId("enableAccountBtn").set("disabled", false);
                    dijit.byId("cancelAccountBtn").set("disabled", true);
                    dijit.byId("suspendAccountBtn").set("disabled", true);                    
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.CANCELED;       
                    dijit.byId("refundAmount").set("value", data.totalPayable * -1);   
                    dijit.byId("refundAmount").set("disabled", true);
                } else {        
                    
                    var currentStatus = "";                                                          
                    if(data.status.name == "BLOCKED") {
                        currentStatus = translator.common.account.status.BLOCKED
                    } else if (data.status.name == "LOCKED") {
                        currentStatus = translator.common.account.status.LOCKED
                    } else if (data.status.name == "NOT_VERIFIED") {
                        currentStatus = translator.common.account.status.NOT_VERIFIED
                    } else if (data.status.name == "CLOSED") {
                        currentStatus = translator.common.account.status.CLOSED
                    } else {
                        currentStatus = ""
                    }
                    
                    dijit.byId("enableAccountBtn").set("disabled", true);
                    dijit.byId("cancelAccountBtn").set("disabled", true);
                    dijit.byId("suspendAccountBtn").set("disabled", true);                    
                    dojo.byId("currentAcStatus").innerHTML = currentStatus;
                }
            });
        });
             
    },
    showCancelAccountDialog : function() {
        dijit.byId("cancelDialog").show();
    },
    cancelAccount : function() {
        dijit.byId("accountProcessLoader2").show();
        var accountId =  dojo.byId("currentAccountId").value;
        
        var cancelAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/cancelAccount"
        });
        
        cancelAccountStore.add({
                accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader2").hide();  
                    dijit.byId("cancelDialog").hide();
                    Summary.populateAccountStatus(accountId);
                    registry.byId("appToaster").setContent(translator.common.account.accountCancelled, "message");
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/account");
                } else {
                    dijit.byId("accountProcessLoader2").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountCancelError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeCancelAccountDialog : function() {
        dijit.byId("cancelDialog").hide();
    },
    showEnableAccountDialog : function() {
        dijit.byId("enableDialog").show();        
    },
    enableAccount : function() {               
        dijit.byId("accountProcessLoader2").show();
        var accountId =  dojo.byId("currentAccountId").value;
        
        var enableAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/enableAccount"
        });
        
        enableAccountStore.add({
                accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader2").hide();  
                    dijit.byId("enableDialog").hide();
                    Summary.populateAccountStatus(accountId);
                    registry.byId("appToaster").setContent(translator.common.account.accountEnabled, "message");
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/account");
                } else {
                    dijit.byId("accountProcessLoader2").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountEnabledError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeEnableAccountDialog : function() {
        dijit.byId("enableDialog").hide();
    },
    showDisableAccountDialog : function() {
        dijit.byId("disableDialog").show();
    },
    disableAccount : function() {
        dijit.byId("accountProcessLoader2").show();
        var accountId =  dojo.byId("currentAccountId").value;
        
        var disableAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/disableAccount"
        });
        
        disableAccountStore.add({
                accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader2").hide();  
                    dijit.byId("disableDialog").hide();
                    Summary.populateAccountStatus(accountId);
                    registry.byId("appToaster").setContent(translator.common.account.accountDisabled, "message");
                    registry.byId("appToaster").show();
                    core.router.go("#/admin/account");
                } else {
                    dijit.byId("accountProcessLoader2").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountDisabledError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeDisableAccountDialog : function() {
        dijit.byId("disableDialog").hide();
    },
    showSuspendAccountDialog : function() {
        dojo.byId("suspendTextContent").value = "";
        dijit.byId("suspendDialog").show();
    },
    suspendAccount : function() {
        
        var accountId =  dojo.byId("currentAccountId").value;
        var suspendTextContent =  dojo.byId("suspendTextContent").value;
        
        var suspendAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/suspendAccount"
        });
        
        if(suspendTextContent == "" || suspendTextContent == null || suspendTextContent == "null") {
            registry.byId("appToaster").setContent(translator.common.message.enterContent, "error");
            registry.byId("appToaster").show();
        } else {
            dijit.byId("accountProcessLoader2").show();
            suspendAccountStore.add({
                    accountId: accountId,
                    suspendTextContent: suspendTextContent 
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        dijit.byId("accountProcessLoader2").hide();  
                        dijit.byId("suspendDialog").hide();
                        Summary.populateAccountStatus(accountId);
                        registry.byId("appToaster").setContent(translator.common.account.accountSuspended, "message");
                        registry.byId("appToaster").show();
                        core.router.go("#/admin/account");
                    } else {
                        dijit.byId("accountProcessLoader2").hide();
                        registry.byId("appToaster").setContent(translator.common.account.accountSuspendError, "error");
                        registry.byId("appToaster").show();
                    }
                });                
            }); 
        }
        
    },
    closeSuspendAccountDialog : function() {
        dijit.byId("suspendDialog").hide();
    },
    showRefundAccountDialog : function() {
        
        dijit.byId("refundAmount").reset();
        dijit.byId("refundDescription").reset();
        
        var id = dojo.byId("currentAccountId").value;
        
        var accountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        
        accountRestStore.get(id).then(function(reponseData) {
            dojo.forEach(reponseData, function(data) {

                if(data.status.name == "CANCELED") {
                    dijit.byId("refundAmount").set("value", data.totalPayable * -1);   
                    dijit.byId("refundAmount").set("disabled", true);
                }
                
            }); 
         });
        
        dijit.byId("refundDialog").show();
    },
    'showInfraDetailsTab' : function() {
        
//        dijit.byId("updateResourceLimitDialog").show();
        ResourceLimitForUser.populateValues(dojo.byId("currentAccountId").value);
        
    },
    showResourceUpdateTab : function() {
        
//        dijit.byId("updateResourceLimitDialog").show();
        ResourceLimitForUser.populateValues(dojo.byId("currentAccountId").value);
        
    },
    'openInfraLimitDetailsTab' : function() {
        var mainTab = dijit.byId("accountSummaryDetailsTabCointainer"); //Tr
        var subIpTab = dijit.byId("accountSummaryInfraDetailsTab"); //tab Id which you want to show
        mainTab.selectChild(subIpTab);   
    },
    'openInfraLimitUpdateTab' : function() {
        var mainTab = dijit.byId("accountSummaryDetailsTabCointainer"); //Tr
        var subIpTab = dijit.byId("accountSummaryInfraUpdateTab"); //tab Id which you want to show
        mainTab.selectChild(subIpTab);   
    },
    refundAccount : function() {
        
        var accountId =  dojo.byId("currentAccountId").value;
        
        var amount = dijit.byId("refundAmount").getValue();
        var description = dijit.byId("refundDescription").getValue();
        
        var refundStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/refund"
        });
                
        var pageNode = dojo.byId("refundFormPage");
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
        
            dijit.byId("accountProcessLoader2").show();
            refundStore.add({
                    accountId: accountId,
                    amount: amount,
                    description: description
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        dijit.byId("accountProcessLoader2").hide();  
                        dijit.byId("refundDialog").hide();
                        Summary.populateAccountStatus(accountId);
                        registry.byId("appToaster").setContent(translator.common.account.amountRefunded, "message");
                        registry.byId("appToaster").show();
                        window.location.reload();
                    } else {
                        dijit.byId("accountProcessLoader2").hide();
                        registry.byId("appToaster").setContent(translator.common.account.amountRefundError, "error");
                        registry.byId("appToaster").show();
                    }
                });                
            });
        }   
    },
    closeRefundAccountDialog : function() {
        dijit.byId("refundDialog").hide();
    }
};

var InfrastructureInfo = {
    _currentUserId: "",
    _vmRestStore: "",
    init: function(id) {
        this._currentUserId = id;
        
        this._instanceCountRestStore = new JsonRest({
                target: core.getContextPath()+"/api/virtualMachine/count"
        });
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("infraCurrencyValue").innerHTML= cur.currency;
               });
        });
    },
    populateValues: function() {
        this._vmRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/count"
        });
        
        var currentUserId = dojo.byId("currentAccountId").value;
        
        this._vmRestStore.query({accountId: currentUserId}).then(function(data) {
            dojo.forEach(data, function(resultData) {                                 
                dojo.byId("vmRunningCount").innerHTML = resultData.runningVms;
                dojo.byId("vmStopCount").innerHTML = resultData.stoppedVms;  
                dojo.byId("diskCount").innerHTML = resultData.totalStorage;  
                dojo.byId("snapCount").innerHTML = resultData.totalSnapshots;         
            });
        });
        
        var accountListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        accountListRestStore.get(currentUserId).then(function(reponseData) {
            dojo.forEach(reponseData, function(data) {
                dojo.byId("usageCost").innerHTML =  localeCurrency.format(data.daily);
                dojo.byId("usagePeriod").innerHTML = data.dailyDate;
                dojo.byId("currentDue").innerHTML =  localeCurrency.format(data.currentDue);
                dojo.byId("Payments").innerHTML =  localeCurrency.format(data.payments);
                dojo.byId("PaymentPeriod").innerHTML = data.paymentPeriod;
                dojo.byId("currentDuePeriod").innerHTML = data.paymentPeriod;
                
                dojo.byId("vmRunningAmount").innerHTML = data.currency +" "+ localeCurrency.format(data.runningVmCost); 
                dojo.byId("vmStopAmount").innerHTML =  data.currency +" "+ localeCurrency.format(data.stopVmCost);
                dojo.byId("diskAmount").innerHTML =  data.currency +" "+ localeCurrency.format(data.totalDiskCost); 
                dojo.byId("snapAmount").innerHTML =  data.currency +" "+ localeCurrency.format(data.totalSnapCost);
                dojo.byId("bandWidthCount").innerHTML = data.totalBandwidth + translator.common.gb;  
                dojo.byId("bandWidthAmount").innerHTML =  data.currency +" "+ localeCurrency.format(data.totalBandwidthCost);
                
                dojo.byId("usageCurrency").innerHTML =  data.currency;
                dojo.byId("dueCurrency").innerHTML =  data.currency;   
                dojo.byId("paymentCurrency").innerHTML =  data.currency;   
                
            });
        });         
    }
};

var DeleteCurrentRecurringItem = {
    _currentId: "",
    _recurringItemStore: "",
    init: function(currentId) {
        this._currentId = currentId;
        this._recurringItemStore = new JsonRest({
            target: core.getContextPath()+"/api/recurringItem/"
        });
    },
    populateValues: function() {
        
        dijit.byId("recurringItemDeleteDialog").hide();
        
        var recurringItemStore = new JsonRest({
            target: core.getContextPath()+"/api/recurringItem/"
        });
        
        recurringItemStore.remove(dojo.byId("currentRecurringDeleteItemId").value).then(function(data) {
            if (data == "OK") {
                registry.byId("appToaster").setContent(translator.common.message.ItemDeleted, "message");
                registry.byId("appToaster").show();
                RecurringListInfo.populateValues();
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();
            }
        });
    },
    showDeleteRecurringItem : function(currentRecurringItemId) {
        dijit.byId("recurringItemDeleteDialog").show();
        dojo.byId("currentRecurringDeleteItemId").value = currentRecurringItemId;
    },
    deleteItemClose : function() {
        dijit.byId("recurringItemDeleteDialog").hide();
    }
};

var EditCurrentRecurringItem = {
    _currentId: "",
    _recurringItemStore: "",
    init: function(currentId) {
        this._currentId = currentId;
        this._recurringItemStore = new JsonRest({
            target: core.getContextPath()+"/api/recurringItem/"
        });

    },
    backToRecurringItemList: function() {
        dojo.byId("currentRecurringItemEditPage").style.display = "none";
        RecurringListInfo.populateValues();
        
    },
    populateValues: function(currentRecurringItemId) {
        
        dojo.byId("currentRecurringItemEditPage").style.display = "block";
        dojo.byId("currentRecurringItemListPage").style.display = "none";
        
        dojo.byId("currentRecurringItemId").value = currentRecurringItemId;
        
        var recurringItemStore = new JsonRest({
            target: core.getContextPath()+"/api/recurringItem/"
        });
        
        recurringItemStore.get(currentRecurringItemId).then(function(resultData) {
            dojo.forEach(resultData, function(data) {
                dojo.byId("currentRecurringItemAccountName").innerHTML = data.account;
                dijit.byId("currentRecurringItemName").setValue(data.name);
                dijit.byId("currentRecurringItemAmount").setValue(data.amount);
                dijit.byId("currentRecurringItemTaxPercentage").setValue(data.taxPercent);
            });
        });
    },
    update: function() {
        var name = dijit.byId("currentRecurringItemName");
        var amount = dijit.byId("currentRecurringItemAmount");
        var taxPercentage = dijit.byId("currentRecurringItemTaxPercentage");
        var node = dojo.byId("editRecurringItemPage");
        var nodeWidget = dijit.registry.findWidgets(node);
        var firstNode = "";
        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if(firstNode) {
            firstNode.focus();
        } else {
            dojo.query("#editRecurringItemLoader").removeClass("hide_text", true);
            dijit.byId("editRecuButton").setAttribute('style', 'display: none');
            var recurringItemUpdateStore = new JsonRest({
                target: core.getContextPath()+"/api/recurringItem/"
            });        
            recurringItemUpdateStore.put({
                id: dojo.byId("currentRecurringItemId").value,
                amount: amount.value,
                name: name.value,
                taxPercentage: taxPercentage.value
            }).then(function(data) {
                dojo.forEach(data, function(recurringItemData) {
                    dojo.query("#editRecurringItemLoader").toggleClass("hide_text", true);
                    dijit.byId("editRecuButton").setAttribute('style', 'display: block');
                    if (recurringItemData.result == "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.updateSuccess, "message");
                        registry.byId("appToaster").show();
                        RecurringListInfo.populateValues();
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("updateRecurringItemForm").reset();
                    }
                });
            });            
        }        
    },
    cancel: function() {
        core.router.go("#/admin/account/recurringItem");
    }
};

var RecurringListInfo = {
    _recurringRestore: "",
    init: function(currentUserId) {
        this._currentAccountId = currentUserId;
        this._recurringRestore = new JsonRest({
            target: core.getContextPath()+"/api/recurringItem/"
        });
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("itemCurrencyValue").innerHTML= cur.currency;
               });
        });
    },
    populateValues: function() {
        
        dojo.byId("currentRecurringItemListPage").style.display = "block";
        dojo.byId("currentRecurringItemEditPage").style.display = "none";
        
        if (dijit.byId("currentUserRecurringList")) {
            dijit.byId("currentUserRecurringList").destroyRecursive();
        }
        var recurringData = {
            items: []
        };
        
        var currentUserId = dojo.byId("currentAccountId").value;
        
        dojo.byId("userRecurringListItems").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var recurringDataList = new ItemFileWriteStore({data: recurringData});
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        var recurringDataLayout = "";
        configRestStore.query().then(function (currencyResult) {
            recurringDataLayout = [
                [
                    {'field': 'id', 'hidden': 'true'},
                    {'name': translator.common.name, 'field': 'name', 'width': '150px'},
                    {'name': translator.common.type, 'field': 'type', 'width': '150px'},
                    {'name': translator.common.amount + " ("+ currencyResult[0].currency +")", 'field': 'amount', 'width': '150px'},
                    {'name': translator.common.billing.taxPercentage, 'field': 'taxPercentage', 'width': '100px'},
                    {'name': translator.common.taxAmount + " ("+ currencyResult[0].currency+")", 'field': 'taxAmount', 'width': '100px'},
                    {'name': translator.common.billing.grid.layout.totalAmount + " ("+ currencyResult[0].currency +")", 'field': 'totalAmount', 'width': '150px', 'formatter': function(data) {                         

                            return  "<span class='orangeColor'>" + data + "</span>";
                        }
                    },    
                    {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter': function(data) {

                            if(data.type == "Recurring") {
                                var html = "<a onclick='EditCurrentRecurringItem.populateValues(\"" + data.id + "\")' title='"+translator.common.edit+"'><i class='icon-edit'></i></a>"+
                                "<a onclick='DeleteCurrentRecurringItem.showDeleteRecurringItem(\"" + data.id + "\")' title='"+translator.common.deleteData+"' class='offset1'><i class='icon-remove'></i></a></a";                                                    
                            } else {
                                html = "";
                            }
                            return html; 
                        }
                    }
                ]
            ];
        });
        this._recurringRestore.query({accountId: currentUserId}).then(function(data) {
            if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == ' ' || data == 'null' || data == null) {
                dojo.byId("noRecurringItemMessageBox").style.display = "block";
                dojo.byId("userRecurringListItems").innerHTML = "";                        
            } else {
                dojo.forEach(data, function(reccuringData) {
                    recurringDataList.newItem({
                        id: reccuringData.id,
                        name: reccuringData.name,
                        type: reccuringData.type,
                        amount: localeCurrency.format(reccuringData.amount),
                        taxPercentage: reccuringData.taxPercent,
                        taxAmount: localeCurrency.format(reccuringData.taxAmount),
                        totalAmount: localeCurrency.format(reccuringData.totalAmount),
                        action: {'id': reccuringData.id, type: reccuringData.type}
                    });
                });   
                dojo.byId("userRecurringListItems").innerHTML = "";
                 dojo.byId("noRecurringItemMessageBox").style.display = "none";
                var recurringDataGrid = new EnhancedGrid({
                    id: 'currentUserRecurringList',      
                    "class": "span12",
                    store: recurringDataList,
                    structure: recurringDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                recurringDataGrid.placeAt("userRecurringListItems");
                recurringDataGrid.startup();
            }                       
        });        
    }
};

var PyamentsInfo = {
    _paymentRestore: "",
    _currentAccountId: "",
    init: function(currentUserId) {
        dojo.byId("paymentTotalDataDiv").style.display = "block";
        dojo.byId("manualPaymentDiv").style.display = "none";
        this._currentAccountId = currentUserId;
        this._paymentRestore = new JsonRest({
            target: core.getContextPath()+"/api/payment/"
        });
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("paymentCurrencyValue").innerHTML= cur.currency;
               });
        });
    },
    populateValues: function() {
        var currentUserId = dojo.byId("currentAccountId").value;
        
        
        dojo.byId("paymentTotalDataDiv").style.display = "block";
        dojo.byId("manualPaymentDiv").style.display = "none";
        
        var accountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        dijit.byId("TransDate").constraints.max = new Date();
        accountRestStore.get(currentUserId).then(function(reponseData) {
            dojo.forEach(reponseData, function(data) {

                if(data.invoice == "yes") {
                    dojo.byId("addPaymentBtn").style.display = "block";
                    dojo.byId("noInvoiceMsgBox").style.display = "none";
                } else {
                    dojo.byId("addPaymentBtn").style.display = "none";
                    dojo.byId("noInvoiceMsgBox").style.display = "block";
                }                 
            });             
        });
         
        if (dijit.byId("currentUserPaymentList")) {
            dijit.byId("currentUserPaymentList").destroyRecursive();
        }
        var paymentData = {
            items: []
        };              
        
        dojo.byId("paymentGrid").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var paymentDataList = new ItemFileWriteStore({data: paymentData});
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        var paymentDataLayout = "";
        configRestStore.query().then(function (currencyResult) {
            paymentDataLayout = [
                [
                    {'field': 'id', 'hidden': 'true'},
                    {'name': translator.common.ticket.tockenNo, 'field': 'refNo', 'width': '300px', 'formatter': function(data) {                         

                                    return  "<span class='bold'>" + data + "</span>";
                    }}, 
                    {'name': translator.common.gateway, 'field': 'gateway', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                    {'name': translator.common.amount + "("+ currencyResult[0].currency +")", 'field': 'amount', 'width': '100px'},
                    {'name': translator.common.billing.processingFee + "("+ currencyResult[0].currency +")", 'field': 'processingFee', 'width': '100px'},
                    {'name': translator.common.billing.grid.layout.totalAmount + "("+ currencyResult[0].currency +")", 'field': 'totalAmount', 'width': '100px', 'formatter': function(data) {                                                                      
                            return  "<span class='orangeColor'>" + data + "</span>";
                        }
                    },
                    {'name': translator.common.billing.grid.layout.paymentDate, 'field': 'paymentDate', 'width': '200px'},
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
       });
        this._paymentRestore.query({accountId: currentUserId}).then(function(data) {
            if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == ' ' || data == 'null' || data == null) {
                dojo.byId("paymentGrid").innerHTML = "";
                dojo.byId("noUserPaymentMessageBox").style.display = "block";                
            } else {
                dojo.byId("noUserPaymentMessageBox").style.display = "none";                
                dojo.forEach(data, function(payData) {
                    paymentDataList.newItem({
                        id: payData.id,
                        refNo: payData.tokenNo,
                        paymentDate: payData.date,
                        amount: localeCurrency.format(payData.amount),
                        processingFee: localeCurrency.format(payData.processingFee),
                        totalAmount: localeCurrency.format(payData.totalAmount),
                        status: payData.status,
                        gateway: payData.gateway
                    });
                });
                dojo.byId("paymentGrid").innerHTML = "";
                var paymentDataGrid = new EnhancedGrid({
                    id: 'currentUserPaymentList',
                    "class":"span12",
                    store: paymentDataList,
                    structure: paymentDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                paymentDataGrid.placeAt("paymentGrid");
                paymentDataGrid.startup();
            }            
        });        
    },
    loadManualPaymentForm: function() {
        dojo.byId("paymentTotalDataDiv").style.display = "none";
        dojo.byId("manualPaymentDiv").style.display = "block";
    },
    submitManualPaymentForm: function() {
        var manualPaymentRestore = new JsonRest({
            target: core.getContextPath()+"/api/payment/manualPaymentEntry"
        });
        
        var amount = dijit.byId("amount");
        var transactionDate = dijit.byId("TransDate");
        var desc = dijit.byId("payDescription");
        var acNo = dojo.byId("acHolderACNo");
                
       var node = dojo.byId("manualPaymentFormPage");
        var nodeWidget = dijit.registry.findWidgets(node);
        var firstNode = "";
        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        if(firstNode) {
            firstNode.focus();
        } else {
            dijit.byId("paymentLoader").show();
            manualPaymentRestore.add({
                acNo: acNo.innerHTML,
                amount: amount.value,
                transactionDate: dojo.date.locale.format(transactionDate.value, {datePattern: "dd/MM/yyyy", selector: "date"}),
                desc: desc.value
            }).then(function(resultData) {            
                if (resultData == "OK") {
                    registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                    registry.byId("appToaster").show();
                    dijit.byId("manualPayForm").reset();
                    dojo.byId("paymentTotalDataDiv").style.display = "block";
                    dojo.byId("manualPaymentDiv").style.display = "none";
                    PyamentsInfo.populateValues();
                    dijit.byId("paymentLoader").hide();
                } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
                    dijit.byId("paymentLoader").hide();
                }
            });
        }
    },
    cancelManualPayment: function() {
        dijit.byId("manualPayForm").reset();
        dojo.byId("paymentTotalDataDiv").style.display = "block";
        dojo.byId("manualPaymentDiv").style.display = "none";
        PyamentsInfo.populateValues();
    }
};
var AddInvoiceItem = {
    _currentUserId: "",
    _invoiceStore: "",
    init: function(currentUserId, currentInvoiceId) {                
        this._currentUserId = currentUserId;
        this._invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/"
        });
        dojo.byId("currentAddInvoiceId").innerHTML = "0000" + currentInvoiceId;
    },
    add: function() {
        var name = dijit.byId("invoiceItemName");
        var amount = dijit.byId("invoiceItemAmount");
        var taxable = dijit.byId("invoiceItemTaxEnabled").checked;
        var isRecurring = dijit.byId("isRecurring").checked;
        var taxPercentage = dijit.byId("invoiceItemTaxPercentage");
        var recurringItemBillingCycles = dijit.byId("invoiceRecurringItemBillingCycles");              
        if(taxable == true || taxable == 'true') {
            taxPercentage.set("required", true);
        } else {
            taxPercentage.set("required", false);
        }        
        if(isRecurring == true || isRecurring == 'true') {
            recurringItemBillingCycles.set("required", true);
        } else {
            recurringItemBillingCycles.set("required", false);
        }              
        var node = dojo.byId("invoiceAddForm");
        var nodeWidget = dijit.registry.findWidgets(node);        
        dojo.forEach(nodeWidget, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();             
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        
        dijit.byId("addItemButton").set('style', {display: 'none'});
        dojo.byId("addItemButtonLoader").style.display = "block";
        
        var invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/"
        });
        
        invoiceStore.add({
            amount: amount.value,
            name: name.value,
            taxable: taxable,
            taxPercentage: taxPercentage.value,
            account: dojo.byId("currentAccountId").value,
            isRecurring: isRecurring,
            isAllAccount: "SELECTIVE",
            billingCycles: recurringItemBillingCycles.value
        }).then(function(data) {
            dijit.byId("addItemButton").set('style', {display: 'block'});
            dojo.byId("addItemButtonLoader").style.display = "none";
            if (data[0] === "OK") {
                registry.byId("appToaster").setContent(translator.common.message.itemAdded, "message");
                registry.byId("appToaster").show();
                CurrentInvoiceInfo.populateValues();
            } else {
                registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                registry.byId("appToaster").show();
            }
        });
    },
    cancel: function() {
        core.router.go("#/admin/account/invoice");
    },
    enableTax: function(taxEnabled) {
        if (taxEnabled.checked == "true" || taxEnabled.checked == true) {
            dojo.byId("invoiceItemTaxPercentageDiv").style.display = "block";
        } else {
            dojo.byId("invoiceItemTaxPercentageDiv").style.display = "none";
        }
    },
    enableRecuringItem: function(data) {
        if (data.checked == "true" || data.checked == true) {
            dojo.byId("recurringItemBillingCyclesDiv").style.display = "block";
        } else {
            dojo.byId("recurringItemBillingCyclesDiv").style.display = "none";
        }
    }
};

var ViewInvoiceDetails = {
    _invoiceStore: "",
    init: function(currentId) {
        document.getElementById("currentInvoice").src = "pdf/currentUsage?invoiceId=" + Number(currentId);
        dojo.byId("currentInvoiceId").innerHTML = "0000" + currentId;
        this._invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/"
        });
        this._invoiceStore.get(currentId).then(function(data) {
            dojo.forEach(data, function(el) {
                if (el.status == "DRAFT") {
                    dojo.byId("viewInvoiceAction").style.display = "block";
                } else {
                    dojo.byId("viewInvoiceAction").style.display = "none";
                }
            });
        });
    }
};
var ViewCurrentAccountInfo = {
    _currentAcId: "",
    _accountListRestStore: "",
    init: function(currentAcId) {
        this._currentAcId = currentAcId;
        this._accountListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
    },
    cancelCreditLimit : function () {
        dijit.byId("updateCreditLimitDialog").hide();
        dijit.byId("vpcUpdateCreditConfirm").hide();
    },
    cancelCreditConfirm : function () {
        dijit.byId("vpcUpdateCreditConfirm").hide();
    },
    showUpdateCreitLimitConfirm : function () {        
        var pageNode = dojo.byId("updateCreditLimitPage");
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
            dijit.byId("vpcUpdateCreditConfirm").show();
        }        
    },
    showUpdateCredit : function () {
        dojo.query("#accountInfoUpdateCreditTag").toggleClass("hide_text", true);
        dojo.query("#accountInfoUpdateCreditLoader").removeClass("hide_text", true);
        var accountListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        accountListRestStore.get(dojo.byId("currentAccountId").value).then(function (data) {
            dojo.byId("updateCurrentDue").innerHTML = data[0].totalPayable < 0  ?  0 : Math.round(data[0].totalPayable * 100) / 100;                        
            dojo.byId("updateCurrentCreditLimit").innerHTML = data[0].creditLimit;              
            if(data[0].firstName) {                
                dojo.query("#accountInfoUpdateCreditTag").removeClass("hide_text", true);
                dojo.query("#accountInfoUpdateCreditLoader").toggleClass("hide_text", true);
                
                dijit.byId("updateCreditLimitForm").reset();
                dijit.byId("updateCreditLimitDialog").show();
                dijit.byId("vpcUpdateCreditConfirm").hide();
            }
        });         
    },
    updateCreditLimit : function () {        
        var creditConfigStore = new JsonRest({
            target: core.getContextPath() + "/api/account/update/creditLimit"
        });
        var retailCreditLimit = dijit.byId("updateCreditLimit");   
        dijit.byId("accountProcessLoader2").show();
        creditConfigStore.add({
            newLimit: retailCreditLimit.value,
            accountId: dojo.byId("currentAccountId").value
        }).then(function(resultData) {
            dijit.byId("accountProcessLoader2").hide();
            if (resultData[0].result === "OK") {
                registry.byId('appToaster').setContent(translator.common.message.updatedSuccess, 'message');
                registry.byId('appToaster').show();
                ViewCurrentAccountInfo.populateValues(dojo.byId("currentAccountId").value)
                dijit.byId("updateCreditLimitDialog").hide();
                dijit.byId("vpcUpdateCreditConfirm").hide();
            } else if (resultData[0].result === "Failed") {
                registry.byId('appToaster').setContent(resultData[0].message, 'error');
                registry.byId('appToaster').show();
                dijit.byId("updateCreditLimitDialog").hide();
                dijit.byId("vpcUpdateCreditConfirm").hide();
            } else {
                registry.byId('appToaster').setContent(translator.common.message.failed, 'error');
                registry.byId('appToaster').show();
                dijit.byId("updateCreditLimitDialog").hide();
                dijit.byId("vpcUpdateCreditConfirm").hide();
            }
        });            
    },
    populateValues: function(currentAcId) {

        this._accountListRestStore.get(currentAcId).then(function(reponseData) {
            dojo.forEach(reponseData, function(data) {
                dojo.byId("currentAccountName").innerHTML = data.firstName;
                dojo.byId("currentAcId").innerHTML = data.id + " ";
                var type = "";
                if(data.accountType.name == "RETAIL") {
                    type = translator.common.account.type.RETAIL
                    dojo.byId("udateCreditLimitDiv").innerHTML = "";
                    
                    dojo.query("#udateCreditLimitDiv").toggleClass("hide_text", true);
                    dojo.query("#updateCLDivider").toggleClass("hide_text", true);
                    
                } else if(data.accountType.name == "TRIAL") {
                    type = translator.common.account.type.TRIAL;
                    dojo.query("#udateCreditLimitDiv").removeClass("hide_text", true);
                    dojo.query("#updateCLDivider").removeClass("hide_text", true);
                }
                dojo.byId("currentAcType").innerHTML = type + " ";
                
                if(data.status.name == "CANCELED") {
                    dojo.byId("currentAcStatus").innerHTML = translator.common.account.status.CANCELED; 
                } else {        
                    var currentStatus = "";
                    if(data.status.name == "ACTIVE") {
                        currentStatus = translator.common.account.status.ACTIVE
                    } else if (data.status.name == "BLOCKED") {
                        currentStatus = translator.common.account.status.BLOCKED
                    } else if (data.status.name == "LOCKED") {
                        currentStatus = translator.common.account.status.LOCKED
                    } else if (data.status.name == "DISABLED") {
                        currentStatus = translator.common.account.status.DISABLED
                    } else if (data.status.name == "NOT_VERIFIED") {
                        currentStatus = translator.common.account.status.NOT_VERIFIED
                    } else if (data.status.name == "SUSPENDED") {
                        currentStatus = translator.common.account.status.SUSPENDED
                    } else if (data.status.name == "CLOSED") {
                        currentStatus = translator.common.account.status.CLOSED
                    } else {
                        currentStatus = "";
                    }
                    
                    dojo.byId("currentAcStatus").innerHTML = currentStatus;
                }
                
                dojo.byId("currentAcFirstName").innerHTML = data.firstName;
                dojo.byId("currentAcLastName").innerHTML = data.lastName;
                dojo.byId("currentAcAccountIdentifier").innerHTML = data.accountIdentifier;
                dojo.byId("currentAcDomain").innerHTML = data.domainName;
                dojo.byId("acHolderName").innerHTML = data.firstName +" "+data.lastName;
                dojo.byId("acHolderACNo").innerHTML = data.id;
                dojo.byId("currentAcEmail").innerHTML = data.email;
                dojo.byId("currentAcPhone").innerHTML = data.phoneNumber;
                
                dojo.byId("currentUserAddress1").innerHTML = data.streetAddress;
                dojo.byId("currentUserAddress2").innerHTML = data.streetAddress2;
                dojo.byId("currentUserCity").innerHTML = data.city;
                dojo.byId("currentUserState").innerHTML = data.state;
                dojo.byId("currentUserCountry").innerHTML = data.country;
                dojo.byId("currentUserZip").innerHTML = data.zip;
//                
                
                

                if (data.cardVerified == true) {
                    dojo.byId("currentAcCardVerifiedStatus").innerHTML = translator.common.account.status.verified;
                } else {
                    dojo.byId("currentAcCardVerifiedStatus").innerHTML = translator.common.account.status.notVerified;
                }

                dojo.byId("currentAcNextBillData").innerHTML = data.nextBillingData;

                dojo.byId("currentAccCurrency").innerHTML = data.currency;
                dojo.byId("currentAcJoinedDate").innerHTML = data.signUpDate;
                dojo.byId("currentAcPaid").innerHTML = data.currency + " " + localeCurrency.format(data.totalPaid); //+ " ("+data.payCount+")";
                dojo.byId("currentAcDue").innerHTML = data.totalPayable < 0  ?  data.currency + " " + localeCurrency.format(0.00) : data.currency + " " + localeCurrency.format(data.totalPayable);                        
                dojo.byId("currentAcCreditLimit").innerHTML = data.currency + " " + localeCurrency.format(data.creditLimit);                
                var acBc = data.totalPayable > 0 ? data.creditLimit - data.totalPayable : data.creditLimit - (data.totalPayable) ;
                dojo.byId("currentAcRefund").innerHTML = data.currency + " " + localeCurrency.format(data.refundAmount);
                dojo.byId("currentAcBc").innerHTML =  data.currency + " " + localeCurrency.format(acBc);
                dojo.byId("currentAcIncome").innerHTML = data.currency + " " + localeCurrency.format(data.totalPaid);                              
            });
        });
    },
    loadPage : function(currentId) { 
//        var domNode =  dojo.query(".nav-tabs li a");  
        
        var accountListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        
        accountListRestStore.get(currentId).then(function(resultData) {
            dojo.forEach(resultData, function(value) {
                dojo.byId("currentAccountName").innerHTML = value.firstName;
            });
        });
    }

};

var CurrentInvoiceInfo = {
    _accountListRestStore: "",
    _invoiceStore: "",
    init: function(currentUserId) {

        this._accountListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/"
        });
        this._invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/"
        });
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("invoiceCurrencyValue").innerHTML= cur.currency;
               });
        });
    },
    backToListInvoice: function() {
        dojo.byId("showInvoiceListDiv").style.display = "block";
        dojo.byId("showCurrentInvoice").style.display = "none";
        dojo.byId("showAddItem").style.display = "none";
    },
    showAddItemForm: function() {        
        dijit.byId("invoiceAddForm").reset();        
        dojo.byId("showInvoiceListDiv").style.display = "none";
        dojo.byId("showCurrentInvoice").style.display = "none";
        dojo.byId("showAddItem").style.display = "block";
    },
    backToInvoice:  function() {
        dojo.byId("showInvoiceListDiv").style.display = "none";
        dojo.byId("showCurrentInvoice").style.display = "block";
        dojo.byId("showAddItem").style.display = "none";        
        document.getElementById("currentInvoice").src = "pdf/currentUsage?invoiceId=" + Number(dojo.byId("currentInvoiceId").value);
    },
    showInvoice: function(currentInvoiceId) {
        
        dojo.byId("showInvoiceListDiv").style.display = "none";
        dojo.byId("showCurrentInvoice").style.display = "block";
        dojo.byId("showAddItem").style.display = "none";
        
        dojo.byId("currentInvoiceId").value = currentInvoiceId;
        
        var currentUserId = dojo.byId("currentAccountId").value;
        
        document.getElementById("currentInvoice").src = "pdf/currentUsage?invoiceId=" + Number(currentInvoiceId);
        dojo.byId("currentInvoiceId").innerHTML = "0000" + currentInvoiceId;
        var invoiceStore = new JsonRest({
            target: core.getContextPath()+"/api/invoice/"
        });
        invoiceStore.get(currentInvoiceId).then(function(data) {
            dojo.forEach(data, function(el) {
                if (el.status === "DRAFT") {
                    
                } else {
                    dojo.byId("viewInvoiceAction").style.display = "none";
                }
            });
        });
    },
    populateValues: function() {
        
        dojo.byId("showInvoiceListDiv").style.display = "block";
        dojo.byId("showCurrentInvoice").style.display = "none";
        dojo.byId("showAddItem").style.display = "none";
        
        var currentUserId = dojo.byId("currentAccountId").value;
        
        if (dijit.byId("currentUserIvoiceList")) {
            dijit.byId("currentUserIvoiceList").destroyRecursive();
        }        
        dojo.byId("userInvoiceList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var invoiceData = {
            items: []
        };
        var taxDataList = new ItemFileWriteStore({data: invoiceData});
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        
        var taxDataLayout = "";
        configRestStore.query().then(function (currencyResult) {
            taxDataLayout = [
                [
                    {'name': translator.common.billing.grid.layout.invoiceNo, 'field': 'invoiceNo', 'width': '100px', 'formatter': function(data) {
                            var item = data.split(",");
                            var node = "<a onclick='CurrentInvoiceInfo.showInvoice(\"" + item[0] + "\")'>" + "0000" + item[0] + "</a>";
                            return node;
                        }
                    },
                    {'name': translator.common.billing.grid.layout.currentUsage + " ("+ currencyResult[0].currency +")", 'field': 'currentDue', 'width': '200px'},
                    {'name':  translator.common.billing.grid.layout.totalPayable + " ("+ currencyResult[0].currency  +")", 'field': 'totalAmount', 'width': '200px', 'formatter': function(data) {                                                                      
                            return  "<span class='orangeColor'>" + data + "</span>";
                        }
                    },
                    {'name':  translator.common.billing.grid.layout.invoiceDate, 'field': 'invoiceDate', 'width': '200px', 'formatter': function(data) {                                                                      
                            return  "<span class='bold'>" + data + "</span>";
                        }
                    }, 
                    {'name':  translator.user.grid.instance.layout.status, 'field': 'status', 'width': '100px'},
                    {'name':  translator.common.action, 'field': 'action',
                        'formatter': function(data) {           
                            var html = "<a href='" + core.getBaseURL() + "/pdf/pdfLink?pdfController=pdf&pdfAction=invoiceSummary&invoiceId=" + data + "&filename=invoice-" + data + "' title='"+translator.common.downloadPDF+"'><i class='pdf_icon'></i></a>" +
                                "<a class='offset1' onclick='CurrentInvoiceInfo.showInvoice(\"" + data + "\")' title='"+translator.common.viewInvoice+"'><i class='icon-eye-open'></i></a>";
                            return html;
                        }, 'width': '100%'}
                ]
            ];
        });
        this._invoiceStore.query({accountId: currentUserId}).then(function(data) {
            if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == ' ' || data == 'null' || data == null) {
                dojo.byId("userInvoiceList").innerHTML = " ";  
                dojo.byId("noUserInvoiceMessageBox").style.display = "block";
            } else {               
                dojo.byId("noUserInvoiceMessageBox").style.display = "none";
                dojo.forEach(data, function(invoiceData) {
                    taxDataList.newItem({
                        invoiceNo: invoiceData.id + "," + invoiceData.status,
                        currentDue: localeCurrency.format(invoiceData.currentDue),
                        totalAmount:  localeCurrency.format(invoiceData.totalAmount < 0  ?  0 :invoiceData.totalAmount),
                        invoiceDate: invoiceData.invoiceDate,
                        status: invoiceData.status,
                        action: invoiceData.id
                    });
                });
                dojo.byId("userInvoiceList").innerHTML = " ";
                var invoiceDataGrid = new EnhancedGrid({
                    id: 'currentUserIvoiceList',
                    "class":"span12",
                    store: taxDataList,
                    structure: taxDataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                invoiceDataGrid.placeAt("userInvoiceList");
                invoiceDataGrid.startup();
            }           
        });        
    }
};

var AccountList = {
//    _accountGrid: "",
    _accountListRestStore: "",
    _accountCountListRestStore:"",
    init: function() {
        this._accountListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account"
        });
        
        this._accountCountListRestStore = new JsonRest({
            target: core.getContextPath()+"/api/account/count"
        });
    },
    populateValues: function() {
        
        dojo.byId("accountGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>"; 
        
        if (dijit.byId("accountGridWidget")) {
            dijit.byId("accountGridWidget").destroyRecursive();
        }                      
        dojo.byId("adminTotalAc").innerHTML = 0;
        dojo.byId("adminRetailAc").innerHTML = 0;
        dojo.byId("adminTrialAc").innerHTML = 0;
        
        var gridData = {
            items: []
        };
        
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
        var gridLayout = "";
        configRestStore.query().then(function (currencyData) {
            gridLayout = [
                [
                    {'name': translator.user.userID, 'field': 'id', 'width': '100px', datatype:"string",  autoComplete: true, 'formatter': function(data) {
                            var node = "<a href='#/admin/account/summary/" + data + "'>" + data + "</a>";
                            return node;
                        }
                    },
                    {'name': translator.user.userName, 'field': 'userName', 'width': '200px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.domain, 'field': 'domainName', 'width': '100px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.email, 'hidden': true, 'field': 'email'},
                    {'name': translator.common.account.accountType, 'field': 'type', 'width': '100px', datatype:"string",  autoComplete: true},
                    {'name': translator.common.billing.currentDue + " (" + currencyData[0].currency +")", 'field': 'due', 'width': '150px', datatype:"string",  autoComplete: true, 'formatter': function(data) {                                                                      
                            return  "<span class='orangeColor'>" + data + "</span>";
                        }
                    },    
                    {'name': translator.common.billing.paid + " (" + currencyData[0].currency +")", 'field': 'paid', 'width': '100px', datatype:"string",  autoComplete: true, 'formatter': function(data) {                                                                      
                            return data;
                        }
                    },     
                    {'name': translator.user.grid.instance.layout.status, 'field': 'status', 'width': '100px',  datatype:"string",
                        // Declare that we need the ComboBox for suggestions
                        autoComplete: true,
                        // Configure the ComboBox, so that it does not auto-complete our input
                        dataTypeArgs: {
                            autoComplete: false
                        }, 'formatter' : function(status) {
                            var currentStatus = "";
                            if(status == "CANCELED") {
                                currentStatus = "<div class='client-stat-canceled overflowLabel'>"+translator.common.account.status.CANCELED+"</div>";
                            } else if(status == "ACTIVE") {
                                currentStatus = "<div class='client-stat-active overflowLabel'>"+translator.common.account.status.ACTIVE+"</div>";
                            } else if(status == "DISABLED") {
                                currentStatus = "<div class='client-stat-disabled overflowLabel'>"+translator.common.account.status.DISABLED+"</div>";
                            } else if(status == "SUSPENDED") {
                                currentStatus = "<div class='client-stat-suspended overflowLabel'>"+translator.common.account.status.SUSPENDED+"</div>";
                            } else if(status == "CLOSED") {
                                currentStatus = "<div class='client-stat-closed overflowLabel'>"+translator.common.account.status.CLOSED+"</div>";
                            } else if(status == "NOT_VERIFIED") {
                                currentStatus = "<div class='client-stat-ntverified overflowLabel'>"+translator.common.account.status.NOT_VERIFIED+"</div>";
                            }
                            return currentStatus;
                        }
                    }, 
                    {'name': 'Action', 'field': 'action', 'width': '100%', 'formatter': function(data) {                    
                            var link = core.getBaseURL() + "/j_spring_security_switch_user?j_username=" + data.userName;
                            var accounrStatus =  new FogPanel.AccountStatus({
                                onResetPassword : function() {
                                    AccountList.showResetPasswordDialog(data.id);
                                },
                                onLockAccount : function() {
                                    AccountList.showUnLockAccountDialog(data.id);
                                },
                                onLoginAccount : function() {
                                    console.log("Enter into user login");
                                    // todo add the SSO login call to CS
                                    AccountLogin.csRequest();
                                }
                            });  
                            accounrStatus.setLoginLink(link);
                            if(data.locked == true) {
                                accounrStatus.lock();
                            } else if(data.locked == false) {
                                accounrStatus.unLock();
                            } 
                            if(data.status == "ACTIVE") {
                                accounrStatus.activeState(link);
                            } else if(data.status == "DISABLED") {
                                 accounrStatus.disableState();
                            } else if(data.status == "SUSPENDED") {
                                 accounrStatus.suspendState();
                            } else if(data.status == "CANCELED") {
                                 accounrStatus.cancelState();
                            } else {
                                accounrStatus.disableAll();
                            }                        
                            return accounrStatus;                                       
                        }
                    }
                ]
            ];
        });
        var gridDataList = new ItemFileWriteStore({data: gridData});
               
        this._accountListRestStore.query().then(function(data) {
            if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == ' ' || data == 'null' || data == null) {
                dojo.byId("noClientMessageBox").style.display = "block";
                dojo.byId("accountGrid").innerHTML = "";                
                dojo.byId("adminTotalAc").innerHTML = 0;
                dojo.byId("adminRetailAc").innerHTML = 0;
                dojo.byId("adminTrialAc").innerHTML = 0;
            } else {
                dojo.byId("noClientMessageBox").style.display = "none";
                dojo.forEach(data, function(accountList) {   
                    var totalPayable = accountList.totalPayable < 0  ?  0 : accountList.totalPayable;
                    gridDataList.newItem({
                        id: accountList.id,
                        userName: accountList.userName,  
                        domainName: accountList.domainName,  
                        email: accountList.email,
                        type: accountList.accountType.name,
                        due: localeCurrency.format(totalPayable),
                        paid: localeCurrency.format(accountList.totalPaid),
                        status: accountList.status.name,                       
                        action: { 'id': accountList.id, 'userName': accountList.userName, 'status': accountList.status.name, 'locked': accountList.locked}
                    });
                });
                dojo.byId("accountGrid").innerHTML = "";
                var accountGrid = new EnhancedGrid({
                    id: 'accountGridWidget',
                    "class":"span12",
                    store: gridDataList,
                    structure: gridLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });                
                accountGrid.placeAt("accountGrid");
                accountGrid.startup();               
                var accountCountListRestStore = new JsonRest({
                    target: core.getContextPath()+"/api/account/count"
                });                
                accountCountListRestStore.query().then(function(data) {
                    dojo.forEach(data, function(resultData) {               
                        dojo.byId("adminTotalAc").innerHTML = resultData.totalAccount;
                        dojo.byId("adminRetailAc").innerHTML = resultData.retailAccount;
                        dojo.byId("adminTrialAc").innerHTML = resultData.trialAccount;                
                    });
                });
            }            
        });      
    },
    showCancelAccountDialog : function(data) {
        dijit.byId("cancelDialog").show();
        dojo.byId("accountId").value = data;
    },
    cancelAccount : function() {
        dijit.byId("accountProcessLoader").show();
        var accountId =  dojo.byId("accountId").value;
        
        var cancelAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/cancelAccount"
        });
        
        cancelAccountStore.add({
            accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader").hide();  
                    dijit.byId("cancelDialog").hide();
                    AccountList.populateValues();
                    registry.byId("appToaster").setContent(translator.common.account.accountCancelled, "message");
                    registry.byId("appToaster").show();
                } else {
                    dijit.byId("accountProcessLoader").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountCancelError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeCancelAccountDialog : function() {
        dijit.byId("cancelDialog").hide();
    },
    showEnableAccountDialog : function(data) {
        dijit.byId("enableDialog").show();
        dojo.byId("accountId").value = data;
    },
    enableAccount : function() {
        dijit.byId("accountProcessLoader").show();
        var accountId =  dojo.byId("accountId").value;
        
        var enableAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/enableAccount"
        });
        
        enableAccountStore.add({
                accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader").hide();  
                    dijit.byId("enableDialog").hide();
                    AccountList.populateValues();
                    registry.byId("appToaster").setContent(translator.common.account.accountEnabled, "message");
                    registry.byId("appToaster").show();
                } else {
                    dijit.byId("accountProcessLoader").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountEnabledError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeEnableAccountDialog : function() {
        dijit.byId("enableDialog").hide();
    },
    showDisableAccountDialog : function(data) {
        dijit.byId("disableDialog").show();
        dojo.byId("accountId").value = data;
    },
    disableAccount : function() {
        dijit.byId("accountProcessLoader").show();
        var accountId =  dojo.byId("accountId").value;
        
        var disableAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/disableAccount"
        });
        
        disableAccountStore.add({
                accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader").hide();  
                    dijit.byId("disableDialog").hide();
                    AccountList.populateValues();
                    registry.byId("appToaster").setContent(translator.common.account.accountDisabled, "message");
                    registry.byId("appToaster").show();
                } else {
                    dijit.byId("accountProcessLoader").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountDisabledError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeDisableAccountDialog : function() {
        dijit.byId("disableDialog").hide();
    },
    showSuspendAccountDialog : function(data) {
        dijit.byId("suspendDialog").show();
        dojo.byId("accountId").value = data;
    },
    suspendAccount : function() {
        dijit.byId("accountProcessLoader").show();
        var accountId =  dojo.byId("accountId").value;
        
        var suspendAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/suspendAccount"
        });
        
        suspendAccountStore.add({
                accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader").hide();  
                    dijit.byId("suspendDialog").hide();
                    AccountList.populateValues();
                    registry.byId("appToaster").setContent(translator.common.account.accountSuspended, "message");
                    registry.byId("appToaster").show();
                } else {
                    dijit.byId("accountProcessLoader").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountSuspendError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeSuspendAccountDialog : function() {
        dijit.byId("suspendDialog").hide();
    },
    showLockAccountDialog : function(data) {
        dijit.byId("lockDialog").show();
        dojo.byId("accountId").value = data;
    },
    lockAccount : function() {
        dijit.byId("accountProcessLoader").show();
        var accountId =  dojo.byId("accountId").value;
        
        var lockAccountStore = new JsonRest({
            target: core.getContextPath()+"/api/account/lockAccount"
        });
        
        lockAccountStore.add({
                accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader").hide();  
                    dijit.byId("lockDialog").hide();
                    AccountList.populateValues();
                    registry.byId("appToaster").setContent(translator.common.account.accountLocked, "message");
                    registry.byId("appToaster").show();
                } else {
                    dijit.byId("accountProcessLoader").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountLockError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeLockAccountDialog : function() {
        dijit.byId("lockDialog").hide();
    },
    showUnLockAccountDialog : function(data) {
        dijit.byId("unLockDialog").show();
        dojo.byId("accountId").value = data;
    },
    unLockAccount : function() {
        dijit.byId("accountProcessLoader").show();
        var accountId =  dojo.byId("accountId").value;
        
        var unLockAccountStore = new JsonRest({ 
            target: core.getContextPath()+"/api/account/unLockAccount"
        });
        
        unLockAccountStore.add({
                accountId: accountId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("accountProcessLoader").hide();  
                    dijit.byId("unLockDialog").hide();
                    AccountList.populateValues();
                    registry.byId("appToaster").setContent(translator.common.account.accountUnlocked, "message");
                    registry.byId("appToaster").show();
                } else {
                    dijit.byId("accountProcessLoader").hide();
                    registry.byId("appToaster").setContent(translator.common.account.accountUnlockedError, "error");
                    registry.byId("appToaster").show();
                }
            });                
        });
    },
    closeUnLockAccountDialog : function() {
        dijit.byId("unLockDialog").hide();
    },
    showResetPasswordDialog : function(data) {
        dijit.byId("resetPasswordDialog").show();
        dijit.byId("resetPasswordForm").reset();
        dojo.byId("accountId").value = data;
    },    
    resetPassword : function() {
        
        var accountId =  dojo.byId("accountId").value;
        
        var resetPasswordStore = new JsonRest({
            target: core.getContextPath()+"/api/account/resetPassword"
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
            dijit.byId("accountProcessLoader").show();

            resetPasswordStore.add({
                    accountId: accountId,
                    confirmPassword: dijit.byId("confirmPassword").getValue()
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if(resultData.result == "OK") {
                        dijit.byId("accountProcessLoader").hide();  
                        dijit.byId("resetPasswordDialog").hide();
                        AccountList.populateValues();
                        registry.byId("appToaster").setContent(translator.common.account.resetPasswordSucess, "message");
                        registry.byId("appToaster").show();
                    } else {
                        dijit.byId("accountProcessLoader").hide();
                        registry.byId("appToaster").setContent(translator.common.account.resetPasswordError, "error");
                        registry.byId("appToaster").show();
                    }
                });                
            });
        }
    },
    closeResetPasswordDialog : function() {
        dijit.byId("resetPasswordDialog").hide();
    },
    checkPasswordStrength : function(id) {
        var pwd =  dijit.byId(id).getValue();        
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
        } else if (false == enough.test(pwd))	{
            strength_text.innerHTML = 'Too short';
            progress_bar.style.backgroundColor = '#DC143C';
        } else if (strong.test(pwd)) {
            strength_text.innerHTML = translator.common.strong;
            width = 100;
            progress_bar.style.backgroundColor = '#228B22';
            strength_id.value = 3;
        } else if (medium.test(pwd)) {
            strength_text.innerHTML = translator.common.medium;
            width = 70;
            progress_bar.style.backgroundColor = '#FF8C30';
            strength_id.value = 2;
        } else {
            width = 60;
            strength_text.innerHTML = translator.common.weak;
            progress_bar.style.backgroundColor = '#FFD700';
            strength_id.value = 1;
        }	
        progress_bar.style.width = width + '%'; 	
        if(pwd.length == 0) {
            dojo.byId('password_strength').style.display = "none";
        } else {
            dojo.byId('password_strength').style.display = "block";        
            if(pwd.length < 8) {          
                dijit.byId("newPassword").set("invalidMessage", translator.common.account.passwordLengthConstrain);
                dijit.byId("confirmPassword").reset();           
                dijit.byId("confirmPassword").validator = function() {};             
            } else if(pwd.length > 15) {
                dijit.byId("newPassword").set("invalidMessage", translator.common.account.passwordLengthConstrain);
                dijit.byId("confirmPassword").reset();           
                dijit.byId("confirmPassword").validator = function() {}; 
            }
        }       
    },
    confirmPassword : function(confirmPassword) {
 
        var pass =  dijit.byId("newPassword").getValue();
        var status = true;   
        var confirmPasswordValue = confirmPassword.getValue();
        confirmPassword.validator = function() {
            if(pass == confirmPasswordValue) {           
                confirmPassword.set("invalidMessage", " ");
                status = true;
                return status;      
            } else if(pass != confirmPasswordValue) {
                confirmPassword.set("invalidMessage", translator.common.account.passwordNotMatch);
    //            confirmPassword.focus();
                status = false;
                return status;
            } else {
                return false;
            }        
        };    
    }
};

var ResourceLimitForUser = {
    
    populateValues : function(id) {
        
        var resourceLimitForUserRest = new JsonRest({
           target: core.getContextPath() + "/api/account/getCloudResourceStat/" 
        });
        
        dojo.byId("resourceLimitDivLoad").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        
        resourceLimitForUserRest.query({id : id}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                                
                
                if(resultData.vmLimit === "-1") {
                    dojo.byId("InstanceLimitDiv").style.display = "none";
                }
                 if(resultData.storageLimit === "-1") {
                    dojo.byId("VolumeLimitDiv").style.display = "none";
                }
                 if(resultData.snapshotLimit === "-1") {
                    dojo.byId("SnapshotLimitDiv").style.display = "none";
                }
                 if(resultData.publicIPLimit === "-1") {
                    dojo.byId("PublicIPLimitDiv").style.display = "none";
                }
                 if(resultData.vpcLimit === "-1") {
                    dojo.byId("VPCLimitDiv").style.display = "none";
                }
                 if(resultData.primaryStorageLimit === "-1") {
                    dojo.byId("PrimaryStorageLimitDiv").style.display = "none";
                }
                 if(resultData.secondaryStorageLimit === "-1") {
                    dojo.byId("SecondaryStorageLimitDiv").style.display = "none";
                }
                 if(resultData.cpuLimit === "-1") {
                    dojo.byId("vCPULimitDiv").style.display = "none";
                }
                 if(resultData.memoryLimit === "-1") {
                    dojo.byId("MemoryLimitDiv").style.display = "none";
                }
                if(resultData.networkLimit === "-1") {
                    dojo.byId("NetworkLimitDiv").style.display = "none";
                } 
                
                dijit.byId("instanceLimitLoader").set("value", Math.round(( resultData.vmUsed / resultData.vmLimit) * 100) % 100.00); 
                dijit.byId("volumeLimitLoader").set("value", Math.round(( resultData.storageUsed / resultData.storageLimit) * 100)  % 100.00); 
                dijit.byId("snapshotLimitLoader").set("value", Math.round(( resultData.snapshotUsed / resultData.snapshotLimit) * 100)  % 100.00); 
                dijit.byId("pulicIpLimitLoader").set("value", Math.round(( resultData.publicIPUsed / resultData.publicIPLimit) * 100)  % 100.00); 
                dijit.byId("vpcLimitLoader").set("value", Math.round(( resultData.vpcUsed / resultData.vpcLimit) * 100)  % 100.00);                 
                dijit.byId("primaryStorageLimitLoader").set("value", Math.round(( resultData.primaryStorageLimitUsage / resultData.primaryStorageLimit) * 100)  % 100.00); 
                dijit.byId("secondaryStorageLimitLoader").set("value", Math.round(( resultData.secondaryStorageLimitUsage / resultData.secondaryStorageLimit) * 100)  % 100.00); 
                dijit.byId("vcpuLimitLoader").set("value", Math.round(( resultData.cpuUsed / resultData.cpuLimit) * 100)  % 100.00); 
                dijit.byId("memoryLimitLoader").set("value", Math.round(( resultData.memoryUsed / resultData.memoryLimit) * 100)  % 100.00); 
                 
                var vmData = resultData.vmLimit === 0 || resultData.vmLimit === null ? "0" : resultData.vmLimit;
                var storageData = resultData.storageLimit === 0 || resultData.storageLimit === null ? "0" : resultData.storageLimit;
                var snapData = resultData.snapshotLimit === 0 || resultData.snapshotLimit === null ? "0" : resultData.snapshotLimit;
                var ipData = resultData.publicIPLimit === 0 || resultData.publicIPLimit === null ? 0 : resultData.publicIPLimit;
                var vpcData = resultData.vpcLimit === 0 || resultData.vpcLimit === null ? 0 : resultData.vpcLimit;
                var psData = resultData.primaryStorageLimit === 0 || resultData.primaryStorageLimit === null ? 0 : resultData.primaryStorageLimit;
                var ssData = resultData.secondaryStorageLimit === 0 || resultData.secondaryStorageLimit === null ? 0 : resultData.secondaryStorageLimit;
                var cpuData = resultData.cpuLimit === 0 || resultData.cpuLimit === null ? 0 : resultData.cpuLimit;
                var ramData = resultData.memoryLimit === 0 || resultData.memoryLimit === null ? 0 : resultData.memoryLimit;    
                
                dijit.byId("networkLimitLoader").set("value", Math.round((resultData.networkUsed / resultData.networkLimit) * 100)  % 100.00); 
                var networkData = resultData.networkLimit === 0 || resultData.networkLimit === null ? 0 : resultData.networkLimit;
                dojo.byId("networkLimitInfo").innerHTML =  resultData.networkUsed + " / " +  networkData + " Network" ;
                   
                 dojo.byId("instanceLimitInfo").innerHTML =  resultData.vmUsed + " / " + vmData + " Instance";
                 dojo.byId("volumeLimitInfo").innerHTML =  resultData.storageUsed + " / " + storageData + " Volume";
                 dojo.byId("snapshotLimitInfo").innerHTML =  resultData.snapshotUsed + " / " + snapData + " Snapshot";
                 dojo.byId("pulicIpInfo").innerHTML =  resultData.publicIPUsed + " / " + ipData + " IP" ;
                 dojo.byId("vpcLimitInfo").innerHTML =  resultData.vpcUsed + " / " + vpcData + " VPC";
                 dojo.byId("primaryStorageInfo").innerHTML =  resultData.primaryStorageLimitUsage + " / " + psData + " GB" ;
                 dojo.byId("secondaryStorageInfo").innerHTML =  resultData.secondaryStorageLimitUsage + " / " + ssData + " GB" ;
                 dojo.byId("vcpuLimitInfo").innerHTML =  resultData.cpuUsed + " / " + cpuData + " Core";
                 dojo.byId("memoryLimitInfo").innerHTML =  resultData.memoryUsed + " / " +  ramData + " MB" ;
                
                var retailSnapshotLimit = dijit.byId("retailSnapshotLimit");
                var retailPublicIPLimit = dijit.byId("retailPublicIPLimit");
                var retailVpcLimit = dijit.byId("retailVpcLimit");
                var retailCpuLimit = dijit.byId("retailCpuLimit");
                var retailMemoryLimit = dijit.byId("retailMemoryLimit");
                var primaryStorageLimit = dijit.byId("primaryStorageLimit");
                var secondaryStorageLimit = dijit.byId("secondaryStorageLimit");
                var retailNetworkLimit = dijit.byId("retailNetworkLimit");
                
                dijit.byId("retailInstanceLimit").setValue(resultData.vmLimit);
                dijit.byId("retailStorageLimit").setValue(resultData.storageLimit);
                
                retailSnapshotLimit.setValue(resultData.snapshotLimit);
                retailPublicIPLimit.setValue(resultData.publicIPLimit);
                retailVpcLimit.setValue(resultData.vpcLimit);
                retailCpuLimit.setValue(resultData.cpuLimit);
                retailMemoryLimit.setValue(resultData.memoryLimit);
                primaryStorageLimit.setValue(resultData.primaryStorageLimit);
                secondaryStorageLimit.setValue(resultData.secondaryStorageLimit);
                retailNetworkLimit.setValue(resultData.networkLimit);

            });
            dojo.byId("resourceLimitDivLoad").innerHTML = "";
            dojo.byId("resourceLimitDataDiv").style.display = "block";
        });
            
    },
    update: function() {
        
        var accountId =  dojo.byId("currentAccountId").value;
        var retailInstanceLimit = dijit.byId("retailInstanceLimit").value;
        var retailStorageLimit = dijit.byId("retailStorageLimit").value;
        var retailSnapshotLimit = dijit.byId("retailSnapshotLimit").value;
        var retailPublicIPLimit = dijit.byId("retailPublicIPLimit").value;
        var retailVpcLimit = dijit.byId("retailVpcLimit").value;
        var retailCpuLimit = dijit.byId("retailCpuLimit").value;
        var retailMemoryLimit = dijit.byId("retailMemoryLimit").value;
        var primaryStorageLimit = dijit.byId("primaryStorageLimit").value;
        var secondaryStorageLimit = dijit.byId("secondaryStorageLimit").value;
        
        var retailNetworkLimit = dijit.byId("retailNetworkLimit").value;
        
        var updateResourceLimitRest = new JsonRest({
            target: core.getContextPath() + "/api/account/updateResourceLimit"
        })
        
        var pageNode = dojo.byId("updateResourceLimitPage");
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
            updateResourceLimitRest.add({
                accountId : accountId,
                retailInstanceLimit : retailInstanceLimit,
                retailStorageLimit : retailStorageLimit,
                retailSnapshotLimit : retailSnapshotLimit,
                retailPublicIPLimit : retailPublicIPLimit,
                retailVpcLimit : retailVpcLimit,
                retailCpuLimit : retailCpuLimit,
                retailMemoryLimit : retailMemoryLimit,
                primaryStorageLimit: primaryStorageLimit,
                secondaryStorageLimit : secondaryStorageLimit,
                retailNetworkLimit:retailNetworkLimit
            
            }).then(function(data){
               dojo.forEach(data, function(resultData) {
                   if(resultData.result == "OK") { 
                        ResourceLimitForUser.populateValues(accountId);
                        dijit.byId("updateResourceLimitDialog").hide();                       
                        registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("appToaster").show();
                    } else {
                        ResourceLimitForUser.populateValues(accountId);
                        dijit.byId("updateResourceLimitDialog").hide();
                        registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("appToaster").show();
                    }
               }); 
            })
        }
    },
    closeResourceLimitDialog : function() {
        dijit.byId("updateResourceLimitDialog").hide();
    },
   
};

window.AccountList = AccountList;
window.ViewCurrentAccountInfo = ViewCurrentAccountInfo;
window.CurrentInvoiceInfo = CurrentInvoiceInfo;
window.ViewInvoiceDetails = ViewInvoiceDetails;
window.AddInvoiceItem = AddInvoiceItem;
window.PyamentsInfo = PyamentsInfo;
window.RecurringListInfo = RecurringListInfo;
window.EditCurrentRecurringItem = EditCurrentRecurringItem;
window.DeleteCurrentRecurringItem = DeleteCurrentRecurringItem;
window.InfrastructureInfo = InfrastructureInfo;
window.AddInvitationInfo=AddInvitationInfo;
window.InvitationInfo=InvitationInfo;
