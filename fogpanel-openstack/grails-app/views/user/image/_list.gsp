<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud" ><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.imageList"/></li>    
        </ul>
    </div>
</div>
<div class="row-fluid">
    <form id="userListImageForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row-fluid">
                <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="imageCurrencyValue"></span></div>
            </div>
            <div class="row-fluid filter-block">
                <div class="pull-right">
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="Image.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                    <a class="btn-flat success new-product" href="#/user/image/create" class="defaultbtn" title="<g:message code='common.createImage'/>"> + <g:message code="common.createImage"/></a> 
                </div>
            </div>
            <div class="row-fluid">
                <div id="userImageList">  
                </div>
                <div class="alert alert-info hide text_gray" id="noUserImageMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="user.noImage"/>&nbsp;&nbsp;<a href="#/user/image/create"><g:message code="common.pleaseCreateOneNow"/></a>
                </div>
            </div>
        </div>
    </form>
</div>
<input type="hidden" id="selectedImageId">
<div data-dojo-type="dijit.Dialog" id="userDeleteImageDialog"  title="<g:message code="common.delete"/>" style="color: black; width: 350px;dispaly:none">
    <input type="hidden" id="imageDeleteId"/>
    <div class="row-fluid form-wrapper">
        Are you sure you want to delete this item?
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="Image.confirmDelete()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="Image.closeDeleteImageDialog()">
                <g:message code="common.no"/>
            </button>       
        </div>  
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="userDeleteImageLoader" style="display:none;" class="span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.deleteImageInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.deleteImageInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="Image.gotoList()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div>
</div>