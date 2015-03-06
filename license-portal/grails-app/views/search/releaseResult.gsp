<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="admin">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Release List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="applications.page.resultforRelease" /></h1>
            </div>
        </div>
        <div class="stack stack-container" id="results">
            <div class="container">
                <div class="row-fluid">
                    <table class="table table-striped table table-hover table-bordered">
                        <thead>
                            <th>Product</th>
                            <th>Product Version</th>
                            <th>VHD Path</th>
                            <th>Bundle Path</th>
                            <th>Release Notes</th>
                        </thead>
                        <tbody> 
                            <g:each in="${results}" var ="result">
                            <tr> 
                                <td><g:link controller="release" action="show" id="${result.id}">${result.product.name}</g:link></td>
                                <td>${result.productVersion}</td>
                                <td>${result.vhdPath}</td>
                                <td>${result.bundlePath}</td>
                                <td>${result.releaseNotes}</td>
                            </tr> 
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

