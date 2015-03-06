 <%@ page contentType="text/html;charset=UTF-8" %>
<h3> Template Page </h3>
<a href="#/user/dashboard">Back to Dashboard</a>
<div class="alert alert-danger" id="errorInfo">
  <span>
    The connection with the server was terminated
  </span>
</div>
<form id="userIsoForm" data-dojo-type="dijit.form.Form">
  <div class="span8" id="UserIsoPage">
    <div class="element"> 
      <label for="userDashboardIsoName">
        <span class="require">*</span>
        Name
      </label>
      <input type="text" 
             data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="invalidMessage: 'invalid isoName',
             required: 'required', placeHolder: 'Enter isoName', 
             regExp: '[a-zA-Z]{4,20}', propercase: true" 
             name="isoName" id="dashboardIsoName">
    </div>
    <div class="element">
      <label for="userDashboardIsoDescription">
        <span class="require">
          *
        </span>
        Description
      </label>
      <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
             data-dojo-props="promptMessage:'Enter Description here', 
             invalidMessage: 'invalid Description', required: 'required',
             placeHolder: 'Enter Description', regExp: '[|a-z0-9A-Z,]{4,20}'"
             name="displayText" id="userDashboardIsoDescription">
    </div>
    <div class="element">
      <label for="userDashboardIsoUrl">
      <span class="require">
        *
      </span>
        URL
      </label>
      <input type="url" data-dojo-type ="dijit.form.ValidationTextBox"
             id="userDashboardIsoUrl" data-dojo-props="required: true,
             invalidMessage:'Invalid Url', trim:'true', placeHolder: 'Enter URL',
             regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+'">
    </div>
    <div class="element">
      <label for="userDashboardIsoZone">Zone:</label>
      <select id="userDashboardIsoZone"></select>
      <span id="userDashboardIsoZoneLabel" class="result"></span>
    </div> 
    <div class="element">
      <label for="userDashboardIsoOsType">OS Type:</label>
      <input id="userDashboardIsoOsType">
      <span id="userDashboardIsoOsTypeLabel" class="result"></span>
    </div>  
    <div class="element">
      <label for="dashboardTempExtractable">Extractable</label>
      <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="dashboardTempExtractable">
    </div>
    <div class="element">
      <label for="dashboardTempPasswordEnabled">Password Enabled</label>
      <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="dashboardTempPasswordEnabled">
    </div>
    <div class="element">
      <label for="dashboardTempPublic">Public</label>
      <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="dashboardTempPublic">
    </div>
    <div class="element">
      <label for="dashboardTempPublic">Bootable</label>
      <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="userDashboardIsoBootable">
    </div>
    <div class="element">
      <label for="dashboardTempFeatured">Featured</label>
      <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="dashboardTempFeatured">
    </div>
    <div class="buttonGroup">
      <button data-dojo-type= "dijit.form.Button" 
              onclick="DashboardTemp.add()" id="dashboardTempAddButton">
        OK
      </button>
      <button data-dojo-type= "dijit.form.Button" 
               onclick="DashboardTemp.cancel()"  
               id="dashboardTempCancelButton">
        Cancel
      </button>
      <button onclick="DashboardTemp.update()" data-dojo-type="dijit.form.Button"
            id="dashboardTempUpdateButton" style="display: none">
      Apply
    </button>
     </div>
  </div>
</form>
<div class="span4" id = "dashboardTempCollection">
  <div id="dashboardTempList"></div>
</div>
