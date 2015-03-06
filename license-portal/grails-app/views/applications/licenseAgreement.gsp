<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>EULA</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <div class="row">
                    <div class='thumbnail' style="border: 0px;">
                        <div class="caption">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                               <div class="row"> 
                                   <div class="col-xs-12 col-sm-12 col-md-12">
                                       <h1>End User License Agreement for ${productName}</h1>
                                   </div>    
                               </div>
                           </div>
                        </div>
                    </div>    
                </div>
            </div> 
        </div>
        <div class="stack stack-container container">
            <div class='thumbnail' style="border: 0px;">
                <div class="caption">
                    <div class="row">
                        <div style="height: 200px; overflow: auto" class="well">
                            <g:render template="eula"/>
                        </div>
                    </div>
                    <div class="row">
                        <g:link class="btn btn-success pull-right" action="${page}" params="[productId: productId , agree: 1]">I Agree</g:link>
                        <span class="pull-right">&nbsp;&nbsp;</span>
                        <g:link class="btn btn-danger pull-right" controller="home" action="dashboard" >I Disagree</g:link>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
