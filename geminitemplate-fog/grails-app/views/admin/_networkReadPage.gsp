<div class="row">
    <div class="new-user"> 
        <form data-dojo-type="dijit.form.Form" id="adminMiscNetworkReadSnapshotForm" class="form-horizontal col-md-12">
            <div class="row">
                <div class="value_dollar pull-right"><g:message code="default.valueIn" /> <span  id="networkReadCurrencyValue"></span></div>  
            </div>
            <div class="row" id="networkReadSnapshotPriceLabelContainer">
                <div class="col-md-2 offset1"><h5><g:message code="common.name"/></h5></div>
                <div class="col-md-2 offset2"><h5><g:message code="common.perGB"/></h5></div>                                                                                             
            </div>
            <div class="row">              
                <div class="col-md-9 offset1">
                    <div id="networkReadMiscSnapshotCostInfo" class="col-md-12"></div>          
                </div>                                    
            </div>  
            <div class="row" id="networkReadSnapUpdateActionButton">
                <div class="col-md-4 pull-right">
                    <button type="button" id="miscNetworkReadSnapButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="NetworkReadSnapshotInfo.updateNetworkWiteShow()"><g:message code="default.button.update.common" /></button>
                    <img src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' id="miscNetworkReadSnapLoader" style="display: none"/>
            </div>
            </div>  
            <div class="row">
                <div class="col-md-12 hide_text" id="networkReadSnapshotZoneLoader">
                    <img   src="${resource(dir: 'images')}/vmload.gif" alt="reset" height="40" width="150">                                     
                    <p><g:message code="common.message.loading" /></p>
                </div> 
            </div>
            <div class="row">
                <div class="row header">    
                </div> 
                <div id="miscNetworkReadSnapshotInfo"></div>
                <div class="alert alert-info hide" id="noNetworkReadMiscSnapshotMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.noBandwidthReadSnapInfo" />
                </div>
            </div> 
        </form>  
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkReadSnapshotEditConformationDialog" title="<g:message code="default.button.update.common" />" class="col-md-4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
      <div class="row offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="NetworkReadSnapshotInfo.updateNwWriteSnapshot()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="SnapshotInfo.closeImageSnapshot()"><g:message code="common.cancel" /></button>
    </div>
</div>