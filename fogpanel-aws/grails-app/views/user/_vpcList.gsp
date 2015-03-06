<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.container"/></a></li>
        <li>/</li>
        <li><g:message code="common.list"/></li>    
      </ul>
  </div>
</div>
<div class="table-wrapper products-table">       
  <div class="row-fluid" style="display: none;">
    <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/><span id="networkCurrencyValue"></span></div>
  </div>
  <div class="row-fluid">
      <div class="alert alert-danger" style="display: none;" id="vpcListAddRachedLimitMsg"><i class="icon-exclamation-sign"></i><g:message code="common.reachedLimit"/></div> 
  </div>
  <div class="row-fluid filter-block">
    <div class="pull-right">           
        <a class="btn-flat success new-product" onclick="ListVpc.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>
    </div>
    </div>
    <div class="row-fluid">
      <div id="userVpcList">  
        </div>
      <div class="alert alert-info hide" id="noVpcMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noVpc"/>
      </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="vpcLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcDeleteDialog" class="span4" title="<g:message code="common.delete"/>">
    <input id="currentVpcId" type="hidden">
    <div class="row-fluid">
        <div class="span2"><a class="span12"><img src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png"></a></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.vpcDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListVpc.deleteVpc()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListVpc.closeDeleteVpc()"><g:message code="common.cancel"/></button>
    </div>
</div>
    <div data-dojo-type="dijit.Dialog" id="vpcRestartDialog" class="span4" title="<g:message code="common.restart"/>">
    <input id="currentVpcId" type="hidden">
    <div class="row-fluid">
        <div class="span2"><a class="span12"><img src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_reboot_icon.png"></a></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.vpcRestartConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListVpc.restartVpc()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListVpc.closeRestartVpc()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpcListEditDialog" class="span4" title="<g:message code="common.edit"/>"> 
    <input type="hidden" id="currentVpcId">
    <div class="row-fluid container">
        <div class="span2"><a class="span12"><img src="images/edit.png"></a></div>
        <div class="span9">
            <div id="vpcListEditPageDiv" class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="editVpcName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        id="editListVpcName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                        promptMessage:'<g:message code="common.name.placeHolder"/>'">
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="editVpcDescription" class="control-label">
                             <g:message code="common.desc"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="editListVpcDescription" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.description.prompt"/>'"/>
                      </div>
                    </div>
                </div>
            </div> 
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListVpc.editVpc()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListVpc.cancelEdit()"><g:message code="common.cancel"/></button>
        </div>
    </div>
</div>