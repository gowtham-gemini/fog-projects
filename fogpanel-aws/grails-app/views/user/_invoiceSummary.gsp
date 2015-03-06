<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/billing"><g:message code="menu.user.billing"/></a></li>
    <li>/<li>
    <li><g:message code="menu.user.billing.invoice"/></li>
  </ul>
</div>
</div>
<div>
  <div class="table-wrapper products-table">
  <div class="row-fluid">
    <div class="value_dollar"><g:message code="default.valueIn"/>  <span id="invoiceCurrency"></span></div>  
  </div>
    <div class="row-fluid filter-block">
      <div class="pull-right">
      </div>
    </div>
    <div class="row-fluid">
      <div class="span12" id="invoceListGrid"></div>
      <div class="alert alert-info hide" id="noInvoiceMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
       <g:message code="common.user.noInvoice"/> 
      </div>
    </div>
  </div>
</div>
