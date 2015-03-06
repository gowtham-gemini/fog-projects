<%@ page import="com.assistanz.fogpanel.licensemanager.Product" %>

<div class="form-group fieldcontain ${hasErrors(bean: productInstance, field: 'name', 'error')} required">
    <label class="control-label col-md-4" for="name">
        <g:message code="product.name.label" default="Name" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:textField class="form-control" name="name" required="" value="${productInstance?.name}"/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: productInstance, field: 'description', 'error')} required">
    <label class="control-label col-md-4" for="description">
        <g:message code="product.description.label" default="Description" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:textArea class="form-control" rows="1" name="description" required="" value="${productInstance?.description}"/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: productInstance, field: 'licenseQuantity', 'error')} required">
    <label class="control-label col-md-4" for="licenseQuantity">
        <g:message code="product.licenseQuantity.label" default="License Quantity" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:field class="form-control" name="licenseQuantity" type="number" value="${productInstance.licenseQuantity}" required=""/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: productInstance, field: 'code', 'error')} required">
    <label class="control-label col-md-4" for="code">
        <g:message code="product.code.label" default="Code" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:textField class="form-control" name="code"  value="${productInstance.code}" required=""/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: productInstance, field: 'price', 'error')} required">
    <label class="control-label col-md-4" for="price">
        <g:message code="product.price.label" default="Price" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:field class="form-control" name="price" type="number" value="${productInstance.price}" required=""/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: productInstance, field: 'status', 'error')} required">
    <label class="control-label col-md-4" for="status">
        <g:message code="product.status.label" default="Status" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:select class="form-control"  name="status" from="${com.assistanz.fogpanel.licensemanager.Product$Status?.values()}" keys="${com.assistanz.fogpanel.licensemanager.Product$Status.values()*.name()}" required="" value="${productInstance?.status?.name()}" />
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'baseProduct', 'error')} required">
    <label for="baseProduct" class="control-label col-md-4">
        <g:message code="productInstance.baseProduct.label" default="Base Product" />
    </label>
    <div class="col-md-6">
        <g:select id="baseProduct" name="baseProduct.id" from="${productsList}" optionKey="id" value="${productInstance?.baseProduct?.id}" class="form-control" noSelection="['':'-- Choose Base Product --']"/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: productInstance, field: 'url', 'error')} required">
    <label class="control-label col-md-4" for="url">
        <g:message code="product.url.label" default="Product URL" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:textField class="form-control" name="url" required="" value="${productInstance?.url}"/>
    </div>
</div>

<div class="form-group fieldcontain">
    <label class="control-label col-md-4" for="showcasedProduct">
        <g:message code="product.showcase.label" default="Showcase Product" />
    </label>
    <div class="col-md-6">
            
        <g:if test="${productInstance.showcasedProduct == true}">
            <g:checkBox checked ="true" name="showcasedProduct" />
        </g:if>
        <g:else>
            <g:checkBox name="showcasedProduct"/>
        </g:else> 
    </div>
</div>

