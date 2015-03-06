<div class="row-fluid filter-block">
    <div class="pull-right" style="display: none;">
        <button   id=""  data-dojo-type="dijit.form.Button" onclick="TierStatiscNatInfo.acquireIp()" class="okbtn">
          <g:message code="common.user.acquireIp"/>
        </button>
    </div>
    <div class="row-fluid" style="margin-top: 70px;">
    <div id="tierStaticNatGrid"></div>
    <div class="alert alert-info hide overflowLabel" id="noStaticNatMsgBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noStaticNatMsg"/>
    </div>
    </div>
</div>