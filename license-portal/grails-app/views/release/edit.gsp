<%@ page import="com.assistanz.fogpanel.licensemanager.Release" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin">
        <title>Edit: Release - ${releaseInstance?.product?.name?.encodeAsHTML()} - ${releaseInstance?.productVersion}</title>
    </head>
    <body><div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">
                    Release - ${releaseInstance?.product?.name?.encodeAsHTML()} - ${releaseInstance?.productVersion}
                    <g:form url="[resource:releaseInstance, action:'delete']" method="DELETE" class="pull-right">
                        <g:link class="btn btn-success" action="show" resource="${releaseInstance}"><g:message code="default.button.show.label" default="Show" /></g:link>
                        <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </g:form>

                </h1>
            </div>
        </div>
        
        
        <div class="stack stack-container">
            <div class="container">
                <div class="row-fluid">
                    <div id="edit-release" class="content scaffold-edit" role="main">
                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <g:hasErrors bean="${releaseInstance}">
                            <ul class="errors" role="alert">
                                <g:eachError bean="${releaseInstance}" var="error">
                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                    </g:eachError>
                            </ul>
                        </g:hasErrors>
                        <g:form url="[resource:releaseInstance, action:'update']" method="PUT" class="form-horizontal">
                            <g:hiddenField name="version" value="${releaseInstance?.version}" />
                            <fieldset class="form">
                                <g:render template="form"/>
                            </fieldset>
                            <fieldset class="pull-right">
                                <g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                            </fieldset>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
