<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><g:message code="menu.admin.billing"/></li>    
    <li>/</li>
    <li><a href="#/admin/billing/recurringItem"><g:message code="common.recurringItem"/></a></li>
    <li>/</li>
    <li><g:message code="common.editRecurringItem"/></li>
  </ul>
</div>
</div>
<div id="" class="new-user">
  <div class="row-fluid header">   
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span9" id="updateRecurringItemPage">
      <div class="container">
          <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="updateRecurringItemForm">
              <div class="span12 field-box">
                  <label for="recurringItemAccountName" class="control-label">
                    <g:message code="common.account"/>:
                  </label>
                   <span id="recurringItemAccountName"></span>
              </div>
              <div class="span12 field-box">
                  <label for="recurringItemName" class="control-label">
                    <span class="require">*</span>
                    <g:message code="common.name"/>:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',  promptMessage:'<g:message code="common.name.placeHolder"/>', required: 'true',  placeHolder: '<g:message code="common.name.placeHolder"/>', regExp: '[A-za-z 0-9-./% ()]{2,20}'" id="recurringItemName">
              </div>
              <div class="span12 field-box">
                 <label for="recurringItemAmount" class="control-label">
                    <span class="require">*</span>
                     <g:message code="common.amount"/>:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>', promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="recurringItemAmount">  
              </div>
            <div class="span12 field-box">
                  <label for="recurringItemTaxPercentage" class="control-label">
                    <span class="require">*</span>
                     <g:message code="common.taxPercentage"/>:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>', promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="recurringItemTaxPercentage">  
              </div>
            
            <div class="pull-right">
              <button class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="EditRecurringItem.updateShow();">
           <g:message code="common.save"/>
        </button>
            </div>
          </form>
      </div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="recItemEditConformationDialog" title="<g:message code="default.button.update.common"/>" class="span4">
    <p><g:message code="admin.updateItem"/></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfo"/></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="EditRecurringItem.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="EditRecurringItem.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>