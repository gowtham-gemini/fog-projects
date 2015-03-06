<div class="row-fluid">
    <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><a href="#/user/cloud/"><g:message code="menu.admin.cloud"/></a></li>
      <li>/<li>   
      <li><g:message code="common.limitSummary"/></li>
    </ul>
  </div>
</div>
<div class="table-wrapper products-table">
    <div class="row-fluid head">
        <div id="resourceLimitDivLoad">
                
        </div>
        <div class="alert alert-info hide" id="unlimitedResourceLimitMessagBox" style="display: none; margin-top: 80px">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.admin.unlimitedResourceLimit"/>
        </div>
        <div id="resourceLimitDataDiv" style="display: none;">
            <div class="span3 pull-right" style="margin-top: 20px;">            
                <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ResourceLimit.enhanceLimitRequestPageRedirect()" id="">
                    <g:message code="common.enhanceLimit"/>
                </button>              
            </div>
            <div class="row-fluid offset1" id="">
                    <div class="row-fluid">
                        <div class="span10 resource_items_cont" id="InstanceLimitDiv">
                            <div class="span3 resource_items_memory">Instance Limit</div>
                            <div class="span4 resource_items_allocation" id="instanceLimitInfo"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="readTotalExe" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="instanceLimitLoader" id="instanceLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid" >
                        <div class="span10 resource_items_cont" id="VolumeLimitDiv">
                            <div class="span3 resource_items_memory"> Volume Limit</div>
                            <div class="span4 resource_items_allocation" id="volumeLimitInfo"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="writeTotalExe" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="volumeLimitLoader" id="volumeLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid" >
                        <div class="span10 resource_items_cont" id="SnapshotLimitDiv">
                            <div class="span3 resource_items_memory"> Snapshot Limit</div>
                            <div class="span4 resource_items_allocation" id="snapshotLimitInfo"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="writeTotalExe" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="snapshotLimitLoader" id="snapshotLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid" >
                        <div class="span10 resource_items_cont" id="NetworkLimitDiv">
                            <div class="span3 resource_items_memory"> Network Limit</div>
                            <div class="span4 resource_items_allocation" id="networkLimitInfo"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="writeTotalExe" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="snapshotLimitLoader" id="networkLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span10 resource_items_cont" id="PublicIPLimitDiv">
                            <div class="span3 resource_items_memory">  Public IP Limit</div>
                            <div class="span4 resource_items_allocation" id="pulicIpInfo"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="pulicIpLimitLoader" id="pulicIpLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span10 resource_items_cont" id="VPCLimitDiv">
                            <div class="span3 resource_items_memory"> VPC Limit</div>
                            <div class="span4 resource_items_allocation" id="vpcLimitInfo"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="vpcLimitLoader" id="vpcLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span10 resource_items_cont" id="PrimaryStorageLimitDiv">
                            <div class="span3 resource_items_memory">Primary Storage Limit (GB)</div>
                            <div class="span4 resource_items_allocation" id="primaryStorageInfo"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="primaryStorageLimitLoader" id="primaryStorageLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span10 resource_items_cont" id="SecondaryStorageLimitDiv">
                            <div class="span3 resource_items_memory">Secondary Storage Limit (GB)</div>
                            <div class="span4 resource_items_allocation" id="secondaryStorageInfo"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="secondaryStorageLimitLoader" id="secondaryStorageLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span10 resource_items_cont" id="vCPULimitDiv">
                            <div class="span3 resource_items_memory">vCPU Limit </div>
                            <div class="span4 resource_items_allocation" id="vcpuLimitInfo"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="vcpuLimitLoader" id="vcpuLimitLoader"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span10 resource_items_cont" id="MemoryLimitDiv">
                            <div class="span3 resource_items_memory">Memory Limit (MB)</div>
                            <div class="span4 resource_items_allocation" id="memoryLimitInfo"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="memoryLimitLoader" id="memoryLimitLoader"></div>
                            </div>
                        </div>
                    </div>           
<!--                    <div class="row-fluid">
                        <div class="span12 resource_refresh">
                            <button data-dojo-type="dijit.form.Button"  onclick="CurrentInstanceInfo.refresh()" class="defaultbtn" title="<g:message code='common.refresh' />"><i class="icon-refresh"></i> <g:message code='common.refresh' /></button> 
                        </div>         
                    </div>-->
                </div>
            
            
            
<!--            <div class="row-fluid">
                <div class="span6">
                    Instance Limit Status
                    <div  id="instanceLimitChart"></div>
                </div>
                <div class="span6">
                    Storage Limit Status
                    <div id="storageLimitChart"></div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6">
                    Volume Limit Status
                    <div id="snapshotLimitChart"></div>
                </div>  
                <div class="span6" id="publicIPLimitChartDiv">
                    Public IP Limit Status
                    <div id="publicIPLimitChart"></div>
                </div> 
            </div>
            <div class="row-fluid">
                <div class="span6">
                    Primary Storage Limit Status (GB)
                    <div id="primaryStorageLimitChart"></div>
                </div>  
                <div class="span6" id="publicIPLimitChartDiv">
                    Secondary Storage Limit Status (GB)
                    <div id="secondaryStorageLimitChart"></div>
                </div> 
            </div>
            <div class="row-fluid">
                <div class="span6">
                    vCPU Limit Status
                    <div id="vCPULimitChart"></div>
                </div>  
                <div class="span6" id="publicIPLimitChartDiv">
                    Memory Limit Status (MB)
                    <div id="memoryLimitChart"></div>
                </div> 
            </div>-->
        </div>
    </div>
</div>