 <g:hasErrors bean="${userInstance}">     
        <div class="errors" role="alert">
            <g:eachError bean="${userInstance}" var = "error" field = "num" > 
                <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
            </g:eachError>
        </div>
</g:hasErrors>  

 <g:eachError bean="${userInstance}" var = "error" field = "num" > 
    <g:if test="${error in org.springframework.validation.FieldError}"><data-field-id ="${error.field}"></g:if><g:message error="${error}"/>
</g:eachError>