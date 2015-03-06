"use strict";
require([
    "dojo/store/JsonRest",
    "dojo/_base/lang",
    "dojo/data/ItemFileWriteStore",
    "dojo/domReady!",
    "dojo/parser",
    "dojo/dom",
    "List/ListItem",
    "dojox/widget/Rotator",
    "dojox/widget/rotator/Pan",
    "dijit/form/ValidationTextBox",
    "dijit/form/CheckBox",
    "dojox/validate/regexp",
    "dojo/data/ItemFileWriteStore",
    "dijit/form/Button",
    "dojox/widget/rotator/Slide",
    "dojo/_base/lang",
    "dojo/query",
    "dijit/dijit",
    "dijit/TitlePane",
    "dijit/form/NumberTextBox",
], function(JsonRest) {
    window.JsonRest = JsonRest;
    window.systemStore = new JsonRest({
    target: "/CloudPortal/api/config/",
     idAttribute: "id",
    "class" : "com.assistanz.cloud.portal.Config"
  });
   
      systemStore.query().then(function(data){
      window.configNameValue = dojo.byId("cloudStackUrl");
      window.configValue = dojo.byId("cloudApi");
      window.configDescriptionValue = dojo.byId("cloudSecretKey"); 
       dojo.forEach(data, function(el){
           if(el.id == "1") {
                   systemStore.get(el.id).then(function(item){
                   configNameValue.value = item.value;
            });
           } else if(el.id == "2"){
               systemStore.get(el.id).then(function(item){
               configValue.value = item.value;
            });
           } else if(el.id == "3") {
               systemStore.get(el.id).then(function(item){
               configDescriptionValue.value = item.value;
            });
           }
       });
   });

  });



function addDiskOffering() {
    
//    window.diskNode = dojo.byId("diskOfferingPage");
//    window.diskwidgets = dijit.registry.findWidgets(diskNode);
//    dojo.forEach(diskwidgets, function(el) {
//        if (el.validate && !el.validate()) {
//            el.focus();
//            if (!firstNode) {
//                firstNode = el;
//            }
//        }
//    });
//    if (firstNode) {
//       firstNode.focus();
//   }
//    window.zoneNode = dojo.byId("computingZoneInfo");
//    window.zoneWidgets = dijit.registry.findWidgets(zoneNode);
//    dojo.forEach(zoneWidgets, function(el) {
//        if (el.validate && !el.validate()) {
//            el.focus();
//            if (!firstNode) {
//                firstNode = el;
//            }
//        }
//    });
    
    window.displayText = dijit.byId("diskDescription");
    window.diskName = dijit.byId("diskName");
    window.diskSize = dijit.byId("diskSize");
    window.diskTags = dijit.byId("diskTags");

    window.diskUsCost = dijit.byId("usDiskCost");
    window.diskUkCost = dijit.byId("ukDiskCost");
    window.diskCanadaCost = dijit.byId("canadaDiskCost");
    window.diskUsUnit = dojo.byId("usDiskUnit");
    window.diskUkUnit = dojo.byId("ukDiskUnit");
    window.diskCanadaUnit = dojo.byId("canadaDiskUnit");
    
    var diskData = {
        displayText: displayText.value, 
        diskName: diskName.value, 
        diskSize: diskSize.value / 1024,
        diskTags: diskTags.value,
        diskZoneInfo: [
            {diskZone: '', diskCost: diskUsCost.value, diskDuration: diskUsUnit.innerHTML},
            {diskZone: '', diskCost: diskUkCost.value, diskDuration: diskUkUnit.innerHTML},
            {diskZone: '', diskCost: diskCanadaCost.value, diskDuration: diskCanadaUnit.innerHTML}        
        ]
    };
    
    window.diskListPage = new List.ListItem({
        heading: 'my name',
        diskDescription: 'mydescription',
        onClick: 'returnDiskData(this.id);'
    });
    
    diskListPage.placeAt("diskList");
    diskListPage.startup();

    diskListPage.additionalProperties = diskData;
    diskListPage.additionalProperties.heading = diskName.value;
    diskListPage.additionalProperties.description = Math.round((diskSize.value / 1024)*100)/100 + "GB";
    diskListPage.getData();
    diskListPage.setAttribute('style', 'background: #CAEBC4');

    window.diskOfferRestStore = new JsonRest({
        target: "/CloudPortal/api/diskOffering/"  
    });
    diskOfferRestStore.add ({
        name: diskName.value,
        description: displayText.value,
        diskSize: diskSize.value, 
        storageTags: diskTags.value
    }); 
    
    displayText.reset();
    diskName.reset();
    diskSize.reset();
    diskTags.reset();

    diskUsCost.reset();
    diskUkCost.reset();
    diskCanadaCost.reset();
    
    diskUsUnit.innerHTML = "0.00000000";
    diskUkUnit.innerHTML = "0.00000000";
    diskCanadaUnit.innerHTML = "0.00000000";
}

function returnDiskData(id) {
    
    window.currentDiskListWidget  = dijit.byId(id);

    diskName.setValue(currentDiskListWidget.additionalProperties.heading);
    displayText.setValue(currentDiskListWidget.additionalProperties.displayText);
    diskSize.setValue(currentDiskListWidget.additionalProperties.diskSize*1024);
    diskTags.setValue(currentDiskListWidget.additionalProperties.diskTags);
 
    diskUsCost.setValue(currentDiskListWidget.additionalProperties.diskZoneInfo[0].diskCost);
    diskUkCost.setValue(currentDiskListWidget.additionalProperties.diskZoneInfo[1].diskCost);
    diskCanadaCost.setValue(currentDiskListWidget.additionalProperties.diskZoneInfo[2].diskCost);
    
    diskUsUnit.innerHTML = currentDiskListWidget.additionalProperties.diskZoneInfo[0].diskDuration;
    diskUkUnit.innerHTML = currentDiskListWidget.additionalProperties.diskZoneInfo[1].diskDuration;
    diskCanadaUnit.innerHTML = currentDiskListWidget.additionalProperties.diskZoneInfo[2].diskDuration;
     
    dijit.byId("disk_item_id").innerHTML = currentDiskListWidget;
   
    dijit.byId('diskSize').set('style', {display: 'none'});
    dojo.byId("diskSizeResult").innerHTML = Math.round((diskSize.value / 1024)*100)/100+  " GB";
    dojo.byId("diskSizeResult").style.display = "block";
    
    if(diskTags.value == "") {     
        dojo.byId("diskTagResult").innerHTML = ".......";
    } else {
        dojo.byId("diskTagResult").innerHTML = diskTags.value; 
    }
    
    dijit.byId('diskTags').set('style', {display: 'none'});
    dojo.byId("diskTagResult").style.display = "block";
    window.diskListCollection = dojo.byId("diskListCollection");
    window.listWidgets = dijit.registry.findWidgets(diskListCollection);
    dojo.forEach(listWidgets, function(el) {
        if (el.id == currentDiskListWidget.id ) {
            currentDiskListWidget.setAttribute("class", 'selectedItem');
        } else {
            el.setAttribute("class", 'unSelectItem');
        }
    });
   
   dijit.byId('diskAddButton').set('style', {display: 'none'});
   dijit.byId('disk_update_button').set('style', {display: 'block', "float": 'right'});
  }
  
  function updateDiskOffering() {
      
//      dojo.forEach(diskwidgets, function(el) {
//          if (el.validate && !el.validate()) {
//              el.focus();
//              if (!firstNode) {
//                  firstNode = el;
//              }
//          }
//      }); 
//      
//      dojo.forEach(zoneWidgets, function(el) {
//          if (el.validate && !el.validate()) {
//              el.focus();
//              if (!firstNode) {
//                  firstNode = el;
//              }
//          }
//      });
      
      currentDiskListWidget.additionalProperties.heading = diskName.value;
      currentDiskListWidget.additionalProperties.displayText = displayText.value;
  
      currentDiskListWidget.additionalProperties.diskZoneInfo[0].diskCost = diskUsCost.value;
      currentDiskListWidget.additionalProperties.diskZoneInfo[1].diskCost = diskUkCost.value;
      currentDiskListWidget.additionalProperties.diskZoneInfo[2].diskCost = diskCanadaCost.value;
    
      currentDiskListWidget.additionalProperties.diskZoneInfo[0].diskDuration =  diskUsUnit.innerHTML;
      currentDiskListWidget.additionalProperties.diskZoneInfo[1].diskDuration = diskUkUnit.innerHTML;
      currentDiskListWidget.additionalProperties.diskZoneInfo[2].diskDuration = diskCanadaUnit.innerHTML;
     
    
      dijit.byId('diskAddButton').set('style', {display: 'block', "float": 'right'});
      dijit.byId('disk_update_button').set('style', {display: 'none'});
     
      dijit.byId('diskSize').set('style', {display: 'block', margin: '10px 0 0 157px' });
      dojo.byId("diskSizeResult").style.display = "none";
     
      dijit.byId('diskTags').set('style', {display: 'block',  margin: '10px 0 0 157px' });
      dojo.byId("diskTagResult").style.display = "none";

      currentDiskListWidget.getData();
      
      diskOfferRestStore.remove(2);
//      diskOfferRestStore.put({
//            id: 2,
//            name: diskName.value,
//            description: displayText.value,
//           
//            "class":"com.assistanz.cloud.portal.ComputingOffering"
//        });
      
      displayText.reset();
      diskName.reset();
      diskSize.reset();
      diskTags.reset();
   
      diskUsCost.reset();
      diskUkCost.reset();
      diskCanadaCost.reset();
      
      diskUsUnit.innerHTML = "0.00000000";
      diskUkUnit.innerHTML = "0.00000000";
      diskCanadaUnit.innerHTML = "0.00000000";
           
      dojo.forEach(listWidgets, function(el) {
          el.setAttribute("class", 'unSelectItem');
      });
  } 
  function clearDiskOffering() {
      
      dijit.byId('diskAddButton').set('style', {display: 'block', "float": 'right' });
      dijit.byId('disk_update_button').set('style', {display: 'none'});
      dijit.byId('diskSize').set('style', {display: 'block',  margin: '10px 0 0 157px' });
      dojo.byId("diskSizeResult").style.display = "none";
      dijit.byId('diskTags').set('style', {display: 'block',  margin: '10px 0 0 157px' });
      dojo.byId("diskTagResult").style.display = "none";
      
      diskName.reset();
      diskSize.reset();
      diskTags.reset();
      displayText.reset();
      diskUsCost.reset();
      diskUkCost.reset();
      diskCanadaCost.reset();
              
      diskUsUnit.innerHTML = "0.00000000";
    diskUkUnit.innerHTML = "0.00000000";
    diskCanadaUnit.innerHTML = "0.00000000";
      dojo.forEach(listWidgets, function(el) {
          el.setAttribute("class", 'unSelectItem');
      });
  }   
  
  function addNetworkOffering() {
      
      window.networkNode = dojo.byId("networkOfferingPage");
      window.networkWidgets = dijit.registry.findWidgets(networkNode);
      dojo.forEach(networkWidgets, function(el) {
          if (el.validate && !el.validate()) {
              el.focus();
              if (!firstNode) {
                  firstNode = el;
              }
          }
      });
      
      window.networkZoneNode = dojo.byId("networkZoneInfo");
      window.networkZoneWidgets = dijit.registry.findWidgets(networkZoneNode);
      dojo.forEach(networkZoneWidgets, function(el) {
          if (el.validate && !el.validate()) {
              el.focus();
              if (!firstNode) {
                  firstNode = el;
              }
          }
      });
     
     window.networkName = dijit.byId("networkName");
     window.networkDescription = dijit.byId("networkDescription");
     window.networkRate = dijit.byId("networkRate");
     window.networkLan = dijit.byId("networkLan");
     window.networkTags = dijit.byId("networkTags");
     window.networkMode = dijit.byId("networkMode");
     
     window.networkUsCost = dijit.byId("usNetworkCost");
     window.networkUkCost = dijit.byId("ukNetworkCost");
     window.networkCanadaCost = dijit.byId("canadaNetworkCost");
      
     window.networkUsUnit = dojo.byId("usNetworkUnit");
     window.networkUkUnit = dojo.byId("ukNetworkUnit");
     window.networkCanadaUnit = dojo.byId("canadaNetworkUnit");
    
     var networkData = {
         networkName: networkName.value, 
         networkDescription:  networkDescription.value,
         networkRate: networkRate.value,
         networkTags: networkTags.value,
         networkMode: networkMode.checked,
         networkLan: networkLan.checked,
         networkZoneInfo: [
            {networkZone: '', networkCost: networkUsCost.value, networkDuration: networkUsUnit.innerHTML},
            {networkZone: '', networkCost: networkUkCost.value, networkDuration: networkUkUnit.innerHTML},
            {networkZone: '', networkCost: networkCanadaCost.value, networkDuration: networkCanadaUnit.innerHTML}
        ]
     };
     window.networkList = new List.ListItem({
       heading: 'my name',
       description: 'mydescription',
       onClick: 'returnNetworkOffering(this.id);'
     });
     networkList.placeAt("networkList");
     networkList.startup();
    
     networkList.additionalProperties = networkData;
     networkList.additionalProperties.heading = networkName.value;
     networkList.additionalProperties.description = networkDescription.value;
     networkList.getData();

     networkName.reset();
     networkDescription.reset();
     networkRate.reset();
     networkLan.reset();
     networkTags.reset();
     networkMode.reset();
    
     networkUsCost.reset();
     networkUkCost.reset();
     networkCanadaCost.reset();
     networkList.setAttribute('style', 'background: #CAEBC4');
     networkUsUnit.innerHTML = "0.00000000";
     networkUkUnit.innerHTML = "0.00000000";
     networkCanadaUnit.innerHTML = "0.00000000";
  }
  
  function returnNetworkOffering(id) {
      
      window.currentNetworkWidget  = dijit.byId(id);
      networkName.setValue(currentNetworkWidget.additionalProperties.heading);
      networkDescription .setValue(currentNetworkWidget.additionalProperties.description);
      networkRate.setValue(currentNetworkWidget.additionalProperties.networkRate);
      networkLan.setValue(currentNetworkWidget.additionalProperties.networkLan);
      networkTags.setValue(currentNetworkWidget.additionalProperties.networkTags);
      networkMode.setValue(currentNetworkWidget.additionalProperties.networkMode);

     networkUsCost.setValue(currentNetworkWidget.additionalProperties.networkZoneInfo[0].networkCost);
     networkUkCost.setValue(currentNetworkWidget.additionalProperties.networkZoneInfo[1].networkCost);
     networkCanadaCost.setValue(currentNetworkWidget.additionalProperties.networkZoneInfo[2].networkCost);
     
     networkUsUnit.innerHTML = currentNetworkWidget.additionalProperties.networkZoneInfo[0].networkDuration;
     networkUkUnit.innerHTML = currentNetworkWidget.additionalProperties.networkZoneInfo[1].networkDuration;
     networkCanadaUnit.innerHTML = currentNetworkWidget.additionalProperties.networkZoneInfo[2].networkDuration;
     
     dijit.byId("network_item_Id").innerHTML = currentNetworkWidget;
     if(networkRate.value=="") {
        dojo.byId("networkResult").innerHTML = ".......";
     } else {
        dojo.byId("networkResult").innerHTML = networkRate.value + " MB/s"; 
     }
     dijit.byId('networkRate').set('style', {display: 'none'});

     dojo.byId("networkResult").style.display = "block";
     dojo.byId("networkResult").style.margin =  "10px 30px 0 0"; 
    
     if(networkTags.value=="") {
        dojo.byId("networkTagResult").innerHTML = ".......";
     } else {
        dojo.byId("networkTagResult").innerHTML = networkTags.value; 
     }
     dijit.byId('networkTags').set('style', {display: 'none'});
     dojo.byId("networkTagResult").style.display = "block";

     if(networkMode.checked == true) {
         dojo.byId("networkModeResult").innerHTML  = "yes";
     } else {
         dojo.byId("networkModeResult").innerHTML  = "no";
     }
    dijit.byId('networkMode').set('style', {display: 'none'});
    dojo.byId("networkModeResult").style.display = "block";
    
    if(networkLan.checked==true) {
        dojo.byId("networkLanResult").innerHTML  = "yes";
    } else {
        dojo.byId("networkLanResult").innerHTML  = "no";
    }
            
    dijit.byId('networkLan').set('style', {display: 'none'});
    dojo.byId("networkLanResult").style.display = "block";

    window.networkListCollection = dojo.byId("networkList");
    window.networkListWidgets = dijit.registry.findWidgets(networkListCollection);

    //var networkListPage = dijit.byId("network_item_Id").innerHTML;

   dojo.forEach(networkListWidgets, function(el) {
       if (el.id == currentNetworkWidget.id ) {
           currentNetworkWidget.setAttribute("class", 'selectedItem');
       } else {
           el.setAttribute("class", 'unSelectItem');
       }
   });
    
    dijit.byId('networkAddButton').set('style', {display: 'none'});
    dijit.byId('networkUpdateButton').set('style', {display: 'block', "float": 'right'});
  }
  
  function updateNetworkOffering() {
     
     dojo.forEach(networkWidgets, function(el) {
         if (el.validate && !el.validate()) {
           el.focus();
           if (!firstNode) {
             firstNode = el;
           }
         }
       }); 
       
       dojo.forEach(networkZoneWidgets, function(el) {
         if (el.validate && !el.validate()) {
           el.focus();
           if (!firstNode) {
             firstNode = el;
           }
         }
       });
       
       currentNetworkWidget.additionalProperties.heading = networkName.value;
       currentNetworkWidget.additionalProperties.description = networkDescription.value;
 
       currentNetworkWidget.additionalProperties.networkZoneInfo[0].networkCost = networkUsCost.value;
       currentNetworkWidget.additionalProperties.networkZoneInfo[1].networkCost = networkUkCost.value;
       currentNetworkWidget.additionalProperties.networkZoneInfo[2].networkCost = networkCanadaCost.value;
       
      currentNetworkWidget.additionalProperties.networkZoneInfo[0].networkDuration =  networkUsUnit.innerHTML;
     currentNetworkWidget.additionalProperties.networkZoneInfo[1].networkDuration = networkUkUnit.innerHTML;
     currentNetworkWidget.additionalProperties.networkZoneInfo[2].networkDuration = networkCanadaUnit.innerHTML;
       
       
       dijit.byId('networkRate').set('style', {display: 'block',  margin: ' 10px 0 0 158px'});
       dojo.byId("networkResult").style.display = "none";
       dojo.byId("networkLanResult").style.display = "none";
       dojo.byId("networkModeResult").style.display = "none";
       dijit.byId('networkMode').set('style', {display: 'block',  margin: '10px 0 0 0'});
       dijit.byId('networkLan').set('style', {display: 'block',  margin: '10px 0 0 0'});
       dijit.byId('networkTags').set('style', {display: 'block', margin: '10px 10px 0px 155px' });
       dojo.byId("networkTagResult").style.display = "none";
       dojo.forEach(networkListWidgets, function(el) {
           el.setAttribute("class", 'unSelectItem');
       });     
      dijit.byId('networkAddButton').set('style', {display: 'block', "float": 'right'});
      dijit.byId('networkUpdateButton').set('style', {display: 'none'});
      
      currentNetworkWidget.getData();
      
      networkName.reset();
      networkDescription.reset();
      networkRate.reset();
      networkLan.reset();
      networkTags.reset();
      networkMode.reset();
    
      networkUsCost.reset();
      networkUkCost.reset();
      networkCanadaCost.reset();
      
      networkUsUnit.innerHTML = "0.00000000";
     networkUkUnit.innerHTML = "0.00000000";
     networkCanadaUnit.innerHTML = "0.00000000";
  }
 
 function clearNetwokOffering() {
     
     dijit.byId('networkRate').set('style', {display: 'block',  margin: ' 10px 0 0 158px'});
     dojo.byId("networkResult").style.display = "none";
     dojo.byId("networkLanResult").style.display = "none";
     dojo.byId("networkModeResult").style.display = "none";
     dijit.byId('networkMode').set('style', {display: 'block',  margin: '10px 0 0 0'});
     dijit.byId('networkLan').set('style', {display: 'block',  margin: '10px 0 0 0'});
     dijit.byId('networkTags').set('style', {display: 'block', margin: '10px 10px 0px 155px' });
     dojo.byId("networkTagResult").style.display = "none";
               
     dijit.byId('networkAddButton').set('style', {display: 'block', "float": 'right'});
     dijit.byId('networkUpdateButton').set('style', {display: 'none'});
      
     dojo.forEach(networkListWidgets, function(el) {
         el.setAttribute("class", 'unSelectItem');
     });
            
     networkName.reset();
     networkDescription.reset();
     networkRate.reset();
     networkLan.reset();
     networkTags.reset();
     networkMode.reset();
    
     networkUsCost.reset();
     networkUkCost.reset();
     networkCanadaCost.reset();
     
     networkUsUnit.innerHTML = "0.00000000";
     networkUkUnit.innerHTML = "0.00000000";
     networkCanadaUnit.innerHTML = "0.00000000";
 } 
 
 function addServiceOffering() {
     
     window.serviceNode = dojo.byId("getItem");
     window.serviceWidgets = dijit.registry.findWidgets(serviceNode);
     dojo.forEach(serviceWidgets, function(widgetElement) {
         if (widgetElement.validate && !widgetElement.validate()) {
                    widgetElement.focus();
                    if (!firstNode) {
                        firstNode = widgetElement;
                    }
                }
            });
    window.serviceZoneInfo = dojo.byId("zoneInfo"); 
    window.serviceZoneInfoWidget = dijit.registry.findWidgets(serviceZoneInfo);
     dojo.forEach(serviceZoneInfoWidget, function(elementZone) {
          if (elementZone.validate && !elementZone.validate()) {
              elementZone.focus();
              if (!firstNode) {
                  firstNode = elementZone;
              }
          }
      });
      window.serviceName = dijit.byId("serviceName");
      window.serviceDescription = dijit.byId("serviceDescription");

      window.cpuNumber = dijit.byId("cpuNumber");
      window.cpuSpeed = dijit.byId("cpuSpeed");
      window.cpuMemory = dijit.byId("cpuMemory");

      window.offerHa = dijit.byId("offerHa");
      window.cpuCap = dijit.byId("cpuCap");
      window.networkRate = dijit.byId("serviceNetworkRate");
      window.hostTag = dijit.byId("hostTag");
      window.storageTag = dijit.byId("storageTag");
      window.usCost = dijit.byId("usServiceCost");
      window.ukCost = dijit.byId("serviceUkCost");
      window.canadaCost = dijit.byId("canadaServiceCost");
      window.usSetupCost = dijit.byId("usSetupCost");
      window.ukSetupCost = dijit.byId("ukSetupCost");
      window.canadaSetupCost = dijit.byId("canadaSetupCost");
      window.usServiceUnit = dojo.byId("usServiceUnit");
      window.ukUnit = dojo.byId("ukServiceUnit");
      window.canadaUnit = dojo.byId("canadaServiceUnit");
            
      var serviceData = {
          cpuNumber: cpuNumber.value,
          cpuSpeed: cpuSpeed.value / 1000, 
          serviceDescription: serviceDescription.value,
          cpuMemory: cpuMemory.value / 1024,
          serviceName: serviceName.value,
          networkRate: networkRate.value, 
          hostTag: hostTag.value,
          storageTag: storageTag.value,
          offerHa: offerHa.checked, 
          cpuCap: cpuCap.checked,
          usCost: usCost.value,
          ukCost: ukCost.value,
          canadaCost: canadaCost.value,
          usServiceUnit: usServiceUnit.innerHTML,
          ukUnit: ukUnit.innerHTML,
          canadaUnit: canadaUnit.innerHTML, 
          usSetupCost: usSetupCost.value,
          ukSetupCost: ukSetupCost.value,
          canadaSetupCost: canadaSetupCost.value
      };
      
      window.serviceListItem = new List.ListItem({
          heading: "heading",
          description: "description",
          onClick: "returnServiceOffering(this.id);"
      });
      
      serviceListItem.placeAt("serviceList");
            
      serviceListItem.startup();

      serviceListItem.additionalProperties = serviceData;
      serviceListItem.additionalProperties.heading = serviceName.value;
      serviceListItem.additionalProperties.description = cpuNumber.value + "Core/" + cpuSpeed.value/1000 + "GHz/" + Math.round((cpuMemory.value / 1024)*100)/100 + "GB RAM";
      serviceListItem.getData();
      serviceListItem.setAttribute('style', 'background: #CAEBC4');

      
                      
      cpuCap.reset();
      offerHa.reset();
      usCost.reset();
      ukCost.reset();
      canadaCost.reset();
      usSetupCost.reset();
      ukSetupCost.reset();
      canadaSetupCost.reset();
      cpuNumber.reset();
      cpuSpeed.reset();
      serviceDescription.reset();
      cpuMemory.reset();
      serviceName.reset();
      networkRate.reset();
      storageTag.reset();
      hostTag.reset();
      
      usServiceUnit.innerHTML = "0.00000000";
      ukUnit.innerHTML = "0.00000000";
      canadaUnit.innerHTML = "0.00000000";
  }
  
  function returnServiceOffering(currentItemId) {
      
      window.currentServiceWidget  = dijit.byId(currentItemId);
            
      serviceName.setValue(currentServiceWidget.additionalProperties.heading);
      serviceDescription.setValue(currentServiceWidget.additionalProperties.serviceDescription);
      cpuNumber.setValue(currentServiceWidget.additionalProperties.cpuNumber);
      networkRate.setValue(currentServiceWidget.additionalProperties.networkRate);
      storageTag.setValue(currentServiceWidget.additionalProperties.storageTag);
      cpuSpeed.setValue(currentServiceWidget.additionalProperties.cpuSpeed*1000);
      cpuMemory.setValue(currentServiceWidget.additionalProperties.cpuMemory*1024);
      hostTag.setValue(currentServiceWidget.additionalProperties.hostTag);
      offerHa.setValue(currentServiceWidget.additionalProperties.offerHa);
      cpuCap.setValue(currentServiceWidget.additionalProperties.cpuCap);
      usCost.setValue(currentServiceWidget.additionalProperties.usCost);
      usSetupCost.setValue(currentServiceWidget.additionalProperties.usSetupCost);
      ukCost.setValue(currentServiceWidget.additionalProperties.ukCost);
      ukSetupCost.setValue(currentServiceWidget.additionalProperties.ukSetupCost);
      canadaCost.setValue(currentServiceWidget.additionalProperties.canadaCost);
      canadaSetupCost.setValue(currentServiceWidget.additionalProperties.canadaSetupCost);
      usServiceUnit.innerHTML =  currentServiceWidget.additionalProperties.usServiceUnit;             
      ukUnit.innerHTML =  currentServiceWidget.additionalProperties.ukUnit;             
      canadaUnit.innerHTML =  currentServiceWidget.additionalProperties.canadaUnit;             
     
          
     dijit.byId("cpuNumber").set('style', {display: 'none'});
     dijit.byId("cpuSpeed").set('style', {display: 'none'});
     dijit.byId("cpuMemory").set('style', {display: 'none'});
     dijit.byId("storageTag").set('style', {display: 'none'});
     dijit.byId("offerHa").set('style', {display: 'none'});
     dijit.byId("cpuCap").set('style', {display: 'none'});
     dijit.byId("serviceNetworkRate").set('style', {display: 'none'});
     dijit.byId("hostTag").set('style', {display: 'none'});
                   
     dojo.byId("cpuNumberLabel").innerHTML = cpuNumber.value + " Core";
     dojo.byId("cpuNumberLabel").style.display = "block";
     dojo.byId("cpuSpeedLabel").innerHTML = cpuSpeed.value / 1000 + " GHz";
     dojo.byId("cpuSpeedLabel").style.display = "block";
     dojo.byId("cpuMemoryLabel").innerHTML = Math.round((cpuMemory.value / 1024)*100)/100 + " GB";
     dojo.byId("cpuMemoryLabel").style.display = "block";
     
     if(offerHa.checked==true) {
         dojo.byId("offerHaLabel").innerHTML = "yes";
     } else {
         dojo.byId("offerHaLabel").innerHTML = "no";
     }
     
     dojo.byId("offerHaLabel").style.display = "block";
     if(cpuCap.checked==true) {
         dojo.byId("cpuCapLabel").innerHTML = "yes";
     } else {
         dojo.byId("cpuCapLabel").innerHTML = "no";
     }
     dojo.byId("cpuCapLabel").style.display = "block";
     if(networkRate.value=="") {
         dojo.byId("networkRateLabel").innerHTML = "......";
     } else {
         dojo.byId("networkRateLabel").innerHTML = networkRate.value + "MB/s"; 
     }
     dojo.byId("networkRateLabel").style.display = "block";
     if(storageTag.value == "") {
         dojo.byId("storageTagLabel").innerHTML = "......";
     } else {
         dojo.byId("storageTagLabel").innerHTML = storageTag.value;
     }
     
     dojo.byId("storageTagLabel").style.display = "block";
     if(hostTag.value == "") {
         dojo.byId("hostTagLabel").innerHTML = "......";
     } else {
         dojo.byId("hostTagLabel").innerHTML = hostTag.value;
     }
     
     dojo.byId("hostTagLabel").style.display = "block";
     window.serviceListCollection = dojo.byId("serviceList");
     window.serviceListWidgets = dijit.registry.findWidgets(serviceListCollection);
     //var serviceListPageItem = dijit.byId("serviceListItem").innerHTML;
     dojo.forEach(serviceListWidgets, function(el) {
         if (el.id == currentServiceWidget.id ) {
             currentServiceWidget.setAttribute("class", 'selectedItem');
         } else {
             el.setAttribute("class", 'unSelectItem');
         }
     });
     dijit.byId('serviceAddButton').set('style', { display: 'none' });
     dijit.byId('serviceUpdateButton').set('style', { display: 'block' });
 }  
 
 /***
* To update the form
*/      
  function updateServiceOffering() {
      dojo.forEach(serviceWidgets, function(widgetElement) {
          if (widgetElement.validate && !widgetElement.validate()) {
              widgetElement.focus();
              if (!firstNode) {
                  firstNode = widgetElement;
              }
          }
      });
      
      //serviceZoneInfoWidget = dijit.registry.findWidgets(serviceZoneInfo);
      dojo.forEach(serviceZoneInfoWidget, function(zoneElement) {
          if (zoneElement.validate && !zoneElement.validate()) {
              zoneElement.focus();
              if (!firstNode) {
                  firstNode = zoneElement;
              }
          }
      });
      
      currentServiceWidget.additionalProperties.heading = serviceName.value;
      currentServiceWidget.additionalProperties.serviceDescription = serviceDescription.value;           
      currentServiceWidget.additionalProperties.usCost = usCost.value;
      currentServiceWidget.additionalProperties.usSetupCost = usSetupCost.value;
      currentServiceWidget.additionalProperties.ukCost = ukCost.value;
      currentServiceWidget.additionalProperties.ukSetupCost = ukSetupCost.value;
      currentServiceWidget.additionalProperties.canadaCost = canadaCost.value;
      currentServiceWidget.additionalProperties.canadaSetupCost = canadaSetupCost.value;
      currentServiceWidget.additionalProperties.usServiceUnit = usServiceUnit.innerHTML;
      currentServiceWidget.additionalProperties.ukUnit = ukUnit.innerHTML;
      currentServiceWidget.additionalProperties.canadaUnit = canadaUnit.innerHTML;
      
      currentServiceWidget.getData();
      
      dijit.byId("cpuNumber").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("cpuSpeed").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("cpuMemory").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("storageTag").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("offerHa").set('style', { display: 'block',  margin: '0 0 0 155px'});
      dijit.byId("cpuCap").set('style', { display: 'block',  margin: '0 0 0 155px'});
      dijit.byId("serviceNetworkRate").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("hostTag").set('style', { display: 'block',  margin: '0 0 0 155px' });

      dojo.byId("cpuNumberLabel").style.display = "none";
      dojo.byId("cpuSpeedLabel").style.display = "none";
      dojo.byId("cpuMemoryLabel").style.display = "none";
      dojo.byId("storageTagLabel").style.display = "none";
      dojo.byId("offerHaLabel").style.display = "none";
      dojo.byId("cpuCapLabel").style.display = "none";
      dojo.byId("networkRateLabel").style.display = "none";
      dojo.byId("hostTagLabel").style.display = "none";

      dijit.byId('serviceAddButton').set('style', { display: 'block' });  
      dijit.byId('serviceUpdateButton').set('style', { display: 'none' });
      dojo.forEach(serviceListWidgets, function(el) {
          el.setAttribute("class", 'unSelectItem');
      });  
      
      cpuCap.reset();
      offerHa.reset();
      usCost.reset();
      ukCost.reset();
      canadaCost.reset();
      usSetupCost.reset();
      ukSetupCost.reset();
      canadaSetupCost.reset();
      cpuNumber.reset();
      cpuSpeed.reset();
      serviceDescription.reset();
      cpuMemory.reset();
      serviceName.reset();
      networkRate.reset();
      storageTag.reset();
      hostTag.reset();
      
      usServiceUnit.innerHTML = "0.00000000";
      ukUnit.innerHTML = "0.00000000";
      canadaUnit.innerHTML = "0.00000000";
  }
  
  function cancelServiceOffering() {
      
      dijit.byId("serviceName").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("serviceDescription").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("cpuNumber").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("cpuSpeed").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("cpuMemory").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("storageTag").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("offerHa").set('style', { display: 'block',  margin: '0 0 0 155px'});
      dijit.byId("cpuCap").set('style', { display: 'block',  margin: '0 0 0 155px'});
      dijit.byId("serviceNetworkRate").set('style', { display: 'block',  margin: '0 0 0 155px' });
      dijit.byId("hostTag").set('style', { display: 'block',  margin: '0 0 0 155px' });
                     
      dojo.byId("cpuNumberLabel").style.display = "none";
      dojo.byId("cpuSpeedLabel").style.display = "none";
      dojo.byId("cpuMemoryLabel").style.display = "none";
      dojo.byId("storageTagLabel").style.display = "none";
      dojo.byId("offerHaLabel").style.display = "none";
      dojo.byId("cpuCapLabel").style.display = "none";
      dojo.byId("networkRateLabel").style.display = "none";
      dojo.byId("hostTagLabel").style.display = "none";
            
      dojo.forEach(serviceListWidgets, function(el) {
          el.setAttribute("class", 'unSelectItem');
      });   
      
      dijit.byId('serviceAddButton').set('style', { display: 'block' });
      dijit.byId('serviceUpdateButton').set('style', { display: 'none' });
      
      cpuCap.reset();
      offerHa.reset();
      usCost.reset();
      ukCost.reset();
      canadaCost.reset();
      usSetupCost.reset();
      ukSetupCost.reset();
      canadaSetupCost.reset();
      cpuNumber.reset();
      cpuSpeed.reset();
      serviceDescription.reset();
      cpuMemory.reset();
      serviceName.reset();
      networkRate.reset();
      storageTag.reset();
      hostTag.reset();
      
      usServiceUnit.innerHTML = "0.00000000";
      ukUnit.innerHTML = "0.00000000";
      canadaUnit.innerHTML = "0.00000000";
  }

  function addMiscellaneous(id) {
      
      var buttonWidget = dijit.byId(id)
      if(buttonWidget.id == "addBandwidthCostButton") {
      var zoneInfo = dojo.byId("miscelZoneInfo"); 
      var zoneInfoWidget = dijit.registry.findWidgets(zoneInfo);
      dojo.forEach(zoneInfoWidget, function(el) {
          if (el.validate && !el.validate()) {
              el.focus();
              if (!firstNode) {
                  firstNode = el;
              }
          }
      });
      
      window.messUsCost = dijit.byId("usMiscelCost");
      window.messUkCost = dijit.byId("ukMiscelCost");
      window.messCanadaCost = dijit.byId("canadaMiscelCost");
      window.bandWidthWidget = dijit.byId("bandwidthCost");
      
      var bandWidthData = {
          messUsCost: messUsCost.value, 
          messUkCost: messUkCost.value, 
          messCanadaCost: messCanadaCost.value
      };
      bandWidthWidget.additionalProperties = bandWidthData;
       bandWidthWidget.setAttribute('style', 'background: #CAEBC4');
      alert(bandWidthWidget.heading + " Added");
      } else if(buttonWidget.id == "addIpCostButton") {
          var zoneIpInfo = dojo.byId("miscelIpInfo"); 
      var zoneIpInfoWidget = dijit.registry.findWidgets(zoneIpInfo);
      dojo.forEach(zoneIpInfoWidget, function(el) {
          if (el.validate && !el.validate()) {
              el.focus();
              if (!firstNode) {
                  firstNode = el;
              }
          }
      });
     
      window.messUsIpCost = dijit.byId("usIpCost");
      window.messUkIpCost = dijit.byId("ukIpCost");
      window.messCanadaIpCost = dijit.byId("canadaIpCost");
      window.ipWidget = dijit.byId("ipCost");
      
      var ipWidthData = {
          messUsIpCost: messUsIpCost .value, 
          messUkIpCost: messUkIpCost.value, 
          messCanadaIpCost: messCanadaIpCost.value
      };
      ipWidget.additionalProperties = ipWidthData;
      ipWidget.setAttribute('style', 'background: #CAEBC4');
      alert(ipWidget.heading + " Added");
      } else if(buttonWidget.id == "addSnapCostButton") {
            var zoneSnapInfo = dojo.byId("miscelSnapInfo"); 
      var zoneSnapInfoWidget = dijit.registry.findWidgets(zoneSnapInfo);
      dojo.forEach(zoneSnapInfoWidget, function(el) {
          if (el.validate && !el.validate()) {
              el.focus();
              if (!firstNode) {
                  firstNode = el;
              }
          }
      });
     
      window.messUsSnapCost = dijit.byId("usSnapCost");
      window.messUkSnapCost = dijit.byId("ukSnapCost");
      window.messCanadaSnapCost = dijit.byId("canadaSnapCost");
      window.snapWidget = dijit.byId("snapCost");
      window.usUnit = dojo.byId("usSnapUnit");
      window.ukUnit =  dojo.byId("ukSnapUnit");
      window.canadaUnit =  dojo.byId("canadaSnapUnit");
      
      var snapWidthData = {
          messUsSnapCost: messUsSnapCost.value, 
          messUkSnapCost: messUkSnapCost.value, 
          messCanadaSnapCost: messCanadaSnapCost.value,
          usUnit: usUnit.innerHTML,
          ukUnit: ukUnit.innerHTML,
          canadaUnit: canadaUnit.innerHTML
          
      };
      snapWidget.additionalProperties = snapWidthData;
      snapWidget.setAttribute('style', 'background: #CAEBC4');
//      messUsSnapCost.reset();
//      messUkSnapCost.reset();
//      messCanadaSnapCost.reset();
      
        usUnit.innerHTML = "0.00000000"
        ukUnit.innerHTML = "0.00000000"
        canadaUnit.innerHTML = "0.00000000"
       alert(snapWidget.heading + " Added");
      }
  }
  
  function returnMiscellaneous(id) {
      var listWidget = dijit.byId(id);
      if(listWidget.id == "bandwidthCost") {
      messUsCost.setValue(bandWidthWidget.additionalProperties.messUsCost);
      messUkCost.setValue(bandWidthWidget.additionalProperties.messUkCost); 
      messCanadaCost.setValue(bandWidthWidget.additionalProperties.messCanadaCost);
  } else if(listWidget.id == "ipCost") {
      messUsIpCost.setValue(ipWidget.additionalProperties.messUsIpCost);
      messUkIpCost.setValue(ipWidget.additionalProperties.messUkIpCost); 
      messCanadaIpCost.setValue(ipWidget.additionalProperties.messCanadaIpCost);
  } else if(listWidget.id == "snapCost") {
      messUsSnapCost.setValue(snapWidget.additionalProperties.messUsSnapCost);
      messUkSnapCost.setValue(snapWidget.additionalProperties.messUkSnapCost); 
      messCanadaSnapCost.setValue(snapWidget.additionalProperties.messCanadaSnapCost);
      
      usUnit.innerHTML = snapWidget.additionalProperties.usUnit;
      ukUnit.innerHTML = snapWidget.additionalProperties.ukUnit;
      canadaUnit.innerHTML = snapWidget.additionalProperties.canadaUnit;
  }
  }
function showSnapZoneCost(id) {
        var currentWidget = dojo.byId(id);
        if(currentWidget.id == "usSnapCost") {
            dojo.byId("usSnapUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } if(currentWidget.id == "ukSnapCost") {
            dojo.byId("ukSnapUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } if(currentWidget.id == "canadaSnapCost") {
            dojo.byId("canadaSnapUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } 
} 
 
  
  
function showServiceZoneCost(id) {
        var currentWidget = dojo.byId(id);
        if(currentWidget.id == "usServiceCost") {
            dojo.byId("usServiceUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } if(currentWidget.id == "serviceUkCost") {
            dojo.byId("ukServiceUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } if(currentWidget.id == "canadaServiceCost") {
            dojo.byId("canadaServiceUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } 
} 
  
//function showNetworkUkZoneCost() {
//    var ukWidget = dijit.byId("ukNetworkCost").getValue();
//    dojo.byId("ukNetworkUnit").innerHTML = (ukWidget/720).toFixed(8);
//}

function showNetworkZoneCost(id) {
        var currentWidget = dojo.byId(id);
         if(currentWidget.id == 'usNetworkCost') {
            dojo.byId("usNetworkUnit").innerHTML = (currentWidget.value/720).toFixed(8) ;
         } else if(currentWidget.id == 'ukNetworkCost') {
             dojo.byId("ukNetworkUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } else if(currentWidget.id == 'canadaNetworkCost') {
            dojo.byId("canadaNetworkUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        }
    } 

function showDiskZoneCost(id) {
        var currentWidget = dojo.byId(id);
//        alert(currentWidget.value);
        if(currentWidget.id == "usDiskCost") {
            dojo.byId("usDiskUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } if(currentWidget.id == "ukDiskCost") {
            dojo.byId("ukDiskUnit").innerHTML = (currentWidget.value/720).toFixed(8);
        } if(currentWidget.id == "canadaDiskCost") {
            dojo.byId("canadaDiskUnit").innerHTML = (currentWidget.value/720).toFixed(8);
       } 
} 
var count= 1;
  function gotoNextSlide() {
      dijit.byId("prevButton").setAttribute('disabled', false);
      count++;
      if(count == rotator_instance.panes.length) {
          dijit.byId("nextButton").setAttribute('disabled', true);
      } 


  } 
       
  
  function gotoPreviousSlide() {
      dijit.byId("nextButton").setAttribute('disabled', false);
      count--;
      if(count == 1) {
          dijit.byId("prevButton").setAttribute('disabled', true);
      }
  }
  function openPane(id) {
      var panWidget = dijit.byId(id);
      if (panWidget.id == "miscelZoneInfo") {
       dijit.byId("miscelZoneInfo").setAttribute('open', true);
       dijit.byId("miscelIpInfo").setAttribute('open', false);
       dijit.byId("miscelSnapInfo").setAttribute('open', false);
      } else if(panWidget.id == "miscelIpInfo") {
          dijit.byId("miscelZoneInfo").setAttribute('open', false);
          dijit.byId("miscelIpInfo").setAttribute('open', true);
          dijit.byId("miscelSnapInfo").setAttribute('open', false);
      }else if(panWidget.id == "miscelSnapInfo") {
          dijit.byId("miscelZoneInfo").setAttribute('open', false);
          dijit.byId("miscelIpInfo").setAttribute('open', false);
          dijit.byId("miscelSnapInfo").setAttribute('open', true);
      }
  }
  function postSystemConfig() {
  var systemNode = dojo.byId("systemConfigPage");
        var systemWidget = dijit.registry.findWidgets(systemNode);
        //var testing = null;
        dojo.forEach(systemWidget, function(el) {
        if (el.validate && !el.validate()) {
            el.focus();
            if (!testing) {
                testing  = el;
            }
        }
        });
        
//        if (firstNode) {
//            	firstNode.focus();
//            }
        
        var configNameValue = dojo.byId("cloudStackUrl");
        var configValue = dojo.byId("cloudApi");
        var configDescriptionValue = dojo.byId("cloudSecretKey"); 
        
        systemStore.put({
            id: "1",
            value: configNameValue.value,
            "class" : "com.assistanz.cloud.portal.Config"
        });
        systemStore.put({
            id: "2",
            value: configValue.value,
            "class" : "com.assistanz.cloud.portal.Config"
        });
        systemStore.put({
            id: "3",
            value: configDescriptionValue.value,
            "class" : "com.assistanz.cloud.portal.Config"
        });


   
  }
  
  function zoneListCheck(){
                  
   var zoneStore = new JsonRest({
    target: '/CloudPortal/api/zone/'
  });
  
  
  
  }
   