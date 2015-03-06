<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
        <li>/</li>
        <li><g:message code="common.volumes"/></li>    
      </ul>
    </div>
</div>
<div class="row-fluid"> 
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="adminTotalVolumes"></span>
                    <g:message code="stat.adminTotalVolumes"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                  <span class="number" id="adminAttachedVolumes"></span>
                   <g:message code="stat.adminAttachedVolumes"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                  <span class="number" id="adminDetachedVolumes"></span>
                   <g:message code="stat.adminDetachedVolumes"/>
                </div>            
            </div>

        </div>
    </div>
</div>
<div class="row-fluid">   
    <ul class="nav nav-tabs span12 customNav">
        <li>
            <a href="#/admin/infrastructure/instance/"><g:message code="menu.user.cloud.instance"/></a>
        </li>
        <li class="active">
            <a href="#/admin/infrastructure/volumes/"><g:message code="menu.volumes"/></a>
        </li>
       <!-- <li>
            <a href="#/admin/infrastructure/snapShot/"><g:message code="menu.user.cloud.snapShot"/></a>
        </li>  -->  
<!--        <li>
            <a href="#/admin/infrastructure/network/"><g:message code="common.network"/></a>
        </li>-->
    </ul>
</div>
<div class="row-fluid" style="display: block;">
    <form id="adminVolumeListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row-fluid" style="display: none;">
                <div class="value_dollar pull-right"><g:message code="default.valueIn"/><span id="adminVolumeCurrencyValue"></span></div>
            </div>
            <div class="row-fluid filter-block" id="adminVolumePullDiv" style="display: block;margin-top: 0px;">
                <div class="pull-right">
                    
<!--                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ListFlavors.conformPull()" id="pullPlanButton">
                        <g:message code="common.pullVolumeFromOpenstack"/>
                        <img id="pullPlanLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                    </button>-->
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListVolume.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                    <!--<a class="btn-flat success new-product" href="#/admin/flavors/createFlavor">+ <g:message code="common.createFlavor"/></a>-->
                </div>
            </div>
            <div class="row-fluid">
                <div id="adminVolumesList">  
                </div>
                <div class="alert alert-info hide text_gray" id="noVolumeAdminMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.admin.noVolumes"/>
                </div>
            </div>
        </div>
    </form>
</div> 
<input type="hidden" id="currentVolumeId">
<input type="hidden" id="currentVolumeUserUuid">
<input type="hidden" id="currentVolumeUserPassword">
<div data-dojo-type="dijit.Dialog" id="adminVolumeDeleteDialog" title="<g:message code='admin.volume.deleteVm.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='volume.deleteVolume.confirm' />  </div>            
        </div>        
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListVolume.delete()" id="volumeDeleteButton">
                <g:message code='default.button.delete.common' />   
            </button>
            <button id="deleteVolumeCancelButton" data-dojo-type="dijit.form.Button" onclick="ListVolume.closeDeleteDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog" style="display: none" class="customDialgue span6" id="adminVolumeActionLoader" >
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.volumeInfoInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.volumeInfoInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="ListVolume.gotoList()"><g:message code='common.gotoVolumeList' /></a>    
    </div>
</div>


