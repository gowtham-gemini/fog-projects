<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud" ><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/user/floatingIp/list"><g:message code="menu.user.floatingIpList"/></a></li>
            <li>/<li>
            <li><g:message code="common.view"/></li>
        </ul>
    </div>
</div>
<input type="hidden" id="selectedFloatingIpId">
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="floatingTabContainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.floatingIp.detail"/>" selected="true" onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3><g:message code="menu.user.floatingIp.routerOverview"/></h3>
                    </div></br>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="floatingIpId"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.ipAddress"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="floatingIpAddress"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.floatingInstance"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="floatingInstance"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.floatingNetworkId"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="floatingNetworkId"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.floatingIpPool"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="floatingIpPool"  ></span></div>
                                </div>
                            </div>                           
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.fixedPortId"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="fixedPortId"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.fixedIpAddress"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="fixedIpAddress"  ></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">

                        </div>
                    </div>
                <!--</div>-->
            </div>
        </div>
    </div>
</div>