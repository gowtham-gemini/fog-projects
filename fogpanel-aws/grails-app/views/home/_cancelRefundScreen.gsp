<!DOCTYPE html>
<html lang="${lang}">
  <head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${orgName}</title>
        <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  
          media="screen" type="text/css" />
    
     <!--<link rel="shortcut icon" href="${resource(dir: 'images')}/favicon.ico" type="image/x-icon" />-->
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
    <link rel="stylesheet" type="text/css" 
      href = "${resource(dir:'js/dojo-1.8/dojox/grid/resources/', 
      file: 'claroGrid.css')}"/>
    <link type="text/css" rel="stylesheet"
      href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster',
      file: 'Toaster.css')}"/>
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
    <script type="text/javascript">
      require([
        "dojo", 
        "dijit/dijit",
        "dojo/store/JsonRest",
        "dijit/registry",    
        "dijit/form/FilteringSelect",
        "dijit/form/Select",
        "dojo/data/ItemFileWriteStore",
        "FogPanel/Navigator",
        "dojox/form/PasswordValidator",
        "dojo/on",
        "dojox/widget/Toaster",
        "FogPanel/ZoneItem",
        "dijit/TitlePane", 
        "Zone/ZoneCost",
        "dojox/widget/rotator/Slide",
        "dojox/widget/Rotator",
        "dojox/widget/rotator/Pan",
        "List/ListItem",
        "dijit/form/Form",
        "dijit/form/Button",
        "dojox/validate/regexp",
        "dijit/form/ValidationTextBox",
        "List/ListItem",
        "dijit/Dialog",
        "dijit/layout/ContentPane"
        ], function(dojo, dijit, JsonRest, registry, FilteringSelect, Select,ItemFileWriteStore, Navigator) {
            window.registry = registry;           
        });          
    </script>    
  </head>
  <body class="fog-classic">    
      <div class="row-fluid">
        <div class="navbar navbar-inverse">
          <div data-dojo-type="FogPanel.Navigator" id="userTemplateNavigator">          
            <a class="brand" href="">
              <g:if test="${logoUrl == ''}">
                <a href="#">
                    <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo"/>
                </a> 
            </g:if>
            <g:else>
                <a href="#">
                    <img src="${logoUrl}" class="logo fogPanelLogo"/>
                </a> 
            </g:else>
            </a>
            <div data-dojo-type="dijit.Toolbar" class="nav pull-right"> 
            <span>
              <sec:ifLoggedIn>
                <span style="color: #fff"><g:message code="common.loggedIn" /><sec:username/></span>
              </sec:ifLoggedIn>
              <sec:ifSwitched>            - 
                <a href='${request.contextPath}/j_spring_security_exit_user'>
                  <g:message code="common.resumeAs" /> <sec:switchedUserOriginalUsername/>
                </a>
              </sec:ifSwitched>
            </span>
              <g:link controller='logout' class="icon-share-alt" ></g:link>
                </div>
            </div>
          </div>    
    </div> 
    <div class="hero-unit" style="margin-top:15px;">
      <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><g:message code="common.welcomeTo" /> ${orgName}</h1>
      <p>
        <div class="alert alert-danger errorMessage" id="" style="display: block;">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.account.cancel.info" />
        </div>
      </p>
    </div>
  </body>
</html>