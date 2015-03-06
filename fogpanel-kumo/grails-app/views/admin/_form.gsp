<form id="configForm" data-dojo-type="dijit.form.Form" data-dojo-id="configForm">
<div>
  <h1>Config - Add</h1>
  <a href="#/admin/config/index">Back</a>
  {% if param.id %}
  <input name="id" value="{{ param.id }}" type="hidden" />
  {% endif %}
  {% if param.version %}
  <input name="version" value="{{ param.version }}" type="hidden" />
  {% endif %} 
  <br /><br />
  Config Name:                  
  <input type="text" name="name" data-dojo-id="name" id="name"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="type Title" trim="true" value="{{ param.name }}"/>
  <br /><br />      
  Config Value:                  
  <input type="text" name="value" data-dojo-id="value" id="value"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="type Value" trim="true" value="{{ param.value }}"/>
    <br /><br />     
    Config Description:                  
    <input type="text" name="description" data-dojo-id="description" id="description"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="type Description" trim="true" value="{{ param.description }}"/>
    <br/><br/>     
    <br/><br/>
    <button data-dojo-type="dijit.form.Button" 
            onclick="config.save('{{ param.id }}')">
      Save
    </button>
</div>
</form>