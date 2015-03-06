<!DOCTYPE html>
<html class="login-bg">
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- bootstrap -->

        <link type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" rel="stylesheet" />
        <link type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" rel="stylesheet" />

    <!-- global styles -->
        <link type="text/css" href="${resource(dir: 'css/compiled/', file: 'layout.css')}" rel="stylesheet" />
        <link type="text/css" href="${resource(dir: 'css/compiled/', file: 'elements.css')}" rel="stylesheet" />
        <link type="text/css" href="${resource(dir: 'css/compiled/', file: 'icons.css')}" rel="stylesheet" />

    <!-- libraries -->
        <link type="text/css" href="${resource(dir: 'css/lib/', file: 'font-awesome.css')}" rel="stylesheet" />

<!-- this page specific styles -->
        <link type="text/css" href="${resource(dir: 'css/compiled/', file: 'signin.css')}" rel="stylesheet" media="screen" />
        <link rel="stylesheet" href="" type="text/css"  />

    <!-- open sans font -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    </head>
    <body>


    <!-- background switcher -->
        <!--div class="bg-switch visible-desktop">
            <div class="bgs">
                <a href="#" data-img="landscape.jpg" class="bg active">
                    <img src="${resource(dir: 'images/bgs/', file: 'landscape.jpg')}" alt="background" />
                </a>
                <a href="#" data-img="blueish.jpg" class="bg">

                    <img src="${resource(dir: 'images/bgs/', file: 'blueish.jpg')}" alt="background" />
                </a>            
                <a href="#" data-img="7.jpg" class="bg">
                    <img src="${resource(dir: 'images/bgs/', file: '7.jpg')}" alt="background" />
                </a>
                <a href="#" data-img="8.jpg" class="bg">
                    <img src="${resource(dir: 'images/bgs/', file: '8.jpg')}" alt="background" />
                </a>
                <a href="#" data-img="9.jpg" class="bg">
                    <img src="${resource(dir: 'images/bgs/', file: '9.jpg')}" alt="background" />
                </a>
                <a href="#" data-img="10.jpg" class="bg">
                    <img src="${resource(dir: 'images/bgs/', file: '10.jpg')}" alt="background" />
                </a>
                <a href="#" data-img="11.jpg" class="bg">
                    <img src="${resource(dir: 'images/bgs/', file: '11.jpg')}" alt="background" />
                </a>
            </div>
        </div-->


        <div class="login-wrapper">
            <a href="#">
                <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo" />
            </a>

            <div class="box span4">
                <div class="content-wrap">
                    <div class='inner'>
                        <h6>${message(code: "springSecurity.login.button")}</h6>    
                        <g:if test='${flash.message}'>
                            <div class='alert alert-danger'>${flash.message}</div>
                        </g:if>

                        <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                            <p>
                                <input class="form-control" placeholder="E-mail address" type='text'  name='j_username' id='username'/>
                            </p>

                            <p>
                                <input type='password' placeholder="Your password" class="form-control" name='j_password' id='password'/>
                            </p>

                            <!--div class="remember">
                                 <input id="remember-me" type="checkbox">
                                 <label for="remember-me">Remember me</label>
                            </div-->

                            <p>
                                <input type='submit' id="submit" class="btn-glow primary login" value='${message(code: "springSecurity.login.button")}'/>
                            </p>
                        </form>
                    </div>
                </div>
            </div>

            <!--div class="no-account">
                <p>Don't have an account?</p>
                <a href="#">Sign up</a>
            </div-->
        </div>

        <!-- scripts -->
        <script src="http://code.jquery.com/jquery-latest.js"></script>

        <script src="${resource(dir: 'js/', file: 'bootstrap.min.js')}"></script>
        <script src="${resource(dir: 'js/', file: 'theme.js')}"></script>

    <!-- pre load bg imgs -->
        <script type="text/javascript">
            $(function () {
            // bg switcher
            var $btns = $(".bg-switch .bg");
            $btns.click(function (e) {
            e.preventDefault();
            $btns.removeClass("active");
            $(this).addClass("active");
            var bg = $(this).data("img");

            $("html").css("background-image", "url('/fogpanel-mail-manager/images/bgs/" + bg + "')");
            });

            });
        </script>
    </body>
</html>