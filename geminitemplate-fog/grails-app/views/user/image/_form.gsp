<div class="row">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud" ><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/user/image/list" ><g:message code="menu.user.imageList"/></a></li> 
            <li>/</li>
            <li id="editImageBreadcrum"><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="row header createVm"></div>
    <div class="row form-wrapper">
        <div class="span3 createvm-banner">
            <img src="${resource(dir: 'images')}/createImage_banner_logo.png" height="151" width="238">
            <h2 class="alphaStyle overflowLabel"><g:message code="user.createImage"/></h2>        
        </div>
        <div class="span6 createvm-form with-sidebar" id="">
            <div class="container span12">
                <form id="userImageContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
                    <div id="imageContent">
                        <!--<div class="row">-->
                            <!--<div class="span12">-->
                        <div class="span12 field-box">
                            <input type="hidden" id="currentImageId" value="">
                            <label for="imageName" class="control-label">          
                                <g:message code="common.name"/>: 
                                <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="userImageName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.name.placeHolder"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageNameHelp', showDelay: 1"><g:message code="common.help.message.imageName"/></div>
                                </div>
                            </div>

                        </div>
                        <div class="span12 field-box">
                            <label for="imageDescription"  class="control-label">         
                                <g:message code="common.desc"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="userImageDescription" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.description.prompt"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageDescriptionHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageDescriptionHelp', showDelay: 1"><g:message code="common.help.message.imageDescription"/></div>
                                </div>

                            </div>
                        </div> 
                        <div class = "span12 field-box">
                            <label for="imageFormat" class="control-label">              
                                <g:message code="common.format"/>: 
                                <span class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <select class="customSelectWidth" data-dojo-type="dijit.form.FilteringSelect" id="userImageFormat">
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
                    <!--</div>-->
                     <!--<div class="row">-->
                        <div class = "span12 field-box" id="imageSourceDiv" style="display: block;">
                            <label for="imageSource" class="control-label">              
                                <g:message code="common.imageSource"/>: 
                                <span class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <select class="customSelectWidth" onChange='ImageInfo.changeImageType(this)' data-dojo-type="dijit.form.FilteringSelect" id="userImageSource">
                                    <option value="url" selected="selected">Image Location</option>
                                    <option value="file">Image File</option>
                                </select>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageSourceHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageSourceHelp', showDelay: 1"><g:message code="common.help.message.imageSource"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="span12 field-box" id="imageUrlDiv" style="display: block;">
                            <label for="imageLocation"  class="control-label">         
                                <g:message code="common.imageLocation"/>:<span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" name="" data-dojo-type ="dijit.form.ValidationTextBox"
                                id="userImageLocation" data-dojo-props="invalidMessage:'Invalid image location', trim:'true', placeHolder: 'http://example.com/image.iso',
                                regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageLocationHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageLocationHelp', showDelay: 1"><g:message code="common.help.message.imageLocation"/></div>
                                </div>

                            </div>
                        </div>

                        <div class="span12 field-box" id="imageFileDiv" style="display: none;">
                            <label for="imageFile"  class="control-label">         
                                <g:message code="common.imageFile"/>: <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input  type="file" id="userImageFile">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="imageFileHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageFileHelp', showDelay: 1"><g:message code="common.help.message.imageFile"/></div>
                                </div>
                                 <!--<div data-dojo-type="dojox.form.Uploader" id="imageFile" data-dojo-props="name:'imageFile',showInput:'before',isDebug:true">Browse</div>-->
                            </div>
                        </div>
                        <div class="span12 field-box" id="imagesArchDiv">
                            <label for="architecture" class="control-label">
                                <g:message code="common.architecture"/>:</label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="userArchitecture" data-dojo-props="invalidMessage: '<g:message code="common.invalid.value"/>', 
                                placeHolder: '<g:message code="common.architecture"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.architecture"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="architectureHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'architectureHelp', showDelay: 1"><g:message code="common.help.message.architecture"/></div>
                                </div>
                            </div>
                        </div>


<!--</div>-->
<!--<div class="row">-->                                

                        <div class="span12 field-box">
                            <label for="minimumDisk" class="control-label">              
                                <g:message code="common.minimumDisk"/>(<g:message code="common.gb"/>):</label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                id="userMinimumDisk" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', trim: 'true',
                                placeHolder: '<g:message code="common.minimumDisk"/>', constraints:{min:0,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:0, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.minimumDisk"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="minimumDiskHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'minimumDiskHelp', showDelay: 1"><g:message code="common.help.message.minimumDisk"/></div>
                                </div>
                            </div>
                            <!--<span id="flavorEphemeralDiskLabel" class="hide_lable"></span>-->
                        </div> 
                        <div class="span12 field-box">
                            <label for="minimumRam" class="control-label">              
                                <g:message code="common.minimumRam"/>(<g:message code="common.mb"/>):</label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                id="userMinimumRam" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', trim: 'true',
                                placeHolder: '<g:message code="common.minimumRam"/>', constraints:{min:0,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:0, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.minimumRam"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorSwapDiskHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorSwapDiskHelp', showDelay: 1"><g:message code="common.help.message.flavorSwapDisk"/></div>
                                </div>
                            </div>
                            <span id="flavorSwapDiskLabel" class="hide_lable"></span>
                        </div>
                        <div class="span12 field-box">
                            <label for="imagePublic" class="control-label">
                                <g:message code="common.imageIsPublic"/>:
                            </label>
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="userImageIsPublic">
                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                <i class="icon-info-sign" id="imageIsPublicHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageIsPublicHelp', showDelay: 1"><g:message code="common.help.message.imageIsPublic"/></div>
                            </div>
                        </div>
                    <!--</div>-->
                    <!--<div class="row">-->            

                        <div class="span12 field-box">
                            <label for="imageProtected" class="control-label">
                                <g:message code="common.imageIsProtected"/>:
                            </label>
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="userImageIsProtected"> 
                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                <i class="icon-info-sign" id="imageIsProtectedHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageIsProtectedHelp', showDelay: 1"><g:message code="common.help.message.imageIsProtected"/></div>
                            </div>
                        </div>
<!--                        <div class="span4 field-box control-group">
                            <label for="imageOneTimeChargeable" class="control-label">
                                <g:message code="common.oneTimeChargeable" />:
                            </label>
                            <div class="control">
                                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="imageOneTimeChargeable">
                            </div>
                        </div>
                        <div class="span4 field-box control-group">
                            <label for="imageCost" class="control-label">                   
                                <g:message code="common.cost" />:( 0 - <g:message code="common.forFree" />): <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                                data-dojo-props="placeHolder: '<g:message code="common.value" />',
                                promptMessage:'<g:message code="common.value" />', invalidMessage:'<g:message code="common.value.invalid" />', required: true" id="imageCost" value="0">  
                            </div>
                        </div>-->
                   <!--</div>-->
                   <!--</div>-->
                   <!--<div class="row">-->
                        <div class="span8"></div>
                        <div class="span4 pull-right">
                            <img class="hide_text" id="imageLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                            <div class="">
                                <div id="imageAddButtonDiv" style="display: block;" class="span3">
                                    <button id="imageAddButton"  data-dojo-type="dijit.form.Button" onclick="ImageInfo.add()" class="okbtn">
                                        <g:message code="common.ok"/>
                                    </button>
                                </div>        
                                <div id="imageUpdateButtonDiv" style="display: none;" class="span5">
                                    <button id="imageUpdateButton"  data-dojo-type="dijit.form.Button" onclick=" ImageInfo.update()" class="okbtn">
                                        <g:message code="common.update"/>
                                    </button>
                                </div>
                                <div id="imageCancelButtonDiv" class="span2">
                                    <button id="imageCancelButton"  data-dojo-type="dijit.form.Button" onclick="ImageInfo.cancel()" class="cancelbtn">
                                        <g:message code="common.cancel"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    <!--</div>-->
                <!--</div>-->
                    </div>
                </form>
            </div>    
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="userCreateImageLoader" class="span6">
    <div class="row">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createImageInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createImageInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="ImageInfo.gotoList()"><g:message code='common.gotoImageList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div>
</div>