<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/</li>
            <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
            <li>/</li>
            <li><g:message code="common.adminUser"/></li>
        </ul>
    </div>
</div>
<div>
    <div class="table-wrapper products-table">
        <div class="row-fluid head">
            <div class="span12">
            </div>
        </div>
        <div class="row-fluid filter-block">
            <div class="pull-right">
                <a class="btn-flat success new-product" href="#/admin/settings/addAdmin">+ <g:message code="common.add"/></a>
            </div>
        </div>
        <div class="row-fluid">
            <div id="adminUserList">  
            </div>
            <div class="alert alert-info hide" id="noAdminMessageBox" style="display: none">
                <i class="icon-exclamation-sign"></i> 
                <g:message code="admin.noAdminInfo"/>
            </div>
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="addAccountProcessLoader2" class="span8">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing"/></p>
    </div>
</div>
<input type="hidden" id="userId">
<div data-dojo-type="dijit.Dialog" id="resetPasswordDialog" title="<g:message code="common.resetPassword"/>">
    <g:message code="admin.passwordResetInfo"/>
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
                                    onkeyup="AdminUser.checkPasswordStrength(this.id)" onchange="AdminUser.checkPasswordStrength(this.id)" id="newPassword" 
                                    data-dojo-props="promptMessage:'<g:message code="common.password.prompt"/>', 
                                    missingMessage: '<g:message code="common.password.missing"/>',required: true,
                                    placeHolder: '<g:message code="common.password.prompt"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}'">
                                </div>
                            </div>
                            <div class="span3">
                                <div id="password_strength" style="display: none; margin-top: 5px;">
                                    <div style="width: 100px; border: #CCCCCC 1px solid;">
                                        <div id="progress_bar" style="height: 5px; border: #FFFFFF 0px solid; font-size: 1px; background-color: #FFD700;"></div>
                                    </div>
                                    <span id="strength_text" style="font-family: Arial; font-size: 10px; color: #333333;"></span>
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
                                    onKeyUp="AdminUser.confirmPassword(this)" onmouseout="AdminUser.confirmPassword(this)"  id="confirmPassword" 
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
                        onclick="AdminUser.resetPassword()" id="" >
                        <g:message code="common.yes"/>
                        </button>
                        <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
                        onclick="AdminUser.closeResetPasswordDialog()">
                        <g:message code="common.no"/>
                        </button> 
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="showAPIKeyDialog" title="<g:message code="admin.showApiKey"/>" class="span5">
    <input type="hidden" id="userId">
    <div class="new-user"> 
        <div class="row-fluid form-wrapper"> 
            <div class="span6"> 
                <form data-dojo-type="dijit.form.Form" class="form-horizontal" id=""> 
                    <div class="control-group span7"> 
                        <label for="apiKeyValue" class="control-label">
                            <g:message code="admin.apiKey"/>
                        </label>
                        <div class="controls">
                            <textarea class="form-control" rows="1" cols="5" id="apiKeyValue" name="apiKeyValue" disabled="true"></textarea>
                        </div>
                    </div>
                    <div class="control-group span7"> 
                        <label for="secretKeyValue" class="control-label">
                            <g:message code="admin.secretKey"/>
                        </label>
                        <div class="controls">
                            <textarea class="form-control" rows="1" cols="5" id="secretKeyValue" name="secretKeyValue" disabled="true"></textarea>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="showResetKeysDialog" title="<g:message code="admin.resetApiKey"/>" class="span3">
    <input type="hidden" id="userId">
    <div class="row-fluid form-wrapper"> 
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="AdminUser.resetKeysForAdmin()" id="" >
                <g:message code="common.yes"/>
            </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="AdminUser.closeResetAPIKeyDialog()">
                <g:message code="common.no"/>
            </button> 
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="showRevokeAPIAccessDialog" title="<g:message code="admin.revokeApiKey"/>" class="span3">
    <input type="hidden" id="userId">
    <div class="row-fluid form-wrapper"> 
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="AdminUser.revokeAPIAccess()" id="" >
                <g:message code="common.yes"/>
            </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="AdminUser.closeRevokeAPIKeyDialog()">
                <g:message code="common.no"/>
            </button> 
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="showGrantAPIAccessDialog" title="<g:message code="admin.grantApiKey"/>" class="span3">
    <input type="hidden" id="userId">
    <div class="row-fluid form-wrapper"> 
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="AdminUser.grantAPIKey()" id="" >
                <g:message code="common.yes"/>
            </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="AdminUser.closeGrantAPIKeyDialog()">
                <g:message code="common.no"/>
            </button> 
        </div>
    </div> 
</div>