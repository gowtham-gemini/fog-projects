<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/dashboard/accountSetting"><g:message code="common.account"/></a></li>
    <li>/</li>    
    <li><g:message code="menu.user.configuration.general"/></li>    
  </ul>
</div>
</div>
  <div class="new-user">
<div class="row-fluid form-wrapper">
    <div class="span12">
        <div class="span7 with-sidebar">		   
    		<input type="hidden" id="currentAdminId">
    	<div class="container">
    <!-- left column -->
      <div id="adminGeneralPage">
          <form data-dojo-type="dijit.form.Form" id="generalForm" class="form-horizontal">              
                <div class="span12 field-box control-group">
                <label for="adminEmail" class="control-label"><g:message code="common.email"/>:<span class="require">*</span></label>
                  <div class="controls">
                  <input data-dojo-type="dijit.form.ValidationTextBox" 
                  data-dojo-props="required:true, promptMessage:'<g:message code="common.promptMessage.email"/>', 
                  invalidMessage:'Invalid Email Adrress', trim:'true',disabled: true, 
                  placeHolder: '<g:message code="common.promptMessage.email"/>', regExp: dojox.validate.regexp.emailAddress" 
                  id="adminEmail"> 
                </div>
               </div>
                <div class="span12 control-group field-box">
                      <label for="adminFirstName" class="control-label"><g:message code="common.firstName"/>:<span class="require">*</span></label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', promptMessage:'<g:message code="common.promptMessage.firstName"/>', 
                        invalidMessage: '<g:message code="common.invalidMessage.firstName"/>',required: true,
                        placeHolder: '<g:message code="common.firstName"/>'" 
                        name="firstName" id="adminFirstName"> 
                      </div>
              </div>
              <div class="span12 control-group field-box">
                  <label for="adminLastName" class="control-label"><g:message code="common.lastName"/>:<span class="require">*</span></label>
                  <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', promptMessage:'<g:message code="common.promptMessage.lastName"/>', 
                        invalidMessage: '<g:message code="common.invalidMessage.lastName"/>',required: true,
                        placeHolder: '<g:message code="common.lastName"/>'" name="lastName" id="adminLastName"> 
                  </div>
              </div>                        
              <div class="span12 control-group field-box">
                <label for="adminDateFormateWidget" class="control-label">                 
                        <g:message code="common.dateFormat"/>:<span class="require">*</span>
                </label>
                <div class="controls">
                    <select data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: 100" id="adminDateFormateWidget">
                        <option value="dd/MMM/yyyy">DD/MMM/YYYY</option>
                        <option value="MMM/dd/yyyy">MMM/DD/YYYY</option>                        
                        <option value="M/d/yyyy">M/D/YYYY</option>
                        <option value="M/d/yy">M/D/YY</option>
                        <option value="MM/dd/yy">MM/DD/YY</option>
                        <option value="MM/dd/yyyy">MM/DD/YYYY</option>
                        <option value="yy-MM-dd">YY/MM/DD</option>
                        <option value="yyyy-MM-dd">YYYY-MM-DD</option>
                        <option value="dd-MMM-yyyy">DD-MMM-YY</option>
                        <option value="M-d-yyyy">M-D-yyyy</option>
                        <option value="M-d-yy">M-D-YY</option>
                        <option value="MM-dd-yy">MM-DD-YY</option>            
                        <option value="MM-dd-yyyy">MM-DD-YYYY</option>            
                        <option value="yy-MM-dd">YY-MM-DD</option>                                                                               
                    </select>
                </div>
              </div>                                          
            <div class="span2 offset11">
<!--              <input type="reset" value="Cancel" class="reset" onclick="EditTax.resetPage()">
              <span>OR</span>-->
              <button id="gereralButton" type="button" class="updatebtn" data-dojo-type="dijit.form.Button" onclick="AccountSettingDetail.update()"><g:message code="common.update"/></button>
              <img id="generalInfoLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading"/>' height='16' width='23' style="display: none"/>
            </div>
          </form>
      </div>
      </div>
    </div>	

    <div class="span5">
      <div class="new_user_form inline-input">
        <div class="span12 field-box"><span><g:message code="common.adminSetting.email"/></span></div>        
        <div class="span12 field-box"><span><g:message code="common.adminSetting.firstName"/></span></div>        
        <div class="span12 field-box"><span><g:message code="common.adminSetting.lastName"/></span></div>        
        <div class="span12 field-box"><span><g:message code="common.adminSetting.dateFormat"/></span></div>                
      </div>
    </div>
    </div>
</div>
</div>
