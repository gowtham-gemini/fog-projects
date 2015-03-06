<!DOCTYPE html>
<html lang="${lang}">
  <head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${orgName} <g:message code="common.signupSuccess"/></title>   
    <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  media="screen" type="text/css" />    
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen">        
  </head>
  <body class="fog-classic">
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
<!--<div class="alert alert-success">
   Registered Successfully!
</div>-->
  <div data-dojo-type="dojox.widget.Toaster" 
       data-dojo-props="positionDirection:'tr-left'" 
       id="successMessage">
  </div>

    <div class="hero-unit">
      <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><g:message code="common.welcomeTo" /> ${orgName}</h1>
      <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px"><g:message code="common.account.successPage.verifyEmail" /></h2>
      <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
        <g:message code="common.account.successPage.oneMoreStep" /> ${orgName}. <g:message code="common.account.successPage.emailVerification" />
      </p>
</body>
</html>
