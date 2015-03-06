<div class="row-fluid">
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
            <li><a onclick="ViewNetwork.showIpTab()" id="networkIPListLink"><g:message code="common.ipAddress"/></a></li>
            <li>/</li>
            <li id="currentIP"></li>
        </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">   
<ul class="nav nav-tabs span12 customNav">
    <li class="active">
        <a id="ipSummaryLink"><g:message code="common.details"/></a>
    </li>
    <li>
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
<input id="ipNetworkId" type="hidden">
<input id="currentIPId" type="hidden">
<input id="currentNetworkId" type="hidden">
<div class="row-fluid">
    <div class="span12">
        <div class="network-title-info span5">
            <div class="grd-row-alt1-tbl">
                <div class="grd-tbl-row">
                    <div class="grd-tbl-cell clm-first"><g:message code="common.ipAddress"/></div>
                    <div class="grd-tbl-cell clm-second"><span id="ipAddressTop"></span></div>
                </div>
            </div>
            <div class="grd-row-alt2-tbl">
                <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.network"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="ipNetworkNameTop"></span></div>
                </div>
            </div>

            <div class="grd-row-alt1-tbl">
                <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                    <div class="grd-tbl-cell clm-second"><span id="ipZoneNameTop"  ></span></div>
                </div>
            </div>
            <div id="staticNatEnabled" style="display: none;" class="grd-row-alt2-tbl">
                <div class="grd-tbl-row">
                    <div class="grd-tbl-cell clm-first"><g:message code="common.staticNatEnabled"/></div>
                    <div class="grd-tbl-cell clm-second"><g:message code="common.vm"/>:<span id="staticNatVmName"></span></div>
                </div>
            </div>
        </div>
        <div class="span2" id="ipOtheActionInfo">
            <div class="network-title-info" style="width: 155px;" id="sourceNatEnableDiv">
                <div class="row-fluid">
                    <div class="span12">    
                        <div class="span3" id="ipEnableStaticNat" style="margin-top:10px; margin-left: 7px;">
                            <a title="<g:message code="common.enableStaticNat"/>" onclick="ViewNetworkIPDetails.enableStaticNatIPShow()">
                                <img style='width: 20px; height: 25px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img>
                            </a>
                        </div>
                        <div class="span3" id="ipDisableStaticNat" style=" margin-top:10px;">
                            <a title="<g:message code="common.disableStaticNat"/>" onclick="ViewNetworkIPDetails.disableStaticNatIPShow()">
                                <img style='width: 20px; height: 25px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img>
                            </a>
                        </div>
                        <div class="span4" style="margin-top:10px;" id="ipInfoDeleteIcon">
                            <a class="offset1" title="<g:message code="common.delete"/>" onclick="ViewNetworkIPDetails.releaseIPShow()">
                                <img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'>
                            </a>
                        </div>                        
                        <div class="span4" id="networkIpInfoEnableDiv" style="margin-top: 10px;margin-left: 10px;">
                            <a id="ipDetailVPNEnableOption" onclick="ViewNetworkIPDetails.enableVPNShow();"><img src='images/ip_enable_icon.png' title="<g:message code="common.network.vpnEnable.title"/>"></a>
                   </div>
                    <div class="span4" id="networkIpInfoDisableDiv" style="margin-top: 10px;margin-left: 10px;">
                            <a onclick="ViewNetworkIPDetails.disableVPNShow();"><img src='images/ip_disable_icon.png' title="<g:message code="common.network.vpnDisable.title"/>"></a>                            
                </div>
            </div>                       
        </div>
            </div>
            </div>                         
        <div class="span3">           
        </div>
    </div>
</div>
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="ipDetailsTabCointainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true" id="networkDetailContainer">
            <div class="row-fluid">
                <div class="span12">
<!--                    <div class="row-fluid header">
                         <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
                    </div>-->
                    <form id="userViewIPForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="grd-row-alt1-tbl" style="display: none;">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.ipAddress"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipAddress"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipAddressReferenceId"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkId"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="networkReferenceId"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkName"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipNetworkName"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.vlan"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipVLan"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.sourceNAT"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipSourceNat"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.staticNAT"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipStaticeNat"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.state"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipState"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl" style="display: none;">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipZoneName"  ></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="span6" id="disableStaticNatDiv">
                                   <div class="ip-address-tree">
                                        <div class="firewall"><button class="primary" onclick="ViewNetworkIPDetails.showFirewall()">View</button></div>
                                        <div class="primarybtn loadbalancer"><button class="primary" onclick="ViewNetworkIPDetails.showLoadBalancer()">View</button></div>
                                        <div class="primarybtn portforwarding"><button class="primary" onclick="ViewNetworkIPDetails.showPortForward()">View</button></div>
                                    
                                    </div>
                                    <!--img src="${resource(dir: 'images')}/ip_address_fc.png" alt='<g:message code="common.network" />' height="200" width="220"-->
                                    <!--div style="position:absolute; background-color: #ff0000; width:50px; height:20px; float: left; margin-left: 100px;">Firewall</div-->
                            </div>
                            <div class="span6" id="enableStaticNatDiv" style="display: none;">
                                   <div class="ip-address-staticNat">
                                        <div class="firewall"><button class="primary" onclick="ViewNetworkIPDetails.showFirewall()">View</button></div>
                                    </div>
                                    <!--img src="${resource(dir: 'images')}/ip_address_fc.png" alt='<g:message code="common.network" />' height="200" width="220"-->
                                    <!--div style="position:absolute; background-color: #ff0000; width:50px; height:20px; float: left; margin-left: 100px;">Firewall</div-->
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div  data-dojo-type="dijit/layout/ContentPane" id="ipFireWallTab"  title="<g:message code="common.fireWall"/>" onShow="ViewNetworkIPFirewallDetails.populateValues()">
            <g:render template="networkIPFirewall" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="ipPortForwarding"  title="<g:message code="common.portForwarding"/>" onShow="ViewNetworkIPPortForwardingDetails.populateValues()">
            <g:render template="networkIPPortForwarding" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="ipLoadBalancing"  title="<g:message code="common.loadBalancing"/>" onShow="ViewNetworkIPLoadBalancingDetails.populateValues(); ViewNetworkIPLoadBalancingDetails.cancelVMGrid();">
           <g:render template="networkIPLoadBalancing" /> 
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="ipVpnUsers"  title="<g:message code="common.vpnUsers"/>" onShow="ViewVPNUserInfo.populateValues();">
           <g:render template="vpnUsers" /> 
        </div>        
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="neworkReleaseIpDialog" title="<g:message code="common.ip.releaseIP"/>" class="span4">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code="common.user.ip.releaseNetwork"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.releaseIP()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.closeReleaseIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkIPEnableStaticNatDialog" title="<g:message code="common.enableStaticNat"/>" class="span4"> 
    <input type="hidden" id="sourceNatIPId">
    <div class="row-fluid container">
    <form id="networkIPEnableStaticNatForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
        <div class="span9" id="networkIPEnableStaticNatFormPage">
            <div class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                              <label for="ipPortForwardingVM" class="control-label">
                            <g:message code="common.instance"/>:
                        </label>
                        <div class="controls ">
                            <div id="staticNatVMListInViewIp"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="portForwardingVMIp" class="control-label">
                            <g:message code="common.ipAddress"/>:
                        </label>
                        <div class="controls ">
                            <div id="staticNatVMIpDivInViewIp"></div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.enableStaticNat()"><g:message code="common.ok"/></button>
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.closeEnableStaticNat()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </form>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkIPDisableStaticNatDialog" title="<g:message code="common.disableStaticNat"/>" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.disableStaticNatMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.disableStaticNat()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.closeDisableStaticNat()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkIpLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="" id="networkIpEnableVPN" class="span4" title="<g:message code="common.network.vpnEnable.title"/>">
    <div class="row-fluid">        
        <div class="span2"><img src='images/ip_enable_icon.png' title="<g:message code="common.network.vpnEnable.title"/>"></div>                                   
        <div class="span10">
            <p><g:message code="common.network.vpnEnable.message"/></p>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <div id="vpnEnableOKButton" class="span2">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.enableVPN()"><g:message code="common.ok"/></button>
            </div>         
                <img id="networkIPVPNEnableLoader" class="hide_text vpn_loader" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                <div class="span2"><button  id="vnpEnableCancelButton" type="button" class="cancelbtn offset3" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.cancelVPNDialogue()"><g:message code="common.cancel"/></button>
                </div>
        </div>   
        <div class="span6"></div>        
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="" id="networkIpDisableVPN" class="span6" title="<g:message code="common.network.vpnDisable.title"/>">
   <div class="row-fluid">        
        <div class="span2"><img src='images/ip_disable_icon.png' title="<g:message code="common.network.vpnDisable.title"/>"></div>                                   
        <div class="span10">
            <p><g:message code="common.network.vpnDisable.message"/></p>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
        <div id="vpnDisableOKButton"  class="span2">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.disableVPN();"><g:message code="common.ok"/></button>
        </div>        
        <img id="networkIPVPNDisableLoader" class="hide_text vpn_loader" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
       <div class="span2 offset2"> <button id="vnpDisableCancelButton"  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.cancelVPNDialogue()"><g:message code="common.cancel"/></button>
        </div>
        </div>
        <div class="span6"></div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="" id="networkIpEnableWarningMsgDialog" class="span6" title="<g:message code="common.vpn.warning"/>">
    <div class="row-fluid">        
        <!--<div class="span2"><img src='images/ip_enable_icon.png' title="<g:message code="common.network.vpnEnable.title"/>"></div>-->                                   
        <div class="span12">
            <div class="alert alert-error"><i class="icon-remove-sign"></i><g:message code="common.vpn.warningMsg"/></div>            
        </div>
    </div>
        <div class="row-fluid">
            <div class="span4">                   
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIPDetails.cancelVPNDialogue()"><g:message code="common.ok"/></button>
            </div>
        <div class="span8"></div>
        
    </div>
</div>
</div>
