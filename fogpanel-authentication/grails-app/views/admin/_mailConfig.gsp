<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
    <li>/</li>
    <li><g:message code="common.emailConfig"/></li>
  </ul>
</div>
</div>

<div  class="new-user">

  <div class="row-fluid form-wrapper">
    <div class="span7 with-sidebar" id="mailConfigPage">
      <div class="container">
        <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="mailConfigForm">
          <div class="span12 field-box control-group">
            <label for="applicationUrl" class="control-label">              
              <g:message code="common.applicationUrl"/>:
              <span class="require">*</span>
            </label> 
            <div class="controls">
            <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: 'invalid applicationUrl',
                   required: 'required', placeHolder: 'Enter applicationUrl', 
                   promptMessage:'applicationUrl', disabled: 'true'" 
                   name="applicationUrl" id="applicationUrl"> 
            </div>
          </div>
          <div class="span12 field-box control-group">
            <label for="host" class="control-label">              
              <g:message code="common.host"/>:
              <span class="require">*</span>
            </label>     
            <div class="controls">
            <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: 'invalid host',
                   required: 'required', placeHolder: 'Enter host', 
                   promptMessage:'host', disabled: 'true'" 
                   name="host" id="host">   
            </div>
          </div>
          <div class="span12 field-box  control-group">
            <label for="userName" class="control-label">              
              <g:message code="common.username"/>:
              <span class="require">*</span>
            </label>
            <div class="controls">
            <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                   data-dojo-props="invalidMessage: 'invalid username',
                   required: 'required', placeHolder: 'Email',promptMessage:'username',
                   regExp: dojox.validate.regexp.emailAddress , disabled: 'true'" 
                   name="userName" id="userName">
            </div>
          </div> 
          <div class="span12 field-box control-group">
            <label for="password" class="control-label">              
              <g:message code="common.password"/>:
              <span class="require">*</span>
            </label>
            <div class="controls">
            <input type="text" 
                     data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage: 'invalid password',
                     required: 'required', placeHolder: 'Enter password', 
                     promptMessage:'password', disabled: 'true'" 
                     name="password" id="password">  
            </div>
          </div> 
          <div class="span12 field-box control-group">
            <label for="port" class="control-label">              
              <g:message code="common.port"/>:
              <span class="require">*</span>
            </label>
            <div class="controls">
            <input type="text" 
                     data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage: 'invalid Port',
                     required: 'required', placeHolder: 'Enter Port', 
                     promptMessage:'password',regExp:'[0-9]{2,5}', disabled: 'true'" 
                     name="port" id="port"> 
            </div>
          </div> 
          <div class="span12 field-box control-group">
            <label for="ssl" class="control-label">                
                <g:message code="common.ssl"/>:
                <span class="require">*</span>
              </label>
            <div class="controls">
            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                         data-dojo-props="checked: false, disabled: 'true'" id="ssl" name="ssl">
            </div>
          </div> 
          <div class="span12 field-box control-group">
            <label for="from" class="control-label">               
                <g:message code="common.from"/>:
                 <span class="require">*</span>
              </label>
            <div class="controls">
            <input type="text" 
                       data-dojo-type="dijit.form.ValidationTextBox" 
                       data-dojo-props="invalidMessage: 'invalid from address',
                       required: 'required', placeHolder: 'from address', 
                       promptMessage:'from address', disabled: 'true'" 
                       name="from" id="from"> 
            </div>
          </div> 
          <div class="span12 field-box control-group">
            <label for="from" class="control-label">               
                <g:message code="common.senderName"/>:
                 <span class="require">*</span>
              </label>
            <div class="controls">
            <input type="text" 
                       data-dojo-type="dijit.form.ValidationTextBox" 
                       data-dojo-props="invalidMessage: 'invalid from address',
                       required: 'required', placeHolder: '', 
                       promptMessage:'', disabled: 'true'" 
                       name="senderName" id="senderName"> 
            </div>
          </div> 

        </form>
      </div>
    </div>
    <div class="span5">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span><g:message code="admin.applicationUrlInfo"/></span>
        </div>
        <div class="span12 field-box" >
          <span><g:message code="admin.emailHostInfo"/></span>
        </div>
        <div class="span12 field-box" >
          <span><g:message code="admin.usernameInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span><g:message code="admin.passwordInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span><g:message code="admin.emailPortInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span><g:message code="admin.emailSSLInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span><g:message code="admin.emailFromInfo"/></span>
        </div>
        
      </div>
    </div>
  </div>
</div>