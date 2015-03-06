<!DOCTYPE html>
<html lang="${lang}">
  <head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${orgName} <g:message code="common.confirm"/></title>
   <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster/', file: 'Toaster.css')}" >    
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen"> 
  </head>
  <body class="claro">    
  <div class="navbar navbar-inverse">
      <div class="navbar-inner">
        <g:if test="${logoUrl == ''}">
                <a href="#">
                    <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo" />
                </a> 
            </g:if>
            <g:else>
                <a href="#">
                    <img src="${logoUrl}" class="logo fogPanelLogo" />
                </a> 
            </g:else>   
      </div>
  </div>
    
<!--    <div class="alert alert-success">
      Your Email Has Verified successfully!
    </div>-->
    <div class="hero-unit">
      <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><g:message code="common.welcomeTo" /> ${orgName}</h1>
      <p>
          <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Let&#39;s Get Started</h2>
          <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
            <g:message code="common.account.confirm.thankYou"/> ${orgName}.<g:message code="common.account.confirm.fewSteps"/>  
            <g:message code="common.account.confirm.utilizeResource"/>
          </p>
          <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Next Steps</h2>
          <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
            <g:message code="common.account.confirm.verifidSuccess"/>:
          </p>
          <ul style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:0 0 15px 24px;padding:0">
            <li> <g:message code="common.account.confirm.manageCloudResource"/>:
              <ul style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:0 0 15px 24px;padding:0">
                <li><g:message code="common.account.confirm.startNewVM" /></li>
                <li><g:message code="common.account.confirm.addBlocksOfDisk" /></li>
                <li><g:message code="common.account.confirm.networkSettingManagement" /></li>
              </ul>
            </li>
            <li><g:message code="common.account.confirm.addNewUser" /></li>
            <li><g:message code="common.account.confirm.dailyBasis" />:
              <ul style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:0 0 15px 24px;padding:0">
                <li><g:message code="common.account.confirm.combinedTally" /></li>
                <li><g:message code="common.account.confirm.breakDown" /></li>
              </ul>
            </li>
            <li><g:message code="common.account.confirm.manageSpendBudget" /></li>
            <li><g:message code="common.account.confirm.visitBillingSection" /></li>
            <li><g:message code="common.account.confirm.accessRefeMaterial" /></li>
          </ul>
      </p>
      <p>
        <g:link controller="login" class="btn btn-primary"><g:message code="common.gotoLogin" /></g:link>
      </p>
    </div>
    <!--<button type="button" onclick="test()"data-dojo-type="dijit.form.Button">Hello</button>-->
  </body>
</html>