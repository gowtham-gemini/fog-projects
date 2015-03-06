<html>
    <head>
        <meta name='layout' content='admin'/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}"/>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">Account Profile: ${user.firstName} ${user.lastName} <g:link class="btn btn-primary pull-right" action="edit" controller="user" resource="${user}">Edit</g:link></h1>
                </div>
            </div>
        <g:if test="${message}">
            <div class="alert alert-success">${message}</div>
        </g:if>
        <g:if test="${messagError}">
            <div class="alert alert-danger">${messagError}</div>
        </g:if>
        <g:eachError bean="${error}" var="error">
            <div class="alert alert-danger"><g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></div>
        </g:eachError>
        <div class="stack stack-container">
            <div class="container">
                <div class="row-fluid">
                    <div class="col col-md-6">
                        <table>
                            <tbody>
                                <tr>
                                    <td>Username/email</td>
                                    <td>
                                        <span>${user.username} </span>
                                        <g:if test="${user.enabled}">
                                          <span class="label label-success">Active</span>  
                                        </g:if>
                                        <g:if test="${!user.enabled}">
                                          <span class="label label-danger">Disabled</span>  
                                        </g:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Organization</td>
                                    <td>${user.companyName}</td>
                                </tr>
                                
                                <tr>
                                    <td>Last Login</td>
                                    
                                    <td><g:formatDate date="${user.lastLogin}" type="datetime" style="MEDIUM"/></td>
                                </tr>
                                <tr>
                                    <td>Login IP</td>
                                    <td>${user.ipAddress}</td>
                                </tr>
                                
                                <tr>
                                    <td>Registration Date</td>
                                     <td><g:formatDate date="${user.registrationDate}" type="datetime" style="MEDIUM"/></td>
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
        </div>
    </body>
</html>
