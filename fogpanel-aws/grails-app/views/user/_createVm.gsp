<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>    
            <li><a href="#/user/home" id="createVMHomeMenuName"><i class="icon-home"></i></a></li>     
            <li>/</li>
            <li><a href="#/user/cloud/" id="createVMCloudMenuName"><g:message code="menu.admin.cloud"/></a></li>
            <li>/<li>
            <li><a href="#/user/server/list" id="vmBreadcrumb"><g:message code="menu.user.cloud.instance"/></a></li>
            <li>/<li>
            <li id="createVMtitle1"><g:message code="user.createVM"/></li>
        </ul>
    </div>
</div>
<div class="new-user createVm"> 
    <div class="row-fluid header createVm">
        <input type="hidden" id="imageCostTemp">
        <input type="hidden" id="flavorCostTemp">      
      <!--<h6>Create VM</h6>-->    
    </div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span3 createvm-banner">
            <img src="${resource(dir: 'images')}/createvm_server_logo.png" height="151" width="238">
            <h2 class="alphaStyle overflowLabel"><g:message code="user.createVM"/></h2>
            <h2 class="alphaStyle overflowLabel"><g:message code="common.volumes"/></h2>
            <h2 class="alphaStyle overflowLabel"><g:message code="common.images"/></h2>
            <h2 class="alphaStyle overflowLabel"><g:message code="common.firewall"/></h2>
        </div>
        <div class="span6 createvm-form with-sidebar">
            <div class="container">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="createVMForm"> 
                    <div id="createVMPage">
                        <div class="row-fluid hide_text" id="addVmAwsException">
                            <div class="span12 alert alert-error"><i class="icon-remove-sign"></i><span id="addVmAwsExceptionMsg"></span></div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="instanceName" class="control-label">
                                <g:message code="user.createVM.dislayName"/> :<span class="require">*</span></label>
                            <div class="controls elements">                       
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="required:'true',
                                invalidMessage:'<g:message code="user.createVM.diskplayName.invalidMessage"/>',
                                placeHolder: '<g:message code="user.createVM.diskplayName.placeHolder"/>',
                                missingMessage:'<g:message code="user.createVM.diskplayName.invalidMessage"/>',
                                promptMessage: '<g:message code="user.createVM.diskplayName.promptMessage"/>'"  
                                id="instanceName" name="instanceName" class="vmhostname">
                                <div class="form_help_icon" style="top: 6px;">
                                    <i class="icon-info-sign" id="createVmHostNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmHostNameHelp', showDelay: 1"><g:message code="user.createVM.diskplayName.toottip"/></div>
                                </div>            
                            </div>
                        </div>
                        <div class="span12 field-box control-group" id="vmTemplateNameInfo">
                            <label for="userTempSelect" class="control-label"><g:message code="common.image"/> :<span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="instanceImageList" class="updatable selectOption"></div>
                                <img id="vmImageLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon">
                                    <i class="icon-info-sign" id="createVmHostTempHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmHostTempHelp', showDelay: 1"><g:message code="user.createVM.template.toottip" /></div>
                                </div>
                            </div> 
                            <!--<span id="vmTemplateName" class="template_text"></span>-->      
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="instanceComputingOffer" class="control-label"><g:message code="common.flavor"/> :<span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="instanceFlavorList" class="selectOption"></div>
                                <img id="vmFlavorLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon">
                                    <i class="icon-info-sign" id="createVmOfferHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmOfferHelp', showDelay: 1"> <g:message code="user.createVM.computingOffer.toottip"/></div>
                                </div>
                                <!--<span id="instanceComputName" class="offset2"></span>-->          
                            </div>
                        </div> 
                        <div class="field-box control-group" id="">
                            <label class="control-label"><g:message code="common.network"/> :<span class="require">*</span></label>
                            <div class="controls updatable elements">                    
                                <div id="networkImageList" class="selectOption"></div>
                                <img id="vmFirewalLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon">
                                    <i class="icon-info-sign" id="createVmNetworkHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmNetworkHelp', showDelay: 1">
                                        <g:message code="user.createVM.vpc.toottip"/>
                                    </div>
                                </div>
                            </div>  
                        </div>
                        <div class="field-box control-group" id="subnetDiv" style="display: none;">
                            <label class="control-label"><g:message code="common.vpc.subnet"/> :<span class="require">*</span></label>
                            <div class="controls updatable elements">                    
                                <div id="subnetImageList" class="selectOption"></div>
                                <img id="vmSubnetLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon">
                                    <i class="icon-info-sign" id="createVmSubnetHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmSubnetHelp', showDelay: 1">
                                        <g:message code="user.createVM.subnet.toottip"/>
                                    </div>
                                </div>
                            </div>  
                        </div>                        
                        <div class="span12 field-box control-group">
                            <label for="rootSize" class="control-label">
                                <g:message code="user.createVM.size"/> :<span class="require">*</span></label>
                            <div class="controls elements">                       
                                <input type="text" data-dojo-type="dijit.form.NumberTextBox" maxlength=4
                                data-dojo-props="required:'true', constraints:{min:8, max:1024, places:0},
                                invalidMessage:'<g:message code="user.createVM.size.invalidMessage"/>',
                                placeHolder: '<g:message code="user.createVM.size.placeHolder"/>',
                                missingMessage:'<g:message code="user.createVM.size.invalidMessage"/>',
                                promptMessage: '<g:message code="user.createVM.size.promptMessage"/>'"  
                                id="rootSize" name="rootSize" class="rootSize" onchange="AddServer.populateIops()">
                                <div class="form_help_icon" style="top: 6px;">
                                    <i class="icon-info-sign" id="createVmRootSizeHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmRootSizeHelp', showDelay: 1"><g:message code="user.createVM.size.tooltip"/></div>
                                </div>            
                            </div>                            
                        </div>
                        <div class="field-box control-group" id="volumeTypeDiv">
                            <label class="control-label"><g:message code="user.createVM.volumeType"/> :<span class="require">*</span></label>
                            <div class="controls updatable elements">                    
                                <div id="volumeTypeImageList" class="selectOption"></div>
                                <img id="vmVolumeTypeLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon">
                                    <i class="icon-info-sign" id="createVmVolumeTypeHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmVolumeTypeHelp', showDelay: 1">
                                        <g:message code="user.createVM.volumeType.tooltip"/>
                                    </div>
                                </div>
                            </div>  
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="iops" class="control-label">
                                <g:message code="user.createVM.iops"/> :<span id="iopsMandatorySpan" class="require" style="display: none;">*</span></label>
                            <div class="controls elements">                       
                                <span id="iopsEditable" style="display: none;"><input type="text" data-dojo-type="dijit.form.NumberTextBox" maxlength=4
                                data-dojo-props="constraints:{min:100,max:4000,places:0}, 
                                invalidMessage:'<g:message code="user.createVM.iops.invalidMessage"/>',
                                placeHolder: '<g:message code="user.createVM.iops.placeHolder"/>',
                                missingMessage:'<g:message code="user.createVM.iops.invalidMessage"/>',
                                promptMessage: '<g:message code="user.createVM.iops.promptMessage"/>'"  
                                id="iops" name="iops" class="iops"></span>
                                <span id="iopsReadOnly"><g:message code="user.createVM.iops.unavailable"/></span>
                                <div class="form_help_icon" style="top: 6px;">
                                    <i class="icon-info-sign" id="createVmHostIopsHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmHostIopsHelp', showDelay: 1"><g:message code="user.createVM.iops.tooltip"/></div>
                                </div>            
                            </div>                            
                        </div>
                        <div class="span12 field-box control-group" id="securityGroupDiv" style="display: none;">
                            <label class="control-label"><g:message code="common.securityGroup"/> :<span class="require">*</span></label>
                            <div class="controls updatable elements">                    
                                <div id="vmSecurityGroupList"></div>
                                <img id="vmSecurityGroupLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon">
                                    <i class="icon-info-sign" id="createVmSecurityGroupHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmSecurityGroupHelp', showDelay: 1">
                                        <g:message code="user.createVM.securityGroup.tooltip"/>
                                    </div>
                                </div>
                            </div>  
                        </div>
                        <div class="span12 field-box control-group">
                            <label class="control-label"><g:message code="user.createVM.SSHKey.label"/> :<span class="require">* ${accountId}</span></label>
                            <div class="controls updatable elements">
                                <div id="instanceSSHkeyList" class="selectOption"></div>
                                <img id="vmsshKeyLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon">
                                    <i class="icon-info-sign" id="createVmsshKeyHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmsshKeyHelp', showDelay: 1">
                                        <g:message code="user.createVM.sshKey.toottip"/>
                                    </div>
                                </div>
                            </div>             
                        </div>
                        <div  class="span12 field-box">
                            <div class="offset4" id="instanceLoader"></div>
                            <div class="span5 pull-right">                            
                                  <!--<input type="reset" value="Cancel" class="cancelbtn" onclick="CreateVMInfo.cancel();"><span>OR</span>-->
                                <button type="button" data-dojo-type="dijit.form.Button" onclick="AddServer.add()" title="<g:message code='user.createVM' />"
                                        id="createButton" class="defaultbtn"><g:message code="user.createVM"/>
                            </button>
                            <button type="button" data-dojo-type="dijit.form.Button" onclick="AddServer.cancel()" title="<g:message code='common.cancel' />" id="" class="cancelbtn"><g:message code="common.cancel"/>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>   
    <div class="span3" id="offeringInfo">
        <div class="new_user_form inline-input">     
            <div class="row-fluid">    
                <!--<div class="span4"><a onclick=""><g:message code='common.rateCard' /></a><g:render template="rateCard" /></div>-->
                <div  class="span6 create_vm_cost offset7"><div class="value_dollar"><g:message code="default.valueIn"/><span id="createVMCurrecy" style="color: #374859; float: none"></span></div></div>             
            </div>
            <div class="row-fluid customLabel" id="imageDataInfo">
                <div class="span12">
                    <h3><g:message code="common.imageInfo"/></h3>
                </div>
                <div class="span12">                
                    <span id="imageCost"></span>
                </div> 
                <hr>  
            </div> 
            <div class="row-fluid customLabel" id="flavorInfo">
                <div class="span12">
                    <h3><g:message code="common.flavorInfo"/></h3>
                </div>
                <div class="span12">
                    <label><g:message code="common.memory"/>:</label>
                    <span id="flavorCost"></span>
                </div> 
                <div class="span12 hide_text" id="flavorMonDiv">
                    <label><g:message code="common.monthly"/>:</label>
                    <span id="monthlyFlavorCost"></span>
                </div> 
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
                        <a onclick="AddServer.showSummary();" title="<g:message code="common.more"/>"><g:message code="common.more"/></a>
                    </div>
                </div>    
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
        <a class="btn-flat default" onclick="AddServer.gotoInstances()"><g:message code='common.gotoVMList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->
        <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="CreateVMInfo.cancelPopup()" style="display: none">
            <g:message code='common.cancel' />
        </button>         
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
                                <h3><g:message code="common.images"/></h3> 
                            </div>
                            <div class="span12">
                                <label><g:message code="common.name"/>:</label>
                                <span id="imageName"></span> 
                            </div>
                            <div class="span12">
                                <label><g:message code="common.imageDescription"/>:</label>
                                 <span id="imageDescription"></span> 
                            </div>
                            <div class="span12">
                                <div class="span7">
                                    <label><g:message code="common.rootDeviceType"/>:</label>
                                    <span id="rootDeviceType"></span>                                     
                                </div>   
                                <div class="span5">
                                    <label><g:message code="common.rootDeviceName"/>:</label>
                                    <span id="rootDeviceName"></span> 
                                </div>             
                            </div>
                            <div class="span12" id="summaryAvgCostInfo">
                                <label><g:message code="common.cost"/></label>
                                <span id="summaryImageCost"></span>
                            </div>
                            <hr>           
                        </div>         
                        <div class="row-fluid summaryInfoWrapper" id="summaryFlavorInfo">
                            <div class="span12">
                              <!--<div class="span6">-->
                                <h3><g:message code="common.flavorInfo"/></h3> 
                             <!--</div>-->   
                            </div>
                            <div class="span12">
                                <div class="span7">
                                    <label><g:message code="common.cpuCore"/>:</label>
                                    <span id="summaryCpuCore"></span> 
                                </div>
                                <div class="span5">
                                    <label><g:message code="common.memory"/>:</label>
                                    <span id="summaryMemory"></span> 
                                </div>              
                            </div>
                            <div class="span12">
                                <div class="span7">
                                    <label><g:message code="common.family"/>:</label>
                                    <span id="family"></span> 
                                </div>
                                <div class="span5">
                                    <label><g:message code="common.instanceStore"/>:</label>
                                    <span id="instanceStore"></span> 
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
                            <div class="span12 hide_text" id="flavorAvgMonthlyCost">
                                <div class="span7">
                                    <div class="span12">
                                        <label><g:message code="common.monthly"/>:</label>
                                        <span id="summaryMonthlyFlavorCost"></span> 
                                    </div>              
                                </div> 
                    <!--            <div class="span5">
                                    <div class="span12 hide_text" id="sumBandwidthCost">
                                        <label><g:message code="common.bandwidthCost"/>:</label>
                                        <span id="summaryBandwidthCost"></span> 
                                    </div>
                                </div>-->
                            </div> 
                            <hr>
                        </div> 
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
