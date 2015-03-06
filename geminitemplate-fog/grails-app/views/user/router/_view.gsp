<div class="row">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/virtualDataCenter" ><g:message code="menu.user.virtualDataCenter"/></a></li>
            <li>/<li>
            <li><a href="#/user/router/list"><g:message code="menu.user.routerlist"/></a></li>
            <li>/<li>
            <li id="viewRouterName"></li>
        </ul>
    </div>
</div>
<input type="hidden" id="selectedRouterId">
<div class="row">
    <div data-dojo-type="dijit/layout/TabContainer" id="routerTabContainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.user.network.routerDetail"/>" selected="true" id="routerContentPane" onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3><g:message code="menu.user.network.routerOverview"/></h3>
                    </div></br>
                    <div class="row">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="routerNameInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="routerIdInfo"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.router.status"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="routerStatusInfo"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="menu.user.router.externalNetwork"/></div>
                                    <div class="grd-tbl-cell clm-second"><g:message code="menu.user.router.connectedExternalNetwork"/> :<span id="externalNetwork"  ></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">

                        </div>
                    </div>
                <!--</div>-->
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="interfaceContentPane" title="<g:message code="menu.user.router.interfaces"/>" onshow="RouterInfo.populateInterfaceValues()">
            <div class="row">
                <form id="listrouterInterfaceForm" data-dojo-type="dijit.form.Form">
                    <div class="table-wrapper products-table">       
                        <div class="row">
                            <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="listrouterInterfaceCurrencyValue"></span></div>
                        </div>
                        <div class="row filter-block">
                            <div class="pull-right">
                                <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="RouterInfo.populateInterfaceValues()">
                                <g:message code='common.refresh' />
                                </button>
                                <a class="btn-flat success new-product" onclick="RouterInfo.interfaceCreateFormDialog()">+ <g:message code="common.router.addInterface"/></a>
                            </div>
                        </div>
                        <div class="row">
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
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="addRouterInterfaceDialog" title="<g:message code="user.router.addInterfaceTitle"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row">
            <!--<div class="span2"><img src='images/popup-icons/vm_snapshot_icon.png'/></div>-->
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="addRouterInterfaceForm">  
                    <div id="addRouterInterfaceFormPage">
                        <div class="control-group"> 
                            <label for="interfaceSubnetList" class="control-label">      
                                <g:message code="common.interfaceSubnetList"/>: <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <div id="" class="selectOption">
                                <div id="interfaceSubnetList" ></div> 
                                <div class="form_help_icon" style="top: 10; left: -15px;">
                                    <i class="icon-info-sign" id="interfaceSubnetListHelpMsg"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'interfaceSubnetListHelpMsg', showDelay: 1"><g:message code="common.help.message.interfaceSubnetList"/></div>
                                </div>
                             </div>
                            </div>
                        </div>
                        <div class="control-group"> 
                            <label for="InterfaceIpAddress" class="control-label">      
                                <g:message code="common.InterfaceIpAddress"/>:</label>
                            <div class="controls">
                                <input type="text" 
                                   data-dojo-type="dijit.form.ValidationTextBox" 
                                   data-dojo-props="invalidMessage: '<g:message code="common.InterfaceIpAddress.invalid"/>',
                                   placeHolder: '<g:message code="common.InterfaceIpAddress.prompt"/>', 
                                   regExp: dojox.validate.regexp.ipAddress, propercase: true" 
                                   name="Name" id="InterfaceIpAddress">  
                                <div class="form_help_icon" style="top: 0; left: -15px;">
                                    <i class="icon-info-sign" id="InterfaceIpAddressHelpMsg"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'InterfaceIpAddressHelpMsg', showDelay: 1"><g:message code="common.help.message.InterfaceIpAddress"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span2 pull-right"> 
                <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="RouterInfo.addInterface()"><g:message code="common.add"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="RouterInfo.closeAddInterfaceDialog()"><g:message code="common.cancel"/></button>
            </div>
           <!--<div class="span5"></div>-->
        </div>
    </div>
</div>
<input type="hidden" id="interfacePortId">
<input type="hidden" id="interfaceSubnetId">
<input type="hidden" id="interfaceRouterId">
<input type="hidden" id="interfaceNetworkId">
<div data-dojo-type="dijit.Dialog"  id="interfaceCreateLoader" class="customDialgue span6">
    <div class="row">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.interfaceCreateLoader1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.interfaceCreateLoader2' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="RouterInfo.closeAddInterfaceDialog()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
<div data-dojo-type="dijit.Dialog"  id="interfaceDeleteLoader" class="customDialgue span6">
    <div class="row">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.interfaceDeleteLoader1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.interfaceDeleteLoader2' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="RouterInfo.closeDeleteInterfaceDialog()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
<div data-dojo-type="dijit.Dialog" id="detachInterfaceAlert" title="<g:message code='user.router.detachInterface.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row">
        <div class="span10">
            <div class="span12"><g:message code='user.router.detachInterface.message' />  </div>            
        </div>        
        <div class="row">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="RouterInfo.deleteInterface()">
                <g:message code='common.delete' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="RouterInfo.closeDeateInterfaceDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
