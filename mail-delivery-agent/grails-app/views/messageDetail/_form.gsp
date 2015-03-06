<%@ page import="com.assistanz.app.MessageDetail" %>



<!--<div class="form-group fieldcontain ${hasErrors(bean: messageDetailInstance, field: 'qFrom', 'error')} ">
    <label for="qFrom" class="col-sm-2 control-label">
        <g:message code="mail.from" default="From" />
        <span class="mandatoryStar">*</span>
    </label>
    <div class="col-sm-10">
        <g:textField name="qFrom" class="form-control form-width" required="true" value="${messageDetailInstance?.qFrom}"/>
    </div>    

</div>-->

<div class="form-group fieldcontain ${hasErrors(bean: messageDetailInstance, field: 'qTo', 'error')} ">
    <label for="qTo" class="col-sm-2 control-label">
        <g:message code="mail.to" default="To" />
        <span class="mandatoryStar">*</span>

    </label>
    <div class="col-sm-10">
        <g:textField name="qTo" class="form-control form-width" required="true" value="${messageDetailInstance?.qTo}"/>
    </div>    

</div>

<div class="form-group fieldcontain ${hasErrors(bean: messageDetailInstance, field: 'subject', 'error')} ">
    <label for="subject" class="col-sm-2 control-label">
        <g:message code="message.subject.label" default="Subject" />
        <span class="mandatoryStar">*</span>

    </label>
    <div class="col-sm-10">

        <g:textField name="subject" class="form-control form-width" required="true" 
        value="${messageDetailInstance?.message?.subject}"
        />
    </div>    

</div>

<div class="form-group fieldcontain ${hasErrors(bean: messageDetailInstance, field: 'body', 'error')} ">
    <label for="body" class="col-sm-2 control-label">
        <g:message code="message.body.label" default="Body" />
        <span class="mandatoryStar">*</span>

    </label>
    <div class="col-sm-10">
        <g:textArea  name="body" required="true" value="${messageDetailInstance?.message?.body}" class="form-control form-width" rows="3" style="height: 300px;"/>
    </div>    

</div>

%{--
<div class="fieldcontain ${hasErrors(bean: messageDetailInstance, field: 'qBcc', 'error')} required">
        <label for="qBcc">
                <g:message code="messageDetail.qBcc.label" default="Q Bcc" />
                <span class="required-indicator">*</span>
        </label>
        <g:textField name="qBcc" required="" value="${messageDetailInstance?.qBcc}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: messageDetailInstance, field: 'qCc', 'error')} required">
        <label for="qCc">
                <g:message code="messageDetail.qCc.label" default="Q Cc" />
                <span class="required-indicator">*</span>
        </label>
        <g:textField name="qCc" required="" value="${messageDetailInstance?.qCc}"/>

</div> --}%


