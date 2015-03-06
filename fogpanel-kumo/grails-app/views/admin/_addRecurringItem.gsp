<div class="span11 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
    <li>/</li>
      <li><a href="#/admin/recurringItem">Recurring Item</a></li>
    <li>/</li>
    <li>add</li>
  </ul>
</div>
<div id="pad-wrapper" class="new-user">
  <div class="row-fluid header">
    <h3>Add Recurring Item</h3>
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span9 with-sidebar" id="updateRecurringItemPage">
      <div class="container">
          <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="addRecurringItemForm">
            <div class="span12 field-box">
                  <label for="forAccount" class="control-label">                   
                    For Account:
                     <span class="require">*</span>
                  </label>
                  <select data-dojo-type="dijit.form.Select" id="forAccount"
                             data-dojo-props="maxHeight: -1" onchange="AddRecurringItem.showAccountList(this.value)">
                     <option value="ALL">All</option>
                     <option value="SELECTIVE">Selective</option>
                 </select>
              </div>
              <div class="span12 field-box" id="accountListDiv" style="display: none">
                  <label for="invoiceItemAccount" class="control-label">                   
                    Account:
                     <span class="require">*</span>
                  </label>
                  <div class="controls">
                    <select name="discountUsers" id="invoiceItemAccountList" multiple="multiple" ></select>
                    <!--<div id="invoiceItemAccountList"></div>-->
                  </div>
              </div>
              <div class="span12 field-box">
                  <label for="recurringItemName" class="control-label">                   
                    Name:
                     <span class="require">*</span>
                  </label>
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="invalidMessage: 'invalid name', required: 'true',
               placeHolder: 'Enter Tax Name', regExp: '[A-za-z 0-9-./% ()]{2,20}'" 
               id="recurringItemName">
              </div>
              <div class="span12 field-box">
                 <label for="recurringItemAmount" class="control-label">            
                     Amount
                    <span class="require">*</span>
                  </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: 'value',
                   promptMessage:'value', invalidMessage:'Invalid Value.'" id="recurringItemAmount">  
              </div>
              <div class="span12 field-box"> 
                <label for="recurringItemTaxEnabled" class="control-label">
                  Tax Enabled:
                </label>
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                     data-dojo-props="checked: false" id="recurringItemTaxEnabled" onchange="AddRecurringItem.enableTax(this)">
              </div>
              <div class="span12 field-box" id="recurringItemTaxPercentageDiv" style="display: none">
                    <label for="recurringItemTaxPercentage" class="control-label">                    
                       Tax Percentage
                         <span class="require">*</span>
                    </label>
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                     data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: 'value',
                       promptMessage:'value', invalidMessage:'Invalid Value.'" id="recurringItemTaxPercentage">  
              </div>
              <div class="span12 field-box">
                <label for="recurringItemBillingCycles" class="control-label">              
                  Billing Cycles(0-for unlimited)
                  <span class="require">*</span>
                </label>
               <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                       data-dojo-props="required:'true', constraints:{max: 100, pattern:'#'}, placeHolder: 'value',
                         promptMessage:'value', invalidMessage:'Invalid Value.'" value="0" id="recurringItemBillingCycles">
              </div>
            
            <div class="span11 field-box actions">
              <button data-dojo-type="dijit.form.Button" onclick="AddRecurringItem.add();">
           Add
        </button>
                  <span>OR</span>
                  <input type="reset" value="Cancel" class="reset" onclick="AddRecurringItem.cancel()">
              </div>
          </form>
      </div>
    </div>
  </div>
</div>