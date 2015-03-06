<html lang="${lang}">
  <head>
    <title><g:message code="common.account.forgotPass.title" /></title>
    <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster/', file: 'Toaster.css')}" >
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
    <link href="${resource(dir: 'css/lib/', file: 'font-awesome.css')}" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen">
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
            locale: '${lang}',
            packages: [
              { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
              { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
            ]
          };
        </script>
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
        <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
    </g:else>
    <script type="text/javascript" src = "${resource(dir: 'js/app')}/forgotPassword.js">
    </script> 
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
    </div>
    <div class="hero-unit">
      <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px"><g:message code="common.account.resetPass.title" /></h1>
      <p>
      <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px"><g:message code="common.promptMessage.email" /></h2>
      <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
         <g:message code="common.account.resetPass.info" />
      </p>
    </p>
    <table>      
      <tr>
        <td><label for="forgotPasswordEmail"><g:message code="common.email" />:</label></td>
        <td class="span4">
          <input data-dojo-type="dijit.form.ValidationTextBox" 
                 data-dojo-props="required:true, promptMessage:'<g:message code="common.promptMessage.email" />', 
                 missingMessage:'<g:message code="common.invalidMessage.email" />',   
                 placeHolder: '<g:message code="common.email" />',
                 regExp: dojox.validate.regexp.emailAddress"
                 id="forgotPasswordEmail">  
        </td>
      </tr>      
      <tr>    
        <td>
        </td>
        <td>
          <button id ="forgotPasswordButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button"
                  onclick="forgotPassword.userName.resetPassword()"><g:message code="common.sendMail" /></button>
          <img id="forgotPasswordLoader" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" 
               alt='<g:message code="common.reset" />' height="20" width="20">  
          <g:link controller='login' style="top: 209px; left: 330px" >
            <button id="cancelForgotPass"  class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" 
                 onclick=""><g:message code="common.cancel" /></button>
      </g:link> 
        </td>           
        <td>
      
    </td>
    </tr> 
    </table>
  </div>   
    <div data-dojo-type="dojox.widget.Toaster"         
         data-dojo-props="positionDirection:'tr-left', duration:8000" id="forgotToaster">
    </div>
    <script type="text/javascript">
      forgotPassword.userName.require();
    </script>    
  </body>
</html>