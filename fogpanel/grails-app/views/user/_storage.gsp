<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/cloud/"><g:message code='menu.user.cloud'/></a></li>
    <li>/<li>
    <li><g:message code='menu.user.cloud.storage'/></li>
  </ul>
</div>
</div>

<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
       <div class="span4 stat">
            <div class="data">
                <span class="number" id="userTotalStorage"></span>
                <g:message code="common.storage.data"/>
            </div>
           
        </div>
        <div class="span4 stat">
            <div class="data">
              <span class="number" id="userAttachedStorage"></span>
                <g:message code="common.storage.attached"/>
            </div>
            
        </div>
        <div class="span4 stat">
            <div class="data">
              <span class="number" id="userDetachedStorage"></span>
               <g:message code="common.storage.detached"/>
            </div>            
        </div>    
    </div>
</div>
</div>
<div class="row-fluid"><div class="span1"></div></div>
<div>
  <div class="table-wrapper products-table">
    <div class="row-fluid filter-block">
    <div class="alert alert-danger errorMessage" id="storageLimitReached" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.reachedLimit"/>
    </div>  
    <div class="pull-right" id="addDiskButton">
        <a class="btn-flat success new-product" title='<g:message code="common.addStorage"/>' href="#/user/cloud/addStorage"><g:message code="common.addStorage"/></a>      
    </div>
    </div>
    <div class="row-fluid" style="text-align: right;">          
        <span>              
            <g:message code="common.warning"/>:          
        </span>                
            <p class="require" style="float: right;"><g:message code="common.vmSnapshotWarning"/></p>                            
    </div>
    <div class="row-fluid">
       	<div id="storageListGrid"></div>
        <div class="alert alert-info hide" id="noStorageMessageBox" style="display: none">
          <i class="icon-exclamation-sign"></i> 
        <g:message code="user.storage.noStorage"/>
      </div>
    </div>
  </div>
</div>

<div data-dojo-type="dijit.Dialog" id="storageDeleteDisk" title="<g:message code="common.confirm"/>" class="customDialgue"> 
  <div class="span4 dijitDialogueBackground">
    <p><g:message code="user.storage.deleteConfirm"/></p>
    <div class="row-fluid offset1">
      <img id="volumeLoaderDelete" src="${resource(dir: 'images')}/loader.gif" alt="loading" height="42" width="42" style="display: none">
      <button type="button" class="defaultbtn" id="storageDeleteAddDisk" data-dojo-type="dijit.form.Button" onclick="StorageInfo.deleteStorage()"><g:message code="common.yes"/></button>
      <button type="button" class="cancelbtn" id="storageDeleteCancelDisk" data-dojo-type="dijit.form.Button" onclick="StorageInfo.cancelDeleteStorage()"><g:message code="common.no"/></button>
    </div>
  </div>  
</div>

<div data-dojo-type="dijit.Dialog" id="storageDetachDisk" title="Confirmation" class="customDialgue">
  <div class="span4 dijitDialogueBackground">
    <p><g:message code="user.storage.detachDisk"/></p>
    <div class="row-fluid offset1">      
      <button id="addDetachDisk" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="StorageInfo.DetachStorage()"><g:message code="common.yes"/></button>
      <button id="cancelDetachDisk" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="StorageInfo.cancelDetachStorage()"><g:message code="common.no"/></button>              
    </div>
  </div>        
</div>

<div data-dojo-type="dijit.Dialog"  id="storageConfirmSnapPage"  class="span4" title="<g:message code="common.confirm"/>">
  <p><g:message code="user.storage.snaphot"/></p>
  <div class="row-fluid">
    <table class="offset1">
      <tr>
        <td><label><g:message code="common.name"/>:</label></td>
        <td><input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="storageManualSnapshotName"></td>
      </tr>
    </table>              
  </div>
  <div class="row-fluid offset1">            
    <button type="button"  data-dojo-type="dijit.form.Button" onclick="StorageInfo.takeSnapShot()"><g:message code="common.yes"/></button>
    <button type="button"  data-dojo-type="dijit.form.Button" onclick="StorageInfo.cancelSnapshot()"><g:message code="common.no"/></button>
  </div>
</div> 
<div data-dojo-type="dijit.Dialog" id="resizeDiskDialogue" title="<g:message code="user.storage.resizeVolume"/>" class="customDialgue">
  <div class="span5 dijitDialogueBackground">   
    <input type="hidden" id="resizeVolumeId">
    <div class="form-horizontal">      
      <div class="control-group" style="margin-top: 25px"> 
        <label class="control-label"><g:message code="user.storage.selectDisk"/>:<span class="require">*</span></label>
        <div class="controls">
          <div id="resizeStorageCollection">
            <div id="resizeStorage" class="selectOption"></div>            
          </div> 
          <div id="resizeStorageSliderCollection" style="display: none">
            <div id="resizeDiskSlider"></div>
            <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                   data-dojo-props="constraints:{min:1,max:100,pattern:'#'},
                   timeoutChangeRate: '0.2', invalidMessage: '<g:message code="user.storage.size.invalid"/>'"
                   id="storageResizeSpinner" class="customSpinner">  
          </div>
          <input type='hidden' id='diskUnit'>
          <input type='hidden' id='diskCost'>
          <span id="resizePlanSize"></span><span id="resizePlanCost" style="margin-left: 60px"></span>
          <img id="resizeStorageLoader" style="display: none; width: 12%" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="100">
        </div>       
      </div> 
      <div class="row-fluid" class="spacePadding" style="padding: 0 0 10px 0">          
        <button id="resizeButton" class="offset5 defaultbtn" style="" type="button" data-dojo-type="dijit.form.Button" onclick="StorageInfo.resizeDisk()"><g:message code="common.ok"/></button>
        <img id="resizeDiskLoader" class="offset5" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none;"/>
        <button id="resizeCancelButton" style="display: none" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="StorageInfo.cancelResize()"><g:message code="common.cancel"/></button>
      </div>
    </div>
  </div>
</div>

<div data-dojo-type="dijit.Dialog" id="storageAttachDisk" title="<g:message code="user.storage.attach.title"/>" class="customDialgue">  
  <div class="span4 dijitDialogueBackground">
    <p><g:message code="user.storage.attach.title"/>.</p>
    <div class="form-horizontal">
      <div class="control-group"> 
        <label class="control-label">              
          <g:message code="user.vm.selectVM"/>:
          <span class="require">*</span>
        </label>
        <div class="controls">
          <div id="strogeInstanceListCollection">
            <div id="strogeInstanceList"></div>                 
          </div>          
          <img id="storageVmLoader" style="display: none; width: 12%" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="100">
        </div>
      </div> 
    <div class="control-group">
        <label class="control-label">              
          <g:message code="common.warning"/>:          
        </label>
        <div class="controls">
            <span class="require"><g:message code="common.vmSnapshotWarning"/></span>
        </div>
    </div>
    </div>
    <div class="row-fluid">          
      <button id="attachDisk" class="offset4 defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="StorageInfo.attachDisk()"><g:message code="common.ok"/></button>
      <img id="diskAttachLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
      <button id="cancelAttach" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="StorageInfo.cancelAttachDisk()"><g:message code="common.cancel"/></button>
    </div>
  </div>                     
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="resizeVolumeLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>
