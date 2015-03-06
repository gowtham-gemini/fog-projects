'use strict';
require([
    "dojo",
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js", 
    "dijit/dijit",
    "dojo/store/JsonRest",
    "dijit/registry",    
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dijit/form/HorizontalSlider",    
    "dojo/dom-construct",
    "dojox/charting/Chart",
    "dojox/charting/plot2d/Pie",
    "dojox/charting/themes/Tufte",
    "dojox/charting/action2d/Tooltip",
    "dojox/charting/action2d/MoveSlice",
    "dojox/charting/axis2d/Default",
    "dojox/charting/plot2d/Lines",
    "dojox/charting/action2d/Magnify",
    "dojox/charting/themes/Harmony",
    "dojox/charting/plot2d/Columns",
    "dojox/charting/action2d/Highlight",
    "dojo/_base/connect",    
    "dijit/Menu", 
    "dijit/MenuItem", 
    "dijit/form/ComboButton", 
    "dijit/form/DropDownButton", 
    "dijit/DropDownMenu", 
    "dijit/Tooltip",
    "dojox/charting/plot2d/Markers",
    "dijit/form/HorizontalRule",
    "dijit/form/HorizontalRuleLabels",
    "dijit/form/HorizontalSlider",    
    "dojo/query",
    "dojo/dom-class",
    "dijit/layout/TabContainer",
    "dojox/charting/widget/Chart2D",
    "dojox/charting/themes/Claro",
    "dijit/form/Button",
    "dijit/layout/ContentPane",
    "dojox/form/PasswordValidator",
    "dojo/on",
    "dojo/query",
    "dojox/validate/regexp",    
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "FogPanel/VolumeListItem",
    "FogPanel/InstanceStatus",
    "FogPanel/StorageAction",
    "dijit/TitlePane", 
    "Zone/ZoneCost",
    "dojox/widget/rotator/Slide",
    "dojox/widget/Rotator",
    "dojox/widget/rotator/Pan",
    "FogPanel/WizardListItem",
    "dijit/form/Form",        
    "dojox/validate/regexp",
    "dijit/form/ValidationTextBox",
    "dijit/form/CheckBox",
    "dijit/form/NumberSpinner",
    "dijit/Dialog",
    "dijit/layout/ContentPane",   
    "List/ListItem",
], function(dojo, i18n, translator, dijit, JsonRest, registry, FilteringSelect, Select,ItemFileWriteStore, DataGrid,
            EnhancedGrid, HorizontalSlider, domConstruct, Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, Magnify, theme, ColumnsPlot, 
            Highlight, connect, Menu, MenuItem, ComboButton, DropDownButton, DropDownMenu) {
        window.translator = translator;
        window.JsonRest = JsonRest;    
        window.Magnify = Magnify;
        window.registry = registry;
        window.Menu = Menu;
        window.MenuItem = MenuItem;
        window.DropDownButton = DropDownButton;
        window.DropDownMenu = DropDownMenu;
        window.ComboButton = ComboButton;
        window.FilteringSelect = FilteringSelect;
        window.ItemFileWriteStore = ItemFileWriteStore;
        window.Select = Select;
        window.DataGrid = DataGrid;
        window.domConstruct = domConstruct;
        window.domConstruct = domConstruct;
        window.Lines = Lines;
        window.HorizontalSlider = HorizontalSlider;
        window.Chart = Chart;
        window.Pie = Pie;
        window.PlotKitGreen = PlotKitGreen;
        window.Tooltip = Tooltip;
        window.MoveSlice = MoveSlice;
        window.theme = theme;
        window.ColumnsPlot = ColumnsPlot;
        window.Highlight = Highlight;
        window.connect = connect;
        window.currentRuleId = "";  
        window.compCount = 0;
        window.createVMCurrentZone = "";
        window.zoneTempRefId = "";
        window.isTierOptionEnabled = false;
        controller({ 
            name:"snapshot",
            module: "user",
            filePath: "/js/app/user/snapshot.js",
            layout: {
                name: "",
                containerId: "content"
            },
            scaffold: false
        },
        {   
            "list": action(function() {
                if(dijit.byId("addVMSnapshotDialog")) {
                    dijit.byId("addVMSnapshotDialog").destroyRecursive();
                }
                if(dijit.byId("snapshotLoader")) {
                    dijit.byId("snapshotLoader").destroyRecursive();
                }
                if(dijit.byId("deleteVMSnap")) {
                    dijit.byId("deleteVMSnap").destroyRecursive();
                }  
                if(dijit.byId("deleteVMsnapshotLoader")) {
                    dijit.byId("deleteVMsnapshotLoader").destroyRecursive();
                }  
                if(dijit.byId("addVolumeSnapshotDialog")) {
                    dijit.byId("addVolumeSnapshotDialog").destroyRecursive();
                }
                if(dijit.byId("volumeSnapshotLoader")) {
                    dijit.byId("volumeSnapshotLoader").destroyRecursive();
                }
                if(dijit.byId("deleteVolumeSnap")) {
                    dijit.byId("deleteVolumeSnap").destroyRecursive();
                } 
                if(dijit.byId("volumeSnapshotDeleteLoader")) {
                    dijit.byId("volumeSnapshotDeleteLoader").destroyRecursive();
                } 
                if(dijit.byId("volumeSnapShotListGridWidget")) {
                dijit.byId("volumeSnapShotListGridWidget").destroyRecursive();                                
            } 
            core.ui.loadTemplate("snapshotList", core.ui.getContentId());    
            VMSnapshotInfo.init();    
        })        
    });
});
var VMSnapshotInfo = {

    'gotoList': function() {
        dijit.byId("snapshotLoader").hide();
        dijit.byId("deleteVMsnapshotLoader").hide();
        dijit.byId('addVMSnapshotDialog').hide();
        dijit.byId("deleteVMSnap").hide();
    },
    'init' :function() {
        
         var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        }; 
        
        var instanceList = new ItemFileWriteStore({data: instanceOptions});    
        
        var instanceWidget = new FilteringSelect({
            store : instanceList,
            placeHolder: translator.common.message.selectInstance
        }, "VMsnapshotInstanceList");
        instanceWidget.startup();
    },
    populateGridValues : function() {
        
        dojo.byId("noVmFoundMessageBoxDiv").style.display = "none";
        dojo.byId("vmSnapShotList").innerHTML = "";
        dojo.byId("vmSnapShotListMsg").innerHTML = "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/> </br> <p>" + translator.user.loader.vmSnapshotLoader + "</p>";
        dojo.byId("noVMSnapshotDiv").style.display = "none";
        var instanceOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        }; 
        var vmCount = 0;
        var instanceList = new ItemFileWriteStore({data: instanceOptions});    
        
        
        var serverRestStore = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/listForAttachement"
        });
        
        serverRestStore.query().then(function(data) {
            if(data.length == 0 || data == "undefined" || data == undefined)  {                  
//                dojo.byId("noVmMessageBox").style.display = "block";                    
//                dojo.byId("userTotalVM").innerHTML = 0;
//                dojo.byId("userRunningVM").innerHTML = 0;
//                dojo.byId("userStoppedVM").innerHTML = 0;
//                dojo.byId("instanceGrid").innerHTML = "";
                dojo.byId("vmSnapShotsDiv").style.display = "none";
                dojo.byId("noVmFoundMessageBoxDiv").style.display = "block";
                dojo.byId("vmSnapShotListMsg").innerHTML = "";
            } else {      
                dojo.byId("noVmFoundMessageBoxDiv").style.display = "none";
//                dojo.byId("noVmMessageBox").style.display = "none";
                dojo.forEach(data, function(instanceData) {  
                instanceList.newItem({
                    id: instanceData.referenceId,
                    name: instanceData.name
                    });                   
                });                

            
                dijit.byId("VMsnapshotInstanceList").set('store', instanceList);


                if(dijit.byId("vmSnapShotListGridWidget")) {
                    dijit.byId("vmSnapShotListGridWidget").destroyRecursive();                                
                }        
                dojo.byId("vmSnapShotListMsg").innerHTML = "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/> </br> <p>" + translator.user.loader.vmSnapshotLoader + "</p>";
                var vmSnapshotData = {
                    items: []
                };
                var vmSnapshotList = new ItemFileWriteStore({data: vmSnapshotData});
                var vmSnapshotLayout = [
                    [
                        {'field': 'id', 'hidden': 'true'},
                        {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                        {'name': translator.common.vm, 'field': 'vm', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                        {'name': translator.common.state, 'field': 'status', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                        {'name': "Format", 'field': 'format', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                        {'name': translator.common.cost, 'field': 'cost', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true'},
                        {'name': "Public", 'field': 'public', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {

                                var html;
                                if(data === "Yes") {
                                    html = "<span class='fog_cost_blue'>"+ data + "</span>"; 
                                } else {
                                    html = "<span class='fog_cost_red overflowLabel'>"+ data + "</span>";
                                }
                                return html;
                         }},
                        {'name': "Protected", 'field': 'protected', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {

                                var html;
                                if(data === "Yes") {
                                    html = "<span class='fog_cost_blue'>"+ data + "</span>"; 
                                } else {
                                    html = "<span class='fog_cost_red overflowLabel'>"+ data + "</span>";
                                }
                                return html;
                         }},             
                         {'name': translator.common.action, 'field': 'action',
                             'formatter': function(data) { 
                                    var menu = new DropDownMenu({ style: "display: none;"});

                                    if(data.status === "ACTIVE") {

                                        var launchVmMenu = new MenuItem({
                                            label: translator.common.launchVm,
                                            onClick: function(){ core.router.go("#/user/server/add/" + data.referenceId + "");}
                                        });
                                        menu.addChild(launchVmMenu);
                                        menu.startup();
                                    }

                                    var deleteMenu = new MenuItem({
                                        label: translator.common.deleteData,
                                        onClick: function(){ VMSnapshotInfo.showDeleteVMSnapshot(data.referenceId)}
                                    });
                                    menu.addChild(deleteMenu);
                                    menu.startup();

                                    var button = new ComboButton({
                                        label: "More",
                                        name: "More",
                                        dropDown: menu
                                    });

                                    return button;                                 
                         },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}

                    ]
                ];
                var vmSnapshotRest = new JsonRest({
                    target: core.getContextPath()+"/api/snapshot/listVMSnapshot/"
                });
                vmSnapshotRest.query().then(function(data) {
                    dojo.byId("vmSnapShotsDiv").style.display = "block";
                    if(data.length  == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                        dojo.byId("noVMSnapshotDiv").style.display = "block"; 
                        dojo.byId("vmSnapShotListMsg").innerHTML = "";
                    } else {
                        dojo.byId("vmSnapShotListMsg").innerHTML = "";
                        dojo.byId("noVMSnapshotDiv").style.display = "none";
                        dojo.forEach(data, function(vmsnapData) {                    
                            vmSnapshotList.newItem({  
                                id:vmsnapData.referenceId,
                                name:vmsnapData.name,
                                status:vmsnapData.status,
                                vm:vmsnapData.vm,
                                format:vmsnapData.diskFormat,
                                cost:vmsnapData.cost,
                                public: vmsnapData.isPublic === true ? "Yes" : "No",
                                protected: vmsnapData.isProtected === true ? "Yes" : "No",
                                action: vmsnapData
                            });                                                    
                        });
                        dojo.byId("noVMSnapshotDiv").style.display = "none"; 
                        dojo.byId("vmSnapShotList").innerHTML = "";
                        var vmSnapshotGridWidget = new EnhancedGrid({                                
                            id: 'vmSnapShotListGridWidget',
                            "class": "span12",
                            store: vmSnapshotList,                
                            structure: vmSnapshotLayout,            
                            autoHeight: true,
                            plugins: core.getGridConfig().plugins
                        });
                        vmSnapshotGridWidget.placeAt("vmSnapShotList");
                        vmSnapshotGridWidget.startup();                          
                    }            
                });
        
            } 
        });
    },
    enableMonthlyCost: function() {
        var formElements = dojo.query("#vmSnapBillingTypeDiv input:checked");
        var checkedRadioId = dijit.byId(formElements[0].id);        
        var billingType = dijit.byId(checkedRadioId).value;
        if(billingType === "monthly") {  
            dojo.query("#vmSnapMonthCostDiv").removeClass("hide_text", true);                                                          
        } else {
            dojo.query("#vmSnapMonthCostDiv").toggleClass("hide_text", true);                  
        }                
    },
    showCreateVMSnapshotDialog: function() {
        
        var miscRestStore = new JsonRest({
            target: core.getContextPath()+"/api/miscellaneousOffer"
        });        
        var currentRegion = dojo.byId("selectedRegionID").value;       
        var cost = 0.00;
        var monthCost = 0.00;
        
        miscRestStore.query({id: 1}).then(function (miscData) {            
            dojo.forEach(miscData[0].miscellaneousOfferRegionCosts, function (el) {                
                if(Number(currentRegion) === el.region.id) {                    
                    cost = el.cost;
                    monthCost = (el.cost * 720).toFixed(2); 
                }
            });   
            dojo.byId("imageSnapshotMonthCost").innerHTML = monthCost + " "+ translator.common.gbPerMonth;
            dojo.byId("imageSnapshotCost").innerHTML = cost + translator.common.billingCostPeGBHR;
        });
        
        var billingTypeConfigRestStore = new JsonRest({
                target: core.getContextPath() + "/api/config/billingType"
        });
        billingTypeConfigRestStore.query().then(function (data) {
            if (data[0].monthlyBillingEnabled === "true") {
                dojo.query("#vmSnapBillingTypeDiv").removeClass("hide_text", true);
            } else {
                dojo.query("#vmSnapBillingTypeDiv").toggleClass("hide_text", true);
            }
        });
        
        dijit.byId("addVMSnapshotForm").reset();
        dijit.byId("addVMSnapshotDialog").show();
    },
    closeCreateVMSnapshotDialog: function() {
        dijit.byId("addVMSnapshotDialog").hide();
    },
    'createVMSnapshot' : function() {
       
        var vmSnapshotRest = new JsonRest({
            target: core.getContextPath()+"/api/virtualMachine/takeVMSnap/"         
        });
        var pageNode = dojo.byId("addVMSnapshotFormPage");
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
            
            var formElements = dojo.query("#vmSnapBillingTypeDiv input:checked");
            var checkedRadioId = dijit.byId(formElements[0].id);        
            var billingType = dijit.byId(checkedRadioId).value;
            
            dijit.byId('addVMSnapshotDialog').hide();
            dijit.byId("snapshotLoader").show();
            var currentRegion = dojo.byId("selectedRegionID").value; 
            vmSnapshotRest.add({
                referenceId: dijit.byId("VMsnapshotInstanceList").value,
                name:dijit.byId("VMSnapshotName").value,
                billingType : billingType,
                regionId : currentRegion
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {                
                    if(resultData.result === "OK") {                      
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotCreated,"message");
                        registry.byId("userToaster").show();                                               
                        
                        dijit.byId("snapshotLoader").hide();
                        VMSnapshotInfo.populateGridValues();
                    } else if(resultData.result == "Pending") {
                        
                    } else  if(resultData.result == "FAILED") {
                        document.body.removeAttribute("style"); 
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotDeletedSuccess,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("snapshotLoader").hide();
                    } else {
                        document.body.removeAttribute("style");
                        registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotCreateVmSnapshot,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("snapshotLoader").hide(); 
                    }
                });
            });
        }
    },
    showDeleteVMSnapshot: function (id) {
        dijit.byId("deleteVMSnap").show();
        dojo.byId("currentVMSnap").value = id;
    },
    closeDeleteVMSnapshot: function () {
        dijit.byId("deleteVMSnap").hide();
    },
    deleteVMSnapshot: function() {           
        var vmSnapshotRest = new JsonRest({
            target: core.getContextPath()+"/api/snapshot/deleteVMSnap/"
        });        
        dijit.byId("deleteVMsnapshotLoader").show();
        vmSnapshotRest.add({
            referenceId:dojo.byId("currentVMSnap").value
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.result == "OK") {                      
                    dijit.byId("deleteVMsnapshotLoader").hide();
                    dijit.byId("deleteVMSnap").hide();
                    registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.vmSnapshotDeletedSuccess,"message");
                    registry.byId("userToaster").show();        
                    VMSnapshotInfo.populateGridValues();
                    
                } else if(resultData.result == "Pending") {
                } else  if(resultData.result == "FAILED") {
                    registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotDeleteVMSnapsot,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("deleteVMsnapshotLoader").hide();
                    dijit.byId("deleteVMSnap").hide();
                } else {
                    registry.byId("userToaster").setContent(translator.user.snapshot.vmSnapshot.cannotDeleteVMSnapsot,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("deleteVMsnapshotLoader").hide(); 
                    dijit.byId("deleteVMSnap").hide();                
                }
            });
        });
    },

};

var VolumeSnapshotInfo = {
     'gotoList': function() {
        dijit.byId("addVolumeSnapshotDialog").hide();
        dijit.byId("volumeSnapshotLoader").hide();
        dijit.byId("volumeSnapshotDeleteLoader").hide();
        
       
    },
    'init': function () {
        if(dijit.byId("volumeListForSnapWidget")) {
            dijit.byId("volumeListForSnapWidget").destroyRecursive();                                
        }
        
        var volumeOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        }; 
//        dojo.byId("volumeSnapshotListDiv").innerHTML = "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/> </br> <p>" + translator.user.loader.volumeSnapshotLoader + "</p>";
        var volumeList = new ItemFileWriteStore({data: volumeOptions}); 
        
        var volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        });
        volumeList.newItem({id: "",name: ""});
        volumeRestStore.query().then(function(data) {
            if(data.length == 0 || data == "undefined" || data == undefined)  { 
                dojo.byId("volumeSnapShotsDiv").style.display = "none";
                dojo.byId("noVolumeFoundMessageBox").style.display = "block";
            } else {                            
                dojo.byId("volumeSnapShotsDiv").style.display = "block";
                dojo.byId("noVolumeFoundMessageBox").style.display = "none";
                dojo.forEach(data, function(volumeData) {  
                volumeList.newItem({
                    id: volumeData.referenceId,
                    name: volumeData.name,
                    description: volumeData.description,
                    size: volumeData.size,
                    status: volumeData.status, 
                    volumeType: volumeData.volumeType, 
                    availabilityZone: volumeData.availabilityZone,                              
                    action: volumeData
                    });                   
                });                


            var volumeWidget = new FilteringSelect({
            store : volumeList,
            id:"volumeListForSnapWidget",
            placeHolder:translator.common.selectVolume
            });
            volumeWidget.placeAt("snapshotVolumeList");
            volumeWidget.startup();
            } 
        });
        
        
        
        
    },

    populateGridValues : function() {
        dojo.byId("noVolumeFoundMessageBox").style.display = "none";
        dojo.byId("volumeSnapshotList").innerHTML = "";
        dojo.byId("noVolumeSnapshotDiv").style.display = "none";
        dojo.byId("volumeSnapshotListMsg").innerHTML = "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/> </br> <p>" + translator.user.loader.volumeSnapshotLoader + "</p>";
         if(dijit.byId("volumeListForSnapWidget")) {
            dijit.byId("volumeListForSnapWidget").destroyRecursive();                                
        }
        
        var volumeOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        }; 
//        dojo.byId("volumeSnapshotList").innerHTML = "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/> </br> <p>" + translator.user.loader.volumeSnapshotLoader + "</p>";
        var volumeList = new ItemFileWriteStore({data: volumeOptions}); 
        
        var volumeRestStore = new JsonRest({
            target: core.getContextPath()+"/api/volume/"
        });
//        volumeList.newItem({id: "",name: ""});
        volumeRestStore.query().then(function(data) {
            if(data.length == 0 || data == "undefined" || data == undefined)  { 
                dojo.byId("volumeSnapShotsDiv").style.display = "none";
                dojo.byId("noVolumeFoundMessageBox").style.display = "block";
                dojo.byId("volumeSnapshotListMsg").innerHTML = "";
            } else {                            
                dojo.byId("noVolumeFoundMessageBox").style.display = "none";
                dojo.forEach(data, function(volumeData) {  
                volumeList.newItem({
                    id: volumeData.referenceId,
                    name: volumeData.name,
                    description: volumeData.description,
                    size: volumeData.size,
                    status: volumeData.status, 
                    volumeType: volumeData.volumeType, 
                    availabilityZone: volumeData.availabilityZone,                              
                    action: volumeData
                    });                   
                });                


            var volumeWidget = new FilteringSelect({
            store : volumeList,
            id:"volumeListForSnapWidget",
            placeHolder:translator.common.selectVolume
            });
            volumeWidget.placeAt("snapshotVolumeList");
            volumeWidget.startup();
        
                

                dojo.byId("volumeSnapshotListMsg").innerHTML = "<img src='images/vmload.gif' alt='Volume Loading' height='36' width='100'/> </br> <p>" + translator.user.loader.volumeSnapshotLoader + "</p>";

                if(dijit.byId("volumeSnapShotListGridWidget")) {
                    dijit.byId("volumeSnapShotListGridWidget").destroyRecursive();                                
                }
                dojo.byId("noVolumeSnapshotDiv").style.display = "none";

                var volumwSnapshotData = {
                    items: []
                };
                var volumeSnapshotsList = new ItemFileWriteStore({data: volumwSnapshotData});
                var volumeSnapshotLayout = [
                    [
                        {'field': 'id', 'hidden': 'true'},
                        {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                        {'name': translator.common.description, 'field': 'description', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                        {'name': translator.user.grid.storage.layout.size, 'field': 'size', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                        {'name': translator.common.status.name, 'field': 'status', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                        {'name': translator.common.volumeName, 'field': 'volumeName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},                
                        {'name': translator.common.action, 'field': 'action',
                             'formatter': function(data) { 
                                    var menu = new DropDownMenu({ style: "display: none;"});


                                    if(data.status !== "error") {
                                        var createVolumeMenu = new MenuItem({
                                            label: translator.common.createVolume,
                                            onClick: function(){ core.router.go("#/user/volume/add/" + data.referenceId + "");}
                                        });
                                        menu.addChild(createVolumeMenu);
                                        menu.startup();

                                    }

                                    var deleteMenu = new MenuItem({
                                        label: translator.common.deleteData,
                                        onClick: function(){ VolumeSnapshotInfo.showDeleteVolumeSnapshot(data.referenceId)}
                                    });
                                    menu.addChild(deleteMenu);
                                    menu.startup();

                                    var button = new ComboButton({
                                        label: "More",
                                        name: "More",
                                        dropDown: menu
                                    });

                                    return button;                                 
                         },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}

                    ]
                ];
                var volumeSnapshotRest = new JsonRest({
                    target: core.getContextPath()+"/api/snapshot/listVolumeSnapshot/"
                });
                volumeSnapshotRest.query().then(function(data) {
                    dojo.byId("volumeSnapShotsDiv").style.display = "block";
                    if(data.length  == 0 || data == undefined || data == "undefined" || data == "" || data == " ") {
                        dojo.byId("noVolumeSnapshotDiv").style.display = "block"; 
                        dojo.byId("volumeSnapshotListMsg").innerHTML = "";
                    } else {
                        dojo.byId("volumeSnapshotListMsg").innerHTML = "";
                        dojo.byId("noVolumeSnapshotDiv").style.display = "none";
                        dojo.forEach(data, function(volumeSnapData) {                    
                            volumeSnapshotsList.newItem({  
                                id:volumeSnapData.referenceId,
                                name:volumeSnapData.name,
                                description:volumeSnapData.description,
                                size:volumeSnapData.size,
                                status:volumeSnapData.status,
                                volumeName:volumeSnapData.volumeName,
                                action: volumeSnapData
                            });                                                    
                        });
                        dojo.byId("noVolumeSnapshotDiv").style.display = "none"; 
                        dojo.byId("volumeSnapshotListMsg").innerHTML = "";
                        var volumeSnapshotGridWidget = new EnhancedGrid({                                
                            id: 'volumeSnapShotListGridWidget',
                            "class": "span12",
                            store: volumeSnapshotsList,                
                            structure: volumeSnapshotLayout,            
                            autoHeight: true,
                            plugins: core.getGridConfig().plugins
                        });
                        volumeSnapshotGridWidget.placeAt("volumeSnapshotList");
                        volumeSnapshotGridWidget.startup();                          
                    }            
                });
            } 
        });    
    },
    showCreateVolumeSnapshotDialog: function() {
        
//        if(dijit.byId("volumeSnapShotListGridWidget")) {
//            dijit.byId("volumeSnapShotListGridWidget").destroyRecursive();                                
//        }
        
        dijit.byId("addVolumeSnapshotForm").reset();
        dijit.byId("addVolumeSnapshotDialog").show(); 
        
        
    },
    closeCreateVolumeSnapshotDialog: function() {
        dijit.byId("addVolumeSnapshotDialog").hide();
    },
    'createVolumeSnapshot' : function() {
       
        var volumeSnapshotRest = new JsonRest({
            target: core.getContextPath()+"/api/snapshot/takeVolumeSnap"         
        });
        var pageNode = dojo.byId("addVolumeSnapshotFormPage");
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
            dijit.byId('addVolumeSnapshotDialog').hide();
            dijit.byId("volumeSnapshotLoader").show();
            volumeSnapshotRest.add({
                name: dijit.byId("VolumeSnapshotName").value,
                description: dijit.byId("volumeSnapshotDescription").value,
                volumeId: dijit.byId("volumeListForSnapWidget").value,
            }).then(function(data) {

                dojo.forEach(data, function(resultData) {                
                    if(resultData.result === "OK") {                      
                        registry.byId("userToaster").setContent(translator.user.snapshot.volumeSnapshot.Created,"message");
                        registry.byId("userToaster").show();                                               
                       
                        dijit.byId("volumeSnapshotLoader").hide();
                        VolumeSnapshotInfo.populateGridValues();
                    } else if(resultData.result == "Pending") {
                        
                    } else  if(resultData.result == "FAILED") {

                        dijit.byId("volumeSnapshotLoader").hide();

                    } else {
                        document.body.removeAttribute("style");
                        registry.byId("userToaster").setContent(translator.user.snapshot.volumeSnapshot.cannotCreateVolumeSnapshot,"error");
                        registry.byId("userToaster").show();
                        dijit.byId("volumeSnapshotLoader").hide(); 

                    }
                });
            });
        }
    },
    showDeleteVolumeSnapshot: function (id) {
        dijit.byId("deleteVolumeSnap").show();
        dojo.byId("currentVolumeSnap").value = id;
    },
    closeDeleteVolumeSnapshot: function () {
        dijit.byId("deleteVolumeSnap").hide();
    },
    deleteVolumeSnapshot: function() {           
        var volumeSnapshotRest = new JsonRest({
            target: core.getContextPath()+"/api/snapshot/deleteVolumeSnap/"
        });  
        dijit.byId("deleteVolumeSnap").hide();
        dijit.byId("volumeSnapshotDeleteLoader").show();
        var id = dojo.byId("currentVolumeSnap").value;
        volumeSnapshotRest.remove(id).then(function(data) {
            
                if(data == "OK") {                      
                    dijit.byId("volumeSnapshotDeleteLoader").hide();
                    registry.byId("userToaster").setContent(translator.user.snapshot.volumeSnapshot.deletedSuccess,"message");
                    registry.byId("userToaster").show();        
                    VolumeSnapshotInfo.populateGridValues();
                    
                } else {
                    registry.byId("userToaster").setContent(translator.user.snapshot.volumeSnapshot.cannotDeleted,"error");
                    registry.byId("userToaster").show();
                    dijit.byId("volumeSnapshotDeleteLoader").hide();  
                    VolumeSnapshotInfo.populateGridValues();
                }
        });
    },
}

