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
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.min.css')}" media="screen"/>
    <script type="text/javascript" src="${resource(dir: 'js')}/jquery-1.9.1.min.js"></script>
    </head>
    <body>
    <div class="row-fluid login-wrapper login">
	<div class="row-fluid signup_panel_logo"> 
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
                        <option value="es" selected>Spanish</option>
                    </g:if>
                    <g:else>
                        <option value="es">Spanish</option>
                    </g:else>
            </select>
        </g:form>
    </div>
      <div class="box span4">
        <div class="content-wrap">
          <form action='${postUrl}' method='POST' id='loginForm' class='' autocomplete='off'>
            <g:if test='${flash.message}'>
              <div class='alert alert-danger'>${flash.message}</div>
            </g:if>
            <fieldset>
              <h6><g:message code="common.login"/></h6> 
              <input class="span12" type="text" placeholder="<g:message code="common.username"/>" name='j_username' id='username'/>
              <input class="span12" type="password" placeholder="<g:message code="common.password"/>" name='j_password' id='password'/>
                <div class="row-fluid">
                 	<div class="remember span6">
<!--                   		<input  type='checkbox' name='${rememberMeParameter}' id='remember_me'  <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                   		<label for='remember_me' ><g:message code="springSecurity.login.remember.me.label" /></label>-->
              		</div>
                        <input type="hidden" name="domainName" id="domainName" value="${domain}"/>
                </div>
              <div style="text-align:center;" class="span12 "><input type='submit' id="submit" value='<g:message code="common.login"/>' class="btn-flat primary login"/></div>
                <!---->
             
            </fieldset>
          </form>
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
