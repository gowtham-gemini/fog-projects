<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
             <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/admin/network/list"><g:message code="menu.admin.cloud.networks"/></a></li>
            <li>/</li>
            <li><a id="adminNetworkName"></a></li>
            <li>/<li>
            <li><a id="adminSubnetListLink"><g:message code="menu.user.subnetlist"/></a></li>
            <li>/<li>
            <li id="adminSubnetName"><g:message code="common.view"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div class="table-wrapper products-table">
        <!--<div class="page-header">-->
            <div class="control-group">
                <h3><g:message code="menu.user.network.subnetOverview"/></h3>
            </div></br>
            <div class="row-fluid">
                <div class="span6">
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetNameInfo"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetIdInfo"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkId"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetNetworkIdInfo"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.ipVersion"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetIpVersionInfo"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.cidr"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetCidrInfo"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.ipAllocationPools"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetIpAllocationInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.dhcpEnable"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetDhcpInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.gatewayIp"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetGatewayIpInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.additionalRoutes"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetAdditionalRoutesInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.dnsNameServer"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="adminSubnetDnsNameServerInfo"  ></span></span></div>
                        </div>
                    </div>
                </div>
                <div class="span6">

                </div>
            </div>
        <!--</div>-->
    </div>
</div>