<div class="row">
    <div class="new-user"> 
        <form data-dojo-type="dijit.form.Form" id="adminMiscSnapshotForm" class="form-horizontal">      
            <div class="row">
                <div class="value_dollar pull-right"><g:message code="default.valueIn" /> <span  id="volumeSnapCurrencyValue"></span></div>  
            </div>
            <div class="row" id="snapshotPriceLabelContainer">
                <div class="col-md-2 offset1"><h5><g:message code="common.zoneName"/></h5></div>
                <div class="col-md-2 offset2"><h5> <g:message code="common.costPerGBPerMonth"/></h5></div>                                        
                <div class="col-md-3 offset1"><h5> <g:message code="common.billingCostPerGBPerHr"/></h5></div>                                              
            </div>
            <div class="row">              
                <div class="col-md-9 offset1">
                    <div id="miscSnapshotCostInfo" class="col-md-12"></div>          
                </div>                                    
            </div>  
            <div class="row" id="snapUpdateActionButton">
                <div class="col-md-4 pull-right">
                    <button type="button" id="miscSnapButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="VolumeSnapshotInfo.updateShow()"><g:message code="default.button.update.common" /></button>
                  <img src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' id="miscSnapLoader" style="display: none"/>
                </div>
            </div>  
            <div class="row">
                <div class="col-md-12 hide_text" id="snapshotZoneLoader">
                    <img   src="${resource(dir: 'images')}/vmload.gif" alt="reset" height="40" width="150">                                     
                    <p><g:message code="common.message.loading" /></p>
                </div> 
            </div>
            <div class="row">
                <div class="row header">    
                </div> 
                <div id="miscSnapshotInfo"></div>
                <div class="alert alert-info hide" id="noMiscSnapshotMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.noVolumeSnapshot" />
                </div>
            </div> 
        </form>  
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="volumeSnapshotEditConformationDialog" title="<g:message code="default.button.update.common" />" class="col-md-4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
      <div class="row offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="VolumeSnapshotInfo.updateSnapshot()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="SnapshotInfo.closeImageSnapshot()"><g:message code="common.cancel" /></button>
    </div>
</div>