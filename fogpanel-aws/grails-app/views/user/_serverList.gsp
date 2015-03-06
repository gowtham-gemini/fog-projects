<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/cloud/" class="overflowLabel"><g:message code="menu.user.cloud"/></a></li>
    <li>/<li>
    <li class="overflowLabel"><g:message code="menu.user.cloud.instance"/></li>
  </ul>
</div>
</div>
<div class="row-fluid"><div class="span1"></div></div>
<div class="table-wrapper products-table">
  <div class="row-fluid filter-block">
    <div class="alert alert-danger errorMessage overflowLabel" id="vmLimitReached" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.reachedLimit"/>
    </div>  
    <div class="pull-right" id="createVmButtom">
        <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListServer.populateValues()">
            <g:message code='common.refresh' />
        </button> 
        <a class="btn-flat success new-product" href="#/user/server/add" title="<g:message code='common.vm.create'/>"><g:message code="common.vm.create"/></a>        
    </div>
  </div>
  <div class="row-fluid">
    <div id="instanceGrid"></div>
    <div class="alert alert-info hide overflowLabel text_gray" id="noVmMessageBox" style="display: none">
      <i class="icon-exclamation-sign text_gray"></i> 
      <g:message code="common.user.noInstance"/>&nbsp;&nbsp;<a href="#/user/server/add"><g:message code="common.createOneNow"/></a>
    </div>
  </div>
</div>
<input type="hidden" id="seletedServerId">
<div data-dojo-type="dijit.Dialog" id="stopDialog" title="<g:message code='user.vm.stopVm.title' />" style="color: black; width: 350px; background: #FFFFFF">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_shutdown_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.stopVm.confirm' /></div>            
        </div>
    </div>
    <div class="row-fluid">           
        <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListServer.stopInstance()" id="instanceStopButton"> <g:message code='common.ok' /> </button>
        <button id="instanceCancelButton" data-dojo-type="dijit.form.Button" onclick="ListServer.closeStopDialog()" class="cancelbtn"> <g:message code='common.cancel' /></button> 
    </div>                    
</div>    
<div data-dojo-type="dijit.Dialog" id="startDialog" title="<g:message code='user.vm.startVm' />" style="color: black; width: 350px;" class="customDialgue">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_play_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.startVm.confirm' /></div>            
        </div>
    </div>
    <div class="row-fluid">
        <button type="button" data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="ListServer.startInstance()" id="instanceStartButton">   
            <g:message code='common.ok' />    
        </button>
        <button class="cancelbtn" id="startInstanceCancelButton" data-dojo-type="dijit.form.Button" onclick="ListServer.closeStartDialog()">
            <g:message code='common.cancel' />
        </button> 
    </div>        
</div>
<div data-dojo-type="dijit.Dialog" id="rebootDialog" title="<g:message code='user.instance.rebootInstance.confirmTitle' />" style="color: black; width: 350px; background: #FFFFFF;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_reboot_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.instance.rebootInstance.confirmMessage' /> </div>            
        </div>        
    </div>            
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListServer.rebootInstance()"> <g:message code='common.reboot' /> </button>
            <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListServer.closeRebootDialog()"> <g:message code='common.cancel' /></button> 
        </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="terminateDialog" title="<g:message code='user.vm.deleteVm.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.deleteVm.confirm' />  </div>            
        </div>        
         <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListServer.terminateInstance()" id="instanceDeleteButton">
                <g:message code='default.button.delete.common' />   
            </button>
            <button id="DeleteInstanceCancelButton" data-dojo-type="dijit.form.Button" onclick="ListServer.closeTerminateDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
         </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog"  id="vmActionLoader" class="customDialgue span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.serverTaskInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.serverTaskInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="ListServer.gotoInstances()"><g:message code='common.gotoVMList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>

<div data-dojo-type="dijit.Dialog" id="currentVMSnapshotDialog" title="<g:message code='user.vmSnapshot.addTitle' />"  class="customDialgue">
    <div class="span6 dijitDialogueBackground">
        <div class="row-fluid" style="display: none">
            <div class="value_dollar pull-right"><g:message code='default.valueIn' /><span id="VMSnapshotCurrencyValue"></span></div>
        </div>
        <div class="row-fluid">
            <div class="span2"><img src='images/popup-icons/vm_snapshot_icon.png'/></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="currentVMSnapshotForm">  
                    <div id="currentVMSnapshotFormPage">
                        <!--<div class="span12"></div>-->
                        <div class="row-fluid">
                            <div class="control-group field-box span12"> 
                                <label for="vmSnapshotName" class="control-label">      
                                    <g:message code='common.name' />
                                    <span class="require">*</span>
                                </label>
                                <div class="controls">
                                    <input type="text" 
                                    data-dojo-type="dijit.form.ValidationTextBox" 
                                    data-dojo-props="invalidMessage: '<g:message code='common.invalidMessage.name' />',
                                    required: 'required', placeHolder: '<g:message code='common.name.placeHolder' />', 
                                    regExp: '[a-zA-Z.0-9- ]{4,50}'" 
                                    name="Name" id="currentVMSnapshotName">
                                    <div class="form_help_icon" style="top: 0; left: -15px;">
                                        <i class="icon-info-sign" id="instanceSnapshotsNameHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'instanceSnapshotsNameHelp', showDelay: 1"><g:message code="common.help.message.VMSnapshotName"/></div>
                                    </div>
                                </div>
                            </div>
<!--                            <div class="span12 field-box control-group hide_text" id="vmListbillingTypeDiv">
                                <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/>:<span class="require">*</span></label>
                                <div class="controls">
                                    <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="billingType" id="hourlyBilling" value="hourly" onchange="AddServer.enableMonthlyCost()"/>
                                    <label for="hourlyBilling"><g:message code="common.hourly"/></label> 
                                    <input type="radio" data-dojo-type="dijit.form.RadioButton" name="billingType"  id="monthlyBilling" value="monthly" onchange="AddServer.enableMonthlyCost()"/> 
                                    <label for="monthlyBilling" class=""><g:message code="common.monthly"/></label> 
                                    <div class="form_help_icon">
                                       <i class="icon-info-sign" id="createVmBillingTypeHelp"></i>
                                       <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmBillingTypeHelp', showDelay: 1">
                                        <g:message code="user.createVM.billingType.toottip"/>
                                       </div>
                                    </div>
                                </div>
                            </div>-->
                        </div>
                    </div>                    
                </form>
            </div>                        
        </div>
        <div class="row-fluid">
            <div class="control-group span7"> 
                <button id="addCurrentVMSnapshotButton" class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListServer.createVMSnapshot()"><g:message code='common.ok' /></button>
                <button id="cancelCurrentVMSshotButon" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListServer.closeCreateVMSnapshotDialog()"><g:message code='common.cancel' /></button>
            </div>
           <div class="span5">
               <!--<span class="require">*<g:message code='common.vm.constMsg' /></span>-->
           </div>

        </div>                                                  
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="resizeInstancedialog" title="<g:message code="common.instance.resizeInstance"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span2"><img src="images/vm_resize_icon.png"></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="resizeInstanceForm"> 
                    <!--<input type="hidden" id="seletedServerId">--> 
                    <div id="resizeInstanceFormPage">
                        <div class="control-group">
                            <label for="newFlavor" class="control-label"><g:message code="common.newFlavor"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                             <div id="" class="selectOption">
                                <div id="flavorListForResize" ></div> 
                                <div class="form_help_icon" style="top: -25px; left: -15px;">
                                    <i class="icon-info-sign" id="flavorListForResizeHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorListForResizeHelp', showDelay: 1"><g:message code="common.help.message.flavorListForResize"/></div>
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
                <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListServer.resizeInstance()"><g:message code="common.ok"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListServer.closeResizeInstanceDialog()"><g:message code="common.cancel"/></button>            </div>
           <!--<div class="span5"></div>-->
        </div>
    </div>
</div>
<input type="hidden" id="imageId">
<div data-dojo-type="dijit.Dialog" id="rebuildInstanceDialog" style=" overflow: visible;" title="<g:message code="common.instance.rebuildInstance"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span2"><img src="images/vm_restore_icon.png"></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="rebuildInstanceForm"> 
                    <!--<input type="hidden" id="seletedServerId">--> 
                    <div id="rebuildInstanceFormPage">
<!--                        <div class="control-group"> 
                            <label for="imageName" class="control-label">      
                                <g:message code='common.oldInstanceName' />
                            </label>
                            <div class="controls">
                                <input type="text" 
                                data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="invalidMessage: '<g:message code='common.invalidMessage.name' />',
                                required: 'required', placeHolder: '<g:message code='common.name.placeHolder' />', 
                                regExp: '[a-zA-Z.0-9- ]{4,50}'" 
                                name="oldInstanceName" id="oldInstanceName">  
                            </div>
                        </div>-->
                        <div class="control-group"> 
                                <label for="newImageName" class="control-label">      
                                    <g:message code='common.newInstanceName' />
                                    <span class="require">*</span>
                                </label>
                                <div class="controls">
                                    <input type="text" 
                                    data-dojo-type="dijit.form.ValidationTextBox" 
                                    data-dojo-props="invalidMessage: '<g:message code='common.invalidMessage.name' />',
                                    required: 'required', placeHolder: '<g:message code='common.name.placeHolder' />', 
                                    regExp: '[a-zA-Z.0-9- ]{4,50}'" 
                                    name="newInstanceName" id="newInstanceName">  
                                </div>
                                <div class="form_help_icon" style="top:-25px; left: -15px;">
                                    <i class="icon-info-sign" id="newInstanceNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'newInstanceNameHelp', showDelay: 1"><g:message code="common.help.message.newInstanceName"/></div>
                                </div>
                            </div>
                        <div class="control-group">
                            <label for="newImage" class="control-label"><g:message code="common.newImage"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                             <div id="" class="selectOption">
                                <div id="imageListToRebuild" ></div> 
                                <div class="form_help_icon" style="top: -25px; left: -15px;">
                                    <i class="icon-info-sign" id="imageListToRebuildHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageListToRebuildHelp', showDelay: 1"><g:message code="common.help.message.imageListToRebuild"/></div>
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
                <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListServer.rebuildInstance()"><g:message code="common.ok"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListServer.closeRebuildInstanceDialog()"><g:message code="common.cancel"/></button>            </div>
           <!--<div class="span5"></div>-->
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="instanceAssociateDialog" title="<g:message code="common.user.maangeFloatingIp"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="instanceAssociateFloatingIpForm">  
                    <div id="associateFloatingIpPage"> 
                        <div class="control-group">
                            <label for="" class="control-label"><g:message code="common.ipAddress"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                    <div id="ipAddressList" class="selectOption"> 
                                        <button class="cancelbtn" style="position: absolute; left: 353px" type="button" data-dojo-type="dijit.form.Button" onclick="InstanceFloatingIpInfo.createFloatingIpDialog()"> + </button>
                                    </div>
                                    
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="ipAddressListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ipAddressListHelp', showDelay: 1"><g:message code="common.help.message.ipAddressAssociate"/></div>
                                </div>
                            </div>
                        </div> 
                        <div class="control-group">
                            <label for="" class="control-label"><g:message code="common.portAssociate"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="portsList" class="selectOption"></div>
                                <img id="associatePortLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="portsListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'portsListHelp', showDelay: 1"><g:message code="common.help.message.portAssociate"/></div>
                                </div>
                            </div>
                        </div> 
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span3 pull-right"> 
               <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="InstanceFloatingIp.associate()"><g:message code="common.associate"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="InstanceFloatingIp.cancelAssociateDialog()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="instanceDisAssociateDialog" title="<g:message code="common.user.disassociateFloatingIp"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="instanceDisassociateFloatingIpForm">  
                    <div id="disassociateFloatingIpPage"> 
                        <div class="control-group">
                            <label for="" class="control-label"><g:message code="common.portAssociate"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="disassociatePortsList" class="selectOption"></div>
                                <div class="form_help_icon" id="ipsListHelpDiv" style="top: -25px; left: -1px; display: block">
                                    <i class="icon-info-sign" id="portsListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'portsListHelp', showDelay: 1"><g:message code="common.help.message.port.disAssociate"/></div>
                                </div>
                            </div>
                        </div> 
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span3 pull-right"> 
               <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="InstanceFloatingIp.diassociateConfirm()"><g:message code="common.disassociate"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="InstanceFloatingIp.cancelDisassociateDialog()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="instanceCreateFloatingIpDialog" title="<g:message code="common.user.createFloatingIp"/>"  class="customDialgue">   
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span10"> 
                <form class="form-horizontal" id="instaceCreateFloatingIpForm" data-dojo-type="dijit.form.Form">
                    <div id="floatingIpPage">
                        <div class="control-group">
                            <label for="" class="control-label"><g:message code="common.pool"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="externalNetworksList" class="selectOption"></div>
                                <div class="form_help_icon" id= "externalNetworksListHelp" style="top: -25px; left: -1px;display: block">
                                    <i class="icon-info-sign" id="externalNetworksListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'externalNetworksListHelp', showDelay: 1"><g:message code="common.help.message.associateFloatingIp"/></div>
                                </div>
                            </div>
                        </div>     
                        <div class="control-group hide_text" id="vmFloatingIPBillingTypeDiv">
                            <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/>:<span class="require">*</span></label>
                            <div class="controls">
                                <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="listFloatingIPBillingType" id="hourlyVMFloatingIPBilling" value="hourly" onchange="InstanceFloatingIpInfo.enableMonthlyCost()"/>
                                <label for="hourlyVMFloatingIPBilling" style="float: left ; display: inline ;"><g:message code="common.hourly"/></label> 
                                <input type="radio" data-dojo-type="dijit.form.RadioButton" name="listFloatingIPBillingType"  id="monthlyVMFloatingIPBilling" value="monthly" onchange="InstanceFloatingIpInfo.enableMonthlyCost()"/> 
                                <label style="float: left ; display: inline ;" for="monthlyVMFloatingIPBilling" class=""><g:message code="common.monthly"/></label> 
                                <div class="form_help_icon" style="left: 0px;">
                                    <i class="icon-info-sign" id="vmFloatingIPBillingTypeHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'vmFloatingIPBillingTypeHelp', showDelay: 1">
                                        <g:message code="user.floatingIP.billingType.toottip"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="control-group" id="vmFloatingIPCostInfo">
                            <label for="" class="control-label"><g:message code="common.cost"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div><span id="vmFloatingIPCost" class="require"></span></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;display: block">
                                    <i class="icon-info-sign" id="vmFloatingIPCostHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'vmFloatingIPCostHelp', showDelay: 1"><g:message code="common.help.message.associateFloatingIpCost"/></div>
                                </div>
                            </div>
                        </div> 
                    </div> 
                </form> 
            </div>
        </div> 
    
        <div class="control-group span3 pull-right"> 
                <div id="addFloatingIpButton">
                    <button type="button"  onclick="InstanceFloatingIpInfo.create()" class="defaultbtn" data-dojo-type="dijit.form.Button"><g:message code="common.create"/></button>
                    <button   class="cancelbtn"  type="button" data-dojo-type="dijit.form.Button" onclick=" InstanceFloatingIpInfo.cancelFloatingIp()"><g:message code="common.cancel"/></button>
                </div>
            <div class="span4" id="floatingIpCreateLoader" style="display: none"> 
                <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="instanceDisassociateFloatingIpAlert" title="<g:message code='user.disassociateFloatingIp.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row-fluid">
<!--        <div class="span2"><img src='images/vm_detach_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><g:message code='user.disassociateFloatingIp.message' />  </div>            
        </div>        
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="InstanceFloatingIp.disassociate()">
                <g:message code='common.disassociate' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="InstanceFloatingIp.cancelDisassociate()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog"  id="instanceFloatingIpLoader" class="customDialgue span6">
    <div class="row-fluid">
        <div class="span9">
            <div class="span12"><p><g:message code='common.releaseFloatingIp1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.releaseFloatingIp2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="InstanceFloatingIpInfo.closeDialog()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
<input type="hidden" id="instanceId">
<div data-dojo-type="dijit.Dialog" id="vmListmonitoringDisableAlert" title="<g:message code='user.monitoringDisableAlert.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
        <div class="row-fluid">
    <!--        <div class="span2"><img src='images/vm_detach_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><g:message code='user.monitoringDisableAlert.message' />  </div>            
            </div>        
            <div class="row-fluid">
                <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="InstanceMonitoring.diableDevice()">
                    <g:message code='common.disable' />   
                </button>
                <button data-dojo-type="dijit.form.Button" onclick="InstanceMonitoring.cancelDiableDevice()" class="cancelbtn">
                    <g:message code='common.cancel' />
                </button> 
            </div>
        </div>                        
</div>



