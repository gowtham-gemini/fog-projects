<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
    <li>/</li>
    <li><g:message code="common.paymentGateway"/></li>    
  </ul>
</div>
</div>
<!--<div class="row-fluid">
<ul class="nav nav-tabs span12">
  <li class="active">
    <a href="#/admin/settings/paymentGatway"><g:message code="common.paymentGateway"/></a>
  </li>
  <li>
    <a href="#/admin/settings/paymentConfig"><g:message code="common.processingFeeSetting"/></a>
  </li>
</ul>
</div>
<div  class="new-user">
  <div class="row-fluid form-wrapper"> 
    <ul class="span12 configList">
      <li>
        <a class="item" href="#/admin/settings/paypal">
          <i class="icon-envelope-alt"></i><g:message code="common.paypal"/></a>
        <a class="time" href="#/admin/settings/paypal">
          <i class="icon-cog"></i></a>
      </li>            
      <li>
        <a class="item" href="#/admin/settings/ccAvenue">
          <i class="icon-envelope-alt"></i><g:message code="common.ccAvenue"/></a>
        <a class="time" href="#/admin/settings/ccAvenue">
          <i class="icon-cog"></i></a>
      </li>       
    </ul>      
  </div>
</div>
-->
<div class="row-fluid">
  <div id="pad-wrapper">
    <div class="table-wrapper products-table">   
      <div class="row-fluid head">
          <div class="span12">
              <!--<h4>Accounts</h4>-->
          </div>
      </div>
      <div class="row-fluid">
        <div id="paymentGatewayGrid"></div>
        <div class="alert alert-info hide" id="noClientMessageBox" style="display: none">
          <i class="icon-exclamation-sign"></i> 
          <g:message code="common.admin.noClientFound"/>
        </div>        
      </div>  
      <div class="pull-right">              
        <button id="paymentGatewayButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="PaymentGatewayList.updatePaymentGateways();">
            <g:message code="common.update"/>
        </button>
        <img id="ccAvenueLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
      </div>
    </div>    
  </div>
</div>