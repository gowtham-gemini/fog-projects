<%@ page import="com.assistanz.fogpanel.licensemanager.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title>FogPanel License Portal</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                                       <h1>FogPanel License Portal</h1>
                                   </div>    
                               </div>
                           </div>
                        </div>
                    </div>    
                </div>
            </div> 
        </div>    
        
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="row"> 
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class='thumbnail' style="border: 0px;">
                                <div class="caption">
                                    <h4>News and Alerts</h4>
                                    <div class="alert alert-info">
                                        <p>We are happy to announce the new version of FogPanel v0.1.1 stable and tested since February 2014 in production environments.</p>
                                    </div>
                                    <div class="alert alert-warning">
                                        <p>We only offer Evaluation License in this portal, to extend your license period or to purchase license, please contact <a href="mailto:sales@fogpanel.com">sales@fogpanel.com</a></p>
                                    </div>
                                    <div class="row-title">
                                        <h4>Documents</h4>
                                        <ul>
                                            <li><a href="http://docs.fogpanel.com/guide/admin.html" target="_blank">Installation Guide</a></li>
                                            <li><a href="http://docs.fogpanel.com/guide/user.html" target="_blank">User Guide</a></li>
                                            <li><a href="http://docs.fogpanel.com/guide/admin.html" target="_blank">Admin Guide</a></li>
                                            <li><a href="http://docs.fogpanel.com/guide/api.html" target="_blank">API Guide</a></li>
                                            <li><a href="http://confluence.fogpanel.com/" target="_blank">Knowledge Base</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div> 
                        

                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class='thumbnail' style="border: 0px;">
                                <div class="caption">
                                    <h4>Featured Products</h4>
                                        <div class="row">
                                            <g:each in="${Product.list()}" var ="product">
                                                <g:if test="${product.showcasedProduct == true}">
                                                    <div class="col-md-12">
                                                         <div class='thumbnail'>
                                                            <div class="caption">
                                                                <div class="row">
                                                                    <div class="col-md-10">
                                                                        <div class="row">
                                                                            <div class="col-md-12">
                                                                                <div class="row-fluid" style="font-size: 18px;" ><p>${product.name}</p></div>
                                                                                <p style="width:100%; white-space:nowrap; overflow:hidden; text-overflow:ellipsis">${product.description}</p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-2">
                                                                        <div class="row"> 
                                                                            <div class="col-md-12">
                                                                                <span class='pull-right' style="top: 10px; position: relative;">
                                                                                    <g:link class="btn btn-primary btn-sm" controller="product" action="details" id="${product.id}">Details</g:link>
                                                                                </span>
                                                                            </div>    
                                                                        </div>    
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>  
                                                    </div>
                                                </g:if>
                                            </g:each>    
                                        </div>
                                    <g:link controller="product" action="list"><u>More...</u></g:link>
                                </div>   
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
