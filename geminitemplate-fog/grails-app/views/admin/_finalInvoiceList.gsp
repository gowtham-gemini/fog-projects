<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row">
<div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/billing"><g:message code="menu.admin.billing"/></a></li>
    <li>/</li>
    <li><a href="#/admin/billing/invoice"><g:message code="menu.admin.billing.invoice"/></a></li>
    <li>/</li>
    <li><g:message code="common.final"/></li>
  </ul>
</div>
</div>
<g:render template="userInvoiceTotalList" />
<div class="row">   
<ul class="nav nav-tabs col-md-12 customNav">
  <li>
    <a href="#/admin/billing/draftInvoice"><g:message code="common.draft"/></a>
  </li>
  <li  class="active">
      <a href="#/admin/billing/finalInvoice"><g:message code="common.final"/></a>
    </li> 
</ul>
</div>
<div class="row">
  <div  class="new-user">
    <div class="table-wrapper products-table">
    <div class="row header">
      <!--<h4>Final Invoice</h4>-->    
    </div>   
<!--    <div class="row filter-block">
        <div class="pull-right">            
            <a class="btn-flat success new-product" href="#/admin/template/addAppTemplate">+ Add</a>
        </div>
    </div>-->
    <div class="row">
      <div id="adminFinalInvoiceList">
      </div>
      <div class="alert alert-info hide" id="noFinalInvoiceMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            You don't have any Final Invoice yet. Create one now.
          </div>
    </div>
    </div>
  </div>
</div>
