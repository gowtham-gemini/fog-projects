<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
    <li>/</li>
    <li><g:message code="common.trialSetting"/></li>    
  </ul>
</div>
</div>
<div class="row-fluid">
  <ul class="nav nav-tabs span12">
  <li class="active">
    <a href="#/admin/settings/credit"><g:message code="common.trialSetting"/></a>
  </li>
  <li>
    <a href="#/admin/settings/retail"><g:message code="common.retailSetting"/></a>
  </li>
    <li>
      <a href="#/admin/settings/creditLimitNotification"><g:message code="common.creditLimitSetting"/></a>
    </li>
</ul>
</div>

<div id="" class="new-user">
  <div class="row-fluid header">
    <!--<h3>Credit Limit</h3>-->
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span6 with-sidebar">
      <div class="container">
        <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="creditForm">
                  <!--<div class="span12 field-box"></div>-->
                   <div id="creditInfoPage">
                    <div class="span12 field-box control-group">
                      <label for="trialEnabled" class="control-label settings_lable">
                            <g:message code="common.trialEnabled"/> :
                        </label>
                      <div class="controls">
                         <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="trialEnabled" onClick="GeneralConfig.enableTrialAccountType(this)"> 
                    </div>
                    </div>
                         <div class="span12 field-box control-group" id="trialLimit">
                        <label for="trialCreditLimit" class="control-label settings_lable">                          
                          <g:message code="common.trialCreditLimit"/> :
                          <span class="require">*</span>
                        </label>
                            <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Credit Limit', promptMessage:'Enter Credit Limit', regExp:'[0-9]{1,200}'" name="trialCreditLimit" id="trialCreditLimit">
                    </div>
                         </div>
                    <div class="span12 field-box control-group">
                        <label for="instanceLimit" class="control-label settings_lable">                          
                          <g:message code="common.trialInstanceLimit"/> :
                          <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Instance Limit', promptMessage:'Enter Instance Limit', regExp:'[0-9]{1,200}'" name="instanceLimit" id="instanceLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="storageLimit" class="control-label settings_lable">                         
                          <g:message code="common.trialStorageLimit"/> :
                           <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Storage Limit', promptMessage:'Enter Storage Limit', regExp:'[0-9]{1,200}'" name="StorageLimit" id="storageLimit">
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="snapshotLimit" class="control-label settings_lable">                        
                          <g:message code="common.trialSnapLimit"/> :
                            <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Snapshot Limit', promptMessage:'Enter Snapshot Limit', regExp:'[0-9]{1,200}'" name="snapshotLimit" id="snapshotLimit">
                    </div>
                    </div>
                    <div class="span12 field-box  control-group">
                        <label for="bandwidthLimit" class="control-label settings_lable">                          
                          <g:message code="common.trialBandLimit"/> :
                          <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Bandwidth Limit', promptMessage:'Enter Bandwidth Limit', regExp:'[0-9]{1,200}'" name="bandwidthLimit" id="bandwidthLimit">
                    </div>
                    </div>
                   </div> 
                    
                    
<!--                    <div class="span12 field-box">
                        <label>Percentage(%):</label>
                        <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                          data-dojo-props="promptMessage:'Enter Tax percentage',
                          invalidMessage: 'invalid size',constraints:{min:0,max:100,pattern:'#'}, 
                          timeoutChangeRate: '0.2',value:1" id="adminTaxPercentage">
                    </div>-->
                      
                   
                    <div class="pull-right">
    <!--                    <input type="reset" value="Cancel" class="reset" onclick="CreditCardInfo.cancel()">
                         <span>OR</span>-->
                        <button id="creditButtton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button"  onclick="CreditCardInfo.update()"><g:message code="common.update"/></button>
                        <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>   
                    </div>
                </form>
            </div>
        </div> 
    <div class="span6">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span id="trialEnabledDescription"><g:message code="admin.trialEnableInfo"/></span>
        </div>
        <div class="span12 field-box" id="trialCreditLimitDescriptionDiv">
          <span id="trialCreditLimitDescription"><g:message code="admin.trialCreditInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id=""><g:message code="admin.trialIntanceInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id=""><g:message code="admin.trialStorageInfo"/> </span>
        </div>
        <div class="span12 field-box">
          <span id=""><g:message code="admin.trialSnapInfoInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id=""><g:message code="admin.trialBandInfoInfo"/> </span>
        </div>        
      </div>
    </div>
    </div>
</div>