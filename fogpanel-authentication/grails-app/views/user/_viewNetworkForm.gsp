<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/virtualDataCenter" ><g:message code="menu.user.virtualDataCenter"/></a></li>
            <li>/<li>
            <li><a href="#/user/network/list"><g:message code="menu.user.networklist"/></a></li>
            <li>/<li>
            <li id="viewNetworkName"></li>
        </ul>
    </div>
</div>
<input type="hidden" id="selectedNetworkId">
<input type="hidden" id="selectedNetworkName">
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="networkTabContainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.network.networkDetail"/>" selected="true" id="networkContentPane" onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3><g:message code="menu.user.network.networkOverview"/></h3>
                    </div></br>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="networkNameInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="networkIdInfo"></span></div>
                                </div>
                            </div>
                    <!--                                <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                        <div class="grd-tbl-cell clm-first"><g:message code="common.projectId"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="networkProjectIdInfo"  ></span></div>
                                </div>
                            </div>-->
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.network.subnetsAssociated"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="networkSubnetInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.network.status"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="networkStatusInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.network.adminState"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="networkAdminStateInfo"  ></span></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.network.shared"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="networkSharedInfo"  ></span></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">

                        </div>
                    </div>
                <!--</div>-->
            </div>
        </div>
        <input type="hidden" id="networkAction">
        <div data-dojo-type="dijit/layout/ContentPane" id="subnetContentPane" title="<g:message code="menu.user.network.subnets"/>" onshow="SubnetInfo.populateValues()">
            <div class="row-fluid">
                <form id="listSubnetForm" data-dojo-type="dijit.form.Form">
                    <div class="table-wrapper products-table">       
                        <div class="row-fluid">
                            <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="networkCurrencyValue"></span></div>
                        </div>
                        <div class="row-fluid filter-block">
                            <div class="pull-right" id="networkViewSubnetListDiv" style="display: block">
                                <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="SubnetInfo.populateValues()">
                                <g:message code='common.refresh' />
                                </button>
                                <a class="btn-flat success new-product" onclick="SubnetInfo.redirectToCreateForm()">+ <g:message code="common.createSubnet"/></a>
                            </div>
                        </div>
                            <div class="row-fluid" style="margin-top: 60px">
                            <div id="userSubnetList">  
                            </div>
                            <div class="alert alert-info hide text_gray" id="noSubnetMessageBox" style="display: none">
                                <i class="icon-exclamation-sign text_gray"></i> 
                                <g:message code="user.network.noSubnetMsg"/>&nbsp;&nbsp;<a href="#/user/network/addSubnet"><g:message code="common.createOneNow"/></a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div data-dojo-type="dijit.Dialog" id="deleteSubnetDialog" title="<g:message code="user.subnet.deleteConfirm"/>" class="span4">
                <input type="hidden" id="currentSubnetId">
                  <p><g:message code="user.subnet.deleteConfirm.message"/></p>
                  <div class="row-fluid offset1">
                    <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="SubnetInfo.delete()"><g:message code="common.ok"/></button>
                    <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="SubnetInfo.closeDeleteSubnetDialog()"><g:message code="common.cancel"/></button>
                  </div>
            </div>
        </div>
         <div data-dojo-type="dijit/layout/ContentPane" id="portContentPane" title="<g:message code="menu.user.network.ports"/>" onshow="PortInfo.populateValues()">
            <div class="row-fluid">
                <form id="listPortForm" data-dojo-type="dijit.form.Form">
                    <div class="table-wrapper products-table">       
                        <div class="row-fluid">
                            <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="networkCurrencyValue"></span></div>
                        </div>
                        <div class="row-fluid filter-block">
                            <div class="pull-right">
                                <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PortInfo.populateValues()">
                                <g:message code='common.refresh' />
                                </button>                               
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <div id="userPortList">  
                                </div>
                            </div>
                            <div class="alert alert-info hide text_gray" id="noPortMessageBox" style="display: none; margin-top:20px">
                                <i class="icon-exclamation-sign text_gray"></i> 
                                <g:message code="user.network.noPortMsg"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div data-dojo-type="dijit.Dialog" id="portEditFormDialog" title="<g:message code="common.user.editPort"/>"  class="customDialgue">
                <div class="span5 dijitDialogueBackground">
                    <div class="row-fluid">
                        <input type="hidden" id="currentPortId">
                        <div class="span10">
                            <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="editnetworkPortForm">  
                                <div id="editNetworkPortFormPage">
                                    <div class="control-group"> 
                                        <label for="PortName" class="control-label">      
                                            <g:message code="common.name"/>
                                            <span class="require">*</span>
                                        </label>
                                        <div class="controls">
                                        <input type="text" 
                                               data-dojo-type="dijit.form.ValidationTextBox" 
                                               data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                               required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                               regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                               name="networkPortName" id="networkPortName">
                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                            <i class="icon-info-sign" id="portNameUpdateHelp"></i>
                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'portNameUpdateHelp', showDelay: 1"><g:message code="common.help.message.portNameUpdatemsg"/></div>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>      
                    </div>
                    <div>
                        <div class="control-group span3 pull-right"> 
                           <button id="upadateNetworkPortButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="PortInfo.update()"><g:message code="common.update"/></button>
                            <button id="cancelEditNetworkPortButon" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="PortInfo.closeEditPortDialog()"><g:message code="common.cancel"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

