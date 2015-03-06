<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/services" ><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><g:message code="menu.images"/></li>    
        </ul>
    </div>
</div>
<!--<div class="row-fluid header">
        <div class="span12">
            <h2>Computing Offer</h2>
        </div>
    </div>-->
<div class="row-fluid"> 
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="adminTotalImages"></span>
                    <g:message code="stat.admin.totalImages"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="adminPublicImages"></span>
                    <g:message code="stat.admin.publicImages"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="adminProtectedImages"></span>
                    <g:message code="stat.admin.protectedImages"/>
                </div>            
            </div>

        </div>
    </div>
</div>
<div class="row-fluid">
    <form id="imageListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row-fluid">
                <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/><span id="imagesCurrencyValue"></span></div>
            </div>
            <div class="row-fluid filter-block" id="adminImagePullDiv" style="display: block;margin-top: 10px;">
                <div class="pull-right">
<!--                    <button type="button" data-dojo-type= "dijit.form.Button" id="pullImageButton" class="cancelbtn" onclick="ListImages.confirmPull()" id="">
                        <g:message code="common.pullImagesFromOpenstack"/>
                        <img id="pullImageLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                    </button>-->
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListImages.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                    <a class="btn-flat success new-product" href="#/admin/images/createImage">+ <g:message code="common.createImage"/></a></div>
            </div>
            <div class="row-fluid">
                <div id="adminImagesList">  
                </div>
                 <div class="alert alert-info text_gray" id="openStackNotConfiguredMsgImage" style="display: none; margin-top: 50px;" >
                    <i class="icon-warning-sign text_gray"></i>
                    <g:message code="common.openStackNotConfigured"/>
                    <a href="#/admin/settings/openstackConfig">
                        <g:message code="common.clickToConfigure"/>
                    </a>
                </div>
                <div class="alert alert-info hide text_gray" id="noImagesMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.admin.noImages"/>&nbsp;&nbsp;<a href="#/admin/images/createImage"><g:message code="common.createOneNow"/></a>
                </div>
            </div>
        </div>
    </form>
</div>        
<div data-dojo-type="dijit.Dialog" id="deleteImageDialog"  title="<g:message code="common.delete"/>" style="color: black; width: 350px;dispaly:none">
    <input type="hidden" id="imageDeleteId"/>
    <div class="row-fluid form-wrapper">
        Are you sure you want to delete this item?
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ImageInfo.confirmDelete()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="ImageInfo.closeDeleteImageDialog()">
                <g:message code="common.no"/>
            </button>       
        </div>  
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="pullImageConfirm" class="span4" style="display:none;">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code='common.openstack.pullImageMessage' /></p></div>
            <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullImageMessageNote' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListImages.pullImages()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListImages.cancelPullImages()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="deleteImageLoader" style="display:none;" class="span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.deleteImageInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.deleteImageInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="ImageInfo.gotoList()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div>
</div>

