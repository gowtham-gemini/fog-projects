<html>
    <head>
        <meta name='layout' content='main'/>
        <title><g:message code="springSecurity.login.title"/></title>
        <script type="text/javascript" src="${resource(dir: 'js/')}/application.js"></script>   
    </head>
    <body>
        <div class="stack stack-section-title">
            <g:if test='${flash.message}'>
                <div class="container">
                <div class='alert alert-info'>${flash.message}</div>
                </div>
            </g:if>            
            
            
            <div class="container">
                <div class="row-fluid">
                    <div class="col-md-8">
                        <h1 id="page-title"><g:message code="global.signup.header"/></h1>
                    </div>
                    <div class="col-md-4">
                        <h1 id="page-title">Sign In</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="stack stack-container container">
            <div class="signup">
                <div class="row-fluid">
                    <div class="col col-md-8">
                        <g:form class="form-horizontal" url="[action:'save',controller:'User']" >
                            <div class="form-group">
                                <label for="firstName" class="control-label col-md-4"><g:message code="user.firstname.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="firstName"  placeholder="First Name" name="firstName" value="${userInstance?.firstName}">
                                     <g:hasErrors bean="${userInstance}">
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "firstName" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>  
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="control-label col-md-4"><g:message code="user.lastname.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="lastName"  placeholder="Last Name" name="lastName" value="${userInstance?.lastName}">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "lastName" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="control-label col-md-4"><g:message code="user.username.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="email" required class="form-control" id="username"  placeholder="john@example.com" name="username" value="${userInstance?.username}">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "username" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label col-md-4"><g:message code="user.password.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="password" required class="form-control" id="password"  placeholder="Password" name="password">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "password" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword" class="control-label col-md-4"><g:message code="user.confirmPassword.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="password" required class="form-control" id="confirmPassword"  placeholder="Confirm Password" name="confirmPassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="company" class="control-label col-md-4"><g:message code="user.companyname.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" id="company"  placeholder="Company Name" name="companyName" value="${userInstance?.companyName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address1" class="control-label col-md-4"><g:message code="user.address1.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="address1"  placeholder="Address" name="address1" value="${userInstance?.address1}">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "address1" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address2" class="control-label col-md-4"></label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" id="address2" placeholder="Address 2" name="address2">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="city" class="control-label col-md-4"><g:message code="user.city.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="city"  placeholder="City" name="city" value="${userInstance?.address2}">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "city" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="state" class="control-label col-md-4"><g:message code="user.state.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="state"  placeholder="State" name="state" value="${userInstance?.state}">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "state" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="country" class="control-label col-md-4"><g:message code="user.country.label"/>*</label>
                                <div class="col-md-6">
                                    <g:countrySelect class="form-control" name="country" value="${userInstance?.country}" noSelection="['':'-Choose your country-']"/>
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "country" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="zip" class="control-label col-md-4"><g:message code="user.zip.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="zip"  placeholder="Zip" name="zip" value="${userInstance?.zip}">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "zip" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="col-md-offset-4 col-md-6">
                                <h3>Terms and Condition</h3>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-6">
                                    <textarea disabled="" class="form-control" name="termsConditions" rows="10">
WEBSITE TERMS & CONDITIONS

These terms and conditions govern your use of this website; by using our website, you accept these terms and conditions in full. If you disagree with any part of these terms and conditions, do not use this website.

LIMITATIONS OF LIABILITY

The information on this website is provided free-of-charge, and you acknowledge that it would be unreasonable to hold us liable in respect of this website and the information on this website.

Whilst we endeavour to ensure that the information on this website is correct, we do not warrant its completeness or accuracy; nor do we not commit to ensuring that the website remains available or that the material on this website is kept up-to-date.

To the maximum extent permitted by applicable law we exclude all representations, warranties and conditions (including, without limitation, the conditions implied by law of satisfactory quality, fitness for purpose and the use of reasonable care and skill).

Our liability is limited and excluded to the maximum extent permitted under applicable law. We will not be liable for any direct, indirect or consequential loss or damage arising under these terms and conditions or in connection with our website, whether arising in tort, contract, or otherwise - including, without limitation, any loss of profit, contracts, business, goodwill, data, income, revenue or anticipated savings.

However, nothing in these terms and conditions shall exclude or limit our liability for fraud, for death or personal injury caused by our negligence, or for any other liability which cannot be excluded or limited under applicable law.

RESTRICTED ACCESS

Access to certain areas of our website is restricted. We reserve the right to restrict access to areas of our website, or indeed our whole website, at our discretion.

If we provide you with a user ID and password to enable you to access restricted areas of our website or other content or services, you must ensure that that user ID and password is kept confidential. You accept responsibility for all activities that occur under your user ID or password.

We may disable your user ID and password at our sole discretion or if you breach any of the policies or terms governing your use of our website or any other contractual obligation you owe to us.

By creating a user account to access restricted areas of this website you agree to receive email communications from AssistanZ Networks Pvt Ltd, the makers of FogPanel. We will provide unsubscribe links to all promotional emails.

DOCUMENTATION WIKI / FORUMS / SUPPORT PORTAL / COMMENTS

You must not use our website in any way that causes, or may cause, damage to the website or impairment of the availability or accessibility of the website.

You must not use our website in any way which is unlawful, illegal, fraudulent or harmful, or in connection with any unlawful, illegal, fraudulent or harmful purpose or activity.

You must not use our website for any purposes related to marketing without our express written consent.

You must not use our website to copy, publish or send mass mailings or spam.

You must not use our website to copy, publish or send material which is illegal or unlawful, or material which could give rise to legal action under English and other applicable law. All material you copy, publish or send via our website must not be defamatory, obscene, indecent, hateful, discriminatory or inflammatory; such material must not infringe any person's intellectual property rights or rights of confidence, impinge upon any person's privacy, or constitute incitement to commit a crime; further, material must not be misleading, deceptive, sexually explicit, threatening, abusive, harassing or menacing.

We reserve the right to edit or remove any material posted upon our website.

We may take such action as we deem appropriate to deal with the posting of unsuitable material, including suspending or cancelling your account, restricting your access to our website, or commencing legal proceedings against you.

In respect of all material that you post on our website, you grant to us a worldwide, irrevocable, non-exclusive, royalty-free licence to use, reproduce, adapt, publish, translate and distribute such material in any media, together with the right to sub-licence such rights

VARIATION

We may revise these terms and conditions from time-to-time. You may have to keep watching the terms and conditions under http://www.fogpanel.com/policies/terms-and-conditions-of-use/.

ENTIRE AGREEMENT

These terms and conditions, together with our privacy policy constitute the entire agreement between you and us in relation to your use of our website, and supersede all previous agreements in respect of your use of this website.

LAW AND JURISDICTION

This notice will be governed by and construed in accordance with Indian law, under Coimbatore jurisdiction of Tamil Nadu.

CONTACT US 
If there are any questions regarding this terms and conditions of use, you may contact us using the information below. 

AssistanZ Networks Pvt. Ltd. 
1136 JayyseeliPlazaa, Dr.Radhakrishnan road, Gandhipuram,
Coimbatore, Tamil Nadu 641012, India
Email: sales@assistanz.com
Website: www.assistanz.com
Phone: +1 888 500 1070                                       
                                    </textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-6">
                                    <p class="clear clearfix">AssistanZ <g:link action="privacy" controller="home" target="_blank">Privacy Policy</g:link></p>
                                    <input type="checkbox" name="acceptTermsConditions" id="acceptTermsConditions" class="form-inline">
                                    <label for="acceptTermsConditions" class="control-label form-inline">Accept Terms & Conditions of Use and Privacy Policy</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-10">
                                    <input name="validator" id="validator" type="text" style="display: none">
                                    <g:submitButton onclick="return signup.checkPassword()" name="${g.message(code: 'global.signup.label')}" class="btn btn-primary pull-right"/>
                                </div>
                            </div>
                        </g:form>
                    </div>
                    <div class="col col-md-4 well well-sm col-xs-12">
                        <div id='login'>
                            <div class='inner'>
<!--                                <h3 class='fheader'><g:message code="springSecurity.login.header"/></h3>-->
                                <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                                    <div class="row form-group">
                                        <div class="col-md-4">
                                            <label for=''><g:message code="springSecurity.login.username.label"/>:</label>
                                        </div>
                                        <div class="col-md-8">
                                            <input type='text' class='form-control' name='j_username' id='signUpName'/>
                                        </div>
                                        <div class="clear"></div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-4">
                                            <label for=''><g:message code="springSecurity.login.password.label"/>:</label>
                                        </div>
                                        <div class="col-md-8">
                                            <input type='password' class='form-control' name='j_password' id='signUpPassword'/>
                                        </div>
                                    </div>
                                    <div class="form-group pull-left">
                                        <g:link action="recoverPassword" controller="user">Forgot Password</g:link>
                                    </div>

<!--                                    <div id="remember_me_holder" class="form-group pull-left">
                                        <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                                        <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
                                    </div>-->

                                    <div class="form-group pull-right">
                                        <input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}' class="btn btn-success"/>
                                    </div>
                                                                        <!--<div class="text-center col-md-12">
                                    <g:message code="auth.text.signup"/><g:link controller="user" action="signup"><g:message code="global.signup.label"/></g:link>      
                                        </div>-->
                                </form>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xs-12 visible-xs visible-xs login-info-tip" style="padding-top: 40px;"></div>
                    <div class="col-sm-4 col-md-4 col-lg-4 visible-sm visible-md visible-lg login-info-tip" style="padding-top: 40px;"></div>
                </div>
            </div> 
        </div>
    </body>
</html>