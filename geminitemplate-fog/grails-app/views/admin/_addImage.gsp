<%@ page coentType="text/html;charset=UTF-8" %>
<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/services"><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><a href="#/admin/images/list"><g:message code="menu.images"/></a></li>
            <li>/</li>
            <li id="editImageBreadcrum"><g:message code="common.create"/></li>
            
        </ul>
    </div>
</div>
<div class="row">
        <div class="row header">
            <h3 id="imagePagehead"></h3>   <div class="col-md-2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>   <span  id="currencyValue"></span></div>
        </div>
        <form id="adminImageContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="imageContent">
                <div class="row">
                    <div class="col-md-12">
                        <div class="control-group col-md-4 horizontalcontent">
                            <input type="hidden" id="currentImageId" value="">
                            <label for="imageName" class="control-label">          
                                <g:message code="common.name"/>: 
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="imageName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.name.placeHolder"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageNameHelp', showDelay: 1"><g:message code="common.help.message.imageName"/></div>
                                </div>
                            </div>

                        </div>
                        <div class="control-group col-md-4 horizontalcontent">
                            <label for="imageDescription"  class="control-label">         
                                <g:message code="common.desc"/> :
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="imageDescription" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.description.prompt"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageDescriptionHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageDescriptionHelp', showDelay: 1"><g:message code="common.help.message.imageDescription"/></div>
                                </div>

                            </div>
                        </div> 
                        <div class = "control-group col-md-4 customDisk">
                            <label for="imageFormat" class="control-label">              
                                <g:message code="common.format"/>: 
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <select class="customSelectWidth" data-dojo-type="dijit.form.FilteringSelect" id="imageFormat">
                                    <!--<option value="" selected="selected"></option>-->
                                    <option value="aki">AKI - Amazon Kernel Image</option>
                                    <option value="ami">AMI - Amazon Machine Image</option>
                                    <option value="ari">ARI - Amazon Ramdisk Image</option>
                                    <option value="iso">ISO - Optical Disk Image</option>
                                    <option value="qcow2">QCOW2 - QEMU Emulator</option>
                                    <option value="raw">Raw</option>
                                    <option value="vdi">VDI</option>
                                    <option value="vhd">VHD</option>
                                    <option value="vmdk">VMDK</option>
                                </select>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageFormatHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageFormatHelp', showDelay: 1"><g:message code="common.help.message.imageFormat"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                     <div class="row">
                        <div class = "control-group col-md-4 customDisk" id="imageSourceDiv" style="display: block;">
                            <label for="imageSource" class="control-label">              
                                <g:message code="common.imageSource"/>: 
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <select class="customSelectWidth" onChange='AddImageInfo.changeImageType(this)' data-dojo-type="dijit.form.FilteringSelect" id="imageSource">
                                    <option value="url" selected="selected">Image Location</option>
                                    <option value="file">Image File</option>
                                </select>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageSourceHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageSourceHelp', showDelay: 1"><g:message code="common.help.message.imageSource"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="control-group col-md-4 horizontalcontent" id="imageUrlDiv" style="display: block;">
                            <label for="imageLocation"  class="control-label">         
                                <g:message code="common.imageLocation"/>: <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" name="" data-dojo-type ="dijit.form.ValidationTextBox"
                                id="imageLocation" data-dojo-props="invalidMessage:'Invalid image location', trim:'true', placeHolder: 'http://example.com/image.iso',
                                regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageLocationHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageLocationHelp', showDelay: 1"><g:message code="common.help.message.imageLocation"/></div>
                                </div>

                            </div>
                        </div>
                        
                        <div class="control-group col-md-4 horizontalcontent" id="imageFileDiv" style="display: none;">
                            <label for="imageFile"  class="control-label">         
                                <g:message code="common.imageFile"/>:  <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input  type="file" id="imageFile">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageFileHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageFileHelp', showDelay: 1"><g:message code="common.help.message.imageFile"/></div>
                                </div>
                                 <!--<div data-dojo-type="dojox.form.Uploader" id="imageFile" data-dojo-props="name:'imageFile',showInput:'before',isDebug:true">Browse</div>-->
                            </div>
                        </div>
                            <div class="control-group col-md-4 customDisk" id="imagesArchDiv">
                            <label for="architecture" class="control-label">
                                <g:message code="common.architecture"/>:</label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="architecture" data-dojo-props="invalidMessage: '<g:message code="common.invalid.value"/>', 
                                placeHolder: '<g:message code="common.architecture"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.architecture"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="architectureHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'architectureHelp', showDelay: 1"><g:message code="common.help.message.architecture"/></div>
                                </div>
                            </div>
                        </div>
                        
                        
                    </div>
                    <div class="row">                                
                        
                        <div class="control-group col-md-4 customDisk">
                            <label for="minimumDisk" class="control-label">              
                                <g:message code="common.minimumDisk"/>(<g:message code="common.gb"/>):</label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                id="minimumDisk" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', trim: 'true',
                                placeHolder: '<g:message code="common.minimumDisk"/>', constraints:{min:0,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:0, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.minimumDisk"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="minimumDiskHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'minimumDiskHelp', showDelay: 1"><g:message code="common.help.message.minimumDisk"/></div>
                                </div>
                            </div>
                            <!-- <span  id="flavorEphemeralDiskLabel" class="hide_lable"></span>-->
                        </div> 
                        <div class="control-group col-md-4 customDisk">
                            <label for="minimumRam" class="control-label">              
                                <g:message code="common.minimumRam"/>(<g:message code="common.mb"/>):</label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                id="minimumRam" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', trim: 'true',
                                placeHolder: '<g:message code="common.minimumRam"/>', constraints:{min:0,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:0, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.minimumRam"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorSwapDiskHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorSwapDiskHelp', showDelay: 1"><g:message code="common.help.message.flavorSwapDisk"/></div>
                                </div>
                            </div>
                             <span  id="flavorSwapDiskLabel" class="hide_lable"></span>
                        </div>
                        <div class="control-group col-md-4 customDisk">
                            <label for="imagePublic" class="control-label">
                                <g:message code="common.imageIsPublic"/>:
                            </label>
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="imageIsPublic">
                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageIsPublicHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageIsPublicHelp', showDelay: 1"><g:message code="common.help.message.imageIsPublic"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="row">            
                        
                        <div class="control-group col-md-4 customDisk">
                            <label for="imageProtected" class="control-label">
                                <g:message code="common.imageIsProtected"/>:
                            </label>
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="imageIsProtected"> 
                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageIsProtectedHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageIsProtectedHelp', showDelay: 1"><g:message code="common.help.message.imageIsProtected"/></div>
                            </div>
                        </div>
                        <div class="col-md-4 field-box control-group">
                            <label for="imageOneTimeChargeable" class="control-label">
                                <g:message code="common.oneTimeChargeable" />:
                            </label>
                            <div class="control">
                                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="imageOneTimeChargeable">
                            </div>
                        </div>
                        <div class="col-md-4 field-box control-group">
                            <label for="imageCost" class="control-label">                   
                                <g:message code="common.cost" />:( 0 - <g:message code="common.forFree" />):  <span  class="require">*</span>
                            </label>
                                <div class="controls">
                                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                                    data-dojo-props="placeHolder: '<g:message code="common.value" />',
                                    promptMessage:'<g:message code="common.value" />', invalidMessage:'<g:message code="common.value.invalid" />', required: true" id="imageCost" value="0">  
                                </div>
                         </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8"></div>
                        <div class="col-md-4">
                            <img class="hide_text" id="imageLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                            <div class="col-md-7 pull-right">
                                <div id="imageAddButtonDiv" style="display: block;" class="col-md-3">
                                    <button id="imageAddButton"  data-dojo-type="dijit.form.Button" onclick="AddImageInfo.add()" class="okbtn">
                                        <g:message code="common.ok"/>
                                    </button>
                                </div>        
                                <div id="imageUpdateButtonDiv" style="display: none;" class="col-md-4">
                                    <button id="imageUpdateButton"  data-dojo-type="dijit.form.Button" onclick=" ImageInfo.update()" class="okbtn">
                                        <g:message code="common.update"/>
                                    </button>
                                </div>
                                <div id="imageCancelButtonDiv" class="col-md-4">
                                    <button id="imageCancelButton"  data-dojo-type="dijit.form.Button" onclick="AddImageInfo.cancel()" class="cancelbtn">
                                        <g:message code="common.cancel"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
</div>
<!--<div data-dojo-type="dijit.Dialog" id="computeOfferEditConformationDialog" title="Update" class="col-md-4">
    <p><g:message code="admin.updateItem"/></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser"/></p>
      <div class="row offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="ViewComputingOfferInfo.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="ViewComputingOfferInfo.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>-->
<div data-dojo-type="dijit.Dialog" class="full_loader" id="pullImageLoader" class="col-md-6">
    <div class="row">
        <div class="col-md-3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="col-md-9">
            <div class="col-md-12"><p><g:message code='common.createImageInfoOne' /></p></div>
            <div class="col-md-12" style="margin-left: 0"><p><g:message code='common.createImageInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="ImageInfo.gotoList()"><g:message code='common.gotoImageList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div>
</div>
