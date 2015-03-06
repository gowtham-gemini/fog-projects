<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/list"><g:message code="common.vpc"/></a></li>
        <li>/</li>
        <li><a id="currentVpcNameLink" href=""><span id="currentVpcName"></span></a></li>
        <li>/</li>
        <li><a onclick="ViewVpc.showIpTab()" id="vpcIPAddressLink"><span><g:message code="common.ipAddress"/></span></a></li>
        <li>/</li>
        <li><span id="currentIpAddress"></span></li>
      </ul>
  </div>
</div>
<input id="currentVpcId" type="hidden">
<input id="currentIPId" type="hidden">
<input id="currentNetworkId" type="hidden">
<input id="currentNetworkReferenceId" type="hidden">
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
                        <div class="grd-tbl-cell clm-first"><g:message code="common.vpcName"/></div>
                        <div class="grd-tbl-cell clm-second"><span id="ipNetworkNameTop"></span></div>
                </div>
            </div>

            <div class="grd-row-alt1-tbl">
                <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                    <div class="grd-tbl-cell clm-second"><span id="ipZoneName"  ></span></div>
                </div>
            </div>
            <div id="staticNatEnabled" style="display: none;" class="grd-row-alt2-tbl">
                <div class="grd-tbl-row">
                    <div class="grd-tbl-cell clm-first"><g:message code="common.staticNatEnabled"/></div>
                    <div class="grd-tbl-cell clm-second"><g:message code="common.vm"/>:<a id="staticNatVMLink"><span id="staticNatVmName"></span></a></div>
                </div>
            </div>
        </div>
        <div class="span2" id="ipOtheActionInfo">
            <div class="network-title-info" style="width: 100px;" id="sourceNatEnableDiv">
                <div class="row-fluid">
                    <div class="span12">    
                        <div class="span3" id="ipEnableStaticNat" style="margin-top:10px; margin-left: 7px;">
                            <a title="<g:message code="common.enableStaticNat"/>" onclick="viewVpcIp.enableStaticNatShow()">
                                <img style='width: 20px; height: 25px;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img>
                            </a>
                        </div>
                        <div class="span3" id="ipDisableStaticNat" style=" margin-top:10px;">
                            <a title="<g:message code="common.disableStaticNat"/>" onclick="viewVpcIp.disableStaticNatIPShow()">
                                <img style='width: 20px; height: 25px; opacity:0.5;' src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img>
                            </a>
                        </div>
                        <div class="span4" style="margin-top:10px; display: none;" id="ipInfoDeleteIcon">
                            <a class="offset1" title="<g:message code="common.delete"/>" onclick="viewVpcIp.releaseIPShow()">
                                <img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'>
                            </a>
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
    <div data-dojo-type="dijit/layout/TabContainer" id="vpcIpDetailsTabCointainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true" id="vpcIpDetailContainer">
            <div class="row-fluid">
                <div class="span12">
<!--                    <div class="row-fluid header">
                         <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
                    </div>-->
                    <form id="userViewVpcIPForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
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
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.vpcId"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="networkReferenceId"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.vpcName"/></div>
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
<!--                                <div class="grd-row-alt2-tbl" style="display: none;">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="ipZoneName"  ></span></div>
                                    </div>
                                </div>-->
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.portForwarding"/>" onshow="vpcPFDetails.populateValues()" selected="true" id="vpcIpPortForwarding">
            <g:render template="vpcIPPortForwarding" />    
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="vpcIpLoadBalancing" title="<g:message code="common.loadBalancing"/>" onShow="vpcLBDetails.populateValues()">
            <g:render template="vpcIPLoadBalancing" />    
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="viewIpLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcIPPageEnableStaticNatDialog" title="<g:message code="common.enableStaticNat"/>" class="span4"> 
    <input type="hidden" id="currentEnableStaticNatIpId">
    <div class="row-fluid container">
    <form id="vpcIPPageEnableStaticNatForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
        <div class="span9" id="vpcIPPageEnableStaticNatFormPage">
            <div class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                        <label for="ipPortForwardingVM" class="control-label">
                            <g:message code="common.tier"/>:
                        </label>
                        <div class="controls ">
                            <div id="vpcIpPageTierList"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="portForwardingVMIp" class="control-label">
                            <g:message code="common.instance"/>:
                        </label>
                        <div class="controls ">
                            <div id="vpcIPPageTierVmList"></div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6"><span  class="hide_text require" id="viewIPenableStaticNatErrorMessage"><g:message code="user.createVM.required"/></span></div>
            <div class="span6">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="viewVpcIp.enableStaticNat()"><g:message code="common.ok"/></button>
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="viewVpcIp.closeEnableStaticNat()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </form>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpcIPPageDisableStaticNatDialog" title="<g:message code="common.disableStaticNat"/>" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.disableStaticNatMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="viewVpcIp.disableStaticNat()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="viewVpcIp.closeDisableStaticNat()"><g:message code="common.cancel"/></button>
    </div>
</div>
