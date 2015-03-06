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
            <li><g:message code="common.portForwarding"/></li>
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
    <li class="active" style="display: none;" id="portForwardingDiv">
        <a id="ipPortForwardingLink"><g:message code="common.portForwarding"/></a>
    </li>
    <li id="loadBalancingDiv" style="display: none;">
        <a id="ipLoadBalancingLink"><g:message code="common.loadBalancing"/></a>
    </li>
</ul>
</div>
<div class="row-fluid"> 
    <div class="row-fluid header">
    </div>
    <form id="userNetworkIPPortForwardingAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
        <div id="userNetworkIPPortForwardingAddPage">
            <div class="row-fluid hide_text" id="networkPFCloudstackException">
                <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="networkPFCloudstackExceptionMsg" class="span10"></span></div>
            </div>
            <div class="row-fluid">                            
                <div class="span4 control-group field-box zone-cost-boxcont">
                    <div class="span12">
                        <label for="ipPortForwardingPrivateStartPort" class="control-label" style="width: 120px !important;">
                        <g:message code="common.privateStartPort"/>
                        <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" required: 'true',
                             invalidMessage: '<g:message code="common.privateStartPort.invalid"/>',
                             placeHolder: '<g:message code="common.privateStartPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.privateStartPort.invalid"/>',
                             promptMessage: '<g:message code="common.privateStartPort.prompt"/>'"
                             name="ipPortForwardingPrivateStartPort" id="ipPortForwardingPrivateStartPort">
                    </div>
                    </div>
                </div>
                <div class="span4 control-group field-box zone-cost-boxcont">
                    <label for="ipPortForwardingPrivateEndPort" class="control-label" style="width: 120px !important;">
                      <g:message code="common.privateEndPort"/>
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" required: 'true',
                             invalidMessage: '<g:message code="common.privateEndPort.invalid"/>',
                             placeHolder: '<g:message code="common.privateEndPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.privateEndPort.invalid"/>',
                             promptMessage: '<g:message code="common.privateEndPort.prompt"/>'"
                             name="ipPortForwardingPrivateEndPort" id="ipPortForwardingPrivateEndPort">  
                    </div>
                </div> 
                <div class="span4 control-group field-box zone-cost-boxcont">
                    <label for="ipPortForwardingProtocol" class="control-label">
                        <g:message code="common.firewall.protocol"/>
                        <span class="require">*</span>
                    </label>
                    <div class="controls">
                        <select name="ipPortForwardingProtocol" id="ipPortForwardingProtocol" data-dojo-type="dijit.form.FilteringSelect">
                            <option value="TCP" selected><g:message code="common.firewall.tcp"/></option>
                            <option value="UDP"> <g:message code="common.firewall.udp"/></option>
                        </select>
                    </div>
                </div> 
            </div>
        <div class="row-fluid">
            <div class="span4 control-group field-box zone-cost-boxcont"  id="ipFirewallEndportDiv">
                <label for="ipPortForwardingPublicStartPort" id="" class="control-label" style="width: 120px !important;">
                      <g:message code="common.publicStartPort"/>
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" 
                             data-dojo-props="invalidMessage: '<g:message code="common.publicStartPort.invalid"/>', required: 'true',
                             placeHolder: '<g:message code="common.publicStartPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535},  missingMessage:'<g:message code="common.publicStartPort.invalid"/>',
                             promptMessage: '<g:message code="common.publicStartPort.prompt"/>'"
                            id="ipPortForwardingPublicStartPort">
                    </div>
                </div>
                <div class="span4 control-group field-box zone-cost-boxcont"  id="ipFirewallEndportDiv">
                    <label for="ipPortForwardingPublicEndPort" id="" class="control-label">
                      <g:message code="common.publicEndPort"/>
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" 
                             data-dojo-props="invalidMessage: '<g:message code="common.publicEndPort.invalid"/>', required: 'true',
                             placeHolder: '<g:message code="common.publicEndPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535},  missingMessage:'<g:message code="common.publicEndPort.invalid"/>',
                             promptMessage: '<g:message code="common.publicEndPort.prompt"/>'"
                            id="ipPortForwardingPublicEndPort">
                    </div>
                </div>  
                <div class="span4 field-box pull-right">                                          
                    <button type="button" data-dojo-type="dijit.form.Button" id="addVMIPPageButton" class="defaultbtn overflowLabel" onclick="ViewNetworkIPPortForwardingDetails.showVMList()"><g:message code="common.addVM"/></button>                                  
                </div>
            </div>             
        </div>
    </form>
</div>
<div class="row-fluid header"></div>
<div class="row-fluid" id="addIPButtonDiv" style="display: none;">
    <div class="span7"></div>
    <div class="span3"><div id="VMRequireMsg" class="hide_text"><p class="require"><g:message code="common.vmRequireList"/></p></div></div>
    <div class="span2">
        <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="ViewNetworkIPPortForwardingDetails.addPortForwarding()"><g:message code="common.apply"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPPortForwardingDetails.cancelVMGrid()"><g:message code="common.cancel"/></button>  
    </div>    
</div>
<div class="row-fluid" id="vmListContainer" style="display: none">        
    <div ><div id="userIPPortForwardVMList"></div></div>    
    <div class="alert alert-info hide" id="portforwardingNoVMList" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.user.noVMForNetworkIP"/>
    </div>    
    <input id="portForwarCurrentIp" type="hidden">
</div>
<div class="row-fluid" id="addIPButtonCopyDiv" style="display: none;">
    <div class="span7"></div>
    <div class="span3"><div id="VMRequireCopyMsg" class="hide_text"><p class="require"><g:message code="common.vmRequireList"/></p></div></div>
    <div class="span2">
        <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="ViewNetworkIPPortForwardingDetails.addPortForwarding()"><g:message code="common.apply"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPPortForwardingDetails.cancelVMGrid()"><g:message code="common.cancel"/></button> 
    </div>    
</div>
<div class="row-fluid" id="userIpExistList">
    <div id="userIpPortForwardingRuleList">  
    </div>
    <div class="alert alert-info hide" id="noPortforwardingMessageBox" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.user.noIPPortForwardingRule"/>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkPortForwardingLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="networkDeletePortForwardingDialog" class="span4">
    <input id="currentNetworkPortForwardingId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.PortForwardingDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPPortForwardingDetails.deletePortForwardingRule()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPPortForwardingDetails.deletePortForwardingRuleClose()"><g:message code="common.cancel"/></button>
    </div>
</div>