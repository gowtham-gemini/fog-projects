<!doctype html>
<html>
    <head>
        <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  media="screen" type="text/css" />
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
        <link href="${resource(dir: 'css/lib/', file: 'font-awesome.css')}" rel="stylesheet" type="text/css" />
    </head>
    <body class="fog-classic" style="background: #EEEEEE">
        <div class="navbar navbar-inverse">
            <div class="navbar-inner">
                <div class="navbar-inner">
                    <a>
                        <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo" style="height: 35px !important;"/>
                    </a> 

                </div>
            </div>
        </div>
        <div class="hero-unit">
            <div class="row-fluid span8"> 
                <g:if test="${flash.message}">
                    <div class="alert alert-info" role="status">${flash.message}</div>
                </g:if>
                <div class="row-fluid">
                    <h1 style="font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px">Generate Usage Records in cloud stack</h1>
                    <h2 style="font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px">Generates usage records. This will generate records only if there any records to be generated, i.e if the scheduled usage job was not run or failed</h2>
                    <p style="font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px">
                      Please check cloud stack for job status
                    </p>
                </div>
            </div>
            
        </div>
    </body>
</html>