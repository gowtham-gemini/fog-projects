<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/services" ><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><g:message code="menu.volumeType"/></li>    
        </ul>
    </div>
</div>
<div class="row"> 
    <div id="main-stats">
        <div class="row stats-row">
            <div class="col-md-4 stat">
                <div class="data">
                     <span  class="number" id="adminTotalImages"></span>
                    <g:message code="stat.admin.totalVolumeType"/>
                </div>

            </div>
            <div class="col-md-4 stat">
                <div class="data">
                     <span  class="number" id="adminPublicImages"></span>
                    <g:message code="stat.admin.enabledVolumeType"/>
                </div>

            </div>
            <div class="col-md-4 stat">
                <div class="data">
                     <span  class="number" id="adminProtectedImages"></span>
                    <g:message code="stat.admin.disabledVolumeType"/>
                </div>            
            </div>

        </div>
    </div>
</div>
<div class="row">
    <form id="volumeTypeListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">                   
            <div class="row filter-block" id="adminVolumeTypePullDiv" style="display: block;margin-top: 10px;">
                <div class="pull-right">
<!--                    <button type="button" data-dojo-type= "dijit.form.Button" id="pullImageButtonVolumeType" class="cancelbtn" onclick="" id="">
                        <g:message code="common.pullVolumeTypeFromOpenstack"/>
                        <img id="pullImageLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                    </button>-->
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VolumeTypeList.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                    <a class="btn-flat success new-product" href="#/admin/volumeType/add">+ <g:message code="common.createVolumeType"/></a>
                </div>
            </div>
            <div class="row">
                <div id="adminVolumeTypeList">  
                </div>
                 <div class="alert alert-info text_gray" id="openStackNotConfiguredMsgVolumeType" style="display: none; margin-top: 50px;" >
                    <i class="icon-warning-sign text_gray"></i>
                    <g:message code="common.openStackNotConfigured"/>
                    <a href="#/admin/settings/openstackConfig">
                        <g:message code="common.clickToConfigure"/>
                    </a>
                </div>
                <div class="alert alert-info hide text_gray" id="noVolumeTypeMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.admin.noImages"/>&nbsp;&nbsp;<a href="#/admin/volumeType/add"><g:message code="common.createOneNow"/></a>
                </div>
            </div>
        </div>
    </form>
</div>        
<div data-dojo-type="dijit.Dialog" id="deleteVolumeTypeDialog"  title="<g:message code="common.delete"/>" style="color: black; width: 350px;dispaly:none">
    <input type="hidden" id="currentVolumeTypeDeleteId"/>
    <div class="row form-wrapper">
        <g:message code="common.deleteItem.confirm"/>
        <div class="col-md-5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="VolumeTypeList.confirmDelete()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="VolumeTypeList.closeDeleteVolumeTypeDialog()">
                <g:message code="common.no"/>
            </button>       
        </div>  
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="disableVolumeTypeDialog"  title="<g:message code="common.updateStatus"/>" style="color: black; width: 350px;dispaly:none">
    <input type="hidden" id="currentVolumeTypeStatUpdateId"/>
    <input type="hidden" id="currentVolumeTypeStatus"/>
    
    <div class="row form-wrapper">
        <p id="volumeTypeAvailableConfimMsg"><g:message code="common.deleteItem.confirm"/></p>
        <div class="col-md-5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="VolumeTypeList.volumeTypeAvailableAction()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="VolumeTypeList.closeDialogue()">
                <g:message code="common.no"/>
            </button>       
        </div>  
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="customDialgue col-md-6" id="deleteVolumeTypeLoader">
    <div class="row">
        <div class="col-md-3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="col-md-9">
            <div class="col-md-12"><p><g:message code='common.deleteVolumeTypeInfoOne' /></p></div>
            <div class="col-md-12" style="margin-left: 0"><p><g:message code='common.deleteVolumeTypeInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="VolumeTypeList.gotoList()"><g:message code='common.gotoVolumeTypeList' /></a>
    </div>
</div>
