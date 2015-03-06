
<%@ page import="com.assistanz.fogpanel.licensemanager.Release" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin">
        <g:set var="entityName" value="${message(code: 'release.label', default: 'Release')}" />
        <title>Release</title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title">
                    Release - ${releaseInstance?.product?.name?.encodeAsHTML()} - ${releaseInstance?.productVersion}
                    <g:form url="[resource:releaseInstance, action:'delete']" method="DELETE" class="pull-right">
                                <g:link class="btn btn-primary" action="edit" resource="${releaseInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                <g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </g:form>
                
                </h1>
            </div>
        </div>

        <div class="stack stack-container">
            <div class="container">
                <div class="row-fluid">
                    <div id="show-release" class="content scaffold-show" role="main">

                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <ul class="list-unstyled">

                            <g:if test="${releaseInstance?.product}">
                                <li class="fieldcontain">
                                    <label id="product-label" class="property-label"><g:message code="release.product.label" default="Product" /></label>

                                    <span class="property-value" aria-labelledby="product-label"><g:link controller="product" action="show" id="${releaseInstance?.product?.id}">${releaseInstance?.product?.name?.encodeAsHTML()}</g:link></span>

                                </li>
                            </g:if>

                            <g:if test="${releaseInstance?.productVersion}">
                                <li class="fieldcontain">
                                    <label id="productVersion-label" class="property-label"><g:message code="release.productVersion.label" default="Product Version" /></label>

                                    <span class="property-value" aria-labelledby="productVersion-label"><g:fieldValue bean="${releaseInstance}" field="productVersion"/></span>

                                </li>
                            </g:if>

                            <g:if test="${releaseInstance?.vhdPath}">
                                <li class="fieldcontain">
                                    <label id="vhdPath-label" class="property-label"><g:message code="release.vhdPath.label" default="Vhd Path" /></label>

                                    <span class="property-value" aria-labelledby="vhdPath-label"><g:fieldValue bean="${releaseInstance}" field="vhdPath"/></span>

                                </li>
                            </g:if>

                            <g:if test="${releaseInstance?.bundlePath}">
                                <li class="fieldcontain">
                                    <label id="bundlePath-label" class="property-label"><g:message code="release.bundlePath.label" default="Bundle Path" /></label>

                                    <span class="property-value" aria-labelledby="bundlePath-label"><g:fieldValue bean="${releaseInstance}" field="bundlePath"/></span>

                                </li>
                            </g:if>

                            <g:if test="${releaseInstance?.releaseNotes}">
                                <li class="fieldcontain">
                                    <label id="releaseNotes-label" class="property-label"><g:message code="release.releaseNotes.label" default="Release Notes" /></label>
                                    <br>
                                    <span class="property-value" aria-labelledby="releaseNotes-label">${releaseInstance.releaseNotes.replaceAll("\n", "<br>")}</span>

                                </li>
                            </g:if>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
