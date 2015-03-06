<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/ec2/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><g:message code="common.ec2Dashboard"/></li>
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
            <a title="<g:message code="common.ec2.launchInstance"/>" class="btn-glow success new-product overflowLabel" href="#/user/server/add"><img src="${resource(dir: 'images')}/vm_servicestat_icon.png" alt=""></img><g:message code="common.ec2.launchInstance"/></a>     
        </div>            
    </div>
</div>

<div class="row-fluid"><div class="span12"></div></div>
<div class="row-fluid form-wrapper vpcdb">
    <ul class="span12 configList">               
        <li><h4 class="vpcdb" style="text-align: center"><g:message code="common.ec2Dashboard.resourceMsg"/></h4> &nbsp; <span id="vpnDashboardZoneInfoText"></span> <span id="vpcDataCenter" style="font-size: 12px; font-weight: bolder; padding: 10px;"></span></li>
    </ul>
</div>         
<div class="row-fluid form-wrapper vpcdb">
    <div class="row-fluid">
        <ul class="span12 configList configListbox">
            <li class="span3"><span id="runningInstanceCount"></span><a class="item" href="#/user/server"><g:message code="common.ec2.running.instances"/></a></li>
            <li class="span3"><span id="imageCount"></span><a class="item" href="#/user/image/list"><g:message code="common.ec2.ami"/></a></li>
            <li class="span3"><span id="volumeCount"></span><a class="item" href="#/user/volume/list"><g:message code="common.ec2.volumes"/></a></li>
            <li class="span3"><span id="snapshotCount"></span><a class="item" href="#/user/snapshot/list"><g:message code="common.ec2.snapshots"/></a></li>
        </ul>
    </div>    
    <div class="row-fluid">
        <ul class="span12 configList configListbox">
            <li class="span3"><span id="securityGroupCount"></span><a class="item" href="#/user/securityGroup/list"><g:message code="common.ec2.securityGroups"/></a></li>
            <li class="span3"><span id="sshKeyCount"></span><a class="item" href="#/user/cloud/sshKey"><g:message code="common.ec2.keypairs"/></a></li>
        </ul>
    </div>
</div>


