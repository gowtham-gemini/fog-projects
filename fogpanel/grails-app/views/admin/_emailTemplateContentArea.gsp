<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/</li>
            <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
            <li>/</li>
            <li><a href="#/admin/settings/emailTemplate"><g:message code="common.emailTemplates"/></a></li>
            <li>/</li>
            <li id="emailTemplateName"></li>    
        </ul>
    </div>
</div>
<div class="new-user">
    <div class="row-fluid header">
        <input type="hidden" id="emailTempNameRefer"> 
    </div>
    <div class="row-fluid form-wrapper">
        <div class="span7 with-sidebar" id="addTaxPage">
            <div class="container">
                <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="emailTemplateContentForm">
                    <div class="span12 field-box control-group" id = "emailTemplateTitle">
                        <label for="templateTilte"  class="control-label"><g:message code="common.email.title"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.email.inValidValue"/>', required: true, disabled: true" 
                            id="templateTilte">
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id = "emailTemplateSubject">
                        <label for="templateSubject" class="control-label"><g:message code="common.email.subject"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.email.inValidValue"/>', required: true" 
                            id="templateSubject">
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id = "emailTemplateHeader">
                        <label  for="includeHeader" class="control-label"><g:message code="common.email.includeHeader"/>:</label>         
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                            data-dojo-props="checked: false" id="includeHeader">         
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label class="control-label"><g:message code="common.email.content"/>: <span class="require">*</span></label>
                        <div class="controls">
                            <div data-dojo-type="dijit.Editor" id="emailTemplateContent" class=""></div>
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id = "emailTemplateFooter">
                        <label  for="includeFooter" class="control-label"><g:message code="common.email.includeFooter"/>:</label>    
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                            data-dojo-props="checked: false" id="includeFooter">         
                        </div>
                    </div>
                    <div class="pull-right span2">
                        <button id="emailTempButton" type="button" onclick="EmailTemplateContentInfo.update()" class="defaultbtn" data-dojo-type="dijit.form.Button"><g:message code="common.update"/></button>
                        <!--<button type="button" onclick="EmailTemplateContentInfo.sendMail()" class="defaultbtn" data-dojo-type="dijit.form.Button">Send mail</button>-->
                        <img id="emailTempLoader" style="display: none; float: right" src="${resource(dir: 'images')}/preloader_circle.gif" 
                             alt="reset" height="20" width="20">
                    </div>
                </form>
            </div>
        </div>
        <div class="span5">
        </div>
    </div>
</div>