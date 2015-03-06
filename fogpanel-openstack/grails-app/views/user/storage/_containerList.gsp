<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/storage/" class="overflowLabel"><g:message code="common.objectStorage"/></a></li>
    <li>/<li>
    <li class="overflowLabel"><g:message code="common.containers"/></li>
  </ul>
</div>
</div>

<div class="table-wrapper products-table">
<!--    <div class="row-fluid"> <div class="span12"></div> </div>
    <div class="row-fluid"> <div class="span12"></div> </div>-->
    <div class="row-fluid"> <div class="span12"></div> </div>
<div class="row-fluid">  
    <form id="addContainerForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
        <div id="addContainerContent">
            <div class="row-fluid">
                <div class="span12">
                    <div class="control-group span4 horizontalcontent">
                        <input type="hidden" id="currentContainerId">
                        <label for="containerName" class="">          
                            <g:message code="common.name"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="storageContainerName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\^:;=]{1,200}',
                                promptMessage:'<g:message code="common.containerName.promptMessage"/>'">
                            <div class="form_help_icon" style="top: 0; left: 0px;">
                                <i class="icon-info-sign" id="containerNameHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'containerNameHelp', showDelay: 1"><g:message code="common.help.message.containerName"/></div>
                            </div>
                        </div>                           
                    </div>
                    <div class="control-group span4 horizontalcontent">
                        <label for="containerAccess"  class="">         
                            <g:message code="common.containerAccess"/> :
                            <span class="require">*</span>
                        </label>
                        <div class="controls elements">
                            <select data-dojo-type="dijit/form/Select" id="storageContainerAccess">
                                <option value="private"><g:message code="common.private"/></option>
                                <option value="public" ><g:message code="common.public"/></option>                                
                            </select>
                            <div class="form_help_icon" style="top: 0; left: 0px;">
                                <i class="icon-info-sign" id="containerAccessHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'containerAccessHelp', showDelay: 1"><g:message code="common.help.message.containerAccessDescription"/></div>
                            </div>
                        </div>
                    </div>  
                    <div class="span4">
                        <div id="containerAddButtonDiv" style="display: block">
                            <div id="" class="span2">
                                <button id=""  data-dojo-type="dijit.form.Button" onclick="ContainerInfo.add()" class="okbtn">
                                    <g:message code="common.ok"/>
                                </button>
                            </div>                                        
                            <div id="" class="span2">
                                <button id=""  data-dojo-type="dijit.form.Button" onclick="ContainerInfo.cancel()" class="cancelbtn">
                                    <g:message code="common.cancel"/>
                                </button>
                            </div>
                        </div>  
                        <div class="span4" id="addContainerButtonLoader" style="display: none"> 
                            <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
                        </div>
                        
                    </div>
                </div>                
            </div>
        </div>
    </form>
</div>
 <div class="row-fluid"> <div class="span12"></div> </div>
 <div class="row-fluid"> <div class="span12"></div> </div>

 
    <div class="row-fluid">
      <div id="containerGridDiv"></div>
      <div class="alert alert-info hide overflowLabel text_gray" id="noContainerMessageBox" style="display: none">
        <i class="icon-exclamation-sign text_gray"></i> 
        <g:message code="common.user.noContainerMsg"/>
      </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="deleteObjectContainerDialog" title="<g:message code="common.delete"/>" style="color: black; width: 350px;">
    <input type="hidden" id="currentContainerName">
    <div class="row-fluid form-wrapper">
        <g:message code="common.user.deleteContainer.confirmMsg"/>
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ContainerInfo.delete()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="ContainerInfo.closeDeleteDialog()">
                <g:message code="common.no"/>
            </button>       
        </div> 
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="objectContainerAccessDialog" title="<g:message code="common.accessConfirmation"/>" style="color: black; width: 350px;">
    <input type="hidden" id="currentContainerAccess">
    <div class="row-fluid form-wrapper">
        <div id="objectContainerAccessConfirmMsg"></div>
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ContainerInfo.changeAccess()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="ContainerInfo.closeAccessConfirmDialog()">
                <g:message code="common.no"/>
            </button>       
        </div> 
    </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="containerActionLoader" class="customDialgue span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.actionInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.actionInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="ContainerInfo.gotoList()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>

