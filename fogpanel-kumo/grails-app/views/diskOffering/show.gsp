
<!doctype html>
<html>
  <head>
    
    <g:set var="entityName" value="${message(code: 'diskOffering.label', default: 'DiskOffering')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
   </head>
    <body clas="claro">
      <a href="#show-diskOffering" class="skip" tabindex="-1">
        <g:message code="default.link.skip.label" 
                   default="Skip to content&hellip;"/></a>
      <div class="nav" role="navigation">
        <ul>
          <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
          <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
          <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        </ul>
      </div>
      <div id="show-diskOffering" class="content scaffold-show" role="main">
        <h1><g:message code="default.show.label" args="[entityName]" /></h1>
        <g:if test="${flash.message}">
          <div class="message" role="status">${flash.message}</div>
        </g:if>
        <ol class="property-list diskOffering">
          <g:if test="${diskOfferingInstance?.displayText}">
            <li class="fieldcontain">
              <span id="displayText-label" class="property-label">
                <g:message code="diskOffering.displayText.label" default="Display Text" />
              </span>
              <span class="property-value" aria-labelledby="displayText-label">
                <g:fieldValue bean="${diskOfferingInstance}" field="displayText"/>
              </span>
            </li>
          </g:if>
          <g:if test="${diskOfferingInstance?.name}">
            <li class="fieldcontain">
              <span id="name-label" class="property-label">
                <g:message code="diskOffering.name.label" default="Name" />
              </span>
              <span class="property-value" aria-labelledby="name-label">
                <g:fieldValue bean="${diskOfferingInstance}" field="name"/>
              </span>
            </li>
          </g:if>
          <g:if test="${diskOfferingInstance?.customized}">
            <li class="fieldcontain">
              <span id="customized-label" class="property-label">
                <g:message code="diskOffering.customized.label" 
                           default="Customized" />
              </span>
              <span class="property-value" aria-labelledby="customized-label">
                <g:formatBoolean boolean="${diskOfferingInstance?.customized}" />
              </span>
            </li>
          </g:if>
          <g:if test="${diskOfferingInstance?.diskSize}">
            <li class="fieldcontain">
              <span id="diskSize-label" class="property-label">
                <g:message code="diskOffering.diskSize.label"
                           default="Disk Size" />
              </span>
              <span class="property-value" aria-labelledby="diskSize-label">
                <g:fieldValue bean="${diskOfferingInstance}"                                                                                           
                              field="diskSize"/>
              </span>
            </li>
          </g:if>
          <g:if test="${diskOfferingInstance?.domainId}">
            <li class="fieldcontain">
              <span id="domainId-label" class="property-label">
                <g:message code="diskOffering.domainId.label" 
                           default="Domain Id" />
              </span>
              <span class="property-value" aria-labelledby="domainId-label">
                <g:fieldValue bean="${diskOfferingInstance}"
                              field="domainId"/>
              </span>
            </li>
          </g:if>	
          <g:if test="${diskOfferingInstance?.tags}">
            <li class="fieldcontain">
              <span id="tags-label" class="property-label"><g:message code="diskOffering.tags.label" default="Tags" />
              </span>
              <span class="property-value" aria-labelledby="tags-label">
                <g:fieldValue bean="${diskOfferingInstance}" field="tags"/>
              </span>
            </li>
          </g:if>
        </ol>
        
          <g:render template="basicDojo"/>
        
        <g:form>
          <fieldset class="buttons">
            <g:hiddenField name="id" value="${diskOfferingInstance?.id}" />
            <g:link class="edit" action="edit" id="${diskOfferingInstance?.id}">
              <g:message code="default.button.edit.label" default="Edit" /></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
              onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"
              data-dojo-type="dijit.form.Button" label="Delete"/>
            
          </fieldset>
        </g:form>
      </div>
    </body>
    </html>
