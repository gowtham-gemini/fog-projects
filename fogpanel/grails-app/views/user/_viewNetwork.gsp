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
        </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">   
    <ul class="nav nav-tabs span12 customNav">
        <li class="active">
            <a id="networkSummaryLink"><g:message code="common.details"/></a>
        </li>
        <li>
            <a id="networkEgressLink"><g:message code="common.egressRule"/></a>
        </li>
        <li id="networkIPTab" style="display: none;">
            <a id="networkIPLink"><g:message code="common.ipAddress"/></a>
        </li>
    </ul>
</div>
<div class="row-fluid">
    <input id="viewNetworkId" type="hidden">
    <div class="row-fluid">
        <div class="network-title-info span4">
            <div class="grd-row-alt1-tbl">
                <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                    <div class="grd-tbl-cell clm-second"><span id="viewNetworkNameTop"  ></span></div>
                </div>
            </div>
            <div class="grd-row-alt2-tbl">
                <div class="grd-tbl-row">
                        <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                    <div class="grd-tbl-cell clm-second"><span id="viewNetworkZoneNameTop"></span></div>
                </div>
            </div>
        </div>
        <div class="span2">
            <div class="network-title-info" style="width: 100px;">
                <div class="row-fluid">
                    <div class="span12">
    <!--                    <div class="span5">
                            <img src="${resource(dir: 'images')}/network_icon_lrg.png" alt='<g:message code="common.network" />' height="auto" width="auto">
                        </div>-->
                        <div class="span3" style="margin-left: 10px; margin-top:15px;">
                            <a id="networkEdit" title="<g:message code="common.edit"/>">
                                <img src='images/edit.png' height="20" width="30"></img>
                            </a>
                        </div>
                        <div class="span4" style="margin-top:15px; margin-left: 20px;">
                            <a class="offset1" title="<g:message code="common.delete"/>" onclick="ViewNetwork.deleteNetworkShow()">
                                <img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'>
                            </a>
                        </div>
                   </div>
                </div>
            </div>
        </div>
        <div class="span6"></div>
    </div>
    <div class="row-fluid">
       <div class="span12"></div> 
    </div>
    <div class="row-fluid">
        <div class="span12" id="userViewNetworkPage">
            <div data-dojo-type="dijit/layout/TabContainer" id="networkTab" class="span12" style="overflow: visible;" tabStrip="true">
                <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true">
<!--                    <div class="row-fluid header">
                         <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
                    </div>-->
                    <form id="userViewNetworkZoneForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
                          <div class="row-fluid">
                            <div class="span6">
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkName"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.desc"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkDesc"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkReferenceId"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkoffering"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkOfferName"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.type"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkType"  ></span></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.gateway"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkGateway"  ></span></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.cidr"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkCidr"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.netmask"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkNetmask"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkDomain"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkDomain"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.state"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkState"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl" style="display: none;">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="viewNetworkZoneName"  ></span></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="span6">
                                
                            </div>
                          </div>
                    </form>
                </div>
                <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.egressRule"/>" onShow="NetworkEgress.populateValues()">
                  <g:render template="networkEgressRule" />
                </div>
                <div data-dojo-type="dijit/layout/ContentPane" id="networkIpAddressTab" title="<g:message code="common.ipAddress"/>" onShow="ViewNetworkIP.populateValues()">
                    <g:render template="networkIPAddressList" />
                </div>
                <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.instance"/>">
                    <div class="row-fluid" style="margin-top: 70px;">
                      <div id="userInstanceNetworkList"></div>
                      <div class="alert alert-info hide" id="noInstanceNetwork" style="display: none;">
                        <i class="icon-exclamation-sign"></i> 
                        <g:message code="common.user.noInstanceNetwork"/>
                      </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="networkDeleteDialog" class="span4">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.networkDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetwork.deleteNetwork()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetwork.deleteNetworkClose()"><g:message code="common.cancel"/></button>
    </div>
</div>
