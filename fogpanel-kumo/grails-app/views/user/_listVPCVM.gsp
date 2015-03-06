<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.yourVPC"/></a></li>
        <li>/</li>
        <li><g:message code="common.instance"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid filter-block">
    <div class="pull-right">
        <input type="hidden" id="currentGeneralTierVmID"/>
        <a class="btn-flat success new-product" onclick="ListAllVPCVMInfo.showLaunchVMDialog()"><g:message code="user.cloud.launchVM"/></a>         
    </div>
    <div class="row-fluid"><div class="span12"></div></div>
    <div class="row-fluid"><div class="span12"></div></div>    
    <div class="row-fluid">
      <div id="vpcGeneralVMList"></div>
    </div>
    <div class="row-fluid">
      <div class="alert alert-info hide" id="noGeneralVPCMVMsg" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noVMForNetworkIP"/>
      </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="generalStartTierVMDialog" title="<g:message code='user.vm.startVm' />" style="color: black; width: 350px;" class="customDialgue">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_play_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.startVm.confirm' /></div>            
        </div>
    </div>
    <div class="row-fluid">
        <button type="button" data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="ListAllVPCVMInfo.startInstance()">   
            <g:message code='common.ok' />    
        </button>
        <button class="cancelbtn"  data-dojo-type="dijit.form.Button" onclick="ListAllVPCVMInfo.closeStartDialog()">
            <g:message code='common.cancel' />
        </button> 
    </div>        
</div>

<div data-dojo-type="dijit.Dialog" id="stopGenereralTierDialog" title="<g:message code='user.vm.stopVm.title' />" style="color: black; width: 350px; background: #FFFFFF">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_shutdown_icon.png'/></div>
        <div class="span10">
             <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form">                
                 <div class="span12"><span><g:message code='user.vm.stopVm.confirm' /></span></div>                 
                 <div class="span12 field-box control-group" style="margin-bottom: 0 !important;margin-left: 0;">                     
                     <div class="span5"><label for="stopGeneralTierInstanceAgreement" style="font-size: 13px;"><g:message code='common.forceshutdown'/></label> </div>
                     <div class="span1"><input  type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="stopGeneralTierInstanceAgreement" name="agreement"/></div>
                </div>  
            </form>
        </div>
    </div>
    <div class="row-fluid">           
        <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListAllVPCVMInfo.stopInstance()" id=""> <g:message code='common.ok' /> </button>
        <button data-dojo-type="dijit.form.Button" onclick="ListAllVPCVMInfo.closeStopDialog()" class="cancelbtn"> <g:message code='common.cancel' /></button> 
    </div>                    
</div> 

<div data-dojo-type="dijit.Dialog" id="rebootGeneralVMTierDialog" title="<g:message code='user.vm.rebootVm.title' />" style="color: black; width: 350px; background: #FFFFFF;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_reboot_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.rebootVm.confirm' /> </div>            
        </div>        
    </div>            
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListAllVPCVMInfo.rebootInstance()"> <g:message code='common.reboot' /> </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="ListAllVPCVMInfo.closeRebootDialog()"> <g:message code='common.cancel' /></button> 
        </div>  
</div>

<div data-dojo-type="dijit.Dialog" id="vmGeneralTierRestoreDialog" title="<g:message code='user.vm.restore.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <input type="hidden" id="currentVmID"/>
        <div class="span2"><img src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_restore_icon.png"/></div>
        <div class="span9">
            <div class="span12"><g:message code='user.vm.restore.confirm' />  </div>            
        </div>                 
    </div>            
    <div class="row-fluid">
        <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListAllVPCVMInfo.restoreInstance()">
            <g:message code='common.restore' />   
        </button>
        <button data-dojo-type="dijit.form.Button" onclick="ListAllVPCVMInfo.closeRestoreVMDialog()" class="cancelbtn">
            <g:message code='common.cancel' />
        </button> 
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="deleteGeneralTierVMDialog" title="<g:message code='user.vm.deleteVm.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.deleteVm.confirm' />  </div>            
        </div>        
         <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ListAllVPCVMInfo.deleteInstance()" id="">
                <g:message code='default.button.delete.common' />   
            </button>
            <button id="" data-dojo-type="dijit.form.Button" onclick="ListAllVPCVMInfo.closeDeleteDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
         </div>
    </div>                        
</div>


<div data-dojo-type="dijit.Dialog"  id="generalVMConfirmDialog" title="<g:message code="common.addTier"/>" class="customDialog span6">
    <div class="row-fluid">   
        <form id="generalConfirmVMForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
            <div class="span9">
                <div class="form-horizontal">
                <div class="row-fluid">                    
                    <div class="control-group">
                         <label class="control-label">
                             <g:message code="common.zone"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                          <div id="generalVMZoneList"></div>
                          <img id="generalVMZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                    <div class="control-group">
                         <label class="control-label">
                             <g:message code="common.vpc"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                         <div id="generalVMVPCList"></div>
                         <img id="generalVMVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                    <div class="control-group">
                         <label class="control-label">
                             <g:message code="common.tier"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls">
                         <div id="generalVMTierList"></div>
                         <img id="generalVMTierLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                </div>
            </div> 
        </div>
        </form>
    </div>    
    <div class="row-fluid">
        <div class="span8"><span style="display: none" class="offset3 require" id="addVMErrorMessage"><g:message code="user.createVM.required"/></span></div>
        <div class="span4">        
            <div class="pull-right">            
                <button data-dojo-type="dijit.form.Button" onclick="ListAllVPCVMInfo.addVM();" class="primarybtn">
                    <g:message code="common.ok"/>
                </button>
                <button data-dojo-type="dijit.form.Button" onclick="ListAllVPCVMInfo.cancelVM();" class="cancelbtn">
                    <g:message code="common.cancel"/>
                </button>
            </div>
         </div>
     </div>
</div>
