<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>           
            <li><a href="#/user/home" id="vmDetailMainMenuName"><i class="icon-home"></i></a></li> 
            <li>/</li>            
            <li><a href="#/user/cloud/" class="overflowLabel" id="vmDetailSubMenu1"><g:message code="menu.user.cloud"/></a></li>
            <li>/<li>
            <li class="overflowLabel"><a href="#/user/cloud/userInstancePage" id="vmDetailSubMenu2"> <g:message code="menu.user.cloud.instance"/></a></li>
            <li>/</li>
            <li id="vmDetailSubMenu3"><g:message code="menu.user.vm.detail"/></li>
            <li id="vmDetailSeparator1"></li>
            <li id="vmNameMenu"></li>
            <li id="vmDetailSeparator2"></li>
            <li id="vmDetailSubMenu4"></li>            
            <li id="vmDetailSeparator3"></li>
            <li id="vmDetailSubMenu5"></li>
          </ul>
    </div>
</div>
<!--<div style="display: none" id="vmPageLoader">-->   
    <g:render template="instanceInfo" />
<!--</div>-->
<input type="hidden" id="currentVMID">
<div class="row-fluid" style="display: none;">   
    <ul class="nav nav-tabs span12">
        <li class="active">
            <a id="vmInfoDetailTag"><g:message code="menu.user.vm.detail"/></a>
        </li>
        <li>
            <a id="vmInfoStorageTag"><g:message code="menu.user.vm.storage"/></a>
        </li>
        <li>
            <a id="vmInfoNicTag"><g:message code="menu.user.vm.nics"/></a>
        </li>  
        <li id="aquireIPMenuItem">
            <a id="vmInfoAquireIPTag"><g:message code="common.secondaryIp"/></a>
        </li> 
    </ul>
</div>
<div class="row-fluid">
    <div class="span12" id="userViewNetworkPage">
        <div data-dojo-type="dijit/layout/TabContainer" class="span12" style="overflow: visible;" tabStrip="true">
            <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true">
                <div class="row-fluid">
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="detailVMName"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first"><g:message code="common.createdOn"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="detailVMCreatedDate"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first"><g:message code="common.osType"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="detailVMOSType"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl">
                        <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first"><g:message code="common.instanceID"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="detailVMRefeID"></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt1-tbl">
                        <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first"><g:message code="common.isEnabled"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="detailVMIsEnabled"  ></span></div>
                        </div>
                    </div>
                    <div class="grd-row-alt2-tbl" style="display: none;">
                        <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first"><g:message code="common.dynamicScale"/></div>
                            <div class="grd-tbl-cell clm-second"><span id="detailVMDynamicScale"></span></div>
                        </div>
                    </div>
                </div>
            </div>
            <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.vm.storage"/>" onshow="CurrentVMStorageInfo.populateValues()">
                <div class="row-fluid">
                    <g:render template="vmStoragePage" />
                </div>
            </div>
            <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.vm.nics"/>"  onshow="CurrentVMNicInfo.populateValues()">
                <div class="row-fluid">
                    <g:render template="vmNicPage" />
                </div>
            </div>
            <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.secondaryIp"/>" onshow="CurrentVMSecondaryIP.populateValues()">
               <div class="row-fluid">
                    <g:render template="vmSecondaryIPPage" />
                </div>
            </div>
        </div>
    </div>
</div>
