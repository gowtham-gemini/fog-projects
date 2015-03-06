<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
            <li>/</li>
            <li><g:message code="menu.admin.cloud.networks"/></a></li>
        </ul>
    </div>
</div>
<div class="row-fluid" style="display: block;">
    <form id="adminNetworkListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row-fluid" style="display: none;">
                <div class="value_dollar pull-right"><g:message code="default.valueIn"/><span id="adminNetworkListCurrencyValue"></span></div>
            </div>
            <div class="row-fluid filter-block" id="adminNetworkListDiv" style="display: block;margin-top: 0px;">
                <div class="pull-right">
                    
<!--                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ListFlavors.conformPull()" id="pullPlanButton">
                        <g:message code="common.pullVolumeFromOpenstack"/>
                        <img id="pullPlanLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                    </button>-->
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="Networks.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                    <a class="btn-flat success new-product" href="#/admin/network/create">+ <g:message code="common.createNetwork"/></a>
                </div>
            </div>
            <div class="row-fluid">
                <div id="adminNetworkList">  
                </div>
                <div class="alert alert-info hide text_gray" id="noNetworkAdminMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.admin.noNetwork"/>
                </div>
            </div>
        </div>
    </form>
</div> 
<div data-dojo-type="dijit.Dialog" id="adminNetworkEditFormDialog" title="<g:message code="common.user.editNetwork"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span2"><a class="index_title_icons_network" style="padding: 1px 0px 50px 42px;"></a></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="adminEditNetworkForm">  
                    <div id="adminEditNetworkFormPage">
                        <div class="control-group"> 
                            <label for="networkName" class="control-label">      
                                <g:message code="common.name"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                            <input type="text" 
                                   data-dojo-type="dijit.form.ValidationTextBox" 
                                   data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                   required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                   regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                   name="adminEditNetworkName" id="adminEditNetworkName"> 
                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkEditNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkEditNameHelp', showDelay: 1"><g:message code="common.help.message.newNetworkName"/></div>
                            </div> 
                            </div>
                        </div>
                        <div class="control-group">
                            <label for="networkId" class="control-label"><g:message code="common.id"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                             <input type="text" 
                                   data-dojo-type="dijit.form.ValidationTextBox" 
                                   data-dojo-props="required: 'required',disabled: true, regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                   name="adminEditNetworkId" id="adminEditNetworkId">
                            </div>
                        </div>
                        <div class = "control-group">
                            <label for="adminStateUp" class="control-label">              
                                <g:message code="common.adminState"/>:
                            </label>
                            <div class="controls updatable">
                                 <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                    data-dojo-props="checked: true" id="adminEditNetworkState">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="networkAdminStateHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkAdminStateHelp', showDelay: 1"><g:message code="common.help.message.networkAdminState"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span3 pull-right"> 
                <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="NetworkInfo.update()"><g:message code="common.update"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="NetworkInfo.closeEditNetworkDialog()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="adminDeleteNetworkDialog" title="<g:message code="user.network.deleteConfirm"/>" class="span4">
    <input type="hidden" id="currentNetworkId">
      <p><g:message code="user.network.deleteConfirm.message"/></p>
      <div class="row-fluid offset1">
        <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="NetworkInfo.delete()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="NetworkInfo.closeDeleteNetworkDialog()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="adminNetworkLoader">
  <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.deleteNetworkInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.deleteNetworkInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="NetworkInfo.closeLoader()"><g:message code='common.clickHereToClose' /></a>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="adminNetworkUpdateLoader">
  <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.updateNetworkInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.updateNetworkInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="NetworkInfo.closeLoader()"><g:message code='common.clickHereToClose' /></a>
    </div>
</div>
