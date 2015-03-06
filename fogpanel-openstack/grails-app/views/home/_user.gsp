<!DOCTYPE html>
<html lang="${lang}">
    <head>   
      <title>${orgName} <g:message code="common.client"/></title>     
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">   
      <r:layoutResources/>    
      <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}" media="screen" type="text/css" />

      <script>
        function logout() {
          var singleSignOnUrl = core.getSingleSignOnURL()+"/api";
          dojo.xhrPost({
                // The URL of the request
                url: singleSignOnUrl,
                'content': {
                    command:"logout"
                }
            });
        }
      </script>
      <g:if test="${GrailsUtil.isDevelopmentEnv()}">
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
              locale: "${lang}", 
            };
          </script>
          <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js"></script>
          <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
      </g:else>
      <script type="text/javascript" src="${resource(dir: 'js')}/core.js" ></script>
      <script type="text/javascript" src="${resource(dir: 'js')}/jquery-1.9.1.min.js" ></script>  
      <script type="text/javascript">      
            core.setConfig({
               baseURL: "${baseUrl}",
               homepage: "#/user/home",   
               context: "${contextPath}"
             });        
        core.setSingleSignOnUrl("${singleSignOnUrl}");
      </script> 
    </head>
    <body class="fog-classic">
      <input type="hidden" id="currentInstanceReferenceId">
      <div data-dojo-type="dojox.widget.Toaster"
           data-dojo-props="positionDirection:'tr-left', duration:8000" id="userToaster">
      </div>   
      <div id="screen-loader"></div> 
      <div class="loader" id="userLoader">
        <div class="loader-cont">
            <div>
                <g:if test="${logoUrl == ''}">                 
                    <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo"/>                 
                </g:if>
                <g:else>                 
                    <img src="${logoUrl}" class="logo fogPanelLogo" />               
                </g:else>
                <img src="${resource(dir: 'images')}/vmload.gif" alt="<g:message code="common.ticket.status.inProgress"/>" height="9" width="100">
            </div>
            <div>                
                <p style="text-align: center"><g:message code="common.loadingPortal"/></p>
            </div>         
        </div>
      </div>  
      <g:if test="${accountType.equals("TRIAL")}">
          <div class="account-type-cont">
              <div style="display: none;" id="creditLimitDiv" ><g:message code="user.billing.creditLimit"/>:  <span id="creditLimitSpan"></span> % </div> 
          </div>
      </g:if>
      <div class="navbar navbar-inverse">
        <div data-dojo-type="FogPanel.Navigator" id="userTemplateNavigator"> 
        <div class="logo_wrapper"> 
          <a class="brand" href="#/user/home">
            <g:if test="${logoUrl == ''}">                   
                    <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="fogPanelLogo" />                    
                </g:if>
                <g:else>                 
                    <img src="${logoUrl}" class="fogPanelLogo" />                
                </g:else>          
          </a>
            <div class="powered">Powered by <a title="FogPanel" href="http://www.fogpanel.com/" target="new">FogPanel</a></div>
        </div>
          <div data-dojo-type="dijit.Toolbar" class="nav pull-right"> 
            <span>
            <sec:ifLoggedIn>
              <span style="color: #fff"><g:message code="common.loggedIn"/><sec:username/></span>
            </sec:ifLoggedIn>
            <sec:ifSwitched>            - 
              <a href='${request.contextPath}/j_spring_security_exit_user' onclick="logout()">
                <g:message code="common.resumeAs"/> <sec:switchedUserOriginalUsername/>
              </a>
            </sec:ifSwitched>
          </span>
          <input type="hidden" id="selectedANZoneID">
        <!--   <div data-dojo-type="dijit.form.DropDownButton" data-dojo-props="showLabel:true" class="notification-dropdown hidden-phone dashboardContent" title="<g:message code="common.zoneInfo"/>">            
              <a href="#" class="trigger">   
                  <input type="hidden" id="selectedANZoneID">
                  <input type="hidden" id="selectedANVPCID"> 
                  <div class="lng_spain_txt" id="currentAdZoneName"></div>   
                  <div class="zone_active"></div>
                  <i class="icon-sort-down"></i>
              </a>                   
            <div data-dojo-type="dijit.TooltipDialog" class="pop-dialog langContainer zone_popup_dialog" onclick="dijit.popup.close(this);" style="width: 210px !important;">                                              
                <div class="zone-cont">
                    <ul class="notifications" id="userZoneListAd">                                       
                    </ul>
                </div>                
            </div>
          </div> -->
        <div data-dojo-type="dijit.form.DropDownButton" data-dojo-props="showLabel:true" class="notification-dropdown hidden-phone dashboardContent" title="<g:message code="common.zoneInfo"/>">            
            <a href="#" class="trigger">   
                <input type="hidden" id="selectedRegionID">                                          
                <div class="lng_spain_txt" id="currentRegionName"></div>   
                <div class="zone_active"></div>
                <i class="icon-sort-down"></i>
            </a>                   
            <div data-dojo-type="dijit.TooltipDialog" class="pop-dialog langContainer zone_popup_dialog" onclick="dijit.popup.close(this);" style="width: 210px !important;">                                              
                <div class="zone-cont">
                    <ul class="notifications" id="userRegionList">                                       
                    </ul>
                </div>                
            </div>
        </div> 
            <div data-dojo-type="dijit.form.DropDownButton" data-dojo-props="showLabel:true" class="notification-dropdown hidden-phone dashboardContent" title="Language Info">
                <g:if test="${lang.equals("en")}">
                    <a href="#" class="trigger">
                        <div class="lng_eng_txt">EN</div>
                        <div class="lng_eng_icon"></div>
                        <i class="icon-sort-down"></i>
                    </a>
                </g:if>
                <g:elseif test="${lang.equals("es")}">
                    <a href="#" class="trigger">                 
                        <div class="lng_spain_txt">ES</div>   
                        <div class="lng_spain_icon"></div>
                        <i class="icon-sort-down"></i>
                    </a>
                </g:elseif>               
                <div data-dojo-type="dijit.TooltipDialog" class="pop-dialog langContainer lang_popup_dialog" onclick="dijit.popup.close(this);" style="width: 120px !important;">   
                    <div>
                        <div data-dojo-type="FogPanel.LanguageSelector" class="custom_zone_selector"></div>                    
                    </div>                               
                </div>
            </div>
<!--            <div data-dojo-type="dijit.form.DropDownButton" data-dojo-props="showLabel:true" class="notification-dropdown hidden-phone dashboardContent" title="<g:message code="stat.alerts"/>">
              <a href="#" class="trigger">
                <i class="icon-warning-sign"></i>
                <span class="count" id="">0</span>
              </a>
              <div data-dojo-type="dijit.TooltipDialog" class="pop-dialog" onclick="dijit.popup.close(this);">      
                  <div id="userNotificationDiv"></div>
              </div>
            </div>-->
 <!--            <div data-dojo-type="dijit.form.DropDownButton" data-dojo-props="showLabel:true" class="notification-dropdown hidden-phone dashboardContent" title="<g:message code="common.cloudNotification"/>">
              <a href="#" class="trigger" >
                <i class="icon-envelope-alt"></i>
              </a>
              <div data-dojo-type="dijit.TooltipDialog" class="pop-dialog" onclick="dijit.popup.close(this);">
                <div id="userCloudNotification"></div>
              </div>
            </div> -->
              <!--<div data-dojo-type="dijit.form.DropDownButton" data-dojo-props="showLabel:true" class="notification-dropdown hidden-phone dashboardContent" title="<g:message code="common.ticketNotification"/>">
                  <a href="#" class="trigger" >
                    <i class="icon-bell-alt"></i>
                    
                  </a>
                     <div data-dojo-type="dijit.TooltipDialog" class="pop-dialog" onclick="dijit.popup.close(this);">
                    <div id="userTicketNotification"></div>
                  </div>
              </div> -->
            <div data-dojo-type="dijit.form.DropDownButton" title="<g:message code="common.settings"/>" data-dojo-props="showLabel:false, iconClass: 'icon-user'" class="settings hidden-phone dashboardContent">  
                <div data-dojo-type="dijit.TooltipDialog" id="settingsDropdown" class="custom_drp_dwn">       
                    <a href=""></a>                   
                    <a class="customWidth" href="#/user/home/general"onclick="Dashboard.closeDialogue();"> <g:message code="user.accountSettings"/></a>
                    <hr>                                        
                    <div class="customWidth">
                        <a href="#/user/home/billingAlert" onclick="Dashboard.closeDialogue();"> <g:message code="menu.user.activity.userAlert"/></a>
                        <span id="notificationCount" class="count"></span>
                    </div>                    
                        <a href="#/user/home/notification" class="customWidth" onclick="Dashboard.closeDialogue();"> <g:message code="menu.user.activity.notification"/></a>
                        <div class="customWidth">
                            <a href="#/user/support/tickets" onclick="Dashboard.closeDialogue();"> <g:message code="menu.user.tickets"/></a>
                            <span class="count" id="ticketCount"></span>
                        </div>                    
                        <hr>
                        <a href="#/user/home/logout" class="customWidth"  title="<g:message code="common.logout"/>"><g:message code="common.logout"/></a> 
                </div>
            </div>   
            
           <!--<g:link controller='logout' class="icon-share-alt" title="Logout"></g:link>-->
              </div>
        </div>
      </div>
    <div id="userVerticalMenuBar">   
        <!--<ul class="dashboard-menu">        
          <li>  
              <a href="#/user/home" class="mainMenu singleMenu">
                  <i class="icon-home"></i>
                  <span><g:message code="menu.user.home"/></span>    
              </a>    
          </li>        
          <li>
            <a href="#/user/cloud/" class="mainMenu dropdown-toggle">
              <i class="icon-cloud"></i>
              <span><g:message code="menu.user.cloud"/></span>
              <i class="icon-chevron-down"></i>
            </a>
            <ul class="submenu" id="instanceMenu">
              <li><a  href="#/user/cloud/userInstancePage"><g:message code="menu.user.cloud.instance"/></a></li>
                <li><a href="#/user/cloud/storage"><g:message code="menu.user.cloud.storage"/></a></li>
                <li><a href="#/user/cloud/snapShot"><g:message code="menu.user.cloud.snapShot"/></a></li>              
                <li><a href="#/user/cloud/firewall"><g:message code="menu.user.cloud.firewall"/></a></li>
                <li><a href="#/user/health"><g:message code="menu.user.cloud.health"/></a></li>                    
            </ul>
          </li> 
          <li>
              <a href="#/user/service/" class="mainMenu dropdown-toggle">
                  <i class="icon-cog"></i>
                      <span><g:message code="menu.user.services"/></span>
                  <i class="icon-chevron-down"></i>
              </a>
              <ul class="submenu" id="instanceMenu">
                <li><a  href="#/user/service/sshKey"><g:message code="menu.user.services.sshKey"/></a></li>
                <li><a href="#/user/service/vmBandwidth"><g:message code="common.bandwidth"/></a></li> 
                <li><a href="#/user/service/ip"><g:message code="menu.user.services.ipManager"/></a></li>         
              </ul>
          </li> 
          <li>
            <a href="#/user/template" class="mainMenu singleMenu">
              <i class="icon-desktop"></i>
              <span><g:message code="menu.user.templates"/></span>
            </a>
          </li>   
          <li>
            <a class="mainMenu dropdown-toggle" href="#/user/billing">
              <i class="icon-shopping-cart"></i>
              <span><g:message code="menu.user.billing"/></span>
              <i class="icon-chevron-down"></i>
            </a>
            <ul class="submenu">            
              <li><a href="#/user/billing/currentUsage"><g:message code="menu.user.billing.currentUsage"/></a></li>
              <li><a href="#/user/billing/invoiceSummary"><g:message code="menu.user.billing.invoice"/></a></li>
              <li><a href="#/user/billing/payment"><g:message code="menu.user.billing.payments"/></a></li>          
              <li><a href="#/user/billing/recurringItem"><g:message code="menu.user.billing.recurringItems"/></a></li>              
            </ul>
          </li>
          <li>
            <a href="#/user/support/tickets" class="mainMenu singleMenu">
              <i class="icon-user"></i>
              <span><g:message code="menu.user.support"/></span>              
            </a>           
          </li> 
          <li>
            <a class="mainMenu dropdown-toggle" href="#/user/reports">
              <i class="icon-bar-chart"></i>
              <span><g:message code="menu.user.report.stats"/></span>
              <i class="icon-chevron-down"></i>
            </a>
            <ul class="submenu">            
              <li><a href="#/user/reports/billableItem"><g:message code="menu.user.report.billableItem"/></a></li>
              <li><a href="#/user/reports/paymentReport"><g:message code="menu.user.report.paymentReport"/></a></li>              
            </ul>
          </li>
         <li>
            <a href="#/user/home/accountAlert" class="mainMenu dropdown-toggle">
              <i class="icon-exchange"></i>
              <span><g:message code="menu.user.activity"/></span>
              <i class="icon-chevron-down"></i>
            </a>
            <ul class="submenu">
                <li><a href="#/user/home/events"><g:message code="menu.user.activity.events"/></a></li>
                <li><a href="#/user/home/userAlert"><g:message code="menu.user.activity.alert"/></a></li>                
                <li><a href="#/user/home/notification"><g:message code="menu.user.activity.notification"/></a></li>  
            </ul>
          </li> 
          </ul>-->  
      </div>
      <div class="content" id="contentArea">
        <div id="content" class="container-fluid"></div>
      </div>
      <script src="js/ddaccordion.js"></script>
          <script type="text/javascript" src="${resource(dir: 'js')}/theme.js" ></script>
      <div id="templateArea"></div>              
    </body>
</html>
<!--<div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'serviceMenuTooltip', showDelay: 1">Coming Soon</div>-->
<div style="display: none; margin-left:-100px;">
    <div id="serviceMenuItemContentContent">
        <div class="row-fluid">                        
            <div class="span12">
                <div class="srv-menu-close"><a style="float: right; color: #000" onclick="ZoneConfigForMenu.hideToolTip();"><i class="icon-remove-sign"></i></a></div>
                <div class="srv-sub-menu">
                    <ul>
                        <li id="compute-network"><g:message code="common.computAndNetwork"/>
                            <div class="srv-sub-menu-sub" id="compute-network-sub">
                                <div class="cont-pointer"></div>
                                <ul>
                                    <li>
                                        <div class="srv-submenu-title-ipmng"></div><a id="serviceMenuIPManager"><h4><g:message code="menu.user.services.ipManager"/></h4></a>
                                        <p><g:message code="common.ipManager.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-ldbala"></div><a id="serviceMenuLoadBalancer"><h4><g:message code="common.loadBalancer"/></h4></a>                                                                           
                                        <p><g:message code="common.loadBalancer.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-vpn"></div><a id="serviceMenuVPN"><h4><g:message code="common.vpn"/></h4></a>
                                        <p><g:message code="common.vpn.decs"/></p>
                                    </li>
                                    <li style="display: none">
                                        <div class="srv-submenu-title-vpn"></div><a id="serviceMenuSSHKey"><h4><g:message code="menu.user.services.sshKey"/></h4></a>
                                        <p><g:message code="common.sshKey.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-prtfrwd"></div><a id="serviceMenuPortForwarding"><h4><g:message code="common.portForwarding"/></h4></a>
                                        <p><g:message code="common.portForwarding.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-firewall"></div><a id="serviceMenuFirewall"><h4><g:message code="common.fireWall"/></h4></a>
                                        <p><g:message code="common.firewall.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-vpc"></div><a id="serviceMenuVPC" onclick=""><h4><g:message code="common.vpc"/></h4></a>
                                        <p><g:message code="common.vpc.desc"/></p>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li id="cloud-storage">
                        <g:message code="common.cloudStorage"/>
                        <div class="srv-sub-menu-sub" id="cloud-storage-sub">
                            <div class="cont-pointer"></div>
                            <ul>
                                <li>
                                    <div class="srv-submenu-title-stcont"></div><a id="serviceMenuStorageContainer"><h4><g:message code="common.storageContainer"/></h4></a>
                                    <p><g:message code="common.storageContainer.desc"/></p>
                                </li>
                                <li>
                                    <div class="srv-submenu-title-amazs3"></div><a id="serviceMenuAmazonS3"><h4><g:message code="common.amazonS3"/></h4></a>
                                    <p><g:message code="common.amazonS3.desc"/></p>
                                </li>
                            </ul>
                        </div>
                        </li>
                        <li id="cdn-service"><g:message code="common.cdnService"/>
                            <div class="srv-sub-menu-sub" id="cdn-service-sub">
                                <div class="cont-pointer"></div>
                                <ul>
                                    <li>
                                        <div class="srv-submenu-title-maxcnd"></div><a id="serviceMenuMaxCnd"><h4><g:message code="common.maxCdn"/></h4></a>
                                        <p><g:message code="common.maxCdn.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-cloudflr"></div><a id="serviceMenuCloudFlare"><h4><g:message code="common.CloudFlare"/></h4></a>
                                            <p><g:message code="common.CloudFlare.desc"/></p>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li id="netscalar"><g:message code="common.netscalar"/>
                            <div class="srv-sub-menu-sub" id="netscalar-sub">
                                <div class="cont-pointer"></div>
                                <ul>
                                    <li>
                                        <div class="srv-submenu-title-elstyip"></div><a id="serviceMenuElasticIP"><h4><g:message code="common.elasticIP"/></h4></a>
                                        <p><g:message code="common.elasticIP.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-autoscl"></div><a id="serviceMenuAutoScale"><h4><g:message code="common.autoScale"/></h4></a>
                                        <p><g:message code="common.autoScale.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-ldbala"></div><a id="serviceMenuNetScaleLoadBalancer"><h4><g:message code="common.loadBalancer"/></h4></a>
                                        <p><g:message code="common.loadBalancer.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-vpn"></div><a id="serviceMenuNetScaleVPN"><h4><g:message code="common.vpn"/></h4></a>
                                        <p><g:message code="common.vpn.decs"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-prtfrwd"></div><a id="serviceMenuNetScalePortForwarding"><h4><g:message code="common.portForwarding"/></h4></a>
                                        <p><g:message code="common.portForwarding.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-firewall"></div><a id="serviceMenuNetScaleFirewall"><h4><g:message code="common.firewall"/></h4></a>
                                        <p><g:message code="common.firewall.desc"/></p>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li id="other-service">
                            <g:message code="common.others"/>
                            <div class="srv-sub-menu-sub" id="other-service-sub">
                                <div class="cont-pointer"></div>
                                <ul>
                                    <li>
                                        <div class="srv-submenu-title-godaddyssl"></div><a id="serviceMenuGoDaddySSL"><h4> <g:message code="common.goDaddySSL"/></h4></a>
                                        <p> <g:message code="common.goDaddySSL.desc"/></p>
                                    </li>
                                    <li>
                                        <div class="srv-submenu-title-comodossl"></div><a id="serviceMenuComodoSSL"><h4><g:message code="common.comodoSSL"/></h4></a>
                                        <p><g:message code="common.comodoSSL.desc"/></p>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <!--<li>History</li>-->
                    </ul>
                </div>              
            </div>
        </div>
    </div>
</div>
