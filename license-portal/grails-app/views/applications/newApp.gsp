<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="applications.newApp.title" /></title>
        <g:set var="entityName" value="${message(code: 'applications.page.title', default: 'My Applications')}" />
        <script type="text/javascript" src="${resource(dir: 'js/')}/application.js"></script>   
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="applications.newApp.title" /></h1>
            </div>
        </div>
        <g:if test='${flash.message}'>
              <div class='alert alert-danger'>${flash.message}</div>
        </g:if>
        <div class="stack stack-container">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <g:form url="[resource:productInstance, action:'save']" >
                        <div class="form-group">
                            <label for="baseProduct" class="control-label col-md-2">
                                <g:message code="productInstance.product.label" default="Product" />
                            </label>
                            <div class="col-md-2">
                                <g:select id="baseProduct" name="baseProduct.id" from="${productsList}" optionKey="id" 
                                    onchange="${remoteFunction(action: 'selectedProduct',
                                    update: 'invoice',
                                    params: '\'productId=\' + this.value')}" noSelection="['':' - Select Product - ']" />
                            </div>
                        </div>
                        </g:form>
                    </div>
                </div>
            </div>
            <div class="container" id="invoice">
                
            </div>
        </div>
    </body>
</html>
