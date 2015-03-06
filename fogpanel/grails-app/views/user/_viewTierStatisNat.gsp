<div class="row-fluid filter-block">
    <div class="pull-right">
        <button   id=""  data-dojo-type="dijit.form.Button" onclick="TierStatiscNatInfo.populateValues()" class="okbtn">
          <i class="icon-refresh"></i> <g:message code='common.refresh' />
        </button>       
    </div>
    <div class="row-fluid" style="margin-top: 70px;">
    <div id="tierStaticNatGrid"></div>
    <div class="alert alert-info hide overflowLabel" id="noStaticNatMsgBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.userTier.staticnat"/>
    </div>
    </div>
</div>