<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li> 
    <li>/</li>
    <li><a href="#/admin/dashboard/maintainance"><g:message code="menu.admin.cloud.maintenance"/></a></li>
    <li>/</li>
    <li><g:message code="common.add"/></li>
  </ul>
</div>
</div>
<div class="new-user">
  <div class="row-fluid header">
    <h3><g:message code="menu.admin.cloud.cloudMaintenance"/></h3>
  </div>
  <div class="row-fluid form-wrapper">
    <div class="span4 createvm-banner">
  	<img src="${resource(dir: 'css/theme/fog-classic/images/cloud_icons')}/maintenance_large_icon.png" height="256" width="300">
    </div>
    <div class="span6">
      <!--<div class="container">-->
        <form class="new_user_form form-horizontal" data-dojo-type="dijit.form.Form" id="cloudMaintenanceForm">
    <div class="span12 field-box control-group">
        <label for="maintenanceZoneList" class="control-label">          
          <g:message code="common.zone"/>:
          <span class="require">*</span>
        </label>
      <div class="controls">
        <select name="maintenanceZoneList" id="maintenanceZoneList" multiple="multiple"></select>
      </div>
    </div>       
    <div class="span5 field-box control-group">
      <label for="cloudMaintenanceDate" class="control-label">        
        <g:message code="common.maintainDate"/>:
        <span class="require">*</span>
      </label>
      <div class="controls">
      <input type="text" name="discountStartDate" id="cloudMaintenanceDate"
            data-dojo-type="dijit.form.DateTextBox"
            data-dojo-props="required:'true', placeHolder: '<g:message code="common.date.placeholder"/>',
            promptMessage:'<g:message code="common.date.placeholder"/>', invalidMessage:'<g:message code="common.date.invalid"/>', constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>
      </div>
      </div> 
  <div class="span5 field-box  control-group">
        <label for="cloudMaintenanceDateDescription" class="control-label">          
          <g:message code="common.description"/>
          <span class="require">*</span>
        </label>
    <div class="controls">
      <div data-dojo-type="dijit.form.Textarea" id="cloudMaintenanceDateDescription">
      </div>  
      </div>
      

  </div>   
  <div class="pull-right">                       
    <button class="defaultbtn" type="button" data-dojo-type= "dijit.form.Button" 
            onclick="CloudMaintenance.add()" id="maintainButton">
      <g:message code="common.add"/>   
    </button>
    <img id="forgotPasswordLoader" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="<g:message code="common.reset"/>" height="20" width="20">
  </div>
</form>     
    </div>
  </div>
</div>
