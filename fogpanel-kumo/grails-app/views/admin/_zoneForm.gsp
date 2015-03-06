<form style="height: auto" id="zone_form" data-dojo-type="dijit.form.Form" data-dojo-id="zoneForm">
  <h2>zone - edit</h2>
  <a href="#/admin/dashboard">Back to Dashboard</a>
  <span name="id" style="display: none" id="zone_id"></span>
  <div class="span8">
      <label for="zone_name">
        <span class="require">*</span>
        Zone Name:
      </label>                
      <input type="text" name="zoneName" data-dojo-id="name" id="zone_name"
              required="true" data-dojo-type="dijit.form.ValidationTextBox" 
              placeholder="type Name" trim="true">         
      <label for="zone_description">
        <span class="require">*</span>
        Zone Description:
      </label>      
      <div name="zoneDescription" data-dojo-props="height: '200px'" data-dojo-type="dijit.Editor" id="zone_description" required="true" placeholder="type Description">
      </div>        
      <button data-dojo-type="dijit.form.Button" onclick="update()">Save</button>
      <button data-dojo-type="dijit.form.Button" onclick="cancel()">cancel</button>
  </div>
  <div class="span4">
  <div></div>
</div>
</form>