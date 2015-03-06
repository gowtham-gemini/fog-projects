<div data-dojo-type="dijit.Dialog" id="configPropertieReloadDialog" title="<g:message code="common.config.propertieReload"/>" class="span3">
    <div class="row-fluid form-wrapper"> 
        <p> <g:message code="config.changeRelaodAlert.message"/> </p>
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ConfigSync.propertieReload()">
                <g:message code="common.yes"/>
            </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="ConfigSync.cancelReload()">
                <g:message code="common.no"/>
            </button> 
        </div>
    </div> 
</div>
<!--<%@ page import="com.assistanz.cloud.config.ConfigLoader" %>
<div id="configPropertieReloadDialog" style="display:none;">
<g:if test="${ConfigLoader.isConfigModified}">
    <div class="alert alert-warning">
        <i class="icon-warning-sign"></i>
        <g:message code="config.isModified.message"/>
        <a href="#/admin/settings/configSync">
            <i class="icon-exchange"></i>
        </a>
    </div>
</g:if>
<g:if test="${flash.message}">
                    <div class="alert alert-success" role="status">
                        <i class="icon-remove-sign"></i>${flash.message}
                    </div>
</g:if> 
</div>-->
