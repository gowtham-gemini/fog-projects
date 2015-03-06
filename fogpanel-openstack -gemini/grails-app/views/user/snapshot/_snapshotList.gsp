<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
          <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
          <li>/</li>
          <li><a href="#/user/cloud/"><g:message code="menu.admin.cloud"/></a></li>
          <li>/<li>
          <li><g:message code="menu.user.cloud.snapshots"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="" class="span12" style="overflow: visible;" tabStrip="true">
<!--        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.cloud.volumeSnapShots"/>" selected="true" id="" onshow="VolumeSnapshotInfo.populateGridValues()">
            <div class="table-wrapper products-table" >
                <div class="row-fluid filter-block" id="volumeSnapShotsDiv" style="display: none">
                    <div class="pull-right" id="">
                        <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VolumeSnapshotInfo.populateGridValues()">
                            <g:message code='common.refresh' />
                        </button> 
                        <button data-dojo-type="dijit.form.Button" id="" onclick="VolumeSnapshotInfo.showCreateVolumeSnapshotDialog()" class="defaultbtn" >+<g:message code="user.snapshot.createVolumeSnapshot"/></button>
                    </div>
                </div>
                <div class="row-fluid" style="text-align: right; display: none;">          
                    <span>              
                        <g:message code="common.warning"/>:          
                    </span>                
                    <p class="require" style="float: right;"><g:message code="common.volumeSnapshotWarning"/></p>                            
                </div>    
                <div class="row-fluid">
                    
                    <div class="row-fluid">
                        <div id="volumeSnapshotListMsg" style="margin-top: 10px"></div>
                        <div id="volumeSnapshotList"></div>
                        <div class="alert alert-info hide text_gray" id="noVolumeSnapshotDiv" style="display: none;">
                            <i class="icon-exclamation-sign text_gray"></i> 
                            <g:message code="user.snapshot.noVolumeSnapshot"/>&nbsp;&nbsp;<a onclick="VolumeSnapshotInfo.showCreateVolumeSnapshotDialog()"><g:message code="common.createOneNow"/></a>
                        </div>
                    </div>
                    
                </div>
            </div>
            <div class="alert alert-info hide overflowLabel text_gray" id="noVolumeFoundMessageBox" style=" margin-top: -30px; display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.volumeSnapshot.noVolumeCreated"/>
                    &nbsp;&nbsp;<a href="#/user/volume/add"><g:message code="common.createOneNow"/></a>
            </div>
        </div>-->
        <div data-dojo-type="dijit/layout/ContentPane" id="" title="<g:message code="menu.user.cloud.vmSnapShot"/>" onshow="VMSnapshotInfo.populateGridValues()"  selected="true" >
            <div class="table-wrapper products-table">
                <div class="row-fluid filter-block" id="vmSnapShotsDiv" style="display: none">
                    <div class="pull-right" id="">
                        <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.populateGridValues()">
                            <g:message code='common.refresh' />
                        </button> 
                        <button data-dojo-type="dijit.form.Button" id="" onclick="VMSnapshotInfo.showCreateVMSnapshotDialog()" class="defaultbtn" >+<g:message code="user.snapshot.createVMSnapshot"/></button>
                    </div>
                </div>
                <div class="row-fluid" style="text-align: right; display: none;">          
                    <span>              
                        <g:message code="common.warning"/>:          
                    </span>                
                    <p class="require" style="float: right;"><g:message code="common.vmSnapshotWarning"/></p>                            
                </div>    
                <div class="row-fluid">
                    
                    <div class="row-fluid">
                        <div id="vmSnapShotListMsg" style="margin-top: 10px"></div>
                        <div id="vmSnapShotList"></div>
                        <div class="alert alert-info hide text_gray" id="noVMSnapshotDiv" style="display: none;">
                            <i class="icon-exclamation-sign text_gray"></i> 
                            <g:message code="user.vmSnapshot.noVMSnapshot"/>&nbsp;&nbsp;<a onclick="VMSnapshotInfo.showCreateVMSnapshotDialog()"><g:message code="common.createOneNow"/></a>
                        </div>
                    </div>
                    
                </div>
            </div>
            <div class="alert alert-info hide overflowLabel text_gray" id="noVmFoundMessageBoxDiv" style=" margin-top: -30px; display: none">
                <i class="icon-exclamation-sign text_gray"></i> 
                <g:message code="common.vmSnap.noInstance"/>
                <!--&nbsp;&nbsp;<a href="#/user/server/add"><g:message code="common.createOneNow"/></a>-->
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" style="color: black;" class="customDialgue span6" id="snapshotLoader">
  <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createVMSnapInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createVMSnapInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="VMSnapshotInfo.gotoList()"><g:message code='common.gotoVMSnapList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>

<div data-dojo-type="dijit.Dialog" style="color: black;" class="customDialgue span6" id="deleteVMsnapshotLoader">
  <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.deleteVMSnapInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.deleteVMSnapInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="VMSnapshotInfo.gotoList()"><g:message code='common.gotoVMSnapList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>



<div data-dojo-type="dijit.Dialog" id="addVMSnapshotDialog" title="<g:message code="user.vmSnapshot.addTitle"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span2"><img src='images/popup-icons/vm_snapshot_icon.png'/></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="addVMSnapshotForm">  
                    <div id="addVMSnapshotFormPage">
                        <div></div>
                        <div class="control-group span12"> 
                            <label for="vmSnapshotName" class="control-label">      
                                <g:message code="common.name"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" 
                                       data-dojo-type="dijit.form.ValidationTextBox" 
                                       data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                       required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                       regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                       name="Name" id="VMSnapshotName"> 
                                <div class="form_help_icon" style="top: 0; left: -15px;">
                                    <i class="icon-info-sign" id="VMSnapshotNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'VMSnapshotNameHelp', showDelay: 1"><g:message code="common.help.message.VMSnapshotName"/></div>
                                </div>
                            </div>
                        </div>
<!--                        <div class="control-group"> 
                            <label for="vmSnapshotDescription" class="control-label">      
                                <g:message code="common.description"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                            <input type="text" 
                                   data-dojo-type="dijit.form.ValidationTextBox" 
                                   data-dojo-props="invalidMessage: '<g:message code="common.description.invalid"/>',
                                   required: 'required', placeHolder: '<g:message code="common.description.prompt"/>', 
                                   regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                   name="Name" id="vmSnapshotDescription">  
                            </div>
                        </div>-->
                        <div class="control-group span12">
                            <label for="VMsnapshotInstanceList" class="control-label"><g:message code="common.instance"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                             <div id="" class="selectOption">
                                <div id="VMsnapshotInstanceList" ></div> 
                                <div class="form_help_icon" style="top: 0; left: -15px;">
                                    <i class="icon-info-sign" id="VMsnapshotInstanceListHelpMsg"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'VMsnapshotInstanceListHelpMsg', showDelay: 1"><g:message code="common.help.message.VMsnapshotInstanceList"/></div>
                                </div>
                             </div>
                            </div>
                        </div>
                        <div class="field-box control-group hide_text span12" id="vmSnapBillingTypeDiv">
                            <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/>:<span class="require">*</span></label>
                            <div class="controls">
                                <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="billingType" id="hourlyVMSnapBilling" value="hourly" onchange="VMSnapshotInfo.enableMonthlyCost()"/>
                                <label for="hourlyVMSnapBilling" style="float: left;"><g:message code="common.hourly"/></label> 
                                <input type="radio" data-dojo-type="dijit.form.RadioButton" name="billingType"  id="monthlyVMSnapBilling" value="monthly" onchange="VMSnapshotInfo.enableMonthlyCost()"/> 
                                <label for="monthlyVMSnapBilling" style="float: left;"><g:message code="common.monthly"/></label> 
                                <div class="form_help_icon" style="left: -5%;">
                                    <i class="icon-info-sign" id="createVmSnapBillingTypeHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmSnapBillingTypeHelp', showDelay: 1">
                                        <g:message code="user.createVM.billingType.toottip"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="control-group span12">
                            <label for="" class="control-label"><g:message code="common.cost"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <div id="" class="selectOption">                                
                                    <span id="imageSnapshotCost" class="require"></span>
                                </div>
                            </div>
                        </div>
                        <div class="control-group hide_text span12" id="vmSnapMonthCostDiv">
                            <label for="" class="control-label"><g:message code="common.monthCost"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <div id="" class="selectOption">                                
                                    <span id="imageSnapshotMonthCost" class="require"></span>
                                </div>
                            </div>
                        </div>
<!--                        <div class="control-group field-box">
                            <label class="control-label">              
                                <g:message code="common.warning"/>:          
                            </label>
                            <div class="controls">
                                <span class="require"><g:message code="common.vmSnapshotWarning"/></span>
                    </div>
                        </div>-->
                    </div>
                </form>
                </div>
            </div>  
        <div>
            <div class="control-group span2 pull-right"> 
                <button id="addVMSnapshotButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.createVMSnapshot()"><g:message code="common.ok"/></button>
                <button id="cancelVMSshotButon" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.closeCreateVMSnapshotDialog()"><g:message code="common.cancel"/></button>
            </div>
           <!--<div class="span5"></div>-->
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="deleteVMSnap" title="<g:message code="user.snapshot.deleteVMSnapshot"/>" class="span4">
    <input type="hidden" id="currentVMSnap">
      <p><g:message code="user.snapshot.deleteVMSnapshot.confirm"/></p>
      <div class="row-fluid offset1">
        <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.deleteVMSnapshot()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.closeDeleteVMSnapshot()"><g:message code="common.cancel"/></button>
    </div>
</div>

<!-- Volume snapshot -->
        
<div data-dojo-type="dijit.Dialog" style="color: black;" class="customDialgue span6" id="volumeSnapshotLoader">
  <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createVolumeSnapInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createVolumeSnapInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="VolumeSnapshotInfo.gotoList()"><g:message code='common.gotoVolumeSnapList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>

<div data-dojo-type="dijit.Dialog" style="color: black;" class="customDialgue span6" id="volumeSnapshotDeleteLoader">
  <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.deleteVolumeSnapInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.deleteVolumeSnapInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="VolumeSnapshotInfo.gotoList()"><g:message code='common.gotoVolumeSnapList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>



<div data-dojo-type="dijit.Dialog" id="addVolumeSnapshotDialog" title="<g:message code="user.volumeSnapshot.addTitle"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span2"><img src='images/popup-icons/vm_snapshot_icon.png'/></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="addVolumeSnapshotForm">  
                    <div id="addVolumeSnapshotFormPage">
                        <div class="control-group"> 
                            <label for="volumeSnapshotName" class="control-label">      
                                <g:message code="common.name"/> : <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" 
                                       data-dojo-type="dijit.form.ValidationTextBox" 
                                       data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                       required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                       regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                       name="Name" id="VolumeSnapshotName">
                                <div class="form_help_icon" style="top: 0; left: -15px;">
                                    <i class="icon-info-sign" id="VolumeSnapshotNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'VolumeSnapshotNameHelp', showDelay: 1"><g:message code="common.help.message.VolumeSnapshotName"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="control-group"> 
                            <label for="volumeSnapshotDescription" class="control-label">      
                                <g:message code="common.description"/> : <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" 
                                       data-dojo-type="dijit.form.ValidationTextBox" 
                                       data-dojo-props="invalidMessage: '<g:message code="common.description.invalid"/>',
                                       required: 'required', placeHolder: '<g:message code="common.description.prompt"/>', 
                                       regExp: '[a-zA-Z.0-9- ]{0,255}', propercase: true" 
                                       name="Name" id="volumeSnapshotDescription">  
                                <div class="form_help_icon" style="top: 0; left: -15px;">
                                    <i class="icon-info-sign" id="volumeSnapshotDescriptionHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeSnapshotDescriptionHelp', showDelay: 1"><g:message code="common.help.message.volumeSnapshotDescription"/></div>
                                </div>  
                            </div>
                        </div>
                        <div class="control-group">
                            <label for="snapshotVolumeList" class="control-label"><g:message code="common.volume.source.volume"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                             <div id="" class="selectOption">
                                <div id="snapshotVolumeList" ></div> 
                                <div class="form_help_icon" style="top: -25px; left: -15px;">
                                    <i class="icon-info-sign" id="snapshotVolumeListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'snapshotVolumeListHelp', showDelay: 1"><g:message code="common.help.message.snapshotVolumeList"/></div>
                                </div>
                             </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span2 pull-right" style="margin-top: -20px"> 
                <button id="addVolumeSnapshotButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="VolumeSnapshotInfo.createVolumeSnapshot()"><g:message code="common.ok"/></button>
                <button id="cancelVolumeSshotButon" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="VolumeSnapshotInfo.closeCreateVolumeSnapshotDialog()"><g:message code="common.cancel"/></button>
            </div>
            <!--<div class="span5"></div>-->
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="deleteVolumeSnap" title="<g:message code="user.snapshot.deleteVolumeSnapshot"/>" class="span4">
    <input type="hidden" id="currentVolumeSnap">
      <p><g:message code="user.snapshot.deleteVolumeSnapshot.confirm"/></p>
      <div class="row-fluid offset1">
        <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="VolumeSnapshotInfo.deleteVolumeSnapshot()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VolumeSnapshotInfo.closeDeleteVolumeSnapshot()"><g:message code="common.cancel"/></button>
    </div>
</div>
