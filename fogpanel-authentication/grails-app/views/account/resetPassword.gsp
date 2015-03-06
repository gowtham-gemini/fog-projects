<html lang="${lang}">
  <head>
    <title><g:message code="common.account.resetPass.title" /></title>
    <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster/', file: 'Toaster.css')}" >
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen">    
    <link rel="stylesheet" type="text/css" href = "${resource(dir:'css/', file: 'elements.css')}"/>
    <g:if test="${GrailsUtil.isDevelopmentEnv()}" >   
        <script>
        var dojoConfig = {
            async: 1,
            ioPublish: true,
            cacheBust: 1,
            parseOnLoad: false,
            tlmSiblingOfDojo: true,
            locale: '${lang}',
            packages: [
                { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
                { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
            ]
            };
        </script>
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
    </g:if >
    <g:else >
         <script>
         var dojoConfig = {
            async: 1,
            ioPublish: true,
            cacheBust: 0,
            parseOnLoad: false,
            tlmSiblingOfDojo: true,
            packages: [
              { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
              { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
            ]
          };
        </script>
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
        <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
    </g:else> 
    <script type="text/javascript" src = "${resource(dir: 'js/app')}/forgotPassword.js"></script>
    <script type="text/javascript" src = "${resource(dir: 'js/app')}/password_strength.js"></script>
    <script type="text/javascript">      
          forgotPassword.setConfig({
             baseURL: "${baseUrl}",
             homepage: "",
             context: "${contextPath}"
           });        
    </script> 
  </head>
  <body class="fog-classic" style="background: #EEEEEE">
    <div class="navbar navbar-inverse">
      <div class="navbar-inner">
        <g:if test="${logoUrl == ''}">
                <a href="#">
                    <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo" style="height: 35px !important;"/>
                </a> 
            </g:if>
            <g:else>
                <a href="#">
                    <img src="${logoUrl}" class="logo fogPanelLogo" style="height: 35px !important;" />
                </a> 
            </g:else>  
      </div>
    </div>
    <div class="hero-unit">
      <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><g:message code="common.account.resetPass.title" /></h1> 
      <p>
      <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px"><g:message code="common.password" /></h2>
      <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
        <g:message code="common.account.resetPassword.info" />
      </p>
    </p>
    <g:form class="new_user_form inline-input"  url="[action:'savePassword',controller:'Account']">
      <input type="hidden"  name="token" value="${token}">
      <table>
        <tr>
          <td><label for="newPassword"><g:message code="common.newPassword" />:</label></td>
          <td class="span4">
            <input type="password"  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                   onKeyUp="checkPasswordStrength(this.id); clearField(this);" onChange="checkPasswordStrength(this.id); clearField(this);" 
                   id="newPassword" data-dojo-props="promptMessage:'<g:message code="common.password" />', 
                   missingMessage: '<g:message code="common.password.constrain" />',regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}', required: true,
                   placeHolder: '<g:message code="common.password" />'">
          </td>
          <td>
            <div id="password_strength" style="display: none; margin-top: -25px;float: right;">
              <div style="width: 150px; border: #CCCCCC 1px solid;margin: 40px 165px 0 0px">
                <div id="progress_bar" style="height: 2px; border: #FFFFFF 0px solid; font-size: 1px; background-color: #FFD700;"></div>
              </div>
              <span id="strength_text" style="font-family: Arial; font-size: 10px; color: #333333;"><g:message code="common.weak" /></span>
              <input type="hidden" name="strength_id" id="strength_id" value="1" />
            </div>
          </td>
        </tr>
        <tr>          
          <td><label for="confirmPassword"><g:message code="common.confirmPassword" />:</label></td>
          <td>
            <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                   id="confirmPassword" data-dojo-props="promptMessage:'<g:message code="common.password" />', 
                   invalidMessage: '<g:message code="common.passwordMismatch" />',required: true,regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}',
                   placeHolder: '<g:message code="common.confirmPassword" />'" onKeyUp="confirmPassword(this);blockSubmitButton(this)">
          </td>
        </tr>
        <tr>
          <td></td>
          <td>
            <g:submitButton name="save" value="${message(code:'common.save')}" class="btn-flat success offset1" id="passwordSubmitButton" disabled="true" style="color:#FFFFFF; margin-left: 180px !important; font-size: 18px;padding: 5px 11px 5px 9px;" />
            <g:link controller='login'>
              <button  class="defaultbtn" type="button" data-dojo-type="dijit.form.Button"><g:message code="common.cancel" /></button>
            </g:link>
            </td>           
            </tr>
      </table>
    </g:form>
  </div>    
    <script type="text/javascript">
      forgotPassword.userName.require();
    </script>
  </body>  
</html>