<div class="row-fluid">  
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/</li>
            <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
            <li>/</li>
            <li><g:message code="common.loginSecurity"/></li>
        </ul>
    </div>
</div>
<div class="new-user">
    <div class="row-fluid"></div>
    <div class="row-fluid form-wrapper">    
        <div class="span6 with-sidebar" id="loginSecurityPage">
            <div class="container">
                <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="loginSecurityForm">          
                    <div class="span12 field-box control-group">
                        <label for="linkActiveTime" class="control-label settings_lable"><g:message code="common.linkActiveTime"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                            data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.linkActiveTime"/>',regExp:'[0-9]{1,5}',
                            required: 'required', placeHolder: '<g:message code="common.promptMessage.linkActiveTime"/>', promptMessage:'<g:message code="common.promptMessage.linkActiveTime"/>'" 
                            name="linkActiveTime" id="linkActiveTime">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="unlockTime" class="control-label settings_lable"><g:message code="common.unLockTime"/>:<span class="require">*</span></label> 
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.unLockTime"/>',
                                   required: 'required', placeHolder: '<g:message code="common.promptMessage.unLockTime"/>', promptMessage:'<g:message code="common.promptMessage.unLockTime"/>', regExp:'[0-9]{1,5}'" 
                                   name="host" id="unlockTime">  
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="perIp" class="control-label settings_lable"><g:message code="common.maxFailurePerIp"/>:
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="test" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.maxFailurePerIp"/>',
                                   required: 'required', placeHolder: '<g:message code="common.promptMessage.maxFailurePerIp"/>', promptMessage:'<g:message code="common.promptMessage.maxFailurePerIp"/>',regExp:'[0-9]{1,5}'" 
                                   name="perIp" id="perIp"> 
                        </div>
                    </div>
                    <div style="display: none;" class="span12 field-box control-group">
                        <label for="perAccount" class="control-label settings_lable"><g:message code="common.maxFailure"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.maxFailurePerIp"/>',                     
                                   required: 'required', placeHolder: '<g:message code="common.promptMessage.maxFailurePerIp"/>', promptMessage:'<g:message code="common.promptMessage.maxFailurePerIp"/>',regExp:'[0-9]{1,5}'" 
                                   name="perAccount" id="perAccount">  
                        </div>
                    </div>
                    <div class="pull-right">                            
<!--                      <input type="reset" value="Cancel" class="reset" onclick="LoginSecurityInfo.calcel();"> 
              <span>OR</span>-->
                        <button class="defaultbtn" data-dojo-type="dijit.form.Button" id="mailConfigUpdate" onclick="LoginSecurityInfo.add();">
                            <g:message code="common.update"/>
                        </button>
                        <img id="loginSecurityLoader" style="display: none; float: right" src="${resource(dir: 'images')}/preloader_circle.gif" 
                             alt="reset" height="20" width="20">
                    </div>
                </form>
            </div>
        </div>    
        <div class="span6">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span id="loginLinkActiveTimeDesc"><g:message code="admin.linkActiveTimeinfo"/></span>
                </div>
                <div class="span12 field-box" id ="fixedDayContainer">
                    <span id="loginUnlockTimeDesc"><g:message code="admin.unLockTimeInfo"/></span>
                </div>
                <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
                    <span id="loginPerIpDesc"><g:message code="admin.maxIpFailureInfo"/></span>
                </div>
                <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
                    <span id="loginPerAccountDesc"><g:message code="admin.maxFailure"/></span>
                </div>              
            </div>
        </div>
    </div>
</div>
