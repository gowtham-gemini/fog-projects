<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/home/general"><g:message code="common.account"/></a></li>
    <li>/</li>    
    <li><g:message code="menu.user.configuration.general"/></li>    
  </ul>
</div>
</div>

<div class="row-fluid form-wrapper overflow-text">
	<div class="span12">
		<div class="span4 generalPage">
		  <div class="row-fluid">
    		<input type="hidden" id="genaralStateId">       
    		<input type="hidden" id="currentId">
    		<input type="hidden" id="generalCountryId">
  		</div>
                    <h2 class="overflowLabel" style="line-height: 27px"><g:message code="menu.user.configuration.general"/></h2>
    <!-- left column -->
      <div id="generalPage">
          <form data-dojo-type="dijit.form.Form" id="generalForm" class="form-horizontal">
              <div class="span1"></div>
                <div class="span12 control-group field-box">
                  <label for="email"><g:message code="common.email"/>:<span class="require">*</span></label>
                  <div class="controls">
                  <input data-dojo-type="dijit.form.ValidationTextBox" 
                  data-dojo-props="required:true, promptMessage:'<g:message code="common.promptMessage.email"/>', 
                  invalidMessage:'Invalid Email Adrress', trim:'true',disabled: true, 
                  placeHolder: '<g:message code="common.invalidMessage.email"/>', regExp: dojox.validate.regexp.emailAddress" 
                  id="email"> 
                </div>
               </div>
                <div class="span12 control-group field-box">
                      <label for="firstName"><g:message code="common.firstName"/>:<span class="require">*</span></label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', promptMessage:'<g:message code="common.promptMessage.firstName"/>', 
                        invalidMessage: '<g:message code="common.invalidMessage.firstName"/>',required: true,
                        placeHolder: '<g:message code="common.firstName"/>'" 
                        name="firstName" id="firstName"> 
                      </div>
              </div>
              <div class="span12 control-group field-box">
                  <label for="lastName"><g:message code="common.lastName"/>:<span class="require">*</span></label>
                  <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', promptMessage:'<g:message code="common.promptMessage.lastName"/>', 
                        invalidMessage: '<g:message code="common.invalidMessage.lastName"/>',required: true,
                        placeHolder: '<g:message code="common.lastName"/>'" name="lastName" id="lastName"> 
                  </div>
              </div>            
            <div class="span12 control-group field-box">
                <label for="streetAddress" class="control-label">                 
                        <g:message code="common.address1"/>:<span class="require">*</span>
                </label>
                <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        data-dojo-props="required: true, promptMessage: '<g:message code="common.promptMessage.address1"/>',
                        invalidMessage: '<g:message code="common.invalidMessage.address1"/>',
                        trim: 'true', placeHolder: '<g:message code="common.address1"/>'
                        "id="streetAddress"  name="streetAddress"  value=""> 
                </div>
            </div>
            <div class="span12 control-group field-box">
                <label for="streetAddress2" class="control-label">                
                        <g:message code="common.address2"/>
                </label>
                <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        data-dojo-props="promptMessage: '<g:message code="common.invalidMessage.address2"/>',
                        invalidMessage: '<g:message code="common.invalidMessage.address2"/>', placeHolder: '<g:message code="common.address2"/>'" 
                        id="streetAddress2"  name="streetAddress">
                </div>
            </div>
            <div class="span12 control-group field-box">
                <label for="city" class="control-label">                 
                        <g:message code="common.city"/>:<span class="require">*</span>
                </label>
                <div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                           data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', required: true, promptMessage: '<g:message code="common.promptMessage.city"/>', 
                           invalidMessage: '<g:message code="common.invalidMessage.city"/>', placeHolder: '<g:message code="common.city"/>'" 
                           id="city" name="city">
                </div>
              </div>
              
              <div class="span12 control-group field-box">
                  <label for="country" class="control-label">
                    <g:message code="common.country"/>:<span class="require">*</span>
                </label>
				<div class="controls">
                  <div id="countryList"></div> 
				</div>
              </div>
              <div class="span12 control-group field-box">
                  <label for="state" class="control-label">
                  <g:message code="common.state"/>:<span class="require">*</span>
                </label>
				<div class="controls">
                 <div id="stateList"></div> 
				</div>
              </div>
              <div class="span12 control-group field-box">
                 <label for="phoneNumber" class="control-label">
                  <g:message code="common.pnoneNumber"/>:<span class="require">*</span>
                </label>
				<div class="controls">
                 <span id="callingCode"><g:message code="common.code"/></span>
                    <span>-</span>
                <input type="text" style="width: 165px;"
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="required:true, promptMessage: '<g:message code="common.pnoneNumber"/>',
                     invalidMessage:'<g:message code="common.invalidMessage.pnoneNumber"/>', placeHolder: '<g:message code="common.pnoneNumber"/>',regExp: '[0-9-+ ]{4,20}'" id="phoneNumber" name= "phoneNumber" >
				</div>
              </div>
            <div class="span12 control-group field-box">
                 <label for="zip" class="control-label">
                  <g:message code="common.user.zip"/>:<span class="require">*</span>
                </label>
				<div class="controls">
                <input type="text"  
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="required:true, 
                     invalidMessage:'<g:message code="common.invalidMessage.zip"/>', placeHolder: '<g:message code="common.user.zip"/>',
                     accept: '',regExp: '[0-9A-Z- a-z]{1,10}$'" id="zip" name= "zip" value="">
				</div>
              </div>
            <div class="span2 offset4">
<!--              <input type="reset" value="Cancel" class="reset" onclick="EditTax.resetPage()">
              <span>OR</span>-->
              <button id="gereralButton" type="button" class="updatebtn" data-dojo-type="dijit.form.Button" onclick="GeneralDetail.update()"><g:message code="common.update"/></button>
              <img id="generalInfoLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading"/>' height='16' width='23' style="display: none"/>
            </div>
          </form>
      </div>
    </div>
<!--    <div class="span3">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span id="editTaxNameLabel">Name of the Tax</span>
        </div>
        <div class="span12 field-box" id ="fixedDayContainer">
          <span id="editDescriptionLabel">A detail description about the tax</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span id="editTaxPercentageLabel">The allocated Percentage(%) of the tax</span>
        </div>
  </div>
		</div>-->
		<div class="span4 billingPage">
			<div class="row-fluid">
			    <input type="hidden" id="billingCurrentId">       
    			<input type="hidden" id="billingStateId"> 
    			<input type="hidden" id="billingCountryId"> 
  			</div>
			<h2 class="overflowLabel" style="line-height: 27px"><g:message code="signup.wizard.title.billingInfo"/></h2>
      <div id="billingPage">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="billingForm">              
            <div class="span12"></div>
            <div class="span12 control-group field-box"> 
                <label for="userCompanyName" class="control-label">
                    <g:message code="common.companyName"/>
                </label>
                <div class="controls">
                    <input type="text" id="userCompanyName" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', promptMessage:'<g:message code="common.promptMessage.companyName"/>', placeHolder: '<g:message code="common.companyName"/>'"> 
                </div>
              </div>
              <div class="span12 control-group field-box">
                <label for="billingStreetAddress" class="control-label">                 
                  <g:message code="common.address1"/>:<span class="require">*</span>
                </label>
                <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         data-dojo-props="required: true, promptMessage: 'Enter Address',
                            invalidMessage: '<g:message code="common.invalidMessage.address1"/>',
                            trim: 'true', placeHolder: '<g:message code="common.address1"/>'
                            " id="billingStreetAddress"  name="streetAddress"  value=""> 
                </div>
              </div>
              <div class="span12 control-group field-box">
                  <label for="billingStreetAddress2" class="control-label">                
                  <g:message code="common.address2"/>
                </label>
                <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         data-dojo-props="promptMessage: '<g:message code="common.invalidMessage.address1" />',
                            invalidMessage: '<g:message code="common.invalidMessage.address1" />',
                            placeHolder: '<g:message code="common.address2"/>'"
                            id="billingStreetAddress2"  name="streetAddress">
                </div>
              </div>
              <div class="span12 control-group field-box">
                  <label for="billingCity" class="control-label">                 
                  <g:message code="common.city"/>:<span class="require">*</span>
                </label>
				<div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                           data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', required: true, promptMessage:  '<g:message code="common.promptMessage.city"/>', 
                           invalidMessage: '<g:message code="common.invalidMessage.city"/>', placeHolder: '<g:message code="common.city"/>'" 
                           id="billingCity" value="" name="city">
				</div>
              </div>
              <div class="span12 control-group field-box">
                  <label for="billingCountry" class="control-label">
                     <g:message code="common.country"/>:<span class="require">*</span>
                  </label>
                  <div class="controls">
                    <div id="billingCountryList"  class="selectOption"></div> 
                  </div>
              </div>
              <div class="span12 control-group field-box">
                  <label for="billingState" class="control-label">
                  <g:message code="common.state"/>:<span class="require">*</span>
                </label>
				<div class="controls">
                 <div id="billingStateList"></div> 
				</div>
              </div>
              <div class="span12 control-group field-box">
                 <label for="billingPhoneNumber" class="control-label">
                  <g:message code="common.pnoneNumber"/>:<span class="require">*</span>
                </label>
                
                
                
				<div class="controls">
                 <span id="billingCallingCode"><g:message code="common.code"/></span>
                    <span>-</span>
                <input type="text" style="width: 165px;"
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="required:true, promptMessage: '<g:message code="common.pnoneNumber"/>',
                     invalidMessage:'<g:message code="common.invalidMessage.pnoneNumber"/>', placeHolder: '<g:message code="common.pnoneNumber"/>'
                     ,regExp: '[0-9-+ ]{4,20}'" id="billingPhoneNumber" name= "phoneNumber">
				</div>
              </div>
              
              
            <div class="span12 control-group field-box">
                 <label for="billingZip" class="control-label">
                   <g:message code="common.user.zip"/>:<span class="require">*</span>
                </label>
				<div class="controls">
                <input type="text"  
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="required:true, 
                     invalidMessage:'<g:message code="common.invalidMessage.zip"/>', placeHolder: '<g:message code="common.user.zip"/>',
                     regExp: '[0-9A-Z- a-z]{1,10}$'" id="billingZip" name= "zip" value="">
				</div>
              </div>
            <div class="span2 offset4">
              <!--<input type="reset" value="Cancel" class="reset" onclick="">-->
              <!--<span>OR</span>-->
              <button id="billingButton" class="updatebtn" type="button" data-dojo-type="dijit.form.Button" onclick="BillingDetail.update()"><g:message code="common.update"/></button>
              <img id="billingInfoLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading"/>' height='16' width='23' style="display: none"/>
            </div>
          </form>
      </div>
    </div>
<!--     <div class="span3">
      <div class="new_user_form inline-input">
       <div class="span12 field-box">
          <span id="editTaxNameLabel">Name of the Tax</span>
        </div>
        <div class="span12 field-box" id ="fixedDayContainer">
          <span id="editDescriptionLabel">A detail description about the tax</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span id="editTaxPercentageLabel">The allocated Percentage(%) of the tax</span>
        </div>
  </div>-->

		<div class="span4 profilePage overflow-text">
			<div class="row-fluid">
			    <input type="hidden" id="profileCurrentId">     
  			</div>
                    <h2 class="overflowLabel" style="line-height: 27px"><g:message code="common.password"/></h2>
			<div id="profilePage">
          		<form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="profileForm">  
				<div class="span1"></div>
            <div class="span12 control-group field-box">
              <label for="oldPassword"><g:message code="common.oldPassword"/><span class="require">*</span>:</label>
              <div class="controls">
              <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
              id="oldPassword" data-dojo-props="promptMessage:'<g:message code="common.promptMessage.password"/>', 
              invalidMessage: '<g:message code="common.invalidMessage.password"/>',required: 'true',
              placeHolder: '<g:message code="common.oldPassword"/>'"> 
              </div>
            </div>
            <div class="span12 control-group field-box">
              <label for="newPassword"><g:message code="common.newPassword"/><span class="require">*</span>:</label>
              <div class="controls">
			  <input type="password"  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                     onkeyup="ProfileDetail.checkPasswordStrength(this)" onchange="ProfileDetail.checkPasswordStrength(this)" 
                     id="newPassword" data-dojo-props="promptMessage:'<g:message code="common.promptMessage.password"/>', 
                     invalidMessage: '<g:message code="common.invalidMessage.password"/>',required: 'true',
                     placeHolder: '<g:message code="common.newPassword"/>'">
			</div>
            </div>
            <div class="span12 control-group field-box">
              <label for="confirmPassword"><g:message code="common.confirmPassword"/><span class="require">*</span>:</label>
              <div class="controls">
			  <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                     onblur="ProfileDetail.confirmPass()"  id="confirmPassword" data-dojo-props="promptMessage:'<g:message code="common.promptMessage.password"/>', 
                     invalidMessage: '<g:message code="common.invalidMessage.password"/>',required: 'required',
                     placeHolder: '<g:message code="common.confirmPassword"/>'">
			</div>
            </div>
            <div class="span2 offset4">
              <!--<input type="reset" value="Cancel" class="reset" onclick="">-->
              <!--<span>OR</span>-->
              <button id="resetBtn" class="resetbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ProfileDetail.resetNewPassword()"><g:message code="common.reset"/></button>
               <img id="resetBtnLoader" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" 
             alt="<g:message code="common.reset"/>" height="20" width="20">
            </div>
          </form>
      </div>
		</div>
	</div>
</div>

