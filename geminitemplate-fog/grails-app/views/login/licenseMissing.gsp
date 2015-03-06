<html>
    <head>
        <title>
            FogPanel License
        </title>         
    <r:layoutResources/>
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.min.css')}" media="screen"/>
    </head>
    <body class="login-bg-screen">
        <div class="row login-wrapper login">
            <div class="row signup_panel_logo">          
                <a href="#">
                  <img src="${logoUrl}" class="logo fogPanelLogo" />
                </a>          
            </div>
            <div class="box col-md-4">
                <div class="content-wrap">
                  <form action='${postUrl}' method='POST' id='loginForm' class='' autocomplete='off'>
                    <g:if test='${message}'>
                      <div class='alert alert-danger'>${message}</div>
                    </g:if>
                    <fieldset>
                        <h6>Enter License Info</h6> 
                        <input required class="col-md-12" type="text" placeholder="Fog Instance ID" name='fogInstanceId' id='fogInstanceId'/>
                        <input required class="col-md-12" type="email" placeholder="Licensee Email" name='licenseeEmail' id='licenseeEmail'/>
                        <div class="row" style="margin-top: 10px;">
                            <div class="remember col-md-6">
                                Don't have a License?
                            </div>
                            <div class="col-md-6">
                                <a href="https://portal.fogpanel.com" class="forgot">Register</a>
                            </div>
                        </div>
                      <div class="col-md-12"><input type='submit' id="submit" value='save' class="btn-flat primary login"/></div>             
                    </fieldset>
                  </form>
                </div>
            </div>
        </div>
    </body>
</html>
