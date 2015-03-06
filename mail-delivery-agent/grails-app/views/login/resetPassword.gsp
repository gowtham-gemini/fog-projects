<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>

    <head>
        <title><g:message code="app_title"/> - <g:message code='common.resetPassword.title'/></title>
        <meta name="layout" content="main">
        <!-- this page specific styles -->
        <link rel="stylesheet" href="${resource(dir: 'css/compiled/', file: 'signup.css')}"  media="screen" />
    </head>

    <body>
        <br/>
        <br/>
        <br/>
        <br/>
        <div class="login-wrapper">
            <div class="box">
                    <g:if test="${flash.message}">
                        <div class="alert alert-danger " role="status">
                            <i class="icon-remove-sign"></i>${flash.message}
                        </div>
                    </g:if>
                    <div class="content-wrap">
                        <g:form  url="[action:'savePassword',controller:'sensitiveContent']">
                            <h6><g:message code="common.resetPassword.title"/></h6>
                            <g:hiddenField name="user.id" value="${sec.loggedInUserInfo(field:"id")}"/>
                            <input class="form-control" type="password" title="Password must contain at least 6 characters, including UPPER/lowercase and numbers."  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="newPassword" id="newPassword"  required="true" placeHolder="${message(code:'common.newPassword')}">
                            <input class="form-control" type="password" title="Please enter the same Password as above." pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="confirmPassword" id="confirmPassword"  required="true" placeHolder="${message(code:'common.confirmPassword')}">

                            <div class="action">
                                <g:submitButton name="save" value="${message(code:'common.button.save')}" class="btn btn-flat success" id="passwordSubmitButton" style="color:#FFFFFF; margin-left: 180px !important; font-size: 18px;" />
                                <a class="btn btn-warning" type="button"  href="${createLink(uri: '/index')}">${message(code: 'mail.form.cancel')}</a>
                            </div>
                        </g:form>
                    </div>
                
            
            </div>
        </div>
    </body>
</html>