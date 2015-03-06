<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/gateway/"><g:message code="common.gateway"/></a></li>
        <li>/</li>
        <li><g:message code="common.privateGateway"/></li>    
      </ul>
  </div>
</div>
<div class="table-wrapper products-table">       
  <!--<div class="row-fluid"><div class="span12"><input type="hidden" id="currentGeneralS2SVPNID"></div></div>-->
  <div class="row-fluid"><div class="span12"></div></div>
  <div class="row-fluid">
      <div class="span12">
           <div class="pull-right"><a class="btn-flat success new-product" onclick="GeneralPrivateGatewayInfo.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>   </div>
      </div>
  </div>  
  <div class="row-fluid">
      <div id="userVpcGeneralPrivateGatewayList"></div>
  </div>
  <div class="row-fluid">      
      <div class="alert alert-info hide" id="noVpcGeneralPrivateGatewayMsgMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.vpc.noVpcPrivateGateway"/>
      </div>
    </div>
</div>
