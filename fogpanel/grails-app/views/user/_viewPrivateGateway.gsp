<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/list"><g:message code="common.vpc"/></a></li>
        <li>/</li>
        <li><a id="currentVpcNameLink" href=""><span id="currentVpcName"></span></a></li>
        <li>/</li>
        <li><a id="currentVpcPrivateGatewayLink" onclick="VPCPrivateGateway.showPrivateGatweayTab()"><g:message code="common.privateGateway"/></a></li> 
        <li>/</li>
        <li><span id="vpcPrivateGatewayIp"></span></li>
        <li>/</li>
        <li><g:message code="common.view"/></li> 
      </ul>
  </div>
</div>
<input type="hidden" id="currentVpcId">
<input type="hidden" id="currentVpcPrivateGatewayId">

<div data-dojo-type="dijit.Dialog" class="full_loader" id="vpcPrivateGatewayLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="privateGatewayContainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true" id="">
            <div class="row-fluid">
                <div class="span12">
<!--                    <div class="row-fluid header">
                         <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
                    </div>-->
                    <form id="" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                        <div class="row-fluid">
                            <div class="span6">
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                        <div class="grd-tbl-cell clm-first"><g:message code="common.ip"/></div>
                                        <div class="grd-tbl-cell clm-second" ><span id="privategatewayIP"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                        <div class="grd-tbl-cell clm-first"><g:message code="common.vpc"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcName"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.netmask"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="netmask"  ></span></div>
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
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.vlan"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vlan"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.gateway"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="gateway"></span></div>
                                    </div>
                                </div>
                              <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.state"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="state"  ></span></div>
                                    </div>
                                </div>
                                <!--  <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="vpcReferenceId"></span></div>
                                    </div>
                                </div>-->
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.staticRoute"/>" onshow="VPCPrivateGateway.populateStaticRoutList()" selected="true" id="staticRoutesTab">
            <div class="row-fluid">
                <div class="row-fluid header">
                     <!--<h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>-->
                </div>
                <!--<input id="currentIPId" type="hidden">-->
                <!--<input id="currentNetworkId" type="hidden">-->
                <form id="staticRouteAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                    <div id="staticRouteAddFormAddPage">           
                        <div class="span12">
                            <div class="span5 control-group field-box zone-cost-boxcont">
                                <label for="staticRouteCidr" class="control-label">
                                    <g:message code="common.cidr"/>:
                                    <span class="require">*</span>
                                </label>
                                <div class="controls">
                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                     data-dojo-props="invalidMessage:'Invalid CIDR', required: 'true',
                                            regExp: '[0-9/.,]{0,160}',
                                        placeHolder: '<g:message code="common.firewall.cidr.prompt"/>',
                                        missingMessage:'<g:message code="common.firewall.cidr.invalid"/>',
                                        promptMessage: '<g:message code="common.firewall.cidr.prompt"/>'"  
                                        id="staticRouteCidr" name="ipFirewallCidr">
                                </div>
                            </div>
                            <div class="span3">
                                 <button type="button" data-dojo-type="dijit.form.Button" id="" class="defaultbtn overflowLabel" onclick="VPCPrivateGateway.addStaticRoute()" id=""><g:message code="common.add"/></button>
                                 <button  data-dojo-type="dijit.form.Button" onclick="VPCPrivateGateway.populateStaticRoutList()" class="okbtn">
                                     <i class="icon-refresh"></i> <g:message code='common.refresh' />
                                 </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="row-fluid"></div>
            <div class="row-fluid"></div>
            <div class="row-fluid" id="">
                <div class="row-fluid header">        
                </div>
                <div id="staticRouteList">  
                </div>
                <div class="alert alert-info hide" id="noStaticRouteMessageBox" style="display: none">
                  <i class="icon-exclamation-sign"></i> 
                  <g:message code="common.user.noStaticRoute"/>
                </div>
            </div> 
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="deleteStaticRouteDialog"  class="span4">
    <input type="hidden" id="currentStaticRoute">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.deleteStaticRoute"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPCPrivateGateway.deleteStaticRoute()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPCPrivateGateway.closeDeleteStaticRoute()"><g:message code="common.cancel"/></button>
    </div>
</div>