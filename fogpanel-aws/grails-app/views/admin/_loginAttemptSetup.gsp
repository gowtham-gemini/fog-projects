<%@ page contentType="text/html;charset=UTF-8" %>
    <h3>Login Attempt Setup</h3> 
    <a href="#/admin/dashboard">Back to Dashboard</a>
    <form id="loginAttemptConfigForm" data-dojo-type="dijit.form.Form"
          class="form-horizontal">
          <div class="row-fluid">
      <div class="control-group span8"> 
          <label for="unlockTime" class="control-label">
            <span class="require">*</span>
            Unlock Time in (min):
          </label>
          <div class="controls">
            <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: 'invalid Unlock Time',
                   required: 'required', placeHolder: 'Enter Unlock Time', 
                   promptMessage:'Unlock Time', regExp:'[0-9]{2,5}'" 
                   name="host" id="unlockTime">  
          </div>
        </div> 
        <div class="control-group span8"> 
            <label for="perIp" class="control-label">
              <span class="require">*</span>
              MAX LOGIN FAILURE PER IP:
            </label>
            <div class="controls">
              <input type="test" 
                     data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage: 'invalid number',
                     required: 'required', placeHolder: 'Enter number', 
                     promptMessage:'number',regExp:'[0-9]{2,5}'" 
                     name="perIp" id="perIp">  
            </div>
        </div> 
        <div class="control-group span8"> 
              <label for="perAccount" class="control-label">
                <span class="require">*</span>
                 MAX LOGIN FAILURE:
              </label>
              <div class="controls">
                <input type="text" 
                       data-dojo-type="dijit.form.ValidationTextBox" 
                       data-dojo-props="invalidMessage: 'invalid number',
                       required: 'required', placeHolder: 'Enter number', 
                       promptMessage:'number',regExp:'[0-9]{2,5}'" 
                       name="perAccount" id="perAccount">  
              </div>
          </div> 
        </div>
          <div class="row-fluid">
            <button data-dojo-type="dijit/form/Button" type="reset">
               Cancel
             </button>
           <button data-dojo-type="dijit.form.Button"
                     id="loginAttemptUpdate" onclick="LoginAttemptSetup.add();">
               Update
             </button>
             <button data-dojo-type="dijit.form.Button"
                     id="loginAttemptAdd" onclick="LoginAttemptSetup.add();">
               ADD
             </button>
          </div>
    </form>
  