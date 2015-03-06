<div class="row-fluid">
    <div class="row-fluid">
        <div class="span1"></div>
    </div>
    <div class="table-wrapper products-table">
        <div class="row-fluid"> 
            <button   id=""  data-dojo-type="dijit.form.Button" onclick="TierPortforwardingInfo.populateValues()" class="okbtn pull-right">
                <i class="icon-refresh"></i> <g:message code='common.refresh' />
            </button>  
        </div>
        <div class="row-fluid">
            <div id="tierPortforwdrGrid"></div>
            <div class="alert alert-info hide overflowLabel" id="noTierPFMsgBox" style="display: none">
                <i class="icon-exclamation-sign"></i> 
                <g:message code="common.userTier.nopf"/>
            </div>
        </div>
    </div>
</div>


