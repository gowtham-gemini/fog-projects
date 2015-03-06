<!DOCTYPE html>
<html>
  <head>
    <title>Cloud App</title>
    <link rel="shortcut icon" href="${resource(dir: 'images')}/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'font.css')}" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster/', file: 'Toaster.css')}" >
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}" media="screen">
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/theme/fog-classic/', file: 'main.css')}"/>
    
<!--    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'bootstrap.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'bootstrap-responsive.css')}"/>-->
   
    <link type="text/css" rel="stylesheet"
      href="${resource(dir: 'js/dojo-1.8/dojox/grid/resources/',
      file: 'claroGrid.css')}"/>
    <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js" data-dojo-config="async: true, parseOnLoad:false"></script>
    <script type="text/javascript" src="${resource(dir: 'js')}/core.js" ></script>
    <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/NodeList-traverse.js"
            data-dojo-config = "async: true, parseOnLoad:true">
    </script>
    <script type="text/javascript">
        require([ 
            "dojo",
            "dijit/dijit", 
            "dojo/query",
            "dojo/store/JsonRest",
        ],function(dojo, dijit, query, JsonRest) {   
          var config = new JsonRest({
                target: "/FogPanel/api/config/test/"
            });
            config.query().then(function(data) {
                if(data == "OK") {
                     core.setConfig({
                        baseURL: "${baseUrl}",
                        homepage: "#/admin/dashboard"
                      });                  
                } else {
                    core.setConfig({
                      baseURL: "${baseUrl}",
                      homepage: "#/admin/setup/welcome"
                    });
                }       
            });      
           
        });
      </script>
  </head>
<body class="fog-classic">
  
  <div id="screen-loader">
    <div class="container">
      <div class="row-fluid">
      <div class="span5 offset4">
      The screen will take sometime to load wait for sometime
      </div>
    </div>
      <div class="row-fluid">
      <div class="progress progress-striped active span4 offset4">
        <div class="bar" style="width: 50%;"></div>
      </div>
      </div>
  </div>
</div>
<!--</div>-->
<div id="appLayout" class="demoLayout" data-dojo-type="dijit.layout.BorderContainer" data-dojo-props="design: 'headline'">
    <div class="edgePanel" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region: 'top'">
      <div id="header">
        <div class="container">
            <div class="navbar">
              <div class="navbar-inner">
                <a class="brand" href="#">FogPanel</a>
                <ul class="nav pull-right">
                  <li class="active"><a href="#">Home</a></li>
                  <li> <a href="#">Cloud</a></li>
                  <li> <a href="#">App Store</a></li>
                  <li> <a href="#">Usage & Billing</a></li>
                  <li> <a href="#">Helpdesk</a></li>
                  <li> <g:link controller='logout'>Logout</g:link></li>
                </ul>
              </div>
            </div>
          </div>
      </div>
      <div data-dojo-type="dojox.widget.Toaster" data-dojo-props="positionDirection:'tr-left'" id="appToaster"></div> 
    </div>
    <div id="contentArea" data-dojo-id="contentArea" class="centerPanel" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region: 'center'">
      <div class="container" style="height: 100%;">
          <div id="content" class="row-fluid"></div>
        </div>
    </div>          
    <div id="footerCol" class="edgePanel" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region: 'bottom'">
      <div class="container">
        <ul class="footerMenu">
          <li> <a href="#">User Guide</a></li>
          <li> <a href="#">Report a bug</a></li>
          <li> <a href="#">About Fogpanel</a></li>
          <li> <a href="#">Support</a></li>          
        </ul>
        <span class="poweredBy">Powered by &copy; <a href="#/admin/setup/welcome">FogPanel</a></span>
      </div>
      <div id="templateArea"></div>
    </div>
</div>
</body>
</html>