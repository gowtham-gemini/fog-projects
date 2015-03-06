<%@ page import="com.assistanz.fogpanel.Config" %>



<div class="fieldcontain ${hasErrors(bean: configInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="config.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${configInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: configInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="config.value.label" default="Value" />
		
	</label>
	<g:textField name="value" value="${configInstance?.value}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: configInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="config.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${configInstance?.description}"/>
</div>

