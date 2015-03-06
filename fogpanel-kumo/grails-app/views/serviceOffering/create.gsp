
<!doctype html>
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}"/>
        <g:set var="entityName" value="${message(code: 'serviceOffering.label', default: 'ServiceOffering')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>  
     <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
        
        
    <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
            data-dojo-config = "async: true, parseOnLoad:true">
    </script>
        
        <script type="text/javascript">
            require([
              "dojo/parser",
              "dojo/dom",
              "dijit/form/ValidationTextBox",
              "dijit/form/CheckBox",
              "dijit/form/Button"
            ]);
        </script>   

	</head>
	<body class="claro">
		<a href="#create-serviceOffering" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-serviceOffering" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${serviceOfferingInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${serviceOfferingInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" data-dojo-type="dijit.form.Button" label="create"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
