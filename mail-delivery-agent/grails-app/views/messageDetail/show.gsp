
<%@ page import="com.assistanz.app.MessageDetail" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
    <head>
        <meta name="layout" content="main">
            <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'common.css')}"/>
            <script type="text/javascript" src = "${resource(dir: 'js/app')}/showMail.js"></script>
            <g:set var="entityName" value="${message(code: 'messageDetail.label', default: 'MessageDetail')}" />
            <title><g:message code="app_title"/> - <g:message code="mail.show.title" /></title>
    </head>
    <body>
        <div id="pad-wrapper" class="user-profile">
            <div class="row header">
                <div class="col-md-4">
                    <h4 class="name">
                        <b><g:message code="mail.show.to"/>  </b><g:fieldValue bean="${messageDetailInstance}" field="qTo"/>
                        </h3>
                </div>
                <!--<a class="btn-flat icon pull-right delete-user" title="Delete user" >
            
                </a>-->
                <div id="button-set">
                    <g:form url="[resource:messageDetailInstance, action:'deleteParent']" method="DELETE">
                        <g:actionSubmit class="pull-right btn-flat danger spacing " value="${message(code: 'default.button.delete.label', default: 'Delete')}" data-toggle="tooltip" data-placement="top" action="deleteParent" title="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
                            <i class="icon-trash"></i>
                        </g:actionSubmit>
                    </g:form>
                    <g:if test="${!messageDetailInstance?.isDelivered}">           
                        <g:link class="btn-flat icon large pull-right edit" action="edit" resource="${messageDetailInstance}">
                            <g:message code="mail.button.resend.label" />
                        </g:link>
                        <a id="linkFailure" class="btn btn-danger large pull-right"  onclick="showPage.displayFailureReason();">
                            <g:message code="mail.button.failure.reason.show" />
                        </a>
                    </g:if>    
                </div>
            </div>    
            <div class="profile-box">
                <div class="col-md-12 ctrls">
                    <h5><b><g:message code="mail.Subject"/> : <g:fieldValue bean="${messageDetailInstance?.message}" field="subject"/></b></h5>
                    <p class="showmail"><g:fieldValue bean="${messageDetailInstance?.message}" field="body"/></p>
                </div>
                <div class="container section" id="failure-container-div" style="display:none">
                    <div class="row">
                        <div class="col-md-10">
                            <div class="alert alert-danger">
                                <i class="icon-remove-sign"></i>
                                ${messageDetailInstance?.failureReason}
                            </div>
                        </div>
                    </div>
                </div>
            </div>    
        </div>

    </body>
</html>
