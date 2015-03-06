<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/admin/network/list"><g:message code="menu.admin.cloud.networks"/></a></li>
            <li>/</li>
            <li><a id="adminNetworkName"></a></li>
            <li>/<li>
            <li><a id="adminPortListLink"><g:message code="menu.user.portlist"/></a></li>
            <li>/<li>
            <li id="editPortBreadCrumbs"><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<input type="hidden" id="adminCurrentPortNetworkId">
<div class="row-fluid">
    <div class="span12" id="">
        <div class="row-fluid header">
            <h3 id="adminNetworkPorthead"></h3>   <div class="span2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>  <span id="addNetworkCurrencyValue"></span></div>
        </div>
        <form id="adminNetworkPortAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="networkPortContent">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group span4 horizontalcontent">
                            <input type="hidden" id="currentNetworkId" name="currentNetworkId" value="">
                            <label for="networkName" class="control-label">          
                                <g:message code="common.networkName"/>: <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="adminPortNetworkName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidMessage.networkName"/>', placeHolder: '<g:message code="common.networkName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                promptMessage:'<g:message code="common.networkName.promptMessage"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkNameHelp', showDelay: 1"><g:message code="common.help.message.portNetworkName"/></div>
                                </div>
                            </div>

                        </div>
                        <div class="control-group span4 horizontalcontent">
                            <label for="networkId" class="control-label">          
                                <g:message code="common.networkId"/>: <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="adminPortNetworkId" data-dojo-props="required: 'true'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkIdHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkIdHelp', showDelay: 1"><g:message code="common.help.message.portNetworkId"/></div>
                                </div>
                            </div>

                        </div>
                        <div class="control-group span4 horizontalcontent">
                            <label for="portName" class="control-label">          
                                <g:message code="common.portName"/>: <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="adminPortName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidMessage.adminPortName"/>', placeHolder: '<g:message code="common.adminPortName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="adminPortNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'adminPortNameHelp', showDelay: 1"><g:message code="common.help.message.portName"/></div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row-fluid"> 
                        <div class = "control-group span4 customDisk">
                            <label for="adminStateUp" class="control-label">              
                                <g:message code="common.adminState"/>:
                            </label>
                            <div class="controls updatable">
                                 <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                    data-dojo-props="checked: true" id="portAdminState">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="portAdminStateHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'portAdminStateHelp', showDelay: 1"><g:message code="common.help.message.portAdminState"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="control-group span4 horizontalcontent">
                            <label for="portDeviceId" class="control-label">          
                                <g:message code="common.deviceId"/>: </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="adminPortDeviceId" data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.portDeviceId"/>', placeHolder: '<g:message code="common.portDeviceId"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="portDeviceIdHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'portDeviceIdHelp', showDelay: 1"><g:message code="common.help.message.portDeviceId"/></div>
                                </div>
                            </div>

                        </div>
                        <div class="control-group span4 horizontalcontent">
                            <label for="portName" class="control-label">          
                                <g:message code="common.deviceName"/>: </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="adminPortDeviceName" data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.adminPortDeviceName"/>', placeHolder: '<g:message code="common.adminPortDeviceName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="adminPortDeviceNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'adminPortDeviceNameHelp', showDelay: 1"><g:message code="common.help.message.adminPortDeviceName"/></div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span9"></div>
                        <div class="span3">
                            
                            <div id="adminPortCancelButtonDiv" class="span4 pull-right">
                                <button id="networkPortCancelButton"  data-dojo-type="dijit.form.Button" onclick="PortInfo.cancel()" class="cancelbtn">
                                    <g:message code="common.cancel"/>
                                </button>
                            </div>
                            <div id="adminPortAddButtonDiv" class="span2 pull-right" style="display: block;" >
                                <button   id="networkPortAddButton"  data-dojo-type="dijit.form.Button" onclick="PortInfo.add()" class="okbtn">
                                    <g:message code="common.ok"/>
                                </button>
                            </div>        
                            <div id="adminPortUpdateButtonDiv" style="display: none;" class="span2 pull-right">
                                <button   id="networkPortUpdateButton"  data-dojo-type="dijit.form.Button" onclick="PortInfo.update()" class="okbtn">
                                    <g:message code="common.apply"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="adminCreatePortLoader">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createPortInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createPortInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a onclick = "PortInfo.gotoList()" class="btn-flat default" ><g:message code='common.gotoPortList' /></a>
    </div>
</div>
    
