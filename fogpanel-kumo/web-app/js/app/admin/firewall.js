'use strict';
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
    "dijit/Dialog"
    ],
    function(dojo, i18n, translator, dijit,  domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
    ItemFileWriteStore, DataGrid, EnhancedGrid, ContentPane, Source, MultiSelect, dom, win) {       
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
        window.DataGrid = DataGrid;
        window.EnhancedGrid = EnhancedGrid;
        window.Source = Source;
        window.MultiSelect = MultiSelect;
        window.dom = dom;
        window.win = win;
        
        controller ({
            name: "firewall",
            module: "admin",
            filePath: "/js/app/admin/firewall.js",
            layout: {
                name: "firewall",
                containerId: "content"
            },	
            scaffold: false
        },
        {
            "index": action(function() {      
                core.ui.loadTemplate("securityGroups", core.ui.getContentId()); 
                FirewalInfo.init();
                FirewalInfo.populateValues();                
            }),
            "add" : action(function() {
                if(dijit.byId("firewallAddForm")) {
                    dijit.byId("firewallAddForm").destroyRecursive();      
                }
                core.ui.loadTemplate("addFirewall", core.ui.getContentId()); 
            }),
            "delete" : action(function(id) {
                core.ui.loadTemplate("securityGroups", core.ui.getContentId()); 
                
                DeleteFirewall.init(id);
                DeleteFirewall.populateValues(id);
                
                FirewalInfo.init();
                FirewalInfo.populateValues(); 
                
                
            }),
            "edit" : action(function(id) {  
                if(dijit.byId("adminIngressSecurityGroupsForm")) {                                    
                    dijit.byId("adminIngressSecurityGroupsForm").destroyRecursive();                    
                }
                core.ui.loadTemplate("ingress", core.ui.getContentId());  
                
                if(id == "undefined" || id == undefined) {            
                    ViewFirewalInfo.loadPage(currentFirewallId);
                    IngressInfo.init(currentFirewallId);
                } else {                     
                    window.currentFirewallId = id; 
                    ViewFirewalInfo.loadPage(id);
                    IngressInfo.init(id);
                    IngressInfo.populateValues(id);
                }                  
            }),            
            "ingress" : action(function(id) {
                if(dijit.byId("adminIngressSecurityGroupsForm")) {                                    
                    dijit.byId("adminIngressSecurityGroupsForm").destroyRecursive();                    
                }
                core.ui.loadTemplate("ingress", core.ui.getContentId());                     
                ViewFirewalInfo.loadPage(id);                
                IngressInfo.init(id);
                IngressInfo.populateValues(id);  
            }),
            "egress" : action(function(id) {
                if(dijit.byId("adminEgressSecurityGroupsForm")) {                                    
                    dijit.byId("adminEgressSecurityGroupsForm").destroyRecursive();                    
                }
                core.ui.loadTemplate("egressRule", core.ui.getContentId());     
                ViewFirewalInfo.loadPage(id);                
                EgressInfo.init(id);
                EgressInfo.populateValues(id);                
            }),
            "deleteIngress" : action(function(id) {
               if(dijit.byId("adminIngressSecurityGroupsForm")) {                                    
                    dijit.byId("adminIngressSecurityGroupsForm").destroyRecursive();                    
                }
                core.ui.loadTemplate("ingress", core.ui.getContentId()); 
                ViewFirewalInfo.loadPage(currentFirewallId);
                IngressInfo.init(currentFirewallId);
                IngressInfo.populateValues(currentFirewallId);
                
                DeleteIngress.init(id);
                DeleteIngress.populateValues(id, currentFirewallId);
                
            }),
            "deleteEgress" : action(function(id) {
               if(dijit.byId("adminEgressSecurityGroupsForm")) {                                    
                    dijit.byId("adminEgressSecurityGroupsForm").destroyRecursive();                    
                }
                core.ui.loadTemplate("egressRule", core.ui.getContentId());              
                ViewFirewalInfo.loadPage(currentFirewallId);
                EgressInfo.init(currentFirewallId);
                EgressInfo.populateValues(currentFirewallId);                
                
                DeleteEgress.init(id);
                DeleteEgress.populateValues(id, currentFirewallId);
            })
        });
    });

var DeleteEgress = {
    _securityGroupsTempRuleRestStore : "",
    _currentId:"",
    init: function(currentId) {
        this._securityGroupsTempRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/ingress/"
        });       
    },
    populateValues : function(currentId, currentFirewallId) {        
    }
    
};
    
var DeleteIngress = {
    _securityGroupsTempRuleRestStore : "",
    _currentId:"",
    init: function(currentId) {
        this._securityGroupsTempRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/ingress/"
        });       
    },
    populateValues : function(currentId, currentFirewallId) {        
         this._securityGroupsTempRuleRestStore.remove(currentId).then(function(data) {
             if(data == "OK") {
                 registry.byId("appToaster").setContent(translator.common.message.deletedSuccess,"message");
                 registry.byId("appToaster").show();
                   core.router.go("#/admin/firewall/ingress/" + currentFirewallId);
//                 IngressInfo.init(currentFirewallId);
//                 IngressInfo.populateValues(currentFirewallId);
             } else {
                 registry.byId("appToaster").setContent(translator.common.message.cannotDelete,"error");
                 registry.byId("appToaster").show();
             }
         });
    }
    
};
var DeleteFirewall = {
    _securityGroupsTempRestStore : "",
    _currentId:"",
    init: function(currentId) {
        this._securityGroupsTempRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/"
        });       
    },
    populateValues : function(currentId) {       
         this._securityGroupsTempRestStore.remove(currentId).then(function(data) {
             if(data == "OK") {
                 registry.byId("appToaster").setContent(translator.common.message.deletedSuccess,"message");
                 registry.byId("appToaster").show();
                 core.router.go("#/admin/firewall");
             } else {
                 registry.byId("appToaster").setContent(translator.common.message.cannotDelete,"error");
                 registry.byId("appToaster").show();
             }
         });
    }
    
};


var ViewFirewalInfo = {
    _securityGroupsRestStore : "",
    _currentId:"",
    init : function(currentId) {
        this._currentId = currentId;
        this._securityGroupsTempRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/"
        });
    },
    populateValues : function(currentId) {
        this._securityGroupsTempRestStore.get(this._currentId).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("firewalName").innerHTML = resultData.securityGroupName;
                dojo.byId("firewalDescription").innerHTML = resultData.description;                
            });
        }); 
    },
    loadPage : function(currentId) {
        var domNode =  dojo.query(".nav-tabs li a"); 
         dojo.forEach(domNode, function(el) {
            if(el.id == "Inbound") {
                el.href = "#/admin/firewall/ingress/" + currentId ;
            } else if(el.id == "Outbound"){
                el.href = "#/admin/firewall/egress/" + currentId;
            }                                 
        });
    }
    
};
var EgressInfo = {
     _securityGroupsRestStore : "",
    _currentId:"",
    _securityTempRuleRestStore:"",
    init : function(currentId) {
        this._currentId = currentId;
        this._securityTempRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/ingress/"
        });
        
         this._securityGroupsTempRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/"
        });
    },
    populateValues : function(currentId) {
        if(dijit.byId("adminEgressDataGrid")) {                                    
            dijit.byId("adminEgressDataGrid").destroyRecursive();                    
        }
        
        this._securityGroupsTempRestStore.get(currentId).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("egressFirewall").innerHTML = resultData.securityGroupName;                         
            });
        });
        var data = {
            items: []
        }; 
        var dataList = new ItemFileWriteStore({data: data}); 
        var dataLayout = [[
                  {'name': 'ID', 'field': 'id', 'hidden': 'true'},
                  {'name': translator.common.firewall.protocol, 'field': 'protocol', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                  {'name': translator.common.firewall.startPortOrICMPType, 'field': 'startPort', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                  {'name': translator.common.firewall.endPortOrICMPCode, 'field': 'endPort', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                  {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                  {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) { 
                        var myButton = new dijit.form.Button({
                            label: translator.common.deleteData,     
                            "class": "cancelbtn",
                            onClick: function() {                            
                                EgressInfo.deleteEgressRule(data);
            
                            }
                        });                                        
                        return myButton;
                },'width': '100%'}
             ]
         ];
         
         this._securityGroupsTempRestStore.query({securityGroupTemplateId:currentId, securityGroupType:"EGRESS"}).then(function(data) {
                 dojo.forEach(data, function(ruleData) {
                 dataList.newItem ({
                     id:ruleData.id,
                     protocol:ruleData.securityGroupPort.name,
                     startPort: ruleData.startPort, 
                     endPort: ruleData.endPort,
                     cidr : ruleData.cidr,
                     action : ruleData.id                     
                 });
             });
        });
            var dataGrid = new EnhancedGrid({
                id: 'adminEgressDataGrid',
                "class" : "span12",
                store: dataList,
                structure: dataLayout,
                autoHeight:  true,
                plugins: core.getGridConfig().plugins,
                noDataMessage: translator.common.firewall.noOutboundRule
            });
            dataGrid.placeAt("firewalEgressList");
            dataGrid.startup();  
    },
    deleteEgressRule : function(currentEgressRuleId) {
        var securityGroupsTempRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/ingress/"
        }); 
        var egressGrid = dijit.byId("adminEgressDataGrid");
        securityGroupsTempRuleRestStore.remove(currentEgressRuleId).then(function(data) {
             if(data == "OK") {
                 registry.byId("appToaster").setContent(translator.common.message.deletedSuccess,"message");
                 registry.byId("appToaster").show();
                  var items = egressGrid.selection.getSelected();
                        dojo.forEach(items, function(selectedItem) {
                            egressGrid.store.deleteItem(selectedItem);
                 });
//                 core.router.go("#/admin/firewall/egress/" + currentFirewallId);
             } else {
                 registry.byId("appToaster").setContent(translator.common.message.cannotDelete,"error");
                 registry.byId("appToaster").show();
             }
         });
          
    },
    addEgress: function() {        
        var pageNode = dojo.byId("adminSecurityGroupsEgressRule");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;        
        var status = true;
         var protocol = dijit.byId('adminSecurityEgressProtocol').value;
         var startPort = "";
         var endPort = "";
         var cidr = dijit.byId("adminSecurityEgressCidr").getValue();         
         if(protocol == "ICMP") {            
             startPort = dijit.byId("egressIcmpTypeWidget").value;
             endPort = dijit.byId("egressIcmpCodeWidget").value;                  
             dijit.byId("adminSecurityEgressStartPort").set("required", false);
             dijit.byId("adminSecurityEgressEndPort").getValue("required", false);
         } else {        
             startPort = dijit.byId("adminSecurityEgressStartPort").getValue();
             endPort = dijit.byId("adminSecurityEgressEndPort").getValue();             
             dijit.byId("adminSecurityEgressStartPort").set("required", true);
             dijit.byId("adminSecurityEgressEndPort").getValue("required", true);
         }
         
         dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {
                el.focus();
                if (!firstNode) {
                    firstNode = el;
                }
                status = false;
            }
        });
        if (firstNode) {
            firstNode.focus();
        }              
        
         if(status == true) {
             dijit.byId('firewallEgressButton').set('style', {'display': 'none'});
             dojo.byId('firewallEgressLoader').style.display = "block";

             dijit.byId("adminSecurityEgressProtocol").set("disabled", true);
             dijit.byId("adminSecurityEgressStartPort").set("disabled", true);
             dijit.byId("adminSecurityEgressEndPort").set("disabled", true);
             dijit.byId("adminSecurityEgressCidr").set("disabled", true);
             dijit.byId("egressIcmpTypeWidget").set("disabled", true);
             dijit.byId("egressIcmpCodeWidget").set("disabled", true);
         
         this._securityTempRuleRestStore.add({
             protocol: protocol, 
             startPort: Number(startPort), 
             endPort: Number(endPort),
             cidr: cidr,
             securityGroupId: this._currentId,
             ruleType: "EGRESS"
        }).then(function(data) {
            dijit.byId("adminSecurityEgressProtocol").set("disabled", false);
             dijit.byId("adminSecurityEgressStartPort").set("disabled", false);
             dijit.byId("adminSecurityEgressEndPort").set("disabled", false);
             dijit.byId("adminSecurityEgressCidr").set("disabled", false);
             dijit.byId("egressIcmpTypeWidget").set("disabled", false);
             dijit.byId("egressIcmpCodeWidget").set("disabled", false);
            dojo.forEach(data, function(resultData) {
                dijit.byId('firewallEgressButton').set('style', {'display': 'block'});
                dojo.byId('firewallEgressLoader').style.display = "none";
                if(resultData.result == "OK") {
                 registry.byId("appToaster").setContent(translator.common.firewall.outboundAddSuccess,"message");
                 registry.byId("appToaster").show();                 
                 dijit.byId("adminEgressDataGrid").store.newItem({
                     id:resultData.ruleId,
                     protocol:resultData.securityGroupPort.name,
                     startPort: resultData.startPort, 
                     endPort: resultData.endPort,
                     cidr : resultData.cidr,
                     action : resultData.ruleId  
                 });
                 dijit.byId("adminSecurityEgressStartPort").reset();    
                 dijit.byId("adminSecurityEgressEndPort").reset();    
                 dijit.byId("adminSecurityEgressCidr").reset();    
             } else {
                 var errorMsg;
                 if(resultData.localizedMessage == null || resultData.localizedMessage == 'null' || resultData.localizedMessage == "" || resultData.localizedMessage == " ") {
                     errorMsg = translator.common.firewall.invalidCIDR;
                 } else {
                     errorMsg = resultData.localizedMessage;
                 }
                 registry.byId("appToaster").setContent(errorMsg, "error");
                 registry.byId("appToaster").show();
             }                    
            });
        });
         }
         
    },
    showIcmpCode : function(icmpType) {
        var icmpOptions = {}
        if(icmpType.value == "-1" || icmpType.value == "0" || icmpType.value == "4" || icmpType.value == "6" ||
            icmpType.value == "8" || icmpType.value == "9" || icmpType.value == "10" || icmpType.value == "13" || 
            icmpType.value == "14" || icmpType.value == "15" || icmpType.value == "16" || icmpType.value == "17" || 
            icmpType.value == "18") {
            dojo.byId("egressendportContainer").style.display = "none";            
            icmpOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            }            
            if(icmpType.value == "-1") {
                var AllIcmp = new ItemFileWriteStore({data: icmpOptions});
                AllIcmp.newItem({id: "0", name: translator.common.empty})
                dijit.byId("egressIcmpCodeWidget").set("store", AllIcmp);
                dijit.byId("egressIcmpCodeWidget").set("value", "-1");
            } else {
                var noIcmp = new ItemFileWriteStore({data: icmpOptions});
                noIcmp.newItem({id: "0", name: translator.common.message.noCode});
                dijit.byId("egressIcmpCodeWidget").set("store", noIcmp);
                dijit.byId("egressIcmpCodeWidget").set("value", "0");
            }                                               
        } else {
            dojo.byId("egressendportContainer").style.display = "block";
            dojo.byId("egressEndPortList").style.display = "none";
            dojo.byId("egressIcmpCodeList").style.display = "block";               
            if(icmpType.value == "3") {              
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
                        {id: "0", name : translator.common.firewall.networkUnreachable},
                        {id: "1", name : translator.common.firewall.hostUnreachable},
                        {id: "2", name : translator.common.firewall.protocolUnreachable},
                        {id: "3", name : translator.common.firewall.portUnreachable},
                        {id: "4", name : translator.common.firewall.fragmentationNeeded},
                        {id: "5", name : translator.common.firewall.sourceRouteFailed},
                        {id: "6", name : translator.common.firewall.designationNetworkUnknown},
                        {id: "7", name : translator.common.firewall.designationHostUnknown},
                        {id: "8", name : translator.common.firewall.sourceHostIsolated},
                        {id: "9", name : translator.common.firewall.networkAdministrativelyProhibited},
                        {id: "10", name : translator.common.firewall.hostAdministrativelyProhibited},
                        {id: "11", name : translator.common.firewall.networkUnreachableForTos},
                        {id: "12", name : translator.common.firewall.hostUnreachableForTos} 
                    ]
                };                
                var cidrCodefor3List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("egressIcmpCodeWidget").set("store", cidrCodefor3List);
                dijit.byId("egressIcmpCodeWidget").set("value","0");                
            } else if(icmpType.value == "5") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                        {id: "0", name : translator.common.firewall.datagramForNetwork},
                        {id: "1", name : translator.common.firewall.datagramForHost},
                        {id: "2", name : translator.common.firewall.datagramForTosAndNetwork},
                        {id: "3", name : translator.common.firewall.datagramForTosAndHost}                      
                    ]
                };                
                var cidrCodefor5List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("egressIcmpCodeWidget").set("store", cidrCodefor5List);
                dijit.byId("egressIcmpCodeWidget").set("value","0");    
            } else if(icmpType.value == "11") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                        {id: "0", name : translator.common.firewall.ttlExpiredTransit},
                        {id: "1", name : translator.common.firewall.fragmentationReassenblyExceeded}                                
                    ]
                };                
                var cidrCodefor11List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("egressIcmpCodeWidget").set("store", cidrCodefor11List);
                dijit.byId("egressIcmpCodeWidget").set("value","0");    
            } else if(icmpType.value == "12") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
                        {id: "0", name : translator.common.firewall.pointerIndicatesError},
                        {id: "1", name : translator.common.firewall.missingRequiredOption},
                        {id: "2", name : translator.common.firewall.badLength}                                                         
                    ]
                };                
                var cidrCodefor12List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("egressIcmpCodeWidget").set("store", cidrCodefor12List);
                dijit.byId("egressIcmpCodeWidget").set("value","0");    
            }
        }
    },
    changeEgressLabel : function(selectWidget) { 
        var span = "<span class='require'>*</span>" ;
        if(selectWidget.value == "ICMP") {
            dojo.byId("adminEgressStartPort").innerHTML = translator.common.firewall.icmpType + span;
            dojo.byId("adminEgressEndPort").innerHTML = translator.common.firewall.icmpCode  + span;                    
            dojo.byId("adminSecurityEgressStartPortList").style.display = "none";
            dojo.byId("egressIcmpTypeList").style.display = "block";
            dojo.byId("egressendportContainer").style.display = "none";            
            dijit.byId("adminSecurityEgressEndPort").setValue("0");
            dijit.byId("adminSecurityEgressStartPort").setValue("-1");   
            
            var allOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            }
            dijit.byId("egressIcmpCodeWidget").set("value", "-1");
            dijit.byId("egressIcmpTypeWidget").set("value","-1"); 
        } else {
            dojo.byId("adminEgressStartPort").innerHTML = translator.common.firewall.startPort + span;
            dojo.byId("adminEgressEndPort").innerHTML = translator.common.firewall.endPort + span;            
            dojo.byId("adminSecurityEgressStartPortList").style.display = "block";
            dojo.byId("egressIcmpTypeList").style.display = "none";            
            dojo.byId("egressendportContainer").style.display = "block";
            dojo.byId("egressEndPortList").style.display = "block";
            dojo.byId("egressIcmpCodeList").style.display = "none";
            dijit.byId("adminSecurityEgressEndPort").setValue("");
            dijit.byId("adminSecurityEgressStartPort").setValue("");                           
        }
        
        
    }
};
var IngressInfo = {
     _securityGroupsRestStore : "",
    _currentId:"",
    _securityTempRuleRestStore:"",
    init : function(currentId) {
        this._currentId = currentId;
        this._securityTempRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/ingress/"
        });
        
         this._securityGroupsTempRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/"
        });
    },
    populateValues : function(currentId) {
        this._currentId = currentId;
        if(dijit.byId("adminIngressDataGrid")) {                                    
            dijit.byId("adminIngressDataGrid").destroyRecursive();                    
        }
        var data = {
                    items: []
        }; 
        
        this._securityGroupsTempRestStore.get(currentId).then(function(data) {
            dojo.forEach(data, function(resultData) {
                dojo.byId("ingressFirewall").innerHTML = resultData.securityGroupName;                         
            });
        });
        var dataList = new ItemFileWriteStore({data: data}); 
        var dataLayout = [
            [                       
                {'name': 'ID', 'field': 'id', 'hidden': 'true'},
                {'name': translator.common.firewall.protocol, 'field': 'protocol', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.firewall.startPortOrICMPType, 'field': 'startPort', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.firewall.endPortOrICMPCode, 'field': 'endPort', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.firewall.cidr, 'field': 'cidr', 'width': '200px', 'datatype':'string',  'autoComplete': 'true'},
                {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) { 
                        var myButton = new dijit.form.Button({
                            label: translator.common.deleteData,     
                            "class": "cancelbtn",
                            onClick: function() {                            
                                IngressInfo.deleteIngressRule(data);            
                            }
                        });                 
                        return myButton;
                    },'width': '100%', 'datatype':'string',  'autoComplete': 'true'}
            ]
        ];
         
         this._securityGroupsTempRestStore.query({securityGroupTemplateId:currentId, securityGroupType:"INGRESS"}).then(function(data) {
             dojo.forEach(data, function(ruleData) {
                 dataList.newItem ({
                     id:ruleData.id,
                     protocol:ruleData.securityGroupPort.name,
                     startPort: ruleData.startPort, 
                     endPort: ruleData.endPort,
                     cidr : ruleData.cidr,
                     action : ruleData.id                     
                 });
             });
         });
          var dataGrid = new EnhancedGrid({
            id: 'adminIngressDataGrid',
            "class" : "span12",
            store: dataList,
            structure: dataLayout,
            autoHeight:  true,
            noDataMessage: translator.common.firewall.noInboundRule,
            plugins: core.getGridConfig().plugins
        });
        dataGrid.placeAt("firewalIngressList");
        dataGrid.startup();  
    },
    deleteIngressRule : function(currentEgressRuleId) {
         var securityGroupsTempRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/ingress/"
        }); 
        var ingressGrid = dijit.byId("adminIngressDataGrid");
        securityGroupsTempRuleRestStore.remove(currentEgressRuleId).then(function(data) {
             if(data == "OK") {
                 registry.byId("appToaster").setContent(translator.common.message.deletedSuccess,"message");
                 registry.byId("appToaster").show();
                  var items = ingressGrid.selection.getSelected();
                        dojo.forEach(items, function(selectedItem) {
                            ingressGrid.store.deleteItem(selectedItem);
                 });
//                 core.router.go("#/admin/firewall/egress/" + currentFirewallId);
             } else {
                 registry.byId("appToaster").setContent(translator.common.message.cannotDelete,"error");
                 registry.byId("appToaster").show();
             }
         });
    },
    showIcmpCode : function(icmpType) {    
        
        var icmpOptions = {}
        if(icmpType.value == "-1" || icmpType.value == "0" || icmpType.value == "4" || icmpType.value == "6" ||
            icmpType.value == "8" || icmpType.value == "9" || icmpType.value == "10" || icmpType.value == "13" || 
            icmpType.value == "14" || icmpType.value == "15" || icmpType.value == "16" || icmpType.value == "17" || 
            icmpType.value == "18") {
            dojo.byId("endportContainer").style.display = "none";            
            icmpOptions = {
                identifier: 'id',
                label: 'name',
                items: []
            }            
            if(icmpType.value == "-1") {
                var AllIcmp = new ItemFileWriteStore({data: icmpOptions});
                AllIcmp.newItem({id: "0", name: translator.common.all})
                dijit.byId("icmpCodeWidget").set("store", AllIcmp);
                dijit.byId("icmpCodeWidget").set("value", "-1");
                dijit.byId("icmpTypeWidget").set("value", "-1");
                
            } else {
                var noIcmp = new ItemFileWriteStore({data: icmpOptions});
                noIcmp.newItem({id: "0", name:translator.common.message.noCode});
                dijit.byId("icmpCodeWidget").set("store", noIcmp);
                dijit.byId("icmpCodeWidget").set("value", "0");
            }                                               
        } else {
            dojo.byId("endportContainer").style.display = "block";
            dojo.byId("endPortList").style.display = "none";
            dojo.byId("icmpCodeList").style.display = "block";               
            if(icmpType.value == "3") {              
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                        {id: "0", name : translator.common.firewall.networkUnreachable},
                        {id: "1", name : translator.common.firewall.hostUnreachable},
                        {id: "2", name : translator.common.firewall.protocolUnreachable},
                        {id: "3", name : translator.common.firewall.portUnreachable},
                        {id: "4", name : translator.common.firewall.fragmentationNeeded},
                        {id: "5", name : translator.common.firewall.sourceRouteFailed},
                        {id: "6", name : translator.common.firewall.designationNetworkUnknown},
                        {id: "7", name : translator.common.firewall.designationHostUnknown},
                        {id: "8", name : translator.common.firewall.sourceHostIsolated},
                        {id: "9", name : translator.common.firewall.networkAdministrativelyProhibited},
                        {id: "10", name : translator.common.firewall.hostAdministrativelyProhibited},
                        {id: "11", name : translator.common.firewall.networkUnreachableForTos},
                        {id: "12", name : translator.common.firewall.hostUnreachableForTos} 
                    ]
                };                
                var cidrCodefor3List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("icmpCodeWidget").set("store", cidrCodefor3List);
                dijit.byId("icmpCodeWidget").set("value","0");                
            } else if(icmpType.value == "5") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                        {id: "0", name : translator.common.firewall.datagramForNetwork},
                        {id: "1", name : translator.common.firewall.datagramForHost},
                        {id: "2", name : translator.common.firewall.datagramForTosAndNetwork},
                        {id: "3", name : translator.common.firewall.datagramForTosAndHost}                  
                    ]
                };                
                var cidrCodefor5List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("icmpCodeWidget").set("store", cidrCodefor5List);
                dijit.byId("icmpCodeWidget").set("value","0");    
            } else if(icmpType.value == "11") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                       {id: "0", name : translator.common.firewall.ttlExpiredTransit},
                        {id: "1", name : translator.common.firewall.fragmentationReassenblyExceeded}                                   
                    ]
                };                
                var cidrCodefor11List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("icmpCodeWidget").set("store", cidrCodefor11List);
                dijit.byId("icmpCodeWidget").set("value","0");    
            } else if(icmpType.value == "12") {
                icmpOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: [
//                        {id: "-1", name : "All"},
                       {id: "0", name : translator.common.firewall.pointerIndicatesError},
                        {id: "1", name : translator.common.firewall.missingRequiredOption},
                        {id: "2", name : translator.common.firewall.badLength}                                                        
                    ]
                };                
                var cidrCodefor12List = new ItemFileWriteStore({data: icmpOptions});
                dijit.byId("icmpCodeWidget").set("store", cidrCodefor12List);
                dijit.byId("icmpCodeWidget").set("value","0");    
            }
        }
    },
    changeIngressLabel : function(selectWidget) {      
        var allOptions = {
            identifier: 'id',
            label: 'name',
            items: []
        }
        var AllIcmpCode = new ItemFileWriteStore({data: allOptions});
        AllIcmpCode.newItem({id: "-1", name: translator.common.message.noCode})
       
        dijit.byId("icmpCodeWidget").set("store", AllIcmpCode);
        
        dijit.byId("icmpCodeWidget").set("value", "-1");
        
        dijit.byId("icmpTypeWidget").set("value","-1"); 
        var span = "<span class='require'>*</span>" 
        if(selectWidget.value == "ICMP") {
            dojo.byId("adminIngressStartPort").innerHTML = translator.common.firewall.icmpType + span;
            dojo.byId("adminIngressEndPort").innerHTML = translator.common.firewall.icmpCode + span;                        
            dojo.byId("adminSecurityIngressStartPortList").style.display = "none";
            dojo.byId("icmpTypeList").style.display = "block";
            dojo.byId("endportContainer").style.display = "none";            
            dijit.byId("adminSecurityIngressEndPort").setValue("-1");
            dijit.byId("adminSecurityIngressStartPort").setValue("-1");                  
        } else {            
            dojo.byId("endportContainer").style.display = "block";
            dojo.byId("endPortList").style.display = "block";
            dojo.byId("adminIngressStartPort").innerHTML = translator.common.firewall.startPort + span;
            dojo.byId("adminIngressEndPort").innerHTML = translator.common.firewall.endPort + span;            
            dojo.byId("adminSecurityIngressStartPortList").style.display = "block";
            dojo.byId("icmpTypeList").style.display = "none";                        
            dojo.byId("icmpCodeList").style.display = "none";
            dijit.byId("adminSecurityIngressEndPort").setValue("");
            dijit.byId("adminSecurityIngressStartPort").setValue("");                           
        }
    },
    addIngress: function() {
        
        var pageNode = dojo.byId("adminSecurityGroupsIngressRule");
        var widgets = dijit.registry.findWidgets(pageNode);
        var firstNode = null;
        var status = true;        
        var protocol = dijit.byId('adminSecurityIngressProtocol').value;
        var startPort = "";
        var endPort = "";
        var cidr = dijit.byId("adminSecurityIngressCidr").getValue();
         
         if(protocol == "ICMP") {            
             startPort = dijit.byId("icmpTypeWidget").value;
             endPort = dijit.byId("icmpCodeWidget").value; 
             
             dijit.byId("adminSecurityIngressStartPort").set("required", false);
             dijit.byId("adminSecurityIngressEndPort").set("required", false);
         } else {        
             startPort = dijit.byId("adminSecurityIngressStartPort").getValue();
             endPort = dijit.byId("adminSecurityIngressEndPort").getValue();
             
             dijit.byId("adminSecurityIngressStartPort").set("required", true);
             dijit.byId("adminSecurityIngressEndPort").set("required", true);
         }
         
         dojo.forEach(widgets, function(el) {
            if (el.validate && !el.validate()) {                                
                el.focus();                
                if (!firstNode) {
                    firstNode = el;
                }
                status = false;
            }
        });
        if (firstNode) {
            firstNode.focus();
        }      
         if(status == true) {
             dijit.byId('firewallIngressButton').set('style', {'display': 'none'});
             dojo.byId('firewallIngressLoader').style.display = "block";
             
             dijit.byId("adminSecurityIngressProtocol").set("disabled", true);
             dijit.byId("adminSecurityIngressStartPort").set("disabled", true);
             dijit.byId("adminSecurityIngressEndPort").set("disabled", true);
             dijit.byId("adminSecurityIngressCidr").set("disabled", true);
             dijit.byId("icmpTypeWidget").set("disabled", true);
             dijit.byId("icmpCodeWidget").set("disabled", true);
             this._securityTempRuleRestStore.add({
                 protocol: protocol, 
                startPort: Number(startPort), 
                endPort: Number(endPort),
                cidr: cidr,
                securityGroupId: this._currentId,
                ruleType: "INGRESS"
            }).then(function(data) {
                dijit.byId("adminSecurityIngressProtocol").set("disabled", false);
                dijit.byId("adminSecurityIngressStartPort").set("disabled", false);
                dijit.byId("adminSecurityIngressEndPort").set("disabled", false);
                dijit.byId("adminSecurityIngressCidr").set("disabled", false);
                dijit.byId("icmpTypeWidget").set("disabled", false);
                dijit.byId("icmpCodeWidget").set("disabled", false);
                dojo.forEach(data, function(resultData) {
                    dijit.byId('firewallIngressButton').set('style', {'display': 'block'});
                    dojo.byId('firewallIngressLoader').style.display = "none";
                    if(resultData.result == "OK") {
                        registry.byId("appToaster").setContent(translator.common.firewall.inboundAddSuccess,"message");
                        registry.byId("appToaster").show();
                        dijit.byId("adminIngressDataGrid").store.newItem({
                            id:resultData.ruleId,
                            protocol:resultData.securityGroupPort.name,
                            startPort: resultData.startPort, 
                            endPort: resultData.endPort,
                            cidr : resultData.cidr,
                            action : resultData.ruleId
                        });                     
                        dijit.byId("adminSecurityIngressStartPort").reset();     
                        dijit.byId("adminSecurityIngressEndPort").reset();     
                        dijit.byId("adminSecurityIngressCidr").reset();                         
                    } else {
                        var errorMsg;
                        if(resultData.localizedMessage == null || resultData.localizedMessage == 'null' || resultData.localizedMessage == "" || resultData.localizedMessage == " ") {
                            errorMsg = translator.common.firewall.invalidCIDR
                        } else {
                            errorMsg = resultData.localizedMessage;
                        }
                        registry.byId("appToaster").setContent(errorMsg, "error");
                        registry.byId("appToaster").show();                               
                    }                                          
                });                               
            });
        }        
    }
};


var FirewalInfo = {
    _securityGroupsRestStore : "",
    _securityTempIngressRestStore:"",
    _securityEgressRestStore:"",
    _ingressGridWidget:"",
    _engressGridWidget:"",
    _zoneRestStore:"",
    _securityGroupsTempRestStore:"",
    init : function() {
        
        this._securityGroupsTempRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/"
        });
        
        
        this._securityTempRuleRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroupTemplate/ingress/"
        });
        
        this._securityEgressRestStore = new JsonRest({
            target: core.getContextPath()+"/api/securityGroup/egress/"
        });        
    },
    populateValues : function() {       
       if(dijit.byId("adminFirewalDataGrid")) {                                   
           dijit.byId("adminFirewalDataGrid").destroyRecursive();                    
       }
       
        var data = {
            items: []
        }; 
        dojo.byId("adminfirewallList").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var taxDataList = new ItemFileWriteStore({data: data}); 
        var taxDataLayout = [[
                 {'field': 'id', 'hidden': 'true'},
                 {'name': translator.common.name, 'field': 'name', 'width': '300px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.description, 'field': 'description', 'width': '300px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.baseOSName, 'field': 'baseOs', 'width': '200px', datatype:"string",  autoComplete: true},
                 {'name': translator.common.action, 'field': 'action',
                     'formatter': function(data) { 
                                         
                       var html = "<a class='btn-flat primary' title='"+translator.common.firewall.managePort+"' href='#/admin/firewall/edit/"+data+"'>"+translator.common.firewall.managePort+"</a>" + "<a class='btn-flat danger spacing' href='#/admin/firewall/delete/"+data+"'>"+translator.common.deleteData+"</a>";
                       return html;
                },'width': '100%', datatype:"string",  autoComplete: true}
             ]
         ];
         
         this._securityGroupsTempRestStore.query().then(function(data) {
             if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") {
                 dojo.byId("adminfirewallList").innerHTML = "";
                 dojo.byId("noFirewallMessageBox").style.display = "block";
             } else {   
                 dojo.byId("noFirewallMessageBox").style.display = "none";
                 
                 dojo.forEach(data, function(firewalData) {
                     taxDataList.newItem({
                         id:firewalData.securityGroupId,                     
                         name:firewalData.securityGroupName,
                         description: firewalData.description, 
                         baseOs: firewalData.baseOs.name,
                         action : firewalData.securityGroupId
                     });                     
                 });
                 dojo.byId("adminfirewallList").innerHTML = "";
                 var dataGrid = new EnhancedGrid({                 
                     id: 'adminFirewalDataGrid',
                     "class" : "span12",
                     store: taxDataList,
                     structure: taxDataLayout,
                     autoHeight: true,
                     plugins: core.getGridConfig().plugins
                 });
                dataGrid.placeAt("adminfirewallList");
                dataGrid.startup();
            }             
        });                       
    },
    authentication : function() {
        var pageNode = dojo.byId("adminSecurityPage");
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
    add : function() {              
        var status = FirewalInfo.authentication();                
        if(status == true) {
            var formElements = dojo.query("#firewallAddForm input:checked");            
            if(formElements[0] == undefined || formElements[0] == 'undefined') {                
                dojo.byId("osExceptionExceptionMsg").style.display = "block";            
            } else {                
                dojo.byId("osExceptionExceptionMsg").style.display = "none";                
                var checkedRadioId = dijit.byId(formElements[0].id);        
            var baseOs = dijit.byId(checkedRadioId).value;
            
            var name = dijit.byId("adminSecurityGoupsName");
            var description = dijit.byId("adminSecurityGoupsDescription");
            var securityGroupsTempRestStore = new JsonRest({
                target: core.getContextPath()+"/api/securityGroupTemplate/"
            });                                                    
            dijit.byId("firewalButton").setAttribute('style', 'display: none');
            dojo.byId("firewalLoader").style.display = "block";
        securityGroupsTempRestStore.add({
            name: name.value,
            description: description.value,
            baseOs : baseOs
        }).then(function(resultData) {
            dojo.forEach(resultData, function(addResponseData) {
                dijit.byId("firewalButton").setAttribute('style', 'display: block');
                dojo.byId("firewalLoader").style.display = "none";
                if(addResponseData.result == "OK") {         
                registry.byId("appToaster").setContent(translator.common.firewall.firewallSuccess,"message");
                registry.byId("appToaster").show();
                dijit.byId("firewallAddForm").reset();
                 core.router.go("#/admin/firewall");
             } else {
                 registry.byId("appToaster").setContent(translator.common.firewall.firewallError, "error");
                 registry.byId("appToaster").show();
             }  
            });
         
        });
            }            
        }         
    } ,
    
    cancel : function() {
         core.router.go("#/admin/firewall");
    }    
    
};

window.FirewalInfo = FirewalInfo;
window.ViewFirewalInfo = ViewFirewalInfo;
window.IngressInfo = IngressInfo;
window.EgressInfo = EgressInfo;
window.DeleteFirewall = DeleteFirewall;
window.DeleteEgress = DeleteEgress;
