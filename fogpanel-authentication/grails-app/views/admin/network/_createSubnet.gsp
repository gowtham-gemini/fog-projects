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
            <li><a id="adminSubnetListLink"><g:message code="menu.user.subnetlist"/></a></li>
            <li>/<li>
            <li id="editSubnetBreadCrumbs"><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<input type="hidden" id="adminSubnetNetworkId">
<input type="hidden" id="adminCurrentSubnetId">
<div class="row-fluid">
    <div class="span12" id="">
        <div class="row-fluid header">
            <h3 id="adminSubnetPagehead"></h3>   
        </div>
        <form id="adminSubnetAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="adminSubnetContent">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group span4 horizontalcontent">
                            <label for="subnetName"  class="control-label">         
                                <g:message code="common.subnetName"/> :<span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="adminNetworkSubnetName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.subnetName.invalid"/>', placeHolder: '<g:message code="common.subnetName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                promptMessage:'<g:message code="common.subnetName.promptMessage"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="subnetNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'subnetNameHelp', showDelay: 1"><g:message code="common.help.message.subnetName"/></div>
                                </div>
                            </div>
                        </div> 
                        <div class="control-group span4 horizontalcontent">
                            <label for="networkAddress"  class="control-label">         
                                <g:message code="common.networkAddress"/>: <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="required:'true', regExp: '[0-9/.,]{0,18}', invalidMessage:'<g:message code="common.networkAddress.invalid"/>',
                                placeHolder: '<g:message code="common.networkAddress"/>', 
                                promptMessage:'<g:message code="common.networkAddress.promptMessage"/>'" id="adminNetworkSubnetAddress">  
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkAddressHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkAddressHelp', showDelay: 1"><g:message code="common.help.message.networkAddress"/></div>
                                </div>
                            </div>
                        </div> 
                    </div>
                    <div class="row-fluid">      
                        <div class = "control-group span4 customDisk">
                            <label for="ipVersion" class="control-label">              
                                <g:message code="common.ipVersion"/>: 
                                <span class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <select class="customSelectWidth" data-dojo-type="dijit.form.Select" id="adminSubnetIpVersions">
                                    <option value=4 selected="selected">IPv4</option>
                                    <option value=6>IPv6</option>
                                </select>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="ipVersionHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ipVersionHelp', showDelay: 1"><g:message code="common.help.message.ipVersion"/></div>
                                </div>
                            </div>
                        </div>
                        <div class = "control-group span4 customDisk">
                            <label for="gatewayIp" class="control-label">              
                                <g:message code="common.gatewayIp"/>:
                            </label>
                            <div class="controls updatable">
                                 <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="invalidMessage:'<g:message code="common.gatewayIp.invalid"/>',
                                regExp: dojox.validate.regexp.ipAddress, placeHolder: '<g:message code="common.gatewayIp"/>', 
                                promptMessage:'<g:message code="common.gatewayIp.promptMessage"/>'" id="adminSubnetGatewayIp">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="gatewayIpHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'gatewayIpHelp', showDelay: 1"><g:message code="common.help.message.gatewayIp"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span9"></div>
                        <div class="span3">
                            <img class="hide_text" id="networkLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                            <div id="adminSubnetNetworkCancelButtonDiv" class="span4 pull-right">
                                <button id="adminSubnetNetworkCancelButton"  data-dojo-type="dijit.form.Button" onclick="SubnetInfo.cancel()" class="cancelbtn">
                                    <g:message code="common.cancel"/>
                                </button>
                            </div>
                            <div id="adminSubnetNetworkAddButtonDiv" class="span2 pull-right" style="display: block;" >
                                <button   id="adminSubnetNetworkAddButton"  data-dojo-type="dijit.form.Button" onclick="SubnetInfo.add()" class="okbtn">
                                    <g:message code="common.ok"/>
                                </button>
                            </div>        
                            <div id="adminSubnetNetworkUpdateButtonDiv" style="display: none;" class="span3 pull-right">
                                <button   id="adminSubnetNetworkUpdateButton"  data-dojo-type="dijit.form.Button" onclick="SubnetInfo.update()" class="okbtn">
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
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="adminCreateSubnetLoader">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createSubnetInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createSubnetInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a onclick = "SubnetInfo.gotoList()" class="btn-flat default" ><g:message code='common.gotoSubnetList' /></a>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="adminUpdateSubnetLoader">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.updateSubnetInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.updateSubnetInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a onclick = "SubnetInfo.gotoList()" class="btn-flat default" ><g:message code='common.gotoSubnetList' /></a>
    </div>
</div>
