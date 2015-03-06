<div  class="row-fluid ">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/</li>
            <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
            <li>/</li>
            <li><g:message code="common.addAdmin"/></li>
        </ul>
    </div>
</div>
<div class="new-user">
    <div class="row-fluid header">
        <!--<h3>Create Admin</h3>-->
    </div>
    <div class="row-fluid form-wrapper">
                    <!-- left column -->
        <div class="span7 with-sidebar" id="">
            <div class="container">
                <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="addAdminUserForm">
                    <div class="span12" id="addAdminUserPage">
                        <div class="control-group"> 
                            <label for="newPassword2" class="control-label settings_lable">
                                <g:message code="common.username"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.username"/>', required: 'true',
                                placeHolder: '<g:message code="common.promptMessage.username"/>'" 
                                id="adminUserName" onkeyup="AddAdmin.validateUserName()">
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="control-group"> 
                                <label for="newPassword2" class="control-label settings_lable">
                                    <g:message code="common.password"/>
                                    <span class="require">*</span>
                                </label>
                                <div class="controls">
                                    <input type="password" value=""  name="newPassword2" data-dojo-type="dijit.form.ValidationTextBox" 
                                    onkeyup="AddAdmin.checkPasswordStrength(this.id)" onchange="AddAdmin.checkPasswordStrength(this.id)" id="newPassword2" 
                                    data-dojo-props="promptMessage:'<g:message code="common.password.prompt"/>', 
                                    missingMessage: '<g:message code="common.password.missing"/>',required: true,
                                    placeHolder: '<g:message code="common.password.prompt"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}'">
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="control-group"> 
                                <label for="confirmPassword2" class="control-label settings_lable">
                                    <g:message code="common.confirmPassword"/>
                                    <span class="require">*</span>
                                </label>
                                <div class="controls">
                                    <input type="password" value="" name="confirmPassword2" data-dojo-type="dijit.form.ValidationTextBox"
                                    onKeyUp="AddAdmin.confirmPassword(this)" onmouseout="AddAdmin.confirmPassword(this)"  id="confirmPassword2" 
                                    data-dojo-props="promptMessage:'<g:message code="common.password.prompt"/>', 
                                    missingMessage: '<g:message code="common.password.donotMatch"/>',required: true,
                                    placeHolder: '<g:message code="common.confirmPassword"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}'">
                                </div>
                            </div>
                            <div class="span3 validationErrorMsg" id="passwordValidateMsg">
                                <span><g:message code="common.passwordMismatch"/></span>
                            </div>
                        </div>
                        <div class="pull-right">                            
      <!--                        <input type="reset" value="Cancel" class="reset">
                              <span>OR</span>-->
                            <button  class="defaultbtn"  type="button" data-dojo-type="dijit.form.Button" onclick="AddAdmin.addUser()"><g:message code="common.create"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
        <div class="span5">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span><g:message code="admin.usernameInfo"/></span>
                </div>
                <div class="span12 field-box" id ="fixedDayContainer">
                    <div class="span6">
                        <span><g:message code="admin.passwordInfo"/></span>
                    </div>
                    <div id="password_strength" style="display: none; margin-top: 5px;" class="span6">
                        <div style="width: 100px; border: #CCCCCC 1px solid;">
                            <div id="progress_bar" style="height: 5px; border: #FFFFFF 0px solid; font-size: 1px; background-color: #FFD700;"></div>
                        </div>
                        <span id="strength_text" style="font-family: Arial; font-size: 10px; color: #333333;"></span>
                        <input type="hidden" name="strength_id" id="strength_id" value="1" />
                    </div>          
                </div>
                <!--</div>-->
                <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
                    <span><g:message code="admin.conformPasswordInfo"/></span>
                </div>
            </div>
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="addAccountProcessLoader" class="span8">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>