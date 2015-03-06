<div class="row-fluid">
    <sec:ifAnyGranted roles="ROLE_FREE_USER">  
        <div><g:message code="common.free"/></div>
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles="ROLE_PAID_USER">
        <div class="row-fluid">
            <div class="span12 breadcrumbs">
                <ul>
                    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
                    <li>/</li>
                    <li><g:message code="menu.user"/></li>
                </ul>
            </div>
        </div> 
        <div id="sshKeyErrorInfo" class="row-fluid" style="display: block;">
            <div class="alert">
                <i class="icon-warning-sign"></i>
                <span id="">
                SSH Private key is stored in DB, for the security reasons you may delete your private key.
                    <a href="#/user/cloud/sshKey"> Click here</a>
                to view private keys
                </span>
            </div>
        </div>
            <div class="row-fluid">
            <div style="height: 150px;">
                <div class="span4">
                    <div class="row-fluid">
                        <div class="head span12">
                            <h5 class="simple_heading"><g:message code="menu.project.info"/></h5>
                        </div>
                    </div>
                    <div class="row-fluid dashboard-info-box" id="projectInfoDiv">
                        <div class="span12 well well-large">
                            <div class="span12 field-box" style="margin-left:10px">
                                <label class="headerLabel"><g:message code="menu.project.name"/></label>
                                <span id="projectName" class="labelValues"></span>
                            </div>
                            <div class="span12 field-box">
                                <label class="headerLabel"><g:message code="menu.project.owner"/></label>
                                <span id="projectOwnerName" class="labelValues"></span>
                            </div>
                            <div class="span12 field-box">
                                <label class="headerLabel"><g:message code="menu.billing.owner"/></label>
                                <span id="billingOwnerName" class="labelValues"></span>
                            </div>
                            <div class="span12">
                                <div>
                                    <hr>
                                </div>
                            </div>
                            <div class="span12">
                                <label class="headerLabel"><g:message code="common.user"/>:</label>
                                <span id="currentUserName" class="labelValues"></span>
                            </div>
                            <div class="span12">
                                <label class="headerLabel"><g:message code="common.group"/>:</label>
                                <span id="groupName" class="labelValues">----------------</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="span8 personal-info">
                    <div class="row-fluid">
                        <div class="head span12">
                            <h5 class="simple_heading"><g:message code="common.resourceQuota"/></h5>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span3">
                            <div id="vmStatsBox" class="zone-cont">
                                <div class="zone-1">
                                    <div class="zone-titles"><g:message code="common.billableItem.instance"/></div>
                                    <div class="zone-counts custom-stat-box">
                                        <i class="iconfg-vm_icon"></i>
                                        <span id="vmCount"></span>
                                    </div>
                                    <div class="zone-pricing">0.00 USD</div>
                                </div>
                            </div>
                        </div>
                        <div class="span3">
                            <div id="vcpuStatsBox" class="zone-cont">
                                <div class="zone-1">
                                    <div class="zone-titles"><g:message code="common.import.vpc"/></div>
                                    <div class="zone-counts custom-stat-box">
                                        <i class="iconfg-netwrk_icon"></i>
                                        <span id="vpcCount"></span>
                                    </div>
                                    <div class="zone-pricing">0.00 USD</div>
                                </div>
                            </div>    
                        </div>
                        <div class="span3">
                            <div id="floatingIpStatBox" class="zone-cont">
                                <div class="zone-1">
                                    <div class="zone-titles"><g:message code="common.subnet"/></div>
                                    <div class="zone-counts custom-stat-box">
                                        <i class="iconosd-ram"></i>
                                        <span id="subnetCount"></span>
                                    </div>
                                    <div class="zone-pricing">0.00 USD</div>
                                </div>
                            </div>    
                        </div>
                        <div class="span3">
                            <div id="cpuRamBox" class="zone-cont">
                                <div class="zone-1">
                                    <div class="zone-titles"><g:message code="menu.securityGroups"/></div>
                                    <div class="zone-counts custom-stat-box">
                                        <i class="iconosd-security_groups"></i>
                                        <span id="securityGroupCount"></span>
                                    </div>
                                    <div class="zone-pricing">0.00 USD</div>
                                </div>
                            </div>    
                        </div>
                    </div>
                    <div class="row-fluid second_row">
                        <div class="span3">
                            <div id="volumesStatsBox" class="zone-cont">
                                <div class="zone-1">
                                    <div class="zone-titles"><g:message code="menu.user.cloud.image"/></div>
                                    <div class="zone-counts custom-stat-box">
                                        <i class="iconosd-volume_storage"></i>
                                        <span id="imagesCount"></span>
                                    </div>
                                    <div class="zone-pricing">0.00 USD</div>
                                </div>
                            </div>
                        </div>
                        <div class="span3">
                            <div id="volumeStorageStatsBox" class="zone-cont">
                                <div class="zone-1">
                                    <div class="zone-titles"><g:message code="common.vpc.internetGateways"/></div>
                                    <div class="zone-counts custom-stat-box">
                                        <i class="iconosd-floating_ip"></i>
                                        <span id="internetGatewayCount"></span>
                                    </div>
                                    <div class="zone-pricing">0.00 USD</div>
                                </div>
                            </div>
                        </div>
                        <div class="span3">
                            <div id="securityGroupStatsBox" class="zone-cont">
                                <div class="zone-1">
                                    <div class="zone-titles"><g:message code="common.ram"/></div>
                                    <div class="zone-counts custom-stat-box">
                                        <i class="iconfg-vm_icon"></i>
                                        0
                                    </div>
                                    <div class="zone-pricing">0.00 USD</div>
                                </div>
                            </div>
                        </div>
                        <div class="span3">
                            <div id="networkStatsBox" class="zone-cont">
                                <div class="zone-1">
                                    <div class="zone-titles"><g:message code="menu.user.services.networks"/></div>
                                    <div class="zone-counts custom-stat-box">
                                        <i class="iconosd-volumes"></i>
                                        0
                                    </div>
                                    <div class="zone-pricing">0.00 USD</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
        
        
        
        
        
        
<!--        <h2>Welcome to fogpanel</h2></br></br>-->
        <div>
<!--        <div class="row-fluid stats-row">
            <div class="span4 stat-box">
                <div id="pieChart1" style="width: 300px; height: 150px; ">No.of Running Instances <span id="runningInstanceSpan"></span> out of 10
                </div>
            </div>
            <div class="span4 stat-box">
                <div id="pieChart2" style="width: 300px; height: 150px; ">No.of Stopped Instances <span id="stoppedInstanceSpan"></span> out of 10</div>
            </div>
            <div class="span4 stat-box">
                <div id="pieChart3" style="width: 300px; height: 150px; ">Total No .of Instances <span id="totalInstanceSpan"></span> out of 10</div>
                <span id="totalInstanceSpan"></span>
            </div>
        </div>
        <div class="row-fluid stats-row">
            <div class="span4 stat-box">
                <div id="noRunningInstanceSpan" style="display: none" class="alert alert-info">
                <g:message code="common.dashboard.noRunnigInstance"/>
                </div>
            </div>
            <div class="span4 stat-box"> 
                <div id="noStoppedInstanceSpan" style="display: none" class="alert alert-info">
                <g:message code="common.dashboard.noStoppedInstance"/>               
                </div>
            </div>
        </div>-->
<!--        <div class="row-fluid stat_cont" id="openstackSetupCheckListDiv" style="display: none;">
            <ul class="span12 configList">
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
        </div>-->
<div style="display: none"> 

<!--<div class="row-fluid" id="dashboardDivLoader" style="display: block;"><img src='images/vmload.gif' alt='<g:message code="common.cannotLoad"/>' height='36' width='100'/> </br> <p class="overflowLabel"><g:message code="common.message.loadingDashboard"/></p></div>-->
<!--<div class="row-fluid" id="dashboardDiv" style="display: none;">
<div class="row-fluid">
    <div id="main-stats">
        <div class="row-fluid stats-row">
        <div class="span3 stat child1">
            <div class="data">
              <div class="span7 price_info_value">
              	<div class="number">
                  <sup id="usageCurrency" class="span3"></sup>
                  <span id="usageCost" class="span6"></span>
                </div>
              </div>
              <div class="span5 price_info_details">
                    <div class="span12 info_tags overflowLabel"><g:message code="stat.billing.usageCost"/></div>
              </div>
              <div class="span11 dbdates usagePeriod" id="usagePeriod"></div>
            </div>
           
        </div>
        <div class="span3 stat">
            <div class="data">
				<div class="span7 price_info_value">
              		<div class="number span12">
                          <sup id="dueCurrency" class="span3"></sup>
                          <span id="currentDue" class="span6"></span>
                    </div>
			  	</div>
				<div class="span5 price_info_details">
			  		<div class="span12 info_tags overflowLabel"><g:message code="stat.user.currentUsage"/></div>
				</div>
				<div class="span11 dbdates currentDuePeriod" id="currentDuePeriod"></div>  
            </div>
        </div>
        <div class="span3 stat">
            <div class="data">
                <div class="span7 price_info_value">
                    <div class="number span12">
                            <sup id="paymentCurrency" class="span3"></sup>
                            <span id="Payments" class="span6"></span>
                    </div>
              	</div>
                <div class="span5 price_info_details">
                    <div class="span12 info_tags overflowLabel"><g:message code="stat.user.payment"/></div>
                </div>	
                <div class="span11 dbdates PaymentPeriod" id="PaymentPeriod"></div>
            </div>  
        </div>
        <div class="span3">
			<div class="span12"></div>
                        <a id="health" title="<g:message code="user.home.cloud.title"/>" class="btn-glow success new-product overflowLabel" href="#/user/health"><img src="${resource(dir: 'images')}/vm_servicestat_icon.png" alt=""></img><g:message code="menu.user.cloud.health"/></a> 
                        <a title="<g:message code="common.vm.create"/>" class="btn-glow primary new-product overflowLabel" href="#/user/cloud/createVm"><img src="${resource(dir: 'images')}/vm_quicklch_icon.png" alt=""></img><g:message code="user.cloud.launchVM"/></a> 
        </div>            
        </div>
    </div>
</div>
  
</div>-->
<hr>
<div class="row-fluid" id="noDataDiv" style="display: none;">
<!--<div class="span6 db_graph">
    <div class="create_item_box span10 offset1">
      <h1 class="overflowLabel">
        <span>Invoice Chart</span>
      </h1>
        <p class="overflowLabel"><g:message code="user.cloud.message"/></p>
        <a title="<g:message code="user.cloud.launchVM"/>" class="btn-flat new-product overflowLabel" href="#/user/cloud/createVm"><img src="${resource(dir: 'images')}/vm_quicklch_icon.png" alt=""></img><g:message code="user.cloud.launchVM"/></a>  
  </div>
</div>-->
<!--<div class="span6 db_graph">
  <div class="create_item_box span10 offset1">
      <h1 class="overflowLabel">
      <g:message code="common.clickHere"/>
      <span><g:message code="common.makePayment"/></span>
      </h1>
        <p class="overflowLabel"><g:message code="common.makePayment"/><g:message code="common.user.noPayment.message"/></p>
        <a title="<g:message code="menu.user.billing.payments"/>" class="btn-flat new-product overflowLabel" href="#/user/billing/payment">+ <g:message code="common.makePayment"/></a>  
  </div>
</div>-->
</div>
<!--<div class="row-fluid" id="chartDivMaster">
    <div id="chartLodDiv" style="display: block;" class="overflowLabel"><img src='images/vmload.gif' alt='Cannot Load Image' height='36' width='100'/> </br> <p><g:message code="common.message.loading"/></p></div>
    <div class="row-fluid" id="chartDiv" style="display: none;">
      <div class="row-fluid">
          <div class="span12">
              <div class="span6 overflowLabel">
                <g:message code="common.summary.invoice"/>
                <div id="invoiceSummaryChart" style="width: 400px; height: 400px;"></div>
              </div>
              <div class="span6 overflowLabel" id="paymentChartNoData">
               <g:message code="common.summary.payment"/>
                <div class="span12 db_graph">
                  <div class="create_item_box span10 offset1">
                      <h1 class="overflowLabel">
                      <g:message code="common.clickHere"/>
                      <span><g:message code="common.user.addTicket"/></span>
                      </h1>
                        <p><g:message code="common.user.addTicket.message"/></p>
                        <a title="<g:message code="menu.user.billing.payments"/>" class="btn-flat new-product overflowLabel" href="#/user/billing/payment"><g:message code="common.makePayment"/></a>  
                  </div>
                </div>
              </div>
              <div class="span6 overflowLabel" id="paymentChartDiv">
                 <g:message code="common.summary.payment"/>
                <div id="paymentSummaryChart" style="width: 400px; height: auto;"></div>
              </div>
          </div>
        </div>
        <div class="row-fluid">
            <div class="span6 overflowLabel" id="recItemChartDiv">
              <g:message code="common.summary.customAndRecurring"/>
              <div id="customItemChart" style="width: 400px; height: 300px;"></div>
              <div id="legend"></div>
            </div>
            <div class="span6 overflowLabel" id="supportChartDiv">
                 <g:message code="common.summary.support"/>
                <div id="supportChart" style="width: 400px; height: 300px;"></div>
            </div>
            <div class="span6 overflowLabel" id="suportChartNoData">
                 <g:message code="common.summary.support"/>
                <div class="span12 db_graph">
                  <div class="create_item_box span10 offset1">
                      <h1 class="overflowLabel">
                     <g:message code="common.clickHere"/>
                      <span><g:message code="common.user.addTicket"/></span>
                      </h1>
                        <p class="overflowLabel"><g:message code="common.user.addTicket.message"/></p>
                        <a title="<g:message code="menu.user.billing.payments"/>" class="btn-flat new-product overflowLabel" href="#/user/support/tickets">+<g:message code="common.user.addTicket"/></a>  
                  </div>
                </div>
              </div>
          
       </div>
    </div>  
</div>-->
</div>
</sec:ifAnyGranted>
</div>  
