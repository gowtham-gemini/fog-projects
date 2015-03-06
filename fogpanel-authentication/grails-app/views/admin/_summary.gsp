<div class="row-fluid">   
<div class="span12 breadcrumbs">
<ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/users"><g:message code="common.users"/></a></li>
    <li>/</li>
    <li id="currentAccountName"></li>    
</ul>
</div>
</div>
<input type="hidden" id="currentAccountId">
<div class="row-fluid" id="userDetailsId">
    <div data-dojo-type="dijit/layout/TabContainer" id="" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.detail"/>" selected="true" onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3>Account Overview</h3>
                    </div></br>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="userNameInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.first.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="userFirstNameInfo"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.last.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="userLastNameInfo"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.accountLocked"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="userAccountLockedInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.state"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="userStateInfo"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.email"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="userEmailInfo"  ></span></div>
                                </div>
                            </div>                           
                        </div>
                    </div>
                <!--</div>-->
            </div>
        </div>
    </div>
</div>

   