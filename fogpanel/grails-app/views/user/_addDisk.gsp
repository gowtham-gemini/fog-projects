<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/cloud/"><g:message code="menu.user.cloud"/></a></li>
    <li>/<li>
    <li><a href="#/user/cloud/storage"><g:message code="menu.admin.services.storage"/></a></li>
    <li>/<li>
    <li><g:message code="user.storage.addStoage"/></li>
  </ul>
</div>
</div>

<div class="new-user">
  <div class="row-fluid header">
      
      <!--<h3>Add Disk</h3>-->
  </div>
  <div class="row-fluid form-wrapper">  
    <!-- left column -->
	<div class="span3 with-sidebar createvm-banner">
		<img src="${resource(dir: 'images')}/createvm_banner_logo.png" height="151" width="238">
                <h2 class="alphaStyle"><g:message code="user.createVM.customDisk.label"/></h2>
		<h2 class="alphaStyle"><g:message code="user.createVM.diskOffer.label"/></h2>
	</div>
    <div class="span6 with-sidebar">
      <div class="container">
        <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="storageAddDiskForm">
              <!--<div class="span12 field-box"></div>-->          
                <div class="span12 field-box">
                  <label for="diskNme" class="control-label"><g:message code="common.name"/><span class="require">*</span></label>
                  <div class="controls elements">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                           data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                           required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', regExp: '[a-zA-Z.0-9- ]{4,200}', 
                           propercase: true" name="diskName" id="addStorageName">
                    <div class="form_help_icon" style="top: 12px; left: 0%;">
                      <i class="icon-info-sign" id="addDiskNameHelp"></i>
                      <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'addDiskNameHelp', showDelay: 1">
                       <g:message code="user.storage.addStoage.name.tooltip"/>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="span12 field-box">
                	<label for="diskNme" class="control-label">                 	
                 		<g:message code="common.instance"/>:<span class="require">*</span>
               		</label>
                  <div class="controls updatable elements">
                      <div id="storageInstance" class="selectOption"></div>
                      <div class="form_help_icon"  style="top: -12px; left: 0%;">
                      <i class="icon-info-sign" id="addDiskVmHelp"></i>
                      <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'addDiskVmHelp', showDelay: 1">
                        <g:message code="user.storage.addStoage.attach.tooltip"/>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="span12 field-box">
               <label for="storageCustomDisk" class="control-label"><g:message code="user.createVM.customDisk.label"/></label>
               <div class="controls updatable elements">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                    data-dojo-props ="disabled: true" id="storageCustomDisk" onchange="AddStorageInfo.showCustomDisk(this)">
                 <div class="form_help_icon" style="top: 0; left: 0%;">
                      <i class="icon-info-sign" id="addDiskCustomHelp"></i>
                      <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'addDiskCustomHelp', showDelay: 1">
                       <g:message code="user.storage.addStoage.custom.tooltip"/> 
                      </div>
                    </div>
               </div>
                </div>
                <div class="span12 field-box" id="storageDiskOfferContent">
                   <label for="diskNme" class="control-label"><g:message code="user.createVM.diskOffer.label"/><span class="require">*</span></label>
                    <div id="diskListContainer" class="controls updatable elements">
                      <div id="storageDiskOffer" class="selectOption"></div>                    
                    </div>
                  <span id="storageDiskName"></span><br>  
                  <img id="storageLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                  <div class="form_help_icon" style="top: -41px; left: 0%;">
                      <i class="icon-info-sign" id="addDiskOfferHelp"></i>
                      <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'addDiskOfferHelp', showDelay: 1">
                        <g:message code="user.storage.addStoage.size.tooltip"/>
                      </div>
                    </div>
                </div>
                
          <div class="span12 field-box non_updatable" id="storageDiskSizeContent">
                    <label for="instanceDiskSize" class="control-label">  
                  <g:message code="user.createVM.diskSize.label"/>:
                </label>
                <div class="controls customDisk customDisk">
              <div id="storageCustomDiskSizeSlider"></div>
              <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                     data-dojo-props="constraints:{min:1,max:100,pattern:'#'},
                     timeoutChangeRate: '0.2', invalidMessage: '<g:message code="user.storage.size.invalid"/>'"
                     id="storageDiskSizeSpinner" class="customSpinner" 
                     onchange="AddStorageInfo.setSliderSize(this)" 
                     onKeyDown="AddStorageInfo.setSliderSize(this)" 
                     onKeyPress="AddStorageInfo.setSliderSize(this)"
                     onMouseDown="AddStorageInfo.setSliderSize(this)" style="margin-right: 17px">   
              <div class="form_help_icon" style="left: 34%">
                      <i class="icon-info-sign" id="addDiskSizeHelp"></i>
                      <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'addDiskSizeHelp', showDelay: 1">
                        <g:message code="user.storage.addStoage.customSize.tooltip"/>
                      </div>
                    </div>
                </div>
                     
             </div>
             <div class="field-box" id="add-disk-billingTypeDiv"> 
                <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/></label>
                <div class="controls radio_button">
                    <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="add-disk-billingType" id="add-disk-hourlyBilling" value="hourly" onchange="AddStorageInfo.enableMonthlyCost()" />
                  <label for="add-disk-hourlyBilling"><g:message code="common.hourly"/></label> 
                  <input type="radio" data-dojo-type="dijit.form.RadioButton" name="add-disk-billingType"  id="add-disk-monthlyBilling" value="monthly" onchange="AddStorageInfo.enableMonthlyCost()"/> 
                  <label for="add-disk-monthlyBilling" class=""><g:message code="common.monthly"/></label> 
                </div>
            </div>
            <div class="control-group field-box">
            <label class="control-label">              
                <g:message code="common.warning"/>:          
            </label>
            <div class="controls">
                <span class="require"><g:message code="common.vmSnapshotWarning"/></span>
            </div>
        </div>        
            <div class="span12 field-box">
              <div class="span6">
                  <div id="addStorageLoader"></div>
                  <div class="" id="addStorageRequireField" style="display: none">
                      <span class="require"><g:message code="user.createVM.required"/>  /  <g:message code="user.noDisk.info"/></span>
                  </div>
              </div>
            <div class="span3 pull-right">            
                  <button id="volumeAdd" type="button" data-dojo-type="dijit.form.Button"
                          onclick="AddStorageInfo.createVolume()" class="defaultbtn" data-dojo-props="disabled: true"
                          title="Create Storage"><g:message code="common.addStorage"/></button>              
            </div>
          </div>
              
            </form>
        </div>
    </div> 
     <div class="span3">
       <div class="row-fluid">       
           <div class="span5"><a onclick="RateCardInfo.showRateCardDialogue();RateCardInfo.populateValues();"><g:message code='common.rateCard' /></a><g:render template="rateCard" /></div>
          <div class="value_dollar create_vm_cost span6"><g:message code="default.valueIn"/><span id="addDiskCurrecy" style="color: #374859; float: none"></span></div>   
      </div>
       <div class="row-fluid" id="addDiskInfo">   
         <div class="span12">
           <h3><g:message code="common.storageInfo"/></h3>
         </div> 
         <div class="span12">
           <label><g:message code="common.cost"/></label>
           <span id="storageDiskCost"></span> <span>  /<g:message code="common.gb"/>/<g:message code="common.hr"/></span>
         </div>    
         <div class="span12">
           <label><g:message code="common.size"/></label>
           <span id="storageDiskSize"></span>
         </div> 
           <div class="span12" id="addDiskMonthly" style="display: none;">
           <label><g:message code="common.monthly"/></label>
           <span id="storageMonthlyCost"></span>
         </div> 
       </div>
     </div>
  </div>   
</div>
<!--</div>-->
