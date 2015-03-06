
<%@ page import="com.assistanz.fogpanel.Config" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'config.label', default: 'Config')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-config" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-config" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'config.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="value" title="${message(code: 'config.value.label', default: 'Value')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'config.description.label', default: 'Description')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${configInstanceList}" status="i" var="configInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${configInstance.id}">${fieldValue(bean: configInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: configInstance, field: "value")}</td>
					
						<td>${fieldValue(bean: configInstance, field: "description")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${configInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
