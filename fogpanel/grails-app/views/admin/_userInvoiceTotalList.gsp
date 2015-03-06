<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
      <div class="span4 stat">
        <div class="data" id="countLabel">
          <div class="span7 price_info_value">
            <div class="number span12">
              <sup id="dailyUsd" class="span2"></sup>
              <span class="span6" id="daily">0</span>
            </div> 
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.usageCost"/></div>
          </div>            
          <div id="dailyDate" class="span11 dbdates usagePeriod"></div>
        </div>  
      </div>
      <div class="span4 stat">                
        <div class="data" id="dailyLabel">
          <div class="span7 price_info_value">
            <div class="number span12">
              <sup class="span2" id="monthlyUsd"></sup>
              <span class="span6" id="monthly">0</span>
            </div>
          </div> 
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.draftInvoiceCost"/></div>
          </div>   
          <div class="span11 dbdates usagePeriod" id="monthlyDate"></div>
        </div>       
      </div>
      <div class="span4 stat">
        <div class="data" id="monthlyLabel">
          <div class="span7 price_info_value">
            <div class="number span12">
              <sup class="span2" id="preMonthUsd"></sup>
              <span class="span6" id="preMonth">0</span>                
            </div>
          </div>                    
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.finalInvoiceCost"/></div>
          </div> 
          <div class="span11 dbdates usagePeriod" id="preMonthDate"></div>
        </div>                   
      </div>      
    </div>
  </div>
</div>