<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/admin/network/list"><g:message code="menu.admin.cloud.networks"/></a></li>
            <li>/</li>
            <li id="adminViewNetworkName"></li>
        </ul>
    </div>
</div>
<input type="hidden" id="adminSelectedNetworkId">
<input type="hidden" id="adminSelectedNetworkName">
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="adminNetworkTabContainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.network.networkDetail"/>" selected="true" id="adminNetworkContentPane" onshow="">
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
                                    <div class="grd-tbl-cell clm-second"><span id="adminNetworkNameInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="adminNetworkIdInfo"></span></div>
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
                                    <div class="grd-tbl-cell clm-second"><span id="adminNetworkSubnetInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.network.status"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="adminNetworkStatusInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.network.adminState"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="adminNetworkAdminStateInfo"  ></span></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.network.shared"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="adminNetworkSharedInfo"  ></span></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">

                        </div>
                    </div>
                <!--</div>-->
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="adminSubnetContentPane" title="<g:message code="menu.user.network.subnets"/>" onshow="SubnetInfo.populateValues()">
            <div class="row-fluid">
                <form id="adminListSubnetForm" data-dojo-type="dijit.form.Form">
                    <div class="table-wrapper products-table">       
                        <div class="row-fluid">
                            <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="adminNetworkCurrencyValue"></span></div>
                        </div>
                        <div class="row-fluid filter-block">
                            <div class="pull-right">
                                <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="SubnetInfo.populateValues()">
                                <g:message code='common.refresh' />
                                </button>
                                <a class="btn-flat success new-product" onclick="SubnetInfo.redirectToCreateForm()">+ <g:message code="common.createSubnet"/></a>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div id="adminSubnetList">  
                            </div>
                            <div class="alert alert-info hide text_gray" id="adminNoSubnetMessageBox" style="display: none">
                                <i class="icon-exclamation-sign text_gray"></i> 
                                <g:message code="user.network.noSubnetMsg"/>&nbsp;&nbsp;<a onclick="SubnetInfo.redirectToCreateForm()"><g:message code="common.createOneNow"/></a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div data-dojo-type="dijit.Dialog" id="adminDeleteSubnetDialog" title="<g:message code="user.subnet.deleteConfirm"/>" class="span4">
                <input type="hidden" id="currentSubnetId">
                  <p><g:message code="user.subnet.deleteConfirm.message"/></p>
                  <div class="row-fluid offset1">
                    <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="SubnetInfo.delete()"><g:message code="common.ok"/></button>
                    <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="SubnetInfo.closeDeleteSubnetDialog()"><g:message code="common.cancel"/></button>
                  </div>
            </div>
            <div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="adminDeleteSubnetLoader">
                <div class="row-fluid">
                  <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
                  <div class="span9">
                      <div class="span12"><p><g:message code='common.deleteSubnetInfo1' /></p></div>
                      <div class="span12" style="margin-left: 0"><p><g:message code='common.deleteSubnetInfo2' /></p></div>              
                  </div>          
                </div>
                <div class="row-fluid">        
                    <a class="btn-flat default" onclick="SubnetInfo.closeLoader()"><g:message code='common.clickHereToClose' /></a>
                </div>
            </div>
        </div>
         <div data-dojo-type="dijit/layout/ContentPane" id="adminPortContentPane" title="<g:message code="menu.user.network.ports"/>" onshow="PortInfo.populateValues()">
            <div class="row-fluid">
                <form id="adminListPortForm" data-dojo-type="dijit.form.Form">
                    <div class="table-wrapper products-table">       
                        <div class="row-fluid">
                            <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="adminNetworkCurrencyValue"></span></div>
                        </div>
                        <div class="row-fluid filter-block">
                            <div class="pull-right">
                                <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PortInfo.populateValues()">
                                <g:message code='common.refresh' />
                                </button>   
                                <a class="btn-flat success new-product" onclick="PortInfo.redirectToCreateForm()">+ <g:message code="common.createPort"/></a>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <div id="adminPortList">  
                                </div>
                            </div>
                            <div class="alert alert-info hide text_gray" id="adminNoPortMessageBox" style="display: none; margin-top:20px">
                                <i class="icon-exclamation-sign text_gray"></i> 
                                <g:message code="user.network.noPortMsg"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div data-dojo-type="dijit.Dialog" id="adminDeletePortDialog" title="<g:message code="admin.port.deleteConfirm"/>" class="span4">
                <input type="hidden" id="adminCurrentPortId">
                  <p><g:message code="admin.portConfirm.message"/></p>
                  <div class="row-fluid offset1">
                    <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="PortInfo.delete()"><g:message code="common.ok"/></button>
                    <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PortInfo.closeDeleteDialog()"><g:message code="common.cancel"/></button>
                  </div>
            </div>
            <div data-dojo-type="dijit.Dialog" id="adminPortEditFormDialog" title="<g:message code="common.user.editPort"/>"  class="customDialgue">
                <div class="span5 dijitDialogueBackground">
                    <div class="row-fluid">
                        <input type="hidden" id="adminCurrentPortId">
                        <div class="span10">
                            <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="adminEditnetworkPortForm">  
                                <div id="adminEditNetworkPortFormPage">
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
                                               name="adminNetworkPortName" id="adminEditPortName">
                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                            <i class="icon-info-sign" id="adminPortNameUpdateHelp"></i>
                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'adminPortNameUpdateHelp', showDelay: 1"><g:message code="common.help.message.portNameUpdatemsg"/></div>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>      
                    </div>
                    <div>
                        <div class="control-group span3 pull-right"> 
                           <button id="adminUpadateNetworkPortButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="PortInfo.update()"><g:message code="common.update"/></button>
                            <button id="adminCancelEditNetworkPortButon" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="PortInfo.closeEditPortDialog()"><g:message code="common.cancel"/></button>
                        </div>
                    </div>
                </div>
            </div>
            <div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="adminPortLoader">
                <div class="row-fluid">
                  <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
                  <div class="span9">
                      <div class="span12"><p><g:message code='common.portInfo1' /></p></div>
                      <div class="span12" style="margin-left: 0"><p><g:message code='common.portInfo2' /></p></div>              
                  </div>          
                </div>
                <div class="row-fluid">        
                    <a class="btn-flat default" onclick="PortInfo.closeLoader()"><g:message code='common.clickHereToClose' /></a>
                </div>
            </div>
        </div>
    </div>
</div>
