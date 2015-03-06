<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/admin/network/list"><g:message code="menu.admin.cloud.networks"/></a></li>
            <li>/</li>
            <li id="editNetworkBreadCrumbs"><g:message code="common.create"/></li>
        </ul>
    </div>
</div>  
<div class="row-fluid">
    <div class="span12" id="">
        <div class="row-fluid header">
            <h3 id="adminNetworkPagehead"></h3>   <div class="span2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>  <span id="addNetworkCurrencyValue"></span></div>
        </div>
        <input type="hidden" id="currentAdminNetworkId">
        <form id="adminNetworkAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="adminNetworkContent">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group span4 horizontalcontent">
                            <input type="hidden" id="currentNetworkId" name="adminCurrentNetworkId" value="">
                            <label for="networkName" class="control-label">          
                                <g:message code="common.networkName"/>: <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="adminNetworkName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidMessage.networkName"/>', placeHolder: '<g:message code="common.networkName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                promptMessage:'<g:message code="common.networkName.promptMessage"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkNameHelp', showDelay: 1"><g:message code="common.help.message.networkName"/></div>
                                </div>
                            </div>

                        </div>
                        <div class="control-group span4 horizontalcontent">
                            <label for="" class="control-label"><g:message code="common.account"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="accountListInNetwork" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="projectListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'projectListHelp', showDelay: 1"><g:message code="common.help.message.projectList"/></div>
                                </div>
                            </div>
                        </div> 
                        <div class = "control-group span4 customDisk">
                            <label for="adminStateUp" class="control-label">              
                                <g:message code="common.adminState"/>:
                            </label>
                            <div class="controls updatable">
                                 <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                    data-dojo-props="checked: true" id="networkAdminState">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkAdminStateHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkAdminStateHelp', showDelay: 1"><g:message code="common.help.message.networkAdminState"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid"> 
                        <div class = "control-group span4 customDisk">
                            <label for="isShared" class="control-label">              
                                <g:message code="common.isShared"/>:
                            </label>
                            <div class="controls updatable">
                                 <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                    data-dojo-props="checked: false" id="networkisShared">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkisSharedHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkisSharedHelp', showDelay: 1"><g:message code="common.help.message.networkiIsShared"/></div>
                                </div>
                            </div>
                        </div>
                        <div class = "control-group span4 customDisk">
                            <label for="isExternal" class="control-label">              
                                <g:message code="common.isExternal"/>:
                            </label>
                            <div class="controls updatable">
                                 <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                    data-dojo-props="checked: false" id="networkIsExternal">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkIsExternalHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkIsExternalHelp', showDelay: 1"><g:message code="common.help.message.networkIsExternal"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span9"></div>
                        <div class="span3">
                            <img class="hide_text" id="adminNetworkLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                            <div id="adminNetworkCancelButtonDiv" class="span4 pull-right">
                                <button id="networkCancelButton"  data-dojo-type="dijit.form.Button" onclick="NetworkInfo.cancel()" class="cancelbtn">
                                    <g:message code="common.cancel"/>
                                </button>
                            </div>
                            <div id="adminNetworkAddButtonDiv" class="span2 pull-right" style="display: block;" >
                                <button   id="networkAddButton"  data-dojo-type="dijit.form.Button" onclick="NetworkInfo.add()" class="okbtn">
                                    <g:message code="common.ok"/>
                                </button>
                            </div>        
                            <div id="adminNetworkUpdateButtonDiv" style="display: none;" class="span3 pull-right">
                                <button   id=""  data-dojo-type="dijit.form.Button" onclick="NetworkInfo.update()" class="okbtn">
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
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="adminCreateNetworkLoader">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.networkLoaderInfo2' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.networkLoaderInfo1' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a onclick = "NetworkInfo.gotoList()" class="btn-flat default" ><g:message code='common.gotoNetworkList' /></a>
    </div>
</div>
