<h3>Account Profile</h3>
<a href="#/admin/dashboard">Back to Dashboard</a>

<div class="alert alert-danger" id="errorInfo">
  <span>
    The connection with the server was terminated
  </span>
</div>
<form id="accountProfileForm" data-dojo-type="dijit.form.Form">
  <div class="span8" id="zonePageContent">
    
    <div class="element">
      <label for="accountUserName"> <g:message code="common.fullName"/>: </label>
      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
           data-dojo-props="promptMessage:'Enter userName', 
           invalidMessage: 'Enter User',required: 'required',
           placeHolder: 'Your Name', regExp: '[a-z0-9A-z]{5,20}$'"
           id="accountUserName"> 
    </div>
    <div class="element">
      <label for="accountEmail"> <g:message code="common.email"/>:</label>
      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
         data-dojo-props="required:'true', promptMessage:'Enter email Address', 
         invalidMessage:'Invalid Email Adrress', trim:'true', 
         regExp: dojox.validate.regexp.emailAddress, placeHolder: 'email'"
         id="accountEmail" name="email"  value="">
    </div>
    <div class="element">
      <label for="accountStreetAddress"><g:message code="common.address"/>:</label>
      <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
             accept="" data-dojo-props="required: 'true', promptMessage: 'Enter Address',
            invalidMessage: 'Adrress is required',
            trim: 'true', placeHolder: 'Your Street Address', 
            regExp: '[A-Z 0-9a-z]{1,15}$'" id="accountStreetAddress"  
            name="streetAddress"  value="">
    </div>
    <div class="address">
      <div class="element city">
      <label for="accountCity"><g:message code="common.city"/></label>
      <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
           data-dojo-props="required: 'true', promptMessage: 'Enter city', 
           invalidMessage: 'City is required', trim: 'true', placeHolder: 'Your City'" 
           id="accountCity" value="" name="city">
      </div>
    </div>
    <div class="element">
      <label for="accountState"><g:message code="common.state"/>:</label>
      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="required:'true', promptMessage:'Enter state',
             invalidMessage:'invalid state', trim:'true', 
             placeHolder: 'Your State/Province'" id="accountState" 
             name="state" value="">
    </div>
    <div class="element">
      <label for="accountCountry"><g:message code="common.country"/>:</label>
      <input type="text" size="30" data-dojo-type="dijit.form.ValidationTextBox"
            data-dojo-props="required: 'true', promptMessage:'Enter country', 
            invalidMessage:'invalid country name', trim: 'true', 
            placeHolder: 'Your Country'" id="accountCountry" 
            name= "country" value= "">
    </div>
    <div class="element zip">
      <label for="accountZip"><g:message code="common.zip"/>:</label>
      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="required:true, invalidMessage:'Invalid zip code.', placeHolder: 'Zipcode',
             accept: '',regExp:'\\d{5}'" id="accountZip" name= "zip" value="">
    </div> 
    <div class="buttonGroup">
      <button onclick="DashboardAccountProfile.update()" data-dojo-type="dijit.form.Button"
            id="dashboardUserUpdateButton">
        Update
      </button>
    </div>
  
   
  </div>
</form>
<div class="span4" id ="userProfileListItems">	
  <div id="userProfileList">    
  </div>
</div>