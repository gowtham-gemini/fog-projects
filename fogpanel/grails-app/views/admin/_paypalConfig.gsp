<div class="row-fluid">
<div class="span12 breadcrumbs">
  
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
     <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
     <li>/</li>    
     <li><a href="#/admin/settings/paymentGatway"><g:message code="common.paymentGateway"/></a></li>
     <li>/</li>
     <li><g:message code="common.paypal"/></li>
     <li>/</li>
     <li><g:message code="common.settings"/></li>
  </ul>
</div>
</div>
<div class="row-fluid">
<ul class="nav nav-tabs span12">
  <li class="active">
    <a href="#/admin/settings/paypal">Settings</a>
  </li>
  <li>
    <a href="#/admin/settings/paymentConfig"><g:message code="common.processingFeeSetting"/></a>
  </li>    
</ul>
</div>
<div  class="new-user">  
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span8 with-sidebar" id="paypalCodePage">
      <div class="container">
          <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="paypalForm">
            <div class="span12 field-box control-group">
                  <label class="control-label settings_lable"><g:message code="paypal.connectionTimeOut"/>:<span class="require">*</span></label>
                  <div class="controls"> 
                  <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   required: 'required', placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="connectionTimeOut" id="connectionTimeOut"> 
                  </div>
              </div>
              <div class="span12 field-box control-group">
                  <label for="httpRetry" class="control-label settings_lable">
                    <g:message code="paypal.httpRetry"/>:
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                  <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   required: 'required', placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="httpRetry" id="httpRetry">  
                </div>
              </div>
              <div class="span12 field-box control-group">
                  <label for="readTimeOut" class="control-label settings_lable">                    
                    <g:message code="paypal.readTimeOut"/>:
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                  <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   required: 'required', placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="readTimeOut" id="readTimeOut">  
                </div>
              </div>
            <div class="span12 field-box control-group">
                 <label for="maxConnection" class="control-label settings_lable">                  
                  <g:message code="paypal.maxConnection"/>:
                  <span class="require">*</span>
                </label>
              <div class="controls">
                  <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   required: 'required', placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="maxConnection" id="maxConnection"> 
              </div>
            </div>
            <div class="span12 field-box control-group">
                 <label for="proxyPort" class="control-label settings_lable">                  
                  <g:message code="paypal.proxyPort"/>:
                  <span class="require">*</span>
                </label>
              <div class="controls">
                  <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   required: 'required', placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="proxyPort" id="proxyPort">  
              </div>
            </div>
            <div class="span12 field-box control-group">
                 <label for="proxyHost" class="control-label settings_lable">              
              <g:message code="paypal.proxyHost"/>:
              <span class="require">*</span>
            </label>
              <div class="controls">
                  <input type="test" 
                     data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage: 'i<g:message code="common.value.invalid"/>',
                     required: 'required',
                     placeHolder: '<g:message code="common.value"/>', 
                     promptMessage:'<g:message code="common.value"/>'" 
                     name="proxyHost" id="proxyHost"> 
              </div>
            </div>
            
            <div class="span12 field-box control-group">
                 <label for="useProxy" class="control-label settings_lable">                  
                  <g:message code="paypal.useProxy"/>:
                  <span class="require">*</span>
                </label>
              <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                  data-dojo-props="checked: false" id="useProxy">  
              </div>
            </div>
            <div class="span12 field-box control-group">
                 <label for="googleAppEngine" class="control-label settings_lable">            
            <g:message code="paypal.googleAppEngine"/>:
            <span class="require">*</span>
          </label>
              <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                  data-dojo-props="checked: false" id="googleAppEngine"> 
              </div>
            </div>
            <div class="span12 field-box control-group">
                 <label for="proxyUserName" class="control-label settings_lable">
             <g:message code="paypal.proxyUsername"/>:
          </label>
              <div class="controls">
                  <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="useProxy" id="proxyUserName">  
              </div>
            </div>
            <div class="span12 field-box control-group">
              <label for="proxyPassword" class="control-label settings_lable">
                <g:message code="paypal.proxyPassword"/>:
              </label>
              <div class="controls">
              <input type="text" 
               data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
               placeHolder: '<g:message code="common.value"/>', 
               promptMessage:'<g:message code="common.value"/>'" 
               name="proxyPassword" id="proxyPassword">  
            </div>
            </div>
            <div class="span12 field-box control-group">
              <label for="serviceEndPoint" class="control-label settings_lable">            
             <g:message code="paypal.serviceEndPoint"/>:
             <span class="require">*</span>
          </label>
              <div class="controls">
              <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   required: 'required', placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="serviceEndPoint" id="serviceEndPoint">
              </div>
            </div>
            <div class="span12 field-box control-group">
              <label for="clientID" class="control-label settings_lable">            
             <g:message code="paypal.clientId"/>:
             <span class="require">*</span>
          </label>
              <div class="controls">
              <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   required: 'required', placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="clientID" id="clientID">  
              </div>
            </div>
            <div class="span12 field-box control-group">
              <label for="clientSecret" class="control-label settings_lable">           
             <g:message code="paypal.clientSecret"/>:
              <span class="require">*</span>
          </label>
              <div class="controls">
              <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                   required: 'required', placeHolder: '<g:message code="common.value"/>', 
                   promptMessage:'<g:message code="common.value"/>'" 
                   name="clientSecret" id="clientSecret">
              </div>
            </div>
            
            
            <div class="pull-right">              
<!--                  <input type="reset" value="Cancel" class="reset" onclick="PaypalConfig.cancel()">
                  <span>OR</span>-->
              <button id="paypallButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="PaypalConfig.add();">
               <g:message code="common.update"/>
             </button>
              <img id="paypallLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
              </div>
          </form>
      </div>
</div>
<div class="span4">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span id="connectionTimeOutDescription"><g:message code="paypal.connectionTimeOutInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id="httpRetryDescription"><g:message code="paypal.httpRetry"/> </span>
        </div>
        <div class="span12 field-box">
          <span id="readTimeOutDescription"><g:message code="paypal.readTimeOut"/></span>
        </div>
        <div class="span12 field-box">
          <span id="maxConnectionDescription"><g:message code="paypal.maxConnection"/> </span>
        </div>
        <div class="span12 field-box">
          <span id="proxyPortDescription"><g:message code="paypal.proxyPort"/></span>
        </div>
        <div class="span12 field-box">
          <span id="proxyHostDescription"><g:message code="paypal.proxyHost"/></span>
        </div>
        <div class="span12 field-box">
          <span id="useProxyDescription"><g:message code="paypal.useProxy"/></span>
        </div>
        <div class="span12 field-box">
          <span id="googleAppEngineDescription"><g:message code="paypal.googleAppEngineInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id="proxyUserNameDescription"><g:message code="paypal.proxyUsernameInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id="proxyPasswordDescription"><g:message code="paypal.proxyPasswordInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id="serviceEndPointDescription"><g:message code="paypal.serviceEndPointInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id="clientIdDescription"><g:message code="paypal.clientIdInfo"/></span>
        </div>
        <div class="span12 field-box">
          <span id="clientSecretDescription"<g:message code="paypal.clientSecretInfo"/></span>
        </div>
      </div>
  </div>
</div>
</div>




  