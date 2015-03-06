<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en" class="no-js"><!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>FogPanel License portal <g:layoutTitle default=""/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.png')}" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'customize-panel.css')}"/>
        <!--<link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'font-awesome.min.css')}"/>-->
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/font-awesome/css/', file: 'font-awesome.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'layout.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'media-queries.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'screen.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'style.css')}"/>
        <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}"/>

        <script type="text/javascript" src="${resource(dir: 'js/', file: 'jQuery.js')}" ></script>
        <script type="text/javascript" src="${resource(dir: 'js/', file: 'bootstrap.js')}" ></script>
        <script type="text/javascript" src="${resource(dir: 'js/', file: 'mytest.js')}" ></script>
         <script type="text/javascript" src="${resource(dir: 'js/', file: 'dataTables.bootstrap.js')}" ></script>
         <script type="text/javascript" src="${resource(dir: 'js/', file: 'jquery.dataTables.min.js')}" ></script>
         
        <script type="text/javascript" src="${resource(dir: 'js/', file: 'jQuery.js')}" ></script>
        <script type="text/javascript" src="${resource(dir: 'js/', file: 'bootstrap.js')}" ></script>
        <script type="text/javascript" src="${resource(dir: 'js/', file: 'mytest.js')}" ></script>
        <g:layoutHead/>
    <r:layoutResources />
</head>
<body class="home page page-template-default">
    <div id="layout" class="full-width">
        <header class="light">
            <div class="container">

                <div id="branding" role="banner">
                    <div id="site-title">
                        <a href="${request.contextPath}" title="FogPanel | Apache Cloudstack Billing System" rel="home">
                            <img src="${resource(dir: 'images', file: 'fog_logo.png')}" alt="FogPanel | Apache Cloudstack Billing System" width="134" height="52" />
                        </a>
                    </div>

                    <div id="site-description">Billing System for Apache CloudStack</div>
                </div><!-- #branding -->

                <nav id="primary-nav">
                    <ul id="primary-nav-list" class=""><li id="menu-item-254" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-254"><li><g:link action="dashboard" controller="home"><g:message code="default.home.label"/></g:link></li>
                        <sec:ifLoggedIn>
                            <li id="menu-item-255" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-255"><li><g:link action="index" controller="product">Products</g:link></li>
                            <li id="menu-item-256" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-256"><g:link action="index" controller="productInstance">Instances</g:link></li>
                            <li id="menu-item-259" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-259"><g:link action="list" controller="user">Accounts</g:link></li>
                            <li id="menu-item-260" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-260"><g:link controller="release" action="index" >Releases</g:link></li>
                        </sec:ifLoggedIn>
                        <sec:ifNotLoggedIn>
                            <div id="menu-item-257" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-257"><a href="http://www.fogpanel.com/">FogPanel.com</a></div>
                        </sec:ifNotLoggedIn>
                    </ul>		
                    <div class="theme-form" id="tiny-nav">
                        <div class="select-wrap input-wrap">
                            <i class="icon-angle-down"></i>			
                        </div>
                    </div>
                </nav>

                <div id="header_top_nav">
                    <ul>
                        <li class="phone"><a href="#" onclick="return false;"><i class="fa fa-phone"></i> <span>+1 888 500 1070</span></a></li>
                        <sec:ifSwitched>
                            <li class="top_mini_menu">
                                   <a href='${request.contextPath}/j_spring_security_exit_user'>
                                      Resume as <sec:switchedUserOriginalUsername/>
                                   </a>
                            </li> 
                        </sec:ifSwitched>
                        <sec:ifLoggedIn>  
                            <li class="top_mini_menu">
                                <g:link controller="user" action="profile"><span><i class="fa fa-user"></i> <sec:username/></span></g:link>
                            </li> 
                            <li class="top_mini_menu">
                                <g:link controller="logout"><i class="fa fa-sign-out"></i> <g:message code="global.logout.label"/></g:link>
                            </li> 
                        </sec:ifLoggedIn>
                        <sec:ifNotLoggedIn>
                            <li class="top_mini_menu">
                                <g:link controller='login' action='auth' class=""><g:message code="global.login.label"/></g:link>
                            </li> 
                        </sec:ifNotLoggedIn>
                        </ul>

                </div>
                <div class="clear"></div>
            </div><!-- .container -->
        </header>
        
        <div class="container">
        
         <g:form class="form-horizontal" method="GET" url="[action:'results',controller:'search']">
             <!--<div class="container">-->
                <div class="row" style="margin-top: 20px;">   
                    <div class="col-md-7"></div>
                       
                    <div class="col-md-5">
                        <div class="input-group" style="left: 12px; position: relative">
                        <input focus-me="search.error" name ="name" type="text" class="form-control" placeholder="Search...">
                        <div class="input-group-btn" dropdown is-open="status.isopen">
                            <button role="button"  data-toggle="dropdown" class="btn btn-default  dropdown-toggle" type="button">
                                <span class="drop" style="padding-right: 10px;">All</span> <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-left pull-right" role="menu" aria-labelledby="dLabel" style="margin-right: 70px">
                                <li><a href="#" style="display: none">All</a></li>
                                <li><a href="#">User</a></li>
                                <li><a href="#">Release</a></li>
                                <li><a href="#">Product</a></li>
                                <li><a href="#">Product Instance</a></li>
                                <input type="hidden" name="menuName" id="selectedSearchMenu" value="All">
                            </ul>
                                <button type="submit" tabindex="-1"  class="btn btn-default" value="Search" style="right: 10px;"/>Submit</button>
                        </div>
                     </div>
                    </div>      
                </div>
            <!--</div>--> 
        </g:form>  
          </div>   
        <div id="content" class="post-content" style="min-height: 500px;">
            <g:layoutBody/>
        </div>

        <section id="pre-footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <div class="widget widget_twitter" id="grizzly-twitter-2">
                            <div class="widget-title"><span class="word1">Follow</span> <span class="word2">Us</span> <span class="word3">on</span> <span class="word4">Twitter</span> 
                              <div class="twitter-box">
                                <a class="twitter-timeline" href="https://twitter.com/FogPanel" data-widget-id="495492993260855297" data-chrome="nofooter noborders noheader transparent"  data-dnt="true" width="300" height="200" data-tweet-limit="1" data-aria-polite="assertive"></a>
                                <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
                              </div> 
                            </div>		
                        </div> 					
                    </div>
                    <div class="col-md-3">
                        <div class="widget widget_twitter" id="grizzly-twitter-2">
                            <div class="widget-title"><span class="word1">Blog</span>
                            </div>	
                            <script type="text/javascript" src="//feed.mikle.com/js/rssmikle.js">
                            </script>
                            <script type="text/javascript">(function() {var params = {rssmikle_url: "http://www.fogpanel.com/feed/",
                                rssmikle_frame_width: "100%",rssmikle_frame_height: "150",
                                rssmikle_target: "_blank",rssmikle_font: "Arial, Helvetica, sans-serif",rssmikle_font_size: "13",rssmikle_border: "off",
                                responsive: "off",rssmikle_css_url: "https://portal.fogpanel.com/static/css/rss-feed.css",text_align: "left",text_align2: "left",
                                corner: "off",scrollbar: "off",autoscroll: "off",scrolldirection: "up",scrollstep: "3",mcspeed: "20",
                                sort: "New",rssmikle_title: "off",rssmikle_title_sentence: "",
                                rssmikle_title_link: "",rssmikle_title_bgcolor: "#0066FF",rssmikle_title_color: "#FFFFFF",rssmikle_title_bgimage: "",
                                rssmikle_item_bgcolor: "#373839",rssmikle_item_bgimage: "",rssmikle_item_title_length: "55",rssmikle_item_title_color: "#AAAAAA",
                                rssmikle_item_border_bottom: "off",rssmikle_item_description: "title_only",item_link: "off",
                                rssmikle_item_description_length: "150",rssmikle_item_description_color: "#666666",rssmikle_item_date: "off",
                                rssmikle_timezone: "Etc/GMT",datetime_format: "%b %e, %Y %l:%M:%S %p",rssmikle_item_description_tag: "off",
                                rssmikle_item_description_image_scaling: "off",article_num: "4",rssmikle_item_podcast: "off",keyword_inc: "",
                                keyword_exc: ""};feedwind_show_widget_iframe(params);})();
                            </script>
                            <div style="font-size:10px; text-align:center; width:100%;">
                            </div>
                        </div> 
                    </div>
                    <div class="col-md-3">
                        <div class="widget widget_contact_info" id="contact-info-2">
                            <div class="widget-title"><span class="word1">Contact</span> <span class="word2">Us</span> </div>
                            <p>We are friendly people who love to talk. So go ahead and contact us for cloudstack billing system. </p>
                            <ul><li><i class="fa fa-map-marker"></i>Coimbatore, India</li><li><i class="fa fa-envelope-square"></i><a href="mailto:sales@fogpanel.com">sales@fogpanel.com</a></li><li><i class="fa fa-phone"></i>+91-422-438 2335</li><li><i class="fa fa-phone-square"></i>+1 888 500 1070</li></ul>
                        </div>					
                    </div>
                    <div class="col-md-3">
                        <div class="widget widget_text" id="text-2">
                            <div class="widget-title"><span class="word1">About</span> <span class="word2">FogPanel&trade;</span> <span class="word3"></span> </div>			
                            <div class="textwidget">FogPanel&trade; is a flexible billing system currently developed for Apache Cloudstack&trade;. </div>
                        </div>
                        <div class="fa" id="widget-social-3">
                            <a target="_blank" rel="nofollow" href="https://www.facebook.com/assistanz"><i class="fa-2x fa-facebook-square"></i></a>
                            <a target="_blank" rel="nofollow" href="https://twitter.com/FogPanel"><i class="fa-2x fa-twitter-square"></i></a>
                            <a target="_blank" rel="nofollow" href="mailto:sales@fogpanel.com"><i class="fa-2x fa-envelope-square"></i></a>
                        </div>					
                    </div>
                </div>
            </div>
        </section>

        <footer id="footer">
            <div class="container">
                <div id="footer-right">
                    <nav id="footer-nav">
                        <ul class="" id="footer-nav-list"><li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-162" id="menu-item-162"><a></a></li>
                            <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-160" id="menu-item-160"><a></a></li>
                            <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-161" id="menu-item-161"><a></a></li>
                            <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-163" id="menu-item-163"><a></a></li>
                            <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-225" id="menu-item-225"><a></a></li>
                        </ul>			
                    </nav>
                </div>
                <div class="row">
                        <div class="col-md-6">
                                <div class="12">
                            <div class="col-md-9 col-xs-12">
                                Copyright &copy; <a target="_blank" href="http://www.assistanz.com">AssistanZ</a>. All Rights Reserved.
                            </div>     
                                <div class="col-md-3 col-xs-12"> 
                                    Version - <g:meta name="app.version"/>
                                </div>
                            </div>
                        </div> 
                     
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-12">
                                <div class="col-md-2"></div>
                                    <div class="col-md-10">
                                        <div class="row">
                                            <div class="col-md-4 col-xs-10"><a href="http://www.fogpanel.com/policies/privacy/">Privacy Policy</a></div>
                                            <div class="col-md-6 col-xs-10"><a href="http://www.fogpanel.com/policies/terms-and-conditions-of-use/">Terms and Conditions of Use</a></div>
                                            <div class="col-md-2 col-xs-10"><a href="http://www.fogpanel.com/policies/disclaimer/">Disclaimer</a></div>
                                        </div>        
                                    </div> 
                                </div>
                            </div>    
                        </div>
                </div>    
        </footer>
    </div>
    <g:javascript library="application"/>
<r:layoutResources />
</body>
</html>
