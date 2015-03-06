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
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.summary"/>" selected="true">
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
                                <div class="span10 resource_items_cont" id="gigabytesLimitDiv">
                                    <div class="span3 resource_items_memory"> Volume Storage </div>
                                    <div class="span4 resource_items_allocation" id="gigabytesLimitInfo"></div>
                                    <div class="span4 resource_items_progress pull-right"> 
                                        <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="gigabytesLimitLoader" id="gigabytesLimitLoader"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span10 resource_items_cont" id="PrimaryStorageLimitDiv">
                                    <div class="span3 resource_items_memory">Security Groups</div>
                                    <div class="span4 resource_items_allocation" id="securityGroupsInfo"></div>
                                    <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                                    <div class="span4 resource_items_progress pull-right"> 
                                        <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="securityGroupsLimitLoader" id="securityGroupsLimitLoader"></div>
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

                        </div>
                    </div>
                </div>      
            </div>
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
            <g:render template="currentLimitPage" /> 
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="accountProcessLoader2" class="span8">
    <div class="row-fluid" id="processPaymentMessage" style="display: block">
        <img src="images/vmload.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing"/></p>
    </div>
  <!--    <div class="row-fluid hide_lable" id="successMessage">
      <img src="images/successMsg.jpg" class="span1 spacing"/>
      <p class="message span9 success">Success! Fogpanel and cloudstack has integrated</p>
      </div>-->
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
<div data-dojo-type="dijit.Dialog" id="updateResourceLimitDialog" style="overflow: hidden;" class="span8" title="<g:message code="common.resourceUpdate"/>" >    
    
</div>