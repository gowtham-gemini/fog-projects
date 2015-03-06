
<div class="row-fluid">
    <div class="span11" id="userNetworkEgressRuleAddPage">
        <div class="row-fluid hide_text" id="aclRuleCloudStackException">
            <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="aclRuleCloudstackExceptionMsg" class="span10"></span></div>
        </div>
        <form  data-dojo-type="dijit.form.Form" id="aclRuleForm" class="form-horizontal">	
            <div class="span12"></div>
            <div class="span12">
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="networkCidr" class="control-label">
                        <g:message code="common.ruleNum"/><span class="require">*</span>:
                    </label>
                    <div class="controls">
                      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage:'<g:message code="common.ruleNum.invalid"/>',
                                      regExp: '[0-9/.,]{0,160}',
                                      placeHolder: '<g:message code="common.ruleNum"/>',
                                      missingMessage:'<g:message code="common.ruleNum.invalid"/>',
                                      required: true,
                                      promptMessage: '<g:message code="common.ruleNum"/>'"  
                                      id="aclRuleNum" name="aclRuleNum">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="" class="control-label">
                        <g:message code="common.firewall.cidr"/><span class="require">*</span>:
                    </label>
                    <div class="controls">
                      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage:'<g:message code="common.invalid.cidr"/>',
                                      regExp: '[0-9/.,]{0,160}',
                                      required: true,
                                      placeHolder: '<g:message code="common.firewall.cidr.prompt"/>',
                                      missingMessage:'<g:message code="common.firewall.cidr.invalid"/>',
                                      promptMessage: '<g:message code="common.firewall.cidr.prompt"/>'"  
                                      id="aclCIDR" name="aclCIDR">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="" class="control-label">
                        <g:message code="common.action"/><span class="require">*</span>:
                    </label>
                    <div class="controls">
                        <select id="aclAction" data-dojo-type="dijit.form.Select">
                            <option value="Deny" selected><g:message code="common.deny"/></option>
                            <option value="Allow"> <g:message code="common.allow"/></option>                        
                        </select>
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="" class="control-label">
                        <g:message code="common.firewall.protocol"/><span class="require">*</span>:
                    </label>
                    <div class="controls">
                        <select name="aclProtocol" id="aclProtocol" data-dojo-type="dijit.form.Select" onchange="ACLInfo.showOptionOnProtocolBase(this.value)">
                            <option value="tcp" selected><g:message code="common.firewall.tcp"/></option>
                            <option value="udp"> <g:message code="common.firewall.udp"/></option>
                            <option value="icmp"><g:message code="common.firewall.icmp"/></option>
                            <option value="all"><g:message code="common.all"/></option>
                            <option value="protocolnumber"><g:message code="common.protocolNum"/></option>                        
                        </select>
                    </div>
                </div>                                               
            </div>
            <div class="span12">                
                <div class="span3 control-group field-box zone-cost-boxcont" id="aclStartPortDiv">
                    <label for="networkEgressStratPort" id="aclStartPort" class="control-label">
                      <g:message code="common.firewall.startPort"/><span class="require">*</span>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.startPort.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.startPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.startPort.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.startPort.prompt"/>'"
                             name="aclStartport" id="aclStartport">  
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="aclEndPortDiv">
                    <label for="networkEgressStratPort" id="aclEndPort" class="control-label">
                      <g:message code="common.firewall.endPort"/><span class="require">*</span>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.endPort.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.endPort"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.startPort.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.endPort"/>'"
                             name="aclEndport" id="aclEndport">  
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="aclIcmpTypeDiv">
                    <label for="aclIcmpType" id="userIngressStartPort" class="control-label">
                      <g:message code="common.firewall.icmpType"/><span class="require">*</span>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.icmpType.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.icmpType.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.icmpType.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.icmpType.prompt"/>'"
                             name="aclIcmpType" id="aclIcmpType">
                    </div>
                </div> 
                <div class="span3 control-group field-box zone-cost-boxcont" id="aclIcmpCodeDiv">
                    <label for="networkEgressIcmpCode" class="control-label">
                      <g:message code="common.firewall.icmpCode"/><span class="require">*</span>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.icmpCode.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.icmpCode.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.icmpCode.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.icmpCode.prompt"/>'"
                             name="aclIcmpCode" id="aclIcmpCode">
                    </div>
                </div>                
                <div class="span3 control-group field-box zone-cost-boxcont" id="aclTrafficTypeDiv">
                    <label for="networkEgressIcmpCode" class="control-label">
                      <g:message code="common.trafficType"/><span class="require">*</span>:
                    </label>                   
                    <div class="controls">
                        <select id="aclTrafficType" data-dojo-type="dijit.form.Select">
                            <option value="Ingress" selected><g:message code="common.ingress"/></option>
                            <option value="Egress"> <g:message code="common.egress"/></option>                        
                        </select>                    
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="aclProtocolNumberDiv" >
                    <div>
                    <label class="control-label">
                      <g:message code="common.protocolNum"/><span class="require">*</span>:
                    </label>
                    <div class="controls">                     
                        <input type="text" id="aclProtocolNumber" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: '<g:message code="common.protocolNum.invalid" />', placeHolder: '<g:message code="common.protocolNum" />', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.protocolNum.invalid"/>', promptMessage: '<g:message code="common.protocolNum" />'" />  
                    </div>
                </div>
                </div>
            </div>
            <div class="span12">      
                <div class="span9"></div>                               
                <div class="span3 control-group field-box">                                             
                    <!--<div class="row-fluid">-->
                        <!--<div class="span7"></div>-->
                        <!--<div class="span5">-->
                            <div class="pull-right hide_text" id="updateButtonContainer">
                                <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn overflowLabel span12" onclick="ACLInfo.updateRuleConfirm()" ><g:message code="common.update"/></button>
                            </div>
                            <div class="pull-right" id="addButtonContainer">
                                <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn overflowLabel span12" onclick="ACLInfo.addRule()" ><g:message code="common.add"/></button>
                                <img  class="span7" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="<g:message code="common.reset"/>" height="20" width="20">
                            </div> 
                            <div class="pull-right hide_text" id="cancelButtonContainer">
                                <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn overflowLabel span12" onclick="ACLInfo.editRuleClose()"><g:message code="common.cancel"/></button>
                            </div>
                                                       
                            
                        <!--</div>-->                                                
                    <!--</div>--> 
                </div>
            </div>
        </form>
    </div>
</div>
                    <div class="row-fluid"><div class="span12"></div></div>
<div class="row-fluid">
    <div id="aclRuleGridList">  
    </div>
    <div class="alert alert-info hide" id="noACLRuleMsgBox" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.user.noRuleMsg"/>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="aclAddRuleLoader" class="span6">
    <div class="row-fluid" id="processPaymentMessage" style="display: block">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="networkAclRuleDeleteDialog" class="span4">    
    <input id="currentAclRuleId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.networkAclDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ACLInfo.deleteAclRule()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ACLInfo.deleteAclRuleClose()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="networkAclRuleEditConfirmDialog" class="span4">    
    <input id="currentAclRuleId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.networkAclEditConfirm' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ACLInfo.editACLRule()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ACLInfo.editRuleClose()"><g:message code="common.cancel"/></button>
    </div>
</div>
