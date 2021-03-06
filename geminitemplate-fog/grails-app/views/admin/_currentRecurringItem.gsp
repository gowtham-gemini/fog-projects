<div class="row" style="display: none;">   
<div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
    <li>/</li>
    <li class="current"><a href="#/admin/account/summary"></a></li>    
    <li>/</li>
    <li><g:message code="common.recurringItem"/></li>    
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
  <li>
    <a href="#/admin/account/invoice/"><g:message code="menu.user.billing.invoice"/></a>
  </li>
    
     <li>
      <a href="#/admin/account/payment/"><g:message code="menu.user.billing.payments"/></a>
    </li>
     <li class="active">
     <a href="#/admin/account/recurringItem/"><g:message code="common.items"/></a>
    </li>
</ul>
</div>
<input type="hidden" id="currentRecurringItemId">
<div  class="new-user" id="currentRecurringItemListPage">
  <div class="row">
    <div class="value_dollar pull-right"><g:message code="default.valueIn"/> <span  id="itemCurrencyValue"></span></div>  
</div> 
  <div class="row">    
  </div> 
  <div class="row filter-block">
        <div class="pull-right">
            <!--<a class="btn-flat success new-product" href="#/admin/billing/add">+ Add </a>-->
        </div>
    </div>
  <div class="row form-wrapper"> 
    <div id="userRecurringListItems"></div>
    <div class="alert alert-info hide" id="noRecurringItemMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.billing.noItemAdmin"/>
      </div>
  </div>
</div>
<div id="currentRecurringItemEditPage" style="display: none;" class="new-user">
<div class="row header">
    <div class="pull-right">
        <button class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="EditCurrentRecurringItem.backToRecurringItemList();">
                <g:message code="common.back"/>
        </button>
    </div>
</div>
<div class="row form-wrapper">
    <div class="col-md-9 with-sidebar" id="editRecurringItemPage">
      <div class="container">
        <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="editRecurringItemForm">
           <div class="col-md-12 field-box">
                  <label for="recurringItemAccountName" class="control-label">
                    <g:message code="common.account"/>:
                  </label>
                    <span  id="currentRecurringItemAccountName"></span>
            </div>
          <div class="col-md-12 field-box">
                  <label for="recurringItemName" class="control-label">
                     <span  class="require">*</span>
                    <g:message code="common.name"/>:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true',
               placeHolder: '<g:message code="common.billing.tax.prompt"/>', regExp: '[A-za-z 0-9-./% ()]{2,20}'" 
               id="currentRecurringItemName">
              </div>
              <div class="col-md-12 field-box">
                 <label for="currentRecurringItemAmount" class="control-label">
                     <span  class="require">*</span>
                     <g:message code="common.amount"/>:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                   promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="currentRecurringItemAmount">  
              </div>
            <div class="col-md-12 field-box">
                  <label for="currentRecurringItemTaxPercentage" class="control-label">
                     <span  class="require">*</span>
                      <g:message code="common.taxPercentage"/>:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                   data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                     promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="currentRecurringItemTaxPercentage">  
            </div>
            <div class="pull-right">
                <img id="editRecurringItemLoader" class="hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="<g:message code="common.reset" />" height="20" width="20">
                <button id="editRecuButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="EditCurrentRecurringItem.update();">
                    <g:message code="default.button.edit.common"/>
                </button>
            </div>
        </form>
      </div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="recurringItemDeleteDialog" class="col-md-4">
    <input type="hidden" id="currentRecurringDeleteItemId">
    <div class="row">
        <div class="col-md-10">
            <div class="col-md-12"><p><g:message code='common.recurringItemDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="DeleteCurrentRecurringItem.populateValues()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="DeleteCurrentRecurringItem.deleteItemClose()"><g:message code="common.cancel"/></button>
    </div>
</div>
