<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin">
        <g:set var="entityName" value="${message(code: 'release.label', default: 'Release')}" />
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

                    <div id="create-release" role="main">
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
                        <g:form url="[resource:releaseInstance, action:'save']" >
                            <fieldset class="form-horizontal" class="form">
                                <g:render template="form"/>
                            </fieldset>
                            <fieldset class="buttons pull-right">
                                <g:submitButton class="btn btn-primary" name="create" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                            </fieldset>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
