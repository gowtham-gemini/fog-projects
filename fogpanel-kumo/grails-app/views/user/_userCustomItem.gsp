<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><a href="#/user/billing"><g:message code="menu.user.billing"/></a></li>
      <li>/</li>
      <li><g:message code="menu.admin.billing.customItems"/></li>
    </ul>
  </div>
</div>
<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
      <div class="span6 stat child1">
        <div class="data" id="countLabel">
          <div class="span7 price_info_value">
            <div class="number span12">
              <sup  id="recCurrency" class="span2">0</sup>    
              <span  id="recAmt">0</span>                         
            </div>   
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="common.billing.recurringItemCost"/></div>
          </div>          
          <div class="span11 PaymentPeriod dbdates" id="recDate" ></div>
        </div>           
      </div>
      <div class="span6 stat">
        <div class="data" id="dailyLabel">
          <div class="span7 price_info_value">
            <div  class="number span12">
              <sup id="cusCurrency" class="span2">0</sup>          
              <span id="cusAmt">0</span>                      
            </div>
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="common.billing.customItemCost"/></div>
          </div>
        </div>              
        <div class="span11 PaymentPeriod dbdates" id="cusDate"></div>                                   
      </div>   
    </div>
  </div>
</div>
<div class="row-fluid"><div class="span1"></div></div>
<div class="row-fluid">   
  <ul class="nav nav-tabs span12 customNav">
    <li>
      <a href="#/user/billing/recurringItem"><g:message code="menu.user.billing.recurringItems"/></a>
    </li>
    <li class="active">
      <a href="#/user/billing/customItem"><g:message code="menu.admin.billing.customItems"/></a>
    </li>  
  </ul>
</div>
<div class="row-fluid">
  <div id="" class="new-user">
    <div class="table-wrapper products-table">
      <div class="row-fluid header">
      <!--<h4>Custom Item</h4>-->    
      </div>   
      <div class="row-fluid">
        <div id="userCustomItemListGrid"></div>
        <div class="alert alert-info hide" id="noCustomMessageBox" style="display: none">
          <i class="icon-exclamation-sign"></i> 
          <g:message code="common.billing.noCustomItem"/>
        </div>
      </div>
    </div>
  </div>
</div>


<!--<div class="row-fluid">
  <div class="span12" id="recurringItemListGrid"></div>
</div>
<div data-dojo-type="dijit.Dialog" title="Add Item"  
     id="updateRecurringItem" class="span8">
  <form class="form-horizontal" id="updateRecurringItemForm" data-dojo-type="dijit.form.Form">
    <input id="currentRecurringItemId" type="hidden">
    <div class="row-fluid" id="updateRecurringItemPage"> </div>
    <div class="row-fluid">
      <div class="control-group span8">
        <label for="recurringItemAccountName" class="control-label">
          Account:
        </label>
        <div class="controls">
          <span id="recurringItemAccountName"></span>
        </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="control-group span8">
        <label for="recurringItemName" class="control-label">
          <span class="require">*</span>
          Name:
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="invalidMessage: 'invalid name', required: 'true',
               placeHolder: 'Enter Tax Name', regExp: '[A-za-z 0-9-./% ()]{2,20}'" 
               id="recurringItemName">
        </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="control-group span6"> 
        <label for="recurringItemAmount" class="control-label">
          <span class="require">*</span>
           Amount
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: 'value',
                   promptMessage:'value', invalidMessage:'Invalid Value.'" id="recurringItemAmount">  
        </div>
      </div> 
    </div>
    <div class="row-fluid">
        <div class="control-group span6"> 
          <label for="recurringItemTaxPercentage" class="control-label">
            <span class="require">*</span>
             Tax Percentage
          </label>
          <div class="controls">
            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                   data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: 'value',
                     promptMessage:'value', invalidMessage:'Invalid Value.'" id="recurringItemTaxPercentage">  
          </div>
        </div> 
      </div>
      <div class="row-fluid">
        <button data-dojo-type="dijit.form.Button"
                 onclick="RecurringItem.update();">
           Update
        </button>
        <button data-dojo-type="dijit.form.Button"
                  onclick="RecurringItem.cancel();">
           Cancel
        </button>
      </div>
    </form>
  </div>-->