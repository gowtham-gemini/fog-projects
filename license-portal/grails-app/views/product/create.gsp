<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin">
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="default.create.label" args="[entityName]" /></h1>
            </div>
        </div>

        <div class="stack stack-container">
            <div class="container">
                <div class="row">
                    <div id="create-product" class="content scaffold-create" role="main">

                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <g:hasErrors bean="${productInstance}">
                            <ul class="errors" role="alert">
                                <g:eachError bean="${productInstance}" var="error">
                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                    </g:eachError>
                            </ul>
                        </g:hasErrors>
                        <g:form class="form-horizontal" url="[resource:productInstance, action:'save']" >
                            <fieldset class="form">
                                <g:render template="form"/>
                            </fieldset>
                            <fieldset class="buttons">
                                <g:submitButton name="create" class="btn btn-primary pull-right" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                            </fieldset>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
