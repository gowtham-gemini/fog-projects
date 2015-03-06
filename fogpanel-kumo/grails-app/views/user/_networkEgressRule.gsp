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
            <li><g:message code="common.egressRule"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">   
    <ul class="nav nav-tabs span12 customNav">
        <li>
            <a id="networkSummaryLink"><g:message code="common.details"/></a>
        </li>
        <li class="active">
            <a id="networkEgressLink"><g:message code="common.egressRule"/></a>
        </li>
        <li id="networkIPTab" style="display: none;">
            <a id="networkIPLink"><g:message code="common.ipAddress"/></a>
        </li>
    </ul>
</div>
<div class="row-fluid">
    <div class="span11" id="userNetworkEgressRuleAddPage">
        <div class="row-fluid header">
             <!--<h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>-->
        </div>
        <form id="userNetworkEgressRuleAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
            <input id="viewEgressNetworkId" type="hidden">
            <div class="span12">
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="networkCidr" class="control-label">
                        <g:message code="common.firewall.sourceCIDR"/>:
                    </label>
                    <div class="controls">
                      <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage:'Invalid CIDR',
                                      regExp: '[0-9/.,]{0,160}',
                                      placeHolder: '<g:message code="common.firewall.cidr.prompt"/>',
                                      missingMessage:'<g:message code="common.firewall.cidr.invalid"/>',
                                      promptMessage: '<g:message code="common.firewall.cidr.prompt"/>'"  
                                      id="networkEgressCidr" name="networkEgressCidr">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont">
                    <label for="" class="control-label">
                        <g:message code="common.firewall.protocol"/>:
                    </label>
                    <div class="controls">
                        <select name="networkEgressProtocol" onChange='NetworkEgress.changeEgressRuleLabel(this)'  id="networkEgressProtocol" data-dojo-type="dijit.form.FilteringSelect">
                        <option value="TCP" selected><g:message code="common.firewall.tcp"/></option>
                        <option value="UDP"> <g:message code="common.firewall.udp"/></option>
                        <option value="ICMP"><g:message code="common.firewall.icmp"/></option>
                        <option value="ALL"><g:message code="common.all"/></option>
                        </select>
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="networkEgressStratPortDiv">
                    <label for="networkEgressStratPort" id="userIngressStartPort" class="control-label">
                      <g:message code="common.firewall.startPort"/>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.startPort.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.startPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.startPort.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.startPort.prompt"/>'"
                             name="networkEgressStratPort" id="networkEgressStratPort">  
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="networkEgressIcmpTypeDiv" style="display: none;">
                    <label for="networkEgressIcmpType" id="userIngressStartPort" class="control-label">
                      <g:message code="common.firewall.icmpType"/>
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.icmpType.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.icmpType.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.icmpType.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.icmpType.prompt"/>'"
                             name="networkEgressIcmpType" id="networkEgressIcmpType">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont"  id="networkEgressEndportDiv">
                    <label for="networkEndPort" id="" class="control-label">
                      <g:message code="common.firewall.endPort"/>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props="invalidMessage: '<g:message code="common.firewall.endPort.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.endPort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535},  missingMessage:'<g:message code="common.firewall.endPort.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.endPort.prompt"/>'"
                            id="networkEgressEndport">
                    </div>
                </div>
                <div class="span3 control-group field-box zone-cost-boxcont" id="networkEgressIcmpCodeDiv" style="display: none;">
                    <label for="networkEgressIcmpCode" class="control-label">
                      <g:message code="common.firewall.icmpCode"/>:
                    </label>
                    <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                             data-dojo-props=" 
                             invalidMessage: '<g:message code="common.firewall.icmpCode.invalid"/>',
                             placeHolder: '<g:message code="common.firewall.icmpCode.prompt"/>', constraints:{pattern:'#',min:-1,max:65535,places:0}, missingMessage:'<g:message code="common.firewall.icmpCode.invalid"/>',
                             promptMessage: '<g:message code="common.firewall.icmpCode.prompt"/>'"
                             name="networkEgressIcmpCode" id="networkEgressIcmpCode">
                    </div>
                </div>
                <div class="row-fluid span1 field-box pull-right">
                    <div class="row-fluid">
<!--                            <label for="" class="control-label"><g:message code="common.add"/></label>-->
                            <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn overflowLabel span12" onclick="NetworkEgress.addEgress()" id="networkEgressAddButton"><g:message code="common.add"/></button>
                            <img id="egressLoader" class="span7" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="<g:message code="common.reset"/>" height="20" width="20">
                    </div> 
                </div>
            </div>
        </form>
    </div>
</div>
<div class="row-fluid">
    <div id="userNetworkEgressRuleList">  
    </div>
    <div class="alert alert-info hide" id="noEgressRuleMessageBox" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.user.noNetworkEgressRule"/>
    </div>
</div>
