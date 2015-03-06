<div class="row-fluid" style="display: none;">   
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
            <li>/</li>
            <li class="current"><a href="#/admin/account/summary"></a></li>    
            <li>/</li>
            <li><g:message code="menu.admin.cloud.infrastructure"/></li>    
        </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">   
    <ul class="nav nav-tabs span12">
      <li>
        <a href="#/admin/account/summary/"><g:message code="common.summary"/></a>
      </li>
      <li class="active">
          <a href="#/admin/account/infrastructure/"><g:message code="menu.admin.cloud.infrastructure"/></a>
        </li>
      <li>
        <a href="#/admin/account/invoice/"><g:message code="menu.user.billing.invoice"/></a>
      </li>

         <li>
          <a href="#/admin/account/payment/"><g:message code="menu.user.billing.payments"/></a>
        </li>
         <li>
       <a href="#/admin/account/recurringItem/"><g:message code="common.items"/></a>
        </li>

    </ul>
</div>
<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
      <div class="span4 stat">
        <div class="data">
          <div class="span7 price_info_value">
            <div class="number span12">
              <sup id="usageCurrency" class="span2"></sup>
              <span id="usageCost" class="span6">0</span>
            </div>
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.billing.usageCost"/></div>
          </div>              			  
          <div class="span11 dbdates usagePeriod" id="usagePeriod"></div>
        </div>           
      </div>
      <div class="span4 stat">
        <div class="data">
          <div class="span7 price_info_value">
            <div class="number span12" id="">
              <sup id="dueCurrency" class="span2"></sup>
              <span id="currentDue" class="span6">0</span>
            </div>
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.user.currentUsage"/></div>
          </div>
          <div class="span11 dbdates usagePeriod" id="currentDuePeriod"></div>
        </div>              			 				                
      </div>
      <div class="span4 stat">
      <div class="data">
        <div class="span7 price_info_value">
          <div class="number span12" id="">
            <sup id="paymentCurrency"  class="span2"></sup>
            <span id="Payments"  class="span6">0</span>
          </div>
        </div>
        <div class="span5 price_info_details">
          <div class="span12 info_tags"><g:message code="stat.user.payment"/></div>
        </div>
        <div class="span11 dbdates usagePeriod" id="PaymentPeriod"></div>       			  
      </div>  
    </div>  
    </div>
        
  </div>
</div>

<div id="pad-wrapper">
    <div class="row-fluid" style="display: none;">
        <div class="value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="infraCurrencyValue"></span></div>  
    </div> 
  <div class="row-fluid">
  	<div class="span12">
		<div class="span1"></div>
		<div class="db-vm-box-cont span2" id="runningvm">
                <div class="db-vm-box-running-sect">
                        <div class="vm-box-running-value" id="vmRunningCount">0</div>
                        <div class="vm-box-running-title"><i class="icon-tasks"></i> <g:message code="common.vm.runningInstances"/> </div>
                </div>
                <div class="db-vm-box-price-sec">
                    <div class="vm-box-price-value" id="vmRunningAmount">0</div>
    		</div>
		</div>
		<div class="db-vm-box-cont span2" id="stoppedvm">
			<div class="db-vm-box-running-sect">
    			<div class="vm-box-stopped-value" id="vmStopCount">0</div>
        		<div class="vm-box-running-title"><i class="icon-minus-sign"></i><g:message code="common.vm.stopedInstances"/></div>
    		</div>
                <div class="db-vm-box-price-sec">
                    <div class="vm-box-price-value" id="vmStopAmount">0</div>
    		</div>
		</div>
		<div class="db-vm-box-cont span2" id="storagedisk">
			<div class="db-vm-box-running-sect">
    			<div class="vm-box-running-value" id="diskCount">0</div>
        		<div class="vm-box-running-title"><i class="icon-hdd"></i> <g:message code="stat.storageDisk"/></div>
    		</div>
                <div class="db-vm-box-price-sec">
                    <div class="vm-box-price-value" id="diskAmount">0</div>
    		</div>
		</div>
		<div class="db-vm-box-cont span2" id="snapshots">
			<div class="db-vm-box-running-sect">
    			<div class="vm-box-running-value" id="snapCount">0</div>
        		<div class="vm-box-running-title"><i class="icon-camera"></i> <g:message code="stat.snapshots"/></div>
    		</div>
                <div class="db-vm-box-price-sec">
                  <div class="vm-box-price-value" id="snapAmount">0</div>
    		</div>
		</div>
		<div class="db-vm-box-cont span2" id="bandwidth">
			<div class="db-vm-box-running-sect">
    			<div class="vm-box-running-value" id="bandWidthCount">0</div>
        		<div class="vm-box-running-title"><i class="icon-exchange"></i> <g:message code="common.bandwidth"/></div>
    		</div>
                <div class="db-vm-box-price-sec">
                      <div class="vm-box-price-value" id="bandWidthAmount">0</div>
    		</div>
		</div>
	</div>
  </div>
</div>
