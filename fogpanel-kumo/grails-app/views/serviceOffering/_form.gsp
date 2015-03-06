
<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'cpuNumber', 'error')} required">
	<label for="cpuNumber">
		<g:message code="serviceOffering.cpuNumber.label" default="Cpu Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cpuNumber" type="number" value="${serviceOfferingInstance.cpuNumber}" required="" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'cpuSpeed', 'error')} required">
	<label for="cpuSpeed">
		<g:message code="serviceOffering.cpuSpeed.label" default="Cpu Speed" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cpuSpeed" type="number" value="${serviceOfferingInstance.cpuSpeed}" required="" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'displayText', 'error')} required">
	<label for="displayText">
		<g:message code="serviceOffering.displayText.label" default="Display Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="displayText" required="" value="${serviceOfferingInstance?.displayText}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'memory', 'error')} required">
	<label for="memory">
		<g:message code="serviceOffering.memory.label" default="Memory" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="memory" type="number" value="${serviceOfferingInstance.memory}" required="" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="serviceOffering.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${serviceOfferingInstance?.name}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'domainId', 'error')} ">
	<label for="domainId">
		<g:message code="serviceOffering.domainId.label" default="Domain Id" />
		
	</label>
	<g:textField name="domainId" value="${serviceOfferingInstance?.domainId}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'hostTags', 'error')} ">
	<label for="hostTags">
		<g:message code="serviceOffering.hostTags.label" default="Host Tags" />
		
	</label>
	<g:textField name="hostTags" value="${serviceOfferingInstance?.hostTags}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'isSystem', 'error')} ">
	<label for="isSystem">
		<g:message code="serviceOffering.isSystem.label" default="Is System" />
		
	</label>
	<g:textField name="isSystem" value="${serviceOfferingInstance?.isSystem}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'limitCpuUse', 'error')} ">
	<label for="limitCpuUse">
		<g:message code="serviceOffering.limitCpuUse.label" default="Limit Cpu Use" />
		
	</label>
	<g:field name="limitCpuUse" type="number" value="${serviceOfferingInstance.limitCpuUse}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'networkRate', 'error')} ">
	<label for="networkRate">
		<g:message code="serviceOffering.networkRate.label" default="Network Rate" />
		
	</label>
	<g:field name="networkRate" type="number" value="${serviceOfferingInstance.networkRate}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'offerHA', 'error')} ">
	<label for="offerHA">
		<g:message code="serviceOffering.offerHA.label" default="Offer HA" />
		
	</label>
	<g:checkBox name="offerHA" value="${serviceOfferingInstance?.offerHA}" data-dojo-type="dijit.form.CheckBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'storageType', 'error')} ">
	<label for="storageType">
		<g:message code="serviceOffering.storageType.label" default="Storage Type" />
		
	</label>
	<g:textField name="storageType" value="${serviceOfferingInstance?.storageType}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'systemViruralMachineType', 'error')} ">
	<label for="systemViruralMachineType">
		<g:message code="serviceOffering.systemViruralMachineType.label" default="System Virural Machine Type" />
		
	</label>
	<g:textField name="systemViruralMachineType" value="${serviceOfferingInstance?.systemViruralMachineType}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serviceOfferingInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="serviceOffering.tags.label" default="Tags" />
		
	</label>
	<g:textField name="tags" value="${serviceOfferingInstance?.tags}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

