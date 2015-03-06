<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unsubscribe</title>
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen"> 
    </head>
    <body>
        <div class="navbar navbar-inverse">
          <div class="navbar-inner">
            <g:if test="${logoUrl == ''}">
                    <a href="#">
                        <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo" style="height: 35px !important;"/>
                    </a> 
                </g:if>
                <g:else>
                    <a href="#">
                        <img src="${logoUrl}" class="logo fogPanelLogo" style="height: 35px !important;" />
                    </a> 
                </g:else>
          </div>
        </div>
    <div class="hero-unit">
    <g:if test="${flash.message}">   
      <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">${flash.message}</h1>
      <p>
        <!--<h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px"><g:message code="common.login"/></h2>-->
        <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
            
            <g:link controller="login" class="btn btn-primary"><g:message code="common.login"/></g:link>
        </p>
      </p>
    </g:if>
      
<!--      <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Next Step</h2>
      <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
        Now that you have verified your email address
      </p>-->
    </div>
    </body>
</html>
