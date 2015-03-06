<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
    <li>/</li>
    <li class="current"><a href="#/admin/account/summary"><g:message code="common.account"/></a></li>
    <li>/</li>
    <li><a href="#/admin/account/invoice"><g:message code="menu.user.billing.invoice"/></a></li>   
    <li>/</li>
    <li><a href="#/admin/account/viewInvoice"><g:message code="common.view"/></a></li>
    <li>/</li>
    <li id="currentInvoiceId"></li>    
  </ul>
</div>
</div>
<g:render template="viewAccount" />
<div class="row-fluid">   
<ul class="nav nav-tabs span12">
  <li>
    <a href="#/admin/account/summary/"><g:message code="common.summary"/></a>
  </li>
  <li>
      <a href="#/admin/account/infrastructure/"><g:message code="menu.admin.cloud.infrastructure"/></a>
    </li>
  <li class="active">
    <a href="#/admin/account/invoice/"><g:message code="menu.admin.billing.invoice"/></a>
  </li>
    
     <li>
      <a href="#/admin/account/payment/"><g:message code="menu.admin.billing.payments"/></a>
    </li>
     <li>
      <a href="#/admin/account/recurringItem/"><g:message code="menu.user.billing.invoice"/></a>
    </li>
</ul>
</div>
<div class="row-fluid filter-block" >
    <div class="pull-right">  
        <a id="viewInvoiceAction" class="btn-flat success new-product" ><g:message code="common.back"/></a>
        <a style="display: none;" id="viewInvoiceAction" class="btn-flat success new-product" >+ <g:message code="common.addItem"/></a>
    </div>
</div>
<div id="pad-wrapper" class="new-user">
    <div class="row-fluid header">
      <h4><g:message code="common.invoiceInfo"/></h4>
    </div> 
    <div class="row-fluid">    
      <iframe class="span12" height="1000px" id="currentInvoice" name="cuurrentInvoiceFrame"></iframe>
    </div>
</div>
