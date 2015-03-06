<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="applications.newApp.title" /></title>
        <g:set var="entityName" value="${message(code: 'applications.page.title', default: 'My Applications')}" />
        <script type="text/javascript" src="${resource(dir: 'js/')}/application.js"></script>   
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <div class = "row">
                <h1 id="page-title" style="margin-left:  16px;">Downloads</h1> 
            </div>
            </div>
        </div>

         <div class="container">
            <div class = "row">
                <p style="padding-left: 18px;">You can install the latest version automatically by executing the following command.</p>
                <div class="panel-heading">
                    <g:if test="Ok">
                        <div class="alert alert-info">
                            bash <(curl -s http://downloads.fogpanel.com/fogpanel.sh)
                        </div>
                    </g:if>
                </div>
            </div>
        </div>     
        
        <g:if test='${flash.message}'>
            <div class='alert alert-danger'>${flash.message}</div>
        </g:if>

        <div class="stack stack-container">
            <div class="container">
                <div class="panel-group" id="accordion">
                    <g:each in="${releaseList}">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <span>${it.product.name}</span> <span>${it.productVersion}&nbsp;</span> - <span>&nbsp;Download &nbsp;</span>
                                    <g:if test="${it.vhdPath}">
                                        <g:link controller="download" action="vhd" id="${it.id}"  >VHD</g:link>
                                    </g:if>&nbsp;&nbsp;
                                    <g:if test="${it.bundlePath}">
                                        <g:link controller="download" action="bundle" id="${it.id}" >WAR</g:link>
                                    </g:if>
                                    <a data-toggle="collapse" data-parent="#accordion" class="pull-right" href="#collapse-${it.id}">
                                        More
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-${it.id}" class="panel-collapse collapse">
                                <div class="panel-body">
                                    ${it.releaseNotes.replaceAll("\n", "<br>")}
                                </div>
                            </div>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </body>
</html>
