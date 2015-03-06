<div class="row">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/virtualDataCenter" ><g:message code="menu.user.virtualDataCenter"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.routerlist"/></li>    
        </ul>
    </div>
</div>
<div class="row" id="createRouterDiv" style="margin-top: 50px;">   
    <div class="span6">
        <div class="row">
            <div class="span12"> 
                <input type="hidden" id="routerId"/>
                <form class="form-horizontal" id="createRouterForm" data-dojo-type="dijit.form.Form">
                    <div class="row" id="routerPage">
                        <div class="span12 control-group field-box">
                            <label for="routerName" class="control-label">
                                <g:message code="common.router.name"/>:<span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="routerName"  data-dojo-props="placeHolder: '<g:message code="common.enterRouterName"/>', 
                                       required: 'true',invalidMessage:'<g:message code="common.router.invalid"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="routerNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'routerNameHelp', showDelay: 1"><g:message code="common.help.message.routerName"/></div>
                                </div>
                            </div>

                        </div>    
                    </div> 


                </form> 
            </div>
        </div> 
    </div>
    <div class="span4">
        <div id="routerButtonDiv" style = "display: block;">
            <div id="addRouterButton">
                <button type="button"  onclick="RouterInfo.create()" class="defaultbtn" data-dojo-type="dijit.form.Button"><g:message code="common.create"/></button>
                <button   class="cancelbtn"  type="button" data-dojo-type="dijit.form.Button" onclick=" RouterInfo.cancelRouter()"><g:message code="common.cancel"/></button>
            </div>
            <div style="display: none;" id="updateRouterButton">
                <button   class="defaultbtn"  type="button" data-dojo-type="dijit.form.Button" onclick=" RouterInfo.updateRouter()"><g:message code="common.update"/></button>

                <button   class="cancelbtn"  type="button" data-dojo-type="dijit.form.Button" onclick=" RouterInfo.cancelRouter()"><g:message code="common.cancel"/></button>
            </div>
        </div>
        <div class="span4" id="routerCreateLoader" style="display: none"> 
            <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
        </div>
    </div>
</div>
<div class="row">
    <form id="listRouterForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row">
                <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="routerCurrencyValue"></span></div>
            </div>
            <div class="row filter-block">
                <div class="pull-right">
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="Routers.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                </div>
            </div>
            <div class="row">
                <div id="userRouterList">  
                </div>
                <div class="alert alert-info hide text_gray" id="noRouterMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="user.router.noRouterMsg"/>&nbsp;&nbsp;<g:message code="common.pleaseCreateOneNow"/></a>
                </div>
            </div>
        </div>
    </form>
</div>
<input type="hidden" id="currentRouterId">
<div data-dojo-type="dijit.Dialog" id="deleteRouterAlert" title="<g:message code='user.router.delete.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row">
<!--        <div class="span2"><img src='images/vm_detach_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><g:message code='user.router.delete.message' />  </div>            
        </div>        
        <div class="row">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="Routers.delete()">
                <g:message code='common.delete' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="Routers.cancelDelete()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog" id="clearGatewayConfirmAlert" title="<g:message code='user.router.clearGateway.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row">
<!--        <div class="span2"><img src='images/vm_detach_icon.png'/></div>-->
        <div class="span12">
            <div class="span12"><g:message code='user.router.clearGateway.message' />  </div>            
        </div>        
        <div class="row">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="RouterInfo.clearGateway()">
                <g:message code='common.ok' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="RouterInfo.cancelClearGatewayConfirmDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog"  id="routerDeleteLoader" class="customDialgue span6">
    <div class="row">
        <div class="span9">
            <div class="span12"><p><g:message code='common.routerDeleteLoader1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.routerDeleteLoader2' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="Routers.closeDeleteDialog()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
<input type="hidden" id="selectedSubnetId">
<div data-dojo-type="dijit.Dialog" id="editRouterDialog" title="<g:message code="common.user.editRouter"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row">
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="routerEditForm">  
                    <div id="routerEditPage"> 
                        <div class="control-group"> 
                            <label for="routerName" class="control-label">      
                                <g:message code="common.name"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                            <input type="text" 
                                   data-dojo-type="dijit.form.ValidationTextBox" 
                                   data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                   required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                   regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                   name="updatedRouterName" id="updatedRouterName">
                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                <i class="icon-info-sign" id="routerUpdatedNameHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'routerUpdatedNameHelp', showDelay: 1"><g:message code="common.help.message.routerUpdateName"/></div>
                            </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span3 pull-right"> 
               <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="RouterInfo.update()"><g:message code="common.update"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="RouterInfo.cancelEditDialog()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="setGatewayDialog" title="<g:message code="common.user.setGateway"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row">
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="routerGatewayForm">  
                    <div id="routerGatewayFormPage">
                        <div class="control-group">
                            <label for="" class="control-label"><g:message code="common.externalNetwork"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="externalNetworkList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="externalNetworkListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'externalNetworkListHelp', showDelay: 1"><g:message code="common.help.message.externalNetworkList"/></div>
                                </div>
                            </div>
                        </div> 
<!--                        <div class="control-group"> 
                            <label for="routerName" class="control-label">      
                                <g:message code="common.name"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                            <input type="text" 
                                   data-dojo-type="dijit.form.ValidationTextBox" 
                                   data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                   required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                   regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                   name="gatewayRouterName" id="gatewayRouterName">
                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                <i class="icon-info-sign" id="routerNameHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'routerNameHelp', showDelay: 1"><g:message code="common.help.message.routerName"/></div>
                            </div>
                            </div>
                        </div>-->
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span3 pull-right"> 
               <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="RouterInfo.setGateway()"><g:message code="common.setGateway"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="RouterInfo.cancelGateway()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="routerGatewayLoader" class="customDialgue span6">
    <div class="row">
        <div class="span9">
            <div class="span12"><p><g:message code='common.routerGatewayLoader1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.routerGatewayLoader2' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="Routers.closeDeleteDialog()"><g:message code='common.close' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>