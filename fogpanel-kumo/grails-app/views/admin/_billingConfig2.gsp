<div id="pad-wrapper" class="new-user">
    <div class="row-fluid header">
        <h3>Billing Configuration</h3>
    </div>

  <div class="row-fluid form-wrapper" id="paymentSettingPage">
         left column 
        <div class="span9 with-sidebar">
            <div class="container">
                <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="billingConfigForm">
                  <div class="span12 field-box"></div>
                    <div class="span12 field-box">
                        <label for="isFixed" class="control-label">
                          <span class="require">*</span>
                          Invoice Generation is Fixed:
                        </label>
                       <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                  data-dojo-props="checked: false" id="isFixed" 
                  onClick="BillingConfig.enableFixed(this)">                           
                    </div>
                  <div class="span12 field-box" id="fixedDayDiv">
                        <label for="fixedDay" class="control-label">
                        <span class="require">*</span>
                        Date of a month
                      </label>
                        <select data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: '-1'" id="fixedDay">
                              <option value="01">01</option>
                           <option value="02">02</option>
                           <option value="03">03</option>
                           <option value="04">04</option>
                           <option value="05">05</option>
                           <option value="06">06</option>
                           <option value="07">07</option>
                           <option value="08">08</option>
                           <option value="09">09</option>
                           <option value="10">10</option>
                           <option value="11">11</option>
                           <option value="12">12</option>
                           <option value="13">13</option>
                           <option value="14">14</option>
                           <option value="15">15</option>
                           <option value="16">16</option>
                           <option value="17">17</option>
                           <option value="18">18</option>
                           <option value="19">19</option>
                           <option value="20">20</option>
                           <option value="21">21</option>
                           <option value="22">22</option>
                           <option value="23">23</option>
                           <option value="24">24</option>
                           <option value="25">25</option>
                           <option value="26">26</option>
                           <option value="27">27</option>
                           <option value="28">28</option>
                         </select> 
                    </div>
                  <div class="span12 field-box" id="billingPeriodDaysDiv" style="display: none">
                        <label for="billingPeriodDays" class="control-label">
                          <span class="require">*</span>
                          Billing period days :
                        </label>
                        <input type="text" 
                      data-dojo-type="dijit.form.ValidationTextBox" 
                      data-dojo-props="invalidMessage: 'invalid No.',
                      required: 'required', placeHolder: 'Enter No of days', 
                      promptMessage:'Enter billing period days'" 
                      name="billingPeriodDays" id="billingPeriodDays"> 
                    </div>               
                  
                  
                    <div class="span12 field-box">
                        <label for="paymentDueDays" class="control-label">
                          <span class="require">*</span>
                           Payment Due Days:
                        </label>
                        <input type="text" 
                          data-dojo-type="dijit.form.ValidationTextBox" 
                          data-dojo-props="invalidMessage: 'invalid No.',
                          required: 'required', placeHolder: 'Enter No of days', 
                          promptMessage:'Enter billing period days'" 
                          name="paymentDueDays" id="paymentDueDays">  
                    </div>
                  <div class="span12 field-box">
                        <label for="creditLimitNotificationLevel1" class="control-label">
                          <span class="require">*</span>
                           Credit Limit Notification Level 1 :
                        </label>
                        <input type="text" 
                            data-dojo-type="dijit.form.NumberTextBox" 
                            data-dojo-props="invalidMessage: 'invalid No.',
                            required: 'required', placeHolder: 'Enter percentage', 
                            promptMessage:'Enter Percentage', constraints:{max: 100, pattern:'#.##'}" 
                            name="lateFeePercentage" id="creditLimitNotificationLevel1">   
                    </div>
                  <div class="span12 field-box">
                        <label for="creditLimitNotificationLevel2" class="control-label">
                          <span class="require">*</span>
                           Credit Limit Notification Level 2 :
                        </label>
                        <input type="text" 
                          data-dojo-type="dijit.form.NumberTextBox" 
                          data-dojo-props="invalidMessage: 'invalid No.',
                          required: 'required', placeHolder: 'Enter percentage', 
                          promptMessage:'Enter Percentage', constraints:{max: 100, pattern:'#.##'}" 
                          name="lateFeePercentage" id="creditLimitNotificationLevel2"> 
                    </div>
                  <div class="span12 field-box">
                        <label for="creditLimitNotificationLevel3" class="control-label">
                            <span class="require">*</span>
                             Credit Limit Notification Level 3:
                          </label>
                        <input type="text" 
                          data-dojo-type="dijit.form.NumberTextBox" 
                          data-dojo-props="invalidMessage: 'invalid No.',
                          required: 'required', placeHolder: 'Enter percentage', 
                          promptMessage:'Enter Percentage', constraints:{max: 100, pattern:'#.##'}" 
                          name="lateFeePercentage" id="creditLimitNotificationLevel3"> 
                    </div>
                  
                  <div class="span11 field-box actions">
                    <button data-dojo-type="dijit.form.Button" onclick="BillingConfig.add();">
                        Update
                      </button>
                        <span>OR</span>
                        <input type="reset" value="Cancel" class="reset">
                    </div>
                </form>
            </div>
        </div> 
        <div class="span3 form-sidebar pull-right">
          <div class="span12"><span id="isFixedDescription"></span></div>
          <div class="span12"><span id="fixedDayDescription"></span></div>
          <div class="span12" style="display: none" id="billingPeriodDaysDescriptionContainer">
            <span id="billingPeriodDaysDescription"></span>
          </div>
          <div class="span12"><span id="paymentDueDaysDescription"></span></div>
          <div class="span12"><span id="creditLimitNotificationLevel1Description"></span></div>
          <div class="span12"><span id="creditLimitNotificationLevel2Description"></span></div>
          <div class="span12"><span id="creditLimitNotificationLevel3Description"></span></div>
        </div>
    </div>
</div>
