<%@ page contentType="text/html;charset=UTF-8" %>
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

<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
       <div class="span3 stat">
            <div class="data">
                <span class="number span12" id="userTotalVM"></span>                
                <g:message code="stat.totalVM"/>
            </div>
           
        </div>
        <div class="span3 stat">
            <div class="data">
              <span class="number span12" id="userRunningVM"></span>
                <g:message code="stat.runningVM"/>
            </div>
            
        </div>
        <div class="span3 stat">
            <div class="data">
              <span class="number span12" id="userStoppedVM"></span>
               <g:message code="stat.stoppedVM"/>
            </div>            
        </div>
      <div class="span3 stat last">
          <div class="customData span12" style="display: none">
          <div id="userZoneList"></div>
        </div>
          <div class="row-fluid">
            <form data-dojo-type="dijit.form.Form" id="userVmActionForm" class="customForm overflow-text">
            <div class="span4">
              <input type="radio" name="userVmInfo" id="userVmRadio" 
                     data-dojo-type="dijit/form/RadioButton"  checked value="All" onclick="UserInstances.showInstanceByRadio(this)">          
              <label for="userVmRadio" class="overflowLabel" style="width:50px"><g:message code="common.vmStatus.all"/></label>      
          
            </div>
        <div class="span4">
          <input type="radio" name="userVmInfo" data-dojo-type="dijit/form/RadioButton"  
                 id="runningUserRadio" value="Running" onclick="UserInstances.showInstanceByRadio(this)">           
          <label for="runningUserRadio" class="overflowLabel" style="width:50px"><g:message code="common.vmStatus.running"/></label> 
            
           
        </div>
        <div class="span4">  
          <input type="radio" name="userVmInfo" data-dojo-type="dijit/form/RadioButton"  
                 id="stoppedUserRadio" value="Stopped" onclick="UserInstances.showInstanceByRadio(this)"> 
          <label for="stoppedUserRadio" class="overflowLabel" style="width:50px"><g:message code="common.vmStatus.stopped"/></label>           
        </div> 
            </form>
          </div>
        </div>
    </div>
</div>
</div>
<div class="row-fluid"><div class="span1"></div></div>
<div>
  <div class="table-wrapper products-table">
    <div class="filter-block">
        <div class="row-fluid">
            <div class="alert alert-danger errorMessage overflowLabel" id="vmLimitReached" style="display: none">
                <i class="icon-exclamation-sign"></i> 
                <g:message code="common.reachedLimit"/>
            </div>
        </div>
        <div class="row-fluid">
            <div class="pull-right">
                <a class="btn-flat success new-product" onclick="UserInstances.init();UserInstances.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>
                <a id="createVmButtom" class="btn-flat success new-product" onClick="UserInstances.getCardStatus()" title="<g:message code='common.vm.create'/>"><g:message code="common.vm.create"/></a>        
            </div>        
        </div>              
    </div>
    <div class="row-fluid">
      <div id="instanceGrid"></div>
      <div class="alert alert-info hide overflowLabel" id="noVmMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noInstance"/>
      </div>
    </div>
  </div>
</div>

<div data-dojo-type="dijit.Dialog" id="stopDialog" title="<g:message code='user.vm.stopVm.title' />" style="color: black; width: 350px; background: #FFFFFF">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_shutdown_icon.png'/></div>
        <div class="span10">
             <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form">                
                 <div class="span12"><span><g:message code='user.vm.stopVm.confirm' /></span></div>                 
                 <div class="span12 field-box control-group" style="margin-bottom: 0 !important;margin-left: 0;">                     
                     <div class="span5"><label for="stopInstanceAgreement" style="font-size: 13px;"><g:message code='common.forceshutdown'/></label> </div>
                     <div class="span1"><input  type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="stopInstanceAgreement" name="agreement"/></div>
                </div>  
            </form>
        </div>
    </div>
    <div class="row-fluid">           
        <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="UserInstances.stopInstance()" id="instanceStopButton"> <g:message code='common.ok' /> </button>
        <button id="instanceCancelButton" data-dojo-type="dijit.form.Button" onclick="UserInstances.closeStopDialog()" class="cancelbtn"> <g:message code='common.cancel' /></button> 
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
        <button type="button" data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="UserInstances.startInstance()" id="instanceStartButton">   
            <g:message code='common.ok' />    
        </button>
        <button class="cancelbtn" id="startInstanceCancelButton" data-dojo-type="dijit.form.Button" onclick="UserInstances.closeStartDialog()">
            <g:message code='common.cancel' />
        </button> 
    </div>        
</div>
<div data-dojo-type="dijit.Dialog" id="rebootDialog" title="<g:message code='user.vm.rebootVm.title' />" style="color: black; width: 350px; background: #FFFFFF;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_reboot_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.rebootVm.confirm' /> </div>            
        </div>        
    </div>            
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="UserInstances.rebootInstance()" id="instanceRebootButton"> <g:message code='common.reboot' /> </button>
            <button class="cancelbtn" id="rebootInstanceCancelButton" data-dojo-type="dijit.form.Button" onclick="UserInstances.closeRebootDialog()"> <g:message code='common.cancel' /></button> 
        </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="deleteDialog" title="<g:message code='user.vm.deleteVm.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.deleteVm.confirm' />  </div>            
        </div>        
         <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="UserInstances.deleteInstance()" id="instanceDeleteButton">
                <g:message code='default.button.delete.common' />   
            </button>
            <button id="DeleteInstanceCancelButton" data-dojo-type="dijit.form.Button" onclick="UserInstances.closeDeleteDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
         </div>
    </div>                        
</div>
          <div data-dojo-type="dijit.Dialog" id="attachIso" title="<g:message code='user.storage.detachVm' />" style="color: black; width: 600px; height: 500px">
            <div style="margin-left: 100px">
              <label for="instanceIso">iso:</label>
              <select id="instanceIso"></select>
              <button data-dojo-type= "dijit.form.Button" 
              onclick="UserInstances.attachIso()" id="">
                <g:message code='user.storage.attachVm' />
              </button>
              <button data-dojo-type= "dijit.form.Button" 
              onclick="UserInstances.detachIso()" id="">
                <g:message code='user.storage.detachVm' />
              </button>
            </div>
          </div>
<div data-dojo-type="dijit.Dialog" id="vmRestoreDialog" title="<g:message code='user.vm.restore.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <input type="hidden" id="currentVmID"/>
        <div class="span2"><img src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_restore_icon.png"/></div>
        <div class="span9">
            <div class="span12"><g:message code='user.vm.restore.confirm' />  </div>            
        </div>                 
    </div>            
    <div class="row-fluid">
        <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="UserInstances.restoreInstance()">
            <g:message code='common.restore' />   
        </button>
        <button data-dojo-type="dijit.form.Button" onclick="UserInstances.closeRestoreVMDialog()" class="cancelbtn">
            <g:message code='common.cancel' />
        </button> 
    </div>
</div>
