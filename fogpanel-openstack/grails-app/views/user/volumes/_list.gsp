<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud/" class="overflowLabel"><g:message code="menu.user.cloud"/></a></li>
            <li>/<li>
            <li class="overflowLabel"><g:message code="menu.user.cloud.volume"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid"><div class="span1"></div>
    <form id="volumeListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">
            <div class="row-fluid filter-block">
                <div class="pull-right" >
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListVolume.populateValues()">
                        <g:message code='common.refresh' />
                    </button> 
                    <a class="btn-flat success new-product" href="#/user/volume/add" title="<g:message code='common.volume.create'/>"><g:message code="common.volume.create"/></a>        
                </div>
            </div>
            <div class="row-fluid">
                <div id="volumeListGrid"></div>
                <div class="alert alert-info hide overflowLabel text_gray" id="noVolumeMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.user.noVolumeCreated"/>&nbsp;&nbsp;<a href="#/user/volume/add"><g:message code="common.createOneNow"/></a>
                </div>
            </div>
        </div>
    </form>
</div>
<input type="hidden" id="seletedVolumeId">
<div data-dojo-type="dijit.Dialog" id="deleteDialog" title="<g:message code='user.vm.deleteVm.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
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

<div data-dojo-type="dijit.Dialog" id="attachToInstanceDialog" title="<g:message code="user.volume.attachToInstance"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span2"><img src="images/vm_attach_icon.png"></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="attachToInstanceForm"> 
                    <input type="hidden" id="volumeId"> 
                    <div id="attachToInstanceFormPage">
                        <div class="control-group">
                            <label for="instanceList" class="control-label"><g:message code="common.instance"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                             <div id="" class="selectOption">
                                <div id="instanceListForAttachment" ></div> 
                                <div class="form_help_icon" style="top: -25px; left: -15px;">
                                    <i class="icon-info-sign" id="instanceListForAttachmentHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'instanceListForAttachmentHelp', showDelay: 1"><g:message code="common.help.message.instanceListForAttachmentHelp"/></div>
                                </div>
                             </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span2 pull-right"> 
                <button id="attachementAddButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListVolume.createAttachment()"><g:message code="common.ok"/></button>
                <button id="attachementCancelButton" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListVolume.closeCreateAttachemntDialog()"><g:message code="common.cancel"/></button>            </div>
           <!--<div class="span5"></div>-->
        </div>
    </div>
</div>
<input type="hidden" id="deatchedVolumeId">
<input type="hidden" id="deatchedInstanceId">
<div data-dojo-type="dijit.Dialog" id="detachVolumeAlertDialog" title="<g:message code='user.volume.detachConfirm.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span2"><img src='images/vm_detach_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.volume.detachConfirm.message' />  </div>            
        </div>        
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListVolume.detachVolume()">
                <g:message code='common.button.detach' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="ListVolume.closeDetachVolumeAlertDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>

<div data-dojo-type="dijit.Dialog" style="display: none" class="customDialgue span6" id="volumeActionLoader" >
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.volumeInfoInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.volumeInfoInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="VolumeInfo.gotoList()"><g:message code='common.gotoVolumeList' /></a>    
    </div>
</div>