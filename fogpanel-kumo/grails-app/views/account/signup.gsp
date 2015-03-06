<!DOCTYPE html>
<html lang="${lang}">
  <head>    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="common.signUp"/></title>                
    <r:layoutResources/> 
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}"  media="screen" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/compiled', file: 'signin.css')}" media="screen"/>    
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'elements.css')}" media="screen"/>       
    <g:if test="${GrailsUtil.isDevelopmentEnv()}" >      
        <script>
             var dojoConfig = {
                async: 1,
                ioPublish: true,
                cacheBust: 0,
                parseOnLoad: false,
                tlmSiblingOfDojo: true,
                locale: "${lang}", 
                packages: [
                  { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
                  { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
                ]
              };
            </script>
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
        <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
    </g:if >
    <g:else >
        <script>
         var dojoConfig = {
            async: 1,
            ioPublish: true,
            cacheBust: 0,
            parseOnLoad: false,
            tlmSiblingOfDojo: true,
             locale: 'en-us',
            extraLocale: ['es-es'],
            packages: [
              { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
              { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
            ]
          };
        </script>
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
        <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
    </g:else>
    <script id="resultPage" type="text/template">
    <g:render template="resultPage" />
    </script>    
    <script type="text/javascript" src = "${resource(dir: 'js/app')}/password_strength.js">
    </script>  
    <script type="text/javascript"  src = "${resource(dir: 'js')}/app.js"></script>
    <script type="text/javascript">      
      app.setConfig({
             baseURL: "${baseUrl}",
             homepage: "",
             context: "${contextPath}"
           });        
    </script> 
  </head>
  <body class="fog-classic" id="signupPage">
    <div class="row-fluid">
      <div id="loader"></div>
      </div>    
      <div class="row-fluid login-wrapper">
        <div id="wrapper" class="span12">
          <div class="row-fluid signup_panel_logo">          
            <div>
                <g:if test="${logoUrl == ''}">                 
                    <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo"/>                 
                </g:if>
                <g:else>                 
                    <img src="${logoUrl}" class="logo fogPanelLogo" />               
                </g:else>                
            </div>         
          </div> 
          <div class="wizard_container span6 welcome_wiz">
            <div class="row-fluid">
              <div class="span12 signup_title">
                <h1><g:message code="signup.type.${signUPType}"/></h1>
              </div>
            </div>
            <div data-dojo-type="FogPanel.Wizard" data-dojo-props="attrib:{steps:{'1' : '<g:message code="signup.wizard.title.personalInfo"/>', 
                 '2': '<g:message code="signup.wizard.title.contactInfo"/>','3' : '<g:message code="signup.wizard.title.billingInfo"/>'}, deSelect: true}" data-dojo-id="setupWizard" id="signupWizard">     
            </div>
              <input type="hidden" id="accountType" value="${accountType}">
              <input type="hidden" id="cardRequired" value="${cardRequired}">
              <input type="hidden" id="userNameStatus">                 
              <div class="row-fluid">		  
                <div id="signup_wizard" data-dojo-type="dojox.widget.Wizard" 
               data-dojo-props="hideDisabled: true">
           
                  <div id="wizard_page_1" data-dojo-type="dojox.widget.WizardPane" 
                 data-dojo-props="passFunction:'app.signup.authetication', canGoBack: false" >
                    <div class="row-fluid">                
                      <form data-dojo-type="dijit.form.Form" class="form-horizontal">                
                  
                <div class="row-fluid">
                  <div class="control-group span9"> 
                    <label for="newEmail" class="control-label" code>
                      <g:message code="common.email"/>
                     <span class="require">*</span>
                    </label>
                    <div class="controls">
                      <input data-dojo-type="dijit.form.ValidationTextBox" 
                             data-dojo-props="required:true, promptMessage:'<g:message code="common.promptMessage.email"/>', 
                             missingMessage:'<g:message code="common.promptMessage.email"/>', 
                             placeHolder: '<g:message code="common.email"/>', regExp: dojox.validate.regexp.emailAddress" 
                             id="newEmail" value="${invitationEmail}" onblur="app.signup.clearEmail(this)">
                    </div>
                    
                  </div> 
                  <div class="span3 validationErrorMsg" id="emailValidationMessage">
                    <span id="invalidEmailMsg">Email Already Exists</span>
                  </div>
                </div>
                <!--<div class="row-fluid">-->
                  <!--<div class="control-group span9">--> 
<!--                    <label for="confirmEmail" class="control-label">
                      Confirm Email
                      <span class="require">*</span>
                    </label>-->
<!--                    <div class="controls">
                      <input data-dojo-type="dijit.form.ValidationTextBox"  
                             data-dojo-props="required:true, promptMessage:'Enter Email Address', 
                             missingMessage:'Enter Email Address', trim:'true', 
                             placeHolder: 'Confirm Email', regExp: dojox.validate.regexp.emailAddress" id="confirmEmail" 
                             onKeyUp="confirmEmail(this)">
                    </div>-->
                  <!--</div>-->
<!--                  <div class="span3 validationErrorMsg" id="emailValidateMsg">
                  <span>Email do not match</span>
                </div>-->
                <!--</div>-->
                <div class="row-fluid">
                <div class="control-group span9"> 
                  <label for="firstName" class="control-label">
                    <g:message code="common.firstName"/>
                    <span class="require">*</span>
                  </label>
                  <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                           data-dojo-props="promptMessage:'<g:message code="common.promptMessage.firstName"/>', 
                           missingMessage: '<g:message code="common.promptMessage.firstName"/>',required: true,
                           placeHolder: '<g:message code="common.firstName"/>'" 
                           name="firstName" id="firstName" value="${invitationName}"> 
                  </div>
                </div> 
                
            </div>
              <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="lastName" class="control-label">
                  <g:message code="common.lastName"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                         data-dojo-props="promptMessage:'<g:message code="common.promptMessage.lastName"/>', 
                         missingMessage: '<g:message code="common.promptMessage.lastName"/>',required: true,
                         placeHolder: '<g:message code="common.lastName"/>'" name="lastName" id="lastName"> 
                </div>
              </div> 
            </div>
                <div class="row-fluid">
                  <div class="control-group span9"> 
                    <label for="newPassword" class="control-label">
                      <g:message code="common.password"/>
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                      <input type="password" value=""  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                             onkeyup="checkPasswordStrength(this.id)" onchange="checkPasswordStrength(this.id)" id="newPassword" 
                             data-dojo-props="promptMessage:'<g:message code="common.promptMessage.password"/>', 
                             missingMessage: '<g:message code="common.promptMessage.password"/>',required: true,
                             placeHolder: '<g:message code="common.password"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,15}'">
                    </div>                    
                  </div>                  
                  <div class="span3">
                    <div id="password_strength" style="display: none; margin-top: 5px;">
                      <div style="width: 100px; border: #CCCCCC 1px solid;">
                        <div id="progress_bar" style="height: 5px; border: #FFFFFF 0px solid; font-size: 1px; background-color: #FFD700;"></div>
                      </div>
                      <span id="strength_text" style="font-family: Arial; font-size: 10px; color: #333333;">Weak</span>
                      <input type="hidden" name="strength_id" id="strength_id" value="1" />
                    </div>
                  </div>
                </div>
                <div class="row-fluid">
                  <div class="control-group span9"> 
                    <label for="confirmPassword" class="control-label">
                      <g:message code="common.confirmPassword"/>
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                      <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                             onKeyUp="confirmPassword(this)" onmouseout="confirmPassword(this)"  id="confirmPassword" 
                             data-dojo-props="promptMessage:'<g:message code="common.promptMessage.confirmPassword"/>', 
                             missingMessage: '<g:message code="common.promptMessage.confirmPassword"/>',required: true,
                             placeHolder: '<g:message code="common.confirmPassword"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}'">
                    </div>
                  </div>
                  <div class="span3 validationErrorMsg" id="passwordValidateMsg">
                  <span><g:message code="common.passwordMismatch"/></span>
                </div>
                </div>
                
                </form>
            </div>
            </div>
            <div data-dojo-type="dojox.widget.WizardPane" id="wizard_page_2" 
                 data-dojo-props="passFunction:app.signup.authetication, canGoBack: false" style="height: auto">
              
              <form data-dojo-type="dijit.form.Form" class="form-horizontal">
              
              <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="companyName" class="control-label">
                  <!--<span class="require">*</span>-->
                   <g:message code="common.companyName"/>
                </label>
                <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                         data-dojo-props="promptMessage:'<g:message code="common.promptMessage.companyName"/>',                         
                         placeHolder: '<g:message code="common.companyName"/>'"
                         name="companyName" id="companyName"> 
                </div>
              </div> 
              <!--<div class="row-fluid">
                <p>Please provide us with your contact information.</p>
              </div>-->
            </div>
              <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="streetAddress" class="control-label">
                   <g:message code="common.address1"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                   <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         data-dojo-props="required: true, promptMessage: '<g:message code="common.promptMessage.address1"/>',
                            missingMessage: '<g:message code="common.promptMessage.address1"/>',
                            trim: 'true', placeHolder: '<g:message code="common.address1"/>'
                            "id="streetAddress"  name="streetAddress">
                </div>
              </div>                 
            </div>
               <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="streetAddress2" class="control-label">                
                  <g:message code="common.address2"/>
                </label>
                <div class="controls">
                   <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         data-dojo-props="promptMessage: '<g:message code="common.promptMessage.address2"/>',
                            missingMessage: '<g:message code="common.promptMessage.address2"/>', placeHolder: '<g:message code="common.address2"/>'"
                            id="streetAddress2"  name="streetAddress">
                </div>
              </div>                 
            </div>
                
              <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="city" class="control-label">
                  <g:message code="common.city"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                           data-dojo-props="required: true, promptMessage: '<g:message code="common.promptMessage.city"/>', 
                           missingMessage: '<g:message code="common.promptMessage.city"/>', placeHolder: '<g:message code="common.city"/>'" 
                           id="city" value="" name="city">
                </div>
              </div> 
            </div>
              <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="country" class="control-label">
                  <g:message code="common.country"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                   <div id="countryList"></div>
                </div>
              </div> 
                <div class="span3 validationErrorMsg" id="countryErrorMsg">
                  <span>Country is required</span>
                </div>
            </div>
              <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="state" class="control-label">
                  <g:message code="common.state"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                  <div id="stateList"></div>
                </div>
              </div>
              </div>
            <div class="row-fluid">
            <div class="control-group span9"> 
                <label for="phoneNumber" class="control-label">
                  <g:message code="common.pnoneNumber"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                    <span id="callingCode"><g:message code="common.code"/></span>
                    <span>-</span>
                <input type="text" style="width: 165px;"
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="required:true, promptMessage: '<g:message code="common.promptMessage.pnoneNumber"/>',
                     missingMessage:'<g:message code="common.promptMessage.pnoneNumber"/>',invalidMessage: '<g:message code="common.invalidMessage.pnoneNumber"/>', placeHolder: '<g:message code="common.pnoneNumber"/>',
                     regExp: '[0-9-+ ]{4,20}$'" id="phoneNumber" name= "phoneNumber">
                </div>
              </div> 
            </div>
            <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="zip" class="control-label">
                  <g:message code="common.zip"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                 <input type="text"  
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="required:true, 
                     missingMessage:'<g:message code="common.promptMessage.zip"/>', promptMessage: '<g:message code="common.promptMessage.zip"/>', placeHolder: '<g:message code="common.zip"/>',
                     invalidMessage: '<g:message code="common.invalidMessage.zip"/>', regExp: '[0-9A-Z- a-z]{1,10}$'" id="zip" name= "zip">
                </div>
              </div> 
            </div>
              </form>
             
            </div>
            <div data-dojo-type="dojox.widget.WizardPane" id="wizard_page_3" 
                 data-dojo-props="doneFunction:app.signup.done_func, canGoBack: false">
              <div class="row-fluid">
                <div class="alert alert-error" id="signupFailedMsg" style="display: block">                
                  <i class="icon-remove-sign"></i>
                  <g:message code="signup.failed.error.msg"/>
                </div>
              </div>
              
               <div class="row-fluid">
              <form data-dojo-type="dijit.form.Form" class="form-horizontal" id="signupForm">
              <div class="row-fluid">
              <div class="validation" id="promoCodeError"></div>
              <div class="validation" style="color: #1fd055" id="promoCodeValid">
                <span>
                  <g:message code="signup.promoCode.valid"/>
                </span>
              </div>
            </div>
            <g:if test="${accountType.equals("TRIAL")}">
              <div class="row-fluid">
                <div class="control-group span9"> 
                  <label for="promotionalCode" class="control-label">
                    <g:message code="common.promotionalCode"/>
                    <span class="require">*</span>
                  </label>
                    <div class="controls">
                      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                             data-dojo-props="promptMessage:'<g:message code="common.promptMessage.promotionalCode"/>', required: true, 
                              missingMessage:'<g:message code="common.promptMessage.promotionalCode"/>',
                              placeHolder: '<g:message code="common.promotionalCode"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{1,100}'"
                              id="promotionalCode" name="promotionalCode" onkeyUp ="app.signup.validatePromoCode(this)">
                    </div>
                </div> 
              </div>
            </g:if>
            <g:else>
              <div class="row-fluid">
                <div class="control-group span9"> 
                  <label for="promotionalCode" class="control-label">
                      <g:message code="common.promotionalCode"/>
                  </label>
                    <div class="controls">
                      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                             data-dojo-props="promptMessage:'<g:message code="common.promptMessage.promotionalCode"/>',
                              missingMessage:'<g:message code="common.promptMessage.promotionalCode"/>',
                              placeHolder: '<g:message code="common.promotionalCode"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{1,100}'" 
                              id="promotionalCode" name="promotionalCode" onkeyUp="app.signup.validatePromoCode(this)">
                    </div>
                </div>  
              </div>
            </g:else>
                <g:if test="${accountType.equals("RETAIL") && cardRequired.equals("TRUE")}">
                    <div class="row-fluid" id="creditCardPage"> 
                      <div id="cardDetails">
                        <div class="row-fluid">
                          <div class="control-group span9"> 
                            <label for="newCardNumber" class="control-label">
                              <g:message code="common.cardNumber"/>
                              <span class="require">*</span>
                            </label>
                            <div class="controls">
                              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="newCardNumber"
                               data-dojo-props="missingMessage: '<g:message code="common.promptMessage.cardNumber"/>',                                
                               invalidMessage:'<g:message code="common.invalidMessage.cardNumber"/>', required: 'true',
                               placeHolder: '<g:message code="common.cardNumber"/>', regExp: '[0-9]{16,16}'">
                            </div>
                          </div> 
                        </div>
                        <div class="row-fluid">
                          <div class="control-group span9"> 
                            <label for="newCvvNumber" class="control-label">
                            <g:message code="common.cvvNumber"/>
                            <span class="require">*</span>
                            </label>
                            <div class="controls">
                              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="newCvvNumber"
                                     data-dojo-props="missingMessage: 'Invalid CVV Number', invalidMessage:'<g:message code="common.invalidMessage.CVV"/>',required: 'true',
                                     placeHolder: '<g:message code="common.cvvNumber"/>',  missingMessage:'<g:message code="common.promptMessage.CVV"/>', regExp: '[0-9]{3,4}'">
                            </div>
                          </div> 
                        </div>
                        <div class="row-fluid">
                          <div class="control-group span9"> 
                            <label for="newCardType" class="control-label" for="newCardType"><g:message code="common.cardType"/></label>
                            <div class="controls">
                              <select class="valid" name="newCardType" data-dojo-type="dijit.form.Select" 
                                      data-dojo-props="maxHeight: -1" id="newCardType">
                                <option value="visa"><g:message code="common.visa"/></option>
                                <option value="mastercard"><g:message code="common.master"/></option>
                                <option value="americanexpress"><g:message code="common.americanExpress"/></option>
                              </select>
                            </div>
                          </div>
                        </div>
                        <div class="row-fluid">
                          <div class="control-group span9"> 
                            <label for="newExpiryMonth" class="control-label" for="newExpiryMonth"><g:message code="common.expiry"/></label>
                            <div class="controls">
                                <div class="span2">
                                    <select class="valid" name="newExpiryMonth" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: -1" id="newExpiryMonth">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                        <option value="9">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                    </select>
                                </div>
                                <div class="span1"><span>/</span></div>
                                <div class="span4"><div id="newYearList"></div></div>
                            </div>
                          </div>
                        </div>                        
                      </div>      
                    </div>
                </g:if>
                <div class="row-fluid">
              <div class="control-group span9"> 
                <label for="captcha" class="control-label">
                  <g:message code="common.captcha"/>
                  <span class="require">*</span>
                </label>
                <div class="controls">
                   <input type="text" size="30" data-dojo-type="dijit.form.ValidationTextBox"
                             id="captcha" name="captcha" data-dojo-props="required:true,  
                             missingMessage:'<g:message code="common.captcha"/>', 
                             invalidMessage: '<g:message code="common.invalidMessage.captcha"/>', 
                             promptMessage:'<g:message code="common.promptMessage.captcha"/>',
                             regExp: '[a-zA-Z0-9]{1,8}', placeHolder: 'Captcha'" 
                             onkeyup="app.signup.validateCaptcha()">
                </div>
              </div> 
            </div>
                <div class="row-fluid">
                  <div class="span3 offset3">
                    <img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}"/>
                  </div>
                </div>
                <div class="row-fluid">
              <!--<div class="">-->
              <div class="element terms">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                         data-dojo-props="checked: false" id="agreement" name="agreement">
                <label for="agreement" class="span4"><g:message code="signup.acceptThe"/>
                    <a href="#" onClick="app.signup.show_condition()"><g:message code="signup.termsAndConditions"/></a></label>
                <span class="validation" id="conditionExceptionMsg"><g:message code="signup.termsCondition.info"/></span>
              </div>            
              <div data-dojo-type="dijit.Dialog"  id="dialog" style="height: 400px;" title="<g:message code="signup.termsAndConditions"/>" class="customDialog span8">
                <div style="overflow-x: hidden; overflow-y: scroll; height: 350px">
                  <div id="contentArea">
                  </div>
                </div>
              </div>
                
           
            </div>
                <div class="row-fluid">
                 
                  <div class="span6 offset4">
                    <img id="signupLoader" style="display: none;" 
                         src="${resource(dir: 'images')}/vmload.gif" alt="<g:message code="common.reset"/>"> </br>
                    <p id="signupLoadingMessanger"></p>
                    
                    
                  </div>
                  
                </div>
              </form>
            </div>
          </div>
            <g:link controller='login'>
              <!--<button type="button" id="cancelButton" class="btn-glow primary login cancelbtn">Cancel</button>-->
			  <button type="button" id="cancelButton" class="btn-glow primary login cancelbtn"><g:message code="common.cancel"/></button>
            </g:link>
      
            </div>    
        </div>
          
          <!--</div>-->
          </div>
          
          
          
      </div>
     
      </div>       
  </div>
    </div> 
    <script type="text/javascript">
      app.signup.require();
    </script>
  </body>
        </html>
  
