<div class="row">
<div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/billing"><g:message code="menu.admin.billing"/></a></li>
    <li>/</li>
    <li><g:message code="common.customItem"/></li>
  </ul>
</div>

</div>

<g:render template="recurringItemTotalList" />
<div class="row">   
<ul class="nav nav-tabs col-md-12 customNav">
  <li>
    <a href="#/admin/billing/recurringItem"><g:message code="common.recurringItem"/></a>
  </li>
  <li class="active">
    <a href="#/admin/billing/customItem"><g:message code="common.customItem"/></a>
  </li>  
</ul>
</div>
<div class="row">
  <div  class="new-user">
    <div class="table-wrapper products-table">
    <div class="row">
      <!--<h4>Custom Item</h4>-->    
    </div>   
    <div class="row filter-block">
        <div class="pull-right">
            <a class="btn-flat success new-product" href="#/admin/billing/add">+ Add </a>
        </div>
    </div>
    <div class="row">
      <div id="customItemListGrid">
      </div>
      <div class="alert alert-info hide" id="noCustomItemMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
          <g:message code="admin.noCustomItem"/>
         </div>
    </div>
    </div>
  </div>
</div>


<!--<div class="row">
  <div class="col-md-12" id="recurringItemListGrid"></div>
</div>
<div data-dojo-type="dijit.Dialog" title="Add Item"  
     id="updateRecurringItem" class="col-md-8">
  <form class="form-horizontal" id="updateRecurringItemForm" data-dojo-type="dijit.form.Form">
    <input id="currentRecurringItemId" type="hidden">
    <div class="row" id="updateRecurringItemPage"> </div>
    <div class="row">
      <div class="control-group col-md-8">
        <label for="recurringItemAccountName" class="control-label">
          Account:
        </label>
        <div class="controls">
           <span  id="recurringItemAccountName"></span>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="control-group col-md-8">
        <label for="recurringItemName" class="control-label">
           <span  class="require">*</span>
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
    <div class="row">
      <div class="control-group col-md-6"> 
        <label for="recurringItemAmount" class="control-label">
           <span  class="require">*</span>
           Amount
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: 'value',
                   promptMessage:'value', invalidMessage:'Invalid Value.'" id="recurringItemAmount">  
        </div>
      </div> 
    </div>
    <div class="row">
        <div class="control-group col-md-6"> 
          <label for="recurringItemTaxPercentage" class="control-label">
             <span  class="require">*</span>
             Tax Percentage
          </label>
          <div class="controls">
            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                   data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: 'value',
                     promptMessage:'value', invalidMessage:'Invalid Value.'" id="recurringItemTaxPercentage">  
          </div>
        </div> 
      </div>
      <div class="row">
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