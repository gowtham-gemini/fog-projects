<div class="span11 breadcrumbs">
	<ul>
		<li><a href="#/admin/dashboard">Back to Dashboard</a></li>
		<li>/</li>
		<li>Form</li>
	</ul>
</div>

<div class="row-fluid">
  <div data-dojo-type="dojox.layout.ScrollPane" data-dojo-props='orientation:"vertical"' style="width:500px; height:50px;overflow:hidden;">
    <p>Test</p>
    <p>Test</p>
    <p>Test</p>
    <p>Test</p>
    <p>Test</p>
    <p>Test</p>
    <p>Test</p>
    <p>Test</p>
    
  </div>
  <div class="span8">
    <div class="control-group">       
        <label for="serviceName" class="control-label">
        Text Box: 
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.TextBox">
        </div>
        
      </div>
    <div class="control-group">       
        <label for="serviceName" class="control-label">
        Validation Text Box: 
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="" data-dojo-props="required: 'true',
                 invalidMessage: 'invalid Name', placeHolder: 'Name',regExp:'[a-zA-Z0-9- ]{4,200}',
                 promptMessage:'Enter Name'">
        </div>
        
      </div>
    <div class="control-group">       
        <label for="serviceName" class="control-label">
        Check Box:
        </label>
        <div class="controls">
          <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                  data-dojo-props="checked: false">
        </div>
        
      </div>
    <div class="control-group">   
      <label for="serviceName" class="control-label">
        Radio Button:
        </label>
      <div class="controls"> 
        <input type="radio" data-dojo-type="dijit.form.RadioButton" name="drink"
               value="coffee"/> 
      </div>
       
        
      </div>
    <div class="control-group">   
      <label for="serviceName" class="control-label">
        Select:
        </label>
      <div class="controls"> 
        <select data-dojo-type="dijit.form.Select">
          <option>value1</option>
          <option>value2</option>
          <option>value3</option>
          <option>value4</option> 
        </select>
      </div>
    </div>
    <div class="control-group">   
      <label for="serviceName" class="control-label">
        Filtering Select:
        </label>
      <div class="controls"> 
        <select data-dojo-type="dijit.form.FilteringSelect">
          <option>value1</option>
          <option>value2</option>
          <option>value3</option>
          <option>value4</option> 
        </select>
      </div>
    </div>
    <div class="control-group">   
      <label for="serviceName" class="control-label">
        Text Area:
        </label>
      <div class="controls"> 
        <textarea  data-dojo-type="dijit.form.Textarea">          
        </textarea>
      </div>
    </div>
    <div class="control-group">   
      <label for="serviceName" class="control-label">
        Spinner:
        </label>
      <div class="controls"> 
        <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
               data-dojo-props="required: 'true', invalidMessage: 'invalid Bandwidth', trim: 'true',
               placeHolder: 'CPU Speed', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
               value:1, regExp:'[0-9]{1,4}', promptMessage:'Enter Bandwidth'">
      </div>
    </div>
    <div class="control-group">   
      <label for="serviceName" class="control-label">
        Date Text box:
        </label>
      <div class="controls"> 
        <input type="text" name="date1" value="2005-12-30"
    data-dojo-type="dijit.form.DateTextBox"
    required="true" />
      </div>
    </div>
    <div class="control-group">   
      <label for="serviceName" class="control-label">
        Slider:
        </label>
      <div class="controls"> 
        <div data-dojo-type="dijit.form.HorizontalSlider"
        data-dojo-props="
            minimum: 0,
            maximum: 10,
            value: 3,
            discreteValues: 11"></div>
      </div>
    </div>
    <div class="control-group">   
      <label for="serviceName" class="control-label">
        Editor:
        </label>
      <div class="controls"> 
        <div data-dojo-type="dijit.Editor">
          
        </div>
      </div>
    </div>
    <div class="control-group">   
      <button type="button" data-dojo-type="dijit.form.Button">Dojo Button</button>
    </div>
  </div>
  	  <div id="testDataGrid" class="span10">
          
        </div>

</div>