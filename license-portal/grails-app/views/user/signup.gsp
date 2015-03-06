<%@ page contentType="text/html;charset=UTF-8" %>
<!-- <html>
    <head>
       <meta name='layout' content='main'/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><g:message code="global.signup.title"/></title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="global.signup.header"/></h1>
            </div>
        </div>
        <div class="stack stack-container">
            <div class="signup">
                <div class="row-fluid">
                    <div class="col col-md-8">
                        <g:form class="form-horizontal" url="[action:'save',controller:'User']">
                            <div class="form-group">
                                <label for="firstName" class="control-label col-md-4"><g:message code="user.firstname.label"/></label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="firstName"  placeholder="First Name" name="firstName">
                                     <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "firstName" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>  
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="control-label col-md-4"><g:message code="user.lastname.label"/></label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="lastName"  placeholder="Last Name" name="lastName">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "lastName" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="control-label col-md-4"><g:message code="user.username.label"/></label>
                                <div class="col-md-6">
                                    <input type="email" required class="form-control" id="username"  placeholder="john@example.com" name="username">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "username" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label col-md-4"><g:message code="user.password.label"/></label>
                                <div class="col-md-6">
                                    <input type="password" required class="form-control" id="password"  placeholder="Password" name="password">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "password" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label col-md-4"><g:message code="user.confirmPassword.label"/></label>
                                <div class="col-md-6">
                                    <input type="password" required class="form-control" id="confirmPassword"  placeholder="Confirm Password" name="confirmPassword">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "confirmPassword" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="company" class="control-label col-md-4"><g:message code="user.companyname.label"/></label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" id="company"  placeholder="Company Name" name="companyName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address1" class="control-label col-md-4"><g:message code="user.address1.label"/></label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="address1"  placeholder="Address" name="address1">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "address1" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
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
                                <label for="city" class="control-label col-md-4"><g:message code="user.city.label"/></label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="city"  placeholder="City" name="city">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "city" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="state" class="control-label col-md-4"><g:message code="user.state.label"/></label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="state"  placeholder="State" name="state">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "state" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="country" class="control-label col-md-4"><g:message code="user.country.label"/></label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="country"  placeholder="Country" name="country">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "country" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="zip" class="control-label col-md-4"><g:message code="user.zip.label"/></label>
                                <div class="col-md-6">
                                    <input type="text" required class="form-control" id="zip"  placeholder="Zip" name="zip">
                                    <g:hasErrors bean="${userInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${userInstance}" var = "error" field = "zip" > 
                                                        <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-10">
                                    <g:submitButton name="${g.message(code: 'global.signup.label')}" class="btn btn-primary pull-right"/>
                                </div>
                            </div>
                        </g:form>
                    </div>
                    <div class="col col-md-3 well well-sm">
                        <div id='login'>
                            <div class='inner'>
                                <h3 class='fheader'><g:message code="springSecurity.login.header"/></h3>

                                <g:if test='${flash.message}'>
                                    <div class='login_message'>${flash.message}</div>
                                </g:if>

                                <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                                    <div class="row form-group">
										<div class="col-md-4">
                                        	<label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
										</div>
										<div class="col-md-8">
                                        	<input type='text' class='form-control' name='j_username' id='username'/>
                                    	</div>
										<div class="clear"></div>
									</div>

                                    <div class="row form-group">
										<div class="col-md-4">
                                        	<label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
										</div>
										<div class="col-md-8">
                                        	<input type='password' class='form-control' name='j_password' id='password'/>
										</div>
                                    </div>

                                    <div id="remember_me_holder" class="form-group">
                                        <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                                        <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
                                    </div>

                                    <div class="form-group">
                                        <input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}' class="btn btn-success"/>
                                    </div>
									<!--<div class="text-center col-md-12">
                                    <g:message code="auth.text.signup"/><g:link controller="user" action="signup"><g:message code="global.signup.label"/></g:link>      
                                	</div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 login-info-tip"></div>
                </div>
            </div> 
        </div>
    </body>
</html>-->
