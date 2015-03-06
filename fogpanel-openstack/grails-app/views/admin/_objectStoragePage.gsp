<div class="row-fluid">
    <div class="new-user"> 
        <form data-dojo-type="dijit.form.Form" id="adminMiscObjectStorageSnapshotForm" class="form-horizontal span12">
            <div class="row-fluid">
                <div class="value_dollar pull-right"><g:message code="default.valueIn" /><span id="swiftCurrencyValue"></span></div>  
            </div>
            <div class="row-fluid" id="miscObjectStorePriceLabelContainer">
                <div class="span2 offset1"><h5><g:message code="common.name"/></h5></div>
                <div class="span2 offset2"><h5><g:message code="common.perGBPerHr"/></h5></div>   
                <div class="span2 offset1"><h5><g:message code="common.billingCostPerGBPerHr"/></h5></div>                   
            </div>
            <div class="row-fluid">              
                <div class="span9 offset1">
                    <div id="objectStorageMiscSnapshotCostInfo" class="span12"></div>          
                </div>                                    
            </div>  
            <div class="row-fluid" id="objectStorageActionButton">
                <div class="span4 pull-right">
                    <button type="button" id="objectStorageButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="ObjectStorageInfo.updateObjectStorageShow()"><g:message code="default.button.update.common" /></button>
                    <img src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' id="miscObjectStoreLoader" style="display: none"/>
            </div>
            </div>  
            <div class="row-fluid">
                <div class="span12 hide_text" id="objectStoreZoneLoader">
                    <img   src="${resource(dir: 'images')}/vmload.gif" alt="reset" height="40" width="150">                                     
                    <p><g:message code="common.message.loading" /></p>
                </div> 
            </div>
            <div class="row-fluid">
                <div class="row-fluid header">    
                </div> 
                <div id="miscObjectStoreGridDiv"></div>
                <div class="alert alert-info hide" id="noObjectStoreMsgBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.noObjectStoreMscInfo" />
                </div>
            </div> 
        </form>  
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="objectStorageConformationDialog" title="<g:message code="default.button.update.common" />" class="span4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="ObjectStorageInfo.updateObjectStorage()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="SnapshotInfo.closeImageSnapshot()"><g:message code="common.cancel" /></button>
    </div>
</div>