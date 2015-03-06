<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/list"><g:message code="common.vpc"/></a></li>
        <li>/</li>
        <li><a id="currentVpcNameLink" href=""><span id="currentVpcName"></span></a></li>
        <li>/</li>
        <li><a  onclick="ViewVpc.showTierTab()"id="currentTierListLink" ><g:message code="common.tier"/></a></li>
        <li>/</li>
        <li><span id="currentTierName"></span></li>
      </ul>
  </div>
</div>
<input type="hidden" id="currentTierId">
<input type="hidden" id="currentTierReferenceId">
<input type="hidden" id="currentLoadBalancingId">
<input type="hidden" id="currentTierZoneId">
<input type="hidden" id="currentTierVmID"/>
<input type="hidden" id="currentTierNwOfferID"/>
<input type="hidden" id="currentVpcId"/>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="tierLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div class="row-fluid">
        <div class="network-title-info span5">
            <div class="grd-row-alt1-tbl">
                <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                    <div class="grd-tbl-cell clm-second"><span id="viewTierNameTop"  ></span></div>
                </div>
            </div>
            <div class="grd-row-alt2-tbl">
                <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                    <div class="grd-tbl-cell clm-second"><span id="viewTierZoneNameTop"></span></div>
                </div>
            </div>
        </div>
        <div class="span2">
            <div class="network-title-info">
                <div class="row-fluid">
                    <div class="span12" style="margin-left: 5px;">
                        <a class="span3" onclick="TierInfo.replaceACLTierShow();" id="changeAclOptionDiv">              
                            <img title="<g:message code="common.changeAcl"/>" src="images/network_acl_icon.png"/>
                        </a>        
                        <a class="span3" onclick="TierInfo.showTierInfoRestart();" id="refreshOptionDiv">
                            <img title="<g:message code="common.restart"/>" src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_reboot_icon.png"/>
                        </a>
                        <a class="span3" onclick="TierInfo.showEditTierInfo();">
                            <img title="<g:message code="common.edit"/>" src="images/edit.png"/>
                        </a>
                        <a class="span3" onclick="TierInfo.showTierInfoDelete();">
                            <img title="<g:message code="common.delete"/>" src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png">
                        </a>  
                    </div>
                   </div>
                </div>
            </div>
        </div>    
    
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="tierTabCointainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true" id="tierDetailContainer">
            <div class="row-fluid">
                <div class="span12">
<!--                    <div class="row-fluid header">
                         <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
                    </div>-->
                    <form id="" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierName"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.desc"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierDesc"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierReferenceId"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkoffering"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierOfferName" ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.type"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierType"  ></span></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.gateway"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierGateway"  ></span></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.cidr"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierCidr"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.netmask"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierNetmask"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkDomain"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierDomain"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.state"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierState"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl" style="display: none;">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewTierZoneName"  ></span></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="span6">
                                
                            </div>
                          </div>
                    </form>
                </div>
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="tierInstanceContentPane" title="<g:message code="common.instance"/>" onshow="VMTierInfo.populateValues()">
           <g:render template="tierInstancePage" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="tierStaticNat"  title="<g:message code="common.staticNAT"/>" onshow="TierStatiscNatInfo.populateValues()">
           <g:render template="viewTierStatisNat" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.portForwarding"/>" onshow="TierPortforwardingInfo.populateValues()" selected="true" id="tierPortForwarding">
            <g:render template="tierPortForwarding" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="tierPublicLoadBalancer" title="<g:message code="common.publicLoadBalancing"/>" onShow="TierPublicLoadBalancerInfo.populateValues()">
            <g:render template="tierPublicLB" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="tierInternalLoadBalancer" title="<g:message code="common.internalLoadBalancing"/>" onShow="TierInternalLoadBalancerInfo.populateValues()">
            <g:render template="tierInternalLB" />
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="addInternalLbDialog" title="<g:message code="common.addInternalLB"/>" class="customDialog span6">
    <div class="row-fluid">
        <div class="span2"><a class="index_title_icons_lb span12"></a></div>
        <div class="spn9"><g:render template="addInternalLB" /></div>    
    </div>    
    <div class="row-fluid">
         <div class="pull-right">
           <button data-dojo-type="dijit.form.Button" onclick="TierInternalLoadBalancerInfo.addLoadBalancing();" class="primarybtn">
              <g:message code="common.ok"/>
           </button>
           <button data-dojo-type="dijit.form.Button" onclick="TierInternalLoadBalancerInfo.closeAddInternalLB();" class="cancelbtn">
              <g:message code="common.cancel"/>
           </button>
         </div>
     </div>
</div>
<div data-dojo-type="dijit.Dialog" id="internalLBDeleteLDialog" class="span4">
    <input id="currentLBId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.LBDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="TierInternalLoadBalancerInfo.deleteLoadBalancerRule()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="TierInternalLoadBalancerInfo.deleteLoadBalancerRuleClose()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="internalLBRemoveVMDialog" class="span4">
    <input id="lbCurrentVMId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.removeVMFromLb' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="TierInternalLoadBalancerInfo.removeVMFromLb()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="TierInternalLoadBalancerInfo.cancelRemoveVMFromLbDialog()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="startTierVMDialog" title="<g:message code='user.vm.startVm' />" style="color: black; width: 350px;" class="customDialgue">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_play_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.startVm.confirm' /></div>            
        </div>
    </div>
    <div class="row-fluid">
        <button type="button" data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="VMTierInfo.startInstance()">   
            <g:message code='common.ok' />    
        </button>
        <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VMTierInfo.closeStartDialog()">
            <g:message code='common.cancel' />
        </button> 
    </div>        
</div>

<div data-dojo-type="dijit.Dialog" id="stopTierDialog" title="<g:message code='user.vm.stopVm.title' />" style="color: black; width: 350px; background: #FFFFFF">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_shutdown_icon.png'/></div>
        <div class="span10">
             <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form">                
                 <div class="span12"><span><g:message code='user.vm.stopVm.confirm' /></span></div>                 
                 <div class="span12 field-box control-group" style="margin-bottom: 0 !important;margin-left: 0;">                     
                     <div class="span5"><label for="stopTierInstanceAgreement" style="font-size: 13px;"><g:message code='common.forceshutdown'/></label> </div>
                     <div class="span1"><input  type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="stopTierInstanceAgreement" name="agreement"/></div>
                </div>  
            </form>
        </div>
    </div>
    <div class="row-fluid">           
        <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="VMTierInfo.stopInstance()" id=""> <g:message code='common.ok' /> </button>
        <button data-dojo-type="dijit.form.Button" onclick="VMTierInfo.closeStopDialog()" class="cancelbtn"> <g:message code='common.cancel' /></button> 
    </div>                    
</div> 

<div data-dojo-type="dijit.Dialog" id="rebootVMTierDialog" title="<g:message code='user.vm.rebootVm.title' />" style="color: black; width: 350px; background: #FFFFFF;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_reboot_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.rebootVm.confirm' /> </div>            
        </div>        
    </div>            
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="VMTierInfo.rebootInstance()"> <g:message code='common.reboot' /> </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="VMTierInfo.closeRebootDialog()"> <g:message code='common.cancel' /></button> 
        </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="vmTierRestoreDialog" title="<g:message code='user.vm.restore.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <input type="hidden" id="currentVmID"/>
        <div class="span2"><img src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_restore_icon.png"/></div>
        <div class="span9">
            <div class="span12"><g:message code='user.vm.restore.confirm' />  </div>            
        </div>                 
    </div>            
    <div class="row-fluid">
        <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="VMTierInfo.restoreInstance()">
            <g:message code='common.restore' />   
        </button>
        <button data-dojo-type="dijit.form.Button" onclick="VMTierInfo.closeRestoreVMDialog()" class="cancelbtn">
            <g:message code='common.cancel' />
        </button> 
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="deleteTierVMDialog" title="<g:message code='user.vm.deleteVm.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.deleteVm.confirm' />  </div>            
        </div>        
         <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="VMTierInfo.deleteInstance()" id="">
                <g:message code='default.button.delete.common' />   
            </button>
            <button id="" data-dojo-type="dijit.form.Button" onclick="VMTierInfo.closeDeleteDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
         </div>
    </div>                        
</div>


<div data-dojo-type="dijit.Dialog" id="tierInfoReplaceACLDialog" class="span4" title="<g:message code="common.ACL"/>">    
    <div class="row-fluid">
        <div class="span3"><img src="images/network_acl_icon.png"></div>
        <div class="span9" style="margin-left: 0">              
            <div class="span2">
                <p><g:message code="common.ACL"/></p>
            </div>
            <div id="generalRepalceAClPage" class="span4">
                <div id="tierInfoReplaceAclList" class="selectOption"></div>   
            </div>                    
        </div>
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="TierInfo.replaceAclTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="TierInfo.closeReplaceAcl()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="tierInfoRestartDialog" class="span4" title="<g:message code='common.restart'/>">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span2"><img src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_reboot_icon.png"></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.tierRestartConform' /></p></div>
            <div class="span12" style="margin-left: 0">              
            <div class="span2">
                <p><g:message code="common.cleanup"/></p>
            </div>
            <div class="span2">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="tierInfocleanup" name="cleanup">     
            </div>                    
        </div>
        </div>  
        
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="TierInfo.restartTierInfo()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="TierInfo.closeTierInfoRestart()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="tierInfoDeleteDialog" class="span4" title="<g:message code="common.delete"/>">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span2"><img src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png"></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.tierDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="TierInfo.deleteTierInfo()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="TierInfo.closeTierInfoDelete()"><g:message code="common.cancel"/></button>
    </div>
</div>


<div data-dojo-type="dijit.Dialog"  id="tierInfoEditDialog" title="<g:message code="common.edit"/>" class="customDialog span6" >
<input type="hidden" id="editNetworkId">
<div class="row-fluid">
    <div class="span2"><img src="images/edit.png"></div>
<form id="tierInfoEditForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
    <div class="span9">
            <div id="vpcEditTierPageDiv" class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="tierEditName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        id="tierInfoEditName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                        promptMessage:'<g:message code="common.name.placeHolder"/>'">
                      </div>
                    </div>
                    <div class="control-group">
                        <label for="tierEditDesc" class="control-label">
                             <g:message code="common.desc"/>
                             <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                          id="tierInfoEditDesc" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.desc"/>', placeHolder: '<g:message code="common.desc"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                          promptMessage:'<g:message code="common.desc.placeHolder"/>'">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tierEditNetworkOffering" class="control-label">
                            <g:message code="common.networkoffering"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="tierInfoNetworkOfferingEditList" class="selectOption"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tierEditNWDomain" class="control-label">
                             <g:message code="common.networkDomain"/>
                             <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                          id="tierInfoEditNWDomain" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.value"/>', placeHolder: '<g:message code="common.networkDomain"/>',
                          promptMessage:'<g:message code="common.networkDomain"/>'">
                        </div>
                    </div>
                </div>
            </div> 
        </div>
</form>
</div>
<div class="row-fluid">
     <div class="pull-right">
       <button data-dojo-type="dijit.form.Button" onclick="TierInfo.showTierInfoEditConfirmation();" class="primarybtn">
          <g:message code="common.ok"/>
       </button>
       <button data-dojo-type="dijit.form.Button" onclick="TierInfo.cancelEditTierInfo();" class="cancelbtn">
          <g:message code="common.cancel"/>
       </button>
     </div>
 </div>
</div>

<div data-dojo-type="dijit.Dialog" id="tierInfoEditConformDialog" class="span4" title="<g:message code="common.edit"/>">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span2"><img src="images/edit.png"></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.tierEditConform' /></p></div>
        </div>  
        <div class="span12" style="margin-left: 0">              
            <div class="span4">
                <p><g:message code="common.cidrchanged"/></p>
            </div>
            <div class="span2">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="tierInfoCirdchange" name="cirdchange">     
            </div>                    
        </div>
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="TierInfo.editTierInfo()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="TierInfo.cancelEditTierInfo()"><g:message code="common.cancel"/></button>
    </div>
</div>