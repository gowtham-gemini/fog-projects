<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="applications.add.additionalLicense" /></title>
        <g:set var="entityName" value="${message(code: 'applications.page.title', default: 'My Applications')}" />
        <script type="text/javascript" src="${resource(dir: 'js/')}/application.js"></script>   
        
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="applications.add.additionalLicense" /></h1>
                <h1 id="page-title">Instance Id : ${productInstance.id}</h1>
            </div>
        </div>
    
        <div class="stack stack-container">
            <div class="container">
                <g:form class="form-verticle" id="${productInstance.id}">  
                <div class="row">
                    <table class="table table-striped table-condensed" id="additionalLicenseListTable">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Hosts Licensed</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Calculation</th>
                            </tr>
                        </thead>    
                        <tbody>
                            <g:each in="${additionalProductList}" status="i" var="additionalProduct">
                                <input name="productId" value="${additionalProduct.id}" type="hidden"/>
                                <input name="price" value="${additionalProduct.price}"  type="hidden"/>
                                <input name="additionalHostQuantity" value="${additionalProduct.licenseQuantity}"  type="hidden"/>   
                                <tr>
                                    <td hidden>
                                        ${additionalProduct.id}
                                    </td>
                                    <td>
                                        ${additionalProduct.name}
                                    </td>
                                    <td>
                                        ${additionalProduct.licenseQuantity}
                                    </td>
                                    <td>
                                        <span id="price-${additionalProduct.id}">${additionalProduct.price}</span>
                                    </td>
                                    <td>
                                       <input name="quantity" type="number" min="0" value="0" class="form-control" onkeyup="product.calculate(this, ${additionalProduct.id})"/>
                                    </td>
                                    <td>
                                        <span id="total-${additionalProduct.id}">0</span>
                                    </td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <g:actionSubmit class="btn btn-primary pull-right" value="buy" action="buyAdditionalLicenseForInstanse" />
                    </div>
                </div>
                </g:form>
            </div>
        </div>
    </body>
</html>
