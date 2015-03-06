<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
                <a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a>
            <li>/</li>
            <li><g:message code="common.signupSetting"/></li>    
        </ul>
    </div>
</div>

<div id="" class="new-user">
    <div class="row-fluid header"></div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span8 with-sidebar">
            <div class="container">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="signupConfigForm">            
                    <div id="signupConfigFormPage">
                        <div class="span12 field-box control-group" id="signupSettingValue">
                            <label style="margin-top: -8px;" for="" class="control-label"><g:message code="common.signupSetting"/>:</label>
                              <div class="controls">
                                  <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton" name="signupSetting" id="signupSettingPublic" value="PUBLIC"/> 
                                  <label for="signupSettingPublic" class=""><g:message code="common.public"/></label>
                                  <input type="radio" data-dojo-type="dijit.form.RadioButton" name="signupSetting" id="signupSettingInvitationOnly" value="INVITATION_ONLY"/>
                                  <label for="signupSettingInvitationOnly"><g:message code="common.invitationOnly"/></label>                         
                                  <input type="radio" data-dojo-type="dijit.form.RadioButton" name="signupSetting" id="signupSettingDisabled" value="DISABLED"/>
                                  <label for="signupSettingDisabled"><g:message code="common.disabled"/></label>                         
                              </div>
                        </div>
                    </div>
                    <div class="pull-right">            
                        <button id="signupConfigButtton" class="defaultbtn" type="button" 
                        data-dojo-type="dijit.form.Button"  onclick="SignupConfig.update()"><g:message code="common.update"/></button>
                <!--<img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>-->   
                    </div>
                </form>
            </div>
        </div> 
        <div class="span4">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <!--<span id=""><g:message code="admin.defaultlangInfo"/></span>-->
                </div>
            </div>
        </div>
    </div>
</div>