<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/dashboard/changePassword"><g:message code="common.account"/></a></li>
    <li>/</li>    
    <li><g:message code="common.passwordSetting"/></li>    
  </ul>
</div>
</div>
<div class="new-user">
    <div class="row-fluid form-wrapper">
        <div class="span12">
            <div class="span7 with-sidebar">		   
                <input type="hidden" id="profileCurrentId"> 
                <div class="container">    
                    <div id="profilePage">
                        <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="profileForm">  
                            <div class="span12"></div>
                            <div class="span12 control-group field-box">
                                <label for="adminOldPassword"><g:message code="common.oldPassword"/>:<span class="require">*</span></label>
                                <div class="controls">
                                <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                                    id="adminOldPassword" data-dojo-props="promptMessage:'<g:message code="common.promptMessage.password"/>', 
                                    invalidMessage: '<g:message code="common.invalidMessage.password"/>',required: 'true',
                                    placeHolder: '<g:message code="common.oldPassword"/>'"> 
                                </div>
                            </div>
                            <div class="span12 control-group field-box">
                                <label for="adminNewPassword"><g:message code="common.newPassword"/>:<span class="require">*</span></label>
                                <div class="controls">
                                <input type="password"  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                                    onkeyup="AccountSettingDetail.checkPasswordStrength(this)" onchange="AccountSettingDetail.checkPasswordStrength(this)" 
                                    id="adminNewPassword" data-dojo-props="promptMessage:'<g:message code="common.promptMessage.password"/>', 
                                    invalidMessage: '<g:message code="common.invalidMessage.password"/>',required: 'true',
                                    placeHolder: '<g:message code="common.newPassword"/>'">
                                </div>
                            </div>
                                <div class="span12 control-group field-box">
                                    <label for="adminConfirmPassword"><g:message code="common.confirmPassword"/>:<span class="require">*</span></label>
                                    <div class="controls">
                                        <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                                            onblur="AccountSettingDetail.confirmPass()"  id="adminConfirmPassword" data-dojo-props="promptMessage:'<g:message code="common.promptMessage.password"/>', 
                                            invalidMessage: '<g:message code="common.invalidMessage.password"/>',required: 'required',
                                            placeHolder: '<g:message code="common.confirmPassword"/>'">
                                    </div>
                                </div>
                                    <div class="span2 offset11">              
                                        <button id="resetBtn" class="resetbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AccountSettingDetail.resetNewPassword()"><g:message code="common.reset"/></button>
                                        <img id="resetBtnLoader" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="<g:message code="common.reset"/>" height="20" width="20">
                                    </div>
                        </form>
                    </div>
                </div>
            </div>	            
            <div class="span5">
                <div class="new_user_form inline-input">
                    <div class="span12"></div>
                    <div class="span12 field-box"><span><g:message code="common.admin.oldPassword.decs"/></span></div>        
                    <div class="span12 field-box"><span><g:message code="common.admin.newPassword.decs"/></span></div>        
                    <div class="span12 field-box"><span><g:message code="common.admin.confirmPassword.decs"/></span></div>                                           
                </div>
            </div>
    </div>
</div>
</div>
