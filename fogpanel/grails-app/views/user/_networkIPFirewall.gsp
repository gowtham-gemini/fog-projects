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
            <li><g:message code="common.fireWall"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">
<ul class="nav nav-tabs span12 customNav">
    <li>
        <a id="ipSummaryLink"><g:message code="common.details"/></a>
    </li>
    <li class="active">
        <a id="ipFirewallLink"><g:message code="common.fireWall"/></a>
    </li>
    <li id="portForwardingDiv" style="display: none;">
        <a id="ipPortForwardingLink"><g:message code="common.portForwarding"/></a>
    </li>
    <li id="loadBalancingDiv" style="display: none;">
        <a id="ipLoadBalancingLink"><g:message code="common.loadBalancing"/></a>
    </li>
</ul>
</div>
<div class="row-fluid">
        <div class="row-fluid header">
             <!--<h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>-->
        </div>
    <form id="userNetworkIPFirewallAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
        <div class="span10" id="userNetworkIPFirewallAddPage">
            <input id="currentIPId" type="hidden">
            <div class="span12">
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="ipFirewallCidr" class="control-label">
                        <g:message code="common.firewall.sourceCIDR"/>:
                        <span class="require">*</span>
                    </label>
                    <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                         data-dojo-props="invalidMessage:'Invalid CIDR', required: 'true',
                                          regExp: '[0-9/.,]{0,160}',
                                          placeHolder: '<g:message code="common.firewall.cidr.prompt"/>',
                                          missingMessage:'<g:message code="common.firewall.cidr.invalid"/>',
                                          promptMessage: '<g:message code="common.firewall.cidr.prompt"/>'"  
                                          id="ipFirewallCidr" name="ipFirewallCidr">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="" class="control-label">
                        <g:message code="common.firewall.protocol"/>:
                        <span class="require">*</span>
                    </label>
                    <div class="controls">
                        <select name="ipFirewallProtocol" onChange='ViewNetworkIPFirewallDetails.changeEgressRuleLabel(this)'  id="ipFirewallProtocol" data-dojo-type="dijit.form.FilteringSelect">
                            <option value="TCP" selected><g:message code="common.firewall.tcp"/></option>
                            <option value="UDP"> <g:message code="common.firewall.udp"/></option>
                            <option value="ICMP"><g:message code="common.firewall.icmp"/></option>
                        </select>
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="ipFirewallStratPortDiv">
                    <label for="ipFirewallStratPort" id="userIngressStartPort" class="control-label">
                      <g:message code="common.firewall.startPort"/>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props="
                             invalidMessage: '<g:message code="common.firewall.startPort.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.startPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.startPort.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.startPort.prompt"/>'"
                             name="ipFirewallStratPort" id="ipFirewallStratPort">  
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="ipFirewallIcmpTypeDiv" style="display: none;">
                    <label for="ipFirewallIcmpType" id="userIngressStartPort" class="control-label">
                      <g:message code="common.firewall.icmpType"/>:
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.icmpType.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.icmpType.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.icmpType.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.icmpType.prompt"/>'"
                             name="ipFirewallIcmpType" id="ipFirewallIcmpType">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont"  id="ipFirewallEndportDiv">
                    <label for="networkEndPort" id="" class="control-label">
                      <g:message code="common.firewall.endPort"/>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props="invalidMessage: '<g:message code="common.firewall.endPort.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.endPort.prompt"/>', constraints:{min:-1,max:65535},  missingMessage:'<g:message code="common.firewall.endPort.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.endPort.prompt"/>'"
                            id="ipFirewallEndport">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="ipFirewallIcmpCodeDiv" style="display: none;">
                    <label for="ipFirewallIcmpCode" class="control-label">
                      <g:message code="common.firewall.icmpCode"/>:
                      <span class="require">*</span>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.icmpCode.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.icmpCode.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.icmpCode.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.icmpCode.prompt"/>'"
                             name="ipFirewallIcmpCode" id="ipFirewallIcmpCode">
                    </div>
                </div>
                <div class="row-fluid span1 field-box pull-right">
                    <div class="row-fluid">
                                <!--<label for="" class="control-label"><g:message code="common.add"/></label>-->
                                <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn overflowLabel span12" onclick="ViewNetworkIPFirewallDetails.addFirewall()" id="ipFirewallAddButton"><g:message code="common.add"/></button>
                                <img id="ipFirewallLoader" class="span7" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="<g:message code="common.reset"/>" height="20" width="20">
                    </div>
                </div>
                
            </div>
        </div>
    </form>
    
</div>
<div class="row-fluid">
    <div id="userIpFirewallRuleList">  
    </div>
    <div class="alert alert-info hide" id="noOfferMessageBox" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.user.noIPFirewallRule"/>
    </div>
</div>
