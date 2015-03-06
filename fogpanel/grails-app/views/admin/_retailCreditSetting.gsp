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
                       promptMessage:'<g:message code="common.enterValue"/>', regExp:'[0-9-]{1,200}'"
                       name="retailCreditLimit" id="retailCreditLimit">  
              </div>
            </div>

            <div class="span12 field-box control-group">
                <label for="enableThresholdLimit" class="control-label settings_lable">                          
                    <g:message code="common.enableThresholdLimit"/> :
                </label>
                <div class="controls">
                    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="enableThresholdLimit" onClick="GeneralConfig.enableThresholdLimitChange(this)">  
                </div>
            </div>
                <div id="enableThresholdLimitDefaultValue" style="display: none;">
<!--                    <div class="span12 field-box">
                    <div class="control-label settings_lable" style="text-justify: auto">
                        <g:message code="common.enableThresholdLimitHead"/>
                    </div></div>
                    <hr>-->
                    <div class="span12 field-box control-group">
                        <label for="retailInstanceLimit" class="control-label settings_lable">                          
                          <g:message code="common.retailInstanceLimit"/> :
                          <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Instance Limit', promptMessage:'Enter Instance Limit', regExp:'[0-9-]{1,200}'" name="retailInstanceLimit" id="retailInstanceLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="retailStorageLimit" class="control-label settings_lable">                         
                          <g:message code="common.retailStorageLimit"/> :
                           <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Storage Limit', promptMessage:'Enter Storage Limit', regExp:'[0-9-]{1,200}'" name="retailStorageLimit" id="retailStorageLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="retailSnapshotLimit" class="control-label settings_lable">                        
                          <g:message code="common.retailSnapshotLimit"/> :
                            <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Snapshot Limit', promptMessage:'Enter Snapshot Limit', regExp:'[0-9-]{1,200}'" name="snapshotLimit" id="retailSnapshotLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="retailNetworkLimit" class="control-label settings_lable">                        
                            <g:message code="common.retailNetworkLimit"/> :
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Network Limit', promptMessage:'Enter Network Limit', regExp:'[0-9-]{1,200}'" name="retailNetworkLimit" id="retailNetworkLimit">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="retailPublicIPLimit" class="control-label settings_lable">                        
                          <g:message code="common.retailPublicIPLimit"/> :
                            <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Public IP Limit', promptMessage:'Enter Public IP Limit', regExp:'[0-9-]{1,200}'" name="retailPublicIPLimit" id="retailPublicIPLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="retailVpcLimit" class="control-label settings_lable">                        
                          <g:message code="common.retailVpcLimit"/> :
                            <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter VPC Limit', promptMessage:'Enter VPC Limit', regExp:'[0-9-]{1,200}'" name="retailVpcLimit" id="retailVpcLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="retailCpuLimit" class="control-label settings_lable">                        
                          <g:message code="common.retailCpuLimit"/> :
                            <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter CPU Limit', promptMessage:'Enter CPU Limit', regExp:'[0-9--]{1,200}'" name="retailCpuLimit" id="retailCpuLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="retailMemoryLimit" class="control-label settings_lable">                        
                          <g:message code="common.retailMemoryLimit"/> :
                            <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Memory Limit', promptMessage:'Enter Memory Limit', regExp:'[0-9-]{1,200}'" name="retailMemoryLimit" id="retailMemoryLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="primaryStorageLimit" class="control-label settings_lable">                        
                          <g:message code="common.primaryStorageLimit"/> :
                            <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Primary Storage Limit', promptMessage:'Enter Primary Storage Limit', regExp:'[0-9-]{1,200}'" name="primaryStorageLimit" id="primaryStorageLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="secondaryStorageLimit" class="control-label settings_lable">                        
                          <g:message code="common.secondaryStorageLimit"/>:<span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Secondary Storage Limit', promptMessage:'Enter Secondary Storage Limit', regExp:'[0-9-]{1,200}'" name="secondaryStorageLimit" id="secondaryStorageLimit">
                        </div>
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
        <div class="span12 field-box">
          <span id="enableThresholdLimitDescription"><g:message code="admin.enableThresholdLimit"/></span>
        </div>
        <div id="resoueceThresould" style="display: none;">
            <div class="span12 field-box">
              <span id="retailInstanceLimitDescription"><g:message code="admin.retailInstanceLimitDescription"/></span>
            </div>
            <div class="span12 field-box">
              <span id="retailStorageLimitDescription"><g:message code="admin.retailStorageLimitDescription"/></span>
            </div>
            <div class="span12 field-box">
              <span id="retailSnapshotLimitDescription"><g:message code="admin.retailSnapshotLimitDescription"/></span>
            </div>
            <div class="span12 field-box">
              <span id="retailPublicIPLimitDescription"><g:message code="admin.retailPublicIPLimitDescription"/></span>
            </div>
            <div class="span12 field-box">
              <span id="retailVpcLimitDescription"><g:message code="admin.retailVpcLimitDescription"/></span>
            </div>
            <div class="span12 field-box">
              <span id="retailCpuLimitDescription"><g:message code="admin.retailCpuLimitDescription"/></span>
            </div>
            <div class="span12 field-box">
              <span id="retailMemoryLimitDescription"><g:message code="admin.retailMemoryLimitDescription"/></span>
            </div>
            <div class="span12 field-box">
              <span id="primaryStorageLimitDescription"><g:message code="admin.primaryStorageLimitDescription"/></span>
            </div>
            <div class="span12 field-box">
              <span id="secondaryStorageLimitDescription"><g:message code="admin.secondaryStorageLimitDescription"/></span>
            </div>
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
<div data-dojo-type="dijit.Dialog" id="retailCreditLimitEditConformationLimitDialog" class="span4">
    <p><g:message code="admin.updateRetailCreditResourceLimitInfo"/></p> 
    <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="RetailManagement.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="RetailManagement.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>