<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
    <li>/</li>
    <li><a href="#/admin/firewall"><g:message code="menu.admin.cloud.firewall"/></a></li>
    <li>/</li>
    <li id="egressFirewall"></li>
    <li>/</li>
    <li><g:message code="common.firewall.outbound"/></li>
  </ul>
</div>
</div>

<div class="row-fluid">   
<ul class="nav nav-tabs span12">
  <li>
    <a href="#/admin/firewall/ingress"  id="Inbound"><g:message code="common.firewall.inbound"/></a>
  </li>
  <li class="active">
      <a href="#/admin/firewall/egress"  id="Outbound"><g:message code="common.firewall.outbound"/></a>
    </li>
 
</ul>
</div>
<div id="pad-wrapper" class="new-user">
<!--  <div class="row-fluid header">    
    <h4>Egress</h4>
  </div> -->
  <div class="row-fluid header" id="adminSecurityGroupsEgressRule">
    <form data-dojo-type="dijit.form.Form" id="adminEgressSecurityGroupsForm">
    <div class="span12">
      <div class="span3 field-box">
          <label for="" class="control-label">
          <g:message code="common.firewall.protocol"/><span class="require">*</span>
        </label>        
         <select data-dojo-type="dijit.form.FilteringSelect"
                  data-dojo-props="placeHolder: '',invalidMessage:'<g:message code="common.firewall.protocol.invalid"/>', 
                  missingMessage:'<g:message code="common.firewall.protocol.prompt"/>', promptMessage: '<g:message code="common.firewall.protocol.prompt"/>'"
                  id="adminSecurityEgressProtocol" value = "TCP"
                  onChange="EgressInfo.changeEgressLabel(this)">
           <option  value="TCP" selected><g:message code="common.firewall.tcp"/></option>
            <option value="UDP"> <g:message code="common.firewall.udp"/></option>
            <option value="ICMP"><g:message code="common.firewall.icmp"/></option>
          </select>
      </div>
      <div class="span3 field-box">
          <label for="networkStratPort" id="adminEgressStartPort" class="control-label">
          <g:message code="common.firewall.startPort"/><span class="require">*</span>
        </label>
        
        <div id="adminSecurityEgressStartPortList">
          <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props=" 
                 invalidMessage: '<g:message code="common.firewall.startPort.invalid"/>', required: 'true',
                 placeHolder: '<g:message code="common.firewall.startPort.prompt"/>', constraints:{min:-1,max:65535,places:0}, 
                 missingMessage:'<g:message code="common.firewall.startPort.prompt"/>',
                 promptMessage: '<g:message code="common.firewall.startPort.prompt"/>'"
                 name="displayText" id="adminSecurityEgressStartPort">
        </div>
        
        <div id="egressIcmpTypeList" style="display: none">
          <select data-dojo-type="dijit.form.FilteringSelect"
                  data-dojo-props="maxHeight: 100, required: false" id="egressIcmpTypeWidget" value="-1" onchange="EgressInfo.showIcmpCode(this)">
            <option value="-1"><g:message code="common.firewall.all"/></option>
            <option value="0"><g:message code="common.firewall.echoReply"/></option>
            <option value="3"><g:message code="common.firewall.designationunreachable"/></option>
            <option value="4"><g:message code="common.firewall.sourceQueue"/></option>
            <option value="5"><g:message code="common.firewall.redirect"/></option>
            <option value="6"><g:message code="common.firewall.alternateHostAddress"/></option>
            <option value="8"><g:message code="common.firewall.echo"/></option>
            <option value="9"><g:message code="common.firewall.routerAdvertisement"/></option>
            <option value="10"><g:message code="common.firewall.routerSelection"/></option>
            <option value="11"><g:message code="common.firewall.timeExceeded"/></option>
            <option value="12"><g:message code="common.firewall.parameterProblems"/></option>
            <option value="13"><g:message code="common.firewall.timestamp"/></option>
            <option value="14"><g:message code="common.firewall.timestampReply"/></option>
            <option value="15"><g:message code="common.firewall.informationRequest"/></option>
            <option value="16"><g:message code="common.firewall.informationReply"/></option>
            <option value="17"><g:message code="common.firewall.addressMaskRequest"/></option>
            <option value="18"><g:message code="common.firewall.addressMaskReply"/></option>                            
          </select>
        </div>
      </div>
      <div class="span3 field-box" id="egressendportContainer">
         <label for="networkEndPort" id="adminEgressEndPort" class="control-label">
          <g:message code="common.firewall.endPort"/><span class="require">*</span>
        </label>
        <div id="egressEndPortList">
        <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="invalidMessage: '<g:message code="common.firewall.endPort.invalid"/>', required: 'true',
                 missingMessage:'<g:message code="common.firewall.endPort.prompt"/>',
                 promptMessage: '<g:message code="common.firewall.endPort.prompt"/>',
                 placeHolder: '<g:message code="common.firewall.endPort.prompt"/>'"
                id="adminSecurityEgressEndPort">
        </div>
        <div id="egressIcmpCodeList" style="display: none">
          <select data-dojo-type="dijit.form.FilteringSelect"
                  data-dojo-props="maxHeight: 100, required: false" id="egressIcmpCodeWidget" value="0">      
            <option value="-1">Empty</option>
          </select>
        </div> 
      </div>
      <div class="span2 field-box">
         <label for="networkCidr" class="control-label">
          <g:message code="common.firewall.cidr"/>  (<g:message code="common.firewall.eg"/>: 0.0.0.0/0)<span class="require">*</span>
        </label>
        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="required:'true',
                              invalidMessage:'Invalid CIDR',
                              regExp: '[0-9/.]{0,16}',
                              missingMessage:'<g:message code="common.firewall.cidr.invalid"/>',
                              promptMessage: '<g:message code="common.firewall.cidr.prompt"/>',
                              placeHolder: '<g:message code="common.firewall.cidr.prompt"/>'" id="adminSecurityEgressCidr" name="networkCidr">
      </div>
       <div class="span1 field-box">
         <!--<label for="" class="control-label"><g:message code="common.add"/></label>-->         
          <button type="button" data-dojo-type="dijit.form.Button"
                  onclick="EgressInfo.addEgress()" class="defaultbtn" id="firewallEgressButton">+<g:message code="common.add"/></button>
          <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="firewallEgressLoader" style="display: none"/>
        </div>
    </div>
  </form>
  </div>
  <div class="row-fluid">
    <div id="firewalEgressCollection"><div id="firewalEgressList"></div></div>
<!--    <img src='images/vmload.gif' alt='Loading' height='36' width='100' id="egressLoader" style="display: none"/>
    <div class="alert alert-info hide" id="noOutboundRuleMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
            You don't have any outbound rule yet. Create one now.
      </div>-->
  </div>
</div>