<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/virtualDataCenter" ><g:message code="menu.user.virtualDataCenter"/></a></li>
            <li>/<li>
            <li><a href="#/user/network/list"><g:message code="menu.user.networklist"/></a></li>
            <li>/<li>
            <li><a id="networkName"><g:message code="menu.admin.cloud"/></a></li>
            <li>/<li>
            <li><a id="subnetListLink"><g:message code="menu.user.subnetlist"/></a></li>
            <li>/<li>
            <li id="editSubnetBreadCrumbs"><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<input type="hidden" id="subnetNetworkId">
<input type="hidden" id="currentSubnetId">
<div class="row">
    <div class="col-md-12" id="">
        <div class="row header">
            <h3 id="subnetPagehead"></h3>   
        </div>
        <form id="subnetAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="subnetContent">
                <div class="row">
                    <div class="col-md-12">
                        <div class="control-group col-md-4 horizontalcontent">
                            <label for="subnetName"  class="control-label">         
                                <g:message code="common.subnetName"/> :
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="networkSubnetName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.subnetName.invalid"/>', placeHolder: '<g:message code="common.subnetName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                promptMessage:'<g:message code="common.subnetName.promptMessage"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="subnetNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'subnetNameHelp', showDelay: 1"><g:message code="common.help.message.subnetName"/></div>
                                </div>
                            </div>
                        </div> 
                        <div class="control-group col-md-4 horizontalcontent">
                            <label for="networkAddress"  class="control-label">         
                                <g:message code="common.networkAddress"/>:  <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="required:'true', regExp: '[0-9/.]{0,16}', invalidMessage:'<g:message code="common.networkAddress.invalid"/>',
                                placeHolder: '<g:message code="common.networkAddress"/>', 
                                promptMessage:'<g:message code="common.networkAddress.promptMessage"/>'" id="networkSubnetAddress">  
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkAddressHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkAddressHelp', showDelay: 1"><g:message code="common.help.message.networkAddress"/></div>
                                </div>
                            </div>
                        </div> 
                    </div>
                    <div class="row">      
                        <div class = "control-group col-md-4 customDisk">
                            <label for="ipVersion" class="control-label">              
                                <g:message code="common.ipVersion"/>: 
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <select class="customSelectWidth" data-dojo-type="dijit.form.Select" id="subnetIpVersions">
                                    <option value=4 selected="selected">IPv4</option>
                                    <option value=6>IPv6</option>
                                </select>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="ipVersionHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ipVersionHelp', showDelay: 1"><g:message code="common.help.message.ipVersion"/></div>
                                </div>
                            </div>
                        </div>
                        <div class = "control-group col-md-4 customDisk">
                            <label for="gatewayIp" class="control-label">              
                                <g:message code="common.gatewayIp"/>:
                            </label>
                            <div class="controls updatable">
                                 <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="invalidMessage:'<g:message code="common.gatewayIp.invalid"/>',
                                regExp: dojox.validate.regexp.ipAddress, placeHolder: '<g:message code="common.gatewayIp"/>', 
                                promptMessage:'<g:message code="common.gatewayIp.promptMessage"/>'" id="subnetGatewayIp">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="gatewayIpHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'gatewayIpHelp', showDelay: 1"><g:message code="common.help.message.gatewayIp"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-9"></div>
                        <div class="col-md-3">
                            <img class="hide_text" id="networkLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                            <div id="subnetNetworkCancelButtonDiv" class="col-md-4 pull-right">
                                <button id="subnetNetworkCancelButton"  data-dojo-type="dijit.form.Button" onclick="SubnetInfo.cancel()" class="cancelbtn">
                                    <g:message code="common.cancel"/>
                                </button>
                            </div>
                            <div id="subnetNetworkAddButtonDiv" class="col-md-2 pull-right" style="display: block;" >
                                <button   id="subnetNetworkAddButton"  data-dojo-type="dijit.form.Button" onclick="SubnetInfo.add()" class="okbtn">
                                    <g:message code="common.ok"/>
                                </button>
                            </div>        
                            <div id="subnetNetworkUpdateButtonDiv" style="display: none;" class="col-md-3 pull-right">
                                <button   id="subnetNetworkUpdateButton"  data-dojo-type="dijit.form.Button" onclick="SubnetInfo.update()" class="okbtn">
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
<div data-dojo-type="dijit.Dialog" class="customDialgue col-md-6" id="pullSubnetLoader">
    <div class="row">
        <div class="col-md-3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="col-md-9">
            <div class="col-md-12"><p><g:message code='common.createSubnetInfoOne' /></p></div>
            <div class="col-md-12" style="margin-left: 0"><p><g:message code='common.createSubnetInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a onclick = "SubnetInfo.gotoList()" class="btn-flat default" ><g:message code='common.gotoSubnetList' /></a>
    </div>
</div>