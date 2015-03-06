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
        name:"image",
        module: "user",
        filePath: "/js/app/user/image.js",
        layout: {
            name: "",
            containerId: "content"
        },
        scaffold: false
    },
    {   
        "list": action(function() {
            if (dijit.byId("userListImageForm")) {
                dijit.byId("userListImageForm").destroyRecursive();
            }  
            if (dijit.byId("userDeleteImageDialog")) {
                dijit.byId("userDeleteImageDialog").destroyRecursive();
            } 
            if (dijit.byId("userDeleteImageLoader")) {
                dijit.byId("userDeleteImageLoader").destroyRecursive();
            } 
            
            core.ui.loadTemplate("imageList", core.ui.getContentId());  
            Image.populateValues();
            
           
        }),
        "create" : action(function() { 
            
            if(dijit.byId("userImageContentForm")) {
                dijit.byId("userImageContentForm").destroyRecursive();
            }   
            if(dijit.byId("userCreateImageLoader")) {
                dijit.byId("userCreateImageLoader").destroyRecursive();
            }                
            core.ui.loadTemplate("addImage", core.ui.getContentId()); 
            dojo.query("#imagesArchDiv").toggleClass("hide_text", true); 
              
        }),
        "edit" : action(function(id) { 
            if(dijit.byId("userImageContentForm")) {
                dijit.byId("userImageContentForm").destroyRecursive();
            }   
            if(dijit.byId("userCreateImageLoader")) {
                dijit.byId("userCreateImageLoader").destroyRecursive();
            } 
            core.ui.loadTemplate("addImage", core.ui.getContentId());
            dojo.query("#imagesArchDiv").toggleClass("hide_text", true); 

            EditImage.init(id);
        }),
        "view": action(function(id) {
            if(dijit.byId("imageTabContainer")) {
                dijit.byId("imageTabContainer").destroyRecursive();
            }  
             core.ui.loadTemplate("viewImage", core.ui.getContentId());
             EditImage.view(id);
            
        }),
        
        
    });
});

var Image = {
    'populateValues': function() {

        dojo.byId("noUserImageMessageBox").style.display = "none";
        dojo.byId("userImageList").innerHTML = "<img src='images/vmload.gif' alt='" + translator.common.loader.loading + "' height='36' width='100'/> </br> <p>" + translator.user.loader.imageLoader + "</p>";

        var gridData = {
            items: []
        };

        var dataList = new ItemFileWriteStore({data: gridData}); 
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
                        var viewTag = "<a href='#/user/image/view/"+data.referenceId+"' title='"+translator.common.view+"'>"+data.name+"</a>";
                        return viewTag;
                }},
                {'name': translator.common.status.name, 'field': 'status', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.format, 'field': 'format', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.cost, 'field': 'cost', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true'},
                {'name': translator.common.public, 'field': 'public', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
                        
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
                        
                        var viewMenu = new MenuItem({
                            label: translator.common.view,
                            onClick: function(){ 
                                core.router.go("#/user/image/view/"+data.referenceId);}
                        });
                        
                        menu.addChild(viewMenu);
                        menu.startup();
                         
                        var editMenu = new MenuItem({
                            label: translator.common.edit,
                            onClick: function(){ 
                                core.router.go("#/user/image/edit/"+data.referenceId);}
                        });
                        
                        menu.addChild(editMenu);
                        menu.startup();
                        
                        var deleteMenu = new MenuItem({
                            label: translator.common.deleteData,
                            onClick: function(){ Image.deleteImageDialogAlert(data.referenceId);}
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

        var networksRestStore = new JsonRest({
            target: core.getContextPath() + "/api/image/"
        });
        var userImage = "true";
        networksRestStore.query({userImage: userImage}).then(function (data) {
            if (data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {

                dojo.byId("userImageList").innerHTML = "";
                dojo.byId("noUserImageMessageBox").style.display = "block";

            } else {

                dojo.byId("noUserImageMessageBox").style.display = "none";
                dojo.forEach(data, function(resultData) {                 
                    dataList.newItem({
                        id:resultData.referenceId,
                        name:resultData,
                        status:resultData.status,
                        format:resultData.diskFormat,
                        cost:resultData.cost,
                        public: resultData.isPublic === true ? "Yes" : "No",
                        protected: resultData.isProtected === true ? "Yes" : "No",
                        action: resultData
                    });
                });
                dojo.byId("userImageList").innerHTML = "";
                var dataGrid = new EnhancedGrid({
                    "class": "span12",
                    store: dataList,
                    structure: dataLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                dataGrid.placeAt("userImageList");
                dataGrid.startup();
                dataGrid.update();
            }
        });
    },
    'deleteImageDialogAlert': function(id) {
        dijit.byId("userDeleteImageDialog").show();
        dojo.byId("selectedImageId").value = id;
    },
    'confirmDelete': function() {
        var currentId = dojo.byId("selectedImageId").value;
        var deleteImageRestStore = new JsonRest({
            target: core.getContextPath() + "/api/image/"
        });
        
        dijit.byId("userDeleteImageDialog").hide();
        dijit.byId("userDeleteImageLoader").show(); 

        deleteImageRestStore.remove(currentId).then(function(data) {

            if (data == "[\"OK\"]") {

                dijit.byId("userDeleteImageLoader").hide(); 
                registry.byId("userToaster").setContent(translator.common.message.deletedSuccess, "message");
                registry.byId("userToaster").show();
                ListImages.populateValues();


            } else {

                dijit.byId("userDeleteImageLoader").hide(); 
                registry.byId("userToaster").setContent(translator.common.message.cannotDelete, "error");
                registry.byId("userToaster").show();
            }

        });

    },
    'closeDeleteImageDialog': function() {
        dijit.byId("userDeleteImageDialog").hide();
    },
    'gotoList': function() {
        
        if(dijit.byId("userDeleteImageLoader")) {
            dijit.byId("userDeleteImageLoader").hide();
        }
        
    },

};
var ImageInfo = {
    'changeImageType' : function(data) {
        
        if(data.value === "url") {
            dojo.byId("imageUrlDiv").style.display = "block";
            dojo.byId("imageFileDiv").style.display = "none";
            dojo.byId("imageFile").value = null;
            dijit.byId("userImageLocation").set("required", true);
        } else if(data.value === "file") {
            dojo.byId("imageFileDiv").style.display = "block";
            dojo.byId("imageUrlDiv").style.display = "none";
            dojo.byId("imageFile").value = null;
            dijit.byId("userImageLocation").set("required", false);
        }
        
    },
    'add': function() {
        dojo.byId("imageAddButtonDiv").style.display = "block";
        var status = true;
        var pageNode = dojo.byId("imageContent");
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

            var imagesRestStore = new JsonRest({
                target: core.getContextPath() + "/api/image/"
            });

            var name = dijit.byId("userImageName").value;
            var description = dijit.byId("userImageDescription").value;
            var location = dijit.byId("userImageLocation").value;
            var imageSource = dijit.byId("userImageSource").value;
            var imageFile = dojo.byId("userImageFile").value;
            var userImage = "true";
            
            
//            var fReader = new FileReader();
////            fReader.readAsDataURL(imageFile.files[0].getAsDataURL());
//            console.log(" imageFile.files[0].getAsDataURL()" +  imageFile.files[0].getAsDataURL())
//            fReader.onloadend = function(event){
////            console.log(" event.target.result" +  event.target.result)
//            }

            var format = dijit.byId("userImageFormat").value;
            var architecture = dijit.byId("userArchitecture").value;
            var minimumDisk = dijit.byId("userMinimumDisk").value;
            var minimumRam = dijit.byId("userMinimumRam").value;
            var imageIsPublic = dijit.byId("userImageIsPublic").checked;
            var imageIsProtected = dijit.byId("userImageIsProtected").checked;
            var cost = 0;
            var oneTimeChargeable = false;
            
            dijit.byId("userCreateImageLoader").show(); 

            imagesRestStore.add({
                name: name,
                description: description,
                diskFormat: format,
                architecture: architecture === ""? null:architecture,
                location: location,
                minDisk: minimumDisk, //=== 0? null: minimumDisk,
                minRam: minimumRam, //=== 0? null: minimumRam,
                isPublic: imageIsPublic,
                isProtected: imageIsProtected,
                imageFile:imageFile,
                imageSource:imageSource,
                userImage: userImage,
                cost: cost,
                oneTimeChargeable: oneTimeChargeable,
                
            }).then(function(data) {

                dojo.forEach(data, function(resultData) {   
                    if(resultData === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.addedSuccess, "message");
                        registry.byId("userToaster").show();
                        core.router.go("#/user/image/list");
                        dijit.byId("userCreateImageLoader").hide();
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cannotBeAdded, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("userCreateImageLoader").hide();
                    }
                });
            });

        }
    }, 
    'cancel': function() {
        core.router.go("#/user/image/list");
    },
    'gotoList': function() {
        
        if(dijit.byId("userCreateImageLoader")) {
            dijit.byId("userCreateImageLoader").hide();
            core.router.go("#/user/image/list");
        }        
    },
    'update': function() {
        var id = dojo.byId("currentImageId").value;
        var name = dijit.byId("userImageName").value;
        var description = dijit.byId("userImageDescription").value;

        var imageIsPublicId = dijit.byId("userImageIsPublic");
        var imageIsPublic = false;
        
        if (imageIsPublicId.checked == "true" || imageIsPublicId.checked == true) {
            imageIsPublic = true;
        }

        var imageIsProtectedID = dijit.byId("userImageIsProtected");
        var imageIsProtected = false;
        if (imageIsProtectedID.checked == "true" || imageIsProtectedID.checked == true) {
            imageIsProtected = true;
        }
        var cost = 0;
        var oneTimeChargeable = false;

        var status = true;
        var pageNode = dojo.byId("imageContent");
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
            var updateFlavorRestStore = new JsonRest({
                target: core.getContextPath() + "/api/image/update/"
            });
            
            dijit.byId("userCreateImageLoader").show(); 
            var userImage = "true";
            updateFlavorRestStore.put({
                id: id,
                name: name,  
                description: description,
                isPublic: imageIsPublic,
                isProtected: imageIsProtected,
                cost: cost,
                userImage: userImage,
                oneTimeChargeable: oneTimeChargeable,
            }).then(function(resultData) {
                dojo.forEach(resultData, function(data) {
                    if (data.result === "OK") {
                        registry.byId("userToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("userToaster").show();
                        dijit.byId("userCreateImageLoader").hide(); 
                        core.router.go("#/user/image/list");
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("userCreateImageLoader").hide(); 
                    }
                });
            });
        }
    },
};
var EditImage = {
    
    'init': function(id) {        
        dojo.byId("editImageBreadcrum").innerHTML = translator.common.edit;                
               
        var getImageRestStore = new JsonRest({
         target: core.getContextPath() + "/api/image/"
        });
        var userImage = "true";
       dojo.byId("currentImageId").value= id;
       getImageRestStore.query({referenceId: id, userImage: userImage}).then(function(resultData) {
            dojo.forEach(resultData, function(data) {
                
            dijit.byId("userImageName").setValue(data.name);
            dijit.byId("userImageDescription").setValue(data.description);
            
            dijit.byId("userImageLocation").set("required", false);
            
            dijit.byId("userImageFormat").set("value",data.diskFormat.toLowerCase());
            dijit.byId("userImageFormat").setAttribute('disabled', true);
            
            dijit.byId("userArchitecture").setValue(data.architecture);
            dijit.byId("userArchitecture").setAttribute('disabled', true);
            
            dijit.byId("userMinimumDisk").setValue(data.minDisk);
            dijit.byId("userMinimumDisk").setAttribute('disabled', true);
            
            dijit.byId("userMinimumRam").setValue(data.minRam);
            dijit.byId("userMinimumRam").setAttribute('disabled', true);
            if(data.isPublic === true) {
                dijit.byId("userImageIsPublic").set("checked", true);
            } else {
                dijit.byId("userImageIsPublic").set("checked", false);  
            }
            if(data.isProtected === true) {
                dijit.byId("userImageIsProtected").set("checked", true);
            } else {
                dijit.byId("userImageIsProtected").set("checked", false);  
            }
            });
        });
        dojo.byId("imageSourceDiv").style.display = "none";
        dojo.byId("imageUrlDiv").style.display = "none";
        dojo.byId("imageAddButtonDiv").style.display = "none";
        dojo.byId("imageUpdateButtonDiv").style.display = "block";
    },
    
    'view': function(id) {
        
        var viewRest = new JsonRest({
            target: core.getContextPath() + "/api/image/"
        });
        var userImage = "true";
        
        viewRest.query({referenceId: id, userImage: userImage}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("imageNameInfo").innerHTML = resultData.name;
                dojo.byId("imageIdInfo").innerHTML = resultData.referenceId;
                dojo.byId("imageSizeInfo").innerHTML = resultData.sizeInMB;
                dojo.byId("imageStatusInfo").innerHTML = resultData.status;
                dojo.byId("imageIsPublicInfo").innerHTML = resultData.isPublic;
                dojo.byId("imageMinRamInfo").innerHTML = resultData.minRam;
                dojo.byId("imageMinDiskInfo").innerHTML = resultData.minDisk;
                dojo.byId("imageIsProtectedInfo").innerHTML = resultData.isProtected;
                dojo.byId("imageDiskFormatInfo").innerHTML = resultData.diskFormat;
                dojo.byId("imageContatinerFormatInfo").innerHTML = resultData.containerFormat;
                dojo.byId("imageCheckSumInfo").innerHTML = resultData.checksum;
                dojo.byId("imageCreatedInfo").innerHTML = resultData.createdOn;
            });
        });    
    }
    
}