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
            name: "signin",
            module: "admin",
            filePath: "/js/app/account/signin.js",
            layout: {
                name: "signin",
                containerId: "content"
            },	
            scaffold: false
        },
        {           
            "index" : action(function() {
                
                })
            });
        });


var FaasLogin = {
    ssoUrl: null,
    ssoRequest: function() {

//        var applicationURL = FaasLogin.ssoUrl ;
        var applicationURL = "http://localhost:7070/FogPanel/login/ssoSuccess";
        
//        var sessionInfoRest = new JsonRest({
//            target: core.getContextPath() + "/api/cloudEngine/getSessionInfo"
//        });
//        
//        var openStackUrl = "" ;
//        var sessionId = "" ;
//        var requestedSessionId = "";
//        var userName = "";
//        
//        var ssoRequestUrl = "";
//        var reAuthUrl = "";
//        
//        sessionInfoRest.query().then(function(data) {
//            dojo.forEach(data, function(resultData) {
//                openStackUrl = resultData.openstackUrl
//                sessionId = resultData.sessionId
//            });
//            
//            var ssoRequestRest = new JsonRest({
//                target: openStackUrl + "/login/sessionInfo?sessionId=" +sessionId+""
//            });
//
//            ssoRequestUrl = openStackUrl + "/login/sessionInfo?sessionId=" +sessionId;
////            ssoRequestRest.add({
////                sessionId: sessionId
////            }).then(function(data) {
////                dojo.forEach(data, function(resultData) {
////                    if(resultData.result == "OK") {
////                        console.log("Successfully get response from openstack")
////                    }
////                    requestedSessionId = resultData.sessionId
////                });
//                
//                dojo.xhrGet({
//                    // The URL of the request
//                    url: ssoRequestUrl,
//                    // The success callback with result from server
//                    load: function(data) {
//                        console.log("data: "+data)
//                          dojo.forEach(data, function(resultData) {
//                            if(resultData.result == "OK") {
//                                console.log("Successfully get response from openstack")
//                            }
//                            requestedSessionId = resultData.sessionId
//                        });
//                        
//                    },
//                      
//                });
//                
//                requestedSessionId = sessionId;
//                
//                var getUserDetail = new JsonRest({
//                    target: core.getContextPath() + "/api/cloudEngine/session/getUser"
//                });
//
//                getUserDetail.query({
//                    sessionId: requestedSessionId
//                }).then(function(data) {
//                    dojo.forEach(data, function(resultData) {
//                       userName = resultData.userName;
//                    });
//
//                     //reauth openstack
//                    reAuthUrl = openStackUrl + "/login/reAuth?userName=" +userName;
//
//                    dojo.xhrPost({
//
//                        url: reAuthUrl,
//                    // The success callback with result from server
//                    load: function(data) {
//                          console.log("success--")
//                    }
//
//                    });
//
//
//                });
//                               
//                
////                //reauth openstack
////                var url = openStackUrl + "/login/reAuth?userName=" +userName;
////                window.open(url);
////                window.focus();
//                
////            });
//            
//            
//            
//        });
        
        

        var url = null;
        if (applicationURL !== undefined && applicationURL !== null && applicationURL.length > 0) {

            url = unescape(applicationURL);
             dojo.xhrGet({
                // The URL of the request
                url: url,
                // The success callback with result from server
                load: function(content) {
                      console.log("succes"+content);
//                    g_loginResponse = content;
//                    document.getElementById("csLoginResponse").value = g_loginResponse;
//                    AccountLogin._formPost();

                    //return true;
                },
                // The error handler
                error: function(content) {
                    console.log("err"+content);
//                    g_loginResponse = content;
//                    document.getElementById("csLoginResponse").value = g_loginResponse;
//                   AccountLogin._formPost();
                   
                    // This means the login failed.  You should redirect to your login page.
                    //return false;
                }
            });

        }

    },
    'ssoCall': function() {
        var sessionRest = new JsonRest({
             target: core.getContextPath() + "/api/cloudEngine/getSessionInfo"
        });
        var userName = "";
        var password = "";
        var openstackUrl = "";
        
        
        
        sessionRest.query({
            
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {
               userName = resultData.userName;
               password = resultData.password;
               openstackUrl = resultData.openstackUrl;
            });
            
            var applicationURL = openstackUrl + "/login/ssoSuccess?userName=" +userName+"";
//            window.open(applicationURL);
//            window.focus();
            var url = null;
        if (applicationURL !== undefined && applicationURL !== null && applicationURL.length > 0) {

            url = unescape(applicationURL);
             dojo.xhrGet({
                // The URL of the request
                url: url,
                // The success callback with result from server
                load: function(content) {
                      console.log("succes"+content);
//                    g_loginResponse = content;
//                    document.getElementById("csLoginResponse").value = g_loginResponse;
//                    AccountLogin._formPost();

                    //return true;
                },
                // The error handler
                error: function(content) {
                    console.log("err"+content);
//                    g_loginResponse = content;
//                    document.getElementById("csLoginResponse").value = g_loginResponse;
//                   AccountLogin._formPost();
                   
                    // This means the login failed.  You should redirect to your login page.
                    //return false;
                }
            });

        }
            
            
            
            
        });
    },
    'verify': function(engineId) {
        var engineRest = new JsonRest({
            target: core.getContextPath() + "/api/cloudEngine/view"
        });
        var engineId = "";
        var cloudUrl; 
        var applicationURL = "";
        var verify = false;
        engineRest.query({engineId: engineId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                engineId = resultData.cloudEngineId;
                cloudUrl = resultData.url;
            });
            
            
        var applicationRest = new JsonRest({
            target: cloudUrl + "/api/auth/verify?engineId="+engineId+""
        });    
        
        applicationRest.query().then(function(data) {
           dojo.forEach(data, function(resultData) {
               if(resultData.result == "OK") {
                   verify = true;
               } 
           });
        });
            
        });
        
    },


};
window.FaasLogin = FaasLogin;

