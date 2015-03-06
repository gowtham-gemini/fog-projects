<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
            <li>/</li>
            <li><a href="#/admin/discount"><g:message code="common.discount"/></a></li>
            <li>/</li>
            <li><g:message code="common.add"/></li>
        </ul>
    </div>
</div>

<div class="new-user">
    <div class="row-fluid header">
      <!--<h3>Add Discount</h3>-->
    </div>
    <div class="row-fluid form-wrapper" id="discountAddPage">
      <!-- left column -->
        <div class="span7 with-sidebar">
            <div class="container">
                <form class="new_user_form form-horizontal" data-dojo-type="dijit.form.Form" id="discountForm">
                    <div class="alert alert-danger errorMessage" id="discountNameValidationMessage" style="display: none;">
                        <g:message code="admin.discountNameExists"/>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="discountName" class="control-label settings_lable">
                            <g:message code="common.discountName"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="promptMessage:'<g:message code="common.discountName"/>', 
                            invalidMessage: '<g:message code="common.enterDiscountName"/>',required: true,
                            placeHolder: '<g:message code="common.enterDiscountName"/>', regExp: '[a-zA-Z0-9 ]{5,20}'" 
                            id="discountName" onchange="AddDiscountManagement.validateDiscountName(this.value)">
                        </div>
                    </div>

                    <div class="span12 field-box control-group">
                        <label for="discountType" class="control-label settings_lable">                            
                            <g:message code="common.type"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <select  data-dojo-type="dijit.form.Select" id="discountType" onchange="AddDiscountManagement.changeDiscountType(this.value)">
                                <option value="USER"><g:message code="common.userBased"/></option>
                                <option  value="PARTICULAR"><g:message code="common.planBased"/></option>
                            </select>
                        </div>
                    </div>
                    <div class="span12 field-box control-group" style="display: none">
                        <label class="control-label settings_lable">                           
                            <g:message code="common.subType"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <label for="createVmRadioButton" class="customLabel"><g:message code="common.CreateVM"/></label> 
                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="subType" id="createVmRadioButton" checked value="CREATE_VM"/>
                            <!--<label for="invoiceDiscountRadioButton" class="customLabel">Invoice Discount</label>-->  
                          <!--<input type="radio" data-dojo-type="dijit.form.RadioButton" name="subType" id="invoiceDiscountRadioButton" value="INVOICE_DISCOUNT"/>-->       
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="discountValue" class="control-label settings_lable">                           
                            <g:message code="common.discountValue"/> : <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                            data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                            promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="discountValue">  
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id="discountStartDateDiv" style="display: none;">
                        <label for="discountStartDate" class="control-label settings_lable">                           
                            <g:message code="common.startDate"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <input type="text" name="discountStartDate" data-dojo-id="discountStartDate" id="discountStartDate" onchange="discountEndDate.constraints.min = arguments[0];"
                            data-dojo-type="dijit.form.DateTextBox"
                            data-dojo-props="required:'true', placeHolder: 'YYYY-MM-DD',
                            promptMessage:'(yyyy-MM-dd)', invalidMessage:'<g:message code="common.value.invalid"/>', constraints:{datePattern:'yyyy-MM-dd', strict:'true'}"/>
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id="discountEndDateDiv" style="display: none;">
                        <label for="discountEndDate" class="control-label settings_lable">                           
                            <g:message code="common.endDate"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <input type="text" name="discountEndDate" data-dojo-id="discountEndDate" id="discountEndDate" onchange="AddDiscountManagement.validateDateRange();discountStartDate.constraints.max = arguments[0];"
                            data-dojo-type="dijit.form.DateTextBox" data-dojo-props="required:'true', placeHolder: 'YYYY-MM-DD',
                            promptMessage:'(yyyy-MM-dd)', invalidMessage:'<g:message code="common.value.invalid"/>', constraints:{datePattern:'yyyy-MM-dd', strict:'true'}"/>  
                        </div>   
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="billingCycles" class="control-label settings_lable">                             
                            <g:message code="common.discountBillingCycle"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                            data-dojo-props="required:'true', constraints:{max: 100, pattern:'#'}, placeHolder: '<g:message code="common.value"/>',
                            promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" value="1" id="billingCycles">
                            <input type="hidden" id="discountApplyToValue">
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id="discountApplyToDiv">
                        <label for="discountApplyTo" class="control-label settings_lable">                            
                            <span id="applyToLable"><g:message code="common.applyToUser"/>:</span> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <select data-dojo-type="dijit.form.Select" id="discountApplyTo" onchange="AddDiscountManagement.showList(this.value)">
                                <option value="ALL"><g:message code="common.all"/></option>
                                <option  value="SELECTIVE"><g:message code="common.selective"/></option>
                            </select> 
                        </div>   
                    </div>
                    <div class="span12 field-box control-group" id="isAllPlanDiv">
                        <label for="isAllPlan" class="control-label settings_lable">                           
                            <g:message code="common.forAllPlan"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="isAllPlan" onchange="AddDiscountManagement.enablePlanList(this)">
                        </div>    
                    </div>
                    <div class="span12 field-box control-group" id="discountPlanList">
                        <label for="discountPlans" class="control-label settings_lable">                           
                            <g:message code="common.plan"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <select name="discountPlans" id="discountPlans" multiple="multiple" onchange="AddDiscountManagement.getUser()" ></select>
                        </div>   
                    </div>
                    <div class="span12 field-box control-group" id="isAllUserDiv">
                        <label for="isAllUser" class="control-label settings_lable">                            
                            <g:message code="common.forAllUser"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="isAllUser" onchange="AddDiscountManagement.enableUserList(this)">
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id="discountUserList">
                        <label for="discountUsers" class="control-label settings_lable">                            
                            <g:message code="common.user"/> : <span class="require">*</span>
                        </label> 
                        <div class="controls">
                            <select name="discountUsers" id="discountUsers" multiple="multiple" ></select>
                        </div>    
                    </div>

                    <div class="pull-right">                       
        <!--                  <input type="reset" value="Cancel" class="reset"  onclick="AddDiscountManagement.cancel()">
                          <span>OR</span>-->
                        <button class="defaultbtn" type="button" data-dojo-type= "dijit.form.Button" 
                        onclick="AddDiscountManagement.add()" id="discountAddButton">
                        <g:message code="common.add"/>   
                        </button>
                        <img id="addDiscountLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="span5">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span id="editTaxNameLabel"><g:message code="admin.discountNameInfo"/></span>
                </div>
                <div class="span12 field-box" id ="fixedDayContainer">
                    <span id="editDescriptionLabel"><g:message code="admin.discountTypeInfo"/>
                    </span>
                </div>
                <div class="span12 field-box" id="" style="display: none">
                    <span id="editTaxPercentageLabel"><g:message code="admin.discountSubTypeInfo"/> </span>
                </div>
                <div class="span12 field-box" id="">
                    <span id="editTaxPercentageLabel"><g:message code="admin.discountValueInfo"/> </span>
                </div>
                <div class="span12 field-box" id="">
                    <span id="editTaxPercentageLabel"><g:message code="admin.discountStartDateInfo"/></span>
                </div>
                <div class="span12 field-box" id="">
                    <span id="editTaxPercentageLabel"> <g:message code="admin.discountEndDateInfo"/></span>
                </div>
                <div class="span12 field-box" id="">
                    <span id="editTaxPercentageLabel">  <g:message code="admin.discountBillingCycleInfo"/></span>
                </div>
                <div class="span12 field-box" id="">
                    <span id="editTaxPercentageLabel">  <g:message code="admin.discountUserInfo"/></span>
                </div>

            </div>
        </div>
    </div>
</div>