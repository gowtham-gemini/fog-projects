<%@ page import="com.assistanz.fogpanel.licensemanager.ProductInstance" %>

<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'name', 'error')} ">
	<label class="control-label col-md-4" for="name">
            <g:message code="productInstance.name.label" default="Name" />
        </label>
        <div class="col-md-6">
            <g:textField class="form-control" name="name" value="${productInstanceInstance?.name}"/>
        </div>
</div>
<div class="form-group fieldcontain required">
    <label class="control-label col-md-4">
            <g:message code="productInstance.productInstanceId.label" default="Product Instance Id" />
            <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:formatNumber number="${productInstanceInstance?.id}" type="number" minIntegerDigits="12" groupingUsed="false" />
    </div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'product', 'error')} required">
    <label class="control-label col-md-4" for="product">
            <g:message code="productInstance.product.label" default="Product" />
            <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:if test="${productInstanceInstance.product.code == "FOG_BASIC"}">
            <select id="product" name="product">
                <g:each in="${productList}">
                    <g:if test="${it.id == productInstanceInstance.product.id}">
                        <option selected="true" value="${it.id}">${it.name}</option>
                    </g:if>
                    <g:else>
                        <option value="${it.id}">${it.name}</option>
                    </g:else>
                </g:each>
            </select>
        </g:if>
        <g:else>
            ${productInstanceInstance.product.name}
        </g:else>
    </div>
</div>
<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'user', 'error')} required">
    <label class="control-label col-md-4" for="user">
            <g:message code="productInstance.user.label" default="User" />
            <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        ${productInstanceInstance?.user?.username}
    </div>
</div>
<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'baseLicense', 'error')} required">
    <label class="control-label col-md-4" for="baseLicense">
            <g:message code="productInstance.baseLicense.label" default="Base License" />
            <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:field class="form-control" name="baseLicense" type="number" value="${productInstanceInstance.baseLicense}" required=""/>
    </div>
</div>
<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'additionalLicense', 'error')} required">
	<label class="control-label col-md-4" for="additionalLicense">
		<g:message code="productInstance.additionalLicense.label" default="Additional Licence" />
		<span class="required-indicator">*</span>
	</label>
        <div class="col-md-6">
           <g:field class="form-control" name="additionalLicense" type="number" value="${productInstanceInstance.additionalLicense}" required=""/>
        </div>
</div>
<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'inceptionDate', 'error')} required">
	<label class="control-label col-md-4" for="inceptionDate">
		<g:message code="productInstance.inceptionDate.label" default="Inception Date" />
	</label>
        <div class="col-md-6">
            <g:formatDate date="${productInstanceInstance?.inceptionDate}" format="dd/MM/yyyy HH:mm" />
        </div>
</div>
<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'expirationDate', 'error')} required">
    <label class="control-label col-md-4" for="expirationDate">
            <g:message code="productInstance.expirationDate.label" default="Expiration Date" />
            <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:datePicker name="expirationDate" value="${productInstanceInstance?.expirationDate}"/>
    </div>
</div>
<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'lastUpdatedOn', 'error')} required">
    <label class="control-label col-md-4" for="lastUpdatedOn">
            <g:message code="productInstance.lastUpdatedOn.label" default="Last Updated Date"/>
    </label>
    <div class="col-md-6">
        <g:hiddenField name="lastUpdatedOn" id="lastUpdatedOn" value="${productInstanceInstance?.lastUpdatedOn}" type="hidden" />
        <span id="updatedDateValue"><g:formatDate date="${productInstanceInstance?.lastUpdatedOn}" format="dd/MM/yyyy HH:mm"/></span> - <a href="#" onclick="product.resetLastUpdatedDate(); return false;">Reset Value</a>
    </div>
</div>
<div class="form-group fieldcontain ${hasErrors(bean: productInstanceInstance, field: 'status', 'error')} required">
    <label class="control-label col-md-4" for="status">
            <g:message code="productInstance.status.label" default="Status" />
            <span class="required-indicator">*</span>
    </label>
    <div class="col-md-6">
        <g:select class="form-control" name="status" from="${com.assistanz.fogpanel.licensemanager.ProductInstance$Status?.values()}" keys="${com.assistanz.fogpanel.licensemanager.ProductInstance$Status.values()*.name()}" required="" value="${productInstanceInstance?.status?.name()}" />
    </div>
</div>