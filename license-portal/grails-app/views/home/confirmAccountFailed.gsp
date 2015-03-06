<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name='layout' content='main'/>
        <title><g:message code="global.signup.success.page.title"/></title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">Email Verification and Account Activation</h1>
            </div>
        </div>
        <div class="stack stack-container">
            <div class="container">
                <div class="row-fluid">
                    <div class="col-md-12">
                        <g:if test="${flash.message}">
                            <div class="alert alert-info" role="status">${flash.message}</div>
                        </g:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
