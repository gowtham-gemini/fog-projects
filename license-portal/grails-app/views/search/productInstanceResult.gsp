<%@ page import="com.assistanz.fogpanel.licensemanager.ProductInstance" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="admin">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Instance List</title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="applications.page.resultforProductInstance" /></h1>
            </div>
        </div>
        <div class="stack stack-container" id="results">
            <div class="container">
                <div class="row-fluid">
                    <table class="table table-striped table table-hover table-bordered">
                        <thead>
                            <th>Instance ID</th>
                            <th>Product</th>
                            <th>User</th>
                            <th>Base License</th>
                            <th>Additional License</th>
                            <th>Total License</th>
                            <th>App Status</th>
                        </thead>
                        <tbody> 
                            <g:each in="${results}" var ="result">
                            <tr>
                                <td><g:link action="show" controller="productInstance" id="${result.id}"><g:formatNumber number="${result.id}" type="number" minIntegerDigits="12" groupingUsed="false" /></g:link></td>
                                <td>${result.product.name}</td>
                                <td>${result.user.username}</td>
                                <td>${result.baseLicense} Sockets</td>
                                <td>${result.additionalLicense} Sockets</td>
                                <td>${result.baseLicense + result.additionalLicense} Sockets</td>
                                <td>${result.appStatus}</td>
                            </tr> 
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
