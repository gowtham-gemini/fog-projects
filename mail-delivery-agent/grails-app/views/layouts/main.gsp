<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="FogPanel Mail Manager"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="shortcut icon" href="${resource(dir: 'images/', file: 'favicon.ico')}" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'common.css')}"/>
    
	
    <!-- bootstrap -->
    <link href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}"rel="stylesheet" />
    <link href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}"rel="stylesheet" />
    <link href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.min.css')}"rel="stylesheet" />

    <!-- libraries -->
    <link href="${resource(dir: 'css/lib/', file: 'jquery-ui-1.10.2.custom.css')}" rel="stylesheet" type="text/css" />
    
    <link href="${resource(dir: 'css/lib/', file: 'font-awesome.css')}" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/compiled/', file: 'layout.css')}" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/compiled/', file: 'elements.css')}" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/compiled/', file: 'icons.css')}" />
    

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/compiled/', file: 'index.css')}" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!-- lato font -->
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
        
        <g:layoutHead/>
        
</head>
<body>
    <!-- navbar -->
    <header class="navbar navbar-inverse" role="banner">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${createLink(uri: '/')}">
                
                <img src="${resource(dir: 'images/', file: 'fog_logo.png')}" alt="logo" />
            </a>
        </div>
        <ul class="nav navbar-nav pull-right hidden-xs">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle hidden-xs hidden-sm" data-toggle="dropdown">
                    <sec:ifLoggedIn>
                        <sec:username/>
                    </sec:ifLoggedIn>
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${createLink(uri: '/sensitiveContent/resetPassword')}">Password Reset</a></li>
                </ul>
            </li>
            <li class="settings hidden-xs hidden-sm">
                <a href="${createLink(uri: '/config')}" role="button">
                    <i class="icon-cog"></i>
                </a>
            </li>
            <li class="settings hidden-xs hidden-sm">
                <g:link controller="logout">
                    <i class="icon-off"></i>
                </g:link>
            </li>
        </ul>
    </header>
    <!-- end navbar -->

    <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            <li class="active">
                <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a href="${createLink(uri: '/')}">
                    <i class="icon-home"></i>
                    <span>Home</span>
                </a>
            </li>            
            <li>
                <a href="${createLink(uri: '/messageDetail')}">
                    <i class="icon-inbox"></i>
                    <span>Mails</span>
                </a>
            </li>
            <li>
                <a href="${createLink(uri: '/config')}">
                    <i class="icon-cog"></i>
                    <span>Configuration</span>
                </a>
            </li>
            %{--<li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-group"></i>
                    <span>Users</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="user-list.html">User list</a></li>
                    <li><a href="new-user.html">New user form</a></li>
                    <li><a href="user-profile.html">User profile</a></li>
                </ul>
            </li>
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-edit"></i>
                    <span>Forms</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="form-showcase.html">Form showcase</a></li>
                    <li><a href="form-wizard.html">Form wizard</a></li>
                </ul>
            </li>
            <li>
                <a href="gallery.html">
                    <i class="icon-picture"></i>
                    <span>Gallery</span>
                </a>
            </li>
            <li>
                <a href="calendar.html">
                    <i class="icon-calendar-empty"></i>
                    <span>Calendar</span>
                </a>
            </li>
            <li>
                <a class="dropdown-toggle" href="tables.html">
                    <i class="icon-th-large"></i>
                    <span>Tables</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="tables.html">Custom tables</a></li>
                    <li><a href="datatables.html">DataTables</a></li>
                </ul>
            </li>
            <li>
                <a class="dropdown-toggle ui-elements" href="#">
                    <i class="icon-code-fork"></i>
                    <span>UI Elements</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="ui-elements.html">UI Elements</a></li>
                    <li><a href="icons.html">Icons</a></li>
                </ul>
            </li>
            <li>
                <a href="personal-info.html">
                    <i class="icon-cog"></i>
                    <span>My Info</span>
                </a>
            </li>
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-share-alt"></i>
                    <span>Extras</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="code-editor.html">Code editor</a></li>
                    <li><a href="grids.html">Grids</a></li>
                    <li><a href="signin.html">Sign in</a></li>
                    <li><a href="signup.html">Sign up</a></li>
                </ul>
            </li>
        </ul>--}%
    </div>
    <!-- end sidebar -->


	<!-- main container -->
    <div class="content">

        <!-- settings changer -->
        <!--div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default skin</span>
            </a>
            
            <a href="#" class="skin second_nav" data-file="${resource(dir: 'css/compiled/skins/', file: 'dark.css')}">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div-->

        <g:layoutBody/>
    </div>


	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    
    <script src="${resource(dir: 'js/', file: 'bootstrap.min.js')}"></script>
    
    <script src="${resource(dir: 'js/', file: 'jquery-ui-1.10.2.custom.min.js')}"></script>
    <!-- knob -->
    <script src="${resource(dir: 'js/', file: 'jquery.knob.js')}"></script>
    <!-- flot charts -->
    
    <script src="${resource(dir: 'js/', file: 'jquery.flot.js')}"></script>
    <script src="${resource(dir: 'js/', file: 'jquery.flot.stack.js')}"></script>
    <script src="${resource(dir: 'js/', file: 'jquery.flot.resize.js')}"></script>
    <script src="${resource(dir: 'js/', file: 'theme.js')}"></script>

    <script type="text/javascript">
        $(function () {

            // jQuery Knobs
            $(".knob").knob();



            // jQuery UI Sliders
            $(".slider-sample1").slider({
                value: 100,
                min: 1,
                max: 500
            });
            $(".slider-sample2").slider({
                range: "min",
                value: 130,
                min: 1,
                max: 500
            });
            $(".slider-sample3").slider({
                range: true,
                min: 0,
                max: 500,
                values: [ 40, 170 ],
            });

            

            // jQuery Flot Chart
            var visits = [[1, 50], [2, 40], [3, 45], [4, 23],[5, 55],[6, 65],[7, 61],[8, 70],[9, 65],[10, 75],[11, 57],[12, 59]];
            var visitors = [[1, 25], [2, 50], [3, 23], [4, 48],[5, 38],[6, 40],[7, 47],[8, 55],[9, 43],[10,50],[11,47],[12, 39]];

            var plot = $.plot($("#statsChart"),
                [ { data: visits, label: "Signups"},
                 { data: visitors, label: "Visits" }], {
                    series: {
                        lines: { show: true,
                                lineWidth: 1,
                                fill: true, 
                                fillColor: { colors: [ { opacity: 0.1 }, { opacity: 0.13 } ] }
                             },
                        points: { show: true, 
                                 lineWidth: 2,
                                 radius: 3
                             },
                        shadowSize: 0,
                        stack: true
                    },
                    grid: { hoverable: true, 
                           clickable: true, 
                           tickColor: "#f9f9f9",
                           borderWidth: 0
                        },
                    legend: {
                            // show: false
                            labelBoxBorderColor: "#fff"
                        },  
                    colors: ["#a7b5c5", "#30a0eb"],
                    xaxis: {
                        ticks: [[1, "JAN"], [2, "FEB"], [3, "MAR"], [4,"APR"], [5,"MAY"], [6,"JUN"], 
                               [7,"JUL"], [8,"AUG"], [9,"SEP"], [10,"OCT"], [11,"NOV"], [12,"DEC"]],
                        font: {
                            size: 12,
                            family: "Open Sans, Arial",
                            variant: "small-caps",
                            color: "#697695"
                        }
                    },
                    yaxis: {
                        ticks:3, 
                        tickDecimals: 0,
                        font: {size:12, color: "#9da3a9"}
                    }
                 });

            function showTooltip(x, y, contents) {
                $('<div id="tooltip">' + contents + '</div>').css( {
                    position: 'absolute',
                    display: 'none',
                    top: y - 30,
                    left: x - 50,
                    color: "#fff",
                    padding: '2px 5px',
                    'border-radius': '6px',
                    'background-color': '#000',
                    opacity: 0.80
                }).appendTo("body").fadeIn(200);
            }

            var previousPoint = null;
            $("#statsChart").bind("plothover", function (event, pos, item) {
                if (item) {
                    if (previousPoint != item.dataIndex) {
                        previousPoint = item.dataIndex;

                        $("#tooltip").remove();
                        var x = item.datapoint[0].toFixed(0),
                            y = item.datapoint[1].toFixed(0);

                        var month = item.series.xaxis.ticks[item.dataIndex].label;

                        showTooltip(item.pageX, item.pageY,
                                    item.series.label + " of " + month + ": " + y);
                    }
                }
                else {
                    $("#tooltip").remove();
                    previousPoint = null;
                }
            });
        });
    </script>
    <r:layoutResources />
</body>
</html>