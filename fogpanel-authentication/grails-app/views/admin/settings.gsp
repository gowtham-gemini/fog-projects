<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
    <title>Admin Settings</title>
  </head>
  <body>
    <div id="create-config" class="content scaffold-create" 
         role="main">
      <h1>User Cloud Settings</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">
            ${flash.message}
        </div>
      </g:if>
      <g:hasErrors bean="${settings}">
        <ul class="errors" role="alert">
          <g:eachError bean="${settings}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">
              data-field-id="${error.field}"
            </g:if>
                <g:message error="${error}"/>
                </li>
          </g:eachError>
        </ul>
      </g:hasErrors>
      <g:form action="save">
        <fieldset class="form">
          <div class="fieldcontain ${hasErrors(bean: settings, field: 'uuid', 'error')} required">
            <label for="uuid">
              <g:message code="settings.uuid.label" default="UUID" />
              <span class="required-indicator">
                *
              </span>
            </label>
            <g:textField name="uuid" value="${settings?.uuid}"/>
          </div>
          <div class="fieldcontain ${hasErrors(bean: settings,
              field: 'apiKey', 'error')} required">
              <label for="apiKey">
                <g:message code="settings.apiKey.label" default="Api Key" />
                <span class="required-indicator">*</span>
              </label>
              <g:textField name="apiKey" required="" 
                           value="${settings?.apiKey}"/>
          </div>
          <div class="fieldcontain ${hasErrors(bean: settings,
                  field: 'secretKey', 'error')} required">
                  <label for="secretKey">
                    <g:message code="settings.secretKey.label" default="Secret Key" />
                    <span class="required-indicator">*</span>
                  </label>
                  <g:textField name="secretKey" value="${settings?.secretKey}"/>
          </div>	
        </fieldset>
        <fieldset class="buttons">
          <g:submitButton name="save" class="save"
                          value="${message(code: 'default.button.create.label', 
                          default: 'Create')}" />
        </fieldset>
      </g:form>
    </div>
  </body>
</html>