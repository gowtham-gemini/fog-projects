<div class="row-fluid">
    <div class="row-fluid">
        <div class="span1"></div>
    </div>
    <div class="table-wrapper products-table">
        <div class="row-fluid filter-block">        
            <div class="pull-right">
                <a class="btn-flat success new-product" onclick="VMTierInfo.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>
                <a class="btn-flat success new-product" onClick="VMTierInfo.launchTierVM()" title="<g:message code='common.vm.create'/>"><g:message code="common.vm.create"/></a>        
            </div>
        </div>
        <div class="row-fluid">
            <div id="tierInstanceGrid"></div>
            <div class="alert alert-info hide overflowLabel" id="noTierVmMessageBox" style="display: none">
                <i class="icon-exclamation-sign"></i> 
                <g:message code="common.user.noInstance"/>
            </div>
        </div>
    </div>
</div>


