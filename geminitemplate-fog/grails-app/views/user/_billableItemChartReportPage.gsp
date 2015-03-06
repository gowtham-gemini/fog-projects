<div class="row">
  <div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/reports"><g:message code="menu.user.report.stats"/></a></li>
    <li>/<li>
    <li><g:message code="menu.user.report.statsBillableItemChart"/></li>
  </ul>
</div>
</div>

<div class="row">   
<ul class="nav nav-tabs col-md-12 customNav">
  <li>
    <a href="#/user/reports/billableItem"><g:message code="menu.user.report.billableItemReport"/></a>
  </li>
  <li class="active">
      <a href="#/user/reports/billableItemChartReport"><g:message code="menu.user.report.billableItemChartReport"/></a>
    </li> 
</ul>
</div>
<div class="row">
  <div class="value_dollar"><g:message code="default.valueIn"/> <span  id="billableItemChartValue"></span></div>  
</div>
<div class="row" style="background:#F7F7F7;padding:8px;width:auto;border:1px solid #DAE1E8;">
<div class="col-md-12">
  <form class="form-horizontal" id="billableItemReportChartForm" data-dojo-type="dijit.form.Form">
    <input type="hidden" id="forAccount" name="forAccount">
    <div class="row">
      <div class="col-md-3 field-box">
            <label for="forDateRange" class="control-label">
              
              <g:message code="user.report.dateRange"/> : <span  class="require">*</span>
            </label>
			<div class="controls updatable elements">
            <select data-dojo-type="dijit.form.Select" name="forDateRange" data-dojo-props="maxHeight: -1" onchange="BillableItemChartReport.showDate(this.value)">
               <option value="ALL"><g:message code="common.ticket.status.all"/></option>
               <option value="SELECTIVE"><g:message code="common.period"/></option>
           </select>
		   </div>
      </div>         

      <div id="billableItemDateRangeDiv" style="display: none">
        <div class="col-md-4 field-box">
            <label for="reportFromDate" class="control-label">
              
              <g:message code="common.fromDate"/>: <span  class="require">*</span>
            </label>
			<div class="controls updatable elements">
            <input type="text" name="reportFromDate" id="reportFromDate" data-dojo-id="billableItemChartUserFromDate" 
            onChange="billableItemChartUserToDate.constraints.min = arguments[0];"
            data-dojo-type="dijit.form.DateTextBox" data-dojo-props="required:'true',
            placeHolder: '<g:message code="common.date.placeholder"/>', promptMessage:'<g:message code="common.date.prompt"/>',
            invalidMessage:'<g:message code="common.date.invalid"/>', constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>
			</div>
        </div>
         
        <div class="col-md-4 field-box">
          <label for="reportToDate" class="control-label">
            
            <g:message code="common.toDate"/> : <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <input type="text" name="reportToDate" id="reportToDate" 
            data-dojo-id="billableItemChartUserToDate"
            onChange="billableItemChartUserFromDate.constraints.max = arguments[0];"
            data-dojo-type="dijit.form.DateTextBox" data-dojo-props="required:'true', 
            placeHolder: '<g:message code="common.date.placeholder"/>', promptMessage:'<g:message code="common.date.prompt"/>',
            invalidMessage:'<g:message code="common.date.invalid"/>', constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>  	
          </div>
        </div> 
      </div>
    </div>
    <div class="row">
      <div class="col-md-3 field-box">
          <label for="forZone" class="control-label">
            
             <g:message code="user.report.zoneBased"/> : <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <div id="billableItemZoneList"></div>
          </div>
      </div>
    </div>          
    <div class="row">
      <div class="col-md-3 field-box">
          <label for="forZone" class="control-label">            
              <span  id="billableItemLable"><g:message code="user.report.forNonZone"/></span> <g:message code="menu.user.report.billableItem"/>: <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
              <select data-dojo-type="dijit.form.Select" name="forBillableItem" id="forBillableItem"
                      data-dojo-props="maxHeight: -1" onchange="BillableItemChartReport.showBillableItemList(this.value)">
                  <option value="ALL"><g:message code="common.ticket.status.all"/></option>
                 <option value="SELECTIVE"><g:message code="common.selective"/></option>
              </select>
          </div>
      </div>
      <div class="col-md-8 field-box" id="billableItemListDiv" style="display: none">
        <label for="billableItemList" class="control-label">
          
          <g:message code="menu.user.report.billableItem"/>(<g:message code="common.use"/> ',' <g:message code="common.multiple"/> : <span  class="require">*</span>
        </label>
        <div class="controls updatable elements">
          <div id="billableItemList"></div>
        </div> 
      </div> 
    </div>
    <div class="row">
      <div class="col-md-3 field-box" id="planListDiv" style="display: none">
          <label for="plan" class="control-label">
            
            <g:message code="user.report.forPlan"/>: <span  class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <div id="billableItemPlanList"></div>
          </div>
      </div>
    </div>
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
    <g:message code="user.report.pleaseVerify"/>
  </span>
</div>
<div class="row" id="noReportDataDiv" style="display: none;">
  <div class="col-md-12 db_graph">
  <div class="create_item_box col-md-10 offset1">
      <h1>
       <span ><g:message code="common.noData"/></span>
      </h1>
  </div>
</div>
</div>
<div class="row" id="reportDataChartDiv">
   <div class="col-md-6">
     <span  id="amountSummary"></span>
    <div id="reportChart"></div>
  </div>
  <div class="col-md-6">
     <span  id="countSummary"></span>
    <div id="reportCountChart"></div>
  </div>
</div>
