
<%@ page import="com.assistanz.fogpanel.licensemanager.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin">
        <title>Product</title>
    </head>
    <body>
        <div class="stack stack-container">
            <div class="container">
                <div class="row-fluid">
                    <div id="show-product" class="content scaffold-show" role="main">
                        <h1>
                            Product: ${productInstance?.name}

                            <g:form url="[resource:productInstance, action:'delete']" method="DELETE" class="pull-right">
                                <fieldset class="buttons">
                                    <g:link class="btn btn-primary" action="edit" resource="${productInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                    <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                </fieldset>
                            </g:form>
                        </h1>
                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <ul class="list-unstyled">

                            <g:if test="${productInstance?.name}">
                                <li class="fieldcontain">
                                    <label id="name-label" class="property-label"><g:message code="product.name.label" default="Name" /></label>

                                    <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${productInstance}" field="name"/></span>

                                </li>
                            </g:if>

                            <g:if test="${productInstance?.description}">
                                <li class="fieldcontain">
                                    <label id="description-label" class="property-label"><g:message code="product.description.label" default="Description" /></label>

                                    <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${productInstance}" field="description"/></span>

                                </li>
                            </g:if>
                            
                            <g:if test="${productInstance?.licenseQuantity}">
                                <li class="fieldcontain">
                                    <label id="licenseQuantity-label" class="property-label"><g:message code="product.licenseQuantity.label" default="License Quantity" /></label>

                                    <span class="property-value" aria-labelledby="licenseQuantity-label"><g:fieldValue bean="${productInstance}" field="licenseQuantity"/></span>

                                </li>
                            </g:if>

                            <g:if test="${productInstance?.price}">
                                <li class="fieldcontain">
                                    <label id="price-label" class="property-label"><g:message code="product.price.label" default="Price" /></label>

                                    <span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${productInstance}" field="price"/></span>

                                </li>
                            </g:if>

                            <g:if test="${productInstance?.status}">
                                <li class="fieldcontain">
                                    <label id="status-label" class="property-label"><g:message code="product.status.label" default="Status" /></label>

                                    <span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${productInstance}" field="status"/></span>

                                </li>
                            </g:if>
                            
                            <g:if test="${productInstance?.url}">
                                <li class="fieldcontain">
                                    <label id="status-label" class="property-label"><g:message code="product.url.label" default="Product URL" /></label>

                                    <span class="property-value" aria-labelledby="url-label"><a href="${productInstance.url}">${productInstance.url}</a></span>

                                </li>
                            </g:if>               
                            
                            <g:if test="${productInstance?.showcasedProduct}">
                                <li class="fieldcontain">

                                    <label id="showcase-label" class="property-label"><g:message code="product.showcase.label" default="Showcase Product " /></label>

                                    <span class="property-value" aria-labelledby="showcase-label"><g:fieldValue bean="${productInstance}" field="showcasedProduct"/></span>

                                </li>
                            </g:if>
                            <g:else>
                                <li class="fieldcontain">

                                    <label id="showcase-label" class="property-label"><g:message code="product.showcase.label" default="Showcase Product " /></label>

                                    <span class="property-value" aria-labelledby="showcase-label"><g:fieldValue bean="${productInstance}" field="showcasedProduct"/></span>

                                </li>
                            </g:else>
                        </ul>
                    </div>
                </div>
            </div>
    </body>
</html>
