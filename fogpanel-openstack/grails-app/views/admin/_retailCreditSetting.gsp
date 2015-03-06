<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
      <li>/<li>
      <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
      <li>/</li>
      <li><g:message code="common.retailSetting"/></li>    
    </ul>
  </div>
</div>
<div class="row-fluid">
  <ul class="nav nav-tabs span12">
    <li><a href="#/admin/settings/credit"><g:message code="common.trialSetting"/></a></li>
    <li class="active"><a href="#/admin/settings/retail"><g:message code="common.retailSetting"/></a></li>
    <li><a href="#/admin/settings/creditLimitNotification"><g:message code="common.creditLimitSetting"/></a></li>
  </ul>
</div>
<div id="" class="new-user">
  <div class="row-fluid header"></div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span6 with-sidebar">
      <div class="container">
        <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="retailManagementForm">            
          <div id="retailManagementFormPage">
            <div class="span12 field-box control-group">
              <label for="retailCreditLimit" class="control-label settings_lable">                          
                <g:message code="common.creditLimit"/> :
                <span class="require">*</span>
              </label>
              <div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                       data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', 
                       required: 'required', placeHolder: '<g:message code="common.enterValue"/>', 
                       promptMessage:'<g:message code="common.enterValue"/>', regExp:'[0-9]{1,200}'"
                       name="retailCreditLimit" id="retailCreditLimit">  
              </div>
            </div>            
            </div>
          <div class="pull-right">            
            <button id="retailManagemntButtton" class="defaultbtn" type="button" 
                    data-dojo-type="dijit.form.Button"  onclick="RetailManagement.updateShow()"><g:message code="common.update"/></button>
            <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>   
          </div>
        </form>
      </div>
    </div> 
    <div class="span6">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span id="retailCreditLimitDescription"><g:message code="admin.retailCreditInfo"/></span>
        </div>
        <div class="span12 field-box" style="display: none;">
          <span id="signupCardVerificationEnabledDescription"><g:message code="admin.trialEnableInfo"/></span>
        </div>        
      </div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="retailCreditLimitEditConformationDialog" class="span4">
    <p><g:message code="admin.updateItem"/></p> 
    <p class="alert alert-info"><g:message code="admin.updateRetailCreditLimitInfo"/></p>
    <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="RetailManagement.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="RetailManagement.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>