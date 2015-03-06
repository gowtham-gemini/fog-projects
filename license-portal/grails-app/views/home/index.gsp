<html>
    <head>
        <meta name='layout' content='main'/>
        <title><g:message code="global.list.label"/></title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="home.heading.welcome"/></h1>
            </div>
        </div>

        <div class="stack stack-container">
            <div class="container">
                <div class="col col-md-9 text-justify">
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et mauris velit. Nullam id pellentesque arcu. Integer tincidunt lacinia dictum. Morbi mi metus, lobortis in lacus dignissim, rhoncus posuere nisl. Suspendisse dictum mauris a dolor malesuada aliquet. Morbi nibh libero, blandit vitae bibendum et, facilisis vel leo. Fusce rhoncus risus diam, eget vestibulum nunc tempor ac. Curabitur consequat semper tempus. Nullam eu tristique metus. Cras a leo id risus fermentum rhoncus. Suspendisse sed aliquam felis, eget ultricies dui.
                    </p>
                    <p>
                        Maecenas et lectus suscipit, varius dolor ut, vulputate erat. Duis nec mi mauris. Proin leo dui, tempor ac massa at, ullamcorper vulputate neque. In consequat dolor in justo convallis rhoncus. Aenean consectetur erat at sapien tempor ultrices. Quisque venenatis accumsan turpis eu lobortis. Nunc sed erat interdum, aliquam arcu a, posuere felis. Vivamus eu tellus turpis. Aenean non ornare augue, a sagittis purus. Proin suscipit risus eu commodo tempor. Donec tincidunt gravida nisi nec cursus. Morbi euismod non ipsum rhoncus bibendum. Maecenas odio justo, congue non velit ut, euismod fringilla ligula. Aliquam erat volutpat.
                    </p>
                </div>
                <div class="col col-md-3">
                    <sec:ifLoggedIn>

                        <g:link controller="home" action="dashboard" class="btn btn-primary">
                            <g:message code="global.dashboard.label"/>
                        </g:link>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <g:link action="signup" controller="user" class="btn btn-success"><g:message code="global.signup.label"/></g:link>
                        &nbsp;
                        <g:link action="auth" controller="login" class="btn btn-info"><g:message code="springSecurity.login.button" /></g:link>
                    </sec:ifNotLoggedIn>

                </div>
            </div>
        </div>
    </body>
</html>
