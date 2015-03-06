<div class="row-fluid">
<form id="addInternalLBPageForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
    <div class="span9">
            <div id="addInternalLBPageDiv" class="form-horizontal">
                <div class="row-fluid hide_text" id="vpcInfoInternalLBCloudStackException">
                    <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="vpcInfoInternalLBCloudstackExceptionMsg" class="span10"></span></div>
                </div>
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="internalLBName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                        <div class="controls ">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                          id="internalLBName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                          promptMessage:'<g:message code="common.name.placeHolder"/>'">
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="internalLBDescription"  class="control-label">         
                            <g:message code="common.desc"/> :
                        </label>
                        <div class="controls elements">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            id="internalLBDescription" data-dojo-props="invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                            promptMessage:'<g:message code="common.description.prompt"/>'"/>

                        </div>
                    </div>
                    <div class="control-group">
                        <label for="internalLBAlgorithm" class="control-label">
                            <g:message code="common.loadBalancingAlgorithm"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <select name="internalLBAlgorithm" id="internalLBAlgorithm" data-dojo-type="dijit.form.FilteringSelect">
                                <option value="roundrobin">Round-robin</option>
                                <option value="leastconn">Least connections</option>
                                <option value="source">Source</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="internalLBSourcePort" id="" class="control-label">
                          <g:message code="common.sourcePort"/>
                          <span class="require">*</span>
                        </label>
                        <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.NumberTextBox" 
                                 data-dojo-props="invalidMessage: '<g:message code="common.sourcePort.invalid"/>', required: 'true',
                                 placeHolder: '<g:message code="common.sourcePort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535},  missingMessage:'<g:message code="common.sourcePort.invalid"/>',
                                 promptMessage: '<g:message code="common.sourcePort.prompt"/>'"
                                id="internalLBSourcePort">
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="internalLBInstancePort" id="" class="control-label">
                          <g:message code="common.instancePort"/>
                          <span class="require">*</span>
                        </label>
                        <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.NumberTextBox" 
                                 data-dojo-props="invalidMessage: '<g:message code="common.instancePort.invalid"/>', required: 'true',
                                 placeHolder: '<g:message code="common.instancePort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535},  missingMessage:'<g:message code="common.instancePort.invalid"/>',
                                 promptMessage: '<g:message code="common.instancePort.prompt"/>'"
                                id="internalLBInstancePort">
                        </div>
                    </div>
                    <div class="control-group" style="display: none;">Add Zone List Drop Down for Outer page use</div>
                    <div class="control-group" style="display: none;">Add VPC List Drop Down for Outer page use</div>
                </div>
            </div> 
        </div>
</form>
</div>
