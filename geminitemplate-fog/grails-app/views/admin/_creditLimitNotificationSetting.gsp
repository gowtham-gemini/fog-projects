<div class="row">
  <div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
    <li>/</li>
    <li><g:message code="common.creditLimitSetting"/></li>    
  </ul>
</div>
</div>
<div class="row">
  <ul class="nav nav-tabs col-md-12">
  <li>
    <a href="#/admin/settings/credit"><g:message code="common.trialSetting"/></a>
  </li>
  <li>
    <a href="#/admin/settings/retail"><g:message code="common.retailSetting"/></a>
  </li>
    <li class="active">
      <a href="#/admin/settings/creditLimitNotification"><g:message code="common.creditLimitSetting"/></a>
    </li>
</ul>
</div>

<div id="" class="new-user">
  <div class="row header">
    <!--<h3>Credit Limit</h3>-->
  </div>
  <div class="row form-wrapper">
    <!-- left column -->
    <div class="col-md-6 with-sidebar">
      <div class="container">
        <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="creditLimitNotificationForm">
                  <!--<div class="col-md-12 field-box"></div>-->
                    <div id="creditLimitNotificationPage">
                    
                    <div class="col-md-12 field-box control-group">
                        <label for="creditLimitNotificationLevel1" class="control-label settings_lable">                          
                           <g:message code="common.Level1"/> :
                            <span  class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" 
                            data-dojo-type="dijit.form.NumberTextBox" 
                            data-dojo-props="invalidMessage: 'invalid No.',
                            required: 'required', placeHolder: 'Enter percentage', 
                            promptMessage:'Enter Percentage', constraints:{max: 100, pattern:'#.##'}" 
                            name="lateFeePercentage" id="creditLimitNotificationLevel1"> 
                      </div>
                    </div>
                    <div class="col-md-12 field-box control-group">
                        <label for="creditLimitNotificationLevel2" class="control-label settings_lable">                          
                           <g:message code="common.Level2"/> :
                            <span  class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" 
                          data-dojo-type="dijit.form.NumberTextBox" 
                          data-dojo-props="invalidMessage: 'invalid No.',
                          required: 'required', placeHolder: 'Enter percentage', 
                          promptMessage:'Enter Percentage', constraints:{max: 100, pattern:'#.##'}" 
                          name="lateFeePercentage" id="creditLimitNotificationLevel2"> 
                    </div>
                    </div>
                    <div class="col-md-12 field-box control-group">
                        <label for="creditLimitNotificationLevel3" class="control-label settings_lable">                           
                             <g:message code="common.Level3"/>:
                               <span  class="require">*</span>
                          </label>
                      <div class="controls">
                        <input type="text" 
                          data-dojo-type="dijit.form.NumberTextBox" 
                          data-dojo-props="invalidMessage: 'invalid No.',
                          required: 'required', placeHolder: 'Enter percentage', 
                          promptMessage:'Enter Percentage', constraints:{max: 100, pattern:'#.##'}" 
                          name="lateFeePercentage" id="creditLimitNotificationLevel3"> 
                    </div>
                    </div>
                    </div>
                    <div class="pull-right">
    <!--                    <input type="reset" value="Cancel" class="reset" onclick="CreditCardInfo.cancel()">
                          <span >OR</span>-->
                        <button id="creditLimitNotificationButtton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button"  onclick="CreditLimitNotification.update()"><g:message code="common.update"/></button>
                        <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>   
                    </div>
                </form>
            </div>
        </div> 
    <div class="col-md-6">
      <div class="new_user_form inline-input">
        <div class="col-md-12 field-box">
           <span  id=""><g:message code="admin.Level1Info"/></span>
        </div>
        <div class="col-md-12 field-box">
           <span  id=""><g:message code="admin.Level2Info"/></span>
        </div>
        <div class="col-md-12 field-box">
           <span  id=""><g:message code="admin.Level3Info"/></span>
        </div>
        
      </div>
    </div>
    </div>
</div>