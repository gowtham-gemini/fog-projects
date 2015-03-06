<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cloud Application</title>
        
	    <link rel = "stylesheet" href = "${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
	    <!--  <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}"/>  -->
	    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'main.css')}"/>
            <script type="text/javascript"
              src = "${resource(dir: 'js/alert')}/alertify.js">
            </script>
	    <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
	            data-dojo-config = "async: true, parseOnLoad: false">
	    </script>
            <script type="text/javascript" src ="${resource(dir: 'js')}/app.js"></script>
	    <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
	    <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster/', file: 'Toaster.css')}"  media="screen" type="text/css" />
	    <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojo/resources/', file: 'dojo.css')}" media="screen" />
	    <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/enhanced/resources/claro/', file: 'EnhancedGrid.css')}" media="screen" />        
	    <!--<link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/resources/', file: 'Grid.css')}" media="screen" type="text/css">-->
<!--<link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dojox/grid/enhanced/resources/claro/', file: 'EnhancedGrid.css')}" media="screen" type="text/css">-->
            <!-- <link rel="stylesheet" href="resources/javascript/lib/dojo-release-1.8.0rc1/dojox/grid/enhanced/resources/Enhanced Grid_rtl.css" media="screen" /> -->
      <g:injectCss themeName="default2" ImagesPath="true"/>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            overflow: hidden;
            padding: 0;
            color: #fff;
        }

        #appLayout {
            height: 100%;
        }
        #leftCol {
            width: 14em;
        }

        #loader {
            width: 100%;
            position: static;
            height: 100%;
            left: 0px;
            top: 0px;
            display: block;
            background: #f0f0f0;
        }            
        
        #list {
            height: 200px;
        }
                
    </style>
    
    <script type="text/javascript" src="${resource(dir: 'js/', file: 'core.js')}"></script>
    <script type="text/javascript">
        core.setConfig({
            baseURL: "http://cloud.ci.assistanz.com:8080/CloudPortal",
            homepage: "#/!/config/list"
        });
        
    </script>
</head>
<body class="claro">
    <div id="screen-loader">
        The screen will take sometime to load wait for sometime
    </div>
    <div id="appLayout" class="demoLayout" data-dojo-type="dijit.layout.BorderContainer" data-dojo-props="design: 'headline'">
          <div class="edgePanel" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region: 'top'">
                <ul>
			     <sec:ifNotLoggedIn>
			      <li><g:link controller="account" action="signup">Signup</g:link></li>
			      <li><g:link controller="login" action="index">Login</g:link></li>
			      </sec:ifNotLoggedIn>
			      <sec:ifLoggedIn>
			        <li><g:link controller="logout" action="index">Logout</g:link></li>  
			      </sec:ifLoggedIn>
			    </ul>
               <div data-dojo-type="dojox.widget.Toaster" data-dojo-props="positionDirection:'tr-left'" id="appToaster"></div>           
          </div>
          <div id="contentArea" data-dojo-id="contentArea" class="centerPanel" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region: 'center'">
          <div id="content"></div>
          </div>     
          <div id="footerCol" class="edgePanel" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region: 'bottom'">
              Footer
              <div id="templateArea"></div>
          </div>
          <script type="text/template">
            <div>  
              <g:injectGSP instance="default2"/>
            </div>
            </script>
    </div>
</html>