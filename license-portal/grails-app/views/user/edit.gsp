<html>
    <head>
        <meta name='layout' content='admin'/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><g:message code="global.edit.label"/></title>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}"/>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">Edit User: ${user.username}</h1>
            </div>
        </div>
        <div class="stack stack-container">
            <div class="container">
                <div class="row-fluid">

                    <g:if test="${message}">
                        <div class="alert alert-success">${message}</div>
                    </g:if>
                    <g:if test="${messagError}">
                        <div class="alert alert-danger">${messagError}</div>
                    </g:if>
                    <g:eachError bean="${error}" var="error">
                        <div class="alert alert-danger"><g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></div>
                    </g:eachError>                    

                    <div class="col col-md-8">
                        <g:form class="form-horizontal" url="[action:'updateUser',controller:'User', resource: user]">
                            <div class="form-group">
                                <label for="firstName" class="control-label col-md-3"><g:message code="user.firstname.label"/></label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="firstName" required="required" placeholder="first name" name="firstName" value="${user.firstName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="control-label col-md-3"><g:message code="user.lastname.label"/></label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="lastName" required="required" placeholder="last name" name="lastName" value="${user.lastName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="control-label col-md-3"><g:message code="user.username.label"/></label>
                                <div class="col-md-9">
                                    <input type="email" class="form-control" id="username" required="required" placeholder="john@example.com" name="username" value="${user.username}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="company" class="control-label col-md-3"><g:message code="user.companyname.label"/></label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="company" placeholder="company name" name="company" value="${user.companyName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address1" class="control-label col-md-3"><g:message code="user.address1.label"/></label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="address1" required="required" placeholder="address" name="address1" value="${user.address1}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address2" class="control-label col-md-3"><g:message code="user.address2.label"/></label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="address2" placeholder="address" name="address2" value="${user.address2}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="city" class="control-label col-md-3"><g:message code="user.city.label"/></label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="city" required="required" placeholder="city" name="city" value="${user.city}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="state" class="control-label col-md-3"><g:message code="user.state.label"/></label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="state" required="required" placeholder="state" name="state" value="${user.state}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="country" class="control-label col-md-3"><g:message code="user.country.label"/></label>
                                <div class="col-md-9">
                                    <g:countrySelect class="form-control" name="country" value="${user.country}" noSelection="['':'-Choose your country-']"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="zip" class="control-label col-md-3"><g:message code="user.zip.label"/></label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="zip" required="required" placeholder="zip" name="zip" value="${user.zip}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <g:submitButton name="${g.message(code: 'global.update.label')}" class="btn btn-primary pull-right"/>
                                </div>
                            </div>
                        </g:form>
                    </div>
                </div>
            </div> 
        </div>
    </body>
</html>
