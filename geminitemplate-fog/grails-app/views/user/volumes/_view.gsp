<div class="row">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/cloud"><g:message code="menu.user.cloud"/></a></li>
        <li>/</li>
        <li><a href="#/user/volume/list"><g:message code="menu.volumes"/></a></li>
        <li>/</li>
        <li><g:message code="common.view"/></li>    
      </ul>
    </div>
</div>
<input type="hidden" id="currentVolumeId">
<input type="hidden" id="currentVolumeUserUuid">
<input type="hidden" id="currentVolumeUserPassword">
<div class="row">
<div class="table-wrapper products-table">
    <!--<div class="page-header">-->
        <div class="control-group">
            <h3><g:message code="menu.VolumeOverview"/></h3>
        </div></br>
        <div class="pull-right span8">
            <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VolumeInfo.refresh()">
                <g:message code='common.refresh' />
            </button>
            <button class="cancelbtn" style= "margin-left: 10px" data-dojo-type="dijit.form.Button" onclick="VolumeInfo.gotoVolumeList()">
                <g:message code='common.cancel' />
            </button>
        </div>
        <div class="row" style="margin-top: 50px">
            <div class="span6">
                <div class="grd-row-alt1-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeNameInfo"  ></span></div>
                    </div>
                </div>
                <div class="grd-row-alt2-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeIdInfo"></span></div>
                    </div>
                </div>
                <div class="grd-row-alt2-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.project"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeProjectInfo"></span></div>
                    </div>
                </div>
                <div class="grd-row-alt2-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.zone"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeZoneInfo"></span></div>
                    </div>
                </div>
                <div class="grd-row-alt2-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.volumeType"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeTypeInfo"></span></div>
                    </div>
                </div>
                <div class="grd-row-alt2-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.status"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeStatusInfo"></span></div>
                    </div>
                </div>
                <div class="grd-row-alt2-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.size"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeSizeInfo"></span></div>
                    </div>
                </div>
                <div class="grd-row-alt2-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.createdOn"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeCreatedInfo"></span></div>
                    </div>
                </div>
                <div class="grd-row-alt1-tbl">
                    <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.attachedTo"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="volumeAttachedInfo"></span></div>
                    </div>
                </div>
            </div>
            <div class="span6">

            </div>
        </div>
    <!--</div>-->
</div>    
</div>
<!--<div data-dojo-type="dijit.Dialog" id="adminPageVolumeDeleteDialog" title="<g:message code='admin.volume.deleteVm.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row">
        <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='volume.deleteVolume.confirm' />  </div>            
        </div>        
        <div class="row">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="VolumeInfo.delete()">
                <g:message code='default.button.delete.common' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="VolumeInfo.closeDeleteDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog" style="display: none" class="customDialgue span6" id="adminVolumePageActionLoader" >
    <div class="row">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.volumeInfoInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.volumeInfoInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="VolumeInfo.gotoList()"><g:message code='common.gotoVolumeList' /></a>    
    </div>
</div>-->
