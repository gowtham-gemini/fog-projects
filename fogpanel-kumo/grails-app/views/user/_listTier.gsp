<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.yourVPC"/></a></li>
        <li>/</li>
        <li><g:message code="common.tiers"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid filter-block">
    <div class="pull-right">
        <input type="hidden" id="generalCurrentTierId">
        <a class="btn-flat success new-product" onclick="ListTierInfo.showAddTierDialogu();"><g:message code="common.addTier"/></a>        
    </div>
    <div class="row-fluid">
        <div class="span12"></div>
        <div class="span12"></div>        
    </div>
    <div class="row-fluid">
      <div id="vpcTierList"></div>
    </div>
    <div class="row-fluid">
      <div class="alert alert-info hide" id="noTierListMsg" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noTier"/>
      </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="generalTierReplaceACLDialog" class="span4">    
    <div class="row-fluid">
        <div class="span12" style="margin-left: 0">              
            <div class="span2">
                <p><g:message code="common.ACL"/></p>
            </div>
            <div id="generalRepalceAClPage" class="span4">
                <div id="vpcGeneralReplaceAclList" class="selectOption"></div>   
            </div>                    
        </div>
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListTierInfo.replaceAclTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListTierInfo.closeReplaceAcl()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="generalListTierLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="generalTierRestartDialog" class="span4">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.tierRestartConform' /></p></div>
        </div>  
        <div class="span12" style="margin-left: 0">              
            <div class="span2">
                <p><g:message code="common.cleanup"/></p>
            </div>
            <div class="span2">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="Generalcleanup" name="cleanup">     
            </div>                    
        </div>
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListTierInfo.restartTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListTierInfo.closeRestart()"><g:message code="common.cancel"/></button>
    </div>
</div>


<div data-dojo-type="dijit.Dialog"  id="vpcGeneralEditTierDialog" title="<g:message code="common.edit"/>" class="customDialog span6">
<input type="hidden" id="generalEditNetworkId">
<div class="row-fluid">
<form id="vpcGeneralEditTierPageForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
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
                        id="vpcGeneralTierEditName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
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
                          id="vpcGeneralTierEditDesc" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.desc"/>', placeHolder: '<g:message code="common.desc"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                          promptMessage:'<g:message code="common.desc.placeHolder"/>'">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tierEditNetworkOffering" class="control-label">
                            <g:message code="common.networkoffering"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="networkOfferingGeneralEditList" class="selectOption"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tierEditNWDomain" class="control-label">
                             <g:message code="common.networkDomain"/>
                             <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                          id="generalTierEditNWDomain" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.value"/>', placeHolder: '<g:message code="common.networkDomain"/>',
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
       <button data-dojo-type="dijit.form.Button" onclick="ListTierInfo.editTierShow();" class="primarybtn">
          <g:message code="common.ok"/>
       </button>
       <button data-dojo-type="dijit.form.Button" onclick="ListTierInfo.cancelEditTier();" class="cancelbtn">
          <g:message code="common.cancel"/>
       </button>
     </div>
 </div>
</div>

<div data-dojo-type="dijit.Dialog" id="generalTierEditConformDialog" class="span4">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.tierEditConform' /></p></div>
        </div>  
        <div class="span12" style="margin-left: 0">              
            <div class="span4">
                <p><g:message code="common.cidrchanged"/></p>
            </div>
            <div class="span2">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="generalCirdchange" name="cirdchange">     
            </div>                    
        </div>
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListTierInfo.editTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListTierInfo.closEditTierConform()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="generalTierDeleteDialog" class="span4">
    <input id="currentTierId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.tierDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListTierInfo.deleteTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListTierInfo.closeDelete()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog"  id="vpcGeneralAddTierDialog" title="<g:message code="common.addTier"/>" class="customDialog span6">
    <div class="row-fluid">   
        <form id="vpcGeneralAddTierForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
    <div class="span9">
            <div id="vpcGeneralAddTierPageDiv" class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="tierName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        id="generalTierName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{3,200}',
                        promptMessage:'<g:message code="common.name.placeHolder"/>'">
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="tierName" class="control-label">
                             <g:message code="common.zone"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                          <div id="generalTierZoneList"></div>
                          <img id="generalTierZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="tierName" class="control-label">
                             <g:message code="common.vpc"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                         <div id="generalVPCZoneList"></div>
                         <img id="generalTierVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                    <div class="control-group">
                        <label for="tierNetworkOffering" class="control-label">
                            <g:message code="common.networkoffering"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="GeneralNetworkOfferingList" class="selectOption"></div>
                          <img id="generalTierNWOfferLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="tierGateway" class="control-label">
                             <g:message code="common.gateway"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="generalTierGateway" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.gateway.invalid"/>', placeHolder: '<g:message code="common.gateway"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.gateway.prompt"/>'"/>
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="tierNetmask" class="control-label">
                             <g:message code="common.netmask"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="generalTierNetmask" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.netmask.invalid"/>', placeHolder: '<g:message code="common.netmask"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.netmask.prompt"/>'"/>
                      </div>
                    </div>
                    <div class="control-group" id="generalAclDiv">
                        <label for="tierAcl" class="control-label">
                            <g:message code="common.ACL"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="generalTierAclList" class="selectOption"></div>
                          <img id="generalTierACLLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                        </div>
                    </div>
                    <div class="control-group" style="display: none;">Add Zone List Drop Down for Outer page use</div>
                    <div class="control-group" style="display: none;">Add VPC List Drop Down for Outer page use</div>
                </div>
            </div> 
        </div>
</form>
    </div>
    <div class="row-fluid">
        <div class="span8"><span style="display: none" class="offset3 require" id="addTierErrorMessage"><g:message code="user.createVM.required"/></span></div>
        <div class="span4">        
            <div class="pull-right">            
                <button data-dojo-type="dijit.form.Button" onclick="ListTierInfo.addTier();" class="primarybtn">
                    <g:message code="common.ok"/>
                </button>
                <button data-dojo-type="dijit.form.Button" onclick="ListTierInfo.cancelTier();" class="cancelbtn">
                    <g:message code="common.cancel"/>
                </button>
            </div>
         </div>
     </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="vpcTierListLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>