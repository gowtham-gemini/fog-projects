<div class="row-fluid">  
<div class="span12 breadcrumbs">
  <ul>    
     <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings">Configuration</a></li>
    <li>/<li>
     <li><a href="#/admin/settings/billing">Billing</a></li>
    <li>/</li>    
     <li><a href="#/admin/settings/paymentGatway">Payment Gateway</a></li>
     <li>/</li>
     <li>Authorised.Net </li>
  </ul>
</div>
</div>
<div  class="new-user">
    
  <div class="row-fluid form-wrapper" id="authorizedPage">
        <!-- left column -->
        <div class="span9 with-sidebar">
            <div class="container">
                <form class="new_user_form form-horizontal" data-dojo-type="dijit.form.Form" id="authorizeNetForm">
                  <!--<div class="span12 field-box"></div>-->
                    <div class="span12 field-box control-group">
                        <label for="authorizeNetApiKey" class="control-label settings_lable">                          
                          AuthorizeNet Api Key:
                          <span class="require">*</span>
                        </label>
                      <div class="controls"> 
                      <input type="text" 
                        data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="invalidMessage: 'invalid Key',
                        required: 'required', placeHolder: 'Api Key', 
                        promptMessage:'Api Key'" 
                        name="authorizeNetApiKey" id="authorizeNetApiKey">                          
                    </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="authorizeNetApiSecret" class="control-label settings_lable">                          
                          AuthorizeNet Api Secret:
                          <span class="require">*</span>
                        </label>
                      <div class="controls"> 
                        <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: 'invalid Key',
                   required: 'required', placeHolder: 'Api Secret', 
                   promptMessage:'Api Key'" 
                   name="authorizeNetApiSecret" id="authorizeNetApiSecret"> 
                      </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="authorizeNetEnvironment"  class="control-label settings_lable">                            
                             AuthorizeNet Environment:
                             <span class="require">*</span>
                          </label>
                      <div class="controls"> 
                        <select data-dojo-type="dijit.form.Select" id="authorizeNetEnvironment">
                          <option value="SANDBOX">SANDBOX</option>
                          <option  value="PRODUCTION">PRODUCTION</option>
                      </select> 
                      </div>
                    </div>
                  <div class="pull-right">
<!--                    <input type="reset" value="Cancel" class="reset" onclick="AuthorizeNetConfig.cancel()">
                     <span>OR</span>-->
                    <button id="authorizedNetButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="AuthorizeNetConfig.add();">
                        Update
                      </button>
                     <img id="authorizedNetLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>   
                        
                    </div>
                </form>
            </div>
        </div>  
        <div class="span3">
          <div class="new_user_form inline-input">
            <div class="span12 field-box">
          <span id="authorizeNetApiKeyDescription"></span>
        </div>
        <div class="span12 field-box">
          <span id="authorizeNetApiSecretDescription"> </span>
        </div>
        <div class="span12 field-box">
          <span id="authorizeNetEnvironmentDescription"></span>
        </div>
          </div>
        </div>

    </div>
</div>


