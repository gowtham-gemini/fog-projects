<div class="row">
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
            <li><a id="portListLink"><g:message code="menu.user.portlist"/></a></li>
            <li>/<li>
            <li id="portName"><g:message code="common.view"/></li>
        </ul>
    </div>
</div>
<div id="viewPortFormLoader" style="margin-top: 50px; display: none" >
</div>    
<div class="row" id="viewPortFormDiv" style="display: none">
    <div class="table-wrapper products-table">
        <!--<div class="page-header">-->
            <div class="control-group">
                <h3><g:message code="menu.user.network.PortOverview"/></h3>
            </div></br>
            <div class="row">
                <div class="span6">
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="portNameInfo"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="portIdInfo"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.networkId"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="portNetworkIdInfo"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.projectId"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="portProjectIdInfo"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.fixedIp"/></div>
                            <div class="grd-tbl-cell clm-second"><span class="bold"><g:message code="common.ipAddress"/>: </span>
                                <span id="portIpAddressInfo"  ></span>, <span class="bold"><g:message code="common.subnetId"/>: </span>
                                <span id="portSubnetIdInfo"  ></span>
                            </div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.macAddress"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="portMacAddressInfo"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.status"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="portStatusInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.adminState"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="portAdminStatusInfo"  ></span></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                            <div class="grd-tbl-cell clm-first"><g:message code="common.attachedDevice"/></div>
                            <div class="grd-tbl-cell clm-second"><span class="bold"><g:message code="common.deviceId"/>: </span>
                                <span id="portDeviceIdInfo"></span>, <span class="bold"><g:message code="common.deviceOwner"/>: </span>
                                <span id="portDeviceOwnerInfo"  ></span></div>
                        </div>
                    </div>
                </div>
                <div class="span6">

                </div>
            </div>
        <!--</div>-->
    </div>
</div>