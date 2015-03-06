<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/storage/" ><g:message code="common.objectStorage"/></a></li>
            <li>/<li>
            <li><a href="#/user/storage/container"><g:message code="common.containers"/></a></li>
            <!--<li>/<li>-->
            <!--<li id="containerNameTag">Detail</li>-->            
           <div id="objectStoreBreadcrumbDiv"></div>
                                 
        </ul>
    </div>
</div>
<div id="containerViewLoaderDiv" style="display: none">
    <div id="containerViewLoader"></div>
</div>
<div class="row-fluid" id="viewContainerDiv">
    <!--<div data-dojo-type="dijit/layout/TabContainer" id="containerDetailTab" class="span12" style="overflow: visible;" tabStrip="true">-->               
        <!--<div data-dojo-type="dijit/layout/ContentPane"  title="<g:message code="menu.user.container.detail"/>" onshow="ObjectInfo.showAvailableObjects()">-->
            <div class="row-fluid" style="display: block">
                <div class="span7 network-title-info">
                    <div class=""></div>     
                    <div class="row-fluid">
                        <div class="grd-row-alt1-tbl">
                            <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first" style="width: 110px"><g:message code="common.containerName"/></div>
                                <div class="grd-tbl-cell clm-second" style="width: 250px"><div class="span12 row-fluid" >
                                        <!--<div class="span8">-->
                                        <span id="containerNameInfo"  class="span8" style="min-height: 12px; margin-top: 8px;"></span>
                                        <!--</div>-->
                                    <div class="span4">                               
                                        <a class="span6" onclick="ContainerView.accessConfirmation();">
                                            <img id="containerAccessIcon" title="<g:message code="common.edit"/>" src="images/edit.png">
                                        </a>
                                        <a class="span6" onclick="ContainerView.deleteConfirmation();">
                                            <img title="<g:message code="common.delete"/>" src="js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png">
                                        </a>  
                                    </div>
                                    </div>
                                  
                                </div>
                            </div>
                        </div>
                        <div class="grd-row-alt2-tbl">
                            <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first" style="width: 110px"><g:message code="common.containerAccess"/></div>
                                <div class="grd-tbl-cell clm-second" style="width: 80px"><span id="containerAccessInfo"></span></div>
                            </div>
                        </div>
                        <div class="grd-row-alt1-tbl">
                            <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first" style="width: 110px"><g:message code="common.objectCount"/></div>
                                <div class="grd-tbl-cell clm-second" style="width: 80px"><span id="containerObjectCountInfo"  ></span></div>
                            </div>
                        </div>
                        <div class="grd-row-alt2-tbl">
                            <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first" style="width: 110px"><g:message code="common.totalSize"/></div>
                                <div class="grd-tbl-cell clm-second" style="width: 80px"><span id="containerSizeInfo"  ></span></span> <g:message code="common.kb"/></div>
                            </div>
                        </div>
                        <div class="grd-row-alt1-tbl">
                            <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first" style="width: 110px"><g:message code="common.createdAt"/></div>
                                <div class="grd-tbl-cell clm-second" style="width: 150px"><span id="containerCreatedDateInfo"  ></span></span></div>
                            </div>
                        </div>
                        <div class="grd-row-alt2-tbl" id="publicUrlDetail" style="display: none">
                            <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first" style="width: 110px"><g:message code="common.publicUrl"/></div>
                                <div class="grd-tbl-cell clm-second" style="width: 500px"><span id="containerPublicUrlInfo"  ></span></div>
                            </div>
                        </div>
                        <div class="grd-row-alt1-tbl">
                            <div class="grd-tbl-row">
                                <div class="grd-tbl-cell clm-first" style="width: 110px"><g:message code="common.account"/></div>
                                <div class="grd-tbl-cell clm-second" style="width: 150px"><span id="containerAccountInfo"  ></span></span></div>
                            </div>
                        </div>
                    </div>        
                </div>
<!--                <div class="span8">
                    <div class="row-fluid" style="margin-top: 30px; margin-left: 4px">
                        <div class="span5">
                           <p style="font-weight: bold" class=""><g:message code="common.pseudoFolder.title"/></p>
                        </div>

                           <div class="span7" style="margin-left: 0px">
                            <p style="font-weight: bold"><g:message code="common.objectUpload.title"/></p>
                        </div>
                    </div>
                </div>-->
                <div class="span5" style="margin-top:10px">
                    <div class="row-fluid span12"> 
                        <div class="span12 well" style="height: 90px">
                          <form id="addPseudoFolderForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-vertical">
                              <div id="addPseudoFolderContent">
                                  <div class="row-fluid">
                                      <div class="span12">
                                          <div class="control-group span5 horizontalcontent">
                                              <input type="hidden" id="selectedContainerName">
                                              <label for="pseudoFolderName" class="">          
                                                  <g:message code="common.pseudoFolderName"/> : <span class="require">*</span>
                                              </label>
                                              <div class="controls elements">
                                                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                                      id="pseudoFolderName" data-dojo-props="required: 'true',
                                                      invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\^:;=]{1,200}',
                                                      promptMessage:'<g:message code="common.pseudoFolderName.promptMessage"/>'">
                                                  <div class="form_help_icon" style="top: 0; left: 0%;">
                                                      <i class="icon-info-sign" id="pseudoFolderNameHelp"></i>
                                                      <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'pseudoFolderNameHelp', showDelay: 1"><g:message code="common.help.message.pseudoFolderName"/></div>
                                                  </div>
                                              </div>                           
                                          </div> 
                                            <div id=""></div>
                                            <div id="addPseudoFolderDiv" class="span6">
                                                <div id="" class="span3 offset2" style="margin-top: 10px; margin-left: 20px">
                                                    <button id=""  data-dojo-type="dijit.form.Button" onclick="ObjectInfo.addPseudoFolder()" class="okbtn">
                                                        <g:message code="common.ok"/>
                                                    </button>
                                                </div>                                        
                                                <div id="" class="span1" style="margin-top: 10px; margin-left: 10px">
                                                    <button id=""  data-dojo-type="dijit.form.Button" onclick="ObjectInfo.cancelFolderCreation()" class="cancelbtn">
                                                        <g:message code="common.cancel"/>
                                                    </button>
                                                </div>
                                            </div>
                                            <div id="addPseudoFolderLoader" style="display: none; margin-top: 10px; margin-left: 200px" >
                                                <img class="offset4" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                            </div>
                                        </div>
                                  </div>   
                              </div>
                              
                          </form>
                      </div> 
                    </div>
                    <div class="row-fluid span12"> 
                        <div class="well span12" id="uploadObjectFormDiv" style="height: 130px; margin-left: -10px;">       
                        <!--<form id="uplaodObjectForm" accept-charset="" action="pdf/upload" enctype="multipart/form-data" class="form-vertical">-->
                            <g:uploadForm action="upload" id="uplaodObjectForm" controller="objectStore" action="upload" class="form-vertical" target="hidden-upload-frame" onsubmit="return(ObjectInfo.uploadObject());">
                                <div id="uplaodObjectContent" class="span12">
                                    <div class="row-fluid">
                                        <div class="span12">
                                            <div class="control-group span5 horizontalcontent">
                                                <label for="uplaodObject" class="">          
                                                    <g:message code="common.objectName"/> : <span class="require">*</span>
                                                </label>
                                                <div class="controls elements">
                                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                                        id="uploadObjectFileName" onchange="ObjectInfo.uploadValidation(this)"  name="uploadObjectFileName" disabled="false" data-dojo-props="required: 'true',
                                                        invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\^:;=]{1,200}',
                                                        promptMessage:'<g:message code="common.objectName.promptMessage"/>'">
                                                    <div class="form_help_icon" style="top: 0; left: 0%;">
                                                        <i class="icon-info-sign" id="fileNameHelp"></i>
                                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'fileNameHelp', showDelay: 1"><g:message code="common.help.message.objectName"/></div>
                                                    </div>
                                                </div>                           
                                            </div> 
                                            <div class="control-group span6 horizontalcontent" id="" style="display: block;">
                                                <label for="selectFile"  class="control-label">         
                                                    <g:message code="common.selectFile"/>: <span class="require">*</span>
                                                </label>
                                                <div class="controls elements">
                                                    <input  type="file" id="objectFile" name="objectFile" onchange="ObjectInfo.uploadFileUrl(this)">
                                                  <!--<input name="restoreFile" multiple="false" type="file" id="restoreBtn"
                                                       data-dojo-type="dojox/form/Uploader" data-dojo-props='label: "Upload", uploadOnSelect: "true"' />
                                                    <div id="fileList" data-dojo-type="dojox/form/uploader/FileList" 
                                                          data-dojo-props='uploaderId: "restoreBtn"'></div>-->

                                                     <!--<div data-dojo-type="dojox.form.Uploader" id="objectFile" data-dojo-props="name:'objectFile',showInput:'before',isDebug:true">Browse</div>-->
                                                </div>
                                            </div>
                                            <input type="hidden" name="selectedPathName" id="selectedPathName">
                                            <input type="hidden" name="selectedObjectName" id="selectedObjectName">
                                            <input type="hidden" name="selectedContainerId" id="selectedContainerId">
                                            <input type="hidden" name="editedObjectName" id="editedObjectName">
                                        </div>
                                    </div>

                                </div>
                                <div id="uploadObjectButtonDiv" style="display: block">
                                    <div id="objectUploadSubmitButton" class="span3 offset4" style="margin-top: 0px; display: block">
                                        <button id=""  data-dojo-type="dijit.form.Button" type="submit" class="okbtn">
                                            <g:message code="common.upload"/>
                                        </button>
                                    </div>
                                    <div id="objectUpdateSubmitButton" class="span3 offset4" style="margin-top: 0px; display: none">
                                        <button id=""  data-dojo-type="dijit.form.Button" type="submit" onclick="ObjectInfo.uploadObject()" class="okbtn">
                                            <g:message code="common.update"/>
                                        </button>
                                    </div>
                                    <div id="" class="span3" style="margin-top: 0px; margin-left:0px; margin-left: -15px">
                                        <button id=""  data-dojo-type="dijit.form.Button" onclick="ObjectInfo.cancelUpload()" class="cancelbtn">
                                            <g:message code="common.cancel"/>
                                        </button>
                                    </div>
                                </div>
                                <div id="uploadObjectButtonLoader" style="display: none;">
                                    <img id="" class="offset4" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                </div>
                            </g:uploadForm>

                           <!--</form>-->
                        </div>
                    </div>    
                </div>
                
                <div class="span5">
                    
                    <div class="row-fluid" id="hidden-upload-frameDiv">
                        <!--<div class="span4 pull-right">-->
                        <iframe id="hidden-upload-frame" name="hidden-upload-frame" style="display: none; height: 60px; width:290px;"  frameBorder="0" onload="ObjectInfo.onUploadComplete()">
                        </iframe>
                    <!--</div>-->
                    </div>
                </div> 
            </div>
<!--            <div class="row-fluid" style="margin-top: 30px">
                <div class="span4">
                   <p style="font-weight: bold" class=""><g:message code="common.pseudoFolder.title"/></p>
                </div>
                <div class="span2">
                </div> 
                <div class="span6 ">
                    <p style="font-weight: bold"><g:message code="common.objectUpload.title"/></p>
                </div>
            </div>-->
                                                              
            <input type="hidden" id="objectFileUrl">
            <input type="hidden" id="uploadObject">
</div>
<!--<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>           
            <li><div id="objectStoreBreadcrumbDiv"></div></li>
                                 
        </ul>
    </div>
</div>-->
<div class="row-fluid">
     <div class="row-fluid" style="margin-top: 20px">
        <div class="span12">
            
            <div class="row-fluid">                       
                <div class="span12">
                    <div class="alert alert-info hide overflowLabel text_gray" id="noObjectMessageBox" style="display: none">
                        <i class="icon-exclamation-sign text_gray"></i> 
                        <g:message code="common.user.noObjectMsg"/>
                    </div>
                </div>
            </div>
            <!--<div id="objectPath" class="span6 pull-right"><g:message code="common.path"/> : <span id="objectPathName"></span></div>-->
            <div class="row-fluid">
                <div class="span12"><div id="objectListDiv"></div></div>
            </div>

        </div>
    </div> 
</div>  
<div data-dojo-type="dijit.Dialog" id="deleteContainerObjectDialog" title="<g:message code="common.delete"/>" style="color: black; width: 350px;">
    <input type="hidden" id="currentContainerName">
    <div class="row-fluid form-wrapper">
        <g:message code="common.user.deleteObject.confirmMsg"/>
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ObjectInfo.delete()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="ObjectInfo.closeDeleteDialog()">
                <g:message code="common.no"/>
            </button>       
        </div> 
    </div>
</div>
<input type="hidden" id="copyPathName">
<input type="hidden" id="copyObjectName">
<input type="hidden"  id="copyContainerId">
<div data-dojo-type="dijit.Dialog" id="copyObjectDialog" title="<g:message code="user.object.copyTitle"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span2"><img src='images/popup-icons/vm_snapshot_icon.png'/></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="copyObjectForm">  
                    <div id="copyObjectFormPage">
                        <div></div>
                        <div class="control-group span12">
                            <label for="containerList" class="control-label"><g:message code="common.destinationContainer"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                             <div id="" class="selectOption">
                                <div id="containerListDiv" ></div> 
                                <img id="containerListDivLoader" class="offset4" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <div class="form_help_icon" style="top: 0; left: -15px;">
                                    <i class="icon-info-sign" id="containerListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'containerListHelp', showDelay: 1"><g:message code="common.help.message.containerList"/></div>
                                </div>
                             </div>
                            </div>
                        </div>
                        <div class="control-group span12"> 
                            <label for="objectDestinationPath" class="control-label">      
                                <g:message code="common.objectDestinationPath"/>: 
                            </label>
                            <div class="controls">
                                <input type="text" 
                                       data-dojo-type="dijit.form.ValidationTextBox" 
                                       data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                       placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                       regExp: '[a-zA-Z.0-9-./]{4,50}'"  
                                       name="Name" id="objectDestinationPath"> 
                                <div class="form_help_icon" style="top: 0; left: -15px;">
                                    <i class="icon-info-sign" id="objectDestinationPathHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'objectDestinationPathHelp', showDelay: 1"><g:message code="common.help.message.objectDestinationPath"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="control-group span12"> 
                            <label for="destinationObjectName" class="control-label">      
                                <g:message code="common.destinationObjectName"/> :<span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" 
                                       data-dojo-type="dijit.form.ValidationTextBox" 
                                       data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                       required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                       regExp: '[a-zA-Z.0-9-./]{4,50}'" name="Name" id="destinationObjectName"> 
                                <div class="form_help_icon" style="top: 0; left: -15px;">
                                    <i class="icon-info-sign" id="destinationObjectNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'destinationObjectNameHelp', showDelay: 1"><g:message code="common.help.message.destinationObjectName"/></div>
                                </div>
                            </div>
                        </div>
                       
                    </div>
                </form>
                </div>
            </div>  
        <div>
            <div class="control-group span2 pull-right"> 
                <button id="" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ObjectInfo.copy()"><g:message code="common.ok"/></button>
                <button id="" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ObjectInfo.cancelCopy()"><g:message code="common.cancel"/></button>
            </div>
           <!--<div class="span5"></div>-->
        </div>
    </div>
</div>
<input type="hidden" id="currentPathName">
<input type="hidden" id="currentObjectName">
<input type="hidden"  id="currentContainerId">
<input type="hidden"  id="currentContainerAccess">
<input type="hidden"  id="selectedContainerAccess">
<div data-dojo-type="dijit.Dialog" id="objectViewDialog" title="<g:message code="user.object.viewTitle"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <dl>
                <dt><g:message code="common.name" /> :</dt>
                <dd><span id="objectNameInfo"  ></span></dd>
                <dt><g:message code="common.hash" /> :</dt>
                <dd><span id="objectHashInfo"  ></span></dd>
                <dt><g:message code="common.contentType" /> :</dt>
                <dd><span id="objectContentTypeInfo"  ></span></dd>
                <dt><g:message code="common.lastModified" /> :</dt>
                <dd><span id="objectLastModifiedDateInfo"  ></span></dd>
                <dt><g:message code="common.size" /> :</dt>
                <dd><span id="objectSizeInfo"  ></span> <g:message code="common.kb" /></dd>
            </dl>    
            
        </div>
    </div>
</div> 
<div data-dojo-type="dijit.Dialog"  id="objectActionLoader" class="customDialgue span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.actionInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.actionInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="ObjectInfo.gotoObjectList()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
<div data-dojo-type="dijit.Dialog" id="viewPageContainerAccessDialog" title="<g:message code="common.accessConfirmation"/>" style="color: black; width: 350px;">
    <div class="row-fluid form-wrapper">
        <div id="objectContainerAccessConfirmMessage"></div>
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ContainerView.changeAccess()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="ContainerView.closeAccessConfirmDialog()">
                <g:message code="common.no"/>
            </button>       
        </div> 
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="viewPageDeleteContainerDialog" title="<g:message code="common.delete"/>" style="color: black; width: 350px;">
    <div class="row-fluid form-wrapper">
        <g:message code="common.user.deleteContainer.confirmMsg"/>
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ContainerView.delete()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="ContainerView.closeDeleteDialog()">
                <g:message code="common.no"/>
            </button>       
        </div> 
    </div>
</div>

