window.ObjectInfo = ObjectInfo;
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
    "dojo/currency",
    "dojox/form/CheckedMultiSelect",
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
    "FogPanel/Breadcrumb",    
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
            Highlight, connect, Menu, MenuItem,ComboButton, DropDownButton, DropDownMenu, localeCurrency, CheckedMultiSelect) {
        window.translator = translator;
        window.JsonRest = JsonRest;    
        window.Magnify = Magnify;
        window.registry = registry;
        window.Menu = Menu;
        window.MenuItem = MenuItem;
        window.DropDownButton = DropDownButton;
        window.DropDownMenu = DropDownMenu;
        window.CheckedMultiSelect = CheckedMultiSelect;
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
        window.localeCurrency = localeCurrency;
        window.GraphUpdateInterval  = "";
        controller({ 
            name:"storage",
            module: "user",
            filePath: "/js/app/user/storage.js",
            layout: {
                name: "",
                containerId: "content"
            },
            scaffold: false
        },
        {   
            "index": action(function() {                
                 core.ui.loadTemplate("stoageMenuContainer", core.ui.getContentId());    
            }),
            "container": action(function() {                
                if(dijit.byId("addContainerForm")) {
                    dijit.byId("addContainerForm").destroyRecursive();
                } 
                if(dijit.byId("deleteObjectContainerDialog")) {
                    dijit.byId("deleteObjectContainerDialog").destroyRecursive();
                } 
                if(dijit.byId("objectContainerAccessDialog")) {
                    dijit.byId("objectContainerAccessDialog").destroyRecursive();
                } 
                if(dijit.byId("containerActionLoader")) {
                    dijit.byId("containerActionLoader").destroyRecursive();
                } 
                core.ui.loadTemplate("containerList", core.ui.getContentId());   
                ContainerInfo.populateValues();
            }),
            "viewContainer" : action(function (containerId) {
                
                if(dijit.byId("addPseudoFolderForm")) {
                    dijit.byId("addPseudoFolderForm").destroyRecursive();
                } 
                if(dijit.byId("deleteContainerObjectDialog")) {
                    dijit.byId("deleteContainerObjectDialog").destroyRecursive();
                } 
                if(dijit.byId("uplaodObjectForm")) {
                    dijit.byId("uplaodObjectForm").destroyRecursive();
                }  
                if(dijit.byId("objectViewDialog")) {
                    dijit.byId("objectViewDialog").destroyRecursive();
                }  
                if(dijit.byId("uploadObjectFileName")) {
                    dijit.byId("uploadObjectFileName").destroyRecursive();
                } 
                if(dijit.byId("copyObjectDialog")) {
                    dijit.byId("copyObjectDialog").destroyRecursive();
                } 
                if(dijit.byId("objectActionLoader")) {
                    dijit.byId("objectActionLoader").destroyRecursive();
                } 
                if(dijit.byId("viewPageContainerAccessDialog")) {
                    dijit.byId("viewPageContainerAccessDialog").destroyRecursive();
                } 
                if(dijit.byId("viewPageDeleteContainerDialog")) {
                    dijit.byId("viewPageDeleteContainerDialog").destroyRecursive();
                } 
                
                core.ui.loadTemplate("viewContainer", core.ui.getContentId());   
                
                var containerRestStore = new JsonRest({
                    target: core.getContextPath() + "/api/objectStore/container/view" 
                });        
                
                containerRestStore.query({containerId: containerId}).then(function (data) {    
                                        
                    ObjectInfo.populateValues(containerId, null, data[0].name, true);                                    
                                                  
                 });  
                
                
                
            })
    }); 
}); 

var ObjectInfo = {   
    'onUploadComplete': function () {
        var id = dojo.byId("hidden-upload-frame").value;
        var uploadObject = dojo.byId("uploadObject").value;
       
        dojo.byId("hidden-upload-frame").style.display = "none";
//        if(uploadObject) {
//            console.log("success"); 
//            dojo.byId("hidden-upload-frame").style.display = "block";
//        }
      
    },
    'uploadValidation': function(widget) {
        if(widget.value == "") {
//            widget.focus();
        }
    },
    'objectUploadSuccess': function() {
  
       var iframe = dojo.byId("hidden-upload-frame").style.display = "none";
       
       dijit.byId("uploadObjectFileName").reset();
       dojo.byId("objectFile").value = "";
       dojo.byId("editedObjectName").value = "";
       
       var containerId = dojo.byId("currentContainerId").value;
       var pathName = dojo.byId("currentPathName").value;
       var objectName = dojo.byId("currentObjectName").value;
       
       registry.byId("userToaster").setContent(translator.common.message.objectUploadSuccess, "message");
       registry.byId("userToaster").show();
       
       dojo.byId("uploadObjectButtonDiv").style.display = "block";
       dojo.byId("uploadObjectButtonLoader").style.display = "none";
         
       ObjectInfo.populateValues(containerId, pathName, objectName, false);

    }, 
    'closeErrorDialog': function() {

       var iframe = dojo.byId("hidden-upload-frame").style.display = "none";

    }, 
    'noFileSelected': function() {
        var iframe = dojo.byId("hidden-upload-frame").style.display = "none";
        dojo.byId("uploadObjectButtonDiv").style.display = "block";
        dojo.byId("uploadObjectButtonLoader").style.display = "none";
        registry.byId("userToaster").setContent(translator.common.message.fileNotSelected, "error");
        registry.byId("userToaster").show();
    },
    'fileUploadError': function() {
       var iframe = dojo.byId("hidden-upload-frame").style.display = "none";
       dojo.byId("uploadObjectButtonDiv").style.display = "block";
       dojo.byId("uploadObjectButtonLoader").style.display = "none";
       registry.byId("userToaster").setContent(translator.common.message.fileNotUploaded, "error");
       registry.byId("userToaster").show(); 
    },
    'closeNoFileSelectedDialog': function () {
        var iframe = dojo.byId("hidden-upload-frame").style.display = "none";
    },
    'gotoObjectList': function() {
        dijit.byId("objectActionLoader").hide();
    },
    checkBreadcrumbWidgetStat : function (currentStatus) {
        if(currentStatus === true) {
            var breadcrumbNode = dojo.byId("objectStoreBreadcrumbDiv");
            var widgets = dijit.registry.findWidgets(breadcrumbNode);
            console.log("Widget Length" + widgets.length);
            dojo.forEach(widgets, function (el, index) {            
                if(index + 1 === widgets.length) {                               
                    console.log("object true");
                    el.addDiableClassTag();
                    el.onClick = function () {};
                } else {               
                    console.log("object false");
                    el.removeDisableClass();
                    el.onClick = function () {ObjectInfo.onBreadcrumbClick(el);};            
                }
                el.additionalProperties.itemNo = index;
            });              
        }
    },    
    showObjects : function (containerId, containerName, pathName, objectName) {
        
        console.log("containerId: "+containerId);
        console.log("pathName: "+pathName);
        console.log("name: "+objectName);
//        dojo.byId("objectPathName").innerHTML = containerName + " :/"+pathName;
        dojo.byId("selectedPathName").value = pathName;
        dojo.byId("selectedObjectName").value = objectName;
        dojo.byId("selectedContainerId").value = containerId;
        ObjectInfo.populateValues(containerId, pathName, objectName, true);                                                      
    },
    onBreadcrumbClick : function (currentBreadCrumb) {        
        var breadcrumbNode = dojo.byId("objectStoreBreadcrumbDiv");
        var widgets = dijit.registry.findWidgets(breadcrumbNode);
        
        dojo.forEach(widgets, function (el, index) {
            if(index > currentBreadCrumb.additionalProperties.itemNo) {               
                el.destroyRecursive();
            } 
        });  
        currentBreadCrumb.addDiableClassTag();
        currentBreadCrumb.onClick = function () {};
        ObjectInfo.populateValues(currentBreadCrumb.additionalProperties.containerId , currentBreadCrumb.additionalProperties.pathName, currentBreadCrumb.additionalProperties.name, false);
    },
    showAvailableObjects : function () {
        ObjectInfo.populateValues(5, null, null, false);
    },
    populateBreadCrumb : function (currentContainerId, pathName, objectName) {
        
        var breadcrumbWidget  = new FogPanel.Breadcrumb({   
            additionalProperties : {
                containerId: currentContainerId,
                pathName: pathName,
                name : objectName,
                breadcrumbStat : true
            },
            onClick : function () {
                ObjectInfo.onBreadcrumbClick(this);                
            }
        });                
        breadcrumbWidget.placeAt("objectStoreBreadcrumbDiv");
        breadcrumbWidget.startup(); 
        return breadcrumbWidget;
    },
    populateValues : function (containerId, pathName, objectName, isBreadcrumb) {                
        var containerRestStore = new JsonRest({
           target: core.getContextPath() + "/api/objectStore/container/view" 
        });
                
        dojo.byId("currentContainerId").value = containerId;
        dojo.byId("currentPathName").value = pathName;
        dojo.byId("currentObjectName").value = objectName;
        
        //Reset all form
        dijit.byId("uploadObjectFileName").reset();
        dojo.byId("objectFile").value = "";
        dijit.byId("uploadObjectFileName").setAttribute('disabled', false);
        dojo.byId("objectUploadSubmitButton").style.display = "block";
        dojo.byId("objectUpdateSubmitButton").style.display = "none";

        dijit.byId("addPseudoFolderForm").reset();
        
        containerRestStore.query({containerId: containerId}).then(function (data) {
            dojo.forEach(data, function(resultData) {
//               dojo.byId("containerNameTag").innerHTML = resultData.name;
               dojo.byId("containerNameInfo").innerHTML = resultData.name; 
               //To set container name to access the object information
               dojo.byId("selectedContainerName").value = resultData.name; 
               dojo.byId("selectedContainerId").value = resultData.containerId; 
               dojo.byId("containerSizeInfo").innerHTML = resultData.size; 
               dojo.byId("containerAccessInfo").innerHTML = resultData.access; 
               dojo.byId("selectedContainerAccess").value = resultData.access; 
               dojo.byId("containerObjectCountInfo").innerHTML = resultData.objectCount; 
               dojo.byId("containerAccountInfo").innerHTML = resultData.accountName; 
               if(resultData.access === "public") {
                   dojo.byId("publicUrlDetail").style.display = "block";
                   dojo.byId("containerPublicUrlInfo").innerHTML = resultData.publicURL; 
                   dojo.byId("containerAccessIcon").setAttribute('title', translator.common.makePrivate)
               } else {
                   dojo.byId("publicUrlDetail").style.display = "none";
                   dojo.byId("containerAccessIcon").setAttribute('title', translator.common.makePublic)
               }
               
               dojo.byId("containerCreatedDateInfo").innerHTML = resultData.createdAt; 
            });
        });
        dojo.byId("noObjectMessageBox").style.display = "none";
        
        dojo.byId("objectListDiv").innerHTML = "<img src='images/vmload.gif' alt="+translator.common.loader.imageLoadError+" height='36' width='100'/> </br> <p>"+translator.user.loader.objectLoader+"</p>";   
        var gridData = {
            items: []
        };
        
        var gridDataList = new ItemFileWriteStore({data: gridData});            
        
        var gridLayout = [
            [               
                {'name': translator.common.name, 'field': 'name', 'width': '300px', datatype:"string",  autoComplete: true, 'formatter': function(data, rowIndex) { 
                        var tagName;
                        
                        if(data.directory == true) {
                            tagName = "<a onclick='ObjectInfo.showObjects(\""+data.containerId+"\",\""+data.containerName+"\",\""+data.pathName+"\",\""+data.name+"\")' title=''>"+data.name+"</a>";
                        } else {
                            tagName = data.name;
                        }
                        
                        return tagName; 
                }},                                 
                {'name': translator.common.folder + " / " + translator.common.size.sizeName + " ( " + translator.common.kb + " )", 'field': 'size', 'width': '300px', 
                        datatype:"string",  autoComplete: true, 'formatter': function(data, rowIndex) {
                    var value;      
                    if(data.directory == true) {
                       value = translator.common.pseudoFolder;
                    } else {
                       value = data.size + " " + translator.common.kb ; 
                    }
                    return value;        
                            
                }},
//                {'name': translator.common.lastModified, 'field': 'lastModified', 'width': '200px', datatype:"string",  autoComplete: true},                                             
                {'name': translator.common.action, 'field': 'action',   'formatter': function(data, rowIndex) {  
                        
                        var menu = new DropDownMenu({ style: "display: none;"});
                        
                        if(data.directory == false) {
                            
                            var viewMenu = new MenuItem({
                                label: translator.common.menu.viewDetails,
                                onClick: function(){ ObjectInfo.view(data.containerId, data.pathName, data.name); }
                            });
                            menu.addChild(viewMenu);
                            menu.startup();
                            
                            var editMenu = new MenuItem({
                                label: translator.common.edit,
                                onClick: function(){ ObjectInfo.edit(data.containerId, data.pathName, data.name); }
                            });
                            menu.addChild(editMenu);
                            menu.startup();
                            
                            var copyMenu = new MenuItem({
                                label: translator.common.copy,
                                onClick: function(){ ObjectInfo.showCopyObjectDialog(data.containerId, data.pathName, data.name); }
                            });
                            menu.addChild(copyMenu);
                            menu.startup();
                            
                            var downloadMenu = new MenuItem({
                                label: translator.common.download,
                                onClick: function(){ ObjectInfo.download(data.containerId, data.pathName, data.name); }
                            });
                            menu.addChild(downloadMenu);
                            menu.startup();
   
                        }   
                 
                            var deleteMenu = new MenuItem({
                                label: translator.common.deleteData,
                                onClick: function(){ ObjectInfo.deleteConfirmation(data.containerId, data.pathName, data.name); }
                            });
                            menu.addChild(deleteMenu);
                            menu.startup();
                            
                            
                            var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                            });
                                                        
                            return button;

                },'width': '100%', datatype:"string",  autoComplete: 'true'}           
        ]
    ];        
        var objectRestStore = new JsonRest({
            target: core.getContextPath()+"/api/objectStore/container/objectList"
        });

        objectRestStore.query({
            containerId: containerId,
            objectName: pathName
        }).then(function(data) {
            if(data.length === 0 || data === "undefined" || data === undefined)  {                  
                dojo.byId("noObjectMessageBox").style.display = "block";                                    
                dojo.byId("objectListDiv").innerHTML = "";
                if (isBreadcrumb === true) {
                    var currentWidget = ObjectInfo.populateBreadCrumb(containerId, pathName, objectName);  
                    if(currentWidget) {
                        ObjectInfo.checkBreadcrumbWidgetStat(currentWidget.additionalProperties.breadcrumbStat);
                    }
                }
            } else {           
                dojo.byId("noObjectMessageBox").style.display = "none";
                dojo.forEach(data, function(objectData) {  
                    gridDataList.newItem({
                        name: objectData,
                        size: objectData,
                        lastModified: objectData.lastModified,                            
                        action: objectData
                    });
                });  
            
                if(dijit.byId("objectGridWidget")) {
                    dijit.byId("objectGridWidget").destroyRecursive();
                }

                dojo.byId("objectListDiv").innerHTML = "";
                var instanceGrid = new EnhancedGrid({
                    id: 'objectGridWidget',
                    store: gridDataList,
                    structure: gridLayout,
                    loadingMessage: translator.user.loader.instanceLoader,
                    noDataMessageL:translator.user.grid.instance.noInstance,
                    autoHeight: true,
                    autoWidth: false,                
                    class:"span12",
                    style: "overflow: hidden",
                    plugins: core.getGridConfig().plugins
                });                     
                instanceGrid.placeAt("objectListDiv");        
                instanceGrid.startup();             
                instanceGrid.resize();      
                if (isBreadcrumb === true) {
                    var currentWidget = ObjectInfo.populateBreadCrumb(containerId, pathName, objectName);
                    if(currentWidget) {
                        ObjectInfo.checkBreadcrumbWidgetStat(currentWidget.additionalProperties.breadcrumbStat);
                    }                    
                }        
                if(pathName) {
                    
                } else {
                    dojo.byId("selectedPathName").value = "";
                }
                ContainerView.populateDetails();
            }             
        }); 
        
    },
    'edit': function(containerId, pathName, objectName) {
       
        var element = document.getElementById("viewContainerDiv")
        element.focus();
        element.scrollIntoView(true);
        dijit.byId("uploadObjectFileName").setAttribute("target","_blank")
        dijit.byId("uploadObjectFileName").setValue(objectName);
        dijit.byId("uploadObjectFileName").setAttribute('disabled', true);
        dijit.byId("uploadObjectFileName").focus();
        dojo.byId("editedObjectName").value = objectName;
        
        dojo.byId("objectUploadSubmitButton").style.display = "none";
        dojo.byId("objectUpdateSubmitButton").style.display = "block";
    },
    'view': function(containerId, pathName, objectName) {
                
        dojo.byId("objectNameInfo").innerHTML = "";
        dojo.byId("objectHashInfo").innerHTML = "";
        dojo.byId("objectContentTypeInfo").innerHTML = "";
        dojo.byId("objectLastModifiedDateInfo").innerHTML = "";
        dojo.byId("objectSizeInfo").innerHTML = "";
        
        var viewObjectRest = new JsonRest({
           target: core.getContextPath() + "/api/objectStore/object/view" 
        });
        
        viewObjectRest.query({
            containerId: containerId,
            pathName: pathName, 
            objectName: objectName
        }).then(function(data) {
           dijit.byId("objectViewDialog").show();
           dojo.forEach(data, function(resultData) {
              dojo.byId("objectNameInfo").innerHTML = resultData.name
              dojo.byId("objectHashInfo").innerHTML = resultData.hashValue
              dojo.byId("objectContentTypeInfo").innerHTML = resultData.mimeType
              dojo.byId("objectLastModifiedDateInfo").innerHTML = resultData.lastModified
              dojo.byId("objectSizeInfo").innerHTML = resultData.size
           });
        });
        
    },
    'addPseudoFolder': function() {
        
        var status = true;
        var pageNode = dojo.byId("addPseudoFolderContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
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
        } else {
            
             dojo.byId("addPseudoFolderDiv").style.display = "none";
             dojo.byId("addPseudoFolderLoader").style.display = "block";
            
             var pseudoFolderRestStore = new JsonRest({
                target: core.getContextPath() + "/api/objectStore/object/createPseudoFolder" 
             });
             var name = dijit.byId("pseudoFolderName").value;
             var containerName = dojo.byId("selectedContainerName").value;
             var objectName = dojo.byId("selectedObjectName").value;
             var pathName = dojo.byId("selectedPathName").value;
             var containerId = dojo.byId("selectedContainerId").value;
             var pseudoFolderName;
             var pathNameSlashPosition = pathName.lastIndexOf("/");
             var pathNameStringSize = pathName.length;
                          
             if(pathName) {
                if(pathNameSlashPosition === pathNameStringSize-1) {
                    pseudoFolderName = pathName + name;
                } else {
                    pseudoFolderName = pathName + "/" + name;
                }
                 
             } else {
                pseudoFolderName = name;
             }
             
             console.log("pathName"+pathName)

             pseudoFolderRestStore.add({
                 name: pseudoFolderName,
                 containerName: containerName,
             }).then(function(data) {
                 
                 dojo.byId("addPseudoFolderDiv").style.display = "block";
                 dojo.byId("addPseudoFolderLoader").style.display = "none";
                 
                dojo.forEach(data, function (resultData) {
                    if(resultData.result == "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.pseudoFolderCreated, "message");
                        registry.byId("userToaster").show();
                        ObjectInfo.populateValues(containerId, pathName, objectName, false); 
                        dijit.byId("addPseudoFolderForm").reset();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.pseudoFolderNotCreated, "error");
                        registry.byId("userToaster").show();
                    }
                });
             });
            
        }                  
        
    },
    'uploadFileUrl': function(uploadObject) {
        console.log("uploadObject: "+uploadObject)
        
        dojo.byId("uploadObject").value = uploadObject;
        if(uploadObject) {
            
        } else {
            dojo.byId("objectFile").focus();
        }
//        
//        var reader = new FileReader();
//        var url = uploadObject.files[0]
//        
//        console.log("uploadObject: "+url.name)
//        console.log("uploadObject: "+uploadObject.files[0])
        
    },
    'uploadObject': function() {
        
        var status = true;
        var pageNode = dojo.byId("uplaodObjectContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                status = false;
                if (!firstNode) {
                    firstNode = el;
                }
            }
        });
        var uploadObject = dojo.byId("uploadObject").value;
        
        if (firstNode) {
            firstNode.focus();
            
            var uploadObjectFileName = dijit.byId("uploadObjectFileName").value;
                        
            return false;
           
        } else {
            if(uploadObject) {
                dojo.byId("uploadObjectButtonDiv").style.display = "none";
                dojo.byId("uploadObjectButtonLoader").style.display = "block";
            }
            return( true );
          
            
        }
        
    },
    'cancelUpload': function() {
        
        dijit.byId("uploadObjectFileName").reset();
        dojo.byId("objectFile").value = "";
        dijit.byId("uploadObjectFileName").setAttribute('disabled', false);
        dojo.byId("objectUploadSubmitButton").style.display = "block";
        dojo.byId("objectUpdateSubmitButton").style.display = "none";
    },
    'cancelFolderCreation': function() {
        dijit.byId("addPseudoFolderForm").reset();
    },
    'showCopyObjectDialog': function(containerId, pathName, objectName) {
        
        dijit.byId("copyObjectDialog").show();
        dijit.byId("destinationObjectName").setValue(objectName + "-copy");
        ObjectInfo.containerList(containerId);

        dojo.byId("copyContainerId").value = containerId;
        dojo.byId("copyPathName").value = pathName;
        dojo.byId("copyObjectName").value = objectName;
    },
    'containerList': function(containerId) {
        
        if(dijit.byId("containerListWidget")) {
            dijit.byId("containerListWidget").destroyRecursive();
        } 
        
        var containerRestStore = new JsonRest({
            target: core.getContextPath() + "/api/objectStore/container/list"
        });
        var containerOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.byId("containerListDivLoader").style.display = "block";
        var containerList = new ItemFileWriteStore({data: containerOptions});
//        imageList.newItem({id: "", name: ""});
        containerRestStore.query().then(function(data) {
            dojo.byId("containerListDivLoader").style.display = "none";
            dojo.forEach(data, function(el) {
                containerList.newItem({id: el.containerId, name: el.name});
            });
            var containerWidget = new FilteringSelect({
                store: containerList,
                id: "containerListWidget",
                value : containerId != null ? containerId: "",
                required: false,
                placeHolder: translator.common.select,
                onChange: function() {
                }
            });
            containerWidget.placeAt("containerListDiv");
            containerWidget.startup();
        });
        
    },
    'copy': function () {
        var status = true;
        var pageNode = dojo.byId("copyObjectFormPage");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
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
        } else {
            
            dijit.byId("copyObjectDialog").hide();
            dijit.byId("objectActionLoader").show();
            
            var copyObjectRest = new JsonRest({
               target: core.getContextPath() + "/api/objectStore/object/copy" 
            });
            
            var sourceContainerId = dojo.byId("copyContainerId").value;
            var sourceObjectName = dojo.byId("copyObjectName").value;
            var sourcePathName = dojo.byId("copyPathName").value;
            
            var destinationConatinerId = dijit.byId("containerListWidget").value
            var destinationPath = dijit.byId("objectDestinationPath").value
            var destinationObjectName = dijit.byId("destinationObjectName").value
 
            copyObjectRest.add({
                sourceContainerId: sourceContainerId,
                sourceObjectName: sourceObjectName,
                destinationConatinerId: destinationConatinerId,
                sourcePathName: sourcePathName,
                destinationPath: destinationPath,
                destinationObjectName: destinationObjectName
                
            }).then(function(data) {
                dijit.byId("objectActionLoader").hide();
               dojo.forEach(data, function (resultData) {
                    if(resultData.result == "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.objectCopied, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("copyObjectForm").reset();
                       
                    } else if(resultData.result == "FAILED") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.objectNotCopied, "error");
                        registry.byId("userToaster").show();
                    } 
               });
            });
        }
    },
    'cancelCopy': function () {
        dijit.byId("copyObjectDialog").hide();
        dijit.byId("copyObjectForm").reset();
    },
    'download': function(containerId, pathName, objectName) {
        
        dojo.byId("selectedContainerId").value = containerId;
        dojo.byId("selectedPathName").value = pathName;
        dojo.byId("selectedObjectName").value = objectName;
        
        var url = core.getBaseURL() + "/objectStore/download?containerId=" +containerId + "&pathName=" + pathName + "&objectName=" + objectName ;
        
        window.open(url, "_blank");
        window.focus(); 
        
//        var downloadRest = new JsonRest({
//            target: core.getContextPath() + "/api/objectStore/object/download"
//        });
//        
//        downloadRest.query({
//            containerId: containerId,
//            pathName: pathName,
//            objectName: objectName
//            
//        }).then(function(data) {
//            dojo.forEach(data, function (resultData) {
//               if(resultData.result === "OK") {
//                  ObjectInfo.OpenInNewTab(resultData.authToken, resultData.downloadUrl)
//               } else {
//                   registry.byId("userToaster").setContent(translator.common.message.notDownloaded, "error");
//                   registry.byId("userToaster").show();
//               }
//            });
//        })
    },
    'OpenInNewTab' : function(authToken, url) {
        var httpRequest = new XMLHttpRequest();
        var formData = new FormData();
        
//        httpRequest.open("GET", "http://localhost:7070/FogPanel/info", true);
        httpRequest.open("GET", url, true);
        
//        httpRequest.setRequestHeader ("Accept", "image/png");
        httpRequest.setRequestHeader ("X-Auth-Token", authToken);
        
        httpRequest.send (formData);
        console.log("download url: "+url)
//        window.open(url, "_blank");
//        window.focus();   
    },
    'deleteConfirmation': function (containerId, pathName, objectName) {
        dijit.byId("deleteContainerObjectDialog").show();
        dojo.byId("copyContainerId").value = containerId;
        dojo.byId("copyPathName").value = pathName;
        dojo.byId("copyObjectName").value = objectName;
    },
    'delete': function () {
        var deleteObjectRest = new JsonRest({
            target: core.getContextPath() + "/api/objectStore/object/delete"
        });
        
        var containerId = dojo.byId("copyContainerId").value;
        var pathName = dojo.byId("copyPathName").value;
        var objectName = dojo.byId("copyObjectName").value;
        
        var currentContainerId = dojo.byId("currentContainerId").value;
        var currentPathName = dojo.byId("currentPathName").value;
        var currentObjectName = dojo.byId("currentObjectName").value;
        
        dijit.byId("deleteContainerObjectDialog").hide();
        dijit.byId("objectActionLoader").show();
        deleteObjectRest.add({
            containerId: containerId,
            pathName: pathName,
            objectName: objectName
        }).then(function(data) {
            dijit.byId("objectActionLoader").hide();
           dojo.forEach(data, function (resultData) { 
                if(resultData.result == "OK") {
                    registry.byId("userToaster").setContent(translator.common.message.ObjectDeleted, "message");
                    registry.byId("userToaster").show();
                    ObjectInfo.populateValues(currentContainerId, currentPathName, currentObjectName, false); 
                } else if(resultData.result == "FAILED") {
                    registry.byId("userToaster").setContent(resultData.message, "error");
                    registry.byId("userToaster").show();
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.ObjectNotDeleted, "error");
                    registry.byId("userToaster").show();
                }
           });
        });
    },
    'closeDeleteDialog': function () {
        dijit.byId("deleteContainerObjectDialog").hide();
    },
    
};

var ContainerInfo = {
    viewContainerDetail : function () {
        dojo.byId("containerDetailDiv").innerHTML = "Detail";
//        dojo.query("#containerDetailDiv").removeClass("hide_text", true);
//        dojo.query("#containerDetailDivider").removeClass("hide_text", true);
        
//        dojo.query("#objectStoreBreadcrumbDiv").toggleClass("hide_text", true);
        
    },
    'gotoList': function () {
        dijit.byId("containerActionLoader").hide(); 
    },
    cancel : function () {
        dijit.byId("addContainerForm").reset();
    },
    populateValues : function () {
        
        dojo.byId("noContainerMessageBox").style.display = "none";
            
        dojo.byId("containerGridDiv").innerHTML = "<img src='images/vmload.gif' alt="+translator.common.loader.imageLoadError+" height='36' width='100'/> </br> <p>"+translator.user.loader.containerLoader+"</p>";   
        var gridData = {
            items: []
        };
        
        var gridDataList = new ItemFileWriteStore({data: gridData});            
        
        var gridLayout = [
            [               
                {'name': translator.common.name, 'field': 'name', 'width': '200px', datatype:"string",  autoComplete: true, 'formatter': function(data, rowIndex) {                                                     
                        return "<a href='#/user/storage/viewContainer/" + data.containerId + "' title='" + translator.common.view + "'>" + data.name + "</a>";
          
                }},                                 
                {'name': translator.common.objectCount, 'field': 'objectCount', 'width': '200px', datatype:"string",  autoComplete: true},
                {'name': translator.common.size.sizeName + " ( " + translator.common.kb + " )", 'field': 'size', 'width': '200px', datatype:"string",  autoComplete: true},
                {'name': translator.common.access, 'field': 'access', 'width': '200px', datatype:"string",  autoComplete: true },                               
                {'name': translator.common.action, 'field': 'action',   'formatter': function(data, rowIndex) {  
                        
                        var menu = new DropDownMenu({ style: "display: none;"});
                            
                            var viewMenu = new MenuItem({
                                label: translator.common.view,
                                onClick: function(){ core.router.go("#/user/storage/viewContainer/" + data.containerId ); }
                            });
                            menu.addChild(viewMenu);
                            menu.startup();
                            
                            if(data.access === "private") {
                                
                                var makePublicMenu = new MenuItem({
                                    label: translator.common.makePublic,
                                    onClick: function(){ ContainerInfo.accessConfirmation(data.containerId, data.access); }
                                });
                                menu.addChild(makePublicMenu);
                                menu.startup();
                                
                            } else {
                                
                                var makePrivateMenu = new MenuItem({
                                    label: translator.common.makePrivate,
                                    onClick: function(){ ContainerInfo.accessConfirmation(data.containerId, data.access); }
                                });
                                menu.addChild(makePrivateMenu);
                                menu.startup(); 
                                
                            }
                            
                            var deleteMenu = new MenuItem({
                                label: translator.common.deleteData,
                                onClick: function(){ ContainerInfo.deleteConfirmation(data.containerId); }
                            });
                            menu.addChild(deleteMenu);
                            menu.startup(); 
                    
                            var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                            });
                                                        
                            return button;
                },'width': '100%', datatype:"string",  autoComplete: 'true'}           
        ]
    ];        
    var storageRestStore = new JsonRest({
        target: core.getContextPath()+"/api/objectStore/container/list"
    });
    
    storageRestStore.query().then(function(data) {
            if(data.length == 0 || data == "undefined" || data == undefined)  {                  
                dojo.byId("noContainerMessageBox").style.display = "block";                    

                dojo.byId("containerGridDiv").innerHTML = "";
            } else {                            
                dojo.byId("noContainerMessageBox").style.display = "none";
                dojo.byId("containerGridDiv").innerHTML = "";
                dojo.forEach(data, function(containerData) {  
                gridDataList.newItem({
                        id: containerData.id,
                        name: containerData,
                        access: containerData.access,
                        objectCount: containerData.objectCount,
                        size: containerData.size,                                          
                        action: containerData
                    });                   
                }); 
            
            
            if(dijit.byId("containerGridWidget")) {
                dijit.byId("containerGridWidget").destroyRecursive();
            }
              
            dojo.byId("containerGridDiv").innerHTML = "";
            var instanceGrid = new EnhancedGrid({
                id: 'containerGridWidget',
                store: gridDataList,
                structure: gridLayout,
                autoHeight: true,
                autoWidth: false,                
                class:"span12",
                style: "overflow: hidden",
                plugins: core.getGridConfig().plugins
            });                     
            instanceGrid.placeAt("containerGridDiv");        
            instanceGrid.startup();             

        } 
    });
    },
    
    'add': function () {
        
        var status = true;
        var pageNode = dojo.byId("addContainerContent");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = "";
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
        } else {
            
            dojo.byId("containerAddButtonDiv").style.display = "none";
            dojo.byId("addContainerButtonLoader").style.display = "block"; 
             
            var containerRestStore = new JsonRest({
               target: core.getContextPath() + "/api/objectStore/container/create" 
            });
                        
            var name = dijit.byId("storageContainerName").value;
            var access = dijit.byId("storageContainerAccess").value;
            containerRestStore.add({
               name: name,
               access: access
            }).then(function(data) {
                dojo.byId("containerAddButtonDiv").style.display = "block";
                dojo.byId("addContainerButtonLoader").style.display = "none";
                
               dojo.forEach(data, function (resultData) {
                  if(resultData.result == "OK") {
                      registry.byId("userToaster").setContent(translator.common.message.containerCreated, "message");
                      registry.byId("userToaster").show();
                      ContainerInfo.populateValues();
                      dijit.byId("addContainerForm").reset();
                  } else if(resultData.result == "FAILED") {
                      registry.byId("userToaster").setContent(resultData.message, "error");
                      registry.byId("userToaster").show();
                  } else if(resultData.result == "ALREADY_EXISTS") {
                      registry.byId("userToaster").setContent(resultData.message, "error");
                      registry.byId("userToaster").show();
                  } else {
                      registry.byId("userToaster").setContent(translator.common.message.containerNotCreated, "error");
                      registry.byId("userToaster").show();
                  }
               });
            });
        }    
    },
    'accessConfirmation' : function (id, access) {
        dojo.byId("currentContainerId").value = id;
        dojo.byId("currentContainerAccess").value = access;
        dijit.byId("objectContainerAccessDialog").show();
        if(access === "public") {
            dojo.byId("objectContainerAccessConfirmMsg").innerHTML = translator.common.message.accessPrivateConfirm;
        } else {
            dojo.byId("objectContainerAccessConfirmMsg").innerHTML = translator.common.message.accessPublicConfirm;
        }
    },
    'changeAccess' : function () {
        var id = dojo.byId("currentContainerId").value;
        var access = dojo.byId("currentContainerAccess").value; 
        var updatedAccess;
        
        if(access === "public") {
            updatedAccess = "Private";
        } else {
            updatedAccess = "Public";
        }
         
         var containerRestStore = new JsonRest({
            target: core.getContextPath() + "/api/objectStore/container/updateAccess/" 
         });
         
         dijit.byId("objectContainerAccessDialog").hide();
         dijit.byId("containerActionLoader").show(); 

         containerRestStore.put({
            id: id,
         }).then(function(data) {
             dijit.byId("containerActionLoader").hide(); 
            dojo.forEach(data, function (resultData) {
               if(resultData.result == "OK") {
                   registry.byId("userToaster").setContent(translator.common.message.containerAccessChanged + " : " + updatedAccess, "message");
                   registry.byId("userToaster").show();
                   ContainerInfo.populateValues();
               } else if(resultData.result == "FAILED") {
                   registry.byId("userToaster").setContent(resultData.message, "error");
                   registry.byId("userToaster").show();
               } else {
                   registry.byId("userToaster").setContent(translator.common.message.containerAccessNotChanged, "error");
                   registry.byId("userToaster").show();
               }
            });
         });
    },
    'closeAccessConfirmDialog': function () {
        dijit.byId("objectContainerAccessDialog").hide();
    },
    'deleteConfirmation': function(id) {
        dojo.byId("currentContainerId").value = id;
        dijit.byId("deleteObjectContainerDialog").show();
    },
    'delete' : function () {
        var id = dojo.byId("currentContainerId").value;
        
        var deleteRestStore = new JsonRest({
            target : core.getContextPath() + "/api/objectStore/container/delete"
        });
        dijit.byId("deleteObjectContainerDialog").hide();
        dijit.byId("containerActionLoader").show(); 
        
        deleteRestStore.add({
            containerId : id
        }).then(function (data) {
            dijit.byId("containerActionLoader").hide(); 
           dojo.forEach(data, function (resultData) {
               
               if(resultData.result === "OK") {
                   registry.byId("userToaster").setContent(translator.common.message.containerDeleted, "message");
                   registry.byId("userToaster").show();
                   ContainerInfo.populateValues();
               } else if(resultData.result === "FAILED") {
                   registry.byId("userToaster").setContent(resultData.message, "error");
                   registry.byId("userToaster").show();
               } else {
                   registry.byId("userToaster").setContent(translator.common.message.containerNotDeleted, "error");
                   registry.byId("userToaster").show();
               }
           });
        });
    },
    'closeDeleteDialog' : function () {
        dijit.byId("deleteObjectContainerDialog").hide();
    },
    
};
var ContainerView = {

    'populateDetails': function () {
        
        var containerRestStore = new JsonRest({
           target: core.getContextPath() + "/api/objectStore/container/view" 
        });
                
        var containerId = dojo.byId("currentContainerId").value;

        
        containerRestStore.query({containerId: containerId}).then(function (data) {
            dojo.forEach(data, function(resultData) {
//               dojo.byId("containerNameTag").innerHTML = resultData.name;
               dojo.byId("containerNameInfo").innerHTML = resultData.name; 
               //To set container name to access the object information
               dojo.byId("selectedContainerName").value = resultData.name; 
               dojo.byId("selectedContainerId").value = resultData.containerId; 
               dojo.byId("containerSizeInfo").innerHTML = resultData.size; 
               dojo.byId("containerAccessInfo").innerHTML = resultData.access; 
               dojo.byId("selectedContainerAccess").value = resultData.access; 
               dojo.byId("containerObjectCountInfo").innerHTML = resultData.objectCount; 
               dojo.byId("containerAccountInfo").innerHTML = resultData.accountName; 
               if(resultData.access === "public") {
                   dojo.byId("publicUrlDetail").style.display = "block";
                   dojo.byId("containerPublicUrlInfo").innerHTML = resultData.publicURL; 
               } else {
                   dojo.byId("publicUrlDetail").style.display = "none";
               }
               
               dojo.byId("containerCreatedDateInfo").innerHTML = resultData.createdAt; 
            });
        });
        
        
    },
    'accessConfirmation' : function () {
        var access = dojo.byId("selectedContainerAccess").value;
        dijit.byId("viewPageContainerAccessDialog").show();
        if(access === "public") {
            dojo.byId("objectContainerAccessConfirmMessage").innerHTML = translator.common.message.accessPrivateConfirm;
        } else {
            dojo.byId("objectContainerAccessConfirmMessage").innerHTML = translator.common.message.accessPublicConfirm;
        }
    },
    'changeAccess' : function () {
        var id = dojo.byId("currentContainerId").value;
        var access = dojo.byId("selectedContainerAccess").value;
            
        var updatedAccess;
        
        if(access === "public") {
            updatedAccess = "Private";
        } else {
            updatedAccess = "Public";
        }
         
         var containerRestStore = new JsonRest({
            target: core.getContextPath() + "/api/objectStore/container/updateAccess/" 
         });
         
         dijit.byId("viewPageContainerAccessDialog").hide();
         dojo.byId("viewContainerDiv").style.display = "none";
         dojo.byId("containerViewLoaderDiv").style.display = "block";
         dojo.byId("containerViewLoader").innerHTML = "<img src='images/vmload.gif' alt="+translator.common.loader.imageLoadError+" height='36' width='100'/> </br> <p>"+translator.common.loader.processingPleaseWait+"</p>";
//         dijit.byId("containerActionLoader").show(); 

         containerRestStore.put({
            id: id,
         }).then(function(data) {
             dojo.byId("viewContainerDiv").style.display = "block";
             dojo.byId("containerViewLoaderDiv").style.display = "none";
             dojo.byId("containerViewLoader").innerHTML = "";
            dojo.forEach(data, function (resultData) {
                
               if(resultData.result == "OK") {
                   registry.byId("userToaster").setContent(translator.common.message.containerAccessChanged + " : " + updatedAccess, "message");
                   registry.byId("userToaster").show();
                   ContainerView.populateDetails();
                   if(access === "public") {
                       dojo.byId("containerAccessIcon").setAttribute('title', translator.common.makePublic)
                   } else {
                       dojo.byId("containerAccessIcon").setAttribute('title', translator.common.makePrivate)
                   }
                   
               } else if(resultData.result == "FAILED") {
                   registry.byId("userToaster").setContent(resultData.message, "error");
                   registry.byId("userToaster").show();
               } else {
                   registry.byId("userToaster").setContent(translator.common.message.containerAccessNotChanged, "error");
                   registry.byId("userToaster").show();
               }
            });
         });
    },
    'closeAccessConfirmDialog': function () {
        dijit.byId("viewPageContainerAccessDialog").hide();
    },
    'deleteConfirmation': function(id) {
        dijit.byId("viewPageDeleteContainerDialog").show();
    },
    'delete' : function () {
        var id = dojo.byId("currentContainerId").value;
        
        var deleteRestStore = new JsonRest({
            target : core.getContextPath() + "/api/objectStore/container/delete"
        });
        dijit.byId("viewPageDeleteContainerDialog").hide();
        dojo.byId("viewContainerDiv").style.display = "none";
        dojo.byId("containerViewLoaderDiv").style.display = "block";
        dojo.byId("containerViewLoader").innerHTML = "<img src='images/vmload.gif' alt="+translator.common.loader.imageLoadError+" height='36' width='100'/> </br> <p>"+translator.common.loader.processingPleaseWait+"</p>";
//        dijit.byId("containerActionLoader").show(); 
        
        deleteRestStore.add({
            containerId : id
        }).then(function (data) {
            dojo.byId("viewContainerDiv").style.display = "block";
            dojo.byId("containerViewLoaderDiv").style.display = "none";
            dojo.byId("containerViewLoader").innerHTML = "";
           dojo.forEach(data, function (resultData) {               
               if(resultData.result === "OK") {
                   registry.byId("userToaster").setContent(translator.common.message.containerDeleted, "message");
                   registry.byId("userToaster").show();
                   core.router.go("#/user/storage/container");
               } else if(resultData.result === "FAILED") {
                   registry.byId("userToaster").setContent(resultData.message, "error");
                   registry.byId("userToaster").show();
               } else {
                   registry.byId("userToaster").setContent(translator.common.message.containerNotDeleted, "error");
                   registry.byId("userToaster").show();
               }
           });
        });
    },
    'closeDeleteDialog' : function () {
        dijit.byId("viewPageDeleteContainerDialog").hide();
    },
};
window.ContainerInfo = ContainerInfo;
window.ContainerView = ContainerView;