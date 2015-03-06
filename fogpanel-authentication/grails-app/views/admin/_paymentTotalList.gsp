<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
      <div class="span4 stat">
        <div class="data" id="countLabel">
          <div class="span7 price_info_value">
            <div class="number span12">
              <sup class="span2"  id="dailycurrency"></sup>      
              <span class="span6"  id="daily">0</span>                      
            </div>  
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.todayPayment"/></div>
          </div>
          <div class="span11 PaymentPeriod dbdates" id="dailyDate" ></div>                                             
        </div>           
      </div>
      <div class="span4 stat">
        <div class="data" id="dailyLabel">
          <div class="span7 price_info_value">
            <div class="number span12">
              <sup id="monthlyCurrency" class="span2"></sup>
              <span  id="monthly" class="span6">0</span>              
            </div>
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.currentMonthpayment"/></div>
          </div>
          <div class="span11 PaymentPeriod dbdates" id="monthlyDate" ></div>                          
        </div>
      </div>
      <div class="span4 stat">
        <div class="data" id="monthlyLabel">
          <div class="span7 price_info_value">
            <div class="number span12">
              <sup id="preMonthCurrency"  class="span2"></sup>
              <span id="preMonth"  class="span6">0</span>              
            </div>
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.thisYearPayment"/></div>
          </div>
          <div class="span11 PaymentPeriod dbdates" id="preMonthDate" ></div>
        </div>              
      </div>      
    </div>
  </div>
</div>