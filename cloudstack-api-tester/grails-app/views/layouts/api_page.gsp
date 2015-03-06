<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
            <title><g:layoutTitle default="CloudStack-API-Integration"/></title>
            <g:layoutHead/>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <script type="text/javascript" src = "${resource(dir: 'js')}/MD5.js"></script>
            <link rel="stylesheet" href="${resource(dir: 'css/', file: 'bootstrap.css')}"  media="screen" type="text/css" />
            <link rel="stylesheet" href="${resource(dir: 'css/', file: 'app_page.css')}"  media="screen" type="text/css" />
            <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.9.2/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
            <script type="text/javascript" src = "${resource(dir: 'js/app/v2.0.0')}/app.js"></script>
            <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
            <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

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
            <div class="row">
                <div class="col-md-2">
<!--                    <div class="col-md-12"><h4>CloudStack Api</h4></div>
                    <div class="col-md-12">
                        <ul>
                            <li><g:link controller='DedicatePublicIpRange' action="index">Home</g:link></li>
                            <li>
                                <a>Network</a>
                                <ul>
                                    <li><g:link  class="${menuCreate}" controller='dedicatePublicIpRange' action="dedicatePublicIpRange" >dedicatePublicIpRange</g:link></li>
                                   
                                </ul>
                            </li>
                            
                    </div>-->
                </div>
                <div class="col-md-10"><g:layoutBody/></div>
            </div>
           
            <div><footer class="navbar navbar-inverse" role="footer">
              <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Copyright © 2014 CloudStack.com. All Rights Reserved</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                </div><!-- /.navbar-collapse -->
              </div><!-- /.container-fluid -->
            </footer></div>
    </body>
    <script type="text/javascript">
      ApiBase.require();
    </script>
    <r:layoutResources />
</html>
