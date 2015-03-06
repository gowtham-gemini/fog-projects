 function returnCost(currentItemId) {
            var messUsCost = dijit.byId("mess_us_cost");
            var messUkCost = dijit.byId("mess_uk_cost");
            var messCanadaCost = dijit.byId("mess_canada_cost");
            
            messUsCost.reset();
            messUkCost.reset();
            messCanadaCost.reset();
                  
            var currentWidget  = dijit.byId(currentItemId);
            dojo.byId("cost_item_id").innerHTML = currentItemId;
            
            dijit.byId("mess_us_cost").setAttribute('constraints', {pattern: '0.00000000'});
            dijit.byId("mess_uk_cost").setAttribute('constraints', {pattern: '0.0000000'});
            dijit.byId("mess_canada_cost").setAttribute('constraints', {pattern: '0.0000000'});
            dojo.byId("details").style.display = "none";
            dijit.byId("mess_zone_info").set('style', { display: 'block'});                     
            dijit.byId("mess_zone_info").setAttribute('title', currentWidget.heading);

            dijit.byId('add_mess_cost').set('style', { display: 'block' });
            dojo.byId("us_unit_id").innerHTML = currentWidget.description;
            dojo.byId("uk_unit_id").innerHTML = currentWidget.description;
            dojo.byId("canada_unit_id").innerHTML = currentWidget.description;
            
            
             var miscellaneousListCollection = dojo.byId("miscellaneous_list_container");
            var miscellaneousListWidgets = dijit.registry.findWidgets(miscellaneousListCollection);

            dojo.forEach(miscellaneousListWidgets, function(el) {

         if (el.id == currentWidget.id ) {
           currentWidget.setAttribute("class", 'selectedItem');
            
          } else {
          el.setAttribute("class", 'unSelectItem');
             
          }
       });
           
           messUsCost.setValue(currentWidget.additionalProperties.messUsCost);
           messUkCost.setValue(currentWidget.additionalProperties.messUkCost); 
           messCanadaCost.setValue(currentWidget.additionalProperties.messCanadaCost); 
 }
  function addMessCost() {
      
            var messUsCost = dijit.byId("mess_us_cost");
            var messUkCost = dijit.byId("mess_uk_cost");
            var messCanadaCost = dijit.byId("mess_canada_cost");

            var zoneInfo = dojo.byId("mess_zone_info"); 
            var zoneInfoWidget = dijit.registry.findWidgets(zoneInfo);
           
            dojo.forEach(zoneInfoWidget, function(el) {
                if (el.validate && !el.validate()) {
                    el.focus();
                    if (!firstNode) {
                        firstNode = el;
                    }
                }
            });
      
             var add = { messUsCost: messUsCost.value, messUkCost: messUkCost.value, messCanadaCost: messCanadaCost.value };
      
            var currentWidgetId = dojo.byId("cost_item_id").innerHTML;
            var currentWidget = dijit.byId(currentWidgetId);
            currentWidget.additionalProperties = add;

            dijit.byId("mess_zone_info").set('style', { display: 'none'});
            dojo.byId("details").style.display = "block";


            messUsCost.reset();
            messUkCost.reset();
            messCanadaCost.reset();
            dijit.byId('add_mess_cost').set('style', { display: 'none' });
            alert(currentWidget.heading + " Added")
           
  }
  
  function setProps () {
       
            dijit.byId("mess_us_cost").setAttribute('constraints', {pattern: '000.00000000'});
            dijit.byId("mess_uk_cost").setAttribute('constraints', {pattern: '000.0000000'});
            dijit.byId("mess_canada_cost").setAttribute('constraints', {pattern: '000.0000000'});
  }