<div class="row-fluid">
  <div class="span11 breadcrumbs">  
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
    <li>/</li>
    <li><a href="#/admin/billableItem"><g:message code="common.billableItem"/></a></li>
    <li>/</li>
    <li><g:message code="common.edit"/></li>
  </ul>
</div>
</div>

<div id="" class="new-user">
    <div class="row-fluid header">
      <!--<h3>Edit Billable Item</h3>-->
    </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span7 with-sidebar">
      <div class="container">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="billableItemForm">
              <div class="span12 field-box control-group">
                  <label for="billableItemName" class="control-label settings_lable">                    
                    <g:message code="common.name"/>:
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                   <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="
                   required: 'required', disabled: 'true',
                   promptMessage:'billable item name'" 
                   name="billable item name" id="billableItemName">  
                </div>
              </div>
              <div class="span12 field-box control-group">
                  <label for="billableItemEnabled" class="control-label settings_lable">                    
                    <g:message code="common.enabled"/>:
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" id="billableItemEnabled">
                </div>
              </div>
              <div class="span12 field-box control-group">
                 <label for="taxList" class="control-label settings_lable">                    
                    <g:message code="common.tax"/>:
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                <div id="taxListContainer"></div>
                </div>
              </div>
            <div class="span12 field-box control-group" id="isDiscountableDiv">
                  <label for="isDiscountable" class="control-label settings_lable">                    
                    <g:message code="common.discountable"/>:
                    <span class="require">*</span>
                  </label>
              <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" id="isDiscountable">
              </div>
              </div>            
            <div class="pull-right">
<!--              <input type="reset" value="Cancel" class="reset" onclick="EditBillableItem.cancel()">
              <span>OR</span>-->
              <button type="button" id="billableItemButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="EditBillableItem.update()"><g:message code="common.save"/></button>
              <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="billableItemLoader" style="display: none"/>   
                  
              </div>
          </form>
      </div>
    </div>
    <div class="span5">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span id="billableItemNameDescription"><g:message code="common.billableItemInfo"/></span>
        </div>
        <div class="span12 field-box" id ="fixedDayContainer">
          <span id="billableItemEnabledDescription"><g:message code="common.enableInfo"/></span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span id="taxListContainerDescription"><g:message code="common.taxInfo"/></span>
        </div>
        <div class="span12 field-box" id="isDiscountableDivInfo">
          <span id="isDiscountableDescription"><g:message code="common.enableDiscountInfo"/></span>
        </div>
      </div>
    </div>
  </div>
</div>