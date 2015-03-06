<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/admin/vpc/list"><g:message code="common.vpc"/></a></li>
        <li>/</li>
        <li><g:message code="common.view"/></li>    
        <li>/</li>
        <li><span id="currentVpcName"></span></li>
      </ul>
  </div>
</div>
<input type="hidden" id="currentAdminVpcId">
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="adminVpcTabCointainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true" id="adminVpcDetailContainer">
            <div class="row-fluid">
                <div class="span12">

                    <form id="adminViewIPForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                        <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                        <div class="grd-tbl-cell clm-second" ><span id="adminVpcName"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                        <div class="grd-tbl-cell clm-first"><g:message code="common.desc"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminVpcDesc"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                        <div class="grd-tbl-cell clm-first"><g:message code="common.user"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminVpcUser"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkDomain"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminVpcNetworkDomain"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.zone"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminVpcZone"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.cidr"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminvpcCidr"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.state"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminVpcState"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.restartRequired"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminVpcRestartRequred"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminVpcReferenceId"></span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.tier"/>" onshow="ViewAdminVpc.populateTierValues()" selected="true" id="adminVpcTierContainer">
            <g:render template="adminVpcTierList" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="adminvpcIpAddressTab" title="<g:message code="common.ipAddress"/>" onShow="ViewAdminVpc.populateIPListValues()">
                    <g:render template="adminVpcIPAddressList" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.S2SVPNGateways"/>" onShow="ViewAdminVpc.populateSite2siteValues()">
            <div class="row-fluid filter-block">
                <div class="pull-right" id="createVPNS2S" style="display: none;">
                </div>
                <div class="row-fluid" style="margin-top: 70px;">
                  <div id="adminS2sGatewayList"></div>
                  <div class="alert alert-info hide" id="noS2SVPNMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.admin.NOS2SVPN"/>
                  </div>
                </div>
            </div>
        </div>
        <div onShow="ViewAdminVpc.populateVPNConnection()" data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.vpnConnection"/>" selected="true">
            <div class="table-wrapper products-table">
                <div class="row-fluid filter-block">   
                    </div>
                <div class="row-fluid"> <div class="span12"></div> </div>
                <div class="row-fluid"> <div class="span12"></div> </div>                
                <div class="row-fluid">
                    <div class="row-fluid">
                      <div id="adminVpnConnectionList"></div>
                      <div class="alert alert-info hide" id="noAdminVpnConnectionMessage" style="display: none">
                        <i class="icon-exclamation-sign"></i> 
                        <g:message code="common.admin.noVpnConnection"/>
                      </div>
                    </div>
                </div>
            </div>
        </div>
        <div onShow="AdminVPCPrivateGateway.populateValues()" id="vpcPrivateGatewayAdminTab" data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.privateGateway"/>" selected="true">
            <div class="table-wrapper products-table">
                <div class="row-fluid filter-block">   
                    <div class="pull-right">
                        <button   id=""  data-dojo-type="dijit.form.Button" onclick="ViewAdminVpc.showAddVPCPriateGatewayDialog()" class="okbtn">
                          <g:message code="common.addVPCPrivateGateway"/>
                        </button>
                    </div>
                </div>                
                <div class="row-fluid">
                    <div class="row-fluid">
                      <div id="vpcAdminPrivateGatewayList"></div>
                      <div class="alert alert-info hide" id="noAdminVpcPrivateGatewayMessage" style="display: none">
                        <i class="icon-exclamation-sign"></i> 
                        <g:message code="common.noVpcAdminPrivateGateway"/>
                      </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="addVPCPriateGatewayDialog" title="<g:message code="common.addVPCPrivateGateway"/>" class="customDialog span6">
    <div class="row-fluid">   
        <div class="span2">                
                <a class="index_title_icons_network span12"></a>                
            </div>
<form id="addVPCPriateGatewayForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
    <div class="span9">
            <div id="addVPCPriateGatewayPage" class="form-horizontal">
                <div class="row-fluid hide_text" id="vpcPrivateGwayCloudstackException">
                    <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="vpcPrivateGwayCloudstackExceptionMsg" class="span10"></span></div>
                </div>
                <div class="row-fluid">
                    <div class="control-group">
                        <label for="physicalNetwork" class="control-label">
                            <g:message code="common.physicalNetwork"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="physicalNetworkList" class="selectOption"></div>
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="privateGatevlan" class="control-label">
                             <g:message code="common.vlan"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        id="privateGatevlan" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.value.invalid"/>', placeHolder: '<g:message code="common.vlan"/>',regExp:'[a-zA-Z0-9- ]{3,200}',
                        promptMessage:'<g:message code="common.vlan"/>'">
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="privateGatewayIp" class="control-label">
                             <g:message code="common.ip"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="privateGatewayIp" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.value.invalid"/>', placeHolder: '<g:message code="common.ip"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.ip"/>'"/>
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="privateGateway" class="control-label">
                             <g:message code="common.gateway"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="privateGateway" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.gateway.invalid"/>', placeHolder: '<g:message code="common.gateway"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.gateway.prompt"/>'"/>
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="privateGatewayNetmask" class="control-label">
                             <g:message code="common.netmask"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="privateGatewayNetmask" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.netmask.invalid"/>', placeHolder: '<g:message code="common.netmask"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.netmask.prompt"/>'"/>
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="privateGatewaySourceNat" class="control-label">
                             <g:message code="common.sourceNAT"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input  type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="privateGatewaySourceNat" name="agreement"/>
                      </div>
                    </div>
                    <div class="control-group" id="aclDiv">
                        <label for="privateGatewayAcl" class="control-label">
                            <g:message code="common.ACL"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="privateGatewayAclList" class="selectOption"></div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
</form>
</div>
<div class="row-fluid">
     <div class="pull-right">
       <button data-dojo-type="dijit.form.Button" onclick="ViewAdminVpc.addVPCPriateGateway();" class="primarybtn">
          <g:message code="common.ok"/>
       </button>
       <button data-dojo-type="dijit.form.Button" onclick="ViewAdminVpc.closeAddVPCPriateGatewayDialog();" class="cancelbtn">
          <g:message code="common.cancel"/>
       </button>
     </div>
 </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="vpcLoader" class="span6">
    <div class="row-fluid" id="processPaymentMessage" style="display: block">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="privateGatewayReplaceACLDialog" class="span4">
    <input id="currentVPCPrivateGatewayId" type="hidden">
    <div class="row-fluid">
        <div class="span12" style="margin-left: 0">              
            <div class="span2">
                <p><g:message code="common.ACL"/></p>
            </div>
            <div id="privateGatewayRepalceAClPage" class="span4">
                <div id="privateGatewayReplaceAclList" class="selectOption"></div>   
            </div>                    
        </div>
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="AdminVPCPrivateGateway.replaceAclTier()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AdminVPCPrivateGateway.closeReplaceAcl()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="privateGatewayDelete"  class="span4">
    
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.deletePrivateGateway"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="AdminVPCPrivateGateway.deletePrivateGateway()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AdminVPCPrivateGateway.closeDeletePrivateGateway()"><g:message code="common.cancel"/></button>
    </div>
</div>
