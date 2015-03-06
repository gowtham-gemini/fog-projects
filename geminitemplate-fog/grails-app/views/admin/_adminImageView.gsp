<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/services"><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><a href="#/admin/images/list"><g:message code="menu.images"/></a></li>
            <li>/</li>
            <li ><g:message code="common.view"/></li>
            
        </ul>
    </div>
</div>
<input type="hidden" id="viewImageId">
<div class="row">
    <div data-dojo-type="dijit/layout/TabContainer" id="" class="col-md-12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.image.detail"/>" selected="true" onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3><g:message code="menu.user.Image.Overview"/></h3>
                    </div></br>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageNameInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageIdInfo"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.status"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageStatusInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.size"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageSizeInfo"></span>&nbsp;<g:message code="common.mb"/></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.containerFormat"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageContatinerFormatInfo"  ></span></div>
                                </div>
                            </div>                           
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.diskFormat"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageDiskFormatInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.minDisk"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageMinDiskInfo"  ></span>&nbsp;<g:message code="common.gb"/></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.minRam"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageMinRamInfo"  ></span>&nbsp;<g:message code="common.gb"/></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.checksum"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageCheckSumInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.public"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageIsPublicInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.protected"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageIsProtectedInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.createdAt"/></div>
                                    <div class="grd-tbl-cell clm-second"> <span  id="imageCreatedInfo"  ></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">

                        </div>
                    </div>
                <!--</div>-->
            </div>
        </div>
    </div>
</div>