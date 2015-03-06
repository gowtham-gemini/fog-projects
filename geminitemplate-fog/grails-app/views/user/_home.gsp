<div class="row">
    <sec:ifAnyGranted roles="ROLE_FREE_USER">  
        <div><g:message code="common.free"/></div>
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles="ROLE_PAID_USER">
        <div class="row">
            <div class="col-md-12 breadcrumbs">
                <ul>
                    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
                    <li>/</li>
                    <li><g:message code="menu.user"/></li>
                </ul>
            </div>
        </div> 
        <h2>Welcome to fogpanel</h2></br></br>
        <div>

        <div class="row stat_cont" id="openstackSetupCheckListDiv" style="display: none;">
            <ul class="col-md-12 configList">
                <li><h4><g:message code="user.home.setup.message"/></h4></li>
                <li id="networkEmptyAlert" style="display: none;">
                    <a href="#/user/network/list" style="color: #e6250e;" class="item">
                        <i class="icon-warning-sign"></i>
                        <g:message code="common.networkEmptyAlert.message"/>
                    </a> 
                </li>
                <li id="sshKeyEmptyAlert" style="display: none;">
                    <a href="#/user/cloud/sshKey" style="color: #e6250e;" class="item">
                        <i class="icon-warning-sign"></i>
                        <g:message code="common.sshKeyEmptyAlert.message"/>
                    </a> 
                </li>
                <li id="securityGroupEmptyAlert" style="display: none;">
                    <a href="#/user/securityGroup/list" style="color: #e6250e;" class="item">
                        <i class="icon-warning-sign"></i>
                        <g:message code="common.securityGroupEmptyAlert.message"/>
                    </a> 
                </li>
            </ul>
        </div>
<div style="display: none"> 

<!--<div class="row" id="dashboardDivLoader" style="display: block;"><img src='images/vmload.gif' alt='<g:message code="common.cannotLoad"/>' height='36' width='100'/> </br> <p class="overflowLabel"><g:message code="common.message.loadingDashboard"/></p></div>-->
<div class="row" id="dashboardDiv" style="display: none;">
<div class="row">
    <div id="main-stats">
        <div class="row stats-row">
        <div class="col-md-3 stat child1">
            <div class="data">
              <div class="col-md-7 price_info_value">
              	<div class="number">
                  <sup id="usageCurrency" class="col-md-3"></sup>
                   <span  id="usageCost" class="col-md-6"></span>
                </div>
              </div>
              <div class="col-md-5 price_info_details">
                    <div class="col-md-12 info_tags overflowLabel"><g:message code="stat.billing.usageCost"/></div>
              </div>
              <div class="col-md-11 dbdates usagePeriod" id="usagePeriod"></div>
            </div>
           
        </div>
        <div class="col-md-3 stat">
            <div class="data">
				<div class="col-md-7 price_info_value">
              		<div class="number col-md-12">
                          <sup id="dueCurrency" class="col-md-3"></sup>
                           <span  id="currentDue" class="col-md-6"></span>
                    </div>
			  	</div>
				<div class="col-md-5 price_info_details">
			  		<div class="col-md-12 info_tags overflowLabel"><g:message code="stat.user.currentUsage"/></div>
				</div>
				<div class="col-md-11 dbdates currentDuePeriod" id="currentDuePeriod"></div>  
            </div>
        </div>
        <div class="col-md-3 stat">
            <div class="data">
                <div class="col-md-7 price_info_value">
                    <div class="number col-md-12">
                            <sup id="paymentCurrency" class="col-md-3"></sup>
                             <span  id="Payments" class="col-md-6"></span>
                    </div>
              	</div>
                <div class="col-md-5 price_info_details">
                    <div class="col-md-12 info_tags overflowLabel"><g:message code="stat.user.payment"/></div>
                </div>	
                <div class="col-md-11 dbdates PaymentPeriod" id="PaymentPeriod"></div>
            </div>  
        </div>
        <div class="col-md-3">
			<div class="col-md-12"></div>
                        <!--<a id="health" title="<g:message code="user.home.cloud.title"/>" class="btn-glow success new-product overflowLabel" href="#/user/health"><img src="${resource(dir: 'images')}/vm_servicestat_icon.png" alt=""></img><g:message code="menu.user.cloud.health"/></a>--> 
                        <!--<a title="<g:message code="common.vm.create"/>" class="btn-glow primary new-product overflowLabel" href="#/user/cloud/createVm"><img src="${resource(dir: 'images')}/vm_quicklch_icon.png" alt=""></img><g:message code="user.cloud.launchVM"/></a>--> 
        </div>            
        </div>
    </div>
</div>
  
</div>
<hr>
<div class="row" id="noDataDiv" style="display: none;">
<div class="col-md-6 db_graph">
    <div class="create_item_box col-md-10 offset1">
      <h1 class="overflowLabel">
         <span >Invoice Chart</span>
      </h1>
        <!--<p class="overflowLabel"><g:message code="user.cloud.message"/></p>-->
        <!--<a title="<g:message code="user.cloud.launchVM"/>" class="btn-flat new-product overflowLabel" href="#/user/cloud/createVm"><img src="${resource(dir: 'images')}/vm_quicklch_icon.png" alt=""></img><g:message code="user.cloud.launchVM"/></a>-->  
  </div>
</div>
<div class="col-md-6 db_graph">
  <div class="create_item_box col-md-10 offset1">
      <h1 class="overflowLabel">
      <g:message code="common.clickHere"/>
       <span ><g:message code="common.makePayment"/></span>
      </h1>
        <p class="overflowLabel"><g:message code="common.makePayment"/><g:message code="common.user.noPayment.message"/></p>
        <a title="<g:message code="menu.user.billing.payments"/>" class="btn-flat new-product overflowLabel" href="#/user/billing/payment">+ <g:message code="common.makePayment"/></a>  
  </div>
</div>
</div>
<div class="row" id="chartDivMaster">
    <div id="chartLodDiv" style="display: block;" class="overflowLabel"><img src='images/vmload.gif' alt='Cannot Load Image' height='36' width='100'/> </br> <p><g:message code="common.message.loading"/></p></div>
    <div class="row" id="chartDiv" style="display: none;">
      <div class="row">
          <div class="col-md-12">
              <div class="col-md-6 overflowLabel">
                <g:message code="common.summary.invoice"/>
                <div id="invoiceSummaryChart" style="width: 400px; height: 400px;"></div>
              </div>
              <div class="col-md-6 overflowLabel" id="paymentChartNoData">
               <g:message code="common.summary.payment"/>
                <div class="col-md-12 db_graph">
                  <div class="create_item_box col-md-10 offset1">
                      <h1 class="overflowLabel">
                      <g:message code="common.clickHere"/>
                       <span ><g:message code="common.user.addTicket"/></span>
                      </h1>
                        <p><g:message code="common.user.addTicket.message"/></p>
                        <a title="<g:message code="menu.user.billing.payments"/>" class="btn-flat new-product overflowLabel" href="#/user/billing/payment"><g:message code="common.makePayment"/></a>  
                  </div>
                </div>
              </div>
              <div class="col-md-6 overflowLabel" id="paymentChartDiv">
                 <g:message code="common.summary.payment"/>
                <div id="paymentSummaryChart" style="width: 400px; height: auto;"></div>
              </div>
          </div>
        </div>
        <div class="row">
            <div class="col-md-6 overflowLabel" id="recItemChartDiv">
              <g:message code="common.summary.customAndRecurring"/>
              <div id="customItemChart" style="width: 400px; height: 300px;"></div>
              <div id="legend"></div>
            </div>
            <div class="col-md-6 overflowLabel" id="supportChartDiv">
                 <g:message code="common.summary.support"/>
                <div id="supportChart" style="width: 400px; height: 300px;"></div>
            </div>
            <div class="col-md-6 overflowLabel" id="suportChartNoData">
                 <g:message code="common.summary.support"/>
                <div class="col-md-12 db_graph">
                  <div class="create_item_box col-md-10 offset1">
                      <h1 class="overflowLabel">
                     <g:message code="common.clickHere"/>
                       <span ><g:message code="common.user.addTicket"/></span>
                      </h1>
                        <p class="overflowLabel"><g:message code="common.user.addTicket.message"/></p>
                        <a title="<g:message code="menu.user.billing.payments"/>" class="btn-flat new-product overflowLabel" href="#/user/support/tickets">+<g:message code="common.user.addTicket"/></a>  
                  </div>
                </div>
              </div>
          
       </div>
    </div>  
</div>
</div>
</sec:ifAnyGranted>
</div>  
