var BandwidthInfo = {
    _misclRestStore:"",
    _zoneRestZone:"",
    _zoneRestStore:"",
    _podRestStore : "",
    _clusterRestStore : "",
    _podWidget:"",
    _clusterWidget : "",
    _zoneWidget : "",
    init: function() {
        this._misclRestStore = new JsonRest({
            target: "/FogPanel/api/miscellaneousOffer/"
        });

        this._zoneRestStore = new JsonRest({
            target: "/FogPanel/api/zone/"
        });

        this._podRestStore = new JsonRest({
            target: "/FogPanel/api/pod/"
        });
        
        this._clusterRestStore = new JsonRest({
            target: "/FogPanel/api/cluster/"
        });
        
        var currency = new JsonRest({
            target: "/FogPanel/api/config/currency"
        });
        currency.query().then(function(data) {
               dojo.forEach(data,function(cur) { 
                   dojo.byId("micIpCurrencyValue").innerHTML= cur.currency;
               });
        }); 
    },
    populateValues: function() {   
        var podRestStore = this._podRestStore;
        var clusterRestStore = this._clusterRestStore;
        var span = "<span class='require'>*</span>";
        if(dijit.byId("bandwidthZone")) {
            dijit.byId("bandwidthZone").destroyRecursive();
            dijit.byId("bandwidthPod").destroyRecursive();
            dijit.byId("bandwidthCluster").destroyRecursive();
        }
        if(dijit.byId("micIpCostWidget")) {
            dijit.byId("micIpCostWidget").destroyRecursive();
        }
        if(dijit.byId("bandwidthGrid")) {
            dijit.byId("bandwidthGrid").destroyRecursive();
        }
        var summaryData = {
            items: []
        };
        dojo.byId("micIpInfo").innerHTML = "<img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p>Loading Please wait ...</p>";
        var summaryDataList = new ItemFileWriteStore({data: summaryData});
        var summaryLayout = [[
                 {'field': 'id',  'hidden' : 'true'},
                 {'name': 'Zone', 'field': 'zone', 'width' : '100%'},
                 {'name': 'Pod', 'field': 'pod', 'width' : '100%'},
                 {'name': 'Cluster', 'field': 'cluster', 'width' : '100%'},
                 {'name': 'Cost', 'field': 'cost', 'width' : '100%'}
                    
             ]
         ];  
              
         this._misclRestStore.get(1).then(function(data) {  
             if(data.miscellaneousOfferZoneCosts.length == 0 || data.miscellaneousOfferZoneCosts == undefined || data.miscellaneousOfferZoneCosts == 'undefined' || data.miscellaneousOfferZoneCosts == '' || data.miscellaneousOfferZoneCosts == " ") {               
                dojo.byId("nomicIpMessageBox").style.display = "block";   
                dojo.byId("micIpInfo").innerHTML = "";
             } else {
                 dojo.byId("nomicIpMessageBox").style.display = "none";   
                 dojo.forEach(data.miscellaneousOfferZoneCosts, function(miscData) {
                     summaryDataList.newItem({
                         id:miscData.id, 
                         zone: miscData.zone.aliasName, 
                         pod: miscData.pod.name, 
                         cluster: miscData.cluster.name, 
                         cost: miscData.cost 
                     });
                 });
                 dojo.byId("micIpInfo").innerHTML = "";
                 var summaryGridWidget = new EnhancedGrid({
                     id: "bandwidthGrid",
                     store: summaryDataList,
                     structure: summaryLayout,
                     plugins: core.getGridConfig().plugins,
                     autoHeight: true           
                 });
                 summaryGridWidget.placeAt("micIpInfo");
                 summaryGridWidget.startup();
             }             
         });
       
        
        var podOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Pod"}]
        };
        var clusterOptions = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Cluster"}]
        };
        var podList = new ItemFileWriteStore({data: podOptions});
        var clusterList = new ItemFileWriteStore({data: clusterOptions});
          
        this._clusterWidget = new Select({
            id: "bandwidthCluster",
            store: clusterList,
            onChange : function() {                             
                var misclRestStore = new JsonRest({
                    target: "/FogPanel/api/miscellaneousOffer/"
                });    
                
                var miscelWidget = dijit.byId("micIpCostWidget");
                
                misclRestStore.query({miscellaneousOfferId: 1, 
                clusterReferenceId: this.value}).then(function(data) {                
                if(data.length == 0 || data == undefined || data == 'undefined' || data == '' || data == " ") { 
                    miscelWidget.clearWidgets();                
                } else {                    
                    dojo.forEach(data, function(miscData) {                        
                        miscelWidget.zoneCost =  miscData.cost;
                        miscelWidget.labelName =  "Excess Bandwidth Cost/GB/Month" + span;      
                        miscelWidget.setZoneId(miscData.zone.id);
                        miscelWidget.setCost();                        
                    })   
                }                                    
            });
        }        
    }).placeAt("micIpCluster"); 
        
        this._podWidget = new Select({
            id: "bandwidthPod",
            store: podList,
            onChange: function() {
                var clusterWidget = dijit.byId("bandwidthCluster");
                var clusterOptions = {
                identifier: 'id',
                label: 'name',
                items: []
                };
                var currentClusterList = new ItemFileWriteStore({data: clusterOptions});
                if(this.value == "empty") {
                    currentClusterList.newItem({id: "empty", name: "Select Cluster"});
                    clusterWidget.setStore(currentClusterList);
                } else {
                    clusterRestStore.get(this.value).then(function(clusterListItems) {
                        dojo.forEach(clusterListItems, function(currentcluster) {
                            currentClusterList.newItem({id: currentcluster.clusterReferenceId, name: currentcluster.clusterName});
                        });
                        clusterWidget.setStore(currentClusterList);
                    });
                }
            }
        }).placeAt("micIpPod");   
        var zoneOptions = { 
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: "Select Zone"}]
        }; 
        
        var zoneList = new ItemFileWriteStore({data: zoneOptions});
        this._zoneRestStore.query().then(function(data) {
            dojo.forEach(data, function(el) {
                zoneList.newItem({id:  el.referenceZoneId, name: el.aliasName});
            });
        });                        
        var currentZoneCost  = new Zone.ZoneCost({
            id:"micIpCostWidget",                        
            labelName: "Excess Bandwidth Cost/GB/Month" + span                   
        });     
        currentZoneCost.removeCosts();    
        currentZoneCost.removeUnitCost();   
        currentZoneCost.placeAt("micIpCostInfo");
        currentZoneCost.startup();
        
        this._zoneWidget = new Select({
            id : "bandwidthZone",
            store: zoneList,
            onChange: function() {  
                
                var podWidget = dijit.byId("bandwidthPod");                 
                
                var podOptions = {
                    identifier: 'id',
                    label: 'name',
                    items: []
                };
                
                var currentPodList = "";
                var podRestStore = new JsonRest({
                    target: "/FogPanel/api/pod/"
                });
                currentPodList = new ItemFileWriteStore({data: podOptions});
                if(this.value == "option") {
                    currentPodList.newItem({id: "empty", name: "Select Pod"});
                    podWidget.setStore(currentPodList);
                    dijit.byId("micIpButton").set("disabled", true);
                } else {
                    dijit.byId("micIpButton").set("disabled", false);
                    podRestStore.get(this.value).then(function(podListItems) {
                        dojo.forEach(podListItems, function(currentPod) {
                            currentPodList.newItem({id: currentPod.podReferenceId, name: currentPod.podName});
                        });
                        podWidget.setStore(currentPodList);
                    });
                }
                
                var zoneRestStore = new JsonRest({
                    target: "/FogPanel/api/zone/"
                });
                
                zoneRestStore.get(this.value).then(function(zoneDate) {
                    var miscelWidget = dijit.byId("micIpCostWidget")
                    miscelWidget.setZoneId(zoneDate.id);
                })
            }
        }).placeAt("micIpZone"); 
        
    },
    update: function() {       
        var podWidget = dijit.byId("bandwidthPod");
        var zoneCosts = [];
        var clusterWidget = dijit.byId("bandwidthCluster");
        var miscelWidget = dijit.byId("micIpCostWidget");
        var status = miscelWidget.showError();
        if(status == true) {
            var miscelZoneCostList = dojo.byId("bandwidthZoneCostList");
            var miscelZoneCostWidgets = dijit.registry.findWidgets(miscelZoneCostList);        
            dojo.forEach(miscelZoneCostWidgets, function(el) {                      
                zoneCosts.push({
                    zoneId: el.getZoneId(), 
                    cost: el.getZoneCostValue()
                });
            });         
            for(var index=0; index < zoneCosts.length-miscelZoneCostWidgets.length; index++) {
                zoneCosts.splice(index, miscelZoneCostWidgets.length);
            }
            dijit.byId('micIpButton').set('style', {'display': 'none'});
            dojo.byId('micIpLoader').style.display = "block";
            this._misclRestStore.put({            
                id: 1,
                zoneCosts: zoneCosts,
                zoneReferenceId : this._zoneWidget.value,
                clusterReferenceId : clusterWidget.value,
                podReferenceId : podWidget.value
            }).then(function(result)  { 
                dijit.byId('micIpButton').set('style', {'display': 'block'});
                dojo.byId('micIpLoader').style.display = "none";
                if(result == "OK") {
                    registry.byId("appToaster").setContent("Updated Successfully!","message");
                    registry.byId("appToaster").show();
                    BandwidthInfo.populateValues();    
                    dijit.byId("miscSnapButton").set("disabled", true);
                } else {
                    registry.byId("appToaster").setContent("Cannot be updated !", "error");
                    registry.byId("appToaster").show();
                }  
            });
        }        
    }     
    };