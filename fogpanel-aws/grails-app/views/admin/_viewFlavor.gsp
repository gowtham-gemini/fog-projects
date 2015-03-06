<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/services"><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><a href="#/admin/flavors/list"><g:message code="menu.flavors"/></a></li>
            <li>/</li>
            <li ><g:message code="common.view"/></li>
            
        </ul>
    </div>
</div>

<div id="adminFlavorsDetails">
</div>

<div class="row-fluid" id="flavorsDetailsId">
    <div data-dojo-type="dijit/layout/TabContainer" id="" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.flavor.detail"/>" selected="true" onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3><g:message code="menu.user.Flavor.Overview"/></h3>
                    </div></br>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorNameInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.clockSpeed"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorClockSpeedInfo"></span>&nbsp;<g:message code="common.ghz"/></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorIdInfo"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.status"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorStatusInfo"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.vcpus"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorVcpusInfo"  ></span></div>
                                </div>
                            </div>                           
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.family"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorFamilyInfo"  ></span></div>
                                </div>
                            </div>

                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.instanceStore"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorInstanceStoreInfo"  ></span></div>
                                </div>
                            </div>

                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.memory"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorMemoryInfo"  ></span>&nbsp;<g:message code="common.gb"/></div>
                                </div>
                            </div>

                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.public"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorIsPublicInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.ebsOptimized"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="flavorEbsInfo"  ></span></div>
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