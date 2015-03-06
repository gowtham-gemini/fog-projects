

<!doctype html>
<html>
    <head>
     <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
   
        
    <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
            data-dojo-config = "async: true, parseOnLoad:true">
    </script>
        
        <script type="text/javascript">
            require([
              "dojo/parser",
              "dojo/dom",
              "dijit/form/Button"
            ]);
        </script>    
		<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}"/>
		<g:set var="entityName" value="${message(code: 'serviceOffering.label', default: 'ServiceOffering')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body class="claro">
		<a href="#show-serviceOffering" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-serviceOffering" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list serviceOffering">
			
				<g:if test="${serviceOfferingInstance?.cpuNumber}">
				<li class="fieldcontain">
					<span id="cpuNumber-label" class="property-label"><g:message code="serviceOffering.cpuNumber.label" default="Cpu Number" /></span>
					
						<span class="property-value" aria-labelledby="cpuNumber-label"><g:fieldValue bean="${serviceOfferingInstance}" field="cpuNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.cpuSpeed}">
				<li class="fieldcontain">
					<span id="cpuSpeed-label" class="property-label"><g:message code="serviceOffering.cpuSpeed.label" default="Cpu Speed" /></span>
					
						<span class="property-value" aria-labelledby="cpuSpeed-label"><g:fieldValue bean="${serviceOfferingInstance}" field="cpuSpeed"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.displayText}">
				<li class="fieldcontain">
					<span id="displayText-label" class="property-label"><g:message code="serviceOffering.displayText.label" default="Display Text" /></span>
					
						<span class="property-value" aria-labelledby="displayText-label"><g:fieldValue bean="${serviceOfferingInstance}" field="displayText"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.memory}">
				<li class="fieldcontain">
					<span id="memory-label" class="property-label"><g:message code="serviceOffering.memory.label" default="Memory" /></span>
					
						<span class="property-value" aria-labelledby="memory-label"><g:fieldValue bean="${serviceOfferingInstance}" field="memory"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="serviceOffering.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${serviceOfferingInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.domainId}">
				<li class="fieldcontain">
					<span id="domainId-label" class="property-label"><g:message code="serviceOffering.domainId.label" default="Domain Id" /></span>
					
						<span class="property-value" aria-labelledby="domainId-label"><g:fieldValue bean="${serviceOfferingInstance}" field="domainId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.hostTags}">
				<li class="fieldcontain">
					<span id="hostTags-label" class="property-label"><g:message code="serviceOffering.hostTags.label" default="Host Tags" /></span>
					
						<span class="property-value" aria-labelledby="hostTags-label"><g:fieldValue bean="${serviceOfferingInstance}" field="hostTags"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.isSystem}">
				<li class="fieldcontain">
					<span id="isSystem-label" class="property-label"><g:message code="serviceOffering.isSystem.label" default="Is System" /></span>
					
						<span class="property-value" aria-labelledby="isSystem-label"><g:fieldValue bean="${serviceOfferingInstance}" field="isSystem"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.limitCpuUse}">
				<li class="fieldcontain">
					<span id="limitCpuUse-label" class="property-label"><g:message code="serviceOffering.limitCpuUse.label" default="Limit Cpu Use" /></span>
					
						<span class="property-value" aria-labelledby="limitCpuUse-label"><g:fieldValue bean="${serviceOfferingInstance}" field="limitCpuUse"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.networkRate}">
				<li class="fieldcontain">
					<span id="networkRate-label" class="property-label"><g:message code="serviceOffering.networkRate.label" default="Network Rate" /></span>
					
						<span class="property-value" aria-labelledby="networkRate-label"><g:fieldValue bean="${serviceOfferingInstance}" field="networkRate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.offerHA}">
				<li class="fieldcontain">
					<span id="offerHA-label" class="property-label"><g:message code="serviceOffering.offerHA.label" default="Offer HA" /></span>
					
						<span class="property-value" aria-labelledby="offerHA-label"><g:formatBoolean boolean="${serviceOfferingInstance?.offerHA}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.storageType}">
				<li class="fieldcontain">
					<span id="storageType-label" class="property-label"><g:message code="serviceOffering.storageType.label" default="Storage Type" /></span>
					
						<span class="property-value" aria-labelledby="storageType-label"><g:fieldValue bean="${serviceOfferingInstance}" field="storageType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.systemViruralMachineType}">
				<li class="fieldcontain">
					<span id="systemViruralMachineType-label" class="property-label"><g:message code="serviceOffering.systemViruralMachineType.label" default="System Virural Machine Type" /></span>
					
						<span class="property-value" aria-labelledby="systemViruralMachineType-label"><g:fieldValue bean="${serviceOfferingInstance}" field="systemViruralMachineType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serviceOfferingInstance?.tags}">
				<li class="fieldcontain">
					<span id="tags-label" class="property-label"><g:message code="serviceOffering.tags.label" default="Tags" /></span>
					
						<span class="property-value" aria-labelledby="tags-label"><g:fieldValue bean="${serviceOfferingInstance}" field="tags"/></span>
					
				</li>
				</g:if>
			
			</ol>
			
			
			
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${serviceOfferingInstance?.id}" />
					<g:link class="edit" action="edit" id="${serviceOfferingInstance?.id}"><g:message code="default.button.edit.label" default="Edit" label="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"
					 data-dojo-type="dijit.form.Button" label="Delete"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
