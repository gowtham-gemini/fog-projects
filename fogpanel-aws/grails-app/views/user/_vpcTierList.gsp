<div class="row-fluid filter-block">
    <div class="row-fluid">
        <div class="span8">
            <div class="alert alert-danger" id="vpcAddTierRachedLimitMsg" style="display: none;">        
                <i class="icon-exclamation-sign"></i><g:message code="common.reachedNetworkLimit"/>
            </div> 
        </div>
        <div class="span4">
            <div class="pull-right">
                <div class="span6">
                    <button  data-dojo-type="dijit.form.Button" onclick="ViewVpc.populateTierValues()" class="okbtn">
                        <i class="icon-refresh"></i> <g:message code='common.refresh' />
                    </button>
                </div>
                <div id="vpcAddTierButton" class="span4">
                    <button  data-dojo-type="dijit.form.Button" onclick="ViewVpc.addTierShow()" class="okbtn">
                        <g:message code="common.addTier"/>
                    </button>     
                </div>                                 
            </div>
        </div>
    </div>    
    <div class="row-fluid" style="margin-top: 70px;">
      <div id="userTierList"></div>
      <div class="alert alert-info hide" id="noTierMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noTier"/>
      </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="vpcAddTierDialog" title="<g:message code="common.addTier"/>" class="customDialog span6">
    <div class="row-fluid">
        <div class="span2">                
            <a class="index_title_icons_network span12"></a>                
        </div>
        <div class="span9">
            <div class="row-fluid hide_text" id="vpcInfoAddTierCloudstackException">
                <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="vpcInfoAddTierCloudstackExceptionMsg" class="span10"></span></div>
            </div>   
            <div class="row-fluid">   
                <g:render template="vpcTierAdd" />
            </div>
        </div>
    </div>
    <div class="row-fluid">
         <div class="pull-right">
           <button data-dojo-type="dijit.form.Button" onclick="ViewVpc.addTier();" class="primarybtn">
              <g:message code="common.ok"/>
           </button>
           <button data-dojo-type="dijit.form.Button" onclick="ViewVpc.cancelTier();" class="cancelbtn">
              <g:message code="common.cancel"/>
           </button>
         </div>
     </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="vpcEditTierDialog" title="<g:message code="common.edit"/>" class="customDialog span6">
<input type="hidden" id="editNetworkId">
<div class="row-fluid">
    <div class="span2"><img src="images/edit.png"></div>
<form id="vpcEditTierPageForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
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
                        id="tierEditName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
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
                          id="tierEditDesc" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.desc"/>', placeHolder: '<g:message code="common.desc"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                          promptMessage:'<g:message code="common.desc.placeHolder"/>'">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tierEditNetworkOffering" class="control-label">
                            <g:message code="common.networkoffering"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="networkOfferingEditList" class="selectOption"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tierEditNWDomain" class="control-label">
                             <g:message code="common.networkDomain"/>
                             <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                          id="tierEditNWDomain" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.value"/>', placeHolder: '<g:message code="common.networkDomain"/>',
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
       <button data-dojo-type="dijit.form.Button" onclick="VPCTierInfo.editTierShow();" class="primarybtn">
          <g:message code="common.ok"/>
       </button>
       <button data-dojo-type="dijit.form.Button" onclick="VPCTierInfo.cancelEditTier();" class="cancelbtn">
          <g:message code="common.cancel"/>
       </button>
     </div>
 </div>
</div>
<div data-dojo-type="dijit.Dialog" id="tierDeleteDialog" class="span4"  title="<g:message code="common.delete"/>">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span2"><img src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png"></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.tierDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPCTierInfo.deleteTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPCTierInfo.closeDelete()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="tierReplaceACLDialog" class="span4" title="<g:message code="common.ACL"/>">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span3"><img src="images/network_acl_icon.png"></div>
        <div class="span9" style="margin-left: 0">              
            <div class="span2">
                <p><g:message code="common.ACL"/></p>
            </div>
            <div id="repalceAClPage" class="span4">
                <div id="tierReplaceAclList" class="selectOption"></div>   
            </div>                    
        </div>
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.replaceAclTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.closeReplaceAcl()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="tierRestartDialog" class="span4" title="<g:message code='common.restart'/>" >
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
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="cleanup" name="cleanup">     
            </div>                    
        </div>
        </div>  
        
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPCTierInfo.restartTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPCTierInfo.closeRestart()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="tierEditConformDialog" class="span4" title="<g:message code="common.edit"/>">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span2"><img src="images/edit.png"></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.tierEditConform' /></p></div>
            <div class="span12" style="margin-left: 0">              
            <div class="span4">
                <p><g:message code="common.cidrchanged"/></p>
            </div>
            <div class="span2">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="cirdchange" name="cirdchange">     
            </div>                    
        </div>
        </div>  
        
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPCTierInfo.editTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPCTierInfo.closEditTierConform()"><g:message code="common.cancel"/></button>
    </div>
</div>
