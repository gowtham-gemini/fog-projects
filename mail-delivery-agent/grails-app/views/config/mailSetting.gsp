<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.assistanz.app.AppConstant" %>
<%@ page import="com.assistanz.app.ConfigLoader" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="app_title"/> - <g:message code="common.emailConfig"/></title>
         <script type="text/javascript" src = "${resource(dir: 'js/app')}/testMail.js"></script>
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

        <div class="row-fluid">
            <div class="span12 breadcrumbs">
                <ul>
                    <li><a href="${createLink(uri: '/index')}"><i class="icon-home"></i></a></li>
                    <li>/</li>
                    <li><a href="${createLink(uri: '/config')}"><g:message code="config.label"/></a></li>
                    <li>/<li>
                    <li><g:message code="common.emailConfig"/><li>
                </ul>
            </div>
        </div>

        <div id="pad-wrapper" class="form-page">

            <div class="row form-wrapper">
                
<!-- left column -->
                <div class="col-md-12 column">
                    <g:form id="mailConfigForm" class="form-horizontal" url="[resource:configInstance, action:'updateMailSetting']" >            
                        <!--div class="form-group">
                            <label for="applicationUrl" class="col-md-2 control-label">              
                                <g:message code="common.applicationUrl"/>:
                                <span class="mandatoryStar">*</span>
                            </label> 
                            <div class="col-md-5">
                                <input type="text" value="${configMap.get(AppConstant.MAIL_APPLICATIONURL)}" class="form-control" title='invalid applicationUrl' required placeHolder='Enter applicationUrl' name="applicationUrl" id="applicationUrl"> 
                            </div>
                            <div class="col-md-3">
                                <span><g:message code="admin.applicationUrlInfo"/></span>
                            </div>
                        </div-->
                        <div class="form-group">
                            <label for="host" class="col-md-2 control-label">              
                                <g:message code="common.host"/>:
                                <span class="mandatoryStar">*</span>
                            </label>     
                            <div class="col-md-5">
                                <input type="text" value="${configMap.get(AppConstant.MAIL_HOST)}" class="form-control" title='invalid host' required placeHolder='Enter host' name="host" id="host">   
                            </div>
                            <div class="col-md-3" >
                                <span><g:message code="admin.emailHostInfo"/></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="userName" class="col-md-2 control-label">              
                                <g:message code="common.username"/>:
                                <span class="mandatoryStar">*</span>
                            </label>
                            <div class="col-md-5">
                                <input type="email" value="${configMap.get(AppConstant.MAIL_USERNAME)}" class="form-control" title='invalid username' required placeHolder='Email' patten="" name="userName" id="userName">
                            </div>
                            <div class="col-md-3" >
                                <span><g:message code="admin.usernameInfo"/></span>
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="password" class="col-md-2 control-label">              
                                <g:message code="common.password"/>:
                                <span class="mandatoryStar">*</span>
                            </label>
                            <div class="col-md-5">
                                <input type="password" value="${configMap.get(AppConstant.MAIL_PASSWORD)}" class="form-control" title='invalid password' required placeHolder='Enter password' name="password" id="password">  
                            </div>
                            <div class="col-md-3">
                                <span><g:message code="admin.passwordInfo"/></span>
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="port" class="col-md-2 control-label">              
                                <g:message code="common.port"/>:
                                <span class="mandatoryStar">*</span>
                            </label>
                            <div class="col-md-5">
                                <input type="text" value="${configMap.get(AppConstant.MAIL_PORT)}" class="form-control" title='invalid Port' required placeHolder='Enter Port' pattern='[0-9]{2,5}' name="port" id="port"> 
                            </div>
                            <div class="col-md-3">
                                <span><g:message code="admin.emailPortInfo"/></span>
                            </div>
                        </div> 
                        <div class="form-group" id="secureConnectionValue">
                            <label for="secureConnection" class="col-md-2 control-label">                
                                <g:message code="common.secureConnection"/>:<span class="mandatoryStar">*</span>
                            </label>
                            <div class="col-md-5">
                                <g:set var="secureConnectionValue" value="${configMap.get(AppConstant.MAIL_SECURECONNECTION)}"/>
                                <g:if test="${secureConnectionValue == 'No'}">
                                    <input type="radio" id="secureConnectionNo" name="secureConnection" checked value="No"/>
                                </g:if>
                                <g:else>
                                    <input type="radio" id="secureConnectionNo" name="secureConnection" value="No"/>
                                </g:else>    
                                <label for="secureConnectionNo">No</label>
                                <g:if test="${secureConnectionValue == 'TLS'}">
                                    <input type="radio" id="secureConnectionTLS" name="secureConnection" checked value="TLS"/>
                                </g:if>
                                <g:else>    
                                    <input type="radio" id="secureConnectionTLS" name="secureConnection" value="TLS"/>
                                </g:else>    
                                <label for="secureConnectionTLS">TLS</label>
                                <g:if test="${secureConnectionValue == 'SSL'}">
                                    <input type="radio"id="secureConnectionSSL" name="secureConnection" checked value="SSL"/>
                                </g:if>
                                <g:else>    
                                    <input type="radio"id="secureConnectionSSL" name="secureConnection" value="SSL"/>
                                </g:else>  
                                
                                <label for="secureConnectionSSL">SSL</label>
                            </div>
                            <div class="col-md-3">
                                <span><g:message code="admin.emailsecureConnectionInfo"/></span>
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="from" class="col-md-2 control-label">               
                                <g:message code="common.from"/>:
                                <span class="mandatoryStar">*</span>
                            </label>
                            <div class="col-md-5">
                                <input type="email" value="${configMap.get(AppConstant.MAIL_FROM)}" class="form-control" title='invalid from address' required placeHolder='from address' name="from" id="from"> 
                            </div>
                            <div class="col-md-3">
                                <span><g:message code="admin.emailFromInfo"/></span>
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="from" class="col-md-2 control-label">               
                                <g:message code="common.senderName"/>:
                                <span class="mandatoryStar">*</span>
                            </label>
                            <div class="col-md-5">
                                <input type="text" value="${configMap.get(AppConstant.MAIL_SENDERNAME)}" class="form-control" title='invalid from sender name' required  placeHolder='Enter Sender Name' name="senderName" id="senderName"> 
                            </div>
                            <div class="col-md-3">
                                <span><g:message code="admin.senderNameInfo"/></span>
                            </div>
                        </div> 
                        <div class="field-box">  
                            <g:link class="btn-flat icon large pull-right edit" action="varifyMailConfig" resource="${configInstance}">
                                <g:message code="button.testmail" />
                            </g:link>
                            <span class="pull-right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <g:submitButton id="saveSettingButtton" name="updateMailSetting" class="btn-flat success pull-right" value="${message(code: 'common.update')}" />
                        </div>
                        </g:form>
                </div>

            </div>

        </div>
    </body>
</html>