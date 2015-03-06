<div class="span11 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard">Home</a></li>
    <li>/</li>
    <li>Recurring Item</li>
  </ul>
</div>

<div id="pad-wrapper">
<div class="table-wrapper products-table">
    <div class="row-fluid head">
        <div class="span12">
            <h4>Recuring Item</h4>
</div>
    </div>   

    <div class="row-fluid filter-block">
        <div class="pull-right">
<!--            <div class="ui-select">
                <select>
                  <option>Filter users</option>
                  <option>Signed last 30 days</option>
                  <option>Active users</option>
                </select>
        </div>-->
            <!--<input type="text" class="search">-->
            <a class="btn-flat success new-product" href="#/admin/recurringItem/add">+ Add </a>
    </div>
    </div>

    <div class="row-fluid">
      <div id="recurringItemListGrid">
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