<%@ page import="com.assistanz.fogpanel.licensemanager.ProductInstance" %>
<!DOCTYPE html>
<html>
    <head>
            <meta name="layout" content="admin">
            <g:set var="entityName" value="${message(code: 'productInstance.label', default: 'ProductInstance')}" />
            <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">
                    License for <g:formatNumber number="${productInstanceInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" />
                    <g:link class="btn btn-success pull-right" action="show" resource="${productInstanceInstance}">View</g:link>
                    <span class="pull-right">&nbsp;</span>
                    <g:actionSubmit class="btn btn-danger pull-right" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </h1>
            </div>
        </div>
        <div class="stack stack-container">
            <div class="container">
                <div class="row">
                    <div id="edit-productInstance" class="content scaffold-edit" role="main">
                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <g:hasErrors bean="${productInstanceInstance}">
                            <ul class="errors" role="alert">
                                    <g:eachError bean="${productInstanceInstance}" var="error">
                                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                    </g:eachError>
                            </ul>
                        </g:hasErrors>
                        <g:form class="form-horizontal" url="[resource:productInstanceInstance, action:'update']" method="PUT" >
                                <g:hiddenField name="version" value="${productInstanceInstance?.version}" />
                                <fieldset class="form">
                                        <g:render template="form"/>
                                </fieldset>
                                <fieldset class="buttons">
                                        <g:actionSubmit class="btn btn-primary pull-right" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                                </fieldset>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
