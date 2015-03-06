<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/admin/infrastructure"><g:message code="menu.admin.cloud.infrastructure"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.cloud.instance"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">   
    <div class="span12">     
        <div data-dojo-type="dijit/layout/TabContainer" id="infrastructureTabContainer" class="row-fluid" style="overflow: visible; display: block; min-height: 500px" tabStrip="true">
            <div onshow="Instance.init()" data-dojo-type="dijit/layout/ContentPane" class="span12" title="<g:message code="menu.user.cloud.instance"/>" selected="true">
                <div class="row-fluid">
                    <div id="main-stats">
                        <div class="row-fluid stats-row">
                            <div class="span3 stat">
                                <div class="data span12">
                                    <span class="number span12" id="adminTotalVM"></span>
                                    <g:message code="stat.vm.totoalVMs"/>
                                </div>

                            </div>
                            <div class="span3 stat">
                                <div class="data">
                                    <span class="number span12" id="adminRunningVM"></span>
                                    <g:message code="stat.vm.runningVMs"/>
                                </div>

                            </div>
                            <div class="span3 stat">
                                <div class="data">
                                    <span class="number span12" id="adminStoppedVM"></span>
                                    <g:message code="stat.vm.stoppedVMs"/>
                                </div>            
                            </div>
                            <div class="span3 stat last">
                                <div class="customData span12">
                                    <div id="adminZoneList"></div>
                                </div>
                                <div class="row-fluid">
                                    <form data-dojo-type="dijit.form.Form" id="adminVmActionForm" class="customForm">
                                        <div class="span3">
                                            <input type="radio" name="vmInfo" id="allVmRadio" 
                                            data-dojo-type="dijit/form/RadioButton"  checked value="All" onclick="InstancesInfo.showInstance(this)">          
                                            <label for="allVmRadio"><g:message code="common.all"/></label>      

                                        </div>
                                        <div class="span4">
                                            <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                                            id="runningVmRadio" value="Running" onclick="InstancesInfo.showInstance(this)">           
                                            <label for="runningVmRadio"><g:message code="common.vmStatus.running"/></label> 


                                        </div>
                                        <div class="span4">  
                                            <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                                            id="stoppedVmRadio" value="Stopped" onclick="InstancesInfo.showInstance(this)"> 
                                            <label for="stoppedVmRadio"><g:message code="common.vmStatus.stopped"/></label>           
                                        </div> 
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>    
            <div onshow="ListVolume.init()" data-dojo-type="dijit/layout/ContentPane" id="infraVolumeTab" class="span12 no-left-space" style="margin-right: 20px" title="<g:message code="menu.volumes"/>" selected="true">
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
                <div class="row-fluid" style="display: block; margin-top: 20px" >
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
            </div>
        </div>
    </div> 
</div>

<!--<div  class="new-user">
    <div class="row-fluid header">
      <h4>Instances</h4>    
    </div>   
    <div class="row-fluid">
        <div id="adminInstanceInfo"></div>
    </div>
    <div class="alert alert-info hide" id="noVMMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.noInstanceAdmin"/>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="migrateVMDialog" title="<g:message code="common.vm.migrateInstance"/>" class="customDialgue">
    <div class="span6 dijitDialogueBackground">
        <p id="hostInfo"></p>
        <input type="hidden" id="vmID">
        <div id="hostListLoadingDiv"></div>  
        <div class="form-horizontal" id="hostListWidDiv">
            <div class="control-group"> 
                <label for="" class="control-label">     
                    <span id="lableValue"></span>
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <div id="hostListDiv">
                    </div> 
                </div>
            </div>
            <div class="control-group offset2"> 
                <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="InstancesInfo.migrateVM()"><g:message code="common.ok"/></button>
                <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="InstancesInfo.closeMigrateVMDialog()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="migrateVMLoader" class="span6">
    <div class="row-fluid" id="processPaymentMessage">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>-->