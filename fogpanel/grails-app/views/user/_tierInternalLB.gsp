<div class="row-fluid">
    <div class="row-fluid">
        <div class="span1"></div>
    </div>
    <div class="table-wrapper products-table">
        <div class="row-fluid">
            <div class="span12" id="addInternalLBButton">
                <div class="pull-right">
                    <a class="btn-flat success new-product" onClick="TierInternalLoadBalancerInfo.populateValues()"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>   
                    <a class="btn-flat success new-product" onClick="TierInternalLoadBalancerInfo.showAddInternalLB()" title="<g:message code='common.addInternalLB'/>"><g:message code="common.addInternalLB"/></a>        
                </div>
            </div>
        </div>
<div class="row-fluid" id="lbDetailPage" style="display: none;">
    <!--<input id="currentIPId" type="hidden">-->
    <!--<input id="currentNetworkId" type="hidden">-->
    <form class="form-horizontal">	
        <div id="">           
            <div class="span12">
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="internalLBName" class="control-label">
                         <g:message code="common.name"/>
                         <span class="require">*</span>
                     </label>
                         <div class="controls overflowLabel" style="width: 100px;">
                        <span style="margin-right: 100px;" id="internalLoadBalancingName"></span>
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="internalLBDescription"  class="control-label">         
                        <g:message code="common.desc"/> :
                    </label>
                    <div class="controls elements overflowLabel" style="width: 100px;" >
                        <span style="margin-right: 100px;" id="internalLoadBalancingDesc"></span>
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont"  id="ipFirewallEndportDiv">
                    <label for="internalLBAlgorithm" class="control-label">
                        <g:message code="common.loadBalancingAlgorithm"/>
                        <span class="require">*</span>
                    </label>
                    <div class="controls">
                        <span style="margin-right: 100px;" id="internalLoadBalancingAlgorithm"></span>
                    </div>        
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="internalLBSourcePort" id="" class="control-label">
                          <g:message code="common.sourcePort"/>
                          <span class="require">*</span>
                    </label>
                    <div class="controls">
                        <span style="margin-right: 100px;"  id="internalLoadBalancingInstancePort"></span>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="span3 control-group field-box zone-cost-boxcont">
                        <label for="internalLBInstancePort" id="" class="control-label">
                          <g:message code="common.instancePort"/>
                          <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <span style="margin-right: 100px;" id="internalLoadBalancingSourcePort"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
        <div class="row-fluid" id="LBAdditionalVMContainer" style="display: none">   
            <div class="row-fluid header"></div>
            <div id="LBAdditionalVMListContainer"><div id="additionalLBVMList"></div></div>    
            <div class="alert alert-info hide" id="additionalLBNoVMList" style="display: none">
              <i class="icon-exclamation-sign"></i> 
              <g:message code="common.user.noVMForNetworkIP"/>
            </div>   
        </div>
        <div class="row-fluid" id="addLBAdditionalVMButtonDiv" style="display: none;">
            <div class="span7"></div>
            <div class="span3"><div id="LBAdditionalVMRequireMsgCopy" class="hide_text"><p class="require"><g:message code="common.vmRequireList"/></p></div></div>
            <div class="span2">
                <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="TierInternalLoadBalancerInfo.addLoadBalancingAditionalVM()"><g:message code="common.addVM"/></button>
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="TierInternalLoadBalancerInfo.cancelAddAdditionalVM()"><g:message code="common.cancel"/></button>
            </div>    
        </div>
        <div class="row-fluid" id="lbRemoveVMListContainer" style="display: none">   
            <div class="row-fluid header"></div>
            <div id=""><div id="removeLBVMList"></div></div>    
        </div>
        <div class="row-fluid" id="removeLBAdditionalVMButtonDiv" style="display: none;">
             <div class="span7"></div>
            <div class="span3"></div>
             <div class="span2">
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="TierInternalLoadBalancerInfo.cancelRemoveVMFromLb()"><g:message code="common.cancel"/></button>
            </div>    
        </div>
        <div class="row-fluid" id="internalLBExistingDiv">
            <div id="tierInternalLBGrid"></div>
            <div class="alert alert-info hide overflowLabel" id="noInternalLBMsgBox" style="display: none">
                <i class="icon-exclamation-sign"></i> 
                <g:message code="common.user.noInternalLBMsg"/>
            </div>
        </div>
    </div>
</div>


