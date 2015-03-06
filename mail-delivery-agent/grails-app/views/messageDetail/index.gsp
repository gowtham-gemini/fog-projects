<%@ page import="com.assistanz.app.MessageDetail" %>
<%@ page import="com.assistanz.app.ConfigLoader" %>
<!DOCTYPE html>
<html>
    <head>
        %{--auto refresh
        <meta http-equiv="refresh" content="20"> --}%
        <meta name="layout" content="main">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'common.css')}"/>
        <g:set var="entityName" value="${message(code: 'messageDetail.label', default: 'MessageDetail')}" />
        <title><g:message code="app_title"/> - <g:message code="mail.send.list.label"  /></title>
    </head>
    <body>
        <g:if test="${ConfigLoader.isConfigModified}">
            <div class="alert alert-warning">
                <i class="icon-warning-sign"></i>
                <g:message code="config.isModified.message"/>
                <a class="time" href="${createLink(uri: '/config/syncConfiguration')}" >
                    <i class="icon-exchange"></i>
                </a>
                
            </div>
        </g:if> 
       
        <div id="pad-wrapper" class="users-list">
            <div class="row header">
                <h3><g:message code="mail.send.list.label"  /></h3>
                <div id="button-set" class="col-md-10 col-sm-12 col-xs-12 pull-right">
                    <g:link class="pull-right btn-flat danger spacing" data-toggle="tooltip" title="${message(code: 'mail.list.purge')}" data-placement="top" action="deleteall" onclick="return confirm('${message(code: 'default.button.purge.confirm.message', default: 'Are you sure want to delete all?')}');" >
                        <i class="icon-trash"></i>
                    </g:link>
                    <g:link class="btn-flat btn btn-primary pull-right" action="resendAllUndeliveredMails">
                        <g:message code="mail.send.all.undelivered"/>
                    </g:link>
                    <g:link class="btn-flat success pull-right" action="create">
                        <g:message code="mail.send.label"/>
                    </g:link>
                    
                </div>
            </div>

            <!-- Users table -->
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <g:sortableColumn width="250px" property="qTo" title="${message(code: 'mail.to')}" />
                                <span class="line"></span>
                                <g:sortableColumn width="200px" property="date" title="${message(code: 'mail.sent.date')}" />
                                <span class="line"></span>
                                <g:sortableColumn width="200px" property="isDelivered" title="${message(code: 'mail.is.delivered')}" />
                                <span class="line"></span>
                                <g:sortableColumn width="200px" property="message.subject" title="${message(code: 'mail.Subject')}" />
                                <th style="text-align:center" class="col-md-3 ">
                                    <span class="line"></span><g:message code="mail.list.action"  />
                                </th>
                            </tr>
                        </thead>
                        <tbody>

                            <g:each in="${messageDetailInstanceList}" status="i" var="messageDetailInstance">
                                <tr >
                                    <td>${fieldValue(bean: messageDetailInstance, field: "qTo")}</td>
                                    <td> 
                                        <g:formatDate date="${messageDetailInstance.date}" type="datetime" style="LONG" timeStyle="SHORT"/>
                                    </td>
                                    <td>
                                        <g:if test="${messageDetailInstance?.isDelivered}">
                                            <g:message code="mail.delivered.yes" />
                                        </g:if> 
                                        <g:else>
                                            <g:message code="mail.delivered.no" />
                                        </g:else> 
                                    </td>

                                    <td>
                                        <g:if test="${messageDetailInstance?.message?.subject?.size() >20}">
                                            ${messageDetailInstance.message.subject.substring(0,20)}
                                            <g:link action="show" id="${messageDetailInstance.id}">
                                                    ... more
                                            </g:link>
                                        </g:if>    
                                        <g:else>
                                            <g:link action="show" id="${messageDetailInstance.id}">
                                                ${fieldValue(bean: messageDetailInstance, field: "message.subject")}
                                            </g:link>
                                        </g:else>    
                                    </td>
                                    <td>
                                        <div id="button-set">
                                            <g:form url="[resource:messageDetailInstance, action:'deleteParent']" method="DELETE">
                                                <g:actionSubmit class="pull-right btn-flat danger spacing " value="${message(code: 'default.button.delete.label', default: 'Delete')}"  data-placement="top" action="deleteParent"  onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
                                                    <i class="icon-trash"></i>
                                                </g:actionSubmit>
                                            </g:form>
                                            <g:if test="${!messageDetailInstance?.isDelivered}">        
                                                <g:link class="btn-flat icon large pull-right edit" action="resendHijax" resource="${messageDetailInstance}">
                                                    <g:message code="mail.button.resend.label" />
                                                </g:link>
                                            </g:if>    
                                        </div>
                                    </td>    
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>                
            </div>
            <div class="pagination pull-right">
                <g:paginate next="Next" prev="Back" total="${messageDetailInstanceCount ?: 0}" />
            </div>
            <!-- end users table -->
        </div>
    </body>
</html>
