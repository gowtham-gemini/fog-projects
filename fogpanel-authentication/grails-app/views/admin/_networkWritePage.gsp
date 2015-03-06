<div class="row-fluid">
    <div class="new-user"> 
        <form data-dojo-type="dijit.form.Form" id="adminMiscNetworkWriteSnapshotForm" class="form-horizontal span12">
            <div class="row-fluid">
                <div class="value_dollar pull-right"><g:message code="default.valueIn" /><span id="networkWriteCurrencyValue"></span></div>  
            </div>
            <div class="row-fluid" id="networkWriteSnapshotPriceLabelContainer">
                <div class="span2 offset1"><h5><g:message code="common.name"/></h5></div>
                <div class="span2 offset2"><h5><g:message code="common.perGB"/></h5></div>                                                                                                     
            </div>
            <div class="row-fluid">              
                <div class="span9 offset1">
                    <div id="networkWriteMiscSnapshotCostInfo" class="span12"></div>          
                </div>                                    
            </div>  
            <div class="row-fluid" id="networkWriteSnapUpdateActionButton">
                 <div class="span4 pull-right">
                    <button type="button" id="miscNetworkWriteSnapButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="NetworkWriteSnapshotInfo.updateNetworkWiteShow()"><g:message code="default.button.update.common" /></button>
                    <img src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' id="miscNetworkWriteSnapLoader" style="display: none"/>
                </div>
            </div>  
            <div class="row-fluid">
                <div class="span12 hide_text" id="networkWriteSnapshotZoneLoader">
                    <img   src="${resource(dir: 'images')}/vmload.gif" alt="reset" height="40" width="150">                                     
                    <p><g:message code="common.message.loading" /></p>
                </div> 
            </div>
            <div class="row-fluid">
                <div class="row-fluid header">    
                </div> 
                <div id="miscNetworkWriteSnapshotInfo"></div>
                <div class="alert alert-info hide" id="noNetworkWriteMiscSnapshotMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.noBandwidthWriteSnapInfo" />
                </div>
            </div> 
        </form>  
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkWriteSnapshotEditConformationDialog" title="<g:message code="default.button.update.common" />" class="span4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="NetworkWriteSnapshotInfo.updateNwWriteSnapshot()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="SnapshotInfo.closeImageSnapshot()"><g:message code="common.cancel" /></button>
    </div>
</div>