<!DOCTYPE html>
<html lang="${lang}">
    <head>
      <title>${orgName} <g:message code="common.admin"/></title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">  
      <r:layoutResources/> 
  
   
      <!--Styles By pradeep :: Begins Here-->
      <!--<link rel="stylesheet" href="${resource(dir: 'css/gemini/css/lib', file: 'font-awesome.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/bootstrap', file: 'bootstrap-responsive.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/bootstrap', file: 'bootstrap-overrides.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled', file: 'index.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled', file: 'layout.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled', file: 'elements.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled', file: 'icons.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled', file: 'web-page-content.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro', file: 'claro.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/classic', file: 'classic.css')}"  media="screen" type="text/css" />
      <link rel="stylesheet " href="${resource(dir: 'css/gemini/css/bootstrap', file: 'bootstrap.css')}"  media="screen" type="text/css" />-->
      
      <!--Styles By Pradeep ::  Begins here-->
      <link href="Boot3/css/bootstrap/bootstrap.css" rel="stylesheet" />
      <link href="Boot3/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
      <!-- libraries -->
      <link href="Boot3/css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
      <link href="Boot3/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
      <!-- global styles -->
      <link rel="stylesheet" type="text/css" href="Boot3/css/compiled/layout.css" />
      <a href="form-wizard.html"></a>
      <link rel="stylesheet" type="text/css" href="Boot3/css/compiled/elements.css" />
      <link rel="stylesheet" type="text/css" href="Boot3/css/compiled/icons.css" />
      <a href="index.html"></a>

    <!-- this page specific styles -->
      <link rel="stylesheet" href="Boot3/css/compiled/index.css" type="text/css" media="screen" />
      <!--Styles By pradeep :: Ends Here-->
  
      
      <link rel="stylesheet"   href="${resource(dir: 'js/dojo-1.8/dojox/form/resources/', file: 'FileInput.css')}" type="text/css"/>
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
          <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js"></script>
          <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
      </g:else>
      <script type="text/javascript" src="${resource(dir: 'js')}/core.js" ></script>
      <script type="text/javascript" src="${resource(dir: 'Boot3/js')}/jquery-1.9.1.min.js" ></script>
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
        <div class="loader" id="adminLoader">
            <div class="loader-cont">
                <div> 
                    <g:if test="${logoUrl == ''}">                  
                        <img height="49" width="133"  src="${resource(dir: 'images', file: 'geminiLogo.png')}" class="logo fogPanelLogo"/>                   
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

<!-- navbar -->
                    
                     <!-- navbar -->
    <header class="navbar navbar-inverse" role="banner">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">
                <img src="../../../web-app/Boot3/img/logo.png" alt=" logo"/>
            </a>
        </div>
        <ul class="nav navbar-nav pull-right hidden-xs">
            <li class="hidden-xs hidden-sm">
                <input class="search" type="text">
            </li>
            <li class="notification-dropdown hidden-xs hidden-sm">
                <a href="#" class="trigger">
                    <i class="icon-warning-sign"></i>
                    <span class="count">8</span>
                </a>
                <div class="pop-dialog">
                    <div class="pointer right">
                        <div class="arrow"></div>
                        <div class="arrow_border"></div>
                    </div>
                    <div class="body">
                        <a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
                        <div class="notifications">
                            <h3>You have 6 new notifications</h3>
                            <a href="#" class="item">
                                <i class="icon-signin"></i> New user registration
                                <span class="time"><i class="icon-time"></i> 13 min.</span>
                            </a>
                            <a href="#" class="item">
                                <i class="icon-signin"></i> New user registration
                                <span class="time"><i class="icon-time"></i> 18 min.</span>
                            </a>
                            <a href="#" class="item">
                                <i class="icon-envelope-alt"></i> New message from Alejandra
                                <span class="time"><i class="icon-time"></i> 28 min.</span>
                            </a>
                            <a href="#" class="item">
                                <i class="icon-signin"></i> New user registration
                                <span class="time"><i class="icon-time"></i> 49 min.</span>
                            </a>
                            <a href="#" class="item">
                                <i class="icon-download-alt"></i> New order placed
                                <span class="time"><i class="icon-time"></i> 1 day.</span>
                            </a>
                            <div class="footer">
                                <a href="#" class="logout">View all notifications</a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="notification-dropdown hidden-xs hidden-sm">
                <a href="#" class="trigger">
                    <i class="icon-envelope"></i>
                </a>
                <div class="pop-dialog">
                    <div class="pointer right">
                        <div class="arrow"></div>
                        <div class="arrow_border"></div>
                    </div>
                    <div class="body">
                        <a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
                        <div class="messages">
                            <a href="#" class="item">
                                <img src="img/contact-img.png" class="display" alt="user">
                                <div class="name">Alejandra Galván</div>
                                <div class="msg">
                                    There are many variations of available, but the majority have suffered alterations.
                                </div>
                                <span class="time"><i class="icon-time"></i> 13 min.</span>
                            </a>
                            <a href="#" class="item">
                                <img src="img/contact-img2.png" class="display" alt="user">
                                <div class="name">Alejandra Galván</div>
                                <div class="msg">
                                    There are many variations of available, have suffered alterations.
                                </div>
                                <span class="time"><i class="icon-time"></i> 26 min.</span>
                            </a>
                            <a href="#" class="item last">
                                <img src="img/contact-img.png" class="display" alt="user">
                                <div class="name">Alejandra Galván</div>
                                <div class="msg">
                                    There are many variations of available, but the majority have suffered alterations.
                                </div>
                                <span class="time"><i class="icon-time"></i> 48 min.</span>
                            </a>
                            <div class="footer">
                                <a href="#" class="logout">View all messages</a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle hidden-xs hidden-sm" data-toggle="dropdown">
                    Your account
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="personal-info.html">Personal info</a></li>
                    <li><a href="#">Account settings</a></li>
                    <li><a href="#">Billing</a></li>
                    <li><a href="#">Export your data</a></li>
                    <li><a href="#">Send feedback</a></li>
                </ul>
            </li>
            <li class="settings hidden-xs hidden-sm">
                <a href="personal-info.html" role="button">
                    <i class="icon-cog"></i>
                </a>
            </li>
            <li class="settings hidden-xs hidden-sm">
                <a href="signin.html" role="button">
                    <i class="icon-share-alt"></i>
                </a>
            </li>
        </ul>
    </header>
    <!-- end navbar -->

    <!-- sidebar -->
    <div data-dojo-type="FogPanel.VerticalMenuBar" id="verticalMenu">      
          <ul class="dashboard-menu">
              <li>         
                  <a href="#/admin/dashboard" class="mainMenu singleMenu"><i class="icon-home"></i><span><g:message code="menu.admin.home"/></span></a>
              </li> 
              <li>
                  <a href="#/admin/account" class="mainMenu singleMenu"><span><i class="icon-group"></i><g:message code="menu.admin.clients"/></span></a>
              </li>   
              <li id="cloudMenu">
                <a href="#/admin/infrastructure/cloud" class="mainMenu dropdown-toggle">
                  <span><i class="icon-cloud"></i><g:message code="menu.admin.cloud"/></span>
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
                      <i class="iconfgs-menu_service_icon"></i>
                      <span><g:message code="menu.admin.services"/></span>
                      <i class="icon-exchange"></i>
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
                      <span><g:message code="menu.admin.configuration"/></span>
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
                      <span><g:message code="menu.admin.billing"/></span>
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
                    <span><g:message code="menu.admin.tickets"/></span>                
                  </a>              
              </li>
             <!--<li>
                <a class="mainMenu dropdown-toggle" href="#/admin/reports">
                    <i class="icon-th-large"></i>
                    <span><g:message code="menu.admin.report"/></span>
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
    <!-- end sidebar -->
  
        <div class="content" id="contentArea">
                      <!--<div class="skins-nav">
                        <a href="#" class="skin first_nav selected">
                            <span class="icon"></span><span class="text">Default</span>
                        </a>
                        <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                            <span class="icon"></span><span class="text">Dark skin</span>
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
