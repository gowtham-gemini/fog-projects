<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name='layout' content='main'/>
        <title><g:message code="global.signup.success.page.title"/></title>
    </head>
    <body>
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
    </body>
</html>
