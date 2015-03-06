<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/vpc/list" ><g:message code="common.vpc"/></a></li>
            <li>/<li>
            <li><a href="#/user/routeTable/list"><g:message code="common.vpc.routeTables"/></a></li>
            <li>/<li>
            <li id="viewRouterName"></li>
        </ul>
    </div>
</div>
<input type="hidden" id="selectedRouterId">
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="routerTabContainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.network.routeTableDetails"/>" selected="true" id="routerContentPane" onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3><g:message code="common.vpc.routeTables"/></h3>
                    </div></br>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.vpc"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="vpcInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="routeTableIdInfo"></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">

                        </div>
                    </div>
                <!--</div>-->
            </div>
        </div>
<!--        <div data-dojo-type="dijit/layout/ContentPane" id="interfaceContentPane" title="<g:message code="menu.user.router.interfaces"/>" onshow="RouterInfo.populateInterfaceValues()">
            <div class="row-fluid">
                <form id="listrouterInterfaceForm" data-dojo-type="dijit.form.Form">
                    <div class="table-wrapper products-table">       
                        <div class="row-fluid">
                            <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="listrouterInterfaceCurrencyValue"></span></div>
                        </div>
                        <div class="row-fluid filter-block">
                            <div class="pull-right">
                                <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="RouterInfo.populateInterfaceValues()">
                                <g:message code='common.refresh' />
                                </button>
                                <a class="btn-flat success new-product" onclick="RouterInfo.interfaceCreateFormDialog()">+ <g:message code="common.router.addInterface"/></a>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div id="userRouterInterfaceList">  
                            </div>
                            <div class="alert alert-info hide text_gray" id="noRouterInterfaceMessageBox" style="display: none">
                                <i class="icon-exclamation-sign text_gray"></i> 
                                <g:message code="user.router.noInterfaceMsg"/>&nbsp;&nbsp;<a onClick="RouterInfo.interfaceCreateFormDialog()"><g:message code="common.addOneNow"/></a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>-->
    </div>
</div>

