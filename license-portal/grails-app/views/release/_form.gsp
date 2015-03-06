<%@ page import="com.assistanz.fogpanel.licensemanager.Release" %>



<div class="form-group fieldcontain ${hasErrors(bean: releaseInstance, field: 'product', 'error')} required">
    <label class="control-label col-md-3" for="product">
        <g:message code="release.product.label" default="Product" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:select id="product" name="product.id" class="form-control" from="${com.assistanz.fogpanel.licensemanager.Product.list()}" optionKey="id" required="" value="${releaseInstance?.product?.id}" class="many-to-one"/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: releaseInstance, field: 'productVersion', 'error')} required">
    <label class="control-label col-md-3" for="productVersion">
        <g:message code="release.productVersion.label" default="Product Version" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:textField class="form-control" name="productVersion" required="" value="${releaseInstance?.productVersion}"/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: releaseInstance, field: 'vhdPath', 'error')} ">
    <label class="control-label col-md-3" for="vhdPath">
        <g:message code="release.vhdPath.label" default="Vhd Path" />
    </label>
    <div class="col-md-6">
        <g:textField class="form-control" name="vhdPath"  value="${releaseInstance?.vhdPath}"/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: releaseInstance, field: 'bundlePath', 'error')} ">
    <label class="control-label col-md-3" for="bundlePath">
        <g:message code="release.bundlePath.label" default="Bundle Path" />
    </label>
    <div class="col-md-6">
        <g:textField class="form-control" name="bundlePath" value="${releaseInstance?.bundlePath}"/>
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: releaseInstance, field: 'releaseNotes', 'error')} required">
    <label class="control-label col-md-3" for="releaseNotes">
        <g:message code="release.releaseNotes.label" default="Release Notes" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:textArea class="form-control" name="releaseNotes" rows="8" required="" value="${releaseInstance?.releaseNotes}"/>
    </div>
</div>

