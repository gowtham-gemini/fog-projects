<%@ page import="com.assistanz.app.Message" %>



<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'subject', 'error')} ">
	<label for="subject">
		<g:message code="message.subject.label" default="Subject" />
		
	</label>
	<g:textField name="subject" value="${messageInstance?.subject}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'body', 'error')} ">
	<label for="body">
		<g:message code="message.body.label" default="Body" />
		
	</label>
	<g:textField name="body" value="${messageInstance?.body}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: messageInstance, field: 'messageDetails', 'error')} ">
	<label for="messageDetails">
		<g:message code="message.messageDetails.label" default="Message Details" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${messageInstance?.messageDetails?}" var="m">
    <li><g:link controller="messageDetail" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="messageDetail" action="create" params="['message.id': messageInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'messageDetail.label', default: 'MessageDetail')])}</g:link>
</li>
</ul>


</div>

