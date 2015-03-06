<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/billing"><g:message code="menu.admin.billing"/></a></li>
    <li>/</li>
    <li><a href="#/admin/tax"><g:message code="menu.admin.tax"/></a></li>
    <li>/</li>
    <li><g:message code="common.edit"/></li>
  </ul>
</div>
</div>
  

<div class="new-user">
  <div class="row-fluid header">
    <!--<h3>Edit Tax</h3>-->
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span7 with-sidebar">
      <div class="container">
          <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="taxEditForm">
              <div class="span12 field-box control-group">
                <label class="control-label" for="EditTaxName"><g:message code="common.billing.taxName"/>:<span class="require">*</span></label>
                  <div class="controls">
                   <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true',
                      placeHolder: '<g:message code="common.billing.tax.prompt"/>', regExp: '[A-za-z 0-9-./%]{2,20}'" 
                      id="EditTaxName">
                  </div>
              </div>
              <div class="span12 field-box control-group">
                <label class="control-label" for="EditTaxDescription"><g:message code="common.description"/>:<span class="require">*</span></label>
                  <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="invalidMessage: '<g:message code="common.description.invalid"/>', required: 'true',
                      placeHolder: '<g:message code="common.description.prompt"/>', regExp: '[A-za-z 0-9-./%]{1,150}'"
                      id="EditTaxDescription">
                  </div>
              </div>
              <div class="span12 field-box control-group">
                <label class="control-label" for="EditTaxPercentage"><g:message code="common.billing.percentage"/>:<span class="require">*</span></label>
                  <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                    data-dojo-props="promptMessage:'<g:message code="common.billing.taxPercentage.prompt"/>',
                    invalidMessage: '<g:message code="common.value.invalid" />',constraints:{min:0,max:100,pattern:'#'}, 
                    timeoutChangeRate: '0.2',value:1" id="EditTaxPercentage">
                  </div>
              </div>
            <div class="pull-right">
<!--              <input type="reset" value="Cancel" class="reset" onclick="EditTax.resetPage()">
              <span>OR</span>-->
                <button id="editTaxButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="EditTax.updateTax()"><g:message code="common.save" /></button>
               <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="editTaxLoader" style="display: none"/>    
                  
              </div>
          </form>
      </div>
    </div>
    <div class="span5">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span id="editTaxNameLabel"><g:message code="common.billing.nameOfTax" /></span>
        </div>
        <div class="span12 field-box" id ="fixedDayContainer">
          <span id="editDescriptionLabel"><g:message code="common.billing.taxDescription" /></span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span id="editTaxPercentageLabel"><g:message code="common.billing.taxAllocatesPercentage" /></span>
        </div>
      </div>
    </div>
  </div>
</div>