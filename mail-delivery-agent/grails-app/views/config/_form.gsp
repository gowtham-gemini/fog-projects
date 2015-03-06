<%@ page import="com.assistanz.app.Config" %>



<div class="fieldcontain ${hasErrors(bean: configInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="config.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${configInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: configInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="config.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${configInstance?.description}"/>

</div>

