<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name='layout' content='main'/>
        <title></title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">Recover your password</h1>
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
            
            
            <div class="row">
                <g:form class="form-horizontal" url="[action:'forgetPasswordSentMail',controller:'User']">
                    <div class="form-group">
                        <label for="email" class="control-label col-md-2"><g:message code="user.email.label"/></label>
                        <div class="col-md-3">
                            <input type="email" class="form-control" id="username" required="required" placeholder="john@example.com" name="username">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-3">
                            <g:submitButton name="${g.message(code: 'global.submit.label')}" class="btn btn-primary pull-right"/>
                            <g:link class="btn pull-right" action="dashboard" controller="home">Back to Home</g:link>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
    </body>
</html>
