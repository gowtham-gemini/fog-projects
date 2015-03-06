<div class="row" style="display: none;">   
    <div class="col-md-12 breadcrumbs">
      <ul>
        <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/admin/account"><g:message code="common.account"/></a></li>    
        <li>/</li>
        <li class="current"><a href="#/admin/account/summary"></a></li>    
        <li>/</li>
        <li><g:message code="menu.admin.billing.invoice"/></li>    
      </ul>
    </div>
</div>
<div class="row" style="display: none;">   
    <ul class="nav nav-tabs col-md-12">
      <li>
        <a href="#/admin/account/summary/"><g:message code="common.summary"/></a>
      </li>
      <li>
          <a href="#/admin/account/infrastructure/"><g:message code="menu.admin.cloud.infrastructure"/></a>
        </li>
      <li class="active">
        <a href="#/admin/account/invoice/"><g:message code="menu.user.billing.invoice"/></a>
      </li>

         <li>
          <a href="#/admin/account/payment/"><g:message code="menu.user.billing.payments"/></a>
        </li>
         <li>
          <a href="#/admin/account/recurringItem/"><g:message code="common.items"/></a>
        </li>
    </ul>
</div>
<div class="new-user">
    <div class="row">
          <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>  <span  id="invoiceCurrencyValue"></span></div>  
    </div> 
    <div class="row" id="showInvoiceListDiv">
        <div id="userInvoiceList"></div>
        <div class="alert alert-info hide" id="noUserInvoiceMessageBox" style="display: none">
          <i class="icon-exclamation-sign"></i> 
          <g:message code="common.billing.noInvoice"/>
        </div>
    </div>
    <div class="row" id="showCurrentInvoice" style="display: none;">
        <input type="hidden" id="currentInvoiceId">
        <div class="row filter-block" >
            <div class="pull-right">  
                <a onclick="CurrentInvoiceInfo.backToListInvoice()" class="btn-flat success new-product" ><g:message code="common.back"/></a>
                <a onclick="CurrentInvoiceInfo.showAddItemForm()" id="viewInvoiceAction" class="btn-flat success new-product" >+ <g:message code="common.addItem"/></a>
            </div>
        </div>
        <div class="new-user">
            <div class="row header">
              <h4><g:message code="common.invoiceInfo"/></h4>
            </div> 
            <div class="row">    
              <iframe class="col-md-12" height="1000px" id="currentInvoice" name="cuurrentInvoiceFrame"></iframe>
            </div>
        </div>
    </div>
</div>
<div id="showAddItem" class="new-user">
  <div class="row header">
    <div class="pull-right">  
        <a onclick="CurrentInvoiceInfo.backToInvoice()" class="btn-flat success new-product" ><g:message code="common.back"/></a>
    </div>
  </div>
  <div class="row form-wrapper">
  <!-- left column -->
  <div class="col-md-9 with-sidebar" id="addTaxPage">
      <div class="container">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="invoiceAddForm">
            <!--<div class="col-md-12 field-box"></div>-->
              
              <div class="col-md-12 field-box">
                  <label for="invoiceItemName" class="control-label">
                   
                    <g:message code="common.name"/>:
                      <span  class="require">*</span>
                  </label>
                   <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true',
                        placeHolder: '<g:message code="common.promptMessage.itemName"/>', regExp: '[A-za-z 0-9-./%]{2,20}'" 
                        id="invoiceItemName">
              </div>
              <div class="col-md-12 field-box">
                <label for="invoiceItemAmount" class="control-label">                  
                   <g:message code="common.amount"/>
                    <span  class="require">*</span>
                </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                   promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="invoiceItemAmount">
              </div>
<!--            <div class="col-md-12 field-box">
                <label for="invoiceItemTaxEnabled" class="control-label">
                  <g:message code="common.taxEnabled"/>:
                </label>
                 <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
             data-dojo-props="checked: false" id="invoiceItemTaxEnabled" onchange="AddInvoiceItem.enableTax(this)">
              </div>
            <div class="col-md-12 field-box" style="display: none" id="invoiceItemTaxPercentageDiv">
              <label for="invoiceItemTaxPercentage" class="control-label">
                <g:message code="common.taxPercentage"/>:
                  <span  class="require">*</span>
              </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                   data-dojo-props="required:'false', constraints:{max: 100, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                     promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="invoiceItemTaxPercentage">  
              </div>-->
            <div class="col-md-12 field-box">
              <label for="isRecurring" class="control-label">
                <g:message code="common.isRecurring"/>:
              </label>
              <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
               data-dojo-props="checked: false" id="isRecurring" onchange="AddInvoiceItem.enableRecuringItem(this)">
            </div>
            <div class="col-md-12 field-box" id="recurringItemBillingCyclesDiv" style="display: none">
              <label for="invoiceRecurringItemBillingCycles" class="control-label">
                
                <g:message code="common.billingCycle"/>
                 <span  class="require">*</span>
              </label>
              <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                       data-dojo-props="required:'false', constraints:{max: 100, pattern:'#'}, placeHolder: '<g:message code="common.value"/>',
                         promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" value="0" id="invoiceRecurringItemBillingCycles">
            </div>
            <div class="pull-right">                            
<!--              <input type="reset" value="Cancel" class="reset"  onclick="AddInvoiceItem.cancel()">
                   <span >OR</span>-->
                  <button id="addItemButton"  class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvoiceItem.add()"><g:message code="common.billing.createInvoiceItem"/></button>
                  <img id="addItemButtonLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading"/>' height='16' width='23' style="display: none"/>   
            </div>
          </form>
      </div>
  </div>    
  </div>
</div>
