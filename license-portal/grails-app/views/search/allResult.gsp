<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="admin">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Results</title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <div class="row" style="padding-left: 10px;">
                    <div class="col-md-12">
                        <h1 id="page-title"><g:message code="applications.page.resultforAll" /></h1>
                    </div>    
                </div>
            </div>
        </div>
        <div class="stack stack-container"style="padding-right: 20px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-3">
                            <ul class="nav nav-pills">
                                <li class="active">
                                    <g:link class="btn btn-primary btn-sm" controller="search"  action="results" params='[menuName: "Product", name: "${value}"]'>Product <span class="badge">${counts.productList}</span></g:link>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-3" style="padding-left: 60px;">
                            <ul class="nav nav-pills">
                                <li class="active">
                                    <g:link class="btn btn-primary btn-sm" controller="search" action="results" params='[menuName: "User", name: "${value}"]'>User<span class="badge">${counts.userList}</span></g:link>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-3"style="padding-left: 110px;">
                            <ul class="nav nav-pills">
                                <li class="active">
                                    <g:link class="btn btn-primary btn-sm" controller="search" action="results" params='[menuName: "Release", name: "${value}"]'>Release<span class="badge">${counts.releaseList}</span></g:link>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-3"style="padding-left: 140px;">
                            <ul class="nav nav-pills">
                                <li class="active">
                                    <g:link class="btn btn-primary btn-sm" controller="search" action="results" params='[menuName: "Product Instance", name: "${value}"]'>Product Instance<span class="badge">${counts.productInstanceList}</span></g:link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
