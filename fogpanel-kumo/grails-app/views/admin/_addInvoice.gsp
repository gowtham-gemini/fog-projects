<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
    <li>/</li>
    <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
    <li>/</li>
    <li class="current"><a href="#/admin/account/summary"></a></li>
    <li>/</li>
    <li><a href="#/admin/account/invoice"><g:message code="menu.user.billing.invoice"/></a></li>   
    <li>/</li>
    <li><a href="#/admin/account/viewInvoice"><g:message code="common.view"/></a></li>   
    <li>/</li>
    <li><a href="#/admin/account/viewInvoice" id="currentAddInvoiceId"></a></li>    
    <li>/</li>
    <li><g:message code="common.add"/></li>
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
<div id="pad-wrapper" class="new-user">
  <div class="row-fluid header">
    <!--<h3>Add Invoice Item</h3>-->
  </div>
  <div class="row-fluid form-wrapper">
  <!-- left column -->
  <div class="span9 with-sidebar" id="addTaxPage">
      <div class="container">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="invoiceAddForm">
            <!--<div class="span12 field-box"></div>-->
              
              <div class="span12 field-box">
                  <label for="invoiceItemName" class="control-label">
                   
                    <g:message code="common.name"/>:
                     <span class="require">*</span>
                  </label>
                   <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true',
                        placeHolder: '<g:message code="common.promptMessage.itemName"/>', regExp: '[A-za-z 0-9-./%]{2,20}'" 
                        id="invoiceItemName">
              </div>
              <div class="span12 field-box">
                <label for="invoiceItemAmount" class="control-label">                  
                   <g:message code="common.amount"/>
                   <span class="require">*</span>
                </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                   promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="invoiceItemAmount">
              </div>
            <div class="span12 field-box">
                <label for="invoiceItemTaxEnabled" class="control-label">
                  <g:message code="common.taxEnabled"/>:
                </label>
                 <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
             data-dojo-props="checked: false" id="invoiceItemTaxEnabled" onchange="AddInvoiceItem.enableTax(this)">
              </div>
            <div class="span12 field-box" style="display: none" id="invoiceItemTaxPercentageDiv">
              <label for="invoiceItemTaxPercentage" class="control-label">
                <g:message code="common.taxPercentage"/>:
                 <span class="require">*</span>
              </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                   data-dojo-props="required:'false', constraints:{max: 100, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                     promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="invoiceItemTaxPercentage">  
              </div>
            <div class="span12 field-box">
              <label for="isRecurring" class="control-label">
                <g:message code="common.isRecurring"/>:
              </label>
              <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
               data-dojo-props="checked: false" id="isRecurring" onchange="AddInvoiceItem.enableRecuringItem(this)">
            </div>
            <div class="span12 field-box" id="recurringItemBillingCyclesDiv" style="display: none">
              <label for="invoiceRecurringItemBillingCycles" class="control-label">
                
                <g:message code="common.billingCycle"/>
                <span class="require">*</span>
              </label>
              <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                       data-dojo-props="required:'false', constraints:{max: 100, pattern:'#'}, placeHolder: '<g:message code="common.value"/>',
                         promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" value="0" id="invoiceRecurringItemBillingCycles">
            </div>
            <div class="pull-right">                            
<!--              <input type="reset" value="Cancel" class="reset"  onclick="AddInvoiceItem.cancel()">
                  <span>OR</span>-->
                  <button id="addItemButton"  class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvoiceItem.add()"><g:message code="common.billing.createInvoiceItem"/></button>
                  <img id="addItemButtonLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading"/>' height='16' width='23' style="display: none"/>   
            </div>
          </form>
      </div>
  </div>    
  </div>
</div>