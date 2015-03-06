<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row">
<div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/billing"><g:message code="menu.admin.billing"/></a></li>
    <li>/</li>
    <li><a href="#/admin/billing/payment"><g:message code="menu.admin.billing.payments"/></a></li>
    <li>/</li>
    <li><g:message code="common.todayPayment"/></li>
  </ul>
</div>
</div>
<g:render template="paymentTotalList" />
<div class="row">   
<ul class="nav nav-tabs col-md-12 customNav">
  <li class="active">
    <a href="#/admin/billing/payment"><g:message code="common.todayPayment"/></a>
  </li>
  <li>
    <a href="#/admin/billing/currentMonth"><g:message code="common.currentMonthpayment"/></a>
  </li> 
  <li>
    <a href="#/admin/billing/thisYear"><g:message code="common.thisYearPayment"/></a>
  </li>   
</ul>
</div>
<div class="row">
  <div  class="new-user">
    <div class="table-wrapper products-table">
    <div class="row header">
      <!--<h4>Today Payments</h4>-->    
    </div>   
    <div class="row filter-block">
<!--        <div class="pull-right">            
            <a class="btn-flat success new-product" href="#/admin/template/addAppTemplate">+ Add</a>
        </div>-->
    </div>
    <div class="row">
      <div id="adminTodayPaymentList">
      </div>
      <div class="alert alert-info hide" id="noPaymentMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="admin.noPaymensts"/>
      </div>
    </div>
    </div>
  </div>
</div>
