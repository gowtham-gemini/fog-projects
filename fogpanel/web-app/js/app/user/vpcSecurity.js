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
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dojox/calendar/Calendar",
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
    "FogPanel/VPNStatus",
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
    "dijit/Dialog"
],
function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
ItemFileWriteStore, Memory, Observable, DataGrid, EnhancedGrid, Calendar, ContentPane, Source, MultiSelect, dom, win) {             
    window.query = query;
    window.domClass = domClass;
    window.domConstruct = domConstruct;
    window.JsonRest = JsonRest;
    window.registry = registry;
    window.FilteringSelect = FilteringSelect;
    window.ItemFileWriteStore = ItemFileWriteStore;
    window.Observable = Observable;
    window.Memory = Memory;
    window.Select = Select;
    window.ContentPane = ContentPane;
    window.DataGrid = DataGrid;
    window.EnhancedGrid = EnhancedGrid;
    window.Calendar = Calendar;
    window.Source = Source;
    window.MultiSelect = MultiSelect;
    window.dom = dom;
    window.win = win;        
    controller ({
        name: "vpcSecurity",
        module: "user",
        filePath: "/js/app/user/vpcSecurity.js",
        layout: {
            name: "vpcSecurity",
            containerId: "content"
        },	
        scaffold: false
    },
    {   "index" : action(function () {
            core.ui.loadTemplate("securityContainer", core.ui.getContentId());
            ACLInfo.pupulateCounts();
        }),
        "listNetworkAcl": action(function() {
            if (dijit.byId("networkAclLoader")) {
                dijit.byId("networkAclLoader").destroyRecursive();
            }
            if (dijit.byId("aclRuleForm")) {
                dijit.byId("aclRuleForm").destroyRecursive();
            }
            if (dijit.byId("networkAclDeleteDialog")) {
                dijit.byId("networkAclDeleteDialog").destroyRecursive();
            } 
            if (dijit.byId("aclDetailedTab")) {
                dijit.byId("aclDetailedTab").destroyRecursive();
            }
            core.ui.loadTemplate("networkAclList", core.ui.getContentId());
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            ListNetworkACL.populateValues();            
        }),
        "addNetworlAcl": action(function(id) {

            if (dijit.byId("userCreateNetworkAclForm")) {
                dijit.byId("userCreateNetworkAclForm").destroyRecursive();
            }            
            if (dijit.byId("aclDetailedTab")) {
                dijit.byId("aclDetailedTab").destroyRecursive();
            }
            if (dijit.byId("networkAclLoader")) {
                dijit.byId("networkAclLoader").destroyRecursive();
            }
            if (dijit.byId("aclAddRuleLoader")) {
                dijit.byId("aclAddRuleLoader").destroyRecursive();
            } 
            if (dijit.byId("aclRuleForm")) {
                dijit.byId("aclRuleForm").destroyRecursive();
            }
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            core.ui.loadTemplate("createNetworkAcl", core.ui.getContentId());
            AddNetworkAcl.init(id);
        }),
        "view" : action(function (id) {             
            if (dijit.byId("aclRuleForm")) {
                dijit.byId("aclRuleForm").destroyRecursive();
            }
            if (dijit.byId("aclDetailedTab")) {
                dijit.byId("aclDetailedTab").destroyRecursive();
            }
            
            if (dijit.byId("networkAclLoader")) {
                dijit.byId("networkAclLoader").destroyRecursive();
            }
            if (dijit.byId("networkAclDeleteDialog")) {
                dijit.byId("networkAclDeleteDialog").destroyRecursive();
            } 
            if (dijit.byId("networkAclRuleDeleteDialog")) {
                dijit.byId("networkAclRuleDeleteDialog").destroyRecursive();
            } 
            if (dijit.byId("networkAclRuleEditConfirmDialog")) {
                dijit.byId("networkAclRuleEditConfirmDialog").destroyRecursive();
            }             
            if (dijit.byId("aclAddRuleLoader")) {
                dijit.byId("aclAddRuleLoader").destroyRecursive();
            } 
//            var currentVPC = dojo.byId("selectedANVPCID").value;
//            VPCMenuInfo.populateValues(currentVPC);
            core.ui.loadTemplate("viewACL", core.ui.getContentId());   

            ACLInfo.init(id);
//            ACLInfo.populateValues(id);           
        })
    });
});
var ACLInfo = {
    pupulateCounts : function () {
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var vpcStatRestResource = new JsonRest({
                target: core.getContextPath()+"/api/vpc/stat"
        });
        var currentVPC = dojo.byId("selectedANVPCID").value;
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {
            dojo.byId("securityAcltotalCounts").innerHTML = "";            
            if(currentVPC === "All" || currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                vpcStatRestResource.query().then(function (data) {
                    if(data[0].networkAcl === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {   
                        dojo.byId("securityAcltotalCounts").innerHTML = 0;
                    } else {
                        dojo.byId("securityAcltotalCounts").innerHTML = data[0].networkAcl;
                    }                    
                });
            } else {
                vpcStatRestResource.query({vpcId:currentVPC}).then(function (data) {                    
                    if(data[0].networkAcl === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {   
                        dojo.byId("securityAcltotalCounts").innerHTML = 0;
                    } else {
                        dojo.byId("securityAcltotalCounts").innerHTML = data[0].networkAcl;
                    }
                });
            }     
        } else {            
            var zoneResource = new JsonRest({
                target: core.getContextPath()+"/api/zone/"
            });              
            zoneResource.get(currentZoneID).then(function (data) {
                dojo.byId("vpcDataCenter").innerHTML = translator.common.intheData + data.aliasName               
            });             
            if(currentVPC === "All" ||  currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID}).then(function (data) {
                    if(data[0].networkAcl === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {   
                        dojo.byId("securityAcltotalCounts").innerHTML = 0;
                    } else {
                        dojo.byId("securityAcltotalCounts").innerHTML = data[0].networkAcl;
                    }
                });
            } else {
                vpcStatRestResource.query({zoneReferenceId: currentZoneID, vpcId:currentVPC}).then(function (data) {                    
                    if(data[0].networkAcl === undefined || data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {   
                        dojo.byId("securityAcltotalCounts").innerHTML = 0;
                    } else {
                        dojo.byId("securityAcltotalCounts").innerHTML = data[0].networkAcl;
                    }
                });
            } 
        }
    },
    'init' : function (id) {
        dojo.byId("currentAclId").value = id;
        var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl"
        }); 
                  
        aclRestStore.query({referenceId: id}).then(function(data) {
            dojo.forEach(data, function(aclData) {                 
                 dojo.byId("viewAclName").innerHTML = aclData.name;
                 dojo.byId("viewAclDesc").innerHTML = aclData.description;
                 dojo.byId("viewAclReferenceId").innerHTML = aclData.referenceId;                                  
             });       
         });         
    },
    populateValues : function () {              
        ACLInfo.showOptionOnProtocolBase("TCP");
    },
    'deleteRule' : function(currentId) {
        dojo.byId("currentAclRuleId").value = currentId;
        dijit.byId("networkAclRuleDeleteDialog").show();
        
    },
    'deleteAclRuleClose' : function() {        
        dijit.byId("networkAclRuleDeleteDialog").hide();        
    },
    updateRuleConfirm : function () {        
        var pageNode = dojo.byId("aclRuleForm");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;  
        var stat = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {                
                el.focus();     
                stat = false;
            }
        });

        if (stat === true) {
            dojo.query("#aclRuleCloudStackException").toggleClass("hide_text", true);
            dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = "";
            dijit.byId("networkAclRuleEditConfirmDialog").show();               
        } 
    },
    editACLRule : function () {
        var currentId = dojo.byId("currentAclRuleId").value;
        var cidr = dijit.byId("aclCIDR");
        var aclRuleNum = dijit.byId("aclRuleNum");
        var aclAction = dijit.byId("aclAction");
        var icmpType = dijit.byId("aclIcmpType");
        var icmpCode = dijit.byId("aclIcmpCode");
        var aclTrafficType = dijit.byId("aclTrafficType");        
        var startPort = dijit.byId("aclStartport");
        var endPort = dijit.byId("aclEndport");
        var protocol = dijit.byId("aclProtocol");
        var aclId = dojo.byId("currentAclId");        
        var pageNode = dojo.byId("aclRuleForm");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {                
                el.focus();
                state = false;               
            }
        });
       
        if (state === true) {
             dijit.byId("aclAddRuleLoader").show();
             dijit.byId("networkAclRuleEditConfirmDialog").hide();
             
            var editRuleRestStore = new JsonRest({
                target: core.getContextPath()+"/api/networkAcl/rule/edit/"
            });                                   
            var currentProto = dijit.byId("aclProtocol").value === "protocolnumber" ? dijit.byId("aclProtocolNumber") : dijit.byId("aclProtocol").value;                 
            editRuleRestStore.put({
                id: currentId,
                protocol: currentProto.toString(), 
                cidr: cidr.value,
                icmpType: icmpType.value,
                icmpCode: icmpCode.value,
                startPort: startPort.value,
                endPort: endPort.value, 
                aclId: aclId.value, 
                traffictype:aclTrafficType.value,
                action:aclAction.value,
                aclRuleNum:aclRuleNum.value
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var ruleJob = setInterval(function(){ACLInfo.editRuleJob(resultData.jobId, ruleJob);},1000);   
                         
                         dojo.query("#aclRuleCloudStackException").toggleClass("hide_text", true);
                         dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = "";
                    } else if (data && resultData.result !== "OK") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("aclAddRuleLoader").hide();
                        dojo.query("#aclRuleCloudStackException").removeClass("hide_text", true);
                        dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = resultData.message;
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("aclAddRuleLoader").hide();
                        
                        dojo.query("#aclRuleCloudStackException").removeClass("hide_text", true);
                        dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = resultData.message;
                    }
                });
            });
            
        } else {             
            firstNode.focus();
        }
        
    },
    editRuleJob : function (jobId, ruleJobstat) {       
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job/"
        });         
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(ruleJobstat);
                    registry.byId("userToaster").setContent(translator.common.message.aclRuleUpdatedSuccess, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("aclAddRuleLoader").hide();                    
                    ACLInfo.populateAclRuleInfo();
                    dijit.byId("aclRuleForm").reset();
                    
                    dojo.query("#aclRuleCloudStackException").toggleClass("hide_text", true);
                    dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = "";
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(ruleJobstat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("aclAddRuleLoader").hide();
                    
                    dojo.query("#aclRuleCloudStackException").removeClass("hide_text", true);
                    dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = resultData.message;
                } else {
                    clearInterval(ruleJobstat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("aclAddRuleLoader").hide();
                    
                    dojo.query("#aclRuleCloudStackException").removeClass("hide_text", true);
                    dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = resultData.message;
                } 
            });
        });
    },
    editRuleClose : function () {
        ACLInfo.showOptionOnProtocolBase("tcp");
        dijit.byId("aclRuleForm").reset();
        dijit.byId("networkAclRuleEditConfirmDialog").hide();
        dojo.query("#addButtonContainer").removeClass("hide_text", true);
        dojo.query("#updateButtonContainer").toggleClass("hide_text", true);   
        dojo.query("#cancelButtonContainer").toggleClass("hide_text", true);        
        
    },
    'deleteAclRule': function() {
        var currentId = dojo.byId("currentAclRuleId").value;
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkAcl/rule/delete"
        });

        dijit.byId("aclAddRuleLoader").show();
        dijit.byId("networkAclRuleDeleteDialog").hide();

        networkRestStore.add({ruleId:currentId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var deleteNWAclRuleJobStat = setInterval(function(){ACLInfo.deleteAclRuleJob(resultData.jobId, deleteNWAclRuleJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("aclAddRuleLoader").hide();
                }
            });
        });
    },
    'deleteAclRuleJob' : function(jobId, deleteNWAclJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job/"
        });         
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.networkAclRuleDeleted, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("aclAddRuleLoader").hide();
                    ACLInfo.populateAclRuleInfo();
                    dijit.byId("aclRuleForm").reset();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("aclAddRuleLoader").hide();
                } else {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("aclAddRuleLoader").hide(); 
                } 
            });
        });
    },
    editRule : function (data) {          
        dojo.byId("currentAclRuleId").value = data.id;
        var cidr = dijit.byId("aclCIDR");
        var aclRuleNum = dijit.byId("aclRuleNum");
        var aclAction = dijit.byId("aclAction");
        var icmpType = dijit.byId("aclIcmpType");
        var icmpCode = dijit.byId("aclIcmpCode");
        var aclTrafficType = dijit.byId("aclTrafficType");
        var aclProtocolNum = dijit.byId("aclProtocolNumber");
        var startPort = dijit.byId("aclStartport");
        var endPort = dijit.byId("aclEndport");
        var protocol = dijit.byId("aclProtocol");
        var aclId = dojo.byId("currentAclId");        
        aclRuleNum.setValue(data.ruleNo);
        cidr.setValue(data.cidr);
        aclAction.set("value", data.action)        
        startPort.setValue(data.startPort);
        endPort.setValue(data.endPort);
        aclTrafficType.setValue(data.trafficType);                   
        var currentProtocol = "";
        if(data.protocol === "TCP") {
            currentProtocol = "tcp"
            protocol.set("value", "tcp");
        } else if(data.protocol === "UDP") {
            currentProtocol = "udp"    
            protocol.set("value", "udp");
        } else if(data.protocol === "ICMP") {
            currentProtocol = "icmp";         
            icmpType.setValue(data.startPort);
            icmpCode.setValue(data.endPort);
            protocol.set("value", "icmp");
        } else if(data.protocol === "ALL") {
            currentProtocol = "all"
            protocol.set("value", "all");
        } else {
            protocol.set("value", "protocolnumber");
            currentProtocol = data.protocol;
            aclProtocolNum.setValue(data.protocol);
        }
        ACLInfo.showOptionOnProtocolBase(currentProtocol);                
        dojo.query("#addButtonContainer").toggleClass("hide_text", true);
        dojo.query("#updateButtonContainer").removeClass("hide_text", true);   
        dojo.query("#cancelButtonContainer").removeClass("hide_text", true);   
        
    },
    populateAclRuleInfo : function () {
        dojo.query("#aclRuleCloudStackException").toggleClass("hide_text", true);
        dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = "";
        ACLInfo.showOptionOnProtocolBase("tcp");
        if(dijit.byId("networkAclRuleGrid")) {                                    
            dijit.byId("networkAclRuleGrid").destroyRecursive();                    
        }
        dojo.byId("aclRuleGridList").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vpcGridData = {
            items: []
        }; 
        var networkACLList = new ItemFileWriteStore({data: vpcGridData}); 
        var aclLayout = [
            [               
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.ruleNo, 'field': 'ruleNo', 'width': '50px', 'datatype':'string',  'autoComplete': 'true'}, 
                {'name': translator.common.cidr, 'field': 'cidr', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                 
                {'name': translator.common.action, 'field': 'action', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.protocolType, 'field': 'protocol', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.firewall.startPort, 'field': 'startPort', 'width': '80px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        var startPort = "";
                        if(data.protocol === "TCP" || data.protocol === "UDP") {
                            startPort = data.startPort;
                        } else if(data.protocol === "ICMP") {
                            startPort = ""
                        } else {
                            startPort = data.startPort;
                        }
                        return startPort;
                }},                
                {'name': translator.common.firewall.endPort, 'field': 'endPort','width': '80px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        var endPort = "";
                        if(data.protocol === "TCP" || data.protocol === "UDP") {
                            endPort = data.endPort;
                        } else if(data.protocol === "ICMP") {
                            endPort = ""
                        } else {
                            endPort = data.endPort;
                        }
                        return endPort;
                }},                
                {'name': translator.common.firewall.icmpType, 'field': 'icmpType','width': '80px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        var icmpType = "";
                        if(data.protocol === "TCP" || data.protocol === "UDP") {
                            icmpType = "";
                        } else if(data.protocol === "ICMP") {
                            icmpType = data.icmpType;
                        } else {
                            icmpType = "";
                        }
                        return icmpType;
                }},                
                {'name': translator.common.firewall.icmpCode, 'field': 'icmpCode','width': '80px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        var icmpCode = "";
                        if(data.protocol === "TCP" || data.protocol === "UDP") {
                            icmpCode = "";
                        } else if(data.protocol === "ICMP") {
                            icmpCode = data.icmpCode;
                        } else {
                            icmpCode = "";
                        }
                        return icmpCode;
                }},                
                {'name': translator.common.firewall.trafficType, 'field': 'trafficType','width': '100px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.actions, 'field': 'actions','width': '100%', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) { 
                        var vpnStatus = new FogPanel.VPNStatus({                           
                           onEditVPN : function () {ACLInfo.editRule(data)},
                           onDeleteVPN : function () {ACLInfo.deleteRule(data.id)},
                           enableACLRuleStat : true,
                           editTile : translator.common.editRule,
                           deleteTile : translator.common.deleteRule                           
                       });
                       return vpnStatus;                           
                   }
               }                
           ]
         ];
         var aclRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/rule/list"
        });                   
        aclRuleRestStore.query({aclId: dojo.byId("currentAclId").value}).then(function(data) {
            if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noACLRuleMsgBox").style.display = "block";
                 dojo.byId("aclRuleGridList").innerHTML = "";
             } else {
                 dojo.byId("noACLRuleMsgBox").style.display = "none";
                 dojo.forEach(data, function(aclRule) {
                     networkACLList.newItem({
                         id:aclRule.id,
                         ruleNo: aclRule.ruleNo,
                         cidr:aclRule.cidrList,
                         action: aclRule.action, 
                         protocol: aclRule.protocol,
                         startPort: {startPort: aclRule.startPort, protocol: aclRule.protocol},     
                         endPort: {endPort: aclRule.endPort, protocol: aclRule.protocol},     
                         icmpType: {icmpType: aclRule.startPort, protocol: aclRule.protocol},     
                         icmpCode: {icmpCode: aclRule.endPort, protocol: aclRule.protocol},    
                         trafficType : aclRule.trafficType,
                         actions : {
                             id: aclRule.id,
                             ruleNo: aclRule.ruleNo,
                             cidr:aclRule.cidrList,
                             action: aclRule.action, 
                             protocol: aclRule.protocol,
                             startPort: aclRule.startPort,     
                             endPort: aclRule.endPort,     
                             icmpType: aclRule.startPort,     
                             icmpCode: aclRule.endPort,    
                             trafficType : aclRule.trafficType
                         }
                     });
                 });
                 dojo.byId("aclRuleGridList").innerHTML = "";
                 var vpcRuleGrid = new EnhancedGrid({
                     id: 'networkAclRuleGrid',
                    "class" : "span12",
                    store: networkACLList,
                    structure: aclLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                vpcRuleGrid.placeAt("aclRuleGridList");
                vpcRuleGrid.startup(); 
            }             
        }); 
        dijit.byId("aclRuleForm").reset();
        dojo.query("#addButtonContainer").removeClass("hide_text", true);
        dojo.query("#updateButtonContainer").toggleClass("hide_text", true);   
        dojo.query("#cancelButtonContainer").toggleClass("hide_text", true);  
    },
    addRule : function () {
                
        var cidr = dijit.byId("aclCIDR");
        var aclRuleNum = dijit.byId("aclRuleNum");
        var aclAction = dijit.byId("aclAction");
        var icmpType = dijit.byId("aclIcmpType");
        var icmpCode = dijit.byId("aclIcmpCode");
        var aclTrafficType = dijit.byId("aclTrafficType");
        var aclProtocolNum = dijit.byId("aclProtocolNumber");


        var startPort = dijit.byId("aclStartport");
        var endPort = dijit.byId("aclEndport");

        var protocol = dijit.byId("aclProtocol");

        var aclId = dojo.byId("currentAclId");
        
        var pageNode = dojo.byId("aclRuleForm");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var state = true;
        dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {                
                el.focus();
                state = false;               
            }
        });

        if(state === true){
             dijit.byId("aclAddRuleLoader").show();
             
            var addAclRuleRestStore = new JsonRest({
                target: core.getContextPath()+"/api/networkAcl/rule/add"
            }); 
            var currentProto = dijit.byId("aclProtocol").value === "protocolnumber" ? dijit.byId("aclProtocolNumber").value : dijit.byId("aclProtocol").value; 
            addAclRuleRestStore.add({
                protocol: currentProto.toString(), cidr: cidr.value, icmpType: icmpType.value, icmpCode: icmpCode.value,
                startPort: startPort.value, endPort: endPort.value, aclId: aclId.value, traffictype:aclTrafficType.value,
                action:aclAction.value, aclRuleNum:aclRuleNum.value
            }).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var ruleJob = setInterval(function(){ACLInfo.addRuleJob(resultData.jobId, ruleJob);},1000);   
                         dojo.query("#aclRuleCloudStackException").toggleClass("hide_text", true);
                         dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = "";
                    }else if (data && resultData.result !== "OK") {
                        registry.byId("userToaster").setContent(resultData.message, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("aclAddRuleLoader").hide();
                        
                        dojo.query("#aclRuleCloudStackException").removeClass("hide_text", true);
                        dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = resultData.message;
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("aclAddRuleLoader").hide();
                        
                        dojo.query("#aclRuleCloudStackException").removeClass("hide_text", true);
                        dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = resultData.message;
                    }
                });
            });

        }
        
    },
    addRuleJob : function (jobId, ruleJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job/"
        });         
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(ruleJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.aclRuleCreated, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("aclAddRuleLoader").hide();
                    dijit.byId("aclRuleForm").reset();
                    ACLInfo.populateAclRuleInfo();    
                    dojo.query("#aclRuleCloudStackException").toggleClass("hide_text", true);
                    dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = "";
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(ruleJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("networkAclLoader").hide();
                    
                    dojo.query("#aclRuleCloudStackException").removeClass("hide_text", true);
                    dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = resultData.message;
                } else {
                    clearInterval(ruleJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("aclAddRuleLoader").hide(); 
                    
                    dojo.query("#aclRuleCloudStackException").removeClass("hide_text", true);
                    dojo.byId("aclRuleCloudstackExceptionMsg").innerHTML = resultData.message;
                } 
            });
        });
    },
    showOptionOnProtocolBase : function (currentProtocol) {      
        if(currentProtocol === "tcp" || currentProtocol === "udp") {
            dojo.query("#aclProtocolNumberDiv").toggleClass("hide_text", true);    
            dojo.query("#aclStartPortDiv").removeClass("hide_text", true);    
            dojo.query("#aclEndPortDiv").removeClass("hide_text", true);    
            dojo.query("#aclIcmpTypeDiv").toggleClass("hide_text", true);    
            dojo.query("#aclIcmpCodeDiv").toggleClass("hide_text", true);    
            dojo.query("#aclTrafficTypeDiv").removeClass("hide_text", true);  
            
            dijit.byId("aclProtocolNumber").set("required", false);
            dijit.byId("aclStartport").set("required", true);
            dijit.byId("aclEndport").set("required", true);
            dijit.byId("aclIcmpType").set("required", false);
            dijit.byId("aclIcmpCode").set("required", false);    
            
            dojo.query("#aclTrafficTypeDiv").removeClass("no_left_magin", true); 
        } else if(currentProtocol === "icmp") {
            dojo.query("#aclIcmpTypeDiv").toggleClass("no_left_magin", true); 
            dojo.query("#aclProtocolNumberDiv").toggleClass("hide_text", true);    
            dojo.query("#aclStartPortDiv").toggleClass("hide_text", true);    
            dojo.query("#aclEndPortDiv").toggleClass("hide_text", true);   
            
            dojo.query("#aclIcmpTypeDiv").removeClass("hide_text", true);    
            dojo.query("#aclIcmpCodeDiv").removeClass("hide_text", true);    
            dojo.query("#aclTrafficTypeDiv").removeClass("hide_text", true);
            
            dijit.byId("aclProtocolNumber").set("required", false);
            dijit.byId("aclStartport").set("required", false);
            dijit.byId("aclEndport").set("required", false);
            dijit.byId("aclIcmpType").set("required", true);
            dijit.byId("aclIcmpCode").set("required", true);
        } else if(currentProtocol === "all") {
            dojo.query("#aclProtocolNumberDiv").toggleClass("hide_text", true);    
            dojo.query("#aclStartPortDiv").toggleClass("hide_text", true);    
            dojo.query("#aclEndPortDiv").toggleClass("hide_text", true);   
            dojo.query("#aclTrafficTypeDiv").toggleClass("no_left_magin", true); 
            dojo.query("#aclIcmpTypeDiv").toggleClass("hide_text", true);    
            dojo.query("#aclIcmpCodeDiv").toggleClass("hide_text", true);    
            dojo.query("#aclTrafficTypeDiv").removeClass("hide_text", true); 
            
            dijit.byId("aclProtocolNumber").set("required", false);
            dijit.byId("aclStartport").set("required", false);
            dijit.byId("aclEndport").set("required", false);
            dijit.byId("aclIcmpType").set("required", false);
            dijit.byId("aclIcmpCode").set("required", false);
        } else if(currentProtocol === "protocolnumber") {
            dojo.query("#aclTrafficTypeDiv").removeClass("no_left_magin", true);
            dojo.query("#aclIcmpTypeDiv").removeClass("no_left_magin", true); 
            
            dojo.query("#aclProtocolNumberDiv").removeClass("hide_text", true);    
            dojo.query("#aclStartPortDiv").removeClass("hide_text", true);    
            dojo.query("#aclEndPortDiv").removeClass("hide_text", true);   
            
            dojo.query("#aclIcmpTypeDiv").toggleClass("hide_text", true);    
            dojo.query("#aclIcmpCodeDiv").toggleClass("hide_text", true);    
            dojo.query("#aclTrafficTypeDiv").removeClass("hide_text", true);
            
            dijit.byId("aclProtocolNumber").set("required", true);
            dijit.byId("aclStartport").set("required", true);
            dijit.byId("aclEndport").set("required", true);
            dijit.byId("aclIcmpType").set("required", true);
            dijit.byId("aclIcmpCode").set("required", true);
            
            dijit.byId("aclIcmpType").setValue("-1");
            dijit.byId("aclIcmpCode").setValue("-1");
        }
        
    },
    onProtocolChange : function (currentProtocol) {
        
    }
}
var ListNetworkACL = {
    populateValues : function () {
        if(dijit.byId("networkAclGrid")) {                                    
            dijit.byId("networkAclGrid").destroyRecursive();                    
        }
        dojo.byId("networkAclListGrid").innerHTML = "<img src='images/vmload.gif' alt='"+translator.common.loader.imageLoadError+"' height='36' width='100'/> </br> <p>"+translator.common.loader.loadingPleaseWait+"</p>";
        var vpcGridData = {
            items: []
        }; 
        var networkACLList = new ItemFileWriteStore({data: vpcGridData}); 
        var aclLayout = [
            [               
                {'field': 'id', 'hidden': 'true'},
                {'name': translator.common.aclName, 'field': 'aclName', 'width': '200px', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                   var html = ""
                    if(data.vpcName) {
                        html= "<a href='#/user/vpcSecurity/view/" +data.aclId+ "'>"+data.name+"</a>"
                        return html;
                    } 
                        html= data.name;
                        return html;
                    }
                }
                ,    
                {'name': translator.common.zone, 'field': 'zone', 'width': '150px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.vpcName, 'field': 'vpcName', 'width': '100px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.description, 'field': 'description', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},                
//                {'name': translator.common.tiers, 'field': 'tiers','width': '100px', 'datatype':'string',  'autoComplete': 'true'},                
                {'name': translator.common.action, 'field': 'action','width': '100%', 'datatype':'string',  'autoComplete': 'true', 'formatter' : function (data) {
                        if(data.vpcName) {
                            var html = "<a onclick='ListNetworkACL.showDeleteAcl(\"" + data.aclId + "\")' title="+translator.common.deleteData+" class='span2'><img  src='images/vm_delete_icon.png'></a>"
                            return html;
                        }
                       
                    }
                },
                
             ]
         ];
        var aclRestStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl"
        });        
        var currentVPC = ""
        if(dojo.byId("selectedANVPCID").value === "All" || dojo.byId("selectedANVPCID").value === "option" || dojo.byId("selectedANVPCID").value === "" || dojo.byId("selectedANVPCID").value === "undefined") {
            currentVPC = ""
        } else {
            currentVPC = dojo.byId("selectedANVPCID").value;
        }            
        
        var vpcRestStore = new JsonRest({
            target: core.getContextPath()+"/api/vpc"
        });
        
        vpcRestStore.query().then(function (data) {
           if(data.length === 0) {
               dojo.query("#aclListActionButtonCollection").toggleClass("hide_text", true); 
           } else {
               dojo.query("#aclListActionButtonCollection").removeClass("hide_text", true); 
           }
        });
        aclRestStore.query({vpcId: currentVPC}).then(function(data) {
            if(data.length === 0 || data === undefined || data === "undefined" || data === "" || data === " ") {
                 dojo.byId("noNetworkAclMsgBox").style.display = "block";
                 dojo.byId("networkAclListGrid").innerHTML = "";
             } else {                 
                 dojo.forEach(data, function(aclData) {
                     networkACLList.newItem({
                         id:aclData.referenceId,
                         zone: aclData.zone,
                         vpcName:aclData.vpc,
                         description: aclData.description, 
                         aclName: {vpcName:aclData.vpc, name:aclData.name, aclId: aclData.referenceId},
//                         tiers: aclData.tiers,                                          
                         action : {vpcName:aclData.vpc, aclId: aclData.referenceId}
                     });
                 });
                 dojo.byId("networkAclListGrid").innerHTML = "";
                 var vpcGrid = new EnhancedGrid({
                     id: 'networkAclGrid',
                    "class" : "span12",
                    store: networkACLList,
                    structure: aclLayout,
                    autoHeight: true,
                    plugins: core.getGridConfig().plugins
                });
                vpcGrid.placeAt("networkAclListGrid");
                vpcGrid.startup(); 
                dojo.byId("noNetworkAclMsgBox").style.display = "none";
            }             
        });         
    },
    'showDeleteAcl' : function(currentId) {
        dojo.byId("currentAclId").value = currentId;
        dijit.byId("networkAclDeleteDialog").show();
        
    },
    'deleteAclClose' : function() {
        
        dijit.byId("networkAclDeleteDialog").hide();
        
    },
    'deleteAcl': function() {
        var currentId = dojo.byId("currentAclId").value;
        var networkRestStore = new JsonRest({
            target: core.getContextPath() + "/api/networkAcl/delete"
        });

        dijit.byId("networkAclLoader").show();
        dijit.byId("networkAclDeleteDialog").hide();

        networkRestStore.add({aclId:currentId}).then(function(data) {
            dojo.forEach(data, function(resultData) {
                if (data && resultData.result === "OK") {
                     var deleteNWAclJobStat = setInterval(function(){ListNetworkACL.deleteJobVpc(resultData.jobId, deleteNWAclJobStat);},2000);  
                } else {
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkAclLoader").hide();
                }
            });
        });
    },
    'deleteJobVpc' : function(jobId, deleteNWAclJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job/"
        });         
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.networkAclDeleted, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("networkAclLoader").hide();
                    ListNetworkACL.populateValues();
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("networkAclLoader").hide();
                } else {
                    clearInterval(deleteNWAclJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkAclLoader").hide(); 
                } 
            });
        });
    }
}


var AddNetworkAcl = {
    loadACLInfo : function () {                
        var zoneOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectZone}]
        };
        var zoneList = new ItemFileWriteStore({data: zoneOption});
        
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: [{id:"option", name: translator.common.selectVPC}]
        };
        
        var vpcList = new ItemFileWriteStore({data: vpcOption});        
        dojo.query("#networkAclZoneList").toggleClass("hide_text", true);
        dojo.query("#generalNetworkACLZoneLoader").removeClass("hide_text", true);
        
        dojo.query("#networkAclVpcList").toggleClass("hide_text", true);
        dojo.query("#generalNetworkACLVPCLoader").removeClass("hide_text", true);
        
        var currentZoneID = dojo.byId("selectedANZoneID").value;   
        var currentVPC = dojo.byId("selectedANVPCID").value;
        var zoneRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/zone/"
        });
        var vpcRestResource = new JsonRest({            
            target: core.getContextPath()+"/api/vpc"
        });   
        
        if(currentZoneID === "All" || currentZoneID === " " || currentZoneID === "undefind" || currentZoneID === "") {                                              
            if(currentVPC === "" || currentVPC === "option" || currentVPC === "undefined") {
                zoneRestResource.query().then(function (data) {
                    dojo.forEach(data, function (el) {
                        if(el.networkType === "Advanced") {
                            zoneList.newItem({id: el.referenceZoneId, name: el.aliasName});
                        }                    
                    });
                    dijit.byId("networkAclZone").set("store",zoneList);
                    dijit.byId("networkAclZone").set("value","option");
                    
                    dijit.byId("networkAclZone").onChange = function () {
                        AddNetworkAcl.loadVpcList(dijit.byId("networkAclZone"));
                    };   
                    dojo.query("#networkAclZoneList").removeClass("hide_text", true);
                    dojo.query("#generalNetworkACLZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#networkAclVpcList").removeClass("hide_text", true);
                    dojo.query("#generalNetworkACLVPCLoader").toggleClass("hide_text", true);
                });
            } else {                    
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone});
                        dijit.byId("networkAclZone").onChange = function () {};
                        dijit.byId("networkAclZone").set("store", zoneList);
                        dijit.byId("networkAclZone").set("value", el.zoneReferenceId);     
                        dojo.query("#networkAclZoneList").removeClass("hide_text", true);
                        dojo.query("#generalNetworkACLZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;                                                                                                                                     
                    });
                dijit.byId("networkAclVpc").set("store", singleVPCList);
                dijit.byId("networkAclVpc").set("value", firstVPC);   
                
                dojo.query("#networkAclVpcList").removeClass("hide_text", true);
                dojo.query("#generalNetworkACLVPCLoader").toggleClass("hide_text", true);                
            });                               
        }                                                
        } else {                                                
            if(currentVPC === "option" || currentVPC === "" || currentVPC === "undefined") {
                var singleZoneOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                var singleZoneList = new ItemFileWriteStore({data: singleZoneOption});
                zoneRestResource.get(currentZoneID).then(function (data) {
                    if(data.networkType === "Advanced") {
                        singleZoneList.newItem({id: data.referenceZoneId, name: data.aliasName});
                    } 
                    dijit.byId("networkAclZone").set("store", singleZoneList);
                    dijit.byId("networkAclZone").set("value", data.referenceZoneId);                    
                    dijit.byId("networkAclZone").onChange = function () {
                        AddNetworkAcl.loadVpcList(dijit.byId("networkAclZone"));
                    }; 
                    dojo.query("#networkAclZoneList").removeClass("hide_text", true);
                    dojo.query("#generalNetworkACLZoneLoader").toggleClass("hide_text", true);
                    
                    dojo.query("#networkAclVpcList").removeClass("hide_text", true);
                    dojo.query("#generalNetworkACLVPCLoader").toggleClass("hide_text", true);                    
                    
                });
            } else {      
                var singleOption = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };                

                var singleVPCList = new ItemFileWriteStore({data: singleOption});
                
                vpcRestResource.query({referenceId: currentVPC}).then(function (data) {
                    var firstVPC = "";                    
                    dojo.forEach(data, function (el) {
                        var zoneOption = {
                            identifier: 'id',
                            label: 'name',
                            items: []
                        };
                        var zoneList = new ItemFileWriteStore({data: zoneOption});
                        
                        zoneList.newItem({id: el.zoneReferenceId, name: el.zone});
                        dijit.byId("networkAclZone").onChange = function () {};
                        dijit.byId("networkAclZone").set("store", zoneList);
                        dijit.byId("networkAclZone").set("value", el.zoneReferenceId);     
                        dojo.query("#networkAclZoneList").removeClass("hide_text", true);
                        dojo.query("#generalNetworkACLZoneLoader").toggleClass("hide_text", true);
                        singleVPCList.newItem({id: el.referenceId, name: el.name});    
                        firstVPC = el.referenceId;                                                           
                                                                          
                });
                dijit.byId("networkAclVpc").set("store", singleVPCList);
                dijit.byId("networkAclVpc").set("value", firstVPC);   
                dojo.query("#networkAclVpcList").removeClass("hide_text", true);
                dojo.query("#generalNetworkACLVPCLoader").toggleClass("hide_text", true);
            });
        } 
    }
    },
    'loadVpcList': function(currentZone) {                
        var vpcOption = {
            identifier: 'id',
            label: 'name',
            items: []
        };
        dojo.query("#networkAclVpcList").toggleClass("hide_text", true);
        dojo.query("#generalNetworkACLVPCLoader").removeClass("hide_text", true);
        var vpcList = new ItemFileWriteStore({data: vpcOption});
        if(currentZone.value === "option") {
            vpcList.newItem({id: "option", name: translator.common.selectVPC});
            dijit.byId("networkAclVpc").set("store", vpcList);
            dijit.byId("networkAclVpc").set("value", "option");
            
            dojo.query("#networkAclVpcList").removeClass("hide_text", true);
            dojo.query("#generalNetworkACLVPCLoader").toggleClass("hide_text", true);
            
        } else {
            var vpcOfferRestStore = new JsonRest({
                target: core.getContextPath() + "/api/vpc"
            });
            
            vpcOfferRestStore.query({zoneReferenceId: currentZone.value}).then(function(data) {
                var firstVPC = ""
                dojo.forEach(data, function (el, index) {
                    vpcList.newItem({id: el.referenceId, name: el.name});
                    if(index === 0) {
                        firstVPC = el.referenceId;
                    }
                });                
                dijit.byId("networkAclVpc").set("store", vpcList);
                dijit.byId("networkAclVpc").set("value", firstVPC);    
                
                dojo.query("#networkAclVpcList").removeClass("hide_text", true);
                dojo.query("#generalNetworkACLVPCLoader").toggleClass("hide_text", true);
            });            
        }           
    },
  'init': function(currentId) {      
      if(dijit.byId("networkAclZone")) {                                    
          dijit.byId("networkAclZone").destroyRecursive();                    
      }
      if(dijit.byId("networkAclVpc")) {                                    
          dijit.byId("networkAclVpc").destroyRecursive();                    
      }        
      var option = {
          identifier: 'id',
          label: 'name',
          items: []
      };
        var storeList = new ItemFileWriteStore({data: option});                        

        var zoneWidget = new FilteringSelect({
            id: "networkAclZone",            
            store: storeList,   
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectZone,
            onChange: function() {
            }
        }).placeAt("networkAclZoneList");
        var vpcWidget = new FilteringSelect({
            id: "networkAclVpc",            
            store: storeList,  
            sortByLabel: false,
            autoHeight: true,
            placeHolder : translator.common.selectVPC            
        }).placeAt("networkAclVpcList");                
        AddNetworkAcl.loadACLInfo();      
    },    
     'cancel': function() {
         core.router.go("#/user/vpcSecurity/listNetworkAcl");
     },    
    'add': function() {

        var name = dijit.byId("networkAclName").value;
        var desc = dijit.byId("networkAclDescription").value;
        var zone = dijit.byId("networkAclZone").value;
        var vpc = dijit.byId("networkAclVpc").value;
       
        var pageNode = dojo.byId("createNetworkAclPage");
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
        if (firstNode) {
            firstNode.focus();
        } else {

            var networkRestStore = new JsonRest({
                target: core.getContextPath() + "/api/networkAcl"
            });
            
            dijit.byId("networkAclLoader").show();
            
            networkRestStore.add({name: name, desc: desc, vpcId: vpc}).then(function(data) {
                dojo.forEach(data, function(resultData) {
                    if (data && resultData.result === "OK") {
                         var networkAclCreateJobStat = setInterval(function(){AddNetworkAcl.createNetworkAclJob(resultData.jobId, networkAclCreateJobStat);},1000);  
                    } else {
                        registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                        registry.byId("userToaster").show();
                        dijit.byId("networkAclLoader").hide();
                    }
                });
            });
        }
    },
    createNetworkAclJob : function (jobId, networkAclCreateJobStat) {
        var jobStore = new JsonRest({
            target: core.getContextPath()+"/api/networkAcl/job/"
        });         
        jobStore.add({
            jobId : jobId
        }).then(function(data) {
            dojo.forEach(data, function(resultData) {                
                if(resultData.jobResult === "OK") {
                    clearInterval(networkAclCreateJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.networkAclCreated, "message");
                    registry.byId("userToaster").show();      
                    dijit.byId("networkAclLoader").hide();
                    core.router.go("#/user/vpcSecurity/listNetworkAcl");
                } else if(resultData.jobResult === "Pending") {
                } else  if(resultData.jobResult === "FAILED") {
                    clearInterval(networkAclCreateJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError,"error");
                    registry.byId("userToaster").show();       
                    dijit.byId("networkAclLoader").hide();
                } else {
                    clearInterval(networkAclCreateJobStat);
                    registry.byId("userToaster").setContent(translator.common.message.cloudStackError, "error");
                    registry.byId("userToaster").show();
                    dijit.byId("networkAclLoader").hide(); 
                } 
            });
        });
    }
};

//Window.VPCMenuInfo = VPCMenuInfo;
Window.ListNetworkACL = ListNetworkACL;
Window.ACLInfo = ACLInfo;


