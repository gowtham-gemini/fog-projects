<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.yourVPC"/></a></li>
        <li>/</li>
        <li><g:message code="common.staticNAT"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid filter-block">
    <div class="pull-right">
<!--        <button   id=""  data-dojo-type="dijit.form.Button" onclick="" class="okbtn">
          <g:message code="common.addTier"/>
        </button>-->
    </div>
    <div class="row-fluid"><div class="span12"></div></div>
    <div class="row-fluid"><div class="span12"></div></div>    
    <div class="row-fluid">
      <div id="vpcGeneralStaicNat"></div>
    </div>
    <div class="row-fluid">
      <div class="alert alert-info hide" id="noStaticNatIPMsg" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noNetworkIp"/>
      </div>
    </div>
</div>