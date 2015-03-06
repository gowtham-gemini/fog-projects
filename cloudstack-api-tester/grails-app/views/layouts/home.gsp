<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="FogPanel-API-Integration"/></title>
        <g:layoutHead/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${resource(dir: 'css/', file: 'bootstrap.css')}"  media="screen" type="text/css" />
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.9.2/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
        <script type="text/javascript" src = "${resource(dir: 'js/app/v2.0.0')}/app.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse" role="navigation">                
            <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">                    
                    <a class="navbar-brand" href="">CloudStack</a>                    
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->             
        </nav>
        <div style="height: 450px;">
            <g:layoutBody/>
        </div>

        <footer class="navbar navbar-inverse" role="footer">
            <div class="container-fluid">
              <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Copyright © 2014 CloudStack.com. All Rights Reserved</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </footer>
    </body>
    <script type="text/javascript">
        ApiBase.require();
    </script>
</html>