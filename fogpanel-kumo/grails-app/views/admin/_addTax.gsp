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
    <li><g:message code="common.add"/></li>
  </ul>
</div>
</div>

<div  class="new-user">
                <div class="row-fluid header">
                    <!--<h3>Create Tax</h3>-->
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span6 with-sidebar" id="addTaxPage">
                        <div class="container">
                            <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="taxAddForm">
                              <!--<div class="span12 field-box"></div>-->
                                <div class="span12 field-box control-group">
                                  <label for="adminTaxName" class="control-label"><g:message code="common.billing.taxName"/>:<span class="require">*</span></label>
                                    <div class="controls">
                                     <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                        data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true',
                                        placeHolder: '<g:message code="common.billing.tax.prompt"/>', regExp: '[A-za-z 0-9-./%]{2,20}'" 
                                        id="adminTaxName">
                                    </div>
                                </div>
                                <div class="span12 field-box control-group">
                                  <label for="adminTaxDescription" class="control-label"><g:message code="common.description"/>:<span class="require">*</span></label>
                                    <div class="controls">
                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                        data-dojo-props="invalidMessage: '<g:message code="common.description.invalid"/>', required: 'true',
                                        placeHolder: '<g:message code="common.description.prompt"/>', regExp: '[A-za-z 0-9-./%]{1,150}'"
                                        id="adminTaxDescription">
                                    </div>
                                </div>
                                <div class="span12 field-box control-group">
                                  <label for="adminTaxPercentage" class="control-label"><g:message code="common.billing.percentage"/>:<span class="require">*</span></label>
                                    <div class="controls">
                                    <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                      data-dojo-props="promptMessage:'<g:message code="common.billing.taxPercentage.prompt"/>',
                                      invalidMessage: '<g:message code="common.value.invalid" />',constraints:{min:0,max:100,pattern:'#'}, 
                                      timeoutChangeRate: '0.2',value:1" id="adminTaxPercentage">
                                    </div>
                                </div>
                              <div class="pull-right">                            
<!--                                    <input type="reset" value="Cancel" class="reset">
                                    <span>OR</span>-->
                                    <button id="taxButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="TaxInfo.addTax()"><g:message code="common.billing.createTax"/></button>
                                    <img id="taxLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
                                </div>
                            </form>
                        </div>
                    </div>    
                    <div class="span6">
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