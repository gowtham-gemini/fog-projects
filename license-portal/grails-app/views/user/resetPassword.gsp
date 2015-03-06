<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name='layout' content='main'/>
        <title>Reset Password</title>
    </head>
    <body>
        
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">Reset your password</h1>
            </div>
        </div>          
        
        
        <div class="stack stack-container">
            <div class="container">
                
                <g:if test="${messageVerify}">
                    <div class="alert alert-info">
                    ${messageVerify}
                   </div>
                </g:if>
                <g:if test="${messageSuccess}">
                    <div class="alert alert-success">
                    ${messageSuccess}
                   </div>
                </g:if>
                <g:if test="${messageError}">
                    <div class="alert alert-danger">
                    ${messageError}
                   </div>
                </g:if>                
                
                <div class="row-fluid">
                    <g:form class="form-horizontal" action="changePassword" controller= "user" id="${user.id}">
                        <div class="form-group">
                            <label for="password" class="control-label col-md-2"><g:message code="user.password.label"/></label>
                            <div class="col-md-3">
                                <input type="password" required class="form-control" id="password"  placeholder="Password" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword" class="control-label col-md-2"><g:message code="user.confirmPassword.label"/></label>
                            <div class="col-md-3">
                                <input type="password" required class="form-control" id="confirmPassword"  placeholder="Confirm Password" name="confirmPassword">
                                <input type="hidden" name="token" value="${token}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-3">
                                <g:submitButton name="${g.message(code: 'global.submit.label')}" class="btn btn-primary pull-right"/>
                            </div>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </body>
</html>
