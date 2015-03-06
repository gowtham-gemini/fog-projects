<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/list"><g:message code="common.vpc"/></a></li>
        <li>/</li>
        <li><a id="currentVpcNameLink" href=""><span id="currentVpcName"></span></a></li>
        <li>/</li>
        <li><a  onclick="ViewAdminVpc.showTierTab()"id="currentTierListLink" ><g:message code="common.tier"/></a></li>
        <li>/</li>
        <li><span id="currentTierName"></span></li>
      </ul>
  </div>
</div>
<input type="hidden" id="currentAdminTierId">
<input type="hidden" id="currentAdminTierReferenceId">
<input type="hidden" id="currentAdminLoadBalancingId">
<input type="hidden" id="currentAdminTierZoneId">
<input type="hidden" id="currentAdminTierVmID"/>
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="adminTierTabCointainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true" id="adminTierDetailContainer">
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
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierName"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.desc"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierDesc"></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.user"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierUser"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierReferenceId"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkoffering"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierOfferName" ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.type"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierType"></span></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.gateway"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierGateway"></span></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.cidr"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierCidr"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.netmask"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierNetmask"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkDomain"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierDomain"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt2-tbl">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="common.state"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierState"  ></span></div>
                                    </div>
                                </div>
                                <div class="grd-row-alt1-tbl" style="display: none;">
                                    <div class="grd-tbl-row">
                                            <div class="grd-tbl-cell clm-first"><g:message code="user.createVM.zone.label"/></div>
                                        <div class="grd-tbl-cell clm-second"><span id="adminViewTierZoneName"  ></span></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="span6">
                                
                            </div>
                          </div>
                    </form>
                </div>
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="adminInfoMenuTierInstanceContentPane" title="<g:message code="common.instance"/>" onshow="AdminTierInfo.populateTierVMInfo()">
           <g:render template="adminTierInstancePage" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane"  title="<g:message code="common.staticNAT"/>" onshow="AdminTierInfo.populateStaticNatInfo()">
           <g:render template="adminViewTierStatisNat" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.portForwarding"/>" onshow="AdminTierInfo.populatePFInfo()" selected="true" id="adminTierPortForwarding">
            <g:render template="adminTierPortForwarding" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="adminTierPublicLoadBalancer" title="<g:message code="common.publicLoadBalancing"/>" onShow="AdminTierInfo.populatePopulatePublicLBInfo()">
            <g:render template="adminTierPublicLB" />
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="adminTierInternalLoadBalancer" title="<g:message code="common.internalLoadBalancing"/>" onShow="AdminTierInfo.populateInternalLBValues()">
            <g:render template="adminTierInternalLB" />
        </div>
    </div>
</div>

