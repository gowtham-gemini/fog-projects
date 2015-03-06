<%@ page contentType="text/html;charset=UTF-8" %>
 <h3> Template Page </h3>
<a href="#/admin/dashboard">Back to Dashboard</a>
<div class="alert alert-danger errorMessage" id="errorInfo">
  <span>
    The connection with the server was terminated
  </span>
</div>

<div class="span8" id="tempPage">
  <form id="dashboardAppTempForm" data-dojo-type="dijit.form.Form"
        class="form-horizontal">
  <div class="control-group">
    <label for="dashboardAppTempName" class="control-label">
      <span class="require">*</span>
      Name: 
    </label>
    <div class="control">
    <input type="text" 
             data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="invalidMessage: 'invalid Template Name',
             required: 'required', placeHolder: 'Enter Template Name', 
             regExp: '[a-zA-Z]{4,20}', propercase: true" 
             name="templateName" id="dashboardAppTempName">
    </div>
  </div>
  <div class="control-group">
    <label for="dashboardAppTempDescription" class="control-label">
      <span class="require">*</span>
      Description:
    </label>
    <div class="control">
      <div class="span6" data-dojo-type="dijit.form.ValidationTextBox" id="dashboardAppTempDescription"
       data-dojo-props="invalidMessage: 'invalid Description Name',
             required: 'required', placeHolder: 'Enter Description Name', 
             propercase: false" >         
      </div>
    </div>
  </div>    
  <div class = "control-group" id="urlWidget">
    <label for="dashboardAppTempURL" class="control-label">
      <span class="require">*</span>
      URL: 
    </label>
    <div class="control">
    <input type="url" data-dojo-type ="dijit.form.ValidationTextBox"
            id="dashboardAppTempURL" data-dojo-props="required: true,
            invalidMessage:'Invalid Url', trim:'true', placeHolder: 'Enter URL',
            regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+'">
    </div>
  </div>
  <div class="control-group">
     <label for="dashboardAppTempZone" class="control-label">
      <span class="require">*</span>
      Zone: 
    </label>
    <div class="control">
      <select id="dashboardAppTempZone"></select>
    </div>
    <span id="dashboardAppTempZoneLabel" class="result"></span>
  </div>
  <div class="control-group">
     <label for="dashboardAppTempHypervisor" class="control-label">
      <span class="require">*</span>
      Hyperviser: 
    </label>
    <div class="control" id="tempHyperviser">
      <select id="dashboardAppTempHypervisor"></select>
    </div>
    <span id="dashboardAppTempHypervisorLabel" class="result"></span>
  </div>
  <div class="control-group">
     <label for="dashboardAppTempFormat" class="control-label">
      <span class="require">*</span>
      Format: 
    </label>
    <div class="control" id="formatDiv">
      <select data-dojo-type="dijit.form.FilteringSelect"
              data-dojo-props="placeHolder: 'Select a Format'" 
              id="dashboardAppTempFormat" value = "VHD">
        <option  selected>VHD</option>
      </select>
    </div>
    <span id="dashboardAppTempFormatLabel" class="result"></span>
  </div>
    
    
  <div class="control-group">
     <label for="dashboardAppTempOsCategoryList" class="control-label">
      <span class="require">*</span>
      Os Category: 
    </label>
    <div class="control">
      <div id="dashboardAppTempOsCategoryList"></div>
    </div>
  </div>
  <div class="control-group">
     <label for="dashboardAppTempOsType" class="control-label">
       <span class="require">*</span>
       OS Type:
     </label>
    <div class="control">
      <div id="dashboardAppTempOsTypeList"></div>
    </div>
     <span id="dashboardAppTempOsTypeLabel" class="result"></span>
  </div>
  <div class="control-group">
    <label for="dashboardAppTempPasswordEnabled" class="control-label">
      Password Enabled:
    </label>
    <div class="control">
    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="dashboardAppTempPasswordEnabled">
    </div>
  </div> 
  <div class="control-group">
    <label for="dashboardAppTempExtractable" class="control-label">
      Extractable:
    </label>
    <div class="control">
    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="dashboardAppTempExtractable">
    </div>
  </div> 
  <div class="control-group">
    <label for="dashboardAppTempPublic" class="control-label">
      Public:
    </label>
    <div class="control">
    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="dashboardAppTempPublic">
    </div>
  </div>   
  <div class="control-group">
    <label for="dashboardAppTempFeatured" class="control-label">
      Featured:
    </label>
    <div class="control">
    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="dashboardAppTempFeatured">
    </div>
  </div>     
  <div class="control-group">
    <label for="dashboardAppTempCost" class="control-label">
      <span class="require">*</span>
      Cost: 
    </label>
    <div class="control">
    <input type="text" 
              data-dojo-type="dijit/form/NumberTextBox"
             data-dojo-props="invalidMessage: 'invalid cost',
             required: 'required', placeHolder: 'Enter cost'" 
             name="cost" id="dashboardAppTempCost">
    </div>
  </div>
  <div class="buttonGroup">
     <button data-dojo-type= "dijit.form.Button" 
              onclick="DashboardAppTemp.add()" id="dashboardAppTempAddButton">
      OK
    </button>
    <button data-dojo-type= "dijit.form.Button" 
         onclick="DashboardAppTemp.cancel()"  
         id="dashboardAppTempCancelButton">
    Cancel
    </button>
    <button onclick="DashboardAppTemp.update()" data-dojo-type="dijit.form.Button"
          id="dashboardAppTempUpdateButton" style="display: none">
    Apply
    </button>
  </div>
</form>
</div>
<div class="span4" id = "dashboardAppTempCollection">
  <div id="dashboardAppTempList"></div>
</div>
