<!DOCTYPE html>
<html lang="${lang}">
    <head>
      <title>${orgName} <g:message code="common.admin"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">  
      <r:layoutResources/> 
     <link href="${resource(dir: 'css/gemini/css/bootstrap/', file: 'bootstrap.css')}" rel="stylesheet" />
    <link href="${resource(dir: 'css/gemini/css/bootstrap/', file: 'bootstrap-overrides.css')}" type="text/css" rel="stylesheet" />
    <link href="${resource(dir: 'css/gemini/css/lib/', file: 'font-awesome.css')}" rel="stylesheet" />

    <!--global styles :: Begins Here-->  
    <link  href="${resource(dir: 'css/gemini/css/compiled/', file: 'layout.css')}" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/gemini/css/compiled/', file: 'elements.css')}" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/gemini/css/compiled/', file: 'icons.css')}"/>
    <link rel="stylesheet"   href="${resource(dir: 'js/dojo-1.8/dojox/form/resources/', file: 'FileInput.css')}" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/gemini/css/compiled/', file: 'index.css')}" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'js/dojo-1.8/dijit/themes/classic', file: 'classic.css')}"/>
  
      <g:if test="${GrailsUtil.isDevelopmentEnv()}" >
          <script>
           var dojoConfig = {
              async: 1,
              ioPublish: true,
              cacheBust: 1,
              parseOnLoad: false,
              tlmSiblingOfDojo: true,
              locale: '${lang}',              
            };
          </script>
          <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js" data-dojo-config="async: true, parseOnLoad:false"></script>
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
            };
          </script>
          <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"></script>
          <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/dojo')}/dojo-x-build.js"></script>
      </g:else>
      <script type="text/javascript" src="${resource(dir: 'js')}/core.js" ></script>
      <script type="text/javascript" src="${resource(dir: 'js')}/jquery-1.9.1.min.js" ></script>
      <script type="text/javascript" src = "${resource(dir: 'js/app')}/password_strength.js">
      </script>  
      <script type="text/javascript"  src = "${resource(dir: 'js')}/app.js"></script>
      <script>      
      core.setConfig({
          baseURL: "${baseUrl}",
          homepage: "${homepage}",
          context: "${contextPath}"
        });     
      </script> 
    </head>
    <body class="classic">  
      <div class="row">
        <div class="loader" id="adminLoader">
          <div class="loader-cont">
          <div> 
            <g:if test="${logoUrl == ''}">                  
                <img height="49" width="133"  src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo"/>                   
            </g:if>
            <g:else>                  
                <img height="49" width="133"  src="${logoUrl}" class="logo fogPanelLogo" />                
            </g:else>
            <img src="${resource(dir: 'images')}/vmload.gif" alt="<g:message code="common.progress"/>" height="9" width="100">
          </div>
          <div>
            <p> <g:message code="common.loadingPanel"/>...</p>
          </div>         
        </div>
        </div>
      </div>
<!--      <div class="account-type-cont">
           <span ><g:meta name="app.version"/></span>
      </div>-->
            
            <header class="navbar navbar-inverse" role="banner">
                <div data-dojo-type="FogPanel.Navigator" id="templateNavigator"> 
                   <div class="navbar-header">
                        <button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#/admin/dashboard">            
                            <g:if test="${logoUrl == ''}">                   
                                <img src="${resource(dir: 'images', file: 'logo.png')}" class="fogPanelLogo" />                    
                            </g:if>
                            <g:else>                 
                                <img src="${logoUrl}" class="fogPanelLogo" />                
                            </g:else>               
                        </a>

                    </div>
                    <ul class="nav navbar-nav pull-right hidden-xs">
                       <li class="hidden-xs hidden-sm">
                            <input class="search" type="text">
                        </li>
                        
                                     
                        <li class="notification-dropdown hidden-xs hidden-sm">
                            <div data-dojo-type="dijit.form.DropDownButton"  title="<g:message code="stat.alerts"/>">
                              <a href="#" class="trigger">
                                <i class="icon-warning-sign"></i>
                                 <span  class="count" id="cloudUsageStatusCount"></span>
                              </a>
                              
                              <div data-dojo-type="dijit.TooltipDialog" class="pop-dialog" onclick="dijit.popup.close(this);">      
                                  <div id="cloudUsageNotificationDiv"></div>
                              </div>
                            </div>
                        </li>
                        <li class="notification-dropdown hidden-xs hidden-sm">
                            <div data-dojo-type="dijit.form.DropDownButton"   title="<g:message code="common.ticketNotification"/>">
                                <a href="#" class="trigger" >
                                  <i class="icon-cog"></i>
                                   <span  class="count" id="ticketCount">0</span>
                                </a>
                                
                                <div data-dojo-type="dijit.TooltipDialog" class="pop-dialog" onclick="dijit.popup.close(this);">
                                  <div id="adminTicketNotification"></div>
                                </div>
                            </div>
                        </li>
                        <li class="settings hidden-phone dashboardContent">
                            <div data-dojo-type="dijit.form.DropDownButton" title="<g:message code="common.settings"/>" >  
                                 <a href="#" class="trigger" >
                                  <i class="icon-warning-sign"></i>
                                   <span class="count" id="ticketCount">0</span>
                                </a>
                                <div data-dojo-type="dijit.TooltipDialog" id="adminSettingsDropdown" class="" style="">            
                                <a href="#/admin/settings/general" class="customWidth" onclick="DashboardWizard.closeDialogue();"><g:message code="menu.admin.configuration"/></a>              
                              </div>
                            </div> 
                        </li>
                        <li class="settings hidden-xs hidden-sm">
                            <a href="personal-info.html" role="button">
                                <i class="icon-cog"></i>
                            </a>
                        </li>
                        <li class="settings hidden-xs hidden-sm">
                            <g:link controller='logout' title="${message(code:'common.logout')}"> <i class="icon-share-alt"></i></g:link>
<!--                            <a href="signin.html" role="button">
                               
                            </a>-->
                        </li>
                    </ul>
                </div>
            </header>     

      <!--Header Section :: Begins-->
<!--      <header class="navbar navbar-inverse">
   
          <g:if test="${isConfigModified == true}"> 
            <div id="configMsgId" style="padding-left : 10px; padding-right : 0px; padding-top : 0px; padding-bottom : 0px; display: block;" class="alert alert-warning">
              <i class="icon-warning-sign"></i>
              <g:message code="config.isModified.message"/>
              <a href="#/admin/settings/configSync">
                  <i class="icon-exchange"></i>
              </a>
            </div>
          </g:if>
          <g:else>
             <div id="configMsgId" style="padding-left : 10px; padding-right : 0px; padding-top : 0px; padding-bottom : 0px; display: none;" class="alert alert-warning">
              <i class="icon-warning-sign"></i>
              <g:message code="config.isModified.message"/>
              <a href="#/admin/settings/configSync">
                  <i class="icon-exchange"></i>
              </a>
            </div>
          </g:else>
        </header>-->
      <!--Header Section :: Ends-->
              
      
      <div data-dojo-type="FogPanel.VerticalMenuBar" id="sidebar-nav">      
          <ul id="dashboard-menu">
              <li>         
                  <a href="#/admin/dashboard" class="mainMenu singleMenu"><i class="icon-home"></i> <span ><g:message code="menu.admin.home"/></span></a>
              </li> 
              <li>
                  <a href="#/admin/account" class="mainMenu singleMenu"> <span ><i class="icon-group"></i><g:message code="menu.admin.clients"/></span></a>
              </li>   
              <li id="cloudMenu">
                <a href="#/admin/infrastructure/cloud" class="mainMenu dropdown-toggle">
                   <span ><i class="icon-cloud"></i><g:message code="menu.admin.cloud"/></span>
                  <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="#/admin/infrastructure"><g:message code="menu.admin.cloud.infrastructure"/></a></li>
                    <li><a href="#/admin/network/list"><g:message code="menu.admin.cloud.networks"/></a></li>
                  <li><a href="#/admin/dashboard/maintainance"><g:message code="menu.admin.cloud.maintenance"/></a></li>           
                </ul>
              </li>
              <li id="serviceMenu">
                  <a class="mainMenu dropdown-toggle" href="#/admin/services">
                      <i class="icon-code-fork"></i>
                       <span ><g:message code="menu.admin.services"/></span>
                      <i class="icon-chevron-down"></i>
                  </a>
                  <ul class="submenu">            
                      <li><a href="#/admin/flavors/list"><g:message code="menu.flavors"/></a></li>
                      <li><a href="#/admin/images/list"><g:message code="menu.images"/></a></li>
                      <li><a href="#/admin/volumeType/list"><g:message code="menu.volumeType"/></a></li>                      
                      <li><a href="#/admin/misc"><g:message code="menu.admin.services.misc"/></a></li>  
                  </ul>
              </li>
              <li>
                  <a class="mainMenu dropdown-toggle" href="#/admin/settings">
                      <i class="icon-cog"></i>
                       <span ><g:message code="menu.admin.configuration"/></span>
                      <i class="icon-chevron-down"></i>
                  </a>
                  <ul class="submenu">            
                      <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
                      <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
                      <li><a href="#/admin/settings/openstack"><g:message code="menu.admin.configuration.openstack"/></a></li>
                      <li><a href="#/admin/settings/zenoss">Zenoss</a></li>
                  </ul>
              </li>
              <li>
                  <a class="mainMenu dropdown-toggle" href="#/admin/billing">
                      <i class="icon-shopping-cart"></i>
                       <span ><g:message code="menu.admin.billing"/></span>
                      <i class="icon-chevron-down"></i>
                  </a>
                  <ul class="submenu">            
                      <li><a href="#/admin/billing/invoice"><g:message code="menu.admin.billing.invoice"/></a></li>
                      <li><a href="#/admin/billing/payment"><g:message code="menu.admin.billing.payments"/></a></li>
                      <li><a href="#/admin/billing/recurringItem"><g:message code="menu.admin.billing.recurringItems"/></a></li>  
                  </ul>
              </li>           
              <li>
                  <a href="#/admin/support/tickets" class="mainMenu singleMenu">
                    <i class="icon-credit-card"></i>
                     <span ><g:message code="menu.admin.tickets"/></span>                
                  </a>              
              </li>
             <!--<li>
                <a class="mainMenu dropdown-toggle" href="#/admin/reports">
                    <i class="icon-th-large"></i>
                     <span ><g:message code="menu.admin.report"/></span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="#/admin/reports/signUp"><g:message code="menu.admin.report.signUpReport"/></a></li>
                    <li><a href="#/admin/reports/billableItem"><g:message code="menu.admin.report.billableItem"/></a></li>
                    <li><a href="#/admin/reports/paymentReport"><g:message code="menu.admin.report.paymentReport"/></a></li>
                    <li><a href="#/admin/reports/creditLimitReport"><g:message code="menu.admin.report.creditLimitReport"/></a></li>
                    <li><a href="#/admin/reports/pendingPaymentReport"><g:message code="menu.admin.report.paymentPending"/></a></li>
                </ul>
            </li>  -->  
      </ul>     
    </div>
    <div class="content" id="contentArea">
                  <!--<div class="skins-nav">
                    <a href="#" class="skin first_nav selected">
                         <span  class="icon"></span> <span  class="text">Default</span>
                    </a>
                    <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                         <span  class="icon"></span> <span  class="text">Dark skin</span>
                    </a>
              </div>-->

          <div id="content" class="container-fluid"></div>
    </div>
    <div id="templateArea"></div>
    <div id="screen-loader"></div>
    <div data-dojo-type="dojox.widget.Toaster" data-dojo-props="positionDirection:'tr-left', duration:4000" id="appToaster"></div>
    <script type="text/javascript" src="${resource(dir: 'js')}/theme.js" ></script>
    </body>
</html>
