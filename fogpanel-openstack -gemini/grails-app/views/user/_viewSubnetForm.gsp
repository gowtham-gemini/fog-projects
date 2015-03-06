<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/virtualDataCenter" ><g:message code="menu.user.virtualDataCenter"/></a></li>
            <li>/<li>
            <li><a href="#/user/network/list"><g:message code="menu.user.networklist"/></a></li>
            <li>/<li>
            <li><a id="networkName"><g:message code="menu.admin.cloud"/></a></li>
            <li>/<li>
            <li><a id="subnetListLink"><g:message code="menu.user.subnetlist"/></a></li>
            <li>/<li>
            <li id="subnetName"><g:message code="common.view"/></li>
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
                            <div class="grd-tbl-cell clm-second"><span id="subnetNameInfo"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="subnetIdInfo"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkId"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="subnetNetworkIdInfo"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.ipVersion"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="SubnetIpVersionInfo"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.cidr"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="subnetCidrInfo"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.ipAllocationPools"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="subnetIpAllocationInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.dhcpEnable"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="subnetDhcpInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.gatewayIp"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="subnetGatewayIpInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.additionalRoutes"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="subnetAdditionalRoutesInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.dnsNameServer"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="subnetDnsNameServerInfo"  ></span></span></div>
                        </div>
                    </div>
                </div>
                <div class="span6">

                </div>
            </div>
        <!--</div>-->
    </div>
</div>