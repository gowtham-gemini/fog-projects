<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/home/general">Account</a></li>
    <li>/</li>    
    <li>Billing</li>    
  </ul>
</div>
</div>
<!--<g:render template="viewAccountInfo" />-->
<div class="row-fluid">   
<ul class="nav nav-tabs span12">
  <li>
    <a href="#/user/home/general">General</a>
  </li>
  
  <li class="active">
    <a href="#/user/home/billing">Billing Info</a>
  </li>
    
     <li>
      <a href="#/user/home/profile">Password</a>
    </li>    
</ul>
</div>
<div  class="new-user">
  <div class="row-fluid header">
    <input type="hidden" id="billingCurrentId">       
    <input type="hidden" id="billingStateId"> 
    <input type="hidden" id="billingCountryId"> 
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span9 with-sidebar">
      <div class="container" id="billingPage">
          <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="billingForm">              
            
              <div class="span12 field-box">
                  <label for="streetAddress" class="control-label">                 
                  Address1
                </label>
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         data-dojo-props="required: true, promptMessage: 'Enter Address',
                            invalidMessage: 'Adrress is required',
                            trim: 'true', placeHolder: 'Street Address1'
                            " id="billingStreetAddress"  name="streetAddress"  value=""> 
              </div>
              <div class="span12 field-box">
                  <label for="streetAddress2" class="control-label">                
                  Address2
                </label>
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         data-dojo-props="promptMessage: 'Enter Address',
                            invalidMessage: 'Adrress is required', placeHolder: 'Address 2'
                            " id="billingStreetAddress2"  name="streetAddress">
              </div>
              <div class="span12 field-box">
                  <label for="city" class="control-label">                 
                  City
                </label>
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                           data-dojo-props="required: true, promptMessage: 'Enter city', 
                           invalidMessage: 'City is required', placeHolder: 'Your City'" 
                           id="billingCity" value="" name="city">
              </div>
              
              <div class="span12 field-box">
                  <label for="country" class="control-label">
                    Country
                </label>
                <div class="controls updatable elements">
                  <div id="billingCountryList" class="selectOption"></div> 
                </div>
                  
              </div>
              <div class="span12 field-box">
                  <label for="state" class="control-label">
                  
                  State
                </label>
                 <div id="billingStateList"></div> 
              </div>
              <div class="span12 field-box">
                 <label for="phoneNumber" class="control-label">
                  Phone number
                </label>
                 <span id="callingCode">code</span>
                    <span>-</span>
                <input type="text" style="width: 165px;"
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="required:true, promptMessage: 'Phone number',
                     invalidMessage:'Invalid Phone number.', placeHolder: 'Phone number'
                     ,regExp: '[0-9]{0,10}'" id="billingPhoneNumber" name= "phoneNumber" >
              </div>
            <div class="span12 field-box">
                 <label for="zip" class="control-label">
                 
                  Zip
                </label>
                <input type="text"  
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="required:true, 
                     invalidMessage:'Invalid zip code.', placeHolder: 'Zipcode',
                     accept: '',regExp: '[0-9]{5,10}'" id="billingZip" name= "zip" value="">
              </div>
            <div class="span11 field-box actions">
              <!--<input type="reset" value="Cancel" class="reset" onclick="">-->
              <!--<span>OR</span>-->
              <button type="button" data-dojo-type="dijit.form.Button" onclick="BillingDetail.update()">Update</button>
            </div>
          </form>
      </div>
    </div>
    <div class="span3">
      <div class="new_user_form inline-input">
<!--        <div class="span12 field-box">
          <span id="editTaxNameLabel">Name of the Tax</span>
        </div>
        <div class="span12 field-box" id ="fixedDayContainer">
          <span id="editDescriptionLabel">A detail description about the tax</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span id="editTaxPercentageLabel">The allocated Percentage(%) of the tax</span>
        </div>-->
      </div>
    </div>
  </div>
</div>

