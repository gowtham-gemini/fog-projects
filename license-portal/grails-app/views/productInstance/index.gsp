<%@ page import="com.assistanz.fogpanel.licensemanager.ProductInstance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'productInstance.label', default: 'ProductInstance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <script type="text/javascript" src="${resource(dir: 'js/')}/application.js"></script>   
	</head>
	<body>
                <div class="stack stack-section-title">
                    <div class="container">
                        <h1 id="page-title">
                            Instances
                        </h1>
                    </div>
                </div>
		<div class="stack stack-container">
            <div class="container">
                        <g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
                        <table class="table table-striped">
                            <thead>
					<tr>
					
						<g:sortableColumn property="id" title="${message(code: 'productInstance.id.label', default: 'Id')}" />
					
						<th><g:message code="productInstance.product.label" default="Product" /></th>
					
						<th><g:message code="productInstance.user.label" default="User" /></th>
					
						<g:sortableColumn property="baseLicense" title="${message(code: 'productInstance.baseLicense.label', default: 'Base Licence')}" />
					
						<g:sortableColumn property="additionalLicense" title="${message(code: 'productInstance.additionalLicense.label', default: 'Additional Licence')}" />
                                                
                                                <th><g:message code="license.total.label" default="Total License" /></th>
                                                
						<g:sortableColumn property="inceptionDate" title="${message(code: 'productInstance.inceptionDate.label', default: 'Inception Date')}" />
                                                
                                                <th><g:message code="productInstance.expirationDate.label" default="Expiration Date" /></th>
                                                
                                                <th><g:message code="productInstance.status.label" default="Status" /></th>
                                                
                                                 <th><g:message code="productInstance.app.status.label" default="App Status" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${productInstanceInstanceList}" status="i" var="productInstanceInstance">
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                        <td><g:link action="show" controller="productInstance" id="${productInstanceInstance.id}"><g:formatNumber number="${fieldValue(bean: productInstanceInstance, field: "id")}" type="number" minIntegerDigits="12" groupingUsed="false" /></g:link></td>
                                        <td>${fieldValue(bean: productInstanceInstance, field: "product")}</td>
                                        <td>${fieldValue(bean: productInstanceInstance, field: "user")}</td>
                                        <td>${fieldValue(bean: productInstanceInstance, field: "baseLicense")} Sockets</td>
                                        <td>${fieldValue(bean: productInstanceInstance, field: "additionalLicense")} Sockets</td>
                                        <td>${productInstanceInstance.baseLicense + productInstanceInstance.additionalLicense} Sockets</td>
                                        <td><g:formatDate date="${productInstanceInstance.inceptionDate}" format="dd/MM/yyyy HH:mm"/></td>
                                        <td><g:formatDate date="${productInstanceInstance.expirationDate}" format="dd/MM/yyyy HH:mm"/></td>
                                        
                                        <g:if test="${productInstanceInstance?.status?.name() == 'ACTIVE'}">
                                            <td><span class="label label-success">Active</span></td>
                                        </g:if>
                                        <g:if test="${productInstanceInstance?.status?.name() == 'FREE_TRIAL'}">
                                            <td><span class="label label-warning">Evaluation</span></td>
                                        </g:if>
                                        <g:if test="${productInstanceInstance?.status?.name() == 'LOCKED'}">
                                            <td><span class="label label-danger">Locked</span></td>
                                        </g:if>
                                        <g:if test="${productInstanceInstance?.status?.name() == 'DISABLED'}">
                                            <td><span class="label label-danger">Disabled</span></td>
                                        </g:if>
                                        <g:if test="${productInstanceInstance?.status?.name() == 'EXPIRED'}">
                                            <td><span class="label label-danger">Expired</span></td>
                                        </g:if>
                                        <g:if test="${productInstanceInstance?.status?.name() == 'EMERGENCY_TRIAL'}">
                                            <td>
                                                <td><span class="label label-danger">Emergency Trial</span></td>
                                            </td>
                                        </g:if>
                                        <g:if test="${productInstanceInstance?.status?.name() == 'VALID'}">
                                            <td><span class="label label-success">Valid</span></td>
                                        </g:if>
                                        
                                        
                                        
                                        <td>${productInstanceInstance.appStatus}</td>
                                    </tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate  controller="productInstance" action="index" total="${productInstanceCount ?: 0}" />
			</div>
                    </div>
                </div>
	</body>
</html>
