<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpnCustomerGateway"><g:message code="common.vpnUsers"/></a></li>
        <li>/</li>
        <li><g:message code="common.customerGateway"/></li>    
      </ul>
  </div>
</div>
<div class="table-wrapper products-table">        
  <div class="row-fluid filter-block">
    <div class="pull-right">
        <a class="btn-flat success new-product" onclick="VPNCutomerGatewayList.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>  
      <a class="btn-flat success new-product" href="#/user/vpnCustomerGateway/add">+ <g:message code="common.add"/></a></div>
    </div>
    <div class="row-fluid">
      <div id="vpnCustomerGatewayList">  
        </div>
      <div class="alert alert-info hide" id="noVPNCustomerMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.noVPNCustomerGateway"/>
      </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="vpnCustomerGatewayLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpnCustomerGatewayDeleteDialog" class="span4">
    <input id="currentVPNCustomerGatewayId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.vpnCustomerGatewayDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPNCutomerGatewayList.deleteVPNCustomerGateway()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPNCutomerGatewayList.closeDeleteVPNCustomerGateway()"><g:message code="common.cancel"/></button>
    </div>
</div>