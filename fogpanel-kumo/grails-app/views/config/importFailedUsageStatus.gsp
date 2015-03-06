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
                    <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
                        <div class="span12">
                            <div class="span8" style="padding: 16px;">
                                <b>Pull Failed Usage Job</b>
                            </div>
                            <div class="span4 pull-right" style="margin-top: 10px;">
                                <button type="button" id="pullImageButton" class="btn-flat success new-product">

                                    <g:if test="${jobStatus == 'Running'}">
                                        Importing
                                        <img id="pullImageLoaderImage" style="margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                                        <img id="pullImageCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                                    </g:if>
                                    <g:else>
                                        Imported
                                        <img id="pullImageLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                                        <img id="pullImageCheckImage" style="margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                                    </g:else>

                                </button>
                                <g:link class="btn-flat success new-product" controller="config" action="importFailedUsageStatus" title="Check Status">Check Status</g:link>
                            </div>
                        </div> 
                    </div> 
                </div>
                <div class="row-fluid">
                <div class="bs-example">
                    <table class="table">
                        <caption>Failed Usage Sync Job List</caption>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Status</th>
                                <th>Started At</th>
                                <th>Completed At</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${jobList}">
                                <tr>                           
                                <td>${it.id}</td>
                                <td>${it.status.name()}</td>
                                <td>${it.startedAt}</td>
                                <td>${it.completedAt}</td>
                                </tr>
                            </g:each>
                            
                        </tbody>
                    </table>
                </div>
                
            </div>
            </div>
            
        </div>
    </body>
</html>