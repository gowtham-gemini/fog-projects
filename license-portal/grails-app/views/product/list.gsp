<%@ page contentType="text/html;charset=UTF-8" %>

 <html>
    <head>
        <meta name="layout" content="main">
        <title>Product List</title>
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
                                       <h1>All Products</h1>
                                   </div>    
                               </div>
                           </div>
                        </div>
                    </div>    
                </div>
            </div> 
        </div>
        
        <div class="container">
            <div class="row" style="padding-left: 10px;">
                <g:each in="${products}" var ="product">
                    <div class="col-md-12">
                        <div class="thumbnail">
                            <div class="caption"> 
                                <div class="row">
                                    <div class="col-md-12">
                                        <h3 style="margin-bottom: 5px; margin-top: 5px;">${product.name}</h3>
                                        <p>${product.description}</p>
                                        <p><g:link class="btn btn-primary btn-sm" controller="product" action="details" id="${product.id}">Details</g:link></p>
                                    </div>
                                </div>    
                            </div>
                        </div>
                    </div>
                </g:each>  
            </div> 
        </div>
    </body>
</html>