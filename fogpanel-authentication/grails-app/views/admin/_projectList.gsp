<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><g:message code="menu.ldap.projects"/></li>
      <li>/</li>
      <li><span id="currentGroupName"></span></li>
    </ul>
  </div>
</div>

<div id="projectGrid"></div>
<div class="alert alert-info hide" id="noProjectsMessageBox">
    <g:message code="menu.admin.noProjects"/>
</div>
<div data-dojo-type="dijit.Dialog" id="assignCloudEngineDialog" title="<g:message code="common.assignCloudEngine"/>">
<g:message code="common.project.assignCloudEngine"/>  
  <div class="new-user"> 
    <div class="row-fluid form-wrapper"> 
      <div class="span12"> 
        <form data-dojo-type="dijit.form.Form" class="form-horizontal" id="resetPasswordForm"> 
          <div id="projectId">
              <div id="resetPasswordFormPage">
          <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="newPassword" class="control-label">
                  <g:message code="common.project.name"/>
                </label>
                <div class="controls">
                 <input type="text" name="firstname" value="testing testing" 
                    data-dojo-type="dijit/form/TextBox"
                    data-dojo-props="trim:true, disabled:true,propercase:true" id="projectName" />
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
                <g:message code="menu.admin.configuration.cloudengine"/>
                <span class="require">*</span>
              </label>
              <div class="controls">
                  <div id="cloudEngine"></div>
              </div>
            </div>
            <div class="span3 validationErrorMsg" id="passwordValidateMsg">
            <span><g:message code="common.passwordMismatch"/></span>
            </div>
          </div>
          </div>  
          <div class="span12 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
                      onclick="cloudEngineInfo.assignProject()">
                <g:message code="common.assign"/>
            </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
                      onclick="cloudEngineInfo.closeCloudEngineDialog()">
                <g:message code="common.cancel"/>
            </button> 
          </div>
       </form>
      </div>
    </div>
  </div>
</div>

