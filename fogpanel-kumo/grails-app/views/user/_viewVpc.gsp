<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/list"><g:message code="common.vpc"/></a></li>
        <li>/</li>
        <li><g:message code="common.view"/></li>    
        <li>/</li>
        <li><span id="currentVpcName"></span></li>
      </ul>
  </div>
</div>
<input type="hidden" id="currentVpcId">
<div data-dojo-type="dijit.Dialog" class="full_loader" id="vpcLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div class="row-fluid" style="display: none;">
    <div class="span12">
        <div class="network-title-info" style="width: 100px;">
            <div class="row-fluid">
                <div class="span12">
                    <div class="span3" style="margin-left: 10px; margin-top:15px;">
                        <a onclick="ViewVpc.showEdit()" title="<g:message code="common.edit"/>">
                            <img src='images/edit.png' height="20" width="30"></img>
                        </a>
                    </div>
<!--                    <div class="span4" style="margin-top:15px; margin-left: 20px;">
                        <a class="offset1" title="<g:message code="common.delete"/>" onclick="ViewNetwork.deleteNetworkShow()">
                            <img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'>
                        </a>
                    </div>-->
               </div>
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">
    <div class="network-title-info span5">
        <div class="grd-row-alt1-tbl">
            <div class="grd-tbl-row">
                <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                <div class="grd-tbl-cell clm-second"><span id="viewVPCNameTop"  ></span></div>
            </div>
        </div>
        <div class="grd-row-alt2-tbl">
            <div class="grd-tbl-row">
                <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                <div class="grd-tbl-cell clm-second"><span id="viewVPCZoneNameTop"></span></div>
            </div>
        </div>
    </div>
        <div class="span2">
            <div class="network-title-info">
                <div class="row-fluid">
                    <div class="span12" style="margin-left: 13px;">                               
                        <a class="span4" onclick="ViewVpc.showRestartVpc();">
                            <img title="<g:message code="common.restart"/>" src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_reboot_icon.png"/>
                        </a>
                        <a class="span4" onclick="ViewVpc.showVPCEdit();">
                            <img title="<g:message code="common.edit"/>" src="images/edit.png"/>
                        </a>
                        <a class="span4" onclick="ViewVpc.showDeleteVpc();">
                            <img title="<g:message code="common.delete"/>" src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png">
                        </a>  
                    </div>
                   </div>
                </div>
            </div>
        </div> 
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="ipVpcTabCointainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" onshow="ViewVpc.vpcTopology()" title="<g:message code="common.configure"/>" selected="true" id="vpcDetailContainer">
            <div class="row-fluid">
                <div class="span12" style="display: none;">
<!--                    <div class="row-fluid header">
                         <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
                    </div>-->
                    <form id="userViewIPForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                        <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                        <div class="grd-tbl-cell clm-second" ><span id="vpcName"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                        <div class="grd-tbl-cell clm-first"><g:message code="common.desc"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcDesc"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkDomain"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcNetworkDomain"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.zone"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcZone"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.cidr"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcCidr"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.state"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcState"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.restartRequired"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcRestartRequred"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcReferenceId"></span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="span11">
                    <div class="row-fluid" style="margin-left: 15px;">
                        <div class="router-cont">
                                <h2><g:message code="common.router"/></h2>
                            <div class="router-info-boxlst tiers-info-desc">
                                    <!--<ul>-->
                                <div  class="span2 field_box">
                                    <a class="no_cursor">
                                        <div class="info-boxlist-value" id="vpcCIDRValue"></div>
                                        <div class="info-boxlist-title" ><g:message code="common.cidr"/></div> 
                                    </a>
                                </div>
                                <div class="span2 field_box">
                                        <a onclick="ViewVpc.showPrivateGateway()"  id="vpcPrivageGatewayLink">
                                           <div class="info-boxlist-value" id="privateGateway">0</div>
                                           <div class="info-boxlist-title" ><g:message code="common.privateGateway"/></div>  
                                        </a>
                                    </div>
                                    <div class="span2 field_box">
                                        <a onclick="ViewVpc.showIpaddress()" id="vpcPublicIpLink">
                                            <div class="info-boxlist-value" id="publicIp">0</div>
                                            <div class="info-boxlist-title" ><g:message code="common.publicIp"/></div>
                                        </a>
                                    </div>
                                    <div class="span2 field_box">
                                        <a onclick="ViewVpc.shows2sVpn()" id="vpcs2sVpnLink">
                                            <div class="info-boxlist-value" id="s2sVpn">0</div>
                                            <div class="info-boxlist-title" ><g:message code="common.s2sVpn"/></div>
                                        </a>
                                    </div>
                                    <div class="span2 field_box">
                                        <a onclick="ViewVpc.showNetworkACL()" id="vpcNetworkAclLink">
                                            <div class="info-boxlist-value" id="networkAcl">0</div>
                                            <div class="info-boxlist-title" ><g:message code="common.networkAcl"/></div>
                                        </a>
                                    </div>
                                <!--</ul>-->
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="span4"><div class="web-tier-info-cont row-fluid"></div></div><div class="span4"><div class="web-tier-info-cont row-fluid"></div></div><div class="span4"><div class="web-tier-info-cont row-fluid"></div></div>
                        </div>
                    </div>
                    <div class="row-fluid" id="vpcTopologyTierMenuList">
                        <div class="web-app-db-cont span12">
                            <div class="web-tier-cont span4">
                                 
                                <h2 class="webtier-title">
                                     <g:message code="common.webTier"/><span id="vpcTopoWebTierCount"></span>
                                     <a onclick="ViewVpc.addTierShow()" title="<g:message code="common.addTier"/>" style="color: #FFFFFF; float: right; margin-right: 14px; text-decoration: none">+</a>                                     
                                </h2>
                                <div class="web-tier-info-cont row-fluid" id="webTierList"></div>
                            </div>
                            <div class="app-tier-cont span4">                                
                                <h2 class="apptier-title">
                                     <g:message code="common.appTier"/><span id="vpcTopoAppTierCount"></span>
                                     <a onclick="ViewVpc.addTierShow()" title="<g:message code="common.addTier"/>" style="color: #FFFFFF; float: right; margin-right: 14px; text-decoration: none">+</a>  
                                </h2>
                                <div class="app-tier-info-cont row-fluid" id="appTierList"></div>
                            </div>
                            <div class="db-tier-cont span4">
                                <h2 class="dbtier-title">
                                     <g:message code="common.dbTier"/><span id="vpcTopoDBTierCount"></span>
                                     <a onclick="ViewVpc.addTierShow()" title="<g:message code="common.addTier"/>" style="color: #FFFFFF; float: right; margin-right: 14px; text-decoration: none">+</a>  
                                </h2>
                                <div class="db-tier-info-cont row-fluid" id="dbTierList"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div id="vpcTopologyTierLoader" class="hide_text">
                            <img src='images/vmload.gif' alt='' height='36' width='100'/> <br> <p><g:message code="common.loading"/></p>
                        </div>
                    </div>
<!--                    <div class="row-fluid">
                        <div class="span1"></div>
                        <div class="span3" id="webTierList"></div>
                        <div class="span3" id="appTierList"></div>
                        <div class="span3" id="dbTierList"></div>
                    </div>-->
                </div>
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.tier"/>" onshow="ViewVpc.populateTierValues()" selected="true" id="vpcTierContainer">
            <g:render template="vpcTierList" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="vpcIpAddressTab" title="<g:message code="common.ipAddress"/>" onShow="ViewVpc.populateIPListValues()">
                    <g:render template="vpcIPAddressList" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="vpcs2sVpnTab" title="<g:message code="common.S2SVPNGateways"/>" onShow="SiteToSiteVPN.list()">
            <div class="row-fluid filter-block">
                <div class="pull-right" id="createVPNS2S" style="display: none;">
                    <button   id=""  data-dojo-type="dijit.form.Button" onclick="SiteToSiteVPN.createS2SGatewayShow()" class="okbtn">
                      <g:message code="common.createS2SVPN"/>
                    </button>
                </div>
                <div class="row-fluid" style="margin-top: 70px;">
                  <div id="s2sGatewayList"></div>
                  <div class="alert alert-info hide" id="noS2SVPNMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.NOS2SVPN"/>
                  </div>
                </div>
            </div>
        </div>
        <div onShow="VPNConnection.populateValues()" id="vpcVpnConnectionTab" data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.vpnConnection"/>" selected="true">
            <div class="table-wrapper products-table">
                <div class="row-fluid filter-block">
                    <div class="pull-right span10 accquireip-form-boxcont" style="margin-top: 10px;">
                        <form class="form-horizontal" id="addVPNConnectionForm" data-dojo-type="dijit.form.Form">
                            <div class="row-fluid">  
                                <div  class="span1"></div>
                                <div id="addVPNConnectionPage" class="span9" style="position: absolute; margin-top: 10px;">
                                    <div class="control-group span6"> 
                                        <label for="vpnCustomerGateway" style="padding-top: 0px;" class="control-label">
                                            <g:message code='common.vpnCustomerGateway' />
                                            <span class="require">*</span>:
                                        </label>
                                        <div class="controls">
                                            <div id="vpnCustomerGatewayList"></div>
                                        </div>
                                    </div>
                                    <div class="span2" id=""><button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPNConnection.showCreateDialog()"><g:message code="common.createVpnConnection"/></button></div>
                                </div>
                                <div id="addVPNConnectionNoPage"  class="span9" style="display: none;position: absolute; margin-top: 10px;">
                                    <i class="icon-exclamation-sign"></i>
                                    <p style="color: red; margin: -17px 15px 10px;"><g:message code='common.vpnCustomerGatewayAllAdded' /></p>
                                </div>
                            </div>
                        </form> 
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="row-fluid">
                      <div id="vpnConnectionList"></div>
                      <div class="alert alert-info hide" id="noVpnConnectionMessage" style="display: none">
                        <i class="icon-exclamation-sign"></i> 
                        <g:message code="common.noVpnConnection"/>
                      </div>
                    </div>
                </div>
            </div>
        </div>
        <div onShow="VPCPrivateGateway.populateValues()" id="vpcPrivateGatewayTab" data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.privateGateway"/>" selected="true">
            <div class="table-wrapper products-table">
                <div class="row-fluid filter-block">
                </div>
                <div class="row-fluid"> <div class="span12"></div> </div>
                <div class="row-fluid"> <div class="span12"></div> </div>         
                <div class="row-fluid">
                    <div class="row-fluid">
                      <div id="vpcPrivateGatewayList"></div>
                      <div class="alert alert-info hide" id="noVpcPrivateGatewayMessage" style="display: none">
                        <i class="icon-exclamation-sign"></i> 
                        <g:message code="common.noUserPrivateGateway"/>
                      </div>
                    </div>
                </div>
            </div>
        </div>
        <div onShow="VPCNetworkACLInfo.populateValues()" id="vpcNetworkACLTab" data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.networkAcl"/>" selected="true">
            <div class="table-wrapper products-table">
                <input type="hidden" id="vpcNetworkAclId">
                <div class="row-fluid filter-block">
                    <div class="pull-right">
                        <a class="btn-flat success new-product" onclick="VPCNetworkACLInfo.showAddACLDialog()">+ <g:message code="common.add"/></a></div>
                </div>
                <div class="row-fluid"> 
                    <div class="alert alert-info hide" id="noVPCNetworkAclMsgBox" style="display: none">
                        <i class="icon-exclamation-sign"></i> 
                        <g:message code="common.message.noNetworkAcl"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div id="vpcNetworkAclListGrid"></div>      
                </div>
            </div>
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcNetworkAclDeleteDialog" class="span4">    
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.networkAclDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPCNetworkACLInfo.deleteAcl()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPCNetworkACLInfo.deleteAclClose()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcEditDialog" class="span4"> 
    <div class="row-fluid container">
        <div class="span9">
            <div id="vpcEditPageDiv" class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="editVpcName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        id="editVpcName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                        promptMessage:'<g:message code="common.name.placeHolder"/>'">
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="editVpcDescription" class="control-label">
                             <g:message code="common.desc"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="editVpcDescription" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.description.prompt"/>'"/>
                      </div>
                    </div>
                </div>
            </div> 
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.editVpc()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.cancelEdit()"><g:message code="common.cancel"/></button>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="s2svpnDeleteDialog"  class="span4">
    
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.removeS2SVPNMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="SiteToSiteVPN.deleteS2SVPN()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="SiteToSiteVPN.closeS2SGatewayShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpnConnectionDeleteDialog"  class="span4">
    <input type="hidden" id="currentVPNConnection">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.removeVPNConnectionMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPNConnection.deleteVPNConnection()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPNConnection.closeDeleteVPNCustomerGateway()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpnConnectionRestartDialog"  class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.restartVPNConnectionMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPNConnection.restartVPNConnection()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPNConnection.closeRestartVPNCustomerGateway()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="createS2SGatewayDialog"  class="span4">
    <input type="hidden" id="currentS2SVPNID">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.createS2SVPNMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="SiteToSiteVPN.creates2svpn()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="SiteToSiteVPN.closeCreateS2SGatewayShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="createVPNConnecionPagePage" class="span4" title=""> 
<div class="row-fluid container">
    <g:message code="common.createVPNConnectionConform"/>
</div>
<div class="row-fluid">
      <div class="span6">
          <img id="snapshotLoader" src="${resource(dir: 'images')}/loader.gif" alt="<g:message code="common.loading"/>" height="42" width="42" style="display: none">
          <button type="button" id="" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick = "VPNConnection.addVPNConnection()"><g:message code="common.ok"/></button>
        <button type="button" class="cancelbtn" id="" data-dojo-type = "dijit.form.Button" onclick="VPNConnection.closeCreateVPNConnection()"><g:message code="common.cancel"/></button>
      </div>
    <div class="span6"><span class="require" id="VPNConnectionCost"></span></div>
</div>
</div>

<div data-dojo-type="dijit.Dialog" id="createAclConnectionPage" class="span4" title="">
    <div class="row-fluid">
         <div class="new-user createVm"> 
            <div class="row-fluid form-wrapper">                
                <div class="createvm-form">
                    <div class="container">
                        <form data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                            <div class="row-fluid" id="createNetworkAclPage">
                                <div class="span12">                                    
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="networkAclVpc" class="control-label">
                                                <g:message code="common.vpc"/>: 
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls updatable elements">
                                                <div id="vpcNetworkAclVpcList" class="selectOption"></div>
                                                <img id="vpcNetworkAclVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="networkAclName" class="control-label">          
                                                <g:message code="common.name"/>: 
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                                id="vpcNetworkAclName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                                                promptMessage:'<g:message code="common.name.placeHolder"/>'">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="networkAclDescription"  class="control-label">         
                                                <g:message code="common.desc"/> :
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                                id="vpcNetworkAclDescription" data-dojo-props="required: 'true',
                                                invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                                                promptMessage:'<g:message code="common.description.prompt"/>'"/>

                                            </div>
                                        </div> 
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span7">
                                        </div>
                                        <div class="span5" id="">
                                            <button   id=""  data-dojo-type="dijit.form.Button" onclick="VPCNetworkACLInfo.add()" class="okbtn">
                                                <g:message code="common.ok"/>
                                            </button>
                                            <button   id=""  data-dojo-type="dijit.form.Button" onclick="VPCNetworkACLInfo.cancel()" class="cancelbtn">
                                                <g:message code="common.cancel"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>                
            </div>    
        </div>        
    </div>
</div>



<div data-dojo-type="dijit.Dialog" id="vpcInfoDeleteDialog" class="span4">
    <input id="currentVpcId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.vpcDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.deleteVpc()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.closeDeleteVpc()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpcInfoRestartDialog" class="span4">
    <input id="currentVpcId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.vpcRestartConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.restartVpc()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.closeRestartVpc()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpcInfoListEditDialog" class="span4"> 
    <input type="hidden" id="currentVpcId">
    <div class="row-fluid container">
        <div class="span9">
            <div id="vpcInfoEditPageDiv" class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="editVpcName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        id="editVpcInfoName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                        promptMessage:'<g:message code="common.name.placeHolder"/>'">
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="editVpcDescription" class="control-label">
                             <g:message code="common.desc"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="editVpcInfoDescription" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.description.prompt"/>'"/>
                      </div>
                    </div>
                </div>
            </div> 
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.editVpcInfo()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.cancelVPCEdit()"><g:message code="common.cancel"/></button>
        </div>
    </div>
</div>

