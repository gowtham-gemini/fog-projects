<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:message code="common.invitation.noUserTitle" /></title>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}" media="screen">
    
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen"> 
    
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'bootstrap.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'bootstrap-responsive.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/', file: 'bootstrap.min.css')}"  media="screen">         
  </head>
  <body class="fog-classic" style="background: #EEEEEE">
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
      <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><g:message code="common.invitation.noUserTitle" /></h1>
      <p>
        <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px"><g:message code="common.error" /></h2>
      <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
        <g:message code="common.invitation.noUser" />.        
      </p>
      </p>      
    </div>   
</body>
</html>
