<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'common.css')}"/>
        <title> <g:message code="app_title"/></title>

    </head>
    <body>
            <!--<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>-->
<!--        <div id="status" role="complementary">
            <h1>Home</h1>

        </div>-->
        <div id="pad-wrapper" class="users-list">
            <div class="row header">
                <g:if test="${flash.message}">
                    <div class="alert alert-success" role="status">
                        <i class="icon-remove-sign"></i>${flash.message}
                    </div>
                </g:if>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <h3>Welcome to Fogpanel Mail Manager App !!!!!</h3>
            </div>
            <div class="row">    
                <p >
                    This app manages the mail sending via Rabit MQ. It shows the reports of sent mails.

                </p>
            </div>    
        </div> 
    </body>
</html>
