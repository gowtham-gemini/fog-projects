<div class="row">
    <div class="new-user"> 
        <form data-dojo-type="dijit.form.Form" id="adminMiscImgSnapshotForm" class="form-horizontal col-md-12">
            <div class="row">
                <div class="value_dollar pull-right"><g:message code="default.valueIn" /> <span  id="miscCurrencyValue"></span></div>  
            </div>
            <div class="row" id="imgSnapshotPriceLabelContainer">
                <div class="col-md-2 offset1"><h5><g:message code="common.regionName"/></h5></div>
                <div class="col-md-2 offset2"><h5><g:message code="common.costPerGBPerMonth"/></h5></div>                                        
                <div class="col-md-3 offset1"><h5><g:message code="common.billingCostPerGBPerHr"/></h5></div>                                              
            </div>
            <div class="row">              
                <div class="col-md-9 offset1">
                    <div id="imgMiscSnapshotCostInfo" class="col-md-12"></div>          
                </div>                                    
            </div>  
            <div class="row" id="imgSnapUpdateActionButton">
                <div class="col-md-4 pull-right">
                    <button type="button" id="miscImgSnapButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="SnapshotInfo.updateImageShow()"><g:message code="default.button.update.common" /></button>
                    <img src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' id="miscImgSnapLoader" style="display: none"/>
                </div>
            </div>  
            <div class="row">
                <div class="col-md-12 hide_text" id="imgSnapshotZoneLoader">
                    <img   src="${resource(dir: 'images')}/vmload.gif" alt="reset" height="40" width="150">                                     
                    <p><g:message code="common.message.loading" /></p>
                </div> 
            </div>
            <div class="row">
                <div class="row header">    
                </div> 
                <div id="miscImgSnapshotInfo"></div>
                <div class="alert alert-info hide" id="noImgMiscSnapshotMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.noImageSnapInfo" />
                </div>
            </div> 
        </form>  
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="imageSnapshotEditConformationDialog" title="<g:message code="default.button.update.common" />" class="col-md-4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
      <div class="row offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="SnapshotInfo.updateImageSnapshot()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="SnapshotInfo.closeImageSnapshot()"><g:message code="common.cancel" /></button>
    </div>
</div>