<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/vpc/list"><g:message code="common.vpc"/></a></li>
            <li>/</li>
            <li><g:message code="common.view"/></li>    
            <li>/</li>
            <li><span id="currentVpcName"></span></li>
        </ul>
    </div>
</div>
<input type="hidden" id="currentVpcId">

<div data-dojo-type="dijit.Dialog" class="full_loader" id="vpcLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>
<div class="row-fluid" style="display: none;">
    <div class="span12">
        <div class="network-title-info" style="width: 100px;">
            <div class="row-fluid">
                <div class="span12">
                    <div class="span3" style="margin-left: 10px; margin-top:15px;">
                        <a onclick="ViewVpc.showEdit()" title="<g:message code="common.edit"/>">
                            <img src='images/edit.png' height="20" width="30"></img>
                        </a>
                    </div>
<!--                    <div class="span4" style="margin-top:15px; margin-left: 20px;">
                        <a class="offset1" title="<g:message code="common.delete"/>" onclick="ViewNetwork.deleteNetworkShow()">
                            <img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'>
                        </a>
                    </div>-->
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">
    <div class="network-title-info span5">
        <div class="grd-row-alt1-tbl">
            <div class="grd-tbl-row">
                <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                <div class="grd-tbl-cell clm-second"><span id="vpcIdInfo"  ></span></div>
            </div>
        </div>
        <div class="grd-row-alt2-tbl">
            <div class="grd-tbl-row">
                <div class="grd-tbl-cell clm-first"><g:message code="common.network.region"/></div>
                <div class="grd-tbl-cell clm-second"><span id="vpcRegionInfo"></span></div>
            </div>
        </div>
    </div>
<!--        <div class="span2">
            <div class="network-title-info">
                <div class="row-fluid">
                    <div class="span12" style="margin-left: 13px;">                               
                        <a class="span4" onclick="ViewVpc.showRestartVpc();">
                            <img title="<g:message code="common.restart"/>" src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_reboot_icon.png"/>
                        </a>
                        <a class="span4" onclick="ViewVpc.showVPCEdit();">
                            <img title="<g:message code="common.edit"/>" src="images/edit.png"/>
                        </a>
                        <a class="span4" onclick="ViewVpc.showDeleteVpc();">
                            <img title="<g:message code="common.delete"/>" src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png">
                        </a>  
                    </div>
                   </div>
                </div>
            </div>-->
</div>
<div data-dojo-type="dijit/layout/TabContainer" style="overflow: visible;" tabStrip="true">
    <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" selected="true">
        <div class="row-fluid">
            <div class="span12">
<!--                    <div class="row-fluid header">
                     <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
                </div>-->
                <form id="" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="viewVpcName"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.cidr"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="viewVpcCidrBlock"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="viewVpcReferenceId"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.dhcpOptions"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="viewVpcDhcpOptionsId" ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.state"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="viewVpcState"  ></span></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.instanceTenancy"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="viewVpcInstanceTenancy"  ></span></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.isDefault"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="viewVpcIsDefault"  ></span></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.subnets"/>" onshow="ListTierInfo.populateValues()">
        <div class="row-fluid filter-block">
            <div id="currentVpcId"></div>
            <div class="span8">
                <div class="alert alert-danger errorMessage overflowLabel" id="tierListLimitReached" style="display: none">        
                    <i class="icon-exclamation-sign"></i><g:message code="common.reachedNetworkLimit"/>
                </div> 
            </div>
            <div class="span4">
                <div class="pull-right" id="tierListActionButtonCollection">
                    <input type="hidden" id="generalCurrentTierId">
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12"></div>
                <div class="span12"></div>        
            </div>
            <div class="row-fluid">
                <div id="vpcTierList"></div>
            </div>
            <div class="row-fluid">
                <div class="alert alert-info hide" id="noTierListMsg" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <span id="noTierMsg"><g:message code="common.user.noTier"/></span>
                </div>
            </div>
        </div>
    </div>
    <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.vpc.routeTables"/>" onShow="RouteTable.populateValues()">
        <div class="table-wrapper products-table">       
            <div class="row-fluid" style="display: none;">
                <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/><span id="networkCurrencyValue"></span></div>
            </div>
            <div class="row-fluid">
                <div class="alert alert-danger" style="display: none;" id="vpcListAddRachedLimitMsg"><i class="icon-exclamation-sign"></i><g:message code="common.reachedLimit"/></div> 
            </div>
            <div class="row-fluid filter-block">
                <div class="pull-right">           
                    <a class="btn-flat success new-product" onclick="RouteTable.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>
                    <!--<a class="btn-flat success new-product" id="listVpcAddButton" href="#/user/vpc/addVpc">+ <g:message code="common.add" /></a>-->
                </div>
            </div>
            <div class="row-fluid">
                <div id="userRouteTableList">  
                </div>
                <div class="alert alert-info hide" id="noRouteTableMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.vpc.noRouteTable"/>
                </div>
            </div>
        </div>
    </div>
</div>






