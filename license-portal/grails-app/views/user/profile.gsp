<html>
    <head>
        <meta name='layout' content='main'/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><g:message code="global.edit.label"/></title>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}"/>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">Account Profile: ${user.firstName} ${user.lastName} 
                    <g:link class="btn btn-primary pull-right" action="editProfile" controller="user" >Edit</g:link>
                        <span class="pull-right">&nbsp;</span>
                    <g:link class="btn btn-primary pull-right" action="changeMyPassword" controller="user" >Change Password</g:link>
                    </h1>
                </div>
            </div>
            <div class="stack stack-container">
                <div class="container">
                <g:if test="${flash.message}">
                    <div class="alert alert-info">${flash.message}</div>
                </g:if>
                <g:if test="${message}">
                    <div class="alert alert-success">${message}</div>
                </g:if>
                <g:if test="${messagError}">
                    <div class="alert alert-danger">${messagError}</div>
                </g:if>
                <g:eachError bean="${error}" var="error">
                    <div class="alert alert-danger"><g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></div>
                </g:eachError>


                <div class="col col-md-4">
                    <table>
                        <tbody>
                            <tr>
                                <td>Username/email</td>
                                <td>${user.username}</td>
                            </tr>
                            <tr>
                                <td>Organization</td>
                                <td>${user.companyName}</td>
                            </tr>
                            <tr>
                                <td style="vertical-align: top">Address</td>
                                <td>${user.address1}, ${user.address2} <br> ${user.city}, <br> ${user.state} ${user.zip}<br> ${user.country} </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col col-md-3 pull-right">
                    <table>
                        <tbody>
                            <tr>
                                <td>Support Tickets</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <td>Licenses</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <td>Purchases</td>
                                <td>-</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div> 
        </div>
    </body>
</html>
