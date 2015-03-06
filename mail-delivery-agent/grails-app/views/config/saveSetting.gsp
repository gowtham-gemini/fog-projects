<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.assistanz.app.AppConstant" %>
<%@ page import="com.assistanz.app.ConfigLoader" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="app_title"/> - <g:message code="common.saveConfig"/></title>
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
                    <li><g:message code="common.saveConfig"/><li>
                </ul>
            </div>
        </div>
        <div id="pad-wrapper" class="form-page">
            <g:form url="[resource:configInstance, action:'updateSaveSetting']" >            
            <div class="row form-wrapper">
                <!-- left column -->
                <div class="col-md-8 column">
                    <div class="field-box">
                        <label for="" class="control-label"><g:message code="common.config.mails.save.type"/>:</label>
                        <label class="checkbox-inline">
                            <g:if test="${configInstance?.value == AppConstant.STORE_ALL_MAILS}">
                                <input type="radio" checked="true" name="saveSettingValue" id="saveMailAll" value="${AppConstant.STORE_ALL_MAILS}" />  
                            </g:if>    
                            <g:else>
                                <input type="radio"  name="saveSettingValue" id="saveMailAll" value="${AppConstant.STORE_ALL_MAILS}" />
                            </g:else>    
                            
                            <label for="" class=""><g:message code="common.config.mails.saveall"/></label>
                        </label>
                        <label class="checkbox-inline">
                            <g:if test="${configInstance?.value == AppConstant.STORE_UNDELIVED_MAILS}">
                                <input type="radio"  checked="true" name="saveSettingValue" id="saveMailUndelivered" value="${AppConstant.STORE_UNDELIVED_MAILS}" />
                            </g:if> 
                            <g:else>
                                <input type="radio"  name="saveSettingValue" id="saveMailUndelivered" value="${AppConstant.STORE_UNDELIVED_MAILS}"/>
                            </g:else>    
                            
                             <label for=""><g:message code="common.config.mails.saveundelivered"/></label>                         
                        </label>
                    </div>
                    <div class="field-box">  
                        <g:submitButton id="saveSettingButtton" name="updateSaveSetting" class="btn-flat success pull-right" value="${message(code: 'common.update')}" />
                    </div>
                </div>
            </div>
            </g:form>
        </div>

        
    </body>
</html>
