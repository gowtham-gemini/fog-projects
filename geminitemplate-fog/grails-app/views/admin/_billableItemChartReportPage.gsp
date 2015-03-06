<div class="row">
	<div class="col-md-12 breadcrumbs">
  	<ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>   
            <li><a href="#/admin/reports"><g:message code="menu.admin.report"/></a></li>
            <li>/</li>
            <li><g:message code="menu.admin.report.billableItemChart"/></li>
  	</ul>
	</div>
</div>
<div class="row">   
<ul class="nav nav-tabs col-md-12 customNav">
  <li>
    <a href="#/admin/reports/billableItem"><g:message code="menu.admin.report.billableItem"/></a>
  </li>
  <li class="active">
      <a href="#/admin/reports/billableItemChartReport"><g:message code="menu.admin.report.billableItemChart"/></a>
    </li> 
</ul>
</div>
<div class="row">
    <div class="value_dollar pull-right"><g:message code="default.valueIn"/>   <span  id="billableItemChartReportCurrencyValue"></span></div>  
</div>
<div class="row" style="background:#F7F7F7;padding:8px;width:auto;border:1px solid #DAE1E8;">
<div class="col-md-12">
  <form class="form-horizontal" id="billableItemReportChartForm" data-dojo-type="dijit.form.Form">
    <div class="row">
      <div class="col-md-3 field-box">
            <label for="forDateRange" class="control-label">              
              <g:message code="common.dateRange"/> :
               <span  class="require">*</span>
            </label>
			<div class="controls updatable elements">
            <select data-dojo-type="dijit.form.Select" name="forDateRange"
                       data-dojo-props="maxHeight: -1" onchange="BillableItemChartReport.showDate(this.value)">
                <option value="ALL"><g:message code="common.all"/></option>
                <option value="SELECTIVE"><g:message code="common.period"/></option>
           </select>
		   </div>
      </div>
      <div id="billableItemDateRangeDiv" style="display: none">
        <div class="col-md-4 field-box">
            <label for="reportFromDate" class="control-label">              
              <g:message code="common.fromDate"/>:
               <span  class="require">*</span>
            </label>
            <div class="controls updatable elements">
            <input type="text" name="reportFromDate" data-dojo-id="billableItemChartReportFromDate" onChange="billableItemChartReportToDate.constraints.min = arguments[0];"
                data-dojo-type="dijit.form.DateTextBox"
                data-dojo-props="required:'true', 
                placeHolder: '<g:message code="common.date.placeholder"/>',
                promptMessage:'<g:message code="common.date.prompt"/>', 
                invalidMessage:'<g:message code="common.date.invalid"/>', 
                constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>
            </div>
        </div>
        <div class="col-md-4 field-box">
          <label for="reportToDate" class="control-label">            
            <g:message code="common.toDate"/> :
             <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <input type="text" name="reportToDate" data-dojo-id="billableItemChartReportToDate" onChange="billableItemChartReportFromDate.constraints.max = arguments[0];"
                data-dojo-type="dijit.form.DateTextBox" data-dojo-props="required:'true', placeHolder: 'DD-MM-YYYY',
                     promptMessage:'To date (DD-MM-YYYY)', invalidMessage:'Invalid date.', constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>  			</div>
        </div> 
      </div>
    </div>
    <div class="row">
      <div class="col-md-3 field-box">
          <label for="forZone" class="control-label">            
            <g:message code="common.zoneBased"/>:
             <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <div id="billableItemZoneList"></div>
          </div>
      </div>
    </div> 
    <div class="row">
      <div class="col-md-3 field-box">
          <label for="forZone" class="control-label">            
             <span  id="billableItemLable"><g:message code="common.forNonZone"/></span> <g:message code="common.billableItem"/>:
             <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
              <select data-dojo-type="dijit.form.Select" name="forBillableItem" id="forBillableItem"
                      data-dojo-props="maxHeight: -1" onchange="BillableItemChartReport.showBillableItemList(this.value)">
                    <option value="ALL"><g:message code="common.all"/></option>
                    <option value="SELECTIVE"><g:message code="common.selective"/></option>
              </select>
          </div>
      </div>
      <div class="col-md-8 field-box" id="billableItemListDiv" style="display: none">
        <label for="billableItemList" class="control-label">          
          <g:message code="common.billableItem"/><g:message code="common.billableItem.selectInfo"/> :
           <span  class="require">*</span>
        </label>
        <div class="controls updatable elements">
          <div id="billableItemList"></div>
        </div> 
      </div> 
    </div>
    <div class="row">
      <div class="col-md-3 field-box">
          <label for="forAccount" class="control-label">            
            <g:message code="common.forAccount"/>:
             <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <div id="billableItemAccountList"></div>
          </div>
      </div>
      <div class="col-md-3 field-box" id="planListDiv" style="display: none">
          <label for="plan" class="control-label">            
            <g:message code="common.forPlan"/>:
             <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <div id="billableItemPlanList"></div>
          </div>
      </div>
    </div>
     <div class="col-md-12"></div>
    <div class="row">
      <button data-dojo-type="dijit.form.Button" onclick="BillableItemChartReport.generate();" class="generatebtn">
         <g:message code="default.button.generate.common"/>
      </button>
      <button data-dojo-type="dijit.form.Button" onclick="BillableItemChartReport.cancel();" class="cancelbtn">
         <g:message code="common.cancel"/>
      </button>
    </div>
    </form>
  </div>
</div> 
<div class="alert alert-danger errorMessage" id="billableItemErrorDiv">
   <span  id="errorContent">
    <g:message code="common.pleaseVerifyDate"/>
  </span>
</div>
<div class="row">
   <div class="col-md-6">
     <span  id="amountSummary"></span>
    <div id="reportChart"></div>
  </div>
  <div class="col-md-6">
     <span  id="countSummary"></span>
    <div id="reportCountChart"></div>
  </div>
</div>
