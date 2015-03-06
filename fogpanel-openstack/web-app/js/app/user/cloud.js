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
    "List/ListItem"
], function(dojo, i18n, translator, dijit, JsonRest, registry, FilteringSelect, Select,ItemFileWriteStore, DataGrid, EnhancedGrid, HorizontalSlider, domConstruct, Chart, Pie, PlotKitGreen, Tooltip, MoveSlice, Default, Lines, 
    Magnify, theme, ColumnsPlot, Highlight, connect, Menu, MenuItem, ComboButton, DropDownButton, DropDownMenu) {
    window.translator = translator;
    window.JsonRest = JsonRest;   
    window.Menu = Menu;
    window.MenuItem = MenuItem;
    window.DropDownButton = DropDownButton;
    window.DropDownMenu = DropDownMenu;
    window.Magnify = Magnify;
    window.registry = registry;
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
        name:"cloud",
        module: "user",
        filePath: "/js/app/user/cloud.js",
        layout: {
            name: "",
            containerId: "content"
        },
        scaffold: false
    },
    {   
        "index": action(function() {
            core.ui.loadTemplate("cloudMenuContainer", core.ui.getContentId());            
//            allCountList.populateValues();            
        }),
        "sshKey": action(function() {
            if(dijit.byId("sshKeyForm")) {
                dijit.byId("sshKeyForm").destroyRecursive();
                dijit.byId("privateKeyDialog").destroyRecursive();                                       
            }
            if(dijit.byId("serviceTooltipDialogue")) {
                dijit.byId("serviceTooltipDialogue").destroyRecursive();
            } 
            if(dijit.byId("deleteSshDialogAlert")) {
                dijit.byId("deleteSshDialogAlert").destroyRecursive();
            }
            if(dijit.byId("privateKeyDeleteDialog")) {
                dijit.byId("privateKeyDeleteDialog").destroyRecursive();
            }
            core.ui.loadTemplate("SSHKeyPage", core.ui.getContentId()); 
            AddSSHKey.init();
//            AddSSHKey.populateValues();
        })        
    });
}); 
var AddSSHKey = {
     deletePrivateKeyFromView : false,
    'init' : function() { 
        
        var isSSHKeyListEmptyRest = new JsonRest({
            target: core.getContextPath() + "/api/keypair/isKeyListEmpty"
        });
        
        dojo.byId("noSSHKey").style.display = "none";
        dojo.byId("sshKeyGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.user.loader.sshKeyLoader+"</p>"; 
        
        isSSHKeyListEmptyRest.query().then(function(data) {
           dojo.forEach(data, function(resultData){
              if(resultData.result == "OK") {
                  AddSSHKey.populateValues();
              } 
           });
        });
        
        
    },
    'sshKeyDelete' : function() {
        dijit.byId("deleteSshDialogAlert").hide();
        var sshKeyName = dojo.byId("currentSshKeyName").value ;
        var sshKeyStore = new JsonRest({
            target: core.getContextPath()+"/api/keypair/delete"
        });    
        dojo.byId('deleteSSHKeyBtnDiv').style.display = 'none';
        dojo.byId('sshDeleteKeyLoader').style.display =  'block';
        sshKeyStore.add({
            sshKeyName:  sshKeyName
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if(resultData.result == "OK") {
                    dijit.byId("privateKeyDialog").hide();
                    registry.byId("userToaster").setContent(translator.user.SSHKey.SSHKeyDeleted, "message");
                    registry.byId("userToaster").show();
                    dijit.byId("sshKeyForm").reset();
                    AddSSHKey.populateValues();
                    dojo.byId('deleteSSHKeyBtnDiv').style.display = 'block';
                    dojo.byId('sshDeleteKeyLoader').style.display =  'none';
                } else if(resultData.result == "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("sshKeyForm").reset();
                    dojo.byId('deleteSSHKeyBtnDiv').style.display = 'block';
                    dojo.byId('sshDeleteKeyLoader').style.display =  'none';
                } else if(resultData.result == "ALREADY_MAPPED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("sshKeyForm").reset();
                    dojo.byId('deleteSSHKeyBtnDiv').style.display = 'block';
                    dojo.byId('sshDeleteKeyLoader').style.display =  'none';
                } else {
                    registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("sshKeyForm").reset();
                    dojo.byId('deleteSSHKeyBtnDiv').style.display = 'block';
                    dojo.byId('sshDeleteKeyLoader').style.display =  'none';
                }
            });                
        }); 
    }, 
    'populateValues': function() {      
        var sshKeyStore = new JsonRest({
            target: core.getContextPath()+"/api/keypair/list"
        }); 
        dojo.byId("noSSHKey").style.display = "none";
        dojo.byId("sshKeyGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.user.loader.sshKeyLoader+"</p>";         
        
        var gridData = {
            items: []
        };
        var gridDataList = new ItemFileWriteStore({data: gridData});
        var gridLayout = [
            [
                {'name': translator.common.name, 'field': 'name', 'width': '200px', datatype:"string",  autoComplete: true,'formatter': function(data) {                                                 
                        return  "<span class='bold'>" + data + "</span>";
                    }
                },
                {'name': translator.user.SSHKey.fingerPrint , 'field': 'fingerprint', 'width': '600px', datatype:"string",  autoComplete: true},
                {'name': translator.common.action, 'field': 'action', 'width': '100%', 'formatter' : function(data) {
                        
                        
                    var menu = new DropDownMenu({ style: "display: none;"});
                    
                    if(data.privatekey != null) {

                        var viewMenu = new MenuItem({
                            label: translator.common.view,
                            onClick: function() { 
                                 AddSSHKey.viewSSHKey(data);
                            }
                        });

                        menu.addChild(viewMenu);
                        menu.startup();
                        
                        var deletePrivateKeyMenu = new MenuItem({
                            label: translator.common.deletePrivateKey,
                            onClick: function() { 
                                 AddSSHKey.deletePrivatKeyAlert(data);
                            }
                        });

                        menu.addChild(deletePrivateKeyMenu);
                        menu.startup();

                    }    

                    var deleteMenu = new MenuItem({
                        label: translator.common.deleteData,
                        onClick: function() {
                            var sshKeyName = data.name;
                            AddSSHKey.sshKeyDeleteAlert(sshKeyName);
                        }
                    });

                    menu.addChild(deleteMenu);
                    menu.startup();

                    var button = new ComboButton({
                        label: "More",
                        name: "More",
                        dropDown: menu
                    });

                    return button;   
                                       
                }}
            ]
        ];        
        sshKeyStore.query().then(function(data) {
            if(data.length === 0) {
                dojo.byId("sshKeyGrid").innerHTML = "";
                dojo.byId("noSSHKey").style.display = "block";
            } else {
                dojo.forEach(data, function(el) {
                if(el.name) {                    
                    dojo.byId("sshKeyGrid").innerHTML = "";
                    dojo.byId("noSSHKey").style.display = "none";
                    
                    gridDataList.newItem({
                        'name': el.name,
                        'fingerprint': el.fingerprint,
                        'action': el,
                    });                
                    dojo.byId("sshKeyGrid").innerHTML = "";                                
                    var depGrid = new EnhancedGrid({
                        "class" : "span12",
                        store: gridDataList,
                        structure: gridLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });                
                    depGrid.placeAt("sshKeyGrid");
                    depGrid.startup();  
                } else {
                    dojo.byId("sshKeyGrid").innerHTML = "";
                    dojo.byId("noSSHKey").style.display = "block";
                }
                               
            });
            }
            
        });         
    },
    'deletePrivatKeyAlert': function(data) {
        
        if(data !== null) {
            dojo.byId("currentSshId").value = data.id;
        } else {
            AddSSHKey.deletePrivateKeyFromView = true;
        }
        
        dijit.byId("privateKeyDeleteDialog").show();
    },
    'deletePrivateKey' : function() {
      var deletePrivateKeyRest = new JsonRest({
          target: core.getContextPath()+"/api/keypair/deletePrivateKey/"
      });
      
     var id = dojo.byId("currentSshId").value;

      deletePrivateKeyRest.remove(id).then(function(data) {

            if(data == "OK") {
                dijit.byId("privateKeyDeleteDialog").hide();
                registry.byId("userToaster").setContent(translator.user.SSHKey.SSHPrivateKeyDeleted, "message");
                registry.byId("userToaster").show();
                AddSSHKey.populateValues();
                
                    // from view means close the view dilaog after delete
               if(AddSSHKey.deletePrivateKeyFromView) {
                   AddSSHKey.cancelViewDialog();
               }

            } else {
                dijit.byId("privateKeyDeleteDialog").hide();
                registry.byId("userToaster").setContent(translator.user.SSHKey.SSHPrivateKeyNotDeleted, "error");
                registry.byId("userToaster").show();

            } 
           

      })
    },
    'cancelPrivateKeyDeleteDialog': function(){
        dijit.byId("privateKeyDeleteDialog").hide();
    },
    'createSShKey' : function() {                        
        var sshKeyStore = new JsonRest({
            target: core.getContextPath()+"/api/keypair"
        });
        
        var pageNode = dojo.byId("sshKeyPage");
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
            dojo.byId('createSSHKeyBtnDiv').style.display = 'none';
            dojo.byId('sshKeyLoader').style.display =  'block';
            sshKeyStore.add({
                name:  dijit.byId("sshKeyName").getValue()
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    dojo.byId('createSSHKeyBtnDiv').style.display = 'block';
                    dojo.byId('sshKeyLoader').style.display =  'none';
                    if(resultData.result == "OK") {
                        registry.byId("userToaster").setContent(translator.user.SSHKey.sshKeyCreated, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("sshKeyForm").reset();
                        AddSSHKey.populateValues();
                    } else if(resultData.result == "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                    } else {
                        registry.byId("userToaster").setContent(resultData.localizedMessage, "error");
                        registry.byId("userToaster").show();
                    }                    
                });                
            });
        } 
    },
    'cancel': function() {  
        dijit.byId("sshKeyForm").reset();
    },
    'cancelViewDialog': function() {
        dijit.byId("privateKeyDialog").hide();
    },
    'viewSSHKey': function(data) {          
        document.getElementById("sshKeyDownload").setAttribute("href", ""+core.getBaseURL()+"/pdf/getSSHKey?keyId="+data.id+""); 
        
        //setting current id
        dojo.byId("currentSshId").value = data.id;
        
        dojo.byId("sshId").value = data.id;
        dojo.byId("sshPrivateKey").value = data.privatekey;
        dijit.byId("privateKeyDialog").setAttribute('title', data.name);
        dijit.byId("privateKeyDialog").show();
    },
    'sshKeyDeleteAlert':function(sshKeyName) {
      dijit.byId("deleteSshDialogAlert").show();
      dojo.byId("currentSshKeyName").value = sshKeyName; 
    },
    'cancelDeleteAlert': function() {
      dijit.byId("deleteSshDialogAlert").hide();
    },
};


