<div class="span11 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard">Home</a></li>
    <li>/</li>
    <li><a href="#/admin/settings/billing">Billing</a></li>
    <li>/</li>
    <li><a href="#/admin/promotion">Promotion Code</a></li>
    <li>/</li>
    <li>edit</li>
  </ul>
</div>
<div id="pad-wrapper" class="new-user">
  <div class="row-fluid header">
    <h3>Edit Promotion Code</h3>
  </div>
  <div class="row-fluid form-wrapper" id="editPromotionCodePage">
    <!-- left column -->
    <div class="span9 with-sidebar">
      <div class="container">
          <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="editPromotionCodeForm">
              <div class="span12 field-box">
                  <label for="promotionCode">
                    <span class="require">*</span>
                    Code:
                  </label>
                   <input type="text" 
                        data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="invalidMessage: 'invalid Code',
                        required: 'required', placeHolder: 'Enter Code', 
                        regExp: '[a-zA-Z0-9-]{4,20}', 
                        promptMessage:'Enter Code'" 
                        name="promotionCode" id="editPromotionCode"> 
              </div>
              
              <div class="span12 field-box">
                  <label for="promotionType" class="control-label">
                    <span class="require">*</span>
                    Type
                  </label>
                      <select data-dojo-type="dijit.form.Select" id="editPromotionType">
                      <option value="FIXED">Fixed Amount</option>
                      <option  value="PERCENTAGE">Percentage</option>
                  </select>
              </div>
              <div class="span12 field-box">
                  <label for="promotionValue" class="control-label">
                    <span class="require">*</span>
                    Value
                  </label>
                      <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                     data-dojo-props="required:'true', constraints:{pattern:'#.##'}, placeHolder: 'value',
                       promptMessage:'value', invalidMessage:'Invalid Value.'" id="editPromotionValue">  
              </div>
            <div class="span12 field-box">
                  <label for="promotionStartDate" class="control-label">
                    Start Date:
                  </label>
                      <input type="text" name="date1" id="editPromotionStartDate"
                data-dojo-type="dijit.form.DateTextBox"
                data-dojo-props="constraints:{datePattern:'yyyy-MM-dd', strict:'true'}"/>
              </div>
            <div class="span12 field-box">
                  <label for="promotionEndDate" class="control-label">
              End Date:
            </label>
            <input type="text" name="date1" id="editPromotionEndDate"
                  data-dojo-type="dijit.form.DateTextBox" data-dojo-props="constraints:{datePattern:'yyyy-MM-dd', strict:'true'}"/>
              </div>
<!--            <div class="span12 field-box">
                  <label for="applyTo" class="control-label">
              <span class="require">*</span>
              Apply to:
            </label>
            <select id="applyTo" multiple="multiple" ></select>
              </div>-->
            <div class="span12 field-box">
                  <label for="promotionMaximumUses" class="control-label">
              Maximum Uses:
            </label>
            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                     data-dojo-props="placeHolder: 'Maximum Uses',
                       promptMessage:'Maximum Uses', invalidMessage:'Invalid number.'" 
                       id="editPromotionMaximumUses">    
              </div>            
            <div class="span12 field-box">
                  <label for="promotionCodeUses" class="control-label">
              Uses:
            </label>
            <span id="editPromotionCodeUses"></span>  
              </div>
            <div class="span12 field-box">
                  <label for="promotionNotes" class="control-label">
              <span class="require">*</span>
              Admin notes:
            </label>
            <textarea data-dojo-type="dijit.form.SimpleTextarea"id="editPromotionNotes" data-dojo-props="trim: 'true'"></textarea>
              </div>
            
            
            
            <div class="span11 field-box actions">
              <input type="reset" value="Cancel" class="reset" onclick="EditPromotionalCode.cancel()">
              <span>OR</span>
              <button type="button" data-dojo-type="dijit.form.Button" onclick="EditPromotionalCode.update()">Edit</button>
                  
                  
              </div>
          </form>
      </div>
    </div>
    <div class="span3">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span>Promotion Code</span>
        </div>
        <div class="span12 field-box" id ="fixedDayContainer">
          <span>Type</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span>Value</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span>Start Date</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span>End Date</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span> Maximum Uses </span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span>Uses</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span>Admin notes</span>
        </div>
         
      </div>
    </div>
  </div>
</div>