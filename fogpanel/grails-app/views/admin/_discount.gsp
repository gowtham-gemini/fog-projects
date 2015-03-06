<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
    <li>/</li>
    <li><g:message code="common.discount"/></li>
  </ul>
</div>
</div>

<!--<div id="pad-wrapper">-->
<div class="table-wrapper products-table">    
  <div class="row-fluid filter-block">
    <div class="alert alert-danger errorMessage" id="didcountDidable" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="admin.discountDisabledInfo"/>
    </div>    
    <div class="pull-right" id="discountButton" style="display: none;">                       
      <a class="btn-flat success new-product" href="#/admin/discount/add">+ <g:message code="common.add"/></a>
    </div>
  </div>
  <div class="row-fluid">
    <div id="discountList">  
    </div>
    <div class="alert alert-info hide" id="noDiscountMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="admin.noDiscountInfo"/>
    </div>
  </div>
</div>
<!--</div>-->
               

                
              

              
    








<!--
<h3>Discount</h3>
<a href="#/admin/dashboard">Back to Dashboard</a>
<div class="row-fluid">
  <div class="span8">
    <form class="form-horizontal" id="discountForm" data-dojo-type="dijit.form.Form">
        <input type="hidden" id="currentDiscountListItem">
        <div class="row-fluid" id="discountPage"> 
        <div class="row-fluid">
          <div class="alert alert-danger errorMessage" id="discountNameValidationMessage" style="display: none;">
              Discount name already exists
          </div>
          <div class="control-group span8"> 
            <label for="discountName" class="control-label">
              <span class="require">*</span>
              Discount name
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="promptMessage:'Enter Discount Name', 
                     invalidMessage: 'Enter discountName',required: true,
                     placeHolder: 'Discount Name', regExp: '[a-zA-Z0-9 ]{5,20}'" 
                     id="discountName" onchange="DiscountManagement.validateDiscountName(this.value)"> 
            </div>
          </div> 
        </div> 
        <div class="row-fluid">
          <div class="control-group span4"> 
            <label for="discountType" class="control-label">
              <span class="require">*</span>
              Type
            </label>
            <div class="controls">
              <select data-dojo-type="dijit.form.Select" id="discountType" onchange="DiscountManagement.changeDiscountType(this.value)">
                  <option value="GENERAL">General</option>
                  <option  value="PARTICULAR">Plan based</option>
              </select>
            </div>
          </div> 
        </div>
          <div class="row-fluid" id="subTypeDiv">
          <div class="control-group span6"> 
            <label class="control-label">
              <span class="require">*</span>
              Sub Type
            </label>
            <div class="controls">
                <input type="radio" data-dojo-type="dijit.form.RadioButton" name="subType" id="createVmRadioButton" checked value="CREATE_VM"/> <label for="createVmRadioButton">Create VM</label> <br />
                <input type="radio" data-dojo-type="dijit.form.RadioButton" name="subType" id="invoiceDiscountRadioButton" value="INVOICE_DISCOUNT"/> <label for="invoiceDiscountRadioButton">Invoice Discount</label> <br />
            </div>
          </div> 
        </div>  
        <div class="row-fluid">
          <div class="control-group span4"> 
            <label for="discountValue" class="control-label">
              <span class="require">*</span>
              Value
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                     data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: 'value',
                       promptMessage:'Value', invalidMessage:'Invalid Value.'" id="discountValue">  
            </div>
          </div> 
          <div class="row-fluid span4">
            <span>in percentage (%)</span>
          </div>
        </div>
        <div class="row-fluid">
          <div class="control-group span6"> 
            <label for="discountStartDate" class="control-label">
              <span class="require">*</span>
              Start Date:
            </label>
            <div class="controls">
              <input type="text" name="discountStartDate" id="discountStartDate" onchange="DiscountManagement.validateStartDate(this.value)"
                data-dojo-type="dijit.form.DateTextBox"
                data-dojo-props="required:'true', placeHolder: 'YYYY-MM-DD',
                       promptMessage:'start date (yyyy-MM-dd)', invalidMessage:'Invalid date.', constraints:{datePattern:'yyyy-MM-dd', strict:'true'}"/>
            </div>
          </div> 
          <div class="row-fluid span4">
            <span>(yyyy-mm-dd)</span>
          </div>
        </div>
        <div class="row-fluid">
          <div class="control-group span6"> 
            <label for="discountEndDate" class="control-label">
              <span class="require">*</span>
              End Date:
            </label>
            <div class="controls">
              <input type="text" name="discountEndDate" id="discountEndDate" onchange="DiscountManagement.validateEndDate(this.value)"
                  data-dojo-type="dijit.form.DateTextBox" data-dojo-props="required:'true', placeHolder: 'yyyy-MM-dd',
                       promptMessage:'end date (yyyy-MM-dd)', invalidMessage:'Invalid date.', constraints:{datePattern:'yyyy-MM-dd', strict:'true'}"/>
            </div>
          </div> 
          <div class="row-fluid span4">
            <span>(yyyy-mm-dd)</span>
          </div>
        </div>
        <div class="row-fluid">
          <div class="control-group span4"> 
            <label for="billingCycles" class="control-label">
              <span class="require">*</span>
              Billing Cycles
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                     data-dojo-props="required:'true', constraints:{max: 100, pattern:'#'}, placeHolder: 'value',
                       promptMessage:'value', invalidMessage:'Invalid Value.'" value="1" id="billingCycles">  
            </div>
          </div> 
        </div>
        <input type="hidden" id="discountApplyToValue">
        <div class="row-fluid" id="discountApplyToDiv">
          <div class="control-group span4"> 
            <label for="discountApplyTo" class="control-label">
              <span class="require">*</span>
              <span id="applyToLable">Apply to user:</span>
            </label>
            <div class="controls">
              <select data-dojo-type="dijit.form.Select" id="discountApplyTo" onchange="DiscountManagement.showList(this.value)">
                  <option value="ALL">All</option>
                  <option  value="SELECTIVE">Selective</option>
              </select> 
            </div>
          </div> 
          <div class="row-fluid span4">
            <span>(apply to des)</span>
          </div>
        </div>  
        <div class="row-fluid" id="isAllPlanDiv">
          <div class="control-group span8"> 
            <label for="isAllPlan" class="control-label">
              <span class="require">*</span>
              for All plan:
            </label>
            <div class="controls">
              <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                 data-dojo-props="checked: false" id="isAllPlan" onchange="DiscountManagement.enablePlanList(this)">
            </div>
          </div> 
        </div> 
        <div class="row-fluid" id="discountPlanList">
          <div class="control-group span6"> 
            <label for="discountPlans" class="control-label">
              <span class="require">*</span>
              Plan:
            </label>
            <div class="controls">
              <select name="discountPlans" id="discountPlans" multiple="multiple" onchange="DiscountManagement.getUser()" ></select>
            </div>
          </div>
        </div>
        <div class="row-fluid" id="isAllUserDiv">
          <div class="control-group span8"> 
            <label for="isAllUser" class="control-label">
              <span class="require">*</span>
              for All user:
            </label>
            <div class="controls">
              <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                     data-dojo-props="checked: false" id="isAllUser" onchange="DiscountManagement.enableUserList(this)">
            </div>
          </div> 
        </div> 
        <div class="row-fluid" id="discountUserList">
          <div class="control-group span6"> 
            <label for="discountUsers" class="control-label">
              <span class="require">*</span>
              User:
            </label>
            <div class="controls">
              <select name="discountUsers" id="discountUsers" multiple="multiple" ></select>
            </div>
          </div>
        </div>  
        <div class="row-fluid">
          <button type="button" data-dojo-type= "dijit.form.Button" 
                  onclick="DiscountManagement.add()" id="discountAddButton">
            OK
          </button>
          <button id="" data-dojo-type="dijit.form.Button"
                  onclick="DiscountManagement.cancel()">
            Cancel
          </button>
          <button onclick="DiscountManagement.update()"
                  data-dojo-type="dijit.form.Button"
                  id="discountUpdateButton" style="display: none">
            Apply
          </button>
        </div> 
      </div> 
    </form>
  </div>
  <div class="span4" id="discountListCollection">
    <div id="discountListItem" data-dojo-type="dojo.dnd.Source"></div>
  </div>
</div>
<input type="hidden" id="currentDeleteItem">
<div data-dojo-type="dijit.Dialog" id="deleteDiscountDialog" 
       title="Delete discount" style="color: black; width: 350px;">
  Are you sure you want to delete this item?
  <div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button"
            onclick="DiscountManagement.deleteDiscount()" id="">
    Yes
    </button>
    <button id="" data-dojo-type="dijit.form.Button"
            onclick="DiscountManagement.closeDeleteDialog()">
      No
    </button> 
  </div>  
</div>  -->