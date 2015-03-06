
<%@ page import="com.assistanz.fogpanel.licensemanager.ProductInstance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'productInstance.label', default: 'ProductInstance')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">
                    License for <g:formatNumber number="${productInstanceInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" />
                    <g:link class="btn btn-primary pull-right" action="edit" resource="${productInstanceInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <span class="pull-right">&nbsp;</span>
                    <g:actionSubmit class="btn btn-danger pull-right" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    <span class="pull-right">&nbsp;</span>
                    <g:actionSubmit value="Generate Checksum" class="btn btn-warning pull-right" name="productInstanceInstance"
                                    onclick="${remoteFunction(action: 'generateChecksum', controller:"ProductInstance",
                                    update: 'newChecksum', id: productInstanceInstance.id)}" />
                </h1>
            </div>
        </div>
        <div class="stack stack-container">
            <div class="container">
                <g:if test="${flash.message}">
                        <div class="alert alert-success">
                            ${flash.message}
                        </div>
                </g:if>
                               
                <div class="row-fluid">
                    <div id="newChecksum"></div>
                </div>
                
                <div class="row-fluid" style="display: none;">
                    <div class="col-md-4 col-md-offset-4"> 
                         <g:link controller="applications" action="showAddAdditionalLicense" id="${productInstanceInstance.id}" class="btn btn-primary pull-right">Buy Additional License</g:link>
                    </div>
                </div>
                <g:if test="${messageSuccess}">
                    <div class="alert alert-success" role="status">${messageSuccess}</div>
                </g:if>
                <div class="row-fluid">
                    <table>
                        <tbody>
                            <tr>
                                <td><label>Instance ID:</label></td>

                                <td><g:formatNumber number="${productInstanceInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" /></td>

                                <td><label>Custom Name:</label></td>

                                <td>
                                    ${productInstanceInstance.name}
                                </td>
                            </tr>
                            <tr>
                                <td><label>User:</label></td>
                                <td><g:link controller="user" action="profile" params="[id: productInstanceInstance?.user?.id]">${productInstanceInstance?.user?.username}</g:link></td>
                                <td><label>Organization</label></td>
                                <td>${productInstanceInstance?.user?.companyName}</td>
                            </tr>
                            <tr>
                                <td><label>Product Name:</label></td>

                                <td> ${productInstanceInstance.product.name} </td>

                                <td><label>Status:</label></td>
                                
                                <td>
                                    <g:if test="${productInstanceInstance?.status?.name() == 'ACTIVE'}">
                                          <span class="label label-success">Active</span>  
                                    </g:if>
                                    <g:if test="${productInstanceInstance?.status?.name() == 'FREE_TRIAL'}">
                                        <span class="label label-warning">Evaluation</span>
                                    </g:if>
                                    <g:if test="${productInstanceInstance?.status?.name() == 'LOCKED'}">
                                        <span class="label label-danger">Locked</span> 
                                    </g:if>
                                    <g:if test="${productInstanceInstance?.status?.name() == 'DISABLED'}">
                                        <span class="label label-danger">Disabled</span> 
                                    </g:if>
                                    <g:if test="${productInstanceInstance?.status?.name() == 'EMERGENCY_TRIAL'}">
                                        <span class="label label-danger">Emergency Trial</span>
                                    </g:if>
                                    <g:if test="${productInstanceInstance?.status?.name() == 'EXPIRED'}">
                                        <span class="label label-danger">EXPIRED</span>
                                    </g:if>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Base Licenses:</label></td>

                                <td> ${productInstanceInstance.baseLicense} Sockets </td>

                                <td><label>Additional Licenses:</label></td>

                                <td> ${productInstanceInstance.additionalLicense} Sockets </td>
                            </tr>
                            <tr>
                                <td><label>Used/Active Licenses:</label></td>

                                <td> ${productInstanceInstance.activeLicense} Sockets </td>

                                <td><label>Last Updated Time:</label></td>

                                <td><g:formatDate date="${productInstanceInstance.lastUpdatedOn}" format="dd/MM/yyyy HH:mm" /></td>
                            </tr>
                            <tr>
                                <td><label>Inception Date:</label></td>

                                <td><g:formatDate date="${productInstanceInstance.inceptionDate}" format="dd/MM/yyyy HH:mm" /></td>

                                <td><label>Expiration Date:</label></td>

                                <td><g:formatDate date="${productInstanceInstance.expirationDate}" format="dd/MM/yyyy HH:mm" /></td>
                            </tr>
                            <tr>
                                <td><label>Initial Version:</label></td>

                                <td>${productInstanceInstance.initialVersion}</td>

                                <td><label>Current Version:</label></td>

                                <td>
                                   
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!--
                <div class="row" style="display: none;">
                    <div class="col-md-1">
                        <g:form class="form-inline" role="form" id="${productInstanceInstance.id}">
                          <div class="form-group">
                            <label class="sr-only" for="licenseExpiryMonth">Month</label>
                            <select class="form-control" name="licenseExpiryMonth" id="licenseExpiryMonth">
                                <option value="3">Quarterly</option>
                                <option value="6">Half Yearly</option>
                                <option value="12">Annualy</option>
                            </select>
                          </div>
                          <g:actionSubmit class="btn btn-primary pull-right" value="Renew your license" action="renewLicense" />
                        </g:form>
                    </div>
                </div>
-->
                <div class="row-fluid">
                    <h3>License Verification Log</h3>
                </div>
                <div class="row-fluid">
                    <table class="table table-striped">
                        <thead>
                            <th>Last Updated Date</th>
                            <th>Host Name</th>
                            <th>Host IP</th>
                            <th>Sockets</th>
                            <th>Valid</th>
                            <th>Message</th>
                        </thead>
                        <tbody>
                            <g:each in="${validationLogList}" status="i" var="validationLog">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                    
                                    <td><g:formatDate date="${validationLog.lastUpdatedDate}" format="yyyy-MM-dd HH:mm:ss" /></td>
                                                                       
                                    <td>${fieldValue(bean: validationLog, field: "hostName")}</td>

                                    <td>${fieldValue(bean: validationLog, field: "hostIP")}</td>

                                    <td>${fieldValue(bean: validationLog, field: "sockets")}</td>
                                    
                                    <td>
                                        <g:if test="${validationLog?.valid == true }">
                                        <span class="fa fa-check-square-o" style="color: green"></span>
                                        </g:if>
                                        <g:if test="${validationLog?.valid == false }">
                                            <span class="fa fa-times" style="color: red"></span>
                                        </g:if>
                                    </td>
                                    <td>${fieldValue(bean: validationLog, field: "errorMessage")}</td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                    <g:if test="${!validationLogList}">
                        <div class="alert alert-info">
                            <p> The instance has not been registered and activated yet.</p>
                        </div>
                    </g:if>
                </div>
            </div>
        </div>
		
	</body>
</html>