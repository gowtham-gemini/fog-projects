<!DOCTYPE html>
<html>
  <head>
    <head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Account Type</title>
    <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'bootstrap.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'bootstrap-responsive.css')}"/>
    <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
            data-dojo-config = "async: true, parseOnLoad:true">
    </script>
    <script type="text/javascript"
        src = "${resource(dir: 'js/app/account')}/signup.js">
    </script>
    <script type="text/javascript"
        src = "${resource(dir: 'js/app')}/password_strength.js">
    </script>
    <script type="text/javascript"
        src = "${resource(dir: 'js')}/app.js">
    </script>
  </head>
  </head>
  <body class="claro" id="accountTypePage">
    <div id="wrapper">
      <div id="accountTypeContainer">
        <h3>Select the type of account you want to sign up for:</h3>
        <div class="row-fluid" id="retailDiv">
           <div class="span4"> 
             <g:link controller="account" action="retailSignup" class="btn btn-primary">Retail</g:link>         
           </div> 
          <div class="span8">
            <h1>Retail</h1>
           <p>Retail customer type. Credit card will be charged automatically when payments are due.</p>
          </div>
        </div>
        <g:if test="${trialEnabled == "TRUE"}">
          <div class="row-fluid" id="trialDiv">
           <div class="span4"> 
              <g:link controller="account" action="trialSignup" class="btn btn-primary">Trial</g:link>        
           </div> 
          <div class="span8">
            <h1>Trial</h1>
           <p>Trial customer type.</p>
          </div>
         </div>
        </g:if>
      </div>
    </div> 
  </body>
</html>
