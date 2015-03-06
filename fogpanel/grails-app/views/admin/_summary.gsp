<div class="row-fluid">   
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
            <li>/</li>
            <li id="currentAccountName"></li>    
        </ul>
    </div>
</div>
<g:render template="generalAccountView" />
<input type="hidden" id="currentAccountId">
<div class="row-fluid" style="display: none;">   
    <ul class="nav nav-tabs span12">
        <li class="active">
            <a href="#/admin/account/summary/"><g:message code="common.summary"/></a>
        </li>
        <li>
            <a href="#/admin/account/infrastructure/"><g:message code="menu.admin.cloud.infrastructure"/></a>
        </li>
        <li>
            <a href="#/admin/account/invoice/"><g:message code="menu.admin.billing.invoice"/></a>
        </li>

        <li>
            <a href="#/admin/account/payment/"><g:message code="menu.admin.billing.payments"/></a>
        </li>
        <li>
            <a href="#/admin/account/recurringItem/"><g:message code="common.recurringItem"/></a>
        </li>
    </ul>
</div>
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="accountSummaryDetailsTabCointainer" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.summary"/>">
            <div  class="new-user">
                <div class="row-fluid header">    
                  <!--<h4>Summary</h4>-->
                </div> 
                <div class="row-fluid">
                    <div class="value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="summaryCurrencyValue"></span></div>  
                </div> 
                <div class="row-fluid">
                    <div id="chartLodDiv" style="display: block;"><img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p><g:message code="common.message.loading"/></p></div>
                    <div id="chartDiv" style="display: none;">
                        <div class="span12">
                            <div class="span6">
                                <g:message code="common.summary.invoice"/>
                                <div id="invoiceSummaryChart" style="width: 400px; height: 400px;"></div>
                            </div>
                            <div class="span6">
                                <g:message code="common.summary.payment"/>
                                <div class="row-fluid">
                                    <div class="span12">
                                        <div id="paymentSummaryChart" style="width: 400px; height: auto;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <g:message code="common.summary.customAndRecurring"/>
                                <div id="customItemChart" style="width: 400px; height: 300px;"></div>
                                <div id="legend"></div>
                            </div>
                            <div class="span6" id="supportChartDiv">
                                <g:message code="common.summary.support"/>
                                <div id="supportChart" style="width: 400px; height: 300px;"></div>
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="accountSummaryInfraDetailsTab" style="overflow: visible;" title="<g:message code="menu.admin.cloud.userInfraLimitSummary"/>" onshow="Summary.showInfraDetailsTab()">
<!--            <div class="row-fluid">
                <div class="span12 breadcrumbs">
                    <ul>
                        <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
                        <li>/</li>
                        <li><a href="#/user/cloud/"><g:message code="menu.admin.cloud"/></a></li>
                        <li>/<li>   
                        <li><g:message code="common.limitSummary"/></li>
                    </ul>
                </div>
            </div>-->
        
                <div class="row-fluid">
                    <div class="pull-right">
                        <button data-dojo-type="dijit.form.Button" class="defaultbtn customBtn" onclick="Summary.openInfraLimitUpdateTab()"
                                title="<g:message code="common.cancel"/>">
                          <g:message code="common.updateLimit"/>
                        </button></div>  
                </div> 
        
            </br>
            <div class="table-wrapper products-table">
                <div class="row-fluid head">
                    <div id="resourceLimitDivLoad">

                    </div>
         <div id="resourceLimitDataDiv" style="display: none;">

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
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.admin.cloud.infrastructure"/>" onshow="InfrastructureInfo.populateValues()">
            <g:render template="infrastructure"/>       
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.admin.billing.invoice"/>" onshow="CurrentInvoiceInfo.populateValues();">
            <g:render template="userInvoiceInfo"/> 
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.admin.billing.payments"/>" onshow="PyamentsInfo.populateValues();">
            <g:render template="payment"/> 
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.recurringItem"/>" onshow=" RecurringListInfo.populateValues();">
            <g:render template="currentRecurringItem"/> 
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="accountSummaryInfraUpdateTab" title="<g:message code="common.infraLimit"/>" onshow="Summary.showResourceUpdateTab();">
            <div class="row-fluid">
                <form id="updateResourceLimitForm" data-dojo-type="dijit.form.Form" class="form-horizontal span8">                    
                    <div id="updateResourceLimitPage">
                        <div class="span12 field-box control-group">
                            <label for="retailInstanceLimit" style="margin-left: 20px" class="control-label settings_lable">                          
                                <g:message code="common.retailInstanceLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Instance Limit', promptMessage:'Enter Instance Limit', regExp:'[0-9-]{1,200}'" name="retailInstanceLimit" id="retailInstanceLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="retailStorageLimit" class="control-label settings_lable">                         
                                <g:message code="common.retailStorageLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Storage Limit', promptMessage:'Enter Storage Limit', regExp:'[0-9-]{1,200}'" name="retailStorageLimit" id="retailStorageLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="retailSnapshotLimit" class="control-label settings_lable">                        
                                <g:message code="common.retailSnapshotLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Snapshot Limit', promptMessage:'Enter Snapshot Limit', regExp:'[0-9-]{1,200}'" name="snapshotLimit" id="retailSnapshotLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="retailNetworkLimit" class="control-label settings_lable">                        
                                <g:message code="common.retailNetworkLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Network Limit', promptMessage:'Enter Network Limit', regExp:'[0-9-]{1,200}'" name="retailNetworkLimit" id="retailNetworkLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="retailPublicIPLimit" class="control-label settings_lable">                        
                                <g:message code="common.retailPublicIPLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Public IP Limit', promptMessage:'Enter Public IP Limit', regExp:'[0-9-]{1,200}'" name="retailPublicIPLimit" id="retailPublicIPLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="retailVpcLimit" class="control-label settings_lable">                        
                                <g:message code="common.retailVpcLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter VPC Limit', promptMessage:'Enter VPC Limit', regExp:'[0-9-]{1,200}'" name="retailVpcLimit" id="retailVpcLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="retailCpuLimit" class="control-label settings_lable">                        
                                <g:message code="common.retailCpuLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter CPU Limit', promptMessage:'Enter CPU Limit', regExp:'[0-9-]{1,200}'" name="retailCpuLimit" id="retailCpuLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="retailMemoryLimit" class="control-label settings_lable">                        
                                <g:message code="common.retailMemoryLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Memory Limit', promptMessage:'Enter Memory Limit', regExp:'[0-9-]{1,200}'" name="retailMemoryLimit" id="retailMemoryLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="primaryStorageLimit" class="control-label settings_lable">                        
                                <g:message code="common.primaryStorageLimit"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Primary Storage Limit', promptMessage:'Enter Primary Storage Limit', regExp:'[0-9-]{1,200}'" name="primaryStorageLimit" id="primaryStorageLimit">
                            </div>
                        </div>
                        <div class="span12 field-box control-group">
                            <label for="secondaryStorageLimit" class="control-label settings_lable">                        
                                <g:message code="common.secondaryStorageLimits"/>:<span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid No.', required: 'required', placeHolder: 'Enter Secondary Storage Limit', promptMessage:'Enter Secondary Storage Limit', regExp:'[0-9-]{1,200}'" name="secondaryStorageLimit" id="secondaryStorageLimit">
                            </div>
                        </div>
                    </div>   
                </form>
            </div>
            <div class="row-fluid  span8">
                <div class="pull-right">
                    <button data-dojo-type="dijit.form.Button" onclick="ResourceLimitForUser.update();" class="primarybtn">
                        <g:message code="common.update"/>
                    </button>
                    <button data-dojo-type="dijit.form.Button" onclick="ResourceLimitForUser.closeResourceLimitDialog();" class="cancelbtn">
                        <g:message code="common.cancel"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="accountProcessLoader2" class="span6">
    <div class="row-fluid" id="processPaymentMessage" style="display: block">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing"/></p>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="cancelDialog" title="<g:message code="common.accounts.cancel"/>" class="customDialgue">      
    <div  class="span4">
        Please confirm that you want to cancel this account.
        <div class="row-fluid offset1">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
            onclick="Summary.cancelAccount()" id="" >
            <g:message code="common.yes"/>
            </button>
            <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
            onclick="Summary.closeCancelAccountDialog()">
            <g:message code="common.no"/>
            </button> 
        </div>

    </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="enableDialog"  title="<g:message code="common.accounts.enableAccount"/>" style="color: black; width: 350px; height: auto">
    <g:message code="common.accounts.enableAccountInfo"/>
    <div style="margin-left: 100px">
        <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
        onclick="Summary.enableAccount()" id="" >
        <g:message code="common.yes"/>
        </button>
        <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
        onclick="Summary.closeEnableAccountDialog()">
        <g:message code="common.no"/>
        </button> 
    </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="disableDialog" 
title="<g:message code="common.accounts.disableAccount"/>" style="color: black; width: 350px; 
height: 150px">
<g:message code="common.accounts.disableAccountInfo"/>
<div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
    onclick="Summary.disableAccount()" id="" >
    <g:message code="common.yes"/>
</button>
<button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
onclick="Summary.closeDisableAccountDialog()">
<g:message code="common.no"/>
</button> 
</div>  
</div>
<div data-dojo-type="dijit.Dialog" id="suspendDialog" title="<g:message code="common.accounts.suspendAccount"/>" class="span9">
    <g:message code="common.accounts.suspendAccountInfo"/>
    <div class="row-fluid">
        <textarea class="span12" rows="12" cols="250" id="suspendTextContent"></textarea>
    </div>

    <div style="margin-left: 100px">
        <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="Summary.suspendAccount()" id="" >
            <g:message code="common.yes"/>
        </button>
        <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="Summary.closeSuspendAccountDialog()">
            <g:message code="common.no"/>
        </button> 
    </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="refundDialog" title="<g:message code="common.refundAccount"/>"  class="span8">
    <g:message code="common.accounts.refunfAccountInfo"/>
    <div class="new-user"> 
        <div class="row-fluid form-wrapper"> 
            <div class="span12"> 
                <div class="container">
                    <form id="" data-dojo-type="dijit.form.Form" class="form-horizontal">
                        <div class="row-fluid" id='refundFormPage'>
                            <div class="control-group span6"> 
                                <label for="refundAmount" class="control-label">
                                    <span class="require">*</span>
                                    <g:message code="common.billing.refundAmount"/>
                                </label>
                                <div class="controls">
                                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="required:'true', constraints:{min: 1, max: 10000000, pattern:'#.00'}, placeHolder: 'value', promptMessage:'value', invalidMessage:'Invalid Value.'" id="refundAmount">  
                                </div>
                            </div> 
                            <div class="control-group span10">
                                <label for="refundDescription" class="control-label">
                                    <span class="require">*</span>
                                    <g:message code="common.description"/>
                                </label>
                                <div class="controls">
                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="promptMessage:'<g:message code="common.description.prompt"/>',  missingMessage: '<g:message code="common.description.required"/>',required: true,  placeHolder: '<g:message code="common.description"/>'"  name="description" id="refundDescription"> 
                                </div>  
                            </div>
                        </div>
                    </form> 
                </div>
            </div>
        </div>
    </div>
    <div style="margin-left: 100px">
        <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
        onclick="Summary.refundAccount()" id="" >
        <g:message code="common.yes"/>
        </button>
        <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
        onclick="Summary.closeRefundAccountDialog()">
        <g:message code="common.no"/>
        </button> 
    </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="updateCreditLimitDialog" class="span4">    
    <div class="row-fluid">
        <form id="updateCreditLimitForm" data-dojo-type="dijit.form.Form" class="form-horizontal">                    
            <div id="updateCreditLimitPage">
                <div class="row-fluid">
                    <div class="control-group">
                        <label for="tierName" class="control-label" style="width: 140px !important;">
                            <g:message code="common.currentCreditLimit"/>                             
                        </label>
                        <div class="controls" style="margin: 5px 0 0 10px;">
                            <span id="updateCurrentCreditLimit"></span>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tierNetworkOffering" class="control-label">
                            <g:message code="common.due"/>                            
                        </label>
                        <div class="controls " style="margin: 5px 0 0 141px !important;">
                            <span id="updateCurrentDue"></span>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tierGateway" class="control-label">
                            <g:message code="common.newLimit"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls span7" style="margin-left: 30px !important">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', 
                                   required: 'required', placeHolder: '<g:message code="common.enterValue"/>', promptMessage:'<g:message code="common.enterValue"/>', regExp:'[0-9]{1,200}'"  name="updateCreditLimit" id="updateCreditLimit"> 
                        </div>
                    </div>                                       
                </div>
            </div>   
        </form>
    </div>
    <div class="row-fluid">
        <div class="pull-right">
            <button data-dojo-type="dijit.form.Button" onclick="ViewCurrentAccountInfo.showUpdateCreitLimitConfirm();" class="primarybtn">
                <g:message code="common.ok"/>
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="ViewCurrentAccountInfo.cancelCreditLimit();" class="cancelbtn">
                <g:message code="common.cancel"/>
            </button>
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcUpdateCreditConfirm" title="<g:message code="common.ip.releaseIP"/>" class="span4">     
    <div class="row-fluid">        
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>        
        <div class="span10">            
            <div class="span12"><p><g:message code="common.user.ip.releaseNetwork"/></p></div>        
        </div>
    </div> 
    <div class="row-fluid">        
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewCurrentAccountInfo.updateCreditLimit()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewCurrentAccountInfo.cancelCreditConfirm()"><g:message code="common.cancel"/></button>    
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="updateResourceLimitDialog" style="overflow: hidden;" class="span8" title="<g:message code="common.resourceUpdate"/>" >    
    
</div>
