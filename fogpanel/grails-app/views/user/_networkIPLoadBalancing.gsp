<div class="row-fluid" style="display: none;">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud/"><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/user/network/"><g:message code="menu.user.services.network"/></a></li>  
            <li>/</li>
            <li><a id="currentNetworkName"></a></li>
            <li>/</li>
            <li><a id="networkIPListLink"><g:message code="common.ipAddress"/></a></li>
            <li>/</li>
            <li><a id="currentIP"></a></li>
            <li>/</li>
            <li><g:message code="common.loadBalancing"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">
<ul class="nav nav-tabs span12 customNav">
    <li>
        <a id="ipSummaryLink"><g:message code="common.details"/></a>
    </li>
    <li>
        <a id="ipFirewallLink"><g:message code="common.fireWall"/></a>
    </li>
    <li id="portForwardingDiv" style="display: none;">
        <a id="ipPortForwardingLink"><g:message code="common.portForwarding"/></a>
    </li>
    <li class="active" id="loadBalancingDiv" style="display: none;">
        <a id="ipLoadBalancingLink"><g:message code="common.loadBalancing"/></a>
    </li>
</ul>
</div>
<div class="row-fluid">
    <div class="row-fluid header">
         <!--<h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>-->
    </div>
    <!--<input id="currentIPId" type="hidden">-->
    <!--<input id="currentNetworkId" type="hidden">-->
    <form id="userNetworkIPLoadBalancingAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
        <div id="userNetworkIPLoadBalancingAddPage">    
            <div class="row-fluid hide_text" id="networkLBCloudstackException">
                <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="networkLBCloudstackExceptionMsg" class="span10"></span></div>
            </div>
            <div class="row-fluid">
            <div class="span12">
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="ipLoadBalancingName" class="control-label">
                        <g:message code="common.loadBalancingName"/>
                        <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage:'<g:message code="common.loadBalancingName.invalid"/>', required: 'true',
                                        regExp: '[a-zA-Z0-9-]{1,63}',
                                        placeHolder: '<g:message code="common.loadBalancingName.prompt"/>',
                                        missingMessage:'<g:message code="common.loadBalancingName.invalid"/>',
                                        promptMessage: '<g:message code="common.loadBalancingName.prompt"/>'"  
                                        id="ipLoadBalancingName" name="ipLoadBalancingName">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="loadBalancingPrivatePort" class="control-label">
                      <g:message code="common.loadBalancingPrivatePort"/>
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" required: 'true',
                             invalidMessage: '<g:message code="common.loadBalancingPrivatePort.invalid"/>',
                             placeHolder: '<g:message code="common.loadBalancingPrivatePort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.loadBalancingPrivatePort.invalid"/>',
                             promptMessage: '<g:message code="common.loadBalancingPrivatePort.prompt"/>'"
                             name="loadBalancingPrivatePort" id="loadBalancingPrivatePort">  
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont"  id="ipFirewallEndportDiv">
                    <label for="loadBalancingPublicport" id="" class="control-label">
                      <g:message code="common.loadBalancingPublicPort"/>
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" 
                             data-dojo-props="invalidMessage: '<g:message code="common.loadBalancingPublicPort.invalid"/>', required: 'true',
                             placeHolder: '<g:message code="common.loadBalancingPublicPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535},  missingMessage:'<g:message code="common.loadBalancingPublicPort.invalid"/>',
                             promptMessage: '<g:message code="common.loadBalancingPublicPort.prompt"/>'"
                            id="loadBalancingPublicPort">
                    </div>        
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="" class="control-label">
                        <g:message code="common.loadBalancingAlgorithm"/>
                        <span class="require">*</span>
                    </label>
                    <div class="controls">
                        <select name="loadBalancingAlgorithm" id="loadBalancingAlgorithm" data-dojo-type="dijit.form.FilteringSelect">
                            <option value="roundrobin">Round-robin</option>
                            <option value="leastconn">Least connections</option>
                            <option value="source">Source</option>
                        </select>
                    </div>
                </div>
            </div>
            </div>
            <div class="row-fluid">
                <!--<div class="span12">-->
                 <div class="span11 field-box"></div>
                    <div class="span1 field-box" id="addLBVMButtonDiv">
                        <!--<div>&nbsp;&nbsp;</div>-->
                        <!--<div class="row-fluid">-->                                
                        <button type="button" data-dojo-type="dijit.form.Button" id="addVMLBPageButton" class="defaultbtn overflowLabel" onclick="ViewNetworkIPLoadBalancingDetails.showLBVMList()" id="ipLoadBalancingAddButton"><g:message code="common.addVM"/></button>
                            
                        <!--</div>--> 
                    </div>
                <!--</div>-->
            </div>
        </div>
    </form>
</div>
<!--<div>&nbsp;&nbsp;</div>-->
<!--<div>&nbsp;&nbsp;</div>-->
<div class="row-fluid"></div>
<div class="row-fluid"></div>
<div class="row-fluid" id="addLBButtonDiv" style="display: none;">
    <div class="span7"></div>
    <div class="span3"><div id="LBVMRequireMsg" class="hide_text"><p class="require"><g:message code="common.vmRequireList"/></p></div></div>
    <div class="span2">
        <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="ViewNetworkIPLoadBalancingDetails.addLoadBalancing()"><g:message code="common.apply"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.cancelVMGrid()"><g:message code="common.cancel"/></button>
    </div>    
</div>
<div class="row-fluid" id="userLBVMContainer" style="display: none">   
    <div class="row-fluid header"></div>
    <div id="LBVMListContainer"><div id="UserLBVMList"></div></div>    
    <div class="alert alert-info hide" id="LBNoVMList" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.user.noVMForNetworkIP"/>
    </div>   
</div> 
<div class="row-fluid" id="addLBButtonDivCopy" style="display: none;">
    <div class="span7"></div>
    <div class="span3"><div id="LBVMRequireMsgCopy" class="hide_text"><p class="require"><g:message code="common.vmRequireList"/></p></div></div>
    <div class="span2">
        <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="ViewNetworkIPLoadBalancingDetails.addLoadBalancing()"><g:message code="common.apply"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.cancelVMGrid()"><g:message code="common.cancel"/></button>
    </div>    
</div>
<input type="hidden" id="currentLoadBalancingId">
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
        <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="ViewNetworkIPLoadBalancingDetails.addLoadBalancingAditionalVM()"><g:message code="common.addVM"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.cancelAddAdditionalVM()"><g:message code="common.cancel"/></button>
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
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.cancelRemoveVMFromLb()"><g:message code="common.cancel"/></button>
    </div>    
</div>
<div class="row-fluid" id="userLBExistContainer">
    <div class="row-fluid header">        
    </div>
    <div id="userIpLoadBalancingList">  
    </div>
    <div class="alert alert-info hide" id="noLoadBalancingMessageBox" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.user.noLoadBalancingRule"/>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkLoadBalancerLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="networkDeleteLBDialog" class="span4">
    <input id="currentLBId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.LBDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.deleteLoadBalancerRule()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.deleteLoadBalancerRuleClose()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="lbRemoveVMDialog" class="span4">
    <input id="lbCurrentVMId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.removeVMFromLb' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.removeVMFromLb()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.cancelRemoveVMFromLbDialog()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkEditLBDialog" class="span4"> 
    <div class="row-fluid container">
        <div class="span9">
            <div id="lbEditPageDiv" class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="ipLoadBalancingEditName" class="control-label">
                             <g:message code="common.loadBalancingName"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                             data-dojo-props="invalidMessage:'<g:message code="common.loadBalancingName.invalid"/>', required: 'true',
                             placeHolder: '<g:message code="common.loadBalancingName.prompt"/>',
                             missingMessage:'<g:message code="common.loadBalancingName.invalid"/>',
                             promptMessage: '<g:message code="common.loadBalancingName.prompt"/>'"  
                             id="ipLoadBalancingEditName" name="ipLoadBalancingEditName">
                      </div>
                    </div>
                    <div class="control-group">
                        <label for="loadBalancingEditAlgorithm" class="control-label">
                            <g:message code="common.loadBalancingAlgorithm"/>
                            <span class="require">*</span>
                        </label>
                      <div class="controls ">
                        <select name="loadBalancingEditAlgorithm" id="loadBalancingEditAlgorithm" data-dojo-type="dijit.form.FilteringSelect">
                            <option value="roundrobin">Round-robin</option>
                            <option value="leastconn">Least connections</option>
                            <option value="source">Source</option>
                        </select>
                      </div>
                    </div>
                </div>
            </div> 
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.editLBRule()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPLoadBalancingDetails.closeEditLB()"><g:message code="common.cancel"/></button>
        </div>
    </div>
</div>