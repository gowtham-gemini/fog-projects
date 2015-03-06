// function loadDojo() {  
        require([
        "dojo/_base/lang",
	
	"dojo/data/ItemFileWriteStore",
	"dojo/dom",
        "List/ListItem",
	"dojo/parser",
	"dojo/domReady!",
	"dojox/validate/regexp",
	"dojox/widget/Rotator",
	"dojox/widget/rotator/Pan",
	"dijit/dijit",
	"dijit/form/Button",
	"dijit/form/ValidationTextBox",
	"dijit/TitlePane",
        "dojo/query",     
        "dijit/form/CheckBox",
        "dijit/form/NumberTextBox",
        "dijit/Tooltip"
	]);
    function addNewService() {

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
            var serviceName = dijit.byId("service_name");
            var serviceDescription = dijit.byId("service_description");

            var cpuNumber = dijit.byId("cpu_number");
            var cpuSpeed = dijit.byId("cpu_speed");
            var cpuMemory = dijit.byId("cpu_memory");
            
            var offerHA = dijit.byId("offer_ha");
            var CPUCap = dijit.byId("cpu_cap");
            var networkRate = dijit.byId("service_network_rate");
            var hostTag = dijit.byId("host_tag");
            var storageTag = dijit.byId("storage_tag");
            var usCost = dijit.byId("service_us_cost");
            var ukCost = dijit.byId("service_uk_cost");
            var canadaCost = dijit.byId("service_canada_cost");
            var usSetupCost = dijit.byId("us_setup_cost");
            var ukSetupCost = dijit.byId("uk_setup_cost");
            var canadaSetupCost = dijit.byId("canada_setup_cost");
            
            var add = {
                cpuNumber: cpuNumber.value,
                cpuSpeed: cpuSpeed.value / 1000, 
                serviceDescription: serviceDescription.value,
                cpuMemory: cpuMemory.value / 1024,
                serviceName: serviceName.value,
                networkRate: networkRate.value, 
                hostTag: hostTag.value,
                storageTag: storageTag.value,
                offerHa: offerHA.checked, 
                cpuCap: CPUCap.checked,
                usCost: usCost.value,
                ukCost: ukCost.value,
                canadaCost: canadaCost.value,
                usSetupCost: usSetupCost.value,
                ukSetupCost: ukSetupCost.value,
                canadaSetupCost: canadaSetupCost.value
            };
           
             window.serviceListItem = new List.ListItem({
                 
                    heading: "heading",
                    description: "description",
                    onClick: "returnServiceData(this.id);"
            });
            
            serviceListItem.placeAt("serviceList");
            
            serviceListItem.startup();
            
           
            serviceListItem.additionalProperties = add;
            serviceListItem.additionalProperties.heading = serviceName.value;
            serviceListItem.additionalProperties.description = cpuNumber.value + "Core/" + cpuSpeed.value/1000 + "GHz/" + Math.round((cpuMemory.value / 1024)*100)/100 + "GB RAM";
            serviceListItem.getData();
            
           
            CPUCap.reset();
            offerHA.reset();
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
        }
        
        
        function returnServiceData(currentItemId) {
            
            var currentWidget  = dijit.byId(currentItemId);
            
            var serviceName = dijit.byId("service_name");
            var serviceDescription = dijit.byId("service_description");

            var cpuNumber = dijit.byId("cpu_number");
            var cpuSpeed = dijit.byId("cpu_speed");
            var cpuMemory = dijit.byId("cpu_memory");
            
            var offerHa = dijit.byId("offer_ha");
            var cpuCap = dijit.byId("cpu_cap");
            var networkRate = dijit.byId("service_network_rate");
            var hostTag = dijit.byId("host_tag");
            var storageTag = dijit.byId("storage_tag");
            var usCost = dijit.byId("service_us_cost");
            var ukCost = dijit.byId("service_uk_cost");
            var canadaCost = dijit.byId("service_canada_cost");
            var usSetupCost = dijit.byId("us_setup_cost");
            var ukSetupCost = dijit.byId("uk_setup_cost");
            var canadaSetupCost = dijit.byId("canada_setup_cost");

            serviceName.setValue(currentWidget.additionalProperties.heading);
            serviceDescription.setValue(currentWidget.additionalProperties.serviceDescription);
            cpuNumber.setValue(currentWidget.additionalProperties.cpuNumber);
            networkRate.setValue(currentWidget.additionalProperties.networkRate);
            storageTag.setValue(currentWidget.additionalProperties.storageTag);
            cpuSpeed.setValue(currentWidget.additionalProperties.cpuSpeed*1000);
            cpuMemory.setValue(currentWidget.additionalProperties.cpuMemory*1024);
            hostTag.setValue(currentWidget.additionalProperties.hostTag);
            offerHa.setValue(currentWidget.additionalProperties.offerHa);
            cpuCap.setValue(currentWidget.additionalProperties.cpuCap);
            usCost.setValue(currentWidget.additionalProperties.usCost);
            usSetupCost.setValue(currentWidget.additionalProperties.usSetupCost);
            ukCost.setValue(currentWidget.additionalProperties.ukCost);
            ukSetupCost.setValue(currentWidget.additionalProperties.ukSetupCost);
            canadaCost.setValue(currentWidget.additionalProperties.canadaCost);
            canadaSetupCost.setValue(currentWidget.additionalProperties.canadaSetupCost);
                                   
            dijit.byId("service_item_id").innerHTML = currentWidget;
          
            dijit.byId("cpu_number").set('style', {display: 'none'});
            dijit.byId("cpu_speed").set('style', {display: 'none'});
            dijit.byId("cpu_memory").set('style', {display: 'none'});
            dijit.byId("storage_tag").set('style', {display: 'none'});
            dijit.byId("offer_ha").set('style', {display: 'none'});
            dijit.byId("cpu_cap").set('style', {display: 'none'});
            dijit.byId("service_network_rate").set('style', {display: 'none'});
            dijit.byId("host_tag").set('style', {display: 'none'});
            
            
            dojo.byId("cpu_number_label").innerHTML = cpu_number.value + " Core";
            dojo.byId("cpu_number_label").style.display = "block";
            dojo.byId("cpu_speed_label").innerHTML = cpu_speed.value / 1000 + " GHz";
            dojo.byId("cpu_speed_label").style.display = "block";
            dojo.byId("cpu_memory_label").innerHTML = Math.round((cpu_memory.value / 1024)*100)/100 + " GB";
            dojo.byId("cpu_memory_label").style.display = "block";
            if(offerHa.checked==true){
                dojo.byId("offer_ha_label").innerHTML = "yes";
            }else{
                dojo.byId("offer_ha_label").innerHTML = "no";
            }
            
            dojo.byId("offer_ha_label").style.display = "block";
            if(cpuCap.checked==true){
                dojo.byId("cpu_cap_label").innerHTML = "yes";
            }else{
                dojo.byId("cpu_cap_label").innerHTML = "no";
            }
            
            dojo.byId("cpu_cap_label").style.display = "block";
            if(networkRate.value==""){
                dojo.byId("network_rate_label").innerHTML = "......";
            }else{
               dojo.byId("network_rate_label").innerHTML = networkRate.value + "MB/s"; 
            }
            dojo.byId("network_rate_label").style.display = "block";
            if(storageTag.value == "") {
                dojo.byId("storage_tag_Label").innerHTML = "......";
            } else {
               dojo.byId("storage_tag_Label").innerHTML = storageTag.value;
            }
            
            dojo.byId("storage_tag_Label").style.display = "block";
             if(hostTag.value == "") {
                dojo.byId("host_tag_label").innerHTML = "......";
            } else {
               dojo.byId("host_tag_label").innerHTML = hostTag.value;
            }
            
            dojo.byId("host_tag_label").style.display = "block";
            
             window.serviceListCollection = dojo.byId("serviceList");
             window.serviceListWidgets = dijit.registry.findWidgets(serviceListCollection);

            var serviceListPageItem = dijit.byId("service_item_id").innerHTML;

            dojo.forEach(serviceListWidgets, function(el) {

            if (el.id == serviceListPageItem.id ) {
                serviceListPageItem.setAttribute("class", 'selectedItem');
            
            } else {
                el.setAttribute("class", 'unSelectItem');
           }
       });
            
            
            dijit.byId('addNewService').set('style', { display: 'none' });
            dijit.byId('updateService').set('style', { display: 'block' });
    }
        
        
        /***
         * To update the form
         */
        function updateService() {
    
          serviceWidgets = dijit.registry.findWidgets(serviceNode);
//        var serviceNode1 = null
           dojo.forEach(serviceWidgets, function(widgetElement) {
                if (widgetElement.validate && !widgetElement.validate()) {
                    widgetElement.focus();
                    if (!firstNode) {
                        firstNode = widgetElement;
                    }
                }
            });

            serviceZoneInfoWidget = dijit.registry.findWidgets(serviceZoneInfo);
            dojo.forEach(serviceZoneInfoWidget, function(zoneElement) {
                if (zoneElement.validate && !zoneElement.validate()) {
                    zoneElement.focus();
                    if (!firstNode) {
                        firstNode = zoneElement;
                    }
                }
            });
            
            var cpuNumber = dijit.byId("cpu_number");
            var cpuSpeed = dijit.byId("cpu_speed");
            var serviceDescription = dijit.byId("service_description");
            var cpuMemory = dijit.byId("cpu_memory");
            var serviceName = dijit.byId("service_name");
            var offerHA = dijit.byId("offer_ha");
            var cpuCap = dijit.byId("cpu_cap");
            var networkRate = dijit.byId("service_network_rate");
            var storageTag = dijit.byId("storage_tag");
            var hostTag = dijit.byId("host_tag");
            var usCost = dijit.byId("service_us_cost");
            var ukCost = dijit.byId("service_uk_cost");
            var usSetupCost = dijit.byId("us_setup_cost");
            var canadaCost = dijit.byId("service_canada_cost");
            var ukSetupCost = dijit.byId("uk_setup_cost");
            var canadaSetupCost = dijit.byId("canada_setup_cost");
            
            var currentListWidget = dijit.byId("service_item_id").innerHTML;
            
            currentListWidget.additionalProperties.heading = serviceName.value;
            currentListWidget.additionalProperties.serviceDescription = serviceDescription.value;           
            currentListWidget.additionalProperties.usCost = usCost.value;
            currentListWidget.additionalProperties.usSetupCost = usSetupCost.value;
            currentListWidget.additionalProperties.ukCost = ukCost.value;
            currentListWidget.additionalProperties.ukSetupCost = ukSetupCost.value;
            currentListWidget.additionalProperties.canadaCost = canadaCost.value;
            currentListWidget.additionalProperties.canadaSetupCost = canadaSetupCost.value;
                                 
            currentListWidget.getData();
            
            dijit.byId("cpu_number").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("cpu_speed").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("cpu_memory").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("storage_tag").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("offer_ha").set('style', { display: 'block',  margin: '0 0 0 155px'});
            dijit.byId("cpu_cap").set('style', { display: 'block',  margin: '0 0 0 155px'});
            dijit.byId("service_network_rate").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("host_tag").set('style', { display: 'block',  margin: '0 0 0 155px' });
          
            dojo.byId("cpu_number_label").style.display = "none";
            dojo.byId("cpu_speed_label").style.display = "none";
            dojo.byId("cpu_memory_label").style.display = "none";
            dojo.byId("storage_tag_Label").style.display = "none";
            dojo.byId("offer_ha_label").style.display = "none";
            dojo.byId("cpu_cap_label").style.display = "none";
            dojo.byId("network_rate_label").style.display = "none";
            dojo.byId("host_tag_label").style.display = "none";
           
            dijit.byId('addNewService').set('style', { display: 'block' });  
            dijit.byId('updateService').set('style', { display: 'none' });
                        
//            serviceListCollection = dojo.byId("serviceList");
           serviceistWidgets = dijit.registry.findWidgets(serviceListCollection);
//              var list_pageItem = dijit.byId("itemId").innerHTML;
            dojo.forEach(serviceistWidgets, function(el) {
           el.setAttribute("class", 'unSelectItem');
             
          });            
                    
                        
            cpuCap.reset();
            offerHA.reset();
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

        }
        
         
    
    count = 1;
    
    /**
     * Next count Logic for dojo Rotator pan
     */
//    function nextCount() {
//
//        count++;
//        if (count >= myRotatorInstance.panes.length) {
//            dijit.byId("next").setAttribute('disabled', true);
//            dijit.byId("finish").setAttribute('disabled', false);
//
//        } else {
//            dijit.byId("pre").setAttribute('disabled', false);
//        }
//
//    }
//    
//    /**
//     * Previous count Logic for dojo Rotator pan
//     */
//    function preCount() {
//        count--
//        dijit.byId("next").setAttribute('disabled', false);
//        if (count == 1) {
//            dijit.byId("pre").setAttribute('disabled', true);
//        }
//    }
//    
    /**
     * cancel form 
     */
    function cancelService() {
            
            var cpuNumber = dijit.byId("cpu_number");
            var cpuSpeed = dijit.byId("cpu_speed");
            var serviceDescription = dijit.byId("service_description");
            var cpuMemory = dijit.byId("cpu_memory");
            var serviceName = dijit.byId("service_name");
            var offerHA = dijit.byId("offer_ha");
            var cpuCap = dijit.byId("cpu_cap");
            var networkRate = dijit.byId("service_network_rate");
            var storageTag = dijit.byId("storage_tag");
            var hostTag = dijit.byId("host_tag");
            var usCost = dijit.byId("service_us_cost");
            var ukCost = dijit.byId("service_uk_cost");
            var usSetupCost = dijit.byId("us_setup_cost");
            var canadaCost = dijit.byId("service_canada_cost");
            var ukSetupCost = dijit.byId("uk_setup_cost");
            var canadaSetupCost = dijit.byId("canada_setup_cost");
        
            serviceistWidgets = dijit.registry.findWidgets(serviceListCollection);
            dojo.forEach(serviceistWidgets, function(el) {
            el.setAttribute("class", 'unSelectItem');
            });
        
            
            cpuCap.reset();
            offerHA.reset();
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
            
        
            dijit.byId("service_name").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("service_description").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("cpu_number").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("cpu_speed").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("cpu_memory").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("storage_tag").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("offer_ha").set('style', { display: 'block',  margin: '0 0 0 155px'});
            dijit.byId("cpu_cap").set('style', { display: 'block',  margin: '0 0 0 155px'});
            dijit.byId("service_network_rate").set('style', { display: 'block',  margin: '0 0 0 155px' });
            dijit.byId("host_tag").set('style', { display: 'block',  margin: '0 0 0 155px' });
           
          
            dojo.byId("cpu_number_label").style.display = "none";
            dojo.byId("cpu_speed_label").style.display = "none";
            dojo.byId("cpu_memory_label").style.display = "none";
            dojo.byId("storage_tag_Label").style.display = "none";
            dojo.byId("offer_ha_label").style.display = "none";
            dojo.byId("cpu_cap_label").style.display = "none";
            dojo.byId("network_rate_label").style.display = "none";
            dojo.byId("host_tag_label").style.display = "none";
       
        dijit.byId('addNewService').set('style', { display: 'block' });
        dijit.byId('updateService').set('style', { display: 'none' });


    }




