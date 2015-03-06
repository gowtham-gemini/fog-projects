
<%@ page import="com.assistanz.app.Config" %>
<%@ page import="com.assistanz.app.ConfigLoader" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        
        <g:set var="entityName" value="${message(code: 'config.label', default: 'Config')}" />
        <title><g:message code="app_title"/> - <g:message code="config.label"/></title>
    </head>
    <body>
        <g:if test="${ConfigLoader.isConfigModified}">
            <div class="alert alert-warning">
                <i class="icon-warning-sign"></i>
                <g:message code="config.isModified.message"/>
                <g:link class="time" action="syncConfiguration" >
                    <i class="icon-exchange"></i>
                </g:link>
                
            </div>
        </g:if>    
        <g:if test="${flash.message}">
                    <div class="alert alert-success" role="status">
                        <i class="icon-remove-sign"></i>${flash.message}
                    </div>
        </g:if>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12 breadcrumbs">
                    <ul >
                        <li><a href="${createLink(uri: '/index')}"><i class="icon-home"></i></a></li>
                        <li>/</li>
                        <li><g:message code="config.label"/></li>
                    </ul>
                </div>
            </div>
        </div> 
        <div> 
    <div class="row-fluid form-wrapper"> 
        <ul class="span12 configList">
            <li>
                <g:link class="item" action="mailSetting" >
                    <i class="icon-envelope-alt"></i>
                    <g:message code="common.emailConfig"/>
                </g:link>
                <g:link class="time" action="mailSetting" >
                    <i class="icon-cog"></i>
                </g:link>
            </li>
            <li>
                 <g:link class="item" action="saveSetting" >
                     <i class="icon-group"></i>
                    <g:message code="common.saveConfig"/>
                 </g:link>
                <g:link class="time" action="saveSetting" >
                    <i class="icon-cog"></i>
                </g:link>
            </li>
            
        </ul>

    </div>
</div>
    </body>
</html>
