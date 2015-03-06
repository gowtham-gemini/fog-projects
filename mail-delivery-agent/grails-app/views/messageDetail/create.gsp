<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
                <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'common.css')}"/>
		<g:set var="entityName" value="${message(code: 'messageDetail.label', default: 'MessageDetail')}" />
		<title><g:message code="app_title"/> - <g:message code="mail.compose.title" /></title>
	</head>
	<body>
            <div id="pad-wrapper" class="new-user">
            <div class="row header">
                <div class="col-md-12">
                    <h3><g:message code="mail.compose.title"/></h3>
                </div>                
            </div>
                        
            <div class="row form-wrapper">
                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${messageDetailInstance}">
                            <ul class="errors" role="alert">
                                    <g:eachError bean="${messageDetailInstance}" var="error">
                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                    </g:eachError>
                            </ul>
			</g:hasErrors>
                <!-- left column -->
                <div class="col-md-12">
                    <g:form url="[resource:messageDetailInstance, action:'save']" >
				<fieldset class="form form-horizontal">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
                                     <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <g:submitButton name="create" class="btn btn-primary" value="${message(code: 'mail.button.send.label', default: 'Send')}" />
                                        </div>
                                     </div>       
				</fieldset>
			</g:form>
                  
                </div>
            </div>
        </div>
	</body>
</html>
