<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
    <li>/</li>
    <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
    <li>/</li>
    <li class="current"><a href="#/admin/account/summary"></a></li>
    <li>/</li>
    <li><a href="#/admin/account/recurringItem"><g:message code="common.items"/></a></li>   
    <li>/</li>
    <li><g:message code="default.button.edit.common"/></li>   
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
  <li>
    <a href="#/admin/account/invoice/"><g:message code="menu.admin.billing.invoice"/></a>
  </li>
    
     <li>
      <a href="#/admin/account/payment/"><g:message code="menu.admin.billing.payments"/></a>
    </li>
     <li class="active">
      <a href="#/admin/account/recurringItem/"><g:message code="common.recurringItem"/></a>
    </li>
</ul>
</div>
<div id="pad-wrapper" class="new-user">
<!--  <div class="row-fluid header">
    <h3>Edit Recurring Item</h3>
  </div>-->
  <div class="row-fluid form-wrapper">
    <div class="span9 with-sidebar" id="editRecurringItemPage">
      <div class="container">
        <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="editRecurringItemForm">
           <div class="span12 field-box">
                  <label for="recurringItemAccountName" class="control-label">
                    <g:message code="common.account"/>:
                  </label>
                   <span id="currentRecurringItemAccountName"></span>
            </div>
          <div class="span12 field-box">
                  <label for="recurringItemName" class="control-label">
                    <span class="require">*</span>
                    <g:message code="common.name"/>:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true',
               placeHolder: '<g:message code="common.billing.tax.prompt"/>', regExp: '[A-za-z 0-9-./% ()]{2,20}'" 
               id="currentRecurringItemName">
              </div>
              <div class="span12 field-box">
                 <label for="currentRecurringItemAmount" class="control-label">
                    <span class="require">*</span>
                     <g:message code="common.amount"/>:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                 data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                   promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="currentRecurringItemAmount">  
              </div>
            <div class="span12 field-box">
                  <label for="currentRecurringItemTaxPercentage" class="control-label">
                    <span class="require">*</span>
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
