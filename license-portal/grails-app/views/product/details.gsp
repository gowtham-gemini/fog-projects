<%@ page import="com.assistanz.fogpanel.licensemanager.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="main">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Details</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
                                        <h1>Product Details</h1>
                                    </div>    
                               </div>
                           </div>
                        </div>
                    </div>    
                </div>
            </div> 
        </div>
     
        <div class="container">
            <div class='thumbnail' style="border: 0px;">
                <div class="caption">
                    <g:each in="${productDetails}" var ="product">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row"> 
                                    <div class="col-xs-8 col-sm-6 col-md-6">
                                        <p style="font-size: 25px;">${product.name}</p>
                                    </div>
                                    <div class="col-xs-4 col-sm-offset-4 col-sm-2 col-md-offset-5 col-md-1">
                                        <div class="row">
                                            <g:link class="btn btn-primary btn-sm" controller="applications" action="freeTrial" id="${product.id}">Evaluate</g:link>
                                        </div>
                                    </div>
                                </div> 
                            </div>   
                        </div>    
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                        <p style="font-size: 14px; padding: 5px;"><a href="http://www.fogpanel.com/pricing/">${product.url}</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>    
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                        <p style="font-size: 15px; padding: 5px;">${product.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>    
                    </g:each>
                </div>   
            </div> 
        </div>   
    </body>
</html>
