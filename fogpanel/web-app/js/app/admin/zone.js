require([ 
    "dojo", 
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js",
    "dijit/dijit",  
    "dojo/store/JsonRest",     
    'dojox/grid/DataGrid', 
    'dojo/data/ItemFileWriteStore', 
    'dojo/dom', 
    'dojo/domReady!', 
    "dijit/form/Button",
    "dijit/Editor"
],function(dojo, i18n, translator, dijit, JsonRest) {
    window.translator = translator;
    controller({ 
        name: "zone",
        module: "admin",
        filePath: "/js/app/admin/zone.js",
        layout: {
            name: "zone_index",
            containerId: "content"
        },			
        scaffold: false
    },
    {
        "index": action(function() {           
            core.ui.loadTemplate("zone_index", core.ui.getContentId());
//            ConfigTest.init();
            AdminZoneInfo.init();
            AdminZoneInfo.populateValues(); 
        }),
        
        "edit": action(function(item) {
            var zoneForm = dijit.byId ("zone_form");
            if(zoneForm) {
                zoneForm.destroyRecursive();                        
            }   
            core.ui.loadTemplate("zone_edit", core.ui.getContentId());                 
            zoneStore.get(item).then(function(data) {
            dojo.byId("zone_id").innerHTML = data.id;
            dijit.byId("zone_name").setValue(data.aliasName);
            dijit.byId("zone_description").setValue(data.aliasDescription); 
        });  
    })
});
});
    
function update() {
    var id =  dojo.byId ("zone_id").innerHTML;
    var name = dijit.byId ("zone_name").value;
    var description = dijit.byId ("zone_description").value;
     
    zoneStore.put({
        id: id,
        aliasName:name,
        aliasDescription: description,
        "class":"com.assistanz.cloud.portal.Zone"
    }).then(function (results) {
        if(results == "OK") {
            dijit.byId("zone_page").destroyRecursive();
            core.router.go("#/admin/zone");
        } else {
            alert("update failed");
        }
    });
    } 
    function cancel() {
    core.router.go("#/admin/zone");
}

function returnZone(widgetId) {
    var currentZoneWidget = dijit.byId (widgetId);
    var zoneId = currentZoneWidget.additionalProperties.widgetId;
    var zoneForm = dijit.byId ("zone_form");
    if(zoneForm) {
        zoneForm.destroyRecursive(); 
        core.router.go("#/admin/zone/edit/" + zoneId);
    }
    core.router.go("#/admin/zone/edit/" + zoneId); 
}


var AdminZoneInfo = {
    zoneWidgetPane: "",
    _zoneRestStore: "",
    _podRestStore : "",
    _clusterRestStore : "",
    _hostRestStore : "",
    
    init : function() {
        this._zoneRestStore = new JsonRest({
            target: core.getContextPath()+"/api/zone/"
        });
        
        this._podRestStore = new JsonRest({
            target: core.getContextPath()+"/api/pod/"
        });
        
        this._clusterRestStore = new JsonRest({
            target: core.getContextPath()+"/api/cluster/"
        });
        
        this._hostRestStore = new JsonRest({
            target: core.getContextPath()+"/api/host/"
        });
           

        dojo.query("#setupWizardSteps li").forEach(function(node, index, arr) {
            node.className = "";
            if (index == 2) {
                node.className = "active";
            }
        });
    },
    populateValues: function() { 
        //Check - Zone List loaded already? if yes exit from this function
        if (dojo.query("#adminZoneList .WizardListItem").length != 0) {
            return;
        }
             
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(zoneData) {
                if(zoneData) {
                   var zoneListItem = new FogPanel.WizardListItem({
                    heading: zoneData.aliasName,
                    description: zoneData.aliasDescription,
                    
                    onClick: function() {
                        AdminZoneInfo.returnZone(this);
                    },
                    
                    onDeleteTagClick: function() {
                        AdminZoneInfo.deleteCurrentWidget(this, "false");
                    },
                    
                    additionalProperties : {
                        id: zoneData.id,
                        name: zoneData.name,
                        aliasName: zoneData.aliasName,
                        description : zoneData.aliasDescription,
                        referenceZoneId : zoneData.referenceZoneId
                    }
                }); 
                }
                
                zoneListItem.placeAt("adminZoneItem");
                zoneListItem.startup();
                zoneListItem.disableStates();
            });
        });
                
    },
    returnZone : function(currentZone) {
        dojo.byId("adminCurrentZoneInfo").className = "active";
        dojo.byId("adminPodListDetails").removeAttribute("class", "active");
        dojo.byId("adminZoneDetails").style.display = "block";
        dojo.byId("adminPodDetails").style.display = "none";
        dojo.byId("adminClusterDetails").style.display = "none";
        dojo.byId("adminPodCollection").style.display = "none";
       
               
        dojo.query("#adminZoneCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminPodListCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminClusterListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#adminClusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.byId("adminZoneCollection").style.display = "block";
        dojo.byId("adminPodListCollection").style.display = "none";
        
        dojo.byId("adminCurrentZoneId").value = currentZone.id;
        var zoneWidgetPane =  new FogPanel.ZoneItem ({
                    zoneName: currentZone.additionalProperties.aliasName,
                    zoneDescription: currentZone.additionalProperties.description,
                    zoneId: currentZone.additionalProperties.referenceZoneId,
                    onZoneClick: function() {
                        AdminZoneInfo.updateZone(this);
                    }
                });
                
                zoneWidgetPane.placeAt("adminZoneInfo"); 
                
       var zoneListCollection = dojo.byId("adminZoneList");
       var zoneListWidgets = dijit.registry.findWidgets(zoneListCollection);
       dojo.forEach(zoneListWidgets, function(el) {
           if (el.id == currentZone.id ) {
               currentZone.setSelectState(true, currentZone.id);
           } else {
               currentZone.setSelectState(false, el.id);
           }
       });  
       
    },
     updateZone: function(currentZoneWidget) {
       var status = currentZoneWidget.validateZoneName();
       if(status == true) {
           var currentZoneWidgetId = dojo.byId("adminCurrentZoneId").value;
       var currentZone = dijit.byId(currentZoneWidgetId);
       this._zoneRestStore.put ({
            aliasName : currentZoneWidget.getZoneName(),
            id: currentZoneWidget.getId(),
            aliasDescription: currentZoneWidget.getDescription(),
            "class":"com.assistanz.fogpanel.Zone"
        }).then(function(result) {
            dojo.forEach(result, function(zoneData) {
                if(zoneData.result == "OK") {
                 currentZone.additionalProperties.heading = zoneData.zoneName;
                 currentZone.additionalProperties.description = zoneData.zoneDescription; 
                 currentZone.additionalProperties.aliasName =  zoneData.zoneName;
                 currentZone.getData();
                 registry.byId("appToaster").setContent(translator.common.message.updateSuccess,"message");
                 registry.byId("appToaster").show();
                 AdminZoneInfo.showPod();
             } else {
                 registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                 registry.byId("appToaster").show();
             }  
         });
     });
       }
       
 },
    updatePod :  function(currentPodWidget) {
       var currentPodId = dojo.byId("adminCurrentPodId").value;
       var currentPod = dijit.byId(currentPodId);
      this._podRestStore.put ({
            id: currentPodWidget.getId(),
            description: currentPodWidget.getDescription()           
        }).then(function(data) {
            dojo.forEach(data, function(podData) {
                if(podData.result == "OK") {
                    currentPod.additionalProperties.heading = podData.podName;
                    currentPod.additionalProperties.description = podData.podDescription; 
                    currentPod.getData();
                    registry.byId("appToaster").setContent(translator.common.message.updateSuccess,"message");
                    registry.byId("appToaster").show();
                    AdminZoneInfo.showCluster();
             } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed,"error");
                    registry.byId("appToaster").show();
             }  
            });
        });
              
    },
    updateCluster :  function(currentClusterWidget) {
        var currentclusterId = dojo.byId("adminCurrentClusterId").value;
       var currentCluster = dijit.byId(currentclusterId);
        this._clusterRestStore.put ({
            id: currentClusterWidget.getId(),
            
            description: currentClusterWidget.getDescription(),
            "class":"com.assistanz.fogpanel.Zone"
        }).then(function(data) {
            dojo.forEach(data, function(clusterata) {
                if(clusterata.result == "OK") {
                    currentCluster.additionalProperties.heading = clusterata.clusterName;
                    currentCluster.additionalProperties.description = clusterata.clusterDescription; 
                    currentCluster.getData();
                    registry.byId("appToaster").setContent(translator.common.message.updateSuccess,"message");
                    registry.byId("appToaster").show();
                    AdminZoneInfo.showHost();
             } else {
                    registry.byId("appToaster").setContent(translator.common.message.failed, "error");
                    registry.byId("appToaster").show();
             }  
            });
        });
    },
    returnPod : function (currentPod) {
        dojo.byId("adminCurrentPodInfo").className = "active";
        dojo.byId("adminClusterListDetails").removeAttribute("class", "active");
        
        dojo.byId("adminZoneDetails").style.display = "block";
        dojo.byId("adminPodDetails").style.display = "block";
        dojo.byId("adminClusterDetails").style.display = "none";
        
        dojo.query("#adminPodCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#adminClusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("adminPodCollection").style.display = "block";
        dojo.byId("adminClusterListCollection").style.display = "none";
        dojo.byId("adminCurrentPodId").value = currentPod.id;
        var zoneWidgetPane =  new FogPanel.ZoneItem ({
                    zoneName: currentPod.additionalProperties.name,
                    zoneDescription: currentPod.additionalProperties.description,
                    zoneId: currentPod.additionalProperties.podReferenceId,
                    onZoneClick: function() {
                        AdminZoneInfo.updatePod(this);
                    }
                });
                zoneWidgetPane.disableZoneName();
                zoneWidgetPane.placeAt("adminPodInfo"); 
                
       var podListCollection = dojo.byId("adminPodListCollection");
       var podListWidgets = dijit.registry.findWidgets(podListCollection);
       dojo.forEach(podListWidgets, function(el) {
           if (el.id == currentPod.id ) {
               currentPod.setSelectState(true, currentPod.id);
           } else {
               currentPod.setSelectState(false, el.id);
           }
       });  
    },
    returnCluster : function(currentCluster) {
        dojo.byId("adminCurrentClusterInfo").className = "active";
        dojo.byId("adminHostListDetails").removeAttribute("class", "active");
        dojo.byId("adminClusterDetails").style.display = "block";
        dojo.query("#adminClusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("adminClusterCollection").style.display = "block";
        dojo.byId("adminHostListCollection").style.display = "none";
        dojo.byId("adminCurrentClusterId").value = currentCluster.id;
        var zoneWidgetPane =  new FogPanel.ZoneItem ({
                    zoneName: currentCluster.additionalProperties.name,
                    zoneDescription: currentCluster.additionalProperties.description,
                    zoneId: currentCluster.additionalProperties.clusterReferenceId,
                    onZoneClick: function() {
                        AdminZoneInfo.updateCluster(this);
                    }
                });
                zoneWidgetPane.disableZoneName();
                zoneWidgetPane.placeAt("adminClusterInfo"); 
                
        var clusterListCollection = dojo.byId("adminClusterListCollection");
       var clusterListWidgets = dijit.registry.findWidgets(clusterListCollection);
       dojo.forEach(clusterListWidgets, function(el) {
           if (el.id == currentCluster.id ) {
               currentCluster.setSelectState(true, currentCluster.id);
           } else {
               currentCluster.setSelectState(false, el.id);
           }
       });  
             
    },
    zoneInfo :  function() {
       dojo.byId("adminCurrentZoneInfo").className = "active";
       dojo.byId("adminPodListDetails").removeAttribute("class", "active");
       
        dojo.byId("adminZoneDetails").style.display = "block";
        dojo.byId("adminPodDetails").style.display = "none";
        dojo.byId("adminClusterDetails").style.display = "none";
               
        dojo.query("#adminZoneCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminPodCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminClusterListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#adminClusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.byId("adminZoneCollection").style.display = "block";
        dojo.byId("adminPodListCollection").style.display = "none";
       
       
        var currentZoneId = dojo.byId("adminCurrentZoneId").value;
        var currentZoneWidget = dijit.byId(currentZoneId);
        
        dojo.query("#adminZoneList .ZoneItem").forEach(dojo.destroy);
        dojo.byId("adminZoneCollection").style.display = "block";
        dojo.byId("adminPodListCollection").style.display = "none";
        
        var zoneWidgetPane =  new FogPanel.ZoneItem({
                    zoneName: currentZoneWidget.additionalProperties.aliasName,
                    zoneDescription: currentZoneWidget.additionalProperties.description,
                    zoneId: currentZoneWidget.additionalProperties.referenceZoneId,
                    onZoneClick: function() {
                        AdminZoneInfo.updateZone(this);
                    }
                });
                zoneWidgetPane.placeAt("adminZoneInfo"); 
    },
    podInfo : function() {
        dojo.byId("adminCurrentPodInfo").className = "active";
       dojo.byId("adminClusterListDetails").removeAttribute("class", "active");
        var currentPodId = dojo.byId("adminCurrentPodId").value;
        
        dojo.byId("adminZoneDetails").style.display = "block";
        dojo.byId("adminPodDetails").style.display = "block";
        dojo.byId("adminClusterDetails").style.display = "none";
        
        dojo.query("#adminPodCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#adminClusterCollection .ZoneItem").forEach(dojo.destroy);
        
        var currentPodWidget = dijit.byId(currentPodId);
        dojo.query("#adminPodCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("adminPodCollection").style.display = "block";
        dojo.byId("adminClusterListCollection").style.display = "none";
        
        
        var zoneWidgetPane =  new FogPanel.ZoneItem({
                    zoneName: currentPodWidget.additionalProperties.name,
                    zoneDescription: currentPodWidget.additionalProperties.description,
                    zoneId: currentPodWidget.additionalProperties.podReferenceId,
                    onZoneClick: function() {
                        AdminZoneInfo.updatePod(this);
                    }
                });
                zoneWidgetPane.disableZoneName();
                zoneWidgetPane.placeAt("adminPodInfo"); 
    },
    clusterInfo : function() {
        dojo.byId("adminCurrentClusterInfo").className = "active";
       dojo.byId("adminHostListDetails").removeAttribute("class", "active");
        var currentClusterId = dojo.byId("adminCurrentClusterId").value;
        
        var currentClusterWidget = dijit.byId(currentClusterId);
        dojo.query("#adminClusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.byId("adminClusterCollection").style.display = "block";
        dojo.byId("adminHostListCollection").style.display = "none";
        
        
        var zoneWidgetPane =  new FogPanel.ZoneItem({
                    zoneName: currentClusterWidget.additionalProperties.name,
                    zoneDescription: currentClusterWidget.additionalProperties.description,
                    zoneId: currentClusterWidget.additionalProperties.clusterReferenceId,
                    onZoneClick: function() {
                        AdminZoneInfo.updateCluster(this);
                    }
                });
                zoneWidgetPane.disableZoneName();
                zoneWidgetPane.placeAt("adminClusterInfo"); 
    },
    returnHost : function(currentHostWidget) {
        currentHostWidget.unSelectItem(currentHostWidget.id);
    },
    showHost : function() {
        dojo.byId("adminHostListDetails").className = "active";
        dojo.byId("adminCurrentClusterInfo").removeAttribute("class", "active");
        dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
        dojo.byId("adminClusterCollection").style.display = "none";
        dojo.byId("adminHostListCollection").style.display = "block";
        var currentClusterId = dojo.byId("adminCurrentClusterId").value;
        var currentClusterWidget = dijit.byId(currentClusterId);
        this._hostRestStore.get(currentClusterWidget.additionalProperties.clusterReferenceId).then(function(data) {
           dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
           if(data) {
               dojo.forEach(data, function(hostInfo) {
              
                   var hostListItem = new FogPanel.WizardListItem({
                    heading: hostInfo.hostName,
                    description : hostInfo.hostDescription,
                    onClick: function() {
                        AdminZoneInfo.returnHost(this);
                    },
                    additionalProperties : {
                        
                        name: hostInfo.hostTag,
                        
                        hostReferenceId : hostInfo.hostReferenceId
                    }
                    
                });
               
               
                hostListItem.placeAt("adminHostListItem");
                hostListItem.startup();
                hostListItem.unSelectItem();
                hostListItem.disableStates();
                
            });
           }
           
        });
    },
    showCluster : function() {
        
        dojo.byId("adminClusterListDetails").className = "active";
       dojo.byId("adminCurrentPodInfo").removeAttribute("class", "active");
       
       dojo.byId("adminZoneDetails").style.display = "block";
        dojo.byId("adminPodDetails").style.display = "block";
        dojo.byId("adminClusterDetails").style.display = "none";
        
        dojo.query("#adminPodCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#adminClusterCollection .ZoneItem").forEach(dojo.destroy);
       
        dojo.query("#adminClusterListCollection .WizardListItem").forEach(dojo.destroy);
        dojo.byId("adminPodCollection").style.display = "none";
        dojo.byId("adminClusterListCollection").style.display = "block";
        var currentPodId = dojo.byId("adminCurrentPodId").value;
        var currentPodWidget = dijit.byId(currentPodId);
        this._clusterRestStore.get(currentPodWidget.additionalProperties.podReferenceId).then(function(data) {
            dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
            if(data) {
               dojo.forEach(data, function(clusterInfo) {
               
                    var clusterListItem = new FogPanel.WizardListItem({
                    heading: clusterInfo.clusterName,
                    description: clusterInfo.clusterDescription,
                    
                    onClick: function() {
                        AdminZoneInfo.returnCluster(this);
                    },
                    additionalProperties : {
                        
                        name: clusterInfo.clusterName,
                        description : clusterInfo.clusterDescription,
                        clusterReferenceId : clusterInfo.clusterReferenceId
                    }
                });
                clusterListItem.placeAt("adminClusterListItem");
                clusterListItem.startup();
                clusterListItem.disableStates();
            }); 
            }
            
        });
    },
    showPod : function() {
       dojo.byId("adminPodListDetails").className = "active";
       dojo.byId("adminCurrentZoneInfo").removeAttribute("class", "active");
       
       dojo.byId("adminZoneDetails").style.display = "block";
        dojo.byId("adminPodDetails").style.display = "none";
        dojo.byId("adminClusterDetails").style.display = "none";
               
        dojo.query("#adminZoneCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminPodCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminClusterListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.query("#adminClusterCollection .ZoneItem").forEach(dojo.destroy);
        dojo.query("#adminHostListCollection .WizardListItem").forEach(dojo.destroy);
        
        dojo.byId("adminZoneCollection").style.display = "block";
        dojo.byId("adminPodListCollection").style.display = "none";
       
       
        dojo.query("#adminPodListCollection .WizardListItem").forEach(dojo.destroy);
        dojo.byId("adminZoneCollection").style.display = "none";
        dojo.byId("adminPodListCollection").style.display = "block";
        var currentZoneId = dojo.byId("adminCurrentZoneId").value;
        var currentZoneWidget = dijit.byId(currentZoneId);
        this._podRestStore.get(currentZoneWidget.additionalProperties.referenceZoneId).then(function(data) {
            dojo.query("#adminClusterListCollection .WizardListItem").forEach(dojo.destroy);
            if(data) {
                dojo.forEach(data, function(zoneInfo) {
                
                    var podListItem = new FogPanel.WizardListItem({
                    heading: zoneInfo.podName,
                    description: zoneInfo.podDescription,
                    
                    onClick: function() {
                        AdminZoneInfo.returnPod(this);
                    },
                    additionalProperties : {
                        
                        name: zoneInfo.podName,
                        podReferenceId : zoneInfo.podReferenceId,
                        description: zoneInfo.podDescription
                    }
                });
                
               
                podListItem.placeAt("adminPodList");
                podListItem.startup();
                podListItem.disableStates();
            });
            }
            
        });
    }  
   
};
window.AdminZoneInfo = AdminZoneInfo;