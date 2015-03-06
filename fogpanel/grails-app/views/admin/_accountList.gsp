<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><g:message code="common.accounts"/></li>
    </ul>
  </div>
</div>
<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
        <div class="span4 stat">
            <div class="data">
                <span class="number" id="adminTotalAc"></span>
                 <g:message code="stat.totalAccount"/>
            </div>
           
        </div>
        <div class="span4 stat">
            <div class="data">
              <span class="number" id="adminRetailAc"></span>
                <g:message code="stat.retailAccount"/>
            </div>
            
        </div>
        <div class="span4 stat">
            <div class="data">
              <span class="number" id="adminTrialAc"></span>
               <g:message code="stat.trialAccount"/>
            </div>            
        </div>       
    </div>
</div>
</div>
<div class="row-fluid">
  <div id="pad-wrapper">
  <div class="table-wrapper products-table">   
    <div class="row-fluid head">
        <div class="span12">
            <!--<h4>Accounts</h4>-->
        </div>
        <div class="pull-right" id="createVmButtom">
            <a class="btn-flat success new-product" href="#/admin/account/invitation" title="<g:message code='common.invitation'/>"><g:message code="common.invitation"/></a>        
        </div>
    </div>
    <div class="row-fluid">
      <div id="accountGrid"></div>
      <div class="alert alert-info hide" id="noClientMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.admin.noClientFound"/>
      </div>
    </div>   
  </div>
</div>
</div>   
<input type="hidden" id="accountId">

<div data-dojo-type="dijit.Dialog" class="full_loader" id="accountProcessLoader" class="span6">
    <div class="row-fluid" id="processPaymentMessage" style="display: block">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="lockDialog" 
               title="Lock Account" style="color: black; width: 350px; 
               height: 150px">
  <g:message code="common.admin.lockAccount"/>
  <div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="AccountList.lockAccount()" id="" >
      <g:message code="common.yes"/>
    </button>
    <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
              onclick="AccountList.closeLockAccountDialog()">
      <g:message code="common.no"/>
    </button> 
  </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="unLockDialog" 
               title="UnLock Account" style="color: black; width: 350px;">
  <g:message code="common.admin.unLockAccount"/>
  <div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="AccountList.unLockAccount()" id="" >
      <g:message code="common.yes"/>
    </button>
    <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
              onclick="AccountList.closeUnLockAccountDialog()">
      <g:message code="common.no"/>
    </button> 
  </div>  
</div>

<div data-dojo-type="dijit.Dialog" id="resetPasswordDialog" title="<g:message code="common.resetPassword"/>">
<g:message code="common.admin.resetPassword"/>  
  <div class="new-user"> 
    <div class="row-fluid form-wrapper"> 
      <div class="span12"> 
        <form data-dojo-type="dijit.form.Form" class="form-horizontal" id="resetPasswordForm"> 
          <div id="resetPasswordFormPage">
          <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="newPassword" class="control-label">
                  <g:message code="common.password"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                  <input type="password" value=""  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                         onkeyup="AccountList.checkPasswordStrength(this.id)" onchange="AccountList.checkPasswordStrength(this.id)" id="newPassword" 
                         data-dojo-props="promptMessage:'<g:message code="common.password.prompt"/>', 
                         missingMessage: '<g:message code="common.password.missing"/>',required: true,
                         placeHolder: '<g:message code="common.password"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}'">
                </div>
              </div>
              <div class="span3">
                <div id="password_strength" style="display: none; margin-top: 5px;">
                  <div style="width: 100px; border: #CCCCCC 1px solid;">
                    <div id="progress_bar" style="height: 5px; border: #FFFFFF 0px solid; font-size: 1px; background-color: #FFD700;"></div>
                  </div>
                  <span id="strength_text" style="font-family: Arial; font-size: 10px; color: #333333;"><g:message code="common.weak"/></span>
                  <input type="hidden" name="strength_id" id="strength_id" value="1" />
                </div>
              </div>
          </div>
          <div class="row-fluid">
            <div class="control-group span9"> 
              <label for="confirmPassword" class="control-label">
                <g:message code="common.confirmPassword"/>
                <span class="require">*</span>
              </label>
              <div class="controls">
                <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                       onKeyUp="AccountList.confirmPassword(this)" onmouseout="AccountList.confirmPassword(this)"  id="confirmPassword" 
                       data-dojo-props="promptMessage:'<g:message code="common.password.prompt"/>', 
                       missingMessage: '<g:message code="common.password.donotMatch"/>',required: true,
                       placeHolder: '<g:message code="common.confirmPassword"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}'">
              </div>
            </div>
            <div class="span3 validationErrorMsg" id="passwordValidateMsg">
            <span><g:message code="common.passwordMismatch"/></span>
            </div>
          </div>
          </div>  
          <div class="span12 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
                      onclick="AccountList.resetPassword()">
                <g:message code="common.yes"/>
            </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
                      onclick="AccountList.closeResetPasswordDialog()">
                <g:message code="common.no"/>
            </button> 
          </div>
       </form>
      </div>
    </div>
  </div>
</div>


                
              
