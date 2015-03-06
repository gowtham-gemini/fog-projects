
       
        
        <link rel="stylesheet" href="${resource(dir: 'js/dojo-1.8/dijit/themes/claro/', file: 'claro.css')}"  media="screen" type="text/css" />
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
            data-dojo-config = "async: true, parseOnLoad:true"></script>
        <script type="text/javascript">
          require([
            "dojo/parser",
            "dojo/dom",
            "dijit/form/ValidationTextBox",
            "dijit/form/Button",
            "dijit/form/CheckBox",
            "dijit/form/RadioButton"
          ]);
        </script>
    
<!--    <body class="claro">-->
      <div class="fieldcontain ${hasErrors(bean: diskOfferingInstance, field: 'displayText', 'error')} required">
        <label for="displayText">
		<g:message code="diskOffering.displayText.label" default="Display Text" />
		<span class="required-indicator">*</span>
	</label>
        <g:textField name="displayText" required="" value="${diskOfferingInstance?.displayText}" 
                     data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: diskOfferingInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="diskOffering.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${diskOfferingInstance?.name}"
                     data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: diskOfferingInstance, field: 'customized', 'error')} ">
	<label for="customized">
		<g:message code="diskOffering.customized.label" default="Customized"  />
		
	</label>
	<g:checkBox name="customized" value="${diskOfferingInstance?.customized}" 
                    data-dojo-type="dijit.form.CheckBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: diskOfferingInstance, field: 'diskSize', 'error')} ">
	<label for="diskSize">
		<g:message code="diskOffering.diskSize.label" default="Disk Size" />
		
	</label>
	<g:field name="diskSize" type="number" value="${diskOfferingInstance.diskSize}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: diskOfferingInstance, field: 'domainId', 'error')} ">
	<label for="domainId">
		<g:message code="diskOffering.domainId.label" default="Domain Id" />
		
	</label>
	<g:textField name="domainId" value="${diskOfferingInstance?.domainId}" data-dojo-type="dijit.form.ValidationTextBox"/>
</div>

<div class="fieldcontain ${hasErrors(bean: diskOfferingInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="diskOffering.tags.label" default="Tags" />
		
	</label>
	<g:textField name="tags" value="${diskOfferingInstance?.tags}" data-dojo-type="dijit.form.ValidationTextBox"/>
        
</div>
<!--    </body>    -->

