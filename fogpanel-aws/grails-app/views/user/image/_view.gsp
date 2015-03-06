<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud" ><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/user/image/list"><g:message code="menu.user.imageList"/></a></li>
            <li>/<li>
            <li><g:message code="common.view"/></li>
        </ul>
    </div>
</div>
<input type="hidden" id="viewImageId">
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="imageTabContainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.image.detail"/>" selected="true" onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3><g:message code="menu.user.Image.Overview"/></h3>
                    </div></br>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageNameInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.description"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageDescriptionInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageIdInfo"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.status"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageStatusInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.architecture"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageArchitectureInfo"></span>&nbsp;<g:message code="common.mb"/></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.rootDeviceName"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageRootDeviceNameInfo"  ></span></div>
                                </div>
                            </div>                           
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.rootDeviceType"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageRootDeviceTypeInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.imageType"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageTypeInfo"  ></span>&nbsp;<g:message code="common.gb"/></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.hypervisor"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageHypervisorInfo"  ></span>&nbsp;<g:message code="common.gb"/></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.VMSnapshot"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageIsVMSnapshotInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.billingType"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageBillingTypeInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.public"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageIsPublicInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.virtualizationType"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageVirtualizationTypeInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.createdAt"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="imageCreatedInfo"  ></span></div>
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