<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
            <li>/</li>
            <li><g:message code="common.invoice"/></li>    
        </ul>
    </div>
</div>

<div id="" class="new-user">
    <div class="row-fluid header">
      <!--<h3>Invoice</h3>-->
    </div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span6 with-sidebar" id="invoiceInfoPage">
            <div class="container">
                <form class="new_user_form form-horizontal" data-dojo-type="dijit.form.Form" id="invoiceForm">
                          <!--<div class="span12 field-box"></div>-->
                    <div class="span12 field-box control-group">
                        <label for="isFixed" class="control-label settings_lable">                          
                            <g:message code="common.invoiceGenerationFixed"/>:
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                            data-dojo-props="checked: false" id="isFixed" 
                            onClick="InvoiceInfo.enableFixed(this)">                           
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id="fixedDayDiv">
                        <label for="fixedDay" class="control-label settings_lable">                        
                            <g:message code="common.dateOfmonth"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
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
                    </div>
                    <div class="span12 field-box control-group" id="billingPeriodDaysDiv" style="display: none">
                        <label for="billingPeriodDays" class="control-label settings_lable">                          
                            <g:message code="common.billingPeriodDaye"/> :
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', required: 'required', placeHolder: '<g:message code="common.enterNoOfDays"/>', promptMessage:'<g:message code="common.enterNoOfDays"/>', constraints:{ min:25, max:1000 }" name="billingPeriodDays" id="billingPeriodDays"> 
                        </div>
                    </div>  
                    <div class="span12 field-box control-group">
                        <label for="paymentDueDays" class="control-label settings_lable">                         
                            <g:message code="common.dueDays"/>:
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',required: 'required', placeHolder: '<g:message code="common.enterNoOfDays"/>', promptMessage:'<g:message code="common.enterNoOfDays"/>'," name="paymentDueDays" id="paymentDueDays">  
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                            <label for="language" class="control-label settings_lable"><g:message code="common.dateFormat"/>:</label>
                            <div class="controls">
                                <select name="language" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: 100"  id="adminDateFormatWidget">                                                                        
                                    <option value="MMM/dd/yyyy">MMM/DD/YYYY</option>             
                                    <option value="dd/MMM/yyyy">DD/MMM/YYYY</option>
                                    <option value="M/d/yyyy">M/D/YYYY</option>
                                    <option value="M/d/yy">M/D/YY</option>
                                    <option value="MM/dd/yy">MM/DD/YY</option>
                                    <option value="MM/dd/yyyy">MM/DD/YYYY</option>
                                    <option value="yy-MM-dd">YY/MM/DD</option>
                                    <option value="yyyy-MM-dd">YYYY-MM-DD</option>
                                    <option value="dd-MMM-yyyy">DD-MMM-YY</option>
                                    <option value="M-d-yyyy">M-D-yyyy</option>
                                    <option value="M-d-yy">M-D-YY</option>
                                    <option value="MM-dd-yy">MM-DD-YY</option>            
                                    <option value="MM-dd-yyyy">MM-DD-YYYY</option>            
                                    <option value="yy-MM-dd">YY-MM-DD</option>            
                                </select>
                            </div>
                        </div>
                    <div class="pull-right">
  <!--                    <input type="reset" value="Cancel" class="reset" onclick="InvoiceInfo.cancel()">
                      <span>OR</span>-->
                        <button id="invoiceButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="InvoiceInfo.add()"><g:message code="common.update"/></button>
                        <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="invoiceLoader" style="display: none"/>   


                    </div>
                </form>
            </div>
        </div> 
        <div class="span6">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span id="isFixedDescription"><g:message code="admin.fixedBillingInfo"/></span>
                </div>
                <div class="span12 field-box" id ="fixedDayContainer">
                    <span id="fixedDayDescription"><g:message code="admin.fixedDateInfo"/></span>
                </div>
                <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer" style="display: none">
                    <span id="billingPeriodDaysDescription"><g:message code="admin.billingCycleInfo"/></span>
                </div>
                <div class="span12 field-box">
                    <span id="paymentDueDaysDescription"><g:message code="admin.dueDaysInfo"/></span>
                </div>
            </div>
        </div>
    </div>
</div>