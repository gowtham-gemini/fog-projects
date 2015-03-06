<html>
    <head>
        <title>
            <g:message code="springSecurity.login.title"/>            
        </title>         
        <style>          
            <g:if test="${bgnImageUrl == ''}">
                body {   
                    background:url("${resource(dir: 'images/bgs/', file: '15.jpg')}") no-repeat !important;
                }
            </g:if>
            <g:else>
                body {   
                    background:url("${bgnImageUrl}") no-repeat;
                }
            </g:else>            
        </style>
    <r:layoutResources/>
    <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/bootstrap/', file: 'bootstrap.css')}" media="screen"/>
    <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled/', file: 'signin.css')}" media="screen"/>
    <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled/', file: 'layout.css')}" media="screen"/>
    <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled/', file: 'elements.css')}" media="screen"/>
    <link rel="stylesheet" href="${resource(dir: 'css/gemini/css/compiled/', file: 'icons.css')}" media="screen"/>
    
    <script type="text/javascript" src="${resource(dir: 'js')}/jquery-1.9.1.min.js"></script>
    </head>
    <body>
    <div class="login-wrapper login">
	<div class="signup_panel_logo"> 
            <g:if test="${logoUrl == ''}">
                <a href="#">
                    <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo" />
                </a> 
            </g:if>
            <g:else>
                <a href="#">
                    <img src="${logoUrl}" class="logo fogPanelLogo" />
                </a> 
            </g:else>            
        </div>
    <div style="float: right; display: none;">
        <g:form controller="login" action="auth" >
            <select name="lang" id="lang" onchange="submit()">
                    <g:if test="${lang == 'en'}">
                        <option value="en" selected>English</option>
                    </g:if>
                    <g:else>
                        <option value="en">English</option>
                    </g:else>
                    <g:if test="${lang == 'es'}">
                        <option value="es" selected>col-md-ish</option>
                    </g:if>
                    <g:else>
                        <option value="es">col-md-ish</option>
                    </g:else>
            </select>
        </g:form>
    </div>
      <div class="box col-md-4">
        <div class="content-wrap">
          <form action='${postUrl}' method='POST' id='loginForm' class='' autocomplete='off'>
            <g:if test='${flash.message}'>
              <div class='alert alert-danger'>${flash.message}</div>
            </g:if>
            <fieldset>
              <h6><g:message code="common.login"/></h6> 
              <input class="form-control" type="text" placeholder="<g:message code="common.username"/>" name='j_username' id='username'/>
              <input class="form-control" type="password" placeholder="<g:message code="common.password"/>" name='j_password' id='password'/>
                <div class="row">
                 	<div class="remember col-md-6">
<!--                   		<input  type='checkbox' name='${rememberMeParameter}' id='remember_me'  <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                   		<label for='remember_me' ><g:message code="springSecurity.login.remember.me.label" /></label>-->
              		</div>
                        <div class="col-md-6">
                          <g:link controller="account" action="forgotPassword" class="forgot">
                            <g:message code="common.forgotPassword"/>
                          </g:link> 
                        </div>
                        <input type="hidden" name="domainName" id="domainName" value="${domain}"/>
                </div>
              <div class="col-md-12"><input type='submit' id="submit" value='<g:message code="common.login"/>' class="btn-flat primary login"/></div>
                <!---->
             
            </fieldset>
          </form>
    <g:if test="${enableSignup == "true"}">   
          
        <g:if test="${trialEnabled == "TRUE"}"> 
          <div class="trial-enable">
            <div id="signup_btn"><div class="col-md-12"><g:message code="common.signUp"/></div> <p><g:message code="common.signUp.msg"/></p></div>
            <!--<g:link controller="account" action="signupTypePage">Sign Up</g:link>-->
			
            <div class="signup_cont row">
                    <div id="rental_cont" class="col-md-6">
                            <div>
                                    <h3><g:message code="common.retail"/></h3>
                                    <p><g:message code="common.retail.msg"/></p>
                            </div>
                                            <div> 
                                    <g:link controller="account" action="retailSignup" class="btn btn-signup" params='[lang: "${lang}"]'> <g:message code="common.go"/></g:link>         
                            </div> 
                    </div>
                    <div id="trail_cont" class="col-md-6">
                                            <div>
                                    <h3><g:message code="common.trial"/></h3>
                                    <p><g:message code="common.trial.msg"/></p>
                            </div>
                            <div> 
                                    <g:link controller="account" action="trialSignup" class="btn btn-signup" params='[lang: "${lang}"]'><g:message code="common.go"/></g:link>        
                            </div> 
                    </div>

            </div>
        </div>
      </g:if>
      <g:else>
	  	<div class="trial-disable">
         <div id="signup_btn_dis"><g:link controller="account" action="retailSignup"><div class="col-md-12"><g:message code="common.signUp"/></div> <p><g:message code="common.signUp.msg"/></p></g:link></div>
		 </div>
      </g:else>
    </g:if>
    </div>
        </div>
      </div>
      
    <script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
<script>
 $.noConflict();
jQuery(document).ready(function($) {
		
	$("#signup_btn").click(function(){
    	$(".signup_cont").slideToggle("slow");
  	});

});
</script>
</body>
</html>
