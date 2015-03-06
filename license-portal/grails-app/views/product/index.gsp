<%@ page import="com.assistanz.fogpanel.licensemanager.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin">
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="stack stack-container">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-xs-12">
                        <div class="col-md-6 col-xs-6">     
                            <h1>Products</h1>   
                        </div>
                        <div class="col-md-6 col-xs-6" style="padding-top: 20px; padding-left: 50px;">
                            <g:link class="btn btn-primary btn-sm pull-right" action="create">New Product</g:link>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
        
        <div class="stack stack-container">
            <div class="container">
                <div class="row-fluid">
                    <div id="list-product" class="content scaffold-list" role="main">

                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <table>
                            <thead>
                                <tr>

                                    <g:sortableColumn property="name" title="${message(code: 'product.name.label', default: 'Name')}" />

                                    <g:sortableColumn property="description" title="${message(code: 'product.description.label', default: 'Description')}" />

                                    <g:sortableColumn property="licenseQuantity" title="${message(code: 'product.licenseQuantity.label', default: 'License Quantity')}" />
                                    
                                    <g:sortableColumn property="code" title="${message(code: 'product.code.label', default: 'Code')}" />
                                    
                                    <g:sortableColumn property="status" title="${message(code: 'product.status.label', default: 'Status')}" />
                                    
                                    <g:sortableColumn property="url" title="${message(code: 'product.url.label', default: 'Product URL')}" />
                                    
                                    <g:sortableColumn property="showcasedProduct" title="${message(code: 'product.showcaseProduct.label', default: 'Showcase Product')}" />

                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${productInstanceList}" status="i" var="productInstance">
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                        <td><g:link action="show" id="${productInstance.id}">${fieldValue(bean: productInstance, field: "name")}</g:link></td>

                                        <td>${fieldValue(bean: productInstance, field: "description")}</td>

                                        <td>${fieldValue(bean: productInstance, field: "licenseQuantity")}</td>
                                        
                                        <td>${fieldValue(bean: productInstance, field: "code")}</td>

                                        <td>${fieldValue(bean: productInstance, field: "status")}</td> 
                                        
                                        <td><a href="${productInstance.url}">${productInstance.url}</a></td> 
                                                                                
                                        <td><g:if test="${productInstance?.showcasedProduct == true }">
                                        <span class="fa fa-check-square-o" style="color: green"></span>
                                        </g:if>
                                        <g:if test="${productInstance?.showcasedProduct == false }">
                                            <span class="fa fa-times" style="color: red"></span>
                                        </g:if>
                                        
                                        </td>

                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                        <div class="pagination">
                            <g:paginate total="${productInstanceCount ?: 0}" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
