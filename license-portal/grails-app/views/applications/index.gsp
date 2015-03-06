<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'applications.page.title', default: 'My Applications')}" />
        <title><g:message code="applications.page.title" /></title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <div class="row">
                    <div class='thumbnail' style="border: 0px;">
                        <div class="caption">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                               <div class="row"> 
                                    <h1 id="page-title"><g:message code="applications.page.title" /></h1>
                               </div>
                           </div>
                        </div>
                    </div>    
                </div>
            </div> 
        </div>
        
        <div class="container">
            <div class="row"> 
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class='thumbnail' style="border: 0px;">
                                <div class="caption">
                                    <g:if test="${productInstanceInstanceList}">
                                        <div class="row" style="overflow: auto">
                                            <table class="table table-hover table table-bordered table-striped">
                                                <thead>
                                                    <th>Instance Id</th>
                                                    <th>Custom Name</th>
                                                    <th>Product Name</th>
                                                    <th>Base Licenses</th>
                                                    <th>Additional Licenses</th>
                                                    <th>Inception Date</th>
                                                    <th>Expiration Date</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <g:each in="${productInstanceInstanceList}" status="i" var="productInstance">
                                                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                                            <td><g:link action="show" controller="applications" id="${productInstance.id}"><g:formatNumber number="${fieldValue(bean: productInstance, field: "id")}" type="number" minIntegerDigits="12" groupingUsed="false" /></g:link></td>

                                                            <td>${fieldValue(bean: productInstance, field: "name")}</td>

                                                            <td>${fieldValue(bean: productInstance, field: "product")}</td>

                                                            <td>${fieldValue(bean: productInstance, field: "baseLicense")} Sockets</td>

                                                            <td>${fieldValue(bean: productInstance, field: "additionalLicense")} Sockets</td>

                                                            <td><g:formatDate date="${productInstance.inceptionDate}" type="datetime" style="MEDIUM" /></td>

                                                            <td><g:formatDate date="${productInstance.expirationDate}" type="datetime" style="MEDIUM" /></td>
                                                            <g:if test="${productInstance?.status?.name() == 'ACTIVE'}">
                                                                <td><span class="label label-success">Active</span></td>
                                                            </g:if>
                                                            <g:if test="${productInstance?.status?.name() == 'FREE_TRIAL'}">
                                                                <td><span class="label label-warning">Evaluation</span></td>
                                                            </g:if>
                                                            <g:if test="${productInstance?.status?.name() == 'LOCKED'}">
                                                                <td><span class="label label-danger">Locked</span></td>
                                                            </g:if>
                                                            <g:if test="${productInstance?.status?.name() == 'DISABLED'}">
                                                                <td><span class="label label-danger">Disabled</span></td>
                                                            </g:if>
                                                            <g:if test="${productInstance?.status?.name() == 'EXPIRED'}">
                                                                <td><span class="label label-danger">Expired</span></td>
                                                            </g:if>
                                                            <g:if test="${productInstance?.status?.name() == 'EMERGENCY_TRIAL'}">
                                                                <td>
                                                                    <td><span class="label label-danger">Emergency Trial</span></td>
                                                                </td>
                                                            </g:if>
                                                            <g:if test="${productInstance?.status?.name() == 'VALID'}">
                                                                <td><span class="label label-success">Valid</span></td>
                                                            </g:if>
                                                        </tr>
                                                    </g:each>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="pagination">
                                                <g:paginate  controller="applications" action="index" total="${productInstanceCount ?: 0}" />
                                        </div>
                                    </g:if>
                                    <g:else>
                                        <div class="alert alert-info">
                                            No Instance! Click <g:link controller="applications" action="freeTrial" params="[productId: 1]" class="btn btn-primary" >here</g:link> to get a instance
                                            <!-- No Instance! Click <g:link controller="applications" action="newApp">here</g:link> to get a instance -->
                                        </div>
                                    </g:else>
                                </div>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>
        </div>    
    </body>
</html>
