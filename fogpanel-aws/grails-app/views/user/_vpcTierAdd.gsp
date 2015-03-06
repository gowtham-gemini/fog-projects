<div class="row-fluid">
<form id="vpcAddTierPageForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
    <div class="span9">
            <div id="vpcAddTierPageDiv" class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="tierName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        id="tierName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{3,200}',
                        promptMessage:'<g:message code="common.name.placeHolder"/>'">
                      </div>
                    </div>
                    <div class="control-group">
                        <label for="tierNetworkOffering" class="control-label">
                            <g:message code="common.networkoffering"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="networkOfferingList" class="selectOption"></div>
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="tierGateway" class="control-label">
                             <g:message code="common.gateway"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="tierGateway" data-dojo-props="required: 'true',
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
                        id="tierNetmask" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.netmask.invalid"/>', placeHolder: '<g:message code="common.netmask"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.netmask.prompt"/>'"/>
                      </div>
                    </div>
                    <div class="control-group" id="aclDiv">
                        <label for="tierAcl" class="control-label">
                            <g:message code="common.ACL"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="tierAclList" class="selectOption"></div>
                        </div>
                    </div>
                    <div class="control-group" style="display: none;">Add Zone List Drop Down for Outer page use</div>
                    <div class="control-group" style="display: none;">Add VPC List Drop Down for Outer page use</div>
                </div>
            </div> 
        </div>
</form>
</div>
