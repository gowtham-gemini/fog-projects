<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/</li>
            <li><g:message code="menu.admin.configuration.cloudengine"/></li>
        </ul>
    </div>
</div> 
<div class="row-fluid" style="display: block;">
    <form id="adminCloudEngineListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            
            <div class="row-fluid filter-block" >
                <div class="pull-right">
             
                    <a class="btn-flat success new-product" href="#/admin/settings/addCloudEngine">+ <g:message code="common.createCloudEngine"/></a>
                </div>
            </div>
            <div class="row-fluid">
                <div id="adminCloudEngineList">  
                </div>
                <div class="alert alert-info hide text_gray" id="noCloudEngineMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.admin.noCloudengine"/>
                </div>
            </div>
        </div>
    </form>
</div>
<div data-dojo-type="dijit.Dialog" id="cloudEngineDeleteDialog" title="<g:message code="user.network.deleteConfirm"/>" class="span4">
    <input type="hidden" id="currentCloudEngineId">
      <p><g:message code="user.cloudEngine.deleteConfirm.message"/></p>
      <div class="row-fluid offset1">
        <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="CloudEngine.deleteCloudEngine()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="CloudEngine.closeDeleteCloudEngineDialog()"><g:message code="common.cancel"/></button>
    </div>
    </div>

    

