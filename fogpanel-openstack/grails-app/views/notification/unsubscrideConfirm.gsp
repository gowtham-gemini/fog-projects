<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unsubscribe</title>
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen"> 
    </head>
    <body>
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
      <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">Are you sure to Unsubscribe?</h1>
      <p>
        <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
            Content
        </p>
      </p>
      
<!--      <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Next Step</h2>
      <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
        Now that you have verified your email address
      </p>-->
        <g:form controller="notification"  id="${token}" action="unsubscribe" params="[force : '1']">
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Yes</button>
                <g:link controller="login" class="btn">No</g:link>
                <!--<button type="button" class="btn">No</button>-->
            </div>
        </g:form>
    </div>
    </body>
</html>
