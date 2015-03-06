<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
            <li>/<li>
            <li><a href="#/admin/support"><g:message code="common.support"/></a></li>
            <li>/</li>
            <li><g:message code="common.ticket.preDefinedReply"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid form-wrapper" id="pad-wrapper">
    <div class="span4 createvm-banner">
        <img src="${resource(dir: 'images')}/defined_reply.png" height="256" width="300">
    </div>
    <input type="hidden" id="preDefRepId">
    <div class="span6" id="definedReplyPage"> 
        <form class="form-horizontal" id="definedReplyForm" data-dojo-type="dijit.form.Form">
            <div class="span12 control-group field-box">
                <label for="supportDepWidget" class="control-label">
                    <g:message code="common.department"/>
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <div id="supportDepList"></div>
                </div>
            </div>
            <div class="span12 control-group field-box">
                <label for="preDefinedReplySubject" class="control-label">
                    <g:message code="common.subject"/>
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="preDefinedReplySubject"
                    data-dojo-props="invalidMessage: '<g:message code="common.subject.invalid"/>', required: 'true',regExp:'[a-zA-Z0-9- ]{1,200}',
                    placeHolder: '<g:message code="common.subject.placeHolder"/>',  invalidMessage:'<g:message code="common.value.invalid"/>'">
                </div>
            </div>
            <div class="span12 control-group field-box">
                <label for="supportDepWidget" class="control-label">
                    <g:message code="common.content"/>
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <textarea class="span10" rows="10" cols="200"  id="preDefinedReplyContent"></textarea>
                </div>
            </div>
            <div class="pull-right span4">
                <div id="repOpenDiv">
                    <button type="button" id="preDepartButton" onclick="AddDefaultReply.add()" class="defaultbtn" data-dojo-type="dijit.form.Button"><g:message code="common.add"/></button>
                    <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="preDepartLoader" style="display: none"/>
                </div>
                <div id="repEditDiv" style="display: none">
                    <button data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddDefaultReply.edit();">
                        <g:message code="common.update"/>
                    </button>
                    <button data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddDefaultReply.delete();">
                        <g:message code="common.delete"/>
                    </button>
                </div>


            </div>
        </form>
    </div>
</div>
<div class="row-fluid form-wrapper" id="pad-wrapper"></div>
<div class="row-fluid form-wrapper">
    <div id="preDefinedReplyGrid"></div>
</div>


