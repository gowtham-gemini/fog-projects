<!DOCTYPE html>
<html lang="${lang}">
  <head>
    <meta charset="utf-8">
    <title>${orgName}</title>
    <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}" media="screen" type="text/css" />    
    <link rel="stylesheet" href = "${resource(dir: 'js/dojo-1.8/dojox/widget/Wizard/', file: 'Wizard.css')}"  media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen">        
    <link href="${resource(dir: 'css/', file: 'layout.css')}" rel="stylesheet" type="text/css" />
    <link href="${resource(dir: 'css/', file: 'icons.css')}" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${resource(dir: 'css/compiled/', file: 'new-user.css')}" media="screen">   
    <link rel="stylesheet" href="${resource(dir: 'css/', file: 'tables.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/compiled/', file: 'index.css')}" media="screen">                
    <script type="text/javascript" src="${resource(dir: 'js')}/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="${resource(dir: 'js')}/bootstrap.min.js" ></script>
    <link rel="stylesheet" type="text/css" href = "${resource(dir:'js/dojo-1.8/dojox/grid/resources/', 
      file: 'claroGrid.css')}"/>
      <link type="text/css" rel="stylesheet"
            href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster',file: 'Toaster.css')}"/>
     <g:if test="${GrailsUtil.isDevelopmentEnv()}" >
        <script>
         var dojoConfig = {
            async: 1,
            ioPublish: true,
            cacheBust: 1,
            parseOnLoad: true,
            tlmSiblingOfDojo: true,
            locale: '${lang}',
            packages: [
              { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
              { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
            ]
          };
        </script>
        <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js" data-dojo-config="async: true, parseOnLoad:true"></script>
    </g:if >
    <g:else >
         <script>
         var dojoConfig = {
            async: 1,
            ioPublish: true,
            cacheBust: 0,
            parseOnLoad: false,
            tlmSiblingOfDojo: true,
            locale: '${lang}',
            packages: [
              { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
              { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
            ]
          };
        </script>
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
        <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
    </g:else>
      <script type="text/javascript" src = "${resource(dir: 'js/app')}/paymentStatus.js"></script>
      
    <script type="text/javascript">      
          payment.setConfig({
             baseURL: "${baseUrl}",
             homepage: "${homepage}",
             context: "${contextPath}"
           });        
    </script> 
    <style>          
        <g:if test="${paymentStatus == 'FAILED'}">            
            .successDivClass {   
                display : none;
            }
            .failureDivClass {   
                display : block;
            }
        </g:if>
        <g:else>
            .successDivClass {   
                display : block;
            }
            .failureDivClass {   
                display : none;
            }
        </g:else>            
    </style>
  </head>
  <body class="fog-classic">
    <div id="screen-loader"></div> 
    <div class="loader" id="loader">
      <div class="loader-cont">
        <div>
          <g:if test="${logoUrl == ''}">                 
              <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo"/>                 
          </g:if>
          <g:else>                 
              <img src="${logoUrl}" class="logo fogPanelLogo" />               
          </g:else>
        </div>
          <div><p class="span2" style="text-align: center"><g:message code="common.message.loading" /></p></div>         
      </div>
    </div>
    <div class="row-fluid">
        <div class="navbar navbar-inverse">
          <div data-dojo-type="FogPanel.Navigator" id="userTemplateNavigator">

             
              
            <a class="brand" href="${baseUrl}">
                <g:if test="${logoUrl == ''}">                 
                  <img height="49" width="133" src="${resource(dir: 'images', file: 'fog_logo.png')}" class="fogPanelLogo"/>                 
                </g:if>
                <g:else>                 
                    <img height="49" width="133" src="${logoUrl}" class="fogPanelLogo">             
                </g:else>
            </a>
            <div data-dojo-type="dijit.Toolbar" class="nav pull-right"> 
            <span>
              <sec:ifLoggedIn>
                <span style="color: #fff"><g:message code="common.loggedIn" /> <sec:username/></span>
              </sec:ifLoggedIn>
              <sec:ifSwitched>            - 
                <a href='${request.contextPath}/j_spring_security_exit_user'>
                  <g:message code="common.resumeAs" />  <sec:switchedUserOriginalUsername/>
                </a>
              </sec:ifSwitched>
            </span>
              <g:link controller='logout' class="icon-share-alt" ></g:link>
                </div>
            </div>
          </div>    
    </div>  
<div class="content-payment-panel">

 <div id="successPageCCAvenue" class="successDivClass">
      <div class="alert alert-info">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.billing.paymentSuccess"/>
      </div>
      <div id="manualPaymentDiv" class="row-fluid form-wrapper">
        <div class="new-user"> 
          <div class="row-fluid form-wrapper">   
              <div class="span6"> 
                <form data-dojo-type="dijit.form.Form" class="form-horizontal" id="paymentStatusForm">
                        <div class="row-fluid" id="paymentStatusFormPage">  
                            <div class="span12 field-box control-group"> 
                            <label for="transactionId" class="control-label settings_lable"> <g:message code="common.payment.transactionId"/></label>
                            <div class="controls">
                               <span id="transactionId">${paymentToken}</span>                                 
                            </div>
                            </div> 
                            <div class="span12 field-box control-group"> 
                            <label for="amount" class="control-label settings_lable"> <g:message code="common.amount"/></label>
                            <div class="controls">
                               <span id="amount">${amount}</span>                                 
                            </div>
                            </div>
                        </div>
                </form>
              </div>
          </div>
        </div>
  </div>
  </div>
  <div id="FailurePageCCAvenue" class="failureDivClass">
      <div class="alert alert-danger">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.billing.paymentFailure" />
      </div>      
  </div>
  <div class="pull-right">
    <a href="${baseUrl}/#/user/billing/payment"><g:message code="common.backToPayment"/></a>  <span> | </span>

    <a href="${baseUrl}"><g:message code="common.goToHome"/></a>  
  </div>

</body>
<script type="text/javascript">
  payment.require();
</script>
</html>
