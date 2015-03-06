<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><g:message code="common.vpcDashboard"/></li>
        </ul>
    </div>
</div>
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
        <a title="<g:message code="common.addVPC"/>" class="btn-glow success new-product overflowLabel" href="#/user/vpc/addVpc"><img src="${resource(dir: 'images')}/vm_servicestat_icon.png" alt=""></img><g:message code="common.addVPC"/></a> 
        <a title="<g:message code="common.vpcWizard"/>" class="btn-glow primary new-product overflowLabel" href="#/user/vpc/help"><g:message code="common.vpcWizard"/></a>
    </div>            
 </div>
</div>
    <div class="row-fluid form-wrapper" style="display: none">
            <div class="span6">
                <ul class="span12 configList">
                    <li>
                        <a class="item" href="#">                            
                            Quick Start VPC Wizard
                        </a>
                    </li>
                </ul>
            </div>
            <div class="span6">
                <ul class="span12 configList">
                    <li>
                        <a class="item" href="#">
                            Quick Help Wizard
                        </a>
                    </li>
                </ul>
            </div>            
        </div>
		<div class="row-fluid"><div class="span12"></div></div>
        <div class="row-fluid form-wrapper vpcdb">
            <ul class="span12 configList">               
                <li><h4 class="vpcdb" style="text-align: center"><g:message code="common.vpcDashboard.resourceMsg"/></h4> &nbsp; <span id="vpnDashboardZoneInfoText"></span> <span id="vpcDataCenter" style="font-size: 12px; font-weight: bolder; padding: 10px;"></span></li>
            </ul>
        </div>         
        <div class="row-fluid form-wrapper vpcdb">
                <ul class="span12 configList configListbox">
                    <li class="span3"><span id="vpcCount"></span><a class="item" href="#/user/vpc/list"><g:message code="common.vpc"/></a></li>
                    <li class="span3"><span id="vpcTotalTierCount"></span><a class="item" href="#/user/tier/list"><g:message code="common.tiers"/></a></li>
                    <li class="span3"><span id="vpcTotalVMCount"></span><a class="item" href="#/user/tier/instance"><g:message code="common.instance"/></a></li>
                    <li class="span3"><span id="vpcPublicIPCount"></span><a class="item" href="#/user/tier/publicIP"><g:message code="common.publicIPAdd"/></a></li>
				</ul>
		<div class="row-fluid">
				<ul class="span12 configList configListbox">
                    <li class="span3"><span id="vpcStaticNatCount"></span><a class="item" href="#/user/tier/staticNat"><g:message code="common.staticNat"/></a></li>
                    <li class="span3"><span id="vpcNetworkAclCount"></span><a class="item" href="#/user/vpcSecurity/listNetworkAcl"><g:message code="common.networkACL"/></a></li>
                    <li class="span3"><span id="vpcVPNConnection"></span><a class="item" href="#/user/vpnCustomerGateway/vpnConnection"><g:message code="common.vpnConnection"/></a></li>                    
<!--                </ul>
            </div>
            <div class="span6">
                <ul class="span12 configList">   -->              
                    <li class="span3"><span id="vpcInternalLB"></span><a class="item" href="#/user/tier/internalLB"><g:message code="common.internaleLB"/></a></li>
				</ul>
		</div>
		<div class="row-fluid">
				<ul class="span12 configList configListbox">
                    <li class="span3"><span id="vpcPublicLB"></span><a class="item" href="#/user/tier/publicLB"><g:message code="common.publicLB"/></a></li>
                    <!--<li><span>5</span><a class="item" href="#"><g:message code="common.publicGateway"/></a></li>-->
                    <!--<li><span >5</span><a class="item" href="#"><g:message code="common.vpcGateway"/></a></li>-->
                    <li class="span3"><span id="vpcVpnPrivateGateway"></span><a class="item" href="#/user/gateway/privateGateway"><g:message code="common.privateGateway"/></a></li>
                    <li class="span3"><span id="vpcVpnGateWay"></span><a class="item" href="#/user/gateway/vpnGateway"><g:message code="common.sitetoSiteVPN"/></a></li>
                    <!--<li><span>21</span><a class="item" href="#"><g:message code="common.vpnGateway"/></a></li>-->
                    <li class="span3"><span id="vpcCustomerGateway"></span><a class="item" href="#/user/vpnCustomerGateway/list"><g:message code="common.customerGateway"/></a></li>                    
                </ul>
            </div>            
        </div>
     

