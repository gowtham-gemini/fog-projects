<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>    
    <li><a href="#/user/home" id="createVMHomeMenuName"><i class="icon-home"></i></a></li>     
    <li>/</li>
    <li><a href="#/user/cloud/" id="createVMCloudMenuName"><g:message code="menu.admin.cloud"/></a></li>
    <li>/<li>
    <li><a href="#/user/cloud/userInstancePage" id="vmBreadcrumb"><g:message code="menu.user.cloud.instance"/></a></li>
    <li>/<li>
    <li id="createVMtitle1"><g:message code="user.createVM"/></li>
    <li id="seperateSymboll"></li>
    <li id="createVMTitle2"></li>    
    <li id="seperateSymbol2"></li>
    <li id="createVMTitle3"></li>
    <li id="seperateSymbol3"></li>
    <li id="vmName" style="line-height: 1.5em; font-weight: 100"></li>
  </ul>
</div>
</div>

<div  class="new-user createVm"> 
  <div class="row-fluid header createVm">
    <!--<h6>Create VM</h6>-->    
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
	<div class="span3 createvm-banner">
		<img src="${resource(dir: 'images')}/createvm_server_logo.png" height="151" width="238">
                <h2 class="alphaStyle overflowLabel"><g:message code="user.createVM"/></h2>
		<h2 class="alphaStyle overflowLabel"><g:message code="common.customDisk"/></h2>
		<h2 class="alphaStyle overflowLabel"><g:message code="common.template"/></h2>
		<h2 class="alphaStyle overflowLabel"><g:message code="common.firewall"/></h2>
	</div>
    <div class="span6 createvm-form with-sidebar" id="createVMPage">
      <div class="container">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="createVMForm"> 
              <input type="hidden" id="computingOfferCost">
              <input type="hidden" id="diskOfferCost">
              <input type="hidden" id="templateCost">    
              <input type="hidden" id="currentTemplate"> 
              <input type="hidden" id="createVMcurrentZoneId">        
              <input type="hidden" id="currentTierId">        
              
              <div class="span12 field-box control-group">
                  <label for="instanceName" class="control-label">
                      <g:message code="user.createVM.dislayName"/><span class="require">*</span></label>
                  <div class="controls elements">                       
                      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                              data-dojo-props="required:'true',
                              invalidMessage:'<g:message code="user.createVM.diskplayName.invalidMessage"/>',
                              regExp: '[a-zA-Z0-9-.]{1,63}',
                              placeHolder: '<g:message code="user.createVM.diskplayName.placeHolder"/>',
                              missingMessage:'<g:message code="user.createVM.diskplayName.invalidMessage"/>',
                              promptMessage: '<g:message code="user.createVM.diskplayName.promptMessage"/>'"  
                              id="instanceName" name="networkCidr" class="vmhostname">
                      <div class="form_help_icon">
                          <i class="icon-info-sign" id="createVmHostNameHelp"></i>
                          <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmHostNameHelp', showDelay: 1"><g:message code="user.createVM.diskplayName.toottip"/></div>
                     </div>            
                  </div>
              </div>
              <div class="span12 field-box control-group">
                  <label for="instanceZone" class="control-label"><g:message code="user.createVM.zone.label"/>:<span class="require">*</span></label>
                  <div class="controls updatable elements">
                      <div id="instanceZone" class="selectOption"></div>
                      <div class="form_help_icon"><i class="icon-info-sign" id="createVmHostZoneHelp"></i>
                          <div data-dojo-type="dijit.Tooltip"data-dojo-props="connectId:'createVmHostZoneHelp', showDelay: 1"><g:message code="user.createVM.zone.toottip"/>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="span12 field-box control-group" id="storageTypeDiv">
                  <label for="instanceZone" class="control-label"><g:message code="user.createVM.storageType.label"/>:<span class="require">*</span></label>
                  <div class="controls">
                      <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton" onclick="CreateVMInfo.resetTemplate()" name="storageType" id="sharedStorageType" value="Shared"/>
                      <label for="sharedStorageType"><g:message code="common.shared"/></label> 
                      <input type="radio" data-dojo-type="dijit.form.RadioButton" name="storageType" onclick="CreateVMInfo.resetTemplate()"  id="localStorageType" value="Local"/> 
                      <label for="localStorageType" class=""><g:message code="common.local"/></label>  
                  </div>
              </div>
            <div class="span12 field-box control-group" id="vmTemplateNameInfo">
                  <label for="userTempSelect" class="control-label"><g:message code="user.createVM.template.label"/>:<span class="require">*</span></label>
                  <div class="controls updatable elements">
                      <div id="userTempSelect" class="updatable selectOption"></div>
                      <img id="vmTempLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      <div class="form_help_icon">
                          <i class="icon-info-sign" id="createVmHostTempHelp"></i>
                          <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmHostTempHelp', showDelay: 1"><g:message code="user.createVM.template.toottip" /></div>
                      </div>
                  </div> 
                      <span id="vmTemplateName" class="template_text"></span>      
              </div>
            <div class="span12 field-box control-group">
                <label for="instanceComputingOffer" class="control-label"><g:message code="user.createVM.ComputingOffer.label"/>:<span class="require">*</span></label>
                <div class="controls updatable elements">
                    <div id="instanceComputingOffer" class="selectOption"></div>
                    <img id="vmOfferingLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                    <div class="form_help_icon">
                        <i class="icon-info-sign" id="createVmOfferHelp"></i>
                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmOfferHelp', showDelay: 1"> <g:message code="user.createVM.computingOffer.toottip"/></div>
                    </div>
                    <span id="instanceComputName" class="offset2"></span>          
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="customCheckBox" class="control-label"><g:message code="user.createVM.customDisk.label"/>:</label>
                <div class="controls">
                    <input type="checkbox" data-dojo-type="dijit.form.CheckBox"  onchange="CreateVMInfo.showCustomDisk(this)" id="customCheckBox" data-dojo-props ="disabled: true">
                    <div class="form_help_icon" style="top: 5px;">
                        <i class="icon-info-sign" id="createVmCustomHelp"></i>
                        <div data-dojo-type="dijit.Tooltip"  data-dojo-props="connectId:'createVmCustomHelp', showDelay: 1"><g:message code="user.createVM.customDisk.toottip"/></div>
                    </div>
                </div>
            </div>                       
            <div class="span12 field-box control-group" id="storageDiskOfferContent">
                <label class="control-label"><g:message code="user.createVM.diskOffer.label"/>:</label>
                <div class="controls updatable elements">
                <div id="instanceDiskSlider" class="updatable selectOption"></div>
                <img id="vmDiskLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                 <div class="form_help_icon">
                   <i class="icon-info-sign" id="createVmDiskHelp"></i>
                   <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmDiskHelp', showDelay: 1">
                    <g:message code="user.createVM.diskOffer.toottip"/>
                   </div>
                 </div>
            </div> 
            <span id="instanceDiskName" class=""></span>
            </div>
            <div class="span12 field-box non_updatable control-group" id="vmDiskSize">
                <label for="instanceDiskSize" class="control-label"><g:message code="user.createVM.diskSize.label"/>:</label>
                <div class="controls"> 
                    <div id="instanceCustomDiskSizeSlider" class="span8"></div> 
                    <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                    data-dojo-props="invalidMessage: 'invalid size',
                    placeHolder: 'Enter size'"name="diskName" id="storageDiskSizeWidget"
                    class="span4 customSpinner" 
                    onchange="CreateVMInfo.setSliderSize(this)" 
                    onKeyDown="CreateVMInfo.setSliderSize(this)" 
                    onKeyPress="CreateVMInfo.setSliderSize(this)"
                    onMouseDown="CreateVMInfo.setSliderSize(this)"> 
                    <div class="form_help_icon" style="left: 34%">
                        <i class="icon-info-sign" id="createVmDiskSizeHelp"></i>
                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmDiskSizeHelp', showDelay: 1">
                    <g:message code="user.createVM.diskSize.toottip"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="span12 field-box control-group" id="createVMFirewallDiv">
                <label class="control-label"><g:message code="user.createVM.firewall.label"/>:<span class="require">*</span></label>
                <div class="controls updatable elements">                    
                    <div id="instanceSecurityGroupList" class="selectOption"></div>
                    <img id="vmFirewalLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                    <div class="form_help_icon">
                        <i class="icon-info-sign" id="createVmFirewallHelp"></i>
                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmFirewallHelp', showDelay: 1">
                            <g:message code="user.createVM.firewall.toottip"/>
                        </div>
                    </div>
                </div>             
            </div>
            <div class="field-box control-group hide_text" id="createVMNetworkDiv">
                <label class="control-label"><g:message code="common.network"/>:<span class="require">*</span></label>
                <div class="controls updatable elements">                    
                    <div id="instanceNetworkList" class="selectOption"></div>
                    <img id="vmFirewalLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                    <div class="form_help_icon">
                        <i class="icon-info-sign" id="createVmNetworkHelp"></i>
                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmNetworkHelp', showDelay: 1">
                            <g:message code="user.createVM.network.toottip"/>
                        </div>
                    </div>
                </div>  
            </div>
             <div class="span12 field-box control-group">
            <label class="control-label"><g:message code="user.createVM.SSHKey.label"/>:<span class="require">*</span></label>
                 <div class="controls updatable elements">
                     <div id="instanceSSHkeyList" class="selectOption"></div>
                     <div class="form_help_icon">
                         <i class="icon-info-sign" id="createVmsshKeyHelp"></i>
                         <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmsshKeyHelp', showDelay: 1">
                             <g:message code="user.createVM.sshKey.toottip"/>
                         </div>
                     </div>
                 </div>             
             </div>
            <div class="span12 field-box control-group" id="billingTypeDiv">
                  <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/>:<span class="require">*</span></label>
                  <div class="controls">
                      <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="billingType" id="hourlyBilling" value="hourly" onchange="CreateVMInfo.enableMonthlyCost()"/>
                    <label for="hourlyBilling"><g:message code="common.hourly"/></label> 
                    <input type="radio" data-dojo-type="dijit.form.RadioButton" name="billingType"  id="monthlyBilling" value="monthly" onchange="CreateVMInfo.enableMonthlyCost()"/> 
                    <label for="monthlyBilling" class=""><g:message code="common.monthly"/></label> 
                    <div class="form_help_icon">
                       <i class="icon-info-sign" id="createVmBillingTypeHelp"></i>
                       <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmBillingTypeHelp', showDelay: 1">
                        <g:message code="user.createVM.billingType.toottip"/>
                       </div>
                    </div>
                  </div>
            </div>
          <!--<div class="span12 field-box">-->
            <span style="display: none" class="offset3 require" id="createVMErrorMessage"><g:message code="user.createVM.required"/></span>
          <!--</div>-->
          <div  class="span12 field-box">
              <div class="offset4" id="instanceLoader"></div>
              <div class="span3 pull-right">                            
                    <!--<input type="reset" value="Cancel" class="cancelbtn" onclick="CreateVMInfo.cancel();"><span>OR</span>-->
                <button type="button" data-dojo-type="dijit.form.Button" onclick="CreateVMInfo.add()" title="<g:message code='user.createVM' />"
                            data-dojo-props = "disabled: true"  id="createButton" class="defaultbtn"><g:message code="user.createVM"/>
              </button>
              </div>
          </div>
          </form>
      </div>
    </div>
         
    
    <div class="span3" id="offeringInfo">
      <div class="new_user_form inline-input">     
        <div class="row-fluid">    
            <div class="span4"><a onclick="RateCardInfo.showRateCardDialogue();RateCardInfo.populateValues();"><g:message code='common.rateCard' /></a><g:render template="rateCard" /></div>
            <div  class="span7 create_vm_cost"><div class="value_dollar"><g:message code="default.valueIn"/><span id="createVMCurrecy" style="color: #374859; float: none"></span></div></div>             
        </div>
          <div class="row-fluid customLabel" id="computOfferInfo">
            <div class="span12">
                <h3><g:message code="common.info"/></h3>
            </div>
            <div class="span12">
              <label><g:message code="common.memory"/>:</label>
              <span id="computCost"></span>
            </div> 
            <div class="span12" id="comMonDiv" style="display: none;">
              <label><g:message code="common.monthly"/>:</label>
              <span id="monthlyComputCost"></span>
            </div> 
          </div>           
          <div class="row-fluid customLabel" id="diskOfferInfo">
            <div class="span12">
                <label><g:message code="user.createVM.diskSize.label"/>:</label>
              <span id="diskCost"><g:message code="user.vm.createVM.noDisk"/></span>
            </div>
            <div class="span12" id="diskMonDiv" style="display: none;">
                <label><g:message code="common.monthly"/>:</label>
                <span id="monthlyDiskCost"></span>
            </div>
          </div>
           <div class="row-fluid customLabel" id="tempDataInfo">
            <div class="span12">
			<div class="row-fluid"><label><g:message code="user.createVM.template.label"/>:</label></div>
              <span id="tempCost"></span>
            </div> 
              <hr>  
          </div>  
             
        <div class="row-fluid customLabel" id="vmAvgCostInfo">   
                <div class="span12">
                  <h3><g:message code="common.avgCost"/></h3>
                </div> 
                <div class="span12">
                   <label><g:message code="common.cost"/>:</label>
              <span id="generalAvgCost"></span>
                </div>           
        </div>
        <div class="row-fluid customLabel" id="summaryInfo">
          <div class="span12">
            <div class="span3 pull-right">
              <a onclick="CreateVMInfo.showSummary();" title="<g:message code="common.more"/>"><g:message code="common.more"/></a>
            </div>
          </div>    
        </div>
      </div>
    </div>            
  </div>
</div>

<div data-dojo-type="dijit.Dialog" title="<g:message code="common.summary"/>" id="vmSummaryPage" class="customDialgue">
  <div  class="new-user span6">
    <div class="row-fluid">
          <div class="value_dollar pull-right"><g:message code="default.valueIn"/><span id="summaryCurrencyValue"></span></div>
    </div>
    <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span12" id="vmSummaryForm">
      <div class="container">        
        <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form"> 
          <div class="row-fluid summaryInfoWrapper">
            <div class="span12">
              <label style="font-size: 18px !important; margin-left: 10px"><g:message code="user.createVM.zone.label"/>:</label>
              <span id="summaryZone"></span>
              </div>
          </div>
          <div class="row-fluid summaryInfoWrapper" id="summaryComputOfferingInfo">
            <div class="span12">
              <!--<div class="span6">-->
                 <h3><g:message code="common.offeringInfo"/></h3> 
              <!--</div>-->   
          </div>
          <div class="span12">
            <div class="span7">
              <label><g:message code="common.cpuCore"/>:</label>
              <span id="summaryCpuCore"></span> 
            </div>
            <div class="span5">
              <label><g:message code="common.speed"/>:</label>
              <span id="summarySpeed"></span> 
            </div>              
          </div>
          <div class="span12">
            <div class="span7">
              <label><g:message code="common.memory"/>:</label>
            <span id="summaryMemory"></span> 
            </div>
            <div class="span5">
              <label><g:message code="common.bandwidth"/>:</label>
              <span id="summaryBandwidth"></span> 
            </div>              
          </div>
          <div class="span12">
            <div class="span7">
              <label><g:message code="common.setupCost"/>:</label>
            <span id="summarySetupCost"></span> 
            </div>
            <div class="span5">
              <label><g:message code="common.runningCost"/>:</label>
              <span id="summaryRunningCost"></span> 
            </div>              
          </div> 
          <div class="span12">
            <div class="span7">
                <div class="span12 hide_text" id="sumComMonDiv">
                    <label><g:message code="common.monthly"/>:</label>
                    <span id="summaryMonthlyComCost"></span> 
                </div>              
            </div> 
            <div class="span5">
                <div class="span12 hide_text" id="sumBandwidthCost">
                    <label><g:message code="common.bandwidthCost"/>:</label>
                    <span id="summaryBandwidthCost"></span> 
                </div>
            </div>
          </div> 
          <hr>
          </div>
          <div class="row-fluid summaryInfoWrapper" id="summaryStorageInfo">
            <div class="span12">
            <h3><g:message code="common.storageInfo"/></h3> 
          </div>
          <div class="span12">
            <div class="span7">
              <label><g:message code="common.size"/>:</label>
              <span id="summaryDiskSize"></span> 
            </div>
            <div class="span5">
              <label><g:message code="common.cost"/>:</label>
              <span id="summaryDiskCost"></span> 
            </div>              
          </div>
              <div class="span12" id="sumDiskMonDiv" style="display: none;">
            <div class="span7">
              <label><g:message code="common.monthly"/>:</label>
            <span id="summaryMonthlyDisCost"></span> 
            </div>           
          </div> 
          <hr> 
          </div>
          <div class="row-fluid summaryInfoWrapper">
            <div class="span12">
            <h3><g:message code="common.templateInfo"/></h3> 
          </div>
          <div class="span12">
            <div class="span7">
              <label><g:message code="common.name"/>:</label>
              <span id="summaryOsType"></span> 
            </div>
            <div class="span5">
              <label><g:message code="common.rootDisk"/>:</label>
              <span id="summaryTempSize"></span> 
            </div>             
          </div>
            <div class="span12" id="summaryAvgCostInfo">
            <label><g:message code="common.cost"/></label>
            <span id="summaryTempCost"></span>
          </div>
          <hr>
           <div class="span12">
            <h3><g:message code="common.avgCost"/></h3> 
          </div>
          <div class="span12">
            <label><g:message code="common.cost"/></label>
            <span id="summaryAvgCost"></span>
          </div>
          </div>         
          
        </form>
      </div>
    </div>
    </div>
  </div>
  </div>
<div data-dojo-type="dijit.Dialog" id="createVMLoading" title="<g:message code='common.vm.buildingVM' />" style="color: black;" class="customDialgue span6">
      <div class="row-fluid">
          <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
          <div class="span9">
              <div class="span12"><p><g:message code='common.vm.buildingVMMsg1' /></p></div>
              <div class="span12" style="margin-left: 0"><p><g:message code='common.vm.buildingVMMsg2' /></p></div>              
          </div>          
      </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="CreateVMInfo.gotoInstances()"><g:message code='common.gotoVMList' /></a>
        <a class="btn-flat default" onclick="CreateVMInfo.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>
        <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="CreateVMInfo.cancelPopup()" style="display: none">
            <g:message code='common.cancel' />
        </button>         
    </div>   
</div>

<div data-dojo-type="dijit.Dialog" id="createTierVMLoadingDialog" title="<g:message code='common.vm.buildingVM' />" style="color: black;" class="customDialgue span6">
      <div class="row-fluid">
          <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
          <div class="span9">
              <div class="span12"><p><g:message code='common.vm.buildingVMMsg3' /></p></div>
              <div class="span12" style="margin-left: 0"><p><g:message code='common.vm.buildingVMMsg2' /></p></div>              
          </div>          
      </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="CreateVMInfo.gotoTierInstances()" id="tierVmButton"><g:message code='common.gotoVMList' /></a>
        <a class="btn-flat default" onclick="CreateVMInfo.gotoVPCDashboard()"><g:message code='common.gotoVPCDashboard' /></a>
        <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="CreateVMInfo.cancelPopup()" style="display: none">
            <g:message code='common.cancel' />
        </button>         
    </div>   
</div>

<div data-dojo-type="dijit.Dialog" id="ceateVMNoNetworkDialogue" style="width: 450px;" title="<g:message code='user.vm.noNetworkTitle' />"> 
    <div class="row-fluid">
        <div class="span12">
            <div class="span2 index_title_icons_network"></div>
            <div class="span10 form-horizontal">                
                <div class="span12 field-box control-group" id="showPasswordInfo">     
                    <p><g:message code='user.createVM.noNetworkMsg' /></p>
                </div>                    
                <div class="span12">
                    <a class="btn-flat default" id="gotoNetworkPageTag" href="#/user/network/add" onclick="CreateVMInfo.cancelNetworkOption()"><g:message code='common.gotoNetwork' /></a>
                    <button type="button" class="cancelbtn" data-dojo-type = "dijit.form.Button" onclick="CreateVMInfo.cancelNetworkOption()"><g:message code='common.cancel' /></button>
                </div>
            </div>
        </div>  
    </div>
</div>