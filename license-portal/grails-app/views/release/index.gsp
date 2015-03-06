
<%@ page import="com.assistanz.fogpanel.licensemanager.Release" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin">
        <title>Releases</title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                
                <div class="row">
                    <div class="col-md-6"> 
                        <h1 id="page-title">Releases</h1>
                    </div>
                    <div class="col-md-6"> 
                        <g:link class="btn btn-primary btn-sm pull-right" action="create">New Release</g:link>
                    </div>    
                </div>

        <div class="stack stack-container">
            <div class="container">
                <div class="row">
                    <div id="list-release" class="content scaffold-list" role="main">
                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <table>
                            <thead>
                                <tr>

                                    <th><g:message code="release.product.label" default="Product" /></th>

                                        <g:sortableColumn property="productVersion" title="${message(code: 'release.productVersion.label', default: 'Product Version')}" />

                                        <g:sortableColumn property="vhdPath" title="${message(code: 'release.vhdPath.label', default: 'Vhd Path')}" />

                                        <g:sortableColumn property="bundlePath" title="${message(code: 'release.bundlePath.label', default: 'Bundle Path')}" />

                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${releaseInstanceList}" status="i" var="releaseInstance">
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                        <td><g:link action="show" id="${releaseInstance.id}">${fieldValue(bean: releaseInstance, field: "product")}</g:link></td>

                                        <td>${fieldValue(bean: releaseInstance, field: "productVersion")}</td>

                                        <td>${fieldValue(bean: releaseInstance, field: "vhdPath")}</td>

                                        <td>${fieldValue(bean: releaseInstance, field: "bundlePath")}</td>

                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                        <div class="pagination">
                            <g:paginate total="${releaseInstanceCount ?: 0}" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
