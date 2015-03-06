<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name='layout' content='admin'/>
        <title><g:message code="dashboard.page.title"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>        
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="dashboard.page.title"/></h1>
            </div>
        </div>
        <div class="stack stack-container">
            <div class="container">
                <div class="row">
                    <div class="col col-md-6">
                        <p>Welcome Admin!</p>
                    </div>
                    <div class="col col-md-6">
                        <ul>
                            <li><g:link controller="user" action="list" >Accounts</g:link></li>
                            <li><g:link controller="product" action="index" >Products</g:link></li>
                            <li><g:link controller="release" action="index" >Releases</g:link></li>
                            <li><g:link controller="ProductInstance" action="index" >Application Instances</g:link></li>
                        </ul>
                    </div>
                </div>
                
            </div>
        </div>
    </body>
</html>
