<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><g:message code="menu.admin.billing"/></li>    
    <li>/</li>
    <li><a href="#/admin/billing/invoice"><g:message code="menu.admin.billing.invoice"/></a></li>
    <li>/</li>
    <li><g:message code="common.addInvoiceItem"/></li>
  </ul>
</div>
</div>
<div class="new-user">
  <div class="row-fluid header">
    <!--<h3>Add Invoice Item</h3>-->
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span9 with-sidebar" id="updateRecurringItemPage">
      <div class="container">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="addInvoiceItemForm">
            <div class="span6 field-box control-group">
                  <label for="forAccount" class="control-label">                   
                    <g:message code="common.forAccount"/>:
                     <span class="require">*</span>
                  </label>
                    <div class="controls">
                    <select data-dojo-type="dijit.form.Select" id="forAccountInInvoicePage" data-dojo-props="maxHeight: -1" onchange="AddItemForInvoice.showAccountList(this.value)">
                     <option value="ALL"><g:message code="common.all"/></option>
                     <option value="SELECTIVE"><g:message code="common.selective"/></option>
                    </select>
                 </div>
              </div>
              <div class="span12 field-box control-group" id="accountListDiv" style="display: none">
                  <label for="invoiceItemAccount" class="control-label">                    
                    <g:message code="common.account"/>:
                    <span class="require">*</span>
                  </label>
                
                  <div class="controls">
                    <select name="invoiceItemAccount" id="invoiceItemAccountListInInvoicePage" multiple="multiple" ></select>
                    <!--<div id="invoiceItemAccountList"></div>-->
                  </div>
              </div>
              <div class="span6 field-box control-group">
                  <label for="invoiceItemNameInInvoicePage" class="control-label">                    
                    <g:message code="common.name"/>:
                    <span class="require">*</span>
                  </label>
                  <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',  promptMessage:'<g:message code="common.name.placeHolder"/>', required: 'true',  placeHolder: '<g:message code="common.name.placeHolder"/>', regExp: '[A-za-z 0-9-./% ()]{2,20}'" id="invoiceItemNameInInvoicePage">
                  </div>
              </div>
              <div class="span6 field-box control-group">
                 <label for="invoiceItemAmountInInvoicePage" class="control-label">                    
                     <g:message code="common.amount"/>:
                     <span class="require">*</span>
                  </label>
                  <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>', promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="invoiceItemAmountInInvoicePage">  
                   </div>
              </div>
              <div class="span6 field-box control-group"> 
                <label for="invoiceItemTaxEnabledInInvoicePage" class="control-label">
                  <g:message code="common.taxEnabled"/>:
                </label>
                <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="invoiceItemTaxEnabledInInvoicePage" onchange="AddItemForInvoice.enableTax(this)">
              </div>
              </div>
              <div class="span6 field-box control-group" id="recurringItemTaxPercentageDiv" style="display: none">
                    <label for="invoiceItemTaxPercentageInInvoicePage" class="control-label">
                        <g:message code="common.taxPercentage"/>:
                       <span class="require">*</span>
                    </label>
                    <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>', promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="invoiceItemTaxPercentageInInvoicePage">  
                    </div>
              </div>
              <div class="span6 field-box control-group">
              <label for="invoiceItemIsRecurringInInvoicePage" class="control-label">
                <g:message code="common.isRecurring"/>:
              </label>
              <div class="controls">
              <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="invoiceItemIsRecurringInInvoicePage" onchange="AddItemForInvoice.enableRecuringItem(this)">
            </div>
              </div>
            <div class="span6 field-box control-group" id="recurringItemBillingCyclesDiv" style="display: none">
              <label for="invoiceRecurringItemBillingCycles" class="control-label">                
                <g:message code="common.billingCycle"/>:
                <span class="require">*</span>
              </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="required:'true', constraints:{max: 100, pattern:'#'}, placeHolder: '<g:message code="common.value"/>', promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" value="0" id="invoiceRecurringItemBillingCycles">
                </div>
            </div>
            
            <div class="pull-right">
              <button id="invoiceItemButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="AddItemForInvoice.add();">
           <g:message code="common.add"/>
        </button>
              <img id="invoiceItemLoader" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
<!--                  <span>OR</span>
                  <input type="reset" value="Cancel" class="reset" onclick="AddItemForInvoice.cancel()">-->
              </div>
          </form>
      </div>
    </div>
  </div>
</div>