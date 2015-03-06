
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="admin">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="applications.page.resultforProduct" /></h1>
            </div>
        </div>
        <div class="stack stack-container" id="results">
            <div class="container">
                <div class="row-fluid">
                    <table class="table table-striped table table-hover table-bordered">
                        <thead>
                            <th>Name</th>
                            <th>Description</th>
                            <th>License Quantity</th>
                            <th>Code</th>
                            <th>Status</th>
                            <th>Product URL</th>
                            <th>Showcase Product</th>
                        </thead>
                        <tbody> 
                            <g:each in="${results}" var ="result">
                            <tr>                     
                                <td><g:link controller="product" action="show"  id="${result.id}">${result.name}</g:link>  </td>
                                <td style="max-width: 300px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis">${result.description}</td>
                                <td>${result.licenseQuantity}</td>
                                <td>${result.code}</td>
                                <td>${result.status}</td>
                                <td><a href="${result.url}">${result.url}</a></td>
                                <td>${result.showcasedProduct}</td>
                            </tr> 
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
