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
    "dijit/Menu", 
    "dijit/MenuItem", 
    "dijit/form/ComboButton", 
    "dijit/form/DropDownButton", 
    "dijit/DropDownMenu", 
    "dijit/registry",    
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dijit/layout/TabContainer",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/DateTextBox",
    "dijit/form/MultiSelect",
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
    "dojo/dom",
    "dojo/_base/window",
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
    "dijit/Dialog",
    "dojox/form/FileInput",
    "dojox/form/FileInputAuto",
    "dojox/form/Uploader"
    ],
    function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, Menu, MenuItem, ComboButton, DropDownButton, DropDownMenu, registry, FilteringSelect, Select,
    ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {              
        window.translator = translator;
        window.query = query;
        window.domClass = domClass;
        window.domConstruct = domConstruct;
        window.Menu = Menu;
        window.MenuItem = MenuItem;
        window.DropDownButton = DropDownButton;
        window.DropDownMenu = DropDownMenu;
        window.ComboButton = ComboButton;
        window.JsonRest = JsonRest;
        window.registry = registry;
        window.FilteringSelect = FilteringSelect;
        window.ItemFileWriteStore = ItemFileWriteStore;
        window.Select = Select;
        window.ContentPane = ContentPane;
        window.DataGrid = DataGrid;
        window.EnhancedGrid = EnhancedGrid;
        window.Source = Source;
        window.MultiSelect = MultiSelect;
        window.dom = dom;
        window.win = win;
        window.computationCount = 0;
        
        controller ({
            name: "images",
            module: "admin",
            filePath: "/js/app/admin/images.js",
            layout: {
                name: "images",
                containerId: "content"
            },	
            scaffold: false
        },
        {
           
            "services" : action(function() {
                core.ui.loadTemplate("services", core.ui.getContentId()); 
            }),
            "list": action(function() {  
                var form = dijit.byId("imageListForm");
                if(form) {
                    form.destroyRecursive();
                }  
                if(dijit.byId("deleteImageDialog")) {
                    dijit.byId("deleteImageDialog").destroyRecursive();
                }  
                if(dijit.byId("pullImageConfirm")) {
                    dijit.byId("pullImageConfirm").destroyRecursive();
                }
                if(dijit.byId("deleteImageLoader")) {
                    dijit.byId("deleteImageLoader").destroyRecursive();
                }
                if(dijit.byId("pullImageButton")) {
                    dijit.byId("pullImageButton").destroyRecursive();
                }
                core.ui.loadTemplate("adminImagesList", core.ui.getContentId()); 
                ListImages.init();
                ListImages.count();
            }),
            "createImage" : action(function() { 
                if(dijit.byId("adminImageContentForm")) {
                    dijit.byId("adminImageContentForm").destroyRecursive();
                }   
                if(dijit.byId("pullImageLoader")) {
                    dijit.byId("pullImageLoader").destroyRecursive();
                }                
                core.ui.loadTemplate("addImage", core.ui.getContentId()); 
                dojo.query("#imagesArchDiv").removeClass("hide_text", true); 
                AddImageInfo.init();               
            }),
            "edit" : action(function(id) { 
                if(dijit.byId("adminImageContentForm")) {
                    dijit.byId("adminImageContentForm").destroyRecursive();
                }   
                if(dijit.byId("pullImageLoader")) {
                    dijit.byId("pullImageLoader").destroyRecursive();
                }
                core.ui.loadTemplate("addImage", core.ui.getContentId());
                dojo.query("#imagesArchDiv").toggleClass("hide_text", true); 
                AddImageInfo.init(id);
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
    
var ListImages= {
    init: function() {
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });
//        console.log("data")
        currency.query().then(function(data) {
            dojo.forEach(data,function(cur) { 
                dojo.byId("imagesCurrencyValue").innerHTML= cur.currency;
            });
        });
        
//        var asyncJob = new JsonRest({
//            target: core.getContextPath() + "/api/asyncJob"
//        });
//        
//        asyncJob.query({jobType:"PULL_IMAGE"}).then(function(data) {
//            if(data[0] === "OK") {
//                dojo.byId("pullImageLoaderImage").style.display = "inline";
//                dijit.byId("pullImageButton").set("disabled", true);
//            } else if(data[0] === "FALSE") {
//                dojo.byId("pullImageLoaderImage").style.display = "none";
//                dijit.byId("pullImageButton").set("disabled", false);
//            }
//        });   
        
//        var openStackConfigCheck = new JsonRest({
//            target: core.getContextPath() + "/api/config/isOpenStackConfigured"
//        })
//        
//        openStackConfigCheck.query().then(function(data) {
//            
//            if(data === false) {
//                dojo.byId("adminImagePullDiv").style.display = "none";
//                dojo.byId("openStackNotConfiguredMsgImage").style.display = "block";
//            } else{
//                ListImages.populateValues();
//            }
//        });
        ListImages.populateValues();
    },
    count: function() {
      
        var imageCountRest = new JsonRest({
            target : core.getContextPath() + "/api/image/count"
        });
        
        imageCountRest.query().then(function(data){
            dojo.forEach(data, function(resultData){
                dojo.byId("adminTotalImages").innerHTML = resultData.totalImages;
                dojo.byId("adminPublicImages").innerHTML = resultData.publicImages;
                dojo.byId("adminProtectedImages").innerHTML = resultData.protectedImages;
            });
        });
    },
    populateValues : function() {
        dojo.byId("adminImagesList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
               
//        dojo.style(dijit.byId("deleteImageDialog").closeButtonNode,"display","none");
        var gridData = {
            items: []
        }; 
        
        dojo.byId("adminTotalImages").innerHTML = 0;
        dojo.byId("adminPublicImages").innerHTML = 0;
        dojo.byId("adminProtectedImages").innerHTML = 0;
        var dataList = new ItemFileWriteStore({data: gridData}); 
        var dataLayout = [[
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.name, 'field': 'name', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
                        var viewTag = "<a href='#/admin/images/view/"+data.referenceId+"' title='"+translator.common.view+"'>"+data.name+"</a>";
                        return viewTag;
                }},
                {'name': translator.common.type, 'field': 'type', 'width': '100px', 'datatype':'string',  'autoComplete': 'true','formatter': function(data) {
                        if(data == true) {
                            return "Snapshot"
                        } else {
                            return "Image"
                        }
                }},
                {'name': translator.common.public, 'field': 'public', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
                        
                        var html;
                        if(data === "Yes") {
                            html = "<span class='fog_cost_blue'>"+ data + "</span>"; 
                        } else {
                            html = "<span class='fog_cost_red overflowLabel'>"+ data + "</span>";
                        }
                        return html;
                 }},
                {'name': translator.common.status.name, 'field': 'status', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.rootDeviceName, 'field': 'rootDeviceName', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.virtualizationType, 'field': 'virtualizationType', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
//                {'name': translator.common.cost, 'field': 'cost', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'hidden': 'true'},
                
//                {'name': "Protected", 'field': 'protected', 'width': '100px', 'datatype':'string',  'autoComplete': 'true', 'formatter': function(data) {
//                        
//                        var html;
//                        if(data === "Yes") {
//                            html = "<span class='fog_cost_blue'>"+ data + "</span>"; 
//                        } else {
//                            html = "<span class='fog_cost_red overflowLabel'>"+ data + "</span>";
//                        }
//                        return html;
//                 }},             
                 
                 {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) { 
                         
                        var menu = new DropDownMenu({ style: "display: none;"});
                        
                        var viewMenu = new MenuItem({
                            label: translator.common.view,
                            onClick: function(){ 
                                core.router.go("#/admin/images/view/"+data.referenceId);}
                        });
                        
                        menu.addChild(viewMenu);
                        menu.startup(); 
                        
//                        var editMenu = new MenuItem({
//                            label: translator.common.edit,
//                            onClick: function(){ 
//                                core.router.go("#/admin/images/edit/"+data.referenceId);}
//                        });
//                        
//                        menu.addChild(editMenu);
//                        menu.startup();
//                        
//                        var deleteMenu = new MenuItem({
//                            label: translator.common.deleteData,
//                            onClick: function(){ ImageInfo.deleteImageDialogAlert(data.referenceId);}
//                        });
//                        
//                        menu.addChild(deleteMenu);
//                        menu.startup();

                        var button = new ComboButton({
                            label: "More",
                            name: "More",
                            dropDown: menu
                        });

                        return button;     
                                                                               
                        },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
                ]
            ];     
            
            var imagesRestStore = new JsonRest({                        
                target: core.getContextPath()+"/api/image/"
            }); 
            
            imagesRestStore.query().then(function(data) {
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    dojo.byId("adminImagesList").innerHTML = "";
                    dojo.byId("noImagesMessageBox").style.display = "block";

//                    dojo.byId("adminTotalFlavor").innerHTML = 0;
//                    dojo.byId("adminEnabledFlavor").innerHTML = 0;
//                    dojo.byId("adminDisabledFlavor").innerHTML = 0;
                } else {
                    
                    dojo.byId("noImagesMessageBox").style.display = "none";
//                    console.log("data" + data)
                    dojo.forEach(data, function(resultData) {
                        dataList.newItem({
                            id:resultData.referenceId,
                            name:resultData,
                            status:resultData.state,
                            format:resultData.diskFormat,
                            virtualizationType:resultData.virtualizationType,
                            rootDeviceName:resultData.rootDeviceName,
                            type:resultData.isVMSnapshot,
                            platform:resultData.platform,
                            public: resultData.isPublic === true ? "Yes" : "No",
//                            protected: resultData.isProtected === true ? "Yes" : "No",
                            action: resultData
                        });
                    });                                    
                    dojo.byId("adminImagesList").innerHTML = "";
                    var dataGrid = new EnhancedGrid({                
                        "class" : "span12",
                        store: dataList,
                        structure: dataLayout,
                        autoHeight: true,
                        plugins: core.getGridConfig().plugins
                    });
                    dataGrid.placeAt("adminImagesList");
                    dataGrid.startup();  
                    dataGrid.update();
//                    var coumputingOfferCountRestStore = new JsonRest({                        
//                        target: core.getContextPath()+"/api/computingOffer/count"
//                    }); 
//                    coumputingOfferCountRestStore.query().then(function(data) {
//                        dojo.forEach(data, function(resultData) {
//                            dojo.byId("adminTotalFlavor").innerHTML = resultData.total;
//                            dojo.byId("adminEnabledFlavor").innerHTML = resultData.enabled;
//                            dojo.byId("adminDisabledFlavor").innerHTML = resultData.disabled;                                            
//                        });
//                    });
                }             
            }); 
        },
    confirmPull: function () {
        dijit.byId("pullImageConfirm").show();
    },
    cancelPullImages: function () {
        dijit.byId("pullImageConfirm").hide();
    },
    pullImages : function() {

//        dijit.byId("pullImageLoader").show();
        dijit.byId("pullImageConfirm").hide();

        var pullImageRestStore = new JsonRest({
           target: core.getContextPath()+"/api/image/pullFromOpenstack"
        });

        pullImageRestStore.query().then(function(data) {
            dojo.forEach(data, function(resultData) {   
                if(resultData === "OK") {
                    dojo.byId("pullImageLoaderImage").style.display = "inline";
                    dijit.byId("pullImageButton").set("disabled", true);
//                    ListImages.populateValues();
//                    registry.byId("appToaster").setContent(translator.common.message.pullImageSuccess, "message");
//                    registry.byId("appToaster").show();
//                    dijit.byId("pullImageLoader").hide();
                } else {
                    dojo.byId("pullImageLoaderImage").style.display = "inline";
                    dijit.byId("pullImageButton").set("disabled", true);
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
//                    dijit.byId("pullImageLoader").hide();
                }
            });
        });

    },
};

var AddImageInfo = {
    'changeImageType' : function(data) {
        
        if(data.value === "url") {
            dojo.byId("imageUrlDiv").style.display = "block";
            dojo.byId("imageFileDiv").style.display = "none";
            dojo.byId("imageFile").value = null;
            dijit.byId("imageLocation").set("required", true);
        } else if(data.value === "file") {
            dojo.byId("imageFileDiv").style.display = "block";
            dojo.byId("imageUrlDiv").style.display = "none";
            dojo.byId("imageFile").value = null;
            dijit.byId("imageLocation").set("required", false);
        }
        
    },
    'init': function(id) {
        
        dojo.byId("imagePagehead").innerHTML = translator.common.head.createImage;               
        if(id) {
            dojo.byId("imagePagehead").innerHTML = translator.common.head.editImage;
        }

//        var currency = new JsonRest({
//            target: core.getContextPath() + "/api/config/currency"
//        });
//        currency.query().then(function(data) {
//            dojo.forEach(data, function(cur) {
//                dojo.byId("currencyValue").innerHTML = cur.currency;
//            });
//        });
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

            var name = dijit.byId("imageName").value;
            var description = dijit.byId("imageDescription").value;
            var location = dijit.byId("imageLocation").value;
            var imageSource = dijit.byId("imageSource").value;
            var imageFile = dojo.byId("imageFile").value;
            
            
//            var fReader = new FileReader();
////            fReader.readAsDataURL(imageFile.files[0].getAsDataURL());
//            console.log(" imageFile.files[0].getAsDataURL()" +  imageFile.files[0].getAsDataURL())
//            fReader.onloadend = function(event){
////            console.log(" event.target.result" +  event.target.result)
//            }

            var format = dijit.byId("imageFormat").value;
            var architecture = dijit.byId("architecture").value;
            var minimumDisk = dijit.byId("minimumDisk").value;
            var minimumRam = dijit.byId("minimumRam").value;
            var imageIsPublic = dijit.byId("imageIsPublic").checked;
            var imageIsProtected = dijit.byId("imageIsProtected").checked;
            var cost = dijit.byId("imageCost").value;
            var oneTimeCharge = dijit.byId("imageOneTimeChargeable").checked;
            
            
            dijit.byId("pullImageLoader").show(); 

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
                cost: cost,
                isOneTimeChargable:oneTimeCharge                
            }).then(function(data) {

                dojo.forEach(data, function(resultData) {   
                    if(resultData === "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.addedSuccess, "message");
                        registry.byId("appToaster").show();
                        core.router.go("#/admin/images/list");
                        dijit.byId("pullImageLoader").hide();
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.cannotBeAdded, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("pullImageLoader").hide();
                    }
                });
            });

        }
    }, 
    'cancel': function() {
        core.router.go("#/admin/images/list");
    }
}

var ImageInfo= {
    'deleteImageDialogAlert': function(currentId) {
        dijit.byId("deleteImageDialog").show();
//        console.log("id is"+currentId)
        dojo.byId("imageDeleteId").value = currentId;
        
    },
    'confirmDelete': function() {
        var currentId = dojo.byId("imageDeleteId").value;
        var deleteImageRestStore = new JsonRest({
            target: core.getContextPath() + "/api/image/"
        });
        
        dijit.byId("deleteImageDialog").hide();
        dijit.byId("deleteImageLoader").show(); 

        deleteImageRestStore.remove(currentId).then(function(data) {

            if (data == "[\"OK\"]") {

                dijit.byId("deleteImageLoader").hide(); 
                registry.byId("appToaster").setContent(translator.common.message.deletedSuccess, "message");
                registry.byId("appToaster").show();
                ListImages.populateValues();
                ListImages.count();

            } else {

                dijit.byId("deleteImageLoader").hide(); 
                registry.byId("appToaster").setContent(translator.common.message.cannotDelete, "error");
                registry.byId("appToaster").show();
            }

        });

    },
    'closeDeleteImageDialog': function() {
        dijit.byId("deleteImageDialog").hide();
    },
    'gotoList': function() {
        
        if(dijit.byId("pullImageLoader")) {
            dijit.byId("pullImageLoader").hide();
            core.router.go("#/admin/images/list");
        }
        if(dijit.byId("deleteImageLoader")) {
            dijit.byId("deleteImageLoader").hide();
        }
        
    },
    'update': function() {
        var id = dojo.byId("currentImageId").value;
        var name = dijit.byId("imageName").value;
        var description = dijit.byId("imageDescription").value;
        var format = dijit.byId("imageFormat").value;
        var imageIsPublicId = dijit.byId("imageIsPublic");
        var imageIsPublic = false;
        var cost = dijit.byId("imageCost").value;
        var oneTimeCharge = dijit.byId("imageOneTimeChargeable").checked;
        
        if(oneTimeCharge === true || oneTimeCharge === "true") {     
            
            if(cost === "0" || cost === 0 || cost === "") {                 
                dijit.byId("imageCost").setValue("");
            } 
        }
        
        if (imageIsPublicId.checked == "true" || imageIsPublicId.checked == true) {
            imageIsPublic = true;
        }

        var imageIsProtectedID = dijit.byId("imageIsProtected");
        var imageIsProtected = false;
        if (imageIsProtectedID.checked == "true" || imageIsProtectedID.checked == true) {
            imageIsProtected = true;
        }

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
            
            dijit.byId("pullImageLoader").show(); 
            
            updateFlavorRestStore.put({
                id:id,
                name:name,
                cost: cost,
                isOneTimeChargable:oneTimeCharge,  
                description:description,
                diskFormat:format,
                isPublic:imageIsPublic,
                isProtected:imageIsProtected,
            }).then(function(resultData) {
                dojo.forEach(resultData, function(data) {
                    if (data.result === "OK") {
                        registry.byId("appToaster").setContent(translator.common.message.updatedSuccess, "message");
                        registry.byId("appToaster").show();
                        dijit.byId("pullImageLoader").hide(); 
                        core.router.go("#/admin/images/list");
                    } else {
                        registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                        registry.byId("appToaster").show();
                        dijit.byId("pullImageLoader").hide(); 
                    }
                });
            });
        }
    },
}

var EditImage = {
    
    'init': function(id) {        
        dojo.byId("editImageBreadcrum").innerHTML = translator.common.edit;                
               
        var getImageRestStore = new JsonRest({
         target: core.getContextPath() + "/api/image/"
        });
       dojo.byId("currentImageId").value= id;
       getImageRestStore.query({referenceId: id}).then(function(resultData) {
            dojo.forEach(resultData, function(data) {
            dojo.byId("imagePagehead").innerHTML = translator.common.head.editImage + " - " + data.name;
            dijit.byId("imageName").setValue(data.name);
            dijit.byId("imageDescription").setValue(data.description);
            dijit.byId("imageCost").setValue(data.cost);
            dijit.byId("imageOneTimeChargeable").set("checked", data.oneTimeChargeable);
            
            dijit.byId("imageLocation").set("required", false);
            
            dijit.byId("imageFormat").set("value",data.diskFormat.toLowerCase());
            dijit.byId("imageFormat").setAttribute('disabled', true);
            
            dijit.byId("architecture").setValue(data.architecture);
            dijit.byId("architecture").setAttribute('disabled', true);
            
            dijit.byId("minimumDisk").setValue(data.minDisk);
            dijit.byId("minimumDisk").setAttribute('disabled', true);
            
            dijit.byId("minimumRam").setValue(data.minRam);
            dijit.byId("minimumRam").setAttribute('disabled', true);
            if(data.isPublic === true) {
                dijit.byId("imageIsPublic").set("checked", true);
            } else {
                dijit.byId("imageIsPublic").set("checked", false);  
            }
            if(data.isProtected === true) {
                dijit.byId("imageIsProtected").set("checked", true);
            } else {
                dijit.byId("imageIsProtected").set("checked", false);  
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
            target: core.getContextPath() + "/api/image/view"
        });
        
        viewRest.query({referenceId: id}).then(function(resultData) {
                
            dojo.byId("imageNameInfo").innerHTML = resultData.name;
            dojo.byId("imageDescriptionInfo").innerHTML = resultData.description;
            dojo.byId("imageIdInfo").innerHTML = resultData.referenceId;
            dojo.byId("imageStatusInfo").innerHTML = resultData.state;
            dojo.byId("imageIsPublicInfo").innerHTML = (resultData.isPublic) == "Yes" ? "<span class='fog_cost_blue'>"+ resultData.isPublic + "</span>" : "<span class='fog_cost_red overflowLabel'>"+ resultData.isPublic + "</span>"
            dojo.byId("imagehypervisorInfo").innerHTML = resultData.hypervisor;
            dojo.byId("imageArchitectureInfo").innerHTML = resultData.architecture;
            dojo.byId("imageTypeInfo").innerHTML = resultData.imageType;
            dojo.byId("rootDeviceTypeInfo").innerHTML = resultData.rootDeviceType;
            dojo.byId("imageCreatedInfo").innerHTML = resultData.createdAt;


        });    
    }
    
}
