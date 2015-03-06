"use strict";
require([
    "dojo",
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js", 
    "dijit/dijit",
    "FogPanel/Pagination",
    "dojo/on",
    "dojo/store/JsonRest",
    "dijit/registry",    
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dijit/form/HorizontalSlider",
    "dojo/dom-construct",
    "dojo/query",
    "dojo/dom-class",
    "dijit/form/Button",
    "FogPanel/TemplateInfo",
    "dijit/layout/TabContainer",
    "dijit/layout/ContentPane",
    "dojox/form/PasswordValidator",
    "FogPanel/StorageAction",    
    "dojox/validate/regexp",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
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
    "dojox/layout/ScrollPane"
],
function(dojo, i18n, translator, dijit, Pagination, on, JsonRest, registry, FilteringSelect, Select,ItemFileWriteStore, DataGrid, EnhancedGrid, HorizontalSlider, domConstruct, query, domClass, Button, TemplateInfo) {  
    window.Pagination = Pagination;
    window.on = on;
    window.JsonRest = JsonRest;
    window.query = query;
    window.domClass = domClass;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Select = Select;
    window.DataGrid = DataGrid;
    window.domConstruct = domConstruct;
    window.HorizontalSlider = HorizontalSlider;
    window.Button = Button;
    window.EnhancedGrid = EnhancedGrid;
    window.TemplateInfo = TemplateInfo;
    window.archiCount = 0;
    controller ({
        name: "template",
        module: "user",
        filePath: "/js/app/user/template.js",
        layout: {
            name: "templatePage",
            containerId: "content"
        },	
        scaffold: false
    },
    {
        "index": action(function() {
            if(dijit.byId("filteringForm")) {
                dijit.byId("filteringForm").destroyRecursive();                
            }
            if(dijit.byId("rateCardDialogue")) {
                dijit.byId("rateCardDialogue").destroyRecursive();  
            }
            core.ui.loadTemplate("templatePage", core.ui.getContentId());                 
            TempConfigTest.init();            
            OsTemplateInfo.init();
            OsTemplateInfo.populateValues();
            OsTemplateInfo.populateTemplateCounts();  
            RateCardInfo.populateValues();  
        })
    });
});

var OsTemplateInfo = {    
    _templateCountRestStore: "",      
    init: function() {       
        this._templateCountRestStore = new JsonRest({
            target: core.getContextPath()+"/api/template/userCount"
        });      
        
        var currency = new JsonRest({
            target: core.getContextPath()+"/api/config/currency"
        });                
    },
    populateTemplateCounts: function() {            
        this._templateCountRestStore.query().then(function(data) {              
            if(data.length == 0) {                
                dojo.byId("linuxCount").innerHTML = 0;
                dojo.byId("windowsCount").innerHTML = 0;
                dojo.byId("appTempCount").innerHTML = 0;
            } else {
                dojo.forEach(data, function(el) {                      
                    dojo.byId("linuxCount").innerHTML = el.linuxTotal;
                    dojo.byId("windowsCount").innerHTML = el.windowsTotal;
                    dojo.byId("appTempCount").innerHTML = el.appCount;                
                })
            }            
        })
    },
    populateValues : function() {         
        if(dijit.byId("tempHypervisorWidget")) {
            dijit.byId("tempHypervisorWidget").destroyRecursive();                
        }
        if(dijit.byId("tempCategoryWidget")) {
            dijit.byId("tempCategoryWidget").destroyRecursive();                
        }
        if(dijit.byId("tempOSTypeWidget")) {
            dijit.byId("tempOSTypeWidget").destroyRecursive();                
        }         
        if(dijit.byId("paginationWidget")) {
            dijit.byId("paginationWidget").destroyRecursive();                
        }                                       
        dojo.query("#templatePaginationList").removeClass("hide_text", true); 
        var templateInfo = "";
        var currentZoneID = dojo.byId("selectedANZoneID").value;
        var currentZone = "";
        var zoneReferID = "";
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            currentZone = "null";
            zoneReferID = "-1"
        } else {
            currentZone = currentZoneID
            zoneReferID = currentZoneID;
        }  
        var path = "";          
        var costType = "";             
        dojo.byId("templateCollection").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        
        var templateRestStore = new JsonRest({                   
            target: core.getContextPath() + "/api/template/pagination"
        });  
        
        var zoneRestStore = new JsonRest({
            target: core.getContextPath() + "/api/zone/"
        });  
        
        var hypervisorRestStore = new JsonRest({
            target: core.getContextPath() + "/api/hypervisor/"
        });  
        
        var osRestStore = new JsonRest({
            target: core.getContextPath() + "/api/osType/"
        });
                             
        var hypervisorOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.all}]
        }; 
        var osTypeOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.all}]
        };
        
        var osCategory = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.all}]
        };
             
        var hypervisorList = new ItemFileWriteStore({data: hypervisorOptions});
        var osTypeList = new ItemFileWriteStore({data: osTypeOptions});             
        var categoryList = new ItemFileWriteStore({data: osCategory}); 
        
        hypervisorRestStore.get(zoneReferID).then(function (data) {                    
                if(data.length == 1 || data.length == 0) {
                    dojo.query("#hypervisorDiv").toggleClass("hide_text", true); 
                } else {
                    dojo.query("#hypervisorDiv").removeClass("hide_text", true); 
                }
                dojo.forEach(data, function (el) {
                    hypervisorList.newItem({id: el.name, name: el.name});                   
                });           
            });
        
         var hypervisorWidget = new FilteringSelect({
            id: "tempHypervisorWidget",            
            sortByLabel: false,
            value: "option",
            store: hypervisorList,            
            maxHeight: 100,
            onChange: function() {          
                dojo.query("#templateDesc").toggleClass("hide_text", true);  
                OsTemplateInfo.filterTemplate("first");                
            }
        }).placeAt("tempHypervisorList");          
                                    
        osRestStore.query().then(function(osCategorytDate) {
            dojo.forEach(osCategorytDate, function(data) {
                osTypeList.newItem({id: data.osCategoryId, name: data.osCategoryName});
            });            
        }); 
        var categoryWidget = new FilteringSelect({
            id: "tempCategoryWidget",           
            value: "option",       
            sortByLabel: false,
            store: categoryList,
            maxHeight: 100,
            onChange: function() {    
                dojo.query("#templateDesc").toggleClass("hide_text", true); 
                OsTemplateInfo.filterTemplate("first");
            }
        }).placeAt("tempCategoryList");    
        var osType = new FilteringSelect({
            id: "tempOSTypeWidget",               
            store: osTypeList,
            value: "option",
            maxHeight: -1, // tells _HasDropDown to fit menu within viewport 
            onChange: function() {    
                dojo.query("#templateDesc").toggleClass("hide_text", true); 
                var osCategoryWidget = dijit.byId("tempCategoryWidget");
                var osCategoryOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var currentOsCategoryList = new ItemFileWriteStore({data: osCategoryOptions});
                if (this.value == "option") {    
                    var noOsCategoryOptions = {
                        identifier: 'id',
                        label: 'name',
                        items: []
                    };
                    var noOsCategoryList = new ItemFileWriteStore({data: noOsCategoryOptions});
                    noOsCategoryList.newItem({id: "option", name: translator.common.all});    
                    osCategoryWidget.set("store",noOsCategoryList)
                    osCategoryWidget.set("value", "option")                      
                } else {                                          
                    currentOsCategoryList.newItem({id:archiCount, name: translator.common.bit32});   
                    archiCount = archiCount + 1;
                    currentOsCategoryList.newItem({id: archiCount, name: translator.common.bit64});                        
                    osCategoryWidget.set("store",currentOsCategoryList);
                    osCategoryWidget.set("value",archiCount);                  
                    archiCount = archiCount + 1;
                }  
            }
        }).placeAt("tempOSTypeList");        

                 
        templateRestStore.query({page: 1, zoneReferenceId: currentZone}).then(function(data) {               
            if(data.length == 0 || data == "" || data == "undefined") {    
                dojo.byId("templateCollection").innerHTML = "";
            } else {                
                dojo.byId("templateCollection").innerHTML = "";
                dojo.forEach(data[0].templateInfo, function(tempData, index) {                            
                    if(tempData.isReady == true) {           
                        if(tempData.appTemplate == true) {
                            path = tempData.imagePath;
                        } else {
                            if(tempData.osCategory == "CentOS") {
                                path ='images/os/os_centos_logo.png'
                            } else if(tempData.osCategory == "Debian") {
                                path ='images/os/os_debian_logo.png'
                            } else if(tempData.osCategory == "Oracle") {
                                path ='images/os/os_oracle_logo.png'
                            } else if(tempData.osCategory == "RedHat") {
                                path ='images/os/os_redhat_logo.png'
                            } else if(tempData.osCategory == "SUSE") {
                                path ='images/os/os_suse_logo.png'
                            } else if(tempData.osCategory == "Windows") {
                                path ='images/os/os_win_logo.png'
                            } else if(tempData.osCategory == "Novel") {
                                path ='images/os/os_novell_logo.png'
                            } else if(tempData.osCategory == "Unix") {
                                path ='images/os/os_unix_logo.png'
                            } else if(tempData.osCategory == "Ubuntu") {
                                path ='images/os/os_ubuntu_logo.png'
                            }                            
                        }
                               
                        if(tempData.oneTimeChargeable == true || tempData.oneTimeChargeable == "true") {
                            costType = translator.common.oneTime;
                        } else {
                            costType = translator.common.month;
                        }
                        var collections = tempData.id + "," + tempData.zoneId;
                        templateInfo =  new FogPanel.TemplateInfo({                                    
                            osLogoPath : path,        
                            templateCost : tempData.cost + "/" + costType,
                            templateCurrency : tempData.currency,
                            templateName : tempData.templateName,
                            templateDesc : tempData.description,
                            path : "#/user/cloud/createVm/"+collections,
                            zoneName : tempData.zone,
                            onMoreOptionClick : function () {
                                OsTemplateInfo.showTemplateDescription(tempData.id, tempData.zone);   
                            }
                        })                        
                        templateInfo.placeAt("templateCollection");
                    }   
                });  
               var pagination = new Pagination({
                   id: "paginationWidget",                   
                    totalResults: data[0].totalRecords,
                    resultsPerPage: 8,
                    "class" : "span12",
                    currentPage: 1,    
                    showPreviousNext: true
                }).placeAt("tempPagination");
                pagination.startup();
                on(pagination, "page", function(evt) {                              
                    // set selected page
                    this.set('currentPage', evt.detail.selectedPage);
                    dojo.query("#templateDesc").toggleClass("hide_text", true); 
                    OsTemplateInfo.filterTemplate("page");
                }); 
            }
            if(templateInfo == "") {
                dojo.byId("templateCollection").innerHTML = ""
                dojo.byId("noTemplateMessageBox").style.display = "block";
            } else {
                dojo.byId("noTemplateMessageBox").style.display = "none"
            }                            
        });
    },
    loadHypervisor : function (currentZone) {     
        var hyperviserItems = {
            identifier: "id",
            label: "name",
            items: [{id: "option", name: translator.common.all}]
        };
        var hyperviserList = new ItemFileWriteStore({data: hyperviserItems});
        var hypervisorRestStore = new JsonRest({
            target: core.getContextPath() + "/api/hypervisor/"
        });  
        if(currentZone.value == "option") {  
            var noTempData = {
                identifier: "id",
                label: "name",
                items: [{id: "option", name: translator.common.all}]
            };
            var noHyperviserList = new ItemFileWriteStore({data: noTempData});
            hypervisorRestStore.get("-1").then(function (data) {    
                if(data.length == 1 || data.length == 0) {
                    dojo.query("#hypervisorDiv").toggleClass("hide_text", true); 
                } else {
                    dojo.query("#hypervisorDiv").removeClass("hide_text", true); 
                }
                dojo.forEach(data, function (el, index) {
                    noHyperviserList.newItem({id: el.name, name: el.name});                   
                });           
            });            
            dijit.byId("tempHypervisorWidget").set("store", noHyperviserList);
            dijit.byId("tempHypervisorWidget").set("value", "option");            
            OsTemplateInfo.filterTemplate("first");            
        } else {
             var hyperviserRestStore = new JsonRest({
                 target: core.getContextPath() + "/api/hypervisor/"
             });
            hyperviserRestStore.get(currentZone.value).then(function(data) {
                if(data.length == 1  || data.length == 0) {
                    dojo.query("#hypervisorDiv").toggleClass("hide_text", true); 
                } else {
                    dojo.query("#hypervisorDiv").removeClass("hide_text", true); 
                }                
                dojo.forEach(data, function(el) {
                    hyperviserList.newItem({id: el.name, name: el.name});                            
                });
                dijit.byId("tempHypervisorWidget").set("store",hyperviserList);            
            });  
            OsTemplateInfo.filterTemplate("first");
        }                        
    },
    serachTemplate : function () {
        dojo.query("#templateDesc").toggleClass("hide_text", true);  
        dojo.query("#templatePaginationList").removeClass("hide_text", true);
        var osWidgetTypeId = dijit.byId("tempCategoryWidget").value;
        var osCategoryId = dijit.byId("tempOSTypeWidget").value;
        var hypervisorWidgeId = dijit.byId("tempHypervisorWidget").value;
        var name = dijit.byId("serachFilterBox").getValue();
        var path = "";          
        var costType = "";     
        dojo.query("#templateCollection .tpl-box-cont").forEach(dojo.destroy);
        dojo.byId("templateCollection").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var templateRestStore = new JsonRest({
            target: core.getContextPath() + "/api/template/pagination"
        });
        var pageNo = 1;
        var osTypeId = "";
        var categoryId = "";
        var zoneId = "";
        var hypervisorId = "";   
        var currentZoneID = dojo.byId("selectedANZoneID").value;                
        
        if(hypervisorWidgeId != "option") {
            hypervisorId = hypervisorWidgeId
        } else {
            hypervisorId = "null"
        }
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            zoneId = "null"
        } else {
            zoneId = currentZoneID;
        }
        if(osWidgetTypeId == "option") {
            osTypeId = "null"  
        } else if(osWidgetTypeId >= 0) {
            osTypeId = dijit.byId("tempCategoryWidget").get("displayedValue");              
        } else {
            osTypeId = dijit.byId("tempCategoryWidget").get("displayedValue");           
        }
        
        if(osCategoryId != "option") {
            categoryId = osCategoryId
        } else {
            categoryId = "null"
        }
        
        templateRestStore.query({
            page: pageNo,
            name: name,   
            zoneReferenceId:zoneId,
            hypervisor:hypervisorId,
            osType:categoryId, 
            architecture:osTypeId                     
        }).then(function(data) { 
            dojo.byId("templateCollection").innerHTML = "";
            var templateInfo = "";
            if(data == "" || data.length == 0 || data == "undefined" || data == "null") {   
                dijit.byId("paginationWidget").set('totalResults', 0);
                dijit.byId("paginationWidget").set('resultsPerPage', 0);
                dijit.byId("paginationWidget").set('currentPage', 1);
            } else {
                dijit.byId("paginationWidget").set('totalResults', data[0].totalRecords);
                dijit.byId("paginationWidget").set('resultsPerPage', data[0].recordPerPage);
                dijit.byId("paginationWidget").set('currentPage', data[0].page);
                dojo.forEach(data[0].templateInfo, function(tempData, index) {                        
                    if(tempData.isReady == true) {                                   
                        if(tempData.appTemplate == true) {
                            path = tempData.imagePath;
                        } else {
                            if(tempData.osCategory == "CentOS") {
                                path ='images/os/os_centos_logo.png'
                            } else if(tempData.osCategory == "Debian") {
                                path ='images/os/os_debian_logo.png'
                            } else if(tempData.osCategory == "Oracle") {
                                path ='images/os/os_oracle_logo.png'
                            } else if(tempData.osCategory == "RedHat") {
                                path ='images/os/os_redhat_logo.png'
                            } else if(tempData.osCategory == "SUSE") {
                                path ='images/os/os_suse_logo.png'
                            } else if(tempData.osCategory == "Windows") {
                                path ='images/os/os_win_logo.png'
                            } else if(tempData.osCategory == "Novel") {
                                path ='images/os/os_novell_logo.png'
                            } else if(tempData.osCategory == "Unix") {
                                path ='images/os/os_unix_logo.png'
                            } else if(tempData.osCategory == "Ubuntu") {
                                path ='images/os/os_ubuntu_logo.png'
                            }                            
                        }      
                        if(tempData.oneTimeChargeable == true || tempData.oneTimeChargeable == "true") {
                            costType = translator.common.oneTime;
                        } else {
                            costType = translator.common.month;
                        }
                        var collections = tempData.id + "," + tempData.zoneId;
                        templateInfo =  new FogPanel.TemplateInfo({                                    
                            osLogoPath : path,        
                            templateCost : tempData.cost + "/" + costType,
                            templateCurrency : tempData.currency,
                            templateName : tempData.templateName,
                            templateDesc : tempData.description,
                            path : "#/user/cloud/createVm/"+collections,
                            zoneName : tempData.zone,
                            onMoreOptionClick : function () {
                                OsTemplateInfo.showTemplateDescription(tempData.id, tempData.zone);   
                            }
                        })                    
                        templateInfo.placeAt("templateCollection");                    
                    }                            
                }); 
            }
                
            if(templateInfo == "") {               
                dojo.query("#otherTempHeading").toggleClass("hide_text", true);  
                dojo.byId("templateCollection").innerHTML = ""
                dojo.byId("noTemplateMessageBox").style.display = "block";
            } else {
                dojo.byId("noTemplateMessageBox").style.display = "none";
            }
        });  
        
    },
    filterTemplate : function (currentPage) {                
        var osWidgetTypeId = dijit.byId("tempCategoryWidget").value;
        var osCategoryId = dijit.byId("tempOSTypeWidget").value;
        var hypervisorWidgeId = dijit.byId("tempHypervisorWidget").value;
        
        dojo.query("#templateDesc").toggleClass("hide_text", true);
        dojo.query("#templatePaginationList").removeClass("hide_text", true);   
        
        var path = "";          
        var costType = "";     
        dojo.query("#templateCollection .tpl-box-cont").forEach(dojo.destroy);
        dojo.byId("templateCollection").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var templateRestStore = new JsonRest({
            target: core.getContextPath() + "/api/template/pagination"
        });
        var osTypeId = "";
        var categoryId = "";
        var zoneId = "";
        var hypervisorId = "";
        var name = dijit.byId("serachFilterBox").getValue();
        var searchName = "";
        if(hypervisorWidgeId != "option") {
            hypervisorId = hypervisorWidgeId
        } else {
            hypervisorId = "null"
        }
        var currentZoneID = dojo.byId("selectedANZoneID").value;      
        
        if(currentZoneID == "All" || currentZoneID == " " || currentZoneID == "undefind" || currentZoneID == "") {
            zoneId = "null" 
        } else {
            zoneId = currentZoneID
        }
        if(osWidgetTypeId == "option") {
            osTypeId = "null"  
        } else if(osWidgetTypeId >= 0) {
            osTypeId = dijit.byId("tempCategoryWidget").get("displayedValue");              
        } else {
            osTypeId = dijit.byId("tempCategoryWidget").get("displayedValue");           
        }
        if(osCategoryId != "option") {
            categoryId = osCategoryId
        } else {
            categoryId = "null"
        }
        if(name == "") {
            searchName = "null"
        } else {
            searchName = dijit.byId("serachFilterBox").getValue();
        }
        var pageNo = 1;
        if(currentPage == "first") {
            pageNo = 1;
        } else if (currentPage == "page") {
            pageNo = dijit.byId("paginationWidget").get("currentPage");
        }                
        templateRestStore.query({
            page: pageNo,
            zoneReferenceId:zoneId,
            hypervisor:hypervisorId, 
            osType:categoryId,
            architecture: osTypeId, 
            name: searchName
        }).then(function(data) { 
            dojo.byId("templateCollection").innerHTML = "";
            var templateInfo = "";
            if(data == "" || data.length ==0 || data == "undefined" || data == "null") {   
                dijit.byId("paginationWidget").set('totalResults', 0);
                dijit.byId("paginationWidget").set('resultsPerPage', 0);
                dijit.byId("paginationWidget").set('currentPage', 1);
            } else {
                dijit.byId("paginationWidget").set('totalResults', data[0].totalRecords);
                dijit.byId("paginationWidget").set('resultsPerPage', data[0].recordPerPage);
                dijit.byId("paginationWidget").set('currentPage', data[0].page);
                dojo.forEach(data[0].templateInfo, function(tempData, index) {                        
                    if(tempData.isReady == true) {                                   
                        if(tempData.appTemplate == true) {
                            path = tempData.imagePath;
                        } else {
                            if(tempData.osCategory == "CentOS") {
                                path ='images/os/os_centos_logo.png'
                            } else if(tempData.osCategory == "Debian") {
                                path ='images/os/os_debian_logo.png'
                            } else if(tempData.osCategory == "Oracle") {
                                path ='images/os/os_oracle_logo.png'
                            } else if(tempData.osCategory == "RedHat") {
                                path ='images/os/os_redhat_logo.png'
                            } else if(tempData.osCategory == "SUSE") {
                                path ='images/os/os_suse_logo.png'
                            } else if(tempData.osCategory == "Windows") {
                                path ='images/os/os_win_logo.png'
                            } else if(tempData.osCategory == "Novel") {
                                path ='images/os/os_novell_logo.png'
                            } else if(tempData.osCategory == "Unix") {
                                path ='images/os/os_unix_logo.png'
                            } else if(tempData.osCategory == "Ubuntu") {
                                path ='images/os/os_ubuntu_logo.png'
                            }                            
                        }       
                        if(tempData.oneTimeChargeable == true || tempData.oneTimeChargeable == "true") {
                            costType = translator.common.oneTime;
                        } else {
                            costType = translator.common.month;
                        }
                        var collections = tempData.id + "," + tempData.zoneId;
                        templateInfo =  new FogPanel.TemplateInfo({                                    
                            osLogoPath : path,        
                            templateCost : tempData.cost + "/" + costType,
                            templateCurrency : tempData.currency,
                            templateName : tempData.templateName,
                            templateDesc : tempData.description,
                            path : "#/user/cloud/createVm/"+collections,
                            zoneName : tempData.zone,
                            onMoreOptionClick : function () {
                                OsTemplateInfo.showTemplateDescription(tempData.id, tempData.zone);   
                            }
                        })                    
                        templateInfo.placeAt("templateCollection");                    
                    }                            
                }); 
            }
                          
            if(templateInfo == "") {               
                dojo.query("#otherTempHeading").toggleClass("hide_text", true);  
                dojo.byId("templateCollection").innerHTML = ""
                dojo.byId("noTemplateMessageBox").style.display = "block";
            } else {
                dojo.byId("noTemplateMessageBox").style.display = "none";
            }
        });    
    },
    populateOtherTemplates : function (category, currentTemplate) {
        var path = "";          
        var costType = "";     
        dojo.query("#templateCollection .tpl-box-cont").forEach(dojo.destroy);
        dojo.byId("templateCollection").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.loading+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var templateRestStore = new JsonRest({
            target: core.getContextPath() + "/api/template/byCategory"
        });          
        templateRestStore.query({osType: category}).then(function(data) { 
            dojo.byId("templateCollection").innerHTML = "";
            var templateInfo = "";
            dojo.forEach(data, function(tempData, index) {                        
                if(tempData.isReady == true && tempData.id != currentTemplate) {
                    if(tempData.appTemplate == true) {
                        path = tempData.imagePath;
                    } else {
                        if(tempData.osCategory == "CentOS") {
                            path ='images/os/os_centos_logo.png'
                        } else if(tempData.osCategory == "Debian") {
                            path ='images/os/os_debian_logo.png'
                        } else if(tempData.osCategory == "Oracle") {
                            path ='images/os/os_oracle_logo.png'
                        } else if(tempData.osCategory == "RedHat") {
                            path ='images/os/os_redhat_logo.png'
                        } else if(tempData.osCategory == "SUSE") {
                            path ='images/os/os_suse_logo.png'
                        } else if(tempData.osCategory == "Windows") {
                            path ='images/os/os_win_logo.png'
                        } else if(tempData.osCategory == "Novel") {
                            path ='images/os/os_novell_logo.png'
                        } else if(tempData.osCategory == "Unix") {
                            path ='images/os/os_unix_logo.png'
                        } else if(tempData.osCategory == "Ubuntu") {
                            path ='images/os/os_ubuntu_logo.png'
                        }                            
                    }     
                    if(tempData.oneTimeChargeable == true || tempData.oneTimeChargeable == "true") {
                        costType = translator.common.oneTime;
                    } else {
                        costType = translator.common.month;
                    }
                    var collections = tempData.id + "," + tempData.zoneId;
                    templateInfo =  new FogPanel.TemplateInfo({                                    
                        osLogoPath : path,        
                        templateCost : tempData.cost + "/" + costType,
                        templateCurrency : tempData.currency,
                        templateName : tempData.templateName,
                        templateDesc : tempData.description,
                        path : "#/user/cloud/createVm/"+collections,
                        zoneName : tempData.zone,
                        onMoreOptionClick : function () {
                            OsTemplateInfo.showTemplateDescription(tempData.id, tempData.zone);   
                        }
                    })                    
                    templateInfo.placeAt("templateCollection");
                    
                }                            
            });     
            if(templateInfo == "") {               
                dojo.query("#otherTempHeading").toggleClass("hide_text", true);  
            }
        });     
    },
    showTemplateDescription : function (currentTemplateId, zoneName) {
        dojo.query("#templateDesc").removeClass("hide_text", true);
        dojo.query("#templatePaginationList").toggleClass("hide_text", true);        
        
        var currentTemplateRestStore = new JsonRest({
            target: core.getContextPath()+"/api/template/"
        });  
        var costType = "";
        currentTemplateRestStore.get(currentTemplateId).then(function (data) {
            var path = "";
            if(data[0].appTemplate == true) {
                path = data[0].imagePath;
            } else {
                if(data[0].osCategory == "CentOS") {
                    path ='images/os/os_centos_logo.png'
                } else if(data[0].osCategory == "Debian") {
                    path ='images/os/os_debian_logo.png'
                } else if(data[0].osCategory == "Oracle") {
                    path ='images/os/os_oracle_logo.png'
                } else if(data[0].osCategory == "RedHat") {
                    path ='images/os/os_redhat_logo.png'
                } else if(data[0].osCategory == "SUSE") {
                    path ='images/os/os_suse_logo.png'
                } else if(data[0].osCategory == "Windows") {
                    path ='images/os/os_win_logo.png'
                } else if(data[0].osCategory == "Novel") {
                    path ='images/os/os_novell_logo.png'
                } else if(data[0].osCategory == "Unix") {
                    path ='images/os/os_unix_logo.png'
                } else if(data[0].osCategory == "Ubuntu") {
                    path ='images/os/os_ubuntu_logo.png'
                }                            
            }
            if(data[0].oneTimeChargeable == true || data[0].oneTimeChargeable == "true") {
                costType = translator.common.oneTime;
            } else {
                costType = translator.common.month;
            }
            dojo.query("#breadcrumTemp").removeClass("hide_text", true);
            dojo.query("#breadcrumTempName").removeClass("hide_text", true);
            dojo.query("#otherTempHeading").removeClass("hide_text", true);            
            dojo.byId("otherTempHeading").innerHTML = "Other Templates on" + " "+data[0].osCategory
            dojo.byId("breadcrumTempName").innerHTML = data[0].name; //for breadcrumb
            dojo.byId("tempDescName").innerHTML = data[0].name + " (" + data[0].osType + ")"; // for heading tag
            dojo.byId("templateDetailDesc").innerHTML = data[0].detailedDescription
            dojo.byId("templatDecsLogo").src = path;
            dojo.byId("templDescOSType").innerHTML = data[0].osCategory;
            dojo.byId("templDescZone").innerHTML = zoneName;
            dojo.byId("templDescHypervisor").innerHTML = data[0].hypervisor;                        
            dojo.byId("tpl-website").innerHTML = data[0].referelUrl;
            dojo.byId("tpl-website").href = data[0].referelUrl;
            var collections = data[0].id + "," + data[0].zone[0].zoneId;
            dojo.byId("templDescCost").innerHTML ="  "+ data[0].currency + "  "+ data[0].cost +"  /  " +costType;
            dojo.byId("tempDescCreateVM").href = "#/user/cloud/createVm/" + collections;
            OsTemplateInfo.populateOtherTemplates(data[0].osCategory, data[0].id);        
        })        
    },
    hideTempDesc : function () {
        dojo.query("#breadcrumTemp").toggleClass("hide_text", true);
        dojo.query("#breadcrumTempName").toggleClass("hide_text", true);        
        dojo.query("#templateCollection").removeClass("hide_text", true);
        dojo.query("#templateDesc").toggleClass("hide_text", true);
//        dojo.query("#templatePaginationList").removeClass("hide_text", true);
        dojo.query("#otherTempHeading").toggleClass("hide_text", true);       
        OsTemplateInfo.filterTemplate("page");
//        OsTemplateInfo.populateValues();
    }
}

var TempConfigTest = { 
    init: function() {
        var config = new JsonRest({
            target: core.getContextPath()+"/api/config/test/"
        });

        config.query().then(function(data) {   
            if(data == "OK") { 
            } else {     
                registry.byId('appToaster').setContent(translator.common.message.configMissing, 'error');
                registry.byId('appToaster').show();
                dojo.byId("errorInfo").style.display = "block";                     
            }                    
        }); 
    },
    createVMValidationCheck: function(template) {
        var accountListStore = new JsonRest({
            target: core.getContextPath()+"/api/account/currentAccount"         
        });                         
        var configRestStore = new JsonRest({
            target: core.getContextPath()+"/api/config/"
        });
            
        var cardEnabled;
        var vmCardEnabled;
        var signupCardEnabled;
            
        configRestStore.query().then(function(resultData) {
            dojo.forEach(resultData, function(config) { 
                if(config.name == "signup.card.verification.enabled") {
                    if(config.value == "true" || config.value == true) {
                        signupCardEnabled = "true";
                    } else {
                        signupCardEnabled = "false";
                    }
                } else if(config.name == "creditcard.processing") {
                    if(config.value == "true" || config.value == true) {
                        cardEnabled = "true";
                    } else {
                        cardEnabled = "false";
                    }
                } else if(config.name == "creditcard.processing.in.createvm") {
                    if(config.value == "true" || config.value == true) {
                        vmCardEnabled = "true";
                    } else {
                        vmCardEnabled = "false";
                    }                     
                }  
            });
            if(cardEnabled == "true") {
                accountListStore.query().then(function(data) {
                    dojo.forEach(data, function(el) {
                        if(el.accountType == "RETAIL" && el.cardVerified == false && (signupCardEnabled == "false" || signupCardEnabled == "true")) {
                            registry.byId("userToaster").setContent("Card is Not Verified!","error");
                            registry.byId("userToaster").show();
                            core.router.go("#/user/billing/creditCard"); 
                        } else if(el.accountType == "TRIAL") {
                            var accountResourceLimitStore = new JsonRest({
                                target: core.getContextPath()+"/api/account/resourceLimit"
                            });
                            accountResourceLimitStore.query().then(function(data) {
                                dojo.forEach(data, function(limitData) {  
                                    if(limitData.accountType == "TRIAL") {
                                        if((limitData.storageLimitNo == limitData.volumeCount) || (limitData.vmLimitNo == limitData.vmCount)) {
                                            core.router.go("#/user/cloud/userInstancePage");
                                            registry.byId("userToaster").setContent(translator.common.message.trialAccountResource,"error");
                                            registry.byId("userToaster").show();
                                        } else {                                                
                                            if(template) { 
                                                core.router.go("#/user/cloud/createVm/"+template); 
                                            } else {
                                                core.router.go("#/user/cloud/createVm"); 
                                            }                                                
                                        }
                                    } 
                                });
                            });
                        } else if(el.accountType == "RETAIL" && el.cardVerified == true) {
                            if(template) {
                                core.router.go("#/user/cloud/createVm/"+template);
                            } else {
                                core.router.go("#/user/cloud/createVm");
                            }
                        } 
                    });
                });
            } else {
                accountListStore.query().then(function(data) {
                    dojo.forEach(data, function(el) {
                        if(el.accountType == "RETAIL") {
                            if(template) { 
                                core.router.go("#/user/cloud/createVm/"+template); 
                            } else {
                                core.router.go("#/user/cloud/createVm"); 
                            }
                        } else if(el.accountType == "TRIAL") {
                            var accountResourceLimitStore = new JsonRest({
                                target: core.getContextPath()+"/api/account/resourceLimit"
                            });                            
                            accountResourceLimitStore.query().then(function(data) {
                                dojo.forEach(data, function(limitData) {  
                                    if(limitData.accountType == "TRIAL") {
                                        if((limitData.storageLimitNo == limitData.volumeCount) || (limitData.vmLimitNo == limitData.vmCount)) {
                                            core.router.go("#/user/cloud/userInstancePage");
                                            registry.byId("userToaster").setContent(translator.common.message.trialAccountResource,"error");
                                            registry.byId("userToaster").show();
                                        } else {
                                            if(template) { 
                                                core.router.go("#/user/cloud/createVm/"+template);
                                            } else {
                                                core.router.go("#/user/cloud/createVm");
                                            }
                                        }
                                    } 
                                });
                            });
                        } 
                    });
                });
            }
        });
    }    
};



window.OsTemplateInfo = OsTemplateInfo;
