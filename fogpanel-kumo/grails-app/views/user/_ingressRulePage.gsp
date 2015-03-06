<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/cloud/"><g:message code="menu.admin.cloud"/></a></li>
    <li>/</li>
    <li><a href="#/user/cloud/firewall"><g:message code="menu.admin.cloud.firewall"/></a></li>
    <li>/</li>
    <li id="currentFirewal"></li>
    <li>/</li>
    <li><g:message code="common.firewall.inbound"/></li>
  </ul>
</div>
</div>
<div class="row-fluid">   
<ul class="nav nav-tabs span12 customNav">
  <li class="active">
    <a href="#/user/cloud/ingress/"><g:message code="common.firewall.inbound"/></a>
  </li>
  <li>
      <a href="#/user/cloud/egress"><g:message code="common.firewall.outbound"/></a>
    </li>
 
</ul>
</div>
<div id="" class="new-user">
  <div class="row-fluid">    
    <!--<h4>Inbound</h4>-->
  </div> 
  <div class="row-fluid" id="">
      <form data-dojo-type="dijit.form.Form" id="userFirewallIngressForm" class="overflow-text">
    <div class="span12">
      <div class="span3 field-box">
          <label for="" class="control-label">
          <g:message code="common.firewall.protocol"/>
          <span class="require">*</span>
        </label>
         <select data-dojo-type="dijit.form.FilteringSelect"                 
                  id="securityGroupIngressProtocol" 
                  data-dojo-props="invalidMessage:'<g:message code="common.firewall.protocol.invalid"/>',
                  missingMessage:'<g:message code="common.firewall.protocol.prompt"/>',
                  promptMessage: '<g:message code="common.firewall.protocol.prompt"/>'"
                  value = "TCP"  onChange="UserSecurityIngressInfo.changeIngressLabel(this)">
            <option value="TCP" selected><g:message code="common.firewall.tcp"/></option>
            <option value="UDP"> <g:message code="common.firewall.udp"/></option>
            <option value="ICMP"><g:message code="common.firewall.icmp"/></option>
          </select>
      </div>
      <div class="span3 field-box">
          <label for="networkStratPort" id="userIngressStartPort" class="control-label">
          <g:message code="common.firewall.startPort"/>
          <span class="require">*</span>
        </label>
        <div id="adminSecurityIngressStartPortList">
        <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props=" 
                 invalidMessage: '<g:message code="common.firewall.startPort.invalid"/>', required: 'true',
                 placeHolder: '<g:message code="common.firewall.startPort.prompt"/>', constraints:{min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.startPort.invalid"/>',
                 promptMessage: '<g:message code="common.firewall.startPort.prompt"/>'"
                 name="displayText" id="securityGroupStratPort">
        </div>
          <div id="icmpTypeList" style="display: none">
          <select data-dojo-type="dijit.form.FilteringSelect" data-dojo-props="maxHeight: 100, required: false" id="icmpTypeWidget" value="-1" onchange="UserSecurityIngressInfo.showIcmpCode(this)">            
              
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
      <div class="span3 field-box"  id="endportContainer">
          <label for="networkEndPort" id="userIngressEndPort" class="control-label">
          <g:message code="common.firewall.endPort"/>
          <span class="require">*</span>
        </label>
         <div id="endPortList">
        <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="invalidMessage: '<g:message code="common.firewall.endPort.invalid"/>', required: 'true',
                 placeHolder: '<g:message code="common.firewall.endPort.prompt"/>', constraints:{min:-1,max:65535},  missingMessage:'<g:message code="common.firewall.endPort.invalid"/>',
                 promptMessage: '<g:message code="common.firewall.endPort.prompt"/>'"
                id="securityGroupEndPort">
         </div>
        <div id="icmpCodeList" style="display: none">
          <select data-dojo-type="dijit.form.FilteringSelect"
                  data-dojo-props="maxHeight: 100, required: false" id="icmpCodeWidget" value="0">      
            <option value="-1"><g:message code="common.firewall.empty"/></option>
          </select>
        </div>  
      </div>
      <div class="span2 field-box">
          <label for="networkCidr" class="control-label">
          <g:message code="common.firewall.cidr"/> (<g:message code="common.firewall.eg"/>: 0.0.0.0/0)<span class="require">*</span>
        </label>
        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="required:'true',
                              invalidMessage:'Invalid CIDR',
                              regExp: '[0-9/.]{0,16}',
                              placeHolder: '<g:message code="common.firewall.cidr.prompt"/>',
                              missingMessage:'<g:message code="common.firewall.cidr.invalid"/>',
                              promptMessage: '<g:message code="common.firewall.cidr.prompt"/>'"  
                              id="securityGroupCidr" name="networkCidr">
      </div>
       <div class="span1 field-box">
           <div class="row-fluid">
               <label for="networkCidr" class="control-label"><g:message code="common.add"/></label>
         <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn overflowLabel span12"
                  onclick="UserSecurityIngressInfo.addIngress()" id="ingressAddButton"><g:message code="common.add"/></button>
         <img id="ingressLoader" class="span7" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="<g:message code="common.reset"/>" height="20" width="20">
           </div>         
        </div>
    </div>
  </form>
  </div>
  <div class="row-fluid"><div class="span1"></div></div>
  <div class="row-fluid">
     <div id="securityGroupIngressGrid"></div>
  </div>
</div>